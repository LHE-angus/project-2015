package com.ebiz.mmt.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaVipPdManage;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.org.apache.commons.lang3.StringUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class KonkaVipPdManageAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// 设置导航信息（当前位置）
		setNaviStringToRequestScope(form, request);
		DynaBean dynabean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynabean, request);
		String mod_id = (String) dynabean.get("mod_id");
		Pager pager = (Pager) dynabean.get("pager");
		pager.setPageSize(10);
		String fgs_id = (String) dynabean.get("dept_id");
		String pd_code = (String) dynabean.get("pd_code");
		String pd_type = (String) dynabean.get("pd_type");
		String is_locked = (String) dynabean.get("is_locked");
		String is_hidden = (String) dynabean.get("is_hidden");

		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		// 拿到分公司
		List<KonkaDept> deptlist = super.getDeptInfoListWithOutLimit(mapping, form, request, response);

		boolean role_fgs = true; // 是否为分公司
		List<Long> role_ids = new ArrayList<Long>(); // 当前登录用户的角色列表
		for (PeRoleUser peRoleUser : peRoleUserList) {
			role_ids.add(peRoleUser.getRole_id());

			if (peRoleUser.getRole_id() >= 10L && peRoleUser.getRole_id() < 30L) {
				role_fgs = false;
			}
		}

		List<KonkaVipPdManage> entitylist = new ArrayList<KonkaVipPdManage>();
		KonkaVipPdManage konkavippdmanage = new KonkaVipPdManage();
		if (role_fgs) {
			konkavippdmanage.setFgs_id(deptlist.get(0).getDept_id());
		}

		if (StringUtils.isNotBlank(fgs_id)) {
			konkavippdmanage.setFgs_id(Long.valueOf(fgs_id));
		}
		if (StringUtils.isNotBlank(is_locked)) {
			konkavippdmanage.setIs_locked(is_locked);
		}
		if (StringUtils.isNotBlank(is_hidden)) {
			konkavippdmanage.setIs_hidden(is_hidden);
		}
		if (StringUtils.isNotBlank(pd_code)) {
			konkavippdmanage.getMap().put("pd_code_like", pd_code);
		}
		if (StringUtils.isNotBlank(pd_type)) {
			konkavippdmanage.setPd_type(Long.valueOf(pd_type));
		}

		long recordCount = super.getFacade().getKonkaVipPdManageService().getKonkaVipPdManageCount(konkavippdmanage);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		konkavippdmanage.getRow().setFirst(pager.getFirstRow());
		konkavippdmanage.getRow().setCount(pager.getRowCount());
		entitylist = super.getFacade().getKonkaVipPdManageService().getKonkaVipPdManagePaginatedList(konkavippdmanage);
		// 拿到分公司
		request.setAttribute("sybDeptInfoList", deptlist);
		request.setAttribute("entityList", entitylist);
		request.setAttribute("_flag", role_fgs);
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		DynaBean dynabean = (DynaBean) form;
		String mod_id = (String) dynabean.get("mod_id");
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		PePdModel pePdModel = new PePdModel();
		pePdModel.setBrand_id(Constants.KONKA_BRAND_ID);
		pePdModel.setIs_del(0);
		request.setAttribute("pdlist", super.getFacade().getPePdModelService().getPePdModelList(pePdModel));
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DynaBean dynabean = (DynaBean) form;
		String mod_id = (String) dynabean.get("mod_id");
		String deptids = (String) dynabean.get("deptids");
		String pd_id = (String) dynabean.get("pd_id");
		String pd_type = (String) dynabean.get("pd_type");
		String pd_desc = (String) dynabean.get("pd_desc");

		PePdModel pepdmodel = new PePdModel();
		if (StringUtils.isNotBlank(pd_id)) {
			pepdmodel.setPd_id(Long.valueOf(pd_id));
			pepdmodel = super.getFacade().getPePdModelService().getPePdModel(pepdmodel);
		}

		if (StringUtils.isNotBlank(deptids)) {
			String[] dept = deptids.split(",");
			PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

			// 拿到上传的图片附件
			// List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH, true, 0,
			// new int[] { 240 });

			List<UploadFile> uploadFileList = super.uploadFile(form, true, 0, new int[] { 60, 86, 95, 120, 240, 280,
					350, 400, 600, 800 });
			List<KonkaPeAttachments> attachmentList = new ArrayList<KonkaPeAttachments>();
			KonkaPeAttachments attachment = null;
			for (UploadFile uploadFile : uploadFileList) {
				attachment = new KonkaPeAttachments();
				attachment.setFile_name(uploadFile.getFileName());
				attachment.setFile_type(uploadFile.getContentType());
				attachment.setFile_size(new Long(uploadFile.getFileSize()));
				attachment.setSave_path(uploadFile.getFileSavePath());
				attachment.setSave_name(uploadFile.getFileSaveName());
				attachment.setIs_del(0l);
				attachment.setLink_tab("KONKA_VIP_PD_MANAGE");
				attachment.setAdd_user_name(ui.getUser_name());
				attachment.setAdd_user_id(ui.getId());
				attachment.setFile_desc(uploadFile.getFormName());
				attachmentList.add(attachment);
			}
			for (String dp : dept) {
				KonkaDept konkadept = new KonkaDept();
				konkadept.setDept_id(Long.valueOf(dp));
				konkadept = super.getFacade().getKonkaDeptService().getKonkaDept(konkadept);
				KonkaVipPdManage konkavippdmanage = new KonkaVipPdManage();
				super.copyProperties(konkavippdmanage, form);
				konkavippdmanage.setFgs_id(Long.valueOf(dp));
				konkavippdmanage.setFgs_name(konkadept.getDept_name());
				konkavippdmanage.setPd_id(Long.valueOf(pd_id));
				konkavippdmanage.setPd_code(pepdmodel.getMd_name());
				konkavippdmanage.setPd_name(pepdmodel.getPd_desc());
				konkavippdmanage.setPd_type(Long.valueOf(pd_type));
				konkavippdmanage.setPd_desc(pd_desc);
				konkavippdmanage.setAdd_user_id(ui.getId());
				konkavippdmanage.setAdd_user_name(ui.getUser_name());
				konkavippdmanage.setIs_del("0");
				konkavippdmanage.setAdd_date(new Date());

				if (attachmentList.size() > 0) {
					konkavippdmanage.setPeAttachmentsList(attachmentList);
				}
				if (null == konkavippdmanage.getId()) {
					super.getFacade().getKonkaVipPdManageService().createKonkaVipPdManage(konkavippdmanage);
					saveMessage(request, "entity.inserted");
				} else {
					String id = (String) dynabean.get("id");
					String pic_edit_id = (String) dynabean.get("pic_edit_id");
					konkavippdmanage.setId(Long.valueOf(id));
					konkavippdmanage.setIs_del("0");
					if (uploadFileList.size() > 0) {
						konkavippdmanage.getMap().put("pic_edit_id", pic_edit_id);
					}
					super.getFacade().getKonkaVipPdManageService().modifyKonkaVipPdManage(konkavippdmanage);// 注：指定机销售在此处添加
					saveMessage(request, "entity.updated");
				}
			}
		}
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DynaBean dynabean = (DynaBean) form;
		String id = (String) dynabean.get("id");
		String pic_id = (String) dynabean.get("pic_id");
		String mod_id = (String) dynabean.get("mod_id");
		KonkaVipPdManage kvpm = new KonkaVipPdManage();
		if (StringUtils.isNotBlank(id)) {
			kvpm.setId(Long.valueOf(id));
		}
		if (StringUtils.isNotBlank(pic_id)) {
			kvpm.getMap().put("pic_id", pic_id);
		}

		super.getFacade().getKonkaVipPdManageService().removeKonkaVipPdManage(kvpm);
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "pic_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		DynaBean dynabean = (DynaBean) form;
		String id = (String) dynabean.get("id");
		String pic_id = (String) dynabean.get("pic_id");
		String mod_id = (String) dynabean.get("mod_id");
		KonkaVipPdManage kvpm = new KonkaVipPdManage();
		if (StringUtils.isNotBlank(id)) {
			kvpm.setId(Long.valueOf(id));
		}
		if (StringUtils.isNotBlank(pic_id)) {
			kvpm.getMap().put("pic_id", pic_id);
		}
		kvpm = super.getFacade().getKonkaVipPdManageService().getKonkaVipPdManage(kvpm);
		super.copyProperties(form, kvpm);

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		PePdModel pePdModel = new PePdModel();
		pePdModel.setBrand_id(Constants.KONKA_BRAND_ID);
		pePdModel.setIs_del(0);
		request.setAttribute("pdlist", super.getFacade().getPePdModelService().getPePdModelList(pePdModel));
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setId(Long.valueOf(pic_id));
		attachment = super.getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachments(attachment);
		request.setAttribute("save_path", attachment.getSave_path());
		request.setAttribute("pic_edit_id", pic_id);
		return mapping.findForward("input");
	}

	public ActionForward show(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DynaBean dynabean = (DynaBean) form;
		String id = (String) dynabean.get("id");
		String is_hidden = (String) dynabean.get("is_hidden_flag");
		String is_locked = (String) dynabean.get("is_locked_flag");
		String mod_id = (String) dynabean.get("mod_id");
		KonkaVipPdManage kvpm = new KonkaVipPdManage();
		if (StringUtils.isNotBlank(id)) {
			kvpm.setId(Long.valueOf(id));
		}
		// 展示-未展示
		if (StringUtils.isNotBlank(is_hidden)) {
			if ("0".equals(is_hidden)) {
				kvpm.setIs_hidden("1");
			} else {
				kvpm.setIs_hidden("0");
			}
		}
		// 锁定---未锁定
		if (StringUtils.isNotBlank(is_locked)) {
			if ("0".equals(is_locked)) {
				kvpm.setIs_locked("1");
			} else {
				kvpm.setIs_locked("0");
			}
		}
		super.getFacade().getKonkaVipPdManageService().modifyKonkaVipPdManage(kvpm);
		saveMessage(request, "entity.updated");
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward deleteAll(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DynaBean dynabean = (DynaBean) form;
		String id = (String) dynabean.get("id");
		String pic_id = (String) dynabean.get("pic_id");
		String[] pks = request.getParameterValues("pks");
		String mod_id = (String) dynabean.get("mod_id");

		if (null != pks && pks.length > 0) {
			for (String pk : pks) {
				KonkaVipPdManage kvpm = new KonkaVipPdManage();
				kvpm.setId(Long.valueOf(pk));
				super.getFacade().getKonkaVipPdManageService().removeKonkaVipPdManage(kvpm);
				KonkaPeAttachments kkam = new KonkaPeAttachments();
				kkam.setLink_id(Long.valueOf(pk));
				kkam.setLink_tab("KONKA_VIP_PD_MANAGE");
				List<KonkaPeAttachments> list = super.getFacade().getKonkaPeAttachmentsService()
						.getKonkaPeAttachmentsList(kkam);
				if (null != list) {
					for (KonkaPeAttachments kpac : list) {
						super.getFacade().getKonkaPeAttachmentsService().removeKonkaPeAttachments(kpac);
					}
				}

			}
		}
		if (StringUtils.isNotBlank(id)) {
			KonkaVipPdManage kvpm = new KonkaVipPdManage();
			kvpm.setId(Long.valueOf(id));

			if (StringUtils.isNotBlank(pic_id)) {
				kvpm.getMap().put("pic_id", pic_id);
			}

			super.getFacade().getKonkaVipPdManageService().removeKonkaVipPdManage(kvpm);
		}
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "pic_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

}
