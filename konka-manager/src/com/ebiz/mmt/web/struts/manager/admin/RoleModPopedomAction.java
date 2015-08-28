package com.ebiz.mmt.web.struts.manager.admin;

import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Xu,ZhengYang
 */
public class RoleModPopedomAction extends BaseAction {
	// public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// return this.edit(mapping, form, request, response);
	// }
	//
	// public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// setNaviStringToRequestScope(form, request);
	// DynaBean dynaBean = (DynaBean) form;
	// String role_id = (String) dynaBean.get("role_id");
	//
	// SysModule sysModule = new SysModule();
	// sysModule.getMap().put("popedom_4_role", "popedom_4_role");
	// List<SysModule> webSysModuleList = getFacade().getSysModuleService().getSysModuleListForModPopedom(sysModule);
	//
	// BasePopedom basePopedom = new BasePopedom();
	// basePopedom.setIs_base(1);
	// List<BasePopedom> basePopedomList = super.getFacade().getBasePopedomService().getBasePopedomList(basePopedom);
	//
	// for (SysModule webSysModule : webSysModuleList) {
	// if (null != webSysModule.getMod_url()) {
	// List<BasePopedom> basePopedomList1 = new ArrayList<BasePopedom>();
	// String[] webModPeopedoms = null;
	// String ppdm_detail = (String) webSysModule.getMap().get("ppdm_detail");
	// webModPeopedoms = StringUtils.split(ppdm_detail, "+");
	// for (BasePopedom basePopedom1 : basePopedomList) {
	// if (ArrayUtils.contains(webModPeopedoms, basePopedom1.getPpdm_code().toString())) {
	// basePopedomList1.add(basePopedom1);
	// }
	// }
	// webSysModule.setBasePopedomList(basePopedomList1);
	//
	// SysModuleRoleCanGrant modPopedom = new SysModuleRoleCanGrant();
	// modPopedom.setMod_id(webSysModule.getMod_id().longValue());
	// if (!StringUtils.isBlank(role_id)) {
	// modPopedom.setRole_id(new Long(role_id));
	// }
	// String[] selectedModPeopedoms = null;
	// modPopedom = super.getFacade().getSysModuleRoleCanGrantService().getSysModuleRoleCanGrant(modPopedom);
	// if (null != modPopedom) {
	// selectedModPeopedoms = StringUtils.split((String) modPopedom.getMap().get("ppdm_detail"), "+");
	// }
	// if (null != selectedModPeopedoms) {
	// request.setAttribute("mod_popedom_" + webSysModule.getMod_id(), selectedModPeopedoms);
	// }
	// }
	// }
	// request.setAttribute("sysModuleList", webSysModuleList);
	// return mapping.findForward("input");
	// }
	//
	// public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	//
	// DynaBean dynaBean = (DynaBean) form;
	//
	// String mod_id = (String) dynaBean.get("mod_id");
	// String role_id = (String) dynaBean.get("role_id");
	//
	// logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX{}", role_id);
	//
	// SysModule sysModule1 = new SysModule();
	// sysModule1.getMap().put("popedom_4_role", "popedom_4_role");// 查找角色可授权模块
	// List<SysModule> sysModuleList = getFacade().getSysModuleService().getSysModuleListForModPopedom(sysModule1);
	// for (SysModule sysModule : sysModuleList) {
	// String _mod_id = sysModule.getMod_id().toString();
	// String parameterName = "checkbox_" + _mod_id;
	// String[] selectedModPeopedoms = request.getParameterValues(parameterName);
	//
	// SysModuleRoleCanGrant entity = new SysModuleRoleCanGrant();
	// entity.setMod_id(new Long(_mod_id));
	// if (StringUtils.isNotBlank(role_id)) {
	// entity.setRole_id(new Long(role_id));
	// }
	//
	// if (null != selectedModPeopedoms) {
	// SysModuleRoleCanGrant _entity = new SysModuleRoleCanGrant();
	// _entity = super.getFacade().getSysModuleRoleCanGrantService().getSysModuleRoleCanGrant(entity);
	// Integer popedomSum = 0;
	// for (int i = 0; i < selectedModPeopedoms.length; i++) {
	// Integer popedom = Integer.valueOf(selectedModPeopedoms[i]);
	// popedomSum += popedom;
	// }
	// entity.setPpdm_code(popedomSum);
	//
	// if (null != _entity) {
	// entity.setId(_entity.getId());
	// super.getFacade().getSysModuleRoleCanGrantService().modifySysModuleRoleCanGrant(entity);
	// } else {
	// super.getFacade().getSysModuleRoleCanGrantService().createSysModuleRoleCanGrant(entity);
	// }
	// } else {
	// entity = super.getFacade().getSysModuleRoleCanGrantService().getSysModuleRoleCanGrant(entity);
	// if (null != entity) {
	// getFacade().getSysModuleRoleCanGrantService().removeSysModuleRoleCanGrant(entity);
	// }
	// }
	// }
	//
	// String msg = super.getMessage(request, "entity.updated");
	// super.renderJavaScript(response, "alert('" + msg + "');location.href='RoleInfo.do?mod_id=" + mod_id + "';");
	//
	// return null;
	// }
}
