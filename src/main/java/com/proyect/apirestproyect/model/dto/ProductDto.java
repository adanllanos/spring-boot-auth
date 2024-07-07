package com.proyect.apirestproyect.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class ProductDto implements Serializable {
    private Integer idProduct;
    private String productName;
    private float price;
    private String description;
}
