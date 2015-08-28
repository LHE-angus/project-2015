package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcExtendDao;
import com.ebiz.mmt.domain.EcExtend;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-03-04 15:46:59
 */
@Service
public class EcExtendDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcExtend> implements EcExtendDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<EcExtend> selectEcExtendGroupByPropNameList(EcExtend t) {
		return super.getSqlMapClientTemplate().queryForList("selectEcExtendGroupByPropNameList", t);
	}

	@SuppressWarnings("unchecked")
	public List<EcExtend> selectEcExtendGroupByPropNameForPropValueList(EcExtend t) {
		return super.getSqlMapClientTemplate().queryForList("selectEcExtendGroupByPropNameForPropValueList", t);
	}

}
