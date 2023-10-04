package com.example.springboot.repository;

import com.example.springboot.model.Role;
import com.example.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    List<User> findAllByRole(Role role);

    List<User> findByApplyHost(Boolean applyHost);
    @Query("SELECT u FROM User u WHERE u.role = :role")
    List<User> findHostUsers(@Param("role") Role role);

    User findByIdAndRole(Long id,Role role);
}