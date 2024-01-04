package com.hua.Distributed.systems.project.service;

import com.hua.Distributed.systems.project.entity.Inspector;
import com.hua.Distributed.systems.project.repository.InspectorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InspectorService {

    @Autowired
    private InspectorRepository inspectorRepository;

    @Transactional
    public List<Inspector> getInspectors(){ return inspectorRepository.findAll(); }
}
