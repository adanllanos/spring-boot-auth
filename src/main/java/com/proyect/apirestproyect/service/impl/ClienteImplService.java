package com.proyect.apirestproyect.service.impl;

import com.proyect.apirestproyect.model.dao.ClienteDAO;
import com.proyect.apirestproyect.model.dto.ClienteDto;
import com.proyect.apirestproyect.model.entity.Cliente;
import com.proyect.apirestproyect.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteImplService implements IClienteService {

    @Autowired
    private ClienteDAO clienteDAO;

    @Transactional
    @Override
    public Cliente save(ClienteDto clienteDto) {

        Cliente cliente = Cliente.builder()
                .idCliente(clienteDto.getIdCliente())
                .nombre(clienteDto.getNombre())
                .apellido(clienteDto.getApellido())
                .correo(clienteDto.getCorreo())
                .fechaRegistro(clienteDto.getFechaRegistro())

                .build();
        return clienteDAO.save(cliente);
    }
    @Transactional(readOnly = true)
    @Override
    public Cliente findByID(Integer id) {
        return clienteDAO.findById(id).orElse(null);
    }
    @Transactional
    @Override
    public void delete(Cliente cliente) {

        clienteDAO.delete(cliente);
    }
}
