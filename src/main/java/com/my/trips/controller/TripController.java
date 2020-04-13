package com.my.trips.controller;

import com.my.trips.model.Trip;
import com.my.trips.service.ITripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class TripController {

    @Autowired
    private ITripService tripService;

    @GetMapping("/trips")
    public List<Trip> getTrips() {
        var cities = (List<Trip>) tripService.findAll();
        return cities;
    }
}
