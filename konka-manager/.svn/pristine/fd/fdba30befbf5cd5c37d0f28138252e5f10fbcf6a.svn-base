package com.ebiz.mmt.web.struts.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaOrderInfoTransEnsu;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.org.apache.commons.lang3.StringUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaOrderInfoTransEnsuAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

        DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		
		
		String dept_id = (String) dynaBean.get("dept_id");// 分 公 司
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");// 分 公 司
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");// 分 公 司
		String trans_ensu_status = (String) dynaBean.get("trans_ensu_status");// 签收状态
		String trans_ensu_type = (String) dynaBean.get("trans_ensu_type");// 签收方式
		//String trans_index_like = (String) dynaBean.get("trans_index_like");// 发货单号
		String trans_index_detail_like = (String) dynaBean.get("trans_index_detail_like");// 发货单号
		String r3_vbedl_like = (String) dynaBean.get("r3_vbedl_like");// 3物流单号
		String r3_vbeln_like = (String) dynaBean.get("r3_vbeln_like");// 3物流单号
		String trans_ensu_date_s = (String) dynaBean.get("trans_ensu_date_s");// 签收日期
		String trans_ensu_date_e = (String) dynaBean.get("trans_ensu_date_e");// 签收日期
		String customer_name_like = (String) dynaBean.get("customer_name_like");// 客户名称
		String r3_code_like = (String) dynaBean.get("r3_code_like");// R3客户编码
		
		KonkaOrderInfoTransEnsu konkaOrderInfoTransEnsu=new KonkaOrderInfoTransEnsu();
		
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);	
		if(null==peProdUser){
			return null;
		}
		if(null!=peProdUser.getCust_id()){
			konkaOrderInfoTransEnsu.getMap().put("cust_id", peProdUser.getCust_id());
		}
		
		
		
		
		
		if(StringUtils.isNotBlank(trans_ensu_status)){
			konkaOrderInfoTransEnsu.setTrans_ensu_status(Integer.parseInt(trans_ensu_status));
		}
		if(StringUtils.isNotBlank(trans_ensu_type)){
			konkaOrderInfoTransEnsu.getMap().put("trans_ensu_type", trans_ensu_type);
		}
		if(StringUtils.isNotBlank(trans_index_detail_like)){
			konkaOrderInfoTransEnsu.getMap().put("trans_index_detail_like", trans_index_detail_like);
		}
		if(StringUtils.isNotBlank(r3_vbedl_like)){
			konkaOrderInfoTransEnsu.getMap().put("r3_vbedl_like",r3_vbedl_like);
		}
		if(StringUtils.isNotBlank(r3_vbeln_like)){
			konkaOrderInfoTransEnsu.getMap().put("r3_vbeln_like",r3_vbeln_like);
		}
		if(StringUtils.isNotBlank(customer_name_like)){
			konkaOrderInfoTransEnsu.getMap().put("customer_name_like", customer_name_like);
		}
		if (StringUtils.isNotBlank(trans_ensu_date_s)) {
			konkaOrderInfoTransEnsu.getMap().put("trans_ensu_date_s", trans_ensu_date_s);
		}
		if (StringUtils.isNotBlank(trans_ensu_date_e)) {
			konkaOrderInfoTransEnsu.getMap().put("trans_ensu_date_e", trans_ensu_date_e);
		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			konkaOrderInfoTransEnsu.getMap().put("par_dept_id", l5_dept_id);
		} else if (StringUtils.isNotBlank(l4_dept_id)) {
			konkaOrderInfoTransEnsu.getMap().put("par_dept_id", l4_dept_id);
		} else if (StringUtils.isNotBlank(dept_id)) {
			konkaOrderInfoTransEnsu.getMap().put("par_dept_id", dept_id);
		}
		if(StringUtils.isNotBlank(r3_code_like)){
			konkaOrderInfoTransEnsu.getMap().put("r3_code_like", r3_code_like);
		}
		
			
		
		Long recordCount = super.getFacade().getKonkaOrderInfoTransEnsuService().
				getKonkaOrderInfoTransEnsuAndDetailsCount(konkaOrderInfoTransEnsu);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		konkaOrderInfoTransEnsu.getRow().setCount(pager.getRowCount());
		konkaOrderInfoTransEnsu.getRow().setFirst(pager.getFirstRow());
		List<KonkaOrderInfoTransEnsu> ensuList=super.getFacade().getKonkaOrderInfoTransEnsuService().
				getKonkaOrderInfoTransEnsuAndDetailsPaginatedList(konkaOrderInfoTransEnsu);
		request.setAttribute("entityList", ensuList);
		
		return mapping.findForward("list");
	}
}
