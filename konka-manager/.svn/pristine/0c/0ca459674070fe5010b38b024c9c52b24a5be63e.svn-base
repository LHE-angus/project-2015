package com.ebiz.mmt.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BasePopedom;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

public class SetFgsModPopedomAction extends BaseAction {

	public static final String[] onlyForAdmin = "105040,105050,105051,105052,105053,105054,903110,903120,903130,903140,903150,903160,903170,903210,903220,903230,903240,904000,904010,904020,906000,908030,908040,908050"
	        .split(",");

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		String role_id = "10";// 系统管理员管理分公司节点授权
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
					selectedModPeopedoms = StringUtils.split("+"
					        + (String) webSysModule.getMap().get("sub_ppdm_detail"), "+");
				}
				if (null != selectedModPeopedoms) {
					request.setAttribute("mod_popedom_" + webSysModule.getMod_id(), selectedModPeopedoms);
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
			String mod_id = t.getMod_id().toString();

			if (!role_id_eq_10 && ArrayUtils.contains(onlyForAdmin, mod_id)) {
				// 非系统管理员登录后，移除 非系统管理员 不可操作的模块节点
				itr.remove();
			}
		}

		request.setAttribute("sysModuleList", sysModuleList);
		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String[] pks_mod_id = (String[]) dynaBean.get("pks");
		logger.info("pks.length--------------->{}", pks_mod_id.length);

		SysModule entity = new SysModule();
		if (null != pks_mod_id && pks_mod_id.length > 0) {
			entity.getMap().put("pks_mod_id", pks_mod_id);
		}
		entity.setFgs_auth_flag(0);// 0表示授权
		entity.setIs_public(0);
		super.getFacade().getSysModuleService().modifySysModule(entity);

		SysModule sysModule = new SysModule();
		if (null != pks_mod_id && pks_mod_id.length > 0) {
			sysModule.getMap().put("pks_mod_id_not", pks_mod_id);
		}
		sysModule.setFgs_auth_flag(1);// 1表示不授权
		sysModule.setIs_public(0);
		super.getFacade().getSysModuleService().modifySysModule(sysModule);

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward ajaxUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String c_mod_id = (String) dynaBean.get("c_mod_id");
		String state = (String) dynaBean.get("state");
		String result = "";
		SysModule entity = new SysModule();

		if (state.equals("1")) {
			entity.setFgs_auth_flag(0);// 0表示授权
		} else if (state.equals("2")) {
			entity.setFgs_auth_flag(1);// 1表示未授权
		}
		entity.getMap().put("update_by_mod_id", c_mod_id);
		super.getFacade().getSysModuleService().modifySysModule(entity);
		result = "0";
		super.renderJson(response, result);
		return null;
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

}
