package com.ebiz.mmt.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.ssi.dao.EntityDao;
import com.ebiz.mmt.domain.KonkaParagonEtcash;

public interface KonkaParagonEtcashDao extends EntityDao<KonkaParagonEtcash> {
	public List<HashMap<String, String>> selectKonkaParagonEtcashListByOne(
			KonkaParagonEtcash t) throws DataAccessException;
}