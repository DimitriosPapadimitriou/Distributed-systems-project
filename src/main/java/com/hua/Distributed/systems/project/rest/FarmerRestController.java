package com.hua.Distributed.systems.project.rest;

import com.hua.Distributed.systems.project.entity.Farmer;
import com.hua.Distributed.systems.project.repository.FarmerRepository;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/farmer")
@Hidden
public class FarmerRestController {

    @Autowired
    private FarmerRepository farmerRepository;

    @GetMapping("")
    public List<Farmer> getFarmers(){
        return farmerRepository.findAll();
    }
}
