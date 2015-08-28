package com.ebiz.mmt.web.struts.manager.spgl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.EcGroup;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcVouchersApply;
import com.ebiz.mmt.domain.EcVouchersAudit;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Pan,Gang
 * @version 2013-09-24
 */
public class EcVouchersApplyAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String title_like = (String) dynaBean.get("title_like");

		String apply_name_like = (String) dynaBean.get("apply_name_like");
		String pd_type_in = (String) dynaBean.get("pd_type");
		String goods_type_in = (String) dynaBean.get("goods_type");
		String goods_like = (String) dynaBean.get("goods_like");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcVouchersApply entity = new EcVouchersApply();
		if (StringUtils.isNotBlank(title_like)) {
			entity.getMap().put("title_like", title_like);

		}
		if (StringUtils.isNotBlank(apply_name_like)) {
			entity.getMap().put("apply_name_like", apply_name_like);

		}
		if (StringUtils.isNotBlank(pd_type_in)) {
			entity.getMap().put("pd_type_in", pd_type_in);

		}
		if (StringUtils.isNotBlank(goods_type_in)) {
			entity.getMap().put("goods_type_in", goods_type_in);

		}
		if (StringUtils.isNotBlank(goods_like)) {
			entity.getMap().put("goods_like", goods_like);

		}

		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 140317 || pu.getRole_id().intValue() == 1001) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}

		if (zb) {
			request.setAttribute("is_admin", "1");
		} else if (!zb && fgs) {
			request.setAttribute("is_fgs", "1");
			entity.setCreate_u_id(user.getId());
		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('你没有权限查看价格！');history.back();}");
			return null;
		}

		entity.setOwn_sys(2);

		Long recordCount = super.getFacade().getEcVouchersApplyService().getEcVouchersApplyCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcVouchersApply> entityList = super.getFacade().getEcVouchersApplyService()
				.getEcVouchersApplyPaginatedList(entity);
		for (EcVouchersApply ecVouchers : entityList) {
			if (null != ecVouchers.getUser_id()) {
				EcUser ec = new EcUser();
				ec.setId(ecVouchers.getUser_id());
				ec = super.getFacade().getEcUserService().getEcUser(ec);
				if (null != ec && null != ec.getReal_name()) {
					ecVouchers.getMap().put("ec_user_name", ec.getReal_name());
				}
			}
			if (ecVouchers.getGoods_type() != null) {
				String goods_type = ecVouchers.getGoods_type().replace("0", "新品").replace("2", "热销").replace("3", "特惠")
						.replace("7", "精品");
				ecVouchers.getMap().put("goods_type", goods_type);
			}

			if (ecVouchers.getPd_type() != null) {
				String pd_type = ecVouchers.getPd_type().replace("0", "彩电").replace("3", "小家电").replace("5", "洗衣机");
				ecVouchers.getMap().put("pd_type", pd_type);
			}

			if (ecVouchers.getGoods_id() != null) {
				String pd_sn = "";
				String[] aa = ecVouchers.getGoods_id().split(",");
				for (String string : aa) {
					KonkaBcompPd kp = new KonkaBcompPd();
					kp.setId(Long.valueOf(string));
					kp = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kp);
					pd_sn = pd_sn + kp.getPd_sn() + ",";
				}
				ecVouchers.getMap().put("pd_sn", pd_sn.substring(0, pd_sn.length() - 1));
			}

			PeProdUser pp = new PeProdUser();
			pp.setId(ecVouchers.getCreate_u_id());
			pp = super.getFacade().getPeProdUserService().getPeProdUser(pp);
			if (pp != null) {
				ecVouchers.getMap().put("add_user_name", pp.getUser_name());
			}
		}

		request.setAttribute("entityList", entityList);
		dynaBean.set("user_id", user.getId());

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 140317 || pu.getRole_id().intValue() == 1001) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}

		if (zb) {
			request.setAttribute("is_admin", "1");

			EcGroup eg = new EcGroup();
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("groupList", deptList);

		} else if (!zb && fgs) {
			if (null != user.getDept_id()) {
				KonkaDept konkaDept = new KonkaDept();
				konkaDept.setDept_id(user.getDept_id());
				konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
				// 分公司取得
				if (null != konkaDept.getDept_type() && konkaDept.getDept_type() > 2) {
					KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);
					dynaBean.set("fgs_id", fgs_dept.getDept_id());

					request.setAttribute("is_fgs", "1");
					EcGroup eg = new EcGroup();
					eg.setLink_dept_id(fgs_dept.getDept_id());
					List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
					request.setAttribute("groupList", deptList);
				}
			}

		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('你没有权限查看价格！');history.back();}");
			return null;
		}

		dynaBean.set("is_other", 0);// 默认可以叠加使用

		return mapping.findForward("input");
	}

	public ActionForward add2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcVouchersApply entity = new EcVouchersApply();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getEcVouchersApplyService().getEcVouchersApply(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		if (entity.getPd_type() != null) {
			dynaBean.set("prod_types", entity.getPd_type() + ",");
			String[] aa = entity.getPd_type().split(",");
			String ss = "";
			for (String string : aa) {
				if (string.equals("0")) {
					ss = ss + "彩电" + ",";
				} else if (string.equals("3")) {
					ss = ss + "小家电" + ",";
				} else if (string.equals("5")) {
					ss = ss + "洗衣机" + ",";
				}
			}
			dynaBean.set("pd_name_hid", ss);
			dynaBean.set("prod_name", ss.substring(0, ss.length() - 1));
		}

		if (entity.getGoods_type() != null) {
			dynaBean.set("goods_types_hid", entity.getGoods_type() + ",");
			String[] aa = entity.getGoods_type().split(",");
			String ss = "";
			for (String string : aa) {
				if (string.equals("0")) {
					ss = ss + "新品" + ",";
				} else if (string.equals("2")) {
					ss = ss + "热销" + ",";
				} else if (string.equals("3")) {
					ss = ss + "特惠" + ",";
				} else if (string.equals("7")) {
					ss = ss + "精品" + ",";
				}
			}
			dynaBean.set("type_name_hid", ss);
			dynaBean.set("goods_types", ss.substring(0, ss.length() - 1));
		}

		EcVouchersAudit ea = new EcVouchersAudit();
		ea.setLink_id(Long.valueOf(id));
		List<EcVouchersAudit> eaList = super.getFacade().getEcVouchersAuditService().getEcVouchersAuditList(ea);

		request.setAttribute("eaList", eaList);
		dynaBean.set("id", id);

		return new ActionForward("/../manager/spgl/EcVouchersApply/form2.jsp");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String prod_types = (String) dynaBean.get("prod_types");
		String goods_types_hid = (String) dynaBean.get("goods_types_hid");
		String goods_hid = (String) dynaBean.get("goods_hid");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcVouchersApply entity = new EcVouchersApply();
		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(prod_types)) {
			entity.setPd_type(prod_types.substring(0, prod_types.length() - 1));
		} else {
			entity.setPd_type(null);
		}

		if (StringUtils.isNotBlank(goods_types_hid)) {
			entity.setGoods_type(goods_types_hid.substring(0, goods_types_hid.length() - 1));
		} else {
			entity.setGoods_type(null);
		}

		if (StringUtils.isNotBlank(goods_hid)) {
			entity.setGoods_id(goods_hid.substring(0, goods_hid.length() - 1));
			entity.setPd_type(null);
			entity.setGoods_type(null);

			String goods = goods_hid.substring(0, goods_hid.length() - 1);
			String[] psd = goods.split(",");
			List<String> goods_sn = new ArrayList<String>();
			for (String string : psd) {
				KonkaBcompPd kp = new KonkaBcompPd();
				kp.setId(Long.valueOf(string));
				kp = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kp);
				if (kp != null) {
					goods_sn.add(kp.getPd_sn());
				}
			}
			entity.setGoods_sn(StringUtils.join(goods_sn, ","));

		} else {
			entity.setGoods_id(null);
			entity.setGoods_sn(null);
		}

		entity.setState(10);// 初始状态10

		if (StringUtils.isEmpty(id)) {
			entity.setAdd_date(new Date());
			entity.setCreate_u_id(user.getId());
			entity.setInfo_state(0);
			entity.setIs_send(0);
			super.getFacade().getEcVouchersApplyService().createEcVouchersApply(entity);
			super.saveMessage(request, "entity.inserted");
			// super.saveErrors(request, errors)
		} else {
			super.getFacade().getEcVouchersApplyService().modifyEcVouchersApply(entity);
			super.saveMessage(request, "entity.updated");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

	}

	public ActionForward save2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		// String prod_types = (String) dynaBean.get("prod_types");
		// String goods_types_hid = (String) dynaBean.get("goods_types_hid");
		// String num = (String) dynaBean.get("num");
		// String has_pwd = (String) dynaBean.get("has_pwd");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		EcVouchersApply ea = new EcVouchersApply();
		ea.setId(Long.valueOf(id));
		ea = super.getFacade().getEcVouchersApplyService().getEcVouchersApply(ea);
		if (ea == null) {
			super.renderJavaScript(response, "window.onload=function(){alert('数据异常！请不要重复发放优惠券');history.back();}");
			return null;
		} else if (ea.getIs_send() == 1) {
			super.renderJavaScript(response, "window.onload=function(){alert('数据异常！请不要重复发放优惠券');history.back();}");
			return null;
		}

		EcVouchersApply entity = new EcVouchersApply();
		super.copyProperties(entity, form);

		if (StringUtils.isNotEmpty(id)) {
			super.getFacade().getEcVouchersApplyService().createEcVouchersApplyBatch(ea);
			super.saveMessage(request, "entity.inserted");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		HttpSession session = request.getSession();

		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcVouchersApply entity = new EcVouchersApply();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getEcVouchersApplyService().getEcVouchersApply(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);

		if (entity.getPd_type() != null) {
			dynaBean.set("prod_types", entity.getPd_type() + ",");
			String[] aa = entity.getPd_type().split(",");
			String ss = "";
			for (String string : aa) {
				if (string.equals("0")) {
					ss = ss + "彩电" + ",";
				} else if (string.equals("3")) {
					ss = ss + "小家电" + ",";
				} else if (string.equals("5")) {
					ss = ss + "洗衣机" + ",";
				}
			}
			dynaBean.set("pd_name_hid", ss);
			dynaBean.set("prod_name", ss.substring(0, ss.length() - 1));
		}

		if (entity.getGoods_type() != null) {
			dynaBean.set("goods_types_hid", entity.getGoods_type() + ",");
			String[] aa = entity.getGoods_type().split(",");
			String ss = "";
			for (String string : aa) {
				if (string.equals("0")) {
					ss = ss + "新品" + ",";
				} else if (string.equals("2")) {
					ss = ss + "热销" + ",";
				} else if (string.equals("3")) {
					ss = ss + "特惠" + ",";
				} else if (string.equals("7")) {
					ss = ss + "精品" + ",";
				}
			}
			dynaBean.set("type_name_hid", ss);
			dynaBean.set("goods_types", ss.substring(0, ss.length() - 1));
		}

		if (entity.getGoods_id() != null) {
			dynaBean.set("goods_hid", entity.getGoods_id() + ",");
			String[] aa = entity.getGoods_id().split(",");
			String ss = "";
			for (String string : aa) {
				KonkaBcompPd kp = new KonkaBcompPd();
				kp.setId(Long.valueOf(string));
				kp = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kp);
				ss = ss + kp.getPd_sn() + ",";
			}
			dynaBean.set("goods_name_hid", ss);
			dynaBean.set("goods", ss.substring(0, ss.length() - 1));
		}

		EcVouchersAudit ea = new EcVouchersAudit();
		ea.setLink_id(Long.valueOf(id));
		List<EcVouchersAudit> eaList = super.getFacade().getEcVouchersAuditService().getEcVouchersAuditList(ea);

		request.setAttribute("eaList", eaList);

		PeProdUser pp = new PeProdUser();
		pp.setId(entity.getCreate_u_id());
		pp = super.getFacade().getPeProdUserService().getPeProdUser(pp);
		dynaBean.set("user_name", pp.getUser_name());

		return mapping.findForward("view");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		EcVouchersApply entity = new EcVouchersApply();
		entity.setId(Long.valueOf(id));
		entity.setInfo_state(1);
		super.getFacade().getEcVouchersApplyService().removeEcVouchersApply(entity);

		saveMessage(request, "entity.deleted");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.serialize(request, "id", "method"));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 140317 || pu.getRole_id().intValue() == 1001) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}

		if (zb) {
			request.setAttribute("is_admin", "1");

			EcGroup eg = new EcGroup();
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("groupList", deptList);

		} else if (!zb && fgs) {
			if (null != user.getDept_id()) {
				KonkaDept konkaDept = new KonkaDept();
				konkaDept.setDept_id(user.getDept_id());
				konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
				// 分公司取得
				if (null != konkaDept.getDept_type() && konkaDept.getDept_type() > 2) {
					KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);
					dynaBean.set("fgs_id", fgs_dept.getDept_id());

					request.setAttribute("is_fgs", "1");
					EcGroup eg = new EcGroup();
					eg.setLink_dept_id(fgs_dept.getDept_id());
					List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
					request.setAttribute("groupList", deptList);
				}
			}

		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('你没有权限查看价格！');history.back();}");
			return null;
		}

		EcVouchersApply entity = new EcVouchersApply();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getEcVouchersApplyService().getEcVouchersApply(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);

		if (entity.getPd_type() != null) {
			dynaBean.set("prod_types", entity.getPd_type() + ",");
			String[] aa = entity.getPd_type().split(",");
			String ss = "";
			for (String string : aa) {
				if (string.equals("0")) {
					ss = ss + "彩电" + ",";
				} else if (string.equals("3")) {
					ss = ss + "小家电" + ",";
				} else if (string.equals("5")) {
					ss = ss + "洗衣机" + ",";
				}
			}
			dynaBean.set("pd_name_hid", ss);
			dynaBean.set("prod_name", ss.substring(0, ss.length() - 1));
		}

		if (entity.getGoods_type() != null) {
			dynaBean.set("goods_types_hid", entity.getGoods_type() + ",");
			String[] aa = entity.getGoods_type().split(",");
			String ss = "";
			for (String string : aa) {
				if (string.equals("0")) {
					ss = ss + "新品" + ",";
				} else if (string.equals("2")) {
					ss = ss + "热销" + ",";
				} else if (string.equals("3")) {
					ss = ss + "特惠" + ",";
				} else if (string.equals("7")) {
					ss = ss + "精品" + ",";
				}
			}
			dynaBean.set("type_name_hid", ss);
			dynaBean.set("goods_types", ss.substring(0, ss.length() - 1));
		}

		if (entity.getGoods_id() != null) {
			dynaBean.set("goods_hid", entity.getGoods_id() + ",");
			String[] aa = entity.getGoods_id().split(",");
			String ss = "";
			for (String string : aa) {
				KonkaBcompPd kp = new KonkaBcompPd();
				kp.setId(Long.valueOf(string));
				kp = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kp);
				ss = ss + kp.getPd_sn() + ",";
			}
			dynaBean.set("goods_name_hid", ss);
			dynaBean.set("goods", ss.substring(0, ss.length() - 1));
		}

		return mapping.findForward("input");
	}

	public ActionForward chooseProdType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		// DynaBean dynaBean = (DynaBean) form;

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		return new ActionForward("/../manager/spgl/EcVouchersApply/chooseProdType.jsp");
	}

	public ActionForward chooseGoods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String pd_sn_like = (String) dynaBean.get("pd_sn_like");
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		String label_of_cate = (String) dynaBean.get("label_of_cate");
		String prod_type = (String) dynaBean.get("prod_type");
		String is_zb = (String) dynaBean.get("is_zb");
		String prod_types = (String) dynaBean.get("prod_types");
		String goods_types_hid = (String) dynaBean.get("goods_types_hid");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaBcompPd entity = new KonkaBcompPd();
		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 178 || pu.getRole_id().intValue() == 140317
						|| pu.getRole_id().intValue() == 1001) {
					zb = true;// 总部
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;// 分公司
				}
			}
		}
		if (zb) {
			// ...
		} else if (!zb && fgs) {
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			EcGroup eg = new EcGroup();
			eg.setLink_dept_id(fgs_dept.getDept_id());
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			if (deptList != null && deptList.size() > 0) {
				eg = deptList.get(0);
				entity.getMap().put("dept_id_in", "(\'0\'," + "\'" + eg.getId().toString() + "\'" + ")");
			} else {
				entity.getMap().put("dept_id_in", "(\'0\')");
			}
		}

		if (StringUtils.isNotBlank(pd_sn_like)) {
			entity.getMap().put("pd_sn_like", pd_sn_like);
		}
		if (StringUtils.isNotBlank(pd_name_like)) {
			entity.getMap().put("pd_name_like", pd_name_like);
		}
		if (StringUtils.isNotBlank(label_of_cate)) {
			entity.setLabel_of_cate(Integer.valueOf(label_of_cate));
		}

		if (StringUtils.isNotBlank(prod_types)) {
			prod_types = prod_types.substring(0, prod_types.length() - 1);
			String[] pds = prod_types.split(",");
			entity.getMap().put("pds", pds);

			dynaBean.set("prod_types", prod_types + ",");

		}
		if (StringUtils.isNotBlank(goods_types_hid)) {
			goods_types_hid = goods_types_hid.substring(0, goods_types_hid.length() - 1);
			String[] types = goods_types_hid.split(",");
			entity.getMap().put("types", types);

			dynaBean.set("goods_types_hid", goods_types_hid + ",");
		}

		if (StringUtils.isNotBlank(prod_type)) {
			entity.setProd_type(Integer.valueOf(prod_type));
		}

		if (StringUtils.isNotBlank(is_zb)) {
			if (is_zb.equals("1")) {
				entity.setDept_sn("0");
				dynaBean.set("is_zb", "1");
			} else if (is_zb.equals("2")) {
				entity.getMap().put("is_not_zb", true);
				dynaBean.set("is_zb", "2");
			}
		}

		entity.setState(1);
		entity.setOwn_sys(2);// 触网
		entity.setIs_allow_voucher(0);// 允许使用有优惠券的商品
		// entity.getMap().put("own_sys_in", new Integer[] { 1, 2, 4 });

		Long recordCount = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdWithDeptAndMdNewCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaBcompPd> entityList = super.getFacade().getKonkaBcompPdService()
				.getKonkaBcompPdWithDeptAndMdNewPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		return new ActionForward("/../manager/spgl/EcVouchersApply/chooseGoods.jsp");
	}

	public ActionForward chooseGoodType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		// DynaBean dynaBean = (DynaBean) form;

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		return new ActionForward("/../manager/spgl/EcVouchersApply/chooseGoodType.jsp");
	}

	public ActionForward ajaxShow(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String cs = (String) dynaBean.get("cs");
		// 存放数据
		Map<String, String> maps = new HashMap<String, String>();
		if (cs.length() > 0) {
			cs = cs.substring(0, cs.length() - 1);
			String[] aa = cs.split(",");
			String ss = "";
			for (String string : aa) {
				KonkaBcompPd kp = new KonkaBcompPd();
				kp.setId(Long.valueOf(string));
				kp = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kp);
				ss = ss + kp.getPd_sn() + ",";
			}
			maps.put("goods_name_hid", ss);
			maps.put("goods", ss.substring(0, ss.length() - 1));
			maps.put("status", "1");
			super.renderJson(response, JSON.toJSONString(maps));
			return null;
		} else {
			maps.put("status", "0");
			super.renderJson(response, JSON.toJSONString(maps));
			return null;
		}

	}

	public ActionForward sheet(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		Pager pager = (Pager) dynaBean.get("pager");
		String title_like = (String) dynaBean.get("title_like");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcVouchersApply entity = new EcVouchersApply();
		if (StringUtils.isNotBlank(title_like)) {
			entity.getMap().put("title_like", title_like);

		}
		entity.setOwn_sys(2);
		Long recordCount = super.getFacade().getEcVouchersApplyService().getEcVouchersApplyCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(0);
		entity.getRow().setCount(recordCount.intValue());

		List<EcVouchersApply> entityList = super.getFacade().getEcVouchersApplyService()
				.getEcVouchersApplyPaginatedList(entity);
		for (EcVouchersApply ecVouchers : entityList) {
			if (null != ecVouchers.getUser_id()) {
				EcUser ec = new EcUser();
				ec.setId(ecVouchers.getUser_id());
				ec = super.getFacade().getEcUserService().getEcUser(ec);
				if (null != ec && null != ec.getReal_name()) {
					ecVouchers.getMap().put("ec_user_name", ec.getReal_name());
				}
			}
			if (ecVouchers.getGoods_type() != null) {
				String goods_type = ecVouchers.getGoods_type().replace("0", "新品").replace("2", "热销").replace("3", "特惠")
						.replace("7", "精品");
				ecVouchers.getMap().put("goods_type", goods_type);
			}

			if (ecVouchers.getPd_type() != null) {
				String pd_type = ecVouchers.getPd_type().replace("0", "彩电").replace("3", "小家电").replace("5", "洗衣机");
				ecVouchers.getMap().put("pd_type", pd_type);
			}
		}
		request.setAttribute("entityList", entityList);
		response.setHeader("Content-disposition", "attachment; filename=noname.xls");// 设定输出文件头
		response.setContentType("application/vnd.ms-excel");// 定义输出类型
		return new ActionForward("/../manager/spgl/EcVouchersApply/sheet.jsp");
	}
}
