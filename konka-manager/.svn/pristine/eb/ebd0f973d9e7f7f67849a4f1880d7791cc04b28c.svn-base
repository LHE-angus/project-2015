package com.ebiz.mmt.web.struts.inter.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.alipay.util.Md5Encrypt;
import com.ebiz.mmt.domain.KonkaInterfaceAccessLog;
import com.ebiz.mmt.domain.KonkaInterfaceBindsUser;
import com.ebiz.mmt.domain.KonkaInterfaceIp;
import com.ebiz.mmt.domain.KonkaInterfaceLicenses;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.struts.inter.form.ValidationForm;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.mmt.web.util.IpUtils;

/**
 * @author pan,gang
 * @version 2014-09-23
 * @see 用户解除绑定接口
 */
public class KonkaInterfaceUnboundUserAction extends BaseInterAction {

	@SuppressWarnings("static-access")
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");
		String user_key = (String) dynaBean.get("user_key");
		String pass_word = (String) dynaBean.get("pass_word");
		String licenses_sn = (String) dynaBean.get("licenses_sn");
		// super.encodeCharacterForGetMethod(dynaBean, request);
		ValidationForm obj = new ValidationForm();

		// start...
		if (StringUtils.isBlank(user_id)) {
			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("用户名为空");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(licenses_sn);
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("用户解绑接口");
			log.setUser_id(null);
			log.setUser_key(null);
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

			obj.setReturn_state(1);
			obj.setReturn_error("user_id is not null...");
			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		}
		if (StringUtils.isBlank(pass_word)) {

			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("用户密码为空");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(licenses_sn);
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("用户解绑接口");
			log.setUser_id(null);
			log.setUser_key(null);
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

			obj.setReturn_state(1);
			obj.setReturn_error("pass_word is not null...");
			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		}
		if (StringUtils.isBlank(licenses_sn)) {
			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("用户授权码为空");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(licenses_sn);
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("用户解绑接口");
			log.setUser_id(null);
			log.setUser_key(null);
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

			obj.setReturn_state(1);
			obj.setReturn_error("licenses_sn is not null...");
			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		}
		if (StringUtils.isBlank(user_key)) {

			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("user_key为空");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(licenses_sn);
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("用户解绑接口");
			log.setUser_id(null);
			log.setUser_key(null);
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

			obj.setReturn_state(1);
			obj.setReturn_error("user_key is not null...");
			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		}

		// user_id = changeToUTF8(user_id);
		// user_key = changeToUTF8(user_key);
		// pass_word = changeToUTF8(pass_word);
		// licenses_sn = changeToUTF8(licenses_sn);

		// 用户校验
		PeProdUser peProdUser = new PeProdUser();
		peProdUser.setId(Long.valueOf(user_id));
		List<PeProdUser> peProdList = super.getFacade().getPeProdUserService().getPeProdUserList(peProdUser);
		if (null == peProdList || peProdList.size() == 0) {

			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("没有找到该用户");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(licenses_sn);
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("用户解绑接口");
			log.setUser_id(null);
			log.setUser_key(null);
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

			obj.setReturn_state(1);
			obj.setReturn_error("user not found...");
			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		}
		if (peProdList.size() > 1) {
			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("用户名重复");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(licenses_sn);
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("用户解绑接口");
			log.setUser_id(null);
			log.setUser_key(null);
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

			obj.setReturn_state(1);
			obj.setReturn_error("the user name repetition...");
			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		}
		peProdUser = peProdList.get(0);

		DESPlus des = new DESPlus();
		String ppd = des.decrypt(peProdUser.getPass_word());

		// 密码校验
		Md5Encrypt md5 = new Md5Encrypt();
		String pw = md5.md5(ppd);
		if (!pass_word.equals(pw)) {

			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("用户名与密码 不匹配");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(licenses_sn);
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("用户解绑接口");
			log.setUser_id(peProdUser.getId());
			log.setUser_key(null);
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

			obj.setReturn_state(1);
			obj.setReturn_error("user_id pass_word does not match...");
			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		}

		// 判断授权码是否有效
		KonkaInterfaceLicenses kl = new KonkaInterfaceLicenses();
		kl.setLicenses_sn(licenses_sn);
		kl.setInfo_state(0);
		kl.setIs_del(0);
		List<KonkaInterfaceLicenses> klList = super.getFacade().getKonkaInterfaceLicensesService()
		        .getKonkaInterfaceLicensesList(kl);
		if (null == klList || klList.size() == 0) {

			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("用户授权码不存在");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(licenses_sn);
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("用户解绑接口");
			log.setUser_id(peProdUser.getId());
			log.setUser_key(null);
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

			obj.setReturn_state(1);
			obj.setReturn_error("licenses_sn does not exist...");
			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		}
		if (klList.size() > 1) {

			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("用户授权码重复");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(licenses_sn);
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("用户解绑接口");
			log.setUser_id(null);
			log.setUser_key(null);
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

			obj.setReturn_state(1);
			obj.setReturn_error("the licenses_sn repetition...");
			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		}
		kl = klList.get(0);

		// 判断访问ip是否授权

		KonkaInterfaceIp kip = new KonkaInterfaceIp();
		kip.setInfo_state(0);
		kip.setIs_del(0);
		kip.setLicenses_sn(licenses_sn);
		List<KonkaInterfaceIp> kipList = super.getFacade().getKonkaInterfaceIpService().getKonkaInterfaceIpList(kip);
		if (null == kipList || kipList.size() == 0) {

			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("IP未授权");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(licenses_sn);
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("用户解绑接口");
			log.setUser_id(peProdUser.getId());
			log.setUser_key(null);
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

			obj.setReturn_state(1);
			obj.setReturn_error("not authorized by IP");
			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		}
		List<String> ipList = new ArrayList<String>();
		if (kipList.size() > 0) {
			for (KonkaInterfaceIp ip : kipList) {
				if (ip.getIp() != null) {
					ipList.add(ip.getIp());
				}
			}
		}

		if (!ipList.contains(IpUtils.getRemortIP(request))) {

			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("IP未授权");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(licenses_sn);
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("用户绑定接口");
			log.setUser_id(peProdUser.getId());
			log.setUser_key(null);
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

			obj.setReturn_state(1);
			obj.setReturn_error("not authorized by IP");
			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		}

		// 判断是否已经绑定
		KonkaInterfaceBindsUser kfb = new KonkaInterfaceBindsUser();
		kfb.setIs_del(0);
		kfb.setUser_id(Long.valueOf(user_id));
		kfb.setLicenses_sn(licenses_sn);
		kfb.setUser_key(user_key);
		List<KonkaInterfaceBindsUser> kfbList = super.getFacade().getKonkaInterfaceBindsUserService()
		        .getKonkaInterfaceBindsUserList(kfb);
		if (null == kfbList || kfbList.size() == 0) {

			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("未找到绑定数据");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(licenses_sn);
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("用户解绑接口");
			log.setUser_id(peProdUser.getId());
			log.setUser_key(null);
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

			obj.setReturn_state(1);
			obj.setReturn_error("did not find the data binding...");
			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		}
		kfb = kfbList.get(0);
		kfb.setIs_del(1);// 解绑
		int id = super.getFacade().getKonkaInterfaceBindsUserService().modifyKonkaInterfaceBindsUser(kfb);

		if (id > 0) {

			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(licenses_sn);
			log.setReq_state(0);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("用户解绑接口");
			log.setUser_id(peProdUser.getId());
			log.setUser_key(kfb.getUser_key());
			log.setReq_content(JSON.toJSONString(obj));
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

			// 检查用户授权信息验证是否可以访问 0正常 1失败
			obj.setReturn_state(0);
			obj.setReturn_msg("unbound success");
			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		} else {

			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("网络异常");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(licenses_sn);
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("用户解绑接口");
			log.setUser_id(peProdUser.getId());
			log.setUser_key(null);
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

			obj.setReturn_state(1);
			obj.setReturn_error("sorry, the network anomaly, bind failed! Please try again later");
			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		}

		// end...

	}

}
