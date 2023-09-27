package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.History;

public interface HistoryRepository extends CrudRepository<History, Integer> {

	@Query("select * from history where costomer_id = :costomer_id and history_id = :history_id")
	Optional <History> historyOpt(@Param("costomer_id")Integer costomerId, @Param("history_id")Integer historyId);
	
	@Query("select max(history_id) from history where costomer_id = :costomer_id")
	Integer maxHistoryId(@Param("costomer_id")Integer costomerId);
}
