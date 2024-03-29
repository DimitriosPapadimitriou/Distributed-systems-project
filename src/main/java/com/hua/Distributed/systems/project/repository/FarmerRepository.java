package com.hua.Distributed.systems.project.repository;

import com.hua.Distributed.systems.project.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Integer> {
}
