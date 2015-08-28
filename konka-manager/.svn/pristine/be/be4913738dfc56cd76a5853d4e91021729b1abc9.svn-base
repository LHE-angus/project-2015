package com.ebiz.mmt.web.struts.jxc;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.StdEntpUser;
import com.ebiz.mmt.web.Constants;

/**
 * @author Wu,Yang
 * @version 2011-10-10
 */
public class IndexAction extends BaseJxcAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.index(mapping, form, request, response);
	}

	public ActionForward index(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String userName = (String) dynaBean.get("userName");// 客户端传过来的用户名
		logger.info("keySeq:{}", keySeq);
		logger.info("userName:{}", userName);

		// StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		// if (null == user) {
		// return null;
		// }
		// Long shop_id = user.getStdEntpMain().getShop_id();

		session.setAttribute(Constants.IS_LOCAL, this.isLocal(request));
		// session.setAttribute("KONKA_BRAND_ID", Constants.KONKA_BRAND_ID);
		if (StringUtils.isNotBlank(keySeq)) {
			session.setAttribute("JXC_TEST_KEY_VALUE", keySeq);
		} else {
			session.setAttribute("JXC_TEST_KEY_VALUE", Constants.JXC_TEST_KEY_VALUE);
		}

		if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(keySeq)) {
			StdEntpUser stdEntpUser = new StdEntpUser();
			// stdEntpUser.setUser_name(userName);
			stdEntpUser.setKey_seq(keySeq);
			stdEntpUser.setUser_state(0);
			List<StdEntpUser> stdEntpUserList = getFacade().getStdEntpUserService()
					.getStdEntpUserListWithOwnStdEntpMain(stdEntpUser);
			if (null != stdEntpUserList && stdEntpUserList.size() > 0) {
				String[] keySeqs = new String[stdEntpUserList.size()];
				int i = 0;
				for (StdEntpUser seu : stdEntpUserList) {
					keySeqs[i++] = seu.getKey_seq();
				}
				session.setAttribute(Constants.USER_OWN_ENTP_KEYS, keySeqs);
			}
		}

		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, (String) session
				.getAttribute("JXC_TEST_KEY_VALUE"));
		if (null == user) {
			return null;
		}

		Long shop_id = user.getStdEntpMain().getShop_id();

		HashMap<String, String> result = super.getKonkaDeptIdAndType(shop_id);
		logger.info("result.get('result_code'):{}", result.get("result_code"));
		if ("0".endsWith(result.get("result_code"))) {
			// 吴洋，正式发布时，去掉隐藏代码
			dynaBean.set("shopIsNotBindKonkaR3", "true");
		}
		// if (!super.judgeShopIdIsMatchKonkaR3(shop_id)) {
		// // 吴洋，正式发布时，去掉隐藏代码
		// dynaBean.set("shopIsNotBindKonkaR3", "true");
		// }

		return mapping.findForward("success");
	}

	private boolean isLocal(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		boolean isLocal = false;

		String requestIP = super.getIpAddr(request);
		// logger.warn("===requestIP:{}", requestIP);
		if ("127.0.0.1".equals(requestIP)) {// localhost of ipv4
			isLocal = true;
		} else if (requestIP.length() >= 8 && "192.168.".equals(requestIP.substring(0, 8))) {// lan of ipv4
			isLocal = true;
		} else if ("::1".equals(requestIP)) {// localhost of ipv6
			isLocal = true;
		} else if (requestIP.length() >= 14 && "0:0:0:0:0:0:0:".equals(requestIP.substring(0, 14))) {// lan of ipv6
			isLocal = true;
		}
		if (StringUtils.isNotBlank(userName)) {// 客户端传过来的用户名
			isLocal = false;
		}
		logger.info("==是否是本地端登录:{}", isLocal);
		return isLocal;
	}

}
