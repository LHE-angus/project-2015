package com.ebiz.mmt.web.struts.manager.leader;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.domain.KonkaR3Backmoney;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdAuditUserInfo;
import com.ebiz.mmt.domain.KonkaoaEventInfo;
import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.KonkaoaFilesAuditAgentUser;
import com.ebiz.mmt.domain.KonkaoaSsuedDocument;
import com.ebiz.mmt.domain.MemberInfo;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Xu,ZhengYang
 */
public class Frames3Action extends BaseAction {
	private static final Long[] EX_dept = new Long[] { 2108L, 2109L, 2110L, 2137L, 2147L, 2176L, 2274L,2273L,2272L, 744L };

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
				own_oa = true;// 专卖店（门店）用户
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
		if (null != userInfo.getUser_type() && 10 == userInfo.getUser_type()) {// 是管理员用户
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

		super.renderJavaScript(response, "window.onload=function(){location.href='JcfxReportXsqs.do'}");
		return null;
	}

	public ActionForward main(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String url = (String) dynaBean.get("url");

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		request.setAttribute("user_id", ui.getId());
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(ui.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		request.setAttribute("peRoleUserList", peRoleUserList);

		// boolean role_id_ge_1000_le_1100 = false;
		boolean role_id_eq_10 = false; // 系统管理员
		boolean role_id_eq_30 = false; // 分公司管理员
		boolean role_id_lt_30 = false; // 总部人员
		boolean role_id_eq_188 = false; // 促销员
		boolean role_id_ne_188 = false; // 非促销员
		String role_ids = "-1";
		for (PeRoleUser peRoleUser : peRoleUserList) {
			role_ids = role_ids + "," + peRoleUser.getRole_id();
			// if (peRoleUser.getRole_id() >= 1000L
			// && peRoleUser.getRole_id() <= 1100L) {
			// role_id_ge_1000_le_1100 = true;
			// }
			if (peRoleUser.getRole_id() == 188L) {
				role_id_eq_188 = true;
			}
			if (peRoleUser.getRole_id() == 10L) {
				role_id_eq_10 = true;
			}
			if (peRoleUser.getRole_id() == 30L) {
				role_id_eq_30 = true;
			}
			if (peRoleUser.getRole_id() != 188L) {
				role_id_ne_188 = true;
			}
			if (peRoleUser.getRole_id() < 30 || (peRoleUser.getRole_id() >= 200 & peRoleUser.getRole_id() < 300)) {
				role_id_lt_30 = true;
			}
		}

		if (role_id_eq_188) {
			// super.renderHtml(response, "Bad request. No permissions.");
			return null;
		}

		if (StringUtils.isNotBlank(url)) {
			url = new DESPlus().decrypt(url);
			super.renderJavaScript(response, "window.onload=function(){location.href='" + url + "'}");
			return null;
		}
		// 默认当前月份任务显示
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		request.setAttribute("this_year", String.valueOf(calendar.get(Calendar.YEAR)));
		request.setAttribute("this_month", String.valueOf(calendar.get(Calendar.MONTH) + 1));

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

		if (!role_id_eq_188 || role_id_ne_188) {

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

			List<KonkaoaFiles> cList = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesPaginatedListForAudit(
			        entity);
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
						_t
						        .setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../chengduoa/SelfEventCenter.do?method=edit&mod_id=951000&is_agent="
						                + ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0")
						                + "&agent_user_id="
						                + t.getCur_audit_user_id()
						                + "&file_type="
						                + t.getFile_type()
						                + "&id="
						                + t.getId() + "'\">审批</a>");
					} else {// 非成都分公司
						_t
						        .setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../oa/SelfEventCenter.do?method=edit&mod_id=991000&is_agent="
						                + ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0")
						                + "&agent_user_id="
						                + t.getCur_audit_user_id()
						                + "&file_type="
						                + t.getFile_type()
						                + "&id="
						                + t.getId() + "'\">审批</a>");
					}

				} else {
					if (t.getIs_node() == 1) {// 成都分公司
						_t
						        .setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../chengduoa/SelfEventCenter.do?method=edit&mod_id=951000&is_agent="
						                + ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0")
						                + "&agent_user_id="
						                + t.getCur_audit_user_id()
						                + "&file_type="
						                + t.getFile_type()
						                + "&id="
						                + t.getId() + "'\">会签</a>");
					} else {// 非成都分公司
						_t
						        .setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../oa/SelfEventCenter.do?method=edit&mod_id=991000&is_agent="
						                + ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0")
						                + "&agent_user_id="
						                + t.getCur_audit_user_id()
						                + "&file_type="
						                + t.getFile_type()
						                + "&id="
						                + t.getId() + "'\">会签</a>");
					}
				}
				String title = _t.getEventDo();
				title = title.replaceAll("会签", _t.getEventiltle()).replaceAll("审批", _t.getEventiltle());
				title = title.replace("<a", "<span").replace("</a>", "</span>");
				_t.getMap().put("title", title);
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
						_t
						        .setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../chengduoa/FilesSubmit.do?method=edit&id="
						                + t.getId() + "'\">审批</a>");
					} else {// 非成都分公司
						_t
						        .setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../oa/FilesSubmit.do?method=edit&id="
						                + t.getId() + "'\">审批</a>");
					}
				} else if (t.getFile_type() == 1) {
					_t.setEventType("费用申请");
					if (t.getIs_node() == 1) {// 成都分公司
						_t
						        .setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../chengduoa/ExpenseClaims.do?method=edit&file_id="
						                + t.getId() + "'\">审批</a>");
					} else {// 非成都分公司
						_t
						        .setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../oa/ExpenseClaims.do?method=edit&file_id="
						                + t.getId() + "'\">审批</a>");
					}
				}
				String title = _t.getEventDo();
				title = title.replaceAll("审批", _t.getEventiltle());
				title = title.replace("<a", "<span").replace("</a>", "</span>");
				_t.getMap().put("title", title);
				list.add(_t);
			}

			Long dept_id = 0L;
			if (!role_id_lt_30) {
				KonkaDept dept_fgs = getKonkaDeptForFgs(ui.getDept_id());
				if (null != dept_fgs) {
					dept_id = dept_fgs.getDept_id();
				}
			}

			// 资质审核
			KonkaXxZmdAuditUserInfo kInfo = new KonkaXxZmdAuditUserInfo();
			kInfo.getMap().put("db_role_ids", role_ids);
			kInfo.setDept_id(dept_id);
			List<KonkaXxZmdAuditUserInfo> kInfoList = super.getFacade().getKonkaXxZmdAuditUserInfoService()
			        .getKonkaXxZmdAuditUserInfoForRoleIdList(kInfo);
			if (kInfoList.size() > 0) {
				for (KonkaXxZmdAuditUserInfo temp1 : kInfoList) {
					KonkaoaEventInfo _tr = new KonkaoaEventInfo();
					if (null != temp1.getMap().get("role_id")) {
						// _tr.setEventiltle(temp1.getUser_name()+ "客户资质申请");
						_tr.getMap().put("title", temp1.getUser_name() + "客户资质申请");
						_tr.setEnterDate(temp1.getAdd_date());
						_tr.setEventType("专卖店资质申请");
						_tr.setFromPerson(temp1.getAdd_user_name());
						_tr
						        .setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../zmd/KonkaXxZmdAuditUserInfoAudit.do?method=audit&zmd_user_id="
						                + temp1.getZmd_user_id()
						                + "&role_id="
						                + temp1.getMap().get("role_id")
						                + "&mod_id=810200" + "'\">审核</a>");
						list.add(_tr);
					}
				}
			}
			// 专卖店备案审核
			KonkaXxZmd zmd = new KonkaXxZmd();
			zmd.getMap().put("db_role_ids", role_ids);
			zmd.setDept_id(dept_id);
			List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdForRoleIdList(zmd);
			if (zmdList.size() > 0) {
				for (KonkaXxZmd temp1 : zmdList) {
					KonkaoaEventInfo _tr = new KonkaoaEventInfo();
					if (null != temp1.getMap().get("role_id")) {
						// _tr.setEventiltle("专卖店"+temp1.getZmd_sn()+ "备案申请");
						_tr.getMap().put("title", "专卖店" + temp1.getZmd_sn() + "备案申请");
						_tr.setEventType("专卖店资质申请");
						_tr.setFromPerson(temp1.getWrite_man());
						_tr.setEnterDate(temp1.getApply_date());
						_tr
						        .setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../zmd/KonkaXxZmdVerification.do?method=edit&zmd_id="
						                + temp1.getZmd_id()
						                + "&role_id="
						                + temp1.getMap().get("role_id")
						                + "&mod_id=810200" + "'\">审核</a>");
						list.add(_tr);
					}

				}
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

		}

		// 总部当月截止到昨日总体情况
		/****************************************************************/
		if (role_id_lt_30) {
			KonkaR3Backmoney entity = new KonkaR3Backmoney();// 结算数据

			// 取当前时间
			SimpleDateFormat formaty = new SimpleDateFormat("yyyy");
			SimpleDateFormat formatm = new SimpleDateFormat("MM");

			Date now = new Date();

			String this_year = formaty.format(now);// 当前年份
			String this_month = formatm.format(now);// 当前月份

			// 今年数据时间段
			String this_date_s = this_year + "-" + this_month + "-01 00:00:00";
			String this_date_e = this_year + "-" + this_month + "-"
			        + getMaxDay(Integer.valueOf(this_month), Integer.valueOf(this_year)) + " 23:59:59";

			// 数据查询的时间(当前年月)
			entity.getMap().put("this_year", this_year);
			entity.getMap().put("this_month", Integer.valueOf(this_month));
			entity.getMap().put("this_date_s", this_date_s);
			entity.getMap().put("this_date_e", this_date_e);

			List<KonkaR3Backmoney> sailList = super.getFacade().getKonkaR3BackmoneyService()
			        .getKonkaR3SailAndJsDataToIndexPaginatedList(entity);// 零售

			List<KonkaR3Backmoney> rwList = super.getFacade().getKonkaR3BackmoneyService()
			        .getKonkaR3BackmoneyFoRwDataPaginatedList(entity); // 回款任务

			String dy_js_money = "0";
			String dy_sail_money = "0";
			String dy_sail_num = "0";
			BigDecimal rw_wcl = new BigDecimal(0);
			if (sailList.size() > 0) {
				dy_sail_money = sailList.get(0).getMap().get("sale_all_price").toString();
				dy_sail_num = sailList.get(0).getMap().get("sale_num").toString();
				dy_js_money = sailList.get(0).getMap().get("js_money").toString();
			}

			if (rwList.size() > 0) {
				if (rwList.get(0) != null && rwList.get(0).getMap().get("rw_money") != null
				        && !"0".equals(rwList.get(0).getMap().get("rw_money").toString())) {
					rw_wcl = (new BigDecimal(dy_js_money)).divide(
					        new BigDecimal(rwList.get(0).getMap().get("rw_money").toString()), 2,
					        BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
				}
			}
			request.setAttribute("dy_js_money", dy_js_money);
			request.setAttribute("dy_sail_money", dy_sail_money);
			request.setAttribute("dy_sail_num", dy_sail_num);
			request.setAttribute("rw_wcl", rw_wcl);
			request.setAttribute("is_admin_data", "1");
		}
		// 待审订单开始 WangKunLin 2014-06-10
		PeProdUser peProdUser1 = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser1) {
			return null;
		}
		PeRoleUser _peRoleUser1 = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser1.getId());
		List<PeRoleUser> peRoleUserList1 = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser1);

		boolean role_id_eq_60 = false;
		for (PeRoleUser peRoleUser : peRoleUserList1) {
			if (peRoleUser.getRole_id() == 60L) {
				role_id_eq_60 = true;
				break;
			}
		}

		KonkaOrderInfo entity = new KonkaOrderInfo();
		entity.setIs_del(0);
		entity.setIs_submit(1);
		entity.getMap().put("where_by_process_id", "true");

		// 处理业务员特例
		if (role_id_eq_60) {
			// 业务员，业务员只能看见其下客户的待审核订单
			entity.getMap().put("querybycust_userid_eq", peProdUser1.getId()); // 按客户查询
		}
		entity.getMap().put("querybyrole_userid_eq", peProdUser1.getId()); // 按下一个审核角色查询

		// 数据级别判断开始
		Long dept_id = peProdUser1.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(peProdUser1.getId()); // 获取当前用户的最高可视部门级别
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(Long.valueOf(dept_id)); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				dept_id = dept_fgs.getDept_id(); // 分公司部门ID
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			dept_id = peProdUser1.getDept_id(); // 默认为当前用户所在部门
			break;
		case 0:
			entity.getMap().put("querybycust_userid_eq", peProdUser1.getId()); // 按客户查询
			break;
		default:
			// 出错
		}
		// 数据级别判断结束

		entity.getMap().put("par_or_children_dept_id", dept_id);
		entity.getMap().put("session_user_id", peProdUser1.getId());// 获取当前客户所查看的数据部门
		entity.setIs_submit(1); // 0:表示暂存；1：表示正式提交
		Pager pager = (Pager) dynaBean.get("pager");
		Long recordCount = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoSearchforR3CodeCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());

		List<KonkaOrderInfo> entityList = super.getFacade().getKonkaOrderInfoService()
		        .getKonkaOrderInfoResultForR3CodePaginatedList(entity);

		request.setAttribute("auditentityList", entityList);
		// 待审订单结束 WangKunLin 2014-06-10

		for (Long exd : EX_dept) {
			if (exd.equals(ui.getDept_id())) {
				// super.renderHtml(response,
				// "<span><font color='grey'>您不具备该页面查看权限</font></span>");
				return null;
			}
		}

		return mapping.findForward("mainFrame");
	}

	public ActionForward main1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		// DynaBean dynaBean = (DynaBean) form;
		// String url = (String) dynaBean.get("url");
		//
		// if (StringUtils.isNotBlank(url)) {
		// url = new DESPlus().decrypt(url);
		// super.renderJavaScript(response,
		// "window.onload=function(){location.href='" + url + "'}");
		// return null;
		// }
		return new ActionForward("/../manager/admin/Frames3/mainframe12.jsp");
	}

	public ActionForward main3(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String url = (String) dynaBean.get("url");
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		if (null == ui) {
			String msg = super.getMessage(request, "konka.zmd.userinfo.missing");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		if (StringUtils.isNotBlank(url)) {
			url = new DESPlus().decrypt(url);
			super.renderJavaScript(response, "window.onload=function(){location.href='" + url + "'}");
			return null;
		}
		// 默认当前月份任务显示
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		request.setAttribute("this_year", String.valueOf(calendar.get(Calendar.YEAR)));
		request.setAttribute("this_month", String.valueOf(calendar.get(Calendar.MONTH) + 1));

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(ui.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		request.setAttribute("peRoleUserList", peRoleUserList);

		// boolean role_id_ge_1000_le_1100 = false;
		boolean role_id_lt_30 = false; // 总部人员
		boolean role_id_btw_30_40 = false; // 分公司管理员
		boolean role_id_btw_40_60 = false; // 经办人员
		boolean role_id_eq_60 = false; // 业务员
		boolean role_id_gt_400 = false; // 业务员

		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() > 30 && peRoleUser.getRole_id() < 40) {
				role_id_btw_30_40 = true;
			}
			if (peRoleUser.getRole_id() >= 40 && peRoleUser.getRole_id() < 60) {
				role_id_btw_40_60 = true;
			}
			if (peRoleUser.getRole_id() < 30 || (peRoleUser.getRole_id() >= 200 & peRoleUser.getRole_id() < 300)) {
				role_id_lt_30 = true;
			}
			if (peRoleUser.getRole_id() == 60) {
				role_id_eq_60 = true;
			}
			if ((peRoleUser.getRole_id() < 200 && peRoleUser.getRole_id() > 60) || (peRoleUser.getRole_id() > 400)) {
				role_id_gt_400 = true;
			}
		}

		// 客户回款 start
		KonkaR3Backmoney backmoney = new KonkaR3Backmoney();
		backmoney.setYear(Long.valueOf(calendar.get(Calendar.YEAR)));
		backmoney.getMap().put("column_value", "aa.column_" + String.valueOf(calendar.get(Calendar.MONTH) + 1));
		if (!role_id_lt_30 && role_id_btw_30_40) {// 分公司人员
			backmoney.getMap().put("dept_id", getKonkaDeptForFgs(ui.getDept_id()).getDept_id());
		}
		if (!role_id_lt_30 && !role_id_btw_30_40 && role_id_btw_40_60) {// 经办人员
			backmoney.getMap().put("jb_id", ui.getDept_id());
		}
		if (!role_id_lt_30 && !role_id_btw_30_40 && !role_id_btw_40_60 && role_id_eq_60) {// 只有业务员
			backmoney.getMap().put("ywy_user_id", ui.getId());
		}
		if (!role_id_lt_30 && !role_id_btw_30_40 && !role_id_btw_40_60 && !role_id_eq_60 && role_id_gt_400) {// 其他人员
			backmoney.getMap().put("is_not_true", true);
		}

		List<KonkaR3Backmoney> kBackmoneysList = super.getFacade().getKonkaR3BackmoneyService()
		        .getKonkaR3BackmoneyForMonth(backmoney);
		request.setAttribute("back_money", kBackmoneysList.get(0).getMap().get("column_m"));
		// end

		request.setAttribute("user_id", ui.getId());
		return new ActionForward("/../manager/admin/Frames3/mainframe3.jsp");
	}

	public ActionForward main4(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(ui.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_188 = false; // 促销员
		boolean role_id_gt_30 = false; // 总部管理员
		String role_ids = "-1";
		for (PeRoleUser peRoleUser : peRoleUserList) {
			role_ids = role_ids + "," + peRoleUser.getRole_id();
			if (peRoleUser.getRole_id() == 188L) {
				role_id_eq_188 = true;
			}
			if (peRoleUser.getRole_id() < 30 || (peRoleUser.getRole_id() >= 200 && peRoleUser.getRole_id() < 300)) {
				role_id_gt_30 = true;
			}
		}

		// 禁止促销员访问
		if (role_id_eq_188) {
			// super.renderHtml(response, "Bad request. No permissions.");
			return null;
		}
		// 禁止促销员访问 结束

		Long sum_count = 0L;// 专卖店总数
		Long dy_xz_count = 0L;// 当月新增专卖店数量
		Long dsp_count = 0L;// 待审批
		Long sp_tg = 0L;// 当月审批通过
		Long hy_count = 0L;// 会员总数
		Long dy_hy_xz = 0L;// 当月会员新增

		Long dept_id = null;
		if (!role_id_gt_30) {
			dept_id = getKonkaDeptForFgs(ui.getDept_id()).getDept_id();
		}

		// 时间
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM");
		String nowDate = fmt.format(new Date());

		if (!role_id_eq_188) {
			// 统计专卖店总数
			KonkaXxZmdAuditUserInfo kInfo = new KonkaXxZmdAuditUserInfo();
			if (!role_id_gt_30) {
				kInfo.setDept_id(dept_id);
			}
			kInfo.getMap().put("arc_state", 20300);
			sum_count = super.getFacade().getKonkaXxZmdAuditUserInfoService()
			        .getKonkaXxZmdAndUserInfoZmdForCount(kInfo);

			// 当月新增专卖店数量
			KonkaXxZmdAuditUserInfo kInfo1 = new KonkaXxZmdAuditUserInfo();
			if (!role_id_gt_30) {
				kInfo1.setDept_id(dept_id);
			}
			kInfo1.getMap().put("add_date_ge", nowDate + "-01 00:00:00");
			kInfo1.getMap().put("is_not_pass", true);
			dy_xz_count = super.getFacade().getKonkaXxZmdAuditUserInfoService().getKonkaXxZmdAndUserInfoZmdForCount(
			        kInfo1);

			// 待审批专卖店数量
			KonkaXxZmdAuditUserInfo kInfo2 = new KonkaXxZmdAuditUserInfo();
			if (!role_id_gt_30) {
				kInfo2.setDept_id(dept_id);
			}
			kInfo2.getMap().put("is_dsp", true);
			dsp_count = super.getFacade().getKonkaXxZmdAuditUserInfoService().getKonkaXxZmdAndUserInfoZmdForCount(
			        kInfo2);

			// 当月审批通过
			KonkaXxZmdAuditUserInfo kInfo3 = new KonkaXxZmdAuditUserInfo();
			if (!role_id_gt_30) {
				kInfo3.setDept_id(dept_id);
			}
			kInfo3.getMap().put("arc_state", 20300);
			kInfo3.getMap().put("add_date_ge", nowDate + "-01 00:00:00");
			sp_tg = super.getFacade().getKonkaXxZmdAuditUserInfoService().getKonkaXxZmdAndUserInfoZmdForCount(kInfo3);

			// 会员总数
			MemberInfo memberInfo = new MemberInfo();
			memberInfo.setUser_state(0);
			hy_count = super.getFacade().getMemberInfoService().getMemberInfoCount(memberInfo);

			// 当月新增会员数
			memberInfo.getMap().put("add_date_ge", nowDate + "-01 00:00:00");
			dy_hy_xz = super.getFacade().getMemberInfoService().getMemberInfoCount(memberInfo);
		}

		request.setAttribute("sum_count", sum_count);
		request.setAttribute("dy_xz_count", dy_xz_count);
		request.setAttribute("dsp_count", dsp_count);
		request.setAttribute("sp_tg", sp_tg);
		request.setAttribute("hy_count", hy_count);
		request.setAttribute("dy_hy_xz", dy_hy_xz);

		List<KonkaoaEventInfo> list = new ArrayList<KonkaoaEventInfo>();

		// 资质审核
		KonkaXxZmdAuditUserInfo kInfo = new KonkaXxZmdAuditUserInfo();
		if (!role_id_gt_30) {
			kInfo.setDept_id(dept_id);
		}
		kInfo.getMap().put("db_role_ids", role_ids);
		List<KonkaXxZmdAuditUserInfo> kInfoList = super.getFacade().getKonkaXxZmdAuditUserInfoService()
		        .getKonkaXxZmdAuditUserInfoForRoleIdList(kInfo);
		if (kInfoList.size() > 0) {
			for (KonkaXxZmdAuditUserInfo temp1 : kInfoList) {
				KonkaoaEventInfo _tr = new KonkaoaEventInfo();
				if (null != temp1.getMap().get("role_id")) {
					// _tr.setEventiltle(temp1.getUser_name()+ "客户资质申请");
					_tr.getMap().put("title", temp1.getUser_name() + "客户资质申请");
					_tr.setEnterDate(temp1.getAdd_date());
					_tr.setEventType("专卖店资质申请");
					_tr.setFromPerson(temp1.getAdd_user_name());
					_tr
					        .setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../zmd/KonkaXxZmdAuditUserInfoAudit.do?method=audit&zmd_user_id="
					                + temp1.getZmd_user_id()
					                + "&role_id="
					                + temp1.getMap().get("role_id")
					                + "&mod_id=810200"
					                + "'\">审核</a> "
					                + "<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../zmd/KonkaXxZmdAuditUserInfoAudit.do?method=view&zmd_user_id="
					                + temp1.getZmd_user_id() + "&mod_id=810300" + "'\">查看</a>");
					list.add(_tr);
				}
			}
		}
		// 专卖店备案审核
		KonkaXxZmd zmd = new KonkaXxZmd();
		zmd.getMap().put("db_role_ids", role_ids);
		if (!role_id_gt_30) {
			zmd.setDept_id(dept_id);
		}
		List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdForRoleIdList(zmd);
		if (zmdList.size() > 0) {
			for (KonkaXxZmd temp1 : zmdList) {
				KonkaoaEventInfo _tr = new KonkaoaEventInfo();
				if (null != temp1.getMap().get("role_id")) {
					// _tr.setEventiltle("专卖店"+temp1.getZmd_sn()+ "备案申请");
					_tr.getMap().put("title", "专卖店" + temp1.getZmd_sn() + "备案申请");
					_tr.setEventType("专卖店资质申请");
					_tr.setFromPerson(temp1.getWrite_man());
					_tr.setEnterDate(temp1.getApply_date());
					_tr
					        .setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../zmd/KonkaXxZmdVerification.do?method=edit&zmd_id="
					                + temp1.getZmd_id()
					                + "&role_id="
					                + temp1.getMap().get("role_id")
					                + "&mod_id=810200"
					                + "'\">审核</a> "
					                + "<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../zmd/KonkaXxZmd.do?method=view&zmd_id="
					                + temp1.getZmd_id() + "&mod_id=810300" + "'\">查看</a>");
					list.add(_tr);
				}

			}
		}

		request.setAttribute("entityList", list);

		return new ActionForward("/../manager/admin/Frames3/mainframe4.jsp");
	}

	public ActionForward main5(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(ui.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_188 = false; // 促销员
		// String role_ids = "-1";
		for (PeRoleUser peRoleUser : peRoleUserList) {
			// role_ids = role_ids + "," + peRoleUser.getRole_id();
			if (peRoleUser.getRole_id() == 188L) {
				role_id_eq_188 = true;
			}
		}

		// 禁止促销员访问
		if (role_id_eq_188) {
			// super.renderHtml(response, "Bad request. No permissions.");
			return null;
		}
		// 禁止促销员访问 结束

		if (!role_id_eq_188) {
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

				if ("1".equals(t.getMap().get("agent_audit").toString())
				        || "0".equals(t.getMap().get("agent_audit").toString())) {
					if (t.getIs_node() == 1) {// 成都分公司
						_t
						        .setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../chengduoa/SelfEventCenter.do?method=edit&mod_id=951000&is_agent="
						                + ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0")
						                + "&agent_user_id="
						                + t.getCur_audit_user_id()
						                + "&file_type="
						                + t.getFile_type()
						                + "&id="
						                + t.getId() + "'\">审批</a>");
					} else {// 非成都分公司
						_t
						        .setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../oa/SelfEventCenter.do?method=edit&mod_id=991000&is_agent="
						                + ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0")
						                + "&agent_user_id="
						                + t.getCur_audit_user_id()
						                + "&file_type="
						                + t.getFile_type()
						                + "&id="
						                + t.getId() + "'\">审批</a>");
					}

				} else {
					if (t.getIs_node() == 1) {// 成都分公司
						_t
						        .setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../chengduoa/SelfEventCenter.do?method=edit&mod_id=951000&is_agent="
						                + ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0")
						                + "&agent_user_id="
						                + t.getCur_audit_user_id()
						                + "&file_type="
						                + t.getFile_type()
						                + "&id="
						                + t.getId() + "'\">会签</a>");
					} else {// 非成都分公司
						_t
						        .setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../oa/SelfEventCenter.do?method=edit&mod_id=991000&is_agent="
						                + ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0")
						                + "&agent_user_id="
						                + t.getCur_audit_user_id()
						                + "&file_type="
						                + t.getFile_type()
						                + "&id="
						                + t.getId() + "'\">会签</a>");
					}

				}
				String title = _t.getEventDo();
				title = title.replaceAll("会签", _t.getEventiltle()).replaceAll("审批", _t.getEventiltle());
				title = title.replace("<a", "<span").replace("</a>", "</span>");
				_t.getMap().put("title", title);
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
						_t
						        .setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../chengduoa/FilesSubmit.do?method=edit&id="
						                + t.getId() + "'\">审批</a>");
					} else {// 非成都分公司
						_t
						        .setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../oa/FilesSubmit.do?method=edit&id="
						                + t.getId() + "'\">审批</a>");
					}
				} else if (t.getFile_type() == 1) {
					_t.setEventType("费用申请");
					if (t.getIs_node() == 1) {// 成都分公司
						_t
						        .setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../chengduoa/ExpenseClaims.do?method=edit&file_id="
						                + t.getId() + "'\">审批</a>");
					} else {// 非成都分公司
						_t
						        .setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='../oa/ExpenseClaims.do?method=edit&file_id="
						                + t.getId() + "'\">审批</a>");
					}
				}
				String title = _t.getEventDo();
				title = title.replaceAll("审批", _t.getEventiltle());
				title = title.replace("<a", "<span").replace("</a>", "</span>");
				_t.getMap().put("title", title);
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

			// 我申请的事项
			KonkaoaFiles kf = new KonkaoaFiles();
			kf.setSubmit_user_id(ui.getId());
			kf.getRow().setFirst(0);
			kf.getRow().setCount(5);
			List<KonkaoaFiles> kfList = super.getFacade().getKonkaoaFilesService().getBaseKonkaoaFilesPaginatedList(kf);
			request.setAttribute("kfList", kfList);
		}

		return new ActionForward("/../manager/admin/Frames3/mainframe5.jsp");
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

	public int getMaxDay(int mm, int year) {
		int day = 0;
		if (mm == 1 || mm == 3 || mm == 5 || mm == 7 || mm == 8 || mm == 10 || mm == 12) {
			day = 31;
		} else if (mm == 2) {
			if (year % 4 == 0) {
				day = 29;
			} else {
				day = 28;
			}
		} else {
			day = 30;
		}
		return day;

	}

}
