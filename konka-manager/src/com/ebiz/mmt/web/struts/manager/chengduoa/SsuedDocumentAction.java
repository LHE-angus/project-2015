package com.ebiz.mmt.web.struts.manager.chengduoa;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaoaSsuedDocument;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

public class SsuedDocumentAction extends BaseMmtoaAction {

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
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");

		KonkaoaSsuedDocument entity = new KonkaoaSsuedDocument();
		super.copyProperties(entity, form);

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		entity.getMap().put("uid", ui.getId());
		entity.getMap().put("receive_org_id", ui.getDept_id());
		
		Long recordCount = super.getFacade().getKonkaoaFilesService().getKonkaoaSsuedDocumentCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
		List<KonkaoaSsuedDocument> entityList = super.getFacade().getKonkaoaFilesService()
				.getKonkaoaSsuedDocumentPaginatedList(entity);

		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

}