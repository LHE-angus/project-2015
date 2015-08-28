package com.ebiz.mmt.web.struts;

import java.util.ArrayList;
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

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.EcBcompPdRebates;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.MobileUser;

/**
 * @author Pan,Gang
 * @version 2014-01-11
 */
public class EcBcompPdRebatesAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		MobileUser mu = (MobileUser) session.getAttribute("mobile_user");

		if (null == mu) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		String verificationCode = (String) dynaBean.get("verificationCode");
		String type = (String) dynaBean.get("type");
		String pd_id = (String) dynaBean.get("pd_id");

		String msg = "";
		if (StringUtils.isNotBlank(type)) {
			if (StringUtils.isBlank(verificationCode)) {
				msg = "验证码不能为空";
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg
				        + "');window.history.back();}");
				return null;
			}
			if (StringUtils.isNotBlank(verificationCode)) {
				if (!verificationCode.equals((String) session.getAttribute("verificationCode"))) {
					msg = "验证码不正确";
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg
					        + "');window.history.back();}");
					return null;
				}
			}

		}
		EcBcompPdRebates entity = new EcBcompPdRebates();

		if (StringUtils.isNotBlank(pd_id)) {
			entity.getMap().put("pd_name_eq", pd_id);
		} else {
			entity.getMap().put("pd_name_not_eq", true);
		}

		entity.getMap().put("own_sys_in_01", true);
		entity.getMap().put("is_upSelf", true);

		entity.setOwn_sys(1);
		entity.setIs_del(0);
		entity.getRow().setFirst(0);
		entity.getRow().setCount(2);
		List<EcBcompPdRebates> entityList = super.getFacade().getEcBcompPdRebatesService()
		        .getEcBcompPdRebatesPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		KonkaBcompPd kp = new KonkaBcompPd();
		kp.setState(1);
		kp.getMap().put("own_sys_in_01", true);
		kp.getMap().put("is_upSelf", true);
		List<KonkaBcompPd> kpList = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdList(kp);
		List<Map<String, String>> kList = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		for (KonkaBcompPd s : kpList) {
			map = new HashMap<String, String>();
			map.put("pd_sn", s.getPd_sn());
			kList.add(map);

		}
		request.setAttribute("kpListJson", JSON.toJSONString(kList));

		dynaBean.set("user_id", mu.getUser_id());
		dynaBean.set("userpass", mu.getUserpass());

		return new ActionForward("/mobile/EcBcompPdRebates/list.jsp");
	}

}