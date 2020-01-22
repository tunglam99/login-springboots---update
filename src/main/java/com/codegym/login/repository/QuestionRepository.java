package com.codegym.login.repository;

import com.codegym.login.model.Category;
import com.codegym.login.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Iterable<Question> findAllByCategoryAndStatusIsTrue(Category category);
}
