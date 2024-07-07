package com.proyect.apirestproyect.service;

import com.proyect.apirestproyect.model.dto.ClienteDto;
import com.proyect.apirestproyect.model.entity.Cliente;

public interface IClienteService {
    Cliente save(ClienteDto cliente);
    Cliente findByID(Integer id);
    void delete(Cliente cliente);
}
