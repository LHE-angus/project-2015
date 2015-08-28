package com.ebiz.mmt.web.struts.jxc;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ebiz.mmt.domain.BaseProvinceList;
import com.ebiz.mmt.domain.JxcCustomer;
import com.ebiz.mmt.domain.JxcSellBill;
import com.ebiz.mmt.domain.JxcSellBillDetails;
import com.ebiz.mmt.domain.JxcUseNojdxxShop;
import com.ebiz.mmt.domain.StdEntpUser;
import com.ebiz.mmt.web.util.EncryptUtilsV2;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Chen,WeiWei
 * @version 2011-3-4
 */
public class JxcCustomerAction extends BaseJxcAction {
	private final String date_pattern = "yyyy-MM-dd HH:mm:ss";

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		Pager pager = (Pager) dynaBean.get("pager");
		String name = (String) dynaBean.get("name");

		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);

		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);

		Long shop_id = user.getStdEntpMain().getShop_id();
		// Integer shop_p_index = user.getStdEntpMain().getP_index();
		JxcCustomer entity = new JxcCustomer();
		entity.setShop_id(shop_id);
		if (name != null && StringUtils.isNotBlank(name)) {
			entity.getMap().put("name_like", name);
			dynaBean.set("name", name);
		}
		// entity.setShop_p_index(Long.parseLong(shop_p_index.toString()));
		entity.setIs_del(0);
		entity.getMap().put("add_date_desc", true);
		Long recordCount = super.getFacade().getJxcCustomerService().getJxcCustomerCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<JxcCustomer> entityList = super.getFacade().getJxcCustomerService().getJxcCustomerPaginatedList(entity);
		
		JxcUseNojdxxShop jxcUseNojdxxShop = new JxcUseNojdxxShop();
		jxcUseNojdxxShop.setShop_id(shop_id);
		Long num = super.getFacade().getJxcUseNojdxxShopService().getJxcUseNojdxxShopCount(jxcUseNojdxxShop);
		if(num.intValue() > 0){
			dynaBean.set("useIsjdxxShop", "true");
		}
		
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		dynaBean.set("keySeq", keySeq);
		dynaBean.set("queryString", super.serialize(request, "mod_id", "method"));
		return mapping.findForward("input");
	}

	public ActionForward saveContinue(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String name = (String) dynaBean.get("name");
		String link_name = (String) dynaBean.get("link_name");
		String fax = (String) dynaBean.get("fax");
		String qq = (String) dynaBean.get("qq");
		String e_mail = (String) dynaBean.get("e_mail");
		String post = (String) dynaBean.get("post");
		String p_index = (String) dynaBean.get("p_index");
		String addr = (String) dynaBean.get("addr");
		String remarks = (String) dynaBean.get("remarks");
		String init_pay = (String) dynaBean.get("init_pay");
		String tel = (String) dynaBean.get("tel");
		
		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);

		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);

		Long shop_id = user.getStdEntpMain().getShop_id();

		Date date = new Date();
		JxcCustomer entity = new JxcCustomer();
		entity.setIs_del(0);
		entity.setShop_id(shop_id);
		entity.setName(name);
		entity = super.getFacade().getJxcCustomerService().getJxcCustomer(entity);
		if (entity != null) {
			String msg = super.getMessage(request, "jxc.customer.name.repeat");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		entity = new JxcCustomer();
		super.copyProperties(entity, form);
		entity.setAdd_date(date);
		entity.setAdd_user_id(user.getUser_id());
		if (addr != null && StringUtils.isNotBlank(addr)) {
			entity.setAddr(addr);
		}
		if (link_name != null && StringUtils.isNotBlank(link_name)) {
			entity.setLink_name(link_name);
		}
		if (fax != null && StringUtils.isNotBlank(fax)) {
			entity.setFax(fax);
		}
		if (e_mail != null && StringUtils.isNotBlank(e_mail)) {
			entity.setE_mail(e_mail);
		}
		if (post != null && StringUtils.isNotBlank(post)) {
			entity.setPost(post);
		}
		if (p_index != null && StringUtils.isNotBlank(p_index)) {
			entity.setP_index(Long.parseLong(p_index));
		}
		if (remarks != null && StringUtils.isNotBlank(remarks)) {
			entity.setRemarks(remarks);
		}
		if (init_pay != null && StringUtils.isNotBlank(init_pay)) {
			entity.setInit_pay(new BigDecimal(init_pay));
			entity.setCur_pay(entity.getInit_pay());
		}
		entity.setIs_del(0);
		if (qq != null && StringUtils.isNotBlank(qq)) {
			entity.setQq(qq);
		}
		if (name != null && StringUtils.isNotBlank(name)) {
			entity.setName(name);
		}
		entity.setShop_id(shop_id);
		entity.setShop_p_index(Long.parseLong(user.getStdEntpMain().getP_index().toString()));
		if (tel != null && StringUtils.isNotBlank(tel)) {
			entity.setTel(tel);
		}
		super.getFacade().getJxcCustomerService().createJxcCustomer(entity);

		return new ActionForward("/../WEB-INF/pages/jsp/client/JxcCustomer/formContinue.jsp");

	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String id = (String) dynaBean.get("id");
		String name = (String) dynaBean.get("name");
		String link_name = (String) dynaBean.get("link_name");
		String fax = (String) dynaBean.get("fax");
		String qq = (String) dynaBean.get("qq");
		String e_mail = (String) dynaBean.get("e_mail");
		String post = (String) dynaBean.get("post");
		String p_index = (String) dynaBean.get("p_index");
		String addr = (String) dynaBean.get("addr");
		String remarks = (String) dynaBean.get("remarks");
		String init_pay = (String) dynaBean.get("init_pay");
		String tel = (String) dynaBean.get("tel");
		
		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);

		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);

		Long shop_id = user.getStdEntpMain().getShop_id();

		Date date = new Date();
		JxcCustomer entity = new JxcCustomer();
		if (!StringUtils.isNotBlank(id)) {
			entity.setIs_del(0);
			entity.setShop_id(shop_id);
			entity.setName(name);
			entity = super.getFacade().getJxcCustomerService().getJxcCustomer(entity);
			if (entity != null) {
				String msg = super.getMessage(request, "jxc.customer.name.repeat");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		}
		entity = new JxcCustomer();
		super.copyProperties(entity, form);
		entity.setAdd_date(date);
		entity.setAdd_user_id(user.getUser_id());
		if (addr != null && StringUtils.isNotBlank(addr)) {
			entity.setAddr(addr);
		}
		if (link_name != null && StringUtils.isNotBlank(link_name)) {
			entity.setLink_name(link_name);
		}
		if (fax != null && StringUtils.isNotBlank(fax)) {
			entity.setFax(fax);
		}
		if (e_mail != null && StringUtils.isNotBlank(e_mail)) {
			entity.setE_mail(e_mail);
		}
		if (post != null && StringUtils.isNotBlank(post)) {
			entity.setPost(post);
		}
		if (p_index != null && StringUtils.isNotBlank(p_index)) {
			entity.setP_index(Long.parseLong(p_index));
		}
		if (remarks != null && StringUtils.isNotBlank(remarks)) {
			entity.setRemarks(remarks);
		}
		if (init_pay != null && StringUtils.isNotBlank(init_pay)) {
			entity.setInit_pay(new BigDecimal(init_pay));
			entity.setCur_pay(entity.getInit_pay());
		}
		entity.setIs_del(0);
		if (qq != null && StringUtils.isNotBlank(qq)) {
			entity.setQq(qq);
		}
		if (name != null && StringUtils.isNotBlank(name)) {
			entity.setName(name);
		}
		entity.setShop_id(shop_id);
		entity.setShop_p_index(Long.parseLong(user.getStdEntpMain().getP_index().toString()));
		if (tel != null && StringUtils.isNotBlank(tel)) {
			entity.setTel(tel);
		}
		if (!StringUtils.isNotBlank(id)) {
			super.getFacade().getJxcCustomerService().createJxcCustomer(entity);

		} else {
			super.getFacade().getJxcCustomerService().modifyJxcCustomer(entity);
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		logger.info("delete begin");
		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			JxcCustomer entity = new JxcCustomer();
			entity.setId(new Long(id));
			entity.setIs_del(1);
			getFacade().getJxcCustomerService().modifyJxcCustomer(entity);
		} else if (!ArrayUtils.isEmpty(pks)) {
			JxcCustomer entity = new JxcCustomer();
			entity.getMap().put("pks", pks);
			entity.setIs_del(1);
			getFacade().getJxcCustomerService().modifyJxcCustomer(entity);
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		dynaBean.set("keySeq", keySeq);
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

		JxcCustomer entity = new JxcCustomer();
		entity.setId(Long.parseLong(id));
		entity = super.getFacade().getJxcCustomerService().getJxcCustomer(entity);

		if (null == entity) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

		Long p_index = entity.getP_index();
		if (p_index != null) {
			BaseProvinceList baseProvinceList = new BaseProvinceList();
			baseProvinceList.setP_index(p_index);
			List<BaseProvinceList> baseProvinceListList = super.getFacade().getBaseProvinceListService()
					.getBaseProvinceListParentList(baseProvinceList);
			ListIterator<BaseProvinceList> it = baseProvinceListList.listIterator();

			Long province = it.next().getP_index();
			Long city = it.next().getP_index();
			Long country = it.next().getP_index();
			dynaBean.set("province", province);
			dynaBean.set("city", city);
			dynaBean.set("country", country);
		}

		super.copyProperties(form, entity);
		dynaBean.set("queryString", super.serialize(request, "mod_id", "method"));
		dynaBean.set("edit", "1");
		return mapping.findForward("input");
	}

	public ActionForward exportToExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		String exportExcel = (String) dynaBean.get("exportExcel");
		if (StringUtils.isNotBlank(exportExcel) && "1".equals(exportExcel)) {
			JxcCustomer entity = new JxcCustomer();
			entity.setIs_del(0);
			entity.getMap().put("add_date_desc", true);
			String keySeq = (String) dynaBean.get("keySeq");

			request.setAttribute("not_validate_record", "true");
			StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);

			if (null == user) {
				return null;
			}

			Long shop_id = user.getStdEntpMain().getShop_id();
			// Integer shop_p_index = user.getStdEntpMain().getP_index();
			entity.setShop_id(shop_id);

			super.copyProperties(entity, form);
			List<JxcCustomer> entityList = super.getFacade().getJxcCustomerService().getJxcCustomerList(entity);

			if (entityList.size() >= 500) { // 禁止导出大于 500条记录的数据
				return null;
			}
			JSONObject result = new JSONObject();
			JSONArray list = new JSONArray();
			for (int i = 0; i < entityList.size(); i++) {
				JSONObject obj = new JSONObject();
				JxcCustomer cur = entityList.get(i);
				obj.put("name", cur.getName());
				obj.put("link_name", cur.getLink_name());
				obj.put("tel", cur.getTel());
				obj.put("fax", cur.getFax());
				obj.put("cur_pay", cur.getCur_pay());
				if (null != cur.getAdd_date()) {
					obj.put("add_date", DateFormatUtils.format(cur.getAdd_date(), date_pattern));
				} else {
					obj.put("add_date", "");
				}
				list.put(obj);
			}
			result.put("list", list);
			logger.info(result.toString());
			super.render(response, result.toString(), "text/x-json;charset=UTF-8");
			return null;
		}
		return null;
	}

	public ActionForward toExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		response.setCharacterEncoding("utf-8");

		String name = request.getParameter("hiddenName");
		String html = request.getParameter("hiddenHtml");

		try {
			response.setContentType("application/vnd.ms-excel");

			response.addHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(name, "UTF-8")
					+ ".xls\"");

			// response.setContentType("text/plain");
			// response.addHeader("Content-Disposition",
			// "attachment; filename=\"" + URLEncoder.encode(name, "UTF-8")
			// + ".csv\"");

			// response.setContentType("application/vnd.ms-word");
			// response.addHeader("Content-Disposition",
			// "attachment; filename=\"" + URLEncoder.encode(name, "UTF-8")
			// + ".doc\"");

			PrintWriter out = response.getWriter();

			out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
			out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
			out.println("<head>");
			out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
			out.println("<meta http-equiv=\"MSThemeCompatible\" content=\"no\" />");
			out.println("<meta name=\"MSSmartTagsPreventParsing\" content=\"true\" />");
			out.println("<title>" + name + "</title>");
			out.println("</head>");

			html = html.replace("border=0", "border=1");
			// html = html.replace("<A href=\"[^>]*?\">([^<]*?)<\/A>", "$1");

			out.println(html);

			out.println("</body>");
			out.println("</html>");
			out.println("<body>");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public ActionForward validateName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String name = (String) dynaBean.get("name");
		String keySeq = (String) dynaBean.get("keySeq");
		String id = (String) dynaBean.get("id");
		JxcCustomer entity = new JxcCustomer();
		String isExist = "0";
		entity.setName(name);
		entity.setIs_del(0);

		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);
		Long shop_id = user.getStdEntpMain().getShop_id();
		entity.setShop_id(shop_id);
		entity.getMap().put("id_not", id);

		Long count = super.getFacade().getJxcCustomerService().getJxcCustomerCount(entity);
		isExist = String.valueOf(count);
		super.render(response, isExist, "text/x-json;charset=UTF-8");
		return null;
	}
	public ActionForward sellBillList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String customer_id = (String) dynaBean.get("customer_id");
		String keySeq = (String) dynaBean.get("keySeq");
		String sell_date_ge = (String) dynaBean.get("sell_date_ge");
		String sell_date_le = (String) dynaBean.get("sell_date_le");
		Pager pager = (Pager) dynaBean.get("pager");
		
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);
		Long shop_id = user.getStdEntpMain().getShop_id();
		
		JxcUseNojdxxShop jxcUseNojdxxShop = new JxcUseNojdxxShop();
		jxcUseNojdxxShop.setShop_id(shop_id);
		Long num = super.getFacade().getJxcUseNojdxxShopService().getJxcUseNojdxxShopCount(jxcUseNojdxxShop);
		if(num.intValue() <= 0){
			return null;
		}
		
		Date now = new Date();
		if (StringUtils.isBlank(sell_date_ge)) {
			sell_date_ge = DateFormatUtils.format(now, "yyyy-MM-") + "01";
		}
		if (StringUtils.isBlank(sell_date_le)) {
			sell_date_le = DateFormatUtils.format(now, "yyyy-MM-dd");
		}

		dynaBean.set("sell_date_ge", sell_date_ge);
		dynaBean.set("sell_date_le", sell_date_le);
		JxcSellBill jxcSellBill = new JxcSellBill();
		if(StringUtils.isNotBlank(customer_id)){
			JxcCustomer jxc_customer = new JxcCustomer();//取客户名
			jxc_customer.setId(Long.valueOf(customer_id));
			jxc_customer = super.getFacade().getJxcCustomerService().getJxcCustomer(jxc_customer);
			dynaBean.set("customer_name", jxc_customer.getName());
			jxcSellBill.setCustomer_id(Long.valueOf(customer_id));
		}
		jxcSellBill.setShop_id(shop_id);
		jxcSellBill.setIs_del(0);
		jxcSellBill.getMap().put("sell_date_le", sell_date_le);
		jxcSellBill.getMap().put("sell_date_ge", sell_date_ge);
		Long recordCount = super.getFacade().getJxcSellBillService().getJxcSellBillCount(jxcSellBill);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		jxcSellBill.getRow().setFirst(pager.getFirstRow());
		jxcSellBill.getRow().setCount(pager.getRowCount());
		List<JxcSellBill> jxcSellkBillList = super.getFacade().getJxcSellBillService().getJxcSellBillPaginatedList(jxcSellBill);
		for(JxcSellBill  temp  : jxcSellkBillList){
			JxcSellBillDetails jxc_sb_details = new JxcSellBillDetails(); 
			jxc_sb_details.setSell_bill_id(temp.getId());
			List<JxcSellBillDetails> jxc_sb_details_list = super.getFacade().getJxcSellBillDetailsService().getJxcSellBillDetailsList(jxc_sb_details);
			temp.setSellBillDetailList(jxc_sb_details_list);
		}
		request.setAttribute("jxcSellkBillList", jxcSellkBillList);
		super.setCustomerListToRequest(request,shop_id);//根据shop_id取客户列表
		return new ActionForward("/../WEB-INF/pages/jsp/client/JxcCustomer/list_sell_bill.jsp");
	}
	
	public ActionForward toExcelForFlv(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ParseException {

		DynaBean dynaBean = (DynaBean) form;
		String customer_id = (String) dynaBean.get("customer_id");
		String keySeq = (String) dynaBean.get("keySeq");
		String sell_date_ge = (String) dynaBean.get("sell_date_ge");
		String sell_date_le = (String) dynaBean.get("sell_date_le");
		StringBuffer queryCondition=new StringBuffer();
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		
		Date now = new Date();
		if (StringUtils.isBlank(sell_date_ge)) {
			sell_date_ge = DateFormatUtils.format(now, "yyyy-MM-") + "01";
		}
		if (StringUtils.isBlank(sell_date_le)) {
			sell_date_le = DateFormatUtils.format(now, "yyyy-MM-dd");
		}
		
		JxcSellBill jxcSellBill = new JxcSellBill();
		if(StringUtils.isNotBlank(customer_id)){
			JxcCustomer jxc_customer = new JxcCustomer();//取客户名
			jxc_customer.setId(Long.valueOf(customer_id));
			jxc_customer = super.getFacade().getJxcCustomerService().getJxcCustomer(jxc_customer);
			dynaBean.set("customer_name", jxc_customer.getName());
			jxcSellBill.setCustomer_id(Long.valueOf(customer_id));
			queryCondition.append("客户姓名："+jxc_customer.getName());
		}
		jxcSellBill.setIs_del(0);
		jxcSellBill.getMap().put("sell_date_le", sell_date_le);
		jxcSellBill.getMap().put("sell_date_ge", sell_date_ge);
		List<JxcSellBill> jxcSellkBillList = super.getFacade().getJxcSellBillService().getJxcSellBillList(jxcSellBill);
		for(JxcSellBill  temp  : jxcSellkBillList){
			JxcSellBillDetails jxc_sb_details = new JxcSellBillDetails(); 
			jxc_sb_details.setSell_bill_id(temp.getId());
			List<JxcSellBillDetails> jxc_sb_details_list = super.getFacade().getJxcSellBillDetailsService().getJxcSellBillDetailsList(jxc_sb_details);
			temp.setSellBillDetailList(jxc_sb_details_list);
		}
		
		Map<String, Object> model = new HashMap<String, Object>();
		if (null != jxcSellkBillList && jxcSellkBillList.size() > 0) {
			
			model.put("entityList", jxcSellkBillList);
		}
		
		
		if(StringUtils.isNotBlank(sell_date_ge) && StringUtils.isNotBlank(sell_date_le)){
			queryCondition.append("   查询时间："+sell_date_ge).append("至"+sell_date_le);
		}
		if(!StringUtils.isNotBlank(sell_date_ge) && StringUtils.isNotBlank(sell_date_le)){
			queryCondition.append("   查询时间：至").append(sell_date_le);
		}
		if(StringUtils.isNotBlank(sell_date_ge) && !StringUtils.isNotBlank(sell_date_le)){
			queryCondition.append("   查询时间："+sell_date_ge).append("至今");
		}
		logger.info("query:{}"+queryCondition.toString());
		model.put("title", "客户交易明细表");
		model.put("queryCondition", queryCondition.toString());
		String content = getFacade().getTemplateService().getContent("jxc/jxc_customer_list.ftl", model);
		// 下载文件出现乱码时，请参见此处
		String fname = EncryptUtilsV2.encodingFileName("客户交易明细表.xls");

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + fname);

		PrintWriter out = response.getWriter();
		out.println(content);
		out.flush();
		out.close();

		return null;
	}
	
	public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, Exception {
		DynaBean dynaBean = (DynaBean) form;
		String customer_id = (String) dynaBean.get("customer_id");
		String keySeq = (String) dynaBean.get("keySeq");
		String sell_date_ge = (String) dynaBean.get("sell_date_ge");
		String sell_date_le = (String) dynaBean.get("sell_date_le");
		StringBuffer queryCondition=new StringBuffer();
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		
		Date now = new Date();
		if (StringUtils.isBlank(sell_date_ge)) {
			sell_date_ge = DateFormatUtils.format(now, "yyyy-MM-") + "01";
		}
		if (StringUtils.isBlank(sell_date_le)) {
			sell_date_le = DateFormatUtils.format(now, "yyyy-MM-dd");
		}
		
		JxcSellBill jxcSellBill = new JxcSellBill();
		if(StringUtils.isNotBlank(customer_id)){
			JxcCustomer jxc_customer = new JxcCustomer();//取客户名
			jxc_customer.setId(Long.valueOf(customer_id));
			jxc_customer = super.getFacade().getJxcCustomerService().getJxcCustomer(jxc_customer);
			dynaBean.set("customer_name", jxc_customer.getName());
			jxcSellBill.setCustomer_id(Long.valueOf(customer_id));
			queryCondition.append("客户姓名："+jxc_customer.getName());
		}
		jxcSellBill.setIs_del(0);
		jxcSellBill.getMap().put("sell_date_le", sell_date_le);
		jxcSellBill.getMap().put("sell_date_ge", sell_date_ge);
		List<JxcSellBill> jxcSellkBillList = super.getFacade().getJxcSellBillService().getJxcSellBillList(jxcSellBill);
		for(JxcSellBill  temp  : jxcSellkBillList){
			JxcSellBillDetails jxc_sb_details = new JxcSellBillDetails(); 
			jxc_sb_details.setSell_bill_id(temp.getId());
			List<JxcSellBillDetails> jxc_sb_details_list = super.getFacade().getJxcSellBillDetailsService().getJxcSellBillDetailsList(jxc_sb_details);
			temp.setSellBillDetailList(jxc_sb_details_list);
		}
		if(StringUtils.isNotBlank(sell_date_ge) && StringUtils.isNotBlank(sell_date_le)){
			queryCondition.append("	    查询时间："+sell_date_ge).append("至"+sell_date_le);
		}
		if(!StringUtils.isNotBlank(sell_date_ge) && StringUtils.isNotBlank(sell_date_le)){
			queryCondition.append("	    查询时间：至").append(sell_date_le);
		}
		if(StringUtils.isNotBlank(sell_date_ge) && !StringUtils.isNotBlank(sell_date_le)){
			queryCondition.append("	    查询时间："+sell_date_ge).append("至今");
		}
		logger.info("query:{}"+queryCondition.toString());
		request.setAttribute("jxcSellkBillList", jxcSellkBillList);
		request.setAttribute("queryCondition", queryCondition.toString());
		return new ActionForward("/../WEB-INF/pages/jsp/client/_public_print_customer_sell_details.jsp");
	}
}
