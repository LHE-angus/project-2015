package com.ebiz.mmt.web.struts.manager.admin;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BasePdType;
import com.ebiz.mmt.domain.EntpShop;
import com.ebiz.mmt.domain.JBasePartner;
import com.ebiz.mmt.domain.KonkaBranchCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaShopDevelop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.StringHelper;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * 代理商网点 网点列表
 * 
 * @author Wang Hao
 */
public class AgentsListAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		String fgs_dept_id = (String) dynaBean.get("fgs_dept_id");
		String jyb_dept_id = (String) dynaBean.get("jyb_dept_id");
		String bsc_dept_id = (String) dynaBean.get("bsc_dept_id");
		String ywy_user_id = (String) dynaBean.get("ywy_user_id");
		String r3_code_like = (String) dynaBean.get("r3_code_like");

		KonkaR3Shop R3Shop = getKonkaR3ShopForSelect(mapping, form, request, response, null);
		R3Shop.getMap().put("is_agents", true);

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		// PeRoleUser _peRoleUser = new PeRoleUser();
		// _peRoleUser.setUser_id(userInfo.getId());
		// List<PeRoleUser> peRoleUserList =
		// this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		//
		// boolean role_id_le_29 = false; // 是否是管理人员
		// boolean role_id_ge_30_le_59 = false; // 是否是分公司人员
		// boolean role_id_ge_60 = false; // 是否是业务员
		//
		// for (PeRoleUser peRoleUser : peRoleUserList) {
		// if (peRoleUser.getRole_id() <= 29)
		// role_id_le_29 = true;
		// if (peRoleUser.getRole_id() >= 30 && peRoleUser.getRole_id() <= 59)
		// role_id_ge_30_le_59 = true;
		// if (peRoleUser.getRole_id() == 60)
		// role_id_ge_60 = true;
		// }
		//
		// // KonkaDept dept = new KonkaDept();
		// if (role_id_le_29) {
		//
		// }// else if (role_id_30_or_34) { // 分公司登陆
		// // dept.setDept_id(userInfo.getDept_id());
		// // dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
		// // if (StringUtils.isNotBlank(dept.getM_areas_names())) {
		// // R3Shop.getMap().put("branch_name_like", dept.getM_areas_names());
		// // } else {
		// // R3Shop.getMap().put("branch_name_like", "1");
		// // }
		// // R3Shop.getMap().put("branch_area_name_like", null);
		// // R3Shop.getMap().put("is_fengongsi", null);// is_fengongsi
		// // 语句用的是or，执行结果有问题
		// // R3Shop.getMap().put("is_jyb", "true");// 与is_fengongsi
		// // 的语句是一样的，但用的是and，结果应该是正确的
		// // }
		// else if (role_id_ge_30_le_59) {
		// R3Shop.getMap().put("is_fgs_user", userInfo.getId());
		// if (userInfo.getDept_id() != null)
		// R3Shop.getMap().put("is_fgs_dept", userInfo.getDept_id());
		// } else if (role_id_ge_60) {
		// R3Shop.getMap().put("is_fgs_user", userInfo.getId());
		// R3Shop.getMap().put("is_ywy_user", userInfo.getId());
		// } else {
		// super.renderHtml(response, "无权访问，无系统权限，请联系管理员授予系统角色。");
		// return null;
		// }

		request.setAttribute("sybDeptInfoList", super.getDeptInfoList(mapping, form, request, response, null));

		// 数据级别判断开始
		Long __dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见

			List<KonkaDept> dList = new ArrayList<KonkaDept>();

			KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				R3Shop.getMap().put("dept_id_start", __dept_id);
				dynaBean.set("fgs_dept_id", __dept_id.toString());
				dList.add(dept_fgs);
				R3Shop.getMap().put("dept_id", __dept_id);
			}
			request.setAttribute("sybDeptInfoList", dList);
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			R3Shop.getMap().put("dept_id_start", __dept_id);
			break;
		case 0:
			__dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			// R3Shop.getMap().put("dept_id_start", __dept_id);
			R3Shop.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
			break;
		default:
			// 出错
		}
		R3Shop.getMap().put("session_user_id", userInfo.getId());
		// 数据级别判断结束

		if (GenericValidator.isLong(fgs_dept_id)) {
			R3Shop.getMap().put("konka_dept_id", fgs_dept_id);
		}
		if (GenericValidator.isLong(jyb_dept_id)) {
			R3Shop.getMap().put("konka_dept_id", jyb_dept_id);
		}
		if (GenericValidator.isLong(bsc_dept_id)) {
			R3Shop.getMap().put("konka_dept_id", bsc_dept_id);
		}
		if (GenericValidator.isLong(ywy_user_id)) {
			R3Shop.getMap().put("ywy_user_id", ywy_user_id);
		}
		if (StringUtils.isNotBlank(r3_code_like)) {
			R3Shop.getMap().put("r3_code_like", r3_code_like);
		}
		Long recordCount = getFacade().getKonkaR3ShopService().getKonkaR3ShopCount(R3Shop);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		R3Shop.getRow().setFirst(pager.getFirstRow());
		R3Shop.getRow().setCount(pager.getRowCount());
		List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService().getKonkaR3ShopPaginatedList(R3Shop);

		super.setBranchNameForR3ShopListByKonkaR3ShopList(entityList);

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	/*
	 * 代理商下的网点
	 */
	public ActionForward jxslist(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		String r3_id = (String) dynaBean.get("r3_id");
		String jxs_name_like = (String) dynaBean.get("jxs_name_like");

		KonkaBranchCategory kbc = new KonkaBranchCategory();
		kbc.setKonka_r3_id(Long.valueOf(r3_id));
		kbc.setCategory_id(20L);
		if (StringUtils.isNotBlank(jxs_name_like)) {
			kbc.getMap().put("jxs_name_like", jxs_name_like);
		}

		Long recordCount = super.getFacade().getKonkaBranchCategoryService().getKonkaBranchCategoryCount(kbc);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		kbc.getRow().setFirst(pager.getFirstRow());
		kbc.getRow().setCount(pager.getRowCount());
		List<KonkaBranchCategory> entityList = super.getFacade().getKonkaBranchCategoryService()
				.getKonkaBranchCategoryPaginatedList(kbc);
		logger.info("entityList_____________" + entityList.size());

		for (int i = 0; i < entityList.size(); i++) {
			KonkaBranchCategory k = entityList.get(i);
			// MmtEntpShop kshop = new MmtEntpShop();
			// kshop.setShop_id(k.getMmt_shop_id());
			// kshop =
			// super.getFacade().getMmtEntpShopService().getMmtEntpShop(kshop);

			if (null != k.getJxs_r3_id()) {
				KonkaR3Shop s = new KonkaR3Shop();
				s.setId(k.getJxs_r3_id());
				s = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(s);
				k.getMap().put("dls_shops", s);
			}

		}

		request.setAttribute("entityList", entityList);

		return new ActionForward("/admin/AgentsList/jxslist.jsp");
	}

	public ActionForward AgentsDirectDevelop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String r3_id = (String) dynaBean.get("r3_id");
		KonkaR3Shop R3Shop = new KonkaR3Shop();
		R3Shop.setId(Long.valueOf(r3_id));
		R3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(R3Shop);
		request.setAttribute("R3Shop", R3Shop);
		setNaviStringToRequestScope(form, request);
		return mapping.findForward("input");
	}

	public ActionForward searchKonkaShop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);
		String r3_id = (String) dynaBean.get("r3_id");
		KonkaR3Shop R3Shop = new KonkaR3Shop();
		R3Shop.setId(Long.valueOf(r3_id));
		R3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(R3Shop);
		request.setAttribute("R3Shop", R3Shop);

		getShopSearchList(mapping, form, request, response);
		return mapping.findForward("input");
	}

	public List<EntpShop> getShopSearchList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");

		String shop_name_like = (String) dynaBean.get("shop_name_like");
		String mod_id = (String) dynaBean.get("mod_id");
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");

		if (StringUtils.isBlank(mod_id)) {
			String msg = super.getMessage(request, "login.prod.failed.param.lost");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 获取登录用户 企业信息
		PeProdUser SxUserInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		// 取base_pd_type的大类
		BasePdType basePdType = new BasePdType();
		basePdType.setIs_model(new Short("1"));
		basePdType.setDel_mark(new Short("0"));

		List<BasePdType> basePdTypeList = super.getFacade().getBasePdTypeService().getBasePdTypeList(basePdType);

		request.setAttribute("basePdTypeList", basePdTypeList);

		// 没有输入查询条件默认不进行查询
		if (StringUtils.isEmpty(province) && StringUtils.isEmpty(city) && StringUtils.isEmpty(country)
				&& StringUtils.isEmpty(town) && StringUtils.isEmpty(shop_name_like)) {
			return null;
		}

		// 根据所在分公司的管辖区域
		PeRoleUser roleUser = new PeRoleUser();
		roleUser.setUser_id(SxUserInfo.getId());
		roleUser = getFacade().getPeRoleUserService().getPeRoleUser(roleUser);

		// 受限部门
		KonkaDept dept = new KonkaDept();
		// 管辖区域P_INDEX串
		String m_p_index = null;
		if (roleUser.getRole_id() >= 30 && roleUser.getRole_id() <= 39) {// 分公司管理员或分公司领导
			dept.setDept_id(SxUserInfo.getDept_id());
			dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
			// 没有管辖区域不进行查询
			if (dept.getM_areas_ids() == null) {
				return null;
			}
			m_p_index = dept.getM_areas_ids();
		}
		if (roleUser.getRole_id() >= 40 && roleUser.getRole_id() <= 59) {// 经营部或办事处
			dept = super.getSuperiorForDeptType(SxUserInfo.getDept_id(), 3);
			// 没有管辖区域不进行查询
			if (dept.getM_areas_ids() == null) {
				return null;
			}
			m_p_index = dept.getM_areas_ids();
		}

		// 查询结果集
		EntpShop entpShop = new EntpShop();

		String p_indexs = "";
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


		// 管辖区域
		if (m_p_index != null) {
			entpShop.getMap().put("m_p_index", m_p_index);
		}

		entpShop.getMap().put("is_konka_shop", true);// 5.5W网点
		entpShop.getMap().put("konka_shop_not_r3", true);// 9000 网点，包括代理商
		entpShop.getMap().put("konka_shop_not_dl_and_jx", true); // 不能是经销商
		entpShop.getMap().put("is_developed", true); // 去除已开拓的网点

		Long recordCount = super.getFacade().getEntpShopService().getEntpShopInFindShopCount(entpShop);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entpShop.getRow().setFirst(pager.getFirstRow());
		entpShop.getRow().setCount(pager.getPageSize());

		List<EntpShop> entityList = super.getFacade().getEntpShopService().getEntpShopInFindShopPaginatedList(entpShop);
		// 区分开拓网点
		setShopDevelopStatus(entityList, "2");
		if (recordCount > 0L) {
			request.setAttribute("have_data", "1");
			request.setAttribute("entityList", entityList);
		} else {
			request.setAttribute("have_data", "0");
		}
		return entityList;
	}

	/*
	 * 设定查询网点中待开拓网点，返回待开拓网点
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

	public void ajaxShopDevelop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 经销商网点
		String pks_shop_id = (String) dynaBean.get("pks_shop_id");
		String dls_r3_id = (String) dynaBean.get("dls_r3_id");
		// 回传显示串
		String ajax_status = "";
		StringBuffer jsonBuffer = new StringBuffer();
		if (StringUtils.isNotBlank(pks_shop_id)) {
			String pks[] = pks_shop_id.split("_");
			for (int i = 0; i < pks.length; i++) {
				// 创建KonkaBranchCategory信息
				KonkaBranchCategory branchCategory = new KonkaBranchCategory();
				branchCategory.setKonka_r3_id(Long.valueOf(dls_r3_id));
				branchCategory.setMmt_shop_id(Long.valueOf(pks[i]));
				branchCategory.setCategory_id(Long.valueOf("20"));
				super.getFacade().getKonkaBranchCategoryService().createKonkaBranchCategory(branchCategory);

				// 页面回显
				jsonBuffer.append("{");
				jsonBuffer.append("\"shop_id\":\"").append(pks[i]).append("\"");
				jsonBuffer.append("},");
			}
			ajax_status = "\"ajax_status\":\"1\"";
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

	public ActionForward jxsShopList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String partner_name_like = (String) dynaBean.get("partner_name_like"); // 合作伙伴名称
		String r3_id = (String) dynaBean.get("r3_id");// 经销商
		String is_del = (String) dynaBean.get("is_del");

		if (!GenericValidator.isLong(r3_id)) {
			super.saveError(request, "errors.param");
			return new ActionForward("/admin/AgentsList/jxs_shop_list.jsp");
		}
		dynaBean.set("r3_id", r3_id);

		JBasePartner entity = new JBasePartner();
		entity.setC_id(Long.valueOf(r3_id));
		if (StringUtils.isNotBlank(partner_name_like)) {
			entity.getMap().put("partner_name_like", partner_name_like);
		}
		if (StringUtils.isNotBlank(is_del)) {
			entity.setIs_del(Integer.valueOf(is_del));
		} else {
			entity.setIs_del(0);
		}
		entity.setPartner_type(1);
		entity.setPartner_obj(1);
		entity.getMap().put("is_konka", r3_id);

		Long recordCount = super.getFacade().getJBasePartnerService().getJBasePartnerForLevelCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<JBasePartner> entityList = super.getFacade().getJBasePartnerService()
				.getJBasePartnerForLevelPaginatedList(entity);

		if (entityList.size() > 0) {
			for (JBasePartner temp : entityList) {
				if ("1".equals(temp.getMap().get("j_level").toString())) {
					temp.getMap().put("par_name", "无");
				} else {
					JBasePartner j = new JBasePartner();
					j.setPartner_id(temp.getPar_c_id());
					j = getFacade().getJBasePartnerService().getJBasePartner(j);
					if (null != j)
						temp.getMap().put("par_name", j.getPartner_name());
				}
			}
		}

		request.setAttribute("entityList", entityList);

		return new ActionForward("/admin/AgentsList/jxs_shop_list.jsp");
	}

	/**
	 * 根据网点记录新增
	 * @author Liang,HouEn
	 * 2014-10-29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addJxsShop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String r3_id = (String) dynaBean.get("r3_id");// 经销商
		String mod_id = (String) dynaBean.get("mod_id");// 节点
		
		String local_dept_id = (String) dynaBean.get("local_dept_id");  //所在分公司
		String dept_id = (String) dynaBean.get("dept_id");// 分公司
		String agent_name = java.net.URLDecoder.decode((String) dynaBean.get("agent_name"),"UTF-8");// 网点名称
		String agent_id = (String) dynaBean.get("agent_id");// 网点编号
		String agent_level = (String) dynaBean.get("agent_level");// 网点级别
		String r3_code = (String) dynaBean.get("r3_code");// 客户R3编码
		String kh_name = java.net.URLDecoder.decode((String) dynaBean.get("kh_name"),"UTF-8");// 上级客户名称
		String ywy_name_like = java.net.URLDecoder.decode((String) dynaBean.get("ywy_name_like"),"UTF-8");// 业务员
		String link_name = java.net.URLDecoder.decode((String) dynaBean.get("link_name"),"UTF-8");// 联系人
		String jb_name = java.net.URLDecoder.decode((String) dynaBean.get("jb_name"),"UTF-8");// 经办名称
		String is_stop = (String) dynaBean.get("is_stop");// 是否停用
		String cus_type1 = (String) dynaBean.get("cus_type1");// 一级客户类型
		String cus_type2 = (String) dynaBean.get("cus_type2");// 二级客户类型
		
		//传递查询条件参数
		String str = "dept_id="+dept_id+"&agent_name="+agent_name+"&agent_id="+agent_id+"&agent_level="+agent_level+"&r3_code="+r3_code+"&kh_name="+kh_name+
					 "&jb_name="+jb_name+"&is_stop="+is_stop+"&ywy_name_like="+ywy_name_like+"&link_name="+link_name+"&cus_type1="+cus_type1+"&cus_type2="+cus_type2;
		request.setAttribute("queryStr", str);

		if (!GenericValidator.isLong(r3_id)) {
			super.saveError(request, "errors.param");
			return new ActionForward("/admin/AgentsList_new/list.jsp");
		}

		request.setAttribute("r3_id", r3_id);
		request.setAttribute("mod_id", mod_id);
		request.setAttribute("local_dept_id", local_dept_id);

		//登录人权限
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		PeRoleUser roleUser = new PeRoleUser();
		roleUser.setUser_id(ui.getId());
		List<PeRoleUser> roleUserList = super.getFacade().getPeRoleUserService().getPeRoleUserList(roleUser);
		
		// 回显该门店所在分公司所有的促销员，不包括已经拥有的
		Boolean role_id_gt_30_btw_200_300 = false;
		// Boolean role_id_eq_10 = false;
		if (roleUserList.size() > 0) {
			for (PeRoleUser temp : roleUserList) {
				if (temp.getRole_id() < 30 || (temp.getRole_id() > 200 && temp.getRole_id() < 300)) {
					role_id_gt_30_btw_200_300 = true;
				}
				// if (temp.getRole_id() == 10L) {
				// role_id_eq_10 = true;
				// request.setAttribute("is_admin", "1");
				// }
			}

		}
		if (role_id_gt_30_btw_200_300) {
			// 部门列表
			KonkaDept konka_dept = new KonkaDept();
			konka_dept.setPar_id(0L);
			List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konka_dept);
			request.setAttribute("is_admin", "1");
			request.setAttribute("peDeptList", deptInfoList);
		} else {
			KonkaDept peDept = new KonkaDept();
			peDept.setDept_type(3);
			peDept.setDept_id(ui.getDept_id());
			List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService().getKonkaDeptByDeptId(peDept);
			request.setAttribute("peDeptList", deptInfoList);
		}
		
		//创建日期
		Date today = new Date();
		dynaBean.set("add_date", today);
		
		request.setAttribute("pList", getEntityList(Long.valueOf(r3_id)));
		dynaBean.set("par_c_id", r3_id);
		request.setAttribute("addormodify", "0");

		return new ActionForward("/admin/AgentsList/add_jxs_shop.jsp");
	}

	/**
	 * 保存网点
	 * @author Liang,HouEn
	 * 2014-9-12
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveJxs(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String partner_id = (String) dynaBean.get("partner_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String partner_user_id = (String) dynaBean.get("partner_user_id");
		String user_name = (String) dynaBean.get("user_name");
		String pass_word = (String) dynaBean.get("pass_word");
		String first_agent = (String) dynaBean.get("first_agent");// 上级客户编码
		
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");
		String addormodify = (String) dynaBean.get("addormodify");  //新增还是修改标识
		String queryStr = (String) dynaBean.get("queryStr");  //从查询页面传递过来的参数
		
		JBasePartner entity = new JBasePartner();
		super.copyProperties(entity, form);
		
		if (StringUtils.isNotBlank(user_name)) {
			entity.getMap().put("user_name", user_name);
		}
		if (StringUtils.isNotBlank(pass_word)) {
			entity.getMap().put("pass_word", pass_word);
		}
		if (StringUtils.isNotBlank(first_agent)) {
			entity.setPar_c_id(Long.valueOf(first_agent));
		}else{
			entity.setPar_c_id(entity.getC_id());
		}
		
		//当前登录人信息
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		entity.getMap().put("current_user_id", ui.getId());
		
		//设置所在城市中的最后维度的代码
		if (StringUtils.isNotBlank(town)) {
			entity.setArea_code(town);
		} else if (StringUtils.isNotBlank(country)) {
			entity.setArea_code(country);
		} else if (StringUtils.isNotBlank(city)) {
			entity.setArea_code(city);
		} else if (StringUtils.isNotBlank(province)) {
			entity.setArea_code(province);
		}
		
		if (GenericValidator.isLong(partner_id)) {  //修改网点
			JBasePartner jBasePartner = new JBasePartner();
			jBasePartner.setPartner_id(Long.valueOf(partner_id));
			jBasePartner = super.getFacade().getJBasePartnerService().getJBasePartner(jBasePartner);
			
			//2014-12-11  LiangHouEn 记录维护人信息 
			entity.setModify_date(new Date());
			entity.setModify_user_id(ui.getId());
			
			if (jBasePartner.getPartner_type() == 1 && jBasePartner.getPartner_obj() == 1
					&& StringUtils.isNotBlank(partner_user_id)) {
				super.getFacade().getKonkaR3ShopService().modifyKonkaR3ShopWithJBasePartner(entity);
			} else {
				if (entity.getPartner_c_id() == null) {
					entity.getMap().put("set_partner_c_id_null", true);
				}
				super.getFacade().getJBasePartnerService().modifyJBasePartner(entity);
			}
		} else {     //新增网点
			entity.setPartner_type(1);
			entity.setPartner_obj(1);
			
			//2014-12-11  LiangHouEn 记录创建人信息 
			entity.setAdd_date(new Date());
			entity.setCreate_user_id(ui.getId());
			
			Long id = super.getFacade().getKonkaR3ShopService().createKonkaR3ShopWithJBasePartner(entity);
			partner_id = String.valueOf(id);
		}

		request.setAttribute("mod_id", mod_id);
		request.setAttribute("flag", addormodify);
		request.setAttribute("queryStr", queryStr);
		//返回标识
		request.setAttribute("return_flag", "1");
		return new ActionForward("/admin/AgentsList_new/list.jsp");
	}

	public ActionForward validateName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		StringBuffer oper = new StringBuffer("{\"result\":");
		String user_name = (String) dynaBean.get("user_name");
		PeProdUser entity = new PeProdUser();
		entity.setUser_name(user_name);
		// entity.setIs_del(0);
		List<PeProdUser> entityList = super.getFacade().getPeProdUserService().getPeProdUserList(entity);
		if (entityList.size() == 0) {
			oper.append(false);
		} else {
			oper.append(true);
		}
		oper.append("}");
		super.render(response, oper.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String partner_id = (String) dynaBean.get("partner_id");
		String r3_id = (String) dynaBean.get("r3_id");
		String mod_id = (String) dynaBean.get("mod_id");
		
		String dept_id = (String) dynaBean.get("dept_id");// 分公司
		String agent_name = java.net.URLDecoder.decode((String) dynaBean.get("agent_name"),"UTF-8");// 网点名称
		String agent_id = (String) dynaBean.get("agent_id");// 网点编号
		String agent_level = (String) dynaBean.get("agent_level");// 网点级别
		String r3_code = (String) dynaBean.get("r3_code");// 客户R3编码
		String kh_name = java.net.URLDecoder.decode((String) dynaBean.get("kh_name"),"UTF-8");// 上级客户名称
		String ywy_name_like = java.net.URLDecoder.decode((String) dynaBean.get("ywy_name_like"),"UTF-8");// 业务员
		String link_name = java.net.URLDecoder.decode((String) dynaBean.get("link_name"),"UTF-8");// 联系人
		String jb_name = java.net.URLDecoder.decode((String) dynaBean.get("jb_name"),"UTF-8");// 经办名称
		String is_stop = (String) dynaBean.get("is_stop");// 是否停用
		String cus_type1 = (String) dynaBean.get("cus_type1");// 一级客户类型
		String cus_type2 = (String) dynaBean.get("cus_type2");// 二级客户类型
		
		//传递查询条件参数
		String str = "dept_id="+dept_id+"&agent_name="+agent_name+"&agent_id="+agent_id+"&agent_level="+agent_level+"&r3_code="+r3_code+"&kh_name="+kh_name+
					 "&jb_name="+jb_name+"&is_stop="+is_stop+"&ywy_name_like="+ywy_name_like+"&link_name="+link_name+"&cus_type1="+cus_type1+"&cus_type2="+cus_type2;
		request.setAttribute("queryStr", str);

//		if (!GenericValidator.isLong(partner_id) || !GenericValidator.isLong(r3_id)) {
//			return new ActionForward("/admin/AgentsList_new/list.jsp");
//		}

		request.setAttribute("r3_id", r3_id);
		request.setAttribute("mod_id", mod_id);

		JBasePartner entity = new JBasePartner();
		entity.setPartner_id(Long.valueOf(partner_id));
		entity = super.getFacade().getJBasePartnerService().getJBasePartner(entity);
		entity.setQueryString(super.serialize(request, "partner_id", "method"));
		super.copyProperties(form, entity);

		Long partner_c_id = entity.getPartner_c_id();
		if (null != partner_c_id) {
			KonkaR3Shop s = new KonkaR3Shop();
			s.setId(partner_c_id);
			s = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(s);

			if (null != s) {
				// 保证客户实体信息存在
				PeProdUser user = new PeProdUser();
				user.setCust_id(partner_c_id);
				List<PeProdUser> userList = super.getFacade().getPeProdUserService().getPeProdUserList(user);
				// user =
				// super.getFacade().getPeProdUserService().getPeProdUser(user);
				if (userList.size() > 0)
					request.setAttribute("partner_user_info", userList.get(0));
			}
		}

		return new ActionForward("/admin/AgentsList/view_jxs_shop.jsp");
	}

	/**
	 * 修改网点信息
	 * @author Liang,HouEn
	 * 2014-9-2
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String partner_id = (String) dynaBean.get("partner_id");
		String local_dept_id = (String) dynaBean.get("local_dept_id");
		String r3_id = (String) dynaBean.get("r3_id");
		String mod_id = (String) dynaBean.get("mod_id");// 节点
		String dept_id = (String) dynaBean.get("dept_id");// 分公司
		String agent_name = java.net.URLDecoder.decode((String) dynaBean.get("agent_name"),"UTF-8");// 网点名称
		String agent_id = (String) dynaBean.get("agent_id");// 网点编号
		String agent_level = (String) dynaBean.get("agent_level");// 网点级别
		String r3_code = (String) dynaBean.get("r3_code");// 客户R3编码
		String kh_name = java.net.URLDecoder.decode((String) dynaBean.get("kh_name"),"UTF-8");// 上级客户名称
		String ywy_name_like = java.net.URLDecoder.decode((String) dynaBean.get("ywy_name_like"),"UTF-8");// 业务员
		String link_name = java.net.URLDecoder.decode((String) dynaBean.get("link_name"),"UTF-8");// 联系人
		String jb_name = java.net.URLDecoder.decode((String) dynaBean.get("jb_name"),"UTF-8");// 经办名称
		String is_stop = (String) dynaBean.get("is_stop");// 是否停用
		String cus_type1 = (String) dynaBean.get("cus_type1");// 一级客户类型
		String cus_type2 = (String) dynaBean.get("cus_type2");// 二级客户类型

		if (!GenericValidator.isLong(partner_id) || !GenericValidator.isLong(r3_id)) {
			super.saveError(request, "errors.param");
			return new ActionForward("/admin/AgentsList_new/list.jsp");
		}

		request.setAttribute("r3_id", r3_id);
		request.setAttribute("mod_id", mod_id);
		request.setAttribute("local_dept_id", local_dept_id);
		
		//传递查询条件参数
		String str = "dept_id="+dept_id+"&agent_name="+agent_name+"&agent_id="+agent_id+"&agent_level="+agent_level+"&r3_code="+r3_code+"&kh_name="+kh_name+
					 "&jb_name="+jb_name+"&is_stop="+is_stop+"&ywy_name_like="+ywy_name_like+"&link_name="+link_name+"&cus_type1="+cus_type1+"&cus_type2="+cus_type2;
		request.setAttribute("queryStr", str);

		JBasePartner entity = new JBasePartner();
		entity.setPartner_id(Long.valueOf(partner_id));
		entity = super.getFacade().getJBasePartnerService().getJBasePartner(entity);
		
		entity.setQueryString(super.serialize(request, "partner_id", "method"));
		super.copyProperties(form, entity);
		
		//判断是否是一级网点
		if (entity.getC_id() != null && entity.getPar_c_id() != null && entity.getP_level()>1) {
			request.setAttribute("first_agent", entity.getPar_c_id());
		}
		
		Long partner_c_id = entity.getPartner_c_id();
		if (null != partner_c_id) {
			KonkaR3Shop s = new KonkaR3Shop();
			s.setId(partner_c_id);
			s = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(s);

			if (null != s) {
				// 保证客户实体信息存在
				PeProdUser user = new PeProdUser();
				user.setCust_id(partner_c_id);
				List<PeProdUser> userList = super.getFacade().getPeProdUserService().getPeProdUserList(user);
				// user =
				// super.getFacade().getPeProdUserService().getPeProdUser(user);
				if (userList.size() > 0)
					request.setAttribute("partner_user_info", userList.get(0));
			}
		}

		//初始化所在城市
		if (null != entity.getArea_code() && String.valueOf(entity.getArea_code()).length() >= 6) {
			request.setAttribute("province", String.valueOf(entity.getArea_code()).substring(0, 2) + "0000");
			request.setAttribute("city", String.valueOf(entity.getArea_code()).substring(0, 4) + "00");
			request.setAttribute("country", String.valueOf(entity.getArea_code()).substring(0, 6));
			request.setAttribute("town", String.valueOf(entity.getArea_code()));
		}
		
		//登录人权限
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		PeRoleUser roleUser = new PeRoleUser();
		roleUser.setUser_id(ui.getId());
		List<PeRoleUser> roleUserList = super.getFacade().getPeRoleUserService().getPeRoleUserList(roleUser);
		
		// 回显该门店所在分公司所有的促销员，不包括已经拥有的
		Boolean role_id_gt_30_btw_200_300 = false;
		// Boolean role_id_eq_10 = false;
		if (roleUserList.size() > 0) {
			for (PeRoleUser temp : roleUserList) {
				if (temp.getRole_id() < 30 || (temp.getRole_id() > 200 && temp.getRole_id() < 300)) {
					role_id_gt_30_btw_200_300 = true;
				}
				// if (temp.getRole_id() == 10L) {
				// role_id_eq_10 = true;
				// request.setAttribute("is_admin", "1");
				// }
			}

		}
		if (role_id_gt_30_btw_200_300) {
			// 部门列表
			KonkaDept konka_dept = new KonkaDept();
			konka_dept.setPar_id(0L);
			List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konka_dept);
			request.setAttribute("is_admin", "1");
			request.setAttribute("peDeptList", deptInfoList);
		} else {
			KonkaDept peDept = new KonkaDept();
			peDept.setDept_type(3);
			peDept.setDept_id(ui.getDept_id());
			List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService().getKonkaDeptByDeptId(peDept);
			request.setAttribute("peDeptList", deptInfoList);
		}
		 
		request.setAttribute("addormodify", "1");
		return new ActionForward("/admin/AgentsList/add_jxs_shop.jsp");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String partner_id = (String) dynaBean.get("partner_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String r3_id = (String) dynaBean.get("r3_id");

		if (!GenericValidator.isLong(partner_id) || !GenericValidator.isLong(r3_id)) {
			super.saveError(request, "errors.param");
			return new ActionForward("/admin/AgentsList/jxs_shop_list.jsp");
		}

		// 停止用户信息
		String c_ids = partner_id;
		JBasePartner jbp = new JBasePartner();
		jbp.setPartner_id(Long.valueOf(partner_id));
		List<JBasePartner> jbpList = super.getFacade().getJBasePartnerService().getJBasePartnerList(jbp);
		if (jbpList.size() > 0) {
			for (JBasePartner jj : jbpList) {
				c_ids = c_ids + "," + jj.getPartner_c_id();
			}
		}
		PeProdUser pUser = new PeProdUser();
		// pUser.setCust_id(Long.valueOf(id));
		pUser.getMap().put("c_ids", c_ids);
		pUser.setIs_del(1);
		super.getFacade().getPeProdUserService().modifyPeProdUser(pUser);

		JBasePartner entity = new JBasePartner();
		entity.setPartner_id(Long.valueOf(partner_id));
		super.getFacade().getJBasePartnerService().removeJBasePartner(entity);

		saveMessage(request, "konka.close.success");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append("AgentsList.do");
		pathBuffer.append("?method=jxsShopList");
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&r3_id=" + r3_id);
		pathBuffer.append("&");

		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward reStart(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String partner_id = (String) dynaBean.get("partner_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String r3_id = (String) dynaBean.get("r3_id");

		if (!GenericValidator.isLong(partner_id) || !GenericValidator.isLong(r3_id)) {
			super.saveError(request, "errors.param");
			return new ActionForward("/admin/AgentsList/jxs_shop_list.jsp");
		}

		// 停止用户信息
		String c_ids = partner_id;
		JBasePartner jbp = new JBasePartner();
		jbp.setPartner_id(Long.valueOf(partner_id));
		List<JBasePartner> jbpList = super.getFacade().getJBasePartnerService().getJBasePartnerList(jbp);
		if (jbpList.size() > 0) {
			for (JBasePartner jj : jbpList) {
				c_ids = c_ids + "," + jj.getPartner_c_id();
			}
		}
		PeProdUser pUser = new PeProdUser();
		// pUser.setCust_id(Long.valueOf(id));
		pUser.getMap().put("c_ids", c_ids);
		pUser.setIs_del(0);
		super.getFacade().getPeProdUserService().modifyPeProdUser(pUser);

		JBasePartner entity = new JBasePartner();
		entity.setPartner_id(Long.valueOf(partner_id));
		entity.setIs_del(0);
		super.getFacade().getJBasePartnerService().modifyJBasePartner(entity);

		saveMessage(request, "konka.restart.success");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append("AgentsList.do");
		pathBuffer.append("?method=jxsShopList");
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&r3_id=" + r3_id);
		pathBuffer.append("&");

		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public List<JBasePartner> getEntityList(Long cust_id) {

		List<JBasePartner> entityList = new ArrayList<JBasePartner>();

		KonkaR3Shop shop = new KonkaR3Shop();
		shop.setId(cust_id);
		shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);

		if (null != shop) {

			// 如果是R3客户，增加客户的编号和名称
			JBasePartner jbp = new JBasePartner();
			jbp.setPartner_id(cust_id);
			jbp.setPartner_name(shop.getCustomer_name());
			entityList.add(jbp);

			JBasePartner entity = new JBasePartner();

			if (shop.getIs_konka() == 0) {
				JBasePartner jp = new JBasePartner();
				jp.setPartner_c_id(cust_id);
				jp.setIs_del(0);
				List<JBasePartner> jpList = super.getFacade().getJBasePartnerService().getJBasePartnerList(jp);
				if (jpList.size() > 0) {
					entity.getMap().put("is_not_konka", jpList.get(0).getPartner_id());
				} else {
					entity.getMap().put("is_not_konka", -1);
				}

			} else {
				entity.getMap().put("is_konka", cust_id);
			}
			entity.setPartner_type(1);
			entity.setPartner_obj(1);
			entity.setIs_del(0);
			List<JBasePartner> entityList1 = super.getFacade().getJBasePartnerService()
					.getJBasePartnerForSonPaginatedList(entity);
			entityList.addAll(entityList1);
		} else {
			entityList = null;
		}
		return entityList;
	}
	
	
	
	//***********改版方法**************************************************/
	/**
	 * 初始化
	 * @author Angus
	 * @date 2014-08-29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HashMap allmap = new HashMap();
        //位置信息
        DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		String naviString = "";
		if (StringUtils.isNotBlank(mod_id)) {
			SysModule sysModule = new SysModule();
			sysModule.getMap().put("mod_id", mod_id);
			List<SysModule> sysModuleList = getFacade().getSysModuleService().getSysModuleList(sysModule);
			naviString = StringHelper.getNaviString(sysModuleList, " > ");
		}
		allmap.put("local_str", naviString);
		
		//当前用户信息
		PeProdUser ui = super.getSessionUserInfo(request);
		if(ui!=null){
			int max_dlevel = super.getMaxDLevel(ui.getId());
			if(max_dlevel==9){
				allmap.put("dept_id", "");
			}else{
				KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id());
				allmap.put("dept_id", dept_fgs.getDept_id());
			}
		}
		
		//用户角色信息
		PeRoleUser roleUser = new PeRoleUser();
		roleUser.setUser_id(ui.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(roleUser);
		for(PeRoleUser t : roleUserList){
			if(t.getRole_id() == 30){//分公司管理员
				allmap.put("fgs_m", true);
				break;
			}
		}
		
		//转换为json数据
		JSONArray jsonArray = JSONArray.fromObject(allmap);
		
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString().substring(start+1, end+1));
		out.flush();
		out.close();
		return null;
	}
	
	public ActionForward queryList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		//验证权限
//		if (null == super.checkUserModPopeDom(form, request, "0")) {
//			return super.checkPopedomInvalid(request, response);
//		}
		
		JBasePartner entity = getEntiy(mapping, form, request);

		Long recordCount = super.getFacade().getJBasePartnerService().getJBasePartnerNewListCount(entity);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		pager.init(recordCount, 10, (String)entity.getMap().get("page"));
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<HashMap> entityList = null;
		if(recordCount>0){
			entityList = super.getFacade().getJBasePartnerService().getJBasePartnerNewList(entity);
		}
		
		//封装成JSON字符串
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("total", recordCount);
		if(entityList==null){
			String[] str = {};
			m.put("rows", str);
		}else{
			m.put("rows", entityList);
		}
		JSONArray jsonArray = JSONArray.fromObject(m);
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start+1, end+1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 导出数据
	 * @author Angus
	 * @date 2014-7-24
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("网点列表")
		        + ".xls");
		JBasePartner entity = getEntiy(mapping, form, request);
		Long recordCount = super.getFacade().getJBasePartnerService().getJBasePartnerNewListCount(entity);
		entity.getRow().setFirst(0);
		entity.getRow().setCount(recordCount.intValue());
		List<HashMap> entityList1 = super.getFacade().getJBasePartnerService().getJBasePartnerNewList(entity);
		request.setAttribute("allList", entityList1);
		return mapping.findForward("view");
	}
	
	/**
	 * 封装数据
	 * @author Angus
	 * @date 2014-7-24
	 * @param mapping
	 * @param form
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public JBasePartner  getEntiy(ActionMapping mapping, ActionForm form, HttpServletRequest request) throws Exception{
		
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		String page = request.getParameter("page");
		
		//分公司编码
		String dept_id = request.getParameter("dept_id");
		
		//网点名称
		String agent_name = request.getParameter("agent_name");
		
		//网点编码
		String agent_id = request.getParameter("agent_id");
		
		//网点级别
		String agent_level = request.getParameter("agent_level");
		
		//客户R3编码
		String r3_code = request.getParameter("r3_code");
		
		//客户名称
		String kh_name = request.getParameter("kh_name");
		
		//业务员
		String ywy_name_like = request.getParameter("ywy_name_like");
		
		//联系人
		String link_name = request.getParameter("link_name");
		
		//经办名称
		String jb_name = request.getParameter("jb_name");
		
		//是否停用，0-未停用        1-已停用
		String is_stop = request.getParameter("is_stop");
		
		//确认状态
		String is_sure = request.getParameter("is_sure");
		
		//一级客户类型
		String cus_type1 = request.getParameter("cus_type1");
		
		//二级客户类型
		String cus_type2 = request.getParameter("cus_type2");
		
		JBasePartner entity = new JBasePartner();

		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
		}
		if (StringUtils.isNotBlank(agent_name)) {
			entity.getMap().put("partner_name_like", agent_name);
		}
		if (StringUtils.isNotBlank(agent_id)) {
			entity.setPartner_sn(agent_id);
		}
		if (StringUtils.isNotBlank(agent_level)) {
			entity.setP_level(Long.valueOf(agent_level));
		}
		if (StringUtils.isNotBlank(r3_code)) {
			entity.getMap().put("r3_code", r3_code);
		}
		if (StringUtils.isNotBlank(kh_name)) {
			entity.getMap().put("kh_name_like", kh_name);
		}
		if (StringUtils.isNotBlank(jb_name)) {
			entity.getMap().put("jb_name_like", jb_name);
		}
		if (StringUtils.isNotBlank(is_stop)) {
			entity.setIs_del(Integer.valueOf(is_stop));
		}
		if(StringUtils.isNotBlank(is_sure)){
        	if("1".equals(is_sure)){
        		entity.getMap().put("is_sure1", is_sure);
        	}
        	if("2".equals(is_sure)){
        		entity.getMap().put("is_sure2", is_sure);
        	}
        }
		if (StringUtils.isNotBlank(ywy_name_like)) {
			entity.getMap().put("ywy_name_like", ywy_name_like);
		}
		if (StringUtils.isNotBlank(link_name)) {
			entity.getMap().put("link_name", link_name);
		}
		if (StringUtils.isNotBlank(cus_type2)) {
			entity.getMap().put("cus_type2", cus_type2);
		}else{
			if (StringUtils.isNotBlank(cus_type1)) {
				entity.getMap().put("cus_type1", cus_type1);
			}
		}
		
		// 数据级别判断开始
		Long __dept_id = StringUtils.isNotBlank(dept_id) ? Long.valueOf(dept_id) : ui.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			if (StringUtils.isNotBlank(dept_id)) {
				entity.getMap().put("dept_id", null);
				entity.getMap().put("fgs_dept_value", dept_id);
			}

			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				entity.getMap().put("fgs_dept_value", __dept_id);
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);
			break;
		case 0:
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", ui.getId());
		// 数据级别判断结束
		
		entity.getMap().put("page", page);
		
		entity.setPartner_obj(1);
		entity.setPartner_type(1);
		return entity;
	}
	
	
	/**
	 * 网点停用/启用
	 * @author Angus
	 * @date 2014-9-2
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stopOrStart(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.noPersission(request,response);
		}

		DynaBean dynaBean = (DynaBean) form;

		String partner_id = (String) dynaBean.get("partner_id");
		String r3_id = (String) dynaBean.get("r3_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String flag = (String) dynaBean.get("flag");
		
		int result = 1;

		if (!StringUtils.isBlank(partner_id) && GenericValidator.isLong(partner_id)) {
			try{
				if("stop".equals(flag)){
					// 停止用户信息
					String c_ids = partner_id;
					JBasePartner jbp = new JBasePartner();
					jbp.setPartner_id(Long.valueOf(partner_id));
					List<JBasePartner> jbpList = super.getFacade().getJBasePartnerService().getJBasePartnerList(jbp);
					if (jbpList.size() > 0) {
						for (JBasePartner jj : jbpList) {
							c_ids = c_ids + "," + jj.getPartner_c_id();
						}
					}
					PeProdUser pUser = new PeProdUser();
					pUser.getMap().put("c_ids", c_ids);
					pUser.setIs_del(1);
					super.getFacade().getPeProdUserService().modifyPeProdUser(pUser);
					
					JBasePartner entity = new JBasePartner();
					entity.setPartner_id(Long.valueOf(partner_id));
					super.getFacade().getJBasePartnerService().removeJBasePartner(entity);
				}else{
					// 启用用户信息
					String c_ids = partner_id;
					JBasePartner jbp = new JBasePartner();
					jbp.setPartner_id(Long.valueOf(partner_id));
					List<JBasePartner> jbpList = super.getFacade().getJBasePartnerService().getJBasePartnerList(jbp);
					if (jbpList.size() > 0) {
						for (JBasePartner jj : jbpList) {
							c_ids = c_ids + "," + jj.getPartner_c_id();
						}
					}
					PeProdUser pUser = new PeProdUser();
					pUser.getMap().put("c_ids", c_ids);
					pUser.setIs_del(0);
					super.getFacade().getPeProdUserService().modifyPeProdUser(pUser);

					JBasePartner entity = new JBasePartner();
					entity.setPartner_id(Long.valueOf(partner_id));
					entity.setIs_del(0);
					entity.setStop_date(new Date());
					super.getFacade().getJBasePartnerService().modifyJBasePartner(entity);
				}
			}catch (Exception e) {
				result = 0;
			}
		}

		//封装成JSON字符串
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("success", result);
		JSONArray jsonArray = JSONArray.fromObject(m);
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start+1, end+1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}
	
	
	/**
	 * 返回列表查询页面时，回显查询条件
	 * @author Liang,HouEn
	 * 2014-10-23
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward backToList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");// 节点
		//传递过来的查询条件
		String dept_id = (String) dynaBean.get("dept_id");// 分公司
		String agent_name = java.net.URLDecoder.decode((String) dynaBean.get("agent_name"),"UTF-8");// 网点名称
		String agent_id = (String) dynaBean.get("agent_id");// 网点编号
		String agent_level = (String) dynaBean.get("agent_level");// 网点级别
		String r3_code = (String) dynaBean.get("r3_code");// 客户R3编码
		String kh_name = java.net.URLDecoder.decode((String) dynaBean.get("kh_name"),"UTF-8");// 上级客户名称
		String ywy_name_like = java.net.URLDecoder.decode((String) dynaBean.get("ywy_name_like"),"UTF-8");// 业务员
		String link_name = java.net.URLDecoder.decode((String) dynaBean.get("link_name"),"UTF-8");// 联系人
		String jb_name = java.net.URLDecoder.decode((String) dynaBean.get("jb_name"),"UTF-8");// 经办名称
		String is_stop = (String) dynaBean.get("is_stop");// 是否停用
		String cus_type1 = (String) dynaBean.get("cus_type1");// 一级客户类型
		String cus_type2 = (String) dynaBean.get("cus_type2");// 二级客户类型
		
		request.setAttribute("mod_id", mod_id);
		String str = "dept_id="+dept_id+"&agent_name="+agent_name+"&agent_id="+agent_id+"&agent_level="+agent_level+"&r3_code="+r3_code+"&kh_name="+kh_name+
				 "&jb_name="+jb_name+"&is_stop="+is_stop+"&ywy_name_like="+ywy_name_like+"&link_name="+link_name+"&cus_type1="+cus_type1+"&cus_type2="+cus_type2;
		request.setAttribute("queryStr", str);
		//返回标识
		request.setAttribute("return_flag", "1");
		return new ActionForward("/admin/AgentsList_new/list.jsp");
	}
	
	/**
	 * 新代理商新增网点
	 * @author Liang,HouEn
	 * 2014-10-29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");// 节点
		
		String local_dept_id = (String) dynaBean.get("local_dept_id");// 当前登录人的分公司编码
		String dept_id = (String) dynaBean.get("dept_id");// 分公司
		String agent_name = java.net.URLDecoder.decode((String) dynaBean.get("agent_name"),"UTF-8");// 网点名称
		String agent_id = (String) dynaBean.get("agent_id");// 网点编号
		String agent_level = (String) dynaBean.get("agent_level");// 网点级别
		String r3_code = (String) dynaBean.get("r3_code");// 客户R3编码
		String kh_name = java.net.URLDecoder.decode((String) dynaBean.get("kh_name"),"UTF-8");// 上级客户名称
		String ywy_name_like = java.net.URLDecoder.decode((String) dynaBean.get("ywy_name_like"),"UTF-8");// 业务员
		String link_name = java.net.URLDecoder.decode((String) dynaBean.get("link_name"),"UTF-8");// 联系人
		String jb_name = java.net.URLDecoder.decode((String) dynaBean.get("jb_name"),"UTF-8");// 经办名称
		String is_stop = (String) dynaBean.get("is_stop");// 是否停用
		String cus_type1 = (String) dynaBean.get("cus_type1");// 一级客户类型
		String cus_type2 = (String) dynaBean.get("cus_type2");// 二级客户类型
		
		//传递查询条件参数
		String str = "dept_id="+dept_id+"&agent_name="+agent_name+"&agent_id="+agent_id+"&agent_level="+agent_level+"&r3_code="+r3_code+"&kh_name="+kh_name+
					 "&jb_name="+jb_name+"&is_stop="+is_stop+"&ywy_name_like="+ywy_name_like+"&link_name="+link_name+"&cus_type1="+cus_type1+"&cus_type2="+cus_type2;
		request.setAttribute("queryStr", str);

		request.setAttribute("mod_id", mod_id);
		request.setAttribute("local_dept_id", local_dept_id);

		//登录人权限
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		PeRoleUser roleUser = new PeRoleUser();
		roleUser.setUser_id(ui.getId());
		List<PeRoleUser> roleUserList = super.getFacade().getPeRoleUserService().getPeRoleUserList(roleUser);
		
		// 回显该门店所在分公司所有的促销员，不包括已经拥有的
		Boolean role_id_gt_30_btw_200_300 = false;
		// Boolean role_id_eq_10 = false;
		if (roleUserList.size() > 0) {
			for (PeRoleUser temp : roleUserList) {
				if (temp.getRole_id() < 30 || (temp.getRole_id() > 200 && temp.getRole_id() < 300)) {
					role_id_gt_30_btw_200_300 = true;
				}
				// if (temp.getRole_id() == 10L) {
				// role_id_eq_10 = true;
				// request.setAttribute("is_admin", "1");
				// }
			}

		}
		if (role_id_gt_30_btw_200_300) {
			// 部门列表
			KonkaDept konka_dept = new KonkaDept();
			konka_dept.setPar_id(0L);
			List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konka_dept);
			request.setAttribute("is_admin", "1");
			request.setAttribute("peDeptList", deptInfoList);
		} else {
			KonkaDept peDept = new KonkaDept();
			peDept.setDept_type(3);
			peDept.setDept_id(ui.getDept_id());
			List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService().getKonkaDeptByDeptId(peDept);
			request.setAttribute("peDeptList", deptInfoList);
		}
		
		//创建日期
		Date today = new Date();
		dynaBean.set("add_date", today);
		
		request.setAttribute("addormodify", "2");

		return new ActionForward("/admin/AgentsList/add_jxs_shop.jsp");
	}
	
	/**
	 * 选择上级客户列表
	 * @author Liang,HouEn
	 * 2014-10-29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
//	public ActionForward listCustomer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//	        HttpServletResponse response) throws Exception {
//		setNaviStringToRequestScope(form, request);
//
//		DynaBean dynaBean = (DynaBean) form;
//
//		String fgs_id = (String) dynaBean.get("fgs_id");
//		String customer_name_like = (String) dynaBean.get("customer_name_like");
//
//		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
//		if (!GenericValidator.isLong(fgs_id)) {
//		}else{
//			KonkaDept t = new KonkaDept();
//			t.setDept_id(Long.valueOf(fgs_id));
//			t = super.getFacade().getKonkaDeptService().getKonkaDept(t);
//			
//			if (null == t || t.getDept_sn() == null) {
//				return null;
//			}
//			konkaR3Shop.setBranch_area_name_2(t.getDept_sn());
//		}
//
//		konkaR3Shop.getMap().put("customer_name_like", customer_name_like);
//		konkaR3Shop.getMap().put("agents", true);
//		List<KonkaR3Shop> entityList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopList(konkaR3Shop);
//		request.setAttribute("entityList", entityList);
//

//		return new ActionForward("/admin/AgentsList_new/list-customer.jsp");
//	}
}
