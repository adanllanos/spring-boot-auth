package com.proyect.apirestproyect.model.dao;

import com.proyect.apirestproyect.model.entity.Laptop;
import org.springframework.data.repository.CrudRepository;

public interface LaptopDAO extends CrudRepository<Laptop, Integer> {
}
