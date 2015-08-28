package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;
import com.ebiz.mmt.dao.BranchAssignDao;
import com.ebiz.mmt.domain.BranchAssign;

@Repository
public class BranchAssignDaoSqlMapImpl extends EntityDaoSqlMapImpl<BranchAssign> implements BranchAssignDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<BranchAssign> selectBranchAssignAndKonkaR3ShopList(BranchAssign t) {
		
		return this.getSqlMapClientTemplate().queryForList("selectBranchAssignAndKonkaR3ShopList",t);
	}

}

