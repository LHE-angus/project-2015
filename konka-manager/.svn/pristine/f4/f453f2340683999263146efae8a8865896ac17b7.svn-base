package com.ebiz.mmt.web.struts.webservice;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.BaseVisitType;
import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileCustVisit;
import com.ebiz.mmt.domain.KonkaMobileCustVisitDetail;
import com.ebiz.mmt.domain.KonkaMobileCustVisitType;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3ShopDev;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class KonkaR3ShopDevAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String mod_id = (String) dynaBean.get("mod_id");// 拜访开始时间
		String begin_date = (String) dynaBean.get("begin_date");// 拜访开始时间
		String end_date = (String) dynaBean.get("end_date");// 拜访结束时间
        String cust_id=(String)dynaBean.get("cust_id");// 拜访结束时间
		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		// 类型
		String type = (String) dynaBean.get("type");
		
		String cust_name_like = (String) dynaBean.get("cust_name_like");// 客户名称
		String is_del = (String) dynaBean.get("is_del");// 是否删除
		String state = (String) dynaBean.get("state");//开拓意向
		String dev_state = (String) dynaBean.get("dev_state");//开拓状态
		String link_man_name_like = (String) dynaBean.get("link_man_name_like");//联系人
		String report_nae_like = (String) dynaBean.get("report_nae_like");// 上报人
		String subcomp_id = (String) dynaBean.get("subcomp_id");//分公司id
		//版本号,android_version,ios_version
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		// 用户名或者密码不存在， 资讯信息类别为公开
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);

		if (null == userInfo) {
			super.renderHtml(response, "登录超时");
			return null;
		}
		
		KonkaR3ShopDev konkaR3ShopDev = new KonkaR3ShopDev();
		if(StringUtils.isNotBlank(cust_id)&&GenericValidator.isLong(cust_id)){
			konkaR3ShopDev.setCust_id(Long.valueOf(cust_id));	
		}
		
		
		if(StringUtils.isNotBlank(is_del)&&GenericValidator.isInt(is_del)){
			konkaR3ShopDev.setIs_del(Integer.parseInt(is_del));
		}else{
		    konkaR3ShopDev.setIs_del(0);
		}
		if(StringUtils.isNotBlank(begin_date)){
			konkaR3ShopDev.getMap().put("begin_time", begin_date+" 00:00:00");
		}
		if(StringUtils.isNotBlank(end_date)){
			konkaR3ShopDev.getMap().put("end_time", end_date+" 23:59:59");
		}
		if(StringUtils.isNotBlank(cust_name_like)){
			konkaR3ShopDev.getMap().put("cust_name_like", new String(cust_name_like.getBytes("iso-8859-1"), "UTF-8"));
		}
		if(StringUtils.isNotBlank(state)){
			konkaR3ShopDev.setState(Integer.parseInt(state));
		}
		if (StringUtils.isNotBlank(dev_state)) {
			konkaR3ShopDev.setDev_state(Integer.valueOf(dev_state));
		}
		if(StringUtils.isNotBlank(link_man_name_like)){
			konkaR3ShopDev.getMap().put("link_man_name_like", new String(link_man_name_like.getBytes("iso-8859-1"), "UTF-8"));
		}
		if(StringUtils.isNotBlank(subcomp_id)&&GenericValidator.isLong(subcomp_id)){
			konkaR3ShopDev.setSubcomp_id(Long.valueOf(subcomp_id));
		}
		/*if (StringUtils.isNotBlank(report_nae_like)) {
			konkaR3ShopDev.getMap().put("report_nae_like", report_nae_like);
		}*/
		if (null!=userInfo.getDept_id()) {
			konkaR3ShopDev.getMap().put("deptId", userInfo.getDept_id());
		}
		if (StringUtils.isNotBlank(report_nae_like)) {
			konkaR3ShopDev.getMap().put("report_nae_like", new String(report_nae_like.getBytes("iso-8859-1"), "UTF-8"));
		}
		List<KonkaR3ShopDev> entityList=new ArrayList<KonkaR3ShopDev>();
		//Long recordCount=super.getFacade().getKonkaR3ShopDevService().getKonkaR3ShopDevCount(konkaR3ShopDev);
		Long recordCount=super.getFacade().getKonkaR3ShopDevService().selectKonkaR3ShopDevLBCount(konkaR3ShopDev);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		konkaR3ShopDev.getRow().setCount(pager.getRowCount());
		konkaR3ShopDev.getRow().setFirst(pager.getFirstRow());
		//entityList=super.getFacade().getKonkaR3ShopDevService().getKonkaR3ShopDevPaginatedList(konkaR3ShopDev);
		entityList=super.getFacade().getKonkaR3ShopDevService().getKonkaR3ShopDevLBPaginatedList(konkaR3ShopDev);
		List<HashMap> list =new ArrayList<HashMap>();
		HashMap map= new HashMap();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if(null!=entityList){
			request.setAttribute("entityList", entityList);
				for(KonkaR3ShopDev kkrs:entityList){	
					map= new HashMap();
					map.put("cust_id",kkrs.getCust_id()==null?"":kkrs.getCust_id());
					map.put("link_man_name",kkrs.getLink_man_name()==null?"":kkrs.getLink_man_name());
					map.put("link_man_tel",kkrs.getLink_man_tel()==null?"":kkrs.getLink_man_tel());
					map.put("link_man_mobile",kkrs.getLink_man_mobile()==null?"":kkrs.getLink_man_mobile());
					map.put("link_man_post",kkrs.getLink_man_post()==null?"":kkrs.getLink_man_post());
					map.put("host_name",kkrs.getHost_name()==null?"":kkrs.getHost_name());
					map.put("host_tel",kkrs.getHost_tel()==null?"":kkrs.getHost_tel());
					map.put("cust_name",kkrs.getCust_name()==null?"":kkrs.getCust_name());
					if(null!=kkrs.getAdd_date()){
						map.put("add_date",df.format(kkrs.getAdd_date()));
					}
					map.put("entp_type",kkrs.getEntp_type()==null?"":kkrs.getEntp_type());
					map.put("entp_reg_money",kkrs.getEntp_reg_money()==null?"":kkrs.getEntp_reg_money());
					map.put("entp_scale",kkrs.getEntp_scale()==null?"":kkrs.getEntp_scale());
					map.put("total_area",kkrs.getTotal_area()==null?"":kkrs.getTotal_area());
					map.put("total_sale",kkrs.getTotal_sale()==null?"":kkrs.getTotal_sale());
					map.put("entp_man_count",kkrs.getEntp_man_count()==null?"":kkrs.getEntp_man_count());
					map.put("entp_tel",kkrs.getEntp_tel()==null?"":kkrs.getEntp_tel());
					map.put("entp_birthday",kkrs.getEntp_birthday()==null?"":kkrs.getEntp_birthday());
					map.put("entp_fax",kkrs.getEntp_fax()==null?"":kkrs.getEntp_fax());
					map.put("entp_post",kkrs.getEntp_post()==null?"":kkrs.getEntp_post());
					map.put("entp_p_index",kkrs.getEntp_p_index()==null?"":kkrs.getEntp_p_index());
					map.put("entp_p_level",kkrs.getEntp_p_level()==null?"":kkrs.getEntp_p_level());
					map.put("entp_addr",kkrs.getEntp_addr()==null?"":kkrs.getEntp_addr());
					map.put("entp_www",kkrs.getEntp_www()==null?"":kkrs.getEntp_www());
					map.put("entp_main_pds",kkrs.getEntp_main_pds()==null?"":kkrs.getEntp_main_pds());
					map.put("subcomp_id",kkrs.getSubcomp_id()==null?"":kkrs.getSubcomp_id());
					map.put("ywy_user_id",kkrs.getYwy_user_id()==null?"":kkrs.getYwy_user_id());
					map.put("is_del",kkrs.getIs_del()==null?"":kkrs.getIs_del());
					map.put("is_match",kkrs.getIs_match()==null?"":kkrs.getIs_match());
					map.put("b_lng",kkrs.getB_lng()==null?"":kkrs.getB_lng());
					map.put("b_lat",kkrs.getB_lat()==null?"":kkrs.getB_lat());
					map.put("link_r3_code",kkrs.getLink_r3_code()==null?"":kkrs.getLink_r3_code());
					map.put("dev_state",kkrs.getDev_state()==null?"":kkrs.getDev_state());
					map.put("state",kkrs.getState()==null?"":kkrs.getState());
					if(null!=kkrs.getBegin_date()){
					map.put("begin_date",df.format(kkrs.getBegin_date()));
					}
					if(null!=kkrs.getEnd_date()){
					map.put("end_date",df.format(kkrs.getEnd_date()));
					}
					map.put("memo",kkrs.getMemo()==null?"":kkrs.getMemo());
					map.put("dept_name", kkrs.getMap().get("dept_name")==null?"":kkrs.getMap().get("dept_name"));
					list.add(map);
				}
			
		}
		
		logger.info(entityList.toString());
		super.renderJson(response,JSON.toJSONString(list));
		return null;
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Map<String, Object> allmap = new HashMap<String, Object>();
		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		// 类型
		String type = (String) dynaBean.get("type");
		
		// 时间
		String start_date = (String) dynaBean.get("start_date");
		
		//版本号,android_version,ios_version
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		// 用户名或者密码不存在， 资讯信息类别为公开
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);


		if (null == userInfo) {
			super.renderHtml(response, "登录超时");
			return null;
		}
		// 拿到公司类型的下拉
		KonkaCategory konkaCategory = new KonkaCategory();
		/*konkaCategory.setC_type(12);
		List<KonkaCategory> categoryList = super.getFacade().getKonkaCategoryService().getKonkaCategoryList(
				konkaCategory);
		allmap.put("categoryList", categoryList);
		
		konkaCategory = new KonkaCategory();*/
		konkaCategory.setC_type(13);
		konkaCategory.setIs_del(0);
		allmap.put("entpScaleList", super.getFacade().getKonkaCategoryService().getKonkaCategoryList(konkaCategory));
		// 拿分公司
	    //List<KonkaDept> deptList = super.getDeptInfoListWithOutLimit(mapping, form, request, response);
		//allmap.put("deptList", deptList);

		allmap.put("today", DateFormatUtils.format(new Date(), "yyyy年MM月dd日"));

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
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		// 类型
		String type = (String) dynaBean.get("type");
		
		//版本号,android_version,ios_version
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		// 用户名或者密码不存在， 资讯信息类别为公开
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);

		if (null == userInfo) {
			super.renderHtml(response, "登录超时");
			return null;
		}
		String cust_id = (String) dynaBean.get("cust_id");// 记录的主键
		String link_r3_code = (String) dynaBean.get("link_r3_code");// 记录的主键
		String cust_name = (String) dynaBean.get("cust_name");// 记录的主键
        String b_lng=(String) dynaBean.get("b_lng");// 地理位置-经度
        String b_lat=(String) dynaBean.get("b_lat");// 地理位置-纬度
        
        String[] link_man_names = (String[]) request.getParameterValues("link_man_name");// 拜访类型
        String[] link_man_tels = (String[]) request.getParameterValues("link_man_tel");// 拜访类型
        String link_man_nameStr="";
        String link_man_telsStr="";
        if(null==link_man_names||null==link_man_tels||link_man_names.length==0||link_man_tels.length==0){
        	super.renderJson(response, JSON.toJSONString("联系人 和电话不能为空"));
			return null;
        }
        if(link_man_names.length!=link_man_tels.length){
        	super.renderJson(response, JSON.toJSONString("联系人 和电话信息不匹配"));
			return null;
        } 
        for (int i = 0; i < link_man_names.length; i++) {
        	 link_man_nameStr+=link_man_names[i]+",";
        	 link_man_telsStr+=link_man_tels[i]+",";
		}
        
		KonkaR3ShopDev konkaR3ShopDev = new KonkaR3ShopDev();
		
		konkaR3ShopDev.setLink_man_name(link_man_nameStr.substring(0,link_man_nameStr.length()-1));
		konkaR3ShopDev.setLink_man_tel(link_man_telsStr.substring(0,link_man_telsStr.length()-1));
		
		super.copyProperties(konkaR3ShopDev, form);
		if (StringUtils.isBlank(cust_id)) {// 如果是添加，给时间
			if (null!=userInfo&&null!=userInfo.getId()) {
				konkaR3ShopDev.setAdd_user_id(userInfo.getId());
			}
			konkaR3ShopDev.setAdd_date(new Date());
			konkaR3ShopDev.setIs_del(0);// 是否删除
			if (null != userInfo.getId()) {
				konkaR3ShopDev.setYwy_user_id(userInfo.getId());
			}
			if(StringUtils.isNotBlank(b_lng)){// 地理位置-经度
				konkaR3ShopDev.setB_lng(b_lng);
			}
			if(StringUtils.isNotBlank(b_lat)){// 地理位置-纬度
				konkaR3ShopDev.setB_lng(b_lat);
			}
		}
		if (StringUtils.isNotBlank(link_r3_code)) {
			konkaR3ShopDev.setIs_match(1);// 如果选择了关联的r3编码，那么就说明是完成了匹配
		} else {
			konkaR3ShopDev.setIs_match(0);// 如果没选择关联的r3编码，那么就说明是完成了匹配
		}
		if (StringUtils.isNotBlank(cust_name)) {
			if ("GET".equals(request.getMethod())) {
				konkaR3ShopDev.setCust_name(new String(cust_name
						.getBytes("iso-8859-1"), "UTF-8"));
			}else {
				konkaR3ShopDev.setCust_name(cust_name);
			}
		}else {
			super.renderJson(response, JSON.toJSONString("客户名称不能为空！"));
			return null;
		}
		// 根据当前用户找到分公司
		KonkaDept konkaDept = new KonkaDept();
		
		if (null!=userInfo&&null!=userInfo.getDept_id()) {
			konkaDept.setDept_id(userInfo.getDept_id());
			konkaDept.getMap().put("dept_type_eq", 3);
			konkaDept = super.getFacade().getKonkaDeptService()
					.getKonkaDeptSuperiorByDeptId(konkaDept);
		}
		if (null != konkaDept && null != konkaDept.getDept_id()) {
			konkaR3ShopDev.setSubcomp_id(konkaDept.getDept_id());// 分公司id
		}

		Long id=0l;
		if (StringUtils.isBlank(cust_id)) {//添加
			id=	super.getFacade().getKonkaR3ShopDevService().createKonkaR3ShopDev(konkaR3ShopDev);
		}else{//修改
			super.getFacade().getKonkaR3ShopDevService().modifyKonkaR3ShopDev(konkaR3ShopDev);
			id=Long.valueOf(cust_id);
		}
		super.renderTextJsonOrJsonp(request, response, "success:"+id);
		
		return null;
	}

	//ajax 获取企业类型
	public ActionForward ajaxForComType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 拿到公司类型的下拉
		KonkaCategory konkaCategory = new KonkaCategory();
		konkaCategory.setC_type(12);
		List<KonkaCategory> categoryList = super.getFacade().getKonkaCategoryService().getKonkaCategoryList(
				konkaCategory);
		List<HashMap> list =new ArrayList<HashMap>();
		HashMap map= new HashMap();
		if(null!=categoryList){
			for(KonkaCategory ba:categoryList){	
				map= new HashMap();
				map.put("c_index",ba.getC_index()==null?"":ba.getC_index());
				map.put("c_name",ba.getC_name()==null?"":ba.getC_name());
				list.add(map);
			}
		}
		logger.info(categoryList.toString());
		super.renderJson(response,JSON.toJSONString(list));
		return null;
	}
}
