package com.ebiz.mmt.web.struts.manager.admin;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.BaseVisitType;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileCustVisit;
import com.ebiz.mmt.domain.KonkaMobileCustVisitDetail;
import com.ebiz.mmt.domain.KonkaMobileCustVisitGps;
import com.ebiz.mmt.domain.KonkaMobileCustVisitType;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class KonkaMobileCustVisitNewAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String report_type = (String) dynaBean.get("report_type");
		String mod_id = (String) dynaBean.get("mod_id");
		dynaBean.set("is_del", "0");
		if (StringUtils.isNotBlank(report_type)) {
			dynaBean.set("report_type", report_type);
		} else {
			dynaBean.set("report_type", 1);
		}
		// dynaBean.set("mod_id", mod_id);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendarbegin = Calendar.getInstance();
		calendarbegin.set(Calendar.DAY_OF_MONTH, calendarbegin.getActualMinimum(Calendar.DAY_OF_MONTH));
		String begindate = df.format(calendarbegin.getTime());
		dynaBean.set("_begin_date", begindate);
		Calendar calendarend = Calendar.getInstance();
		String enddate = df.format(calendarend.getTime());
		dynaBean.set("_end_date", enddate);

		request.setAttribute("mod_id", mod_id);
		return new ActionForward("/../manager/admin/KonkaMobileCustVisitNew/list.jsp");
	}

	public ActionForward initList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		// Pager pager = (Pager) dynaBean.get("pager");

		String rows = (String) dynaBean.get("rows");// 每页显示条数
		String page = (String) dynaBean.get("page");// 当前第几页

		String mod_id = (String) dynaBean.get("mod_id");
		String report_type = (String) dynaBean.get("report_type");
		String begin_date = (String) dynaBean.get("_begin_date");
		String end_date = (String) dynaBean.get("_end_date");
		String state = (String) dynaBean.get("state");
		String is_del = (String) dynaBean.get("is_del");
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String shop_id = (String) dynaBean.get("shop_id");
		String shop_name_like = (String) dynaBean.get("shop_name_like");
		String report_nae_like = (String) dynaBean.get("report_nae_like");// 上报人
		String subcomp_name_like = (String) dynaBean.get("subcomp_name_like");// 分公司
		String cust_type_id = (String) dynaBean.get("cust_type_id");// 分公司
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(user.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().intValue() == 60) {
				flag = true;
			}
		}

		KonkaMobileCustVisit entity = new KonkaMobileCustVisit();
		if (flag) {// 如果是业务员，只能看到自己的拜访记录
			entity.setAdd_user_id(user.getId());
		} else {

			Long _dept_id = 0L;
			int max_dlevel = super.getMaxDLevel(user.getId()); // 获取当前用户的最高可视部门级别
			logger.info("Max level : {}", max_dlevel);
			switch (max_dlevel) {
			case 9:
				// 集团及以下部门可见
				_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
				break;
			case 8:
				// 分公司及以下部门可见

				KonkaDept dept_fgs = super.getKonkaDeptForFgs(user.getDept_id()); // 查询部门分公司
				if (null != dept_fgs && null != dept_fgs.getDept_id()) {
					_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
					entity.getMap().put("dept_id_start", _dept_id);
					// entity.getMap().put("fgs_dept_value", _dept_id);
				}
				break;
			case 7:
				// 我所在的部门及以下部门可见
				_dept_id = user.getDept_id(); // 默认为当前用户所在部门
				entity.getMap().put("dept_id_start", _dept_id);
				break;
			case 0:
				_dept_id = user.getDept_id(); // 默认为当前用户所在部门
				// entity.getMap().put("dept_id_start", __dept_id);
				entity.getMap().put("filter_by_ywy_id_eq", user.getId());
				break;
			default:
				// 出错
			}
			entity.getMap().put("session_user_id", user.getId());

		}
		if (StringUtils.isNotBlank(report_type)) {
			entity.setReport_type(Integer.valueOf(report_type));
			dynaBean.set("report_type", report_type);
		} else {
			entity.setReport_type(1);
			report_type = "1";
		}
		if (StringUtils.isNotBlank(state)) {
			entity.setState(Integer.valueOf(state));
			dynaBean.set("state", state);
		}
		String begindate = "";
		String enddate = "";
		if (StringUtils.isEmpty(begin_date) || StringUtils.isEmpty(end_date)) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
			Calendar calendarbegin = Calendar.getInstance();
			calendarbegin.set(Calendar.DAY_OF_MONTH, calendarbegin.getActualMinimum(Calendar.DAY_OF_MONTH));
			begindate = df.format(calendarbegin.getTime());
			entity.getMap().put("time_start", begindate + " 00:00:00");
			dynaBean.set("_begin_date", begindate);
			Calendar calendarend = Calendar.getInstance();
			enddate = df.format(calendarend.getTime());
			entity.getMap().put("time_end", df1.format(calendarend.getTime()));
			dynaBean.set("_end_date", enddate);

		} else {
			if (StringUtils.isNotBlank(begin_date)) {
				begindate = begin_date + " 00:00:00";
				entity.getMap().put("time_start", begindate);
				dynaBean.set("_begin_date", begin_date);
			}
			if (StringUtils.isNotBlank(end_date)) {
				enddate = end_date + " 23:59:59";
				entity.getMap().put("time_end", enddate);
				dynaBean.set("_end_date", end_date);
			}
		}

		if (StringUtils.isNotBlank(is_del)) {
			entity.setIs_del(Integer.parseInt(is_del));
		} else {
			entity.setIs_del(0);
		}
		if (StringUtils.isNotBlank(r3_code_like)) {
			entity.getMap().put("r3_code_like", r3_code_like);
		}
		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like);
		}
		if (StringUtils.isNotBlank(shop_id)) {
			entity.getMap().put("shop_id", shop_id);
		}
		if (StringUtils.isNotBlank(shop_name_like)) {
			entity.getMap().put("shop_name_like", shop_name_like);
		}
		if (StringUtils.isNotBlank(report_nae_like)) {
			entity.getMap().put("report_nae_like", report_nae_like);
			dynaBean.set("report_nae_like", report_nae_like);
		}
		if (StringUtils.isNotBlank(subcomp_name_like)) {
			entity.getMap().put("subcomp_name_like", subcomp_name_like);
			dynaBean.set("subcomp_name_like", subcomp_name_like);
		}
		if (StringUtils.isNotBlank(cust_type_id)) {
			entity.getMap().put("cust_type_id", cust_type_id);
			dynaBean.set("cust_type_id", cust_type_id);
		}
		List<KonkaMobileCustVisit> entityList = null;
		if ("2".equals(report_type) || "1".equals(report_type) || "3".equals(report_type)) {

			Long recordCount = super.getFacade().getKonkaMobileCustVisitService()
			        .getKonkaMobileCustVisitAndDetailCount(entity);
			if (StringUtils.isBlank(page)) {
				page = "1";
			}
			if (StringUtils.isBlank(rows)) {
				rows = "10";
			}

			Integer first_row = Integer.valueOf(rows) * (Integer.valueOf(page) - 1);
			entity.getRow().setFirst(first_row);
			entity.getRow().setCount(Integer.valueOf(rows));
			if (recordCount > 0) {
				entityList = super.getFacade().getKonkaMobileCustVisitService()
				        .getKonkaMobileCustVisitAndDetailPaginatedList(entity);
			}

			String ctx = getCtxPath(request);

			// 封装成JSON字符串
			StringBuffer json = new StringBuffer("{");
			json.append("\"total\":\"").append(recordCount).append("\",");
			if (entityList == null) {
				json.append("\"rows\":[").append("]");
			} else {
				StringBuffer jsonBuffer = new StringBuffer();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if (null != entityList && entityList.size() > 0) {
					for (KonkaMobileCustVisit kb : entityList) {
						jsonBuffer.append("{");
						String begin_date_1 = "";// 开始时间
						try {
							begin_date_1 = sf.format(kb.getBegin_date());
						} catch (Exception e) {
						}
						String end_date_1 = "";// 结束时间
						try {
							end_date_1 = sf.format(kb.getEnd_date());
						} catch (Exception e) {
						}
						String subcomp_name = (String) kb.getMap().get("subcomp_name");// 分公司
						if (null == subcomp_name) {
							subcomp_name = "";
						}
						String report_nae = kb.getReport_nae();// 上报人
						if (null == report_nae) {
							report_nae = "";
						}
						String r3_code = (String) kb.getMap().get("r3_code");// R3编码
						if (null == r3_code) {
							r3_code = "";
						}
						String customer_name = (String) kb.getMap().get("customer_name");// 客户名称
						if (null == customer_name) {
							customer_name = "";
						}
						String shop_name = (String) kb.getMap().get("shop_name");// 门店名称
						if (null == shop_name) {
							shop_name = "";
						}
						String cust_type_name = (String) kb.getMap().get("cust_type_name");// 客户类型
						if (null == cust_type_name) {
							cust_type_name = "";
						}
						String visit_type_names = (String) kb.getMap().get("visit_type_names");// 拜访类型
						if (null == visit_type_names) {
							visit_type_names = "";
						}
						String feed_list = kb.getFeed_list();// 反馈问题
						if (null == feed_list) {
							feed_list = "";
						}
						String visit_desc = kb.getVisit_desc();// 拜访说明
						if (null == visit_desc) {
							visit_desc = "";
						}
						String consumer_name = kb.getConsumer_name();// 被访人
						if (null == consumer_name) {
							consumer_name = "";
						}
						String consumer_phone = kb.getConsumer_phone();// 被访人电话
						if (null == consumer_phone) {
							consumer_phone = "";
						}
						String state_1 = kb.getState().toString();// 拜访状态
						if (state_1.equals("0")) {
							state_1 = "需跟踪";
						} else if (state_1.equals("1")) {
							state_1 = "已关闭";
						} else {
							state_1 = "";
						}
						String add_date = "";// 添加时间
						try {
							add_date = sf.format(kb.getAdd_date());
						} catch (Exception e) {
						}
						String is_del_1 = kb.getIs_del().toString();// 删除状态
						if (is_del_1.equals("1")) {
							is_del_1 = "已删除";
						} else if (is_del_1.equals("0")) {
							is_del_1 = "正常";
						} else {
							is_del_1 = "";
						}
						String data_source = kb.getData_source().toString();// 数据来源
						if (data_source.equals("0")) {
							data_source = "手机端";
						} else if (data_source.equals("1")) {
							data_source = "手机端";
						} else if (data_source.equals("2")) {
							data_source = "WEB端";
						} else if (data_source.equals("3")) {
							data_source = "手机端";
						} else {
							data_source = "";
						}

						String address = (String) kb.getMap().get("address");// 地址
						if (null == address) {
							address = "";
						} else {
							address = address
							        + "<a class='loan_ct_view_button' href='javascript:void(0);' onclick='showMap("
							        + kb.getVisit_id() + ")'>定位</a>";
						}

						String fj_paths = (String) kb.getMap().get("fj_paths");// 处理附件
						String att = "";
						if (null != fj_paths) {
							String[] ff = fj_paths.split(",");
							int j = 0;
							for (int i = 0; i < ff.length; i++) {
								j++;
								att += "<a href=\'" + ctx + "/" + ff[i] + "\' target=\'_blank\'>附件" + j + "<br/></a>";
							}
						}

						jsonBuffer.append("\"begin_date\":\"").append(begin_date_1);
						jsonBuffer.append("\",\"end_date\":\"").append(end_date_1);
						jsonBuffer.append("\",\"subcomp_name\":\"").append(subcomp_name);
						jsonBuffer.append("\",\"report_nae\":\"").append(report_nae);
						jsonBuffer.append("\",\"visit_id\":\"").append(kb.getVisit_id());
						jsonBuffer.append("\",\"r3_code\":\"").append(r3_code);
						jsonBuffer.append("\",\"customer_name\":\"").append(customer_name);
						jsonBuffer.append("\",\"shop_name\":\"").append(shop_name);
						jsonBuffer.append("\",\"cust_type_name\":\"").append(cust_type_name);
						jsonBuffer.append("\",\"visit_type_names\":\"").append(visit_type_names);
						jsonBuffer.append("\",\"feed_list\":\"").append(feed_list);
						jsonBuffer.append("\",\"visit_desc\":\"").append(visit_desc);
						jsonBuffer.append("\",\"visit_desc\":\"").append(visit_desc);
						jsonBuffer.append("\",\"consumer_name\":\"").append(consumer_name);
						jsonBuffer.append("\",\"consumer_phone\":\"").append(consumer_phone);
						jsonBuffer.append("\",\"state\":\"").append(state_1);
						jsonBuffer.append("\",\"add_date\":\"").append(add_date);
						jsonBuffer.append("\",\"is_del\":\"").append(is_del_1);
						jsonBuffer.append("\",\"data_source\":\"").append(data_source);
						jsonBuffer.append("\",\"address\":\"").append(address);
						jsonBuffer.append("\",\"att\":\"").append(att);
						jsonBuffer.append("\"},");
					}
					String listStr = StringUtils.removeEnd(jsonBuffer.toString(), ",");
					json.append("\"rows\" : [").append(listStr).append("]");
				}
			}
			json.append("}");
			request.setAttribute("mod_id", mod_id);

			logger.info("josn---->{}" + json);
			response.setContentType("application/json;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
			out.close();
			return null;

		} else {
			Long recordCount = super.getFacade().getKonkaMobileCustVisitService()
			        .getKonkaMobileCustVisitLBCount(entity);
			if (StringUtils.isBlank(page)) {
				page = "1";
			}
			if (StringUtils.isBlank(rows)) {
				rows = "10";
			}

			Integer first_row = Integer.valueOf(rows) * (Integer.valueOf(page) - 1);
			entity.getRow().setFirst(first_row);
			entity.getRow().setCount(Integer.valueOf(rows));
			if (recordCount > 0) {
				entityList = super.getFacade().getKonkaMobileCustVisitService().getKonkaMobileCustVisitPaginatedLBList(
				        entity);
			}

		}
		request.setAttribute("mod_id", mod_id);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward exportData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String report_type = (String) dynaBean.get("report_type");
		String begin_date = (String) dynaBean.get("_begin_date");
		String end_date = (String) dynaBean.get("_end_date");
		String state = (String) dynaBean.get("state");
		String is_del = (String) dynaBean.get("is_del");
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String shop_id = (String) dynaBean.get("shop_id");
		String shop_name_like = (String) dynaBean.get("shop_name_like");
		String report_nae_like = (String) dynaBean.get("report_nae_like");// 上报人
		String subcomp_name_like = (String) dynaBean.get("subcomp_name_like");// 分公司
		String cust_type_id = (String) dynaBean.get("cust_type_id");//
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("网点列表")
		        + ".xls");
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(user.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().intValue() == 60) {
				flag = true;
			}
		}

		KonkaMobileCustVisit entity = new KonkaMobileCustVisit();
		if (flag) {// 如果是业务员，只能看到自己的拜访记录
			entity.setAdd_user_id(user.getId());
		} else {

			Long _dept_id = 0L;
			int max_dlevel = super.getMaxDLevel(user.getId()); // 获取当前用户的最高可视部门级别
			logger.info("Max level : {}", max_dlevel);
			switch (max_dlevel) {
			case 9:
				// 集团及以下部门可见
				_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
				break;
			case 8:
				// 分公司及以下部门可见

				KonkaDept dept_fgs = super.getKonkaDeptForFgs(user.getDept_id()); // 查询部门分公司
				if (null != dept_fgs && null != dept_fgs.getDept_id()) {
					_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
					entity.getMap().put("dept_id_start", _dept_id);
					// entity.getMap().put("fgs_dept_value", _dept_id);
				}
				break;
			case 7:
				// 我所在的部门及以下部门可见
				_dept_id = user.getDept_id(); // 默认为当前用户所在部门
				entity.getMap().put("dept_id_start", _dept_id);
				break;
			case 0:
				_dept_id = user.getDept_id(); // 默认为当前用户所在部门
				// entity.getMap().put("dept_id_start", __dept_id);
				entity.getMap().put("filter_by_ywy_id_eq", user.getId());
				break;
			default:
				// 出错
			}
			entity.getMap().put("session_user_id", user.getId());

		}
		if (StringUtils.isNotBlank(report_type)) {
			entity.setReport_type(Integer.valueOf(report_type));
			dynaBean.set("report_type", report_type);
		} else {
			entity.setReport_type(1);
			report_type = "1";
		}
		if (StringUtils.isNotBlank(state)) {
			entity.setState(Integer.valueOf(state));
			dynaBean.set("state", state);
		}
		String begindate = "";
		String enddate = "";
		if (StringUtils.isEmpty(begin_date) || StringUtils.isEmpty(end_date)) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
			Calendar calendarbegin = Calendar.getInstance();
			calendarbegin.set(Calendar.DAY_OF_MONTH, calendarbegin.getActualMinimum(Calendar.DAY_OF_MONTH));
			begindate = df.format(calendarbegin.getTime());
			entity.getMap().put("time_start", begindate + " 00:00:00");
			dynaBean.set("_begin_date", begindate);
			Calendar calendarend = Calendar.getInstance();
			enddate = df.format(calendarend.getTime());
			entity.getMap().put("time_end", df1.format(calendarend.getTime()));
			dynaBean.set("_end_date", enddate);

		} else {
			if (StringUtils.isNotBlank(begin_date)) {
				begindate = begin_date + " 00:00:00";
				entity.getMap().put("time_start", begindate);
				dynaBean.set("_begin_date", begin_date);
			}
			if (StringUtils.isNotBlank(end_date)) {
				enddate = end_date + " 23:59:59";
				entity.getMap().put("time_end", enddate);
				dynaBean.set("_end_date", end_date);
			}
		}

		if (StringUtils.isNotBlank(is_del)) {
			entity.setIs_del(Integer.parseInt(is_del));
		}
		if (StringUtils.isNotBlank(r3_code_like)) {
			entity.getMap().put("r3_code_like", changeToUTF8(r3_code_like));
		}
		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", changeToUTF8(customer_name_like));
		}
		if (StringUtils.isNotBlank(shop_id)) {
			entity.getMap().put("shop_id", shop_id);
		}
		if (StringUtils.isNotBlank(shop_name_like)) {
			entity.getMap().put("shop_name_like", changeToUTF8(shop_name_like));
		}
		if (StringUtils.isNotBlank(report_nae_like)) {
			entity.getMap().put("report_nae_like", changeToUTF8(report_nae_like));
			dynaBean.set("report_nae_like", report_nae_like);
		}
		if (StringUtils.isNotBlank(subcomp_name_like)) {
			entity.getMap().put("subcomp_name_like", changeToUTF8(subcomp_name_like));
			dynaBean.set("subcomp_name_like", subcomp_name_like);
		}
		if (StringUtils.isNotBlank(cust_type_id)) {
			entity.getMap().put("cust_type_id", cust_type_id);
			dynaBean.set("cust_type_id", cust_type_id);

		}

		Long recordCount = super.getFacade().getKonkaMobileCustVisitService().getKonkaMobileCustVisitAndDetailCount(
		        entity);
		entity.getRow().setFirst(0);
		entity.getRow().setCount(recordCount.intValue());
		List<KonkaMobileCustVisit> entityList1 = super.getFacade().getKonkaMobileCustVisitService()
		        .getKonkaMobileCustVisitAndDetailPaginatedList(entity);
		request.setAttribute("allList", entityList1);
		return mapping.findForward("view");
	}

	public ActionForward getCategoryType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		// 转换为json数据
		// 网点类型
		KonkaCategory kc = new KonkaCategory();
		List<HashMap> entityList = super.getFacade().getKonkaCategoryService().getKonkaCategoryGroupByCCommNew(kc);

		List list = new ArrayList();
		if (entityList.size() == 1) {
			list = entityList;
		} else {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("PAR_INDEX", "");
			m.put("C_COMM", "-请选择-");
			list.add(m);
			list.addAll(entityList);
		}

		JSONArray jsonArray = JSONArray.fromObject(list);
		logger.info("json----->" + list);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString());
		out.flush();
		out.close();
		return null;
	}

	public ActionForward getR3Cust(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			return null;
		}
		List<KonkaR3Shop> custList = getcust(userInfo, 1);
		StringBuffer json = new StringBuffer("[");
		json.append("{\"CUSTOMER_CODE\":\"\"").append(",").append("\"CUSTOMER_NAME\":").append("\"-请选择-\"}");
		StringBuffer jsb = new StringBuffer();
		if (custList.size() > 0) {
			for (KonkaR3Shop konkaR3Shop : custList) {
				jsb.append(",{\"CUSTOMER_CODE\":\"").append((String) konkaR3Shop.getMap().get("customer_code")).append(
				        "\",");
				jsb.append("\"CUSTOMER_NAME\":\"").append((String) konkaR3Shop.getMap().get("customer_name")).append(
				        "\"}");
			}
		}
		String listStr = StringUtils.removeEnd(jsb.toString(), ",");
		json.append(listStr).append("]");

		logger.info("json----->" + json);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
		return null;
	}

	public ActionForward getR3Shop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			return null;
		}
		List<KonkaR3Store> shopList = getShop(userInfo, 1);
		StringBuffer json = new StringBuffer("[");
		json.append("{\"STORE_ID\":\"\"").append(",").append("\"STORE_NAME\":").append("\"-请选择-\"}");
		StringBuffer jsb = new StringBuffer();
		if (shopList.size() > 0) {
			for (KonkaR3Store konkaR3Store : shopList) {
				jsb.append(",{\"STORE_ID\":").append(konkaR3Store.getStore_id()).append(",");
				jsb.append("\"STORE_NAME\":\"").append(konkaR3Store.getStore_name()).append("\"}");
			}
		}
		String listStr = StringUtils.removeEnd(jsb.toString(), ",");
		json.append(listStr).append("]");
		logger.info("json----->" + json);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
		return null;
	}

	public ActionForward getVisitType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			return null;
		}
		BaseVisitType baseVisitType = new BaseVisitType();
		baseVisitType.setReport_type(1);
		baseVisitType.setState(0);
		List<BaseVisitType> visitTypeList = super.getFacade().getBaseVisitTypeService()
		        .getBaseVisitTypeByReportTypeList(baseVisitType);

		StringBuffer json = new StringBuffer("[");
		StringBuffer jsb = new StringBuffer();
		if (visitTypeList.size() > 0) {
			for (BaseVisitType bt : visitTypeList) {
				jsb.append("{\"VISIT_TYPE_ID\":").append(bt.getVisit_type_id()).append(",");
				jsb.append("\"VISIT_TYPE_NAME\":\"").append(bt.getVisit_type_name()).append("\"},");
			}
		}
		String listStr = StringUtils.removeEnd(jsb.toString(), ",");
		json.append(listStr).append("]");
		logger.info("json----->" + json);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
		return null;
	}

	public ActionForward addInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		logger.info("i coming------------------->");
		DynaBean dynaBean = (DynaBean) form;
		StringBuffer json = new StringBuffer("{");
		String add_date = DateFormatUtils.format(new Date(), "yyyy年MM月dd日");
		json.append("\"add_date\":\"" + add_date + "\"");
		json.append("}");

		logger.info("josn---->{}" + json);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
		return null;
	}

	public ActionForward easyuiEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		logger.info("i coming------------------->");
		DynaBean dynaBean = (DynaBean) form;
		StringBuffer json = new StringBuffer("{");
		// json.append("\"success\":");
		KonkaMobileCustVisit entity = new KonkaMobileCustVisit();
		String id = (String) dynaBean.get("id");
		logger.info("id----------->" + id);
		entity.setVisit_id(Long.valueOf(id));
		entity = super.getFacade().getKonkaMobileCustVisitService().getKonkaMobileCustVisit(entity);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		if (entity != null) {
			json.append("\"add_date\":\"" + sf.format(entity.getAdd_date()) + "\",");
			String _begin_date = "";
			if (null != entity.getBegin_date()) {
				_begin_date = sf.format(entity.getBegin_date());
			}
			json.append("\"_begin_date\":\"" + _begin_date + "\",");

			String end_time = "";
			if (null != entity.getEnd_date()) {
				end_time = sf.format(entity.getEnd_date());
			}
			json.append("\"_end_date\":\"" + end_time + "\",");
			// 客户信息
			KonkaMobileCustVisitDetail konkaMobileCustVisitDetail = new KonkaMobileCustVisitDetail();
			konkaMobileCustVisitDetail.setVisit_id(Long.valueOf(id));
			konkaMobileCustVisitDetail = super.getFacade().getKonkaMobileCustVisitDetailService()
			        .getKonkaMobileCustVisitDetail(konkaMobileCustVisitDetail);
			String r3_code = "";
			if (konkaMobileCustVisitDetail.getR3_code() != null) {
				r3_code = konkaMobileCustVisitDetail.getR3_code();
			}
			String shop_id = "";
			if (konkaMobileCustVisitDetail.getShop_id() != null) {
				shop_id = konkaMobileCustVisitDetail.getShop_id().toString();
			}
			json.append("\"r3_code\":\"" + r3_code + "\",");
			json.append("\"shop_id\":\"" + shop_id + "\",");

			Attachment a = new Attachment();
			a.setLink_tab("KONKA_MOBILE_CUST_VISIT");
			a.setLink_id(Long.valueOf(id));
			Short isdel = new Short("0");
			a.setDel_mark(isdel);
			List<Attachment> attachmentList = super.getFacade().getAttachmentService().getAttachmentList(a);

			String ctx = getCtxPath(request);
			String att = "";
			if (null != attachmentList) {
				for (Attachment attachment : attachmentList) {
					att += "<span>" + "<a href=\'" + ctx + "/" + attachment.getSave_path() + "\' target=\'_blank\'>"
					        + attachment.getFile_name() + "</a>" + "<span id='" + attachment.getId() + "'"
					        + " onclick=" + "'del(" + attachment.getId() + ")'>" + "删除" + "</span>" + "</br></span>";
				}

			}
			json.append("\"att\":\"" + att + "\",");
			KonkaMobileCustVisitType konkaMobileCustVisitType = new KonkaMobileCustVisitType();
			konkaMobileCustVisitType.setVisit_id(Long.valueOf(id));
			List<KonkaMobileCustVisitType> konkaMobileCustVisitTypeList = super.getFacade()
			        .getKonkaMobileCustVisitTypeService().getKonkaMobileCustVisitTypeList(konkaMobileCustVisitType);
			List<String> visit_type_id = new ArrayList<String>();
			for (KonkaMobileCustVisitType kk : konkaMobileCustVisitTypeList) {
				visit_type_id.add(kk.getVisit_type_id().toString());
			}
			String visit_type_ids = StringUtils.join(visit_type_id, ",");

			json.append("\"visit_type_ids\":\"" + visit_type_ids + "\",");
			String feed_list = "";
			if (null != entity.getFeed_list()) {
				feed_list = entity.getFeed_list();
			}
			json.append("\"feed_list\":\"" + feed_list + "\",");
			String visit_desc = "";
			if (null != entity.getVisit_desc()) {
				visit_desc = entity.getVisit_desc();
			}
			json.append("\"visit_desc\":\"" + visit_desc + "\",");
			String remark = "";
			if (null != entity.getRemark()) {
				remark = entity.getRemark();
			}
			json.append("\"remark\":\"" + remark + "\",");

			json.append("\"domestic_ranking\":\"" + entity.getDomestic_ranking() + "\",");
			String consumer_sales = "";
			if (null != entity.getConsumer_sales()) {
				consumer_sales = entity.getConsumer_sales().toString();
			}

			json.append("\"consumer_sales\":\"" + consumer_sales + "\",");

			String retail_sales = "";
			if (null != entity.getRetail_sales()) {
				retail_sales = entity.getRetail_sales().toString();
			}
			json.append("\"retail_sales\":\"" + retail_sales + "\",");
			String consumer_name = "";
			if (null != entity.getConsumer_name()) {
				consumer_name = entity.getConsumer_name();
			}

			json.append("\"consumer_name\":\"" + consumer_name + "\",");
			String consumer_phone = "";
			if (null != entity.getConsumer_phone()) {
				consumer_phone = entity.getConsumer_phone();
			}
			json.append("\"consumer_phone\":\"" + consumer_phone + "\",");
			json.append("\"state\":\"" + entity.getState() + "\"");
		}
		json.append("}");

		logger.info("josn---->{}" + json);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
		return null;
	}

	public ActionForward easyuiView(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		logger.info("i coming------------------->");
		DynaBean dynaBean = (DynaBean) form;
		StringBuffer json = new StringBuffer("{");
		// json.append("\"success\":");
		KonkaMobileCustVisit entity = new KonkaMobileCustVisit();
		String id = (String) dynaBean.get("id");
		logger.info("id----------->" + id);
		entity.setVisit_id(Long.valueOf(id));
		entity = super.getFacade().getKonkaMobileCustVisitService().getKonkaMobileCustVisit(entity);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		if (entity != null) {
			json.append("\"add_date\":\"" + sf.format(entity.getAdd_date()) + "\",");
			String _begin_date = "";
			if (null != entity.getBegin_date()) {
				_begin_date = sf.format(entity.getBegin_date());
			}
			json.append("\"_begin_date\":\"" + _begin_date + "\",");

			String end_time = "";
			if (null != entity.getEnd_date()) {
				end_time = sf.format(entity.getEnd_date());
			}
			json.append("\"_end_date\":\"" + end_time + "\",");
			// 客户信息
			KonkaMobileCustVisitDetail konkaMobileCustVisitDetail = new KonkaMobileCustVisitDetail();
			konkaMobileCustVisitDetail.setVisit_id(Long.valueOf(id));
			konkaMobileCustVisitDetail = super.getFacade().getKonkaMobileCustVisitDetailService()
			        .getKonkaMobileCustVisitDetail(konkaMobileCustVisitDetail);
			String r3_code = "";
			if (konkaMobileCustVisitDetail.getR3_code() != null) {
				r3_code = konkaMobileCustVisitDetail.getR3_code();
			}
			String shop_id = "";
			if (konkaMobileCustVisitDetail.getShop_id() != null) {
				shop_id = konkaMobileCustVisitDetail.getShop_id().toString();
			}
			json.append("\"r3_code\":\"" + r3_code + "\",");
			json.append("\"shop_id\":\"" + shop_id + "\",");

			Attachment a = new Attachment();
			a.setLink_tab("KONKA_MOBILE_CUST_VISIT");
			a.setLink_id(Long.valueOf(id));
			Short isdel = new Short("0");
			a.setDel_mark(isdel);
			List<Attachment> attachmentList = super.getFacade().getAttachmentService().getAttachmentList(a);

			String ctx = getCtxPath(request);
			String att = "";
			if (null != attachmentList) {
				for (Attachment attachment : attachmentList) {
					att += "<span>" + "<a href=\'" + ctx + "/" + attachment.getSave_path() + "\' target=\'_blank\'>"
					        + attachment.getFile_name() + "</a>" + "</br></span>";
				}

			}
			json.append("\"att\":\"" + att + "\",");
			KonkaMobileCustVisitType konkaMobileCustVisitType = new KonkaMobileCustVisitType();
			konkaMobileCustVisitType.setVisit_id(Long.valueOf(id));
			List<KonkaMobileCustVisitType> konkaMobileCustVisitTypeList = super.getFacade()
			        .getKonkaMobileCustVisitTypeService().getKonkaMobileCustVisitTypeList(konkaMobileCustVisitType);
			List<String> visit_type_id = new ArrayList<String>();
			for (KonkaMobileCustVisitType kk : konkaMobileCustVisitTypeList) {
				visit_type_id.add(kk.getVisit_type_id().toString());
			}
			String visit_type_ids = StringUtils.join(visit_type_id, ",");

			json.append("\"visit_type_ids\":\"" + visit_type_ids + "\",");
			String feed_list = "";
			if (null != entity.getFeed_list()) {
				feed_list = entity.getFeed_list();
			}
			json.append("\"feed_list\":\"" + feed_list + "\",");
			String visit_desc = "";
			if (null != entity.getVisit_desc()) {
				visit_desc = entity.getVisit_desc();
			}
			json.append("\"visit_desc\":\"" + visit_desc + "\",");
			String remark = "";
			if (null != entity.getRemark()) {
				remark = entity.getRemark();
			}
			json.append("\"remark\":\"" + remark + "\",");

			json.append("\"domestic_ranking\":\"" + entity.getDomestic_ranking() + "\",");
			String consumer_sales = "";
			if (null != entity.getConsumer_sales()) {
				consumer_sales = entity.getConsumer_sales().toString();
			}

			json.append("\"consumer_sales\":\"" + consumer_sales + "\",");

			String retail_sales = "";
			if (null != entity.getRetail_sales()) {
				retail_sales = entity.getRetail_sales().toString();
			}
			json.append("\"retail_sales\":\"" + retail_sales + "\",");
			String consumer_name = "";
			if (null != entity.getConsumer_name()) {
				consumer_name = entity.getConsumer_name();
			}

			json.append("\"consumer_name\":\"" + consumer_name + "\",");
			String consumer_phone = "";
			if (null != entity.getConsumer_phone()) {
				consumer_phone = entity.getConsumer_phone();
			}
			json.append("\"consumer_phone\":\"" + consumer_phone + "\",");
			json.append("\"state\":\"" + entity.getState() + "\"");
		}
		json.append("}");

		logger.info("josn---->{}" + json);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
		return null;
	}

	public ActionForward easyuiSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		logger.info("i coming------------------->");
		DynaBean dynaBean = (DynaBean) form;

		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			super.renderHtml(response, "登录超时");
			return null;
		}
		StringBuffer json = new StringBuffer("{");
		json.append("\"rs\":");
		String visit_id = (String) dynaBean.get("visit_id");

		String report_type = (String) dynaBean.get("report_type");// 拜访类型

		String mod_id = (String) dynaBean.get("mod_id");// 拜访开始时间
		String is_del = (String) dynaBean.get("is_del");// 拜访开始时间
		String begin_date = (String) dynaBean.get("_begin_date");// 拜访开始时间
		String end_date = (String) dynaBean.get("_end_date");// 拜访结束时间
		String r3_code = (String) dynaBean.get("r3_code");// 拜访客户编码
		String shop_id = (String) dynaBean.get("shop_id");// 拜访门店id
		String feed_list = (String) dynaBean.get("feed_list");// 反馈内容
		String visit_desc = (String) dynaBean.get("visit_desc");// 拜访内容(上报类型)
		String state = (String) dynaBean.get("state");// 拜访内容(上报类型)
		String[] visit_type_id = request.getParameterValues("visit_type_id");// 拜访类型
		String visit_per_count = (String) dynaBean.get("visit_per_count");// 拜访内容(上报类型)
		String cust_id = (String) dynaBean.get("cust_id");// 开拓客户上报客户id
		String cust_name = (String) dynaBean.get("cust_name");// 开拓客户上报客户名称

		logger.info("begin_date--->" + begin_date);
		logger.info("end_date--->" + end_date);

		if (StringUtils.isBlank(report_type)) {
			report_type = "1";
		}
		// 根据当前用户找到分公司
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(userInfo.getDept_id());
		konkaDept.getMap().put("dept_type_eq", 3);
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept);

		logger.info("visit_id------------>" + visit_id);
		KonkaMobileCustVisit konkaMobileCustVisit = new KonkaMobileCustVisit();
		super.copyProperties(konkaMobileCustVisit, form);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		if (StringUtils.isBlank(visit_id)) {
			konkaMobileCustVisit.setAdd_date(new Date());// 添加时间
		}
		if (StringUtils.isNotBlank(begin_date)) {
			konkaMobileCustVisit.setBegin_date(df.parse(begin_date));// 添加时间
		}
		if (StringUtils.isNotBlank(end_date)) {
			konkaMobileCustVisit.setEnd_date(df.parse(end_date));// 添加时间
		}
		if (StringUtils.isNotBlank(state)) {
			konkaMobileCustVisit.setState(Integer.valueOf(state));
		}
		if (StringUtils.isNotBlank(report_type)) {
			konkaMobileCustVisit.setReport_type(Integer.parseInt(report_type));// 上报类型
		} else {
			konkaMobileCustVisit.setReport_type(1);// 上报类型
		}
		if (null != userInfo && null != userInfo.getId()) {
			konkaMobileCustVisit.setAdd_user_id(userInfo.getId());// 添加人userid
		}
		if (null != userInfo && null != userInfo.getJob_id()) {
			konkaMobileCustVisit.setYwy_job_id(userInfo.getJob_id());// 业务员jobId
		}
		if (null != userInfo && null != userInfo.getDept_id()) {
			konkaMobileCustVisit.setDept_id(userInfo.getDept_id());// 添加人单位id
		}
		if (null != userInfo && null != userInfo.getReal_name()) {
			konkaMobileCustVisit.setReport_nae(userInfo.getReal_name());// 上报人姓名
		}
		if (StringUtils.isNotBlank(visit_id)) {
			konkaMobileCustVisit.setVisit_id(Long.valueOf(visit_id));
		}
		if (null != konkaDept && null != konkaDept.getDept_id()) {
			konkaMobileCustVisit.setSubcomp_id(konkaDept.getDept_id());// 分公司id
		}
		if (StringUtils.isBlank(visit_per_count)) {
			konkaMobileCustVisit.setVisit_per_count(1L);// 拜访人数
		} else {
			konkaMobileCustVisit.setVisit_per_count(Long.valueOf(visit_per_count));// 拜访人数
		}
		konkaMobileCustVisit.setData_source(2);// 数据来源
		// 数据处理 存储客户信息的表
		if (StringUtils.isNotBlank(report_type) && "3".equals(report_type) && StringUtils.isNotBlank(cust_id)) {
			KonkaMobileCustVisitDetail konkaMobileCustVisitDetail = new KonkaMobileCustVisitDetail();
			konkaMobileCustVisitDetail.setR3_code(cust_id);
			konkaMobileCustVisitDetail.setCustomer_name(StringUtils.isBlank(cust_name) ? "未知客户" : cust_name);
			konkaMobileCustVisit.setKonkaMobileCustVisitDetail(konkaMobileCustVisitDetail);
		}
		if (StringUtils.isNotBlank(report_type) && ("1".equals(report_type) || "2".equals(report_type))) {
			if (StringUtils.isNotBlank(r3_code) || StringUtils.isNotBlank(shop_id)) {
				KonkaMobileCustVisitDetail konkaMobileCustVisitDetail = new KonkaMobileCustVisitDetail();

				KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
				KonkaR3Store konkaR3Store = new KonkaR3Store();
				Map entityMap = new HashMap();
				if (StringUtils.isNotBlank(r3_code)) {
					konkaR3Shop.setR3_code(r3_code);
					entityMap = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopOrJbasePartner(konkaR3Shop);
					if (null == entityMap || null == entityMap.get("CUSTOMER_NAME")
					        || "".equals(entityMap.get("CUSTOMER_NAME"))) {
						konkaR3Shop.setCustomer_name("未知客户");
					}
					konkaMobileCustVisitDetail.setR3_code(r3_code);// 客户r3编码
					konkaMobileCustVisitDetail.setCustomer_name((String) entityMap.get("CUSTOMER_NAME"));// 客户名称

				}
				if (StringUtils.isNotBlank(shop_id)) {
					konkaR3Store.setStore_id(Long.valueOf(shop_id));
					konkaR3Store = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(konkaR3Store);
					if (null == konkaR3Store || null == konkaR3Store.getStore_name()
					        || "".equals(konkaR3Store.getStore_name())) {
						konkaR3Store.setStore_name("未知门店");
					}
					konkaMobileCustVisitDetail.setShop_id(Long.valueOf(shop_id));// 门店id
					konkaMobileCustVisitDetail.setShop_name(konkaR3Store.getStore_name());// 门店名称
				}
				konkaMobileCustVisit.setKonkaMobileCustVisitDetail(konkaMobileCustVisitDetail);
			}
		}
		// 数据处理 存储拜访细类的表
		List<KonkaMobileCustVisitType> konkaMobileCustVisitTypeList = new ArrayList<KonkaMobileCustVisitType>();
		if (null != visit_type_id) {
			for (String type_id : visit_type_id) {
				KonkaMobileCustVisitType konkaMobileCustVisitType = new KonkaMobileCustVisitType();
				BaseVisitType baseVisitType = new BaseVisitType();
				baseVisitType.setVisit_type_id(Long.valueOf(type_id));
				baseVisitType = super.getFacade().getBaseVisitTypeService().getBaseVisitType(baseVisitType);
				konkaMobileCustVisitType.setVisit_type_id(Integer.parseInt(type_id));
				konkaMobileCustVisitType.setVisit_type_name(baseVisitType.getVisit_type_name());
				konkaMobileCustVisitType.setAdd_date(new Date());
				konkaMobileCustVisitTypeList.add(konkaMobileCustVisitType);
			}
			konkaMobileCustVisit.setKonkaMobileCustVisitTypeList(konkaMobileCustVisitTypeList);
		}
		if ("2".equals(report_type) || "1".equals(report_type)) {
			// 拿到上传的图片附件
			// List<UploadFile> uploadFileList = super.uploadFile(form, true, 0,
			// new int[] { 240 });
			List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.YWT_PATH, true, 0,
					new int[] { 240 });
			List<Attachment> attachmentList = new ArrayList<Attachment>();
			Attachment attachment = null;
			if (null != uploadFileList && uploadFileList.size() > 0) {
				for (UploadFile uploadFile : uploadFileList) {
					attachment = new Attachment();
					attachment.setFile_name(uploadFile.getFileName());
					attachment.setFile_type(uploadFile.getContentType());
					attachment.setFile_size(new Integer(uploadFile.getFileSize()));
					attachment.setSave_path(uploadFile.getFileSavePath());
					attachment.setSave_name(uploadFile.getFileSaveName());
					attachment.setLink_tab("KONKA_MOBILE_CUST_VISIT");
					attachment.setFile_desc(uploadFile.getFormName());
					Short isdel = new Short("0");
					attachment.setDel_mark(isdel);
					attachmentList.add(attachment);
				}
				konkaMobileCustVisit.setAttachmentsList(attachmentList);
			}
		}

		if (StringUtils.isBlank(visit_id)) {
			Long i = super.getFacade().getKonkaMobileCustVisitService()
			        .createKonkaMobileCustVisit(konkaMobileCustVisit);
			if (i > 0) {
				json.append("0");
			} else {
				json.append("1");
				json.append(",\"errorMsg\":\"提交失败\"");
			}
		} else {
			int i = super.getFacade().getKonkaMobileCustVisitService().modifyKonkaMobileCustVisit(konkaMobileCustVisit);
			if (i > 0) {
				json.append("0");
			} else {
				json.append("1");
				json.append(",\"errorMsg\":\"提交失败\"");
			}
		}

		json.append("}");

		logger.info("josn---->{}" + json);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
		return null;
	}

	public ActionForward mapInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		logger.info("i coming------------------->");
		DynaBean dynaBean = (DynaBean) form;
		StringBuffer json = new StringBuffer("{");
		String id = (String) dynaBean.get("id");

		KonkaMobileCustVisitGps gps = new KonkaMobileCustVisitGps();
		gps.setLink_id(Long.valueOf(id));
		List<KonkaMobileCustVisitGps> gpsList = super.getFacade().getKonkaMobileCustVisitGpsService()
		        .getKonkaMobileCustVisitGpsList(gps);
		if (null != gpsList && gpsList.size() > 0) {
			gps = gpsList.get(0);
		}
		String lng = "";
		String lat = "";
		if (null != gps.getPosition_x() && null != gps.getPosition_y()) {
			lng = gps.getPosition_x().toString();
			lat = gps.getPosition_y().toString();
			json.append("\"state\":\"" + 0 + "\",");
		} else {
			json.append("\"state\":\"" + 1 + "\",");
		}

		json.append("\"lng\":\"" + lng + "\",");
		json.append("\"lat\":\"" + lat + "\"");
		json.append("}");

		logger.info("josn---->{}" + json);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
		return null;
	}

	public ActionForward deleteFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");

		if (StringUtils.isNotBlank(id) && GenericValidator.isLong(id)) {
			Attachment entity = new Attachment();
			entity.setId(new Long(id));
			super.getFacade().getAttachmentService().removeAttachment(entity);
		}

		super.renderText(response, "success");
		return null;
	}

	public ActionForward easyuiDel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		logger.info("i coming------------------->");
		DynaBean dynaBean = (DynaBean) form;
		StringBuffer json = new StringBuffer("{");
		json.append("\"success\":");
		KonkaMobileCustVisit entity = new KonkaMobileCustVisit();
		String id = (String) dynaBean.get("id");
		logger.info("id----------->" + id);

		String[] pks = id.split(",");

		if (StringUtils.isNotBlank(id)) {
			entity.getMap().put("pks", pks);
			entity.setIs_del(1);
		} else {
			String msg = "删除失败！";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		int i = super.getFacade().getKonkaMobileCustVisitService().modifyKonkaMobileCustVisit(entity);
		if (i > 0) {
			json.append(true);
		} else {
			json.append(false);
			json.append(",\"errorMsg\":\"删除失败！\"");
		}
		json.append("}");

		logger.info("josn---->{}" + json);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
		return null;
	}

	private static int day(int year, int month) {
		int maxDay = 0;
		int day = 1;
		/**
		 * 与其他语言环境敏感类一样，Calendar 提供了一个类方法 getInstance， 以获得此类型的一个通用的对象。Calendar 的
		 * getInstance 方法返回一 个 Calendar 对象，其日历字段已由当前日期和时间初始化：
		 */
		Calendar calendar = Calendar.getInstance();
		/**
		 * 实例化日历各个字段,这里的day为实例化使用
		 */
		calendar.set(year, month - 1, day);
		/**
		 * Calendar.Date:表示一个月中的某天 calendar.getActualMaximum(int
		 * field):返回指定日历字段可能拥有的最大值
		 */
		maxDay = calendar.getActualMaximum(Calendar.DATE);
		return maxDay;
	}

	@SuppressWarnings("unused")
	private List<KonkaR3Shop> getcust(PeProdUser userInfo, Integer report_type) {
		KonkaR3Shop entity = new KonkaR3Shop();
		if (null != report_type) {
			if (report_type == 1) {

				entity.getMap().put("cust_type", new String[] { "1", "2" });
			}
			if (report_type == 2) {
				entity.getMap().put("cust_type", new String[] { "3", "4" });
			}
		}
		boolean flag = false;
		boolean fgsgly = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
			if (peRoleUser.getRole_id().equals(new Long(30))) {
				fgsgly = true;
			}
		}
		// if (flag) {// 是业务员
		// entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
		// entity.getMap().put("fgs_dept_value", "");
		// }else

		{

			Long _dept_id = 0L;
			int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
			logger.info("Max level : {}", max_dlevel);
			switch (max_dlevel) {
			case 9:
				// 集团及以下部门可见
				_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
				// break;
				return null;
			case 8:
				// 分公司及以下部门可见
				if (!fgsgly) {
					KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
					if (null != dept_fgs && null != dept_fgs.getDept_id()) {
						_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
						entity.getMap().put("dept_id_start", _dept_id);
						// entity.getMap().put("fgs_dept_value", _dept_id);
					}
					break;
				} else {
					return null;
				}
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
		}
		entity.setIs_del(0L);// 未被停用的
		List<KonkaR3Shop> entityList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopForCustVisit(entity);

		return entityList;
	}

	@SuppressWarnings("unused")
	private List<KonkaR3Store> getShop(PeProdUser userInfo, Integer report_type) {
		KonkaR3Store entity = new KonkaR3Store();
		if (null != report_type) {
			if (report_type == 1) {

				entity.getMap().put("cust_type", new String[] { "1", "2" });
			}
			if (report_type == 2) {
				entity.getMap().put("cust_type", new String[] { "3", "4" });
			}
		}
		boolean flag = false;
		boolean fgsgly = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
			if (peRoleUser.getRole_id().equals(new Long(30))) {
				fgsgly = true;
			}
		}
		// if (flag) {// 是业务员
		// entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
		// entity.getMap().put("fgs_dept_value", "");
		// }else
		{

			Long _dept_id = 0L;
			int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
			logger.info("Max level : {}", max_dlevel);
			switch (max_dlevel) {
			case 9:
				// 集团及以下部门可见
				_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
				// break;
				return null;
			case 8:
				// 分公司及以下部门可见
				if (!fgsgly) {
					KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
					if (null != dept_fgs && null != dept_fgs.getDept_id()) {
						_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
						entity.getMap().put("dept_id_start", _dept_id);
						// entity.getMap().put("fgs_dept_value", _dept_id);
					}
					break;
				} else {
					return null;
				}
				// return null;
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
		}
		entity.setIs_del(0);// 未被停用的
		List<KonkaR3Store> entityList = super.getFacade().getKonkaR3StoreService().getKonkaR3StoreForCustVisit(entity);

		return entityList;
	}
}
