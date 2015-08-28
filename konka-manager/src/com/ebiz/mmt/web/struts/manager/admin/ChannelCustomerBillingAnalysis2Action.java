package com.ebiz.mmt.web.struts.manager.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KhjxQueryResult;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * 客户管理 > 渠道统计分析 > 客户结算统计
 * 
 * @author zhou
 */
public class ChannelCustomerBillingAnalysis2Action extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		String add_date_st = DateFormatUtils.format(c.getTime(), "yyyy-MM-dd"); // 取当月第一天
		dynaBean.set("begindate", add_date_st);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		String add_date_en = DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd");
		dynaBean.set("enddate", add_date_en);

		// List<KonkaCategory> konkaCategoryList = new
		// ArrayList<KonkaCategory>();// 客户大类和小类
		List<KonkaCategory> p_CategoryList = new ArrayList<KonkaCategory>();// 去重后的客户大类
		// 名称

		// KonkaCategory kc = new KonkaCategory();
		// kc.setC_type(10);
		// kc.setIs_del(0);
		// konkaCategoryList =
		// super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc);
		// request.setAttribute("konkaCategoryList", konkaCategoryList);
		// 客户大类
		KonkaCategory kc1 = new KonkaCategory();
		p_CategoryList = super.getFacade().getKonkaCategoryService().getKonkaCategoryGroupByCCommList(kc1);
		request.setAttribute("p_CategoryList", p_CategoryList);

		Pager pager = (Pager) dynaBean.get("pager");

		pager.init(0l, 40, "0");

		dynaBean.set("pager", pager);

		return mapping.findForward("list");
	}


	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// filter
		DynaBean dynaBean = (DynaBean) form;

		Pager pager = (Pager) dynaBean.get("pager");

		String begindate = (String) dynaBean.get("begindate");
		String enddate = (String) dynaBean.get("enddate");
		String songdf = (String) dynaBean.get("songdf");// 送达方
		String r3code = (String) dynaBean.get("r3code");// 售达方
		String username = (String) dynaBean.get("userName");
		String customerName = (String) dynaBean.get("customerName");// 客户名称

		String modelcode = (String) dynaBean.get("modelcode");// 机型

		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");

		String selectedFields = (String) dynaBean.get("selectedFields");
		String groupFields = "";
		// 客户大类小类 如果大类为空，算小类，小类为空，算不传值
		String v_c_comm = (String) dynaBean.get("v_customer_type");// 大类(名字)
		String v_c_name = (String) dynaBean.get("v_customer_type2");// 小类(id)

		List<KonkaCategory> konkaCategoryList = new ArrayList<KonkaCategory>();// 客户大类和小类
		List<KonkaCategory> p_CategoryList = new ArrayList<KonkaCategory>();// 去重后的客户大类
		// 名称

		KonkaCategory kk = new KonkaCategory();
		kk.setC_type(10);
		kk.setIs_del(0);
		konkaCategoryList = super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kk);
		request.setAttribute("konkaCategoryList", konkaCategoryList);
		// 客户大类
		KonkaCategory kc1 = new KonkaCategory();
		p_CategoryList = super.getFacade().getKonkaCategoryService().getKonkaCategoryGroupByCCommList(kc1);
		request.setAttribute("p_CategoryList", p_CategoryList);

		String customerType = "";

		// 如果大类不能为,而小类为空,需要把大类下面的所有小类查询出来
		if (StringUtils.isNotEmpty(v_c_comm) && StringUtils.isEmpty(v_c_name)) {
			KonkaCategory kc = new KonkaCategory();
			kc.setC_type(10);
			kc.setIs_del(0);
			kc.setC_comm(v_c_comm);

			List<KonkaCategory> kclist = super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc);

			StringBuilder sb = new StringBuilder("");
			for (KonkaCategory k : kclist) {
				sb.append(k.getC_index()).append(",");
			}
			if (sb.length() > 0)
				sb.deleteCharAt(sb.length() - 1);
			customerType = sb.toString();

		}
		if (StringUtils.isNotEmpty(v_c_name)) {
			customerType = v_c_name;
		}

		if (StringUtils.isEmpty(begindate)) {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_MONTH, 1);
			begindate = DateFormatUtils.format(c.getTime(), "yyyy-MM-dd"); // 取当月第一天
		}
		if (StringUtils.isEmpty(enddate)) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			enddate = DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd");
		}

		dynaBean.set("begindate", begindate);
		dynaBean.set("enddate", enddate);

		// filter
		// begindate = "2014-05-01";// not null
		// enddate = "2014-05-31";// not null
		// songdf = "";// 送达方
		// username = "";// 业务员id
		// customerName = "";// like
		// customerType = "";// number
		// r3code = "F14701001";// r3code ,shoudaf ;F14701001
		// modelcode = "";// 机型LED24F2260CE
		// salescode = "KF47";// 销售组织 根据部门deptid来求?

		// 字段名
		if (selectedFields != null && !"".equals(selectedFields)) {
			groupFields = selectedFields;
			selectedFields = selectedFields + ", ";

			for (String s : selectedFields.trim().split(",")) {
				if ("f.DEPT_NAME".equals(s.trim())) {
					dynaBean.set("DEPT_NAME", "f.DEPT_NAME");
				}
				if ("f.L4_DEPT_NAME".equals(s.trim())) {
					dynaBean.set("L4_DEPT_NAME", "f.L4_DEPT_NAME");
				}
				if ("f.L5_DEPT_NAME".equals(s.trim())) {
					dynaBean.set("L5_DEPT_NAME", "f.L5_DEPT_NAME");
				}
				if ("f.COLUMN_1".equals(s.trim())) {
					dynaBean.set("COLUMN_1", "f.COLUMN_1");
				}
				if ("f.COLUMN_5".equals(s.trim())) {
					dynaBean.set("COLUMN_5", "f.COLUMN_5");
				}
				if ("f.CUSTOMER_TYPE".equals(s.trim())) {
					dynaBean.set("CUSTOMER_TYPE", "f.CUSTOMER_TYPE");
				}
				if ("f.CUSTOMER_TYPE_NAME".equals(s.trim())) {
					dynaBean.set("CUSTOMER_TYPE_NAME", "f.CUSTOMER_TYPE_NAME");
				}
				if ("f.YWY_USER_NAME".equals(s.trim())) {
					dynaBean.set("YWY_USER_NAME", "f.YWY_USER_NAME");
				}
				if ("f.COLUMN_11".equals(s.trim())) {
					dynaBean.set("COLUMN_11", "f.COLUMN_11");
				}

			}

		} else {
			selectedFields = "f.DEPT_NAME, f.L4_DEPT_NAME, f.L5_DEPT_NAME, f.COLUMN_1, f.COLUMN_5, "
					+ "f.CUSTOMER_TYPE, f.CUSTOMER_TYPE_NAME, f.YWY_USER_NAME, f.COLUMN_11, ";
			groupFields = "f.DEPT_NAME, f.L4_DEPT_NAME, f.L5_DEPT_NAME, f.COLUMN_1, f.COLUMN_5, "
					+ "f.CUSTOMER_TYPE, f.CUSTOMER_TYPE_NAME, f.YWY_USER_NAME, f.COLUMN_11 ";
		}

		List<KhjxQueryResult> entityList = new ArrayList<KhjxQueryResult>();
		// 记录数
		int recordCount = 0;
//		recordCount = this.getKhjxDataCount(begindate, enddate, songdf, username, customerName, customerType, r3code,
//				modelcode, selectedFields, groupFields, dept_id, l4_dept_id, l5_dept_id);
//		pager.init((long) recordCount, 40, pager.getRequestPage());
//
//		int first = pager.getFirstRow();
//		int count = pager.getFirstRow() + pager.getPageSize();
//		entityList = this.getKhjxData(begindate, enddate, songdf, username, customerName, customerType, r3code,
//				modelcode, selectedFields, groupFields, dept_id, l4_dept_id, l5_dept_id, first, count);

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	/**
	 * 
	 * 用V_ORG_OF_CUSTOMER_ALL 与订单表关联,往上填充数据,确保数据落在各个部门中
	 * 但如果去年有客户在不同部门调去年同期等数据是不正确的.
	 * 
	 * @return
	 */
	protected List<KhjxQueryResult> getKhjxData(String begindate, String enddate, String songdf, String username,
			String customerName, String customerType, String r3code, String modelcode, String selectedFields,
			String groupFields, String fgsid, String jybid, String bscid, int first, int count) {

		StringBuilder sb = new StringBuilder();
		List<String> array = new ArrayList<String>();
		// with sql
		sb.append("WITH data AS (SELECT a.DEPT_ID, a.DEPT_SN, a.DEPT_NAME, a.L4_DEPT_ID, a.L4_DEPT_NAME,");
		sb.append("a.L5_DEPT_ID, a.L5_DEPT_NAME, a.USER_ID, a.YWY_USER_NAME,").append(" ");
		sb.append("a.YWY_DEPT_ID, a.R3_CODE, a.CUSTOMER_NAME, a.CUSTOMER_TYPE,");
		sb.append("a.CUSTOMER_TYPE_NAME, b.COLUMN_1, b.COLUMN_5, b.COLUMN_8, b.COLUMN_9,");
		sb.append("b.COLUMN_11, nvl(b.COLUMN_15, 0) COLUMN_15,");
		sb.append("nvl(b.COLUMN_16, 0) COLUMN_16, nvl(b.COLUMN_17, 0) COLUMN_17,");
		sb.append("nvl(b.COLUMN_13, 0) COLUMN_13, nvl(b.COLUMN_12, 0) COLUMN_12,");
		sb.append("nvl(b.COLUMN_27, 0) COLUMN_27, b.COLUMN_25, b.COLUMN_26").append(" ");
		sb.append("FROM MV_ORG_OF_CUSTOMER_ALL a").append(" ");
		sb.append("LEFT JOIN CHANNEL_DATA_IMPORT b ON a.R3_CODE = b.column_1)").append(" ");

		// select sql
		sb.append("SELECT *").append(" ");
		sb.append("FROM (SELECT t_.*, rownum rn_").append(" ");
		sb.append("FROM (SELECT ");

		// selected fields
		sb.append(selectedFields);//

		// DEPT_NAME,
		// L4_DEPT_NAME,
		// L5_DEPT_NAME,
		// CUSTOMER_TYPE_NAME,
		// COLUMN_1,
		// COLUMN_5,
		// YWY_USER_NAME,
		// COLUMN_11

		// sb.append("f.DEPT_NAME, f.L4_DEPT_NAME, f.L5_DEPT_NAME,f.CUSTOMER_TYPE_NAME, f.CUSTOMER_NAME, f.column_1,");
		// sb.append("f.column_5, f.ywy_user_name, f.COLUMN_11,");

		// sb.append("count(DISTINCT f.COLUMN_1) AS bill_customer_count,");
		sb.append("sum(f.c_all_amount) c_all_amount,");
		sb.append("sum(f.c_all_money) c_all_money,");
		sb.append("sum(f.c_all_k007) c_all_k007,");
		sb.append("sum(f.c_all_rb00) c_all_rb00,");
		sb.append("sum(f.p_all_amount) p_all_amount,");
		sb.append("sum(f.p_all_money) p_all_money,");
		sb.append("(round(decode(sum(nvl(f.p_all_amount, 0)),");
		sb.append("0,");
		sb.append("0,");
		sb.append("(sum(f.c_all_amount) - sum(f.p_all_amount)) / sum(f.p_all_amount)), 2) * 100) p_amount,");
		sb.append("(round(decode(sum(nvl(f.p_all_money, 0)),");
		sb.append("0,");
		sb.append("0,");
		sb.append("(sum(f.c_all_money) - sum(f.p_all_money)) / sum(f.p_all_money)), 2) * 100) p_money").append(" ");
		sb.append("FROM ");
		sb.append("(SELECT c.DEPT_ID, c.DEPT_NAME, c.L4_DEPT_ID,");
		sb.append("c.L4_DEPT_NAME, c.L5_DEPT_ID, c.L5_DEPT_NAME,");
		sb.append("c.USER_ID, c.YWY_USER_NAME, c.CUSTOMER_NAME,");
		sb.append("c.COLUMN_1, c.COLUMN_5, c.CUSTOMER_TYPE,");
		sb.append("c.CUSTOMER_TYPE_NAME, c.COLUMN_11,");
		sb.append("nvl(sum(c.COLUMN_12), 0) c_all_amount,");
		sb.append("nvl(sum(c.COLUMN_17), 0) c_all_money,");
		sb.append("nvl(sum(c.COLUMN_15), 0) c_all_k007,");
		sb.append("nvl(sum(c.COLUMN_16), 0) c_all_rb00,");

		// p_all_amount
		sb.append("(SELECT nvl(sum(nvl(d.COLUMN_12, 0)), 0)").append(" ");
		sb.append("FROM data d").append(" ");
		// p_all_amount filter1
		sb.append("WHERE 1=1").append(" ");
		sb.append("AND d.COLUMN_1 = c.COLUMN_1").append(" ");
		sb.append("AND d.COLUMN_25 = c.COLUMN_25").append(" ");
		sb.append("AND d.COLUMN_11 = c.COLUMN_11").append(" ");
		//
		// and d.dept_id = c.DEPT_ID
		// and d.L4_DEPT_ID = c.L4_DEPT_ID
		// and d.L5_DEPT_ID = c.L5_DEPT_ID
		// and d.CUSTOMER_NAME = c.CUSTOMER_NAME
		// and d.YWY_USER_NAME = c.YWY_USER_NAME
		// and d.CUSTOMER_TYPE = c.CUSTOMER_TYPE
		// and d.COLUMN_5 = c.COLUMN_5
		//
		if (StringUtils.isNotBlank(begindate)) {
			sb.append("AND to_date (d.COLUMN_26, 'yyyy-MM-dd hh24:mi:ss') >= add_months (to_date (?, 'yyyy-MM-dd hh24:mi:ss'), -12) ");
			array.add(begindate + " 00:00:00");
		}
		if (StringUtils.isNotBlank(enddate)) {
			sb.append("AND to_date (d.COLUMN_26, 'yyyy-MM-dd hh24:mi:ss') <= add_months (to_date (?, 'yyyy-MM-dd hh24:mi:ss'), -12) ");
			array.add(enddate + " 23:59:59");
		}

		if (StringUtils.isNotBlank(fgsid)) {
			sb.append("and d.dept_id = c.DEPT_ID").append(" ");
		}

		if (StringUtils.isNotBlank(jybid)) {
			sb.append("and d.L4_DEPT_ID = c.L4_DEPT_ID").append(" ");
		}
		if (StringUtils.isNotBlank(bscid)) {
			sb.append("and d.L5_DEPT_ID = c.L5_DEPT_ID").append(" ");
		}

		if (StringUtils.isNotBlank(customerName)) {
			sb.append("and d.CUSTOMER_NAME = c.CUSTOMER_NAME").append(" ");
		}
		if (StringUtils.isNotBlank(username)) {
			sb.append("and d.YWY_USER_NAME = c.YWY_USER_NAME ").append(" ");
			array.add(username);
		}
		if (StringUtils.isNotBlank(customerType)) {
			sb.append("and d.CUSTOMER_TYPE = c.CUSTOMER_TYPE");
		}
		if (StringUtils.isNotBlank(songdf)) {
			sb.append("and d.COLUMN_5 = c.COLUMN_5").append(" ");
		}

		// sb.append("AND to_date(d.COLUMN_26, 'yyyy-MM-dd hh24:mi:ss')").append(" ");
		// sb.append(">= add_months(to_date('2014-05-30','yyyy-MM-dd hh24:mi:ss'),-12)").append(" ");
		// sb.append("AND to_date(d.COLUMN_26, 'yyyy-MM-dd hh24:mi:ss')").append(" ");
		// sb.append("<= add_months(to_date('2014-06-30', 'yyyy-MM-dd hh24:mi:ss'),-12)").append(" ");
		// sb.append("AND d.dept_id = '8'").append(" ");
		// sb.append("AND d.COLUMN_1 = 'F112745'").append(" ");

		sb.append(") p_all_amount,");

		// p_all_money
		sb.append("(SELECT nvl(sum(nvl(d.COLUMN_17, 0)), 0)").append(" ");
		sb.append("FROM data d").append(" ");
		// p_all_money filter1
		sb.append("WHERE 1=1").append(" ");
		sb.append("AND d.COLUMN_1 = c.COLUMN_1").append(" ");
		sb.append("AND d.COLUMN_25 = c.COLUMN_25").append(" ");
		sb.append("AND d.COLUMN_11 = c.COLUMN_11").append(" ");
		//
		// and d.dept_id = c.DEPT_ID
		// and d.L4_DEPT_ID = c.L4_DEPT_ID
		// and d.L5_DEPT_ID = c.L5_DEPT_ID
		// and d.CUSTOMER_NAME = c.CUSTOMER_NAME
		// and d.YWY_USER_NAME = c.YWY_USER_NAME
		// and d.CUSTOMER_TYPE = c.CUSTOMER_TYPE
		// and d.COLUMN_5 = c.COLUMN_5
		//
		if (StringUtils.isNotBlank(begindate)) {
			sb.append("AND to_date (d.COLUMN_26, 'yyyy-MM-dd hh24:mi:ss') >= add_months (to_date (?, 'yyyy-MM-dd hh24:mi:ss'), -12) ");
			array.add(begindate + " 00:00:00");
		}
		if (StringUtils.isNotBlank(enddate)) {
			sb.append("AND to_date (d.COLUMN_26, 'yyyy-MM-dd hh24:mi:ss') <= add_months (to_date (?, 'yyyy-MM-dd hh24:mi:ss'), -12) ");
			array.add(enddate + " 23:59:59");
		}

		if (StringUtils.isNotBlank(fgsid)) {
			sb.append("and d.dept_id = c.DEPT_ID").append(" ");
		}

		if (StringUtils.isNotBlank(jybid)) {
			sb.append("and d.L4_DEPT_ID = c.L4_DEPT_ID").append(" ");
		}
		if (StringUtils.isNotBlank(bscid)) {
			sb.append("and d.L5_DEPT_ID = c.L5_DEPT_ID").append(" ");
		}

		if (StringUtils.isNotBlank(customerName)) {
			sb.append("and d.CUSTOMER_NAME = c.CUSTOMER_NAME").append(" ");
		}
		if (StringUtils.isNotBlank(username)) {
			sb.append("and d.YWY_USER_NAME = c.YWY_USER_NAME ").append(" ");
			array.add(username);
		}
		if (StringUtils.isNotBlank(customerType)) {
			sb.append("and d.CUSTOMER_TYPE = c.CUSTOMER_TYPE");
		}
		if (StringUtils.isNotBlank(songdf)) {
			sb.append("and d.COLUMN_5 = c.COLUMN_5").append(" ");
		}

		sb.append(") p_all_money").append(" ");

		sb.append("FROM data c").append(" ");
		sb.append("WHERE 1 = 1").append(" ");
		// filter2
		// sb.append("AND to_date(c.COLUMN_26, 'yyyy-MM-dd hh24:mi:ss') ").append(" ");
		// sb.append(">= to_date('2014-05-30', 'yyyy-MM-dd hh24:mi:ss')").append(" ");
		// sb.append(" AND to_date(c.COLUMN_26, 'yyyy-MM-dd hh24:mi:ss') ").append(" ");
		// sb.append("<= to_date('2014-06-30', 'yyyy-MM-dd hh24:mi:ss')").append(" ");
		//

		if (StringUtils.isNotEmpty(begindate)) {
			sb.append("AND to_date (c.COLUMN_26, 'yyyy-MM-dd hh24:mi:ss') >= to_date (?, 'yyyy-MM-dd hh24:mi:ss') ");
			array.add(begindate + " 00:00:00");
		}
		if (StringUtils.isNotEmpty(enddate)) {
			sb.append("AND to_date (c.COLUMN_26, 'yyyy-MM-dd hh24:mi:ss') <= to_date (?, 'yyyy-MM-dd hh24:mi:ss') ");
			array.add(enddate + " 23:59:59");
		}

		// and c.DEPT_ID = '8'
		// and c.L4_DEPT_ID =''
		// and c.L5_DEPT_ID = ''
		// and c.CUSTOMER_NAME = ''
		// and c.YWY_USER_NAME = ''
		// and c.CUSTOMER_TYPE= ''
		// and c.COLUMN_1 = 'F112745'
		// and c.COLUMN_5 = ''
		// and c.COLUMN_11 = ''

		if (StringUtils.isNotBlank(fgsid)) {
			sb.append("AND c.DEPT_ID = ? ").append(" ");
			array.add(fgsid);
		}
		if (StringUtils.isNotBlank(jybid)) {
			sb.append("AND c.L4_DEPT_ID = ? ").append(" ");
			array.add(jybid);
		}
		if (StringUtils.isNotBlank(bscid)) {
			sb.append("AND c.L5_DEPT_ID = ? ").append(" ");
			array.add(bscid);
		}

		if (StringUtils.isNotBlank(customerName)) {
			sb.append("AND c.CUSTOMER_NAME like '%'||?||'%'  ").append(" ");
			array.add(customerName);
		}

		if (StringUtils.isNotBlank(customerType)) {
			sb.append("AND c.CUSTOMER_TYPE in (").append(customerType).append(")");
		}

		if (StringUtils.isNotBlank(r3code)) {
			sb.append("AND UPPER(c.COLUMN_1) = ? ");
			array.add(r3code);
		}
		if (StringUtils.isNotBlank(songdf)) {
			sb.append("AND UPPER(c.COLUMN_5) = ? ");
			array.add(songdf);
		}
		if (StringUtils.isNotBlank(modelcode)) {
			sb.append("AND UPPER(c.COLUMN_11) = ? ");
			array.add(modelcode);
		}

		sb.append("GROUP BY c.DEPT_ID, c.DEPT_NAME, c.L4_DEPT_ID,");
		sb.append(" c.L4_DEPT_NAME, c.L5_DEPT_ID, c.L5_DEPT_NAME,");
		sb.append(" c.USER_ID, c.YWY_USER_NAME, c.CUSTOMER_NAME,");
		sb.append(" c.CUSTOMER_TYPE, c.CUSTOMER_TYPE_NAME,");
		sb.append(" c.COLUMN_25, c.COLUMN_1, c.COLUMN_5,");
		sb.append(" c.COLUMN_11) f").append(" ");// f

		sb.append("GROUP BY ");

		// sb.append("f.DEPT_NAME, f.L4_DEPT_NAME, f.L5_DEPT_NAME,");
		// sb.append("f.CUSTOMER_TYPE_NAME, f.CUSTOMER_NAME, f.column_1,");
		// sb.append("f.column_5, f.ywy_user_name, f.COLUMN_11").append(" ");

		sb.append(groupFields);
		sb.append(" )t_ ");
		sb.append("WHERE rownum <= ?)").append(" ");
		array.add(count + "");
		sb.append("WHERE rn_ >= (?+1)").append(" ");
		array.add(first + "");
		// <![CDATA[ ) t_ where rownum <= (#row.first# + #row.count#)) where rn_
		// >= (#row.first# + 1) ]]>

		// //System.out.println("datasql:" + sb.toString());

        // 在优化好之前，不开放使用
        // List<KhjxQueryResult> list =
        // getFacade().getChannelDataImportReportService().getKhjxDataFromChannelDataImport(
        // sb.toString(), array.toArray());
        List<KhjxQueryResult> list = new ArrayList<KhjxQueryResult>();
		return list;
	}

	protected int getKhjxDataCount(String begindate, String enddate, String songdf, String username,
			String customerName, String customerType, String r3code, String modelcode, String selectedFields,
			String groupFields, String fgsid, String jybid, String bscid) {

		int l = 0;

		StringBuilder sb = new StringBuilder();
		List<String> array = new ArrayList<String>();
		// with sql
		sb.append("WITH data AS (SELECT a.DEPT_ID, a.DEPT_SN, a.DEPT_NAME, a.L4_DEPT_ID, a.L4_DEPT_NAME,");
		sb.append("a.L5_DEPT_ID, a.L5_DEPT_NAME, a.USER_ID, a.YWY_USER_NAME,").append(" ");
		sb.append("a.YWY_DEPT_ID, a.R3_CODE, a.CUSTOMER_NAME, a.CUSTOMER_TYPE,");
		sb.append("a.CUSTOMER_TYPE_NAME, b.COLUMN_1, b.COLUMN_5, b.COLUMN_8, b.COLUMN_9,");
		sb.append("b.COLUMN_11, nvl(b.COLUMN_15, 0) COLUMN_15,");
		sb.append("nvl(b.COLUMN_16, 0) COLUMN_16, nvl(b.COLUMN_17, 0) COLUMN_17,");
		sb.append("nvl(b.COLUMN_13, 0) COLUMN_13, nvl(b.COLUMN_12, 0) COLUMN_12,");
		sb.append("nvl(b.COLUMN_27, 0) COLUMN_27, b.COLUMN_25, b.COLUMN_26").append(" ");
		sb.append("FROM MV_ORG_OF_CUSTOMER_ALL a").append(" ");
		sb.append("LEFT JOIN CHANNEL_DATA_IMPORT b ON a.R3_CODE = b.column_1)").append(" ");

		// select sql
		sb.append("SELECT count(1) howmany").append(" ");
		sb.append("FROM (SELECT ");

		// selected fields
		sb.append(selectedFields);//

		// DEPT_NAME,
		// L4_DEPT_NAME,
		// L5_DEPT_NAME,
		// CUSTOMER_TYPE_NAME,
		// COLUMN_1,
		// COLUMN_5,
		// YWY_USER_NAME,
		// COLUMN_11

		// sb.append("f.DEPT_NAME, f.L4_DEPT_NAME, f.L5_DEPT_NAME,f.CUSTOMER_TYPE_NAME, f.CUSTOMER_NAME, f.column_1,");
		// sb.append("f.column_5, f.ywy_user_name, f.COLUMN_11,");

		// sb.append("count(DISTINCT f.COLUMN_1) AS bill_customer_count,");
		sb.append("sum(f.c_all_amount) c_all_amount,");
		sb.append("sum(f.c_all_money) c_all_money,");
		sb.append("sum(f.c_all_k007) c_all_k007,");
		sb.append("sum(f.c_all_rb00) c_all_rb00,");
		sb.append("sum(f.p_all_amount) p_all_amount,");
		sb.append("sum(f.p_all_money) p_all_money,");
		sb.append("(round(decode(sum(nvl(f.p_all_amount, 0)),");
		sb.append("0,");
		sb.append("0,");
		sb.append("(sum(f.c_all_amount) - sum(f.p_all_amount)) / sum(f.p_all_amount)), 2) * 100) p_amount,");
		sb.append("(round(decode(sum(nvl(f.p_all_money, 0)),");
		sb.append("0,");
		sb.append("0,");
		sb.append("(sum(f.c_all_money) - sum(f.p_all_money)) / sum(f.p_all_money)), 2) * 100) p_money").append(" ");
		sb.append("FROM ");
		sb.append("(SELECT c.DEPT_ID, c.DEPT_NAME, c.L4_DEPT_ID,");
		sb.append("c.L4_DEPT_NAME, c.L5_DEPT_ID, c.L5_DEPT_NAME,");
		sb.append("c.USER_ID, c.YWY_USER_NAME, c.CUSTOMER_NAME,");
		sb.append("c.COLUMN_1, c.COLUMN_5, c.CUSTOMER_TYPE,");
		sb.append("c.CUSTOMER_TYPE_NAME, c.COLUMN_11,");
		sb.append("nvl(sum(c.COLUMN_12), 0) c_all_amount,");
		sb.append("nvl(sum(c.COLUMN_17), 0) c_all_money,");
		sb.append("nvl(sum(c.COLUMN_15), 0) c_all_k007,");
		sb.append("nvl(sum(c.COLUMN_16), 0) c_all_rb00,");

		// p_all_amount
		sb.append("(SELECT nvl(sum(nvl(d.COLUMN_12, 0)), 0)").append(" ");
		sb.append("FROM data d").append(" ");
		// p_all_amount filter1
		sb.append("WHERE 1=1").append(" ");
		sb.append("AND d.COLUMN_1 = c.COLUMN_1").append(" ");
		sb.append("AND d.COLUMN_25 = c.COLUMN_25").append(" ");
		sb.append("AND d.COLUMN_11 = c.COLUMN_11").append(" ");
		//
		// and d.dept_id = c.DEPT_ID
		// and d.L4_DEPT_ID = c.L4_DEPT_ID
		// and d.L5_DEPT_ID = c.L5_DEPT_ID
		// and d.CUSTOMER_NAME = c.CUSTOMER_NAME
		// and d.YWY_USER_NAME = c.YWY_USER_NAME
		// and d.CUSTOMER_TYPE = c.CUSTOMER_TYPE
		// and d.COLUMN_5 = c.COLUMN_5
		//
		if (StringUtils.isNotBlank(begindate)) {
			sb.append("AND to_date (d.COLUMN_26, 'yyyy-MM-dd hh24:mi:ss') >= add_months (to_date (?, 'yyyy-MM-dd hh24:mi:ss'), -12) ");
			array.add(begindate + " 00:00:00");
		}
		if (StringUtils.isNotBlank(enddate)) {
			sb.append("AND to_date (d.COLUMN_26, 'yyyy-MM-dd hh24:mi:ss') <= add_months (to_date (?, 'yyyy-MM-dd hh24:mi:ss'), -12) ");
			array.add(enddate + " 23:59:59");
		}

		if (StringUtils.isNotBlank(fgsid)) {
			sb.append("and d.dept_id = c.DEPT_ID").append(" ");
		}

		if (StringUtils.isNotBlank(jybid)) {
			sb.append("and d.L4_DEPT_ID = c.L4_DEPT_ID").append(" ");
		}
		if (StringUtils.isNotBlank(bscid)) {
			sb.append("and d.L5_DEPT_ID = c.L5_DEPT_ID").append(" ");
		}

		if (StringUtils.isNotBlank(customerName)) {
			sb.append("and d.CUSTOMER_NAME = c.CUSTOMER_NAME").append(" ");
		}
		if (StringUtils.isNotBlank(username)) {
			sb.append("and d.YWY_USER_NAME = c.YWY_USER_NAME ").append(" ");
			array.add(username);
		}
		if (StringUtils.isNotBlank(customerType)) {
			sb.append("and d.CUSTOMER_TYPE = c.CUSTOMER_TYPE");
		}
		if (StringUtils.isNotBlank(songdf)) {
			sb.append("and d.COLUMN_5 = c.COLUMN_5").append(" ");
		}

		// sb.append("AND to_date(d.COLUMN_26, 'yyyy-MM-dd hh24:mi:ss')").append(" ");
		// sb.append(">= add_months(to_date('2014-05-30','yyyy-MM-dd hh24:mi:ss'),-12)").append(" ");
		// sb.append("AND to_date(d.COLUMN_26, 'yyyy-MM-dd hh24:mi:ss')").append(" ");
		// sb.append("<= add_months(to_date('2014-06-30', 'yyyy-MM-dd hh24:mi:ss'),-12)").append(" ");
		// sb.append("AND d.dept_id = '8'").append(" ");
		// sb.append("AND d.COLUMN_1 = 'F112745'").append(" ");

		sb.append(") p_all_amount,");

		// p_all_money
		sb.append("(SELECT nvl(sum(nvl(d.COLUMN_17, 0)), 0)").append(" ");
		sb.append("FROM data d").append(" ");
		// p_all_money filter1
		sb.append("WHERE 1=1").append(" ");
		sb.append("AND d.COLUMN_1 = c.COLUMN_1").append(" ");
		sb.append("AND d.COLUMN_25 = c.COLUMN_25").append(" ");
		sb.append("AND d.COLUMN_11 = c.COLUMN_11").append(" ");
		//
		// and d.dept_id = c.DEPT_ID
		// and d.L4_DEPT_ID = c.L4_DEPT_ID
		// and d.L5_DEPT_ID = c.L5_DEPT_ID
		// and d.CUSTOMER_NAME = c.CUSTOMER_NAME
		// and d.YWY_USER_NAME = c.YWY_USER_NAME
		// and d.CUSTOMER_TYPE = c.CUSTOMER_TYPE
		// and d.COLUMN_5 = c.COLUMN_5
		//
		if (StringUtils.isNotBlank(begindate)) {
			sb.append("AND to_date (d.COLUMN_26, 'yyyy-MM-dd hh24:mi:ss') >= add_months (to_date (?, 'yyyy-MM-dd hh24:mi:ss'), -12) ");
			array.add(begindate + " 00:00:00");
		}
		if (StringUtils.isNotBlank(enddate)) {
			sb.append("AND to_date (d.COLUMN_26, 'yyyy-MM-dd hh24:mi:ss') <= add_months (to_date (?, 'yyyy-MM-dd hh24:mi:ss'), -12) ");
			array.add(enddate + " 23:59:59");
		}

		if (StringUtils.isNotBlank(fgsid)) {
			sb.append("and d.dept_id = c.DEPT_ID").append(" ");
		}

		if (StringUtils.isNotBlank(jybid)) {
			sb.append("and d.L4_DEPT_ID = c.L4_DEPT_ID").append(" ");
		}
		if (StringUtils.isNotBlank(bscid)) {
			sb.append("and d.L5_DEPT_ID = c.L5_DEPT_ID").append(" ");
		}

		if (StringUtils.isNotBlank(customerName)) {
			sb.append("and d.CUSTOMER_NAME = c.CUSTOMER_NAME").append(" ");
		}
		if (StringUtils.isNotBlank(username)) {
			sb.append("and d.YWY_USER_NAME = c.YWY_USER_NAME ").append(" ");
			array.add(username);
		}
		if (StringUtils.isNotBlank(customerType)) {
			sb.append("and d.CUSTOMER_TYPE = c.CUSTOMER_TYPE");
		}
		if (StringUtils.isNotBlank(songdf)) {
			sb.append("and d.COLUMN_5 = c.COLUMN_5").append(" ");
		}

		sb.append(") p_all_money").append(" ");

		sb.append("FROM data c").append(" ");
		sb.append("WHERE 1 = 1").append(" ");
		// filter2
		// sb.append("AND to_date(c.COLUMN_26, 'yyyy-MM-dd hh24:mi:ss') ").append(" ");
		// sb.append(">= to_date('2014-05-30', 'yyyy-MM-dd hh24:mi:ss')").append(" ");
		// sb.append(" AND to_date(c.COLUMN_26, 'yyyy-MM-dd hh24:mi:ss') ").append(" ");
		// sb.append("<= to_date('2014-06-30', 'yyyy-MM-dd hh24:mi:ss')").append(" ");
		//

		if (StringUtils.isNotEmpty(begindate)) {
			sb.append("AND to_date (c.COLUMN_26, 'yyyy-MM-dd hh24:mi:ss') >= to_date (?, 'yyyy-MM-dd hh24:mi:ss') ");
			array.add(begindate + " 00:00:00");
		}
		if (StringUtils.isNotEmpty(enddate)) {
			sb.append("AND to_date (c.COLUMN_26, 'yyyy-MM-dd hh24:mi:ss') <= to_date (?, 'yyyy-MM-dd hh24:mi:ss') ");
			array.add(enddate + " 23:59:59");
		}

		// and c.DEPT_ID = '8'
		// and c.L4_DEPT_ID =''
		// and c.L5_DEPT_ID = ''
		// and c.CUSTOMER_NAME = ''
		// and c.YWY_USER_NAME = ''
		// and c.CUSTOMER_TYPE= ''
		// and c.COLUMN_1 = 'F112745'
		// and c.COLUMN_5 = ''
		// and c.COLUMN_11 = ''

		if (StringUtils.isNotBlank(fgsid)) {
			sb.append("AND c.DEPT_ID = ? ").append(" ");
			array.add(fgsid);
		}
		if (StringUtils.isNotBlank(jybid)) {
			sb.append("AND c.L4_DEPT_ID = ? ").append(" ");
			array.add(jybid);
		}
		if (StringUtils.isNotBlank(bscid)) {
			sb.append("AND c.L5_DEPT_ID = ? ").append(" ");
			array.add(bscid);
		}

		if (StringUtils.isNotBlank(customerName)) {
			sb.append("AND c.CUSTOMER_NAME like '%'||?||'%'  ").append(" ");
			array.add(customerName);
		}

		if (StringUtils.isNotBlank(customerType)) {
			sb.append("AND c.CUSTOMER_TYPE in (").append(customerType).append(")");
		}

		if (StringUtils.isNotBlank(r3code)) {
			sb.append("AND UPPER(c.COLUMN_1) = ? ");
			array.add(r3code);
		}
		if (StringUtils.isNotBlank(songdf)) {
			sb.append("AND UPPER(c.COLUMN_5) = ? ");
			array.add(songdf);
		}
		if (StringUtils.isNotBlank(modelcode)) {
			sb.append("AND UPPER(c.COLUMN_11) = ? ");
			array.add(modelcode);
		}

		sb.append("GROUP BY c.DEPT_ID, c.DEPT_NAME, c.L4_DEPT_ID,");
		sb.append(" c.L4_DEPT_NAME, c.L5_DEPT_ID, c.L5_DEPT_NAME,");
		sb.append(" c.USER_ID, c.YWY_USER_NAME, c.CUSTOMER_NAME,");
		sb.append(" c.CUSTOMER_TYPE, c.CUSTOMER_TYPE_NAME,");
		sb.append(" c.COLUMN_25, c.COLUMN_1, c.COLUMN_5,");
		sb.append(" c.COLUMN_11) f").append(" ");// f

		sb.append("GROUP BY ");

		// sb.append("f.DEPT_NAME, f.L4_DEPT_NAME, f.L5_DEPT_NAME,");
		// sb.append("f.CUSTOMER_TYPE_NAME, f.CUSTOMER_NAME, f.column_1,");
		// sb.append("f.column_5, f.ywy_user_name, f.COLUMN_11").append(" ");

		sb.append(groupFields);
		sb.append(" )t_ ");

		// //System.out.println("countsql:" + sb.toString());
        // 想办法优化。在优化好之前不开放使用
        // l =
        // getFacade().getChannelDataImportReportService().getKhjxDataFromChannelDataImportCount(sb.toString(),
        // array.toArray());
        l = 0;
		return l;
	}

	@Override
	public ActionForward toExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		super.HtmltoExcel(request, response);
		return null;
	}

}
