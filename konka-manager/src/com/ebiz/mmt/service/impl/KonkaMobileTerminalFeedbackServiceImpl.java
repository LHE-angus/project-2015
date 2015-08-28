package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileTerminalFeedbackDao;
import com.ebiz.mmt.domain.KonkaMobileTerminalFeedback;
import com.ebiz.mmt.service.KonkaMobileTerminalFeedbackService;


@Service
public class KonkaMobileTerminalFeedbackServiceImpl implements KonkaMobileTerminalFeedbackService {

	@Resource
	private KonkaMobileTerminalFeedbackDao konkaMobileTerminalFeedbackDao;

	public Long createKonkaMobileTerminalFeedback(KonkaMobileTerminalFeedback t) {
		return this.konkaMobileTerminalFeedbackDao.insertEntity(t);
	}

	public KonkaMobileTerminalFeedback getKonkaMobileTerminalFeedback(KonkaMobileTerminalFeedback t) {
		return this.konkaMobileTerminalFeedbackDao.selectEntity(t);
	}

	public Long getKonkaMobileTerminalFeedbackCount(KonkaMobileTerminalFeedback t) {
		return this.konkaMobileTerminalFeedbackDao.selectEntityCount(t);
	}

	public List<KonkaMobileTerminalFeedback> getKonkaMobileTerminalFeedbackList(KonkaMobileTerminalFeedback t) {
		return this.konkaMobileTerminalFeedbackDao.selectEntityList(t);
	}

	public int modifyKonkaMobileTerminalFeedback(KonkaMobileTerminalFeedback t) {
		return this.konkaMobileTerminalFeedbackDao.updateEntity(t);
	}

	public int removeKonkaMobileTerminalFeedback(KonkaMobileTerminalFeedback t) {
		return this.konkaMobileTerminalFeedbackDao.deleteEntity(t);
	}

	public List<KonkaMobileTerminalFeedback> getKonkaMobileTerminalFeedbackPaginatedList(KonkaMobileTerminalFeedback t) {
		return this.konkaMobileTerminalFeedbackDao.selectEntityPaginatedList(t);
	}
	/**
	 * @author Wang,Yang
	 * @version 2011-11-18
	 */
	
	public Long getKonkaMobileTerminalFeedbackWithFbBackCount(KonkaMobileTerminalFeedback t){
		return this.konkaMobileTerminalFeedbackDao.selectKonkaMobileTerminalFeedbackWithFbBackCount(t);
	}
	
	public List<KonkaMobileTerminalFeedback> getKonkaMobileTerminalFeedbackListWithFbBack(KonkaMobileTerminalFeedback t){
		return this.konkaMobileTerminalFeedbackDao.selectKonkaMobileTerminalFeedbackListWithFbBack(t);
	}

}
