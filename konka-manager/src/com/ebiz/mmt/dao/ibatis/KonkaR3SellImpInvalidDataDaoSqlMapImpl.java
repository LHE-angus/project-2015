package com.ebiz.mmt.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3SellImpInvalidDataDao;
import com.ebiz.mmt.domain.KonkaR3SellImpInvalidData;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Wu,Yang
 * @version 2011-11-16 17:47
 */
@Service
@SuppressWarnings("unchecked")
public class KonkaR3SellImpInvalidDataDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaR3SellImpInvalidData> implements KonkaR3SellImpInvalidDataDao {
	
	/**
	 * @author Li,Ka
	 * @version 2011-11-17 根据R3_sell_date分组订单号
	 */
	@Override
	public List<Map> selectOrderSnGroupList(KonkaR3SellImpInvalidData t) {
		return this.getSqlMapClientTemplate().queryForList("selectOrderSnGroupList", t);
	}

}
