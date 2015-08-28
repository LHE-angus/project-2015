package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcSpecFbCalDao;
import com.ebiz.mmt.domain.EcSpecFbCal;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-08 10:31:47
 */
@Service
public class EcSpecFbCalDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcSpecFbCal> implements EcSpecFbCalDao {

	@SuppressWarnings("unchecked")
	public List<EcSpecFbCal> selectEcSpecFbCalForTjList(EcSpecFbCal t) {
		return super.getSqlMapClientTemplate().queryForList("selectEcSpecFbCalForTjList", t);
	}

}
