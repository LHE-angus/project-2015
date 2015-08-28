package com.ebiz.mmt.web.struts.inter.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.KonkaInterfaceAccessLog;
import com.ebiz.mmt.domain.KonkaInterfaceBindsUser;
import com.ebiz.mmt.domain.KonkaR3ShopLink;
import com.ebiz.mmt.web.struts.inter.form.InterUser;
import com.ebiz.mmt.web.struts.inter.form.R3ShopLink;
import com.ebiz.mmt.web.struts.inter.form.R3ShopLinkForm;
import com.ebiz.mmt.web.util.IpUtils;
import com.ebiz.org.apache.commons.lang3.StringUtils;

/**
 * @author pan,gang
 * @version 2014-09-23
 * @see 客户联系人接口
 */
public class KonkaR3ShopLinkInterfaceAction extends BaseInterAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String r3_code = (String) dynaBean.get("r3_code");
		InterUser user = super.getInterUser(form, request);

		KonkaInterfaceBindsUser entity = new KonkaInterfaceBindsUser();
		entity.setUser_id(user.getUser_id());
		entity.setLicenses_sn(user.getLicenses_sn());
		entity.setUser_key(user.getUser_key());

		entity = super.getFacade().getKonkaInterfaceBindsUserService().getKonkaInterfaceBindsUser(entity);
		R3ShopLinkForm obj = new R3ShopLinkForm();
		if (null == entity) {
			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("未找到绑定数据");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(user.getLicenses_sn());
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("R3客户联系人接口");
			log.setUser_id(user.getUser_id());
			log.setUser_key(user.getUser_key());
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

			obj.setReturn_state(1);
			obj.setReturn_error("user_id user_key licenses_sn does not match...");
			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		}
		if (StringUtils.isBlank(r3_code)) {
			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("r3_code is null");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(user.getLicenses_sn());
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("R3客户联系人接口");
			log.setUser_id(user.getUser_id());
			log.setUser_key(user.getUser_key());
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

			obj.setReturn_state(1);
			obj.setReturn_error("r3_code is not null...");
			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		}
		// customer_name = changeToUTF8(customer_name);

		KonkaR3ShopLink kg = new KonkaR3ShopLink();
		if (StringUtils.isNotBlank(r3_code)) {
			kg.getMap().put("r3_code", r3_code);
		}
		kg.setIs_del(0);
		kg.getMap().put("real_name_is_not_null", true);
		kg.getMap().put("position_is_not_null", true);
		Long count = super.getFacade().getKonkaR3ShopLinkService().getKonkaR3ShopLinkCount(kg);
		if (count > 500) {

			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("查询数据不能超过500条");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(user.getLicenses_sn());
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("R3客户联系人接口");
			log.setUser_id(user.getUser_id());
			log.setUser_key(user.getUser_key());
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

			obj.setReturn_state(1);
			obj.setReturn_error("display up to 500 data...");
			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		}

		kg.getRow().setFirst(0);
		kg.getRow().setCount(count.intValue());
		List<KonkaR3ShopLink> konkaR3ShopLinkList = super.getFacade().getKonkaR3ShopLinkService()
		        .getKonkaR3ShopLinkList(kg);
		List<R3ShopLink> r3List = new ArrayList<R3ShopLink>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		for (KonkaR3ShopLink rs : konkaR3ShopLinkList) {
			R3ShopLink r = new R3ShopLink();
			// copyProperties(r, r3ShopLink);
			if (rs.getAdd_date() != null) {
				r.setAdd_date(sf.format(rs.getAdd_date()));
			}
			if (rs.getAdd_user_id() != null) {
				r.setAdd_user_id(rs.getAdd_user_id().toString());
			} else {
				r.setAdd_user_id("");
			}
			if (rs.getAddr() != null) {
				r.setAddr(rs.getAddr());
			} else {
				r.setAddr("");
			}
			if (rs.getIs_del() != null) {
				r.setIs_del(rs.getIs_del().toString());
			} else {
				r.setIs_del("");
			}
			if (rs.getBirthday() != null) {
				r.setBirthday(sf.format(rs.getBirthday()));
			} else {
				r.setBirthday("");
			}
			if (rs.getEmail() != null) {
				r.setEmail(rs.getEmail());
			} else {
				r.setEmail("");
			}
			if (rs.getFavor() != null) {
				r.setFavor(rs.getFavor());
			} else {
				r.setFavor("");
			}
			if (rs.getFax() != null) {
				r.setFax(rs.getFax());
			} else {
				r.setFax("");
			}
			if (rs.getIs_default() != null) {
				r.setIs_default(rs.getIs_default());
			} else {
				r.setIs_default("");
			}
			if (rs.getIs_valid() != null) {
				r.setIs_valid(rs.getIs_valid());
			} else {
				r.setIs_valid("");
			}
			if (rs.getJob() != null) {
				r.setJob(rs.getJob());
			} else {
				r.setJob("");
			}
			if (rs.getLink_id() != null) {
				r.setLink_id(rs.getLink_id().toString());
			} else {
				r.setLink_id("");
			}
			if (rs.getMobile() != null) {
				r.setMobile(rs.getMobile());
			} else {
				r.setMobile("");
			}
			if (rs.getOrder_value() != null) {
				r.setOrder_value(rs.getOrder_value().toString());
			} else {
				r.setOrder_value("");
			}
			if (rs.getPosition() != null) {
				r.setPosition(rs.getPosition());
			} else {
				r.setPosition("");
			}
			if (rs.getQq() != null) {
				r.setQq(rs.getQq());
			} else {
				r.setQq("");
			}
			if (rs.getR3_shop_id() != null) {
				r.setR3_shop_id(rs.getR3_shop_id().toString());
			} else {
				r.setR3_shop_id("");
			}
			if (rs.getReal_name() != null) {
				r.setReal_name(rs.getReal_name());
			} else {
				r.setReal_name("");
			}
			if (rs.getSex() != null) {
				r.setSex(rs.getSex().toString());
			} else {
				r.setSex("");
			}
			if (rs.getTel() != null) {
				r.setTel(rs.getTel());
			} else {
				r.setTel("");
			}
			if (rs.getTelephone() != null) {
				r.setTelephone(rs.getTelephone());
			} else {
				r.setTelephone("");
			}
			if (rs.getWeixin() != null) {
				r.setWeixin(rs.getWeixin());
			} else {
				r.setWeixin("");
			}
			r3List.add(r);
		}
		obj.setR3ShopList(r3List);
		obj.setReturn_state(0);

		KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
		log.setAdd_date(new Date());
		log.setIp(IpUtils.getRemortIP(request));
		log.setLicenses_sn(user.getLicenses_sn());
		log.setReq_state(0);
		log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
		log.setReq_mod_name("R3客户联系人接口");
		log.setUser_id(user.getUser_id());
		log.setUser_key(user.getUser_key());
		log.setReq_content(JSON.toJSONString(obj));
		super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

		super.renderJson(response, JSON.toJSONString(obj));
		return null;
	}
}
