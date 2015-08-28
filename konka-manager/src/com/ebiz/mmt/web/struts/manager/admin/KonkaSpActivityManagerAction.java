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
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.ChannelDataImport;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileSailData;
import com.ebiz.mmt.domain.KonkaOrderMeetingManager;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaSpActivityManager;
import com.ebiz.mmt.domain.KonkaSpActivityType;
import com.ebiz.mmt.domain.KonkaSpMdSail;
import com.ebiz.mmt.domain.KonkaSpMdType;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author OuYang,BaiYang
 * @version 2014-2-8
 */

public class KonkaSpActivityManagerAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {// 左边菜单栏导航树
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);// 导航
		DynaBean dynaBean = (DynaBean) form;

		PePdModel entity = new PePdModel();
		entity.setIs_del(0);
		List<PePdModel> pePdModelList = getFacade().getPePdModelService().getPePdModelList(entity);
		request.setAttribute("pePdModelList", pePdModelList);

		// 上报人、分公司
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		dynaBean.set("report_user_name", ui.getUser_name());
		dynaBean.set("report_user_job_id", ui.getJob_id());
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
			request.setAttribute("dept_fgs", dept_fgs);

			// 单据编号
			if (null != dept_fgs) {
				PeProdUser user = new PeProdUser();
				user.getMap().put("dept_id_in", dept_fgs.getDept_id());
				user.getMap().put("cust_id_null", "true");
				user.setIs_del(0);
				List<PeProdUser> user_list = super.getFacade().getPeProdUserService().getPeProdUserList(user);
				request.setAttribute("user_list", user_list);
				request.setAttribute("dept_fgs", dept_fgs);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String order_num = "CX" + dept_fgs.getDept_sn() + sdf.format(new Date()) + order_s;
				dynaBean.set("sp_sn", order_num);
			}
		}

		dynaBean.set("add_sp_date", new Date());

		// 活动类型
		KonkaSpActivityType spActivity = new KonkaSpActivityType();
		spActivity.setIs_del(0);
		spActivity.setP_type(0l);
		spActivity.getMap().put("now_date", new Date());
		List<KonkaSpActivityType> spActivityList = getFacade().getKonkaSpActivityTypeService()
				.getKonkaSpActivityTypeList(spActivity);
		if (spActivityList == null || spActivityList.size() < 1) {
			super.renderJavaScript(response, "alert('当前时间没有活动类型！');history.back();");
			return null;
		}
		request.setAttribute("spActivityList", spActivityList);

		// super.copyProperties(form, entity);
		dynaBean.set("queryString", super.serialize(request, "id", "method"));
		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String export = (String) dynaBean.get("export");
		String hd_id = (String) dynaBean.get("hd_id");
		String dept_id = (String) dynaBean.get("dept_id");
		String hd_status = (String) dynaBean.get("hd_status");
		String file_status = (String) dynaBean.get("file_status");
		// String hd_type_like = (String) dynaBean.get("hd_type_like");
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(15);

		// 活动类型
		KonkaSpActivityType activityType = new KonkaSpActivityType();
		activityType.setIs_del(0);
		activityType.setP_type(0l);
		List<KonkaSpActivityType> activityTypeList = getFacade().getKonkaSpActivityTypeService()
				.getKonkaSpActivityTypeList(activityType);
		request.setAttribute("activityTypeList", activityTypeList);

		KonkaSpActivityManager entity = new KonkaSpActivityManager();

		entity.setIs_del(0);
		entity.getMap().put("main_customer_like", dynaBean.get("main_customer_like"));
		entity.getMap().put("main_r3_code_like", dynaBean.get("main_r3_code_like"));
		entity.getMap().put("sp_sn_like", dynaBean.get("sp_sn_like"));
		entity.getMap().put("report_user_name_like", dynaBean.get("report_user_name_like"));
		entity.getMap().put("charge_person_like", dynaBean.get("charge_person_like"));
		entity.getMap().put("total_sail_money_min", dynaBean.get("total_sail_money_min"));
		entity.getMap().put("total_sail_money_max", dynaBean.get("total_sail_money_max"));

		entity.getMap().put("bf_s_date_k", dynaBean.get("bf_s_date_k"));
		entity.getMap().put("bf_s_date_j", dynaBean.get("bf_s_date_j"));
		entity.getMap().put("bf_e_date_k", dynaBean.get("bf_e_date_k"));
		entity.getMap().put("bf_e_date_j", dynaBean.get("bf_e_date_j"));

		entity.getMap().put("add_sp_date_k", dynaBean.get("add_sp_date_k"));
		entity.getMap().put("add_sp_date_j", dynaBean.get("add_sp_date_j"));

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

		if (StringUtils.isNotBlank(file_status)) {
			entity.setFile_status(Integer.valueOf(file_status));
		}
		if (StringUtils.isNotBlank(hd_id)) {
			entity.setHd_id(Long.valueOf(hd_id));
		}
		if (StringUtils.isNotBlank(hd_status)) {
			entity.setHd_status(Integer.valueOf(hd_status));
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.parseLong(dept_id));
		}

		Long recordCount = getFacade().getKonkaSpActivityManagerService().getKonkaSpActivityManagerCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaSpActivityManager> entityList = getFacade().getKonkaSpActivityManagerService()
				.getKonkaSpActivityManagerPaginatedList(entity);

		if (null != entityList && 0 != entityList.size()) {
			for (KonkaSpActivityManager meetingManager : entityList) {
				// 判断操作权限
				if (ui.getJob_id().equals(meetingManager.getReport_user_job_id()) || "34".equals(ui.getRole_id())
						|| ui.getJob_id().equals(meetingManager.getCharge_person_job_id())) {
					meetingManager.getMap().put("allowUpdate", true);
				}
			}
		}

		request.setAttribute("entityList", entityList);

		if (StringUtils.isNotBlank(export)) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("促销活动数据")
					+ ".xls");
			entity.getRow().setCount(recordCount.intValue());
			dynaBean.set("export", export);
			List<KonkaSpActivityManager> entityList1 = getFacade().getKonkaSpActivityManagerService()
					.getKonkaSpActivityManagerListForExcel(entity);

			if (null != entityList && 0 != entityList.size()) {
				for (KonkaSpActivityManager meetingManager : entityList1) {
					// 添加指定机型销售，只在导出的时候显示出来
					if (meetingManager != null && meetingManager.getId() != null) {
						KonkaSpMdSail spMdSail = new KonkaSpMdSail();
						spMdSail.setLink_id(meetingManager.getId());
						spMdSail.setType(10);
						List<KonkaSpMdSail> konkaSpMdSailList = super.getFacade().getKonkaSpMdSailService()
								.getKonkaSpMdSailList(spMdSail);
						meetingManager.setKonkaSpMdSailList(konkaSpMdSailList);
					}
				}
			}

			request.setAttribute("entityList1", entityList1);
			return new ActionForward("/admin/KonkaSpActivityManager/listForReport.jsp");
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

		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		resetToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String[] md_names = request.getParameterValues("md_name");
		String[] md_moneys = request.getParameterValues("md_money");
		String[] md_nums = request.getParameterValues("md_num");
		String[] md_memos = request.getParameterValues("md_memo");

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		List<KonkaSpMdSail> konkaSpMdSailList = new ArrayList<KonkaSpMdSail>();
		KonkaSpActivityManager entity = new KonkaSpActivityManager();

		super.copyProperties(entity, form);
		KonkaSpMdSail sailBook = null;

		// 指定机销售
		if (null != md_names) {
			for (int i = 0; i < md_names.length; i++) {
				if (StringUtils.isNotEmpty(md_names[i])) {
					sailBook = new KonkaSpMdSail();
					sailBook.setType(10);
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

		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.MD_PATH, true, 0, new int[] { 240 });
		List<KonkaPeAttachments> attachmentList = new ArrayList<KonkaPeAttachments>();
		// 文件上传
		KonkaPeAttachments attachment = null;
		for (UploadFile uploadFile : uploadFileList) {
			attachment = new KonkaPeAttachments();
			attachment.setFile_name(uploadFile.getFileName());
			attachment.setFile_type(uploadFile.getContentType());
			attachment.setFile_size(new Long(uploadFile.getFileSize()));
			attachment.setSave_path(uploadFile.getFileSavePath());
			attachment.setSave_name(uploadFile.getFileSaveName());
			attachment.setIs_del(0l);
			attachment.setLink_tab("KONKA_SP_ACTIVITY_MANAGER");
			attachment.setAdd_user_name(ui.getUser_name());
			attachment.setAdd_user_id(ui.getId());
			attachment.setFile_desc(uploadFile.getFormName());
			attachmentList.add(attachment);
		}
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
			getFacade().getKonkaSpActivityManagerService().createKonkaSpActivityManager(entity);// 注：指定机销售在此处添加
			saveMessage(request, "entity.inserted");
		} else {
			entity.setUpdate_date(new Date());
			entity.setUpdate_user(ui.getId().toString());
			entity.setUpdate_user_job_id(ui.getJob_id());
			getFacade().getKonkaSpActivityManagerService().modifyKonkaSpActivityManager(entity);// 注：指定机销售在此处添加
			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		// pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
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
		// String hd_id = (String) dynaBean.get("hd_id");

		KonkaSpActivityManager entity = new KonkaSpActivityManager();
		entity.setIs_del(0);
		entity.setId(Long.valueOf(id));
		entity = getFacade().getKonkaSpActivityManagerService().getKonkaSpActivityManager(entity);

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		dynaBean.set("add_date", new Date());

		if (null != entity.getDept_id()) {
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(entity.getDept_id()); // 查询部门分公司
			if (null == dept_fgs) {
				PeProdUser user = new PeProdUser();
				user.getMap().put("dept_id_in", entity.getDept_id());
				user.getMap().put("cust_id_null", "true");
				user.setIs_del(0);
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
			}
		}

		// 附件
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(entity.getId());
		attachment.setLink_tab("KONKA_SP_ACTIVITY_MANAGER");
		attachment.setIs_del(0l);
		List<KonkaPeAttachments> konkaPeAttachmentsList = super.getFacade().getKonkaPeAttachmentsService()
				.getKonkaPeAttachmentsList(attachment);
		entity.setPeAttachmentsList(konkaPeAttachmentsList);

		// 活动类型
		KonkaSpActivityType spActivity = new KonkaSpActivityType();
		spActivity.setIs_del(0);
		spActivity.setP_type(0l);
		spActivity.getMap().put("now_date", new Date());
		List<KonkaSpActivityType> spActivityList = getFacade().getKonkaSpActivityTypeService()
				.getKonkaSpActivityTypeList(spActivity);
		if (spActivityList == null || spActivityList.size() < 1) {
			super.renderJavaScript(response, "window.onload=function(){alert('当前时间活动类型已过期！');history.back();}");
			return null;
		}
		request.setAttribute("spActivityList", spActivityList);

		// 指定机型订货
		KonkaSpMdSail spMdSail = new KonkaSpMdSail();
		spMdSail.setLink_id(entity.getId());
		spMdSail.setType(10);
		List<KonkaSpMdSail> konkaSpMdSailList = super.getFacade().getKonkaSpMdSailService()
				.getKonkaSpMdSailList(spMdSail);
		entity.setKonkaSpMdSailList(konkaSpMdSailList);
		KonkaSpMdType konkaSpMdType = new KonkaSpMdType();
		konkaSpMdType.setLink_id(entity.getHd_id());
		List<KonkaSpMdType> konkaSpMdTypeList = super.getFacade().getKonkaSpMdTypeService()
				.getKonkaSpMdTypeList(konkaSpMdType);
		request.setAttribute("konkaSpMdTypeList", konkaSpMdTypeList);

		// 考核結算金額
		String main_r3_code = entity.getMain_r3_code();
		Date bf_e_date = entity.getBf_e_date();
		Date zb_s_date = entity.getZb_s_date();

		String other_r3_codes = entity.getOther_r3_code();
		// other_r3_codes.substring(0, other_r3_codes.length() - 1);
		if (StringUtils.isNotBlank(other_r3_codes)) {
			other_r3_codes = other_r3_codes.substring(0, other_r3_codes.length() - 1);
		}

		if (null != bf_e_date && StringUtils.isNotBlank(main_r3_code) && StringUtils.isNotBlank(other_r3_codes)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date kh_date = new Date(sdf.parse(sdf.format(bf_e_date)).getTime() + 30 * 24 * 60 * 60 * 1000);

			SimpleDateFormat sdf_bf = new SimpleDateFormat("yyyyMM");

			// 主要客户
			KonkaMobileSailData konkaMobileSailData = new KonkaMobileSailData();
			konkaMobileSailData.setIs_del(0);
			konkaMobileSailData.setCustomer_r3_code(main_r3_code);
			konkaMobileSailData.getMap().put("startime", sdf.format(bf_e_date));
			konkaMobileSailData.getMap().put("endtime", sdf.format(kh_date));
			konkaMobileSailData = getFacade().getKonkaMobileSailDataService().getKonkaMobileSailDataForTotal(
					konkaMobileSailData);
			if (konkaMobileSailData != null && konkaMobileSailData.getMap() != null) {
				request.setAttribute("total_sail_money", konkaMobileSailData.getMap().get("sum_all_price"));
				request.setAttribute("total_sail_num", konkaMobileSailData.getMap().get("sum_num"));
			}
			// 本月结算金额(爆发期结束当月)
			KonkaMobileSailData sailDataMonth = new KonkaMobileSailData();
			sailDataMonth.setIs_del(0);
			sailDataMonth.setCustomer_r3_code(main_r3_code);

			//sailDataMonth.getMap().put("bf_e_date_like", sdf_bf.format(bf_e_date));
			//sailDataMonth = getFacade().getKonkaMobileSailDataService().getKonkaMobileSailDataForTotal(sailDataMonth);
			/*if (null != sailDataMonth && null != sailDataMonth.getMap()) {
				request.setAttribute("month_sail_money", sailDataMonth.getMap().get("sum_all_price"));
				request.setAttribute("month_sail_num", sailDataMonth.getMap().get("sum_num"));
			}*/
			
			
			BigDecimal channelDatemoney=new BigDecimal("0"),channelDateSum=new BigDecimal("0");
			ChannelDataImport channelDate = new ChannelDataImport();
			channelDate.getMap().put("column", "column_12");// 查询数量
			channelDate.setColumn_1(main_r3_code);// 送达方

				channelDate.getMap().put("startDate", sdf.format(bf_e_date));

				channelDate.getMap().put("endDate_lt", sdf.format(kh_date));

			if (getFacade().getChannelDataImportService().getChannelDataSumByPdId(channelDate) != null) {
				  channelDateSum = getFacade().getChannelDataImportService().getChannelDataSumByPdId(channelDate);
			}

			
			 channelDate = new ChannelDataImport();
			channelDate.getMap().put("column", "column_14");// 查询数量
			channelDate.setColumn_1(main_r3_code);// 送达方

				channelDate.getMap().put("startDate", sdf.format(bf_e_date));

				channelDate.getMap().put("endDate_lt", sdf.format(kh_date));

			if (getFacade().getChannelDataImportService().getChannelDataSumByPdId(channelDate) != null) {
				  channelDatemoney
				 = getFacade().getChannelDataImportService().getChannelDataSumByPdId(channelDate);
			}
			
			if (null != sailDataMonth && null != sailDataMonth.getMap()) {
				request.setAttribute("month_sail_money", channelDatemoney);
				request.setAttribute("month_sail_num", channelDateSum);
			}
			
			// 零售总金额（准备期起 至 爆发期止）
			KonkaMobileSailData sailDataSum = new KonkaMobileSailData();
			sailDataSum.setIs_del(0);
			sailDataSum.setCustomer_r3_code(main_r3_code);
			sailDataSum.getMap().put("startime", sdf.format(zb_s_date));
			sailDataSum.getMap().put("endtime", sdf.format(bf_e_date));
			sailDataSum = getFacade().getKonkaMobileSailDataService().getKonkaMobileSailDataForTotal(sailDataSum);
			if (null != sailDataSum && null != sailDataSum.getMap()) {
				request.setAttribute("sum_sail_money", sailDataSum.getMap().get("sum_all_price"));
				request.setAttribute("sum_sail_num", sailDataSum.getMap().get("sum_num"));
			}
		}

		PePdModel pePdModel = new PePdModel();
		pePdModel.setIs_del(0);
		List<PePdModel> pePdModelList = getFacade().getPePdModelService().getPePdModelList(pePdModel);
		request.setAttribute("pePdModelList", pePdModelList);

		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);

		if (StringUtils.isNotEmpty(remethod)) {
			dynaBean.set("process", remethod);
			dynaBean.set("result", remethod);
			return new ActionForward(response.encodeRedirectURL("/admin/KonkaSpActivityManager/" + remethod + ".jsp"));
		}

		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String remethod = (String) dynaBean.get("mt");

		KonkaSpActivityManager entity = new KonkaSpActivityManager();
		entity.setIs_del(0);
		entity.setId(Long.valueOf(id));
		entity = getFacade().getKonkaSpActivityManagerService().getKonkaSpActivityManager(entity);

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
			}
		}

		// 附件
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(entity.getId());
		attachment.setLink_tab("KONKA_SP_ACTIVITY_MANAGER");
		attachment.setIs_del(0l);
		List<KonkaPeAttachments> konkaPeAttachmentsList = super.getFacade().getKonkaPeAttachmentsService()
				.getKonkaPeAttachmentsList(attachment);
		entity.setPeAttachmentsList(konkaPeAttachmentsList);

		// 活动类型
		KonkaSpActivityType spActivity = new KonkaSpActivityType();
		spActivity.setIs_del(0);
		spActivity.setP_type(0l);
		spActivity.setId(entity.getHd_id());
		// spActivity.getMap().put("now_date", new Date());
		KonkaSpActivityType spActivityname = getFacade().getKonkaSpActivityTypeService().getKonkaSpActivityType(
				spActivity);
		if (spActivityname != null && null != spActivityname.getHd_type()) {
			request.setAttribute("hd_type", spActivityname.getHd_type());
		}
		// 指定机型销售
		KonkaSpMdSail spMdSail = new KonkaSpMdSail();
		spMdSail.setLink_id(entity.getId());
		List<KonkaSpMdSail> konkaSpMdSailList = super.getFacade().getKonkaSpMdSailService()
				.getKonkaSpMdSailList(spMdSail);
		entity.setKonkaSpMdSailList(konkaSpMdSailList);
		spMdSail = super.getFacade().getKonkaSpMdSailService().getKonkaSpMdSailForSum(spMdSail);
		if (spMdSail != null && null != spMdSail.getMap()) {
			request.setAttribute("dd_money_sum", spMdSail.getMap().get("sum_money"));
			request.setAttribute("dd_num_sum", spMdSail.getMap().get("sum_num"));
		}

		// 考核結算金額
		String main_r3_code = entity.getMain_r3_code();
		Date bf_e_date = entity.getBf_e_date();
		Date zb_s_date = entity.getZb_s_date();

		String other_r3_codes = entity.getOther_r3_code();
		// other_r3_codes.substring(0, other_r3_codes.length() - 1);
		if (StringUtils.isNotBlank(other_r3_codes)) {
			other_r3_codes = other_r3_codes.substring(0, other_r3_codes.length() - 1);
		}

		if (null != bf_e_date && StringUtils.isNotBlank(main_r3_code) && StringUtils.isNotBlank(other_r3_codes)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date kh_date = new Date(sdf.parse(sdf.format(bf_e_date)).getTime() + 30 * 24 * 60 * 60 * 1000);

			SimpleDateFormat sdf_bf = new SimpleDateFormat("yyyyMM");

			// 主要客户
			KonkaMobileSailData konkaMobileSailData = new KonkaMobileSailData();
			konkaMobileSailData.setIs_del(0);
			konkaMobileSailData.setCustomer_r3_code(main_r3_code);
			konkaMobileSailData.getMap().put("startime", sdf.format(bf_e_date));
			konkaMobileSailData.getMap().put("endtime", sdf.format(kh_date));
			konkaMobileSailData = getFacade().getKonkaMobileSailDataService().getKonkaMobileSailDataForTotal(
					konkaMobileSailData);
			if (konkaMobileSailData != null && konkaMobileSailData.getMap() != null) {
				request.setAttribute("total_sail_money", konkaMobileSailData.getMap().get("sum_all_price"));
				request.setAttribute("total_sail_num", konkaMobileSailData.getMap().get("sum_num"));
			}
			// 本月结算金额(爆发期结束当月)
			KonkaMobileSailData sailDataMonth = new KonkaMobileSailData();
			sailDataMonth.setIs_del(0);
			sailDataMonth.setCustomer_r3_code(main_r3_code);

			//sailDataMonth.getMap().put("bf_e_date_like", sdf_bf.format(bf_e_date));
			//sailDataMonth = getFacade().getKonkaMobileSailDataService().getKonkaMobileSailDataForTotal(sailDataMonth);
			/*if (null != sailDataMonth && null != sailDataMonth.getMap()) {
				request.setAttribute("month_sail_money", sailDataMonth.getMap().get("sum_all_price"));
				request.setAttribute("month_sail_num", sailDataMonth.getMap().get("sum_num"));
			}*/
			
			
			BigDecimal channelDatemoney=new BigDecimal("0"),channelDateSum=new BigDecimal("0");
			ChannelDataImport channelDate = new ChannelDataImport();
			channelDate.getMap().put("column", "column_12");// 查询数量
			channelDate.setColumn_1(main_r3_code);// 送达方

				channelDate.getMap().put("startDate", sdf.format(bf_e_date));

				channelDate.getMap().put("endDate_lt", sdf.format(kh_date));

			if (getFacade().getChannelDataImportService().getChannelDataSumByPdId(channelDate) != null) {
				  channelDateSum = getFacade().getChannelDataImportService().getChannelDataSumByPdId(channelDate);
			}

			
			 channelDate = new ChannelDataImport();
			channelDate.getMap().put("column", "column_14");// 查询jin e
			channelDate.setColumn_1(main_r3_code);// 送达方

				channelDate.getMap().put("startDate", sdf.format(bf_e_date));

				channelDate.getMap().put("endDate_lt", sdf.format(kh_date));

			if (getFacade().getChannelDataImportService().getChannelDataSumByPdId(channelDate) != null) {
				  channelDatemoney
				 = getFacade().getChannelDataImportService().getChannelDataSumByPdId(channelDate);
			}
			
			if (null != sailDataMonth && null != sailDataMonth.getMap()) {
				request.setAttribute("month_sail_money", channelDatemoney.divide(new BigDecimal("10000")));
				request.setAttribute("month_sail_num", channelDateSum);
			}
			
			// 零售总金额（准备期起 至 爆发期止）
			KonkaMobileSailData sailDataSum = new KonkaMobileSailData();
			sailDataSum.setIs_del(0);
			sailDataSum.setCustomer_r3_code(main_r3_code);
			sailDataSum.getMap().put("startime", sdf.format(zb_s_date));
			sailDataSum.getMap().put("endtime", sdf.format(bf_e_date));
			sailDataSum = getFacade().getKonkaMobileSailDataService().getKonkaMobileSailDataForTotal(sailDataSum);
			if (null != sailDataSum && null != sailDataSum.getMap()) {
				request.setAttribute("sum_sail_money", sailDataSum.getMap().get("sum_all_price"));
				request.setAttribute("sum_sail_num", sailDataSum.getMap().get("sum_num"));
			}
		}

		PePdModel pePdModel = new PePdModel();
		pePdModel.setIs_del(0);
		List<PePdModel> pePdModelList = getFacade().getPePdModelService().getPePdModelList(pePdModel);
		request.setAttribute("pePdModelList", pePdModelList);
		super.copyProperties(form, entity);
		return mapping.findForward("view");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaSpActivityManager entity = new KonkaSpActivityManager();
			entity.setIs_del(0);
			entity.setId(Long.valueOf(id));
			getFacade().getKonkaSpActivityManagerService().removeKonkaSpActivityManager(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaSpActivityManager entity = new KonkaSpActivityManager();
			entity.getMap().put("pks", pks);
			getFacade().getKonkaSpActivityManagerService().removeKonkaSpActivityManager(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward enable(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaSpActivityManager entity = new KonkaSpActivityManager();
			entity.setId(new Long(id));
			entity.setIs_del(0);
			entity.setFile_status(0);// 设为无效状态
			entity.getMap().put("enable", true);
			entity.setUpdate_user(ui.getUser_name());
			entity.setUpdate_date(new Date());
			entity.setUpdate_user_job_id(ui.getJob_id());
			getFacade().getKonkaSpActivityManagerService().modifyKonkaSpActivityManager(entity);
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

	/**
	 * 促销活动管理获取R3编码客户用
	 * 
	 * @author BaiYang,OuYang
	 * @date 2014-2-8
	 */
	public ActionForward spActivityManagerForMainR3List(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

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

		entity.getMap().put("is_assign", "true");
		entity.getMap().put("leftYWY", "true");
		entity.getMap().put("YWY", "true");
		entity.getMap().put("is_sdf", is_sdf);
		entity.getMap().put("dept_id_start", __dept_id);
		entity.getMap().put("customer_name_like", customer_name_like);
		// entity.setIs_match(Long.valueOf(is_match));
		// 数据级别判断结束

		// dynaBean.set("is_sdf", 0);
		dynaBean.set("is_del", 0);

		entity.setCustomer_type(c_index);
		entity.getMap().put("c_index", c_index);
		entity.getMap().put("code_like", code_like);

		Long recordCount = getFacade().getKonkaR3ShopService().getKonkaR3ShopResultListByViewCustomerCount(entity);
		pager.init(recordCount, 10000, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService().getKonkaR3ShopResultListByViewCustomerList(
				entity);

		request.setAttribute("entityList", entityList);

		return new ActionForward("/admin/KonkaSpActivityManager/MainR3List.jsp");
	}

	public ActionForward spActivityManagerForOtherR3List(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

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

		entity.getMap().put("is_assign", "true");
		entity.getMap().put("leftYWY", "true");
		entity.getMap().put("YWY", "true");
		entity.getMap().put("is_sdf", is_sdf);
		entity.getMap().put("dept_id_start", __dept_id);
		entity.getMap().put("customer_name_like", customer_name_like);
		// entity.setIs_match(Long.valueOf(is_match));
		// 数据级别判断结束

		dynaBean.set("is_del", 0);

		entity.setCustomer_type(c_index);
		entity.getMap().put("c_index", c_index);
		entity.getMap().put("code_like", code_like);

		Long recordCount = getFacade().getKonkaR3ShopService().getKonkaR3ShopResultListByViewCustomerCount(entity);
		pager.init(recordCount, 10000, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService().getKonkaR3ShopResultListByViewCustomerList(
				entity);
		request.setAttribute("entityList", entityList);
		return new ActionForward(response.encodeRedirectURL("/admin/KonkaSpActivityManager/OtherR3List.jsp"));
	}

	public ActionForward getQueryNames(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String key_word = (String) dynaBean.get("key_word");
		// String is_sdf = (String)
		// request.getSession().getAttribute("selects");
		String dept_id = (String) dynaBean.get("dept_id");
		String c_index = (String) dynaBean.get("c_index");

		KonkaR3Shop entity = new KonkaR3Shop();
		// super.copyProperties(entity, form);
		entity.setIs_del(0L);

		/*
		 * if (StringUtils.isNotBlank(key_word)) {
		 * entity.getMap().put("code_like", key_word); } if
		 * (GenericValidator.isInt(is_sdf)) {
		 * entity.setIs_sdf(Integer.valueOf(is_sdf)); } else {
		 * dynaBean.set("is_sdf", "0"); }
		 */

		if (StringUtils.isNotBlank(key_word)) {
			entity.getMap().put("customer_name_like", key_word);
		}

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

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
		entity.getMap().put("is_assign", "true");
		entity.getMap().put("leftYWY", "true");
		entity.getMap().put("YWY", "true");
		// entity.getMap().put("dept_id_start", __dept_id);
		// entity.setIs_match(Long.valueOf(is_match));
		entity.getMap().put("session_user_id", ui.getId());
		// 数据级别判断结束

		dynaBean.set("is_sdf", "0");

		entity.setCustomer_type(c_index);
		entity.getMap().put("c_index", c_index);

		Long recordCount = getFacade().getKonkaR3ShopService().getKonkaR3ShopResultListByViewCustomerCount(entity);
		pager.init(recordCount, 10000, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService().getKonkaR3ShopResultListByViewCustomerList(
				entity);

		StringBuffer sb = new StringBuffer("[");

		for (KonkaR3Shop t : entityList) {
			String ids = t.getMap().get("l4_dept_id") + "_" + t.getMap().get("l4_dept_name") + "_";
			ids += t.getMap().get("ywy_user_name") + "_" + t.getMap().get("customer_name") + "_";
			ids += t.getMap().get("customer_type") + "_" + t.getMap().get("customer_type_name") + "_"
					+ t.getMap().get("job_id");

			sb.append("{\"id\":\"" + ids + "\",");
			sb.append("\"name\":\"" + StringEscapeUtils.escapeJavaScript(t.getR3_code()) + "\"},");
		}

		sb.append("{\"end\":\"\"}]");

		super.renderJson(response, sb.toString());

		return null;
	}

	/**
	 * 促销活动管理获取:责任人
	 * 
	 * @author BaiYang,OuYang
	 * @date 2014-2-10
	 */
	public ActionForward chargePerson(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		// String dept_id = (String) dynaBean.get("dept_id");

		// PeProdUser ui = (PeProdUser)
		// request.getSession().getAttribute(Constants.USER_INFO);

		PeProdUser entity = new PeProdUser();
		super.copyProperties(entity, form);
		entity.setIs_del(0);

		Long recordCount = getFacade().getPeProdUserService().getPeProdUserCountForShowInfo(entity);
		pager.init(recordCount, 100, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<PeProdUser> entityList = getFacade().getPeProdUserService().getPeProdUserPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		return new ActionForward(response.encodeRedirectURL("/admin/KonkaSpActivityManager/PersonList.jsp"));
	}

	/**
	 * 促销活动管理:指定机型销售
	 * 
	 * @author BaiYang,OuYang
	 * @return
	 * @date 2014-2-11
	 */

	public ActionForward addSdSail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String hd_id = (String) dynaBean.get("hd_id");

		if (StringUtils.isNotBlank(hd_id)) {
			KonkaSpMdType entity = new KonkaSpMdType();
			entity.setLink_id(Long.valueOf(hd_id));
			List<KonkaSpMdType> entityList = getFacade().getKonkaSpMdTypeService().getKonkaSpMdTypeList(entity);

			StringBuffer sb = new StringBuffer("[");
			for (KonkaSpMdType t : entityList) {
				sb.append("{\"md_type\":\"" + t.getType() + "\",");
				sb.append("\"md_name\":\"" + StringEscapeUtils.escapeJavaScript(t.getMd_name()) + "\"},");
			}
			sb.append("{\"end\":\"\"}]");
			super.renderJson(response, sb.toString());
		}

		return null;
	}

	public ActionForward deleteFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");

		if (StringUtils.isNotBlank(id) && GenericValidator.isLong(id)) {
			KonkaPeAttachments entity = new KonkaPeAttachments();
			entity.setId(new Long(id));
			getFacade().getKonkaPeAttachmentsService().removeKonkaPeAttachments(entity);
			saveMessage(request, "entity.deleted");
		}

		super.renderText(response, "success");
		return null;
	}
	
    /*
     * 业务通附件删除
     */
	public ActionForward deleteFile1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");

		if (StringUtils.isNotBlank(id) && GenericValidator.isLong(id)) {
			Attachment entity = new Attachment();
			entity.setId(new Long(id));
			getFacade().getAttachmentService().removeAttachment(entity);
			saveMessage(request, "entity.deleted");
		}

		super.renderText(response, "success");
		return null;
	}
	
	/**
	 * 促销活动管理就算:考核结算金额
	 * 
	 * @author BaiYang,OuYang
	 * @return
	 * @date 2014-2-11
	 */

	public BigDecimal caluate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		// 考核期结算金
		String main_r3_code = (String) dynaBean.get("main_r3_code");
		String bf_e_date = (String) dynaBean.get("bf_e_date");

		if (StringUtils.isNotBlank(main_r3_code) && StringUtils.isNotBlank(bf_e_date)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date kh_date = new Date(sdf.parse(bf_e_date).getTime() + 30 * 24 * 60 * 60 * 1000);

			KonkaMobileSailData konkaMobileSailData = new KonkaMobileSailData();
			konkaMobileSailData.setIs_del(0);
			konkaMobileSailData.setCustomer_r3_code(main_r3_code);
			konkaMobileSailData.getMap().put("startime", kh_date);
			konkaMobileSailData.getMap().put("endtime", sdf.parse(bf_e_date));
			konkaMobileSailData = getFacade().getKonkaMobileSailDataService().getKonkaMobileSailDataForTotal(
					konkaMobileSailData);
			if (null != konkaMobileSailData && null != konkaMobileSailData.getMap()) {
				request.setAttribute("total_sail_money", konkaMobileSailData.getMap().get("sum_all_price"));
				request.setAttribute("total_sail_num", konkaMobileSailData.getMap().get("sum_num"));
				if (null != konkaMobileSailData.getMap().get("sum_all_price")) {
					return new BigDecimal(konkaMobileSailData.getMap().get("sum_all_price").toString());
				}
			}
		}
		return new BigDecimal(0);
	}
}