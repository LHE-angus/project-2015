package com.ebiz.mmt.web.struts.manager.paragon;

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

import com.ebiz.mmt.domain.KonkaParagonShowimg;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class KonkaParagonShowimgAction extends BaseAction {
	
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		dynaBean.set("queryString", super.serialize(request, "img_id", "method"));
		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String show_shop_code = (String) dynaBean.get("show_shop_code"); //编码
		KonkaParagonShowimg entity = new KonkaParagonShowimg();
		entity.setShow_shop_code(show_shop_code);
		
		List<KonkaParagonShowimg> entityList = getFacade().getKonkaParagonShowimgService().getKonkaParagonShowimgList(entity);
		request.setAttribute("entityList", entityList);
		
		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String show_shop_code = (String) dynaBean.get("show_shop_code"); //编码

		KonkaParagonShowimg entity = new KonkaParagonShowimg();
		super.copyProperties(entity, form);
		
		// 附件处理
		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH, true, 0,
				new int[] { 120 });
		for (UploadFile uploadFile : uploadFileList) {
			entity.setImg_path(uploadFile.getFileSavePath());
		}
		
		if (null == entity.getImg_id()) {
			PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
			entity.setAddman(peProdUser.getId());
			entity.setAddtime(new Date());
			getFacade().getKonkaParagonShowimgService().createKonkaParagonShowimg(entity);
			saveMessage(request, "entity.insertPhoto");
		} else {
			getFacade().getKonkaParagonShowimgService().modifyKonkaParagonShowimg(entity);
			saveMessage(request, "entity.updatePhoto");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&show_shop_code=" + show_shop_code);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String img_id = (String) dynaBean.get("img_id");

		if (GenericValidator.isLong(img_id)) {
			KonkaParagonShowimg entity = new KonkaParagonShowimg();
			entity.setImg_id(new Long(img_id));
			entity = getFacade().getKonkaParagonShowimgService().getKonkaParagonShowimg(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			entity.setQueryString(super.serialize(request, "img_id", "method"));
			super.copyProperties(form, entity);
			
			return mapping.findForward("input");
		} else {
			saveError(request, "errors.long", img_id);
			return mapping.findForward("list");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		DynaBean dynaBean = (DynaBean) form;
		String img_id = (String) dynaBean.get("img_id");
		String[] pks = (String[]) dynaBean.get("pks");

		if (!StringUtils.isBlank(img_id) && GenericValidator.isLong(img_id)) {
			KonkaParagonShowimg entity = new KonkaParagonShowimg();
			entity.setImg_id(new Long(img_id));
			getFacade().getKonkaParagonShowimgService().removeKonkaParagonShowimg(entity);
			saveMessage(request, "entity.deletePhoto");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaParagonShowimg entity = new KonkaParagonShowimg();
			entity.getMap().put("pks", pks);
			getFacade().getKonkaParagonShowimgService().removeKonkaParagonShowimg(entity);
			saveMessage(request, "entity.deletePhoto");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "img_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String img_id = (String) dynaBean.get("img_id");

		if (GenericValidator.isLong(img_id)) {
			KonkaParagonShowimg entity = new KonkaParagonShowimg();
			entity.setImg_id(new Long(img_id));
			entity = getFacade().getKonkaParagonShowimgService().getKonkaParagonShowimg(entity);

			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			super.copyProperties(form, entity);
			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", img_id);
			return mapping.findForward("list");
		}
	}
}