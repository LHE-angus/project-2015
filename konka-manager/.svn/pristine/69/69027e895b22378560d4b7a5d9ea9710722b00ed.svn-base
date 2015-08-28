package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPeAttachmentsDao;
import com.ebiz.mmt.dao.KonkaXxZmdAuditUserHisDao;
import com.ebiz.mmt.dao.KonkaXxZmdAuditUserInfoDao;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaXxZmdAuditUserHis;
import com.ebiz.mmt.domain.KonkaXxZmdAuditUserInfo;
import com.ebiz.mmt.service.KonkaXxZmdAuditUserInfoService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-03-13 11:03:11
 */
@Service
public class KonkaXxZmdAuditUserInfoServiceImpl implements KonkaXxZmdAuditUserInfoService {

	@Resource
	private KonkaXxZmdAuditUserInfoDao konkaXxZmdAuditUserInfoDao;

	@Resource
	private KonkaXxZmdAuditUserHisDao konkaXxZmdAuditUserHisDao;

	@Resource
	private KonkaPeAttachmentsDao konkaPeAttachmentsDao;

	public Long createKonkaXxZmdAuditUserInfo(KonkaXxZmdAuditUserInfo t) {
		return this.konkaXxZmdAuditUserInfoDao.insertEntity(t);
	}

	public KonkaXxZmdAuditUserInfo getKonkaXxZmdAuditUserInfo(KonkaXxZmdAuditUserInfo t) {
		return this.konkaXxZmdAuditUserInfoDao.selectEntity(t);
	}

	public Long getKonkaXxZmdAuditUserInfoCount(KonkaXxZmdAuditUserInfo t) {
		return this.konkaXxZmdAuditUserInfoDao.selectEntityCount(t);
	}

	public List<KonkaXxZmdAuditUserInfo> getKonkaXxZmdAuditUserInfoList(KonkaXxZmdAuditUserInfo t) {
		return this.konkaXxZmdAuditUserInfoDao.selectEntityList(t);
	}

	public int modifyKonkaXxZmdAuditUserInfo(KonkaXxZmdAuditUserInfo t) {
		return this.konkaXxZmdAuditUserInfoDao.updateEntity(t);
	}

	public int removeKonkaXxZmdAuditUserInfo(KonkaXxZmdAuditUserInfo t) {
		return this.konkaXxZmdAuditUserInfoDao.deleteEntity(t);
	}

	public List<KonkaXxZmdAuditUserInfo> getKonkaXxZmdAuditUserInfoPaginatedList(KonkaXxZmdAuditUserInfo t) {
		return this.konkaXxZmdAuditUserInfoDao.selectEntityPaginatedList(t);
	}

	public void modifyKonkaXxZmdAuditUserInfoAndHistory(KonkaXxZmdAuditUserInfo t) {
		this.konkaXxZmdAuditUserInfoDao.updateEntity(t);// 更新审核记录

		// 历史记录表
		KonkaXxZmdAuditUserHis entity = t.getKonkaXxZmdAuditUserHis();
		this.konkaXxZmdAuditUserHisDao.insertEntity(entity);

	}

	/**
	 * @author Hu,Hao
	 * @version 2013-04-24
	 */
	public Long getKonkaXxZmdAuditUserInfoAndZmdCount(KonkaXxZmdAuditUserInfo t) {
		return this.konkaXxZmdAuditUserInfoDao.selectKonkaXxZmdAuditUserInfoAndZmdCount(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-04-24
	 */
	public List<KonkaXxZmdAuditUserInfo> getKonkaXxZmdAuditUserInfoAndZmdPaginatedList(KonkaXxZmdAuditUserInfo t) {
		return this.konkaXxZmdAuditUserInfoDao.selectKonkaXxZmdAuditUserInfoAndZmdPaginatedList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-05-02
	 */
	public Long getKonkaXxZmdAndUserInfoCount(KonkaXxZmdAuditUserInfo t) {
		return this.konkaXxZmdAuditUserInfoDao.selectKonkaXxZmdAndUserInfoCount(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-05-02
	 */
	public List<KonkaXxZmdAuditUserInfo> getKonkaXxZmdAndUserInfoPaginatedList(KonkaXxZmdAuditUserInfo t) {
		return this.konkaXxZmdAuditUserInfoDao.selectKonkaXxZmdAndUserInfoPaginatedList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-07-24
	 */
	public void createKonkaXxZmdAuditUserInfoForFiles(KonkaXxZmdAuditUserInfo t) {
		Long id = this.konkaXxZmdAuditUserInfoDao.insertEntity(t);

		List<KonkaPeAttachments> konkaPeAttachmentsList = t.getAttachmentList();
		if (null != konkaPeAttachmentsList) {
			for (KonkaPeAttachments konkaPeAttachments : konkaPeAttachmentsList) {
				konkaPeAttachments.setLink_id(id);
				this.konkaPeAttachmentsDao.insertEntity(konkaPeAttachments);
			}
		}

		List<KonkaXxZmdAuditUserHis> konkaXxZmdAuditUserHisList = t.getKonkaXxZmdAuditUserHisList();
		if (null != konkaXxZmdAuditUserHisList) {
			for (KonkaXxZmdAuditUserHis KonkaXxZmdAuditUserHis : konkaXxZmdAuditUserHisList) {
				KonkaXxZmdAuditUserHis.setZmd_user_id(id);
				this.konkaXxZmdAuditUserHisDao.insertEntity(KonkaXxZmdAuditUserHis);
			}
		}
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-07-24
	 */
	public void modifyKonkaXxZmdAuditUserInfoForFiles(KonkaXxZmdAuditUserInfo t) {
		this.konkaXxZmdAuditUserInfoDao.updateEntity(t);

		List<KonkaPeAttachments> konkaPeAttachmentsList = t.getAttachmentList();
		if (null != konkaPeAttachmentsList) {
			for (KonkaPeAttachments konkaPeAttachments : konkaPeAttachmentsList) {
				konkaPeAttachments.setLink_id(t.getZmd_user_id());
				this.konkaPeAttachmentsDao.insertEntity(konkaPeAttachments);
			}
		}
	}

	/**
	 * @author Hu,hao
	 * @version 2013-08-04
	 */
	public List<KonkaXxZmdAuditUserInfo> getKonkaXxZmdAuditUserInfoForRoleIdList(KonkaXxZmdAuditUserInfo t) {
		return this.konkaXxZmdAuditUserInfoDao.selectKonkaXxZmdAuditUserInfoForRoleIdList(t);
	}

	/**
	 * @author Hu,hao
	 * @version 2013-09-06
	 */
	public Long getKonkaXxZmdAndUserInfoZmdForCount(KonkaXxZmdAuditUserInfo t) {
		return this.konkaXxZmdAuditUserInfoDao.selectKonkaXxZmdAndUserInfoZmdForCount(t);
	}
}
