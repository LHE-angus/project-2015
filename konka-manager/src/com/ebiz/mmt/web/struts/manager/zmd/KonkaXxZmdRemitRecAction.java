package com.ebiz.mmt.web.struts.manager.zmd;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxZmdRemitRec;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * 
 * @author Hu Hao
 * @version 2012-03-22
 */
public class KonkaXxZmdRemitRecAction extends BaseZmdAction {
	
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return this.list(mapping, form, request, response);
	}
	

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
		//PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		Pager pager = (Pager) dynaBean.get("pager");
		String plan_date_ge = (String) dynaBean.get("plan_date_ge");
		String plan_date_le = (String) dynaBean.get("plan_date_le");
		KonkaXxZmdRemitRec entity = new KonkaXxZmdRemitRec();

		if (StringUtils.isNotBlank(plan_date_ge)) {
			entity.getMap().put("plan_date_ge", plan_date_ge);
		}
		if (StringUtils.isNotBlank(plan_date_le)) {
			entity.getMap().put("plan_date_le", plan_date_le);
		}
		
		KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();//取专卖店ID
		konkaXxZmdUsers.setUser_id(user_info.getId());
		List<KonkaXxZmdUsers> konkaXxZmdUsersList = super.getFacade().getKonkaXxZmdUsersService().getKonkaXxZmdUsersList(
				konkaXxZmdUsers);
		if(0 == konkaXxZmdUsersList.size()){
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		entity.setZmd_id(konkaXxZmdUsersList.get(0).getZmd_id());
		entity.setState(0);
		Long recordCount = super.getFacade().getKonkaXxZmdRemitRecService().getKonkaXxZmdRemitRecCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
		List<KonkaXxZmdRemitRec> entityList = super.getFacade().getKonkaXxZmdRemitRecService().getKonkaXxZmdRemitRecPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}
	

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String remit_rec_id = (String) dynaBean.get("remit_rec_id");

		KonkaXxSellBill  konkaXxSellBill = new KonkaXxSellBill();
		konkaXxSellBill.setRemit_rec_id(Long.valueOf(remit_rec_id));
		List<KonkaXxSellBill> konkaXxSellBillList = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillList(konkaXxSellBill);
		
		request.setAttribute("konkaXxSellBillList", konkaXxSellBillList);
		
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
	

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		String remit_sn = (String) dynaBean.get("remit_sn");
		String man = (String) dynaBean.get("man");
		String remit_date = (String) dynaBean.get("remit_date");
		String remit_rec_id = (String) dynaBean.get("remit_rec_id");
		String total_money = (String) dynaBean.get("total_money");
		
		KonkaXxZmdRemitRec entity = new KonkaXxZmdRemitRec();
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtils.isNotBlank(remit_date)) {
			entity.setRemit_date(format.parse(remit_date));
		}
		if (StringUtils.isNotBlank(remit_sn)) {
			entity.setRemit_sn(remit_sn);
		}
		if (StringUtils.isNotBlank(man)) {
			entity.setMan(man);
		}
		if (StringUtils.isNotBlank(total_money)) {
			entity.setTotal_money(new BigDecimal(total_money));
		}
		entity.setDone_date(new Date());
		entity.setState(1);
		
		entity.setRemit_rec_id(Long.valueOf(remit_rec_id));
		super.getFacade().getKonkaXxZmdRemitRecService().modifyKonkaXxZmdRemitRec(entity);
		saveMessage(request, "entity.updated");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
}
