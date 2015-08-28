package com.ebiz.mmt.web.struts.manager.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.ebiz.mmt.domain.BaseBrandInfo;
import com.ebiz.mmt.domain.BasePdClass;
import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.BasePdType;
import com.ebiz.mmt.domain.EntpShop;
import com.ebiz.mmt.domain.KonkaBranchCategory;
import com.ebiz.mmt.domain.MmtEntpShop;
import com.ebiz.mmt.domain.KonkaR3MmtMatch;
import com.ebiz.mmt.domain.KonkaShopDevelop;
import com.ebiz.mmt.domain.MvClsidJoinBrandXxhx;
import com.ebiz.mmt.domain.MvShopPdtypeAndBrand;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Cheng,Bing
 * @version 2011-09-30 (新建)
 * @version 2011-10-19 (变更)
 * @version 2011-10-28 (变更)
 */
public class EntpShopSearchAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public List<EntpShop> getShopSearchList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		
		Pager pager = (Pager) dynaBean.get("pager");
		String page_id = (String) dynaBean.get("page_id");
		String page_type = (String) dynaBean.get("page_type");

		if (page_id != null && "EntpShopSellAnalysis".equals(page_id)) {
			DynaBean EntpShopSearch_form = (DynaBean) super
					.getSessionAttribute(request, "EntpShopSearch");
			setTransFormValue(dynaBean, EntpShopSearch_form);
		}

		String shop_name_like = (String) dynaBean.get("shop_name_like");
		String mod_id = (String) dynaBean.get("mod_id");
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");
		
		// String shop_types_str = (String) dynaBean.get("shop_types_str");
		// String shop_types[] = request.getParameterValues("shop_types");
		
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String pd_type = (String) dynaBean.get("pd_type");
		String busi_total1 = (String) dynaBean.get("busi_total1");
		String busi_proportion1 = (String) dynaBean.get("busi_proportion1");
		String brand_id = (String) dynaBean.get("brand_id");
		String busi_total2 = (String) dynaBean.get("busi_total2");
		String busi_proportion2 = (String) dynaBean.get("busi_proportion2");

		String cls_ids_select = (String) dynaBean.get("cls_ids_select");
		String bpcList_string = (String) dynaBean.get("bpcList_string");
		String[] brand_ids = (String[]) request.getParameterValues("brand_ids");
		String more = (String) dynaBean.get("more");
		String brand_ids_select = (String) dynaBean.get("brand_ids_select");

		if (StringUtils.isBlank(mod_id)) {
			String msg = super.getMessage(request,"login.prod.failed.param.lost");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 获取登录用户 企业信息
		PeProdUser SxUserInfo = (PeProdUser) super.getSessionAttribute(request,Constants.USER_INFO);

		String p_indexs = "";
		String cls_ids = "";
		String brand_ids_string = "";

		if (null != brand_ids) {
			for (int i = 0; i < brand_ids.length; i++) {
				if (StringUtils.isNotBlank(brand_ids[i])) {
					brand_ids_string += brand_ids[i] + ",";
				}
			}
		}

		List<BasePdClazz> basePdClassList = getBasePdClazzList();

		if (null != basePdClassList && basePdClassList.size() > 0) {
			for (BasePdClazz temp : basePdClassList) {
				if (SxUserInfo.getUser_type() == 1) {
					if (("," + cls_ids).indexOf("," + temp.getCls_id() + ",") > 0) {
						temp.getMap().put("is_use", 1);
					} else {
						temp.getMap().put("is_use", 0);
					}
				} else {
					temp.getMap().put("is_use", 1);
				}
			}
			request.setAttribute("basePdClassList", basePdClassList);
		}

		// 产品类别和产品品牌回显
		if (StringUtils.isNotBlank(cls_ids_select)) {
			BasePdClass bpc = new BasePdClass();
			bpc.getMap().put("cls_id_in",
					cls_ids_select.substring(0, cls_ids_select.length() - 1));
			bpc.setIs_del(0);

			List<BasePdClass> bpcList = super.getFacade()
					.getBasePdClassService().getBasePdClassList(bpc);

			if (StringUtils.isBlank(bpcList_string)) {
				bpcList_string = "";
				if (null != bpcList && bpcList.size() > 0) {
					for (BasePdClass temp : bpcList) {
						bpcList_string += temp.getCls_name() + "-" + temp.getCls_id() + ";";
					}
				}

				if (StringUtils.isNotBlank(bpcList_string)) {
					request.setAttribute("bpcList_string", bpcList_string.substring(0, bpcList_string.length() - 1));
				}
			}

			String brandList_string = "";
			MvClsidJoinBrandXxhx mvbx = new MvClsidJoinBrandXxhx();
			mvbx.getMap().put("cls_ids_par",cls_ids_select.substring(0, cls_ids_select.length() - 1));

			List<MvClsidJoinBrandXxhx> mvbxList = super.getFacade().getMvClsidJoinBrandXxhxService().getBrandsByClsidsXxhxList(mvbx);

			if (StringUtils.isEmpty(brand_ids_string)) {
				brand_ids_string = brand_ids_select;
			}
			if (null != mvbxList && mvbxList.size() > 0) {
				for (MvClsidJoinBrandXxhx temp : mvbxList) {
					brandList_string += temp.getBrand_name() + "-" + temp.getBrand_id();
					if (("," + brand_ids_string).indexOf("," + temp.getBrand_id() + ",") >= 0) {
						brandList_string += "selected";
					}
					brandList_string += ";";
				}
			}

			if (StringUtils.isNotBlank(brandList_string)) {
				request.setAttribute("brandList_string", brandList_string.substring(0, brandList_string.length() - 1));

				brand_ids_select = brand_ids_string;
				request.setAttribute("brand_ids_select", brand_ids_select);
			}
		}

		// 取base_pd_type的大类
		BasePdType basePdType = new BasePdType();
		basePdType.setIs_model(new Short("1"));
		basePdType.setDel_mark(new Short("0"));

		List<BasePdType> basePdTypeList = super.getFacade().getBasePdTypeService().getBasePdTypeList(basePdType);

		request.setAttribute("basePdTypeList", basePdTypeList);

		// 初始化年月 （2010.8----今）
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		Date today = new Date();
		String today_string = df.format(today);

		request.setAttribute("now_year", today_string.split("-")[0]);
		request.setAttribute("now_month", today_string.split("-")[1]);

		// 初始化 "更多条件"的checkbox
		if (StringUtils.isNotBlank(more)) {
			request.setAttribute("more", "checked");
		}

		// 处理分页回显网点类别checkbox
		/*if (StringUtils.isNotBlank(shop_types_str)) {
			String[] array = new String[5];
			int i = 0;
			for (String str : StringUtils.split(shop_types_str, ",")) {
				array[i++] = str;
			}
			shop_types = array;
		}

		// 网点类别checkboxcheckBox回显
		if (null != shop_types && shop_types.length > 0) {
			request.setAttribute("shop_types_str", StringUtils.join(shop_types, ","));
		}	
		*/
		// 没有输入查询条件默认不进行查询
		if (StringUtils.isEmpty(province) && StringUtils.isEmpty(city) && StringUtils.isEmpty(country) && StringUtils.isEmpty(town)
				&& StringUtils.isEmpty(cls_ids_select)/*&& (null == shop_types || shop_types.length == 0)*/
				&& StringUtils.isEmpty(year) && StringUtils.isEmpty(month)
				&& StringUtils.isEmpty(shop_name_like)) {

			return null;
		}

		// 查询结果集
		EntpShop entpShop = new EntpShop();

		if (StringUtils.isNotBlank(cls_ids)
				|| StringUtils.isNotBlank(cls_ids_select)
				|| StringUtils.isNotBlank(brand_ids_string)) {
			entpShop.getMap().put("cls_brand_not_null", true);
			if (StringUtils.isNotBlank(cls_ids_select)) {
				entpShop.getMap().put("cls_ids",cls_ids_select.substring(0, cls_ids_select.length() - 1).split(","));
			} else if (StringUtils.isNotBlank(cls_ids)) {
				entpShop.getMap().put("cls_ids",cls_ids.substring(0, cls_ids.length() - 1).split(","));
			}
			if (StringUtils.isNotBlank(brand_ids_string)) {
				entpShop.getMap().put("brand_ids",brand_ids_string.substring(0,brand_ids_string.length() - 1).split(","));
			}
		}
		if (!StringUtils.isBlank(town)) {
			entpShop.getMap().put("reg_info_not_null", true);
			entpShop.getMap().put("reg_p_index_par", (Long.valueOf(town)));
		} else if (StringUtils.isBlank(town) && !StringUtils.isBlank(country)) {
			entpShop.getMap().put("p_index_par", (Long.valueOf(country)));
		} else if (StringUtils.isBlank(town) && StringUtils.isBlank(country) 
				&& !StringUtils.isBlank(city)) {
			entpShop.getMap().put("p_index_par", (Long.valueOf(city)));
		} else if (StringUtils.isBlank(town) && StringUtils.isBlank(country) 
				&& StringUtils.isBlank(city) && !StringUtils.isBlank(province)) {
			entpShop.getMap().put("p_index_par", (Long.valueOf(province)));
		} else if (StringUtils.isBlank(town) && StringUtils.isBlank(country) 
				&& StringUtils.isBlank(city) && StringUtils.isBlank(province) 
				&& StringUtils.isNotBlank(p_indexs)) {
			entpShop.getMap().put("p_indexs_par", p_indexs.split(","));
		}

		if (StringUtils.isNotBlank(more) && StringUtils.isNotBlank(year)
				&& StringUtils.isNotBlank(month)) {
			if (StringUtils.isNotBlank(pd_type) && (StringUtils.isNotBlank(busi_total1) || StringUtils.isNotBlank(busi_proportion1))) {
				entpShop.getMap().put("pd_type_sell_info_not_null", true);
				entpShop.getMap().put("pd_type", pd_type);
				entpShop.getMap().put("yearMon", year + month);
				if (StringUtils.isNotBlank(busi_total1)) {
					entpShop.getMap().put("busi_total1", busi_total1);
				}
				if (StringUtils.isNotBlank(busi_proportion1)) {
					entpShop.getMap().put("busi_proportion1", busi_proportion1);
				}
			}
			if (StringUtils.isNotBlank(brand_id) 
					&& (StringUtils.isNotBlank(busi_total2) || StringUtils.isNotBlank(busi_proportion2))) {
				entpShop.getMap().put("brand_sell_info_not_null", true);
				entpShop.getMap().put("brand_id", brand_id);
				entpShop.getMap().put("yearMon", year + month);
				if (StringUtils.isNotBlank(busi_total2)) {
					entpShop.getMap().put("busi_total2", busi_total2);
				}
				if (StringUtils.isNotBlank(busi_proportion2)) {
					entpShop.getMap().put("busi_proportion2", busi_proportion2);
				}
			}
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
		setShopDevelopStatus(entityList,null);
		if ("0".equals(page_type)) {
			if (recordCount > 0L) {
				request.setAttribute("have_data", "1");
				request.setAttribute("entityList", entityList);
			} else {
				request.setAttribute("have_data", "0");
			}
			request.getSession().setAttribute("EntpShopSearch_entpShop", null);
		} else {
			request.getSession().setAttribute("EntpShopSearch_entpShop",entpShop);
			request.getSession().setAttribute("EntpShopSearch_count",recordCount);
			request.getSession().setAttribute("EntpShopSearch_list", entityList);
		}
		request.getSession().setAttribute("EntpShopSearch_ajaxEntpShop", null);

		return entityList;

	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		setNaviStringToRequestScope(form, request);
		getShopSearchList(mapping, form, request, response);

		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String shop_id = (String) dynaBean.get("shop_id");

		if (!GenericValidator.isLong(shop_id)) {
			saveError(request, "errors.long", new String[] { shop_id });
			return mapping.findForward("list");
		}

		EntpShop entity = new EntpShop();
		entity.setShop_id(Long.valueOf(shop_id));

		entity = super.getFacade().getEntpShopService().getEntpShop(entity);

		entity.getMap().put("p_index_name",super.getPIndexName(entity.getP_index().toString(), ""));

		MvShopPdtypeAndBrand mspb_cls = new MvShopPdtypeAndBrand();
		mspb_cls.setShop_id(Long.valueOf(shop_id));

		List<MvShopPdtypeAndBrand> mspb_clsList = super.getFacade().getMvShopPdtypeAndBrandService().getDistinctClsIdByShopIdList(mspb_cls);

		if (null != mspb_clsList && mspb_clsList.size() > 0) {
			String List_string = "";
			for (MvShopPdtypeAndBrand temp : mspb_clsList) {
				List_string += temp.getMap().get("cls_name") + ",";
			}
			entity.getMap().put("cls_names",List_string.substring(0, List_string.length() - 1));
		}

		MvShopPdtypeAndBrand mspb_brand = new MvShopPdtypeAndBrand();
		mspb_brand.setShop_id(Long.valueOf(shop_id));

		List<MvShopPdtypeAndBrand> mspb_brandList = super.getFacade().getMvShopPdtypeAndBrandService().getDistinctBrandIdByShopIdList(mspb_brand);

		if (null != mspb_brandList && mspb_brandList.size() > 0) {
			String List_string = "";
			for (MvShopPdtypeAndBrand temp : mspb_brandList) {
				List_string += temp.getMap().get("brand_name") + ",";
			}
			entity.getMap().put("brand_names",List_string.substring(0, List_string.length() - 1));
		}

		super.copyProperties(form, entity);

		return mapping.findForward("view");
	}

	/*
	 * 
	 * 品牌展示
	 */
	public ActionForward listBaseBrandInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String brand_name_like = (String) dynaBean.get("brand_name_like");

		BaseBrandInfo baseBrandInfo = new BaseBrandInfo();
		baseBrandInfo.getMap().put("brand_name_like", brand_name_like);
		baseBrandInfo.getMap().put("MMTDBUser", "chea_fill");
		baseBrandInfo.setIs_del(0);

		List<BaseBrandInfo> baseBrandInfoList = super.getFacade().getBaseBrandInfoService().getBaseBrandInfoList(baseBrandInfo);
		request.setAttribute("baseBrandInfoList", baseBrandInfoList);

		return new ActionForward("/../manager/admin/EntpShopSearch/list_basebrandinfo.jsp");
	}

	public ActionForward getBrandIdsByClsId(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String cls_ids = (String) dynaBean.get("cls_ids");
		if (StringUtils.isNotBlank(cls_ids)) {

			MvClsidJoinBrandXxhx entity = new MvClsidJoinBrandXxhx();
			entity.getMap().put("cls_ids_par", cls_ids);

			List<MvClsidJoinBrandXxhx> entityList = super.getFacade().getMvClsidJoinBrandXxhxService().getBrandsByClsidsXxhxList(entity);

			StringBuffer sb = new StringBuffer("{\"result\":\"");
			if (null != entityList && entityList.size() > 0) {
				for (MvClsidJoinBrandXxhx temp : entityList) {
					String str = temp.getBrand_name() + "-" + temp.getBrand_id();
					sb.append(str).append(";");
				}
			} else {
				sb.append("");
			}
			sb.append("\"}");
			super.render(response, sb.toString(), "text/x-json;charset=UTF-8");
		}
		return null;
	}

	public void ajaxGetPIndexNamesStr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
	 * 设定查询网点中开拓网点
	 * 
	 * */
	public void setShopDevelopStatus(List<EntpShop> list,String status){
		// 定义shop_id List
		List<Long> shop_id_List = new ArrayList<Long>();
		// 待开拓网点 <Shop_ID串,开拓状态>
		Map<Long,Long> dev_map = new HashMap<Long,Long>();
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				EntpShop shop = list.get(i);
				shop_id_List.add(shop.getShop_id());
			}
			
			// 网点开拓信息获取
			KonkaShopDevelop ksd = new KonkaShopDevelop();
			ksd.getMap().put("develop_not_in", status);
			ksd.getMap().put("shop_id_string", StringUtils.join(shop_id_List, ","));		
			List<KonkaShopDevelop> li_C= super.getFacade().getKonkaShopDevelopService().getKonkaShopDevelopList(ksd);
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
	public void ajaxGetEntpShopJson(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		EntpShop EntpShopSearch_entpShop = (EntpShop) super
				.getSessionAttribute(request, "EntpShopSearch_entpShop");
		List<EntpShop> entpShopList = null;
		Long allCount = null;
		if (EntpShopSearch_entpShop != null) {
			entpShopList = (List<EntpShop>) super.getSessionAttribute(request,"EntpShopSearch_list");
			allCount = (Long) super.getSessionAttribute(request,"EntpShopSearch_count");
			request.getSession().setAttribute("EntpShopSearch_ajaxEntpShop",EntpShopSearch_entpShop);

		} else {
			String firstRow = (String) dynaBean.get("firstRow");
			String pageSize = (String) dynaBean.get("pageSize");
			EntpShop ajaxEntpShop = (EntpShop) super.getSessionAttribute(request, "EntpShopSearch_ajaxEntpShop");

			// 全网点查找
			allCount = super.getFacade().getEntpShopService().getEntpShopInFindShopCount(ajaxEntpShop);
			ajaxEntpShop.getRow().setFirst(Integer.valueOf(firstRow) - 1);
			ajaxEntpShop.getRow().setCount(Integer.valueOf(pageSize));
			entpShopList = super.getFacade().getEntpShopService().getEntpShopInFindShopPaginatedList(ajaxEntpShop);

	        // 设定查询网点中待开拓网点，返回待开拓网点
			setShopDevelopStatus(entpShopList,null);
			
			request.getSession().setAttribute("EntpShopSearch_ajaxEntpShop",ajaxEntpShop);
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
		//String list_C_ids = null;
		
		if (entpShopList != null && entpShopList.size() > 0) {
			for (int i = 0; i < entpShopList.size(); i++) {
				EntpShop shop = entpShopList.get(i);
				shop_id_List.add(shop.getShop_id());
			}
			shop_ids = StringUtils.join(shop_id_List, ","); // 全网点MMT_Shop_ID串 shop_ids
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
			List<KonkaBranchCategory> Li_B = super.getFacade().getKonkaBranchCategoryService().getKonkaBranchCategoryList(kbc);

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
				String shop_develop_status = ""+shop.getMap().get("shop_develop_status");
				if("0".equals(shop_develop_status) || "1".equals(shop_develop_status)){
					shop.getMap().put("is_R3Shop", "3"); // 待开拓、开拓中网点
					list_C.add(shop);
				}else if(searchMatchId(list_B_ids, shop.getShop_id(), ",")){
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
			jsonBuffer.append("\"shop_name\":\"").append(replaceSpecialCharacter(es.getShop_name(), "\t|\r|\n")).append("\",");
			jsonBuffer.append("\"online_qq\":\"").append(es.getOnline_qq()).append("\",");
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

	public void ajaxGetEntpShopInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// DynaBean dynaBean = (DynaBean) form;
		// String shop_id = (String) dynaBean.get("shop_id");

		// 判断商铺 ID
		StringBuffer sb = new StringBuffer("{'result':'false'}");
		super.render(response, sb.toString(), "text/x-json;charset=UTF-8");
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

	private void setTransFormValue(DynaBean dynaBean, DynaBean back_form) {
		String keys[] = { "mod_id", "tree_param", "province", "city",
				"country", "town", "cls_id", "cls_ids_select",
				"shop_types_str", "more", "year", "month", "pd_type",
				"busi_total1", "busi_proportion1", "brand_id", "brand_name",
				"busi_total2", "busi_proportion2", "brand_ids_select" };

		for (int i = 0; i < keys.length; i++) {
			dynaBean.set(keys[i], back_form.get(keys[i]));
		}
	}
	
	public void ajaxShopDevelop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String pks_shop_id = (String) dynaBean.get("pks_shop_id");

		List<KonkaShopDevelop> shops = null;
		KonkaShopDevelop shopDevelop = new KonkaShopDevelop();
		// 回传显示串
		String ajax_status = "";
		StringBuffer jsonBuffer = new StringBuffer();
		if (StringUtils.isNotBlank(pks_shop_id)) {
			// 判断网点是否是待开拓网点
			shopDevelop.getMap().put("shop_id_string", pks_shop_id.replaceAll("_", ","));
			shops = super.getFacade().getKonkaShopDevelopService().getKonkaShopDevelopList(shopDevelop);

			if (shops != null && shops.size() > 0) {
				for (int i = 0; i < shops.size(); i++) {
					String pks[] = pks_shop_id.split("_");
					KonkaShopDevelop shop = shops.get(i);

					for (int j = 0; j < pks.length; j++) {
						if (shop.getShop_id().equals(Long.valueOf(pks[j]))) {
							jsonBuffer.append("{");
							jsonBuffer.append("\"shop_id\":\"").append(shop.getShop_id()).append("\",");
							jsonBuffer.append("\"shop_name\":\"").append(shop.getShop_name()).append("\"");
							jsonBuffer.append("},");
						}
					}
				}
				ajax_status = "\"ajax_status\":\"1\"";

			} else {
				// 开拓批量网点 或者一个网点
				// 获取登录用户 企业信息
				PeProdUser SxUserInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
				shopDevelop.setAdd_user_id(SxUserInfo.getId());
				shopDevelop.setDept_id(SxUserInfo.getDept_id());

				String pks[] = pks_shop_id.split("_");
				for (int i = 0; i < pks.length; i++) {
					shopDevelop.setShop_id(Long.valueOf(pks[i]));

					// 取shop信息
					MmtEntpShop konkashop = new MmtEntpShop();
					konkashop.setShop_id(Long.valueOf(pks[i]));
					konkashop = super.getFacade().getMmtEntpShopService().getMmtEntpShop(konkashop);
					// 页面回显
					jsonBuffer.append("{");
					jsonBuffer.append("\"shop_id\":\"").append(shopDevelop.getShop_id()).append("\",");
					jsonBuffer.append("\"shop_name\":\"").append(shopDevelop.getShop_name()).append("\"");
					jsonBuffer.append("},");
					// 开拓网点
					shopDevelop.setDevelop_status(Long.valueOf("0"));
					shopDevelop.setShop_name(konkashop.getShop_name());
					super.getFacade().getKonkaShopDevelopService().createKonkaShopDevelop(shopDevelop);
				}
				ajax_status = "\"ajax_status\":\"2\"";
			}
		} else {
			ajax_status = "\"ajax_status\":\"0\"";
		}
		String listStr = StringUtils.removeEnd(jsonBuffer.toString(), ",");
		StringBuffer json = new StringBuffer("{");
		json.append(ajax_status).append(",");
		json.append("\"list\" : [").append(listStr).append("]}");

		logger.info("JSON Output : [{}]", json.toString());

		super.render(response, json.toString(), "text/x-json;charset=UTF-8");

		return;
	}	
}
