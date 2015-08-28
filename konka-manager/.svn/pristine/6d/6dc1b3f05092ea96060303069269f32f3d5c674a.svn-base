package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaPersonPwd;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.org.apache.commons.lang3.StringUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wang,XuLi
 */
public class PersonPwdAction extends BaseAction {

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
		String real_name_like = (String) dynaBean.get("real_name_like");
		String id_card_no_like = (String) dynaBean.get("id_card_no_like");

		KonkaPersonPwd entity = new KonkaPersonPwd();
		if (StringUtils.isNotBlank(real_name_like)) {
			entity.getMap().put("real_name_like", real_name_like);
		}
		if (StringUtils.isNotBlank(id_card_no_like)) {
			entity.getMap().put("id_card_no_like", id_card_no_like);
		}

		Long recordCount = super.getFacade().getKonkaPersonPwdService().getKonkaPersonPwdCount(entity);
		pager.init(recordCount, 10, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaPersonPwd> entityList = super.getFacade().getKonkaPersonPwdService().getKonkaPersonPwdPaginatedList(
		        entity);

		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward initPassword(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

		KonkaPersonPwd entity = new KonkaPersonPwd();
		entity.setId(Long.valueOf(id));

		DESPlus des = new DESPlus();
		entity.setPwd(des.encrypt("888888")); // 默认密码

		super.getFacade().getKonkaPersonPwdService().modifyKonkaPersonPwd(entity);

		saveMessage(request, "password.init.success");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

}
