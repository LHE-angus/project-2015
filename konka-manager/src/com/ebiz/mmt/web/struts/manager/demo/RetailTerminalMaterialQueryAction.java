package com.ebiz.mmt.web.struts.manager.demo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileCategory;
import com.ebiz.mmt.domain.KonkaMobileThingsUseReport;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wang Hao
 */
public class RetailTerminalMaterialQueryAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(15);
		String wl_index=(String) dynaBean.get("wl_index");
		
		String report_name_like = (String)dynaBean.get("report_name_like");
		
		//分公司,下拉选项
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		List<KonkaDept> baseDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		request.setAttribute("baseDeptList", baseDeptList);
		
		//类别 下拉选项
		KonkaMobileCategory kmc = new KonkaMobileCategory();
		kmc.setIs_type(1);
		kmc.setIs_del(0);
		kmc.setC_type(4);
		List<KonkaMobileCategory> categoryList = super.getFacade().getKonkaMobileCategoryService().getKonkaMobileCategoryListForSelect(kmc);
		request.setAttribute("categoryList", categoryList);
		
		KonkaMobileThingsUseReport entity=new KonkaMobileThingsUseReport();
		super.copyProperties(entity, form);
		
		entity.getMap().put("report_name_like", report_name_like);
		if(wl_index!=null&& wl_index !=""){
			entity.setThing_id(new Long(wl_index));
		}
		entity.getMap().put("busi", 1);
		entity.getMap().put("retail", 1);
		Long recordCount = getFacade().getKonkaMobileThingsUseReportService().getKonkaMobileThingsUseReportCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaMobileThingsUseReport> entityList = getFacade().getKonkaMobileThingsUseReportService().getKonkaMobileThingsUseReportPaginatedList(entity);
		
		request.setAttribute("entityList", entityList);
		
		return mapping.findForward("list");
	}
}
