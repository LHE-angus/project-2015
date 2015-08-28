package com.ebiz.mmt.web.struts.manager.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Liu,ZhiXiang
 * @version 2013-07-02
 */
public class KonkaSellUnReportAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		KonkaDept dept = new KonkaDept();
		dept.setDept_type(3);
		dept.getMap().put("order_by_dept_name", "no_empty");
		List<KonkaDept> deptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(dept);
		request.setAttribute("deptList", deptList);
		dynaBean.set("is_report", "0");

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		String this_day = df.format(calendar.getTime());
		dynaBean.set("sell_date_start", this_day);
		dynaBean.set("sell_date_end", this_day);

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		} else if (peProdUser.getUser_type() == 0) {
			// 康佳总部
			dynaBean.set("dept_type", "1");
		} else if (peProdUser.getUser_type() == 1) {
			// 康佳分公司
			KonkaDept konkaDept = super.getKonkaDeptForFgs(peProdUser.getDept_id());
			if (null == konkaDept) {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else {
				dynaBean.set("dept_type", "3");
				dynaBean.set("dept_id", konkaDept.getDept_id().toString());
			}
		}

		return mapping.findForward("list");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String sell_date_start = (String) dynaBean.get("sell_date_start");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
		String store_name_like = (String) dynaBean.get("store_name_like");
		String cxy_user_name_like = (String) dynaBean.get("cxy_user_name_like");
		String is_report = (String) dynaBean.get("is_report");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		KonkaDept dept = new KonkaDept();
		dept.setDept_type(3);
		dept.getMap().put("order_by_dept_name", "no_empty");
		List<KonkaDept> deptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(dept);
		request.setAttribute("deptList", deptList);
		if (null == peProdUser) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		} else if (peProdUser.getUser_type() == 0) {
			// 康佳总部
			dynaBean.set("dept_type", "1");
		} else if (peProdUser.getUser_type() == 1) {
			// 康佳分公司
			KonkaDept konkaDept = super.getKonkaDeptForFgs(peProdUser.getDept_id());
			if (null == konkaDept) {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}else{
				dynaBean.set("dept_type", "3");
 			dynaBean.set("dept_id", konkaDept.getDept_id().toString());
				dept_id = konkaDept.getDept_id().toString();
				
//			else if (konkaDept.getDept_type() == 3) {
//				// 分公司
//				dynaBean.set("dept_type", "3");
//				dynaBean.set("dept_id", konkaDept.getDept_id().toString());
//				dept_id = konkaDept.getDept_id().toString();
//			} else if (konkaDept.getDept_type() == 4 || konkaDept.getDept_type() == 5) {
//				// 经营处、办事处
//				// super.renderJavaScript(response,
//				// "window.onload=function(){alert('" + msg +
//				// "');history.back();}");
//				// return null;
//			} else {
				// super.renderJavaScript(response,
				// "window.onload=function(){alert('" + msg +
				// "');history.back();}");
				// return null;
			}
		}

		List<String> array = new ArrayList<String>();

		String sql = " select c.*,FUNC_GET_CXYNAME(c.store_id) as cxy_name from ( ";
		sql += " select  a.DEPT_SN,a.DEPT_ID,a.DEPT_NAME,a.L4_DEPT_ID,a.L4_DEPT_NAME,a.L5_DEPT_ID,a.L5_DEPT_NAME,";
		sql += " a.YWY_USER_NAME,a.YWY_DEPT_ID,a.KONKA_R3_ID,a.CUSTOMER_NAME,a.R3_CODE,a.MERGED_R3_CODE,a.STORE_ID,a.STORE_NAME, a.store_del_mark,a.store_stop_date,decode(b.report_num, null, '0', '1') is_report,value(d.report_count,0) as report_count,value(b.report_total_money,0) as report_total_money,";
		sql += " value(b.report_num,0) as report_num,a.ywy_job_id,a.ywzg_job_id,a.jbjl_job_id from (select distinct t.DEPT_SN,t.DEPT_ID,t.DEPT_NAME,t.L4_DEPT_ID,t.L4_DEPT_NAME,t.L5_DEPT_ID,t.L5_DEPT_NAME,";
		sql += " t.YWY_USER_NAME,t.YWY_DEPT_ID,t.KONKA_R3_ID,t.CUSTOMER_NAME,t.R3_CODE,t.MERGED_R3_CODE,t.STORE_ID,t.STORE_NAME,t.STORE_DEL_MARK,t.STORE_STOP_DATE,ywy_job_id,ywzg_job_id,jbjl_job_id ";
		sql += " from V_ORG_OF_CXY_ALL_BY_STORE_WITH_STOP_STATE t where 1 = 1 and t.cxy_user_id is not null) a ";
		sql += " left join (select km.dept_id,sum (km.all_price) report_total_money,sum (km.num) report_num from konka_mobile_sail_data km where is_del=0 and 1 = 1 ";
		if (StringUtils.isNotBlank(sell_date_start)) {
			sql += " and km.sale_date >= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(sell_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(sell_date_end)) {
			sql += " and km.sale_date <= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(sell_date_end + " 23:59:59");
		}
		sql += " group by km.dept_id) b on  a.store_id = b.dept_id ";
		sql += " left join (select e.DEPT_ID,count(*) as report_count from ( SELECT distinct km.dept_id,to_char(km.SALE_DATE,'yyyy-MM-dd') as SALE_DATE FROM konka_mobile_sail_data km where 1 = 1 ";
		if (StringUtils.isNotBlank(sell_date_start)) {
			sql += " and km.sale_date >= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(sell_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(sell_date_end)) {
			sql += " and km.sale_date <= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(sell_date_end + " 23:59:59");
		}
		sql += " ) e group by e.DEPT_ID";
		sql += " ) d ON a.store_id = d.dept_id ";

		sql += " where 1 = 1 ";
		// 数据级别判断开始
		Long __dept_id = peProdUser.getDept_id(); // 默认为当前用户所在部门
		// 数据级别判断开始

		int max_dlevel = super.getMaxDLevel(peProdUser.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(Long.valueOf(__dept_id)); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = peProdUser.getDept_id(); // 默认为当前用户所在部门

			break;
		case 0:
			break;
		default:
			// 出错
		}

		String dept_id_in = "";
		List<KonkaDept> deptList2 = super.getKonkaDeptListOfUser(peProdUser.getId(), true);
		for (KonkaDept dept2 : deptList2) {
			if (dept_id_in != null && !dept_id_in.equals("")) {
				dept_id_in = dept_id_in + ",";
			}
			dept_id_in = dept_id_in == null ? "" : dept_id_in + dept2.getDept_id() + "";
		}

		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			// sql += "  	 AND rownum<100 ";
			break;
		case 8:
			// 分公司及以下部门可见
			sql += "  	 AND a.dept_id IN ( " + dept_id_in + " )";
			break;
		case 7:
			// 我所在的部门及以下部门可见
			sql += "  	 AND (a.l4_dept_id IN ( " + dept_id_in + " ) or a.l5_dept_id in ( " + dept_id_in + " ))";

			break;
		case 0:
			String job_id = peProdUser.getJob_id();
			sql += "AND ( (ywy_job_id = '" + job_id + "' or ywzg_job_id = '" + job_id + "' or jbjl_job_id ='" + job_id
					+ "')";
			sql += "  	or   a.konka_r3_id IN ( select konka_r3_id from BRANCH_ASSIGN t where t.USER_ID = "
					+ peProdUser.getId() + "  ))  ";
			break;
		default:
			// 集团及以下部门可见
			String job_id1 = peProdUser.getJob_id();
			sql += "AND ( (ywy_job_id = '" + job_id1 + "' or ywzg_job_id = '" + job_id1 + "' or jbjl_job_id ='"
					+ job_id1 + "')";
			sql += "  	or   a.konka_r3_id IN ( select konka_r3_id from BRANCH_ASSIGN t where t.USER_ID = "
					+ peProdUser.getId() + "  ))  ";
			// 出错
		}
		if (max_dlevel == 0) {
			// sql +=
			// "  	 AND a.konka_r3_id IN ( select konka_r3_id from BRANCH_ASSIGN t where t.USER_ID = "
			// + peProdUser.getId() + "  )";
		}

		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and a.DEPT_ID = ? ";
			array.add(dept_id);
		}
		if (StringUtils.isNotBlank(l4_dept_id)) {
			sql += " and a.l4_dept_id = ? ";
			array.add(l4_dept_id);
		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			sql += " and a.l5_dept_id = ? ";
			array.add(l5_dept_id);
		}
		if (StringUtils.isNotBlank(store_name_like)) {
			sql += " and a.STORE_NAME like '%' ||?|| '%' ";
			array.add(store_name_like);
		}
		if (StringUtils.isNotBlank(cxy_user_name_like)) {
			sql += " and a.CXY_USER_NAME like '%' ||?|| '%' ";
			array.add(cxy_user_name_like);
		}

		sql += " order by a.dept_name asc,a.dept_id asc,a.l4_dept_id asc,a.l4_dept_name asc,a.l5_dept_id asc,a.l5_dept_name asc,";
		sql += " a.ywy_user_name asc,a.ywy_dept_id asc,a.konka_r3_id asc,a.r3_code asc,a.customer_name  asc, ";
		sql += " a.merged_r3_code asc,a.store_id asc,a.store_name asc) c where 1 = 1";
		if (StringUtils.isNotBlank(is_report)) {
			sql += " and c.is_report = ? ";
			array.add(is_report);
		}

		request.setAttribute("entityList", super.getFacade().getBaseReportService().getBaseReportForBindToArray(sql,
				array.toArray()));

		 logger.info("sql============={}", sql);
		 if (null != array && array.size() > 0) {
		 String array_string = "";
		 for (int i = 0; i < array.size(); i++) {
		 array_string += array.get(i) + ",";
		 }
		 log.info("**********array:" + array.size());
		 log.info("**********array_string:" + array_string);
		 }
		return mapping.findForward("list");
	}

}