package com.devsupeior.dscommerce.services;


import com.devsupeior.dscommerce.dto.ProductDto;
import com.devsupeior.dscommerce.entities.Product;
import com.devsupeior.dscommerce.repositories.ProductRepository;
import com.devsupeior.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        Product result = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Recurso nao encontrado"));
        return new ProductDto(result);
    }

    @Transactional(readOnly = true)
    public Page<ProductDto> findAll(Pageable pageable) {
        Page<Product> result = productRepository.findAll(pageable);
        return result.map(product -> new ProductDto(product));
    }

    @Transactional
    public ProductDto insert(ProductDto productDto) {
        Product entity = new Product();
        entity.setName(productDto.getName());
        entity.setDescription(productDto.getDescription());
        entity.setPrice(productDto.getPrice());
        entity.setImgUrl(productDto.getImgUrl());

        entity = productRepository.save(entity);
        return new ProductDto(entity);
    }


    @Transactional
    public ProductDto update(Long id, ProductDto productDto) {
        Product entity = productRepository.getReferenceById(id); // Busca a referencia pelo id

        entity.setName(productDto.getName()); // seta os valores na entidade
        entity.setDescription(productDto.getDescription()); // seta os valores na entidade
        entity.setPrice(productDto.getPrice()); // seta os valores na entidade
        entity.setImgUrl(productDto.getImgUrl()); // seta os valores na entidade

        entity = productRepository.save(entity); // salva a entidade no banco
        return new ProductDto(entity); // retorna a entidade para acesso na camada de controler
    }

    @DeleteMapping
    public void delete(@PathVariable long id){
        productRepository.deleteById(id);
    }
}


