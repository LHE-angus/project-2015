package com.ebiz.mmt.web.struts;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ebiz.mmt.domain.MmtStdEntpUser;
import com.ebiz.mmt.domain.MmtUserInfo;
import com.ebiz.mmt.web.Constants;

/**
 * @author Chen,WeiWei
 * @version 2011-3-22
 */
public class MmstLoginAction extends BaseAction {
	// 限制地区的p_index
	// private static final String limit_root_p_index = "340000";

	// 验证传递进来的省市县根代码是否有权限
	@SuppressWarnings("unused")
	private boolean validRootPIndex(String rootPIndex) {
		// 没有限制地区
		return true;
		// return StringUtils.equals(rootPIndex, limit_root_p_index);
	}

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.loginByKeySeq(mapping, form, request, response);
	}

	public ActionForward loginByKeySeq(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");

		//HttpSession session = request.getSession();

		if (StringUtils.isBlank(keySeq)) {
			String msg = super.getMessage(request, "login.failed.param.keySeq.notExists");
			renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		MmtStdEntpUser stdEntpUser = new MmtStdEntpUser();
		stdEntpUser.setKey_seq(keySeq);
		stdEntpUser = super.getFacade().getMmtStdEntpUserService().getMmtStdEntpUser(stdEntpUser);
		if (stdEntpUser == null) {
			String msg = super.getMessage(request, "login.failed.keySeq.notExists");
			renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		} else {
			// 通过密钥值，查到MMT用户-》shop_id->SX_SHOP中是否存在，是否审核通过？
			MmtUserInfo userInfo = new MmtUserInfo();
			userInfo.setUser_name(stdEntpUser.getMmt_user());
			userInfo = super.getFacade().getMmtUserInfoService().getMmtUserInfo(userInfo);
			if (userInfo == null) {
				String msg = super.getMessage(request, "login.failed.keySeq.username.notExists");
				renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			// 直接登录系统
			request.getSession().setAttribute(Constants.MMT_USER_INFO, userInfo);
			return new ActionForward("/branch/manager/Frames.do", true);
		}
	}

}
