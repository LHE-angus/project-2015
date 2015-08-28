package com.ebiz.mmt.web.struts.manager.zmd;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxPd;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PeProdUser;

/**
 * @author Li,Yuan
 * @version 2012-01-11
 */
public class KonkaXxZmdKcSearchAction extends BaseZmdAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		// Long user_id = super.getSessionUserInfo(request).getId();
		PeProdUser user_info = super.getSessionUserInfo(request);
		KonkaXxZmdUsers kkzmdUsers = new KonkaXxZmdUsers();
		kkzmdUsers.setUser_id(user_info.getId());
		List<KonkaXxZmdUsers> kkzmdUsersList = super.getFacade().getKonkaXxZmdUsersService().getKonkaXxZmdUsersList(
				kkzmdUsers);

		if (null == kkzmdUsersList || kkzmdUsersList.size() == 0) {
			request.setAttribute("zmd_id_is_null", true);
		} else {
			KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
			konkaXxZmd.setZmd_id(kkzmdUsersList.get(0).getZmd_id());
			konkaXxZmd = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(konkaXxZmd);
			if (null == konkaXxZmd) {
				request.setAttribute("zmd_id_is_null", true);
			} else {
				// 商品表取商品型号
				KonkaXxPd konkaXxPd = new KonkaXxPd();
				KonkaDept kDept = getKonkaDeptForFgs(user_info.getDept_id());
				if (kDept != null)
					konkaXxPd.setDept_id(kDept.getDept_id());
				List<KonkaXxPd> konkaXxPdList = super.getFacade().getKonkaXxPdService().getKonkaXxPdList(konkaXxPd);
				request.setAttribute("konkaXxPdList", konkaXxPdList);
			}

		}

		String md_name = (String) dynaBean.get("md_name");
		if (StringUtils.isNotBlank(md_name)) {
			dynaBean.set("md_name", md_name);
			Long ret = super.getStocks(md_name, kkzmdUsersList.get(0).getZmd_id());
			request.setAttribute("ret", ret);
		}
		return mapping.findForward("list");
	}
}
