package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.AttachmentDao;
import com.ebiz.mmt.dao.GcxmAuditProcessDao;
import com.ebiz.mmt.dao.GcxmAuditProcessNodeDao;
import com.ebiz.mmt.dao.GcxmProjAuditDao;
import com.ebiz.mmt.dao.GcxmProjAuditNodeDao;
import com.ebiz.mmt.dao.GcxmProjOfferDao;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.GcxmAuditProcessNode;
import com.ebiz.mmt.domain.GcxmProjAudit;
import com.ebiz.mmt.domain.GcxmProjAuditNode;
import com.ebiz.mmt.domain.GcxmProjOffer;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.service.GcxmProjOfferService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-23 13:53:09
 */
@Service
public class GcxmProjOfferServiceImpl implements GcxmProjOfferService {

	@Resource
	private GcxmProjOfferDao gcxmProjOfferDao;

	@Resource
	private AttachmentDao attachmentDao;

	@Resource
	private GcxmProjAuditNodeDao gcxmProjAuditNodeDao;

	@Resource
	private GcxmAuditProcessNodeDao gcxmAuditProcessNodeDao;

	@Resource
	private GcxmProjAuditDao gcxmProjAuditDao;

	public Long createGcxmProjOffer(GcxmProjOffer t) {
		Long id = this.gcxmProjOfferDao.insertEntity(t);

		if (t.getAttachmentList() != null && t.getAttachmentList().size() > 0) {
			for (Attachment att : t.getAttachmentList()) {
				att.setLink_id(id);
				att.setLink_tab("GCXM_PROJ_OFFER");
				this.attachmentDao.insertEntity(att);
			}
		}

		String process_id = (String) t.getMap().get("process_id");
		GcxmAuditProcessNode gp = new GcxmAuditProcessNode();
		gp.setProcess_id(Long.valueOf(process_id));
		List<GcxmAuditProcessNode> gpList = this.gcxmAuditProcessNodeDao.selectEntityList(gp);

		GcxmProjAuditNode gn = new GcxmProjAuditNode();
		gn.setProj_id(Long.valueOf(id));
		gn.setProcess_id(Long.valueOf(process_id));
		gn.setAudit_role_name(gpList.get(0).getAudit_role_name());
		gn.setAudit_role_id(gpList.get(0).getAudit_role_id());
		gn.setAudit_type(1001L);
		gn.setNext_audit_role_id(gpList.get(0).getNext_audit_role_id());
		gn.setNext_audit_role_name(gpList.get(0).getNext_audit_role_name());
		gn.setPre_audit_role_id(gpList.get(0).getPre_audit_role_id());
		gn.setPre_audit_role_name(gpList.get(0).getPre_audit_role_name());
		this.gcxmProjAuditNodeDao.insertEntity(gn);

		return id;
	}

	public GcxmProjOffer getGcxmProjOffer(GcxmProjOffer t) {
		return this.gcxmProjOfferDao.selectEntity(t);
	}

	public Long getGcxmProjOfferCount(GcxmProjOffer t) {
		return this.gcxmProjOfferDao.selectEntityCount(t);
	}

	public List<GcxmProjOffer> getGcxmProjOfferList(GcxmProjOffer t) {
		return this.gcxmProjOfferDao.selectEntityList(t);
	}

	public int modifyGcxmProjOffer(GcxmProjOffer t) {
		if (t.getAttachmentList() != null && t.getAttachmentList().size() > 0) {
			for (Attachment att : t.getAttachmentList()) {
				att.setLink_id(t.getId());
				att.setLink_tab("GCXM_PROJ_OFFER");
				this.attachmentDao.insertEntity(att);
			}
		}

		return this.gcxmProjOfferDao.updateEntity(t);
	}

	public int removeGcxmProjOffer(GcxmProjOffer t) {
		return this.gcxmProjOfferDao.deleteEntity(t);
	}

	public List<GcxmProjOffer> getGcxmProjOfferPaginatedList(GcxmProjOffer t) {
		return this.gcxmProjOfferDao.selectEntityPaginatedList(t);
	}

	public int modifyGcxmProjOfferForCh(GcxmProjOffer t) {

		GcxmProjAuditNode gd = new GcxmProjAuditNode();
		gd.setProj_id(t.getId());
		gd = this.gcxmProjAuditNodeDao.selectEntity(gd);

		GcxmAuditProcessNode gp = new GcxmAuditProcessNode();
		gp.setProcess_id(gd.getProcess_id());
		List<GcxmAuditProcessNode> gpList = this.gcxmAuditProcessNodeDao.selectEntityList(gp);

		GcxmProjAuditNode gn = new GcxmProjAuditNode();
		gn.setId(gd.getId());
		gn.setProj_id(t.getId());
		gn.setProcess_id(gd.getProcess_id());
		gn.setAudit_role_name(gpList.get(0).getAudit_role_name());
		gn.setAudit_role_id(gpList.get(0).getAudit_role_id());
		gn.setAudit_type(1001L);
		gn.setNext_audit_role_id(gpList.get(0).getNext_audit_role_id());
		gn.setNext_audit_role_name(gpList.get(0).getNext_audit_role_name());
		gn.setPre_audit_role_id(gpList.get(0).getPre_audit_role_id());
		gn.setPre_audit_role_name(gpList.get(0).getPre_audit_role_name());
		this.gcxmProjAuditNodeDao.updateEntity(gn);

		PeProdUser user = (PeProdUser) t.getMap().get("peProdUser");

		GcxmProjAudit gt = new GcxmProjAudit();
		gt.setAudit_date(new Date());
		gt.setAudit_idea("撤回");
		gt.setAudit_type(1001L);
		gt.setIs_meet(-1L);
		gt.setAudit_result(-2);// 表示撤回
		gt.setAudit_user_id(new BigDecimal(user.getId()));
		gt.setProj_id(t.getId());
		this.gcxmProjAuditDao.insertEntity(gt);

		return this.gcxmProjOfferDao.updateEntity(t);

	}

}
