package com.example.springboot.repository;

import com.example.springboot.model.Booking;
import com.example.springboot.model.BookingNotify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingNotifyRepository extends JpaRepository<BookingNotify, Long> {
    List<BookingNotify> findByBooking(Booking booking);

    List<BookingNotify> findByTargetUserId(Long id);

    @Query("SELECT bn FROM BookingNotify bn WHERE bn.isRead = false ")
    List<BookingNotify> findAllNotiUnread();
}
