package com.ebiz.mmt.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MvOrgOfCustomerDao;
import com.ebiz.mmt.domain.MvOrgOfCustomer;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-05-25 14:26:30
 */
@Service
public class MvOrgOfCustomerDaoSqlMapImpl extends EntityDaoSqlMapImpl<MvOrgOfCustomer> implements MvOrgOfCustomerDao {

	@Override
	public int updateMvOrgOfCustomerByUserId(MvOrgOfCustomer t) {
		int id = super.getSqlMapClientTemplate().update("updateMvOrgOfCustomerByUserId", t);
		return id;
	}

}
