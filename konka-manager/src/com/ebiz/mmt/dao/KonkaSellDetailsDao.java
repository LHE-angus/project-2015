package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.KonkaSellDetails;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-09-01 12:46:44
 */
public interface KonkaSellDetailsDao extends EntityDao<KonkaSellDetails> {

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-25
	 */
	public abstract int deleteKonkaSellDetailsWithSID(KonkaSellDetails t)
			throws DataAccessException;

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-25
	 */
	public abstract List<KonkaSellDetails> selectKonkaSellDetailsForStatisticsPaginatedList(
			KonkaSellDetails t) throws DataAccessException;

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-25
	 */
	public abstract Long selectKonkaSellDetailsForStatisticsCount(
			KonkaSellDetails t) throws DataAccessException;

	public List<KonkaSellDetails> selectKonkaSellDetailsToSum(KonkaSellDetails t);

}
