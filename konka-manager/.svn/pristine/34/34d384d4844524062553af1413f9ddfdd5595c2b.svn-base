package com.ebiz.mmt.web.struts.manager.spgl;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EcCust;
import com.ebiz.mmt.domain.EcGroup;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.mmt.web.util.ExcelUtil;
import com.ebiz.mmt.web.util.ShortUUID;
import com.ebiz.ssi.web.struts.bean.Pager;

public class EcUserNewAction extends BaseAction {

	private final static String do_with_fgs_roleids = "60,188";

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		Pager pager = (Pager) dynaBean.get("pager");
		String user_name_like = (String) dynaBean.get("user_name_like");
		String is_del = (String) dynaBean.get("is_del");
		String excel_all = (String) dynaBean.get("excel_all");
		String is_act = (String) dynaBean.get("is_act");
		String user_type = (String) dynaBean.get("user_type");
		String add_time_start = (String) dynaBean.get("add_time_start");
		String add_time_end = (String) dynaBean.get("add_time_end");
		String cust_code_like = (String) dynaBean.get("cust_code_like");
		String plat_sys = (String) dynaBean.get("plat_sys");
		String dept_id = (String) dynaBean.get("dept_id");
		String level1 = (String) dynaBean.get("level1");
		String user_no_like = (String) dynaBean.get("user_no_like");
		String chargeman_like = (String) dynaBean.get("chargeman_like");

		PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		request.setAttribute("peRoleUser", peRoleUser);

		EcUser entity = new EcUser();

		if (StringUtils.isNotBlank(user_type)) {
			entity.setUser_type(Integer.valueOf(user_type));
		} else {
			entity.setUser_type(2);
		}
		if (StringUtils.isNotBlank(cust_code_like)) {
			entity.getMap().put("cust_code_like", cust_code_like);
		}
		if (StringUtils.isNotBlank(chargeman_like)) {
			entity.getMap().put("chargeman_like", chargeman_like);
		}
		if (StringUtils.isNotBlank(user_no_like)) {
			entity.getMap().put("user_no_like", user_no_like);
		}
		if (StringUtils.isNotBlank(level1)) {
			entity.getMap().put("level1", level1);
		}

		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
		}
		if (StringUtils.isNotBlank(plat_sys)) {
			entity.setPlat_sys(Integer.valueOf(plat_sys));
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
		if (StringUtils.isNotBlank(is_act)) {
			entity.setIs_act(Integer.valueOf(is_act));
		}

		if (StringUtils.isNotBlank(is_del)) {
			entity.setIs_del(Integer.valueOf(is_del));
		} else {
			entity.setIs_del(0);
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
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 178 || pu.getRole_id().intValue() == 140317
						|| pu.getRole_id().intValue() == 1001) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
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
				dynaBean.set("group_id", StringUtils.join(ids, ","));
				dynaBean.set("is_binding", true);
				entity.getMap().put("is_fgs", ids);
			} else {
				entity.getMap().put("is_fgs", new String[] { "999999" });
			}
			entity.setPlat_sys(1);
		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('你没有权限查看价格！');history.back();}");
			return null;
		}

		entity.getMap().put("add_date_gt", "2015-01-09");

		entity.setIs_epp_fgs(0);// 非EPP分公司会员
		Long recordCount = super.getFacade().getEcUserService().getEcUserNewCount(entity);
		pager.init(recordCount, 10, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcUser> entityList = super.getFacade().getEcUserService().getEcUserNewPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			if (recordCount.intValue() > 5000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
						+ "！');history.back();");
				return null;
			}
			entity.getRow().setCount(recordCount.intValue());
			List<EcUser> entityList1 = super.getFacade().getEcUserService().getEcUserNewPaginatedList(entity);
			dynaBean.set("excel_all", excel_all);
			request.setAttribute("allList", entityList1);
		}

		request.setAttribute("requestStr", super.serialize(request, "is_del"));
		return mapping.findForward("list");
	}

	public ActionForward excel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		String user_name_like = (String) dynaBean.get("user_name_like");
		String chargeman_like = (String) dynaBean.get("chargeman_like");
		String is_del = (String) dynaBean.get("is_del");
		String is_act = (String) dynaBean.get("is_act");
		String user_type = (String) dynaBean.get("user_type");
		String add_time_start = (String) dynaBean.get("add_time_start");
		String add_time_end = (String) dynaBean.get("add_time_end");
		String cust_code_like = (String) dynaBean.get("cust_code_like");
		String plat_sys = (String) dynaBean.get("plat_sys");
		String dept_id = (String) dynaBean.get("dept_id");
		String level1 = (String) dynaBean.get("level1");

		PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		request.setAttribute("peRoleUser", peRoleUser);

		EcUser entity = new EcUser();

		if (StringUtils.isNotBlank(plat_sys)) {
			entity.setPlat_sys(Integer.valueOf(plat_sys));
		}
		if (StringUtils.isNotBlank(user_type)) {
			entity.setUser_type(Integer.valueOf(user_type));
		} else {
			entity.setUser_type(2);
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
		}
		if (StringUtils.isNotBlank(chargeman_like)) {
			entity.getMap().put("chargeman_like", chargeman_like);
		}
		if (StringUtils.isNotBlank(user_name_like)) {
			entity.getMap().put("user_name_like", user_name_like);
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
		}
		if (StringUtils.isNotBlank(cust_code_like)) {
			entity.getMap().put("cust_code_like", cust_code_like);
		}
		if (StringUtils.isNotBlank(level1)) {
			entity.getMap().put("level1", level1);
		}
		if (StringUtils.isNotBlank(is_act)) {
			entity.setIs_act(Integer.valueOf(is_act));
		}
		if (StringUtils.isNotBlank(add_time_start)) {
			entity.getMap().put("add_time_start", add_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_time_end)) {
			entity.getMap().put("add_time_end", add_time_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(is_del)) {
			entity.setIs_del(Integer.valueOf(is_del));
		} else {
			entity.setIs_del(0);
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
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 178 || pu.getRole_id().intValue() == 140317
						|| pu.getRole_id().intValue() == 1001) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
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
				dynaBean.set("group_id", StringUtils.join(ids, ","));
				dynaBean.set("is_binding", true);
				entity.getMap().put("is_fgs", ids);
			}
			entity.setPlat_sys(1);
		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('你没有权限查看价格！');history.back();}");
			return null;
		}

		entity.getMap().put("add_date_gt", "2015-01-09");
		entity.setIs_epp_fgs(0);// 非EPP分公司会员

		Long recordCount = super.getFacade().getEcUserService().getEcUserNewCount(entity);
		entity.getRow().setFirst(new Integer(0));
		entity.getRow().setCount(new Integer(recordCount.intValue()));

		List<EcUser> entityList = super.getFacade().getEcUserService().getEcUserNewPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		ExcelUtil e = new ExcelUtil();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		int r = 0;
		e.setWorkbook(workbook);
		e.setSheet(sheet);
		e.createRow(r);
		e.setCell(0, "序号");
		e.setCell(1, "登录名");
		e.setCell(2, "手机");
		e.setCell(3, "客户名称");
		e.setCell(4, "客户编码");
		e.setCell(5, "所属组织");
		e.setCell(6, "总部/分公司");
		e.setCell(7, "审核状态");
		e.setCell(8, "审核时间");
		e.setCell(9, "审核人");
		e.setCell(10, "会员级别");
		e.setCell(11, "上级会员");
		for (EcUser pds : entityList) {
			r++;
			e.createRow(r);
			e.setCell(0, r);
			e.setCell(1, pds.getUser_name() == null ? "" : pds.getUser_name());
			e.setCell(2, pds.getLink_phone() == null ? "" : pds.getLink_phone());
			String cust_name = "";
			if (null != pds.getMap().get("cust_name")) {
				cust_name = (String) pds.getMap().get("cust_name");
			}
			e.setCell(3, cust_name);
			String cust_code = "";
			if (null != pds.getMap().get("cust_code")) {
				cust_code = (String) pds.getMap().get("cust_code");
			}
			e.setCell(4, cust_code);
			String group_name = "";
			if (null != pds.getMap().get("group_name")) {
				group_name = (String) pds.getMap().get("group_name");
			}
			e.setCell(5, group_name);

			String plat_sys1 = "";
			if (pds.getPlat_sys().intValue() == 0) {
				plat_sys1 = "总部";
			} else if (pds.getPlat_sys().intValue() == 1) {
				plat_sys1 = "分公司";
			}
			e.setCell(6, plat_sys1);

			String state = "";
			if (null != pds.getIs_act()) {
				if (pds.getIs_act() == 0) {
					state = "审核通过";
				} else if (pds.getIs_act() == 1) {
					state = "待完善资料";
				} else if (pds.getIs_act() == 2) {
					state = "待审核";
				} else if (pds.getIs_act() == 3) {
					state = "审核不通过";
				}
			}
			e.setCell(7, state);
			if (pds.getAudit_date() != null) {
				e.setCell(8, new SimpleDateFormat("yyyy-MM-dd").format(pds.getAudit_date()));
			} else {
				e.setCell(8, "");
			}

			String chargeman = "";
			if (null != pds.getChargeman()) {
				chargeman = pds.getChargeman();
			}
			e.setCell(9, chargeman);

			String level = "";
			if (null != pds.getMap().get("user_level")) {
				level = ((Integer) pds.getMap().get("user_level")).toString();
			}
			e.setCell(10, level);
			e.setCell(11, pds.getLink_user_name() == null ? "" : pds.getLink_user_name());
		}

		// 输出
		response.setHeader("Content-disposition", "attachment; filename=noname.xls");// 设定输出文件头
		response.setContentType("application/vnd.ms-excel");// 定义输出类型
		OutputStream out = response.getOutputStream();
		e.exportXLS(out);
		return null;
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		DynaBean dynaBean = (DynaBean) form;
		super.setNaviStringToRequestScope(form, request);

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

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
			request.setAttribute("is_admin", "1");
			List<EcGroup> groupList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("groupList", groupList);
		} else if (fgs) {
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			eg.setLink_dept_id(fgs_dept.getDept_id());
			List<EcGroup> groupList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("groupList", groupList);
		}

		dynaBean.set("is_allowed", "1");
		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");

		if (!GenericValidator.isLong(user_id)) {
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
			request.setAttribute("is_admin", "1");
			List<EcGroup> groupList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("groupList", groupList);
		} else if (fgs) {
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			eg.setLink_dept_id(fgs_dept.getDept_id());
			List<EcGroup> groupList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("groupList", groupList);
		}

		// 获取选择的修改用户 企业信息
		EcUser entity = new EcUser();
		entity.setId(Long.valueOf(user_id));
		entity = super.getFacade().getEcUserService().getEcUser(entity);

		if (null == entity) {
			return this.list(mapping, form, request, response);
		}

		super.copyProperties(form, entity);

		if (null != entity.getCust_id()) {
			EcCust ec = new EcCust();
			ec.setId(entity.getCust_id());
			ec = super.getFacade().getEcCustService().getEcCust(ec);

			if (ec != null) {
				dynaBean.set("c_name", ec.getCust_code());
				dynaBean.set("group_id", ec.getGroup_id());
				dynaBean.set("cust_name", ec.getCust_name());
			}
		}

		entity.setQueryString(super.serialize(request, "id", "mod_id", "tree_param"));
		if (entity.getP_index() != null && entity.getP_index() != -1) {
			String p_index = entity.getP_index().toString();
			if (StringUtils.isNotBlank(p_index)) {
				if (!p_index.endsWith("00")) {
					if (p_index.length() == 6) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "00");
						request.setAttribute("country", p_index);
					} else if (p_index.length() == 8) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "00");
						request.setAttribute("country", StringUtils.substring(p_index, 0, 6));
						request.setAttribute("town", p_index);
					}
				} else if (p_index.endsWith("0000")) {
					if (p_index.length() == 6) {
						request.setAttribute("province", p_index);
					} else if (p_index.length() == 8) {
						request.setAttribute("province", p_index);
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "00");
					}
				} else if (p_index.endsWith("00")) {
					if (p_index.length() == 6) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
						request.setAttribute("city", p_index);
					} else if (p_index.length() == 8) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "000000");
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "0000");
						request.setAttribute("country", StringUtils.substring(p_index, 0, 6) + "00");
					}
				}
			}
		}

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");

		if (!GenericValidator.isLong(user_id)) {
			return this.list(mapping, form, request, response);
		}

		EcUser entity = new EcUser();
		entity.setId(Long.valueOf(user_id));
		entity = super.getFacade().getEcUserService().getEcUser(entity);

		if (null == entity) {
			return this.list(mapping, form, request, response);
		}

		super.copyProperties(form, entity);

		PeProdUser pp = new PeProdUser();
		if (null != entity.getC_id()) {
			pp.setId(entity.getC_id());
			pp = super.getFacade().getPeProdUserService().getPeProdUser(pp);
			if (pp != null)
				request.setAttribute("real_name", pp.getReal_name());
		}

		if (null != entity.getDept_id()) {
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(entity.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			request.setAttribute("dept_name", konkaDept.getDept_name());
		}

		if (null != entity.getP_index()) {
			BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
			baseProvinceListFour.setP_index(entity.getP_index());
			baseProvinceListFour = super.getFacade().getBaseProvinceListFourService()
					.getBaseProvinceListFour(baseProvinceListFour);
			if (null != baseProvinceListFour) {
				String p_index_name = baseProvinceListFour.getFull_name();
				request.setAttribute("p_index_name", p_index_name);
			}
		}

		return mapping.findForward("view");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");
		String cust_id = (String) dynaBean.get("cust_id");
		String group_id = (String) dynaBean.get("group_id");
		String link_code = (String) dynaBean.get("link_code");

		// 获取登录用户 企业信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		EcUser entity = new EcUser();
		super.copyProperties(entity, form);

		if (entity.getIs_xx_user() == null) {
			entity.setIs_xx_user(0);
		}

		if (StringUtils.isNotBlank(group_id)) {
			entity.setDept_id(Long.valueOf(group_id));
		}

		// 人员居住地设定
		if (GenericValidator.isLong(province) && !GenericValidator.isLong(city)) {
			entity.setP_index(Long.valueOf(province));
		}
		if (GenericValidator.isLong(province) && GenericValidator.isLong(city) && !GenericValidator.isLong(country)) {
			entity.setP_index(Long.valueOf(city));
		}
		if (GenericValidator.isLong(province) && GenericValidator.isLong(city) && GenericValidator.isLong(country)
				&& !GenericValidator.isLong(town)) {
			entity.setP_index(Long.valueOf(country));
		}
		if (GenericValidator.isLong(province) && GenericValidator.isLong(city) && GenericValidator.isLong(country)
				&& GenericValidator.isLong(town)) {
			entity.setP_index(Long.valueOf(town));
		}
		
		//生成关联码 如果有上级会员 则取上级会员的关联码
		if (!StringUtils.isNotBlank(link_code)) {
			if(StringUtils.isNotBlank(entity.getLink_user_name())){
				EcUser linkUser = new EcUser();
				linkUser.setUser_name(entity.getLink_user_name());
				linkUser =super.getFacade().getEcUserService().getEcUser(linkUser);
				if(linkUser!=null){
					entity.setLink_code(linkUser.getLink_code());
				}
			}else{
				entity.setLink_code(ShortUUID.generateShortUuid()) ;//
			}
		}
		
		if (!GenericValidator.isLong(user_id)) {// 创建用户
			entity.setAdd_e_user_id(peProdUser.getId());
			EcUser prodUser = new EcUser();
			prodUser.setUser_name(entity.getUser_name());
			// prodUser.setIs_del(0);

			Long count = super.getFacade().getEcUserService().getEcUserCount(prodUser);
			if (count != 0) {
				super.renderJavaScript(response, "window.onload=function(){alert('用户名\"" + entity.getUser_name()
						+ "\"与数据库中重复，请重新填写 ');history.back();}");
				return null;
			}

			// EcUser ec = new EcUser();
			// ec.setCust_id(Long.valueOf(cust_id));
			// Long count2 =
			// super.getFacade().getEcUserService().getEcUserCount(ec);
			// if (count2 > 0) {
			// super.renderJavaScript(response,
			// "window.onload=function(){alert('该客户已被绑定，请重新选择 ');history.back();}");
			// return null;
			// }

			if (StringUtils.isNotBlank(cust_id)) {
				entity.getMap().put("touch", cust_id);
			}

			// 用户编码
			String user_no = "";
			Long no = super.getFacade().getEcUserService().getEcUserNo(entity);
			user_no = "KK" + no.toString();
			entity.setUser_no(user_no);

			entity.setCard_no(user_no);

			DESPlus des = new DESPlus();
			entity.setPass_word(des.encrypt("1"));
			entity.setPay_pwd(null);
			super.getFacade().getEcUserService().createEcUser(entity);
			super.saveMessage(request, "entity.inserted");
		} else {
			entity.setId(Long.valueOf(user_id));

			// EcUser ec = new EcUser();
			// ec.setId(Long.valueOf(user_id));
			// ec = super.getFacade().getEcUserService().getEcUser(ec);
			// if (ec != null) {
			// if (ec.getCust_id() != null) {
			// if (!ec.getCust_id().toString().equals(cust_id)) {
			// EcUser ec2 = new EcUser();
			// ec2.setCust_id(Long.valueOf(cust_id));
			// Long count2 =
			// super.getFacade().getEcUserService().getEcUserCount(ec2);
			// if (count2 > 0) {
			// super.renderJavaScript(response,
			// "window.onload=function(){alert('该客户已被绑定，请重新选择 ');history.back();}");
			// return null;
			// }
			// }
			// }
			// }

			if (StringUtils.isNotBlank(cust_id)) {
				entity.getMap().put("touch", cust_id);
			}

			super.getFacade().getEcUserService().modifyEcUser(entity);
			// super.getFacade().getEcUserService().modifyEcUserWithMultiRoleUser(entity);
			super.saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(user_id)) {
			saveError(request, "errors.long", new String[] { user_id });
			return mapping.findForward("list");
		}

		EcUser entity = new EcUser();
		entity.setId(Long.valueOf(user_id));
		entity.setIs_del(1);
		super.getFacade().getEcUserService().modifyEcUser(entity);

		saveMessage(request, "konka.close.success");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "user_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward initPassword(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String tree_param = (String) dynaBean.get("tree_param");

		if (!GenericValidator.isLong(user_id)) {
			saveError(request, "errors.long", new String[] { user_id });
			return mapping.findForward("list");
		}

		EcUser entity = new EcUser();
		entity.setId(Long.valueOf(user_id));

		DESPlus des = new DESPlus();
		entity.setPass_word(des.encrypt("888888")); // 默认密码

		super.getFacade().getEcUserService().modifyEcUser(entity);

		saveMessage(request, "password.init.success");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("tree_param=" + tree_param);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "user_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward initPassword2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String tree_param = (String) dynaBean.get("tree_param");

		if (!GenericValidator.isLong(user_id)) {
			saveError(request, "errors.long", new String[] { user_id });
			return mapping.findForward("list");
		}

		EcUser entity = new EcUser();
		entity.setId(Long.valueOf(user_id));

		DESPlus des = new DESPlus();
		entity.setPay_pwd(des.encrypt("666666")); // 默认密码

		super.getFacade().getEcUserService().modifyEcUser(entity);

		saveMessage(request, "password.init.success2");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("tree_param=" + tree_param);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "user_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward validateName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		StringBuffer oper = new StringBuffer("{\"result\":");
		String user_name = (String) dynaBean.get("user_name");
		EcUser entity = new EcUser();
		entity.setUser_name(user_name);
		// entity.setIs_del(0);
		Long count = super.getFacade().getEcUserService().getEcUserCount(entity);
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

		Pager pager = (Pager) dynaBean.get("pager");
		String group_id = (String) dynaBean.get("group_id");
		String cust_name_like = (String) dynaBean.get("cust_name_like");
		String cust_code_like = (String) dynaBean.get("cust_code_like");
		String r3_code = (String) dynaBean.get("r3_code");

		if (!GenericValidator.isLong(group_id)) {
			return null;
		}

		EcCust entity = new EcCust();
		if (StringUtils.isNotBlank(cust_name_like)) {
			entity.getMap().put("cust_name_like", cust_name_like);
		}
		if (StringUtils.isNotBlank(cust_code_like)) {
			entity.getMap().put("cust_code_like", cust_code_like);
		}
		if (StringUtils.isNotBlank(r3_code)) {
			entity.setR3_code(r3_code);
		}

		entity.setGroup_id(Long.valueOf(group_id));

		Long recordCount = super.getFacade().getEcCustService().getEcCustForDetailsCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcCust> entityList = super.getFacade().getEcCustService().getEcCustForDetailsPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return new ActionForward("/../manager/spgl/EcUserNew/list-customer.jsp");
	}

	public ActionForward audit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");

		if (!GenericValidator.isLong(user_id)) {
			return this.list(mapping, form, request, response);
		}

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		// 获取登录用户 企业信息
		EcUser SxPeProdUser = new EcUser();
		SxPeProdUser.setIs_del(0);
		SxPeProdUser.setUser_name(peProdUser.getUser_name());
		SxPeProdUser = super.getFacade().getEcUserService().getEcUser(SxPeProdUser);

		// 获取选择的修改用户 企业信息
		EcUser entity = new EcUser();
		entity.setId(Long.valueOf(user_id));
		entity = super.getFacade().getEcUserService().getEcUser(entity);

		if (null == entity) {
			return this.list(mapping, form, request, response);
		}

		if (null != entity.getDept_id()) {
			KonkaDept dept = new KonkaDept();
			dept.setDept_id(entity.getDept_id());
			List<KonkaDept> deptList = super.getFacade().getKonkaDeptService().getKonkaDeptByDeptId(dept);

			if (null != deptList) {
				for (int i = 1; i < deptList.size(); i++) {
					KonkaDept d = deptList.get(i);
					dynaBean.set("l" + (i + 2) + "_dept_id", String.valueOf(d.getDept_id()));
				}
			}
		}

		super.copyProperties(form, entity);

		if (null != entity.getCust_id()) {
			EcCust ec = new EcCust();
			ec.setId(entity.getCust_id());
			ec = super.getFacade().getEcCustService().getEcCust(ec);

			if (ec != null) {
				dynaBean.set("c_name", ec.getCust_code());
				dynaBean.set("group_id", ec.getGroup_id());
			}
		}

		entity.setQueryString(super.serialize(request, "id", "mod_id", "tree_param"));
		if (entity.getP_index() != null && entity.getP_index() != -1) {
			String p_index = entity.getP_index().toString();
			if (StringUtils.isNotBlank(p_index)) {
				if (!p_index.endsWith("00")) {
					if (p_index.length() == 6) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "00");
						request.setAttribute("country", p_index);
					} else if (p_index.length() == 8) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "00");
						request.setAttribute("country", StringUtils.substring(p_index, 0, 6));
						request.setAttribute("town", p_index);
					}
				} else if (p_index.endsWith("0000")) {
					if (p_index.length() == 6) {
						request.setAttribute("province", p_index);
					} else if (p_index.length() == 8) {
						request.setAttribute("province", p_index);
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "00");
					}
				} else if (p_index.endsWith("00")) {
					if (p_index.length() == 6) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
						request.setAttribute("city", p_index);
					} else if (p_index.length() == 8) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "000000");
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "0000");
						request.setAttribute("country", StringUtils.substring(p_index, 0, 6) + "00");
					}
				}
			}
		}

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		return new ActionForward("/../manager/spgl/EcUserNew/audit.jsp");
	}

	public ActionForward save1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String returnUrl = (String) dynaBean.get("returnUrl");
		String is_act = (String) dynaBean.get("is_act");

		// 获取登录用户 企业信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		EcUser ee = new EcUser();
		ee.setId(Long.valueOf(user_id));
		ee = super.getFacade().getEcUserService().getEcUser(ee);

		EcUser entity = new EcUser();
		super.copyProperties(entity, form);

		if (entity.getIs_xx_user() == null) {
			entity.setIs_xx_user(0);
		}

		if (!GenericValidator.isLong(user_id)) {// 创建用户
			entity.setAdd_e_user_id(peProdUser.getId());
			EcUser prodUser = new EcUser();
			prodUser.setUser_name(entity.getUser_name());
			prodUser.setIs_del(0);
			Long count = super.getFacade().getEcUserService().getEcUserCount(prodUser);
			if (count != 0) {
				super.renderJavaScript(response, "window.onload=function(){alert('用户名\"" + entity.getUser_name()
						+ "\"与数据库中重复，请重新填写 ');history.back();}");
				return null;
			}
			DESPlus des = new DESPlus();
			entity.setPass_word(des.encrypt(entity.getPass_word()));
			super.getFacade().getEcUserService().createEcUser(entity);
			super.saveMessage(request, "entity.inserted");
		} else {
			entity.setId(Long.valueOf(user_id));
			if (StringUtils.isNotBlank(is_act)) {
				entity.setIs_act(Integer.valueOf(is_act));
			}
			if (StringUtils.isNotBlank(ee.getEmail())) {
				entity.setEmail(ee.getEmail());
			}
			entity.setAudit_date(new Date());// 审核时间
			entity.setChargeman(peProdUser.getReal_name());// 审核人姓名

			String msg = "";
			Properties props = new Properties();
			try {
				props.load(getClass().getResourceAsStream("/mail.properties"));
			} catch (IOException e2) {
				msg = " 对不起， 发生错误  ";
			}
			if (!"".equals(msg)) {
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "'); history.back();}");
				return null;
			}

			String mailForm = "kaixinmao@konka.com";// props.getProperty("mail.username");//;//
			// 发件人邮箱
			String senderName = "开心猫";
			JavaMailSenderImpl mailSender = super.getMailSender();
			mailSender.setUsername(mailForm);
			mailSender.setPassword("kaixinmao123");
			entity.setReal_name(ee.getReal_name());
			super.getFacade().getEcUserService()
					.createEcUserAndSendEmail5(entity, mailSender, mailForm, senderName, request);
			super.saveMessage(request, "entity.updated");
		}

		if (StringUtils.isNotBlank(returnUrl)) {
			response.sendRedirect(URLDecoder.decode(returnUrl, Constants.SYS_ENCODING));
			return null;
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward reStart(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(user_id)) {
			saveError(request, "errors.long", new String[] { user_id });
			return mapping.findForward("list");
		}

		EcUser entity = new EcUser();
		entity.setId(Long.valueOf(user_id));
		entity.setIs_del(0);
		super.getFacade().getEcUserService().modifyEcUser(entity);

		saveMessage(request, "konka.restart.success");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "user_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
}
