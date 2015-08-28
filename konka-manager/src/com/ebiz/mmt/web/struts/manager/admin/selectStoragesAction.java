package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.ImportDataTypes;
import com.ebiz.mmt.domain.KonkaMobileStorages;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Xu,Wei
 * 
 */
public class selectStoragesAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		KonkaMobileStorages entity1 = new KonkaMobileStorages();
//		entity1.setIs_del(0);
//		entity1.setPar_id(0l);
//		List<KonkaMobileStorages> entity1List = getFacade().getKonkaMobileStoragesService().getKonkaMobileStoragesList(entity1);
//		request.setAttribute("entity1List", entity1List);
		
		request.setAttribute("sybDeptInfoList", super.getDeptInfoList(mapping, form, request, response, null));
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(15);
//		String storage_par_name = (String) dynaBean.get("storage_par_name");
		String storage_type = (String) dynaBean.get("storage_type");
		String storage_par_id = (String) dynaBean.get("storage_par_id");
		String storage_name = (String) dynaBean.get("storage_name");
		
		KonkaMobileStorages entity = new KonkaMobileStorages();
		if(StringUtils.isNotBlank(storage_par_id)){
			entity.setPar_id(new Long(storage_par_id));
			if(StringUtils.isNotBlank(storage_type)){
			entity.setStorage_type(new Integer(storage_type));
			}
			if(StringUtils.isNotBlank(storage_name)){
		        entity.setStorage_name(storage_name);
			}
		}else{
			if(StringUtils.isNotBlank(storage_type)&& storage_type.equals("1")){
				ImportDataTypes data = new ImportDataTypes();    
				data.setData_type(5l);
				List<ImportDataTypes> dataList= super.getFacade().getImportDataTypesService().getImportDataTypesList(data);
				request.setAttribute("sybDeptInfoList", dataList);
				entity.setStorage_type(new Integer(storage_type));
//				if(StringUtils.isNotBlank(storage_par_name)){
//					entity.setStorage_com(new Long(storage_par_name));
//				}
			}
			if(StringUtils.isNotBlank(storage_type)&& storage_type.equals("2")){
				request.setAttribute("sybDeptInfoList", super.getDeptInfoList(mapping, form, request, response, null));
				entity.setStorage_type(new Integer(storage_type));
//				if(StringUtils.isNotBlank(storage_par_name)){
//					entity.setStorage_areacom(new Long(storage_par_name));
//				}
			}
		}
		
		entity.setIs_del(0);
		Long recordCount = getFacade().getKonkaMobileStoragesService().getKonkaMobileStoragesCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaMobileStorages> entityList = getFacade().getKonkaMobileStoragesService().getKonkaMobileStoragesPaginatedList(entity);
		super.copyProperties(form, entity);
		request.setAttribute("entityList", entityList);
		

		
		return new ActionForward("/admin/selectStorages/list.jsp");
	}

}
