package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.FighterInfo;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaProSq;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaProBeiAnAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
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
		Pager pager = (Pager) dynaBean.get("pager");
		String file_no_like = (String) dynaBean.get("file_no_like");
		String pro_name_like = (String) dynaBean.get("pro_name_like");
		String add_time_start = (String) dynaBean.get("add_time_start");
		String add_time_end = (String) dynaBean.get("add_time_end");
		String excel_all = (String) dynaBean.get("excel_all");
		String pro_state = (String) dynaBean.get("pro_state");
		String area_id = (String) dynaBean.get("area_id");
		String dept_id = (String) dynaBean.get("dept_id");

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		boolean role_id_eq_10 = false;

		for (PeRoleUser peRoleUser : peRoleUserList) {
			// 总部管理员
			if (peRoleUser.getRole_id() <= 10) {
				role_id_eq_10 = true;
				request.setAttribute("is_admin", true);
				break;
			}
		}
		KonkaProSq entity = new KonkaProSq();

		if (StringUtils.isNotBlank(file_no_like)) {
			entity.getMap().put("file_no_like", file_no_like);
		}
		if (StringUtils.isNotBlank(pro_name_like)) {
			entity.getMap().put("pro_name_like", pro_name_like);
		}
		if (StringUtils.isNotBlank(add_time_start)) {
			entity.getMap().put("add_time_start", add_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_time_end)) {
			entity.getMap().put("add_time_end", add_time_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(pro_state)) {
			entity.setPro_state(Integer.valueOf(pro_state));
		}
		if (StringUtils.isNotBlank(area_id)) {
			entity.setArea_id(Integer.valueOf(area_id));
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
		}

		entity.setIs_del(0);// 未删除

		// 状态为保存的看不到
		entity.getMap().put("file_state_begin", "0");
		entity.getMap().put("file_state_end", "5");

		Long recordCount = getFacade().getKonkaProSqService().getKonkaProSqCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaProSq> entityList = getFacade().getKonkaProSqService().getKonkaProSqPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			if (recordCount.intValue() > 5000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
				        + "！');history.back();");
				return null;
			}
			entity.getRow().setCount(recordCount.intValue());
			List<KonkaProSq> entityList1 = getFacade().getKonkaProSqService().getKonkaProSqAndFighterInfoPaginatedList(
			        entity);
			dynaBean.set("excel_all", excel_all);
			request.setAttribute("allList", entityList1);
		}

		return mapping.findForward("list");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaProSq entity = new KonkaProSq();
			entity.setId(new Long(id));
			entity.setIs_del(1);
			getFacade().getKonkaProSqService().modifyKonkaProSq(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaProSq entity = new KonkaProSq();
			entity.getMap().put("pks", pks);
			for (String xx : pks) {
				entity.setId(new Long(xx));
				entity.setIs_del(1);
				getFacade().getKonkaProSqService().modifyKonkaProSq(entity);
			}
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaProSq entity = new KonkaProSq();
		entity.setId(new Long(id));
		entity.setPro_state(1);// 跟踪中
		super.getFacade().getKonkaProSqService().modifyKonkaProSq(entity);

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaProSq entity = new KonkaProSq();
		entity.setId(new Long(id));
		entity = getFacade().getKonkaProSqService().getKonkaProSq(entity);

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(entity.getDept_id());
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		request.setAttribute("dept_name", konkaDept.getDept_name());

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		super.copyProperties(form, entity);

		FighterInfo ft = new FighterInfo();
		ft.setPro_id(entity.getId());
		List<FighterInfo> fighterInfoList = super.getFacade().getFighterInfoService().getFighterInfoList(ft);

		request.setAttribute("fighterInfoList", fighterInfoList);

		return mapping.findForward("view");

	}
}
