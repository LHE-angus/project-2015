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
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.AreaFightInfo;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.StringHelper;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * 区域作战
 * 
 * @author LiangHouEn
 */
public class AreaFightAction extends BaseAction {
	/**
	 * 初始化
	 * @author Angus
	 * @date 2014-08-29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HashMap allmap = new HashMap();
        //位置信息
        DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		String naviString = "";
		if (StringUtils.isNotBlank(mod_id)) {
			SysModule sysModule = new SysModule();
			sysModule.getMap().put("mod_id", mod_id);
			List<SysModule> sysModuleList = getFacade().getSysModuleService().getSysModuleList(sysModule);
			naviString = StringHelper.getNaviString(sysModuleList, " > ");
		}
		allmap.put("local_str", naviString);
		
		//当前用户信息
		PeProdUser ui = super.getSessionUserInfo(request);
		if(ui!=null){
			int max_dlevel = super.getMaxDLevel(ui.getId());
			if(max_dlevel==9){
				allmap.put("dept_id", "");
			}else{
				KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id());
				allmap.put("dept_id", dept_fgs.getDept_id());
			}
		}
		
		//转换为json数据
		JSONArray jsonArray = JSONArray.fromObject(allmap);
		
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString().substring(start+1, end+1));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 查询列表
	 * @author Liang,HouEn
	 * 2015-3-3
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		AreaFightInfo entity = getEntiy(mapping, form, request);
		
		//当前用户信息，若为分公司人员，则仅能查看已分配在该分公司下的区域以及未分配分公司的区域信息
		/*PeProdUser ui = super.getSessionUserInfo(request);
		if(ui!=null){
			int max_dlevel = super.getMaxDLevel(ui.getId());
			if(max_dlevel!=9){
				KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id());
				entity.getMap().put("dept_id", dept_fgs.getDept_id());
			}
		}*/

		Long recordCount = super.getFacade().getAreaFightInfoService().getTotalCount(entity);
		Pager pager = (Pager) dynaBean.get("pager");
		pager.init(recordCount, 10, (String)entity.getMap().get("page"));
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<HashMap> entityList = null;
		HashMap temp = new HashMap();
		if(recordCount>0){
			entityList = super.getFacade().getAreaFightInfoService().getAreaFightListForMap(entity);
			temp = entityList.get(entityList.size()-1);
			entityList.remove(entityList.size()-1);
		}
		
		//封装成JSON字符串
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("queryStr", entity.getMap().get("queryStr"));
		m.put("tal", temp);
		m.put("total", recordCount);
		if(entityList==null){
			String[] str = {};
			m.put("rows", str);
		}else{
			m.put("rows", entityList);
		}
		JSONArray jsonArray = JSONArray.fromObject(m);
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start+1, end+1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 导出数据
	 * @author Angus
	 * @date 2014-7-24
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("区域作战分析列表")
		        + ".xls");
		AreaFightInfo entity = getEntiy(mapping, form, request);
		Long recordCount = super.getFacade().getAreaFightInfoService().getTotalCount(entity);
		entity.getRow().setFirst(0);
		entity.getRow().setCount(recordCount.intValue());
		entity.getMap().put("export", true);
		List<HashMap> entityList1 = super.getFacade().getAreaFightInfoService().getAreaFightListForMap(entity);
		entityList1.remove(entityList1.size()-1);
		request.setAttribute("allList", entityList1);
		return mapping.findForward("view");
	}
	
	/**
	 * 封装数据
	 * @author Angus
	 * @date 2014-7-24
	 * @param mapping
	 * @param form
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public AreaFightInfo  getEntiy(ActionMapping mapping, ActionForm form, HttpServletRequest request) throws Exception{
		
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		String page = request.getParameter("page");
		
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		String town = request.getParameter("town");
		String p_index = request.getParameter("p_index");
		String dept_id = request.getParameter("dept_id");
		String modify_name = request.getParameter("modify_name");
		String modify_date = request.getParameter("modify_date");
		String market_money_begin = request.getParameter("market_money_begin");
		String market_money_end = request.getParameter("market_money_end");
		String market_num_begin = request.getParameter("market_num_begin");
		String market_num_end = request.getParameter("market_num_end");
		String market_prop_begin = request.getParameter("market_prop_begin");
		String market_prop_end = request.getParameter("market_prop_end");
		String t_num_begin = request.getParameter("t_num_begin");
		String t_num_end = request.getParameter("t_num_end");
		String gdp_begin = request.getParameter("gdp_begin");
		String gdp_end = request.getParameter("gdp_end");
		String human_num_begin = request.getParameter("human_num_begin");
		String human_num_end = request.getParameter("human_num_end");
		String area_size_begin = request.getParameter("area_size_begin");
		String area_size_end = request.getParameter("area_size_end");
		
		AreaFightInfo entity = new AreaFightInfo();
		super.copyProperties(entity, form);
		entity.setP_index(null);
		entity.setDept_id(null);
		
		//拼接查询条件
		StringBuffer sb = new StringBuffer("");
		if(null!=entity.getArea_name()){
			sb.append("&area_name="+entity.getArea_name());
		}
		if(null!=entity.getT_type()){
			sb.append("&t_type="+entity.getT_type());
		}
		if(null!=entity.getT_status()){
			sb.append("&t_status="+entity.getT_status());
		}
		if(null!=entity.getJd_in()){
			sb.append("&jd_in="+entity.getJd_in());
		}
		if(null!=entity.getKonka_in()){
			sb.append("&konka_in="+entity.getKonka_in());
		}
		if(null!=entity.getKonka_rank()){
			sb.append("&konka_rank="+entity.getKonka_rank());
		}
		if(null!=entity.getFirst_comp()){
			sb.append("&first_comp="+entity.getFirst_comp());
		}
		if(null!=entity.getSecond_comp()){
			sb.append("&second_comp="+entity.getSecond_comp());
		}
		if(null!=entity.getThird_comp()){
			sb.append("&third_comp="+entity.getThird_comp());
		}
		if (StringUtils.isNotBlank(province)) {
			entity.getMap().put("province", province);
			sb.append("&province="+province);
		}
		if (StringUtils.isNotBlank(city)) {
			entity.getMap().put("city", city);
			sb.append("&city="+city);
		}
		if (StringUtils.isNotBlank(country)) {
			entity.getMap().put("country", country);
			sb.append("&country="+country);
		}
		if (StringUtils.isNotBlank(town)) {
			entity.getMap().put("town", town);
			sb.append("&town="+town);
		}
		if (StringUtils.isNotBlank(p_index)) {
			entity.getMap().put("p_index", p_index);
			sb.append("&p_index="+p_index);
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("dept_id", dept_id);
			sb.append("&dept_id="+dept_id);
		}
		if (StringUtils.isNotBlank(modify_name)) {
			entity.getMap().put("modify_name", modify_name);
			sb.append("&modify_name="+modify_name);
		}
		if (StringUtils.isNotBlank(modify_date)) {
			entity.getMap().put("modify_date", modify_date);
			sb.append("&modify_date="+modify_date);
		}
		if (StringUtils.isNotBlank(market_money_begin)) {
			entity.getMap().put("market_money_begin", market_money_begin);
			sb.append("&market_money_begin="+market_money_begin);
		}
		if (StringUtils.isNotBlank(market_money_end)) {
			entity.getMap().put("market_money_end", market_money_end);
			sb.append("&market_money_end="+market_money_end);
		}
		if (StringUtils.isNotBlank(market_num_begin)) {
			entity.getMap().put("market_num_begin", market_num_begin);
			sb.append("&market_num_begin="+market_num_begin);
		}
		if (StringUtils.isNotBlank(market_num_end)) {
			entity.getMap().put("market_num_end", market_num_end);
			sb.append("&market_num_end="+market_num_end);
		}
		if (StringUtils.isNotBlank(market_prop_begin)) {
			entity.getMap().put("market_prop_begin", market_prop_begin);
			sb.append("&market_prop_begin="+market_prop_begin);
		}
		if (StringUtils.isNotBlank(market_prop_end)) {
			entity.getMap().put("market_prop_end", market_prop_end);
			sb.append("&market_prop_end="+market_prop_end);
		}
		if (StringUtils.isNotBlank(t_num_begin)) {
			entity.getMap().put("t_num_begin", t_num_begin);
			sb.append("&t_num_begin="+t_num_begin);
		}
		if (StringUtils.isNotBlank(t_num_end)) {
			entity.getMap().put("t_num_end", t_num_end);
			sb.append("&t_num_end="+t_num_end);
		}
		if (StringUtils.isNotBlank(gdp_begin)) {
			entity.getMap().put("gdp_begin", gdp_begin);
			sb.append("&gdp_begin="+gdp_begin);
		}
		if (StringUtils.isNotBlank(gdp_end)) {
			entity.getMap().put("gdp_end", gdp_end);
			sb.append("&gdp_end="+gdp_end);
		}
		if (StringUtils.isNotBlank(human_num_begin)) {
			entity.getMap().put("human_num_begin", human_num_begin);
			sb.append("&human_num_begin="+human_num_begin);
		}
		if (StringUtils.isNotBlank(human_num_end)) {
			entity.getMap().put("human_num_end", human_num_end);
			sb.append("&human_num_end="+human_num_end);
		}
		if (StringUtils.isNotBlank(area_size_begin)) {
			entity.getMap().put("area_size_begin", area_size_begin);
			sb.append("&area_size_begin="+area_size_begin);
		}
		if (StringUtils.isNotBlank(area_size_end)) {
			entity.getMap().put("area_size_end", area_size_end);
			sb.append("&area_size_end="+area_size_end);
		}
		entity.getMap().put("queryStr", sb.toString());
		
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
		
		entity.getMap().put("page", page);
		
		return entity;
	}
	
	/**
	 * 进入修改页面
	 * @author Liang,HouEn
	 * 2015-3-3
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String tp_index = (String) dynaBean.get("tp_index");
		String op_type = (String) dynaBean.get("op_type");
		String mod_id = (String) dynaBean.get("mod_id");
		
		StringBuffer sb = new StringBuffer("");
		sb.append("area_name="+java.net.URLDecoder.decode((String) dynaBean.get("area_name"), "utf-8"));
		sb.append("&province="+dynaBean.get("province"));
		sb.append("&city="+dynaBean.get("city"));
		sb.append("&country="+dynaBean.get("country"));
		sb.append("&town="+dynaBean.get("town"));
		sb.append("&p_index="+dynaBean.get("p_index"));
		sb.append("&t_type="+dynaBean.get("t_type"));
		sb.append("&t_status="+dynaBean.get("t_status"));
		sb.append("&dept_id="+dynaBean.get("dept_id"));
		sb.append("&modify_name="+java.net.URLDecoder.decode((String) dynaBean.get("modify_name"), "utf-8"));
		sb.append("&modify_date="+dynaBean.get("modify_date"));
		sb.append("&jd_in="+dynaBean.get("jd_in"));
		sb.append("&konka_in="+dynaBean.get("konka_in"));
		sb.append("&first_comp="+dynaBean.get("first_comp"));
		sb.append("&second_comp="+dynaBean.get("second_comp"));
		sb.append("&third_comp="+dynaBean.get("third_comp"));
		sb.append("&market_money_begin="+dynaBean.get("market_money_begin"));
		sb.append("&market_money_end="+dynaBean.get("market_money_end"));
		sb.append("&market_num_begin="+dynaBean.get("market_num_begin"));
		sb.append("&market_num_end="+dynaBean.get("market_num_end"));
		sb.append("&market_prop_begin="+dynaBean.get("market_prop_begin"));
		sb.append("&market_prop_end="+dynaBean.get("market_prop_end"));
		sb.append("&t_num_begin="+dynaBean.get("t_num_begin"));
		sb.append("&t_num_end="+dynaBean.get("t_num_end"));
		sb.append("&gdp_begin="+dynaBean.get("gdp_begin"));
		sb.append("&gdp_end="+dynaBean.get("gdp_end"));
		sb.append("&human_num_begin="+dynaBean.get("human_num_begin"));
		sb.append("&human_num_end="+dynaBean.get("human_num_end"));
		sb.append("&area_size_begin="+dynaBean.get("area_size_begin"));
		sb.append("&area_size_end="+dynaBean.get("area_size_end"));
		
		request.setAttribute("queryStr", sb.toString());
		
		if (!GenericValidator.isLong(tp_index) || !GenericValidator.isLong(tp_index)) {
			return new ActionForward("/admin/AreaFight/list.jsp");
		}

		AreaFightInfo entity = new AreaFightInfo();
		entity.setP_index(Long.parseLong(tp_index));
		entity = super.getFacade().getAreaFightInfoService().getAreaFightDetail(entity);
		
		//分公司
		Boolean role_id_ge_30 = false;
		PeRoleUser role_user = new PeRoleUser();
		role_user.setUser_id(super.getSessionUserInfo(request).getId());
	
		// update by zhou 2013/10/25
		// 规则判定:当一个人的角色已经是大权限的时候,就以最大的权限给他
		List<PeRoleUser> roleList = super.getFacade().getPeRoleUserService().getPeRoleUserList(role_user);
		if (roleList.size() > 0) {
			for (PeRoleUser _roleUser : roleList) {
				if (_roleUser.getRole_id() >= 30l) {
					role_id_ge_30 = true;// 如果已经确认是分公司角色,直接跳出
					break;
				} else {
					role_id_ge_30 = false;// 如果已经确认是总部角色,直接跳出
					break;
				}
			}
		}

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		if (role_id_ge_30) {
			KonkaDept a1 = getKonkaDeptForFgs(super.getSessionUserInfo(request).getDept_id());
			if (a1 != null) {
				konkaDept.setDept_id(a1.getDept_id());
			}

		}
		konkaDept.getMap().put("order_by_dept_name", true);
		List<KonkaDept> kList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		request.setAttribute("BranchList", kList);
		
		entity.setQueryString(super.serialize(request, "partner_id", "method"));
		super.copyProperties(form, entity);
		request.setAttribute("op_type", op_type);
		request.setAttribute("mod_id", mod_id);
		return mapping.findForward("input");
	}
	
	
	/**
	 * 保存修改
	 * @author Liang,HouEn
	 * 2015-3-4
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String queryStr = (String) dynaBean.get("queryStr");
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == ui) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		AreaFightInfo entity = new AreaFightInfo();
		super.copyProperties(entity, form);
		entity.setModify_user_id(ui.getId());
		entity.setModify_date(new Date());
		
		AreaFightInfo temp = new AreaFightInfo();
		temp.setP_index(entity.getP_index());
		Long count = super.getFacade().getAreaFightInfoService().getAreaFightInfoCount(temp);
		
		if(count==1){
			super.getFacade().getAreaFightInfoService().modifyAreaFightInfo(entity);
		}else{
			super.getFacade().getAreaFightInfoService().createAreaFightInfo(entity);
		}

		request.setAttribute("queryStr", queryStr);
		return new ActionForward("/admin/AreaFight/list.jsp");
	}
	
	
	public ActionForward queryDetailList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		
		AreaFightInfo entity = getEntiy(mapping, form, request);

		Long recordCount = super.getFacade().getAreaFightInfoService().getDetailCount(entity);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		pager.init(recordCount, 10, (String)entity.getMap().get("page"));
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<HashMap> entityList = null;
		if(recordCount>0){
			entityList = super.getFacade().getAreaFightInfoService().getAreaFightDetailList(entity);
		}
		
		//封装成JSON字符串
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("total", recordCount);
		if(entityList==null){
			String[] str = {};
			m.put("rows", str);
		}else{
			m.put("rows", entityList);
		}
		JSONArray jsonArray = JSONArray.fromObject(m);
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start+1, end+1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 返回查询列表页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward backToList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		//传递过来的查询条件
		StringBuffer sb = new StringBuffer("");
		if(null!=(String) dynaBean.get("area_name")){
			sb.append("&area_name="+java.net.URLDecoder.decode((String) dynaBean.get("area_name"), "utf-8"));
		}
		sb.append("&province="+dynaBean.get("province"));
		sb.append("&city="+dynaBean.get("city"));
		sb.append("&country="+dynaBean.get("country"));
		sb.append("&town="+dynaBean.get("town"));
		sb.append("&p_index="+dynaBean.get("p_index"));
		sb.append("&t_type="+dynaBean.get("t_type"));
		sb.append("&t_status="+dynaBean.get("t_status"));
		sb.append("&dept_id="+dynaBean.get("dept_id"));
		if(null!=(String) dynaBean.get("modify_name")){
			sb.append("&modify_name="+java.net.URLDecoder.decode((String) dynaBean.get("modify_name"), "utf-8"));
		}
		sb.append("&modify_date="+dynaBean.get("modify_date"));
		sb.append("&jd_in="+dynaBean.get("jd_in"));
		sb.append("&konka_in="+dynaBean.get("konka_in"));
		sb.append("&first_comp="+dynaBean.get("first_comp"));
		sb.append("&second_comp="+dynaBean.get("second_comp"));
		sb.append("&third_comp="+dynaBean.get("third_comp"));
		sb.append("&market_money_begin="+dynaBean.get("market_money_begin"));
		sb.append("&market_money_end="+dynaBean.get("market_money_end"));
		sb.append("&market_num_begin="+dynaBean.get("market_num_begin"));
		sb.append("&market_num_end="+dynaBean.get("market_num_end"));
		sb.append("&market_prop_begin="+dynaBean.get("market_prop_begin"));
		sb.append("&market_prop_end="+dynaBean.get("market_prop_end"));
		sb.append("&t_num_begin="+dynaBean.get("t_num_begin"));
		sb.append("&t_num_end="+dynaBean.get("t_num_end"));
		sb.append("&gdp_begin="+dynaBean.get("gdp_begin"));
		sb.append("&gdp_end="+dynaBean.get("gdp_end"));
		sb.append("&human_num_begin="+dynaBean.get("human_num_begin"));
		sb.append("&human_num_end="+dynaBean.get("human_num_end"));
		sb.append("&area_size_begin="+dynaBean.get("area_size_begin"));
		sb.append("&area_size_end="+dynaBean.get("area_size_end"));
		
		request.setAttribute("queryStr", sb.toString());
		request.setAttribute("mod_id", mod_id);
		return new ActionForward("/admin/AreaFight/list.jsp");
	}
}
