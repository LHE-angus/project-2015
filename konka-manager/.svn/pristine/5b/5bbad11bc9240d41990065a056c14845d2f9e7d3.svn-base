package com.ebiz.mmt.web.struts.manager.spgl;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.ebiz.mmt.domain.EcBaseCardLevel;
import com.ebiz.mmt.domain.EcBaseCardNo;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcUserScoreRec;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.mmt.web.util.ExcelUtil;
import com.ebiz.ssi.web.struts.bean.Pager;

public class EcUserForFgsAction extends BaseAction {

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
		String name_like = (String) dynaBean.get("name_like");
		String is_del = (String) dynaBean.get("is_del");
		String dept_id = (String) dynaBean.get("dept_id");
		String excel_all = (String) dynaBean.get("excel_all");
		String is_act = (String) dynaBean.get("is_act");
		String card_no_like = (String) dynaBean.get("card_no_like");
		String department_like = (String) dynaBean.get("department_like");
		String card_level_name = (String) dynaBean.get("card_level_name");
		String user_type = (String) dynaBean.get("user_type");
		String add_time_start = (String) dynaBean.get("add_time_start");
		String add_time_end = (String) dynaBean.get("add_time_end");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		request.setAttribute("peRoleUser", peRoleUser);

		EcUser entity = new EcUser();

		if (StringUtils.isNotBlank(user_type)) {
			entity.setUser_type(Integer.valueOf(user_type));
		}
		if (StringUtils.isNotBlank(card_level_name)) {
			entity.getMap().put("card_level_name", card_level_name);
		}
		if (StringUtils.isNotBlank(name_like)) {
			entity.getMap().put("name_like", name_like);
		}
		if (StringUtils.isNotBlank(card_no_like)) {
			entity.getMap().put("card_no_like", card_no_like);
		}
		if (StringUtils.isNotBlank(department_like)) {
			entity.getMap().put("department_like", department_like);
		}
		if (StringUtils.isNotBlank(add_time_start)) {
			entity.getMap().put("add_time_start", add_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_time_end)) {
			entity.getMap().put("add_time_end", add_time_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
		}
		if (StringUtils.isNotBlank(is_act)) {
			entity.setIs_act(Integer.valueOf(is_act));
		}

		if (StringUtils.isNotBlank(is_del)) {
			entity.setIs_del(Integer.valueOf(is_del));
		} else {
			entity.setIs_del(0);
		}

		entity.setUser_type(1);// 分公司工卡会员
		entity.setIs_epp_fgs(1);// EPP分公司标识

		// rold_id = 25 是内购会员管理员，可以看到所有会员
		if (peRoleUser.getRole_id() != 10 && peRoleUser.getRole_id() != 25
		        && peRoleUser.getRole_id().intValue() != 1001) { // 非系统管理员只能看到自己部门下的用户，管理员能看到所有用户
			if (peRoleUser.getRole_id() < 200 || peRoleUser.getRole_id() >= 300) {
				entity.getMap().put("dept_id_in", peProdUser.getDept_id());
				entity.getMap().put("user_id", peProdUser.getId());
			}
		}

		Long recordCount = super.getFacade().getEcUserService().getEcUserCount(entity);
		pager.init(recordCount, 10, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcUser> entityList = super.getFacade().getEcUserService()
		        .getEcUserWithPositionNameAndFullDeptNamePaginatedList(entity);

		request.setAttribute("entityList", entityList);
		for (EcUser ecUser : entityList) {
			String total_score = super.getFacade().getEcUserScoreRecService().getEcUserScoreRecForUserTotalScore(
			        ecUser.getId());
			// 奖励积分
			String jlScore1 = super.getFacade().getEcUserScoreRecService().getEcUserScoreRecForUserJLScore(
			        ecUser.getId());
			jlScore1 = String.valueOf(new BigDecimal(jlScore1).setScale(2, BigDecimal.ROUND_HALF_UP));
			ecUser.getMap().put("jlScore", jlScore1);
			// String da_score = (String) ecUser.getMap().get("da_score");
			// if (null == da_score) {
			// da_score = "0";
			// }
			// BigDecimal wa_score = new BigDecimal(total_score).subtract(new
			// BigDecimal(da_score));
			ecUser.getMap().put("wa_score", total_score);
			// 查找当前卡号的级别
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("leve", 1);
			searchLeve(map, ecUser.getCard_no());
			ecUser.getMap().put("leve", map.get("leve"));
		}

		EcBaseCardLevel level = new EcBaseCardLevel();
		List<EcBaseCardLevel> levelList = super.getFacade().getEcBaseCardLevelService().getEcBaseCardLevelList(level);
		request.setAttribute("levelList", levelList);

		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			if (recordCount.intValue() > 5000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
				        + "！');history.back();");
				return null;
			}
			entity.getRow().setCount(recordCount.intValue());
			List<EcUser> entityList1 = super.getFacade().getEcUserService()
			        .getEcUserWithPositionNameAndFullDeptNamePaginatedList(entity);
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

		String name_like = (String) dynaBean.get("name_like");
		String is_del = (String) dynaBean.get("is_del");
		String dept_id = (String) dynaBean.get("dept_id");
		String is_act = (String) dynaBean.get("is_act");
		String card_no_like = (String) dynaBean.get("card_no_like");
		String department_like = (String) dynaBean.get("department_like");
		String card_level_name = (String) dynaBean.get("card_level_name");
		String user_type = (String) dynaBean.get("user_type");
		String add_time_start = (String) dynaBean.get("add_time_start");
		String add_time_end = (String) dynaBean.get("add_time_end");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		request.setAttribute("peRoleUser", peRoleUser);

		EcUser entity = new EcUser();

		if (StringUtils.isNotBlank(user_type)) {
			entity.setUser_type(Integer.valueOf(user_type));
		}
		if (StringUtils.isNotBlank(card_level_name)) {
			entity.getMap().put("card_level_name", card_level_name);
		}
		if (StringUtils.isNotBlank(name_like)) {
			entity.getMap().put("name_like", name_like);
		}
		if (StringUtils.isNotBlank(card_no_like)) {
			entity.getMap().put("card_no_like", card_no_like);
		}
		if (StringUtils.isNotBlank(department_like)) {
			entity.getMap().put("department_like", department_like);
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
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

		if (peRoleUser.getRole_id() != 10 && peRoleUser.getRole_id() != 25
		        && peRoleUser.getRole_id().intValue() != 1001) { // 非系统管理员只能看到自己部门下的用户，管理员能看到所有用户
			if (peRoleUser.getRole_id() < 200 || peRoleUser.getRole_id() >= 300) {
				entity.getMap().put("dept_id_in", peProdUser.getDept_id());
				entity.getMap().put("user_id", peProdUser.getId());
			}
		}

		Long recordCount = super.getFacade().getEcUserService().getEcUserCount(entity);
		entity.getRow().setFirst(new Integer(0));
		entity.getRow().setCount(new Integer(recordCount.intValue()));

		List<EcUser> entityList = super.getFacade().getEcUserService()
		        .getEcUserWithPositionNameAndFullDeptNamePaginatedList(entity);

		request.setAttribute("entityList", entityList);
		for (EcUser ecUser : entityList) {
			String total_score = super.getFacade().getEcUserScoreRecService().getEcUserScoreRecForUserTotalScore(
			        ecUser.getId());
			ecUser.getMap().put("wa_score", total_score);
			String jlScore1 = super.getFacade().getEcUserScoreRecService().getEcUserScoreRecForUserJLScore(
			        ecUser.getId());
			jlScore1 = String.valueOf(new BigDecimal(jlScore1).setScale(2, BigDecimal.ROUND_HALF_UP));
			ecUser.getMap().put("jlScore", jlScore1);
		}

		EcBaseCardLevel level = new EcBaseCardLevel();
		List<EcBaseCardLevel> levelList = super.getFacade().getEcBaseCardLevelService().getEcBaseCardLevelList(level);
		request.setAttribute("levelList", levelList);

		ExcelUtil e = new ExcelUtil();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		int r = 0;
		e.setWorkbook(workbook);
		e.setSheet(sheet);
		e.createRow(r);
		e.setCell(0, "序号");
		e.setCell(1, "工卡号");
		e.setCell(2, "登录名");
		e.setCell(3, "姓名");
		e.setCell(4, "注册时间");
		e.setCell(5, "类型");
		e.setCell(6, "部门");
		e.setCell(7, "会员等级");
		e.setCell(8, "付款积分");
		e.setCell(9, "电话");
		e.setCell(10, "审核状态");
		e.setCell(11, "审核时间");
		e.setCell(12, "R3编码");
		e.setCell(13, "所在分公司");
		e.setCell(14, "已使用积分");
		e.setCell(15, "未使用积分");
		e.setCell(16, "已发放佣金");
		e.setCell(17, "未发放佣金");
		e.setCell(18, "已使用购物券");
		e.setCell(19, "未使用购物券");
		e.setCell(20, "审核人");
		e.setCell(21, "奖励积分");
		for (EcUser pds : entityList) {
			r++;
			e.createRow(r);
			e.setCell(0, r);
			e.setCell(1, pds.getCard_no() == null ? "" : pds.getCard_no());
			e.setCell(2, pds.getUser_name() == null ? "" : pds.getUser_name());
			e.setCell(3, pds.getReal_name() == null ? "" : pds.getReal_name());
			// 添加时间
			if (pds.getAdd_date() != null) {
				e.setCell(4, new SimpleDateFormat("yyyy-MM-dd").format(pds.getAdd_date()));
			} else {
				e.setCell(4, "");
			}
			String type = "";
			if (null != pds.getUser_type() && pds.getUser_type() == 1) {
				type = "工卡会员";
			} else if (null != pds.getUser_type() && pds.getUser_type() == 2) {
				type = "触网会员";
			}
			e.setCell(5, type);
			String department = "";
			if (null != pds.getDepartment()) {
				department = pds.getDepartment();
			}
			e.setCell(6, department);
			String level_name = "";
			if (null != pds.getCard_no()) {
				EcBaseCardNo ecn = new EcBaseCardNo();
				ecn.setCard_no(pds.getCard_no());
				List<EcBaseCardNo> ecnList = super.getFacade().getEcBaseCardNoService().getEcBaseCardNoList(ecn);
				if (ecnList.size() == 1) {
					EcBaseCardNo ee = ecnList.get(0);
					if (null != ee.getCard_level()) {
						EcBaseCardLevel el = new EcBaseCardLevel();
						el.setCard_level(ee.getCard_level());
						el = super.getFacade().getEcBaseCardLevelService().getEcBaseCardLevel(el);
						if (null != el.getCard_level_name()) {
							level_name = el.getCard_level_name();
						}
					}
				}
			}

			e.setCell(7, level_name);
			BigDecimal pay_integral = (BigDecimal) pds.getMap().get("pay_integral");// 物流费用
			if (pay_integral == null) {
				pay_integral = new BigDecimal("0.0");
			}
			e.setCell(8, pay_integral.toString());
			e.setCell(9, pds.getLink_phone());
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
			e.setCell(10, state);
			if (pds.getAudit_date() != null) {
				e.setCell(11, new SimpleDateFormat("yyyy-MM-dd").format(pds.getAudit_date()));
			} else {
				e.setCell(11, "");
			}

			// r3编码
			String r3_code = "";
			if (null != pds.getMap().get("r3_code")) {
				r3_code = String.valueOf(pds.getMap().get("r3_code"));
			}
			e.setCell(12, r3_code);
			// 所在分公司
			String dept_name = "";
			if (null != pds.getMap().get("dept_name")) {
				dept_name = String.valueOf(pds.getMap().get("dept_name"));
			}
			e.setCell(13, dept_name);
			// 已使用积分
			String da_score = "0.00";
			if (null != pds.getMap().get("da_score")) {
				da_score = String.valueOf(pds.getMap().get("da_score"));
			}
			e.setCell(14, da_score);
			// 未使用积分
			String wa_score = "0.00";
			if (null != pds.getMap().get("wa_score")) {
				wa_score = String.valueOf(pds.getMap().get("wa_score")) != "0" ? String.valueOf(pds.getMap().get(
				        "wa_score")) : "0.00";
			}
			e.setCell(15, wa_score);
			// 已发放佣金
			String da_rebates = "0.00";
			if (null != pds.getMap().get("da_rebates")) {
				da_rebates = String.valueOf(pds.getMap().get("da_rebates"));
			}
			e.setCell(16, da_rebates);
			// 未发放佣金
			String wa_rebates = "0.00";
			if (null != pds.getMap().get("wa_rebates")) {
				wa_rebates = String.valueOf(pds.getMap().get("wa_rebates"));
			}
			e.setCell(17, wa_rebates);
			// 已使用购物卷
			String da_price = "0.00";
			if (null != pds.getMap().get("da_price")) {
				da_price = String.valueOf(pds.getMap().get("da_price"));
			}
			e.setCell(18, da_price);
			// 未使用购物卷
			String wa_price = "0.00";
			if (null != pds.getMap().get("wa_price")) {
				wa_price = String.valueOf(pds.getMap().get("wa_price"));
			}
			e.setCell(19, wa_price);

			String chargeman = "";
			if (null != pds.getChargeman()) {
				chargeman = pds.getChargeman();
			}
			e.setCell(20, chargeman);
			// 奖励积分
			String jlScore = "0.00";
			if (null != pds.getMap().get("jlScore")) {
				jlScore = String.valueOf(pds.getMap().get("jlScore"));
			}
			e.setCell(21, jlScore);
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

		super.setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_10 = false; // 系统管理员
		boolean role_id_eq_30 = false; // 分公司管理员
		boolean role_id_btw_200_600 = false;
		boolean role_id_btw_300_400 = false;
		boolean role_id_btw_1000_1100 = false;
		for (PeRoleUser cur : peRoleUserList) {
			if (cur.getRole_id() == 10L || cur.getRole_id() == 25 || cur.getRole_id().intValue() == 1001) {
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

		List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService().getKonkaDeptListWithTreeNameForProdUser(
		        konka_dept);
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

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

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

		if (null != entity.getC_id()) {
			PeProdUser pp = new PeProdUser();
			pp.setId(entity.getC_id());
			pp = super.getFacade().getPeProdUserService().getPeProdUser(pp);
			if (pp != null)
				dynaBean.set("c_name", pp.getReal_name());
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

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_10 = false; // 系统管理员
		boolean role_id_eq_30 = false; // 分公司管理员
		boolean role_id_btw_200_600 = false;
		boolean role_id_btw_300_400 = false;
		boolean role_id_btw_1000_1100 = false;
		for (PeRoleUser cur : peRoleUserList) {
			if (cur.getRole_id() == 10L || cur.getRole_id() == 25 || cur.getRole_id().intValue() == 1001) {
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
			baseProvinceListFour = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
			        baseProvinceListFour);
			if (null != baseProvinceListFour) {
				String p_index_name = baseProvinceListFour.getFull_name();
				request.setAttribute("p_index_name", p_index_name);
			}
		}

		return mapping.findForward("view");
	}

	public ActionForward view1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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

		/*
		 * PshowOrder pso = new PshowOrder(); PshowOrdeDetails psoDetail = new
		 * PshowOrdeDetails(); KonkaBcompPd kkPd = new KonkaBcompPd(); if(null
		 * != entity){ pso.setOrder_user_id(pso.getId()); List<PshowOrder>
		 * psoList =
		 * super.getFacade().getPshowOrderService().getPshowOrderList(pso); for
		 * (PshowOrder pso_1 : psoList) { psoDetail.setOrder_id(pso.getId());
		 * psoDetail =
		 * super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetails
		 * (psoDetail); if(null != psoDetail){ kkPd.setId(psoDetail.getPd_id());
		 * kkPd =
		 * super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kkPd);
		 * request.setAttribute("Pd_name", kkPd.getPd_name());
		 * request.setAttribute("Pd_name", kkPd.getPd_name()); } } }
		 */

		if (null != entity) {
			List<PshowOrdeDetails> psoDetail = super.getFacade().getPshowOrdeDetailsService()
			        .getPshowOrdeDetailsFromUser(entity.getId());
			request.setAttribute("entityList", psoDetail);
		}

		return new ActionForward("/../manager/spgl/EcUserForFgs/view1.jsp");
	}

	public ActionForward view2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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

		/*
		 * PshowOrder pso = new PshowOrder(); PshowOrdeDetails psoDetail = new
		 * PshowOrdeDetails(); KonkaBcompPd kkPd = new KonkaBcompPd(); if(null
		 * != entity){ pso.setOrder_user_id(pso.getId()); List<PshowOrder>
		 * psoList =
		 * super.getFacade().getPshowOrderService().getPshowOrderList(pso); for
		 * (PshowOrder pso_1 : psoList) { psoDetail.setOrder_id(pso.getId());
		 * psoDetail =
		 * super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetails
		 * (psoDetail); if(null != psoDetail){ kkPd.setId(psoDetail.getPd_id());
		 * kkPd =
		 * super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kkPd);
		 * request.setAttribute("Pd_name", kkPd.getPd_name());
		 * request.setAttribute("Pd_name", kkPd.getPd_name()); } } }
		 */

		if (null != entity) {
			List<PshowOrdeDetails> psoDetail = super.getFacade().getPshowOrdeDetailsService()
			        .getPshowOrdeDetailsStatusFromUser(entity.getId());
			request.setAttribute("entityList", psoDetail);
		}

		return new ActionForward("/../manager/spgl/EcUserForFgs/view2.jsp");
	}

	public ActionForward view3(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		String user_id = (String) dynaBean.get("user_id");

		if (!GenericValidator.isLong(user_id)) {
			return this.list(mapping, form, request, response);
		}

		EcUser ecUser = new EcUser();
		ecUser.setId(Long.valueOf(user_id));
		ecUser = super.getFacade().getEcUserService().getEcUser(ecUser);

		dynaBean.set("dept_id", ecUser.getDept_id() == null ? "" : ecUser.getDept_id());

		EcUserScoreRec entity = new EcUserScoreRec();
		entity.setUser_id(ecUser.getId());
		Long recordCount = super.getFacade().getEcUserScoreRecService().getEcUserScoreRecCount(entity);
		pager.setPageSize(recordCount.intValue());
		pager.init(recordCount, 10000, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<EcUserScoreRec> entityList = super.getFacade().getEcUserScoreRecService().getEcUserScoreRecPaginatedList(
		        entity);
		request.setAttribute("entityList", entityList);

		request.setAttribute("totalScore", super.getFacade().getEcUserScoreRecService()
		        .getEcUserScoreRecForUserTotalScore(ecUser.getId()));

		// 付款积分
		String payTotalScore = super.getFacade().getEcUserScoreRecService().getEcUserScoreRecForPayJf(ecUser.getId());
		request.setAttribute("payTotalScore", new BigDecimal(payTotalScore).setScale(2, BigDecimal.ROUND_HALF_UP));

		// 奖励积分
		String jlScore = super.getFacade().getEcUserScoreRecService().getEcUserScoreRecForUserJLScore(ecUser.getId());
		request.setAttribute("jlScore", new BigDecimal(jlScore).setScale(2, BigDecimal.ROUND_HALF_UP));

		// 已消费积分
		String xfScore = super.getFacade().getEcUserScoreRecService().getEcUserScoreRecForUserXFScore(ecUser.getId());
		request.setAttribute("xfScore", xfScore);
		logger.info("payTotalScore--------->", payTotalScore);// 取整

		PshowOrdeDetails orderDetail = new PshowOrdeDetails();
		orderDetail.getRow().setFirst(0);
		orderDetail.getRow().setCount(20);
		orderDetail.getMap().put("user_id", ecUser.getId());
		orderDetail.getMap().put("state_in", new Integer[] { 60 });
		orderDetail.getMap().put("order_from", ecUser.getUser_type());
		List<PshowOrdeDetails> orderDetailList = super.getFacade().getPshowOrdeDetailsService()
		        .getPshowOrdeDetailsForFgsPaginatedList(orderDetail);
		request.setAttribute("orderDetailList", orderDetailList);
		if (null != orderDetailList && orderDetailList.size() > 0) {
			for (PshowOrdeDetails pshowOrdeDetails : orderDetailList) {
				if (null != pshowOrdeDetails.getIntegral()) {
					pshowOrdeDetails.getMap().put("jl_jf",
					        pshowOrdeDetails.getIntegral().setScale(2, BigDecimal.ROUND_HALF_UP));
				}
			}
		}
		return new ActionForward("/../manager/spgl/EcUserForFgs/view3.jsp");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String dept_id = (String) dynaBean.get("dept_id");
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");
		String returnUrl = (String) dynaBean.get("returnUrl");
		String card_no = (String) dynaBean.get("card_no");

		// 获取登录用户 企业信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		EcUser entity = new EcUser();
		super.copyProperties(entity, form);

		if (entity.getIs_xx_user() == null) {
			entity.setIs_xx_user(0);
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
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
		if (!GenericValidator.isLong(user_id)) {// 创建用户
			entity.setAdd_e_user_id(peProdUser.getId());
			entity.setIs_epp_fgs(1);
			EcUser prodUser = new EcUser();
			prodUser.setUser_name(entity.getUser_name());
			// prodUser.setIs_del(0);

			Long count = super.getFacade().getEcUserService().getEcUserCount(prodUser);
			if (count != 0) {
				super.renderJavaScript(response, "window.onload=function(){alert('用户名\"" + entity.getUser_name()
				        + "\"与数据库中重复，请重新填写 ');history.back();}");
				return null;
			}
			if (StringUtils.isNotBlank(card_no)) {
				EcUser ee = new EcUser();
				ee.setCard_no(card_no);
				Long count1 = super.getFacade().getEcUserService().getEcUserCount(ee);
				if (count1 > 0) {
					super.renderJavaScript(response, "window.onload=function(){alert('该会员卡号已经被绑定！');history.back();}");
					return null;
				}
			}

			DESPlus des = new DESPlus();
			entity.setPass_word(des.encrypt("1"));
			entity.setPay_pwd(null);
			super.getFacade().getEcUserService().createEcUserForFgs(entity);
			super.saveMessage(request, "entity.inserted");
		} else {
			entity.setId(Long.valueOf(user_id));

			if (StringUtils.isNotBlank(card_no)) {
				EcUser ec = new EcUser();
				ec.setId(Long.valueOf(user_id));
				ec = super.getFacade().getEcUserService().getEcUser(ec);
				if (null != ec.getCard_no()) {
					if (!ec.getCard_no().equals(card_no)) {
						EcUser ee = new EcUser();
						ee.setCard_no(card_no);
						Long count = super.getFacade().getEcUserService().getEcUserCount(ee);
						if (count > 0) {
							super.renderJavaScript(response,
							        "window.onload=function(){alert('该会员卡号已经被绑定！');history.back();}");
							return null;
						}
					}
				} else {
					EcUser ee = new EcUser();
					ee.setCard_no(card_no);
					Long count = super.getFacade().getEcUserService().getEcUserCount(ee);
					if (count > 0) {
						super.renderJavaScript(response,
						        "window.onload=function(){alert('该会员卡号已经被绑定！');history.back();}");
						return null;
					}
				}
			}

			// super.getFacade().getEcUserService().modifyPeProdUser(entity);
			super.getFacade().getEcUserService().modifyEcUserWithMultiRoleUser(entity);
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

		String fgs_id = (String) dynaBean.get("fgs_id");
		String real_name_like = (String) dynaBean.get("real_name_like");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String r3_code = (String) dynaBean.get("r3_code");
		if (!GenericValidator.isLong(fgs_id)) {
			return null;
		}

		KonkaDept t = new KonkaDept();
		t.setDept_id(Long.valueOf(fgs_id));
		t = super.getFacade().getKonkaDeptService().getKonkaDept(t);

		if (null == t || t.getDept_sn() == null) {
			return null;
		}

		PeProdUser entity = new PeProdUser();
		entity.getMap().put("fgs_id_eq", fgs_id);
		entity.getMap().put("id_not_in", true);
		entity.getMap().put("customer_name_like", customer_name_like);
		entity.getMap().put("r3_code", r3_code);
		entity.getMap().put("real_name_like", real_name_like);
		entity.setIs_del(0);
		List<PeProdUser> entityList = super.getFacade().getPeProdUserService().getPeProdUserForCidList(entity);
		request.setAttribute("entityList", entityList);

		return new ActionForward("/../manager/spgl/EcUserForFgs/list-customer.jsp");
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

		if (null != entity.getC_id()) {
			PeProdUser pp = new PeProdUser();
			pp.setId(entity.getC_id());
			pp = super.getFacade().getPeProdUserService().getPeProdUser(pp);
			if (pp != null)
				dynaBean.set("c_name", pp.getReal_name());
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

		if (entity.getEcBaseCardLevel() != null) {
			request.setAttribute("level_name", entity.getEcBaseCardLevel().getCard_level_name());
		}
		request.setAttribute("role_id_eq_10", role_id_eq_10);
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		return new ActionForward("/../manager/spgl/EcUserForFgs/audit.jsp");
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
			super.getFacade().getEcUserService().createEcUserAndSendEmail2(entity, mailSender, mailForm, senderName,
			        request);
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

	// 通过发卡人取的下级会员
	public ActionForward memberList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String user_name_like = (String) dynaBean.get("user_name_like");
		String card_no_like = (String) dynaBean.get("card_no_like");
		String link_phone_like = (String) dynaBean.get("link_phone_like");
		String card_no = (String) dynaBean.get("card_no");// 上级卡号
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		EcUser entity = new EcUser();
		if (StringUtils.isNotBlank(card_no)) {
			entity.getMap().put("card_sender", card_no);
			dynaBean.set("card_sender", card_no);
		}
		if (StringUtils.isNotBlank(user_name_like)) {
			entity.getMap().put("user_name_like", user_name_like);
			dynaBean.set("user_name_like", user_name_like);
		}
		if (StringUtils.isNotBlank(card_no_like)) {
			entity.getMap().put("card_no_like", card_no_like);
			dynaBean.set("card_no_like", card_no_like);
		}
		if (StringUtils.isNotBlank(link_phone_like)) {
			entity.getMap().put("link_phone_like", link_phone_like);
			dynaBean.set("link_phone_like", link_phone_like);
		}

		Long recordCount = super.getFacade().getEcUserService().getSubEcUserByUserNameCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		this.copyProperties(dynaBean, entity);
		List<EcUser> entityList = super.getFacade().getEcUserService().getSubEcUserByUserNameList(entity);

		for (EcUser ecUser2 : entityList) {
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("leve", 1);
			searchLeve(map, ecUser2.getCard_no());
			ecUser2.getMap().put("leve", map.get("leve"));
		}
		request.setAttribute("entityList", entityList);

		// EcBaseCardLevel el = new EcBaseCardLevel();
		// List<EcBaseCardLevel> elList =
		// super.getFacade().getEcBaseCardLevelService().getEcBaseCardLevelList(el);
		// request.setAttribute("elList", elList);
		// request.setAttribute("sybDeptInfoList",
		// super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		return new ActionForward("/../manager/spgl/EcUserForFgs/memberList.jsp");
	}

	// 根据卡号查级别
	private void searchLeve(Map<String, Integer> map, String cardno) {
		EcBaseCardNo card = new EcBaseCardNo();
		card.setCard_no(cardno);// 当前人卡号
		// 找上级发卡人
		card = super.getFacade().getEcBaseCardNoService().getEcBaseCardNo(card);
		// 上级人
		if (null != card && null != card.getCard_sender()) {
			EcUser ecuser = new EcUser();
			ecuser.setCard_no(card.getCard_sender());
			// 上级的发卡人信息
			ecuser = super.getFacade().getEcUserService().getEcUser(ecuser);
			if (null != ecuser && null != ecuser.getCard_no()) {
				cardno = ecuser.getCard_no();
				map.put("leve", map.get("leve") + 1);
				searchLeve(map, cardno);
			}

		}
	}
}
