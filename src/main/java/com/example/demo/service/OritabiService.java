package com.example.demo.service;

import java.util.Optional;

import javax.xml.crypto.KeySelector.Purpose;

import com.example.demo.entity.Costomer;
import com.example.demo.entity.History;
import com.example.demo.entity.Spot;

public interface OritabiService {

	//観光スポットリストの全件取得
	Iterable<Spot> selectAllSpot();

	//目的地リストの全件取得
	Iterable<Purpose> selectAllPurpose();

	//顧客リストの全件取得
	Iterable<Costomer> selectAllCostomer();

	//顧客×観光地（履歴）リストの全件取得
	Iterable<History> selectAllHistory();

	//観光スポット情報を、idをキーにして１件取得する
	Optional<Spot> selectOneByIdSpot(Integer spotId);

	//目的地リスト情報を、idをキーにして１件取得する
	Optional<Purpose> selectOneByIdPurpose(Integer purposeId);

	//顧客リスト情報を、idをキーにして１件取得する	
	Optional<Costomer> selectOneByIdCostomer(Integer id);

	// 顧客×観光地(履歴)リスト情報を、idをキーにして１件取得する
	Optional<History> selectOneByIdHistory(Integer historyId);

	//情報をランダムに１件取得する
	//Optional<> selectOneRandom();

	//観光スポットの登録
	void insertSpot(Spot spot);

	//目的地リストの登録
	void insertPurpose(Purpose purpose);

	//顧客リストの登録
	void insertCostomer(Costomer costomer);

	//顧客×観光地(履歴)リストの登録
	void insertHistory(History history);

	//観光スポットの更新
	void updateSpot(Spot spot);

	//目的地リストの更新
	void updatePurpose(Purpose purpose);

	//顧客リストの更新
	void updateCostomer(Costomer costomer);

	//顧客×観光地(履歴)リストぼ更新
	void updateHistory(History history);

	//観光スポットの削除
	void deleteSpotById(Integer id);

	//目的地リストの削除
	void deletePurposeById(Integer purposeId);

	//顧客リストの削除
	void deleteCostomerById(Integer id);

	//顧客×観光地(履歴)リストの削除
	void deleteHistoryById(Integer historyId);
}
