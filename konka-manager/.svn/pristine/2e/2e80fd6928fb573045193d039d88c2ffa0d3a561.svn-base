package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileAnnouncementDao;
import com.ebiz.mmt.domain.KonkaMobileAnnouncement;
import com.ebiz.mmt.service.KonkaMobileAnnouncementService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-04-19 15:44:46
 */
@Service
public class KonkaMobileAnnouncementServiceImpl implements KonkaMobileAnnouncementService {

	@Resource
	private KonkaMobileAnnouncementDao konkaMobileAnnouncementDao;
	

	public Long createKonkaMobileAnnouncement(KonkaMobileAnnouncement t) {
		return this.konkaMobileAnnouncementDao.insertEntity(t);
	}

	public KonkaMobileAnnouncement getKonkaMobileAnnouncement(KonkaMobileAnnouncement t) {
		return this.konkaMobileAnnouncementDao.selectEntity(t);
	}

	public Long getKonkaMobileAnnouncementCount(KonkaMobileAnnouncement t) {
		return this.konkaMobileAnnouncementDao.selectEntityCount(t);
	}

	public List<KonkaMobileAnnouncement> getKonkaMobileAnnouncementList(KonkaMobileAnnouncement t) {
		return this.konkaMobileAnnouncementDao.selectEntityList(t);
	}

	public int modifyKonkaMobileAnnouncement(KonkaMobileAnnouncement t) {
		return this.konkaMobileAnnouncementDao.updateEntity(t);
	}

	public int removeKonkaMobileAnnouncement(KonkaMobileAnnouncement t) {
		return this.konkaMobileAnnouncementDao.deleteEntity(t);
	}

	public List<KonkaMobileAnnouncement> getKonkaMobileAnnouncementPaginatedList(KonkaMobileAnnouncement t) {
		return this.konkaMobileAnnouncementDao.selectEntityPaginatedList(t);
	}

}
