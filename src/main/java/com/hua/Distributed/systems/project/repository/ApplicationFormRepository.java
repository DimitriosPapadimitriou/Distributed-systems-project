package com.hua.Distributed.systems.project.repository;

import com.hua.Distributed.systems.project.entity.ApplicationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationFormRepository extends JpaRepository<ApplicationForm, Integer> {
}
