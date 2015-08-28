package com.ebiz.mmt.web.struts.manager.zmd;

import java.util.ArrayList;
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

import com.ebiz.mmt.domain.BasePdClass;
import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.BasePropValItem;
import com.ebiz.mmt.domain.KonkaXxPdProp;
import com.ebiz.mmt.domain.KonkaXxPropCategory;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu,Hao
 * @version 2013-04-05
 */
public class KonkaXxPdPropAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String prop_name_like = (String) dynaBean.get("prop_name_like");
		String cls_id = (String) dynaBean.get("cls_id");
		String category_name_like = (String) dynaBean.get("category_name_like");

		KonkaXxPdProp entity = new KonkaXxPdProp();
		entity.setIs_del(0);
		if (StringUtils.isNotBlank(prop_name_like)) {
			entity.getMap().put("prop_name_like", prop_name_like);
		}
		if (StringUtils.isNotBlank(cls_id)) {
			entity.getMap().put("cls_id", cls_id);
		}
		if (StringUtils.isNotBlank(category_name_like)) {
			entity.getMap().put("category_name_like", category_name_like);
		}

		Long recordCount = super.getFacade().getKonkaXxPdPropService().getKonkaXxPdPropCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaXxPdProp> entityList = super.getFacade().getKonkaXxPdPropService().getKonkaXxPdPropPaginatedList(
				entity);
		request.setAttribute("entityList", entityList);

		if (null != entityList && entityList.size() > 0) {
			List<String> strList = new ArrayList<String>();
			for (KonkaXxPdProp bp : entityList) {
				BasePdClazz bpc = new BasePdClazz();
				bpc.setIs_del(0);
				bpc.getMap().put("cls_id_like", bp.getCls_id());
				List<BasePdClazz> bpcList = super.getFacade().getBasePdClazzService().getBasePdClazzList(bpc);
				if (null != bpcList && bpcList.size() > 0) {
					StringBuffer sb = new StringBuffer();
					if (bpcList.size() == 1) {
						sb.append(bpcList.get(0).getCls_name());
					} else {
						sb.append(bpcList.get(0).getCls_name());
						for (int i = 1; i < bpcList.size(); i++) {
							sb.append(">>").append(bpcList.get(i).getCls_name());
						}
					}
					strList.add(sb.toString());
				} else {
					strList.add("");
				}

				KonkaXxPropCategory kpc = new KonkaXxPropCategory();
				kpc.setCategory_id(entity.getCategory_id());
				List<KonkaXxPropCategory> kpcList = super.getFacade().getKonkaXxPropCategoryService()
						.getKonkaXxPropCategoryList(kpc);
				bp.getMap().put("category_name", kpcList.get(0).getCategory_name());
			}
			request.setAttribute("strList", strList);

		}

		BasePdClazz basePdClass = new BasePdClazz();
		basePdClass.setIs_del(0);
		List<BasePdClazz> basePdClassList = super.getFacade().getBasePdClazzService().getBasePdClazzList(basePdClass);
		request.setAttribute("basePdClassList", basePdClassList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		request.setAttribute("basePdClazzList", getBasePdClazzList());
		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String prop_id = (String) dynaBean.get("prop_id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(prop_id)) {
			saveError(request, "errors.long", new String[] { prop_id });
			return mapping.findForward("list");
		}

		KonkaXxPdProp entity = new KonkaXxPdProp();
		entity.setProp_id(Long.valueOf(prop_id));
		entity = super.getFacade().getKonkaXxPdPropService().getKonkaXxPdProp(entity);
		if (null == entity) {
			saveError(request, "errors.long", new String[] { prop_id });
			return mapping.findForward("list");
		} else {
			Long cls_id = entity.getCls_id();
			Long category_id = entity.getCategory_id();
			if (null != category_id) {
				KonkaXxPropCategory bpcg = new KonkaXxPropCategory();
				bpcg.setCategory_id(category_id);
				bpcg = super.getFacade().getKonkaXxPropCategoryService().getKonkaXxPropCategory(bpcg);
				dynaBean.set("category_name", bpcg.getCategory_name());
			}
			BasePdClazz bpp = new BasePdClazz();
			bpp.setIs_del(0);
			bpp.getMap().put("cls_id_like", cls_id);
			List<BasePdClazz> bppList = super.getFacade().getBasePdClazzService().getBasePdClazzList(bpp);
			StringBuffer sb = new StringBuffer();
			if (null != bppList && bppList.size() > 0) {
				for (BasePdClazz bpc : bppList) {
					KonkaXxPdProp bpp1 = new KonkaXxPdProp();
					bpp1.setIs_del(0);
					bpp1.setCls_id(bpc.getCls_id());
					List<KonkaXxPdProp> bpp1List = super.getFacade().getKonkaXxPdPropService().getKonkaXxPdPropList(
							bpp1);
					if (null != bpp1List && bpp1List.size() > 0) {
						sb = sb.append(bpc.getTree_name()).append(":");
						for (int i = 0; i < bpp1List.size(); i++) {
							if (StringUtils.isNotBlank(bpp1List.get(i).getProp_name())) {
								sb = sb.append("'").append(bpp1List.get(i).getProp_name()).append("'").append(";");
							}
						}
						sb.append("<br/>");
					}
				}
			} else {
				sb.append("");
			}
			dynaBean.set("add_property", sb.toString());

		}
		BasePropValItem bpvi = new BasePropValItem();
		bpvi.setIs_del(0);
		bpvi.setProp_id(Long.valueOf(prop_id));
		List<BasePropValItem> basePropValItemList = super.getFacade().getBasePropValItemService()
				.getBasePropValItemList(bpvi);
		request.setAttribute("basePropValItemList", basePropValItemList);

		entity.setQueryString(super.serialize(request, "id", "mod_id"));
		dynaBean.set("mod_id", mod_id);

		request.setAttribute("basePdClazzList", getBasePdClazzList());
		super.copyProperties(form, entity);
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String prop_id = (String) dynaBean.get("prop_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String[] more_values = request.getParameterValues("more_values");
		String category_id_set_null = (String) dynaBean.get("category_id_set_null");

		PeProdUser user_id = super.getSessionUserInfo(request);

		KonkaXxPdProp entity = new KonkaXxPdProp();
		super.copyProperties(entity, form);
		entity.setProp_name(entity.getProp_name().trim());
		if (StringUtils.isNotBlank(category_id_set_null)) {
			entity.getMap().put("category_id_set_null", "true");
		}
		if (null != more_values && more_values.length > 0) {
			entity.getMap().put("more_values", more_values);
		}
		if (StringUtils.isBlank(prop_id)) {
			KonkaXxPdProp bp = new KonkaXxPdProp();
			bp.setProp_name(entity.getProp_name().trim());
			// bp.setCategory_id(entity.getCategory_id());
			bp.setCls_id(entity.getCls_id());
			// bp.getMap().put("par_cls_id", entity.getCls_id());
			List<KonkaXxPdProp> bpList = super.getFacade().getKonkaXxPdPropService().getKonkaXxPdPropList(bp);
			if (null != bpList && bpList.size() > 0) {
				BasePdClazz bpp = new BasePdClazz();
				bpp.setIs_del(0);
				bpp.getMap().put("cls_id_like", entity.getCls_id());
				List<BasePdClazz> bppList = super.getFacade().getBasePdClazzService().getBasePdClazzList(bpp);
				StringBuffer sb = new StringBuffer();
				if (null != bppList && bppList.size() > 0) {
					for (BasePdClazz bpc : bppList) {
						KonkaXxPdProp bpp1 = new KonkaXxPdProp();
						bpp1.setIs_del(0);
						bpp1.setCls_id(bpc.getCls_id());
						List<KonkaXxPdProp> bpp1List = super.getFacade().getKonkaXxPdPropService()
								.getKonkaXxPdPropList(bpp1);
						if (null != bpp1List && bpp1List.size() > 0) {
							sb = sb.append(bpc.getTree_name()).append(":");
							for (int i = 0; i < bpp1List.size(); i++) {
								if (StringUtils.isNotBlank(bpp1List.get(i).getProp_name())) {
									sb = sb.append("'").append(bpp1List.get(i).getProp_name()).append("'").append(";");
								}
							}
							sb.append("<br/>");
						}
					}
				} else {
					sb.append("");
				}
				dynaBean.set("add_property", sb.toString());
				String msg = super.getMessage(request, "konka.xx.property.exist");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else {
				entity.setAdd_date(new Date());
				entity.setIs_del(0);
				entity.setAdd_user_id(user_id.getId());
				// 添加人id
				// entity.setAdd_pe_user_id(peProdUser.getId());
				// 添加人生产企业id
				// entity.setAdd_pe_entp_id(peProdUser.getProd_entp_id());

				super.getFacade().getKonkaXxPdPropService().createKonkaXxPdProp(entity);
				saveMessage(request, "entity.inserted");
			}
		} else {
			// 修改回选。
			KonkaXxPdProp bp = new KonkaXxPdProp();
			bp.setIs_del(0);
			bp.setProp_name(entity.getProp_name().trim());
			bp.setCls_id(entity.getCls_id());
			// bp.getMap().put("par_cls_id", entity.getCls_id());
			bp.getMap().put("prop_id_not_eq", prop_id);
			List<KonkaXxPdProp> bpList = super.getFacade().getKonkaXxPdPropService().getKonkaXxPdPropList(bp);
			if (null != bpList && bpList.size() > 0) {
				BasePdClazz bpp = new BasePdClazz();
				bpp.setIs_del(0);
				bpp.getMap().put("cls_id_like", entity.getCls_id());
				List<BasePdClazz> bppList = super.getFacade().getBasePdClazzService().getBasePdClazzList(bpp);
				StringBuffer sb = new StringBuffer();
				if (null != bppList && bppList.size() > 0) {
					for (BasePdClazz bpc : bppList) {
						KonkaXxPdProp bpp1 = new KonkaXxPdProp();
						bpp1.setIs_del(0);
						bpp1.setCls_id(bpc.getCls_id());
						List<KonkaXxPdProp> bpp1List = super.getFacade().getKonkaXxPdPropService()
								.getKonkaXxPdPropList(bpp1);
						if (null != bpp1List && bpp1List.size() > 0) {
							sb = sb.append(bpc.getTree_name()).append(":");
							for (int i = 0; i < bpp1List.size(); i++) {
								if (StringUtils.isNotBlank(bpp1List.get(i).getProp_name())) {
									sb = sb.append("'").append(bpp1List.get(i).getProp_name()).append("'").append(";");
								}
							}
							sb.append("<br/>");
						}
					}
				} else {
					sb.append("");
				}
				dynaBean.set("add_property", sb.toString());

				String msg = super.getMessage(request, "entity.exists");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else {
				entity.setLast_edit_user_id(user_id.getId());
				entity.setLast_edit_date(new Date());

				super.getFacade().getKonkaXxPdPropService().modifyKonkaXxPdProp(entity);
				saveMessage(request, "entity.updated");
			}
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String prop_id = (String) dynaBean.get("prop_id");

		if (!GenericValidator.isLong(prop_id)) {
			saveError(request, "errors.long", new String[] { prop_id });
			return mapping.findForward("list");
		}

		KonkaXxPdProp entity = new KonkaXxPdProp();
		entity.setProp_id(Long.valueOf(prop_id));
		entity = super.getFacade().getKonkaXxPdPropService().getKonkaXxPdProp(entity);

		if (null == entity) {
			saveError(request, "errors.long", new String[] { prop_id });
			return mapping.findForward("list");
		}
		request.setAttribute("entity", entity);

		if (null != entity.getCls_id()) {
			BasePdClass bpc = new BasePdClass();
			bpc.setCls_id(entity.getCls_id());
			bpc = super.getFacade().getBasePdClassService().getBasePdClass(bpc);
			request.setAttribute("cls_name", bpc.getCls_name());
		}
		if (null != entity.getCategory_id()) {
			KonkaXxPropCategory bpcg = new KonkaXxPropCategory();
			bpcg.setCategory_id(entity.getCategory_id());
			bpcg = super.getFacade().getKonkaXxPropCategoryService().getKonkaXxPropCategory(bpcg);
			request.setAttribute("category_name", bpcg.getCategory_name());
		}
		return mapping.findForward("view");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String prop_id = (String) dynaBean.get("prop_id");

		if (!StringUtils.isBlank(prop_id) && GenericValidator.isLong(prop_id)) {
			KonkaXxPdProp entity = new KonkaXxPdProp();
			entity.setProp_id(Long.valueOf(prop_id));
			entity.setIs_del(1);
			getFacade().getKonkaXxPdPropService().modifyKonkaXxPdProp(entity);
		}
		saveMessage(request, "entity.deleted");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward getAddedProperty(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String cls_id = (String) dynaBean.get("cls_id");
		if (GenericValidator.isLong(cls_id)) {
			// KonkaXxPdProp bpp = new KonkaXxPdProp();
			KonkaXxPdProp bp = new KonkaXxPdProp();
			bp.setIs_del(0);
			if (StringUtils.isNotBlank(cls_id)) {
				bp.setCls_id(Long.valueOf(cls_id));
			}
			List<KonkaXxPdProp> bpList = super.getFacade().getKonkaXxPdPropService().getKonkaXxPdPropWithTreeNameList(
					bp);

			StringBuffer sb = new StringBuffer("{'result':'");

			if (null != bpList && bpList.size() > 0) {
				for (KonkaXxPdProp temp : bpList) {
					if (null != temp.getMap().get("total_prop_name") && null != temp.getMap().get("tree_name")) {
						String str = temp.getMap().get("tree_name").toString() + ":"
								+ temp.getMap().get("total_prop_name").toString();
						sb.append(str).append(";");
					}
				}
			} else {
				sb.append("");
			}
			sb.append("'}");
			super.render(response, sb.toString(), "text/x-json;charset=UTF-8");
		}
		return null;
	}

	public ActionForward listKonkaXxPdPropCategory(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String category_name_like = (String) dynaBean.get("category_name_like");
		String cls_id = (String) dynaBean.get("cls_id");

		KonkaXxPropCategory entity = new KonkaXxPropCategory();
		if (GenericValidator.isLong(cls_id)) {
			entity.setCls_id(Long.valueOf(cls_id));
		}
		entity.getMap().put("category_name_like", category_name_like);

		Long recordCount = super.getFacade().getKonkaXxPropCategoryService().getKonkaXxPropCategoryCount(entity);
		pager.init(recordCount, Integer.valueOf("45"), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(Integer.valueOf("45"));

		List<KonkaXxPropCategory> entpList = super.getFacade().getKonkaXxPropCategoryService()
				.getKonkaXxPropCategoryPaginatedList(entity);
		request.setAttribute("entpList", entpList);

		dynaBean.set("category_name_like", category_name_like);
		return new ActionForward("/../manager/zmd/KonkaXxPdProp/list_basepropcategory.jsp");

	}
}
