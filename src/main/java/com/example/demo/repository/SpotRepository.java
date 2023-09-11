package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Spot;

public interface SpotRepository extends CrudRepository<Spot, Integer> {

}
