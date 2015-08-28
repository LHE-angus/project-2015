package com.ebiz.mmt.web.struts.manager.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaMobileCategory;
import com.ebiz.mmt.domain.KonkaMobileRetailRest;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaMobileRetailRestAction extends BaseAction {

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

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_INFO);
		String dept = "";
		if (peProdUser != null) {
			KonkaMobileCategory _t = new KonkaMobileCategory();
			_t.setC_index(peProdUser.getDept_id());
			dept = super.getFacade().getKonkaMobileCategoryService()
					.getKonkaMobileDept(_t);
		}

		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(15);

		String retail_name_like = (String) dynaBean.get("retail_name_like");
		String retail_date_begin = (String) dynaBean.get("retail_date_begin");
		String retail_date_end = (String) dynaBean.get("retail_date_end");
		String query_or_count = (String) dynaBean.get("query_or_count");
		String status = (String) dynaBean.get("status");

		KonkaMobileRetailRest entity = new KonkaMobileRetailRest();

		if (null != dept)
			entity.setOffice_id(Long.parseLong(dept));

		entity.getMap().put("retail_name_like", retail_name_like);
		if (GenericValidator.isLong(status))
			entity.setStatus(Long.valueOf(status));

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtils.isNotBlank(retail_date_begin)) {
			entity.getMap().put("retail_date_begin",
					simpleDateFormat.parse(retail_date_begin));
		}
		if (StringUtils.isNotBlank(retail_date_end)) {
			entity.getMap().put("retail_date_end",
					simpleDateFormat.parse(retail_date_end));
		}

		Long recordCount = 0l;
		List<KonkaMobileRetailRest> entityList = new ArrayList<KonkaMobileRetailRest>();

		if (StringUtils.isBlank(query_or_count)
				|| query_or_count.equals("query")) { // 查询
			recordCount = getFacade().getKonkaMobileRetailRestService()
					.getKonkaMobileRetailRestCount(entity);
		} else { // 统计
			entity.setStatus(1L); // 查询审批通过的
			recordCount = getFacade().getKonkaMobileRetailRestService()
					.getRetailRestStatisticsCount(entity);
		}

		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		if (StringUtils.isBlank(query_or_count)
				|| query_or_count.equals("query")) { // 查询
			entityList = getFacade().getKonkaMobileRetailRestService()
					.getKonkaMobileRetailRestPaginatedList(entity);
		} else { // 统计
			entityList = getFacade().getKonkaMobileRetailRestService()
					.getRetailRestStatisticsPaginatedList(entity);
		}
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String tree_param = (String) dynaBean.get("tree_param");
		String status = (String) dynaBean.get("status");
		String[] id_s = request.getParameterValues("id_s");

		KonkaMobileRetailRest entity = new KonkaMobileRetailRest();
		entity.setStatus(Long.valueOf(status));
		for (String string : id_s) {
			entity.setId(Long.valueOf(string));
			super.getFacade().getKonkaMobileRetailRestService()
					.modifyKonkaMobileRetailRest(entity);
		}

		super.saveMessage(request, "entity.inserted_success");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("tree_param=" + tree_param);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
}