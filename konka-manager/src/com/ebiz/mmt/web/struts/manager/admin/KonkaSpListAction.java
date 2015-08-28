package com.ebiz.mmt.web.struts.manager.admin;

import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaSpList;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Hu,Hao
 * @version 2014-01-24
 */
public class KonkaSpListAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");

		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");

		KonkaSpList entity = new KonkaSpList();

		if (StringUtils.isNotBlank(year))
			entity.setYear(Integer.valueOf(year));

		if (StringUtils.isNotBlank(month))
			entity.setMonth(Integer.valueOf(month));

		entity.setIs_del(0);

		Long recordCount = getFacade().getKonkaSpListService().getKonkaSpListCount(entity);
		pager.init(recordCount, 12, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaSpList> entityList = getFacade().getKonkaSpListService().getKonkaSpListList(entity);

		request.setAttribute("entityList", entityList);

		// 遍历年份，取前10年以及后一年
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy");

		ArrayList<String> yearList = new ArrayList<String>();

		for (int y = -1; y < 10; y++) {
			yearList.add((Integer.valueOf(fmt.format(new Date())) - y) + "");
		}
		request.setAttribute("yearList", yearList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		// 遍历年份，取前10年
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy");
		SimpleDateFormat fmtm = new SimpleDateFormat("mm");

		Date date = new Date();
		ArrayList<String> yearList = new ArrayList<String>();

		for (int y = -1; y < 10; y++) {
			yearList.add((Integer.valueOf(fmt.format(date)) - y) + "");
		}
		request.setAttribute("yearList", yearList);

		dynaBean.set("year", fmt.format(date));
		dynaBean.set("month", fmtm.format(date));

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			String msg = super.getMessage(request, "errors.param");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaSpList entity = new KonkaSpList();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaSpListService().getKonkaSpList(entity);

		super.copyProperties(form, entity);

		// 遍历年份，取前10年及后一年
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy");

		Date date = new Date();
		ArrayList<String> yearList = new ArrayList<String>();

		for (int y = -1; y < 10; y++) {
			yearList.add((Integer.valueOf(fmt.format(date)) - y) + "");
		}
		request.setAttribute("yearList", yearList);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");

		KonkaSpList entity = new KonkaSpList();
		super.copyProperties(entity, form);

		if (GenericValidator.isLong(id)) {

			KonkaSpList k = new KonkaSpList();
			k.getMap().put("is_not_id", id);
			k.setYear(Integer.valueOf(year));
			k.setMonth(Integer.valueOf(month));
			k.setIs_del(0);
			Long count = super.getFacade().getKonkaSpListService().getKonkaSpListCount(k);
			if (count > 0) {
				String msg = super.getMessage(request, "yyyy.mm.date.error");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			entity.setId(Long.valueOf(id));
			entity.setUpdate_date(new Date());
			entity.setUpdate_user_name(ui.getUser_name());
			entity.setUpdate_job_id(ui.getJob_id());
			super.getFacade().getKonkaSpListService().modifyKonkaSpList(entity);
			saveMessage(request, "entity.updated");

		} else {

			KonkaSpList k = new KonkaSpList();
			k.setYear(Integer.valueOf(year));
			k.setMonth(Integer.valueOf(month));
			k.setIs_del(0);
			Long count = super.getFacade().getKonkaSpListService().getKonkaSpListCount(k);
			if (count > 0) {
				String msg = super.getMessage(request, "yyyy.mm.date.error");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			entity.setC_type(10L);
			entity.setAdd_date(new Date());
			entity.setAdd_user_name(ui.getUser_name());
			entity.setAdd_job_id(ui.getJob_id());
			super.getFacade().getKonkaSpListService().createKonkaSpList(entity);

			saveMessage(request, "entity.inserted");

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
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			String msg = super.getMessage(request, "errors.param");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaSpList entity = new KonkaSpList();
		entity.setIs_del(1);
		
		
		saveMessage(request, "entity.deleted");
		
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

	}
	
	public ActionForward imp(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return new ActionForward(response.encodeRedirectURL("/admin/KonkaSpList/impForm.jsp"));
	}

	public ActionForward impSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		FileInputStream isFile = null;
		String fileSavePath = null;

		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.MD_PATH);
		for (UploadFile uploadFile : uploadFileList) {
			if ("up_load_file".equals(uploadFile.getFormName())) {
				fileSavePath = uploadFile.getFileSavePath();
			}
		}

		String filetype = fileSavePath.substring(fileSavePath.lastIndexOf(".") + 1);
		if (!filetype.equalsIgnoreCase("xls") && !filetype.equalsIgnoreCase("xlsx")) {
			super.renderJavaScript(response, "alert('上传数据格式不正确！');history.back();");
			return null;
		}
		String ctxDir = request.getSession().getServletContext().getRealPath("/");
		// 附件保存路径更改遗留问题
		if (StringUtils.contains(fileSavePath, MmtFilePathConfig.S_MD_PATH)) {
			ctxDir = "";
			fileSavePath = "/Attachment_new/konka-files/"+ fileSavePath;
		}

		fileSavePath = ctxDir + fileSavePath;
		isFile = new FileInputStream(fileSavePath);

		List<KonkaSpList> entityList = new ArrayList<KonkaSpList>();
		boolean excel_is_ok = true;// 验证通过标识位 是否是数字
		String regExY = "^[0-9]{4}$";
		String regExM = "^[1-9]|([0][1-9])|([1][0-2])$";
		String regDate = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";

		Pattern vali_y = Pattern.compile(regExY);
		Pattern vali_m = Pattern.compile(regExM);
		Pattern vali_date = Pattern.compile(regDate);

		int startRow = 1;
		int startCol = 1;
		HSSFWorkbook workbook = new HSSFWorkbook(isFile);
		HSSFSheet sheet = null;

		// 验证数据完整性BEGIN
		sheet = workbook.getSheetAt(0);
		int rowCounts = sheet.getLastRowNum();// 行数

		for (int j = startRow; j <= rowCounts; j++) {// 获取每行
			HSSFRow row = sheet.getRow(j);
			KonkaSpList data = new KonkaSpList();

			String c0 = getCellText(row, startCol);
			String c1 = getCellText(row, startCol + 1);
			String c2 = getCellText(row, startCol + 2);
			String c3 = getCellText(row, startCol + 3);

			if (StringUtils.isBlank(c0) && StringUtils.isBlank(c1) && StringUtils.isBlank(c2)
					&& StringUtils.isBlank(c3)) {
				break;
			}

			// 1. 年份
			if (StringUtils.isNotBlank(c0) && vali_y.matcher(c0).matches()) {
				data.setYear(Integer.valueOf(c0));
			} else {
				excel_is_ok = false;
				data.getMap().put("msg0", "【*年份】不能为空或日期格式错误，请填写正确的日期格式: 2014！");
			}
			data.getMap().put("c0", c0);

			// 1. 月份
			if (StringUtils.isNotBlank(c1) && vali_m.matcher(c1).matches()) {
				data.setMonth(Integer.valueOf(c1));
			} else {
				excel_is_ok = false;
				data.getMap().put("msg1", "【*月份】不能为空或日期格式错误，请填写正确的日期格式: 01,1！");
			}
			data.getMap().put("c1", c1);

			// 1. 起始日期
			if (StringUtils.isNotBlank(c2) && vali_date.matcher(c2).matches()) {
				try {
					Date c2_ = DateUtils.parseDate(c2, new String[] { "yyyy-MM-dd" });
					data.setS_date(c2_);
				} catch (Exception e) {
					excel_is_ok = false;
					data.getMap().put("msg2", "【*起始日期】不能为空或日期格式错误，请填写正确的日期格式: yyyy-MM-dd！");
				}
			} else {
				excel_is_ok = false;
				data.getMap().put("msg2", "【*起始日期】不能为空或日期格式错误，请填写正确的日期格式: yyyy-MM-dd！");
			}
			data.getMap().put("c2", c2);

			// 1. 结束日期
			if (StringUtils.isNotBlank(c3) && vali_date.matcher(c3).matches()) {
				try {
					Date c3_ = DateUtils.parseDate(c3, new String[] { "yyyy-MM-dd" });
					data.setE_date(c3_);
				} catch (Exception e) {
					excel_is_ok = false;
					data.getMap().put("msg3", "【*截止日期】不能为空或日期格式错误，请填写正确的日期格式: yyyy-MM-dd！");
				}
			} else {
				excel_is_ok = false;
				data.getMap().put("msg3", "【*截止日期】不能为空或日期格式错误，请填写正确的日期格式: yyyy-MM-dd！");
			}
			data.getMap().put("c3", c3);

			entityList.add(data);
		}

		if (!excel_is_ok) {
			request.setAttribute("demoEntityList", entityList);
			return impView(mapping, form, request, response);
		}

		int exsit_count = 0;
		for (KonkaSpList temp : entityList) {
			PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
			KonkaSpList k = new KonkaSpList();
			k.setYear(temp.getYear());
			k.setMonth(temp.getMonth());
			k.setIs_del(0);
			List<KonkaSpList> klist = super.getFacade().getKonkaSpListService().getKonkaSpListList(temp);

			if (klist.size() > 0) {
				temp.setId(klist.get(0).getId());
				temp.setUpdate_date(new Date());
				temp.setUpdate_job_id(ui.getJob_id());
				temp.setUpdate_user_name(ui.getReal_name());

				super.getFacade().getKonkaSpListService().modifyKonkaSpList(temp);
				exsit_count++;
			} else {
				temp.setC_type(10L);
				temp.setAdd_date(new Date());
				temp.setAdd_job_id(ui.getJob_id());
				temp.setAdd_user_name(ui.getReal_name());
				super.getFacade().getKonkaSpListService().createKonkaSpList(temp);
			}
		}

		String msg = "数据总计 " + entityList.size() + " 条，新增了 " + (entityList.size() - exsit_count) + " 条数据，更新了" + exsit_count+" 条数据!";
		super.saveMessage(request, "message", msg);

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward impView(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// DynaBean dynaBean = (DynaBean) form;
		super.setNaviStringToRequestScope(form, request);
		return new ActionForward(response.encodeRedirectURL("/admin/KonkaSpList/viewForm.jsp"));
	}

	private String getCellText(HSSFRow row, int col) {
		if (row.getCell(col) == null) {
			return "";
		}
		DecimalFormat df = new DecimalFormat("#");
		HSSFCell cell = row.getCell(col);
		String data = "";
		if (null != cell) {
			if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				data = df.format(cell.getNumericCellValue());
			} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
				data = cell.getRichStringCellValue().toString();
			}
		}
		return data.trim();
	}
}
