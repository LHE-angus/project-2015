package com.ebiz.mmt.web.struts.manager.spgl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.EcCust;
import com.ebiz.mmt.domain.EcCustRelUser;
import com.ebiz.mmt.domain.EcGroup;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.r3.ReturnInfo;
import com.ebiz.mmt.r3.StockCheckResult;
import com.ebiz.mmt.r3.ZLEBIN;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Pan,Gang
 * @version 2013-09-23
 */
public class EcCustAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String cust_name_like = (String) dynaBean.get("cust_name_like");
		String cust_code_like = (String) dynaBean.get("cust_code_like");
		String del_mark = (String) dynaBean.get("del_mark");

		String cust_type = (String) dynaBean.get("cust_type");
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String add_user_name_like = (String) dynaBean.get("add_user_name_like");
		String group_id = (String) dynaBean.get("group_id");
		String user_name_like = (String) dynaBean.get("user_name_like");
		String add_time_start = (String) dynaBean.get("add_time_start");
		String add_time_end = (String) dynaBean.get("add_time_end");

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
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 178 || pu.getRole_id().intValue() == 140317
				        || pu.getRole_id().intValue() == 1001) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}

		EcCust entity = new EcCust();

		if (StringUtils.isNotBlank(cust_type)) {
			entity.setCust_type(Integer.valueOf(cust_type));
		}
		if (StringUtils.isNotBlank(group_id)) {
			entity.setGroup_id(Long.valueOf(group_id));
		}
		if (StringUtils.isNotBlank(r3_code_like)) {
			entity.getMap().put("r3_code_like", r3_code_like);
		}
		if (StringUtils.isNotBlank(add_user_name_like)) {
			entity.getMap().put("add_user_name_like", add_user_name_like);
		}
		if (StringUtils.isNotBlank(user_name_like)) {
			entity.getMap().put("user_name_like", user_name_like);
		}
		if (StringUtils.isNotBlank(add_time_start)) {
			entity.getMap().put("add_time_start", add_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_time_end)) {
			entity.getMap().put("add_time_end", add_time_end + " 23:59:59");
		}

		if (StringUtils.isNotBlank(del_mark)) {
			entity.setDel_mark(Integer.valueOf(del_mark));
		}

		if (zb) {
			EcGroup eg = new EcGroup();
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("deptList", deptList);

		} else if (!zb && fgs) {
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			EcGroup eg = new EcGroup();
			eg.setLink_dept_id(fgs_dept.getDept_id());
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("deptList", deptList);
			if (deptList != null && deptList.size() > 0) {
				List<String> ids = new ArrayList<String>();
				for (EcGroup ecGroup : deptList) {
					ids.add(ecGroup.getId().toString());
				}
				entity.getMap().put("is_fgs", ids);
			} else {
				entity.getMap().put("is_fgs", new String[] { "999999" });
			}

		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('你没有权限查看价格！');history.back();}");
			return null;
		}

		if (StringUtils.isNotBlank(cust_code_like)) {
			entity.getMap().put("cust_code_like", cust_code_like);

		}
		if (StringUtils.isNotBlank(cust_name_like)) {
			entity.getMap().put("cust_name_like", cust_name_like);

		}

		Long recordCount = super.getFacade().getEcCustService().getEcCustForDetailsCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcCust> entityList = super.getFacade().getEcCustService().getEcCustForDetailsPaginatedList(entity);
		if (entityList != null && entityList.size() > 0) {
			for (EcCust ecCust : entityList) {
				String ec_user_names = "";
				List<String> ec_names = new ArrayList<String>();
				EcCustRelUser eu = new EcCustRelUser();
				eu.setCust_id(ecCust.getId());
				List<EcCustRelUser> euList = super.getFacade().getEcCustRelUserService().getEcCustRelUserList(eu);
				if (euList != null && euList.size() > 0) {
					for (EcCustRelUser ecCustRelUser : euList) {
						EcUser ec = new EcUser();
						ec.setId(ecCustRelUser.getUser_id());
						ec = super.getFacade().getEcUserService().getEcUser(ec);
						if (ec != null) {
							ec_names.add(ec.getUser_name());
						}
					}
				}
				ec_user_names = StringUtils.join(ec_names, ",");
				ecCust.getMap().put("ec_user_names", ec_user_names);

			}
		}
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward sheet(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String cust_name_like = (String) dynaBean.get("cust_name_like");
		String cust_code_like = (String) dynaBean.get("cust_code_like");
		String del_mark = (String) dynaBean.get("del_mark");

		String cust_type = (String) dynaBean.get("cust_type");
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String add_user_name_like = (String) dynaBean.get("add_user_name_like");
		String group_id = (String) dynaBean.get("group_id");
		String user_name_like = (String) dynaBean.get("user_name_like");
		String add_time_start = (String) dynaBean.get("add_time_start");
		String add_time_end = (String) dynaBean.get("add_time_end");

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
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 178 || pu.getRole_id().intValue() == 140317
				        || pu.getRole_id().intValue() == 1001) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}

		EcCust entity = new EcCust();

		if (StringUtils.isNotBlank(cust_type)) {
			entity.setCust_type(Integer.valueOf(cust_type));
		}
		if (StringUtils.isNotBlank(group_id)) {
			entity.setGroup_id(Long.valueOf(group_id));
		}
		if (StringUtils.isNotBlank(r3_code_like)) {
			entity.getMap().put("r3_code_like", r3_code_like);
		}
		if (StringUtils.isNotBlank(add_user_name_like)) {
			entity.getMap().put("add_user_name_like", add_user_name_like);
		}
		if (StringUtils.isNotBlank(user_name_like)) {
			entity.getMap().put("user_name_like", user_name_like);
		}
		if (StringUtils.isNotBlank(add_time_start)) {
			entity.getMap().put("add_time_start", add_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_time_end)) {
			entity.getMap().put("add_time_end", add_time_end + " 23:59:59");
		}

		if (StringUtils.isNotBlank(del_mark)) {
			entity.setDel_mark(Integer.valueOf(del_mark));
		}

		if (zb) {
			EcGroup eg = new EcGroup();
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("deptList", deptList);

		} else if (!zb && fgs) {
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			EcGroup eg = new EcGroup();
			eg.setLink_dept_id(fgs_dept.getDept_id());
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("deptList", deptList);
			if (deptList != null && deptList.size() > 0) {
				List<String> ids = new ArrayList<String>();
				for (EcGroup ecGroup : deptList) {
					ids.add(ecGroup.getId().toString());
				}
				entity.getMap().put("is_fgs", ids);
			} else {
				entity.getMap().put("is_fgs", new String[] { "999999" });
			}

		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('你没有权限查看价格！');history.back();}");
			return null;
		}

		if (StringUtils.isNotBlank(cust_code_like)) {
			entity.getMap().put("cust_code_like", cust_code_like);

		}
		if (StringUtils.isNotBlank(cust_name_like)) {
			entity.getMap().put("cust_name_like", cust_name_like);

		}

		Long recordCount = super.getFacade().getEcCustService().getEcCustForDetailsCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(0);
		entity.getRow().setCount(recordCount.intValue());

		List<EcCust> entityList = super.getFacade().getEcCustService().getEcCustForDetailsPaginatedList(entity);
		if (entityList != null && entityList.size() > 0) {
			for (EcCust ecCust : entityList) {
				String ec_user_names = "";
				List<String> ec_names = new ArrayList<String>();
				EcCustRelUser eu = new EcCustRelUser();
				eu.setCust_id(ecCust.getId());
				List<EcCustRelUser> euList = super.getFacade().getEcCustRelUserService().getEcCustRelUserList(eu);
				if (euList != null && euList.size() > 0) {
					for (EcCustRelUser ecCustRelUser : euList) {
						EcUser ec = new EcUser();
						ec.setId(ecCustRelUser.getUser_id());
						ec = super.getFacade().getEcUserService().getEcUser(ec);
						if (ec != null) {
							ec_names.add(ec.getUser_name());
						}
					}
				}
				ec_user_names = StringUtils.join(ec_names, ",");
				ecCust.getMap().put("ec_user_names", ec_user_names);

			}
		}
		request.setAttribute("entityList", entityList);

		response.setHeader("Content-disposition", "attachment; filename=noname.xls");// 设定输出文件头
		response.setContentType("application/vnd.ms-excel");// 定义输出类型
		return new ActionForward("/../manager/spgl/EcCust/sheet.jsp");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

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
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 178 || pu.getRole_id().intValue() == 140317
				        || pu.getRole_id().intValue() == 1001) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}

		EcGroup eg = new EcGroup();
		if (zb) {
			List<EcGroup> groupList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("groupList", groupList);
		} else if (fgs) {
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			eg.setLink_dept_id(fgs_dept.getDept_id());
			List<EcGroup> groupList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("groupList", groupList);
		}

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String cust_code = (String) dynaBean.get("cust_code");
		String cust_type = (String) dynaBean.get("cust_type");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcCust entity = new EcCust();
		super.copyProperties(entity, form);
		entity.setAdd_date(new Date());
		entity.setAdd_user_id(user.getId());

		if (StringUtils.isNotBlank(cust_type)) {
			if (cust_type.equals("1")) {
				entity.getMap().put("cust_type_eq_1", true);
				entity.setR3_code(null);
			}
		}

		if (StringUtils.isEmpty(id)) {

			EcCust cc = new EcCust();
			cc.setCust_code(cust_code);
			Long count = super.getFacade().getEcCustService().getEcCustCount(entity);
			if (count != 0) {
				super.renderJavaScript(response, "window.onload=function(){alert('客户编码\"" + cust_code
				        + "\"与数据库中重复，请重新填写 ');history.back();}");
				return null;
			}

			entity.setDel_mark(0);
			super.getFacade().getEcCustService().createEcCust(entity);
			super.saveMessage(request, "entity.inserted");
		} else {
			super.getFacade().getEcCustService().modifyEcCust(entity);
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

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		EcCust entity = new EcCust();
		entity.setId(Long.valueOf(id));
		entity.setDel_mark(1);
		super.getFacade().getEcCustService().modifyEcCust(entity);

		saveMessage(request, "entity.updated");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward restart(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		EcCust entity = new EcCust();
		entity.setId(Long.valueOf(id));
		entity.setDel_mark(0);
		super.getFacade().getEcCustService().modifyEcCust(entity);

		saveMessage(request, "entity.updated");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
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

		EcCust entity = new EcCust();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getEcCustService().getEcCust(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);

		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 178 || pu.getRole_id().intValue() == 140317
				        || pu.getRole_id().intValue() == 1001) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}

		if (entity != null && entity.getR3_code() != null) {
			dynaBean.set("r3_code", entity.getR3_code());
			dynaBean.set("r3_code_show", entity.getR3_code());
		}

		EcGroup eg = new EcGroup();
		if (zb) {
			List<EcGroup> groupList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("groupList", groupList);
		} else if (fgs) {
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			eg.setLink_dept_id(fgs_dept.getDept_id());
			List<EcGroup> groupList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("groupList", groupList);
		}

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		return mapping.findForward("input");
	}

	public ActionForward validateName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		StringBuffer oper = new StringBuffer("{\"result\":");
		String cust_code = (String) dynaBean.get("cust_code");
		EcCust entity = new EcCust();
		entity.setCust_code(cust_code);
		Long count = super.getFacade().getEcCustService().getEcCustCount(entity);
		if ("0".equals(count.toString())) {
			oper.append(false);
		} else {
			oper.append(true);
		}
		oper.append("}");
		super.render(response, oper.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward listCustomer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String fgs_id = (String) dynaBean.get("fgs_id");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String r3_code = (String) dynaBean.get("r3_code");
		String id = (String) dynaBean.get("id");
		Pager pager = (Pager) dynaBean.get("pager");

		if (!GenericValidator.isLong(fgs_id)) {
			return null;
		}

		EcGroup ec = new EcGroup();
		ec.setId(Long.valueOf(fgs_id));
		ec = super.getFacade().getEcGroupService().getEcGroup(ec);
		if (ec != null && ec.getLink_dept_id() != null) {
			KonkaDept t = new KonkaDept();
			t.setDept_id(ec.getLink_dept_id());
			t = super.getFacade().getKonkaDeptService().getKonkaDept(t);

			if (null == t || t.getDept_sn() == null) {
				return null;
			}
			KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
			konkaR3Shop.setBranch_area_name_2(t.getDept_sn());
			konkaR3Shop.getMap().put("customer_name_like", customer_name_like);
			konkaR3Shop.setIs_del(0L);// 未删除的客户
			if (StringUtils.isNotBlank(r3_code)) {
				konkaR3Shop.setR3_code(r3_code);
			}

			Long recordCount = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopCount(konkaR3Shop);
			pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
			konkaR3Shop.getRow().setFirst(pager.getFirstRow());
			konkaR3Shop.getRow().setCount(pager.getRowCount());

			List<KonkaR3Shop> entityList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopPaginatedList(
			        konkaR3Shop);
			request.setAttribute("entityList", entityList);
		} else {
			return null;
		}

		dynaBean.set("c_id", id);

		return new ActionForward("/spgl/EcCust/list-customer.jsp");
	}

	public ActionForward viewEcUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			return this.list(mapping, form, request, response);
		}

		EcCustRelUser eu = new EcCustRelUser();
		eu.setCust_id(Long.valueOf(id));
		List<EcCustRelUser> entityList = super.getFacade().getEcCustRelUserService().getEcCustRelUserList(eu);
		if (entityList != null && entityList.size() > 0) {
			for (EcCustRelUser ecCustRelUser : entityList) {
				EcUser es = new EcUser();
				es.setId(ecCustRelUser.getUser_id());
				es = super.getFacade().getEcUserService().getEcUser(es);
				if (es != null) {
					ecCustRelUser.getMap().put("user_name", es.getUser_name());
					ecCustRelUser.getMap().put("link_phone", es.getLink_phone());

					EcGroup eg = new EcGroup();
					eg.setId(es.getDept_id());
					eg = super.getFacade().getEcGroupService().getEcGroup(eg);
					if (eg != null) {
						ecCustRelUser.getMap().put("group_name", eg.getGroup_name());
					}
				}

			}
		}
		request.setAttribute("entityList", entityList);

		return new ActionForward("/spgl/EcCust/list_user_for_show.jsp");
	}
	
	public ActionForward ajaxJson(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String group_id = (String) dynaBean.get("group_id"); 
		List<HashMap> listJson = new ArrayList<HashMap>();
		if (StringUtils.isNotBlank(group_id)  ) { 
			EcCust ecCust = new EcCust();			
			ecCust.setGroup_id(new Long(group_id));
			List<EcCust> list = super.getFacade().getEcCustService().getEcCustList(ecCust);
			for(EcCust c :list){
				HashMap map = new HashMap();
				map.put("c_name", c.getCust_name());
				map.put("c_id", c.getId());  
				listJson.add(map);
			}
		}
		super.renderJson(response, JSON.toJSONString(listJson));
		return null;
	}
}
