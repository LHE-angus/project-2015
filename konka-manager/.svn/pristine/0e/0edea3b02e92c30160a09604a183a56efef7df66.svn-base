package com.ebiz.mmt.web.struts.sfmall;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.Md5Encrypt;

public class EcBackPasswordAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.add(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		logger.info("--------------->i coming in");
		return mapping.findForward("input");
	}

	/**
	 * @desc 注册保存
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public ActionForward nextStep(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String card_no = (String) dynaBean.get("card_no");
		String email = (String) dynaBean.get("email");
		String real_name = (String) dynaBean.get("real_name");
		String msg = "";

		if (StringUtils.isBlank(card_no) || StringUtils.isBlank(real_name) || StringUtils.isBlank(email)) {
			super.renderJavaScript(response, "alert('参数丢失！');history.back();");
			return null;
		}

		EcUser entity = new EcUser();
		entity.setCard_no(card_no);
		entity.setReal_name(real_name);
		entity.setEmail(email);
		List<EcUser> entityList = super.getFacade().getEcUserService().getEcUserList(entity);

		if (null == entityList || entityList.size() == 0) {
			msg = "对不起！您的工卡号，姓名，邮箱匹配不成功！";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');window.history.back();}");
			return null;
		} else if (entityList.size() > 1) {
			msg = "对不起！您的工卡号重复！";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');window.history.back();}");
			return null;
		} else {
			entity.setEmail(email);
			entity.setReal_name(real_name);
			entity.setCard_no(card_no);
			Date date = new Date();
			Long ss = date.getTime();
			Md5Encrypt md5 = new Md5Encrypt();
			String key = md5.md5(ss.toString() + card_no);
			logger.info("ssssssssss------>" + ss);
			logger.info("ssssssssss------>" + key);
			entity.getMap().put("key", key);
			entity.setId(entityList.get(0).getId());

			String mms = "";
			Properties props = new Properties();
			try {
				props.load(getClass().getResourceAsStream("/mail.properties"));
			} catch (IOException e2) {
				mms = " 对不起， 发生错误  注册失败";
			}
			if (!"".equals(mms)) {
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "'); history.back();}");
				return null;
			}

			String mailForm = "kaixinmao@konka.com";
			String senderName = "开心猫";
			JavaMailSenderImpl mailSender = super.getMailSender();
			mailSender.setUsername(mailForm);
			mailSender.setPassword("kaixinmao123");

			//String mailForm = props.getProperty("mail.username");// 发件人邮箱
			//String senderName = props.getProperty("mail.senderName");

			// Long id =
			// super.getFacade().getEcUserService().createEcUserAndSendEmail(entity,
			// mailSender, mailForm,
			// senderName, request);
			Long id = super.getFacade().getEcUserService().createEcUserAndSendEmail(entity, super.getMailSender(),
			        mailForm, senderName, request);
			if (null != id) {
				String s1 = "请去您的邮箱修改密码！";
				super.renderJavaScript(response, "window.onload=function(){alert('" + s1 + "');location.href='"
				        + super.getCtxPath(request) + "/sfmall/login.do';}");
				return null;
			}

		}

		return null;

	}

}
