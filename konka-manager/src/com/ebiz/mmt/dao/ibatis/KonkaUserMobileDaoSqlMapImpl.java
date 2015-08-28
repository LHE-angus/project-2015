package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.KonkaUserMobileDao;
import com.ebiz.mmt.domain.KonkaUserMobile;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Cheng,Bing
 * @version 2012-01-09
 */
@Repository
public class KonkaUserMobileDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaUserMobile> implements KonkaUserMobileDao {

	/**
	 * @author Wu,ShangLong
	 * @version 2013-06-07
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaUserMobile> selectKonkaUserMobileAndGpsList(KonkaUserMobile t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaUserMobileAndGpsList", t);
	}
}
