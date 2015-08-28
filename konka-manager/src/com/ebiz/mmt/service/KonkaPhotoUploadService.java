package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaPhotoUpload;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-11 16:38:19
 */
public interface KonkaPhotoUploadService {

	Long createKonkaPhotoUpload(KonkaPhotoUpload t);

	int modifyKonkaPhotoUpload(KonkaPhotoUpload t);

	int removeKonkaPhotoUpload(KonkaPhotoUpload t);

	KonkaPhotoUpload getKonkaPhotoUpload(KonkaPhotoUpload t);

	List<KonkaPhotoUpload> getKonkaPhotoUploadList(KonkaPhotoUpload t);

	Long getKonkaPhotoUploadCount(KonkaPhotoUpload t);

	List<KonkaPhotoUpload> getKonkaPhotoUploadPaginatedList(KonkaPhotoUpload t);

    Long getKonkaPhotoUploadLBCount(KonkaPhotoUpload v);
	
	List<KonkaPhotoUpload> getKonkaPhotoUploadLBPaginatedList(KonkaPhotoUpload v);
}