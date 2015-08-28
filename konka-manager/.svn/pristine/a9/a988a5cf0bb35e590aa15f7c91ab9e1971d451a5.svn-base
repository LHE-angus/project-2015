package com.ebiz.mmt.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.CrmCustomerGroupDao;
import com.ebiz.mmt.domain.CrmCustomerGroup;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;


@Service
public class CrmCustomerGroupDaoSqlMapImpl extends EntityDaoSqlMapImpl<CrmCustomerGroup> implements CrmCustomerGroupDao {

	@Override
	public CrmCustomerGroup selectCrmCustomerGroupByCustomerIdAndDeptId(CrmCustomerGroup t) {
		return (CrmCustomerGroup) getSqlMapClientTemplate().queryForObject(
				"selectCrmCustomerGroupByCustomerCodeAndDeptId", t);
	}

}
