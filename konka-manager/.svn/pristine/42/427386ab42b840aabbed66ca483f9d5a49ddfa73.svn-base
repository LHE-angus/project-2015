package com.ebiz.mmt.web.struts.mobile.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileTerminalFbBack;
import com.ebiz.mmt.domain.KonkaMobileTerminalFeedback;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;

/**
 * @author Wang Hao
 */
public class TerminalFeedbackReportAction extends MobileBaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("mobileCategoryList", getMobileCategories(7));
		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String page = (String) dynaBean.get("page");
		String forward = (String) dynaBean.get("forward");
		int currentPage = 1;
		int pageSize = 1;
		if (StringUtils.isEmpty(page))
			request.setAttribute("page", currentPage);
		else {
			currentPage = (Integer.parseInt(page))
					+ (Integer.parseInt(forward));
			request.setAttribute("page", currentPage);
		}
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(
				Constants.USER_MOBILE);
		KonkaMobileTerminalFeedback _t = new KonkaMobileTerminalFeedback();
		_t.setQuestion_id(ui.getId());
		_t.setIs_del(0);
		_t.getMap().put("is_history_null", "true");
		_t.getRow().setFirst((currentPage - 1) * pageSize);
		_t.getRow().setCount(pageSize);
		Long count = super.getFacade().getKonkaMobileTerminalFeedbackService()
				.getKonkaMobileTerminalFeedbackWithFbBackCount(_t);
		if (count % pageSize > 0)
			request.setAttribute("pagelimit", (count / pageSize) + 1);
		else
			request.setAttribute("pagelimit", (count / pageSize));
		if (count > 0) {
			List<KonkaMobileTerminalFeedback> feedBackList = getFacade()
					.getKonkaMobileTerminalFeedbackService()
					.getKonkaMobileTerminalFeedbackListWithFbBack(_t);

			if (feedBackList.get(0).getMap().get("fb_id") != null) {
				KonkaMobileTerminalFbBack fbBack = new KonkaMobileTerminalFbBack();
				fbBack.setId(Long.valueOf(feedBackList.get(0).getMap().get(
						"fb_id").toString()));
				fbBack.setIs_history(1);
				getFacade().getKonkaMobileTerminalFbBackService()
						.modifyKonkaMobileTerminalFbBack(fbBack);
			}

			request.setAttribute("feedBackList", feedBackList);
			request.setAttribute("id", feedBackList.get(0).getId());
		}

		request.setAttribute("pagelimit", count);

		return mapping.findForward("list");
	}

	public ActionForward old(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String page = (String) dynaBean.get("page");
		String forward = (String) dynaBean.get("forward");
		int currentPage = 1;
		int pageSize = 1;
		if (StringUtils.isEmpty(page))
			request.setAttribute("page", currentPage);
		else {
			currentPage = (Integer.parseInt(page))
					+ (Integer.parseInt(forward));
			request.setAttribute("page", currentPage);
		}
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(
				Constants.USER_MOBILE);
		KonkaMobileTerminalFeedback _t = new KonkaMobileTerminalFeedback();
		_t.setQuestion_id(ui.getId());
		_t.setIs_del(0);
		_t.getMap().put("is_history", 1);
		_t.getRow().setFirst((currentPage - 1) * pageSize);
		_t.getRow().setCount(pageSize);
		Long count = super.getFacade().getKonkaMobileTerminalFeedbackService()
				.getKonkaMobileTerminalFeedbackWithFbBackCount(_t);
		if (count % pageSize > 0)
			request.setAttribute("pagelimit", (count / pageSize) + 1);
		else
			request.setAttribute("pagelimit", (count / pageSize));
		if (count > 0) {
			List<KonkaMobileTerminalFeedback> feedBackList = getFacade()
					.getKonkaMobileTerminalFeedbackService()
					.getKonkaMobileTerminalFeedbackListWithFbBack(_t);
			request.setAttribute("feedBackList", feedBackList);
			request.setAttribute("id", feedBackList.get(0).getId());
		}
		request.setAttribute("pagelimit", count);

		return mapping.findForward("view");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaBean lazyForm = (DynaBean) form;

		String message_type = (String) lazyForm.get("message_type");
		String content = (String) lazyForm.get("content");

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(
				Constants.USER_MOBILE);

		KonkaMobileTerminalFeedback feedBack = new KonkaMobileTerminalFeedback();

		if (StringUtils.isNotBlank(message_type)) {
			feedBack.setMessage_type(Integer.valueOf(message_type));
		} else {
			super.renderText(response, "请选择反馈类别！");
			return null;
		}

		if (StringUtils.isNotBlank(content)) {
			feedBack.setContent(content);
		} else {
			super.renderText(response, "内容不可为空！");
			return null;
		}

		feedBack.setQuestion_person(ui.getReal_name());
		feedBack.setAdd_date(new Date());
		feedBack.setIs_del(0);
		feedBack.setQuestion_id(ui.getId());
		feedBack.setOffice_id(ui.getDept_id());
		KonkaDept kd = getSuperiorForDeptType(ui.getDept_id(), 3);
		if (null != kd)
			feedBack.setSubcomp_id(getSuperiorForDeptType(ui.getDept_id(), 3)
					.getDept_id());

		super.getFacade().getKonkaMobileTerminalFeedbackService()
				.createKonkaMobileTerminalFeedback(feedBack);

		super.renderText(response, "success");
		return null;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		KonkaMobileTerminalFeedback feedBack = new KonkaMobileTerminalFeedback();
		if (StringUtils.isNotBlank(id)) {
			feedBack.setId(Long.valueOf(id));
		}
		feedBack.setIs_del(1);
		getFacade().getKonkaMobileTerminalFeedbackService()
				.modifyKonkaMobileTerminalFeedback(feedBack);

		return this.list(mapping, form, request, response);
	}

	public ActionForward read(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		KonkaMobileTerminalFbBack feedBack = new KonkaMobileTerminalFbBack();
		if (StringUtils.isNotBlank(id)) {
			feedBack.setFeed_id(Long.valueOf(id));
		}
		feedBack.setIs_history(1);
		getFacade().getKonkaMobileTerminalFbBackService()
				.modifyKonkaMobileTerminalFbBack(feedBack);
		return null;
	}
}
