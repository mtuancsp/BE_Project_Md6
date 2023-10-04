package com.example.springboot.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDateRange {
    @JsonProperty("start")
    private Date startDate;

    @JsonProperty("end")
    private Date endDate;

    public BookingDateRange() {
    }

    public BookingDateRange(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and setters
    // ...
}