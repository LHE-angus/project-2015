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

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.ChannelDataImport3;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Hu,Hao
 * @version 2013-12-19
 */
public class KonkaR3OrderYjhbAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);
		
		// 1.检查当前菜单连接是否有权限
		// if (null == super.checkUserModPopeDom(form, request, "0")) {
		// return super.checkPopedomInvalid(request, response);
		// }

		// 2.导航信息
		setNaviStringToRequestScope(form, request);
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
		// 是否导出
		String excel_all = (String) dynaBean.get("excel_all");
		
		ChannelDataImport3 entity = new ChannelDataImport3();
		entity.getMap().put("count1", true);
		
		if (StringUtils.isNotBlank(salesCode)) {
			entity.setColumn_25(salesCode.toLowerCase());
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
				entity.getMap().put("s_date", theFirstDayOfCurrentMonth() + " 00:00:00");
				dynaBean.set("s_date", theFirstDayOfCurrentMonth());
			}
		}

		if (StringUtils.isNotBlank(e_date)) {
			entity.getMap().put("e_date", e_date + " 23:59:59");
		} else {
			if (StringUtils.isBlank(is_first)) {
				entity.getMap().put("e_date", fmt1.format(new Date()) + " 23:59:59");
				dynaBean.set("e_date", fmt1.format(new Date()));
			}
		}

		if (StringUtils.isNotBlank(startDate1)) {
			entity.getMap().put("startDate1", startDate1 + " 00:00:00");
		}
		
		if (StringUtils.isNotBlank(endDate1)) {
			entity.getMap().put("endDate1", endDate1 + " 23:59:59");
		}
		
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		
		Long recordCount = super.getFacade().getChannelDataImport3Service().getChannelDataImport3Count(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<ChannelDataImport3> entityList = super.getFacade().getChannelDataImport3Service()
				.getChannelDataImport3PaginatedList(entity);
		
		// excel导出处理
		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("R3订单明细（总部）")
					+ ".xls");
			
			entity.getRow().setCount(recordCount.intValue());
			List<ChannelDataImport3> entityList1 = super.getFacade().getChannelDataImport3Service()
					.getChannelDataImport3PaginatedList(entity);
			request.setAttribute("entityList1", entityList1);
			
			return mapping.findForward("view");
		}
		
		request.setAttribute("entityList", entityList);
		request.setAttribute("recordCount", recordCount);
		return mapping.findForward("list");
	}
	
	public ActionForward imp(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);
		SimpleDateFormat formaty = new SimpleDateFormat("yyyy");
		
		// 遍历年份，取前10年
		ArrayList<String> yearList = new ArrayList<String>();
		
		for (int y = 0; y < 10; y++) {
			yearList.add((Integer.valueOf(formaty.format(new Date())) - y) + "");
		}
		request.setAttribute("yearList", yearList);

		return new ActionForward("/../manager/admin/KonkaR3OrderYjhb/impform.jsp");
		// return mapping.findForward("input");
	}

	public ActionForward impSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");

		FileInputStream isFile = null;
		String fileSavePath = null;

		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH);
		for (UploadFile uploadFile : uploadFileList) {
			if ("up_load_file".equals(uploadFile.getFormName())) {
				fileSavePath = uploadFile.getFileSavePath();
			}
		}

		String filetype = fileSavePath.substring(fileSavePath.lastIndexOf(".") + 1);
		if (!filetype.equalsIgnoreCase("xls")) {
			super.renderJavaScript(response, "alert('上传数据格式不正确！');history.back();");
			return null;
		}
		String ctxDir = request.getSession().getServletContext().getRealPath("/");
		// 附件保存路径更改遗留问题
		if (StringUtils.contains(fileSavePath, MmtFilePathConfig.S_OTHERS_PATH)) {
			ctxDir = "";
			fileSavePath = "/Attachment_new/konka-files/"+ fileSavePath;
		}

		fileSavePath = ctxDir + fileSavePath;
		isFile = new FileInputStream(fileSavePath);

		List<ChannelDataImport3> entityList = new ArrayList<ChannelDataImport3>();
		boolean excel_is_ok = true;// 验证通过标识位 是否是数字
		String regEx = "^-?\\d{1,10}(\\.\\d+)?$";
		String regDate = "^[1-9][0-9]{3}-[0-9]{2}-[0-9]{2}$";
		Pattern vali_dig = Pattern.compile(regEx);
		Pattern vali_date = Pattern.compile(regDate);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		int startRow = 2;
		int startCol = 0;
		HSSFWorkbook workbook = new HSSFWorkbook(isFile);
		HSSFSheet sheet = null;

		// 验证数据完整性BEGIN
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			sheet = workbook.getSheetAt(i);
			int rowCounts = sheet.getLastRowNum();// 行数

			for (int j = startRow; j <= rowCounts; j++) {// 获取每行
				HSSFRow row = sheet.getRow(j);
				ChannelDataImport3 data = new ChannelDataImport3();

				String c0 = getCellText(row, startCol);
				String c1 = getCellText(row, startCol + 1);
				String c2 = getCellText(row, startCol + 2);
				String c3 = getCellText(row, startCol + 3);
				String c4 = getCellText(row, startCol + 4);
				String c5 = getCellText(row, startCol + 5);
				String c6 = getCellText(row, startCol + 6);
				String c7 = getCellText(row, startCol + 7);
				String c8 = getCellText(row, startCol + 8);
				String c9 = getCellText(row, startCol + 9);
				String c10 = getCellText(row, startCol + 10);
				String c11 = getCellText(row, startCol + 11);
				String c12 = getCellText(row, startCol + 12);
				String c13 = getCellText(row, startCol + 13);
				String c14 = getCellText(row, startCol + 14);
				String c15 = getCellText(row, startCol + 15);
				String c16 = getCellText(row, startCol + 16);
				String c17 = getCellText(row, startCol + 17);
				String c18 = getCellText(row, startCol + 18);
				String c19 = getCellText(row, startCol + 19);
				String c20 = getCellText(row, startCol + 20);

				if (StringUtils.isBlank(c0) && StringUtils.isBlank(c2) && StringUtils.isBlank(c3)
						&& StringUtils.isBlank(c7)&& StringUtils.isBlank(c8)&& StringUtils.isBlank(c9)&& StringUtils.isBlank(c10)&& StringUtils.isBlank(c11)
						&& StringUtils.isBlank(c16)&& StringUtils.isBlank(c17) && StringUtils.isBlank(c18) && StringUtils.isBlank(c19) && StringUtils.isBlank(c20)) {
					break;
				}

				// 1.售达方
				if (StringUtils.isNotBlank(c0)) {
					data.setColumn_1(StringUtils.trim(c0));
				} else {
					excel_is_ok = false;
					data.getMap().put("msg0", "售达方不能为空！");
				}
				data.getMap().put("c0", c0);

				// 2.送达方
				data.setColumn_5(StringUtils.trim(c1));
				data.getMap().put("c1", c1);

				// 3. 订单创建日期
				if (StringUtils.isNotBlank(c2) && vali_date.matcher(c2).matches()) {
					data.setColumn_7(format.parse(c2));
				} else {
					excel_is_ok = false;
					data.getMap().put("msg2", "【*订单创建日期】不能为空或日期格式错误，请填写正确的日期格式: yyyy-MM-dd！");
				}
				data.getMap().put("c2", c2);

				// 销售单号
				data.setColumn_8(StringUtils.trim(c3));
				data.getMap().put("c3", c3);

				// 订单类型
				data.setColumn_9(StringUtils.trim(c4));
				data.getMap().put("c4", c4);

				// 项目号
				data.setColumn_10(StringUtils.trim(c5));
				data.getMap().put("c5", c5);

				// 机型
				data.setColumn_11(StringUtils.trim(c6));
				data.getMap().put("c6", c6);

				// 每一订单行机型数量
				if (StringUtils.isNotBlank(c7)) {
					String c7v = StringUtils.replace(StringUtils.replace(c7, ",", ""), "，", "");

					if (StringUtils.isNotBlank(c7v) && vali_dig.matcher(c7v).matches()) {
						data.setColumn_12(c7v);
					} else {
						excel_is_ok = false;
						data.getMap().put("msg7", "【每一行订单机型数量】不合法，必须填写数字。");
					}
					data.getMap().put("c7", subZeroAndDot(c7v));
				}

				// 含税单价
				if (StringUtils.isNotBlank(c8)) {
					String c8v = StringUtils.replace(StringUtils.replace(c8, ",", ""), "，", "");

					if (StringUtils.isNotBlank(c8v) && vali_dig.matcher(c8v).matches()) {
						data.setColumn_13(new BigDecimal(c8v));
					} else {
						excel_is_ok = false;
						data.getMap().put("msg8", "【含税单价】不合法，必须填写数字。");
					}
					data.getMap().put("c8", subZeroAndDot(c8v));
				}

				// 总金额(含税)
				if (StringUtils.isNotBlank(c9)) {
					String c9v = StringUtils.replace(StringUtils.replace(c9, ",", ""), "，", "");

					if (StringUtils.isNotBlank(c9v) && vali_dig.matcher(c9v).matches()) {
						data.setColumn_14(new BigDecimal(c9v));
					} else {
						excel_is_ok = false;
						data.getMap().put("msg9", "【总金额(含税)】不合法，必须填写数字。");
					}
					data.getMap().put("c9", subZeroAndDot(c9v));
				}

				// 007(含税)
				if (StringUtils.isNotBlank(c10)) {
					String c10v = StringUtils.replace(StringUtils.replace(c10, ",", ""), "，", "");

					if (StringUtils.isNotBlank(c10v) && vali_dig.matcher(c10v).matches()) {
						data.setColumn_15(new BigDecimal(c10v));
					} else {
						excel_is_ok = false;
						data.getMap().put("msg10", "【007(含税)】不合法，必须填写数字。");
					}
					data.getMap().put("c10", subZeroAndDot(c10v));
				}

				// 总净挣值
				if (StringUtils.isNotBlank(c11)) {
					String c11v = StringUtils.replace(StringUtils.replace(c11, ",", ""), "，", "");

					if (StringUtils.isNotBlank(c11v) && vali_dig.matcher(c11v).matches()) {
						data.setColumn_17(new BigDecimal(c11v));
					} else {
						excel_is_ok = false;
						data.getMap().put("msg11", "【总净挣值】不合法，必须填写数字。");
					}
					data.getMap().put("c11", subZeroAndDot(c11v));
				}

				// 采购订单编号
				data.setColumn_22(c12);
				data.getMap().put("c12", c12);

				// 物料组
				data.setColumn_23(c13);
				data.getMap().put("c13", c13);

				// 办事处
				data.setColumn_24(c14);
				data.getMap().put("c14", c14);

				// 销售组织
				data.setColumn_25(c15);
				data.getMap().put("c15", c15);

				// R3订单凭证日期
				if (StringUtils.isNotBlank(c16) && vali_date.matcher(c16).matches()) {
					data.setColumn_26(format.parse(c16));
				} else {
					excel_is_ok = false;
					data.getMap().put("msg16", "【R3订单凭证日期】不能为空或日期格式错误，请填写正确的日期格式: yyyy-MM-dd！");
				}
				data.getMap().put("c16", c16);

				// 交货单数量
				if (StringUtils.isNotBlank(c17)) {
					String c17v = StringUtils.replace(StringUtils.replace(c17, ",", ""), "，", "");

					if (StringUtils.isNotBlank(c17v) && vali_dig.matcher(c17v).matches()) {
						data.setColumn_27(new BigDecimal(c17v));
					} else {
						excel_is_ok = false;
						data.getMap().put("msg17", "【交货单数量】不合法，必须填写数字。");
					}
					data.getMap().put("c17", subZeroAndDot(c17v));
				}

				// 已发货数量
				if (StringUtils.isNotBlank(c18)) {
					String c18v = StringUtils.replace(StringUtils.replace(c18, ",", ""), "，", "");

					if (StringUtils.isNotBlank(c18v) && vali_dig.matcher(c18v).matches()) {
						data.setColumn_28(new BigDecimal(c18v));
					} else {
						excel_is_ok = false;
						data.getMap().put("msg18", "【已发货数量】不合法，必须填写数字。");
					}
					data.getMap().put("c18", subZeroAndDot(c18v));
				}

				// 已开发票数量
				if (StringUtils.isNotBlank(c19)) {
					String c19v = StringUtils.replace(StringUtils.replace(c19, ",", ""), "，", "");

					if (StringUtils.isNotBlank(c19v) && vali_dig.matcher(c19v).matches()) {
						data.setColumn_29(new BigDecimal(c19v));
					} else {
						excel_is_ok = false;
						data.getMap().put("msg19", "【已发货数量】不合法，必须填写数字。");
					}
					data.getMap().put("c19", subZeroAndDot(c19v));
				}

				// 客户采购订单日期
				if (StringUtils.isNotBlank(c20) && vali_date.matcher(c20).matches()) {
					data.setColumn_31(format.parse(c20));
				} else {
					excel_is_ok = false;
					data.getMap().put("msg20", "【客户采购订单日期】不能为空或日期格式错误，请填写正确的日期格式: yyyy-MM-dd！");
				}
				data.getMap().put("c20", c20);
				
				entityList.add(data);
			}
		}

		if (!excel_is_ok) {
			request.setAttribute("demoEntityList", entityList);
			return new ActionForward("/../manager/admin/KonkaR3OrderYjhb/impview.jsp");
		}

		String m = year + month;

		Long exsit_count = super.getFacade().getChannelDataImport3Service().createChannelDataImport3ForImp(entityList,Long.valueOf(m));

		String msg = "成功导入 " + (entityList.size()) + " 条数据！";
		if (0 != (entityList.size() - exsit_count)) {
			super.saveError(request, "message", msg);
		} else {
			super.saveMessage(request, "message", msg);
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append("/../manager/admin/KonkaR3OrderYjhb.do?method=list");
		pathBuffer.append("&mod_id=").append(mod_id).append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	private String getCellText(HSSFRow row, int col) {
		if (row.getCell(col) == null) {
			return "";
		}
		DecimalFormat df = new DecimalFormat("#0");
		DecimalFormat df1 = new DecimalFormat("#0.00");
		HSSFCell cell = row.getCell(col);
		String data = "";
		if (null != cell) {
			if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				if (col != 7 && col != 2) {
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
