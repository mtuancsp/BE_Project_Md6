package com.example.springboot.controller.bookingController;

import com.example.springboot.exception.NotFoundException;
import com.example.springboot.exception.UnauthorizedException;
import com.example.springboot.model.Booking;
import com.example.springboot.service.bookingService.IBookingService;
import com.example.springboot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        boolean isSuccessfullyCreated = bookingService.createBooking(booking);
        System.out.println(booking);
        if (isSuccessfullyCreated) return new ResponseEntity<>(HttpStatus.OK);
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
}
