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
import com.ebiz.mmt.dao.GcxmProjDao;
import com.ebiz.mmt.dao.GcxmProjTjDao;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.GcxmAuditProcessNode;
import com.ebiz.mmt.domain.GcxmProj;
import com.ebiz.mmt.domain.GcxmProjAudit;
import com.ebiz.mmt.domain.GcxmProjAuditNode;
import com.ebiz.mmt.domain.GcxmProjTj;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.service.GcxmProjService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-23 13:53:09
 */
@Service
public class GcxmProjServiceImpl implements GcxmProjService {

	@Resource
	private GcxmProjDao gcxmProjDao;

	@Resource
	private AttachmentDao attachmentDao;

	@Resource
	private GcxmProjTjDao gcxmProjTjDao;

	@Resource
	private GcxmProjAuditNodeDao gcxmProjAuditNodeDao;

	@Resource
	private GcxmAuditProcessDao gcxmAuditProcessDao;

	@Resource
	private GcxmAuditProcessNodeDao gcxmAuditProcessNodeDao;

	@Resource
	private GcxmProjAuditDao gcxmProjAuditDao;

	public Long createGcxmProj(GcxmProj t) {
		Long id = this.gcxmProjDao.insertEntity(t);

		if (t.getAttachmentList() != null && t.getAttachmentList().size() > 0) {
			for (Attachment a : t.getAttachmentList()) {
				a.setLink_tab("GCXM_PROJ");
				a.setLink_id(id);
				this.attachmentDao.insertEntity(a);
			}
		}

		String model_1 = (String) t.getMap().get("model_1");
		String model_2 = (String) t.getMap().get("model_2");
		String model_3 = (String) t.getMap().get("model_3");

		if (model_1 != null && !model_1.equals("")) {
			GcxmProjTj gt = new GcxmProjTj();
			gt.setProj_id(id.toString());
			gt.setProj_code(t.getProj_code());
			gt.setMemo("1");
			gt.setModel(model_1);
			if (t.getAttachment1List() != null && t.getAttachment1List().size() > 0) {
				Attachment at = new Attachment();
				at = t.getAttachment1List().get(0);
				gt.setFj_name(at.getFile_name());
				gt.setFj_url(at.getSave_path());
			}
			this.gcxmProjTjDao.insertEntity(gt);

		}
		if (model_2 != null && !model_2.equals("")) {
			GcxmProjTj gt = new GcxmProjTj();
			gt.setProj_id(id.toString());
			gt.setProj_code(t.getProj_code());
			gt.setModel(model_2);
			gt.setMemo("2");
			if (t.getAttachment2List() != null && t.getAttachment2List().size() > 0) {
				Attachment at = new Attachment();
				at = t.getAttachment2List().get(0);
				gt.setFj_name(at.getFile_name());
				gt.setFj_url(at.getSave_path());
			}
			this.gcxmProjTjDao.insertEntity(gt);
		}
		if (model_3 != null && !model_3.equals("")) {
			GcxmProjTj gt = new GcxmProjTj();
			gt.setProj_id(id.toString());
			gt.setProj_code(t.getProj_code());
			gt.setMemo("3");
			gt.setModel(model_3);
			if (t.getAttachment3List() != null && t.getAttachment3List().size() > 0) {
				Attachment at = new Attachment();
				at = t.getAttachment3List().get(0);
				gt.setFj_name(at.getFile_name());
				gt.setFj_url(at.getSave_path());
			}
			this.gcxmProjTjDao.insertEntity(gt);

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

	public GcxmProj getGcxmProj(GcxmProj t) {
		return this.gcxmProjDao.selectEntity(t);
	}

	public Long getGcxmProjCount(GcxmProj t) {
		return this.gcxmProjDao.selectEntityCount(t);
	}

	public List<GcxmProj> getGcxmProjList(GcxmProj t) {
		return this.gcxmProjDao.selectEntityList(t);
	}

	public int modifyGcxmProjForFj(GcxmProj t) {
		if (t.getAttachmentList() != null && t.getAttachmentList().size() > 0) {
			for (Attachment a : t.getAttachmentList()) {
				a.setLink_tab("GCXM_PROJ");
				a.setLink_id(t.getId());
				this.attachmentDao.insertEntity(a);
			}
		}

		// GcxmProjTj gg = new GcxmProjTj();
		// gg.setProj_id(t.getId().toString());
		// List<GcxmProjTj> ggList = this.gcxmProjTjDao.selectEntityList(gg);
		// if (ggList != null && ggList.size() > 0) {
		// for (GcxmProjTj gcxmProjTj : ggList) {
		// this.gcxmProjTjDao.deleteEntity(gcxmProjTj);
		// }
		// }

		String model_1 = (String) t.getMap().get("model_1");
		String model_2 = (String) t.getMap().get("model_2");
		String model_3 = (String) t.getMap().get("model_3");

		if (model_1 != null && !model_1.equals("")) {
			GcxmProjTj gg = new GcxmProjTj();
			gg.setProj_id(t.getId().toString());
			gg.setMemo("1");
			gg = this.gcxmProjTjDao.selectEntity(gg);
			if (gg == null) {
				GcxmProjTj gt = new GcxmProjTj();
				gt.setProj_id(t.getId().toString());
				gt.setProj_code(t.getProj_code());
				gt.setMemo("1");
				gt.setModel(model_1);
				if (t.getAttachment1List() != null && t.getAttachment1List().size() > 0) {
					Attachment at = new Attachment();
					at = t.getAttachment1List().get(0);
					gt.setFj_name(at.getFile_name());
					gt.setFj_url(at.getSave_path());
				}
				this.gcxmProjTjDao.insertEntity(gt);
			} else {
				GcxmProjTj gt = new GcxmProjTj();
				gt.setProj_id(t.getId().toString());
				gt.setProj_code(t.getProj_code());
				gt.setMemo("1");
				gt.setModel(model_1);
				if (t.getAttachment1List() != null && t.getAttachment1List().size() > 0) {
					Attachment at = new Attachment();
					at = t.getAttachment1List().get(0);
					gt.setFj_name(at.getFile_name());
					gt.setFj_url(at.getSave_path());
				}
				gt.setId(gg.getId());
				this.gcxmProjTjDao.updateEntity(gt);
			}

		}
		if (model_2 != null && !model_2.equals("")) {

			GcxmProjTj gg = new GcxmProjTj();
			gg.setProj_id(t.getId().toString());
			gg.setMemo("2");
			gg = this.gcxmProjTjDao.selectEntity(gg);
			if (gg == null) {
				GcxmProjTj gt = new GcxmProjTj();
				gt.setProj_id(t.getId().toString());
				gt.setProj_code(t.getProj_code());
				gt.setMemo("2");
				gt.setModel(model_2);
				if (t.getAttachment2List() != null && t.getAttachment2List().size() > 0) {
					Attachment at = new Attachment();
					at = t.getAttachment2List().get(0);
					gt.setFj_name(at.getFile_name());
					gt.setFj_url(at.getSave_path());
				}
				this.gcxmProjTjDao.insertEntity(gt);
			} else {
				GcxmProjTj gt = new GcxmProjTj();
				gt.setProj_id(t.getId().toString());
				gt.setProj_code(t.getProj_code());
				gt.setMemo("2");
				gt.setModel(model_2);
				if (t.getAttachment2List() != null && t.getAttachment2List().size() > 0) {
					Attachment at = new Attachment();
					at = t.getAttachment2List().get(0);
					gt.setFj_name(at.getFile_name());
					gt.setFj_url(at.getSave_path());
				}
				gt.setId(gg.getId());
				this.gcxmProjTjDao.updateEntity(gt);
			}

		}
		if (model_3 != null && !model_3.equals("")) {

			GcxmProjTj gg = new GcxmProjTj();
			gg.setProj_id(t.getId().toString());
			gg.setMemo("3");
			gg = this.gcxmProjTjDao.selectEntity(gg);
			if (gg == null) {
				GcxmProjTj gt = new GcxmProjTj();
				gt.setProj_id(t.getId().toString());
				gt.setProj_code(t.getProj_code());
				gt.setMemo("3");
				gt.setModel(model_3);
				if (t.getAttachment3List() != null && t.getAttachment3List().size() > 0) {
					Attachment at = new Attachment();
					at = t.getAttachment3List().get(0);
					gt.setFj_name(at.getFile_name());
					gt.setFj_url(at.getSave_path());
				}
				this.gcxmProjTjDao.insertEntity(gt);
			} else {
				GcxmProjTj gt = new GcxmProjTj();
				gt.setProj_id(t.getId().toString());
				gt.setProj_code(t.getProj_code());
				gt.setMemo("3");
				gt.setModel(model_3);
				if (t.getAttachment3List() != null && t.getAttachment3List().size() > 0) {
					Attachment at = new Attachment();
					at = t.getAttachment3List().get(0);
					gt.setFj_name(at.getFile_name());
					gt.setFj_url(at.getSave_path());
				}
				gt.setId(gg.getId());
				this.gcxmProjTjDao.updateEntity(gt);
			}

		}

		return this.gcxmProjDao.updateEntity(t);
	}

	public int removeGcxmProj(GcxmProj t) {
		return this.gcxmProjDao.deleteEntity(t);
	}

	public List<GcxmProj> getGcxmProjPaginatedList(GcxmProj t) {
		return this.gcxmProjDao.selectEntityPaginatedList(t);
	}

	@Override
	public int modifyGcxmProj(GcxmProj t) {
		return this.gcxmProjDao.updateEntity(t);
	}

	@Override
	public int modifyGcxmProjForCh(GcxmProj t) {

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

		return this.gcxmProjDao.updateEntity(t);
	}

	public Long getGcxmProjForUnionCount(GcxmProj t) {
		return this.gcxmProjDao.selectGcxmProjForUnionCount(t);
	}

	public List<GcxmProj> getGcxmProjForUnionPaginatedList(GcxmProj t) {
		return this.gcxmProjDao.selectGcxmProjForUnionPaginatedList(t);
	}

}
