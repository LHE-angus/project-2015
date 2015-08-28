package com.ebiz.mmt.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseProvinceList;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EntpShop;
import com.ebiz.mmt.domain.JxcSellBill;
import com.ebiz.mmt.domain.KonkaBranchCategory;
import com.ebiz.mmt.domain.KonkaR3MmtMatch;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaShopDevelop;
import com.ebiz.mmt.domain.MmtEntpShop;
import com.ebiz.mmt.domain.MvMmtjxcForKonkafight;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaShopMapFightAction extends BaseAction {
	protected String yyyymmdd = "20111001";

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		setNaviStringToRequestScope(form, request);

		return mapping.findForward("list");
	}

	public ActionForward province(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String p_index = (String) dynaBean.get("p_index");

		request.setAttribute("p_name", super.getBaseProvinceListFourByPindex(Long.valueOf(p_index)).getP_name());

		Map<String, Double> map_all = getKonkaShopCount(request, p_index);
		request.setAttribute("map_all", map_all);

		return new ActionForward("/admin/KonkaShopMapFight/province.jsp");
	}

	public ActionForward city(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String p_index = (String) dynaBean.get("p_index");

		request.setAttribute("p_name", super.getBaseProvinceListFourByPindex(Long.valueOf(p_index)).getP_name());
		BaseProvinceListFour city_childs = new BaseProvinceListFour();
		city_childs.setPar_index(Long.valueOf(p_index));
		city_childs.setDel_mark(0);
		List<BaseProvinceListFour> town_list = super.getFacade().getBaseProvinceListFourService()
				.getBaseProvinceListFourList(city_childs);
		for (BaseProvinceListFour t : town_list) {
			MvMmtjxcForKonkafight mvMmtjxcForKonkafight = new MvMmtjxcForKonkafight();
			mvMmtjxcForKonkafight.setShop_p_index(t.getP_index().intValue());
			mvMmtjxcForKonkafight = super.getFacade().getMvMmtjxcForKonkafightService()
					.getMvMmtjxcForKonkafightSumXlXe(mvMmtjxcForKonkafight);
			if (mvMmtjxcForKonkafight != null) {
				t.getMap().put("r3_count", mvMmtjxcForKonkafight.getMap().get("r3_count").toString());
				t.getMap().put("sum_xl", mvMmtjxcForKonkafight.getMap().get("sum_xl").toString());
				t.getMap().put("sum_xe", mvMmtjxcForKonkafight.getMap().get("sum_xe").toString());
			}
		}

		request.setAttribute("town_list", town_list);

		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		
		request.setAttribute("sel_year", year);
		request.setAttribute("sel_month", month);
		
		// Map<String, Double> map_all = getKonkaShopCount(request, p_index);
		// Map<String, Double> map_cur = getKonkaShopDevelop(request, p_index,
		// yyyymmdd);
		// request.setAttribute("map_all", map_all);
		// //request.setAttribute("map_cur", map_cur);

		// // 市 网点类型 R3 、代理商、代理商网点、其它的销量
		// // Double xl_arr[] = { 0.00, 0.00, 0.00, 0.00 }; 暂时不查 代理商，经销商，其他销售额
		// Double xl_arr[] = { 0.00 };
		// // 市总销售额
		// Double totalXE = 0.00;
		// // 市总销量
		// Double totalXL = 0.00;
		// if (town_list != null && town_list.size() > 0) {
		// for (BaseProvinceListFour e : town_list) {
		// // 当前区域下所有网点销售统计
		// getAreaSalesInfoForNew(request, e, xl_arr);
		// // 市总计
		// totalXL += (Double) e.getMap().get("perShopTypeXL");
		// totalXE += (Double) e.getMap().get("perShopTypeXE");
		// }
		// }

		// request.setAttribute("totalXE", totalXE.toString());
		// request.setAttribute("totalXL", totalXL.toString());
		// request.setAttribute("allxeList", Arrays.asList(xl_arr));

		return new ActionForward("/admin/KonkaShopMapFight/city.jsp");
	}

	// /////////////////////////////////////////////////////////

	public ActionForward province_list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String p_index = (String) dynaBean.get("p_index");

		// p_index区域所有黑电经销网点 、R3网点 、代理商、 代理商网点
		Map<String, Double> map_all = getKonkaShopCount(request, p_index);

		// 2011年10月后新开拓网点、销售统计
		String ymd = "20111001";
		Map<String, Double> map_cur = getKonkaShopDevelop(request, p_index, ymd);

		request.setAttribute("map_all", map_all);
		request.setAttribute("map_cur", map_cur);

		return new ActionForward("/admin/KonkaShopMapFight/map_province.jsp");
	}

	public ActionForward city_list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String p_index = (String) dynaBean.get("p_index");
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");

		if (StringUtils.isNotBlank(country) && StringUtils.isNotBlank(city) && StringUtils.isNotBlank(province)) {
			dynaBean.set("p_index", country);
			return town_list(mapping, form, request, response);
		} else if (StringUtils.isNotBlank(city) && StringUtils.isNotBlank(province)) {
			p_index = city;
		} else if (StringUtils.isNotBlank(province)) {
			dynaBean.set("p_index", province);
			return province_list(mapping, form, request, response);
		}

		// 区域名获取
		BaseProvinceListFour self = new BaseProvinceListFour();
		self.setP_index(Long.valueOf(p_index));
		self = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(self);
		dynaBean.set("p_name", self.getP_name());

		// 直辖市的区
		if (self.getP_level() == 1) {
			dynaBean.set("p_index", p_index);
			return province_list(mapping, form, request, response);
		} else if (self.getP_level() == 3) {
			dynaBean.set("p_index", p_index);
			return town_list(mapping, form, request, response);
		}

		// 当前年月日
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;

		if (province == null && p_index != null) {
			BaseProvinceListFour area = new BaseProvinceListFour();
			area.setP_index(Long.valueOf(p_index));
			List<BaseProvinceListFour> pList = super.getFacade().getBaseProvinceListFourService()
					.getBaseProvinceListFourParentList(area);
			if (pList != null && pList.size() > 0) {
				for (BaseProvinceListFour bp : pList) {
					if (bp.getP_level() == 1) {
						dynaBean.set("province", "" + bp.getP_index());
					} else if (bp.getP_level() == 2) {
						dynaBean.set("city", "" + bp.getP_index());
					} else if (bp.getP_level() == 3) {
						dynaBean.set("country", "" + bp.getP_index());
					}
				}
			}
		}

		// 取下一级区域
		BaseProvinceListFour city_childs = new BaseProvinceListFour();
		city_childs.setPar_index(Long.valueOf(p_index));
		city_childs.setDel_mark(0);
		List<BaseProvinceListFour> town_list = super.getFacade().getBaseProvinceListFourService()
				.getBaseProvinceListFourList(city_childs);

		// 市 网点类型 R3 、代理商、代理商网点、其它的销量
		Double xl_arr[] = { 0.00, 0.00, 0.00, 0.00 };
		// 市总销售额
		Double totalXE = 0.00;
		// 市总销量
		Double totalXL = 0.00;
		if (town_list != null && town_list.size() > 0) {
			for (BaseProvinceListFour e : town_list) {
				// 当前区域下所有网点销售统计
				getAreaSalesInfo(request, e, xl_arr);
				// 市总计
				totalXL += (Double) e.getMap().get("perShopTypeXL");
				totalXE += (Double) e.getMap().get("perShopTypeXE");
			}
		}

		dynaBean.set("sel_year", year);
		dynaBean.set("sel_month", month);

		// 页面显示---市家电下乡官方数据
		request.setAttribute("town_list", town_list);
		request.setAttribute("totalXE", totalXE.toString());
		request.setAttribute("totalXL", totalXL.toString());
		request.setAttribute("allxeList", Arrays.asList(xl_arr));

		getShopSearchList(mapping, form, request, response);
		return new ActionForward("/admin/KonkaShopMapFight/map_citytown.jsp");
	}

	public ActionForward town_list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String p_index = (String) dynaBean.get("p_index");
		// 当前年月日
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		int day = now.get(Calendar.DATE);
		// 销售额统计期间
		String e_ymd = "";

		if (month < 10) {
			e_ymd = year + "0" + month;
		} else {
			e_ymd = year + "" + month;
		}

		if (day < 10) {
			e_ymd = e_ymd + "0" + day;
		} else {
			e_ymd = e_ymd + "" + day;
		}

		// 区域名获取
		BaseProvinceListFour self = new BaseProvinceListFour();
		self.setP_index(Long.valueOf(p_index));
		self = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(self);
		dynaBean.set("p_name", self.getP_name());

		if (dynaBean.get("province") == null && p_index != null) {
			BaseProvinceListFour area = new BaseProvinceListFour();
			area.setP_index(Long.valueOf(p_index));
			List<BaseProvinceListFour> pList = super.getFacade().getBaseProvinceListFourService()
					.getBaseProvinceListFourParentList(area);
			if (pList != null && pList.size() > 0) {
				for (BaseProvinceListFour bp : pList) {
					if (bp.getP_level() == 1) {
						dynaBean.set("province", "" + bp.getP_index());
					} else if (bp.getP_level() == 2) {
						dynaBean.set("city", "" + bp.getP_index());
					} else if (bp.getP_level() == 3) {
						dynaBean.set("country", "" + bp.getP_index());
					}
				}
			}
		}

		// 当前区域下所有网点销售统计
		getAreaSalesInfo(request, self, null);

		dynaBean.set("sel_year", year);
		dynaBean.set("sel_month", month);

		// 页面显示---区、县家电下乡官方数据
		request.setAttribute("town_self", self);

		getShopSearchList(mapping, form, request, response);
		return new ActionForward("/admin/KonkaShopMapFight/map_citytown.jsp");
	}

	/*
	 * 当前区域所有网点销售统计
	 */
	public void getAreaSalesInfo(HttpServletRequest request, BaseProvinceListFour self, Double xl_arr[])
			throws Exception {

		BaseProvinceListFour child_areas = new BaseProvinceListFour();
		child_areas.setP_index(self.getP_index());
		child_areas.getMap().put("p_level", self.getP_level() - 1);
		List<BaseProvinceListFour> list = super.getFacade().getBaseProvinceListFourService()
				.getBaseProvinceListFourSonTreeList(child_areas);
		// 市辖区，县，镇--p_index串
		String p_index_String = "";
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				p_index_String = p_index_String + list.get(i).getP_index() + ",";
			}
			p_index_String = StringUtils.removeEnd(p_index_String, ",");
		}

		// 各区域销售统计取得
		JxcSellBill jxcsb = new JxcSellBill();
		jxcsb.getMap().put("p_index_String", p_index_String);
		jxcsb.getMap().put("sell_date_ge", yyyymmdd);
		List<JxcSellBill> jxcList = super.getFacade().getJxcSellBillService().getJxcSellBillForArea(jxcsb);
		// 当前区域 网点类型 R3 、代理商、代理商网点、其它的销量
		Double perShop_xl_arr[] = { 0.00, 0.00, 0.00, 0.00 };
		// 当前区域网点总销售额
		Double perShopTypeXE = 0.00;
		// 当前区域网点总销量
		Double perShopTypeXL = 0.00;

		for (int i = 0; i < jxcList.size(); i++) {
			JxcSellBill jxc = jxcList.get(i);

			Double sum_xl = Double.valueOf(jxc.getMap().get("sum_xl").toString());
			Double sum_xe = Double.valueOf(jxc.getMap().get("sum_xe").toString());

			boolean flg = true;
			// 分类处理
			if ("1".equals("" + jxc.getMap().get("is_r3"))) {
				perShop_xl_arr[0] += sum_xl;
				if (xl_arr != null && xl_arr.length > i)
					xl_arr[0] += sum_xl;
				flg = false;
			}
			if (jxc.getMap().get("category_id") != null) {
				String category_id = "" + jxc.getMap().get("category_id");
				if ("10".equals(category_id)) {
					perShop_xl_arr[1] += sum_xl;
					if (xl_arr != null && xl_arr.length > i)
						xl_arr[1] += sum_xl;
				} else {
					perShop_xl_arr[2] += sum_xl;
					if (xl_arr != null && xl_arr.length > i)
						xl_arr[2] += sum_xl;
				}
				flg = false;
			}

			if (flg) {
				perShop_xl_arr[3] += sum_xl;
				if (xl_arr != null && xl_arr.length > i)
					xl_arr[3] += sum_xl;
			}

			perShopTypeXL += sum_xl;
			perShopTypeXE += sum_xe;

		}
		self.getMap().put("shopTypeXL_list", Arrays.asList(perShop_xl_arr));
		self.getMap().put("perShopTypeXL", perShopTypeXL);
		self.getMap().put("perShopTypeXE", perShopTypeXE);
	}

	/*
	 * 当前区域所有网点销售统计 by wang,yang 2012-05-22
	 */
	public void getAreaSalesInfoForNew(HttpServletRequest request, BaseProvinceListFour self, Double xl_arr[])
			throws Exception {

		// 各区域销售统计取得
		JxcSellBill jxcsb = new JxcSellBill();
		jxcsb.setShop_p_index(self.getP_index());
		jxcsb.getMap().put("sell_date_ge", yyyymmdd);

		Long R3_count = getFacade().getJxcSellBillService().getJxcSellBillDetailsCountSumForR3(jxcsb);

		Long sum_xl = getFacade().getJxcSellBillService().getJxcSellBillDetailsCountSum(jxcsb);

		Long sum_xe = getFacade().getJxcSellBillService().getJxcSellBillMoneySum(jxcsb);

		// 当前区域 网点类型 R3 、代理商、代理商网点、其它的销量
		// Double perShop_xl_arr[] = { 0.00,0.00,0.00,0.00};
		Double perShop_xl_arr[] = { 0.00 };
		// 当前区域网点总销售额
		Double perShopTypeXE = 0.00;
		// 当前区域网点总销量
		Double perShopTypeXL = 0.00;

		perShop_xl_arr[0] += R3_count;
		xl_arr[0] += R3_count;

		perShopTypeXL += sum_xl;
		perShopTypeXE += sum_xe;

		self.getMap().put("shopTypeXL_list", Arrays.asList(perShop_xl_arr));
		self.getMap().put("perShopTypeXL", perShopTypeXL);
		self.getMap().put("perShopTypeXE", perShopTypeXE);
	}

	/*
	 * AJAX获取网点信息及销量
	 */
	public ActionForward ajaxGetShopInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String shop_id = (String) dynaBean.get("shop_id");
		String seq = (String) dynaBean.get("seq");
		EntpShop esp = new EntpShop();
		if (StringUtils.isNotBlank(shop_id)) {
			esp.setShop_id(Long.valueOf(shop_id));
		} else {
			return null;
		}
		// 网点信息
		esp = super.getFacade().getEntpShopService().getEntpShop(esp);

		// 网点所在地区
		String td_dq = "";
		BaseProvinceListFour area = new BaseProvinceListFour();
		area.setP_index(Long.valueOf(esp.getP_index()));
		List<BaseProvinceListFour> pList = super.getFacade().getBaseProvinceListFourService()
				.getBaseProvinceListFourParentList(area);
		if (pList != null && pList.size() > 0) {
			for (BaseProvinceListFour bp : pList) {
				td_dq = td_dq + bp.getP_name() + " ";
			}
		}

		// 网点按月销售统计取得
		JxcSellBill jxcsb = new JxcSellBill();
		jxcsb.getMap().put("sell_date_ge", yyyymmdd);
		jxcsb.setShop_id(Long.valueOf(shop_id));
		List<JxcSellBill> jxcList = super.getFacade().getJxcSellBillService().getJxcSellBillForShop(jxcsb);

		// 页面【授权编号、地区、行业、主营产品、法人代表、电话、传真、联系人、电子邮箱、邮编、地址、网址】
		// [td_sqbh、td_dq、td_hy、td_zycp、td_frdb、td_dh、td_cz、td_lxr、td_dzyx、td_yb、td_dz、td_wz]
		//
		StringBuffer jsonBuffer = new StringBuffer();
		StringBuffer jsonBuffer_xl = new StringBuffer();
		if (esp != null) {
			jsonBuffer.append("\"ajax_status\":\"1\",");
			jsonBuffer.append("\"seq\":\"").append(seq).append("\",");

			jsonBuffer.append("\"shop_id\":\"").append(esp.getShop_id()).append("\",");
			jsonBuffer.append("\"shop_name\":\"").append(replaceSpecialCharacter(esp.getShop_name(), "\t|\r|\n"))
					.append("\",");

			jsonBuffer.append("\"td_sqbh\":\"").append("--").append("\",");
			jsonBuffer.append("\"td_dq\":\"").append(td_dq).append("\",");
			jsonBuffer.append("\"td_hy\":\"").append(esp.getC_index()).append("\",");
			jsonBuffer.append("\"td_zycp\":\"").append(esp.getMain_pd()).append("\",");
			jsonBuffer.append("\"td_frdb\":\"").append("--").append("\",");
			jsonBuffer.append("\"td_dh\":\"").append(esp.getLink_phone()).append("\",");
			jsonBuffer.append("\"td_cz\":\"").append("--").append("\",");
			jsonBuffer.append("\"td_lxr\":\"").append(esp.getLink_user()).append("\",");
			jsonBuffer.append("\"td_dzyx\":\"").append("--").append("\",");
			jsonBuffer.append("\"td_yb\":\"").append(esp.getPost_code()).append("\",");
			jsonBuffer.append("\"td_dz\":\"").append(esp.getStreet_addr()).append("\",");
			jsonBuffer.append("\"td_wz\":\"").append("--").append("\",");

			if (jxcList != null && jxcList.size() > 0) {
				for (int i = 0; i < jxcList.size(); i++) {
					JxcSellBill jxc = jxcList.get(i);
					jsonBuffer_xl.append("{");
					jsonBuffer_xl.append("\"month\":\"").append(jxc.getMap().get("month") + "月份销量").append("\",");
					jsonBuffer_xl.append("\"month_xl\":\"").append(jxc.getMap().get("sum_xl")).append("\"");
					jsonBuffer_xl.append("},");
				}
			}
		}
		// jsonBuffer.append("\"ajax_status\":\"0\"");
		StringBuffer json = new StringBuffer("{");
		json.append(jsonBuffer);
		json.append("\"xl_list\" : [").append(StringUtils.removeEnd(jsonBuffer_xl.toString(), ",")).append("]");
		json.append("}");
		logger.info("JSON Output : [{}]", json.toString());

		super.render(response, json.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

	public void ajaxGetPIndexNamesStr(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String p_index = (String) dynaBean.get("p_index");
		p_index = GenericValidator.isLong(p_index) ? p_index : "0";

		String rendText = getPIndexName(p_index, " ");
		if (null == rendText) {
			rendText = "0";
		}
		super.render(response, rendText, "text/x-json;charset=UTF-8");
		return;
	}

	/*
	 * 替换特殊字符 \t \r \n 等
	 */
	public String replaceSpecialCharacter(String str, String reg) {
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(str);
		return m.replaceAll("");
	}

	public List<EntpShop> getShopSearchList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String mod_id = (String) dynaBean.get("mod_id");
		Pager pager = (Pager) dynaBean.get("pager");
		String shop_name_like = (String) dynaBean.get("shop_name_like");

		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");

		if (StringUtils.isBlank(mod_id)) {
			String msg = super.getMessage(request, "login.prod.failed.param.lost");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		String p_indexs = "";

		// 没有输入查询条件默认不进行查询
		if (StringUtils.isEmpty(province) && StringUtils.isEmpty(city) && StringUtils.isEmpty(country)
				&& StringUtils.isEmpty(town) && StringUtils.isEmpty(shop_name_like)) {

			return null;
		}

		// 查询结果集
		EntpShop entpShop = new EntpShop();

		if (!StringUtils.isBlank(town)) {
			entpShop.getMap().put("reg_info_not_null", true);
			entpShop.getMap().put("reg_p_index_par", (Long.valueOf(town)));
		} else if (StringUtils.isBlank(town) && !StringUtils.isBlank(country)) {
			entpShop.getMap().put("p_index_par", (Long.valueOf(country)));
		} else if (StringUtils.isBlank(town) && StringUtils.isBlank(country) && !StringUtils.isBlank(city)) {
			entpShop.getMap().put("p_index_par", (Long.valueOf(city)));
		} else if (StringUtils.isBlank(town) && StringUtils.isBlank(country) && StringUtils.isBlank(city)
				&& !StringUtils.isBlank(province)) {
			entpShop.getMap().put("p_index_par", (Long.valueOf(province)));
		} else if (StringUtils.isBlank(town) && StringUtils.isBlank(country) && StringUtils.isBlank(city)
				&& StringUtils.isBlank(province) && StringUtils.isNotBlank(p_indexs)) {
			entpShop.getMap().put("p_indexs_par", p_indexs.split(","));
		}

		if (StringUtils.isNotBlank(shop_name_like)) {
			entpShop.getMap().put("shop_name_like", shop_name_like);
		}

		entpShop.getMap().put("is_konka_shop", true);// 5.5W网点

		Long recordCount = super.getFacade().getEntpShopService().getEntpShopInFindShopCount(entpShop);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		pager.setPageSize(26);
		entpShop.getRow().setFirst(pager.getFirstRow());
		entpShop.getRow().setCount(pager.getPageSize());

		List<EntpShop> entityList = super.getFacade().getEntpShopService().getEntpShopInFindShopPaginatedList(entpShop);
		// 设定查询网点的开拓状态，返回待开拓网点
		setShopDevelopStatus(entityList, null);

		request.getSession().setAttribute("EntpShopSearch_entpShop", entpShop);
		request.getSession().setAttribute("EntpShopSearch_count", recordCount);
		request.getSession().setAttribute("EntpShopSearch_list", entityList);

		request.getSession().setAttribute("EntpShopSearch_ajaxEntpShop", null);

		return entityList;

	}

	/*
	 * 设定查询网点中开拓网点
	 */
	public void setShopDevelopStatus(List<EntpShop> list, String status) {
		// 定义shop_id List
		List<Long> shop_id_List = new ArrayList<Long>();
		// 待开拓网点 <Shop_ID串,开拓状态>
		Map<Long, Long> dev_map = new HashMap<Long, Long>();

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				EntpShop shop = list.get(i);
				shop_id_List.add(shop.getShop_id());
			}

			// 网点开拓信息获取
			KonkaShopDevelop ksd = new KonkaShopDevelop();
			ksd.getMap().put("develop_not_in", status);
			ksd.getMap().put("shop_id_string", StringUtils.join(shop_id_List, ","));
			List<KonkaShopDevelop> li_C = super.getFacade().getKonkaShopDevelopService().getKonkaShopDevelopList(ksd);
			if (li_C != null && li_C.size() > 0) {
				for (int i = 0; i < li_C.size(); i++) {
					KonkaShopDevelop ksd_shop = li_C.get(i);
					dev_map.put(ksd_shop.getShop_id(), ksd_shop.getDevelop_status());
				}
			}
			for (int i = 0; i < list.size(); i++) {
				EntpShop shop = list.get(i);
				shop.getMap().put("shop_develop_status", dev_map.get(shop.getShop_id()));
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void ajaxGetEntpShopJson(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		EntpShop EntpShopSearch_entpShop = (EntpShop) super.getSessionAttribute(request, "EntpShopSearch_entpShop");
		List<EntpShop> entpShopList = null;
		Long allCount = null;
		if (EntpShopSearch_entpShop != null) {
			entpShopList = (List<EntpShop>) super.getSessionAttribute(request, "EntpShopSearch_list");
			allCount = (Long) super.getSessionAttribute(request, "EntpShopSearch_count");
			request.getSession().setAttribute("EntpShopSearch_ajaxEntpShop", EntpShopSearch_entpShop);

		} else {
			String firstRow = (String) dynaBean.get("firstRow");
			String pageSize = (String) dynaBean.get("pageSize");
			EntpShop ajaxEntpShop = (EntpShop) super.getSessionAttribute(request, "EntpShopSearch_ajaxEntpShop");

			// 全网点查找
			allCount = super.getFacade().getEntpShopService().getEntpShopInFindShopCount(ajaxEntpShop);
			ajaxEntpShop.getRow().setFirst(Integer.parseInt(firstRow));
			ajaxEntpShop.getRow().setCount(Integer.parseInt(pageSize));
			entpShopList = super.getFacade().getEntpShopService().getEntpShopInFindShopPaginatedList(ajaxEntpShop);

			// 设定查询网点中待开拓网点，返回待开拓网点
			setShopDevelopStatus(entpShopList, null);

			request.getSession().setAttribute("EntpShopSearch_ajaxEntpShop", ajaxEntpShop);
		}
		request.getSession().setAttribute("EntpShopSearch_entpShop", null);
		// R3用户List
		List<EntpShop> list_A = new ArrayList<EntpShop>();
		// 经销商List
		List<EntpShop> list_B = new ArrayList<EntpShop>();
		// 待开拓网点List
		List<EntpShop> list_C = new ArrayList<EntpShop>();
		// 5.5W其他网点(不含R3用户,经销商,待开拓网点)
		List<EntpShop> list_D = new ArrayList<EntpShop>();

		// 定义shop_id List
		List<Long> shop_id_List = new ArrayList<Long>();

		// 全网点ID串 shop_ids
		String shop_ids = null;

		// R3用户Shop_ID串 list_A_ids
		String list_A_ids = null;
		// 经销商Shop_ID串 list_B_ids
		String list_B_ids = null;
		// 待开拓网点 Shop_ID串list_C_ids
		// String list_C_ids = null;

		if (entpShopList != null && entpShopList.size() > 0) {
			for (int i = 0; i < entpShopList.size(); i++) {
				EntpShop shop = entpShopList.get(i);
				shop_id_List.add(shop.getShop_id());
			}
			shop_ids = StringUtils.join(shop_id_List, ","); // 全网点MMT_Shop_ID串
															// shop_ids
		}
		// 清空List
		shop_id_List.clear();

		if (shop_ids != null) {
			// R3用户获取
			KonkaR3MmtMatch R3Mmt = new KonkaR3MmtMatch();
			R3Mmt.getMap().put("selects_mmt", shop_ids);
			List<KonkaR3MmtMatch> li_A = super.getFacade().getKonkaR3MmtMatchService().getKonkaR3MmtMatchList(R3Mmt);

			if (li_A != null && li_A.size() > 0) {
				for (int i = 0; i < li_A.size(); i++) {
					KonkaR3MmtMatch r3mmt = li_A.get(i);
					shop_id_List.add(r3mmt.getMmt_shop_id());
				}
				list_A_ids = StringUtils.join(shop_id_List, ",");
			}
			// 清空List
			shop_id_List.clear();

			// 经销商获取
			KonkaBranchCategory kbc = new KonkaBranchCategory();
			kbc.setCategory_id(20L);
			kbc.getMap().put("MMT_shop_ids", shop_ids);
			List<KonkaBranchCategory> Li_B = super.getFacade().getKonkaBranchCategoryService()
					.getKonkaBranchCategoryList(kbc);

			if (Li_B != null && Li_B.size() > 0) {
				for (int i = 0; i < Li_B.size(); i++) {
					KonkaBranchCategory kbc_shop = Li_B.get(i);
					shop_id_List.add(kbc_shop.getMmt_shop_id());
				}
				list_B_ids = StringUtils.join(shop_id_List, ",");
			}
			// 清空List
			shop_id_List.clear();

			for (int i = 0; i < entpShopList.size(); i++) {
				EntpShop shop = entpShopList.get(i);
				// is_develop_ready == "1" 为待开拓
				String shop_develop_status = "" + shop.getMap().get("shop_develop_status");
				if ("0".equals(shop_develop_status) || "1".equals(shop_develop_status)) {
					shop.getMap().put("is_R3Shop", "3"); // 待开拓、开拓中网点
					list_C.add(shop);
				} else if (searchMatchId(list_B_ids, shop.getShop_id(), ",")) {
					shop.getMap().put("is_R3Shop", "2"); // 经销商
					list_B.add(shop);
				} else if (searchMatchId(list_A_ids, shop.getShop_id(), ",")) {
					shop.getMap().put("is_R3Shop", "1"); // R3用户
					list_A.add(shop);
				} else {
					shop.getMap().put("is_R3Shop", "0"); // 5.5W其他网点(不含R3用户,经销商,待开拓网点)
					list_D.add(shop);
				}
			}
		}

		// R3用户 个数
		int count_A = list_A.size();
		// 经销商 个数
		int count_B = list_B.size();
		// 开拓网点个数
		int count_C = list_C.size();
		// 5.5W其他网点个数(不含R3用户,经销商,待开拓网点)
		int count_D = list_D.size();

		// 合并
		list_A.addAll(list_B);
		list_A.addAll(list_C);
		list_A.addAll(list_D);

		StringBuffer jsonBuffer = new StringBuffer();
		for (EntpShop es : list_A) {
			jsonBuffer.append("{");
			jsonBuffer.append("\"shop_id\":\"").append(es.getShop_id()).append("\",");
			jsonBuffer.append("\"shop_name\":\"").append(replaceSpecialCharacter(es.getShop_name(), "\t|\r|\n"))
					.append("\",");
			jsonBuffer.append("\"online_qq\":\"").append("null").append("\",");
			jsonBuffer.append("\"is_rural\":\"").append(es.getIs_rural()).append("\",");
			jsonBuffer.append("\"is_otn\":\"").append(es.getIs_otn()).append("\",");
			jsonBuffer.append("\"is_sall\":\"").append(es.getIs_sall()).append("\",");
			jsonBuffer.append("\"is_callb\":\"").append(es.getIs_callb()).append("\",");
			jsonBuffer.append("\"shop_level\":\"").append(es.getShop_level()).append("\",");
			jsonBuffer.append("\"is_maint\":\"").append(es.getIs_maint()).append("\",");
			jsonBuffer.append("\"g_is_audit\":\"").append(es.getG_is_audit()).append("\",");
			jsonBuffer.append("\"g_lat\":\"").append(es.getG_lat()).append("\",");
			jsonBuffer.append("\"g_lng\":\"").append(es.getG_lng()).append("\",");

			// R3网点
			jsonBuffer.append("\"is_R3Shop\":\"").append(es.getMap().get("is_R3Shop")).append("\",");
			jsonBuffer.append("\"shop_develop_status\":\"").append(es.getMap().get("shop_develop_status")).append("\"");

			jsonBuffer.append("},");
		}

		String listStr = StringUtils.removeEnd(jsonBuffer.toString(), ",");
		StringBuffer json = new StringBuffer("{");
		json.append("\"list\" : [").append(listStr).append("],");
		json.append("\"count\":\"").append(allCount).append("\",");
		json.append("\"count_A\":\"").append(count_A).append("\",");
		json.append("\"count_B\":\"").append(count_B).append("\",");
		json.append("\"count_C\":\"").append(count_C).append("\",");
		json.append("\"count_D\":\"").append(count_D).append("\",");
		json.append("\"shop_list_ids\":\"").append(shop_ids).append("\"");
		json.append("}");
		logger.info("JSON Output : [{}]", json.toString());

		super.render(response, json.toString(), "text/x-json;charset=UTF-8");
		return;
	}

	/*
	 * 查找ID串是否有匹配的ID
	 */
	public boolean searchMatchId(String source, String id, String regex) {
		if (source == null)
			return false;
		String arr[] = source.split(regex);
		for (int i = 0; i < arr.length; i++) {
			if (id.equals(arr[i]))
				return true;
		}
		return false;
	}

	/*
	 * 查找ID串是否有匹配的ID
	 */
	public boolean searchMatchId(String source, Long id, String regex) {
		if (source == null)
			return false;
		String arr[] = source.split(regex);
		for (int i = 0; i < arr.length; i++) {
			if (id.toString().equals(arr[i]))
				return true;
		}
		return false;
	}

	public void ajaxGetEntpShopInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// DynaBean dynaBean = (DynaBean) form;
		// String shop_id = (String) dynaBean.get("shop_id");

		// 判断商铺 ID
		StringBuffer sb = new StringBuffer("{'result':'false'}");
		super.render(response, sb.toString(), "text/x-json;charset=UTF-8");
		return;
	}

	/**
	 * p_index区域所有黑电经销网点 、R3网点 、代理商、 代理商网点
	 * 
	 * @param request
	 * @param p_index
	 * @return
	 * @throws Exception
	 */
	public Map<String, Double> getKonkaShopCount(HttpServletRequest request, String p_index) throws Exception {

		// 当前区域所有黑电经销网点
		MmtEntpShop ks = new MmtEntpShop();
		ks.getMap().put("p_index", p_index);
		Long count_all = super.getFacade().getMmtEntpShopService().getMmtEntpShopCount(ks);

		BaseProvinceList child_areas = new BaseProvinceList();
		child_areas.setP_index(Long.valueOf(p_index));
		child_areas.setP_level(new Short("2"));
		String pro_city = "110000,120000,310000,500000,810000,820000";
		if (pro_city.indexOf(p_index) != -1)
			child_areas.setP_level(new Short("1"));
		List<BaseProvinceList> list = super.getFacade().getBaseProvinceListService()
				.getBaseProvinceListChildrenList(child_areas);
		// 市
		String p_name_String = "";
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				p_name_String = p_name_String + "'" + list.get(i).getP_name().substring(0, 2) + "',";
			}
			p_name_String = StringUtils.removeEnd(p_name_String, ",");
		}

		// 当前区域R3网点---已匹配的
		KonkaR3Shop r3s = new KonkaR3Shop();
		r3s.getMap().put("p_name_String", p_name_String);
		r3s.setIs_match(1L);
		Long count_r3 = getFacade().getKonkaR3ShopService().getKonkaR3ShopCount(r3s);

		// 当前区域代理商
		KonkaR3Shop r3s2 = new KonkaR3Shop();
		r3s2.getMap().put("is_agents", "true"); // 代理商
		r3s2.getMap().put("p_name_String", p_name_String);
		Long count_dls = getFacade().getKonkaR3ShopService().getKonkaR3ShopCount(r3s2);

		// 当前区域代理商网点
		KonkaBranchCategory kbc = new KonkaBranchCategory();
		kbc.setCategory_id(20L);
		Long count_jxs = super.getFacade().getKonkaBranchCategoryService().getKonkaBranchCategoryCount(kbc);

		Map<String, Double> map = new HashMap<String, Double>();
		map.put("count_all", Double.valueOf(count_all));
		map.put("count_r3", Double.valueOf(count_r3));
		map.put("count_dls", Double.valueOf(count_dls));
		map.put("count_jxs", Double.valueOf(count_jxs));

		return map;
	}

	/**
	 * 2011年10月后新开拓网点、销售统计
	 * 
	 * @param request
	 * @param p_index
	 * @param ymd
	 * @return
	 * @throws Exception
	 * @deprecated by wanghao 20120606
	 */
	public Map<String, Double> getKonkaShopDevelop(HttpServletRequest request, String p_index, String ymd)
			throws Exception {

		// 网点开拓----直接开拓 ----->R3用户
		// ----->代理商网点
		KonkaShopDevelop ksd = new KonkaShopDevelop();
		ksd.setDevelop_status(2L);
		ksd.getMap().put("shop_developed_date", ymd);
		ksd.getMap().put("p_index", p_index);
		List<KonkaShopDevelop> ksdList = super.getFacade().getKonkaShopDevelopService().getKonkaShopDevelopList(ksd);

		String shop_id_String = "";
		List<Long> r3List = new ArrayList<Long>();
		List<Long> jxsList = new ArrayList<Long>();
		for (int i = 0; i < ksdList.size(); i++) {
			KonkaShopDevelop k = ksdList.get(i);
			if (k.getR3_id() != null)
				r3List.add(k.getR3_id());
			else
				jxsList.add(k.getJxs_id());
			shop_id_String = shop_id_String + k.getShop_id() + ",";
		}
		shop_id_String = StringUtils.removeEnd(shop_id_String, ",");

		// 代理商

		// 区域R3用户、代理商网点销售统计
		JxcSellBill jxcsb = new JxcSellBill();
		List<JxcSellBill> jxcList = null;
		if (!"".equals(shop_id_String)) {
			jxcsb.getMap().put("shop_id_String", shop_id_String);
			jxcsb.getMap().put("p_index_in", p_index);
			jxcsb.getMap().put("sell_date_ge", ymd);
			jxcList = super.getFacade().getJxcSellBillService().getJxcSellBillForArea(jxcsb);
		}

		// R3用户销量
		Double r3_xl = 0.00;
		// R3用户销售额
		Double r3_xe = 0.00;

		// 代理商网点销量
		Double dls_xl = 0.00;
		// 代理商网点销售额
		Double dls_xe = 0.00;

		if (jxcList != null) {
			for (int i = 0; i < jxcList.size(); i++) {
				JxcSellBill jxc = jxcList.get(i);

				Double sum_xl = Double.valueOf(jxc.getMap().get("sum_xl").toString());
				Double sum_xe = Double.valueOf(jxc.getMap().get("sum_xe").toString());
				// 分类处理
				if ("1".equals("" + jxc.getMap().get("is_r3"))) {
					r3_xl += sum_xl;
					r3_xe += sum_xe;
				} else {
					dls_xl += sum_xl;
					dls_xe += sum_xe;
				}
			}
		}

		Map<String, Double> map = new HashMap<String, Double>();
		map.put("develop_count", Double.valueOf(ksdList.size()));
		map.put("develop_r3", Double.valueOf(r3List.size()));
		map.put("r3_xl", r3_xl);
		map.put("r3_xe", r3_xe);
		// 代理商
		map.put("develop_dls", Double.valueOf(10));
		// 代理商网点，即经销商
		map.put("develop_jxs", Double.valueOf(jxsList.size()));
		map.put("dls_xl", dls_xl);
		map.put("dls_xe", dls_xe);

		return map;
	}
}
