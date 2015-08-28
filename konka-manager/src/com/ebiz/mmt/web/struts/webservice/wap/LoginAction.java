package com.ebiz.mmt.web.struts.webservice.wap;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.util.CookieGenerator;
import org.springframework.web.util.WebUtils;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.EcBaseCardLevel;
import com.ebiz.mmt.domain.EcBaseCardNo;
import com.ebiz.mmt.domain.EcCust;
import com.ebiz.mmt.domain.EcShoppingCart;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcUserLevelAuditInfo;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.MobileUser;
import com.ebiz.mmt.domain.OperLog;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.manager.oa.IpUtils;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.mmt.web.util.ShoppingCarJsonUtil;

/**
 * @author Jiang,JiaYong
 * @version 2013-09-12
 */
public class LoginAction extends BaseAction {
	private static final String DEFAULT_PASSWORD = "~~@^_^@~~";

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.showLoginForm(mapping, form, request, response);
	}

	public ActionForward showLoginForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setCookies2RequestScope(request);
		DynaBean dynaBean = (DynaBean) form;
		String url = (String) dynaBean.get("url");
		saveToken(request);
		request.setAttribute("url", url);

		// 如果会话中又session则直接跳转进去
		HttpSession session = request.getSession();
		EcUser userInfo = (EcUser) session.getAttribute(Constants.EPP_USER);
		if (null != userInfo) {
			super.renderJavaScript(response, "location.href='Index.do';");
			return null;
		}
		return mapping.findForward("login");
	}

	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!StringUtils.equalsIgnoreCase(request.getMethod(), "POST")) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		DynaBean lazyForm = (DynaBean) form;

		String user_name = (String) lazyForm.get("user_name");
		String password = (String) lazyForm.get("password");
		String is_remember = (String) lazyForm.get("is_remember");
		String url = request.getParameter("url");

		String msg = null;
		if (StringUtils.isBlank(user_name)) {
			msg = super.getMessage(request, "login.failed.username.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('"
					+ msg + "');location.href='login.do'}");
			return null;
		}

		if (StringUtils.isBlank(password)) {
			msg = super.getMessage(request, "login.failed.password.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('"
					+ msg + "');location.href='login.do'}");

			return null;
		}

		HttpSession session = request.getSession();

		user_name = user_name.trim();
		// 增加登录错误次数验证
		String valiMsg = validateLoginErrLogs("table_ec_user_" + user_name, 5,
				30);
		if (null != valiMsg) {
			super.renderJavaScript(response, "window.onload=function(){alert('"
					+ valiMsg + "');location.href='login.do'}");
			return null;
		}
		EcUser entity = new EcUser();
		entity.getRow().setCount(2);
		entity.setUser_name(user_name);
		entity.setIs_del(0);

		List<EcUser> userInfoList = getFacade().getEcUserService()
				.getEcUserList(entity);
		// 如果没有找到的话，找工卡验证
		if (userInfoList == null || userInfoList.size() == 0) {
			entity.setUser_name(null);
			entity.setCard_no(user_name);
			userInfoList = getFacade().getEcUserService().getEcUserList(entity);
		}
		// 如果没有找到的话，找移动电话验证
		if (userInfoList == null || userInfoList.size() == 0) {
			entity.setUser_name(null);
			entity.setLink_phone(user_name);
			entity.setCard_no(null);
			userInfoList = getFacade().getEcUserService().getEcUserList(entity);
		}
		if (null == userInfoList || userInfoList.size() == 0) {
			String logmsg = createLoginErrLogs("table_ec_user_" + user_name,
					password, 5, 30);
			if (null != logmsg) {
				super.renderJavaScript(response,
						"window.onload=function(){alert('" + logmsg
								+ "');location.href='login.do'}");
				return null;
			}
			OperLog t = new OperLog();
			t.setOper_time(new Timestamp(Calendar.getInstance().getTime()
					.getTime()));
			t.setOper_ip(IpUtils.getIpAddr(request));
			t.setLink_tab("EC_USER");
			t.setLink_id(0l);
			String type = "账户名：" + user_name + ",密码：" + password;
			t.setOper_type(type);
			String name = user_name + "用户不存在，登录管理系统失败";
			t.setPpdm_name(name);
			t.setOper_uid(0l);
			t.setOper_uname(user_name);
			getFacade().getOperLogService().createOperLog(t);

			msg = super.getMessage(request, "login.failed.username.invalid");
			super.renderJavaScript(response, "window.onload=function(){alert('"
					+ msg + "');location.href='login.do'}");
			return null;
		} else if (userInfoList.size() > 1) {
			String logmsg = createLoginErrLogs("table_ec_user_" + user_name,
					password, 5, 30);
			if (null != logmsg) {
				super.renderJavaScript(response,
						"window.onload=function(){alert('" + logmsg
								+ "');location.href='login.do'}");
				return null;
			}
			OperLog t = new OperLog();
			t.setOper_time(new Timestamp(Calendar.getInstance().getTime()
					.getTime()));
			t.setOper_ip(IpUtils.getIpAddr(request));
			t.setLink_tab("EC_USER");
			t.setLink_id(0l);
			String type = "账户名：" + user_name + ",密码：" + password;
			t.setOper_type(type);
			String name = user_name + "用户名重复，登录管理系统失败";
			t.setPpdm_name(name);
			t.setOper_uid(0l);
			t.setOper_uname(user_name);
			getFacade().getOperLogService().createOperLog(t);

			msg = super.getMessage(request, "login.failed.username.repeat");
			super.renderJavaScript(response, "window.onload=function(){alert('"
					+ msg + "');location.href='login.do'}");

			return null;
		}

		Cookie passwordCookie = WebUtils.getCookie(request, "ec_password");
		if (null != passwordCookie && DEFAULT_PASSWORD.equals(password)) {
			entity.setPass_word(passwordCookie.getValue());
		} else {
			DESPlus des = new DESPlus();
			entity.setPass_word(des.encrypt(password));
		}
		logger.info("Login User : {}", BeanUtils.describe(entity));
		EcUser userInfo = userInfoList.get(0);
//		if(userInfo.getCust_id()!=null){
//			EcCust ecCust = new EcCust();
//			ecCust.setId(userInfo.getCust_id());
//			ecCust = super.getFacade().getEcCustService().getEcCust(ecCust);
//			if(ecCust!=null&&ecCust.getDel_mark()!=null&&ecCust.getDel_mark().intValue()==1){
//			super.renderJavaScript(response,
//					"window.onload=function(){alert('对不起该，客户已经停用');location.href='login.do'}");
//			return null;
//			}
//		}
		
		boolean f = true;
		if (!userInfo.getPass_word().equals(entity.getPass_word())) {
			f = false;
			if (userInfo.getPay_pwd() != null
					&& userInfo.getPay_pwd().equals(entity.getPass_word())) {
				f = true;
			}
		}
		if (!f) {
			String logmsg = createLoginErrLogs("table_ec_user_" + user_name,
					password, 5, 30);
			if (null != logmsg) {
				super.renderJavaScript(response,
						"window.onload=function(){alert('" + logmsg
								+ "');location.href='login.do'}");
				return null;
			}
			OperLog t = new OperLog();
			t.setOper_time(new Timestamp(Calendar.getInstance().getTime()
					.getTime()));
			t.setOper_ip(IpUtils.getIpAddr(request));
			t.setLink_tab("EC_USER");
			t.setLink_id(0l);
			String type = "账户名：" + user_name + ",密码：" + password;
			t.setOper_type(type);
			String name = "密码错误，登录管理系统失败";
			t.setPpdm_name(name);
			t.setOper_uid(0l);
			t.setOper_uname(user_name);
			getFacade().getOperLogService().createOperLog(t);

			msg = super.getMessage(request, "login.failed.password.invalid");
			super.renderJavaScript(response, "window.onload=function(){alert('"
					+ msg + "');location.href='login.do'}");
			return null;
		} else {
			CookieGenerator cg = new CookieGenerator();
			if (is_remember != null) {
				cg.setCookieMaxAge(60 * 60 * 24 * 14);
				cg.setCookieName("ec_user_name");
				cg.addCookie(response, URLEncoder.encode(user_name, "UTF-8"));
				cg.setCookieName("ec_password");
				cg.addCookie(response,
						URLEncoder.encode(entity.getPass_word(), "UTF-8"));
				cg.setCookieName("is_remember");
				cg.addCookie(response, URLEncoder.encode(is_remember, "UTF-8"));
			} else {
				cg.setCookieMaxAge(0);
				cg.setCookieName("ec_user_name");
				cg.removeCookie(response);
				cg.setCookieName("ec_password");
				cg.removeCookie(response);
				cg.setCookieName("is_remember");
				cg.removeCookie(response);
			}

			// update login count
			EcUser ui = new EcUser();
			ui.setId(userInfo.getId());
			ui.setLogin_count(userInfo.getLogin_count().longValue() + 1);
			ui.setLast_login_time(new Date());
			ui.setLast_login_ip(this.getIpAddr(request));
			getFacade().getEcUserService().modifyEcUser(ui);

			if (userInfo.getPay_pwd() != null
					&& !userInfo.getPay_pwd().equals(entity.getPass_word())) {
				session.setAttribute("touch", "1");
			} else {
				session.setAttribute("touch", "");
			}

			userInfo.setLogin_count(userInfo.getLogin_count().longValue() + 1);
			userInfo.setLast_login_time(ui.getLast_login_time());
			userInfo.setLast_login_ip(ui.getLast_login_ip());
			if (userInfo.getPay_pwd() == null
					|| "".equals(userInfo.getPay_pwd())) {
				userInfo.setPay_pwd(userInfo.getPass_word());
			}
			userInfo.getMap().put("password",
					new DESPlus().decrypt(userInfo.getPass_word()));
			userInfo.getMap().put("pay_pwd",
					new DESPlus().decrypt(userInfo.getPay_pwd()));
			if (userInfo.getCust_id() != null) {
				KonkaR3Shop r3shop = new KonkaR3Shop();
				r3shop.setId(userInfo.getCust_id());
				r3shop = super.getFacade().getKonkaR3ShopService()
						.getKonkaR3Shop(r3shop);
				if (r3shop != null && r3shop.getWeb_type() != null) {
					userInfo.getMap().put("web_type", r3shop.getWeb_type());
				}
			}
			session.setAttribute(Constants.EPP_USER, userInfo);
			session.setAttribute("memberUser", null);
			session.setAttribute("shopUser", null);
			session.setAttribute("touchUser", null);
			session.setAttribute("sfmallUser", null);
			OperLog t = new OperLog();
			t.setOper_time(new Timestamp(Calendar.getInstance().getTime()
					.getTime()));
			t.setOper_ip(IpUtils.getIpAddr(request));
			t.setLink_tab("EC_USER");
			t.setLink_id(userInfo.getId());
			String type = "账户名：" + userInfo.getUser_name() + ",密码："
					+ userInfo.getPass_word();
			t.setOper_type(type);
			String name = userInfo.getUser_name() + "-登录管理系统成功";
			t.setPpdm_name(name);
			if (userInfo != null) {
				t.setOper_uid(userInfo.getId());
				t.setOper_uname(userInfo.getUser_name());
			}
			getFacade().getOperLogService().createOperLog(t);
			EcShoppingCart shoppingCar = new EcShoppingCart();
			shoppingCar.setUser_id(userInfo.getId());
			shoppingCar.setOwn_sys(userInfo.getUser_type());
			List<EcShoppingCart> ecShoppingcarList = super.getFacade()
					.getEcShoppingCartService()
					.getEcShoppingCartList(shoppingCar);
			List<HashMap<String, String>> shoppingCarTableList = new ArrayList<HashMap<String, String>>();
			if (ecShoppingcarList != null && ecShoppingcarList.size() > 0) {
				for (EcShoppingCart ecShoppingCart : ecShoppingcarList) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("goods_id", "" + ecShoppingCart.getGoods_id());
					map.put("md_name", "" + ecShoppingCart.getMd_name());
					map.put("buy_num", "" + ecShoppingCart.getPd_num());
					map.put("price", "" + ecShoppingCart.getPrice());
					map.put("img_url", "" + ecShoppingCart.getImg_url());
					map.put("p_index", "" + ecShoppingCart.getP_index());
					map.put("service_ids", "" + ecShoppingCart.getService_ids());
					shoppingCarTableList.add(map);
				}
			}
			// 合并购物车
			List<HashMap<String, String>> shopingCarList = shoppingCarTableList;// ShoppingCarJsonUtil.joinShoppingCarList(shopingCarCookieList,shoppingCarTableList);

			EcShoppingCart esc = new EcShoppingCart();
			esc.setUser_id(userInfo.getId());
			esc.setOwn_sys(userInfo.getUser_type());
			super.getFacade().getEcShoppingCartService()
					.removeEcShoppingCartWithGoodsIdAndUserId(esc);
			// 写table
			List<HashMap<String, String>> newcookielist = new ArrayList<HashMap<String, String>>();
			for (int i = 0; i < shopingCarList.size() && i < 7; i++) {
				HashMap<String, String> carMap = new HashMap<String, String>();
				carMap = shopingCarList.get(i);
				EcShoppingCart ecShoppingCart = new EcShoppingCart();
				try {
					ecShoppingCart
							.setGoods_id(new Long(carMap.get("goods_id")));
					ecShoppingCart.setMd_name(carMap.get("md_name"));
					ecShoppingCart
							.setPd_num(new Integer(carMap.get("buy_num")));
					ecShoppingCart
							.setPrice(new BigDecimal(carMap.get("price")));
					ecShoppingCart.setImg_url(carMap.get("img_url"));
					ecShoppingCart.setUser_id(userInfo.getId());
					ecShoppingCart.setOwn_sys(userInfo.getUser_type());
					ecShoppingCart.setAdd_date(new Date());
					ecShoppingCart.setP_index(new Long(carMap.get("p_index")));
					String service_ids = carMap.get("service_ids");
					if ("null".equals(service_ids) || service_ids == null) {
						service_ids = "";
					}
					ecShoppingCart.setService_ids(service_ids);
					super.getFacade().getEcShoppingCartService()
							.createEcShoppingCart(ecShoppingCart);
					newcookielist.add(carMap);
				} catch (Exception e) {
				}
			}
			// 写cookie
			Cookie cookie = new Cookie("SHOPING_CAR_COOKIE", null);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			ShoppingCarJsonUtil shoppingCarUtil = new ShoppingCarJsonUtil();
			String shopingCarJson = shoppingCarUtil
					.toGoodsJsonsStr(newcookielist);
			CookieGenerator cGen = new CookieGenerator();
			cGen.setCookieMaxAge(60 * 60 * 24 * 14);
			cGen.setCookieName("SHOPING_CAR_COOKIE");
			cGen.addCookie(response, URLEncoder.encode(shopingCarJson, "UTF-8"));

			// 会员自动升级
			if (userInfo.getUser_type().intValue() == 1
					&& userInfo.getEcBaseCardLevel() != null
					&& userInfo.getEcBaseCardLevel().getCard_level_name() != null) {
				String inte = super.getFacade().getEcUserScoreRecService()
						.getEcUserScoreRecForPayJf(userInfo.getId());
				Integer jf = new Integer(0);
				if (inte != null && !"".equals(inte)) {
					jf = new Integer(inte);
				}
				if (userInfo.getEcBaseCardLevel().getCard_level_name()
						.equals("金卡会员")
						&& jf.intValue() >= 100000) {
					EcBaseCardLevel ecBaseCardLevel = new EcBaseCardLevel();
					ecBaseCardLevel.setCard_level_name("白金卡会员");
					List<EcBaseCardLevel> clevelList = super.getFacade()
							.getEcBaseCardLevelService()
							.getEcBaseCardLevelList(ecBaseCardLevel);
					EcBaseCardLevel clevel = clevelList.get(0);
					EcUserLevelAuditInfo auditInfo = new EcUserLevelAuditInfo();
					auditInfo.setCard_no(userInfo.getCard_no());
					auditInfo.setEc_user_id(userInfo.getId());
					auditInfo.setLevel_id(clevel.getCard_level());
					auditInfo.setLevel_name(clevel.getCard_level_name());
					auditInfo.setOld_level_name(userInfo.getEcBaseCardLevel()
							.getCard_level_name());
					auditInfo.setReal_name(userInfo.getReal_name());
					auditInfo.setMemo("系统自动升级");
					userInfo.setEcBaseCardLevel(clevel);
					EcBaseCardNo ecBaseCardNo = new EcBaseCardNo();
					ecBaseCardNo.setCard_no(userInfo.getCard_no());
					ecBaseCardNo.setCard_level(clevel.getCard_level());
					super.getFacade().getEcBaseCardNoService()
							.modifyEcBaseCardNoBYCardNo(ecBaseCardNo);
					super.getFacade().getEcUserLevelAuditInfoService()
							.createEcUserLevelAuditInfo(auditInfo);
				} else if (userInfo.getEcBaseCardLevel().getCard_level_name()
						.equals("银卡会员")
						&& jf.intValue() >= 30000) {
					EcBaseCardLevel ecBaseCardLevel = new EcBaseCardLevel();
					ecBaseCardLevel.setCard_level_name("金卡会员");
					List<EcBaseCardLevel> clevelList = super.getFacade()
							.getEcBaseCardLevelService()
							.getEcBaseCardLevelList(ecBaseCardLevel);
					EcBaseCardLevel clevel = clevelList.get(0);
					EcUserLevelAuditInfo auditInfo = new EcUserLevelAuditInfo();
					auditInfo.setCard_no(userInfo.getCard_no());
					auditInfo.setEc_user_id(userInfo.getId());
					auditInfo.setLevel_id(clevel.getCard_level());
					auditInfo.setLevel_name(clevel.getCard_level_name());
					auditInfo.setOld_level_name(userInfo.getEcBaseCardLevel()
							.getCard_level_name());
					auditInfo.setReal_name(userInfo.getReal_name());
					auditInfo.setMemo("系统自动升级");
					userInfo.setEcBaseCardLevel(clevel);
					EcBaseCardNo ecBaseCardNo = new EcBaseCardNo();
					ecBaseCardNo.setCard_no(userInfo.getCard_no());
					ecBaseCardNo.setCard_level(clevel.getCard_level());
					super.getFacade().getEcBaseCardNoService()
							.modifyEcBaseCardNoBYCardNo(ecBaseCardNo);
					super.getFacade().getEcUserLevelAuditInfoService()
							.createEcUserLevelAuditInfo(auditInfo);
				}

			}

			if (StringUtils.isNotBlank(url)) {
				String ctx = request.getContextPath();
				url = URLDecoder.decode(url, Constants.SYS_ENCODING);
				ctx = "'" + ctx + url + "';";
				super.renderJavaScript(response, "location.href=" + ctx);
				return null;
			} else {
				return mapping.findForward("success");
			}
		}
	}

	public ActionForward m(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String user_id = (String) request.getParameter("user_id");
		String username = (String) request.getParameter("username");
		String userpass = (String) request.getParameter("userpass");
		String mod_id = (String) request.getParameter("mod_id");// 手机端根据mod_id跳转不同的action
		// String url = (String) request.getParameter("url");
		String url = (String) request.getParameter("link_url");// 手机端根据url跳转不同的action

		EcUser ecUser = (EcUser) super.getSessionAttribute(request,
				Constants.EPP_USER);
		if (null != ecUser) {// 能拿到session
			if (StringUtils.isNotBlank(url)) {
				String ctx = request.getContextPath();
				url = URLDecoder.decode(url, Constants.SYS_ENCODING);
				ctx = "'" + ctx + url + "';";
				super.renderJavaScript(response, "location.href=" + ctx);
				return null;
			}
		}
		EcUser ui = new EcUser();
		String msg = "恭喜登录成功";
		Map map = new HashMap();
		ui.setIs_del(new Integer(0));
		String state = "0";
		if (StringUtils.isNotBlank(user_id) && GenericValidator.isLong(user_id)) {

			ui.setId(Long.valueOf(user_id));
			ui.setPass_word(new DESPlus().encrypt(userpass));
			ui = getFacade().getEcUserService().getEcUser(ui);
			if (ui == null) {
				ui = new EcUser();
				ui.setId(Long.valueOf(user_id));
				ui.setPay_pwd(new DESPlus().encrypt(userpass));
				ui = getFacade().getEcUserService().getEcUser(ui);
			}
		} else {
			ui = checkUser(username, userpass);
		}
		if (null == ui) {
			msg = "登录失败";
			map.put("msg", msg);
			map.put("state", state);
			super.renderJson(response, JSON.toJSONString(map));
			return null;
		} else {
			// update login count
			EcUser userInfo = new EcUser();
			userInfo.setId(ui.getId());
			userInfo.setLogin_count(ui.getLogin_count().longValue() + 1);
			userInfo.setLast_login_time(new Date());
			userInfo.setLast_login_ip(this.getIpAddr(request));
			getFacade().getEcUserService().modifyEcUser(userInfo);
		}
		session.setAttribute(Constants.EPP_USER, ui);
		if (ui.getPay_pwd() != null
				&& !ui.getPay_pwd().equals(ui.getPass_word())) {
			session.setAttribute("touch", "1");
		} else {
			session.setAttribute("touch", "");
		}
		session.setAttribute("memberUser", null);
		session.setAttribute("shopUser", null);
		session.setAttribute("touchUser", null);
		session.setAttribute("sfmallUser", null);
		state = "1";
		map.put("msg", msg);
		map.put("state", state);

		Map user = new HashMap();
		user.put("user_id", ui.getId());
		user.put("user_name", ui.getUser_name());
		user.put("user_real_name", ui.getReal_name());
		user.put("user_no", ui.getUser_no());
		user.put("user_type", ui.getUser_type());
		user.put("user_phone", ui.getLink_phone());
		user.put("user_email", ui.getEmail());
		user.put("user_card_no", ui.getCard_no());
		map.put("user", user);

		if (StringUtils.isNotBlank(url)) {
			String ctx = request.getContextPath();
			url = URLDecoder.decode(url, Constants.SYS_ENCODING);
			ctx = "'" + ctx + url + "';";
			super.renderJavaScript(response, "location.href=" + ctx);
			return null;
		}

		super.renderJson(response, JSON.toJSONString(map));
		return null;
	}

	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession(false);
		if (null != session) {
			session.removeAttribute(Constants.EPP_USER);
			session.invalidate();
		}
		request.setAttribute("isEnabledCode",
				super.getSysSetting("isEnabledCode"));
		setCookies2RequestScope(request);
		saveToken(request);
		// return mapping.findForward("login");

		String ctx = super.getCtxPath(request);
		String url = ctx + "/webservice/wap/login.do";
		response.sendRedirect(url);
		return null;
	}

	private void setCookies2RequestScope(HttpServletRequest request)
			throws Exception {
		Cookie user_name = WebUtils.getCookie(request, "ec_user_name");
		Cookie password = WebUtils.getCookie(request, "ec_password");
		Cookie is_remember = WebUtils.getCookie(request, "is_remember");

		if (null != user_name) {
			request.setAttribute("user_name",
					URLDecoder.decode(user_name.getValue(), "UTF-8"));
		}
		if (null != password) {
			request.setAttribute("password", DEFAULT_PASSWORD);
		}
		if (null != is_remember) {
			request.setAttribute("is_remember",
					URLDecoder.decode(is_remember.getValue(), "UTF-8"));
		}
	}

	@Override
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	protected EcUser checkUser(String username, String userpass)
			throws Exception {
		if (StringUtils.isBlank(username) || StringUtils.isBlank(userpass)) {
			return null;
		}
		EcUser user = new EcUser();
		EcUser user1;
		user.setUser_name(username.trim());
		user.setIs_del(0);
		/**
		 * 0-审核通过 1-待完善资料 2-待审核 3-审核不通过
		 */
		user.setPass_word(new DESPlus().encrypt(userpass));
		user1 = getFacade().getEcUserService().getEcUser(user);

		// 如果没有找到的话，找工卡验证
		if (user1 == null || user1.getId() == null) {
			user = new EcUser();
			user.setUser_name(null);
			user.setCard_no(username);
			user.setPass_word(new DESPlus().encrypt(userpass));
			user1 = getFacade().getEcUserService().getEcUser(user);
			if (user1 == null) {
				user = new EcUser();
				user.setUser_name(null);
				user.setCard_no(username);
				user.setPay_pwd(new DESPlus().encrypt(userpass));
				user1 = getFacade().getEcUserService().getEcUser(user);
			}
		}

		// 如果没有找到的话，找移动电话验证
		if (user1 == null || user1.getId() == null) {
			user = new EcUser();
			user.setUser_name(null);
			user.setLink_phone(username);
			user.setCard_no(null);
			user.setPass_word(new DESPlus().encrypt(userpass));
			user1 = getFacade().getEcUserService().getEcUser(user);
			if (user1 == null) {
				user = new EcUser();
				user.setUser_name(null);
				user.setLink_phone(username);
				user.setPay_pwd(new DESPlus().encrypt(userpass));
				user1 = getFacade().getEcUserService().getEcUser(user);
			}
		}

		if (user1 != null && user1.getCust_id() != null) {
			KonkaR3Shop r3shop = new KonkaR3Shop();
			r3shop.setId(user1.getCust_id());

			r3shop = super.getFacade().getKonkaR3ShopService()
					.getKonkaR3Shop(r3shop);
			if (r3shop != null && r3shop.getWeb_type() != null) {
				user1.getMap().put("web_type", r3shop.getWeb_type());
			}
		}
		return user1;
	}

}