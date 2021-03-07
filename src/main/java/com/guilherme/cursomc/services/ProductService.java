package com.guilherme.cursomc.services;

import java.util.List;
import java.util.Optional;

import com.guilherme.cursomc.domain.Category;
import com.guilherme.cursomc.domain.Product;
import com.guilherme.cursomc.repositories.CategoryRepository;
import com.guilherme.cursomc.repositories.ProductRepository;
import com.guilherme.cursomc.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public Product find(Integer id) {
        Optional<Product> obj = productRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Produto: " + id + " não encontrado"));
    }

    public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy,
            String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

        List<Category> categories = categoryRepository.findAllById(ids);
        return productRepository.search(name, categories, pageRequest);
    }

}
