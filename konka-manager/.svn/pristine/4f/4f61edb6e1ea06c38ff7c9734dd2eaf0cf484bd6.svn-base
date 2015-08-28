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
import com.ebiz.mmt.domain.KonkaR3ShopDev;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.KonkaYwHzTj;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.mmt.web.util.StringHelper;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class KonkaMobileCustVisitAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String report_type_tj = (String) dynaBean.get("report_type_tj");
		dynaBean.set("is_del", "0");
		if (StringUtils.isNotBlank(report_type_tj)) {
			dynaBean.set("report_type_tj", ""+report_type_tj);
		} else {
			dynaBean.set("report_type_tj", "5");
		}
		return list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String mod_id = (String) dynaBean.get("mod_id");
		String report_type_tj = (String) dynaBean.get("report_type_tj");
		String begin_date = (String) dynaBean.get("_begin_date");
		String end_date = (String) dynaBean.get("_end_date");
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String shop_id = (String) dynaBean.get("shop_id");
		String shop_name_like = (String) dynaBean.get("shop_name_like");
		String excel_all = (String) dynaBean.get("excel_all");//
		String report_nae_like = (String) dynaBean.get("report_nae_like");// 上报人
		// 一级客户类型
		String customer_type1 = (String) dynaBean.get("v_customer_type1");
		// 二级客户类型
		String customer_type2 = (String) dynaBean.get("v_customer_type2");
		
		String isfirst = (String) dynaBean.get("isfirst");// 是否第一次
		String visit_type_name_like = (String) dynaBean.get("visit_type_name_like");// 拜访类型
		
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		PeRoleUser roleUser=new PeRoleUser();
		roleUser.setUser_id(user.getId());
        List<PeRoleUser> userroles=super.getFacade().getPeRoleUserService().getPeRoleUserList(roleUser);
        for (PeRoleUser peRoleUser : userroles) {
			if (peRoleUser.getRole_id()==34||peRoleUser.getRole_id()==30) {
				dynaBean.set("haveRole", true);
			}
			if (peRoleUser.getRole_id()>=1&&peRoleUser.getRole_id()<=30) {
				dynaBean.set("haveRole", true);
			}
		}
		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(user.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}
		
		KonkaMobileCustVisit entity = new KonkaMobileCustVisit();
		super.copyProperties(entity, form);
		/*if (flag) {// 如果是业务员，只能看到自己的拜访记录
			entity.setAdd_user_id(user.getId());
		} else {*/

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

		//}
		if (StringUtils.isNotBlank(customer_type2)) {
			entity.getMap().put("cus_type2", customer_type2);
			dynaBean.set("v_customer_type2", customer_type2);
		} else {
			if (StringUtils.isNotBlank(customer_type1)) {
				entity.getMap().put("cus_type1", customer_type1);
				dynaBean.set("v_customer_type1", customer_type1);
			}
		}
		if (StringUtils.isNotBlank(report_type_tj)&&"1".equals(report_type_tj)) {//正常
			entity.setReport_type(1);
			dynaBean.set("report_type_tj", "1");
		}else if (StringUtils.isNotBlank(report_type_tj)&&"2".equals(report_type_tj)) {//重拾
			entity.setReport_type(2);
			dynaBean.set("report_type_tj", "2");
		}else if (StringUtils.isNotBlank(report_type_tj)&&"5".equals(report_type_tj)) {//正常加重拾
			entity.getMap().put("report_type_tj", "5");
			dynaBean.set("report_type_tj", "5");
		}else if (StringUtils.isNotBlank(report_type_tj)&&"3".equals(report_type_tj)) {//新开拓客户日志上报
			entity.setReport_type(3);
			dynaBean.set("report_type_tj", "3");
			dynaBean.set("report_type", "3");
		}else if (StringUtils.isNotBlank(report_type_tj)&&"4".equals(report_type_tj)) {//事物日志上报
			entity.setReport_type(4);
			dynaBean.set("report_type_tj", "4");
			dynaBean.set("report_type", "4");
		}
		
		if (StringUtils.isNotBlank(isfirst)) {
			dynaBean.set("isfirst", isfirst);
		}
		String begindate = "";
		String enddate = "";
		if (StringUtils.isEmpty(isfirst)) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
			Calendar calendarbegin = Calendar.getInstance();
			calendarbegin.set(Calendar.DAY_OF_MONTH, calendarbegin.getActualMinimum(Calendar.DAY_OF_MONTH));
			begindate = df.format(calendarbegin.getTime());
			entity.getMap().put("time_start", begindate + " 00:00:00");
			dynaBean.set("_begin_date", begindate);
			Calendar calendarend = Calendar.getInstance();
			// calendar.set(Calendar.DAY_OF_MONTH,
			// calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
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

		/*
		 * if (StringUtils.isNotBlank(begin_date)) { String
		 * begindate=begin_date+" 00:00:00"; entity.getMap().put("time_start",
		 * begindate); dynaBean.set("_time_start", begin_date); } if
		 * (StringUtils.isNotBlank(end_date)) { String
		 * enddate=end_date+" 23:59:59"; entity.getMap().put("time_end",
		 * enddate); dynaBean.set("_time_end", end_date); }
		 */
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
		if (StringUtils.isNotBlank(visit_type_name_like)) {
			entity.getMap().put("visit_type_name_like", visit_type_name_like);
			dynaBean.set("visit_type_name_like", visit_type_name_like);
		}
		
		List<KonkaMobileCustVisit> entityList = null;
		if ("2".equals(report_type_tj) || "1".equals(report_type_tj)||"5".equals(report_type_tj)) {

			Long recordCount = super.getFacade().getKonkaMobileCustVisitService()
			        .getKonkaMobileCustVisitAndDetailCount(entity);
			pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
			entity.getRow().setFirst(pager.getFirstRow());
			entity.getRow().setCount(pager.getRowCount());

			String filename = "";
			Integer fileIndex = Integer.parseInt(report_type_tj);
			switch (fileIndex) {
			case 1:
				filename = "正常客户导出";
				break;
			case 2:
				filename = "重拾客户导出";
				break;
			case 5:
				filename = "客户拜访";
				break;	
			default:
				break;
			}
			// 导出
			if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
				if (recordCount.intValue() > 20000) {
					renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
					        + "！');history.back();");
					return null;
				}
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment;filename="
				        + EncryptUtils.encodingFileName(filename) + ".xls");
				entity.getRow().setFirst(0);
				entity.getRow().setCount(recordCount.intValue());

				List<KonkaMobileCustVisit> entityList1 = super.getFacade().getKonkaMobileCustVisitService()
				        .getKonkaMobileCustVisitAndDetailPaginatedList(entity);
				request.setAttribute("allList", entityList1);
				Integer count = entityList1.size();
				if (count != null && count > 0) {
					return new ActionForward("/admin/KonkaMobileCustVisit/listReport.jsp");
				} else {
					// super.renderJavaScript(response,
					// "alert('没有数据要导出！');history.back();");
					return null;
				}
			}

			entityList = super.getFacade().getKonkaMobileCustVisitService()
			        .getKonkaMobileCustVisitAndDetailPaginatedList(entity);
			request.setAttribute("mod_id", mod_id);

		} else {
			Long recordCount = super.getFacade().getKonkaMobileCustVisitService()
			        .getKonkaMobileCustVisitLBCount(entity);
			pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
			entity.getRow().setFirst(pager.getFirstRow());
			entity.getRow().setCount(pager.getRowCount());
			String filename = "";
			Integer fileIndex = Integer.parseInt(report_type_tj);
			switch (fileIndex) {
			case 3:
				filename = "开拓日志上报导出";
				break;
			case 4:
				filename = "事务日志上报导出";
				break;
			default:
				break;
			}
			// 导出
			if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
				if (recordCount.intValue() > 20000) {
					renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
					        + "！');history.back();");
					return null;
				}
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment;filename="
				        + EncryptUtils.encodingFileName(filename) + ".xls");
				entity.getRow().setFirst(0);
				entity.getRow().setCount(recordCount.intValue());

				List<KonkaMobileCustVisit> entityList1 = super.getFacade().getKonkaMobileCustVisitService()
				        .getKonkaMobileCustVisitPaginatedLBList(entity);
				request.setAttribute("allList", entityList1);
				Integer count = entityList1.size();
				if (count != null && count > 0) {
					return new ActionForward("/admin/KonkaMobileCustVisit/listReport.jsp");
				} else {
					// super.renderJavaScript(response,
					// "alert('没有数据要导出！');history.back();");
					return null;
				}
			}

			entityList = super.getFacade().getKonkaMobileCustVisitService().getKonkaMobileCustVisitPaginatedLBList(
			        entity);
		}
		request.setAttribute("mod_id", mod_id);
		request.setAttribute("entityList", entityList);
		// 拿分公司
		List<KonkaDept> deptList = super.getDeptInfoListWithOutLimit(mapping, form, request, response);
		request.setAttribute("deptList", deptList);
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		super.saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String report_type = (String) dynaBean.get("report_type");
		String report_type_tj = (String) dynaBean.get("report_type_tj");
		String is_del = (String) dynaBean.get("is_del");
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			return null;
		}
		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}
		if (!flag) {// 说明不是业务员，不给他进页面
			// super.renderHtml(response, "该用户（" + userInfo.getReal_name() +
			// "）不是业务员！");
			// return null;
		}
		if ("1".equals(report_type)||"2".equals(report_type)) {
			// 通过用户找客户
			List<KonkaR3Shop> custList = getcust(userInfo, Integer
					.parseInt(report_type));
			request.setAttribute("custList", custList);// 客户
			// 通过用户找终端
			List<KonkaR3Store> storeList = getShop(userInfo, Integer
					.parseInt(report_type));
			request.setAttribute("storeList", storeList);
		}else {
			KonkaR3ShopDev konkaR3ShopDev = new KonkaR3ShopDev();
			konkaR3ShopDev.setIs_del(0);
			List<KonkaR3ShopDev> konkaR3ShopDevList = new ArrayList<KonkaR3ShopDev>();
			// konkaR3ShopDevList=super.getFacade().getKonkaR3ShopDevService).getKonkaR3ShopDevList(konkaR3ShopDev);
			konkaR3ShopDev.getMap().put("deptId", userInfo.getDept_id());
			konkaR3ShopDevList = super.getFacade().getKonkaR3ShopDevService().getKtUserByUserIdList(konkaR3ShopDev);
			request.setAttribute("konkaR3ShopDevList", konkaR3ShopDevList);
		}
		request.setAttribute("report_type", report_type);
		request.setAttribute("report_type_tj", report_type_tj);//列表查询条件
		BaseVisitType baseVisitType = new BaseVisitType();
		baseVisitType.setReport_type(Integer.parseInt(report_type));
		baseVisitType.setState(0);
		List<BaseVisitType> visitTypeList = super.getFacade().getBaseVisitTypeService()
		        .getBaseVisitTypeByReportTypeList(baseVisitType);
		dynaBean.set("mod_id", mod_id);
		dynaBean.set("is_del", is_del);
		request.setAttribute("visitTypeList", visitTypeList);
		request.setAttribute("today", DateFormatUtils.format(new Date(), "yyyy年MM月dd日"));
		if ("2".equals(report_type) || "1".equals(report_type)) {
			return new ActionForward("/../manager/admin/KonkaMobileCustVisit/input.jsp");
		} else {
			return new ActionForward("/../manager/admin/KonkaMobileCustVisit/form.jsp");
		}
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (!super.isTokenValid(request, true)) {
			super.saveMessage(request, "errors.token");
			return mapping.findForward("list");
		}
		resetToken(request);
		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);
		super.saveToken(request);
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			super.renderHtml(response, "登录超时");
			return null;
		}

		String visit_id = (String) dynaBean.get("visit_id");// 拜访类型

		String report_type = (String) dynaBean.get("report_type");// 拜访类型
		String report_type_tj = (String) dynaBean.get("report_type_tj");// 拜访类型

		String mod_id = (String) dynaBean.get("mod_id");// 拜访开始时间
		String is_del = (String) dynaBean.get("is_del");// 拜访开始时间
		String begin_date = (String) dynaBean.get("begin_date");// 拜访开始时间
		String end_date = (String) dynaBean.get("end_date");// 拜访结束时间
		String r3_code_name = (String) dynaBean.get("r3_code_name");// 拜访客户编码
		String shop_id_name = (String) dynaBean.get("shop_id_name");// 拜访终端id
		String feed_list = (String) dynaBean.get("feed_list");// 反馈内容
		String visit_desc = (String) dynaBean.get("visit_desc");// 拜访内容(上报类型)
		String state = (String) dynaBean.get("state");// 拜访内容(上报类型)
		String[] visit_type_id = request.getParameterValues("visit_type_id");// 拜访类型
		String visit_per_count = (String) dynaBean.get("visit_per_count");// 拜访内容(上报类型)
		String cust_id_name = (String) dynaBean.get("cust_id_name");// 开拓客户上报客户id
		//String cust_name = (String) dynaBean.get("cust_name");// 开拓客户上报客户名称
		String data_source = (String) dynaBean.get("data_source");// 开拓客户上报客户id
		

		// 根据当前用户找到分公司
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(userInfo.getDept_id());
		konkaDept.getMap().put("dept_type_eq", 3);
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept);

		// 数据处理 --主表
		KonkaMobileCustVisit konkaMobileCustVisit = new KonkaMobileCustVisit();
		super.copyProperties(konkaMobileCustVisit, form);
		if (StringUtils.isBlank(visit_id)) {
			konkaMobileCustVisit.setAdd_date(new Date());// 添加时间
		}
		if (StringUtils.isNotBlank(state)) {
			konkaMobileCustVisit.setState(Integer.valueOf(state));
		}
		if (StringUtils.isNotBlank(report_type)) {
			konkaMobileCustVisit.setReport_type(Integer.parseInt(report_type));// 上报类型
		}
		
		if (StringUtils.isBlank(visit_id)) {
			if (null != userInfo && null != userInfo.getId()) {
				konkaMobileCustVisit.setAdd_user_id(userInfo.getId());// 添加人userid
			}
		}
		if (null != userInfo && null != userInfo.getJob_id()) {
			konkaMobileCustVisit.setYwy_job_id(userInfo.getJob_id());// 业务员jobId
		}
		if (null != userInfo && null != userInfo.getDept_id()) {
			konkaMobileCustVisit.setDept_id(userInfo.getDept_id());// 添加人单位id
		}
		if (StringUtils.isBlank(visit_id)) {
			if (null != userInfo && null != userInfo.getReal_name()) {
				konkaMobileCustVisit.setReport_nae(userInfo.getReal_name());// 上报人姓名
			}	
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
		if (StringUtils.isBlank(data_source)) {
			konkaMobileCustVisit.setData_source(2);// 数据来源
		}else{
			konkaMobileCustVisit.setData_source(Integer.valueOf(data_source));// 数据来源
		}
		// 数据处理 存储客户信息的表
		if (StringUtils.isNotBlank(report_type) && "3".equals(report_type) && StringUtils.isNotBlank(cust_id_name)) {
			String cust_id_name_temp[]=cust_id_name.split("#");
			KonkaMobileCustVisitDetail konkaMobileCustVisitDetail = new KonkaMobileCustVisitDetail();
			konkaMobileCustVisitDetail.setR3_code(cust_id_name_temp[0]);
			konkaMobileCustVisitDetail.setCustomer_name(cust_id_name_temp[1]);
			konkaMobileCustVisit.setKonkaMobileCustVisitDetail(konkaMobileCustVisitDetail);
		}
		if (StringUtils.isNotBlank(report_type) && ("1".equals(report_type) || "2".equals(report_type))) {
			if (StringUtils.isNotBlank(r3_code_name) || StringUtils.isNotBlank(shop_id_name)) {
				KonkaMobileCustVisitDetail konkaMobileCustVisitDetail = new KonkaMobileCustVisitDetail();

				/*KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
				KonkaR3Store konkaR3Store = new KonkaR3Store();
				Map entityMap = new HashMap();*/
				if (StringUtils.isNotBlank(r3_code_name)) {
					//konkaR3Shop.setR3_code(r3_code);
					/*entityMap = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopOrJbasePartner(konkaR3Shop);
					if (null == entityMap || null == entityMap.get("CUSTOMER_NAME")
					        || "".equals(entityMap.get("CUSTOMER_NAME"))) {
						konkaR3Shop.setCustomer_name("未知客户");
					}*/
					String r3_code_name_temp[]=r3_code_name.split("#");
					konkaMobileCustVisitDetail.setR3_code(r3_code_name_temp[0]);// 客户r3编码
					konkaMobileCustVisitDetail.setCustomer_name(r3_code_name_temp[1]);// 客户名称

				}
				if (StringUtils.isNotBlank(shop_id_name)) {
					/*konkaR3Store.setStore_id(Long.valueOf(shop_id));
					konkaR3Store = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(konkaR3Store);
					if (null == konkaR3Store || null == konkaR3Store.getStore_name()
					        || "".equals(konkaR3Store.getStore_name())) {
						konkaR3Store.setStore_name("未知终端");
					}*/
					String shop_id_name_temp[]=shop_id_name.split("#");
					String shop_id=shop_id_name_temp[0];
					konkaMobileCustVisitDetail.setShop_id(Long.parseLong(shop_id));// 终端id
					konkaMobileCustVisitDetail.setShop_name(shop_id_name_temp[1]);// 终端名称
					if (shop_id.endsWith("191919")) {
						konkaMobileCustVisitDetail.setCustomer_type(Long.valueOf(""+10));
					}
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
		if ("2".equals(report_type) ||"1".equals(report_type) || "4".equals(report_type)) {
			// 拿到上传的图片附件
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
		if (StringUtils.isNotBlank(visit_id)) {
			super.getFacade().getKonkaMobileCustVisitService().modifyKonkaMobileCustVisit(konkaMobileCustVisit);
		} else {
			//konkaMobileCustVisit.setFile_no(null);
			super.getFacade().getKonkaMobileCustVisitService().createKonkaMobileCustVisit(konkaMobileCustVisit);
		}
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&report_type=" + report_type);
		pathBuffer.append("&report_type_tj=" + report_type_tj);
		pathBuffer.append("&is_del=" + is_del);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(konkaMobileCustVisit.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward map(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String visit_id = (String) dynaBean.get("visit_id");
		if (!GenericValidator.isLong(visit_id)) {
			super.saveError(request, "errors.long", "visit_id");
			return this.list(mapping, form, request, response);
		}

		KonkaMobileCustVisitGps gps = new KonkaMobileCustVisitGps();
		gps.setLink_id(Long.valueOf(visit_id));
		List<KonkaMobileCustVisitGps> gpsList = super.getFacade().getKonkaMobileCustVisitGpsService()
		        .getKonkaMobileCustVisitGpsList(gps);
		if (null != gpsList && gpsList.size() > 0) {
			gps = gpsList.get(0);
		}

		if (null != gps.getPosition_x() && null != gps.getPosition_y()) {
			dynaBean.set("lng", gps.getPosition_x());
			dynaBean.set("lat", gps.getPosition_y());
		} else {// 给一个默认值
			dynaBean.set("lng", "116.397428");
			dynaBean.set("lat", "39.90923");
		}

		return new ActionForward("/../manager/admin/KonkaMobileCustVisit/map2.jsp");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);
		super.saveToken(request);
		String report_type = (String) dynaBean.get("report_type");
		String report_type_tj = (String) dynaBean.get("report_type_tj");
		String visit_id = (String) dynaBean.get("visit_id");
		if (StringUtils.isBlank(visit_id)) {
			super.renderHtml(response, "请选择一条记录修改!");
			return null;
		}
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			return null;
		}
		// 主表信息
		KonkaMobileCustVisit konkaMobileCustVisit = new KonkaMobileCustVisit();
		konkaMobileCustVisit.setVisit_id(Long.valueOf(visit_id));
		konkaMobileCustVisit = super.getFacade().getKonkaMobileCustVisitService().getKonkaMobileCustVisit(
		        konkaMobileCustVisit);

		if ("2".equals(report_type) || "1".equals(report_type)) {
			// 你选择的细类
			KonkaMobileCustVisitType konkaMobileCustVisitType = new KonkaMobileCustVisitType();
			konkaMobileCustVisitType.setVisit_id(Long.valueOf(visit_id));
			List<KonkaMobileCustVisitType> konkaMobileCustVisitTypeList = super.getFacade()
			        .getKonkaMobileCustVisitTypeService().getKonkaMobileCustVisitTypeList(konkaMobileCustVisitType);
			request.setAttribute("konkaMobileCustVisitTypeList", konkaMobileCustVisitTypeList);

			// 客户信息
			KonkaMobileCustVisitDetail konkaMobileCustVisitDetail = new KonkaMobileCustVisitDetail();
			konkaMobileCustVisitDetail.setVisit_id(Long.valueOf(visit_id));
			konkaMobileCustVisitDetail = super.getFacade().getKonkaMobileCustVisitDetailService()
			        .getKonkaMobileCustVisitDetail(konkaMobileCustVisitDetail);
			if (null != konkaMobileCustVisitDetail) {
				if (null != konkaMobileCustVisitDetail.getR3_code()) {
					request.setAttribute("r3_code_name", konkaMobileCustVisitDetail.getR3_code()+"#"+konkaMobileCustVisitDetail.getCustomer_name());
					request.setAttribute("r3_code",konkaMobileCustVisitDetail.getR3_code());
				}
				if (null != konkaMobileCustVisitDetail.getShop_id()) {
					request.setAttribute("shop_id_name", konkaMobileCustVisitDetail.getShop_id()+"#"+konkaMobileCustVisitDetail.getShop_name());
				}
			}
			// 拿到图片

			Attachment a = new Attachment();
			a.setLink_tab("KONKA_MOBILE_CUST_VISIT");
			a.setLink_id(Long.valueOf(visit_id));
			Short isdel = new Short("0");
			a.setDel_mark(isdel);
			List<Attachment> aList = super.getFacade().getAttachmentService().getAttachmentList(a);

			konkaMobileCustVisit.setAttachmentsList(aList);

			List<KonkaR3Shop> custList = getcust(userInfo, Integer.parseInt(report_type));
			request.setAttribute("custList", custList);// 客户

			// 通过用户找终端
			List<KonkaR3Store> storeList = getShop(userInfo, Integer.parseInt(report_type));

			BaseVisitType baseVisitType = new BaseVisitType();
			baseVisitType.setReport_type(Integer.parseInt(report_type));
			baseVisitType.setState(0);// 没有停用的
			List<BaseVisitType> visitTypeList = super.getFacade().getBaseVisitTypeService()
			        .getBaseVisitTypeByReportTypeList(baseVisitType);
			// 所有细类
			request.setAttribute("visitTypeList", visitTypeList);

			request.setAttribute("storeList", storeList);// 终端
		}
		if ("3".equals(report_type)) {
			// 客户信息
			KonkaMobileCustVisitDetail konkaMobileCustVisitDetail = new KonkaMobileCustVisitDetail();
			konkaMobileCustVisitDetail.setVisit_id(Long.valueOf(visit_id));
			konkaMobileCustVisitDetail = super.getFacade().getKonkaMobileCustVisitDetailService()
			        .getKonkaMobileCustVisitDetail(konkaMobileCustVisitDetail);
			if (null != konkaMobileCustVisitDetail) {
				if (null != konkaMobileCustVisitDetail.getR3_code()) {
					request.setAttribute("cust_id_name", konkaMobileCustVisitDetail.getR3_code()+"#"+konkaMobileCustVisitDetail.getCustomer_name());
				}
			}
			// 拿到图片
			Attachment a = new Attachment();
			a.setLink_tab("KONKA_MOBILE_CUST_VISIT");
			a.setLink_id(Long.valueOf(visit_id));
			Short isdel = new Short("0");
			a.setDel_mark(isdel);
			List<Attachment> aList = super.getFacade().getAttachmentService().getAttachmentList(a);

			konkaMobileCustVisit.setAttachmentsList(aList);

			//List<KonkaR3Shop> custList = getcust(userInfo, Integer.parseInt(report_type));
			//request.setAttribute("custList", custList);// 客户
			KonkaR3ShopDev konkaR3ShopDev = new KonkaR3ShopDev();
			//konkaR3ShopDev.setIs_del(0);
			List<KonkaR3ShopDev> konkaR3ShopDevList = new ArrayList<KonkaR3ShopDev>();
			// konkaR3ShopDevList=super.getFacade().getKonkaR3ShopDevService).getKonkaR3ShopDevList(konkaR3ShopDev);
			konkaR3ShopDev.getMap().put("deptId", userInfo.getDept_id());
			konkaR3ShopDevList = super.getFacade().getKonkaR3ShopDevService().getKtUserByUserIdList(konkaR3ShopDev);
			request.setAttribute("konkaR3ShopDevList", konkaR3ShopDevList);
		}
		
		
		
		
		if("4".equals(report_type)){
			// 拿到图片
			Attachment a = new Attachment();
			a.setLink_tab("KONKA_MOBILE_CUST_VISIT");
			a.setLink_id(Long.valueOf(visit_id));
			Short isdel = new Short("0");
			a.setDel_mark(isdel);
			List<Attachment> aList = super.getFacade().getAttachmentService().getAttachmentList(a);

			konkaMobileCustVisit.setAttachmentsList(aList);
		}
		konkaMobileCustVisit.setQueryString(super.serialize(request, "visit_id", "method"));
		super.copyProperties(form, konkaMobileCustVisit);

		request.setAttribute("report_type", report_type);// 上报类型
		request.setAttribute("report_type_tj", report_type_tj);//列表查询条件
		if ("2".equals(report_type) || "1".equals(report_type)) {
			return new ActionForward("/../manager/admin/KonkaMobileCustVisit/input.jsp");
		} else {
			return new ActionForward("/../manager/admin/KonkaMobileCustVisit/form.jsp");
		}

	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String visit_id = (String) dynaBean.get("visit_id");
		String report_type = (String) dynaBean.get("report_type");
		String report_type_tj = (String) dynaBean.get("report_type_tj");
		if (!GenericValidator.isLong(visit_id)) {
			super.saveError(request, "errors.long", "visit_id");
			return this.list(mapping, form, request, response);
		}
		KonkaMobileCustVisit entity = new KonkaMobileCustVisit();
		entity.setVisit_id(Long.valueOf(visit_id));
		entity.setIs_del(1);
		Integer number = super.getFacade().getKonkaMobileCustVisitService().modifyKonkaMobileCustVisit(entity);
		logger.info("-----" + number);
		entity.setQueryString(super.serialize(request, "visit_id", "method"));

		logger.info("++++++++++++++QueryString=" + super.encodeSerializedQueryString(entity.getQueryString()));
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("report_type=" + report_type);
		pathBuffer.append("&").append("report_type_tj=" + report_type_tj);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);
		super.saveToken(request);
		String report_type = (String) dynaBean.get("report_type");
		String report_type_tj = (String) dynaBean.get("report_type_tj");
		String visit_id = (String) dynaBean.get("visit_id");
		if (StringUtils.isBlank(visit_id)) {
			super.renderHtml(response, "请选择一条记录修改!");
			return null;
		}
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			return null;
		}
		// 主表信息
		KonkaMobileCustVisit konkaMobileCustVisit = new KonkaMobileCustVisit();
		konkaMobileCustVisit.setVisit_id(Long.valueOf(visit_id));
		konkaMobileCustVisit = super.getFacade().getKonkaMobileCustVisitService().getKonkaMobileCustVisit(
		        konkaMobileCustVisit);

		if ("2".equals(report_type) || "1".equals(report_type) || "3".equals(report_type)) {
			// 你选择的细类
			KonkaMobileCustVisitType konkaMobileCustVisitType = new KonkaMobileCustVisitType();
			konkaMobileCustVisitType.setVisit_id(Long.valueOf(visit_id));
			List<KonkaMobileCustVisitType> konkaMobileCustVisitTypeList = super.getFacade()
			        .getKonkaMobileCustVisitTypeService().getKonkaMobileCustVisitTypeList(konkaMobileCustVisitType);
			request.setAttribute("konkaMobileCustVisitTypeList", konkaMobileCustVisitTypeList);

			// 客户信息
			KonkaMobileCustVisitDetail konkaMobileCustVisitDetail = new KonkaMobileCustVisitDetail();
			konkaMobileCustVisitDetail.setVisit_id(Long.valueOf(visit_id));
			konkaMobileCustVisitDetail = super.getFacade().getKonkaMobileCustVisitDetailService()
			        .getKonkaMobileCustVisitDetail(konkaMobileCustVisitDetail);
			konkaMobileCustVisit.setKonkaMobileCustVisitDetail(konkaMobileCustVisitDetail);

			// 拿到图片
			Attachment a = new Attachment();
			a.setLink_tab("KONKA_MOBILE_CUST_VISIT");
			a.setLink_id(Long.valueOf(visit_id));
			Short isdel = new Short("0");
			a.setDel_mark(isdel);
			List<Attachment> aList = super.getFacade().getAttachmentService().getAttachmentList(a);
			konkaMobileCustVisit.setAttachmentsList(aList);

			BaseVisitType baseVisitType = new BaseVisitType();
			baseVisitType.setReport_type(Integer.parseInt(report_type));
			baseVisitType.setState(0);// 没有停用的
			List<BaseVisitType> visitTypeList = super.getFacade().getBaseVisitTypeService()
			        .getBaseVisitTypeByReportTypeList(baseVisitType);
			// 所有细类
			request.setAttribute("visitTypeList", visitTypeList);

		}
		konkaMobileCustVisit.setQueryString(super.serialize(request, "visit_id", "method"));
		super.copyProperties(form, konkaMobileCustVisit);

		KonkaR3ShopDev konkaR3ShopDev = new KonkaR3ShopDev();
		konkaR3ShopDev.setIs_del(0);
		List<KonkaR3ShopDev> konkaR3ShopDevList = new ArrayList<KonkaR3ShopDev>();
		konkaR3ShopDevList = super.getFacade().getKonkaR3ShopDevService().getKonkaR3ShopDevList(konkaR3ShopDev);
		request.setAttribute("konkaR3ShopDevList", konkaR3ShopDevList);
		request.setAttribute("report_type", report_type);// 上报类型
		request.setAttribute("report_type_tj", report_type_tj);// 列表条件
		if ("2".equals(report_type) || "1".equals(report_type)) {
			return new ActionForward("/../manager/admin/KonkaMobileCustVisit/view.jsp");
		} else {
			return new ActionForward("/../manager/admin/KonkaMobileCustVisit/view1.jsp");
		}

	}
	// 老版
	public ActionForward listForCount1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String isfirst = (String) dynaBean.get("isfirst");// 第一次没有初始值默认当前年月
		String mod_id = String.valueOf(dynaBean.get("mod_id"));
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String user_name_like = (String) dynaBean.get("user_name_like");
		String year = (String) dynaBean.get("year");//
		String month = (String) dynaBean.get("month");//
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String excel_all = (String) dynaBean.get("excel_all");//
		String jh_visit_count = (String) dynaBean.get("jh_visit_count");//
		String role_id = (String) dynaBean.get("role_id");

		HttpSession session = request.getSession();
		PeProdUser userInfo = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == userInfo) {
			return null;
		}
		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}
		KonkaMobileCustVisit entity = new KonkaMobileCustVisit();
		if (flag) {// 如果是业务员，只能看到自己的拜访记录
			entity.getMap().put("user_id", userInfo.getId());
		} else {

			Long _dept_id = 0L;
			int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
			logger.info("Max level : {}", max_dlevel);
			switch (max_dlevel) {
			case 9:
				// 集团及以下部门可见
				_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
				break;
			case 8:
				// 分公司及以下部门可见

				KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
				if (null != dept_fgs && null != dept_fgs.getDept_id()) {
					_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
					entity.getMap().put("par_dept_id", _dept_id);
					// entity.getMap().put("fgs_dept_value", _dept_id);
				}
				break;
			case 7:
				// 我所在的部门及以下部门可见
				_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
				entity.getMap().put("par_dept_id", _dept_id);
				break;
			case 0:
				_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
				entity.getMap().put("par_dept_id", _dept_id);
				entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
				break;
			default:
				// 出错
			}
			entity.getMap().put("session_user_id", userInfo.getId());

		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			entity.getMap().put("par_dept_id", l5_dept_id);
			dynaBean.set("l5_dept_id", l5_dept_id);
		} else if (StringUtils.isNotBlank(l4_dept_id)) {
			entity.getMap().put("par_dept_id", l4_dept_id);
			dynaBean.set("l4_dept_id", l4_dept_id);
		} else if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("par_dept_id", dept_id);
			dynaBean.set("dept_id", dept_id);
		}
		if (StringUtils.isNotBlank(user_name_like)) {
			entity.getMap().put("user_name_like", user_name_like);
			dynaBean.set("user_name_like", user_name_like);
		}
		if (StringUtils.isNotBlank(jh_visit_count)) {
			entity.getMap().put("jh_visit_count", jh_visit_count);
			if (jh_visit_count.equals("1")) {// 有计划
				entity.getMap().put("yjh", jh_visit_count);
			} else if (jh_visit_count.equals("0")) {// 无计划
				entity.getMap().put("wjh", jh_visit_count);
			}
			dynaBean.set("jh_visit_count", jh_visit_count);
		}
		if (StringUtils.isNotBlank(role_id)) {
			String role_ids[] = new String[1];
			role_ids[0] = role_id;
			entity.getMap().put("role_ids", role_ids);
			dynaBean.set("role_id", role_id);
		}

		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy年MM月");
		Calendar ca = Calendar.getInstance();
		ca.setTime(today); // someDate 为你要获取的那个月的时间
		ca.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate = ca.getTime();
		ca.add(Calendar.MONTH, 1);
		ca.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDate = ca.getTime();
		// entity.getMap().put("begin_time",df.format(firstDate)+" 00:00:00");
		// entity.getMap().put("end_time", df.format(lastDate)+" 23:59:59");
		request.setAttribute("year_month", df1.format(new Date()));
		if (StringUtils.isNotBlank(month) && StringUtils.isNotBlank(year)) {
			dynaBean.set("year", year);
			dynaBean.set("month", month);
			Date date = sdf.parse(year + "-" + month + "-01");
			ca.setTime(date); // someDate 为你要获取的那个月的时间
			ca.set(Calendar.DAY_OF_MONTH, 1);
			firstDate = ca.getTime();
			ca.add(Calendar.MONTH, 1);
			ca.add(Calendar.DAY_OF_MONTH, -1);
			lastDate = ca.getTime();
			entity.getMap().put("begin_time", df.format(firstDate) + " 00:00:00");
			entity.getMap().put("end_time", df.format(lastDate) + " 23:59:59");
			request.setAttribute("year_month", year + "年" + month + "月");
		}
		String beginStr = "";
		String endStr = "";
		if (StringUtils.isBlank(isfirst)) {
			Calendar now = Calendar.getInstance();
			int monthSearch = now.get(Calendar.MONTH) + 1;
			String yearMr = "" + now.get(Calendar.YEAR);
			String monthMr = "" + (monthSearch < 10 ? "0" + monthSearch : monthSearch);
			dynaBean.set("year", yearMr);
			dynaBean.set("month", monthMr);
			Integer yearI = Integer.valueOf(yearMr);
			Integer monthI = Integer.valueOf(monthMr);
			String day = "" + KonkaMobileCustVisitAction.day(yearI, monthI);
			beginStr = yearMr + "-" + monthMr + "-" + "01";
			endStr = yearMr + "-" + monthMr + "-" + day;
			entity.getMap().put("begin_time", beginStr + " 00:00:00");
			entity.getMap().put("end_time", endStr + " 23:59:59");
		}
		if (StringUtils.isNotBlank(isfirst)) {
			dynaBean.set("isfirst", isfirst);
		}

		List<KonkaMobileCustVisit> entityList = new ArrayList<KonkaMobileCustVisit>();
		Long recordCount = super.getFacade().getKonkaMobileCustVisitService()
		        .getKonkaMobileCustVisitAcountPaginatedListCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		// 导出
		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			if (recordCount.intValue() > 20000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
				        + "！');history.back();");
				return null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("业务完成汇总表")
			        + ".xls");
			entity.getRow().setFirst(0);
			entity.getRow().setCount(recordCount.intValue());

			List<KonkaMobileCustVisit> entityList1 = super.getFacade().getKonkaMobileCustVisitService()
			        .getKonkaMobileCustVisitAcountPaginatedList(entity);
			request.setAttribute("allList", entityList1);
			return new ActionForward("/admin/KonkaMobileCustVisit/listForCountReport1.jsp");
		}
		entityList = super.getFacade().getKonkaMobileCustVisitService().getKonkaMobileCustVisitAcountPaginatedList(
		        entity);

		request.setAttribute("entityList", entityList);

		return new ActionForward("/../manager/admin/KonkaMobileCustVisit/listForCount1.jsp");
	}
	
//	easyui
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 默认显示当前1月的时间区间
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		// String day_first = theFirstDayOfCurrentMonth();
		String year=df.format(today);
		//String year = year_month[0];
		df = new SimpleDateFormat("MM");
		String month=df.format(today);

	 
		//		
		// KonkaMobileSailData entity = new KonkaMobileSailData();
		// entity.getMap().put("date_begin", day_first);
		// entity.getMap().put("date_end", day_last);
		//        
		Map<String, Object> allmap = new HashMap<String, Object>();
		allmap.put("year", year);
		allmap.put("month", month);
		// allmap.put("date_begin", day_first);
		// allmap.put("date_end", day_last);

		// 位置信息
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		String naviString = "";
		if (StringUtils.isNotBlank(mod_id)) {
			SysModule sysModule = new SysModule();
			sysModule.getMap().put("mod_id", mod_id);
			List<SysModule> sysModuleList = getFacade().getSysModuleService().getSysModuleList(sysModule);
			naviString = StringHelper.getNaviString(sysModuleList, " > ");
		}
		allmap.put("local_str", naviString);

		// 当前用户信息
		PeProdUser ui = new PeProdUser();
		ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		int max_dlevel = super.getMaxDLevel(ui.getId());
		if (max_dlevel == 9) {
			allmap.put("dept_id", "");
		} else {
			allmap.put("dept_id", ui.getDept_id());
		}

		// 转换为json数据
		JSONArray jsonArray = JSONArray.fromObject(allmap);

		int start = jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString().substring(start + 1, end + 1));
		out.flush();
		out.close();
		return null;
	}
//	easyui
	public ActionForward listForCount2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String isfirst = (String) dynaBean.get("isfirst");// 第一次没有初始值默认当前年月
		String mod_id = String.valueOf(dynaBean.get("mod_id"));
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String user_name_like = (String) dynaBean.get("user_name_like");
		String year = (String) dynaBean.get("year");//
		String month = (String) dynaBean.get("month");//
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String excel_all = (String) dynaBean.get("excel_all");//
		String jh_visit_count = (String) dynaBean.get("jh_visit_count");//
		String role_id = (String) dynaBean.get("role_id");
		String page =(String) dynaBean.get("page");
		HttpSession session = request.getSession();
		PeProdUser userInfo = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == userInfo) {
			return null;
		}
		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}
		KonkaYwHzTj entity = new KonkaYwHzTj();
		if (flag) {// 如果是业务员，只能看到自己的拜访记录
			entity.getMap().put("user_id", userInfo.getId());
		} else {

			Long _dept_id = 0L;
			int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
			logger.info("Max level : {}", max_dlevel);
			switch (max_dlevel) {
			case 9:
				// 集团及以下部门可见
				_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
				break;
			case 8:
				// 分公司及以下部门可见

				KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
				if (null != dept_fgs && null != dept_fgs.getDept_id()) {
					_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
					entity.getMap().put("par_dept_id", _dept_id);
					// entity.getMap().put("fgs_dept_value", _dept_id);
				}
				break;
			case 7:
				// 我所在的部门及以下部门可见
				_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
				entity.getMap().put("par_dept_id", _dept_id);
				break;
			case 0:
				_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
				entity.getMap().put("par_dept_id", _dept_id);
				entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
				break;
			default:
				// 出错
			}
			entity.getMap().put("session_user_id", userInfo.getId());

		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			entity.getMap().put("par_dept_id", l5_dept_id);
			dynaBean.set("l5_dept_id", l5_dept_id);
		} else if (StringUtils.isNotBlank(l4_dept_id)) {
			entity.getMap().put("par_dept_id", l4_dept_id);
			dynaBean.set("l4_dept_id", l4_dept_id);
		} else if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("par_dept_id", dept_id);
			dynaBean.set("dept_id", dept_id);
		}
		if (StringUtils.isNotBlank(user_name_like)) {
			entity.getMap().put("user_name_like", user_name_like);
			dynaBean.set("user_name_like", user_name_like);
		}
		if (StringUtils.isNotBlank(jh_visit_count)) {
			entity.getMap().put("jh_visit_count", jh_visit_count);
			if (jh_visit_count.equals("1")) {// 有计划
				entity.getMap().put("yjh", jh_visit_count);
			} else if (jh_visit_count.equals("0")) {// 无计划
				entity.getMap().put("wjh", jh_visit_count);
			}
			dynaBean.set("jh_visit_count", jh_visit_count);
		}
		if (StringUtils.isNotBlank(role_id)) {
			String role_ids[] = new String[1];
			role_ids[0] = role_id;
			entity.getMap().put("role_ids", role_ids);
			dynaBean.set("role_id", role_id);
		}

		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy年MM月");
		Calendar ca = Calendar.getInstance();
		ca.setTime(today); // someDate 为你要获取的那个月的时间
		ca.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate = ca.getTime();
		ca.add(Calendar.MONTH, 1);
		ca.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDate = ca.getTime();
		request.setAttribute("year_month", df1.format(new Date()));
		if (StringUtils.isNotBlank(month) && StringUtils.isNotBlank(year)) {
			dynaBean.set("year", year);
			dynaBean.set("month", month);
			Date date = sdf.parse(year + "-" + month + "-01");
			ca.setTime(date); // someDate 为你要获取的那个月的时间
			ca.set(Calendar.DAY_OF_MONTH, 1);
			firstDate = ca.getTime();
			ca.add(Calendar.MONTH, 1);
			ca.add(Calendar.DAY_OF_MONTH, -1);
			lastDate = ca.getTime();
			entity.getMap().put("begin_time", df.format(firstDate) + " 00:00:00");
			entity.getMap().put("end_time", df.format(lastDate) + " 23:59:59");
			request.setAttribute("year_month", year + "年" + month + "月");
		}
		String beginStr = "";
		String endStr = "";
		if (StringUtils.isBlank(isfirst)) {
			Calendar now = Calendar.getInstance();
			int monthSearch = now.get(Calendar.MONTH) + 1;
			String yearMr = "" + now.get(Calendar.YEAR);
			String monthMr = "" + (monthSearch < 10 ? "0" + monthSearch : monthSearch);
			dynaBean.set("year", yearMr);
			dynaBean.set("month", monthMr);
			Integer yearI = Integer.valueOf(yearMr);
			Integer monthI = Integer.valueOf(monthMr);
			String day = "" + KonkaMobileCustVisitAction.day(yearI, monthI);
			beginStr = yearMr + "-" + monthMr + "-" + "01";
			endStr = yearMr + "-" + monthMr + "-" + day;
			entity.getMap().put("begin_time", beginStr + " 00:00:00");
			entity.getMap().put("end_time", endStr + " 23:59:59");
		}
		if (StringUtils.isNotBlank(isfirst)) {
			dynaBean.set("isfirst", isfirst);
		}
		List<KonkaYwHzTj> entityList = new ArrayList<KonkaYwHzTj>();
		Long recordCount = super.getFacade().getKonkaYwHzTjService().getKonkaYwHzTjCount(entity);
		

		// 导出

		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			if (recordCount.intValue() > 20000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
				        + "！');history.back();");
				return null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("业务完成汇总表")
			        + ".xls");
			entity.getRow().setFirst(0);
			entity.getRow().setCount(recordCount.intValue());
			List<KonkaYwHzTj> entityList1 = super.getFacade().getKonkaYwHzTjService().getKonkaYwHzTjPaginatedList(
			        entity);
			request.setAttribute("allList", entityList1);
			return new ActionForward("/admin/KonkaMobileCustVisit/listForCountReport.jsp");
		}
		pager.init(recordCount, pager.getPageSize(), page);
		//	pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
			entity.getRow().setFirst(pager.getFirstRow());
			entity.getRow().setCount(pager.getRowCount());
		// 查找最新数据
		entityList = super.getFacade().getKonkaYwHzTjService().getKonkaYwHzTjPaginatedList(entity);
	
		// 封装成JSON字符串
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("total", recordCount);
		if (entityList == null) {
			String[] str = {};
			m.put("rows", str);
		} else {
			m.put("rows", entityList);
		}

		JSONArray jsonArray = JSONArray.fromObject(m);
		int start = jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start + 1, end + 1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}
	// 新版
	public ActionForward listForCount(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String isfirst = (String) dynaBean.get("isfirst");// 第一次没有初始值默认当前年月
		String mod_id = String.valueOf(dynaBean.get("mod_id"));
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String user_name_like = (String) dynaBean.get("user_name_like");
		String year = (String) dynaBean.get("year");//
		String month = (String) dynaBean.get("month");//
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String excel_all = (String) dynaBean.get("excel_all");//
		String jh_visit_count = (String) dynaBean.get("jh_visit_count");//
		String role_id = (String) dynaBean.get("role_id");
		String update_year = (String) dynaBean.get("update_year");
		String update_month = (String) dynaBean.get("update_month");

		HttpSession session = request.getSession();
		PeProdUser userInfo = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == userInfo) {
			return null;
		}
		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}
		KonkaYwHzTj entity = new KonkaYwHzTj();
		if (flag) {// 如果是业务员，只能看到自己的拜访记录
			entity.getMap().put("user_id", userInfo.getId());
		} else {

			Long _dept_id = 0L;
			int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
			logger.info("Max level : {}", max_dlevel);
			switch (max_dlevel) {
			case 9:
				// 集团及以下部门可见
				_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
				break;
			case 8:
				// 分公司及以下部门可见

				KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
				if (null != dept_fgs && null != dept_fgs.getDept_id()) {
					_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
					entity.getMap().put("par_dept_id", _dept_id);
					// entity.getMap().put("fgs_dept_value", _dept_id);
				}
				break;
			case 7:
				// 我所在的部门及以下部门可见
				_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
				entity.getMap().put("par_dept_id", _dept_id);
				break;
			case 0:
				_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
				entity.getMap().put("par_dept_id", _dept_id);
				entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
				break;
			default:
				// 出错
			}
			entity.getMap().put("session_user_id", userInfo.getId());

		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			entity.getMap().put("par_dept_id", l5_dept_id);
			dynaBean.set("l5_dept_id", l5_dept_id);
		} else if (StringUtils.isNotBlank(l4_dept_id)) {
			entity.getMap().put("par_dept_id", l4_dept_id);
			dynaBean.set("l4_dept_id", l4_dept_id);
		} else if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("par_dept_id", dept_id);
			dynaBean.set("dept_id", dept_id);
		}
		if (StringUtils.isNotBlank(user_name_like)) {
			entity.getMap().put("user_name_like", user_name_like);
			dynaBean.set("user_name_like", user_name_like);
		}
		if (StringUtils.isNotBlank(jh_visit_count)) {
			entity.getMap().put("jh_visit_count", jh_visit_count);
			if (jh_visit_count.equals("1")) {// 有计划
				entity.getMap().put("yjh", jh_visit_count);
			} else if (jh_visit_count.equals("0")) {// 无计划
				entity.getMap().put("wjh", jh_visit_count);
			}
			dynaBean.set("jh_visit_count", jh_visit_count);
		}
		if (StringUtils.isNotBlank(role_id)) {
			String role_ids[] = new String[1];
			role_ids[0] = role_id;
			entity.getMap().put("role_ids", role_ids);
			dynaBean.set("role_id", role_id);
		}

		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy年MM月");
		Calendar ca = Calendar.getInstance();
		ca.setTime(today); // someDate 为你要获取的那个月的时间
		ca.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate = ca.getTime();
		ca.add(Calendar.MONTH, 1);
		ca.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDate = ca.getTime();
		request.setAttribute("year_month", df1.format(new Date()));
		if (StringUtils.isNotBlank(month) && StringUtils.isNotBlank(year)) {
			dynaBean.set("year", year);
			dynaBean.set("month", month);
			Date date = sdf.parse(year + "-" + month + "-01");
			ca.setTime(date); // someDate 为你要获取的那个月的时间
			ca.set(Calendar.DAY_OF_MONTH, 1);
			firstDate = ca.getTime();
			ca.add(Calendar.MONTH, 1);
			ca.add(Calendar.DAY_OF_MONTH, -1);
			lastDate = ca.getTime();
			entity.getMap().put("begin_time", df.format(firstDate) + " 00:00:00");
			entity.getMap().put("end_time", df.format(lastDate) + " 23:59:59");
			request.setAttribute("year_month", year + "年" + month + "月");
		}
		String beginStr = "";
		String endStr = "";
		if (StringUtils.isBlank(isfirst)) {
			Calendar now = Calendar.getInstance();
			int monthSearch = now.get(Calendar.MONTH) + 1;
			String yearMr = "" + now.get(Calendar.YEAR);
			String monthMr = "" + (monthSearch < 10 ? "0" + monthSearch : monthSearch);
			dynaBean.set("year", yearMr);
			dynaBean.set("month", monthMr);
			Integer yearI = Integer.valueOf(yearMr);
			Integer monthI = Integer.valueOf(monthMr);
			String day = "" + KonkaMobileCustVisitAction.day(yearI, monthI);
			beginStr = yearMr + "-" + monthMr + "-" + "01";
			endStr = yearMr + "-" + monthMr + "-" + day;
			entity.getMap().put("begin_time", beginStr + " 00:00:00");
			entity.getMap().put("end_time", endStr + " 23:59:59");
		}
		if (StringUtils.isNotBlank(isfirst)) {
			dynaBean.set("isfirst", isfirst);
		}
		List<KonkaYwHzTj> entityList = new ArrayList<KonkaYwHzTj>();
		Long recordCount = super.getFacade().getKonkaYwHzTjService().getKonkaYwHzTjCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		// 导出
		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			if (recordCount.intValue() > 20000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
				        + "！');history.back();");
				return null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("业务完成汇总表")
			        + ".xls");
			entity.getRow().setFirst(0);
			entity.getRow().setCount(recordCount.intValue());
			List<KonkaYwHzTj> entityList1 = super.getFacade().getKonkaYwHzTjService().getKonkaYwHzTjPaginatedList(
			        entity);
			request.setAttribute("allList", entityList1);
			return new ActionForward("/admin/KonkaMobileCustVisit/listForCountReport.jsp");
		}

		// 查找最新数据
		entityList = super.getFacade().getKonkaYwHzTjService().getKonkaYwHzTjPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		return new ActionForward("/../manager/admin/KonkaMobileCustVisit/listForCount.jsp");
	}
    /**
     * 月度拜访明细
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ActionForward listMonthVisitReport(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String mod_id = (String) dynaBean.get("mod_id");
		String isfirst = (String) dynaBean.get("isfirst");// 第一次没有初始值默认当前年月
		String is_cust_or_shop = (String) dynaBean.get("is_cust_or_shop");
		String peport_name_like = (String) dynaBean.get("peport_name_like");
		String dept_id_fgs = (String) dynaBean.get("dept_id_fgs");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String shop_name_like = (String) dynaBean.get("shop_name_like");
		String excel_all = (String) dynaBean.get("excel_all");//
		String year = (String) dynaBean.get("year");//
		String month = (String) dynaBean.get("month");// 
		String role_id = (String) dynaBean.get("role_id");
		String r3_code_like = (String) dynaBean.get("r3_code_like");// 客户r3或者终端对已的r3code
		String par_customer_type = (String) dynaBean.get("par_customer_type");// 类型id

		HttpSession session = request.getSession();
		PeProdUser userInfo = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == userInfo) {
			return null;
		}
		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}
		KonkaMobileCustVisit entity = new KonkaMobileCustVisit();
		if (flag) {// 如果是业务员，只能看到自己的拜访记录
			entity.getMap().put("user_id", userInfo.getId());
		} else {
			Long _dept_id = 0L;
			int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
			logger.info("Max level : {}", max_dlevel);
			switch (max_dlevel) {
			case 9:
				// 集团及以下部门可见
				_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
				break;
			case 8:
				// 分公司及以下部门可见

				KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
				if (null != dept_fgs && null != dept_fgs.getDept_id()) {
					_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
					entity.getMap().put("par_dept_id", _dept_id);
					// entity.getMap().put("fgs_dept_value", _dept_id);
				}
				break;
			case 7:
				// 我所在的部门及以下部门可见
				_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
				entity.getMap().put("par_dept_id", _dept_id);
				break;
			case 0:
				_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
				entity.getMap().put("par_dept_id", _dept_id);
				entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
				break;
			default:
				// 出错
			}
			entity.getMap().put("session_user_id", userInfo.getId());
		}
		if (StringUtils.isNotBlank(role_id)) {
			String role_ids[] = new String[1];
			role_ids[0] = role_id;
			entity.getMap().put("role_ids", role_ids);
			dynaBean.set("role_id", role_id);
		}
		if (StringUtils.isNotBlank(peport_name_like)) {
			entity.getMap().put("peport_name_like", peport_name_like);
			dynaBean.set("peport_name_like", peport_name_like);
		}
		if (StringUtils.isNotBlank(l5_dept_id) && StringUtils.isNumeric(l5_dept_id)) {
			entity.getMap().put("l5_dept_id", Integer.valueOf(l5_dept_id));
			dynaBean.set("l5_dept_id", l5_dept_id);
		} else if (StringUtils.isNotBlank(l4_dept_id) && StringUtils.isNumeric(l4_dept_id)) {
			entity.getMap().put("l4_dept_id", Integer.valueOf(l4_dept_id));
			dynaBean.set("l4_dept_id", l4_dept_id);
		} else if (StringUtils.isNotBlank(dept_id_fgs) && StringUtils.isNumeric(dept_id_fgs)) {
			entity.getMap().put("dept_id_fgs", Integer.valueOf(dept_id_fgs));
			dynaBean.set("dept_id_fgs", dept_id_fgs);
		}
		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like);
			dynaBean.set("customer_name_like", customer_name_like);
		}
		if (StringUtils.isNotBlank(shop_name_like)) {
			entity.getMap().put("shop_name_like", shop_name_like);
			dynaBean.set("shop_name_like", shop_name_like);
		}
		// 客户类型或终端对应的客类型
		if (StringUtils.isNotBlank(par_customer_type)) {
			entity.getMap().put("par_customer_type", par_customer_type);
			dynaBean.set("par_customer_type", par_customer_type);
		}
		// 客户r3或终端对应的客户r3编码
		if (StringUtils.isNotBlank(r3_code_like)) {
			entity.getMap().put("r3_code_like", r3_code_like);
			dynaBean.set("r3_code_like", r3_code_like);
		}
		String beginStr = "";
		String endStr = "";
		if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
			Integer yearI = Integer.valueOf(year);
			Integer monthI = Integer.valueOf(month);

			dynaBean.set("year", year);
			dynaBean.set("month", month);

			String day = "" + KonkaMobileCustVisitAction.day(yearI, monthI);
			beginStr = yearI + "-" + monthI + "-" + "01";
			endStr = yearI + "-" + monthI + "-" + day;
			entity.getMap().put("begin_time", beginStr);
			entity.getMap().put("end_time", endStr);
		}

		if (StringUtils.isBlank(isfirst)) {
			Calendar now = Calendar.getInstance();
			int monthSearch = now.get(Calendar.MONTH) + 1;
			String yearMr = "" + now.get(Calendar.YEAR);
			String monthMr = "" + monthSearch;
			dynaBean.set("year", yearMr);
			dynaBean.set("month", monthMr);
			Integer yearI = Integer.valueOf(yearMr);
			Integer monthI = Integer.valueOf(monthMr);
			String day = "" + KonkaMobileCustVisitAction.day(yearI, monthI);
			beginStr = yearMr + "-" + monthMr + "-" + "01";
			endStr = yearMr + "-" + monthMr + "-" + day;
			entity.getMap().put("begin_time", beginStr);
			entity.getMap().put("end_time", endStr);
		}
		if (StringUtils.isNotBlank(isfirst)) {
			dynaBean.set("isfirst", isfirst);
		}
		List<KonkaMobileCustVisit> entityList = new ArrayList<KonkaMobileCustVisit>();
		dynaBean.set("is_cust_or_shop", is_cust_or_shop);
		if (StringUtils.isNotBlank(is_cust_or_shop) && is_cust_or_shop.equals("cust")) {

			Long recordCount = super.getFacade().getKonkaMobileCustVisitService().getCustMonthVisitPaginatedListCount(
			        entity);
			pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
			entity.getRow().setFirst(pager.getFirstRow());
			entity.getRow().setCount(pager.getRowCount());
			// 导出月度客户详情
			if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
				if (recordCount.intValue() > 20000) {
					renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
					        + "！');history.back();");
					return null;
				}
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment;filename="
				        + EncryptUtils.encodingFileName(year+"年"+month+"月"+"月度拜访客户明细表") + ".xls");
				entity.getRow().setFirst(0);
				entity.getRow().setCount(recordCount.intValue());

				List<KonkaMobileCustVisit> entityList1 = super.getFacade().getKonkaMobileCustVisitService()
				        .getCustMonthVisitPaginatedList(entity);
				request.setAttribute("allList", entityList1);
				return new ActionForward("/admin/KonkaMobileCustVisit/listMonthVisitReport.jsp");
			}
			entityList = super.getFacade().getKonkaMobileCustVisitService().getCustMonthVisitPaginatedList(entity);
		} else if (StringUtils.isNotBlank(is_cust_or_shop) && is_cust_or_shop.equals("shop")) {
			Long recordCount = super.getFacade().getKonkaMobileCustVisitService().getShopMonthVisitPaginatedListCount(
			        entity);
			pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
			entity.getRow().setFirst(pager.getFirstRow());
			entity.getRow().setCount(pager.getRowCount());
			// 导出终端月度详情
			if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
				if (recordCount.intValue() > 20000) {
					renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
					        + "！');history.back();");
					return null;
				}
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment;filename="
				        + EncryptUtils.encodingFileName(year+"年"+month+"月"+"月度拜访终端明细数据") + ".xls");
				entity.getRow().setFirst(0);
				entity.getRow().setCount(recordCount.intValue());
				List<KonkaMobileCustVisit> entityList1 = super.getFacade().getKonkaMobileCustVisitService()
				        .getShopMonthVisitPaginatedList(entity);
				request.setAttribute("allList", entityList1);
				return new ActionForward("/admin/KonkaMobileCustVisit/listMonthVisitReport.jsp");
			}
			entityList = super.getFacade().getKonkaMobileCustVisitService().getShopMonthVisitPaginatedList(entity);
		}
		request.setAttribute("entityList", entityList);

		// 客户类型
		KonkaCategory kc = new KonkaCategory();
		List<HashMap> custtypelist = super.getFacade().getKonkaCategoryService().getKonkaCategoryGroupByCCommNew(kc);
		request.setAttribute("konkaCategoryList", custtypelist);
		return new ActionForward("/../manager/admin/KonkaMobileCustVisit/listMonthVisit.jsp");
	}

	/**
	 * 客户 结算汇款，结算统计报表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listMoneryReport(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String mod_id = (String) dynaBean.get("mod_id");
		String isfirst = (String) dynaBean.get("isfirst");// 第一次没有初始值默认当前年月
		String r3_code_like = (String) dynaBean.get("r3_code_like");// 客户R3code
		String customer_name_like = (String) dynaBean.get("customer_name_like");// 客户名称
		String ywy_user_name_like = (String) dynaBean.get("ywy_user_name_like");// 上报人
		String shop_status = (String) dynaBean.get("shop_status");// 客户状态 lg 正常
		// 重拾。。。
		String par_customer_type = (String) dynaBean.get("par_customer_type");// 客户类型
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String excel_all = (String) dynaBean.get("excel_all");//
		String year = (String) dynaBean.get("year");//
		String month = (String) dynaBean.get("month");// 
		String role_id = (String) dynaBean.get("role_id");
		String dept_id = (String) dynaBean.get("dept_id");

		HttpSession session = request.getSession();
		PeProdUser userInfo = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == userInfo) {
			return null;
		}
		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}
		KonkaR3Shop entity = new KonkaR3Shop();
		if (flag) {// 如果是业务员，只能看到自己的拜访记录
			entity.getMap().put("user_id", userInfo.getId());
		} else {
			Long _dept_id = 0L;
			int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
			logger.info("Max level : {}", max_dlevel);
			switch (max_dlevel) {
			case 9:
				// 集团及以下部门可见
				_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
				break;
			case 8:
				// 分公司及以下部门可见

				KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
				if (null != dept_fgs && null != dept_fgs.getDept_id()) {
					_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
					entity.getMap().put("par_dept_id", _dept_id);
				}
				break;
			case 7:
				// 我所在的部门及以下部门可见
				_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
				entity.getMap().put("par_dept_id", _dept_id);
				break;
			case 0:
				_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
				entity.getMap().put("par_dept_id", _dept_id);
				entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
				break;
			default:
				// 出错
			}
			entity.getMap().put("session_user_id", userInfo.getId());
		}
		if (StringUtils.isNotBlank(role_id)) {
			String role_ids[] = new String[1];
			role_ids[0] = role_id;
			entity.getMap().put("role_ids", role_ids);
			dynaBean.set("role_id", role_id);
		}
        //分公司id
		if (null!=dept_id&&StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("branch_area_code", dept_id);
			dynaBean.set("dept_id", dept_id);
		}
		// 客户r3或终端对应的客户r3编码
		if (StringUtils.isNotBlank(r3_code_like)) {
			entity.getMap().put("r3_code_like", r3_code_like);
			dynaBean.set("r3_code_like", r3_code_like);
		}
		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like);
			dynaBean.set("customer_name_like", customer_name_like);
		}
		if (StringUtils.isNotBlank(ywy_user_name_like)) {
			entity.getMap().put("ywy_user_name_like", ywy_user_name_like);
			dynaBean.set("ywy_user_name_like", ywy_user_name_like);
		}
		if (StringUtils.isNotBlank(shop_status)) {
			entity.getMap().put("shop_status", shop_status);
			dynaBean.set("shop_status", shop_status);
		}
		if (StringUtils.isNotBlank(par_customer_type)) {
			entity.getMap().put("par_customer_type", par_customer_type);
			dynaBean.set("par_customer_type", par_customer_type);
		}
		if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
			entity.getMap().put("search_time", year + month);
			dynaBean.set("year", year);
			dynaBean.set("month", month);
		}
		// 第一次默认当前年月
		if (StringUtils.isBlank(isfirst)) {
			DateFormat df1 = new SimpleDateFormat("yyyyMM");
			Calendar now = Calendar.getInstance();
			entity.getMap().put("search_time", df1.format(now.getTime()));
			dynaBean.set("year", "" + now.get(Calendar.YEAR));
			dynaBean.set("month", "" + now.get(Calendar.MONTH) + 1);
		}
		if (StringUtils.isNotBlank(isfirst)) {
			dynaBean.set("isfirst", isfirst);
		}
		List<HashMap> entityList = new ArrayList<HashMap>();
		Long recordCount = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopMoneryPaginatedListCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		// 导出月客户 汇款 结算 零售数据
		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			if (recordCount.intValue() > 20000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
				        + "！');history.back();");
				return null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
			        + EncryptUtils.encodingFileName("客户销售统计表") + ".xls");
			entity.getRow().setFirst(0);
			entity.getRow().setCount(recordCount.intValue());

			List<HashMap> entityList1 = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopMoneryPaginatedList(
			        entity);
			request.setAttribute("allList", entityList1);
			return new ActionForward("/admin/KonkaMobileCustVisit/listForCustMoneryCountReport.jsp");
		}
		entityList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopMoneryPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		// 客户类型
		KonkaCategory kc = new KonkaCategory();
		List<HashMap> custtypelist = super.getFacade().getKonkaCategoryService().getKonkaCategoryGroupByCCommNew(kc);
		request.setAttribute("konkaCategoryList", custtypelist);
		return new ActionForward("/../manager/admin/KonkaMobileCustVisit/listForCustMoneryCount.jsp");
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
		boolean flag = false;//业务员
		boolean fgsgly = false;//分公司管理员
		boolean fgsfz = false;//分公司副总
		boolean jybjl = false;//经营部经理
		boolean bsczr = false;//办事处主任
		boolean ywzg = false;//业务主管
		boolean fgzjl = false;//分公司总经理
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
			if (peRoleUser.getRole_id().equals(new Long(31))) {
				fgsfz = true;
			}
			if (peRoleUser.getRole_id().equals(new Long(40))) {
				jybjl = true;
			}
			if (peRoleUser.getRole_id().equals(new Long(50))) {
				bsczr = true;
			}
			if (peRoleUser.getRole_id().equals(new Long(69))) {
				ywzg = true;
			}
			if (peRoleUser.getRole_id().equals(new Long(34))) {
				fgzjl = true;
			}
		}
		// if (flag) {// 是业务员
		// entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
		// entity.getMap().put("fgs_dept_value", "");
		// }else
		//是指定角色同时不是指定的不可报的角色可以取到数据并上报
		 if ((flag||jybjl||bsczr||ywzg)&&(!fgsgly||!fgsfz||!fgzjl)) {
		     
	     }else {
			return null;
		 }
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
					KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
					if (null != dept_fgs && null != dept_fgs.getDept_id()) {
						_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
						entity.getMap().put("dept_id_start", _dept_id);
						// entity.getMap().put("fgs_dept_value", _dept_id);
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
		boolean flag = false;//业务员
		boolean fgsgly = false;//分公司管理员
		boolean fgsfz = false;//分公司副总
		boolean jybjl = false;//经营部经理
		boolean bsczr = false;//办事处主任
		boolean ywzg = false;//业务主管
		boolean fgzjl = false;//分公司总经理
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
			if (peRoleUser.getRole_id().equals(new Long(31))) {
				fgsfz = true;
			}
			if (peRoleUser.getRole_id().equals(new Long(40))) {
				jybjl = true;
			}
			if (peRoleUser.getRole_id().equals(new Long(50))) {
				bsczr = true;
			}
			if (peRoleUser.getRole_id().equals(new Long(69))) {
				ywzg = true;
			}
			if (peRoleUser.getRole_id().equals(new Long(34))) {
				fgzjl = true;
			}
		}

		   if ((flag||jybjl||bsczr||ywzg)&&(!fgsgly||!fgsfz||!fgzjl)) {
			     
		    }else {
				return null;
			}
		
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
					KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
					if (null != dept_fgs && null != dept_fgs.getDept_id()) {
						_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
						entity.getMap().put("dept_id_start", _dept_id);
						// entity.getMap().put("fgs_dept_value", _dept_id);
					}
					break;
			case 7:
				// 我所在的部门及以下部门可见
				if (jybjl || bsczr || ywzg || flag) {
					_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
					entity.getMap().put("dept_id_start", _dept_id);
					break;
				} else {
					return null;
				}
			case 0:
				_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			   // entity.getMap().put("dept_id_start", _dept_id);
				entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
				break;
			default:
				return null;
			}
			entity.getMap().put("session_user_id", userInfo.getId());
		entity.setIs_del(0);// 未被停用的
		List<KonkaR3Store> entityList = super.getFacade().getKonkaR3StoreService().getKonkaR3StoreForCustVisit(entity);

		return entityList;
	}
}
