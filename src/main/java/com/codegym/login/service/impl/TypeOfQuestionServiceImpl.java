package com.codegym.login.service.impl;

import com.codegym.login.model.TypeOfQuestion;
import com.codegym.login.repository.TypeOfQuestionRepository;
import com.codegym.login.service.TypeOfQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeOfQuestionServiceImpl implements TypeOfQuestionService {
    @Autowired
    private TypeOfQuestionRepository typeOfQuestionRepository;

    @Override
    public TypeOfQuestion findByName(String typeOfQuestionName) {
        return typeOfQuestionRepository.findByName(typeOfQuestionName);
    }

    @Override
    public Iterable<TypeOfQuestion> findAll() {
        return typeOfQuestionRepository.findAll();
    }

    @Override
    public Optional<TypeOfQuestion> findById(Long id) {
        return typeOfQuestionRepository.findById(id);
    }

    @Override
    public void save(TypeOfQuestion model) {
        typeOfQuestionRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        typeOfQuestionRepository.deleteById(id);
    }
}
