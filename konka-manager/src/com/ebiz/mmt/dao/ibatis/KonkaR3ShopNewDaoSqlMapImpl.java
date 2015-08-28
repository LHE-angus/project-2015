package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import com.ebiz.mmt.dao.KonkaR3ShopNewDao;
import com.ebiz.mmt.domain.KonkaR3ShopNew;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-30 11:05:44
 */
@Service
public class KonkaR3ShopNewDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaR3ShopNew> implements KonkaR3ShopNewDao {

	@Override
	public Long insertNewCustomer(KonkaR3ShopNew t) {
		return (Long)super.getSqlMapClientTemplate().queryForObject("insertNewCustomer",t);
	}

	@Override
	public Long selectWaitAuditCustCount(KonkaR3ShopNew t) {
		return (Long)super.getSqlMapClientTemplate().queryForObject("selectWaitAuditCustCount",t);
	}

	@Override
	public List<HashMap> selectWaitAuditCustList(KonkaR3ShopNew t) {
		return super.getSqlMapClientTemplate().queryForList("selectWaitAuditCustList",t);
	}

	@Override
	public KonkaR3ShopNew selectKonkaR3ShopNewByCustId(KonkaR3ShopNew t) {
		return (KonkaR3ShopNew)super.getSqlMapClientTemplate().queryForObject("selectKonkaR3ShopNewByCustId",t);
	}

}
