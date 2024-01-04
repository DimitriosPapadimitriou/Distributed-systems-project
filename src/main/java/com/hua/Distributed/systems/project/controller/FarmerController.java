package com.hua.Distributed.systems.project.controller;

import com.hua.Distributed.systems.project.entity.Farmer;
import com.hua.Distributed.systems.project.repository.FarmerRepository;
import com.hua.Distributed.systems.project.service.FarmerService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("farmer")
public class FarmerController {

    @Autowired
    private FarmerService farmerService;

    @GetMapping("")
    public String showFarmers(Model model){
        model.addAttribute("farmers", farmerService.getFarmers());

        return "farmers";
    }
}
