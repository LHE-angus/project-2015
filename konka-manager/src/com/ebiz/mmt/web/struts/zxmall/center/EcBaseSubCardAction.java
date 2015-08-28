package com.ebiz.mmt.web.struts.zxmall.center;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcBaseCardLevel;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.web.struts.zxmall.BaseMemberAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author tudp
 * @version 2013-09-09
 */
public class EcBaseSubCardAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String user_name_like = (String) dynaBean.get("user_name_like");
		String link_phone_like = (String) dynaBean.get("link_phone_like");
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("zxmall");
		// 账户中心用户登录验证
				if (ecUser==null) {
					String ctx = super.getCtxPath(request);
					String url = ctx + "/zxmall/";
					response.sendRedirect(url);
					return null;
				}
		
		// 账户中心用户登录验证
		if (ecUser.getUser_type().intValue() == 2) {
			String touch = (String) session.getAttribute("touch");
			if (!"1".equals(touch)) {
				String ctx = super.getCtxPath(request);
				String url = ctx + "/zxmall/center/Skip.do";
				response.sendRedirect(url);
				return null;
			}
		}
		EcUser entity = new EcUser();
		if (StringUtils.isNotBlank(user_name_like)) {
			entity.getMap().put("user_name_like", user_name_like);
			dynaBean.set("user_name_like", user_name_like);
		}
		if (StringUtils.isNotBlank(link_phone_like)) {
			entity.getMap().put("link_phone_like", link_phone_like);
			dynaBean.set("link_phone_like", link_phone_like);
		}
		if (null != ecUser.getUser_name()) {
			entity.getMap().put("link_user_name", ecUser.getUser_name());
		}

		Long recordCount = super.getFacade().getEcUserService().getSubEcUserByUserNameCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		this.copyProperties(dynaBean, entity);
		List<EcUser> entityList = super.getFacade().getEcUserService().getSubEcUserByUserNameList(entity);

		for (EcUser ecUser2 : entityList) {
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("leve", 1);
			searchLeve(map, ecUser2.getLink_user_name());
			ecUser2.getMap().put("leve", map.get("leve"));
		}

		request.setAttribute("entityList", entityList);

		EcBaseCardLevel el = new EcBaseCardLevel();
		List<EcBaseCardLevel> elList = super.getFacade().getEcBaseCardLevelService().getEcBaseCardLevelList(el);
		request.setAttribute("elList", elList);

		// request.setAttribute("sybDeptInfoList",
		// super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		return mapping.findForward("list");
	}

	private void searchLeve(Map<String, Integer> map, String user_name) {

		// XANO1080
		EcUser ec = new EcUser();
		ec.setUser_name(user_name);
		ec = super.getFacade().getEcUserService().getEcUser(ec);
		// 上级人
		if (null != ec && null != ec.getLink_user_name()) {
			map.put("leve", map.get("leve") + 1);
			searchLeve(map, ec.getLink_user_name());

		}
	}

}
