package com.ebiz.mmt.web.struts.inter.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.web.struts.inter.form.InterUser;
import com.ebiz.mmt.web.struts.inter.form.ValidationForm;
import com.ebiz.mmt.web.util.IpUtils;

/**
 * @author tudp
 * @version 2014-09-19
 * @see 验证接口是否可以访问
 */
public class ValidationAction extends BaseInterAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		InterUser user = super.getInterUser(form, request);

		ValidationForm obj = new ValidationForm();
		// 检查用户授权信息验证是否可以访问 0正常 1失败
		obj.setReturn_state(1);
		obj.setReturn_msg("用户信息：" + user.toString() + "");
		obj.setReq_ip(IpUtils.getRemortIP(request));
		obj.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
		super.renderJson(response, JSON.toJSONString(obj));
		return null;
	}
}
