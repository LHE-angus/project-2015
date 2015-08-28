package com.ebiz.mmt.web.struts.manager.zmd;

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

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxZmdHdSet;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author hu hao
 *@version 2012-3-2
 */
public class KonkaXxZmdHdSetAction extends BaseZmdAction {
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
		String hd_title_like = (String) dynaBean.get("hd_title_like");

		KonkaXxZmdHdSet entity = new KonkaXxZmdHdSet();

		if (StringUtils.isNotBlank(hd_title_like)) {
			entity.getMap().put("hd_title_like", hd_title_like);
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
				if (temp.getRole_id() >= 300 && temp.getRole_id() < 400) {
					role_id_btw_300_400 = true;
				}
			}
		}

		Long dept_id = null;
		KonkaDept kDept = getKonkaDeptForFgs(user_id.getDept_id());
		if (kDept != null)
			dept_id = kDept.getDept_id();

		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		if (role_id_btw_300_400) {
			entity.setDept_id(dept_id);
		}

		Long recordCount = super.getFacade().getKonkaXxZmdHdSetService().getKonkaXxZmdHdSetCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaXxZmdHdSet> entityList = super.getFacade().getKonkaXxZmdHdSetService()
				.getKonkaXxZmdHdSetPaginatedList(entity);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		Long now_date = Long.valueOf(format.format(new Date()));

		if (entityList.size() > 0) {
			for (KonkaXxZmdHdSet temp : entityList) {
				if (now_date < Long.valueOf(format.format(temp.getStart_date()))) {
					temp.getMap().put("counts", "0");
				} else {
					DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
					konkaXxSellBill.setDept_id(dept_id);
					if (now_date < Long.valueOf(format.format(temp.getEnd_date()))) {
						konkaXxSellBill.getMap().put("start_date", fmt.format(temp.getStart_date()));
						konkaXxSellBill.getMap().put("end_date", fmt.format(new Date()));
					} else {
						konkaXxSellBill.getMap().put("start_date", fmt.format(temp.getStart_date()));
						konkaXxSellBill.getMap().put("end_date", fmt.format(temp.getEnd_date()));
					}
					String counts = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillTotalMoneySumHd(
							konkaXxSellBill);
					temp.getMap().put("counts", counts);
				}
			}
		}
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		PeProdUser user_id = super.getSessionUserInfo(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(user_id.getId());

		Boolean role_id_gt_400 = false;
		Boolean role_id_btw_300_400 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if (temp.getRole_id() >= 300 && temp.getRole_id() < 400) {
					role_id_btw_300_400 = true;
				}
			}
		}
		// 取用户角色
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		if (role_id_btw_300_400) {
			KonkaDept kDept = getKonkaDeptForFgs(user_id.getDept_id());
			if (kDept != null) {
				request.setAttribute("dept_id", kDept.getDept_id());
				request.setAttribute("dept_name", kDept.getDept_name());
			}
		}
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		String hd_title = (String) dynaBean.get("hd_title");
		String hd_contend = (String) dynaBean.get("hd_contend");
		String start_date = (String) dynaBean.get("start_date");
		String end_date = (String) dynaBean.get("end_date");
		String start_date_hh = (String) dynaBean.get("start_date_hh");
		String start_date_mm = (String) dynaBean.get("start_date_mm");
		String end_date_hh = (String) dynaBean.get("end_date_hh");
		String end_date_mm = (String) dynaBean.get("end_date_mm");
		PeProdUser userInfo = super.getSessionUserInfo(request);

		String sd = start_date + " " + start_date_hh + ":" + start_date_mm + ":00";
		String ed = end_date + " " + end_date_hh + ":" + end_date_mm + ":00";

		KonkaXxZmdHdSet entity = new KonkaXxZmdHdSet();
		entity.setHd_title(hd_title);

		if (StringUtils.isNotBlank(hd_contend)) {
			entity.setHd_contend(hd_contend);
		}

		Long dept_id = null;
		KonkaDept kDept = getKonkaDeptForFgs(userInfo.getDept_id());
		if (kDept != null)
			dept_id = kDept.getDept_id();
		
		entity.setDept_id(dept_id);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (StringUtils.isNotBlank(start_date) && StringUtils.isNotBlank(end_date)) {
			KonkaXxZmdHdSet konkaXxZmdHdSet = new KonkaXxZmdHdSet();
			konkaXxZmdHdSet.setDept_id(dept_id);
			List<KonkaXxZmdHdSet> konkaXxZmdHdSetList = super.getFacade().getKonkaXxZmdHdSetService()
					.getKonkaXxZmdHdSetList(konkaXxZmdHdSet);
			if (null != konkaXxZmdHdSetList && konkaXxZmdHdSetList.size() > 0) {
				for (KonkaXxZmdHdSet temp : konkaXxZmdHdSetList) {

					if (hd_title.equals(temp.getHd_title())) {
						String msg = super.getMessage(request, "konka.xx.zmd.hd.title.exist");
						super.renderJavaScript(response, "window.onload=function(){alert('" + msg
								+ "');history.back();}");
						return null;
					}
					// 验证时间段
					if (format.parse(sd).getTime() >= temp.getStart_date().getTime()
							&& format.parse(sd).getTime() <= temp.getEnd_date().getTime()) {
						String msg = super.getMessage(request, "konka.xx.zmd.hd.date.error");
						super.renderJavaScript(response, "window.onload=function(){alert('" + msg
								+ "');history.back();}");
						return null;
					} else if (format.parse(ed).getTime() >= temp.getStart_date().getTime()
							&& format.parse(ed).getTime() <= temp.getEnd_date().getTime()) {
						String msg = super.getMessage(request, "konka.xx.zmd.hd.date.error");
						super.renderJavaScript(response, "window.onload=function(){alert('" + msg
								+ "');history.back();}");
						return null;
					} else if (format.parse(sd).getTime() <= temp.getStart_date().getTime()
							&& format.parse(ed).getTime() >= temp.getEnd_date().getTime()) {
						String msg = super.getMessage(request, "konka.xx.zmd.hd.date.error");
						super.renderJavaScript(response, "window.onload=function(){alert('" + msg
								+ "');history.back();}");
						return null;
					} else if (format.parse(sd).getTime() > format.parse(ed).getTime()) {
						String msg = super.getMessage(request, "konka.xx.zmd.hd.date.error");
						super.renderJavaScript(response, "window.onload=function(){alert('" + msg
								+ "');history.back();}");
						return null;
					} else {
						entity.setStart_date(format.parse(sd));
						entity.setEnd_date(format.parse(ed));
					}
				}
			} else {
				entity.setStart_date(format.parse(sd));
				entity.setEnd_date(format.parse(ed));
			}
		}

		super.getFacade().getKonkaXxZmdHdSetService().createKonkaXxZmdHdSet(entity);
		saveMessage(request, "entity.inserted");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
}
