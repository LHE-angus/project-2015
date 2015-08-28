package com.ebiz.mmt.web.struts.manager.leader;

import java.io.PrintWriter;
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

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.StatisticalDimensionMd;
import com.ebiz.mmt.domain.VOrgOfDept;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * 零售产品结构分析
 * 
 * @author Wang Hao
 */
public class jcfxReportLscpAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.view(mapping, form, request, response);
	}
//    零售产品结构分析总入口
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> allmap = new HashMap<String, Object>();
		// 默认显示当前1月的时间区间
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String user_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		//web端取session
		PeProdUser userInfo = null;
		
		if (null==userInfo) {
			userInfo = (PeProdUser) super.getSessionAttribute(request,
					Constants.USER_INFO);
		}
		if (null==userInfo) {
			//手机端通过用户查
			userInfo = checkUser(user_id, inuserpass);
		}
		if (null == userInfo) {
			return null;
		}
		VOrgOfDept dept =new VOrgOfDept();
		if (null!=userInfo&&null!=userInfo.getDept_id()) {
			
			dept.setCur_dept_id(userInfo.getDept_id());
			dept=super.getFacade().getVOrgOfDeptService().getVOrgOfDept(dept );
		}
		// 默认显示当前1月的时间区间
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		String []current_yyyy_mm = df.format(today).split("-");
		request.setAttribute("_year", current_yyyy_mm[0]);//
		request.setAttribute("_month", current_yyyy_mm[1]);//默认 
		
		if (null!=dept&&null!=dept.getDept_id()) {
			request.setAttribute("_dept_id", dept.getDept_id());//默认 分公司
		}
		//request.setAttribute("_data_type", 0);//默认 分公司
		dynaBean.set("isNotEpp", "isNotEpp");
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		
		String is_bi = (String) dynaBean.get("is_bi");
		if(StringUtils.isNotBlank(is_bi)){
			request.setAttribute("is_bi",is_bi);
		}
		return mapping.findForward("view");
	}
	
	
	//品牌列表
	public ActionForward brandList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> allmap = new HashMap<String, Object>();
		// 默认显示当前1月的时间区间
		DynaBean dynaBean = (DynaBean) form;
		String _year=(String)dynaBean.get("_year"); //年
		String _month=(String)dynaBean.get("_month");//月
		String _dept_id=(String)dynaBean.get("_dept_id");// 分公司
		String _data_type=(String)dynaBean.get("_data_type");//数据来源
		
		StatisticalDimensionMd v=new StatisticalDimensionMd();
        //零售或分销
		if (StringUtils.isNotBlank(_data_type)) {
			if ("is_lingshou".equals(_data_type)) {
                  v.getMap().put("is_lingshou", "is_lingshou");
			}
			if ("is_fenxiao".equals(_data_type)) {
				v.getMap().put("is_fenxiao", "is_fenxiao");
			}
		}
		//年
		if (StringUtils.isNotBlank(_year)) {
			v.getMap().put("year", _year);
		}
		//分公司
		if (StringUtils.isNotBlank(_dept_id)) {
			v.getMap().put("dept_id", _dept_id);
		}
		//当月
		if (StringUtils.isNotBlank(_year)&&StringUtils.isNotBlank(_month)) {
			v.getMap().put("cur_year", _year);
			v.getMap().put("cur_month", _month);
		}
		//上月
		if (StringUtils.isNotBlank(_year)&&StringUtils.isNotBlank(_month)) {
			String up_year_month[]=getLastDate(_year+"-"+_month).split("-");
			v.getMap().put("up_year",up_year_month[0]);
			v.getMap().put("up_month",up_year_month[1]);
		}
		KonkaDept dept=null;
		if (null!=_dept_id&&StringUtils.isNotBlank(_dept_id)&&StringUtils.isNumeric(_dept_id)) {
			dept=new KonkaDept();
			dept.setDept_id(Long.valueOf(_dept_id));
			dept=super.getFacade().getKonkaDeptService().getKonkaDept(dept);
		}
		String titleText="";
		if (StringUtils.isNotBlank(_year)&&StringUtils.isNotBlank(_month)&&null!=dept&&StringUtils.isNotBlank(dept.getDept_name())) {
			titleText = _year + "年" + _month + "月" + dept.getDept_name()
					+ "品牌零售额占比";
		}
		allmap.put("titleText", titleText);
		
		List<Map<String, String>> brandList=super.getFacade().getStatisticalDimensionMdService().getBrandList(v);
		if (brandList == null) {
			String[] str = {};
			allmap.put("rows", str);
		} else {
			allmap.put("rows", brandList);
		}
		allmap.put("title", "品牌");
		allmap.put("title1", "单位：万元");
		allmap.put("title2", "单位：台");
		allmap.put("title3", "单位：元/台");
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
	
	//品类列表
	public ActionForward brandTypeList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> allmap = new HashMap<String, Object>();
		// 默认显示当前1月的时间区间
		DynaBean dynaBean = (DynaBean) form;
		String _year=(String)dynaBean.get("_year"); //年
		String _month=(String)dynaBean.get("_month");//月
		String _dept_id=(String)dynaBean.get("_dept_id");// 分公司
		String _data_type=(String)dynaBean.get("_data_type");//数据来源
		
		StatisticalDimensionMd v=new StatisticalDimensionMd();
		//零售或分销
		if (StringUtils.isNotBlank(_data_type)) {
			if ("is_lingshou".equals(_data_type)) {
                  v.getMap().put("is_lingshou", "is_lingshou");
			}
			if ("is_fenxiao".equals(_data_type)) {
				v.getMap().put("is_fenxiao", "is_fenxiao");
			}
		}
		//年
		if (StringUtils.isNotBlank(_year)) {
			v.getMap().put("year", _year);
		}
		//分公司
		if (StringUtils.isNotBlank(_dept_id)) {
			v.getMap().put("dept_id", _dept_id);
		}
		//当月
		if (StringUtils.isNotBlank(_year)&&StringUtils.isNotBlank(_month)) {
			v.getMap().put("cur_year", _year);
			v.getMap().put("cur_month", _month);
		}
		//上月
		if (StringUtils.isNotBlank(_year)&&StringUtils.isNotBlank(_month)) {
			String up_year_month[]=getLastDate(_year+"-"+_month).split("-");
			v.getMap().put("up_year",up_year_month[0]);
			v.getMap().put("up_month",up_year_month[1]);
		}
		List<Map<String, String>> brandTypeList=super.getFacade().getStatisticalDimensionMdService().getBrandTypeList(v);
		
		KonkaDept dept=null;
		if (null!=_dept_id&&StringUtils.isNotBlank(_dept_id)&&StringUtils.isNumeric(_dept_id)) {
			dept=new KonkaDept();
			dept.setDept_id(Long.valueOf(_dept_id));
			dept=super.getFacade().getKonkaDeptService().getKonkaDept(dept);
		}
		String titleText="";
		if (StringUtils.isNotBlank(_year)&&StringUtils.isNotBlank(_month)&&null!=dept&&StringUtils.isNotBlank(dept.getDept_name())) {
			titleText = _year + "年" + _month + "月" + dept.getDept_name()
					+ "品类零售额对比";
		}
		allmap.put("titleText", titleText);
		if (brandTypeList == null) {
			String[] str = {};
			allmap.put("rows", str);
		} else {
			allmap.put("rows", brandTypeList);
		}
		allmap.put("title", "品类");
		
		// 转换为json数据
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
	
	//尺寸段列表
	public ActionForward sizeSecList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> allmap = new HashMap<String, Object>();
		// 默认显示当前1月的时间区间
		DynaBean dynaBean = (DynaBean) form;
		String _year=(String)dynaBean.get("_year"); //年
		String _month=(String)dynaBean.get("_month");//月
		String _dept_id=(String)dynaBean.get("_dept_id");// 分公司
		String _data_type=(String)dynaBean.get("_data_type");//数据来源
		
	    StatisticalDimensionMd v=new StatisticalDimensionMd();
	  //零售或分销
		if (StringUtils.isNotBlank(_data_type)) {
			if ("is_lingshou".equals(_data_type)) {
                  v.getMap().put("is_lingshou", "is_lingshou");
			}
			if ("is_fenxiao".equals(_data_type)) {
				v.getMap().put("is_fenxiao", "is_fenxiao");
			}
		}
		//年
		if (StringUtils.isNotBlank(_year)) {
			v.getMap().put("year", _year);
		}
		//分公司
		if (StringUtils.isNotBlank(_dept_id)) {
			v.getMap().put("dept_id", _dept_id);
		}
		//当月
		if (StringUtils.isNotBlank(_year)&&StringUtils.isNotBlank(_month)) {
			v.getMap().put("cur_year", _year);
			v.getMap().put("cur_month", _month);
		}
		//上月
		if (StringUtils.isNotBlank(_year)&&StringUtils.isNotBlank(_month)) {
			String up_year_month[]=getLastDate(_year+"-"+_month).split("-");
			v.getMap().put("up_year",up_year_month[0]);
			v.getMap().put("up_month",up_year_month[1]);
		}
		
		KonkaDept dept=null;
		if (null!=_dept_id&&StringUtils.isNotBlank(_dept_id)&&StringUtils.isNumeric(_dept_id)) {
			dept=new KonkaDept();
			dept.setDept_id(Long.valueOf(_dept_id));
			dept=super.getFacade().getKonkaDeptService().getKonkaDept(dept);
		}
		String titleText="";
		if (StringUtils.isNotBlank(_year)&&StringUtils.isNotBlank(_month)&&null!=dept&&StringUtils.isNotBlank(dept.getDept_name())) {
			titleText = _year + "年" + _month + "月" + dept.getDept_name()
					+ "尺寸段零售额占比";
		}
		allmap.put("titleText", titleText);
		
		List<Map<String, String>> sizeSecList=super.getFacade().getStatisticalDimensionMdService().getSizeSecList(v);
		if (sizeSecList == null) {
			String[] str = {};
			allmap.put("rows", str);
		} else {
			allmap.put("rows", sizeSecList);
		}
		allmap.put("title", "尺寸段");
		// 转换为json数据
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
	
	//尺寸列表
	public ActionForward MdSizeList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> allmap = new HashMap<String, Object>();
		// 默认显示当前1月的时间区间
		DynaBean dynaBean = (DynaBean) form;
		String _year=(String)dynaBean.get("_year"); //年
		String _month=(String)dynaBean.get("_month");//月
		String _dept_id=(String)dynaBean.get("_dept_id");// 分公司
		String _data_type=(String)dynaBean.get("_data_type");//数据来源
		
        StatisticalDimensionMd v=new StatisticalDimensionMd();
      //零售或分销
		if (StringUtils.isNotBlank(_data_type)) {
			if ("is_lingshou".equals(_data_type)) {
                  v.getMap().put("is_lingshou", "is_lingshou");
			}
			if ("is_fenxiao".equals(_data_type)) {
				v.getMap().put("is_fenxiao", "is_fenxiao");
			}
		}
		//年
		if (StringUtils.isNotBlank(_year)) {
			v.getMap().put("year", _year);
		}
		//分公司
		if (StringUtils.isNotBlank(_dept_id)) {
			v.getMap().put("dept_id", _dept_id);
		}
		//当月
		if (StringUtils.isNotBlank(_year)&&StringUtils.isNotBlank(_month)) {
			v.getMap().put("cur_year", _year);
			v.getMap().put("cur_month", _month);
		}
		//上月
		if (StringUtils.isNotBlank(_year)&&StringUtils.isNotBlank(_month)) {
			String up_year_month[]=getLastDate(_year+"-"+_month).split("-");
			v.getMap().put("up_year",up_year_month[0]);
			v.getMap().put("up_month",up_year_month[1]);
		}
		List<Map<String, String>> mdSizeList=super.getFacade().getStatisticalDimensionMdService().getMdSizeList(v);
		if (mdSizeList == null) {
			String[] str = {};
			allmap.put("rows", str);
		} else {
			allmap.put("rows", mdSizeList);
		}
		allmap.put("title", "尺寸");
		
		// 转换为json数据
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
	
	//价格段列表
	public ActionForward priceDuanList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> allmap = new HashMap<String, Object>();
		// 默认显示当前1月的时间区间
		DynaBean dynaBean = (DynaBean) form;
		String _year=(String)dynaBean.get("_year"); //年
		String _month=(String)dynaBean.get("_month");//月
		String _dept_id=(String)dynaBean.get("_dept_id");// 分公司
		String _data_type=(String)dynaBean.get("_data_type");//数据来源
		
		 StatisticalDimensionMd v=new StatisticalDimensionMd();
		//零售或分销
			if (StringUtils.isNotBlank(_data_type)) {
				if ("is_lingshou".equals(_data_type)) {
	                  v.getMap().put("is_lingshou", "is_lingshou");
				}
				if ("is_fenxiao".equals(_data_type)) {
					v.getMap().put("is_fenxiao", "is_fenxiao");
				}
			}
			//年
			if (StringUtils.isNotBlank(_year)) {
				v.getMap().put("year", _year);
			}
			//分公司
			if (StringUtils.isNotBlank(_dept_id)) {
				v.getMap().put("dept_id", _dept_id);
			}
			//当月
			if (StringUtils.isNotBlank(_year)&&StringUtils.isNotBlank(_month)) {
				v.getMap().put("cur_year", _year);
				v.getMap().put("cur_month", _month);
			}
			//上月
			if (StringUtils.isNotBlank(_year)&&StringUtils.isNotBlank(_month)) {
				String up_year_month[]=getLastDate(_year+"-"+_month).split("-");
				v.getMap().put("up_year",up_year_month[0]);
				v.getMap().put("up_month",up_year_month[1]);
			}
		List<Map<String, String>> priceDuanList=super.getFacade().getStatisticalDimensionMdService().getPriceDuanList(v);
		if (priceDuanList == null) {
			String[] str = {};
			allmap.put("rows", str);
		} else {
			allmap.put("rows", priceDuanList);
		}
		allmap.put("title", "价格段");
		
		// 转换为json数据
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
	/**
	 * yyyy-MM 当月
	 * @param datestr
	 * @return
	 */
	private static String getLastDate(String datestr){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		Date date;
		Calendar cal=Calendar.getInstance();
		try {
			date = df.parse(datestr);
			cal.setTime(date);
			cal.add(Calendar.MARCH,-1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return df.format(cal.getTime());
	}
	
	//手机端查用户
	protected PeProdUser checkUser(String user_id, String userpass) throws Exception {
		PeProdUser ui = new PeProdUser();
		if (!GenericValidator.isLong(user_id))
			return null;
		if (StringUtils.isEmpty(userpass))
			return null;
		PeProdUser entity = new PeProdUser();
		entity.setId(Long.valueOf(user_id));
		entity.setIs_del(0);
		DESPlus des = new DESPlus();
		entity.setPass_word(des.encrypt(userpass));
		ui = getFacade().getPeProdUserService().getPeProdUser(entity);
		return ui;
	}
}
