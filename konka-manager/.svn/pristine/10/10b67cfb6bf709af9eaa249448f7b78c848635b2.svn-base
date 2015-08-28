package com.ebiz.mmt.web.struts.manager.jf;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JfGiftInfo;
import com.ebiz.mmt.domain.JfScorts;
import com.ebiz.mmt.domain.JfScortsDetails;
import com.ebiz.mmt.domain.JfScortsExchange;
import com.ebiz.mmt.domain.MemberInfo;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wu,ShangLong
 * @version 2013-06-26
 */
public class JfExchangeAction extends BaseJfAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String user_sn_like = (String) dynaBean.get("user_sn_like");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		JfScorts entity = new JfScorts();
		if (StringUtils.isNotBlank(user_sn_like)) {
			entity.getMap().put("user_sn_like", user_sn_like);
		}
		entity.setIs_del(0);

		Long recordCount = super.getFacade().getJfScortsService().getJfScortsCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<JfScorts> entityList = super.getFacade().getJfScortsService().getJfScortsPaginatedList(entity);

		if (null != entityList && entityList.size() > 0) {
			for (JfScorts temp : entityList) {
				MemberInfo mem = new MemberInfo();
				mem.setUser_sn(temp.getUser_sn());
				mem = super.getFacade().getMemberInfoService().getMemberInfo(mem);
				if (null != mem) {
					temp.getMap().put("user_name", mem.getUser_name());
				}
			}

		}

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}
		JfScorts entity = new JfScorts();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getJfScortsService().getJfScorts(entity);

		if (null == entity) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		entity.setQueryString(super.serialize(request, "id", "method"));

		dynaBean.set("user_sn", entity.getUser_sn());
		dynaBean.set("total_scorts", entity.getTotal_scorts());

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		JfScortsDetails entity = new JfScortsDetails();
		entity.setJf_cate(2);
		super.copyProperties(entity, form);
		entity.setAdd_user_id(user.getId());
		entity.setStatus(1);

		super.getFacade().getJfScortsDetailsService().createJfScortsDetailsAndTotalScores(entity);

		super.saveMessage(request, "entity.updated");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward exchange(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}
		JfScorts entity = new JfScorts();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getJfScortsService().getJfScorts(entity);

		if (null == entity) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		// 取礼品
		JfGiftInfo gift = new JfGiftInfo();
		gift.setDept_id(user.getDept_id());
		gift.getMap().put("num_gt_0", true);

		List<JfGiftInfo> giftList = super.getFacade().getJfGiftInfoService().getJfGiftInfoList(gift);
		request.setAttribute("giftList", giftList);

		entity.setQueryString(super.serialize(request, "id", "method"));
		dynaBean.set("user_sn", entity.getUser_sn());
		dynaBean.set("total_scorts", entity.getTotal_scorts());
		return mapping.findForward("view");

	}

	public ActionForward saveEx(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		JfScortsExchange entity = new JfScortsExchange();
		super.copyProperties(entity, form);
		entity.setAdd_user(entity.getId());

		// 验证礼品是否有库存
		JfGiftInfo gift = new JfGiftInfo();
		gift.setId(entity.getGift_id());
		gift.getMap().put("num_gt_0", true);
		gift = super.getFacade().getJfGiftInfoService().getJfGiftInfo(gift);
		if (null == gift) {
			String msg = super.getMessage(request, "jf.gift.num.zero");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		super.getFacade().getJfScortsExchangeService().createJfScortsExchange(entity);

		super.saveMessage(request, "entity.updated");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward ajaxSetScortsByGift(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String gift_id = (String) dynaBean.get("gift_id");

		StringBuffer sb = new StringBuffer("{");
		if (StringUtils.isNotBlank(gift_id)) {
			JfGiftInfo gift = new JfGiftInfo();
			gift.setId(Long.valueOf(gift_id));
			gift.getMap().put("num_gt_0", true);
			gift = super.getFacade().getJfGiftInfoService().getJfGiftInfo(gift);
			if (null != gift) {
				sb = sb.append("\"scores\":\"").append(gift.getScorts()).append("\"");
			}
		}

		sb = sb.append("}");
		super.renderJson(response, sb.toString());
		// //System.out.println("sb="+sb.toString());//{"scores":"2"}
		return null;
	}
}
