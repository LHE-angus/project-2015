package com.ebiz.mmt.web.struts.manager.admin;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.ebiz.mmt.domain.KonkaDeptR3Pd;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Pan，Gang
 * @version 2013-12-17
 */
public class KonkaDeptR3PdAction extends BaseAction {
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

		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String dept_id = (String) dynaBean.get("dept_id");

		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(ui.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id().intValue() < 30) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}

		KonkaDeptR3Pd entity = new KonkaDeptR3Pd();

		if (!zb && fgs) { // session用户不属于总部，属于分公司
			KonkaDept fgs_dept = super.getSuperiorForDeptType(ui.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			dynaBean.set("fgs_id", fgs_dept.getDept_id());
			dynaBean.set("fgs_name", fgs_dept.getDept_name());
			entity.setDept_id(fgs_dept.getDept_id());
		}

		if (StringUtils.isNotBlank(r3_code_like)) {
			entity.getMap().put("r3_code_like", r3_code_like);
		}
		if (StringUtils.isNotBlank(year)) {
			entity.setYear(Integer.valueOf(year));
		}
		if (StringUtils.isNotBlank(month)) {
			entity.setMonth(Integer.valueOf(month));
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
		}

		// 取当前时间
		SimpleDateFormat formaty = new SimpleDateFormat("yyyy");
		SimpleDateFormat formatm = new SimpleDateFormat("MM");
		Date now = new Date();

		entity.setIs_del(0);// 显示未删除的

		Long recordCount = getFacade().getKonkaDeptR3PdService().getKonkaDeptR3PdCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaDeptR3Pd> entityList = super.getFacade().getKonkaDeptR3PdService().getKonkaDeptR3PdPaginatedList(
		        entity);

		request.setAttribute("entityList", entityList);

		// 遍历年份，取前10年
		ArrayList<String> yearList = new ArrayList<String>();

		for (int y = 0; y < 10; y++) {
			yearList.add((Integer.valueOf(formaty.format(now)) - y) + "");
		}
		request.setAttribute("yearList", yearList);

		// dynaBean.set("year", formaty.format(new Date()));
		// dynaBean.set("month", formatm.format(new Date()));

		// 取分公司列表
		List<KonkaDept> deptList = getDeptInfoListWithOutLimit(mapping, form, request, response);
		request.setAttribute("deptList", deptList);

		return mapping.findForward("list");

	}

	public ActionForward imp(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(ui.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id().intValue() < 30) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}

		if (!zb && fgs) { // session用户不属于总部，属于分公司
			KonkaDept fgs_dept = super.getSuperiorForDeptType(ui.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			dynaBean.set("fgs_id", fgs_dept.getDept_id());
			dynaBean.set("fgs_name", fgs_dept.getDept_name());
		}

		// 遍历年份，取前10年
		ArrayList<String> yearList = new ArrayList<String>();
		SimpleDateFormat formaty = new SimpleDateFormat("yyyy");
		SimpleDateFormat formatm = new SimpleDateFormat("MM");

		for (int y = 0; y < 10; y++) {
			yearList.add((Integer.valueOf(formaty.format(new Date())) - y) + "");
		}
		request.setAttribute("yearList", yearList);

		dynaBean.set("year", formaty.format(new Date()));
		dynaBean.set("month", formatm.format(new Date()));

		// 取分公司列表
		List<KonkaDept> deptList = getDeptInfoListWithOutLimit(mapping, form, request, response);
		request.setAttribute("deptList", deptList);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String dept_id = (String) dynaBean.get("dept_id");

		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		FileInputStream isFile = null;
		String fileSavePath = null;
		String ctxDir = "";

		// List<UploadFile> uploadFileList = super.uploadFile(form, false);
		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH, false);
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
		ctxDir = request.getSession().getServletContext().getRealPath("/");
		// 附件保存路径更改遗留问题
		if (StringUtils.contains(fileSavePath, MmtFilePathConfig.S_OTHERS_PATH)) {
			ctxDir = "";
			fileSavePath = "/Attachment_new/konka-files/" + fileSavePath;
		}

		fileSavePath = ctxDir + fileSavePath;
		isFile = new FileInputStream(fileSavePath);

		List<KonkaDeptR3Pd> entityList = new ArrayList<KonkaDeptR3Pd>();
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
			KonkaDeptR3Pd entity = new KonkaDeptR3Pd();

			if (StringUtils.isNotBlank(dept_id)) {
				entity.setDept_id(Long.valueOf(dept_id));
				KonkaDept dp = new KonkaDept();
				dp.setDept_id(Long.valueOf(dept_id));
				dp.setDept_type(3);
				dp = super.getFacade().getKonkaDeptService().getKonkaDept(dp);
				entity.setDept_name(dp.getDept_name());
				entity.setDept_sn(dp.getDept_sn());
			}
			entity.setAdd_date(new Date());
			entity.setAdd_user_id(ui.getId());
			entity.setAdd_user_name(ui.getReal_name());

			String c0 = getCellText(row, startCol);
			String c1 = getCellText(row, startCol + 1);
			String c2 = getCellText(row, startCol + 2);
			String c3 = getCellText(row, startCol + 3);
			String c4 = getCellText(row, startCol + 4);
			String c5 = getCellText(row, startCol + 5);
			String c6 = getCellText(row, startCol + 6);

			if (StringUtils.isBlank(c0) && StringUtils.isBlank(c1) && StringUtils.isBlank(c2)
			        && StringUtils.isBlank(c3) && StringUtils.isBlank(c4) && StringUtils.isBlank(c5)) {
				break;
			}

			// 1. *R3客户编码
			if (StringUtils.isNotBlank(c0)) {
				entity.setR3_code(StringUtils.trim(c0));
			} else {
				excel_is_ok = false;
				entity.getMap().put("msg0", "【*R3客户编码】不能为空！");
			}
			entity.getMap().put("c0", c0);

			// 2. *型号
			if (!StringUtils.isNotBlank(c1)) {
				excel_is_ok = false;
				entity.getMap().put("msg1", "【*型号】不能为空！");
			} else {
				entity.setPd_sn(StringUtils.trim(c1));
			}
			entity.getMap().put("c1", c1);

			// 3. *年
			String v_c2 = c2.replaceAll(" ", "").replaceAll(" ", "");
			if (StringUtils.isNotBlank(v_c2) && GenericValidator.isInt(v_c2) && v_c2.length() == 4) {
				entity.setYear(Integer.valueOf(v_c2));
			} else {
				excel_is_ok = false;
				entity.getMap().put("msg2", "【*年份】不合法，必须填写4位整数！");
			}
			entity.getMap().put("c2", c2);

			// 4. *月
			String v_c3 = c3.replaceAll(" ", "").replaceAll(" ", "");
			if (StringUtils.isNotBlank(v_c3) && GenericValidator.isInt(v_c3) && v_c3.length() == 2) {
				entity.setMonth(Integer.valueOf(v_c3));
			} else {
				excel_is_ok = false;
				entity.getMap().put("msg3", "【*月份】不合法，必须填写2位整数。");
			}
			entity.getMap().put("c3", c3);

			//System.out.println("v_c2------>" + v_c2);
			//System.out.println("v_c3------>" + v_c3);
			// 开始时间和截止时间
			if (StringUtils.isNotBlank(v_c2) && StringUtils.isNotBlank(v_c3)) {
				String date = "";
				if (Integer.valueOf(v_c3) <= 12 && Integer.valueOf(v_c3) >= 10) {
					date = date + v_c2 + v_c3 + "01";
				} else if (Integer.valueOf(v_c3) < 10) {
					date = date + v_c2 + v_c3 + "01";
				} else {
					excel_is_ok = false;
					entity.getMap().put("msg3", "【*月份】不合法，必须是小于12的2位整数。");
				}
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

				//System.out.println("df-------->" + date);
				Date dd = df.parse(date);
				Calendar c = Calendar.getInstance();
				c.setTime(dd);
				c.add(Calendar.MONTH, 1);
				c.add(Calendar.DATE, -1);
				entity.setS_date(dd);
				entity.setE_date(c.getTime());

				entity.getMap().put("c3", c3);
			}

			// 5. *参考价格
			if (!StringUtils.isNotBlank(c4)) {
				excel_is_ok = false;
				entity.getMap().put("msg4", "【*参考价格】不能为空！");
			} else {
				entity.setPrice(new BigDecimal(StringUtils.trim(c4)));
			}
			entity.getMap().put("c4", c4);

			// 6. *最低价格
			if (!StringUtils.isNotBlank(c5)) {
				excel_is_ok = false;
				entity.getMap().put("msg5", "【*最低价格】不能为空！");
			} else {
				entity.setMin_price(new BigDecimal(StringUtils.trim(c5)));
			}
			entity.getMap().put("c5", c5);

			// 6. *最高价格
			if (!StringUtils.isNotBlank(c6)) {
				excel_is_ok = false;
				entity.getMap().put("msg5", "【*最高价格】不能为空！");
			} else {
				entity.setMax_price(new BigDecimal(StringUtils.trim(c6)));
			}
			entity.getMap().put("c6", c6);

			entityList.add(entity);
		}

		if (!excel_is_ok) {
			request.setAttribute("demoEntityList", entityList);
			return view(mapping, form, request, response);
		}

		String msg = super.getFacade().getKonkaDeptR3PdService().createKonkaDeptR3Pd(entityList);

		if (0 == entityList.size()) {
			super.saveError(request, "message", msg);
		} else {
			super.saveMessage(request, "message", msg);
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append("/../manager/admin/KonkaDeptR3Pd.do?method=list");
		pathBuffer.append("&mod_id=").append(mod_id).append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaDeptR3Pd entity = new KonkaDeptR3Pd();
		entity.setId(new Long(id));
		entity = getFacade().getKonkaDeptR3PdService().getKonkaDeptR3Pd(entity);

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		super.copyProperties(form, entity);

		return new ActionForward(response.encodeRedirectURL("/admin/KonkaDeptR3Pd/view2.jsp"));
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		KonkaDeptR3Pd entity = new KonkaDeptR3Pd();
		entity.setId(Long.valueOf(id));
		entity.setIs_del(1);
		entity.setUpdate_date(new Date());
		entity.setUpdate_user_id(ui.getId());
		entity.setUpdate_user_name(ui.getReal_name());
		super.getFacade().getKonkaDeptR3PdService().modifyKonkaDeptR3Pd(entity);

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

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		KonkaDeptR3Pd entity = new KonkaDeptR3Pd();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getKonkaDeptR3PdService().getKonkaDeptR3Pd(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);

		return new ActionForward(response.encodeRedirectURL("/admin/KonkaDeptR3Pd/edit.jsp"));
	}

	public ActionForward save1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaDeptR3Pd entity = new KonkaDeptR3Pd();
		super.copyProperties(entity, form);
		entity.setId(Long.valueOf(id));
		entity.setUpdate_date(new Date());
		entity.setUpdate_user_id(user.getId());
		entity.setUpdate_user_name(user.getUser_name());

		super.getFacade().getKonkaDeptR3PdService().modifyKonkaDeptR3Pd(entity);
		super.saveMessage(request, "entity.updated");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

	}

	private String getCellText(HSSFRow row, int col) {
		if (row.getCell(col) == null) {
			return "";
		}
		DecimalFormat df = new DecimalFormat("#00");
		HSSFCell cell = row.getCell(col);
		String data = "";
		if (null != cell) {
			if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				if (col == 4) {
					// data = String.valueOf((new
					// BigDecimal(cell.getNumericCellValue()).setScale(2,
					// BigDecimal.ROUND_HALF_UP).doubleValue()));
					data = df.format(cell.getNumericCellValue());
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
