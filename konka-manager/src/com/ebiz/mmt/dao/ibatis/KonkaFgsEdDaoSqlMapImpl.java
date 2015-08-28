package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaFgsEdDao;
import com.ebiz.mmt.domain.KonkaFgsEd;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-21 10:27:05
 */
@Service
public class KonkaFgsEdDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaFgsEd> implements KonkaFgsEdDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaFgsEd> selectKonkaFgsEdGroupByDeptIdList(KonkaFgsEd t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaFgsEdGroupByDeptIdList", t);
	}

}
