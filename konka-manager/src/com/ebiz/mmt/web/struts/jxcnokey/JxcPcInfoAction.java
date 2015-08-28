package com.ebiz.mmt.web.struts.jxcnokey;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseBrandInfo;
import com.ebiz.mmt.domain.BasePdType;
import com.ebiz.mmt.domain.JxcPd;
import com.ebiz.mmt.domain.JxcPdType;
import com.ebiz.mmt.domain.KonkaJxcPcInfo;
import com.ebiz.mmt.domain.MvPdTypeJoinBrand;
import com.ebiz.mmt.domain.UserInfo;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Guo,Jun
 * @version 2011-10-10
 */
public class JxcPcInfoAction extends BaseJxcAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		// String keySeq = (String) dynaBean.get("keySeq");
		Pager pager = (Pager) dynaBean.get("pager");

		String pd_type = (String) dynaBean.get("pd_type");
		String jxc_pd_type_id = (String) dynaBean.get("jxc_pd_type_id");
		request.setAttribute("not_validate_record", "true");
		UserInfo user = super.getUserInfoFromWebService(request, response);

		if (null == user) {
			return null;
		}

		Long shop_id = user.getEntp_shop().getShop_id();

		KonkaJxcPcInfo entity = new KonkaJxcPcInfo();
		entity.setPc_type(0);
		// entity.setAdd_user_id(user.getUser_id());
		entity.setShop_id(shop_id);

		if (StringUtils.isNotBlank(pd_type) && "0".equals(pd_type)) {
			entity.setPd_type_id(Long.valueOf(jxc_pd_type_id));
		} else if (StringUtils.isNotBlank(pd_type)) {
			entity.setPd_type_id(Long.valueOf(pd_type));
		}

		super.copyProperties(entity, form);

		Long recordCount = getFacade().getKonkaJxcPcInfoService().getKonkaJxcPcInfoCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaJxcPcInfo> entityList = getFacade().getKonkaJxcPcInfoService().getKonkaJxcPcInfoPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		// 产品大类列表
		BasePdType pdType = new BasePdType();
		pdType.setIs_model((short) 1);
		pdType.setDel_mark((short) 0);
		List<BasePdType> basePdTypeList = getFacade().getBasePdTypeService().getBasePdTypeList(pdType);
		request.setAttribute("basePdTypeList", basePdTypeList);

		// 其他产品类型
		List<JxcPdType> jxcPdTypeList = getFacade().getJxcPdTypeService().getJxcPdTypeList(new JxcPdType());
		request.setAttribute("jxcPdTypeList", jxcPdTypeList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		// String keySeq = (String) dynaBean.get("keySeq");
		dynaBean.set("queryString", super.serialize(request, "method", "keySeq"));

		request.setAttribute("not_validate_record", "true");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}

		dynaBean.set("pc_date", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));// 默认盘存时间为当天

		// 产品大类列表
		BasePdType basePdType = new BasePdType();
		basePdType.setDel_mark(new Short((short) 0));
		basePdType.setIs_model(new Short((short) 1));
		List<BasePdType> basePdTypeList = super.getFacade().getBasePdTypeService().getBasePdTypeList(basePdType);
		request.setAttribute("basePdTypeList", basePdTypeList);

		// 其他产品类型
		List<JxcPdType> jxcPdTypeList = getFacade().getJxcPdTypeService().getJxcPdTypeList(new JxcPdType());
		request.setAttribute("jxcPdTypeList", jxcPdTypeList);
		dynaBean.set("queryString", super.serialize(request, "mod_id", "method"));

		dynaBean.set("own_sys", "0");

		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			KonkaJxcPcInfo entity = new KonkaJxcPcInfo();

			entity.setId(Long.valueOf(id));

			entity = super.getFacade().getKonkaJxcPcInfoService().getKonkaJxcPcInfo(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			entity.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, entity);
			dynaBean.set("add_date", entity.getAdd_date());

			return mapping.findForward("view");
		} else {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		String keySeq = (String) dynaBean.get("keySeq");
		request.setAttribute("not_validate_record", "true");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		Long shop_id = user.getEntp_shop().getShop_id();

		String pd_type = (String) dynaBean.get("pd_type");
		String jxc_pd_type_id = (String) dynaBean.get("jxc_pd_type_id");
		String pc_date = (String) dynaBean.get("pc_date");

		KonkaJxcPcInfo entity = new KonkaJxcPcInfo();
		super.copyProperties(entity, form);
		entity.setPd_type_id(Long.valueOf(pd_type));

		if (StringUtils.isBlank(pc_date)) {
			entity.setPc_date(new Date());
		}

		// 产品大类名称
		if (StringUtils.isNotBlank(jxc_pd_type_id)) {
			entity.setPd_type_id(Long.valueOf(jxc_pd_type_id));
			JxcPdType pdType = new JxcPdType();
			pdType.setId(entity.getPd_type_id());
			pdType = getFacade().getJxcPdTypeService().getJxcPdType(pdType);
			if (null != pdType) {
				entity.setPd_type_name(pdType.getName());
			}
		} else {
			BasePdType pdType = new BasePdType();
			pdType.setIs_model((short) 1);
			pdType.setPd_type(entity.getPd_type_id());
			pdType = getFacade().getBasePdTypeService().getBasePdType(pdType);
			if (null != pdType) {
				entity.setPd_type_name(pdType.getPd_name());
			}
		}

		// 品牌名称
		BaseBrandInfo baseBrandInfo = new BaseBrandInfo();
		baseBrandInfo.setIs_del(0);
		baseBrandInfo.setBrand_id(entity.getBrand_id());
		baseBrandInfo = super.getFacade().getBaseBrandInfoService().getBaseBrandInfo(baseBrandInfo);
		if (null != baseBrandInfo) {
			entity.setBrand_name(baseBrandInfo.getBrand_name());
		}

		// 型号名称
		JxcPd jxcPd = new JxcPd();
		jxcPd.setId(entity.getPd_id());
		jxcPd = getFacade().getJxcPdService().getJxcPd(jxcPd);
		if (null != jxcPd) {
			entity.setPd_name(jxcPd.getName());
		}

		if (null == entity.getId()) {
			entity.setPc_type(0);// 网点端盘存
			entity.setShop_id(shop_id);
			entity.setAdd_user_id(user.getId());
			entity.setIs_del(0);
			entity.setAdd_date(new Date());

			super.getFacade().getKonkaJxcPcInfoService().createKonkaJxcPcInfo(entity);
			saveMessage(request, "entity.inserted");
		} else {
			super.getFacade().getKonkaJxcPcInfoService().modifyKonkaJxcPcInfo(entity);
			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&keySeq=" + keySeq);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward getJxcPdList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String own_sys = (String) dynaBean.get("own_sys");
		String pd_type = (String) dynaBean.get("pd_type");
		String brand_id = (String) dynaBean.get("brand_id");
		String jxc_pd_type_id = (String) dynaBean.get("jxc_pd_type_id");
		// String keySeq = (String) dynaBean.get("keySeq");

		request.setAttribute("not_validate_record", "true");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		Long shop_id = user.getEntp_shop().getShop_id();

		if (StringUtils.isBlank(brand_id) || StringUtils.isBlank(pd_type) || StringUtils.isBlank(own_sys)) {
			return null;
		}

		JxcPd jxcPd = new JxcPd();
		jxcPd.setOwn_sys(Integer.valueOf(own_sys));
		jxcPd.setShop_id(shop_id);
		if (StringUtils.isNotBlank(jxc_pd_type_id)) {
			jxcPd.setJxc_pd_type_id(Long.valueOf(jxc_pd_type_id));
		}
		jxcPd.setPd_type(Long.valueOf(pd_type));
		jxcPd.setBrand_id(Long.valueOf(brand_id));
		jxcPd.setIs_del(0);

		List<JxcPd> list = getFacade().getJxcPdService().getJxcPdList(jxcPd);

		StringBuffer sb = new StringBuffer("[");
		for (JxcPd pm : list) {
			String values = pm.getId() + "@#" + pm.getCount() + "@#" + pm.getCur_cost_price();
			sb.append("{\"values\":\"" + values + "\",");
			sb.append("\"name\":\"" + pm.getName() + "\"},");
		}
		sb.append("{\"end\":\"rz\"}]");

		super.renderJson(response, sb.toString());

		return null;
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

}