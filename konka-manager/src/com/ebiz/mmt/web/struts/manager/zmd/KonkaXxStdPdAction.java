package com.ebiz.mmt.web.struts.manager.zmd;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.KonkaXxModelPropsVal;
import com.ebiz.mmt.domain.KonkaXxPdProp;
import com.ebiz.mmt.domain.KonkaXxPropValItem;
import com.ebiz.mmt.domain.KonkaXxStdPd;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Xing,Xiudong
 * @version 2012-04-01
 */
public class KonkaXxStdPdAction extends BaseZmdAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String md_name_like = (String) dynaBean.get("md_name_like");

		KonkaXxStdPd entity = new KonkaXxStdPd();
		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(md_name_like)) {
			entity.getMap().put("md_name_like", md_name_like.trim());
		}

		Pager pager = (Pager) dynaBean.get("pager");
		Long recordCount = getFacade().getKonkaXxStdPdService().getKonkaXxStdPdCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		// request.setAttribute("basePdClazzList", getBasePdClazzList());

		List<KonkaXxStdPd> entityList = getFacade().getKonkaXxStdPdService().getKonkaXxStdPdForPdNamePaginatedList(
				entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		dynaBean.set("queryString", super.serialize(request, "id", "method"));

		request.setAttribute("basePdClazzList", getBasePdClazzList());

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String md_name = (String) dynaBean.get("md_name");
		KonkaXxStdPd entity = new KonkaXxStdPd();
		entity.setMd_name(md_name.trim());
		Long pdCount = super.getFacade().getKonkaXxStdPdService().getKonkaXxStdPdCount(entity);
		if (pdCount != 1) {
			String msg = getMessage(request, "errors.param");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		entity = super.getFacade().getKonkaXxStdPdService().getKonkaXxStdPd(entity);
		super.copyProperties(form, entity);
		request.setAttribute("basePdClazzList", getBasePdClazzList());

		request.setAttribute("is_edit", true);
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String md_name = (String) dynaBean.get("md_name");
		String pd_id = (String) dynaBean.get("pd_id");

		if (StringUtils.isBlank(md_name)) {
			String msg = getMessage(request, "errors.param");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		md_name = md_name.trim();
		KonkaXxStdPd pd = new KonkaXxStdPd();
		pd.setMd_name(md_name);
		Long c = super.getFacade().getKonkaXxStdPdService().getKonkaXxStdPdCount(pd);

		KonkaXxStdPd entity = new KonkaXxStdPd();
		super.copyProperties(entity, form);
		entity.setPd_id(Long.valueOf(pd_id));

		if (c > 1L) {
			String msg = getMessage(request, "entity.exists", new String[] { md_name });
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		} else if (c == 1L) {
			getFacade().getKonkaXxStdPdService().modifyKonkaXxStdPd(entity);
			saveMessage(request, "entity.updated");
		} else {
			getFacade().getKonkaXxStdPdService().createKonkaXxStdPd(entity);
			saveMessage(request, "entity.inserted");
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

	public ActionForward editProperty(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String md_name = (String) dynaBean.get("md_name");

		if (StringUtils.isNotBlank(md_name)) {
			KonkaXxStdPd entity = new KonkaXxStdPd();
			entity.setMd_name(md_name);
			entity = super.getFacade().getKonkaXxStdPdService().getKonkaXxStdPd(entity);
			if (null != entity && null != entity.getPd_id()) {
				BasePdClazz bpc = new BasePdClazz();
				bpc.setCls_id(entity.getPd_id());
				bpc.setIs_del(0);
				bpc = super.getFacade().getBasePdClazzService().getBasePdClazz(bpc);
				if (null != bpc) {
					request.setAttribute("full_cls_name", bpc.getFull_name().replace(",", ">>"));
				}

				// property
				KonkaXxPdProp bp = new KonkaXxPdProp();
				bp.setCls_id(entity.getPd_id());
				bp.setIs_del(0);
				List<KonkaXxPdProp> bpList = super.getFacade().getKonkaXxPdPropService().getKonkaXxPdPropList(bp);
				if (null == bpList || bpList.size() < 1) {
					String error = super.getMessage(request, "pdProperty.no.data");
					super.renderJavaScript(response, "alert('" + error + "');history.back();");
					return null;
				}

				for (KonkaXxPdProp temp : bpList) {
					KonkaXxPropValItem bpvi = new KonkaXxPropValItem();
					bpvi.setProp_id(temp.getProp_id());
					bpvi.setIs_del(0);

					List<KonkaXxPropValItem> konkaXxPropValItemList = super.getFacade().getKonkaXxPropValItemService()
							.getKonkaXxPropValItemList(bpvi);
					temp.setKonkaXxPropValItemList(konkaXxPropValItemList);

					KonkaXxModelPropsVal propVal = new KonkaXxModelPropsVal();
					propVal.setProp_id(temp.getProp_id());

					List<KonkaXxModelPropsVal> propValList = super.getFacade().getKonkaXxModelPropsValService()
							.getKonkaXxModelPropsValList(propVal);

					if (null != propValList && propValList.size() > 0) {
						String prop_value = propValList.get(0).getProp_value();
						temp.getMap().put("prop_value", prop_value);
					}
				}
				request.setAttribute("bpList", bpList);
				dynaBean.set("md_name", md_name);
				entity.setQueryString(super.serialize(request, "pd_id", "method"));
				super.copyProperties(form, entity);
				return new ActionForward("/../manager/zmd/KonkaXxStdPd/property.jsp");
			} else {
				String msg = getMessage(request, "errors.param");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		} else {
			String msg = getMessage(request, "errors.param");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
	}

	public ActionForward saveProperty(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String md_name = (String) dynaBean.get("md_name");
		String[] prop_ids = request.getParameterValues("prop_ids");
		String[] prop_values = request.getParameterValues("prop_values");

		if (StringUtils.isBlank(md_name)) {
			saveError(request, "param.isEmpty", new String[] { md_name });
			return mapping.findForward("list");
		}

		KonkaXxModelPropsVal entity = new KonkaXxModelPropsVal();
		if (null != prop_ids && prop_ids.length > 0) {
			int max_length = prop_ids.length;
			for (int i = 0; i < max_length; i++) {
				String[] selectedPdMoreProperties = request.getParameterValues("check_box_" + prop_ids[i]);
				if (null != selectedPdMoreProperties && selectedPdMoreProperties.length > 0) {
					StringBuffer temp_valuesBuffer = new StringBuffer();
					StringBuffer temp_idsBuffer = new StringBuffer();
					for (String temp : selectedPdMoreProperties) {
						temp_valuesBuffer.append(temp.split("-")[1] + ",");
						temp_idsBuffer.append(temp.split("-")[0] + ",");
					}
					String temp_valuesString = String.valueOf(temp_valuesBuffer);
					String temp_idsString = String.valueOf(temp_idsBuffer);
					if (StringUtils.isNotBlank(temp_idsString) && temp_idsString.length() > 1) {
						temp_idsString = StringUtils.substring(temp_idsString, 0, temp_idsString.length() - 1);
						temp_valuesString = StringUtils.substring(temp_valuesString, 0, temp_valuesString.length() - 1);
						prop_values[i] = temp_idsString + "|||" + temp_valuesString;
					}
				}
			}
			entity.setMd_name(md_name);
			entity.getMap().put("prop_ids", prop_ids);
			entity.getMap().put("prop_values", prop_values);
			super.getFacade().getKonkaXxModelPropsValService().createKonkaXxModelPropsValList(entity);
			super.saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "pd_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
}
