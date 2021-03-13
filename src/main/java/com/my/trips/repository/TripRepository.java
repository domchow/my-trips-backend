package com.my.trips.repository;

import com.my.trips.model.Trip;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface TripRepository extends CrudRepository<Trip, Long> {

    @Modifying
    @Transactional
    @Query("update Trip t set t.City = ?1, t.date = ?2, t.description = ?3 where t.id = ?4")
    void setTripInfoById(String city, LocalDate date, String description, Long userId);
}
