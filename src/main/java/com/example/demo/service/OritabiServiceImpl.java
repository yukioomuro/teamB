package com.example.demo.service;

import java.util.Optional;

import javax.xml.crypto.KeySelector.Purpose;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Costomer;
import com.example.demo.entity.History;
import com.example.demo.entity.Spot;
import com.example.demo.repository.CostomerRepository;
import com.example.demo.repository.HistoryRepository;
import com.example.demo.repository.PurposeRepository;
import com.example.demo.repository.SpotRepository;

@Service
@Transactional
public class OritabiServiceImpl implements OritabiService {

	//Repository：注入
	@Autowired
	SpotRepository spotRep;
	@Autowired
	PurposeRepository purposeRep;
	@Autowired
	CostomerRepository costomerRep;
	@Autowired
	HistoryRepository historyRep;

	@Override
	public Iterable<Spot> selectAllSpot() {
		return spotRep.findAll();
	}

	@Override
	public Iterable<Purpose> selectAllPurpose() {
		return purposeRep.findAll();
	}

	@Override
	public Iterable<Costomer> selectAllCostomer() {
		return costomerRep.findAll();
	}

	@Override
	public Iterable<History> selectAllHistory() {
		return historyRep.findAll();
	}

	@Override
	public Optional<Spot> selectOneByIdSpot(Integer spotId) {
		return spotRep.findById(spotId);
	}

	@Override
	public Optional<Purpose> selectOneByIdPurpose(Integer purposeId) {
		return purposeRep.findById(purposeId);
	}

	@Override
	public Optional<Costomer> selectOneByIdCostomer(Integer id) {
		return costomerRep.findById(id);
	}

	@Override
	public Optional<History> selectOneByIdHistory(Integer historyId) {
		return historyRep.findById(historyId);
	}

	@Override
	public void insertSpot(Spot spot) {
		spotRep.save(spot);
	}

	@Override
	public void insertPurpose(Purpose purpose) {
		purposeRep.save(purpose);
	}

	@Override
	public void insertCostomer(Costomer costomer) {
		costomerRep.save(costomer);
	}

	@Override
	public void insertHistory(History history) {
		historyRep.save(history);
	}

	@Override
	public void updateSpot(Spot spot) {
		spotRep.save(spot);
	}

	@Override
	public void updatePurpose(Purpose purpose) {
		purposeRep.save(purpose);
	}

	@Override
	public void updateCostomer(Costomer costomer) {
		costomerRep.save(costomer);
	}

	@Override
	public void updateHistory(History history) {
		historyRep.save(history);
	}

	@Override
	public void deleteSpotById(Integer id) {
		spotRep.deleteById(id);
	}

	@Override
	public void deletePurposeById(Integer purposeId) {
		purposeRep.deleteById(purposeId);
	}

	@Override
	public void deleteCostomerById(Integer id) {
		costomerRep.deleteById(id);
	}

	@Override
	public void deleteHistoryById(Integer historyId) {
		historyRep.deleteById(historyId);
	}

}
