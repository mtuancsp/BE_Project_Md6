package com.example.springboot.repository;
import com.example.springboot.model.House;
import com.example.springboot.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
    Iterable<Image> findByHouse(House house);
    void deleteAllByHouse(House house);
}