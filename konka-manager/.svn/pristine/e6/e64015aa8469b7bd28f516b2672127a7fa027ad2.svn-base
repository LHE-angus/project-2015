package com.ebiz.mmt.web.struts.touch.center;

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
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Liu,ZhiXiang
 * @version 2013-09-11
 */
public class EcBcompPdRebatesAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		// 账户中心用户登录验证
		if (ecUser.getUser_type().intValue() == 1) {
			String touch = (String) session.getAttribute("touch");
			if (!"1".equals(touch)) {
				String ctx = super.getCtxPath(request);
				String url = ctx + "/touch/center/Skip.do";
				response.sendRedirect(url);
				return null;
			}
		}

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String pd_id = (String) dynaBean.get("pd_id");
		String pd_id1 = (String) dynaBean.get("pd_id1");
		String verificationCode = (String) dynaBean.get("verificationCode");
		String type = (String) dynaBean.get("type");
		
		if(pd_id1!=null){
			pd_id=pd_id1.trim();
		}
		
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

		// KonkaBcompPd pd = new KonkaBcompPd();
		// if (StringUtils.isNotBlank(pd_id)) {
		// pd.setId(Long.valueOf(pd_id));
		// pd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(pd);
		// }

		if (StringUtils.isNotBlank(pd_id)) {
			entity.getMap().put("pd_name_eq", pd_id);
		} else {
			entity.getMap().put("pd_name_not_eq", true);
		}

		entity.getMap().put("own_sys_in_01", true);
		entity.getMap().put("is_upSelf", true);

		entity.setOwn_sys(1);
		entity.setIs_del(0);
		Long recordCount = super.getFacade().getEcBcompPdRebatesService().getEcBcompPdRebatesCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
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

		logger.info("--------------xxx------>{}" + kpList.size());

		return mapping.findForward("list");
	}

}