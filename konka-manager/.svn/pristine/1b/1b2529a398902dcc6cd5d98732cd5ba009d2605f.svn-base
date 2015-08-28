package com.ebiz.mmt.web.struts.m.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.BasePropValItem;
import com.ebiz.mmt.domain.KonkaMobileCategory;
import com.ebiz.mmt.domain.KonkaMobileReportHistory;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.MobileSelectItem;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.struts.BaseMobileAction;

public class MobileBaseAction extends BaseMobileAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.getClass(mapping, form, request, response);
	}

	// 取网点
	public List<KonkaR3Shop> getR3Shop(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PeProdUser ui = super.getSessionUserInfo(request);
		KonkaR3Shop entity = this.getKonkaR3ShopForSelect(mapping, form, request, response, ui.getDept_id());
		return getFacade().getKonkaR3ShopService().getKonkaR3ShopList(entity);
	}
	
	// 取品类
	public List<MobileSelectItem> getClazz() {
		// String[] cls_ids = { "1010100", "1010200", "1010300", "1010400",
		// "1010500" };
		List<BasePdClazz> baseList = super.getFacade().getRetailMsBaseService()
				.getKonkaBasePdClazzListByClsIds();

		List<MobileSelectItem> list = new ArrayList<MobileSelectItem>();
		for (BasePdClazz _t : baseList) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(_t.getCls_id().toString());
			String[] fullname = _t.getFull_name().split(",");
			t.setName(fullname[fullname.length - 1]);
			list.add(t);
		}
		if (list.size() > 0)
			return list;
		else
			return null;
	}

	public ActionForward getClass(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<MobileSelectItem> list = this.getClazz();
		if (list != null)
			super.renderJson(response, JSON.toJSONString(list));
		return null;
	}

	// 取尺寸
	public List<MobileSelectItem> getSizes(String id) {
		List<BasePropValItem> baseList = super.getFacade()
				.getRetailMsBaseService().getBasePropValItemListByPdId(id);

		List<MobileSelectItem> list = new ArrayList<MobileSelectItem>();
		for (BasePropValItem _t : baseList) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(_t.getProp_item_id().toString());
			t.setName(_t.getProp_item_name());
			list.add(t);
		}
		if (list.size() > 0)
			return list;
		else
			return null;
	}

	public ActionForward getSize(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("pd_id");
		List<MobileSelectItem> list = this.getSizes(id);
		if (list != null)
			super.renderJson(response, JSON.toJSONString(list));
		return null;
	}
//
//	// 取型号
//	public ActionForward getModel(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		DynaBean dynaBean = (DynaBean) form;
//		String r3_code = (String) dynaBean.get("r3_code");
//		List<PePdModel> baseList = super.getFacade().getRetailMsBaseService()
//				.getKonkaPePdModelListByClsIds(r3_code);
//
//		List<MobileSelectItem> list = new ArrayList<MobileSelectItem>();
//		for (PePdModel _t : baseList) {
//			MobileSelectItem t = new MobileSelectItem();
//			t.setId(_t.getPd_id().toString());
//			t.setName(_t.getMd_name());
//			list.add(t);
//		}
//		if (list.size() > 0)
//			super.renderJson(response, JSON.toJSONString(list));
//
//		return null;
//	}

	// 取样机
	public ActionForward getDemo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<KonkaMobileCategory> baseList = super.getFacade()
				.getKonkaMobileCategoryService().getCategoryList();

		List<MobileSelectItem> list = new ArrayList<MobileSelectItem>();
		for (KonkaMobileCategory _t : baseList) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(_t.getC_index().toString());
			t.setName(_t.getC_name());
			list.add(t);
		}
		if (list.size() > 0)
			super.renderJson(response, JSON.toJSONString(list));

		return null;
	}

	// 存历史上报数据信息

	public void insertReportHistory(KonkaMobileReportHistory entity) {
		entity.setReport_time(new Date());
		super.getFacade().getKonkaMobileReportHistoryService()
				.createKonkaMobileReportHistory(entity);
	}

	public List<MobileSelectItem> getMobileCategories(int type) {
		List<MobileSelectItem> list = new ArrayList<MobileSelectItem>();
		KonkaMobileCategory t = new KonkaMobileCategory();
		t.setC_type(type);
		List<KonkaMobileCategory> _list = super.getFacade()
				.getKonkaMobileCategoryService().getKonkaMobileCategoryList(t);
		for (KonkaMobileCategory _t : _list) {
			MobileSelectItem entity = new MobileSelectItem();
			entity.setId(_t.getC_index().toString());
			entity.setName(_t.getC_name());
			list.add(entity);
		}
		return list;
	}
}
