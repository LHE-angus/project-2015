package com.ebiz.mmt.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileCategory;
import com.ebiz.mmt.domain.KonkaMobileSailDatas;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.MobileSelectItem;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.MobileBaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**w
 * @author Jiang,JiaYong
 * @version 2013-04-28
 */
public class KonkaMobileSailDatasAction extends MobileBaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		KonkaMobileSailDatas entity = new KonkaMobileSailDatas();
		super.copyProperties(entity, form);

		String report_name_like = (String) dynaBean.get("report_name_like");
		String date_begin = (String) dynaBean.get("date_begin");
		String date_end = (String) dynaBean.get("date_end");

		entity.getMap().put("report_name_like", report_name_like);

		if (StringUtils.isNotBlank(date_begin)) {
			entity.getMap().put("report_date_begin",
			        DateUtils.parseDate(date_begin + " 00:00:00", new String[] { "yyy-MM-dd HH:mm:ss" }));
		}
		if (StringUtils.isNotBlank(date_end)) {
			entity.getMap().put("report_date_end",
			        DateUtils.parseDate(date_end + " 23:59:59", new String[] { "yyy-MM-dd HH:mm:ss" }));
		}

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		String dept = "";
		if (peProdUser != null) {
			KonkaMobileCategory _t = new KonkaMobileCategory();
			_t.setC_index(peProdUser.getDept_id());
			dept = super.getFacade().getKonkaMobileCategoryService().getKonkaMobileDept(_t);
		}
		if (null != dept) // 分公司用户
			entity.setSubcomp_id(Long.parseLong(dept));
		else
			// 总部用户
			request.setAttribute("isFgsUser", "true");

		Long recordCount = getFacade().getKonkaMobileSailDatasService().getKonkaMobileSailDatasCount(entity);
		pager.init(recordCount, 15, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaMobileSailDatas> entityList = getFacade().getKonkaMobileSailDatasService()
		        .getKonkaMobileSailDatasPaginatedList(entity);
		for (KonkaMobileSailDatas tt : entityList) {
			KonkaR3Store t = new KonkaR3Store();
			if (null != tt.getDept_id()) {
				t.setStore_id(tt.getDept_id());
				t = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(t);
				if (t != null) {
					tt.getMap().put("store_name", t.getStore_name());
					tt.getMap().put("r3_kh_name", t.getKh_name());
					tt.getMap().put("dept_name", t.getDept_name());
				}
			}
		}
		request.setAttribute("entityList", entityList);

		// 分公司,下拉选项
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		konkaDept.setDept_id(peProdUser.getDept_id());// ++2013-04-18
		List<KonkaDept> baseDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		request.setAttribute("baseDeptList", baseDeptList);

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

	/*
	 * 办事处
	 */
	public ActionForward getDept(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		// subcomp_id
		DynaBean dynaBean = (DynaBean) form;
		String subcomp_id = (String) dynaBean.get("subcomp_id");
		if (null != subcomp_id && "null" != subcomp_id && StringUtils.isNotBlank(subcomp_id)) {
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setPar_id(new Long(subcomp_id));

			List<KonkaDept> baseDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);

			List<String> dataList = new ArrayList<String>();
			for (KonkaDept _t : baseDeptList) {
				dataList.add(StringUtils.join(new String[] { "[\"", _t.getDept_name(), "\",\"",
				        String.valueOf(_t.getDept_id()), "\"]" }));
			}
			super.renderJson(response, StringUtils.join(new String[] { "[", StringUtils.join(dataList, ","), "]" }));
		}
		return null;
	}

	/*
	 * 产品型号
	 */
	public ActionForward getModel1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		// subcomp_id
		DynaBean dynaBean = (DynaBean) form;
		String size_id = (String) dynaBean.get("size_id");
		String category_id = (String) dynaBean.get("category_id");
		PePdModel t = new PePdModel();
		t.setIs_del(0);
		if (StringUtils.isNotEmpty(size_id)) {
			t.setMd_size(size_id);
		}
		if (StringUtils.isNotEmpty(category_id)) {
			t.setCls_id(Long.valueOf(category_id));
		}
		List<String> dataList = new ArrayList<String>();
		t.getMap().put("order_by_pd_name_desc", true);
		List<PePdModel> tList = new ArrayList<PePdModel>();
		tList = super.getFacade().getPePdModelService().getPePdModelList(t);
		for (PePdModel _t : tList) {
			dataList.add(StringUtils.join(new String[] { "[\"", _t.getMd_name(), "\",\"",
			        String.valueOf(_t.getPd_id()), "\"]" }));
		}
		super.renderJson(response, StringUtils.join(new String[] { "[", StringUtils.join(dataList, ","), "]" }));
		return null;
	}

	public ActionForward getModel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		String state = "0";
		JSONObject result = new JSONObject();
		JSONArray list = new JSONArray();

		DynaBean dynaBean = (DynaBean) form;
		String size_id = (String) dynaBean.get("size_id");
		String category_id = (String) dynaBean.get("category_id");
		PePdModel t = new PePdModel();
		t.setIs_del(0);
		if (StringUtils.isNotEmpty(size_id)) {
			t.setMd_size(size_id);
		} else {
			t.getMap().put("not_select", "1=2");
		}
		if (StringUtils.isNotEmpty(category_id)) {
			t.setCls_id(Long.valueOf(category_id));
		}
		t.getMap().put("order_by_pd_name_desc", true);
		List<PePdModel> pdList = new ArrayList<PePdModel>();
		pdList = super.getFacade().getPePdModelService().getPePdModelList(t);
		if (null != pdList && pdList.size() > 0) {
			for (PePdModel pm : pdList) {
				JSONObject obj = new JSONObject();
				obj.put("pd_id", pm.getPd_id());
				obj.put("md_name", pm.getMd_name());
				list.put(obj);
			}
			state = "1";
			result.put("list", list);
		}

		result.put("state", state);
		super.renderJson(response, result.toString());
		return null;
	}

}