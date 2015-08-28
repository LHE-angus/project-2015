package com.ebiz.mmt.dao.ibatis;

import java.math.BigDecimal;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaSellDao;
import com.ebiz.mmt.domain.KonkaSell;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-09-01 12:46:44
 */
@Service
public class KonkaSellDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaSell> implements KonkaSellDao {
	
	/**
	 * @author Wang,Yang
	 * @version 2011-11-10
	 */
	public Long selectKonkaSellCountByPd(KonkaSell t) throws DataAccessException {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectKonkaSellCountByPd", t);
	}

	public BigDecimal selectKonkaSellCountCostByPd(KonkaSell t) throws DataAccessException {
		return (BigDecimal) this.getSqlMapClientTemplate().queryForObject("selectKonkaSellCountCostByPd", t);
	}

}
