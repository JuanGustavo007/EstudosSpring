package com.devsupeior.dscommerce.controllers;


import com.devsupeior.dscommerce.dto.CategoryDto;
import com.devsupeior.dscommerce.dto.ProductDto;
import com.devsupeior.dscommerce.dto.ProductMinDto;
import com.devsupeior.dscommerce.services.CategoryService;
import com.devsupeior.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;



    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAll(){
        List<CategoryDto> categoryDtos = categoryService.findAll();
        return ResponseEntity.ok(categoryDtos);
    }

}
