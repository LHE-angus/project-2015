package com.ebiz.mmt.web.struts.inter.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.KonkaInterfaceAccessLog;
import com.ebiz.mmt.domain.KonkaInterfaceBindsUser;
import com.ebiz.mmt.web.struts.inter.form.AttachmentForm;
import com.ebiz.mmt.web.struts.inter.form.InterUser;
import com.ebiz.mmt.web.struts.inter.form.KonkaOrderAttachmentForm;
import com.ebiz.mmt.web.util.IpUtils;
import com.ebiz.org.apache.commons.lang3.StringUtils;

/**
 * @author Wang,KunLin
 * @version 2014-09-24
 * @see 客户订单附加数据
 */
public class KonkaOAInterfaceAction extends BaseInterAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String order_id = (String) dynaBean.get("order_id");// 订单号

		InterUser user = super.getInterUser(form, request);

		KonkaInterfaceBindsUser entity = new KonkaInterfaceBindsUser();
		entity.setUser_id(user.getUser_id());
		entity.setLicenses_sn(user.getLicenses_sn());
		entity.setUser_key(user.getUser_key());
		entity = super.getFacade().getKonkaInterfaceBindsUserService().getKonkaInterfaceBindsUser(entity);

		// 处理具体数据
		KonkaOrderAttachmentForm obj = new KonkaOrderAttachmentForm();

		// 如果不是授权的账号不予进行后续操作
		if (null == entity) {
			obj.setReturn_state(1);
			obj.setReturn_error("user_id user_key licenses_sn does not match...");
			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("用户授权验证不通过");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(user.getLicenses_sn());
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("查询订单的附件信息");
			log.setUser_id(user.getUser_id());
			log.setUser_key(user.getUser_key());
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);
			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		}

		// 订单号码或者流水号必须传一个
		if (StringUtils.isNotBlank(order_id) && GenericValidator.isLong(order_id)) {
			Attachment attachment = new Attachment();
			attachment.setLink_id(Long.valueOf(order_id));
			attachment.setDel_mark(Short.valueOf("0"));
			List<Attachment> attList = super.getFacade().getAttachmentService().getAttachmentList(attachment);
			if (null != attList && attList.size() > 0) {
				List<AttachmentForm> AttachmentList = new ArrayList<AttachmentForm>();
				for (Attachment att : attList) {
					AttachmentForm attach = new AttachmentForm();
					attach.setId(att.getId());
					attach.setLink_id(att.getLink_id());
					attach.setLink_tab(att.getLink_tab());
					attach.setFile_name(att.getFile_name());
					attach.setFile_type(att.getFile_type());
					attach.setFile_size(att.getFile_size());
					attach.setSave_path(att.getSave_path());
					attach.setSave_name(att.getSave_name());
					attach.setFile_desc(att.getFile_desc());
					attach.setDel_mark(att.getDel_mark());
					AttachmentList.add(attach);
				}
				obj.setAttachmentList(AttachmentList);
				request.setAttribute("attachmentList", AttachmentList);
			}
		} else {
			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("order_id is null");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(user.getLicenses_sn());
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("附件页面接口");
			log.setUser_id(user.getUser_id());
			log.setUser_key(user.getUser_key());
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);
			obj.setReturn_state(1);
			obj.setReturn_error("order_id is not null...");
			request.setAttribute("errorMsg", "订单ID为空！");
			return mapping.findForward("errorMsg");
		}

		KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
		log.setAdd_date(new Date());
		// log.setError_msg("查询订单的附件信息成功");
		log.setIp(IpUtils.getRemortIP(request));
		log.setLicenses_sn(user.getLicenses_sn());
		log.setReq_state(1);
		log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
		log.setReq_mod_name("查询订单的附件信息");
		log.setUser_id(user.getUser_id());
		log.setUser_key(user.getUser_key());
		super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

		// obj.setReturn_state(0);
		// super.renderJson(response, JSON.toJSONString(obj));
		// return null;

		super.copyProperties(form, obj);
		return mapping.findForward("list");
	}
}
