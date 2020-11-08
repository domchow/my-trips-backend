package com.my.trips.service;

import com.my.trips.model.Trip;
import com.my.trips.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService implements ITripService {

    @Autowired
    private TripRepository repository;

    @Override
    public List<Trip> getTrips() {
        return (List<Trip>) repository.findAll();
    }

    @Override
    public Optional<Trip> getTrips(long id) {
        return repository.findById(id);
    }

    @Override
    public Trip save(Trip trip) {
        return repository.save(trip);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
