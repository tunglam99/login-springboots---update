package com.codegym.login.service.impl;

import com.codegym.login.model.CorrectAnswer;
import com.codegym.login.model.Question;
import com.codegym.login.repository.CorrectAnswerRepository;
import com.codegym.login.service.CorrectAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CorrectAnswerServiceImpl implements CorrectAnswerService {
    @Autowired
    private CorrectAnswerRepository correctAnswerRepository;
    @Override
    public Iterable<CorrectAnswer> findAllByQuestion(Question question) {
        return correctAnswerRepository.findAllByQuestion(question);
    }

    @Override
    public Iterable<CorrectAnswer> findAll() {
        return correctAnswerRepository.findAll();
    }

    @Override
    public Optional<CorrectAnswer> findById(Long id) {
        return correctAnswerRepository.findById(id);
    }

    @Override
    public void save(CorrectAnswer model) {
        correctAnswerRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        correctAnswerRepository.deleteById(id);
    }
}
