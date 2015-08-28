package com.ebiz.mmt.web.struts.webservice;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaR3OrderHeader;
import com.ebiz.mmt.domain.KonkaR3OrderLines;
import com.ebiz.mmt.r3.Call_Create_So_bak;
import com.ebiz.mmt.r3.ReturnInfo;
import com.ebiz.mmt.r3.StockCheckResult;
import com.ebiz.mmt.r3.helper.R3daoImpl;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

@Deprecated
public class OrderTestAction extends BaseAction {
	
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.add(mapping, form, request, response);
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return null;
	}
	 
	/***
	 * 显示数据列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//设置查询条件(包括page)
		//取数据
		//放入request
		//导航
		
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager)dynaBean.get("pager");
		//String customer_name = (String) dynaBean.get("customer_name");
		//logger.info("customer_name======={}",customer_name);
		
		//System.out.println(dynaBean.get("r3_order_no"));
		KonkaR3OrderHeader entity = new KonkaR3OrderHeader();
//		KonkaR3OrderHeader entity2 = new KonkaR3OrderHeader();
//		for(int i =2 ;i<20 ;i++){
//			entity2.setCustomer_code("cus"+i);
//			entity2.setCustomer_name("customerName"+i);
//			entity2.setApply_date(new Date());
//			entity2.setShip_date(new Date());
//			entity2.setRecipient("konka"+i);
//			getFacade().getKonkaR3OrderHeaderService().createKonkaR3OrderHeader(entity2);
//		}
		
		copyProperties(entity, form);
		if(null ==pager.getPageSize()){
			pager.setPageSize(10);
		}
		if( null ==pager.getRequestPage()){
			pager.setRequestPage("1");
		}

		Long recordCount = getFacade().getKonkaR3OrderHeaderService().getKonkaR3OrderHeaderCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
		List<KonkaR3OrderHeader> list = getFacade().getKonkaR3OrderHeaderService().getKonkaR3OrderHeaderPaginatedList(entity);
		
		request.setAttribute("entityList", list);
		return mapping.findForward("list");
	}
	
	/**
	 * 查看 一般只用于查看单据页面
	 */
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//System.out.println("view func");
		
		//根据头表id,查头表数据
		//头表数据查询后,再查从表数据
		//从表数据放request范围供列表显示
		
		DynaBean dynaBean  = (DynaBean)form;
		String headerId = (String)dynaBean.get("r3_order_header_id");
		
		if("".equals(headerId)){
			return mapping.findForward("list");
		}
		
		KonkaR3OrderHeader entityHeader = new KonkaR3OrderHeader();
		entityHeader.setR3_order_header_id(Long.valueOf(headerId));
		entityHeader = getFacade().getKonkaR3OrderHeaderService().getKonkaR3OrderHeader(entityHeader);
		
		//把头表数据放入dynaBean
		copyProperties(dynaBean, entityHeader);
		
		//取行数据
		KonkaR3OrderLines entityLines = new KonkaR3OrderLines();
		entityLines.setR3_order_header_id(Long.valueOf(headerId));
		List<KonkaR3OrderLines> entityLineList = getFacade().getKonkaR3OrderLinesService().getKonkaR3OrderLinesList(entityLines);
		request.setAttribute("entityLineList", entityLineList);
		return mapping.findForward("view");
	}
	
	/**
	 * 进行编辑 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//System.out.println("edit func");
		return mapping.findForward("input");
	}
	
	
	
	/**
	 * 单据保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public ActionForward save(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{
		
		
		DynaBean dynaBean = (DynaBean)form;
		KonkaR3OrderHeader konkaR3OrderHeader = new KonkaR3OrderHeader() ;
		
		//BeanUtils.copyProperties(konkaR3OrderHeader, dynaBean);
		//把form的值set到domain里面去
		super.copyProperties(konkaR3OrderHeader, dynaBean);
		
		//必须保证数组长度一样
		String[] material_code = request.getParameterValues("material_code");
		//String[] material_name = request.getParameterValues("material_name");
		String[] material_desc = request.getParameterValues("material_desc");
		String[] review_notes = request.getParameterValues("review_notes");
		String[] ship_desc = request.getParameterValues("ship_desc");
		String[] unit_code = request.getParameterValues("unit_code");
		String[] apply_amount = request.getParameterValues("apply_amount");
		String[] review_amount = request.getParameterValues("review_amount");
		String[] unit_price = request.getParameterValues("unit_price");
		String[] total_money = request.getParameterValues("total_money2");
		
		
		List<KonkaR3OrderLines> linesList = new ArrayList<KonkaR3OrderLines>();
		List<StockCheckResult> stockCheckResultList = new ArrayList<StockCheckResult>();
		for(int i = 0 ;i<material_code.length ;i++){
			KonkaR3OrderLines lines = new KonkaR3OrderLines();
			lines.setR3_order_header_id(konkaR3OrderHeader.getR3_order_header_id());//设置外键
			lines.setMaterial_code(material_code[i]);
		//	lines.setMaterial_name(material_name[i]);
			lines.setMaterial_desc(material_desc[i]);
			lines.setReview_notes(review_notes[i]);
			lines.setShip_desc(ship_desc[i]);
			lines.setUnit_code(unit_code[i]);
			lines.setApply_amount(new BigDecimal(apply_amount[i]==null?"0":apply_amount[i]));
			lines.setReview_amount(new BigDecimal(review_amount[i]==null?"0":review_amount[i]));
			lines.setUnit_price(new BigDecimal(unit_price[i]==null?"0":unit_price[i]));
			lines.setTotal_money(new BigDecimal(total_money[i]==null?"0":total_money[i]));
			linesList.add(lines);
		}
		
		konkaR3OrderHeader.setKonkaR3OrderLinesList(linesList);//set数据
		//System.out.println("行表的行数:"+linesList.size());
		
		
		//保存前校验,还是审核通过后再校验库存?他们定
		//stockCheckResultList = doCheck(konkaR3OrderHeader.getSales_org(),linesList);
		
		
		int isOk = 1 ;
		for(StockCheckResult stockResult:stockCheckResultList){
			isOk  = isOk * stockResult.getIsOk();
		}
		
		//校验通过
		if(isOk==11111){
			//保存主从表
			//long headid = getFacade().getKonkaR3OrderHeaderService().createKonkaR3OrderHeader(konkaR3OrderHeader);
			long headid = 1043L;
			//写R/3
			ReturnInfo returnInfo = buildR3Order(headid);
			
			if(returnInfo.getItemNO() !=null){
				konkaR3OrderHeader.setR3_order_header_id(headid);
				konkaR3OrderHeader.setR3_order_sell_no(returnInfo.getItemNO()) ;
				//getFacade().getKonkaR3OrderHeaderService().modifyKonkaR3OrderHeader(konkaR3OrderHeader) ;
			}
			request.setAttribute("orderNO", returnInfo.getItemNO());//处理结果
			request.setAttribute("excuteMsg", returnInfo.getMsgList()); ////s : 头处理成功   ; e:处理失败
		}else{
			request.setAttribute("stockCheckResultList", stockCheckResultList); //校验不能过,把校验结果列印
			
		}
		request.setAttribute("isOk", isOk);
		return mapping.findForward("message");
	}
	
	private List<StockCheckResult> doCheck(String zbukrs,List<KonkaR3OrderLines> itemList){
		if(zbukrs==null || "".equals(zbukrs)){
			return null ;
		}
		if(itemList.size()<=0){
			return null ;
		}
		return new R3daoImpl().checkStock(zbukrs, itemList);
	}
	
	
	//写r/3
	private ReturnInfo buildR3Order(long headid){
		
		//取数据,写r/3
		KonkaR3OrderHeader orderHeader = new KonkaR3OrderHeader();
		orderHeader.setR3_order_header_id(headid);
		
		//头表
		orderHeader = getFacade().getKonkaR3OrderHeaderService().getKonkaR3OrderHeader(orderHeader);
		
		//行表
		KonkaR3OrderLines orderLine = new KonkaR3OrderLines();
		orderLine.setR3_order_header_id(headid);
		List<KonkaR3OrderLines> linesList = new ArrayList<KonkaR3OrderLines>();
		linesList = getFacade().getKonkaR3OrderLinesService().getKonkaR3OrderLinesList(orderLine) ;
		orderHeader.setKonkaR3OrderLinesList(linesList);
		
		Call_Create_So_bak cco = new Call_Create_So_bak() ;
		
		ReturnInfo returnInfo  =  null ;
		 
		return returnInfo ;
	}
	
	
	
}
