package com.ebiz.mmt.web.struts.mobile.html;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.KonkaShopDevelop;
import com.ebiz.mmt.domain.KonkaShopVisit;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Wang Hao
 */
public class VisitDoneAction extends BaseAction {

	public ActionForward getCustomers(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_MOBILE);
		KonkaShopDevelop shopDevelop = new KonkaShopDevelop();
		shopDevelop.setUser_id(peProdUser.getId());
		List<KonkaShopDevelop> shop_List = super.getFacade()
				.getKonkaShopDevelopService().getKonkaShopDevelopList(
						shopDevelop);
		super.renderJson(response, JSON.toJSONString(shop_List));
		// //System.out.print(JSON.toJSONString(shop_List));
		return null;
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaBean lazyForm = (DynaBean) form;
		String date = (String) lazyForm.get("date");
		KonkaShopVisit _visit = new KonkaShopVisit();
		super.copyProperties(_visit, form);
		KonkaShopVisit visit = new KonkaShopVisit();
		visit.setShop_id(_visit.getShop_id());
		_visit.setVisit_count(super.getFacade().getKonkaShopVisitService()
				.getKonkaShopVisitCount(visit) + 1);
		if (StringUtils.isNotEmpty(date)) {
			SimpleDateFormat DataFormat = new SimpleDateFormat("MM/dd/yyyy");
			_visit.setVisit_date(DataFormat.parse(date));
		} else {
			super.renderText(response, "请填写拜访日期！");
			return null;
		}
		if (_visit.getShop_id() == null) {
			super.renderText(response, "请选择拜访对象！");
			return null;
		}
		if (StringUtils.isEmpty(_visit.getVisit_note())) {
			super.renderText(response, "请填写拜访笔记！");
			return null;
		}
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(
				Constants.USER_MOBILE);
		_visit.setUser_id(ui.getId());
		_visit.setUser_name(ui.getUser_name());
		Long id = super.getFacade().getKonkaShopVisitService()
				.createKonkaShopVisit(_visit);
		
		if(_visit.getVisit_count()==1){//如果是第一次拜访，将开拓状态改为正在开拓。
			KonkaShopDevelop shopDevelop = new KonkaShopDevelop();
			shopDevelop.setShop_id(_visit.getShop_id());
			shopDevelop.setUser_id(ui.getId());
			
			shopDevelop = super.getFacade().getKonkaShopDevelopService().getKonkaShopDevelop(shopDevelop);
			
			if(shopDevelop!=null){
				KonkaShopDevelop shop = new KonkaShopDevelop();
				shop.setId(shopDevelop.getId());
				shop.setDevelop_status(Long.valueOf("1"));
				super.getFacade().getKonkaShopDevelopService().modifyKonkaShopDevelop(shop);
			}
		}
		// 写日志
		createMobileSysOperLog(request, "KonkaShopVisit", id, "711040", "写入",
				"手机端-拜访执行-登记");
		super.renderText(response, "success");
		return null;
	}
}
