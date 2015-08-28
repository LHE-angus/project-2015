package com.ebiz.mmt.web.struts.manager.admin;

import java.io.PrintWriter;
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
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileSailData;
import com.ebiz.mmt.domain.MobileSelectItem;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.domain.VADefailsOfConsumer;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.StringHelper;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Li,DeYu
 * @version 2013-07-09
 */

public class VADefailsOfConsumerAction extends BaseAction {

	public String[] size_strs = { "24", "26", "28", "29", "32", "37", "39", "40", "42", "43", "46", "47", "48", "50",
			"55", "58", "60", "65", "84" };

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		VADefailsOfConsumer entity = new VADefailsOfConsumer();
		// super.copyProperties(entity, form);

		String report_name_like = (String) dynaBean.get("report_name_like");
		// 门店
		String store_name = (String) dynaBean.get("store_name");
		// 姓名
		String buyer_name = (String) dynaBean.get("buyer_name");
		// 电话
		String buyer_tel = (String) dynaBean.get("buyer_tel");
		// 产品型号
		String model_id = (String) dynaBean.get("model_id");
		// 产品尺寸
		//String md_size = (String) dynaBean.get("size_id");
		String date_begin = (String) dynaBean.get("date_begin");
		String date_end = (String) dynaBean.get("date_end");
		String dept_id = (String) dynaBean.get("dept_id");
		String r3_kh_name = (String) dynaBean.get("r3_kh_name");
		String excel_all = (String) dynaBean.get("excel_all");

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ne_10 = false; // 非系统管理员
		boolean role_id_eq_10 = false; // 非系统管理员
		// boolean role_id_eq_30 = false; // 分公司管理员
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() != 10L) {
				role_id_ne_10 = true;
			}
			if (peRoleUser.getRole_id() == 10L) {
				role_id_eq_10 = true;
			}

		}

		if (role_id_ne_10) {
			// 非系统管理员
			entity.getMap().put("dept_id_start", peProdUser.getDept_id());
		}
		if (role_id_eq_10) {
			// 系统管理员
			request.setAttribute("isFgsUser", "true");
		}

		if (StringUtils.isNotBlank(dept_id)) {
			// 分公司用户
			entity.getMap().put("dept_id_start", Long.valueOf(dept_id));
		}

		if (StringUtils.isNotBlank(report_name_like)) {
			entity.getMap().put("report_name_like", report_name_like);
		}
		if (StringUtils.isNotBlank(r3_kh_name)) {
			entity.getMap().put("r3_kh_name", r3_kh_name);
		}
		if (StringUtils.isNotBlank(store_name)) {
			entity.getMap().put("store_name", store_name);
		}
		if (StringUtils.isNotBlank(buyer_name)) {
			entity.getMap().put("buyer_name", buyer_name);
		}
		if (StringUtils.isNotBlank(buyer_tel)) {
			entity.getMap().put("buyer_tel", buyer_tel);
		}
		if (StringUtils.isNotBlank(model_id)) {
			entity.getMap().put("md_name", model_id);
		}
		/**
		if (StringUtils.isNotBlank(md_size)) {
			entity.setMd_size(md_size);
		}
        **/
		
		// 默认显示当前月份1号开始的时间区间
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String day_last = df.format(today);
		
		if (StringUtils.isNotBlank(date_begin)) {
			entity.getMap().put("date_begin", date_begin + " 00:00:00");
		}else{
			dynaBean.set("date_begin", theFirstDayOfCurrentMonth()); // 默认当前月份1号开始
			entity.getMap().put("date_begin", theFirstDayOfCurrentMonth() + " 00:00:00");
		}
		if (StringUtils.isNotBlank(date_end)) {
			entity.getMap().put("date_end", date_end + " 23:59:59");
		}else{
			dynaBean.set("date_end", day_last);
			entity.getMap().put("date_end", day_last + " 23:59:59");
		}
		

		Long recordCount = getFacade().getVADefailsOfConsumerService().getVADefailsOfConsumerCount(entity);
		pager.init(recordCount, 15, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<VADefailsOfConsumer> entityList = getFacade().getVADefailsOfConsumerService()
				.getVADefailsOfConsumerPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			entity.getRow().setCount(recordCount.intValue());
			List<HashMap> entityList1 = getFacade().getVADefailsOfConsumerService()
			.getVADefailsOfConsumerMapList(entity);
			dynaBean.set("excel_all", excel_all);
			request.setAttribute("entityList", entityList1);
			return mapping.findForward("excel");
		}

		// 分公司,下拉选项
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		konkaDept.setDept_id(peProdUser.getDept_id());// ++2013-04-18
		List<KonkaDept> baseDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		request.setAttribute("baseDeptList", baseDeptList);

		// 部门列表
		KonkaDept kdept = new KonkaDept();
		if (role_id_ne_10) {
			kdept.getMap().put("dept_id", peProdUser.getDept_id());

		}

		List<KonkaDept> peDeptList = super.getFacade().getKonkaDeptService()
				.getKonkaDeptListWithTreeNameForProdUser(kdept);
		request.setAttribute("peDeptList", peDeptList);

		// 类别 下拉选项
		List<BasePdClazz> basePdClazzList = super.getFacade().getRetailMsBaseService()
				.getKonkaBasePdClazzListByClsIds();
		request.setAttribute("basePdClazzList", basePdClazzList);

		// 尺寸sizeList
		List<MobileSelectItem> sizeList = new ArrayList<MobileSelectItem>();
		for (String str : size_strs) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(str);
			t.setName(str);
			sizeList.add(t);
		}
		request.setAttribute("sizeList", sizeList);
		return mapping.findForward("list");
	}

	public ActionForward exportExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		DynaBean dynaBean = (DynaBean) form;
		String report_name_like = (String) dynaBean.get("report_name_like");
		// 门店
		String store_name = (String) dynaBean.get("store_name");
		// 姓名
		String buyer_name = (String) dynaBean.get("buyer_name");
		// 电话
		String buyer_tel = (String) dynaBean.get("buyer_tel");
		// 产品型号
		String model_id = (String) dynaBean.get("model_id");

		String date_begin = (String) dynaBean.get("date_begin");
		String date_end = (String) dynaBean.get("date_end");
		String dept_id = (String) dynaBean.get("dept_id");
		String r3_kh_name = (String) dynaBean.get("r3_kh_name");

		VADefailsOfConsumer entity = new VADefailsOfConsumer();
		// super.copyProperties(entity, form);

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ne_10 = false; // 非系统管理员
		boolean role_id_eq_10 = false; // 非系统管理员
		// boolean role_id_eq_30 = false; // 分公司管理员
		// boolean role_id_eq_60 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() != 10L) {
				role_id_ne_10 = true;
			}
			if (peRoleUser.getRole_id() == 10L) {
				role_id_eq_10 = true;
			}
		}

		if (role_id_ne_10) {
			// 非系统管理员
			entity.getMap().put("dept_id_start", peProdUser.getDept_id());
		}
		if (role_id_eq_10) {
			// 系统管理员
			request.setAttribute("isFgsUser", "true");
		}

		if (StringUtils.isNotBlank(dept_id)) {
			// 分公司用户
			entity.getMap().put("dept_id_start", Long.valueOf(dept_id));
		}

		if (StringUtils.isNotBlank(report_name_like)) {
			entity.getMap().put("report_name_like", report_name_like);
		}
		if (StringUtils.isNotBlank(r3_kh_name)) {
			entity.getMap().put("r3_kh_name", r3_kh_name);
		}
		if (StringUtils.isNotBlank(store_name)) {
			entity.getMap().put("store_name", store_name);
		}
		if (StringUtils.isNotBlank(buyer_name)) {
			entity.getMap().put("buyer_name", buyer_name);
		}
		if (StringUtils.isNotBlank(buyer_tel)) {
			entity.getMap().put("buyer_tel", buyer_tel);
		}
		if (StringUtils.isNotBlank(model_id)) {
			entity.getMap().put("md_name", model_id);
		}

		if (StringUtils.isNotBlank(date_begin)) {
			entity.getMap().put("date_begin", date_begin + " 00:00:00");
		}
		if (StringUtils.isNotBlank(date_end)) {
			entity.getMap().put("date_end", date_end + " 23:59:59");
		}

		List<HashMap> entityList = getFacade().getVADefailsOfConsumerService()
		.getVADefailsOfConsumerMapList(entity);

		request.setAttribute("entityList", entityList);

		// 分公司,下拉选项
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		konkaDept.setDept_id(peProdUser.getDept_id());// ++2013-04-18
		List<KonkaDept> baseDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		request.setAttribute("baseDeptList", baseDeptList);

		// 部门列表
		KonkaDept kdept = new KonkaDept();
		if (role_id_ne_10) {
			kdept.getMap().put("dept_id", peProdUser.getDept_id());
		}

		List<KonkaDept> peDeptList = super.getFacade().getKonkaDeptService()
				.getKonkaDeptListWithTreeNameForProdUser(kdept);
		request.setAttribute("peDeptList", peDeptList);

		// 类别 下拉选项
		List<BasePdClazz> basePdClazzList = super.getFacade().getRetailMsBaseService()
				.getKonkaBasePdClazzListByClsIds();
		request.setAttribute("basePdClazzList", basePdClazzList);

		// 尺寸sizeList
		List<MobileSelectItem> sizeList = new ArrayList<MobileSelectItem>();
		for (String str : size_strs) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(str);
			t.setName(str);
			sizeList.add(t);
		}
		request.setAttribute("sizeList", sizeList);
		request.setAttribute("date", DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		return mapping.findForward("excel");
	}

	/*
	 * 办事处
	 */
	public ActionForward getDept(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// subcomp_id
		DynaBean dynaBean = (DynaBean) form;
		String subcomp_id = (String) dynaBean.get("subcomp_id");
		if (StringUtils.isNotEmpty(subcomp_id)) {
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setPar_id(new Long(subcomp_id));

			List<KonkaDept> baseDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);

			List<String> dataList = new ArrayList<String>();
			for (KonkaDept _t : baseDeptList) {
				dataList.add(StringUtils.join(new String[] { "[\"", _t.getDept_name(), "\",\"",
						String.valueOf(_t.getDept_id()), "\"]" }));
			}
			super.renderJson(response, StringUtils.join(new String[] { "[", StringUtils.join(dataList, ","), "]" }));
		}
		return null;
	}

	/*
	 * 产品型号
	 */
	public ActionForward getModel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// subcomp_id
		DynaBean dynaBean = (DynaBean) form;
		String md_size = (String) dynaBean.get("md_size");
		if (StringUtils.isNotEmpty(md_size)) {

			// String[] cls_ids = { category_id };
			// List<PePdModel> pePdModelList = super.getFacade()
			// .getRetailMsBaseService().getKonkaPePdModelListByClsIds(
			// cls_ids);
			//
			List<String> dataList = new ArrayList<String>();
			PePdModel t = new PePdModel();
			t.getMap().put("led_name_like", md_size);
			List<PePdModel> tList = new ArrayList<PePdModel>();
			tList = super.getFacade().getPePdModelService().getPePdModelPaginatedList(t);
			for (PePdModel _t : tList) {
				dataList.add(StringUtils.join(new String[] { "[\"", _t.getMd_name(), "\",\"",
						String.valueOf(_t.getPd_id()), "\"]" }));
			}
			super.renderJson(response, StringUtils.join(new String[] { "[", StringUtils.join(dataList, ","), "]" }));
		}
		return null;
	}
	
	/**
	 * 获得当前月份的首日
	 * @author Angus
	 * @date 2014-7-10
	 * @return
	 */
	private static String theFirstDayOfCurrentMonth() {
		Calendar c = Calendar.getInstance();

		String y = String.valueOf(c.get(Calendar.YEAR));
		String m = "";
		String d = "";// the first day of a month
		if (c.get(Calendar.MONTH) + 1 >= 10) {
			m = String.valueOf(c.get(Calendar.MONTH) + 1);
		} else {
			m = "0" + String.valueOf(c.get(Calendar.MONTH) + 1);
		}
		d = "0" + c.getActualMinimum(Calendar.DAY_OF_MONTH);
		String start_day = y + "-" + m + "-" + d;
		return start_day;
	}

	
	
	/*************************更改UI后新方法*****************************/
	/**
	 * 初始化
	 * @author Angus
	 * @date 2014-08-11
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// 默认显示当前1月的时间区间
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String day_first = theFirstDayOfCurrentMonth();
		String day_last = df.format(today);
		
        Map<String, Object> allmap = new HashMap<String, Object>();
        allmap.put("date_begin", day_first);
        allmap.put("date_end", day_last);
        
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
//		PeProdUser ui = new PeProdUser();
//		ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
//		int max_dlevel = super.getMaxDLevel(ui.getId());
//		if(max_dlevel==9){
//			allmap.put("dept_id", "");
//		}else{
//			allmap.put("dept_id", ui.getDept_id());
//		}
		
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
	 * 获取分公司/办事处下拉列表
	 * @author Angus
	 * @date 2014-8-11
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getDeptList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//转换为json数据
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		boolean role_id_ne_10 = false; 
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() != 10L) {
				role_id_ne_10 = true;
			}
		}
		// 部门列表
		KonkaDept kdept = new KonkaDept();
		if (role_id_ne_10) {
			kdept.getMap().put("dept_id", peProdUser.getDept_id());
		}
		
		List<HashMap> peDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptTreeNameForProdUser(kdept);
		List<HashMap> list = new ArrayList<HashMap>();
		
		HashMap map = new HashMap();
		map.put("DEPT_ID", "");
		map.put("TREE_NAME", "-分公司/办事处-");
		list.add(map);
		list.addAll(peDeptList);
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString());
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 产品品类
	 * @author Angus
	 * @date 2014-8-11
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getProType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<BasePdClazz> list = new ArrayList<BasePdClazz>();
		List<BasePdClazz> basePdClazzList = super.getFacade().getRetailMsBaseService()
											.getKonkaBasePdClazzListByClsIds();
		BasePdClazz map = new BasePdClazz();
		map.setCls_id(null);
		map.setCls_name("-产品品类-");
		list.add(map);
		list.addAll(basePdClazzList);
		
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString());
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 尺寸列表
	 * @author Angus
	 * @date 2014-8-11
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getProSize(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<HashMap> list = new ArrayList<HashMap>();
		
		HashMap map = new HashMap();
		map.put("value", "");
		map.put("name", "-选择尺寸-");
		list.add(map);
		for (String str : size_strs) {
			HashMap t = new HashMap();
			t.put("value", str);
			t.put("name", str);
			list.add(t);
		}
		
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString());
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 联动查询型号
	 * @author Angus
	 * @date 2014-8-13
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getProModel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String pro_size = request.getParameter("pro_size");
		
		List<HashMap> list = new ArrayList<HashMap>();
		HashMap map = new HashMap();
		map.put("PD_ID", "");
		map.put("MD_NAME","-产品型号-");
		list.add(map);
		if (StringUtils.isNotEmpty(pro_size)) {
			PePdModel t = new PePdModel();
			t.getMap().put("led_name_like", pro_size);
			t.getMap().put("OrderByMd", true);
			List<HashMap> tList =  super.getFacade().getPePdModelService().getPePdModelMapList(t);
			list.addAll(tList);
		}
		
		
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString());
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 获取参数
	 * @author Angus
	 * @date 2014-8-11
	 * @param mapping
	 * @param form
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public VADefailsOfConsumer  getEntiy(ActionMapping mapping, ActionForm form, HttpServletRequest request) throws Exception{
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		
		String date_begin = request.getParameter("date_begin");
		String date_end = request.getParameter("date_end");
		String dept_id = request.getParameter("dept_id");
		String store_name = request.getParameter("store_name");
		String pro_type = request.getParameter("pro_type");
		String pro_size = request.getParameter("pro_size");
		String pro_modle = request.getParameter("pro_modle");
		String xf_name = request.getParameter("xf_name");
		String telephone = request.getParameter("telephone");
		String cust_name = request.getParameter("cust_name");
		String add_name = request.getParameter("add_name");
		String is_valid = request.getParameter("is_valid");
		String page = request.getParameter("page");
		
        PeProdUser ui = new PeProdUser();
		ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		VADefailsOfConsumer entity = new VADefailsOfConsumer();

		//销售时间
		if (StringUtils.isNotBlank(date_begin)) {
			entity.getMap().put("date_begin", date_begin+" 00:00:00");
		}
		if (StringUtils.isNotBlank(date_end)) {
			entity.getMap().put("date_end", date_end+" 23:59:59");
		}
		//分公司ID
		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("dept_id_start", Long.valueOf(dept_id));
		}
		//门店名称
		if (StringUtils.isNotBlank(store_name)) {
			entity.getMap().put("store_name", store_name);
		}
		//型号
		if (StringUtils.isNotBlank(pro_modle)) {
			entity.getMap().put("md_name", pro_modle);
		}
		//消费者姓名
		if (StringUtils.isNotBlank(xf_name)) {
			entity.getMap().put("buyer_name", xf_name);
		}
		//联系电话
		if (StringUtils.isNotBlank(telephone)) {
			entity.getMap().put("buyer_tel", telephone);
		}
		//客户名称
		if (StringUtils.isNotBlank(cust_name)) {
			entity.getMap().put("r3_kh_name", cust_name);
		}
		//上报人员
		if (StringUtils.isNotBlank(add_name)) {
			entity.getMap().put("report_name_like", add_name);
		}
		
		entity.getMap().put("page", page);
		return entity;
	}
	
	/**
	 * 列表查询
	 * @author Angus
	 * @date 2014-8-11
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		VADefailsOfConsumer entity = getEntiy(mapping, form, request);
		
		Long recordCount = getFacade().getVADefailsOfConsumerService().getVADefailsOfConsumerCount(entity);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		pager.init(recordCount, pager.getPageSize(), (String)entity.getMap().get("page"));
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<HashMap> entityList = getFacade().getVADefailsOfConsumerService()
			.getVADefailsOfConsumerMapList(entity);
		
		
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
	 * 导出数据
	 * @author Liang,HouEn
	 * @date 2014-8-13
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
		response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("门店零售排名")
		        + ".xls");
		VADefailsOfConsumer entity = getEntiy(mapping, form, request);
		Long recordCount = getFacade().getVADefailsOfConsumerService().getVADefailsOfConsumerCount(entity);
		entity.getRow().setFirst(0);
		entity.getRow().setCount(recordCount.intValue());
		List<HashMap> entityList = getFacade().getVADefailsOfConsumerService()
		.getVADefailsOfConsumerMapList(entity);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("excel");
	}
}
