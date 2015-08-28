package com.ebiz.mmt.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MvOrgOfCxyDao;
import com.ebiz.mmt.domain.MvOrgOfCxy;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-05-25 14:26:30
 */
@Service
public class MvOrgOfCxyDaoSqlMapImpl extends EntityDaoSqlMapImpl<MvOrgOfCxy> implements MvOrgOfCxyDao {

	@Override
	public int updateMvOrgOfCxyByCxyUserId(MvOrgOfCxy t) {
		int id = super.getSqlMapClientTemplate().update("updateMvOrgOfCxyByCxyUserId", t);
		return id;
	}

}
