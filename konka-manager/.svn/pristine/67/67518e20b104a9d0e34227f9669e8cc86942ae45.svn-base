package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcGiftOrdeDao;
import com.ebiz.mmt.domain.EcGiftOrde;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
@Service
public class EcGiftOrdeDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcGiftOrde> implements EcGiftOrdeDao {

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-17
	 */
	public Long selectEcGiftOrdeForDeptNameAndFullNameListCount(EcGiftOrde t) throws DataAccessException {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectEcGiftOrdeForDeptNameAndFullNameListCount",
				t);
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-17
	 */
	@SuppressWarnings("unchecked")
	public List<EcGiftOrde> selectEcGiftOrdeForDeptNameAndFullNamePaginatedList(EcGiftOrde t)
			throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectEcGiftOrdeForDeptNameAndFullNamePaginatedList", t);
	}
}
