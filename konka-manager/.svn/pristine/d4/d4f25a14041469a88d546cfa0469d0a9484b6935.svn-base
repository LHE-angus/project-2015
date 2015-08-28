package com.ebiz.mmt.web.struts.manager.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.ebiz.mmt.r3.SOXX;
import com.ebiz.mmt.r3.helper.DAOFactory;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Hu,Hao
 * @version 2013-12-16
 * @desc R3订单明细查询
 */
public class KonkaR3OrderInfoSearchAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dnBean = (DynaBean) form;

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		// 销售组织
		String v_vkorg = (String) dnBean.get("v_vkorg");
		// 分销渠道
		String v_vtweg = (String) dnBean.get("v_vtweg");
		// 产品组
		String v_spart = (String) dnBean.get("v_spart");
		// 开始日期
		String v_audat_begin = (String) dnBean.get("v_audat_begin");
		// 结束日期
		String v_audat_end = (String) dnBean.get("v_audat_end");
		// 客户编码
		String v_kunnr = (String) dnBean.get("v_kunnr");

		List<SOXX> list = new ArrayList<SOXX>();

		KonkaDept kd = getKonkaDeptForFgs(ui.getDept_id());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// 如果结束日期为空,自动默认为今天
		if (v_audat_end == null || "".equals(v_audat_end)) {
			Calendar calendar = Calendar.getInstance();
			v_audat_end = sdf.format(calendar.getTime());
		}
		if (v_audat_end != null && v_audat_end.length() >= 0) {
			v_audat_end = StringUtils.replace(v_audat_end, "-", "");
		}

		if (v_audat_begin != null && v_audat_begin.length() >= 0) {
			v_audat_begin = StringUtils.replace(v_audat_begin, "-", "");
		}

		// 权限判断
		if (null == kd) {
			if (StringUtils.isEmpty(v_vkorg)) {
				return mapping.findForward("list");
			}
		} else {
			v_vkorg = kd.getDept_sn();
			request.setAttribute("is_fgs", "1");
		}

		if (StringUtils.isEmpty(v_vtweg)) {
			return mapping.findForward("list");
		}
		if (StringUtils.isEmpty(v_spart)) {
			return mapping.findForward("list");
		}
		list = DAOFactory.getInstance().getSoxxMx(v_vkorg, v_vtweg, v_spart, v_audat_begin, v_audat_end, v_kunnr);

		request.setAttribute("entityList", list);

		return mapping.findForward("list");
	}

}
