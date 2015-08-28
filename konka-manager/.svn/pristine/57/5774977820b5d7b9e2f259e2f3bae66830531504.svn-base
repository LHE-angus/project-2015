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

import com.ebiz.mmt.domain.KonkaR3DeptStockInfo;
import com.ebiz.mmt.domain.KonkaSpList;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.util.EncryptUtils;

/**
 * @author Hu,Hao
 * @version 2013-12-16
 */
public class KonkaR3DeptStockInfoToFgsTopAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		// 分公司名称:
		// String v_class = (String) dynaBean.get("v_class");

		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String excel_all = (String) dynaBean.get("excel_all");

		// 取当前时间
		SimpleDateFormat formaty = new SimpleDateFormat("yyyy");
		SimpleDateFormat formatm = new SimpleDateFormat("MM");
		SimpleDateFormat formatd = new SimpleDateFormat("dd");

		KonkaR3DeptStockInfo entity = new KonkaR3DeptStockInfo();

		Date now = new Date();

		String this_year = null;// 当前年份
		String this_month = null;// 当前月份
		if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
			this_year = year;
			this_month = month;
		} else {
			this_year = formaty.format(now);// 当前年份
			this_month = formatm.format(now);// 当前月份
		}

		//关联报表时间设定，若不存在则使用自然月时间段
		KonkaSpList t = new KonkaSpList();
		t.setYear(Integer.valueOf(this_year));
		t.setMonth(Integer.valueOf(this_month));
		List<KonkaSpList> splist = super.getFacade().getKonkaSpListService().getKonkaSpListList(t);
		
		if(splist.size()>0){
			entity.getMap().put("s_date", formaty.format(splist.get(0).getS_date())+"-"+formatm.format(splist.get(0).getS_date())+"-"+formatd.format(splist.get(0).getS_date()));
			entity.getMap().put("e_date",formaty.format(splist.get(0).getE_date())+"-"+formatm.format(splist.get(0).getE_date())+"-"+formatd.format(splist.get(0).getE_date()));
		}else{
			entity.getMap().put("s_date", this_year + "-" + this_month + "-01");
			entity.getMap().put(
					"e_date",
					this_year + "-" + this_month + "-" + getMaxDay(Integer.valueOf(this_month), Integer.valueOf(this_year)));
		}

		// if (StringUtils.isNotBlank(v_class))
		// entity.setClass1(v_class);
		// 取最大更新日期 start
		List<KonkaR3DeptStockInfo> maxList = super.getFacade().getKonkaR3DeptStockInfoService()
				.getKonkaR3DeptStockInfoListForMaxDate(entity);

		if (maxList.size() > 0)
			request.setAttribute("max_date", maxList.get(0).getAdd_date());
		// end

		List<KonkaR3DeptStockInfo> entityList = super.getFacade().getKonkaR3DeptStockInfoService()
				.getKonkaR3DeptStockInfoListForFgsZzl(entity);

		// 合计
		BigDecimal v_lfimg = new BigDecimal(0);
		BigDecimal v_lfimg1 = new BigDecimal(0);
		BigDecimal v_sum = new BigDecimal(0);
		BigDecimal v_mzt = new BigDecimal(0);
		BigDecimal v_mwf = new BigDecimal(0);
		BigDecimal v_pdlabst = new BigDecimal(0);
		BigDecimal v_zzlabst = new BigDecimal(0);
		BigDecimal v_jylabst = new BigDecimal(0);
		BigDecimal v_cllabst = new BigDecimal(0);
		BigDecimal v_yjlabst = new BigDecimal(0);
		BigDecimal v_tjlabst = new BigDecimal(0);
		BigDecimal v_pclabst = new BigDecimal(0);
		BigDecimal v_zclabst = new BigDecimal(0);
		BigDecimal v_bclabst = new BigDecimal(0);
		BigDecimal v_dzlabst = new BigDecimal(0);
		BigDecimal v_zzl = new BigDecimal(0);

		BigDecimal sum_count = new BigDecimal(0);
		// 计算合计
		if (entityList.size() > 0) {
			for (KonkaR3DeptStockInfo temp : entityList) {
				if (null != temp.getLfimg())
					v_lfimg = v_lfimg.add(temp.getLfimg());
				if (null != temp.getLfimg1())
					v_lfimg1 = v_lfimg1.add(temp.getLfimg1());
				if (null != temp.getSum_())
					v_sum = v_sum.add(temp.getSum_());
				if (null != temp.getMzt())
					v_mzt = v_mzt.add(temp.getMzt());
				if (null != temp.getMwf())
					v_mwf = v_mwf.add(temp.getMwf());
				if (null != temp.getPdlabst())
					v_pdlabst = v_pdlabst.add(temp.getPdlabst());
				if (null != temp.getZzlabst())
					v_zzlabst = v_zzlabst.add(temp.getZzlabst());
				if (null != temp.getJylabst())
					v_jylabst = v_jylabst.add(temp.getJylabst());
				if (null != temp.getCllabst())
					v_cllabst = v_cllabst.add(temp.getCllabst());
				if (null != temp.getYjlabst())
					v_yjlabst = v_yjlabst.add(temp.getYjlabst());
				if (null != temp.getTjlabst())
					v_tjlabst = v_tjlabst.add(temp.getTjlabst());

				if (null != temp.getPclabst())
					v_pclabst = v_pclabst.add(temp.getPclabst());
				if (null != temp.getZclabst())
					v_zclabst = v_zclabst.add(temp.getZclabst());
				if (null != temp.getBclabst())
					v_bclabst = v_bclabst.add(temp.getBclabst());
				if (null != temp.getDzlabst())
					v_dzlabst = v_dzlabst.add(temp.getDzlabst());

				if (null != temp.getMap().get("sum_count"))
					sum_count = sum_count.add(new BigDecimal(temp.getMap().get("sum_count").toString()));
			}
		}

		if (sum_count.compareTo(new BigDecimal(0)) != 0) {
			v_zzl = v_lfimg1.multiply(new BigDecimal(100)).divide(sum_count, 2, BigDecimal.ROUND_HALF_UP);
		}

		request.setAttribute("v_lfimg", v_lfimg);
		request.setAttribute("v_lfimg1", v_lfimg1);
		request.setAttribute("v_sum", v_sum);
		request.setAttribute("v_mzt", v_mzt);
		request.setAttribute("v_mwf", v_mwf);
		request.setAttribute("v_pdlabst", v_pdlabst);
		request.setAttribute("v_zzlabst", v_zzlabst);
		request.setAttribute("v_jylabst", v_jylabst);
		request.setAttribute("v_cllabst", v_cllabst);
		request.setAttribute("v_yjlabst", v_yjlabst);
		request.setAttribute("v_tjlabst", v_tjlabst);
		request.setAttribute("v_pclabst", v_pclabst);
		request.setAttribute("v_zclabst", v_zclabst);
		request.setAttribute("v_bclabst", v_bclabst);
		request.setAttribute("v_dzlabst", v_dzlabst);
		request.setAttribute("v_zzl", v_zzl);

		request.setAttribute("entityList", entityList);

		dynaBean.set("month", this_month);
		dynaBean.set("year", this_year);

		// 导出
		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			String str = this_year + "年" + this_month + "月分公司销售评估表";
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName(str)
					+ ".xls");

			List<KonkaR3DeptStockInfo> entityList1 = super.getFacade().getKonkaR3DeptStockInfoService()
					.getKonkaR3DeptStockInfoListForZzl(entity);

			// 合计
			BigDecimal v_lfimg_1 = new BigDecimal(0);
			BigDecimal v_lfimg1_1 = new BigDecimal(0);
			BigDecimal v_sum_1 = new BigDecimal(0);
			BigDecimal v_mzt_1 = new BigDecimal(0);
			BigDecimal v_mwf_1 = new BigDecimal(0);
			BigDecimal v_pdlabst_1 = new BigDecimal(0);
			BigDecimal v_zzlabst_1 = new BigDecimal(0);
			BigDecimal v_jylabst_1 = new BigDecimal(0);
			BigDecimal v_cllabst_1 = new BigDecimal(0);
			BigDecimal v_yjlabst_1 = new BigDecimal(0);
			BigDecimal v_tjlabst_1 = new BigDecimal(0);
			BigDecimal v_pclabst_1 = new BigDecimal(0);
			BigDecimal v_zclabst_1 = new BigDecimal(0);
			BigDecimal v_bclabst_1 = new BigDecimal(0);
			BigDecimal v_dzlabst_1 = new BigDecimal(0);
			BigDecimal v_zzl_1 = new BigDecimal(0);

			BigDecimal sum_count_1 = new BigDecimal(0);
			// 计算合计
			if (entityList.size() > 0) {
				for (KonkaR3DeptStockInfo temp : entityList) {
					if (null != temp.getLfimg())
						v_lfimg_1 = v_lfimg_1.add(temp.getLfimg());
					if (null != temp.getLfimg1())
						v_lfimg1_1 = v_lfimg1_1.add(temp.getLfimg1());
					if (null != temp.getSum_())
						v_sum_1 = v_sum_1.add(temp.getSum_());
					if (null != temp.getMzt())
						v_mzt_1 = v_mzt_1.add(temp.getMzt());
					if (null != temp.getMwf())
						v_mwf_1 = v_mwf_1.add(temp.getMwf());
					if (null != temp.getPdlabst())
						v_pdlabst_1 = v_pdlabst_1.add(temp.getPdlabst());
					if (null != temp.getZzlabst())
						v_zzlabst_1 = v_zzlabst_1.add(temp.getZzlabst());
					if (null != temp.getJylabst())
						v_jylabst_1 = v_jylabst_1.add(temp.getJylabst());
					if (null != temp.getCllabst())
						v_cllabst_1 = v_cllabst_1.add(temp.getCllabst());
					if (null != temp.getYjlabst())
						v_yjlabst_1 = v_yjlabst_1.add(temp.getYjlabst());
					if (null != temp.getTjlabst())
						v_tjlabst_1 = v_tjlabst_1.add(temp.getTjlabst());

					if (null != temp.getPclabst())
						v_pclabst_1 = v_pclabst_1.add(temp.getPclabst());
					if (null != temp.getZclabst())
						v_zclabst_1 = v_zclabst_1.add(temp.getZclabst());
					if (null != temp.getBclabst())
						v_bclabst_1 = v_bclabst_1.add(temp.getBclabst());
					if (null != temp.getDzlabst())
						v_dzlabst_1 = v_dzlabst_1.add(temp.getDzlabst());

					if (null != temp.getMap().get("sum_count"))
						sum_count_1 = sum_count_1.add(new BigDecimal(temp.getMap().get("sum_count").toString()));
				}
			}

			if (sum_count.compareTo(new BigDecimal(0)) != 0) {
				v_zzl_1 = v_lfimg1_1.multiply(new BigDecimal(100)).divide(sum_count_1, 2, BigDecimal.ROUND_HALF_UP);
			}

			request.setAttribute("v_lfimg_1", v_lfimg_1);
			request.setAttribute("v_lfimg1_1", v_lfimg1);
			request.setAttribute("v_sum_1", v_sum_1);
			request.setAttribute("v_mzt_1", v_mzt_1);
			request.setAttribute("v_mwf_1", v_mwf_1);
			request.setAttribute("v_pdlabst_1", v_pdlabst_1);
			request.setAttribute("v_zzlabst_1", v_zzlabst_1);
			request.setAttribute("v_jylabst_1", v_jylabst_1);
			request.setAttribute("v_cllabst_1", v_cllabst_1);
			request.setAttribute("v_yjlabst_1", v_yjlabst_1);
			request.setAttribute("v_tjlabst_1", v_tjlabst_1);
			request.setAttribute("v_pclabst_1", v_pclabst_1);
			request.setAttribute("v_zclabst_1", v_zclabst_1);
			request.setAttribute("v_bclabst_1", v_bclabst_1);
			request.setAttribute("v_dzlabst_1", v_dzlabst_1);
			request.setAttribute("v_zzl_1", v_zzl_1);

			request.setAttribute("entityList1", entityList1);

			return mapping.findForward("view");
		}

		// 遍历年份，取前10年
		ArrayList<String> yearList = new ArrayList<String>();

		for (int y = 0; y < 10; y++) {
			yearList.add((Integer.valueOf(formaty.format(now)) - y) + "");
		}

		request.setAttribute("yearList", yearList);

		return mapping.findForward("list");
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		super.encodeCharacterForGetMethod(dynaBean, request);
		// 分公司名称:
		String v_class = (String) dynaBean.get("v_class");

		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String matnr_like = (String) dynaBean.get("matnr_like");

		//if (StringUtils.isNotBlank(v_class)) {
		//	v_class = new String(v_class.getBytes("iso-8859-1"), "UTF-8");
			request.setAttribute("v_class", v_class);
		//}

		// 取当前时间
		SimpleDateFormat formaty = new SimpleDateFormat("yyyy");
		SimpleDateFormat formatm = new SimpleDateFormat("MM");

		KonkaR3DeptStockInfo entity = new KonkaR3DeptStockInfo();

		Date now = new Date();

		String this_year = null;// 当前年份
		String this_month = null;// 当前月份
		if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
			this_year = year;
			this_month = month;
		} else {
			this_year = formaty.format(now);// 当前年份
			this_month = formatm.format(now);// 当前月份
		}

		if (StringUtils.isNotBlank(matnr_like))
			entity.getMap().put("matnr_like", matnr_like);

		entity.getMap().put("s_date", this_year + "-" + this_month + "-01 00:00:00");
		entity.getMap().put(
				"e_date",
				this_year + "-" + this_month + "-" + getMaxDay(Integer.valueOf(this_month), Integer.valueOf(this_year))
						+ " 23:59:59");
		
		// 取最大更新日期 start
		List<KonkaR3DeptStockInfo> maxList = super.getFacade().getKonkaR3DeptStockInfoService()
				.getKonkaR3DeptStockInfoListForMaxDate(entity);

		if (maxList.size() > 0)
			request.setAttribute("max_date", maxList.get(0).getAdd_date());
		// end

		if (StringUtils.isNotBlank(v_class))
			entity.setClass1(v_class);

		List<KonkaR3DeptStockInfo> entityList = super.getFacade().getKonkaR3DeptStockInfoService()
				.getKonkaR3DeptStockInfoListForZzl(entity);

		request.setAttribute("entityList", entityList);

		dynaBean.set("month", this_month);
		dynaBean.set("year", this_year);

		// 遍历年份，取前10年
		ArrayList<String> yearList = new ArrayList<String>();

		for (int y = 0; y < 10; y++) {
			yearList.add((Integer.valueOf(formaty.format(now)) - y) + "");
		}

		request.setAttribute("yearList", yearList);

		KonkaR3DeptStockInfo kk = new KonkaR3DeptStockInfo();
		List<KonkaR3DeptStockInfo> kdeptList = super.getFacade().getKonkaR3DeptStockInfoService()
				.getKonkaR3DeptStockInfoListForClass(kk);

		request.setAttribute("kdeptList", kdeptList);

		return new ActionForward("/admin/KonkaR3DeptStockInfoToFgsTop/detail.jsp");
	}

	public int getMaxDay(int mm, int year) {
		int day = 0;
		if (mm == 1 || mm == 3 || mm == 5 || mm == 7 || mm == 8 || mm == 10 || mm == 12) {
			day = 31;
		} else if (mm == 2) {
			if (year % 4 == 0) {
				day = 29;
			} else {
				day = 28;
			}
		} else {
			day = 30;
		}
		return day;

	}
}
