package com.example.springboot.service.bookingService;

import com.example.springboot.exception.NotFoundException;
import com.example.springboot.exception.UnauthorizedException;
import com.example.springboot.model.*;
import com.example.springboot.repository.BookingRepository;
import com.example.springboot.repository.HouseRepository;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class BookService implements IBookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public Iterable<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Optional<Booking> findById(Long id) {
        return bookingRepository.findById(id);
    }

    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public void remove(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public Booking createBooking(Booking booking) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> currentUserOptional = userRepository.findByUsername(username);

        List<Booking> bookingListOfHouse = findAllByHouse(booking.getHouse());

        Date inputBookingStartDate = booking.getStartDate();
        Date inputbookingEndDate = booking.getEndDate();

        for (var bookingElement : bookingListOfHouse) {
            Date bokkingElementStartDate = bookingElement.getStartDate();
            Date bookingElementEndDate = bookingElement.getEndDate();

            if (
                    (inputBookingStartDate.compareTo(bokkingElementStartDate) >= 0 && inputBookingStartDate.compareTo(bookingElementEndDate) <= 0)
                            || (inputbookingEndDate.compareTo(bokkingElementStartDate) >= 0 && inputbookingEndDate.compareTo(bookingElementEndDate) <= 0)
                            || (inputBookingStartDate.compareTo(bokkingElementStartDate) <= 0 && inputbookingEndDate.compareTo(bookingElementEndDate) >= 0)
            ) return null;
        }

        booking.setUser(currentUserOptional.get());
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByUser(User user) {
        return bookingRepository.findByUser(user);
    }

    @Override
    public void cancelBookingForUser(Long bookingId) {
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);

        if (bookingOptional.isEmpty()) {
            throw new NotFoundException("Booking not found");
        }

        Booking booking = bookingOptional.get();
        User user = booking.getUser();
        User currentUser = userService.getCurrentUser();
        if (!currentUser.equals(user)) {
            throw new UnauthorizedException("You are not authorized to cancel this booking");
        }

        Date currentDate = new Date(new java.util.Date().getTime());
        long diffInMillies = Math.abs(booking.getStartDate().getTime() - currentDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        if (diff <= 1) {
            throw new IllegalArgumentException("You can only cancel booking 1 day before the start date");
        }

        booking.setBookingStatus(BookingStatus.CANCELLED);
        booking.setUpdateAt(currentDate);
        bookingRepository.save(booking);
    }

    public List<Booking> findAllByHouse(House house) {
        return bookingRepository.findByHouse(house);
    }

    public List<Booking> findAllByOwner(User user) {
        List<House> houseList = (List<House>) houseRepository.findByUser(user);
        List<Booking> bookingList = new ArrayList<>();

        for (var house : houseList) {
            List<Booking> bookingInHouse = findAllByHouse(house);
            bookingList.addAll(bookingInHouse);
        }

        return bookingList.stream()
                .sorted((b1, b2) -> b2.getId().compareTo(b1.getId()))
                .collect(Collectors.toList());
    }


    @Override
    public void checkInBooking(Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            booking.setBookingStatus(BookingStatus.CHECKED_IN);
            bookingRepository.save(booking);
        } else {
            throw new RuntimeException("Booking not found with id " + id);
        }
    }

    @Override
    public void checkOutBooking(Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            booking.setBookingStatus(BookingStatus.CHECKED_OUT);
            booking.setEndDate(new Date(new java.util.Date().getTime())); // Cập nhật ngày endDate thành thời gian hiện tại
            bookingRepository.save(booking);
        } else {
            throw new RuntimeException("Booking not found with id " + id);
        }
    }

    public List<BookingDateRange> getBookingsByHouseId(Long houseId) {
        return bookingRepository.findBookingDateRangesByHouseId(houseId);
    }
}
