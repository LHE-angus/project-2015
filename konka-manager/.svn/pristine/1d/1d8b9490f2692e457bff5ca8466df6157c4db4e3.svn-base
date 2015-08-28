package com.ebiz.mmt.web.struts.webservice;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.r3.Zles29a;
import com.ebiz.mmt.r3.Zles29aCriteria;
import com.ebiz.mmt.r3.helper.DAOFactory;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Hu,Hao
 * @version 2013-12-11
 */
public class KonkaR3DeptSailCountInfoToEmAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 取当前时间
		SimpleDateFormat formaty = new SimpleDateFormat("yyyy");
		SimpleDateFormat formatm = new SimpleDateFormat("MM");
		SimpleDateFormat formatd = new SimpleDateFormat("dd");
		Date now = new Date();

		// 当前日期
		String now_date = formaty.format(now).toString() + formatm.format(now).toString()
				+ formatd.format(now).toString();

		// 当月第一天
		String now_y_f = formaty.format(now).toString() + formatm.format(now).toString() + "01";

		// 前三个月
		String t_ = getMonth(formaty.format(now).toString(), formatm.format(now).toString()) + "01";

		// 销售日期(*)
		Hashtable<String, String> v_BSTDK = new Hashtable<String, String>();
		v_BSTDK.put("LOW", now_y_f);
		v_BSTDK.put("HIGH", now_date);

		// 转储单号 (*):
		Hashtable<String, String> v_EBELN = new Hashtable<String, String>();
		v_EBELN.put("LOW", "UA00-00000");
		v_EBELN.put("HIGH", "UZ99-99999");

		// 转储单日期(*):
		Hashtable<String, String> v_EINDT = new Hashtable<String, String>();
		v_EINDT.put("LOW", t_);
		v_EINDT.put("HIGH", now_date);

		// 分销渠道 (*):
		Hashtable<String, String> v_VTWEG = new Hashtable<String, String>();
		v_VTWEG.put("LOW", "10");

		Zles29aCriteria zc = new Zles29aCriteria();
		zc.setV_BSTDK(v_BSTDK);
		zc.setV_EBELN(v_EBELN);
		zc.setV_EINDT(v_EINDT);
		zc.setV_VTWEG(v_VTWEG);

		// 销售组织
		zc.setV_VKORG("1001");

		// 机型数组
		String[] matnrArrs = { "LED*", "LC*", "PDP*" };

		List<Zles29a> list = new ArrayList<Zles29a>();

		for (int i = 0; i < matnrArrs.length; i++) {
			// 机型(*):
			Hashtable<String, String> v_MATNR = new Hashtable<String, String>();
			v_MATNR.put("LOW", matnrArrs[i]);
			zc.setV_MATNR(v_MATNR);

			// 查询LED数据
			List<Zles29a> list1 = DAOFactory.getInstance().getZles29a(zc);

			if (list1.size() > 0) {
				for (Zles29a temp : list1) {
					BigDecimal counts = new BigDecimal(0);

					// 总量
					if (StringUtils.isNotBlank(temp.getSUM()))
						counts = counts.add(new BigDecimal(temp.getSUM()));

					// 未发
					if (StringUtils.isNotBlank(temp.getMWF()))
						counts = counts.multiply(new BigDecimal(temp.getMWF()));

					// 周转P仓
					if (StringUtils.isNotBlank(temp.getPCLABST()))
						counts = counts.multiply(new BigDecimal(temp.getPCLABST()));

					// 退机T仓
					if (StringUtils.isNotBlank(temp.getTJLABST()))
						counts = counts.multiply(new BigDecimal(temp.getTJLABST()));

					// 周转率
					if (counts.compareTo(new BigDecimal(0)) == 0 || StringUtils.isBlank(temp.getLFIMG1())) {
						temp.getMap().put("zzl", "0.00");
					} else {
						temp.getMap().put(
								"zzl",
								(new BigDecimal(temp.getLFIMG1()).multiply(new BigDecimal(100))).divide(counts, 2,
										BigDecimal.ROUND_HALF_EVEN));

					}
					list.add(temp);
				}
			}
		}

		request.setAttribute("list", list);

		return mapping.findForward("list");

	}

	public String getMonth(String yyyy, String mm) {
		String yyyymm = null;

		int m = 0;
		int mm_i = Integer.valueOf(mm);
		int yyyy_i = Integer.valueOf(yyyy);
		if (mm_i > 3) {
			m = mm_i - 3;
		} else {
			m = mm_i + 12 - 3;
			yyyy_i = yyyy_i - 1;
		}

		if (m < 10) {
			yyyymm = yyyy_i + "0" + m;
		} else {
			yyyymm = yyyy_i + "" + m;
		}

		return yyyymm;

	}
}
