package com.ebiz.mmt.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BasePopedom;
import com.ebiz.mmt.domain.ModPopedom;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Xu,ZhengYang
 * @author ZHOU
 * @version 2014/02/13
 */
public class SetModPopedomAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String role_id = (String) dynaBean.get("role_id");
		List<SysModule> sysModuleList = null;

		// 获取登录用户 企业信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		if (StringUtils.isNotBlank(role_id)) {// 角色授权
			sysModuleList = getSysModuleForRoleList(role_id, 0L);
		} else {
			PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
			sysModuleList = getSysModuleList(peRoleUser.getRole_id(), peProdUser.getId());
		}

		if (null == sysModuleList) {
			return super.invalidOper(request, response, "entity.empty");
		}

		BasePopedom basePopedom = new BasePopedom();
		basePopedom.setIs_base(1);
		List<BasePopedom> basePopedomList = super.getFacade().getBasePopedomService().getBasePopedomList(basePopedom);

		for (SysModule sysmodule : sysModuleList) {
			if (null != sysmodule.getMod_url()) {
				List<BasePopedom> basePopedomList1 = new ArrayList<BasePopedom>();
				String[] webModPeopedoms = null;
				String ppdm_detail = (String) sysmodule.getMap().get("ppdm_detail");
				webModPeopedoms = StringUtils.split(ppdm_detail, "+");
				for (BasePopedom basePopedom1 : basePopedomList) {
					if (ArrayUtils.contains(webModPeopedoms, basePopedom1.getPpdm_code().toString())) {
						basePopedomList1.add(basePopedom1);
					}
				}
				sysmodule.setBasePopedomList(basePopedomList1);

				String[] selectedModPeopedoms = null;
				if (null != sysmodule.getMap().get("sub_ppdm_detail")) {
					selectedModPeopedoms = StringUtils.split("+" + (String) sysmodule.getMap().get("sub_ppdm_detail"), "+");
				}
				if (null != selectedModPeopedoms) {
					request.setAttribute("mod_popedom_" + sysmodule.getMod_id(), selectedModPeopedoms);
				}
			}
		}

		Boolean role_id_eq_10 = false;
		PeRoleUser pUser = new PeRoleUser();
		pUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> pUserList = super.getFacade().getPeRoleUserService().getPeRoleUserList(pUser);
		if (pUserList.size() > 0) {
			for (PeRoleUser pu : pUserList) {
				if (pu.getRole_id() == 10) {
					role_id_eq_10 = true;
				}
			}
		}

		Iterator<SysModule> itr = sysModuleList.iterator();
		while (itr.hasNext()) {
			SysModule t = itr.next();
			if (!role_id_eq_10 && t.getFgs_auth_flag() == 1) {
				// fgs_auth_flag 为 0 表示是，1表示否
				// 非系统管理员登录后，移除 非系统管理员 不可操作的模块节点
				itr.remove();
			}
		}

		request.setAttribute("sysModuleList", sysModuleList);
		if(role_id_eq_10){
			request.setAttribute("role_id_eq_10", 1);
		}else{
			request.setAttribute("role_id_eq_10", 0);
		}
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		String mod_id = (String) dynaBean.get("mod_id");
		String role_id = (String) dynaBean.get("role_id");
		String user_id = (String) dynaBean.get("user_id");
		String url = (String) dynaBean.get("url");

		List<SysModule> sysModuleList = null;

		if (StringUtils.isNotBlank(role_id)) {// 角色授权
			sysModuleList = getSysModuleList(new Integer(role_id));
		} else {
			// 获取登录用户 企业信息
			PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

			// PeRoleUser peRoleUser = new PeRoleUser();
			// peRoleUser.setUser_id(peProdUser.getId());
			// List<PeRoleUser> peRoleUserList =
			// super.getFacade().getPeRoleUserService().getPeRoleUserList(peRoleUser);
			// if (null == peRoleUserList || peRoleUserList.size() > 1) {
			// super.renderJavaScript(response,
			// "alert('当前登录用户没有设置职务或设置了多个职务!');history.back();");
			// return null;
			// }

			PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);

			sysModuleList = getSysModuleList(peRoleUser.getRole_id(), peProdUser.getId());
		}

		if (null == sysModuleList) {
			return super.invalidOper(request, response, "entity.empty");
		}

		ModPopedom entity = new ModPopedom();
		if (StringUtils.isNotBlank(role_id)) {
			entity.setRole_id(new Long(role_id));
		} else if (StringUtils.isNotBlank(user_id)) {
			entity.setUser_id(new Long(user_id));
		}

		Set<String> modIdSet = new HashSet<String>();
		List<ModPopedom> modPopedomList = new ArrayList<ModPopedom>();
		int listIndex = -1;

		for (SysModule sysModule : sysModuleList) {
			String _mod_id = sysModule.getMod_id().toString();
			String parameterName = "checkbox_" + _mod_id;
			String[] selectedModPeopedoms = request.getParameterValues(parameterName);

			if (null != selectedModPeopedoms) {
				if (modIdSet.add(_mod_id)) {
					ModPopedom modPopedom = new ModPopedom();
					Integer popedomSum = 0;
					for (int i = 0; i < selectedModPeopedoms.length; i++) {
						Integer popedom = Integer.valueOf(selectedModPeopedoms[i]);
						popedomSum += popedom;
					}
					modPopedom.setPpdm_code(popedomSum);
					modPopedom.setMod_id(new Long(_mod_id));

					modPopedomList.add(modPopedom);
					listIndex++;
				} else {
					Integer popedomSum = 0;
					for (int i = 0; i < selectedModPeopedoms.length; i++) {
						Integer popedom = Integer.valueOf(selectedModPeopedoms[i]);
						popedomSum += popedom;
					}

					ModPopedom modPopedom = modPopedomList.get(listIndex);
					if (null != modPopedom) {
						if (popedomSum > modPopedom.getPpdm_code()) {
							modPopedom.setPpdm_code(popedomSum);
						}
					}
				}
			}
		}

		entity.setModPopedomList(modPopedomList);
		super.getFacade().getModPopedomService().createModPopedom(entity);

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		String msg = super.getMessage(request, "entity.updated");

		if (String.valueOf(ui.getId()).equals(user_id)) {
			super.renderJavaScript(response, "alert('" + msg + "');location.href='" + url + ".do?mod_id=" + mod_id
					+ "';window.parent.frames['leftFrame'].location.reload(true);");
		} else {
			super.renderJavaScript(response, "alert('" + msg + "');location.href='" + url + ".do?mod_id=" + mod_id + "';");
		}
		return null;
	}

	private List<SysModule> getSysModuleList(Integer role_id) {
		SysModule sysModule = new SysModule();
		sysModule.setIs_del(0);
		sysModule.setIs_public(0);
		sysModule.getMap().put("par_id", 0);

		// if (role_id == 188) {// 促销员职务
		// sysModule.getMap().put("is_cxy", "true");
		// } else if (role_id > 199 && role_id < 1000) {// 专卖店职务
		// sysModule.getMap().put("is_zmd", "true");
		// } else if (role_id >= 1000 && role_id <= 1100) {// 完美终端职务
		// sysModule.getMap().put("is_pec", "true");
		// } else {// 什么都不是，正常的渠道职务
		// sysModule.getMap().put("is_nothing", "true");
		// }

		List<SysModule> sysModuleList = null;
		sysModuleList = getFacade().getSysModuleService().getSysModuleListForModPopedom(sysModule);

		return sysModuleList;
	}

	private List<SysModule> getSysModuleForRoleList(String role_id, Long is_view) {
		SysModule sysModule = new SysModule();
		sysModule.setIs_del(0);
		sysModule.setIs_public(0);
		sysModule.getMap().put("par_id", 0);
		sysModule.getMap().put("role_id", role_id);

		if (null != is_view) {
			// sysModule.setIs_view(1);
		}

		List<SysModule> sysModuleList = null;
		sysModuleList = getFacade().getSysModuleService().getSysModuleListForModPopedom(sysModule);

		return sysModuleList;
	}

	private List<SysModule> getSysModuleForRoleList(String role_id) {
		SysModule sysModule = new SysModule();
		sysModule.setIs_del(0);
		sysModule.setIs_public(0);
		sysModule.getMap().put("par_id", 0);
		sysModule.getMap().put("role_id", role_id);

		List<SysModule> sysModuleList = null;
		sysModuleList = getFacade().getSysModuleService().getSysModuleListForModPopedom(sysModule);

		return sysModuleList;
	}

	/**
	 * @desc 根据当前登录用户的角色及用户权限设置
	 */
	private List<SysModule> getSysModuleList(Long role_id, Long user_id) {
		SysModule sysModule = new SysModule();
		sysModule.getMap().put("role_id", role_id);
		sysModule.getMap().put("user_id", user_id);

		List<SysModule> sysModuleList = null;
		sysModuleList = getFacade().getSysModuleService().getSysModuleListForChildModPopedom(sysModule);

		return sysModuleList;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String role_id = (String) dynaBean.get("role_id");

		List<SysModule> sysModuleList = null;

		if (StringUtils.isNotBlank(role_id)) {// 角色授权
			sysModuleList = getSysModuleForRoleList(role_id);
		} else {

			// 获取登录用户 企业信息
			PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

			// PeRoleUser peRoleUser = new PeRoleUser();
			// peRoleUser.setUser_id(peProdUser.getId());
			// List<PeRoleUser> peRoleUserList =
			// super.getFacade().getPeRoleUserService().getPeRoleUserList(peRoleUser);
			// if (null == peRoleUserList || peRoleUserList.size() > 1) {
			// super.renderJavaScript(response,
			// "alert('当前登录用户没有设置职务或设置了多个职务!');history.back();");
			// return null;
			// }

			PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);

			sysModuleList = getSysModuleList(peRoleUser.getRole_id(), peProdUser.getId());
		}

		if (null == sysModuleList) {
			return super.invalidOper(request, response, "entity.empty");
		}

		BasePopedom basePopedom = new BasePopedom();
		basePopedom.setIs_base(1);
		List<BasePopedom> basePopedomList = super.getFacade().getBasePopedomService().getBasePopedomList(basePopedom);

		for (SysModule webSysModule : sysModuleList) {
			if (null != webSysModule.getMod_url()) {
				List<BasePopedom> basePopedomList1 = new ArrayList<BasePopedom>();
				String[] webModPeopedoms = null;
				String ppdm_detail = (String) webSysModule.getMap().get("ppdm_detail");
				webModPeopedoms = StringUtils.split(ppdm_detail, "+");
				for (BasePopedom basePopedom1 : basePopedomList) {
					if (ArrayUtils.contains(webModPeopedoms, basePopedom1.getPpdm_code().toString())) {
						basePopedomList1.add(basePopedom1);
					}
				}
				webSysModule.setBasePopedomList(basePopedomList1);

				String[] selectedModPeopedoms = null;
				if (null != webSysModule.getMap().get("sub_ppdm_detail")) {
					selectedModPeopedoms = StringUtils.split("+" + (String) webSysModule.getMap().get("sub_ppdm_detail"), "+");
				}
				if (null != selectedModPeopedoms) {
					request.setAttribute("mod_popedom_" + webSysModule.getMod_id(), selectedModPeopedoms);
				}
			}
		}
		request.setAttribute("sysModuleList", sysModuleList);
		return mapping.findForward("view");
	}
}
