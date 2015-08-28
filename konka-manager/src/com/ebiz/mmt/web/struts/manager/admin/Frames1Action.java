package com.ebiz.mmt.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaoaEventInfo;
import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.KonkaoaFilesAuditAgentUser;
import com.ebiz.mmt.domain.KonkaoaSsuedDocument;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.StringHelper;

@Deprecated
public class Frames1Action extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.index(mapping, form, request, response);
	}

	public ActionForward index(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

//		PeRoleUser peRoleUser = (PeRoleUser) request.getSession().getAttribute(Constants.ROLE_INFO);
		
		@SuppressWarnings("unchecked")
		List<PeRoleUser> peRoleUserList =  (List<PeRoleUser>) request.getSession().getAttribute(Constants.ROLE_INFO_LIST);
		boolean role_id_ge_30 = false;
		for (PeRoleUser  peRoleUser: peRoleUserList) {
			if (peRoleUser.getRole_id() >= 30) {
				role_id_ge_30 = true;
				break;
			}
		}
		
		boolean is_fengongsi = false;
		if (role_id_ge_30) {
			is_fengongsi = true;
			PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
			KonkaDept konkaDept = super.getSuperiorForDeptType(ui.getDept_id(), new Integer(3));
			String dept_name = konkaDept.getDept_desc();
			request.setAttribute("dept_name", dept_name);
		}
		request.setAttribute("is_fengongsi", is_fengongsi);

		HttpSession session = request.getSession();
		PeProdUser userInfo  = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		// DynaBean dynaBean = (DynaBean) form;
		// String par_id = (String) dynaBean.get("par_id");

		// 查所属角色
		PeRoleUser roleUser = new PeRoleUser();
		roleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> roleUserList = super.getFacade().getPeRoleUserService().getPeRoleUserList(roleUser);

		// 获取角色ID
		List<Long> role_ids = new ArrayList<Long>();
		if (null != roleUserList) {
			for (PeRoleUser ru : roleUserList) {
				role_ids.add(ru.getRole_id());
			}
		}

		KonkaDept fgs = super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
		if (null != fgs) {
			request.setAttribute("fgs_name", fgs.getDept_name());
		}
		
		SysModule sysModule = new SysModule();
		sysModule.getMap().put("user_id", userInfo.getId());
		sysModule.getMap().put("role_ids", role_ids);
		if ("99".equals(userInfo.getUser_type().toString())) {// 是管理员用户
			sysModule.getMap().put("is_admin", "is_admin");
		}

		if (role_id_ge_30) {
			is_fengongsi = true;
			PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
			KonkaDept konkaDept = super.getSuperiorForDeptType(ui.getDept_id(), new Integer(3));
			String dept_name = konkaDept.getDept_desc();
			request.setAttribute("dept_name", dept_name);
			request.setAttribute("is_fengongsi", true);
		}

		List<SysModule> sysModuleList = super.getFacade().getSysModuleService().getSysModuleListForLeftTree(sysModule);

		String treeNodes = StringHelper.getTreeNodes(sysModuleList);
		request.setAttribute("sysModuleList", sysModuleList);
		session.setAttribute("treeNodes", treeNodes);
		session.setAttribute(Constants.USER_INFO, userInfo);

		return mapping.findForward("indexFrame");
	}

	public ActionForward main(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 通知
		// NewsInfo notice = new NewsInfo();
		// notice.setNews_type(2); // 通知
		// notice.setInfo_state(0); // 未删除
		// notice.getMap().put("now_date", new Date());// 只显示出版时间>当前时间 的记录
		// List<NewsInfo> noticeList =
		// getFacade().getNewsInfoService().getNewsInfoList(notice);
		// request.setAttribute("noticeList", noticeList);

		// 新闻
		// NewsInfo newsInfo = new NewsInfo();
		// newsInfo.setNews_type(1); // 新闻
		// newsInfo.setInfo_state(0); // 未删除
		// newsInfo.getMap().put("now_date", new Date()); // 只显示出版时间>当前时间 的记录
		// List<NewsInfo> newsInfoList =
		// getFacade().getNewsInfoService().getNewsInfoList(newsInfo);
		// request.setAttribute("newsInfoList", newsInfoList);

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		int i = 0;
		List<KonkaoaEventInfo> list = new ArrayList<KonkaoaEventInfo>();
		// 待审文件
		// List<Files> cList = new ArrayList<Files>();
		// Files entity = new KonkaoaFiles();
		// entity.getMap().put("lt_file_status", 2);
		// entity.setIs_del(0);
		// entity.setCur_audit_user_id(ui.getId());
		// cList = super.getFacade().getKonkaoaFilesService().getFilesList(entity);

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
						// _files.getMap().put("agent_user_name",
						// ui.getUser_name());
						// entityList.add(_files);
						// ++recordCount;
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

		entity.getRow().setFirst(0);
		entity.getRow().setCount(5);

		List<KonkaoaFiles> cList = super.getFacade().getKonkaoaFilesService()
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
				_t.setEventDo("<a href='../oa/SelfEventCenter.do?method=edit&is_agent="
						+ ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0") + "&agent_user_id="
						+ t.getCur_audit_user_id() + "&file_type=" + t.getFile_type() + "&id=" + t.getId() + "'>审批</a>");
			} else {
				_t.setEventDo("<a href='../oa/SelfEventCenter.do?method=edit&is_agent="
						+ ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0") + "&agent_user_id="
						+ t.getCur_audit_user_id() + "&file_type=" + t.getFile_type() + "&id=" + t.getId() + "'>会签</a>");
			}
			list.add(_t);
		}

		KonkaoaFiles file5 = new KonkaoaFiles();
		file5.setFile_status(0);
		file5.setIs_del(0);
		file5.setSubmit_user_id(ui.getId());
		List<KonkaoaFiles> file5List = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesList(file5);

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
				_t.setEventDo("<a href='../oa/FilesSubmit.do?method=edit&id=" + t.getId() + "'>审批</a>");
			} else if (t.getFile_type() == 1) {
				_t.setEventType("费用申请");
				_t.setEventDo("<a href='../oa/ExpenseClaims.do?method=edit&file_id=" + t.getId() + "'>审批</a>");
			}

			list.add(_t);
		}

		request.setAttribute("entityList", list);

		// 下发文件
		KonkaoaSsuedDocument _entity = new KonkaoaSsuedDocument();
		_entity.getMap().put("uid", ui.getId());
		_entity.getMap().put("receive_org_id", ui.getDept_id());

		// 设置只显示5条数据，更多的数据点连接到行政办公系统的下发中进行查询
		_entity.getRow().setFirst(0);
		_entity.getRow().setCount(5);

		List<KonkaoaSsuedDocument> entityList = super.getFacade().getKonkaoaFilesService()
				.getKonkaoaSsuedDocumentPaginatedList(_entity);

		request.setAttribute("_entityList", entityList);

		return mapping.findForward("mainFrame");
	}

}
