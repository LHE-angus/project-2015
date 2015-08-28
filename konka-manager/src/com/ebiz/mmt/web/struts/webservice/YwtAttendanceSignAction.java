package com.ebiz.mmt.web.struts.webservice;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ebiz.mmt.domain.KonkaBaseTypeData;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.domain.YwtAttendanceSign;
import com.ebiz.mmt.domain.YwtAttendanceSignTime;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.mmt.web.util.StringHelper;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Pan,Gang
 * @version 2013-09-24
 */
public class YwtAttendanceSignAction extends BaseAction {

	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Map<String, Object> allmap = new HashMap<String, Object>();
		// 默认显示当前1月的时间区间
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String day_first = theFirstDayOfCurrentMonth();
		String day_last = df.format(today);
        allmap.put("_sign_in_date", day_first);
        allmap.put("_sign_out_date", day_last);
        allmap.put("_is_lock", 0);

		// 位置信息
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String user_id = (String) dynaBean.get("user_id");
		// 密码
		String userpass = (String) dynaBean.get("userpass");
		String from_html = (String) dynaBean.get("from_html");
		
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		//web端取session
		PeProdUser userInfo = null;
		
		if (null==userInfo) {
			userInfo = (PeProdUser) super.getSessionAttribute(request,
					Constants.USER_INFO);
		}
		if (null==userInfo) {
			//手机端通过用户查
			userInfo = checkUserid(user_id, userpass,android_version,ios_version);
		}
		
		if (null == userInfo) {
			return null;
		}
		String naviString = "";
		if (StringUtils.isNotBlank(mod_id)) {
			SysModule sysModule = new SysModule();
			sysModule.getMap().put("mod_id", mod_id);
			List<SysModule> sysModuleList = getFacade().getSysModuleService().getSysModuleList(sysModule);
			naviString = StringHelper.getNaviString(sysModuleList, " > ");
		}
		
		YwtAttendanceSignTime fgsSignTime=new YwtAttendanceSignTime();
		int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
		if (null!=dept_fgs&&null!=dept_fgs.getDept_id()) {
			fgsSignTime.setDept_id(dept_fgs.getDept_id());
		}
		SimpleDateFormat df1 = new SimpleDateFormat("HH:mm");
		List<YwtAttendanceSignTime> list=super.getFacade().getYwtAttendanceSignTimeService().getYwtAttendanceSignTimeSignTimeList(fgsSignTime);
		fgsSignTime=list.get(0);
		allmap.put("am_sign_in", df1.format(fgsSignTime.getAm_sign_in_date()));
		allmap.put("am_sign_out", df1.format(fgsSignTime.getAm_sign_out_date()));
		allmap.put("pm_sign_in", df1.format(fgsSignTime.getPm_sign_in_date()));
		allmap.put("pm_sign_out", df1.format(fgsSignTime.getPm_sign_out_date()));
		String fgsSignTimeStr = "考勤时间: 上午("
				+ df1.format(fgsSignTime.getAm_sign_in_date()) + "-"
				+ df1.format(fgsSignTime.getAm_sign_out_date()) + ") 下午("
				+ df1.format(fgsSignTime.getPm_sign_in_date()) + "-"
				+ df1.format(fgsSignTime.getPm_sign_out_date()) + ")";
		allmap.put("fgsSignTimeStr", fgsSignTimeStr);
		allmap.put("local_str", naviString);
		allmap.put("user_id",user_id);
		allmap.put("userpass",userpass);
		// 转换为json数据
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
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String mod_id = String.valueOf(dynaBean.get("mod_id"));
		String _fgs_dept_id = (String)dynaBean.get("_fgs_dept_id");
		String _sign_in_date = (String)dynaBean.get("_sign_in_date");
		String _sign_out_date = (String)dynaBean.get("_sign_out_date");
		String isSignInState = (String)dynaBean.get("isSignInState");
		String isSignOutState = (String)dynaBean.get("isSignOutState");
		String _real_name_like = (String)dynaBean.get("_real_name_like");
		String excel_all = (String) dynaBean.get("excel_all");//
		String page =(String) dynaBean.get("page");
		//手机端控制
		String first = (String) dynaBean.get("first");
		String count = (String) dynaBean.get("count");
		// 密码
		String user_id = (String) dynaBean.get("user_id");
		// 密码
		String userpass = (String) dynaBean.get("userpass");
		String from_html = (String) dynaBean.get("from_html");//1表示手机端
		//web端取session
		PeProdUser userInfo = null;
		
		// 封装成JSON字符串
		Map<String, Object> allmap = new HashMap<String, Object>();
		if (null==userInfo) {
			userInfo = (PeProdUser) super.getSessionAttribute(request,
					Constants.USER_INFO);
		}
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		if (null==userInfo) {
			//手机端通过用户查
			userInfo = checkUserid(user_id, userpass,android_version,ios_version);
		}
		
		if (null == userInfo) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		YwtAttendanceSign entity = new YwtAttendanceSign();
        entity.getMap().put("user_id", userInfo.getId());
        
        if (StringUtils.isNotBlank(_fgs_dept_id)) {
        	entity.getMap().put("_fgs_dept_id", _fgs_dept_id);
		}
		if (StringUtils.isNotBlank(_sign_in_date)) {
			entity.getMap().put("_sign_in_date", _sign_in_date);
		}
		if (StringUtils.isNotBlank(_sign_out_date)) {
			entity.getMap().put("_sign_out_date", _sign_out_date);
		}
		
		if(null==isSignInState||"-1".equals(isSignInState)){
		}else{
			entity.getMap().put("isSignInState", isSignInState);
		}
		
        if(null==isSignOutState||"-1".equals(isSignOutState)){
		}else{
			entity.getMap().put("isSignOutState", isSignOutState);
		}
        
		if (StringUtils.isNotBlank(_real_name_like)) {
			entity.getMap().put("_real_name_like", _real_name_like);
		}
		
		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}
		if (flag) {// 如果是业务员，只能看到自己的拜访记录
			entity.setSign_user_id(userInfo.getId());
		} else {
			Long _dept_id = 0L;
			int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
			logger.info("Max level : {}", max_dlevel);
			switch (max_dlevel) {
			case 9:
				// 集团及以下部门可见
				_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
				break;
			case 8:
				// 分公司及以下部门可见
				KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
				if (null != dept_fgs && null != dept_fgs.getDept_id()) {
					_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
					entity.getMap().put("dept_id_start", _dept_id);
					// entity.getMap().put("fgs_dept_value", _dept_id);
				}
				break;
			case 7:
				// 我所在的部门及以下部门可见
				_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
				entity.getMap().put("dept_id_start", _dept_id);
				break;
			case 0:
				_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
				// entity.getMap().put("dept_id_start", __dept_id);
				entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
				break;
			default:
				// 出错
			}
			entity.getMap().put("session_user_id", userInfo.getId());
		}
		Boolean haveRole=null;
		PeRoleUser roleUser=new PeRoleUser();
		roleUser.setUser_id(userInfo.getId());
        List<PeRoleUser> userroles=super.getFacade().getPeRoleUserService().getPeRoleUserList(roleUser);
        for (PeRoleUser peRoleUser : userroles) {
			if (peRoleUser.getRole_id()==34||peRoleUser.getRole_id()==30 || peRoleUser.getRole_id()==135) {
				haveRole=true;
			}
			if (peRoleUser.getRole_id()>=1&&peRoleUser.getRole_id()<=30) {
				haveRole=true;
			}
		}
		List<Map<String, Object>> entityList = null;
		Long recordCount = super.getFacade().getYwtAttendanceSignService().gettYwtAttendanceSignLBCount(entity);
		
		// 导出
		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {

			if (recordCount.intValue() > 20000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
						+ "！');history.back();");
				return null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ EncryptUtils.encodingFileName("考勤记录") + ".xls");
			entity.getRow().setFirst(0);
			entity.getRow().setCount(recordCount.intValue());

			List<Map<String, Object>> entityList1 = super.getFacade().getYwtAttendanceSignService()
					.getYwtAttendanceSignLBPaginatedList(entity);
			for (Map<String, Object> YwtAttendanceSign : entityList1) {
				//Iphone手机型号需转义
				if(null!=YwtAttendanceSign.get("AM_PHONE_MODEL_IN")){
					if("iPhone3,1".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_IN"))){
						YwtAttendanceSign.put("AM_PHONE_MODEL_IN","iPhone 4 (A1332)");
					}
					if("iPhone3,2".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_IN"))){
						YwtAttendanceSign.put("AM_PHONE_MODEL_IN","iPhone 4 (A1332)");
					}
					if("iPhone3,3".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_IN"))){
						YwtAttendanceSign.put("AM_PHONE_MODEL_IN","iPhone 4 (A1349)");
					}
					if("iPhone4,1".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_IN"))){
						YwtAttendanceSign.put("AM_PHONE_MODEL_IN","iPhone 4S (A1387/A1431)");
					}
					if("iPhone5,1".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_IN"))){
						YwtAttendanceSign.put("AM_PHONE_MODEL_IN","iPhone 5 (A1428)");
					}
					if("iPhone5,2".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_IN"))){
						YwtAttendanceSign.put("AM_PHONE_MODEL_IN","iPhone 5 (A1429/A1442)");
					}
					if("iPhone5,3".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_IN"))){
						YwtAttendanceSign.put("AM_PHONE_MODEL_IN","iPhone 5C (A1456/A1532)");
					}
					if("iPhone5,4".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_IN"))){
						YwtAttendanceSign.put("AM_PHONE_MODEL_IN","iPhone 5C (A1507/A1516/A1526/A1529)");
					}
					if("iPhone6,1".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_IN"))){
						YwtAttendanceSign.put("AM_PHONE_MODEL_IN","iPhone 5S (A1453/A1533)");
					}
					if("iPhone6,2".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_IN"))){
						YwtAttendanceSign.put("AM_PHONE_MODEL_IN","iPhone 5S (A1457/A1518/A1528/A1530)");
					}
					if("iPhone7,1".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_IN"))){
						YwtAttendanceSign.put("AM_PHONE_MODEL_IN","iPhone 6 Plus (A1522/A1524)");
					}
					if("iPhone7,2".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_IN"))){
						YwtAttendanceSign.put("AM_PHONE_MODEL_IN","iPhone 6 (A1549/A1586)");
					}
				}
				if(null!=YwtAttendanceSign.get("AM_PHONE_MODEL_OUT")){
					if("iPhone3,1".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_OUT"))){
						YwtAttendanceSign.put("AM_PHONE_MODEL_OUT","iPhone 4 (A1332)");
					}
					if("iPhone3,2".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_OUT"))){
						YwtAttendanceSign.put("AM_PHONE_MODEL_OUT","iPhone 4 (A1332)");
					}
					if("iPhone3,3".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_OUT"))){
						YwtAttendanceSign.put("AM_PHONE_MODEL_OUT","iPhone 4 (A1349)");
					}
					if("iPhone4,1".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_OUT"))){
						YwtAttendanceSign.put("AM_PHONE_MODEL_OUT","iPhone 4S (A1387/A1431)");
					}
					if("iPhone5,1".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_OUT"))){
						YwtAttendanceSign.put("AM_PHONE_MODEL_OUT","iPhone 5 (A1428)");
					}
					if("iPhone5,2".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_OUT"))){
						YwtAttendanceSign.put("AM_PHONE_MODEL_OUT","iPhone 5 (A1429/A1442)");
					}
					if("iPhone5,3".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_OUT"))){
						YwtAttendanceSign.put("AM_PHONE_MODEL_OUT","iPhone 5C (A1456/A1532)");
					}
					if("iPhone5,4".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_OUT"))){
						YwtAttendanceSign.put("AM_PHONE_MODEL_OUT","iPhone 5C (A1507/A1516/A1526/A1529)");
					}
					if("iPhone6,1".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_OUT"))){
						YwtAttendanceSign.put("AM_PHONE_MODEL_OUT","iPhone 5S (A1453/A1533)");
					}
					if("iPhone6,2".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_OUT"))){
						YwtAttendanceSign.put("AM_PHONE_MODEL_OUT","iPhone 5S (A1457/A1518/A1528/A1530)");
					}
					if("iPhone7,1".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_OUT"))){
						YwtAttendanceSign.put("AM_PHONE_MODEL_OUT","iPhone 6 Plus (A1522/A1524)");
					}
					if("iPhone7,2".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_OUT"))){
						YwtAttendanceSign.put("AM_PHONE_MODEL_OUT","iPhone 6 (A1549/A1586)");
					}
				}
				if(null!=YwtAttendanceSign.get("PM_PHONE_MODEL_IN")){
					if("iPhone3,1".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_IN"))){
						YwtAttendanceSign.put("PM_PHONE_MODEL_IN","iPhone 4 (A1332)");
					}
					if("iPhone3,2".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_IN"))){
						YwtAttendanceSign.put("PM_PHONE_MODEL_IN","iPhone 4 (A1332)");
					}
					if("iPhone3,3".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_IN"))){
						YwtAttendanceSign.put("PM_PHONE_MODEL_IN","iPhone 4 (A1349)");
					}
					if("iPhone4,1".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_IN"))){
						YwtAttendanceSign.put("PM_PHONE_MODEL_IN","iPhone 4S (A1387/A1431)");
					}
					if("iPhone5,1".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_IN"))){
						YwtAttendanceSign.put("PM_PHONE_MODEL_IN","iPhone 5 (A1428)");
					}
					if("iPhone5,2".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_IN"))){
						YwtAttendanceSign.put("PM_PHONE_MODEL_IN","iPhone 5 (A1429/A1442)");
					}
					if("iPhone5,3".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_IN"))){
						YwtAttendanceSign.put("PM_PHONE_MODEL_IN","iPhone 5C (A1456/A1532)");
					}
					if("iPhone5,4".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_IN"))){
						YwtAttendanceSign.put("PM_PHONE_MODEL_IN","iPhone 5C (A1507/A1516/A1526/A1529)");
					}
					if("iPhone6,1".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_IN"))){
						YwtAttendanceSign.put("PM_PHONE_MODEL_IN","iPhone 5S (A1453/A1533)");
					}
					if("iPhone6,2".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_IN"))){
						YwtAttendanceSign.put("PM_PHONE_MODEL_IN","iPhone 5S (A1457/A1518/A1528/A1530)");
					}
					if("iPhone7,1".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_IN"))){
						YwtAttendanceSign.put("PM_PHONE_MODEL_IN","iPhone 6 Plus (A1522/A1524)");
					}
					if("iPhone7,2".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_IN"))){
						YwtAttendanceSign.put("PM_PHONE_MODEL_IN","iPhone 6 (A1549/A1586)");
					}
				}
				if(null!=YwtAttendanceSign.get("PM_PHONE_MODEL_OUT")){
					if("iPhone3,1".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_OUT"))){
						YwtAttendanceSign.put("PM_PHONE_MODEL_OUT","iPhone 4 (A1332)");
					}
					if("iPhone3,2".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_OUT"))){
						YwtAttendanceSign.put("PM_PHONE_MODEL_OUT","iPhone 4 (A1332)");
					}
					if("iPhone3,3".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_OUT"))){
						YwtAttendanceSign.put("PM_PHONE_MODEL_OUT","iPhone 4 (A1349)");
					}
					if("iPhone4,1".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_OUT"))){
						YwtAttendanceSign.put("PM_PHONE_MODEL_OUT","iPhone 4S (A1387/A1431)");
					}
					if("iPhone5,1".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_OUT"))){
						YwtAttendanceSign.put("PM_PHONE_MODEL_OUT","iPhone 5 (A1428)");
					}
					if("iPhone5,2".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_OUT"))){
						YwtAttendanceSign.put("PM_PHONE_MODEL_OUT","iPhone 5 (A1429/A1442)");
					}
					if("iPhone5,3".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_OUT"))){
						YwtAttendanceSign.put("PM_PHONE_MODEL_OUT","iPhone 5C (A1456/A1532)");
					}
					if("iPhone5,4".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_OUT"))){
						YwtAttendanceSign.put("PM_PHONE_MODEL_OUT","iPhone 5C (A1507/A1516/A1526/A1529)");
					}
					if("iPhone6,1".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_OUT"))){
						YwtAttendanceSign.put("PM_PHONE_MODEL_OUT","iPhone 5S (A1453/A1533)");
					}
					if("iPhone6,2".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_OUT"))){
						YwtAttendanceSign.put("PM_PHONE_MODEL_OUT","iPhone 5S (A1457/A1518/A1528/A1530)");
					}
					if("iPhone7,1".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_OUT"))){
						YwtAttendanceSign.put("PM_PHONE_MODEL_OUT","iPhone 6 Plus (A1522/A1524)");
					}
					if("iPhone7,2".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_OUT"))){
						YwtAttendanceSign.put("PM_PHONE_MODEL_OUT","iPhone 6 (A1549/A1586)");
					}
				}
			}
			dynaBean.set("haveRole", haveRole);
			for (Map<String, Object> YwtAttendanceSign : entityList1) {
				if (null!=YwtAttendanceSign.get("AM_SIGN_IN")) {
					YwtAttendanceSign.put("AM_SIGN_IN",
							df.format((Date)YwtAttendanceSign.get("AM_SIGN_IN")));
				}
				if (null!=YwtAttendanceSign.get("AM_SIGN_STATE_IN")) {
					String state= ""+YwtAttendanceSign.get("AM_SIGN_STATE_IN");
					if (state.equals("1")) {
						YwtAttendanceSign.put("AM_SIGN_STATE_IN","正常");
					}else if(state.equals("2")){
						YwtAttendanceSign.put("AM_SIGN_STATE_IN","迟到");
					}else if(state.equals("3")){
						YwtAttendanceSign.put("AM_SIGN_STATE_IN","早退");
					}
				}
				if (null!=YwtAttendanceSign.get("AM_SIGN_OUT")) {
					YwtAttendanceSign.put("AM_SIGN_OUT",
							df.format((Date)YwtAttendanceSign.get("AM_SIGN_OUT")));
				}
				if (null!=YwtAttendanceSign.get("AM_SIGN_STATE_OUT")) {
					String state= ""+YwtAttendanceSign.get("AM_SIGN_STATE_OUT");
					if (state.equals("1")) {
						YwtAttendanceSign.put("AM_SIGN_STATE_OUT","正常");
					}else if(state.equals("2")){
						YwtAttendanceSign.put("AM_SIGN_STATE_OUT","迟到");
					}else if(state.equals("3")){
						YwtAttendanceSign.put("AM_SIGN_STATE_OUT","早退");
					}
				}
				if (null!=YwtAttendanceSign.get("PM_SIGN_IN")) {
					YwtAttendanceSign.put("PM_SIGN_IN",
							df.format((Date)YwtAttendanceSign.get("PM_SIGN_IN")));
				}
				if (null!=YwtAttendanceSign.get("PM_SIGN_STATE_IN")) {
					String state= ""+YwtAttendanceSign.get("PM_SIGN_STATE_IN");
					if (state.equals("1")) {
						YwtAttendanceSign.put("PM_SIGN_STATE_IN","正常");
					}else if(state.equals("2")){
						YwtAttendanceSign.put("PM_SIGN_STATE_IN","迟到");
					}else if(state.equals("3")){
						YwtAttendanceSign.put("PM_SIGN_STATE_IN","早退");
					}
				}
				if (null!=YwtAttendanceSign.get("PM_SIGN_OUT")) {
					YwtAttendanceSign.put("PM_SIGN_OUT",
							df.format((Date)YwtAttendanceSign.get("PM_SIGN_OUT")));
				}
				if (null!=YwtAttendanceSign.get("PM_SIGN_STATE_OUT")) {
					String state= ""+YwtAttendanceSign.get("PM_SIGN_STATE_OUT");
					if (state.equals("1")) {
						YwtAttendanceSign.put("PM_SIGN_STATE_OUT","正常");
					}else if(state.equals("2")){
						YwtAttendanceSign.put("PM_SIGN_STATE_OUT","迟到");
					}else if(state.equals("3")){
						YwtAttendanceSign.put("PM_SIGN_STATE_OUT","早退");
					}
				}
			}
			request.setAttribute("allList", entityList1);
			return new ActionForward("/../manager/admin/YwtAttendanceSign/report_list.jsp");
		}
		
		if (!(StringUtils.isNotBlank(from_html)&&"1".equals(""+from_html))) {
			pager.init(recordCount, pager.getPageSize(), page);
		}
		//有值传来表示手机端调的
		if (StringUtils.isNotBlank(first)) {
			entity.getRow().setFirst(Integer.valueOf(first));
		}else {
			if(pager.getFirstRow()>=0){
				entity.getRow().setFirst(pager.getFirstRow());
			}else{
				entity.getRow().setFirst(0);
			}
			
		}
		if (StringUtils.isNotBlank(count)) {
			entity.getRow().setCount(Integer.valueOf(count));
		}else {
			entity.getRow().setCount(pager.getRowCount());
		}
		
		entityList = super.getFacade().getYwtAttendanceSignService().getYwtAttendanceSignLBPaginatedList(entity);
		for (Map<String, Object> YwtAttendanceSign : entityList) {
			//Iphone手机型号需转义
			if(null!=YwtAttendanceSign.get("AM_PHONE_MODEL_IN")){
				if("iPhone3,1".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_IN"))){
					YwtAttendanceSign.put("AM_PHONE_MODEL_IN","iPhone 4 (A1332)");
				}
				if("iPhone3,2".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_IN"))){
					YwtAttendanceSign.put("AM_PHONE_MODEL_IN","iPhone 4 (A1332)");
				}
				if("iPhone3,3".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_IN"))){
					YwtAttendanceSign.put("AM_PHONE_MODEL_IN","iPhone 4 (A1349)");
				}
				if("iPhone4,1".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_IN"))){
					YwtAttendanceSign.put("AM_PHONE_MODEL_IN","iPhone 4S (A1387/A1431)");
				}
				if("iPhone5,1".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_IN"))){
					YwtAttendanceSign.put("AM_PHONE_MODEL_IN","iPhone 5 (A1428)");
				}
				if("iPhone5,2".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_IN"))){
					YwtAttendanceSign.put("AM_PHONE_MODEL_IN","iPhone 5 (A1429/A1442)");
				}
				if("iPhone5,3".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_IN"))){
					YwtAttendanceSign.put("AM_PHONE_MODEL_IN","iPhone 5C (A1456/A1532)");
				}
				if("iPhone5,4".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_IN"))){
					YwtAttendanceSign.put("AM_PHONE_MODEL_IN","iPhone 5C (A1507/A1516/A1526/A1529)");
				}
				if("iPhone6,1".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_IN"))){
					YwtAttendanceSign.put("AM_PHONE_MODEL_IN","iPhone 5S (A1453/A1533)");
				}
				if("iPhone6,2".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_IN"))){
					YwtAttendanceSign.put("AM_PHONE_MODEL_IN","iPhone 5S (A1457/A1518/A1528/A1530)");
				}
				if("iPhone7,1".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_IN"))){
					YwtAttendanceSign.put("AM_PHONE_MODEL_IN","iPhone 6 Plus (A1522/A1524)");
				}
				if("iPhone7,2".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_IN"))){
					YwtAttendanceSign.put("AM_PHONE_MODEL_IN","iPhone 6 (A1549/A1586)");
				}
			}
			if(null!=YwtAttendanceSign.get("AM_PHONE_MODEL_OUT")){
				if("iPhone3,1".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_OUT"))){
					YwtAttendanceSign.put("AM_PHONE_MODEL_OUT","iPhone 4 (A1332)");
				}
				if("iPhone3,2".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_OUT"))){
					YwtAttendanceSign.put("AM_PHONE_MODEL_OUT","iPhone 4 (A1332)");
				}
				if("iPhone3,3".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_OUT"))){
					YwtAttendanceSign.put("AM_PHONE_MODEL_OUT","iPhone 4 (A1349)");
				}
				if("iPhone4,1".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_OUT"))){
					YwtAttendanceSign.put("AM_PHONE_MODEL_OUT","iPhone 4S (A1387/A1431)");
				}
				if("iPhone5,1".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_OUT"))){
					YwtAttendanceSign.put("AM_PHONE_MODEL_OUT","iPhone 5 (A1428)");
				}
				if("iPhone5,2".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_OUT"))){
					YwtAttendanceSign.put("AM_PHONE_MODEL_OUT","iPhone 5 (A1429/A1442)");
				}
				if("iPhone5,3".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_OUT"))){
					YwtAttendanceSign.put("AM_PHONE_MODEL_OUT","iPhone 5C (A1456/A1532)");
				}
				if("iPhone5,4".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_OUT"))){
					YwtAttendanceSign.put("AM_PHONE_MODEL_OUT","iPhone 5C (A1507/A1516/A1526/A1529)");
				}
				if("iPhone6,1".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_OUT"))){
					YwtAttendanceSign.put("AM_PHONE_MODEL_OUT","iPhone 5S (A1453/A1533)");
				}
				if("iPhone6,2".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_OUT"))){
					YwtAttendanceSign.put("AM_PHONE_MODEL_OUT","iPhone 5S (A1457/A1518/A1528/A1530)");
				}
				if("iPhone7,1".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_OUT"))){
					YwtAttendanceSign.put("AM_PHONE_MODEL_OUT","iPhone 6 Plus (A1522/A1524)");
				}
				if("iPhone7,2".equals(YwtAttendanceSign.get("AM_PHONE_MODEL_OUT"))){
					YwtAttendanceSign.put("AM_PHONE_MODEL_OUT","iPhone 6 (A1549/A1586)");
				}
			}
			if(null!=YwtAttendanceSign.get("PM_PHONE_MODEL_IN")){
				if("iPhone3,1".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_IN"))){
					YwtAttendanceSign.put("PM_PHONE_MODEL_IN","iPhone 4 (A1332)");
				}
				if("iPhone3,2".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_IN"))){
					YwtAttendanceSign.put("PM_PHONE_MODEL_IN","iPhone 4 (A1332)");
				}
				if("iPhone3,3".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_IN"))){
					YwtAttendanceSign.put("PM_PHONE_MODEL_IN","iPhone 4 (A1349)");
				}
				if("iPhone4,1".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_IN"))){
					YwtAttendanceSign.put("PM_PHONE_MODEL_IN","iPhone 4S (A1387/A1431)");
				}
				if("iPhone5,1".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_IN"))){
					YwtAttendanceSign.put("PM_PHONE_MODEL_IN","iPhone 5 (A1428)");
				}
				if("iPhone5,2".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_IN"))){
					YwtAttendanceSign.put("PM_PHONE_MODEL_IN","iPhone 5 (A1429/A1442)");
				}
				if("iPhone5,3".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_IN"))){
					YwtAttendanceSign.put("PM_PHONE_MODEL_IN","iPhone 5C (A1456/A1532)");
				}
				if("iPhone5,4".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_IN"))){
					YwtAttendanceSign.put("PM_PHONE_MODEL_IN","iPhone 5C (A1507/A1516/A1526/A1529)");
				}
				if("iPhone6,1".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_IN"))){
					YwtAttendanceSign.put("PM_PHONE_MODEL_IN","iPhone 5S (A1453/A1533)");
				}
				if("iPhone6,2".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_IN"))){
					YwtAttendanceSign.put("PM_PHONE_MODEL_IN","iPhone 5S (A1457/A1518/A1528/A1530)");
				}
				if("iPhone7,1".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_IN"))){
					YwtAttendanceSign.put("PM_PHONE_MODEL_IN","iPhone 6 Plus (A1522/A1524)");
				}
				if("iPhone7,2".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_IN"))){
					YwtAttendanceSign.put("PM_PHONE_MODEL_IN","iPhone 6 (A1549/A1586)");
				}
			}
			if(null!=YwtAttendanceSign.get("PM_PHONE_MODEL_OUT")){
				if("iPhone3,1".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_OUT"))){
					YwtAttendanceSign.put("PM_PHONE_MODEL_OUT","iPhone 4 (A1332)");
				}
				if("iPhone3,2".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_OUT"))){
					YwtAttendanceSign.put("PM_PHONE_MODEL_OUT","iPhone 4 (A1332)");
				}
				if("iPhone3,3".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_OUT"))){
					YwtAttendanceSign.put("PM_PHONE_MODEL_OUT","iPhone 4 (A1349)");
				}
				if("iPhone4,1".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_OUT"))){
					YwtAttendanceSign.put("PM_PHONE_MODEL_OUT","iPhone 4S (A1387/A1431)");
				}
				if("iPhone5,1".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_OUT"))){
					YwtAttendanceSign.put("PM_PHONE_MODEL_OUT","iPhone 5 (A1428)");
				}
				if("iPhone5,2".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_OUT"))){
					YwtAttendanceSign.put("PM_PHONE_MODEL_OUT","iPhone 5 (A1429/A1442)");
				}
				if("iPhone5,3".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_OUT"))){
					YwtAttendanceSign.put("PM_PHONE_MODEL_OUT","iPhone 5C (A1456/A1532)");
				}
				if("iPhone5,4".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_OUT"))){
					YwtAttendanceSign.put("PM_PHONE_MODEL_OUT","iPhone 5C (A1507/A1516/A1526/A1529)");
				}
				if("iPhone6,1".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_OUT"))){
					YwtAttendanceSign.put("PM_PHONE_MODEL_OUT","iPhone 5S (A1453/A1533)");
				}
				if("iPhone6,2".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_OUT"))){
					YwtAttendanceSign.put("PM_PHONE_MODEL_OUT","iPhone 5S (A1457/A1518/A1528/A1530)");
				}
				if("iPhone7,1".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_OUT"))){
					YwtAttendanceSign.put("PM_PHONE_MODEL_OUT","iPhone 6 Plus (A1522/A1524)");
				}
				if("iPhone7,2".equals(YwtAttendanceSign.get("PM_PHONE_MODEL_OUT"))){
					YwtAttendanceSign.put("PM_PHONE_MODEL_OUT","iPhone 6 (A1549/A1586)");
				}
			}
			YwtAttendanceSign.put("haveRole",haveRole);
			
			if (null!=YwtAttendanceSign.get("AM_SIGN_IN")) {
				YwtAttendanceSign.put("AM_SIGN_IN",
						df.format((Date)YwtAttendanceSign.get("AM_SIGN_IN")));
			}
			if (null!=YwtAttendanceSign.get("AM_SIGN_OUT")) {
				YwtAttendanceSign.put("AM_SIGN_OUT",
						df.format((Date)YwtAttendanceSign.get("AM_SIGN_OUT")));
			}
			if (null!=YwtAttendanceSign.get("PM_SIGN_IN")) {
				YwtAttendanceSign.put("PM_SIGN_IN",
						df.format((Date)YwtAttendanceSign.get("PM_SIGN_IN")));
			}
			if (null!=YwtAttendanceSign.get("PM_SIGN_OUT")) {
				YwtAttendanceSign.put("PM_SIGN_OUT",
						df.format((Date)YwtAttendanceSign.get("PM_SIGN_OUT")));
			}
		}
	
		allmap.put("user_id",user_id);
		allmap.put("userpass",userpass);
		allmap.put("total", recordCount);
		if (entityList == null) {
			String[] str = {};
			allmap.put("rows", str);
		} else {
			allmap.put("rows", entityList);
		}
		allmap.put("mod_id", mod_id);
		JSONArray jsonArray = JSONArray.fromObject(allmap);
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
    //手机端列表
	public ActionForward mobilelist(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String mod_id = String.valueOf(dynaBean.get("mod_id"));
		String _real_name_like = (String)dynaBean.get("_real_name_like");
		String page =(String) dynaBean.get("page");
		//手机端控制
		String first = (String) dynaBean.get("first");
		String count = (String) dynaBean.get("count");
		
		String user_id = (String) dynaBean.get("user_id");
		// 密码
		String userpass = (String) dynaBean.get("userpass");
		String from_html = (String) dynaBean.get("from_html");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		//web端取session
		PeProdUser userInfo = null;
		
		if (null==userInfo) {
			userInfo = (PeProdUser) super.getSessionAttribute(request,
					Constants.USER_INFO);
		}
		if (null==userInfo) {
			//手机端通过用户查
			userInfo = checkUserid(user_id, userpass,android_version,ios_version);
		}
		if (null == userInfo) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		YwtAttendanceSign entity = new YwtAttendanceSign();
        entity.getMap().put("user_id", userInfo.getId());
        
		if (StringUtils.isNotBlank(_real_name_like)) {
			entity.getMap().put("_real_name_like", _real_name_like);
		}
		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}
		if (flag) {// 如果是业务员，只能看到自己的拜访记录
			entity.setSign_user_id(userInfo.getId());
		} else {
			Long _dept_id = 0L;
			int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
			logger.info("Max level : {}", max_dlevel);
			switch (max_dlevel) {
			case 9:
				// 集团及以下部门可见
				_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
				break;
			case 8:
				// 分公司及以下部门可见
				KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
				if (null != dept_fgs && null != dept_fgs.getDept_id()) {
					_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
					entity.getMap().put("dept_id_start", _dept_id);
					// entity.getMap().put("fgs_dept_value", _dept_id);
				}
				break;
			case 7:
				// 我所在的部门及以下部门可见
				_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
				entity.getMap().put("dept_id_start", _dept_id);
				break;
			case 0:
				_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
				// entity.getMap().put("dept_id_start", __dept_id);
				entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
				break;
			default:
				// 出错
			}
			entity.getMap().put("session_user_id", userInfo.getId());
		}

		List<Map<String, Object>> entityList = null;
		Long recordCount = super.getFacade().getYwtAttendanceSignService().gettYwtAttendanceSignMobileCount(entity);
		
		if (StringUtils.isNotBlank(page)){
			pager.init(recordCount, pager.getPageSize(), page);
		}
		//有值传来表示手机端调的
		if (StringUtils.isNotBlank(first)) {
			entity.getRow().setFirst(Integer.valueOf(first));
		}else {
			entity.getRow().setFirst(pager.getFirstRow());
		}
		if (StringUtils.isNotBlank(count)) {
			entity.getRow().setCount(Integer.valueOf(count));
		}else {
			entity.getRow().setCount(pager.getRowCount());
		}
		entityList = super.getFacade().getYwtAttendanceSignService().getYwtAttendanceSignMobilePaginatedList(entity);
		for (Map<String, Object> YwtAttendanceSign : entityList) {
			if (null!=YwtAttendanceSign.get("AM_SIGN_IN")) {
				YwtAttendanceSign.put("AM_SIGN_IN",
						df.format((Date)YwtAttendanceSign.get("AM_SIGN_IN")));
			}
			if (null!=YwtAttendanceSign.get("AM_SIGN_OUT")) {
				YwtAttendanceSign.put("AM_SIGN_OUT",
						df.format((Date)YwtAttendanceSign.get("AM_SIGN_OUT")));
			}
			if (null!=YwtAttendanceSign.get("PM_SIGN_IN")) {
				YwtAttendanceSign.put("PM_SIGN_IN",
						df.format((Date)YwtAttendanceSign.get("PM_SIGN_IN")));
			}
			if (null!=YwtAttendanceSign.get("PM_SIGN_OUT")) {
				YwtAttendanceSign.put("PM_SIGN_OUT",
						df.format((Date)YwtAttendanceSign.get("PM_SIGN_OUT")));
			}
		}
		// 封装成JSON字符串
		Map<String, Object> allmap = new HashMap<String, Object>();
		allmap.put("user_id",user_id);
		allmap.put("userpass",userpass);
		allmap.put("total", recordCount);
		if (entityList == null) {
			String[] str = {};
			allmap.put("rows", str);
		} else {
			allmap.put("rows", entityList);
		}
		allmap.put("mod_id", mod_id);
		JSONArray jsonArray = JSONArray.fromObject(allmap);
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
	
	//手机端登陆
	public ActionForward mobileLogin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String username = (String) dynaBean.get("username");
		String userpass = (String) dynaBean.get("userpass");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser entity=this.checkUser(username, userpass,android_version,ios_version);
		
		// 封装成JSON字符串
		Map<String, Object> allmap = new HashMap<String, Object>();
		if (null!=entity&&null!=entity.getId()&&null!=entity.getPass_word()) {
			allmap.put("isSuccess", 1);
			allmap.put("user_id", entity.getId());
			allmap.put("userpass", userpass);
		}else {
			allmap.put("isSuccess", "");
		}
		JSONArray jsonArray = JSONArray.fromObject(allmap);
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
	//手机端日历初始化数据
	public ActionForward mobileCalendar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		String mod_id = String.valueOf(dynaBean.get("mod_id"));
		String sign_user_id = (String) dynaBean.get("sign_user_id");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		// 封装成JSON字符串
		Map<String, Object> allmap = new HashMap<String, Object>();
	   	Calendar calendar = Calendar.getInstance();
	   	
		if (StringUtils.isNotBlank(year)) {
			calendar.set(Calendar.YEAR, Integer.valueOf(year));
		}
		if (StringUtils.isNotBlank(month)) {
			calendar.set(Calendar.MONTH,(Integer.valueOf(month)-1));
		}
	   	allmap.put("year", calendar.get(Calendar.YEAR));
	   	allmap.put("month", (calendar.get(Calendar.MONTH)+1));
        allmap.put("maxday", day(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1));		
        allmap.put("sign_user_id", sign_user_id);
        YwtAttendanceSign entity=new YwtAttendanceSign();
        entity.getMap().put("yyyymm", df.format(calendar.getTime()));
        if (StringUtils.isNotBlank(sign_user_id)) {
			entity.setSign_user_id(Long.valueOf(sign_user_id));
		}
		List<Map<String, Object>> sign4=super.getFacade().getYwtAttendanceSignService().getMonthYwtAttendanceSignList(entity);        

		allmap.put("sign4", sign4);
        allmap.put("mod_id", mod_id);
		JSONArray jsonArray = JSONArray.fromObject(allmap);
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
	
	//年月日的人的签到记录
	public ActionForward mobileCalendarDay(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		String mod_id = String.valueOf(dynaBean.get("mod_id"));
		String sign_user_id = (String) dynaBean.get("sign_user_id");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String day = (String) dynaBean.get("day");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 封装成JSON字符串
		Map<String, Object> allmap = new HashMap<String, Object>();
	   	Calendar calendar = Calendar.getInstance();
	   	
	   	if (StringUtils.isNotBlank(year)&&StringUtils.isNotBlank(month)&&StringUtils.isNotBlank(day)) {
			calendar.set(Integer.valueOf(year), Integer.valueOf(month) - 1,
					Integer.valueOf(day));
		}else {
			allmap.put("res", "时间设置失败！");
		}
        YwtAttendanceSign entity=new YwtAttendanceSign();
        entity.getMap().put("yyyymmdd", df.format(calendar.getTime()));
        if (StringUtils.isNotBlank(sign_user_id)) {
			entity.setSign_user_id(Long.valueOf(sign_user_id));
			List<Map<String, Object>> signviewlist=super.getFacade().getYwtAttendanceSignService().getDaySignList(entity);
			for (Map<String, Object> map : signviewlist) {
				map.put("SIGN_DATE", df1.format(map.get("SIGN_DATE")));
			}
			allmap.put("signviewlist", signviewlist);
		}
        allmap.put("mod_id", mod_id);
		JSONArray jsonArray = JSONArray.fromObject(allmap);
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
	//签到
	public ActionForward newsign(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);
		String user_id = (String) dynaBean.get("user_id");
		// 密码
		String userpass = (String) dynaBean.get("userpass");
		String from_html = (String) dynaBean.get("from_html");
		
		String sign_latlng = (String) dynaBean.get("sign_latlng");// 经纬度
		String sign_addr = (String) dynaBean.get("sign_addr");// 签到位置
		
		String phone_model = (String) dynaBean.get("phone_model");// 手机型号
		String imei = (String) dynaBean.get("imei");// 手机IMEI
		
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		//web端取session
		PeProdUser userInfo = null;
		
		if (null==userInfo) {
			userInfo = (PeProdUser) super.getSessionAttribute(request,
					Constants.USER_INFO);
		}
		if (null==userInfo) {
			//手机端通过用户查
			userInfo = checkUserid(user_id, userpass,android_version,ios_version);
		}
		if (null == userInfo) {
			return null;
		}
		//签到标示
		String sign_type = (String) dynaBean.get("sign_type");
		String ip=request.getRemoteAddr();
		Map<String, Object> allmap = new HashMap<String, Object>();
		YwtAttendanceSignTime  ywtAttendanceSignTime=new YwtAttendanceSignTime();
		
		int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		if(max_dlevel!=9){
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
			ywtAttendanceSignTime.setDept_id(dept_fgs.getDept_id());
		}
		
		List<YwtAttendanceSignTime> list=super.getFacade().getYwtAttendanceSignTimeService().getYwtAttendanceSignTimeSignTimeList(ywtAttendanceSignTime);
		ywtAttendanceSignTime=list.get(0);
		
		
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		Long amSignIn=millisecond(df.format(ywtAttendanceSignTime.getAm_sign_in_date()));//上午签到时间
		Long amSignOut=millisecond(df.format(ywtAttendanceSignTime.getAm_sign_out_date()));//上午签退时间
		Long am_center_pm=millisecond(df.format(ywtAttendanceSignTime.getAm_center_pm()));//午休分界点
		Long pmSignIn=millisecond(df.format(ywtAttendanceSignTime.getPm_sign_in_date()));//下午签到时间
		Long pmSignOut=millisecond(df.format(ywtAttendanceSignTime.getPm_sign_out_date()));//下午签退时间
		Long listDate=millisecond("23:59:59");//当天最后时间
		Long currentDate=millisecond(df.format(new Date()));//现在是什么时间
		
		//用于计算分钟时差
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDate1=df2.format(new Date());//现在是什么时间
		
		
		//查找当前人的签到数据
		YwtAttendanceSign entity=new YwtAttendanceSign();
		entity.setSign_user_id(userInfo.getId());
		entity.setSign_type(Long.valueOf(sign_type));
		entity.getMap().put("cursign_date", new Date());
		
		entity=super.getFacade().getYwtAttendanceSignService().getYwtAttendanceSign(entity);
		if (null==entity) {
			entity=new YwtAttendanceSign();
			entity.setAdd_date(new Date());
			
			if(max_dlevel!=9){
				if (null!=userInfo&&null!=userInfo.getDept_id()) {
					KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
					if (null!=dept_fgs&&null!=dept_fgs.getDept_id()) {
						entity.setDept_id(dept_fgs.getDept_id());
					}
					entity.setJb_id(userInfo.getDept_id());
				}
			}
			
			//初始化部分共有签到信息（后面通过id判断是否签过d）
			entity.setReal_name(userInfo.getReal_name());
			entity.setSign_date(new Date());
			entity.setSign_type(Long.valueOf(sign_type));
			entity.setSign_user_id(userInfo.getId());
			if (StringUtils.isNotBlank(ip)) {
				entity.setIp(ip);
			}
			if (StringUtils.isNotBlank(sign_addr)) {
				entity.setSign_addr(sign_addr);
			}
			if (StringUtils.isNotBlank(sign_latlng)) {
				entity.setSign_latlng(sign_latlng);
			}
			if (StringUtils.isNotBlank(phone_model)) {
				entity.setPhone_model(phone_model);
			}
			if (StringUtils.isNotBlank(imei)) {
				entity.setImei(imei);
			}
		}
		/**
		 * 新逻辑
		 */
		if ("1".equals(sign_type)) {//上午可以签到
			if (currentDate>0&&currentDate<am_center_pm) {
				if (null==entity.getId()) {//未签到
					if (currentDate>0&&currentDate<amSignIn) {//正常
						entity.setSign_state(1L);
					}
					if(currentDate>amSignIn&&currentDate<am_center_pm) {//迟到
						entity.setSign_state(2L);
					}
					entity.setMemo("上午签到！");
					super.getFacade().getYwtAttendanceSignService().createYwtAttendanceSign(entity);
					allmap.put("signType",2);
					allmap.put("signTypeName","上午签退");
					allmap.put("res","上午签到成功");
				}else{//已签到
					allmap.put("res","上午已经签过了不可重复签到");
				}
				
			}
		}else if ("2".equals(sign_type)) {//上午可以签退
			if (currentDate>0&&currentDate<am_center_pm) {
				String amSignInTime=upSignDateStr(user_id,"1");
				if(StringUtils.isNotBlank(amSignInTime)){//有上午签到
					if(miniteSpan(amSignInTime,currentDate1)){
						if (null==entity.getId()) {
							if (currentDate<amSignOut) {//早退
								entity.setSign_state(3L);
							}else {
								entity.setSign_state(1L);
							}
							entity.setMemo("上午签退！");
							super.getFacade().getYwtAttendanceSignService().createYwtAttendanceSign(entity);
							allmap.put("signType",3);
							allmap.put("signTypeName","下午签到");
							allmap.put("res","上午签退成功");
						}else{//上午已经签过退
							allmap.put("res","上午已经签过退了不可重复签退");
						}
					}else {
						allmap.put("signType",2);
						allmap.put("signTypeName","上午签退");
						allmap.put("res","两次签到必须间隔15分钟");
					}
				}else {
					if (null==entity.getId()) {
						if (currentDate<amSignOut) {//早退
							entity.setSign_state(3L);
						}else {
							entity.setSign_state(1L);
						}
						entity.setMemo("上午签退！");
						super.getFacade().getYwtAttendanceSignService().createYwtAttendanceSign(entity);
						allmap.put("signType",3);
						allmap.put("signTypeName","下午签到");
						allmap.put("res","上午签退成功");
					}else{//上午已经签过退
						allmap.put("res","上午已经签过退了不可重复签退");
					}
				}
			}else {
				allmap.put("res","不在签到时间范围内");
			}
		}else if ("3".equals(sign_type)) {//下午可以签到
			if (currentDate>am_center_pm&&currentDate<listDate) {
				String amSignOutTime=upSignDateStr(user_id,"2");
					if(StringUtils.isNotBlank(amSignOutTime)){//有上午签到
						if(miniteSpan(amSignOutTime,currentDate1)){
							if (null==entity.getId()) {
								if (currentDate>am_center_pm&&currentDate<pmSignIn) {//正常
									entity.setSign_state(1L);
								}else {//迟到
									entity.setSign_state(2L);
								}
								entity.setMemo("下午签到！");
								super.getFacade().getYwtAttendanceSignService().createYwtAttendanceSign(entity);
								allmap.put("signType",4);
								allmap.put("signTypeName","下午签退");
								allmap.put("res","下午签到成功");
							}else{//未签到
								allmap.put("res","下午已经签过了不可重复签到");
							}
						}else {
							allmap.put("signType",3);
							allmap.put("signTypeName","下午签到！");
							allmap.put("res","两次签到必须间隔15分钟");
						}
					}else{
						if (null==entity.getId()) {
							if (currentDate>am_center_pm&&currentDate<pmSignIn) {//正常
								entity.setSign_state(1L);
							}else {//迟到
								entity.setSign_state(2L);
							}
							entity.setMemo("下午签到！");
							super.getFacade().getYwtAttendanceSignService().createYwtAttendanceSign(entity);
							allmap.put("signType",4);
							allmap.put("signTypeName","下午签退");
							allmap.put("res","下午签到成功");
						}else{//未签到
							allmap.put("res","下午已经签过了不可重复签到");
						}
					}
				}else {
					allmap.put("res","不在签到时间范围内");
				}
		}else if ("4".equals(sign_type)) {//下午可以签退
			if (currentDate>am_center_pm&&currentDate<listDate) {
				String pmSignInTime=upSignDateStr(user_id,"3");
				
				if(StringUtils.isNotBlank(pmSignInTime)){//有上午签到
					if(miniteSpan(pmSignInTime,currentDate1)){
						if (null==entity.getId()) {//下午下午未签退
							if (currentDate<pmSignOut) {//早退
								entity.setSign_state(3L);
							}else {
								entity.setSign_state(1L);
							}
							entity.setMemo("下午签退！");
							super.getFacade().getYwtAttendanceSignService().createYwtAttendanceSign(entity);
							allmap.put("signType",4);
							allmap.put("signTypeName","下午签退");
							allmap.put("res","下午签退成功");
						}else{//下午签过退
							allmap.put("res","下午已经签过退了不可重复签退");
						}
					}else {
						allmap.put("signType",4);
						allmap.put("signTypeName","下午签退！");
						allmap.put("res","两次签到必须间隔15分钟");
					}
				}else {
					if (null==entity.getId()) {//下午下午未签退
						if (currentDate<pmSignOut) {//早退
							entity.setSign_state(3L);
						}else {
							entity.setSign_state(1L);
						}
						entity.setMemo("下午签退！");
						super.getFacade().getYwtAttendanceSignService().createYwtAttendanceSign(entity);
						allmap.put("signType",4);
						allmap.put("signTypeName","下午签退");
						allmap.put("res","下午签退成功");
					}else{//下午签过退
						allmap.put("res","下午已经签过退了不可重复签退");
					}
				}
			}else {
				allmap.put("res","不在签到时间范围内");
			}
		}else{
			allmap.put("res","签到初始化信息失败！");
		}
		
		
		/**
		 * 旧的逻辑
		 */
		/*if ("1".equals(sign_type)) {//上午可以签到
			if (currentDate>0&&currentDate<amSignOut) {
				//未签到
				if (null==entity.getId()) {
					if (currentDate>0&&currentDate<amSignIn) {//迟到
						entity.setSign_state(1L);
					}
					if(currentDate>amSignIn&&currentDate<amSignOut) {
						entity.setSign_state(2L);
					}
					entity.setMemo("上午签到！");
					super.getFacade().getYwtAttendanceSignService().createYwtAttendanceSign(entity);
					allmap.put("signType",2);
					allmap.put("signTypeName","上午签退");
					allmap.put("res","上午签到成功");
				}else{//已签到
					allmap.put("res","上午已经签过了不可重复签到");
				}
			}else {
				allmap.put("res","不在签到时间范围内");
			}
		}else if ("2".equals(sign_type)) {//上午可以签退
			//已签到
			if (currentDate>amSignIn&&currentDate<pmSignIn) {
				//上午是否签到
				YwtAttendanceSign amSign=new YwtAttendanceSign();
				amSign.setSign_user_id(userInfo.getId());
				amSign.setSign_type(1L);
				amSign.getMap().put("cursign_date", new Date());
				amSign=super.getFacade().getYwtAttendanceSignService().getYwtAttendanceSign(amSign);
				if (null==amSign) {
					allmap.put("res","上午未签到，不可签退");
				}else{
					//上午未签退
					if (null==entity.getId()) {
							if (currentDate<amSignOut) {//早退
								entity.setSign_state(3L);
							}else {
								entity.setSign_state(1L);
							}
							entity.setMemo("上午签退！");
							super.getFacade().getYwtAttendanceSignService().createYwtAttendanceSign(entity);
							allmap.put("signType",3);
							allmap.put("signTypeName","下午签到");
							allmap.put("res","上午签退成功");
					}else{//上午已经签过退
						allmap.put("res","上午已经签过退了不可重复签退");
					}
				}
			}else {
				allmap.put("res","不在签到时间范围内");
			}
		}else if ("3".equals(sign_type)) {//下午可以签到
			//已签到
			if (currentDate>amSignOut&&currentDate<pmSignOut) {
				if (null==entity.getId()) {
					if (currentDate>amSignIn&&currentDate<pmSignIn) {//正常
						entity.setSign_state(1L);
					}
					if (currentDate>pmSignIn&&currentDate>pmSignOut) {//迟到
						entity.setSign_state(2L);
					}
					entity.setMemo("下午签到！");
					super.getFacade().getYwtAttendanceSignService().createYwtAttendanceSign(entity);
					allmap.put("signType",4);
					allmap.put("signTypeName","下午签退");
					allmap.put("res","下午签到成功");
				}else{//未签到
					allmap.put("res","下午已经签过了不可重复签到");
				}
			}else {
				allmap.put("res","不在签到时间范围内");
			}
		}else if ("4".equals(sign_type)) {//下午可以签退
			//已签到
			if (currentDate>pmSignIn&&currentDate<listDate) {
				//下午是否签到
				YwtAttendanceSign pmSign=new YwtAttendanceSign();
				pmSign.setSign_user_id(userInfo.getId());
				pmSign.setSign_type(3L);
				pmSign.getMap().put("cursign_date", new Date());
				pmSign=super.getFacade().getYwtAttendanceSignService().getYwtAttendanceSign(pmSign);
				if (null==pmSign) {
					allmap.put("res","下午未签到，不可签退");
				}else{
					if (null==entity.getId()) {//下午下午未签退
						if (currentDate>pmSignIn&&currentDate<pmSignOut) {//早退
							entity.setSign_state(3L);
						}else {
							entity.setSign_state(1L);
						}
						entity.setMemo("下午签退！");
						super.getFacade().getYwtAttendanceSignService().createYwtAttendanceSign(entity);
						allmap.put("res","下午签退成功");
					}else{//下午签过退
						allmap.put("res","下午已经签过退了不可重复签退");
					}
				}
			}else {
				allmap.put("res","不在签到时间范围内");
			}
		}else{
			allmap.put("res","签到初始化信息失败！");
		}*/
		allmap.put("user_id",user_id);
		allmap.put("userpass",userpass);
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
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);
		String user_id = (String) dynaBean.get("user_id");
		// 密码
		String userpass = (String) dynaBean.get("userpass");
		
		String from_html = (String) dynaBean.get("from_html");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		//web端取session
		PeProdUser userInfo = null;
		
		if (null==userInfo) {
			userInfo = (PeProdUser) super.getSessionAttribute(request,
					Constants.USER_INFO);
		}
		if (null==userInfo) {
			//手机端通过用户查
			userInfo = checkUserid(user_id, userpass,android_version,ios_version);
		}
		if (null == userInfo) {
			return null;
		}
		
		Map<String, Object> allmap = new HashMap<String, Object>();
		KonkaBaseTypeData taskType=new KonkaBaseTypeData();
		taskType.setType_name("签到类型");
		taskType=super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeData(taskType);
		List<KonkaBaseTypeData> taskTypeList=null;
		if (null!=taskType && null!=taskType.getType_id()) {
			Long parTypeId=taskType.getType_id();
			taskType=new KonkaBaseTypeData();
			taskType.setPar_type_id(parTypeId);
			//taskTypeList = super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeDataList(taskType);
		}
		//allmap.put("taskTypeList", taskTypeList);
		
		//查找考勤时间
		YwtAttendanceSignTime  ywtAttendanceSignTime=new YwtAttendanceSignTime();
		int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		if(max_dlevel!=9){
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
			ywtAttendanceSignTime.setDept_id(dept_fgs.getDept_id());
		}
		List<YwtAttendanceSignTime> list=super.getFacade().getYwtAttendanceSignTimeService().getYwtAttendanceSignTimeSignTimeList(ywtAttendanceSignTime);
		ywtAttendanceSignTime=list.get(0);
		
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		Long amSignIn=millisecond(df.format(ywtAttendanceSignTime.getAm_sign_in_date()));//上午签到时间 06
		Long amSignOut=millisecond(df.format(ywtAttendanceSignTime.getAm_sign_out_date()));//上午签退时间 12
		Long am_center_pm=millisecond(df.format(ywtAttendanceSignTime.getAm_center_pm()));//午休分界点
		Long pmSignIn=millisecond(df.format(ywtAttendanceSignTime.getPm_sign_in_date()));//下午签到时间 14
		Long pmSignOut=millisecond(df.format(ywtAttendanceSignTime.getPm_sign_out_date()));//下午签退时间 18
		
		Long lastDate=millisecond("23:59:59");//当天最后时间
		//System.out.println(df.format(new Date()));
		Long currentDate=millisecond(df.format(new Date()));//现在是什么时间
		
		YwtAttendanceSign entity=new YwtAttendanceSign();
		entity.setSign_user_id(userInfo.getId());
		entity.getMap().put("yyyymmdd", df1.format(new Date()));
		List<Map<String, Object>> signhistory=super.getFacade().getYwtAttendanceSignService().getDaySignList(entity);
		
		/**
		 * 新逻辑
		 */
		
		/**
		 * 确认当前是上午签到还是签退
		 */
		if (currentDate>0&&currentDate<am_center_pm) {
			if (null!=signhistory) {
				if(isSign(signhistory, "1")){
					allmap.put("signType", 2);
					allmap.put("signTypeName", "上午签退");
				}else {
					allmap.put("signType", 1);
					allmap.put("signTypeName", "上午签到");
				};
			}
		}
		
		/**
		 * 确认当前是下午签到还是签退
		 */
		if (currentDate>am_center_pm&&currentDate<lastDate) {
			if (null!=signhistory) {
				if(isSign(signhistory, "3")){
					allmap.put("signType", 4);
					allmap.put("signTypeName", "下午签退");
				}else {
					allmap.put("signType", 3);
					allmap.put("signTypeName", "下午签到");
				};
			}
		}
		
		/**
		 * 就的逻辑
		 */
		/*//0-8
		if (currentDate>0&&currentDate<amSignIn) {
			allmap.put("signType", 1);
			allmap.put("signTypeName", "上午签到");
		}
		//8-12
		if (currentDate>amSignIn&&currentDate<amSignOut) {
			if (null!=signhistory) {
				if(isSign(signhistory, "1")){
					allmap.put("signType", 2);
					allmap.put("signTypeName", "上午签退");
				}else {
					allmap.put("signType", 1);
					allmap.put("signTypeName", "上午签到");
				};
			}
		}
		//12-14
		if (currentDate>amSignOut&&currentDate<pmSignIn) {
			if (null!=signhistory) {
				if(isSign(signhistory, "1")&&isSign(signhistory, "2")){
					allmap.put("signType", 3);
					allmap.put("signTypeName", "下午签到");
				}else if(!isSign(signhistory, "1")&&!isSign(signhistory, "2")){
					allmap.put("signType", 3);
					allmap.put("signTypeName", "下午签到");
				}else {
					allmap.put("signType", 2);
					allmap.put("signTypeName", "上午签退");
				};
			}
		}
		//14-18
		if (currentDate>pmSignIn&&currentDate<pmSignOut) {
			if (null!=signhistory) {
				if(isSign(signhistory, "3")){
					allmap.put("signType", 4);
					allmap.put("signTypeName", "下午签退");
				}else {
					allmap.put("signType", 3);
					allmap.put("signTypeName", "下午签到");
				};
			}
		}
		//18-24
		if (currentDate>pmSignOut&&currentDate<lastDate) {
					allmap.put("signType", 4);
					allmap.put("signTypeName", "下午签退");
		}*/
		
		
		allmap.put("sign_user_id", userInfo.getId());
		allmap.put("user_id",user_id);
		allmap.put("userpass",userpass);
		// 转换为json数据
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
		String user_id = (String) dynaBean.get("user_id");
		// 密码
		String userpass = (String) dynaBean.get("userpass");
		String from_html = (String) dynaBean.get("from_html");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		//web端取session
		PeProdUser userInfo = null;
		
		if (null==userInfo) {
			userInfo = (PeProdUser) super.getSessionAttribute(request,
					Constants.USER_INFO);
		}
		if (null==userInfo) {
			//手机端通过用户查
			userInfo = checkUserid(user_id, userpass,android_version,ios_version);
		}
		if (null == userInfo) {
			super.renderHtml(response, "登录超时");
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String id = (String) dynaBean.get("id");// 
		String mod_id = (String) dynaBean.get("mod_id");//
		YwtAttendanceSign entity=new YwtAttendanceSign();
		this.copyProperties(entity, dynaBean);
		
		Map<String, Object> allmap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(id)) {
			super.getFacade().getYwtAttendanceSignService()
					.modifyYwtAttendanceSign(entity);
			allmap.put("result", id);
			allmap.put("msg", "修改成功");
		} else {
			 super.getFacade()
					.getYwtAttendanceSignService()
					.createYwtAttendanceSign(entity);
			allmap.put("result", id);
			allmap.put("msg", "添加成功");
		}
		allmap.put("user_id",user_id);
		allmap.put("userpass",userpass);
		allmap.put("mod_id", mod_id);
		JSONArray jsonArray = JSONArray.fromObject(allmap);
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
	
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String user_id = (String) dynaBean.get("user_id");
		// 密码
		String userpass = (String) dynaBean.get("userpass");
		String from_html = (String) dynaBean.get("from_html");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		//web端取session
		PeProdUser userInfo = null;
		
		if (null==userInfo) {
			userInfo = (PeProdUser) super.getSessionAttribute(request,
					Constants.USER_INFO);
		}
		if (null==userInfo) {
			//手机端通过用户查
			userInfo = checkUserid(user_id, userpass,android_version,ios_version);
		}
		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}
//		存放数据
		Map<String, Object> allmap = new HashMap<String, Object>();
		
		KonkaBaseTypeData taskType=new KonkaBaseTypeData();
		taskType.setType_name("签到类型");
		taskType=super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeData(taskType);
		List<KonkaBaseTypeData> taskTypeList=null;
		if (null!=taskType && null!=taskType.getType_id()) {
			Long parTypeId=taskType.getType_id();
			taskType=new KonkaBaseTypeData();
			taskType.setPar_type_id(parTypeId);
			taskTypeList = super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeDataList(taskType);
		}
		allmap.put("taskTypeList", taskTypeList);
		
		YwtAttendanceSign attendanceDay = new YwtAttendanceSign();
		if (StringUtils.isNotBlank(id)) {
			attendanceDay.setId(Long.valueOf(id));
			attendanceDay.getRow().setFirst(0);
			attendanceDay.getRow().setCount(1);
		}
		attendanceDay=super.getFacade().getYwtAttendanceSignService().getYwtAttendanceSign(attendanceDay);
		allmap.put("entity", attendanceDay);
		allmap.put("mod_id", mod_id);
		allmap.put("user_id",user_id);
		allmap.put("userpass",userpass);
		JSONArray jsonArray = JSONArray.fromObject(allmap);
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
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Map<String, Object> allmap = new HashMap<String, Object>();
		
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return null;
		}
		String user_id = (String) dynaBean.get("user_id");
		// 密码
		String userpass = (String) dynaBean.get("userpass");
		
		String from_html = (String) dynaBean.get("from_html");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		//web端取session
		PeProdUser userInfo = null;
		
		if (null==userInfo) {
			userInfo = (PeProdUser) super.getSessionAttribute(request,
					Constants.USER_INFO);
		}
		if (null==userInfo) {
			//手机端通过用户查
			userInfo = checkUserid(user_id, userpass,android_version,ios_version);
		}
		YwtAttendanceSign entity = new YwtAttendanceSign();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
			int delcountid=super.getFacade().getYwtAttendanceSignService().removeYwtAttendanceSign(entity);
			allmap.put("result", delcountid);
			allmap.put("mod_id", mod_id);
			if (delcountid>0) {
				allmap.put("msg", "删除成功！");
			}else {
				allmap.put("msg", "删除失败！");
			}
		}else {
			allmap.put("msg", "未选择要删除的数据！");
		}
		allmap.put("user_id",user_id);
		allmap.put("userpass",userpass);
		
		JSONArray jsonArray = JSONArray.fromObject(allmap);
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
	
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String user_id = (String) dynaBean.get("user_id");
		// 密码
		String userpass = (String) dynaBean.get("userpass");
		String from_html = (String) dynaBean.get("from_html");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		//web端取session
		PeProdUser userInfo = null;
		
		if (null==userInfo) {
			userInfo = (PeProdUser) super.getSessionAttribute(request,
					Constants.USER_INFO);
		}
		if (null==userInfo) {
			//手机端通过用户查
			userInfo = checkUserid(user_id, userpass,android_version,ios_version);
		}
		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}
//		存放数据
		Map<String, Object> allmap = new HashMap<String, Object>();
		
		KonkaBaseTypeData taskType=new KonkaBaseTypeData();
		taskType.setType_name("签到类型");
		taskType=super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeData(taskType);
		List<KonkaBaseTypeData> taskTypeList=null;
		if (null!=taskType && null!=taskType.getType_id()) {
			Long parTypeId=taskType.getType_id();
			taskType=new KonkaBaseTypeData();
			taskType.setPar_type_id(parTypeId);
			taskTypeList = super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeDataList(taskType);
		}
		allmap.put("taskTypeList", taskTypeList);
		
		YwtAttendanceSign attendanceDay = new YwtAttendanceSign();
		if (StringUtils.isNotBlank(id)) {
			attendanceDay.setId(Long.valueOf(id));
			attendanceDay.getRow().setFirst(0);
			attendanceDay.getRow().setCount(1);
		}
		attendanceDay=super.getFacade().getYwtAttendanceSignService().getYwtAttendanceSign(attendanceDay);
		allmap.put("entity", attendanceDay);
		allmap.put("mod_id", mod_id);
		allmap.put("user_id",user_id);
		allmap.put("userpass",userpass);
		JSONArray jsonArray = JSONArray.fromObject(allmap);
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
    //是否签过到
	private boolean isSign(List<Map<String, Object>> signhistory,String signType){
		boolean sign=false;
		for (Map<String, Object> map : signhistory) {
			if (null!=map.get("SIGN_TYPE")&&signType.equals(""+map.get("SIGN_TYPE"))) {
				return true;
			}
		}
		return sign;
	}
    //计算指定年月最多多少天
	private static int day(int year, int month) {
		int maxDay = 0;
		int day = 1;
		/**
		 * 与其他语言环境敏感类一样，Calendar 提供了一个类方法 getInstance， 以获得此类型的一个通用的对象。Calendar 的
		 * getInstance 方法返回一 个 Calendar 对象，其日历字段已由当前日期和时间初始化：
		 */
		Calendar calendar = Calendar.getInstance();
		/**
		 * 实例化日历各个字段,这里的day为实例化使用
		 */
		calendar.set(year, month - 1, day);
		/**
		 * Calendar.Date:表示一个月中的某天 calendar.getActualMaximum(int
		 * field):返回指定日历字段可能拥有的最大值
		 */
		maxDay = calendar.getActualMaximum(Calendar.DATE);
		return maxDay;
	}
	
	private static String theFirstDayOfCurrentMonth() {
		Calendar c = Calendar.getInstance();
		String y = String.valueOf(c.get(Calendar.YEAR));
		String m = "";
		String d = "";// the first day of a month
		if (c.get(Calendar.MONTH) + 1 >= 10) {
			m = String.valueOf(c.get(Calendar.MONTH) + 1);
		} else {
			m = "0" + String.valueOf(c.get(Calendar.MONTH) + 1);
		}
		d = "0" + c.getActualMinimum(Calendar.DAY_OF_MONTH);
		String start_day = y + "-" + m + "-" + d;
		return start_day;
	}
	
//	/**
//	 * 
//	 * @param username  用户名
//	 * @param userpass  密码
//	 * @param from_html 来源于wap静态页面
//	 * @return
//	 * @throws Exception
//	 */
//	protected PeProdUser checkMobileUser(String username, String userpass,String from_html,String android_version, String ios_version) throws Exception {
//		PeProdUser ui = new PeProdUser();
//		if (StringUtils.isEmpty(username))
//			return null;
//		if (StringUtils.isEmpty(userpass))
//			return null;
//		
//		PeProdUser entity = new PeProdUser();
//		if(StringUtils.isNotBlank(android_version) && Long.valueOf(android_version)>37)
//		{
//			entity.setId(new Long(username.trim()));
//			entity.setPass_word(userpass);
//		}else if(StringUtils.isNotBlank(ios_version) && Long.valueOf(ios_version)>37){
//			entity.setId(new Long(userid.trim()));
//			entity.setPass_word(userpass);
//		}else
//		{
//			user.setId(new Long(userid.trim()));
//			user.setPass_word(new DESPlus().encrypt(userpass));
//		}
//		entity.setUser_name(username);
//		entity.setIs_del(0);
//		DESPlus des = new DESPlus();
//		entity.setPass_word(des.encrypt(userpass));
//		ui = getFacade().getPeProdUserService().getPeProdUser(entity);
//		return ui;
//	}
	//转换当前时分秒未Long类型
	private Long millisecond(String str){
		return Long.valueOf(str.replace(":", ""));
	}
	
	/**
	 * 计算两个时间的相差是否大于15分钟分钟数
	 * @param begin
	 * @param end
	 * @return
	 */
	private boolean miniteSpan(String begin,String end){
		
	        // Print out the dates
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Date begindate=null;
			Date enddate=null;
			try {
				begindate = sdf.parse(begin);
				enddate = sdf.parse(end);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        // Get the represented date in milliseconds
	        long time1 = begindate.getTime();
	        long time2 = enddate.getTime();

	        // Calculate difference in milliseconds
	        long diff = time2 - time1;

	        // Difference in minutes
	        long diffMin = diff / (60 * 1000);
	        if(diffMin>15){
	        	return true;
	        }else {
				return false;
			}
	}
	
	private String upSignDateStr(String user_id,String sign_type) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		YwtAttendanceSign entity = new YwtAttendanceSign();
		entity.setSign_user_id(Long.valueOf(user_id));
		entity.setSign_type(Long.valueOf(sign_type));
		entity.getMap().put("cursign_date", new Date());
		entity = super.getFacade().getYwtAttendanceSignService().getYwtAttendanceSign(entity);
		String add_date = "";
		if (null !=entity && null !=entity.getAdd_date()) {
			add_date = sdf.format(entity.getAdd_date());
		}
		return add_date;
	}

}
