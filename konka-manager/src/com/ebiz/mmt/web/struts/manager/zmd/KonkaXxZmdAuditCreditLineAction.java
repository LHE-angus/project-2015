package com.ebiz.mmt.web.struts.manager.zmd;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdCreditApply;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaXxZmdAuditCreditLineAction extends BaseZmdAction {

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		String msg = request.getParameter("msg");
		String zmd_sn_like = request.getParameter("zmd_sn_like");

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		Long dept_id = null;
		KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
		if(kDept != null)
			dept_id = kDept.getDept_id();
		
		KonkaXxZmdCreditApply entity = new KonkaXxZmdCreditApply();
		super.copyProperties(entity, form);

		entity.setDept_id(dept_id);
		entity.getMap().put("zmd_sn_like", zmd_sn_like);

		Long recordCount = super.getFacade().getKonkaXxZmdCreditApplyService().getKonkaXxZmdCreditApplyCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());
		List<KonkaXxZmdCreditApply> entityList = getFacade().getKonkaXxZmdCreditApplyService()
				.getKonkaXxZmdCreditApplyForTypePaginatedList(entity);
		if (null != entityList && entityList.size() > 0) {
			for (KonkaXxZmdCreditApply kxzca : entityList) {
				KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
				konkaXxZmd.setZmd_id(kxzca.getZmd_id());
				konkaXxZmd.setIs_del(0);
				konkaXxZmd = getFacade().getKonkaXxZmdService().getKonkaXxZmd(konkaXxZmd);
				if (null != konkaXxZmd) {
					kxzca.getMap().put("zmd_sn", konkaXxZmd.getZmd_sn());
				}
			}
		}
		request.setAttribute("entityList", entityList);

		KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
		konkaXxZmd.setArc_state(20300);
		konkaXxZmd.setDept_id(dept_id);
		// konkaXxZmd.getMap().put("zmdNotIn", dept_id);
		List<KonkaXxZmd> zmdList = getFacade().getKonkaXxZmdService().getKonkaXxZmdList(konkaXxZmd);
		request.setAttribute("zmdList", zmdList);

		if ("1".equals(msg)) {
			msg = super.getMessage(request, "konka.zmd.credit.line.audit.success");
			dynaBean.set("msg", msg);
		}

		return mapping.findForward("list");
	}

	public ActionForward plAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String mod_id = (String) dynaBean.get("mod_id");
		String acc_id_hidden = (String) dynaBean.get("acc_id_hidden");
		String[] pkss = request.getParameterValues("pkss");
		String is_audit = (String) dynaBean.get("is_audit");
		String msg = "";

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		KonkaXxZmdCreditApply entity = new KonkaXxZmdCreditApply();
		super.copyProperties(entity, form);
		entity.setAudit_date(new Date());
		entity.setAudit_user_id(ui.getId());

		String ms = getMessage(request, "konka.xx.zmd.audit.apply.credit.line");
		entity.getMap().put("ms", ms);
		if (StringUtils.isNotBlank(is_audit)) {
			entity.getMap().put("is_audit", is_audit);
		}

		if (ArrayUtils.isNotEmpty(pkss)) {
			entity.getMap().put("pkss", pkss);
			super.getFacade().getKonkaXxZmdCreditApplyService().modifyKonkaXxZmdCreditApplyWithZmdResult(entity);
			msg = "1";
		} else if (StringUtils.isNotBlank(acc_id_hidden)) {
			entity.setAcc_id(Long.valueOf(acc_id_hidden));
			super.getFacade().getKonkaXxZmdCreditApplyService().modifyKonkaXxZmdCreditApplyWithZmdResult(entity);
			msg = "1";
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("msg=" + msg);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String acc_id = (String) dynaBean.get("acc_id");

		if (StringUtils.isBlank(acc_id)) {
			super.renderJavaScript(response, "alert('丢失参数！');history.back()");
			return null;
		}

		KonkaXxZmdCreditApply entity = new KonkaXxZmdCreditApply();
		entity.setAcc_id(Long.valueOf(acc_id));
		entity = getFacade().getKonkaXxZmdCreditApplyService().getKonkaXxZmdCreditApply(entity);
		super.copyProperties(form, entity);

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(entity.getDept_id());
		konkaDept = getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		dynaBean.set("dept_name", konkaDept.getDept_desc());

		KonkaXxZmd zmd = new KonkaXxZmd();
		zmd.setZmd_id(entity.getZmd_id());
		zmd = getFacade().getKonkaXxZmdService().getKonkaXxZmd(zmd);
		dynaBean.set("zmd_sn", zmd.getZmd_sn());

		return mapping.findForward("input");
	}

}
