package com.ebiz.mmt.web.struts;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JcfxReportXsqs;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;

public class KonkaMobileDateReportAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form; 
		String user_id=(String) dynaBean.get("user_id");
		String user_password=(String) dynaBean.get("userpass");
		String start_time=(String) dynaBean.get("start_time");
		String end_time=(String) dynaBean.get("end_time");
		String dept_id=(String) dynaBean.get("dept_id");
		String data_type=(String) dynaBean.get("data_type");//0 表示 销售量，1，表示销售额 ，2 表示结算量    3 表示结算额
		//版本号,android_version,ios_version
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
      
		
		PeProdUser peProdUser = checkUserid(user_id,user_password,android_version,ios_version);
		//PeProdUser peProdUser = checkUser("0","p@ssword9");
		if(null==peProdUser){
			return null;
		}
		
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
	
		if(StringUtils.isNotBlank(end_time)){
			request.setAttribute("end_time", end_time);
		}else{
			
			request.setAttribute("end_time", df.format(new Date())+" 23:59:59");
		}
		if(StringUtils.isNotBlank(start_time)){
			request.setAttribute("start_time", start_time);
		}else{
			 now.add(Calendar.MONTH, -1);
			request.setAttribute("start_time", df.format(now.getTime())+" 00:00:00");
		}
		if (StringUtils.isNotBlank(data_type)) {
			request.setAttribute("data_type", data_type);
		}else{
			request.setAttribute("data_type", "0");
		}
		
		
		if(StringUtils.isNotBlank(dept_id)){
			request.setAttribute("dept_id", dept_id);
		}else{
			 if (peProdUser.getUser_type() == 0) {
				
			} else if (peProdUser.getUser_type() == 1) {
				// 康佳分公司
				KonkaDept konkaDept = super.getKonkaDeptForFgs(peProdUser.getDept_id());
				if (null == konkaDept) {
					String msg = super.getMessage(request, "popedom.check.invalid");
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
					return null;
				} else {
					request.setAttribute("dept_id", konkaDept.getDept_id());
				}
			}
		}
		
		request.setAttribute("user_id", peProdUser.getId());
		request.setAttribute("userpass",peProdUser.getPass_word());
		
//		String report_type = (String) dynaBean.get("report_type");
//		dynaBean.set("is_del", "0");
//		dynaBean.set("report_type", report_type);
		return  new ActionForward("/KonkaMobileDateReport/MainContent.jsp");
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form; 
		String user_id=(String) dynaBean.get("user_id");
		String user_password=(String) dynaBean.get("userpass");
		String start_time=(String) dynaBean.get("start_time");
		String end_time=(String) dynaBean.get("end_time");
		String dept_id=(String) dynaBean.get("dept_id");
		String type=(String) dynaBean.get("type");
		//1按分公司
		//2按渠道
		//3按類型
		String data_type=(String) dynaBean.get("data_type");//0 表示 销售量，1，表示销售额 ，2 表示结算量    3 表示结算额
		
		
		int type_id=Integer.parseInt(type);
		
		
		//為了避免出現很多個基礎類，適用其他的不想關的類來接收與傳參數
			
		JcfxReportXsqs jcfxReportXsqs=new JcfxReportXsqs();
		if(StringUtils.isNotBlank(dept_id)){
			jcfxReportXsqs.getMap().put("dept_id", dept_id);
		}
		if(StringUtils.isNotBlank(start_time)){
			jcfxReportXsqs.getMap().put("start_time", start_time);
		}
		if(StringUtils.isNotBlank(end_time)){
			jcfxReportXsqs.getMap().put("end_time", end_time);
		}
		if(StringUtils.isNotBlank(type)){
			jcfxReportXsqs.getMap().put("type", type);
		}
		
		switch (type_id){
		case 0: 
			jcfxReportXsqs.getMap().put("group_name", "dept_name");
			jcfxReportXsqs.getMap().put("link_table","STATISTICAL_DIMENSION_STORE");
			jcfxReportXsqs.getMap().put("join_name", "on a.DEPT_ID=b.STORE_ID");
			request.setAttribute("title", "分公司");
		break;
		case 1: 
			jcfxReportXsqs.getMap().put("group_name", "PAR_CUSTOMER_TYPE_NAME");
			jcfxReportXsqs.getMap().put("join_name", "on a.DEPT_ID=b.STORE_ID");
			jcfxReportXsqs.getMap().put("link_table","STATISTICAL_DIMENSION_STORE");
			request.setAttribute("title", "渠道");
			break;	
		case 2: 
			jcfxReportXsqs.getMap().put("group_name", "size_sec");
			jcfxReportXsqs.getMap().put("join_name", "on a.model_id=b.PD_ID");
			jcfxReportXsqs.getMap().put("link_table","KONKA_PE_PD_MODEL");
			jcfxReportXsqs.getMap().put("ext_select","true");
			request.setAttribute("title", "尺寸");
			
		break;
		
		}
	
		List<JcfxReportXsqs> entityList=super.getFacade().getJcfxReportXsqsService().
				getKonkaMobileDateReportList(jcfxReportXsqs);
		
//		request.setAttribute("title", "分公司");
		request.setAttribute("entityList", entityList);
		request.setAttribute("data_type", null==data_type?"0":data_type);
		//尺寸段基础数据
		super.getFacade().getKonkaBaseTypeDataService().setRequestSizeSecList(request, "sizeSecList", "100023");
		return  new ActionForward("/KonkaMobileDateReport/DataView.jsp");
	}
	
	
	public ActionForward getDataCount(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form; 
		String user_id=(String) dynaBean.get("user_id");
		String user_password=(String) dynaBean.get("userpass");
		String start_time=(String) dynaBean.get("start_time");
		String end_time=(String) dynaBean.get("end_time");
		String dept_id=(String) dynaBean.get("dept_id");
	
		//版本号,android_version,ios_version
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
      
		
		PeProdUser peProdUser = checkUserid(user_id,user_password,android_version,ios_version);
		//PeProdUser peProdUser = checkUser("0","p@ssword9");
		if(null==peProdUser){
			return null;
		}
		
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
	
		if(StringUtils.isNotBlank(end_time)){
		}else{
			
			end_time=df.format(now.getTime())+" 23:59:59";
		}
		if(StringUtils.isNotBlank(start_time)){
		}else{
			 now.add(Calendar.MONTH, -1);
			 start_time=df.format(now.getTime())+" 00:00:00";
		}
		
		
		
		JcfxReportXsqs jcfxReportXsqs=new JcfxReportXsqs();
		if(StringUtils.isNotBlank(dept_id)){
			 jcfxReportXsqs.getMap().put("dept_id", dept_id);
		}else{
			 if (peProdUser.getUser_type() == 0) {
			} else if (peProdUser.getUser_type() == 1) {
				// 康佳分公司
				KonkaDept konkaDept = super.getKonkaDeptForFgs(peProdUser.getDept_id());
				if (null == konkaDept) {
				} else {
					jcfxReportXsqs.getMap().put("dept_id", konkaDept.getDept_id());
				}
			}
		}
		
		
		
		if(StringUtils.isNotBlank(start_time)){
			jcfxReportXsqs.getMap().put("start_time", start_time);
		}
		if(StringUtils.isNotBlank(end_time)){
			jcfxReportXsqs.getMap().put("end_time", end_time);
		}
		
		
		
		request.setAttribute("user_id", peProdUser.getId());
		request.setAttribute("userpass",peProdUser.getPass_word());
		
		List<JcfxReportXsqs> entityList=super.getFacade().getJcfxReportXsqsService().getKonkaMobileDateReportCount(jcfxReportXsqs);
		
		HashMap map=new HashMap<String, Object>();
		if(null!=entityList && entityList.size()>0){
			for(JcfxReportXsqs domain:entityList ){
				map.put("num", null==domain.getMap().get("num")?"0":domain.getMap().get("num"));
				map.put("money", null==domain.getMap().get("money")?"0":domain.getMap().get("money"));
				map.put("last_num", null==domain.getMap().get("last_num")?"0":domain.getMap().get("last_num"));
				map.put("last_money", null==domain.getMap().get("last_money")?"0":domain.getMap().get("last_money"));
				map.put("settle_num", null==domain.getMap().get("settle_num")?"0":domain.getMap().get("settle_num"));
				map.put("settle_money", null==domain.getMap().get("settle_money")?"0":domain.getMap().get("settle_money"));
				map.put("last_settle_num", null==domain.getMap().get("last_settle_num")?"0":domain.getMap().get("last_settle_num"));
				map.put("last_settle_money", null==domain.getMap().get("last_settle_money")?"0":domain.getMap().get("last_settle_money"));
				map.put("num_range", null==domain.getMap().get("num_range")?"0":domain.getMap().get("num_range"));
				map.put("money_range", null==domain.getMap().get("money_range")?"0":domain.getMap().get("money_range"));
				map.put("settle_num_range", null==domain.getMap().get("settle_num_range")?"0":domain.getMap().get("settle_num_range"));
				map.put("settle_money_range", null==domain.getMap().get("settle_money_range")?"0":domain.getMap().get("settle_money_range"));
			}
		}
		JSONArray jsonArray = JSONArray.fromObject(map);
		int start = jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start + 1, end + 1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}
	

}
