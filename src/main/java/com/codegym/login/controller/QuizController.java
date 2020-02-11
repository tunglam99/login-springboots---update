package com.codegym.login.controller;

import com.codegym.login.model.Quiz;
import com.codegym.login.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin("*")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @GetMapping
    public ResponseEntity<Iterable<Quiz>> listQuiz(){
        Iterable<Quiz> quizzes = quizService.findAll();
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Quiz> quizDetail(@PathVariable Long id){
        Optional<Quiz> quizOptional = quizService.findById(id);
        return quizOptional.map(quiz -> new ResponseEntity<>(quiz,HttpStatus.OK)).
                orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
