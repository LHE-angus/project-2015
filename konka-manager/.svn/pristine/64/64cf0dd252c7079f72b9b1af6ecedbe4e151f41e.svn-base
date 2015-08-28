package com.ebiz.mmt.web.struts.mobile.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaoaEventInfo;
import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.KonkaoaFilesAuditAgentUser;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;

public class SelfEventCenterAction extends MobileBaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String page = (String) dynaBean.get("page");
		String forward = (String) dynaBean.get("forward");
		int currentPage = 1;
		int pageSize = 3;
		if (StringUtils.isEmpty(page))
			request.setAttribute("page", currentPage);
		else {
			currentPage = (Integer.parseInt(page))
					+ (Integer.parseInt(forward));
			request.setAttribute("page", currentPage);
		}

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(
				Constants.USER_MOBILE);

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
		List<KonkaoaFilesAuditAgentUser> filesAuditAgentUserList = getFacade()
				.getKonkaoaFilesAuditAgentUserService()
				.getKonkaoaFilesAuditAgentUserList(faau);
		if (null != filesAuditAgentUserList
				&& filesAuditAgentUserList.size() > 0) {
			Long[] agent_user_ids = new Long[filesAuditAgentUserList.size()];
			for (i = 0; i < filesAuditAgentUserList.size(); i++) {
				agent_user_ids[i] = filesAuditAgentUserList.get(i).getUser_id();
			}

			// 查出被代理用户的需要审批的文件列表
			KonkaoaFiles files = new KonkaoaFiles();
			files.setIs_del(0);
			files.getMap().put("lt_file_status", 2);
			files.getMap().put("cur_audit_user_ids", agent_user_ids);
			List<KonkaoaFiles> filesList = super.getFacade()
					.getKonkaoaFilesService().getKonkaoaFilesList(files);

			// 将查到的文件列表的文件属性查出，与审批权限表进行比对
			if (null != filesList && filesList.size() > 0) {
				agentAuditFileIds = new ArrayList<Long>();
				for (KonkaoaFiles _files : filesList) {
					KonkaoaFilesAuditAgentUser _faau = new KonkaoaFilesAuditAgentUser();
					_faau.setUser_id(_files.getCur_audit_user_id());
					_faau.setAgent_user_id(ui.getId());
					_faau.getMap().put("link_id", _files.getId());
					Long count = super.getFacade()
							.getKonkaoaFilesAuditAgentUserService()
							.getKonkaoaAgentFilesAuditPopedomCount(_faau);
					if (count == 0) {// 有审批权限的
						agentAuditFileIds.add(_files.getId());
					}
				}
			}
		}

		KonkaoaFiles entity = new KonkaoaFiles();
		entity.getMap().put("lt_file_status", 2);
		entity.getMap().put("agentAuditFileIds", agentAuditFileIds);

		entity.setIs_del(0);
		entity.setCur_audit_user_id(ui.getId());

		entity.getRow().setFirst((currentPage - 1) * pageSize);
		entity.getRow().setCount(pageSize);
		Long count = super.getFacade().getKonkaoaFilesService()
				.getKonkaoaFilesCountForAudit(entity);

		if (count % pageSize > 0)
			request.setAttribute("pagelimit", (count / pageSize) + 1);
		else
			request.setAttribute("pagelimit", (count / pageSize));
		if (count > 0) {
			List<KonkaoaFiles> cList = super.getFacade()
					.getKonkaoaFilesService()
					.getKonkaoaFilesPaginatedListForAudit(entity);
			for (KonkaoaFiles t : cList) {
				i++;
				KonkaoaEventInfo _t = new KonkaoaEventInfo();
				_t.setId(t.getId());
				// _t.setMod_id(20030);
				_t.setEnterDate(t.getSubmit_datetime());
				_t.setEventiltle(t.getFile_title());
				_t.setSequence(i);
				_t.setFromPerson(t.getSubmit_user());
				if (t.getFile_type() == 0) {
					_t.setEventType("文件提交");
				} else if (t.getFile_type() == 1) {
					_t.setEventType("费用申请");
				}

				if ("1".equals(t.getMap().get("agent_audit").toString())
						|| "0".equals(t.getMap().get("agent_audit").toString())) {
					_t
							.setEventDo("<a href='SelfEventCenter.do?method=edit&mod_id=991000&is_agent="
									+ ("1"
											.equals(t.getMap().get(
													"agent_audit")) ? "1" : "0")
									+ "&agent_user_id="
									+ t.getCur_audit_user_id()
									+ "&file_type="
									+ t.getFile_type()
									+ "&id="
									+ t.getId()
									+ "'>审批</a>");
				} else {
					_t
							.setEventDo("<a href='SelfEventCenter.do?method=edit&mod_id=991000&is_agent="
									+ ("1"
											.equals(t.getMap().get(
													"agent_audit")) ? "1" : "0")
									+ "&agent_user_id="
									+ t.getCur_audit_user_id()
									+ "&file_type="
									+ t.getFile_type()
									+ "&id="
									+ t.getId()
									+ "'>会签</a>");
				}
				list.add(_t);
			}

			KonkaoaFiles file5 = new KonkaoaFiles();
			file5.setFile_status(0);
			file5.setIs_del(0);
			file5.setSubmit_user_id(ui.getId());
			List<KonkaoaFiles> file5List = super.getFacade()
					.getKonkaoaFilesService().getKonkaoaFilesList(file5);

			for (KonkaoaFiles t : file5List) {
				i++;
				KonkaoaEventInfo _t = new KonkaoaEventInfo();
				_t.setId(t.getId());
				// _t.setMod_id(20030);
				_t.setEnterDate(t.getSubmit_datetime());
				_t.setEventiltle(t.getFile_title());
				_t.setSequence(i);
				_t.setFromPerson(t.getSubmit_user());
				if (t.getFile_type() == 0) {
					_t.setEventType("文件提交");
					_t
							.setEventDo("<a href='FilesSubmit.do?method=edit&mod_id=991000&id="
									+ t.getId() + "'>审批</a>");
				} else if (t.getFile_type() == 1) {
					_t.setEventType("费用申请");
					_t
							.setEventDo("<a href='ExpenseClaims.do?method=edit&mod_id=991000&file_id="
									+ t.getId() + "'>审批</a>");
				}

				list.add(_t);
			}

			request.setAttribute("entityList", list);
		}

		createMobileSysOperLog(request, "KONKAOA_FILES", 720100l, "720100",
				"查询", "手机端-待办文件-查询");
		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		saveToken(request);

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
		return mapping.findForward("input");
	}

}