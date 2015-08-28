package com.ebiz.mmt.web.struts.manager.admin;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.StatisticalDimensionSaleArea;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.org.apache.commons.lang3.StringUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Xiao,GuoJian
 * @version 2014-11-24
 */
public class StatisticalDimensionSaleAreaAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	// 区域客户统计表 2014-11-22
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(50);
		String mod_id = (String) dynaBean.get("mod_id");

		String province = (String) dynaBean.get("province");// 省
		String city = (String) dynaBean.get("city");// 市
		String country = (String) dynaBean.get("country");// 县
		String town = (String) dynaBean.get("town");// 乡镇

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		StatisticalDimensionSaleArea entity = new StatisticalDimensionSaleArea();
		entity.setIs_del(0);// 默认只统计未停用的客户

		if (GenericValidator.isInt(town)) {// 精确到乡镇
			entity.setP_index(Long.parseLong(town));
		} else if (GenericValidator.isInt(country)) {// 精确到县区
			entity.getMap().put("cou_p_index", country.substring(0, 6));
		} else if (GenericValidator.isInt(city)) {// 精确到市
			entity.getMap().put("cit_p_index", city.substring(0, 4));
		} else if (GenericValidator.isInt(province)) {// 精确到省
			entity.getMap().put("pro_p_index", province.substring(0, 2));
		}

		Long recordCount = getFacade().getStatisticalDimensionSaleAreaService()
				.getStatisticalDimensionSaleAreaWithPindexCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<StatisticalDimensionSaleArea> entityList = super.getFacade().getStatisticalDimensionSaleAreaService()
				.getStatisticalDimensionSaleAreaWithPindexPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}

		return mapping.findForward("input");
	}

	public ActionForward add_excel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		// if (null == super.checkUserModPopeDom(form, request, "1")) {
		// return super.checkPopedomInvalid(request, response);
		// }

		return new ActionForward("/admin/StatisticalDimensionSaleArea/form_excel.jsp");
	}

	public ActionForward save_excel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		List<UploadFile> uploadFilesList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH);
		if (uploadFilesList.size() == 1) {
			String file_save_path = uploadFilesList.get(0).getFileSavePath();
			String ctxDir = getServlet().getServletContext().getRealPath(String.valueOf(File.separatorChar));
			
			// 附件保存路径更改遗留问题
			if (StringUtils.contains(file_save_path, MmtFilePathConfig.S_OTHERS_PATH)) {
				ctxDir = "/Attachment_new/konka-files/";
			}

			Map<Object, String> map = new HashMap<Object, String>();
			map.put(1, "p_index");
			map.put(2, "population");
			map.put(3, "area");
			map.put(4, "gdp");
			map.put(5, "market_size");
			map.put(6, "sale_num1");
			map.put(7, "sale_num2");
			map.put(8, "wpzb");
			map.put(9, "jpzb1");
			map.put(10, "jpzb2");
			map.put(11, "scjj");
			map.put(12, "wpjj");
			List<Object> list = super.getExcelList(map, StatisticalDimensionSaleArea.class, new File(ctxDir
					+ file_save_path), 1);
			if (null != list && list.size() > 0) {
				for (Object obj : list) {
					StatisticalDimensionSaleArea area = (StatisticalDimensionSaleArea) obj;
					area.setIs_del(0);
					area.setUpdate_date(new Date());
					super.getFacade().getStatisticalDimensionSaleAreaService().createStatisticalDimensionSaleArea(area);
				}
			}
		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('Excel文件未选择！');history.back();}");
			return null;
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end

		return forward;
	}

}
