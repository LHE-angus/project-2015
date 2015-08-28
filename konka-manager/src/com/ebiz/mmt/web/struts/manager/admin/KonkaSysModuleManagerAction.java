package com.ebiz.mmt.web.struts.manager.admin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * 
 * @author Hu,Hao
 * @version 模块管理
 * 
 */
public class KonkaSysModuleManagerAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// setNaviStringToRequestScope(form, request);

		SysModule sysModule = new SysModule();
		sysModule.setIs_public(0);
		sysModule.setPar_id(0);
		List<SysModule> smList = super.getFacade().getSysModuleService()
				.getSysModuleSonList(sysModule);

		request.setAttribute("entityList", smList);
		return mapping.findForward("view");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// setNaviStringToRequestScope(form, request);

		SysModule sysModule = new SysModule();
		sysModule.setIs_public(0);
		sysModule.setPar_id(0);
		List<SysModule> smList = super.getFacade().getSysModuleService()
				.getSysModuleSonList(sysModule);

		request.setAttribute("entityList", smList);
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		String mod_id = (String) dynaBean.get("mod_id");

		SysModule entity = new SysModule();
		entity.setIs_del(0);
		entity.setIs_public(0);
		List<SysModule> entityList = super.getFacade().getSysModuleService()
				.getSysModuleList(entity);

		Set<String> modIdSet = new HashSet<String>();	
		for (SysModule sysModule : entityList) {
			if (null != sysModule.getMod_url()
					&& sysModule.getTree_level() != 1) {
				String _mod_id = sysModule.getMod_id().toString();
				String parameterName = "checkbox_" + _mod_id;
				String[] selectedModPeopedoms = request
						.getParameterValues(parameterName);
				if (null != selectedModPeopedoms) {
					String value = selectedModPeopedoms[0];
					if ("on".equals(value)) {
						SysModule sysModule2 = new SysModule();
						sysModule2.setMod_id(sysModule.getMod_id());
						List<SysModule> sList = super.getFacade().getSysModuleService().getSysModuleParentList(sysModule2);
						if(sList.size()>0){
							for(SysModule sysM :sList){
								modIdSet.add(sysM.getMod_id().toString());
							}
						}
					}
				}
			}
		}
		
		super.getFacade().getSysModuleService().modifySysModuleForFgs(modIdSet);

		saveMessage(request, "entity.updated");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
}
