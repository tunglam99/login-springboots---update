package com.codegym.login.service;

import com.codegym.login.model.Category;
import com.codegym.login.model.Question;

public interface QuestionService extends GeneralService<Question> {
    Iterable<Question> findAllByCategoryAndStatusIsTrue(Category category);
    Iterable<Question> findAllByContentContainingAndStatusIsTrue(String content);
}
