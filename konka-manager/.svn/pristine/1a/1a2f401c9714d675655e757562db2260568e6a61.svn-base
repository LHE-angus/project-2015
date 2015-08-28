package com.ebiz.mmt.web.struts.manager.ywygps;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaUserMobile;
import com.ebiz.mmt.domain.KonkaUserMobileSet;
import com.ebiz.mmt.domain.KonkaUserMobileSetPlan;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Cheng,Bing
 * @version 2012-02-15
 */
public class KonkaYwyMobileAction extends BaseAction {

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
		String entp_crc_like = (String) dynaBean.get("entp_crc_like");
		String ywy_mobile_no = (String) dynaBean.get("ywy_mobile_no");
		String fgs_dept_id = (String) dynaBean.get("fgs_dept_id");
		String jyb_dept_id = (String) dynaBean.get("jyb_dept_id");
		String bsc_dept_id = (String) dynaBean.get("bsc_dept_id");
		String ywy_user_id = (String) dynaBean.get("ywy_user_id");
		
		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		
		KonkaUserMobile mt = new KonkaUserMobile();
		mt.setIs_del(0L);
	
		if (StringUtils.isNotBlank(entp_crc_like)) {
			mt.setEntp_crc(entp_crc_like);
		}
		if (StringUtils.isNotBlank(ywy_mobile_no)) {
			mt.setMobile_no(ywy_mobile_no);
		}
		if (GenericValidator.isLong(fgs_dept_id)) {
			mt.getMap().put("konka_dept_id", fgs_dept_id);
		}
		if (GenericValidator.isLong(jyb_dept_id)) {
			mt.getMap().put("konka_dept_id", jyb_dept_id);
		}
		if (GenericValidator.isLong(bsc_dept_id)) {
			mt.getMap().put("konka_dept_id", bsc_dept_id);
		}
		if (GenericValidator.isLong(ywy_user_id)) {
			mt.getMap().put("ywy_user_id", ywy_user_id);
		}
		
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_20_lt_60 = false;
		boolean role_id_eq_60 = false;
		for (PeRoleUser peRoleUser: peRoleUserList) {
			if (peRoleUser.getRole_id() >= 20L && peRoleUser.getRole_id() < 60L) {
				role_id_ge_20_lt_60 = true;
			}
			if (peRoleUser.getRole_id() == 60L) {
				role_id_eq_60 = true;
			}
		}
		
		if (role_id_ge_20_lt_60) { // 不是系统管理员组全部要加上网点分配
			mt.getMap().put("dept_id", this.getSessionUserInfo(request).getDept_id());
			mt.getMap().put("user_id", this.getSessionUserInfo(request).getId());
		}
		if (role_id_eq_60) { // 业务员只能看到分配给自己的
			mt.getMap().put("ywy_user_id", this.getSessionUserInfo(request).getId());
		}
		Long recordCount = super.getFacade().getKonkaUserMobileService().getKonkaUserMobileCount(mt);
		pager.init(recordCount, 10, pager.getRequestPage());
		mt.getRow().setFirst(pager.getFirstRow());
		mt.getRow().setCount(pager.getRowCount());
		List<KonkaUserMobile> mtList = super.getFacade().getKonkaUserMobileService().getKonkaUserMobilePaginatedList(mt);
		for (int i = 0; i < mtList.size(); i++) {
			KonkaUserMobile tmp = mtList.get(i);	
			PeProdUser pu = new PeProdUser();
			pu.setId(tmp.getUser_id());
			pu.setIs_del(0);		
			pu = getFacade().getPeProdUserService().getPeProdUser(pu);
			if(pu != null){
				tmp.getMap().put("real_name", pu.getReal_name());
			}
			
			KonkaUserMobileSet ms = new KonkaUserMobileSet();
			ms.setEntp_crc(tmp.getEntp_crc());
			ms.setIs_del(0L);
			ms = super.getFacade().getKonkaUserMobileSetService().getKonkaUserMobileSet(ms);
			if(ms != null){
				tmp.getMap().put("entp_name", ms.getEntp_name());
			}
			
			KonkaUserMobileSetPlan msp = new KonkaUserMobileSetPlan();
			
			String sp_crc = tmp.getSetplan_crc();
			if(sp_crc != null){
				if(sp_crc.length() > 8){
					String arr[] = sp_crc.split(",");
					msp.setSetplan_crc(arr[arr.length -1]);
				}else{
					msp.setSetplan_crc(sp_crc);
				}

				msp.setIs_del(0L);
				msp = super.getFacade().getKonkaUserMobileSetPlanService().getKonkaUserMobileSetPlan(msp);
				if(msp != null){
				   tmp.getMap().put("setplan_name", msp.getYear()+"年"+msp.getMonth()+"月_"+msp.getSetplan_name());
				}
			}
		}
		request.setAttribute("entityList", mtList);
		
		request.setAttribute("sybDeptInfoList", super.getDeptInfoList(mapping, form, request, response, null));
		
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
		// 变更
		String fs_id = (String) dynaBean.get("fs_id");
		KonkaUserMobileSet ms = new KonkaUserMobileSet();
		if (StringUtils.isNotBlank(fs_id)) {
			ms.setId(Long.valueOf(fs_id));
		}
		ms.setIs_del(0L);
		List<KonkaUserMobileSet> list = super.getFacade().getKonkaUserMobileSetService().getKonkaUserMobileSetList(ms);
		if (StringUtils.isNotBlank(fs_id)) {
			KonkaUserMobileSet m = list.get(0);
			dynaBean.set("entp_crc", m.getId()+"_"+m.getEntp_crc());
		}
		request.setAttribute("entpList",list);
		
		request.setAttribute("sybDeptInfoList", super.getDeptInfoList(mapping, form, request, response, null));
		return mapping.findForward("input");
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;	
		String id = (String) dynaBean.get("id");
		// 变更
		String fs_id = (String) dynaBean.get("fs_id");
		KonkaUserMobile mt = new KonkaUserMobile();
		
		mt.setId(Long.valueOf(id));
		mt = super.getFacade().getKonkaUserMobileService().getKonkaUserMobile(mt);
		super.copyProperties(form, mt);
		
		
		// 部门信息
		PeProdUser pu = new PeProdUser();
		pu.setId(mt.getUser_id());
		pu.setIs_del(0);
		pu = getFacade().getPeProdUserService().getPeProdUser(pu);
		if(pu != null){
			KonkaDept dept3 = getSuperiorForDeptType(pu.getDept_id(),3);
			if(dept3 != null){
				dynaBean.set("fgs_dept_id", dept3.getDept_id().toString());	
			}
			KonkaDept dept4 = getSuperiorForDeptType(pu.getDept_id(),4);
			if(dept4 != null){
				dynaBean.set("jyb_dept_id", dept4.getDept_id().toString());
			}		
			KonkaDept dept5 = getSuperiorForDeptType(pu.getDept_id(),5);
			if(dept5 != null){
				dynaBean.set("bsc_dept_id", dept5.getDept_id().toString());	
			}
		}
		
		KonkaUserMobileSet ms1 = new KonkaUserMobileSet();
		ms1.setEntp_crc(mt.getEntp_crc());
		ms1.setIs_del(0L);
		ms1 = super.getFacade().getKonkaUserMobileSetService().getKonkaUserMobileSet(ms1);
		if(ms1 != null){
			dynaBean.set("entp_id", ms1.getId());
		}
		
		KonkaUserMobileSet ms2 = new KonkaUserMobileSet();
		if (StringUtils.isNotBlank(fs_id)) {
			ms2.setId(Long.valueOf(fs_id));
		}
		ms2.setEntp_crc(mt.getEntp_crc());
		ms2.setIs_del(0L);
		List<KonkaUserMobileSet> list = super.getFacade().getKonkaUserMobileSetService().getKonkaUserMobileSetList(ms2);
		if (StringUtils.isNotBlank(fs_id)) {
			KonkaUserMobileSet m = list.get(0);
			dynaBean.set("entp_crc", m.getId()+"_"+m.getEntp_crc());
		}
		request.setAttribute("entpList",list);
		
		request.setAttribute("sybDeptInfoList", super.getDeptInfoList(mapping, form, request, response, null));
		
		return mapping.findForward("input");
	}
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (isCancelled(request)) {
			return list(mapping, form, request, response);
		}
		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}
		resetToken(request);
		
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String entp_crc = (String) dynaBean.get("entp_crc");
		String setplan_crc = (String) dynaBean.get("setplan_crc");
		String ywy_user_id = (String) dynaBean.get("user_id");
		String ywy_mobile_no = (String) dynaBean.get("mobile_no");
		String fgs_dept_id = (String) dynaBean.get("fgs_dept_id");
		String jyb_dept_id = (String) dynaBean.get("jyb_dept_id");
		String bsc_dept_id = (String) dynaBean.get("bsc_dept_id");

		// 登录用户信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		KonkaUserMobile mt = new KonkaUserMobile();
		mt.setUser_id(Long.valueOf(ywy_user_id));
		mt.setMobile_no(ywy_mobile_no);
		mt.setEntp_crc(entp_crc.split("_")[1]);
		
		if(StringUtils.isNotBlank(bsc_dept_id)){
			mt.setDept_id(new Long(bsc_dept_id));
		}else if(StringUtils.isNotBlank(jyb_dept_id)){
			mt.setDept_id(new Long(jyb_dept_id));
		}else if(StringUtils.isNotBlank(fgs_dept_id)){
			mt.setDept_id(new Long(fgs_dept_id));
		}
		
		mt.setSetplan_crc(setplan_crc);
		Date d = new Date();
		String randString = createReferCode(ywy_mobile_no,d);		
		mt.setYwy_crc(randString);

		mt.setIs_activate(0L);		
		mt.setCreate_date(d);
		mt.setCreate_uid(peProdUser.getId());
		mt.setUpdate_uid(peProdUser.getId());
		mt.setUpdate_date(d);
		
		if(StringUtils.isNotBlank(id)){
			mt.setId(Long.valueOf(id));
			super.getFacade().getKonkaUserMobileService().modifyKonkaUserMobile(mt);
			saveMessage(request, "entity.updated");
		}else{		
			super.getFacade().getKonkaUserMobileService().createKonkaUserMobile(mt);
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
	
	public ActionForward getMonthSetPlan(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id_crc = (String) dynaBean.get("id_crc");
		StringBuffer sb = new StringBuffer("[");

		if (StringUtils.isBlank(id_crc)) {
			return null;
		}

		KonkaUserMobileSetPlan ms = new KonkaUserMobileSetPlan();
		ms.setS_id(Long.valueOf(id_crc.split("_")[0]));
		ms.setIs_del(0L);
		List<KonkaUserMobileSetPlan> entityList  = super.getFacade().getKonkaUserMobileSetPlanService().getKonkaUserMobileSetPlanList(ms);

		for (KonkaUserMobileSetPlan t : entityList) {
			String sp_crc = t.getSetplan_crc();
			if(sp_crc.length() > 10){
				String arr[] = sp_crc.split(",");
				sp_crc = arr[arr.length - 1];
			}
		
			sb.append("{\"setplan_crc\":\"" + String.valueOf(sp_crc) + "\",");
			sb.append("\"name\":\"" + t.getYear() + "年" +t.getMonth() + "月_" + t.getSetplan_name()+ "\"},");
		}
		sb.append("{\"end\":\"\"}]");

		log.info(sb.toString());
		super.renderJson(response, sb.toString());

		return null;
	}
	
	
	public ActionForward useStatusProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String mobile_no = (String) dynaBean.get("mobile_no");
		String use_status = (String) dynaBean.get("use_status");
		if (StringUtils.isNotBlank(id)) {
			KonkaUserMobile ms = new KonkaUserMobile();
			ms.setId(Long.valueOf(id));
			ms.setIs_del(0L);
			ms.setUse_status(Long.valueOf(use_status));
			
			// 登录用户信息
			PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
			ms.setUpdate_uid(peProdUser.getId());
			ms.setUpdate_date(new Date());
			super.getFacade().getKonkaUserMobileService().modifyKonkaUserMobile(ms);
			String msg = super.getMessage(request, "entity.unEnable", new String[] { mobile_no });
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");		
		}
		return this.list(mapping, form, request, response);
	}
	
	public ActionForward validateMobile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		StringBuffer oper = new StringBuffer("{\"result\":");
		String mobile_no = (String) dynaBean.get("mobile_no");
		KonkaUserMobile ms = new KonkaUserMobile();
		ms.setMobile_no(mobile_no);
		ms.setIs_del(0L);
		ms.setUse_status(1L);
		Long count = super.getFacade().getKonkaUserMobileService().getKonkaUserMobileCount(ms);
		if ("0".equals(count.toString())) {
			oper.append(false);
		} else {
			oper.append(true);
		}
		oper.append("}");
		super.render(response, oper.toString(), "text/x-json;charset=UTF-8");
		return null;
	}
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;	
		String id = (String) dynaBean.get("id");
		
		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaUserMobile mobile = new KonkaUserMobile();
			mobile.setId(new Long(id));
			mobile.setIs_del(1L);
			getFacade().getKonkaUserMobileService().modifyKonkaUserMobile(mobile);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
}
