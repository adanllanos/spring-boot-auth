package com.proyect.apirestproyect.service;

import com.proyect.apirestproyect.model.dto.ProductDto;
import com.proyect.apirestproyect.model.entity.Product;

public interface IProductService {
    Product save(ProductDto product);
    Product findByID(Integer id);
    void delete(Product product);
}
