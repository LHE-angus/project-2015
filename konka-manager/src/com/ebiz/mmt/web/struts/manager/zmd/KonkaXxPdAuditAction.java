package com.ebiz.mmt.web.struts.manager.zmd;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxPd;
import com.ebiz.mmt.domain.KonkaXxPdHistory;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaXxPdAuditAction extends BaseZmdAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return this.list(mapping, form, request, response);
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
		// String par_cls_id = (String) dynaBean.get("cls_id");
		String audit_state = (String) dynaBean.get("audit_state");
		String md_name_like = (String) dynaBean.get("md_name_like");
		KonkaXxPd entity = new KonkaXxPd();

		// if (StringUtils.isNotBlank(par_cls_id)) {
		// entity.getMap().put("par_cls_id", par_cls_id);
		// }
		if (StringUtils.isNotBlank(audit_state)) {
			entity.setAudit_state(Integer.valueOf(audit_state));
		}

		if (StringUtils.isNotBlank(md_name_like))
			entity.getMap().put("md_name_like", md_name_like);

		PeProdUser user_id = super.getSessionUserInfo(request);
		// 取用户角色
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(user_id.getId());

		Boolean role_id_gt_400 = false;// 专卖店人员
		Boolean role_id_eq_350 = false;// 专卖店人员
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if (temp.getRole_id() == 350) {
					role_id_eq_350 = true;
				}
			}
		}

		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		if(role_id_eq_350){
			request.setAttribute("is_cw", "1");
		}
		
		KonkaDept fgs = getKonkaDeptForFgs(user_id.getDept_id());
		if (null != fgs) {
			entity.setDept_id(fgs.getDept_id());
		} else {
			entity.setDept_id(user_id.getDept_id());
		}

		request.setAttribute("basePdClazzList", getBasePdClazzList());

		Long recordCount = super.getFacade().getKonkaXxPdService().getKonkaXxPdWithUserCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaXxPd> entityList = super.getFacade().getKonkaXxPdService().getKonkaXxWithUsersPdPaginatedList(entity);

		if (entityList.size() > 0) {
			for (KonkaXxPd temp : entityList) {
				if (null != temp.getFac_sn() && null != temp.getStore_sn() && null != temp.getStore_sn())
					temp.getMap().put("fac_store_name",
							temp.getFac_desc() + "[" + temp.getFac_sn() + "," + temp.getStore_sn() + "]");
			}
		}

		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String dept_pd_id = (String) dynaBean.get("dept_pd_id");
		String dept_id = (String) dynaBean.get("dept_id");

		if (!GenericValidator.isLong(dept_pd_id)) {
			this.saveError(request, "errors.long", new String[] { dept_pd_id });
			return mapping.findForward("list");
		}

		KonkaXxPd entity = new KonkaXxPd();
		entity.setDept_pd_id(Long.valueOf(dept_pd_id));
		entity = super.getFacade().getKonkaXxPdService().getKonkaXxPd(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(Long.valueOf(dept_id));
		List<KonkaDept> konkaDeptsList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		if (null != konkaDeptsList && konkaDeptsList.size() > 0) {
			request.setAttribute("dept_name", konkaDeptsList.get(0).getDept_name());
		}

		super.copyProperties(form, entity);

		// 回显仓库信息
		if (null != entity.getFac_sn() && null != entity.getStore_sn() && null != entity.getStore_sn())
			request.setAttribute("fac_store_name", entity.getFac_desc() + "[" + entity.getFac_sn() + "," + entity.getStore_sn()
					+ "]");

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String dept_pd_id = (String) dynaBean.get("dept_pd_id");
		String audit_state = (String) dynaBean.get("audit_state");
		PeProdUser userInfo = super.getSessionUserInfo(request);

		KonkaXxPd entity = new KonkaXxPd();

		if (audit_state.equals("1")) {// 审核通过
			entity.setDept_pd_id(Long.valueOf(dept_pd_id));
			entity = super.getFacade().getKonkaXxPdService().getKonkaXxPd(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			entity.setAudit_state(1);
			entity.setAudit_user_id(userInfo.getId());
			entity.setAudit_date(new Date());
			super.copyProperties(form, entity);
			super.getFacade().getKonkaXxPdService().modifyKonkaXxPdForHistory(entity);
			saveMessage(request, "entity.updated");
		} else {// 审核不通过
			KonkaXxPdHistory konkaXxPdHistory = new KonkaXxPdHistory();
			konkaXxPdHistory.setDept_pd_id(Long.valueOf(dept_pd_id));

			List<KonkaXxPdHistory> konkaXxPdHistoryList = super.getFacade().getKonkaXxPdHistoryService()
					.getKonkaXxPdHistoryListForHistoryId(konkaXxPdHistory);

			if (null != konkaXxPdHistoryList && konkaXxPdHistoryList.size() > 0) {
				KonkaXxPdHistory entity1 = new KonkaXxPdHistory();
				entity1.setHistory_id(konkaXxPdHistoryList.get(0).getHistory_id());
				entity1 = super.getFacade().getKonkaXxPdHistoryService().getKonkaXxPdHistory(entity1);

				KonkaXxPd konkaXxPd = new KonkaXxPd();
				konkaXxPd.setDept_pd_id(entity1.getDept_pd_id());
				konkaXxPd.setDept_id(entity1.getDept_id());
				konkaXxPd.setAdd_user_id(entity1.getAdd_user_id());
				konkaXxPd.setAdd_date(entity1.getAdd_date());
				konkaXxPd.setDown_time(entity1.getDown_time());
				konkaXxPd.setMd_name(entity1.getMd_name());
				konkaXxPd.setPd_cls(entity1.getPd_cls());
				konkaXxPd.setPd_cls_name(entity1.getPd_cls_name());
				konkaXxPd.setPrice_min(entity1.getPrice_min());
				konkaXxPd.setUp_time(entity1.getUp_time());
				konkaXxPd.setPrice_ref(entity1.getPrice_ref());
				konkaXxPd.setRemarks(entity1.getRemarks());
				konkaXxPd.setFac_sn(entity1.getFac_sn());
				konkaXxPd.setFac_desc(entity1.getFac_desc());
				konkaXxPd.setStore_sn(entity1.getStore_sn());
				konkaXxPd.setAudit_state(1);
				super.getFacade().getKonkaXxPdService().modifyKonkaXxPd(konkaXxPd);
				saveMessage(request, "entity.updated");
			} else {
				entity.setDept_pd_id(Long.valueOf(dept_pd_id));
				entity = super.getFacade().getKonkaXxPdService().getKonkaXxPd(entity);
				if (null == entity) {
					saveMessage(request, "entity.missing");
					return mapping.findForward("list");
				}

				entity.setAudit_state(2);
				entity.setAudit_user_id(userInfo.getId());
				entity.setAudit_date(new Date());
				super.copyProperties(form, entity);
				super.getFacade().getKonkaXxPdService().modifyKonkaXxPd(entity);
				saveMessage(request, "entity.updated");
			}
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
}
