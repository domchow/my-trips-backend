package com.my.trips.service;

import com.my.trips.model.Trip;

import java.util.List;
import java.util.Optional;

public interface ITripService {

    List<Trip> getTrips();

    Optional<Trip> getTrips(long id);

    Trip save(Trip trip);

    void deleteById(long id);

    void updateById(long id, Trip trip);
}
