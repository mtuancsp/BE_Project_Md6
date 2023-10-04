package com.example.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Formula;


import java.sql.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "houses")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private Integer totalBedrooms;

    private Integer totalBathrooms;

    private String address;

    @Min(1)
    @Max(1000000000)
    private Double price;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String featuredImage;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private Date createdAt;

    @Formula("(SELECT ROUND(AVG(r.rating), 1) FROM reviews r WHERE r.house_id = id)")
    private Double ratingScore;

    @Formula("(SELECT COUNT(*) FROM reviews r WHERE r.house_id = id)")
    private Integer numberOfReviews;

    @Formula("(SELECT COUNT(*) FROM bookings b WHERE b.house_id = id AND (b.booking_status = 'CHECKED_OUT'))")
    private Integer numberOfRented;

    @Formula("(SELECT b.booking_status FROM bookings b WHERE b.house_id = id AND CURRENT_DATE BETWEEN b.start_date AND b.end_date LIMIT 1)")
    private String houseStatus;

    @OneToMany(mappedBy = "house")
    private List<Image> images;

    @OneToMany(mappedBy = "house")
    private List<Review> reviews;

    private boolean isBlocked = false;

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = new Date(new java.util.Date().getTime());
        this.featuredImage = "https://a0.muscache.com/im/pictures/miso/Hosting-813137457313942137/original/34eaa638-9027-4e9a-9e91-239db6f2e844.jpeg?im_w=720";
    }

}
