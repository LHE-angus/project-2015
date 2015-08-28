package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcSpecFbCalDao;
import com.ebiz.mmt.dao.EcSpecFbUgdetailDao;
import com.ebiz.mmt.domain.EcSpecFbCal;
import com.ebiz.mmt.domain.EcSpecFbUgdetail;
import com.ebiz.mmt.service.EcSpecFbCalService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-08 10:31:47
 */
@Service
public class EcSpecFbCalServiceImpl implements EcSpecFbCalService {

	@Resource
	private EcSpecFbCalDao ecSpecFbCalDao;

	@Resource
	private EcSpecFbUgdetailDao ecSpecFbUgdetailDao;

	public Long createEcSpecFbCal(EcSpecFbCal t) {
		return this.ecSpecFbCalDao.insertEntity(t);
	}

	public EcSpecFbCal getEcSpecFbCal(EcSpecFbCal t) {
		return this.ecSpecFbCalDao.selectEntity(t);
	}

	public Long getEcSpecFbCalCount(EcSpecFbCal t) {
		return this.ecSpecFbCalDao.selectEntityCount(t);
	}

	public List<EcSpecFbCal> getEcSpecFbCalList(EcSpecFbCal t) {
		return this.ecSpecFbCalDao.selectEntityList(t);
	}

	public int modifyEcSpecFbCal(EcSpecFbCal t) {

		int id = this.ecSpecFbCalDao.updateEntity(t);
		// 维护比方，同时更新用户的竞猜得分
		if (null != t.getA_team_goal() && null != t.getB_team_goal()) {
			EcSpecFbUgdetail ec = new EcSpecFbUgdetail();
			ec.setMatch_id(t.getMatch_id());
			List<EcSpecFbUgdetail> ecList = this.ecSpecFbUgdetailDao.selectEntityList(ec);
			for (EcSpecFbUgdetail ecs : ecList) {
				// 猜输赢 猜对+30 猜输 不扣分
				if (null != ecs.getGuess_win() && ecs.getGuess_win().intValue() == t.getPlay_status().intValue()) {
					ecs.setGuess_win_gift(30);
				}
				// 猜比分 猜对+50 猜输 -15
				if (null != ecs.getGuess_a_team_goal() && null != ecs.getGuess_b_team_goal()) {
					if (ecs.getGuess_a_team_goal().intValue() == t.getA_team_goal().intValue()
					        && ecs.getGuess_b_team_goal().intValue() == t.getB_team_goal().intValue()) {
						ecs.setGuess_goal_gift(50);
					} else {
						ecs.setGuess_goal_gift(-15);
					}
				}
				this.ecSpecFbUgdetailDao.updateEntity(ecs);
			}
		}

		return id;
	}

	public int removeEcSpecFbCal(EcSpecFbCal t) {
		return this.ecSpecFbCalDao.deleteEntity(t);
	}

	public List<EcSpecFbCal> getEcSpecFbCalPaginatedList(EcSpecFbCal t) {
		return this.ecSpecFbCalDao.selectEntityPaginatedList(t);
	}

	public List<EcSpecFbCal> getEcSpecFbCalForTjList(EcSpecFbCal t) {
		return this.ecSpecFbCalDao.selectEcSpecFbCalForTjList(t);
	}

}
