package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.History;

public interface HistoryRepository extends CrudRepository<History, Integer> {

}
