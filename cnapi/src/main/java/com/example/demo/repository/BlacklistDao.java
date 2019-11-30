package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.model.Blacklist;
import com.example.demo.model.Dish;

@Repository
@CrossOrigin(origins = { "http://localhost:8100", "http://localhost:8080" })
public interface BlacklistDao extends MongoRepository<Blacklist, String> { // Dish DAO

}
