package com.example.springboot.repository;

import com.example.springboot.model.House;
import com.example.springboot.model.Review;
import com.example.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByHouseId(Long id);


}
