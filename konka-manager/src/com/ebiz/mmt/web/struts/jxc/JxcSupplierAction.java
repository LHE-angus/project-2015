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
import com.ebiz.mmt.domain.JxcStockBill;
import com.ebiz.mmt.domain.JxcStockBillDetails;
import com.ebiz.mmt.domain.JxcSupplier;
import com.ebiz.mmt.domain.JxcUseNojdxxShop;
import com.ebiz.mmt.domain.StdEntpUser;
import com.ebiz.mmt.web.util.EncryptUtilsV2;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Chen,WeiWei
 * @version 2011-3-3
 */
public class JxcSupplierAction extends BaseJxcAction {
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
		// test

		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);

		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);

		Long shop_id = user.getStdEntpMain().getShop_id();
		// Integer shop_p_index = user.getStdEntpMain().getP_index();
		JxcSupplier entity = new JxcSupplier();
		entity.setShop_id(shop_id);
		if (name != null && StringUtils.isNotBlank(name)) {
			entity.getMap().put("name_like", name);
			dynaBean.set("name", name);
		}
		// entity.setShop_p_index(Long.parseLong(shop_p_index.toString()));
		entity.setIs_del(0);

		entity.getMap().put("add_date_desc", true);

		Long recordCount = super.getFacade().getJxcSupplierService().getJxcSupplierCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<JxcSupplier> entityList = super.getFacade().getJxcSupplierService().getJxcSupplierPaginatedList(entity);
		
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
		dynaBean.set("queryString", super.serialize(request, "mod_id", "method"));
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String keySeq = (String) dynaBean.get("keySeq");
		String id = (String) dynaBean.get("id");
		String name = (String) dynaBean.get("name");
		String link_man = (String) dynaBean.get("link_man");
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
		JxcSupplier entity = new JxcSupplier();
		if (!StringUtils.isNotBlank(id)) {
			entity.setIs_del(0);
			entity.setName(name);
			entity.setShop_id(shop_id);
			entity = super.getFacade().getJxcSupplierService().getJxcSupplier(entity);
			if (entity != null) {
				String msg = super.getMessage(request, "jxc.supplier.name.repeat");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		}
		entity = new JxcSupplier();
		super.copyProperties(entity, form);
		entity.setAdd_date(date);
		entity.setAdd_user_id(user.getUser_id());
		if (addr != null && StringUtils.isNotBlank(addr)) {
			entity.setAddr(addr);
		}
		if (link_man != null && StringUtils.isNotBlank(link_man)) {
			entity.setLink_man(link_man);
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
		if (link_man != null && StringUtils.isNotBlank(link_man)) {
			entity.setLink_man(link_man);
		}
		if (name != null && StringUtils.isNotBlank(name)) {
			entity.setName(name);
		}
		if (addr != null && StringUtils.isNotBlank(addr)) {
			entity.setAddr(addr);
		}
		entity.setShop_id(shop_id);
		entity.setShop_p_index(Long.parseLong(user.getStdEntpMain().getP_index().toString()));
		if (tel != null && StringUtils.isNotBlank(tel)) {
			entity.setTel(tel);
		}
		if (!StringUtils.isNotBlank(id)) {
			super.getFacade().getJxcSupplierService().createJxcSupplier(entity);

		} else {
			super.getFacade().getJxcSupplierService().modifyJxcSupplier(entity);
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
		JxcSupplier entity = new JxcSupplier();
		entity.setId(Long.parseLong(id));
		entity = super.getFacade().getJxcSupplierService().getJxcSupplier(entity);

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

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		logger.info("delete begin");
		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			JxcSupplier entity = new JxcSupplier();
			entity.setId(new Long(id));
			entity.setIs_del(1);
			getFacade().getJxcSupplierService().modifyJxcSupplier(entity);
		} else if (!ArrayUtils.isEmpty(pks)) {
			JxcSupplier entity = new JxcSupplier();
			entity.getMap().put("pks", pks);
			entity.setIs_del(1);
			getFacade().getJxcSupplierService().modifyJxcSupplier(entity);
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

	public ActionForward saveContinue(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String name = (String) dynaBean.get("name");
		String link_man = (String) dynaBean.get("link_man");
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
		JxcSupplier entity = new JxcSupplier();
		entity.setIs_del(0);
		entity.setShop_id(shop_id);
		entity.setName(name);
		entity = super.getFacade().getJxcSupplierService().getJxcSupplier(entity);
		if (entity != null) {
			String msg = super.getMessage(request, "jxc.supplier.name.repeat");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		entity = new JxcSupplier();
		entity.setAdd_date(date);
		entity.setAdd_user_id(user.getUser_id());
		if (addr != null && StringUtils.isNotBlank(addr)) {
			entity.setAddr(addr);
		}
		if (link_man != null && StringUtils.isNotBlank(link_man)) {
			entity.setLink_man(link_man);
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
		super.getFacade().getJxcSupplierService().createJxcSupplier(entity);

		return new ActionForward("/../WEB-INF/pages/jsp/client/JxcSupplier/formContinue.jsp");

	}

	public ActionForward exportToExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		String exportExcel = (String) dynaBean.get("exportExcel");
		if (StringUtils.isNotBlank(exportExcel) && "1".equals(exportExcel)) {
			JxcSupplier entity = new JxcSupplier();
			entity.setIs_del(0);

			String keySeq = (String) dynaBean.get("keySeq");

			request.setAttribute("not_validate_record", "true");
			StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);

			if (null == user) {
				return null;
			}

			Long shop_id = user.getStdEntpMain().getShop_id();
			// Integer shop_p_index = user.getStdEntpMain().getP_index();
			entity.setShop_id(shop_id);
			entity.getMap().put("add_date_desc", true);
			super.copyProperties(entity, form);
			List<JxcSupplier> entityList = super.getFacade().getJxcSupplierService().getJxcSupplierList(entity);

			if (entityList.size() >= 500) { // 禁止导出大于 500条记录的数据
				return null;
			}
			JSONObject result = new JSONObject();
			JSONArray list = new JSONArray();
			for (int i = 0; i < entityList.size(); i++) {
				JSONObject obj = new JSONObject();
				JxcSupplier cur = entityList.get(i);
				obj.put("name", cur.getName());
				obj.put("link_man", cur.getLink_man());
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

			out
					.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
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
		JxcSupplier entity = new JxcSupplier();
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

		Long count = super.getFacade().getJxcSupplierService().getJxcSupplierCount(entity);
		isExist = String.valueOf(count);
		super.render(response, isExist, "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward stockBillList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String supper_id = (String) dynaBean.get("supper_id");
		String keySeq = (String) dynaBean.get("keySeq");
		String add_date_gt = (String) dynaBean.get("add_date_gt");
		String add_date_lt = (String) dynaBean.get("add_date_lt");
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
		if (StringUtils.isBlank(add_date_gt)) {
			add_date_gt = DateFormatUtils.format(now, "yyyy-MM-") + "01";
		}
		if (StringUtils.isBlank(add_date_lt)) {
			add_date_lt = DateFormatUtils.format(now, "yyyy-MM-dd");
		}
		dynaBean.set("add_date_gt", add_date_gt);
		dynaBean.set("add_date_lt", add_date_lt);
		JxcStockBill jxcStockBill = new JxcStockBill();
		if(StringUtils.isNotBlank(supper_id)){
			JxcSupplier jxc_supplier = new JxcSupplier();
			jxc_supplier.setId(Long.valueOf(supper_id));
			jxc_supplier = super.getFacade().getJxcSupplierService().getJxcSupplier(jxc_supplier);
			dynaBean.set("supplier_name", jxc_supplier.getName());
			jxcStockBill.setSupplier_id(Long.valueOf(supper_id));
		}
		jxcStockBill.setShop_id(shop_id);
		jxcStockBill.setIs_del(0);
		jxcStockBill.getMap().put("add_date_lt", add_date_lt);
		jxcStockBill.getMap().put("add_date_gt", add_date_gt);
		Long recordCount = super.getFacade().getJxcStockBillService().getJxcStockBillCount(jxcStockBill);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		jxcStockBill.getRow().setFirst(pager.getFirstRow());
		jxcStockBill.getRow().setCount(pager.getRowCount());
		List<JxcStockBill> jxcStockBillList = super.getFacade().getJxcStockBillService().getJxcStockBillPaginatedList(
				jxcStockBill);
		for (JxcStockBill temp : jxcStockBillList) {
			JxcStockBillDetails jxc_sb_details = new JxcStockBillDetails();
			jxc_sb_details.setStock_bill_id(temp.getId());
			List<JxcStockBillDetails> listJxcStockBillDetails = super.getFacade().getJxcStockBillDetailsService()
					.getJxcStockBillDetailsList(jxc_sb_details);
			temp.setJxcStockBillDetailsList(listJxcStockBillDetails);
		}
		request.setAttribute("jxcStockBillList", jxcStockBillList);
		super.setSupplierListToRequest(request, shop_id);
		return new ActionForward("/../WEB-INF/pages/jsp/client/JxcSupplier/list_stock_bill.jsp");
	}

	public ActionForward toExcelForFlv(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ParseException {

		DynaBean dynaBean = (DynaBean) form;
		String supper_id = (String) dynaBean.get("supper_id");
		String keySeq = (String) dynaBean.get("keySeq");
		String add_date_gt = (String) dynaBean.get("add_date_gt");
		String add_date_lt = (String) dynaBean.get("add_date_lt");
		StringBuffer queryCondition=new StringBuffer();
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}

		Date now = new Date();
		if (StringUtils.isBlank(add_date_gt)) {
			add_date_gt = DateFormatUtils.format(now, "yyyy-MM-") + "01";
		}
		if (StringUtils.isBlank(add_date_lt)) {
			add_date_lt = DateFormatUtils.format(now, "yyyy-MM-dd");
		}

		JxcStockBill jxcStockBill = new JxcStockBill();
		if(StringUtils.isNotBlank(supper_id)){
			JxcSupplier jxc_supplier = new JxcSupplier();
			jxc_supplier.setId(Long.valueOf(supper_id));
			jxc_supplier = super.getFacade().getJxcSupplierService().getJxcSupplier(jxc_supplier);
			dynaBean.set("supplier_name", jxc_supplier.getName());
			jxcStockBill.setSupplier_id(Long.valueOf(supper_id));
			queryCondition.append("供应商名称："+jxc_supplier.getName());
		}
		jxcStockBill.setIs_del(0);
		jxcStockBill.getMap().put("add_date_lt", add_date_lt);
		jxcStockBill.getMap().put("add_date_gt", add_date_gt);
		List<JxcStockBill> jxcStockBillList = super.getFacade().getJxcStockBillService().getJxcStockBillList(
				jxcStockBill);
		for (JxcStockBill temp : jxcStockBillList) {
			JxcStockBillDetails jxc_sb_details = new JxcStockBillDetails();
			jxc_sb_details.setStock_bill_id(temp.getId());
			List<JxcStockBillDetails> listJxcStockBillDetails = super.getFacade().getJxcStockBillDetailsService()
					.getJxcStockBillDetailsList(jxc_sb_details);
			temp.setJxcStockBillDetailsList(listJxcStockBillDetails);
		}

		Map<String, Object> model = new HashMap<String, Object>();
		if (null != jxcStockBillList && jxcStockBillList.size() > 0) {

			model.put("entityList", jxcStockBillList);
		}
		if(StringUtils.isNotBlank(add_date_gt) && StringUtils.isNotBlank(add_date_lt)){
			queryCondition.append("   查询时间："+add_date_gt).append("至"+add_date_lt);
		}
		if(!StringUtils.isNotBlank(add_date_gt) && StringUtils.isNotBlank(add_date_lt)){
			queryCondition.append("   查询时间：至").append(add_date_lt);
		}
		if(StringUtils.isNotBlank(add_date_gt) && !StringUtils.isNotBlank(add_date_lt)){
			queryCondition.append("   查询时间："+add_date_gt).append("至今");
		}
		logger.info("query:{}"+queryCondition.toString());
		model.put("title", "供应商交易明细表");
		model.put("queryCondition", queryCondition.toString());
		String content = getFacade().getTemplateService().getContent("jxc/jxc_supplier_list.ftl", model);
		// 下载文件出现乱码时，请参见此处
		String fname = EncryptUtilsV2.encodingFileName("供应商交易明细表.xls");

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
		String supper_id = (String) dynaBean.get("supper_id");
		String keySeq = (String) dynaBean.get("keySeq");
		String add_date_gt = (String) dynaBean.get("add_date_gt");
		String add_date_lt = (String) dynaBean.get("add_date_lt");
		StringBuffer queryCondition=new StringBuffer();
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}

		Date now = new Date();
		if (StringUtils.isBlank(add_date_gt)) {
			add_date_gt = DateFormatUtils.format(now, "yyyy-MM-") + "01";
		}
		if (StringUtils.isBlank(add_date_lt)) {
			add_date_lt = DateFormatUtils.format(now, "yyyy-MM-dd");
		}

		JxcStockBill jxcStockBill = new JxcStockBill();
		if(StringUtils.isNotBlank(supper_id)){
			JxcSupplier jxc_supplier = new JxcSupplier();
			jxc_supplier.setId(Long.valueOf(supper_id));
			jxc_supplier = super.getFacade().getJxcSupplierService().getJxcSupplier(jxc_supplier);
			dynaBean.set("supplier_name", jxc_supplier.getName());
			jxcStockBill.setSupplier_id(Long.valueOf(supper_id));
			queryCondition.append("供应商名称："+jxc_supplier.getName());
		}
		jxcStockBill.setIs_del(0);
		jxcStockBill.getMap().put("add_date_lt", add_date_lt);
		jxcStockBill.getMap().put("add_date_gt", add_date_gt);
		List<JxcStockBill> jxcStockBillList = super.getFacade().getJxcStockBillService().getJxcStockBillList(
				jxcStockBill);
		for (JxcStockBill temp : jxcStockBillList) {
			JxcStockBillDetails jxc_sb_details = new JxcStockBillDetails();
			jxc_sb_details.setStock_bill_id(temp.getId());
			List<JxcStockBillDetails> listJxcStockBillDetails = super.getFacade().getJxcStockBillDetailsService()
					.getJxcStockBillDetailsList(jxc_sb_details);
			temp.setJxcStockBillDetailsList(listJxcStockBillDetails);
		}
		if(StringUtils.isNotBlank(add_date_gt) && StringUtils.isNotBlank(add_date_lt)){
			queryCondition.append("   查询时间："+add_date_gt).append("至"+add_date_lt);
		}
		if(!StringUtils.isNotBlank(add_date_gt) && StringUtils.isNotBlank(add_date_lt)){
			queryCondition.append("   查询时间：至").append(add_date_lt);
		}
		if(StringUtils.isNotBlank(add_date_gt) && !StringUtils.isNotBlank(add_date_lt)){
			queryCondition.append("   查询时间："+add_date_gt).append("至今");
		}
		logger.info("query:{}"+queryCondition.toString());
		request.setAttribute("jxcStockBillList", jxcStockBillList);
		request.setAttribute("queryCondition", queryCondition.toString());
		return new ActionForward("/../WEB-INF/pages/jsp/client/_public_print_supplier_sell_details.jsp");
	}
}
