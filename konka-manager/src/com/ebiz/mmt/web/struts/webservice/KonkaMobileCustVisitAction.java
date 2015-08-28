package com.ebiz.mmt.web.struts.webservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.BaseVisitType;
import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.JBasePartner;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileCustVisit;
import com.ebiz.mmt.domain.KonkaMobileCustVisitDetail;
import com.ebiz.mmt.domain.KonkaMobileCustVisitType;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3ShopDev;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaMobileCustVisitAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String report_type = (String) dynaBean.get("report_type");
		dynaBean.set("is_del", "0");
		dynaBean.set("report_type", report_type);
		return list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String mod_id = String.valueOf(dynaBean.get("mod_id"));
		String report_type = String.valueOf(dynaBean.get("report_type"));
		String begin_date = (String) dynaBean.get("begin_date");
		String end_date = (String) dynaBean.get("end_date");
		String state = (String) dynaBean.get("state");
		String is_del = (String) dynaBean.get("is_del");
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String report_nae_like = (String) dynaBean.get("report_nae_like");// 上报人
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String shop_id = (String) dynaBean.get("shop_id");
		String shop_name_like = (String) dynaBean.get("shop_name_like");
		String visit_id = (String) dynaBean.get("visit_id");
		String first = (String) dynaBean.get("first");
		String count = (String) dynaBean.get("count");
		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		// 类型
		// String type = (String) dynaBean.get("type");
		
		//版本号,android_version,ios_version
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		String result = "操作错误";
		if (null == userInfo) {
			result = "用户验证错误，请重新登录后提交";
			super.renderJson(response, JSON.toJSONString(result));
		}
		KonkaMobileCustVisit entity = new KonkaMobileCustVisit();
		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}

		if (flag) {// 如果是业务员，只能看到自己的拜访记录
			entity.setAdd_user_id(userInfo.getId());
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

		}
		entity.setIs_del(0);
		if (StringUtils.isNotBlank(report_type) && StringUtils.isNumeric(report_type)) {
			entity.setReport_type(Integer.valueOf(report_type));
		}
		if (StringUtils.isNotBlank(visit_id) && StringUtils.isNumeric(visit_id)) {
			entity.setVisit_id(Long.valueOf(visit_id));
		}
		if (StringUtils.isNotBlank(begin_date)) {
			entity.getMap().put("time_start", begin_date);
		}
		if (StringUtils.isNotBlank(end_date)) {
			entity.getMap().put("time_end", end_date);
		}
		if (StringUtils.isNotBlank(state)) {
			entity.setState(Integer.valueOf(state));
		}
		if (StringUtils.isNotBlank(is_del) && StringUtils.isNumeric(is_del)) {
			entity.setIs_del(Integer.parseInt(is_del));
		}
		if (StringUtils.isNotBlank(r3_code_like)) {
			entity.getMap().put("r3_code_like", r3_code_like);
		}
		if (StringUtils.isNotBlank(report_nae_like)) {
			entity.getMap().put("report_nae_like", report_nae_like);
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
		List<KonkaMobileCustVisit> entityList = null;
		if ("2".equals(report_type) || "1".equals(report_type) || "3".equals(report_type)) {

			Long recordCount = super.getFacade().getKonkaMobileCustVisitService()
					.getKonkaMobileCustVisitAndDetailCount(entity);
			pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
			// entity.getRow().setFirst(pager.getFirstRow());
			// entity.getRow().setCount(pager.getRowCount());
			// 没有传入起始行默认第一行开始
			if (StringUtils.isNotBlank(first) && StringUtils.isNumeric(first)) {
				entity.getRow().setFirst(Integer.valueOf(first));
			} else {
				entity.getRow().setFirst(0);
			}
			// 没有传入查看几行默认查看起始行后面所有行数
			if (StringUtils.isNotBlank(count) && StringUtils.isNumeric(count)) {
				entity.getRow().setCount(Integer.valueOf(count));
			} else {
				entity.getRow().setCount(recordCount.intValue());
			}
			entityList = super.getFacade().getKonkaMobileCustVisitService()
					.getKonkaMobileCustVisitAndDetailPaginatedList(entity);
		} else {
			Long recordCount = super.getFacade().getKonkaMobileCustVisitService()
					.getKonkaMobileCustVisitLBCount(entity);
			pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
			// entity.getRow().setFirst(pager.getFirstRow());
			// entity.getRow().setCount(pager.getRowCount());
			// 没有传入起始行默认第一行开始
			// 没有传入起始行默认第一行开始
			if (StringUtils.isNotBlank(first) && StringUtils.isNumeric(first)) {
				entity.getRow().setFirst(Integer.valueOf(first));
			} else {
				entity.getRow().setFirst(0);
			}
			// 没有传入查看几行默认查看起始行后面所有行数
			if (StringUtils.isNotBlank(count) && StringUtils.isNumeric(count)) {
				entity.getRow().setCount(Integer.valueOf(count));
			} else {
				entity.getRow().setCount(recordCount.intValue());
			}
			entityList = super.getFacade().getKonkaMobileCustVisitService().getKonkaMobileCustVisitPaginatedLBList(
					entity);
		}
		request.setAttribute("mod_id", mod_id);
		request.setAttribute("entityList", entityList);

		List<HashMap> list = new ArrayList<HashMap>();
		HashMap map = new HashMap();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if (null != entityList) {
			for (KonkaMobileCustVisit kkcv : entityList) {
				map = new HashMap();
				map.put("visit_id", kkcv.getVisit_id() == null ? "" : kkcv.getVisit_id());
				if (null != kkcv.getAdd_date()) {
					map.put("add_date", df.format(kkcv.getAdd_date()));
				}
				if (null != kkcv.getBegin_date()) {
					map.put("begin_date", df.format(kkcv.getBegin_date()));
				}
				if (null != kkcv.getEnd_date()) {
					map.put("end_date", df.format(kkcv.getEnd_date()));
				}
				map.put("report_type", kkcv.getReport_type() == null ? "" : kkcv.getReport_type());
				map.put("feed_list", kkcv.getFeed_list() == null ? "" : kkcv.getFeed_list());
				map.put("state", kkcv.getState() == null ? "" : kkcv.getState());
				map.put("visit_desc", kkcv.getVisit_desc() == null ? "" : kkcv.getVisit_desc());
				map.put("ywy_job_id", kkcv.getYwy_job_id() == null ? "" : kkcv.getYwy_job_id());
				map.put("dept_id", kkcv.getDept_id() == null ? "" : kkcv.getDept_id());
				map.put("subcomp_id", kkcv.getSubcomp_id() == null ? "" : kkcv.getSubcomp_id());
				map.put("is_del", kkcv.getIs_del() == null ? "" : kkcv.getIs_del());
				map.put("add_user_id", kkcv.getAdd_user_id() == null ? "" : kkcv.getAdd_user_id());
				map.put("visit_per_count", kkcv.getVisit_per_count() == null ? "" : kkcv.getVisit_per_count());
				map.put("report_nae", kkcv.getReport_nae() == null ? "" : kkcv.getReport_nae());
				map.put("data_source", kkcv.getData_source() == null ? "" : kkcv.getData_source());

				map.put("r3_code", kkcv.getMap().get("r3_code") == null ? "" : kkcv.getMap().get("r3_code"));
				map.put("customer_name", kkcv.getMap().get("customer_name") == null ? "" : kkcv.getMap().get(
						"customer_name"));
				map.put("shop_id", kkcv.getMap().get("shop_id") == null ? "" : kkcv.getMap().get("shop_id"));
				map.put("shop_name", kkcv.getMap().get("shop_name") == null ? "" : kkcv.getMap().get("shop_name"));

				map.put("visit_type_ids", kkcv.getMap().get("visit_type_ids") == null ? "" : kkcv.getMap().get(
						"visit_type_ids"));
				map.put("visit_type_names", kkcv.getMap().get("visit_type_names") == null ? "" : kkcv.getMap().get(
						"visit_type_names"));
				map.put("dept_name", kkcv.getMap().get("dept_name") == null ? "" : kkcv.getMap().get("dept_name"));
				map.put("subcomp_name", kkcv.getMap().get("subcomp_name") == null ? "" : kkcv.getMap().get(
						"subcomp_name"));

				map.put("domestic_ranking", kkcv.getDomestic_ranking() == null ? "" : kkcv.getDomestic_ranking());
				map.put("consumer_sales", kkcv.getConsumer_sales() == null ? "" : kkcv.getConsumer_sales());
				map.put("retail_sales", kkcv.getRetail_sales() == null ? "" : kkcv.getRetail_sales());
				map.put("consumer_name", kkcv.getConsumer_name() == null ? "" : kkcv.getConsumer_name());
				map.put("consumer_phone", kkcv.getConsumer_phone() == null ? "" : kkcv.getConsumer_phone());
				map.put("fj_paths", kkcv.getMap().get("fj_paths") == null ? "" : kkcv.getMap().get("fj_paths"));
				
				map.put("addr", kkcv.getMap().get("address") == null ? "" : kkcv.getMap().get("address"));
				list.add(map);
			}

		}
		logger.info(entityList.toString());
		super.renderJson(response, JSON.toJSONString(list));
		return null;
		// return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String report_type = (String) dynaBean.get("report_type");
		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		// 类型
		String type = (String) dynaBean.get("type");

		// 时间
		String start_date = (String) dynaBean.get("start_date");
		
		//版本号,android_version,ios_version
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

		// 用户名或者密码不存在， 资讯信息类别为公开
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);

		if (null == userInfo) {
			super.renderHtml(response, "登录超时");
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
			super.renderHtml(response, "该用户（" + userInfo.getReal_name() + "）不是业务员！");
			return null;
		}
		Long user_id = userInfo.getId();
		BranchAssign branchAssign = new BranchAssign();
		branchAssign.setUser_id(user_id);
		List<BranchAssign> branchAssignList = super.getFacade().getBranchAssignService()
				.getBranchAssignAndKonkaR3ShopList(branchAssign);

		// 通过用户找门店
		KonkaR3Store store = new KonkaR3Store();
		store.getMap().put("user_id", userInfo.getId());
		store.getMap().put("ywy_job_id", userInfo.getJob_id());
		List<KonkaR3Store> storeList = super.getFacade().getKonkaR3StoreService().getKonkaR3StoreListWithYwyUserId(
				store);

		request.setAttribute("storeList", storeList);
		request.setAttribute("custList", branchAssignList);

		request.setAttribute("report_type", report_type);
		BaseVisitType baseVisitType = new BaseVisitType();
		if (StringUtils.isNotBlank(report_type) && StringUtils.isNumeric(report_type)) {
			baseVisitType.setReport_type(Integer.parseInt(report_type));
		}
		baseVisitType.setState(0);
		List<BaseVisitType> visitTypeList = super.getFacade().getBaseVisitTypeService()
				.getBaseVisitTypeByReportTypeList(baseVisitType);

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
		DynaBean dynaBean = (DynaBean) form;

		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		// 类型
		String type = (String) dynaBean.get("type");

		// 时间
		String start_date = (String) dynaBean.get("start_date");
		String result = "操作异常";
		
		//版本号,android_version,ios_version
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		// 用户名或者密码不存在， 资讯信息类别为公开
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		if (null == userInfo) {
			result = "用户验证错误，请重新登录后提交";
			super.renderJson(response, JSON.toJSONString(result));
		}

		String visit_id = (String) dynaBean.get("visit_id");// 拜访类型

		String report_type = (String) dynaBean.get("report_type");// 拜访类型

		String mod_id = (String) dynaBean.get("mod_id");// 拜访开始时间
		String begin_date = (String) dynaBean.get("begin_date");// 拜访开始时间
		String end_date = (String) dynaBean.get("end_date");// 拜访结束时间
		String r3_code = (String) dynaBean.get("r3_code");// 拜访客户编码
		String shop_id = (String) dynaBean.get("shop_id");// 拜访门店id
		String feed_list = (String) dynaBean.get("feed_list");// 反馈内容
		String visit_desc = (String) dynaBean.get("visit_desc");// 拜访说明
		String[] visit_type_id = request.getParameterValues("visit_type_id");// 拜访类型
		String visit_per_count = (String) dynaBean.get("visit_per_count");// 拜访内容(上报类型)
		String cust_id = (String) dynaBean.get("cust_id");// 开拓客户id
		String cust_name = (String) dynaBean.get("cust_name");// 开拓客户
		String consumer_name = (String) dynaBean.get("consumer_name");// 开拓客户联系人  
		String consumer_phone = (String) dynaBean.get("consumer_phone");// 开拓客户电话

		String state = (String) dynaBean.get("state");// 开拓客户上报客户名称
		String data_source = (String) dynaBean.get("data_source");
		String position_x = (String) dynaBean.get("position_x");// 上报人经度
		String position_y = (String) dynaBean.get("position_y");// 上报人纬度
		String addr = (String) dynaBean.get("addr");// 上报人地址

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
		if (StringUtils.isNotBlank(report_type) && StringUtils.isNumeric(report_type)) {
			konkaMobileCustVisit.setReport_type(Integer.parseInt(report_type));// 上报类型
		}
		if (StringUtils.isBlank(visit_id)) {
			konkaMobileCustVisit.setAdd_user_id(userInfo.getId());// 添加人userid
		}
		konkaMobileCustVisit.setYwy_job_id(userInfo.getJob_id());// 业务员jobId
		konkaMobileCustVisit.setDept_id(userInfo.getDept_id());// 添加人单位id
		if (StringUtils.isBlank(visit_id)) {
			konkaMobileCustVisit.setReport_nae(userInfo.getReal_name());// 上报人姓名
		}
		if (null != konkaDept && null != konkaDept.getDept_id()) {
			konkaMobileCustVisit.setSubcomp_id(konkaDept.getDept_id());// 分公司id
		}
		if (StringUtils.isBlank(visit_per_count)) {
			konkaMobileCustVisit.setVisit_per_count(1L);// 拜访人数
		} else {
			if (StringUtils.isNumeric(visit_per_count)) {
				konkaMobileCustVisit.setVisit_per_count(Long.valueOf(visit_per_count));// 拜访人数
			}
		}
		if ("1".equals(report_type) || "2".equals(report_type)) {

			if (null == begin_date || StringUtils.isEmpty(begin_date)) {
				result = "开始时间不能为空！";
				super.renderJson(response, JSON.toJSONString(result));
				return null;
			}
			if ((null == r3_code || StringUtils.isEmpty(r3_code)) && (null == shop_id || StringUtils.isEmpty(shop_id))) {
				result = "客户门店必选一个";
				super.renderJson(response, JSON.toJSONString(result));
				return null;
			}
			if (null == visit_type_id || visit_type_id.length == 0) {
				result = "拜访类型不能为空！";
				super.renderJson(response, JSON.toJSONString(result));
				return null;
			} else {

			}
		}

		if ("3".equals(report_type)) {

			if (null == begin_date || StringUtils.isEmpty(begin_date)) {
				result = "开拓时间不能为空！";
				super.renderJson(response, JSON.toJSONString(result));
				return null;
			}
			if (null == cust_id || StringUtils.isEmpty(cust_id)) {
				result = "客户不能为空！";
				super.renderJson(response, JSON.toJSONString(result));
				return null;
			}
			/*
			 * if (null==consumer_name||StringUtils.isEmpty(consumer_name)) {
			 * result = "被拜访人不能为空！";
			 * super.renderJson(response, JSON.toJSONString(result));
			 * return null;
			 * }
			 * if (null==consumer_phone||StringUtils.isEmpty(consumer_phone)) {
			 * result = "被拜访人电话不能为空！";
			 * super.renderJson(response, JSON.toJSONString(result));
			 * return null;
			 * }
			 */
		}

		if ("1".equals(report_type) || "2".equals(report_type)) {
			if (null == feed_list || StringUtils.isEmpty(feed_list)) {
				result = "反馈问题不能为空";
				super.renderJson(response, JSON.toJSONString(result));
				return null;
			} else {
				konkaMobileCustVisit.setFeed_list(feed_list);
			}

			if (null == visit_desc || StringUtils.isEmpty(visit_desc)) {
				result = "拜访说明不能为空";
				super.renderJson(response, JSON.toJSONString(result));
				return null;
			} else {
				konkaMobileCustVisit.setVisit_desc(visit_desc);
			}

		}
		if (null != state && StringUtils.isNotBlank(state) && StringUtils.isNumeric(state)) {
			konkaMobileCustVisit.setState(Integer.parseInt(state));
		} else {
			result = "状态不能为空";
			super.renderJson(response, JSON.toJSONString(result));
			return null;
		}
		if (StringUtils.isBlank(data_source)) {
			konkaMobileCustVisit.setData_source(0);// 数据来源
		} else {
			konkaMobileCustVisit.setData_source(Integer.valueOf(data_source));// 数据来源
		}
		if (StringUtils.isNotBlank(addr) && "获取地址信息失败".equals(addr)) {
			result = "GPS定位失败！";
			super.renderJson(response, JSON.toJSONString(result));
			return null;
		} else {
			if (StringUtils.isNotBlank(position_x)) {
				konkaMobileCustVisit.getMap().put("position_x", position_x);
			}
			if (StringUtils.isNotBlank(position_y)) {
				konkaMobileCustVisit.getMap().put("position_y", position_y);
			}
			konkaMobileCustVisit.getMap().put("addr", addr);
		}

		if (StringUtils.isNotBlank(position_x) && StringUtils.isNotBlank(position_x)) {
			konkaMobileCustVisit.getMap().put("position_x", position_x);
			konkaMobileCustVisit.getMap().put("position_y", position_y);
		}
		// 数据处理 存储客户信息的表
		if (StringUtils.isNotBlank(report_type) && "3".equals(report_type) && StringUtils.isNotBlank(cust_id)) {
			KonkaMobileCustVisitDetail konkaMobileCustVisitDetail = new KonkaMobileCustVisitDetail();
			konkaMobileCustVisitDetail.setR3_code(cust_id);
			KonkaR3ShopDev custname=new KonkaR3ShopDev();
			if (StringUtils.isNotBlank(cust_id)) {
				custname.setCust_id(Long.valueOf(cust_id));
				custname=super.getFacade().getKonkaR3ShopDevService().getKonkaR3ShopDev(custname);
			}
			konkaMobileCustVisitDetail.setCustomer_name(null!=custname&&null!=custname.getCust_name() ? custname.getCust_name():"未知客户");
			konkaMobileCustVisit.setKonkaMobileCustVisitDetail(konkaMobileCustVisitDetail);
		}

		konkaMobileCustVisit.getMap().put("opr_user_id", userInfo.getId().toString());// 上报人id
		// konkaMobileCustVisit.getMap().put("ip_addr",
		// IpUtils.getIpAddr(request));// 上报人ip

		if (StringUtils.isNotBlank(report_type) && ("1".equals(report_type) || "2".equals(report_type))) {
			if (StringUtils.isNotBlank(r3_code) || StringUtils.isNotBlank(shop_id)) {
				KonkaMobileCustVisitDetail konkaMobileCustVisitDetail = new KonkaMobileCustVisitDetail();

				KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
				KonkaR3Store konkaR3Store = new KonkaR3Store();
				JBasePartner jbase = new JBasePartner();
				if (StringUtils.isNotBlank(r3_code)) {
					konkaR3Shop.setR3_code(r3_code);
					konkaR3Shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
					if (null == konkaR3Shop || null == konkaR3Shop.getCustomer_name()
							|| "".equals(konkaR3Shop.getCustomer_name())) {
						konkaR3Shop.setCustomer_name("未知客户");
					}
					konkaMobileCustVisitDetail.setR3_code(r3_code);// 客户r3编码
					konkaMobileCustVisitDetail.setCustomer_name(konkaR3Shop.getCustomer_name());// 客户名称

				}
				if (StringUtils.isNotBlank(shop_id)) {
					if (StringUtils.isNumeric(shop_id)) {
						konkaR3Store.setStore_id(Long.valueOf(shop_id));
					}

					if (StringUtils.isNumeric(shop_id)) {
						if (shop_id.indexOf("191919") > -1) {
							jbase.setPartner_id(Long.valueOf(shop_id.replace("191919", "")));
							jbase.setIs_del(0);
							List<JBasePartner> jbaseList = super.getFacade().getJBasePartnerService()
									.getJBasePartnerList(jbase);
							if (jbaseList != null && jbaseList.size() > 0) {
								jbase = jbaseList.get(0);
							}
							if (jbase != null) {
									konkaMobileCustVisitDetail.setShop_id(jbase.getPartner_id());
								if (jbase.getP_level() != null) {
									konkaMobileCustVisitDetail.setShop_name("[" + jbase.getP_level() + "级]"
											+ jbase.getPartner_name());// 门店名称
								} else {
									konkaMobileCustVisitDetail.setShop_name("[" + "级]" + jbase.getPartner_name());// 门店名称

								}
								konkaMobileCustVisitDetail.setCustomer_type(10L);

							}
						} else {
							KonkaR3Store storeTemp = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(konkaR3Store);
							if (null == storeTemp || null == storeTemp.getStore_name()
									|| "".equals(storeTemp.getStore_name())) {
								konkaR3Store.setStore_name("未知门店");
							}
							konkaMobileCustVisitDetail.setShop_id(Long.valueOf(shop_id));// 门店id
							konkaMobileCustVisitDetail.setShop_name(storeTemp.getStore_name());// 门店名称
						}

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
				if (StringUtils.isNotBlank(type_id) && StringUtils.isNumeric(type_id)) {
					baseVisitType.setVisit_type_id(Long.valueOf(type_id));
				}
				baseVisitType = super.getFacade().getBaseVisitTypeService().getBaseVisitType(baseVisitType);
				if (StringUtils.isNotBlank(type_id) && StringUtils.isNumeric(type_id)) {
					konkaMobileCustVisitType.setVisit_type_id(Integer.parseInt(type_id));

					konkaMobileCustVisitType.setVisit_type_name(baseVisitType.getVisit_type_name());
					konkaMobileCustVisitType.setAdd_date(new Date());
					konkaMobileCustVisitTypeList.add(konkaMobileCustVisitType);
				}
			}
			konkaMobileCustVisit.setKonkaMobileCustVisitTypeList(konkaMobileCustVisitTypeList);
		}
		Long id = 0l;

		if (StringUtils.isNotBlank(visit_id)) {
			super.getFacade().getKonkaMobileCustVisitService().modifyKonkaMobileCustVisit(konkaMobileCustVisit);
			result = "success:" + visit_id;
		} else {
			id = super.getFacade().getKonkaMobileCustVisitService().createKonkaMobileCustVisit(konkaMobileCustVisit);
			result = "success:" + id;
		}
		// super.renderJson(response, JSON.toJSONString(result));
		super.renderTextOrJsonp(request, response, result);
		return null;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String report_type = (String) dynaBean.get("report_type");
		String visit_id = (String) dynaBean.get("visit_id");
		if (StringUtils.isBlank(visit_id)) {
			super.renderHtml(response, "请选择一条记录修改!");
			return null;
		}

		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		// 类型
		String type = (String) dynaBean.get("type");

		// 时间
		String start_date = (String) dynaBean.get("start_date");

		//版本号,android_version,ios_version
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		// 用户名或者密码不存在， 资讯信息类别为公开
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);

		if (null == userInfo) {
			super.renderHtml(response, "登录超时");
			return null;
		}

		// 主表信息
		KonkaMobileCustVisit konkaMobileCustVisit = new KonkaMobileCustVisit();
		if (StringUtils.isNotBlank(visit_id) && StringUtils.isNumeric(visit_id)) {
			konkaMobileCustVisit.setVisit_id(Long.valueOf(visit_id));
		}
		konkaMobileCustVisit.setState(0);// 未停用
		konkaMobileCustVisit = super.getFacade().getKonkaMobileCustVisitService().getKonkaMobileCustVisit(
				konkaMobileCustVisit);

		if ("2".equals(report_type) || "1".equals(report_type) || "3".equals(report_type)) {
			// 你选择的细类
			KonkaMobileCustVisitType konkaMobileCustVisitType = new KonkaMobileCustVisitType();
			if (StringUtils.isNotBlank(visit_id) && StringUtils.isNumeric(visit_id)) {
				konkaMobileCustVisitType.setVisit_id(Long.valueOf(visit_id));
			}
			List<KonkaMobileCustVisitType> konkaMobileCustVisitTypeList = super.getFacade()
					.getKonkaMobileCustVisitTypeService().getKonkaMobileCustVisitTypeList(konkaMobileCustVisitType);
			request.setAttribute("konkaMobileCustVisitTypeList", konkaMobileCustVisitTypeList);

			// 客户信息
			KonkaMobileCustVisitDetail konkaMobileCustVisitDetail = new KonkaMobileCustVisitDetail();
			if (StringUtils.isNotBlank(visit_id) && StringUtils.isNumeric(visit_id)) {
				konkaMobileCustVisitDetail.setVisit_id(Long.valueOf(visit_id));
			}
			konkaMobileCustVisitDetail = super.getFacade().getKonkaMobileCustVisitDetailService()
					.getKonkaMobileCustVisitDetail(konkaMobileCustVisitDetail);
			if (null != konkaMobileCustVisitDetail) {
				if (null != konkaMobileCustVisitDetail.getR3_code()) {
					request.setAttribute("r3_code", konkaMobileCustVisitDetail.getR3_code());
				}
				if (null != konkaMobileCustVisitDetail.getShop_id()) {
					request.setAttribute("shop_id", konkaMobileCustVisitDetail.getShop_id());
				}
			}
			// 拿到图片
			KonkaPeAttachments attachment = new KonkaPeAttachments();
			if (StringUtils.isNotBlank(visit_id) && StringUtils.isNumeric(visit_id)) {
				attachment.setLink_id(Long.valueOf(visit_id));
			}
			attachment.setLink_tab("KONKA_MOBILE_CUST_VISIT");
			attachment = super.getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachments(attachment);
			if (null != attachment) {
				request.setAttribute("save_path", attachment.getSave_path());
			}

			Long user_id = userInfo.getId();
			BranchAssign branchAssign = new BranchAssign();
			branchAssign.setUser_id(user_id);
			List<BranchAssign> branchAssignList = super.getFacade().getBranchAssignService()
					.getBranchAssignAndKonkaR3ShopList(branchAssign);
			request.setAttribute("custList", branchAssignList);// 客户
			// 通过用户找门店
			KonkaR3Store store = new KonkaR3Store();
			store.getMap().put("user_id", userInfo.getId());
			store.getMap().put("ywy_job_id", userInfo.getJob_id());
			List<KonkaR3Store> storeList = super.getFacade().getKonkaR3StoreService().getKonkaR3StoreListWithYwyUserId(
					store);
			BaseVisitType baseVisitType = new BaseVisitType();
			if (StringUtils.isNotBlank(report_type) && StringUtils.isNumeric(report_type)) {
				baseVisitType.setReport_type(Integer.parseInt(report_type));
			}
			List<BaseVisitType> visitTypeList = super.getFacade().getBaseVisitTypeService()
					.getBaseVisitTypeByReportTypeList(baseVisitType);
			// 所有细类
			request.setAttribute("visitTypeList", visitTypeList);

			request.setAttribute("storeList", storeList);// 门店
		}
		super.copyProperties(form, konkaMobileCustVisit);

		request.setAttribute("report_type", report_type);// 上报类型
		if ("2".equals(report_type) || "1".equals(report_type) || "3".equals(report_type)) {
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

		if (!GenericValidator.isLong(visit_id)) {
			super.saveError(request, "errors.long", "visit_id");
			return this.list(mapping, form, request, response);
		}
		KonkaMobileCustVisit entity = new KonkaMobileCustVisit();
		if (StringUtils.isNotBlank(visit_id) && StringUtils.isNumeric(visit_id)) {
			entity.setVisit_id(Long.valueOf(visit_id));
		}
		entity.setIs_del(1);
		Integer number = super.getFacade().getKonkaMobileCustVisitService().modifyKonkaMobileCustVisit(entity);
		logger.info("-----" + number);
		entity.setQueryString(super.serialize(request, "visit_id", "method"));

		logger.info("++++++++++++++QueryString=" + super.encodeSerializedQueryString(entity.getQueryString()));
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("report_type=" + report_type);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	// 获取门店
	public ActionForward ajaxGetShop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		// 类型
		String type = (String) dynaBean.get("type");

		// 时间
		String start_date = (String) dynaBean.get("start_date");

		String report_type = (String) dynaBean.get("report_type");
		//版本号,android_version,ios_version
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");


		// 用户名或者密码不存在， 资讯信息类别为公开
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);

		if (null == userInfo) {
			super.renderHtml(response, "登录超时");
			return null;
		}
		if (StringUtils.isBlank(report_type) || !GenericValidator.isInt(report_type)) {
			report_type = "1";
		}

		List<KonkaR3Store> storeList = getShop(userInfo, Integer.parseInt(report_type));
		List<HashMap> list = new ArrayList<HashMap>();
		HashMap map = new HashMap();
		if (null != storeList) {
			for (KonkaR3Store krs : storeList) {
				map = new HashMap();
				map.put("store_id", krs.getStore_id() == null ? "" : krs.getStore_id());
				map.put("store_name", krs.getStore_name() == null ? "" : krs.getStore_name());
				list.add(map);
			}
		}
		// logger.info(storeList.toString());
		super.renderJson(response, JSON.toJSONString(list));

		return null;
	}

	// 获取客户
	public ActionForward ajaxGetCustomer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		// 类型
		String type = (String) dynaBean.get("type");

		String report_type = (String) dynaBean.get("report_type");
		//版本号,android_version,ios_version
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

		// 用户名或者密码不存在， 资讯信息类别为公开
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);

		if (null == userInfo) {
			super.renderHtml(response, "登录超时");
			return null;
		}
		if (StringUtils.isBlank(report_type) || !GenericValidator.isInt(report_type)) {
			report_type = "1";
		}
		List<KonkaR3Shop> entityList = getCust(userInfo, Integer.parseInt(report_type));

		List<HashMap> list = new ArrayList<HashMap>();
		HashMap map = new HashMap();
		if (null != entityList) {
			for (KonkaR3Shop ba : entityList) {
				map = new HashMap();
				map.put("customer_code", ba.getMap().get("customer_code") == null ? "" : ba.getMap().get(
						"customer_code"));
				map.put("customer_name", ba.getMap().get("customer_name") == null ? "" : ba.getMap().get(
						"customer_name"));
				list.add(map);
			}
		}
		// logger.info(entityList.toString());
		super.renderJson(response, JSON.toJSONString(list));

		return null;
	}

	/*
	 * 拜访次数，拜访客户数量，拜访门店数量统计
	 * 
	 * @Wang,KunLin
	 */

	public ActionForward ajaxForCount(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 开始时间
		String start_date = (String) dynaBean.get("start_date");
		// 结束时间
		String end_date = (String) dynaBean.get("end_date");
		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		// 类型
		String type = (String) dynaBean.get("type");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		String result = "操作异常";
		if (null == userInfo) {
			result = "用户验证错误，请重新登录后提交";
			super.renderJson(response, JSON.toJSONString(result));
			return null;
		}
		KonkaMobileCustVisit konkMobileCustVisit = new KonkaMobileCustVisit();
		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}
		if (flag) {// 如果是业务员，只能看到自己的拜访记录
			konkMobileCustVisit.setAdd_user_id(userInfo.getId());
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
					konkMobileCustVisit.getMap().put("dept_id_start", _dept_id);
					// entity.getMap().put("fgs_dept_value", _dept_id);
				}
				break;
			case 7:
				// 我所在的部门及以下部门可见
				_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
				konkMobileCustVisit.getMap().put("dept_id_start", _dept_id);
				break;
			case 0:
				_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
				// entity.getMap().put("dept_id_start", __dept_id);
				konkMobileCustVisit.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
				break;
			default:
				// 出错
			}
			konkMobileCustVisit.getMap().put("session_user_id", userInfo.getId());

		}
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);

		calendar.add(Calendar.DATE, -15);
		String day_first = df.format(calendar.getTime());
		String day_last = df.format(today);
		
        if(null!=start_date&&start_date.length()>10){
        	start_date=start_date.split(" ")[0];
        }
		if (StringUtils.isNotBlank(start_date)) {
			konkMobileCustVisit.getMap().put("begin_date", start_date + " 00:00:00");
		} else {
			konkMobileCustVisit.getMap().put("begin_date", day_first);
		}
		if(null!=end_date&&end_date.length()>10){
			end_date=end_date.split(" ")[0];
		}
		if (StringUtils.isNotBlank(end_date)) {
			konkMobileCustVisit.getMap().put("end_date", end_date + " 23:59:59");
		} else {
			konkMobileCustVisit.getMap().put("end_date", day_last);
		}

		@SuppressWarnings("unused")
		List<KonkaMobileCustVisit> konkaMobileCustVisitList = super.getFacade().getKonkaMobileCustVisitService()
				.getKonkaMobileCustVisitForCount(konkMobileCustVisit);

		List<HashMap> list = new ArrayList<HashMap>();
		HashMap map = new HashMap();
		if (null != konkaMobileCustVisitList) {
			for (KonkaMobileCustVisit kkcv : konkaMobileCustVisitList) {
				map = new HashMap();
				map.put("report_type", kkcv.getReport_type() == null ? "" : kkcv.getReport_type());
				map.put("report_type_name", kkcv.getMap().get("report_type_name") == null ? "" : kkcv.getMap().get(
						"report_type_name"));
				map
						.put("visit_count", kkcv.getMap().get("visit_count") == null ? "" : kkcv.getMap().get(
								"visit_count"));// 拜访次数
				map.put("cust_count", kkcv.getMap().get("cust_count") == null ? "" : kkcv.getMap().get("cust_count"));// 拜访
				map.put("shop_count", kkcv.getMap().get("shop_count") == null ? "" : kkcv.getMap().get("shop_count"));
				list.add(map);
			}
		}
		logger.info(konkaMobileCustVisitList.toString());
		super.renderJson(response, JSON.toJSONString(list));
		return null;

	}

	// 拿到新开拓的客户 这个是为了新客户开拓日志上报使用
	public ActionForward ajaxForCustDev(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		//版本号,android_version,ios_version
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		String result = "操作异常";
		if (null == userInfo) {
			result = "用户验证错误，请重新登录后提交";
			super.renderJson(response, JSON.toJSONString(result));
			return null;
		}

		KonkaR3ShopDev konkaR3ShopDev = new KonkaR3ShopDev();
		konkaR3ShopDev.setIs_del(0);
		konkaR3ShopDev.setYwy_user_id(userInfo.getId());
		List<KonkaR3ShopDev> konkaR3ShopDevList = new ArrayList<KonkaR3ShopDev>();
		konkaR3ShopDevList = super.getFacade().getKonkaR3ShopDevService().getKonkaR3ShopDevList(konkaR3ShopDev);
		request.setAttribute("konkaR3ShopDevList", konkaR3ShopDevList);
		List<HashMap> list = new ArrayList<HashMap>();
		HashMap map = new HashMap();
		if (null != konkaR3ShopDevList) {
			for (KonkaR3ShopDev kksd : konkaR3ShopDevList) {
				map = new HashMap();
				map.put("cust_id", kksd.getCust_id() == null ? "" : kksd.getCust_id());
				map.put("cust_name", kksd.getCust_name() == null ? "" : kksd.getCust_name());
				list.add(map);
			}
		}
		logger.info(konkaR3ShopDevList.toString());
		super.renderJson(response, JSON.toJSONString(list));
		return null;
	}

	@SuppressWarnings("unused")
	private List<KonkaR3Shop> getCust(PeProdUser userInfo, Integer report_type) {
		KonkaR3Shop entity = new KonkaR3Shop();
		if (null != report_type) {
			if (report_type.equals(1)) {

				entity.getMap().put("cust_type", new String[] { "1", "2" });
			}
			if (report_type.equals(2)) {
				entity.getMap().put("cust_type", new String[] { "3", "4" });
			}
		} else {
			report_type = 1;// 如果没有传默认是所有客户
			entity.getMap().put("cust_type", new String[] { "1", "2", "3", "4" });
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
		//是指定角色同时不是指定的不可报的角色可以取到数据并上报
		if ((flag||jybjl||bsczr||ywzg)&&(!fgsgly||!fgsfz||!fgzjl)) {
		     
	     }else {
			return null;
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
					KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
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
		}
		entity.setIs_del(0L);
		List<KonkaR3Shop> entityList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopForCustVisit(entity);

		return entityList;
	}

	@SuppressWarnings("unused")
	private List<KonkaR3Store> getShop(PeProdUser userInfo, Integer report_type) {
		KonkaR3Store entity = new KonkaR3Store();
		if (null != report_type) {
			if (report_type.equals(1)) {

				entity.getMap().put("cust_type", new String[] { "1", "2" });
			}
			if (report_type.equals(2)) {
				entity.getMap().put("cust_type", new String[] { "3", "4" });
			}
		} else {
			report_type = 1;// 如果没有传默认是所有客户
			entity.getMap().put("cust_type", new String[] { "1", "2", "3", "4" });
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
		// /if (flag) {// 是业务员
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
					KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
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
				_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
				// entity.getMap().put("dept_id_start", __dept_id);
				entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
				break;
			}
			entity.getMap().put("session_user_id", userInfo.getId());
		}
		entity.setIs_del(0);
		List<KonkaR3Store> entityList = super.getFacade().getKonkaR3StoreService().getKonkaR3StoreForCustVisit(entity);

		return entityList;
	}

	/**
	 * 拜访报表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listForCount(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");

		String mod_id = String.valueOf(dynaBean.get("mod_id"));
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String user_name_like = (String) dynaBean.get("user_name_like");
		String begin_time = (String) dynaBean.get("begin_time");// 开始时间
		String end_time = (String) dynaBean.get("end_time");// 结束时间
		String jh_visit_count = (String) dynaBean.get("jh_visit_count");//
		String role_id = (String) dynaBean.get("role_id");
		String first = (String) dynaBean.get("first");
		String count = (String) dynaBean.get("count");
		//版本号,android_version,ios_version
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		String result = "操作错误";
		if (null == userInfo) {
			result = "用户验证错误，请重新登录后提交";
			super.renderJson(response, JSON.toJSONString(result));
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

		if (StringUtils.isNotBlank(begin_time)) {
			entity.getMap().put("begin_time", begin_time + " 00:00:00");
		}
		if (StringUtils.isNotBlank(end_time)) {
			entity.getMap().put("end_time", end_time + " 23:59:59");
		}
		List<KonkaMobileCustVisit> entityList = new ArrayList<KonkaMobileCustVisit>();
		Long recordCount = super.getFacade().getKonkaMobileCustVisitService()
				.getKonkaMobileCustVisitAcountPaginatedListCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		// 没有传入起始行默认第一行开始
		if (StringUtils.isNotBlank(first) && StringUtils.isNumeric(first)) {
			entity.getRow().setFirst(Integer.valueOf(first));
		} else {
			entity.getRow().setFirst(0);
		}
		// 没有传入查看几行默认查看起始行后面所有行数
		if (StringUtils.isNotBlank(count) && StringUtils.isNumeric(count)) {
			entity.getRow().setCount(Integer.valueOf(count));
		} else {
			entity.getRow().setCount(recordCount.intValue());
		}
		entityList = super.getFacade().getKonkaMobileCustVisitService().getKonkaMobileCustVisitAcountPaginatedList(
				entity);

		List<HashMap> list = new ArrayList<HashMap>();
		HashMap map = new HashMap();
		for (KonkaMobileCustVisit custVisit : entityList) {
			map = new HashMap();
			map.put("user_id", custVisit.getMap().get("user_id") == null ? "" : custVisit.getMap().get("user_id"));
			map
					.put("user_name", custVisit.getMap().get("user_name") == null ? "" : custVisit.getMap().get(
							"user_name"));
			map.put("dept_one", custVisit.getMap().get("dept_one") == null ? "" : custVisit.getMap().get("dept_one"));
			map.put("dept_name_one", custVisit.getMap().get("dept_name_one") == null ? "" : custVisit.getMap().get(
					"dept_name_one"));
			map.put("dept_two", custVisit.getMap().get("dept_two") == null ? "" : custVisit.getMap().get("dept_two"));
			map.put("dept_name_two", custVisit.getMap().get("dept_name_two") == null ? "" : custVisit.getMap().get(
					"dept_name_two"));
			map.put("dept_three", custVisit.getMap().get("dept_three") == null ? "" : custVisit.getMap().get(
					"dept_three"));
			map.put("dept_name_three", custVisit.getMap().get("dept_name_three") == null ? "" : custVisit.getMap().get(
					"dept_name_three"));
			map.put("jh_visit_cust_count", custVisit.getMap().get("jh_visit_cust_count") == null ? "" : custVisit
					.getMap().get("jh_visit_cust_count"));
			map.put("jh_visit_shop_count", custVisit.getMap().get("jh_visit_shop_count") == null ? "" : custVisit
					.getMap().get("jh_visit_shop_count"));
			map.put("jh_visit_count", custVisit.getMap().get("jh_visit_count") == null ? "" : custVisit.getMap().get(
					"jh_visit_count"));
			map.put("bf_count", custVisit.getMap().get("bf_count") == null ? "" : custVisit.getMap().get("bf_count"));
			map.put("bf_cust_count", custVisit.getMap().get("bf_cust_count") == null ? "" : custVisit.getMap().get(
					"bf_cust_count"));
			map.put("bf_shop_count", custVisit.getMap().get("bf_shop_count") == null ? "" : custVisit.getMap().get(
					"bf_shop_count"));
			map.put("zc_cust_visit_count", custVisit.getMap().get("zc_cust_visit_count") == null ? "" : custVisit
					.getMap().get("zc_cust_visit_count"));
			map.put("zc_shop_visit_count", custVisit.getMap().get("zc_shop_visit_count") == null ? "" : custVisit
					.getMap().get("zc_shop_visit_count"));
			map.put("cs_cust_visit_count", custVisit.getMap().get("cs_cust_visit_count") == null ? "" : custVisit
					.getMap().get("cs_cust_visit_count"));
			map.put("cs_shop_visit_count", custVisit.getMap().get("cs_shop_visit_count") == null ? "" : custVisit
					.getMap().get("cs_shop_visit_count"));
			map.put("jh_dev_cust_count", custVisit.getMap().get("jh_dev_cust_count") == null ? "" : custVisit.getMap()
					.get("jh_dev_cust_count"));
			map.put("kt_cust_count", custVisit.getMap().get("kt_cust_count") == null ? "" : custVisit.getMap().get(
					"kt_cust_count"));
			map.put("kt_cust_visit_count", custVisit.getMap().get("kt_cust_visit_count") == null ? "" : custVisit
					.getMap().get("kt_cust_visit_count"));
			list.add(map);
		}
		logger.info(entityList.toString());
		super.renderJson(response, JSON.toJSONString(list));
		return null;
	}

	/**
	 * 通过登陆用户查找业务员列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listYwyByUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		String mod_id = String.valueOf(dynaBean.get("mod_id"));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		//版本号,android_version,ios_version
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		KonkaMobileCustVisit entity = new KonkaMobileCustVisit();
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		String result = "操作错误";
		if (null == userInfo) {
			result = "用户验证错误，请重新登录后提交";
			super.renderJson(response, JSON.toJSONString(result));
			return null;
		}
		if (null != userInfo.getId()) {
			entity.getMap().put("userId", userInfo.getId());
		}
		if (null != userInfo.getDept_id()) {
			entity.getMap().put("deptId", userInfo.getDept_id());
		}
		List<PeProdUser> entityList = new ArrayList<PeProdUser>();
		entityList = super.getFacade().getKonkaMobileCustVisitService().getYwyByUserList(entity);

		List<HashMap> list = new ArrayList<HashMap>();
		HashMap map = new HashMap();
		for (PeProdUser peProdUser : entityList) {
			map = new HashMap();
			map.put("id", peProdUser.getId() == null ? "" : peProdUser.getId());
			map.put("user_name", peProdUser.getUser_name() == null ? "" : peProdUser.getUser_name());
			list.add(map);
		}
		logger.info(entityList.toString());
		super.renderJson(response, JSON.toJSONString(list));
		return null;
	}

	/**
	 * 取得当前用户的下拉部门列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listKonkaDeptListForUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		String mod_id = String.valueOf(dynaBean.get("mod_id"));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		//版本号,android_version,ios_version
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		KonkaDept entity = new KonkaDept();
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		String result = "操作错误";
		if (null == userInfo) {
			result = "用户验证错误，请重新登录后提交";
			super.renderJson(response, JSON.toJSONString(result));
			return null;
		}
		if (null != userInfo.getDept_id()) {
			entity.getMap().put("user_dept_Id", userInfo.getDept_id());
		}
		List<KonkaDept> entityList = new ArrayList<KonkaDept>();
		entityList = super.getFacade().getKonkaDeptService().getKonkaDeptListForUser(entity);

		List<HashMap> list = new ArrayList<HashMap>();
		HashMap map = new HashMap();
		for (KonkaDept konkaDept : entityList) {
			map = new HashMap();
			map.put("dept_id", konkaDept.getDept_id() == null ? "" : konkaDept.getDept_id());
			map.put("dept_name", konkaDept.getDept_name() == null ? "" : konkaDept.getDept_name());
			list.add(map);
		}
		logger.info(entityList.toString());
		super.renderJson(response, JSON.toJSONString(list));
		return null;
	}

	@Override
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
		

}
