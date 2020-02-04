package com.codegym.login.repository;

import com.codegym.login.model.Category;
import com.codegym.login.model.Question;
import com.codegym.login.model.TypeOfQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Iterable<Question> findAllByCategoryAndStatusIsTrue(Category category);
    Iterable<Question> findAllByContentContainingAndStatusIsTrue(String content);
    Iterable<Question> findAllQuestionByTypeOfQuestion(TypeOfQuestion typeOfQuestion);
}
