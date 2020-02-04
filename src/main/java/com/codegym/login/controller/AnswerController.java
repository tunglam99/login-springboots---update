package com.codegym.login.controller;

import com.codegym.login.model.Answer;
import com.codegym.login.model.Question;
import com.codegym.login.service.AnswerService;
import com.codegym.login.service.QuestionService;
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
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/answers/{questionId}")
    public ResponseEntity<Iterable<Answer>> showAllByQuestion(@PathVariable Long questionId){
        Optional<Question> questionOptional = questionService.findById(questionId);
        if (!questionOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Iterable<Answer> answers = answerService.findAllByQuestion(questionOptional.get());
        return new ResponseEntity<>(answers,HttpStatus.OK);
    }

    @GetMapping("/getAnswer/{id}")
    public ResponseEntity<Answer> getAnswerDetail(@PathVariable Long id){
        Optional<Answer> answer = answerService.findById(id);
        return answer.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).
                orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
