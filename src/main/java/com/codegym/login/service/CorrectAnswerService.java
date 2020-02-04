package com.codegym.login.service;

import com.codegym.login.model.CorrectAnswer;
import com.codegym.login.model.Question;

public interface CorrectAnswerService extends GeneralService<CorrectAnswer> {
    Iterable<CorrectAnswer> findAllByQuestion(Question question);
}
