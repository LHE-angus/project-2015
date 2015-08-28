package com.ebiz.mmt.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaOrderInfoTransEnsu;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.org.apache.commons.lang3.StringUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaOrderInfoTransEnsuAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		String dept_id = (String) dynaBean.get("dept_id");// 分 公 司
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");// 分 公 司
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");// 分 公 司
		String trans_ensu_status = (String) dynaBean.get("trans_ensu_status");// 签收状态
		String trans_ensu_type = (String) dynaBean.get("trans_ensu_type");// 签收方式
		// String trans_index_like = (String)
		// dynaBean.get("trans_index_like");// 发货单号
		String trans_index_detail_like = (String) dynaBean.get("trans_index_detail_like");// 发货单号
		String r3_vbedl_like = (String) dynaBean.get("r3_vbedl_like");// 3物流单号
		String r3_vbeln_like = (String) dynaBean.get("r3_vbeln_like");// 3物流单号
		String trans_ensu_date_s = (String) dynaBean.get("trans_ensu_date_s");// 签收日期
		String trans_ensu_date_e = (String) dynaBean.get("trans_ensu_date_e");// 签收日期
		String customer_name_like = (String) dynaBean.get("customer_name_like");// 客户名称
		String r3_code_like = (String) dynaBean.get("r3_code_like");// R3客户编码

		KonkaOrderInfoTransEnsu konkaOrderInfoTransEnsu = new KonkaOrderInfoTransEnsu();

		if (StringUtils.isNotBlank(trans_ensu_status)) {
			konkaOrderInfoTransEnsu.setTrans_ensu_status(Integer.parseInt(trans_ensu_status));
		}
		if (StringUtils.isNotBlank(trans_ensu_type)) {
			konkaOrderInfoTransEnsu.getMap().put("trans_ensu_type", trans_ensu_type);
		}
		if (StringUtils.isNotBlank(trans_index_detail_like)) {
			konkaOrderInfoTransEnsu.getMap().put("trans_index_detail_like", trans_index_detail_like);
		}
		if (StringUtils.isNotBlank(r3_vbedl_like)) {
			konkaOrderInfoTransEnsu.getMap().put("r3_vbedl_like", r3_vbedl_like);
		}
		if (StringUtils.isNotBlank(r3_vbeln_like)) {
			konkaOrderInfoTransEnsu.getMap().put("r3_vbeln_like", r3_vbeln_like);
		}
		if (StringUtils.isNotBlank(customer_name_like)) {
			konkaOrderInfoTransEnsu.getMap().put("customer_name_like", customer_name_like);
		}
		if (StringUtils.isNotBlank(trans_ensu_date_s)) {
			konkaOrderInfoTransEnsu.getMap().put("trans_ensu_date_s", trans_ensu_date_s);
		}
		if (StringUtils.isNotBlank(trans_ensu_date_e)) {
			konkaOrderInfoTransEnsu.getMap().put("trans_ensu_date_e", trans_ensu_date_e);
		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			konkaOrderInfoTransEnsu.getMap().put("par_dept_id", l5_dept_id);
		} else if (StringUtils.isNotBlank(l4_dept_id)) {
			konkaOrderInfoTransEnsu.getMap().put("par_dept_id", l4_dept_id);
		} else if (StringUtils.isNotBlank(dept_id)) {
			konkaOrderInfoTransEnsu.getMap().put("par_dept_id", dept_id);
		}
		if (StringUtils.isNotBlank(r3_code_like)) {
			konkaOrderInfoTransEnsu.getMap().put("r3_code_like", r3_code_like);
		}

		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		boolean role_id_eq_10 = false; // 是否为系统管理员
		List<Long> role_ids = new ArrayList<Long>(); // 当前登录用户的角色列表
		for (PeRoleUser peRoleUser : peRoleUserList) {
			role_ids.add(peRoleUser.getRole_id());

			if (peRoleUser.getRole_id() >= 10L && peRoleUser.getRole_id() < 30L) {
				role_id_eq_10 = true;
			}
		}

		if (role_id_eq_10) {// 系统管理员

		} else {
			// 数据级别判断开始
			Long __dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
			request.setAttribute("max_dlevel", max_dlevel);
			logger.info("Max level : {}", max_dlevel);
			switch (max_dlevel) {
			case 9:
				// 集团及以下部门可见
				__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
				break;
			case 8:
				// 分公司及以下部门可见
				KonkaDept dept_fgs = super.getKonkaDeptForFgs(Long.valueOf(__dept_id)); // 查询部门分公司
				if (null != dept_fgs && null != dept_fgs.getDept_id()) {
					__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				}
				break;
			case 7:
				// 我所在的部门及以下部门可见
				__dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
				break;
			case 0:
				break;
			default:
				// 出错
			}
			// 数据级别判断结束
			konkaOrderInfoTransEnsu.getMap().put("session_user_id", userInfo.getId());// 获取当前客户所查看的数据部门
			konkaOrderInfoTransEnsu.getMap().put("par_or_children_dept_id", __dept_id);
		}
		Long recordCount = super.getFacade().getKonkaOrderInfoTransEnsuService()
				.getKonkaOrderInfoTransEnsuAndDetailsCount(konkaOrderInfoTransEnsu);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		konkaOrderInfoTransEnsu.getRow().setCount(pager.getRowCount());
		konkaOrderInfoTransEnsu.getRow().setFirst(pager.getFirstRow());
		List<KonkaOrderInfoTransEnsu> ensuList = super.getFacade().getKonkaOrderInfoTransEnsuService()
				.getKonkaOrderInfoTransEnsuAndDetailsPaginatedList(konkaOrderInfoTransEnsu);
		request.setAttribute("entityList", ensuList);

		return mapping.findForward("list");
	}
}
