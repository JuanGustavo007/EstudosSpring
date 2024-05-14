package com.devsupeior.dscommerce.services;


import com.devsupeior.dscommerce.dto.ProductDto;
import com.devsupeior.dscommerce.dto.ProductMinDto;
import com.devsupeior.dscommerce.entities.Product;
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
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;

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
    public Page<ProductMinDto> findAll(String name, Pageable pageable) {
        Page<Product> result = productRepository.seachByName(name, pageable);
        return result.map(product -> new ProductMinDto(product));
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
        try{
            Product entity = productRepository.getReferenceById(id); // Busca a referencia pelo id

            entity.setName(productDto.getName()); // seta os valores na entidade
            entity.setDescription(productDto.getDescription()); // seta os valores na entidade
            entity.setPrice(productDto.getPrice()); // seta os valores na entidade
            entity.setImgUrl(productDto.getImgUrl()); // seta os valores na entidade

            entity = productRepository.save(entity); // salva a entidade no banco
            return new ProductDto(entity); // retorna a entidade para acesso na camada de controler
        }catch (EntityNotFoundException e){
          throw new ResourceNotFoundException("Recurso nao encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(@PathVariable long id){
        try {
            productRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }catch (DataIntegrityViolationException d){
            throw new DatabaseException("Falha na integridade referencial");
        }
        productRepository.deleteById(id);
    }
}


