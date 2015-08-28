package com.ebiz.mmt.web.struts.jxcnokey;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseBrandInfo;
import com.ebiz.mmt.domain.BasePdType;
import com.ebiz.mmt.domain.JxcPd;
import com.ebiz.mmt.domain.JxcPdType;
import com.ebiz.mmt.domain.JxcSellBillDetails;
import com.ebiz.mmt.domain.JxcStockBillDetails;
import com.ebiz.mmt.domain.MvPdTypeJoinBrand;
import com.ebiz.mmt.domain.PdModel;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.UserInfo;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Qin,Yue
 * @version 2011-10-10
 */
public class JxcPdAction extends BaseJxcAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		Pager pager = (Pager) dynaBean.get("pager");
		// String is_del = (String) dynaBean.get("is_del");

		request.setAttribute("not_validate_record", "true");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		Long shop_id = user.getEntp_shop().getShop_id();

		dynaBean.set("keySeq", keySeq);

		JxcPd entity = new JxcPd();
		super.copyProperties(entity, form);

		entity.setIs_del(0);
		entity.setShop_id(shop_id);
		Long recordCount = getFacade().getJxcPdService().getJxcPdCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<JxcPd> list = getFacade().getJxcPdService().getJxcPdPaginatedList(entity);

		BasePdType basePdType = new BasePdType();
		basePdType.setDel_mark(new Short((short) 0));
		basePdType.setIs_model(new Short((short) 1));
		List<BasePdType> basePdTypeList = super.getFacade().getBasePdTypeService().getBasePdTypeList(basePdType);
		request.setAttribute("basePdTypeList", basePdTypeList);

		request.setAttribute("entityList", list);
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);

		String out_sys_id = (String) dynaBean.get("out_sys_id");

		BasePdType basePdType = new BasePdType();
		basePdType.setDel_mark(new Short((short) 0));
		basePdType.setIs_model(new Short((short) 1));
		List<BasePdType> basePdTypeList = super.getFacade().getBasePdTypeService().getBasePdTypeList(basePdType);
		request.setAttribute("basePdTypeList", basePdTypeList);

		List<JxcPdType> JxcPdTypeList = getFacade().getJxcPdTypeService().getJxcPdTypeList(new JxcPdType());
		request.setAttribute("JxcPdTypeList", JxcPdTypeList);
		dynaBean.set("queryString", super.serialize(request, "mod_id", "method"));
		dynaBean.set("unit", "台");
		dynaBean.set("add", "add");

		if (StringUtils.isNotBlank(out_sys_id)) {
			PdModel pdModel = new PdModel();
			pdModel.setPd_id(Long.valueOf(out_sys_id));
			pdModel = getFacade().getPdModelService().getPdModel(pdModel);
			if (null == pdModel) {
				renderJavaScript(response, "alert('该产品型号不存在，请您核实标识卡号和产品编号！');history.back();");
				return null;
			}

			BaseBrandInfo baseBrandInfo = new BaseBrandInfo();
			baseBrandInfo.setBrand_id(pdModel.getBrand_id());
			baseBrandInfo = getFacade().getBaseBrandInfoService().getBaseBrandInfo(baseBrandInfo);
			if (null == baseBrandInfo) {
				renderJavaScript(response, "alert('该产品品牌不存在，请您核实标识卡号和产品编号');history.back();");
				return null;
			}

			dynaBean.set("own_sys", "1");
			dynaBean.set("pd_type", pdModel.getPd_big_type());
			dynaBean.set("brand_name", baseBrandInfo.getBrand_name());
			dynaBean.set("brand_id", pdModel.getBrand_id());
			dynaBean.set("jdxx_sale", "true");
		} else {
			dynaBean.set("own_sys", "1");
		}

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String keySeq = (String) dynaBean.get("keySeq");

		request.setAttribute("not_validate_record", "true");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}

		dynaBean.set("keySeq", keySeq);
		// Long shop_id = user.getStdEntpMain().getShop_id();

		if (StringUtils.isNotBlank(id)) {
			JxcPd entity = new JxcPd();
			entity.setId(Long.valueOf(id));
			entity = getFacade().getJxcPdService().getJxcPd(entity);

			if (null == entity) {
				saveError(request, "errors.long", new String[] { id });
				return mapping.findForward("list");
			}
			BasePdType basePdType = new BasePdType();
			basePdType.setDel_mark(new Short((short) 0));
			basePdType.setIs_model(new Short((short) 1));
			List<BasePdType> basePdTypeList = super.getFacade().getBasePdTypeService().getBasePdTypeList(basePdType);
			request.setAttribute("basePdTypeList", basePdTypeList);

			List<JxcPdType> JxcPdTypeList = getFacade().getJxcPdTypeService().getJxcPdTypeList(new JxcPdType());
			request.setAttribute("JxcPdTypeList", JxcPdTypeList);

			entity.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, entity);

			dynaBean.set("edit", "edit");

			JxcStockBillDetails jxcStockBillDetails = new JxcStockBillDetails();
			jxcStockBillDetails.setPd_id(Long.valueOf(id));
			Long count_stock = getFacade().getJxcStockBillDetailsService().getJxcStockBillDetailsCount(
					jxcStockBillDetails);

			JxcSellBillDetails jxcSellBillDetails = new JxcSellBillDetails();
			jxcSellBillDetails.setPd_id(Long.valueOf(id));
			Long count_sell = getFacade().getJxcSellBillDetailsService().getJxcSellBillDetailsCount(jxcSellBillDetails);
			if (count_sell.intValue() > 0 || count_stock.intValue() > 0) {// 如果有销售数据则产品不能修改。
				dynaBean.set("canNotEdit", "canNotEdit");
			}
			return mapping.findForward("input");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");

		request.setAttribute("not_validate_record", "true");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		Long shop_id = user.getEntp_shop().getShop_id();

		dynaBean.set("keySeq", keySeq);

		String id = (String) dynaBean.get("id");
		String pd_type = (String) dynaBean.get("pd_type"); // Base_Pd_Type 产品大类
		String jxc_pd_type_id = (String) dynaBean.get("jxc_pd_type_id"); // JXC_PD_TYPE
		// 自定义产品大类
		String out_sys_id = (String) dynaBean.get("out_sys_id");
		String ref_price = (String) dynaBean.get("ref_price");
		String pf_price = (String) dynaBean.get("pf_price");
		String price = (String) dynaBean.get("price");

		JxcPd entity = new JxcPd();
		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(out_sys_id)) {
			JxcPd jxcPd = new JxcPd();
			jxcPd.setShop_id(shop_id);
			jxcPd.setOwn_sys(entity.getOwn_sys());
			jxcPd.setOut_sys_id(Long.valueOf(out_sys_id));
			jxcPd.setIs_del(0);
			if (StringUtils.isNotBlank(id)) {
				jxcPd.getMap().put("validateNameWithNotInID", id);
			}
			Long count = getFacade().getJxcPdService().getJxcPdCount(jxcPd);
			if (count > 0) {
				super.renderJavaScript(response, "alert('产品编码：【" + out_sys_id + "】已经存在，请勿重复添加！');history.back();");
				return null;
			}
		} else if (StringUtils.isNotBlank(entity.getName())) {
			JxcPd jxcPd = new JxcPd();
			jxcPd.setOwn_sys(entity.getOwn_sys());
			jxcPd.setShop_id(shop_id);
			jxcPd.setName(entity.getName());
			jxcPd.setIs_del(0);
			if (StringUtils.isNotBlank(id)) {
				jxcPd.getMap().put("validateNameWithNotInID", id);
			}
			Long count = getFacade().getJxcPdService().getJxcPdCount(jxcPd);
			if (count > 0) {
				super
						.renderJavaScript(response, "alert('产品名称：【" + entity.getName()
								+ "】已经存在，请勿重复添加！');history.back();");
				return null;
			}
		}

		if (StringUtils.isNotBlank(pd_type)) {
			BasePdType basePdType = new BasePdType();
			basePdType.setPd_type(Long.valueOf(pd_type));
			basePdType = getFacade().getBasePdTypeService().getBasePdType(basePdType);
			if (basePdType != null) {
				entity.setPd_type_name(basePdType.getPd_name());
			}

			if (StringUtils.isNotBlank(jxc_pd_type_id)) {
				JxcPdType jxcPdType = new JxcPdType();
				jxcPdType.setId(Long.valueOf(jxc_pd_type_id));
				jxcPdType = getFacade().getJxcPdTypeService().getJxcPdType(jxcPdType);
				if (null != jxcPdType) {
					entity.setPd_type_name(jxcPdType.getName());
				}
			}

			if (StringUtils.isNotBlank(out_sys_id)) {
				entity.setOut_sys_id(Long.valueOf(out_sys_id));
				PdModel pdModel = new PdModel();
				pdModel.setPd_id(Long.valueOf(out_sys_id));
				pdModel = getFacade().getPdModelService().getPdModel(pdModel);
				if (pdModel != null) {
					entity.setName(pdModel.getMd_name());
				}
			}
		}

		if (StringUtils.isBlank(ref_price)) {
			entity.setRef_price(BigDecimal.valueOf(0));
		}
		if (StringUtils.isBlank(pf_price)) {
			entity.setPf_price(BigDecimal.valueOf(0));
		}
		if (StringUtils.isBlank(price)) {
			entity.setPrice(BigDecimal.valueOf(0));
		}
		entity.setShop_id(Long.valueOf(shop_id));
		entity.setShop_p_index(Long.valueOf(user.getEntp_shop().getP_index()));
		entity.setAdd_user_id(user.getId());

		if (StringUtils.isBlank(entity.getName())) {
			super.renderJavaScript(response, "alert('产品型号不能为空，请重新选择');history.back();");
			return null;
		}
		if (null == (entity.getOwn_sys())) {
			super.renderJavaScript(response, "alert('请选择产品所属系统');history.back();");
			return null;
		}
		if ("1".equalsIgnoreCase(entity.getOwn_sys().toString()) && StringUtils.isBlank(out_sys_id)) {
			super.renderJavaScript(response, "alert('产品型号不能为空，请重新选择');history.back();");
			return null;
		}

		if (StringUtils.isBlank(id)) { // 新增产品
			entity.setCount(entity.getInit_count());
			entity.setCur_cost_price(entity.getRef_price());
			entity.setInit_cost_price(entity.getRef_price());
			entity.setAdd_date(new Date());
			super.getFacade().getJxcPdService().createJxcPd(entity);
			saveMessage(request, "entity.inserted");
		} else {
			super.getFacade().getJxcPdService().modifyJxcPd(entity);
			saveMessage(request, "entity.updated");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String keySeq = (String) dynaBean.get("keySeq");

		request.setAttribute("not_validate_record", "true");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);
		// Long shop_id = user.getStdEntpMain().getShop_id();

		if (StringUtils.isNotBlank(id)) {
			JxcPd entity = new JxcPd();
			entity.setId(Long.valueOf(id));
			entity = getFacade().getJxcPdService().getJxcPd(entity);

			if (null == entity) {
				saveError(request, "errors.long", new String[] { id });
				return mapping.findForward("list");
			}
			BasePdType basePdType = new BasePdType();
			basePdType.setDel_mark(new Short((short) 0));
			basePdType.setIs_model(new Short((short) 1));
			List<BasePdType> basePdTypeList = super.getFacade().getBasePdTypeService().getBasePdTypeList(basePdType);
			request.setAttribute("basePdTypeList", basePdTypeList);

			List<JxcPdType> JxcPdTypeList = getFacade().getJxcPdTypeService().getJxcPdTypeList(new JxcPdType());
			request.setAttribute("JxcPdTypeList", JxcPdTypeList);

			entity.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, entity);

			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = request.getParameterValues("pks");

		JxcPd entity = new JxcPd();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
			// entity.setIs_del(1);
			super.getFacade().getJxcPdService().removeJxcPd(entity);
		} else if (ArrayUtils.isNotEmpty(pks)) {
			entity.getMap().put("pks", pks);
			super.getFacade().getJxcPdService().removeJxcPd(entity);
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward chooseBrand(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String brand_name_like = (String) dynaBean.get("brand_name_like");
		String pd_type = (String) dynaBean.get("pd_type");

		if ("0".equals(pd_type)) {
			BaseBrandInfo entity = new BaseBrandInfo();
			super.copyProperties(entity, dynaBean);
			entity.setIs_del(0);
			entity.getMap().put("brand_name_like", brand_name_like);

			Long recordCount = getFacade().getBaseBrandInfoService().getBaseBrandInfoCount(entity);
			pager.init(recordCount, 45, pager.getRequestPage());
			entity.getRow().setFirst(pager.getFirstRow());
			entity.getRow().setCount(45);
			List<BaseBrandInfo> list = getFacade().getBaseBrandInfoService().getBaseBrandInfoPaginatedList(entity);
			request.setAttribute("entityList", list);
		} else {
			MvPdTypeJoinBrand entity = new MvPdTypeJoinBrand();
			super.copyProperties(entity, form);
			if (StringUtils.isNotBlank(pd_type)) {
				entity.setPd_type(Long.valueOf(pd_type));
			}
			entity.getMap().put("brand_name_like", brand_name_like);

			Long recordCount = getFacade().getMvPdTypeJoinBrandService().getMvPdTypeJoinBrandCount(entity);
			pager.init(recordCount, 45, pager.getRequestPage());
			entity.getRow().setFirst(pager.getFirstRow());
			entity.getRow().setCount(45);
			List<MvPdTypeJoinBrand> list = getFacade().getMvPdTypeJoinBrandService().getMvPdTypeJoinBrandPaginatedList(
					entity);
			request.setAttribute("entityList", list);
		}

		dynaBean.set("pd_type", pd_type);
		return new ActionForward("/../jxc/JxcPd/chooseBrand.jsp");
	}

	public ActionForward chooseAllPdModel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String pd_id = (String) dynaBean.get("pd_id");
		String md_name_like = (String) dynaBean.get("md_name_like");
		String entp_name_like = (String) dynaBean.get("entp_name_like");

		PdModel entity = new PdModel();
		super.copyProperties(entity, form);
		if (StringUtils.isNotBlank(pd_id)) {
			entity.setPd_id(Long.valueOf(pd_id));
		}
		entity.getMap().put("entp_name_like", entp_name_like);
		entity.getMap().put("md_name_like", md_name_like);

		Long recordCount = getFacade().getPdModelService().getPdModelCountForJxcPd(entity);
		pager.init(recordCount, 45, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(45);
		List<PdModel> list = getFacade().getPdModelService().getPdModelPaginatedListForJxcPd(entity);

		request.setAttribute("entityList", list);

		return new ActionForward("/../jxc/JxcPd/chooseAllPdModel.jsp");
	}

	public ActionForward getPdModel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String pd_type = (String) dynaBean.get("pd_type");
		String brand_id = (String) dynaBean.get("brand_id");
		// String keySeq = (String) dynaBean.get("keySeq");

		request.setAttribute("not_validate_record", "true");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		Long shop_id = user.getEntp_shop().getShop_id();

		if (StringUtils.isNotBlank(brand_id) && StringUtils.isNotBlank(pd_type)) {
			StringBuffer sb = new StringBuffer("[");
			if (StringUtils.equals(brand_id, Constants.KONKA_BRAND_ID.toString())) {// 康佳产品取产品型号只根据部门Id
				HashMap<String, String> result = this.getKonkaDeptIdAndType(shop_id);
				if (!"0".equals(result.get("result_code"))) {
					PePdModel pePdModel = new PePdModel();
					pePdModel.getMap().put("getOwnDeptPdModel_brand_konka", "true");
					pePdModel.getMap().put("dept_id", result.get("r3_dept_id"));
					List<PePdModel> ppmList = getFacade().getPePdModelService().getPePdModelList(pePdModel);
					for (PePdModel ppm : ppmList) {
						sb.append("{\"pd_id\":\"" + ppm.getPd_id() + "\",");
						sb.append("\"md_name\":\"" + ppm.getMd_name() + "\"},");
					}
				}
			} else {// 非康佳品牌
				PdModel pdModel = new PdModel();
				// pdModel.setPd_type(Long.valueOf(pd_type));
				pdModel.setPd_big_type(Long.valueOf(pd_type));
				pdModel.setBrand_id(Long.valueOf(brand_id));
				pdModel.getMap().put("jxcAddPdFilterPdModel", "true");
				pdModel.getMap().put("p_index_jxcPd", user.getEntp_shop().getP_index());
				List<PdModel> list = getFacade().getPdModelService().getPdModelListForJxcPd(pdModel);// 买卖提的产品型号

				for (PdModel pm : list) {
					sb.append("{\"pd_id\":\"" + pm.getPd_id() + "\",");
					sb.append("\"md_name\":\"" + pm.getMd_name() + "\"},");
				}
			}
			sb.append("{\"end\":\"rz\"}]");

			super.renderJson(response, sb.toString());
		}

		return null;
	}

	public ActionForward validateName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String own_sys = (String) dynaBean.get("own_sys");
		String name = (String) dynaBean.get("name");
		String out_sys_id = (String) dynaBean.get("out_sys_id");
		// String keySeq = (String) dynaBean.get("keySeq");
		String isExist = "";

		request.setAttribute("not_validate_record", "true");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			isExist = "2";
			StringBuffer sb = new StringBuffer();
			sb.append("{\"isExist\":\"" + isExist + "\",");
			sb.append("\"msg\":\"\"}");
			super.renderJson(response, sb.toString());
			return null;
		}
		Long shop_id = user.getEntp_shop().getShop_id();

		if (StringUtils.isNotBlank(out_sys_id)) {
			JxcPd jxcPd = new JxcPd();
			jxcPd.setShop_id(shop_id);
			jxcPd.setOwn_sys(Integer.valueOf(own_sys));
			jxcPd.setOut_sys_id(Long.valueOf(out_sys_id));
			jxcPd.setIs_del(0);
			if (StringUtils.isNotBlank(id)) {
				jxcPd.getMap().put("validateNameWithNotInID", id);
			}
			Long count = getFacade().getJxcPdService().getJxcPdCount(jxcPd);
			if (count > 0) {
				isExist = "1";
				StringBuffer sb = new StringBuffer();
				sb.append("{\"isExist\":\"" + isExist + "\",");
				sb.append("\"msg\":\"[产品编号：" + out_sys_id + "]\"}");
				super.renderJson(response, sb.toString());
				return null;
			}
		}

		if (StringUtils.isNotBlank(name)) {
			JxcPd entity = new JxcPd();
			entity.setOwn_sys(Integer.valueOf(own_sys));
			entity.setShop_id(shop_id);
			entity.setName(name);
			entity.setIs_del(0);
			if (StringUtils.isNotBlank(id)) {
				entity.getMap().put("validateNameWithNotInID", id);
			}
			Long count = getFacade().getJxcPdService().getJxcPdCount(entity);
			if (count > 0) {
				isExist = "1";
			}
		}

		StringBuffer sb = new StringBuffer();
		sb.append("{\"isExist\":\"" + isExist + "\",");
		sb.append("\"msg\":\"\"}");
		super.renderJson(response, sb.toString());

		return null;
	}

	public ActionForward saveJxcPdType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String jxc_pd_type_name = (String) dynaBean.get("jxc_pd_type_name");
		// String keySeq = (String) dynaBean.get("keySeq");

		request.setAttribute("not_validate_record", "true");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}

		String isExist = "-1";

		boolean isBasePdType = false;
		BasePdType basePdType = new BasePdType();
		basePdType.setDel_mark(new Short((short) 0));
		basePdType.setIs_model(new Short((short) 1));
		List<BasePdType> basePdTypeList = super.getFacade().getBasePdTypeService().getBasePdTypeList(basePdType);
		for (BasePdType bpt : basePdTypeList) {
			if (StringUtils.equals(jxc_pd_type_name, bpt.getPd_name())) {
				isBasePdType = true;
				break;
			}
		}

		if (!isBasePdType && StringUtils.isNotBlank(jxc_pd_type_name)) {
			JxcPdType entity = new JxcPdType();
			entity.setName(jxc_pd_type_name);
			Long count = getFacade().getJxcPdTypeService().getJxcPdTypeCount(entity);
			if (count <= 0) {
				entity.setShop_id(user.getEntp_shop().getShop_id());
				entity.setAdd_user_id(user.getId());
				entity.setAdd_date(new Date());
				Long id = getFacade().getJxcPdTypeService().createJxcPdType(entity);
				isExist = id.toString();
			}
		}
		super.render(response, isExist, "text/x-json;charset=UTF-8");
		return null;
	}
}
