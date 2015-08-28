package com.ebiz.mmt.web.struts.shop;

import java.util.Date;
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

import com.ebiz.mmt.domain.EcPdEavl;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author tudp
 * @version 2013-09-17
 */
public class EcPdEavlAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String eavl = (String) dynaBean.get("eavl");
		String goods_id = (String) dynaBean.get("goods_id");
		if (!StringUtils.isBlank(goods_id) && GenericValidator.isLong(goods_id)) {
			HttpSession session = request.getSession();
			EcUser ecUser = (EcUser) session.getAttribute("ecUser");

			EcPdEavl entity = new EcPdEavl();
			entity.setOwn_sys(ecUser.getUser_type());
			entity.setIs_del(0);
			entity.setGoods_id(new Long(goods_id));
			if (ecUser != null) {
				entity.getMap().put("user_id", ecUser.getId());// 取已审核的和当前用户的评论
			} else {
				entity.setAudit_state(new Integer(1));// 取已审核的评论
			}
			if ("1".equals(eavl)) {// 好评
				entity.getMap().put("eval_score_a", "1");
			} else if ("2".equals(eavl)) {// 中评
				entity.getMap().put("eval_score_b", "1");
			} else if ("3".equals(eavl)) {// 差评
				entity.getMap().put("eval_score_c", "1");
			}

			Long recordCount = super.getFacade().getEcPdEavlService().getEcPdEavlCount(entity);

			pager.init(recordCount, new Integer(5), pager.getRequestPage());
			entity.getRow().setFirst(pager.getFirstRow());
			entity.getRow().setCount(pager.getRowCount());

			List<EcPdEavl> entityList = super.getFacade().getEcPdEavlService().getEcPdEavlPaginatedList(entity);
			request.setAttribute("entityList", entityList);

			// 评论数
			entity = new EcPdEavl();
			entity.setOwn_sys(ecUser.getUser_type());;
			entity.setIs_del(0);
			entity.setGoods_id(new Long(goods_id));
			if (ecUser != null) {
				entity.getMap().put("user_id", ecUser.getId());// 取已审核的和当前用户的评论
			}else{
				entity.setAudit_state(new Integer(1));//取已审核的评论
			}
			Long scoreSum = super.getFacade().getEcPdEavlService().getEcPdEavlSumEvalScore(entity);
			Long scoreCount = super.getFacade().getEcPdEavlService().getEcPdEavlCount(entity);
			entity.setEval_score(new Integer(1));
			Long scoreCount1 = super.getFacade().getEcPdEavlService().getEcPdEavlCount(entity);
			entity.setEval_score(new Integer(2));
			Long scoreCount2 = super.getFacade().getEcPdEavlService().getEcPdEavlCount(entity);
			entity.setEval_score(new Integer(3));
			Long scoreCount3 = super.getFacade().getEcPdEavlService().getEcPdEavlCount(entity);
			entity.setEval_score(new Integer(4));
			Long scoreCount4 = super.getFacade().getEcPdEavlService().getEcPdEavlCount(entity);
			entity.setEval_score(new Integer(5));
			Long scoreCount5 = super.getFacade().getEcPdEavlService().getEcPdEavlCount(entity);

			request.setAttribute("scoreCount", scoreCount);
			if (scoreCount.intValue() > 0) {
				request.setAttribute("score1", "" + (scoreCount1.intValue() * 100.0f / scoreCount.intValue()));
				request.setAttribute("score2", "" + (scoreCount2.intValue() * 100.0f / scoreCount.intValue()));
				request.setAttribute("score3", "" + (scoreCount3.intValue() * 100.0f / scoreCount.intValue()));
				request.setAttribute("score4", "" + (scoreCount4.intValue() * 100.0f / scoreCount.intValue()));
				request.setAttribute("score5", "" + (scoreCount5.intValue() * 100.0f / scoreCount.intValue()));
				request.setAttribute("score45", ""
						+ ((scoreCount5.intValue() + scoreCount4.intValue()) * 100.0f / scoreCount.intValue()));
				request.setAttribute("scorea", "" + (scoreSum.intValue() * 1.0f / scoreCount.intValue()));// 用户平均打分
			} else {
				request.setAttribute("score1", "0");
				request.setAttribute("score2", "0");
				request.setAttribute("score3", "0");
				request.setAttribute("score4", "0");
				request.setAttribute("score5", "0");
				request.setAttribute("score45", "0");
			}

			// 取好评 score(4,5) 中评 score(3) 差评 score(2,1)
			entity = new EcPdEavl();
			entity.setOwn_sys(ecUser.getUser_type());;
			entity.setIs_del(0);
			entity.setGoods_id(new Long(goods_id));
			if (ecUser != null) {
				entity.getMap().put("user_id", ecUser.getId());// 取已审核的和当前用户的评论
			}else{
				entity.setAudit_state(new Integer(1));//取已审核的评论
			}
			entity.getMap().put("eval_score_a", "1");
			request.setAttribute("eval_score_a", super.getFacade().getEcPdEavlService().getEcPdEavlCount(entity));

			entity = new EcPdEavl();
			entity.setOwn_sys(ecUser.getUser_type());;
			entity.setIs_del(0);
			entity.setGoods_id(new Long(goods_id));
			entity.getMap().put("eval_score_b", "1");
			if (ecUser != null) {
				entity.getMap().put("user_id", ecUser.getId());// 取已审核的和当前用户的评论
			}else{
				entity.setAudit_state(new Integer(1));//取已审核的评论
			}
			request.setAttribute("eval_score_b", super.getFacade().getEcPdEavlService().getEcPdEavlCount(entity));

			entity = new EcPdEavl();
			entity.setOwn_sys(ecUser.getUser_type());;
			entity.setIs_del(0);
			entity.setGoods_id(new Long(goods_id));
			entity.getMap().put("eval_score_c", "1");
			if (ecUser != null) {
				entity.getMap().put("user_id", ecUser.getId());// 取已审核的和当前用户的评论
			}else{
				entity.setAudit_state(new Integer(1));//取已审核的评论
			}
			request.setAttribute("eval_score_c", super.getFacade().getEcPdEavlService().getEcPdEavlCount(entity));

			if (null != ecUser) {
				// 取当前用户是否已评论
				entity = new EcPdEavl();
				entity.setOwn_sys(ecUser.getUser_type());;
				entity.setIs_del(0);// 取未删除的
				entity.setGoods_id(new Long(goods_id));
				entity.getMap().put("user_id", ecUser.getId());// 取已审核的和当前用户的评论
				entity.setEval_user_id(ecUser.getId());
				Long c = super.getFacade().getEcPdEavlService().getEcPdEavlCount(entity);
				request.setAttribute("c", c);
				PshowOrdeDetails  details = new PshowOrdeDetails();
				details.setPd_id(new Long(goods_id));
				details.getMap().put("state_in", new Integer[]{60});
				Long d = super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetailsForFgsCount(details);
			    request.setAttribute("d",d); 
			}

			return mapping.findForward("list");
		} else {
			return null;
		}
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		String goods_id = (String) dynaBean.get("goods_id");
		String msg = "恭喜，发表评论成功！";
		if (ecUser != null && !StringUtils.isBlank(goods_id) && GenericValidator.isLong(goods_id)) {
			EcPdEavl entity = new EcPdEavl();
			// 取当前用户是否已评论
			entity = new EcPdEavl();
			entity.setGoods_id(new Long(goods_id));
			if (ecUser != null) {
				entity.getMap().put("user_id", ecUser.getId());// 取已审核的和当前用户的评论
			}
			// entity.setAudit_state(new Integer(1));//取已审核的评论
			entity.setEval_user_id(ecUser.getId());
			Long c = super.getFacade().getEcPdEavlService().getEcPdEavlCount(entity);

			if (c.intValue() == 0) {
				entity = new EcPdEavl();
				super.copyProperties(entity, form);
				entity.setEval_user_id(ecUser.getId());
				entity.setId(null);
				// entity.setAudit_state(new Integer(0));
				// entity.setIs_del(new Integer(0));
				if (entity.getIs_anon() == null) {
					entity.setIs_anon(new Integer(0));
				}
				entity.setEval_ip(super.getIpAddr(request));
				entity.setEval_useful(new Long(0));
				entity.setEval_useless(new Long(0));
				entity.setEval_date(new Date());
				entity.setOwn_sys(ecUser.getUser_type());;
				entity.setIs_del(0);
				super.getFacade().getEcPdEavlService().createEcPdEavl(entity);
			} else {
				msg = "对不起,您已经评论过了！";
			}
		} else {
			msg = "您还未登录，不能评论！";
		}
		if (!StringUtils.isBlank(goods_id) && GenericValidator.isLong(goods_id)) {
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='"
					+ super.getCtxPath(request) + "/shop/EcPdEavl.do?method=list&goods_id=" + goods_id + "';}");
		}
		return null;
	}
}
