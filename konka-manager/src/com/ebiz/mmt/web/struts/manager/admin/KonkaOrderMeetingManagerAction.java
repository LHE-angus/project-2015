package com.ebiz.mmt.web.struts.manager.admin;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaOrderMeetingCustomer;
import com.ebiz.mmt.domain.KonkaOrderMeetingManager;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaSpActivityType;
import com.ebiz.mmt.domain.KonkaSpMdSail;
import com.ebiz.mmt.domain.KonkaSpMdType;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * Code by AutoGenerated Builder AutoGenerated Builder Version 2.1
 * 
 * @author Zhang,Chao
 * @datetime 2014-01-23 14:50:49
 */
public class KonkaOrderMeetingManagerAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	/**
	 * @author Zhang,Chao
	 * @date 2013-7-5
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;

		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(10);
		String dept_id = (String) dynaBean.get("dept_id");
		String customer_like = (String) dynaBean.get("customer_like");
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String file_status = (String) dynaBean.get("file_status");
		String meeting_name_like = (String) dynaBean.get("meeting_name_like");
		String meeting_id = (String) dynaBean.get("meeting_id");
		String order_money_min = (String) dynaBean.get("order_money_min");
		String order_money_max = (String) dynaBean.get("order_money_max");
		String open_date_start = (String) dynaBean.get("open_date_start");
		String open_date_end = (String) dynaBean.get("open_date_end");
		String add_meeting_date_start = (String) dynaBean.get("add_meeting_date_start");
		String add_meeting_date_end = (String) dynaBean.get("add_meeting_date_end");
		String report_user_name_like = (String) dynaBean.get("report_user_name_like");
		String charge_person_like = (String) dynaBean.get("charge_person_like");

		String export = (String) dynaBean.get("export");

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		boolean is_fgsgly = false;// 是否是分公司管理员
		boolean is_fgszjl = false;// 是否是分公司总经理
		if (null == ui) {
			super.renderJavaScript(response, "alert（'用户登录超时');history.back();");
			return null;
		}
		PeRoleUser roleuser = new PeRoleUser();
		roleuser.setUser_id(ui.getId());
		List<PeRoleUser> roleList = super.getFacade().getPeRoleUserService().getPeRoleUserList(roleuser);// 拿到该用户的所有角色
		if (null != roleList && roleList.size() > 0) {
			for (PeRoleUser role : roleList) {
				if (role.getRole_id() == 30)
					is_fgsgly = true;
				if (role.getRole_id() == 34)
					is_fgszjl = true;
			}
		}

		KonkaOrderMeetingManager entity = new KonkaOrderMeetingManager();

		super.copyProperties(entity, form);
		entity.setIs_del(0);

		if (StringUtils.isNotEmpty(file_status)) {
			entity.setFile_status(Integer.valueOf(file_status));
		} else {
			entity.setFile_status(3);
		}
		if (StringUtils.isNotEmpty(meeting_id)) {
			entity.setMeeting_id(Long.valueOf(meeting_id));
		}

		// entity.getMap().put("leftCustomer", "true");
		entity.getMap().put("YWY", "true");

		// if (StringUtils.isNotBlank(dept_id)) {
		// entity.getMap().put("dept_id", dept_id);
		// entity.getMap().put("user_id", ui.getId());
		// }
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.parseLong(dept_id));
		}

		// 数据级别判断开始
		Long __dept_id = StringUtils.isNotBlank(dept_id) ? Long.valueOf(dept_id) : ui.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				entity.getMap().put("dept_id_start", __dept_id);
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);
			break;
		case 0:
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", ui.getId());
		// 数据级别判断结束

		if (StringUtils.isNotEmpty(customer_like) || StringUtils.isNotEmpty(r3_code_like)) {
			entity.getMap().put("leftCustomer", "leftCustomer");
			entity.getMap().put("customer_like", customer_like);
			entity.getMap().put("r3_code_like", r3_code_like); // 业务员模糊查询字段
		}
		entity.getMap().put("meeting_name_like", meeting_name_like);
		entity.getMap().put("order_money_min", order_money_min);
		entity.getMap().put("order_money_max", order_money_max);
		if (StringUtils.isNotBlank(report_user_name_like)) {
			entity.getMap().put("report_user_name_like", report_user_name_like);
		}

		entity.getMap().put("report_user_job_id", ui.getJob_id());

		entity.getMap().put("charge_person_like", charge_person_like);
		if (StringUtils.isNotBlank(open_date_start)) {
			entity.getMap().put("open_date_start", open_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(open_date_end)) {
			entity.getMap().put("open_date_end", open_date_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(add_meeting_date_start)) {
			entity.getMap().put("add_meeting_date_start", add_meeting_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_meeting_date_end)) {
			entity.getMap().put("add_meeting_date_end", add_meeting_date_end + " 23:59:59");
		}

		Long recordCount = getFacade().getKonkaOrderMeetingManagerService().getKonkaOrderMeetingManagerCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaOrderMeetingManager> entityList = getFacade().getKonkaOrderMeetingManagerService()
				.getKonkaOrderMeetingManagerPaginatedList(entity);

		if (null != entityList && 0 != entityList.size()) {
			for (KonkaOrderMeetingManager meetingManager : entityList) {
				if (ui.getJob_id().equals(meetingManager.getReport_user_job_id()) || is_fgszjl || is_fgsgly
						|| ui.getJob_id().equals(meetingManager.getCharge_person_job_id())) {
					meetingManager.getMap().put("allowUpdate", true);
				}
			}
		}

		request.setAttribute("entityList", entityList);

		if (StringUtils.isNotBlank(export)) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("订货会数据")
					+ ".xls");
			entity.getRow().setCount(recordCount.intValue());
			dynaBean.set("export", export);
			List<KonkaOrderMeetingManager> entityList1 = getFacade().getKonkaOrderMeetingManagerService()
					.getKonkaOrderMeetingManagerPaginatedList(entity);

			if (null != entityList1 && entityList1.size() > 0) {
				for (KonkaOrderMeetingManager c : entityList1) {
					if (null != c.getMeeting_id()) {
						KonkaSpActivityType spa = new KonkaSpActivityType();
						spa.setId(c.getMeeting_id());
						spa = super.getFacade().getKonkaSpActivityTypeService().getKonkaSpActivityType(spa);
						if (null != spa) {
							c.getMap().put("meeting_id", spa.getHd_type());
						}
					}

					// if (null != c.getCharge_person()) {
					// PeProdUser user = new PeProdUser();
					// user.setId(Long.valueOf(c.getCharge_person()));
					// user =
					// super.getFacade().getPeProdUserService().getPeProdUser(user);
					// if (null != user) {
					// c.getMap().put("charge_person", c.getCharge_person());
					// }
					// }

					KonkaOrderMeetingCustomer custorm = new KonkaOrderMeetingCustomer();
					custorm.setLink_id(c.getId());
					List<KonkaOrderMeetingCustomer> custormList = super.getFacade()
							.getKonkaOrderMeetingCustomerService().getKonkaOrderMeetingCustomerList(custorm);
					c.setOrderMeetingCustomerList(custormList);

					KonkaSpMdSail spSail = new KonkaSpMdSail();
					spSail.setLink_id(c.getId());
					List<KonkaSpMdSail> spSailList = super.getFacade().getKonkaSpMdSailService()
							.getKonkaSpMdSailList(spSail);
					c.setKonkaSpMdSailList(spSailList);
				}
			}
			request.setAttribute("entityList1", entityList1);
			return new ActionForward("/admin/KonkaOrderMeetingManager/listForReport.jsp");
		}

		// 分公司
		KonkaDept kd = new KonkaDept();
		if (max_dlevel == 9) {
			kd.setDept_id(0L);
		} else {
			kd.setDept_id(ui.getDept_id());
		}
		kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
		entity.getMap().put("dept_name", kd.getDept_name());
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		// 网点类型
		KonkaCategory kc = new KonkaCategory();
		kc.setC_type(10);
		request.setAttribute("konkaCategoryList", super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc));

		// 会议类型
		KonkaSpActivityType spActivity = new KonkaSpActivityType();
		spActivity.setIs_del(0);
		spActivity.setP_type(1l);
		List<KonkaSpActivityType> spActivityList = getFacade().getKonkaSpActivityTypeService()
				.getKonkaSpActivityTypeList(spActivity);
		request.setAttribute("spActivityList", spActivityList);

		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
		KonkaOrderMeetingManager entity = new KonkaOrderMeetingManager();
		entity.setId(Long.valueOf(id));
		entity.getMap().put("is_assign", 1);
		entity = super.getFacade().getKonkaOrderMeetingManagerService().getKonkaOrderMeetingManager(entity);

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		// 会议类型
		KonkaSpActivityType spActivity = new KonkaSpActivityType();
		spActivity.setId(entity.getMeeting_id());
		spActivity.setIs_del(0);
		spActivity.setP_type(1l);
		spActivity = getFacade().getKonkaSpActivityTypeService().getKonkaSpActivityType(spActivity);
		if (null != spActivity) {
			dynaBean.set("hd_type", spActivity.getHd_type());
		}

		if (null != entity.getCharge_person()) {
			PeProdUser user = new PeProdUser();
			user.setJob_id(entity.getCharge_person());
			user = super.getFacade().getPeProdUserService().getPeProdUser(user);
			if (null != user) {
				dynaBean.set("chargeUser", user.getUser_name());
			}
		}

		// 会议参与客户
		KonkaOrderMeetingCustomer _entity = new KonkaOrderMeetingCustomer();
		_entity.setLink_id(entity.getId());
		List<KonkaOrderMeetingCustomer> konkaOrderMeetingCustomerList = super.getFacade()
				.getKonkaOrderMeetingCustomerService().getKonkaOrderMeetingCustomerList(_entity);
		entity.setOrderMeetingCustomerList(konkaOrderMeetingCustomerList);

		// 附件
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(entity.getId());
		attachment.setLink_tab("KONKA_ORDER_MEETING_MANAGER");
		attachment.setIs_del(0l);
		List<KonkaPeAttachments> konkaPeAttachmentsList = super.getFacade().getKonkaPeAttachmentsService()
				.getKonkaPeAttachmentsList(attachment);
		entity.setPeAttachmentsList(konkaPeAttachmentsList);

		// 指定机型订货
		KonkaSpMdSail spMdSail = new KonkaSpMdSail();
		spMdSail.setLink_id(entity.getId());
		List<KonkaSpMdSail> konkaSpMdSailList = super.getFacade().getKonkaSpMdSailService()
				.getKonkaSpMdSailList(spMdSail);
		entity.setKonkaSpMdSailList(konkaSpMdSailList);
		spMdSail = super.getFacade().getKonkaSpMdSailService().getKonkaSpMdSailForSum(spMdSail);
		if (null != spMdSail && null != spMdSail.getMap()) {
			dynaBean.set("dh_sum", spMdSail.getMap().get("sum_num"));
			dynaBean.set("dh_money", spMdSail.getMap().get("sum_money"));
		}

		entity.setQueryString(super.serialize(request, "str_pks", "method"));
		super.copyProperties(form, entity);

		return mapping.findForward("view");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String remethod = (String) dynaBean.get("mt");

		if (!GenericValidator.isLong(id)) {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
		KonkaOrderMeetingManager entity = new KonkaOrderMeetingManager();
		entity.setId(Long.valueOf(id));
		entity.getMap().put("is_assign", 1);
		entity = super.getFacade().getKonkaOrderMeetingManagerService().getKonkaOrderMeetingManager(entity);

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		if (null != entity.getDept_id()) {
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(entity.getDept_id()); // 查询部门分公司
			if (null == dept_fgs) {
				PeProdUser user = new PeProdUser();
				user.getMap().put("dept_id_in", entity.getDept_id());
				user.getMap().put("cust_id_null", "true");
				user.setIs_del(0);
                // 非分公司的时候，此处的值太大。造成页面卡死
				List<PeProdUser> user_list = super.getFacade().getPeProdUserService().getPeProdUserList(user);
				request.setAttribute("user_list", user_list);
				request.setAttribute("dept_fgs", dept_fgs);
			} else {
				PeProdUser user = new PeProdUser();
				user.getMap().put("dept_id_in", dept_fgs.getDept_id());
				user.getMap().put("cust_id_null", "true");
				user.setIs_del(0);
				List<PeProdUser> user_list = super.getFacade().getPeProdUserService().getPeProdUserList(user);
				request.setAttribute("user_list", user_list);
				request.setAttribute("dept_fgs", dept_fgs);
				dynaBean.set("tradeIndex", entity.getMeeting_sn());
			}
		}

		// 会议类型
		KonkaSpActivityType spActivity = new KonkaSpActivityType();
		spActivity.setIs_del(0);
		spActivity.setP_type(1l);
		spActivity.getMap().put("now_date", new Date());
		List<KonkaSpActivityType> spActivityList = getFacade().getKonkaSpActivityTypeService()
				.getKonkaSpActivityTypeList(spActivity);
		request.setAttribute("spActivityList", spActivityList);

		// 会议参与客户
		KonkaOrderMeetingCustomer _entity = new KonkaOrderMeetingCustomer();
		_entity.setLink_id(entity.getId());
		List<KonkaOrderMeetingCustomer> konkaOrderMeetingCustomerList = super.getFacade()
				.getKonkaOrderMeetingCustomerService().getKonkaOrderMeetingCustomerList(_entity);
		entity.setOrderMeetingCustomerList(konkaOrderMeetingCustomerList);

		// 附件
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(entity.getId());
		attachment.setLink_tab("KONKA_ORDER_MEETING_MANAGER");
		attachment.setIs_del(0l);
		List<KonkaPeAttachments> konkaPeAttachmentsList = super.getFacade().getKonkaPeAttachmentsService()
				.getKonkaPeAttachmentsList(attachment);
		entity.setPeAttachmentsList(konkaPeAttachmentsList);

		// 指定机型订货
		KonkaSpMdSail spMdSail = new KonkaSpMdSail();
		spMdSail.setLink_id(entity.getId());
		spMdSail.setType(20);
		List<KonkaSpMdSail> konkaSpMdSailList = super.getFacade().getKonkaSpMdSailService()
				.getKonkaSpMdSailList(spMdSail);
		entity.setKonkaSpMdSailList(konkaSpMdSailList);
		KonkaSpMdType konkaSpMdType = new KonkaSpMdType();
		konkaSpMdType.setLink_id(entity.getMeeting_id());
		List<KonkaSpMdType> konkaSpMdTypeList = super.getFacade().getKonkaSpMdTypeService()
				.getKonkaSpMdTypeList(konkaSpMdType);
		request.setAttribute("konkaSpMdTypeList", konkaSpMdTypeList);

		entity.setQueryString(super.serialize(request, "str_pks", "method"));
		super.copyProperties(form, entity);

		if (StringUtils.isNotEmpty(remethod)) {
			dynaBean.set("process", remethod);
			dynaBean.set("outcome", remethod);
			return new ActionForward(response.encodeRedirectURL("/admin/KonkaOrderMeetingManager/" + remethod + ".jsp"));
		}

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		if (isCancelled(request)) {
			return list(mapping, form, request, response);
		}
		resetToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String[] r3_codes = request.getParameterValues("r3_code");
		String[] md_names = request.getParameterValues("md_name");
		String[] md_moneys = request.getParameterValues("md_money");
		String[] md_nums = request.getParameterValues("md_num");
		String[] md_memos = request.getParameterValues("md_memo");

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		KonkaOrderMeetingManager entity = new KonkaOrderMeetingManager();
		KonkaOrderMeetingCustomer _entity = null;
		KonkaSpMdSail sailBook = null;
		List<KonkaOrderMeetingCustomer> orderMeetingCustomerList = new ArrayList<KonkaOrderMeetingCustomer>();
		List<KonkaSpMdSail> konkaSpMdSailList = new ArrayList<KonkaSpMdSail>();
		if (null != r3_codes) {
			for (String r3_code : r3_codes) {
				if (StringUtils.isNotEmpty(r3_code)) {
					_entity = new KonkaOrderMeetingCustomer();
					_entity.setR3_code(r3_code);
					_entity.setCustomer((String) dynaBean.get("customer_" + r3_code));
					_entity.setC_name((String) dynaBean.get("c_name_" + r3_code));

					String c_type = (String) dynaBean.get("c_type_" + r3_code);
					if (GenericValidator.isLong(c_type)) {
						_entity.setC_type(Long.valueOf(c_type));
					}
					_entity.setYwy_nmae((String) dynaBean.get("ywy_name_" + r3_code));
					_entity.setYwy_job_id((String) dynaBean.get("ywy_job_id_" + r3_code));
					_entity.setJyb_name((String) dynaBean.get("jyb_name_" + r3_code));
					String jyb_id = (String) dynaBean.get("jyb_id_" + r3_code);
					if (GenericValidator.isLong(jyb_id)) {
						_entity.setJyb_id(Long.valueOf(jyb_id));
					}

					orderMeetingCustomerList.add(_entity);
				}
			}
		}

		if (null != md_names) {
			for (int i = 0; i < md_names.length; i++) {
				if (StringUtils.isNotEmpty(md_names[i])) {
					sailBook = new KonkaSpMdSail();
					sailBook.setType(20);
					sailBook.setMd_name(md_names[i]);
					String num = md_nums[i];
					if (StringUtils.isNotEmpty(num)) {
						sailBook.setNum(new Long(num));
					}
					String money = md_moneys[i];
					if (StringUtils.isNotEmpty(money)) {
						sailBook.setMoney(new BigDecimal(money));
					}
					sailBook.setMemo(md_memos[i]);
					//System.out.println("name=" + md_names[i] + "   num=" + num + "   money=" + money);
					konkaSpMdSailList.add(sailBook);
				}
			}
		}

		super.copyProperties(entity, form);

		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH, true, 0,
				new int[] { 240 });
		List<KonkaPeAttachments> attachmentList = new ArrayList<KonkaPeAttachments>();

		KonkaPeAttachments attachment = null;
		for (UploadFile uploadFile : uploadFileList) {
			attachment = new KonkaPeAttachments();
			attachment.setFile_name(uploadFile.getFileName());
			attachment.setFile_type(uploadFile.getContentType());
			attachment.setFile_size(new Long(uploadFile.getFileSize()));
			attachment.setSave_path(uploadFile.getFileSavePath());
			attachment.setSave_name(uploadFile.getFileSaveName());
			attachment.setIs_del(0l);
			attachment.setLink_tab("KONKA_ORDER_MEETING_MANAGER");
			attachment.setAdd_user_name(ui.getUser_name());
			attachment.setAdd_user_id(ui.getId());
			attachment.setFile_desc(uploadFile.getFormName());
			attachmentList.add(attachment);
		}

		entity.setOrderMeetingCustomerList(orderMeetingCustomerList);
		entity.setPeAttachmentsList(attachmentList);
		entity.setKonkaSpMdSailList(konkaSpMdSailList);

		// 负责人
		if (StringUtils.isNotEmpty(entity.getCharge_person_job_id())) {
			PeProdUser charge = new PeProdUser();
			charge.setJob_id(entity.getCharge_person_job_id());
			charge = super.getFacade().getPeProdUserService().getPeProdUser(charge);
			if (null != charge) {
				entity.setCharge_person(charge.getUser_name());
			}
		}

		if (null == entity.getId()) {
			entity.setIs_del(0);
			super.getFacade().getKonkaOrderMeetingManagerService().createKonkaOrderMeetingManager(entity);
			saveMessage(request, "entity.inserted");
		} else {
			entity.setUpdate_user(ui.getUser_name());
			entity.setUpdate_date(new Date());
			entity.setUpdate_user_job_id(ui.getJob_id());
			super.getFacade().getKonkaOrderMeetingManagerService().modifyKonkaOrderMeetingManager(entity);
			saveMessage(request, "entity.updated");

		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end

		return forward;
	}

	public ActionForward addSdSail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String link_Id = (String) dynaBean.get("meeting_id");
		StringBuffer sb = new StringBuffer("[");

		if (!GenericValidator.isLong(link_Id)) {
			return null;
		}

		KonkaSpMdType entity = new KonkaSpMdType();
		entity.setLink_id(Long.valueOf(link_Id));
		entity.setType(2);
		List<KonkaSpMdType> entityList = getFacade().getKonkaSpMdTypeService().getKonkaSpMdTypeList(entity);
		if (null != entityList && 0 != entityList.size()) {
			for (KonkaSpMdType spType : entityList) {
				sb.append("{\"type\":\"" + String.valueOf(spType.getType()) + "\"" + ",\"md_name\":\""
						+ String.valueOf(spType.getMd_name()) + "\"},");
			}
		}

		sb.append("{\"end\":\"\"}]");
		super.renderJson(response, sb.toString());
		return null;
	}

	/**
	 * Zhang,Chao 订货会无效处理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward enable(ActionMapping mapping, ActionForm form, HttpServletRequest request,// 解除匹配
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		KonkaOrderMeetingManager entity = new KonkaOrderMeetingManager();

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			entity.setId(new Long(id));
			entity.setIs_del(0);
			entity.setFile_status(0);// 设为无效状态
			entity.getMap().put("enable", true);
			entity.setUpdate_user(ui.getUser_name());
			entity.setUpdate_date(new Date());
			entity.setUpdate_user_job_id(ui.getJob_id());
			getFacade().getKonkaOrderMeetingManagerService().modifyKonkaOrderMeetingManager(entity);
			saveMessage(request, "entity.updated");
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

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		KonkaOrderMeetingManager entity = new KonkaOrderMeetingManager();

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			entity.setId(new Long(id));
			entity.setIs_del(1);
			entity.setUpdate_user(ui.getUser_name());
			entity.setUpdate_date(new Date());
			entity.setUpdate_user_job_id(ui.getJob_id());
			getFacade().getKonkaOrderMeetingManagerService().modifyKonkaOrderMeetingManager(entity);
			saveMessage(request, "konka.close.success");
		} else if (!ArrayUtils.isEmpty(pks)) {
			entity.getMap().put("pks", pks);
			entity.setIs_del(1);
			entity.setUpdate_user(ui.getUser_name());
			entity.setUpdate_date(new Date());
			entity.setUpdate_user_job_id(ui.getJob_id());
			getFacade().getKonkaOrderMeetingManagerService().modifyKonkaOrderMeetingManager(entity);
			saveMessage(request, "konka.close.success");
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

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        // if (null == super.checkUserModPopeDom(form, request, "1")) {
        // return super.checkPopedomInvalid(request, response);
        // }
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		request.setAttribute("user", ui);

		KonkaSpActivityType spActivity = new KonkaSpActivityType();
		spActivity.setIs_del(0);
		spActivity.setP_type(1l);
		spActivity.getMap().put("now_date", new Date());
		List<KonkaSpActivityType> spActivityList = getFacade().getKonkaSpActivityTypeService()
				.getKonkaSpActivityTypeList(spActivity);
		request.setAttribute("spActivityList", spActivityList);

		Date now = new Date();
		SimpleDateFormat simp = new SimpleDateFormat("yyyyMMdd");

		Long suqu = super.getFacade().getKonkaOrderMeetingManagerService()
				.getSequenceResult(new KonkaOrderMeetingManager());
		String order_s = suqu.toString();
		if (order_s.length() < 8) {
			int z_num = 8 - order_s.length();
			for (int i = 0; i < z_num; i++) {
				order_s = "0" + order_s;
			}
		}

		if (null != ui.getDept_id()) {
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
			List<PeProdUser> user_list = null;
			if (null == dept_fgs) {
				PeProdUser user = new PeProdUser();
				user.getMap().put("dept_id_in", ui.getDept_id());
				user.getMap().put("cust_id_null", "true");
				user.setIs_del(0);

                // 非分公司的时候，此处的值太大。造成页面卡死
				user_list = super.getFacade().getPeProdUserService().getPeProdUserList(user);
			} else {
				PeProdUser user = new PeProdUser();
				user.getMap().put("dept_id_in", dept_fgs.getDept_id());
				user.getMap().put("cust_id_null", "true");
				user.setIs_del(0);
				user_list = super.getFacade().getPeProdUserService().getPeProdUserList(user);

				dynaBean.set("dept_id", dept_fgs.getDept_id());
				dynaBean.set("dept_name", dept_fgs.getDept_name());
				String order_num = "DH" + dept_fgs.getDept_sn() + simp.format(now) + order_s;
				dynaBean.set("meeting_sn", order_num);
			}
			request.setAttribute("user_list", user_list);
		}

		dynaBean.set("report_user_name", ui.getUser_name());
		dynaBean.set("report_user_job_id", ui.getJob_id());
		dynaBean.set("add_meeting_date", now);
		dynaBean.set("add_date", now);
		dynaBean.set("open_date", now);
		dynaBean.set("outcome", "null");
		dynaBean.set("process", "null");
		return mapping.findForward("input");
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

	/**
	 * 订货会添加参会客户时使用
	 * 
	 * @author GuoJian,Xiao
	 * @date 2014-3-4
	 */
	public ActionForward orderMeetingList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String code_like = (String) dynaBean.get("code_like");
		String dept_id = (String) dynaBean.get("dept_id");
		String c_index = (String) dynaBean.get("c_index");
		String is_sdf = (String) dynaBean.get("is_sdf");
		String customer_name_like = (String) dynaBean.get("customer_name_like");

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		KonkaR3Shop entity = new KonkaR3Shop();
		super.copyProperties(entity, form);
		entity.setIs_del(0L);

		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("dept_id", dept_id);
			entity.getMap().put("user_id", ui.getId());
		}

		// 数据级别判断开始
		Long __dept_id = StringUtils.isNotBlank(dept_id) ? Long.valueOf(dept_id) : ui.getDept_id(); // 默认为当前用户所在部门
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
			entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", ui.getId());

		entity.getMap().put("is_assign", "true");
		entity.getMap().put("leftYWY", "true");
		entity.getMap().put("YWY", "true");
		entity.getMap().put("dept_id_start", __dept_id);
		// entity.setIs_match(Long.valueOf(is_match));
		// 数据级别判断结束

		entity.setCustomer_type(c_index);
		entity.getMap().put("c_index", c_index);
		entity.getMap().put("code_like", code_like);
		entity.getMap().put("is_sdf", is_sdf);
		entity.getMap().put("customer_name_like", customer_name_like);

		Long recordCount = getFacade().getKonkaR3ShopService().getKonkaR3ShopResultListByViewCustomerCount(entity);
		pager.init(recordCount, 100, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService().getKonkaR3ShopResultListByViewCustomerList(
				entity);
		request.setAttribute("entityList", entityList);
		return new ActionForward(response.encodeRedirectURL("/admin/KonkaOrderMeetingManager/orderMeetingList.jsp"));
	}

}