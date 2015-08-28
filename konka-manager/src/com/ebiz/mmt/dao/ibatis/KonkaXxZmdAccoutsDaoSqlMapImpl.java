package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdAccoutsDao;
import com.ebiz.mmt.domain.KonkaXxZmdAccouts;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Xu,XiaoYuan
 * @version 2012-03-02 09:12
 */
@Service
public class KonkaXxZmdAccoutsDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaXxZmdAccouts> implements
		KonkaXxZmdAccoutsDao {

	/**
	 * @author hu,hao
	 * @version 2012-03-05
	 */
	public Long selectKonkaXxZmdAccoutsForZmdAndDeptCount(KonkaXxZmdAccouts t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaXxZmdAccoutsForZmdAndDeptCount", t);
	}

	/**
	 * @author hu,hao
	 * @version 2012-03-05
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxZmdAccouts> selectKonkaXxZmdAccoutsForZmdAndDeptPaginatedList(KonkaXxZmdAccouts t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaXxZmdAccoutsForZmdAndDeptPaginatedList", t);
	}
}
