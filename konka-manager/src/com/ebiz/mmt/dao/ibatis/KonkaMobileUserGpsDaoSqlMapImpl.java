package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileUserGpsDao;
import com.ebiz.mmt.domain.KonkaMobileUserGps;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-05 15:52:02
 */
@Service
public class KonkaMobileUserGpsDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaMobileUserGps> implements
		KonkaMobileUserGpsDao {
	/**
	 * @author Wu,ShangLong
	 * @version 2013-07-08
	 */
	@Override
	public Long selectKonkaMobileUserGpsAndYwyCount(KonkaMobileUserGps t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaMobileUserGpsAndYwyCount", t);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<KonkaMobileUserGps> selectKonkaMobileUserGpsAndYwyPaginatedList(KonkaMobileUserGps t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileUserGpsAndYwyPaginatedList", t);
	}
}
