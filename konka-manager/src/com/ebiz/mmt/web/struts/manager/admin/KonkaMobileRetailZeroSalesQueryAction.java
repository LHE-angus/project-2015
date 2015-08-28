package com.ebiz.mmt.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wang Hao
 */
public class KonkaMobileRetailZeroSalesQueryAction extends BaseAction {

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
		super.encodeCharacterForGetMethod(dynaBean, request);
		String report_date_start = (String) dynaBean.get("report_date_start");
		String report_date_end = (String) dynaBean.get("report_date_end");
		String subcomp_id = (String) dynaBean.get("subcomp_id");
		String office_id = (String) dynaBean.get("office_id");
		
		PePdModel entity = new PePdModel();
		entity.getMap().put("zeroSales", "true");
		entity.setIs_del(0);
		if (StringUtils.isNotBlank(report_date_start)) {
			entity.getMap().put("report_date_start", report_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(report_date_end)) {
			entity.getMap().put("report_date_end", report_date_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(subcomp_id)) {
			entity.getMap().put("subcomp_id", subcomp_id);
		}
		if (StringUtils.isNotBlank(office_id)) {
			entity.getMap().put("office_id", office_id);
		}
		
		Long recordCount = super.getFacade().getPePdModelService().getPePdModelCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<PePdModel> entityList = super.getFacade().getPePdModelService()
				.getPePdModulePaginatedIncludeMdNameList(entity);
		for(PePdModel t : entityList){
			if(null!=t.getMap().get("full_name")){
				String[] cls = t.getMap().get("full_name").toString().split(",");
				t.getMap().put("full_name", cls[cls.length-1]);
			}
		}

		request.setAttribute("entityList", entityList);
		
		KonkaDept konkaDept = new KonkaDept();

		konkaDept.setDept_type(3);

		List<KonkaDept> baseDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);

		request.setAttribute("DeptList", baseDeptList);
		return mapping.findForward("list");
	}
	/*
	 * 办事处
	 */
	public ActionForward getDept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		//subcomp_id
		DynaBean dynaBean = (DynaBean) form;
		String subcomp_id = (String) dynaBean.get("subcomp_id");
		if (StringUtils.isNotEmpty(subcomp_id)) {
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setPar_id(new Long(subcomp_id));
			
			List<KonkaDept> baseDeptList = super.getFacade()
			.getKonkaDeptService().getKonkaDeptList(konkaDept);
			
			List<String> dataList = new ArrayList<String>();
			for (KonkaDept _t : baseDeptList) {
				dataList.add(StringUtils.join(new String[] { "[\"", _t.getDept_name(), "\",\"",
						String.valueOf(_t.getDept_id()), "\"]" }));
			}
			super.renderJson(response, StringUtils.join(new String[] { "[", StringUtils.join(dataList, ","), "]" }));
		}
		return null;
	}
	
}
