package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Liu,ZhiXiang
 * @version 2013-09-28
 */
public class DiaLogAction extends BaseAction {

	public ActionForward selectUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// String orgId = (String) dynaBean.get("myselect");// 部门ID，如果不传择默认为1
		String selectedUsersID = (String) dynaBean.get("selectedUsersID");// 已经选择的人员ID字符串，格式：***,**,**
		String key_word = (String) dynaBean.get("key_word"); // 搜索关键字 姓名
		// String selectype = (String) dynaBean.get("selectype");// 传值为：signal时表示单选；不传则表示多选
		String selectedIds = (String) dynaBean.get("selectedIds");// 已经选择的人员ID字符串，格式：***,**,**,用于审批流程

		if (!"".equals(selectedUsersID) && selectedUsersID != null && selectedUsersID.indexOf(",") > 0) {
			selectedUsersID = selectedUsersID.substring(0, selectedUsersID.length() - 1);
		}

		if (!"".equals(selectedIds) && selectedIds != null && selectedIds.indexOf(",") > 0) {
			dynaBean.set("selectedIds", selectedIds.substring(0, selectedIds.length() - 1));
			selectedIds = selectedIds.substring(0, selectedIds.length() - 1);
		}

		PeProdUser peProdUser = new PeProdUser();
		peProdUser.setIs_del(0);

		if (StringUtils.isNotBlank(key_word)) {
			peProdUser.getMap().put("key_word_like", key_word);
		}

		if (!"".equals(selectedUsersID) && selectedUsersID != null && !"".equals(selectedIds) && selectedIds != null) {
			String sumIds = selectedUsersID + "," + selectedIds;
			if (!",".equals(StringUtils.trim(sumIds))) {
				peProdUser.getMap().put("id_not_in", sumIds);
			}
		} else {
			if (!"".equals(selectedUsersID) && selectedUsersID != null) {
				peProdUser.getMap().put("id_not_in", selectedUsersID);
			} else {
				peProdUser.getMap().put("id_not_in", selectedIds);
			}
		}

		peProdUser.getMap().put("role_id", "8000");
		List<PeProdUser> peProdUserList = super.getFacade().getPeProdUserService().getPeProdUserList(peProdUser);
		// String noStr = super.getSysSetting("authorisedUserName");
		for (PeProdUser _t : peProdUserList) {
			_t.getMap().put(
					"department",
					_t.getDepartment() == null ? _t.getReal_name()
							: (_t.getDepartment() + "(" + _t.getReal_name() + ")"));
		}
		request.setAttribute("entityList", peProdUserList);

		if (StringUtils.isNotBlank(selectedUsersID)) {
			PeProdUser user = new PeProdUser();
			user.setIs_del(0);
			user.getMap().put("ids", selectedUsersID);
			// user.getMap().put("id_not_in", null);
			List<PeProdUser> selectPeProdUserList = super.getFacade().getPeProdUserService().getPeProdUserList(user);
			for (PeProdUser _t : selectPeProdUserList) {
				_t.getMap().put(
						"department",
						_t.getDepartment() == null ? _t.getReal_name()
								: (_t.getDepartment() + "(" + _t.getReal_name() + ")"));
			}
			request.setAttribute("selectList", selectPeProdUserList);
		}

		// PeRoleUser peRoleUser = (PeRoleUser) super.getSessionAttribute(request, Constants.ROLE_INFO);

		String address = "/admin/Dialog/selectUser.jsp?1=1&";

		return new ActionForward(address);
	}

	public ActionForward getQueryUserNames(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String key_word = (String) dynaBean.get("key_word");
		// String role_id = (String) dynaBean.get("role_id");// 角色ID
		String selectedUsersID = (String) dynaBean.get("selectedUsersID");// 已经选择的人员ID字符串，格式：***,**,**
		String selectedIds = (String) dynaBean.get("selectedIds");// 已经选择的人员ID字符串，格式：***,**,**,用于审批流程

		StringBuffer sb = new StringBuffer("[");

		PeProdUser peProdUser = new PeProdUser();

		if (!"".equals(selectedUsersID) && selectedUsersID != null && selectedUsersID.indexOf(",") > 0) {
			selectedUsersID = selectedUsersID.substring(0, selectedUsersID.length());
		}

		peProdUser.setIs_del(new Integer(0));
		peProdUser.getMap().put("key_word_like", key_word);
		// peProdUser.getMap().put("role_id_not_null", role_id);

		if ("" != selectedUsersID && selectedUsersID != null && "" != selectedIds && selectedIds != null) {
			String sumIds = selectedUsersID + "," + selectedIds;
			peProdUser.getMap().put("id_not_in", sumIds);
		} else {
			if ("" != selectedUsersID && selectedUsersID != null) {
				peProdUser.getMap().put("id_not_in", selectedUsersID);
			} else {
				peProdUser.getMap().put("id_not_in", selectedIds);
			}
		}
		// peProdUser.getMap().put("dept_id_in", role_id);

		// peProdUser.getMap().put("user_id", _peProdUser.getId());

		peProdUser.getMap().put("role_id", "8000");
		List<PeProdUser> peProdUserList = getFacade().getPeProdUserService().getPeProdUserList(peProdUser);
		for (PeProdUser entity : peProdUserList) {
			sb.append("{\"user_id\":\"").append(String.valueOf(entity.getId())).append("\",");
			sb.append("\"user_name\":\"");
			sb.append(StringEscapeUtils.escapeJavaScript(entity.getDepartment() == null ? entity.getReal_name()
					: (entity.getDepartment() + "(" + entity.getReal_name() + ")")));
			sb.append("\"},");
		}
		sb.append("{\"end\":\"\"}]");

		super.renderJson(response, sb.toString());

		return null;
	}

}
