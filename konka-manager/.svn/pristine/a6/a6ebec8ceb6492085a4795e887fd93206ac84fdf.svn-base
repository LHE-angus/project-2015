package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcGroupDao;
import com.ebiz.mmt.domain.EcGroup;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-08 14:09:38
 */
@Service
public class EcGroupDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcGroup> implements EcGroupDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<EcGroup> selectEcGroupForTreePaginatedList(EcGroup t) {
		return super.getSqlMapClientTemplate().queryForList("selectEcGroupForTreePaginatedList", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EcGroup> selectEcGroupForTreeNameList(EcGroup t) {
		return super.getSqlMapClientTemplate().queryForList("selectEcGroupForTreeNameList", t);
	}

}
