package com.example.springboot.repository;

import com.example.springboot.model.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);

    List<Booking> findByHouse(House house);

    @Query("SELECT new com.example.springboot.model.BookingDateRange(b.startDate, b.endDate) FROM Booking b WHERE b.house.id = :houseId AND b.bookingStatus != 'CANCELLED' AND b.endDate >= CURRENT_DATE")
    List<BookingDateRange> findBookingDateRangesByHouseId(@Param("houseId") Long houseId);
}
