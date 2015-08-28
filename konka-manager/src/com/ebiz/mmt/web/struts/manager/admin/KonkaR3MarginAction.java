package com.ebiz.mmt.web.struts.manager.admin;

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
 * @version 2013-07-15
 */
public class KonkaR3MarginAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		Date today = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		dynaBean.set("year", String.valueOf(calendar.get(Calendar.YEAR)));
		dynaBean.set("month", String.valueOf(calendar.get(Calendar.MONTH) + 1));

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		} else if (peProdUser.getUser_type() == 0) {
			// 康佳总部
			dynaBean.set("dept_type", "1");
			return this.list(mapping, form, request, response);
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
				return this.list(mapping, form, request, response);
			} else if (konkaDept.getDept_type() == 4 || konkaDept.getDept_type() == 5) {
				// 经营处、办事处
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		}
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String ywy_user_name = (String) dynaBean.get("ywy_user_name");
		String customer_name = (String) dynaBean.get("customer_name");
		String last_year = "";

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
			} else if (konkaDept.getDept_type() == 3) {
				// 分公司
				dynaBean.set("dept_type", "3");
				dynaBean.set("dept_id", konkaDept.getDept_id().toString());
				dept_id = konkaDept.getDept_id().toString();
			} else if (konkaDept.getDept_type() == 4 || konkaDept.getDept_type() == 5) {
				// 经营处、办事处
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		}

		if (StringUtils.isNotBlank(year)) {
			last_year = String.valueOf((Integer.valueOf(year) - 1));
		}
		if (StringUtils.isNotBlank(month)) {
			switch (Integer.valueOf(month)) {
			case 1:
				month = "01";
				break;
			case 2:
				month = "02";
				break;
			case 3:
				month = "03";
				break;
			case 4:
				month = "04";
				break;
			case 5:
				month = "05";
				break;
			case 6:
				month = "06";
				break;
			case 7:
				month = "07";
				break;
			case 8:
				month = "08";
				break;
			case 9:
				month = "09";
				break;
			default:
				break;
			}
		}
		List<?> entityList = this.getR3MarginList(year, last_year, month, dept_id, l4_dept_id, l5_dept_id,
				ywy_user_name, customer_name);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public List<?> getR3MarginList(String year, String last_year, String month, String dept_id, String l4_dept_id,
			String l5_dept_id, String ywy_user_name, String customer_name) {
		List<String> array = new ArrayList<String>();

		String sql = " select ff.*,decode(to_char(gg.pd_total_money),null,'-',0,'-',to_char(round(((ff.pd_total_money - gg.pd_total_money) / gg.pd_total_money),4) * 100)) as tb_pd_total_money, ";
		sql += " decode(to_char(gg.pd_count),null,'-',0,'-',to_char(round(((ff.pd_count - gg.pd_count) / gg.pd_count), 4) * 100)) as tb_pd_count,";
		sql += " decode(to_char(gg.pj_ml_money),null,'-',0,'-',to_char(round(((ff.pj_ml_money - gg.pj_ml_money) / gg.pj_ml_money), 4) * 100)) as pj_ml_money,";
		sql += " decode(to_char(gg.pj_unitprice),null,'-',0,'-',to_char(round(((ff.pj_unitprice - gg.pj_unitprice) / gg.pj_unitprice),4) * 100)) as tb_unitprice from ( ";

		sql += " select cc.r3_code,cc.customer_name,cc.category_name,cc.pd_total_money,cc.pd_count,decode(cc.pd_count, 0, 0, round((cc.ml_money / cc.pd_count), 4)) as pj_ml_money, ";
		sql += " decode(cc.pd_count,0,0,round((cc.pd_total_money / cc.pd_count), 4)) as pj_unitprice ";
		sql += " from (select bb.r3_code,bb.customer_name,bb.category_name,sum(bb.pd_total_money) as pd_total_money,sum(bb.pd_count) as pd_count,sum(bb.ml_money) as ml_money";
		sql += " from (select aa.r3_code,aa.customer_name,aa.category_name,aa.pd_name,aa.pd_total_money,aa.pd_count,(aa.pd_total_money - (aa.pd_count * aa.cash_price)) as ml_money";
		sql += " from (select a.r3_code,a.customer_name,a.category_name,a.pd_name,value(a.pd_total_money, 0) as pd_total_money,value(a.pd_count, 0) as pd_count,value(b.cash_price, 0) as cash_price";
		sql += " from V_A_DETAILS_OF_PURCHASE a left join (select k.cash_price,k.pd_name from KONKA_PD_MODEL_PRICES k where 1 = 1 ";

		if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
			sql += " and k.price_month = ? ";
			array.add(year + month);
		}
		sql += " ) b on a.pd_name = b.pd_name where 1 = 1 ";

		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and a.dept_id = ? ";
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
		if (StringUtils.isNotBlank(ywy_user_name)) {
			sql += " and a.ywy_user_name like '%' ||?|| '%' ";
			array.add(ywy_user_name);
		}
		if (StringUtils.isNotBlank(customer_name)) {
			sql += " and a.customer_name like '%' ||?|| '%' ";
			array.add(customer_name);
		}
		if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
			sql += " and to_char(a.create_date,'yyyy-MM') = ? ";
			array.add(year + "-" + month);
		}
		sql += " ) aa) bb group by bb.r3_code, bb.customer_name, bb.category_name) cc ";

		sql += " order by cc.r3_code desc, cc.customer_name desc, cc.category_name desc ) ff ";

		sql += " left join (select cc.r3_code,cc.customer_name,cc.category_name,cc.pd_total_money,cc.pd_count, ";
		sql += " decode(cc.pd_count,0,0,round((cc.ml_money / cc.pd_count), 4)) as pj_ml_money, ";
		sql += " decode(cc.pd_count,0,0,round((cc.pd_total_money / cc.pd_count), 4)) as pj_unitprice ";
		sql += " from (select bb.r3_code,bb.customer_name,bb.category_name,sum(bb.pd_total_money) as pd_total_money,sum(bb.pd_count) as pd_count,sum(bb.ml_money) as ml_money";
		sql += " from (select aa.r3_code,aa.customer_name,aa.category_name,aa.pd_name,aa.pd_total_money,aa.pd_count,(aa.pd_total_money - (aa.pd_count * aa.cash_price)) as ml_money ";
		sql += " from (select a.r3_code,a.customer_name,a.category_name,a.pd_name,value(a.pd_total_money, 0) as pd_total_money,value(a.pd_count, 0) as pd_count,value(b.cash_price, 0) as cash_price ";
		sql += " from V_A_DETAILS_OF_PURCHASE a left join (select k.cash_price, k.pd_name from KONKA_PD_MODEL_PRICES k where 1 = 1 ";

		if (StringUtils.isNotBlank(last_year) && StringUtils.isNotBlank(month)) {
			sql += " and k.price_month = ? ";
			array.add(last_year + month);
		}
		sql += " ) b on a.pd_name = b.pd_name where 1 = 1 ";

		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and a.dept_id = ? ";
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
		if (StringUtils.isNotBlank(ywy_user_name)) {
			sql += " and a.ywy_user_name like '%' ||?|| '%' ";
			array.add(ywy_user_name);
		}
		if (StringUtils.isNotBlank(customer_name)) {
			sql += " and a.customer_name like '%' ||?|| '%' ";
			array.add(customer_name);
		}
		if (StringUtils.isNotBlank(last_year) && StringUtils.isNotBlank(month)) {
			sql += " and to_char(a.create_date,'yyyy-MM') = ? ";
			array.add(last_year + "-" + month);
		}
		sql += " ) aa) bb group by bb.r3_code, bb.customer_name, bb.category_name) cc) gg on ff.r3_code = gg.r3_code ";

		log.info("**********sql:" + sql);
		if (null != array && array.size() > 0) {
			String array_string = "";
			for (int i = 0; i < array.size(); i++) {
				array_string += array.get(i) + ",";
			}
			log.info("**********array:" + array.size());
			log.info("**********array_string:" + array_string);
		}

		List<?> list = super.getFacade().getBaseReportService().getBaseReportForBindToArray(sql, array.toArray());

		return list;
	}
}