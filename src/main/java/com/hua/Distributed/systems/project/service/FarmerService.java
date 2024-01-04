package com.hua.Distributed.systems.project.service;

import com.hua.Distributed.systems.project.entity.Farmer;
import com.hua.Distributed.systems.project.repository.FarmerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmerService {

    @Autowired
    private FarmerRepository farmerRepository;

    @Transactional
    public List<Farmer> getFarmers(){
        return farmerRepository.findAll();
    }
}
