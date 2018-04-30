package com.jaxi.service.impl;

import com.jaxi.entity.TeamLeader;
import com.jaxi.entity.User;
import com.jaxi.repository.TeamLeaderRepository;
import com.jaxi.repository.UserRepository;
import com.jaxi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StringMultipartFileEditor;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
