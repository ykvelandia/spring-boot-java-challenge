package com.bookings.controller;

import ch.qos.logback.core.boolex.EvaluationException;
import com.bookings.model.Bookings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bookings.service.BookingsService;

import java.awt.print.Book;
import java.util.Optional;

@RestController
@RequestMapping("v1/bookings")

public class BookingsController {

    @Autowired
    private BookingsService bookingsService;

    @PostMapping
    public ResponseEntity<Bookings> createBookings (@RequestBody Bookings bookings){
        Bookings newBookings = bookingsService.createBookings(bookings);
        return new ResponseEntity<>(newBookings, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Bookings> updateBookings (@PathVariable String id,@RequestBody Bookings bookings){
        Optional<Bookings> existingBookings = bookingsService.findBookingsById(id);
        if(existingBookings.isPresent()){
            Bookings updateBooking = bookingsService.updateBookings(id,bookings);
            return new ResponseEntity<>(updateBooking,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bookings> findBookingsById(@PathVariable String id){
        Optional<Bookings> bookings = bookingsService.findBookingsById(id);
        return bookings.map(value -> new ResponseEntity<>(value,HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookings (@PathVariable String id){
        bookingsService.deleteBookings(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
