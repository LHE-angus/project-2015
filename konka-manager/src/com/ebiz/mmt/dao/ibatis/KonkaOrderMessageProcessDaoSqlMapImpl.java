package com.ebiz.mmt.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaOrderMessageProcessDao;
import com.ebiz.mmt.domain.JStocksSummary;
import com.ebiz.mmt.domain.KonkaOrderMessageProcess;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-15 14:04:59
 */
@Service
public class KonkaOrderMessageProcessDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaOrderMessageProcess> implements KonkaOrderMessageProcessDao {

	@Override
	public List<Map<String, Object>> selectAll_BCB_List(KonkaOrderMessageProcess t) {
		return super.getSqlMapClientTemplate().queryForList("selectAll_BCB_List", t);
	}

}
