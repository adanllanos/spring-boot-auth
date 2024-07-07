package com.proyect.apirestproyect.model.dao;

import com.proyect.apirestproyect.model.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDAO extends CrudRepository<Product, Integer> {
}
