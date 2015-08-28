package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaStockDetailsDao;
import com.ebiz.mmt.domain.KonkaStockDetails;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-08-25 08:32:14
 */
@Service
public class KonkaStockDetailsDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaStockDetails> implements KonkaStockDetailsDao {

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-25
	 */
	public int deleteKonkaStockDetailsWithSID(KonkaStockDetails t) throws DataAccessException {
		return this.getSqlMapClientTemplate().delete("deleteKonkaStockDetailsWithSID", t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-25
	 */
	public Long selectKonkaStockDetailsForStatisticsCount(KonkaStockDetails t) throws DataAccessException {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectKonkaStockDetailsForStatisticsCount", t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-25
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaStockDetails> selectKonkaStockDetailsForStatisticsPaginatedList(KonkaStockDetails t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaStockDetailsForStatisticsPaginatedList", t);
	}

}
