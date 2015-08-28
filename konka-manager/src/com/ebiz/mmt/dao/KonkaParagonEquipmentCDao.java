package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.KonkaParagonEquipmentC;
import com.ebiz.ssi.dao.EntityDao;

public interface KonkaParagonEquipmentCDao extends EntityDao<KonkaParagonEquipmentC> {

	List<KonkaParagonEquipmentC> selectKonkaParagonEquipment(
			KonkaParagonEquipmentC t) throws DataAccessException;

}