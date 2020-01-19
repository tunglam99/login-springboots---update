package com.codegym.login.service;

import com.codegym.login.model.Category;

public interface CategoryService extends  GeneralService<Category> {
    Category findByName(String categoryName);
}
