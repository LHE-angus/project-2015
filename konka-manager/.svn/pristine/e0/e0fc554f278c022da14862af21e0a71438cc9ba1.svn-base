package com.ebiz.mmt.web.struts.customer;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.ConsumerInfo;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Li,DeYu
 * @version 2013-07-09
 */

public class ConsumerInfoAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		Long konkaR3Id=ui.getCust_id();
		
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		
		ConsumerInfo entity = new ConsumerInfo();
		super.copyProperties(entity, form);
		
		entity.setCust_id(konkaR3Id);
		
		String is_del = (String) dynaBean.get("is_del");
		//默认查询有效数据
		if(null==is_del){
			entity.setIs_del(0);
			dynaBean.set("is_del", "0");
		}
		
		// 消费者姓名
		String consumer_name_like = (String) dynaBean.get("consumer_name_like");
		String report_name_like = (String) dynaBean.get("report_name_like");
		String date_begin = (String) dynaBean.get("date_begin");
		String date_end = (String) dynaBean.get("date_end");
		String consumer_phone_like = (String) dynaBean.get("consumer_phone_like");
		
		String excel_all = (String) dynaBean.get("excel_all");
		if (StringUtils.isNotBlank(consumer_name_like)) {
			entity.getMap().put("consumer_name_like", consumer_name_like);
		}
		if (StringUtils.isNotBlank(report_name_like)) {
			entity.getMap().put("report_name_like", report_name_like);
		}
		if (StringUtils.isNotBlank(date_begin)) {
			entity.getMap().put("date_begin", date_begin);
		}
		if (StringUtils.isNotBlank(date_end)) {
			entity.getMap().put("date_end", date_end);
		}
		if (StringUtils.isNotBlank(consumer_phone_like)) {
			entity.getMap().put("consumer_phone_like", consumer_phone_like);
		}

		Long recordCount = getFacade().getConsumerInfoService().getConsumerInfoAllCount(entity);
		pager.init(recordCount, 15, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<ConsumerInfo> entityList = getFacade().getConsumerInfoService().getConsumerInfoForList(entity);
		request.setAttribute("entityList", entityList);

		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			Date now = new Date(); 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			String nowstr = dateFormat.format(now); 
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + 
					EncryptUtils.encodingFileName("消费者信息库-"+nowstr)+".xls");

			entity.getRow().setCount(recordCount.intValue());
			List<ConsumerInfo> entityList1 = getFacade().getConsumerInfoService().getConsumerInfoForList(entity);
			request.setAttribute("allList", entityList1);
			return mapping.findForward("view");
		}
		return mapping.findForward("list");
	}
	
	/**
	 * 有效、无效操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stopOrStart(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String consumer_phone = (String) dynaBean.get("consumer_phone");
		String is_del = (String) dynaBean.get("is_del");
		
		ConsumerInfo con = new ConsumerInfo();
		con.setConsumer_phone(consumer_phone);
		con.setIs_del(Integer.parseInt(is_del));
		con.getMap().put("phone", true);
		
		int res = super.getFacade().getConsumerInfoService().modifyConsumerInfo(con);
		

		//封装成JSON字符串
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("result", res);
		JSONArray jsonArray = JSONArray.fromObject(m);
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start+1, end+1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 点击数量查询详情
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listForDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String consumer_phone = (String) dynaBean.get("consumer_phone");//电话号码
		String consumer_name = java.net.URLDecoder.decode((String) dynaBean.get("consumer_name"), "utf-8"); //消费者姓名
		
		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		Long konkaR3Id=ui.getCust_id();
		
		ConsumerInfo consumerInfo = new ConsumerInfo();
		consumerInfo.setConsumer_phone(consumer_phone);
		consumerInfo.setConsumer_name(consumer_name);
		consumerInfo.setCust_id(konkaR3Id);
		
		List<HashMap> entryList = super.getFacade().getConsumerInfoService().getConsumerInfoDetails(consumerInfo);
		
		request.setAttribute("entryList", entryList);

		return new ActionForward("/ConsumerInfo/details.jsp");
	}
}
