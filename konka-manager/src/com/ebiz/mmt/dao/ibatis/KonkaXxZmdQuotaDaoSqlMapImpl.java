package com.ebiz.mmt.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdQuotaDao;
import com.ebiz.mmt.domain.KonkaXxZmdQuota;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2012-03-19 15:33:05
 */
@Service
public class KonkaXxZmdQuotaDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaXxZmdQuota> implements KonkaXxZmdQuotaDao {
	
	/**
	 * @author Jiang,JiaYong
	 * @version 2012-03-20
	 */
	public int deleteKonkaXxZmdQuotaByDidAndZidAndQdate(KonkaXxZmdQuota t) {
		return this.getSqlMapClientTemplate().delete("deleteKonkaXxZmdQuotaByDidAndZidAndQdate", t);
	}

}
