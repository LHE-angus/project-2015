package com.ebiz.mmt.web.struts.manager.admin;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaStoreUserCInfo;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.UserChangeInfo;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;



public class PeProdUserAction extends BaseAction {

	private final static String do_with_fgs_roleids = "60,188,32,187,8003,8004,8005,8008,300,50,40,47,41";

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

		Pager pager = (Pager) dynaBean.get("pager");
		String job_id = (String) dynaBean.get("job_id");
		String name_like = (String) dynaBean.get("name_like");
		String is_del = (String) dynaBean.get("is_del");

		String contains_sub_depts = (String) dynaBean.get("contains_sub_depts");
		String role_id = (String) dynaBean.get("role_id");
		String excel_all = (String) dynaBean.get("excel_all");
		String excel_all_1 = (String) dynaBean.get("excel_all_1");
		String add_date_s = (String) dynaBean.get("add_date_s");
		String add_date_e = (String) dynaBean.get("add_date_e");
		String sales_type = (String) dynaBean.get("sales_type");

		String r3_job_id = (String) dynaBean.get("r3_job_id");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		PeProdUser entity = new PeProdUser();
		entity.getMap().put("is_position_dept", true);

		if (StringUtils.isNotBlank(name_like)) {
			entity.getMap().put("name_like", name_like);
		}

		if (StringUtils.isNotBlank(add_date_s))
			entity.getMap().put("add_date_s", add_date_s + " 00:00:00");
		if (StringUtils.isNotBlank(add_date_e))
			entity.getMap().put("add_date_e", add_date_e + " 23:59:59");

		String l3_dept_id = (String) dynaBean.get("l3_dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String _dept_id = null;
		if (StringUtils.isNotBlank(l5_dept_id)) {
			_dept_id = l5_dept_id;
		} else if (StringUtils.isNotBlank(l4_dept_id)) {
			_dept_id = l4_dept_id;
		} else if (StringUtils.isNotBlank(l3_dept_id)) {
			_dept_id = l3_dept_id;
		}
		if (StringUtils.isNotBlank(contains_sub_depts)) {
			entity.getMap().put("dept_id_all_prod_user", _dept_id);
		} else {
			entity.getMap().put("dept_id", _dept_id);
		}

		if (StringUtils.isNotBlank(role_id)) {
			entity.getMap().put("role_id", role_id);
		}
		if (StringUtils.isNotBlank(sales_type)) {
			entity.getMap().put("sales_type", sales_type);
		}
		if (StringUtils.isNotBlank(is_del)) {
			entity.setIs_del(Integer.valueOf(is_del));
		} else {
			entity.setIs_del(0);
		}

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_10 = false;
		boolean role_id_lt_200 = false;
		boolean role_id_ge_300 = false;
		boolean role_id_btw_200_300 = false;
		boolean role_id_btw_200_600 = false;
		boolean role_id_btw_300_400 = false;
		boolean role_id_btw_1000_1100 = false;
		for (PeRoleUser __peRoleUser : peRoleUserList) {
			if (__peRoleUser.getRole_id() == 10L) {
				role_id_eq_10 = true;
				request.setAttribute("is_admin", "0");
			}
			if (__peRoleUser.getRole_id() < 200L) {
				role_id_lt_200 = true;
			}
			if (__peRoleUser.getRole_id() >= 300L) {
				role_id_ge_300 = true;
			}
			if (__peRoleUser.getRole_id() >= 200L && __peRoleUser.getRole_id() < 300L) {
				role_id_btw_200_300 = true;
			}
			if (__peRoleUser.getRole_id() >= 200L && __peRoleUser.getRole_id() < 600L) {
				role_id_btw_200_600 = true;
			}
			if (__peRoleUser.getRole_id() >= 300L && __peRoleUser.getRole_id() < 400L) {
				role_id_btw_300_400 = true;
			}
			if (__peRoleUser.getRole_id() >= 1000L && __peRoleUser.getRole_id() < 1100L) {
				role_id_btw_1000_1100 = true;
			}
		}
		if (!role_id_eq_10) { // 非系统管理员只能看到自己部门下的用户，管理员能看到所有用户
			if (role_id_lt_200 || role_id_ge_300) {
				entity.getMap().put("dept_id_in", peProdUser.getDept_id());
				entity.getMap().put("user_id", peProdUser.getId());
			}
		}
		request.setAttribute("role_id_eq_10", role_id_eq_10);

		if (role_id_btw_200_300) {
			entity.getMap().put("is_pe_prod_user", "");
			entity.getMap().put("is_zmd_head", true);// 康佳专卖店总部端
		} else if (role_id_btw_300_400) {
			entity.getMap().put("is_pe_prod_user", "");
			entity.getMap().put("is_zmd_dept", true);// 康佳专卖店分公司
		} else if (role_id_btw_1000_1100) {
			entity.getMap().put("is_pec_user", "true");// 完美终端用户
		} else {
			entity.getMap().put("is_pe_prod_user", "true");// 康佳专版中人员管理中查询总数中将非专卖店的去掉所加的条件
		}
		if (StringUtils.isNotBlank(job_id)) {
			entity.getMap().put("job_id_eq", job_id);
		}
		if (StringUtils.isNotBlank(r3_job_id)) {
			entity.getMap().put("r3_job_id", r3_job_id);
		}

		Long recordCount = super.getFacade().getPeProdUserService()
				.getpeProdUserWithPositionNameAndFullDeptNameCount(entity);
		pager.init(recordCount, 10, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<PeProdUser> entityList = super.getFacade().getPeProdUserService()
				.getpeProdUserWithPositionNameAndFullDeptNamePaginatedList(entity);

		for (PeProdUser pp : entityList) {
			Attachment att = new Attachment();
			att.setLink_id(pp.getId());
			att.setLink_tab("KONKA_PE_PROD_USER");
			att.setFile_desc("zhaopian");
			List<Attachment> attlist = super.getFacade().getAttachmentService().getAttachmentList(att);
			if (attlist != null && attlist.size() > 0) {
				att = attlist.get(0);
				pp.getMap().put("sava_path", att.getSave_path());
				pp.getMap().put("file_name", att.getFile_name());
			}
		}

		request.setAttribute("entityList", entityList);

		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("人员岗位信息")
					+ ".xls");

			entity.getRow().setCount(recordCount.intValue());
			List<PeProdUser> entityList1 = super.getFacade().getPeProdUserService()
					.getpeProdUserWithPositionNameAndFullDeptNamePaginatedList(entity);
			dynaBean.set("excel_all", excel_all);
			request.setAttribute("allList", entityList1);

			return new ActionForward("/../manager/admin/PeProdUser/excel.jsp");
		}

		if (StringUtils.isNotBlank(excel_all_1) && "1".equals(excel_all_1)) {
			UserChangeInfo userInfo = new UserChangeInfo();
			List<UserChangeInfo> entityList2 = super.getFacade().getUserChangeInfoService()
					.getUserChangeInfoList(userInfo);
			dynaBean.set("excel_all_1", excel_all_1);
			request.setAttribute("allList_1", entityList2);
		}

		if (role_id_eq_10) {
			// 部门列表
			KonkaDept dept = new KonkaDept();
			dept.getMap().put("dept_id", peProdUser.getDept_id());
			List<KonkaDept> peDeptList = super.getFacade().getKonkaDeptService()
					.getKonkaDeptListWithTreeNameForProdUser(dept);
			request.setAttribute("peDeptList", peDeptList);
		}else{
            KonkaDept konkaDept = new KonkaDept();
            konkaDept.setDept_type(3);
            konkaDept.setDept_id(peProdUser.getDept_id());
            konkaDept.getMap().put("order_by_dept_name", true);
            List<KonkaDept> deptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
            request.setAttribute("peDeptList", deptList);
		}

		// 职务列表
		PeRoleInfo peRoleInfo = new PeRoleInfo();
		peRoleInfo.setIs_del(0);
		if (role_id_btw_200_600) {
			peRoleInfo.getMap().put("is_pe_prod_user", "");// 非康佳专版中人员管理中查询总数中将专卖店的去掉所加的条件
			if (role_id_btw_300_400) {
				peRoleInfo.getMap().put("is_zmd_manager_pe", true);// 添加专卖店管理人员
			}
		} else if (role_id_btw_1000_1100) {
			peRoleInfo.getMap().put("is_pec_user", "true");// 完美终端用户
		} else {
			peRoleInfo.getMap().put("is_pe_prod_user", "true");// 康佳专版中人员管理中查询总数中将非专卖店的去掉所加的条件
		}
		
		List<PeRoleInfo> roleInfoList = this.getFacade().getPeRoleInfoService().getPeRoleInfoList(peRoleInfo);
		request.setAttribute("roleInfoList", roleInfoList);

		request.setAttribute("requestStr", super.serialize(request, "is_del"));
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}

		super.setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		PeProdUser entity = new PeProdUser();
		entity.setProd_entp_id(peProdUser.getProd_entp_id());

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_10 = false; // 系统管理员
		boolean role_id_eq_30 = false; // 分公司管理员
		boolean role_id_btw_200_600 = false;
		boolean role_id_btw_300_400 = false;
		boolean role_id_btw_1000_1100 = false;
		for (PeRoleUser cur : peRoleUserList) {
			if (cur.getRole_id() == 10L) {
				role_id_eq_10 = true;
			}
			if (cur.getRole_id() == 30L) {
				role_id_eq_30 = true;
			}
			if (cur.getRole_id() >= 200L && cur.getRole_id() <= 600L) {
				role_id_btw_200_600 = true;
			}
			if (cur.getRole_id() >= 300L && cur.getRole_id() <= 400L) {
				role_id_btw_300_400 = true;
			}
			if (cur.getRole_id() >= 1000L && cur.getRole_id() <= 1100L) {
				role_id_btw_1000_1100 = true;
			}
		}

		request.setAttribute("role_id_eq_10", role_id_eq_10);

		// 部门列表
		KonkaDept konka_dept = new KonkaDept();

		Long dept_id = 0L;
		if (role_id_eq_10) {
		} else if (role_id_eq_30) {
			KonkaDept dept = super.getSuperiorForDeptType(peProdUser.getDept_id(), 3);
			if (null != dept) {
				dept_id = dept.getDept_id();
			} else {
				dept_id = peProdUser.getDept_id();
			}
		} else {
			dept_id = peProdUser.getDept_id();
		}
		konka_dept.getMap().put("dept_id", dept_id);
		request.setAttribute("cs_par_id", dept_id);

		List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService()
				.getKonkaDeptListWithTreeNameForProdUser(konka_dept);
		request.setAttribute("peDeptList", deptInfoList);

		dynaBean.set("position_id", "0");// 默认添加业务员
		dynaBean.set("order_value", "0");// 默认的排序值为0

		PeRoleInfo peRoleInfo = new PeRoleInfo();
		peRoleInfo.setIs_del(0);

		if (role_id_btw_200_600) {
			peRoleInfo.getMap().put("is_pe_prod_user", "");// 非康佳专版中人员管理中查询总数中将专卖店的去掉所加的条件
			if (role_id_btw_300_400) {
				peRoleInfo.getMap().put("is_zmd_manager_pe", true);// 添加专卖店管理人员
			}
		} else if (role_id_btw_1000_1100) {
			peRoleInfo.getMap().put("is_pec_user", "true");// 完美终端用户
		} else {
			peRoleInfo.getMap().put("is_pe_prod_user", "true");// 康佳专版中人员管理中查询总数中将非专卖店的去掉所加的条件
		}

		if (!role_id_eq_10) {
			// 非系统管理员查询分公司ID
			KonkaDept dept = super.getSuperiorForDeptType(peProdUser.getDept_id(), 3);
			if (null != dept) {
				peRoleInfo.setDept_id(dept.getDept_id());
			}
		}

		peRoleInfo.getMap().put("role_id_is_not_400", true);// 不回显 “专卖店管理员” 角色

		List<PeRoleInfo> roleInfoList = this.getFacade().getPeRoleInfoService().getPeRoleInfoList(peRoleInfo);

		if (!role_id_eq_10) {
			// 如果是分公司人员登录，放开60、188角色，使其可以操作
			List<PeRoleInfo> prependRoleInfoList = new ArrayList<PeRoleInfo>();

			for (String cur : do_with_fgs_roleids.split(",")) {
				PeRoleInfo r = new PeRoleInfo();
				r.setRole_id(Long.valueOf(cur));
				r = super.getFacade().getPeRoleInfoService().getPeRoleInfo(r);

				prependRoleInfoList.add(r);
			}

			prependRoleInfoList.addAll(roleInfoList);

			roleInfoList = prependRoleInfoList;
		}

		request.setAttribute("roleInfoList", roleInfoList);

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
		// String mod_id = (String) dynaBean.get("mod_id");
		// String tree_param = (String) dynaBean.get("tree_param");

		if (!GenericValidator.isLong(user_id)) {
			return this.list(mapping, form, request, response);
		}

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		
		request.setAttribute("current_user_name", peProdUser.getUser_name());
		request.setAttribute("current_user_id", peProdUser.getId());

		// 获取登录用户 企业信息
//		PeProdUser SxPeProdUser = new PeProdUser();
//		SxPeProdUser.setIs_del(0);
//		SxPeProdUser.setUser_type(1)
//		SxPeProdUser.setUser_name(peProdUser.getUser_name());
//		SxPeProdUser = super.getFacade().getPeProdUserService().getPeProdUser(SxPeProdUser);

		// 获取选择的修改用户 企业信息
		PeProdUser entity = new PeProdUser();
		entity.setProd_entp_id(peProdUser.getProd_entp_id());
		entity.setId(Long.valueOf(user_id));
		entity = super.getFacade().getPeProdUserService().getPeProdUser(entity);

		if (null == entity) {
			return this.list(mapping, form, request, response);
		}

		Attachment attachment = new Attachment();
		attachment.setLink_tab("KONKA_PE_PROD_USER");
		attachment.setLink_id(Long.valueOf(user_id));
		attachment.setFile_desc("zhaopian");
		attachment.setDel_mark(new Short("0"));
		List<Attachment> attList = super.getFacade().getAttachmentService().getAttachmentList(attachment);
		if (attList != null && attList.size() > 0) {
			for (Attachment attachment2 : attList) {
				dynaBean.set("save_path", attachment2.getSave_path());
				dynaBean.set("file_name", attachment2.getFile_name());
			}
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

		// PeRoleUser _ru = new PeRoleUser();
		// _ru.setUser_id(entity.getId());
		// _ru = super.getFacade().getPeRoleUserService().getPeRoleUser(_ru);
		// request.setAttribute("role_id", _ru.getRole_id());
		PeRoleUser _ru = new PeRoleUser();
		_ru.setUser_id(entity.getId());
		List<PeRoleUser> _ruList = super.getFacade().getPeRoleUserService().getPeRoleUserList(_ru);
		request.setAttribute("roleUserList1", _ruList);

		entity.setQueryString(super.serialize(request, "id", "mod_id", "tree_param"));
		super.copyProperties(form, entity);
		//System.out.println(entity.getQueryString() + "++++++++++++++++++++++++++++++++++++");
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
					// request.setAttribute("province", p_index);
				} else if (p_index.endsWith("00")) {
					if (p_index.length() == 6) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
						request.setAttribute("city", p_index);
					} else if (p_index.length() == 8) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "000000");
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "0000");
						request.setAttribute("country", StringUtils.substring(p_index, 0, 6) + "00");
					}
					// request.setAttribute("province",
					// StringUtils.substring(p_index, 0, 2) + "0000");
					// request.setAttribute("city", p_index);
				}
			}
		}

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_10 = false; // 系统管理员
		boolean role_id_eq_30 = false; // 分公司管理员
		boolean role_id_btw_200_600 = false;
		boolean role_id_btw_300_400 = false;
		boolean role_id_btw_1000_1100 = false;
		for (PeRoleUser cur : peRoleUserList) {
			if (cur.getRole_id() == 10L) {
				role_id_eq_10 = true;
			}
			if (cur.getRole_id() == 30L) {
				role_id_eq_30 = true;
			}
			if (cur.getRole_id() >= 200L && cur.getRole_id() <= 600L) {
				role_id_btw_200_600 = true;
			}
			if (cur.getRole_id() >= 300L && cur.getRole_id() <= 400L) {
				role_id_btw_300_400 = true;
			}
			if (cur.getRole_id() >= 1000L && cur.getRole_id() <= 1100L) {
				role_id_btw_1000_1100 = true;
			}
		}

		request.setAttribute("role_id_eq_10", role_id_eq_10);

		// 部门列表
		KonkaDept konka_dept = new KonkaDept();

		Long dept_id = 0L;
		if (role_id_eq_10) {
		} else if (role_id_eq_30) {
			KonkaDept dept = super.getSuperiorForDeptType(peProdUser.getDept_id(), 3);
			if (null != dept) {
				dept_id = dept.getDept_id();
			} else {
				dept_id = peProdUser.getDept_id();
			}
		} else {
			dept_id = peProdUser.getDept_id();
		}
		konka_dept.getMap().put("dept_id", dept_id);
		request.setAttribute("cs_par_id", dept_id);

		List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService()
				.getKonkaDeptListWithTreeNameForProdUser(konka_dept);
		request.setAttribute("peDeptList", deptInfoList);

		// 查询可见角色列表

		PeRoleInfo peRoleInfo = new PeRoleInfo();
		peRoleInfo.setIs_del(0);

		if (role_id_btw_200_600) {
			peRoleInfo.getMap().put("is_pe_prod_user", "");// 非康佳专版中人员管理中查询总数中将专卖店的去掉所加的条件
			if (role_id_btw_300_400) {
				peRoleInfo.getMap().put("is_zmd_manager_pe", true);// 添加专卖店管理人员
			}
		} else if (role_id_btw_1000_1100) {
			peRoleInfo.getMap().put("is_pec_user", "true");// 完美终端用户
		} else {
			peRoleInfo.getMap().put("is_pe_prod_user", "true");// 康佳专版中人员管理中查询总数中将非专卖店的去掉所加的条件
		}

		if (!role_id_eq_10) {
			// 查询分公司ID
			KonkaDept dept = super.getSuperiorForDeptType(peProdUser.getDept_id(), 3);
			if (null != dept) {
				peRoleInfo.setDept_id(dept.getDept_id());
			}
		}

		peRoleInfo.getMap().put("role_id_is_not_400", true);
		if (!role_id_eq_10) {
			peRoleInfo.getMap().put("role_id_60_32_187_188", do_with_fgs_roleids);
		}

		List<PeRoleInfo> roleInfoList = this.getFacade().getPeRoleInfoService().getPeRoleInfoList(peRoleInfo);

		Long[] roleids = new Long[roleInfoList.size()];
		int i = 0;
		for (PeRoleInfo cur : roleInfoList) {
			roleids[i++] = cur.getRole_id();
		}

		if (!role_id_eq_10) {
			// 如果是分公司人员登录，放开60、188角色，使其可以操作

			// List<PeRoleInfo> prependRoleInfoList = new
			// ArrayList<PeRoleInfo>();
			//
			// for (String cur : do_with_fgs_roleids.split(",")) {
			// if (ArrayUtils.contains(roleids, Long.valueOf(cur))) {
			// continue;
			// }
			//
			// PeRoleInfo r = new PeRoleInfo();
			// r.setRole_id(Long.valueOf(cur));
			// r = super.getFacade().getPeRoleInfoService().getPeRoleInfo(r);
			//
			// prependRoleInfoList.add(r);
			// }
			//
			// prependRoleInfoList.addAll(roleInfoList);
			//
			// roleInfoList = prependRoleInfoList;
		}

		// 查询指定用户已有的角色
		for (PeRoleUser user : _ruList) {
			if (ArrayUtils.contains(roleids, user.getRole_id())) {
				continue;
			}
			PeRoleInfo role = new PeRoleInfo();
			role.setRole_id(user.getRole_id());
			role = super.getFacade().getPeRoleInfoService().getPeRoleInfo(role);

			if (null != role) {
				// 将系统角色补充追加到角色列表
				roleInfoList.add(role);
			}
		}

		// 获取USER_ID的角色
		PeRoleUser peRoleUser_1 = new PeRoleUser();
		peRoleUser_1.setUser_id(Long.valueOf(user_id));
		List<PeRoleUser> peRoleUserList_1 = this.getFacade().getPeRoleUserService().getPeRoleUserList(peRoleUser_1);

		boolean role_id_eq_60 = false;
		boolean isRomveYwy = false;
		for (PeRoleUser pp : peRoleUserList_1) {
			if (pp.getRole_id() == 60L) {
				role_id_eq_60 = true;
				break;
			}
		}
		// 如果不是业务员，可以移除
		if (!role_id_eq_60) {
			isRomveYwy = true;
		}
		// 如果是业务员，并且没有关联客户，可以移除，如果有关联客户，则不可以移除
		if (role_id_eq_60) {
			BranchAssign branchAssign = new BranchAssign();
			branchAssign.setUser_id(Long.valueOf(user_id));
			Long bn = super.getFacade().getBranchAssignService().getBranchAssignCount(branchAssign);
			if (bn > 0) {
				isRomveYwy = false;
			} else {
				isRomveYwy = true;
			}
		}
		// dynaBean.set("isRomveYwy", isRomveYwy); ${af.map.isRomveYwy}

		request.setAttribute("isRomveYwy", isRomveYwy);

		request.setAttribute("roleInfoList", roleInfoList);
		// 查询可见角色列表结束

		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");
		String role_id = (String) dynaBean.get("role_id");

		if (!GenericValidator.isLong(user_id)) {
			return this.list(mapping, form, request, response);
		}

		PeProdUser entity = new PeProdUser();
		entity.setId(Long.valueOf(user_id));
		entity = super.getFacade().getPeProdUserService().getPeProdUser(entity);

		if (null == entity) {
			return this.list(mapping, form, request, response);
		}

		// 职务名称
		if (GenericValidator.isLong(role_id)) {
			PeRoleInfo peRoleInfo = new PeRoleInfo();
			peRoleInfo.setRole_id(Long.parseLong(role_id));
			peRoleInfo = super.getFacade().getPeRoleInfoService().getPeRoleInfo(peRoleInfo);
			String role_name = peRoleInfo.getRole_name();

			request.setAttribute("role_name", role_name);
		}

		if (null != entity.getDept_id()) {
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(entity.getDept_id());
			List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService()
					.getKonkaDeptListWithTreeNameAndFullName(konkaDept);
			if (null != konkaDeptList && konkaDeptList.size() > 0) {
				request.setAttribute("konkaDept", konkaDeptList.get(0));
				if (null != konkaDeptList.get(0).getLeader_user_id()) {
					PeProdUser leader = new PeProdUser();
					leader.setId(konkaDeptList.get(0).getLeader_user_id());
					leader = super.getFacade().getPeProdUserService().getPeProdUser(leader);
					request.setAttribute("leader", leader);
				}
			}
		}

		if (null != entity.getP_index()) {
			BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
			baseProvinceListFour.setP_index(entity.getP_index());
			baseProvinceListFour = super.getFacade().getBaseProvinceListFourService()
					.getBaseProvinceListFour(baseProvinceListFour);
			if (null != baseProvinceListFour && null != baseProvinceListFour.getFull_name()) {
				String p_index_name = baseProvinceListFour.getFull_name();

				request.setAttribute("p_index_name", p_index_name);
			}
		}

		Attachment att = new Attachment();
		att.setLink_id(Long.valueOf(user_id));
		att.setLink_tab("KONKA_PE_PROD_USER");
		att.setFile_desc("zhaopian");
		List<Attachment> attlist = super.getFacade().getAttachmentService().getAttachmentList(att);
		if (attlist != null && attlist.size() > 0) {
			att = attlist.get(0);
			entity.getMap().put("sava_path", att.getSave_path());
			entity.getMap().put("file_name", att.getFile_name());
		}
		super.copyProperties(form, entity);

		return mapping.findForward("view");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String tree_param = (String) dynaBean.get("tree_param");
		String dept_id = (String) dynaBean.get("dept_id");
		// String role_id = (String) dynaBean.get("role_id");
		String pass_word_old = (String) dynaBean.get("pass_word_old");
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");
		String returnUrl = (String) dynaBean.get("returnUrl");
		// 职务多选，Ren,zhong，2013-06-25
		String roleIds = (String) dynaBean.get("roleIds");
		String chan_status = (String) dynaBean.get("chan_status");

		// 全职类型，入职日期，岗位
		String sales_type = (String) dynaBean.get("sales_type");
		String work_date = (String) dynaBean.get("work_date");
		String position = (String) dynaBean.get("position");
		String r3JobId = (String) dynaBean.get("r3_job_id");

		// 获取登录用户 企业信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		PeProdUser entity = new PeProdUser();
		super.copyProperties(entity, form);

		String l3_dept_id = (String) dynaBean.get("l3_dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		if (StringUtils.isNotBlank(l5_dept_id)) {
			entity.setDept_id(Long.valueOf(l5_dept_id));
		} else if (StringUtils.isNotBlank(l4_dept_id)) {
			entity.setDept_id(Long.valueOf(l4_dept_id));
		} else if (StringUtils.isNotBlank(l3_dept_id)) {
			entity.setDept_id(Long.valueOf(l3_dept_id));
		} else if (StringUtils.isBlank(l3_dept_id)) {
			KonkaDept dept = getKonkaDeptForFgs(peProdUser.getDept_id());
			if (null != dept) {
				entity.setDept_id(dept.getDept_id());
			}
		}

		if (StringUtils.isNotBlank(sales_type)) {
			entity.getMap().put("sales_type", sales_type);
		}
		if (StringUtils.isNotBlank(work_date)) {
			entity.getMap().put("work_date", work_date);
		}
		if (StringUtils.isNotBlank(position)) {
			entity.getMap().put("position", position);
		}

		entity.setProd_entp_id(peProdUser.getProd_entp_id());
		entity.setAdd_e_user_id(peProdUser.getId());


		// 用户部门信息设定
		if (GenericValidator.isLong(dept_id)) {
			KonkaDept konka_dept = new KonkaDept();
			konka_dept.setDept_id(Long.valueOf(dept_id));
			konka_dept = super.getFacade().getKonkaDeptService().getKonkaDept(konka_dept);
			if (null == konka_dept) {
				return this.list(mapping, form, request, response);
			}
			entity.setDept_id(Long.valueOf(dept_id));
			entity.setDepartment(konka_dept.getDept_name());
			entity.setPar_dept_id(konka_dept.getPar_id());
		}

		List<UploadFile> uploadFileList = super.uploadFile(form, true, 0, new int[] { 240 });
		List<Attachment> filesAttachmentList = new ArrayList<Attachment>();

		for (UploadFile uploadFile : uploadFileList) {
			Attachment filesAttachment = new Attachment();
			filesAttachment.setFile_name(uploadFile.getFileName());
			filesAttachment.setFile_type(uploadFile.getContentType());
			filesAttachment.setFile_size(new Integer(uploadFile.getFileSize()));
			filesAttachment.setSave_path(uploadFile.getFileSavePath());
			filesAttachment.setSave_name(uploadFile.getFileSaveName());
			filesAttachment.setDel_mark(new Short("0"));
			filesAttachment.setFile_desc("zhaopian");
			filesAttachmentList.add(filesAttachment);
		}

		entity.setAttachmentList(filesAttachmentList);

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
		if (StringUtils.isNotBlank(roleIds)) {
			entity.getMap().put("roleIds", roleIds);
		}
		if (StringUtils.isNotBlank(chan_status)) {
			entity.getMap().put("chan_status", chan_status);
		}

		if (!GenericValidator.isLong(user_id)) {// 创建用户
			PeProdUser prodUser = new PeProdUser();
			prodUser.setUser_name(entity.getUser_name());
			prodUser.setIs_del(0);
			Long count = super.getFacade().getPeProdUserService().getPeProdUserCount(prodUser);
			if (count != 0) {
				super.renderJavaScript(response, "window.onload=function(){alert('用户名\"" + entity.getUser_name()
						+ "\"与数据库中重复，请重新填写 ');history.back();}");
				return null;
			}
			if (r3JobId != null && !"".equals(r3JobId)) {
				PeProdUser prodUser2 = new PeProdUser();
				prodUser2.setR3_job_id(r3JobId);
				count = super.getFacade().getPeProdUserService().getPeProdUserCount(prodUser2);
				if (count > 0) {
					super.renderJavaScript(response, "window.onload=function(){alert('R3人员编码\"" + r3JobId
							+ "\"重复，请重新输入 ');history.back();}");
					return null;
				}
			}

			DESPlus des = new DESPlus();
			entity.setPass_word(des.encrypt(entity.getPass_word()));
			super.getFacade().getPeProdUserService().createPeProdUser(entity);
			super.saveMessage(request, "entity.inserted");
		} else {

			if (r3JobId != null && !"".equals(r3JobId)) {
				boolean r3jobidflag = false;
				PeProdUser prodUser2 = new PeProdUser();
				prodUser2.setR3_job_id(r3JobId);
				List<PeProdUser> userList2 = super.getFacade().getPeProdUserService().getPeProdUserList(prodUser2);

				if (userList2 != null && userList2.size() > 0) {
					for (PeProdUser u2 : userList2) {
						if (!u2.getId().equals(Long.valueOf(user_id))) {
							r3jobidflag = true;
							break;
						}
					}
				}
				if (r3jobidflag) {
					super.renderJavaScript(response, "window.onload=function(){alert('R3人员编码\"" + r3JobId
							+ "\"重复，请重新输入 ');history.back();}");
					return null;
				}

			}

			// 查询已有角色
			String old_role_name = "";
			PeRoleInfo p = new PeRoleInfo();
			p.getMap().put("user_id", user_id);
			p = super.getFacade().getPeRoleInfoService().getPeRoleInfoForRoleNames(p);
			if (null != p && null != p.getMap().get("role_names")) {
				old_role_name = p.getMap().get("role_names").toString();
			}

			String new_role_name = "";
			if (StringUtils.isNotBlank(roleIds)) {
				PeRoleInfo p1 = new PeRoleInfo();
				p1.getMap().put("role_ids", roleIds);
				p1 = super.getFacade().getPeRoleInfoService().getPeRoleInfoForRoleNames(p1);
				if (null != p1 && null != p1.getMap().get("role_names")) {
					new_role_name = p1.getMap().get("role_names").toString();
				}
			}

			// 保存停用记录
			PeProdUser pp = new PeProdUser();
			pp.setId(Long.valueOf(user_id));
			pp = super.getFacade().getPeProdUserService().getPeProdUser(pp);
			entity.setJob_id(pp.getJob_id());

			if (null != p) {
				KonkaStoreUserCInfo k = new KonkaStoreUserCInfo();
				k.setAdd_date(new Date());
				k.setAdd_user_id(peProdUser.getId());
				k.setAdd_user_job_id(peProdUser.getJob_id());
				k.setAdd_user_name(peProdUser.getUser_name());
				k.setC_type(70);
				k.setChange_info("该人员的角色“" + old_role_name + "”修改为“" + new_role_name + "”");
				k.setSs_id(pp.getId());
				k.setSs_name(pp.getUser_name());
				super.getFacade().getKonkaStoreUserCInfoService().createKonkaStoreUserCInfo(k);
			}

			entity.setId(Long.valueOf(user_id));
			if (!StringUtils.equals(pass_word_old, entity.getPass_word())) {
				DESPlus des = new DESPlus();
				entity.setPass_word(des.encrypt(entity.getPass_word()));
			}

			// super.getFacade().getPeProdUserService().modifyPeProdUser(entity);
			entity.setModify_date(new Date());
			entity.setModify_user_id(peProdUser.getId());
			super.getFacade().getPeProdUserService().modifyPeProdUserWithMultiRoleUser(entity);
			super.saveMessage(request, "entity.updated");
		}

		if (StringUtils.isNotBlank(returnUrl)) {
			return null;
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("tree_param=" + tree_param);
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
		String tree_param = (String) dynaBean.get("tree_param");

		if (!GenericValidator.isLong(user_id)) {
			saveError(request, "errors.long", new String[] { user_id });
			return mapping.findForward("list");
		}

		// 保存停用记录
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		PeProdUser p = new PeProdUser();
		p.setId(Long.valueOf(user_id));
		p = super.getFacade().getPeProdUserService().getPeProdUser(p);

		if (null != p) {
			KonkaStoreUserCInfo k = new KonkaStoreUserCInfo();
			k.setAdd_date(new Date());
			k.setAdd_user_id(ui.getId());
			k.setAdd_user_job_id(ui.getJob_id());
			k.setAdd_user_name(ui.getUser_name());
			k.setC_type(60);
			k.setChange_info("该人员被停用!");
			k.setSs_id(p.getId());
			k.setSs_name(p.getUser_name());
			super.getFacade().getKonkaStoreUserCInfoService().createKonkaStoreUserCInfo(k);
		}

		PeProdUser entity = new PeProdUser();
		entity.setId(Long.valueOf(user_id));
		entity.setIs_del(1);
		super.getFacade().getPeProdUserService().modifyPeProdUser(entity);

		saveMessage(request, "konka.close.success");

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

	public ActionForward save1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String tree_param = (String) dynaBean.get("tree_param");
		String chan_status = (String) dynaBean.get("chan_status");

		if (!GenericValidator.isLong(user_id)) {
			saveError(request, "errors.long", new String[] { user_id });
			return mapping.findForward("list");
		}

		// 保存停用记录
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		PeProdUser p = new PeProdUser();
		p.setId(Long.valueOf(user_id));
		p = super.getFacade().getPeProdUserService().getPeProdUser(p);

		if (null != p) {
			KonkaStoreUserCInfo k = new KonkaStoreUserCInfo();
			k.setAdd_date(new Date());
			k.setAdd_user_id(ui.getId());
			k.setAdd_user_job_id(ui.getJob_id());
			k.setAdd_user_name(ui.getUser_name());
			k.setC_type(60);
			k.setChange_info("该人员被停用!");
			k.setSs_id(p.getId());
			k.setSs_name(p.getUser_name());
			super.getFacade().getKonkaStoreUserCInfoService().createKonkaStoreUserCInfo(k);
		}

		// 变更记录表插入数据
		UserChangeInfo u = new UserChangeInfo();
		u.setJob_id(p.getJob_id());
		List<UserChangeInfo> userChangeInfoList = super.getFacade().getUserChangeInfoService()
				.getUserChangeInfoByTimeList(u);
		if (null != userChangeInfoList && userChangeInfoList.size() > 0) {
			UserChangeInfo userInfo = userChangeInfoList.get(0);
			userInfo.setEnd_date(new Date());
			super.getFacade().getUserChangeInfoService().modifyUserChangeInfo(userInfo);

			UserChangeInfo userInfo2 = new UserChangeInfo();
			userInfo2.setJob_id(p.getJob_id());
			userInfo2.setReal_name_new(p.getReal_name());
			userInfo2.setUser_name_new(p.getUser_name());
			userInfo2.setReal_name_old(p.getReal_name());
			userInfo2.setUser_name_old(p.getUser_name());
			userInfo2.setStart_date(new Date());
			userInfo2.setChan_status(Integer.valueOf(chan_status));
			super.getFacade().getUserChangeInfoService().createUserChangeInfo(userInfo2);
		} else {
			UserChangeInfo userInfo = new UserChangeInfo();
			userInfo.setJob_id(p.getJob_id());
			userInfo.setUser_name_old(p.getUser_name());
			userInfo.setUser_name_new(p.getUser_name());
			userInfo.setReal_name_old(p.getReal_name());
			userInfo.setReal_name_new(p.getReal_name());
			userInfo.setStart_date(new Date());
			userInfo.setChan_status(Integer.valueOf(chan_status));
			super.getFacade().getUserChangeInfoService().createUserChangeInfo(userInfo);

		}

		PeProdUser entity = new PeProdUser();
		entity.setId(Long.valueOf(user_id));
		entity.setIs_del(1);
		super.getFacade().getPeProdUserService().modifyPeProdUser(entity);

		saveMessage(request, "konka.close.success");

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

	public ActionForward stop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");

		if (!GenericValidator.isLong(user_id)) {
			return this.list(mapping, form, request, response);
		}

		PeProdUser entity = new PeProdUser();
		entity.setId(Long.valueOf(user_id));
		entity = super.getFacade().getPeProdUserService().getPeProdUser(entity);

		if (null == entity) {
			return this.list(mapping, form, request, response);
		}

		super.copyProperties(form, entity);

		if (null != entity.getDept_id()) {
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(entity.getDept_id());
			List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService()
					.getKonkaDeptListWithTreeNameAndFullName(konkaDept);
			if (null != konkaDeptList && konkaDeptList.size() > 0) {
				request.setAttribute("konkaDept", konkaDeptList.get(0));
				if (null != konkaDeptList.get(0).getLeader_user_id()) {
					PeProdUser leader = new PeProdUser();
					leader.setId(konkaDeptList.get(0).getLeader_user_id());
					leader = super.getFacade().getPeProdUserService().getPeProdUser(leader);
					request.setAttribute("leader", leader);
				}
			}
		}

		if (null != entity.getP_index()) {
			BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
			baseProvinceListFour.setP_index(entity.getP_index());
			baseProvinceListFour = super.getFacade().getBaseProvinceListFourService()
					.getBaseProvinceListFour(baseProvinceListFour);

			if (null != baseProvinceListFour && null != baseProvinceListFour.getFull_name()) {
				String p_index_name = baseProvinceListFour.getFull_name();

				request.setAttribute("p_index_name", p_index_name);
			}
		}

		return new ActionForward("/../manager/admin/PeProdUser/stop.jsp");
	}

	public ActionForward reStart(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String tree_param = (String) dynaBean.get("tree_param");

		if (!GenericValidator.isLong(user_id)) {
			saveError(request, "errors.long", new String[] { user_id });
			return mapping.findForward("list");
		}

		// 保存停用记录
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		PeProdUser p = new PeProdUser();
		p.setId(Long.valueOf(user_id));
		p = super.getFacade().getPeProdUserService().getPeProdUser(p);

		// 判断用户名是否别其他的用户使用，如果有其他使用者，不给于启用操作
		if (null != p) {
			PeProdUser p_exist = new PeProdUser();
			p_exist.setUser_name(p.getUser_name());
			p_exist.setIs_del(0);
			Long count = super.getFacade().getPeProdUserService().getPeProdUserCount(p_exist);
			if (count != 0) {
				super.renderJavaScript(response, "window.onload=function(){alert('用户名\"" + p.getUser_name()
						+ "\"与数据库中重复，请重新填写 ');history.back();}");
				return null;
			}
		}

		if (null != p) {
			KonkaStoreUserCInfo k = new KonkaStoreUserCInfo();
			k.setAdd_date(new Date());
			k.setAdd_user_id(ui.getId());
			k.setAdd_user_job_id(ui.getJob_id());
			k.setAdd_user_name(ui.getUser_name());
			k.setC_type(60);
			k.setChange_info("该人员被启用!");
			k.setSs_id(p.getId());
			k.setSs_name(p.getUser_name());
			super.getFacade().getKonkaStoreUserCInfoService().createKonkaStoreUserCInfo(k);
		}

		PeProdUser entity = new PeProdUser();
		entity.setId(Long.valueOf(user_id));
		entity.setIs_del(0);
		super.getFacade().getPeProdUserService().modifyPeProdUser(entity);

		saveMessage(request, "konka.restart.success");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("tree_param=" + tree_param);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "user_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
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

		PeProdUser entity = new PeProdUser();
		entity.setId(Long.valueOf(user_id));

		DESPlus des = new DESPlus();
		entity.setPass_word(des.encrypt("888888")); // 默认密码

		super.getFacade().getPeProdUserService().modifyPeProdUser(entity);

		saveMessage(request, "password.init.success");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("tree_param=" + tree_param);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "user_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward validateJobId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		StringBuffer oper = new StringBuffer("{\"result\":");
		String job_id = (String) dynaBean.get("job_id");
		PeProdUser entity = new PeProdUser();
		entity.setJob_id(job_id);
		// entity.setIs_del(0);
		Long count = super.getFacade().getPeProdUserService().getPeProdUserCount(entity);
		if ("0".equals(count.toString())) {
			oper.append(false);
		} else {
			oper.append(true);
		}
		oper.append("}");
		super.render(response, oper.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward validateName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		StringBuffer oper = new StringBuffer("{\"result\":");
		String user_name = (String) dynaBean.get("user_name");
		PeProdUser entity = new PeProdUser();
		entity.setUser_name(user_name);
		Long count = super.getFacade().getPeProdUserService().getPeProdUserCount(entity);
		if ("0".equals(count.toString())) {
			oper.append(false);
		} else {
			oper.append(true);
		}
		oper.append("}");
		super.render(response, oper.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward listUserForShow(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String position_id = (String) dynaBean.get("position_id");
		String dept_id = (String) dynaBean.get("dept_id");
		String role_id = (String) dynaBean.get("role_id");

		// 获取登录用户 企业信息
		PeProdUser PeProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		PeProdUser entity = new PeProdUser();
		entity.setProd_entp_id(PeProdUser.getProd_entp_id());
		entity.setIs_del(0);

		if (GenericValidator.isLong(position_id)) {
			entity.setPosition_id(Long.valueOf(position_id));
		}
		if (GenericValidator.isLong(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
		}
		if (GenericValidator.isLong(role_id)) {
			entity.getMap().put("role_id", role_id);
		}
		// 没有分页，取不多于500条记录
		entity.getRow().setFirst(0);
		entity.getRow().setCount(500);

		List<PeProdUser> entityList = super.getFacade().getPeProdUserService()
				.getpeProdUserWithPositionNameAndFullDeptNamePaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return new ActionForward("/../manager/admin/PeProdUser/list_user_for_show.jsp");
	}

	public ActionForward userLogin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean lazyForm = (DynaBean) form;

		String user_id = (String) lazyForm.get("user_id");

		String msg = null;
		if (StringUtils.isBlank(user_id)) {
			msg = super.getMessage(request, "login.failed.username.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		}

		PeProdUser entity = new PeProdUser();
		entity.setId(new Long(user_id));
		List<PeProdUser> userInfoList = getFacade().getPeProdUserService().getPeProdUserList(entity);
		if (null == userInfoList || userInfoList.size() == 0) {
			msg = super.getMessage(request, "login.failed.username.invalid");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		} else if (userInfoList.size() > 1) {
			msg = super.getMessage(request, "login.failed.username.repeat");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		}

		PeProdUser userInfo = getFacade().getPeProdUserService().getPeProdUser(entity);
		if (null != userInfo) {
			HttpSession session = request.getSession();
			session.setAttribute(Constants.USER_INFO, userInfo);
			session.setAttribute(Constants.ROLE_INFO, super.getRoleInfoByUserId(userInfo.getId()));

			PeRoleUser _peRoleUser = new PeRoleUser();
			_peRoleUser.setUser_id(userInfo.getId());
			List<PeRoleUser> peRoleUserList = super.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

			if (null != peRoleUserList && peRoleUserList.size() > 0) {
				String[] roleNames = new String[peRoleUserList.size()];
				int i = 0;
				for (PeRoleUser peRoleUser : peRoleUserList) {
					roleNames[i] = (String) peRoleUser.getMap().get("role_name");
				}
				session.setAttribute(Constants.ROLE_NAMES, StringUtils.join(roleNames, ","));
				session.setAttribute(Constants.ROLE_INFO_LIST, peRoleUserList);
			}
		}

		super.renderJavaScript(response,
				"window.onload=function(){top.location.href='../../manager/admin/Frames3.do?method=index'}");
		return null;
	}

	public ActionForward change(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String job_id = (String) dynaBean.get("job_id");

		UserChangeInfo entity = new UserChangeInfo();
		entity.setJob_id(job_id);
		List<UserChangeInfo> userChangeInfoList = super.getFacade().getUserChangeInfoService()
				.getUserChangeInfoList(entity);

		request.setAttribute("userChangeInfoList", userChangeInfoList);

		return new ActionForward("/../manager/admin/PeProdUser/list_user_for_change.jsp");
	}

	public ActionForward listForChange(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String role_id = (String) dynaBean.get("role_id");
		String excel_all_1 = (String) dynaBean.get("excel_all_1");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		PeProdUser entity = new PeProdUser();
		entity.getMap().put("is_position_dept", true);

		if (StringUtils.isNotBlank(role_id)) {
			entity.getMap().put("role_id", role_id);
		}

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_10 = false;
		boolean role_id_lt_200 = false;
		boolean role_id_ge_300 = false;
		boolean role_id_btw_200_300 = false;
		boolean role_id_btw_200_600 = false;
		boolean role_id_btw_300_400 = false;
		boolean role_id_btw_1000_1100 = false;
		for (PeRoleUser __peRoleUser : peRoleUserList) {
			if (__peRoleUser.getRole_id() == 10L) {
				role_id_eq_10 = true;
				request.setAttribute("is_admin", "0");
			}
			if (__peRoleUser.getRole_id() < 200L) {
				role_id_lt_200 = true;
			}
			if (__peRoleUser.getRole_id() >= 300L) {
				role_id_ge_300 = true;
			}
			if (__peRoleUser.getRole_id() >= 200L && __peRoleUser.getRole_id() < 300L) {
				role_id_btw_200_300 = true;
			}
			if (__peRoleUser.getRole_id() >= 200L && __peRoleUser.getRole_id() < 600L) {
				role_id_btw_200_600 = true;
			}
			if (__peRoleUser.getRole_id() >= 300L && __peRoleUser.getRole_id() < 400L) {
				role_id_btw_300_400 = true;
			}
			if (__peRoleUser.getRole_id() >= 1000L && __peRoleUser.getRole_id() < 1100L) {
				role_id_btw_1000_1100 = true;
			}
		}

		if (!role_id_eq_10) { 
			// 非系统管理员只能看到自己部门下的用户，管理员能看到所有用户
			if (role_id_lt_200 || role_id_ge_300) {
				entity.getMap().put("dept_id_in", peProdUser.getDept_id());
				entity.getMap().put("user_id", peProdUser.getId());
			}
		}
		request.setAttribute("role_id_eq_10", role_id_eq_10);

		if (role_id_btw_200_300) {
			entity.getMap().put("is_pe_prod_user", "");
			entity.getMap().put("is_zmd_head", true);// 康佳专卖店总部端
		} else if (role_id_btw_300_400) {
			entity.getMap().put("is_pe_prod_user", "");
			entity.getMap().put("is_zmd_dept", true);// 康佳专卖店分公司
		} else if (role_id_btw_1000_1100) {
			entity.getMap().put("is_pec_user", "true");// 完美终端用户
		} else {
			entity.getMap().put("is_pe_prod_user", "true");// 康佳专版中人员管理中查询总数中将非专卖店的去掉所加的条件
		}

		if (StringUtils.isNotBlank(excel_all_1) && "1".equals(excel_all_1)) {
			UserChangeInfo userInfo = new UserChangeInfo();
			List<UserChangeInfo> entityList2 = super.getFacade().getUserChangeInfoService()
					.getUserChangeInfoList(userInfo);
			dynaBean.set("excel_all_1", excel_all_1);
			request.setAttribute("allList_1", entityList2);
		}

		if (role_id_eq_10) {
			// 部门列表
			KonkaDept dept = new KonkaDept();
			dept.getMap().put("dept_id", peProdUser.getDept_id());
			List<KonkaDept> peDeptList = super.getFacade().getKonkaDeptService()
					.getKonkaDeptListWithTreeNameForProdUser(dept);
			request.setAttribute("peDeptList", peDeptList);
		}

		// 职务列表
		PeRoleInfo peRoleInfo = new PeRoleInfo();
		peRoleInfo.setIs_del(0);
		if (role_id_eq_10) {

		}
		if (role_id_btw_200_600) {
			peRoleInfo.getMap().put("is_pe_prod_user", "");// 非康佳专版中人员管理中查询总数中将专卖店的去掉所加的条件
			if (role_id_btw_300_400) {
				peRoleInfo.getMap().put("is_zmd_manager_pe", true);// 添加专卖店管理人员
			}
		} else if (role_id_btw_1000_1100) {
			peRoleInfo.getMap().put("is_pec_user", "true");// 完美终端用户
		} else {
			peRoleInfo.getMap().put("is_pe_prod_user", "true");// 康佳专版中人员管理中查询总数中将非专卖店的去掉所加的条件
		}
		List<PeRoleInfo> roleInfoList = this.getFacade().getPeRoleInfoService().getPeRoleInfoList(peRoleInfo);
		request.setAttribute("roleInfoList", roleInfoList);

		request.setAttribute("requestStr", super.serialize(request, "is_del"));

		return new ActionForward("/../manager/admin/PeProdUser.do?method=list&mod_id=902300");
	}

	// Xiao,guojian Ajax获取职务列表
	public ActionForward ajaxSelectRoleList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String role_name_like = (String) dynaBean.get("role_name_like");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		PeProdUser entity = new PeProdUser();
		entity.setProd_entp_id(peProdUser.getProd_entp_id());

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_10 = false; // 系统管理员
		boolean role_id_eq_30 = false; // 分公司管理员
		boolean role_id_btw_200_600 = false;
		boolean role_id_btw_300_400 = false;
		boolean role_id_btw_1000_1100 = false;
		for (PeRoleUser cur : peRoleUserList) {
			if (cur.getRole_id() == 10L) {
				role_id_eq_10 = true;
			}
			if (cur.getRole_id() == 30L) {
				role_id_eq_30 = true;
			}
			if (cur.getRole_id() >= 200L && cur.getRole_id() <= 600L) {
				role_id_btw_200_600 = true;
			}
			if (cur.getRole_id() >= 300L && cur.getRole_id() <= 400L) {
				role_id_btw_300_400 = true;
			}
			if (cur.getRole_id() >= 1000L && cur.getRole_id() <= 1100L) {
				role_id_btw_1000_1100 = true;
			}
		}

		request.setAttribute("role_id_eq_10", role_id_eq_10);

		// 部门列表
		KonkaDept konka_dept = new KonkaDept();

		Long dept_id = 0L;
		if (role_id_eq_10) {
		} else if (role_id_eq_30) {
			KonkaDept dept = super.getSuperiorForDeptType(peProdUser.getDept_id(), 3);
			if (null != dept) {
				dept_id = dept.getDept_id();
			} else {
				dept_id = peProdUser.getDept_id();
			}
		} else {
			dept_id = peProdUser.getDept_id();
		}
		konka_dept.getMap().put("dept_id", dept_id);
		request.setAttribute("cs_par_id", dept_id);

		List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService()
				.getKonkaDeptListWithTreeNameForProdUser(konka_dept);
		request.setAttribute("peDeptList", deptInfoList);

		dynaBean.set("position_id", "0");// 默认添加业务员
		dynaBean.set("order_value", "0");// 默认的排序值为0

		PeRoleInfo peRoleInfo = new PeRoleInfo();
		peRoleInfo.setIs_del(0);

		if (role_id_btw_200_600) {
			peRoleInfo.getMap().put("is_pe_prod_user", "");// 非康佳专版中人员管理中查询总数中将专卖店的去掉所加的条件
			if (role_id_btw_300_400) {
				peRoleInfo.getMap().put("is_zmd_manager_pe", true);// 添加专卖店管理人员
			}
		} else if (role_id_btw_1000_1100) {
			peRoleInfo.getMap().put("is_pec_user", "true");// 完美终端用户
		} else {
			peRoleInfo.getMap().put("is_pe_prod_user", "true");// 康佳专版中人员管理中查询总数中将非专卖店的去掉所加的条件
		}

		if (!role_id_eq_10) {
			// 非系统管理员查询分公司ID
			KonkaDept dept = super.getSuperiorForDeptType(peProdUser.getDept_id(), 3);
			if (null != dept) {
				peRoleInfo.setDept_id(dept.getDept_id());
			}
		}

		peRoleInfo.getMap().put("role_id_is_not_400", true);// 不回显 “专卖店管理员” 角色

		List<PeRoleInfo> roleInfoList = this.getFacade().getPeRoleInfoService().getPeRoleInfoList(peRoleInfo);

		if (!role_id_eq_10) {
			// 如果是分公司人员登录，放开60、188角色，使其可以操作
			List<PeRoleInfo> prependRoleInfoList = new ArrayList<PeRoleInfo>();

			for (String cur : do_with_fgs_roleids.split(",")) {
				PeRoleInfo r = new PeRoleInfo();
				r.setRole_id(Long.valueOf(cur));
				r = super.getFacade().getPeRoleInfoService().getPeRoleInfo(r);

				prependRoleInfoList.add(r);
			}

			prependRoleInfoList.addAll(roleInfoList);

			roleInfoList = prependRoleInfoList;
		}
		StringBuffer sb = new StringBuffer("{\"status\":\"1\",\"data\":[");
		for (PeRoleInfo role : roleInfoList) {
			if (role.getRole_id() < 10000) {// 系统角色
				if (StringUtils.isNotBlank(role_name_like)) {
					if (("[系统角色]" + role.getRole_name()).indexOf(role_name_like) != -1) {
						sb.append("{\"role_id\":\"").append(role.getRole_id()).append("\", \"role_name\":\"")
								.append("[系统角色]" + role.getRole_name()).append("\"},");
					}
				} else {
					sb.append("{\"role_id\":\"").append(role.getRole_id()).append("\", \"role_name\":\"")
							.append("[系统角色]" + role.getRole_name()).append("\"},");
				}
			} else {
				if (StringUtils.isNotBlank(role_name_like)) {
					if ((role.getRole_name()).indexOf(role_name_like) != -1) {
						sb.append("{\"role_id\":\"").append(role.getRole_id()).append("\", \"role_name\":\"")
								.append(role.getRole_name()).append("\"},");
					}
				} else {
					sb.append("{\"role_id\":\"").append(role.getRole_id()).append("\", \"role_name\":\"")
							.append(role.getRole_name()).append("\"},");
				}
			}
		}
		sb.delete(sb.length() - 1, sb.length());
		sb.append("]}");
		logger.info("------json---->{}", sb.toString());
		super.renderJson(response, sb.toString());
		return null;
	}

	public ActionForward ajaxUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String is_show = (String) dynaBean.get("is_show");
		String result = "";
		PeProdUser entity = new PeProdUser();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getPeProdUserService().getPeProdUser(entity);

		if (null == entity || entity.getDept_id() == null) {
			result = "1";
			super.renderJson(response, result);
			return null;
		}

		if (is_show.equals("1")) {
			entity.setIs_show(1);
		} else if (is_show.equals("0")) {
			entity.setIs_show(0);
		}
		super.getFacade().getPeProdUserService().modifyPeProdUser(entity);
		result = "0";
		super.renderJson(response, result);
		return null;
	}

	// 根据用户id 和部门查找下级用户列表
	public ActionForward ajaxGetUserId_DeptId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		String id = String.valueOf(dynaBean.get("id"));
		String dept_id = (String) dynaBean.get("dept_id");

		PeProdUser entity = new PeProdUser();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
		}
		List<PeProdUser> entityList = super.getFacade().getPeProdUserService().getPeProdUserBy_id_deptid(entity);
		// 封装成JSON字符串
		Map<String, Object> m = new HashMap<String, Object>();
		if (entityList == null) {
			String[] str = {};
			m.put("rows", str);
		} else {
			m.put("rows", entityList);
		}
		JSONArray jsonArray = JSONArray.fromObject(m);
		int start = jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start + 1, end + 1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}
}
