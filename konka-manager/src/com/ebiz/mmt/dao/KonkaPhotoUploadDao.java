package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaPhotoUpload;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-11 16:38:19
 */
public interface KonkaPhotoUploadDao extends EntityDao<KonkaPhotoUpload> {
	
	Long selectKonkaPhotoUploadLBCount(KonkaPhotoUpload v);
	
	List<KonkaPhotoUpload> selectKonkaPhotoUploadLBPaginatedList(KonkaPhotoUpload v);
}
