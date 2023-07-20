package com.example.springboot.controller.houseController;

import com.example.springboot.model.House;
import com.example.springboot.model.User;
import com.example.springboot.service.UserService;
import com.example.springboot.service.house.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/house")
@CrossOrigin("*")
public class HouseController {
    @Autowired
    public IHouseService houseService;
    @Autowired
    public UserService userService;

    @GetMapping("find/{id}")
    public ResponseEntity<Iterable<House>> listHouseByUser(@PathVariable long id){
        Optional<User> optionalUser = userService.findById(id);
        if (!optionalUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(houseService.findByUser(optionalUser.get()),HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<Iterable<House>> listHouse() {
        return new ResponseEntity<>(houseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<House> findById(@PathVariable Long id) {
        Optional<House> optionalHouse = houseService.findById(id);
        if (!optionalHouse.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalHouse.get(), HttpStatus.OK);
    }

    @PostMapping("/api")
    public ResponseEntity<House> createHouse(@RequestBody House house) {
        return new ResponseEntity<>(houseService.save(house), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        houseService.remove(id);
    }

    @PutMapping("/api/{id}")
    public ResponseEntity<House> update(@PathVariable Long id, @RequestBody House house) {
        Optional<House> optionalHouse = houseService.findById(id);
        if (!optionalHouse.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        house.setId(id);
        return new ResponseEntity<>(houseService.save(house), HttpStatus.OK);
    }
}
