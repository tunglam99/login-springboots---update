package com.codegym.login.service;

import com.codegym.login.model.Answer;
import com.codegym.login.model.Question;

public interface AnswerService extends GeneralService<Answer> {
    Iterable<Answer> findAllByQuestion(Question question);
}
