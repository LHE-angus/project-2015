package com.ebiz.mmt.web.struts.manager.ywygps;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaUserInvalidMobile;
import com.ebiz.mmt.domain.KonkaUserMobile;
import com.ebiz.mmt.domain.KonkaUserMobileSet;
import com.ebiz.mmt.domain.KonkaUserMobileSetPlan;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.CrcUtil;
import com.ebiz.mmt.web.util.Encrypt;
import com.ebiz.mmt.web.util.IpUtils;

/**
 * @author Cheng,Bing
 * @version 2012-01-09
 */
public class GPSConfigAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		/* 初始安装或变更，确认手机持有者身份  */
		String no = (String) dynaBean.get("no");
		String rx = (String) dynaBean.get("rx");//公司确认码
		String ry = (String) dynaBean.get("ry");//业务员确认码
		String rt = (String) dynaBean.get("rt");//发送时间
		String idc = (String) dynaBean.get("idc");//校验码
		if (StringUtils.isNotBlank(no) && StringUtils.isNotBlank(rx)
				&& StringUtils.isNotBlank(ry) && StringUtils.isNotBlank(rt)
				&& StringUtils.isNotBlank(idc)) {
			if(CrcUtil.CheckVerifyCode(no, rx, ry, rt, idc)){
				// 2.解密数据
				String t_no = Encrypt.decrypt(no, null);
				String t_x = Encrypt.decrypt(rx, null);
				String t_y = Encrypt.decrypt(ry, null);
				String crc = rt.substring(20) + idc;
				String ip = IpUtils.getIpAddr(request);
				// 确认手机持有者身份
				return this.confirmUserMobile(response, t_no, t_x, t_y , convertTimeStr(rt.substring(0,19)), crc , ip);
			}else{
				// 违法数据拒绝处理
				//// //System.out.println("没有通过校验！");
				return null;
			}  
		}
		
		/* 手机端配置更新  */
		String cx = (String) dynaBean.get("cx"); //设置方案确认码
		String cy = (String) dynaBean.get("cy"); //版本号
		String ct = (String) dynaBean.get("ct"); //发送时间
		if (StringUtils.isNotBlank(no) && StringUtils.isNotBlank(cx)
				&& StringUtils.isNotBlank(cy) && StringUtils.isNotBlank(ct)
				&& StringUtils.isNotBlank(idc)) {
			if(CrcUtil.CheckVerifyCode(no, cx, cy, ct, idc)){
				// 2.解密数据
				String t_no = Encrypt.decrypt(no, null);
				String t_x = Encrypt.decrypt(cx, null);
				String t_y = Encrypt.decrypt(cy, null);
				String crc = ct.substring(20) + idc;
				String ip = IpUtils.getIpAddr(request);
				// 手机端配置更新
				return this.updateMobileConfig(response, t_no, t_x, t_y , convertTimeStr(ct.substring(0,19)), crc , ip);
			}else{
				// 违法数据拒绝处理
				//// //System.out.println("没有通过校验！");
				return null;
			}  
		}
		return null;
	}
	
	public ActionForward confirmUserMobile(HttpServletResponse response, String mobile_no, String entp_crc, String ywy_crc,
			Date send_time, String crc, String ip) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		// 核对确认码
		KonkaUserMobile um = new KonkaUserMobile();
		um.setMobile_no(mobile_no);
		um.setEntp_crc(entp_crc);
		um.setYwy_crc(ywy_crc);
		um.setIs_del(0L);
		um.setUse_status(0L);
		um = super.getFacade().getKonkaUserMobileService().getKonkaUserMobile(um);
		if(um != null){
			if(Long.valueOf(0L).equals(um.getIs_activate())){
			    // 公司手机设置
				KonkaUserMobileSet ms = new KonkaUserMobileSet();
				ms.setEntp_crc(entp_crc);
				ms.setIs_del(0L);
				ms = super.getFacade().getKonkaUserMobileSetService().getKonkaUserMobileSet(ms);

				// 公司手机设置--月度设置方案
				KonkaUserMobileSetPlan msp = new KonkaUserMobileSetPlan();
				msp.setSetplan_crc(um.getSetplan_crc());
				msp.setIs_del(0L);
				msp = super.getFacade().getKonkaUserMobileSetPlanService().getKonkaUserMobileSetPlan(msp);
				
				// 更新确认
			    um.setIs_activate(1L);
			    um.setUse_status(1L);
			    um.setConfirm_date(send_time);
			    um.setUpdate_date(new Date());
			    super.getFacade().getKonkaUserMobileService().modifyKonkaUserMobile(um);
			    model.put("ret_code", 1);
			    model.put("msg", "恭喜,您已通过公司确认！");
				model.put("servers", ms);
				model.put("mobile", um);
				model.put("config", msp);
			}else{
			    model.put("ret_code", 0);
			    model.put("msg", "[EC001],对不起,您的确认码已失效！");
			}
		}else{
			// 未通过确认，手机端发送信息存放到未激活信息表
		    // 公司手机设置
			KonkaUserMobileSet ms = new KonkaUserMobileSet();
			ms.setEntp_crc(entp_crc);
			ms.setIs_del(0L);
			ms = super.getFacade().getKonkaUserMobileSetService().getKonkaUserMobileSet(ms);
			if(ms == null){
			    model.put("ret_code", 0);
			    model.put("msg", "[EC002],对不起,您安装的手机端非公司提供或已过期,请联系管理员！");
			}else{
			    PeProdUser pu = new PeProdUser();
			    pu.setLink_phone(mobile_no);
			    pu.setIs_del(0);
				List<PeProdUser> userInfoList = getFacade().getPeProdUserService().getPeProdUserList(pu);

		    	// 确认未通过信息
		    	KonkaUserInvalidMobile kui = new KonkaUserInvalidMobile();
                // 生成业务员确认码
				Date d = new Date();
				String randString = createReferCode(mobile_no,d);
				kui.setYwy_crc(randString);
		    	kui.setMobile_no(mobile_no);
			    if(userInfoList != null && userInfoList.size() > 0){
			    	pu = userInfoList.get(0);
			    	kui.setUser_id(Long.valueOf(pu.getId()));
			    }
		    	kui.setEntp_crc(entp_crc);

		    	// 当前年月获取
				Calendar cal = Calendar.getInstance(Locale.CHINA); // 日历对象
				int year = cal.get(Calendar.YEAR);//获取年份
			    int month = cal.get(Calendar.MONTH)+ 1;//获取月份
			    
		    	// 月度设置方案---默认取当前年、当前月份的设置方案
				KonkaUserMobileSetPlan msp = new KonkaUserMobileSetPlan();
				msp.setS_id(ms.getId());
				msp.setYear(Long.valueOf(year));
				msp.setMonth(Long.valueOf(month));
				msp.setIs_del(0L);
				List<KonkaUserMobileSetPlan> mspList = super.getFacade().getKonkaUserMobileSetPlanService().getKonkaUserMobileSetPlanList(msp);
				if(mspList != null){
					msp = mspList.get(0);
					kui.setSetplan_crc(msp.getSetplan_crc());
			    	kui.setIs_del(0L);
			    	super.getFacade().getKonkaUserInvalidMobileService().createKonkaUserInvalidMobile(kui);

					model.put("servers", ms);
					model.put("mobile", kui);
					model.put("config", msp);
				    model.put("ret_code", 1);
				    model.put("msg", "个人确认码有误,请联系管理员激活！");					    
				}else{
				    model.put("ret_code", 0);
				    model.put("msg", "[EC003],对不起,系统没有找到对应设置,请联系管理员！");
				}
		
			}  
		}
		response.setHeader("Content-Disposition", "attachment;filename=config.xml");
		String xmlString = getFacade().getTemplateService().getContent("ywygps/config.xml.ftl", model);
		super.renderXml(response, xmlString);
		return null;
	}
	
	public ActionForward updateMobileConfig(HttpServletResponse response, String mobile_no, String setplan_crc, String version,
			Date send_time, String crc, String ip) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		boolean flg = false;
		// 核对确认码
		KonkaUserMobile um = new KonkaUserMobile();
		um.setMobile_no(mobile_no);
		um.setIs_activate(1L);
		um.setUse_status(1L);
		um.setIs_del(0L);
		List<KonkaUserMobile> umList = super.getFacade().getKonkaUserMobileService().getKonkaUserMobileList(um);
		if(umList != null && umList.size() > 0){
			um = umList.get(0);
			String sp_crc = um.getSetplan_crc();
			if(sp_crc != null){
				if(sp_crc.length() > 8){
					String arr[] = sp_crc.split(",");
					if(setplan_crc.equals(arr[arr.length - 1])){
						flg = true;
					}
				}else{
					if(setplan_crc.equals(sp_crc)){
						flg = true;
					}
				}
			}
		}
			
		if(flg){
		    // 公司手机设置
			KonkaUserMobileSet ms = new KonkaUserMobileSet();
			ms.setEntp_crc(um.getEntp_crc());
			ms.setIs_del(0L);
			ms = super.getFacade().getKonkaUserMobileSetService().getKonkaUserMobileSet(ms);
		
			Calendar cal = Calendar.getInstance(Locale.CHINA); // 日历对象
			int year = cal.get(Calendar.YEAR);//获取年份
		    int month = cal.get(Calendar.MONTH);//获取月份
			
		    String yearMonth = year + "" + (month + 1);
		    
			// 公司手机设置--月度设置方案
			KonkaUserMobileSetPlan msp = new KonkaUserMobileSetPlan();
			msp.setSetplan_crc(um.getSetplan_crc());
			msp.setIs_del(0L);
			msp = super.getFacade().getKonkaUserMobileSetPlanService().getKonkaUserMobileSetPlan(msp);
			if(msp != null){
				if(yearMonth.equals(msp.getYear()+""+msp.getMonth())){
					Double ver1 = Double.valueOf(version);
					Double ver2 = Double.valueOf(msp.getVersion());
					if(ver2 > ver1){
					    model.put("ret_code", 1);
					    model.put("msg", "恭喜,您手机的对应配置已成功更新！");
						model.put("servers", ms);
						model.put("mobile", um);
						model.put("config", msp);
					}else{
					    model.put("ret_code", 2);
					    model.put("msg", "您手机的对应配置已是最新版！");
					}
			    }else{
			    	if(msp.getNext_id() != null){
						KonkaUserMobileSetPlan next_msp = new KonkaUserMobileSetPlan();
						next_msp.setId(msp.getNext_id());
						next_msp.setIs_del(0L);
						next_msp = super.getFacade().getKonkaUserMobileSetPlanService().getKonkaUserMobileSetPlan(next_msp);
						if(yearMonth.equals(msp.getYear() + "" + next_msp.getMonth())){
						    model.put("ret_code", 1);
						    model.put("msg", "恭喜,您手机的对应配置已成功更新！");
							model.put("servers", ms);
							model.put("mobile", um);
							model.put("config", next_msp);

							String sp_crc = um.getSetplan_crc()+","+next_msp.getSetplan_crc();
							um.setSetplan_crc(sp_crc);
							super.getFacade().getKonkaUserMobileService().modifyKonkaUserMobile(um);
						}else{
						    model.put("ret_code", 0);
						    model.put("msg", "[EC005],对不起,找不到您手机的对应设置,请联系管理员！");
						}						
			    	}else{
					    model.put("ret_code", 0);
					    model.put("msg", "[EC006],对不起,找不到您手机的对应设置,请联系管理员！");
			    	}
			    }
			}else{
			    model.put("ret_code", 0);
			    model.put("msg", "[EC007],对不起,找不到您手机的对应设置,请联系管理员！");
			}
			
			// 更新确认
		    um.setIs_activate(1L);
		    um.setConfirm_date(send_time);
		    um.setUpdate_date(new Date());
		    super.getFacade().getKonkaUserMobileService().modifyKonkaUserMobile(um);

		}else{
		    model.put("ret_code", 0);
		    model.put("msg", "[EC008],对不起,您的手机号码不存在！");
		}
		response.setHeader("Content-Disposition", "attachment;filename=config.xml");
		String xmlString = getFacade().getTemplateService().getContent("ywygps/config.xml.ftl", model);
		super.renderXml(response, xmlString);
		return null;
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
