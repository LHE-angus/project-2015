package com.ebiz.mmt.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.KonkaParagonEquipmentJ;

public interface KonkaParagonEquipmentJService {

	Long createKonkaParagonEquipmentJ(KonkaParagonEquipmentJ t);

	Long selectEquipmentNum(KonkaParagonEquipmentJ t);

	int modifyKonkaParagonEquipmentJ(KonkaParagonEquipmentJ t);

	int removeKonkaParagonEquipmentJ(KonkaParagonEquipmentJ t);

	KonkaParagonEquipmentJ getKonkaParagonEquipmentJ(KonkaParagonEquipmentJ t);

	List<KonkaParagonEquipmentJ> getKonkaParagonEquipmentJList(
			KonkaParagonEquipmentJ t);

	Long getKonkaParagonEquipmentJCount(KonkaParagonEquipmentJ t);

	List<KonkaParagonEquipmentJ> getKonkaParagonEquipmentJPaginatedList(
			KonkaParagonEquipmentJ t);

	List<HashMap<String, String>> selectKonkaParagonEquipmentPaginatedList(
			KonkaParagonEquipmentJ t) throws DataAccessException;

}
