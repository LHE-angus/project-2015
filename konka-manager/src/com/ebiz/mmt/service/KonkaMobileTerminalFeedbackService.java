package com.ebiz.mmt.service;

import java.util.List;
import com.ebiz.mmt.domain.KonkaMobileTerminalFeedback;

public interface KonkaMobileTerminalFeedbackService {

	Long createKonkaMobileTerminalFeedback(KonkaMobileTerminalFeedback t);

	int modifyKonkaMobileTerminalFeedback(KonkaMobileTerminalFeedback t);

	int removeKonkaMobileTerminalFeedback(KonkaMobileTerminalFeedback t);

	KonkaMobileTerminalFeedback getKonkaMobileTerminalFeedback(KonkaMobileTerminalFeedback t);

	List<KonkaMobileTerminalFeedback> getKonkaMobileTerminalFeedbackList(KonkaMobileTerminalFeedback t);

	Long getKonkaMobileTerminalFeedbackCount(KonkaMobileTerminalFeedback t);

	List<KonkaMobileTerminalFeedback> getKonkaMobileTerminalFeedbackPaginatedList(KonkaMobileTerminalFeedback t);

	/**
	 * @author Wang,Yang
	 * @version 2011-11-18
	 */
	
	Long getKonkaMobileTerminalFeedbackWithFbBackCount(KonkaMobileTerminalFeedback t);
	
	List<KonkaMobileTerminalFeedback> getKonkaMobileTerminalFeedbackListWithFbBack(KonkaMobileTerminalFeedback t);
}
