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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ebiz.mmt.domain.KonkaUserMobileSet;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Cheng,Bing
 * @version 2012-01-09
 */
public class KonkaYwyMobileSetAction extends BaseAction {

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
		String entp_name_like = (String) dynaBean.get("entp_name_like");
		String entp_crc_like = (String) dynaBean.get("entp_crc_like");
		KonkaUserMobileSet mt = new KonkaUserMobileSet();
		if(StringUtils.isNotBlank(entp_name_like)){
			mt.setEntp_name(entp_name_like);
		}
		
		if(StringUtils.isNotBlank(entp_crc_like)){
			mt.setEntp_crc(entp_crc_like);
		}
		
		mt.setIs_del(0L);
	
		Long recordCount = super.getFacade().getKonkaUserMobileSetService().getKonkaUserMobileSetCount(mt);
		pager.init(recordCount, 10, pager.getRequestPage());
		mt.getRow().setFirst(pager.getFirstRow());
		mt.getRow().setCount(pager.getRowCount());
		List<KonkaUserMobileSet> mtList = super.getFacade().getKonkaUserMobileSetService().getKonkaUserMobileSetPaginatedList(mt);
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
		
		KonkaUserMobileSet mt = new KonkaUserMobileSet();
		mt.setId(Long.valueOf(id));
		mt = super.getFacade().getKonkaUserMobileSetService().getKonkaUserMobileSet(mt);
		super.copyProperties(form, mt);
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
		String entp_name = (String) dynaBean.get("entp_name");
		String soft_url = (String) dynaBean.get("soft_url");
		String config_url = (String) dynaBean.get("config_url");
		String gps_url = (String) dynaBean.get("gps_url");
		// 登录用户信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		KonkaUserMobileSet mt = new KonkaUserMobileSet();
		if(StringUtils.isNotBlank(id)){
			
			mt.setSoft_url(soft_url);
			mt.setConfig_url(config_url);
			mt.setGps_url(gps_url);
			mt.setUpdate_uid(peProdUser.getId());
			mt.setUpdate_date(new Date());
			
			super.getFacade().getKonkaUserMobileSetService().modifyKonkaUserMobileSet(mt);
		}else{	
			mt.setEntp_name(entp_name);
			mt.setSoft_url(soft_url);
			mt.setConfig_url(config_url);
			mt.setGps_url(gps_url);
			
			Date d = new Date();
			String randString = "";
			while(true){			
				randString = createReferCode(entp_name,d);
				
				KonkaUserMobileSet tmp = new KonkaUserMobileSet();
				tmp.setEntp_crc(randString);
				tmp.setIs_del(0L);
				tmp = super.getFacade().getKonkaUserMobileSetService().getKonkaUserMobileSet(tmp);
				if(tmp == null) break;
				d = new Date();
			}
			
			mt.setCreate_date(d);
			mt.setCreate_uid(peProdUser.getId());
			mt.setUpdate_uid(peProdUser.getId());
			mt.setUpdate_date(d);
			
			mt.setEntp_crc(randString);
			
			super.getFacade().getKonkaUserMobileSetService().createKonkaUserMobileSet(mt);
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
