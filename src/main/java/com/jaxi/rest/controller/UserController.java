package com.jaxi.rest.controller;

import com.jaxi.common.JaxiUserPrincipal;
import com.jaxi.entity.Canvasser;
import com.jaxi.entity.Coordinator;
import com.jaxi.entity.TeamLeader;
import com.jaxi.entity.User;
import com.jaxi.exception.PasswordEmptyException;
import com.jaxi.exception.UserAlreadyExistException;
import com.jaxi.repository.CanvasserRepository;
import com.jaxi.repository.TeamLeaderRepository;
import com.jaxi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TeamLeaderRepository teamLeaderRepository;

    @Autowired
    private CanvasserRepository canvasserRepository;


    //    @GetMapping("user/all")
//    @Cacheable("users")
    public List<User> findAll() {

        simulateSlowService();
        logger.info("agak lemot coy");
        return userRepository.findAll();
    }


    // Don't do this at home
    private void simulateSlowService() {
        logger.info("agak lemot coy xxx");
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }


    //@PostMapping("user/create")
    public ResponseEntity create(@Valid @RequestBody User user) {

        final User _user = userRepository.findByEmail(user.getEmail());
        if (_user != null) {
            throw new UserAlreadyExistException("User Already Exists");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
    }


    @GetMapping("/user/profile")
    public User profile(Authentication authentication) {
        JaxiUserPrincipal _principal = (JaxiUserPrincipal) authentication.getPrincipal();
        User _auth = _principal.getUser();
        return _auth;
    }


    @PostMapping("/user/changepassword")
    public void changePassword(@RequestBody User user, Authentication authentication) {

        logger.info("user " + user);

        String password = user.getPassword();
        if (StringUtils.isEmpty(password)) {
            throw new PasswordEmptyException("Password Not Empty");
        }
        JaxiUserPrincipal _principal = (JaxiUserPrincipal) authentication.getPrincipal();
        User _auth = _principal.getUser();
        User _db = userRepository.findById(_auth.getId()).get();
        _db.setPassword(passwordEncoder.encode(password));
        userRepository.save(_db);

        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @GetMapping("/user/contact")
    public ResponseEntity contact(Authentication authentication) {
        JaxiUserPrincipal _principal = (JaxiUserPrincipal) authentication.getPrincipal();
        User user = _principal.getUser();

        Set<User> users = new HashSet<>();


        if (user instanceof Canvasser) {

            Canvasser _cv = (Canvasser) user;
            if (_cv.getTeamLeader() != null) {
                users.add(_cv.getTeamLeader());
                if(_cv.getTeamLeader().getCoordinator() !=null) {
                    users.add(_cv.getTeamLeader().getCoordinator());
                }
            }
        }


        if (user instanceof TeamLeader) {
            TeamLeader _tl = (TeamLeader) user;
            if(_tl.getCoordinator() != null) {
                users.add(_tl.getCoordinator());
            }
            List<Canvasser> _cvs = canvasserRepository.findAllByTeamLeader(_tl);
            users.addAll(_cvs);
        }


        if (user instanceof Coordinator) {
            Coordinator _cd = (Coordinator) user;
            List<TeamLeader> _tls = teamLeaderRepository.findAllByCoordinator(_cd);
            users.addAll(_tls);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(
                users
        );

    }


}
