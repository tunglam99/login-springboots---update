package com.codegym.login.service.impl;

import com.codegym.login.model.Quiz;
import com.codegym.login.repository.QuizRepository;
import com.codegym.login.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizRepository quizRepository;
    @Override
    public Iterable<Quiz> findAll() {
        return quizRepository.findAll();
    }

    @Override
    public Optional<Quiz> findById(Long id) {
        return quizRepository.findById(id);
    }

    @Override
    public void save(Quiz model) {
        quizRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        quizRepository.deleteById(id);
    }
}
