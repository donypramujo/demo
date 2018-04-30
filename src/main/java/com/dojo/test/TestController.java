package com.dojo.test;

import com.dojo.entity.Student;
import com.dojo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class TestController {


    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping("/students")
    public Iterable<Student> students(){
        return studentRepository.findAll();
    }


    @PostMapping("/create")
    public ResponseEntity createStudent(@Valid @RequestBody Student student){

        studentRepository.save(student);
       return ResponseEntity.ok().body("berak ");
    }
}
