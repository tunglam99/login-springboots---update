package com.codegym.login.controller;


import com.codegym.login.model.Category;
import com.codegym.login.model.Question;
import com.codegym.login.model.TypeOfQuestion;
import com.codegym.login.service.CategoryService;
import com.codegym.login.service.QuestionService;
import com.codegym.login.service.TypeOfQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TypeOfQuestionService typeOfQuestionService;

    @PostMapping("/questions")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        questionService.save(question);
        return new ResponseEntity<>(question,HttpStatus.CREATED);
    }

    @DeleteMapping("/questions/{id}")
    public ResponseEntity<Question> deleteQuestion(@PathVariable Long id) {
        Optional<Question> question = questionService.findById(id);
        if (!question.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        questionService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/questions/{id}")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question, @PathVariable Long id) {
        Optional<Question> questionOptional = questionService.findById(id);
        if (!questionOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        question.setId(questionOptional.get().getId());
        questionService.save(question);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @GetMapping("/findAllQuestionByCategoryAndStatusIsTrue")
    public ResponseEntity<Iterable<Question>> findAllQuestionByCategoryAndStatusIsTrue(@RequestParam("category") String category){
        Category currentCategory = categoryService.findByName(category);
        if (currentCategory == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Iterable<Question> questions = questionService.findAllByCategoryAndStatusIsTrue(currentCategory);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    @GetMapping("/findAllQuestionByContentContainingAndStatusIsTrue")
    public ResponseEntity<Iterable<Question>> findAllQuestionByContentContainingAndStatusIsTrue(@RequestParam("content")
                                                                                                String content) {
        Iterable<Question> questions = questionService.findAllByContentContainingAndStatusIsTrue(content);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    @GetMapping("/findAllQuestionByTypeOfQuestion")
    public ResponseEntity<Iterable<Question>> findAllQuestionByTypeOfQuestion(@RequestParam("typeOfQuestion")String typeOfQuestion){
        TypeOfQuestion currentTypeOfQuestion = typeOfQuestionService.findByName(typeOfQuestion);
        if (currentTypeOfQuestion == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Iterable<Question> questions = questionService.findAllQuestionByTypeOfQuestion(currentTypeOfQuestion);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }
}
