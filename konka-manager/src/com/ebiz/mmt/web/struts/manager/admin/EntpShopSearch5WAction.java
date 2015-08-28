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
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.MmtEntpShop;
import com.ebiz.mmt.domain.KonkaShopDevelop;
import com.ebiz.mmt.domain.MvClsidJoinBrandXxhx;
import com.ebiz.mmt.domain.MvShopPdtypeAndBrand;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Cheng,Bing
 * @version 2011-09-30
 * @version 2011-10-20 (变更)
 */
public class EntpShopSearch5WAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public List<EntpShop> getShopSearchList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		
		Pager pager = (Pager) dynaBean.get("pager");
		String page_id = (String) dynaBean.get("page_id");
		String page_type = (String) dynaBean.get("page_type");

		if (page_id != null && "EntpShopSellAnalysis".equals(page_id)) {
			DynaBean EntpShopSearch_form = (DynaBean) super.getSessionAttribute(request, "EntpShopSearch");
			setTransFormValue(dynaBean, EntpShopSearch_form);
		}

		String shop_name_like = (String) dynaBean.get("shop_name_like");
		String mod_id = (String) dynaBean.get("mod_id");
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");

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
			String msg = super.getMessage(request, "login.prod.failed.param.lost");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		String p_indexs = "";
		String cls_ids = "";
		String brand_ids_string = "";
		
		// 获取登录用户 企业信息
		PeProdUser SxUserInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		if (null != brand_ids) {
			for (int i = 0; i < brand_ids.length; i++) {
				if (StringUtils.isNotBlank(brand_ids[i])) {
					brand_ids_string += brand_ids[i] + ",";
				}
			}
		}

		BasePdClazz basePdClass = new BasePdClazz();
		basePdClass.setIs_del(0);
		List<BasePdClazz> basePdClassList = getBasePdClazzList();

		request.setAttribute("basePdClassList", basePdClassList);
		
		// 产品类别和产品品牌回显
		if (StringUtils.isNotBlank(cls_ids_select)) {
			BasePdClass bpc = new BasePdClass();
			bpc.getMap().put("cls_id_in", cls_ids_select.substring(0, cls_ids_select.length() - 1));
			bpc.setIs_del(0);

			List<BasePdClass> bpcList = super.getFacade().getBasePdClassService().getBasePdClassList(bpc);

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
			mvbx.getMap().put("cls_ids_par", cls_ids_select.substring(0, cls_ids_select.length() - 1));

			List<MvClsidJoinBrandXxhx> mvbxList = super.getFacade().getMvClsidJoinBrandXxhxService()
					.getBrandsByClsidsXxhxList(mvbx);

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

		// 没有输入查询条件默认不进行查询
		if (StringUtils.isEmpty(province) && StringUtils.isEmpty(city) && StringUtils.isEmpty(country)
				&& StringUtils.isEmpty(town) && StringUtils.isEmpty(cls_ids_select) 
				&& StringUtils.isEmpty(year) && StringUtils.isEmpty(month)
				&& StringUtils.isEmpty(year) && StringUtils.isEmpty(month)
		        && StringUtils.isEmpty(shop_name_like)) {
			return null;
		}
		
		//根据所在分公司的管辖区域
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(SxUserInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_30_le_39 = false;
		boolean role_id_ge_40_le_59 = false;
		for (PeRoleUser peRoleUser: peRoleUserList) {
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() <= 39L) {
				role_id_ge_30_le_39 = true;
			}
			if (peRoleUser.getRole_id() >= 40L && peRoleUser.getRole_id() <= 59L) {
				role_id_ge_40_le_59 = true;
			}
		}
		
		// 受限部门
		KonkaDept dept = new KonkaDept();
		// 管辖区域P_INDEX串
		String m_p_index = null;
		if(role_id_ge_30_le_39){//分公司管理员或分公司领导
			dept.setDept_id(SxUserInfo.getDept_id());
			dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
			// 没有管辖区域不进行查询
			if(dept.getM_areas_ids() == null){
				return null;
			}
			m_p_index = dept.getM_areas_ids();
		}
		if(role_id_ge_40_le_59){//经营部或办事处
			dept = super.getSuperiorForDeptType(SxUserInfo.getDept_id(), 3);
			// 没有管辖区域不进行查询
			if(dept.getM_areas_ids() == null){
				return null;
			}
			m_p_index = dept.getM_areas_ids();
		}

		// 查询结果集
		EntpShop entpShop = new EntpShop();

		if (StringUtils.isNotBlank(cls_ids) || StringUtils.isNotBlank(cls_ids_select)
				|| StringUtils.isNotBlank(brand_ids_string)) {
			entpShop.getMap().put("cls_brand_not_null", true);
			if (StringUtils.isNotBlank(cls_ids_select)) {
				entpShop.getMap().put("cls_ids", cls_ids_select.substring(0, cls_ids_select.length() - 1).split(","));
			} else if (StringUtils.isNotBlank(cls_ids)) {
				entpShop.getMap().put("cls_ids", cls_ids.substring(0, cls_ids.length() - 1).split(","));
			}
			if (StringUtils.isNotBlank(brand_ids_string)) {
				entpShop.getMap().put("brand_ids",
						brand_ids_string.substring(0, brand_ids_string.length() - 1).split(","));
			}
		}
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


		if (StringUtils.isNotBlank(more) && StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
			if (StringUtils.isNotBlank(pd_type)
					&& (StringUtils.isNotBlank(busi_total1) || StringUtils.isNotBlank(busi_proportion1))) {
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

		// 管辖区域
		if(m_p_index != null){
		   entpShop.getMap().put("m_p_index", m_p_index);
		}
		
		entpShop.getMap().put("is_konka_shop", true);// 5.5W网点
		entpShop.getMap().put("konka_shop_not_r3", true);// 9000 网点，包括代理商
		entpShop.getMap().put("konka_shop_not_dl_and_jx", true); // 不能是经销商
		entpShop.getMap().put("is_developed", true); // 去除已开拓的网点

		Long recordCount = super.getFacade().getEntpShopService().getEntpShopInFindShopCount(entpShop);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		pager.setPageSize(26);
		entpShop.getRow().setFirst(pager.getFirstRow());
		entpShop.getRow().setCount(pager.getPageSize());

		List<EntpShop> entityList = super.getFacade().getEntpShopService().getEntpShopInFindShopPaginatedList(entpShop);
        // 区分开拓网点
		setShopDevelopStatus(entityList,"2");
		
		if ("0".equals(page_type)) {
			if (recordCount > 0L) {
				request.setAttribute("have_data", "1");
				request.setAttribute("entityList", entityList);
			} else {
				request.setAttribute("have_data", "0");
			}
			request.getSession().setAttribute("EntpShopSearch_entpShop", null);
		} else {
			request.getSession().setAttribute("EntpShopSearch_entpShop", entpShop);
			request.getSession().setAttribute("EntpShopSearch_count", recordCount);
			request.getSession().setAttribute("EntpShopSearch_list", entityList);
		}
		request.getSession().setAttribute("EntpShopSearch_ajaxEntpShop", null);

		return entityList;
	}
	
	/* 
	 * 设定查询网点中待开拓网点，返回待开拓网点
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
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);
		getShopSearchList(mapping, form, request, response);

		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
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

		entity.getMap().put("p_index_name", super.getPIndexName(entity.getP_index().toString(), ""));

		MvShopPdtypeAndBrand mspb_cls = new MvShopPdtypeAndBrand();
		mspb_cls.setShop_id(Long.valueOf(shop_id));

		List<MvShopPdtypeAndBrand> mspb_clsList = super.getFacade().getMvShopPdtypeAndBrandService()
				.getDistinctClsIdByShopIdList(mspb_cls);

		if (null != mspb_clsList && mspb_clsList.size() > 0) {
			String mspb_clsList_string = "";
			for (MvShopPdtypeAndBrand temp : mspb_clsList) {
				mspb_clsList_string += temp.getMap().get("cls_name") + ",";
			}
			entity.getMap().put("cls_names", mspb_clsList_string.substring(0, mspb_clsList_string.length() - 1));
		}

		MvShopPdtypeAndBrand mspb_brand = new MvShopPdtypeAndBrand();
		mspb_brand.setShop_id(Long.valueOf(shop_id));

		List<MvShopPdtypeAndBrand> mspb_brandList = super.getFacade().getMvShopPdtypeAndBrandService()
				.getDistinctBrandIdByShopIdList(mspb_brand);

		if (null != mspb_brandList && mspb_brandList.size() > 0) {
			String mspb_brandList_string = "";
			for (MvShopPdtypeAndBrand temp : mspb_brandList) {
				mspb_brandList_string += temp.getMap().get("brand_name") + ",";
			}
			entity.getMap().put("brand_names", mspb_brandList_string.substring(0, mspb_brandList_string.length() - 1));
		}

		super.copyProperties(form, entity);

		return mapping.findForward("view");
	}

	/*
	 * 品牌展示
	 */
	public ActionForward listBaseBrandInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String brand_name_like = (String) dynaBean.get("brand_name_like");

		BaseBrandInfo baseBrandInfo = new BaseBrandInfo();
		baseBrandInfo.getMap().put("brand_name_like", brand_name_like);
		baseBrandInfo.getMap().put("MMTDBUser", "chea_fill");
		baseBrandInfo.setIs_del(0);

		List<BaseBrandInfo> baseBrandInfoList = super.getFacade().getBaseBrandInfoService()
				.getBaseBrandInfoList(baseBrandInfo);
		request.setAttribute("baseBrandInfoList", baseBrandInfoList);

		return new ActionForward("/../manager/admin/EntpShopSearch/list_basebrandinfo.jsp");
	}

	public ActionForward getBrandIdsByClsId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String cls_ids = (String) dynaBean.get("cls_ids");
		if (StringUtils.isNotBlank(cls_ids)) {

			MvClsidJoinBrandXxhx entity = new MvClsidJoinBrandXxhx();
			entity.getMap().put("cls_ids_par", cls_ids);

			List<MvClsidJoinBrandXxhx> entityList = super.getFacade().getMvClsidJoinBrandXxhxService()
					.getBrandsByClsidsXxhxList(entity);

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

	@SuppressWarnings("unchecked")
	public void ajaxGetEntpShopJson(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		EntpShop EntpShopSearch_entpShop = (EntpShop) super.getSessionAttribute(request, "EntpShopSearch_entpShop");
		// 5.5万网点--9000网点、已分配代理商的网点或者是转换为R3用户的网点
		List<EntpShop> entpShopList = null;
		// 网点个数
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
			ajaxEntpShop.getRow().setFirst(Integer.valueOf(firstRow) - 1);
			ajaxEntpShop.getRow().setCount(Integer.valueOf(pageSize));
			entpShopList = super.getFacade().getEntpShopService().getEntpShopInFindShopPaginatedList(ajaxEntpShop);

			request.getSession().setAttribute("EntpShopSearch_ajaxEntpShop", ajaxEntpShop);
		}
		request.getSession().setAttribute("EntpShopSearch_entpShop", null);
	
		// 定义shop_id序列串
		String shop_list_ids = "";
		List<Long> id_List = new ArrayList<Long>();
				
		StringBuffer jsonBuffer = new StringBuffer();
		for (EntpShop es : entpShopList) {
			id_List.add(es.getShop_id());
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
			jsonBuffer.append("\"g_lng\":\"").append(es.getG_lng()).append("\"");

			// 直营网点
			jsonBuffer.append(",\"is_R3Shop\":\"").append(es.getMap().get("is_R3Shop")).append("\"");

			jsonBuffer.append("},");
		}
		shop_list_ids = StringUtils.join(id_List, ",");
		
		String listStr = StringUtils.removeEnd(jsonBuffer.toString(), ",");
		StringBuffer json = new StringBuffer("{");
		json.append("\"list\" : [").append(listStr).append("],");
		json.append("\"count\":\"").append(allCount).append("\",");
		json.append("\"shop_list_ids\":\"").append(shop_list_ids).append("\"");	
			
		json.append("}");
		logger.info("JSON Output : [{}]", json.toString());

		super.render(response, json.toString(), "text/x-json;charset=UTF-8");
		return;
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
		String keys[] = { "mod_id", "tree_param", "province", "city", "country", "town", "cls_id", "cls_ids_select",
				"chain_types_str", "more", "year", "month", "pd_type", "busi_total1", "busi_proportion1", "brand_id",
				"brand_name", "busi_total2", "busi_proportion2", "brand_ids_select" };

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
