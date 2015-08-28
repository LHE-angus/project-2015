package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.SfhkRelEppOrderDao;
import com.ebiz.mmt.domain.SfhkRelEppOrder;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-23 14:51:55
 */
@Service
public class SfhkRelEppOrderDaoSqlMapImpl extends EntityDaoSqlMapImpl<SfhkRelEppOrder> implements SfhkRelEppOrderDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<SfhkRelEppOrder> selectSfhkRelEppOrderAndOrderIdList(SfhkRelEppOrder t) {
		return super.getSqlMapClientTemplate().queryForList("selectSfhkRelEppOrderAndOrderIdList", t);
	}

}
