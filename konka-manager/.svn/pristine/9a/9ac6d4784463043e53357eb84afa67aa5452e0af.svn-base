package com.ebiz.mmt.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.KonkaBaseTypeData;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileSpRelation;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.KonkaStoreUserCInfo;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Jiang,JiaYong
 * @version：2013-04-18
 */
public class PromotersPeProdUserAction extends BaseAction {

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 判断session是否超时
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		String name_like = (String) dynaBean.get("name_like");
		String is_del = (String) dynaBean.get("is_del");
		String dept_id = (String) dynaBean.get("dept_id");
		String job_like = (String) dynaBean.get("job_like");
		String store_name_like = (String) dynaBean.get("store_name_like");
		String job_id = (String) dynaBean.get("job_id");
		String excel_all = (String) dynaBean.get("excel_all");
		String sales_type = (String) dynaBean.get("sales_type");
		String r3_job_id = (String) dynaBean.get("r3_job_id");

		PeRoleUser __peRoleUser = new PeRoleUser();
		// __peRoleUser.setUser_id(peProdUser.getId());
		// __peRoleUser =
		// super.getFacade().getPeRoleUserService().getPeRoleUser(__peRoleUser);
		__peRoleUser = super.getRoleInfoByThisLogin(request);
		request.setAttribute("peRoleUser", __peRoleUser);

		PeProdUser entity = new PeProdUser();
		entity.getMap().put("role_id", "188"); // 定死为促销员
		entity.getMap().put("is_position_dept", true);

		if (GenericValidator.isInt(is_del)) {
			entity.getMap().put("is_del", is_del);
		} else {
			entity.getMap().put("is_del", 0);
			dynaBean.set("is_del", 0);
		}
		if (StringUtils.isNotBlank(name_like)) {
			entity.getMap().put("name_like", name_like);
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("dept_id_all_prod_user", dept_id);
		}
		if (StringUtils.isNotBlank(job_like)) {
			entity.getMap().put("job_like", job_like);
		}
		if (StringUtils.isNotBlank(sales_type)) {
			entity.getMap().put("sales_type", sales_type);
		}
		if (StringUtils.isNotBlank(store_name_like)) {
			String ids = "-1";

			entity.setJob_id(job_id);

			KonkaMobileSpRelation konkaMobileSpRelation1 = new KonkaMobileSpRelation();
			konkaMobileSpRelation1.getMap().put("store_name_like", store_name_like);
			List<KonkaMobileSpRelation> konkaMobileSpRelation1List = super.getFacade()
					.getKonkaMobileSpRelationService().getKonkaMobileSpRelationForCxyIdList(konkaMobileSpRelation1);
			if (konkaMobileSpRelation1List.size() > 0) {
				for (KonkaMobileSpRelation temp : konkaMobileSpRelation1List) {
					ids = ids + "," + temp.getSeller_id();
				}
			}

			entity.getMap().put("in_stroe_ids", ids);
		}
		if (StringUtils.isNotBlank(r3_job_id)) {
			entity.getMap().put("r3_job_id", r3_job_id);
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
		for (PeRoleUser _peRoleUser1 : peRoleUserList) {
			if (_peRoleUser1.getRole_id() == 10L) {
				role_id_eq_10 = true;
				request.setAttribute("is_admin", "0");
			}
			if (_peRoleUser1.getRole_id() < 200L) {
				role_id_lt_200 = true;
			}
			if (_peRoleUser1.getRole_id() >= 300L) {
				role_id_ge_300 = true;
			}
			if (_peRoleUser1.getRole_id() >= 200L && _peRoleUser1.getRole_id() < 300L) {
				role_id_btw_200_300 = true;
			}
			if (_peRoleUser1.getRole_id() >= 200L && _peRoleUser1.getRole_id() < 600L) {
				role_id_btw_200_600 = true;
			}
			if (_peRoleUser1.getRole_id() >= 300L && _peRoleUser1.getRole_id() < 400L) {
				role_id_btw_300_400 = true;
			}
			if (_peRoleUser1.getRole_id() >= 1000L && _peRoleUser1.getRole_id() < 1100L) {
				role_id_btw_1000_1100 = true;
			}
		}

		if (!role_id_eq_10) { // 非系统管理员只能看到自己部门下的用户，管理员能看到所有用户
			// entity.setDept_id(SxPeProdUser.getDept_id());
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

		if (role_id_btw_200_600) {
			entity.getMap().put("is_pe_prod_user", "");// 非康佳专版中人员管理中查询总数中将专卖店的去掉所加的条件
			if (role_id_btw_300_400) {
				entity.getMap().put("is_zmd_manager_pe", true);// 添加专卖店管理人员
			}
		} else if (role_id_btw_1000_1100) {
			entity.getMap().put("is_pec_user", "true");// 完美终端用户
		} else {
			entity.getMap().put("is_pe_prod_user", "true");// 康佳专版中人员管理中查询总数中将非专卖店的去掉所加的条件
		}
		entity.getMap().put("order_by_name", "true");// 按照姓名排序
		Long recordCount = super.getFacade().getPeProdUserService().getPeProdUserCount(entity);
		pager.init(recordCount, 10, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<PeProdUser> entityList = super.getFacade().getPeProdUserService()
				.getpeProdUserWithPositionNameAndFullDeptNamePaginatedList(entity);
		// 查询关联门店信息
		for (PeProdUser peProdUser2 : entityList) {
			KonkaMobileSpRelation konkaMobileSpRelation = new KonkaMobileSpRelation();
			konkaMobileSpRelation.setSeller_id(peProdUser2.getId());
			List<KonkaMobileSpRelation> konkaMobileSpRelationList = super.getFacade().getKonkaMobileSpRelationService()
					.getKonkaMobileSpRelationInShopNameList(konkaMobileSpRelation);
			peProdUser2.getMap().put("konkaMobileSpRelationList", konkaMobileSpRelationList);
		}

		request.setAttribute("entityList", entityList);

		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			if (recordCount.intValue() > 20000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
						+ "！');history.back();");
				return null;
			}
			entity.getRow().setCount(recordCount.intValue());
			List<PeProdUser> entityList1 = getFacade().getPeProdUserService()
					.getpeProdUserWithPositionNameAndFullDeptNamePaginatedList(entity);

			for (PeProdUser peProdUser3 : entityList1) {
				KonkaMobileSpRelation konkaMobileSpRelation = new KonkaMobileSpRelation();
				konkaMobileSpRelation.setSeller_id(peProdUser3.getId());
				List<KonkaMobileSpRelation> konkaMobileSpRelationList = super.getFacade()
						.getKonkaMobileSpRelationService()
						.getKonkaMobileSpRelationInShopNameList(konkaMobileSpRelation);
				peProdUser3.getMap().put("konkaMobileSpRelationList", konkaMobileSpRelationList);
			}

			dynaBean.set("excel_all", excel_all);
			request.setAttribute("allList", entityList1);
		}

		if (role_id_eq_10) {
			// 部门列表
			KonkaDept dept = new KonkaDept();
			dept.getMap().put("dept_id", peProdUser.getDept_id());
			List<KonkaDept> peDeptList = super.getFacade().getKonkaDeptService()
					.getKonkaDeptListWithTreeNameForProdUser(dept);
			request.setAttribute("peDeptList", peDeptList);
		}

		request.setAttribute("requestStr", super.serialize(request, "is_del"));
		return mapping.findForward("list");
	}

	/**
	 * 新增促销员
	 * 
	 * @author Liang,HouEn 2014-10-27
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}

		super.setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		// 部门列表
		KonkaDept konka_dept = new KonkaDept();
		konka_dept.getMap().put("dept_id", peProdUser.getDept_id());
		List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService()
				.getKonkaDeptListWithTreeNameForProdUser(konka_dept);
		request.setAttribute("peDeptList", deptInfoList);

		// 学历列表
		KonkaBaseTypeData kbtd = new KonkaBaseTypeData();
		kbtd.setPar_type_id(100007L);
		List<KonkaBaseTypeData> kbtdInfoList = super.getFacade().getKonkaBaseTypeDataService()
				.getKonkaBaseTypeDataList(kbtd);
		request.setAttribute("kbtdInfoList", kbtdInfoList);

		dynaBean.set("position_id", "0");// 默认添加业务员
		dynaBean.set("order_value", "0");// 默认的排序值为0

		PeRoleUser peRoleUser = this.getRoleInfoByThisLogin(request);
		request.setAttribute("peRoleUser", peRoleUser);

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

		// 用户
		PeProdUser PeProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		// 角色与用户
		PeRoleUser peRoleUser = this.getRoleInfoByThisLogin(request);
		if (null == peRoleUser) {
			super.renderJavaScript(response, "alert('用户不具备系统角色！');history.back();");
			return null;
		}
		request.setAttribute("peRoleUser", peRoleUser);

		// 获取登录用户 企业信息
		PeProdUser SxPeProdUser = new PeProdUser();
		SxPeProdUser.setIs_del(0);
		SxPeProdUser.setUser_name(PeProdUser.getUser_name());
		SxPeProdUser = super.getFacade().getPeProdUserService().getPeProdUser(SxPeProdUser);

		// 获取选择的修改用户 企业信息
		PeProdUser entity = new PeProdUser();
		entity.setProd_entp_id(SxPeProdUser.getProd_entp_id());
		entity.setId(Long.valueOf(user_id));

		entity = super.getFacade().getPeProdUserService().getCXYPeProdUser(entity);

		if (null == entity) {
			return this.list(mapping, form, request, response);
		}

		// PeRoleUser _ru = new PeRoleUser();
		// _ru.setUser_id(entity.getId());
		// _ru = super.getFacade().getPeRoleUserService().getPeRoleUser(_ru);
		// request.setAttribute("role_id", _ru.getRole_id());

		super.copyProperties(form, entity);

		entity.setQueryString(super.serialize(request, "id", "mod_id", "tree_param"));

		if (entity.getP_index() != null && entity.getP_index() != -1) {// 回显地区信息
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
					request.setAttribute("province", p_index);
				} else if (p_index.endsWith("00")) {
					request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
					request.setAttribute("city", p_index);
				}
			}
		}

		// 部门列表
		KonkaDept konka_dept = new KonkaDept();
		konka_dept.getMap().put("dept_id", SxPeProdUser.getDept_id());
		// konka_dept.setPar_id(entity.getDept_id());
		List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService()
				.getKonkaDeptListWithTreeNameForProdUser(konka_dept);
		request.setAttribute("peDeptList", deptInfoList);

		// 学历列表
		KonkaBaseTypeData kbtd = new KonkaBaseTypeData();
		kbtd.setPar_type_id(100007L);
		List<KonkaBaseTypeData> kbtdInfoList = super.getFacade().getKonkaBaseTypeDataService()
				.getKonkaBaseTypeDataList(kbtd);
		request.setAttribute("kbtdInfoList", kbtdInfoList);

		PeRoleInfo peRoleInfo = new PeRoleInfo();
		peRoleInfo.setIs_del(0);
		// TODO peRoleUser can be null ,should have handler exception!!!
		peRoleInfo.getMap().put("ge_role_id", peRoleUser.getRole_id().intValue());
		if (peRoleUser.getRole_id() >= 200 && peRoleUser.getRole_id() <= 600) {
			peRoleInfo.getMap().put("is_pe_prod_user", "");// 非康佳专版中人员管理中查询总数中将专卖店的去掉所加的条件
		} else if (peRoleUser.getRole_id() >= 1000 && peRoleUser.getRole_id() <= 1100) {
			peRoleInfo.getMap().put("is_pec_user", "true");// 完美终端用户
		} else {
			peRoleInfo.getMap().put("is_pe_prod_user", "true");// 康佳专版中人员管理中查询总数中将非专卖店的去掉所加的条件
		}
		List<PeRoleInfo> roleInfoList = this.getFacade().getPeRoleInfoService().getPeRoleInfoList(peRoleInfo);
		request.setAttribute("roleInfoList", roleInfoList);

		// 查询关联门店
		KonkaMobileSpRelation konkaMobileSpRelation = new KonkaMobileSpRelation();
		konkaMobileSpRelation.setSeller_id(Long.valueOf(user_id));
		List<KonkaMobileSpRelation> konkaMobileSpRelationList = super.getFacade().getKonkaMobileSpRelationService()
				.getKonkaMobileSpRelationInShopNameList(konkaMobileSpRelation);

		if (konkaMobileSpRelationList.size() > 0) {
			String store_ids = ",";
			for (KonkaMobileSpRelation k : konkaMobileSpRelationList) {
				store_ids = store_ids + k.getShop_id() + ",";
			}
			request.setAttribute("store_ids", store_ids);
			request.setAttribute("shop_change", "1");
		}

		request.setAttribute("konkaMobileSpRelationList", konkaMobileSpRelationList);

		// 查询工作简历
		Attachment attachment = new Attachment();
		attachment.setLink_id(new Long(entity.getId()));
		attachment.setLink_tab("KONKA_PE_PROD_USER");
		attachment.setDel_mark(new Short("0"));
		request.setAttribute("attachment", getFacade().getAttachmentService().getAttachment(attachment));

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

		super.copyProperties(form, entity);

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

			String p_index_name = baseProvinceListFour.getFull_name();

			request.setAttribute("p_index_name", p_index_name);
		}

		// 查询关联门店
		KonkaMobileSpRelation konkaMobileSpRelation = new KonkaMobileSpRelation();
		konkaMobileSpRelation.setSeller_id(Long.valueOf(user_id));
		List<KonkaMobileSpRelation> konkaMobileSpRelationList = super.getFacade().getKonkaMobileSpRelationService()
				.getKonkaMobileSpRelationInShopNameList(konkaMobileSpRelation);
		request.setAttribute("konkaMobileSpRelationList", konkaMobileSpRelationList);

		return mapping.findForward("view");
	}

	/**
	 * 保存促销员信息
	 * 
	 * @author Liang,HouEn 2014-10-30
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String tree_param = (String) dynaBean.get("tree_param");
		String dept_id = (String) dynaBean.get("dept_id");
		String pass_word_old = (String) dynaBean.get("pass_word_old");
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");
		String returnUrl = (String) dynaBean.get("returnUrl");
		String shop_change = (String) dynaBean.get("shop_change");
		String store_ids = (String) dynaBean.get("store_ids");

		String[] select2 = request.getParameterValues("select2");

		// 2014-10-28 新增促销员信息
		String sales_type = (String) dynaBean.get("sales_type");
		String sales_stat = (String) dynaBean.get("sales_stat");
		String work_date = (String) dynaBean.get("work_date");
		String work_age = (String) dynaBean.get("work_age");
		String nation_name = (String) dynaBean.get("nation_name");
		String academic = (String) dynaBean.get("academic");
		String married = (String) dynaBean.get("married");
		String emergency_man = (String) dynaBean.get("emergency_man");
		String emergency_tel = (String) dynaBean.get("emergency_tel");
		String identity_id = (String) dynaBean.get("identity_id");
		String id_valid_date = (String) dynaBean.get("id_valid_date");
		String residence_addr = (String) dynaBean.get("residence_addr");
		String bank_name = (String) dynaBean.get("bank_name");
		String bank_account = (String) dynaBean.get("bank_account");
		String r3_job_id = (String) dynaBean.get("r3_job_id");

		// 获取登录用户 企业信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		PeProdUser entity = new PeProdUser();
		super.copyProperties(entity, form);
		entity.setRole_id("188");
		entity.setProd_entp_id(peProdUser.getProd_entp_id());
		entity.setAdd_e_user_id(peProdUser.getId());

		if (StringUtils.isNotBlank(sales_type)) {
			entity.getMap().put("sales_type", sales_type);
		}
		if (StringUtils.isNotBlank(sales_stat)) {
			entity.getMap().put("sales_stat", sales_stat);
		}
		if (StringUtils.isNotBlank(work_date)) {
			entity.getMap().put("work_date", work_date);
		}
		if (StringUtils.isNotBlank(work_age)) {
			entity.getMap().put("work_age", work_age);
		}
		if (StringUtils.isNotBlank(nation_name)) {
			entity.getMap().put("nation_name", nation_name);
		}
		if (StringUtils.isNotBlank(academic)) {
			entity.getMap().put("academic", academic);
		}
		if (StringUtils.isNotBlank(married)) {
			entity.getMap().put("married", married);
		}
		if (StringUtils.isNotBlank(married)) {
			entity.getMap().put("married", married);
		}
		if (StringUtils.isNotBlank(emergency_man)) {
			entity.getMap().put("emergency_man", emergency_man);
		}
		if (StringUtils.isNotBlank(emergency_tel)) {
			entity.getMap().put("emergency_tel", emergency_tel);
		}
		if (StringUtils.isNotBlank(identity_id)) {
			entity.getMap().put("identity_id", identity_id);
		}
		if (StringUtils.isNotBlank(id_valid_date)) {
			entity.getMap().put("id_valid_date", id_valid_date);
		}

		if (StringUtils.isNotBlank(residence_addr)) {
			entity.getMap().put("residence_addr", residence_addr);
		}
		if (StringUtils.isNotBlank(bank_name)) {
			entity.getMap().put("bank_name", bank_name);
		}
		if (StringUtils.isNotBlank(bank_account)) {
			entity.getMap().put("bank_account", bank_account);
		}

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
		List<KonkaMobileSpRelation> konkaMobileSpRelationList = new ArrayList<KonkaMobileSpRelation>();

		Boolean flag = false;
		String store_ids_new = null;
		if (null != select2 && 0 != select2.length) {
			for (String string : select2) {
				KonkaMobileSpRelation t = new KonkaMobileSpRelation();
				t.setShop_id(Long.valueOf(string));
				konkaMobileSpRelationList.add(t);

				if (StringUtils.isNotBlank(shop_change) && StringUtils.isNotBlank(store_ids)) {
					if (!store_ids.contains("," + string + ",")) {
						flag = true;
					}

					if (store_ids.length() != select2.length + 2) {
						flag = true;
					}
				}
				store_ids_new = store_ids_new + "," + string;
			}

		}

		// 附件处理
		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH, true, 0,
				new int[] { 240 });
		List<Attachment> filesAttachmentList = new ArrayList<Attachment>();
		Attachment filesAttachment = null;
		for (UploadFile uploadFile : uploadFileList) {
			filesAttachment = new Attachment();
			filesAttachment.setFile_name(uploadFile.getFileName());
			filesAttachment.setFile_type(uploadFile.getContentType());
			filesAttachment.setFile_size(new Integer(uploadFile.getFileSize()));
			filesAttachment.setSave_path(uploadFile.getFileSavePath());
			filesAttachment.setSave_name(uploadFile.getFileSaveName());
			filesAttachment.setDel_mark(new Short("0"));
			filesAttachment.setFile_desc(uploadFile.getFormName());
			filesAttachmentList.add(filesAttachment);
		}
		entity.setAttachmentList(filesAttachmentList);

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
			if (r3_job_id != null && !"".equals(r3_job_id)) {
				PeProdUser prodUser2 = new PeProdUser();
				prodUser2.setR3_job_id(r3_job_id);
				count = super.getFacade().getPeProdUserService().getPeProdUserCount(prodUser2);
				if (count > 0) {
					super.renderJavaScript(response, "window.onload=function(){alert('R3人员编码\"" + r3_job_id
							+ "\"重复，请重新输入 ');history.back();}");
					return null;
				}
			}

			DESPlus des = new DESPlus();
			entity.setPass_word(des.encrypt(entity.getPass_word()));
			super.getFacade().getPeProdUserService().createPeProdUserWithShop(entity, konkaMobileSpRelationList);
			super.saveMessage(request, "entity.inserted");
		} else { // 修改促销员信息
			// 校验R3人员编码是否存在
			if (r3_job_id != null && !"".equals(r3_job_id)) {
				boolean r3jobidflag = false;
				PeProdUser prodUser2 = new PeProdUser();
				prodUser2.setR3_job_id(r3_job_id);
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
					super.renderJavaScript(response, "window.onload=function(){alert('R3人员编码\"" + r3_job_id
							+ "\"重复，请重新输入 ');history.back();}");
					return null;
				}

			}

			entity.setId(Long.valueOf(user_id));

			// 如果有变动，新增变更信息
			if (flag || (null == select2 && StringUtils.isNotBlank(shop_change))) {

				PeProdUser p = new PeProdUser();
				p.setId(Long.valueOf(user_id));
				p = super.getFacade().getPeProdUserService().getPeProdUser(p);

				if (null != p)
					entity.setUser_name(p.getUser_name());

				entity.getMap().put("is_change", "1");

				KonkaR3Store krs = new KonkaR3Store();
				krs.getMap().put("store_values", store_ids.substring(1, store_ids.length() - 1));

				List<KonkaR3Store> krsList1 = super.getFacade().getKonkaR3StoreService()
						.getKonkaR3StoreForStoresList(krs);

				if (krsList1.size() > 0 && null != krsList1.get(0)
						&& null != krsList1.get(0).getMap().get("store_names").toString()) {
					entity.getMap().put("old_store_names", krsList1.get(0).getMap().get("store_names"));
				} else {
					entity.getMap().put("old_store_names", "");
				}

				if (store_ids_new != null) {
					krs.getMap().put("store_values", store_ids_new);
					List<KonkaR3Store> krsList2 = super.getFacade().getKonkaR3StoreService()
							.getKonkaR3StoreForStoresList(krs);
					if (krsList2.size() > 0)
						entity.getMap().put("new_store_names", krsList2.get(0).getMap().get("store_names"));

				}

				entity.getMap().put("add_user_id", peProdUser.getId());
				entity.getMap().put("add_user_name", peProdUser.getUser_name());
				entity.getMap().put("add_user_job_id", peProdUser.getJob_id());

			}

			if (!StringUtils.equals(pass_word_old, entity.getPass_word())) {
				DESPlus des = new DESPlus();
				entity.setPass_word(des.encrypt(entity.getPass_word()));
			}
			entity.setModify_date(new Date());
			entity.setModify_user_id(peProdUser.getId());
			super.getFacade().getPeProdUserService().modifyPeProdUserWithShop(entity, konkaMobileSpRelationList);
			super.saveMessage(request, "entity.updated");
		}

		if (StringUtils.isNotBlank(returnUrl)) {
			// response.sendRedirect(URLDecoder.decode(returnUrl,
			// Constants.SYS_ENCODING));
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

		// 如果user_id 只有促销员一个角色，直接删除这个用户
		// 如果user_id 除了促销员之外 还有其他角色，则移除他的促销员职务
		// 促销员的role_id=188
		PeRoleUser _PeRoleUser = new PeRoleUser();
		_PeRoleUser.setUser_id(Long.valueOf(user_id));
		Long recordCount = super.getFacade().getPeRoleUserService().getPeRoleUserCount(_PeRoleUser);

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		if (recordCount == 1L) {

			// 取人员信息
			PeProdUser p = new PeProdUser();
			p.setId(Long.valueOf(user_id));
			p = super.getFacade().getPeProdUserService().getPeProdUser(p);

			if (null != p) {
				KonkaStoreUserCInfo k = new KonkaStoreUserCInfo();
				k.setAdd_date(new Date());
				k.setAdd_user_id(ui.getId());
				k.setAdd_user_job_id(ui.getJob_id());
				k.setAdd_user_name(ui.getUser_name());
				k.setC_type(50);
				k.setChange_info("该促销员被停用!");
				k.setSs_id(p.getId());
				k.setSs_name(p.getUser_name());
				super.getFacade().getKonkaStoreUserCInfoService().createKonkaStoreUserCInfo(k);
			}

			PeProdUser entity = new PeProdUser();
			entity.setId(Long.valueOf(user_id));
			entity.getMap().put("cxy_delete", "1");
			super.getFacade().getPeProdUserService().removePeProdUser(entity);
		} else {

			// 取人员信息
			PeProdUser p = new PeProdUser();
			p.setId(Long.valueOf(user_id));
			p = super.getFacade().getPeProdUserService().getPeProdUser(p);

			if (null != p) {
				KonkaStoreUserCInfo k = new KonkaStoreUserCInfo();
				k.setAdd_date(new Date());
				k.setAdd_user_id(ui.getId());
				k.setAdd_user_job_id(ui.getJob_id());
				k.setAdd_user_name(ui.getUser_name());
				k.setC_type(50);
				k.setChange_info("该促销员被停用!");
				k.setSs_id(p.getId());
				k.setSs_name(p.getUser_name());
				super.getFacade().getKonkaStoreUserCInfoService().createKonkaStoreUserCInfo(k);
			}

			PeRoleUser cxy = new PeRoleUser();
			cxy.setUser_id(Long.valueOf(user_id));
			cxy.setRole_id(188L);
			cxy.getMap().put("role_id_and_user_id", true);
			super.getFacade().getPeRoleUserService().removePeRoleUser(cxy);
		}

		saveMessage(request, "entity.deleted");

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
		// end
		return forward;
	}

	public ActionForward validateName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		StringBuffer oper = new StringBuffer("{\"result\":");
		String user_name = (String) dynaBean.get("user_name");
		PeProdUser entity = new PeProdUser();
		entity.setUser_name(user_name);
		entity.setIs_del(0);
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

	public ActionForward ajaxSelectShopList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String shop_province = (String) dynaBean.get("shop_province");
		String shop_city = (String) dynaBean.get("shop_city");
		String shop_country = (String) dynaBean.get("shop_country");
		String shop_town = (String) dynaBean.get("shop_town");
		String dept_id = (String) dynaBean.get("dept_id");
		String shop_name_like = (String) dynaBean.get("shop_name_like");

		KonkaR3Store entpShop = new KonkaR3Store();
		entpShop.setIs_del(0);
		if (StringUtils.isNotEmpty(shop_province)) {
			entpShop.getMap().put("p_index_like", StringUtils.substring(shop_province, 0, 2));
		}
		if (StringUtils.isNotEmpty(shop_city)) {
			entpShop.getMap().put("p_index_like", StringUtils.substring(shop_city, 0, 4));
		}
		if (StringUtils.isNotEmpty(shop_country)) {
			entpShop.getMap().put("p_index_like", shop_country);
		}
		if (StringUtils.isNotEmpty(shop_town)) {
			entpShop.getMap().put("p_index_like", shop_town);
		}
		if (StringUtils.isNotEmpty(dept_id)) {
			KonkaDept fgs = super.getKonkaDeptForFgs(Long.valueOf(dept_id));
			if (fgs != null && fgs.getDept_id() != null) {
				entpShop.setDept_id(fgs.getDept_id());
			}
		} else {
			PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
			KonkaDept fgs = super.getKonkaDeptForFgs(ui.getDept_id());
			if (fgs != null && fgs.getDept_id() != null) {
				entpShop.setDept_id(fgs.getDept_id());
			}
		}
		entpShop.setStore_name(shop_name_like);

		Long count = super.getFacade().getKonkaR3StoreService().getKonkaR3StoreCount(entpShop);
		// 查询结果过大
		if (500L <= count) {
			super.renderJson(response, "{\"status\":\"0\"}");
			return null;
		}

		// 查询结果为空
		List<KonkaR3Store> entpShopList = super.getFacade().getKonkaR3StoreService().getKonkaR3StoreList(entpShop);
		if (0 == entpShopList.size()) {
			super.renderJson(response, "{\"status\":\"-1\"}");
			return null;
		}

		// 遍历结果
		StringBuffer sb = new StringBuffer("{\"status\":\"1\",\"data\":[");
		for (KonkaR3Store es : entpShopList) {
			sb.append("{\"shop_id\":\"").append(es.getStore_id()).append("\", \"store_name\":\"")
					.append(es.getStore_name()).append("\"},");
		}
		sb.delete(sb.length() - 1, sb.length());
		sb.append("]}");
		logger.info("------json---->{}", sb.toString());
		super.renderJson(response, sb.toString());
		return null;
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
}
