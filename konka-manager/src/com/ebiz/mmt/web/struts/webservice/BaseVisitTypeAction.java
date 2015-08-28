package com.ebiz.mmt.web.struts.webservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.write.DateFormat;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.BaseVisitType;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * 拜访类型基础数据
 * @author Administrator
 *
 */
public class BaseVisitTypeAction extends BaseAction {
	
	/**
	 * 通过上报类型查找对应的拜访类型
	 */
	public ActionForward ajaxBaseVisitTypeByReportTypeList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		 DynaBean dynaBean = (DynaBean) form;
         String report_type = (String) dynaBean.get("report_type");
         BaseVisitType entity=new BaseVisitType();
         if (StringUtils.isNotBlank(report_type)&&StringUtils.isNumeric(report_type)) {
        	 entity.setReport_type(Integer.valueOf(report_type));
		 }
         entity.setState(0);//未停用
         List<BaseVisitType> entityList =super.getFacade().getBaseVisitTypeService().getBaseVisitTypeByReportTypeList(entity);
		super.renderJson(response, JSON.toJSONString(entityList));
		return null;
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String report_type = (String) dynaBean.get("report_type");
		String visit_type_name = (String) dynaBean.get("visit_type_name");
		String state = (String) dynaBean.get("state");
		

		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		// 类型
		String type = (String) dynaBean.get("type");

		// 时间
		String start_date = (String) dynaBean.get("start_date");
		
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

		// 用户名或者密码不存在， 资讯信息类别为公开
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);

		if (null == userInfo) {
			super.renderHtml(response, "登录超时");
			return null;
		}
		BaseVisitType entity = new BaseVisitType();
		super.copyProperties(entity, form);
		
		
		if (StringUtils.isNotBlank(report_type)&&StringUtils.isNumeric(report_type)) {
			entity.setReport_type(Integer.valueOf(report_type));
		}
		if (StringUtils.isNotBlank(visit_type_name)) {
			entity.setVisit_type_name(String.valueOf(visit_type_name));
		}
		if (StringUtils.isNotBlank(state)&&StringUtils.isNumeric(state)) {
			entity.setState(Integer.valueOf(state));
		}
		entity.setAdd_id(userInfo.getId());
		entity.setAdd_date(new Date());
		Long visit_type_id=super.getFacade().getBaseVisitTypeService().createBaseVisitType(entity);
		if (visit_type_id<0) {
			visit_type_id=0L;
		}
		super.renderJson(response, JSON.toJSONString("success:" + visit_type_id));
		return null;
	}
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		 DynaBean dynaBean = (DynaBean) form;
		// 用户名
			String inuser_id = (String) dynaBean.get("user_id");
			// 密码
			String inuserpass = (String) dynaBean.get("userpass");
			// 类型
			String type = (String) dynaBean.get("type");

			// 时间
			String start_date = (String) dynaBean.get("start_date");

			//版本号
			String android_version=(String) dynaBean.get("android_version");
			String ios_version=(String) dynaBean.get("ios_version");
			// 用户名或者密码不存在， 资讯信息类别为公开
			PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);  

			if (null == userInfo) {
				super.renderHtml(response, "登录超时");
				return null;
			}
         String report_type = (String) dynaBean.get("report_type");
         BaseVisitType entity=new BaseVisitType();
         if (StringUtils.isNotBlank(report_type)&&StringUtils.isNumeric(report_type)) {
        	 entity.setReport_type(Integer.valueOf(report_type));
		 }
         List<BaseVisitType> entityList =super.getFacade().getBaseVisitTypeService().getBaseVisitTypeList(entity);
         List<HashMap> list =new ArrayList<HashMap>();
     	 HashMap map= new HashMap();
     	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
 		 for(BaseVisitType baseVisitType:entityList){	
 			map= new HashMap();
 			map.put("visit_type_id",baseVisitType.getVisit_type_id()==null?"":baseVisitType.getVisit_type_id());
 			map.put("visit_type_name",baseVisitType.getVisit_type_name()==null?"":baseVisitType.getVisit_type_name());
 			map.put("report_type",baseVisitType.getReport_type()==null?"":baseVisitType.getReport_type());
 			map.put("add_id",baseVisitType.getAdd_id()==null?"":baseVisitType.getAdd_id());
 			if(null!=baseVisitType.getAdd_date()){
 			map.put("add_date",df.format(baseVisitType.getAdd_date()));
 			}
 			map.put("state",baseVisitType.getState()==null?"":baseVisitType.getState());
 			list.add(map);
 		 }
	     logger.info(entityList.toString());
	     super.renderJson(response,JSON.toJSONString(list));
	     return null;
	}
}
