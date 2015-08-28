package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPhotoUploadDao;
import com.ebiz.mmt.domain.KonkaPhotoUpload;
import com.ebiz.mmt.service.KonkaPhotoUploadService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-11 16:38:19
 */
@Service
public class KonkaPhotoUploadServiceImpl implements KonkaPhotoUploadService {

	@Resource
	private KonkaPhotoUploadDao konkaPhotoUploadDao;
	

	public Long createKonkaPhotoUpload(KonkaPhotoUpload t) {
		return this.konkaPhotoUploadDao.insertEntity(t);
	}

	public KonkaPhotoUpload getKonkaPhotoUpload(KonkaPhotoUpload t) {
		return this.konkaPhotoUploadDao.selectEntity(t);
	}

	public Long getKonkaPhotoUploadCount(KonkaPhotoUpload t) {
		return this.konkaPhotoUploadDao.selectEntityCount(t);
	}

	public List<KonkaPhotoUpload> getKonkaPhotoUploadList(KonkaPhotoUpload t) {
		return this.konkaPhotoUploadDao.selectEntityList(t);
	}

	public int modifyKonkaPhotoUpload(KonkaPhotoUpload t) {
		return this.konkaPhotoUploadDao.updateEntity(t);
	}

	public int removeKonkaPhotoUpload(KonkaPhotoUpload t) {
		return this.konkaPhotoUploadDao.deleteEntity(t);
	}

	public List<KonkaPhotoUpload> getKonkaPhotoUploadPaginatedList(KonkaPhotoUpload t) {
		return this.konkaPhotoUploadDao.selectEntityPaginatedList(t);
	}

	@Override
	public Long getKonkaPhotoUploadLBCount(KonkaPhotoUpload v) {
		
		return this.konkaPhotoUploadDao.selectKonkaPhotoUploadLBCount(v);
	}

	@Override
	public List<KonkaPhotoUpload> getKonkaPhotoUploadLBPaginatedList(
			KonkaPhotoUpload v) {
		
		return this.konkaPhotoUploadDao.selectKonkaPhotoUploadLBPaginatedList(v);
	}

}
