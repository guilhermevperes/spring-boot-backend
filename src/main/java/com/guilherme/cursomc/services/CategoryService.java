package com.guilherme.cursomc.services;

import java.util.Optional;

import com.guilherme.cursomc.domain.Category;
import com.guilherme.cursomc.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public Category find(Integer id) {
        Optional<Category> obj = repo.findById(id);
        return obj.orElse(null);
    }

}
