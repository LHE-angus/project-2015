package com.ebiz.mmt.web.struts.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3ShopJson;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;

public class KonkaR3ShopInterfaceAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.getJson(mapping, form, request, response);
	}

	public ActionForward getJson(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		if (StringUtils.isBlank(username) || StringUtils.isBlank(userpass)) {
			super.renderText(response, "参数错误，请联系管理员！");
			return null;
		}
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser ui = checkUser(username, userpass,android_version,ios_version);
		if (null == ui) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		Boolean role_id_lt_30 = false;// 总部人员
		Boolean role_id_btw_30_40 = false;// 分公司人员
		Boolean role_id_eq_40 = false;// 经营部人员
		Boolean role_id_eq_50 = false;// 办事处人员
		Boolean role_id_eq_60 = false;// 业务员
		//Boolean role_id_le_60 = false;// 业务员
		PeRoleUser pu = new PeRoleUser();
		pu.setUser_id(ui.getId());
		List<PeRoleUser> puList = super.getFacade().getPeRoleUserService()
				.getPeRoleUserList(pu);
		String role_id = "";
		if (puList.size() > 0) {
			for (PeRoleUser p : puList) {
				role_id =role_id+ p.getRole_id()+",";
				if (p.getRole_id() < 30) {
					role_id_lt_30 = true;
				}
				if (p.getRole_id() >= 30 && p.getRole_id() < 40) {
					role_id_btw_30_40 = true;
				}
				if (p.getRole_id() == 40) {
					role_id_eq_40 = true;
				}
				if (p.getRole_id() == 50) {
					role_id_eq_50 = true;
				}
				if (p.getRole_id() == 60) {
					role_id_eq_60 = true;
				}
//				if (p.getRole_id() <= 60) {
//					role_id_le_60 = true;
//				}
			}
		}
		
//		if (!role_id_le_60) {
//			super.renderText(response, "您没有权限查看，请联系管理员！");
//			return null;
//		}

		String customer_name = (String) dynaBean.get("customer_name");
		String ywy_name = (String) dynaBean.get("ywy_name");
		String r3_code = (String) dynaBean.get("r3_code");

		KonkaR3Shop kShop = new KonkaR3Shop();
		if (StringUtils.isNotBlank(customer_name)) {
			kShop.getMap().put("customer_name_like", customer_name);
		}

		if (StringUtils.isNotBlank(r3_code)) {
			kShop.getMap().put("r3_code_like", r3_code);
		}

		if (!role_id_lt_30 && role_id_btw_30_40) {// 分公司管理员
			kShop.getMap().put("dept_id",
					getKonkaDeptForFgs(ui.getDept_id()).getDept_id());
		}
		if (!role_id_lt_30 && !role_id_btw_30_40 && role_id_eq_40) {
			kShop.getMap().put("jyb_id", ui.getDept_id());
		}
		if (!role_id_lt_30 && !role_id_btw_30_40 && !role_id_eq_40
				&& role_id_eq_50) {
			kShop.getMap().put("bsc_id", ui.getDept_id());
		}
		if (!role_id_lt_30 && !role_id_btw_30_40 && !role_id_eq_40
				&& !role_id_eq_50 && role_id_eq_60) {
			kShop.getMap().put("user_id", ui.getId());
		} else {
			if (StringUtils.isNotBlank(ywy_name)) {
				kShop.getMap().put("user_name_like", ywy_name);
			}
		}
		List<KonkaR3ShopJson> entityList = new ArrayList<KonkaR3ShopJson>();

		List<KonkaR3Shop> kShopList = super.getFacade().getKonkaR3ShopService()
				.getKonkaR3ShopListByUserName(kShop);
		if (kShopList.size() > 0) {
			for (KonkaR3Shop krs : kShopList) {
				KonkaR3ShopJson entity = new KonkaR3ShopJson();
				entity.setR3_code(krs.getR3_code());
				if (null != krs.getCustomer_name()) {
					entity.setR3_name(krs.getCustomer_name());
				} else {
					entity.setR3_name("");
				}
				if (null != krs.getBranch_area_name()) {
					entity.setDept_name(krs.getBranch_area_name());
				} else {
					entity.setDept_name("");
				}
				//联系人
				if (null != krs.getLink_man_name()) {
					entity.setLink_man_name(krs.getLink_man_name());
				} else {
					entity.setLink_man_name("");
				}
				//联系人电话
				if (null != krs.getLink_man_tel()) {
					entity.setLink_man_tel(krs.getLink_man_tel());
				} else {
					entity.setLink_man_tel("");
				}
				//联系人手机
				if (null != krs.getLink_man_mobile()) {
					entity.setLink_man_mobile(krs.getLink_man_mobile());
				} else {
					entity.setLink_man_mobile("");
				}
				//联系人地址
				if (null != krs.getLink_man_addr()) {
					entity.setLink_man_addr(krs.getLink_man_addr());
				} else {
					entity.setLink_man_addr("");
				}
				// 客户邮编
				if (null != krs.getLink_man_post()) {
					entity.setLink_man_post(krs.getLink_man_post());
				} else {
					entity.setLink_man_post("");
				}
				//法人姓名
				if (null != krs.getHost_name()) {
					entity.setHost_name(krs.getHost_name());
				} else {
					entity.setHost_name("");
				}
				// 法人联系电话
				if (null != krs.getHost_tel()) {
					entity.setHost_tel(krs.getHost_tel());
				} else {
					entity.setHost_tel("");
				}
				
				if (krs.getMap().get("real_name") != null) {
					entity
							.setYwy_name(krs.getMap().get("real_name")
									.toString());
				} else {
					entity.setYwy_name("");
				}
				if (krs.getMap().get("c_name") != null) {
					entity.setC_name(krs.getMap().get("c_name").toString());
				} else {
					entity.setC_name("");
				}

				entityList.add(entity);
			}
		}

		super.renderTextJsonOrJsonp(request, response, JSON
				.toJSONString(entityList));
		return null;
	}

	protected PeProdUser checkUser(String username, String userpass)
			throws Exception {
		PeProdUser ui = new PeProdUser();
		if (StringUtils.isEmpty(username))
			return null;
		if (StringUtils.isEmpty(userpass))
			return null;
		username = username.trim();
		PeProdUser entity = new PeProdUser();
			entity.setUser_name(username);
			entity.setPass_word(new DESPlus().encrypt(userpass));
		entity.setIs_del(0);
		ui = getFacade().getPeProdUserService().getPeProdUser(entity);
		return ui;
	}
}
