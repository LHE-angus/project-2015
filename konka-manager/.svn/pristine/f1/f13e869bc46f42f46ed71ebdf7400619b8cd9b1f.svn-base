package com.ebiz.mmt.web.struts.webservice.sso;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.OperLog;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.SsoOaUserBind;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.manager.oa.IpUtils;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * @author TUDP
 * @version 2014-11-27
 * @desc sso登陆
 */
public class QdglAction extends BaseAction {
	public static String sso_url="http://127.0.0.1:9090/konka-wd/webservice/sso/Qdgl.do";

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String ticket = (String) dynaBean.get("ticket"); 
		if (StringUtils.isNotBlank(ticket)) {
			return this.list(mapping, form, request, response);
		} else { 
			super.renderJavaScript(
					response,
					"window.onload=function(){location.href='http://sso.konka.com/sso/login?service="+request.getRequestURL().toString()+"'}");
			return null;
		}
	}
    /**
     * sso登陆渠道管理系统
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String ticket = (String) dynaBean.get("ticket");
	
		if (ticket != null) {
			try { 
				String msg="";
				String url = "http://sso.konka.com/sso/validate?ticket="+ ticket+ "&service="+request.getRequestURL().toString()+"";
				java.net.URL l_url = new java.net.URL(url);
				java.net.HttpURLConnection l_connection = (java.net.HttpURLConnection) l_url.openConnection();
				try{
					l_connection.connect();
				}catch(java.net.UnknownHostException e){
					msg ="网络连接异常[sso服务连接不上]，请稍后再试";
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');window.close();'}");
					return null;
				}
				java.io.BufferedReader l_reader = new java.io.BufferedReader(
						new java.io.InputStreamReader(
								l_connection.getInputStream()));
				String sCurrentLine = "";
				String sTotalString = "";
				while ((sCurrentLine = l_reader.readLine()) != null) {
					sTotalString += sCurrentLine;
				}
				if (sTotalString.indexOf("yes") > -1) {
					HttpSession session = request.getSession();
					String samaccount_name = sTotalString.substring(3);
					if (StringUtils.isNotBlank(ticket)) {
					 SsoOaUserBind bind = new SsoOaUserBind();
					 bind.setSamaccount_name(samaccount_name);
					 bind.setLink_tab("KONKA_PE_PROD_USER");
					 List<SsoOaUserBind> bindUserList =super.getFacade().getSsoOaUserBindService().getSsoOaUserBindList(bind);
					 if(bindUserList!=null&&bindUserList.size()>0){
						 bind=bindUserList.get(0); 
						 if(bind.getState().intValue()==1){
							msg ="对不起,用户绑定已失效";
							super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='"+super.getCtxPath(request)+"/login.do'}");
							return null;
						 }
						 PeProdUser entity = new PeProdUser();
						 entity.setId(bind.getUser_id());
						 entity.setUser_name(bind.getUser_name());
						 entity.setIs_del(0);
						 entity.getMap().put("user_type_in", "0,1"); 
						 List<PeProdUser> userInfoList = getFacade().getPeProdUserService().getPeProdUserList(entity);
						 
						 if (null == userInfoList || userInfoList.size() == 0) { 
								OperLog t = new OperLog();
								t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
								t.setOper_ip(IpUtils.getIpAddr(request));
								t.setLink_tab("KONKA_PE_PROD_USER");
								t.setLink_id(0l);
								String type = "账户名：" + samaccount_name ;
								t.setOper_type(type);
								String name = samaccount_name + "绑定用户失效，从sso登录管理系统失败";
								t.setPpdm_name(name);
								t.setOper_uid(0l);
								t.setOper_uname(samaccount_name);
								getFacade().getOperLogService().createOperLog(t);

								msg = "绑定用户失效，从sso登录管理系统失败";
								super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='"+super.getCtxPath(request)+"/login.do'}");
								return null;
						} else if (userInfoList.size() > 1) {
								OperLog t = new OperLog();
								t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
								t.setOper_ip(IpUtils.getIpAddr(request));
								t.setLink_tab("KONKA_PE_PROD_USER");
								t.setLink_id(0l);
								String type = "账户名：" + samaccount_name ;
								t.setOper_type(type);
								String name = samaccount_name + "绑定用户失效，从sso登录管理系统失败";
								t.setPpdm_name(name);
								t.setOper_uid(0l);
								t.setOper_uname(samaccount_name);
								getFacade().getOperLogService().createOperLog(t);

								msg = "绑定用户失效，从sso登录管理系统失败";
								super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='"+super.getCtxPath(request)+"/login.do'}");
								return null;
						}
						PeProdUser userInfo = userInfoList.get(0);
						PeProdUser ui = new PeProdUser();// update login count
						ui.setId(userInfo.getId());
						ui.setLogin_count(userInfo.getLogin_count().longValue() + 1);
						ui.setLast_login_time(new Date());
						ui.setLast_login_ip(this.getIpAddr(request));
						getFacade().getPeProdUserService().modifyPeProdUser(ui);

						userInfo.setLogin_count(userInfo.getLogin_count().longValue() + 1);
						userInfo.setLast_login_time(ui.getLast_login_time());
						userInfo.setLast_login_ip(ui.getLast_login_ip());

						PeRoleUser _peRoleUser = new PeRoleUser();
						_peRoleUser.setUser_id(userInfo.getId());
						List<PeRoleUser> peRoleUserList = super.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

						boolean role_id_lt_10000 = false;
						if (null != peRoleUserList && peRoleUserList.size() > 0) {
							for (PeRoleUser peRoleUser : peRoleUserList) {
								if (peRoleUser.getRole_id() < 10000L) {
									role_id_lt_10000 = true;
									break;
								}
							}
						}
						if (!role_id_lt_10000) {// 无系统角色
								KonkaDept dept_fgs = super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
								if (null != dept_fgs && userInfo.getCust_id() == null) {// 分公司用户 非客户
									PeRoleUser ru = new PeRoleUser();
									ru.getMap().put("user_ids", new String[] { String.valueOf(userInfo.getId()) });
									ru.setRole_id(32L); // 分公司工作人员
									super.getFacade().getPeRoleUserService().createPeRoleUser(ru);
								}
						}

						userInfo.getMap().put("password", new DESPlus().decrypt(userInfo.getPass_word()));
						session.setAttribute(Constants.USER_INFO, userInfo);
						session.setAttribute(Constants.ROLE_INFO, super.getRoleInfoByThisLogin(request));
						peRoleUserList = super.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
						if (null != peRoleUserList && peRoleUserList.size() > 0) {
								String[] roleNames = new String[peRoleUserList.size()];
								int i = 0;
								for (PeRoleUser peRoleUser : peRoleUserList) {
									roleNames[i] = (String) peRoleUser.getMap().get("role_name");
								}
								session.setAttribute(Constants.ROLE_NAMES, StringUtils.join(roleNames, ","));
								session.setAttribute(Constants.ROLE_INFO_LIST, peRoleUserList);
						}

						OperLog t = new OperLog();
						t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
						t.setOper_ip(IpUtils.getIpAddr(request));
						t.setLink_tab("KONKA_PE_PROD_USER");
						t.setLink_id(userInfo.getId());
						String type = "账户名：" + userInfo.getUser_name() + ",密码：" + userInfo.getPass_word();
						t.setOper_type(type);
						String name = userInfo.getUser_name() + "-从sso登录管理系统成功";
						t.setPpdm_name(name);
						if (userInfo != null) {
							t.setOper_uid(userInfo.getId());
							t.setOper_uname(userInfo.getUser_name());
						}
						getFacade().getOperLogService().createOperLog(t); 
						clearLoginErrLogs(userInfo.getUser_name());
						//跳转到渠道系统
						super.renderJavaScript(response, "window.onload=function(){location.href='"+super.getCtxPath(request)+"/manager/admin/Frames3.do?method=index'}");
						return null;
						
					 }else{
						 //未绑定用户
						msg="对不起,未绑定渠道系统用户";
						request.setAttribute("samaccount_name", samaccount_name);
						session.setAttribute("s_samaccount_name", samaccount_name);
						return mapping.findForward("input");
						//super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='"+super.getCtxPath(request)+"/login.do'}");
						//return null;
					 }
					} 
					request.setAttribute("user_name", samaccount_name);
					//System.out.println("用户名：" + sTotalString.substring(3));
				} else {
					request.setAttribute("user_name", sTotalString);
					//System.out.println("登陆失败：" + sTotalString);
				}
			} catch (java.net.MalformedURLException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("msg", ticket);
		return mapping.findForward("view");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		String samaccount_name = (String) dynaBean.get("samaccount_name");
		String s_samaccount_name = (String) session.getAttribute("s_samaccount_name");
		String msg="绑定用户成功！";
		String username = (String) dynaBean.get("username");
		String password = (String) dynaBean.get("password");
		if(StringUtils.isNotBlank(s_samaccount_name)&&StringUtils.isNotBlank(samaccount_name)){
			if(samaccount_name.equals(s_samaccount_name)){				
				if(StringUtils.isNotBlank(password)&&StringUtils.isNotBlank(username)){
					 PeProdUser entity = new PeProdUser(); 
					 entity.setUser_name(username);
					 entity.setIs_del(0);
					 entity.getMap().put("user_type_in", "0,1"); 
					 List<PeProdUser> userInfoList = getFacade().getPeProdUserService().getPeProdUserList(entity);
					 if (null == userInfoList || userInfoList.size() == 0) {
						 msg="用户不存在";
					 }else if (userInfoList.size() > 1) {
						 msg="用户名重复";
					 }else{
						 PeProdUser userInfo = userInfoList.get(0);
						 DESPlus des = new DESPlus();
						 if (!userInfo.getPass_word().equals(des.encrypt(password))) {
							 msg="密码错误";
						 }else{
							 SsoOaUserBind bind = new SsoOaUserBind();
							 bind.setLink_tab("KONKA_PE_PROD_USER");
							 bind.setUser_id(userInfo.getId());
							 bind.setState(0);
							 bind.setSamaccount_name(s_samaccount_name);
							 bind.setUser_name(username);
							 super.getFacade().getSsoOaUserBindService().createSsoOaUserBind(bind);
							 session.setAttribute("s_samaccount_name", null);
							 msg="绑定用户成功！";
							 super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='"+super.getCtxPath(request)+"/webservice/sso/Qdgl.do';}");
							 return null; 
						 }
					 }
				}else{ 
					msg="用户名密码不能为空";  
				}
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='"+super.getCtxPath(request)+"/webservice/sso/Qdgl.do';}");
				return null; 		
			}
		}
		msg+="用户绑定失败";
		super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='"+super.getCtxPath(request)+"/webservice/sso/Qdgl.do';}");
		return null;	
	}

}
