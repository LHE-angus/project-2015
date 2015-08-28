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

import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileCategory;
import com.ebiz.mmt.domain.KonkaMobileSailsReturn;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.MobileSelectItem;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.MobileBaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaMobileSailsReturnAction extends MobileBaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(15);

		KonkaMobileSailsReturn entity = new KonkaMobileSailsReturn();
		super.copyProperties(entity, form);

		String report_name_like = (String) dynaBean.get("report_name_like");
		String date_begin = (String) dynaBean.get("date_begin");
		String date_end = (String) dynaBean.get("date_end");

		entity.getMap().put("report_name_like", report_name_like);

		if (StringUtils.isNotBlank(date_begin)) {
			entity.getMap().put(
					"report_date_begin",
					DateUtils.parseDate(date_begin + " 00:00:00",
							new String[] { "yyy-MM-dd HH:mm:ss" }));
		}
		if (StringUtils.isNotBlank(date_end)) {
			entity.getMap().put(
					"report_date_end",
					DateUtils.parseDate(date_end + " 23:59:59",
							new String[] { "yyy-MM-dd HH:mm:ss" }));
		}

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_INFO);
		String dept = "";
		if (peProdUser != null) {
			KonkaMobileCategory _t = new KonkaMobileCategory();
			_t.setC_index(peProdUser.getDept_id());
			dept = super.getFacade().getKonkaMobileCategoryService()
					.getKonkaMobileDept(_t);
		}
		if (null != dept) // 分公司用户
			entity.setSubcomp_id(Long.parseLong(dept));
		else
			// 总部用户
			request.setAttribute("isFgsUser", "true");

		Long recordCount = getFacade().getKonkaMobileSailsReturnService()
				.getKonkaMobileSailsReturnCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaMobileSailsReturn> entityList = getFacade()
				.getKonkaMobileSailsReturnService()
				.getKonkaMobileSailsReturnPaginatedList(entity);
		for (KonkaMobileSailsReturn tt : entityList) {
			KonkaR3Store t = new KonkaR3Store();
			if (null != tt.getDept_id()) {
				t.setStore_id(tt.getDept_id());
				t = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(
						t);
				if (t != null) {
					tt.getMap().put("store_name", t.getStore_name());
					tt.getMap().put("dept_name", t.getDept_name());
				}
			}
		}
		request.setAttribute("entityList", entityList);

		// 分公司,下拉选项
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		konkaDept.setDept_id(peProdUser.getDept_id());// ++2013-04-18
		List<KonkaDept> baseDeptList = super.getFacade().getKonkaDeptService()
				.getKonkaDeptList(konkaDept);
		request.setAttribute("baseDeptList", baseDeptList);

		// 类别 下拉选项
		List<BasePdClazz> basePdClazzList = super.getFacade()
				.getRetailMsBaseService().getKonkaBasePdClazzListByClsIds();
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

}