package com.ebiz.mmt.web.struts.manager.admin;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.ebiz.mmt.domain.BasePdType;
import com.ebiz.mmt.domain.EntpShop;
import com.ebiz.mmt.domain.JBasePartner;
import com.ebiz.mmt.domain.KonkaBranchCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaShopDevelop;
import com.ebiz.mmt.domain.LogOfJob;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.StringHelper;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * 代理商网点 网点列表
 * 
 * @author Wang Hao
 */
public class LogOfJobAction extends BaseAction {
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
		
		//用户角色信息
		PeRoleUser roleUser = new PeRoleUser();
		roleUser.setUser_id(ui.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(roleUser);
		for(PeRoleUser t : roleUserList){
			if(t.getRole_id() == 30){//分公司管理员
				allmap.put("fgs_m", true);
				break;
			}
		}
		
		SimpleDateFormat mm_fmt = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd");
        
		allmap.put("syn_date_begin", mm_fmt.format(new Date()) + "-01");
		allmap.put("syn_date_end", fmt1.format(new Date()));
		
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
	
	public ActionForward queryList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		LogOfJob entity = getEntiy(mapping, form, request);

		Long recordCount = super.getFacade().getLogOfJobService().getLogOfJobCount(entity);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		pager.init(recordCount, 10, (String)entity.getMap().get("page"));
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<HashMap> entityList = null;
		if(recordCount>0){
			entityList = super.getFacade().getLogOfJobService().getLogOfJobListForMap(entity);
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
	 * 封装数据
	 * @author Angus
	 * @date 2014-7-24
	 * @param mapping
	 * @param form
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public LogOfJob  getEntiy(ActionMapping mapping, ActionForm form, HttpServletRequest request) throws Exception{
		
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		String page = request.getParameter("page");
		
		//开始同步时间段
		String syn_date_begin = request.getParameter("syn_date_begin");
		//结束同步时间段
		String syn_date_end = request.getParameter("syn_date_end");
		//操作人
		String ope_name = request.getParameter("ope_name");
		//同步类型
		String syn_type = request.getParameter("syn_type");
		
		LogOfJob entity = new LogOfJob();

		if (StringUtils.isNotBlank(syn_date_begin)) {
			entity.getMap().put("syn_date_begin", syn_date_begin);
		}
		if (StringUtils.isNotBlank(syn_date_end)) {
			entity.getMap().put("syn_date_end", syn_date_end);
		}
		if (StringUtils.isNotBlank(ope_name)) {
			entity.setOper_real_name(ope_name);
		}
		if (StringUtils.isNotBlank(syn_type)&&!"-请选择-".equals(syn_type)) {
			entity.setJob_type(syn_type);
		}
		
		entity.getMap().put("page", page);
		return entity;
	}
	
	/**
	 * 查询同步类型
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSynTypeList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<LogOfJob> entityList = super.getFacade().getLogOfJobService().getSynTypeList();
		List list = new ArrayList();
		
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("JOB_TYPE", "-请选择-");
		list.add(m);
		list.addAll(entityList);
		
		//封装成JSON数据
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString());
		out.flush();
		out.close();
		return null;
	}
}
