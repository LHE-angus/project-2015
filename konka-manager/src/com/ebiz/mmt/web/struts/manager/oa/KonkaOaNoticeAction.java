package com.ebiz.mmt.web.struts.manager.oa;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
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

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaoaCategory;
import com.ebiz.mmt.domain.KonkaoaDocInfo;
import com.ebiz.mmt.domain.KonkaoaDocRecipient;
import com.ebiz.mmt.domain.KonkaoaFilesProperty;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Hu,Hao
 * @version 2013-12-17
 */
public class KonkaOaNoticeAction extends BaseMmtoaAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		saveToken(request);

		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String doc_type = (String) dynaBean.get("doc_type");

		// 登录用户信息
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		// 部门信息
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(ui.getDept_id());
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
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

		dynaBean.set("doc_type", doc_type);
		dynaBean.set("receive_type", "0");

		request.setAttribute("is_add", "2");

		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String doc_type = (String) dynaBean.get("doc_type");

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		KonkaoaDocInfo entity = new KonkaoaDocInfo();
		super.copyProperties(entity, dynaBean);
		if (null == entity.getReceive_type()) {
		}
		if (null == entity.getIs_del()) {
			entity.setIs_del(0);
			dynaBean.set("is_del", "0");
		}

		if (StringUtils.isBlank(doc_type))
			doc_type = "0";
		
		entity.setDoc_type(Integer.valueOf(doc_type));
//		entity.setDoc_type(0);
		entity.getMap().put("user_id", userInfo.getId());

		Long recordCount = getFacade().getKonkaoaDocInfoService().getKonkaoaDocInfoCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaoaDocInfo> entityList = getFacade().getKonkaoaDocInfoService().getKonkaoaDocInfoPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		
		dynaBean.set("doc_type", doc_type);
		
		return mapping.findForward("list");
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
		String doc_type = (String) dynaBean.get("doc_type");

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		KonkaoaDocInfo entity = new KonkaoaDocInfo();
		super.copyProperties(entity, form);

		String link_user_ids = (String) dynaBean.get("link_user_ids");
		String link_users = (String) dynaBean.get("link_users");
		String link_dept_ids = (String) dynaBean.get("link_dept_ids");
		String link_depts = (String) dynaBean.get("link_depts");

		String[] file_types = request.getParameterValues("file_types");
		if (null != file_types && file_types.length > 0) {
			String file_type = StringUtils.join(file_types, ",");
			file_type = "," + file_type + ",";
			entity.setFile_type(file_type);
		}

		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OA_PATH, true, 0, new int[] { 240 });
		List<KonkaPeAttachments> filesAttachmentList = new ArrayList<KonkaPeAttachments>();
		KonkaPeAttachments filesAttachment = null;
		for (UploadFile uploadFile : uploadFileList) {
			filesAttachment = new KonkaPeAttachments();
			filesAttachment.setFile_name(uploadFile.getFileName());
			filesAttachment.setFile_type(uploadFile.getContentType());
			filesAttachment.setFile_size(new Long(uploadFile.getFileSize()));
			filesAttachment.setSave_path(uploadFile.getFileSavePath());
			filesAttachment.setSave_name(uploadFile.getFileSaveName());
			filesAttachment.setIs_del(0l);
			filesAttachment.setLink_tab(uploadFile.getFormName());
			filesAttachment.setAdd_user_name(ui.getUser_name());
			filesAttachment.setAdd_user_id(ui.getId());
			filesAttachmentList.add(filesAttachment);

		}
		entity.setAttachmentList(filesAttachmentList);

		if (StringUtils.isNotBlank(link_user_ids) && StringUtils.isNotBlank(link_users)) {
			entity.getMap().put("link_user_ids", link_user_ids);
			entity.getMap().put("link_users", link_users);
		}
		if (StringUtils.isNotBlank(link_dept_ids) && StringUtils.isNotBlank(link_depts)) {
			entity.getMap().put("link_dept_ids", link_dept_ids);
			entity.getMap().put("link_depts", link_depts);
		}

		if (null == entity.getId()) {
			entity.setAdd_time(new Date());
			entity.setAdd_user_id(ui.getId());
			entity.setAdd_user_name(ui.getUser_name());

			// 登录用户所在部门的提交文件编号最大值
			String file_no_lm = dynaBean.get("file_no_left") + "" + dynaBean.get("file_no_middle");
			entity.setFile_no(getFilesMaxNo(file_no_lm));

			getFacade().getKonkaoaDocInfoService().createKonkaoaDocInfo(entity);

			saveMessage(request, "entity.inserted");
		} else if (null != entity) {
			getFacade().getKonkaoaDocInfoService().modifyKonkaoaDocInfo(entity);

			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&doc_type=" + doc_type);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		saveToken(request);
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaoaDocInfo entity = new KonkaoaDocInfo();
		entity.setId(new Long(id));
		entity = getFacade().getKonkaoaDocInfoService().getKonkaoaDocInfo(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		String file_type = entity.getFile_type();
		String[] file_types = StringUtils.split(file_type, ",");

		ArrayList<KonkaoaFilesProperty> filesProperty11List = new ArrayList<KonkaoaFilesProperty>();

		if (file_types != null) {
			for (int i = 0; i < file_types.length; i++) {

				if (file_types[i] != null) {
					KonkaoaFilesProperty filesProperty = new KonkaoaFilesProperty();
					filesProperty.setC_index(Long.valueOf(file_types[i]));
					filesProperty11List.add(filesProperty);
				}
			}
		}
		// dynaBean.set("file_types", file_types);
		request.setAttribute("filesProperty11List", filesProperty11List);

		// 附件
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(new Long(id));
		request.setAttribute("attachmentList", getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachmentsList(
				attachment));

		if (null != entity.getReceive_type() && entity.getReceive_type() == 1) {
			// 公文下发接收人
			KonkaoaDocRecipient docRecipient = new KonkaoaDocRecipient();
			docRecipient.setReceive_type(0);// 接收类型为：人
			docRecipient.setLink_id(entity.getId());
			docRecipient.setReceive_user_type(0);
			List<KonkaoaDocRecipient> drList = super.getFacade().getKonkaoaDocRecipientService()
					.getKonkaoaDocRecipientList(docRecipient);

			Long[] user_ids = new Long[drList.size()];
			String[] user_names = new String[drList.size()];
			if (null != drList && drList.size() > 0) {
				for (int i = 0; i < drList.size(); i++) {
					user_ids[i] = drList.get(i).getReceive_id();
					user_names[i] = drList.get(i).getReceive_user();
				}
			}
			dynaBean.set("user_ids", StringUtils.join(user_ids, ",") + ",");
			dynaBean.set("user_names", StringUtils.join(user_names, ","));

			docRecipient.setReceive_user_type(1);
			drList = super.getFacade().getKonkaoaDocRecipientService().getKonkaoaDocRecipientList(docRecipient);

			Long[] dept_ids = new Long[drList.size()];
			String[] dept_names = new String[drList.size()];
			if (null != drList && drList.size() > 0) {
				for (int i = 0; i < drList.size(); i++) {
					dept_ids[i] = drList.get(i).getReceive_id();
					dept_names[i] = drList.get(i).getReceive_user();
				}
			}
			dynaBean.set("dept_ids", StringUtils.join(dept_ids, ",") + ",");
			dynaBean.set("dept_names", StringUtils.join(dept_names, ","));
		}
		return mapping.findForward("input");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// if (null == super.checkUserModPopeDom(form, request, "4")) {
		// return super.checkPopedomInvalid(request, response);
		// }

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaoaDocInfo entity = new KonkaoaDocInfo();
			entity.setId(new Long(id));
			entity.setIs_del(1);
			getFacade().getKonkaoaDocInfoService().modifyKonkaoaDocInfo(entity);

			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaoaDocInfo entity = new KonkaoaDocInfo();
			entity.getMap().put("pks", pks);
			for (String xx : pks) {
				entity.setId(new Long(xx));
				entity.setIs_del(1);
				getFacade().getKonkaoaDocInfoService().modifyKonkaoaDocInfo(entity);
			}
			entity.setIs_del(1);
			getFacade().getKonkaoaDocInfoService().modifyKonkaoaDocInfo(entity);
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
		// setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaoaDocInfo entity = new KonkaoaDocInfo();
		entity.setId(new Long(id));
		entity = getFacade().getKonkaoaDocInfoService().getKonkaoaDocInfo(entity);

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		// 更新浏览次数，需要判断是全部用户，还是指定用户？注：receive_type 0:全部用户，1：指定用户
		// if (entity.getReceive_type() == 1) {
		KonkaoaDocRecipient dr = new KonkaoaDocRecipient();
		// dr.setReceive_id(ui.getId());// 崩溃，更新浏览次数，更新接收人id干吗？
		dr.setLink_id(Long.valueOf(id));
		dr.setIs_view(1);
		super.getFacade().getKonkaoaDocRecipientService().modifyKonkaoaDocRecipient(dr);
		// }

		KonkaoaCategory category = new KonkaoaCategory();
		category.setIs_del(0);

		super.copyProperties(form, entity);

		String file_type = entity.getFile_type();
		String[] file_types = StringUtils.split(file_type, ",");

		request.setAttribute("file_types", file_types);

		// 附件
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(new Long(id));
		request.setAttribute("attachmentList", getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachmentsList(
				attachment));

		if (null != entity.getReceive_type() && entity.getReceive_type() == 1) {
			// 公文下发接收人
			KonkaoaDocRecipient docRecipient = new KonkaoaDocRecipient();
			docRecipient.setReceive_type(0);
			docRecipient.setLink_id(entity.getId());
			docRecipient.setReceive_user_type(0);// 接收类型为：人
			List<KonkaoaDocRecipient> drList = super.getFacade().getKonkaoaDocRecipientService()
					.getKonkaoaDocRecipientList(docRecipient);

			Long[] user_ids = new Long[drList.size()];
			String[] user_names = new String[drList.size()];
			if (null != drList && drList.size() > 0) {
				for (int i = 0; i < drList.size(); i++) {
					user_ids[i] = drList.get(i).getReceive_id();
					user_names[i] = drList.get(i).getReceive_user();
				}
			}
			dynaBean.set("user_ids", StringUtils.join(user_ids, ","));
			dynaBean.set("user_names", StringUtils.join(user_names, ","));

			docRecipient.setReceive_user_type(1);// 接收类型为：部门
			drList = super.getFacade().getKonkaoaDocRecipientService().getKonkaoaDocRecipientList(docRecipient);

			Long[] dept_ids = new Long[drList.size()];
			String[] dept_names = new String[drList.size()];
			if (null != drList && drList.size() > 0) {
				for (int i = 0; i < drList.size(); i++) {
					dept_ids[i] = drList.get(i).getReceive_id();
					dept_names[i] = drList.get(i).getReceive_user();
				}
			}
			dynaBean.set("mod_id", mod_id);
			dynaBean.set("dept_ids", StringUtils.join(dept_ids, ","));
			dynaBean.set("dept_names", StringUtils.join(dept_names, ","));
		}

		return mapping.findForward("view");

	}

	public ActionForward checkFileNo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String file_no = request.getParameter("file_no");
		String id = request.getParameter("id");
		KonkaoaDocInfo docInfo = new KonkaoaDocInfo();

		if (StringUtils.isNotBlank(id)) {
			docInfo.getMap().put("id", Long.valueOf(id));
		}
		docInfo.setFile_no(file_no);
		docInfo.setIs_del(0);
		Long count = super.getFacade().getKonkaoaDocInfoService().getKonkaoaDocInfoCount(docInfo);
		String flag = "";

		if (count == 0) {
			flag = "0"; // user not exits
		} else {
			flag = "1"; // already exits
		}
		super.renderJson(response, flag);
		return null;
	}

	public ActionForward open(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		KonkaPeAttachments entity = new KonkaPeAttachments();
		entity.setLink_id(new Long(id));
		entity = super.getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachments(entity);

		File f;
		int i = 0;
		byte[] b = new byte[1024];

		response.reset();

		String fileName = entity.getFile_name();
		response.setContentType(entity.getFile_type());
		response.setCharacterEncoding("UTF-8");
		fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "inline; filename=" + new String(fileName.getBytes("UTF-8"), "GBK"));

		String ctxDir = getServlet().getServletContext().getRealPath(String.valueOf(File.separatorChar));
		if (!ctxDir.endsWith(String.valueOf(File.separatorChar))) {
			ctxDir = ctxDir + File.separatorChar;
		}

		String docName = new String(entity.getSave_path().getBytes("utf-8"));

		f = new File(ctxDir + "/" + docName);
		// f = new File("D:\\Program Files\\tt.txt");
		// 读取文件
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));

		OutputStream out = response.getOutputStream();
		response.flushBuffer();

		while ((i = br.read(b)) > 0)
			out.write(b, 0, i);
		out.close();

		return null;
	}

}
