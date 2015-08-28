package com.ebiz.mmt.web.struts.manager.admin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.ebiz.mmt.domain.KonkaSendMailInfo;
import com.ebiz.mmt.domain.KonkaSendMailUser;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.service.Facade;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author TUDP
 * @version 2013-11-02
 */
public class KonkaSendMailInfoAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		 
		Pager pager = (Pager) dynaBean.get("pager"); 
		String start_date=(String) dynaBean.get("start_date");
		String end_date=(String) dynaBean.get("end_date");
		KonkaSendMailInfo entity = new KonkaSendMailInfo(); 
		entity.getMap().put("add_date_start", start_date);
		entity.getMap().put("add_date_end", end_date);	

		Long recordCount = super.getFacade().getKonkaSendMailInfoService().getKonkaSendMailInfoCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaSendMailInfo> entityList = super.getFacade().getKonkaSendMailInfoService()
				.getKonkaSendMailInfoPaginatedList(entity);
 
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	 
		super.getModPopeDom(form, request); 		
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		
		KonkaSendMailUser mailUser = new KonkaSendMailUser();
		mailUser.setState(new Integer(1));
		//主送
		mailUser.setSend_type(new Integer(1));		
		List<KonkaSendMailUser> toList = super.getFacade().getKonkaSendMailUserService().getKonkaSendMailUserList(mailUser);
		request.setAttribute("toList", toList);
		//抄送
		mailUser.setSend_type(new Integer(2));		
		List<KonkaSendMailUser> ccList = super.getFacade().getKonkaSendMailUserService().getKonkaSendMailUserList(mailUser);
		request.setAttribute("ccList", ccList);
		
		String title=(String) dynaBean.get("title");
		String content=(String) dynaBean.get("content");
		if(title!=null){
			title = title.replaceAll("&nbsp;", " ");
			title = title.replaceAll("<br>", "\n");
			title = title.replaceAll("&gt;", ">");
			title = title.replaceAll("&lt;", "<");
			title = title.replaceAll("&#39;", "'");
		}
		if(content!=null){
		content = content.replaceAll("&nbsp;", " ");
		content = content.replaceAll("<br>", "\n");
		content = content.replaceAll("&gt;", ">");
		content = content.replaceAll("&lt;", "<");
		content = content.replaceAll("&#39;", "'");
		}
		KonkaSendMailInfo entity = new KonkaSendMailInfo(); 
		entity.setTitle(title);
		entity.setContent(content); 
		super.copyProperties(form, entity);
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;  		
		PeProdUser userInfo = super.getSessionUserInfo(request);
		KonkaSendMailInfo entity = new KonkaSendMailInfo();
		super.copyProperties(entity, form); 
		String title=entity.getTitle();
		if(title!=null){
			title = title.replaceAll("&nbsp;", " ");
			title = title.replaceAll("<br>", "\n");
			title = title.replaceAll("&gt;", ">");
			title = title.replaceAll("&lt;", "<");
			title = title.replaceAll("&#39;", "'");
			entity.setTitle(title);
		}
		String content=entity.getContent();
		if(content!=null){
		content = content.replaceAll("&nbsp;", " ");
		content = content.replaceAll("<br>", "\n");
		content = content.replaceAll("&gt;", ">");
		content = content.replaceAll("&lt;", "<");
		content = content.replaceAll("&#39;", "'");
		entity.setContent(content);
		}	
		
		String[] tos=request.getParameterValues("tos");
		String[] ccs=request.getParameterValues("ccs");

		String msg="";
		String to="";//主送
		String cc="";//抄送
		String to_name="";
		String cc_name="";
		String[] to_names= new String[]{};//多个主送
		String[] cc_names= new String[]{};//多个抄送
		
		KonkaSendMailUser mailUser = new KonkaSendMailUser();
		mailUser.setState(new Integer(1));
		//主送
		mailUser.setSend_type(new Integer(1));		
		if(tos!=null&&tos.length>0){
			log.info(tos[0]);
			mailUser.getMap().put("emails", tos);		
			List<KonkaSendMailUser> toList = super.getFacade().getKonkaSendMailUserService().getKonkaSendMailUserList(mailUser);
			if(toList !=null){
				KonkaSendMailUser sendUser = new KonkaSendMailUser();
				for(int i=0;i<toList.size();i++){
					sendUser = new KonkaSendMailUser();
					sendUser =toList.get(i);
					if(i==0){
						to = sendUser.getEmail();
						to_name = sendUser.getReal_name();
					}else{
						to += ","+sendUser.getEmail();
						to_name += ","+sendUser.getReal_name();					
					}
				}
				if(toList.size()>1){
					tos =to.split(",");
					to_names=to_name.split(",");
				}
			}
		}else{
			msg+="请选择发件人";
		}
		if(ccs!=null&&ccs.length>0){
			//抄送
			mailUser.setSend_type(new Integer(2));		
			mailUser.getMap().put("emails", ccs);		
			List<KonkaSendMailUser> ccList = super.getFacade().getKonkaSendMailUserService().getKonkaSendMailUserList(mailUser);
					
			if(ccList !=null){
				KonkaSendMailUser sendUser = new KonkaSendMailUser();
				for(int i=0;i<ccList.size();i++){
					sendUser = new KonkaSendMailUser();
					sendUser =ccList.get(i);
					if(i==0){
						cc = sendUser.getEmail();
						cc_name = sendUser.getReal_name();
					}else{
						cc += ","+sendUser.getEmail();
						cc_name += ","+sendUser.getReal_name();					
					}
				}
				if(ccList.size()>1){
					ccs =cc.split(",");
					cc_names=cc_name.split(",");
				}
			}
		} 
		
		Properties props = new Properties();
		try {
			props.load(getClass().getResourceAsStream("/mail.properties"));
		} catch (IOException e2) {		
			msg="  发生错误";
		} 
		String mailform=props.getProperty("mail.username");//发件人邮箱
		String senderName="康佳渠道管理";//发件人
		String body=entity.getContent();//内容
		String subject=entity.getTitle(); //主题

		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分"); 
	    Date date = new Date();
	    String date_str = df.format(date);
	    JavaMailSenderImpl mailSender=super.getMailSender();
		if(to!=null&&!"".equals(to)){
			try {
				logger.info("-------------->>开始发送邮件");
				mailSender.setDefaultEncoding("gbk");
				MimeMessage mailMessage = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);			
				
				
				if(tos!=null&&tos.length>1){
					messageHelper.setTo(tos);
				}else{
					messageHelper.setTo(to);
				}
				if(ccs!=null&&ccs.length>1){
					messageHelper.setCc(ccs);
				}else if(cc!=null&&!"".equals(cc)){
					messageHelper.setCc(cc);
				}
				
				messageHelper.setFrom(mailform);
				try {
					InternetAddress add = new  InternetAddress(mailform,   senderName);
					messageHelper.setFrom(add);
				} catch (UnsupportedEncodingException e) {logger.error(e.toString()); msg+="邮件发送发生错误";}
				 
				messageHelper.setSubject(subject);
				messageHelper.setText(body, true); 
				messageHelper.setSentDate(new Date());		
				if("".equals(msg)){//如果发生错误就不发送邮件了
				mailSender.send(mailMessage);
				}
				logger.info("-------------->>邮件发送结束");
			} catch (MessagingException e1) { 
				logger.error(e1.toString());
				msg+="邮件发送发生错误";
			}
		}else{
				msg+="收件人为空，请设置收件人信息";
		}
			//保存邮件发送信息
			KonkaSendMailInfo mailInfo = new KonkaSendMailInfo();
			String to_name_str = "";
			if(tos!=null&&tos.length>1){
				for(int i=0;i<tos.length;i++){
					if(to_name_str.length()<1800){
						if(i==0){
							to_name_str = to_names[i]+"<"+tos[i]+">";	
						}else{
							to_name_str += ";"+to_names[i]+"<"+tos[i]+">";
						}
					} 
				}
			}else{
				to_name_str = to_name+"<"+to+">";
			}
			if(to_name_str.length()>=1800){
				to_name_str+="...";
			}
			
			String cc_name_str = "";
			if(ccs!=null&&ccs.length>1){
				for(int i=0;i<ccs.length;i++){
					if(cc_name_str.length()<1800){
						if(i==0){
						cc_name_str = cc_names[i]+"<"+ccs[i]+">";	
						}else{
						cc_name_str += ";"+cc_names[i]+"<"+ccs[i]+">";	
						}
					}
				}
			}else{
				cc_name_str = cc_name+"<"+cc+">";
			}
			if(cc_name_str.length()>=1800){
				cc_name_str+="...";
			}
			
			mailInfo.setTo_name(to_name_str);
			mailInfo.setCc_nane(cc_name_str);
			mailInfo.setTitle(subject+msg);
			mailInfo.setContent(body);
			mailInfo.setSend_name(senderName);	
			mailInfo.setAdd_date(date);
			
	 
		super.getFacade().getKonkaSendMailInfoService().createKonkaSendMailInfo(mailInfo);
        if("".equals(msg)){
        	msg="邮件发送成功";
        	super.renderJavaScript(response,"alert('"+msg+"!');");
    		return null; 
        }
		super.renderJavaScript(response,"alert('"+msg+"!');history.back();");
		return null; 
	}

	public ActionForward sendMail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String msg=this.autoSendMail(super.getFacade(), super.getMailSender());
		 if("".equals(msg)){
	        	msg="邮件发送成功";
	        	super.renderJavaScript(response,"alert('"+msg+"!');");
	    		return null; 
	      }
		super.renderJavaScript(response,"alert('"+msg+"!');history.back();");
		return null; 
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id"); 
		String[] pks = (String[]) dynaBean.get("pks");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaSendMailInfo entity = new KonkaSendMailInfo(); 
			entity.setId(new Long(id));
			getFacade().getKonkaSendMailInfoService().removeKonkaSendMailInfo(entity);

			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaSendMailInfo entity = new KonkaSendMailInfo();
			entity.getMap().put("pks", pks);
			super.getFacade().getKonkaSendMailInfoService().removeKonkaSendMailInfo(entity);

			saveMessage(request, "entity.deleted");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id); 
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "pks", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	} 

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

		KonkaSendMailInfo entity = new KonkaSendMailInfo();
		entity.setId(new Long(id));
		entity = super.getFacade().getKonkaSendMailInfoService().getKonkaSendMailInfo(entity);

		if (null == entity) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
		entity.setQueryString(super.serialize(request, "id", "mod_id"));
		request.setAttribute("entity", entity);
 
		return mapping.findForward("view");
	}
	
	public String autoSendMail(Facade facade,JavaMailSenderImpl mailSender) {
		String msg="";
		String to="";//主送
		String cc="";//抄送
		String[] tos= new String[]{};//多个主送
		String[] ccs= new String[]{};//多个抄送
		
		String to_name="";
		String cc_name="";
		String[] to_names= new String[]{};//多个主送
		String[] cc_names= new String[]{};//多个抄送
		
		KonkaSendMailUser entity = new KonkaSendMailUser();
		entity.setState(new Integer(1));
		//主送
		entity.setSend_type(new Integer(1));		
		List<KonkaSendMailUser> toList = facade.getKonkaSendMailUserService().getKonkaSendMailUserList(entity);
		if(toList !=null){
			KonkaSendMailUser sendUser = new KonkaSendMailUser();
			for(int i=0;i<toList.size();i++){
				sendUser = new KonkaSendMailUser();
				sendUser =toList.get(i);
				if(i==0){
					to = sendUser.getEmail();
					to_name = sendUser.getReal_name();
				}else{
					to += ","+sendUser.getEmail();
					to_name += ","+sendUser.getReal_name();					
				}
			}
			if(toList.size()>1){
				tos =to.split(",");
				to_names=to_name.split(",");
			}
		}
		
		//抄送
		entity.setSend_type(new Integer(2));		
		List<KonkaSendMailUser> ccList = facade.getKonkaSendMailUserService().getKonkaSendMailUserList(entity);
				
		if(ccList !=null){
			KonkaSendMailUser sendUser = new KonkaSendMailUser();
			for(int i=0;i<ccList.size();i++){
				sendUser = new KonkaSendMailUser();
				sendUser =ccList.get(i);
				if(i==0){
					cc = sendUser.getEmail();
					cc_name = sendUser.getReal_name();
				}else{
					cc += ","+sendUser.getEmail();
					cc_name += ","+sendUser.getReal_name();					
				}
			}
			if(ccList.size()>1){
				ccs =cc.split(",");
				cc_names=cc_name.split(",");
			}
		}
		//组合收件人 保存到数据库
		String to_name_str = "";
		if(tos!=null&&tos.length>1){
			for(int i=0;i<tos.length;i++){
				if(to_name_str.length()<1800){
					if(i==0){
						to_name_str = to_names[i]+"<"+tos[i]+">";	
					}else{
						to_name_str += ";"+to_names[i]+"<"+tos[i]+">";
					}
				} 
			}
		}else{
			to_name_str = to_name+"<"+to+">";
		}
		if(to_name_str.length()>=1800){
			to_name_str+="...";
		}
		
		String cc_name_str = "";
		if(ccs!=null&&ccs.length>1){
			for(int i=0;i<ccs.length;i++){
				if(cc_name_str.length()<1800){
					if(i==0){
					cc_name_str = cc_names[i]+"<"+ccs[i]+">";	
					}else{
					cc_name_str += ";"+cc_names[i]+"<"+ccs[i]+">";	
					}
				}
			}
		}else{
			cc_name_str = cc_name+"<"+cc+">";
		}
		if(cc_name_str.length()>=1800){
			cc_name_str+="...";
		}
		
		
		Properties props = new Properties();
		try {
			props.load(getClass().getResourceAsStream("/mail.properties"));
		} catch (IOException e2) {		
			msg="  发生错误";
		} 
		String form=props.getProperty("mail.username");//发件人邮箱
		String senderName=props.getProperty("mail.senderName");//发件人
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分"); 
	    Date date = new Date();
	    String date_str = df.format(date);
	    
	    String row=props.getProperty("sendinfo.rows");//发件人邮箱
	    String[] rows =new String[]{row};
	    if(row!=null&&!"".equals(row)){
	    	rows=row.trim().split(",");
	    }else{
	    	rows = new String[]{""};	
	    }
	   for(int r=0;r<rows.length;r++){
		String subject=props.getProperty("sendinfo.title"+rows[r])+date_str; //主题
		String url=props.getProperty("sendinfo.url"+rows[r]);

		String body="";//内容

		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpGet httpget = new HttpGet(url);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			body = httpclient.execute(httpget, responseHandler);
			logger.info("-------------->>邮件内容\n"+url+body);
		} catch (ClientProtocolException e) {
			msg="读取内容出错";
			logger.error(e.getMessage());
		} catch (IOException e) {
			msg="读取内容出错";
			logger.error(e.getMessage());
		} 
		
		if(to!=null&&!"".equals(to)){
			try {
				logger.info("-------------->>开始发送邮件"+r);
				mailSender.setDefaultEncoding("gbk");
				MimeMessage mailMessage = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);		 
				if(tos!=null&&tos.length>1){
					messageHelper.setTo(tos);
				}else{
					messageHelper.setTo(to);
				}
				if(ccs!=null&&ccs.length>1){
					messageHelper.setCc(ccs);
				}else if(cc!=null&&!"".equals(cc)){
					messageHelper.setCc(cc);
				}
				
				messageHelper.setFrom(form);
				try {
					InternetAddress add = new   InternetAddress(form,   senderName);
					messageHelper.setFrom(add);
				} catch (UnsupportedEncodingException e) {logger.error(e.toString()); msg+="邮件发送发生错误";}
				 
				messageHelper.setSubject(subject);
				messageHelper.setText(body, true); 
				messageHelper.setSentDate(new Date());		
				if("".equals(msg)){//如果发生错误就不发送邮件了
				mailSender.send(mailMessage);
				}
				logger.info("-------------->>邮件发送结束"+r);
			} catch (MessagingException e1) { 
				logger.error(e1.toString());
				msg+="邮件发送发生错误";
			}
		}else{
			msg+="收件人为空，请设置收件人信息";
		}
		
		//保存邮件发送信息
		KonkaSendMailInfo mailInfo = new KonkaSendMailInfo();
		
		mailInfo.setTo_name(to_name_str);
		mailInfo.setCc_nane(cc_name_str);
		mailInfo.setTitle(subject+msg);
		mailInfo.setContent(body);
		mailInfo.setSend_name(senderName);	
		mailInfo.setAdd_date(date);
		facade.getKonkaSendMailInfoService().createKonkaSendMailInfo(mailInfo);
	   }
	   return msg;
	}
}
