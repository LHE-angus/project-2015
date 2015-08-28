package com.ebiz.mmt.web.struts.manager.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import report.dao.db2.ColumnsForDb2;
import report.domain.Column;
import report.domain.FiltersBean;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.SearchFilters;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.SerializeUtil;
import com.ebiz.org.apache.commons.lang3.StringUtils;

/**
 * @author Liu,ZhiXiang
 * @version 2013-09-05
 */
public class KonkaR3ShopForReportAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

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
				dynaBean.set("dept_sn", konkaDept.getDept_sn());
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

		List<Column> list = ColumnsForDb2.selectColumnList("KONKA_R3_SHOP");

		if (null == list || list.size() < 1) {
			String msg = "您查询的表在数据库中不存在！";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		} else {
			request.setAttribute("entityList", list);

			return new ActionForward("/admin/KonkaR3ShopForReport/search.jsp");
		}

	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String[] filter_params = (String[]) dynaBean.get("filter_params");// 过滤器名称
		String[] filter_operators = (String[]) dynaBean.get("filter_operators");// 过滤器运算符
		String[] filter_values = (String[]) dynaBean.get("filter_values");// 过滤器值域
		String[] filter_types = (String[]) dynaBean.get("filter_types");// 过滤器数据类型

		// String[] filter_group_flag = (String[]) dynaBean.get("filter_group_flag");// 分组标识
		// String[] group_params = (String[]) dynaBean.get("group_params");// 分组

		// String[] group_out_params = (String[]) dynaBean.get("group_out_params");// 分组输出 待计算列名称
		// String[] group_out_operators = (String[]) dynaBean.get("group_out_operators");// 分组输出 待计算列名称

		String[] out_all_flag = (String[]) dynaBean.get("out_all_flag");// 数据列全部输出
		String[] out_params = (String[]) dynaBean.get("out_params");// 输出

		// String[] order_params = (String[]) dynaBean.get("order_params");// 排序列名称
		// String[] order_values = (String[]) dynaBean.get("order_values");// 排序列

		FiltersBean b = new FiltersBean();
		b.setTable_name("KONKA_R3_SHOP");
		b.setFilter_params(filter_params);
		b.setFilter_operators(filter_operators);
		b.setFilter_values(filter_values);
		b.setFilter_types(filter_types);
		// b.setFilter_group_flag(filter_group_flag);
		// b.setGroup_params(group_params);
		// b.setGroup_out_params(group_out_params);
		// b.setGroup_out_operators(group_out_operators);
		if (null != out_all_flag && out_all_flag.length == 1 && out_all_flag[0].equals("1")) {
			List<Column> list = ColumnsForDb2.selectColumnList("KONKA_R3_SHOP");
			if (null != list && list.size() > 0) {
				String[] array = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					Column column = list.get(i);
					array[i] = column.getColumn_name();
				}
				b.setOut_params(array);
			}

		} else {
			b.setOut_params(out_params);
		}

		// b.setOrder_params(order_params);
		// b.setOrder_values(order_values);

		FiltersBean f = new FiltersBean();
		f = ColumnsForDb2.getReportForResult(b);
		if (StringUtils.isNotBlank(f.getError()) && "0".equals(f.getError())) {
			List<?> entityList = super.getFacade().getBaseReportService().getBaseReportForBindToArray(f.getSql(),
					f.getParams().toArray());
			request.setAttribute("entityList", entityList);
			request.setAttribute("diplay_name_list", f.getDiplay_name());
			request.setAttribute("show_num", f.getDiplay_name().size());
		} else {
			// 查询条件错误，无法返回结果
			String msg = "查询条件错误，无法返回结果";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		String filter_name = (String) dynaBean.get("filter_name");
		String search_save_flag = (String) dynaBean.get("search_save_flag");
		if (StringUtils.isNotBlank(search_save_flag) && "1".equals(search_save_flag)) {
			PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
			SearchFilters entity = new SearchFilters();
			entity.setFilter_name(filter_name);
			entity.setFilter_obj(SerializeUtil.objectToByteArray(b));
			logger.info("********************:" + SerializeUtil.objectToByteArray(b));
			logger.info("*******^^^^^^^^^^^^^:" + SerializeUtil.objectToByteArray(b).length);
			entity.setAdd_user_id(peProdUser.getId());
			entity.setAdd_date(new Date());
			entity.setFilter_type(3);// 1:订单统计, 2:综合查询分析, 3:客户信息查询
			super.getFacade().getSearchFiltersService().createSearchFilters(entity);
		}

		return mapping.findForward("view");
	}

}