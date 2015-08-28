package com.ebiz.mmt.web.struts.manager.admin;

import com.ebiz.mmt.domain.*;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.StringHelper;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 定时任务列表
 * 
 * @author Wang Hao
 */
public class JobsListAction extends BaseAction {
	/**
	 * 初始化
	 * @author Liang Houen
	 * @date 2015-08-18
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
		SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		KonkaJobs entity = new KonkaJobs();
		String page = request.getParameter("page");

		Long recordCount = super.getFacade().getKonkaJobsService().getKonkaJobsCount(entity);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		pager.init(recordCount, 15, page);
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaJobs> entityList = super.getFacade().getKonkaJobsService().getKonkaJobsPaginatedList(entity);
		for(KonkaJobs job : entityList){
			if(null!=job.getUpdate_time()){
				job.getMap().put("up_time",sformat.format(job.getUpdate_time()));
			}
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
	 * 停用/启用
	 * @author Angus
	 * @date 2014-9-2
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stopOrstart(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		String func_name = (String) dynaBean.get("func_name");
		String flag = (String) dynaBean.get("flag");
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		
		KonkaJobs entity = new KonkaJobs();
		entity.setFun_name(func_name);
		entity.setStatus(Integer.valueOf(flag));
		entity.setUpdated_id(ui.getId());
		entity.setUpdated_name(ui.getReal_name());
		entity.setUpdate_time(new Date());
		super.getFacade().getKonkaJobsService().modifyKonkaJobs(entity);

		//封装成JSON字符串
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.flush();
		out.close();
		return null;
	}

}
