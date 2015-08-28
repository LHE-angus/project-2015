package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Wu,ShangLong
 * @version 2013-08-09
 */
public class UserListAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");
		String role_id = (String) dynaBean.get("role_id");
		String cust_id = (String) dynaBean.get("cust_id");

		PeProdUser user = new PeProdUser();
		
		PeRoleInfo r = new PeRoleInfo();
		r.setRole_id(Long.valueOf(role_id));
		r = super.getFacade().getPeRoleInfoService().getPeRoleInfo(r);
		if (null != r && r.getD_level() != null) {
			switch (r.getD_level()) {
			case 9:
				// 集团及以下部门可见
				dept_id = "0";
				break;
			case 8:
				// 分公司及以下部门可见
				if (GenericValidator.isLong(dept_id)) {
					KonkaDept dept = super.getKonkaDeptForFgs(Long.valueOf(dept_id));
					if (null != dept && null != dept.getDept_id()) {
						dept_id = dept.getDept_id().toString();
					}
				}
				break;
			case 7:
				// 我所在的部门及以下部门可见
				break;
			case 0:
				// 无部门查看权限，仅限自己管理的数据
				if (GenericValidator.isLong(cust_id)) {
					BranchAssign ba = new BranchAssign();
					ba.setKonka_r3_id(Long.valueOf(cust_id));
					ba = super.getFacade().getBranchAssignService().getBranchAssign(ba);

					if (null != ba) {
						user.setId(ba.getUser_id());
					}
				}
				break;
			default:
				// 出错
			}
		}

		if (StringUtils.isNotBlank(dept_id)) {
			user.getMap().put("par_or_children_dept_id", dept_id);
		}
		if (StringUtils.isNotBlank(role_id)) {
			user.getMap().put("role_id", role_id);
		}
		user.setIs_del(0);

		List<PeProdUser> userList = getFacade().getPeProdUserService().getPeProdUserListByDeptIdAndRoleId(user);

		request.setAttribute("userList", userList);

		return mapping.findForward("list");
	}
}