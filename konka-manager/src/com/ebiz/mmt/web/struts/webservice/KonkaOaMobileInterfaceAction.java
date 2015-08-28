package com.ebiz.mmt.web.struts.webservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.KonkaoaFilesAuditAgentUser;
import com.ebiz.mmt.domain.KonkaoaSsuedDocument;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * @author Hu,Hao
 * @version 2013-12-17
 */
public class KonkaOaMobileInterfaceAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.getJson(mapping, form, request, response);
	}

	public ActionForward getJson(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// Pager pager = (Pager) dynaBean.get("pager");

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		// 用户名
		String user_id = (String) dynaBean.get("user_id");
		// 密码
		String userpass = (String) dynaBean.get("userpass");
		// 类型
		String type = (String) dynaBean.get("type");
		
		// 时间
		String start_date = (String) dynaBean.get("start_date");
		
		//版本号,android_version,ios_version
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		// 用户名或者密码不存在， 资讯信息类别为公开
		PeProdUser ui = checkUserid(user_id, userpass,android_version,ios_version);
		if (null == ui) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}
		// type:1-待办文件，2-下发文件，3-公告通知，4待审核订单
		if (StringUtils.isBlank(type)) {
			type = "1";
		}

		List<HashMap<String, String>> entityList = new ArrayList<HashMap<String, String>>();

		if ("1".equals(type)) {// 待审核
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

			if (cList.size() > 0) {
				for (KonkaoaFiles t : cList) {
					// i++;
					HashMap<String, String> map = new HashMap<String, String>();
					// map.put("num", "" + i);
					map.put("title", t.getFile_title());
					map.put("add_date", format.format(t.getSubmit_datetime()));
					map.put("data_source", t.getSubmit_user());
					entityList.add(map);
				}
			}

			KonkaoaFiles file5 = new KonkaoaFiles();
			file5.setFile_status(0);
			file5.setIs_del(0);
			file5.setSubmit_user_id(ui.getId());
			List<KonkaoaFiles> file5List = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesList(file5);

			if (file5List.size() > 0) {
				for (KonkaoaFiles t : file5List) {
					// i++;
					HashMap<String, String> map = new HashMap<String, String>();
					// map.put("num", "" + i);
					map.put("title", t.getFile_title());
					map.put("add_date", format.format(t.getSubmit_datetime()));
					map.put("data_source", t.getSubmit_user());
					entityList.add(map);
				}
			}
		} else if ("2".equals(type)) {// 下发文件

			KonkaoaSsuedDocument _entity = new KonkaoaSsuedDocument();
			_entity.getMap().put("uid", ui.getId());
			_entity.getMap().put("receive_org_id", ui.getDept_id());
			if (StringUtils.isNotBlank(start_date)) {
				_entity.getMap().put("start_date", start_date);
			}

			// 设置只显示5条数据，更多的数据点连接到行政办公系统的下发中进行查询
			_entity.getRow().setFirst(0);
			_entity.getRow().setCount(5);

			List<KonkaoaSsuedDocument> list = super.getFacade().getKonkaoaFilesService()
					.getKonkaoaSsuedDocumentPaginatedList(_entity);
			if (list.size() > 0) {
				for (KonkaoaSsuedDocument k : list) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("title", k.getTitle());
					map.put("add_date", format.format(k.getAdd_date()));
					map.put("data_source", k.getPass_man_name() + "/" + k.getPart_name());
					entityList.add(map);
				}
			}
		} else if ("4".equals(type)) {

			KonkaOrderInfo entity = new KonkaOrderInfo();
			entity.setIs_del(0);
			entity.setIs_submit(1);
			entity.getMap().put("where_by_process_id", "true");

			entity.getMap().put("querybyrole_userid_eq", ui.getId()); // 按下一个审核角色查询

			// 数据级别判断开始
			Long dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
			logger.info("Max level : {}", max_dlevel);
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
				dept_id = ui.getDept_id(); // 默认为当前用户所在部门
				break;
			case 0:
				entity.getMap().put("querybycust_userid_eq", ui.getId()); // 按客户查询
				break;
			default:
				// 出错
			}
			// 数据级别判断结束

			if (StringUtils.isNotBlank(start_date)) {
				entity.getMap().put("start_date", start_date);
			}
			
			entity.getMap().put("par_or_children_dept_id", dept_id);
			entity.setIs_submit(1); // 0:表示暂存；1：表示正式提交

			entity.getRow().setCount(5);
			entity.getRow().setFirst(0);

			List<KonkaOrderInfo> list = super.getFacade().getKonkaOrderInfoService()
					.getKonkaOrderInfoResultForR3CodePaginatedList(entity);
			if (list.size() > 0) {
				for (KonkaOrderInfo o : list) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("title", o.getTrade_index());
					map.put("add_date", format.format(o.getAdd_date()));
					map.put("data_source", o.getAdd_user_name());
					entityList.add(map);
				}
			}
		}

		super.renderTextJsonOrJsonp(request, response, JSON.toJSONString(entityList));
		return null;
	}
}
