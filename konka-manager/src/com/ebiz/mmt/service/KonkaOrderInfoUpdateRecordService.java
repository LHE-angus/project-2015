package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaOrderInfoUpdateRecord;

/**
 * @author Wu,Yang
 * @version 2011-12-13 09:56
 */
public interface KonkaOrderInfoUpdateRecordService {

	Long createKonkaOrderInfoUpdateRecord(KonkaOrderInfoUpdateRecord t);

	int modifyKonkaOrderInfoUpdateRecord(KonkaOrderInfoUpdateRecord t);

	int removeKonkaOrderInfoUpdateRecord(KonkaOrderInfoUpdateRecord t);

	KonkaOrderInfoUpdateRecord getKonkaOrderInfoUpdateRecord(KonkaOrderInfoUpdateRecord t);

	List<KonkaOrderInfoUpdateRecord> getKonkaOrderInfoUpdateRecordList(KonkaOrderInfoUpdateRecord t);

	Long getKonkaOrderInfoUpdateRecordCount(KonkaOrderInfoUpdateRecord t);

	List<KonkaOrderInfoUpdateRecord> getKonkaOrderInfoUpdateRecordPaginatedList(KonkaOrderInfoUpdateRecord t);

	/**
	 * @author Wu,ShangLong
	 * @version 2013-08-09
	 */
	List<KonkaOrderInfoUpdateRecord> getKonkaOrderInfoUpdateRecordGroupList(KonkaOrderInfoUpdateRecord t);
}