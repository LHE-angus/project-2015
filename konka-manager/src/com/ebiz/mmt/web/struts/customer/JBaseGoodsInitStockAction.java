package com.ebiz.mmt.web.struts.customer;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.ebiz.mmt.domain.JBaseGoods;
import com.ebiz.mmt.domain.JBaseGoodsInitStock;
import com.ebiz.mmt.domain.JBaseStore;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Xiao,GuoJian
 * @version 2014-06-20
 */
public class JBaseGoodsInitStockAction extends BaseClientJxcAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String store_id = (String) dynaBean.get("store_id"); // 仓库ID
		String init_state = (String) dynaBean.get("init_state"); // 初始化状态
		String init_date_s = (String) dynaBean.get("init_date_s"); // 初始化时间
		String init_date_e = (String) dynaBean.get("init_date_e"); // 初始化时间
		String goods_name_like = (String) dynaBean.get("goods_name_like"); // 商品名称
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}

		JBaseGoodsInitStock entity = new JBaseGoodsInitStock();
		entity.setC_id(user.getCust_id());
		if (GenericValidator.isLong(store_id)) {
			entity.setStore_id(Long.valueOf(store_id));
		}
		if (GenericValidator.isLong(init_state)) {
			entity.setInit_state(Integer.valueOf(init_state));
		}
		if (StringUtils.isNotBlank(init_date_s)) {
			entity.getMap().put("init_date_s", init_date_s + " 00:00:00");
		}
		if (StringUtils.isNotBlank(init_date_e)) {
			entity.getMap().put("init_date_e", init_date_e + " 23:59:59");
		}
		if (StringUtils.isNotBlank(goods_name_like)) {
			entity.getMap().put("goods_name_like", goods_name_like.trim());
		}

		Long recordCount = super.getFacade().getJBaseGoodsInitStockService().getJBaseGoodsInitStockCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<JBaseGoodsInitStock> entityList = super.getFacade().getJBaseGoodsInitStockService()
				.getJBaseGoodsInitStockPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		JBaseStore jBaseStore = new JBaseStore();
		jBaseStore.getMap().put("order_by_is_dft_buy_store_desc", true);
		jBaseStore.setC_id(user.getCust_id());
		jBaseStore.setIs_del(0);
		List<JBaseStore> jBaseStoreList = super.getFacade().getJBaseStoreService().getJBaseStoreList(jBaseStore);
		request.setAttribute("jBaseStoreList", jBaseStoreList);

		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		request.setAttribute("nowdate", format.format(date));// 当前时间
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		cale.add(Calendar.DATE, -30);
		request.setAttribute("beforedate", format.format(cale.getTime()));

		return mapping.findForward("list");
	}

	/**
	 * Xiao,GuoJian 保存仓库和型号到绑定
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.saveToken(request);
		setNaviStringToRequestScope(form, request);
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		request.setAttribute("today", df.format(today));
		request.setAttribute("jBaseGoodsList", super.getJBaseGoodsList(user.getCust_id(), 0));
		request.setAttribute("jBaseStoreList", super.getJBaseStores(request, 0));
		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		JBaseGoodsInitStock entity = new JBaseGoodsInitStock();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getJBaseGoodsInitStockService().getJBaseGoodsInitStock(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		request.setAttribute("jBaseGoodsList", super.getJBaseGoodsList(user.getCust_id(), 0));
		request.setAttribute("jBaseStoreList", super.getJBaseStores(request, 0));
		return new ActionForward("/../customer/JBaseGoodsInitStock/edit.jsp");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		JBaseGoodsInitStock entity = new JBaseGoodsInitStock();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getJBaseGoodsInitStockService().getJBaseGoodsInitStock(entity);
		super.copyProperties(form, entity);
		return mapping.findForward("view");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		resetToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String returnUrl = (String) dynaBean.get("returnUrl");

		String[] store_ids = request.getParameterValues("store_id");
		String[] goods_ids = request.getParameterValues("goods_id");
		String[] init_counts = request.getParameterValues("init_count");
		String[] buy_prices = request.getParameterValues("buy_price");
		String[] init_descs = request.getParameterValues("init_desc");
		String[] init_money = request.getParameterValues("init_money");

		String opr_date = (String) dynaBean.get("opr_date");

		String HH = (String) dynaBean.get("HH");
		String mm = (String) dynaBean.get("mm");
		HH = StringUtils.isBlank(HH) ? "00" : HH;
		mm = StringUtils.isBlank(mm) ? "00" : mm;
		opr_date = opr_date + " " + HH + ":" + mm;
		Date _date = DateUtils.parseDate(opr_date, new String[] { "yyyy-MM-dd HH:mm" });

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		if (GenericValidator.isLong(id)) {// 修改
			JBaseGoodsInitStock entity = new JBaseGoodsInitStock();
			super.copyProperties(entity, form);

			JBaseGoodsInitStock initStock = new JBaseGoodsInitStock();
			initStock.setGoods_id(entity.getGoods_id());
			initStock.setStore_id(entity.getStore_id());
			initStock.setC_id(user.getCust_id());
			initStock.getMap().put("goods_id_is_not_eq", entity.getGoods_id());
			Long count = super.getFacade().getJBaseGoodsInitStockService().getJBaseGoodsInitStockCount(initStock);
			if (count > 0) {
				super.saveError(request, "konka.jgoods.error");
			} else {
				super.getFacade().getJBaseGoodsInitStockService().modifyJBaseGoodsInitStock(entity);
				saveMessage(request, "entity.updated");
			}
		} else {// 添加
			if (null != store_ids && null != goods_ids && store_ids.length > 0) {
				boolean flag = true;
				for (int i = 0; i < store_ids.length; i++) {
					if (StringUtils.isNotBlank(store_ids[i]) && StringUtils.isNotBlank(goods_ids[i])) {
						JBaseGoodsInitStock entity = new JBaseGoodsInitStock();
						entity.setStore_id(Long.parseLong(store_ids[i]));
						entity.setGoods_id(Long.parseLong(goods_ids[i]));
						entity.setC_id(user.getCust_id());
						entity.getMap().put("goods_id_is_not_eq", entity.getGoods_id());
						Long count = super.getFacade().getJBaseGoodsInitStockService()
								.getJBaseGoodsInitStockCount(entity);
						if (count > 0) {
							flag = false;
							break;
						} else {
							entity.setBuy_price(new BigDecimal(buy_prices[i]));
							entity.setInit_money(new BigDecimal(init_money[i]));
							entity.setInit_count(Long.parseLong(init_counts[i]));
							entity.setInit_date(_date);
							entity.setInit_user(user.getId());
							entity.setInit_desc(init_descs[i]);
							entity.setInit_state(0);
							entity.getMap().put("is_first_init", true);
							super.getFacade().getJBaseGoodsInitStockService().createJBaseGoodsInitStockAndStock(entity);
							
							
							
							//将初始化的库存数据入栈
							super.getFacade()
									.getJxcFifoStocksService()
									.stock_in(Long.parseLong(store_ids[i]),  Long.parseLong(goods_ids[i]), new BigDecimal(buy_prices[i]),Integer.parseInt(init_counts[i]), _date,30);
							
							
							
							
							
							
							flag = true;
						}
					}
				}
				if (flag) {
					super.saveMessage(request, "entity.inserted");
				} else {
					super.saveError(request, "konka.jgoods.error");
				}
			}
		}

		// 画面迁移从哪里来哪里去
		if (StringUtils.isNotBlank(returnUrl)) {
			super.toReturnUrl(returnUrl + "&mod_id=" + mod_id, response);
			return null;
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward oneKeyInitStock(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		resetToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String store_id = (String) dynaBean.get("init_store_id");
		String one_key_init_date = (String) dynaBean.get("one_key_init_date");

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			super.renderJavaScript(response, "alert('出现未知错误，请联系管理员！');history.back();");
			return null;
		}
		if (StringUtils.isBlank(store_id)) {
			super.renderJavaScript(response, "alert('必须选择仓库！');history.back();");
			return null;
		}
		if (StringUtils.isBlank(one_key_init_date)) {
			super.renderJavaScript(response, "alert('必须选择初始化时间！');history.back();");
			return null;
		}

		String HH = (String) dynaBean.get("HH");
		String mm = (String) dynaBean.get("mm");
		HH = StringUtils.isBlank(HH) ? "00" : HH;
		mm = StringUtils.isBlank(mm) ? "00" : mm;
		one_key_init_date = one_key_init_date + " " + HH + ":" + mm;
		Date _date = DateUtils.parseDate(one_key_init_date, new String[] { "yyyy-MM-dd HH:mm" });

		SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date current = new Date();
		Date d_date = DateUtils.parseDate(dformat.format(current), new String[] { "yyyy-MM-dd HH:mm" });
		if (_date.compareTo(d_date) > 0) {
			super.renderJavaScript(response, "alert('初始化时间超出当前时间,请重新选择！');history.back();");
			return null;
		}

		// 取30天之前的日期
		// Calendar theCa = Calendar.getInstance();
		// theCa.setTime(new Date());
		// theCa.add(theCa.DATE, -30);
		// Date date = theCa.getTime();

		JBaseGoods jBaseGoods = new JBaseGoods();
		jBaseGoods.setC_id(user.getCust_id());
		jBaseGoods.getMap().put("store_id", store_id);
		List<JBaseGoods> goodsList = super.getFacade().getJBaseGoodsService().getJBaseGoodsWithOutInitList(jBaseGoods);
		if (goodsList != null && goodsList.size() > 0) {
			for (JBaseGoods goods : goodsList) {
				JBaseGoodsInitStock entity = new JBaseGoodsInitStock();
				entity.setC_id(user.getCust_id());
				entity.setBuy_price(goods.getBuy_price());
				entity.setGoods_id(goods.getGoods_id());
				entity.setInit_count(0l);
				entity.setInit_money(new BigDecimal("0"));
				entity.setInit_state(0);
				entity.setInit_user(user.getId());
				entity.setSell_price(goods.getSell_price());
				entity.setStore_id(Long.parseLong(store_id));
				entity.setInit_date(_date);
				entity.getMap().put("is_first_init", true);
				super.getFacade().getJBaseGoodsInitStockService().createJBaseGoodsInitStockAndStock(entity);
			}
			super.saveMessage(request, "konka.jbasegoodsinitstock.onkeyinit.success");
		} else {
			super.saveMessage(request, "konka.jbasegoodsinitstock.onkeyinit.alert");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
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

		JBaseGoodsInitStock entity = new JBaseGoodsInitStock();
		entity.setId(Long.valueOf(id));
		entity.setInit_state(1);
		super.getFacade().getJBaseGoodsInitStockService().modifyJBaseGoodsInitStock(entity);

		saveMessage(request, "konka.close.success");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward reStart(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		JBaseGoodsInitStock entity = new JBaseGoodsInitStock();
		entity.setId(Long.valueOf(id));
		entity.setInit_state(0);
		super.getFacade().getJBaseGoodsInitStockService().modifyJBaseGoodsInitStock(entity);

		saveMessage(request, "konka.restart.success");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward excel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		request.setAttribute("mod_id", mod_id);
		return new ActionForward("/../customer/JBaseGoodsInitStock/excel.jsp");
	}

	public ActionForward saveExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

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

		// 附件保存路径更改遗留问题
		if (StringUtils.contains(fileSavePath, MmtFilePathConfig.S_OTHERS_PATH)) {
			ctxDir = "";
			fileSavePath = "/Attachment_new/konka-files/" + fileSavePath;
		}
		fileSavePath = ctxDir + fileSavePath;
		excelFile = new FileInputStream(fileSavePath);
		String regEx = "^\\d{0,10}(\\.\\d+)?$";
		Pattern vali_dig = Pattern.compile(regEx);

		int startRow = 3;
		int startCol = 1;
		HSSFWorkbook workbook = new HSSFWorkbook(excelFile);
		HSSFSheet sheet = null;

		String r3_code = getCellText(workbook.getSheetAt(0).getRow(0), 2);
		String ts = getCellText(workbook.getSheetAt(0).getRow(0), 4);
		logger.info("R3_Code : {}, Time : {}", r3_code, ts);

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

			if (!s.getId().equals(user.getCust_id())) {
				super.renderJavaScript(response, "alert('您填写的客户编码 “" + r3_code + "”不正确 ！');history.back();");
				return null;
			}
		}
		Date ts_;
		if (StringUtils.isBlank(ts)) {
			super.renderJavaScript(response, "alert('导入时间为空！');history.back();");
			return null;
		} else {
			 DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		     Date dt = df.parse(ts);
		     Date curdate=new Date();
		     if (dt.getTime()>curdate.getTime()) {
		    	 super.renderJavaScript(response, "alert('导入时间不能大于当前时间！');history.back();");
			 }
			try {
				ts_ = DateUtils.parseDate(ts, new String[] { "yyyy-MM-dd HH:mm" });
			} catch (Exception e) {
				super.renderJavaScript(response, "alert('导入时间“" + ts
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
			String c3 = getCellText(row, startCol + 3);
			String c4 = getCellText(row, startCol + 4);

			logger.info("c0=" + c0 + "   c1=" + c1 + "   c2=" + c2 + "   c3=" + c3 + "   c4=" + c4);

			JBaseGoodsInitStock goodsInitStock = new JBaseGoodsInitStock();
			JBaseGoodsInitStock goodsInitStock2 = new JBaseGoodsInitStock();
			if (StringUtils.isBlank(c0) || StringUtils.isBlank(c1) || StringUtils.isBlank(c2)
					|| StringUtils.isBlank(c3)) {
				break;
			}

			// 1. *仓库名称
			if (null != c0 && !"".equals(c0)) {
				JBaseStore store = new JBaseStore();
				store.setC_id(user.getCust_id());
				store.setIs_del(0);
				store.setStore_name(c0);
				List<JBaseStore> list = super.getFacade().getJBaseStoreService().getJBaseStoreList(store);
				if (null != list && list.size() > 0) {
					goodsInitStock.setStore_id(list.get(0).getStore_id());
					goodsInitStock2.setStore_id(list.get(0).getStore_id());
				} else {
					super.renderJavaScript(response, "alert('EXCEL第" + (j + 1) + "行，仓库名称“" + c0
							+ "”不存在！');history.back();");
					return null;
				}
			} else {
				super.renderJavaScript(response, "alert('EXCEL第" + (j + 1) + "行，仓库名称“" + c0
						+ "”格式不正确，不能为空！');history.back();");
				return null;
			}

			// 2. *电视规格型号
			if (null != c1 && !"".equals(c1)) {
				JBaseGoods jBaseGoods = new JBaseGoods();
				jBaseGoods.setGoods_stutes(0);
				jBaseGoods.setC_id(user.getCust_id());
				jBaseGoods.setGoods_name(c1);
				List<JBaseGoods> list = super.getFacade().getJBaseGoodsService().getJBaseGoodsList(jBaseGoods);
				if (null != list && list.size() > 0) {
					goodsInitStock.setGoods_id(list.get(0).getGoods_id());
					goodsInitStock2.setGoods_id(list.get(0).getGoods_id());
				} else {
					super.renderJavaScript(response, "alert('EXCEL第" + (j + 1) + "行，电视规格型号“" + c1
							+ "”不存在！请到“账户设置－基础数据－商品数据”中维护！');history.back();");
					return null;
				}
			} else {
				super.renderJavaScript(response, "alert('EXCEL第" + (j + 1) + "行，电视规格型号“" + c1
						+ "”格式不正确，不能为空！');history.back();");
				return null;
			}

			// 3. *初始化库存
			String c2v = StringUtils.replace(StringUtils.replace(c2, ",", ""), "，", "");

			if (StringUtils.isNotBlank(c2) && StringUtils.isNotBlank(c2v)) {
				goodsInitStock.setInit_count(Long.valueOf(subZeroAndDot(c2v)));
			} else {
				super.renderJavaScript(response, "alert('EXCEL第" + (j + 1) + "行，初始化库存不合法，必须填写数字！');history.back();");
				return null;
			}

			// 4. *进货单价
			String c3v = StringUtils.replace(StringUtils.replace(c3, ",", ""), "，", "");
			if (StringUtils.isNotBlank(c3) && StringUtils.isNotBlank(c3v) && vali_dig.matcher(c3v).matches()) {
				goodsInitStock.setBuy_price(new BigDecimal(subZeroAndDot(c3v)));
			} else {
				super.renderJavaScript(response, "alert('EXCEL第" + (j + 1) + "行，进货单价不合法，必须填写数字！');history.back();");
				return null;
			}

			// 5. *备注说明
			if (StringUtils.isNotBlank(c4)) {
				if (c4.length() > 200) {
					super.renderJavaScript(response, "alert('EXCEL第" + (j + 1)
							+ "行，备注说明太长，最多不能超过200字！');history.back();");
					return null;
				} else {
					goodsInitStock.setInit_desc(c4);
				}
			}

			goodsInitStock2.setC_id(user.getCust_id());
			goodsInitStock2.getMap().put("goods_id_is_not_eq", goodsInitStock.getGoods_id());
			Long count = super.getFacade().getJBaseGoodsInitStockService().getJBaseGoodsInitStockCount(goodsInitStock2);
			if (count > 0) {
				super.renderJavaScript(response, "alert('EXCEL第" + (j + 1) + "行，商品在仓库中已经存在，不能重复添加！');history.back();");
				return null;
			}
			if (ts_ != null) {
				goodsInitStock.setInit_date(ts_);
			} else {
				goodsInitStock.setInit_date(new Date());
			}
			goodsInitStock.setInit_state(0);
			goodsInitStock.setInit_user(user.getId());
			goodsInitStock.setC_id(user.getCust_id());
			goodsInitStock.getMap().put("is_first_init", true);
		    goodsInitStock.setInit_money(goodsInitStock.getBuy_price().multiply(
				new BigDecimal(goodsInitStock.getInit_count().toString())));

			super.getFacade().getJBaseGoodsInitStockService().createJBaseGoodsInitStockAndStock(goodsInitStock);
			//将初始化的库存数据入栈
			
				super.getFacade()
				.getJxcFifoStocksService()
				.stock_in(goodsInitStock.getStore_id(),c1, new BigDecimal(subZeroAndDot(c3v)),Integer.parseInt(subZeroAndDot(c2v)), new Date(),30);
		
			
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	private String getCellText(HSSFRow row, int col) {
		if (row.getCell(col) == null) {
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
