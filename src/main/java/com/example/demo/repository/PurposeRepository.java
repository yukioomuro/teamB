package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Purpose;

public interface PurposeRepository extends CrudRepository<Purpose, Integer> {

}
