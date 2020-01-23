package com.codegym.login.service;

import com.codegym.login.model.TypeOfQuestion;

public interface TypeOfQuestionService extends GeneralService<TypeOfQuestion> {
    TypeOfQuestion findByName(String typeOfQuestionName);
}
