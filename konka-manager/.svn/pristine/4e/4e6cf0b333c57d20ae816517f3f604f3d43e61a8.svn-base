package com.ebiz.mmt.web.struts;

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
import com.ebiz.mmt.domain.MobileSelectItemNew;
import com.ebiz.mmt.domain.PeProdUser;

public class MobileBaseAction extends BaseMobileAction {

	public String[] size_strs = { "24", "26", "28", "29", "32", "37", "39", "40", "42", "43", "46", "47", "48", "49",
			"50", "55", "57", "58", "60", "65", "84" };

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.getClass(mapping, form, request, response);
	}

	// 取网点
	public List<KonkaR3Shop> getR3Shop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		PeProdUser ui = super.getSessionUserInfo(request);
		KonkaR3Shop entity = this.getKonkaR3ShopForSelect(mapping, form, request, response, ui.getDept_id());
		return getFacade().getKonkaR3ShopService().getKonkaR3ShopList(entity);
	}

	// 取品类
	public List<MobileSelectItem> getClazz() {
		// String[] cls_ids = { "1010100", "1010200", "1010300", "1010400",
		// "1010500" };
		List<BasePdClazz> baseList = super.getFacade().getRetailMsBaseService().getKonkaBasePdClazzListByClsIds();

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
	
	public List<MobileSelectItemNew> getClazzNew() {
		// String[] cls_ids = { "1010100", "1010200", "1010300", "1010400",
		// "1010500" };
		List<BasePdClazz> baseList = super.getFacade().getRetailMsBaseService().getKonkaBasePdClazzListByClsIds();

		List<MobileSelectItemNew> list = new ArrayList<MobileSelectItemNew>();
		for (BasePdClazz _t : baseList) {
			MobileSelectItemNew t = new MobileSelectItemNew();
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

	public ActionForward getClass(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<MobileSelectItem> list = this.getClazz();
		if (list != null)
			super.renderJson(response, JSON.toJSONString(list));
		return null;
	}

	// 取尺寸
	public List<MobileSelectItem> getSizes(String id) {
		List<BasePropValItem> baseList = super.getFacade().getRetailMsBaseService().getBasePropValItemListByPdId(id);

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

	public ActionForward getSize(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("pd_id");
		List<MobileSelectItem> list = this.getSizes(id);
		if (list != null)
			super.renderJson(response, JSON.toJSONString(list));
		return null;
	}

	//
	// // 取型号
	// public ActionForward getModel(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request, HttpServletResponse response)
	// throws Exception {
	// DynaBean dynaBean = (DynaBean) form;
	// String r3_code = (String) dynaBean.get("r3_code");
	// List<PePdModel> baseList = super.getFacade().getRetailMsBaseService()
	// .getKonkaPePdModelListByClsIds(r3_code);
	//
	// List<MobileSelectItem> list = new ArrayList<MobileSelectItem>();
	// for (PePdModel _t : baseList) {
	// MobileSelectItem t = new MobileSelectItem();
	// t.setId(_t.getPd_id().toString());
	// t.setName(_t.getMd_name());
	// list.add(t);
	// }
	// if (list.size() > 0)
	// super.renderJson(response, JSON.toJSONString(list));
	//
	// return null;
	// }

	// 取样机
	public ActionForward getDemo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<KonkaMobileCategory> baseList = super.getFacade().getKonkaMobileCategoryService().getCategoryList();

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
		super.getFacade().getKonkaMobileReportHistoryService().createKonkaMobileReportHistory(entity);
	}

	public List<MobileSelectItem> getMobileCategories(int type) {
		List<MobileSelectItem> list = new ArrayList<MobileSelectItem>();
		KonkaMobileCategory t = new KonkaMobileCategory();
		t.setC_type(type);
		List<KonkaMobileCategory> _list = super.getFacade().getKonkaMobileCategoryService()
				.getKonkaMobileCategoryList(t);
		for (KonkaMobileCategory _t : _list) {
			MobileSelectItem entity = new MobileSelectItem();
			entity.setId(_t.getC_index().toString());
			entity.setName(_t.getC_name());
			list.add(entity);
		}
		return list;
	}

	public String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);

		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	public String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}
}
