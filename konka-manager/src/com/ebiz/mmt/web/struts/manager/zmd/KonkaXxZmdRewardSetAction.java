package com.ebiz.mmt.web.struts.manager.zmd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdRewardSet;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author hu hao
 *@version 2012-1-10
 */
public class KonkaXxZmdRewardSetAction extends BaseZmdAction {

	private static String CHECK_BOX_CHECKED_VALUE = "on";

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		Pager pager = (Pager) dynaBean.get("pager");
		String zmd_sn = (String) dynaBean.get("zmd_sn");
		String is_open = (String) dynaBean.get("is_open");

		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		PeProdUser user_id = super.getSessionUserInfo(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(user_id.getId());
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
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaXxZmd entity = new KonkaXxZmd();

		if (StringUtils.isNotBlank(zmd_sn)) {
			entity.getMap().put("zmd_sn_like", zmd_sn);
		}
		if (StringUtils.isNotBlank(is_open)) {
			entity.setIs_open(Integer.valueOf(is_open));
		}
		if (role_id_btw_300_390) {
			KonkaDept kDept = getKonkaDeptForFgs(user_id.getDept_id());
			if (kDept != null)
				entity.setDept_id(kDept.getDept_id());
		}
		entity.setIs_del(Integer.valueOf(0));
		entity.setArc_state(Integer.valueOf(20300));

		Long recordCount = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaXxZmd> entityList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdPaginatedList(entity);
		if (null != entityList && entityList.size() > 0) {

			for (KonkaXxZmd temp : entityList) {
				if (temp.getP_index() != null) {// 取详细地址
					BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
					baseProvinceListFour.setP_index(temp.getP_index());
					List<BaseProvinceListFour> baseProvinceListFoursList = super.getFacade()
							.getBaseProvinceListFourService().getBaseProvinceListFourList(baseProvinceListFour);
					String p_name = baseProvinceListFoursList.get(0).getFull_name();
					temp.getMap().put("p_index_name", p_name);
				}

				KonkaXxZmdRewardSet konkaXxZmdRewardSet = new KonkaXxZmdRewardSet();// 取返佣
				konkaXxZmdRewardSet.setZmd_id(temp.getZmd_id());
				List<KonkaXxZmdRewardSet> konkaXxZmdRewardSetList = super.getFacade().getKonkaXxZmdRewardSetService()
						.getKonkaXxZmdRewardSetForTypeNameList(konkaXxZmdRewardSet);
				temp.getMap().put("konkaXxZmdRewardSetList", konkaXxZmdRewardSetList);
			}

			request.setAttribute("entityList", entityList);
		}

		setBaseTypeListByParIdToRequest(30000l, request);

		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String zmd_id = (String) dynaBean.get("zmd_id");

		setBaseTypeListByParIdToRequest(80000l, request);

		KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
		konkaXxZmd.setZmd_id(Long.valueOf(zmd_id));
		List<KonkaXxZmd> konkaXxZmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(konkaXxZmd);
		request.setAttribute("zmd_sn", konkaXxZmdList.get(0).getZmd_sn());

		KonkaXxZmdRewardSet entity = new KonkaXxZmdRewardSet();
		entity.setZmd_id(Long.valueOf(zmd_id));
		List<KonkaXxZmdRewardSet> konkaXxZmdRewardSetList = super.getFacade().getKonkaXxZmdRewardSetService()
				.getKonkaXxZmdRewardSetList(entity);
		List<Integer> list = new ArrayList<Integer>();
		for (KonkaXxZmdRewardSet set : konkaXxZmdRewardSetList) {
			dynaBean.set("reward_ratio_" + set.getReward_type(), set.getReward_ratio());
			dynaBean.set("is_enabled_" + set.getReward_type(), set.getIs_enabled() == 1 ? CHECK_BOX_CHECKED_VALUE : "");
			dynaBean.set("is_locked_" + set.getReward_type(), set.getIs_locked() == 1 ? CHECK_BOX_CHECKED_VALUE : "");
			list.add(set.getIs_locked());
		}
		request.setAttribute("list", list);
		return mapping.findForward("input");

	}

	public ActionForward ajaxSaveRewardType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String zmd_id = (String) dynaBean.get("zmd_id");
		String type_id = (String) dynaBean.get("type_id");
		String reward_ratio = (String) dynaBean.get("reward_ratio");
		String is_enabled = (String) dynaBean.get("is_enabled");
		String is_locked = (String) dynaBean.get("is_locked");

		HttpSession session = request.getSession();
		PeProdUser userInfo = new PeProdUser();
		userInfo = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		if (StringUtils.isBlank(is_enabled) || StringUtils.isBlank(is_locked) || StringUtils.isBlank(reward_ratio)) {
			super.renderText(response, "0");
			return null;
		}

		KonkaXxZmdRewardSet entity = new KonkaXxZmdRewardSet();
		entity.setZmd_id(Long.valueOf(zmd_id));
		entity.setReward_type(Long.valueOf(type_id));
		entity = super.getFacade().getKonkaXxZmdRewardSetService().getKonkaXxZmdRewardSet(entity);
		if (null == entity) {
			// insert
			KonkaXxZmdRewardSet set = new KonkaXxZmdRewardSet();
			set.setZmd_id(Long.valueOf(zmd_id));
			set.setReward_type(Long.valueOf(type_id));
			set.setReward_ratio(new BigDecimal(reward_ratio));
			set.setIs_enabled(Integer.valueOf(is_enabled));
			set.setIs_locked(Integer.valueOf(is_locked));

			KonkaDept kDept = getKonkaDeptForFgs(userInfo.getDept_id());
			if (kDept != null)
				set.setDept_id(kDept.getDept_id());
			set.setSet_user_id(userInfo.getId());
			set.setUpdate_user_id(userInfo.getId());
			super.getFacade().getKonkaXxZmdRewardSetService().createKonkaXxZmdRewardSet(set);
		} else {
			// update
			entity.setZmd_id(Long.valueOf(zmd_id));
			entity.setReward_type(Long.valueOf(type_id));
			entity.setReward_ratio(new BigDecimal(reward_ratio));
			entity.setIs_enabled(Integer.valueOf(is_enabled));
			entity.setIs_locked(Integer.valueOf(is_locked));

			entity.setLast_update_time(new Date());
			entity.setUpdate_user_id(userInfo.getId());
			super.getFacade().getKonkaXxZmdRewardSetService().modifyKonkaXxZmdRewardSet(entity);
		}

		super.renderText(response, "1");
		return null;
	}

	public ActionForward ajaxOpenRewardType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String zmd_id = (String) dynaBean.get("zmd_id");
		String type_id = (String) dynaBean.get("type_id");

		HttpSession session = request.getSession();
		PeProdUser userInfo = new PeProdUser();
		userInfo = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		KonkaXxZmdRewardSet entity = new KonkaXxZmdRewardSet();
		entity.setZmd_id(Long.valueOf(zmd_id));
		entity.setReward_type(Long.valueOf(type_id));
		entity = super.getFacade().getKonkaXxZmdRewardSetService().getKonkaXxZmdRewardSet(entity);
		if (entity != null) {
			entity.setIs_locked(Integer.valueOf(0));
			entity.setLast_update_time(new Date());
			entity.setUpdate_user_id(userInfo.getId());
			super.getFacade().getKonkaXxZmdRewardSetService().modifyKonkaXxZmdRewardSet(entity);
			super.renderText(response, "1");
		} else {
			super.renderText(response, "0");
			return null;
		}
		return null;
	}
}
