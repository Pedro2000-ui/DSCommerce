package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String teste()
    {

        /*
         * Implementada a partir do java 8 "Optional" serve como um contêiner que pode ou não conter um valor não nulo,
         * tendo o principal objetivo de evitar o erro NullPointerException e sinalizar de forma clara e explícita quando
         * o resultado de um metodo pode estar ausente.
        */
        Optional<Product> result = productRepository.findById(1L);
        Product product = result.get();

        return product.getName();

    }

}
