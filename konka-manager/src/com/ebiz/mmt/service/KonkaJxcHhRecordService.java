package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaJxcHhRecord;

/**
 * @author Wu,Yang
 * @version 2011-10-11 09:18
 */
public interface KonkaJxcHhRecordService {

	Long createKonkaJxcHhRecord(KonkaJxcHhRecord t);

	int modifyKonkaJxcHhRecord(KonkaJxcHhRecord t);

	int removeKonkaJxcHhRecord(KonkaJxcHhRecord t);

	KonkaJxcHhRecord getKonkaJxcHhRecord(KonkaJxcHhRecord t);

	List<KonkaJxcHhRecord> getKonkaJxcHhRecordList(KonkaJxcHhRecord t);

	Long getKonkaJxcHhRecordCount(KonkaJxcHhRecord t);

	List<KonkaJxcHhRecord> getKonkaJxcHhRecordPaginatedList(KonkaJxcHhRecord t);

}