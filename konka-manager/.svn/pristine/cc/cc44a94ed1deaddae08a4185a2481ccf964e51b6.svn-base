package com.ebiz.mmt.web.struts.webservice.wap;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.EcArticleInfo;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.web.struts.member.BaseMemberAction;
import com.ebiz.mmt.web.util.DESPlus;

public class EcArticleInfoAction extends BaseMemberAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}
		/**
		 * 拿广告位的方法
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
	private ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		String user_id = (String) request.getParameter("user_id");
		String username = (String) request.getParameter("username");
		String userpass = (String) request.getParameter("userpass");
		EcUser ecUser = null;
		if (StringUtils.isNotEmpty(username)
				&& StringUtils.isNotEmpty(userpass)) {
			ecUser = checkUser(username, userpass);
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
		EcArticleInfo ecArticleInfo = new EcArticleInfo();
		ecArticleInfo.setDel_mark(0);
		ecArticleInfo.setInfo_state(new Integer(1));
		ecArticleInfo.setOwn_sys(ecUser.getUser_type());
		ecArticleInfo.setPlat_sys(ecUser.getPlat_sys());
		ecArticleInfo.setDept_id(ecUser.getDept_id());
		List<EcArticleInfo> ecArticleInfoList = super.getFacade()
				.getEcArticleInfoService().getEcArticleInfoList(ecArticleInfo);
		if (ecArticleInfoList != null && ecArticleInfoList.size() > 0) {
			ecArticleInfo = ecArticleInfoList.get(0);
			request.setAttribute("ecArticleInfo", ecArticleInfo);
		} else {
			ecArticleInfo = new EcArticleInfo();
			ecArticleInfo.setDel_mark(0);
			ecArticleInfo.setInfo_state(new Integer(1));
			ecArticleInfo.setOwn_sys(2);
			ecArticleInfo.setPlat_sys(0);
			ecArticleInfo.setDept_id(0L);
			ecArticleInfoList = super.getFacade().getEcArticleInfoService()
					.getEcArticleInfoList(ecArticleInfo);
			if (ecArticleInfoList != null && ecArticleInfoList.size() > 0) {
				ecArticleInfo= ecArticleInfoList.get(0);
			}
		}
	//	super.renderTextJsonOrJsonp(request, response, JSON.toJSONString(ecArticleInfo));
		request.setAttribute("ecArticleInfo", ecArticleInfo);
		return new ActionForward("/../webservice/wap/Index/list_artical_info.jsp");
		
	}

	protected EcUser checkUser(String username, String userpass)
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
