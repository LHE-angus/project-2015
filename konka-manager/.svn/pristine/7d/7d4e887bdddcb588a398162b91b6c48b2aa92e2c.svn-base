package com.ebiz.mmt.web.struts.manager.ywygps;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaUserGpsInfo;
import com.ebiz.mmt.domain.KonkaUserMobile;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Cheng,Bing
 * @version 2012-02-14
 */
public class KonkaYwyGPSAction extends BaseAction {

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
		request.setAttribute("sybDeptInfoList", super.getDeptInfoList(mapping, form, request, response, null));

		String sm = (String) dynaBean.get("sm");

		if (StringUtils.isBlank(sm)) {
			dynaBean.set("show_map_type", "0");// 默认显示百度地图
			return mapping.findForward("list");
		}
		return mapping.findForward("list");
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

	public List<KonkaUserGpsInfo> getUserGPSList(String mobile_no, String mobile_nos) throws Exception {
		KonkaUserGpsInfo gpsInfo = new KonkaUserGpsInfo();

		if (StringUtils.isBlank(mobile_no)) {
			gpsInfo.getMap().put("mobile_nos", StringUtils.split(mobile_nos, ","));
		} else {
			gpsInfo.getMap().put("mobile_nos", new String[] { mobile_no });
		}
		return getFacade().getKonkaUserGpsInfoService().getKonkaUserGpsInfoList(gpsInfo);
	}

	public ActionForward ajaxGetUserList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String ywy_name_like = (String) dynaBean.get("ywy_name_like");
		String ywy_phone = (String) dynaBean.get("ywy_phone");
		String update_time_st = (String) dynaBean.get("update_time_st");
		String update_time_en = (String) dynaBean.get("update_time_en");
		String fgs_dept_id = (String) dynaBean.get("fgs_dept_id");
		String jyb_dept_id = (String) dynaBean.get("jyb_dept_id");
		String bsc_dept_id = (String) dynaBean.get("bsc_dept_id");
		String ywy_user_id = (String) dynaBean.get("ywy_user_id");

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		if (StringUtils.isNotBlank(ywy_name_like)) {
			ywy_name_like = java.net.URLDecoder.decode(ywy_name_like, "utf-8");
		}

		// String firstRow = (String) dynaBean.get("firstRow");
		// String pageSize = (String) dynaBean.get("pageSize");
		// 部门人员列表
		KonkaUserMobile kUser = new KonkaUserMobile();
		kUser.setIs_del(0L);
		kUser.setUse_status(1L);

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_20_lt_60 = false;
		boolean role_id_eq_60 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() >= 20L && peRoleUser.getRole_id() < 60L) {
				role_id_ge_20_lt_60 = true;
			}
			if (peRoleUser.getRole_id() == 60L) {
				role_id_eq_60 = true;
			}
		}

		if (role_id_ge_20_lt_60) { // 不是系统管理员组全部要加上网点分配
			kUser.getMap().put("dept_id", this.getSessionUserInfo(request).getDept_id());
			kUser.getMap().put("user_id", this.getSessionUserInfo(request).getId());
		}
		if (role_id_eq_60) { // 业务员只能看到分配给自己的
			kUser.getMap().put("ywy_user_id", this.getSessionUserInfo(request).getId());
		}
		// 查询条件
		kUser.getMap().put("ywy_name_like", ywy_name_like);
		kUser.getMap().put("ywy_phone", ywy_phone);
		if (StringUtils.isNotBlank(update_time_st)) {
			kUser.getMap().put("update_time_st", update_time_st);
		}
		if (StringUtils.isNotBlank(update_time_en)) {
			kUser.getMap().put("update_time_en", update_time_en);
		}
		if (GenericValidator.isLong(fgs_dept_id)) {
			kUser.getMap().put("konka_dept_id", fgs_dept_id);
		}
		if (GenericValidator.isLong(jyb_dept_id)) {
			kUser.getMap().put("konka_dept_id", jyb_dept_id);
		}
		if (GenericValidator.isLong(bsc_dept_id)) {
			kUser.getMap().put("konka_dept_id", bsc_dept_id);
		}
		if (GenericValidator.isLong(ywy_user_id)) {
			kUser.getMap().put("ywy_user_id", ywy_user_id);
		}

		// Long recordCount =
		// super.getFacade().getKonkaUserMobileService().getKonkaUserMobileCount(kUser);
		// kUser.getRow().setFirst(Integer.valueOf(firstRow) - 1);
		// kUser.getRow().setCount(Integer.valueOf(pageSize));
		List<KonkaUserMobile> mtList = super.getFacade().getKonkaUserMobileService().getKonkaUserMobileList(kUser);

		StringBuffer jsonBuffer = new StringBuffer();
		int first_gps_person = -1;
		if (null != mtList && mtList.size() > 0) {
			int back = 0;// 页面是否需要回传信息0:不回传,1：回传
			for (int i = 0; i < mtList.size(); i++) {
				KonkaUserMobile ywy = mtList.get(i);
				String ywy_name = "--";
				String gps_id = "--";
				String gps_type = "--";
				String mobile_no = "--"; // 在Konka_User_Gps_Info表中作为主键，存放user_name值
				String longitude = "--";
				String latitude = "--";
				String speed = "--";
				String signal_strength = "--";
				String update_time = "--";
				String address = "--";
				String phone = "--";
				String duty = "";
				String dept_name = "--";
				if (ywy.getUser_id() != null) {
					List<PeRoleInfo> peRoleInfoList = super.getPeRoleInfoListByUserId(ywy.getUser_id());
					if (null != peRoleInfoList && peRoleInfoList.size() > 0) {
						for (PeRoleInfo temp : peRoleInfoList) {
							duty = duty + temp.getRole_name() + ",";
						}
					}
					if ("".equals(duty)) {
						duty = "--";
					} else {
						duty = StringUtils.removeEnd(duty, ",");
					}

					phone = ywy.getMobile_no();

					KonkaDept konkaDept = new KonkaDept();
					konkaDept.setDept_id(ywy.getDept_id());
					konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
					if (null != konkaDept) {
						dept_name = konkaDept.getDept_name();
					}

					PeProdUser ui = new PeProdUser();
					ui.setId(ywy.getUser_id());
					ui = super.getFacade().getPeProdUserService().getPeProdUser(ui);
					mobile_no = ui.getUser_name();
					PeProdUser pu = new PeProdUser();
					pu.setId(ywy.getUser_id());
					pu = super.getFacade().getPeProdUserService().getPeProdUser(pu);
					if (pu != null) {
						ywy_name = pu.getReal_name();
					}

					List<KonkaUserGpsInfo> gpsInfoList = getUserGPSList(mobile_no, null);
					if (null != gpsInfoList && gpsInfoList.size() > 0) {
						KonkaUserGpsInfo gps = gpsInfoList.get(0);
						gps_id = "" + gps.getId();
						if (gps.getGps_type().equals(0L)) {
							back = 1;
							address = "系统正在获取业务员位置...";
							longitude = gps.getGps_longitude();
							latitude = gps.getGps_latitude();
							gps_type = "0";
						} else {
							address = gps.getAddress();
							longitude = gps.getBlongitude();
							latitude = gps.getBlatitude();
							gps_type = "1";
						}
						if (first_gps_person == -1)
							first_gps_person = i;
						speed = gps.getSpeed();
						signal_strength = "" + gps.getSignal_strength();
						update_time = DateFormatUtils.format(gps.getUpdate_time(), "yyyy-MM-dd HH:mm:ss");
					}
				}

				jsonBuffer.append("{");
				jsonBuffer.append("\"ywy_name\":\"").append(ywy_name);
				jsonBuffer.append("\",\"gps_id\":\"").append(gps_id);
				jsonBuffer.append("\",\"mobile_no\":\"").append(mobile_no);
				jsonBuffer.append("\",\"phone\":\"").append(phone);
				jsonBuffer.append("\",\"duty\":\"").append(duty);
				jsonBuffer.append("\",\"dept_name\":\"").append(dept_name);
				jsonBuffer.append("\",\"gps_type\":\"").append(gps_type);
				jsonBuffer.append("\",\"longitude\":\"").append(longitude);
				jsonBuffer.append("\",\"latitude\":\"").append(latitude);
				jsonBuffer.append("\",\"speed\":\"").append(speed);
				jsonBuffer.append("\",\"signal_strength\":\"").append(signal_strength);
				jsonBuffer.append("\",\"update_time\":\"").append(update_time);
				jsonBuffer.append("\",\"address\":\"").append(address);
				jsonBuffer.append("\",\"back\":\"").append(back);
				jsonBuffer.append("\"},");
			}

		}
		String listStr = StringUtils.removeEnd(jsonBuffer.toString(), ",");
		StringBuffer json = new StringBuffer("{");
		json.append("\"list\" : [").append(listStr).append("],");
		// json.append("\"count\":\"").append(recordCount).append("\",");
		json.append("\"first_gps_person\":\"").append(first_gps_person).append("\"");
		json.append("}");
		logger.info("JSON Output : [{}]", json);
		super.render(response, json.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward ajaxGetUserAllList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String ywy_name_like = (String) dynaBean.get("ywy_name_like");
		String ywy_phone = (String) dynaBean.get("ywy_phone");
		String update_time_st = (String) dynaBean.get("update_time_st");
		String update_time_en = (String) dynaBean.get("update_time_en");
		String fgs_dept_id = (String) dynaBean.get("fgs_dept_id");
		String jyb_dept_id = (String) dynaBean.get("jyb_dept_id");
		String bsc_dept_id = (String) dynaBean.get("bsc_dept_id");
		String ywy_user_id = (String) dynaBean.get("ywy_user_id");

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		if (StringUtils.isNotBlank(ywy_name_like)) {
			ywy_name_like = java.net.URLDecoder.decode(ywy_name_like, "utf-8");
		}

		// String firstRow = (String) dynaBean.get("firstRow");
		// String pageSize = (String) dynaBean.get("pageSize");
		// 部门人员列表
		KonkaUserMobile kUser = new KonkaUserMobile();
		kUser.setIs_del(0L);
		kUser.setUse_status(1L);

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_20_lt_60 = false;
		boolean role_id_eq_60 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() >= 20L && peRoleUser.getRole_id() < 60L) {
				role_id_ge_20_lt_60 = true;
			}
			if (peRoleUser.getRole_id() == 60L) {
				role_id_eq_60 = true;
			}
		}

		if (role_id_ge_20_lt_60) { // 不是系统管理员组全部要加上网点分配
			kUser.getMap().put("dept_id", this.getSessionUserInfo(request).getDept_id());
			kUser.getMap().put("user_id", this.getSessionUserInfo(request).getId());
		}
		if (role_id_eq_60) { // 业务员只能看到分配给自己的
			kUser.getMap().put("ywy_user_id", this.getSessionUserInfo(request).getId());
		}
		// 查询条件
		kUser.getMap().put("ywy_name_like", ywy_name_like);
		kUser.getMap().put("ywy_phone", ywy_phone);
		if (StringUtils.isNotBlank(update_time_st)) {
			kUser.getMap().put("update_time_st", update_time_st);
		}
		if (StringUtils.isNotBlank(update_time_en)) {
			kUser.getMap().put("update_time_en", update_time_en);
		}
		if (GenericValidator.isLong(fgs_dept_id)) {
			kUser.getMap().put("konka_dept_id", fgs_dept_id);
		}
		if (GenericValidator.isLong(jyb_dept_id)) {
			kUser.getMap().put("konka_dept_id", jyb_dept_id);
		}
		if (GenericValidator.isLong(bsc_dept_id)) {
			kUser.getMap().put("konka_dept_id", bsc_dept_id);
		}
		if (GenericValidator.isLong(ywy_user_id)) {
			kUser.getMap().put("ywy_user_id", ywy_user_id);
		}

		List<KonkaUserMobile> mtList = super.getFacade().getKonkaUserMobileService()
				.getKonkaUserMobileAndGpsList(kUser);

		StringBuffer jsonBuffer = new StringBuffer();
		if (null != mtList && mtList.size() > 0) {
			// int back = 0;// 页面是否需要回传信息0:不回传,1：回传
			for (KonkaUserMobile ywy : mtList) {

				jsonBuffer.append("{");
				jsonBuffer.append("\"user_name\":\"").append(ywy.getUser_name());
				jsonBuffer.append("\",\"ywy_name\":\"").append(ywy.getReal_name());
				jsonBuffer.append("\",\"full_name\":\"").append(ywy.getMap().get("full_name"));
				jsonBuffer.append("\",\"duty\":\"").append(ywy.getMap().get("duty"));
				jsonBuffer.append("\",\"mobile_no\":\"").append(ywy.getMobile_no());
				jsonBuffer.append("\",\"gps_type\":\"").append(
						ywy.getMap().get("gps_type") == null ? "--" : ywy.getMap().get("gps_type"));
				jsonBuffer.append("\",\"longitude\":\"").append(
						ywy.getMap().get("blongitude") == null ? "--" : ywy.getMap().get("blongitude"));
				jsonBuffer.append("\",\"latitude\":\"").append(
						ywy.getMap().get("blatitude") == null ? "--" : ywy.getMap().get("blatitude"));
				jsonBuffer.append("\",\"update_time\":\"").append(
						ywy.getMap().get("update_time") == null ? "--" : ywy.getMap().get("update_time"));
				jsonBuffer.append("\",\"address\":\"").append(
						ywy.getMap().get("address") == null ? "--" : ywy.getMap().get("address"));
				jsonBuffer.append("\"},");
			}

		}
		String listStr = StringUtils.removeEnd(jsonBuffer.toString(), ",");
		StringBuffer json = new StringBuffer("{");
		json.append("\"status\":\"").append(1).append("\",");
		json.append("\"list\" : [").append(listStr).append("]");
		json.append("}");
		logger.info("JSON Output : [{}]", json);
		super.render(response, json.toString(), "text/x-json;charset=UTF-8");
		return null;
	}
}
