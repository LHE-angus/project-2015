package com.ebiz.mmt.web.struts.webservice;

import java.util.Date;
import java.util.List;

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

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.ArticleImg;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.GlobalIpGeoLib;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;

public class KonkaArticleImgInterfaceAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.getJson(mapping, form, request, response);
	}

	public ActionForward getJson(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");
		String type_id = (String) dynaBean.get("type_id");// 1100 : 手机界面轮播图
		
		String used_field=(String) dynaBean.get("used_field");

		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		List<ArticleImg> entityList=null;
		
		if(StringUtils.isBlank(used_field)){
			
		
		
		
		if (StringUtils.isBlank(type_id)) {
			super.renderText(response, "参数错误，请联系管理员！");
			return null;
		}
		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(userpass)) {

			PeProdUser ui = checkUser(username, userpass,android_version,ios_version);
			if(!type_id.equals("2100") && !type_id.equals("1103")){//为了epp中的轮播图
				if (null == ui) {
					super.renderText(response, "用户信息出错，请联系管理员！");
					return null;
				}
			}
			
		}
		
		ArticleImg entity = new ArticleImg();
		entity.setNews_module(Long.valueOf(type_id));
		entity.setInfo_state(new Short("1"));
		entity.getMap().put("today", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		entity.getRow().setCount(10);
		entityList = super.getFacade().getArticleImgService().getArticleImgList(entity);
		String ctx = getCtxPath(request);
		String small_pic=""; 
		
		if (entityList.size() > 0) {
			for (ArticleImg temp : entityList) {
				
				temp.setImage_path(ctx + "/" + temp.getImage_path());
				if(temp != null && temp.getImage_path() != null){
					if(temp.getImage_path().indexOf(".") >-1){
						//temp.setImage_path( temp.getImage_path().substring(0,temp.getImage_path().indexOf(".")) + "_400" + temp.getImage_path().substring(temp.getImage_path().indexOf("."),temp.getImage_path().length()));
					}
				}
				
				temp.setImage_url(ctx + temp.getImage_url());
				if (null != temp.getTunnel() || "".equals(temp.getTunnel())) {
					temp.setTunnel("");
				}
				if (null != temp.getImage_desc() || "".equals(temp.getImage_desc())) {
					temp.setImage_desc("");
				}
			}
		}
		
		
		}else{
			
			String p_index = null;
			EcUser ecUser = null;
			if (StringUtils.isNotEmpty(username)
					&& StringUtils.isNotEmpty(userpass)) {
				ecUser=checkEcUser(username, userpass);
			}

			if (ecUser == null) {
				ecUser = new EcUser();
				ecUser.setUser_name("游客");
				ecUser.setUser_type(new Integer(2));
				ecUser.setId(new Long(1));
				ecUser.setDept_id(0L);
				ecUser.setPlat_sys(new Integer(0));
				ecUser.setCust_id(0L);
				ecUser.setPlat_sys(0);
			}
			if (p_index == null && ecUser != null && ecUser.getUser_type().intValue() == 2 && ecUser.getP_index() != null) {
				p_index = ecUser.getP_index().toString();
			}
			if (p_index == null || "".equals(p_index)) {
				try {
					p_index = getPIndexByRequest(request);
				} catch (Exception e) {
					p_index = "440300";
				}
			}
			long l = 0L;
			ArticleImg img = new ArticleImg();
			if (ecUser.getUser_type().intValue() == 1) {
				img.setNews_module(2100L);
			} else {
				img.setNews_module(2111L);
				img.setDept_id(ecUser.getDept_id());
			}  
			img.getMap().put("today", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
			img.setInfo_state(new Short("1"));
			img.getRow().setCount(5);
			 entityList = super.getFacade().getArticleImgService().getArticleImgList(img);

			for (int i = 0; i < entityList.size(); i++) {
				ArticleImg a = (ArticleImg) entityList.get(i);
				String url = a.getImage_url();
				if (url != null) {
					String[] dd = url.split("%2C");
					if(dd.length>1){ 
					for (int z = 0; z < dd.length; z++) {
						if ("productDetail".equals(dd[z]) && z + 1 < dd.length) {
							url = "PdShow.do?goods_id=" + dd[z + 1];
							a.getMap().put("url", url);
							entityList.set(i, a);
						}
					}
					}else if(url.indexOf("webservice")!=-1){
						a.getMap().put("url", url);
					}
				}
			}
			
			
		}
		
		
		
		
		
		
		

		super.renderTextJsonOrJsonp(request, response, JSON.toJSONString(entityList));
		return null;
	}
	
	
	/**
	 * @author Jiang,JiaYong
	 * @version 2013-09-10
	 * @desc 根据Request得到Pindex值
	 */
	public String getPIndexByRequest(HttpServletRequest request) {
		// 获取请求地区
		HttpSession session = request.getSession();
		String p_index = request.getParameter("p_index");
		if (StringUtils.isEmpty(p_index) || !GenericValidator.isLong(p_index)) {
			p_index = (String) session.getAttribute("p_index");
		}
		if (StringUtils.isEmpty(p_index) || p_index.length() != 6) {
			EcUser ecUser = (EcUser) session.getAttribute("ecUser");
			if (ecUser != null && ecUser.getP_index() != null && ecUser.getUser_type().intValue() == 2) {
				p_index = ecUser.getP_index().toString();
				if (p_index.length() > 6) {
					p_index = p_index.substring(0, 6);
				}
				return p_index;
			}
			// 获取请求的IP
			String ip = super.getIpAddr(request);
			// 处理本地IP
			if ("0:0:0:0:0:0:0:1".equals(ip) || ip.indexOf("192.") != -1) {
				p_index = "440300"; // 本地开发，默认为深圳
			} else {
				// 根据请求的IP ，转换成p_index, 例会 p_index = 34
				String[] ipArr = ip.split("\\.");
				try {
					Long ip1 = Long.valueOf(ipArr[0]);
					Long ip2 = Long.valueOf(ipArr[1]);
					Long ip3 = Long.valueOf(ipArr[2]);
					Long ip4 = Long.valueOf(ipArr[3]);

					Long ipValue = ip1 * 255 * 255 * 255 + ip2 * 255 * 255 + ip3 * 255 + ip4;

					GlobalIpGeoLib globalIpGeoLib = new GlobalIpGeoLib();
					globalIpGeoLib.getMap().put("ipValue", ipValue);
					List<GlobalIpGeoLib> globalIpGeoLibList = super.getFacade().getGlobalIpGeoLibService()
					        .getGlobalIpGeoLibForIndexList(globalIpGeoLib);

					if (null != globalIpGeoLibList && globalIpGeoLibList.size() > 0) {
						p_index = String.valueOf(globalIpGeoLibList.get(0).getMap().get("p_index"));
					} else { // IP为空则转换为深圳
						p_index = "440300";
					}
				} catch (Exception e) {
					p_index = "440300";
				}
			}
		}

		// 异常判断处理
		if (!GenericValidator.isLong(p_index)) {
			p_index = "440300";
		}
		session.setAttribute("p_index", p_index);
		return p_index;
	}
	protected EcUser checkEcUser(String username, String userpass)
	throws Exception {
if (StringUtils.isBlank(username) || StringUtils.isBlank(userpass)) {
	return null;
}
EcUser user = new EcUser();
EcUser user1;
user.setUser_name(username.trim());
user.setIs_del(0);
/**
 * 0-审核通过 1-待完善资料 2-待审核 3-审核不通过
 */
user.setPass_word(new DESPlus().encrypt(userpass));
user1 = getFacade().getEcUserService().getEcUser(user);

// 如果没有找到的话，找工卡验证
if (user1 == null || user1.getId() == null) {
	user = new EcUser();
	user.setUser_name(null);
	user.setCard_no(username);
	user.setPass_word(new DESPlus().encrypt(userpass));
	user1 = getFacade().getEcUserService().getEcUser(user);
	if(user1==null){
		user = new EcUser();
		user.setUser_name(null);
		user.setCard_no(username);
		user.setPay_pwd(new DESPlus().encrypt(userpass));
		user1 = getFacade().getEcUserService().getEcUser(user);
	}
}

// 如果没有找到的话，找移动电话验证
if (user1 == null || user1.getId() == null) {
	user = new EcUser();
	user.setUser_name(null);
	user.setLink_phone(username);
	user.setCard_no(null);
	user.setPass_word(new DESPlus().encrypt(userpass));
	user1 = getFacade().getEcUserService().getEcUser(user);
	if(user1==null){
		user = new EcUser();
		user.setUser_name(null);
		user.setLink_phone(username);
		user.setPay_pwd(new DESPlus().encrypt(userpass));
		user1 = getFacade().getEcUserService().getEcUser(user);
	}
}

if (user1 != null && user1.getCust_id() != null) {
	KonkaR3Shop r3shop = new KonkaR3Shop();
	r3shop.setId(user1.getCust_id());

	r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);
	if (r3shop != null && r3shop.getWeb_type() != null) {
		user1.getMap().put("web_type", r3shop.getWeb_type());
	}
}
return user1;
}
}
