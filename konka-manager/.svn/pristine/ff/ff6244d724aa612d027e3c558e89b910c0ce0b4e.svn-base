package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileUserGpsTrackDao;
import com.ebiz.mmt.domain.KonkaMobileUserGpsTrack;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-05 15:52:02
 */
@Service
public class KonkaMobileUserGpsTrackDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaMobileUserGpsTrack> implements
		KonkaMobileUserGpsTrackDao {

	/**
	 * @author Wu,ShangLong
	 * @version 2013-07-12
	 */
	public Long selectKonkaMobileUserGpsTrackAndYwyCount(KonkaMobileUserGpsTrack t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaMobileUserGpsTrackAndYwyCount", t);
	}

	@SuppressWarnings("unchecked")
	public List<KonkaMobileUserGpsTrack> selectKonkaMobileUserGpsTrackAndYwyPaginatedList(KonkaMobileUserGpsTrack t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileUserGpsTrackAndYwyPaginatedList", t);
	}
}
