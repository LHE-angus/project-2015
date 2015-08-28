package com.ebiz.mmt.web.struts.manager.zmd;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxZmdCredit;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu,Hao
 * @version 2013-03-26
 */
public class KonkaXxZmdCreditAction extends BaseZmdAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		PeProdUser user_id = super.getSessionUserInfo(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(user_id.getId());

		// 用户角色
		Boolean role_id_gt_400 = false;
		Boolean role_id_btw_300_400 = false;
		Boolean role_id_eq_350 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if (temp.getRole_id() < 400 && temp.getRole_id() >= 300 && temp.getRole_id() != 350) {
					role_id_btw_300_400 = true;
				}
				if (temp.getRole_id() == 350) {
					role_id_eq_350 = true;
				}
			}
		}

		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaXxZmdCredit entity = new KonkaXxZmdCredit();

		KonkaDept kDept = getKonkaDeptForFgs(user_id.getDept_id());
		
		if (role_id_btw_300_400) {// 分公司人员
			request.setAttribute("dept_name", kDept.getDept_name());
		} else if (role_id_eq_350) {// 分公司财务
			request.setAttribute("dept_id", kDept.getDept_id());
			request.setAttribute("dept_name", kDept.getDept_name());
		} else {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		Pager pager = (Pager) dynaBean.get("pager");
		Long recordCount = getFacade().getKonkaXxZmdCreditService().getKonkaXxZmdCreditCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaXxZmdCredit> entityList = super.getFacade().getKonkaXxZmdCreditService()
				.getKonkaXxZmdCreditPaginatedList(entity);

		if (null != entityList && entityList.size() > 0) {

			for (KonkaXxZmdCredit temp : entityList) {
				PeProdUser peProdUser = new PeProdUser();
				peProdUser.setId(temp.getAdd_user_id());
				peProdUser = super.getFacade().getPeProdUserService().getPeProdUser(peProdUser);
				temp.getMap().put("add_user_name", peProdUser.getUser_name());
			}
			request.setAttribute("entityList", entityList);
		}

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");

		if (!GenericValidator.isLong(dept_id)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(Long.valueOf(dept_id));
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		if (null == konkaDept) {
			String msg = super.getMessage(request, "entity.missing");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		dynaBean.set("dept_id", dept_id);
		dynaBean.set("dept_name", konkaDept.getDept_name());

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		KonkaXxZmdCredit entity = new KonkaXxZmdCredit();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaXxZmdCreditService().getKonkaXxZmdCredit(entity);
		super.copyProperties(form, entity);

		if (null == entity) {
			String msg = super.getMessage(request, "entity.missing");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		if (null != entity.getDetp_id()) {
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(entity.getDetp_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			dynaBean.set("dept_name", konkaDept.getDept_name());
		}

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");

		PeProdUser user_id = super.getSessionUserInfo(request);

		KonkaXxZmdCredit entity = new KonkaXxZmdCredit();
		super.copyProperties(entity, form);

		entity.setAdd_user_id(user_id.getId());
		entity.setAdd_date(new Date());

		if (GenericValidator.isLong(id)) {
			entity.setId(Long.valueOf(id));
			super.getFacade().getKonkaXxZmdCreditService().modifyKonkaXxZmdCreditForUpdate(entity);
			saveMessage(request, "entity.updated");
		} else {

			if (!GenericValidator.isLong(dept_id)) {
				String msg = super.getMessage(request, "errors.parm");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			entity.setDetp_id(Long.valueOf(dept_id));
			super.getFacade().getKonkaXxZmdCreditService().createKonkaXxZmdCredit(entity);
			saveMessage(request, "entity.inserted");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward validateType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String credit_type = (String) dynaBean.get("credit_type");
		KonkaXxZmdCredit entity = new KonkaXxZmdCredit();
		String isExist = "null";

		if (StringUtils.isNotBlank(credit_type)) {
			entity.setCredit_type(credit_type);
			List<KonkaXxZmdCredit> entityList = getFacade().getKonkaXxZmdCreditService()
					.getKonkaXxZmdCreditList(entity);
			if (entityList.size() == 0) {// 可用
				isExist = String.valueOf("0");
			} else {

				isExist = String.valueOf("11");
			}
		}

		super.render(response, isExist, "text/x-json;charset=UTF-8");
		return null;
	}
}
