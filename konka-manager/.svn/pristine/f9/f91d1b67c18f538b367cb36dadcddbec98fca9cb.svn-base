package com.ebiz.mmt.web.struts.jxc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JxcPd;
import com.ebiz.mmt.domain.JxcStockBillDetails;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaJxcFhBillDetails;
import com.ebiz.mmt.domain.KonkaR3MmtMatch;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;

/**
 * @author du,zhiming
 * @version 2011-11-17
 */
@SuppressWarnings("unchecked")
public class KonkaJxcReceiveSendCompareAction extends BaseJxcAction{
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return this.list(mapping, form, request, response);
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		

		super.setNaviStringToRequestScope(form, request);
		
		PeProdUser peProdUser = super.getSessionUserInfo(request);
		if (null == peProdUser) {
			return null;
		}
		DynaBean dynaBean = (DynaBean) form;
		String search_flag = (String)dynaBean.get("search_flag");//查询标记
		String start_date = (String)dynaBean.get("start_date");
		String end_date = (String)dynaBean.get("end_date");
//		String pd_id = (String)dynaBean.get("pd_id");
		String branch_or_wd_id = (String)dynaBean.get("branch_or_wd_id");
		String is_confirm = (String)dynaBean.get("is_confirm");
		
		Date now = new Date();
		if(StringUtils.isBlank(start_date)){
			start_date = DateFormatUtils.format(now, "yyyy-MM-") + "01";
		}
		if(StringUtils.isBlank(end_date)){
			end_date = DateFormatUtils.format(now, "yyyy-MM-dd");
		}
		if(StringUtils.isNotBlank(branch_or_wd_id)){
			KonkaR3Shop  konkaR3Shop = super.getKonkaR3ShopById(new Long(branch_or_wd_id));//取R3网点名称 回显列表
			if(konkaR3Shop != null){
				request.setAttribute("shopName", konkaR3Shop.getCustomer_name());
			}
		}
		
		
		List<KonkaR3Shop> konkaR3ShopList = super.getShopListByDeptId(peProdUser.getDept_id());
		List<PePdModel> pePdModelList = super.getPePdModelListByDeptId(peProdUser.getDept_id());
		request.setAttribute("konkaR3ShopList", konkaR3ShopList);
		request.setAttribute("pePdModelList", pePdModelList);
		
		KonkaJxcFhBillDetails  entity = new KonkaJxcFhBillDetails();
		List<KonkaJxcFhBillDetails> entityList= new ArrayList<KonkaJxcFhBillDetails>();
		List<Map> resultList= new ArrayList<Map>();
		if(StringUtils.isNotBlank(search_flag)){//查询列表
			super.copyProperties(entity, dynaBean);
			entity.getMap().put("start_date", start_date);
			entity.getMap().put("end_date", end_date);
			entity.getMap().put("is_confirm", is_confirm);
			resultList = getFacade().getKonkaJxcFhBillDetailsService().getKonkaJxcFhBillDetailsSumPdCountList(entity);
			if( resultList != null && resultList.size()>0){
				for(Map map :resultList){
					KonkaJxcFhBillDetails entity_temp = new KonkaJxcFhBillDetails();
					if(map.get("PD_ID") != null && GenericValidator.isLong(map.get("PD_ID").toString())){
						entity_temp.setPd_id( new Long (map.get("PD_ID").toString()));
					}
					if(map.get("PD_NAME") != null){
						entity_temp.setPd_name(map.get("PD_NAME").toString());
					}
					if(map.get("PD_TYPE_NAME") != null){
						entity_temp.setPd_type_name( map.get("PD_TYPE_NAME").toString());
					}
					if(map.get("BRAND_NAME") != null){
						entity_temp.setBrand_name(map.get("BRAND_NAME").toString());
					}
					if(map.get("BRANCH_OR_WD_ID") != null && GenericValidator.isLong(map.get("BRANCH_OR_WD_ID").toString()) ){
						entity_temp.setBranch_or_wd_id( new Long(map.get("BRANCH_OR_WD_ID").toString()));
					}
//					if(map.get("CONFIRM") != null && GenericValidator.isLong(map.get("CONFIRM").toString()) ){//存放是否确认字段
//						entity_temp.setIs_pc(new Integer(map.get("CONFIRM").toString()));
//					}
					if(map.get("PDSUMCOUNT") != null && GenericValidator.isLong(map.get("PDSUMCOUNT").toString())){
						entity_temp.setCount( new Long (map.get("PDSUMCOUNT").toString()));
					}
					
					/********************************查询进货总数列表 Start************************************/
					
					JxcStockBillDetails entity_jh = new JxcStockBillDetails();
					KonkaR3MmtMatch konkaR3MmtMatch = new KonkaR3MmtMatch();
					if(GenericValidator.isLong(branch_or_wd_id)){//网点进货统计
						
						konkaR3MmtMatch = super.getKonkaR3MmtMatchByR3Id(new Long(branch_or_wd_id));
						if(konkaR3MmtMatch != null){
							entity_jh.setShop_id(konkaR3MmtMatch.getMmt_shop_id());
							List<JxcPd> jxc_pd_list = super.getJxcPdByPdAndShopId(new Long (map.get("PD_ID").toString()), konkaR3MmtMatch.getMmt_shop_id());
							if(jxc_pd_list != null && jxc_pd_list.size()>0){
								String pd_ids = "";
								for(JxcPd pd :jxc_pd_list){
									pd_ids += pd.getId() +",";
								}
								if( pd_ids.lastIndexOf(",")>-1){
									pd_ids = pd_ids.substring(0, pd_ids.lastIndexOf(","));
								}
								//logger.info("pd_ids=="+pd_ids);
								if(StringUtils.isNotBlank(pd_ids)){
									entity_jh.getMap().put("pd_ids", pd_ids);
								}else{//无对应型号串
									entity_jh.setPd_id(-1l);
								}
							}else{//无对应型号
								entity_jh.setPd_id(-1l);
							}
						}else{//R3网点没有匹配买买提网点 
							entity_jh.setShop_id(-1l);
							entity_jh.setPd_id(-1l);
						}
					}else{//无网点信息或非法信息
						entity_jh.setShop_id(-1l);
						entity_jh.setPd_id(-1l);
					}
					
					
					//entity.setPd_id(new Long(pd_id));
					entity_jh.getMap().put("start_date", start_date);
					entity_jh.getMap().put("end_date", end_date);
					Long jh_count = getFacade().getJxcStockBillDetailsService().getJxcstockBillDetailsSumPdCountNotSrc(entity_jh);
					
					/********************************查询进货总数列表 end************************************/
					entity_temp.getMap().put("jh_count", jh_count);
					
					entityList.add(entity_temp);
					
				}
			}
		}
		dynaBean.set("start_date", start_date);
		dynaBean.set("end_date", end_date);
		dynaBean.set("search_flag", search_flag);
		dynaBean.set("is_confirm", is_confirm);//放到表单中提供传递参数

		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}
	
	public ActionForward viewFh(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		
		super.setNaviStringToRequestScope(form, request);
		
		PeProdUser peProdUser = super.getSessionUserInfo(request);
		if (null == peProdUser) {
			return null;
		}
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		KonkaDept konkaDept = super.getSuperiorForDeptType(ui.getDept_id(), new Integer(3));
		String dept_name = konkaDept.getDept_desc();
		request.setAttribute("dept_name", dept_name);//获取部门名称
		
		DynaBean dynaBean = (DynaBean) form;
		String start_date = (String)dynaBean.get("start_date");
		String end_date = (String)dynaBean.get("end_date");
		String is_confirm = (String)dynaBean.get("is_confirm_temp");
		String pd_id = (String)dynaBean.get("pd_id");
		String branch_or_wd_id = (String)dynaBean.get("branch_or_wd_id");
		String pd_fh_num = (String)dynaBean.get("pd_fh_num");
		String pd_jh_num = (String)dynaBean.get("pd_jh_num");
		if(StringUtils.isNotBlank(branch_or_wd_id)){
			KonkaR3Shop  konkaR3Shop = super.getKonkaR3ShopById(new Long(branch_or_wd_id));//取R3网点名称 回显列表
			if(konkaR3Shop != null){
				request.setAttribute("shopName", konkaR3Shop.getCustomer_name());
			}
		}
		request.setAttribute("pd_fh_num", pd_fh_num);
		request.setAttribute("pd_jh_num", pd_jh_num);
		request.setAttribute("start_date", start_date);
		request.setAttribute("end_date", end_date);
		
		List<KonkaR3Shop> konkaR3ShopList = super.getShopListByDeptId(peProdUser.getDept_id());
		List<PePdModel> pePdModelList = super.getPePdModelListByDeptId(peProdUser.getDept_id());
		request.setAttribute("konkaR3ShopList", konkaR3ShopList);
		request.setAttribute("pePdModelList", pePdModelList);
		
		KonkaJxcFhBillDetails  entity = new KonkaJxcFhBillDetails();
		List<KonkaJxcFhBillDetails> entityList= new ArrayList<KonkaJxcFhBillDetails>();
		List<Map> resultList= new ArrayList<Map>();
			super.copyProperties(entity, dynaBean);
			//entity.getMap().put("fh_src", "true"); // 发货来源
			entity.getMap().put("start_date", start_date);
			entity.getMap().put("end_date", end_date);
			entity.getMap().put("is_confirm", is_confirm);
			resultList = getFacade().getKonkaJxcFhBillDetailsService().getKonkaJxcFhBillDetailsSumPdCountListWithSrc(entity);
			if( resultList != null && resultList.size()>0){
				Long tatolCount=0l;
				for(Map map :resultList){
					KonkaJxcFhBillDetails entity_temp = new KonkaJxcFhBillDetails();
					if(map.get("PD_ID") != null && GenericValidator.isLong(map.get("PD_ID").toString())){
						entity_temp.setPd_id( new Long (map.get("PD_ID").toString()));
					}
					if(map.get("PD_NAME") != null){
						entity_temp.setPd_name(map.get("PD_NAME").toString());
					}
					if(map.get("PD_TYPE_NAME") != null){
						entity_temp.setPd_type_name( map.get("PD_TYPE_NAME").toString());
					}
					if(map.get("BRAND_NAME") != null){
						entity_temp.setBrand_name(map.get("BRAND_NAME").toString());
					}

					if(map.get("BRANCH_OR_WD_ID") != null && GenericValidator.isLong(map.get("BRANCH_OR_WD_ID").toString()) ){
						entity_temp.setBranch_or_wd_id( new Long(map.get("BRANCH_OR_WD_ID").toString()));
					}
//					if(map.get("CONFIRM") != null && GenericValidator.isLong(map.get("CONFIRM").toString()) ){//存放是否确认字段
//						entity_temp.setIs_pc(new Integer(map.get("CONFIRM").toString()));
//					}
					if(map.get("PDSUMCOUNT") != null && GenericValidator.isLong(map.get("PDSUMCOUNT").toString())){
						entity_temp.setCount( new Long (map.get("PDSUMCOUNT").toString()));
						tatolCount += new Long (map.get("PDSUMCOUNT").toString());
					}
					if(map.get("DATA_SRC") != null){
						entity_temp.getMap().put("data_src", map.get("DATA_SRC").toString());
					}
					
					entityList.add(entity_temp);
					
				}
				request.setAttribute("tatolCount", tatolCount);
			}
		dynaBean.set("start_date", start_date);
		dynaBean.set("end_date", end_date);
		if(StringUtils.isNotBlank(pd_id)){
			dynaBean.set("pd_id", pd_id);
		}
		if(StringUtils.isNotBlank(branch_or_wd_id)){
			dynaBean.set("branch_or_wd_id", branch_or_wd_id);
		}
	
		request.setAttribute("entityList", entityList);
		return new ActionForward("/KonkaJxcReceiveSendCompare/viewFh.jsp");
	}
	
	public ActionForward viewJh(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		setNaviStringToRequestScope(form, request);
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		KonkaDept konkaDept = super.getSuperiorForDeptType(ui.getDept_id(), new Integer(3));
		String dept_name = konkaDept.getDept_desc();
		request.setAttribute("dept_name", dept_name);//获取部门名称
		DynaBean dynaBean = (DynaBean) form;
		String pd_id = (String) dynaBean.get("pd_id");
		String branch_or_wd_id = (String) dynaBean.get("branch_or_wd_id");
		String start_date = (String)dynaBean.get("start_date");
		String end_date = (String)dynaBean.get("end_date");
		String pd_fh_num = (String)dynaBean.get("pd_fh_num");
		if(StringUtils.isNotBlank(branch_or_wd_id)){
			KonkaR3Shop  konkaR3Shop = super.getKonkaR3ShopById(new Long(branch_or_wd_id));//取R3网点名称 回显列表
			if(konkaR3Shop != null){
				request.setAttribute("shopName", konkaR3Shop.getCustomer_name());
			}
		}
		request.setAttribute("pd_fh_num", pd_fh_num);
		request.setAttribute("start_date", start_date);
		request.setAttribute("end_date", end_date);
	
		JxcStockBillDetails entity = new JxcStockBillDetails();
		KonkaR3MmtMatch konkaR3MmtMatch = new KonkaR3MmtMatch();
		if(GenericValidator.isLong(branch_or_wd_id)){//网点进货统计
			
			konkaR3MmtMatch = super.getKonkaR3MmtMatchByR3Id(new Long(branch_or_wd_id));
			if(konkaR3MmtMatch != null){
				entity.setShop_id(konkaR3MmtMatch.getMmt_shop_id());
				List<JxcPd> jxc_pd_list = super.getJxcPdByPdAndShopId(new Long(pd_id), konkaR3MmtMatch.getMmt_shop_id());
				if(jxc_pd_list != null && jxc_pd_list.size()>0){
					String pd_ids = "";
					for(JxcPd pd :jxc_pd_list){
						pd_ids += pd.getId() +",";
					}
					if( pd_ids.lastIndexOf(",")>-1){
						pd_ids = pd_ids.substring(0, pd_ids.lastIndexOf(","));
					}
					//logger.info("pd_ids=="+pd_ids);
					if(StringUtils.isNotBlank(pd_ids)){
						entity.getMap().put("pd_ids", pd_ids);
					}else{//无对应型号串
						entity.setPd_id(-1l);
					}
				}else{//无对应型号
					entity.setPd_id(-1l);
				}
			}else{//R3网点没有匹配买买提网点 
				entity.setShop_id(-1l);
				entity.setPd_id(-1l);
			}
		}else{//无网点信息或非法信息
			entity.setShop_id(-1l);
			entity.setPd_id(-1l);
		}
		
		
		//entity.setPd_id(new Long(pd_id));
		entity.getMap().put("start_date", start_date);
		entity.getMap().put("end_date", end_date);
		
		List<JxcStockBillDetails> entityList= new ArrayList<JxcStockBillDetails>();
		List<Map> resultList= new ArrayList<Map>();
		resultList = getFacade().getJxcStockBillDetailsService().getJxcstockBillDetailsSumPdCountList(entity);
		
		if( resultList != null && resultList.size()>0){
			Long tatolCount=0l;
			for(Map map :resultList){
				JxcStockBillDetails entity_temp = new JxcStockBillDetails();
				
				if(map.get("STOCK_SRC") != null && GenericValidator.isLong(map.get("STOCK_SRC").toString()) ){//存放进货来源字段 进货来源 1：进货 2：收货确认 3：分销确认
					entity_temp.setIs_pc(new Integer(map.get("STOCK_SRC").toString()));
				}
				if(map.get("SUMCOUNT") != null && GenericValidator.isLong(map.get("SUMCOUNT").toString())){
					entity_temp.setCount( new Long (map.get("SUMCOUNT").toString()));
					tatolCount += new Long (map.get("SUMCOUNT").toString());
				}
				if(map.get("PD_TYPE_NAME") != null){
					entity_temp.setPd_type_name(map.get("PD_TYPE_NAME").toString());
				}
				if(map.get("PD_NAME") != null){
					entity_temp.setPd_name(map.get("PD_NAME").toString());
				}
				if(map.get("BRAND_NAME") != null){
					entity_temp.setBrand_name(map.get("BRAND_NAME").toString());
				}
				entityList.add(entity_temp);
				
			}
			request.setAttribute("tatolCount", tatolCount);
		}
		
		request.setAttribute("entityList", entityList);
		return mapping.findForward("view");
	}
	
	public ActionForward toExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		super.renderExcelWithEncoding(request, response, "GBK");
		return null;
	}


}
