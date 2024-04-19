package com.bookings.service;

import com.bookings.model.Bookings;
import com.bookings.repository.BookingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingsService {

    @Autowired
    private BookingsRepository bookingsRepository;

    public Bookings createBookings(Bookings bookings){
        return bookingsRepository.save(bookings);
    }

    public Bookings updateBookings(String id,Bookings bookings){
        bookings.setId(id);
        return bookingsRepository.save(bookings);
    }
    public void deleteBookings(String id){
        bookingsRepository.deleteById(id);
    }
    public Optional <Bookings> findBookingsById(String id){
        return bookingsRepository.findById(id);
    }

}
