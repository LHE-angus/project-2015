package com.ebiz.mmt.service;

import java.util.List;
import java.util.Map;

import com.ebiz.mmt.domain.KonkaPhotoUploadType;
import com.ebiz.mmt.domain.KonkaR3ShopLink;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-11 16:10:29
 */
public interface KonkaPhotoUploadTypeService {

	Long createKonkaPhotoUploadType(KonkaPhotoUploadType t);

	int modifyKonkaPhotoUploadType(KonkaPhotoUploadType t);

	int removeKonkaPhotoUploadType(KonkaPhotoUploadType t);

	KonkaPhotoUploadType getKonkaPhotoUploadType(KonkaPhotoUploadType t);

	List<KonkaPhotoUploadType> getKonkaPhotoUploadTypeList(KonkaPhotoUploadType t);

	Long getKonkaPhotoUploadTypeCount(KonkaPhotoUploadType t);

	List<KonkaPhotoUploadType> getKonkaPhotoUploadTypePaginatedList(KonkaPhotoUploadType t);
	
	List<KonkaPhotoUploadType> getKonkaPhotoUploadTypeForDeptPaginatedList(KonkaPhotoUploadType t);
	
	KonkaPhotoUploadType getKonkaPhotoUploadTypeForDept(KonkaPhotoUploadType t);
	
	 List<Map<String, String>> getVisiblePhotoType(KonkaPhotoUploadType v);
	 
	 Long getKonkaPhotoUploadTypeForDeptPaginatedListCount(KonkaPhotoUploadType v);

}