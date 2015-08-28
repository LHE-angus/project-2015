package com.ebiz.mmt.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MvOrgOfCxyAllDao;
import com.ebiz.mmt.domain.MvOrgOfCxyAll;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-05-25 14:26:30
 */
@Service
public class MvOrgOfCxyAllDaoSqlMapImpl extends EntityDaoSqlMapImpl<MvOrgOfCxyAll> implements MvOrgOfCxyAllDao {

	@Override
	public int updateMvOrgOfCxyAllByCxyUserId(MvOrgOfCxyAll t) {
		int id = super.getSqlMapClientTemplate().update("updateMvOrgOfCxyAllByCxyUserId", t);
		return id;
	}

}
