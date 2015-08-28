package com.ebiz.mmt.web.struts.epp.weixin;

import java.nio.charset.Charset;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.util.StreamUtils;

import com.ebiz.mmt.web.struts.member.BaseMemberAction;
import com.opensymphony.oscache.util.StringUtil;
import com.weixin.popular.bean.EventMessage;
import com.weixin.popular.bean.xmlmessage.XMLTextMessage;
import com.weixin.popular.util.SignatureUtil;
import com.weixin.popular.util.XMLConverUtil;

/**
 * @author TUDP
 * @version 2014-10-24
 */
public class IndexAction extends BaseMemberAction {
	
	private static String token="epp.konka.com";
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form; 
		ServletInputStream inputStream = request.getInputStream();
		ServletOutputStream outputStream = response.getOutputStream();
		String signature = (String)dynaBean.get("signature");//request.getParameter("signature");
		String timestamp = (String)dynaBean.get("timestamp");//request.getParameter("timestamp");
		String nonce = (String)dynaBean.get("nonce"); //request.getParameter("nonce");
		//System.out.println("signature: "+signature+"timestamp:"+timestamp+"nonce:"+nonce);
		String echostr =(String)dynaBean.get("echostr");
		if(!StringUtil.isEmpty(echostr)){
			outputStream.write(echostr.getBytes("utf-8")); 
		    return null;
		}else if(StringUtil.isEmpty(nonce)){
			outputStream.write("no msg".getBytes("utf-8")); 
		    return null;
		}
		try{
			//验证请求签名
		    if(!signature.equals(SignatureUtil.generateEventMessageSignature(token,timestamp,nonce))){
		        //System.out.println("The request signature is invalid");
		        outputStream.write("".getBytes("utf-8")); 
			    return null;
		    } 
		}catch(Exception ex){
			ex.printStackTrace();
		}
	    if(inputStream!=null){
	        String xml = StreamUtils.copyToString(inputStream, Charset.forName("utf-8"));
	        //System.out.println("xml: "+xml);
	        //转换XML
	        EventMessage eventMessage = XMLConverUtil.convertToObject(EventMessage.class,xml); 
	        //创建回复
	        XMLTextMessage xmlTextMessage = new XMLTextMessage(
	                eventMessage.getFromUserName(), 
	                eventMessage.getToUserName(),
	                "你好"+eventMessage.getEventKey());
	        //回复
	        xmlTextMessage.outputStreamWrite(outputStream);
	        return null;
	    }
	    outputStream.write("".getBytes("utf-8")); 
	    return null;
	}

	public ActionForward index(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("list");
	} 
}
