package com.example.springboot.repository;

import com.example.springboot.model.Role;
import com.example.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findAllByRole(Role role);

    @Query("SELECT u.username, h.createAt, h.price, u.isActive FROM User u JOIN House h WHERE u.role = 'HOST'")
    List<Object[]> findHosts();
}