package com.my.trips.repository;

import com.my.trips.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailsRepository extends CrudRepository<User, Long> {
    List<User> findByNameEquals(String name);
}
