package com.ebiz.mmt.web.struts.manager.admin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Backmoney;
import com.ebiz.mmt.domain.KonkaR3Order;
import com.ebiz.mmt.domain.OperLog;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.domain.TaskPara;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.mmt.web.util.StringHelper;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * Code by AutoGenerated Builder AutoGenerated Builder Version 2.1
 * 
 * @author Gao,YongXiang
 * @datetime 2011-11-10 15:33:16
 */
public class KonkaOrderInfoStatisticsAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		
		//验证权限
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		//年份
		String time = (String) dynaBean.get("year");
		//R3编码及名称
		String r3_code = (String) dynaBean.get("r3_code");
		String customer_name = (String) dynaBean.get("customer_name");
		//一级客户类型
		String customer_type1 = (String) dynaBean.get("v_customer_type1");
		//二级客户类型
		String customer_type2 = (String) dynaBean.get("v_customer_type2");
		//分公司
		String dept_id = (String) dynaBean.get("dept_id");
		//统计维度
		String computer_type = (String) dynaBean.get("computer_type");
		
		if(null==computer_type||"".equals(computer_type)){
			request.setAttribute("c_type", "2");
		}else{
			request.setAttribute("c_type", computer_type);
		}
		
		
		String excel_to_all = (String) dynaBean.get("excel_to_all");
		String is_kh = (String) dynaBean.get("is_kh");
		dynaBean.set("is_kh", is_kh);
		
		// 2012至今的年份
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		ArrayList<String> yearList = new ArrayList<String>();
		for (int i = (year - 2012); i >= 0; i--) {
			yearList.add((year - i) + "");
		}
		request.setAttribute("yearList", yearList);
		
		//统计维度列表computer_type
		ArrayList<HashMap> typeList = new ArrayList<HashMap>();
		HashMap map = new HashMap();
		map.put("val", "2");
		map.put("name", "订单总额");
		typeList.add(map);
		
		map = new HashMap();
		map.put("val", "1");
		map.put("name", "订单数量");
		typeList.add(map);
		
		request.setAttribute("typelist", typeList);

		KonkaR3Order entity = new KonkaR3Order();
		
		if("1".equals(computer_type)){
			entity.getMap().put("com_type_num", computer_type);
		}else{
			entity.getMap().put("com_type_mon", computer_type);
		}

		if (StringUtils.isNotBlank(r3_code)) {
			entity.setR3_code(r3_code);
		}
		if (StringUtils.isNotBlank(time)) {
			entity.setYear(time);
		}
		
		//初次进入则查询当前年份数据   Angus  2014-07-10
		if(null == time){
			entity.setYear(String.valueOf(year));
			dynaBean.set("year", String.valueOf(year));
		}
		
		if (StringUtils.isNotBlank(customer_type1)) {
			entity.getMap().put("customer_type1", customer_type1);
		}
		if (StringUtils.isNotBlank(customer_type2)) {
			entity.getMap().put("customer_type2", customer_type2);
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("dept_id", dept_id);
		}
		if (StringUtils.isNotBlank(customer_name)) {
			entity.getMap().put("customer_name", customer_name);
		}
		
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");

		// 数据级别判断开始
		PeProdUser ui = new PeProdUser();
		ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		Long __dept_id = ui.getDept_id(); 		// 默认为当前用户所在部门
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
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);
			break;
		case 0:
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			// entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", ui.getId());
//		// 数据级别判断结束
		
		

		Long recordCount = super.getFacade().getKonkaR3OrderService().getKonkaR3OrderCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaR3Order> entityList = super.getFacade().getKonkaR3OrderService().getKonkaR3OrderList(entity);
		
		request.setAttribute("entityList", entityList);
		request.setAttribute("recordCount", recordCount);
		
		// 导出
		if (StringUtils.isNotBlank(excel_to_all)) {
//			if (recordCount.intValue() > 20000) {
//				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
//				        + "！');history.back();");
//				return null;
//			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("R3订单统计")
			        + ".xls");
			entity.getRow().setFirst(0);
			entity.getRow().setCount(recordCount.intValue());
			List<HashMap> entityList1 = super.getFacade().getKonkaR3OrderService().getKonkaR3OrderListNew(entity);
			request.setAttribute("allList", entityList1);
			return mapping.findForward("view");
		}

		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		request.setAttribute("year", year);

		return mapping.findForward("input");
	}

	public ActionForward saveBatch(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String year = (String) dynaBean.get("year");

		List<UploadFile> uploadFilesList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH);
		if (uploadFilesList.size() == 1) {
			return new ActionForward("KonkaR3Backmoney.do?method=step&mod_id=" + mod_id + "&year=" + year
			        + "&file_save_path=" + uploadFilesList.get(0).getFileSavePath() + "&file_save_this=0", true);
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
		String year = (String) dynaBean.get("year");

		KonkaR3Backmoney entity = new KonkaR3Backmoney();

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
			z = rows - 1;
			int start_row = 0;
			int end_row = 0;

			if (rows > Integer.parseInt(file_save_this) + 1000) {
				start_row = Integer.parseInt(file_save_this) + 1;
				end_row = Integer.parseInt(file_save_this) + 1000;
			}
			if (rows < Integer.parseInt(file_save_this) + 1000) {
				start_row = Integer.parseInt(file_save_this) + 1;
				end_row = rows;
			}
			if (Integer.parseInt(file_save_this) == 0) {
				start_row = 1;
			}

			if (rows > Integer.parseInt(file_save_this)) {
				for (int i = start_row; i < end_row; i++) {
					if (StringUtils.isBlank(sheet.getCell(0, i).getContents())) {
						super.renderJavaScript(response, "alert('" + "第" + (i + 1) + "行R3编码为空" + "');history.go(-1);");
						return null;
					} else if (StringUtils.isNotBlank(sheet.getCell(0, i).getContents())) {

						// 重复性判断
						KonkaR3Backmoney krb = new KonkaR3Backmoney();
						krb.setYear(Long.valueOf(year));
						krb.setR3_code(sheet.getCell(0, i).getContents());
						KonkaR3Backmoney data = super.getFacade().getKonkaR3BackmoneyService().getKonkaR3Backmoney(krb);

						if (data != null) {
							entity.setId(data.getId());
							entity.setR3_code(sheet.getCell(0, i).getContents());
							entity.setBranch_area_name(sheet.getCell(1, i).getContents());
							entity.setHandle_name(sheet.getCell(2, i).getContents());
							entity.setCustomer_type(sheet.getCell(3, i).getContents());
							entity.setArea_name(sheet.getCell(4, i).getContents());
							entity.setCustomer_name(sheet.getCell(5, i).getContents());
							if (sheet.getCell(6, i).getContents() != "") {
								String column_1 = sheet.getCell(6, i).getContents();
								entity.setColumn_1(Double.valueOf(column_1));
							} else {
								entity.setColumn_1(null);
							}
							if (sheet.getCell(7, i).getContents() != "") {
								String column_2 = sheet.getCell(7, i).getContents();
								entity.setColumn_2(Double.valueOf(column_2));
							} else {
								entity.setColumn_2(null);
							}
							if (sheet.getCell(8, i).getContents() != "") {
								String column_3 = sheet.getCell(8, i).getContents();
								entity.setColumn_3(Double.valueOf(column_3));
							} else {
								entity.setColumn_3(null);
							}
							if (sheet.getCell(9, i).getContents() != "") {
								String column_4 = sheet.getCell(9, i).getContents();
								entity.setColumn_4(Double.valueOf(column_4));
							} else {
								entity.setColumn_4(null);
							}
							if (sheet.getCell(10, i).getContents() != "") {
								String column_5 = sheet.getCell(10, i).getContents();
								entity.setColumn_5(Double.valueOf(column_5));
							} else {
								entity.setColumn_5(null);
							}
							if (sheet.getCell(11, i).getContents() != "") {
								String column_6 = sheet.getCell(11, i).getContents();
								entity.setColumn_6(Double.valueOf(column_6));
							} else {
								entity.setColumn_6(null);
							}
							if (sheet.getCell(12, i).getContents() != "") {
								String column_7 = sheet.getCell(12, i).getContents();
								entity.setColumn_7(Double.valueOf(column_7));
							} else {
								entity.setColumn_7(null);
							}
							if (sheet.getCell(13, i).getContents() != "") {
								String column_8 = sheet.getCell(13, i).getContents();
								entity.setColumn_8(Double.valueOf(column_8));
							} else {
								entity.setColumn_8(null);
							}
							if (sheet.getCell(14, i).getContents() != "") {
								String column_9 = sheet.getCell(14, i).getContents();
								entity.setColumn_9(Double.valueOf(column_9));
							} else {
								entity.setColumn_9(null);
							}
							if (sheet.getCell(15, i).getContents() != "") {
								String column_10 = sheet.getCell(15, i).getContents();
								entity.setColumn_10(Double.valueOf(column_10));
							} else {
								entity.setColumn_10(null);
							}
							if (sheet.getCell(16, i).getContents() != "") {
								String column_11 = sheet.getCell(16, i).getContents();
								entity.setColumn_11(Double.valueOf(column_11));
							} else {
								entity.setColumn_11(null);
							}
							if (sheet.getCell(17, i).getContents() != "") {
								String column_12 = sheet.getCell(17, i).getContents();
								entity.setColumn_12(Double.valueOf(column_12));
							} else {
								entity.setColumn_12(null);
							}
							entity.setAdd_date(new Date());
							entity.setAdd_uid(super.getSessionUserInfo(request).getId());
							entity.setYear(Long.valueOf(year));

							super.getFacade().getKonkaR3BackmoneyService().modifyKonkaR3Backmoney(entity);
							x = x + 1;
						} else {
							entity.setR3_code(sheet.getCell(0, i).getContents());
							entity.setBranch_area_name(sheet.getCell(1, i).getContents());
							entity.setHandle_name(sheet.getCell(2, i).getContents());
							entity.setCustomer_type(sheet.getCell(3, i).getContents());
							entity.setArea_name(sheet.getCell(4, i).getContents());
							entity.setCustomer_name(sheet.getCell(5, i).getContents());
							if (sheet.getCell(6, i).getContents() != "") {
								String column_1 = sheet.getCell(6, i).getContents();
								entity.setColumn_1(Double.valueOf(column_1));
							} else {
								entity.setColumn_1(null);
							}
							if (sheet.getCell(7, i).getContents() != "") {
								String column_2 = sheet.getCell(7, i).getContents();
								entity.setColumn_2(Double.valueOf(column_2));
							} else {
								entity.setColumn_2(null);
							}
							if (sheet.getCell(8, i).getContents() != "") {
								String column_3 = sheet.getCell(8, i).getContents();
								entity.setColumn_3(Double.valueOf(column_3));
							} else {
								entity.setColumn_3(null);
							}
							if (sheet.getCell(9, i).getContents() != "") {
								String column_4 = sheet.getCell(9, i).getContents();
								entity.setColumn_4(Double.valueOf(column_4));
							} else {
								entity.setColumn_4(null);
							}
							if (sheet.getCell(10, i).getContents() != "") {
								String column_5 = sheet.getCell(10, i).getContents();
								entity.setColumn_5(Double.valueOf(column_5));
							} else {
								entity.setColumn_5(null);
							}
							if (sheet.getCell(11, i).getContents() != "") {
								String column_6 = sheet.getCell(11, i).getContents();
								entity.setColumn_6(Double.valueOf(column_6));
							} else {
								entity.setColumn_6(null);
							}
							if (sheet.getCell(12, i).getContents() != "") {
								String column_7 = sheet.getCell(12, i).getContents();
								entity.setColumn_7(Double.valueOf(column_7));
							} else {
								entity.setColumn_7(null);
							}
							if (sheet.getCell(13, i).getContents() != "") {
								String column_8 = sheet.getCell(13, i).getContents();
								entity.setColumn_8(Double.valueOf(column_8));
							} else {
								entity.setColumn_8(null);
							}
							if (sheet.getCell(14, i).getContents() != "") {
								String column_9 = sheet.getCell(14, i).getContents();
								entity.setColumn_9(Double.valueOf(column_9));
							} else {
								entity.setColumn_9(null);
							}
							if (sheet.getCell(15, i).getContents() != "") {
								String column_10 = sheet.getCell(15, i).getContents();
								entity.setColumn_10(Double.valueOf(column_10));
							} else {
								entity.setColumn_10(null);
							}
							if (sheet.getCell(16, i).getContents() != "") {
								String column_11 = sheet.getCell(16, i).getContents();
								entity.setColumn_11(Double.valueOf(column_11));
							} else {
								entity.setColumn_11(null);
							}
							if (sheet.getCell(17, i).getContents() != "") {
								String column_12 = sheet.getCell(17, i).getContents();
								entity.setColumn_12(Double.valueOf(column_12));
							} else {
								entity.setColumn_12(null);
							}
							entity.setAdd_date(new Date());
							entity.setAdd_uid(super.getSessionUserInfo(request).getId());
							entity.setYear(Long.valueOf(year));
							super.getFacade().getKonkaR3BackmoneyService().createKonkaR3Backmoney(entity);
							y = y + 1;
						}
					} else {
						request.setAttribute("error", "第" + i + 1 + "行R3编码为空");
						super.renderJavaScript(response, "alert('" + "第" + (i + 1) + "行R3编码为空" + "');history.go(-1);");
						return null;
					}

					file_save_this = Integer.toString(i);
				}
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
		if (Integer.parseInt(file_save_this) == z) {
			request.setAttribute("is_end", 1);
			super.createSysOperLog(request, "KONKA_R3_BACKMONEY", entity.getId(), mod_id, "导入：" + "新增了" + y + "条数据"
			        + "," + "更新了" + x + "条数据", BeanUtils.describe(entity).toString());
		}

		return new ActionForward(response.encodeRedirectURL("/admin/KonkaR3Backmoney/step.jsp?mod_id=" + mod_id));
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String r3_code = (String) dynaBean.get("r3_code");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaR3Backmoney entity = new KonkaR3Backmoney();
			entity.setId(new Long(id));
			super.getFacade().getKonkaR3BackmoneyService().removeKonkaR3Backmoney(entity);
			super.createSysOperLog(request, "KONKA_R3_BACKMONEY", entity.getId(), mod_id, "删除:删除了一条R3编码为" + r3_code
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
		        .append(super.encodeSerializedQueryString(super.serialize(request, "id", "pks", "r3_code", "method")));
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
		// PeProdUser ui = new PeProdUser();
		// ui = (PeProdUser)
		// request.getSession().getAttribute(Constants.USER_INFO);
		super.encodeCharacterForGetMethod(dynaBean, request);

		// PeRoleUser peRoleUser = new PeRoleUser();
		// peRoleUser.setUser_id(ui.getId());
		// peRoleUser =
		// this.getFacade().getPeRoleUserService().getPeRoleUser(peRoleUser);
		Pager pager = (Pager) dynaBean.get("pager");

		OperLog operLog = new OperLog();
		operLog.setLink_tab("KONKA_R3_BACKMONEY");
		Long recordCount = super.getFacade().getOperLogService().getOperLogCount(operLog);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		operLog.getRow().setFirst(pager.getFirstRow());
		operLog.getRow().setCount(pager.getRowCount());

		List<OperLog> operLogList = super.getFacade().getOperLogService().getOperLogPaginatedList(operLog);
		request.setAttribute("operLogList", operLogList);
		return new ActionForward("/admin/KonkaR3Backmoney/log.jsp");

	}

	@Override
	public ActionForward toExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws IOException {
		super.HtmltoExcel(request, response);
		return null;
	}

	public ActionForward tbData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws IOException {

		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
//
//		String v_gjahr = "";// 年
//		String v_monat = "";// 月
//		SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy");
//		SimpleDateFormat fmt2 = new SimpleDateFormat("MM");
//
//		v_gjahr = fmt1.format(new Date());
//		v_monat = fmt2.format(new Date());
//		String[] month_value = { ("" + (Integer.valueOf(v_monat) - 1)), v_monat };
//
//		// String[] yyyymm = { "2012-01", "2012-02", "2012-03", "2012-04",
//		// "2012-05", "2012-06", "2012-07", "2012-08",
//		// "2012-09", "2012-10", "2012-11", "2012-12", "2013-01", "2013-02",
//		// "2013-03", "2013-04", "2013-05",
//		// "2013-06", "2013-07", "2013-08", "2013-09" };
//
//		// 销售组织
//		KonkaSalesDept ksd = new KonkaSalesDept();
//		ksd.setIs_del(0);
//		// ksd.setSales_org_code("F182");
//		List<KonkaSalesDept> ksdList = super.getFacade().getKonkaSalesDeptService().getKonkaSalesDeptList(ksd);
//
//		List<KonkaR3Backmoney> entityList = new ArrayList<KonkaR3Backmoney>();
//
//		Long counts = 0L;
//		for (int i = 0; i < month_value.length; i++) {
//			// logger.info("month_value============{}",month_value[i]);
//			v_monat = month_value[i];
//			// for (int i = 0; i < yyyymm.length; i++) {
//			// v_gjahr = yyyymm[i].split("-")[0];
//			// v_monat = yyyymm[i].split("-")[1];
//			for (KonkaSalesDept temp : ksdList) {
//				// 经办
//				KonkaDept kDept1 = new KonkaDept();
//				kDept1.setDept_sn(temp.getSales_org_code());
//				List<KonkaDept> kDeptList1 = super.getFacade().getKonkaDeptService().getKonkaDeptList(kDept1);
//				if (kDeptList1.size() > 0) {
//					kDept1 = kDeptList1.get(0);
//				}
//				// 查询分公司
//				KonkaDept kDept = new KonkaDept();
//				kDept.setDept_sn(temp.getP_sales_org_code());
//				List<KonkaDept> kDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(kDept);
//
//				if (kDeptList.size() > 0) {
//					kDept = kDeptList.get(0);
//				}
//				List<KHYS> khysList = super.getFacade().getR3WebInterfaceService().getKhys(v_gjahr, v_monat, "10",
//				        temp.getSales_org_code(), null);
//				if (khysList.size() > 0) {
//					for (KHYS khys : khysList) {
//						KonkaR3Backmoney konkaR3Backmoney = new KonkaR3Backmoney();
//						konkaR3Backmoney.setYear(Long.valueOf(v_gjahr));// 年份
//						if (kDept.getDept_name() != null && !"".equals(kDept.getDept_name())) {
//							konkaR3Backmoney.setBranch_area_name(kDept.getDept_name());// 分公司名称
//						}

//						if (kDept.getDept_id() != null) {
//							konkaR3Backmoney.setBranch_area_code(kDept.getDept_id());
//						}
//						if (kDept1.getDept_name() != null) {
//							konkaR3Backmoney.setHandle_name(kDept1.getDept_name());
//						}
//
//						String area_name = null;
//						if (kDept.getP_area() != null && !"".equals(kDept.getP_area())) {
//							switch (kDept.getP_area()) {
//							case 10:
//								area_name = "华东";
//								break;
//							case 20:
//								area_name = "北方";
//								break;
//							case 30:
//								area_name = "南方";
//								break;
//							case 40:
//								area_name = "中部";
//								break;
//							}
//							konkaR3Backmoney.setArea_code(Long.valueOf(kDept.getP_area()));
//						}
//						konkaR3Backmoney.setArea_name(area_name);// 大区
//						konkaR3Backmoney.setCustomer_type(khys.getKUKLA());// 客户类型
//						konkaR3Backmoney.setR3_code(khys.getKUNNR());// R3客户编号
//						konkaR3Backmoney.setCustomer_name(khys.getNAME1());// 客户名称
//						konkaR3Backmoney.setAdd_uid(0L);
//						konkaR3Backmoney.setAdd_date(new Date());// add时间
//
//						Double zdf = 0.00;
//						if (null != khys.getZDF() && !"".equals(khys.getZDF().toString())) {
//							zdf = zdf - Double.valueOf(khys.getZDF().toString()) / 10000;
//						}
//
//						zdf = ArithUtil.round(zdf, 2);
//						switch (Integer.valueOf(v_monat)) {
//						case 1:
//							konkaR3Backmoney.setColumn_1(zdf);
//							break;
//						case 2:
//							konkaR3Backmoney.setColumn_2(zdf);
//							break;
//						case 3:
//							konkaR3Backmoney.setColumn_3(zdf);
//							break;
//						case 4:
//							konkaR3Backmoney.setColumn_4(zdf);
//							break;
//						case 5:
//							konkaR3Backmoney.setColumn_5(zdf);
//							break;
//						case 6:
//							konkaR3Backmoney.setColumn_6(zdf);
//							break;
//						case 7:
//							konkaR3Backmoney.setColumn_7(zdf);
//							break;
//						case 8:
//							konkaR3Backmoney.setColumn_8(zdf);
//							break;
//						case 9:
//							konkaR3Backmoney.setColumn_9(zdf);
//							break;
//						case 10:
//							konkaR3Backmoney.setColumn_10(zdf);
//							break;
//						case 11:
//							konkaR3Backmoney.setColumn_11(zdf);
//							break;
//						case 12:
//							konkaR3Backmoney.setColumn_12(zdf);
//							break;
//						}
//						entityList.add(konkaR3Backmoney);
//					}
//				}
//			}
//			Long count1 = super.getFacade().getKonkaR3BackmoneyService().modifyKonkaR3BackmoneyForTb(entityList);
//			counts = counts + count1;
//		}
//		// }
		
		
		Long counts = super.getFacade().getAutoRunService().createR3Order();
		
		//暂时没有返回同步的条数，因此下面的提示信息不会显示
		if(counts!=null){
			saveMessage(request, "prodadmin.md.tb.success", new String[] { counts.toString() });
		}
//
//		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
		
	}
	
	
	/*************************更改UI后新方法***********************************/
	/**
	 * 初始化
	 * @author Angus
	 * @date 2014-7-24
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HashMap allmap = new HashMap();
        //位置信息
        DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		String naviString = "";
		if (StringUtils.isNotBlank(mod_id)) {
			SysModule sysModule = new SysModule();
			sysModule.getMap().put("mod_id", mod_id);
			List<SysModule> sysModuleList = getFacade().getSysModuleService().getSysModuleList(sysModule);
			naviString = StringHelper.getNaviString(sysModuleList, " > ");
		}
		allmap.put("local_str", naviString);
		
		//当前年份
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		allmap.put("year", year);
		
		//当前用户信息
		PeProdUser ui = new PeProdUser();
		ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		int max_dlevel = super.getMaxDLevel(ui.getId());
		if(max_dlevel==9){
			allmap.put("dept_id", "");
		}else{
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id());
			allmap.put("dept_id", dept_fgs.getDept_id());
		}
		
		//转换为json数据
		JSONArray jsonArray = JSONArray.fromObject(allmap);
		
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString().substring(start+1, end+1));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 初始化年份列表
	 * @author Angus
	 * @date 2014-7-24
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getYearList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 2012至今的年份
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		ArrayList<HashMap> yearList = new ArrayList<HashMap>();
		HashMap map = null;
		for (int i = (year - 2012); i >= 0; i--) {
			map = new HashMap();
			map.put("code_value", (year - i)+"");
			map.put("text_value", (year - i)+"年");
			yearList.add(map);
		}
		
		//转换为json数据
		JSONArray jsonArray = JSONArray.fromObject(yearList);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString());
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 初始化统计维度列表
	 * @author Angus
	 * @date 2014-7-24
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getComType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//统计维度
		ArrayList<HashMap> typeList = new ArrayList<HashMap>();
		HashMap m = new HashMap();
		m.put("code_value", "2");
		m.put("text_value", "订单总额");
		typeList.add(m);
		m = new HashMap();
		m.put("code_value", "1");
		m.put("text_value", "订单数量");
		typeList.add(m);
		
		//转换为json数据
		JSONArray jsonArray = JSONArray.fromObject(typeList);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString());
		out.flush();
		out.close();
		return null;
	}
	
	
	public ActionForward queryList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		//验证权限
//		if (null == super.checkUserModPopeDom(form, request, "0")) {
//			return super.checkPopedomInvalid(request, response);
//		}
		
		KonkaR3Order entity = getEntiy(mapping, form, request);

		Long recordCount = super.getFacade().getKonkaR3OrderService().getKonkaR3OrderCount(entity);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		pager.init(recordCount, pager.getPageSize(), (String)entity.getMap().get("page"));
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<HashMap> entityList = null;
		HashMap allmap = null;
		if(recordCount>0){
			entityList = super.getFacade().getKonkaR3OrderService().getKonkaR3OrderListNew(entity);
		}
		
		//封装成JSON字符串
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("total", recordCount);
		if(entityList==null){
			String[] str = {};
			m.put("rows", str);
		}else{
			m.put("rows", entityList);
		}
		JSONArray jsonArray = JSONArray.fromObject(m);
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start+1, end+1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 导出数据
	 * @author Angus
	 * @date 2014-7-24
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("R3订单统计")
		        + ".xls");
		KonkaR3Order entity = getEntiy(mapping, form, request);
		Long recordCount = super.getFacade().getKonkaR3OrderService().getKonkaR3OrderCount(entity);
		entity.getRow().setFirst(0);
		entity.getRow().setCount(recordCount.intValue());
		List<HashMap> entityList1 = super.getFacade().getKonkaR3OrderService().getKonkaR3OrderListNew(entity);
		request.setAttribute("allList", entityList1);
		return mapping.findForward("view");
	}
	
	/**
	 * 封装数据
	 * @author Angus
	 * @date 2014-7-24
	 * @param mapping
	 * @param form
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public KonkaR3Order  getEntiy(ActionMapping mapping, ActionForm form, HttpServletRequest request) throws Exception{
		
		String page = request.getParameter("page");
		//年份
		String time = request.getParameter("year_list");
		//R3编码及名称
		String r3_code = request.getParameter("r3_code");
		String customer_name = request.getParameter("cus_name");
		//一级客户类型
		String customer_type1 = request.getParameter("cus_type1");
		//二级客户类型
		String customer_type2 = request.getParameter("cus_type2");
		//分公司
		String dept_id = request.getParameter("dept_id");
		//统计维度
		String computer_type = request.getParameter("com_type");
		
		KonkaR3Order entity = new KonkaR3Order();

		if (StringUtils.isNotBlank(r3_code)) {
			entity.setR3_code(r3_code);
		}
		if (StringUtils.isNotBlank(time)) {
			entity.setYear(time);
		}
		if("1".equals(computer_type)){
			entity.getMap().put("com_type_num", computer_type);
		}else{
			entity.getMap().put("com_type_mon", computer_type);
		}
		
		if (StringUtils.isNotBlank(customer_type1)) {
			entity.getMap().put("customer_type1", customer_type1);
		}
		if (StringUtils.isNotBlank(customer_type2)) {
			entity.getMap().put("customer_type2", customer_type2);
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("dept_id", dept_id);
		}
		if (StringUtils.isNotBlank(customer_name)) {
			entity.getMap().put("customer_name", customer_name);
		}
		
		// 数据级别判断开始
		PeProdUser ui = new PeProdUser();
		ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		Long __dept_id = ui.getDept_id(); 		// 默认为当前用户所在部门
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
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);
			break;
		case 0:
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			// entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", ui.getId());
		// 数据级别判断结束
		entity.getMap().put("page", page);
		return entity;
	}
	
	/**
	 * 同步订单数据
	 * @author Angus
	 * @date 2014-7-25
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward synData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		super.getFacade().getAutoRunService().createR3Order();
		KonkaR3Order t = new KonkaR3Order(); 
		Long counts = super.getFacade().getKonkaR3OrderService().getKonkaR3OrderCount(t);
		HashMap m = new HashMap();
		m.put("data_count", counts);
		
		//转换为json数据
		JSONArray jsonArray = JSONArray.fromObject(m);
		
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString().substring(start+1, end+1));
		out.flush();
		out.close();
		return null;
	}
}