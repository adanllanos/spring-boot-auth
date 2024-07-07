package com.proyect.apirestproyect.controller;

import com.proyect.apirestproyect.model.dto.ClienteDto;
import com.proyect.apirestproyect.model.entity.Cliente;
import com.proyect.apirestproyect.model.payload.MensajeResponse;
import com.proyect.apirestproyect.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @PostMapping("cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody ClienteDto clienteDto){
        Cliente clienteSave =null;
        try {
            clienteSave = clienteService.save(clienteDto);
            ClienteDto.builder()

                    .idCliente(clienteSave.getIdCliente())
                    .nombre(clienteSave.getNombre())
                    .apellido(clienteSave.getApellido())
                    .correo(clienteSave.getCorreo())
                    .fechaRegistro(clienteSave.getFechaRegistro())

                    .build();
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Creaded successfully")
                            .object(clienteSave)
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

    @PutMapping("cliente/{id}")
    public ResponseEntity<?> update(@RequestBody ClienteDto clienteDto, @PathVariable Integer id){
        Cliente clienteUpdate = null;
        try {
            Cliente findCliente = clienteService.findByID(id);
            if (findCliente != null){
                clienteUpdate = clienteService.save(clienteDto);
                ClienteDto.builder()
                        .idCliente(clienteUpdate.getIdCliente())
                        .nombre(clienteUpdate.getNombre())
                        .apellido(clienteUpdate.getApellido())
                        .correo(clienteUpdate.getCorreo())
                        .fechaRegistro(clienteUpdate.getFechaRegistro())

                        .build();
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("Updated successfully")
                                .object(clienteUpdate)
                                .build()
                        , HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("User not found")
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

    @DeleteMapping("cliente/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try {
            Cliente clienteDelete = clienteService.findByID(id);
            clienteService.delete(clienteDelete);
            return new ResponseEntity<>(clienteDelete, HttpStatus.NO_CONTENT);
        }catch (DataAccessException exDT){

            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDT.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);

        }
    }

    @GetMapping("cliente/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id){
        Cliente cliente = clienteService.findByID(id);

        if(cliente == null){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("the user not found")
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND);
        }
        ClienteDto.builder()
                .idCliente(cliente.getIdCliente())
                .nombre(cliente.getNombre())
                .apellido(cliente.getApellido())
                .correo(cliente.getCorreo())
                .fechaRegistro(cliente.getFechaRegistro())

                .build();
        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje(null)
                        .object(cliente)
                        .build()
                , HttpStatus.OK);
    }
}
