package com.example.springboot.service.img;

import com.example.springboot.model.House;
import com.example.springboot.model.Image;
import com.example.springboot.service.IGeneralService;

public interface IImageService extends IGeneralService<Image> {
    Iterable<Image> findByHouse(House house);

    void deleteAllByHouse(House house);
}
