package com.ebiz.mmt.dao;

import java.math.BigDecimal;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.KonkaSell;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-09-01 12:46:44
 */
public interface KonkaSellDao extends EntityDao<KonkaSell> {
	
	/**
	 * @author Wang,Yang
	 * @version 2011-11-10
	 */
	public abstract Long selectKonkaSellCountByPd(KonkaSell t) throws DataAccessException;
	
	public abstract BigDecimal selectKonkaSellCountCostByPd(KonkaSell t) throws DataAccessException;

}
