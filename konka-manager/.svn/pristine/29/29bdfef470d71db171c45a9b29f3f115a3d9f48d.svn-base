package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.GcxmAuditProcessNodeDao;
import com.ebiz.mmt.dao.GcxmProjAuditDao;
import com.ebiz.mmt.dao.GcxmProjAuditNodeDao;
import com.ebiz.mmt.dao.GcxmProjDao;
import com.ebiz.mmt.dao.GcxmProjOfferDao;
import com.ebiz.mmt.dao.GcxmProjTjDao;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.GcxmAuditProcessNode;
import com.ebiz.mmt.domain.GcxmProj;
import com.ebiz.mmt.domain.GcxmProjAudit;
import com.ebiz.mmt.domain.GcxmProjAuditNode;
import com.ebiz.mmt.domain.GcxmProjOffer;
import com.ebiz.mmt.domain.GcxmProjTj;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.service.GcxmProjAuditService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-23 13:53:09
 */
@Service
public class GcxmProjAuditServiceImpl implements GcxmProjAuditService {

	@Resource
	private GcxmProjAuditDao gcxmProjAuditDao;

	@Resource
	private GcxmProjDao gcxmProjDao;

	@Resource
	private GcxmProjTjDao gcxmProjTjDao;

	@Resource
	private GcxmProjOfferDao gcxmProjOfferDao;

	@Resource
	private GcxmProjAuditNodeDao gcxmProjAuditNodeDao;

	@Resource
	private GcxmAuditProcessNodeDao gcxmAuditProcessNodeDao;

	public Long createGcxmProjOfferAudit(GcxmProjAudit t) {
		GcxmProjAuditNode pn = new GcxmProjAuditNode();
		pn.setProj_id(t.getProj_id());
		pn = this.gcxmProjAuditNodeDao.selectEntity(pn);// null 30 38
		if (t.getAudit_result().intValue() == 1) {// 审核通过
			if (pn.getNext_audit_role_id() == null) {
				GcxmProjOffer gj = new GcxmProjOffer();
				gj.setId(t.getProj_id());
				gj.setInfo_state(1L);
				this.gcxmProjOfferDao.updateEntity(gj);

				GcxmProjAuditNode pn1 = new GcxmProjAuditNode();
				pn1.setAudit_name(t.getAudit_model());
				pn1.setAudit_user_id(t.getAudit_user_id().longValue());
				pn1.setId(pn.getId());
				this.gcxmProjAuditNodeDao.updateEntity(pn1);
			} else if (pn.getNext_audit_role_id() != null) {
				GcxmAuditProcessNode ga = new GcxmAuditProcessNode();
				ga.setProcess_id(pn.getProcess_id());
				ga.setAudit_role_id(pn.getNext_audit_role_id());
				ga = this.gcxmAuditProcessNodeDao.selectEntity(ga);

				GcxmProjAuditNode pn1 = new GcxmProjAuditNode();
				pn1.setPre_audit_role_id(ga.getPre_audit_role_id() == null ? null : ga.getPre_audit_role_id());
				pn1.setPre_audit_role_name(ga.getPre_audit_role_name() == null ? null : ga.getPre_audit_role_name());
				pn1.setAudit_role_id(ga.getAudit_role_id());
				pn1.setAudit_role_name(ga.getAudit_role_name());
				pn1.setNext_audit_role_id(ga.getNext_audit_role_id() == null ? null : ga.getNext_audit_role_id());
				pn1.setNext_audit_role_name(ga.getNext_audit_role_name() == null ? null : ga.getNext_audit_role_name());
				pn1.setId(pn.getId());
				this.gcxmProjAuditNodeDao.updateEntity(pn1);
			}

		} else if (t.getAudit_result().intValue() == -1) {// 驳回
			String node_id = (String) t.getMap().get("node_id");
			GcxmAuditProcessNode pn1 = new GcxmAuditProcessNode();
			pn1.setId(Long.valueOf(node_id));
			pn1 = this.gcxmAuditProcessNodeDao.selectEntity(pn1);

			GcxmProjAuditNode pn2 = new GcxmProjAuditNode();
			pn2.setPre_audit_role_id(pn1.getPre_audit_role_id() == null ? null : pn1.getPre_audit_role_id());
			pn2.setPre_audit_role_name(pn1.getPre_audit_role_name() == null ? null : pn1.getPre_audit_role_name());
			pn2.setAudit_role_id(pn1.getAudit_role_id());
			pn2.setAudit_role_name(pn1.getAudit_role_name());
			pn2.setNext_audit_role_id(pn1.getNext_audit_role_id() == null ? null : pn1.getNext_audit_role_id());
			pn2.setNext_audit_role_name(pn1.getNext_audit_role_name() == null ? null : pn1.getNext_audit_role_name());
			pn2.setId(pn.getId());
			this.gcxmProjAuditNodeDao.updateEntity(pn2);

		} else if (t.getAudit_result().intValue() == -3) {// 驳回至制单人

			GcxmProjOffer pro = new GcxmProjOffer();
			pro.setId(t.getProj_id());
			pro.setInfo_state(-1L);
			this.gcxmProjOfferDao.updateEntity(pro);

			GcxmProjAuditNode gd = new GcxmProjAuditNode();
			gd.setProj_id(t.getProj_id());
			gd = this.gcxmProjAuditNodeDao.selectEntity(gd);

			GcxmAuditProcessNode gp = new GcxmAuditProcessNode();
			gp.setProcess_id(gd.getProcess_id());
			List<GcxmAuditProcessNode> gpList = this.gcxmAuditProcessNodeDao.selectEntityList(gp);

			GcxmProjAuditNode gn = new GcxmProjAuditNode();
			gn.setId(gd.getId());
			gn.setProj_id(t.getProj_id());
			gn.setProcess_id(gd.getProcess_id());
			gn.setAudit_role_name(gpList.get(0).getAudit_role_name());
			gn.setAudit_role_id(gpList.get(0).getAudit_role_id());
			gn.setAudit_type(1001L);
			gn.setNext_audit_role_id(gpList.get(0).getNext_audit_role_id());
			gn.setNext_audit_role_name(gpList.get(0).getNext_audit_role_name());
			gn.setPre_audit_role_id(gpList.get(0).getPre_audit_role_id());
			gn.setPre_audit_role_name(gpList.get(0).getPre_audit_role_name());
			this.gcxmProjAuditNodeDao.updateEntity(gn);

		}

		return this.gcxmProjAuditDao.insertEntity(t);
	}

	public Long createGcxmProjAudit(GcxmProjAudit t) {

		String is_tj = (String) t.getMap().get("is_tj");
		if (is_tj != null && is_tj.equals("1")) {
			String model_1 = (String) t.getMap().get("model_1");
			String model_2 = (String) t.getMap().get("model_2");
			String model_3 = (String) t.getMap().get("model_3");
			List<Attachment> attachment1List = (List<Attachment>) t.getMap().get("filesAttachment1List");
			List<Attachment> attachment2List = (List<Attachment>) t.getMap().get("filesAttachment2List");
			List<Attachment> attachment3List = (List<Attachment>) t.getMap().get("filesAttachment3List");

			if (model_1 != null && !model_1.equals("")) {
				GcxmProjTj gt = new GcxmProjTj();
				gt.setProj_id(t.getProj_id().toString());
				gt.setProj_code(t.getProj_code());
				gt.setMemo("1");
				gt.setModel(model_1);
				if (attachment1List != null && attachment1List.size() > 0) {
					Attachment at = new Attachment();
					at = attachment1List.get(0);
					gt.setFj_name(at.getFile_name());
					gt.setFj_url(at.getSave_path());
				}
				this.gcxmProjTjDao.insertEntity(gt);

			}
			if (model_2 != null && !model_2.equals("")) {
				GcxmProjTj gt = new GcxmProjTj();
				gt.setProj_id(t.getProj_id().toString());
				gt.setProj_code(t.getProj_code());
				gt.setModel(model_2);
				gt.setMemo("2");
				if (attachment2List != null && attachment2List.size() > 0) {
					Attachment at = new Attachment();
					at = attachment2List.get(0);
					gt.setFj_name(at.getFile_name());
					gt.setFj_url(at.getSave_path());
				}
				this.gcxmProjTjDao.insertEntity(gt);
			}
			if (model_3 != null && !model_3.equals("")) {
				GcxmProjTj gt = new GcxmProjTj();
				gt.setProj_id(t.getProj_id().toString());
				gt.setProj_code(t.getProj_code());
				gt.setMemo("3");
				gt.setModel(model_3);
				if (attachment3List != null && attachment3List.size() > 0) {
					Attachment at = new Attachment();
					at = attachment3List.get(0);
					gt.setFj_name(at.getFile_name());
					gt.setFj_url(at.getSave_path());
				}
				this.gcxmProjTjDao.insertEntity(gt);

			}

		}

		GcxmProjAuditNode pn = new GcxmProjAuditNode();
		pn.setProj_id(t.getProj_id());
		pn = this.gcxmProjAuditNodeDao.selectEntity(pn);// null 30 38
		if (t.getAudit_result().intValue() == 1) {// 审核通过
			if (pn.getNext_audit_role_id() == null) {
				GcxmProj gj = new GcxmProj();
				gj.setId(t.getProj_id());
				gj.setInfo_state(1);
				this.gcxmProjDao.updateEntity(gj);

				GcxmProjAuditNode pn1 = new GcxmProjAuditNode();
				pn1.setAudit_name(t.getAudit_model());
				pn1.setAudit_user_id(t.getAudit_user_id().longValue());
				pn1.setId(pn.getId());
				this.gcxmProjAuditNodeDao.updateEntity(pn1);
			} else if (pn.getNext_audit_role_id() != null) {
				GcxmAuditProcessNode ga = new GcxmAuditProcessNode();
				ga.setProcess_id(pn.getProcess_id());
				ga.setAudit_role_id(pn.getNext_audit_role_id());
				ga = this.gcxmAuditProcessNodeDao.selectEntity(ga);

				GcxmProjAuditNode pn1 = new GcxmProjAuditNode();
				pn1.setPre_audit_role_id(ga.getPre_audit_role_id() == null ? null : ga.getPre_audit_role_id());
				pn1.setPre_audit_role_name(ga.getPre_audit_role_name() == null ? null : ga.getPre_audit_role_name());
				pn1.setAudit_role_id(ga.getAudit_role_id());
				pn1.setAudit_role_name(ga.getAudit_role_name());
				pn1.setNext_audit_role_id(ga.getNext_audit_role_id() == null ? null : ga.getNext_audit_role_id());
				pn1.setNext_audit_role_name(ga.getNext_audit_role_name() == null ? null : ga.getNext_audit_role_name());
				pn1.setId(pn.getId());
				this.gcxmProjAuditNodeDao.updateEntity(pn1);
			}

		} else if (t.getAudit_result().intValue() == -1) {// 驳回
			String node_id = (String) t.getMap().get("node_id");
			GcxmAuditProcessNode pn1 = new GcxmAuditProcessNode();
			pn1.setId(Long.valueOf(node_id));
			pn1 = this.gcxmAuditProcessNodeDao.selectEntity(pn1);

			GcxmProjAuditNode pn2 = new GcxmProjAuditNode();
			pn2.setPre_audit_role_id(pn1.getPre_audit_role_id() == null ? null : pn1.getPre_audit_role_id());
			pn2.setPre_audit_role_name(pn1.getPre_audit_role_name() == null ? null : pn1.getPre_audit_role_name());
			pn2.setAudit_role_id(pn1.getAudit_role_id());
			pn2.setAudit_role_name(pn1.getAudit_role_name());
			pn2.setNext_audit_role_id(pn1.getNext_audit_role_id() == null ? null : pn1.getNext_audit_role_id());
			pn2.setNext_audit_role_name(pn1.getNext_audit_role_name() == null ? null : pn1.getNext_audit_role_name());
			pn2.setId(pn.getId());
			this.gcxmProjAuditNodeDao.updateEntity(pn2);

		} else if (t.getAudit_result().intValue() == -3) {// 驳回至制单人

			GcxmProj pro = new GcxmProj();
			pro.setId(t.getProj_id());
			pro.setInfo_state(-1);
			this.gcxmProjDao.updateEntity(pro);

			GcxmProjAuditNode gd = new GcxmProjAuditNode();
			gd.setProj_id(t.getProj_id());
			gd = this.gcxmProjAuditNodeDao.selectEntity(gd);

			GcxmAuditProcessNode gp = new GcxmAuditProcessNode();
			gp.setProcess_id(gd.getProcess_id());
			List<GcxmAuditProcessNode> gpList = this.gcxmAuditProcessNodeDao.selectEntityList(gp);

			GcxmProjAuditNode gn = new GcxmProjAuditNode();
			gn.setId(gd.getId());
			gn.setProj_id(t.getProj_id());
			gn.setProcess_id(gd.getProcess_id());
			gn.setAudit_role_name(gpList.get(0).getAudit_role_name());
			gn.setAudit_role_id(gpList.get(0).getAudit_role_id());
			gn.setAudit_type(1001L);
			gn.setNext_audit_role_id(gpList.get(0).getNext_audit_role_id());
			gn.setNext_audit_role_name(gpList.get(0).getNext_audit_role_name());
			gn.setPre_audit_role_id(gpList.get(0).getPre_audit_role_id());
			gn.setPre_audit_role_name(gpList.get(0).getPre_audit_role_name());
			this.gcxmProjAuditNodeDao.updateEntity(gn);

		}

		return this.gcxmProjAuditDao.insertEntity(t);
	}

	public GcxmProjAudit getGcxmProjAudit(GcxmProjAudit t) {
		return this.gcxmProjAuditDao.selectEntity(t);
	}

	public Long getGcxmProjAuditCount(GcxmProjAudit t) {
		return this.gcxmProjAuditDao.selectEntityCount(t);
	}

	public List<GcxmProjAudit> getGcxmProjAuditList(GcxmProjAudit t) {
		return this.gcxmProjAuditDao.selectEntityList(t);
	}

	public int modifyGcxmProjAudit(GcxmProjAudit t) {
		return this.gcxmProjAuditDao.updateEntity(t);
	}

	public int removeGcxmProjAudit(GcxmProjAudit t) {
		return this.gcxmProjAuditDao.deleteEntity(t);
	}

	public List<GcxmProjAudit> getGcxmProjAuditPaginatedList(GcxmProjAudit t) {
		return this.gcxmProjAuditDao.selectEntityPaginatedList(t);
	}

}
