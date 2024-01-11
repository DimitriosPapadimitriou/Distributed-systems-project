package com.hua.Distributed.systems.project.repository;

import com.hua.Distributed.systems.project.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String roleName);
}
