package com.hua.Distributed.systems.project.repository;

import com.hua.Distributed.systems.project.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Integer> {

    Optional<Roles> findByName(String roleName);
}
