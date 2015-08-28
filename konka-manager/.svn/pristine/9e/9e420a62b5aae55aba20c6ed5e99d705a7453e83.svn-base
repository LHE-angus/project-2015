package com.ebiz.mmt.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPhotoUploadTypeDao;
import com.ebiz.mmt.domain.KonkaPhotoUploadType;
import com.ebiz.mmt.domain.KonkaR3ShopLink;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-11 16:10:29
 */
@Service
public class KonkaPhotoUploadTypeDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaPhotoUploadType> implements KonkaPhotoUploadTypeDao {
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaPhotoUploadType> selectKonkaPhotoUploadTypeForDeptPaginatedList(
			KonkaPhotoUploadType t) {
		
		return this.getSqlMapClientTemplate().queryForList("selectKonkaPhotoUploadTypeForDeptPaginatedList", t);
	}
	
	public KonkaPhotoUploadType selectKonkaPhotoUploadTypeForDept(KonkaPhotoUploadType t) {
		return (KonkaPhotoUploadType) this.getSqlMapClientTemplate().queryForObject("selectKonkaPhotoUploadTypeForDept",t);
	}

	@Override
	public List<Map<String, String>> selectVisiblePhotoType(
			KonkaPhotoUploadType v) {
		return super.getSqlMapClientTemplate().queryForList("selectVisiblePhotoType", v);
	}

	@Override
	public Long selectKonkaPhotoUploadTypeForDeptPaginatedListCount(
			KonkaPhotoUploadType v) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaPhotoUploadTypeForDeptPaginatedListCount", v);
	}

}
