package com.proyect.apirestproyect.service.impl;

import com.proyect.apirestproyect.model.dao.LaptopDAO;
import com.proyect.apirestproyect.model.dto.LaptopDto;
import com.proyect.apirestproyect.model.entity.Laptop;
import com.proyect.apirestproyect.service.ILaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LaptopImplService implements ILaptopService {
    @Autowired
    private LaptopDAO laptopDAO;

    @Transactional
    @Override
    public Laptop save(LaptopDto laptopDto){
        Laptop laptop = Laptop.builder()
                .idLaptop(laptopDto.getIdLaptop())
                .name(laptopDto.getName())
                .price(laptopDto.getPrice())
                .description(laptopDto.getDescription())
                .brand(laptopDto.getBrand())
                .build();
        return laptopDAO.save(laptop);
    }

    @Transactional(readOnly = true)
    @Override
    public Laptop findByID(Integer id){
        return laptopDAO.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Laptop laptop){
        laptopDAO.delete(laptop);
    }
}
