package com.ebiz.mmt.web.struts.manager.zmd;

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
import com.ebiz.mmt.domain.KonkaXxZmdHdSet;
import com.ebiz.mmt.domain.KonkaXxZmdRewardSetHd;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wu,ShangLong
 *@version 2012-03-02
 */
public class KonkaXxZmdRewardSetHdAction extends BaseZmdAction {

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
		String hd_start_date = (String) dynaBean.get("hd_start_date");
		String hd_end_date = (String) dynaBean.get("hd_end_date");
		String hd_title_like = (String) dynaBean.get("hd_title_like");
		String reward_type = (String) dynaBean.get("reward_type");

		PeProdUser user_info = super.getSessionUserInfo(request);
		// PeRoleUser role_info = super.getRoleInfoByThisLogin(request);
		Long dept_id = null;
		KonkaDept kDept = getKonkaDeptForFgs(user_info.getDept_id());
		if (kDept != null)
			dept_id = kDept.getDept_id();

		List<PeRoleUser> peRoleUserList = getPeRoleList(user_info.getId());
		Boolean role_id_gt_400 = false;
		Boolean role_id_btw_300_390 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if (temp.getRole_id() >= 300 && temp.getRole_id() < 390) {
					role_id_btw_300_390 = true;
				}
			}
		}

		if (!role_id_gt_400) {
			String msg = getMessage(request, "popedom.check.invalid");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaXxZmdRewardSetHd entity = new KonkaXxZmdRewardSetHd();

		if (role_id_btw_300_390) {
			entity.getMap().put("dept_id", dept_id);

			// 分公司下面的专卖店
			KonkaXxZmd zmd = new KonkaXxZmd();
			zmd.setDept_id(dept_id);
			List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);

			request.setAttribute("zmdList", zmdList);
		} else {
			String msg = getMessage(request, "popedom.check.invalid");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		if (GenericValidator.isLong(zmd_id)) {
			entity.setZmd_id(Long.valueOf(zmd_id));
		}

		if (StringUtils.isNotBlank(hd_title_like)) {
			entity.getMap().put("hd_title_like", hd_title_like);
		}

		if (StringUtils.isNotBlank(hd_start_date)) {
			entity.getMap().put("hd_start_date", hd_start_date);
		}

		if (StringUtils.isNotBlank(hd_end_date)) {
			entity.getMap().put("hd_end_date", hd_end_date);
		}

		if (GenericValidator.isLong(reward_type)) {
			entity.setReward_type(Long.valueOf(reward_type));
		}

		Long recordCount = super.getFacade().getKonkaXxZmdRewardSetHdService()
				.getKonkaXxZmdRewardSetHdWithZmdAndHdCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaXxZmdRewardSetHd> entityList = super.getFacade().getKonkaXxZmdRewardSetHdService()
				.getKonkaXxZmdRewardSetHdWithZmdAndHdPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		// 反佣类型
		setBaseTypeListByParIdToRequest(70000L, request);
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		PeProdUser user_info = super.getSessionUserInfo(request);

		KonkaDept kDept = getKonkaDeptForFgs(user_info.getDept_id());
		if (kDept != null)
			request.setAttribute("dept_name", kDept.getDept_name());

		// 分公司下面的专卖店
		KonkaXxZmd zmd = new KonkaXxZmd();
		if (kDept != null)
			zmd.setDept_id(kDept.getDept_id());
		List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);

		request.setAttribute("zmdList", zmdList);

		// 分公司下面的活动品
		KonkaXxZmdHdSet konkaXxZmdHdSet = new KonkaXxZmdHdSet();
		if (kDept != null)
			konkaXxZmdHdSet.setDept_id(kDept.getDept_id());
		konkaXxZmdHdSet.getMap().put("activing", true);
		konkaXxZmdHdSet.getMap().put("start_date_order_asc", true);

		List<KonkaXxZmdHdSet> konkaXxZmdHdSetList = super.getFacade().getKonkaXxZmdHdSetService()
				.getKonkaXxZmdHdSetList(konkaXxZmdHdSet);
		request.setAttribute("konkaXxZmdHdSetList", konkaXxZmdHdSetList);

		// 反佣类型
		setBaseTypeListByParIdToRequest(70000L, request);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String hd_set_id = (String) dynaBean.get("hd_set_id");

		KonkaXxZmdRewardSetHd entity = new KonkaXxZmdRewardSetHd();

		super.copyProperties(entity, form);

		if (!GenericValidator.isLong(hd_set_id)) {

			KonkaXxZmdRewardSetHd temp = new KonkaXxZmdRewardSetHd();
			temp.setZmd_id(entity.getZmd_id());
			temp.setHd_id(entity.getHd_id());
			temp.setReward_type(entity.getReward_type());

			Long recordCount = super.getFacade().getKonkaXxZmdRewardSetHdService().getKonkaXxZmdRewardSetHdCount(temp);

			if (recordCount > 0L) {
				String msg = getMessage(request, "konka.xx.zmd.hd.reward.have.set");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			super.getFacade().getKonkaXxZmdRewardSetHdService().createKonkaXxZmdRewardSetHd(entity);
			saveMessage(request, "entity.inserted");
		} else {
			super.getFacade().getKonkaXxZmdRewardSetHdService().modifyKonkaXxZmdRewardSetHd(entity);
			saveMessage(request, "entity.updated");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String hd_set_id = (String) dynaBean.get("hd_set_id");
		PeProdUser user_info = super.getSessionUserInfo(request);

		KonkaDept kDept = getKonkaDeptForFgs(user_info.getDept_id());
		// 分公司下面的专卖店
		KonkaXxZmd zmd = new KonkaXxZmd();
		if (kDept != null)
			zmd.setDept_id(kDept.getDept_id());
		List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);

		request.setAttribute("zmdList", zmdList);

		// 分公司下面的活动品
		KonkaXxZmdHdSet konkaXxZmdHdSet = new KonkaXxZmdHdSet();
		if (kDept != null)
			konkaXxZmdHdSet.setDept_id(kDept.getDept_id());
		konkaXxZmdHdSet.getMap().put("activing", true);
		konkaXxZmdHdSet.getMap().put("start_date_order_asc", true);

		List<KonkaXxZmdHdSet> konkaXxZmdHdSetList = super.getFacade().getKonkaXxZmdHdSetService()
				.getKonkaXxZmdHdSetList(konkaXxZmdHdSet);
		request.setAttribute("konkaXxZmdHdSetList", konkaXxZmdHdSetList);

		// 反佣类型
		setBaseTypeListByParIdToRequest(70000L, request);

		if (kDept != null)
			request.setAttribute("dept_name", kDept.getDept_name());

		if (GenericValidator.isLong(hd_set_id)) {
			KonkaXxZmdRewardSetHd entity = new KonkaXxZmdRewardSetHd();
			entity.setHd_set_id(Long.valueOf(hd_set_id));

			entity = super.getFacade().getKonkaXxZmdRewardSetHdService().getKonkaXxZmdRewardSetHd(entity);

			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			} else {
				entity.setQueryString(super.serialize(request, "id", "method"));
				super.copyProperties(form, entity);
				return mapping.findForward("input");
			}
		} else {
			saveError(request, "errors.long", hd_set_id);
			return mapping.findForward("list");
		}
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String hd_set_id = (String) dynaBean.get("hd_set_id");

		PeProdUser user_info = super.getSessionUserInfo(request);

		if (!GenericValidator.isLong(hd_set_id)) {
			this.saveError(request, "errors.long", new String[] { hd_set_id });
			return mapping.findForward("list");
		}

		KonkaXxZmdRewardSetHd entity = new KonkaXxZmdRewardSetHd();
		entity.setHd_set_id(Long.valueOf(hd_set_id));

		entity = super.getFacade().getKonkaXxZmdRewardSetHdService().getKonkaXxZmdRewardSetHd(entity);

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		KonkaDept konkaDept = new KonkaDept();
		KonkaDept kDept = getKonkaDeptForFgs(user_info.getDept_id());
		if (kDept != null)
			konkaDept.setDept_id(kDept.getDept_id());
		List<KonkaDept> konkaDeptsList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		if (null != konkaDeptsList && konkaDeptsList.size() > 0) {
			request.setAttribute("dept_name", konkaDeptsList.get(0).getDept_name());
		}

		super.copyProperties(form, entity);

		// 专卖店
		KonkaXxZmd zmd = new KonkaXxZmd();
		zmd.setZmd_id(entity.getZmd_id());
		zmd = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(zmd);

		if (null != zmd) {
			request.setAttribute("zmd_sn", zmd.getZmd_sn());
		}

		// 分公司下面的活动品
		KonkaXxZmdHdSet konkaXxZmdHdSet = new KonkaXxZmdHdSet();
		konkaXxZmdHdSet.setHd_id(entity.getHd_id());

		konkaXxZmdHdSet = super.getFacade().getKonkaXxZmdHdSetService().getKonkaXxZmdHdSet(konkaXxZmdHdSet);

		if (null != konkaXxZmdHdSet) {
			request.setAttribute("konkaXxZmdHdSet", konkaXxZmdHdSet);
		}

		// 反佣类型
		setBaseTypeListByParIdToRequest(70000L, request);

		return mapping.findForward("view");
	}
}
