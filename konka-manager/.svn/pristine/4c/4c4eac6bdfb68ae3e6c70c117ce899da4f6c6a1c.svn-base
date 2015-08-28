package com.ebiz.mmt.web.struts.manager.oa;

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
import com.ebiz.mmt.domain.KonkaoaSsuedDocument;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

public class SsuedDocumentAction extends BaseMmtoaAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	@SuppressWarnings("unchecked")
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
		String pass_man_name = (String) dynaBean.get("pass_man_name");
		String add_starttime = (String) dynaBean.get("add_starttime");
		String add_endtime = (String) dynaBean.get("add_endtime");
		String fgs_dept_id = (String) dynaBean.get("fgs_dept_id");
		String jyb_dept_id = (String) dynaBean.get("jyb_dept_id");
		String bsc_dept_id = (String) dynaBean.get("bsc_dept_id");
		String ywy_user_id = (String) dynaBean.get("ywy_user_id");

		KonkaoaSsuedDocument entity = new KonkaoaSsuedDocument();
		super.copyProperties(entity, form);

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		entity.getMap().put("uid", ui.getId());
		entity.getMap().put("receive_org_id", ui.getDept_id());

		// 部门列表
		List<PeRoleUser> peRoleUserList = (List<PeRoleUser>) request.getSession()
				.getAttribute(Constants.ROLE_INFO_LIST);
		boolean role_id_lt_30 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() < 30) {
				role_id_lt_30 = true;
			}
		}

		if (role_id_lt_30) {// 管理员或事业部
			entity.getMap().put("konka_dept_id", fgs_dept_id);
		} else {
			KonkaDept _dept = this.getSuperiorForDeptType(ui.getDept_id(), 3);
			if (null != _dept && null != _dept.getDept_id()) {
				entity.getMap().put("konka_dept_id", _dept.getDept_id());
			}
		}

		if (GenericValidator.isLong(jyb_dept_id)) {
			entity.getMap().put("konka_dept_id", jyb_dept_id);
		}
		if (GenericValidator.isLong(bsc_dept_id)) {
			entity.getMap().put("konka_dept_id", bsc_dept_id);
		}
		if (GenericValidator.isLong(ywy_user_id)) {
			entity.getMap().put("ywy_user_id", ywy_user_id);
		}

		if (StringUtils.isNotBlank(pass_man_name)) {
			entity.setPass_man_name(pass_man_name);
		}
		if (StringUtils.isNotBlank(add_starttime)) {
			entity.getMap().put("add_starttime", add_starttime);
		}
		if (StringUtils.isNotBlank(add_endtime)) {
			entity.getMap().put("add_endtime", add_endtime);
		}

		Long recordCount = super.getFacade().getKonkaoaFilesService().getKonkaoaSsuedDocumentCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaoaSsuedDocument> entityList = super.getFacade().getKonkaoaFilesService()
				.getKonkaoaSsuedDocumentPaginatedList(entity);

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		if (StringUtils.isNotBlank(fgs_dept_id)) {
			dynaBean.set("fgs_dept_id", fgs_dept_id);
		}
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

}