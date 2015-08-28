package com.ebiz.mmt.web.struts.webservice;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaBbItr2Import;
import com.ebiz.mmt.domain.KonkaBbZj98Import;
import com.ebiz.mmt.domain.KonkaPdModelTask;
import com.ebiz.mmt.domain.KonkaSpList;
import com.ebiz.mmt.r3.ReturnInfo;
import com.ebiz.mmt.r3.ZdmtrxCriteria;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * 平板内销各机型销售评估
 * 
 * @author ZHOU
 * 
 */
public class KonkaModelXSReportAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return list(mapping, form, request, response);
	}

	// 页面进来就直接查询
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		KonkaPdModelTask kv = new KonkaPdModelTask();

		long year = 0;
		long month = 0;
		long day = 0;

		String enddate = "";
		String lastmonth = "";
		String syncTime = "";

		String y = "";// year
		String m = "";// month
		String d = "";// day

		Date date = new Date();
		Calendar c = Calendar.getInstance();

		// 昨天
		c.setTime(date);
		c.add(Calendar.DATE, -1);
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH) + 1;
		day = c.get(Calendar.DAY_OF_MONTH);

		y = year + "";
		if (month < 10) {
			m = "0" + month;
		} else {
			m = month + "";
		}
		if (day < 10) {
			d = "0" + day;
		} else {
			d = day + "";
		}
		kv.setCyear(y);
		kv.setCmonth(m);
		kv.setCday(d);

		// 某一天
		enddate = y + "年" + m + "月" + d + "日";

		// 前天
		c.setTime(date);
		c.add(Calendar.DATE, -2);
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH) + 1;
		day = c.get(Calendar.DAY_OF_MONTH);
		y = year + "";
		if (month < 10) {
			m = "0" + month;
		} else {
			m = month + "";
		}
		if (day < 10) {
			d = "0" + day;
		} else {
			d = day + "";
		}
		kv.setLyear(y);
		kv.setLmonth(m);
		kv.setLday(d);

		// 上个月
		c.setTime(date);
		c.add(Calendar.MONTH, -1);
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH) + 1;
		y = year + "";
		if (month < 10) {
			m = "0" + month;
		} else {
			m = month + "";
		}
		// 上个月
		lastmonth = y + "年" + m + "月";

		if (day < 10) {
			d = "0" + day;
		} else {
			d = day + "";
		}
		kv.setLlyear(y);
		kv.setLlmonth(m);

		request.setAttribute("lastmonth", lastmonth);
		request.setAttribute("enddate", enddate);

		// syncTime =
		// super.getFacade().getKonkaBbItr2ImportService().getMaxSyncTime();
		// if (syncTime != null && !"".equals(syncTime))
		// request.setAttribute("syncTime", syncTime.substring(0, 19));

		// 规则要到明天才能看到今天的结果
		// 如果数据还没有同步,返回空,并给出提示
		c.setTime(date);
		c.add(Calendar.DATE, -1);
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH) + 1;
		day = c.get(Calendar.DAY_OF_MONTH);
		KonkaBbItr2Import ki = new KonkaBbItr2Import();
		ki.setYear(year);
		ki.setMonth(month);
		ki.setDay(day);

		y = year + "";
		if (month < 10) {
			m = "0" + month;
		} else {
			m = month + "";
		}

		request.setAttribute("year", y);
		request.setAttribute("month", m);

		long resultSize = 0l;
		resultSize = super.getFacade().getKonkaBbItr2ImportService().getKonkaBbItr2ImportCount(ki);
		// 数据已经同步
		if (resultSize > 0) {
			List<HashMap<String, Object>> kkList = super.getFacade().getKonkaPdModelTaskService()
					.getKonkaPdModelXCLYHZ(kv);
			
			request.setAttribute("entityList", kkList);
		} else {
			request.setAttribute("entityList", null);
			return mapping.findForward("list");
		}
		return mapping.findForward("list");
	}

	/**
	 * 测试用,待功能稳定后,此功能交由定时器完成数据同步,目前页面提供数据同步操作,执行此方法
	 * 
	 * @throws SQLException
	 */
	public ActionForward syncData4Report(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException {

		// 同步方法
		// 1.按当前日期来同步,通过配置来取得月结开始和月结结束
		// 2.机型分别为LED*,LC*,PDP*
		// 3.分销渠道,产品组 固定为10
		// 4. 由于机型的数量固定,而且数量不会太多. 估计(目前大约)500+500+50 个机型是极限

		// 一.同步机型存销比(上个月数据,昨天数据,今天数据)
		// 二.同步机型利润(只需要今天数据,只有当月的)
		// 三.同步前,需要清除一些垃圾数据(机型存比:同步的时间点如果为一个月的第一天,那么删除上个月的数据;删除前天的数据 ;
		// 机型利润:每天清除)

		// 前天
		String qianTian = "";
		// 昨天
		String zuoTian = "";
		// 当月月初
		String firstDayOfCurrentMonth = "";
		// 上月初
		String fisrtDayOfLastMonth = "";
		// 上月尾
		String lastDayOfLastMonth = "";

		String y = "";// year
		String m = "";// month
		String d = "";// the first day of a month
		String d2 = "";// the last day of a month

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		Date date = new Date();
		Calendar c = Calendar.getInstance();
		// 昨天
		c.setTime(date);
		c.add(Calendar.DATE, -1);
		zuoTian = sdf.format(c.getTime());

		// 前天
		c.setTime(date);
		c.add(Calendar.DATE, -2);
		qianTian = sdf.format(c.getTime());

		// ===========================时间配置start================================//
		// 当月月初
		KonkaSpList ksp = new KonkaSpList();
		c.setTime(date);
		y = c.get(Calendar.YEAR) + "";
		if (c.get(Calendar.MONTH) + 1 >= 10) {
			m = String.valueOf(c.get(Calendar.MONTH) + 1);
		} else {
			m = "0" + String.valueOf(c.get(Calendar.MONTH) + 1);
		}
		d = "01";

		ksp.setYear(Integer.valueOf(y));
		ksp.setMonth(Integer.valueOf(m));
		ksp.setIs_del(0);
		ksp = super.getFacade().getKonkaSpListService().getKonkaSpList(ksp);
		// 如果系统没有维护时间,就以自然月进行数据同步
		if (ksp.getS_date() != null && ksp.getE_date() != null) {
			firstDayOfCurrentMonth = sdf.format(ksp.getS_date());
		} else {
			firstDayOfCurrentMonth = y + m + d;
		}

		// 上月初
		KonkaSpList ksp2 = new KonkaSpList();
		c.setTime(date);
		c.add(Calendar.MONTH, -1);
		y = c.get(Calendar.YEAR) + "";
		if (c.get(Calendar.MONTH) + 1 >= 10) {
			m = String.valueOf(c.get(Calendar.MONTH) + 1);
		} else {
			m = "0" + String.valueOf(c.get(Calendar.MONTH) + 1);
		}
		d = "01";
		d2 = "" + c.getActualMaximum(Calendar.DAY_OF_MONTH);


		ksp2.setYear(Integer.valueOf(y));
		ksp2.setMonth(Integer.valueOf(m));
		ksp2.setIs_del(0);
		ksp2 = super.getFacade().getKonkaSpListService().getKonkaSpList(ksp2);
		// 如果系统没有维护时间,就以自然月进行数据同步
		if (ksp2.getS_date() != null && ksp2.getE_date() != null) {
			fisrtDayOfLastMonth = sdf.format(ksp2.getS_date());
			lastDayOfLastMonth = sdf.format(ksp2.getE_date());
		} else {
			// 上月初
			fisrtDayOfLastMonth = y + m + d;
			// 上月尾
			lastDayOfLastMonth = y + m + d2;
		}

		// ============================时间配置end=============================//

		// 一 .机型存销
		List<KonkaBbZj98Import> zj98LastMonthList = new ArrayList<KonkaBbZj98Import>();
		List<KonkaBbZj98Import> zj98toQiantiandayList = new ArrayList<KonkaBbZj98Import>();
		List<KonkaBbZj98Import> zj98toZuotiandayList = new ArrayList<KonkaBbZj98Import>();

		String[] matnrs = { "LED*", "LC*", "PDP*" };
		// 上个月数据 Y
		zj98LastMonthList = this.getZJ98Data(matnrs, fisrtDayOfLastMonth, lastDayOfLastMonth);
		// 截至前天的数据
		zj98toQiantiandayList = this.getZJ98Data(matnrs, firstDayOfCurrentMonth, qianTian);
		// 截至昨天的数据
		zj98toZuotiandayList = this.getZJ98Data(matnrs, firstDayOfCurrentMonth, zuoTian);
		//
		List<KonkaBbZj98Import> list1 = new ArrayList<KonkaBbZj98Import>();
		// 上个月数据
		for (KonkaBbZj98Import ki : zj98LastMonthList) {
			ki.setYear(Long.valueOf(ksp2.getYear()));
			ki.setMonth(Long.valueOf(ksp2.getMonth()));
			ki.setDay(28l);// 写死一天
			list1.add(ki);
		}

		// 截至前天数据
		c.setTime(date);
		c.add(Calendar.DATE, -2);
		for (KonkaBbZj98Import ki : zj98toQiantiandayList) {
			ki.setYear(Long.valueOf(c.get(Calendar.YEAR)));
			ki.setMonth(Long.valueOf(c.get(Calendar.MONTH) + 1));
			ki.setDay(Long.valueOf(c.get(Calendar.DAY_OF_MONTH)));
			list1.add(ki);
		}
		// 截至昨天数据
		c.setTime(date);
		c.add(Calendar.DATE, -1);
		for (KonkaBbZj98Import ki : zj98toZuotiandayList) {
			ki.setYear(Long.valueOf(c.get(Calendar.YEAR)));
			ki.setMonth(Long.valueOf(c.get(Calendar.MONTH) + 1));
			ki.setDay(Long.valueOf(c.get(Calendar.DAY_OF_MONTH)));
			list1.add(ki);
		}

		// 二 .机型利润()
		List<KonkaBbItr2Import> itr2toZuoTianList = new ArrayList<KonkaBbItr2Import>();
		itr2toZuoTianList = this.getZdmrtxsData(matnrs, firstDayOfCurrentMonth, zuoTian);
		List<KonkaBbItr2Import> list2 = new ArrayList<KonkaBbItr2Import>();
		list2 = itr2toZuoTianList;
		// execute function
		super.getFacade().getKonkaBbZj98ImportService().createZJ98AndItr2Sync(list1, list2);

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

	}

	// zj98 机型存销比
	private List<KonkaBbZj98Import> getZJ98Data(String[] matnrs, String begin_date, String end_date) {

		// init params //
		String v_vtweg = "10";
		String v_spart = "10";
		// String v_matnr_begin = "";
		// String v_matnr_end = "";
		// String v_bstdk_begin = "";
		// String v_bstdk_end = "";
		// String v_cxb_begin = "";
		// String v_cxb_end = "";
		// 目前就只有这三类机型需求
		// String[] matnrs = { "LED*", "LC*", "PDP*" };

		List<List<KonkaBbZj98Import>> tempList = new ArrayList<List<KonkaBbZj98Import>>();
		for (String matnr : matnrs) {
			List<KonkaBbZj98Import> list = new ArrayList<KonkaBbZj98Import>();
			ReturnInfo<KonkaBbZj98Import> info = new ReturnInfo<KonkaBbZj98Import>();
			info = super.getFacade().getR3WebInterfaceService()
					.getZlesZJ98(matnr, null, v_vtweg, v_spart, begin_date, end_date, null, null);
			list = info.getDataResult();
			tempList.add(list);
		}

		// 合并
		List<KonkaBbZj98Import> destList = new ArrayList<KonkaBbZj98Import>();
		for (List<KonkaBbZj98Import> sourceList : tempList) {
			for (KonkaBbZj98Import ki : sourceList) {
				destList.add(ki);
			}
		}
		tempList.clear();
		return destList;

	}

	// zdmrtxs 机型利润(当月截至今天数据)
	private List<KonkaBbItr2Import> getZdmrtxsData(String[] matnrs, String begin_date, String end_date) {

		List<List<KonkaBbItr2Import>> tempList = new ArrayList<List<KonkaBbItr2Import>>();
		List<KonkaBbItr2Import> destList = new ArrayList<KonkaBbItr2Import>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		Date date2 = new Date();
		String m = "";
		if (end_date != null && !"".equals(end_date)) {
			try {
				date2 = sdf.parse(end_date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		Calendar c = Calendar.getInstance();
		c.setTime(date2);
		ZdmtrxCriteria zc = new ZdmtrxCriteria();
		if (c.get(Calendar.MONTH) + 1 >= 10) {
			m = c.get(Calendar.MONTH) + 1 + "";
		} else {
			m = "0" + (c.get(Calendar.MONTH) + 1);
		}
		for (String matnr : matnrs) {
			zc.resetFieldValue();
			// 机型
			zc.getV_MATNR().put("LOW", matnr);
			// 年度
			zc.getV_LFGJA().put("LOW", c.get(Calendar.YEAR) + "");
			zc.getV_LFGJA().put("HIGH", c.get(Calendar.YEAR) + "");
			// 月份
			zc.getV_LFMON().put("LOW", m);
			zc.getV_LFMON().put("HIGH", m);

			List<KonkaBbItr2Import> list = new ArrayList<KonkaBbItr2Import>();
			ReturnInfo<KonkaBbItr2Import> info = new ReturnInfo<KonkaBbItr2Import>();
			info = super.getFacade().getR3WebInterfaceService().getITR2(zc);
			list = info.getDataResult();

			tempList.add(list);
		}

		for (List<KonkaBbItr2Import> sourceList : tempList) {
			for (KonkaBbItr2Import ki : sourceList) {
				destList.add(ki);
			}
		}
		return destList;
	}

	// public static void main(String[] args) {
	// String s1 = "a1";
	// String s2 = "b1";
	// String s3 = "c1";
	// String s4 = "a2";
	// String s5 = "b2";
	// String s6 = "c2";
	// String s7 = "a3";
	// String s8 = "b3";
	// String s9 = "c3";
	//
	// List<String> list1 = new ArrayList<String>();
	// list1.add(s1);
	// list1.add(s2);
	// list1.add(s3);
	// list1.add(s4);
	// list1.add(s5);
	// list1.add(s6);
	// list1.add(s7);
	// list1.add(s8);
	// list1.add(s9);

		// Map<String, List> map = new HashMap<String, List>();
		// // key
		// for (String s : list1) {
		// map.put(s.charAt(s.length() - 1) + "", null);
		// }
		// // 组list
		// for (String s0 : map.keySet()) {
		// List<String> list = new ArrayList<String>();
		// for (String s : list1) {
		// if (s0.equals(s.charAt(s.length() - 1) + "")) {
		// list.add(s);
		// }
		// }
		// map.put(s0, list);
		// }

		// //System.out.println(map);

	// String s = "02";
	// //System.out.println(Integer.valueOf(s));

	// }
}
