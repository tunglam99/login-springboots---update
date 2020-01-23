package com.codegym.login.repository;

import com.codegym.login.model.TypeOfQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeOfQuestionRepository extends JpaRepository<TypeOfQuestion, Long> {
    TypeOfQuestion findByName(String typeOfQuestionName);
}
