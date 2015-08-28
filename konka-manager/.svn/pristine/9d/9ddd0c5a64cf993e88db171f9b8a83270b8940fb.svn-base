package com.ebiz.mmt.web.struts.manager.ywygps;

import java.io.ByteArrayInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ebiz.mmt.domain.KonkaUserMobileSet;
import com.ebiz.mmt.domain.KonkaUserMobileSetPlan;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Cheng,Bing
 * @version 2012-02-14
 */
public class KonkaYwyMobileSetPlanAction extends BaseAction {

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
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		
		String fs_id = (String) dynaBean.get("fs_id");
		String setplan_name_like = (String) dynaBean.get("setplan_name_like");
		
		KonkaUserMobileSetPlan msp = new KonkaUserMobileSetPlan();
		msp.setIs_del(0L);
		if (StringUtils.isNotBlank(fs_id)) {
			msp.setS_id(Long.valueOf(fs_id));
		}
		if (StringUtils.isNotBlank(setplan_name_like)) {
			msp.setSetplan_name(setplan_name_like);
		}
		
		Long recordCount = super.getFacade().getKonkaUserMobileSetPlanService().getKonkaUserMobileSetPlanCount(msp);
		pager.init(recordCount, 10, pager.getRequestPage());
		msp.getRow().setFirst(pager.getFirstRow());
		msp.getRow().setCount(pager.getRowCount());
		List<KonkaUserMobileSetPlan> mtList = super.getFacade().getKonkaUserMobileSetPlanService().getKonkaUserMobileSetPlanPaginatedList(msp);
		for (int i = 0; i < mtList.size(); i++) {
			KonkaUserMobileSetPlan tmp = mtList.get(i);

			if (StringUtils.isBlank(fs_id)) {
				KonkaUserMobileSet ms = new KonkaUserMobileSet();
				ms.setId(tmp.getS_id());
				ms = super.getFacade().getKonkaUserMobileSetService().getKonkaUserMobileSet(ms);
				if(ms != null)
				   tmp.getMap().put("entp_name",ms.getEntp_name());
			}
			
			if(tmp.getNext_id() != null){
				KonkaUserMobileSetPlan next_msp = new KonkaUserMobileSetPlan();
				next_msp.setId(tmp.getNext_id());
				next_msp.setIs_del(0L);
				next_msp = super.getFacade().getKonkaUserMobileSetPlanService().getKonkaUserMobileSetPlan(next_msp);
				if(next_msp != null)
				   tmp.getMap().put("next_setplan_name",next_msp.getSetplan_name());
			}
		}
		
		if (StringUtils.isNotBlank(fs_id)) {
			KonkaUserMobileSet ms = new KonkaUserMobileSet();
			ms.setId(Long.valueOf(fs_id));
			ms = super.getFacade().getKonkaUserMobileSetService().getKonkaUserMobileSet(ms);
			dynaBean.set("entp_name",ms.getEntp_name());

		}

		request.setAttribute("entityList", mtList);
		
		return mapping.findForward("list");
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String s_id = (String) dynaBean.get("s_id");
		if (StringUtils.isNotBlank(s_id)) {
			KonkaUserMobileSet ms = new KonkaUserMobileSet();
			ms.setId(Long.valueOf(s_id));
			ms = super.getFacade().getKonkaUserMobileSetService().getKonkaUserMobileSet(ms);
			dynaBean.set("entp_name",ms.getEntp_name());
		}
		
		Calendar calendar = Calendar.getInstance(Locale.CHINA); // 日历对象
		calendar.setTime(new Date());
		
		String year = DateFormatUtils.format(calendar, "yyyy");
		dynaBean.set("year", Integer.parseInt(year));
		
		return mapping.findForward("input");
	}
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String p_id = (String) dynaBean.get("p_id");
		String s_id = (String) dynaBean.get("s_id");
		String id = (String) dynaBean.get("id");
		
		KonkaUserMobileSet ms = new KonkaUserMobileSet();
		ms.setId(Long.valueOf(s_id));
		ms = super.getFacade().getKonkaUserMobileSetService().getKonkaUserMobileSet(ms);
		dynaBean.set("entp_name",ms.getEntp_name());
		
		KonkaUserMobileSetPlan msp = new KonkaUserMobileSetPlan();
		msp.setId(Long.valueOf(id));
		msp.setS_id(Long.valueOf(s_id));
		msp.setIs_del(0L);
		msp = super.getFacade().getKonkaUserMobileSetPlanService().getKonkaUserMobileSetPlan(msp);
		super.copyProperties(form, msp);
		
		if(StringUtils.isNotBlank(p_id)){
			KonkaUserMobileSetPlan before_msp = new KonkaUserMobileSetPlan();
			before_msp.setId(Long.valueOf(p_id));
			before_msp.setS_id(Long.valueOf(s_id));
			before_msp.setIs_del(0L);
			before_msp = super.getFacade().getKonkaUserMobileSetPlanService().getKonkaUserMobileSetPlan(before_msp);
			if(before_msp != null){
				dynaBean.set("before_setplan_name", before_msp.getSetplan_name());
			}
		}
	
		return mapping.findForward("view");
	}
	public ActionForward nextMonth(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String s_id = (String) dynaBean.get("s_id");
		String p_id = (String) dynaBean.get("p_id");

		KonkaUserMobileSetPlan msp = new KonkaUserMobileSetPlan();
		if (StringUtils.isNotBlank(p_id) && StringUtils.isNotBlank(s_id)) {
			KonkaUserMobileSet ms = new KonkaUserMobileSet();
			ms.setId(Long.valueOf(s_id));
			ms = super.getFacade().getKonkaUserMobileSetService().getKonkaUserMobileSet(ms);
			dynaBean.set("entp_name",ms.getEntp_name());
			
			msp.setId(Long.valueOf(p_id));
			msp.setS_id(Long.valueOf(s_id));
			msp.setIs_del(0L);
			msp = super.getFacade().getKonkaUserMobileSetPlanService().getKonkaUserMobileSetPlan(msp);

			if(Long.valueOf(12L).equals(msp.getMonth())){
				dynaBean.set("year",msp.getYear() + 1);
				dynaBean.set("month",1);
			}else{
				dynaBean.set("year",msp.getYear());
				dynaBean.set("month",msp.getMonth() + 1);
			}
			
			request.setAttribute("setplan", msp);
			
			return mapping.findForward("input");
		}else{
			return mapping.findForward("list");
		}
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;	
		String s_id = (String) dynaBean.get("s_id");
		String id = (String) dynaBean.get("id");
		
		KonkaUserMobileSet ms = new KonkaUserMobileSet();
		ms.setId(Long.valueOf(s_id));
		ms = super.getFacade().getKonkaUserMobileSetService().getKonkaUserMobileSet(ms);
		dynaBean.set("entp_name",ms.getEntp_name());
		
		KonkaUserMobileSetPlan msp = new KonkaUserMobileSetPlan();
		msp.setId(Long.valueOf(id));
		msp.setS_id(Long.valueOf(s_id));
		msp.setIs_del(0L);
		msp = super.getFacade().getKonkaUserMobileSetPlanService().getKonkaUserMobileSetPlan(msp);
		super.copyProperties(form, msp);
		
		return mapping.findForward("input");
	}
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		if (isCancelled(request)) {
			return list(mapping, form, request, response);
		}
		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}
		resetToken(request);
		
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String s_id = (String) dynaBean.get("s_id");
		String p_id = (String) dynaBean.get("p_id");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String setplan_name = (String) dynaBean.get("setplan_name");

		String month_days = (String) dynaBean.get("month_days");		
		String week_days[] = (String[]) request.getParameterValues("week_days");
		
		String add_nos = (String) dynaBean.get("add_nos");
		String gps_sendType = (String) dynaBean.get("gps_sendType");
		// 登录用户信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		KonkaUserMobileSetPlan mtp = new KonkaUserMobileSetPlan();
		mtp.setS_id(Long.valueOf(s_id));
	
		mtp.setYear(Long.valueOf(year));
		mtp.setMonth(Long.valueOf(month));
		String date_arr[] = new String[Integer.parseInt(month_days)];
		if(week_days != null && week_days.length > 0){
			for(int i = 0; i < Integer.parseInt(month_days); i++){
				date_arr[i] = "0";
			}
			for(int i = 0; i < week_days.length; i++){
				date_arr[Integer.parseInt(week_days[i]) - 1] = "1";
			}
			mtp.setDate_str(StringUtils.join(date_arr,","));
		}else{
			mtp.setDate_str("");
		}

		String user_defined = (String) dynaBean.get("user_defined");
		if(StringUtils.isNotBlank(user_defined)){
			String user_defined_interval = (String) dynaBean.get("user_defined_interval");
			mtp.setTime_interval(Long.valueOf(user_defined_interval));
		}else{
			String default_interval = (String) dynaBean.get("default_interval");
			mtp.setTime_interval(Long.valueOf(default_interval));
		}
		
		String start_time[] = (String[]) request.getParameterValues("start_time");
		String end_time[] = (String[]) request.getParameterValues("end_time");		
		
		String time_str = "";
		if (start_time != null && end_time != null) {
			String time_arr[] = new String[start_time.length];
			for (int i = 0; i < time_arr.length; i++) {
				time_arr[i] = start_time[i] + "--" + end_time[i];
			}
			time_str = StringUtils.join(time_arr, ",");
		}
		
		if(StringUtils.isNotBlank(time_str)){
			mtp.setTime_str(time_str);
		}
		
		if(StringUtils.isNotBlank(id)){
			mtp.setId(Long.valueOf(id));
			String blockmobile_str = "";
			String blockmobile[] = (String[]) request.getParameterValues("blockmobile");
			if(blockmobile != null && blockmobile.length > 0){
				blockmobile_str = StringUtils.join(blockmobile, ",");
			}
			if(StringUtils.isNotBlank(add_nos) && add_nos.length() > 10){
				blockmobile_str = blockmobile_str + "," + add_nos;
			}

			mtp.setBlockmobile_str(blockmobile_str);
			mtp.setUpdate_uid(peProdUser.getId());
			mtp.setUpdate_date(new Date());
			
			String s_version = (String) dynaBean.get("s_version");
			if(StringUtils.isNotBlank(s_version)){
				Double d = Double.valueOf(s_version);
				d = d + 0.1;
				DecimalFormat df =new DecimalFormat("#.0");
				mtp.setVersion(df.format(d));
			}else{
				mtp.setVersion("1.0");
			}
			mtp.setGps_sendType(Long.valueOf(gps_sendType));
			super.getFacade().getKonkaUserMobileSetPlanService().modifyKonkaUserMobileSetPlan(mtp);
			
			saveMessage(request, "entity.updated");
		}else{	
			mtp.setSetplan_name(setplan_name);
			if(StringUtils.isNotBlank(add_nos) && add_nos.length() > 10){
				mtp.setBlockmobile_str(add_nos);
			}

			Date d = new Date();
			String randString = "";
			while(true){			
				randString = createReferCode(setplan_name,d);
				
				KonkaUserMobileSetPlan tmp = new KonkaUserMobileSetPlan();
				tmp.setSetplan_crc(randString);
				tmp.setIs_del(0L);
				tmp = super.getFacade().getKonkaUserMobileSetPlanService().getKonkaUserMobileSetPlan(tmp);
				if(tmp == null) break;
				d = new Date();
			}
			mtp.setCreate_date(d);
			mtp.setCreate_uid(peProdUser.getId());
			mtp.setUpdate_uid(peProdUser.getId());
			mtp.setUpdate_date(d);
			
			mtp.setSetplan_crc(randString);
			mtp.setVersion("1.0");
			mtp.setIs_del(0L);
			mtp.setGps_sendType(Long.valueOf(gps_sendType));
			
			Long nid = super.getFacade().getKonkaUserMobileSetPlanService().createKonkaUserMobileSetPlan(mtp);
			
			if(StringUtils.isNotBlank(p_id) && nid != null){
				KonkaUserMobileSetPlan tmp = new KonkaUserMobileSetPlan();
				tmp.setId(Long.valueOf(p_id));
				tmp.setIs_del(0L);
				tmp = super.getFacade().getKonkaUserMobileSetPlanService().getKonkaUserMobileSetPlan(tmp);
				if(tmp != null){
				   tmp.setNext_id(nid);
				   super.getFacade().getKonkaUserMobileSetPlanService().modifyKonkaUserMobileSetPlan(tmp);
				}
			}
			saveMessage(request, "entity.inserted");
		}

		return list(mapping, form,request,response);
	}
	
	public String createReferCode(String user_name, Date d) throws Exception {
		
		String randString = user_name + convertDateToStr(d,"yyyy-MM-dd HH:mm:ss");
		ByteArrayInputStream bais = null;
		String crcCode = null;
		bais = new ByteArrayInputStream(randString.getBytes("UTF-8"));
		crcCode = getCRC32Code(bais);
		//CRC32验证码，低于8位补齐
		String fillStr = "00000000";
		randString = fillStr.substring(crcCode.length()) + crcCode;
		return randString;
	}
	
	/**
	 * @param tiemstr 传入的字符型日期数据 /yyyy-MM-dd HH:mm:ss/
	 * @throws Exception
	 * @return
	 * @throws Exception
	 */
	public String convertDateToStr(Date time,String format) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(time);
	}
	
	public static String getCRC32Code(ByteArrayInputStream bais) throws Exception {

		CRC32 crc32 = new CRC32();
		CheckedInputStream cis = new CheckedInputStream(bais, crc32);

		byte[] buf = new byte[128];
		while (cis.read(buf) >= 0) {

		}
		return Long.toHexString(crc32.getValue());
	}
}
