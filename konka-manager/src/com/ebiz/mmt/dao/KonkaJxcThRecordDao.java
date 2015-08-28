package com.ebiz.mmt.dao;

import com.ebiz.mmt.domain.KonkaJxcThRecord;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Wu,Yang
 * @version 2011-10-14 19:45
 */
public interface KonkaJxcThRecordDao extends EntityDao<KonkaJxcThRecord> {

	/**
	 * @author GUO,JUN
	 * @date 2011-10-18
	 */
	KonkaJxcThRecord selectKonkaJxcThRecordWith2Names(KonkaJxcThRecord t);
}
