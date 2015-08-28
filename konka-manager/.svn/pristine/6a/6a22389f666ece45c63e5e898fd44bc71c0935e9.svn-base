package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPhotoUploadDao;
import com.ebiz.mmt.domain.KonkaPhotoUpload;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-11 16:38:19
 */
@Service
public class KonkaPhotoUploadDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaPhotoUpload> implements KonkaPhotoUploadDao {

	@Override
	public Long selectKonkaPhotoUploadLBCount(KonkaPhotoUpload v) {
		 return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaPhotoUploadLBCount", v);
	}

	@Override
	public List<KonkaPhotoUpload> selectKonkaPhotoUploadLBPaginatedList(
			KonkaPhotoUpload v) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaPhotoUploadLBPaginatedList", v);
	}

}
