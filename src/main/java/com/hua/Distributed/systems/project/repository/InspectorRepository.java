package com.hua.Distributed.systems.project.repository;

import com.hua.Distributed.systems.project.entity.Inspector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspectorRepository extends JpaRepository<Inspector, Integer> {
}
