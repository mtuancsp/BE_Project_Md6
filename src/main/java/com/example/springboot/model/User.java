package com.example.springboot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @JsonIgnore
    private String password;

    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String phoneNumber;

    private String address;

    private String profileImage;

    @JsonFormat(pattern = "dd/MM/yyyy", locale = "vi-VN", timezone = "Asia/Ho_Chi_Minh")
    private Date createAt;

    private boolean applyHost;

    private boolean isBlocked;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Formula("(SELECT COUNT(*) FROM houses h WHERE h.user_id = id)")
    private Integer numberOfHouse;

    @Formula("(SELECT SUM(b.total) FROM houses h JOIN bookings b ON h.id = b.house_id WHERE h.user_id = id AND b.booking_status = 'CHECKED_OUT')")
    private Double earnedMoney;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @PrePersist
    public void setCreateAt() {
            this.createAt = new Date(new java.util.Date().getTime());
    }

}