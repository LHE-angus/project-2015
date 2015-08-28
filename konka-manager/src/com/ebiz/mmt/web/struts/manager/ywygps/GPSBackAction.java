package com.ebiz.mmt.web.struts.manager.ywygps;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaUserGpsInfo;
import com.ebiz.mmt.domain.KonkaUserMobile;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.CrcUtil;
import com.ebiz.mmt.web.util.Encrypt;
import com.ebiz.mmt.web.util.IpUtils;

/**
 * @author Cheng,Bing
 * @version 2012-01-09
 */
public class GPSBackAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String no = (String) dynaBean.get("no");
		String x = (String) dynaBean.get("x");// 经度
		String y = (String) dynaBean.get("y");// 纬度
		String z = (String) dynaBean.get("z");// 纬度
		String idc = (String) dynaBean.get("idc");// 纬度
		if (StringUtils.isNotBlank(no) && StringUtils.isNotBlank(x) && StringUtils.isNotBlank(y)
				&& StringUtils.isNotBlank(z) && StringUtils.isNotBlank(idc)) {
			if (CrcUtil.CheckVerifyCode(no, x, y, z, idc)) {
				// 2.解密数据
				String t_no = Encrypt.decrypt(no, null);
				String t_x = Encrypt.decrypt(x, null);
				String t_y = Encrypt.decrypt(y, null);
				String crc = z.substring(20) + idc;
				String ip = IpUtils.getIpAddr(request);
				// 接收手机GPS数据
				return this.createUserGPSInfo(response, t_no, t_x, t_y, convertTimeStr(z.substring(0, 19)), crc, ip);
			} else {
				// 违法数据拒绝处理
				// // //System.out.println("没有通过校验！");
				return null;
			}

		}
		return null;
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String sm = (String) dynaBean.get("sm");

		if (StringUtils.isBlank(sm)) {
			dynaBean.set("show_map_type", "0");// 默认显示百度地图
			return mapping.findForward("list");
		}
		return mapping.findForward("list");
	}

	public ActionForward createUserGPSInfo(HttpServletResponse response, String mobile_no, String longitude,
			String latitude, Date send_time, String crc, String ip) throws Exception {
		String status = "{\"status\":\"0\"}";
		// 找出提交手机好的人员
		KonkaUserMobile kUser = new KonkaUserMobile();
		kUser.setMobile_no(mobile_no);
		kUser.setIs_activate(1L);
		kUser.setIs_del(0L);
		List<KonkaUserMobile> list = super.getFacade().getKonkaUserMobileService().getKonkaUserMobileList(kUser);
		// 是康佳的业务员才可以提交GPS数据
		if (null != list && list.size() > 0) {
			KonkaUserGpsInfo gpsInfo = new KonkaUserGpsInfo();
			gpsInfo.setMobile_no(mobile_no);
			gpsInfo.setGps_longitude(longitude);
			gpsInfo.setGps_latitude(latitude);
			gpsInfo.setGps_type(0L);
			gpsInfo.setSend_time(send_time);
			gpsInfo.setUpdate_time(new Date());
			gpsInfo.setRandCRC(crc);
			gpsInfo.setIp(ip);
			super.getFacade().getKonkaUserGpsInfoService().createKonkaUserGpsInfo(gpsInfo);
			status = "{\"status\":\"1\"}";
		}

		super.render(response, status, "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward updateUserGPSInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		// 参数获取
		String gps_type = (String) dynaBean.get("gps_type");
		String gps_id = (String) dynaBean.get("gps_id");
		String latitude = (String) dynaBean.get("latitude");
		String longitude = (String) dynaBean.get("longitude");
		String address = (String) dynaBean.get("address");

		String status = "{\"status\":\"0\"}";
		if (GenericValidator.isLong(gps_id)) {
			KonkaUserGpsInfo gpsInfo = new KonkaUserGpsInfo();
			gpsInfo.setId(Long.valueOf(gps_id));
			if ("0".equals(gps_type)) {
				if (StringUtils.isNotBlank(address)) {
					address = java.net.URLDecoder.decode(address, "utf-8");
					gpsInfo.setAddress(address);
				}
				gpsInfo.setBlongitude(longitude);
				gpsInfo.setBlatitude(latitude);
				if (longitude != null && latitude != null)
					gpsInfo.setGps_type(1L);
			} else {
				super.render(response, status, "text/x-json;charset=UTF-8");
				return null;
			}
			status = "{\"status\":\"1\",\"addr\":\"" + address + "\"}";
			super.getFacade().getKonkaUserGpsInfoService().modifyKonkaUserGpsInfo(gpsInfo);
		}

		super.render(response, status, "text/x-json;charset=UTF-8");
		return null;
	}
}
