package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPhotoUploadTypeDeptDao;
import com.ebiz.mmt.domain.KonkaPhotoUploadTypeDept;
import com.ebiz.mmt.service.KonkaPhotoUploadTypeDeptService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-11 16:46:01
 */
@Service
public class KonkaPhotoUploadTypeDeptServiceImpl implements KonkaPhotoUploadTypeDeptService {

	@Resource
	private KonkaPhotoUploadTypeDeptDao konkaPhotoUploadTypeDeptDao;
	

	public Long createKonkaPhotoUploadTypeDept(KonkaPhotoUploadTypeDept t) {
		return this.konkaPhotoUploadTypeDeptDao.insertEntity(t);
	}

	public KonkaPhotoUploadTypeDept getKonkaPhotoUploadTypeDept(KonkaPhotoUploadTypeDept t) {
		return this.konkaPhotoUploadTypeDeptDao.selectEntity(t);
	}

	public Long getKonkaPhotoUploadTypeDeptCount(KonkaPhotoUploadTypeDept t) {
		return this.konkaPhotoUploadTypeDeptDao.selectEntityCount(t);
	}

	public List<KonkaPhotoUploadTypeDept> getKonkaPhotoUploadTypeDeptList(KonkaPhotoUploadTypeDept t) {
		return this.konkaPhotoUploadTypeDeptDao.selectEntityList(t);
	}

	public int modifyKonkaPhotoUploadTypeDept(KonkaPhotoUploadTypeDept t) {
		return this.konkaPhotoUploadTypeDeptDao.updateEntity(t);
	}

	public int removeKonkaPhotoUploadTypeDept(KonkaPhotoUploadTypeDept t) {
		return this.konkaPhotoUploadTypeDeptDao.deleteEntity(t);
	}

	public List<KonkaPhotoUploadTypeDept> getKonkaPhotoUploadTypeDeptPaginatedList(KonkaPhotoUploadTypeDept t) {
		return this.konkaPhotoUploadTypeDeptDao.selectEntityPaginatedList(t);
	}

}
