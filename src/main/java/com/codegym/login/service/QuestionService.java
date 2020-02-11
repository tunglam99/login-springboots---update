package com.codegym.login.service;

import com.codegym.login.model.Category;
import com.codegym.login.model.Question;
import com.codegym.login.model.Quiz;
import com.codegym.login.model.TypeOfQuestion;

public interface QuestionService extends GeneralService<Question> {
    Iterable<Question> findAllByCategoryAndStatusIsTrue(Category category);
    Iterable<Question> findAllByContentContainingAndStatusIsTrue(String content);
    Iterable<Question> findAllQuestionByTypeOfQuestion(TypeOfQuestion typeOfQuestion);
    Iterable<Question> findAllByQuiz(Quiz quiz);
}
