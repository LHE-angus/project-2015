package com.ebiz.mmt.web.struts.manager.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaPhotoUploadType;
import com.ebiz.mmt.domain.KonkaPhotoUploadTypeDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3ShopLink;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ibm.db2.jcc.b.el;

/**
 * @author OuYang,BaiYang
 * @version 2014-1-23
 */

public class KonkaPhotoUploadTypeAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		 String begindate = "";
			String enddate = "";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
		Calendar calendarbegin = Calendar.getInstance();
		calendarbegin.set(Calendar.DAY_OF_MONTH, calendarbegin.getActualMinimum(Calendar.DAY_OF_MONTH));
		begindate = df.format(calendarbegin.getTime());
		//dynaBean.set("add_date_s", begindate);
		Calendar calendarend = Calendar.getInstance();
		enddate = df.format(calendarend.getTime());
		//dynaBean.set("add_date_e", enddate);
		dynaBean.set("_state", "0");
		return this.list(mapping, form, request, response);
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String)dynaBean.get("mod_id");
		Pager pager = (Pager) dynaBean.get("pager");
		String type_title_like = (String)dynaBean.get("type_title_like");
		String add_date_s = (String) dynaBean.get("add_date_s");
		String add_date_e = (String) dynaBean.get("add_date_e");
		String dept_id = (String) dynaBean.get("dept_id");
		String state = (String) dynaBean.get("_state");
		String isfirst = (String) dynaBean.get("isfirst");// 是否第一次
		KonkaDept kdDept = new KonkaDept();
		
		//判断session是否超时
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == ui) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		KonkaPhotoUploadType entity = new KonkaPhotoUploadType();
		if (StringUtils.isNotBlank(dept_id)) {
			//entity.getMap().put("dept_id", dept_id);
			kdDept.setDept_id(Long.parseLong(dept_id));
			kdDept = super.getFacade().getKonkaDeptService().getKonkaDept(kdDept);
			entity.getMap().put("dept_name", kdDept.getDept_name());
			dynaBean.set("dept_id", dept_id);
		}
        if (StringUtils.isNotBlank(type_title_like)) {
			entity.getMap().put("type_tilte_like", type_title_like);
			dynaBean.set("type_tilte_like", type_title_like);
		}
        if (StringUtils.isNotBlank(add_date_s)) {
			entity.getMap().put("add_date_s", add_date_s+" 00:00:00");
			dynaBean.set("add_date_s", add_date_s);
		}
        if (StringUtils.isNotBlank(add_date_e)) {
			entity.getMap().put("add_date_e", add_date_e+" 23:59:59");
			dynaBean.set("add_date_e", add_date_e);
		}
        if (StringUtils.isNotBlank(state)) {
			entity.setState(Integer.valueOf(state));
			dynaBean.set("_state", state);
		}
        if (StringUtils.isNotBlank(isfirst)) {
			dynaBean.set("isfirst", isfirst);
		}
        
        String begindate = "";
		String enddate = "";
		if (StringUtils.isNotBlank(add_date_s)) {
			begindate = add_date_s + " 00:00:00";
			entity.getMap().put("add_date_s", begindate);
			dynaBean.set("add_date_s", add_date_s);
		}
		if (StringUtils.isNotBlank(add_date_e)) {
			enddate = add_date_e + " 23:59:59";
			entity.getMap().put("add_date_e", enddate);
			dynaBean.set("add_date_e", add_date_e);
		}
      //检查权限
        // 数据级别判断开始
		Long __dept_id = StringUtils.isNotBlank(dept_id) ? Long.valueOf(dept_id) : ui.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		
		//分公司
		if (max_dlevel == 9) {
		} else {
			entity.getMap().put("dept_id", ui.getDept_id());
		}
		
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		
		//分页
        Long recordCount = super.getFacade().getKonkaPhotoUploadTypeService().getKonkaPhotoUploadTypeForDeptPaginatedListCount(entity);
        pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
        entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
		//查询
		List<KonkaPhotoUploadType> entityList = super.getFacade().getKonkaPhotoUploadTypeService().getKonkaPhotoUploadTypeForDeptPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,              //保存
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String)dynaBean.get("mod_id");
		String type_tilte = (String)dynaBean.get("type_tilte");
		String type_memo = (String)dynaBean.get("type_memo");
		String state = (String)dynaBean.get("state");
		
		//判断session是否超时
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == ui) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		
		String[] dept_id_name = request.getParameterValues("multiselect_dept_id_name");
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		for (int i = 0; i < dept_id_name.length; i++) {
			String []temp=dept_id_name[i].split("#");
			String string=temp[0];
			String string1=temp[1];
			list1.add(string);
			list2.add(string1);
		}
		KonkaPhotoUploadType entity = new KonkaPhotoUploadType();
		
		
		
		
		if (StringUtils.isNotEmpty(id)) { //修改
			KonkaPhotoUploadType kh = new KonkaPhotoUploadType();
			kh.setId(Long.parseLong(id));
			List<KonkaPhotoUploadType> photolist = super.getFacade().getKonkaPhotoUploadTypeService().getKonkaPhotoUploadTypeList(kh);
			if (null!=photolist && photolist.size()>0) {
				kh = photolist.get(0);
				if (null!=kh) {
					Long ids = kh.getId();
					entity.setId(ids);
					entity.setType_tilte(type_tilte);
					entity.setType_memo(type_memo);
					entity.setUpdate_date(new Date());
					entity.setState(Integer.parseInt(state));
					entity.setUpdate_user_id(ui.getId());
					entity.setUpdate_user_name(ui.getUser_name());
					super.getFacade().getKonkaPhotoUploadTypeService().modifyKonkaPhotoUploadType(entity);//更新
					
					//先删除原来的type_id
					KonkaPhotoUploadTypeDept deptentity = new KonkaPhotoUploadTypeDept();
					deptentity.setType_id(ids);
					List<KonkaPhotoUploadTypeDept> deptList  = super.getFacade().getKonkaPhotoUploadTypeDeptService().getKonkaPhotoUploadTypeDeptList(deptentity);
					for (KonkaPhotoUploadTypeDept konkaPhotoUploadTypeDept : deptList) {
						Long idee = konkaPhotoUploadTypeDept.getId();
						deptentity=new KonkaPhotoUploadTypeDept();
						deptentity.setId(idee);
						super.getFacade().getKonkaPhotoUploadTypeDeptService().removeKonkaPhotoUploadTypeDept(deptentity);	
					}
					
					//再重新添加type_id
					for (int i = 0; i < list1.size(); i++) {
						deptentity = new KonkaPhotoUploadTypeDept();
						deptentity.setType_id(ids);
						deptentity.setAdd_date(new Date());
						deptentity.setDept_id(Long.parseLong(list1.get(i)));
						deptentity.setDept_name(list2.get(i));
						super.getFacade().getKonkaPhotoUploadTypeDeptService().createKonkaPhotoUploadTypeDept(deptentity);
					}
					
					super.saveMessage(request, "entity.inserted");
				}else {
					super.renderJavaScript(response, "The customer linked is empty!");
					return null;
				}
			}else {
				super.renderJavaScript(response, "The customer linked is empty!");
				return null;
			}
		}else {  //新增
			entity.setType_memo(type_memo);
			entity.setType_tilte(type_tilte);
			entity.setState(Integer.parseInt(state));
			entity.setAdd_date(new Date());
			entity.setAdd_user_id(ui.getId());
			entity.setAdd_user_name(ui.getUser_name());
			Long typeid = super.getFacade().getKonkaPhotoUploadTypeService().createKonkaPhotoUploadType(entity);
			
			
			KonkaPhotoUploadTypeDept deptentity = null;
			for (int i = 0; i < list1.size(); i++) {
				deptentity = new KonkaPhotoUploadTypeDept();
				deptentity.setType_id(typeid);
				deptentity.setAdd_date(new Date());
				deptentity.setDept_id(Long.parseLong(list1.get(i)));
				deptentity.setDept_name(list2.get(i));
				super.getFacade().getKonkaPhotoUploadTypeDeptService().createKonkaPhotoUploadTypeDept(deptentity);
			}
		    super.saveMessage(request, "entity.updated");			
		}
		
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append("/admin/KonkaPhotoUploadType.do?method=list");
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(
				request, "id", "pks", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form,                  //新增
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");
		
		
		// 判断用户是否是空
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		KonkaPhotoUploadType entity = new KonkaPhotoUploadType();
		//检查权限
        // 数据级别判断开始
		Long __dept_id = StringUtils.isNotBlank(dept_id) ? Long.valueOf(dept_id) : userInfo.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
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
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				// entity.getMap().put("dept_id_start", __dept_id);
				entity.getMap().put("fgs_dept_value", __dept_id);
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);
			break;
		case 0:
			__dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			// entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", userInfo.getId());
		// 数据级别判断结束
		
		//分公司
		KonkaDept kd = new KonkaDept();
		if (max_dlevel == 9) {
			kd.setDept_id(0L);
		} else {
			kd.setDept_id(userInfo.getDept_id());
		}
		kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
		entity.getMap().put("dept_name", kd.getDept_name());
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		
		//取得当前时间
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(calendar.YEAR);
		int month = calendar.get(calendar.MONTH) + 1 ;
		int day = calendar.get(calendar.DATE);
		dynaBean.set("year", "" + year);
		dynaBean.set("month", "" + month);
		dynaBean.set("day", "" + day);
		
		
		return mapping.findForward("input");
	}
	
	//修改
	public ActionForward edit(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		//导航信息
		setNaviStringToRequestScope(form, request);
		//防止重复提交
		super.saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
        String dept_id = (String) dynaBean.get("dept_id");
		
		
		// 判断用户是否是空
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		KonkaPhotoUploadType entity = new KonkaPhotoUploadType();
		//检查权限
        // 数据级别判断开始
		int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
		
		//分公司
		KonkaDept kd = new KonkaDept();
		if (max_dlevel == 9) {
			kd.setDept_id(0L);
		} else {
			kd.setDept_id(userInfo.getDept_id());
		}
		kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		
		//取得当前时间
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(calendar.YEAR);
		int month = calendar.get(calendar.MONTH) + 1 ;
		int day = calendar.get(calendar.DATE);
		dynaBean.set("year", "" + year);
		dynaBean.set("month", "" + month);
		dynaBean.set("day", "" + day);

		
		entity.setId(Long.parseLong(id));
		entity = super.getFacade().getKonkaPhotoUploadTypeService().getKonkaPhotoUploadType(entity); //查询到需要修改的信息
		
		
		KonkaPhotoUploadTypeDept deptentity = new KonkaPhotoUploadTypeDept();
		deptentity.setType_id(Long.parseLong(id));
		List<KonkaPhotoUploadTypeDept> deptlist = super.getFacade()
				.getKonkaPhotoUploadTypeDeptService()
				.getKonkaPhotoUploadTypeDeptList(deptentity);
		String deptString = "";
		for (KonkaPhotoUploadTypeDept konkaPhotoUploadTypeDept : deptlist) {
			deptString += konkaPhotoUploadTypeDept.getDept_id()+"#"+konkaPhotoUploadTypeDept.getDept_name()+",";
		}
		dynaBean.set("deptString",deptString);
		
		

		entity.setQueryString(super.serialize(request, "id","method"));
		super.copyProperties(form, entity);
		return mapping.findForward("input");
	}
	
	//查看
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		KonkaPhotoUploadType entity = new KonkaPhotoUploadType();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaPhotoUploadTypeService().getKonkaPhotoUploadTypeForDept(entity);
//        KonkaR3ShopLink entity = new KonkaR3ShopLink();
//        entity.setLink_id(Long.valueOf(link_id));
//        entity =  super.getFacade().getKonkaR3ShopLinkService().getKonkaR3ShopLinkForCustomer(entity);
        super.copyProperties(form, entity);
		return mapping.findForward("view");
	}
}
