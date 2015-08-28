package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaMobileUserGpsTrack;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-05 15:52:02
 */
public interface KonkaMobileUserGpsTrackDao extends EntityDao<KonkaMobileUserGpsTrack> {

	/**
	 * @author Wu,ShangLong
	 * @version 2013-07-12
	 */
	Long selectKonkaMobileUserGpsTrackAndYwyCount(KonkaMobileUserGpsTrack t);

	List<KonkaMobileUserGpsTrack> selectKonkaMobileUserGpsTrackAndYwyPaginatedList(KonkaMobileUserGpsTrack t);
}
