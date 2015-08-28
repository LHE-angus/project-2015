package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcBcompBindingDao;
import com.ebiz.mmt.domain.EcBcompBinding;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-10 11:43:03
 */
@Service
public class EcBcompBindingDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcBcompBinding> implements EcBcompBindingDao {

	/**
	 * @author Pan,Gang
	 * @date 2013-09-13
	 */
	@SuppressWarnings("unchecked")
	public List<EcBcompBinding> selectEcBcompBindingForFuWuTaoCanList(EcBcompBinding t) {
		return this.getSqlMapClientTemplate().queryForList("selectEcBcompBindingForFuWuTaoCanList", t);
	}

	@SuppressWarnings("unchecked")
	public List<EcBcompBinding> selectEcBcompBindingForPdTaoCanList(EcBcompBinding t) {
		return this.getSqlMapClientTemplate().queryForList("selectEcBcompBindingForPdTaoCanList", t);
	}

}
