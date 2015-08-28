package com.ebiz.mmt.web.struts.manager.chengduoa;

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

import com.ebiz.mmt.domain.KonkaItem;
import com.ebiz.mmt.domain.KonkaItemProperty;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Jin, QingHua
 */
public class KonkaItemFinishAction extends BaseMmtoaAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String p_type_1 = (String) dynaBean.get("p_type_1");
		String p_type_2 = (String) dynaBean.get("p_type_2");
		String item_content = (String) dynaBean.get("item_content");
		String receive_user_name = (String) dynaBean.get("receive_user_name");
		String is_finished = (String) dynaBean.get("is_finished");
		Pager pager = (Pager) dynaBean.get("pager");

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		
		KonkaItem entity = new KonkaItem();
		entity.setIs_del(0);
		if (StringUtils.isNotBlank(item_content)) {
			//entity.setItem_content(item_content);
			entity.getMap().put("item_content_like", item_content);
		}
		if (StringUtils.isNotBlank(receive_user_name)) {
			//entity.setReceive_user_name(receive_user_name);
			entity.getMap().put("receive_user_name_like", receive_user_name);
		}
		if (StringUtils.isNotBlank(is_finished)) {
			entity.setIs_finished(Integer.valueOf(is_finished));
		}
		if (StringUtils.isNotBlank(p_type_1)) {
			entity.setP_type_1(Long.valueOf(p_type_1));
		}
		if (StringUtils.isNotBlank(p_type_2)) {
			entity.setP_type_2(Long.valueOf(p_type_2));
		}

		Long recordCount = super.getFacade().getKonkaItemService().getKonkaItemCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaItem> entityList = super.getFacade().getKonkaItemService().getKonkaItemPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		KonkaItemProperty property = new KonkaItemProperty();
		property.setIs_del(0);
		property.setP_type(0);

		List<KonkaItemProperty> propertyList1 = super.getFacade().getKonkaItemPropertyService()
				.getKonkaItemPropertyList(property);
		request.setAttribute("propertyList1", propertyList1);
		property.setP_type(1);
		List<KonkaItemProperty> propertyList2 = super.getFacade().getKonkaItemPropertyService()
				.getKonkaItemPropertyList(property);
		request.setAttribute("propertyList2", propertyList2);

		request.setAttribute("loginId", ui.getId());
//		if (isKonkaBaseManager(ui)) {
//			request.setAttribute("canModify", 1);
//		}

		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (GenericValidator.isLong(id)) {
			KonkaItem entity = new KonkaItem();
			entity.setIs_del(0);
			entity.setId(Long.valueOf(id));
			entity = super.getFacade().getKonkaItemService().getKonkaItem(entity);
			entity.setQueryString(super.serialize(request, "id", "method"));
			if (null != entity) {
				super.copyProperties(form, entity);
				return mapping.findForward("input");
			}
		}
		return this.list(mapping, form, request, response);
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (GenericValidator.isLong(id)) {
			KonkaItem entity = new KonkaItem();
			entity.setId(Long.valueOf(id));
			entity = super.getFacade().getKonkaItemService().getKonkaItem(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			super.copyProperties(form, entity);
			return mapping.findForward("view");
		} else {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");

		KonkaItem entity = new KonkaItem();
		super.copyProperties(entity, form);
		if (GenericValidator.isLong(id)) {
			entity.setLast_update_time(new Date());
			super.getFacade().getKonkaItemService().modifyKonkaItem(entity);
			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

}