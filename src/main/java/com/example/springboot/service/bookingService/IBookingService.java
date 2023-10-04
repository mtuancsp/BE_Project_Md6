package com.example.springboot.service.bookingService;

import com.example.springboot.model.Booking;
import com.example.springboot.model.BookingDateRange;
import com.example.springboot.model.User;
import com.example.springboot.service.IGeneralService;

import java.util.List;

public interface IBookingService extends IGeneralService<Booking> {

    Booking createBooking(Booking booking);

    List<Booking> getBookingsByUser(User user);
    void cancelBookingForUser(Long bookingId);
    void checkInBooking(Long id);
    void checkOutBooking(Long id);

    List<Booking> findAllByOwner(User user);

    List<BookingDateRange> getBookingsByHouseId(Long houseId);
}
