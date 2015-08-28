package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.ShopMemberDao;
import com.ebiz.mmt.domain.ShopMember;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Jin,QingHua
 */
@Repository
public class ShopMemberDaoSqlMapImpl extends EntityDaoSqlMapImpl<ShopMember> implements ShopMemberDao {

	@SuppressWarnings("unchecked")
	public List<ShopMember> selectShopMemberSumPaginatedList(ShopMember ShopMember) {
		return super.getSqlMapClientTemplate().queryForList("selectShopMemberSumPaginatedList", ShopMember);
	}

	public Long selectShopMemberSumCount(ShopMember ShopMember) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectShopMemberSumCount", ShopMember);
	}

}
