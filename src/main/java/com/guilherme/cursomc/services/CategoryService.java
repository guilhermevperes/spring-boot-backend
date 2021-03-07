package com.guilherme.cursomc.services;

import java.util.Optional;

import com.guilherme.cursomc.domain.Category;
import com.guilherme.cursomc.repositories.CategoryRepository;
import com.guilherme.cursomc.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category find(Integer id) throws ObjectNotFoundException {
        Optional<Category> obj = categoryRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Categoria não encontrada Id: " + id + ", Tipo: " + Category.class.getName()));
    }

    public Category insert(Category category) {
        category.setId(null);
        return categoryRepository.save(category);
    }

    public Category update(Integer id, Category category) {
        find(id);
        category.setId(id);
        return categoryRepository.save(category);
    }

}
