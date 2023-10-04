package com.example.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private Integer rating;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Date createdAt;
    private Date updatedAt;

    @Enumerated(EnumType.STRING)
    private ReviewStatus reviewStatus;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "house_id", referencedColumnName = "id")
    private House house;

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = new Date(new java.util.Date().getTime());
        this.reviewStatus = ReviewStatus.APPROVED;
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = new Date(new java.util.Date().getTime());
    }
}
