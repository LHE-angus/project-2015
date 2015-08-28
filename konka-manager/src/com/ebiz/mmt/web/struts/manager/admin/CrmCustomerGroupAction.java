package com.ebiz.mmt.web.struts.manager.admin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.CrmCustomerGroup;
import com.ebiz.mmt.domain.CrmCustomerGroupDetails;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.org.apache.commons.lang3.StringUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

public class CrmCustomerGroupAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String groupcode = (String) dynaBean.get("groupcode");
		String groupname = (String) dynaBean.get("groupname");
		String dept_id = (String) dynaBean.get("dept_id");
		String modid = (String) dynaBean.get("mod_id");
		String isdel = (String) dynaBean.get("isdel");
		String begindate = (String) dynaBean.get("begindate");
		String enddate = (String) dynaBean.get("enddate");
		String customername = (String) dynaBean.get("customername");

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		CrmCustomerGroup h = new CrmCustomerGroup();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (groupcode != null && !"".equals(groupcode)) {
			h.setGroupcode(groupcode.trim());
		}
		if (groupname != null && !"".equals(groupname)) {
			h.setGroupname(groupname.trim());
		}

		if (StringUtils.isNotBlank(dept_id)) {
			h.setDeptid(Long.valueOf(dept_id));
		} else {
			if (userInfo.getDept_id() != null && userInfo.getDept_id() != 0) {
				h.setDeptid(Long.valueOf(userInfo.getDept_id()));
			}
		}
		if (isdel != null && !"".equals(isdel)) {
			h.setIsdel(Integer.valueOf(isdel));
		}
		if (customername != null && !"".equals(customername)) {
			h.getMap().put("line_customername", customername);
		}

		Long recordCount = super.getFacade().getCrmCustomerGroupService().getCrmCustomerGroupCount(h);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		h.getRow().setFirst(pager.getFirstRow());
		h.getRow().setCount(pager.getRowCount());

		List<CrmCustomerGroup> entityList = super.getFacade().getCrmCustomerGroupService()
				.getCrmCustomerGroupPaginatedList(h);

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		// 多媒体事业本部 0 系统管理员
		if (userInfo.getDept_id() != 0) {
			konkaDept.setDept_id(userInfo.getDept_id());
		}
		konkaDept.getMap().put("order_by_dept_name", true);
		List<KonkaDept> deptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		request.setAttribute("deptList", deptList);

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");

	}

	// 新增显示form表单
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		// String deptid = (String) dynaBean.get("deptid");
		// String mod_id = (String) dynaBean.get("mod_id");
		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		Pager pager = (Pager) dynaBean.get("pager");
		pager.init(0l, 10, "1");
		dynaBean.set("pager", pager);
		CrmCustomerGroup crmCustomerGroup = new CrmCustomerGroup();
		String s = super.getFacade().getCrmCustomerGroupService().getGroupCode();
		crmCustomerGroup.setGroupcode(s);
		super.copyProperties(form, crmCustomerGroup);// important

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		// 多媒体事业本部 0 系统管理员
		if (userInfo.getDept_id() != 0) {
			konkaDept.setDept_id(userInfo.getDept_id());
		}
		konkaDept.getMap().put("order_by_dept_name", true);
		List<KonkaDept> deptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		request.setAttribute("deptList", deptList);

		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		Pager pager = (Pager) dynaBean.get("pager");
		String id = (String) dynaBean.get("id");// headerId
		CrmCustomerGroup crmCustomerGroup = new CrmCustomerGroup();
		if (!GenericValidator.isLong(id)) {
			String msg = super.getMessage(request, "errors.param");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		crmCustomerGroup.setId(Long.valueOf(id));
		// get header
		crmCustomerGroup = super.getFacade().getCrmCustomerGroupService().getCrmCustomerGroup(crmCustomerGroup);
		if (null == crmCustomerGroup) {
			String msg = super.getMessage(request, "entity.empty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(crmCustomerGroup.getDeptid());
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		crmCustomerGroup.getMap().put("dept_name", konkaDept.getDept_name());

		super.copyProperties(form, crmCustomerGroup);// important

		// get linescreateCrmCustomerGroupDetails
		CrmCustomerGroupDetails d = new CrmCustomerGroupDetails();
		d.setHeadid(Long.valueOf(id));

		Long recordCount = super.getFacade().getCrmCustomerGroupDetailsService().getCrmCustomerGroupDetailsCount(d);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		d.getRow().setFirst(pager.getFirstRow());
		d.getRow().setCount(pager.getRowCount());
		List<CrmCustomerGroupDetails> dList = super.getFacade().getCrmCustomerGroupDetailsService()
				.getCrmCustomerGroupDetailsPaginatedList(d);
		request.setAttribute("ctmList", dList);

		return mapping.findForward("view");

	}

	/**
	 * 修改数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		Pager pager = (Pager) dynaBean.get("pager");
		String id = (String) dynaBean.get("id");// headerId
		String deptid = (String) dynaBean.get("deptid");
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		CrmCustomerGroup crmCustomerGroup = new CrmCustomerGroup();
		if (!GenericValidator.isLong(id)) {
			String msg = super.getMessage(request, "errors.param");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		crmCustomerGroup.setId(Long.valueOf(id));
		// get header
		crmCustomerGroup = super.getFacade().getCrmCustomerGroupService().getCrmCustomerGroup(crmCustomerGroup);
		if (null == crmCustomerGroup) {
			String msg = super.getMessage(request, "entity.empty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		super.copyProperties(form, crmCustomerGroup);// important

		// get linescreateCrmCustomerGroupDetails
		CrmCustomerGroupDetails d = new CrmCustomerGroupDetails();
		d.setHeadid(Long.valueOf(id));

		Long recordCount = super.getFacade().getCrmCustomerGroupDetailsService().getCrmCustomerGroupDetailsCount(d);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		d.getRow().setFirst(pager.getFirstRow());
		d.getRow().setCount(pager.getRowCount());
		List<CrmCustomerGroupDetails> dList = super.getFacade().getCrmCustomerGroupDetailsService()
				.getCrmCustomerGroupDetailsPaginatedList(d);
		request.setAttribute("ctmList", dList);

		// KonkaDept konkaDept = new KonkaDept();
		// konkaDept.setDept_type(3);
		// konkaDept.getMap().put("user_id_eq", userInfo.getId());
		// List<KonkaDept> deptList =
		// super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		// request.setAttribute("deptList", deptList);

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		// 多媒体事业本部 0 系统管理员
		if (userInfo.getDept_id() != 0) {
			konkaDept.setDept_id(userInfo.getDept_id());
		}
		konkaDept.getMap().put("order_by_dept_name", true);
		List<KonkaDept> deptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		request.setAttribute("deptList", deptList);

		return mapping.findForward("input");
	}

	// 启用禁用
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");// headerid
		String isdel = (String) dynaBean.get("isdel");
		String mod_id = (String) dynaBean.get("mod_id");

		CrmCustomerGroup temp = new CrmCustomerGroup();
		temp.setId(Long.valueOf(id));
		temp = super.getFacade().getCrmCustomerGroupService().getCrmCustomerGroup(temp);

		// 组名+部门是否重复
		boolean isExis = super.getFacade().getCrmCustomerGroupService()
				.isExitsGroupName(String.valueOf(temp.getDeptid()), temp.getGroupname());

		CrmCustomerGroup crmCustomerGroup = new CrmCustomerGroup();
		if (StringUtils.isNotEmpty(id)) {
			crmCustomerGroup.setId(Long.valueOf(id));
		} else {
			return mapping.findForward("list");
		}
		if (StringUtils.isNotEmpty(isdel)) {
			crmCustomerGroup.setIsdel(Integer.valueOf(isdel));
			if (Integer.valueOf(isdel) == 0) {
				if (!isExis) {
					super.getFacade().getCrmCustomerGroupService().modifyCrmCustomerGroup(crmCustomerGroup);
					saveMessage(request, "entity.updated");
				} else {
					saveDirectlyError(request, "您维护了重名的客户群组:" + temp.getGroupname());
				}
			} else {
				// 禁用开绿灯
				super.getFacade().getCrmCustomerGroupService().modifyCrmCustomerGroup(crmCustomerGroup);
				saveMessage(request, "entity.updated");
			}
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward deleteL(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String lineId = (String) dynaBean.get("line_id");
		CrmCustomerGroupDetails d = new CrmCustomerGroupDetails();
		if (StringUtils.isNotEmpty(id)) {
			d.setId(Long.valueOf(id));
		} else {
			return mapping.findForward("list");
		}
		if (StringUtils.isNotEmpty(lineId)) {
			d.setId(Long.valueOf(lineId));
		} else {
			return mapping.findForward("list");
		}

		super.getFacade().getCrmCustomerGroupDetailsService().removeCrmCustomerGroupDetails(d);
		saveMessage(request, "entity.updated");
		return this.edit(mapping, form, request, response);
	}

	/**
	 * 弹出页面查询客户页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward showGetCustomerPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String deptid = (String) dynaBean.get("deptid");// 当前操作人所在分公司id
		// 客户大类小类 如果大类为空，算小类，小类为空，算不传值
		String v_c_name = (String) dynaBean.get("v_customer_type2");
		dynaBean.set("v_customer_type2", v_c_name);

		List<KonkaCategory> konkaCategoryList = new ArrayList<KonkaCategory>();// 客户大类和小类
		KonkaCategory kc = new KonkaCategory();
		kc.setC_type(10);
		kc.setIs_del(0);
		konkaCategoryList = super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc);
		request.setAttribute("konkaCategoryList", konkaCategoryList);

		Pager pager = (Pager) dynaBean.get("pager");
		pager.init(0l, 10, "1");
		dynaBean.set("pager", pager);
		return new ActionForward("/admin/CrmCustomerGroup/getcustomer.jsp");
	}

	/**
	 * 弹出页面查询客户数据(已经在当前组的客户不查询出来)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward getCustomerList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String mod_id = (String) dynaBean.get("mod_id");
		String deptid = (String) dynaBean.get("deptid");//
		String id = (String) dynaBean.get("id");// headerid

		// 客户大类小类 如果大类为空，算小类，小类为空，算不传值
		String v_c_name = (String) dynaBean.get("v_customer_type2");
		dynaBean.set("v_customer_type2", v_c_name);
		String customerType = "";
		if (StringUtils.isNotEmpty(v_c_name)) {
			customerType = v_c_name;
		}
		List<KonkaCategory> konkaCategoryList = new ArrayList<KonkaCategory>();// 客户大类和小类
		KonkaCategory kc = new KonkaCategory();
		kc.setC_type(10);
		kc.setIs_del(0);
		konkaCategoryList = super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc);
		request.setAttribute("konkaCategoryList", konkaCategoryList);

		String customercode = (String) dynaBean.get("customercode");
		String customername = (String) dynaBean.get("customername");

		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		// get ctmList

		KonkaR3Shop t = new KonkaR3Shop();
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		// 一个人拥有多个角色
		List<PeRoleUser> peRoleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_le_29 = false; // 是否是管理人员
		boolean role_id_ge_30_le_59 = false; // 是否是分公司人员
		boolean role_id_30_or_34 = false; // 分公司管理员或者分公司总经理
		boolean role_id_ge_60 = false; // 是否是业务员

		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() <= 29)
				role_id_le_29 = true;
			if (peRoleUser.getRole_id() == 30 || peRoleUser.getRole_id() == 34)
				role_id_30_or_34 = true;
			if (peRoleUser.getRole_id() >= 30 && peRoleUser.getRole_id() <= 59)
				role_id_ge_30_le_59 = true;
			if (peRoleUser.getRole_id() == 60)
				role_id_ge_60 = true;
		}

		// 分公司层面,管理员查看全部,其它角色通过菜单控制
		// if (role_id_30_or_34 || role_id_ge_30_le_59) {
		KonkaDept kp = new KonkaDept();
		kp.setDept_id(Long.valueOf(deptid));
		kp = super.getFacade().getKonkaDeptService().getKonkaDept(kp);
		t.setBranch_area_name_2(kp.getDept_sn()); // KF47 ...
		// }

		t.setCustomer_type(customerType);
		t.setIs_del(0l);
		t.getMap().put("only_fgs_customer", "true");
		t.getMap().put("not_in_headerId", id);// 过滤已经在当前组的客户
		t.getMap().put("code_like", customercode);
		t.getMap().put("customer_name_like", customername);
		Long recordCount = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopCount(t);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		t.getRow().setFirst(pager.getFirstRow());
		t.getRow().setCount(pager.getRowCount());
		List<KonkaR3Shop> entityList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopPaginatedList(t);
		List<KonkaR3Shop> ctmList = new ArrayList<KonkaR3Shop>();
		for (KonkaR3Shop s : entityList) {
			KonkaR3Shop ks = new KonkaR3Shop();
			ks.setR3_code(s.getR3_code());
			ks.setCustomer_name(s.getCustomer_name());
			ks.setBranch_area_name_2(s.getBranch_area_name_2());// KF47
			ks.setBranch_area_name(s.getBranch_area_name());// 深圳
			ks.setId(s.getId());
			ctmList.add(ks);
		}
		entityList = null;
		request.setAttribute("ctmList", ctmList);
		return new ActionForward("/admin/CrmCustomerGroup/getcustomer.jsp");
	}

	/**
	 * 保存头表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String groupname = (String) dynaBean.get("groupname");

		HttpSession session = request.getSession();
		PeProdUser userInfo = new PeProdUser();
		userInfo = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		CrmCustomerGroup entity = new CrmCustomerGroup();
		super.copyProperties(entity, form);
		entity.setGroupname(groupname.trim());

		// 组名+部门是否重复
		boolean isExis = super.getFacade().getCrmCustomerGroupService()
				.isExitsGroupName(String.valueOf(entity.getDeptid()), entity.getGroupname());
		// do insert
		if (entity.getId() == null || "".equals(String.valueOf(entity.getId()))) {
			// 存在
			if (isExis) {
				saveMessage(request, "entity.exists", entity.getGroupname());
			} else {
				entity.setCreated_by(userInfo.getId());
				entity.setCreated_date(new Date());
				entity.setIsdel(0);
				this.getFacade().getCrmCustomerGroupService().createCrmCustomerGroup(entity);
				saveMessage(request, "entity.inserted");
			}
		} else {// do update
			// 存在
			if (isExis) {
				// 有可能是自家存在,也有可能是和其它数据重复,那么和其它数据重复,不允许更新,与自身重复可以更新
				List<CrmCustomerGroup> eList = new ArrayList<CrmCustomerGroup>();
				List<Long> ids = new ArrayList<Long>();
				CrmCustomerGroup temp = new CrmCustomerGroup();
				temp.setDeptid(entity.getDeptid());
				temp.setGroupname(entity.getGroupname());
				// eList.size()>0 always true
				eList = super.getFacade().getCrmCustomerGroupService().getCrmCustomerGroupList(temp);
				for (CrmCustomerGroup h1 : eList) {
					ids.add(h1.getId());
				}
				if (ids.contains(entity.getId())) {
					if (ids.size() <= 1) {
						entity.setLast_updated_by(userInfo.getId());
						entity.setLast_updated_date(new Date());
						this.getFacade().getCrmCustomerGroupService().modifyCrmCustomerGroup(entity);
						saveMessage(request, "entity.updated");
					} else {
						saveMessage(request, "entity.exists", entity.getGroupname());
					}
				} else {
					saveMessage(request, "entity.exists", entity.getGroupname());
				}

			} else {
				entity.setLast_updated_by(userInfo.getId());
				entity.setLast_updated_date(new Date());
				this.getFacade().getCrmCustomerGroupService().modifyCrmCustomerGroup(entity);
				saveMessage(request, "entity.updated");
			}
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	/**
	 * 保存行表
	 * 
	 * 要求一个客户只能在某一分公司的一个有效客户分组里面,这个非常重要
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward saveCrmCustomerDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String returnValue = (String) dynaBean.get("deptid");
		String deptid = (String) dynaBean.get("deptid");
		String ids = (String) dynaBean.get("ids"); // r3id
		String customernames = (String) dynaBean.get("customernames");// customername
		String r3codes = (String) dynaBean.get("r3codes");// r3code
		String branch_area_name_2s = (String) dynaBean.get("branch_area_name_2s");// KF47
		String branch_area_names = (String) dynaBean.get("branch_area_names");// 深圳

		if (id == null || "".equals(id)) {
			// 提示错误
			response.getWriter().write("<script>alert('保存失败,先保存头表信息');window.close();</script>");
			return null;
		}

		// 这两数组必须长度相等
		String[] array_ids = ids.split(",");
		String[] array_customernames = customernames.split(",");
		// String[] array_r3codes = r3codes.split(",");
		// String[] array_branch_area_name_2s = branch_area_name_2s.split(",");
		// String[] array_branch_area_names = branch_area_names.split(",");

		// 查现有分公司已经存在的有效客户群组并且里面存在的客户
		List<CrmCustomerGroup> list = new ArrayList<CrmCustomerGroup>();
		CrmCustomerGroup temp = new CrmCustomerGroup();
		temp.setDeptid(Long.valueOf(deptid));
		temp.setIsdel(0);
		// 某分公司所有客户分组
		list = super.getFacade().getCrmCustomerGroupService().getCrmCustomerGroupList(temp);
		// 分公司已经分组的客户ids,理论上得到这部分客户ids也不会有重复出现
		List<String> havesaveCids = new ArrayList<String>();

		// 不能保存的客户
		String cannotInsertCustomerNames = "";

		if (list.size() > 0) {
			for (CrmCustomerGroup ox : list) {
				// 不算当前分组
				if (Long.valueOf(ox.getId()) != Long.valueOf(id)) {
					CrmCustomerGroupDetails detail = new CrmCustomerGroupDetails();
					List<CrmCustomerGroupDetails> dlist = new ArrayList<CrmCustomerGroupDetails>();
					detail.setHeadid(ox.getId());
					dlist = super.getFacade().getCrmCustomerGroupDetailsService()
							.getCrmCustomerGroupDetailsList(detail);
					for (CrmCustomerGroupDetails d : dlist) {
						havesaveCids.add(String.valueOf(d.getCustomerid()));
					}
				}
			}
		}
		// 当要新增的客户id不在已经存有客户ids时,允许新增
		for (int i = 0; i < array_ids.length; i++) {
			if (strInAlist(array_ids[i], havesaveCids) == false) {
				CrmCustomerGroupDetails detail = new CrmCustomerGroupDetails();
				detail.setHeadid(Long.valueOf(id));
				detail.setCustomerid(Long.valueOf(array_ids[i]));
				detail.setCustomername(array_customernames[i]);
				detail.setDeptid(Long.valueOf(deptid));
				super.getFacade().getCrmCustomerGroupDetailsService().createCrmCustomerGroupDetails(detail);
			} else {
				cannotInsertCustomerNames += array_customernames[i];
				continue;
			}
		}

		if (cannotInsertCustomerNames.length() > 1) {
			saveDirectlyMessage(request, "部分客户保存成功:但下面客户:" + cannotInsertCustomerNames + ",可能已经存在其它分组!");
			response.getWriter().write("<script>window.returnValue='1';window.close();</script>");
			return null;
		} else {
			try {
				saveMessage(request, "entity.updated");
				response.getWriter().write("<script>window.returnValue='1';window.close();</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private boolean strInAlist(final String o, final List<String> list) {
		for (String oo : list) {
			if (o.equals(oo)) {
				return true;
			}
		}
		return false;
	}

	public ActionForward add_excel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return null;
	}

}
