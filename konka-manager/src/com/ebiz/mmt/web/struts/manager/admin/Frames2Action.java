package com.ebiz.mmt.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaParagonAttentionC;
import com.ebiz.mmt.domain.KonkaPeArticleInfo;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaoaEventInfo;
import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.KonkaoaFilesAuditAgentUser;
import com.ebiz.mmt.domain.KonkaoaSsuedDocument;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;

@Deprecated
public class Frames2Action extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.iframe(mapping, form, request, response);
	}

	public ActionForward iframe(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("iFrame");
	}

	public ActionForward index(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String url = (String) dynaBean.get("url");

		HttpSession session = request.getSession();
		PeProdUser userInfo = (PeProdUser) session.getAttribute(Constants.USER_INFO);

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
		boolean own_oa = false;
		for (PeRoleUser peRoleUser : roleUserList) {
			if (peRoleUser.getRole_id() >= 200L && peRoleUser.getRole_id() <= 400L) {
				// 专卖店（门店）用户
			} else if (peRoleUser.getRole_id() == 188L) {
				// 直销员用户
			} else {
				// 保留区间为管理员用户
				own_oa = true;
				break;
			}
		}

		SysModule sysModule = new SysModule();
		sysModule.getMap().put("user_id", userInfo.getId());
		sysModule.getMap().put("role_ids", role_ids);
		if ("99".equals(userInfo.getUser_type().toString())) {// 是管理员用户
			sysModule.getMap().put("is_admin", "is_admin");
		}

		// 以下判断当前用户属于那个分司，如果是成都分公司的话，mod_id在950000到959999之间的显示，990000到999999的不显示，如果不是成都分公司，则反之
		// KonkaDept konkaDept =
		// super.getSuperiorForDeptType(super.getSessionUserInfo(request).getDept_id(),
		// 3);
		List<SysModule> sysModuleList = super.getFacade().getSysModuleService().getSysModuleListForLeftTree(sysModule);

		List<SysModule> sysModuleList_1 = new ArrayList<SysModule>();
		List<SysModule> sysModuleList_2 = new ArrayList<SysModule>();
		List<SysModule> sysModuleList_3 = new ArrayList<SysModule>();
		List<SysModule> sysModuleList_4 = new ArrayList<SysModule>();

		Iterator<SysModule> itr = sysModuleList.iterator();
		while (itr.hasNext()) {
			SysModule t = itr.next();

			// 以下判断当前用户属于那个分司，如果是成都分公司的话，mod_id在950000到959999之间的显示，990000到999999的不显示，如果不是成都分公司，则反之

			boolean oa_old_mod = (t.getMod_id() >= 990000L && t.getMod_id() <= 999999L);
			boolean oa_new_mod = (t.getMod_id() >= 950000L && t.getMod_id() <= 959999L); // 成都OA，新版OA，带流程自定义

			boolean removed = false;

			// if (konkaDept != null && konkaDept.getDept_id() == 5) {
			if (oa_old_mod) {
				itr.remove();
				removed = true;
			}
			// } else {
			// if (oa_new_mod) {
			// itr.remove();
			// removed = true;
			// }
			// }

			if (!removed && !own_oa && (oa_new_mod || oa_old_mod)) {
				// 不具备拥有OA模块权限（专卖店和直销员）
				itr.remove();
				removed = true;
			} else if (!removed && !own_oa && t.getMod_id() == 900100L) {
				itr.remove();
				removed = true;
			}

			if (removed) {
				continue;
			}

			if (null != t.getTree_level()) {
				if (1 == t.getTree_level()) {
					sysModuleList_1.add(t);
				} else if (2 == t.getTree_level()) {
					sysModuleList_2.add(t);
				} else if (3 == t.getTree_level()) {
					sysModuleList_3.add(t);
				} else if (4 == t.getTree_level()) {
					sysModuleList_4.add(t);
				}
			}
		}

		request.setAttribute("sysModuleList", sysModuleList);
		request.setAttribute("sysModuleList_1", sysModuleList_1);
		request.setAttribute("sysModuleList_2", sysModuleList_2);
		request.setAttribute("sysModuleList_3", sysModuleList_3);
		request.setAttribute("sysModuleList_4", sysModuleList_4);
		session.setAttribute(Constants.USER_INFO, userInfo);

		request.setAttribute("url", url);

		KonkaDept fgs = super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
		if (null != fgs) {
			request.setAttribute("fgs_name", fgs.getDept_name());

			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(userInfo.getDept_id());
			konkaDept = getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			request.setAttribute("dept_name", konkaDept.getDept_name());
		}

		return mapping.findForward("indexFrame");
	}

	public ActionForward main(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String url = (String) dynaBean.get("url");

		if (StringUtils.isNotBlank(url)) {
			url = new DESPlus().decrypt(url);
			super.renderJavaScript(response, "window.onload=function(){location.href='" + url + "'}");
			return null;
		}
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

		List<KonkaPeArticleInfo> articleInfoList = null;
		KonkaPeArticleInfo konkaPeArticleInfo = new KonkaPeArticleInfo();
		konkaPeArticleInfo.setIs_display_index(1L);
		konkaPeArticleInfo.setIs_del(0L);
		konkaPeArticleInfo.getRow().setCount(5);

		konkaPeArticleInfo.setArticle_mod_id(860201L);
		articleInfoList = super.getFacade().getKonkaPeArticleInfoService()
				.getKonkaPeArticleInfoList(konkaPeArticleInfo);
		request.setAttribute("articleInfoList860201", articleInfoList);

		konkaPeArticleInfo.setArticle_mod_id(860202L);
		articleInfoList = super.getFacade().getKonkaPeArticleInfoService()
				.getKonkaPeArticleInfoList(konkaPeArticleInfo);
		request.setAttribute("articleInfoList860202", articleInfoList);

		konkaPeArticleInfo.setArticle_mod_id(860203L);
		articleInfoList = super.getFacade().getKonkaPeArticleInfoService()
				.getKonkaPeArticleInfoList(konkaPeArticleInfo);
		request.setAttribute("articleInfoList860203", articleInfoList);

		konkaPeArticleInfo.setArticle_mod_id(860204L);
		articleInfoList = super.getFacade().getKonkaPeArticleInfoService()
				.getKonkaPeArticleInfoList(konkaPeArticleInfo);
		request.setAttribute("articleInfoList860204", articleInfoList);

		konkaPeArticleInfo.setArticle_mod_id(860205L);
		articleInfoList = super.getFacade().getKonkaPeArticleInfoService()
				.getKonkaPeArticleInfoList(konkaPeArticleInfo);
		request.setAttribute("articleInfoList860205", articleInfoList);

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		request.setAttribute("user_id", ui.getId());

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(ui.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_1000_le_1100 = false;
		boolean role_id_eq_10 = false; // 系统管理员
		boolean role_id_eq_30 = false; // 分公司管理员
		boolean role_id_eq_188 = false; // 促销员
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() >= 1000L && peRoleUser.getRole_id() <= 1100L) {
				role_id_ge_1000_le_1100 = true;
			}
			if (peRoleUser.getRole_id() == 188L) {
				role_id_eq_188 = true;
			}
			if (peRoleUser.getRole_id() == 10L) {
				role_id_eq_10 = true;
			}
			if (peRoleUser.getRole_id() == 30L) {
				role_id_eq_30 = true;
			}
		}

		if (role_id_eq_10) {
			// 系统管理员
			KonkaR3Shop s = new KonkaR3Shop();
			List<KonkaR3Shop> staticByFGSList = super.getFacade().getKonkaR3ShopService()
					.getKonkaR3ShopStaticsGroupByFGS(s);
			request.setAttribute("staticByFGSList", staticByFGSList);
		}
		if (!role_id_eq_10 && role_id_eq_30) {
			// 仅是分公司管理员
			boolean data_ok = false;
			KonkaDept dept = null;

			if (null != ui.getDept_id()) {
				dept = super.getSuperiorForDeptType(ui.getDept_id(), 3);
				if (null != dept) {
					data_ok = true;
				}
			}

			if (data_ok) {
				KonkaR3Shop s = new KonkaR3Shop();
				s.getMap().put("dept_id_eq", dept.getDept_id());
				List<KonkaR3Shop> staticByFGSList = super.getFacade().getKonkaR3ShopService()
						.getKonkaR3ShopStaticsGroupByFGS(s);
				request.setAttribute("staticByFGSList", staticByFGSList);
			}
		}

		if (!role_id_eq_188) {

			int i = 0;
			List<KonkaoaEventInfo> list = new ArrayList<KonkaoaEventInfo>();
			// 待审文件
			// List<Files> cList = new ArrayList<Files>();
			// Files entity = new KonkaoaFiles();
			// entity.getMap().put("lt_file_status", 2);
			// entity.setIs_del(0);
			// entity.setCur_audit_user_id(ui.getId());
			// cList =
			// super.getFacade().getKonkaoaFilesService().getFilesList(entity);

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
					.getKonkaoaFilesAuditAgentUserService().getKonkaoaFilesAuditAgentUserList(faau);
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

			// KonkaDept dept = super.getSuperiorForDeptType(ui.getDept_id(),
			// 3);// 获取登录用户所属分公司的名称

			// boolean is_chengdufgs = false;

			// if (dept != null) {
			// if (dept.getDept_id() == 5) {
			// is_chengdufgs = true;
			// }
			// } else {
			// is_chengdufgs = false;
			// }

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
					if (t.getIs_node() == 1) {// 成都分公司
						_t.setEventDo("<span style=\"cursor:pointer;\" onclick=\"javascript:window.location.href='../chengduoa/SelfEventCenter.do?method=edit&mod_id=951000&is_agent="
								+ ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0")
								+ "&agent_user_id="
								+ t.getCur_audit_user_id()
								+ "&file_type="
								+ t.getFile_type()
								+ "&id="
								+ t.getId()
								+ "'\">审批</span>");
					} else {// 非成都分公司
						_t.setEventDo("<span style=\"cursor:pointer;\" onclick=\"javascript:window.location.href='../oa/SelfEventCenter.do?method=edit&mod_id=991000&is_agent="
								+ ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0")
								+ "&agent_user_id="
								+ t.getCur_audit_user_id()
								+ "&file_type="
								+ t.getFile_type()
								+ "&id="
								+ t.getId()
								+ "'\">审批</span>");
					}

				} else {
					if (t.getIs_node() == 1) {// 成都分公司
						_t.setEventDo("<span style=\"cursor:pointer;\" onclick=\"javascript:window.location.href='../chengduoa/SelfEventCenter.do?method=edit&mod_id=951000&is_agent="
								+ ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0")
								+ "&agent_user_id="
								+ t.getCur_audit_user_id()
								+ "&file_type="
								+ t.getFile_type()
								+ "&id="
								+ t.getId()
								+ "'\">会签</span>");
					} else {// 非成都分公司
						_t.setEventDo("<span style=\"cursor:pointer;\" onclick=\"javascript:window.location.href='../oa/SelfEventCenter.do?method=edit&mod_id=991000&is_agent="
								+ ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0")
								+ "&agent_user_id="
								+ t.getCur_audit_user_id()
								+ "&file_type="
								+ t.getFile_type()
								+ "&id="
								+ t.getId()
								+ "'\">会签</span>");
					}

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
					if (t.getIs_node() == 1) {// 成都分公司
						_t.setEventDo("<span style=\"cursor:pointer;\" onclick=\"javascript:window.location.href='../chengduoa/FilesSubmit.do?method=edit&id="
								+ t.getId() + "\">审批</span>");
					} else {// 非成都分公司
						_t.setEventDo("<span style=\"cursor:pointer;\" onclick=\"javascript:window.location.href='../oa/FilesSubmit.do?method=edit&id="
								+ t.getId() + "\">审批</span>");
					}
				} else if (t.getFile_type() == 1) {
					_t.setEventType("费用申请");
					if (t.getIs_node() == 1) {// 成都分公司
						_t.setEventDo("<span style=\"cursor:pointer;\" onclick=\"javascript:window.location.href='../chengduoa/ExpenseClaims.do?method=edit&file_id="
								+ t.getId() + "\">审批</span>");
					} else {// 非成都分公司
						_t.setEventDo("<span style=\"cursor:pointer;\" onclick=\"javascript:window.location.href='../oa/ExpenseClaims.do?method=edit&file_id="
								+ t.getId() + "\">审批</span>");
					}
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
			// request.setAttribute("is_chengdufgs", is_chengdufgs);

		}

		// 取完美终端最新上报数据
		KonkaParagonAttentionC _atc = new KonkaParagonAttentionC();

		if (role_id_ge_1000_le_1100) {
			if (ui != null)
				if (ui.getMap().get("dept_type").toString().equals("1")
						|| ui.getMap().get("dept_type").toString().equals("2")) {
				} else if (ui.getMap().get("dept_type").toString().equals("3")) {
					_atc.getMap().put("part_company_id", new Long(ui.getMap().get("par_dept_ids").toString()));
				} else if (ui.getMap().get("dept_type").toString().equals("4")) {
					_atc.getMap().put("part_company_id", new Long(ui.getMap().get("par_id").toString()));
				} else if (ui.getMap().get("dept_type").toString().equals("5")) {
					KonkaDept konka_dept_5 = new KonkaDept();
					konka_dept_5.setDept_id(new Long(ui.getMap().get("par_id").toString()));
					konka_dept_5 = super.getFacade().getKonkaDeptService().getKonkaDept(konka_dept_5);
					_atc.getMap().put("channel_id", konka_dept_5.getPar_id());
				}
			List<HashMap<String, String>> atclist = super.getFacade().getKonkaParagonAttentionCService()
					.selectKonkaParagonAttentionPaginatedList(_atc);
			request.setAttribute("atcList", atclist);
		}

		return mapping.findForward("mainFrame");
	}

	public ActionForward top(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SysModule sysModule = new SysModule();
		sysModule.setPar_id(0);
		List<SysModule> sysModuleList = super.getFacade().getSysModuleService().getSysModuleList(sysModule);

		boolean is_fengongsi = false;
		// modify by Jiang,Jiayong 增加对新兴渠道的支持 ，2012-01-10
		// if (peRoleUser.getRole_id() >= 30) {

		@SuppressWarnings("unchecked")
		List<PeRoleUser> peRoleUserList = (List<PeRoleUser>) request.getSession()
				.getAttribute(Constants.ROLE_INFO_LIST);
		boolean role_id_ge_30_lt_200_or_ge_300 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() >= 30 && peRoleUser.getRole_id() < 200 || peRoleUser.getRole_id() >= 300) {
				role_id_ge_30_lt_200_or_ge_300 = true;
				break;
			}
		}

		if (role_id_ge_30_lt_200_or_ge_300) {
			is_fengongsi = true;
			PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
			KonkaDept konkaDept = super.getSuperiorForDeptType(ui.getDept_id(), new Integer(3));
			String dept_name = "";
			if (konkaDept != null)
				dept_name = konkaDept.getDept_desc();
			request.setAttribute("dept_name", dept_name);
		}
		request.setAttribute("sysModuleList", sysModuleList);
		request.setAttribute("is_fengongsi", is_fengongsi);

		return mapping.findForward("topFrame");
	}

	public ActionForward middle(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("middleFrame");
	}

	public ActionForward footer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("footerFrame");
	}

	public ActionForward wait(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return mapping.findForward("waitFrame");
	}

}
