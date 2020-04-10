package com.my.trips.service;
import com.my.trips.model.Trip;
import com.my.trips.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService implements ITripService {

    @Autowired
    private TripRepository repository;

    @Override
    public List<Trip> findAll() {
        return (List<Trip>) repository.findAll();
    }
}
