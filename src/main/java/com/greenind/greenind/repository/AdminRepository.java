package com.greenind.greenind.repository;

import com.greenind.greenind.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    // Custom finder method
    Admin findByUsername(String username);
}
