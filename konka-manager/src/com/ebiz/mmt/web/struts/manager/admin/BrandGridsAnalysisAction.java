package com.ebiz.mmt.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseBrandInfo;
import com.ebiz.mmt.domain.BasePdType;
import com.ebiz.mmt.domain.BaseProvinceList;
import com.ebiz.mmt.domain.EntpShop;
import com.ebiz.mmt.domain.GeoCn;
import com.ebiz.mmt.domain.MdasBuyerInfo;
import com.ebiz.mmt.domain.MdasModPermission;
import com.ebiz.mmt.domain.MdasRegions;
import com.ebiz.mmt.domain.MdasRegionsProvince;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.StdEntpMain;
import com.ebiz.mmt.domain.chart.BaseChart;
import com.ebiz.mmt.domain.chart.MSColumn3DChart;
import com.ebiz.mmt.domain.chart.MsLineChart;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Cheng,Bing
 * @version 2011.10.10
 */
public class BrandGridsAnalysisAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		String msg = "";
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		
		///
		dynaBean.set("mdas_mod_id","10501");
		////
		Pager pager = (Pager) dynaBean.get("pager");
		String mdas_mod_id = (String) dynaBean.get("mdas_mod_id");
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String own_sys = (String) dynaBean.get("own_sys");
		String brand_id = (String) dynaBean.get("brand_id");
		String sell_price_min = (String) dynaBean.get("sell_price_min");
		String sell_price_max = (String) dynaBean.get("sell_price_max");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");

		PeProdUser mdas_userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		////////////////////////////
		//mdas_userInfo.setId(Long.valueOf("104362"));
		mdas_userInfo.getMap().put("pd_type", Long.valueOf("5"));
        ////////////////////////////
		
		if (StringUtils.isBlank(province) && StringUtils.isBlank(own_sys) && StringUtils.isBlank(brand_id)
				&& StringUtils.isBlank(sell_price_min) && StringUtils.isBlank(sell_price_max)
				&& StringUtils.isBlank(year) && StringUtils.isBlank(month)) {
			return mapping.findForward("list");
		}
		
		Long pd_type = (Long) mdas_userInfo.getMap().get("pd_type");

		MdasModPermission mdasModPermission = new MdasModPermission();
		mdasModPermission.setUser_id(Long.valueOf("104362"));
		mdasModPermission.setMdas_mod_id(Long.valueOf(mdas_mod_id));
		mdasModPermission.getMap().put("p_index_is_not_null", "true");
		mdasModPermission.getMap().put("p_name", "true");
		List<MdasModPermission> mdasModPermList_p_index = super.getFacade().getMdasModPermissionService()
				.getMdasModPermissionWithPNameList(mdasModPermission);

		mdasModPermission.getMap().clear();
		mdasModPermission.getMap().put("brand_id_is_not_null", "true");
		List<MdasModPermission> mdasModPermList_brand = super.getFacade().getMdasModPermissionService()
				.getMdasModPermissionList(mdasModPermission);

		mdasModPermission.getMap().clear();
		mdasModPermission.getMap().put("view_date_start_is_not_null", "true");
		mdasModPermission.getMap().put("view_date_end_is_not_null", "true");
		List<MdasModPermission> mdasModPermList_viewDate = super.getFacade().getMdasModPermissionService()
				.getMdasModPermissionList(mdasModPermission);

		if (null == mdasModPermList_p_index || mdasModPermList_p_index.size() == 0) {
			msg += super.getMessage(request, "analytics.modpermission.notconfig.pindex");
		}
		if (null == mdasModPermList_brand || mdasModPermList_brand.size() == 0) {
			msg += super.getMessage(request, "analytics.modpermission.notconfig.brand");
		}
		if (null == mdasModPermList_viewDate || mdasModPermList_viewDate.size() == 0) {
			msg += super.getMessage(request, "analytics.modpermission.notconfig.viewdate");
		}

		request.setAttribute("mdasModPermList_p_index", mdasModPermList_p_index);
		request.setAttribute("msg", msg);
		if (StringUtils.isBlank(msg)) {
			EntpShop entpShop = new EntpShop();

			Long brand_id_s[] = null;
			if (StringUtils.isBlank(brand_id)) {
				brand_id_s = new Long[mdasModPermList_brand.size()];
				int count = 0;
				for (MdasModPermission m_brand_id : mdasModPermList_brand) {
					brand_id_s[count++] = m_brand_id.getBrand_id();
				}
				entpShop.getMap().put("brand_id_in", brand_id_s);
			} else {
				entpShop.getMap().put("brand_id_in", new Long[] { Long.valueOf(brand_id) });
			}

			// 所在区域
			String p_index = "";
			String szom = null;
			if (StringUtils.isNotBlank(country)) {
				p_index = country;
				szom = "8";
			} else if (StringUtils.isNotBlank(city)) {
				p_index = city;
				szom = "7";
			} else if (StringUtils.isNotBlank(province)) {
				p_index = province;
				szom = "6";
			}

			Long p_index_s[] = null;
			if (StringUtils.isBlank(p_index)) {// 显示所有有权限省份的网点
				p_index_s = new Long[mdasModPermList_p_index.size()];
				int count = 0;
				for (MdasModPermission m_p_index : mdasModPermList_p_index) {
					p_index_s[count++] = m_p_index.getP_index();
				}
				entpShop.getMap().put("p_index_in", p_index_s);
			} else {
				entpShop.getMap().put("p_index_par", p_index);
				GeoCn cecn = new GeoCn();
				cecn.setP_index(Long.valueOf(p_index));
				cecn = super.getFacade().getGeoCnService().getGeoCn(cecn);
				dynaBean.set("lat", cecn.getLat());
				dynaBean.set("lng", cecn.getLng());
				dynaBean.set("szom", szom);
			}

			Date viewDateStart = null;
			Date viewDateEnd = null;
			if (null != mdasModPermList_viewDate && mdasModPermList_viewDate.size() > 0) {
				viewDateStart = mdasModPermList_viewDate.get(0).getView_date_start();
				viewDateEnd = mdasModPermList_viewDate.get(0).getView_date_end();
			}
			entpShop.getMap().put("sell_date_ge", viewDateStart);
			entpShop.getMap().put("sell_date_le", viewDateEnd);

			// 设置查询时间
			if (StringUtils.isNotBlank(year)) {
				if (StringUtils.isNotBlank(month)) {
					int iMonth = Integer.parseInt(month);
					entpShop.getMap().put("sell_date_selected_ge", year + "-" + month + "-01");
					if (iMonth == 12) {
						entpShop.getMap().put("sell_date_selected_1e", year + "-" + month + "-31");
					} else {
						entpShop.getMap().put("sell_date_lt", year + "-" + (iMonth + 1) + "-01");
					}
				} else {
					entpShop.getMap().put("sell_date_selected_ge", year + "-01-01");
					entpShop.getMap().put("sell_date_selected_le", year + "-12-31");
				}
			}

			entpShop.getMap().put("sell_price_total_ge", sell_price_min);
			entpShop.getMap().put("sell_price_total_le", sell_price_max);

			entpShop.getMap().put("own_sys", own_sys);
			entpShop.getMap().put("par_pd_type", pd_type);

			if (StringUtils.isNotBlank(own_sys) || StringUtils.isNotBlank(year) || StringUtils.isNotBlank(month)) {
				entpShop.getMap().put("exists_date", true);
			}

			List<EntpShop> entpShopList = new ArrayList<EntpShop>();
			Long regCount = getFacade().getEntpShopService().getEntpShopCountForMapUpdated(entpShop);
			if (regCount > Constants.POINTS_LIMIT) {
				dynaBean.set("points_limit", "true");
				pager.setPageSize(20);
				pager.init(regCount, pager.getPageSize(), pager.getRequestPage());
				entpShop.getRow().setCount(pager.getRowCount());
				entpShop.getRow().setFirst(pager.getFirstRow());

				entpShopList = super.getFacade().getEntpShopService().getEntpShopPaginatedListForMapUpdated(entpShop);
			} else {
				entpShopList = super.getFacade().getEntpShopService().getEntpShopListForMapUpdated(entpShop);
			}

			request.setAttribute("entpShopList", entpShopList);
		}

		return mapping.findForward("list");
	}

	public ActionForward listBrand(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mdas_mod_id = (String) dynaBean.get("mdas_mod_id");
		String brand_name_like = (String) dynaBean.get("brand_name_like");
		Pager pager = (Pager) dynaBean.get("pager");

		PeProdUser mdas_userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		Long pd_type = (Long) mdas_userInfo.getMap().get("pd_type");

		MdasModPermission mdasModPermission = new MdasModPermission();
		mdasModPermission.setUser_id(Long.valueOf("104362"));
		mdasModPermission.setMdas_mod_id(Long.valueOf(mdas_mod_id));
		mdasModPermission.getMap().put("pd_type", pd_type);
		mdasModPermission.getMap().put("brand_id_is_not_null", "true");
		if (StringUtils.isNotBlank(brand_name_like)) {
			mdasModPermission.getMap().put("brand_name_like", brand_name_like);
		}

		Long recordCount = getFacade().getMdasModPermissionService().getMdasModPermissionWithBrandNameCount(
				mdasModPermission);

		pager.init(recordCount, new Integer(30), pager.getRequestPage());// pager.getPageSize()
		mdasModPermission.getRow().setFirst(pager.getFirstRow());
		mdasModPermission.getRow().setCount(pager.getRowCount());

		List<MdasModPermission> mdasModPermList_brand = super.getFacade().getMdasModPermissionService()
				.getMdasModPermissionWithBrandNamePaginatedList(mdasModPermission);

		request.setAttribute("mdasModPermList_brand", mdasModPermList_brand);

		return new ActionForward("/../manager/admin/BrandGridsAnalysis/list_brand.jsp");
	}

	public ActionForward toTable(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mdas_mod_id = (String) dynaBean.get("mdas_mod_id");
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String own_sys = (String) dynaBean.get("own_sys");
		String brand_id = (String) dynaBean.get("brand_id");
		String sell_price_min = (String) dynaBean.get("sell_price_min");
		String sell_price_max = (String) dynaBean.get("sell_price_max");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		int table_type = 0;

		PeProdUser mdas_userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		Long pd_type = (Long) mdas_userInfo.getMap().get("pd_type");

		MdasModPermission mdasModPermission = new MdasModPermission();
		mdasModPermission.setUser_id(Long.valueOf("104362"));
		mdasModPermission.setMdas_mod_id(Long.valueOf(mdas_mod_id));
		mdasModPermission.getMap().put("p_index_is_not_null", "true");
		mdasModPermission.getMap().put("p_name", "true");
		List<MdasModPermission> mdasModPermList_p_index = super.getFacade().getMdasModPermissionService()
				.getMdasModPermissionWithPNameList(mdasModPermission);

		mdasModPermission.getMap().clear();
		mdasModPermission.getMap().put("pd_type", pd_type);
		mdasModPermission.getMap().put("brand_id_is_not_null", "true");
		List<MdasModPermission> mdasModPermList_brand = super.getFacade().getMdasModPermissionService()
				.getMdasModPermissionWithBrandNameList(mdasModPermission);

		mdasModPermission.getMap().clear();
		mdasModPermission.getMap().put("view_date_start_is_not_null", "true");
		mdasModPermission.getMap().put("view_date_end_is_not_null", "true");
		List<MdasModPermission> mdasModPermList_viewDate = super.getFacade().getMdasModPermissionService()
				.getMdasModPermissionList(mdasModPermission);

		request.setAttribute("mdasModPermList_p_index", mdasModPermList_p_index);

		if (StringUtils.isNotBlank(brand_id)) {// 查询品牌
			mdasModPermission = new MdasModPermission();
			mdasModPermission.setBrand_id(Long.valueOf(brand_id));
			mdasModPermission = super.getFacade().getMdasModPermissionService().getMdasModPermissionWithBrandNameList(
					mdasModPermission).get(0);

			mdasModPermList_brand.clear();
			mdasModPermList_brand.add(mdasModPermission);
		}

		Date viewDateStart = null;
		Date viewDateEnd = null;
		if (null != mdasModPermList_viewDate && mdasModPermList_viewDate.size() > 0) {
			viewDateStart = mdasModPermList_viewDate.get(0).getView_date_start();
			viewDateEnd = mdasModPermList_viewDate.get(0).getView_date_end();
		}

		String sell_date_selected_ge = "";
		String sell_date_selected_le = "";
		String sell_date_lt = "";
		if (StringUtils.isNotBlank(year)) {// 设置查询时间
			if (StringUtils.isNotBlank(month)) {
				int iMonth = Integer.parseInt(month);
				sell_date_selected_ge = year + "-" + month + "-01";
				if (iMonth == 12) {
					sell_date_selected_le = year + "-" + month + "-31";
				} else {
					sell_date_lt = year + "-" + (iMonth + 1) + "-01";
				}
			} else {
				sell_date_selected_ge = year + "-01-01";
				sell_date_selected_le = year + "-12-31";
			}
		}

		List<Long> provincePIndexList = new ArrayList<Long>();// 用来保存所有有权限的省份或者市县p_index

		if (StringUtils.isBlank(province)) {// 默认按照大区省份格式显示
			Long p_index_s[] = null;
			p_index_s = new Long[mdasModPermList_p_index.size()];
			int count = 0;
			for (MdasModPermission m_p_index : mdasModPermList_p_index) {
				p_index_s[count++] = m_p_index.getP_index();
			}

			MdasRegions mdasRegions = new MdasRegions();
			mdasRegions.setIs_del(0);
			List<MdasRegions> mdasRegionList = getFacade().getMdasRegionsService().getMdasRegionsList(mdasRegions);
			if (null != mdasRegionList && mdasRegionList.size() > 0) {
				for (MdasRegions region : mdasRegionList) {
					MdasRegionsProvince mdasRegionsProvince = new MdasRegionsProvince();
					mdasRegionsProvince.setRegions_id(region.getId());
					mdasRegionsProvince.getMap().put("p_index_in", p_index_s);
					List<MdasRegionsProvince> mdasRegionsProvinceList = getFacade().getMdasRegionsProvinceService()
							.getMdasRegionsProvinceList(mdasRegionsProvince);
					region.setRgProvinceList(mdasRegionsProvinceList);

					if (null != mdasRegionsProvinceList && mdasRegionsProvinceList.size() > 0) {
						for (MdasRegionsProvince _regionsProvince : mdasRegionsProvinceList) {
							provincePIndexList.add(_regionsProvince.getP_index());
						}
					}
				}
			}

			request.setAttribute("mdasRegionList", mdasRegionList);

			if (null != mdasModPermList_brand && mdasModPermList_brand.size() > 0) {// 某个大类下的已授权的品牌
				for (MdasModPermission _mdasModPermission : mdasModPermList_brand) {
					List<Long> sumList = new ArrayList<Long>();
					for (Long p_index : provincePIndexList) {
						MdasBuyerInfo mdasBuyerInfo = new MdasBuyerInfo();
						mdasBuyerInfo.setBrand_id(_mdasModPermission.getBrand_id());
						if (StringUtils.isNotBlank(own_sys)) {
							mdasBuyerInfo.setOwn_sys(Integer.valueOf(own_sys));
						}
						mdasBuyerInfo.getMap().put("par_p_index", p_index);
						mdasBuyerInfo.getMap().put("sell_price_total_ge", sell_price_min);
						mdasBuyerInfo.getMap().put("sell_price_total_le", sell_price_max);
						mdasBuyerInfo.getMap().put("sell_date_ge", viewDateStart);
						mdasBuyerInfo.getMap().put("sell_date_le", viewDateEnd);
						mdasBuyerInfo.getMap().put("sell_date_selected_ge", sell_date_selected_ge);
						mdasBuyerInfo.getMap().put("sell_date_selected_le", sell_date_selected_le);
						mdasBuyerInfo.getMap().put("sell_date_lt", sell_date_lt);

						for (int j = 0; j < 4; j++) {// 分四个统计段分别统计
							mdasBuyerInfo.getMap().put("salePriceSection_" + (j + 1), "true");
							Long sum = getFacade().getMdasBuyerInfoService()
									.getEntpShopStatisticForTable(mdasBuyerInfo);
							sumList.add(sum);

							mdasBuyerInfo.getMap().put("salePriceSection_" + (j + 1), null);
						}
					}
					_mdasModPermission.setSumList(sumList);
				}
			}
		} else {// 选择地区，按照具体省市县格式显示
			table_type = 1;

			String par_p_index = "";
			if (StringUtils.isNotBlank(city)) {// 市县
				par_p_index = city;
			} else if (StringUtils.isNotBlank(province)) {// 省市
				par_p_index = province;
			}

			BaseProvinceList baseProvinceList = new BaseProvinceList();
			baseProvinceList.setPar_index(Long.valueOf(par_p_index));
			baseProvinceList.setDel_mark(new Short("0"));
			List<BaseProvinceList> bpList = super.getFacade().getBaseProvinceListService().getBaseProvinceListList(
					baseProvinceList);
			if (null != bpList && bpList.size() > 0) {
				for (BaseProvinceList bp : bpList) {
					provincePIndexList.add(bp.getP_index());
				}
			}

			for (MdasModPermission _mdasModPermission : mdasModPermList_brand) {
				List<Long> sumList = new ArrayList<Long>();
				for (BaseProvinceList baseProvince : bpList) {
					MdasBuyerInfo mdasBuyerInfo = new MdasBuyerInfo();
					mdasBuyerInfo.setBrand_id(_mdasModPermission.getBrand_id());
					if (StringUtils.isNotBlank(own_sys)) {
						mdasBuyerInfo.setOwn_sys(Integer.valueOf(own_sys));
					}
					mdasBuyerInfo.getMap().put("par_p_index", baseProvince.getP_index());
					mdasBuyerInfo.getMap().put("sell_price_total_ge", sell_price_min);
					mdasBuyerInfo.getMap().put("sell_price_total_le", sell_price_max);
					mdasBuyerInfo.getMap().put("sell_date_ge", viewDateStart);
					mdasBuyerInfo.getMap().put("sell_date_le", viewDateEnd);
					mdasBuyerInfo.getMap().put("sell_date_selected_ge", sell_date_selected_ge);
					mdasBuyerInfo.getMap().put("sell_date_selected_le", sell_date_selected_le);
					mdasBuyerInfo.getMap().put("sell_date_lt", sell_date_lt);

					for (int j = 0; j < 4; j++) {// 分四个统计段分别统计
						mdasBuyerInfo.getMap().put("salePriceSection_" + (j + 1), "true");
						Long sum = getFacade().getMdasBuyerInfoService().getEntpShopStatisticForTable(mdasBuyerInfo);
						sumList.add(sum);

						mdasBuyerInfo.getMap().put("salePriceSection_" + (j + 1), null);
					}
				}
				_mdasModPermission.setSumList(sumList);
			}

			request.setAttribute("bpList", bpList);
		}

		/* 表格最后一行的【网点数合计】begin */
		List<Long> entpShopSumList = new ArrayList<Long>();

		Long brand_id_s[] = null;
		brand_id_s = new Long[mdasModPermList_brand.size()];
		int count = 0;
		for (MdasModPermission m_brand_id : mdasModPermList_brand) {
			brand_id_s[count++] = m_brand_id.getBrand_id();
		}

		for (Long p_index : provincePIndexList) {
			MdasBuyerInfo mdasBuyerInfo = new MdasBuyerInfo();
			if (StringUtils.isNotBlank(own_sys)) {
				mdasBuyerInfo.setOwn_sys(Integer.valueOf(own_sys));
			}
			mdasBuyerInfo.getMap().put("entp_shop_sum", "true");
			mdasBuyerInfo.getMap().put("brand_id_in", brand_id_s);
			mdasBuyerInfo.getMap().put("par_p_index", p_index);
			mdasBuyerInfo.getMap().put("sell_price_total_ge", sell_price_min);
			mdasBuyerInfo.getMap().put("sell_price_total_le", sell_price_max);
			mdasBuyerInfo.getMap().put("sell_date_ge", viewDateStart);
			mdasBuyerInfo.getMap().put("sell_date_le", viewDateEnd);
			mdasBuyerInfo.getMap().put("sell_date_selected_ge", sell_date_selected_ge);
			mdasBuyerInfo.getMap().put("sell_date_selected_le", sell_date_selected_le);
			mdasBuyerInfo.getMap().put("sell_date_lt", sell_date_lt);

			Long per_prov_sum = super.getFacade().getMdasBuyerInfoService().getEntpShopStatisticForTable(mdasBuyerInfo);
			entpShopSumList.add(per_prov_sum);
		}
		request.setAttribute("entpShopSumList", entpShopSumList);
		/* 表格最后一行的【网点数合计】end */

		request.setAttribute("table_type", table_type);
		request.setAttribute("mdasModPermList_brand", mdasModPermList_brand);

		return new ActionForward("/../manager/admin/BrandGridsAnalysis/toTable.jsp");
	}

	@SuppressWarnings("unchecked")
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String shop_id = (String) dynaBean.get("shop_id");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String mdas_mod_id = (String) dynaBean.get("mdas_mod_id");

		if (!GenericValidator.isLong(shop_id)) {
			super.renderText(response, "{'msg':'shop_id is error'}");
			return null;
		}

		// 区域p_index
		int p_index = 0;
		EntpShop entpShop = new EntpShop();
		entpShop.setShop_id(Long.valueOf(shop_id));
		entpShop = getFacade().getEntpShopService().getEntpShop(entpShop);
		if (null != entpShop) {
			p_index = entpShop.getP_index();
			request.setAttribute("shop_name", entpShop.getShop_name());
		}
		if (p_index != 0) {
			request.setAttribute("p_name", getPIndexName("" + p_index, ""));
		}

		HttpSession session = request.getSession(false);
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (ui != null) {
			BasePdType pdType = new BasePdType();
			pdType.setIs_model((short) 1);
			pdType.setPd_type((Long) ui.getMap().get("pd_type"));
			pdType = getFacade().getBasePdTypeService().getBasePdType(pdType);
			if (null != pdType) {
				request.setAttribute("pd_name", pdType.getPd_name());
			}

			MdasModPermission modPermission = new MdasModPermission();
			modPermission.setUser_id(ui.getId());
			if (GenericValidator.isLong(mdas_mod_id)) {
				modPermission.setMdas_mod_id(Long.valueOf(mdas_mod_id));
			}

			// 起始时间
			modPermission.getMap().put("view_date_start_is_not_null", true);
			modPermission.getMap().put("view_date_end_is_not_null", true);
			List<MdasModPermission> mPermissionList = getFacade().getMdasModPermissionService()
					.getMdasModPermissionList(modPermission);

			// 授权时间
			Date viewDateStart = null;
			Date viewDateEnd = null;
			if (null != mPermissionList && mPermissionList.size() > 0) {
				viewDateStart = mPermissionList.get(0).getView_date_start();
				viewDateEnd = mPermissionList.get(0).getView_date_end();
			}

			MdasBuyerInfo mdasBuyerInfo = new MdasBuyerInfo();
			mdasBuyerInfo.getMap().put("sell_date_ge", viewDateStart);
			mdasBuyerInfo.getMap().put("sell_date_le", viewDateEnd);
			mdasBuyerInfo.setShop_id(Long.valueOf(shop_id));

			if (StringUtils.isNotBlank(month)) {
				if (StringUtils.isNotBlank(year)) {
					int iMonth = Integer.parseInt(month);
					mdasBuyerInfo.getMap().put("sell_date_selected_ge", year + "-" + month + "-01");
					if (iMonth == 12) {
						mdasBuyerInfo.getMap().put("sell_date_selected_le", year + "-" + month + "-31");
					} else {
						mdasBuyerInfo.getMap().put("sell_date_lt", year + "-" + (iMonth + 1) + "-01");
					}

					// 当月的销售额
					List<MdasBuyerInfo> mBuyerInfoList = getFacade().getMdasBuyerInfoService()
							.getMdasBuyerInfoListForMsg(mdasBuyerInfo);
					if (null != mBuyerInfoList && mBuyerInfoList.size() == 1) {
						request.setAttribute("mdasBuyerInfoformonth", mBuyerInfoList.get(0));

						// 销售排行
						mdasBuyerInfo.setShop_id(null);
						mdasBuyerInfo.getMap().put("xs_amount_gt", mBuyerInfoList.get(0).getMap().get("xs_amount"));
						if (0 != p_index) {
							mdasBuyerInfo.getMap().put("p_index_par", p_index);
						}
						mBuyerInfoList = getFacade().getMdasBuyerInfoService()
								.getMdasBuyerInfoListForMsg(mdasBuyerInfo);

						if (null != mBuyerInfoList) {
							request.setAttribute("rank", mBuyerInfoList.size() + 1);
						} else {
							request.setAttribute("rank", 1);
						}
					}

					// 上月的销售额
					mdasBuyerInfo.setShop_id(Long.valueOf(shop_id));
					mdasBuyerInfo.getMap().clear();
					int iYear = Integer.parseInt(year);
					if (iMonth == 1) {
						mdasBuyerInfo.getMap().put("sell_date_selected_ge", "" + (iYear - 1) + "-12-01");
						mdasBuyerInfo.getMap().put("sell_date_lt", year + "-01-01");
					} else {
						mdasBuyerInfo.getMap().put("sell_date_selected_ge", year + "-" + (iMonth - 1) + "-01");
						mdasBuyerInfo.getMap().put("sell_date_lt", year + "-" + month + "-01");
					}

					mBuyerInfoList = getFacade().getMdasBuyerInfoService().getMdasBuyerInfoListForMsg(mdasBuyerInfo);
					if (null != mBuyerInfoList && mBuyerInfoList.size() == 1) {
						request.setAttribute("mdasBuyerInfoformonthbefore", mBuyerInfoList.get(0));
					}
				}
			} else {
				if (StringUtils.isNotBlank(year)) {
					mdasBuyerInfo.getMap().put("sell_date_selected_ge", year + "-01-01");
					mdasBuyerInfo.getMap().put("sell_date_selected_le", year + "-12-31");

					// 当年的销售额
					List<MdasBuyerInfo> mBuyerInfoList = getFacade().getMdasBuyerInfoService()
							.getMdasBuyerInfoListForMsg(mdasBuyerInfo);
					if (null != mBuyerInfoList && mBuyerInfoList.size() == 1) {
						request.setAttribute("mdasBuyerInfoforyear", mBuyerInfoList.get(0));

						// 销售排行
						mdasBuyerInfo.setShop_id(null);
						mdasBuyerInfo.getMap().put("xs_amount_gt", mBuyerInfoList.get(0).getMap().get("xs_amount"));
						if (0 != p_index) {
							mdasBuyerInfo.getMap().put("p_index_par", p_index);
						}
						mBuyerInfoList = getFacade().getMdasBuyerInfoService()
								.getMdasBuyerInfoListForMsg(mdasBuyerInfo);

						if (null != mBuyerInfoList) {
							request.setAttribute("rank", mBuyerInfoList.size() + 1);
						} else {
							request.setAttribute("rank", 1);
						}
					}

					// 去年的销售额
					mdasBuyerInfo.setShop_id(Long.valueOf(shop_id));
					mdasBuyerInfo.getMap().clear();
					int iYear = Integer.parseInt(year);
					mdasBuyerInfo.getMap().put("sell_date_selected_ge", "" + (iYear - 1) + "-01-01");
					mdasBuyerInfo.getMap().put("sell_date_selected_le", "" + (iYear - 1) + "-12-31");
					mBuyerInfoList = getFacade().getMdasBuyerInfoService().getMdasBuyerInfoListForMsg(mdasBuyerInfo);
					if (null != mBuyerInfoList && mBuyerInfoList.size() == 1) {
						request.setAttribute("mdasBuyerInfoforyearbefore", mBuyerInfoList.get(0));
					}

				}
			}
		}

		Map<String, Object> modelAmount = chartForBrand(mapping, form, request, response);
		Map<String, Object> modelCount = chartForBrandCount(mapping, form, request, response);

		List<BaseChart> baseChartListForAmount = (List<BaseChart>) modelAmount.get("baseChartList");
		double sumForAmount = 0;
		if (null != baseChartListForAmount && baseChartListForAmount.size() > 0) {
			for (BaseChart baseChart : baseChartListForAmount) {
				sumForAmount += Double.parseDouble(baseChart.getValue());
			}
		}

		List<BaseChart> baseChartListForCount = (List<BaseChart>) modelCount.get("baseChartList");
		int sumForCount = 0;
		if (null != baseChartListForCount && baseChartListForCount.size() > 0) {
			for (BaseChart baseChart : baseChartListForCount) {
				sumForCount += Integer.parseInt(baseChart.getValue());
			}
		}

		StdEntpMain entity = new StdEntpMain();
		entity.setShop_id(new Long(shop_id));
		List<StdEntpMain> entityList = super.getFacade().getStdEntpMainService().getStdEntpMainList(entity);
		if (null != entityList && entityList.size() > 0) {
			request.setAttribute("entity", entityList.get(0));
			if (null != entityList.get(0).getP_index()) {
				BaseProvinceList base = new BaseProvinceList();
				base.setP_index(new Long(entityList.get(0).getP_index()));
				base = super.getFacade().getBaseProvinceListService().getBaseProvinceList(base);
				request.setAttribute("p_name", base.getP_name());
			}

		}

		request.setAttribute("sumForAmount", sumForAmount);
		request.setAttribute("sumForCount", sumForCount);
		request.setAttribute("modelAmount", modelAmount);
		request.setAttribute("modelCount", modelCount);
		this.viewInfo(mapping, form, request, response);

		return mapping.findForward("view");
	}

	@SuppressWarnings("unchecked")
	public ActionForward viewInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String shop_id = (String) dynaBean.get("shop_id");
		String year = (String) dynaBean.get("year_son");
		String month = (String) dynaBean.get("month_son");
		String mdas_mod_id = (String) dynaBean.get("mdas_mod_id");

		if (!GenericValidator.isLong(shop_id)) {
			super.renderText(response, "{'msg':'shop_id is error'}");
			return null;
		}

		// 区域p_index
		int p_index = 0;
		EntpShop entpShop = new EntpShop();
		entpShop.setShop_id(Long.valueOf(shop_id));
		entpShop = getFacade().getEntpShopService().getEntpShop(entpShop);
		if (null != entpShop) {
			p_index = entpShop.getP_index();
			request.setAttribute("shop_name", entpShop.getShop_name());
		}
		if (p_index != 0) {
			request.setAttribute("p_name", getPIndexName("" + p_index, ""));
		}

		HttpSession session = request.getSession(false);
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (ui != null) {
			BasePdType pdType = new BasePdType();
			pdType.setIs_model((short) 1);
			pdType.setPd_type((Long) ui.getMap().get("pd_type"));
			pdType = getFacade().getBasePdTypeService().getBasePdType(pdType);
			if (null != pdType) {
				request.setAttribute("pd_name", pdType.getPd_name());
			}

			MdasModPermission modPermission = new MdasModPermission();
			modPermission.setUser_id(ui.getId());
			if (GenericValidator.isLong(mdas_mod_id)) {
				modPermission.setMdas_mod_id(Long.valueOf(mdas_mod_id));
			}

			// 起始时间
			modPermission.getMap().put("view_date_start_is_not_null", true);
			modPermission.getMap().put("view_date_end_is_not_null", true);
			List<MdasModPermission> mPermissionList = getFacade().getMdasModPermissionService()
					.getMdasModPermissionList(modPermission);

			// 授权时间
			Date viewDateStart = null;
			Date viewDateEnd = null;
			if (null != mPermissionList && mPermissionList.size() > 0) {
				viewDateStart = mPermissionList.get(0).getView_date_start();
				viewDateEnd = mPermissionList.get(0).getView_date_end();
			}

			MdasBuyerInfo mdasBuyerInfo = new MdasBuyerInfo();
			mdasBuyerInfo.getMap().put("sell_date_ge", viewDateStart);
			mdasBuyerInfo.getMap().put("sell_date_le", viewDateEnd);
			mdasBuyerInfo.setShop_id(Long.valueOf(shop_id));

			if (StringUtils.isNotBlank(month)) {
				if (StringUtils.isNotBlank(year)) {
					int iMonth = Integer.parseInt(month);
					mdasBuyerInfo.getMap().put("sell_date_selected_ge", year + "-" + month + "-01");
					if (iMonth == 12) {
						mdasBuyerInfo.getMap().put("sell_date_selected_le", year + "-" + month + "-31");
					} else {
						mdasBuyerInfo.getMap().put("sell_date_lt", year + "-" + (iMonth + 1) + "-01");
					}

					// 当月的销售额
					List<MdasBuyerInfo> mBuyerInfoList = getFacade().getMdasBuyerInfoService()
							.getMdasBuyerInfoListForMsg(mdasBuyerInfo);
					if (null != mBuyerInfoList && mBuyerInfoList.size() == 1) {
						request.setAttribute("mdasBuyerInfoformonth", mBuyerInfoList.get(0));

						// 销售排行
						mdasBuyerInfo.setShop_id(null);
						mdasBuyerInfo.getMap().put("xs_amount_gt", mBuyerInfoList.get(0).getMap().get("xs_amount"));
						if (0 != p_index) {
							mdasBuyerInfo.getMap().put("p_index_par", p_index);
						}
						mBuyerInfoList = getFacade().getMdasBuyerInfoService()
								.getMdasBuyerInfoListForMsg(mdasBuyerInfo);

						if (null != mBuyerInfoList) {
							request.setAttribute("rank", mBuyerInfoList.size() + 1);
						} else {
							request.setAttribute("rank", 1);
						}
					}

					// 上月的销售额
					mdasBuyerInfo.setShop_id(Long.valueOf(shop_id));
					mdasBuyerInfo.getMap().clear();
					int iYear = Integer.parseInt(year);
					if (iMonth == 1) {
						mdasBuyerInfo.getMap().put("sell_date_selected_ge", "" + (iYear - 1) + "-12-01");
						mdasBuyerInfo.getMap().put("sell_date_lt", year + "-01-01");
					} else {
						mdasBuyerInfo.getMap().put("sell_date_selected_ge", year + "-" + (iMonth - 1) + "-01");
						mdasBuyerInfo.getMap().put("sell_date_lt", year + "-" + month + "-01");
					}

					mBuyerInfoList = getFacade().getMdasBuyerInfoService().getMdasBuyerInfoListForMsg(mdasBuyerInfo);
					if (null != mBuyerInfoList && mBuyerInfoList.size() == 1) {
						request.setAttribute("mdasBuyerInfoformonthbefore", mBuyerInfoList.get(0));
					}
				}
			} else {
				if (StringUtils.isNotBlank(year)) {
					mdasBuyerInfo.getMap().put("sell_date_selected_ge", year + "-01-01");
					mdasBuyerInfo.getMap().put("sell_date_selected_le", year + "-12-31");

					// 当年的销售额
					List<MdasBuyerInfo> mBuyerInfoList = getFacade().getMdasBuyerInfoService()
							.getMdasBuyerInfoListForMsg(mdasBuyerInfo);
					if (null != mBuyerInfoList && mBuyerInfoList.size() == 1) {
						request.setAttribute("mdasBuyerInfoforyear", mBuyerInfoList.get(0));

						// 销售排行
						mdasBuyerInfo.setShop_id(null);
						mdasBuyerInfo.getMap().put("xs_amount_gt", mBuyerInfoList.get(0).getMap().get("xs_amount"));
						if (0 != p_index) {
							mdasBuyerInfo.getMap().put("p_index_par", p_index);
						}
						mBuyerInfoList = getFacade().getMdasBuyerInfoService()
								.getMdasBuyerInfoListForMsg(mdasBuyerInfo);

						if (null != mBuyerInfoList) {
							request.setAttribute("rank", mBuyerInfoList.size() + 1);
						} else {
							request.setAttribute("rank", 1);
						}
					}

					// 去年的销售额
					mdasBuyerInfo.setShop_id(Long.valueOf(shop_id));
					mdasBuyerInfo.getMap().clear();
					int iYear = Integer.parseInt(year);
					mdasBuyerInfo.getMap().put("sell_date_selected_ge", "" + (iYear - 1) + "-01-01");
					mdasBuyerInfo.getMap().put("sell_date_selected_le", "" + (iYear - 1) + "-12-31");
					mBuyerInfoList = getFacade().getMdasBuyerInfoService().getMdasBuyerInfoListForMsg(mdasBuyerInfo);
					if (null != mBuyerInfoList && mBuyerInfoList.size() == 1) {
						request.setAttribute("mdasBuyerInfoforyearbefore", mBuyerInfoList.get(0));
					}

				}
			}
		}

		Map<String, Object> model = chart(mapping, form, request, response);

		List<BaseChart> baseChartListForAmount = (List<BaseChart>) model.get("baseChartList");
		double sumForAmount = 0;
		if (null != baseChartListForAmount && baseChartListForAmount.size() > 0) {
			for (BaseChart baseChart : baseChartListForAmount) {
				sumForAmount += Double.parseDouble(baseChart.getValue());
			}
		}

		request.setAttribute("sumForAmountWithPd", sumForAmount);
		request.setAttribute("model", model);

		return new ActionForward("view");
	}

	public ActionForward Pie3DForShop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> model = chart(mapping, form, request, response);

		String xmlDate = getFacade().getTemplateService().getContent("chart/Pie3D.ftl", model);
		renderXmlWithEncoding(response, xmlDate, "GBK");
		return null;
	}

	public static String getLastDayOfMonth(String year, String month) {
		Calendar cal = Calendar.getInstance();
		// 年
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		// 月，因为Calendar里的月是从0开始，所以要-1
		cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		// 日，设为一号
		cal.set(Calendar.DATE, 1);
		// 月份加一，得到下个月的一号
		cal.add(Calendar.MONTH, 1);
		// 下一个月减一为本月最后一天
		cal.add(Calendar.DATE, -1);
		return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));// 获得月末是几号

	}

	public ActionForward viewForGraphics(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String chart_type = (String) dynaBean.get("chart_type");
		PeProdUser mdas_userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		Long pd_type = (Long) mdas_userInfo.getMap().get("pd_type");

		dynaBean.set("pd_type", pd_type.toString());

		dynaBean.set("chart_type", chart_type);

		return new ActionForward("/../manager/admin/BrandGridsAnalysis/viewForGraphics.jsp");
	}

	// 形象图,网点数曲线图
	public ActionForward msLine(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> model = chartForMSLine(mapping, form, request, response);

		String xmlDate = getFacade().getTemplateService().getContent("chart/MSLine.ftl", model);
		renderXmlWithEncoding(response, xmlDate, "GBK");
		return null;
	}

	public Map<String, Object> chartForMSLine(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mdas_mod_id = (String) dynaBean.get("mdas_mod_id");
		String pd_type = (String) dynaBean.get("pd_type");
		String own_sys = (String) dynaBean.get("own_sys");
		String brand_id = (String) dynaBean.get("brand_id");

		Map<String, Object> model = new HashMap<String, Object>();

		//PeProdUser mdas_userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		MdasModPermission mdasModPermission = new MdasModPermission();
		mdasModPermission.setUser_id(Long.valueOf("104362"));
		mdasModPermission.setMdas_mod_id(Long.valueOf(mdas_mod_id));
		mdasModPermission.getMap().put("p_index_is_not_null", "true");
		mdasModPermission.getMap().put("p_name", "true");
		List<MdasModPermission> mdasModPermList_p_index = super.getFacade().getMdasModPermissionService()
				.getMdasModPermissionWithPNameList(mdasModPermission);

		Long p_index_s[] = new Long[mdasModPermList_p_index.size()];// 显示所有有权限省份的网点
		int count = 0;
		for (MdasModPermission m_p_index : mdasModPermList_p_index) {
			p_index_s[count++] = m_p_index.getP_index();
		}

		List<String> provinceName_list = new ArrayList<String>();
		List<Long> p_index_list = new ArrayList<Long>();

		MdasRegions mdasRegions = new MdasRegions();// 为了保证与表格显示的省份顺序一致
		mdasRegions.setIs_del(0);
		List<MdasRegions> mdasRegionList = getFacade().getMdasRegionsService().getMdasRegionsList(mdasRegions);
		if (null != mdasRegionList && mdasRegionList.size() > 0) {
			for (MdasRegions region : mdasRegionList) {
				MdasRegionsProvince mdasRegionsProvince = new MdasRegionsProvince();
				mdasRegionsProvince.setRegions_id(region.getId());
				mdasRegionsProvince.getMap().put("p_index_in", p_index_s);
				List<MdasRegionsProvince> mdasRegionsProvinceList = getFacade().getMdasRegionsProvinceService()
						.getMdasRegionsProvinceList(mdasRegionsProvince);

				if (null != mdasRegionsProvinceList && mdasRegionsProvinceList.size() > 0) {
					for (MdasRegionsProvince regionsProvince : mdasRegionsProvinceList) {
						provinceName_list.add(regionsProvince.getP_name());
						p_index_list.add(regionsProvince.getP_index());
					}
				}
			}
		}

		List<BaseChart> baseChartList = new ArrayList<BaseChart>();
		for (String p_name : provinceName_list) {
			BaseChart baseChart = new BaseChart();
			baseChart.setCategory_label(p_name);
			baseChartList.add(baseChart);
		}

		List<MdasModPermission> mdasModPermList_brand = new ArrayList<MdasModPermission>();
		if (StringUtils.isBlank(brand_id)) {// 所有有权限品牌
			mdasModPermission.getMap().clear();
			mdasModPermission.getMap().put("pd_type", pd_type);
			mdasModPermission.getMap().put("brand_id_is_not_null", "true");
			mdasModPermList_brand = super.getFacade().getMdasModPermissionService()
					.getMdasModPermissionWithBrandNameList(mdasModPermission);
		} else {// 查询某个品牌
			mdasModPermission = new MdasModPermission();
			mdasModPermission.setBrand_id(Long.valueOf(brand_id));
			mdasModPermList_brand = super.getFacade().getMdasModPermissionService()
					.getMdasModPermissionWithBrandNameList(mdasModPermission);
		}

		List<MsLineChart> msLineChartList = new ArrayList<MsLineChart>();
		for (MdasModPermission permission_brand : mdasModPermList_brand) {
			MsLineChart msLineChart = new MsLineChart();
			msLineChart.setSeries_name((String) permission_brand.getMap().get("brand_name"));

			List<BaseChart> base_chart_list = new ArrayList<BaseChart>();
			for (Long p_index : p_index_list) {
				MdasBuyerInfo mdasBuyerInfo = new MdasBuyerInfo();
				mdasBuyerInfo.setBrand_id(permission_brand.getBrand_id());
				if (StringUtils.isNotBlank(own_sys)) {
					mdasBuyerInfo.setOwn_sys(Integer.valueOf(own_sys));
				}
				mdasBuyerInfo.getMap().put("par_p_index", p_index);
				Long sum = getFacade().getMdasBuyerInfoService().getEntpShopStatisticForTable(mdasBuyerInfo);

				BaseChart baseChart = new BaseChart();
				baseChart.setValue(sum.toString());
				base_chart_list.add(baseChart);
			}

			msLineChart.setBaseChartList(base_chart_list);
			msLineChartList.add(msLineChart);
		}

		model.put("baseChartList", baseChartList);
		model.put("msLineChartList", msLineChartList);

		return model;
	}

	// 形象图，网点数饼状图
	public ActionForward pie(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> model = chartForPie(mapping, form, request, response);
		if (null != model && model.size() > 0) {
			String xmlDate = getFacade().getTemplateService().getContent("chart/Pie3D.ftl", model);
			renderXmlWithEncoding(response, xmlDate, "GBK");
		}
		return null;
	}

	public Map<String, Object> chartForPie(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Map<String, Object> model = new HashMap<String, Object>();
		List<BaseChart> baseChartList = new ArrayList<BaseChart>();
		String mdas_mod_id = (String) dynaBean.get("mdas_mod_id");
		String province = (String) dynaBean.get("province");
		String own_sys = (String) dynaBean.get("own_sys");
		String brand_id = (String) dynaBean.get("brand_id");
		if (!GenericValidator.isLong(brand_id)) {
			BaseChart baseChart = new BaseChart();
			baseChart.setValue("" + 0);
			baseChart.setLabel("品牌不能为空");
			baseChartList.add(baseChart);
			String unit = "个 数";
			// String moneyUnit = "%";
			StringBuffer caption = new StringBuffer();

			caption.append(" 品牌在各省市的网点数饼状图");

			model.put("baseChartList", baseChartList);

			model.put("caption", caption.toString());
			model.put("unit", unit);
			// model.put("moneyUnit", moneyUnit);
			model.put("baseFont", "宋体");
			return model;
		}

		PeProdUser mdas_userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		Long pd_type = (Long) mdas_userInfo.getMap().get("pd_type");

		// 取省市列表
		List<String> p_nameList = new ArrayList<String>();
		Long p_index_in[] = null;
		if (StringUtils.isBlank(province)) {
			MdasModPermission mdasModPermission = new MdasModPermission();
			mdasModPermission.setUser_id(Long.valueOf("104362"));
			mdasModPermission.setMdas_mod_id(Long.valueOf(mdas_mod_id));
			mdasModPermission.getMap().put("p_index_is_not_null", true);
			List<MdasModPermission> mdasModPermList_p_index = getFacade().getMdasModPermissionService()
					.getMdasModPermissionList(mdasModPermission);
			if (null != mdasModPermList_p_index) {
				p_index_in = new Long[mdasModPermList_p_index.size()];
				for (int i = 0; i < p_index_in.length; i++) {
					p_index_in[i] = mdasModPermList_p_index.get(i).getP_index();
					p_nameList.add(getPIndexName(p_index_in[i].toString(), ""));
				}
			}
		} else {
			BaseProvinceList baseProvince = new BaseProvinceList();
			baseProvince.setPar_index(Long.valueOf(province));
			baseProvince.setP_level((short) 2);
			List<BaseProvinceList> provinceList = getFacade().getBaseProvinceListService().getBaseProvinceListList(
					baseProvince);
			for (BaseProvinceList baseProvinceList : provinceList) {
				p_nameList.add(baseProvinceList.getP_name());
			}
		}

		// 取品牌列表
		MdasModPermission mdasModPermission = new MdasModPermission();
		mdasModPermission.setUser_id(Long.valueOf("104362"));
		mdasModPermission.setMdas_mod_id(Long.valueOf(mdas_mod_id));
		mdasModPermission.getMap().put("pd_type", pd_type);
		mdasModPermission.getMap().put("brand_id_is_not_null", "true");
		if (GenericValidator.isLong(brand_id)) {
			mdasModPermission.setBrand_id(Long.valueOf(brand_id));
		}

		List<MdasModPermission> mdasModPermList_brand = super.getFacade().getMdasModPermissionService()
				.getMdasModPermissionWithBrandNameList(mdasModPermission);

		List<MsLineChart> msLineChartList = new ArrayList<MsLineChart>();
		MdasModPermission mdasModPermission2 = mdasModPermList_brand.get(0);
		String brand_name = (String) mdasModPermission2.getMap().get("brand_name");

		MdasBuyerInfo mdasBuyerInfo = new MdasBuyerInfo();
		mdasBuyerInfo.setBrand_id(mdasModPermission2.getBrand_id());
		if (GenericValidator.isInt(own_sys)) {
			mdasBuyerInfo.setOwn_sys(Integer.parseInt(own_sys));
		}
		if (StringUtils.isBlank(province)) {
			if (null != p_index_in) {
				mdasBuyerInfo.getMap().put("p_index_in", p_index_in);
			}
		} else {
			mdasBuyerInfo.getMap().put("province", province);
			mdasBuyerInfo.getMap().put("p_index_par", province);
		}

		List<MdasBuyerInfo> mBuyerInfoList = getFacade().getMdasBuyerInfoService().getMdasBuyerInfoForShopCount(
				mdasBuyerInfo);

		if (null != mBuyerInfoList && mBuyerInfoList.size() > 0) {
			for (MdasBuyerInfo mbi : mBuyerInfoList) {
				BaseChart baseChart = new BaseChart();
				baseChart.setValue("" + mbi.getMap().get("count_num"));
				baseChart.setLabel((String) mbi.getMap().get("p_name"));
				baseChartList.add(baseChart);
			}
		}
		String unit = "单位/个 数";
		// String moneyUnit = "%";
		StringBuffer caption = new StringBuffer();

		caption.append(" " + brand_name + " 在各省市的网点数饼状图");

		model.put("baseChartList", baseChartList);
		model.put("msLineChartList", msLineChartList);

		model.put("caption", caption.toString());
		model.put("unit", unit);
		// model.put("moneyUnit", moneyUnit);
		model.put("baseFont", "宋体");
		return model;
	}

	// 形象图，网点数比率多曲线图
	public ActionForward rateMsLine(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> model = chartForRateMsLine(mapping, form, request, response);
		if (null != model && model.size() > 0) {
			String xmlDate = getFacade().getTemplateService().getContent("chart/MSLine.ftl", model);
			renderXmlWithEncoding(response, xmlDate, "GBK");
		}
		return null;
	}

	public Map<String, Object> chartForRateMsLine(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mdas_mod_id = (String) dynaBean.get("mdas_mod_id");
		String province = (String) dynaBean.get("province");
		String own_sys = (String) dynaBean.get("own_sys");
		String brand_id = (String) dynaBean.get("brand_id");

		PeProdUser mdas_userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		Long pd_type = (Long) mdas_userInfo.getMap().get("pd_type");

		// 取省市列表
		List<String> p_nameList = new ArrayList<String>();
		Long p_index_in[] = null;
		if (StringUtils.isBlank(province)) {
			MdasModPermission mdasModPermission = new MdasModPermission();
			mdasModPermission.setUser_id(Long.valueOf("104362"));
			mdasModPermission.setMdas_mod_id(Long.valueOf(mdas_mod_id));
			mdasModPermission.getMap().put("p_index_is_not_null", true);
			List<MdasModPermission> mdasModPermList_p_index = getFacade().getMdasModPermissionService()
					.getMdasModPermissionList(mdasModPermission);
			if (null != mdasModPermList_p_index) {
				p_index_in = new Long[mdasModPermList_p_index.size()];
				for (int i = 0; i < p_index_in.length; i++) {
					p_index_in[i] = mdasModPermList_p_index.get(i).getP_index();
					p_nameList.add(getPIndexName(p_index_in[i].toString(), ""));
				}
			}
		} else {
			BaseProvinceList baseProvince = new BaseProvinceList();
			baseProvince.setPar_index(Long.valueOf(province));
			baseProvince.setP_level((short) 2);
			List<BaseProvinceList> provinceList = getFacade().getBaseProvinceListService().getBaseProvinceListList(
					baseProvince);
			for (BaseProvinceList baseProvinceList : provinceList) {
				p_nameList.add(baseProvinceList.getP_name());
			}
		}

		// 取品牌列表
		MdasModPermission mdasModPermission = new MdasModPermission();
		mdasModPermission.setUser_id(Long.valueOf("104362"));
		mdasModPermission.setMdas_mod_id(Long.valueOf(mdas_mod_id));
		mdasModPermission.getMap().put("pd_type", pd_type);
		mdasModPermission.getMap().put("brand_id_is_not_null", "true");
		if (GenericValidator.isLong(brand_id)) {
			mdasModPermission.setBrand_id(Long.valueOf(brand_id));
		}

		List<MdasModPermission> mdasModPermList_brand = super.getFacade().getMdasModPermissionService()
				.getMdasModPermissionWithBrandNameList(mdasModPermission);

		Map<String, Object> model = new HashMap<String, Object>();
		List<MsLineChart> msLineChartList = new ArrayList<MsLineChart>();
		for (MdasModPermission mdasModPermission2 : mdasModPermList_brand) {
			MsLineChart msLineChart = new MsLineChart();
			String brand_name = (String) mdasModPermission2.getMap().get("brand_name");
			msLineChart.setSeries_name(brand_name);

			MdasBuyerInfo mdasBuyerInfo = new MdasBuyerInfo();
			mdasBuyerInfo.setBrand_id(mdasModPermission2.getBrand_id());
			if (GenericValidator.isInt(own_sys)) {
				mdasBuyerInfo.setOwn_sys(Integer.parseInt(own_sys));
			}
			if (StringUtils.isBlank(province)) {
				if (null != p_index_in) {
					mdasBuyerInfo.getMap().put("p_index_in", p_index_in);
				}
			} else {
				mdasBuyerInfo.getMap().put("province", province);
				mdasBuyerInfo.getMap().put("p_index_par", province);
			}

			List<MdasBuyerInfo> mBuyerInfoList = getFacade().getMdasBuyerInfoService().getMdasBuyerInfoForShopCount(
					mdasBuyerInfo);

			List<BaseChart> baseChartList = new ArrayList<BaseChart>();
			if (null != p_nameList && p_nameList.size() > 0) {
				for (String p_name : p_nameList) {
					BaseChart baseChart = new BaseChart();

					boolean flag = true;

					if (null != mBuyerInfoList && mBuyerInfoList.size() > 0) {
						for (MdasBuyerInfo mdasBuyerInfo2 : mBuyerInfoList) {
							if (p_name.equals(mdasBuyerInfo2.getMap().get("p_name"))) {
								baseChart.setValue("" + mdasBuyerInfo2.getMap().get("proportion"));
								flag = false;
							}
						}
					}

					if (flag) {
						baseChart.setValue("0");
					}
					baseChartList.add(baseChart);
				}
			}
			msLineChart.setBaseChartList(baseChartList);
			msLineChartList.add(msLineChart);
		}

		List<BaseChart> baseChartList = new ArrayList<BaseChart>();

		if (null != p_nameList && p_nameList.size() > 0) {
			for (String p_name : p_nameList) {
				BaseChart baseChart = new BaseChart();
				baseChart.setCategory_label(p_name);
				baseChartList.add(baseChart);
			}
		}
		String unit = "单位 百分比";
		// String moneyUnit = "%";
		StringBuffer caption = new StringBuffer();

		caption.append(" 各品牌在各省市的网点数与总网点数比例曲线图");

		model.put("baseChartList", baseChartList);
		model.put("msLineChartList", msLineChartList);

		model.put("caption", caption.toString());
		model.put("unit", unit);
		// model.put("moneyUnit", moneyUnit);
		model.put("baseFont", "宋体");
		return model;
	}

	// 形象图,网点数柱状图
	public ActionForward msColumn(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> model = chartForMSColumn(mapping, form, request, response);// 调用

		String xmlDate = getFacade().getTemplateService().getContent("chart/MSColumn3D.ftl", model);
		renderXmlWithEncoding(response, xmlDate, "GBK");
		return null;
	}

	public Map<String, Object> chartForMSColumn(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mdas_mod_id = (String) request.getParameter("mdas_mod_id");
		String province = (String) dynaBean.get("province");
		String brand_id = (String) dynaBean.get("brand_id");
		Long province_id[] = null;
		HttpSession session = request.getSession(false);
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		Map<String, Object> model = new HashMap<String, Object>();

		if (ui != null) {
			List<BaseChart> baseChartListForProv = new ArrayList<BaseChart>();
			List<MdasModPermission> mPermissionList = new ArrayList<MdasModPermission>();
			List<MSColumn3DChart> mSColumn3DChartList = new ArrayList<MSColumn3DChart>();
			List<BaseChart> baseChartList = new ArrayList<BaseChart>();

			// 根据选择取省级下的市级数据
			if (StringUtils.isNotBlank(province)) {
				BaseProvinceList bpl = new BaseProvinceList();
				bpl.setP_index(Long.parseLong(province));
				bpl.setP_level(Short.parseShort("2"));
				List<BaseProvinceList> baseProvinceListList = super.getFacade().getBaseProvinceListService()
						.getBaseProvinceListChildrenList(bpl);
				if (baseProvinceListList != null) {
					for (BaseProvinceList b : baseProvinceListList) {
						BaseChart bc = new BaseChart();
						bc.setLabel(b.getP_name());
						bc.setCategory_label(b.getP_name());
						bc.setValue(String.valueOf(b.getP_index()));
						baseChartListForProv.add(bc);
					}
				}
				model.put("baseChartList", baseChartListForProv);
			} else {// 根据授权取得省份数据
				List<MdasModPermission> mPermissionListForProv = new ArrayList<MdasModPermission>();
				MdasModPermission modPermissionForProv = new MdasModPermission();
				PeProdUser mdas_userInfo1 = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
				modPermissionForProv.setUser_id(mdas_userInfo1.getId());
				modPermissionForProv.setMdas_mod_id(Long.valueOf(mdas_mod_id));

				modPermissionForProv.getMap().put("p_index_is_not_null", true);
				mPermissionListForProv = super.getFacade().getMdasModPermissionService().getMdasModPermissionList(
						modPermissionForProv);
				province_id = new Long[mPermissionListForProv.size()];
				int i = 0;
				for (MdasModPermission m : mPermissionListForProv) {// 根据授权的省份取得省数据
					BaseChart bc = new BaseChart();
					Long p_index = m.getP_index();
					BaseProvinceList bpl = new BaseProvinceList();
					bpl.setP_index(p_index);
					province_id[i++] = p_index;
					bpl = super.getFacade().getBaseProvinceListService().getBaseProvinceList(bpl);
					if (bpl != null) {
						bc.setLabel(bpl.getP_name());
						bc.setCategory_label(bpl.getP_name());
						bc.setValue(String.valueOf(bpl.getP_index()));
						baseChartListForProv.add(bc);
					}
				}
				model.put("baseChartList", baseChartListForProv);
			}

			// 根据选择的取出品牌list
			if (StringUtils.isNotBlank(brand_id)) {
				BaseBrandInfo bi = new BaseBrandInfo();
				bi.setBrand_id(Long.parseLong(brand_id));
				bi = super.getFacade().getBaseBrandInfoService().getBaseBrandInfo(bi);
				if (bi != null) {
					MSColumn3DChart MSColumn3DChart = new MSColumn3DChart();
					MSColumn3DChart.setSeries_name(bi.getBrand_name());

					MdasBuyerInfo mdasBuyerInfo = new MdasBuyerInfo();
					mdasBuyerInfo.setBrand_id(Long.parseLong(brand_id));
					Long p_index[] = new Long[baseChartListForProv.size()];
					for (int i = 0; i < baseChartListForProv.size(); i++) {
						p_index[i] = Long.parseLong(baseChartListForProv.get(i).getValue());
					}
					if (StringUtils.isNotBlank(province)) {
						mdasBuyerInfo.getMap().put("province", true);
						mdasBuyerInfo.getMap().put("par_p_index", province);
					} else {
						mdasBuyerInfo.getMap().put("p_index_in", province_id);
					}

					List<MdasBuyerInfo> mBuyerInfoList = getFacade().getMdasBuyerInfoService()
							.getMdasBuyerInfoForShopCount(mdasBuyerInfo);

					for (BaseChart b : baseChartListForProv) {
						BaseChart baseChart = new BaseChart();
						for (MdasBuyerInfo m : mBuyerInfoList) {
							if (StringUtils.equalsIgnoreCase(b.getLabel(), (String) m.getMap().get("p_name"))) {
								baseChart.setValue(String.valueOf(m.getMap().get("count_num")));
							}
						}
						baseChartList.add(baseChart);
					}
					MSColumn3DChart.setBaseChartList(baseChartList);
					mSColumn3DChartList.add(MSColumn3DChart);
					model.put("mSColumn3DChartChartList", mSColumn3DChartList);
				}

			} else {
				MdasModPermission modPermission = new MdasModPermission();
				//PeProdUser mdas_userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
				modPermission.setUser_id(Long.valueOf("104362"));
				modPermission.setMdas_mod_id(Long.valueOf(mdas_mod_id));
				modPermission.getMap().put("brand_id_is_not_null", true);
				mPermissionList = getFacade().getMdasModPermissionService().getMdasModPermissionList(modPermission);
				for (MdasModPermission m : mPermissionList) {// 根据授权的品牌循环品牌
					MSColumn3DChart MSColumn3DChart = new MSColumn3DChart();
					Long brandId = m.getBrand_id();
					BaseBrandInfo bi = new BaseBrandInfo();
					bi.setBrand_id(brandId);
					bi = super.getFacade().getBaseBrandInfoService().getBaseBrandInfo(bi);
					if (null != bi) {
						MSColumn3DChart.setSeries_name(bi.getBrand_name());
					}
					MdasBuyerInfo mdasBuyerInfo = new MdasBuyerInfo();
					mdasBuyerInfo.setBrand_id(brandId);
					Long p_index[] = new Long[baseChartListForProv.size()];
					for (int i = 0; i < baseChartListForProv.size(); i++) {
						p_index[i] = Long.parseLong(baseChartListForProv.get(i).getValue());
					}
					if (StringUtils.isNotBlank(province)) {
						mdasBuyerInfo.getMap().put("province", true);
						mdasBuyerInfo.getMap().put("par_p_index", province);
					} else {
						mdasBuyerInfo.getMap().put("p_index_in", province_id);
					}
					List<MdasBuyerInfo> mBuyerInfoList = getFacade().getMdasBuyerInfoService()
							.getMdasBuyerInfoForShopCount(mdasBuyerInfo);
					baseChartList = new ArrayList<BaseChart>();
					for (BaseChart b : baseChartListForProv) {
						BaseChart baseChart = new BaseChart();
						for (MdasBuyerInfo mbi : mBuyerInfoList) {
							if (StringUtils.equalsIgnoreCase(b.getLabel(), (String) mbi.getMap().get("p_name"))) {
								baseChart.setValue(String.valueOf(mbi.getMap().get("count_num")));
							}
						}
						baseChartList.add(baseChart);
					}

					MSColumn3DChart.setBaseChartList(baseChartList);
					mSColumn3DChartList.add(MSColumn3DChart);

				}
				model.put("mSColumn3DChartChartList", mSColumn3DChartList);
			}
		}
		return model;
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-09-02
	 */
	// 时间级联
	public ActionForward getDateForSelectList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mdas_mod_id = (String) dynaBean.get("mdas_mod_id");
		String year = (String) dynaBean.get("year");
		String year_son = (String) dynaBean.get("year_son");
		if (StringUtils.isNotBlank(year_son)) {
			year = year_son;
		}

		if (!GenericValidator.isLong(year)) {
			return null;
		}

		// 起始时间
		HttpSession session = request.getSession(false);
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (ui != null) {
			MdasModPermission modPermission = new MdasModPermission();
			modPermission.setUser_id(ui.getId());
			if (GenericValidator.isLong(mdas_mod_id)) {
				modPermission.setMdas_mod_id(Long.valueOf(mdas_mod_id));
			}
			modPermission.getMap().put("view_date_start_is_not_null", true);
			modPermission.getMap().put("view_date_end_is_not_null", true);
			List<MdasModPermission> mPermissions = getFacade().getMdasModPermissionService().getMdasModPermissionList(
					modPermission);

			Date viewDateStart = null;
			Date viewDateEnd = null;
			if (null != mPermissions && mPermissions.size() > 0) {
				viewDateStart = mPermissions.get(0).getView_date_start();
				viewDateEnd = mPermissions.get(0).getView_date_end();

				int startYear = Integer.parseInt(DateFormatUtils.format(viewDateStart, "yyyy"));
				int startMonth = Integer.parseInt(DateFormatUtils.format(viewDateStart, "MM"));
				int endYear = Integer.parseInt(DateFormatUtils.format(viewDateEnd, "yyyy"));
				int endMonth = Integer.parseInt(DateFormatUtils.format(viewDateEnd, "MM"));
				List<String> dataList = new ArrayList<String>();

				// 采用unicode编码，防止在程序中出现中文，年=\u5e74；月=\u6708。
				if ("0".equals(year)) {
					for (int i = startYear; i <= endYear; i++) {
						dataList.add(StringUtils.join(new String[] { "[\"", "" + i + "\u5e74", "\",\"", "" + i, "\"]" }));
					}
					super.renderJson(response, StringUtils.join(new String[] { "[", StringUtils.join(dataList, ","),
							"]" }));
				} else {
					if (startYear == endYear) {
						for (int i = startMonth; i <= endMonth; i++) {
							dataList.add(StringUtils
									.join(new String[] { "[\"", "" + i + "\u6708", "\",\"", "" + i, "\"]" }));
						}
						super.renderJson(response, StringUtils.join(new String[] { "[",
								StringUtils.join(dataList, ","), "]" }));
					} else {
						if (("" + startYear).equals(year)) {
							for (int i = startMonth; i <= 12; i++) {
								dataList.add(StringUtils.join(new String[] { "[\"", "" + i + "\u6708", "\",\"", "" + i,
										"\"]" }));
							}
							super.renderJson(response, StringUtils.join(new String[] { "[",
									StringUtils.join(dataList, ","), "]" }));
						} else if (("" + endYear).equals(year)) {
							for (int i = 1; i <= endMonth; i++) {
								dataList.add(StringUtils.join(new String[] { "[\"", "" + i + "\u6708", "\",\"", "" + i,
										"\"]" }));
							}
							super.renderJson(response, StringUtils.join(new String[] { "[",
									StringUtils.join(dataList, ","), "]" }));
						} else {
							for (int i = 1; i <= 12; i++) {
								dataList.add(StringUtils.join(new String[] { "[\"", "" + i + "\u6708", "\",\"", "" + i,
										"\"]" }));
							}
							super.renderJson(response, StringUtils.join(new String[] { "[",
									StringUtils.join(dataList, ","), "]" }));
						}
					}
				}
			}
		}

		return null;
	}

	/**
	 * @author Wu,Yang
	 * @version 2010-09-07
	 * @param encoding
	 *            编码，用GBK图形才能正常显示
	 */
	protected void renderXmlWithEncoding(HttpServletResponse response, String text, String encoding) {
		render(response, text, "text/xml;charset=".concat(encoding));
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-09-13
	 */
	// 单个网点大类销售额饼状图
	public ActionForward Pie3D(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> model = chart(mapping, form, request, response);
		if (null != model && model.size() > 0) {
			String xmlDate = getFacade().getTemplateService().getContent("chart/Pie3D.ftl", model);
			renderXmlWithEncoding(response, xmlDate, "GBK");
		}
		return null;
	}

	public Map<String, Object> chart(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String shop_id = (String) dynaBean.get("shop_id");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String mdas_mod_id = (String) dynaBean.get("mdas_mod_id");

		HttpSession session = request.getSession(false);
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		Map<String, Object> model = new HashMap<String, Object>();
		if (ui != null) {
			MdasModPermission modPermission = new MdasModPermission();
			modPermission.setUser_id(ui.getId());
			if (GenericValidator.isLong(mdas_mod_id)) {
				modPermission.setMdas_mod_id(Long.valueOf(mdas_mod_id));
			}

			// 起始时间
			modPermission.getMap().put("view_date_start_is_not_null", true);
			modPermission.getMap().put("view_date_end_is_not_null", true);
			List<MdasModPermission> mPermissionList = getFacade().getMdasModPermissionService()
					.getMdasModPermissionList(modPermission);

			Date viewDateStart = null;
			Date viewDateEnd = null;
			if (null != mPermissionList && mPermissionList.size() > 0) {
				viewDateStart = mPermissionList.get(0).getView_date_start();
				viewDateEnd = mPermissionList.get(0).getView_date_end();
			}

			MdasBuyerInfo mBuyerInfo = new MdasBuyerInfo();
			mBuyerInfo.getMap().put("sell_date_ge", viewDateStart);
			mBuyerInfo.getMap().put("sell_date_le", viewDateEnd);
			if (GenericValidator.isLong(shop_id)) {
				mBuyerInfo.setShop_id(Long.valueOf(shop_id));
			}

			// 设置查询时间
			if (StringUtils.isNotBlank(year)) {
				if (StringUtils.isNotBlank(month)) {
					int iMonth = Integer.parseInt(month);
					mBuyerInfo.getMap().put("sell_date_selected_ge", year + "-" + month + "-01");
					if (iMonth == 12) {
						mBuyerInfo.getMap().put("sell_date_selected_le", year + "-" + month + "-31");
					} else {
						mBuyerInfo.getMap().put("sell_date_lt", year + "-" + (iMonth + 1) + "-01");
					}
				} else {
					mBuyerInfo.getMap().put("sell_date_selected_ge", year + "-01-01");
					mBuyerInfo.getMap().put("sell_date_selected_le", year + "-12-31");
				}
			}

			List<MdasBuyerInfo> mBuyerInfoList = getFacade().getMdasBuyerInfoService().getMdasBuyerInfoListForXsCount(
					mBuyerInfo);

			List<BaseChart> baseChartList = new ArrayList<BaseChart>();

			if (null != mBuyerInfoList && mBuyerInfoList.size() > 0) {
				for (MdasBuyerInfo mbi : mBuyerInfoList) {
					BaseChart baseChart = new BaseChart();
					baseChart.setValue("" + mbi.getMap().get("xs_amount"));
					baseChart.setLabel((String) mbi.getMap().get("pd_name"));
					baseChartList.add(baseChart);
				}
			}
			String unit = "单位 元";
			String moneyUnit = "￥";
			StringBuffer caption = new StringBuffer();

			caption.append(" 品类 销售对比图");

			model.put("baseChartList", baseChartList);

			model.put("caption", caption.toString());
			model.put("unit", unit);
			model.put("moneyUnit", moneyUnit);
			model.put("baseFont", "宋体");
		}

		return model;
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-09-13
	 */
	// 单个网点品牌销售额饼状图
	public ActionForward Pie3DForBrand(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> model = chartForBrand(mapping, form, request, response);
		if (null != model && model.size() > 0) {
			String xmlDate = getFacade().getTemplateService().getContent("chart/Pie3D.ftl", model);
			renderXmlWithEncoding(response, xmlDate, "GBK");
		}
		return null;
	}

	public Map<String, Object> chartForBrand(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String shop_id = (String) dynaBean.get("shop_id");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String mdas_mod_id = (String) dynaBean.get("mdas_mod_id");

		HttpSession session = request.getSession(false);
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		Map<String, Object> model = new HashMap<String, Object>();
		if (ui != null) {
			MdasModPermission modPermission = new MdasModPermission();
			modPermission.setUser_id(ui.getId());
			if (GenericValidator.isLong(mdas_mod_id)) {
				modPermission.setMdas_mod_id(Long.valueOf(mdas_mod_id));
			}

			// 起始时间
			modPermission.getMap().put("view_date_start_is_not_null", true);
			modPermission.getMap().put("view_date_end_is_not_null", true);
			List<MdasModPermission> mPermissionList = getFacade().getMdasModPermissionService()
					.getMdasModPermissionList(modPermission);

			Date viewDateStart = null;
			Date viewDateEnd = null;
			if (null != mPermissionList && mPermissionList.size() > 0) {
				viewDateStart = mPermissionList.get(0).getView_date_start();
				viewDateEnd = mPermissionList.get(0).getView_date_end();
			}

			MdasBuyerInfo mBuyerInfo = new MdasBuyerInfo();
			mBuyerInfo.getMap().put("sell_date_ge", viewDateStart);
			mBuyerInfo.getMap().put("sell_date_le", viewDateEnd);
			mBuyerInfo.setPar_pd_type_id((Long) ui.getMap().get("pd_type"));
			if (GenericValidator.isLong(shop_id)) {
				mBuyerInfo.setShop_id(Long.valueOf(shop_id));
			}

			// 设置查询时间
			if (StringUtils.isNotBlank(year)) {
				if (StringUtils.isNotBlank(month)) {
					int iMonth = Integer.parseInt(month);
					mBuyerInfo.getMap().put("sell_date_selected_ge", year + "-" + month + "-01");
					if (iMonth == 12) {
						mBuyerInfo.getMap().put("sell_date_selected_le", year + "-" + month + "-31");
					} else {
						mBuyerInfo.getMap().put("sell_date_lt", year + "-" + (iMonth + 1) + "-01");
					}
				} else {
					mBuyerInfo.getMap().put("sell_date_selected_ge", year + "-01-01");
					mBuyerInfo.getMap().put("sell_date_selected_le", year + "-12-31");
				}
			}

			List<MdasBuyerInfo> mBuyerInfoList = getFacade().getMdasBuyerInfoService()
					.getMdasBuyerInfoListForBrandXsCount(mBuyerInfo);

			List<BaseChart> baseChartList = new ArrayList<BaseChart>();

			if (null != mBuyerInfoList && mBuyerInfoList.size() > 0) {
				double sumOtherAmount = 0;
				for (int i = 0; i < mBuyerInfoList.size(); i++) {
					MdasBuyerInfo mbi = mBuyerInfoList.get(i);
					if (i < 5) {
						BaseChart baseChart = new BaseChart();
						baseChart.setValue("" + mbi.getMap().get("xs_amount"));
						baseChart.setLabel(mbi.getBrand_name());
						baseChartList.add(baseChart);
					} else {
						sumOtherAmount += Double.parseDouble("" + mbi.getMap().get("xs_amount"));
					}
				}
				if (sumOtherAmount != 0) {
					BaseChart baseChart = new BaseChart();
					baseChart.setValue("" + sumOtherAmount);
					baseChart.setLabel(getMessage(request, "analytics.chart.other"));
					baseChartList.add(baseChart);
				}
			}
			String unit = "单位 元";
			String moneyUnit = "￥";
			StringBuffer caption = new StringBuffer();
			if (null != mBuyerInfoList && mBuyerInfoList.size() > 0) {
				caption.append(mBuyerInfoList.get(0).getShop_name() + " 网点");
			}
			BasePdType pdType = new BasePdType();
			pdType.setPd_type((Long) ui.getMap().get("pd_type"));
			pdType.setIs_model((short) 1);
			pdType = getFacade().getBasePdTypeService().getBasePdType(pdType);

			if (null != pdType) {
				caption.append(pdType.getPd_name());
			}
			caption.append(" 品牌 销售额对比图");

			model.put("baseChartList", baseChartList);

			model.put("caption", caption.toString());
			model.put("unit", unit);
			model.put("moneyUnit", moneyUnit);
			model.put("baseFont", "宋体");
		}

		return model;
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-09-15
	 */
	// 单个网点品牌销售量饼状图
	public ActionForward Pie3DForBrandCount(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> model = chartForBrandCount(mapping, form, request, response);
		if (null != model && model.size() > 0) {
			String xmlDate = getFacade().getTemplateService().getContent("chart/Pie3D.ftl", model);
			renderXmlWithEncoding(response, xmlDate, "GBK");
		}
		return null;
	}

	public Map<String, Object> chartForBrandCount(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String shop_id = (String) dynaBean.get("shop_id");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String mdas_mod_id = (String) dynaBean.get("mdas_mod_id");

		HttpSession session = request.getSession(false);
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		Map<String, Object> model = new HashMap<String, Object>();
		if (ui != null) {
			MdasModPermission modPermission = new MdasModPermission();
			modPermission.setUser_id(ui.getId());
			if (GenericValidator.isLong(mdas_mod_id)) {
				modPermission.setMdas_mod_id(Long.valueOf(mdas_mod_id));
			}

			// 起始时间
			modPermission.getMap().put("view_date_start_is_not_null", true);
			modPermission.getMap().put("view_date_end_is_not_null", true);
			List<MdasModPermission> mPermissionList = getFacade().getMdasModPermissionService()
					.getMdasModPermissionList(modPermission);

			Date viewDateStart = null;
			Date viewDateEnd = null;
			if (null != mPermissionList && mPermissionList.size() > 0) {
				viewDateStart = mPermissionList.get(0).getView_date_start();
				viewDateEnd = mPermissionList.get(0).getView_date_end();
			}

			MdasBuyerInfo mBuyerInfo = new MdasBuyerInfo();
			mBuyerInfo.getMap().put("sell_date_ge", viewDateStart);
			mBuyerInfo.getMap().put("sell_date_le", viewDateEnd);
			mBuyerInfo.setPar_pd_type_id((Long) ui.getMap().get("pd_type"));
			if (GenericValidator.isLong(shop_id)) {
				mBuyerInfo.setShop_id(Long.valueOf(shop_id));
			}

			// 设置查询时间
			if (StringUtils.isNotBlank(year)) {
				if (StringUtils.isNotBlank(month)) {
					int iMonth = Integer.parseInt(month);
					mBuyerInfo.getMap().put("sell_date_selected_ge", year + "-" + month + "-01");
					if (iMonth == 12) {
						mBuyerInfo.getMap().put("sell_date_selected_le", year + "-" + month + "-31");
					} else {
						mBuyerInfo.getMap().put("sell_date_lt", year + "-" + (iMonth + 1) + "-01");
					}
				} else {
					mBuyerInfo.getMap().put("sell_date_selected_ge", year + "-01-01");
					mBuyerInfo.getMap().put("sell_date_selected_le", year + "-12-31");
				}
			}

			List<MdasBuyerInfo> mBuyerInfoList = getFacade().getMdasBuyerInfoService()
					.getMdasBuyerInfoListForBrandXsSales(mBuyerInfo);

			List<BaseChart> baseChartList = new ArrayList<BaseChart>();

			if (null != mBuyerInfoList && mBuyerInfoList.size() > 0) {
				int sumOtherCount = 0;
				for (int i = 0; i < mBuyerInfoList.size(); i++) {
					MdasBuyerInfo mbi = mBuyerInfoList.get(i);
					if (i < 5) {
						BaseChart baseChart = new BaseChart();
						baseChart.setValue("" + mbi.getMap().get("xs_amount"));
						baseChart.setLabel(mbi.getBrand_name());
						baseChartList.add(baseChart);
					} else {
						sumOtherCount += Integer.parseInt("" + mbi.getMap().get("xs_amount"));
					}
				}
				if (sumOtherCount != 0) {
					BaseChart baseChart = new BaseChart();
					baseChart.setValue("" + sumOtherCount);
					baseChart.setLabel(getMessage(request, "analytics.chart.other"));
					baseChartList.add(baseChart);
				}
			}

			String unit = "单位 台";
			StringBuffer caption = new StringBuffer();
			if (null != mBuyerInfoList && mBuyerInfoList.size() > 0) {
				caption.append(mBuyerInfoList.get(0).getShop_name() + " 网点");
			}
			BasePdType pdType = new BasePdType();
			pdType.setPd_type((Long) ui.getMap().get("pd_type"));
			pdType.setIs_model((short) 1);
			pdType = getFacade().getBasePdTypeService().getBasePdType(pdType);

			if (null != pdType) {
				caption.append(pdType.getPd_name());
			}

			caption.append(" 品牌 销售量对比图");

			model.put("baseChartList", baseChartList);

			model.put("caption", caption.toString());
			model.put("unit", unit);
			model.put("baseFont", "宋体");
		}

		return model;
	}

	public ActionForward getShopInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String shop_id = (String) dynaBean.get("shop_id");
		StringBuffer sb = new StringBuffer();

		sb.append("[");
		if (StringUtils.isNotBlank(shop_id)) {
			EntpShop entpShop = new EntpShop();
			entpShop.setShop_id(Long.valueOf(shop_id));
			entpShop.setState(0);
			entpShop = getFacade().getEntpShopService().getEntpShop(entpShop);
			if (null != entpShop) {
				sb.append("{");
				sb.append("\"shop_id\":\"").append(entpShop.getShop_id()).append("\",");
				sb.append("\"shop_name\":\"").append(entpShop.getShop_name()).append("\",");
				sb.append("\"link_user\":\"").append(entpShop.getLink_user()).append("\",");
				sb.append("\"link_phone\":\"").append(entpShop.getLink_phone()).append("\",");
				sb.append("\"street_addr\":\"").append(entpShop.getStreet_addr()).append("\",");
				MdasBuyerInfo mdasBuyerInfo = new MdasBuyerInfo();
				mdasBuyerInfo.setShop_id(entpShop.getShop_id());
				mdasBuyerInfo = super.getFacade().getMdasBuyerInfoService().getSumPrice(mdasBuyerInfo);
				String sumMoney = "";
				if (null == mdasBuyerInfo) {
					sumMoney = "0";
				} else if (null != mdasBuyerInfo.getMap().get("sum_price")) {
					// BigDecimal.valueOf(mdasBuyerInfo.getMap().get("sum_price"))
					// new
					// BigDecimal(String.valueOf(mdasBuyerInfo.getMap().get("sum_price")));
					sumMoney = String.valueOf(mdasBuyerInfo.getMap().get("sum_price"));
				}
				sb.append("\"sumMoney\":\"").append(sumMoney).append("\",");
				String main_pd = null == entpShop.getMain_pd() ? "" : entpShop.getMain_pd();
				sb.append("\"main_pd\":\"").append(main_pd).append("\"");
				sb.append("},");
			}
		}
		sb.append("{\"author\" : \"liyuan\"}]");
		super.render(response, sb.toString(), "text/x-json;charset=UTF-8");
		return null;

	}
}