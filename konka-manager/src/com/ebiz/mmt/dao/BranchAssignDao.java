package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.ssi.dao.EntityDao;
import com.ebiz.mmt.domain.BranchAssign;

public interface BranchAssignDao extends EntityDao<BranchAssign> {

	List<BranchAssign> selectBranchAssignAndKonkaR3ShopList(BranchAssign t);

}