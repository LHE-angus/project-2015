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
 * @version 2013-11-06
 */
public class KonkaSellUnReportDetailAction extends BaseAction {
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
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(peProdUser.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			if (null == konkaDept) {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else if (konkaDept.getDept_type() != null && konkaDept.getDept_type() == 3) {
				// 分公司
				dynaBean.set("dept_type", "3");
				dynaBean.set("dept_id", konkaDept.getDept_id().toString());
			} else if ((konkaDept.getDept_type() != null && konkaDept.getDept_type() == 4)
					|| (konkaDept.getDept_type() != null && konkaDept.getDept_type() == 5)) {
				// 经营处、办事处
				String msg = super.getMessage(request, "popedom.check.invalid");
				//super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				//return null;
			} else {
				String msg = super.getMessage(request, "popedom.check.invalid");
				//super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				//return null;
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
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(peProdUser.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			if (null == konkaDept) {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else if (konkaDept.getDept_type() == 3) {
				// 分公司
				dynaBean.set("dept_type", "3");
				dynaBean.set("dept_id", konkaDept.getDept_id().toString());
				dept_id = konkaDept.getDept_id().toString();
			} else if (konkaDept.getDept_type() == 4 || konkaDept.getDept_type() == 5) {
				// 经营处、办事处
				String msg = super.getMessage(request, "popedom.check.invalid");
				//super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				//return null;
			} else {
				String msg = super.getMessage(request, "popedom.check.invalid");
				//super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				//return null;
			}
		}

		List<String> array = new ArrayList<String>();

		String sql = " SELECT h.*,FUNC_GET_CXYNAME(h.store_id) as cxy_name FROM (SELECT f.DEPT_SN,f.DEPT_ID,f.DEPT_NAME,f.L4_DEPT_ID,f.L4_DEPT_NAME,f.L5_DEPT_ID,f.L5_DEPT_NAME,f.YWY_USER_NAME, ";
		sql += "		            f.YWY_DEPT_ID,f.KONKA_R3_ID,f.CUSTOMER_NAME,f.R3_CODE,f.MERGED_R3_CODE,f.STORE_ID,f.STORE_NAME,f.flag, to_char (f.SALE_DATE, 'yyyy-MM-dd') AS SALE_DATE,decode (d.report_count,NULL,'0','1') as is_report,value (d.report_count, 0),value (b.report_total_money, 0),value (b.report_num, 0) ";
		sql += " FROM (SELECT a.DEPT_SN,a.DEPT_ID,a.DEPT_NAME,a.L4_DEPT_ID,a.L4_DEPT_NAME,a.L5_DEPT_ID,a.L5_DEPT_NAME,a.YWY_USER_NAME, ";
		sql += "		            a.YWY_DEPT_ID,a.KONKA_R3_ID,a.CUSTOMER_NAME,a.R3_CODE,a.MERGED_R3_CODE,a.STORE_ID,a.STORE_NAME,a.flag, to_char (b.SALE_DATE, 'yyyy-MM-dd') AS SALE_DATE,ywy_job_id,ywzg_job_id,jbjl_job_id  ";
		sql += "       FROM (SELECT DISTINCT  t.DEPT_SN,t.DEPT_ID,t.DEPT_NAME,t.L4_DEPT_ID,t.L4_DEPT_NAME,t.L5_DEPT_ID,t.L5_DEPT_NAME,t.YWY_USER_NAME, ";
		sql += "		            t.YWY_DEPT_ID,t.KONKA_R3_ID,t.CUSTOMER_NAME,t.R3_CODE,t.MERGED_R3_CODE,t.STORE_ID,t.STORE_NAME,1 AS flag ,ywy_job_id,ywzg_job_id,jbjl_job_id ";
		sql += " 		     FROM V_ORG_OF_CXY_ALL_BY_STORE t WHERE 1 = 1 AND t.cxy_user_id IS NOT NULL) a,";
		sql += "			(SELECT 1 AS flag,trunc(to_date('" + sell_date_start
				+ "', 'yyyy-MM-dd')) + rownum - 1 AS sale_date	FROM ALL_OBJECTS ";
		sql += "  			 WHERE trunc (to_date ('" + sell_date_start + "', 'yyyy-MM-dd'))+ rownum - 1 <= trunc (to_date ('"
				+ sell_date_end + "', 'yyyy-MM-dd'))) b  WHERE a.FLAG = b.FLAG) f ";
		sql += " LEFT JOIN (SELECT aa.dept_id,aa.SALE_DATE,sum (aa.all_price) report_total_money,sum (aa.num) report_num ";
		sql += "            FROM (SELECT km.dept_id,km.all_price,km.num,to_char (km.SALE_DATE, 'yyyy-MM-dd') AS SALE_DATE ";
		sql += "   				  FROM konka_mobile_sail_data km WHERE is_del=0 and 1 = 1 ";
		if (StringUtils.isNotBlank(sell_date_start)) {
			sql += " and km.sale_date >= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(sell_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(sell_date_end)) {
			sql += " and km.sale_date <= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(sell_date_end + " 23:59:59");
		}
		sql += "  				 ) aa GROUP BY  aa.dept_id, aa.SALE_DATE) b ON  f.store_id = b.dept_id AND f.SALE_DATE = b.SALE_DATE ";
		sql += " LEFT JOIN (SELECT DISTINCT km.dept_id,to_char (km.SALE_DATE, 'yyyy-MM-dd') AS SALE_DATE,1 AS report_count ";
		sql += " 			FROM konka_mobile_sail_data km WHERE 1 = 1 ";
		if (StringUtils.isNotBlank(sell_date_start)) {
			sql += " and km.sale_date >= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(sell_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(sell_date_end)) {
			sql += " and km.sale_date <= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(sell_date_end + " 23:59:59");
		}
		sql += " 		   ) d ON  f.store_id = d.dept_id AND f.SALE_DATE = d.SALE_DATE where 1 = 1 ";

		
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
		String dept_id_in="";
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
			//sql += "  	 AND rownum<100 ";
			break;
		case 8:
			// 分公司及以下部门可见
			sql += "  	 AND f.dept_id IN ( " + __dept_id + " )";
			break;
		case 7:
			// 我所在的部门及以下部门可见
			sql += "  	 AND (f.l4_dept_id IN ( " + dept_id_in + " ) or f.l5_dept_id in ( " + dept_id_in + " ))";
			
			break;
			
		case 0:
			String job_id=peProdUser.getJob_id();
			sql += "AND ( (f.ywy_job_id = '" + job_id  + "' or f.ywzg_job_id = '" + job_id + "' or f.jbjl_job_id ='" + job_id + "')";
			sql += "  	or   f.konka_r3_id IN ( select konka_r3_id from BRANCH_ASSIGN t where t.USER_ID = " + peProdUser.getId() + "  ))  ";
			break;
		default:
			// 集团及以下部门可见
			String job_id1=peProdUser.getJob_id();
			sql += "AND ( (f.ywy_job_id = '" + job_id1  + "' or f.ywzg_job_id = '" + job_id1 + "' or f.jbjl_job_id ='" + job_id1 + "')";
			sql += "  	or   f.konka_r3_id IN ( select konka_r3_id from BRANCH_ASSIGN t where t.USER_ID = " + peProdUser.getId() + "  ))  ";
		}	
		if (max_dlevel == 0) {
			//sql += "  	 AND a.konka_r3_id IN ( select konka_r3_id from BRANCH_ASSIGN t where t.USER_ID = " + peProdUser.getId() + "  )";
		}
		
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and f.DEPT_ID = ? ";
			array.add(dept_id);
		}
		if (StringUtils.isNotBlank(l4_dept_id)) {
			sql += " and f.l4_dept_id = ? ";
			array.add(l4_dept_id);
		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			sql += " and f.l5_dept_id = ? ";
			array.add(l5_dept_id);
		}
		if (StringUtils.isNotBlank(store_name_like)) {
			sql += " and f.STORE_NAME like '%' ||?|| '%' ";
			array.add(store_name_like);
		}
		if (StringUtils.isNotBlank(cxy_user_name_like)) {
			sql += " and f.CXY_USER_NAME like '%' ||?|| '%' ";
			array.add(cxy_user_name_like);
		}

		sql += " ORDER BY f.SALE_DATE desc,f.dept_name ASC,f.dept_id ASC,f.l4_dept_id ASC,f.l4_dept_name ASC,f.l5_dept_id ASC,f.l5_dept_name ASC,f.ywy_user_name ASC,f.ywy_dept_id ASC,";
		sql += " f.konka_r3_id ASC,f.r3_code ASC,f.customer_name ASC,f.merged_r3_code ASC,f.store_id ASC,f.store_name ASC) h ";
		sql += " WHERE 1 = 1 ";
		if (StringUtils.isNotBlank(is_report)) {
			sql += " and h.is_report = ? ";
			array.add(is_report);
		}

		//System.out.println("executesql:" + sql);

		request.setAttribute("entityList",
				super.getFacade().getBaseReportService().getBaseReportForBindToArray(sql, array.toArray()));

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