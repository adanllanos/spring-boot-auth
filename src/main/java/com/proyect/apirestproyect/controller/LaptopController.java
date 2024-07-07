package com.proyect.apirestproyect.controller;

import com.proyect.apirestproyect.model.dto.LaptopDto;
import com.proyect.apirestproyect.model.entity.Laptop;
import com.proyect.apirestproyect.model.payload.MensajeResponse;
import com.proyect.apirestproyect.service.ILaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class LaptopController {

    @Autowired
    private ILaptopService laptopService;

    @PostMapping("laptop")
    public ResponseEntity<?> create(@RequestBody LaptopDto laptopDto) {
        Laptop laptopSave = null;
        try {
            laptopSave = laptopService.save(laptopDto);
            laptopDto.builder()
                    .idLaptop(laptopSave.getIdLaptop())
                    .brand(laptopSave.getBrand())
                    .name(laptopSave.getName())
                    .price(laptopSave.getPrice())
                    .description(laptopSave.getDescription())
                    .build();
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Creaded successfully")
                            .object(laptopSave)
                            .build()
                    , HttpStatus.CREATED);
        } catch (DataAccessException exDT) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDT.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
        @PutMapping("laptop/{id}")
        public ResponseEntity<?> update(@RequestBody LaptopDto laptopDto, @PathVariable Integer id){
            Laptop laptopUdate = null;
            try {
                Laptop findLaptop = laptopService.findByID(id);
                if(findLaptop != null) {
                    laptopUdate = laptopService.save(laptopDto);
                    laptopDto.builder()
                            .idLaptop(laptopDto.getIdLaptop())
                            .brand(laptopDto.getBrand())
                            .name(laptopDto.getName())
                            .price(laptopDto.getPrice())
                            .description(laptopDto.getDescription())
                            .build();
                    return new ResponseEntity<>(
                            MensajeResponse.builder()
                                    .mensaje("Update successfully")
                                    .object(laptopUdate)
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

    @DeleteMapping("laptop/{id}")
    public  ResponseEntity<?> delete(@PathVariable Integer id){
        try {
            Laptop laptopDelete = laptopService.findByID(id);
            laptopService.delete(laptopDelete);
            return new ResponseEntity<>(laptopDelete,HttpStatus.NO_CONTENT);
        }catch (DataAccessException exDT){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDT.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("latop/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id){
        Laptop laptop = laptopService.findByID(id);
        if(laptop == null){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("the laptop not found")
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND);
        }
        LaptopDto.builder()
                .idLaptop(laptop.getIdLaptop())
                .brand(laptop.getBrand())
                .name(laptop.getName())
                .price(laptop.getPrice())
                .description(laptop.getDescription())
                .build();
        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("Update successfully")
                        .object(laptop)
                        .build()
                , HttpStatus.CREATED);

    }
}
