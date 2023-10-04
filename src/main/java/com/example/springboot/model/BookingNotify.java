package com.example.springboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookingnotify")
public class BookingNotify {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private Long fromId;

    private boolean isRead;
    @ManyToOne
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "source_id", referencedColumnName = "id")
    private User sourceUser;

    @ManyToOne
    @JoinColumn(name = "target_id", referencedColumnName = "id")
    private User targetUser;

}
