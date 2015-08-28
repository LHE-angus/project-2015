package com.ebiz.mmt.web.struts.manager.jf;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JfGiftInfo;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * 
 * @author pangang
 * @date 2013-6-25
 */
public class JfGiftInfoAction extends BaseJfAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String dept_id = (String) dynaBean.get("dept_id");
		String gift_name_like = (String) dynaBean.get("gift_name_like");
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		JfGiftInfo entity = new JfGiftInfo();
		if (user.getDept_id() > 0L) {
			entity.setDept_id(user.getDept_id());
		} else {
			if (StringUtils.isNotBlank(dept_id)) {
				entity.setDept_id(Long.valueOf(dept_id));
			}
		}
		if (StringUtils.isNotBlank(gift_name_like)) {
			entity.getMap().put("gift_name_like", gift_name_like);
		}

		Long recordCount = super.getFacade().getJfGiftInfoService().getJfGiftInfoCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<JfGiftInfo> entityList = super.getFacade().getJfGiftInfoService().getJfGiftInfoPaginatedList(entity);

		if (null != entityList && entityList.size() > 0) {
			for (JfGiftInfo rule : entityList) {
				KonkaDept dept = new KonkaDept();
				dept.setDept_id(rule.getDept_id());
				dept = super.getFacade().getKonkaDeptService().getKonkaDept(dept);
				rule.getMap().put("dept_name", dept.getDept_name());
			}

		}

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		PePdModel pd = new PePdModel();
		List<PePdModel> pdList = super.getFacade().getPePdModelService().getPePdModelList(pd);

		request.setAttribute("pdList", pdList);

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		PePdModel pd = new PePdModel();
		List<PePdModel> pdList = super.getFacade().getPePdModelService().getPePdModelList(pd);
		request.setAttribute("pdList", pdList);

		JfGiftInfo entity = new JfGiftInfo();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getJfGiftInfoService().getJfGiftInfo(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		JfGiftInfo entity = new JfGiftInfo();
		super.copyProperties(entity, form);

		// 上传图片
		List<com.ebiz.ssi.web.struts.bean.UploadFile> uploadFileList = super.uploadFile(form,
				MmtFilePathConfig.NEWS_PATH, true, 0, new int[] { 120, 240, 400, 640 });
		boolean flag = false;
		for (UploadFile uploadFile : uploadFileList) {
			if ("image_src".equalsIgnoreCase(uploadFile.getFormName())) {
				if (uploadFile.getExtension().equals("jpg") || uploadFile.getExtension().equals("png")
						|| uploadFile.getExtension().equals("gif")) {
					entity.setImage_src((uploadFile.getFileSavePath()));
					flag = true;
				}
			}
		}

		if (StringUtils.isBlank(entity.getImage_src())) {
			entity.setImage_src(null);
		}
		if (StringUtils.isEmpty(id)) {
			if (flag) {
				super.getFacade().getJfGiftInfoService().createJfGiftInfo(entity);
				super.saveMessage(request, "entity.inserted");
			} else {
				String msg = super.getMessage(request, "jf.rule.pic");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		} else {
			super.getFacade().getJfGiftInfoService().modifyJfGiftInfo(entity);
			super.saveMessage(request, "entity.updated");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		JfGiftInfo entity = new JfGiftInfo();
		entity.setId(Long.valueOf(id));
		super.getFacade().getJfGiftInfoService().removeJfGiftInfo(entity);

		saveMessage(request, "entity.deleted");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.serialize(request, "id", "method"));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
}
