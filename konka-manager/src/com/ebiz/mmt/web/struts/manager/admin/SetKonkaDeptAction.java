package com.ebiz.mmt.web.struts.manager.admin;

import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Cheng,Bing
 * @version 2011-09-26
 */
public class SetKonkaDeptAction extends BaseAction {

	// public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// return this.list(mapping, form, request, response);
	// }
	//
	// public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// setNaviStringToRequestScope(form, request);
	//
	// return mapping.findForward("list");
	// }
	//
	// public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// setNaviStringToRequestScope(form, request);
	// DynaBean dynaBean = (DynaBean) form;
	// String id = (String) dynaBean.get("id"); // 部门ID
	// String bj = (String) dynaBean.get("bj");
	//
	// PeDept entity = new PeDept();
	// entity.setDept_id(Long.valueOf(id));
	// entity = getFacade().getPeDeptService().getPeDept(entity);
	// dynaBean.set("par_dept_name", entity.getDept_name());
	// dynaBean.set("par_dept_id", entity.getDept_id());
	//
	// BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
	// baseProvinceListFour.setDel_mark(0);
	// baseProvinceListFour.setPar_index(0l);
	// List<BaseProvinceListFour> provinceList = getFacade().getBaseProvinceListFourService()
	// .getBaseProvinceListFourList(baseProvinceListFour);
	// request.setAttribute("provinceList", provinceList);
	//
	// // KonkaUser konkaUser = new KonkaUser();
	// // konkaUser.setDept_id(Long.valueOf(id));
	// // konkaUser.setIs_del(0);
	// // konkaUser.getMap().put("not_admin", "true");
	// // List<KonkaUser> list =
	// // getFacade().getKonkaUserService().getKonkaUserListWithDeptNameForLeaderResultList(konkaUser);
	// // request.setAttribute("leaderList", list);
	//
	// dynaBean.set("bj", bj);
	// dynaBean.set("id", id);
	// dynaBean.set("add", "add");
	// request.setAttribute("queryString", super.serialize(request, "dept_id", "method"));
	// return mapping.findForward("input");
	// }
	//
	// public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// setNaviStringToRequestScope(form, request);
	// DynaBean dynaBean = (DynaBean) form;
	// String id = (String) dynaBean.get("id");
	// String bj = (String) dynaBean.get("bj");
	//
	// logger.info("__________________________id={}" + id);
	//
	// PeDept entity = new PeDept();
	// entity.setDept_id(Long.valueOf(id));
	// entity = getFacade().getPeDeptService().getPeDept(entity);
	// super.copyProperties(form, entity);
	//
	// PeProdUser PeProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
	// // 获取登录用户 企业信息
	// PeProdUser SxPeProdUser = new PeProdUser();
	// SxPeProdUser.setIs_del(0);
	// SxPeProdUser.setUser_name(PeProdUser.getUser_name());
	// SxPeProdUser = super.getFacade().getPeProdUserService().getPeProdUser(SxPeProdUser);
	// Integer a = Integer.valueOf(id);
	// if (a < SxPeProdUser.getDept_id()) {
	// dynaBean.set("not_see", "true");
	// }
	//
	// BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
	// baseProvinceListFour.setDel_mark(0);
	// baseProvinceListFour.setPar_index(0l);
	// List<BaseProvinceListFour> provinceList = getFacade().getBaseProvinceListFourService()
	// .getBaseProvinceListFourList(baseProvinceListFour);
	// request.setAttribute("provinceList", provinceList);
	//
	// PeProdUser konkaUser = new PeProdUser();
	// konkaUser.setDept_id(Long.valueOf(id));
	// konkaUser.setIs_del(0);
	// konkaUser.getMap().put("not_admin", "true");
	// // List<PeProdUser> list =
	// // getFacade().getKonkaUserService().getKonkaUserListWithDeptNameForLeaderResultList(konkaUser);
	// // request.setAttribute("leaderList", list);
	//
	// PeProdUser kUser = new PeProdUser();
	// kUser.setIs_del(0);
	// kUser.getMap().put("deptId", id);
	// // List<PeProdUser> deptUserlist =
	// // getFacade().getKonkaUserService().getKonkaUserListWithDeptNameForResultList(kUser);
	// // request.setAttribute("deptUserlist", deptUserlist);
	//
	// dynaBean.set("par_dept_id", entity.getPar_id());
	// dynaBean.set("edit", "edit");
	// dynaBean.set("bj", bj);
	//
	// return mapping.findForward("input");
	// }
	//
	// public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// PeDept entity = new PeDept();
	// super.copyProperties(entity, form);
	//
	// // HttpSession session = request.getSession();
	// // KonkaUser ku= (KonkaUser) session.getAttribute(Keys.ADMIN);
	// //
	// // if (null != entity.getP_index()) {
	// // Long p_index = entity.getP_index();
	// // BaseProvinceListFour b = new BaseProvinceListFour();
	// // b.setP_index(p_index);
	// // b.setDel_mark(0);
	// // b = getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(b);
	// // entity.setP_name(b.getP_name());
	// // }
	// //
	// // if (StringUtils.isNotBlank(add)) {
	// // entity.setAdd_user_id(ku.getUser_id());
	// // entity.setAdd_date(new Date());
	// // super.getFacade().getKonkaDeptService().createKonkaDept(entity);
	// // } else if (StringUtils.isNotBlank(edit)) {
	// // super.getFacade().getKonkaDeptService().modifyKonkaDept(entity);
	// // }
	// //
	// super.renderJavaScript(response, "alert(\"" + super.getMessage(request, "operate.ok")
	// + "\");window.parent.frames[0].location.reload(true);");
	// return null;
	// }
	//
	// public ActionForward showDeptInfoTree(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// // KonkaUser ku= (KonkaUser) session.getAttribute(Keys.ADMIN);
	//
	// // PeDept entity = new PeDept();
	// // entity.getMap().put("dept_id", ku.getDept_id());
	// // List<PeDept> deptInfoList = getFacade().getPeDeptService().getKonkaDeptTreeNameByUserForResultList(entity);
	//
	// // String treeNodes = getTreeNodesFromDeptInfoList(deptInfoList);
	//
	// // request.setAttribute("treeNodes", treeNodes);
	//
	// return new ActionForward("/../WEB-INF/pages/jsp/admin/KonkaDept/deptInfoTree.jsp");
	// // return new ActionForward("/KonkaDept/deptInfoTree.jsp");
	// }
	//
	// public String getTreeNodesFromDeptInfoList(List<PeDept> deptInfoList) {
	//
	// StringBuffer sb = new StringBuffer();
	//
	// for (PeDept konkaDept : deptInfoList) {
	//
	// String _id = String.valueOf(konkaDept.getDept_id());
	// String _par_id = String.valueOf(konkaDept.getPar_id());
	//
	// if ("0".equals(_par_id)) {
	// if (StringUtils.equals(_id, _par_id)) {
	// _par_id = "-1";
	// }
	// }
	// String _text = StringUtils.replace(konkaDept.getDept_name(), ":", "&#58;");
	// if (StringUtils.isEmpty(_text)) {
	// continue;
	// }
	// String _hint = _text;
	// // String _url = StringUtils.replace(sysModule.getMod_url(), ":", "&#58;");
	//
	// sb.append("\ntree.nodes[\"").append(_par_id).append("_").append(_id).append("\"]=\"");
	// sb.append("text:").append(_text).append(";");
	// if (_hint.length() > 0) {
	// if (null != konkaDept.getDept_desc()) {
	// sb.append("hint:").append(konkaDept.getDept_desc()).append(";");
	// } else {
	// sb.append("hint:").append(_hint).append(";");
	// }
	// }
	//
	// sb.append("url:").append("KonkaDept.do").append(";");
	//
	// sb.append("data:").append("method=edit");
	// sb.append("&par_id=").append(_par_id);
	// sb.append("&id=").append(_id).append(";");
	//
	// sb.append("\";");
	// }
	// logger.info("_____________________________________sb{}=" + sb.toString());
	// return sb.toString();
	// }
	//
	// public ActionForward validateDeptName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	//
	// DynaBean dynaBean = (DynaBean) form;
	// String dept_name = (String) dynaBean.get("dept_name");
	// String dept_id = (String) dynaBean.get("dept_id");
	// PeDept entity = new PeDept();
	// String isExist = "null";
	//
	// if (StringUtils.isNotBlank(dept_name)) {
	// entity.setDept_name(dept_name);
	// entity.getMap().put("map.not_konka", dept_id);
	// if (null == super.getFacade().getPeDeptService().getPeDept(entity)) {
	// isExist = String.valueOf("0");
	// } else {
	// isExist = String.valueOf("1");
	// }
	// }
	// super.render(response, isExist, "text/x-json;charset=UTF-8");
	// return null;
	//
	// }
}
