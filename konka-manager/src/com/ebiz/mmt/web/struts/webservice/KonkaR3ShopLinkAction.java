package com.ebiz.mmt.web.struts.webservice;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3ShopLink;
import com.ebiz.mmt.domain.KonkaSpActivityBookReport;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author OuYang,BaiYang
 * @version 2014-1-23
 */

public class KonkaR3ShopLinkAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward listForJsonp(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		// 用户名
		String user_id = (String) dynaBean.get("user_id");
		// 密码
		String userpass = (String) dynaBean.get("userpass");
		String dept_id = (String) dynaBean.get("dept_id");// 分公司id
		String customer_name_like = (String) dynaBean.get("customer_name_like");// 客户名称
		String r3_code_like = (String) dynaBean.get("r3_code_like");// R3编码
		String real_name_like = (String) dynaBean.get("real_name_like");// 姓名
		String sex = (String) dynaBean.get("sex");// 性别
		String birthday_s = (String) dynaBean.get("birthday_s");// 生日
		String birthday_e = (String) dynaBean.get("birthday_e");// 生日
		String tel_like = (String) dynaBean.get("tel_like");// 固定电话
		String mobile_like = (String) dynaBean.get("mobile_like");// 移动电话
		String telephone_like = (String) dynaBean.get("telephone_like");// 移动电话
		String job_like = (String) dynaBean.get("job_like");// 岗位
		String position = (String) dynaBean.get("position");// 职务
		String fax_like = (String) dynaBean.get("fax_like");// 传真
		String email_like = (String) dynaBean.get("email_like");// 电子邮箱
		String weixin_like = (String) dynaBean.get("weixin_like");// 微信号
		String qq_like = (String) dynaBean.get("qq_like");// QQ号
		String is_default = (String) dynaBean.get("is_default");// 是否默认
		String is_valid = (String) dynaBean.get("is_valid");// 是否有效
		String is_kf = (String) dynaBean.get("is_kf");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

		String jsonpcallback = request.getParameter("jsonpcallback");

		// 判断session是否超时
		/*
		 * PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO); if (null == ui) {
		 * String msg = super.getMessage(request, "user.session.isEmpty"); super.renderJavaScript(response,
		 * "window.onload=function(){alert('" + msg + "');history.back();}"); return null; }
		 */

		// 分公司、客户名称、R3编码、姓名、性别、生日、固定电话、移动电话、岗位、职务、传真人、电子邮箱、微信号、QQ号、是否默认、是否有效、

		String page = (String) dynaBean.get("page");// 页码
		if (StringUtils.isEmpty(page) || !GenericValidator.isInt(page)) {
			page = "1";
		}
		// 判断session是否超时
		PeProdUser ui = (PeProdUser) checkUserid(user_id, userpass,android_version,ios_version);

		// 分公司、客户名称、R3编码、姓名、性别、生日、固定电话、移动电话、岗位、职务、传真人、电子邮箱、微信号、QQ号、是否默认、是否有效、

		KonkaR3ShopLink entity = new KonkaR3ShopLink();
		String r3_code = (String) dynaBean.get("r3_code");
		entity.getMap().put("r3_code", r3_code);
		String dept_name = (String) dynaBean.get("dept_name");
		entity.getMap().put("dept_name", dept_name);
		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("dept_id", dept_id);
		}

		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like);
		}
		if (StringUtils.isNotBlank(r3_code_like)) {
			entity.getMap().put("r3_code_like", r3_code_like);
		}
		if (StringUtils.isNotBlank(real_name_like)) {
			entity.getMap().put("real_name_like", real_name_like);
		}
		if (StringUtils.isNotBlank(sex) && GenericValidator.isInt(sex)) {
			entity.setSex(Integer.valueOf(sex));
		}
		if (StringUtils.isNotBlank(birthday_s)) {
			entity.getMap().put("birthday_s", birthday_s + " 00:00:00");
		}
		if (StringUtils.isNotBlank(birthday_e)) {
			entity.getMap().put("birthday_e", birthday_e + " 23:59:59");
		}
		if (StringUtils.isNotBlank(tel_like)) {
			entity.getMap().put("tel_like", tel_like);
		}
		if (StringUtils.isNotBlank(telephone_like)) {
			entity.getMap().put("telephone_like", telephone_like);
		}
		if (StringUtils.isNotBlank(job_like)) {
			entity.getMap().put("job_like", job_like);
		}
		if (StringUtils.isNotBlank(position)) {
			entity.setPosition(position);
		}
		if (StringUtils.isNotBlank(fax_like)) {
			entity.getMap().put("fax_like", fax_like);
		}
		if (StringUtils.isNotBlank(email_like)) {
			entity.getMap().put("email_like", email_like);
		}
		if (StringUtils.isNotBlank(weixin_like)) {
			entity.getMap().put("weixin_like", weixin_like);
		}
		if (StringUtils.isNotBlank(qq_like)) {
			entity.getMap().put("qq_like", qq_like);
		}
		String min = (String) dynaBean.get("min");// 是否有效
		String max = (String) dynaBean.get("max");
		String first = (String) dynaBean.get("first");// 是否有效
		String count = (String) dynaBean.get("count");
		// 是否默认
		if (StringUtils.isNotBlank(is_default)) {
			entity.setIs_default(is_default);
		}
		// 是否有效
		if (StringUtils.isNotBlank(is_valid)) {
			entity.setIs_valid(is_valid);
		}

		// 检查权限
		// 数据级别判断开始
		
		Long _dept_id = 0L;
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见

			KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				entity.getMap().put("dept_id_start", _dept_id);
				// entity.getMap().put("fgs_dept_value", _dept_id);
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			_dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", _dept_id);
			break;
		case 0:
			_dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			// entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
			break;
		default:
			// 出错
		}
		 

		// 分页
		/*
		 * Long recordCount = super.getFacade().getKonkaR3ShopLinkService().getKonkaR3ShopLinkForCustomerCount(entity);
		 * pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		 * entity.getRow().setFirst(pager.getFirstRow()); entity.getRow().setCount(pager.getRowCount());
		 */

		if (null != min && StringUtils.isNotBlank(min)) {
			// 分页
			// Long recordCount =
			// super.getFacade().getKonkaR3ShopLinkService().getKonkaR3ShopLinkForCustomerCount(entity);
			// pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
			entity.getRow().setFirst(Integer.valueOf(min) - 10);
			entity.getRow().setCount(10);
		}

		if (null != max && StringUtils.isNotBlank(max)) {
			// 分页
			// Long recordCount =
			// super.getFacade().getKonkaR3ShopLinkService().getKonkaR3ShopLinkForCustomerCount(entity);
			// pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
			entity.getRow().setFirst(Integer.valueOf(max));
			entity.getRow().setCount(10);
		}

		// 查询
		List<KonkaR3ShopLink> entityList = super.getFacade().getKonkaR3ShopLinkService()
				.getKonkaR3ShopLinkPaginatedForCustomerList(entity);
		List<KonkaR3ShopLink> entityList1 = new ArrayList<KonkaR3ShopLink>();

		// kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
		// entity.getMap().put("dept_name", kd.getDept_name());
		// request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		// 分页
		Long recordCount = super.getFacade().getKonkaR3ShopLinkService().getKonkaR3ShopLinkForCustomerCount(entity);
		pager.init(recordCount, pager.getPageSize(), page);
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		if (null != min && StringUtils.isNotBlank(min)) {
			// 分页
			// Long recordCount =
			// super.getFacade().getKonkaR3ShopLinkService().getKonkaR3ShopLinkForCustomerCount(entity);
			// pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
			entity.getRow().setFirst(Integer.valueOf(min) - 10);
			entity.getRow().setCount(10);
		}

		if (null != max && StringUtils.isNotBlank(max)) {
			// 分页
			// Long recordCount =
			// super.getFacade().getKonkaR3ShopLinkService().getKonkaR3ShopLinkForCustomerCount(entity);
			// pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
			entity.getRow().setFirst(Integer.valueOf(max));
			entity.getRow().setCount(10);
		}
		// 查询
		// List<KonkaR3ShopLink> entityList = super.getFacade().getKonkaR3ShopLinkService()
		// .getKonkaR3ShopLinkPaginatedForCustomerList(entity);

		// JSONArray jsonArray = JSONArray.fromObject(entityList);

		for (KonkaR3ShopLink konkaR3ShopLink : entityList) {
			KonkaR3ShopLink kk = new KonkaR3ShopLink();
			kk.setAdd_date(new Date());
			kk.setBirthday(new Date());
			kk.setReal_name(konkaR3ShopLink.getReal_name() == null ? "" : konkaR3ShopLink.getReal_name());
			kk.setTel(konkaR3ShopLink.getTel() == null ? "" : konkaR3ShopLink.getTel());
			kk.setLink_id(konkaR3ShopLink.getLink_id());
			kk.getMap()
					.put("dept_name",
							(konkaR3ShopLink.getMap().get("dept_name") == null ? "" : konkaR3ShopLink.getMap().get(
									"dept_name")));
			kk.getMap().put(
					"customer_name",
					(konkaR3ShopLink.getMap().get("customer_name") == null ? "" : konkaR3ShopLink.getMap().get(
							"customer_name")));
			entityList1.add(kk);
		}

		JSONArray jsonArray = JSONArray.fromObject(entityList1);

		int start = jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "POWERED-BY-MENGXIANHUI");
		// response.setHeader("Access-Control-Max-Age", "30");

		PrintWriter out = response.getWriter();
		String json = "";
		if (entityList.size() == 0) {
			json = "{" + "\"state\":" + "-1}";
		} else {
			json = "{" + "\"result\":" + "[" + jsonArray.toString().substring(start + 1, end + 1) + "]," + "\"state\":"
					+ "1}";
		}

		// String json = "{\"user_name\":" + 1 + "}";
		out.print(jsonpcallback + "(" + json + ")");
		out.flush();
		out.close();
		return null;
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String user_id = (String) dynaBean.get("user_id");
		// 密码
		String userpass = (String) dynaBean.get("userpass");
		Pager pager = (Pager) dynaBean.get("pager");
		String dept_name_like = (String) dynaBean.get("dept_name_like");
		String dept_id = (String) dynaBean.get("dept_id");// 分公司id
		String customer_name_like = (String) dynaBean.get("customer_name_like");// 客户名称
		String real_name_like = (String) dynaBean.get("real_name_like");// 姓名
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		if (StringUtils.isEmpty(user_id) || StringUtils.isEmpty(userpass)) {
			return null;
		} else {
			request.setAttribute("user_id", user_id);
			request.setAttribute("userpass", userpass);
		}

		PeProdUser ui = checkUserid(user_id, userpass,android_version,ios_version);
		if (null == ui) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('"
					+ msg + "');history.back();}");
			return null;
		}

		// 分公司、客户名称、R3编码、姓名、性别、生日、固定电话、移动电话、岗位、职务、传真人、电子邮箱、微信号、QQ号、是否默认、是否有效、

		KonkaR3ShopLink entity = new KonkaR3ShopLink();

		if (StringUtils.isNotBlank(dept_name_like)) {
			entity.getMap().put("dept_name_like", dept_name_like);
		}
		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like);
		}

		if (StringUtils.isNotBlank(real_name_like)) {
			entity.getMap().put("real_name_like", real_name_like);
		}

		// 检查权限
		// 数据级别判断开始
		Long __dept_id = StringUtils.isNotBlank(dept_id) ? Long
				.valueOf(dept_id) : ui.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			if (StringUtils.isNotBlank(dept_id)) {
				entity.getMap().put("dept_id", null);
				entity.getMap().put("fgs_dept_value", dept_id);
			}

			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				// entity.getMap().put("dept_id_start", __dept_id);
				entity.getMap().put("fgs_dept_value", __dept_id);
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);
			break;
		case 0:
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			// entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", ui.getId());
		// 数据级别判断结束

		// 分公司
		KonkaDept kd = new KonkaDept();
		if (max_dlevel == 9) {
			kd.setDept_id(0L);
		} else {
			kd.setDept_id(ui.getDept_id());
		}
		kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
		entity.getMap().put("dept_name", kd.getDept_name());

		Long recordCount = super.getFacade().getKonkaR3ShopLinkService()
				.getKonkaR3ShopLinkForCustomerCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		// 查询
		List<KonkaR3ShopLink> entityList = super.getFacade()
				.getKonkaR3ShopLinkService()
				.getKonkaR3ShopLinkPaginatedForCustomerList(entity);
		request.setAttribute("entityList", entityList);

		// request.setAttribute("deptList", super.getDeptInfoListWithOutLimit(
		// mapping, form, request, response));

		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String link_id = (String) dynaBean.get("link_id");

		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

		if (StringUtils.isEmpty(inuser_id) || StringUtils.isEmpty(inuserpass)) {
			return null;
		} else {
			request.setAttribute("user_id", inuser_id);
			request.setAttribute("userpass", inuserpass);
		}
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);

		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('"
					+ msg + "');history.back();}");
			return null;
		}

		KonkaR3ShopLink entity = new KonkaR3ShopLink();
		entity.setLink_id(Long.valueOf(link_id));
		entity = super.getFacade().getKonkaR3ShopLinkService()
				.getKonkaR3ShopLinkForCustomer(entity);
		super.copyProperties(form, entity);

		PeProdUser pp = new PeProdUser();
		pp.setId(entity.getAdd_user_id());
		pp = super.getFacade().getPeProdUserService().getPeProdUser(pp);

		if (pp != null) {
			dynaBean.set("user_name", pp.getUser_name());
		}

		return mapping.findForward("view");
	}

	// 修改
	public ActionForward editForJsonp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 导航信息
		// setNaviStringToRequestScope(form, request);
		// 防止重复提交
		// super.saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String link_id = (String) dynaBean.get("link_id");
		String jsonpcallback = request.getParameter("jsonpcallback");
		KonkaR3ShopLink entity = new KonkaR3ShopLink();
		entity.setLink_id(Long.valueOf(link_id));
		entity = super.getFacade().getKonkaR3ShopLinkService()
				.getKonkaR3ShopLinkForCustomer(entity);

		JSONArray jsonArray = JSONArray.fromObject(entity);

		int start = jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers",
				"POWERED-BY-MENGXIANHUI");
		// response.setHeader("Access-Control-Max-Age", "30");
		PrintWriter out = response.getWriter();
		String json = jsonArray.toString().substring(start + 1, end + 1);

		StringBuffer sb = new StringBuffer("\"dept_list\":[");
		KonkaDept dept = new KonkaDept();
		dept.setIs_del(0);
		dept.setDept_type(3);
		List<KonkaDept> dept_list = super.getFacade().getKonkaDeptService()
				.getKonkaDeptList(dept);
		for (KonkaDept konkaDept : dept_list) {
			sb.append("{\"dept_id\":\""
					+ String.valueOf(konkaDept.getDept_id()) + "\",");
			sb.append("\"dept_name\":\""
					+ StringEscapeUtils.escapeJavaScript(konkaDept
							.getDept_name()) + "\"},");
		}

		String ss = sb.toString();
		ss = ss.substring(0, ss.length() - 1);
		ss = ss + "]";

		json = json.substring(0, json.length() - 1);

		// out.print(ss);

		out.print(jsonpcallback + "(" + json + "," + ss + "})");
		out.flush();
		out.close();
		return null;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 导航信息
		// 防止重复提交
		// super.saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String link_id = (String) dynaBean.get("link_id");

		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

		if (StringUtils.isEmpty(inuser_id) || StringUtils.isEmpty(inuserpass)) {
			return null;
		} else {
			request.setAttribute("user_id", inuser_id);
			request.setAttribute("userpass", inuserpass);
		}
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);

		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('"
					+ msg + "');history.back();}");
			return null;
		}

		KonkaR3ShopLink entity = new KonkaR3ShopLink();
		entity.setLink_id(Long.valueOf(link_id));
		entity = super.getFacade().getKonkaR3ShopLinkService()
				.getKonkaR3ShopLinkForCustomer(entity);

		PeProdUser pp = new PeProdUser();
		pp.setId(entity.getAdd_user_id());
		pp = super.getFacade().getPeProdUserService().getPeProdUser(pp);

		if (pp != null) {
			dynaBean.set("user_name", pp.getUser_name());
		}

		super.copyProperties(form, entity);
		return mapping.findForward("input");
	}

	// 保存
	public ActionForward saveForJsonp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String link_id = (String) dynaBean.get("link_id");
		String real_name = (String) dynaBean.get("real_name");
		String jsonpcallback = request.getParameter("jsonpcallback");

		KonkaR3ShopLink entity = new KonkaR3ShopLink();
		super.copyProperties(entity, form);

		if (StringUtils.isEmpty(link_id)) {
			// 获取客户姓名，客户r3编码的id

		} else {
			if (StringUtils.isNotBlank(link_id)) {
				entity.setLink_id(Long.valueOf(link_id));
			}
			if (StringUtils.isNotBlank(real_name)) {
				entity.setReal_name(real_name);
			}

			entity.setAdd_date(new Date());
			super.getFacade().getKonkaR3ShopLinkService()
					.modifyKonkaR3ShopLink(entity);
			// super.saveMessage(request, "entity.updated");
		}

		response.setContentType("application/json;charset=UTF-8");

		response.setHeader("Cache-Control", "no-cache");

		PrintWriter out = response.getWriter();

		String json = "{\"state\":1}";

		out.print(jsonpcallback + "(" + json + ")");

		out.flush();

		out.close();

		return null;

	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String link_id = (String) dynaBean.get("link_id");
		String r3_code = (String) dynaBean.get("r3_code");

		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		if (StringUtils.isEmpty(inuser_id) || StringUtils.isEmpty(inuserpass)) {
			return null;
		} else {
			request.setAttribute("user_id", inuser_id);
			request.setAttribute("userpass", inuserpass);
		}
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('"
					+ msg + "');history.back();}");
			return null;
		}
		KonkaR3ShopLink entity = new KonkaR3ShopLink();
		super.copyProperties(entity, form);

		if (StringUtils.isEmpty(link_id)) {
			// 获取客户姓名，客户r3编码的id
			KonkaR3Shop ks = new KonkaR3Shop();

			ks.setR3_code(r3_code);
			List<KonkaR3Shop> shopList = super.getFacade()
					.getKonkaR3ShopService().getKonkaR3ShopList(ks);
			if (null != shopList && shopList.size() > 0) {
				ks = shopList.get(0);
				if (null != ks) {
					Long id = ks.getId();
					entity.setAdd_user_id(userInfo.getId());
					entity.setR3_shop_id(id);
					entity.setAdd_date(new Date());
					super.getFacade().getKonkaR3ShopLinkService()
							.createKonkaR3ShopLink(entity);
					super.saveMessage(request, "entity.inserted");
				} else {
					super.renderJavaScript(response,
							"The customer linked is empty!");
					return null;
				}
			} else {
				super.renderJavaScript(response,
						"The customer linked is empty!");
				return null;
			}
		} else {

			entity.setAdd_date(new Date());
			super.getFacade().getKonkaR3ShopLinkService()
					.modifyKonkaR3ShopLink(entity);
			super.saveMessage(request, "entity.updated");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&user_id=" + inuser_id);
		pathBuffer.append("&userpass=" + inuserpass);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	// 添加
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

		if (StringUtils.isEmpty(inuser_id) || StringUtils.isEmpty(inuserpass)) {
			return null;
		} else {
			request.setAttribute("user_id", inuser_id);
			request.setAttribute("userpass", inuserpass);
		}
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);

		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('"
					+ msg + "');history.back();}");
			return null;
		}

		String dept_id = (String) dynaBean.get("dept_id");// 分公司id

		KonkaR3ShopLink entity = new KonkaR3ShopLink();
		// 检查权限
		// 数据级别判断开始
		Long __dept_id = StringUtils.isNotBlank(dept_id) ? Long
				.valueOf(dept_id) : userInfo.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			if (StringUtils.isNotBlank(dept_id)) {
				entity.getMap().put("dept_id", null);
				entity.getMap().put("fgs_dept_value", dept_id);
			}

			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super
					.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				// entity.getMap().put("dept_id_start", __dept_id);
				entity.getMap().put("fgs_dept_value", __dept_id);
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);
			break;
		case 0:
			__dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			// entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", userInfo.getId());
		// 数据级别判断结束

		// 分公司
		KonkaDept kd = new KonkaDept();
		if (max_dlevel == 9) {
			kd.setDept_id(0L);
		} else {
			kd.setDept_id(userInfo.getDept_id());
		}
		kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
		entity.getMap().put("dept_name", kd.getDept_name());
		request.setAttribute("sybDeptInfoList",
				getDeptInfoListWithOutLimitForMobile(inuser_id, inuserpass,android_version,ios_version));

		// 拿到客户的列表

		List<KonkaR3Shop> customerList = getCustomer(userInfo);
		request.setAttribute("customerList", customerList);

		dynaBean.set("user_name", userInfo.getUser_name());

		return mapping.findForward("input");
	}

	/**
	 * 根据用户按照部门级别获取客户
	 * 
	 * @param userInfo
	 * @return
	 */
	protected List<KonkaR3Shop> getCustomer(PeProdUser userInfo) {
		KonkaR3Shop entity = new KonkaR3Shop();

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());

		Long _dept_id = 0L;
		int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		// return null;
		case 8:
			// 分公司及以下部门可见

			KonkaDept dept_fgs = super
					.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				entity.getMap().put("dept_id_start", _dept_id);
				// entity.getMap().put("fgs_dept_value", _dept_id);
			}
			break;

		case 7:
			// 我所在的部门及以下部门可见
			_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", _dept_id);
			break;
		case 0:
			_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			// entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", userInfo.getId());

		// entity.setIs_del(0L);// 未被停用的
		List<KonkaR3Shop> entityList = super.getFacade()
				.getKonkaR3ShopService().getKonkaR3ShopForCustVisit(entity);
		logger.info("++++++++++++++++++++++++++++++++");
		return entityList;
	}


	protected List<KonkaDept> getDeptInfoListWithOutLimitForMobile(
			String user_id, String userpass, String android_version, String ios_version) {

		PeProdUser userInfo = null;
		try {
			userInfo = checkUserid(user_id, userpass,android_version,ios_version);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade()
				.getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_10 = false;

		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() < 30L
					|| peRoleUser.getRole_id().intValue() == 140317
					|| peRoleUser.getRole_id().intValue() == 1001
					|| (peRoleUser.getRole_id() >= 200 && peRoleUser
							.getRole_id() < 300)) {
				role_id_eq_10 = true;
			}

		}

		Long fgs_dept_id = 0L;
		Long currentUserDeptId = userInfo.getDept_id();

		KonkaDept dept_level3 = this.getSuperiorForDeptType(currentUserDeptId,
				3);
		if (null != dept_level3 && null != dept_level3.getDept_id()) {
			fgs_dept_id = dept_level3.getDept_id();
		}

		KonkaDept entity = new KonkaDept();
		entity.setIs_del(0);

		entity.getMap().put("order_by_dept_name", true);
		List<KonkaDept> deptInfoList = new ArrayList<KonkaDept>();
		if (role_id_eq_10) {// 管理员
			entity.setPar_id(0L);
			entity.setDept_type(3);
			deptInfoList = getFacade().getKonkaDeptService().getKonkaDeptList(
					entity);

		} else {
			// 或者办事处
			// 都通过父子查询级dept_type
			// 查出所属分公司
			KonkaDept _dept = this.getSuperiorForDeptType(currentUserDeptId, 3);
			if (null != _dept && null != _dept.getDept_id()) {
				entity.setDept_id(_dept.getDept_id());
				deptInfoList = getFacade().getKonkaDeptService()
						.getKonkaDeptList(entity);
			}
		}
		return deptInfoList;
	}

}