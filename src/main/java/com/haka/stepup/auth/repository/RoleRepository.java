package com.haka.stepup.auth.repository;

import com.haka.stepup.db.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
