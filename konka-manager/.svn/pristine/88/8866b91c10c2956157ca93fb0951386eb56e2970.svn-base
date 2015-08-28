package com.ebiz.mmt.web.struts.jxc;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaOrderAuditProcess;
import com.ebiz.mmt.domain.KonkaPePdModelLowestPrice;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Li,Ka
 * @version 2011-11-30
 */
public class KonkaJxcOrderPdModelLowestPriceAction extends BaseJxcAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		setNaviStringToRequestScope(form, request);
		return this.list(mapping, form, request, response);
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		
		PeProdUser user = super.getSessionUserInfo(request);
		PeRoleInfo peRoleInfo = super.getPeRoleInfoByUserId(user.getId());
		Pager pager = (Pager) dynaBean.get("pager");
		if (null == user) {
			return null;
		}
		String isDel = (String) dynaBean.get("is_del");
		
		//全部康佳产品
		PePdModel pePdModel = new PePdModel();
		pePdModel.setBrand_id(Constants.KONKA_BRAND_ID);
		pePdModel.setIs_del(0);
		List<PePdModel> pdList = getFacade().getPePdModelService().getPePdModelList(pePdModel);
		request.setAttribute("pdList", pdList);
		
		KonkaPePdModelLowestPrice konkaPePdModelLowestPrice = new KonkaPePdModelLowestPrice();
		super.copyProperties(konkaPePdModelLowestPrice, form);
		if (null == isDel) {
			dynaBean.set("is_del", 0);
			konkaPePdModelLowestPrice.setIs_del(0);
		}
		if (peRoleInfo.getRole_id().intValue() != 10) {//管理员看全部部门
			konkaPePdModelLowestPrice.setAdd_dept_id(user.getDept_id());
		}
		
		Long recordCount = getFacade().getKonkaPePdModelLowestPriceService().getKonkaPePdModelLowestPriceCount(konkaPePdModelLowestPrice);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		konkaPePdModelLowestPrice.getRow().setFirst(pager.getFirstRow());
		konkaPePdModelLowestPrice.getRow().setCount(pager.getRowCount());
		
		List<KonkaPePdModelLowestPrice> konkaPePdModelLowestPriceList = getFacade().getKonkaPePdModelLowestPriceService().getKonkaPePdModelLowestPricePaginatedList(konkaPePdModelLowestPrice);
		request.setAttribute("konkaPePdModelLowestPriceList", konkaPePdModelLowestPriceList);
		request.setAttribute("yearList", super.getYearList());
		request.setAttribute("monthList", super.getMonthList());
		
		//处理操作按钮
		if (peRoleInfo.getRole_id().intValue() == 10) {
			request.setAttribute("hide", 1);
		}
		return mapping.findForward("list");
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		
		PeProdUser user = super.getSessionUserInfo(request);
		if (null == user) {
			return null;
		}
		
		//判断是否定义特殊流程，如果没定义则提示先定义流程后添加限价
		KonkaOrderAuditProcess konkaOrderAuditProcess = super.getSpecialProcessByFgsDeptId(user.getDept_id());
		if (null == konkaOrderAuditProcess) {
			String send_error = "您还没有定义订单审核的特殊流程，请先定义特殊流程！";
			super.renderJavaScript(response, "alert('" + send_error + "');history.back();");
			return null;
		}
		
		//全部康佳产品
		PePdModel pePdModel = new PePdModel();
		pePdModel.setBrand_id(Constants.KONKA_BRAND_ID);
		pePdModel.setIs_del(0);
		List<PePdModel> pdList = getFacade().getPePdModelService().getPePdModelWithClsNameBrandNameList(pePdModel);
		request.setAttribute("pdList", pdList);
		request.setAttribute("yearList", super.getYearList());
		request.setAttribute("monthList", super.getMonthList());
		
		dynaBean.set("flag", "add");
		return mapping.findForward("input");
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		
		PeProdUser user = super.getSessionUserInfo(request);
		if (null == user) {
			return null;
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaPePdModelLowestPrice konkaPePdModelLowestPrice = new KonkaPePdModelLowestPrice();
			konkaPePdModelLowestPrice.setId(Long.valueOf(id));
			konkaPePdModelLowestPrice = getFacade().getKonkaPePdModelLowestPriceService().getKonkaPePdModelLowestPrice(konkaPePdModelLowestPrice);
			super.copyProperties(form, konkaPePdModelLowestPrice);
		} else {
			super.saveMessage(request, "entity.missing");
		}
		//全部康佳产品
		PePdModel pePdModel = new PePdModel();
		pePdModel.setBrand_id(Constants.KONKA_BRAND_ID);
		pePdModel.setIs_del(0);
		List<PePdModel> pdList = getFacade().getPePdModelService().getPePdModelList(pePdModel);
		request.setAttribute("pdList", pdList);
		request.setAttribute("yearList", super.getYearList());
		request.setAttribute("monthList", super.getMonthList());
		
		dynaBean.set("flag", "edit");
		return mapping.findForward("input");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		PeProdUser user = super.getSessionUserInfo(request);
		if (null == user) {
			return null;
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		KonkaPePdModelLowestPrice konkaPePdModelLowestPrice = new KonkaPePdModelLowestPrice();
		super.copyProperties(konkaPePdModelLowestPrice, dynaBean);
		konkaPePdModelLowestPrice.setAdd_dept_id(user.getDept_id());
		
		if (StringUtils.isNotBlank(id) && GenericValidator.isLong(id)) {// 更新
			KonkaPePdModelLowestPrice entity = new KonkaPePdModelLowestPrice();
			entity.setId(Long.valueOf(id));
			entity.setLowest_price(konkaPePdModelLowestPrice.getLowest_price());
			entity.setAdd_user_id(user.getId());
			entity.setAdd_date(new Date());
			getFacade().getKonkaPePdModelLowestPriceService().modifyKonkaPePdModelLowestPrice(entity);
			super.saveMessage(request, "entity.updated");
			
		} else {// 新增
			PePdModel pePdModel = super.getPePdModel(konkaPePdModelLowestPrice.getPd_id());//所选型号
			KonkaPePdModelLowestPrice entity = new KonkaPePdModelLowestPrice();
			entity.setPd_id(konkaPePdModelLowestPrice.getPd_id());
			entity.setSet_year(konkaPePdModelLowestPrice.getSet_year());
			entity.setSet_month(konkaPePdModelLowestPrice.getSet_month());
			entity.setAdd_dept_id(user.getDept_id());
			entity.setIs_del(0);
			entity = getFacade().getKonkaPePdModelLowestPriceService().getKonkaPePdModelLowestPrice(entity);
			if (null != entity) {
				String send_error = "型号【" + pePdModel.getMd_name() + "】已经添加了" + konkaPePdModelLowestPrice.getSet_year() + "年" + konkaPePdModelLowestPrice.getSet_month() + "月的最低限价，不能重复添加！";
				super.renderJavaScript(response, "alert('" + send_error + "');history.back();");
				return null;
			}
			
			konkaPePdModelLowestPrice.setAdd_user_id(user.getId());
			konkaPePdModelLowestPrice.setDept_name(super.getKonkaDeptById(user.getDept_id()).getDept_name());
			konkaPePdModelLowestPrice.setBrand_id(Constants.KONKA_BRAND_ID);
			konkaPePdModelLowestPrice.setBrand_name(Constants.KONKA_BRAND_NAME);
			konkaPePdModelLowestPrice.setPd_type_id(pePdModel.getCls_id());
			konkaPePdModelLowestPrice.setPd_type_name(super.getBasePdClazz(pePdModel.getCls_id()).getCls_name());
			konkaPePdModelLowestPrice.setPd_name(pePdModel.getMd_name());
			getFacade().getKonkaPePdModelLowestPriceService().createKonkaPePdModelLowestPrice(konkaPePdModelLowestPrice);
			super.saveMessage(request, "entity.inserted");
		}
		
		// the line below is added for pagination
		ActionForward forward = new ActionForward(mapping.findForward("success").getPath(), true);
		// end
		return forward;
		
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		PeProdUser user = super.getSessionUserInfo(request);
		if (null == user) {
			return null;
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		//String mod_id = (String) dynaBean.get("mod_id");
		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaPePdModelLowestPrice entity = new KonkaPePdModelLowestPrice();
			entity.setId(Long.valueOf(id));
			entity.setIs_del(1);
			entity.setDel_date(new Date());
			entity.setDel_user_id(user.getId());
			getFacade().getKonkaPePdModelLowestPriceService().modifyKonkaPePdModelLowestPrice(entity);
			saveMessage(request, "entity.deleted");
		} else {
			for (String pk : pks) {
				KonkaPePdModelLowestPrice entity = new KonkaPePdModelLowestPrice();
				entity.setId(Long.valueOf(pk));
				entity.setIs_del(1);
				entity.setDel_date(new Date());
				entity.setDel_user_id(user.getId());
				getFacade().getKonkaPePdModelLowestPriceService().modifyKonkaPePdModelLowestPrice(entity);
				saveMessage(request, "entity.deleted");
			}
		}
		
		ActionForward forward = new ActionForward(mapping.findForward("success").getPath(), true);
		return forward;
	}
	
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		PeProdUser user = super.getSessionUserInfo(request);
		if (null == user) {
			return null;
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			KonkaPePdModelLowestPrice konkaPePdModelLowestPrice = new KonkaPePdModelLowestPrice();
			konkaPePdModelLowestPrice.setId(Long.valueOf(id));
			konkaPePdModelLowestPrice = getFacade().getKonkaPePdModelLowestPriceService().getKonkaPePdModelLowestPrice(konkaPePdModelLowestPrice);
			super.copyProperties(form, konkaPePdModelLowestPrice);
			
			return mapping.findForward("view");
		} else {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
	}
	
}
