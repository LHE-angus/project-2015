package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxRewardModifyDao;
import com.ebiz.mmt.domain.KonkaXxRewardModify;
import com.ebiz.mmt.service.KonkaXxRewardModifyService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2012-03-21 16:21:20
 */
@Service
public class KonkaXxRewardModifyServiceImpl implements KonkaXxRewardModifyService {

	@Resource
	private KonkaXxRewardModifyDao konkaXxRewardModifyDao;
	

	public Long createKonkaXxRewardModify(KonkaXxRewardModify t) {
		return this.konkaXxRewardModifyDao.insertEntity(t);
	}

	public KonkaXxRewardModify getKonkaXxRewardModify(KonkaXxRewardModify t) {
		return this.konkaXxRewardModifyDao.selectEntity(t);
	}

	public Long getKonkaXxRewardModifyCount(KonkaXxRewardModify t) {
		return this.konkaXxRewardModifyDao.selectEntityCount(t);
	}

	public List<KonkaXxRewardModify> getKonkaXxRewardModifyList(KonkaXxRewardModify t) {
		return this.konkaXxRewardModifyDao.selectEntityList(t);
	}

	public int modifyKonkaXxRewardModify(KonkaXxRewardModify t) {
		return this.konkaXxRewardModifyDao.updateEntity(t);
	}

	public int removeKonkaXxRewardModify(KonkaXxRewardModify t) {
		return this.konkaXxRewardModifyDao.deleteEntity(t);
	}

	public List<KonkaXxRewardModify> getKonkaXxRewardModifyPaginatedList(KonkaXxRewardModify t) {
		return this.konkaXxRewardModifyDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2012-04-01
	 */
	public List<KonkaXxRewardModify> getKonkaXxRewardModifyCountByBillIds(KonkaXxRewardModify t) {
		return this.konkaXxRewardModifyDao.selectKonkaXxRewardModifyCountByBillIds(t);
	}

}
