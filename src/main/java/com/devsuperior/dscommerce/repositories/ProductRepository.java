package com.devsuperior.dscommerce.repositories;

import com.devsuperior.dscommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * JpaRepository espera receber o tipo da entidade (nesse caso, Product)
 * e o tipo da chave primária dessa entidade (no caso o id, que é do tipo Long)
*/
public interface ProductRepository extends JpaRepository<Product, Long> {}
