package com.ebiz.mmt.web.struts.manager.admin;

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
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.KonkaActInfo;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileSailData;
import com.ebiz.mmt.domain.KonkaProSq;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class KonkaActInfoAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String c_name_like = (String) dynaBean.get("c_name_like");
		String add_user_name_like = (String) dynaBean.get("add_user_name_like");
		String excel_all = (String) dynaBean.get("excel_all");
		String status = (String) dynaBean.get("status");
		String dept_sn = (String) dynaBean.get("dept_sn");
		String start_time_start = (String) dynaBean.get("start_time_start");
		String start_time_end = (String) dynaBean.get("start_time_end");
		String type_id = (String) dynaBean.get("type_id");

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaActInfo entity = new KonkaActInfo();
		if (StringUtils.isNotBlank(r3_code_like)) {
			entity.getMap().put("r3_code_like", r3_code_like);
		}
		if (StringUtils.isNotBlank(c_name_like)) {
			entity.getMap().put("c_name_like", c_name_like);
		}
		if (StringUtils.isNotBlank(add_user_name_like)) {
			entity.getMap().put("add_user_name_like", add_user_name_like);
		}
		if (StringUtils.isNotBlank(type_id)) {
			entity.setType_id(Long.valueOf(type_id));
		}
		if (StringUtils.isNotBlank(status)) {
			entity.setStatus(Integer.valueOf(status));
		}

		if (StringUtils.isNotBlank(start_time_start)) {
			entity.getMap().put("start_time_start", start_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(start_time_end)) {
			entity.getMap().put("start_time_end", start_time_end + " 23:59:59");
		}

		if (StringUtils.isNotBlank(dept_sn)) {
			entity.setDept_sn(dept_sn);
		}

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		boolean role_id_eq_10 = false;
		for (PeRoleUser __peRoleUser : peRoleUserList) {
			if (__peRoleUser.getRole_id() == 10L) {
				role_id_eq_10 = true;
				break;
			}
		}
		if (!role_id_eq_10) {// 如果不是总部，只能看自己分公司的数据
			if (null != userInfo.getDept_id()) {
				KonkaDept kd = new KonkaDept();
				kd.setDept_id(userInfo.getDept_id());
				kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
				entity.setDept_sn(kd.getDept_sn());
			}
		}

		Long recordCount = getFacade().getKonkaActInfoService().getKonkaActInfoCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaActInfo> entityList = getFacade().getKonkaActInfoService().getKonkaActInfoPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		for (KonkaActInfo konkaActInfo : entityList) {
			// 附件
			Attachment attachment = new Attachment();
			attachment.setLink_id(konkaActInfo.getAct_id());
			attachment.setLink_tab("KONKA_ACT_INFO");
			attachment.setDel_mark(new Short("0"));
			konkaActInfo.getMap().put("attachmentList",
			        getFacade().getAttachmentService().getAttachmentList(attachment));
		}

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			if (recordCount.intValue() > 5000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
				        + "！');history.back();");
				return null;
			}
			// entity.getRow().setCount(recordCount.intValue());
			List<KonkaActInfo> entityList1 = getFacade().getKonkaActInfoService().getKonkaActInfoAndSaleDatasList(
			        entity);
			dynaBean.set("excel_all", excel_all);
			request.setAttribute("allList", entityList1);
		}

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}

		saveToken(request);
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(ui.getDept_id());
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);

		dynaBean.set("dept_id", ui.getDept_id());
		dynaBean.set("dept_name", konkaDept.getDept_name());
		dynaBean.set("add_user_name", ui.getUser_name());

		// 分公司取得
		if (null != konkaDept.getDept_type() && konkaDept.getDept_type() > 2) {
			KonkaDept fgs_dept = super.getSuperiorForDeptType(ui.getDept_id(), 3);
			dynaBean.set("fgs_dept_name", fgs_dept.getDept_name());
		}
		// 当前年月日
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date date = new Date();
		String ym = sdf.format(date);
		dynaBean.set("yymm", ym.substring(2, ym.length()));

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		KonkaCategory kbt = new KonkaCategory();
		kbt.setC_type(15);
		List<KonkaCategory> konkaCategoryList = super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kbt);
		request.setAttribute("konkaCategoryList", konkaCategoryList);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (isCancelled(request)) {
			return list(mapping, form, request, response);
		}
		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}
		resetToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String act_id = (String) dynaBean.get("act_id");

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(ui.getDept_id());
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);

		KonkaActInfo entity = new KonkaActInfo();
		super.copyProperties(entity, form);

		// 附件处理
		// List<UploadFile> uploadFileList = super.uploadFile(form, true, 0, new
		// int[] { 240 });
		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH, true, 0,
				new int[] { 240 });
		List<Attachment> filesAttachmentList = new ArrayList<Attachment>();
		Attachment filesAttachment = null;
		for (UploadFile uploadFile : uploadFileList) {
			filesAttachment = new Attachment();
			filesAttachment.setFile_name(uploadFile.getFileName());
			filesAttachment.setFile_type(uploadFile.getContentType());
			filesAttachment.setFile_size(new Integer(uploadFile.getFileSize()));
			filesAttachment.setSave_path(uploadFile.getFileSavePath());
			filesAttachment.setSave_name(uploadFile.getFileSaveName());
			filesAttachment.setDel_mark(new Short("0"));
			filesAttachmentList.add(filesAttachment);
		}
		entity.setAttachmentList(filesAttachmentList);
		if (StringUtils.isBlank(act_id)) {
			entity.setAdd_user_id(ui.getId());
			entity.setAdd_user_name(ui.getUser_name());
			entity.setDept_sn(konkaDept.getDept_sn());
			String file_no_lm = dynaBean.get("file_no_left") + "" + dynaBean.get("file_no_middle");
			entity.setAct_sn(getFilesMaxNo(file_no_lm));

			getFacade().getKonkaActInfoService().createKonkaActInfo(entity);
			saveMessage(request, "entity.inserted");
		} else {
			entity.setAct_id(new Long(act_id));
			getFacade().getKonkaActInfoService().modifyKonkaActInfo(entity);
			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
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
		String act_id = (String) dynaBean.get("act_id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(act_id) && GenericValidator.isLong(act_id)) {
			KonkaActInfo entity = new KonkaActInfo();
			entity.setAct_id(Long.valueOf(act_id));
			getFacade().getKonkaActInfoService().removeKonkaActInfo(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaActInfo entity = new KonkaActInfo();
			entity.getMap().put("pks", pks);
			for (String xx : pks) {
				entity.setAct_id(Long.valueOf(xx));
				getFacade().getKonkaActInfoService().removeKonkaActInfo(entity);
			}
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

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String act_id = (String) dynaBean.get("act_id");

		if (!GenericValidator.isLong(act_id)) {
			saveError(request, "errors.long", act_id);
			return mapping.findForward("list");
		}

		KonkaActInfo entity = new KonkaActInfo();
		entity.setAct_id(new Long(act_id));
		entity = getFacade().getKonkaActInfoService().getKonkaActInfo(entity);

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_sn(entity.getDept_sn());
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		dynaBean.set("dept_id", konkaDept.getDept_id());

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		entity.setQueryString(super.serialize(request, "act_id", "method"));
		super.copyProperties(form, entity);

		// 附件
		Attachment attachment = new Attachment();
		attachment.setLink_id(new Long(act_id));
		attachment.setLink_tab("KONKA_ACT_INFO");
		attachment.setDel_mark(new Short("0"));
		request.setAttribute("attachmentList", getFacade().getAttachmentService().getAttachmentList(attachment));

		// 销售明细
		KonkaMobileSailData konkaMobileSailData = new KonkaMobileSailData();
		konkaMobileSailData.setCustomer_r3_code(entity.getR3_code());
		konkaMobileSailData.getMap().put("report_date_begin", entity.getStart_date());
		konkaMobileSailData.getMap().put("report_date_end", entity.getEnd_date());

		List<KonkaMobileSailData> entityList = super.getFacade().getKonkaMobileSailDataService()
		        .getKonkaMobileSailDataList(konkaMobileSailData);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String act_id = (String) dynaBean.get("act_id");
		if (!GenericValidator.isLong(act_id)) {
			saveError(request, "errors.long", act_id);
			return mapping.findForward("list");
		}

		KonkaActInfo entity = new KonkaActInfo();
		entity.setAct_id(new Long(act_id));
		entity = getFacade().getKonkaActInfoService().getKonkaActInfo(entity);

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		super.copyProperties(form, entity);

		// 附件
		Attachment attachment = new Attachment();
		attachment.setLink_id(new Long(act_id));
		attachment.setLink_tab("KONKA_ACT_INFO");
		attachment.setDel_mark(new Short("0"));
		request.setAttribute("attachmentList", getFacade().getAttachmentService().getAttachmentList(attachment));

		// 销售明细
		KonkaMobileSailData konkaMobileSailData = new KonkaMobileSailData();
		konkaMobileSailData.setCustomer_r3_code(entity.getR3_code());
		konkaMobileSailData.getMap().put("report_date_begin", entity.getStart_date());
		konkaMobileSailData.getMap().put("report_date_end", entity.getEnd_date());

		List<KonkaMobileSailData> entityList = super.getFacade().getKonkaMobileSailDataService()
		        .getKonkaMobileSailDataList(konkaMobileSailData);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("view");

	}

	// 有效
	public ActionForward valid(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		String act_id = (String) dynaBean.get("act_id");
		if (!GenericValidator.isLong(act_id)) {
			saveError(request, "errors.long", act_id);
			return mapping.findForward("list");
		}

		KonkaActInfo entity = new KonkaActInfo();
		entity.setAct_id(new Long(act_id));
		entity.setStatus(0);
		super.getFacade().getKonkaActInfoService().modifyKonkaActInfo(entity);

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	// 无效
	public ActionForward invalid(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		String act_id = (String) dynaBean.get("act_id");
		if (!GenericValidator.isLong(act_id)) {
			saveError(request, "errors.long", act_id);
			return mapping.findForward("list");
		}

		KonkaActInfo entity = new KonkaActInfo();
		entity.setAct_id(new Long(act_id));
		entity.setStatus(1);
		super.getFacade().getKonkaActInfoService().modifyKonkaActInfo(entity);

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	/**
	 * @param request
	 *            自动生成登录用户所在部门的提交文件编号
	 * @author Cheng,Bing
	 */
	public String getFilesMaxNo(String file_no_lm) {

		Long max_fileno = null;

		KonkaActInfo kd = new KonkaActInfo();
		kd.getMap().put("file_no_lm", file_no_lm);
		Long max_fileno_1 = super.getFacade().getKonkaActInfoService().getKonkaActInfoNoMax(kd);
		max_fileno_1 = max_fileno_1 == null ? 0 : max_fileno_1;

		KonkaProSq kf = new KonkaProSq();
		kf.getMap().put("file_no_lm", file_no_lm);
		Long max_fileno_2 = super.getFacade().getKonkaActInfoService().getKonkaActInfoNoMax(kd);
		max_fileno_2 = max_fileno_2 == null ? 0 : max_fileno_2;

		max_fileno = max_fileno_1 >= max_fileno_2 ? max_fileno_1 : max_fileno_2;
		max_fileno = max_fileno + 1;
		String file_no_r = "";
		if (max_fileno < 1000) {
			file_no_r = "0000".substring(0, 4 - ("" + max_fileno).length()) + max_fileno;
		} else {
			file_no_r = "" + max_fileno;
		}

		return file_no_lm + file_no_r;
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

	public ActionForward getSaleDatas(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String start_date = (String) dynaBean.get("start_date");
		String end_date = (String) dynaBean.get("end_date");
		String r3_code = (String) dynaBean.get("r3_code");

		KonkaMobileSailData entity = new KonkaMobileSailData();
		if (StringUtils.isNotBlank(start_date)) {
			Date d = DateUtils.parseDate(start_date + " 00:00:00", new String[] { "yyy-MM-dd HH:mm:ss" });
			entity.getMap().put("report_date_begin", d);
		}
		if (StringUtils.isNotBlank(end_date)) {
			Date d = DateUtils.parseDate(end_date + " 23:59:59", new String[] { "yyy-MM-dd HH:mm:ss" });
			entity.getMap().put("report_date_end", d);
		}
		if (StringUtils.isNotBlank(r3_code)) {
			entity.setCustomer_r3_code(r3_code);
		}

		List<KonkaMobileSailData> entityList = super.getFacade().getKonkaMobileSailDataService()
		        .getKonkaMobileSailDataList(entity);

		StringBuffer sb = new StringBuffer("[");
		for (KonkaMobileSailData t : entityList) {
			sb.append("{\"model_name\":\"" + String.valueOf(t.getModel_name()) + "\",");
			sb.append("\"num\":\"" + String.valueOf(t.getNum()) + "\",");
			sb.append("\"single_price\":\"" + String.valueOf(t.getSingle_price()) + "\",");
			sb.append("\"all_price\":\"" + StringEscapeUtils.escapeJavaScript(t.getAll_price().toString()) + "\"},");

		}
		sb.append("{\"end\":\"\"}]");
		super.renderJson(response, sb.toString());
		return null;
	}

}
