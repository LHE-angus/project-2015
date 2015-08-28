package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaActInfoDao;
import com.ebiz.mmt.domain.KonkaActInfo;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-06 14:11:44
 */
@Service
public class KonkaActInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaActInfo> implements KonkaActInfoDao {

	public Long selectKonkaActInfoNoMax(KonkaActInfo t) {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectKonkaActInfoNoMax", t);
	}

	@SuppressWarnings("unchecked")
	public List<KonkaActInfo> selectKonkaActInfoAndSaleDatasList(KonkaActInfo t) {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaActInfoAndSaleDatasList", t);
	}

}
