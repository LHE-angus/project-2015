package com.ebiz.mmt.web.struts.manager.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaDeptPdLink;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Cheng,Bing
 * @version 2011.10.10
 */
public class DeptPdManagerAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		String dept_name_like = (String) dynaBean.get("dept_name_like");

		// 登录用户信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		peProdUser.setRole_id(peRoleUser.getRole_id().toString());
		request.setAttribute("role_id", peProdUser.getRole_id());

		KonkaDept dept = new KonkaDept();
		// 可查看部门范围
		if (peProdUser.getDept_id() != null) {
			dept.getMap().put("dept_id", peProdUser.getDept_id());
		} else {
			dept.getMap().put("dept_id", 0);
		}
		// 1.抽取事业部
		String types[] = new String[1];
		types[0] = "2";
		dept.getMap().put("dept_types_Y", StringUtils.join(types, ","));

		// 事业部List
		List<KonkaDept> dept_list_A = getFacade().getKonkaDeptService().getKonkaDeptTreeNameByUserForResultList(dept);

		Map<Long, String> map = new HashMap<Long, String>();
		for (int i = 0; i < dept_list_A.size(); i++) {
			KonkaDept konka_dept = dept_list_A.get(i);
			map.put(konka_dept.getDept_id(), konka_dept.getDept_name());
		}

		// 2.抽取分公司
		types[0] = "3";
		dept.getMap().put("dept_types_Y", StringUtils.join(types, ","));
		// 采用分页
		dept.getMap().put("pager_true", true);

		// 查询条件
		if (StringUtils.isNotBlank(dept_name_like)) {
			dept.getMap().put("dept_name_like", dept_name_like);
		}

		// 分公司总数
		Long recordCount = super.getFacade().getKonkaDeptService().getKonkaDeptTreeNameByUserForResultCount(dept);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		dept.getRow().setFirst(pager.getFirstRow());
		dept.getRow().setCount(pager.getRowCount());

		// 分公司List获取
		List<KonkaDept> dept_list_B = getFacade().getKonkaDeptService().getKonkaDeptTreeNameByUserForResultList(dept);

		for (int i = 0; i < dept_list_B.size(); i++) {
			KonkaDept konka_dept = dept_list_B.get(i);
			String name = map.get(konka_dept.getPar_id());
			konka_dept.getMap().put("par_dept_name", name);
		}
		request.setAttribute("entityList", dept_list_B);

		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");
		String cls_id_par = (String) dynaBean.get("cls_id");

		// 获取部门信息
		KonkaDept entity = new KonkaDept();
		entity.setDept_id(Long.valueOf(dept_id));
		entity = super.getFacade().getKonkaDeptService().getKonkaDept(entity);

		if (entity == null) {
			String msg = "部门不存在或已被删除！";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		super.copyProperties(form, entity);

		KonkaDeptPdLink deptPdLink = new KonkaDeptPdLink();
		deptPdLink.setDept_id(Long.valueOf(dept_id));
		// 已分派产品数
		// Long count = super.getFacade().getKonkaDeptPdLinkService().getKonkaDeptPdLinkCount(deptPdLink);
		// request.setAttribute("deptPd_count_yes", count);

		// 获取已分派的产品List
		List<KonkaDeptPdLink> deptPdLinkList = super.getFacade().getKonkaDeptPdLinkService()
				.getKonkaDeptPdLinkList(deptPdLink);
		request.setAttribute("deptPdLinkList", deptPdLinkList);

		// 获取所有产品List
		PePdModel pdModel = new PePdModel();
		pdModel.setIs_del(0);

		if (StringUtils.isNotBlank(cls_id_par)) {
			pdModel.getMap().put("cls_id_par", cls_id_par);
		}

		// 登录用户信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		request.setAttribute("role_id", peProdUser.getRole_id());

		request.setAttribute("basePdClazzList", getBasePdClazzList());
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (isCancelled(request)) {
			return list(mapping, form, request, response);
		}
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String dept_id = (String) dynaBean.get("dept_id");
		String[] select2 = request.getParameterValues("select2");
		
		log.info(select2);

		// 登录用户信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		// 新分派产品
		KonkaDeptPdLink entity = new KonkaDeptPdLink();
		entity.setDept_id(Long.valueOf(dept_id));
		entity.setAdd_user_id(peProdUser.getId());

		getFacade().getKonkaDeptPdLinkService().createKonkaDeptPdLinkAndClean(entity, StringUtils.join(select2, ","));

		super.saveMessage(request, "entity.updated");
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append("mod_id=" + mod_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward getMdName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String md_name = (String) dynaBean.get("md_name");

		if (StringUtils.isNotBlank(md_name)) {
			md_name = "康佳" + " " + md_name;
		}

		PePdModel pePdModel = new PePdModel();
		pePdModel.setMd_name(md_name);

		List<PePdModel> pePdModelList = super.getFacade().getPePdModelService().getPePdModelPaginatedList(pePdModel);

		StringBuffer sb = new StringBuffer("{isExist:");

		if (null != pePdModelList && pePdModelList.size() > 0) {
			String isExist_str = "1"; // "1" 表示该产品型号已存在，不可用
			for (PePdModel temp : pePdModelList) {
				if (temp.getIs_del() == 1) {
					isExist_str = "2"; // "2" 表示该产品型号已存在并且已被删除，不可用
					break;
				}
			}
			sb.append(isExist_str); // "1"表示该产品型号已存在，不可用
		} else {
			sb.append("0"); // "0"表示该产品型号不存在，可用
		}
		sb.append("}");
		super.render(response, sb.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward searchPeDept(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// DynaBean dynaBean = (DynaBean) form;
		// Pager pager = (Pager) dynaBean.get("pager");
		// String dept_name_like = (String) dynaBean.get("dept_name_like");
		//
		// PeDept entity = new PeDept();
		// super.copyProperties(entity, form);
		// entity.getMap().put("key_name", dept_name_like);
		// entity.setIs_del(0);
		// request.setAttribute("entityList",
		// getFacade().getPeDeptService().getPeDeptListWithTreeNameAndFullName(entity));

		return new ActionForward("/admin/PdDeptManager/listDept.jsp");

	}

	public ActionForward ajaxSelectDeptPdList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");
		String cls_id_par = (String) dynaBean.get("cls_id");

		// 获取部门信息
		KonkaDept entity = new KonkaDept();
		entity.setDept_id(Long.valueOf(dept_id));
		entity = super.getFacade().getKonkaDeptService().getKonkaDept(entity);

		if (entity == null) {
			String msg = "部门不存在或已被删除！";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 获取未分派的产品List
		PePdModel pdModel = new PePdModel();
		pdModel.setIs_del(0);

		// 大类cls_id
		pdModel.setCls_id(Long.valueOf(cls_id_par));

		if (StringUtils.isNotBlank(cls_id_par)) {
			pdModel.getMap().put("cls_id_par", cls_id_par);
		}
		pdModel.getMap().put("pd_not_in_dept", Long.valueOf(dept_id));

		List<PePdModel> entityList = super.getFacade().getPePdModelService().getPePdModelPaginatedList(pdModel);

		StringBuffer sb = new StringBuffer("[");

		for (PePdModel t : entityList) {
			if(t.getMap().get("full_name")!=null && t.getMd_name()!=null){
				sb.append("{\"id\":\"" + String.valueOf(t.getPd_id()) + "\",");
				sb.append("\"name\":\"" + StringEscapeUtils.escapeJavaScript(t.getMap().get("full_name").toString()+","+t.getMd_name())
						+ "\"},");
			}
		}
		sb.append("{\"end\":\"\"}]");
		super.renderJson(response, sb.toString());
		
		log.info(sb.toString());

		return null;
	}
}
