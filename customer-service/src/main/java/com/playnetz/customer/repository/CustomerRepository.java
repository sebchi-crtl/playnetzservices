package com.playnetz.customer.repository;

import com.playnetz.customer.model.RoleUser;
import com.playnetz.customer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    void updateRoleUser(String username, RoleUser newRole);
}
