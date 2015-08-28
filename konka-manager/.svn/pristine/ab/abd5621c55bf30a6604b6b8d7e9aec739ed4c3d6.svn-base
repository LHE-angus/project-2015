package com.ebiz.mmt.web.struts.customer;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JBaseGoods;
import com.ebiz.mmt.domain.JBaseType;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class ImportCustomerPdDataAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.add(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// DynaBean dynaBean = (DynaBean) form;
		super.setNaviStringToRequestScope(form, request);
		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// DynaBean dynaBean = (DynaBean) form;
		super.setNaviStringToRequestScope(form, request);
		return mapping.findForward("view");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		FileInputStream excelFile = null;
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
		ctxDir = request.getSession().getServletContext().getRealPath("/");
		if (StringUtils.contains(fileSavePath, MmtFilePathConfig.S_OTHERS_PATH)) {
			ctxDir = "";
			fileSavePath = "/Attachment_new/konka-files/" + fileSavePath;
		}
		fileSavePath = ctxDir + fileSavePath;
		excelFile = new FileInputStream(fileSavePath);
		List<JBaseGoods> goodsList = new ArrayList<JBaseGoods>();
		boolean excel_is_ok = true;// 验证通过标识位
		String regEx = "^\\d{0,10}(\\.\\d+)?$";
		Pattern vali_dig = Pattern.compile(regEx);

		int startRow = 3;
		int startCol = 1;
		HSSFWorkbook workbook = new HSSFWorkbook(excelFile);
		HSSFSheet sheet = null;

		String r3_code = getCellText(workbook.getSheetAt(0).getRow(0), 2);
		String ts = getCellText(workbook.getSheetAt(0).getRow(0), 5);
		logger.info("R3_Code : {}, Time : {}", r3_code, ts);

		Long cur_c_id = null;

		if (StringUtils.isBlank(r3_code)) {
			super.renderJavaScript(response, "alert('客户编码为空！');history.back();");
			return null;
		} else {
			KonkaR3Shop s = new KonkaR3Shop();
			s.setR3_code(r3_code);
			s = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(s);
			if (null == s) {
				super.renderJavaScript(response, "alert('系统中不存在客户编码 “" + r3_code + "” ！');history.back();");
				return null;
			}

			HttpSession session = request.getSession();
			PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
			if (!s.getId().equals(user.getCust_id())) {
				super.renderJavaScript(response, "alert('您填写的客户编码 “" + r3_code + "”不正确 ！');history.back();");
				return null;
			}

			cur_c_id = s.getId();
		}

		Date ts_;
		if (StringUtils.isBlank(ts)) {
			super.renderJavaScript(response, "alert('统计时间为空！');history.back();");
			return null;
		} else {
			try {
				ts_ = DateUtils.parseDate(ts, new String[] { "yyyy-MM-dd HH:mm" });
			} catch (Exception e) {
				super.renderJavaScript(response, "alert('统计时间“" + ts
						+ "”格式不正确，请按格式“yyyy-MM-dd HH:mm”，例如：2014-09-09 09:09');history.back();");
				return null;
			}
		}
		// start excel data
		// 只支持单个sheet
		sheet = workbook.getSheetAt(0);
		int rowCounts = sheet.getLastRowNum();// 行数
		for (int j = startRow; j <= rowCounts; j++) {// 获取每行
			HSSFRow row = sheet.getRow(j);
			String c0 = getCellText(row, startCol);
			String c1 = getCellText(row, startCol + 1);
			String c2 = getCellText(row, startCol + 2);
			// String c3 = getCellText(row, startCol + 3);
			// String c4 = getCellText(row, startCol + 4);
			String c5 = getCellText(row, startCol + 3);
			String c6 = getCellText(row, startCol + 4);
			String c7 = getCellText(row, startCol + 5);

			logger.info("c0=" + c0 + "   c1=" + c1 + "   c2=" + c2 + "   c3=" + c5 + "   c4=" + c6 + "    c5=" + c7);

			JBaseGoods goods = new JBaseGoods();
			if (StringUtils.isBlank(c0) || StringUtils.isBlank(c1) || StringUtils.isBlank(c2)
					|| StringUtils.isBlank(c5)) {
				break;
			}

			// 1. *康佳商品标识：是|否
			if ("".equals(c0) || !ArrayUtils.contains("是,否".split(","), c0)) {
				excel_is_ok = false;
				goods.getMap().put("msg0", "【*康佳商品标识】不合法，只能为“是”或“否”！");
			} else {
				goods.setIs_konka("是".equals(c0) ? 1 : 0);
			}
			goods.getMap().put("c0", c0);

			// 2. *电视规格型号
			if ("".equals(c1)) {
				excel_is_ok = false;
				goods.getMap().put("msg1", "【*电视规格型号】不能为空！");
			} else {
				PePdModel pepdmodel=new PePdModel();
				pepdmodel.setMd_name(c1);
				pepdmodel.setIs_del(0);
				pepdmodel=super.getFacade().getPePdModelService().getPePdModel(pepdmodel);
				if (null!=pepdmodel&&null!=pepdmodel.getMd_name()) {
					goods.setGoods_name(StringUtils.trim(c1));
				}else {
					excel_is_ok = false;
					goods.getMap().put("msg1", "【*电视规格型号】不存在或已删除！");
				}
			}
			goods.getMap().put("c1", c1);

			// 3.*商品状态
			if ("".equals(c2) || !ArrayUtils.contains("上架,下架".split(","), c2)) {
				excel_is_ok = false;
				goods.getMap().put("msg2", "【*商品状态】不合法，`能为“上架”或“下架”！");
			} else {
				goods.setGoods_stutes("下架".equals(c2) ? 1 : 0);
			}
			goods.getMap().put("c2", c2);

			// 4. *初始化库存
			// String c3v = StringUtils.replace(StringUtils.replace(c3, ",",
			// ""), "，", "");
			//
			// if (StringUtils.isNotBlank(c3) && StringUtils.isNotBlank(c3v)) {
			// goods.setInit_count(Long.valueOf(subZeroAndDot(c3v)));
			// } else {
			// excel_is_ok = false;
			// goods.getMap().put("msg3", "【*初始化库存】不合法，必须填写数字。");
			// }
			// goods.getMap().put("c3", subZeroAndDot(c3v));

			// 5. *进货单价
			// String c4v = StringUtils.replace(StringUtils.replace(c4, ",",
			// ""), "，", "");
			// if (StringUtils.isNotBlank(c4) && StringUtils.isNotBlank(c4v) &&
			// vali_dig.matcher(c4v).matches()) {
			// goods.setBuy_price(new BigDecimal(subZeroAndDot(c4v)));
			// } else {
			// excel_is_ok = false;
			// goods.getMap().put("msg4", "【*进货单价】不合法，必须填写数字。");
			// }
			// goods.getMap().put("c4", subZeroAndDot(c4v));

			// 6. *销售单价
			String c5v = StringUtils.replace(StringUtils.replace(c5, ",", ""), "，", "");
			if (StringUtils.isNotBlank(c5)) {
				goods.setSell_price(new BigDecimal(subZeroAndDot(c5v)));
			} else {
				excel_is_ok = false;
				goods.getMap().put("msg5", "【*销售单价】不合法，必须填写数字。");
			}
			goods.getMap().put("c5", subZeroAndDot(c5v));

			// 7. 商品条码
			goods.setGoods_sn(c6);
			goods.getMap().put("c6", c6);

			// 8. 商品描述
			goods.setGoods_desc(c7);
			goods.getMap().put("c7", c7);

			goodsList.add(goods);
		}

		// end excel data
		if (!excel_is_ok) {
			request.setAttribute("demoEntityList", goodsList);
			return view(mapping, form, request, response);
		}

		StringBuilder sb = new StringBuilder();

		int exsit_count = 0;
		for (JBaseGoods cur : goodsList) {
			JBaseGoods g = new JBaseGoods();
			g.setC_id(cur_c_id);
			g.setGoods_name(cur.getGoods_name());
			if (0L < super.getFacade().getJBaseGoodsService().getJBaseGoodsCount(g)) {
				sb.append(cur.getGoods_name()).append(" ");
				exsit_count++;
				continue;
			}

			cur.setC_id(cur_c_id);

			// 获取客户商品单位ID
			JBaseType type_10002 = new JBaseType();
			type_10002.setPar_id(10002L);
			type_10002.setType_name("台");
			type_10002.setC_id(cur_c_id);
			type_10002 = super.getFacade().getJBaseTypeService().getJBaseType(type_10002);
			Long tai_id = null;
			if (null == type_10002) {
				// 初始化 商品单位 信息
				JBaseType __type = new JBaseType();
				__type.setPar_id(10002L);
				__type.setType_name("台");
				__type.setType_desc("大件描述单位");
				__type.setC_id(cur_c_id);
				tai_id = super.getFacade().getJBaseTypeService().createJBaseType(__type);
			} else {
				tai_id = type_10002.getType_id();
			}
			cur.setGoods_unit(tai_id);

			// 获取客户商品类型
			if (cur.getIs_konka() == 1) {
				JBaseType type_10001 = new JBaseType();
				type_10001.setPar_id(10001L);
				type_10001.setType_name("康佳电视");
				type_10001.setC_id(cur_c_id);
				type_10001.setIs_del(0);
				type_10001 = super.getFacade().getJBaseTypeService().getJBaseType(type_10001);
				Long konka_tv_id = null;
				if (null == type_10001) {
					// 初始化 商品单位 信息
					JBaseType __type = new JBaseType();
					__type.setPar_id(10001L);
					__type.setType_name("康佳电视");
					__type.setType_desc("系统初始化");
					__type.setC_id(cur_c_id);
					konka_tv_id = super.getFacade().getJBaseTypeService().createJBaseType(__type);
				} else {
					konka_tv_id = type_10001.getType_id();
				}
				cur.setGoods_type(konka_tv_id);
			}

			// // 获取客户总库仓库
			// // 如果该用户没有设置进出货仓库，系统默认创建一个“总库”作为默认进出货仓库
			// JBaseStore store = new JBaseStore();
			// store.setC_id(cur_c_id);
			// store.setIs_del(0);
			// store.setStore_name(super.getMessage(request,
			// "konka.jbill.store.total.name"));
			// store =
			// super.getFacade().getJBaseStoreService().getJBaseStore(store);
			// Long store_id = null;
			// if (null == store) {
			//
			// JBaseStore __store = new JBaseStore();
			// __store.setC_id(cur_c_id);
			// __store.setStore_sn("CK-" + cur_c_id + "-01");
			// __store.setIs_del(0);
			// __store.setIs_dft_buy_store(1);
			// __store.setIs_dft_sell_store(1);
			// __store.setStore_name(super.getMessage(request,
			// "konka.jbill.store.total.name"));
			// store_id =
			// super.getFacade().getJBaseStoreService().createJBaseStore(__store);
			// } else {
			// store_id = store.getStore_id();
			// }
			// cur.getMap().put("store_id", store_id);

			cur.getMap().put("opr_date", DateFormatUtils.format(ts_, "yyyy-MM-dd HH:mm:ss"));

			super.getFacade().getJBaseGoodsService().createJBaseGoods(cur);
		}

		String msg = "数据总计 " + goodsList.size() + " 条，成功导入 " + (goodsList.size() - exsit_count) + " 条，" + exsit_count
				+ " 条已存在( " + sb.toString() + ")。";
		if (0 == (goodsList.size() - exsit_count)) {
			super.saveError(request, "message", msg);
		} else {
			super.saveMessage(request, "message", msg);
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append("/../customer/manager/ImportCustomerPdData.do?method=add");
		pathBuffer.append("&mod_id=").append(mod_id).append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	private String getCellText(HSSFRow row, int col) {
		if (null==row.getCell(col)  ) {
			return "";
		}
		DecimalFormat df = new DecimalFormat("#0.000000");
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
