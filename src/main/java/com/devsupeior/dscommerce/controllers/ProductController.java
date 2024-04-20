package com.devsupeior.dscommerce.controllers;


import com.devsupeior.dscommerce.dto.ProductDto;
import com.devsupeior.dscommerce.entities.Product;
import com.devsupeior.dscommerce.repositories.ProductRepository;
import com.devsupeior.dscommerce.services.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id){
        ProductDto productDto = productService.findById(id);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDto>> findAll(Pageable pageable){
        Page<ProductDto> productDto = productService.findAll(pageable);
        return ResponseEntity.ok(productDto);
    }


    @PostMapping
    public ResponseEntity<ProductDto> insert(@RequestBody ProductDto productDto){
        productDto = productService.insert(productDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(productDto.getId()).toUri();
        return ResponseEntity.created(uri).body(productDto);
    }





    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody ProductDto productDto){ // Recebe um id e um corpo
        productDto = productService.update(id, productDto);// Manda o DTO e o ID para a camada de servico, que faz a consulta
        return ResponseEntity.ok(productDto); // Retorna uma response para o usuario
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){ // Recebe um id
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
