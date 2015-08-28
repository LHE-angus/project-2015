package com.ebiz.mmt.web.struts.manager.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
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
public class KonkaR3DeptSailInfoAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dnBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);

		// 销售片区:
		String v_bzirk_low = (String) dnBean.get("v_bzirk_low");
		String v_bzirk_high = (String) dnBean.get("v_bzirk_high");

		// 分公司名称:
		String v_class_low = (String) dnBean.get("v_class_low");
		String v_class_high = (String) dnBean.get("v_class_high");

		// 机型(*):
		String v_matnr_low = (String) dnBean.get("v_matnr_low");
		String v_matnr_high = (String) dnBean.get("v_matnr_high");

		// 物料组 :
		String v_matkl_low = (String) dnBean.get("v_matkl_low");
		String v_matkl_high = (String) dnBean.get("v_matkl_high");

		// 转储单号 (*):
		String v_ebeln_low = (String) dnBean.get("v_ebeln_low");
		String v_ebeln_high = (String) dnBean.get("v_ebeln_high");

		// 转储单日期(*):
		String v_eindt_low = (String) dnBean.get("v_eindt_low");
		String v_eindt_high = (String) dnBean.get("v_eindt_high");

		// 销售日期(*)
		String v_bstdk_low = (String) dnBean.get("v_bstdk_low");
		String v_bstdk_high = (String) dnBean.get("v_bstdk_high");

		// 分销渠道 (*):
		String v_vtweg_low = (String) dnBean.get("v_vtweg_low");
		String v_vtweg_high = (String) dnBean.get("v_vtweg_high");

		// 销售组织(*) :
		String v_vkorg_low = (String) dnBean.get("v_vkorg_low");

		if (v_matnr_low == null || "".equals(v_matnr_low)) {
			return mapping.findForward("list");
		}
		if (v_ebeln_low == null || "".equals(v_ebeln_low)) {
			return mapping.findForward("list");
		}
		if (v_eindt_low == null || "".equals(v_eindt_low)) {
			return mapping.findForward("list");
		}
		if (v_bstdk_low == null || "".equals(v_bstdk_low)) {
			return mapping.findForward("list");
		}
		if (v_vtweg_low == null || "".equals(v_vtweg_low)) {
			return mapping.findForward("list");
		}
		if (v_vkorg_low == null || "".equals(v_vkorg_low)) {
			return mapping.findForward("list");
		}

		Hashtable<String, String> v_BSTDK = new Hashtable<String, String>();
		if (v_bstdk_low != null && !"".equals(v_bstdk_low)) {
			v_BSTDK.put("LOW", v_bstdk_low);
		}
		if (v_bstdk_high != null && !"".equals(v_bstdk_high)) {
			v_BSTDK.put("HIGH", v_bstdk_high);
		}

		Hashtable<String, String> v_CLASS = new Hashtable<String, String>();
		if (v_class_low != null && !"".equals(v_class_low)) {
			v_CLASS.put("LOW", v_class_low);
		}
		if (v_class_high != null && !"".equals(v_class_high)) {
			v_CLASS.put("HIGH", v_class_high);
		}

		Hashtable<String, String> v_MATNR = new Hashtable<String, String>();
		if (v_matnr_low != null && !"".equals(v_matnr_low)) {
			v_MATNR.put("LOW", v_matnr_low);
		}
		if (v_matnr_high != null && !"".equals(v_matnr_high)) {
			v_MATNR.put("HIGH", v_matnr_high);
		}

		Hashtable<String, String> v_MATKL = new Hashtable<String, String>();
		if (v_matkl_low != null && !"".equals(v_matkl_low)) {
			v_MATKL.put("LOW", v_matkl_low);
		}
		if (v_matkl_high != null && !"".equals(v_matkl_high)) {
			v_MATKL.put("HIGH", v_matkl_high);
		}

		Hashtable<String, String> v_EBELN = new Hashtable<String, String>();
		if (v_ebeln_low != null && !"".equals(v_ebeln_low)) {
			v_EBELN.put("LOW", v_ebeln_low);
		}
		if (v_ebeln_high != null && !"".equals(v_ebeln_high)) {
			v_EBELN.put("HIGH", v_ebeln_high);
		}

		Hashtable<String, String> v_EINDT = new Hashtable<String, String>();
		if (v_eindt_low != null && !"".equals(v_eindt_low)) {
			v_EINDT.put("LOW", v_eindt_low);
		}
		if (v_eindt_high != null && !"".equals(v_eindt_high)) {
			v_EINDT.put("HIGH", v_eindt_high);
		}

		Hashtable<String, String> v_BZIRK = new Hashtable<String, String>();
		if (v_bzirk_low != null && !"".equals(v_bzirk_low)) {
			v_BZIRK.put("LOW", v_bzirk_low);
		}
		if (v_bzirk_high != null && !"".equals(v_bzirk_high)) {
			v_BZIRK.put("HIGH", v_bzirk_high);
		}

		Hashtable<String, String> v_VTWEG = new Hashtable<String, String>();
		if (v_vtweg_low != null && !"".equals(v_vtweg_low)) {
			v_VTWEG.put("LOW", v_vtweg_low);
		}
		if (v_vtweg_high != null && !"".equals(v_vtweg_high)) {
			v_VTWEG.put("HIGH", v_vtweg_high);
		}

		Zles29aCriteria zc = new Zles29aCriteria();
		zc.setV_BSTDK(v_BSTDK);
		zc.setV_BZIRK(v_BZIRK);
		zc.setV_CLASS(v_CLASS);
		zc.setV_EBELN(v_EBELN);
		zc.setV_EINDT(v_EINDT);
		zc.setV_MATKL(v_MATKL);
		zc.setV_MATNR(v_MATNR);
		zc.setV_VTWEG(v_VTWEG);
		zc.setV_VKORG(v_vkorg_low);

		List<Zles29a> list = new ArrayList<Zles29a>();
		list = DAOFactory.getInstance().getZles29a(zc);

		if (list.size() > 0) {
			for (Zles29a temp : list) {
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
			}
		}

		request.setAttribute("entityList", list);

		return mapping.findForward("list");

	}
}
