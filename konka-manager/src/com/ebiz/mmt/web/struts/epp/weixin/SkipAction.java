package com.ebiz.mmt.web.struts.epp.weixin;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONException;
import org.json.JSONObject;

import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.OperLog;
import com.ebiz.mmt.domain.WeixinBindUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.manager.oa.IpUtils;
import com.ebiz.mmt.web.struts.member.BaseMemberAction;
import com.ebiz.mmt.web.util.DESPlus;
import com.opensymphony.oscache.util.StringUtil;
import com.weixin.SnsToken;

/**
 * @author TUDP
 * @version 2014-10-24
 */
public class SkipAction extends BaseMemberAction {
	
	public static String appid="wxf1ebf4fa2b7abc93";//"wx9ff7cc6d172604e6";
	public static String appsecret="40ced59f3915072f5897835f3d043ed6";//"965c10301eaef3f2c75e104c567de0fe";
	
	//https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx9ff7cc6d172604e6&redirect_uri=http%3A%2F%2Feppmobile.konka.com%2Fepp%2Fweixin%2FSkip.do&response_type=code&scope=snsapi_base&state=a#wechat_redirect
	public static String authorize_url="https://open.weixin.qq.com/connect/oauth2/authorize?";//授权页面
	//https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx9ff7cc6d172604e6&secret=965c10301eaef3f2c75e104c567de0fe&code=${param.code}&grant_type=authorization_code
	public static String access_token_url_01="https://api.weixin.qq.com/sns/oauth2/access_token?";//获取微信用户
	//https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN
	public static String refresh_token_url="https://api.weixin.qq.com/sns/oauth2/refresh_token?";
	//https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
	public static String access_token_url_02="https://api.weixin.qq.com/sns/userinfo?";	
	public static String redirect_uri="http%3A%2F%2Feppmobile.konka.com%2Fepp%2Fweixin%2FSkip.do";
		
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String code =(String)dynaBean.get("code");
		if(!StringUtil.isEmpty(code)){
			SnsToken snsToken=getSnsToken(code);
			if(snsToken!=null&&snsToken.getOpenid()!=null){		
				//检查openid 是否绑定了
				//如果绑定了直接登录
				WeixinBindUser bindUser = new WeixinBindUser();
				bindUser.setLink_tab("EC_USER");
				bindUser.setIs_del(new Integer(0));
				bindUser.setIs_lock(new Integer(0));
				bindUser.setOpenid(snsToken.getOpenid());
				List<WeixinBindUser> list = super.getFacade().getWeixinBindUserService().getWeixinBindUserList(bindUser);
				EcUser user= new EcUser();
				boolean flg= false;
				if(list!=null&&list.size()>0){
					bindUser=list.get(0);
					user.setId(bindUser.getUser_id());
					user = super.getFacade().getEcUserService().getEcUser(user);
					if(user!=null&&user.getId()!=null){
						flg=true;
					}
				}
				
				if(flg){
					//保存用户登录信息
					HttpSession session = request.getSession();
					session.setAttribute(Constants.EPP_USER, user);
					session.setAttribute("memberUser", null);
					session.setAttribute("shopUser", null);
					session.setAttribute("touchUser", null);
					session.setAttribute("sfmallUser", null);
					OperLog t = new OperLog();
					t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
					t.setOper_ip(IpUtils.getIpAddr(request));
					t.setLink_tab("EC_USER");
					t.setLink_id(user.getId());
					String type = "账户名：" + user.getUser_name() + ",密码：" + user.getPass_word()+ ",微信用户ID：" + snsToken.getOpenid();
					t.setOper_type(type);
					String name = user.getUser_name() + "-用户微信绑定登录系统成功";
					t.setPpdm_name(name);
					if (user != null) {
						t.setOper_uid(user.getId());
						t.setOper_uname(user.getUser_name());
					}
					getFacade().getOperLogService().createOperLog(t);
					super.renderJavaScript(response, "window.onload=function(){location.href='" + super.getCtxPath(request)+"/epp/mobile/Index.do';}");
					return null;
					
				}else{
					//未绑定 进入绑定页面
					request.setAttribute("snsToken", snsToken);
					return this.toLogin(mapping, form, request, response);
				}
			}
		} 
		super.renderHtml(response, "对不起,未获取到授权信息,请从微信重新进入");
		return null;
	}
	 
	public ActionForward toLogin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return new ActionForward("/../epp/weixin/login.jsp");
	}
	
	public ActionForward bind(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean lazyForm = (DynaBean) form;
		String user_name = (String) lazyForm.get("user_name");
		String password = (String) lazyForm.get("password");
		String openid = (String) lazyForm.get("openid");
		String access_token = (String) lazyForm.get("access_token"); 
		String expires_in = (String) lazyForm.get("expires_in");
		logger.info("openid:"+openid+"access_token:"+access_token+"expires_in:"+expires_in);
		String msg="";
		EcUser user=super.checkUser(user_name, password);
		if(CheckToken(openid,access_token)!=0){
			msg="微信授权错误或超时";
			super.renderJavaScript(response, "alert('" + msg + "');");
			return null;
		}else if(user==null){
			msg="用户名或密码错误";
			super.renderJavaScript(response, "alert('" + msg + "');history.back()");
			return null;
		}else{//用户绑定
			//insert 用户绑定表
			WeixinBindUser bindUser = new WeixinBindUser();
			bindUser.setLink_tab("EC_USER");
			bindUser.setIs_del(new Integer(0));
			bindUser.setIs_lock(new Integer(0));
			bindUser.setOpenid(openid);
			bindUser.setUser_name(user_name);
			bindUser.setUser_type(new Long(user.getUser_type().intValue()));
			bindUser.setUser_id(user.getId());
			super.getFacade().getWeixinBindUserService().createWeixinBindUser(bindUser);
			//保存用户登录信息
			HttpSession session = request.getSession();
			session.setAttribute(Constants.EPP_USER, user);
			session.setAttribute("memberUser", null);
			session.setAttribute("shopUser", null);
			session.setAttribute("touchUser", null);
			session.setAttribute("sfmallUser", null);
			if(!(new DESPlus().encrypt(password)).equals(user.getPass_word())&& (new DESPlus().encrypt(password)).equals(user.getPay_pwd())){
				session.setAttribute("touch", 1);
			}else{
				session.setAttribute("touch", null);
			}
			OperLog t = new OperLog();
			t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
			t.setOper_ip(IpUtils.getIpAddr(request));
			t.setLink_tab("EC_USER");
			t.setLink_id(user.getId());
			String type = "账户名：" + user.getUser_name() + ",密码：" + user.getPass_word()+ ",微信用户ID：" + openid;
			t.setOper_type(type);
			String name = user.getUser_name() + "-用户微信绑定登录系统成功";
			t.setPpdm_name(name);
			if (user != null) {
				t.setOper_uid(user.getId());
				t.setOper_uname(user.getUser_name());
			}
			getFacade().getOperLogService().createOperLog(t);
			super.renderJavaScript(response, "window.onload=function(){location.href='" + super.getCtxPath(request)+"/epp/mobile/Index.do';}");
			return null;
		}		
		
	}
	
	public ActionForward toAuth(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//跳转到用户授权页面
		String url= authorize_url+"appid="+appid+"&redirect_uri="+redirect_uri+"&response_type=code&scope=snsapi_base&state=a#wechat_redirect";
		response.sendRedirect(url);
		return null; 
	}

	public SnsToken getSnsToken(String code){
		SnsToken snsToken = new SnsToken();
		String openid="";
		String refresh_token="";
		String access_token="";
		String scope="";
		String access_token_body_01=getUrlContent(access_token_url_01+"appid="+appid+"&secret="+appsecret+"&code="+code+"&grant_type=authorization_code");
		try {
			JSONObject jsonObj = new JSONObject(access_token_body_01);
			openid=	jsonObj.getString("openid");
			access_token=jsonObj.getString("access_token");
			refresh_token=jsonObj.getString("refresh_token");
			Integer expires_in=jsonObj.getInt("expires_in");
			snsToken.setOpenid(openid);
			snsToken.setAccess_token(access_token);
			snsToken.setRefresh_token(refresh_token);
			snsToken.setScope(scope);
			snsToken.setExpires_in(expires_in);
			
			//String refresh_token_body=getUrlContent(refresh_token_url+"appid="+appid+"&grant_type=refresh_token&refresh_token="+refresh_token);
			//String access_token_body_02=getUrlContent(access_token_url_02+"access_token="+access_token+"&openid="+openId+"&lang=zh_CN");
		}catch (JSONException e){
			e.printStackTrace();
	    } 
		return snsToken;
	} 
	
	public int CheckToken(String openid,String access_token){ 
		int errcode=-1;
		String body=getUrlContent("https://api.weixin.qq.com/sns/auth?access_token="+access_token+"&openid="+openid);
		try {
			JSONObject jsonObj = new JSONObject(body); 
			errcode=jsonObj.getInt("errcode"); 
		}catch (JSONException e){
			e.printStackTrace();
	    } 
		return errcode;
	} 
	public String getUrlContent(String url){
		String body ="";
		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpGet httpget = new HttpGet(url);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			body = httpclient.execute(httpget, responseHandler); 
		}catch (ClientProtocolException e) {			
		}catch (IOException e) {			
		}
		return body;
	}
}
