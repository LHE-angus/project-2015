package com.ebiz.mmt.dao.ibatis;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.ebiz.mmt.dao.KonkaSellDetailsDao;
import com.ebiz.mmt.domain.KonkaSellDetails;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-08-25 08:32:14
 */
@Service
public class KonkaSellDetailsDaoSqlMapImpl extends
		EntityDaoSqlMapImpl<KonkaSellDetails> implements KonkaSellDetailsDao {

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-25
	 */
	public int deleteKonkaSellDetailsWithSID(KonkaSellDetails t)
			throws DataAccessException {
		return this.getSqlMapClientTemplate().delete(
				"deleteKonkaSellDetailsWithSID", t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-25
	 */
	public Long selectKonkaSellDetailsForStatisticsCount(KonkaSellDetails t)
			throws DataAccessException {
		return (Long) this.getSqlMapClientTemplate().queryForObject(
				"selectKonkaSellDetailsForStatisticsCount", t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-25
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaSellDetails> selectKonkaSellDetailsForStatisticsPaginatedList(
			KonkaSellDetails t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList(
				"selectKonkaSellDetailsForStatisticsPaginatedList", t);
	}

	@SuppressWarnings("unchecked")
	public List<KonkaSellDetails> selectKonkaSellDetailsToSum(
			KonkaSellDetails t) {
		return this.getSqlMapClientTemplate().queryForList(
				"selectKonkaSellDetailsToSum", t);
	}

}
