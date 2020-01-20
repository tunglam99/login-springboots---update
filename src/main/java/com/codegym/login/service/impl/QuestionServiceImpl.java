package com.codegym.login.service.impl;

import com.codegym.login.model.Question;
import com.codegym.login.repository.QuestionRepository;
import com.codegym.login.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Override
    public Iterable<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Optional<Question> findById(Long id) {
        return questionRepository.findById(id);
    }

    @Override
    public void save(Question model) {
        questionRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        questionRepository.deleteById(id);
    }
}
