package com.ebiz.mmt.web.struts.manager.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Xing,XiuDong
 * @version 2013-05-17
 */
public class KonkaR3StoreSaleAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 判断session是否超时
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == ui) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		DynaBean dynaBean = (DynaBean) form;
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		super.encodeCharacterForGetMethod(dynaBean, request);

		PeRoleUser roleUser = new PeRoleUser();
		roleUser.setUser_id(ui.getId());
		List<PeRoleUser> roleUserList = super.getFacade().getPeRoleUserService().getPeRoleUserList(roleUser);

		boolean role_id_eq_10 = false; // 系统管理员
		// boolean role_id_eq_30 = false; // 分公司管理员

		// boolean role_id_eq_188 = false; // 促销员

		for (PeRoleUser peRoleUser : roleUserList) {
			if (peRoleUser.getRole_id() < 30L || (peRoleUser.getRole_id() >= 200L && peRoleUser.getRole_id() < 300L)) {
				role_id_eq_10 = true;
			}
		}

		Pager pager = (Pager) dynaBean.get("pager");
		String store_name_like = (String) dynaBean.get("store_name_like");
		String dept_id = (String) dynaBean.get("dept_id");
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String is_del = (String) dynaBean.get("is_del");
		String store_id = (String) dynaBean.get("store_id");
		String excel_to_all = (String) dynaBean.get("excel_to_all");

		String ywy_name_like = (String) dynaBean.get("ywy_name_like");
		String kh_name_like = (String) dynaBean.get("kh_name_like");

		String cur_date_begin = (String) dynaBean.get("cur_date_begin");// 本期的时间开始
		String cur_date_end = (String) dynaBean.get("cur_date_end");// 本期的时间结束

		// 一级客户类型
		String customer_type1 = (String) dynaBean.get("v_customer_type1");

		// 二级客户类型
		String customer_type2 = (String) dynaBean.get("v_customer_type2");

		// 经办名称
		String jing_ban_like = (String) dynaBean.get("jing_ban_like");

		// R3客户合并转入
		String merge_r3_code = (String) dynaBean.get("merge_r3_code");

		KonkaR3Store entity = new KonkaR3Store();
		super.copyProperties(entity, form);
		dynaBean.set("r3_code", entity.getR3_code());

		if (StringUtils.isNotBlank(merge_r3_code)) {
			entity.setR3_code(merge_r3_code);
			dynaBean.set("r3_code_like", merge_r3_code);
		}

		if (StringUtils.isNotBlank(kh_name_like)) {
			entity.getMap().put("kh_name_like", kh_name_like);
		}

		entity.setIs_del(0);

		if (StringUtils.isNotBlank(store_id) && GenericValidator.isLong(store_id)) {
			entity.setStore_id(Long.valueOf(store_id));
		}

		if (StringUtils.isNotBlank(is_del)) {
			entity.setIs_del(Integer.valueOf(is_del));
		}
		if (StringUtils.isNotBlank(store_name_like)) {
			store_name_like = store_name_like.replaceAll("&#40;", "（").replaceAll("&#41;", "）");
			entity.getMap().put("store_name_like", store_name_like);
		}

		if (StringUtils.isNotBlank(ywy_name_like)) {
			entity.getMap().put("ywy_name_like", ywy_name_like);
		}
		entity.getMap().put("r3_code_like", r3_code_like);

		if (StringUtils.isNotBlank(jing_ban_like)) {
			entity.setJb_name(jing_ban_like);
		}

		// 添加客户类型筛选条件
		if (customer_type1 != null && !"".equals(customer_type1)) {
			entity.getMap().put("customer_type1", customer_type1);
		}
		if (customer_type2 != null && !"".equals(customer_type2)) {
			entity.getMap().put("customer_type2", customer_type2);
		}

		// 数据级别判断开始
		Long __dept_id = StringUtils.isNotBlank(dept_id) ? Long.valueOf(dept_id) : ui.getDept_id(); // 默认为当前用户所在部门
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
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				entity.getMap().put("dept_id_start", __dept_id);
				request.setAttribute("current_fgs_code", __dept_id);
				request.setAttribute("show_fgs", true);
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);
			request.setAttribute("current_fgs_code", __dept_id);
			request.setAttribute("show_fgs", true);
			break;
		case 0:
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			// entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("filter_by_ywy_job_id_eq", ui.getJob_id());
			request.setAttribute("current_fgs_code", __dept_id);
			request.setAttribute("show_fgs", true);
			break;
		default:
			// 出错
		}
		// 数据级别判断结束
		entity.getMap().put("session_user_id", ui.getId());
		entity.getMap().put("filter_by_job_id_eq", ui.getJob_id());

		if (role_id_eq_10) {
			// 查询分公司基础数据
			KonkaDept kd = new KonkaDept();
			kd.setDept_id(ui.getDept_id());
			kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
			entity.getMap().put("dept_name", kd.getDept_name());
			entity.getMap().put("order_by_dept_name", true);
			// request.setAttribute("konkaDeptList",
			// super.getDeptInfoListWithOutLimit(mapping, form, request,
			// response));

			request.setAttribute("is_admin", "1");
		} else {
			dynaBean.set("dept_name", getKonkaDeptForFgs(ui.getDept_id()).getDept_name());
		}
		Long recordCount = 0L;

		if (StringUtils.isBlank(cur_date_begin) && StringUtils.isBlank(cur_date_end)) {
			DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			Calendar now = Calendar.getInstance();
			Calendar now_first = Calendar.getInstance();
			// 今天作为统计的结束时间
			entity.getMap().put("cur_date_end", df1.format(now.getTime()));

			dynaBean.set("cur_date_end", df1.format(now.getTime()));

			// 一年前今天作为上期的统计的结束时间
			now.add(Calendar.YEAR, -1);
			entity.getMap().put("last_date_end", df1.format(now.getTime()));

			// 当月第一天作为统计的开始时间
			now_first.set(Calendar.DAY_OF_MONTH, 1);
			entity.getMap().put("cur_date_begin", df1.format(now_first.getTime()));

			dynaBean.set("cur_date_begin", df1.format(now_first.getTime()));

			// 一年前的当月第一天作为统计的开始时间
			now_first.add(Calendar.YEAR, -1);
			entity.getMap().put("last_date_begin", df1.format(now_first.getTime()));

		} else {
			entity.getMap().put("cur_date_end", cur_date_end);
			entity.getMap().put("cur_date_begin", cur_date_begin);

			String[] data_end_spit = cur_date_end.split("-");
			String[] data_begin_spit = cur_date_begin.split("-");

			entity.getMap().put("last_date_end",
					(Integer.parseInt(data_end_spit[0]) - 1) + "-" + data_end_spit[1] + "-" + data_end_spit[2]);

			entity.getMap().put("last_date_begin",
					(Integer.parseInt(data_begin_spit[0]) - 1) + "-" + data_begin_spit[1] + "-" + data_begin_spit[2]);

		}

		recordCount = getFacade().getKonkaR3StoreService().getKonkaR3StoreSaleCount(entity);

		pager.init(recordCount, 10, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaR3Store> entityList = getFacade().getKonkaR3StoreService().getKonkaR3StoreSalePaginatedList(entity);
		request.setAttribute("entityList", entityList);

		if (StringUtils.isNotBlank(r3_code_like)) {
			KonkaR3Shop shop = new KonkaR3Shop();
			shop.setR3_code(r3_code_like);
			shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
			request.setAttribute("konkaR3Shop", shop);
		}

		if (StringUtils.isNotBlank(excel_to_all)) {

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("门店销售与照片")
					+ ".xls");

			entity.getRow().setCount(recordCount.intValue());
			List<KonkaR3Store> entityList1 = getFacade().getKonkaR3StoreService().getKonkaR3StoreSalePaginatedList(
					entity);
			request.setAttribute("allList", entityList1);
			return new ActionForward("/admin/KonkaR3StoreSale/listForReport.jsp");
		}

		return mapping.findForward("list");
	}

}