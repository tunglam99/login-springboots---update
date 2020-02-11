package com.codegym.login.service.impl;

import com.codegym.login.model.Exam;
import com.codegym.login.repository.ExamRepository;
import com.codegym.login.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamRepository examRepository;
    @Override
    public Iterable<Exam> findAll() {
        return examRepository.findAll();
    }

    @Override
    public Optional<Exam> findById(Long id) {
        return examRepository.findById(id);
    }

    @Override
    public void save(Exam model) {
        examRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        examRepository.deleteById(id);
    }
}
