package com.ebiz.mmt.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaJxcFhBillDetailsDao;
import com.ebiz.mmt.domain.KonkaJxcFhBillDetails;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Wu,Yang
 * @version 2011-10-12 16:24
 */
@Service
@SuppressWarnings("unchecked")
public class KonkaJxcFhBillDetailsDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaJxcFhBillDetails> implements KonkaJxcFhBillDetailsDao {


	public List<KonkaJxcFhBillDetails> selectKonkaJxcFhBillDetailsWithNamesList(KonkaJxcFhBillDetails t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaJxcFhBillDetailsWithNamesList", t);
	}


	@Override
	public List<Map> selectKonkaJxcFhBillDetailsSumPdCountList(KonkaJxcFhBillDetails t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaJxcFhBillDetailsSumPdCountList", t);
	}

	@Override
	public List<Map> selectKonkaJxcFhBillDetailsSumPdCountListWithSrc(KonkaJxcFhBillDetails t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaJxcFhBillDetailsSumPdCountListWithSrc", t);
	}
}
