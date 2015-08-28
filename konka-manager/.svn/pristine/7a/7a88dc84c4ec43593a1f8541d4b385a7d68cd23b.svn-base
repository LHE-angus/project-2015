package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JStocksVerify;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author ChenXiaoqi
 * @version 2014-08-15
 */
public class JStocksVerifyAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

        DynaBean dynaBean = (DynaBean) form;
        dynaBean.set("hander_type","0");
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		//
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		//
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String handle_name_like = (String) dynaBean.get("handle_name_like");// 经办名称
		String r3_code_like = (String) dynaBean.get("r3_code_like");

		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");

		String excel_to_all = (String) dynaBean.get("excel_to_all");

		// 仓库名称、编码、送达方编码
		String store_name_like = (String) dynaBean.get("store_name_like");
		String sale_r3_code_like = (String) dynaBean.get("sale_r3_code_like");
		// 一级客户类型
		String customer_type1 = (String) dynaBean.get("v_customer_type1");
		String is_allow_back = (String) dynaBean.get("is_allow_back");
		request.setAttribute("is_allow_back", is_allow_back);
		// 二级客户类型
		String customer_type2 = (String) dynaBean.get("v_customer_type2");
		// 操作类型，0人工，1系统自动
		String hander_type = (String) dynaBean.get("hander_type");

		// 是否区分仓库
		String store_flag = (String) dynaBean.get("store_flag");

		String date_begin = (String) dynaBean.get("date_begin");
		String date_end = (String) dynaBean.get("date_end");

		String goods_name_like = (String) dynaBean.get("goods_name_like");

		JStocksVerify entity = new JStocksVerify();

		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like.trim());
		}
		if (StringUtils.isNotBlank(r3_code_like)) {
			entity.getMap().put("r3_code_like", r3_code_like.trim());
		}

		if (StringUtils.isNotBlank(store_name_like)) {
			entity.getMap().put("store_name_like", store_name_like.trim());
			entity.getMap().put("store_flag", store_flag);
			request.setAttribute("store_flag", store_flag);
			dynaBean.set("store_flag", store_flag);
		}

		if (StringUtils.isNotBlank(sale_r3_code_like)) {
			entity.getMap().put("sale_r3_code_like", sale_r3_code_like.trim());
		}
		if (StringUtils.isNotBlank(goods_name_like)) {
			entity.getMap().put("goods_name_like", goods_name_like.trim());
		}
		// 添加客户类型筛选条件
		if (StringUtils.isNotBlank(customer_type2)) {
			entity.getMap().put("customer_type2", customer_type2);
		} else {
			if (StringUtils.isNotBlank(customer_type1)) {
				entity.getMap().put("customer_type1", customer_type1);
			}
		}

		if (StringUtils.isNotBlank(date_begin)) {
			entity.getMap().put("date_begin", date_begin);
		}
		if (StringUtils.isNotBlank(date_end)) {
			entity.getMap().put("date_end", date_end);
		}

		if (StringUtils.isNotBlank(handle_name_like)) {
			entity.getMap().put("handle_name_like_1", handle_name_like.trim());
			dynaBean.set("handle_name_like_1", handle_name_like.trim());
		}
		if (StringUtils.isNotBlank(hander_type))
			entity.setType(Integer.parseInt(hander_type));

		if (StringUtils.isNotBlank(store_flag)) {
			if ("1".equals(store_flag)) {
				entity.getMap().put("store_flag", store_flag);
				request.setAttribute("store_flag", store_flag);
				dynaBean.set("store_flag", store_flag);
			}

		} else {
			entity.getMap().put("store_flag", 1);
			request.setAttribute("store_flag", 1);
			dynaBean.set("store_flag", 1);
		}
		// 数据级别判断开始
		Long __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_sn()) {
				entity.getMap().put("dept_sn", dept_fgs.getDept_sn());
			}
			__dept_id = dept_fgs.getDept_id();
			entity.getMap().put("fgs_dept_id", __dept_id);
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);
			break;
		case 0:
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", ui.getId());

		if (StringUtils.isNotBlank(l5_dept_id)) {
			entity.getMap().put("l5_dept_id", l5_dept_id);
		} else if (StringUtils.isNotBlank(l4_dept_id)) {
			entity.getMap().put("l4_dept_id", l4_dept_id);
		} else if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("fgs_dept_id", dept_id);
		}

		Long recordCount = super.getFacade().getJStocksVerifyService().getJStocksVerifyForManagerCount(entity);
		//
		// Long recordCount =
		// super.getFacade().getJStocksStoreSummaryService().getJStocksStoreSummaryForR3ShopCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		if (StringUtils.isNotBlank(excel_to_all)) {

			if (recordCount > 30000) {// 导出数据不能超过30000条
				super.renderJavaScript(response, "alert('导出记录不能超过30000条，请缩小查找范围！');history.back();");
				return null;
			}

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("库存盘点记录")
					+ ".xls");

			// entity.getRow().setCount(recordCount.intValue());
			pager.init(recordCount, recordCount.intValue(), "1");
			entity.getRow().setFirst(pager.getFirstRow());
			entity.getRow().setCount(pager.getRowCount());

			List<JStocksVerify> entityList = super.getFacade().getJStocksVerifyService()
					.getJStocksVerifyForManagerPaginatedList(entity);
			request.setAttribute("entityList", entityList);
			return new ActionForward("/admin/JStocksVerify/list_for_report.jsp");
		}
		// // 客户类型

		List<JStocksVerify> entityList = super.getFacade().getJStocksVerifyService()
				.getJStocksVerifyForManagerPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward showDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String store_id = (String) dynaBean.get("store_id");
		String goods_id = (String) dynaBean.get("goods_id");
		String c_id = (String) dynaBean.get("c_id");
		String date_begin = (String) dynaBean.get("date_begin");
		String date_end = (String) dynaBean.get("date_end");
		String hander_type = (String) dynaBean.get("hander_type");
		JStocksVerify entity = new JStocksVerify();

		if (StringUtils.isNotBlank(store_id) && GenericValidator.isLong(store_id)) {
			entity.setStore_id(Long.valueOf(store_id));
		}

		if (StringUtils.isNotBlank(goods_id) && GenericValidator.isLong(goods_id)) {
			entity.setGoods_id(Long.valueOf(goods_id));
		}

		if (StringUtils.isNotBlank(c_id) && GenericValidator.isLong(c_id)) {
			entity.setC_id(Long.valueOf(c_id));
		}

		if (StringUtils.isNotBlank(date_begin)) {
			entity.getMap().put("date_begin", date_begin);
		}
		if (StringUtils.isNotBlank(date_end)) {
			entity.getMap().put("date_end", date_end);
		}
		if (StringUtils.isNotBlank(hander_type)) {
            if("1".equals(hander_type)) {
                entity.getMap().put("hander_type_1", "hander_type_1");
            }else{
                entity.getMap().put("hander_type_0", "hander_type_0");
            }
        }

		List<JStocksVerify> entityList = super.getFacade().getJStocksVerifyService().getJStocksVerifyList(entity);
		request.setAttribute("entityList", entityList);
		return new ActionForward("/admin/JStocksVerify/listdetails.jsp");
	}
}
