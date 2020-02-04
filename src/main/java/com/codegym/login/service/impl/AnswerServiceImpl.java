package com.codegym.login.service.impl;

import com.codegym.login.model.Answer;
import com.codegym.login.model.Question;
import com.codegym.login.repository.AnswerRepository;
import com.codegym.login.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    @Override
    public Iterable<Answer> findAllByQuestion(Question question) {
        return answerRepository.findAllByQuestion(question);
    }

    @Override
    public Iterable<Answer> findAll() {
        return answerRepository.findAll();
    }

    @Override
    public Optional<Answer> findById(Long id) {
        return answerRepository.findById(id);
    }

    @Override
    public void save(Answer model) {
        answerRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        answerRepository.deleteById(id);
    }
}
