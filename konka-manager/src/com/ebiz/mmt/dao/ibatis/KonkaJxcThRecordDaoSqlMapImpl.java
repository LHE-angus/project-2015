package com.ebiz.mmt.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaJxcThRecordDao;
import com.ebiz.mmt.domain.KonkaJxcThRecord;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Wu,Yang
 * @version 2011-10-14 19:45
 */
@Service
public class KonkaJxcThRecordDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaJxcThRecord> implements KonkaJxcThRecordDao {

	/**
	 * @author GUO,JUN
	 * @date 2011-10-18
	 */
	public KonkaJxcThRecord selectKonkaJxcThRecordWith2Names(KonkaJxcThRecord t){
		return (KonkaJxcThRecord)super.getSqlMapClientTemplate().queryForObject("selectKonkaJxcThRecordWith2Names", t);
	}
}
