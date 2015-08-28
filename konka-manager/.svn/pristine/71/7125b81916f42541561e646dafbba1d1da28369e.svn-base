package com.ebiz.mmt.web.struts.manager.admin;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaPartership;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Xiao,GuoJian 
 * @version 2014-04-16
 */
public class KonkaPartershipAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String mod_id = (String) dynaBean.get("mod_id");
		String dept_id = (String) dynaBean.get("dept_id");
		String shoudf_id_like = (String) dynaBean.get("shoudf_id_like");
		String songdf_id_like = (String) dynaBean.get("songdf_id_like");
		String shoudf_name_like = (String) dynaBean.get("shoudf_name_like");
		String songdf_name_like = (String) dynaBean.get("songdf_name_like");
		String export = (String)dynaBean.get("export");
		
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		KonkaPartership entity = new KonkaPartership();
		if (StringUtils.isNotBlank(shoudf_id_like)) {
			entity.getMap().put("shoudf_id_like", shoudf_id_like);
		}
		if (StringUtils.isNotBlank(songdf_id_like)) {
			entity.getMap().put("songdf_id_like", songdf_id_like);
		}
		if (StringUtils.isNotBlank(shoudf_name_like)) {
			entity.getMap().put("shoudf_name_like", shoudf_name_like);
		}
		if (StringUtils.isNotBlank(songdf_name_like)) {
			entity.getMap().put("songdf_name_like", songdf_name_like);
		}

//		// 数据级别判断开始
//		Long __dept_id = StringUtils.isNotBlank(dept_id) ? Long.valueOf(dept_id) : ui.getDept_id(); // 默认为当前用户所在部门
//		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
//		logger.info("Max level : {}", max_dlevel);
//		request.setAttribute("max_dlevel", max_dlevel);
//		switch (max_dlevel) {
//		case 9:
//			// 集团及以下部门可见
//			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
//			if (StringUtils.isNotBlank(dept_id)) {
//				entity.getMap().put("dept_id", null);
//				entity.getMap().put("fgs_dept_value", dept_id);
//			}
//
//			break;
//		case 8:
//			// 分公司及以下部门可见
//			KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
//			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
//				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
//				entity.getMap().put("fgs_dept_value", __dept_id);
//			}
//			break;
//		case 7:
//			// 我所在的部门及以下部门可见
//			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
//			entity.getMap().put("dept_id_start", __dept_id);
//			break;
//		case 0:
//			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
//			entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
//			break;
//		default:
//			// 出错
//		}
//		entity.getMap().put("session_user_id", ui.getId());
//		// 数据级别判断结束
		
		// 数据级别判断开始
		Long __dept_id = StringUtils.isNotBlank(dept_id) ? Long.valueOf(dept_id) : ui.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				entity.getMap().put("dept_id_start", __dept_id);
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);
			break;
		case 0:
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", ui.getId());
		// 数据级别判断结束

		Long recordCount = getFacade().getKonkaPartershipService().getKonkaPartershipCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaPartership> entityList = super.getFacade().getKonkaPartershipService()
				.getKonkaPartershipPaginatedList(entity);

		request.setAttribute("entityList", entityList);
		
		// 导出Excel
		if (StringUtils.isNotBlank(export)) {
			if (recordCount > 5000) {
				super.renderJavaScript(response, "window.onload=function(){alert('只能导出5000条以下数据！');history.back();}");
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("客户管理合作伙伴关系")
			        + ".xls");
			entity.getRow().setFirst(0);
			entity.getRow().setCount(recordCount.intValue());
			
			List<KonkaPartership> allList = super.getFacade().getKonkaPartershipService()
					.getKonkaPartershipPaginatedList(entity);
			request.setAttribute("allList", allList);
			if (allList != null && allList.size() > 0) {
				return new ActionForward("/admin/KonkaPartership/list_report.jsp");
			} else {
				return null;
			}
		}

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {// 左边菜单栏导航树
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);// 导航
		// DynaBean dynaBean = (DynaBean) form;
		// PeProdUser ui = (PeProdUser)
		// request.getSession().getAttribute(Constants.USER_INFO);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {


		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String songdf_ids = (String) dynaBean.get("songdf_ids");
		KonkaPartership entity = new KonkaPartership();
		super.copyProperties(entity, form);
		entity.setAdd_date(new Date());

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		entity.setAdd_user_id(ui.getJob_id());
		if (entity.getId() == null) {
			if (StringUtils.isNotEmpty(songdf_ids)) {
				String[] songdf_id_s = songdf_ids.replaceAll("\r|\n", "").split(",");
				for (String songdf_id : songdf_id_s) {
					if (songdf_id != null && !songdf_id.equals("")) {
						entity.setSongdf_id(songdf_id);
						KonkaPartership ship = new KonkaPartership();
						ship.setShoudf_id(entity.getShoudf_id());
						ship.setSongdf_id(entity.getSongdf_id());
						List<KonkaPartership> list = super.getFacade().getKonkaPartershipService()
								.getKonkaPartershipList(ship);
						if (null != list && list.size() > 0) {
							super.renderJavaScript(response, "alert('售达方对应的送达方记录已经存在，请重新选择！');history.back();");
							return null;
						} else {
							if (null != entity.getShoudf_id()) {
								KonkaR3Shop shoudf = new KonkaR3Shop();
								shoudf.setR3_code(entity.getShoudf_id());
								shoudf = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shoudf);
								entity.setShoudf_name(shoudf.getCustomer_name());
							}
							if (null != entity.getSongdf_id()) {
								KonkaR3Shop songdf = new KonkaR3Shop();
								songdf.setR3_code(entity.getSongdf_id());
								songdf = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(songdf);
								entity.setSongdf_name(songdf.getCustomer_name());
							}
							super.getFacade().getKonkaPartershipService().createKonkaPartership(entity);
						}
					}
				}
			}
			saveMessage(request, "entity.inserted");
		} else {
			KonkaPartership ship = new KonkaPartership();
			ship.setShoudf_id(entity.getShoudf_id());
			ship.setSongdf_id(entity.getSongdf_id());
			List<KonkaPartership> list = super.getFacade().getKonkaPartershipService().getKonkaPartershipList(ship);
			if (null != list && list.size() > 0) {
				super.renderJavaScript(response, "alert('售达方对应的送达方记录已经存在，请重新选择！');history.back();");
				return null;
			} else {
				super.getFacade().getKonkaPartershipService().modifyKonkaPartership(entity);
			}
			saveMessage(request, "entity.updated");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		KonkaPartership entity = new KonkaPartership();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaPartershipService().getKonkaPartership(entity);

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		// return mapping.findForward("input");
		return new ActionForward("/admin/KonkaPartership/form_edit.jsp");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		KonkaPartership entity = new KonkaPartership();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaPartershipService().getKonkaPartership(entity);

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		return mapping.findForward("view");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaPartership entity = new KonkaPartership();
			entity.setId(Long.valueOf(id));
			getFacade().getKonkaPartershipService().removeKonkaPartership(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaPartership entity = new KonkaPartership();
			entity.getMap().put("pks", pks);
			getFacade().getKonkaPartershipService().removeKonkaPartership(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	
	public ActionForward songDFList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ActionForward("/admin/KonkaPartership/songDFList.jsp");
	}
	
	/**
	 * 合作伙伴关系获取R3编码
	 * 
	 * @author BaiYang,OuYang
	 * @date 2014-2-8
	 */
	public ActionForward songDFListDate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String code_like = (String) dynaBean.get("code_like");
		String dept_id = (String) dynaBean.get("dept_id");
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		Map<String, Object> allmap = new HashMap<String, Object>();
		KonkaR3Shop entity = new KonkaR3Shop();
		super.copyProperties(entity, form);
		// entity.setIs_del(0L);

		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("dept_id", dept_id);
			entity.getMap().put("user_id", ui.getId());
		}

		// 数据级别判断开始
		Long __dept_id = StringUtils.isNotBlank(dept_id) ? Long.valueOf(dept_id) : ui.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			if (StringUtils.isNotBlank(dept_id)) {
				entity.getMap().put("dept_id", null);
				entity.getMap().put("fgs_dept_value", dept_id);
			}

			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				// entity.getMap().put("dept_id_start", __dept_id);
				entity.getMap().put("fgs_dept_value", __dept_id);
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);
			break;
		case 0:
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			// entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", ui.getId());

		// entity.getMap().put("is_assign", "true");
		// entity.getMap().put("leftYWY", "true");
		// entity.getMap().put("YWY", "true");
		// entity.getMap().put("dept_id_start", __dept_id);
		// 数据级别判断结束

		if (StringUtils.isNotBlank(code_like)) {
			entity.getMap().put("code_like", code_like);
		}

		Long recordCount = getFacade().getKonkaR3ShopService().getKonkaR3ShopForR3CodeCount(entity);
		pager.init(recordCount, 800, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService().getKonkaR3ShopForR3CodePaginatedList(entity);
        allmap.put("entityList", entityList);
		JSONArray jsonArray = JSONArray.fromObject(allmap);

		int start = jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString().substring(start + 1, end + 1));
		out.flush();
		out.close();
		return null;
	}

	public ActionForward shouDFList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String code_like = (String) dynaBean.get("code_like");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		
		String dept_id = (String) dynaBean.get("dept_id");
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		KonkaR3Shop entity = new KonkaR3Shop();
		super.copyProperties(entity, form);
		entity.setIs_del(0L);

		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("dept_id", dept_id);
			entity.getMap().put("user_id", ui.getId());
		}

		// 数据级别判断开始
		Long __dept_id = StringUtils.isNotBlank(dept_id) ? Long.valueOf(dept_id) : ui.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			if (StringUtils.isNotBlank(dept_id)) {
				entity.getMap().put("dept_id", null);
				entity.getMap().put("fgs_dept_value", dept_id);
			}

			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				// entity.getMap().put("dept_id_start", __dept_id);
				entity.getMap().put("fgs_dept_value", __dept_id);
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);
			break;
		case 0:
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			// entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", ui.getId());

		// entity.getMap().put("is_assign", "true");
		// entity.getMap().put("leftYWY", "true");
		// entity.getMap().put("YWY", "true");
		// entity.getMap().put("dept_id_start", __dept_id);
		// 数据级别判断结束

		if (StringUtils.isNotBlank(code_like)) {
			entity.getMap().put("code_like", code_like);
		}
		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like);
		}
		
		Long recordCount = getFacade().getKonkaR3ShopService().getKonkaR3ShopForR3CodeCount(entity);
		pager.init(recordCount, 800, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService().getKonkaR3ShopForR3CodePaginatedList(entity);

		request.setAttribute("entityList", entityList);

		return new ActionForward(response.encodeRedirectURL("/admin/KonkaPartership/shouDFList.jsp"));
	}

}
