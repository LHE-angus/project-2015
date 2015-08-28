package com.ebiz.mmt.web.struts.manager.zmd;

import java.math.BigDecimal;
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
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdAccouts;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author hu hao
 *@version 2012-3-5
 */
public class KonkaXxZmdAccoutsAction extends BaseZmdAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		String zmd_id = (String) dynaBean.get("zmd_id");
		String add_date_start = (String) dynaBean.get("add_date_start");
		String add_date_end = (String) dynaBean.get("add_date_end");
		String bill_state = (String) dynaBean.get("bill_state");

		KonkaXxZmdAccouts entity = new KonkaXxZmdAccouts();

		if (StringUtils.isNotBlank(add_date_start)) {
			entity.getMap().put("add_date_start", add_date_start);
		}
		if (StringUtils.isNotBlank(add_date_end)) {
			entity.getMap().put("add_date_end", add_date_end);
		}
		if (StringUtils.isNotBlank(zmd_id)) {
			entity.setZmd_id(Long.valueOf(zmd_id));
		}
		if (StringUtils.isNotBlank(bill_state)) {
			entity.setBill_state(Integer.valueOf(bill_state));
		}

		PeProdUser user_id = super.getSessionUserInfo(request);
		// 取用户角色
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(user_id.getId());

		Boolean role_id_gt_400 = false;
		Boolean role_id_btw_300_400 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if (temp.getRole_id() < 400 && temp.getRole_id() >= 300) {
					role_id_btw_300_400 = true;
				}
			}
		}
		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.property.exist");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
		if (role_id_btw_300_400) {
			KonkaDept kDept = getKonkaDeptForFgs(user_id.getDept_id());
			if (kDept != null) {
				entity.setDept_id(user_id.getDept_id());
				konkaXxZmd.setDept_id(user_id.getDept_id());
			}
		}

		List<KonkaXxZmd> konkaXxZmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(konkaXxZmd);

		Long recordCount = super.getFacade().getKonkaXxZmdAccoutsService().getKonkaXxZmdAccoutsForZmdAndDeptCount(
				entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaXxZmdAccouts> entityList = super.getFacade().getKonkaXxZmdAccoutsService()
				.getKonkaXxZmdAccoutsForZmdAndDeptPaginatedList(entity);
		request.setAttribute("konkaXxZmdList", konkaXxZmdList);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String bill_id = (String) dynaBean.get("bill_id");

		PeProdUser user_id = super.getSessionUserInfo(request);
		// 取用户角色
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);

		List<PeRoleUser> peRoleUserList = getPeRoleList(user_id.getId());

		Boolean role_id_gt_400 = false;
		Boolean role_id_btw_300_400 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if (temp.getRole_id() < 400 && temp.getRole_id() >= 300) {
					role_id_btw_300_400 = true;
				}
			}
		}
		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.property.exist");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		if (role_id_btw_300_400) {
			request.setAttribute("dept_name", user_id.getDepartment());
		}

		if (GenericValidator.isLong(bill_id)) {
			KonkaXxZmdAccouts entity = new KonkaXxZmdAccouts();
			entity.setBill_id(Long.valueOf(bill_id));

			entity = getFacade().getKonkaXxZmdAccoutsService().getKonkaXxZmdAccouts(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			} else {
				KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
				konkaXxZmd.setZmd_id(entity.getZmd_id());
				konkaXxZmd = getFacade().getKonkaXxZmdService().getKonkaXxZmd(konkaXxZmd);
				request.setAttribute("zmd_sn", konkaXxZmd.getZmd_sn());

				entity.setQueryString(super.serialize(request, "id", "method"));
				super.copyProperties(form, entity);
				return mapping.findForward("input");
			}
		} else {
			saveError(request, "errors.long", bill_id);
			return mapping.findForward("list");
		}
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		String bill_id = (String) dynaBean.get("bill_id");
		String rec_money = (String) dynaBean.get("rec_money");
		String rec_money_s = (String) dynaBean.get("rec_money_s");
		String jz_user = (String) dynaBean.get("jz_user");

		KonkaXxZmdAccouts entity = new KonkaXxZmdAccouts();

		if (StringUtils.isNotBlank(rec_money)) {
			entity.setRec_money(new BigDecimal(rec_money));
		}
		if (StringUtils.isNotBlank(jz_user)) {
			entity.setJz_user(jz_user);
		}

		entity.setBill_state(1);
		entity.setRec_money_e((new BigDecimal(rec_money)).add(new BigDecimal(rec_money_s)));
		entity.setJz_time(new Date());
		entity.setBill_id(Long.valueOf(bill_id));
		super.getFacade().getKonkaXxZmdAccoutsService().modifyKonkaXxZmdAccouts(entity);
		saveMessage(request, "entity.updated");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
}
