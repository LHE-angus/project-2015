package com.ebiz.mmt.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaPeArticleInfo;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Hu,Hao
 * @version 2013-12-19
 */
public class KonkaPeArticleGxwjjAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		super.getModPopeDom(form, request);
		super.setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		Pager pager = (Pager) dynaBean.get("pager");

		super.encodeCharacterForGetMethod(dynaBean, request);

		String mod_code = (String) dynaBean.get("mod_id");
		String title = (String) dynaBean.get("title");
		String is_self = (String) dynaBean.get("is_self");
		String add_date_s = (String) dynaBean.get("add_date_s");
		String add_date_e = (String) dynaBean.get("add_date_e");

		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
		entity.setArticle_mod_id(Long.valueOf(mod_code));
		entity.setArticle_type_id(-10L);
		if (StringUtils.isNotBlank(title)) {
			entity.getMap().put("title_likes", title);
		}
		if (StringUtils.isNotBlank(is_self)) {
			if ("1".equals(is_self)) {
				entity.setAdd_user_id(ui.getId());
			} else {
				entity.getMap().put("is_not_self", ui.getId());
			}
		}
		
		if(StringUtils.isNotBlank(add_date_s))
			entity.getMap().put("add_date_s", add_date_s +" 00:00:00");
		
		if(StringUtils.isNotBlank(add_date_e))
			entity.getMap().put("add_date_e", add_date_e +" 23:59:59");

		entity.setIs_del(0L);

		KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
		if (kDept != null) {
			entity.setAdd_prod_entp_id(Long.valueOf(kDept.getDept_id()));
		}

		Long recordCount = getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfoCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaPeArticleInfo> entityList = getFacade().getKonkaPeArticleInfoService()
				.getKonkaPeArticleInfoPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		request.setAttribute("https", getCtxPath(request));

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		super.setNaviStringToRequestScope(form, request);

		saveToken(request);

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			String msg = super.getMessage(request, "errors.parm");
			renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfo(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		super.copyProperties(form, entity);

		// 附件列表
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(entity.getId());
		attachment.setIs_del((long) 0);
		request.setAttribute("attachmentList", getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachmentsList(
				attachment));
		
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		PeRoleUser p = new PeRoleUser();
		p.setUser_id(peProdUser.getId());
		List<PeRoleUser> pList = super.getFacade().getPeRoleUserService().getPeRoleUserList(p);

		Boolean role_id_gt_30_and_200_btw_300 = false;
		if (pList.size() > 0) {
			for (PeRoleUser pp : pList) {
				if (pp.getRole_id() < 30 || (pp.getRole_id() >= 200 && pp.getRole_id() < 300))
					role_id_gt_30_and_200_btw_300 = true;
			}
		}

		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
		super.copyProperties(entity, form);

		entity.setArticle_type_id(-10L);
		entity.setIs_sent(0);

		if (role_id_gt_30_and_200_btw_300) {
			entity.setAdd_prod_entp_id(0L);
		} else {
			KonkaDept kDept = getKonkaDeptForFgs(peProdUser.getDept_id());
			if (null != kDept)
				entity.setAdd_prod_entp_id(kDept.getDept_id());
		}

		entity.setArticle_mod_id(Long.valueOf(mod_id));

		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH, true, 0, new int[] {
				240, 400, 480,
				600, 720 });
		List<KonkaPeAttachments> konkaPeAttachmentsList = new ArrayList<KonkaPeAttachments>();
		KonkaPeAttachments konkaPeAttachments = null;
		for (UploadFile uploadFile : uploadFileList) {
			if ("img_path".equalsIgnoreCase(uploadFile.getFormName())) {
				entity.setImg_path(uploadFile.getFileSavePath());
			} else {
				konkaPeAttachments = new KonkaPeAttachments();
				konkaPeAttachments.setFile_name(uploadFile.getFileName());
				konkaPeAttachments.setFile_type(uploadFile.getContentType());
				konkaPeAttachments.setFile_size(new Long(uploadFile.getFileSize()));
				konkaPeAttachments.setSave_path(uploadFile.getFileSavePath());
				konkaPeAttachments.setSave_name(uploadFile.getFileSaveName());
				konkaPeAttachments.setIs_del((long) 0);
				konkaPeAttachments.setLink_tab(uploadFile.getFormName());
				konkaPeAttachments.setAdd_user_name(peProdUser.getUser_name());
				konkaPeAttachments.setAdd_user_id(peProdUser.getId());
				konkaPeAttachmentsList.add(konkaPeAttachments);
			}
		}
		entity.setKonkaPeAttachments(konkaPeAttachmentsList);

		if (StringUtils.isBlank(entity.getImg_path())) {
			entity.setImg_path(null);
		}

		if (!GenericValidator.isLong(id)) {// insert
			entity.setAdd_user_id(peProdUser.getId());
			entity.setAdd_user_name(peProdUser.getUser_name());
			entity.setView_counts(0l);
			entity.setIs_del(0l);

			getFacade().getKonkaPeArticleInfoService().createKonkaPeArticleInfoForGroup(entity);
			saveMessage(request, "entity.inserted");
		} else if (null != entity) {// update
			getFacade().getKonkaPeArticleInfoService().modifyKonkaPeArticleInfoForGroup(entity);
			saveMessage(request, "entity.updated");
		}
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=").append(mod_id);
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			String msg = super.getMessage(request, "errors.parm");
			renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfo(entity);
		super.copyProperties(form, entity);

		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(new Long(id));
		attachment.setIs_del((long) 0);
		request.setAttribute("attachmentList", getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachmentsList(
				attachment));

		return mapping.findForward("view");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		PeProdUser PeProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
			entity.setId(new Long(id));
			entity.setDel_date(new Date());
			entity.setIs_del((long) 1);
			entity.setDel_user_id(PeProdUser.getId());
			super.getFacade().getKonkaPeArticleInfoService().removeKonkaPeArticleInfo(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
			entity.setDel_date(new Date());
			entity.setDel_user_id(PeProdUser.getId());
			entity.setIs_del((long) 1);
			entity.getMap().put("pks", pks);
			super.getFacade().getKonkaPeArticleInfoService().removeKonkaPeArticleInfo(entity);
			saveMessage(request, "entity.deleted");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=").append(mod_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward deleteFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");

		if (StringUtils.isNotBlank(id) && GenericValidator.isLong(id)) {
			KonkaPeAttachments entity = new KonkaPeAttachments();
			entity.setId(new Long(id));
			getFacade().getKonkaPeAttachmentsService().removeKonkaPeAttachments(entity);
			saveMessage(request, "entity.deleted");
		}

		super.renderText(response, "success");
		return null;
	}

	public ActionForward viewShow(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {

			KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
			entity.setId(Long.valueOf(id));

			entity = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfo(entity);

			KonkaPeAttachments attachment = new KonkaPeAttachments();
			attachment.setLink_id(new Long(id));
			attachment.setIs_del((long) 0);
			request.setAttribute("attachmentList", getFacade().getKonkaPeAttachmentsService()
					.getKonkaPeAttachmentsList(attachment));

			KonkaPeArticleInfo update = new KonkaPeArticleInfo();
			update.setId(entity.getId());
			update.setView_counts(entity.getView_counts() + 1L);
			super.getFacade().getKonkaPeArticleInfoService().modifyKonkaPeArticleInfo(update);

			request.setAttribute("entity", entity);
			request.setAttribute("https", getCtxPath(request));
			return new ActionForward("/admin/KonkaPeArticleGxwjj/view-content.jsp");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}

}
