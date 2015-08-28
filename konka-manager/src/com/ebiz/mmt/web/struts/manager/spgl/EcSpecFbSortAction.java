package com.ebiz.mmt.web.struts.manager.spgl;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcSpecFbUgdetail;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.ExcelUtil;
import com.ebiz.ssi.web.struts.bean.Pager;

public class EcSpecFbSortAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String user_name_like = (String) dynaBean.get("user_name_like");
		String real_name_like = (String) dynaBean.get("real_name_like");
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		EcSpecFbUgdetail entity = new EcSpecFbUgdetail();
		if (StringUtils.isNotBlank(user_name_like)) {
			entity.getMap().put("user_name_like", user_name_like);
		}
		if (StringUtils.isNotBlank(real_name_like)) {
			entity.getMap().put("real_name_like", real_name_like);
		}

		Long recordCount = super.getFacade().getEcSpecFbUgdetailService().getEcSpecFbUgdetailForTjCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcSpecFbUgdetail> entityList = super.getFacade().getEcSpecFbUgdetailService()
		        .getEcSpecFbUgdetailForTjList(entity);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward excel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String user_name_like = (String) dynaBean.get("user_name_like");
		String real_name_like = (String) dynaBean.get("real_name_like");
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		EcSpecFbUgdetail entity = new EcSpecFbUgdetail();
		if (StringUtils.isNotBlank(user_name_like)) {
			entity.getMap().put("user_name_like", user_name_like);
		}
		if (StringUtils.isNotBlank(real_name_like)) {
			entity.getMap().put("real_name_like", real_name_like);
		}

		Long recordCount = super.getFacade().getEcSpecFbUgdetailService().getEcSpecFbUgdetailForTjCount(entity);
		entity.getRow().setFirst(new Integer(0));
		entity.getRow().setCount(new Integer(recordCount.intValue()));
		List<EcSpecFbUgdetail> entityList = super.getFacade().getEcSpecFbUgdetailService()
		        .getEcSpecFbUgdetailForTjList(entity);

		ExcelUtil e = new ExcelUtil();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		int r = 0;
		e.setWorkbook(workbook);
		e.setSheet(sheet);
		e.createRow(r);
		e.setCell(0, "序号");
		e.setCell(1, "工卡号");
		e.setCell(2, "真实姓名");
		e.setCell(3, "猜中场次");
		e.setCell(4, "猜中比分");
		e.setCell(5, "积分");
		e.setCell(6, "排名");
		for (EcSpecFbUgdetail ecs : entityList) {
			r++;
			e.createRow(r);
			e.setCell(0, r);
			e.setCell(1, (String) ecs.getMap().get("card_no"));
			e.setCell(2, (String) ecs.getMap().get("real_name"));
			Object ww2_count = (Object) ecs.getMap().get("ww2_count");
			Object ww1_count = (Object) ecs.getMap().get("ww1_count");
			Object ww3_count = (Object) ecs.getMap().get("ww3_count");
			Object rowno = (Object) ecs.getMap().get("rowno");

			e.setCell(3, ww2_count.toString());
			e.setCell(4, ww1_count.toString());
			e.setCell(5, ww3_count.toString());
			e.setCell(6, rowno.toString());
		}
		// 输出
		response.setHeader("Content-disposition", "attachment; filename=jicai_record.xls");// 设定输出文件头
		response.setContentType("application/vnd.ms-excel");// 定义输出类型
		OutputStream out = response.getOutputStream();
		e.exportXLS(out);
		return null;
	}
}
