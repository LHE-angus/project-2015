package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BranchAssignDao;
import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.service.BranchAssignService;


@Service
public class BranchAssignServiceImpl implements BranchAssignService {

	@Resource
	private BranchAssignDao branchAssignDao;

	public Long createBranchAssign(BranchAssign t) {
		return this.branchAssignDao.insertEntity(t);
	}

	public BranchAssign getBranchAssign(BranchAssign t) {
		return this.branchAssignDao.selectEntity(t);
	}

	public Long getBranchAssignCount(BranchAssign t) {
		return this.branchAssignDao.selectEntityCount(t);
	}

	public List<BranchAssign> getBranchAssignList(BranchAssign t) {
		return this.branchAssignDao.selectEntityList(t);
	}

	public int modifyBranchAssign(BranchAssign t) {
		return this.branchAssignDao.updateEntity(t);
	}

	public int removeBranchAssign(BranchAssign t) {
		return this.branchAssignDao.deleteEntity(t);
	}

	public List<BranchAssign> getBranchAssignPaginatedList(BranchAssign t) {
		return this.branchAssignDao.selectEntityPaginatedList(t);
	}
	
	public Long createBranchAssignAndBranchCategory(BranchAssign t) {
		BranchAssign branchAssign = new BranchAssign();
		branchAssign.setKonka_r3_id(t.getKonka_r3_id());
		this.branchAssignDao.deleteEntity(branchAssign);
		return this.branchAssignDao.insertEntity(t);
	}

	@Override
	public List<BranchAssign> getBranchAssignAndKonkaR3ShopList(BranchAssign t) {
		
		return this.branchAssignDao.selectBranchAssignAndKonkaR3ShopList(t);
	}

}
