package com.ebiz.mmt.web.struts.inter.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaInterfaceAccessLog;
import com.ebiz.mmt.domain.KonkaInterfaceBindsUser;
import com.ebiz.mmt.web.struts.inter.form.CategoryType;
import com.ebiz.mmt.web.struts.inter.form.CategoryTypeForm;
import com.ebiz.mmt.web.struts.inter.form.InterUser;
import com.ebiz.mmt.web.util.IpUtils;

/**
 * @author pan,gang
 * @version 2014-09-23
 * @see 客户细分类型接口
 */
public class KonkaCategoryTypeInterfaceAction extends BaseInterAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		InterUser user = super.getInterUser(form, request);
		KonkaInterfaceBindsUser entity = new KonkaInterfaceBindsUser();
		entity.setUser_id(user.getUser_id());
		entity.setLicenses_sn(user.getLicenses_sn());
		entity.setUser_key(user.getUser_key());

		entity = super.getFacade().getKonkaInterfaceBindsUserService().getKonkaInterfaceBindsUser(entity);
		CategoryTypeForm obj = new CategoryTypeForm();
		if (null == entity) {
			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("未找到绑定数据");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(user.getLicenses_sn());
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("R3客户类型接口");
			log.setUser_id(user.getUser_id());
			log.setUser_key(user.getUser_key());
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

			obj.setReturn_state(1);
			obj.setReturn_error("user_id user_key licenses_sn does not match...");
			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		}

		KonkaCategory kg = new KonkaCategory();
		try {
			List<KonkaCategory> konkaCategoryList = super.getFacade().getKonkaCategoryService()
			        .getKonkaCategoryAndParIndexList(kg);
			List<CategoryType> ctList = new ArrayList<CategoryType>();
			for (KonkaCategory konkaCategory : konkaCategoryList) {
				CategoryType gt = new CategoryType();
				gt.setC_comm((String) konkaCategory.getMap().get("c_comm"));
				gt.setC_index((Long) konkaCategory.getMap().get("c_index"));
				gt.setC_name((String) konkaCategory.getMap().get("c_name"));
				gt.setPar_index((String) konkaCategory.getMap().get("par_index"));
				ctList.add(gt);
			}

			obj.setReturn_state(0);
			obj.setKonkaCategoryList(ctList);

			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(user.getLicenses_sn());
			log.setReq_state(0);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("R3客户类型接口");
			log.setUser_id(user.getUser_id());
			log.setUser_key(user.getUser_key());
			log.setReq_content(JSON.toJSONString(obj));
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		} catch (Exception e) {
			obj.setReturn_state(1);
			obj.setReturn_error("sorry, the network anomaly, bind failed! Please try again later");

			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setError_msg("网络异常");
			log.setAdd_date(new Date());
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(user.getLicenses_sn());
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("R3客户类型接口");
			log.setUser_id(user.getUser_id());
			log.setUser_key(user.getUser_key());
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		}

	}
}
