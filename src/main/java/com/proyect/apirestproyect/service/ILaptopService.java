package com.proyect.apirestproyect.service;

import com.proyect.apirestproyect.model.dto.LaptopDto;
import com.proyect.apirestproyect.model.entity.Laptop;

public interface ILaptopService {
    Laptop save(LaptopDto laptop);
    Laptop findByID(Integer id);
    void delete(Laptop laptop);
}
