package com.ebiz.mmt.web.struts.manager.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaZles23ResultInfo;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.org.apache.commons.lang3.StringUtils;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaZles23ResultInfoAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		/*String begindate = "";
		String enddate = "";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendarbegin = Calendar.getInstance();
		calendarbegin.set(Calendar.MONTH, -2);
		calendarbegin.set(Calendar.DAY_OF_MONTH, calendarbegin.getActualMinimum(Calendar.DAY_OF_MONTH));
		begindate = df.format(calendarbegin.getTime());
		dynaBean.set("bedat_s", begindate);
		Calendar calendarend = Calendar.getInstance();
		enddate = df.format(calendarend.getTime());
		dynaBean.set("bedat_e", enddate);*/
		Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        // calendar.add(Calendar.DATE, -30);
        calendar.add(Calendar.MONTH, -1);
        String day_first = df.format(calendar.getTime());
        String day_last = df.format(today);
        dynaBean.set("bedat_s", day_first);
        dynaBean.set("bedat_e", day_last);
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		// String dept_id = (String) dynaBean.get("dept_id");
        String customer_code_like = (String) dynaBean.get("customer_code_like");
		String lgort_like = (String) dynaBean.get("lgort_like");
		String matnr_like = (String) dynaBean.get("matnr_like");
		String ebeln = (String) dynaBean.get("ebeln");
		String vbeln = (String) dynaBean.get("vbeln");
		String dept_id = (String) dynaBean.get("dept_id");
		String bedat_s = (String) dynaBean.get("bedat_s");
		String bedat_e = (String) dynaBean.get("bedat_e");
		// 导出
		String excel_all = (String) dynaBean.get("excel_all");

		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		KonkaZles23ResultInfo entity = new KonkaZles23ResultInfo();
		entity.setEbeln(ebeln);
		entity.setVbeln(vbeln);
        if (StringUtils.isNotBlank(customer_code_like)) {
            entity.getMap().put("r3_code_like", customer_code_like);
		}
		if (StringUtils.isNotBlank(lgort_like)) {
			entity.getMap().put("lgort_like", lgort_like);
		}
		if (StringUtils.isNotBlank(matnr_like)) {
			entity.getMap().put("matnr_like", matnr_like);
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("dept_id", dept_id);
		}
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtils.isBlank(bedat_s) && StringUtils.isBlank(bedat_e)) {
			bedat_e = dateformat.format(calendar.getTime());
			calendar.add(Calendar.MONTH, -3);
			bedat_s = dateformat.format(calendar.getTime());

			dynaBean.set("bedat_s", bedat_s);
			dynaBean.set("bedat_e", bedat_e);
		}
		if (StringUtils.isNotBlank(bedat_s)) {
			entity.getMap().put("bedat_s", bedat_s + " 00:00:00");
			dynaBean.set("bedat_s", bedat_s);
		}
		if (StringUtils.isNotBlank(bedat_e)) {
			entity.getMap().put("bedat_e", bedat_e + " 23:59:59");
			dynaBean.set("bedat_e", bedat_e);
		}
		/*
		 * KonkaDept kd = super.getKonkaDeptForFgs(ui.getDept_id());
		 * 
		 * if (kd != null) { request.setAttribute("is_fgs", "is_fgs");
		 * entity.setDept_id(kd.getDept_id()); } else { if
		 * (StringUtils.isNotBlank(dept_id))
		 * entity.setDept_id(Long.valueOf(dept_id)); }
		 */

		// 数据级别判断开始
		Long __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				entity.getMap().put("dept_id_start", __dept_id);
				request.setAttribute("current_fgs_code", __dept_id);
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			// __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			// entity.getMap().put("dept_id_start", __dept_id);
			// break;
			// 分公司及以下部门可见
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				entity.getMap().put("dept_id_start", __dept_id);
				request.setAttribute("current_fgs_code", __dept_id);
			}
			break;
		case 0:
			// __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			// entity.getMap().put("filter_by_ywy_job_id_eq", ui.getJob_id());
			// break;
			// 分公司及以下部门可见
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				entity.getMap().put("dept_id_start", __dept_id);
				request.setAttribute("current_fgs_code", __dept_id);
			}
			break;
		default:
			// 出错
		}
		// 数据级别判断结束
		entity.getMap().put("session_user_id", ui.getId());

		Long recordCount = getFacade().getKonkaZles23ResultInfoService().getKonkaZles23ResultInfoCount(entity);

        // excel导出
		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			if (recordCount.intValue() > 20000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
						+ "！');history.back();");
				return null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("集采数据")
					+ ".xls");
			entity.getRow().setFirst(0);
			entity.getRow().setCount(recordCount.intValue());
			List<KonkaZles23ResultInfo> entityList1 = getFacade().getKonkaZles23ResultInfoService()
					.getKonkaZles23ResultInfoPaginatedList(entity);
			request.setAttribute("entityList1", entityList1);
			return new ActionForward("/admin/KonkaZles23ResultInfo/excel.jsp");
        }// excel导出

		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaZles23ResultInfo> entityList = super.getFacade().getKonkaZles23ResultInfoService()
				.getKonkaZles23ResultInfoPaginatedList(entity);

		// TODO 今天必须修复脑残代码
		// if (entityList != null && entityList.size() > 0) {
		// for (KonkaZles23ResultInfo info : entityList) {
		// if (info != null && StringUtils.isNotBlank(info.getMatnr()) &&
		// StringUtils.isNotBlank(info.getLgort())
		// && info.getBudat1() != null) {
		// SimpleDateFormat format = new
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// info.getMap().put(
		// "price",
		// super.getFacade()
		// .getKonkaPriceManagerService()
		// .getKonkaPriceManagerForPrice(info.getMatnr(), info.getLgort(),
		// format.format(info.getBudat1())));
		// }
		// }
		// }

        // 集采数据暂时不计算价格.因为速度太慢.modify by zhouhaojie
		for (KonkaZles23ResultInfo info : entityList) {
			if (info != null && StringUtils.isNotBlank(info.getMatnr()) && StringUtils.isNotBlank(info.getLgort())
					&& info.getBudat1() != null) {
				info.getMap().put("price", 0);
			}
		}
			request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

}