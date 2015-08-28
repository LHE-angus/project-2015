package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcVouchCodeDao;
import com.ebiz.mmt.domain.EcVouchCode;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-24 17:45:53
 */
@Service
public class EcVouchCodeDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcVouchCode> implements EcVouchCodeDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<EcVouchCode> selectEcVouchCodeAndCodeList(EcVouchCode t) {
		return this.getSqlMapClientTemplate().queryForList("selectEcVouchCodeAndCodeList", t);
	}

}
