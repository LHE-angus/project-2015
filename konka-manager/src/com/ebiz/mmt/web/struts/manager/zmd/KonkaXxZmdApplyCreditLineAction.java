package com.ebiz.mmt.web.struts.manager.zmd;

import java.math.BigDecimal;
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
import com.ebiz.mmt.domain.KonkaXxZmdCredit;
import com.ebiz.mmt.domain.KonkaXxZmdCreditApply;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Ren, zhong
 * @version 2012-04-03
 */
public class KonkaXxZmdApplyCreditLineAction extends BaseZmdAction {

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
		String zmd = request.getParameter("zmd");
		String zmd_sn_like = request.getParameter("zmd_sn_like");

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		KonkaXxZmdCreditApply entity = new KonkaXxZmdCreditApply();
		KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
		if (kDept != null)
			entity.setDept_id(kDept.getDept_id());
		entity.getMap().put("zmd_sn_like", zmd_sn_like);

		Long recordCount = super.getFacade().getKonkaXxZmdCreditApplyService().getKonkaXxZmdCreditApplyCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());
		List<KonkaXxZmdCreditApply> entityList = getFacade().getKonkaXxZmdCreditApplyService()
				.getKonkaXxZmdCreditApplyPaginatedList(entity);
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

		if ("1".equals(msg)) {
			msg = super.getMessage(request, "konka.zmd.credit.line.apply.success", new String[] { zmd });
			dynaBean.set("msg", msg);
		} else if ("2".equals(msg)) {
			msg = super.getMessage(request, "konka.zmd.credit.line.apply.update.success", new String[] { zmd });
			dynaBean.set("msg", msg);
		} else if ("3".equals(msg)) {
			msg = super.getMessage(request, "konka.zmd.credit.line.apply.delete.success");
			dynaBean.set("msg", msg);
		}

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		// DynaBean dynaBean = (DynaBean) form;

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		Long dept_id = null;
		KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
		if(kDept != null)
			dept_id = kDept.getDept_id();
		
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(dept_id);
		konkaDept = getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		request.setAttribute("konkaDept", konkaDept);

		KonkaXxZmdCredit konkaXxZmdCredit = new KonkaXxZmdCredit();// 取信用等级
		konkaXxZmdCredit.setDetp_id(dept_id);
		konkaXxZmdCredit.setIs_del(0);
		List<KonkaXxZmdCredit> konkaXxZmdCreditList = super.getFacade().getKonkaXxZmdCreditService()
				.getKonkaXxZmdCreditList(konkaXxZmdCredit);
		if (konkaXxZmdCreditList.size() > 0) {
			request.setAttribute("konkaXxZmdCreditList", konkaXxZmdCreditList);
		}

		KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
		konkaXxZmd.setDept_id(dept_id);
		konkaXxZmd.getMap().put("zmdIdNotIn", true);
		List<KonkaXxZmd> zmdList = getFacade().getKonkaXxZmdService().getKonkaXxZmdList(konkaXxZmd);

		request.setAttribute("zmdList", zmdList);

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String acc_id = (String) dynaBean.get("acc_id");

		if (StringUtils.isBlank(acc_id)) {
			super.renderJavaScript(response, "alert('参数丢失！');");
			return null;
		}
		KonkaXxZmdCreditApply entity = new KonkaXxZmdCreditApply();
		entity.setAcc_id(Long.valueOf(acc_id));
		entity = getFacade().getKonkaXxZmdCreditApplyService().getKonkaXxZmdCreditApply(entity);
		super.copyProperties(form, entity);

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		Long dept_id = null;
		KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
		if(kDept != null)
			dept_id = kDept.getDept_id();
		
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(dept_id);
		konkaDept = getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		request.setAttribute("konkaDept", konkaDept);

		KonkaXxZmdCredit konkaXxZmdCredit = new KonkaXxZmdCredit();// 取信用等级
		konkaXxZmdCredit.setDetp_id(dept_id);
		konkaXxZmdCredit.setIs_del(0);
		List<KonkaXxZmdCredit> konkaXxZmdCreditList = super.getFacade().getKonkaXxZmdCreditService()
				.getKonkaXxZmdCreditList(konkaXxZmdCredit);
		if (konkaXxZmdCreditList.size() > 0) {
			request.setAttribute("konkaXxZmdCreditList", konkaXxZmdCreditList);
		}

		KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
		konkaXxZmd.setZmd_id(entity.getZmd_id());
		konkaXxZmd = getFacade().getKonkaXxZmdService().getKonkaXxZmd(konkaXxZmd);
		dynaBean.set("zmd_sn", konkaXxZmd.getZmd_sn());
		dynaBean.set("cur_credit_line", konkaXxZmd.getCur_credit_line());
		dynaBean.set("credit_money", entity.getMoney());

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String mod_id = (String) dynaBean.get("mod_id");
		String acc_id = (String) dynaBean.get("acc_id");
		String credit_money = (String) dynaBean.get("credit_money");
		String zmd_id = (String) dynaBean.get("zmd_id");
		String credit_id = (String) dynaBean.get("credit_id");
		String msg = "";
		String zmd_sn = "";

		if (StringUtils.isNotBlank(zmd_id)) {
			KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
			konkaXxZmd.setZmd_id(Long.valueOf(zmd_id));
			konkaXxZmd.setIs_del(0);
			konkaXxZmd = getFacade().getKonkaXxZmdService().getKonkaXxZmd(konkaXxZmd);
			zmd_sn = konkaXxZmd.getZmd_sn();

			if (StringUtils.isNotBlank(acc_id)) {
				KonkaXxZmdCreditApply apply = new KonkaXxZmdCreditApply();
				apply.setZmd_id(Long.valueOf(zmd_id));
				apply.getMap().put("noAudit", 1);
				apply.getMap().put("notAccid", acc_id);
				Long count = getFacade().getKonkaXxZmdCreditApplyService().getKonkaXxZmdCreditApplyCount(apply);
				if (count != 0L) {
					String ms = super.getMessage(request, "konka.zmd.credit.line.apply.error", new String[] { zmd_sn });
					renderJavaScript(response, "alert('" + ms + "');history.back();");
					return null;
				}
			}
		}

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		KonkaXxZmdCreditApply entity = new KonkaXxZmdCreditApply();
		super.copyProperties(entity, form);

		entity.setCredit_id(Long.valueOf(credit_id));
		entity.setMoney(new BigDecimal(credit_money));
		entity.setApply_user_id(ui.getId());
		entity.setApply_username(ui.getReal_name());
		entity.setAudit_state(0);

		if (StringUtils.isBlank(acc_id)) {
			entity.setApply_date(new Date());
			super.getFacade().getKonkaXxZmdCreditApplyService().createKonkaXxZmdCreditApplyWithMessage(entity);
			msg = "1";
		} else {
			super.getFacade().getKonkaXxZmdCreditApplyService().modifyKonkaXxZmdCreditApplyWithMessage(entity);
			msg = "2";
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("msg=" + msg);
		pathBuffer.append("&").append("zmd=" + zmd_sn);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String acc_id = (String) dynaBean.get("acc_id");
		String[] pks = (String[]) dynaBean.get("pks");

		KonkaXxZmdCreditApply entity = new KonkaXxZmdCreditApply();

		if (StringUtils.isNotBlank(acc_id)) {
			entity.setAcc_id(Long.valueOf(acc_id));
			super.getFacade().getKonkaXxZmdCreditApplyService().removeKonkaXxZmdCreditApply(entity);
		} else if (ArrayUtils.isNotEmpty(pks)) {
			entity.getMap().put("pks", pks);
			super.getFacade().getKonkaXxZmdCreditApplyService().removeKonkaXxZmdCreditApply(entity);
		}

		saveMessage(request, "entity.deleted");
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("msg=3");
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
}
