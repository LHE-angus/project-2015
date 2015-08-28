package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaMobileTerminalFeedback;
import com.ebiz.ssi.dao.EntityDao;

public interface KonkaMobileTerminalFeedbackDao extends EntityDao<KonkaMobileTerminalFeedback> {

	/**
	 * @author Wang,Yang
	 * @version 2011-11-18
	 */
	public Long selectKonkaMobileTerminalFeedbackWithFbBackCount(KonkaMobileTerminalFeedback t);
	
	public List<KonkaMobileTerminalFeedback> selectKonkaMobileTerminalFeedbackListWithFbBack(KonkaMobileTerminalFeedback t);
}