package com.example.springboot.controller.bookingController;

import com.example.springboot.exception.NotFoundException;
import com.example.springboot.exception.UnauthorizedException;
import com.example.springboot.model.Booking;
import com.example.springboot.model.BookingDateRange;
import com.example.springboot.model.House;
import com.example.springboot.model.User;
import com.example.springboot.service.bookingService.IBookingService;
import com.example.springboot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("booking")
@CrossOrigin("*")
public class BookingController {
    @Autowired
    private IBookingService bookingService;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBooking() {
        return new ResponseEntity<>((List<Booking>) bookingService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Booking> create(@RequestBody Booking booking) {
        Booking isSuccessfullyCreated = bookingService.createBooking(booking);
        System.out.println(booking);
        if (isSuccessfullyCreated != null) return new ResponseEntity<>(isSuccessfullyCreated, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("cancel/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long bookingId) {
        try {
            bookingService.cancelBookingForUser(bookingId);
            return ResponseEntity.ok("Booking cancelled successfully");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/owner")
    public ResponseEntity<List<Booking>> findBookingByOwner() {
        User currentUser = userService.getCurrentUser();
        try {
            List<Booking> bookingList = bookingService.findAllByOwner(currentUser);
            return new ResponseEntity<>(bookingList, HttpStatus.OK);
        }catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/maintenance-to-empty/{bookingId}")
    public ResponseEntity<HttpStatus> cancelMaintenance(@PathVariable Long bookingId) {
        try {
            bookingService.remove(bookingId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping("/check-in/{bookingId}")
//    public ResponseEntity<Booking> checkIn (@PathVariable Long bookingId) {
//
//    }

    @PutMapping("/check-in/{id}")
    public void checkInBooking(@PathVariable Long id) {
        bookingService.checkInBooking(id);
    }

    @PutMapping("/check-out/{id}")
    public void checkOutBooking(@PathVariable Long id) {
        bookingService.checkOutBooking(id);
    }

    @GetMapping("/house/{houseId}")
    public List<BookingDateRange> getBookingsByHouseId(@PathVariable Long houseId) {
        return bookingService.getBookingsByHouseId(houseId);
    }
}
