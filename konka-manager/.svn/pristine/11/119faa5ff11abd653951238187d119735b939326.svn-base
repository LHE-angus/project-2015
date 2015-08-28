package com.ebiz.mmt.web.struts.webservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.EcBaseCardLevel;
import com.ebiz.mmt.domain.EcBaseCardNo;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcUserLevelAuditInfo;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.member.BaseMemberAction;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * @author TUDP
 * @version 2013-12-17
 */
public class EcUserLoginForMobileAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(); 
		DynaBean dynaBean = (DynaBean) form;  
		String p_index =  (String) dynaBean.get("p_index");
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");
		EcUser ecUser= new EcUser();// = (EcUser) session.getAttribute(Constants.EPP_USER);


		ecUser = super.checkUser(username, userpass);
		if(ecUser!=null){
			if(p_index==null&&ecUser!=null&&ecUser.getUser_type().intValue()==2){
				p_index=ecUser.getP_index().toString(); 
			}
			if(p_index ==null || "".equals(p_index)){
				try{
					p_index = super.getPIndexByRequest(request);   
				}catch(Exception e){
					p_index = "440300";//默认等于深圳
				}
			}
			if(ecUser.getUser_type().intValue()==1){
				if(new DESPlus().encrypt(userpass).equals(ecUser.getPass_word())){
					session.setAttribute("touch", null);
				}else{
					session.setAttribute("touch", 1);
				}
			}else{
				session.setAttribute("touch", null);
			}
			session.setAttribute(Constants.EPP_USER,ecUser);
			session.setAttribute("sfmallUser", null);
			session.setAttribute("shopUser", null);
			session.setAttribute("memberUser", null);
			session.setAttribute("touchUser", null); 
			ecUser.setP_index(Long.valueOf(p_index));
			
			//会员自动升级	
			if(ecUser.getUser_type().intValue()==1&&ecUser.getEcBaseCardLevel()!=null&&ecUser.getEcBaseCardLevel().getCard_level_name()!=null){
				String inte= super.getFacade().getEcUserScoreRecService().getEcUserScoreRecForPayJf(ecUser.getId());
				Integer jf =new Integer(0);
				if(inte!=null&&!"".equals(inte)){ jf=new Integer(inte); } 
				if (ecUser.getEcBaseCardLevel().getCard_level_name().equals("金卡会员")&&jf.intValue()>=100000){
					EcBaseCardLevel ecBaseCardLevel = new EcBaseCardLevel(); 
					ecBaseCardLevel.setCard_level_name("白金卡会员");
					List<EcBaseCardLevel> clevelList=super.getFacade().getEcBaseCardLevelService().getEcBaseCardLevelList(ecBaseCardLevel);
					EcBaseCardLevel clevel= clevelList.get(0);					
					EcUserLevelAuditInfo auditInfo = new EcUserLevelAuditInfo();
					auditInfo.setCard_no(ecUser.getCard_no());
					auditInfo.setEc_user_id(ecUser.getId());
					auditInfo.setLevel_id(clevel.getCard_level());
					auditInfo.setLevel_name(clevel.getCard_level_name());
					auditInfo.setOld_level_name(ecUser.getEcBaseCardLevel().getCard_level_name());  
					auditInfo.setReal_name(ecUser.getReal_name());
					auditInfo.setMemo("系统自动升级"); 
					ecUser.setEcBaseCardLevel(clevel);
					EcBaseCardNo ecBaseCardNo = new EcBaseCardNo();
					ecBaseCardNo.setCard_no(ecUser.getCard_no());
					ecBaseCardNo.setCard_level(clevel.getCard_level());
					super.getFacade().getEcBaseCardNoService().modifyEcBaseCardNoBYCardNo(ecBaseCardNo);
					super.getFacade().getEcUserLevelAuditInfoService().createEcUserLevelAuditInfo(auditInfo);	
				}else if (ecUser.getEcBaseCardLevel().getCard_level_name().equals("银卡会员")&&jf.intValue()>=30000){
					EcBaseCardLevel ecBaseCardLevel = new EcBaseCardLevel(); 
					ecBaseCardLevel.setCard_level_name("金卡会员");
					List<EcBaseCardLevel> clevelList=super.getFacade().getEcBaseCardLevelService().getEcBaseCardLevelList(ecBaseCardLevel);
					EcBaseCardLevel clevel= clevelList.get(0);					
					EcUserLevelAuditInfo auditInfo = new EcUserLevelAuditInfo();
					auditInfo.setCard_no(ecUser.getCard_no());
					auditInfo.setEc_user_id(ecUser.getId());
					auditInfo.setLevel_id(clevel.getCard_level());
					auditInfo.setLevel_name(clevel.getCard_level_name());
					auditInfo.setOld_level_name(ecUser.getEcBaseCardLevel().getCard_level_name());  
					auditInfo.setReal_name(ecUser.getReal_name());
					auditInfo.setMemo("系统自动升级"); 
					ecUser.setEcBaseCardLevel(clevel);
					EcBaseCardNo ecBaseCardNo = new EcBaseCardNo();
					ecBaseCardNo.setCard_no(ecUser.getCard_no());
					ecBaseCardNo.setCard_level(clevel.getCard_level());
					super.getFacade().getEcBaseCardNoService().modifyEcBaseCardNoBYCardNo(ecBaseCardNo);
					super.getFacade().getEcUserLevelAuditInfoService().createEcUserLevelAuditInfo(auditInfo);	
					
				} 
			}			
			super.renderJson(response, JSON.toJSONString(ecUser));
			return null;
		}else{
			if(ecUser == null) 
				ecUser = new 	EcUser();
			ecUser.setId(-1L);		//验证失败
			super.renderJson(response, JSON.toJSONString(ecUser));
		 	return null;
		}
	} 
}