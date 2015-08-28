package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcVoteOptionDao;
import com.ebiz.mmt.domain.EcVoteMain;
import com.ebiz.mmt.domain.EcVoteOption;
import com.ebiz.mmt.service.EcVoteOptionService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-29 14:02:25
 */
@Service
public class EcVoteOptionServiceImpl implements EcVoteOptionService {

	@Resource
	private EcVoteOptionDao ecVoteOptionDao;
	

	public Long createEcVoteOption(EcVoteOption t) {
		return this.ecVoteOptionDao.insertEntity(t);
	}

	public EcVoteOption getEcVoteOption(EcVoteOption t) {
		return this.ecVoteOptionDao.selectEntity(t);
	}

	public Long getEcVoteOptionCount(EcVoteOption t) {
		return this.ecVoteOptionDao.selectEntityCount(t);
	}

	public List<EcVoteOption> getEcVoteOptionList(EcVoteOption t) {
		return this.ecVoteOptionDao.selectEntityList(t);
	}

	public int modifyEcVoteOption(EcVoteOption t) {
		return this.ecVoteOptionDao.updateEntity(t);
	}

	public int removeEcVoteOption(EcVoteOption t) {
		return this.ecVoteOptionDao.deleteEntity(t);
	}

	public List<EcVoteOption> getEcVoteOptionPaginatedList(EcVoteOption t) {
		return this.ecVoteOptionDao.selectEntityPaginatedList(t);
	}
	
	public int modifyEcVoteOptionViewCounts(EcVoteOption t) {
		return this.ecVoteOptionDao.updateEcVoteOptionViewCounts(t);
	}

}
