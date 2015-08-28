package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaOrderMeetingManager;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-01-23 11:36:10
 */
public interface KonkaOrderMeetingManagerService {

	Long createKonkaOrderMeetingManager(KonkaOrderMeetingManager t);

	int modifyKonkaOrderMeetingManager(KonkaOrderMeetingManager t);

	int removeKonkaOrderMeetingManager(KonkaOrderMeetingManager t);

	KonkaOrderMeetingManager getKonkaOrderMeetingManager(KonkaOrderMeetingManager t);

	List<KonkaOrderMeetingManager> getKonkaOrderMeetingManagerList(KonkaOrderMeetingManager t);

	Long getKonkaOrderMeetingManagerCount(KonkaOrderMeetingManager t);

	List<KonkaOrderMeetingManager> getKonkaOrderMeetingManagerPaginatedList(KonkaOrderMeetingManager t);

	Long getSequenceResult(KonkaOrderMeetingManager t);
}