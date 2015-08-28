package com.ebiz.mmt.web.struts.manager.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.ChannelDataImport;
import com.ebiz.mmt.domain.ImportDataTypes;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * 客户管理 > 渠道统计分析 > 客户结算统计
 * 
 * @author 高永祥
 */
public class ChannelCustomerBillingAnalysisAction extends BaseAction {

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
		super.encodeCharacterForGetMethod(dynaBean, request);
		String column_1 = (String)dynaBean.get("column_1");
		String column_11 = (String)dynaBean.get("column_11");
		String date = (String) dynaBean.get("date");
		String date_start = (String) dynaBean.get("date_start");
		String date_end = (String) dynaBean.get("date_end");
		
		ImportDataTypes  importDataTypes  = new ImportDataTypes();
		importDataTypes.setPar_id(0);
		
		List<ImportDataTypes> idtList = super.getFacade().getImportDataTypesService()
				.getImportDataTypesList(importDataTypes);
		
		request.setAttribute("idtList", idtList);
		
		ChannelDataImport entity = new ChannelDataImport();
		entity.getMap().put("count2", true);
//		entity.getMap().put("group", true);
		if(StringUtils.isNotBlank(column_1)){
			entity.setColumn_1(column_1);
		}
		if(StringUtils.isNotBlank(column_11)){
			entity.setColumn_11(column_11);
		}
		if("0".equals(date)){
			entity.getMap().put("date1", true);
			if(StringUtils.isNotBlank(date_start)){
				String s = date_start.replaceAll("-", "");
				String s_date = s.substring(0, 8);
				entity.getMap().put("date_start", s_date);
				}
			if(StringUtils.isNotBlank(date_end)){
				String s = date_end.replaceAll("-", "");
				String e_date = s.substring(0, 8);
				entity.getMap().put("date_end", e_date);
				}
		}
			
		if("1".equals(date)){
			entity.getMap().put("date2", true);
			if(StringUtils.isNotBlank(date_start)){
				String s = date_start.replaceAll("-", "");
				String s_date = s.substring(0, 6);
				entity.getMap().put("date_start", s_date);
				}
			if(StringUtils.isNotBlank(date_end)){
				String s = date_end.replaceAll("-", "");
				String e_date = s.substring(0, 6);
				entity.getMap().put("date_end", e_date);
				}
		}
		
		Pager pager = (Pager)dynaBean.get("pager");
		Long recordCount =super.getFacade().getChannelDataImportService().getChannelDataImportCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
		List<ChannelDataImport> entityList=super.getFacade().getChannelDataImportService().getChannelDataImportPaginatedListForStat(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}
	
	public ActionForward toExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		super.HtmltoExcel(request, response);
		return null;
	}
	
}
