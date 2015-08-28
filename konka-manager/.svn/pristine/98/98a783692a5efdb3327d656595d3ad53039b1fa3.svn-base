package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaOrderInfoUpdateRecordDao;
import com.ebiz.mmt.domain.KonkaOrderInfoUpdateRecord;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Wu,Yang
 * @version 2011-12-13 09:56
 */
@Service
public class KonkaOrderInfoUpdateRecordDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaOrderInfoUpdateRecord> implements
		KonkaOrderInfoUpdateRecordDao {

	/**
	 * @author Wu,ShangLong
	 * @version 2013-08-09
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaOrderInfoUpdateRecord> selectKonkaOrderInfoUpdateRecordGroupList(KonkaOrderInfoUpdateRecord t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaOrderInfoUpdateRecordGroupList", t);
	}
}
