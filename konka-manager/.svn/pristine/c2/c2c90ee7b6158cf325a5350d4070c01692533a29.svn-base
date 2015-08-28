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
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdPosRec;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu Hao
 * @version 2012-5-3
 */
public class KonkaXxZmdPosRecForZmdAction extends BaseZmdAction {

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		PeProdUser user_info = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		Boolean role_flag = super.getRoleIdFlag(user_info.getId(), -1L, 401L);
		if (role_flag) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		Pager pager = (Pager) dynaBean.get("pager");
		String out_date_ge = (String) dynaBean.get("out_date_ge");
		String out_date_le = (String) dynaBean.get("out_date_le");
		String zmd_id = (String) dynaBean.get("zmd_id");
		String state = (String) dynaBean.get("state");

		KonkaXxZmdPosRec entity = new KonkaXxZmdPosRec();

		if (StringUtils.isNotBlank(out_date_ge)) {
			entity.getMap().put("out_date_ge", out_date_ge);
		}
		if (StringUtils.isNotBlank(out_date_le)) {
			entity.getMap().put("out_date_le", out_date_le);
		}
		if (StringUtils.isNotBlank(zmd_id)) {
			entity.setZmd_id(Long.valueOf(zmd_id));
		}
		if (StringUtils.isNotBlank(state)) {
			entity.setState(Integer.valueOf(state));
		} else {
			entity.setState(1);
		}

		Long dept_id = null;
		KonkaDept kDept = getKonkaDeptForFgs(user_info.getDept_id());
		if (kDept != null)
			dept_id = kDept.getDept_id();

		KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
		konkaXxZmd.setDept_id(dept_id);
		List<KonkaXxZmd> konkaXxZmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(konkaXxZmd);
		request.setAttribute("konkaXxZmdList", konkaXxZmdList);

		entity.setDept_id(dept_id);

		Long recordCount = super.getFacade().getKonkaXxZmdPosRecService().getKonkaXxZmdPosRecForZmdSnCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaXxZmdPosRec> entityList = super.getFacade().getKonkaXxZmdPosRecService()
				.getKonkaXxZmdPosRecForZmdSnPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String pos_id = (String) dynaBean.get("pos_id");

		KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
		konkaXxSellBill.setPos_id(Long.valueOf(pos_id));
		List<KonkaXxSellBill> konkaXxSellBillList = super.getFacade().getKonkaXxSellBillService()
				.getKonkaXxSellBillList(konkaXxSellBill);
		if (konkaXxSellBillList.size() > 0) {
			request.setAttribute("konkaXxSellBillList", konkaXxSellBillList);
			request.setAttribute("zmd_sn", konkaXxSellBillList.get(0).getZmd_sn());
		}

		KonkaXxZmdPosRec entity = new KonkaXxZmdPosRec();
		entity.setPos_id(Long.valueOf(pos_id));

		entity = getFacade().getKonkaXxZmdPosRecService().getKonkaXxZmdPosRec(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		} else {
			entity.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, entity);
			return mapping.findForward("input");
		}
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String pos_id = (String) dynaBean.get("pos_id");
		String state = (String) dynaBean.get("state");

		if (state.equals("2")) {
			KonkaXxZmdPosRec entity = new KonkaXxZmdPosRec();
			PeProdUser user_info = super.getSessionUserInfo(request);

			entity.setState(2);
			entity.setConfirm_date(new Date());
			entity.setPos_id(Long.valueOf(pos_id));
			entity.setConfirm_user_id(user_info.getId());

			super.getFacade().getKonkaXxZmdPosRecService().modifyKonkaXxZmdPosRecForZmd(entity);
			saveMessage(request, "entity.updated");
		} else {
			saveMessage(request, "entity.updated");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String pos_id = (String) dynaBean.get("pos_id");

		if (!GenericValidator.isLong(pos_id)) {
			this.saveError(request, "errors.long", new String[] { pos_id });
			return mapping.findForward("list");
		}

		KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
		konkaXxSellBill.setPos_id(Long.valueOf(pos_id));
		List<KonkaXxSellBill> konkaXxSellBillList = super.getFacade().getKonkaXxSellBillService()
				.getKonkaXxSellBillList(konkaXxSellBill);
		request.setAttribute("konkaXxSellBillList", konkaXxSellBillList);

		KonkaXxZmdPosRec entity = new KonkaXxZmdPosRec();
		entity.setPos_id(Long.valueOf(pos_id));
		entity = super.getFacade().getKonkaXxZmdPosRecService().getKonkaXxZmdPosRec(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		super.copyProperties(form, entity);
		return mapping.findForward("view");
	}
}
