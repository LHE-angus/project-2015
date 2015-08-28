package com.ebiz.mmt.web.struts.mobile.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Wang Hao
 */
public class FramesAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.index(mapping, form, request, response);
	}

	public ActionForward index(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("modelArray", getModel());

//		HttpSession session = request.getSession();
//		PeRoleUser peRoleUser = (PeRoleUser) session.getAttribute(Constants.ROLE_INFO);
//
//		SysModule sysModule = new SysModule();
//		sysModule.getMap().put("role_id", peRoleUser.getRole_id());
//		List<SysModule> sysModuleList = getFacade().getSysModuleService().getSysModuleListForMobileRole(sysModule);
//		request.setAttribute("sysModuleList", sysModuleList);

		return mapping.findForward("indexFrame");
	}

	// 取型号
	public String getModel() throws Exception {
		PePdModel pePdModel = new PePdModel();
		List<PePdModel> pePdModelList = super.getFacade().getPePdModelService().getPePdModelList(pePdModel);

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < pePdModelList.size(); i++) {
			PePdModel p = pePdModelList.get(i);
			if (StringUtils.isNotBlank(p.getMd_name())) {
				sb.append(p.getMd_name().trim());
				if (i != pePdModelList.size() - 1) {
					sb.append(",");
				}
			}
		}
		return sb.toString();
	}

	public ActionForward moduleListForAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		PeRoleUser peRoleUser = (PeRoleUser) session.getAttribute(Constants.ROLE_INFO);

		SysModule sysModule = new SysModule();
		sysModule.getMap().put("role_id", peRoleUser.getRole_id());
		List<SysModule> sysModuleList = getFacade().getSysModuleService().getSysModuleListForMobileRole(sysModule);

		StringBuffer dataSb = new StringBuffer("[");
		for (SysModule sm : sysModuleList) {
			dataSb.append("{");
			dataSb.append("\"mod_id\":\"").append(sm.getMod_id()).append("\",");
			dataSb.append("\"mod_name\":\"").append(sm.getMod_name()).append("\",");
			dataSb.append("\"mod_url\":\"").append(sm.getMod_url()).append("\",");

			if (StringUtils.indexOf(sm.getMod_url(), "ajax=false") != -1) {
				dataSb.append("\"is_ajax\":\"false\"");
			} else {
				dataSb.append("\"is_ajax\":\"true\"");
			}

			dataSb.append("},");
		}

		dataSb.append("{}]");

		logger.info(dataSb.toString());
		
		super.renderJson(response, dataSb.toString());
		return null;
	}
}
