package com.ebiz.mmt.web.struts.manager.admin;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSONObject;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaPersonWage;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.mmt.web.util.IDCard;
import com.ebiz.mmt.web.util.ListVerify;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class SalaryManageAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			String msg = super.getMessage(request, "wage.session.userinfo.none");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		}

		Date year = new Date();
		request.setAttribute("year", DateFormatUtils.format(year, "yyyy"));
		return mapping.findForward("list");
	}

	@SuppressWarnings("static-access")
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String mark = "insert";

		if (StringUtils.isBlank(year) || StringUtils.isBlank(month)) {
			super.renderJavaScript(response, "alert('参数丢失！');history.back();");
			return null;
		}

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			super.renderJavaScript(response, "alert('登录信息丢失！');history.back();");
			return null;
		}

		KonkaDept fgs = super.getSuperiorForDeptType(user.getDept_id(), 3);
		if (null == fgs) {
			super.renderJavaScript(response, "alert('未查询到分公司信息！');history.back();");
			return null;
		}

		KonkaPersonWage temp = new KonkaPersonWage();
		temp.setY(Integer.valueOf(year));
		temp.setM(Integer.valueOf(month));
		temp.setFgs_dept_sn(fgs.getDept_id().toString());
		Long countRecord = getFacade().getKonkaPersonWageService().getKonkaPersonWageCount(temp);
		if (countRecord.intValue() > 0) {
			mark = "update";
		}

		Date now = new Date();

		FileInputStream isFile = null;
		String fileSavePath = null;

		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH);
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
		if (StringUtils.contains(fileSavePath, MmtFilePathConfig.S_OTHERS_PATH)) {
			ctxDir = "";
			fileSavePath = "/Attachment_new/konka-files/"+ fileSavePath;
		}
		fileSavePath = ctxDir + fileSavePath;
		isFile = new FileInputStream(fileSavePath);

		KonkaPersonWage entity = new KonkaPersonWage();
		entity.setY(Integer.valueOf(year));
		entity.setM(Integer.valueOf(month));
		entity.setFgs_dept_sn(fgs.getDept_id().toString());
		List<KonkaPersonWage> entityList = new ArrayList<KonkaPersonWage>();
		boolean verifi_flag = true;// 验证通过标识位

		List<String> idCardList = new ArrayList<String>(); // 身份证list

		if (filetype.equalsIgnoreCase("xls") || filetype.equalsIgnoreCase("xlsx")) {
			HSSFWorkbook workbook = new HSSFWorkbook(isFile);
			HSSFSheet sheet = null;
			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {// 获取每个Sheet表
				sheet = workbook.getSheetAt(i);
				int rowNum = sheet.getLastRowNum();// 行数
				for (int j = 2; j <= rowNum; j++) {// 获取每行
					HSSFRow row = sheet.getRow(j);

					KonkaPersonWage wage = new KonkaPersonWage();
					wage.setFgs_dept_sn(fgs.getDept_id().toString());
					wage.setFgs_dept_name(fgs.getDept_name());
					wage.setAdd_date(now);

					String y = getCellText(row, 1); // 年份
					String m = getCellText(row, 2); // 月份
					String id_card_no = getCellText(row, 3); // 身份证号码
					String employ_date = getCellText(row, 4); // 入职日期
					String employ_years = getCellText(row, 5); // 入职年限
					String real_name = getCellText(row, 6); // 员工姓名
					String month_rank = getCellText(row, 7); // 月度排名
					String w_std = getCellText(row, 8); // 标准工资
					String w_base = getCellText(row, 9); // 基本工资
					String employ_year_w = getCellText(row, 10); // 工龄工资
					String w_float = getCellText(row, 11); // 浮动工资
					String w_allowance = getCellText(row, 12); // 补助津贴
					String w_rewards = getCellText(row, 13); // 提成奖金
					String w_overtime = getCellText(row, 14); // 加班费
					String w_encourage = getCellText(row, 15); // 奖励金额
					String w_deduct = getCellText(row, 16); // 扣款金额
					String sale_money = getCellText(row, 17); // 销售额
					String sale_num = getCellText(row, 18); // 销售量
					String loss_profit = getCellText(row, 19); // 负毛利
					String w_ss = getCellText(row, 20); // 社保
					String w_cpf = getCellText(row, 21); // 公积金
					String w_before_tax = getCellText(row, 22); // 税前工资
					String w_money_of_tax = getCellText(row, 23); // 税金
					String w_after_tax = getCellText(row, 24); // 税后工资
					String w_other = getCellText(row, 25); // 其他
					String w_fact = getCellText(row, 26); // 实发工资

					// 验证
					verifi_flag = cellValidate(wage, 1, y, verifi_flag, year);
					verifi_flag = cellValidate(wage, 2, m, verifi_flag, month);
					verifi_flag = cellValidate(wage, 3, id_card_no, verifi_flag, null);
					verifi_flag = cellValidate(wage, 4, employ_date, verifi_flag, null);
					verifi_flag = cellValidate(wage, 5, employ_years, verifi_flag, null);
					verifi_flag = cellValidate(wage, 6, real_name, verifi_flag, null);
					verifi_flag = cellValidate(wage, 7, month_rank, verifi_flag, null);
					verifi_flag = cellValidate(wage, 8, w_std, verifi_flag, null);
					verifi_flag = cellValidate(wage, 9, w_base, verifi_flag, null);
					verifi_flag = cellValidate(wage, 10, employ_year_w, verifi_flag, null);
					verifi_flag = cellValidate(wage, 11, w_float, verifi_flag, null);
					verifi_flag = cellValidate(wage, 12, w_allowance, verifi_flag, null);
					verifi_flag = cellValidate(wage, 13, w_rewards, verifi_flag, null);
					verifi_flag = cellValidate(wage, 14, w_overtime, verifi_flag, null);
					verifi_flag = cellValidate(wage, 15, w_encourage, verifi_flag, null);
					verifi_flag = cellValidate(wage, 16, w_deduct, verifi_flag, null);
					verifi_flag = cellValidate(wage, 17, sale_money, verifi_flag, null);
					verifi_flag = cellValidate(wage, 18, sale_num, verifi_flag, null);
					verifi_flag = cellValidate(wage, 19, loss_profit, verifi_flag, null);
					verifi_flag = cellValidate(wage, 20, w_ss, verifi_flag, null);
					verifi_flag = cellValidate(wage, 21, w_cpf, verifi_flag, null);
					verifi_flag = cellValidate(wage, 22, w_before_tax, verifi_flag, null);
					verifi_flag = cellValidate(wage, 23, w_money_of_tax, verifi_flag, null);
					verifi_flag = cellValidate(wage, 24, w_after_tax, verifi_flag, null);
					verifi_flag = cellValidate(wage, 25, w_other, verifi_flag, null);
					verifi_flag = cellValidate(wage, 26, w_fact, verifi_flag, null);

					idCardList.add(id_card_no);
					entityList.add(wage);
				}
			}
		}
		if (!verifi_flag) {
			request.setAttribute("entityList", entityList);
			return list(mapping, form, request, response);
		}

		// 验证上传文件中是否有重复身份证号码Begin,2013-08-04
		ListVerify verify = new ListVerify();
		for (KonkaPersonWage wage : entityList) {
			String idCard = wage.getId_card_no();
			int[] indexArray = verify.reduplicateIndex(idCardList, idCard);
			if (indexArray.length > 1) { // 该身份证有重复记录
				verifi_flag = false;
				wage.getMap().put("msg3",
						"上传文件中身份证号码<br/><span style=\\'color:#8B0000;\\'>【" + idCard + "】</span>有重复，请认真核对！");
				wage.getMap().put("con3", idCard);
			}
		}
		for (KonkaPersonWage wage : entityList) {
			KonkaPersonWage ww = new KonkaPersonWage();
			ww.setId_card_no(wage.getId_card_no());
			ww.setY(Integer.valueOf(year));
			ww.setM(Integer.valueOf(month));
			ww = super.getFacade().getKonkaPersonWageService().getKonkaPersonWage(ww);
			if (null != ww) {
				if (!ww.getFgs_dept_sn().equals(
						super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id().toString())) {
					verifi_flag = false;
					String msg = "身份证号码" + ww.getId_card_no() + "在别的分公司已经存在！你认真核对！";
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
					return null;

				}
			}
		}
		if (!verifi_flag) {
			request.setAttribute("entityList", entityList);
			return list(mapping, form, request, response);
		}
		// 验证上传文件中是否有重复身份证号码End

		// 验证当前年，月，该身份证是否有记录Begin
		// 验证当前年，月，该身份证是否有记录End

		entity.getMap().put("mark", mark);
		entity.setKonkaPersonWageList(entityList);
		super.getFacade().getKonkaPersonWageService().createKonkaPersonWageForImport(entity);
		super.saveMessage(request, "entity.imported");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=").append(mod_id);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward verifyRepeat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String state = "0";

		if (StringUtils.isBlank(year) || StringUtils.isBlank(month)) {
			state = "0";
		}

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			state = "-1";// session丢失
		}

		JSONObject result = new JSONObject();
		KonkaDept fgs = super.getSuperiorForDeptType(user.getDept_id(), 3);
		if (null == fgs) {
			state = "-2";// 未查询到分公司
		} else {
			KonkaPersonWage entity = new KonkaPersonWage();
			entity.setY(Integer.valueOf(year));
			entity.setM(Integer.valueOf(month));
			entity.setFgs_dept_sn(fgs.getDept_id().toString());
			Long countRecord = getFacade().getKonkaPersonWageService().getKonkaPersonWageCount(entity);
			if (countRecord.intValue() <= 0) {
				state = "1";
			} else {
				state = "2";// 该分公司当年当月的工资excel已上传
				result.put("fgs_name", fgs.getDept_name());
			}
		}
		result.put("state", state);

		logger.info("_________________________result.toString() = " + result.toString());
		super.renderJson(response, result.toString());
		return null;
	}

	/**
	 * @param row
	 * @param col
	 * @return
	 * @throws ParseException
	 */
	private boolean cellValidate(KonkaPersonWage wage, Integer type, String cellText, boolean verifi_flag, String date)
			throws ParseException {
		switch (type) {
		case 1:// 年份
			if ("".equals(subZeroAndDot(cellText))) {
				verifi_flag = false;
				wage.getMap().put("msg1", "<span style=\\'color:#EE0000;\\'>【工资年份】</span>不能为空！");
				wage.getMap().put("con1", subZeroAndDot(cellText));
			} else {
				if (GenericValidator.isInt(subZeroAndDot(cellText))) {
					if (StringUtils.equals(date, subZeroAndDot(cellText))) {
						wage.setY(Integer.valueOf(subZeroAndDot(cellText)));
					} else {
						verifi_flag = false;
						wage.getMap().put(
								"msg1",
								"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(cellText)
										+ "】</span>非法！与您选择的年份【" + date + "】不一致！");
						wage.getMap().put("con1", subZeroAndDot(cellText));
					}
				} else {
					verifi_flag = false;
					wage.getMap().put("msg1",
							"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(cellText) + "】</span>非法！必须填写整数！");
					wage.getMap().put("con1", subZeroAndDot(cellText));
				}
			}
			break;
		case 2:// 月份
			if ("".equals(subZeroAndDot(cellText))) {
				verifi_flag = false;
				wage.getMap().put("msg2", "<span style=\\'color:#EE0000;\\'>【工资月份】</span>不能为空！");
				wage.getMap().put("con2", subZeroAndDot(cellText));
			} else {
				if (GenericValidator.isInt(subZeroAndDot(cellText))) {
					if (StringUtils.equals(date, subZeroAndDot(cellText))) {
						wage.setM(Integer.valueOf(subZeroAndDot(cellText)));
					} else {
						verifi_flag = false;
						wage.getMap().put(
								"msg2",
								"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(cellText)
										+ "】</span>非法！与您选择的月份【" + date + "】不一致！");
						wage.getMap().put("con2", subZeroAndDot(cellText));
					}
				} else {
					verifi_flag = false;
					wage.getMap().put("msg2",
							"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(cellText) + "】</span>非法！必须填写整数！");
					wage.getMap().put("con2", subZeroAndDot(cellText));
				}
			}
			break;
		case 3:// 身份证号码
			IDCard idCard = new IDCard();
			if ("".equals(cellText)) {
				verifi_flag = false;
				wage.getMap().put("msg3", "<span style=\\'color:#EE0000;\\'>【身份证号码】</span>不能为空！");
				wage.getMap().put("con3", cellText);
			} else {
				String result = idCard.IDCardValidate(cellText);
				if ("0".equals(result)) {
					wage.setId_card_no(cellText);
				} else {
					verifi_flag = false;
					wage.getMap().put("msg3", result);
					wage.getMap().put("con3", cellText);
				}
			}
			break;
		case 4:// 入职日期
			if (!"".equals(subZeroAndDot(cellText))) {
				if (GenericValidator.isDate(cellText, "yyyy-MM-dd", true)) {
					Date _date = DateUtils.parseDate(cellText, new String[] { "yyyy-MM-dd" });
					wage.setEmploy_date(_date);
				} else {
					verifi_flag = false;
					wage.getMap().put(
							"msg4",
							"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(cellText)
									+ "】</span>非法！格式必须是yyyy-MM-dd");
					wage.getMap().put("con4", subZeroAndDot(cellText));
				}
			}
			break;
		case 5:// 入职年限
			if (!"".equals(cellText)) {
				wage.setEmploy_years(cellText);
			}
			break;
		case 6:// 员工姓名
			if ("".equals(cellText)) {
				verifi_flag = false;
				wage.getMap().put("msg6", "<span style=\\'color:#EE0000;\\'>【员工姓名】</span>不能为空！");
				wage.getMap().put("con6", cellText);
			} else {
				wage.setReal_name(cellText);
			}
			break;
		case 7:// 月度排名
			if (!"".equals(subZeroAndDot(cellText))) {
				if (GenericValidator.isInt(subZeroAndDot(cellText))) {
					wage.setMonth_rank(Integer.valueOf(subZeroAndDot(cellText)));
				} else {
					verifi_flag = false;
					wage.getMap().put("msg7",
							"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(cellText) + "】</span>非法！必须填写整数！");
				}
			}
			break;
		case 8:// 标准工资
			if ("".equals(subZeroAndDot(cellText))) {
				verifi_flag = false;
				wage.getMap().put("msg8", "<span style=\\'color:#EE0000;\\'>【标准工资】</span>不能为空！");
				wage.getMap().put("con8", subZeroAndDot(cellText));
			} else {
				if (GenericValidator.isDouble(subZeroAndDot(cellText))) {
					wage.setW_std(new BigDecimal(subZeroAndDot(cellText)));
				} else {
					verifi_flag = false;
					wage.getMap().put("msg8",
							"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(cellText) + "】</span>非法！必须填写数字！");
					wage.getMap().put("con8", subZeroAndDot(cellText));
				}
			}
			break;
		case 9:// 基本工资
			if ("".equals(subZeroAndDot(cellText))) {
				verifi_flag = false;
				wage.getMap().put("msg9", "<span style=\\'color:#EE0000;\\'>【基本工资】</span>不能为空！");
				wage.getMap().put("con9", subZeroAndDot(cellText));
			} else {
				if (GenericValidator.isDouble(subZeroAndDot(cellText))) {
					wage.setW_base(new BigDecimal(subZeroAndDot(cellText)));
				} else {
					verifi_flag = false;
					wage.getMap().put("msg9",
							"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(cellText) + "】</span>非法！必须填写数字！");
					wage.getMap().put("con9", subZeroAndDot(cellText));
				}
			}
			break;
		case 10:// 工龄工资
			if (!"".equals(subZeroAndDot(cellText))) {
				if (GenericValidator.isDouble(subZeroAndDot(cellText))) {
					wage.setEmploy_year_w(new BigDecimal(subZeroAndDot(cellText)));
				} else {
					verifi_flag = false;
					wage.getMap().put("msg10",
							"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(cellText) + "】</span>非法！必须填写数字！");
					wage.getMap().put("con10", subZeroAndDot(cellText));
				}
			}
			break;
		case 11:// 浮动工资
			if ("".equals(subZeroAndDot(cellText))) {
				verifi_flag = false;
				wage.getMap().put("msg11", "<span style=\\'color:#EE0000;\\'>【浮动工资】</span>不能为空！");
				wage.getMap().put("con11", subZeroAndDot(cellText));
			} else {
				if (GenericValidator.isDouble(subZeroAndDot(cellText))) {
					wage.setW_float(new BigDecimal(subZeroAndDot(cellText)));
				} else {
					verifi_flag = false;
					wage.getMap().put("msg11",
							"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(cellText) + "】</span>非法！必须填写数字！");
					wage.getMap().put("con11", subZeroAndDot(cellText));
				}
			}
			break;
		case 12:// 补助津贴
			if ("".equals(subZeroAndDot(cellText))) {
				verifi_flag = false;
				wage.getMap().put("msg12", "<span style=\\'color:#EE0000;\\'>【补助津贴】</span>不能为空！");
				wage.getMap().put("con12", subZeroAndDot(cellText));
			} else {
				if (GenericValidator.isDouble(subZeroAndDot(cellText))) {
					wage.setW_allowance(new BigDecimal(subZeroAndDot(cellText)));
				} else {
					verifi_flag = false;
					wage.getMap().put("msg12",
							"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(cellText) + "】</span>非法！必须填写数字！");
					wage.getMap().put("con12", subZeroAndDot(cellText));
				}
			}
			break;
		case 13:// 提成奖金
			if ("".equals(subZeroAndDot(cellText))) {
				verifi_flag = false;
				wage.getMap().put("msg13", "<span style=\\'color:#EE0000;\\'>【提成奖金】</span>不能为空！");
				wage.getMap().put("con13", subZeroAndDot(cellText));
			} else {
				if (GenericValidator.isDouble(subZeroAndDot(cellText))) {
					wage.setW_rewards(new BigDecimal(subZeroAndDot(cellText)));
				} else {
					verifi_flag = false;
					wage.getMap().put("msg13",
							"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(cellText) + "】</span>非法！必须填写数字！");
					wage.getMap().put("con13", subZeroAndDot(cellText));
				}
			}
			break;
		case 14:// 加班费
			if ("".equals(subZeroAndDot(cellText))) {
				verifi_flag = false;
				wage.getMap().put("msg14", "<span style=\\'color:#EE0000;\\'>【加班费】</span>不能为空！");
				wage.getMap().put("con14", subZeroAndDot(cellText));
			} else {
				if (GenericValidator.isDouble(subZeroAndDot(cellText))) {
					wage.setW_overtime(new BigDecimal(subZeroAndDot(cellText)));
				} else {
					verifi_flag = false;
					wage.getMap().put("msg14",
							"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(cellText) + "】</span>非法！必须填写数字！");
					wage.getMap().put("con14", subZeroAndDot(cellText));
				}
			}
			break;
		case 15:// 奖励金额
			if ("".equals(subZeroAndDot(cellText))) {
				verifi_flag = false;
				wage.getMap().put("msg15", "<span style=\\'color:#EE0000;\\'>【奖励金额】</span>不能为空！");
				wage.getMap().put("con15", subZeroAndDot(cellText));
			} else {
				if (GenericValidator.isDouble(subZeroAndDot(cellText))) {
					wage.setW_encourage(new BigDecimal(subZeroAndDot(cellText)));
				} else {
					verifi_flag = false;
					wage.getMap().put("msg15",
							"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(cellText) + "】</span>非法！必须填写数字！");
					wage.getMap().put("con15", subZeroAndDot(cellText));
				}
			}
			break;
		case 16:// 扣款金额
			if ("".equals(subZeroAndDot(cellText))) {
				verifi_flag = false;
				wage.getMap().put("msg16", "<span style=\\'color:#EE0000;\\'>【扣款金额】</span>不能为空！");
				wage.getMap().put("con16", subZeroAndDot(cellText));
			} else {
				if (GenericValidator.isDouble(subZeroAndDot(cellText))) {
					wage.setW_deduct(new BigDecimal(subZeroAndDot(cellText)));
				} else {
					verifi_flag = false;
					wage.getMap().put("msg16",
							"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(cellText) + "】</span>非法！必须填写数字！");
					wage.getMap().put("con16", subZeroAndDot(cellText));
				}
			}
			break;
		case 17:// 销售额
			if (!"".equals(subZeroAndDot(cellText))) {
				if (GenericValidator.isDouble(subZeroAndDot(cellText))) {
					wage.setSale_money(new BigDecimal(subZeroAndDot(cellText)));
				} else {
					verifi_flag = false;
					wage.getMap().put("msg17",
							"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(cellText) + "】</span>非法！必须填写数字！");
					wage.getMap().put("con17", subZeroAndDot(cellText));
				}
			}
			break;
		case 18:// 销售量
			if (!"".equals(subZeroAndDot(cellText))) {
				if (GenericValidator.isInt(subZeroAndDot(cellText))) {
					wage.setSale_num(Integer.parseInt(subZeroAndDot(cellText)));
				} else {
					verifi_flag = false;
					wage.getMap().put("msg18",
							"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(cellText) + "】</span>非法！必须填写数字！");
					wage.getMap().put("con18", subZeroAndDot(cellText));
				}
			}
			break;
		case 19:// 负毛利
			if (!"".equals(subZeroAndDot(cellText))) {
				if (GenericValidator.isDouble(subZeroAndDot(cellText))) {
					wage.setLoss_profit(new BigDecimal(subZeroAndDot(cellText)));
				} else {
					verifi_flag = false;
					wage.getMap().put("msg19",
							"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(cellText) + "】</span>非法！必须填写数字！");
					wage.getMap().put("con19", subZeroAndDot(cellText));
				}
			}
			break;
		case 20:// 社保
			if ("".equals(subZeroAndDot(cellText))) {
				verifi_flag = false;
				wage.getMap().put("msg20", "<span style=\\'color:#EE0000;\\'>【社保】</span>不能为空！");
				wage.getMap().put("con20", subZeroAndDot(cellText));
			} else {
				if (GenericValidator.isDouble(subZeroAndDot(cellText))) {
					wage.setW_ss(new BigDecimal(subZeroAndDot(cellText)));
				} else {
					verifi_flag = false;
					wage.getMap().put("msg20",
							"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(cellText) + "】</span>非法！必须填写数字！");
					wage.getMap().put("con20", subZeroAndDot(cellText));
				}
			}
			break;
		case 21:// 公积金
			if ("".equals(subZeroAndDot(cellText))) {
				verifi_flag = false;
				wage.getMap().put("msg21", "<span style=\\'color:#EE0000;\\'>【公积金】</span>不能为空！");
				wage.getMap().put("con21", subZeroAndDot(cellText));
			} else {
				if (GenericValidator.isDouble(subZeroAndDot(cellText))) {
					wage.setW_cpf(new BigDecimal(subZeroAndDot(cellText)));
				} else {
					verifi_flag = false;
					wage.getMap().put("msg21",
							"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(cellText) + "】</span>非法！必须填写数字！");
					wage.getMap().put("con21", subZeroAndDot(cellText));
				}
			}
			break;
		case 22:// 税前工资
			if ("".equals(subZeroAndDot(cellText))) {
				verifi_flag = false;
				wage.getMap().put("msg22", "<span style=\\'color:#EE0000;\\'>【税前工资】</span>不能为空！");
				wage.getMap().put("con22", subZeroAndDot(cellText));
			} else {
				if (GenericValidator.isDouble(subZeroAndDot(cellText))) {
					wage.setW_before_tax(new BigDecimal(subZeroAndDot(cellText)));
				} else {
					verifi_flag = false;
					wage.getMap().put("msg22",
							"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(cellText) + "】</span>非法！必须填写数字！");
					wage.getMap().put("con22", subZeroAndDot(cellText));
				}
			}
			break;
		case 23:// 税金
			if ("".equals(subZeroAndDot(cellText))) {
				verifi_flag = false;
				wage.getMap().put("msg23", "<span style=\\'color:#EE0000;\\'>【税金】</span>不能为空！");
				wage.getMap().put("con23", subZeroAndDot(cellText));
			} else {
				if (GenericValidator.isDouble(subZeroAndDot(cellText))) {
					wage.setW_money_of_tax(new BigDecimal(subZeroAndDot(cellText)));
				} else {
					verifi_flag = false;
					wage.getMap().put("msg23",
							"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(cellText) + "】</span>非法！必须填写数字！");
					wage.getMap().put("con23", subZeroAndDot(cellText));
				}
			}
			break;
		case 24:// 税后工资
			if ("".equals(subZeroAndDot(cellText))) {
				verifi_flag = false;
				wage.getMap().put("msg24", "<span style=\\'color:#EE0000;\\'>【税后工资】</span>不能为空！");
				wage.getMap().put("con24", subZeroAndDot(cellText));
			} else {
				if (GenericValidator.isDouble(subZeroAndDot(cellText))) {
					wage.setW_after_tax(new BigDecimal(subZeroAndDot(cellText)));
				} else {
					verifi_flag = false;
					wage.getMap().put("msg24",
							"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(cellText) + "】</span>非法！必须填写数字！");
					wage.getMap().put("con24", subZeroAndDot(cellText));
				}
			}
			break;
		case 25:// 其他
			if ("".equals(subZeroAndDot(cellText))) {
				verifi_flag = false;
				wage.getMap().put("msg25", "<span style=\\'color:#EE0000;\\'>【其他】</span>不能为空！");
				wage.getMap().put("con25", subZeroAndDot(cellText));
			} else {
				if (GenericValidator.isDouble(subZeroAndDot(cellText))) {
					wage.setW_other(new BigDecimal(subZeroAndDot(cellText)));
				} else {
					verifi_flag = false;
					wage.getMap().put("msg25",
							"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(cellText) + "】</span>非法！必须填写数字！");
					wage.getMap().put("con25", subZeroAndDot(cellText));
				}
			}
			break;
		case 26:// 实发工资
			if ("".equals(subZeroAndDot(cellText))) {
				verifi_flag = false;
				wage.getMap().put("msg26", "<span style=\\'color:#EE0000;\\'>【实发工资】</span>不能为空！");
				wage.getMap().put("con26", subZeroAndDot(cellText));
			} else {
				if (GenericValidator.isDouble(subZeroAndDot(cellText))) {
					wage.setW_fact(new BigDecimal(subZeroAndDot(cellText)));
				} else {
					verifi_flag = false;
					wage.getMap().put("msg26",
							"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(cellText) + "】</span>非法！必须填写数字！");
					wage.getMap().put("con26", subZeroAndDot(cellText));
				}
			}
			break;
		default:
			break;
		}
		return verifi_flag;
	}

	private String getCellText(HSSFRow row, int col) {
		if (row.getCell(col) == null) {
			return "";
		}
		DecimalFormat df = new DecimalFormat("#.000000");
		HSSFCell cell = row.getCell(col);
		String data = "";
		if (null != cell) {
			if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				data = df.format(cell.getNumericCellValue());
				// data = String.format("%.6f", cell.getNumericCellValue());
				// data = String.valueOf(cell.getNumericCellValue());
				// data = new BigDecimal(cell.getNumericCellValue()).toString();
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
		if (s.indexOf(".") > 0) {
			s = s.replaceAll("0+?$", "");// 去掉多余的0
			s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
		}
		return s;
	}
}
