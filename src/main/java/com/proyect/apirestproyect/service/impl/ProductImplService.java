package com.proyect.apirestproyect.service.impl;

import com.proyect.apirestproyect.model.dao.ProductDAO;
import com.proyect.apirestproyect.model.dto.ProductDto;
import com.proyect.apirestproyect.model.entity.Product;
import com.proyect.apirestproyect.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductImplService implements IProductService {

    @Autowired
    private ProductDAO productDAO;

    @Transactional
    @Override
    public Product save(ProductDto productDto) {
        Product product = Product.builder()
                .idProduct(productDto.getIdProduct())
                .productName(productDto.getProductName())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .build();

        return productDAO.save(product);
    }
    @Transactional(readOnly = true)
    @Override
    public Product findByID(Integer id) {
        return productDAO.findById(id).orElse(null);
    }
    @Transactional
    @Override
    public void delete(Product product) {
        productDAO.delete(product);
    }
}
