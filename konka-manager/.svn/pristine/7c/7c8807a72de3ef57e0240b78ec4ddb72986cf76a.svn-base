package com.ebiz.mmt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcBackPasswordDao;
import com.ebiz.mmt.dao.EcBaseCardNoDao;
import com.ebiz.mmt.dao.EcCustDao;
import com.ebiz.mmt.dao.EcCustRelUserDao;
import com.ebiz.mmt.dao.EcUserDao;
import com.ebiz.mmt.dao.PeRoleUserDao;
import com.ebiz.mmt.domain.EcBackPassword;
import com.ebiz.mmt.domain.EcBaseCardNo;
import com.ebiz.mmt.domain.EcCust;
import com.ebiz.mmt.domain.EcCustRelUser;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.service.EcUserService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
@Service
public class EcUserServiceImpl implements EcUserService {

	@Resource
	private EcUserDao ecUserDao;

	@Resource
	private EcBaseCardNoDao ecBaseCardNoDao;

	@Resource
	private PeRoleUserDao peRoleUserDao;

	@Resource
	private EcCustDao ecCustDao;

	@Resource
	private EcBackPasswordDao ecBackPasswordDao;

	@Resource
	private EcCustRelUserDao ecCustRelUserDao;

	public Long createEcUser(EcUser t) {

		Long id = this.ecUserDao.insertEntity(t);

		// 触网绑定客户的时候，绑定客户表
		if (t.getMap().get("touch") != null) {
			String cust_id = (String) t.getMap().get("touch");

			EcCustRelUser eu = new EcCustRelUser();
			eu.setCust_id(Long.valueOf(cust_id));
			eu.setUser_id(id);
			this.ecCustRelUserDao.insertEntity(eu);

		}

		return id;
	}

	public EcUser getEcUser(EcUser t) {
		return this.ecUserDao.selectEntity(t);
	}

	public Long getEcUserCount(EcUser t) {
		return this.ecUserDao.selectEntityCount(t);
	}

	public List<EcUser> getEcUserList(EcUser t) {
		return this.ecUserDao.selectEntityList(t);
	}

	public int modifyEcUser(EcUser t) {
		if (t.getMap().get("touch") != null) {
			String cust_id = (String) t.getMap().get("touch");

			EcUser ee = new EcUser();
			ee.setId(t.getId());
			ee = this.ecUserDao.selectEntity(ee);
			if (ee.getCust_id() != null && !ee.getCust_id().toString().equals(cust_id)) {

				EcCustRelUser eeu = new EcCustRelUser();
				eeu.setUser_id(t.getId());
				eeu = this.ecCustRelUserDao.selectEntity(eeu);
				if (eeu != null) {
					EcCustRelUser eu = new EcCustRelUser();
					eu.setId(eeu.getId());
					eu.setUser_id(t.getId());
					eu.setCust_id(Long.valueOf(cust_id));
					this.ecCustRelUserDao.updateEntity(eu);
				} else {
					EcCustRelUser eu = new EcCustRelUser();
					eu.setUser_id(t.getId());
					eu.setCust_id(Long.valueOf(cust_id));
					this.ecCustRelUserDao.insertEntity(eu);
				}

			} else if (ee.getCust_id() != null && ee.getCust_id().toString().equals(cust_id)) {
				EcCustRelUser eeu = new EcCustRelUser();
				eeu.setUser_id(t.getId());
				eeu = this.ecCustRelUserDao.selectEntity(eeu);
				if (eeu == null) {
					EcCustRelUser eu = new EcCustRelUser();
					eu.setUser_id(t.getId());
					eu.setCust_id(Long.valueOf(cust_id));
					this.ecCustRelUserDao.insertEntity(eu);
				}
			}
		}
		int n = this.ecUserDao.updateEntity(t);
		return n;
	}

	public int removeEcUser(EcUser t) {
		return this.ecUserDao.deleteEntity(t);
	}

	public List<EcUser> getEcUserPaginatedList(EcUser t) {
		return this.ecUserDao.selectEntityPaginatedList(t);
	}

	public List<EcUser> getEcUserWithPositionNameAndFullDeptNamePaginatedList(EcUser t) {
		return this.ecUserDao.selectEcUserWithPositionNameAndFullDeptNamePaginatedList(t);
	}

	public Long getEcUserForHydjCount(EcUser t) {
		return this.ecUserDao.selectEcUserForHydjCount(t);
	}

	public List<EcUser> getEcUserPaginatedForHydjList(EcUser t) {
		return this.ecUserDao.selectEcUserPaginatedForHydjList(t);
	}

	public int modifyEcUserWithMultiRoleUser(EcUser t) {
		int n = this.ecUserDao.updateEntity(t);

		// 先删后增
		PeRoleUser deRoleUser = new PeRoleUser();
		deRoleUser.setUser_id(t.getId());
		this.peRoleUserDao.deleteEntity(deRoleUser);

		String roleIds = (String) t.getMap().get("roleIds");
		if (null != roleIds) {
			String[] role_id = StringUtils.split(roleIds, ",");
			for (int i = 0; i < role_id.length; i++) {
				PeRoleUser _ru = new PeRoleUser();
				_ru.setUser_id(t.getId());
				_ru.setRole_id(Long.valueOf(role_id[i]));
				this.peRoleUserDao.insertEntity(_ru);
			}
		}

		return n;
	}

	public Long createEcUserAndSendEmail(EcUser t, JavaMailSenderImpl mailSender, String mailForm, String senderName,
			HttpServletRequest request) {

		EcBackPassword ecb = new EcBackPassword();
		ecb.setAdd_date(new Date());
		ecb.setCard_no(t.getCard_no());
		ecb.setEmail(t.getEmail());
		ecb.setYz_key((String) t.getMap().get("key"));
		ecb.setReal_name(t.getReal_name());
		Long id = this.ecBackPasswordDao.insertEntity(ecb);

		String msg = "";
		String to = "";//
		to = t.getEmail();
		String body = "";// 内容
		String subject = "";// 主题

		body = "尊敬的会员！您好：请点击下面的链接修改密码：<br/>";
		StringBuffer ctx = new StringBuffer();
		ctx.append(request.getScheme()).append("://").append(request.getServerName()).append(":")
				.append(request.getServerPort());
		ctx.append(request.getContextPath());
		String ss = ctx.toString();
		String key = "";
		String s0 = "开心猫内部购机优惠平台 对您致以诚挚的问候！<br/>我们收到了对与此电子邮件地址关联的密码进行重置的请求。如果您提出了该请求，请按照以下说明操作。<br/>如果您没有尝试过重置密码，不用担心，您的帐户仍然安全，我们未将您帐户的访问权授予任何人。您可以安心地忽略此电子邮件。<br/>要重置 开心猫内部购机优惠平台  帐户密码，单击以下链接即可。该链接会将您转至一个可以创建新密码的网页。<br/>";

		try {
			key = (String) t.getMap().get("key");
			//System.out.println("key----------->" + key);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		body = s0
				+ ss
				+ "/webservice/"
				+ "IsAct.do"
				+ "?key="
				+ key
				+ "</br>请注意，该链接将在发送此电子邮件 24 小时后失效。如果单击链接后无法打开网页，则可以将链接复制并粘贴到浏览器的地址栏中。</br>我们乐于为您提供帮助。希望您很快再次访问 epp.konka.com。再次感谢您成为 开心猫内部购机优惠平台  的会员。 ";
		//System.out.println("body---------------->{}" + body);
		//System.out.println("-------------->>邮件内容\n" + body);
		subject = "修改密码！";
		if (to != null && !"".equals(to)) {
			try {
				//System.out.println("-------------->>开始发送邮件");
				mailSender.setDefaultEncoding("gbk");
				MimeMessage mailMessage = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);
				messageHelper.setTo(to);
				messageHelper.setFrom(mailForm);
				// try {
				// InternetAddress add = new InternetAddress(mailForm,
				// senderName);
				// messageHelper.setFrom(add);
				// } catch (UnsupportedEncodingException e) {
				// //System.out.println(e.toString());
				// msg += "邮件发送发生错误";
				// }
				messageHelper.setSubject(subject);
				messageHelper.setText(body, true);
				messageHelper.setSentDate(new Date());
				if ("".equals(msg)) {// 如果发生错误就不发送邮件了
					mailSender.send(mailMessage);
				}
				//System.out.println("-------------->>邮件发送结束");
			} catch (MessagingException e1) {
				//System.out.println(e1.toString());
				msg += "邮件发送发生错误";
			}
		} else {
			msg += "收件人为空，请设置收件人信息";
		}
		return id;
	}

	public Long createEcUserAndSendEmail4(EcUser t, JavaMailSenderImpl mailSender, String mailForm, String senderName,
			HttpServletRequest request) {

		EcBackPassword ecb = new EcBackPassword();
		ecb.setAdd_date(new Date());
		ecb.setCard_no(t.getUser_name());
		ecb.setEmail(t.getEmail());
		ecb.setYz_key((String) t.getMap().get("key"));
		ecb.setReal_name(t.getUser_name());
		Long id = this.ecBackPasswordDao.insertEntity(ecb);

		String msg = "";
		String to = "";//
		to = t.getEmail();
		String body = "";// 内容
		String subject = "";// 主题

		body = "尊敬的会员！您好：请点击下面的链接修改密码：<br/>";
		StringBuffer ctx = new StringBuffer();
		ctx.append(request.getScheme()).append("://").append(request.getServerName()).append(":")
				.append(request.getServerPort());
		ctx.append(request.getContextPath());
		String ss = ctx.toString();
		String key = "";
		String s0 = "康佳触网直销商城 对您致以诚挚的问候！<br/>我们收到了对与此电子邮件地址关联的密码进行重置的请求。如果您提出了该请求，请按照以下说明操作。<br/>如果您没有尝试过重置密码，不用担心，您的帐户仍然安全，我们未将您帐户的访问权授予任何人。您可以安心地忽略此电子邮件。<br/>要重置 康佳触网直销商城  帐户密码，单击以下链接即可。该链接会将您转至一个可以创建新密码的网页。<br/>";

		try {
			key = (String) t.getMap().get("key");
			//System.out.println("key----------->" + key);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		body = s0
				+ ss
				+ "/webservice/"
				+ "IsAct3.do"
				+ "?key="
				+ key
				+ "</br>请注意，该链接将在发送此电子邮件 24 小时后失效。如果单击链接后无法打开网页，则可以将链接复制并粘贴到浏览器的地址栏中。</br>我们乐于为您提供帮助。再次感谢您成为 康佳触网直销商城  的会员。 ";
		//System.out.println("body---------------->{}" + body);
		//System.out.println("-------------->>邮件内容\n" + body);
		subject = "修改密码！";
		if (to != null && !"".equals(to)) {
			try {
				//System.out.println("-------------->>开始发送邮件");
				mailSender.setDefaultEncoding("gbk");
				MimeMessage mailMessage = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);
				messageHelper.setTo(to);
				messageHelper.setFrom(mailForm);
				// try {
				// InternetAddress add = new InternetAddress(mailForm,
				// senderName);
				// messageHelper.setFrom(add);
				// } catch (UnsupportedEncodingException e) {
				// //System.out.println(e.toString());
				// msg += "邮件发送发生错误";
				// }
				messageHelper.setSubject(subject);
				messageHelper.setText(body, true);
				messageHelper.setSentDate(new Date());
				if ("".equals(msg)) {// 如果发生错误就不发送邮件了
					mailSender.send(mailMessage);
				}
				//System.out.println("-------------->>邮件发送结束");
			} catch (MessagingException e1) {
				//System.out.println(e1.toString());
				msg += "邮件发送发生错误";
			}
		} else {
			msg += "收件人为空，请设置收件人信息";
		}
		return id;
	}

	public Long createEcUserAndSendEmail3(EcUser t, JavaMailSenderImpl mailSender, String mailForm, String senderName,
			HttpServletRequest request) {

		EcBackPassword ecb = new EcBackPassword();
		ecb.setAdd_date(new Date());
		ecb.setCard_no(t.getUser_name());
		ecb.setEmail(t.getEmail());
		ecb.setYz_key((String) t.getMap().get("key"));
		ecb.setReal_name(t.getReal_name());
		Long id = this.ecBackPasswordDao.insertEntity(ecb);

		String msg = "";
		String to = "";//
		to = t.getEmail();
		String body = "";// 内容
		String subject = "";// 主题

		body = "尊敬的会员！您好：请点击下面的链接修改密码：<br/>";
		StringBuffer ctx = new StringBuffer();
		ctx.append(request.getScheme()).append("://").append(request.getServerName()).append(":")
				.append(request.getServerPort());
		ctx.append(request.getContextPath());
		String ss = ctx.toString();
		String key = "";
		String s0 = "开心猫内部购机优惠平台 对您致以诚挚的问候！<br/>我们收到了对与此电子邮件地址关联的密码进行重置的请求。如果您提出了该请求，请按照以下说明操作。<br/>如果您没有尝试过重置密码，不用担心，您的帐户仍然安全，我们未将您帐户的访问权授予任何人。您可以安心地忽略此电子邮件。<br/>要重置 开心猫内部购机优惠平台  帐户密码，单击以下链接即可。该链接会将您转至一个可以创建新密码的网页。<br/>";

		try {
			key = (String) t.getMap().get("key");
			//System.out.println("key----------->" + key);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		body = s0
				+ ss
				+ "/webservice/"
				+ "IsAct2.do"
				+ "?key="
				+ key
				+ "</br>请注意，该链接将在发送此电子邮件 24 小时后失效。如果单击链接后无法打开网页，则可以将链接复制并粘贴到浏览器的地址栏中。</br>我们乐于为您提供帮助。希望您很快再次访问 epp.konka.com。再次感谢您成为 开心猫内部购机优惠平台  的会员。 ";
		//System.out.println("body---------------->{}" + body);
		//System.out.println("-------------->>邮件内容\n" + body);
		subject = "修改密码！";
		if (to != null && !"".equals(to)) {
			try {
				//System.out.println("-------------->>开始发送邮件");
				mailSender.setDefaultEncoding("gbk");
				MimeMessage mailMessage = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);
				messageHelper.setTo(to);
				messageHelper.setFrom(mailForm);
				// try {
				// InternetAddress add = new InternetAddress(mailForm,
				// senderName);
				// messageHelper.setFrom(add);
				// } catch (UnsupportedEncodingException e) {
				// //System.out.println(e.toString());
				// msg += "邮件发送发生错误";
				// }
				messageHelper.setSubject(subject);
				messageHelper.setText(body, true);
				messageHelper.setSentDate(new Date());
				if ("".equals(msg)) {// 如果发生错误就不发送邮件了
					mailSender.send(mailMessage);
				}
				//System.out.println("-------------->>邮件发送结束");
			} catch (MessagingException e1) {
				//System.out.println(e1.toString());
				msg += "邮件发送发生错误";
			}
		} else {
			msg += "收件人为空，请设置收件人信息";
		}
		return id;
	}

	public int createEcUserAndSendEmail2(EcUser t, JavaMailSenderImpl mailSender, String mailForm, String senderName,
			HttpServletRequest request) {
		int id = 0;
		String msg = "";
		String to = "";//
		to = t.getEmail();
		String body = "";// 内容
		String subject = "";// 主题

		// body = "欢迎光临！！";
		StringBuffer ctx = new StringBuffer();
		ctx.append(request.getScheme()).append("://").append(request.getServerName()).append(":")
				.append(request.getServerPort());
		ctx.append(request.getContextPath());
		String ss = ctx.toString();
		id = this.ecUserDao.updateEntity(t);
		if (t.getIs_act().intValue() == 0) {
			body += "<div style=\"line-height:28px;\"><b>亲爱的" + t.getReal_name()
					+ "，您好！</b><br/>欢迎您注册开心猫内部购机优惠平台。</div>";
			body += "<div style=\"line-height:28px;\">恭喜您的账号已经审核通过！<br/>";
			body += "今后，您可使用工卡号和密码登陆网站，享受开心猫内部购机平台为您提供的优质服务。<br/>";
			body += "希望您使用 开心猫内部购机优惠平台 开心愉快！请前往 http://epp.konka.com/member/login.do  购物，祝您购物愉快！";
			body += "</div> ";
			body += "<div style=\"margin:15px;\"> ";
			body += "<a href=\"http://epp.konka.com/member/KonkaGroupPeArticleInfo.do?method=view&amp;id=755139\" target=\"_blank\">";
			body += "<img src=\"http://epp.konka.com:80/styles/member/images/ec_hygz.jpg\" alt=\"会员规则\" border=\"0\"></a>";
			body += "<a href=\"http://epp.konka.com/member/KonkaGroupPeArticleInfo.do?method=view&amp;id=753541\"  target=\"_blank\">";
			body += "<img src=\"http://epp.konka.com:80/styles/member/images/ec_hyqy.jpg\" alt=\"会员权益\" border=\"0\"></a> ";
			body += "<a href=\"http://epp.konka.com/member/KonkaGroupPeArticleInfo.do?method=view&amp;id=755186\" target=\"_blank\">";
			body += "<img src=\"http://epp.konka.com:80/styles/member/images/ec_jfgz.jpg\" alt=\"积分规则\" border=\"0\"></a>";
			body += "<a href=\"http://epp.konka.com/member/KonkaGroupPeArticleInfo.do?method=view&amp;id=755187\" target=\"_blank\">";
			body += "<img src=\"http://epp.konka.com:80/styles/member/images/ec_yjgz.jpg\" alt=\"佣金规则\" border=\"0\"></a> ";
			body += "</div>";
			body += "<p>谨致问候，</p>";
			body += "<p style=\"margin:15px;\">开心猫内部购机优惠平台 客户服务</p>";
			body += "<p style=\"margin-left:15px;\"><a href=\"http://epp.konka.com/\" target=\"_blank\">epp.konka.com</a> </p>";
			body += "<div style=\"margin-left:15px;\">";
			body += "<p>请注意：此电子邮件由无法接受外部电子邮件的自动通知系统发送。请不要答复此邮件。 </p>";
			body += "<div>服务热线：0755-61368827 (工作时间：周一至周五，9:00 - 18:00)</div>";
			body += "<div>客服邮箱：<a target=\"_blank\" href=\"mailto:kaixinmao@konka.com\">kaixinmao@konka.com</a>(工作时间：周一至周日，9:00 - 18:00) </div>";
			body += "</div>";
		} else if (t.getIs_act().intValue() == 3) {
			body = "尊敬的会员，您好！您的账号审核未通过！请联系网站管理员！";
		}
		subject = "账号激活信息！";
		if (to != null && !"".equals(to)) {
			try {
				//System.out.println("-------------->>开始发送邮件");
				mailSender.setDefaultEncoding("gbk");
				MimeMessage mailMessage = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);
				messageHelper.setTo(to);
				messageHelper.setFrom(mailForm);
				messageHelper.setSubject(subject);
				messageHelper.setText(body, true);
				messageHelper.setSentDate(new Date());
				if ("".equals(msg)) {// 如果发生错误就不发送邮件了
					mailSender.send(mailMessage);
				}
				//System.out.println("-------------->>邮件发送结束");
			} catch (MessagingException e1) {
				msg += "邮件发送发生错误";
			}
		} else {
			msg += "收件人为空，请设置收件人信息";
		}
		return id;
	}

	public int createEcUserAndSendEmail5(EcUser t, JavaMailSenderImpl mailSender, String mailForm, String senderName,
			HttpServletRequest request) {
		int id = 0;
		String msg = "";
		String to = "";//
		to = t.getEmail();
		String body = "";// 内容
		String subject = "";// 主题

		// body = "欢迎光临！！";
		StringBuffer ctx = new StringBuffer();
		ctx.append(request.getScheme()).append("://").append(request.getServerName()).append(":")
				.append(request.getServerPort());
		ctx.append(request.getContextPath());
		String ss = ctx.toString();
		id = this.ecUserDao.updateEntity(t);
		if (t.getIs_act().intValue() == 0) {
			body += "<div style=\"line-height:28px;\"><b>亲爱的" + t.getReal_name() + "，您好！</b><br/>欢迎您注册康佳触网直销商城。</div>";
			body += "<div style=\"line-height:28px;\">恭喜您的账号已经审核通过！<br/>";
			body += "今后，您可使用用户名和密码登陆网站，享受康佳触网直销商城为您提供的优质服务。<br/>";
			body += "希望您使用 康佳触网直销商城 开心愉快！祝您购物愉快！";
			body += "</div> ";
			body += "<p>谨致问候，</p>";
			body += "<div style=\"margin-left:15px;\">";
			body += "<p>请注意：此电子邮件由无法接受外部电子邮件的自动通知系统发送。请不要答复此邮件。 </p>";
			body += "<div>服务热线：0755-61368827 (工作时间：周一至周五，9:00 - 18:00)</div>";
			body += "<div>客服邮箱：<a target=\"_blank\" href=\"mailto:kaixinmao@konka.com\">kaixinmao@konka.com</a>(工作时间：周一至周日，9:00 - 18:00) </div>";
			body += "</div>";
		} else if (t.getIs_act().intValue() == 3) {
			body = "尊敬的会员，您好！您的账号审核未通过！请联系网站管理员！";
		}
		subject = "账号激活信息！";
		if (to != null && !"".equals(to)) {
			try {
				//System.out.println("-------------->>开始发送邮件");
				mailSender.setDefaultEncoding("gbk");
				MimeMessage mailMessage = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);
				messageHelper.setTo(to);
				messageHelper.setFrom(mailForm);
				messageHelper.setSubject(subject);
				messageHelper.setText(body, true);
				messageHelper.setSentDate(new Date());
				if ("".equals(msg)) {// 如果发生错误就不发送邮件了
					mailSender.send(mailMessage);
				}
				//System.out.println("-------------->>邮件发送结束");
			} catch (MessagingException e1) {
				msg += "邮件发送发生错误";
			}
		} else {
			msg += "收件人为空，请设置收件人信息";
		}
		return id;
	}

	public int modifyEcUserAndEid(EcUser t) {
		this.ecUserDao.updateEntity(t);

		EcBackPassword ec = new EcBackPassword();
		String e_id = (String) t.getMap().get("e_id");
		//System.out.println("aaaaaaaaa---->" + e_id);
		ec.setId(Long.valueOf(e_id));
		ec.setState(1);
		this.ecBackPasswordDao.updateEntity(ec);
		return 0;
	}

	@Override
	public Long getSubEcUserByUserNameCount(EcUser t) {
		return this.ecUserDao.selectSubEcUserByUserNameCount(t);
	}

	@Override
	public List<EcUser> getSubEcUserByUserNameList(EcUser t) {
		return this.ecUserDao.selectSubEcUserByUserNameList(t);
	}

	@Override
	public Long createEcUserForFgs(EcUser t) {
		Long id = this.ecUserDao.insertEntity(t);
		EcBaseCardNo en = new EcBaseCardNo();
		en.setCard_no(t.getCard_no());
		en.setCard_level(802L);
		this.ecBaseCardNoDao.insertEcBaseCardNo(en);
		return id;
	}

	@Override
	public Long getEcUserNewCount(EcUser t) throws DataAccessException {
		return this.ecUserDao.selectEcUserNewCount(t);
	}

	@Override
	public List<EcUser> getEcUserNewPaginatedList(EcUser t) throws DataAccessException {
		return this.ecUserDao.selectEcUserNewPaginatedList(t);
	}

	@Override
	public Long createEcUserForZc(EcUser t) {
		if(t.getCust_id()==null){
			String csut_name = (String) t.getMap().get("cust_name");
			String cust_code = (String) t.getMap().get("cust_code");
			EcCust ecCust = new EcCust();
			ecCust.setAdd_date(new Date());
			ecCust.setAdd_user_id(0L);
			ecCust.setCust_code(cust_code);
			ecCust.setCust_name(csut_name);
			ecCust.setDel_mark(0);
			ecCust.setCust_type(1);// 0--r3客户 1--虚拟客户
			ecCust.setGroup_id(0L);
			Long cust_id = this.ecCustDao.insertEntity(ecCust);
			t.setCust_id(cust_id);
		}
		String user_no = "";
		Long no = this.ecUserDao.selectEcUserNo(t);
		user_no = "ZC" + no.toString();
		t.setUser_no(user_no);
		t.setCard_no(user_no);

		Long ecuser_id = this.ecUserDao.insertEntity(t);

		EcCustRelUser eu = new EcCustRelUser();
		eu.setCust_id(t.getCust_id());
		eu.setUser_id(ecuser_id);
		this.ecCustRelUserDao.insertEntity(eu);

		return ecuser_id;
	}

	@Override
	public Long getEcUserNo(EcUser t) throws DataAccessException {
		return this.ecUserDao.selectEcUserNo(t);
	}

}
