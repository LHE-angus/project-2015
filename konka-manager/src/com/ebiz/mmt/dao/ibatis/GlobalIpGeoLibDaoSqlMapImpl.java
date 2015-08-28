package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.GlobalIpGeoLibDao;
import com.ebiz.mmt.domain.GlobalIpGeoLib;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-07-05 14:22:07
 */
@Service
public class GlobalIpGeoLibDaoSqlMapImpl extends EntityDaoSqlMapImpl<GlobalIpGeoLib> implements GlobalIpGeoLibDao {

	/**
	 * @author Hu,Hao
	 * @version 2012-07-05
	 */
	@SuppressWarnings("unchecked")
	public List<GlobalIpGeoLib> selectGlobalIpGeoLibForIndexList(GlobalIpGeoLib t) {
		return super.getSqlMapClientTemplate().queryForList("selectGlobalIpGeoLibForIndexList", t);
	}
}
