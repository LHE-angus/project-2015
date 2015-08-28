package com.ebiz.mmt.web.struts.inter.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
 * @see 用户绑定接口
 */
public class KonkaInterfaceBingdsUserAction extends BaseInterAction {

	@SuppressWarnings("static-access")
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String user_name = (String) dynaBean.get("user_name");
		String pass_word = (String) dynaBean.get("pass_word");
		String licenses_sn = (String) dynaBean.get("licenses_sn");
		// super.encodeCharacterForGetMethod(dynaBean, request);
		ValidationForm obj = new ValidationForm();

		// start...
		if (StringUtils.isBlank(user_name)) {

			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("用户名为空");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(licenses_sn);
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("用户绑定接口");
			log.setUser_id(null);
			log.setUser_key(null);
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

			obj.setReturn_state(1);
			obj.setReturn_error("user_name is not null...");
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
			log.setReq_mod_name("用户绑定接口");
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
			log.setReq_mod_name("用户绑定接口");
			log.setUser_id(null);
			log.setUser_key(null);
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

			obj.setReturn_state(1);
			obj.setReturn_error("licenses_sn is not null...");
			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		}

		// 本地需要转utf8，正式环境不需要
		// user_name = changeToUTF8(user_name);
		// pass_word = changeToUTF8(pass_word);
		// licenses_sn = changeToUTF8(licenses_sn);

		// 用户校验
		PeProdUser peProdUser = new PeProdUser();
		peProdUser.setIs_del(0);
		peProdUser.setUser_name(user_name);
		List<PeProdUser> peProdList = super.getFacade().getPeProdUserService().getPeProdUserList(peProdUser);
		if (null == peProdList || peProdList.size() == 0) {

			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("没有找到该用户");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(licenses_sn);
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("用户绑定接口");
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
			log.setReq_mod_name("用户绑定接口");
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
			log.setReq_mod_name("用户绑定接口");
			log.setUser_id(peProdUser.getId());
			log.setUser_key(null);
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

			obj.setReturn_state(1);
			obj.setReturn_error("username password does not match...");
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
			log.setReq_mod_name("用户绑定接口");
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
			log.setReq_mod_name("用户绑定接口");
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
			log.setReq_mod_name("用户绑定接口");
			log.setUser_id(peProdUser.getId());
			log.setUser_key(null);
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

			obj.setReturn_state(1);
			obj.setReturn_error("not authorized by IP");
			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		}
		/*
		 * if (kipList.size() > 1) {
		 * 
		 * KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
		 * log.setAdd_date(new Date()); log.setError_msg("IP授权重复");
		 * log.setIp(IpUtils.getRemortIP(request));
		 * log.setLicenses_sn(licenses_sn); log.setReq_state(1);
		 * log.setReq_url(request.getRequestURL().toString() + "?" +
		 * request.getQueryString()); log.setReq_mod_name("用户绑定接口");
		 * log.setUser_id(peProdUser.getId()); log.setUser_key(null);
		 * super.getFacade
		 * ().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog
		 * (log);
		 * 
		 * obj.setReturn_state(1);
		 * obj.setReturn_error("ip authorized sending abnormality");
		 * super.renderJson(response, JSON.toJSONString(obj)); return null; }
		 * kip = kipList.get(0);
		 */

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
		kfb.setUser_name(user_name);
		List<KonkaInterfaceBindsUser> kfbList = super.getFacade().getKonkaInterfaceBindsUserService()
		        .getKonkaInterfaceBindsUserList(kfb);

		if (kfbList.size() > 1) {

			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("重复绑定");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(licenses_sn);
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("用户绑定接口");
			log.setUser_id(peProdUser.getId());
			log.setUser_key(null);
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

			obj.setReturn_state(1);
			obj.setReturn_error("repeat binding...");
			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		}
		if (kfbList.size() == 1) {
			KonkaInterfaceBindsUser kk = new KonkaInterfaceBindsUser();
			kk.setUser_name(user_name);
			kk.setLicenses_sn(licenses_sn);
			List<KonkaInterfaceBindsUser> kkList = super.getFacade().getKonkaInterfaceBindsUserService()
			        .getKonkaInterfaceBindsUserList(kk);
			if (null == kkList || kkList.size() == 0) {

				KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
				log.setAdd_date(new Date());
				log.setError_msg("未找到绑定数据");
				log.setIp(IpUtils.getRemortIP(request));
				log.setLicenses_sn(licenses_sn);
				log.setReq_state(1);
				log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
				log.setReq_mod_name("用户绑定接口");
				log.setUser_id(peProdUser.getId());
				log.setUser_key(null);
				super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

				obj.setReturn_state(1);
				obj.setReturn_error("did not find the data binding...");
				super.renderJson(response, JSON.toJSONString(obj));
				return null;
			}

			kfb = kkList.get(0);
			if (kfb.getIs_del() == 0) {// 已经存在绑定

				KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
				log.setAdd_date(new Date());
				log.setError_msg("重复绑定");
				log.setIp(IpUtils.getRemortIP(request));
				log.setLicenses_sn(licenses_sn);
				log.setReq_state(1);
				log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
				log.setReq_mod_name("用户绑定接口");
				log.setUser_id(peProdUser.getId());
				log.setUser_key(null);
				super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

				obj.setReturn_state(1);
				obj.setReturn_error("repeat binding...");
				super.renderJson(response, JSON.toJSONString(obj));
				return null;
			} else if (kfb.getIs_del() == 1) {// 重新绑定
				kfb.setIs_del(0);
				int id = super.getFacade().getKonkaInterfaceBindsUserService().modifyKonkaInterfaceBindsUser(kfb);
				if (id > 0) {
					KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
					log.setAdd_date(new Date());
					log.setIp(IpUtils.getRemortIP(request));
					log.setLicenses_sn(licenses_sn);
					log.setReq_state(0);
					log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
					log.setReq_mod_name("用户绑定接口");
					log.setUser_id(peProdUser.getId());
					log.setUser_key(kfb.getUser_key());
					log.setReq_content(JSON.toJSONString(obj));
					super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

					// 检查用户授权信息验证是否可以访问 0正常 1失败
					obj.setReturn_state(0);
					obj.setReturn_msg("user_id:" + kfb.getUser_id() + ",user_key:" + kfb.getUser_key()
					        + ",licenses_sn:" + kfb.getLicenses_sn());
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
					log.setReq_mod_name("用户绑定接口");
					log.setUser_id(peProdUser.getId());
					log.setUser_key(null);
					super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

					obj.setReturn_state(1);
					obj.setReturn_error("sorry, the network anomaly, bind failed! Please try again later");
					super.renderJson(response, JSON.toJSONString(obj));
					return null;
				}
			}

		}

		if (null == kfbList || kfbList.size() == 0) {// 新增
			// 绑定表插入记录
			KonkaInterfaceBindsUser ku = new KonkaInterfaceBindsUser();
			ku.setAdd_date(new Date());
			ku.setIs_del(0);
			ku.setLicenses_sn(licenses_sn);
			ku.setReal_name(peProdUser.getReal_name());
			ku.setUser_id(peProdUser.getId());
			ku.setUser_name(user_name);
			ku.setUser_key(generateShortUuid());

			KonkaInterfaceBindsUser ki = new KonkaInterfaceBindsUser();
			ki.setUser_key(ku.getUser_key());
			Long count = super.getFacade().getKonkaInterfaceBindsUserService().getKonkaInterfaceBindsUserCount(ki);
			if (count > 0) {

				KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
				log.setAdd_date(new Date());
				log.setError_msg("user_key重复");
				log.setIp(IpUtils.getRemortIP(request));
				log.setLicenses_sn(licenses_sn);
				log.setReq_state(1);
				log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
				log.setReq_mod_name("用户绑定接口");
				log.setUser_id(peProdUser.getId());
				log.setUser_key(null);
				super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

				// 判断user_key是否存在
				obj.setReturn_state(1);
				obj.setReturn_error("user_key repeat, please try again later...");
				super.renderJson(response, JSON.toJSONString(obj));
				return null;
			}

			Long id = super.getFacade().getKonkaInterfaceBindsUserService().createKonkaInterfaceBindsUser(ku);

			if (id > 0) {

				KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
				log.setAdd_date(new Date());
				log.setIp(IpUtils.getRemortIP(request));
				log.setLicenses_sn(licenses_sn);
				log.setReq_state(0);
				log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
				log.setReq_mod_name("用户绑定接口");
				log.setUser_id(peProdUser.getId());
				log.setUser_key(kfb.getUser_key());
				log.setReq_content(JSON.toJSONString(obj));
				super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

				// 检查用户授权信息验证是否可以访问 0正常 1失败
				obj.setReturn_state(0);
				obj.setReturn_msg("user_id:" + ku.getUser_id() + ",user_key:" + ku.getUser_key() + ",licenses_sn:"
				        + ku.getLicenses_sn());
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
				log.setReq_mod_name("用户绑定接口");
				log.setUser_id(peProdUser.getId());
				log.setUser_key(null);
				super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

				obj.setReturn_state(1);
				obj.setReturn_error("sorry, the network anomaly, bind failed! Please try again later");
				super.renderJson(response, JSON.toJSONString(obj));
				return null;
			}
		}
		// end...
		return null;

	}

	// 生成8位随机码
	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
	        "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8",
	        "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
	        "U", "V", "W", "X", "Y", "Z" };

	public static String generateShortUuid() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();
	}
}
