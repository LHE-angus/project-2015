package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaOrderInfoUpdateRecord;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Wu,Yang
 * @version 2011-12-13 09:56
 */
public interface KonkaOrderInfoUpdateRecordDao extends EntityDao<KonkaOrderInfoUpdateRecord> {

	/**
	 * @author Wu,ShangLong
	 * @version 2013-08-09
	 */
	List<KonkaOrderInfoUpdateRecord> selectKonkaOrderInfoUpdateRecordGroupList(KonkaOrderInfoUpdateRecord t);
}
