package com.ebiz.mmt.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.JBaseGoods;
import com.ebiz.mmt.domain.JBaseStore;
import com.ebiz.mmt.domain.JStocksVerify;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Jiang,JiaYong
 * @version 2013-08-29
 */
public class KonkaR3ShopStockAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String branch_area_name = (String) dynaBean.get("branch_area_name");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String r3_code = (String) dynaBean.get("r3_code");
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String is_pd = (String) dynaBean.get("is_pd");
		String is_jz = (String) dynaBean.get("is_jz");
		// 经办名称 与konka_r3_shop
		String handle_name_like = (String) dynaBean.get("handle_name_like");
		Pager pager = (Pager) dynaBean.get("pager");
		String excel_all = (String) dynaBean.get("excel_all");
		// 一级客户类型
		String customer_type1 = (String) dynaBean.get("v_customer_type1");

		// 二级客户类型
		String customer_type2 = (String) dynaBean.get("v_customer_type2");

		// 获取登录用户 企业信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		// 判断角色
		PeRoleUser peRoleUser = new PeRoleUser();
		peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = super.getFacade().getPeRoleUserService().getPeRoleUserList(peRoleUser);
		Boolean role_id_eq_10 = false;
		for (PeRoleUser temp : peRoleUserList) {
			if (temp.getRole_id() == 10) {
				role_id_eq_10 = true;
				break;
			}
		}

		// 如果是总部则需要查询分公司
		if (role_id_eq_10) {
			KonkaDept kkd = new KonkaDept();
			kkd.setDept_type(3);
			List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(kkd);
			request.setAttribute("konkaDeptList", konkaDeptList);
		}

		KonkaR3Shop entity = new KonkaR3Shop();
		
		entity.setIs_del(0L); // 查询未删除的
		entity.getMap().put("customer_name_like", customer_name_like);
		if(StringUtils.isNotEmpty(r3_code)){
			entity.setR3_code(r3_code);
		}
		if(StringUtils.isNotEmpty(r3_code_like)){
			entity.getMap().put("r3_code_like", r3_code_like);
		}
		
		if (StringUtils.isNotBlank(branch_area_name)) {
			entity.setBranch_area_name(branch_area_name);
		}
		if (StringUtils.isNotBlank(is_pd)) {
			entity.getMap().put("is_pd", is_pd);
		}
		if (StringUtils.isNotBlank(is_jz)) {
			entity.getMap().put("is_jz", is_jz);
		}
		if (StringUtils.isNotBlank(customer_type2)) {
			entity.getMap().put("cus_type2", customer_type2);
			dynaBean.set("customer_type2", customer_type2);
		} else {
			if (StringUtils.isNotBlank(customer_type1)) {
				entity.getMap().put("cus_type1", customer_type1);
				dynaBean.set("customer_type1", customer_type1);
			}
		}
		if (StringUtils.isNotBlank(handle_name_like)) {
			entity.getMap().put("handle_name_like", handle_name_like);
		}

		// 数据级别判断开始
		Long __dept_id = peProdUser.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(peProdUser.getId()); // 获取当前用户的最高可视部门级别
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(peProdUser.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				entity.getMap().put("dept_id_start", __dept_id);
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = peProdUser.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);
			break;
		case 0:
			__dept_id = peProdUser.getDept_id(); // 默认为当前用户所在部门
			// entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("filter_by_ywy_id_eq", peProdUser.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", peProdUser.getId());
		// 数据级别判断结束

		// entity.getMap().put("viewd_by_user_id_eq", peProdUser.getId());

		Long recordCount = getFacade().getKonkaR3ShopService().getKonkaR3ShopWithJzAndPdCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService().getKonkaR3ShopWithJzAndPdPaginatedList(
				entity);
		request.setAttribute("entityList", entityList);

		List<KonkaDept> konkaDeptList = super.getDeptInfoListWithOutLimit(mapping, form, request, response);
		request.setAttribute("konkaDeptList", konkaDeptList);
		
		// 导出
		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			if (recordCount.intValue() > 20000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
						+ "！');history.back();");
				return null;
			}
			entity.getRow().setCount(recordCount.intValue());
			List<KonkaR3Shop> entityList1 = getFacade().getKonkaR3ShopService().getKonkaR3ShopWithJzAndPdList(entity);
			dynaBean.set("excel_all", excel_all);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("客户库存")
					+ ".xls");
			entity.getRow().setFirst(0);
			entity.getRow().setCount(recordCount.intValue());

			request.setAttribute("allList", entityList1);
			return new ActionForward("/../manager/admin/KonkaR3ShopStock/report_list.jsp");
		}

		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String cust_id = (String) dynaBean.get("cust_id");
		// String excel_all_view = (String) dynaBean.get("excel_all_view");
		String store_id = (String) dynaBean.get("store_id");// 仓库ID
		String goods_id = (String) dynaBean.get("goods_id");// 型号ID
		Pager pager = (Pager) dynaBean.get("pager");
		// logger.info("excel_all_view------------>" + excel_all_view);

		if (StringUtils.isEmpty(cust_id)) {
			this.saveError(request, "errors.long", new String[] { cust_id });
			return mapping.findForward("view");
		}
		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(Long.valueOf(cust_id));
		konkaR3Shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);

		request.setAttribute("konkaR3Shop", konkaR3Shop);

		JBaseGoods jBaseGoods = new JBaseGoods();
		if (StringUtils.isNotBlank(store_id)) {
			jBaseGoods.getMap().put("store_id", store_id);
		} else {
			jBaseGoods.getMap().put("r3_code", konkaR3Shop.getR3_code());
		}
		if (StringUtils.isNotBlank(goods_id)) {
			jBaseGoods.getMap().put("goods_id", goods_id);
		}
		jBaseGoods.getMap().put("r3_id", cust_id);
		List<JBaseGoods> entityList = super.getFacade().getJBaseGoodsService()
				.getJBaseGoodsForComeOutNumWithMoney(jBaseGoods);

		Long recordCount = Long.valueOf(entityList.size());
		int pageSize = recordCount.intValue();
		if (pageSize <= 10) {
			pageSize = 10;
		}
		pager.init(recordCount, pageSize, pager.getRequestPage());
		request.setAttribute("entityList", entityList);

		JBaseStore jBaseStore = new JBaseStore();
		jBaseStore.getMap().put("order_by_is_dft_buy_store_desc", true);
		jBaseStore.setC_id(Long.valueOf(cust_id));
		jBaseStore.setIs_del(0);
		List<JBaseStore> jBaseStoreList = super.getFacade().getJBaseStoreService().getJBaseStoreList(jBaseStore);
		request.setAttribute("jBaseStoreList", jBaseStoreList);
		JBaseGoods goods = new JBaseGoods();
		goods.setC_id(Long.valueOf(cust_id));
		goods.setGoods_stutes(0);
		List<JBaseGoods> goodsList = super.getFacade().getJBaseGoodsService().getJBaseGoodsList(goods);
		if (null != goodsList && goodsList.size() > 0) {
			request.setAttribute("jBaseGoodsList", goodsList);
		}
		dynaBean.set("cust_id", cust_id);

		return mapping.findForward("view");
	}

	public ActionForward view_old(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String cust_id = (String) dynaBean.get("cust_id");
		// String excel_all_view = (String) dynaBean.get("excel_all_view");
		String store_id = (String) dynaBean.get("store_id");// 仓库ID
		String goods_id = (String) dynaBean.get("goods_id");// 型号ID
		Pager pager = (Pager) dynaBean.get("pager");
		// logger.info("excel_all_view------------>" + excel_all_view);

		if (StringUtils.isEmpty(cust_id)) {
			this.saveError(request, "errors.long", new String[] { cust_id });
			return mapping.findForward("view");
		}
		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(Long.valueOf(cust_id));
		konkaR3Shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);

		request.setAttribute("konkaR3Shop", konkaR3Shop);

		KonkaR3Shop krs = new KonkaR3Shop();
		if (StringUtils.isNotBlank(store_id)) {
			krs.getMap().put("store_id", store_id);
		} else {
			krs.getMap().put("r3_code", konkaR3Shop.getR3_code());
		}
		if (StringUtils.isNotBlank(goods_id)) {
			krs.getMap().put("goods_id", goods_id);
		}
		krs.setR3_code(konkaR3Shop.getR3_code());
		List<KonkaR3Shop> entityList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopStockByR3CodeList(krs);
		for (KonkaR3Shop kr3shop : entityList) {
			JBaseGoods jbg = new JBaseGoods();
			if (StringUtils.isNotBlank(store_id)) {// 送达方
				jbg.getMap().put("store_id", store_id);
			} else {
				jbg.getMap().put("r3_code", konkaR3Shop.getR3_code());
			}
			// 产品初始化信息
			JBaseGoods jGoods = new JBaseGoods();
			jGoods.setC_id(Long.valueOf(cust_id));
			jGoods.setGoods_name(kr3shop.getMap().get("pd_name").toString());
			List<JBaseGoods> jGoodsList = getFacade().getJBaseGoodsService().getJBaseGoodsList(jGoods);
			if (jGoodsList.size() == 1) {
				jGoods = jGoodsList.get(0);
			} else {
				logger.info("未知道商品");
			}
			jbg.getMap().put("goods_id", jGoods.getGoods_id());
			jbg.getMap().put("md_name", jGoods.getGoods_name());
			jbg.getMap().put("r3_id", konkaR3Shop.getId());
			jbg = super.getFacade().getJBaseGoodsService().getJBaseGoodsForComeOutNumNew(jbg);
			if (jbg != null) {
				kr3shop.getMap().put("come_num", jbg.getMap().get("come_num"));
				kr3shop.getMap().put("out_num", jbg.getMap().get("out_num"));
				kr3shop.getMap().put("stocks", jbg.getMap().get("init_num"));
			}
		}

		Long recordCount = Long.valueOf(entityList.size());
		// 导出
		// if (StringUtils.isNotBlank(excel_all_view) &&
		// "1".equals(excel_all_view)) {
		// if (recordCount > 20000) {
		// renderJavaScript(response, "alert('" + super.getMessage(request,
		// "export.too.many")
		// + "！');history.back();");
		// return null;
		// }
		// dynaBean.set("excel_all_view", excel_all_view);
		// request.setAttribute("allList", entityList);
		// }
		int pageSize = recordCount.intValue();
		if (pageSize <= 10) {
			pageSize = 10;
		}
		pager.init(recordCount, pageSize, pager.getRequestPage());
		request.setAttribute("entityList", entityList);

		// 导出
		// if (StringUtils.isNotBlank(excel_all_view) &&
		// "1".equals(excel_all_view)) {
		// if (recordCount > 20000) {
		// renderJavaScript(response, "alert('" + super.getMessage(request,
		// "export.too.many")
		// + "！');history.back();");
		// return null;
		// }
		// List<KonkaR3Shop> entityList1 =
		// super.getFacade().getKonkaR3ShopService().getKonkaR3ShopStockByR3CodeList(krs);
		// dynaBean.set("excel_all_view", excel_all_view);
		// request.setAttribute("allList", entityList1);
		// }
		JBaseStore jBaseStore = new JBaseStore();
		jBaseStore.getMap().put("order_by_is_dft_buy_store_desc", true);
		jBaseStore.setC_id(Long.valueOf(cust_id));
		jBaseStore.setIs_del(0);
		List<JBaseStore> jBaseStoreList = super.getFacade().getJBaseStoreService().getJBaseStoreList(jBaseStore);
		request.setAttribute("jBaseStoreList", jBaseStoreList);
		JBaseGoods goods = new JBaseGoods();
		goods.setC_id(Long.valueOf(cust_id));
		goods.setGoods_stutes(0);
		List<JBaseGoods> goodsList = super.getFacade().getJBaseGoodsService().getJBaseGoodsList(goods);
		if (null != goodsList && goodsList.size() > 0) {
			request.setAttribute("jBaseGoodsList", goodsList);
		}
		dynaBean.set("cust_id", cust_id);

		return mapping.findForward("view");
	}

	/**
	 * 库存查询明细 2014-08-19
	 */
	public ActionForward viewDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String store_id = (String) dynaBean.get("store_id");// 仓库ID
		String goods_id = (String) dynaBean.get("goods_id");// 仓库ID
		String direction = (String) dynaBean.get("direction");// 0：进 1：销
		String cust_id = (String) dynaBean.get("cust_id");

		if (StringUtils.isEmpty(cust_id)) {
			this.saveError(request, "errors.long", new String[] { cust_id });
			return mapping.findForward("view");
		}

		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(Long.parseLong(cust_id));
		konkaR3Shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);

		request.setAttribute("konkaR3Shop", konkaR3Shop);

		JBaseGoods jBaseGoods = new JBaseGoods();
		if (StringUtils.isNotBlank(store_id)) {
			jBaseGoods.getMap().put("store_id", store_id);
		} else {
			jBaseGoods.getMap().put("r3_code", konkaR3Shop.getR3_code());
		}
		if (StringUtils.isNotBlank(goods_id)) {
			jBaseGoods.getMap().put("goods_id", goods_id);
		} else {
			super.renderJavaScript(response, "alert('数据丢失！');history.back();");
			return null;
		}
		if (StringUtils.isNotBlank(direction)) {
			jBaseGoods.getMap().put("direction", direction);
		} else {
			jBaseGoods.getMap().put("direction", 0);
		}
		jBaseGoods.getMap().put("r3_id", cust_id);
		List<JBaseGoods> entityList = super.getFacade().getJBaseGoodsService()
				.getJBaseGoodsForComeOutNumWithMoneyDetail(jBaseGoods);
		request.setAttribute("entityList", entityList);
		if (null != entityList && entityList.size() > 0) {
			if (entityList.get(0).getMap().get("direction") != null) {
				request.setAttribute("direction", entityList.get(0).getMap().get("direction"));
			}
		}
		return new ActionForward("/admin/KonkaR3ShopStock/view_detail.jsp");
	}

	// 就是将上个月月末的库存作为这个月月初的库存，自动重新盘点
	public ActionForward jiezhuan(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		super.saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		String cust_id = (String) dynaBean.get("cust_id");

		if (StringUtils.isEmpty(cust_id)) {
			this.saveError(request, "errors.long", new String[] { cust_id });
			return mapping.findForward("view");
		}

		Calendar calendar = Calendar.getInstance();
		int c_day = calendar.get(Calendar.DAY_OF_MONTH);
		int c_month = calendar.get(Calendar.MONTH);
		if (c_day > 5) {
			super.renderJavaScript(response, "alert('结转日期应该在每月1号-5号之间！！');history.back();");
			return null;
		}

		// 如果上月25号-31号已经全部盘点过，则提示无需再结转
		JStocksVerify jsv1 = new JStocksVerify();
		jsv1.setC_id(Long.parseLong(cust_id));
		jsv1.setType(4);
		jsv1.getRow().setCount(1);
		jsv1 = getFacade().getJStocksVerifyService().getJStocksVerifyForLastedVerify(jsv1);
		if (null != jsv1) {
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(jsv1.getOpr_date());
			int o_day = calendar1.get(Calendar.DAY_OF_MONTH);
			int o_month = calendar1.get(Calendar.MONTH);
			if (c_month == (o_month + 1)) {// 如果上月月末已经全部盘点过了，则不需要结转了
				if (o_day <= 31 && o_day >= 25) {
					super.renderJavaScript(response, "alert('上月" + o_day + "号已经全部盘点过，本月无需结转！');history.back();");
					return null;
				}
			}
		}

		// 如果1号到目前为止有盘点过，则提示无需再结转
		JStocksVerify jsv = new JStocksVerify();
		jsv.setC_id(Long.parseLong(cust_id));
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
		entity.getMap().put("c_id", Long.parseLong(cust_id));

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
											Long.parseLong(cust_id), null, 2, new String[] {},
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

	public ActionForward getJBDataBydeptName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String branch_area_name = (String) dynaBean.get("branch_area_name"); // 分公司
		List<String> jbList = new ArrayList<String>();

		if (branch_area_name != null && branch_area_name.length() > 0) {

			List<KonkaDept> kklist = new ArrayList<KonkaDept>();

			KonkaDept kk2 = new KonkaDept();

			KonkaDept kk = new KonkaDept();
			kk.setIs_del(0);
			kk.setDept_name(branch_area_name);
			kklist = super.getFacade().getKonkaDeptService().getKonkaDeptList(kk);
			if (kklist != null && kklist.size() == 1) {
				kk = kklist.get(0);
				kk2.getMap().put("fgs_dept_id", kk.getDept_id());
				kk2.setIs_del(0);
				kklist.clear();
				kklist = super.getFacade().getKonkaDeptService().getKonkaDeptAndJbNameList(kk2);

				request.setAttribute("kkList", kklist);
			}

			for (KonkaDept x : kklist) {
				jbList.add(x.getDept_name());
			}
			super.renderJson(response, JSON.toJSONString(jbList));
		} else {
			super.renderJson(response, JSON.toJSONString(jbList));
		}

		return null;
	}
}