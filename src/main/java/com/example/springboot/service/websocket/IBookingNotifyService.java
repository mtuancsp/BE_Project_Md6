package com.example.springboot.service.websocket;

import com.example.springboot.model.Booking;
import com.example.springboot.model.BookingNotify;
import com.example.springboot.service.IGeneralService;

import java.util.List;

public interface IBookingNotifyService extends IGeneralService<BookingNotify> {
    BookingNotify createBookingNotify(BookingNotify notify);
    List<BookingNotify> getBookingNotifyByBooking(Booking booking);

    List<BookingNotify> getUnreadNotify();
}
