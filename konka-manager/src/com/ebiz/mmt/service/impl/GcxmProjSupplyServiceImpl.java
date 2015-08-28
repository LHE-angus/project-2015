package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.GcxmProjCompetDao;
import com.ebiz.mmt.dao.GcxmProjDao;
import com.ebiz.mmt.dao.GcxmProjSupplyDao;
import com.ebiz.mmt.domain.GcxmProj;
import com.ebiz.mmt.domain.GcxmProjCompet;
import com.ebiz.mmt.domain.GcxmProjSupply;
import com.ebiz.mmt.service.GcxmProjSupplyService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-23 13:53:09
 */
@Service
public class GcxmProjSupplyServiceImpl implements GcxmProjSupplyService {

	@Resource
	private GcxmProjSupplyDao gcxmProjSupplyDao;

	@Resource
	private GcxmProjCompetDao gcxmProjCompetDao;

	@Resource
	private GcxmProjDao gcxmProjDao;

	public Long createGcxmProjSupply(GcxmProjSupply t) {
		return this.gcxmProjSupplyDao.insertEntity(t);
	}

	@Override
	public Long createGcxmProjSupplyForCompt(GcxmProjSupply t) {

		String is_win = (String) t.getMap().get("is_win");
		GcxmProj gp = new GcxmProj();
		gp.setId(Long.valueOf(t.getProj_id()));
		if (is_win != null) {
			gp.setIs_win(Integer.valueOf(is_win));
		}
		this.gcxmProjDao.updateEntity(gp);

		Long id = this.gcxmProjSupplyDao.insertEntity(t);

		List<GcxmProjCompet> gcxmProjCompetList = (List<GcxmProjCompet>) t.getMap().get("gcxmProjCompetList");
		if (gcxmProjCompetList != null && gcxmProjCompetList.size() > 0) {
			for (GcxmProjCompet gcxmProjCompet : gcxmProjCompetList) {
				this.gcxmProjCompetDao.insertEntity(gcxmProjCompet);
			}
		}
		return id;
	}

	public GcxmProjSupply getGcxmProjSupply(GcxmProjSupply t) {
		return this.gcxmProjSupplyDao.selectEntity(t);
	}

	public Long getGcxmProjSupplyCount(GcxmProjSupply t) {
		return this.gcxmProjSupplyDao.selectEntityCount(t);
	}

	public List<GcxmProjSupply> getGcxmProjSupplyList(GcxmProjSupply t) {
		return this.gcxmProjSupplyDao.selectEntityList(t);
	}

	public int modifyGcxmProjSupply(GcxmProjSupply t) {
		return this.gcxmProjSupplyDao.updateEntity(t);
	}

	public int modifyGcxmProjSupplyForCompt(GcxmProjSupply t) {

		GcxmProjSupply gs = new GcxmProjSupply();
		gs.setId(t.getId());
		gs = this.gcxmProjSupplyDao.selectEntity(gs);

		String is_win = (String) t.getMap().get("is_win");
		GcxmProj gp = new GcxmProj();
		gp.setId(Long.valueOf(gs.getProj_id()));
		if (is_win != null) {
			gp.setIs_win(Integer.valueOf(is_win));
		}
		this.gcxmProjDao.updateEntity(gp);

		GcxmProjCompet gc = new GcxmProjCompet();
		gc.setProj_id(Long.valueOf(gs.getProj_id()));
		List<GcxmProjCompet> gcList = this.gcxmProjCompetDao.selectEntityList(gc);
		if (gcList != null && gcList.size() > 0) {
			for (GcxmProjCompet gcxmProjCompet : gcList) {
				this.gcxmProjCompetDao.deleteEntity(gcxmProjCompet);
			}
		}

		List<GcxmProjCompet> gcxmProjCompetList = (List<GcxmProjCompet>) t.getMap().get("gcxmProjCompetList");
		if (gcxmProjCompetList != null && gcxmProjCompetList.size() > 0) {
			for (GcxmProjCompet gcxmProjCompet : gcxmProjCompetList) {
				this.gcxmProjCompetDao.insertEntity(gcxmProjCompet);
			}
		}

		return this.gcxmProjSupplyDao.updateEntity(t);
	}

	public int removeGcxmProjSupply(GcxmProjSupply t) {
		return this.gcxmProjSupplyDao.deleteEntity(t);
	}

	public List<GcxmProjSupply> getGcxmProjSupplyPaginatedList(GcxmProjSupply t) {
		return this.gcxmProjSupplyDao.selectEntityPaginatedList(t);
	}

}
