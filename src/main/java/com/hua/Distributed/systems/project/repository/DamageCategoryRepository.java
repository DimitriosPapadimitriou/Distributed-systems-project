package com.hua.Distributed.systems.project.repository;

import com.hua.Distributed.systems.project.entity.DamageCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DamageCategoryRepository extends JpaRepository<DamageCategory, Integer> {
}
