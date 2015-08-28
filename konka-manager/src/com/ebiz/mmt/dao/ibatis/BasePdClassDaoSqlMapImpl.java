package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BasePdClassDao;
import com.ebiz.mmt.domain.BasePdClass;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Chen,ShunHua
 * @date 2011-09-21 10:45:12
 */
@Service
public class BasePdClassDaoSqlMapImpl extends EntityDaoSqlMapImpl<BasePdClass> implements BasePdClassDao {

	@SuppressWarnings("unchecked")
	public List<BasePdClass> selectBasePdClassListByParClassId(BasePdClass t) {
		return super.getSqlMapClientTemplate().queryForList("selectBasePdClassListByParClassId", t);
	}

	@SuppressWarnings("unchecked")
	public List<BasePdClass> selectBasePdClassCountNum(BasePdClass t) {
		return super.getSqlMapClientTemplate().queryForList("selectBasePdClassCountNum", t);
	}
}
