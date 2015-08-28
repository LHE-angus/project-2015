package com.ebiz.mmt.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPhotoUploadTypeDao;
import com.ebiz.mmt.domain.KonkaPhotoUploadType;
import com.ebiz.mmt.service.KonkaPhotoUploadTypeService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-11 16:10:29
 */
@Service
public class KonkaPhotoUploadTypeServiceImpl implements KonkaPhotoUploadTypeService {

	@Resource
	private KonkaPhotoUploadTypeDao konkaPhotoUploadTypeDao;
	

	public Long createKonkaPhotoUploadType(KonkaPhotoUploadType t) {
		return this.konkaPhotoUploadTypeDao.insertEntity(t);
	}

	public KonkaPhotoUploadType getKonkaPhotoUploadType(KonkaPhotoUploadType t) {
		return this.konkaPhotoUploadTypeDao.selectEntity(t);
	}

	public Long getKonkaPhotoUploadTypeCount(KonkaPhotoUploadType t) {
		return this.konkaPhotoUploadTypeDao.selectEntityCount(t);
	}

	public List<KonkaPhotoUploadType> getKonkaPhotoUploadTypeList(KonkaPhotoUploadType t) {
		return this.konkaPhotoUploadTypeDao.selectEntityList(t);
	}

	public int modifyKonkaPhotoUploadType(KonkaPhotoUploadType t) {
		return this.konkaPhotoUploadTypeDao.updateEntity(t);
	}

	public int removeKonkaPhotoUploadType(KonkaPhotoUploadType t) {
		return this.konkaPhotoUploadTypeDao.deleteEntity(t);
	}

	public List<KonkaPhotoUploadType> getKonkaPhotoUploadTypePaginatedList(KonkaPhotoUploadType t) {
		return this.konkaPhotoUploadTypeDao.selectEntityPaginatedList(t);
	}
	
	//分公司查询
	public List<KonkaPhotoUploadType> getKonkaPhotoUploadTypeForDeptPaginatedList(KonkaPhotoUploadType t) {
		return this.konkaPhotoUploadTypeDao.selectKonkaPhotoUploadTypeForDeptPaginatedList(t);
	}
	
	public KonkaPhotoUploadType getKonkaPhotoUploadTypeForDept(KonkaPhotoUploadType t) {
		return this.konkaPhotoUploadTypeDao.selectKonkaPhotoUploadTypeForDept(t);
	}
	/**
	 * 查找当前人的可视类型
	 */
	@Override
	public List<Map<String, String>> getVisiblePhotoType(KonkaPhotoUploadType v) {
		return this.konkaPhotoUploadTypeDao.selectVisiblePhotoType(v);
	}

	@Override
	public Long getKonkaPhotoUploadTypeForDeptPaginatedListCount(
			KonkaPhotoUploadType v) {
		return this.konkaPhotoUploadTypeDao.selectKonkaPhotoUploadTypeForDeptPaginatedListCount(v);
	}

}
