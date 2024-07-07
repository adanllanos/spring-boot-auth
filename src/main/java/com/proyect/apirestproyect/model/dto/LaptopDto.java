package com.proyect.apirestproyect.model.dto;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class LaptopDto implements Serializable {
    private Integer idLaptop;
    private String name;
    private float price;
    private String description;
    private String brand;

}
