package com.ebiz.mmt.web.struts.manager.zmd;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxZmdPosRec;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;

/**
 * 
 * @author Hu Hao
 * @version 2012-05-02
 *
 */
public class KonkaXxZmdPosRecAction extends BaseZmdAction{

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return this.posList(mapping, form, request, response);
	}
	
	
	public ActionForward posList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);

		// 取用户角色
		PeProdUser user_info = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		Boolean role_flag = super.getRoleIdFlag(user_info.getId(), -1L, 401L);
		if (role_flag) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		return new ActionForward("/../manager/zmd/KonkaXxZmdPosRec/posList.jsp");
		//return new ActionForward("/../manager/zmd/KonkaXxZmdPosRec.do?mod_id=808500", true);
	}
	
	
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		
		String out_date = (String)dynaBean.get("out_date");
		
		KonkaXxSellBill  entity = new KonkaXxSellBill();
		if (StringUtils.isNotBlank(out_date)) {
			entity.getMap().put("add_date_ge", out_date);
			entity.getMap().put("add_date_le", out_date);
			request.setAttribute("out_date", out_date);
		}
		
		PeProdUser user_info = super.getSessionUserInfo(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(user_info.getId());

		Boolean role_id_eq_400 = false;
		Boolean role_id_gt_400 = false;
		//Boolean role_id_eq_400 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() == 400) {
					role_id_eq_400 = true;
				}
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
			}
		}
		
		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		if (role_id_eq_400) {
			KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();//取专卖店ID
			konkaXxZmdUsers.setUser_id(user_info.getId());
			List<KonkaXxZmdUsers> konkaXxZmdUsersList = super.getFacade().getKonkaXxZmdUsersService().getKonkaXxZmdUsersList(
					konkaXxZmdUsers);
			entity.setZmd_id(konkaXxZmdUsersList.get(0).getZmd_id());
			entity.setPay_way(2l);
			entity.setCheckout_pos_state(0);
			request.setAttribute("zmd_id",konkaXxZmdUsersList.get(0).getZmd_id());
			List<KonkaXxSellBill> konkaXxSellBillList= super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillList(entity);
			if(null != konkaXxSellBillList && konkaXxSellBillList.size()> 0){
			request.setAttribute("konkaXxSellBillList", konkaXxSellBillList);
			request.setAttribute("zmd_sn",konkaXxSellBillList.get(0).getZmd_sn());
			}else{
				String msg = super.getMessage(request, "konka.xx.zmd.pos.no.data");
				super.renderJavaScript(response, "window.onload=function(){alert('"+ out_date + msg + "');history.back();}");
				return null;	
			}
		}
		return mapping.findForward("input");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		
		DynaBean dynaBean = (DynaBean) form;
		String bill_ids = (String) dynaBean.get("bill_ids");
		String todo_money = (String) dynaBean.get("total");
		String zmd_id = (String) dynaBean.get("zmd_id");
		String out_date = (String) dynaBean.get("out_date"); 
		String zmd_sn = (String) dynaBean.get("zmd_sn");
		String man = (String) dynaBean.get("man");

		PeProdUser user_info = super.getSessionUserInfo(request);
		
		KonkaXxZmdPosRec entity = new KonkaXxZmdPosRec();
		super.copyProperties(entity, form);
		
		if (StringUtils.isNotBlank(todo_money)) {
			entity.setMoney(new BigDecimal(todo_money));
		}
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtils.isNotBlank(out_date)) {
			entity.setOut_date(format.parse(out_date));
		}
		entity.setZmd_id(Long.valueOf(zmd_id));
		entity.setMan(man);
		entity.getMap().put("bill_ids", bill_ids);
		entity.getMap().put("user_id",String.valueOf(user_info.getId()));
		entity.setTitle(zmd_sn+"专卖店"+out_date+"POS机刷卡账单");
		
		KonkaDept kDept = getKonkaDeptForFgs(user_info.getDept_id());
		if (kDept != null)
		entity.setDept_id(kDept.getDept_id());
		entity.setState(1);
		super.getFacade().getKonkaXxZmdPosRecService().createKonkaXxZmdPosRecForZmd(entity);
		saveMessage(request, "entity.inserted");
		return new ActionForward("/../manager/zmd/KonkaXxZmdPosForMyRec.do?mod_id=808502", true);
	}
}
