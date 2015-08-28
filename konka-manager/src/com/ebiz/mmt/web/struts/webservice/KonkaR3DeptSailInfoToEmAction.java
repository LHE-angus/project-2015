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
 * @version 2013-12-09
 */
public class KonkaR3DeptSailInfoToEmAction extends BaseAction {
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

		// 机型(*):
		Hashtable<String, String> v_MATNR = new Hashtable<String, String>();
		v_MATNR.put("LOW", "LED*");

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
		zc.setV_MATNR(v_MATNR);
		zc.setV_VTWEG(v_VTWEG);

		// 销售组织
		zc.setV_VKORG("1001");

		// 查询LED数据
		List<Zles29a> list = new ArrayList<Zles29a>();
		list = DAOFactory.getInstance().getZles29a(zc);

		// 机型(*):查询LC数据
		v_MATNR.put("LOW", "LC*");
		zc.setV_MATNR(v_MATNR);
		List<Zles29a> list2 = new ArrayList<Zles29a>();
		list2 = DAOFactory.getInstance().getZles29a(zc);

		BigDecimal p_count = new BigDecimal(0); // P仓
		BigDecimal z_90_count = new BigDecimal(0);// 90仓
		BigDecimal sw_count = new BigDecimal(0);// 商务仓
		BigDecimal f_count = new BigDecimal(0);// F仓
		BigDecimal y_count = new BigDecimal(0);// Y仓
		BigDecimal q_count = new BigDecimal(0);// Q仓
		BigDecimal dzsw_count = new BigDecimal(0);// 电子商务仓
		BigDecimal zt_count = new BigDecimal(0);// 在途
		BigDecimal wf_count = new BigDecimal(0);// 未发
		// BigDecimal rdc_count =new BigDecimal(0);//RDC未发
		BigDecimal zl_count = new BigDecimal(0);// 总量
		BigDecimal sy_count = new BigDecimal(0);// 上月销量
		BigDecimal by_count = new BigDecimal(0);// 本月销量

		BigDecimal zz_count = new BigDecimal(0);// 周转总数
		BigDecimal zzl = new BigDecimal(0);// 周转率

		if (list.size() > 0) {
			for (Zles29a temp : list) {
				// P仓
				if (StringUtils.isNotBlank(temp.getPCLABST()))
					p_count = p_count.add(new BigDecimal(temp.getPCLABST()));

				// 90仓
				if (StringUtils.isNotBlank(temp.getZZLABST()))
					z_90_count = z_90_count.add(new BigDecimal(temp.getZZLABST()));

				// 商务仓
				if (StringUtils.isNotBlank(temp.getBCLABST()))
					sw_count = sw_count.add(new BigDecimal(temp.getBCLABST()));

				// F仓
				if (StringUtils.isNotBlank(temp.getJYLABST()))
					f_count = f_count.add(new BigDecimal(temp.getJYLABST()));

				// Y仓
				if (StringUtils.isNotBlank(temp.getYJLABST()))
					y_count = y_count.add(new BigDecimal(temp.getYJLABST()));

				// Q仓
				if (StringUtils.isNotBlank(temp.getCLLABST()))
					q_count = q_count.add(new BigDecimal(temp.getCLLABST()));

				// 电子商务
				if (StringUtils.isNotBlank(temp.getDZLABST()))
					dzsw_count = dzsw_count.add(new BigDecimal(temp.getDZLABST()));

				// 在途
				if (StringUtils.isNotBlank(temp.getMZT()))
					zt_count = zt_count.add(new BigDecimal(temp.getMZT()));

				// 未发
				if (StringUtils.isNotBlank(temp.getMWF()))
					wf_count = wf_count.add(new BigDecimal(temp.getMWF()));

				// 总量
				if (StringUtils.isNotBlank(temp.getSUM()))
					zl_count = zl_count.add(new BigDecimal(temp.getSUM()));

				// 上月销量
				if (StringUtils.isNotBlank(temp.getLFIMG()))
					sy_count = sy_count.add(new BigDecimal(temp.getLFIMG()));

				// 本月销量
				if (StringUtils.isNotBlank(temp.getLFIMG1()))
					by_count = by_count.add(new BigDecimal(temp.getLFIMG1()));
			}
		}

		if (list2.size() > 0) {
			for (Zles29a temp : list2) {
				// P仓
				if (StringUtils.isNotBlank(temp.getPCLABST()))
					p_count = p_count.add(new BigDecimal(temp.getPCLABST()));

				// 90仓
				if (StringUtils.isNotBlank(temp.getZZLABST()))
					z_90_count = z_90_count.add(new BigDecimal(temp.getZZLABST()));

				// 商务仓
				if (StringUtils.isNotBlank(temp.getBCLABST()))
					sw_count = sw_count.add(new BigDecimal(temp.getBCLABST()));

				// F仓
				if (StringUtils.isNotBlank(temp.getJYLABST()))
					f_count = f_count.add(new BigDecimal(temp.getJYLABST()));

				// Y仓
				if (StringUtils.isNotBlank(temp.getYJLABST()))
					y_count = y_count.add(new BigDecimal(temp.getYJLABST()));

				// Q仓
				if (StringUtils.isNotBlank(temp.getCLLABST()))
					q_count = q_count.add(new BigDecimal(temp.getCLLABST()));

				// 电子商务
				if (StringUtils.isNotBlank(temp.getDZLABST()))
					dzsw_count = dzsw_count.add(new BigDecimal(temp.getDZLABST()));

				// 在途
				if (StringUtils.isNotBlank(temp.getMZT()))
					zt_count = zt_count.add(new BigDecimal(temp.getMZT()));

				// 未发
				if (StringUtils.isNotBlank(temp.getMWF()))
					wf_count = wf_count.add(new BigDecimal(temp.getMWF()));

				// 总量
				if (StringUtils.isNotBlank(temp.getSUM()))
					zl_count = zl_count.add(new BigDecimal(temp.getSUM()));

				// 上月销量
				if (StringUtils.isNotBlank(temp.getLFIMG()))
					sy_count = sy_count.add(new BigDecimal(temp.getLFIMG()));

				// 本月销量
				if (StringUtils.isNotBlank(temp.getLFIMG1()))
					by_count = by_count.add(new BigDecimal(temp.getLFIMG1()));
			}
		}

		zz_count = zz_count.add(p_count).add(z_90_count).add(f_count).add(y_count).add(q_count).add(zt_count);

		if (zz_count.compareTo(new BigDecimal(0)) != 0) {
			zzl = (by_count.multiply(new BigDecimal(100))).divide(zz_count, 2, BigDecimal.ROUND_HALF_EVEN);
		}

		request.setAttribute("p_count", p_count);
		request.setAttribute("z_90_count", z_90_count);
		request.setAttribute("sw_count", sw_count);
		request.setAttribute("f_count", f_count);
		request.setAttribute("y_count", y_count);
		request.setAttribute("q_count", q_count);
		request.setAttribute("dzsw_count", dzsw_count);
		request.setAttribute("zt_count", zt_count);
		request.setAttribute("wf_count", wf_count);
		request.setAttribute("zl_count", zl_count);
		request.setAttribute("sy_count", sy_count);
		request.setAttribute("by_count", by_count);
		request.setAttribute("zzl", zzl);

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
