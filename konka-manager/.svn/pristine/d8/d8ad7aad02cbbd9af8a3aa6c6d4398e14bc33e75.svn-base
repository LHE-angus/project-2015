package com.ebiz.mmt.web.struts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaExpenseClaims;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaoaCategory;
import com.ebiz.mmt.domain.KonkaoaDocInfo;
import com.ebiz.mmt.domain.KonkaoaEventInfo;
import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.KonkaoaFilesAuditAgentUser;
import com.ebiz.mmt.domain.KonkaoaFilesAuditNode;
import com.ebiz.mmt.domain.KonkaoaFilesProperty;
import com.ebiz.mmt.domain.KonkaoaSsuedDocument;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.util.DESPlus;

public class OaFileSearchAction extends MobileListAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list1(mapping, form, request, response);
	}

	// 查询我审核的文件start
	public ActionForward list1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// super.encodeCharacterForGetMethod(dynaBean, request);

		String user_id = (String) dynaBean.get("user_id");
		String userpass = (String) dynaBean.get("userpass");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

		if (StringUtils.isBlank(user_id) && StringUtils.isBlank(userpass)) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		PeProdUser ui = new PeProdUser();

		String username = "";
		if (user_id != null) {
			ui.setId(Long.valueOf(user_id));
			ui.setPass_word(new DESPlus().encrypt(userpass));
			ui = getFacade().getPeProdUserService().getPeProdUser(ui);
		} else {
			username = request.getParameter("username");
			ui = checkUser(username, userpass,android_version,ios_version);
		}
		if (null == ui) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		int i = 0;
		List<KonkaoaEventInfo> list = new ArrayList<KonkaoaEventInfo>();

		List<Long> agentAuditFileIds = null;

		/**
		 * @version Build 2010-12-30
		 * @desc 文件代审批功能实现
		 */
		// 先查当前用户帮哪些用户代审文件，将被代理审批的用户查出来
		KonkaoaFilesAuditAgentUser faau = new KonkaoaFilesAuditAgentUser();
		faau.setIs_del(0);
		faau.setAgent_user_id(ui.getId());
		faau.getMap().put("expired_date", "true");
		List<KonkaoaFilesAuditAgentUser> filesAuditAgentUserList = getFacade().getKonkaoaFilesAuditAgentUserService()
				.getKonkaoaFilesAuditAgentUserList(faau);
		if (null != filesAuditAgentUserList && filesAuditAgentUserList.size() > 0) {
			Long[] agent_user_ids = new Long[filesAuditAgentUserList.size()];
			for (i = 0; i < filesAuditAgentUserList.size(); i++) {
				agent_user_ids[i] = filesAuditAgentUserList.get(i).getUser_id();
			}

            // 查出被代理用户的需要审批的文件列表
            KonkaoaFiles files = new KonkaoaFiles();
            files.setIs_del(0);
            files.getMap().put("gt_file_status", 0);
            files.getMap().put("lt_file_status", 2);
            files.getMap().put("cur_audit_user_ids", agent_user_ids);
            List<KonkaoaFiles> filesList = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesList(files);

			// 将查到的文件列表的文件属性查出，与审批权限表进行比对
			if (null != filesList && filesList.size() > 0) {
				agentAuditFileIds = new ArrayList<Long>();
				for (KonkaoaFiles _files : filesList) {
					KonkaoaFilesAuditAgentUser _faau = new KonkaoaFilesAuditAgentUser();
					_faau.setUser_id(_files.getCur_audit_user_id());
					_faau.setAgent_user_id(ui.getId());
					_faau.getMap().put("link_id", _files.getId());
					Long count = super.getFacade().getKonkaoaFilesAuditAgentUserService()
							.getKonkaoaAgentFilesAuditPopedomCount(_faau);
					if (count == 0) {// 有审批权限的
						agentAuditFileIds.add(_files.getId());
					}
				}
			}
		}

		KonkaoaFiles entity = new KonkaoaFiles();
        entity.getMap().put("gt_file_status", 0);
        entity.getMap().put("lt_file_status", 2);
        entity.getMap().put("agentAuditFileIds", agentAuditFileIds);

		entity.setIs_del(0);
		entity.setCur_audit_user_id(ui.getId());

		// entity.getRow().setFirst(0);
		// entity.getRow().setCount(5);
		Long recordCount = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesCountForAudit(entity);

		String page = request.getParameter("page");
		String forward = (String) dynaBean.get("forward");
		int currentPage = 1;
		int pageSize = 5;
		if (!StringUtils.isEmpty(page)) {
			currentPage = (Integer.parseInt(page)) + (Integer.parseInt(forward));
		}
		entity.getRow().setFirst((currentPage - 1) * pageSize);
		entity.getRow().setCount(pageSize);

		if (recordCount > 0) {
			dynaBean.set("currentPage", currentPage);

			List<KonkaoaFiles> cList = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesPaginatedListForAudit(
					entity);

			for (KonkaoaFiles t : cList) {
				i++;
				KonkaoaEventInfo _t = new KonkaoaEventInfo();
				_t.setId(t.getId());

				_t.setEnterDate(t.getSubmit_datetime());
				_t.setEventiltle(t.getFile_title());
				_t.setSequence(i);
				_t.setFromPerson(t.getSubmit_user());
				if (t.getFile_type() == 0) {
					_t.setEventType("文件提交");
				} else if (t.getFile_type() == 1) {
					_t.setEventType("费用申请");
				}

				_t.setEventDo("<a href='OaFileSearch.do?method=view1&id=" + t.getId() + "&username="
						+ ui.getUser_name() + "&user_id=" + ui.getId() + "&userpass=" + userpass + "'><img src=\""
						+ request.getContextPath() + "/mobile/images/search.jpg\" width=\"49\" height=\"30\" /></a>");

				String url = (_t.getEventDo().split("href='")[1]).split("'>")[0];
				_t.getMap().put("url", url);
				list.add(_t);
			}

			request.setAttribute("entityList", list);
		}
		return new ActionForward("/mobile/oaFileSearch/list1.jsp");
	}

	public ActionForward getCount1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		// super.encodeCharacterForGetMethod(dynaBean, request);

		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		String user_id = (String) dynaBean.get("user_id");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser ui = new PeProdUser();
		if (user_id != null) {
			ui.setId(Long.valueOf(user_id));
			ui.setPass_word(new DESPlus().encrypt(userpass));
			ui = getFacade().getPeProdUserService().getPeProdUser(ui);
		} else {
			username = request.getParameter("username");
			ui =checkUser(username, userpass,android_version,ios_version);
		}

		if (null == ui) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		int i = 0;
		List<Long> agentAuditFileIds = null;

		/**
		 * @version Build 2010-12-30
		 * @desc 文件代审批功能实现
		 */
		// 先查当前用户帮哪些用户代审文件，将被代理审批的用户查出来
		KonkaoaFilesAuditAgentUser faau = new KonkaoaFilesAuditAgentUser();
		faau.setIs_del(0);
		faau.setAgent_user_id(ui.getId());
		faau.getMap().put("expired_date", "true");
		List<KonkaoaFilesAuditAgentUser> filesAuditAgentUserList = getFacade().getKonkaoaFilesAuditAgentUserService()
				.getKonkaoaFilesAuditAgentUserList(faau);
		if (null != filesAuditAgentUserList && filesAuditAgentUserList.size() > 0) {
			Long[] agent_user_ids = new Long[filesAuditAgentUserList.size()];
			for (i = 0; i < filesAuditAgentUserList.size(); i++) {
				agent_user_ids[i] = filesAuditAgentUserList.get(i).getUser_id();
			}

            // 查出被代理用户的需要审批的文件列表
            KonkaoaFiles files = new KonkaoaFiles();
            files.setIs_del(0);
            files.getMap().put("gt_file_status", 0);
            files.getMap().put("lt_file_status", 2);
            files.getMap().put("cur_audit_user_ids", agent_user_ids);
            List<KonkaoaFiles> filesList = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesList(files);

			// 将查到的文件列表的文件属性查出，与审批权限表进行比对
			if (null != filesList && filesList.size() > 0) {
				agentAuditFileIds = new ArrayList<Long>();
				for (KonkaoaFiles _files : filesList) {
					KonkaoaFilesAuditAgentUser _faau = new KonkaoaFilesAuditAgentUser();
					_faau.setUser_id(_files.getCur_audit_user_id());
					_faau.setAgent_user_id(ui.getId());
					_faau.getMap().put("link_id", _files.getId());
					Long count = super.getFacade().getKonkaoaFilesAuditAgentUserService()
							.getKonkaoaAgentFilesAuditPopedomCount(_faau);
					if (count == 0) {// 有审批权限的
						agentAuditFileIds.add(_files.getId());
					}
				}
			}
		}

		KonkaoaFiles entity = new KonkaoaFiles();
        entity.getMap().put("gt_file_status", 0);
        entity.getMap().put("lt_file_status", 2);
        entity.getMap().put("agentAuditFileIds", agentAuditFileIds);

		entity.setIs_del(0);
		entity.setCur_audit_user_id(ui.getId());
		Long recordCount = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesCountForAudit(entity);

		super.renderText(response, String.valueOf(Math.ceil(recordCount)));
		return null;
	}

	public ActionForward view1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String id = (String) dynaBean.get("id");

		// 查询当前文件
		KonkaoaFiles entity = new KonkaoaFiles();
		entity.setId(Long.parseLong(id));
		entity = super.getFacade().getKonkaoaFilesService().getKonkaoaFiles(entity);

		super.copyProperties(form, entity);

		if (entity.getFile_type() == 1) {// 费用申请
			KonkaExpenseClaims kec = new KonkaExpenseClaims();
			kec.setFile_id(Long.parseLong(id));
			kec = super.getFacade().getKonkaExpenseClaimsService().getKonkaExpenseClaims(kec);

			KonkaoaFilesProperty filesProperty = new KonkaoaFilesProperty();
			filesProperty.setLink_id(Long.parseLong(id));
			filesProperty.setC_type(0);
			filesProperty.setAdd_type(0L);
			List<KonkaoaFilesProperty> filesPropertyList = super.getFacade().getKonkaoaFilesPropertyService()
					.getKonkaoaFilesPropertyList(filesProperty);

			if (filesPropertyList.size() > 0) {
				for (KonkaoaFilesProperty temp : filesPropertyList) {

					KonkaoaCategory kc = new KonkaoaCategory();
					kc.setC_index(temp.getC_index());
					kc = super.getFacade().getKonkaoaCategoryService().getKonkaoaCategory(kc);
					if (null != kc) {
						temp.getMap().put("c_name", kc.getC_name());
					}
				}

			}
			request.setAttribute("filesPropertyList", filesPropertyList);
			dynaBean.set("column_6", kec.getColumn_6());

		}

		// 附件
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(entity.getId());
		attachment.setLink_tab("FILES");
		attachment.setIs_del(0l);
		request.setAttribute("attachmentList", super.getFacade().getKonkaPeAttachmentsService()
				.getKonkaPeAttachmentsList(attachment));

		// 审核记录
		KonkaoaFilesAuditNode fan = new KonkaoaFilesAuditNode();
		fan.setLink_id(Long.valueOf(id));
		fan.setAudit_type(0);
		fan.getMap().put("is_audit", "is_audit");
		request.setAttribute("filesAuditNodeList", getFacade().getKonkaoaFilesAuditNodeService()
				.getKonkaoaFilesAuditNodeListForView(fan));

		return new ActionForward("/mobile/oaFileSearch/view1.jsp");
	}

	// end

	// 查询下发给我的文件 start
	public ActionForward list2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		// super.encodeCharacterForGetMethod(dynaBean, request);
		// // 用户名
		// String username = (String) dynaBean.get("username");
		// // 密码
		// String userpass = (String) dynaBean.get("userpass");

		String user_id = (String) dynaBean.get("user_id");
		String userpass = (String) dynaBean.get("userpass");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

		if (StringUtils.isBlank(user_id) && StringUtils.isBlank(userpass)) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}
		String username = "";
		PeProdUser ui = new PeProdUser();

		if (user_id != null) {
			ui.setId(Long.valueOf(user_id));
			ui.setPass_word(new DESPlus().encrypt(userpass));
			ui = getFacade().getPeProdUserService().getPeProdUser(ui);
		} else {
			username = request.getParameter("username");
			ui = checkUser(username, userpass,android_version,ios_version);
		}
		if (null == ui) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}
		int i = 0;
		List<KonkaoaEventInfo> list = new ArrayList<KonkaoaEventInfo>();

		KonkaoaSsuedDocument entity = new KonkaoaSsuedDocument();
		entity.getMap().put("uid", ui.getId());
		entity.getMap().put("receive_org_id", ui.getDept_id());

		Long recordCount = super.getFacade().getKonkaoaFilesService().getKonkaoaSsuedDocumentCount(entity);
		String page = request.getParameter("page");
		String forward = (String) dynaBean.get("forward");
		int currentPage = 1;
		int pageSize = 5;
		if (!StringUtils.isEmpty(page)) {
			currentPage = (Integer.parseInt(page)) + (Integer.parseInt(forward));
		}
		entity.getRow().setFirst((currentPage - 1) * pageSize);
		entity.getRow().setCount(pageSize);

		if (recordCount > 0) {
			dynaBean.set("currentPage", currentPage);

			List<KonkaoaSsuedDocument> entityList = super.getFacade().getKonkaoaFilesService()
					.getKonkaoaSsuedDocumentPaginatedList(entity);
			for (KonkaoaSsuedDocument t : entityList) {
				i++;
				KonkaoaEventInfo _t = new KonkaoaEventInfo();
				_t.setId(t.getId());

				_t.setEnterDate(t.getAdd_date());
				_t.setEventiltle(t.getTitle());
				_t.setSequence(i);
				_t.setFromPerson(t.getPass_man_name());
				_t.setEventType(t.getMod_name());
				if ("notice".equals(t.getMod_type())) {
					_t.setEventDo("<a href='OaFileSearch.do?method=view_22&id=" + t.getId() + "&username="
							+ ui.getUser_name() + "&user_id=" + ui.getId() + "&userpass=" + userpass + "'><img src=\""
							+ request.getContextPath()
							+ "/mobile/images/search.jpg\" width=\"49\" height=\"30\" /></a>");
				} else {
					_t.setEventDo("<a href='OaFileSearch.do?method=view_21&id=" + t.getId() + "&username="
							+ ui.getUser_name() + "&user_id=" + ui.getId() + "&userpass=" + userpass + "'><img src=\""
							+ request.getContextPath()
							+ "/mobile/images/search.jpg\" width=\"49\" height=\"30\" /></a>");
				}

				String url = (_t.getEventDo().split("href='")[1]).split("'>")[0];
				_t.getMap().put("url", url);
				list.add(_t);
			}
			request.setAttribute("entityList", list);
		}
		return new ActionForward("/mobile/oaFileSearch/list2.jsp");
	}

	public ActionForward getCount2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		// super.encodeCharacterForGetMethod(dynaBean, request);

		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		String user_id = (String) dynaBean.get("user_id");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser ui = new PeProdUser();
		if (user_id != null) {
			ui.setId(Long.valueOf(user_id));
			ui.setPass_word(new DESPlus().encrypt(userpass));
			ui = getFacade().getPeProdUserService().getPeProdUser(ui);
		} else {
			username = request.getParameter("username");
			ui = checkUser(username, userpass,android_version,ios_version);
		}

		if (null == ui) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		KonkaoaSsuedDocument entity = new KonkaoaSsuedDocument();
		entity.getMap().put("uid", ui.getId());
		entity.getMap().put("receive_org_id", ui.getDept_id());

		Long recordCount = super.getFacade().getKonkaoaFilesService().getKonkaoaSsuedDocumentCount(entity);

		super.renderText(response, String.valueOf(Math.ceil(recordCount)));
		return null;
	}

	public ActionForward view_21(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String id = (String) dynaBean.get("id");

		// 查询当前文件
		KonkaoaFiles entity = new KonkaoaFiles();
		entity.setId(Long.parseLong(id));
		entity = super.getFacade().getKonkaoaFilesService().getKonkaoaFiles(entity);

		super.copyProperties(form, entity);

		if (entity.getFile_type() == 1) {// 费用申请
			KonkaExpenseClaims kec = new KonkaExpenseClaims();
			kec.setFile_id(Long.parseLong(id));
			kec = super.getFacade().getKonkaExpenseClaimsService().getKonkaExpenseClaims(kec);

			KonkaoaFilesProperty filesProperty = new KonkaoaFilesProperty();
			filesProperty.setLink_id(Long.parseLong(id));
			filesProperty.setC_type(0);
			filesProperty.setAdd_type(0L);
			List<KonkaoaFilesProperty> filesPropertyList = super.getFacade().getKonkaoaFilesPropertyService()
					.getKonkaoaFilesPropertyList(filesProperty);

			if (filesPropertyList.size() > 0) {
				for (KonkaoaFilesProperty temp : filesPropertyList) {

					KonkaoaCategory kc = new KonkaoaCategory();
					kc.setC_index(temp.getC_index());
					kc = super.getFacade().getKonkaoaCategoryService().getKonkaoaCategory(kc);
					if (null != kc) {
						temp.getMap().put("c_name", kc.getC_name());
					}
				}

			}
			request.setAttribute("filesPropertyList", filesPropertyList);
			dynaBean.set("column_6", kec.getColumn_6());

		}

		// 附件
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(entity.getId());
		attachment.setLink_tab("FILES");
		attachment.setIs_del(0l);
		request.setAttribute("attachmentList", super.getFacade().getKonkaPeAttachmentsService()
				.getKonkaPeAttachmentsList(attachment));

		// 审核记录
		KonkaoaFilesAuditNode fan = new KonkaoaFilesAuditNode();
		fan.setLink_id(Long.valueOf(id));
		fan.setAudit_type(0);
		fan.getMap().put("is_audit", "is_audit");
		request.setAttribute("filesAuditNodeList", getFacade().getKonkaoaFilesAuditNodeService()
				.getKonkaoaFilesAuditNodeListForView(fan));

		return new ActionForward("/mobile/oaFileSearch/view21.jsp");
	}

	public ActionForward view_22(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String id = (String) dynaBean.get("id");

		KonkaoaDocInfo entity = new KonkaoaDocInfo();
		entity.setId(new Long(id));
		entity = getFacade().getKonkaoaDocInfoService().getKonkaoaDocInfo(entity);
		super.copyProperties(form, entity);

		// 附件
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(new Long(id));
		request.setAttribute("attachmentList", getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachmentsList(
				attachment));

		return new ActionForward("/mobile/oaFileSearch/view22.jsp");
	}

	// end

	// 我提交的文件
	public ActionForward list3(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String user_id = (String) dynaBean.get("user_id");
		String userpass = (String) dynaBean.get("userpass");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

		if (StringUtils.isBlank(user_id) && StringUtils.isBlank(userpass)) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		PeProdUser ui = new PeProdUser();

		String username = "";
		if (user_id != null) {
			ui.setId(Long.valueOf(user_id));
			ui.setPass_word(new DESPlus().encrypt(userpass));
			ui = getFacade().getPeProdUserService().getPeProdUser(ui);
		} else {
			username = request.getParameter("username");
			ui = checkUser(username, userpass,android_version,ios_version);
		}
		if (null == ui) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		int i = 0;
		List<KonkaoaEventInfo> list = new ArrayList<KonkaoaEventInfo>();

		KonkaoaFiles entity = new KonkaoaFiles();
		entity.setIs_del(0);
		entity.setSubmit_user_id(ui.getId());

		Long recordCount = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesCount(entity);

		String page = request.getParameter("page");
		String forward = (String) dynaBean.get("forward");
		int currentPage = 1;
		int pageSize = 5;
		if (!StringUtils.isEmpty(page)) {
			currentPage = (Integer.parseInt(page)) + (Integer.parseInt(forward));
		}
		entity.getRow().setFirst((currentPage - 1) * pageSize);
		entity.getRow().setCount(pageSize);

		if (recordCount > 0) {
			dynaBean.set("currentPage", currentPage);

			List<KonkaoaFiles> cList = super.getFacade().getKonkaoaFilesService().getBaseKonkaoaFilesPaginatedList(
					entity);

			for (KonkaoaFiles t : cList) {
				i++;
				KonkaoaEventInfo _t = new KonkaoaEventInfo();
				_t.setId(t.getId());

				_t.setEnterDate(t.getSubmit_datetime());
				_t.setEventiltle(t.getFile_title());
				_t.setSequence(i);
				_t.setFromPerson(t.getSubmit_user());
				if (t.getFile_type() == 0) {
					_t.setEventType("文件提交");
				} else if (t.getFile_type() == 1) {
					_t.setEventType("费用申请");
				}

				_t.setEventDo("<a href='OaFileSearch.do?method=view3&id=" + t.getId() + "&username="
						+ ui.getUser_name() + "&user_id=" + ui.getId() + "&userpass=" + userpass + "'><img src=\""
						+ request.getContextPath() + "/mobile/images/search.jpg\" width=\"49\" height=\"30\" /></a>");

				String url = (_t.getEventDo().split("href='")[1]).split("'>")[0];
				_t.getMap().put("url", url);
				list.add(_t);
			}

			request.setAttribute("entityList", list);
		}
		return new ActionForward("/mobile/oaFileSearch/list3.jsp");
	}

	public ActionForward getCount3(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		// super.encodeCharacterForGetMethod(dynaBean, request);

		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		String user_id = (String) dynaBean.get("user_id");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser ui = new PeProdUser();
		if (user_id != null) {
			ui.setId(Long.valueOf(user_id));
			ui.setPass_word(new DESPlus().encrypt(userpass));
			ui = getFacade().getPeProdUserService().getPeProdUser(ui);
		} else {
			username = request.getParameter("username");
			ui = checkUser(username, userpass,android_version,ios_version);
		}

		if (null == ui) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		KonkaoaFiles entity = new KonkaoaFiles();
		entity.setIs_del(0);
		entity.setSubmit_user_id(ui.getId());

		Long recordCount = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesCount(entity);

		super.renderText(response, String.valueOf(Math.ceil(recordCount)));
		return null;
	}

	public ActionForward view3(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String id = (String) dynaBean.get("id");

		KonkaoaFiles entity = new KonkaoaFiles();
		entity.setId(new Long(id));
		entity = getFacade().getKonkaoaFilesService().getKonkaoaFiles(entity);
		super.copyProperties(form, entity);

		if (null!=entity&&null!=entity.getFile_type()&&entity.getFile_type() == 1) {// 费用申请
			KonkaExpenseClaims kec = new KonkaExpenseClaims();
			kec.setFile_id(Long.parseLong(id));
			kec = super.getFacade().getKonkaExpenseClaimsService().getKonkaExpenseClaims(kec);

			KonkaoaFilesProperty filesProperty = new KonkaoaFilesProperty();
			filesProperty.setLink_id(Long.parseLong(id));
			filesProperty.setC_type(0);
			filesProperty.setAdd_type(0L);
			List<KonkaoaFilesProperty> filesPropertyList = super.getFacade().getKonkaoaFilesPropertyService()
					.getKonkaoaFilesPropertyList(filesProperty);

			if (filesPropertyList.size() > 0) {
				for (KonkaoaFilesProperty temp : filesPropertyList) {

					KonkaoaCategory kc = new KonkaoaCategory();
					kc.setC_index(temp.getC_index());
					kc = super.getFacade().getKonkaoaCategoryService().getKonkaoaCategory(kc);
					if (null != kc && null!=kc.getC_name()) {
						temp.getMap().put("c_name", kc.getC_name());
					}
				}

			}
			request.setAttribute("filesPropertyList", filesPropertyList);
			dynaBean.set("column_6", kec.getColumn_6());

		}

		// 附件
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(entity.getId());
		attachment.setLink_tab("FILES");
		attachment.setIs_del(0l);
		request.setAttribute("attachmentList", super.getFacade().getKonkaPeAttachmentsService()
				.getKonkaPeAttachmentsList(attachment));

		// 审核记录
		KonkaoaFilesAuditNode fan = new KonkaoaFilesAuditNode();
		fan.setLink_id(Long.valueOf(id));
		fan.setAudit_type(0);
		fan.getMap().put("is_audit", "is_audit");
		request.setAttribute("filesAuditNodeList", getFacade().getKonkaoaFilesAuditNodeService()
				.getKonkaoaFilesAuditNodeListForView(fan));

		return new ActionForward("/mobile/oaFileSearch/view3.jsp");
	}

}
