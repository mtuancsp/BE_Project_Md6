package com.example.springboot.service.img;

import com.example.springboot.model.House;
import com.example.springboot.model.Image;
import com.example.springboot.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ImageService implements IImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Override
    public Iterable<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Optional<Image> findById(Long id) {
        return imageRepository.findById(id);
    }

    @Override
    public Image save(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public void remove(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public Iterable<Image> findByHouse(House house) {
        return imageRepository.findByHouse(house);
    }

    @Override
    @Transactional
    public void deleteAllByHouse(House house) {
        imageRepository.deleteAllByHouse(house);
    }
}
