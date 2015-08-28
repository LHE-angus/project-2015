package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPeAttachmentsDao;
import com.ebiz.mmt.dao.KonkaXxZmdAuditUserHisDao;
import com.ebiz.mmt.dao.KonkaXxZmdDao;
import com.ebiz.mmt.dao.KonkaXxZmdGcysDao;
import com.ebiz.mmt.dao.KonkaXxZmdRewardSetDao;
import com.ebiz.mmt.dao.KonkaXxZmdStoreDao;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdAuditUserHis;
import com.ebiz.mmt.domain.KonkaXxZmdGcys;
import com.ebiz.mmt.domain.KonkaXxZmdRewardSet;
import com.ebiz.mmt.domain.KonkaXxZmdStore;
import com.ebiz.mmt.service.KonkaXxZmdService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2012-01-09 09:19:48
 */
@Service
public class KonkaXxZmdServiceImpl implements KonkaXxZmdService {

	@Resource
	private KonkaXxZmdDao konkaXxZmdDao;

	@Resource
	private KonkaXxZmdRewardSetDao konkaXxZmdRewardSetDao;

	@Resource
	private KonkaXxZmdStoreDao konkaXxZmdStoreDao;

	@Resource
	private KonkaXxZmdGcysDao konkaXxZmdGcysDao;

	@Resource
	private KonkaXxZmdAuditUserHisDao konkaXxZmdAuditUserHisDao;

	@Resource
	private KonkaPeAttachmentsDao konkaPeAttachmentsDao;

	public Long createKonkaXxZmd(KonkaXxZmd t) {
		Long id = this.konkaXxZmdDao.insertEntity(t);
		String store_ids = (String) t.getMap().get("store_ids");
		String[] ary = store_ids.split("#");
		for (int i = 0; i < ary.length; i++) {
			if (1 < ary[i].split("_").length) {
				String factory_id = ary[i].split("_")[0];
				String store_id = ary[i].split("_")[1];
				KonkaXxZmdStore konkaXxZmdStore = new KonkaXxZmdStore();
				konkaXxZmdStore.setFactory_id(factory_id);
				konkaXxZmdStore.setStore_id(store_id);
				konkaXxZmdStore.setZmd_id(id);
				this.konkaXxZmdStoreDao.insertEntity(konkaXxZmdStore);
			}
		}
		for (KonkaXxZmdRewardSet set : t.getKonkaXxZmdRewardSetList()) {
			set.setZmd_id(id);
			this.konkaXxZmdRewardSetDao.insertEntity(set);
		}

		// for (KonkaXxZmdRes konkaXxZmdRes : t.getKonkaXxZmdResList()) {
		// konkaXxZmdRes.setZmd_id(id);
		// this.konkaXxZmdResDao.insertEntity(konkaXxZmdRes);
		// }
		// 工程预算
		for (KonkaXxZmdGcys konkaXxZmdGcys : t.getKonkaXxZmdGcysList()) {
			konkaXxZmdGcys.setZmd_id(id);
			this.konkaXxZmdGcysDao.insertEntity(konkaXxZmdGcys);
		}

		// 附件
		List<KonkaPeAttachments> konkaPeAttachmentsList = t.getAttachmentList();
		if (null != konkaPeAttachmentsList) {
			for (KonkaPeAttachments konkaPeAttachments : konkaPeAttachmentsList) {
				konkaPeAttachments.setLink_id(id);
				this.konkaPeAttachmentsDao.insertEntity(konkaPeAttachments);
			}
		}

		// 插入历史记录表
		KonkaXxZmdAuditUserHis kHis = t.getKonkaXxZmdAuditUserHis();
		if (null != kHis) {
			kHis.setZmd_user_id(t.getZmd_user_id());
			this.konkaXxZmdAuditUserHisDao.insertEntity(kHis);
		}

		return id;
	}

	public KonkaXxZmd getKonkaXxZmd(KonkaXxZmd t) {
		return this.konkaXxZmdDao.selectEntity(t);
	}

	public Long getKonkaXxZmdCount(KonkaXxZmd t) {
		return this.konkaXxZmdDao.selectEntityCount(t);
	}

	public List<KonkaXxZmd> getKonkaXxZmdList(KonkaXxZmd t) {
		return this.konkaXxZmdDao.selectEntityList(t);
	}

	/**
	 * @author Zheng,Kun
	 * @version 2012-01-12
	 */
	public int modifyKonkaXxZmd(KonkaXxZmd t) {
		KonkaXxZmdStore konkaXxZmdStore = new KonkaXxZmdStore();
		konkaXxZmdStore.setZmd_id(t.getZmd_id());
		String Verification = (String) t.getMap().get("Verification");
		if (Verification.equals("0")) {
			this.konkaXxZmdStoreDao.deleteEntity(konkaXxZmdStore);
		}
		String store_ids = (String) t.getMap().get("store_ids");
		if (store_ids != null) {
			String[] ary = store_ids.split("#");
			for (int i = 0; i < ary.length; i++) {
				String factory_id = ary[i].split("_")[0];
				String store_id = ary[i].split("_")[1];

				konkaXxZmdStore.setFactory_id(factory_id);
				konkaXxZmdStore.setStore_id(store_id);
				this.konkaXxZmdStoreDao.insertEntity(konkaXxZmdStore);
			}
		}
		// // 更新资源
		// if (t.getMap().get("is_add").equals("-1")) {
		// for (KonkaXxZmdRes konkaXxZmdRes : t.getKonkaXxZmdResList()) {
		// konkaXxZmdRes.setZmd_id(t.getZmd_id());
		// this.konkaXxZmdResDao.updateEntity(konkaXxZmdRes);
		// }
		// } else if (t.getMap().get("is_add").equals("1")) {
		// for (KonkaXxZmdRes konkaXxZmdRes : t.getKonkaXxZmdResList()) {
		// konkaXxZmdRes.setZmd_id(t.getZmd_id());
		// this.konkaXxZmdResDao.insertEntity(konkaXxZmdRes);
		// }
		// }

		// KonkaXxZmdRes konkaXxZmdRes = new KonkaXxZmdRes();
		// konkaXxZmdRes.setZmd_id(t.getZmd_id());
		// this.konkaXxZmdResDao.deleteEntity(konkaXxZmdRes);
		//		
		// for (KonkaXxZmdRes temp : t.getKonkaXxZmdResList()) {
		// temp.setZmd_id(t.getZmd_id());
		// this.konkaXxZmdResDao.insertEntity(konkaXxZmdRes);
		// }

		List<KonkaPeAttachments> konkaPeAttachmentsList = t.getAttachmentList();
		if (null != konkaPeAttachmentsList) {
			for (KonkaPeAttachments konkaPeAttachments : konkaPeAttachmentsList) {
				konkaPeAttachments.setLink_id(t.getZmd_id());
				this.konkaPeAttachmentsDao.insertEntity(konkaPeAttachments);
			}
		}

		this.konkaXxZmdDao.updateEntity(t);

		// KonkaXxZmd zmd = new KonkaXxZmd();
		// zmd.setZmd_id(t.getZmd_id());
		// zmd.setIs_del(0);
		// zmd = this.konkaXxZmdDao.selectEntity(zmd);

		String is_audit = (String) t.getMap().get("is_audit");
		if ("0".equals(is_audit)) { // 审核
			// msgService.messageToRemindTrigger("auditZmd", t.getZmd_id());
		} else { // 修改
			if (null != t.getKonkaXxZmdGcysList()) {

				KonkaXxZmdGcys entity = new KonkaXxZmdGcys();
				entity.setZmd_id(t.getZmd_id());
				entity.getMap().put("del_zmd_gcys", true);
				this.konkaXxZmdGcysDao.deleteEntity(entity);

				for (KonkaXxZmdGcys konkaXxZmdGcys : t.getKonkaXxZmdGcysList()) {
					konkaXxZmdGcys.setZmd_id(t.getZmd_id());
					this.konkaXxZmdGcysDao.insertEntity(konkaXxZmdGcys);
				}
			}
			// if (null != zmd.getArc_state() && !"20300".equals(zmd.getArc_state().toString())) {// 已备案通过的专卖店修改，不发送消息
			// msgService.messageToRemindTrigger("applyZmd", t.getZmd_id());
			// }
		}
		return 0;
	}

	public int removeKonkaXxZmd(KonkaXxZmd t) {
		return this.konkaXxZmdDao.deleteEntity(t);
	}

	public List<KonkaXxZmd> getKonkaXxZmdPaginatedList(KonkaXxZmd t) {
		return this.konkaXxZmdDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2012-01-09
	 */
	public List<KonkaXxZmd> getKonkaXxZmdPaginatedListIncludeRelevanceInfo(KonkaXxZmd t) {
		return this.konkaXxZmdDao.selectKonkaXxZmdPaginatedListIncludeRelevanceInfo(t);
	}

	/**
	 * @author Zheng,Kun
	 * @version 2012-01-10
	 */
	public List<KonkaXxZmd> getKonkaXxZmdForClerkPaginatedList(KonkaXxZmd t) {
		return this.konkaXxZmdDao.selectKonkaXxZmdForClerkPaginatedList(t);
	}

	/**
	 * @author Zheng,Kun
	 * @version 2012-01-10
	 */
	public Long getKonkaXxZmdForClerkCount(KonkaXxZmd t) {
		return this.konkaXxZmdDao.selectKonkaXxZmdForClerkCount(t);
	}

	/**
	 * @author Ren,zhong
	 * @version 2012-04-05
	 */
	public int modifyKonkaXxZmdForPos(KonkaXxZmd t) {
		return this.konkaXxZmdDao.updateEntity(t);
	}

	public int deleteKonkaXxZmdForPosAllocation(KonkaXxZmd t) {
		return this.konkaXxZmdDao.deleteKonkaXxZmdForPosAllocation(t);
	}

	/**
	 * @author Ren,zhong
	 * @version 2012-04-06
	 */
	public int modifyKonkaXxZmdForCreditLine(KonkaXxZmd t) {
		return this.konkaXxZmdDao.updateKonkaXxZmdForCreditLine(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-06-19
	 */
	public Long getKonkaXxZmdForRoleCount(KonkaXxZmd t) {
		return this.konkaXxZmdDao.selectKonkaXxZmdForRoleCount(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-06-19
	 */
	public List<KonkaXxZmd> getKonkaXxZmdForRolePaginatedList(KonkaXxZmd t) {
		return this.konkaXxZmdDao.selectKonkaXxZmdForRolePaginatedList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-07-02
	 */
	public void modifyKonkaXxZmdForHis(KonkaXxZmd t) {
		this.konkaXxZmdDao.updateEntity(t);

		//历史记录表
		KonkaXxZmdAuditUserHis entity = t.getKonkaXxZmdAuditUserHis();
		this.konkaXxZmdAuditUserHisDao.insertEntity(entity);
	}
	
	/**
	 * @author Hu,Hao
	 * @version 2013-08-04
	 */
	public List<KonkaXxZmd> getKonkaXxZmdForRoleIdList(KonkaXxZmd t){
		return this.konkaXxZmdDao.selectKonkaXxZmdForRoleIdList(t);
	}
}
