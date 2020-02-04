package com.codegym.login.repository;

import com.codegym.login.model.Answer;
import com.codegym.login.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Iterable<Answer> findAllByQuestion(Question question);
}
