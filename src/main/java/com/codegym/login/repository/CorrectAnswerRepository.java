package com.codegym.login.repository;

import com.codegym.login.model.CorrectAnswer;
import com.codegym.login.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorrectAnswerRepository extends JpaRepository<CorrectAnswer, Long> {
    Iterable<CorrectAnswer> findAllByQuestion(Question question);
}
