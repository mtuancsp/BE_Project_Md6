package com.example.springboot.controller.reviewController;

import com.example.springboot.model.Review;
import com.example.springboot.model.User;
import com.example.springboot.model.Review;
import com.example.springboot.service.review.ReviewService;
import com.example.springboot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@CrossOrigin("*")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @PostMapping("/bookings/{id}")
    public void createReviewFromBooking(@PathVariable Long id, @RequestBody Review reviewDto) {
        Integer rating = reviewDto.getRating();
        String content = reviewDto.getContent();
        reviewService.createReviewFromBooking(id, rating, content);
    }

    @GetMapping("/house/{id}")
    public ResponseEntity<List<Review>> getReviewByHouseId(@PathVariable Long id){
        List<Review> reviews = reviewService.getReviewByHouseId(id);
        return new ResponseEntity<>(reviews,HttpStatus.OK);
    }



    @PatchMapping("/{id}/hide")
    public ResponseEntity<String> hideReview(@PathVariable Long id) {
        reviewService.hideReview(id);
        return ResponseEntity.ok().build();
    }

}