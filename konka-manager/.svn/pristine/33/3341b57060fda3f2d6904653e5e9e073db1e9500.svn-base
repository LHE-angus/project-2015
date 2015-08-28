package com.ebiz.mmt.web.struts.manager.spgl;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcGroup;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcVouchers;
import com.ebiz.mmt.domain.EcVouchersApply;
import com.ebiz.mmt.domain.EcVouchersAudit;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Pan,Gang
 * @version 2013-09-24
 */
public class EcVouchersGlAction extends BaseAction {
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
		String dept_id = (String) dynaBean.get("dept_id");

		String apply_name_like = (String) dynaBean.get("apply_name_like");
		String pd_type_in = (String) dynaBean.get("pd_type");
		String goods_type_in = (String) dynaBean.get("goods_type");
		String goods_like = (String) dynaBean.get("goods_like");
		String info_state = (String) dynaBean.get("info_state");

		String add_date_start = (String) dynaBean.get("add_date_start");
		String add_date_end = (String) dynaBean.get("add_date_end");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcVouchers entity = new EcVouchers();
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

		if (StringUtils.isNotBlank(info_state)) {
			entity.setInfo_state(Integer.valueOf(info_state));
		}

		if (StringUtils.isNotBlank(add_date_start)) {
			entity.getMap().put("add_date_start", add_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_date_end)) {
			entity.getMap().put("add_date_end", add_date_end + " 23:59:59");
		}

		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
		}

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
			EcGroup eg = new EcGroup();
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("deptList", deptList);
		} else if (!zb && fgs) {
			EcGroup eg = new EcGroup();
			eg.setLink_dept_id(super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id());
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("deptList", deptList);
			if (deptList != null && deptList.size() > 0) {
				List<String> ids = new ArrayList<String>();
				for (EcGroup ecGroup : deptList) {
					ids.add(ecGroup.getId().toString());
				}
				dynaBean.set("group_id", StringUtils.join(ids, ","));
				dynaBean.set("is_binding", true);
				entity.getMap().put("is_fgs", ids);
			} else {
				entity.getMap().put("is_fgs", new String[] { "999999" });
			}
		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('你没有权限查看价格！');history.back();}");
			return null;
		}

		entity.setOwn_sys(2);
		Long recordCount = super.getFacade().getEcVouchersService().getEcVouchersCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcVouchers> entityList = super.getFacade().getEcVouchersService().getEcVouchersPaginatedList(entity);
		for (EcVouchers ecVouchers : entityList) {
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
			if (ecVouchers.getDept_id() != null) {
				EcGroup eg = new EcGroup();
				eg.setId(ecVouchers.getDept_id());
				eg = super.getFacade().getEcGroupService().getEcGroup(eg);
				if (eg != null) {
					ecVouchers.getMap().put("group_name", eg.getGroup_name());
				}
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

			if (ecVouchers.getLink_id() != null) {
				EcVouchersApply ea = new EcVouchersApply();
				ea.setId(ecVouchers.getLink_id());
				ea = super.getFacade().getEcVouchersApplyService().getEcVouchersApply(ea);
				if (ea != null) {
					PeProdUser pp = new PeProdUser();
					pp.setId(ea.getCreate_u_id());
					pp = super.getFacade().getPeProdUserService().getPeProdUser(pp);
					if (pp != null) {
						ecVouchers.getMap().put("apply_user_name", pp.getUser_name());
					}

					if (ea.getAdd_date() != null) {
						ecVouchers.getMap().put("apply_date", ea.getAdd_date());
					}

				}
			}

			if (ecVouchers.getOrder_id() != null && !ecVouchers.getOrder_id().equals("")) {
				PshowOrder pp = new PshowOrder();
				pp.setId(Long.valueOf(ecVouchers.getOrder_id()));
				pp = super.getFacade().getPshowOrderService().getPshowOrder(pp);
				if (pp != null) {
					ecVouchers.getMap().put("used_date", pp.getAdd_date());
				}
			}

		}

		request.setAttribute("entityList", entityList);

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

		return new ActionForward("/../manager/spgl/EcVouchersGl/form2.jsp");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String prod_types = (String) dynaBean.get("prod_types");
		String goods_types_hid = (String) dynaBean.get("goods_types_hid");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcVouchers entity = new EcVouchers();
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

		if (StringUtils.isEmpty(id)) {
			entity.setAdd_date(new Date());
			entity.setCreate_u_id(user.getId());
			entity.setInfo_state(0);
			super.getFacade().getEcVouchersService().createEcVouchers(entity);
			super.saveMessage(request, "entity.inserted");
		} else {
			super.getFacade().getEcVouchersService().modifyEcVouchers(entity);
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
		String prod_types = (String) dynaBean.get("prod_types");
		String goods_types_hid = (String) dynaBean.get("goods_types_hid");
		String num = (String) dynaBean.get("num");
		String has_pwd = (String) dynaBean.get("has_pwd");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcVouchers entity = new EcVouchers();
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

		if (StringUtils.isEmpty(id)) {
			entity.setAdd_date(new Date());
			entity.setCreate_u_id(user.getId());
			entity.setInfo_state(0);
			entity.getMap().put("num", num);
			entity.getMap().put("has_pwd", has_pwd);
			super.getFacade().getEcVouchersService().createBatch(entity);
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

		EcVouchers ec = new EcVouchers();
		ec.setId(Long.valueOf(id));
		ec = super.getFacade().getEcVouchersService().getEcVouchers(ec);
		super.copyProperties(form, ec);

		if (ec.getLink_id() != null) {
			EcVouchersApply ea = new EcVouchersApply();
			ea.setId(ec.getLink_id());
			ea = super.getFacade().getEcVouchersApplyService().getEcVouchersApply(ea);
			if (ea != null) {
				PeProdUser pp = new PeProdUser();
				pp.setId(ea.getCreate_u_id());
				pp = super.getFacade().getPeProdUserService().getPeProdUser(pp);
				if (pp != null) {
					dynaBean.set("apply_user_name", pp.getUser_name());
				}

			}
		}

		if (ec.getUser_id() != null) {
			EcUser ecUser = new EcUser();
			ecUser.setId(ec.getUser_id());
			ecUser = super.getFacade().getEcUserService().getEcUser(ecUser);
			if (null != ecUser && null != ecUser.getReal_name()) {
				dynaBean.set("real_name", ecUser.getReal_name());
			}
		}

		if (ec.getPd_type() != null) {
			dynaBean.set("prod_types", ec.getPd_type() + ",");
			String[] aa = ec.getPd_type().split(",");
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

		if (ec.getGoods_type() != null) {
			dynaBean.set("goods_types_hid", ec.getGoods_type() + ",");
			String[] aa = ec.getGoods_type().split(",");
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

		if (ec.getGoods_id() != null) {
			String pd_sn = "";
			String[] aa = ec.getGoods_id().split(",");
			for (String string : aa) {
				KonkaBcompPd kp = new KonkaBcompPd();
				kp.setId(Long.valueOf(string));
				kp = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kp);
				pd_sn = pd_sn + kp.getPd_sn() + ",";
			}
			dynaBean.set("pd_sn", pd_sn.substring(0, pd_sn.length() - 1));
		}

		if (ec.getLink_id() != null) {
			EcVouchersAudit ea = new EcVouchersAudit();
			ea.setLink_id(ec.getLink_id());
			List<EcVouchersAudit> eaList = super.getFacade().getEcVouchersAuditService().getEcVouchersAuditList(ea);
			request.setAttribute("eaList", eaList);
		}

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

		EcVouchers entity = new EcVouchers();
		entity.setId(Long.valueOf(id));
		entity.setInfo_state(1);
		super.getFacade().getEcVouchersService().modifyEcVouchers(entity);

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

		EcVouchers entity = new EcVouchers();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getEcVouchersService().getEcVouchers(entity);
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

		return new ActionForward("/../manager/spgl/EcVouchersGl/chooseProdType.jsp");
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

		return new ActionForward("/../manager/spgl/EcVouchersGl/chooseGoodType.jsp");
	}

	public ActionForward chooseGoods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String pd_sn_like = (String) dynaBean.get("pd_sn_like");
		String pd_name_like = (String) dynaBean.get("pd_name_like");

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

		entity.setState(1);
		entity.setOwn_sys(2);// 触网
		// entity.getMap().put("own_sys_in", new Integer[] { 1, 2, 4 });

		Long recordCount = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdWithDeptAndMdNewCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaBcompPd> entityList = super.getFacade().getKonkaBcompPdService()
				.getKonkaBcompPdWithDeptAndMdNewPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		return new ActionForward("/../manager/spgl/EcVouchersGl/chooseGoods.jsp");
	}

	public ActionForward sheet(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		Pager pager = (Pager) dynaBean.get("pager");
		String title_like = (String) dynaBean.get("title_like");
		String dept_id = (String) dynaBean.get("dept_id");

		String apply_name_like = (String) dynaBean.get("apply_name_like");
		String pd_type_in = (String) dynaBean.get("pd_type");
		String goods_type_in = (String) dynaBean.get("goods_type");
		String goods_like = (String) dynaBean.get("goods_like");
		String info_state = (String) dynaBean.get("info_state");

		String add_date_start = (String) dynaBean.get("add_date_start");
		String add_date_end = (String) dynaBean.get("add_date_end");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcVouchers entity = new EcVouchers();
		if (StringUtils.isNotBlank(title_like)) {
			entity.getMap().put("title_like", title_like);

		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
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

		if (StringUtils.isNotBlank(info_state)) {
			entity.setInfo_state(Integer.valueOf(info_state));
		}

		if (StringUtils.isNotBlank(add_date_start)) {
			entity.getMap().put("add_date_start", add_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_date_end)) {
			entity.getMap().put("add_date_end", add_date_end + " 23:59:59");
		}

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

		} else if (!zb && fgs) {
			EcGroup eg = new EcGroup();
			eg.setLink_dept_id(super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id());
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			if (deptList != null && deptList.size() > 0) {
				List<String> ids = new ArrayList<String>();
				for (EcGroup ecGroup : deptList) {
					ids.add(ecGroup.getId().toString());
				}
				dynaBean.set("group_id", StringUtils.join(ids, ","));
				dynaBean.set("is_binding", true);
				entity.getMap().put("is_fgs", ids);
			}
		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('你没有权限查看价格！');history.back();}");
			return null;
		}

		entity.setOwn_sys(2);
		Long recordCount = super.getFacade().getEcVouchersService().getEcVouchersCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(0);
		entity.getRow().setCount(recordCount.intValue());

		List<EcVouchers> entityList = super.getFacade().getEcVouchersService().getEcVouchersPaginatedList(entity);
		for (EcVouchers ecVouchers : entityList) {
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

			if (ecVouchers.getPd_type() != null) {
				String pd_type = ecVouchers.getPd_type().replace("0", "彩电").replace("3", "小家电").replace("5", "洗衣机");
				ecVouchers.getMap().put("pd_type", pd_type);
			}
			if (ecVouchers.getDept_id() != null) {
				EcGroup eg = new EcGroup();
				eg.setId(ecVouchers.getDept_id());
				eg = super.getFacade().getEcGroupService().getEcGroup(eg);
				if (eg != null) {
					ecVouchers.getMap().put("group_name", eg.getGroup_name());
				}
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

			if (ecVouchers.getLink_id() != null) {
				EcVouchersApply ea = new EcVouchersApply();
				ea.setId(ecVouchers.getLink_id());
				ea = super.getFacade().getEcVouchersApplyService().getEcVouchersApply(ea);
				if (ea != null) {
					PeProdUser pp = new PeProdUser();
					pp.setId(ea.getCreate_u_id());
					pp = super.getFacade().getPeProdUserService().getPeProdUser(pp);
					if (pp != null) {
						ecVouchers.getMap().put("apply_user_name", pp.getUser_name());
					}

					if (ea.getAdd_date() != null) {
						ecVouchers.getMap().put("apply_date", ea.getAdd_date());
					}

				}
			}

			if (ecVouchers.getOrder_id() != null && !ecVouchers.getOrder_id().equals("")) {
				PshowOrder pp = new PshowOrder();
				pp.setId(Long.valueOf(ecVouchers.getOrder_id()));
				pp = super.getFacade().getPshowOrderService().getPshowOrder(pp);
				if (pp != null) {
					ecVouchers.getMap().put("used_date", pp.getAdd_date());
				}
			}
		}

		response.setCharacterEncoding(Constants.SYS_ENCODING);
		request.setAttribute("entityList", entityList);
		response.setHeader("Content-disposition",
				"attachment; filename=\"" + URLEncoder.encode("优惠券管理", Constants.SYS_ENCODING) + ".xls\"");// 设定输出文件头
		response.setContentType("application/vnd.ms-excel");// 定义输出类型
		return new ActionForward("/../manager/spgl/EcVouchersGl/sheet.jsp");
	}
}
