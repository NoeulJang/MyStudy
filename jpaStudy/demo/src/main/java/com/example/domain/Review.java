package com.example.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Review {

    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    @Column(name = "hotel_id")
    private String hotelId;

    public String getHotelId() {
        return hotelId;
    }
    
    public Review(String hotelId) {
        this.hotelId = hotelId;
    }

    public Review() {
    }

    
    @Override
    public String toString() {
        return "Review [id=" + id + ", hotelId=" + hotelId + "]";
    }
}
