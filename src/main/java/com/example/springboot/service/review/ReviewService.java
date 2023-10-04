package com.example.springboot.service.review;

import com.example.springboot.exception.NotFoundException;
import com.example.springboot.exception.UnauthorizedException;
import com.example.springboot.model.Booking;
import com.example.springboot.model.House;
import com.example.springboot.model.Review;
import com.example.springboot.model.ReviewStatus;
import com.example.springboot.model.User;
import com.example.springboot.repository.BookingRepository;
import com.example.springboot.repository.HouseRepository;
import com.example.springboot.repository.ReviewRepository;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService implements IReviewService{
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public void createReviewFromBooking(Long id, Integer rating, String content) {
        // Lấy đối tượng Booking từ cơ sở dữ liệu
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isEmpty()) {
            throw new NotFoundException("Booking not found with id " + id);
        }
        Booking booking = optionalBooking.get();

        // Kiểm tra xem người dùng viết đánh giá có phải là người dùng đã đặt phòng không
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> currentUserOptional = userRepository.findByUsername(username);

        if (currentUserOptional.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        User currentUser =  currentUserOptional.get();
        if (!booking.getUser().equals(currentUser)) {
            throw new UnauthorizedException("You are not allowed to write a review for this booking");
        }

        // Tạo một đối tượng Review từ đối tượng Booking và thông tin đánh giá được cung cấp
        Review review = Review.builder()
                .user(currentUser)
                .rating(rating)
                .content(content)
                .house(booking.getHouse())
                .build();

        // Lưu đối tượng Review vào cơ sở dữ liệu
        reviewRepository.save(review);

        // Liên kết đối tượng Review với đối tượng Booking
        booking.setReview(review);
        bookingRepository.save(booking);
    }


    @Override
    public Iterable<Review> findAll() {
        return null;
    }

    @Override
    public Optional<Review> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Review save(Review review) {
        return null;
    }

    @Override
    public void remove(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public void hideReview(Long id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (reviewOptional.isEmpty()) {
            throw new NotFoundException("Review not found");
        }
        var review = reviewOptional.get();
        var user = userService.getCurrentUser();
        if (!review.getHouse().getUser().equals(user)) {
            throw new UnauthorizedException("You are not allowed to hide this review");
        }
        review.setReviewStatus(ReviewStatus.REJECTED);
        reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewByHouseId(Long id) {
        return reviewRepository.findByHouseId(id);
    }
}