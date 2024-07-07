package com.proyect.apirestproyect.controller;

import com.proyect.apirestproyect.model.dto.ProductDto;
import com.proyect.apirestproyect.model.entity.Product;
import com.proyect.apirestproyect.model.payload.MensajeResponse;
import com.proyect.apirestproyect.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private IProductService productService;

    @PostMapping("product")
    public ResponseEntity<?> create(@RequestBody ProductDto productDto){
        Product productSave = null;
        try {
            productSave = productService.save(productDto);
            productDto.builder()
                    .idProduct(productSave.getIdProduct())
                    .productName(productSave.getProductName())
                    .price(productSave.getPrice())
                    .description(productSave.getDescription())
                    .build();
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Creaded successfully")
                            .object(productSave)
                            .build()
                    , HttpStatus.CREATED);

        }catch (DataAccessException exDT){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDT.getMessage())
                            .object(null)
                            .build()
            , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    @PutMapping("product/{id}")
    public ResponseEntity<?> update(@RequestBody ProductDto productDto, @PathVariable Integer id){
        Product productUpdate = null;
        try{
            Product findProduct = productService.findByID(id);
            if (findProduct != null){
                productUpdate = productService.save(productDto);
                productDto.builder()
                        .idProduct(productDto.getIdProduct())
                        .productName(productDto.getProductName())
                        .price(productDto.getPrice())
                        .description(productDto.getDescription())
                        .build();
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("Update successfully")
                                .object(productUpdate)
                                .build()
                        , HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("Product Not found")
                                .object(null)
                                .build()
                        , HttpStatus.NOT_FOUND);
            }

        }catch (DataAccessException exDT){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDT.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    @DeleteMapping("product/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try {
            Product productDelete = productService.findByID(id);
            productService.delete(productDelete);
            return new ResponseEntity<>(productDelete, HttpStatus.NO_CONTENT);
        }catch (DataAccessException exDT){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDT.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("product/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id){
        Product product = productService.findByID(id);
        if (product == null){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("the product not found")
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND);
        }
        ProductDto.builder()
                .idProduct(product.getIdProduct())
                .productName(product.getProductName())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje(null)
                        .object(product)
                        .build()
                , HttpStatus.NOT_FOUND);
    }
}
