package com.proyect.apirestproyect.model.dao;

import com.proyect.apirestproyect.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteDAO extends CrudRepository<Cliente, Integer> {

}
