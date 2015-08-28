package com.ebiz.mmt.web.struts.manager.admin;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.ChannelDataImport;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.support.Table;
import com.ebiz.mmt.domain.support.TableUtils;
import com.ebiz.mmt.domain.support.Tbody;
import com.ebiz.mmt.domain.support.Td;
import com.ebiz.mmt.domain.support.Thead;
import com.ebiz.mmt.domain.support.Tr;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Hu,Hao
 * @version 2013-09-23
 * @desc 任务完成分析表
 */
public class KonkaRwWcFxAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");
		String year = (String) dynaBean.get("year");

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_10 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() == 10L) {
				role_id_eq_10 = true;
			}
		}
		
		if (!role_id_eq_10) {
			KonkaDept d = super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
			if (null != d)
			dept_id = d.getDept_id().toString();
			request.setAttribute("fgs_dept", d);
		}

		// 取当前时间
		SimpleDateFormat formaty = new SimpleDateFormat("yyyy");
		Date now = new Date();

		String this_year = null;// 当前年份
		if (StringUtils.isNotBlank(year)) {
			this_year = year;
		} else {
			this_year = formaty.format(now);// 当前年份
		}
		// 去年
		Integer last_year = Integer.valueOf(this_year) - 1;

		// 今年数据时间段
		String this_date_s = this_year + "-01-01 00:00:00";
		String this_date_e = this_year + "-12-30 23:59:59";

		// 去年数据时间段
		String last_date_s = last_year + "-01-01 00:00:00";
		String last_date_e = last_year + "-12-30 23:59:59";

		ChannelDataImport entity = new ChannelDataImport();
		entity.getMap().put("this_date_s", this_date_s);
		entity.getMap().put("this_date_e", this_date_e);

		entity.getMap().put("last_date_s", last_date_s);
		entity.getMap().put("last_date_e", last_date_e);
		entity.getMap().put("this_year", this_year);

		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("dept_id", dept_id);
		}

		// 按月份
		List<ChannelDataImport> entityList1 = super.getFacade().getChannelDataImportService()
				.getChannelDataImportListToMonth(entity);

		// 按季度
		List<ChannelDataImport> entityList2 = super.getFacade().getChannelDataImportService()
				.getChannelDataImportListToSeason(entity);

		// 所有
		List<ChannelDataImport> entityList3 = super.getFacade().getChannelDataImportService()
				.getChannelDataImportListToAll(entity);

		// 计算月度、季度累计达成率、上年月全占全年比、当年月占全年比
		BigDecimal yd_lj_money = new BigDecimal(0);// 月度累计
		BigDecimal yd_lj_money_m = new BigDecimal(0);// 月度累计
		BigDecimal jd_lj_money = new BigDecimal(0);// 季度累计
		BigDecimal jd_lj_money_j = new BigDecimal(0);// 季度累计
		BigDecimal last_js_money = new BigDecimal(0);
		BigDecimal this_js_money = new BigDecimal(0);
		if (null != entityList3.get(0).getMap().get("total_money")) {
			this_js_money = new BigDecimal(entityList3.get(0).getMap().get("total_money").toString());
		}
		if (null != entityList3.get(0).getMap().get("l_total_money")) {
			last_js_money = new BigDecimal(entityList3.get(0).getMap().get("l_total_money").toString());
		}

		// 计算月度累计达成率、上年月全占全年比、当年月占全年比
		for (ChannelDataImport temp1 : entityList1) {
			if (null != temp1.getMap().get("cur_money_of_month_task")) {
				yd_lj_money = yd_lj_money.add(new BigDecimal(temp1.getMap().get("cur_money_of_month_task").toString()));
			}
			if (null != temp1.getMap().get("total_money")) {
				yd_lj_money_m = yd_lj_money_m.add(new BigDecimal(temp1.getMap().get("total_money").toString()));
			}

			if (yd_lj_money.compareTo(new BigDecimal(0)) != 0) {// 累计达成率
				temp1.getMap().put("lj_dcl",
						yd_lj_money_m.divide(yd_lj_money, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)));
			}

			if (this_js_money.compareTo(new BigDecimal(0)) != 0 && null != temp1.getMap().get("total_money")) {// 当年月占全年比
				temp1.getMap().put(
						"this_month_zb",
						(new BigDecimal(temp1.getMap().get("total_money").toString())).divide(this_js_money, 4,
								BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)));
			}

			if (null != temp1.getMap().get("l_total_money")) {
				if (last_js_money.compareTo(new BigDecimal(0)) != 0) {// 上年月占全年比
					temp1.getMap().put(
							"last_month_zb",
							(new BigDecimal(temp1.getMap().get("l_total_money").toString())).divide(last_js_money, 4,
									BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)));
				}
			}
		}

		// 计算季度累计达成率、上年月全占全年比、当年月占全年比
		for (ChannelDataImport temp2 : entityList2) {
			if (null != temp2.getMap().get("cur_money_of_month_task")) {
				jd_lj_money = jd_lj_money.add(new BigDecimal(temp2.getMap().get("cur_money_of_month_task").toString()));
			}
			if (null != temp2.getMap().get("total_money")) {
				jd_lj_money_j = jd_lj_money_j.add(new BigDecimal(temp2.getMap().get("total_money").toString()));
			}

			if (jd_lj_money.compareTo(new BigDecimal(0)) != 0) {// 累计达成率
				temp2.getMap().put("lj_dcl",
						jd_lj_money_j.divide(jd_lj_money, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)));
			}

			if (this_js_money.compareTo(new BigDecimal(0)) != 0 && null != temp2.getMap().get("total_money")) {// 当年月占全年比
				temp2.getMap().put(
						"this_month_zb",
						(new BigDecimal(temp2.getMap().get("total_money").toString())).divide(this_js_money, 4,
								BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)));
			}

			if (null != temp2.getMap().get("l_total_money")) {
				if (last_js_money.compareTo(new BigDecimal(0)) != 0) {// 上年月占全年比
					temp2.getMap().put(
							"last_month_zb",
							(new BigDecimal(temp2.getMap().get("l_total_money").toString())).divide(last_js_money, 4,
									BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)));
				}
			}
		}

		dynaBean.set("year", this_year);
		// 遍历年份，取前10年
		ArrayList<String> yearList = new ArrayList<String>();

		for (int y = 0; y < 10; y++) {
			yearList.add((Integer.valueOf(formaty.format(now)) - y) + "");
		}

		KonkaDept kDept = new KonkaDept();
		kDept.setDept_type(3);
		kDept.getMap().put("isNotEpp", "isNotEpp");
		List<KonkaDept> deptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(kDept);
		request.setAttribute("deptList", deptList);

		request.setAttribute("yearList", yearList);
		request.setAttribute("entityList1", entityList1);
		request.setAttribute("entityList2", entityList2);
		request.setAttribute("entityList3", entityList3);

		return mapping.findForward("list");
	}

	public ActionForward getJson(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");
		String year = (String) dynaBean.get("year");

		// 取当前时间
		SimpleDateFormat formaty = new SimpleDateFormat("yyyy");
		Date now = new Date();

		String this_year = null;// 当前年份
		if (StringUtils.isNotBlank(year)) {
			this_year = year;
		} else {
			this_year = formaty.format(now);// 当前年份
		}
		// 去年
		Integer last_year = Integer.valueOf(this_year) - 1;

		// 今年数据时间段
		String this_date_s = this_year + "-01-01 00:00:00";
		String this_date_e = this_year + "-12-30 23:59:59";

		// 去年数据时间段
		String last_date_s = last_year + "-01-01 00:00:00";
		String last_date_e = last_year + "-12-30 23:59:59";

		ChannelDataImport entity = new ChannelDataImport();
		entity.getMap().put("this_date_s", this_date_s);
		entity.getMap().put("this_date_e", this_date_e);

		entity.getMap().put("last_date_s", last_date_s);
		entity.getMap().put("last_date_e", last_date_e);
		entity.getMap().put("this_year", this_year);

		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("dept_id", dept_id);
		}

		// 按月份
		List<ChannelDataImport> entityList1 = super.getFacade().getChannelDataImportService()
				.getChannelDataImportListToMonth(entity);

		// 所有
		List<ChannelDataImport> entityList3 = super.getFacade().getChannelDataImportService()
				.getChannelDataImportListToAll(entity);

		// 计算月度、季度累计达成率、上年月全占全年比、当年月占全年比
		BigDecimal yd_lj_money = new BigDecimal(0);// 月度累计
		BigDecimal yd_lj_money_m = new BigDecimal(0);// 月度累计
		BigDecimal last_js_money = new BigDecimal(0);
		BigDecimal this_js_money = new BigDecimal(0);
		if (null != entityList3.get(0).getMap().get("total_money")) {
			this_js_money = new BigDecimal(entityList3.get(0).getMap().get("total_money").toString());
		}
		if (null != entityList3.get(0).getMap().get("l_total_money")) {
			last_js_money = new BigDecimal(entityList3.get(0).getMap().get("l_total_money").toString());
		}

		// 计算月度累计达成率、上年月全占全年比、当年月占全年比
		for (ChannelDataImport temp1 : entityList1) {
			if (null != temp1.getMap().get("cur_money_of_month_task")) {
				yd_lj_money = yd_lj_money.add(new BigDecimal(temp1.getMap().get("cur_money_of_month_task").toString()));
			}
			if (null != temp1.getMap().get("total_money")) {
				yd_lj_money_m = yd_lj_money_m.add(new BigDecimal(temp1.getMap().get("total_money").toString()));
			}

			if (yd_lj_money.compareTo(new BigDecimal(0)) != 0) {// 累计达成率
				temp1.getMap().put("lj_dcl",
						yd_lj_money_m.divide(yd_lj_money, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)));
			}

			if (this_js_money.compareTo(new BigDecimal(0)) != 0 && null != temp1.getMap().get("total_money")) {// 当年月占全年比
				temp1.getMap().put(
						"this_month_zb",
						(new BigDecimal(temp1.getMap().get("total_money").toString())).divide(this_js_money, 4,
								BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)));
			}

			if (null != temp1.getMap().get("l_total_money")) {
				if (last_js_money.compareTo(new BigDecimal(0)) != 0) {// 上年月占全年比
					temp1.getMap().put(
							"last_month_zb",
							(new BigDecimal(temp1.getMap().get("l_total_money").toString())).divide(last_js_money, 4,
									BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)));
				}
			}
		}

		Tr tr1 = new Tr();
		List<Td> tdList1 = new ArrayList<Td>();

		Td th1 = new Td();
		th1.setText("月份");
		th1.setTdDataType(null);
		tdList1.add(th1);

		Td th2 = new Td();
		th2.setText("当年结算任务");
		th2.setTdDataType(null);
		tdList1.add(th2);

		Td th3 = new Td();
		th3.setText("当年结算额");
		th3.setTdDataType(null);
		tdList1.add(th3);

		Td th4 = new Td();
		th4.setText("月度达成率");
		th4.setTdDataType(null);
		tdList1.add(th4);

		Td th5 = new Td();
		th5.setText("累计达成率");
		th5.setTdDataType("");
		tdList1.add(th5);
		
		tr1.setTds(tdList1);
		tr1.setTrDataType(null);

		List<Tr> trList1 = new ArrayList<Tr>();
		Thead thead = new Thead();
		trList1.add(tr1);
		thead.setTrs(trList1);

		List<Tr> trList = new ArrayList<Tr>();
		for (ChannelDataImport temp : entityList1) {
			Tr tr = new Tr();
			List<Td> tdList = new ArrayList<Td>();

			Td td1 = new Td();
			td1.setText(temp.getMap().get("m").toString() + "月");
			td1.setTdDataType("1");
			tdList.add(td1);

			Td td2 = new Td();
			if (null != temp.getMap().get("cur_money_of_month_task")) {
				td2.setText(temp.getMap().get("cur_money_of_month_task").toString());
			} else {
				td2.setText("0");
			}
			td2.setUnit("万元");
			tdList.add(td2);

			Td td3 = new Td();
			if (null != temp.getMap().get("total_money")) {
				td3.setText(temp.getMap().get("total_money").toString());
			} else {
				td3.setText("0");
			}
			td3.setUnit("万元");
			tdList.add(td3);

			Td td4 = new Td();
			if (null != temp.getMap().get("this_month_zb")) {
				td4.setText(temp.getMap().get("this_month_zb").toString());
			} else {
				td4.setText("0");
			}
			td4.setUnit("%");
			tdList.add(td4);

			Td td5 = new Td();
			if (null != temp.getMap().get("last_month_zb")) {
				td5.setText(temp.getMap().get("last_month_zb").toString());
			} else {
				td5.setText("0");
			}
			td5.setUnit("%");
			tdList.add(td5);

			tr.setTds(tdList);
			trList.add(tr);
		}
		Tbody tbody = new Tbody();
		tbody.setTrs(trList);

		Table t = new Table();
		t.setTitle("任务达成分析图");
		t.setSubTitle("任务达成分析图");
		t.setThead(thead);
		t.setTbody(tbody);

		super.renderJson(response, TableUtils.toJson(t));
		return null;
	}
}
