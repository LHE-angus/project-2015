package com.ebiz.mmt.web.struts.member.center;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcGroupBuyMain;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcVouchCode;
import com.ebiz.mmt.web.struts.member.BaseMemberAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author tudp
 * @version 2013-09-14
 */
public class EcGroupBuyAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");

		Pager pager = (Pager) dynaBean.get("pager");
		String start_date = (String) dynaBean.get("start_date");
		String end_date = (String) dynaBean.get("end_date");

		EcVouchCode entity = new EcVouchCode();
		entity.setAdd_user_id(ecUser.getId());
		entity.getMap().put("add_date_start", start_date);
		entity.getMap().put("add_date_end", end_date);

		Long recordCount = super.getFacade().getEcVouchCodeService().getEcVouchCodeCount(entity);

		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcVouchCode> entityList = super.getFacade().getEcVouchCodeService().getEcVouchCodePaginatedList(entity);
		for (EcVouchCode ecVouchCode : entityList) {
			if (ecVouchCode.getVouch_id() != null) {
				EcGroupBuyMain em = new EcGroupBuyMain();
				em.setId(ecVouchCode.getVouch_id());
				em = super.getFacade().getEcGroupBuyMainService().getEcGroupBuyMain(em);
				if (em != null && em.getShop_id() != null) {
					ecVouchCode.getMap().put("shop_name", em.getShop_name());
					ecVouchCode.getMap().put("shop_addr", em.getAddr());
					ecVouchCode.getMap().put("shop_tel", em.getTel());
					ecVouchCode.getMap().put("title", em.getTitle());
					ecVouchCode.getMap().put("is_djq", 1);
				} else {
					ecVouchCode.getMap().put("title", "橙子");
					ecVouchCode.getMap().put("shop_name", "康佳集团总部");
					ecVouchCode.getMap().put("shop_addr", "深圳南山区华侨城");
					ecVouchCode.getMap().put("shop_tel", "0775-26608866转6346");
					ecVouchCode.getMap().put("is_djq", 0);
				}
			}
		}

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");

		EcVouchCode entity = new EcVouchCode();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}
		entity.setAdd_user_id(ecUser.getId());
		entity = super.getFacade().getEcVouchCodeService().getEcVouchCode(entity);
		if (entity == null) {
			String msg = "对不起，未找到该订单信息！";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg
			        + "');location.href='EcGroupBuy.do';}");
			return null;
		}

		if (entity.getVouch_id() != null) {
			EcGroupBuyMain em = new EcGroupBuyMain();
			em.setId(entity.getVouch_id());
			em = super.getFacade().getEcGroupBuyMainService().getEcGroupBuyMain(em);
			if (em != null) {
				entity.getMap().put("title", em.getTitle());
			} else {
				entity.getMap().put("title", "橙子");
			}
		}

		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		return mapping.findForward("view");
	}
}
