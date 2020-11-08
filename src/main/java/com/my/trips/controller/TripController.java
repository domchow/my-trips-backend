package com.my.trips.controller;

import com.my.trips.model.Trip;
import com.my.trips.service.ITripService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.lang.String.format;

@RestController
@CrossOrigin
@Slf4j
public class TripController {

    @Autowired
    private ITripService tripService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/trips")
    public List<Trip> getTrips() {
        return tripService.getTrips();
    }

    @GetMapping("/trips/{id}")
    public Trip getTrips(@PathVariable Long id) {
        log.info("Get Trip by id: {}", id);
        return tripService
                .getTrips(id)
                .orElseThrow(() -> new EntityNotFoundException(format("Trip id %s does not exist", id)));
    }

    @PostMapping("/trips")
    public Trip createTrip(@RequestBody Trip trip) {
        log.info("Save new Trip: {}", trip.toString());
        kafkaTemplate.send("trip_topic", trip.toString());
        return tripService.save(trip);
    }

    @DeleteMapping("/trips/{id}")
    void deleteTip(@PathVariable Long id) {
        log.info("Delete Trip id: {}", id);
        tripService.deleteById(id);
    }

    @PutMapping("/trips/{id}")
    void updateTrip(@PathVariable Long id, @RequestBody Trip trip) {
        log.info("Update Trip id: {}, body: {}", id, trip.toString());
        tripService.updateById(id, trip);
    }
}
