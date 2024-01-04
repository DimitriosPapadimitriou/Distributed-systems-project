package com.hua.Distributed.systems.project.controller;

import com.hua.Distributed.systems.project.service.InspectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("inspector")
public class InspectorController {

    @Autowired
    private InspectorService inspectorService;

    @GetMapping("")
    public String showInspectors(Model model){
        model.addAttribute("inspectors", inspectorService.getInspectors());
        return "inspectors";
    }
}
