package com.ebiz.mmt.web.struts.customer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.KonkaMobileSailData;
import com.ebiz.mmt.domain.MobileSelectItem;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.MobileBaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaMobileSailDataAction extends MobileBaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String begindate = "";
		String enddate = "";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendarbegin = Calendar.getInstance();
		//calendarbegin.set(Calendar.MONTH, -2);
		calendarbegin.set(Calendar.DAY_OF_MONTH, calendarbegin
				.getActualMinimum(Calendar.DAY_OF_MONTH));
		begindate = df.format(calendarbegin.getTime());
		dynaBean.set("date_begin", begindate);
		Calendar calendarend = Calendar.getInstance();
		enddate = df.format(calendarend.getTime());
		dynaBean.set("date_end", enddate);
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		// super.getModPopeDom(form, request);

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		Long currentCustId = peProdUser.getCust_id();

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		KonkaMobileSailData entity = new KonkaMobileSailData();
		super.copyProperties(entity, form);

		String report_name_like = (String) dynaBean.get("report_name_like");
		String date_begin = (String) dynaBean.get("date_begin");
		String date_end = (String) dynaBean.get("date_end");
		String is_zero = (String) dynaBean.get("is_zero"); // true为0销量 false为>0销量

		entity.getMap().put("cust_id", currentCustId);
		entity.getMap().put("report_name_like", report_name_like);
		entity.setIs_del(0);


		if (StringUtils.isNotBlank(date_begin)) {
			entity.getMap().put("report_date_begin", date_begin + " 00:00:00");
		}
		if (StringUtils.isNotBlank(date_end)) {
			entity.getMap().put("report_date_end", date_end + " 23:59:59");
		}
		// 是否显示0销量
        if (GenericValidator.isBlankOrNull(is_zero)) {

        } else {
            if ("true".equals(is_zero)) {// 0销量的
                entity.getMap().put("not_Show", "and a.num = 0");
            } else {// 非0销量的
                entity.getMap().put("not_Show", "and a.num <> 0");
            }
        }
		Long recordCount = getFacade().getKonkaMobileSailDataService().getKonkaMobileSailDataCount(entity);
		pager.init(recordCount, 15, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaMobileSailData> entityList = getFacade().getKonkaMobileSailDataService()
				.getKonkaMobileSailDataPaginatedList(entity);

		/*for (KonkaMobileSailData konkaMobileSailData : entityList) {
			KonkaR3Store t = new KonkaR3Store();
			if (null != konkaMobileSailData.getDept_id()) {
				t.setStore_id(konkaMobileSailData.getDept_id());
				t = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(t);
				if (t != null) {
					konkaMobileSailData.getMap().put("r3_code", t.getR3_code());
					konkaMobileSailData.getMap().put("r3_shsdf_sn", t.getR3_shsdf_sn());
					konkaMobileSailData.getMap().put("r3_kh_name", t.getKh_name());
					konkaMobileSailData.getMap().put("store_name", t.getStore_name());
					konkaMobileSailData.getMap().put("mf_sn", t.getMf_sn());
					konkaMobileSailData.getMap().put("dept_name", t.getDept_name());
				}
			}
		}*/
		request.setAttribute("entityList", entityList);

		// 类别 下拉选项
		List<BasePdClazz> basePdClazzList = super.getFacade().getRetailMsBaseService()
				.getKonkaBasePdClazzListByClsIds();
		request.setAttribute("basePdClazzList", basePdClazzList);

		// 尺寸sizeList
		List<MobileSelectItem> sizeList = new ArrayList<MobileSelectItem>();
		for (String str : size_strs) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(str);
			t.setName(str);
			sizeList.add(t);
		}
		request.setAttribute("sizeList", sizeList);

		return mapping.findForward("list");
	}

	public ActionForward exportExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		Long currentCustId = peProdUser.getCust_id();

		DynaBean dynaBean = (DynaBean) form;
		KonkaMobileSailData entity = new KonkaMobileSailData();
		super.copyProperties(entity, form);

		String report_name_like = (String) dynaBean.get("report_name_like");
		String date_begin = (String) dynaBean.get("date_begin");
		String date_end = (String) dynaBean.get("date_end");
		String is_zero = (String) dynaBean.get("is_zero");

		entity.getMap().put("report_name_like", report_name_like);

		entity.getMap().put("cust_id", currentCustId);

		if (StringUtils.isNotBlank(date_begin)) {
			entity.getMap().put("report_date_begin", date_begin + " 00:00:00");
		}
		if (StringUtils.isNotBlank(date_end)) {
			entity.getMap().put("report_date_end", date_end + " 23:59:59");
		}
		// 是否显示0销量
        if (GenericValidator.isBlankOrNull(is_zero)) {

        } else {
            if ("true".equals(is_zero)) {// 0销量的
                entity.getMap().put("not_Show", "and a.num = 0");
            } else {// 非0销量的
                entity.getMap().put("not_Show", "and a.num <> 0");
            }
        }

		Long recordCount = getFacade().getKonkaMobileSailDataService().getKonkaMobileSailDataCount(entity);
		if (recordCount > 5000L) { // 限制导出数据条数
			super.renderJavaScript(response,
					"window.onload=function(){alert('" + this.getMessage(request, "result.data.num.many") + "');}");
			return null;
		}

		List<KonkaMobileSailData> entityList = getFacade().getKonkaMobileSailDataService().getKonkaMobileSailDataForExportList(
				entity);
//		for (KonkaMobileSailData konkaMobileSailData : entityList) {
//			KonkaR3Store t = new KonkaR3Store();
//			if (null != konkaMobileSailData.getDept_id()) {
//				t.setStore_id(konkaMobileSailData.getDept_id());
//				t = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(t);
//				if (t != null) {
//					konkaMobileSailData.getMap().put("r3_code", t.getR3_code());
//					konkaMobileSailData.getMap().put("r3_shsdf_sn", t.getR3_shsdf_sn());
//					konkaMobileSailData.getMap().put("r3_kh_name", t.getKh_name());
//					konkaMobileSailData.getMap().put("store_name", t.getStore_name());
//					konkaMobileSailData.getMap().put("mf_sn", t.getMf_sn());
//					konkaMobileSailData.getMap().put("dept_name", t.getDept_name());
		
//				}
//			}
//		}
		request.setAttribute("entityList", entityList);

		// 类别 下拉选项
		List<BasePdClazz> basePdClazzList = super.getFacade().getRetailMsBaseService()
				.getKonkaBasePdClazzListByClsIds();
		request.setAttribute("basePdClazzList", basePdClazzList);

		// 尺寸sizeList
		List<MobileSelectItem> sizeList = new ArrayList<MobileSelectItem>();
		for (String str : size_strs) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(str);
			t.setName(str);
			sizeList.add(t);
		}
		request.setAttribute("sizeList", sizeList);
		request.setAttribute("date", DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		return mapping.findForward("excel");
	}

	/*
	 * 产品型号
	 */
	public ActionForward getModel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// subcomp_id
		DynaBean dynaBean = (DynaBean) form;
		String size_id = (String) dynaBean.get("size_id");
		if (StringUtils.isNotEmpty(size_id)) {

			// String[] cls_ids = { category_id };
			// List<PePdModel> pePdModelList = super.getFacade()
			// .getRetailMsBaseService().getKonkaPePdModelListByClsIds(
			// cls_ids);
			//
			List<String> dataList = new ArrayList<String>();
			PePdModel t = new PePdModel();
			t.getMap().put("led_name_like", size_id);
			List<PePdModel> tList = new ArrayList<PePdModel>();
			tList = super.getFacade().getPePdModelService().getPePdModelList(t);
			for (PePdModel _t : tList) {
				dataList.add(StringUtils.join(new String[] { "[\"", _t.getMd_name(), "\",\"",
						String.valueOf(_t.getPd_id()), "\"]" }));
			}
			super.renderJson(response, StringUtils.join(new String[] { "[", StringUtils.join(dataList, ","), "]" }));
		}
		return null;
	}

}