package com.ebiz.mmt.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MvOrgOfCustomerAllDao;
import com.ebiz.mmt.domain.MvOrgOfCustomerAll;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-05-25 14:26:30
 */
@Service
public class MvOrgOfCustomerAllDaoSqlMapImpl extends EntityDaoSqlMapImpl<MvOrgOfCustomerAll> implements
		MvOrgOfCustomerAllDao {

	@Override
	public int updateMvOrgOfCustomerAllByUserId(MvOrgOfCustomerAll t) {
		int id = super.getSqlMapClientTemplate().update("updateMvOrgOfCustomerAllByUserId", t);
		return id;
	}

}
