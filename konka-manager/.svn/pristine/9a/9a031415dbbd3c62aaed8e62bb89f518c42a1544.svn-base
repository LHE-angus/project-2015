package com.ebiz.mmt.web.struts.customer;

import com.ebiz.mmt.domain.*;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 月底全部盘点和月初人工结转
 * 
 * @author ZHOU
 * 
 */
public class JStockInventoryAction extends BaseClientJxcAction {

	public static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	public static DateFormat dfm = new SimpleDateFormat("yyyyMMdd");

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
		// 单据编号
		String bill_sn_like = (String) dynaBean.get("bill_sn_like");
		String trade_type = (String) dynaBean.get("trade_type");
		String store_id = (String) dynaBean.get("store_id");
		String goods_id = (String) dynaBean.get("goods_id");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String type = (String) dynaBean.get("type");// 4:月末全部盘点 2：月初结转

		HttpSession session = request.getSession();
		// 取人员信息
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		} else if (null == user.getCust_id()) {
			return null;
		}

		// 库存盘点实体
		JStocksVerify entity = new JStocksVerify();
		entity.setC_id(user.getCust_id());

		if (GenericValidator.isInt(year) && GenericValidator.isInt(month)) {
			String qdate = year + "-" + month;
			entity.getMap().put("qdate", qdate);
		} else {
			Calendar calendar = Calendar.getInstance();
			int c_year = calendar.get(Calendar.YEAR);
			int c_month = calendar.get(Calendar.MONTH) + 1;
			String qdate = c_year + "-" + c_month;
			entity.getMap().put("qdate", qdate);
			dynaBean.set("year", c_year);
			dynaBean.set("month", c_month);
		}

		if (StringUtils.isNotBlank(bill_sn_like)) {
			entity.getMap().put("bill_sn_like", bill_sn_like);
		}
		if (GenericValidator.isLong(store_id)) {
			entity.setStore_id(Long.parseLong(store_id));
		}
		if (GenericValidator.isLong(goods_id)) {
			entity.setGoods_id(Long.parseLong(goods_id));
		}
		if (GenericValidator.isInt(trade_type)) {
			entity.setTrade_type(Integer.parseInt(trade_type));
		}
		if (GenericValidator.isInt(type)) {
			entity.setType(Integer.parseInt(type));
		} else {
			entity.setType(4);
			dynaBean.set("type", 4);
		}
		// 查条数
		Long recordCount = super.getFacade().getJStocksVerifyService().getJStocksVerifyForInventoryCount(entity);
		pager.init(recordCount, 50, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		// 分页查
		List<JStocksVerify> entityList = getFacade().getJStocksVerifyService()
				.getJStocksVerifyForInventoryPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		// 取仓库
		JBaseStore jBaseStore = new JBaseStore();
		jBaseStore.setC_id(user.getCust_id());
		jBaseStore.setIs_del(0);
		List<JBaseStore> jBaseStoreList = getFacade().getJBaseStoreService().getJBaseStoreList(jBaseStore);
		request.setAttribute("jBaseStoreList", jBaseStoreList);

		// 取客户商品库产品
		JBaseGoods jBaseGoods = new JBaseGoods();
		jBaseGoods.setC_id(user.getCust_id());
		List<JBaseGoods> jBaseGoodsList = getFacade().getJBaseGoodsService().getJBaseGoodsList(jBaseGoods);
		request.setAttribute("jBaseGoodsList", jBaseGoodsList);
		
		// 取实时库存 ldy
		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(user.getCust_id());
		konkaR3Shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);

		if (null != konkaR3Shop) {
			if (konkaR3Shop.getIs_inventory() == 0) {
				request.setAttribute("is_pd", 0);
			} else if (konkaR3Shop.getIs_inventory() == 1) {
				request.setAttribute("is_pd", 1);
			}
		}

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		super.saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);

		if (null == user) {
			return null;
		} else if (user.getCust_id() == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		int c_day = calendar.get(Calendar.DAY_OF_MONTH);
		int c_month = calendar.get(Calendar.MONTH);
//		暂时先注释掉
//		if (c_day < 25) {// 如果日小于25日，则给出提示
//			super.renderJavaScript(response, "alert('全部盘点日期应该在每月25号-31号之间！！');history.back();");
//			return null;
//		}

		// 如果25号到目前为止有盘点过，则提示无需再盘点
		JStocksVerify jsv = new JStocksVerify();
		jsv.setC_id(user.getCust_id());
		jsv.setType(4);
		jsv.getRow().setCount(1);
		jsv = getFacade().getJStocksVerifyService().getJStocksVerifyForLastedVerify(jsv);
		if (null != jsv) {
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(jsv.getOpr_date());
			int o_day = calendar1.get(Calendar.DAY_OF_MONTH);
			int o_month = calendar1.get(Calendar.MONTH);
			if (c_month == o_month) {// 上次全部盘点的月份和此次相同，则判断此次盘点之前是否已经盘点过了
				if (o_day <= c_day && o_day >= 25) {
					super.renderJavaScript(response, "alert('本月" + o_day + "号已经全部盘点过，无需再盘点！');history.back();");
					return null;
				}
			}
		}

		JStocksVerify entity = new JStocksVerify();
		entity.getMap().put("c_id", user.getCust_id());

		// 取客户商品库产品
		List<JStocksVerify> entityList = getFacade().getJStocksVerifyService().getJStocksVerifyForInventoryFormList(
				entity);
		// 计算单价
		for(JStocksVerify tempStock: entityList){
			BigDecimal money = (BigDecimal)tempStock.getMap().get("money");
			BigDecimal stocks = (BigDecimal)tempStock.getMap().get("stocks");
			if(stocks.compareTo(new BigDecimal(0))>0){
				tempStock.getMap().put("price", (money).divide(stocks, 2));
			}else{
				tempStock.getMap().put("price", 0);
			}
		}
		request.setAttribute("entityList", entityList);
		
		// 导出Excel
		String export = (String)dynaBean.get("export");
		if (StringUtils.isNotBlank(export)) {
			String title = "全部盘点待修改导入数据(不会直接提交，会先回显到页面)";
			String[] headers = {"序号","仓库","商品","盘点前数量","盘点前金额","盘点后数量","盘点单价","盘点后金额","差异原因"};
			List<Object[]> dataList = new ArrayList<Object[]>();
			Object[] objArr = null;
			for(int i=0; i<entityList.size(); i++){
				objArr = new Object[9];
				objArr[0] = i+1;
				objArr[1] = entityList.get(i).getMap().get("store_name");
				objArr[2] = entityList.get(i).getMap().get("goods_name");
				objArr[3] = entityList.get(i).getMap().get("stocks");
				objArr[4] = entityList.get(i).getMap().get("money");
				objArr[5] = entityList.get(i).getMap().get("stocks");
				objArr[6] = entityList.get(i).getMap().get("price");
				objArr[7] = entityList.get(i).getMap().get("money");
				objArr[8] = entityList.get(i).getMap().get("memo");
				dataList.add(objArr);
			}
			ServletOutputStream out = null;
			try {
				response.setContentType("octets/stream");
				response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("全部盘点待修改导入列表")
			        + ".xls");
				out = response.getOutputStream();
				this.exportExcel(title, headers, dataList, out, null);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				if(out != null){
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		resetToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String[] store_ids = request.getParameterValues("store_id");
		String[] goods_ids = request.getParameterValues("goods_id");
		String[] stockss = request.getParameterValues("stocks");
		String[] moneys = request.getParameterValues("money");
		String[] ver_stockss = request.getParameterValues("ver_stocks");
		String[] ver_moneys = request.getParameterValues("ver_money");
		String[] memos = request.getParameterValues("memo");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);

		if (store_ids == null || goods_ids == null || stockss == null || ver_stockss == null) {
			super.renderJavaScript(response, "alert('出现未知错误，请联系管理员！');history.back();");
			return null;
		}

		Date date = new Date();
		Long result = 0l;

		for (int i = 0; i < store_ids.length; i++) {
			String store_id = store_ids[i];
			String goods_id = goods_ids[i];
			String stocks = stockss[i];// 当前库存
			String money = moneys[i];// 期初金额
			String ver_stocks = ver_stockss[i];// 盘点数
			String ver_money = ver_moneys[i];// 盘点金额

			String memo = null;
			if (memos != null) {
				memo = memos[i];
			}
			String price = null;
			logger.info("store_id=" + store_id + "   goods_id=" + goods_id + "    stocks=" + stocks + "   money="
					+ money + "  verstocks=" + ver_stocks + "   ver_money=" + ver_money + "   memo=" + memo);
			if (StringUtils.isNotBlank(store_id) && StringUtils.isNotBlank(goods_id) && StringUtils.isNotBlank(stocks)
					&& StringUtils.isNotBlank(ver_stocks)) {

				String checkResult = super.checkKhContainsMd(user.getCust_id(), Long.valueOf(goods_id),
						Long.valueOf(store_id));// 检查客户/网点中是否已经初始化商品数据或者初始化库存
				if (StringUtils.isNotBlank(checkResult)) {
					super.renderJavaScript(response, "alert('" + checkResult + "');history.back();");
					return null;
				}

				Long re_value = Long.parseLong(ver_stocks) - Long.parseLong(stocks);
				if (0 != re_value) {// 计算价格
					// Long lprice = (Long.parseLong(ver_money) -
					// Long.parseLong(money)) / re_value;
					Float lprice = (Float.parseFloat(ver_money) - Float.parseFloat(money)) / re_value;
					price = lprice.toString();

				}

				//月末的盘点也是盘盈亏，类型也改成1
				if (re_value > 0) {// 盘盈,遍历价格
					if (StringUtils.isNotBlank(price)) {
						String[] price_array = new String[re_value.intValue()];
						for (int va = 0; va < re_value.intValue(); va++) {
							price_array[va] = price;
						}
						result = result
								+ super.getFacade()
										.getKonkaCustomerPublicService()
										.createStockVerify(date, store_id, goods_id, stocks, money, ver_stocks,
												ver_money, user.getCust_id(), memo, 1, price_array, new String[] {});



						super.getFacade()
								.getJxcFifoStocksService()
								.stock_in(Long.valueOf(store_id), Long.valueOf(goods_id),
										new BigDecimal(price), re_value.intValue(), new Date(), 50);
					} else {
						super.renderJavaScript(response, "alert('抱歉，盘盈的商品必须填写价格！');history.back();");
						return null;
					}
				} else {
					result = result
							+ super.getFacade()
									.getKonkaCustomerPublicService()
									.createStockVerify(date, store_id, goods_id, stocks, money, ver_stocks, ver_money,
											user.getCust_id(), memo, 1, new String[] {},
											new String[-re_value.intValue()]);
					//  盘亏相当于是销货了,相当于销售
					if (re_value < 0) {
						super.getFacade()
								.getJxcFifoStocksService()
								.stock_out(Long.valueOf(store_id), Long.valueOf(goods_id), new BigDecimal(price),
										re_value.intValue() * -1, new Date(), 560);
					}
				}
			}
		}
		if (0 == result.intValue()) { // 成功插入
			saveMessage(request, "konka.jstockverify.inserted.success");
		} else if (0 != result.intValue()) { // 盘点日期选择非法
			saveError(request, "konka.jstockverify.oprdate.error");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	// 就是将上个月月末的库存作为这个月月初的库存，自动重新盘点
	public ActionForward jiezhuan(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		super.saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);

		if (null == user) {
			return null;
		} else if (user.getCust_id() == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		int c_day = calendar.get(Calendar.DAY_OF_MONTH);
		int c_month = calendar.get(Calendar.MONTH);
		if (c_day > 5) {
			super.renderJavaScript(response, "alert('结转日期应该在每月1号-5号之间！！');history.back();");
			return null;
		}

		// 如果上月月末有盘点过，则提示无需再结转
		JStocksVerify jsv1 = new JStocksVerify();
		jsv1.setC_id(user.getCust_id());
		jsv1.setType(4);
		jsv1.getRow().setCount(1);
		jsv1 = getFacade().getJStocksVerifyService().getJStocksVerifyForLastedVerify(jsv1);
		if (null != jsv1) {
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(jsv1.getOpr_date());
			int o_day = calendar1.get(Calendar.DAY_OF_MONTH);
			int o_month = calendar1.get(Calendar.MONTH);

			if (c_month == 1) {// 如果上月月末已经全部盘点过了，则不需要结转了
				if (o_month == 12) {
					if (o_day <= 31 && o_day >= 25) {
						super.renderJavaScript(response, "alert('上月" + o_day + "号已经全部盘点过，本月无需结转！');history.back();");
						return null;
					}
				}
			} else {
				if (c_month == (o_month + 1)) {// 如果上月月末已经全部盘点过了，则不需要结转了
					if (o_day <= 31 && o_day >= 25) {
						super.renderJavaScript(response, "alert('上月" + o_day + "号已经全部盘点过，本月无需结转！');history.back();");
						return null;
					}
				}
			}
		}

		// 如果1号到目前为止有盘点过，则提示无需再结转
		JStocksVerify jsv = new JStocksVerify();
		jsv.setC_id(user.getCust_id());
		jsv.setType(2);
		jsv.getRow().setCount(1);
		jsv = getFacade().getJStocksVerifyService().getJStocksVerifyForLastedVerify(jsv);
		if (null != jsv) {
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(jsv.getOpr_date());
			int o_day = calendar1.get(Calendar.DAY_OF_MONTH);
			int o_month = calendar1.get(Calendar.MONTH);
			if (c_month == o_month) {// 上次全部盘点的月份和此次相同，则判断此次盘点之前是否已经盘点过了
				if (o_day <= c_day && o_day >= 1) {
					super.renderJavaScript(response, "alert('本月" + o_day + "号已经结转过，无需再结转！');history.back();");
					return null;
				}
			}
		}

		JStocksVerify entity = new JStocksVerify();
		entity.getMap().put("c_id", user.getCust_id());

		// 取客户商品库产品
		List<JStocksVerify> entityList = getFacade().getJStocksVerifyService().getJStocksVerifyForInventoryFormList(
				entity);
		Long result = 0l;
		Date date = new Date();
		if (null != entityList && entityList.size() > 0) {
			for (JStocksVerify verify : entityList) {
				String store_id = verify.getMap().get("store_id").toString();
				String goods_id = verify.getMap().get("goods_id").toString();
				String stocks = verify.getMap().get("stocks").toString();// 当前库存
				String money = verify.getMap().get("money").toString();// 期初金额
				String ver_stocks = verify.getMap().get("stocks").toString();// 盘点数
				String ver_money = verify.getMap().get("money").toString();// 盘点金额

				logger.info("store_id=" + store_id + "   goods_id=" + goods_id + "    stocks=" + stocks + "   money="
						+ money + "  verstocks=" + ver_stocks + "   ver_money=" + ver_money);

				Long re_value = Long.parseLong(ver_stocks) - Long.parseLong(stocks);
				if (re_value == 0) {
					result = result
							+ super.getFacade()
									.getKonkaCustomerPublicService()
									.createStockVerify(date, store_id, goods_id, stocks, money, ver_stocks, ver_money,
											user.getCust_id(), null, 2, new String[] {},
											new String[-re_value.intValue()]);
				}
			}
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
	
	public ActionForward empty(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}
	
	// 到导入页面
	public ActionForward excel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		request.setAttribute("mod_id", mod_id);
		return new ActionForward("/../customer/JStockInventory/excel.jsp");
	}
	
	public ActionForward showExcelData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);

		if (null == user) {
			return null;
		} else if (user.getCust_id() == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		int c_day = calendar.get(Calendar.DAY_OF_MONTH);
		int c_month = calendar.get(Calendar.MONTH);
//		if (c_day < 25) {// 如果日小于25日，则给出提示
//			super.renderJavaScript(response, "alert('全部盘点日期应该在每月25号-31号之间！！');history.back();");
//			return null;
//		}

		// 如果25号到目前为止有盘点过，则提示无需再盘点
		JStocksVerify jsv = new JStocksVerify();
		jsv.setC_id(user.getCust_id());
		jsv.setType(4);
		jsv.getRow().setCount(1);
		jsv = getFacade().getJStocksVerifyService().getJStocksVerifyForLastedVerify(jsv);
		if (null != jsv) {
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(jsv.getOpr_date());
			int o_day = calendar1.get(Calendar.DAY_OF_MONTH);
			int o_month = calendar1.get(Calendar.MONTH);
			if (c_month == o_month) {// 上次全部盘点的月份和此次相同，则判断此次盘点之前是否已经盘点过了
				if (o_day <= c_day && o_day >= 25) {
					super.renderJavaScript(response, "alert('本月" + o_day + "号已经全部盘点过，无需再盘点！');history.back();");
					return null;
				}
			}
		}

		JStocksVerify entity = new JStocksVerify();
		entity.getMap().put("c_id", user.getCust_id());

		// 取客户商品库产品
		List<JStocksVerify> entityList = getFacade().getJStocksVerifyService().getJStocksVerifyForInventoryFormList(
				entity);
		//request.setAttribute("entityList", entityList);

		// 导入回显到页面
		
		FormFile formFile = (FormFile)dynaBean.get("up_load_file");

		String regEx = "^\\d{0,10}(\\.\\d+)?$";
		Pattern vali_dig = Pattern.compile(regEx);

		int startRow = 2;
		int startCol = 1;
		HSSFWorkbook workbook = new HSSFWorkbook(formFile.getInputStream());
		HSSFSheet sheet = null;
		
		// 先查询用户的全部仓库
		JBaseStore store = new JBaseStore();
		store.setC_id(user.getCust_id());
		store.setIs_del(0);
		List<JBaseStore> storeList = super.getFacade().getJBaseStoreService().getJBaseStoreList(store);
		
		// 仓库对应的商品 通过 store_id,商品List 该map用户缓存已经查询过的仓库对应的商品，避免重复查询
		Map<Long, List<JBaseGoodsInitStock>>  goodsListMap = new HashMap<Long, List<JBaseGoodsInitStock>>();

		// 导入的数据
		List<JStocksVerify> tempList = new ArrayList<JStocksVerify>();
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
			String c5 = getCellText(row, startCol + 5);
			String c6 = getCellText(row, startCol + 6);
			String c7 = getCellText(row, startCol + 7);

			logger.info("c0=" + c0 + "   c1=" + c1 + "   c2=" + c2 + "   c3=" + c3 + "   c4=" + c4 + "   c5=" + c5 + "   c6=" + c6);

			if (StringUtils.isBlank(c0) || StringUtils.isBlank(c1) || StringUtils.isBlank(c2)
					|| StringUtils.isBlank(c3)) {
				break;
			}
			JStocksVerify tempEntity = new JStocksVerify();
			// 1. *仓库名称
			if (null != c0 && !"".equals(c0)) {
				// 仓库名是否存在
				boolean storeExistFlag = false;
				if (null != storeList && storeList.size() > 0) {
					for(JBaseStore tempStore: storeList){
						if(c0.equals(tempStore.getStore_name())){
							tempEntity.getMap().put("store_id", tempStore.getStore_id());
							storeExistFlag = true;
							break;
						}
					}
				}
				if(!storeExistFlag){
					super.renderJavaScript(response, "alert('EXCEL第" + (j + 1) + "行，仓库名称“" + c0
							+ "”不存在！');history.back();");
					return null;
				}
			} else {
				super.renderJavaScript(response, "alert('EXCEL第" + (j + 1) + "行，仓库名称“" + c0
						+ "”格式不正确，不能为空！');history.back();");
				return null;
			}

			// 2. *商品名称
			if (null != c1 && !"".equals(c1)) {
				// 获取商品名称
				List<JBaseGoodsInitStock> goodsList = goodsListMap.get((Long)tempEntity.getMap().get("store_id"));
				if(goodsList==null){
					JBaseGoodsInitStock initStock = new JBaseGoodsInitStock();
					initStock.setStore_id((Long)tempEntity.getMap().get("store_id"));
					initStock.setC_id(user.getCust_id());
					goodsList = super.getFacade().getJBaseGoodsInitStockService()
							.getJBaseGoodsInitStockList(initStock);
					goodsListMap.put((Long)tempEntity.getMap().get("store_id"), goodsList);
				}
				// 商品是否存在
				boolean goodsExistFlag = false;
				if (null != goodsList && goodsList.size() > 0) {
					for (JBaseGoodsInitStock temp : goodsList) {
						if(c1.equals((String)temp.getMap().get("goods_name"))){
							goodsExistFlag = true;
							tempEntity.getMap().put("goods_id", temp.getGoods_id());
							break;
						}
					}
				}
				if (!goodsExistFlag) {
					super.renderJavaScript(response, "alert('EXCEL第" + (j + 1) + "行，商品名称“" + c1
							+ "”不存在！');history.back();");
					return null;
				}
			} else {
				super.renderJavaScript(response, "alert('EXCEL第" + (j + 1) + "行，商品名称“" + c1
						+ "”格式不正确，不能为空！');history.back();");
				return null;
			}

            // 3. *盘点数量
			String c4v = StringUtils.replace(StringUtils.replace(c4, ",", ""), "，", "");

			if (StringUtils.isNotBlank(c4) && StringUtils.isNotBlank(c4v)) {
				tempEntity.getMap().put("ver_stocks", Long.valueOf(subZeroAndDot(c4v)));
			} else {
				super.renderJavaScript(response, "alert('EXCEL第" + (j + 1) + "行，盘点后数量不合法，必须填写数字！');history.back();");
				return null;
			}
			// 4. *盘点单价
			String c5v = StringUtils.replace(StringUtils.replace(c5, ",", ""), "，", "");
			if (StringUtils.isNotBlank(c5) && StringUtils.isNotBlank(c5v) && vali_dig.matcher(c5v).matches()) {
				tempEntity.getMap().put("price", new BigDecimal(subZeroAndDot(c5v)));
			} else {
				super.renderJavaScript(response, "alert('EXCEL第" + (j + 1) + "行，单价不合法，必须填写数字！');history.back();");
				return null;
			}

			// 5. *盘点后金额
			String c6v = StringUtils.replace(StringUtils.replace(c6, ",", ""), "，", "");
			if (StringUtils.isNotBlank(c6) && StringUtils.isNotBlank(c6v) && vali_dig.matcher(c6v).matches()) {
				tempEntity.getMap().put("ver_money", new BigDecimal(subZeroAndDot(c6v)));
			} else {
				super.renderJavaScript(response, "alert('EXCEL第" + (j + 1) + "行，盘点后金额不合法，必须填写数字！');history.back();");
				return null;
			}

			// 6. *差异原因
			if (StringUtils.isNotBlank(c7)) {
				if (c7.length() > 200) {
					super.renderJavaScript(response, "alert('EXCEL第" + (j + 1)
							+ "行，差异原因太长，最多不能超过200字！');history.back();");
					return null;
				} else {
					tempEntity.getMap().put("memo", c7);
				}
			}
			tempList.add(tempEntity);
		}
		// 判断导入的数量与真实的数量是否一致
		if(entityList.size() != tempList.size()){
			super.renderJavaScript(response, "alert('导入的Excel多出或缺失行项目！请不要增加或删除导出的Excel中的行项目！');history.back();");
			return null;
		}
		for(int i=0; i<entityList.size(); i++){
			// 不能乱序
			if(entityList.get(i).getMap().get("store_id").equals(tempList.get(i).getMap().get("store_id"))
					&& entityList.get(i).getMap().get("goods_id").equals(tempList.get(i).getMap().get("goods_id"))){
				entityList.get(i).getMap().put("ver_stocks", tempList.get(i).getMap().get("ver_stocks"));
				entityList.get(i).getMap().put("ver_money", tempList.get(i).getMap().get("ver_money"));
				entityList.get(i).getMap().put("price", tempList.get(i).getMap().get("price"));
				entityList.get(i).getMap().put("memo", tempList.get(i).getMap().get("memo"));
			}else{
				super.renderJavaScript(response, "alert('导入的Excel行项目次序错乱！请不要颠倒导出的Excel中的行项目上下次序！');history.back();");
				return null;
			}
		}
		request.setAttribute("allList", entityList);
		return mapping.findForward("input");
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
	
	
	/**
	 * 这是一个通用的方法，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 * 
	 * @param title
	 *            表格标题名
	 * @param headers
	 *            表格属性列名数组
	 * @param dataList
	 *            需要显示的数据集合,集合中Object数组。此方法支持的
	 *            Object类型有基本数据类型及String,Date,byte[](图片数据)
	 * @param out
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param pattern
	 *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
	 */
	private void exportExcel(String title, String[] headers,
			List<Object[]> dataList, OutputStream out, String dateFormat) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth(15);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		// 生成并设置另一个样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.WHITE.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		font2.setFontHeightInPoints((short) 10);
		// 把字体应用到当前的样式
		style2.setFont(font2);
		
		// 红色的头style
		HSSFCellStyle style3 = workbook.createCellStyle();
		// 设置这些样式
		style3.setFillForegroundColor(HSSFColor.WHITE.index);
		style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成一个字体
		HSSFFont font3 = workbook.createFont();
		font3.setFontHeightInPoints((short) 12);
		font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font3.setColor(HSSFColor.RED.index);
		// 把字体应用到当前的样式
		style3.setFont(font3);

		// 声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		// 定义注释的大小和位置,详见文档
		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
				0, 0, 0, (short) 4, 2, (short) 6, 5));
		// 设置注释内容
		comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
		comment.setAuthor("Jacky");

		// 第一行title 合并单元格
		// 四个参数分别是：起始行，起始列，结束行，结束列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.length-1));
		HSSFRow row = sheet.createRow(0);
		if(headers!=null && headers.length>0){
			row.setHeightInPoints(22);
			HSSFCell cell = row.createCell(0);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(title);
			cell.setCellValue(text);
		}
		
		// 产生表格标题行
		row = sheet.createRow(1);
		row.setHeightInPoints(18);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			if(i>=5 && i<=7){
				cell.setCellStyle(style3);
			}else{
				cell.setCellStyle(style);
			}
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		// 遍历集合数据，产生数据行
		for (int k = 0; k < dataList.size(); k++) {
			row = sheet.createRow(k + 2);
			row.setHeightInPoints(16);
			Object[] obj = dataList.get(k);
			for (int i = 0; i < obj.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style2);
				try {
					Object value = obj[i];
					if (value == null){
						cell.setCellValue("");
					}else if (value instanceof Integer) {
						int intValue = (Integer) value;
						cell.setCellValue(intValue);
					} else if (value instanceof Float) {
						float fValue = (Float) value;
						cell.setCellValue(fValue);
					} else if (value instanceof Double) {
						double dValue = (Double) value;
						cell.setCellValue(dValue);
					} else if (value instanceof Long) {
						long longValue = (Long) value;
						cell.setCellValue(longValue);
					} else if (value instanceof BigDecimal){
						cell.setCellValue(value.toString());
					}
					else if (value instanceof Date) {
						Date date = (Date) value;
						if(dateFormat==null){
							dateFormat = "yyyy-MM-dd";
						}
						SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
						cell.setCellValue(sdf.format(date));
					} 
//					else if (value instanceof byte[]) {
//						// 有图片时，设置行高为60px;
//						row.setHeightInPoints(60);
//						// 设置图片所在列宽度为80px,注意这里单位的一个换算
//						sheet.setColumnWidth(i, (short) (35.7 * 80));
//						// sheet.autoSizeColumn(i);
//						byte[] bsValue = (byte[]) value;
//						HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
//								1023, 255, (short) 6, k, (short) 6, k);
//						anchor.setAnchorType(2);
//						patriarch.createPicture(anchor, workbook.addPicture(
//								bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
//					} 
					else {
						// 其它数据类型都当作字符串简单处理
						String textValue = value.toString();
						cell.setCellValue(textValue);
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} finally {
					// 清理资源
				}
			}
		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
