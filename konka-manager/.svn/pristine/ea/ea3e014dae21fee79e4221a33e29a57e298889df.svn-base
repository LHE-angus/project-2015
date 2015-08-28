package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxLogisticsDao;
import com.ebiz.mmt.domain.KonkaXxLogistics;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-03-31 14:24:50
 */
@Service
public class KonkaXxLogisticsDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaXxLogistics> implements KonkaXxLogisticsDao {

	/**
	 * @author Wu,ShangLong
	 * @version 2012-04-06
	 */
	public Long selectKonkaXxLogisticsWithPNameCount(KonkaXxLogistics t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaXxLogisticsWithPNameCount", t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2012-04-06
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxLogistics> selectKonkaXxLogisticsWithPNamePaginatedList(KonkaXxLogistics t)
			throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaXxLogisticsWithPNamePaginatedList", t);
	}

}
