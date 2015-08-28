package com.ebiz.mmt.web.struts.manager.zmd;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Ren,zhong
 * @version 2011-04-05
 */
public class KonkaXxZmdPosAllocationAction extends BaseZmdAction {

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String msg = request.getParameter("msg");
		String zmd = request.getParameter("zmd");
		String zmd_sn_like = (String) dynaBean.get("zmd_sn_like");
		String pos_sn_like = (String) dynaBean.get("pos_sn_like");

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		KonkaXxZmd entity = new KonkaXxZmd();
		KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
		if (kDept != null)
			entity.setDept_id(ui.getDept_id());

		entity.getMap().put("posNotNull", "true");
		entity.setArc_state(20300);
		entity.getMap().put("zmd_sn_like", zmd_sn_like);
		entity.getMap().put("pos_sn_like", pos_sn_like);

		Long recordCount = getFacade().getKonkaXxZmdService().getKonkaXxZmdCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());
		List<KonkaXxZmd> list = getFacade().getKonkaXxZmdService().getKonkaXxZmdPaginatedList(entity);

		request.setAttribute("entityList", list);

		if ("1".equals(msg)) {
			msg = super.getMessage(request, "konka.zmd.pos.allocation.success", new String[] { zmd });
			dynaBean.set("msg", msg);
		} else if ("2".equals(msg)) {
			msg = super.getMessage(request, "konka.zmd.pos.allocation.delete.success");
			dynaBean.set("msg", msg);
		}

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		// DynaBean dynaBean = (DynaBean) form;
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		Long dept_id = null;
		KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
		if (kDept != null)
			dept_id = kDept.getDept_id();

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(dept_id);
		konkaDept = getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		request.setAttribute("konkaDept", konkaDept);

		KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
		konkaXxZmd.setDept_id(dept_id);
		konkaXxZmd.getMap().put("posIsNull", "true");
		konkaXxZmd.setArc_state(20300);
		List<KonkaXxZmd> zmdList = getFacade().getKonkaXxZmdService().getKonkaXxZmdList(konkaXxZmd);
		request.setAttribute("zmdList", zmdList);

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String zmd_id = (String) dynaBean.get("zmd_id");

		if (StringUtils.isBlank(zmd_id)) {
			super.renderJavaScript(response, "alert('数据丢失！');history.back();");
			return null;
		}

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		Long dept_id = null;
		KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
		if (kDept != null)
			dept_id = kDept.getDept_id();
		
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(dept_id);
		konkaDept = getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		request.setAttribute("konkaDept", konkaDept);

		KonkaXxZmd entity = new KonkaXxZmd();
		entity.setZmd_id(Long.valueOf(zmd_id));
		entity.setArc_state(20300);
		entity = getFacade().getKonkaXxZmdService().getKonkaXxZmd(entity);
		super.copyProperties(form, entity);

		KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
		konkaXxZmd.setDept_id(dept_id);
		konkaXxZmd.getMap().put("posIsNull", "true");
		konkaXxZmd.setArc_state(20300);
		konkaXxZmd.getMap().put("posIn", entity.getZmd_id());
		List<KonkaXxZmd> zmdList = getFacade().getKonkaXxZmdService().getKonkaXxZmdList(konkaXxZmd);
		request.setAttribute("zmdList", zmdList);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String zmd_id = (String) dynaBean.get("zmd_id");
		String msg = "";
		String zmd_sn = "";

		KonkaXxZmd entity = new KonkaXxZmd();
		super.copyProperties(entity, form);

		if (StringUtils.isBlank(zmd_id)) {
			renderJavaScript(response, "alert('参数丢失！');history.back();");
			return null;
		}

		KonkaXxZmd zmd = new KonkaXxZmd();
		zmd.setZmd_id(Long.valueOf(zmd_id));
		zmd.setArc_state(20300);
		zmd = getFacade().getKonkaXxZmdService().getKonkaXxZmd(zmd);
		if (null != zmd) {
			zmd_sn = zmd.getZmd_sn();
		}

		super.getFacade().getKonkaXxZmdService().modifyKonkaXxZmdForPos(entity);
		msg = "1";

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("msg=" + msg);
		pathBuffer.append("&").append("zmd=" + zmd_sn);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String zmd_id = (String) dynaBean.get("zmd_id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");
		String msg = "";

		KonkaXxZmd entity = new KonkaXxZmd();

		if (StringUtils.isNotBlank(zmd_id)) {
			entity.setZmd_id(Long.valueOf(zmd_id));
			super.getFacade().getKonkaXxZmdService().deleteKonkaXxZmdForPosAllocation(entity);
		} else if (ArrayUtils.isNotEmpty(pks)) {
			entity.getMap().put("pks", pks);
			super.getFacade().getKonkaXxZmdService().deleteKonkaXxZmdForPosAllocation(entity);
		}
		msg = "2";

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("msg=" + msg);
		// pathBuffer.append("&").append("zmd=" + zmd_sn);
		// pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward validatePos(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String zmd_id = (String) dynaBean.get("zmd_id");
		String pos_sn = (String) dynaBean.get("pos_sn");

		String result = "";

		KonkaXxZmd entity = new KonkaXxZmd();
		entity.setPos_sn(pos_sn);
		entity.getMap().put("notIncZmd", zmd_id);
		Long recordCount = getFacade().getKonkaXxZmdService().getKonkaXxZmdCount(entity);

		if (0L == recordCount) {
			result = "1";
		} else {
			result = "2";
		}

		renderJson(response, result);
		return null;
	}

}
