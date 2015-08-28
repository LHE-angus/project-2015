package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.ShopEmployeeDao;
import com.ebiz.mmt.domain.ShopEmployee;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Jin,QingHua
 */
@Repository
public class ShopEmployeeDaoSqlMapImpl extends EntityDaoSqlMapImpl<ShopEmployee> implements ShopEmployeeDao {

	@SuppressWarnings("unchecked")
	public List<ShopEmployee> selectShopEmployeeSumPaginatedList(ShopEmployee ShopEmployee) {
		return super.getSqlMapClientTemplate().queryForList("selectShopEmployeeSumPaginatedList", ShopEmployee);
	}

	public Long selectShopEmployeeSumCount(ShopEmployee ShopEmployee) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectShopEmployeeSumCount", ShopEmployee);
	}

}
