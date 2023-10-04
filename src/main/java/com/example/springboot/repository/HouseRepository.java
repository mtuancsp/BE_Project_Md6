package com.example.springboot.repository;

import com.example.springboot.model.House;
import com.example.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
    Iterable<House> findByUser(User user);

    @Query("SELECT COUNT(h) FROM House h WHERE h.user.id = :userId")
    Long countByUserId(@Param("userId") Long userId);

    @Query("SELECT h FROM House h WHERE " +
            "h.isBlocked = false AND h.user.isBlocked = false " +
            "AND (:totalBedrooms = 0 OR h.totalBedrooms = :totalBedrooms) " +
            "AND (:totalBathrooms = 0 OR h.totalBathrooms = :totalBathrooms) " +
            "AND (REPLACE(h.address, ' ', '') LIKE CONCAT('%', REPLACE(:address, ' ', ''), '%')) " +
            "AND h.price >= :minPrice AND h.price <= :maxPrice " +
            "AND NOT EXISTS (SELECT 1 FROM Booking b WHERE b.house = h AND b.bookingStatus != 'CANCELLED' AND " +
            "(b.endDate < CURRENT_DATE OR " +
            "(:startDate BETWEEN b.startDate AND b.endDate) OR " +
            "(:endDate BETWEEN b.startDate AND b.endDate) OR " +
            "(b.startDate BETWEEN :startDate AND :endDate) OR " +
            "(b.endDate BETWEEN :startDate AND :endDate)))")
    List<House> findBySearchCriteriaAndTimeRange(@Param("totalBedrooms") Integer totalBedrooms,
                                                 @Param("totalBathrooms") Integer totalBathrooms,
                                                 @Param("address") String address,
                                                 @Param("minPrice") Double minPrice,
                                                 @Param("maxPrice") Double maxPrice,
                                                 @Param("startDate") Date startDate,
                                                 @Param("endDate") Date endDate);

    @Modifying
    @Query("UPDATE House h SET h.isBlocked = true WHERE h.id = :id")
    void blockHouse(Long id);

    @Modifying
    @Query("UPDATE House h SET h.isBlocked = false WHERE h.id = :id")
    void UnBlockHouse(Long id);

    @Query("SELECT h FROM House h WHERE h.isBlocked = false AND h.user.isBlocked = false ORDER BY h.id DESC")
    List<House> findAllByOrderByIdDesc();

    @Query("SELECT h FROM House h WHERE h.user = :user AND h.id <> :houseId")
    List<House> findRelatedHousesByUser(User user, Long houseId);

    @Query("SELECT h FROM House h WHERE h.price BETWEEN :minPrice AND :maxPrice AND h.user <> :user AND h.id <> :houseId")
    List<House> findAdditionalHousesByPriceRange(Double minPrice, Double maxPrice, User user, Long houseId);
}
