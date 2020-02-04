package com.codegym.login.controller;

import com.codegym.login.model.Answer;
import com.codegym.login.model.Question;
import com.codegym.login.service.AnswerService;
import com.codegym.login.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/answers")
    public ResponseEntity<Answer> createAnswer(@RequestBody Answer answer) {
        Optional<Question> questionOptional = questionService.findById(answer.getQuestion().getId());
        if (!questionOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        answerService.save(answer);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @PutMapping("/answers/{id}")
    public ResponseEntity<Answer> updateAnswer(@RequestBody Answer answer, @PathVariable Long id) {
        Optional<Answer> answerOptional = answerService.findById(id);
        if (!answerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        answer.setId(answerOptional.get().getId());
        answerService.save(answer);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @DeleteMapping("/answers/{id}")
    public ResponseEntity<Answer> deleteAnswer(@PathVariable Long id) {
        Optional<Answer> answerOptional = answerService.findById(id);
        if (!answerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        answerService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
