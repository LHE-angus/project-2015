package com.ebiz.mmt.web.struts.manager.admin;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaDeptJbTask;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Pan，Gang
 * @version 2013-12-17
 */
public class KonkaDeptJbTaskAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		// 分公司
		KonkaDept dept = new KonkaDept();
		dept.setDept_type(3);
		List<KonkaDept> deptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(dept);
		request.setAttribute("deptList", deptList);

		String dept_name = (String) dynaBean.get("dept_name");
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String jb_type = (String) dynaBean.get("jb_type");
		String jb_name_like = (String) dynaBean.get("jb_name_like");
		String datetime = (String) dynaBean.get("datetime");

		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		KonkaDeptJbTask entity = new KonkaDeptJbTask();

		if (StringUtils.isNotBlank(datetime)) {
			String dateyear = datetime.substring(0, 4);
			String datemonth = datetime.substring(4, 6);
			entity.getMap().put("datemonth", datemonth);
			entity.getMap().put("dateyear", dateyear);
		}

		KonkaDept kd = super.getKonkaDeptForFgs(ui.getDept_id());

		if (kd != null) {
			request.setAttribute("is_fgs", "is_fgs");
			entity.setDept_name(kd.getDept_name());
		} else {
			if (StringUtils.isNotBlank(dept_name))
				entity.setDept_name(dept_name);
		}
		if (StringUtils.isNotBlank(r3_code_like)) {
			entity.getMap().put("r3_code_like", r3_code_like);
		}
		if (StringUtils.isNotBlank(jb_name_like)) {
			entity.getMap().put("jb_name_like", jb_name_like);
		}
		if (StringUtils.isNotBlank(jb_type)) {
			request.setAttribute("jb_type", jb_type);
			if ("4".equals(jb_type)) {
				entity.getMap().put("jb_type_4", true);
			} else {
				entity.getMap().put("jb_type", jb_type);
			}
		} else {
			entity.getMap().put("jb_type_4", true);
			request.setAttribute("jb_type", 4);
		}

		String this_year = null;
		String this_month = null;

		// 取当前时间
		SimpleDateFormat formaty = new SimpleDateFormat("yyyy");
		SimpleDateFormat formatm = new SimpleDateFormat("MM");
		Date now = new Date();

		if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
			this_year = year;
			this_month = month;
		} else {
			this_year = formaty.format(now);// 当前年份
			this_month = formatm.format(now);// 当前月份
			// 去年
		}
		Integer last_year = Integer.valueOf(this_year) - 1;

		// 今年数据时间段
		String this_date_s = this_year + "-" + this_month + "-01 00:00:00";
		String this_date_e = this_year + "-" + this_month + "-"
				+ getMaxDay(Integer.valueOf(this_month), Integer.valueOf(this_year)) + " 23:59:59";

		entity.getMap().put("column_value", "column_" + Integer.valueOf(this_month));
		entity.getMap().put("this_year", this_year);
		entity.getMap().put("this_month", Integer.valueOf(this_month));
		entity.getMap().put("last_year", last_year);
		entity.getMap().put("this_date_s", this_date_s);
		entity.getMap().put("this_date_e", this_date_e);

		dynaBean.set("year", this_year);
		dynaBean.set("month", this_month);
		dynaBean.set("jb_type", jb_type);
		request.setAttribute("mm", Integer.valueOf(this_month));

		Long recordCount = getFacade().getKonkaDeptJbTaskService().getKonkaDeptJbTaskCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaDeptJbTask> entityList = super.getFacade().getKonkaDeptJbTaskService()
				.getKonkaDeptJbTaskPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		// 遍历年份，取前10年
		ArrayList<String> yearList = new ArrayList<String>();

		for (int y = 0; y < 10; y++) {
			yearList.add((Integer.valueOf(formaty.format(now)) - y) + "");
		}
		request.setAttribute("yearList", yearList);
		return mapping.findForward("list");

	}

	public ActionForward imp(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);

		HttpSession session = request.getSession();

		return mapping.findForward("input");
	}

	/**
	 * @author zhou 方法没有手工的新增保存,只有从excel读取数据,然后执行保存 对大批量数据的保存进行分批处理
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		FileInputStream isFile = null;
		String fileSavePath = null;
		String ctxDir = null;

		// List<UploadFile> uploadFileList = super.uploadFile(form, false);
		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH, false);
		for (UploadFile uploadFile : uploadFileList) {
			if ("up_load_file".equals(uploadFile.getFormName())) {
				fileSavePath = uploadFile.getFileSavePath();
				break;
			}
		}

		ctxDir = request.getSession().getServletContext().getRealPath("/");
		// 附件保存路径更改遗留问题
		if (StringUtils.contains(fileSavePath, MmtFilePathConfig.S_OTHERS_PATH)) {
			ctxDir = "";
			fileSavePath = "/Attachment_new/konka-files/" + fileSavePath;
		}

		String filetype = fileSavePath.substring(fileSavePath.lastIndexOf(".") + 1);
		if (!filetype.equalsIgnoreCase("xls") && !filetype.equalsIgnoreCase("xlsx")) {
			super.renderJavaScript(response, "alert('上传数据格式不正确!');history.back();");
			return null;
		}
		fileSavePath = ctxDir + fileSavePath;
		isFile = new FileInputStream(fileSavePath);

		boolean ispass = true;// 验证通过标识位 是否是数字
		String regEx = "^\\d{1,10}(\\.\\d+)?$";
		Pattern vali_dig = Pattern.compile(regEx);

		int startRow = 1;
		int startCol = 1;
		HSSFWorkbook workbook = new HSSFWorkbook(isFile);
		HSSFSheet sheet = null;
		// 验证数据完整性BEGIN
		sheet = workbook.getSheetAt(0);
		int rowCounts = sheet.getLastRowNum();
	//	//System.out.println(sheet.getLastRowNum());

		if (rowCounts <= 1) {
			super.renderJavaScript(response, "alert('上传数据为空,请重新处理excel数据!');history.back();");
			return null;
		}

		String c0 = "";
		String c1 = "";
		String c2 = "";
		String c3 = "";
		String c4 = "";
		String c5 = "";
		String c6 = "";
		String c7 = "";
		String c8 = "";
		String c9 = "";

		List<KonkaDeptJbTask> sourceList = new ArrayList<KonkaDeptJbTask>();
		List<KonkaDeptJbTask> inList = new ArrayList<KonkaDeptJbTask>();
		String msg = "";
		for (int j = startRow; j <= rowCounts - 1; j++) {// 获取每行
			HSSFRow row = sheet.getRow(j);
			KonkaDeptJbTask task = new KonkaDeptJbTask();

			c0 = getCellText(row, startCol);
			c1 = getCellText(row, startCol + 1);
			c2 = getCellText(row, startCol + 2);
			c3 = getCellText(row, startCol + 3);
			c4 = getCellText(row, startCol + 4);
			c5 = getCellText(row, startCol + 5);
			c6 = getCellText(row, startCol + 6);
			c7 = getCellText(row, startCol + 7);
			c8 = getCellText(row, startCol + 8);
			c9 = getCellText(row, startCol + 9);

			// c0 = row.getCell(startCol + 0).getStringCellValue();
			// c1 = row.getCell(startCol + 1).getStringCellValue();
			// c2 = row.getCell(startCol + 2).getStringCellValue();
			// c3 = row.getCell(startCol + 3).getStringCellValue();
			// c4 = row.getCell(startCol + 4).getStringCellValue();
			// c5 = row.getCell(startCol + 5).getStringCellValue();
			// c6 = row.getCell(startCol + 6).getStringCellValue();
			// c7 = row.getCell(startCol + 7).getStringCellValue();
			// c8 = row.getCell(startCol + 8).getStringCellValue();
			// c9 = row.getCell(startCol + 9).getStringCellValue();

			if (StringUtils.isBlank(c0) || StringUtils.isBlank(c1) || StringUtils.isBlank(c2)
					|| StringUtils.isBlank(c3) || StringUtils.isBlank(c4) || StringUtils.isBlank(c5)
					|| StringUtils.isBlank(c6) || StringUtils.isBlank(c7) || StringUtils.isBlank(c8)
					|| StringUtils.isBlank(c9)) {
				break;
			}
			// 1. *分公司名称
			if (StringUtils.isNotBlank(c0)) {
				task.setDept_name(StringUtils.trim(c0));
			} else {
				ispass = false;
				task.getMap().put("msg0", "[*分公司名称]不能为空!");
			}
			task.getMap().put("c0", c0);

			// 2. *ABC类
			if (StringUtils.isNotBlank(c1)) {
				if ("A".equalsIgnoreCase(StringUtils.trim(c1))) {
					task.setJb_type(1);
				} else if ("B".equalsIgnoreCase(StringUtils.trim(c1))) {
					task.setJb_type(2);
				} else if ("C".equalsIgnoreCase(StringUtils.trim(c1))) {
					task.setJb_type(3);
				}
				task.setJb_type_name(StringUtils.trim(c1));
			} else {
				ispass = false;
				task.getMap().put("msg1", "[*ABC类]不能为空!");
			}
			task.getMap().put("c1", c1);

			// 3. *经办名称
			if (!StringUtils.isNotBlank(c2)) {
				ispass = false;
				task.getMap().put("msg2", "[*经办名称]不能为空!");
			} else {
				task.setJb_name(StringUtils.trim(c2));
			}
			task.getMap().put("c2", c2);

			// 4. *任务系数

			String c3v = StringUtils.replace(StringUtils.replace(c3, ",", ""), ",", "");
			if (StringUtils.isNotBlank(c3) && StringUtils.isNotBlank(c3v) && vali_dig.matcher(c3v).matches()) {
				task.setTask(new BigDecimal(subZeroAndDot(c3v)));
			} else {
				ispass = false;
				task.getMap().put("msg3", "[*任务系数]不合法，必须填写数字。");
			}
			task.getMap().put("c3", subZeroAndDot(c3v));

			// 5. *经办经理
			if (StringUtils.isNotBlank(c4)) {
				task.setJb_jl(StringUtils.trim(c4));
			} else {
				ispass = false;
				task.getMap().put("msg4", "[*经办经理]不能为空!");
			}
			task.getMap().put("c4", c4);

			// 6. *客户R3编码
			if (StringUtils.isNotBlank(c5)) {
				task.setR3_code(StringUtils.trim(c5));
			} else {
				ispass = false;
				task.getMap().put("msg5", "[*经办经理]不能为空!");
			}
			task.getMap().put("c5", c5);

			// 6. *计数
			String v_c1 = c6.replaceAll(" ", "");
			if (StringUtils.isNotBlank(v_c1) && GenericValidator.isLong(v_c1)) {
				task.setCount(Long.valueOf(v_c1));
			} else {
				ispass = false;
				task.getMap().put("msg6", "[*计数]不合法，必须为整数!");
			}
			task.getMap().put("c6", c6);

			// 7.*年份
			String v_c2 = c7.replaceAll(" ", "").replaceAll(" ", "");
			if (StringUtils.isNotBlank(v_c2) && GenericValidator.isInt(v_c2) && v_c2.length() == 4) {
				task.setYear(Integer.valueOf(v_c2));
			} else {
				ispass = false;
				task.getMap().put("msg7", "[*年份]不合法，必须填写4位整数!");
			}
			task.getMap().put("c7", c7);

			// 8. *月份
			String v_c3 = c8.replaceAll(" ", "").replaceAll(" ", "");
			if (StringUtils.isNotBlank(v_c3) && GenericValidator.isInt(v_c3) && Integer.valueOf(v_c3) >= 1
					&& Integer.valueOf(v_c3) <= 12) {
				task.setMonth(Integer.valueOf(v_c3));
			} else {
				ispass = false;
				task.getMap().put("msg8", "[*月份]不合法，必须填写1~1整数");
			}
			task.getMap().put("c8", c8);

			// 9. *任务额
			String c4v = StringUtils.replace(StringUtils.replace(c9, ",", ""), ",", "");
			if (StringUtils.isNotBlank(c9) && StringUtils.isNotBlank(c4v) && vali_dig.matcher(c4v).matches()) {
				task.setTask_money(new BigDecimal(subZeroAndDot(c4v)));
			} else {
				ispass = false;
				task.getMap().put("msg9", "[*任务金额]不合法，必须填写数字。");
			}
			task.getMap().put("c9", subZeroAndDot(c4v));

			sourceList.add(task);
		}



		if (!ispass) {
			request.setAttribute("demoEntityList", sourceList);// warnDatas
			return view(mapping, form, request, response);
		}



		long total = sourceList.size();
		long everyCount = 500;
		long cycelTotal = total / everyCount;
		if (total % everyCount != 0) {
			cycelTotal += 1;
		}

		for (int i = 0; i < cycelTotal; i++) {
			// 判断剩下的list数据，大于指定?
			if (sourceList.size() >= everyCount) {
				for (int j = 0; j < everyCount; j++) {
					if (sourceList.get(j) == null) {
						break;
					}
					inList.add(sourceList.get(j));
				}
			} else {
				everyCount = sourceList.size();
				for (int j = 0; j < everyCount; j++) {
					if (sourceList.get(j) == null) {
						break;
					}
					inList.add(sourceList.get(j));
				}
			}

			// 每次循环保存的数据输出
			// //System.out.println("保存" + inList.size() + "条数据入库");
			msg = super.getFacade().getKonkaDeptJbTaskService().createKonkaDeptJbTaskByExcel(inList);

			// 移出已经保存过的数据
			sourceList.removeAll(inList);
			// //System.out.println("剩下条数" + sourceList.size());
			inList.clear();// 移出当前保存的数据
		}


		if (0 == sourceList.size()) {
			super.saveError(request, "message", msg);
		} else {
			super.saveMessage(request, "message", msg);
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append("/../manager/admin/KonkaDeptJbTask.do?method=list");
		pathBuffer.append("&mod_id=").append(mod_id).append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// DynaBean dynaBean = (DynaBean) form;
		super.setNaviStringToRequestScope(form, request);
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

		KonkaDeptJbTask entity = new KonkaDeptJbTask();
		entity.setId(Long.valueOf(id));
		super.getFacade().getKonkaDeptJbTaskService().removeKonkaDeptJbTask(entity);

		saveMessage(request, "entity.deleted");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.serialize(request, "id", "method"));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	private static String getCellText(HSSFRow row, int col) {
		if (row.getCell(col) == null) {
			return "";
		}
		DecimalFormat df = new DecimalFormat("#00");
		HSSFCell cell = row.getCell(col);
		String data = "";
		if (null != cell) {
			if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				if (col == 4) {
					data = String.valueOf((new BigDecimal(cell.getNumericCellValue()).setScale(2,
							BigDecimal.ROUND_HALF_UP).doubleValue()));
				} else {
					data = df.format(cell.getNumericCellValue());
				}

			} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
				data = cell.getRichStringCellValue().toString();
			}
		}
		return data.trim();
	}

	/**
	 * 使用java正则表达式去掉多余的.与0
	 * 
	 * @param s
	 * @return
	 */
	public static String subZeroAndDot(String s) {
		if (null != s && s.indexOf(".") > 0) {
			s = s.replaceAll("0+?$", "");// 去掉多余的0
			s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
		}
		return s;
	}

	public int getMaxDay(int mm, int year) {
		int day = 0;
		if (mm == 1 || mm == 3 || mm == 5 || mm == 7 || mm == 8 || mm == 10 || mm == 12) {
			day = 31;
		} else if (mm == 2) {
			if (year % 4 == 0) {
				day = 29;
			} else {
				day = 28;
			}
		} else {
			day = 30;
		}
		return day;
	}
}
