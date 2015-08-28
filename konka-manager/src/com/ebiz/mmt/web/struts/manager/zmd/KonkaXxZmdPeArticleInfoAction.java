package com.ebiz.mmt.web.struts.manager.zmd;

import java.text.SimpleDateFormat;
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
 * @version 2013-07-14
 */
public class KonkaXxZmdPeArticleInfoAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		super.setNaviStringToRequestScope(form, request);

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		String mod_code = (String) dynaBean.get("mod_id");
		String type_name = (String) dynaBean.get("type_name");
		String st_pub_date = (String) dynaBean.get("st_pub_date");
		String en_pub_date = (String) dynaBean.get("en_pub_date");
		String states = (String) dynaBean.get("states");

		Boolean role_id_ge_400 = false;
		Boolean role_id_btw_300_400_or_30_40 = false;
		Boolean role_id_btw_200_300_or_gt_30 = false;

		// 判断用户角色
		PeRoleUser pUser = new PeRoleUser();
		pUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> pUserList = super.getFacade().getPeRoleUserService().getPeRoleUserList(pUser);

		if (pUserList.size() > 0) {
			for (PeRoleUser ppu : pUserList) {
				if (ppu.getRole_id() <= 400) {
					role_id_ge_400 = true;
				}
				if ((ppu.getRole_id() >= 300 && ppu.getRole_id() < 400)
						|| (ppu.getRole_id() >= 200 && ppu.getRole_id() < 300)) {
					role_id_btw_300_400_or_30_40 = true;
				}
				if ((ppu.getRole_id() >= 200 && ppu.getRole_id() < 300) || ppu.getRole_id() <= 30) {
					role_id_btw_200_300_or_gt_30 = true;
				}
			}
		}

		if (!role_id_ge_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();

		entity.setArticle_mod_id(Long.valueOf(mod_code));

		if (role_id_btw_300_400_or_30_40 && !role_id_btw_200_300_or_gt_30) {
			//entity.setDept_ids(getKonkaDeptForFgs(peProdUser.getDept_id()).toString());
			entity.getMap().put("dept_ids_fgs", getKonkaDeptForFgs(peProdUser.getDept_id()).toString());
		}
		if (role_id_btw_200_300_or_gt_30) {
			entity.getMap().put("dept_ids_is_not_null", true);
		}

		if (StringUtils.isNotBlank(st_pub_date)) {
			entity.getMap().put("st_pub_date", st_pub_date);
		}
		if (StringUtils.isNotBlank(en_pub_date)) {
			entity.getMap().put("en_pub_date", en_pub_date);
		}
		if (StringUtils.isNotBlank(type_name)) {
			entity.getMap().put("type_name", type_name);
		}
		if (StringUtils.isNotBlank(states)) {
			entity.setStates(Long.valueOf(states));
		}
		entity.getMap().put("user_name", peProdUser.getUser_name());

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
		DynaBean dynaBean = (DynaBean) form;

		dynaBean.set("is_link_out", "0");
		dynaBean.set("states", "0");

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
		String pub_date = (String) dynaBean.get("pub_date");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		Boolean role_id_btw_300_400_or_30_40 = false;
		Boolean role_id_btw_200_300_or_gt_30 = false;

		// 判断用户角色
		PeRoleUser pUser = new PeRoleUser();
		pUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> pUserList = super.getFacade().getPeRoleUserService().getPeRoleUserList(pUser);
		if (pUserList.size() > 0) {
			for (PeRoleUser ppu : pUserList) {
				if ((ppu.getRole_id() >= 300 && ppu.getRole_id() < 400)
						|| (ppu.getRole_id() >= 200 && ppu.getRole_id() < 300)) {
					role_id_btw_300_400_or_30_40 = true;
				}
				if ((ppu.getRole_id() >= 200 && ppu.getRole_id() < 300) || ppu.getRole_id() <= 30) {
					role_id_btw_200_300_or_gt_30 = true;
				}
			}
		}

		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
		super.copyProperties(entity, form);

		if (role_id_btw_300_400_or_30_40) {
			entity.setDept_ids(getKonkaDeptForFgs(peProdUser.getDept_id()).toString());
		}
		if (role_id_btw_200_300_or_gt_30) {
			entity.setDept_ids("0");
		}

		if (StringUtils.isNotBlank(pub_date)) {
			SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String pub_date_value = pub_date + " " + sdf2.format(new Date());
			entity.setPub_date(sdf1.parse(pub_date_value));
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
			return new ActionForward("/zmd/KonkaGroupPeArticleInfo/view-content.jsp");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}
}
