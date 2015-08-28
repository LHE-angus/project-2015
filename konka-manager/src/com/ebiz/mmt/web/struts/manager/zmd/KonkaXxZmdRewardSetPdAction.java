package com.ebiz.mmt.web.struts.manager.zmd;

import java.util.ArrayList;
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
import com.ebiz.mmt.domain.KonkaXxPd;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdRewardSetPd;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wu,ShangLong
 *@version 2012-03-02
 */
public class KonkaXxZmdRewardSetPdAction extends BaseZmdAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		String zmd_id = (String) dynaBean.get("zmd_id");
		String dept_id = (String) dynaBean.get("dept_id");
		String md_name = (String) dynaBean.get("md_name");
		String reward_type = (String) dynaBean.get("reward_type");

		PeProdUser user_info = super.getSessionUserInfo(request);
		// PeRoleUser role_info = super.getRoleInfoByThisLogin(request);
		Long dept_id_l = null;
//
//		KonkaDept kDept = getKonkaDeptForFgs(user_info.getDept_id());
//		if (kDept != null)
//			dept_id = kDept.getDept_id();

		List<PeRoleUser> peRoleUserList = getPeRoleList(user_info.getId());
//		Boolean role_id_gt_400 = false;
//		Boolean role_id_btw_300_390 = false;
//		if (peRoleUserList.size() > 0) {
//			for (PeRoleUser temp : peRoleUserList) {
//				if (temp.getRole_id() <= 400) {
//					role_id_gt_400 = true;
//				}
//				if (temp.getRole_id() >= 300 && temp.getRole_id() < 390) {
//					role_id_btw_300_390 = true;
//				}
//			}
//		}
		
		Boolean role_id_eq_400 = false;
		Boolean role_id_eq_390 = false;
		Boolean role_id_btw_300_400 = false;
		Boolean role_id_btw_200_300 = false;

		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() == 400) {
					role_id_eq_400 = true;
				}
				if (temp.getRole_id() == 390) {
					role_id_eq_390 = true;
				}
				if ((temp.getRole_id() >= 300 && temp.getRole_id() < 400)
						|| (temp.getRole_id() >= 30 && temp.getRole_id() < 40)) {
					role_id_btw_300_400 = true;
				}
				if ((temp.getRole_id() >= 200 && temp.getRole_id() < 300) || (temp.getRole_id() <= 30)) {
					role_id_btw_200_300 = true;
				}
			}
		}

		
		KonkaXxZmdRewardSetPd entity = new KonkaXxZmdRewardSetPd();
		
		// 页面角色控制 start
		if (role_id_eq_400) { // 专卖店人员
			KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();
			konkaXxZmdUsers.setUser_id(user_info.getId());

			List<KonkaXxZmdUsers> konkaXxZmdUsersList = super.getFacade().getKonkaXxZmdUsersService()
					.getKonkaXxZmdUsersList(konkaXxZmdUsers);
			if (null != konkaXxZmdUsersList && konkaXxZmdUsersList.size() > 0) {
				entity.setZmd_id(konkaXxZmdUsersList.get(0).getZmd_id());
			} else {
				String msg = getMessage(request, "konka.xx.zmd.user.not.bind.zmd");
				saveDirectlyError(request, msg);
				return mapping.findForward("list");
			}

			KonkaXxZmd zmd = new KonkaXxZmd();
			zmd.setZmd_id(konkaXxZmdUsersList.get(0).getZmd_id());
			zmd = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(zmd);

			KonkaDept kd = new KonkaDept();
			kd.setDept_id(zmd.getDept_id());
			kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
			dept_id_l = zmd.getDept_id();
			request.setAttribute("dept_name", kd.getDept_name());

			request.setAttribute("zmd_sn", zmd.getZmd_sn());

		} else if (role_id_btw_300_400) { // 分公司人员
			if (role_id_eq_390) { // 分公司业务员
				KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();
				konkaXxZmdUsers.setUser_id(user_info.getId());

				List<KonkaXxZmdUsers> konkaXxZmdUsersList = super.getFacade().getKonkaXxZmdUsersService()
						.getKonkaXxZmdUsersList(konkaXxZmdUsers);
				if (null != konkaXxZmdUsersList && konkaXxZmdUsersList.size() > 0) {
					List<Long> zmds = new ArrayList<Long>();
					for (KonkaXxZmdUsers temp : konkaXxZmdUsersList) {
						zmds.add(temp.getZmd_id());
					}

					entity.getMap().put("zmds", zmds.toArray());

					// 初始化业务员下面专卖店的下拉框
					KonkaXxZmd zmd = new KonkaXxZmd();
					zmd.getMap().put("zmds", zmds.toArray());
					List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);

					request.setAttribute("zmdList", zmdList);
				} else {
					String msg = getMessage(request, "konka.xx.zmd.user.not.bind.zmd");
					saveDirectlyError(request, msg);
					return mapping.findForward("list");
				}
			} else {
				KonkaDept kDept = getKonkaDeptForFgs(user_info.getDept_id());
				if (kDept != null)
					entity.setDept_id(kDept.getDept_id());

				// 初始化业务员下面专卖店的下拉框
				KonkaXxZmd zmd = new KonkaXxZmd();
				if (kDept != null)
					zmd.setDept_id(kDept.getDept_id());
				List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);

				request.setAttribute("zmdList", zmdList);
			}
			dept_id_l = getKonkaDeptForFgs(user_info.getDept_id()).getDept_id();
			KonkaDept kd = new KonkaDept();
			kd.setDept_id(getKonkaDeptForFgs(user_info.getDept_id()).getDept_id());
			kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);

			request.setAttribute("dept_name", kd.getDept_name());

		} else if (role_id_btw_200_300) { // 总部人员
			KonkaXxZmd zmd = new KonkaXxZmd();
			if (GenericValidator.isLong(dept_id)) {
				// 初始化业务员下面专卖店的下拉框
				zmd.setDept_id(Long.valueOf(dept_id));
				dept_id_l = Long.valueOf(dept_id);
			}
			List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);
			request.setAttribute("zmdList", zmdList);
		}
		// 页面角色控制 end

//		if (role_id_btw_300_390) {
//			entity.setDept_id(dept_id);
//
//			// 分公司下面的专卖店
//			KonkaXxZmd zmd = new KonkaXxZmd();
//			zmd.setDept_id(dept_id);
//			List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);
//
//			request.setAttribute("zmdList", zmdList);
//		}
		
		if (GenericValidator.isLong(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
		}

		if (GenericValidator.isLong(zmd_id)) {
			entity.setZmd_id(Long.valueOf(zmd_id));
		}

		if (StringUtils.isNotBlank(md_name)) {
			entity.setMd_name(md_name);
		}

		if (GenericValidator.isLong(reward_type)) {
			entity.setReward_type(Long.valueOf(reward_type));
		}

		Long recordCount = super.getFacade().getKonkaXxZmdRewardSetPdService().getKonkaXxZmdRewardSetPdWithZmdSnCount(
				entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaXxZmdRewardSetPd> entityList = super.getFacade().getKonkaXxZmdRewardSetPdService()
				.getKonkaXxZmdRewardSetPdWithZmdSnPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		// 分公司下面的产品
		KonkaXxPd konkaXxPd = new KonkaXxPd();
		konkaXxPd.setDept_id(dept_id_l);

		List<KonkaXxPd> konkaXxPdList = super.getFacade().getKonkaXxPdService().getKonkaXxPdList(konkaXxPd);
		request.setAttribute("konkaXxPdList", konkaXxPdList);
		
		request.setAttribute("konkaDepts", super.getKonkaDepts());

		// 反佣类型
		setBaseTypeListByParIdToRequest(70000L, request);
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		PeProdUser user_info = super.getSessionUserInfo(request);

		KonkaDept konka_dept = getKonkaDeptForFgs(user_info.getDept_id());
		if(konka_dept != null){
			request.setAttribute("dept_name", konka_dept.getDept_name());
		} else {
			request.setAttribute("konkaDepts", super.getKonkaDepts());
		}

		// 分公司下面的专卖店
		KonkaXxZmd zmd = new KonkaXxZmd();
		if (konka_dept != null)
			zmd.setDept_id(konka_dept.getDept_id());
		List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);

		request.setAttribute("zmdList", zmdList);

		// 分公司下面的产品
		KonkaXxPd konkaXxPd = new KonkaXxPd();
		if (konka_dept != null)
			konkaXxPd.setDept_id(konka_dept.getDept_id());

		List<KonkaXxPd> konkaXxPdList = super.getFacade().getKonkaXxPdService().getKonkaXxPdList(konkaXxPd);
		request.setAttribute("konkaXxPdList", konkaXxPdList);

		// 反佣类型
		setBaseTypeListByParIdToRequest(70000L, request);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String pd_set_id = (String) dynaBean.get("pd_set_id");
		String reward_type_temp = (String) dynaBean.get("reward_type_temp");
		String dept_id_s = (String) dynaBean.get("dept_id_s");

		PeProdUser userInfo = super.getSessionUserInfo(request);

		KonkaXxZmdRewardSetPd entity = new KonkaXxZmdRewardSetPd();

		Long dept_id = null;

		KonkaDept kDept = getKonkaDeptForFgs(userInfo.getDept_id());
		if (kDept != null)
			dept_id = kDept.getDept_id();
		if(StringUtils.isNotEmpty(dept_id_s)){
			entity.setDept_id(Long.parseLong(dept_id_s));
		} else {
			entity.setDept_id(dept_id);
		}
		super.copyProperties(entity, form);

		entity.setReward_type(Long.valueOf(reward_type_temp));

		if (!GenericValidator.isLong(pd_set_id)) {

			KonkaXxZmdRewardSetPd temp = new KonkaXxZmdRewardSetPd();
			temp.setDept_id(entity.getDept_id());
			temp.setZmd_id(entity.getZmd_id());
			temp.setMd_name(entity.getMd_name());
			temp.setReward_type(Long.valueOf(reward_type_temp));

			Long recordCount = super.getFacade().getKonkaXxZmdRewardSetPdService().getKonkaXxZmdRewardSetPdCount(temp);

			if (recordCount > 0L) {
				String msg = getMessage(request, "konka.xx.zmd.pd.reward.have.set");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			super.getFacade().getKonkaXxZmdRewardSetPdService().createKonkaXxZmdRewardSetPd(entity);
			saveMessage(request, "entity.inserted");
		} else {
			KonkaXxZmdRewardSetPd temp = new KonkaXxZmdRewardSetPd();
			temp.setDept_id(entity.getDept_id());
			temp.setZmd_id(entity.getZmd_id());
			temp.setMd_name(entity.getMd_name());
			temp.setReward_type(Long.valueOf(reward_type_temp));

			Long recordCount = super.getFacade().getKonkaXxZmdRewardSetPdService().getKonkaXxZmdRewardSetPdCount(temp);

			if (recordCount > 1L) {
				String msg = getMessage(request, "konka.xx.zmd.pd.reward.have.set");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			super.getFacade().getKonkaXxZmdRewardSetPdService().modifyKonkaXxZmdRewardSetPd(entity);
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
		String pd_set_id = (String) dynaBean.get("pd_set_id");

		PeProdUser user_info = super.getSessionUserInfo(request);

		KonkaDept konkaDept = getKonkaDeptForFgs(user_info.getDept_id());
		// 分公司下面的产品
		KonkaXxPd konkaXxPd = new KonkaXxPd();
		if (konkaDept != null)
			konkaXxPd.setDept_id(konkaDept.getDept_id());

		List<KonkaXxPd> konkaXxPdList = super.getFacade().getKonkaXxPdService().getKonkaXxPdList(konkaXxPd);
		request.setAttribute("konkaXxPdList", konkaXxPdList);

		// 反佣类型
		setBaseTypeListByParIdToRequest(70000L, request);

		// 分公司下面的专卖店
		KonkaXxZmd zmd = new KonkaXxZmd();
		if (konkaDept != null)
			zmd.setDept_id(konkaDept.getDept_id());
		List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);
		request.setAttribute("zmdList", zmdList);
		if(konkaDept != null){
			request.setAttribute("dept_name", konkaDept.getDept_name());
		} else {
			request.setAttribute("konkaDepts", super.getKonkaDepts());
		}

		if (GenericValidator.isLong(pd_set_id)) {
			KonkaXxZmdRewardSetPd entity = new KonkaXxZmdRewardSetPd();
			entity.setPd_set_id(Long.valueOf(pd_set_id));

			entity = super.getFacade().getKonkaXxZmdRewardSetPdService().getKonkaXxZmdRewardSetPd(entity);

			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			} else {
				entity.setQueryString(super.serialize(request, "id", "method"));
				super.copyProperties(form, entity);
				request.setAttribute("md_name", entity.getMd_name());
				return mapping.findForward("input");
			}
		} else {
			saveError(request, "errors.long", pd_set_id);
			return mapping.findForward("list");
		}
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String pd_set_id = (String) dynaBean.get("pd_set_id");

		PeProdUser user_info = super.getSessionUserInfo(request);
		
		Long dept_id = null;
		KonkaDept kDept = getKonkaDeptForFgs(user_info.getDept_id());
		if (kDept != null)
			dept_id = kDept.getDept_id();

		if (!GenericValidator.isLong(pd_set_id)) {
			this.saveError(request, "errors.long", new String[] { pd_set_id });
			return mapping.findForward("list");
		}

		KonkaXxZmdRewardSetPd entity = new KonkaXxZmdRewardSetPd();
		entity.setPd_set_id(Long.valueOf(pd_set_id));

		entity = super.getFacade().getKonkaXxZmdRewardSetPdService().getKonkaXxZmdRewardSetPd(entity);

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		KonkaDept konkaDept = new KonkaDept();
		if(entity != null){
			konkaDept.setDept_id(entity.getDept_id());
		}
		List<KonkaDept> konkaDeptsList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		if (null != konkaDeptsList && konkaDeptsList.size() > 0) {
			request.setAttribute("dept_name", konkaDeptsList.get(0).getDept_name());
		}

		super.copyProperties(form, entity);

		// 分公司下面的产品
		KonkaXxPd konkaXxPd = new KonkaXxPd();
		konkaXxPd.setDept_id(dept_id);

		List<KonkaXxPd> konkaXxPdList = super.getFacade().getKonkaXxPdService().getKonkaXxPdList(konkaXxPd);
		request.setAttribute("konkaXxPdList", konkaXxPdList);

		// 反佣类型
		setBaseTypeListByParIdToRequest(70000L, request);

		// 分公司下面的专卖店
		KonkaXxZmd zmd = new KonkaXxZmd();
		zmd.setZmd_id(entity.getZmd_id());
		zmd = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(zmd);
		if (null != zmd) {
			request.setAttribute("zmd_sn", zmd.getZmd_sn());
		}

		return mapping.findForward("view");
	}
}
