package com.codegym.login.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Boolean status;

    @ManyToOne
    private Category category;

    @ManyToOne
    private TypeOfQuestion typeOfQuestion;

    @ManyToOne
    private Quiz quiz;
}
