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
import com.ebiz.mmt.domain.KonkaXxZmdRemitRec;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * 
 * @author Hu Hao
 * @version 2012-03-27
 */
public class KonkaXxZmdRemitForZmdAction extends BaseZmdAction {

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

		PeProdUser user_info = super.getSessionUserInfo(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(user_info.getId());

		Boolean role_id_gt_400 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
			}
		}
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		Pager pager = (Pager) dynaBean.get("pager");
		String done_date_ge = (String) dynaBean.get("done_date_ge");
		String done_date_le = (String) dynaBean.get("done_date_le");
		String state = (String) dynaBean.get("state");
		String zmd_id = (String) dynaBean.get("zmd_id");

		KonkaXxZmdRemitRec entity = new KonkaXxZmdRemitRec();

		if (StringUtils.isNotBlank(done_date_ge)) {
			entity.getMap().put("done_date_ge", done_date_ge);
		}
		if (StringUtils.isNotBlank(done_date_le)) {
			entity.getMap().put("done_date_le", done_date_le);
		}
		if (StringUtils.isNotBlank(state)) {
			entity.setState(Integer.valueOf(state));
		} else {
			entity.setState(1);
		}
		if (StringUtils.isNotBlank(zmd_id)) {
			entity.setZmd_id(Long.valueOf(zmd_id));
		}

		KonkaDept kDept = getKonkaDeptForFgs(user_info.getDept_id());
		if (kDept != null)
			entity.setDept_id(kDept.getDept_id());

		KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
		if (kDept != null)
			konkaXxZmd.setDept_id(kDept.getDept_id());
		List<KonkaXxZmd> konkaXxZmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(konkaXxZmd);
		request.setAttribute("konkaXxZmdList", konkaXxZmdList);

		Long recordCount = super.getFacade().getKonkaXxZmdRemitRecService().getKonkaXxZmdRemitRecForZmdSnCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaXxZmdRemitRec> entityList = super.getFacade().getKonkaXxZmdRemitRecService()
				.getKonkaXxZmdRemitRecForPaZmdSnginatedList(entity);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String remit_rec_id = (String) dynaBean.get("remit_rec_id");

		KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
		konkaXxSellBill.setRemit_rec_id(Long.valueOf(remit_rec_id));
		List<KonkaXxSellBill> konkaXxSellBillList = super.getFacade().getKonkaXxSellBillService()
				.getKonkaXxSellBillList(konkaXxSellBill);

		request.setAttribute("konkaXxSellBillList", konkaXxSellBillList);
		request.setAttribute("zmd_sn", konkaXxSellBillList.get(0).getZmd_sn());

		KonkaXxZmdRemitRec entity = new KonkaXxZmdRemitRec();
		entity.setRemit_rec_id(Long.valueOf(remit_rec_id));

		entity = getFacade().getKonkaXxZmdRemitRecService().getKonkaXxZmdRemitRec(entity);
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
		String remit_rec_id = (String) dynaBean.get("remit_rec_id");
		String state = (String) dynaBean.get("state");

		if (state.equals("2")) {
			KonkaXxZmdRemitRec entity = new KonkaXxZmdRemitRec();
			PeProdUser user_info = super.getSessionUserInfo(request);

			entity.setConfirm_date(new Date());
			entity.setState(2);
			entity.setRemit_rec_id(Long.valueOf(remit_rec_id));
			entity.setConfirm_user_id(user_info.getId());
			entity.setConfirm_date(new Date());

			super.getFacade().getKonkaXxZmdRemitRecService().modifyKonkaXxZmdRemitRecForZmd(entity);
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
		String remit_rec_id = (String) dynaBean.get("remit_rec_id");

		if (!GenericValidator.isLong(remit_rec_id)) {
			this.saveError(request, "errors.long", new String[] { remit_rec_id });
			return mapping.findForward("list");
		}

		KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
		konkaXxSellBill.setRemit_rec_id(Long.valueOf(remit_rec_id));
		List<KonkaXxSellBill> konkaXxSellBillList = super.getFacade().getKonkaXxSellBillService()
				.getKonkaXxSellBillList(konkaXxSellBill);
		request.setAttribute("konkaXxSellBillList", konkaXxSellBillList);

		KonkaXxZmdRemitRec entity = new KonkaXxZmdRemitRec();
		entity.setRemit_rec_id(Long.valueOf(remit_rec_id));
		entity = super.getFacade().getKonkaXxZmdRemitRecService().getKonkaXxZmdRemitRec(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		super.copyProperties(form, entity);
		return mapping.findForward("view");
	}

}
