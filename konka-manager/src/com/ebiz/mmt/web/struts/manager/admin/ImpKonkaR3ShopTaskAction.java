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
import com.ebiz.mmt.domain.KonkaR3ShopTask;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Hu,Hao
 * @version 2013-12-05
 */
public class ImpKonkaR3ShopTaskAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		SimpleDateFormat formaty = new SimpleDateFormat("yyyy");
		SimpleDateFormat formatm = new SimpleDateFormat("MM");
		Pager pager = (Pager) dynaBean.get("pager");

		String dept_id = (String) dynaBean.get("dept_id");
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String p_user_name_like = (String) dynaBean.get("p_user_name_like");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String task_type = (String) dynaBean.get("task_type");

		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		KonkaR3ShopTask entity = new KonkaR3ShopTask();

		if (StringUtils.isNotBlank(r3_code_like))
			entity.getMap().put("r3_code_like", r3_code_like);
		if (StringUtils.isNotBlank(p_user_name_like))
			entity.getMap().put("p_user_name_like", p_user_name_like);
		if (StringUtils.isNotBlank(year)) {
			entity.setYear(Integer.valueOf(year));
		} else {
			entity.setYear(Integer.valueOf(formaty.format(new Date())));
		}
		if (StringUtils.isNotBlank(month)) {
			entity.setMonth(Integer.valueOf(month));
		} else {
			entity.setMonth(Integer.valueOf(formatm.format(new Date())));
		}

		if (StringUtils.isNotBlank(task_type))
			entity.setTask_type(Integer.valueOf(task_type));

		KonkaDept kd = super.getKonkaDeptForFgs(ui.getDept_id());

		if (kd != null) {
			request.setAttribute("is_fgs", "is_fgs");
			entity.setDept_id(kd.getDept_id());
		} else {
			if (StringUtils.isNotBlank(dept_id))
				entity.setDept_id(Long.valueOf(dept_id));
		}

		Long recordCount = getFacade().getKonkaR3ShopTaskService().getKonkaR3ShopTaskToDeptUserCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaR3ShopTask> entityList = super.getFacade().getKonkaR3ShopTaskService()
		        .getKonkaR3ShopTaskToDeptUserPaginatedList(entity);

		KonkaDept dept = new KonkaDept();
		dept.setDept_type(3);
		List<KonkaDept> deptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(dept);

		request.setAttribute("entityList", entityList);
		request.setAttribute("deptList", deptList);

		// 遍历年份，取前10年
		ArrayList<String> yearList = new ArrayList<String>();

		for (int y = 0; y < 10; y++) {
			yearList.add((Integer.valueOf(formaty.format(new Date())) - y) + "");
		}
		request.setAttribute("yearList", yearList);

		// dynaBean.set("year", formaty.format(new Date()));
		// dynaBean.set("month", formatm.format(new Date()));

		return mapping.findForward("list");
	}

	public ActionForward imp(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);

		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		KonkaDept entity = new KonkaDept();
		entity.setDept_type(3);

		KonkaDept kd = super.getKonkaDeptForFgs(ui.getDept_id());

		if (kd != null) {
			entity.setDept_id(kd.getDept_id());
			request.setAttribute("is_fgs", "is_fgs");
		}

		List<KonkaDept> entityList = super.getFacade().getKonkaDeptService().getKonkaDeptList(entity);

		request.setAttribute("entityList", entityList);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String dept_id = (String) dynaBean.get("dept_id");
		String task_type = (String) dynaBean.get("task_type");

		FileInputStream isFile = null;
		String fileSavePath = null;
		String ctxDir = "";

		// List<UploadFile> uploadFileList = super.uploadFile(form, false);
		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH);

		for (UploadFile uploadFile : uploadFileList) {
			if ("up_load_file".equals(uploadFile.getFormName())) {
				fileSavePath = uploadFile.getFileSavePath();
				break;
			}
		}

		String filetype = fileSavePath.substring(fileSavePath.lastIndexOf(".") + 1);
		if (!filetype.equalsIgnoreCase("xls") && !filetype.equalsIgnoreCase("xlsx")) {
			super.renderJavaScript(response, "alert('上传数据格式不正确！');history.back();");
			return null;
		}

		// 附件保存路径更改遗留问题
		if (StringUtils.contains(fileSavePath, MmtFilePathConfig.S_OTHERS_PATH)) {
			ctxDir = "";
			fileSavePath = "/Attachment_new/konka-files/" + fileSavePath;
		}

		fileSavePath = ctxDir + fileSavePath;
		isFile = new FileInputStream(fileSavePath);

		List<KonkaR3ShopTask> entityList = new ArrayList<KonkaR3ShopTask>();
		boolean excel_is_ok = true;// 验证通过标识位 是否是数字
		String regEx = "^\\d{1,10}(\\.\\d+)?$";
		Pattern vali_dig = Pattern.compile(regEx);

		int startRow = 1;
		int startCol = 1;
		HSSFWorkbook workbook = new HSSFWorkbook(isFile);
		HSSFSheet sheet = null;

		// 验证数据完整性BEGIN

		sheet = workbook.getSheetAt(0);
		int rowCounts = sheet.getLastRowNum();// 行数

		for (int j = startRow; j <= rowCounts; j++) {// 获取每行
			HSSFRow row = sheet.getRow(j);
			KonkaR3ShopTask task = new KonkaR3ShopTask();

			String c0 = getCellText(row, startCol);
			String c1 = getCellText(row, startCol + 1);
			String c2 = getCellText(row, startCol + 2);
			String c3 = getCellText(row, startCol + 3);
			String c4 = getCellText(row, startCol + 4);

			if (StringUtils.isBlank(c1) && StringUtils.isBlank(c2) && StringUtils.isBlank(c3)
			        && StringUtils.isBlank(c4)) {
				break;
			}

			// 1. *客户R3编码
			if (StringUtils.isNotBlank(c0)) {
				task.setR3_code(StringUtils.trim(c0));
			}

			// 2. *绑定业务员岗位ID
			if (!StringUtils.isNotBlank(c1)) {
				excel_is_ok = false;
				task.getMap().put("msg1", "【*绑定业务员岗位ID】不能为空！");
			} else {

				PeProdUser p = new PeProdUser();
				p.setJob_id(StringUtils.trim(c1));
				p.setIs_del(0);
				p = super.getFacade().getPeProdUserService().getPeProdUser(p);
				if (null != p) {
					task.setR_user_id(p.getId());
				} else {
					excel_is_ok = false;
					task.getMap().put("msg1", "【*绑定业务员岗位ID】不存在！");
				}
			}
			task.getMap().put("c1", c1);

			// 3.*年份
			String v_c2 = c2.replaceAll(" ", "").replaceAll(" ", "");
			if (StringUtils.isNotBlank(v_c2) && GenericValidator.isInt(v_c2) && v_c2.length() == 4) {
				task.setYear(Integer.valueOf(v_c2));
			} else {
				excel_is_ok = false;
				task.getMap().put("msg2", "【*年份】不合法，必须填写4位整数！");
			}
			task.getMap().put("c2", c2);

			// 4. *月份
			String v_c3 = c3.replaceAll(" ", "").replaceAll(" ", "");
			if (StringUtils.isNotBlank(v_c3) && GenericValidator.isInt(v_c3) && v_c3.length() == 2) {
				task.setMonth(Integer.valueOf(v_c3));
			} else {
				excel_is_ok = false;
				task.getMap().put("msg3", "【*月份】不合法，必须填写2位整数。");
			}
			task.getMap().put("c3", c3);

			// 5. *任务金额
			String c4v = StringUtils.replace(StringUtils.replace(c4, ",", ""), "，", "");
			if (StringUtils.isNotBlank(c4) && StringUtils.isNotBlank(c4v) && vali_dig.matcher(c4v).matches()) {
				task.setTask_money(new BigDecimal(subZeroAndDot(c4v)));
			} else {
				excel_is_ok = false;
				task.getMap().put("msg4", "【*任务金额】不合法，必须填写数字。");
			}
			task.getMap().put("c4", subZeroAndDot(c4v));

			entityList.add(task);
		}

		if (!excel_is_ok) {
			request.setAttribute("demoEntityList", entityList);
			return view(mapping, form, request, response);
		}

		StringBuilder sb = new StringBuilder();

		int exsit_count = 0;
		for (KonkaR3ShopTask temp : entityList) {

			KonkaR3ShopTask k = new KonkaR3ShopTask();
			if (null != temp.getR3_code()) {
				k.setR3_code(temp.getR3_code());
			}
			if (null != temp.getR_user_id()) {
				k.setR_user_id(temp.getR_user_id());
			}
			k.setYear(temp.getYear());
			k.setMonth(temp.getMonth());
			k.setDept_id(Long.valueOf(dept_id));
			k.setTask_type(Integer.valueOf(task_type));
			List<KonkaR3ShopTask> klist = super.getFacade().getKonkaR3ShopTaskService().getKonkaR3ShopTaskList(k);

			if (klist.size() > 0) {
				exsit_count++;
			} else {
				temp.setYear(temp.getYear());
				temp.setMonth(temp.getMonth());
				temp.setDept_id(Long.valueOf(dept_id));
				temp.setTask_type(Integer.valueOf(task_type));
				temp.setAdd_date(new Date());
				super.getFacade().getKonkaR3ShopTaskService().createKonkaR3ShopTask(temp);
			}
		}

		String msg = "数据总计 " + entityList.size() + " 条，成功导入 " + (entityList.size() - exsit_count) + " 条，" + exsit_count
		        + " 条已存在( " + sb.toString() + ")。";
		if (0 == (entityList.size() - exsit_count)) {
			super.saveError(request, "message", msg);
		} else {
			super.saveMessage(request, "message", msg);
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append("/../manager/admin/ImpKonkaR3ShopTask.do?method=list");
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

	private String getCellText(HSSFRow row, int col) {
		if (row.getCell(col) == null) {
			return "";
		}
		DecimalFormat df = new DecimalFormat("#00");
		DecimalFormat df1 = new DecimalFormat("#0.0000");
		HSSFCell cell = row.getCell(col);
		String data = "";
		if (null != cell) {
			if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				if (col == 5) {
					data = df1.format(cell.getNumericCellValue());
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
}
