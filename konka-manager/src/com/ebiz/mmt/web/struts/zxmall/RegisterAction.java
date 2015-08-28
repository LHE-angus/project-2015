package com.ebiz.mmt.web.struts.zxmall;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.ebiz.mmt.domain.ArticleImg;
import com.ebiz.mmt.domain.EcBaseCardNo;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaPeArticleInfo;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;

public class RegisterAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.add(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception { 

		// 取资讯
		KonkaPeArticleInfo article = new KonkaPeArticleInfo();
		article.setArticle_type_id(3200L);
		article.setStates(1L); // 已发布
		article.setMsg_info_type(0); // 公开
		article.setIs_del(0L);
		article.getRow().setCount(1);
		List<KonkaPeArticleInfo> articleList = super.getFacade().getKonkaPeArticleInfoService()
		        .getKonkaPeArticleInfoList(article);
		if (articleList != null && articleList.size() > 0) {
			Long id = articleList.get(0).getId();
			KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
			entity.setId(Long.valueOf(id));
			entity = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfo(entity);
			request.setAttribute("entity", entity);
		}

		return mapping.findForward("input");
	}

	/**
	 * @desc 注册保存
	 * @return
	 * @throws Exception
	 */
	public ActionForward nextStep(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String card_no = (String) dynaBean.get("card_no");
		String member_name = (String) dynaBean.get("member_name");
		String verificationCode = (String) dynaBean.get("verificationCode");
		String msg = "";

		HttpSession session = request.getSession();
		if (StringUtils.isBlank(card_no) || StringUtils.isBlank(member_name)) {
			super.renderJavaScript(response, "alert('参数丢失！');history.back();");
			return null;
		}
		if (StringUtils.isBlank(verificationCode)) {
			msg = "验证码不能为空";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');window.history.back();}");
			return null;
		}
		if (!verificationCode.equals((String) session.getAttribute("verificationCode"))) {
			msg = "验证码不正确";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');window.history.back();}");
			return null;
		}

		EcBaseCardNo entity = new EcBaseCardNo();
		// entity.getMap().put("card_no_upper", card_no);
		entity.setCard_no(card_no);
		entity.setMember_name(member_name);
		Long count = super.getFacade().getEcBaseCardNoService().getEcBaseCardNoCount(entity);
		EcUser ec = new EcUser();
		ec.setCard_no(card_no);
		Long count1 = super.getFacade().getEcUserService().getEcUserCount(ec);
		if (count == 0) {
			msg = "对不起！你的工卡号与姓名匹配不成功！";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');window.history.back();}");
			return null;
		} else if (count > 0 && count1 > 0) {
			msg = "对不起！你的工卡号已经注册！";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');window.history.back();}");
			return null;
		}
		dynaBean.set("card_no", card_no);
		dynaBean.set("sex", "2");
		dynaBean.set("real_name", member_name);

		ArticleImg img = new ArticleImg();
		img.setNews_module(100010L + 0L); // 在线商城首页轮播图
		img.getMap().put("today", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
		img.setInfo_state(new Short("1"));
		img.getRow().setCount(5); // 数量
		List<ArticleImg> imgList = super.getFacade().getArticleImgService().getArticleImgList(img);
		request.setAttribute("imgList", imgList);

		return new ActionForward("/../zxmall/Register/step_two.jsp");

	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String card_no = (String) dynaBean.get("card_no");
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");
		String msg = "";

		EcUser entity = new EcUser();
		super.copyProperties(entity, form);

		if (entity.getIs_xx_user() == null) {
			entity.setIs_xx_user(0);
		}
		if (GenericValidator.isLong(province) && !GenericValidator.isLong(city)) {
			entity.setP_index(Long.valueOf(province));
		}
		if (GenericValidator.isLong(province) && GenericValidator.isLong(city) && !GenericValidator.isLong(country)) {
			entity.setP_index(Long.valueOf(city));
		}
		if (GenericValidator.isLong(province) && GenericValidator.isLong(city) && GenericValidator.isLong(country)
		        && !GenericValidator.isLong(town)) {
			entity.setP_index(Long.valueOf(country));
		}
		if (GenericValidator.isLong(province) && GenericValidator.isLong(city) && GenericValidator.isLong(country)
		        && GenericValidator.isLong(town)) {
			entity.setP_index(Long.valueOf(town));
		}
		if (entity.getUser_name() != null && !"".equals(entity.getUser_name())) {
			EcUser t = new EcUser();
			t.setUser_name(entity.getUser_name());
			List<EcUser> ecUserList = super.getFacade().getEcUserService().getEcUserList(t);
			if (ecUserList != null && ecUserList.size() > 0) {
				msg = "该用户名已经存在";
			}
		}
		logger.info(entity.getUser_name() + ":" + msg);
		Properties props = new Properties();
		try {
			props.load(getClass().getResourceAsStream("/mail.properties"));
		} catch (IOException e2) {
			msg = " 对不起， 发生错误  注册失败";
		}
		if (!"".equals(msg)) {
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "'); history.back();}");
			return null;
		}

		String mailForm = props.getProperty("mail.username");// 发件人邮箱
		String senderName = props.getProperty("mail.senderName");

		EcUser prodUser = new EcUser();
		prodUser.setIs_del(0);
		DESPlus des = new DESPlus();
		entity.setPass_word(des.encrypt(entity.getPass_word()));
		entity.setAdd_date(new Date());
		entity.setUser_type(1);// 工卡用户
		entity.setCard_no(card_no);
		Long id = super.getFacade().getEcUserService().createEcUserAndSendEmail(entity, super.getMailSender(),
		        mailForm, senderName, request);
		if (null != id) {
			String ss = "请去您的邮箱激活验证！";
			super.renderJavaScript(response, "window.onload=function(){alert('" + ss + "');location.href='"
			        + super.getCtxPath(request) + "/zxmall/login.do';}");
		}
		return null;
	}

	public ActionForward validateName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		StringBuffer oper = new StringBuffer("{\"result\":");
		String user_name = (String) dynaBean.get("user_name");
		EcUser entity = new EcUser();
		entity.setUser_name(user_name);
		// entity.setIs_del(0);
		Long count = super.getFacade().getEcUserService().getEcUserCount(entity);
		if ("0".equals(count.toString())) {
			oper.append(false);
		} else {
			oper.append(true);
		}
		oper.append("}");
		super.render(response, oper.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

	public void sendMail(EcUser t, JavaMailSenderImpl mailSender) {
		String msg = "";
		String to = "";//

		to = t.getEmail();

		Properties props = new Properties();
		try {
			props.load(getClass().getResourceAsStream("/mail.properties"));
		} catch (IOException e2) {
			msg = "  发生错误";
		}
		String form = props.getProperty("mail.username");// 发件人邮箱
		String senderName = props.getProperty("mail.senderName");// 发件人

		// SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
		// Date date = new Date();
		// String date_str = df.format(date);

		String body = "";// 内容
		String subject = "";// 主题

		body = "欢迎光临！！";
		logger.info("-------------->>邮件内容\n" + body);
		subject = "激活账号！";
		if (to != null && !"".equals(to)) {
			try {
				logger.info("-------------->>开始发送邮件");
				mailSender.setDefaultEncoding("gbk");
				MimeMessage mailMessage = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);
				messageHelper.setTo(to);
				messageHelper.setFrom(form);
				try {
					InternetAddress add = new InternetAddress(form, senderName);
					messageHelper.setFrom(add);
				} catch (UnsupportedEncodingException e) {
					logger.error(e.toString());
					msg += "邮件发送发生错误";
				}

				messageHelper.setSubject(subject);
				messageHelper.setText(body, true);
				messageHelper.setSentDate(new Date());
				if ("".equals(msg)) {// 如果发生错误就不发送邮件了
					mailSender.send(mailMessage);
				}
				logger.info("-------------->>邮件发送结束");
			} catch (MessagingException e1) {
				logger.error(e1.toString());
				msg += "邮件发送发生错误";
			}
		} else {
			msg += "收件人为空，请设置收件人信息";
		}

	}
}
