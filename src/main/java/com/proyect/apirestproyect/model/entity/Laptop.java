package com.proyect.apirestproyect.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "laptops")
public class Laptop {
    @Id
    @Column(name = "id_laptop")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLaptop;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private float price;

    @Column(name = "description")
    private String description;

    @Column(name = "brand")
    private String brand;

}
