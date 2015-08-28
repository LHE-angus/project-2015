package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaYjglYjedDao;
import com.ebiz.mmt.domain.KonkaYjglYjed;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-03 14:08:34
 */
@Service
public class KonkaYjglYjedDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaYjglYjed> implements KonkaYjglYjedDao {

	@Override
	public Long selectKonkaYjglYjedAndDeptNameCount(KonkaYjglYjed t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaYjglYjedAndDeptNameCount", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaYjglYjed> selectKonkaYjglYjedAndDeptNamePaginatedList(KonkaYjglYjed t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaYjglYjedAndDeptNamePaginatedList", t);
	}

}
