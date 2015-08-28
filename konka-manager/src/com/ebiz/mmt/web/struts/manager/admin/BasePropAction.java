package com.ebiz.mmt.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BasePdClass;
import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.BaseProp;
import com.ebiz.mmt.domain.BasePropCategory;
import com.ebiz.mmt.domain.BasePropValItem;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Chen,ShunHua
 * @version 2011-09-23
 */
public class BasePropAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}
	
	public ActionForward toWindowFramePage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ActionForward("/../manager/admin/BaseProp/windowFrame.jsp");
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String prop_name_like = (String) dynaBean.get("prop_name_like");
		String cls_id = (String) dynaBean.get("cls_id");
		String category_name_like = (String) dynaBean.get("category_name_like");

		BaseProp entity = new BaseProp();

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

		Long recordCount = super.getFacade().getBasePropService().getBasePropCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<BaseProp> entityList = super.getFacade().getBasePropService().getBasePropPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		if (null != entityList && entityList.size() > 0) {
			List<String> strList = new ArrayList<String>();
			for (BaseProp bp : entityList) {
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
			}
			request.setAttribute("strList", strList);
		}

//		PeRoleUser peRoleUser = (PeRoleUser) request.getSession().getAttribute(Constants.ROLE_INFO);

		// 判断当前用户是否是事业部或系统管理员
		String is_division_or_admin = "false";
		
		@SuppressWarnings("unchecked")
		List<PeRoleUser> peRoleUserList =  (List<PeRoleUser>) request.getSession().getAttribute(Constants.ROLE_INFO_LIST);
		boolean role_id_ge_20_le_29_or_ge_10 = false;
		for (PeRoleUser  peRoleUser: peRoleUserList) {
			if ((peRoleUser.getRole_id() >= 20L && peRoleUser.getRole_id() <= 29L) || peRoleUser.getRole_id() == 10L) {
				role_id_ge_20_le_29_or_ge_10 = true;
				break;
			}
		}
		
		if (role_id_ge_20_le_29_or_ge_10) {
			is_division_or_admin = "true";
		}
		
		request.setAttribute("is_division_or_admin", is_division_or_admin);

		request.setAttribute("basePdClassList", getBasePdClazzList());
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		SysModule sysModule = new SysModule();
		sysModule.setMod_id(Integer.valueOf(mod_id));
		sysModule = super.getFacade().getSysModuleService().getSysModule(sysModule);

		if (null != sysModule) {
			dynaBean.set("article_mod_name", sysModule.getMod_name());
			dynaBean.set("order_value", "0");
		}

		request.setAttribute("basePdClassList", getBasePdClazzList());
		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String prop_id = (String) dynaBean.get("prop_id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(prop_id)) {
			saveError(request, "errors.long", new String[] { prop_id });
			return mapping.findForward("list");
		}

		BaseProp entity = new BaseProp();
		entity.setProp_id(Long.valueOf(prop_id));
		entity = super.getFacade().getBasePropService().getBaseProp(entity);
		if (null == entity) {
			saveError(request, "errors.long", new String[] { prop_id });
			return mapping.findForward("list");
		} else {
			Long cls_id = entity.getCls_id();
			Long category_id = entity.getCategory_id();
			if (null != category_id) {
				BasePropCategory bpcg = new BasePropCategory();
				bpcg.setCategory_id(category_id);
				bpcg = super.getFacade().getBasePropCategoryService().getBasePropCategory(bpcg);
				dynaBean.set("category_name", bpcg.getCategory_name());
			}

			BasePdClazz bpp = new BasePdClazz();
			bpp.setIs_del(0);
			bpp.getMap().put("cls_id_like", cls_id);
			List<BasePdClazz> bppList = super.getFacade().getBasePdClazzService().getBasePdClazzList(bpp);
			StringBuffer sb = new StringBuffer();
			if (null != bppList && bppList.size() > 0) {
				for (BasePdClazz bpc : bppList) {
					BaseProp bpp1 = new BaseProp();
					bpp1.setIs_del(0);
					bpp1.setCls_id(bpc.getCls_id());
					List<BaseProp> bpp1List = super.getFacade().getBasePropService().getBasePropList(bpp1);
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
		request.setAttribute("basePdClassList", getBasePdClazzList());
		super.copyProperties(form, entity);
		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String prop_id = (String) dynaBean.get("prop_id");

		if (!GenericValidator.isLong(prop_id)) {
			saveError(request, "errors.long", new String[] { prop_id });
			return mapping.findForward("list");
		}

		BaseProp entity = new BaseProp();
		entity.setProp_id(Long.valueOf(prop_id));
		entity = super.getFacade().getBasePropService().getBaseProp(entity);

		if (null == entity) {
			saveError(request, "errors.long", new String[] { prop_id });
			return mapping.findForward("list");
		}
		entity.setQueryString(super.serialize(request, "id", "mod_id"));
		request.setAttribute("entity", entity);

		if (null != entity.getCls_id()) {
			BasePdClass bpc = new BasePdClass();
			bpc.setCls_id(entity.getCls_id());
			bpc = super.getFacade().getBasePdClassService().getBasePdClass(bpc);
			request.setAttribute("cls_name", bpc.getCls_name());
		}
		if (null != entity.getCategory_id()) {
			BasePropCategory bpcg = new BasePropCategory();
			bpcg.setCategory_id(entity.getCategory_id());
			bpcg = super.getFacade().getBasePropCategoryService().getBasePropCategory(bpcg);
			request.setAttribute("category_name", bpcg.getCategory_name());
		}
		return mapping.findForward("view");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String prop_id = (String) dynaBean.get("prop_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String tree_param = (String) dynaBean.get("tree_param");
		String[] more_values = request.getParameterValues("more_values");
		String category_id_set_null = (String) dynaBean.get("category_id_set_null");

		BaseProp entity = new BaseProp();
		super.copyProperties(entity, form);

		entity.setProp_name(entity.getProp_name().trim());
		if (StringUtils.isNotBlank(category_id_set_null)) {
			entity.getMap().put("category_id_set_null", "true");
		}
		if (null != more_values && more_values.length > 0) {
			entity.getMap().put("more_values", more_values);
		}

		if (StringUtils.isBlank(prop_id)) {
			BaseProp bp = new BaseProp();
			bp.setProp_name(entity.getProp_name().trim());
			// bp.setCategory_id(entity.getCategory_id());
			// bp.setCls_id(entity.getCls_id());
			bp.getMap().put("par_cls_id", entity.getCls_id());
			List<BaseProp> bpList = super.getFacade().getBasePropService().getBasePropList(bp);
			if (null != bpList && bpList.size() > 0) {
				BasePdClazz bpp = new BasePdClazz();
				bpp.setIs_del(0);
				bpp.getMap().put("cls_id_like", entity.getCls_id());
				List<BasePdClazz> bppList = super.getFacade().getBasePdClazzService().getBasePdClazzList(bpp);
				StringBuffer sb = new StringBuffer();
				if (null != bppList && bppList.size() > 0) {
					for (BasePdClazz bpc : bppList) {
						BaseProp bpp1 = new BaseProp();
						bpp1.setIs_del(0);
						bpp1.setCls_id(bpc.getCls_id());
						List<BaseProp> bpp1List = super.getFacade().getBasePropService().getBasePropList(bpp1);
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
				String msg = ("您输入的属性名称已存在，请重新输入");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else {
				if (null == super.checkUserModPopeDom(form, request, "1")) {
					return super.checkPopedomInvalid(request, response);
				}
				// HttpSession session = request.getSession();
				// PeProdUser peProdUser = (PeProdUser)
				// session.getAttribute(Constants.PE_PROD_USER_SESSION);

				entity.setAdd_date(new Date());
				entity.setIs_del(0);
				// 添加人id
				// entity.setAdd_pe_user_id(peProdUser.getId());
				// 添加人生产企业id
				// entity.setAdd_pe_entp_id(peProdUser.getProd_entp_id());

				super.getFacade().getBasePropService().createBaseProp(entity);
				saveMessage(request, "entity.inserted");
			}
		} else {
			BaseProp bp = new BaseProp();
			bp.setIs_del(0);
			bp.setProp_name(entity.getProp_name().trim());
			// bp.setCls_id(entity.getCls_id());
			bp.getMap().put("par_cls_id", entity.getCls_id());
			bp.getMap().put("prop_id_not_eq", prop_id);
			List<BaseProp> bpList = super.getFacade().getBasePropService().getBasePropList(bp);
			if (null != bpList && bpList.size() > 0) {
				BasePdClazz bpp = new BasePdClazz();
				bpp.setIs_del(0);
				bpp.getMap().put("cls_id_like", entity.getCls_id());
				List<BasePdClazz> bppList = super.getFacade().getBasePdClazzService().getBasePdClazzList(bpp);
				StringBuffer sb = new StringBuffer();
				if (null != bppList && bppList.size() > 0) {
					for (BasePdClazz bpc : bppList) {
						BaseProp bpp1 = new BaseProp();
						bpp1.setIs_del(0);
						bpp1.setCls_id(bpc.getCls_id());
						List<BaseProp> bpp1List = super.getFacade().getBasePropService().getBasePropList(bpp1);
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
				// String msg = super.getMessage(request, "prop.name.repeat");
				// super.renderJavaScript(response,
				// "window.onload=function(){alert('" + msg +
				// "');history.back();}");
				// return null;
			} else {
				if (null == super.checkUserModPopeDom(form, request, "2")) {
					return super.checkPopedomInvalid(request, response);
				}

				super.getFacade().getBasePropService().modifyBaseProp(entity);
				saveMessage(request, "entity.updated");
			}
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("tree_param=" + tree_param);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String[] pks = (String[]) dynaBean.get("pks");
		String prop_id = (String) dynaBean.get("prop_id");

		if (!StringUtils.isBlank(prop_id) && GenericValidator.isLong(prop_id)) {
			BaseProp entity = new BaseProp();
			entity.setProp_id(Long.valueOf(prop_id));
			entity.setIs_del(1);
			getFacade().getBasePropService().removeBaseProp(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			BaseProp entity = new BaseProp();
			for (String id : pks) {
				entity.setProp_id(Long.valueOf(id));
				entity.setIs_del(1);
				super.getFacade().getBasePropService().removeBaseProp(entity);
			}
			saveMessage(request, "entity.deleted");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "pks", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward getBasePdClass(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String cls_name_like = (String) dynaBean.get("cls_name_like");
		String cls_id_max = (String) dynaBean.get("cls_id_max");

		BasePdClass entity = new BasePdClass();
		entity.setIs_del(0);
		entity.getMap().put("cls_name_like", cls_name_like);

		if (StringUtils.isBlank(cls_id_max)) {
			entity.setPar_id(Long.valueOf(0));
		} else {
			entity.setPar_id(Long.valueOf(cls_id_max));
		}

		Long recordCount = super.getFacade().getBasePdClassService().getBasePdClassCount(entity);
		pager.init(recordCount, Integer.valueOf("45"), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(Integer.valueOf("45"));

		List<BasePdClass> entpList = super.getFacade().getBasePdClassService().getBasePdClassPaginatedList(entity);
		request.setAttribute("entpList", entpList);

		dynaBean.set("cls_name_like", cls_name_like);
		dynaBean.set("cls_id_max", cls_id_max);
		return new ActionForward("/../admin/BaseProp/list_basepdclass.jsp");

	}

	public ActionForward listBasePropCategory(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String category_name_like = (String) dynaBean.get("category_name_like");
		String par_cls_id = (String) dynaBean.get("cls_id");

		BasePropCategory entity = new BasePropCategory();
		if (GenericValidator.isLong(par_cls_id)) {
			entity.getMap().put("par_cls_id", par_cls_id);
		}
		if (StringUtils.isNotBlank(category_name_like)) {
			entity.getMap().put("category_name_like", category_name_like);
		}

		Long recordCount = super.getFacade().getBasePropCategoryService().getBasePropCategoryCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<BasePropCategory> entpList = super.getFacade().getBasePropCategoryService()
				.getBasePropCategoryPaginatedList(entity);

		request.setAttribute("entpList", entpList);

		dynaBean.set("cls_name_like", category_name_like);
		return new ActionForward("/../manager/admin/BaseProp/list_basepropcategory.jsp");
	}

	public ActionForward getAddedProperty(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String cls_id = (String) dynaBean.get("cls_id");
		if (GenericValidator.isLong(cls_id)) {
			// BaseProp bpp = new BaseProp();
			BaseProp bp = new BaseProp();
			bp.setIs_del(0);
			if (StringUtils.isNotBlank(cls_id)) {
				bp.getMap().put("par_cls_id", cls_id);
			}
			List<BaseProp> bpList = super.getFacade().getBasePropService().getBasePropWithTreeNameList(bp);

			StringBuffer sb = new StringBuffer("{'result':'");
			if (null != bpList && bpList.size() > 0) {
				for (BaseProp temp : bpList) {
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
}
