package com.ebiz.mmt.web.struts.customer;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.http.impl.cookie.DateUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.ChannelDataImport;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaSalesDept;
import com.ebiz.mmt.domain.OperLog;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.TaskPara;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * 订单明细包括分公司对客户的订单明细 ,也包括总部对分公司的订单明细,大客户的订单明细. 这些明细数据可以用于做单据统计用
 * 
 */
public class ChannelDataImportAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 1.检查当前菜单连接是否有权限
		// if (null == super.checkUserModPopeDom(form, request, "0")) {
		// return super.checkPopedomInvalid(request, response);
		// }

		// 2.导航信息
		setNaviStringToRequestScope(form, request);

		// 3.条件过滤
		DynaBean dynaBean = (DynaBean) form;
		String salesCode = (String) dynaBean.get("salesCode");
		String keyword = (String) dynaBean.get("keyword");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String column_8_like = (String) dynaBean.get("column_8_like");
		// 单据类型
		String column_9 = (String) dynaBean.get("column_9");

		// 订单日期
		String s_date = (String) dynaBean.get("s_date");
		String e_date = (String) dynaBean.get("e_date");

		// 凭证日期
		String startDate1 = (String) dynaBean.get("startDate1");
		String endDate1 = (String) dynaBean.get("endDate1");

		// 判断是否是第一次查询
		String is_first = (String) dynaBean.get("is_first");

		SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd");

		// 是否excel
		String excel_all = (String) dynaBean.get("excel_all");

		String md_name_like = (String) dynaBean.get("md_name_like");// 机型

		// 判断是否从客户管理里面跳入进来的
		String is_kh = (String) dynaBean.get("is_kh");
		dynaBean.set("is_kh", is_kh);

		PeProdUser ui = new PeProdUser();
		ui = (PeProdUser) request.getSession().getAttribute(Constants.CUSTOMER_USER_INFO);

		ChannelDataImport entity = new ChannelDataImport();
		entity.getMap().put("count1", true);
		entity.setColumn_25(salesCode);
		// entity.set

		if (StringUtils.isNotBlank(ui.getCust_id().toString())) {
			entity.getMap().put("shop_cust_id", ui.getCust_id());
		}

		if (StringUtils.isNotBlank(keyword)) {
			entity.getMap().put("keyword", keyword);
		}
		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like);
		}
		if (StringUtils.isNotBlank(column_9)) {
			entity.setColumn_9(column_9.toUpperCase());
		}
		if (StringUtils.isNotBlank(column_8_like)) {
			entity.getMap().put("column_8_like", column_8_like);
		}
		if (StringUtils.isNotBlank(s_date)) {
			entity.getMap().put("s_date", s_date + " 00:00:00");
		} else {
			if (StringUtils.isBlank(is_first)) {
				entity.getMap().put("s_date", ChannelDataImportAction.theFirstDayOfCurrentMonth() + " 00:00:00");
				dynaBean.set("s_date", ChannelDataImportAction.theFirstDayOfCurrentMonth());
			}
			// dynaBean.set(s_date,
			// ChannelDataImportAction.theFirstDayOfCurrentMonth());
		}

		if (StringUtils.isNotBlank(e_date)) {
			entity.getMap().put("e_date", e_date + " 23:59:59");
		} else {
			if (StringUtils.isBlank(is_first)) {
				entity.getMap().put("e_date", fmt1.format(new Date()) + " 23:59:59");
				dynaBean.set("e_date", fmt1.format(new Date()));
			}

			// dynaBean.set(e_date,
			// ChannelDataImportAction.theFirstDayOfCurrentMonth());
		}

		if (StringUtils.isNotBlank(startDate1)) {
			entity.getMap().put("startDate1", startDate1 + " 00:00:00");
		}
		if (StringUtils.isNotBlank(md_name_like)) {
			entity.getMap().put("md_name_like", md_name_like);
		}
		// else {
		// if (StringUtils.isBlank(is_first)) {
		// entity.getMap().put("startDate1",
		// ChannelDataImportAction.theFirstDayOfCurrentMonth() + " 00:00:00");
		// dynaBean.set("startDate1",
		// ChannelDataImportAction.theFirstDayOfCurrentMonth());
		// }
		// dynaBean.set(startDate1,
		// ChannelDataImportAction.theFirstDayOfCurrentMonth());
		// }

		if (StringUtils.isNotBlank(endDate1)) {
			entity.getMap().put("endDate1", endDate1 + " 23:59:59");
		}
		// else {
		// if (StringUtils.isBlank(is_first)) {
		// entity.getMap().put("endDate1", fmt1.format(new Date()) +
		// " 23:59:59");
		// dynaBean.set("endDate1", fmt1.format(new Date()));
		// }
		// dynaBean.set(endDate1,
		// ChannelDataImportAction.theFirstDayOfCurrentMonth());
		// }

		// PeRoleUser _peRoleUser = new PeRoleUser();
		// _peRoleUser.setUser_id(ui.getId());
		// List<PeRoleUser> peRoleUserList =
		// this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		//

		// List<PeRoleUser> peRoleUserList = (List<PeRoleUser>)
		// request.getSession().getAttribute("roleUserList");
		// boolean role_id_lt_30 = false;
		// boolean role_id_ge_30_lt_60 = false;
		// boolean role_id_eq_60 = false;
		// for (PeRoleUser peRoleUser : peRoleUserList) {
		// if (peRoleUser.getRole_id() < 30) {
		// // KONKA_PE_ROLE_info 10
		// // 10 系统管理员
		// // 20 事业部管理员
		// // 21 事业部领导
		// // 22 事业部市场部管理员
		// // 28 渠道管理部人员
		// // 29 分公司-数据主管
		// role_id_lt_30 = true;
		// }
		// if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() < 60L)
		// {
		// // 30 分公司管理员
		// // 31 分公司领导
		// // 32 分公司工作人员
		// // 34 分公司总经理
		// // 35 分公司财务部
		// // 36 分公司销管部
		// // 37 分公司市场部
		// // 38 分公司产品会计
		// // 39 分公司财务经理
		// // 40 经营部管理员
		// // 50 办事处管理员
		// role_id_ge_30_lt_60 = true;
		// }
		// if (peRoleUser.getRole_id() == 60L) {
		// // 业务员
		// role_id_eq_60 = true;
		// }
		// }

		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		// if (role_id_lt_30) {
		//
		// } else if (role_id_ge_30_lt_60) {
		// KonkaDept kd = new KonkaDept();
		// kd.setDept_id(ui.getDept_id());
		// kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
		// // 数据查询权限
		// entity.getMap().put("is_fgs_user", true);
		// entity.getMap().put("fgs_dept_id", ui.getDept_id());
		// entity.getMap().put("fgs_user_id", ui.getId());
		// entity.getMap().put("count1", null);
		// } else if (role_id_eq_60) {
		// entity.getMap().put("count1", true);
		// entity.getMap().put("ywy_user_id", ui.getId());
		// } else {
		// String msg = super.getMessage(request, "popedom.check.invalid");
		// super.renderJavaScript(response, "window.onload=function(){alert('" +
		// msg + "');history.back();}");
		// return null;
		// }

		// 数据级别判断开始
		if (null != ui.getDept_id()) {
			Long __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
			logger.info("Max level : {}", max_dlevel);
			request.setAttribute("max_dlevel", max_dlevel);
			switch (max_dlevel) {
			case 9:
				// 集团及以下部门可见
				__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
				break;
			case 8:
				// 分公司及以下部门可见
				KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
				if (null != dept_fgs && null != dept_fgs.getDept_id()) {
					__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
					entity.getMap().put("dept_id_start", __dept_id);
					entity.getMap().put("count1", null);
				}
				break;
			case 7:
				// 我所在的部门及以下部门可见
				__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
				entity.getMap().put("dept_id_start", __dept_id);
				entity.getMap().put("count1", null);
				break;
			case 0:
				__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
				// entity.getMap().put("dept_id_start", __dept_id);
				entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
				entity.getMap().put("count1", null);
				break;
			default:
				// 出错
			}
		}
		// entity.getMap().put("session_user_id", ui.getId());
		// 数据级别判断结束
		// TODO what is it ?
		Long recordCount = super.getFacade().getChannelDataImportService().getChannelDataImportCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<ChannelDataImport> entityList = super.getFacade().getChannelDataImportService()
				.getChannelDataImportPaginatedList(entity);

		if (entityList != null && entityList.size() > 0) {
			Double pagerCount = 0d;
			BigDecimal pagerMoney = new BigDecimal(0);
			for (ChannelDataImport t : entityList) {
				if (t.getColumn_12() != null) {
					pagerCount = pagerCount + Double.valueOf(t.getColumn_12());
				}
				pagerMoney = pagerMoney.add(t.getColumn_14() == null ? BigDecimal.valueOf(0f) : t.getColumn_14());
			}
			request.setAttribute("pagerCount", pagerCount);
			request.setAttribute("pagerMoney", pagerMoney);

			// HashMap<BigDecimal, BigDecimal> map =
			// super.getFacade().getChannelDataImportService()
			// .getChannelDataImportAllCountAndAllMoney(new
			// ChannelDataImport());
			// 统计总数
			HashMap<BigDecimal, BigDecimal> map = super.getFacade().getChannelDataImportService()
					.getChannelDataImportAllCountAndAllMoney(entity);

			if (map.get("ALL_COUNT") != null && map.get("ALL_MONEY") != null) {
				request.setAttribute("allCount", map.get("ALL_COUNT").toString());
				request.setAttribute("allMoney", map.get("ALL_MONEY").toString());
			}
			if (map.get("ALL_COUNT") == null && map.get("ALL_MONEY") == null) {
				request.setAttribute("allCount", "0.0");
				request.setAttribute("allMoney", "0");
			}
		} else {
			request.setAttribute("pagerCount", 0);
			request.setAttribute("pagerMoney", 0);
			request.setAttribute("allCount", "0.0");
			request.setAttribute("allMoney", "0");
		}

		// excel导出处理
		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("R3订单明细")
					+ ".xls");

			entity.getRow().setCount(recordCount.intValue());
			List<ChannelDataImport> entityList1 = super.getFacade().getChannelDataImportService()
					.getChannelDataImportPaginatedList(entity);
			request.setAttribute("entityList1", entityList1);

			return mapping.findForward("view");
		}

		request.setAttribute("entityList", entityList);
		request.setAttribute("recordCount", recordCount);
		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		return mapping.findForward("input");
	}

	public ActionForward saveBatch(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		List<UploadFile> uploadFilesList = super.uploadFile(form, MmtFilePathConfig.S_OTHERS_PATH);
		if (uploadFilesList.size() == 1) {
			return new ActionForward("ChannelDataImport.do?method=step&mod_id=" + mod_id + "&file_save_path="
					+ uploadFilesList.get(0).getFileSavePath() + "&file_save_this=0", true);
		} else {
			return null;
		}
	}

	public ActionForward step(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String file_save_path = (String) dynaBean.get("file_save_path");
		String file_save_this = (String) dynaBean.get("file_save_this");

		String mod_id = (String) dynaBean.get("mod_id");
		String xx = (String) dynaBean.get("x");
		String yy = (String) dynaBean.get("y");
		// x更新条数 // y新增条数 //z excel表格里面的总数
		int x = 0, y = 0, z = 0;
		if (StringUtils.isNotBlank(xx)) {
			x = Integer.parseInt(xx);
		}
		if (StringUtils.isNotBlank(yy)) {
			y = Integer.parseInt(yy);
		}

		String ctxDir = getServlet().getServletContext().getRealPath(String.valueOf(File.separatorChar));
		if (StringUtils.contains(file_save_path, MmtFilePathConfig.S_OTHERS_PATH)) {
			ctxDir = "";
			file_save_path = "/Attachment_new/konka-files/" + file_save_path;
		}
		try {
			Workbook workbook = Workbook.getWorkbook(new File(ctxDir + file_save_path));
			Sheet sheet = workbook.getSheet(0);
			int rows = sheet.getRows();
			z = rows - 3;
			int start_row = 0;
			int end_row = 0;
			int count = 800;// 每次处理数据条数

			if (rows > Integer.parseInt(file_save_this) + count) {
				start_row = Integer.parseInt(file_save_this) + 1;
				end_row = Integer.parseInt(file_save_this) + count;
			}
			if (rows < Integer.parseInt(file_save_this) + count) {
				start_row = Integer.parseInt(file_save_this) + 1;
				end_row = rows;
			}
			if (Integer.parseInt(file_save_this) == 0) {
				start_row = 3;
			}

			List<ChannelDataImport> modifyList = new ArrayList<ChannelDataImport>();
			List<ChannelDataImport> createList = new ArrayList<ChannelDataImport>();
			if (rows > Integer.parseInt(file_save_this)) {
				for (int i = start_row; i < end_row; i++) {
					ChannelDataImport entity = new ChannelDataImport();
					if (StringUtils.isBlank(sheet.getCell(0, i).getContents())) {
						super.renderJavaScript(response, "alert('" + "第" + (i + 1) + "行售达方为空" + "');history.go(-1);");
						return null;
					} else if (StringUtils.isNotBlank(sheet.getCell(7, i).getContents())) {

						// 重复性判断
						ChannelDataImport entity0 = new ChannelDataImport();
						entity0.setColumn_8(sheet.getCell(7, i).getContents());
						entity0.setColumn_10(sheet.getCell(9, i).getContents());
						entity0.setColumn_11(sheet.getCell(10, i).getContents());
						entity0.setColumn_12(sheet.getCell(11, i).getContents().replaceAll(",", ""));
						BigDecimal column_13 = null;
						if (sheet.getCell(12, i).getContents() != "") {
							column_13 = BigDecimal.valueOf(Double.valueOf(sheet.getCell(12, i).getContents()
									.replaceAll("\\,", "")));
						}
						if (column_13 != null) {
							entity0.setColumn_13(column_13);
						} else {
							entity0.getMap().put("column_13", true);
						}
						entity0.setColumn_20(sheet.getCell(19, i).getContents());
						ChannelDataImport data = super.getFacade().getChannelDataImportService()
								.getChannelDataImport(entity0);

						if (data != null) {
							entity.setId(data.getId());
							entity.setColumn_1(sheet.getCell(0, i).getContents());
							entity.setColumn_2(sheet.getCell(1, i).getContents());
							entity.setColumn_3(sheet.getCell(2, i).getContents());
							entity.setColumn_4(sheet.getCell(3, i).getContents());
							entity.setColumn_5(sheet.getCell(4, i).getContents());
							entity.setColumn_6(sheet.getCell(5, i).getContents());

							if (sheet.getCell(6, i).getContents() != "") {
								Date column_7 = DateUtils.parseDate(sheet.getCell(6, i).getContents(),
										new String[] { "yyyy.MM.dd" });
								entity.setColumn_7(column_7);
							}

							entity.setColumn_8(sheet.getCell(7, i).getContents());
							entity.setColumn_9(sheet.getCell(8, i).getContents());
							entity.setColumn_10(sheet.getCell(9, i).getContents());
							entity.setColumn_11(sheet.getCell(10, i).getContents());
							entity.setColumn_12(sheet.getCell(11, i).getContents().replaceAll(",", ""));

							if (sheet.getCell(12, i).getContents() != "") {
								double d1 = Double.valueOf(sheet.getCell(12, i).getContents().replaceAll("\\,", ""));
								entity.setColumn_13(BigDecimal.valueOf(d1));
							}
							if (sheet.getCell(13, i).getContents() != "") {
								String column_14 = sheet.getCell(13, i).getContents().replaceAll("\\,", "");
								double d2 = Double.valueOf(column_14);
								entity.setColumn_14(BigDecimal.valueOf(d2));
							}
							if (sheet.getCell(14, i).getContents() != "") {
								String column_15 = sheet.getCell(14, i).getContents().replaceAll("\\,", "");
								double d3 = Double.valueOf(column_15);
								entity.setColumn_15(BigDecimal.valueOf(d3));
							}
							if (sheet.getCell(15, i).getContents() != "") {
								String column_16 = sheet.getCell(15, i).getContents().replaceAll("\\,", "");
								double d4 = Double.valueOf(column_16);
								entity.setColumn_16(BigDecimal.valueOf(d4));
							}
							if (sheet.getCell(16, i).getContents() != "") {
								String column_17 = sheet.getCell(16, i).getContents().replaceAll("\\,", "");
								double d5 = Double.valueOf(column_17);
								entity.setColumn_17(BigDecimal.valueOf(d5));
							}
							if (sheet.getCell(17, i).getContents() != "") {
								Date column_18 = DateUtils.parseDate(sheet.getCell(17, i).getContents(),
										new String[] { "yyyy.MM.dd" });
								entity.setColumn_18(column_18);
							}

							entity.setColumn_19(sheet.getCell(18, i).getContents());
							entity.setColumn_20(sheet.getCell(19, i).getContents());
							entity.setColumn_21(sheet.getCell(20, i).getContents());
							entity.setColumn_22(sheet.getCell(21, i).getContents());
							entity.setColumn_23(sheet.getCell(22, i).getContents());
							entity.setColumn_24(sheet.getCell(23, i).getContents());
							entity.setColumn_25(sheet.getCell(24, i).getContents());
							entity.setImport_date(new Date());
							entity.setImport_uid(super.getSessionUserInfo(request).getId());

							// super.getFacade().getChannelDataImportService().modifyChannelDataImport(entity);
							modifyList.add(entity);
							x = x + 1;
						} else {
							entity.setColumn_1(sheet.getCell(0, i).getContents());
							entity.setColumn_2(sheet.getCell(1, i).getContents());
							entity.setColumn_3(sheet.getCell(2, i).getContents());
							entity.setColumn_4(sheet.getCell(3, i).getContents());
							entity.setColumn_5(sheet.getCell(4, i).getContents());
							entity.setColumn_6(sheet.getCell(5, i).getContents());

							if (sheet.getCell(6, i).getContents() != "") {
								Date column_7 = DateUtils.parseDate(sheet.getCell(6, i).getContents(),
										new String[] { "yyyy.MM.dd" });
								entity.setColumn_7(column_7);
							}

							entity.setColumn_8(sheet.getCell(7, i).getContents());
							entity.setColumn_9(sheet.getCell(8, i).getContents());
							entity.setColumn_10(sheet.getCell(9, i).getContents());
							entity.setColumn_11(sheet.getCell(10, i).getContents());
							entity.setColumn_12(sheet.getCell(11, i).getContents().replaceAll(",", ""));

							if (sheet.getCell(12, i).getContents() != "") {
								double d1 = Double.valueOf(sheet.getCell(12, i).getContents().replaceAll("\\,", ""));
								entity.setColumn_13(BigDecimal.valueOf(d1));
							}
							if (sheet.getCell(13, i).getContents() != "") {
								String column_14 = sheet.getCell(13, i).getContents().replaceAll("\\,", "");
								double d2 = Double.valueOf(column_14);
								entity.setColumn_14(BigDecimal.valueOf(d2));
							}
							if (sheet.getCell(14, i).getContents() != "") {
								String column_15 = sheet.getCell(14, i).getContents().replaceAll("\\,", "");
								double d3 = Double.valueOf(column_15);
								entity.setColumn_15(BigDecimal.valueOf(d3));
							}
							if (sheet.getCell(15, i).getContents() != "") {
								String column_16 = sheet.getCell(15, i).getContents().replaceAll("\\,", "");
								double d4 = Double.valueOf(column_16);
								entity.setColumn_16(BigDecimal.valueOf(d4));
							}
							if (sheet.getCell(16, i).getContents() != "") {
								String column_17 = sheet.getCell(16, i).getContents().replaceAll("\\,", "");
								double d5 = Double.valueOf(column_17);
								entity.setColumn_17(BigDecimal.valueOf(d5));
							}
							if (sheet.getCell(17, i).getContents() != "") {
								Date column_18 = DateUtils.parseDate(sheet.getCell(17, i).getContents(),
										new String[] { "yyyy.MM.dd" });
								entity.setColumn_18(column_18);
							}

							entity.setColumn_19(sheet.getCell(18, i).getContents());
							entity.setColumn_20(sheet.getCell(19, i).getContents());
							entity.setColumn_21(sheet.getCell(20, i).getContents());
							entity.setColumn_22(sheet.getCell(21, i).getContents());
							entity.setColumn_23(sheet.getCell(22, i).getContents());
							entity.setColumn_24(sheet.getCell(23, i).getContents());
							entity.setColumn_25(sheet.getCell(24, i).getContents());
							entity.setImport_date(new Date());
							entity.setImport_uid(super.getSessionUserInfo(request).getId());
							// super.getFacade().getChannelDataImportService().createChannelDataImport(entity);
							createList.add(entity);
							y = y + 1;
						}
					} else {
						request.setAttribute("error", "第" + i + 1 + "行订单号为空");
						super.renderJavaScript(response, "alert('" + "第" + (i + 1) + "行订单号为空" + "');history.go(-1);");
						return null;
					}

					file_save_this = Integer.toString(i);
				}
			}
			// 更新列表
			if (0 != modifyList.size()) {
				super.getFacade().getChannelDataImportService().modifyChannelDataImportList(modifyList);
			}
			// 新增列表
			if (0 != createList.size()) {
				super.getFacade().getChannelDataImportService().createChannelDataImportList(createList);
			}
			workbook.close();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		request.setAttribute("file_save_path", file_save_path);
		request.setAttribute("file_save_this", file_save_this);
		request.setAttribute("x", x);
		request.setAttribute("y", y);
		request.setAttribute("z", z);

		int sum = x + y;
		request.setAttribute("sum", sum);
		if (Integer.parseInt(file_save_this) - 2 == z) {
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			request.setAttribute("is_end", 1);
			super.createSysOperLog(request, "CHANNEL_DATA_IMPORT", 0l, mod_id, "导入：" + "新增了" + y + "条数据" + "," + "更新了"
					+ x + "条数据", null);

		}

		return new ActionForward(response.encodeRedirectURL("/admin/ChannelDataImport/step.jsp?mod_id=" + mod_id));
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String column_8 = (String) dynaBean.get("column_8");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			ChannelDataImport entity = new ChannelDataImport();
			entity.setId(new Long(id));
			super.getFacade().getChannelDataImportService().removeChannelDataImport(entity);
			super.createSysOperLog(request, "CHANNEL_DATA_IMPORT", entity.getId(), mod_id, "删除:删除了一条订单号为" + column_8
					+ "的数据", BeanUtils.describe(entity).toString());
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			TaskPara entity = new TaskPara();
			entity.getMap().put("pks", pks);
			super.getFacade().getTaskParaService().removeTaskPara(entity);
			saveMessage(request, "entity.deleted");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer
				.append(super.encodeSerializedQueryString(super.serialize(request, "id", "pks", "column_8", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end

		return forward;
	}

	public ActionForward logList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		PeProdUser ui = new PeProdUser();
		ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		super.encodeCharacterForGetMethod(dynaBean, request);

		PeRoleUser peRoleUser = new PeRoleUser();
		peRoleUser.setUser_id(ui.getId());
		peRoleUser = this.getFacade().getPeRoleUserService().getPeRoleUser(peRoleUser);
		Pager pager = (Pager) dynaBean.get("pager");

		OperLog operLog = new OperLog();
		operLog.setLink_tab("CHANNEL_DATA_IMPORT");
		Long recordCount = super.getFacade().getOperLogService().getOperLogCount(operLog);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		operLog.getRow().setFirst(pager.getFirstRow());
		operLog.getRow().setCount(pager.getRowCount());

		List<OperLog> operLogList = super.getFacade().getOperLogService().getOperLogPaginatedList(operLog);
		request.setAttribute("operLogList", operLogList);
		return new ActionForward("/admin/ChannelDataImport/log.jsp");

	}


	/**
	 * <p>
	 * 总部管理员手工同步 (系统同步按总部管理员角色来处理,以后不把订单同步功能分配给分公司操作!)
	 * </p>
	 * <p>
	 * 1.每次同步,都从当前月第一天开始同步,所以同步前把1号开始的数据先删掉.(能想到的最快的一种方法了)
	 * </p>
	 * <p>
	 * 2.删数据和插入接口过来的数据放在同一个service里面做
	 * </p>
	 * <p>
	 * 3.每次同步一个销售组织,提交后再进行下一个销售组织同步
	 * </p>
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ZbSync(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		// String lock_status = (String)
		// request.getSession().getServletContext().getAttribute("channelDataImport_lock");
		// if (lock_status != null && "locking".equals(lock_status)) {
		// super.renderJavaScript(response,
		// "alert('其它用户正正使用此功能,请稍候再尝试！');history.back();");
		// return null;
		// }
		// request.getSession().getServletContext().setAttribute("channelDataImport_lock",
		// "locking");

		HttpSession session = request.getSession(false);
		PeProdUser userInfo = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		String mod_id = (String) dynaBean.get("mod_id");// 205500
		String resetflag = (String) dynaBean.get("resetflag");// 是否重置数据
		Long isize = 0l;
		Long msize = 0l;

		// 需要同步销售组织集合
		Set<String> syncVkorgList = new HashSet<String>();
		PeProdUser user = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		int roleId = 0;
		if (user.getRole_id() != null && user.getRole_id().length() > 0) {
			roleId = Integer.valueOf(user.getRole_id());
		}
		// 只有总部人员 角色 0<= roleid <30 ,200<= roleid <300
		if ((roleId >= 30 && roleId < 200) || (roleId >= 300)) {
			super.renderJavaScript(response, "alert('对不起，您没有权限操作此模块！');history.back();");
			return null;
		}

		// 取出所有销售组织
		List<KonkaSalesDept> konkaSalesDeptList = new ArrayList<KonkaSalesDept>();
		KonkaSalesDept konkaSalesDept = new KonkaSalesDept();
		konkaSalesDept.setIs_del(0);
		konkaSalesDeptList = super.getFacade().getKonkaSalesDeptService().getKonkaSalesDeptList(konkaSalesDept);

		for (KonkaSalesDept kd : konkaSalesDeptList) {
			syncVkorgList.add(kd.getSales_org_code());// 为了去重,减少数据操作
		}
		// 执行结果
		Map<String, Long> map = new HashMap<String, Long>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 计算当前日期往前推30天的时间
		Calendar c = Calendar.getInstance();
		String year = c.get(Calendar.YEAR) + "";
		c.add(Calendar.DATE, -30);
		Date s_date = c.getTime();

		// =========================同步参数 正式 start=============================//
		// 产品组
		String v_vtweg = "10";
		// 分销渠道
		String v_spart = "10";
		// 同步开始时间
		String v_audat_begin = sdf.format(s_date);
		// 同步截止时间
		String v_audat_end = sdf.format(new Date());
		//
		String v_kunnr = null;
		// String v_kunnr = "F1067DQJD";
		// =========================同步参数 正式 end=============================//

		/** 1. product start **/
		// 系统稳定后,使用定时器同步,只会同步当前日期,再往前推一个月得到区间数据 **/
		if (resetflag == null || resetflag.equals("")) {

			// syncVkorgList.clear();
			// syncVkorgList.add("F067");
			// v_audat_begin = "2013-11-18";
			// v_audat_end = "2013-11-18";
			map = super
					.getFacade()
					.getChannelDataImportService()
					.createOrModifySyncChannelDataForfgsTj(syncVkorgList, v_vtweg, v_spart, v_audat_begin, v_audat_end,
							v_kunnr);
			isize += map.get("isize");
			msize += map.get("msize");
		}
		/** product end */

		/** 2. reset start 重置数据 */
		if (resetflag != null && (resetflag.length() > 0)) {
			// 同步两年数据 ---每个销售组织两年一个月一个月的同步
			v_audat_begin = "";
			v_audat_end = "";
			LinkedHashMap<String, String> DateHashMap = getDateMap(Integer.parseInt(year) - 1, Integer.parseInt(year));
			for (String korg : syncVkorgList) {
				Set<String> korgList = new HashSet<String>();
				korgList.clear();
				korgList.add(korg);
				// 按销售组织,一个月一个月的同步
				for (Entry<String, String> s : DateHashMap.entrySet()) {
					v_audat_begin = s.getKey();
					v_audat_end = s.getValue();
					// 处理数据
					map = super
							.getFacade()
							.getChannelDataImportService()
							.createOrModifySyncChannelDataForfgsTj(korgList, v_vtweg, v_spart, v_audat_begin,
									v_audat_end, v_kunnr);

					// 统计被影响的数据
					isize += map.get("isize");
					msize += map.get("msize");
				}
			}
			syncVkorgList.clear();
		}
		/** reset end */

		// 同步完成.释放
		// request.getSession().getServletContext().setAttribute("ChannelDataImport2_lock",
		// "unlock");

		// 记录操作
		createOpLog((isize + msize), userInfo);
		syncVkorgList.clear();
		saveMessage(request, "prodadmin.md.tb.success", new String(String.valueOf(isize + msize)));

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;

	}

	private void createOpLog(long effectNumber, PeProdUser userInfo) {

		OperLog t = new OperLog();
		t.setPpdm_name("进销存-进货管理-R3订单");
		t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
		t.setOper_ip("0:0:0:0:0:0:0:1");

		t.setOper_desc(effectNumber + "条数据被影响.[user]");
		t.setLink_tab("CHANNEL_DATA_IMPORT_2");
		t.setLink_id(0l);
		t.setOper_type("INSERT");
		t.setOper_uid(userInfo.getId());
		t.setOper_uname(userInfo.getUser_name());
		getFacade().getOperLogService().createOperLog(t);
	}

	public static synchronized LinkedHashMap<String, String> getDateMap(int... year) {

		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		for (int i = 0; i < year.length; i++) {// year
			if (year[i] <= 1970)
				throw new IllegalArgumentException("the year that you input can not le 1970");
			if (year[i] >= 9999)
				throw new IllegalArgumentException("the year that you input can not gt 9999");
			for (int j = 0; j <= 12; j++) {// month
				Calendar c = Calendar.getInstance();
				c.set(Calendar.YEAR, year[i]);
				c.set(Calendar.MONTH, j);
				String y = String.valueOf(year[i]);
				String m = "";
				String d = "";// the first day of a month
				String d2 = "";// the last day of a month
				if (c.get(Calendar.MONTH) + 1 >= 10) {
					m = String.valueOf(c.get(Calendar.MONTH) + 1);
				} else {
					m = "0" + String.valueOf(c.get(Calendar.MONTH) + 1);
				}
				d = "0" + c.getActualMinimum(Calendar.DAY_OF_MONTH);
				d2 = "" + c.getActualMaximum(Calendar.DAY_OF_MONTH);

				String start_day = y + "-" + m + "-" + d;
				String last_day = y + "-" + m + "-" + d2;

				map.put(start_day, last_day);
			}
		}
		return map;
	}

	private static String theFirstDayOfCurrentMonth() {
		Calendar c = Calendar.getInstance();

		String y = String.valueOf(c.get(Calendar.YEAR));
		String m = "";
		String d = "";// the first day of a month
		if (c.get(Calendar.MONTH) + 1 >= 10) {
			m = String.valueOf(c.get(Calendar.MONTH) + 1);
		} else {
			m = "0" + String.valueOf(c.get(Calendar.MONTH) + 1);
		}
		d = "0" + c.getActualMinimum(Calendar.DAY_OF_MONTH);
		String start_day = y + "-" + m + "-" + d;
		return start_day;
	}



}
