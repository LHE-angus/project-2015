package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaOrderInfoDetailsDao;
import com.ebiz.mmt.domain.KonkaOrderInfoDetails;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Wu,Yang
 * @version 2011-11-25 09:08
 */
@Service
public class KonkaOrderInfoDetailsDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaOrderInfoDetails> implements KonkaOrderInfoDetailsDao {
	
	/**
	 * Xiao GuoJian 获取详细列表，用于发货显示和添加
	 * KonkaOrderInfoDetails中只能添加trade_index和trade_index_pks用于查询
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaOrderInfoDetails> selectKonkaOrderInfoDetailsForTrans(KonkaOrderInfoDetails t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaOrderInfoDetailsForTrans", t);
	}
}
