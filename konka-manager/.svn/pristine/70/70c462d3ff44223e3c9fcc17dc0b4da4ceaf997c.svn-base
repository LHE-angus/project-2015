package com.ebiz.mmt.web.struts.jxc;

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

import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.JxcBrandApply;
import com.ebiz.mmt.domain.StdEntpUser;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;
/**
 * @author Qin,Yue
 * @version 2011-10-10
 */
public class JxcBrandApplyAction extends BaseJxcAction {
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		Pager pager = (Pager) dynaBean.get("pager");
		String brand_name = (String) dynaBean.get("brand_name");
		String states = (String) dynaBean.get("states");
		// test
		
		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);

		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);

		Long shop_id = user.getStdEntpMain().getShop_id();

		JxcBrandApply entity = new JxcBrandApply();
		entity.setShop_id(shop_id);
		if (brand_name != null && StringUtils.isNotBlank(brand_name)) {
			entity.getMap().put("brand_name", brand_name);
			dynaBean.set("brand_name", brand_name);
		}
		if (states != null && StringUtils.isNotBlank(states)) {
			entity.setStates(Integer.parseInt(states));
			dynaBean.set("states", states);
		}
		entity.setIs_del(0);
		entity.getMap().put("add_date_desc", true);
		Long recordCount = getFacade().getJxcBrandApplyService().getJxcBrandApplyCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<JxcBrandApply> entityList = getFacade().getJxcBrandApplyService().getJxcBrandApplyPaginatedList(
				entity);
		request.setAttribute("entityList", entityList);
		logger.info("shop_id:{}",shop_id);
		return mapping.findForward("list");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			JxcBrandApply entity = new JxcBrandApply();
			entity.setId(new Long(id));
			entity.setIs_del(1);
			getFacade().getJxcBrandApplyService().modifyJxcBrandApply(entity);
		} else if (!ArrayUtils.isEmpty(pks)) {
			JxcBrandApply entity = new JxcBrandApply();
			entity.getMap().put("pks", pks);
			entity.setIs_del(1);
			super.getFacade().getJxcBrandApplyService().modifyJxcBrandApply(entity);
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		dynaBean.set("keySeq", keySeq);
		dynaBean.set("queryString", super.serialize(request, "mod_id", "method"));
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String id = (String) dynaBean.get("id");
		
		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);

		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);
		Long shop_id = user.getStdEntpMain().getShop_id();

		String brand_name = (String) dynaBean.get("brand_name");
		String del_brand_logo = (String) dynaBean.get("del_brand_logo");
		Date date = new Date();

		JxcBrandApply jxcBrandApply = new JxcBrandApply();
		jxcBrandApply.setShop_id(shop_id);
		jxcBrandApply.setBrand_name(brand_name);
		jxcBrandApply.setIs_del(0);
		jxcBrandApply.getMap().put("id_not", id);
		Long count = getFacade().getJxcBrandApplyService().getJxcBrandApplyCount(jxcBrandApply);
		if (count.intValue() > 0) {
			String msg = super.getMessage(request, "jxc.brand.name.repeat");
			super.renderJavaScript(response, "alert('" + msg + "');history.back();");
			return null;
		}

		JxcBrandApply entity = new JxcBrandApply();
		super.copyProperties(entity, form);
		entity.setShop_id(shop_id);
		entity.setBrand_name(brand_name);
		entity.setIs_del(0);
		entity.setApply_user_id(user.getUser_id());
		entity.setApply_user_name(user.getUser_name());
		entity.setAdd_date(date);

		entity.setStates(0);

		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.NEWS_PATH, true, 0, new int[] { 120,
				240 });

		for (UploadFile uploadFile : uploadFileList) {
			Attachment attachment = new Attachment();
			attachment.setFile_type(uploadFile.getContentType());
			attachment.setFile_size(uploadFile.getFileSize());
			attachment.setFile_name(uploadFile.getFileName());
			attachment.setSave_path(uploadFile.getFileSavePath());
			if ("brand_logo".equalsIgnoreCase(uploadFile.getFormName())) {
				entity.setBrand_logo(uploadFile.getFileSavePath());
			}
		}
		if (StringUtils.isNotBlank(del_brand_logo)) {
			entity.setBrand_logo(null);
		}

		if (!StringUtils.isNotBlank(id)) {
			super.getFacade().getJxcBrandApplyService().createJxcBrandApply(entity);
		} else {
			entity.setId(Long.parseLong(id));
			super.getFacade().getJxcBrandApplyService().modifyJxcBrandApply(entity);
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		logger.info("pathBuffer={}", pathBuffer);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		dynaBean.set("keySeq", keySeq);
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
		JxcBrandApply entity = new JxcBrandApply();
		entity.setId(Long.parseLong(id));
		entity = super.getFacade().getJxcBrandApplyService().getJxcBrandApply(entity);

		if (null == entity) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

		super.copyProperties(form, entity);
		dynaBean.set("edit", "1");
		dynaBean.set("queryString", super.serialize(request, "mod_id", "method"));
		return mapping.findForward("input");
	}

	public ActionForward validateName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String brand_name = (String) dynaBean.get("brand_name");
		String keySeq = (String) dynaBean.get("keySeq");
		String id = (String) dynaBean.get("id");
		JxcBrandApply entity = new JxcBrandApply();
		String isExist = "0";
		entity.setBrand_name(brand_name);
		entity.setIs_del(0);

		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);
		Long shop_id = user.getStdEntpMain().getShop_id();
		entity.setShop_id(shop_id);
		entity.getMap().put("id_not", id);

		Long count = super.getFacade().getJxcBrandApplyService().getJxcBrandApplyCount(entity);
		isExist = String.valueOf(count);
		super.render(response, isExist, "text/x-json;charset=UTF-8");
		return null;
	}
}
