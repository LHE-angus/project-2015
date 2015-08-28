package com.ebiz.mmt.web.struts.customer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileSpRelation;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.KonkaR3StoreShow;
import com.ebiz.mmt.domain.KonkaR3Target;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class KonkaR3TargetAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	/**
	 * 查询目标管理列表
	 * @author Liang Houen
	 * @since 2015-7-15
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);

		super.encodeCharacterForGetMethod(dynaBean, request);

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		if (null == ui || null == ui.getCust_id()) {
			super.renderJavaScript(response, "alert('用户信息有误，请联系相关人员处理！');history.back();");
			return null;
		}

		KonkaR3Shop shop = new KonkaR3Shop();
		shop.setId(ui.getCust_id());
		shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
		if (null == shop || null == shop.getR3_code()) {
			super.renderJavaScript(response, "alert('客户信息有误，请联系相关人员处理！');history.back();");
			return null;
		}

		Pager pager = (Pager) dynaBean.get("pager");

		KonkaR3Target entity = new KonkaR3Target();
		super.copyProperties(entity, form);

		entity.setC_id(shop.getId());

		Long recordCount = getFacade().getKonkaR3TargetService().getKonkaR3TargetCount(entity);
		pager.init(recordCount, 10, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaR3Target> entityList = getFacade().getKonkaR3TargetService().getKonkaR3TargetList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}
	
	/**
	 * 添加目标
	 * @author Liang Houen
	 * @since 2015-7-15
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);
		
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		if (null == ui || null == ui.getCust_id()) {
			super.renderJavaScript(response, "alert('用户信息有误，请联系相关人员处理！');history.back();");
			return null;
		}
		
		// 遍历年份，取前10年
		SimpleDateFormat formaty = new SimpleDateFormat("yyyy");
		ArrayList<String> yearList = new ArrayList<String>();

		for (int y = -5; y < 5; y++) {
			yearList.add((Integer.valueOf(formaty.format(new Date())) - y) + "");
		}
		request.setAttribute("yearList", yearList);
		
		String mod_id = (String) dynaBean.get("mod_id");
		request.setAttribute("c_id", ui.getCust_id());
		request.setAttribute("mod_id", mod_id);
		request.setAttribute("flag", "add");
		
		return mapping.findForward("input");
	}
	
	/**
	 * 保存目标
	 * @author Liang Houen
	 * @since 2015-7-15
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);
		String mod_id = (String) dynaBean.get("mod_id");
		String flag = (String) dynaBean.get("flag");
		
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		
		if (null == ui || null == ui.getCust_id()) {
			super.renderJavaScript(response, "alert('用户信息有误，请联系相关人员处理！');history.back();");
			return null;
		}
		
		KonkaR3Target entity = new KonkaR3Target();
		super.copyProperties(entity, form);
		
		if("add".equals(flag)){  //新增目标
			KonkaR3Target en_old = new KonkaR3Target();
			en_old.setC_id(entity.getC_id());
			en_old.setSale_year(entity.getSale_year());
			Long count = super.getFacade().getKonkaR3TargetService().getKonkaR3TargetCount(en_old);
			if(count>0){
				super.renderJavaScript(response, "alert('该年度已存在记录，不能重复新增！');history.back();");
				return null;
			}
			entity.setCreate_user_id(ui.getId());
			entity.setYear_total(
					(null!=entity.getJan()?entity.getJan():0)+
					(null!=entity.getFeb()?entity.getFeb():0)+
					(null!=entity.getMar()?entity.getMar():0)+
					(null!=entity.getApr()?entity.getApr():0)+
					(null!=entity.getMay()?entity.getMay():0)+
					(null!=entity.getJune()?entity.getJune():0)+
					(null!=entity.getJuly()?entity.getJuly():0)+
					(null!=entity.getAug()?entity.getAug():0)+
					(null!=entity.getSept()?entity.getSept():0)+
					(null!=entity.getOct()?entity.getOct():0)+
					(null!=entity.getNov()?entity.getNov():0)+
					(null!=entity.getDece()?entity.getDece():0));
			super.getFacade().getKonkaR3TargetService().createKonkaR3Target(entity);
		}else{  //修改目标
			entity.setModify_user_id(ui.getId());
			entity.setYear_total(
					(null!=entity.getJan()?entity.getJan():0)+
					(null!=entity.getFeb()?entity.getFeb():0)+
					(null!=entity.getMar()?entity.getMar():0)+
					(null!=entity.getApr()?entity.getApr():0)+
					(null!=entity.getMay()?entity.getMay():0)+
					(null!=entity.getJune()?entity.getJune():0)+
					(null!=entity.getJuly()?entity.getJuly():0)+
					(null!=entity.getAug()?entity.getAug():0)+
					(null!=entity.getSept()?entity.getSept():0)+
					(null!=entity.getOct()?entity.getOct():0)+
					(null!=entity.getNov()?entity.getNov():0)+
					(null!=entity.getDece()?entity.getDece():0));
			entity.setModify_date(new Date());
			super.getFacade().getKonkaR3TargetService().modifyKonkaR3Target(entity);
		}
		
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&"+mod_id);
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
	
	/**
	 * 修改目标
	 * @author Liang Houen
	 * @since 2015-7-15
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);
		String mod_id = (String) dynaBean.get("mod_id");
		String c_id = (String) dynaBean.get("c_id");
		String sales_year = (String) dynaBean.get("sales_year");
		request.setAttribute("flag", "edit");
		
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		
		if (null == ui || null == ui.getCust_id()) {
			super.renderJavaScript(response, "alert('用户信息有误，请联系相关人员处理！');history.back();");
			return null;
		}
		
		KonkaR3Target entity = new KonkaR3Target();
		entity.setC_id(Long.parseLong(c_id));
		entity.setSale_year(Long.parseLong(sales_year));
		entity = super.getFacade().getKonkaR3TargetService().getKonkaR3Target(entity);
		super.copyProperties(form, entity);
		
		// 遍历年份，取前10年
		SimpleDateFormat formaty = new SimpleDateFormat("yyyy");
		ArrayList<String> yearList = new ArrayList<String>();

		for (int y = -5; y < 5; y++) {
			yearList.add((Integer.valueOf(formaty.format(new Date())) - y) + "");
		}
		request.setAttribute("yearList", yearList);
		
		return mapping.findForward("input");
	}
}
