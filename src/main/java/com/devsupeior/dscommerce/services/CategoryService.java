package com.devsupeior.dscommerce.services;


import com.devsupeior.dscommerce.dto.CategoryDto;
import com.devsupeior.dscommerce.dto.ProductDto;
import com.devsupeior.dscommerce.dto.ProductMinDto;
import com.devsupeior.dscommerce.entities.Category;
import com.devsupeior.dscommerce.entities.Product;
import com.devsupeior.dscommerce.repositories.CategoryRepository;
import com.devsupeior.dscommerce.repositories.ProductRepository;
import com.devsupeior.dscommerce.services.exceptions.DatabaseException;
import com.devsupeior.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
     private CategoryRepository categoryRepository;


    @Transactional(readOnly = true)
    public List<CategoryDto> findAll() {
        List<Category> result = categoryRepository.findAll();
        return result.stream().map(category -> new CategoryDto(category)).toList();
    }


}


