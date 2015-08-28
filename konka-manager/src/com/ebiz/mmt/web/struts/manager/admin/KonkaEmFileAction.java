package com.ebiz.mmt.web.struts.manager.admin;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.ebiz.mmt.domain.KonkaEmFile;
import com.ebiz.mmt.domain.KonkaEmFileContent;
import com.ebiz.mmt.domain.KonkaEmFileReceiveUser;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Liu,ZhiXiang
 * @version 2013-09-28
 */
public class KonkaEmFileAction extends BaseAction {
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
		String file_no_like = (String) dynaBean.get("file_no_like");
		String file_title_like = (String) dynaBean.get("file_title_like");
		String file_type = (String) dynaBean.get("file_type");
		String add_time_start = (String) dynaBean.get("add_time_start");
		String add_time_end = (String) dynaBean.get("add_time_end");

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_le_29 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() <= 29L) {
				role_id_le_29 = true;
			}
		}

		boolean role_id_eq_8100 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() == 8100L) {
				role_id_eq_8100 = true;
			}
		}

		// 角色判断处理
		if (role_id_le_29 || role_id_eq_8100) { // 康佳总部或总部商用电视工作人员

		} else {
			String msg = super.getMessage(request, "popedom.check.invalid");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaEmFile entity = new KonkaEmFile();
		if (StringUtils.isNotBlank(file_no_like)) {
			entity.getMap().put("file_no_like", file_no_like);
		}
		if (StringUtils.isNotBlank(file_title_like)) {
			entity.getMap().put("file_title_like", file_title_like);
		}
		if (StringUtils.isNotBlank(file_type)) {
			entity.setFile_type(new Integer(file_type));
		}
		if (StringUtils.isNotBlank(add_time_start)) {
			entity.getMap().put("add_time_start", add_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_time_end)) {
			entity.getMap().put("add_time_end", add_time_end + " 23:59:59");
		}
		entity.setIs_del(0);

		Long recordCount = getFacade().getKonkaEmFileService().getKonkaEmFileCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaEmFile> entityList = getFacade().getKonkaEmFileService().getKonkaEmFilePaginatedList(entity);
		request.setAttribute("entityList", entityList);

		entity.getRow().setCount(null);// 去除数量限制
		List<KonkaEmFile> excelList = getFacade().getKonkaEmFileService().getKonkaEmFileList(entity);
		request.setAttribute("excelList", excelList);

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

		// 当前年月日
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date date = new Date();
		String ym = sdf.format(date);
		dynaBean.set("yymm", ym.substring(2, ym.length()));
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

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		KonkaEmFile entity = new KonkaEmFile();
		super.copyProperties(entity, form);

		String content = (String) dynaBean.get("content");
		String link_user_ids = (String) dynaBean.get("link_user_ids");
		String file_xf_type = (String) dynaBean.get("file_xf_type");// 下发对象选择

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
			// filesAttachment.setLink_tab("KONKA_EM_FILE");
			filesAttachmentList.add(filesAttachment);

		}
		entity.setAttachmentList(filesAttachmentList);

		// 文件内容处理
		if (StringUtils.isNotBlank(content)) {
			KonkaEmFileContent kc = new KonkaEmFileContent();
			kc.setFile_content(content);
			entity.setKonkaEmFileContent(kc);
		}

		// 下发对象处理
		if (StringUtils.isNotBlank(file_xf_type) && file_xf_type.equals("0")) {
			// 全部工程机角色用户
			PeProdUser peProdUser = new PeProdUser();
			peProdUser.setIs_del(new Integer(0));
			peProdUser.getMap().put("role_id", "8000");
			List<PeProdUser> peProdUserList = getFacade().getPeProdUserService().getPeProdUserList(peProdUser);
			if (null != peProdUserList && peProdUserList.size() > 0) {
				List<KonkaEmFileReceiveUser> konkaEmFileReceiveUserList = new ArrayList<KonkaEmFileReceiveUser>();
				for (int i = 0; i < peProdUserList.size(); i++) {
					KonkaEmFileReceiveUser ku = new KonkaEmFileReceiveUser();
					ku.setFile_receive_id(peProdUserList.get(i).getId());
					konkaEmFileReceiveUserList.add(ku);
				}
				entity.setKonkaEmFileReceiveUserList(konkaEmFileReceiveUserList);
			}
		} else if (StringUtils.isNotBlank(file_xf_type) && file_xf_type.equals("2")
				&& StringUtils.isNotBlank(link_user_ids)) {
			String[] array = link_user_ids.split(",");
			if (null != array && array.length > 0) {
				List<KonkaEmFileReceiveUser> konkaEmFileReceiveUserList = new ArrayList<KonkaEmFileReceiveUser>();
				for (int i = 0; i < array.length; i++) {
					KonkaEmFileReceiveUser ku = new KonkaEmFileReceiveUser();
					ku.setFile_receive_id(new Long(array[i]));
					konkaEmFileReceiveUserList.add(ku);
				}
				entity.setKonkaEmFileReceiveUserList(konkaEmFileReceiveUserList);
			}
		}

		if (null == entity.getId()) {
			entity.setAdd_time(new Date());
			entity.setAdd_user_id(ui.getId());
			entity.setAdd_user_name(ui.getUser_name());

			getFacade().getKonkaEmFileService().createKonkaEmFileIncludeAll(entity);
			// super.createSysOperLog(request, "DOC_INFO", entity.getId(),
			// mod_id, "添加", BeanUtils.describe(entity)
			// .toString());
			saveMessage(request, "entity.inserted");
		} else if (null != entity) {
			getFacade().getKonkaEmFileService().modifyKonkaEmFileIncludeAll(entity);
			// super.createSysOperLog(request, "DOC_INFO", entity.getId(),
			// mod_id, "修改", BeanUtils.describe(entity)
			// .toString());
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

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaEmFile entity = new KonkaEmFile();
		entity.setId(new Long(id));
		entity = getFacade().getKonkaEmFileService().getKonkaEmFile(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		// 文件内容
		KonkaEmFileContent kc = new KonkaEmFileContent();
		kc.setLink_id(new Long(id));
		List<KonkaEmFileContent> kcList = super.getFacade().getKonkaEmFileContentService()
				.getKonkaEmFileContentList(kc);
		if (null != kcList && kcList.size() > 0) {
			kc = kcList.get(0);
			request.setAttribute("file_content", kc.getFile_content());
		}

		// 附件
		Attachment attachment = new Attachment();
		attachment.setLink_id(new Long(id));
		attachment.setLink_tab("KONKA_EM_FILE");
		attachment.setDel_mark(new Short("0"));
		request.setAttribute("attachmentList", getFacade().getAttachmentService().getAttachmentList(attachment));

		// 公件下发对象
		KonkaEmFileReceiveUser ku = new KonkaEmFileReceiveUser();
		ku.setLink_id(entity.getId());
		List<KonkaEmFileReceiveUser> kuList = super.getFacade().getKonkaEmFileReceiveUserService()
				.getKonkaEmFileReceiveUserList(ku);

		if (null != kuList && kuList.size() > 0) {
			Long[] user_ids = new Long[kuList.size()];
			String[] user_names = new String[kuList.size()];
			if (null != kuList && kuList.size() > 0) {
				for (int i = 0; i < kuList.size(); i++) {
					user_ids[i] = kuList.get(i).getFile_receive_id();
					// user_names[i] = kuList.get(i).getReceive_user();
				}
				PeProdUser peProdUser = new PeProdUser();
				String sumIds = StringUtils.join(user_ids, ",");

				peProdUser.getMap().put("ids", sumIds);
				List<PeProdUser> peProdUserList = getFacade().getPeProdUserService().getPeProdUserList(peProdUser);
				if (null != peProdUserList && peProdUserList.size() > 0) {
					// 防止异常数据报错
					int num = kuList.size();
					if (peProdUserList.size() < kuList.size()) {
						num = peProdUserList.size();
					}
					for (int i = 0; i < num; i++) {
						PeProdUser p = peProdUserList.get(i);
						user_names[i] = p.getDepartment() == null ? p.getReal_name() : (p.getDepartment() + "("
								+ p.getReal_name() + ")");
					}
				}
			}
			dynaBean.set("user_ids", StringUtils.join(user_ids, ",") + ",");// 为了和页面保持一致,需在之后加,
			logger.info("=====> user_ids is {}", StringUtils.join(user_ids, ","));
			dynaBean.set("user_names", StringUtils.join(user_names, ","));
		}

		return mapping.findForward("input");
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
			KonkaEmFile entity = new KonkaEmFile();
			entity.setId(new Long(id));
			entity.setIs_del(1);
			getFacade().getKonkaEmFileService().modifyKonkaEmFile(entity);
			// super.createSysOperLog(request, "DOC_INFO", entity.getId(),
			// mod_id, "删除", BeanUtils.describe(entity)
			// .toString());
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaEmFile entity = new KonkaEmFile();
			entity.getMap().put("pks", pks);
			for (String xx : pks) {
				entity.setId(new Long(xx));
				entity.setIs_del(1);
				getFacade().getKonkaEmFileService().modifyKonkaEmFile(entity);
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

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		// HttpSession session = request.getSession();
		// UserInfo ui = (UserInfo) session.getAttribute(Keys.ADMIN_USER);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		// String mod_id = (String) dynaBean.get("mod_id");
		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaEmFile entity = new KonkaEmFile();
		entity.setId(new Long(id));
		entity = getFacade().getKonkaEmFileService().getKonkaEmFile(entity);

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		super.copyProperties(form, entity);

		// 文件内容
		KonkaEmFileContent kc = new KonkaEmFileContent();
		kc.setLink_id(new Long(id));
		List<KonkaEmFileContent> kcList = super.getFacade().getKonkaEmFileContentService()
				.getKonkaEmFileContentList(kc);
		if (null != kcList && kcList.size() > 0) {
			kc = kcList.get(0);
			request.setAttribute("file_content", kc.getFile_content());
		}

		// 附件
		Attachment attachment = new Attachment();
		attachment.setLink_id(new Long(id));
		attachment.setLink_tab("KONKA_EM_FILE");
		attachment.setDel_mark(new Short("0"));
		request.setAttribute("attachmentList", getFacade().getAttachmentService().getAttachmentList(attachment));

		// 公件下发对象
		KonkaEmFileReceiveUser ku = new KonkaEmFileReceiveUser();
		ku.setLink_id(entity.getId());
		List<KonkaEmFileReceiveUser> kuList = super.getFacade().getKonkaEmFileReceiveUserService()
				.getKonkaEmFileReceiveUserList(ku);

		if (null != kuList && kuList.size() > 0) {
			Long[] user_ids = new Long[kuList.size()];
			String[] user_names = new String[kuList.size()];
			if (null != kuList && kuList.size() > 0) {
				for (int i = 0; i < kuList.size(); i++) {
					user_ids[i] = kuList.get(i).getFile_receive_id();
					// user_names[i] = kuList.get(i).getReceive_user();
				}
				PeProdUser peProdUser = new PeProdUser();
				String sumIds = StringUtils.join(user_ids, ",");

				peProdUser.getMap().put("ids", sumIds);
				List<PeProdUser> peProdUserList = getFacade().getPeProdUserService().getPeProdUserList(peProdUser);
				if (null != peProdUserList && peProdUserList.size() > 0) {
					// 防止异常数据报错
					int num = kuList.size();
					if (peProdUserList.size() < kuList.size()) {
						num = peProdUserList.size();
					}
					for (int i = 0; i < num; i++) {
						PeProdUser p = peProdUserList.get(i);
						user_names[i] = p.getDepartment() == null ? p.getReal_name() : (p.getDepartment() + "("
								+ p.getReal_name() + ")");
					}
				}

			}
			dynaBean.set("user_ids", StringUtils.join(user_ids, ",") + ",");// 为了和页面保持一致,需在之后加,
			logger.info("=====> user_ids is {}", StringUtils.join(user_ids, ","));
			dynaBean.set("user_names", StringUtils.join(user_names, ","));
		}
		return mapping.findForward("view");

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

	public ActionForward createCode(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String file_type = request.getParameter("file_type");
		String code = "";
		if (StringUtils.isNotBlank(file_type)) {
			KonkaEmFile entity = new KonkaEmFile();
			entity.setFile_type(new Integer(file_type));

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			String day_first = df.format(calendar.getTime());// 当月第一天

			calendar.setTime(df.parse(day_first));
			calendar.add(Calendar.MONTH, 1);
			calendar.add(Calendar.DATE, -1);
			String day_last = df.format(calendar.getTime());// 当月最后一天
			entity.getMap().put("add_time_start", day_first + " 00:00:00");
			entity.getMap().put("add_time_end", day_last + " 23:59:59");

			Long count = getFacade().getKonkaEmFileService().getKonkaEmFileCount(entity);

			code = day_first.substring(2, 4) + day_first.substring(5, 7);
			count += 1L;
			DecimalFormat decf = new DecimalFormat("00000");
			code = code + decf.format(count);
		}

		super.renderText(response, code);
		return null;
	}
}