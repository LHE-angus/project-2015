package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileUserGpsTrackDao;
import com.ebiz.mmt.domain.KonkaMobileUserGpsTrack;
import com.ebiz.mmt.service.KonkaMobileUserGpsTrackService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-05 15:52:02
 */
@Service
public class KonkaMobileUserGpsTrackServiceImpl implements KonkaMobileUserGpsTrackService {

	@Resource
	private KonkaMobileUserGpsTrackDao konkaMobileUserGpsTrackDao;

	public Long createKonkaMobileUserGpsTrack(KonkaMobileUserGpsTrack t) {
		return this.konkaMobileUserGpsTrackDao.insertEntity(t);
	}

	public KonkaMobileUserGpsTrack getKonkaMobileUserGpsTrack(KonkaMobileUserGpsTrack t) {
		return this.konkaMobileUserGpsTrackDao.selectEntity(t);
	}

	public Long getKonkaMobileUserGpsTrackCount(KonkaMobileUserGpsTrack t) {
		return this.konkaMobileUserGpsTrackDao.selectEntityCount(t);
	}

	public List<KonkaMobileUserGpsTrack> getKonkaMobileUserGpsTrackList(KonkaMobileUserGpsTrack t) {
		return this.konkaMobileUserGpsTrackDao.selectEntityList(t);
	}

	public int modifyKonkaMobileUserGpsTrack(KonkaMobileUserGpsTrack t) {
		return this.konkaMobileUserGpsTrackDao.updateEntity(t);
	}

	public int removeKonkaMobileUserGpsTrack(KonkaMobileUserGpsTrack t) {
		return this.konkaMobileUserGpsTrackDao.deleteEntity(t);
	}

	public List<KonkaMobileUserGpsTrack> getKonkaMobileUserGpsTrackPaginatedList(KonkaMobileUserGpsTrack t) {
		return this.konkaMobileUserGpsTrackDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2013-07-08
	 */
	public Long getKonkaMobileUserGpsTrackAndYwyCount(KonkaMobileUserGpsTrack t) {
		return this.konkaMobileUserGpsTrackDao.selectKonkaMobileUserGpsTrackAndYwyCount(t);
	}

	public List<KonkaMobileUserGpsTrack> getKonkaMobileUserGpsTrackAndYwyPaginatedList(KonkaMobileUserGpsTrack t) {
		return this.konkaMobileUserGpsTrackDao.selectKonkaMobileUserGpsTrackAndYwyPaginatedList(t);
	}

}
