package com.ebiz.mmt.web.struts.manager.zmd;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxSellBillDetails;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdAccouts;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu, Hao
 *@version 2012-3-20
 */
public class KonkaXxZmdAccoutsSearchAction extends BaseZmdAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return this.accoutList(mapping, form, request, response);
	}

	public ActionForward accoutList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PeProdUser user_id = super.getSessionUserInfo(request);

		List<PeRoleUser> peRoleUserList = getPeRoleList(user_id.getId());

		Boolean role_id_gt_400 = false;
		Boolean role_id_eq_400 = false;
		Boolean role_id_btw_200_300 = false;
		Boolean role_id_btw_300_400 = false;

		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
			}
		}

		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.property.exist");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		String mod_id = "808009";
		if (role_id_eq_400) {
			return new ActionForward("/zmd/KonkaXxZmdAccoutsSearch.do?method=accoutsList&mod_id=" + mod_id, true);
		}
		if (role_id_btw_300_400) {
			return new ActionForward("/zmd/KonkaXxZmdAccoutsSearch.do?method=zmdList&mod_id=" + mod_id, true);
		}
		if (role_id_btw_200_300) {
			return new ActionForward("/zmd/KonkaXxZmdAccoutsSearch.do?method=deptList&mod_id=" + mod_id, true);
		}
		return null;
	}

	public ActionForward accoutsList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String zmd_id = (String) dynaBean.get("zmd_id");
		String add_date_ge = (String) dynaBean.get("add_date_ge");
		String add_date_le = (String) dynaBean.get("add_date_le");

		Pager pager = (Pager) dynaBean.get("pager");
		PeProdUser user_id = super.getSessionUserInfo(request);
		KonkaXxZmdAccouts entity = new KonkaXxZmdAccouts();

		if (StringUtils.isNotBlank(add_date_ge)) {
			entity.getMap().put("add_date_ge", add_date_ge);
		}
		if (StringUtils.isNotBlank(add_date_le)) {
			entity.getMap().put("add_date_le", add_date_le);
		}
		if (StringUtils.isNotBlank(zmd_id)) {
			entity.setZmd_id(Long.valueOf(zmd_id));
		} else {
			KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();
			konkaXxZmdUsers.setUser_id(user_id.getId());
			List<KonkaXxZmdUsers> konkaXxZmdUsersList = super.getFacade().getKonkaXxZmdUsersService()
					.getKonkaXxZmdUsersList(konkaXxZmdUsers);
			if (null != konkaXxZmdUsersList && konkaXxZmdUsersList.size() > 0) {
				entity.setZmd_id(konkaXxZmdUsersList.get(0).getZmd_id());
			}
		}
		Long recordCount = super.getFacade().getKonkaXxZmdAccoutsService().getKonkaXxZmdAccoutsCount(entity);

		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaXxZmdAccouts> entityList = super.getFacade().getKonkaXxZmdAccoutsService().getKonkaXxZmdAccoutsList(
				entity);

		if (null != entityList && entityList.size() > 0) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			for (KonkaXxZmdAccouts temp : entityList) {
				temp.getMap().put("start_time1", format.format(temp.getStart_time()));
				temp.getMap().put("end_time1", format.format(temp.getEnd_time()));
			}
		}

		request.setAttribute("entityList", entityList);
		return new ActionForward("/../manager/zmd/KonkaXxZmdAccoutsSearch/accoutsList.jsp");
	}

	public ActionForward zmdList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String dept_id = (String) dynaBean.get("dept_id");
		Pager pager = (Pager) dynaBean.get("pager");
		PeProdUser user_id = super.getSessionUserInfo(request);
		KonkaXxZmd entity = new KonkaXxZmd();
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
			// entity.setDept_id(Long.valueOf("100000" + dept_id));
		} else {
			KonkaDept kDept = getKonkaDeptForFgs(user_id.getDept_id());
			if (kDept != null)
				entity.setDept_id(kDept.getDept_id());
		}
		Long recordCount = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdCount(entity);

		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaXxZmd> entityList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(entity);

		request.setAttribute("entityList", entityList);
		return new ActionForward("/../manager/zmd/KonkaXxZmdAccoutsSearch/zmdList.jsp");
	}

	public ActionForward deptList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		KonkaDept entity = new KonkaDept();
		entity.setDept_type(3);
		entity.setPar_id(0L);

		Long recordCount = super.getFacade().getKonkaDeptService().getKonkaDeptCount(entity);

		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaDept> entityList = super.getFacade().getKonkaDeptService().getKonkaDeptPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		return new ActionForward("/../manager/zmd/KonkaXxZmdAccoutsSearch/deptList.jsp");
	}

	public ActionForward billSellList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String bill_id = (String) dynaBean.get("bill_id");

		KonkaXxZmdAccouts acc = new KonkaXxZmdAccouts();
		acc.setBill_id(Long.valueOf(bill_id));
		acc = super.getFacade().getKonkaXxZmdAccoutsService().getKonkaXxZmdAccouts(acc);
		request.setAttribute("acc", acc);

		KonkaXxSellBill entity = new KonkaXxSellBill();

		String start = DateFormatUtils.format(acc.getStart_time(), "yyyy-MM-dd HH:mm:ss");
		String end = DateFormatUtils.format(acc.getEnd_time(), "yyyy-MM-dd HH:mm:ss");
		entity.getMap().put("remit_date_ge", start);
		entity.getMap().put("remit_date_le", end);

		entity.setZmd_id(acc.getZmd_id());
		entity.setCz_state(1);

		List<KonkaXxSellBill> entityList = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillList(entity);
		for (KonkaXxSellBill temp : entityList) {
			KonkaXxSellBillDetails konkaXxSellBillDetails = new KonkaXxSellBillDetails();
			konkaXxSellBillDetails.setSell_bill_id(temp.getSell_bill_id());
			List<KonkaXxSellBillDetails> konkaXxSellBillDetailsList = super.getFacade()
					.getKonkaXxSellBillDetailsService().getKonkaXxSellBillDetailsList(konkaXxSellBillDetails);
			if (null != konkaXxSellBillDetailsList && konkaXxSellBillDetailsList.size() > 0) {
				temp.getMap().put("konkaXxSellBillDetailsList", konkaXxSellBillDetailsList);
			}
		}
		request.setAttribute("entityList", entityList);
		return new ActionForward("/../manager/zmd/KonkaXxZmdAccoutsSearch/billSellList.jsp");
	}
}
