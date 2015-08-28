package com.ebiz.mmt.web.struts.manager.spgl;

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
public class EcVouchersApplyForZbAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String title_like = (String) dynaBean.get("title_like");
		String audit_state = (String) dynaBean.get("audit_state");
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

		if (StringUtils.isNotBlank(audit_state)) {
			if (audit_state.equals("0")) {
				entity.setState(30);
				entity.setInfo_state(0);
			} else if (audit_state.equals("1")) {
				EcVouchersAudit ec = new EcVouchersAudit();
				ec.setOpr_user_id(user.getId());
				List<EcVouchersAudit> ecList = super.getFacade().getEcVouchersAuditService().getEcVouchersAuditList(ec);
				if (ecList != null && ecList.size() > 0) {
					List<String> ids = new ArrayList<String>();
					for (EcVouchersAudit ecVouchersAudit : ecList) {
						ids.add(ecVouchersAudit.getLink_id().toString());
					}
					entity.getMap().put("ids_in", ids);
				}
			}

		} else {
			entity.setState(30);
			entity.setInfo_state(0);
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

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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

		EcVouchersAudit ea = new EcVouchersAudit();
		ea.setLink_id(Long.valueOf(id));
		List<EcVouchersAudit> eaList = super.getFacade().getEcVouchersAuditService().getEcVouchersAuditList(ea);

		request.setAttribute("eaList", eaList);

		return mapping.findForward("input");
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

		EcVouchersAudit ea = new EcVouchersAudit();
		ea.setLink_id(Long.valueOf(id));
		List<EcVouchersAudit> eaList = super.getFacade().getEcVouchersAuditService().getEcVouchersAuditList(ea);

		request.setAttribute("eaList", eaList);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String state = (String) dynaBean.get("state");
		String remark = (String) dynaBean.get("remark");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcVouchersApply entity = new EcVouchersApply();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getEcVouchersApplyService().getEcVouchersApply(entity);
		if (entity.getState().intValue() != 30) {
			super.renderJavaScript(response, "window.onload=function(){alert('审核状态已经发生改变！不要重复审核');history.back();}");
			return null;

		}
		super.copyProperties(entity, form);

		if (state.equals("0")) {
			entity.setInfo_state(1);
			entity.setState(40);//
		} else if (state.equals("1")) {
			entity.setInfo_state(2);
			entity.setState(40);
		}

		if (StringUtils.isNotEmpty(id)) {
			super.getFacade().getEcVouchersApplyService().modifyEcVouchersApply(entity);

			EcVouchersAudit ea = new EcVouchersAudit();
			ea.setLink_id(Long.valueOf(id));
			ea.setOper_date(new Date());
			ea.setOpr_user_id(user.getId());
			ea.setOpr_user_real_name(user.getUser_name());
			if (StringUtils.isNotBlank(remark)) {
				ea.setRemark(remark);
			}
			ea.setState(Integer.valueOf(state));
			super.getFacade().getEcVouchersAuditService().createEcVouchersAudit(ea);

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
