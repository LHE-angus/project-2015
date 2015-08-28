package com.ebiz.mmt.web.struts.manager.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.SearchFilters;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.SelectBean;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.SerializeUtil;
import com.ebiz.org.apache.commons.lang3.StringUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Liu,ZhiXiang
 * @version 2013-07-25
 */
public class KonkaR3OrSellReportAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		Date today = new Date();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);

		calendar.add(Calendar.DATE, -30);
		String day_first = df.format(calendar.getTime());
		String day_last = df.format(today);
		dynaBean.set("sell_date_start", day_first);
		dynaBean.set("sell_date_end", day_last);

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		} else if (peProdUser.getUser_type() == 0) {
			// 康佳总部
			dynaBean.set("dept_type", "1");
		} 
//		else if (peProdUser.getUser_type() == 1) {
//			String msg = super.getMessage(request, "popedom.check.invalid");
//			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
//			return null;
//		}
		//尺寸段基础数据
		super.getFacade().getKonkaBaseTypeDataService().setRequestSizeSecList(request, "sizeSecList", "100023");
		return mapping.findForward("list");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String sell_date_start = (String) dynaBean.get("sell_date_start");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String ywy_user_name = (String) dynaBean.get("ywy_user_name");
		String customer_name = (String) dynaBean.get("customer_name");
		String size_sec = (String) dynaBean.get("size_sec");
		String model_name = (String) dynaBean.get("model_name");
		String label_db = (String) dynaBean.get("label_db");
		String label_int = (String) dynaBean.get("label_int");

		// String sj_select = (String) dynaBean.get("sj_select");// 时间维度
		String[] group_by_field = (String[]) dynaBean.get("group_by_field");
		String[] show_field = (String[]) dynaBean.get("show_field");
		String[] contrast = (String[]) dynaBean.get("contrast");

		SelectBean b = new SelectBean();
		b.setSell_date_start(sell_date_start);
		b.setSell_date_end(sell_date_end);
		b.setDept_id(dept_id);
		b.setL4_dept_id(l4_dept_id);
		b.setL5_dept_id(l5_dept_id);
		b.setYwy_user_name(ywy_user_name);
		b.setCustomer_name(customer_name);
		b.setSize_sec(size_sec);
		b.setModel_name(model_name);
		b.setLabel_db(label_db);
		b.setLabel_int(label_int);
		b.setGroup_by_field(group_by_field);
		b.setShow_field(show_field);
		b.setContrast(contrast);

		b = this.getKonkaR3OrSellReport(b);
		request.setAttribute("entityList", b.getEntityList());
		request.setAttribute("show_num", b.getShow_num());
		request.setAttribute("group_flag_string", b.getGroup_flag_string());
		request.setAttribute("show_field_string", b.getShow_field_string());

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
			entity.setFilter_type(1);// 1:订单统计, 2:综合查询分析
			super.getFacade().getSearchFiltersService().createSearchFilters(entity);
		}
		//尺寸段基础数据
		super.getFacade().getKonkaBaseTypeDataService().setRequestSizeSecList(request, "sizeSecList", "100023");
		return mapping.findForward("view");
	}

	public ActionForward list_mul(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		Date today = new Date();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);

		String month = df.format(calendar.getTime());

		dynaBean.set("sell_date_start", month);
		dynaBean.set("sell_date_end", month);

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		} else if (peProdUser.getUser_type() == 0) {
			// 康佳总部
			dynaBean.set("dept_type", "1");
		} else if (peProdUser.getUser_type() == 1) {
			//String msg = super.getMessage(request, "popedom.check.invalid");
			//super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			//return null;
		}
		//尺寸段基础数据
		super.getFacade().getKonkaBaseTypeDataService().setRequestSizeSecList(request, "sizeSecList", "100023");
		
		return new ActionForward("/admin/KonkaR3OrSellReport/list_mul.jsp");
	}

	public ActionForward view_mul(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String sell_date_start = (String) dynaBean.get("sell_date_start");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String ywy_user_name = (String) dynaBean.get("ywy_user_name");
		String customer_name = (String) dynaBean.get("customer_name");
		String size_sec = (String) dynaBean.get("size_sec");
		String model_name = (String) dynaBean.get("model_name");
		String label_db = (String) dynaBean.get("label_db");
		String label_int = (String) dynaBean.get("label_int");

		// String sj_select = (String) dynaBean.get("sj_select");// 时间维度
		String[] group_by_field = (String[]) dynaBean.get("group_by_field");
		String[] show_field = (String[]) dynaBean.get("show_field");
		String[] contrast = (String[]) dynaBean.get("contrast");

		SelectBean b = new SelectBean();
		b.setSell_date_start(sell_date_start);
		b.setSell_date_end(sell_date_end);
		b.setDept_id(dept_id);
		b.setL4_dept_id(l4_dept_id);
		b.setL5_dept_id(l5_dept_id);
		b.setYwy_user_name(ywy_user_name);
		b.setCustomer_name(customer_name);
		b.setSize_sec(size_sec);
		b.setModel_name(model_name);
		b.setLabel_db(label_db);
		b.setLabel_int(label_int);
		b.setGroup_by_field(group_by_field);
		b.setShow_field(show_field);
		b.setContrast(contrast);

		b = this.getKonkaR3OrSellReportForMul(b);
		request.setAttribute("entityList", b.getEntityList());
		request.setAttribute("show_num", b.getShow_num());
		request.setAttribute("group_flag_string", b.getGroup_flag_string());
		request.setAttribute("show_field_string", b.getShow_field_string());

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
			entity.setFilter_type(2);// 1:订单统计, 2:综合查询分析
			super.getFacade().getSearchFiltersService().createSearchFilters(entity);
		}
		//尺寸段基础数据
		super.getFacade().getKonkaBaseTypeDataService().setRequestSizeSecList(request, "sizeSecList", "100023");
		
		return new ActionForward("/admin/KonkaR3OrSellReport/view_mul.jsp");
	}

	public ActionForward list_search(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String filter_name = (String) dynaBean.get("filter_name");
		String filter_type = (String) dynaBean.get("filter_type");
		Pager pager = (Pager) dynaBean.get("pager");
		SearchFilters entity = new SearchFilters();
		if (StringUtils.isNotBlank(filter_name)) {
			entity.setFilter_name(filter_name);
		}
		if (StringUtils.isNotBlank(filter_type)) {
			entity.setFilter_type(Integer.valueOf(filter_type));
		}
		entity.setIs_del(0);

		Long recordCount = getFacade().getSearchFiltersService().getSearchFiltersCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<SearchFilters> entityList = getFacade().getSearchFiltersService().getSearchFiltersPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		return new ActionForward("/admin/KonkaR3OrSellReport/list_search.jsp");
	}

	public ActionForward view_search(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String filter_type = (String) dynaBean.get("filter_type");
		SearchFilters entity = new SearchFilters();
		if (GenericValidator.isLong(id)) {
			entity.setId(new Long(id));
			entity = super.getFacade().getSearchFiltersService().getSearchFilters(entity);
		} else {
			String msg = super.getMessage(request, "errors.long");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		SelectBean b = new SelectBean();
		b = (SelectBean) SerializeUtil.byteArrayToObject(entity.getFilter_obj());

		if (StringUtils.isNotBlank(filter_type) && "1".equals(filter_type)) {
			b = this.getKonkaR3OrSellReport(b);
		} else if (StringUtils.isNotBlank(filter_type) && "2".equals(filter_type)) {
			b = this.getKonkaR3OrSellReportForMul(b);
		}

		request.setAttribute("entityList", b.getEntityList());
		request.setAttribute("show_num", b.getShow_num());
		request.setAttribute("group_flag_string", b.getGroup_flag_string());
		request.setAttribute("show_field_string", b.getShow_field_string());

		if (StringUtils.isNotBlank(filter_type) && "1".equals(filter_type)) {
			return mapping.findForward("view");
		} else if (StringUtils.isNotBlank(filter_type) && "2".equals(filter_type)) {
			return new ActionForward("/admin/KonkaR3OrSellReport/view_mul.jsp");
		}
		return null;
	}

	public SelectBean getKonkaR3OrSellReport(SelectBean b) throws ParseException {

		String sell_date_start = b.getSell_date_start();
		String sell_date_end = b.getSell_date_end();
		String dept_id = b.getDept_id();
		String l4_dept_id = b.getL4_dept_id();
		String l5_dept_id = b.getL5_dept_id();
		String ywy_user_name = b.getYwy_user_name();
		String customer_name = b.getCustomer_name();
		String size_sec = b.getSize_sec();
		String model_name = b.getModel_name();
		String label_db = b.getLabel_db();
		String label_int = b.getLabel_int();

		String[] group_by_field = b.getGroup_by_field();
		String[] show_field = b.getShow_field();
		String[] contrast = b.getContrast();

		String select1 = " select ";// 查询结果数据项（包含同比、环比等）
		String select2 = " select ";// 查询结果数据项
		String group = " group by ";// 分组
		String order = " order by ";// 排序

		String select_tb = " select "; // 同比sql
		String select_hb = " select ";// 环比sql
		String left_join_on_tb = " on 1 = 1 "; // 同比
		String left_join_on_hb = " on 1 = 1 "; // 环比
		int contrast_type = 0;
		if (null != contrast && contrast.length > 0) {
			if (contrast.length == 1 && "1".equals(contrast[0])) {
				// 同比
				contrast_type = 1;
			} else if (contrast.length == 1 && "2".equals(contrast[0])) {
				// 环比
				contrast_type = 2;
			} else if (contrast.length == 2 && "1".equals(contrast[0]) && "2".equals(contrast[1])) {
				// 同比、环比
				contrast_type = 3;

			}
		}
		// aa_1:分公司 , aa_2:经营部, aa_3:办事处 , aa_4:业务员 , aa_5:客户
		// bb_1:年度 , bb_2:季度 , bb_3:月度 , bb_4:周 , bb_5:日
		// cc_1:尺寸规格, cc_2:型号 , cc_3:是否大板, cc_4:是否智能
		// dd_1:客户分类大类, dd_2:客户分类明细

		String where = " where 1 = 1 and t.model_name is not null ";

		String group_flag_string = ",";
		int show_num = 0;
		String time_flag = "";// 用于环比的时间范围定义
		if (null != group_by_field && group_by_field.length > 0) {
			group_by_field = selectSortForString(group_by_field);
			show_num += group_by_field.length;
			for (int i = 0; i < group_by_field.length; i++) {

				// 分组织架构
				if ("aa_1".equals(group_by_field[i])) {
					select1 += " aa.dept_name,";
					select2 += " t.dept_id,t.dept_name,";
					group += " t.dept_name,t.dept_id,";
					order += " t.dept_name asc,t.dept_id asc,";
					where += " and t.dept_id is not null ";

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.dept_id,t.dept_name,";
						left_join_on_tb += " and aa.dept_id = bb.dept_id ";
					} else if (contrast_type == 2) {
						select_hb += " t.dept_id,t.dept_name,";
						left_join_on_hb += " and aa.dept_id = cc.dept_id ";
					} else if (contrast_type == 3) {
						select_tb += " t.dept_id,t.dept_name,";
						select_hb += " t.dept_id,t.dept_name,";
						left_join_on_tb += " and aa.dept_id = bb.dept_id ";
						left_join_on_hb += " and aa.dept_id = cc.dept_id ";
					}

				}
				if ("aa_2".equals(group_by_field[i])) {
					select1 += " aa.l4_dept_name,";
					select2 += " t.l4_dept_id,t.l4_dept_name,";
					group += " t.l4_dept_name,t.l4_dept_id,";
					order += " t.l4_dept_name asc,t.l4_dept_id asc,";
					where += " and t.l4_dept_id is not null ";

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.l4_dept_id,t.l4_dept_name,";
						left_join_on_tb += " and aa.l4_dept_id = bb.l4_dept_id ";
					} else if (contrast_type == 2) {
						select_hb += " t.l4_dept_id,t.l4_dept_name,";
						left_join_on_hb += " and aa.l4_dept_id = cc.l4_dept_id ";
					} else if (contrast_type == 3) {
						select_tb += " t.l4_dept_id,t.l4_dept_name,";
						select_hb += " t.l4_dept_id,t.l4_dept_name,";
						left_join_on_tb += " and aa.l4_dept_id = bb.l4_dept_id ";
						left_join_on_hb += " and aa.l4_dept_id = cc.l4_dept_id ";
					}
				}
				if ("aa_3".equals(group_by_field[i])) {
					select1 += " aa.l5_dept_name,";
					select2 += " t.l5_dept_id,t.l5_dept_name,";
					group += " t.l5_dept_name,t.l5_dept_id,";
					order += " t.l5_dept_name asc,t.l5_dept_id asc,";
					where += " and t.l5_dept_id is not null ";

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.l5_dept_id,t.l5_dept_name,";
						left_join_on_tb += " and aa.l5_dept_id = bb.l5_dept_id ";
					} else if (contrast_type == 2) {
						select_hb += " t.l5_dept_id,t.l5_dept_name,";
						left_join_on_hb += " and aa.l5_dept_id = cc.l5_dept_id ";
					} else if (contrast_type == 3) {
						select_tb += " t.l5_dept_id,t.l5_dept_name,";
						select_hb += " t.l5_dept_id,t.l5_dept_name,";
						left_join_on_tb += " and aa.l5_dept_id = bb.l5_dept_id ";
						left_join_on_hb += " and aa.l5_dept_id = cc.l5_dept_id ";
					}
				}
				if ("aa_4".equals(group_by_field[i])) {
					select1 += " aa.ywy_user_name,";
					select2 += " t.user_id,t.ywy_user_name,";
					group += " t.ywy_user_name,t.user_id,";
					order += " t.ywy_user_name asc,t.user_id asc,";
					where += " and t.user_id is not null ";

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.user_id,t.ywy_user_name,";
						left_join_on_tb += " and aa.user_id = bb.user_id ";
					} else if (contrast_type == 2) {
						select_hb += " t.user_id,t.ywy_user_name,";
						left_join_on_hb += " and aa.user_id = cc.user_id ";
					} else if (contrast_type == 3) {
						select_tb += " t.user_id,t.ywy_user_name,";
						select_hb += " t.user_id,t.ywy_user_name,";
						left_join_on_tb += " and aa.user_id = bb.user_id ";
						left_join_on_hb += " and aa.user_id = cc.user_id ";
					}
				}
				if ("aa_5".equals(group_by_field[i])) {
					select1 += " aa.customer_name,";
					select2 += " t.konka_r3_id,t.customer_name,";
					group += " t.customer_name,t.konka_r3_id,";
					order += " t.customer_name asc,t.konka_r3_id asc,";
					where += " and t.konka_r3_id is not null ";

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.konka_r3_id,t.customer_name,";
						left_join_on_tb += " and aa.konka_r3_id = bb.konka_r3_id ";
					} else if (contrast_type == 2) {
						select_hb += " t.konka_r3_id,t.customer_name,";
						left_join_on_hb += " and aa.konka_r3_id = cc.konka_r3_id ";
					} else if (contrast_type == 3) {
						select_tb += " t.konka_r3_id,t.customer_name,";
						select_hb += " t.konka_r3_id,t.customer_name,";
						left_join_on_tb += " and aa.konka_r3_id = bb.konka_r3_id ";
						left_join_on_hb += " and aa.konka_r3_id = cc.konka_r3_id ";
					}
				}

				// 时间维度
				if ("bb_1".equals(group_by_field[i])) {
					select1 += " aa.y,";
					select2 += " t.y,";
					group += " t.y,";
					order += " t.y asc,";

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.y,";
						left_join_on_tb += " and aa.y-1 = bb.y ";
					} else if (contrast_type == 2) {
						select_hb += " t.y,";
						left_join_on_hb += " and aa.y-1 = cc.y ";
					} else if (contrast_type == 3) {
						select_tb += " t.y,";
						select_hb += " t.y,";
						left_join_on_tb += " and aa.y-1 = bb.y ";
						left_join_on_hb += " and aa.y-1 = cc.y ";
					}
					time_flag = "bb_1";// 用于环比的时间范围定义
				}
				if ("bb_2".equals(group_by_field[i])) {
					select1 += " aa.y,aa.q,";
					select2 += " t.y,t.q,";
					group += " t.y,t.q,";
					order += " t.y asc,t.q asc,";

					group_flag_string += "bb_1" + ",";
					show_num += 1;

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.y,t.q,";
						left_join_on_tb += " and aa.y-1 = bb.y and aa.q = bb.q ";
					} else if (contrast_type == 2) {
						select_hb += " t.y,t.q,";
						left_join_on_hb += " and ((aa.q = '1' and aa.y - 1 = cc.y and cc.q = '4') or (aa.q != '1' and aa.y = cc.y and aa.q - 1 = cc.q)) ";
					} else if (contrast_type == 3) {
						select_tb += " t.y,t.q,";
						select_hb += " t.y,t.q,";
						left_join_on_tb += " and aa.y-1 = bb.y and aa.q = bb.q ";
						left_join_on_hb += " and ((aa.q = '1' and aa.y - 1 = cc.y and cc.q = '4') or (aa.q != '1' and aa.y = cc.y and aa.q - 1 = cc.q)) ";
					}

					time_flag = "bb_2";// 用于环比的时间范围定义
				}
				if ("bb_3".equals(group_by_field[i])) {
					select1 += " aa.y,aa.m,";
					select2 += " t.y,t.m,";
					group += " t.y,t.m,";
					order += " t.y asc,t.m asc,";

					group_flag_string += "bb_1" + ",";
					show_num += 1;

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.y,t.m,";
						left_join_on_tb += " and aa.y-1 = bb.y and aa.m = bb.m ";
					} else if (contrast_type == 2) {
						select_hb += " t.y,t.m,";
						left_join_on_hb += " and ((aa.m = '01' and aa.y - 1 = cc.y and cc.m = '12') or (aa.m != '01' and aa.y = cc.y and to_number(aa.m) - 1 = to_number(cc.m))) ";
					} else if (contrast_type == 3) {
						select_tb += " t.y,t.m,";
						select_hb += " t.y,t.m,";
						left_join_on_tb += " and aa.y-1 = bb.y and aa.m = bb.m ";
						left_join_on_hb += " and ((aa.m = '01' and aa.y - 1 = cc.y and cc.m = '12') or (aa.m != '01' and aa.y = cc.y and to_number(aa.m) - 1 = to_number(cc.m))) ";
					}

					time_flag = "bb_3";// 用于环比的时间范围定义
				}
				if ("bb_4".equals(group_by_field[i])) {
					select1 += " aa.y,aa.ww,";
					select2 += " t.y,t.ww,";
					group += " t.y,t.ww,";
					order += " t.y asc,t.ww asc,";

					group_flag_string += "bb_1" + ",";
					show_num += 1;

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.y,t.ww,";
						left_join_on_tb += " and aa.y-1 = bb.y and aa.ww = bb.ww ";
					} else if (contrast_type == 2) {
						select_hb += " t.y,t.ww,";
						left_join_on_hb += " and ((aa.ww = '01' and aa.y - 1 = cc.y and cc.ww = to_char(to_date(cc.y||'-12-31','yyyy-mm-dd'),'ww')) or (aa.ww != '01' and aa.y = cc.y and to_number(aa.ww) - 1 = to_number(cc.ww))) ";
					} else if (contrast_type == 3) {
						select_tb += " t.y,t.ww,";
						select_hb += " t.y,t.ww,";
						left_join_on_tb += " and aa.y-1 = bb.y and aa.ww = bb.ww ";
						left_join_on_hb += " and ((aa.ww = '01' and aa.y - 1 = cc.y and cc.ww = to_char(to_date(cc.y||'-12-31','yyyy-mm-dd'),'ww')) or (aa.ww != '01' and aa.y = cc.y and to_number(aa.ww) - 1 = to_number(cc.ww))) ";
					}

					time_flag = "bb_4";// 用于环比的时间范围定义
				}
				if ("bb_5".equals(group_by_field[i])) {
					select1 += " aa.add_day,";
					select2 += " t.add_day,";
					group += " t.add_day,";
					order += " t.add_day asc,";

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.add_day,";
						left_join_on_tb += " and (aa.y-1)||substr(aa.add_day,5,10) = bb.add_day ";
					} else if (contrast_type == 2) {
						select_hb += " t.add_day,";
						left_join_on_hb += " and to_date(aa.add_day,'yyyy-mm-dd')-1 = to_date(cc.add_day,'yyyy-mm-dd') ";
					} else if (contrast_type == 3) {
						select_tb += " t.add_day,";
						select_hb += " t.add_day,";
						left_join_on_tb += " and (aa.y-1)||substr(aa.add_day,5,10) = bb.add_day ";
						left_join_on_hb += " and to_date(aa.add_day,'yyyy-mm-dd')-1 = to_date(cc.add_day,'yyyy-mm-dd') ";
					}

					time_flag = "bb_5";// 用于环比的时间范围定义
				}
				// 产品维度
				if ("cc_1".equals(group_by_field[i])) {
					select1 += " decode(aa.size_sec,0,'32寸及以下',1,'32-36寸',2,'37-39寸',3,'40-45寸',4,'46-50寸',5,'51-59寸',6,'60-69寸',7,'70寸及以上',-1,'其他','未标识') as size_sec,";
					select2 += " t.size_sec,";
					group += " t.size_sec,";
					order += " t.size_sec asc,";

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.size_sec,";
						left_join_on_tb += " and aa.size_sec = bb.size_sec ";
					} else if (contrast_type == 2) {
						select_hb += " t.size_sec,";
						left_join_on_hb += " and aa.size_sec = cc.size_sec ";
					} else if (contrast_type == 3) {
						select_tb += " t.size_sec,";
						select_hb += " t.size_sec,";
						left_join_on_tb += " and aa.size_sec = bb.size_sec ";
						left_join_on_hb += " and aa.size_sec = cc.size_sec ";
					}
				}
				if ("cc_2".equals(group_by_field[i])) {
					select1 += " aa.model_name,";
					select2 += " t.model_name,";
					group += " t.model_name,";
					order += " t.model_name asc,";

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.model_name,";
						left_join_on_tb += " and aa.model_name = bb.model_name ";
					} else if (contrast_type == 2) {
						select_hb += " t.model_name,";
						left_join_on_hb += " and aa.model_name = cc.model_name ";
					} else if (contrast_type == 3) {
						select_tb += " t.model_name,";
						select_hb += " t.model_name,";
						left_join_on_tb += " and aa.model_name = bb.model_name ";
						left_join_on_hb += " and aa.model_name = cc.model_name ";
					}
				}
				if ("cc_3".equals(group_by_field[i])) {
					select1 += " decode(aa.label_db,0,'非大板',1,'大板','未标识'),";
					select2 += " t.label_db,";
					group += " t.label_db,";
					order += " t.label_db asc,";

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.label_db,";
						left_join_on_tb += " and aa.label_db = bb.label_db ";
					} else if (contrast_type == 2) {
						select_hb += " t.label_db,";
						left_join_on_hb += " and aa.label_db = cc.label_db ";
					} else if (contrast_type == 3) {
						select_tb += " t.label_db,";
						select_hb += " t.label_db,";
						left_join_on_tb += " and aa.label_db = bb.label_db ";
						left_join_on_hb += " and aa.label_db = cc.label_db ";
					}
				}
				if ("cc_4".equals(group_by_field[i])) {
					select1 += " decode(aa.label_int,0,'非智能',1,'智能','未标识'),";
					select2 += " t.label_int,";
					group += " t.label_int,";
					order += " t.label_int asc,";

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.label_int,";
						left_join_on_tb += " and aa.label_int = bb.label_int ";
					} else if (contrast_type == 2) {
						select_hb += " t.label_int,";
						left_join_on_hb += " and aa.label_int = cc.label_int ";
					} else if (contrast_type == 3) {
						select_tb += " t.label_int,";
						select_hb += " t.label_int,";
						left_join_on_tb += " and aa.label_int = bb.label_int ";
						left_join_on_hb += " and aa.label_int = cc.label_int ";
					}
				}

				// 客户分类
				if ("dd_1".equals(group_by_field[i])) {
					select1 += " aa.par_customer_type_name,";
					select2 += " t.par_customer_type,t.par_customer_type_name,";
					group += " t.par_customer_type_name,t.par_customer_type,";
					order += " t.par_customer_type_name asc,t.par_customer_type asc,";
					where += " and t.par_customer_type is not null ";

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.par_customer_type,t.par_customer_type_name,";
						left_join_on_tb += " and aa.par_customer_type = bb.par_customer_type ";
					} else if (contrast_type == 2) {
						select_hb += " t.par_customer_type,t.par_customer_type_name,";
						left_join_on_hb += " and aa.par_customer_type = cc.par_customer_type ";
					} else if (contrast_type == 3) {
						select_tb += " t.par_customer_type,t.par_customer_type_name,";
						select_hb += " t.par_customer_type,t.par_customer_type_name,";
						left_join_on_tb += " and aa.par_customer_type = bb.par_customer_type ";
						left_join_on_hb += " and aa.par_customer_type = cc.par_customer_type ";
					}
				}
				if ("dd_2".equals(group_by_field[i])) {
					select1 += " aa.customer_type_name,";
					select2 += " t.customer_type,t.customer_type_name,";
					group += " t.customer_type_name,t.customer_type,";
					order += " t.customer_type_name asc,t.customer_type asc,";
					where += " and t.customer_type is not null ";

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.customer_type,t.customer_type_name,";
						left_join_on_tb += " and aa.customer_type = bb.customer_type ";
					} else if (contrast_type == 2) {
						select_hb += " t.customer_type,t.customer_type_name,";
						left_join_on_hb += " and aa.customer_type = cc.customer_type ";
					} else if (contrast_type == 3) {
						select_tb += " t.customer_type,t.customer_type_name,";
						select_hb += " t.customer_type,t.customer_type_name,";
						left_join_on_tb += " and aa.customer_type = bb.customer_type ";
						left_join_on_hb += " and aa.customer_type = cc.customer_type ";
					}
				}

				group_flag_string += group_by_field[i] + ",";

			}
		}
		// 10001:R3销售量, 10011:R3销售额, 10021:零售量, 10031:零售额
		String show_field_string = ",";
		if (null != show_field && show_field.length > 0) {
			show_field = selectSortForInteger(show_field);
			show_num += show_field.length;
			for (int i = 0; i < show_field.length; i++) {
				show_field_string += show_field[i] + ",";
				if ("10001".equals(show_field[i])) {
					select1 += " aa.total_counts_of_buy,";
					select2 += " value(sum(t.total_counts_of_buy),0) as total_counts_of_buy,";
					// 同比、环比判断
					if (contrast_type == 1) {
						select1 += " value(bb.total_counts_of_buy,0),";
						select_tb += " value(sum(t.total_counts_of_buy),0) as total_counts_of_buy,";
						show_field_string += "10002" + ",";// 增加页面环比显示的判断标识
						show_num += 1;// 显示list横向的循环此次
					} else if (contrast_type == 2) {
						select1 += " value(cc.total_counts_of_buy,0),";
						select_hb += " value(sum(t.total_counts_of_buy),0) as total_counts_of_buy,";
						show_field_string += "10003" + ",";// 增加页面环比显示的判断标识
						show_num += 1;// 显示list横向的循环此次
					} else if (contrast_type == 3) {
						select1 += " value(bb.total_counts_of_buy,0),value(cc.total_counts_of_buy,0),";
						select_tb += " value(sum(t.total_counts_of_buy),0) as total_counts_of_buy,";
						select_hb += " value(sum(t.total_counts_of_buy),0) as total_counts_of_buy,";
						show_field_string += "10002" + ",";// 增加页面环比显示的判断标识
						show_field_string += "10003" + ",";// 增加页面环比显示的判断标识
						show_num += 2;// 显示list横向的循环此次
					}
				}
				if ("10011".equals(show_field[i])) {
					select1 += " aa.total_money_of_buy,";
					select2 += " value(sum(t.total_money_of_buy),0) as total_money_of_buy,";
					// 同比、环比判断
					if (contrast_type == 1) {
						select1 += " value(bb.total_money_of_buy,0),";
						select_tb += " value(sum(t.total_money_of_buy),0) as total_money_of_buy,";
						show_field_string += "10012" + ",";// 增加页面环比显示的判断标识
						show_num += 1;// 显示list横向的循环此次
					} else if (contrast_type == 2) {
						select1 += " value(cc.total_money_of_buy,0),";
						select_hb += " value(sum(t.total_money_of_buy),0) as total_money_of_buy,";
						show_field_string += "10013" + ",";// 增加页面环比显示的判断标识
						show_num += 1;// 显示list横向的循环此次
					} else if (contrast_type == 3) {
						select1 += " value(bb.total_money_of_buy,0),value(cc.total_money_of_buy,0),";
						select_tb += " value(sum(t.total_money_of_buy),0) as total_money_of_buy,";
						select_hb += " value(sum(t.total_money_of_buy),0) as total_money_of_buy,";
						show_field_string += "10012" + ",";// 增加页面环比显示的判断标识
						show_field_string += "10013" + ",";// 增加页面环比显示的判断标识
						show_num += 2;// 显示list横向的循环此次
					}
				}
				if ("10021".equals(show_field[i])) {
					select1 += " aa.total_counts_of_sale,";
					select2 += " value(sum(t.total_counts_of_sale),0) as total_counts_of_sale,";
					// 同比、环比判断
					if (contrast_type == 1) {
						select1 += " value(bb.total_counts_of_sale,0),";
						select_tb += " value(sum(t.total_counts_of_sale),0) as total_counts_of_sale,";
						show_field_string += "10022" + ",";// 增加页面环比显示的判断标识
						show_num += 1;// 显示list横向的循环此次
					} else if (contrast_type == 2) {
						select1 += " value(cc.total_counts_of_sale,0),";
						select_hb += " value(sum(t.total_counts_of_sale),0) as total_counts_of_sale,";
						show_field_string += "10023" + ",";// 增加页面环比显示的判断标识
						show_num += 1;// 显示list横向的循环此次
					} else if (contrast_type == 3) {
						select1 += " value(bb.total_counts_of_sale,0),value(cc.total_counts_of_sale,0),";
						select_tb += " value(sum(t.total_counts_of_sale),0) as total_counts_of_sale,";
						select_hb += " value(sum(t.total_counts_of_sale),0) as total_counts_of_sale,";
						show_field_string += "10022" + ",";// 增加页面环比显示的判断标识
						show_field_string += "10023" + ",";// 增加页面环比显示的判断标识
						show_num += 2;// 显示list横向的循环此次
					}
				}
				if ("10031".equals(show_field[i])) {
					select1 += " aa.total_money_of_sale,";
					select2 += " value(sum(t.total_money_of_sale),0) as total_money_of_sale,";
					// 同比、环比判断
					if (contrast_type == 1) {
						select1 += " value(bb.total_money_of_sale,0),";
						select_tb += " value(sum(t.total_money_of_sale),0) as total_money_of_sale,";
						show_field_string += "10032" + ",";// 增加页面环比显示的判断标识
						show_num += 1;// 显示list横向的循环此次
					} else if (contrast_type == 2) {
						select1 += " value(cc.total_money_of_sale,0),";
						select_hb += " value(sum(t.total_money_of_sale),0) as total_money_of_sale,";
						show_field_string += "10033" + ",";// 增加页面环比显示的判断标识
						show_num += 1;// 显示list横向的循环此次
					} else if (contrast_type == 3) {
						select1 += " value(bb.total_money_of_sale,0),value(cc.total_money_of_sale,0),";
						select_tb += " value(sum(t.total_money_of_sale),0) as total_money_of_sale,";
						select_hb += " value(sum(t.total_money_of_sale),0) as total_money_of_sale,";
						show_field_string += "10032" + ",";// 增加页面环比显示的判断标识
						show_field_string += "10033" + ",";// 增加页面环比显示的判断标识
						show_num += 2;// 显示list横向的循环此次
					}
				}
			}
		}
		select1 = StringUtils.removeEnd(select1, ",") + " from ( ";
		select2 = StringUtils.removeEnd(select2, ",");
		select_tb = StringUtils.removeEnd(select_tb, ",");
		select_hb = StringUtils.removeEnd(select_hb, ",");
		group = StringUtils.removeEnd(group, ",");
		order = StringUtils.removeEnd(order, ",");

		List<String> array = new ArrayList<String>();
		List<String> array_temp = new ArrayList<String>();
		// 因为，同比、环比时间需要特殊处理
		// if (StringUtils.isNotBlank(sell_date_start)) {
		// where += " and t.add_day >= ? ";
		// array.add(sell_date_start);
		//
		// // 同比、环比判断时间，重新定义时间范围
		// }
		// if (StringUtils.isNotBlank(sell_date_end)) {
		// where += " and t.add_day <= ? ";
		// array.add(sell_date_end);
		//
		// // 同比、环比判断时间，重新定义时间范围
		// }
		if (StringUtils.isNotBlank(dept_id)) {
			where += " and t.dept_id = ? ";
			array.add(dept_id);
			array_temp.add(dept_id);
		}
		if (StringUtils.isNotBlank(l4_dept_id)) {
			where += " and t.l4_dept_id = ? ";
			array.add(l4_dept_id);
			array_temp.add(l4_dept_id);
		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			where += " and t.l5_dept_id = ? ";
			array.add(l5_dept_id);
			array_temp.add(l5_dept_id);
		}
		if (StringUtils.isNotBlank(ywy_user_name)) {
			where += " and t.ywy_user_name like '%'||?||'%' ";
			array.add(ywy_user_name);
			array_temp.add(ywy_user_name);
		}
		if (StringUtils.isNotBlank(customer_name)) {
			where += " and t.customer_name like '%'||?||'%' ";
			array.add(customer_name);
			array_temp.add(customer_name);
		}
		if (StringUtils.isNotBlank(size_sec)) {
			where += " and t.size_sec = ? ";
			array.add(size_sec);
			array_temp.add(size_sec);
		}
		if (StringUtils.isNotBlank(model_name)) {
			where += " and t.model_name like '%'||?||'%' ";
			array.add(model_name);
			array_temp.add(model_name);
		}
		if (StringUtils.isNotBlank(label_db)) {
			where += " and t.label_db = ? ";
			array.add(label_db);
			array_temp.add(label_db);
		}
		if (StringUtils.isNotBlank(label_int)) {
			where += " and t.label_int = ? ";
			array.add(label_int);
			array_temp.add(label_int);
		}

		select2 += " from V_A_DETAILS_OF_SALE_AND_BUY t ";
		select2 += where;
		// 因为，同比、环比，时间需要单独处理
		if (StringUtils.isNotBlank(sell_date_start)) {
			select2 += " and t.add_day >= ? ";
			array.add(sell_date_start);
		}
		if (StringUtils.isNotBlank(sell_date_end)) {
			select2 += " and t.add_day <= ? ";
			array.add(sell_date_end);
		}
		select2 += group;
		select2 += order;
		select2 += " ) aa ";
		String sql = select1 + select2;

		// 同比、环比判断
		if (contrast_type == 1) {
			sql += " left join ( ";
			sql += select_tb;
			sql += " from V_A_DETAILS_OF_SALE_AND_BUY t ";
			sql += where;
			if (null != array_temp && array_temp.size() > 0) {
				for (String temp : array_temp) {
					array.add(temp);
				}
			}
			// 因为，同比、环比，时间需要特殊处理
			// 同比判断，重新定义时间范围
			if (StringUtils.isNotBlank(sell_date_start)) {

				sql += " and t.add_day >= ? ";
				String sell_date_start_ = String.valueOf((Integer.valueOf(sell_date_start.substring(0, 4)) - 1))
						+ sell_date_start.substring(4, 10);
				array.add(sell_date_start_);
			}
			if (StringUtils.isNotBlank(sell_date_end)) {
				sql += " and t.add_day <= ? ";
				String sell_date_end_ = String.valueOf((Integer.valueOf(sell_date_end.substring(0, 4)) - 1))
						+ sell_date_end.substring(4, 10);
				array.add(sell_date_end_);
			}
			//

			sql += group;
			sql += " ) bb ";
			sql += left_join_on_tb;
		} else if (contrast_type == 2) {
			sql += " left join ( ";
			sql += select_hb;
			sql += " from V_A_DETAILS_OF_SALE_AND_BUY t ";
			sql += where;
			if (null != array_temp && array_temp.size() > 0) {
				for (String temp : array_temp) {
					array.add(temp);
				}
			}
			// 环比判断，重新定义时间范围
			if (StringUtils.isNotBlank(sell_date_start)) {

				sql += " and t.add_day >= ? ";
				String sell_date_start_ = "";
				if (StringUtils.isNotBlank(time_flag) && "bb_1".equals(time_flag)) {
					// 年度环比
					sell_date_start_ = String.valueOf((Integer.valueOf(sell_date_start.substring(0, 4)) - 1))
							+ sell_date_start.substring(4, 10);
				} else if (StringUtils.isNotBlank(time_flag) && "bb_2".equals(time_flag)) {
					// 季度环比
					// 2013-01-01
					String month = sell_date_start.substring(5, 7);

					if ("01".equals(month) || "02".equals(month) || "03".equals(month)) {
						// 1季度环比时间：上年度4季度开始时间
						sell_date_start_ = String.valueOf((Integer.valueOf(sell_date_start.substring(0, 4)) - 1))
								+ "-10-01";
					} else if ("04".equals(month) || "05".equals(month) || "06".equals(month)) {
						// 2季度环比时间：当前年度1季度开始时间
						sell_date_start_ = sell_date_start.substring(0, 4) + "-01-01";
					} else if ("07".equals(month) || "08".equals(month) || "09".equals(month)) {
						// 3季度环比时间：当前年度2季度开始时间
						sell_date_start_ = sell_date_start.substring(0, 4) + "-04-01";
					} else if ("10".equals(month) || "11".equals(month) || "12".equals(month)) {
						// 4季度环比时间：当前年度3季度开始时间
						sell_date_start_ = sell_date_start.substring(0, 4) + "-07-01";
					}
				} else if (StringUtils.isNotBlank(time_flag) && "bb_3".equals(time_flag)) {
					// 月度环比
					String month = sell_date_start.substring(5, 7);
					switch (Integer.valueOf(month)) {
					case 1:
						sell_date_start_ = String.valueOf((Integer.valueOf(sell_date_start.substring(0, 4)) - 1))
								+ "-12-01";
						break;
					case 2:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-01-01";
						break;
					case 3:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-02-01";
						break;
					case 4:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-03-01";
						break;
					case 5:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-04-01";
						break;
					case 6:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-05-01";
						break;
					case 7:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-06-01";
						break;
					case 8:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-07-01";
						break;
					case 9:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-08-01";
						break;
					case 10:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-09-01";
						break;
					case 11:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-10-01";
						break;
					case 12:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-11-01";
						break;
					default:
						break;
					}
				} else if (StringUtils.isNotBlank(time_flag) && "bb_4".equals(time_flag)) {
					// 周环比
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(df.parse(sell_date_start));
					calendar.add(Calendar.DATE, -14); // 当前时间-14，得到环比时间的开始时间，页面的时间选择不一定是一周的开始，所以，这里不是-7
					sell_date_start_ = df.format(calendar.getTime());
				} else if (StringUtils.isNotBlank(time_flag) && "bb_5".equals(time_flag)) {
					// 日环比
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(df.parse(sell_date_start));
					calendar.add(Calendar.DATE, -1); // 当前时间-1，得到环比时间的开始时间
					sell_date_start_ = df.format(calendar.getTime());
				}

				array.add(sell_date_start_);
			}
			if (StringUtils.isNotBlank(sell_date_end)) {
				sql += " and t.add_day <= ? ";
				String sell_date_end_ = "";
				if (StringUtils.isNotBlank(time_flag) && "bb_1".equals(time_flag)) {
					// 年度环比
					sell_date_end_ = String.valueOf((Integer.valueOf(sell_date_end.substring(0, 4)) - 1))
							+ sell_date_end.substring(4, 10);
				} else if (StringUtils.isNotBlank(time_flag) && "bb_2".equals(time_flag)) {
					// 季度环比
					// 2013-01-01
					String month = sell_date_end.substring(5, 7);

					if ("01".equals(month) || "02".equals(month) || "03".equals(month)) {
						// 1季度环比时间：上年度4季度结束时间
						sell_date_end_ = String.valueOf((Integer.valueOf(sell_date_end.substring(0, 4)) - 1))
								+ "-12-31";
					} else if ("04".equals(month) || "05".equals(month) || "06".equals(month)) {
						// 2季度环比时间：当前年度1季度结束时间
						sell_date_end_ = sell_date_end.substring(0, 4) + "-03-31";
					} else if ("07".equals(month) || "08".equals(month) || "09".equals(month)) {
						// 3季度环比时间：当前年度2季度结束时间
						sell_date_end_ = sell_date_end.substring(0, 4) + "-06-30";
					} else if ("10".equals(month) || "11".equals(month) || "12".equals(month)) {
						// 4季度环比时间：当前年度3季度结束时间
						sell_date_end_ = sell_date_end.substring(0, 4) + "-09-30";
					}
				} else if (StringUtils.isNotBlank(time_flag) && "bb_3".equals(time_flag)) {
					// 月度环比
					String month = sell_date_end.substring(5, 7);
					switch (Integer.valueOf(month)) {
					case 1:
						sell_date_end_ = String.valueOf((Integer.valueOf(sell_date_end.substring(0, 4)) - 1))
								+ "-12-31";
						break;
					case 2:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-01-31";
						break;
					case 3:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-02-28";
						break;
					case 4:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-03-31";
						break;
					case 5:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-04-30";
						break;
					case 6:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-05-31";
						break;
					case 7:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-06-30";
						break;
					case 8:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-07-31";
						break;
					case 9:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-08-31";
						break;
					case 10:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-09-30";
						break;
					case 11:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-10-31";
						break;
					case 12:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-11-30";
						break;
					default:
						break;
					}
				} else if (StringUtils.isNotBlank(time_flag) && "bb_4".equals(time_flag)) {
					// 周环比
					sell_date_end_ = sell_date_end;// 页面的时间选择不一定是一周的开始，所以，sell_date_end也可能在上周的范围内
				} else if (StringUtils.isNotBlank(time_flag) && "bb_5".equals(time_flag)) {
					// 日环比
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(df.parse(sell_date_end));
					calendar.add(Calendar.DATE, -1); // 当前时间-1，得到环比时间的结束时间
					sell_date_end_ = df.format(calendar.getTime());
				}
				array.add(sell_date_end_);
			}
			//
			sql += group;
			sql += " ) cc ";
			sql += left_join_on_hb;
		} else if (contrast_type == 3) {
			sql += " left join( ";
			sql += select_tb;
			sql += " from V_A_DETAILS_OF_SALE_AND_BUY t ";
			sql += where;
			if (null != array_temp && array_temp.size() > 0) {
				for (String temp : array_temp) {
					array.add(temp);
				}
			}

			// 同比判断，重新定义时间范围
			if (StringUtils.isNotBlank(sell_date_start)) {

				sql += " and t.add_day >= ? ";
				String sell_date_start_ = String.valueOf((Integer.valueOf(sell_date_start.substring(0, 4)) - 1))
						+ sell_date_start.substring(4, 10);
				array.add(sell_date_start_);
			}
			if (StringUtils.isNotBlank(sell_date_end)) {
				sql += " and t.add_day <= ? ";
				String sell_date_end_ = String.valueOf((Integer.valueOf(sell_date_end.substring(0, 4)) - 1))
						+ sell_date_end.substring(4, 10);
				array.add(sell_date_end_);
			}
			//

			sql += group;
			sql += " ) bb ";
			sql += left_join_on_tb;

			sql += " left join ( ";
			sql += select_hb;
			sql += " from V_A_DETAILS_OF_SALE_AND_BUY t ";
			sql += where;
			if (null != array_temp && array_temp.size() > 0) {
				for (String temp : array_temp) {
					array.add(temp);
				}
			}

			// 环比判断，重新定义时间范围
			if (StringUtils.isNotBlank(sell_date_start)) {

				sql += " and t.add_day >= ? ";
				String sell_date_start_ = "";
				if (StringUtils.isNotBlank(time_flag) && "bb_1".equals(time_flag)) {
					// 年度环比
					sell_date_start_ = String.valueOf((Integer.valueOf(sell_date_start.substring(0, 4)) - 1))
							+ sell_date_start.substring(4, 10);
				} else if (StringUtils.isNotBlank(time_flag) && "bb_2".equals(time_flag)) {
					// 季度环比
					// 2013-01-01
					String month = sell_date_start.substring(5, 7);

					if ("01".equals(month) || "02".equals(month) || "03".equals(month)) {
						// 1季度环比时间：上年度4季度开始时间
						sell_date_start_ = String.valueOf((Integer.valueOf(sell_date_start.substring(0, 4)) - 1))
								+ "-10-01";
					} else if ("04".equals(month) || "05".equals(month) || "06".equals(month)) {
						// 2季度环比时间：当前年度1季度开始时间
						sell_date_start_ = sell_date_start.substring(0, 4) + "-01-01";
					} else if ("07".equals(month) || "08".equals(month) || "09".equals(month)) {
						// 3季度环比时间：当前年度2季度开始时间
						sell_date_start_ = sell_date_start.substring(0, 4) + "-04-01";
					} else if ("10".equals(month) || "11".equals(month) || "12".equals(month)) {
						// 4季度环比时间：当前年度3季度开始时间
						sell_date_start_ = sell_date_start.substring(0, 4) + "-07-01";
					}
				} else if (StringUtils.isNotBlank(time_flag) && "bb_3".equals(time_flag)) {
					// 月度环比
					String month = sell_date_start.substring(5, 7);
					switch (Integer.valueOf(month)) {
					case 1:
						sell_date_start_ = String.valueOf((Integer.valueOf(sell_date_start.substring(0, 4)) - 1))
								+ "-12-01";
						break;
					case 2:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-01-01";
						break;
					case 3:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-02-01";
						break;
					case 4:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-03-01";
						break;
					case 5:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-04-01";
						break;
					case 6:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-05-01";
						break;
					case 7:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-06-01";
						break;
					case 8:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-07-01";
						break;
					case 9:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-08-01";
						break;
					case 10:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-09-01";
						break;
					case 11:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-10-01";
						break;
					case 12:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-11-01";
						break;
					default:
						break;
					}
				} else if (StringUtils.isNotBlank(time_flag) && "bb_4".equals(time_flag)) {
					// 周环比
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(df.parse(sell_date_start));
					calendar.add(Calendar.DATE, -14); // 当前时间-14，得到环比时间的开始时间，页面的时间选择不一定是一周的开始，所以，这里不是-7
					sell_date_start_ = df.format(calendar.getTime());
				} else if (StringUtils.isNotBlank(time_flag) && "bb_5".equals(time_flag)) {
					// 日环比
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(df.parse(sell_date_start));
					calendar.add(Calendar.DATE, -1); // 当前时间-1，得到环比时间的开始时间
					sell_date_start_ = df.format(calendar.getTime());
				}

				array.add(sell_date_start_);
			}
			if (StringUtils.isNotBlank(sell_date_end)) {
				sql += " and t.add_day <= ? ";
				String sell_date_end_ = "";
				if (StringUtils.isNotBlank(time_flag) && "bb_1".equals(time_flag)) {
					// 年度环比
					sell_date_end_ = String.valueOf((Integer.valueOf(sell_date_end.substring(0, 4)) - 1))
							+ sell_date_end.substring(4, 10);
				} else if (StringUtils.isNotBlank(time_flag) && "bb_2".equals(time_flag)) {
					// 季度环比
					// 2013-01-01
					String month = sell_date_end.substring(5, 7);

					if ("01".equals(month) || "02".equals(month) || "03".equals(month)) {
						// 1季度环比时间：上年度4季度结束时间
						sell_date_end_ = String.valueOf((Integer.valueOf(sell_date_end.substring(0, 4)) - 1))
								+ "-12-31";
					} else if ("04".equals(month) || "05".equals(month) || "06".equals(month)) {
						// 2季度环比时间：当前年度1季度结束时间
						sell_date_end_ = sell_date_end.substring(0, 4) + "-03-31";
					} else if ("07".equals(month) || "08".equals(month) || "09".equals(month)) {
						// 3季度环比时间：当前年度2季度结束时间
						sell_date_end_ = sell_date_end.substring(0, 4) + "-06-30";
					} else if ("10".equals(month) || "11".equals(month) || "12".equals(month)) {
						// 4季度环比时间：当前年度3季度结束时间
						sell_date_end_ = sell_date_end.substring(0, 4) + "-09-30";
					}
				} else if (StringUtils.isNotBlank(time_flag) && "bb_3".equals(time_flag)) {
					// 月度环比
					String month = sell_date_end.substring(5, 7);
					switch (Integer.valueOf(month)) {
					case 1:
						sell_date_end_ = String.valueOf((Integer.valueOf(sell_date_end.substring(0, 4)) - 1))
								+ "-12-31";
						break;
					case 2:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-01-31";
						break;
					case 3:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-02-28";
						break;
					case 4:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-03-31";
						break;
					case 5:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-04-30";
						break;
					case 6:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-05-31";
						break;
					case 7:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-06-30";
						break;
					case 8:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-07-31";
						break;
					case 9:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-08-31";
						break;
					case 10:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-09-30";
						break;
					case 11:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-10-31";
						break;
					case 12:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-11-30";
						break;
					default:
						break;
					}
				} else if (StringUtils.isNotBlank(time_flag) && "bb_4".equals(time_flag)) {
					// 周环比
					sell_date_end_ = sell_date_end;// 页面的时间选择不一定是一周的开始，所以，sell_date_end也可能在上周的范围内
				} else if (StringUtils.isNotBlank(time_flag) && "bb_5".equals(time_flag)) {
					// 日环比
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(df.parse(sell_date_end));
					calendar.add(Calendar.DATE, -1); // 当前时间-1，得到环比时间的结束时间
					sell_date_end_ = df.format(calendar.getTime());
				}
				array.add(sell_date_end_);
			}
			//

			sql += group;
			sql += " ) cc ";
			sql += left_join_on_hb;
		}
		log.info("**********sql:" + sql);
		if (null != array && array.size() > 0) {
			String array_string = "";
			for (int i = 0; i < array.size(); i++) {
				array_string += array.get(i) + ",";
			}
			log.info("**********array:" + array.size());
			log.info("**********array_string:" + array_string);
		}

		List<?> entityList = super.getFacade().getBaseReportService().getBaseReportForBindToArray(sql, array.toArray());
		b.setGroup_by_field(group_by_field);
		b.setShow_field(show_field);
		b.setShow_num(show_num);
		b.setGroup_flag_string(group_flag_string);
		b.setShow_field_string(show_field_string);
		b.setEntityList(entityList);
		return b;
	}

	public SelectBean getKonkaR3OrSellReportForMul(SelectBean b) throws ParseException {

		String sell_date_start = b.getSell_date_start();
		String sell_date_end = b.getSell_date_end();
		String dept_id = b.getDept_id();
		String l4_dept_id = b.getL4_dept_id();
		String l5_dept_id = b.getL5_dept_id();
		String ywy_user_name = b.getYwy_user_name();
		String customer_name = b.getCustomer_name();
		String size_sec = b.getSize_sec();
		String model_name = b.getModel_name();
		String label_db = b.getLabel_db();
		String label_int = b.getLabel_int();

		String[] group_by_field = b.getGroup_by_field();
		String[] show_field = b.getShow_field();
		String[] contrast = b.getContrast();

		String select1 = " select ";// 查询结果数据项（包含同比、环比等）
		String select2 = " select ";// 查询结果数据项
		String group = " group by ";// 分组
		String order = " order by ";// 排序

		String select_tb = " select "; // 同比sql
		String select_hb = " select ";// 环比sql
		String left_join_on_tb = " on 1 = 1 "; // 同比
		String left_join_on_hb = " on 1 = 1 "; // 环比
		int contrast_type = 0;
		if (null != contrast && contrast.length > 0) {
			if (contrast.length == 1 && "1".equals(contrast[0])) {
				// 同比
				contrast_type = 1;
			} else if (contrast.length == 1 && "2".equals(contrast[0])) {
				// 环比
				contrast_type = 2;
			} else if (contrast.length == 2 && "1".equals(contrast[0]) && "2".equals(contrast[1])) {
				// 同比、环比
				contrast_type = 3;

			}
		}
		// aa_1:分公司 , aa_2:经营部, aa_3:办事处 , aa_4:业务员 , aa_5:客户
		// bb_1:年度 , bb_2:季度 , bb_3:月度 去除周，日； bb_4:周 , bb_5:日
		// cc_1:尺寸规格, cc_2:型号 , cc_3:是否大板, cc_4:是否智能
		// dd_1:客户分类大类, dd_2:客户分类明细

		String where = " where 1 = 1 and t.model_name is not null ";

		String group_flag_string = ",";
		int show_num = 0;
		String time_flag = "";// 用于环比的时间范围定义
		if (null != group_by_field && group_by_field.length > 0) {
			group_by_field = selectSortForString(group_by_field);
			show_num += group_by_field.length;
			for (int i = 0; i < group_by_field.length; i++) {

				// 分组织架构
				if ("aa_1".equals(group_by_field[i])) {
					select1 += " aa.dept_name,";
					select2 += " t.dept_id,t.dept_name,";
					group += " t.dept_name,t.dept_id,";
					order += " t.dept_name asc,t.dept_id asc,";
					where += " and t.dept_id is not null ";

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.dept_id,t.dept_name,";
						left_join_on_tb += " and aa.dept_id = bb.dept_id ";
					} else if (contrast_type == 2) {
						select_hb += " t.dept_id,t.dept_name,";
						left_join_on_hb += " and aa.dept_id = cc.dept_id ";
					} else if (contrast_type == 3) {
						select_tb += " t.dept_id,t.dept_name,";
						select_hb += " t.dept_id,t.dept_name,";
						left_join_on_tb += " and aa.dept_id = bb.dept_id ";
						left_join_on_hb += " and aa.dept_id = cc.dept_id ";
					}

				}
				if ("aa_2".equals(group_by_field[i])) {
					select1 += " aa.l4_dept_name,";
					select2 += " t.l4_dept_id,t.l4_dept_name,";
					group += " t.l4_dept_name,t.l4_dept_id,";
					order += " t.l4_dept_name asc,t.l4_dept_id asc,";
					where += " and t.l4_dept_id is not null ";

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.l4_dept_id,t.l4_dept_name,";
						left_join_on_tb += " and aa.l4_dept_id = bb.l4_dept_id ";
					} else if (contrast_type == 2) {
						select_hb += " t.l4_dept_id,t.l4_dept_name,";
						left_join_on_hb += " and aa.l4_dept_id = cc.l4_dept_id ";
					} else if (contrast_type == 3) {
						select_tb += " t.l4_dept_id,t.l4_dept_name,";
						select_hb += " t.l4_dept_id,t.l4_dept_name,";
						left_join_on_tb += " and aa.l4_dept_id = bb.l4_dept_id ";
						left_join_on_hb += " and aa.l4_dept_id = cc.l4_dept_id ";
					}
				}
				if ("aa_3".equals(group_by_field[i])) {
					select1 += " aa.l5_dept_name,";
					select2 += " t.l5_dept_id,t.l5_dept_name,";
					group += " t.l5_dept_name,t.l5_dept_id,";
					order += " t.l5_dept_name asc,t.l5_dept_id asc,";
					where += " and t.l5_dept_id is not null ";

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.l5_dept_id,t.l5_dept_name,";
						left_join_on_tb += " and aa.l5_dept_id = bb.l5_dept_id ";
					} else if (contrast_type == 2) {
						select_hb += " t.l5_dept_id,t.l5_dept_name,";
						left_join_on_hb += " and aa.l5_dept_id = cc.l5_dept_id ";
					} else if (contrast_type == 3) {
						select_tb += " t.l5_dept_id,t.l5_dept_name,";
						select_hb += " t.l5_dept_id,t.l5_dept_name,";
						left_join_on_tb += " and aa.l5_dept_id = bb.l5_dept_id ";
						left_join_on_hb += " and aa.l5_dept_id = cc.l5_dept_id ";
					}
				}
				if ("aa_4".equals(group_by_field[i])) {
					select1 += " aa.ywy_user_name,";
					select2 += " t.user_id,t.ywy_user_name,";
					group += " t.ywy_user_name,t.user_id,";
					order += " t.ywy_user_name asc,t.user_id asc,";
					where += " and t.user_id is not null ";

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.user_id,t.ywy_user_name,";
						left_join_on_tb += " and aa.user_id = bb.user_id ";
					} else if (contrast_type == 2) {
						select_hb += " t.user_id,t.ywy_user_name,";
						left_join_on_hb += " and aa.user_id = cc.user_id ";
					} else if (contrast_type == 3) {
						select_tb += " t.user_id,t.ywy_user_name,";
						select_hb += " t.user_id,t.ywy_user_name,";
						left_join_on_tb += " and aa.user_id = bb.user_id ";
						left_join_on_hb += " and aa.user_id = cc.user_id ";
					}
				}
				if ("aa_5".equals(group_by_field[i])) {
					select1 += " aa.customer_name,";
					select2 += " t.konka_r3_id,t.customer_name,";
					group += " t.customer_name,t.konka_r3_id,";
					order += " t.customer_name asc,t.konka_r3_id asc,";
					where += " and t.konka_r3_id is not null ";

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.konka_r3_id,t.customer_name,";
						left_join_on_tb += " and aa.konka_r3_id = bb.konka_r3_id ";
					} else if (contrast_type == 2) {
						select_hb += " t.konka_r3_id,t.customer_name,";
						left_join_on_hb += " and aa.konka_r3_id = cc.konka_r3_id ";
					} else if (contrast_type == 3) {
						select_tb += " t.konka_r3_id,t.customer_name,";
						select_hb += " t.konka_r3_id,t.customer_name,";
						left_join_on_tb += " and aa.konka_r3_id = bb.konka_r3_id ";
						left_join_on_hb += " and aa.konka_r3_id = cc.konka_r3_id ";
					}
				}

				// 时间维度
				if ("bb_1".equals(group_by_field[i])) {
					select1 += " aa.y,";
					select2 += " t.y,";
					group += " t.y,";
					order += " t.y asc,";

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.y,";
						left_join_on_tb += " and aa.y-1 = bb.y ";
					} else if (contrast_type == 2) {
						select_hb += " t.y,";
						left_join_on_hb += " and aa.y-1 = cc.y ";
					} else if (contrast_type == 3) {
						select_tb += " t.y,";
						select_hb += " t.y,";
						left_join_on_tb += " and aa.y-1 = bb.y ";
						left_join_on_hb += " and aa.y-1 = cc.y ";
					}
					time_flag = "bb_1";// 用于环比的时间范围定义
				}
				if ("bb_2".equals(group_by_field[i])) {
					select1 += " aa.y,aa.q,";
					select2 += " t.y,t.q,";
					group += " t.y,t.q,";
					order += " t.y asc,t.q asc,";

					group_flag_string += "bb_1" + ",";
					show_num += 1;

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.y,t.q,";
						left_join_on_tb += " and aa.y-1 = bb.y and aa.q = bb.q ";
					} else if (contrast_type == 2) {
						select_hb += " t.y,t.q,";
						left_join_on_hb += " and ((aa.q = '1' and aa.y - 1 = cc.y and cc.q = '4') or (aa.q != '1' and aa.y = cc.y and aa.q - 1 = cc.q)) ";
					} else if (contrast_type == 3) {
						select_tb += " t.y,t.q,";
						select_hb += " t.y,t.q,";
						left_join_on_tb += " and aa.y-1 = bb.y and aa.q = bb.q ";
						left_join_on_hb += " and ((aa.q = '1' and aa.y - 1 = cc.y and cc.q = '4') or (aa.q != '1' and aa.y = cc.y and aa.q - 1 = cc.q)) ";
					}

					time_flag = "bb_2";// 用于环比的时间范围定义
				}
				if ("bb_3".equals(group_by_field[i])) {
					select1 += " aa.y,aa.m,";
					select2 += " t.y,t.m,";
					group += " t.y,t.m,";
					order += " t.y asc,t.m asc,";

					group_flag_string += "bb_1" + ",";
					show_num += 1;

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.y,t.m,";
						left_join_on_tb += " and aa.y-1 = bb.y and aa.m = bb.m ";
					} else if (contrast_type == 2) {
						select_hb += " t.y,t.m,";
						left_join_on_hb += " and ((aa.m = '01' and aa.y - 1 = cc.y and cc.m = '12') or (aa.m != '01' and aa.y = cc.y and to_number(aa.m) - 1 = to_number(cc.m))) ";
					} else if (contrast_type == 3) {
						select_tb += " t.y,t.m,";
						select_hb += " t.y,t.m,";
						left_join_on_tb += " and aa.y-1 = bb.y and aa.m = bb.m ";
						left_join_on_hb += " and ((aa.m = '01' and aa.y - 1 = cc.y and cc.m = '12') or (aa.m != '01' and aa.y = cc.y and to_number(aa.m) - 1 = to_number(cc.m))) ";
					}

					time_flag = "bb_3";// 用于环比的时间范围定义
				}

				// 产品维度
				if ("cc_1".equals(group_by_field[i])) {
					select1 += " decode(aa.size_sec,0,'32寸及以下',1,'32-36寸',2,'37-39寸',3,'40-45寸',4,'46-50寸',5,'51-59寸',6,'60-69寸',7,'70寸及以上',-1,'其他','未标识') as size_sec,";
					select2 += " t.size_sec,";
					group += " t.size_sec,";
					order += " t.size_sec asc,";

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.size_sec,";
						left_join_on_tb += " and aa.size_sec = bb.size_sec ";
					} else if (contrast_type == 2) {
						select_hb += " t.size_sec,";
						left_join_on_hb += " and aa.size_sec = cc.size_sec ";
					} else if (contrast_type == 3) {
						select_tb += " t.size_sec,";
						select_hb += " t.size_sec,";
						left_join_on_tb += " and aa.size_sec = bb.size_sec ";
						left_join_on_hb += " and aa.size_sec = cc.size_sec ";
					}
				}
				if ("cc_2".equals(group_by_field[i])) {
					select1 += " aa.model_name,";
					select2 += " t.model_name,";
					group += " t.model_name,";
					order += " t.model_name asc,";

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.model_name,";
						left_join_on_tb += " and aa.model_name = bb.model_name ";
					} else if (contrast_type == 2) {
						select_hb += " t.model_name,";
						left_join_on_hb += " and aa.model_name = cc.model_name ";
					} else if (contrast_type == 3) {
						select_tb += " t.model_name,";
						select_hb += " t.model_name,";
						left_join_on_tb += " and aa.model_name = bb.model_name ";
						left_join_on_hb += " and aa.model_name = cc.model_name ";
					}
				}
				if ("cc_3".equals(group_by_field[i])) {
					select1 += " decode(aa.label_db,0,'非大板',1,'大板','未标识'),";
					select2 += " t.label_db,";
					group += " t.label_db,";
					order += " t.label_db asc,";

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.label_db,";
						left_join_on_tb += " and aa.label_db = bb.label_db ";
					} else if (contrast_type == 2) {
						select_hb += " t.label_db,";
						left_join_on_hb += " and aa.label_db = cc.label_db ";
					} else if (contrast_type == 3) {
						select_tb += " t.label_db,";
						select_hb += " t.label_db,";
						left_join_on_tb += " and aa.label_db = bb.label_db ";
						left_join_on_hb += " and aa.label_db = cc.label_db ";
					}
				}
				if ("cc_4".equals(group_by_field[i])) {
					select1 += " decode(aa.label_int,0,'非智能',1,'智能','未标识'),";
					select2 += " t.label_int,";
					group += " t.label_int,";
					order += " t.label_int asc,";

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.label_int,";
						left_join_on_tb += " and aa.label_int = bb.label_int ";
					} else if (contrast_type == 2) {
						select_hb += " t.label_int,";
						left_join_on_hb += " and aa.label_int = cc.label_int ";
					} else if (contrast_type == 3) {
						select_tb += " t.label_int,";
						select_hb += " t.label_int,";
						left_join_on_tb += " and aa.label_int = bb.label_int ";
						left_join_on_hb += " and aa.label_int = cc.label_int ";
					}
				}

				// 客户分类
				if ("dd_1".equals(group_by_field[i])) {
					select1 += " aa.par_customer_type_name,";
					select2 += " t.par_customer_type,t.par_customer_type_name,";
					group += " t.par_customer_type_name,t.par_customer_type,";
					order += " t.par_customer_type_name asc,t.par_customer_type asc,";
					where += " and t.par_customer_type is not null ";

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.par_customer_type,t.par_customer_type_name,";
						left_join_on_tb += " and aa.par_customer_type = bb.par_customer_type ";
					} else if (contrast_type == 2) {
						select_hb += " t.par_customer_type,t.par_customer_type_name,";
						left_join_on_hb += " and aa.par_customer_type = cc.par_customer_type ";
					} else if (contrast_type == 3) {
						select_tb += " t.par_customer_type,t.par_customer_type_name,";
						select_hb += " t.par_customer_type,t.par_customer_type_name,";
						left_join_on_tb += " and aa.par_customer_type = bb.par_customer_type ";
						left_join_on_hb += " and aa.par_customer_type = cc.par_customer_type ";
					}
				}
				if ("dd_2".equals(group_by_field[i])) {
					select1 += " aa.customer_type_name,";
					select2 += " t.customer_type,t.customer_type_name,";
					group += " t.customer_type_name,t.customer_type,";
					order += " t.customer_type_name asc,t.customer_type asc,";
					where += " and t.customer_type is not null ";

					// 同比、环比判断
					if (contrast_type == 1) {
						select_tb += " t.customer_type,t.customer_type_name,";
						left_join_on_tb += " and aa.customer_type = bb.customer_type ";
					} else if (contrast_type == 2) {
						select_hb += " t.customer_type,t.customer_type_name,";
						left_join_on_hb += " and aa.customer_type = cc.customer_type ";
					} else if (contrast_type == 3) {
						select_tb += " t.customer_type,t.customer_type_name,";
						select_hb += " t.customer_type,t.customer_type_name,";
						left_join_on_tb += " and aa.customer_type = bb.customer_type ";
						left_join_on_hb += " and aa.customer_type = cc.customer_type ";
					}
				}

				group_flag_string += group_by_field[i] + ",";

			}
		}
		// 10001:R3销售量, 10011:R3销售额, 10021:零售量, 10031:零售额, 10041:回款额, 10051:任务额, 10061:任务系数
		// 10071:回款任务完成度, 10081:销售任务完成度
		// 10111:平均单价, 10121:平均毛利, 10131:产品零售指导价, 10141:产品现款价
		String show_field_string = ",";
		if (null != show_field && show_field.length > 0) {
			show_field = selectSortForInteger(show_field);
			show_num += show_field.length;
			for (int i = 0; i < show_field.length; i++) {
				show_field_string += show_field[i] + ",";
				if ("10001".equals(show_field[i])) {
					select1 += " aa.total_counts_of_buy,";
					select2 += " value(sum(t.total_counts_of_buy),0) as total_counts_of_buy,";
					// 同比、环比判断
					if (contrast_type == 1) {
						select1 += " value(bb.total_counts_of_buy,0),";
						select_tb += " value(sum(t.total_counts_of_buy),0) as total_counts_of_buy,";
						show_field_string += "10002" + ",";// 增加页面环比显示的判断标识
						show_num += 1;// 显示list横向的循环此次
					} else if (contrast_type == 2) {
						select1 += " value(cc.total_counts_of_buy,0),";
						select_hb += " value(sum(t.total_counts_of_buy),0) as total_counts_of_buy,";
						show_field_string += "10003" + ",";// 增加页面环比显示的判断标识
						show_num += 1;// 显示list横向的循环此次
					} else if (contrast_type == 3) {
						select1 += " value(bb.total_counts_of_buy,0),value(cc.total_counts_of_buy,0),";
						select_tb += " value(sum(t.total_counts_of_buy),0) as total_counts_of_buy,";
						select_hb += " value(sum(t.total_counts_of_buy),0) as total_counts_of_buy,";
						show_field_string += "10002" + ",";// 增加页面环比显示的判断标识
						show_field_string += "10003" + ",";// 增加页面环比显示的判断标识
						show_num += 2;// 显示list横向的循环此次
					}
				}
				if ("10011".equals(show_field[i])) {
					select1 += " aa.total_money_of_buy,";
					select2 += " value(sum(t.total_money_of_buy),0) as total_money_of_buy,";
					// 同比、环比判断
					if (contrast_type == 1) {
						select1 += " value(bb.total_money_of_buy,0),";
						select_tb += " value(sum(t.total_money_of_buy),0) as total_money_of_buy,";
						show_field_string += "10012" + ",";// 增加页面环比显示的判断标识
						show_num += 1;// 显示list横向的循环此次
					} else if (contrast_type == 2) {
						select1 += " value(cc.total_money_of_buy,0),";
						select_hb += " value(sum(t.total_money_of_buy),0) as total_money_of_buy,";
						show_field_string += "10013" + ",";// 增加页面环比显示的判断标识
						show_num += 1;// 显示list横向的循环此次
					} else if (contrast_type == 3) {
						select1 += " value(bb.total_money_of_buy,0),value(cc.total_money_of_buy,0),";
						select_tb += " value(sum(t.total_money_of_buy),0) as total_money_of_buy,";
						select_hb += " value(sum(t.total_money_of_buy),0) as total_money_of_buy,";
						show_field_string += "10012" + ",";// 增加页面环比显示的判断标识
						show_field_string += "10013" + ",";// 增加页面环比显示的判断标识
						show_num += 2;// 显示list横向的循环此次
					}
				}
				if ("10021".equals(show_field[i])) {
					select1 += " aa.total_counts_of_sale,";
					select2 += " value(sum(t.total_counts_of_sale),0) as total_counts_of_sale,";
					// 同比、环比判断
					if (contrast_type == 1) {
						select1 += " value(bb.total_counts_of_sale,0),";
						select_tb += " value(sum(t.total_counts_of_sale),0) as total_counts_of_sale,";
						show_field_string += "10022" + ",";// 增加页面环比显示的判断标识
						show_num += 1;// 显示list横向的循环此次
					} else if (contrast_type == 2) {
						select1 += " value(cc.total_counts_of_sale,0),";
						select_hb += " value(sum(t.total_counts_of_sale),0) as total_counts_of_sale,";
						show_field_string += "10023" + ",";// 增加页面环比显示的判断标识
						show_num += 1;// 显示list横向的循环此次
					} else if (contrast_type == 3) {
						select1 += " value(bb.total_counts_of_sale,0),value(cc.total_counts_of_sale,0),";
						select_tb += " value(sum(t.total_counts_of_sale),0) as total_counts_of_sale,";
						select_hb += " value(sum(t.total_counts_of_sale),0) as total_counts_of_sale,";
						show_field_string += "10022" + ",";// 增加页面环比显示的判断标识
						show_field_string += "10023" + ",";// 增加页面环比显示的判断标识
						show_num += 2;// 显示list横向的循环此次
					}
				}
				if ("10031".equals(show_field[i])) {
					select1 += " aa.total_money_of_sale,";
					select2 += " value(sum(t.total_money_of_sale),0) as total_money_of_sale,";
					// 同比、环比判断
					if (contrast_type == 1) {
						select1 += " value(bb.total_money_of_sale,0),";
						select_tb += " value(sum(t.total_money_of_sale),0) as total_money_of_sale,";
						show_field_string += "10032" + ",";// 增加页面环比显示的判断标识
						show_num += 1;// 显示list横向的循环此次
					} else if (contrast_type == 2) {
						select1 += " value(cc.total_money_of_sale,0),";
						select_hb += " value(sum(t.total_money_of_sale),0) as total_money_of_sale,";
						show_field_string += "10033" + ",";// 增加页面环比显示的判断标识
						show_num += 1;// 显示list横向的循环此次
					} else if (contrast_type == 3) {
						select1 += " value(bb.total_money_of_sale,0),value(cc.total_money_of_sale,0),";
						select_tb += " value(sum(t.total_money_of_sale),0) as total_money_of_sale,";
						select_hb += " value(sum(t.total_money_of_sale),0) as total_money_of_sale,";
						show_field_string += "10032" + ",";// 增加页面环比显示的判断标识
						show_field_string += "10033" + ",";// 增加页面环比显示的判断标识
						show_num += 2;// 显示list横向的循环此次
					}
				}
				if ("10041".equals(show_field[i])) {
					select1 += " aa.cur_month_real_backmoney,";
					select2 += " value(sum(t.cur_month_real_backmoney),0) as cur_month_real_backmoney,";
					// 同比、环比判断
					if (contrast_type == 1) {
						select1 += " value(bb.cur_month_real_backmoney,0),";
						select_tb += " value(sum(t.cur_month_real_backmoney),0) as cur_month_real_backmoney,";
						show_field_string += "10042" + ",";// 增加页面环比显示的判断标识
						show_num += 1;// 显示list横向的循环此次
					} else if (contrast_type == 2) {
						select1 += " value(cc.cur_month_real_backmoney,0),";
						select_hb += " value(sum(t.cur_month_real_backmoney),0) as cur_month_real_backmoney,";
						show_field_string += "10043" + ",";// 增加页面环比显示的判断标识
						show_num += 1;// 显示list横向的循环此次
					} else if (contrast_type == 3) {
						select1 += " value(bb.cur_month_real_backmoney,0),value(cc.cur_month_real_backmoney,0),";
						select_tb += " value(sum(t.cur_month_real_backmoney),0) as cur_month_real_backmoney,";
						select_hb += " value(sum(t.cur_month_real_backmoney),0) as cur_month_real_backmoney,";
						show_field_string += "10042" + ",";// 增加页面环比显示的判断标识
						show_field_string += "10043" + ",";// 增加页面环比显示的判断标识
						show_num += 2;// 显示list横向的循环此次
					}
				}

				// ---------------------//任务额,任务系数,回款任务完成度,销售任务完成度 在视图中的计算有问题
				// 任务额,任务系数直接到经营部，分公司的任务额,任务系数由所属经营部的总计，不能细化到客户，产品
				// 任务额,任务系数的数据根据经营部，分公司可能有多条，用max取值? 月份可以，但是年度、季度呢，如何累计任务额？
				if ("10051".equals(show_field[i])) {
					if ("bb_1".equals(time_flag)) {// 年度
						select1 += " aa.cur_money_of_year_task,";
						select2 += " value(max(t.cur_money_of_year_task),0) as cur_money_of_year_task,";
						// 同比、环比判断
						if (contrast_type == 1) {
							select1 += " value(bb.cur_money_of_year_task,0),";
							select_tb += " value(max(t.cur_money_of_year_task),0) as cur_money_of_year_task,";
							show_field_string += "10052" + ",";
							show_num += 1;
						} else if (contrast_type == 2) {
							select1 += " value(cc.cur_money_of_year_task,0),";
							select_hb += " value(max(t.cur_money_of_year_task),0) as cur_money_of_year_task,";
							show_field_string += "10053" + ",";
							show_num += 1;
						} else if (contrast_type == 3) {
							select1 += "value(bb.cur_money_of_year_task,0),value(cc.cur_money_of_year_task,0),";
							select_tb += " value(max(t.cur_money_of_year_task),0) as cur_money_of_year_task,";
							select_hb += " value(max(t.cur_money_of_year_task),0) as cur_money_of_year_task,";
							show_field_string += "10052" + ",";
							show_field_string += "10053" + ",";
							show_num += 2;
						}
					} else if ("bb_2".equals(time_flag)) {// 季度
						select1 += " aa.cur_money_of_q_task,";
						select2 += " value(max(t.cur_money_of_q_task),0) as cur_money_of_q_task,";
						// 同比、环比判断
						if (contrast_type == 1) {
							select1 += " value(bb.cur_money_of_q_task,0),";
							select_tb += " value(max(t.cur_money_of_q_task),0) as cur_money_of_q_task,";
							show_field_string += "10052" + ",";
							show_num += 1;
						} else if (contrast_type == 2) {
							select1 += " value(cc.cur_money_of_q_task,0),";
							select_hb += " value(max(t.cur_money_of_q_task),0) as cur_money_of_q_task,";
							show_field_string += "10053" + ",";
							show_num += 1;
						} else if (contrast_type == 3) {
							select1 += "value(bb.cur_money_of_q_task,0),value(cc.cur_money_of_q_task,0),";
							select_tb += " value(max(t.cur_money_of_q_task),0) as cur_money_of_q_task,";
							select_hb += " value(max(t.cur_money_of_q_task),0) as cur_money_of_q_task,";
							show_field_string += "10052" + ",";
							show_field_string += "10053" + ",";
							show_num += 2;
						}
					} else if ("bb_3".equals(time_flag)) {// 月份
						select1 += " aa.cur_month_real_task_money,";
						select2 += " value(max(t.cur_month_real_task_money),0) as cur_month_real_task_money,";
						// 同比、环比判断
						if (contrast_type == 1) {
							select1 += " value(bb.cur_month_real_task_money,0),";
							select_tb += " value(max(t.cur_month_real_task_money),0) as cur_month_real_task_money,";
							show_field_string += "10052" + ",";
							show_num += 1;
						} else if (contrast_type == 2) {
							select1 += " value(cc.cur_month_real_task_money,0),";
							select_hb += " value(max(t.cur_month_real_task_money),0) as cur_month_real_task_money,";
							show_field_string += "10053" + ",";
							show_num += 1;
						} else if (contrast_type == 3) {
							select1 += "value(bb.cur_month_real_task_money,0),value(cc.cur_month_real_task_money,0),";
							select_tb += " value(max(t.cur_month_real_task_money),0) as cur_month_real_task_money,";
							select_hb += " value(max(t.cur_month_real_task_money),0) as cur_month_real_task_money,";
							show_field_string += "10052" + ",";
							show_field_string += "10053" + ",";
							show_num += 2;
						}
					}
				}
				if ("10061".equals(show_field[i])) {
					select1 += " aa.ratio_of_year,";
					select2 += " value(max(t.ratio_of_year),0) as ratio_of_year,";
					// 同比、环比判断
					if (contrast_type == 1) {
						select1 += " value(bb.ratio_of_year,0),";
						select_tb = " value(max(t.ratio_of_year),0) as ratio_of_year,";
						show_field_string += "10062" + ",";
						show_num += 1;
					} else if (contrast_type == 2) {
						select1 += " value(cc.ratio_of_year,0),";
						select_hb += " value(max(t.ratio_of_year),0) as ratio_of_year,";
						show_field_string += "10063" + ",";
						show_num += 1;
					} else if (contrast_type == 3) {
						select1 += "value(bb.ratio_of_year,0).value(cc.ratio_of_year,0),";
						select_tb = " value(max(t.ratio_of_year),0) as ratio_of_year,";
						select_hb += " value(max(t.ratio_of_year),0) as ratio_of_year,";
						show_field_string += "10062" + ",";
						show_field_string += "10063" + ",";
						show_num += 2;
					}
				}
				if ("10071".equals(show_field[i])) {
					if ("bb_1".equals(time_flag)) {// 年度
						select1 += " aa.cur_month_backtaskdone_percent,";
						select2 += " round(decode(value(max(t.cur_money_of_year_task),0),0,0,value(sum(t.cur_month_real_backmoney),0)*100/value(max(t.cur_money_of_year_task),0)),0) as cur_month_backtaskdone_percent,";
						// 同比、环比判断
						if (contrast_type == 1) {
							select1 += " value(bb.cur_month_backtaskdone_percent,0),";
							select_tb += " round(decode(value(max(t.cur_money_of_year_task),0),0,0,value(sum(t.cur_month_real_backmoney),0)*100/value(max(t.cur_money_of_year_task),0)),0) as cur_month_backtaskdone_percent,";
							show_field_string += "10072" + ",";
							show_num += 1;
						} else if (contrast_type == 2) {
							select1 += " value(cc.cur_month_backtaskdone_percent,0),";
							select_hb += " round(decode(value(max(t.cur_money_of_year_task),0),0,0,value(sum(t.cur_month_real_backmoney),0)*100/value(max(t.cur_money_of_year_task),0)),0) as cur_month_backtaskdone_percent,";
							show_field_string += "10073" + ",";
							show_num += 1;
						} else if (contrast_type == 3) {
							select1 += " value(bb.cur_month_backtaskdone_percent,0),value(cc.cur_month_backtaskdone_percent,0),";
							select_tb += " round(decode(value(max(t.cur_money_of_year_task),0),0,0,value(sum(t.cur_month_real_backmoney),0)*100/value(max(t.cur_money_of_year_task),0)),0) as cur_month_backtaskdone_percent,";
							select_hb += " round(decode(value(max(t.cur_money_of_year_task),0),0,0,value(sum(t.cur_month_real_backmoney),0)*100/value(max(t.cur_money_of_year_task),0)),0) as cur_month_backtaskdone_percent,";
							show_field_string += "10072" + ",";
							show_field_string += "10073" + ",";
							show_num += 2;
						}
					} else if ("bb_2".equals(time_flag)) {// 季度
						select1 += " aa.cur_month_backtaskdone_percent,";
						select2 += " round(decode(value(max(t.cur_money_of_q_task),0),0,0,value(sum(t.cur_month_real_backmoney),0)*100/value(max(t.cur_money_of_q_task),0)),0) as cur_month_backtaskdone_percent,";
						// 同比、环比判断
						if (contrast_type == 1) {
							select1 += " value(bb.cur_month_backtaskdone_percent,0),";
							select_tb += " round(decode(value(max(t.cur_money_of_q_task),0),0,0,value(sum(t.cur_month_real_backmoney),0)*100/value(max(t.cur_money_of_q_task),0)),0) as cur_month_backtaskdone_percent,";
							show_field_string += "10072" + ",";
							show_num += 1;
						} else if (contrast_type == 2) {
							select1 += " value(cc.cur_month_backtaskdone_percent,0),";
							select_hb += " round(decode(value(max(t.cur_money_of_q_task),0),0,0,value(sum(t.cur_month_real_backmoney),0)*100/value(max(t.cur_money_of_q_task),0)),0) as cur_month_backtaskdone_percent,";
							show_field_string += "10073" + ",";
							show_num += 1;
						} else if (contrast_type == 3) {
							select1 += " value(bb.cur_month_backtaskdone_percent,0),value(cc.cur_month_backtaskdone_percent,0),";
							select_tb += " round(decode(value(max(t.cur_money_of_q_task),0),0,0,value(sum(t.cur_month_real_backmoney),0)*100/value(max(t.cur_money_of_q_task),0)),0) as cur_month_backtaskdone_percent,";
							select_hb += " round(decode(value(max(t.cur_money_of_q_task),0),0,0,value(sum(t.cur_month_real_backmoney),0)*100/value(max(t.cur_money_of_q_task),0)),0) as cur_month_backtaskdone_percent,";
							show_field_string += "10072" + ",";
							show_field_string += "10073" + ",";
							show_num += 2;
						}
					} else if ("bb_3".equals(time_flag)) {// 月份
						select1 += " aa.cur_month_backtaskdone_percent,";
						select2 += " round(decode(value(max(t.cur_month_real_task_money),0),0,0,value(sum(t.cur_month_real_backmoney),0)*100/value(max(t.cur_month_real_task_money),0)),0) as cur_month_backtaskdone_percent,";
						// 同比、环比判断
						if (contrast_type == 1) {
							select1 += " value(bb.cur_month_backtaskdone_percent,0),";
							select_tb += " round(decode(value(max(t.cur_month_real_task_money),0),0,0,value(sum(t.cur_month_real_backmoney),0)*100/value(max(t.cur_month_real_task_money),0)),0) as cur_month_backtaskdone_percent,";
							show_field_string += "10072" + ",";
							show_num += 1;
						} else if (contrast_type == 2) {
							select1 += " value(cc.cur_month_backtaskdone_percent,0),";
							select_hb += " round(decode(value(max(t.cur_month_real_task_money),0),0,0,value(sum(t.cur_month_real_backmoney),0)*100/value(max(t.cur_month_real_task_money),0)),0) as cur_month_backtaskdone_percent,";
							show_field_string += "10073" + ",";
							show_num += 1;
						} else if (contrast_type == 3) {
							select1 += " value(bb.cur_month_backtaskdone_percent,0),value(cc.cur_month_backtaskdone_percent,0),";
							select_tb += " round(decode(value(max(t.cur_month_real_task_money),0),0,0,value(sum(t.cur_month_real_backmoney),0)*100/value(max(t.cur_month_real_task_money),0)),0) as cur_month_backtaskdone_percent,";
							select_hb += " round(decode(value(max(t.cur_month_real_task_money),0),0,0,value(sum(t.cur_month_real_backmoney),0)*100/value(max(t.cur_month_real_task_money),0)),0) as cur_month_backtaskdone_percent,";
							show_field_string += "10072" + ",";
							show_field_string += "10073" + ",";
							show_num += 2;
						}
					}
				}
				if ("10081".equals(show_field[i])) {
					if ("bb_1".equals(time_flag)) {// 年度
						select1 += " aa.cur_month_taskdone_percent,";
						select2 += " round(decode(value(max(t.cur_money_of_year_task),0),0,0,value(sum(t.total_money_of_buy),0)*100/value(max(t.cur_money_of_year_task),0)),0) as cur_month_taskdone_percent,";
						// 同比、环比判断
						if (contrast_type == 1) {
							select1 += " value(bb.cur_month_taskdone_percent,0),";
							select_tb += " round(decode(value(max(t.cur_money_of_year_task),0),0,0,value(sum(t.total_money_of_buy),0)*100/value(max(t.cur_money_of_year_task),0)),0) as cur_month_taskdone_percent,";
							show_field_string += "10082" + ",";
							show_num += 1;
						} else if (contrast_type == 2) {
							select1 += " value(cc.cur_month_taskdone_percent,0),";
							select_hb += " round(decode(value(max(t.cur_money_of_year_task),0),0,0,value(sum(t.total_money_of_buy),0)*100/value(max(t.cur_money_of_year_task),0)),0) as cur_month_taskdone_percent,";
							show_field_string += "10083" + ",";
							show_num += 1;
						} else if (contrast_type == 3) {
							select1 += " value(bb.cur_month_taskdone_percent,0),value(cc.cur_month_taskdone_percent,0),";
							select_tb += " round(decode(value(max(t.cur_money_of_year_task),0),0,0,value(sum(t.total_money_of_buy),0)*100/value(max(t.cur_money_of_year_task),0)),0) as cur_month_taskdone_percent,";
							select_hb += " round(decode(value(max(t.cur_money_of_year_task),0),0,0,value(sum(t.total_money_of_buy),0)*100/value(max(t.cur_money_of_year_task),0)),0) as cur_month_taskdone_percent,";
							show_field_string += "10082" + ",";
							show_field_string += "10083" + ",";
							show_num += 2;
						}
					} else if ("bb_2".equals(time_flag)) {// 季度
						select1 += " aa.cur_month_taskdone_percent,";
						select2 += " round(decode(value(max(t.cur_money_of_q_task),0),0,0,value(sum(t.total_money_of_buy),0)*100/value(max(t.cur_money_of_q_task),0)),0) as cur_month_taskdone_percent,";
						// 同比、环比判断
						if (contrast_type == 1) {
							select1 += " value(bb.cur_month_taskdone_percent,0),";
							select_tb += " round(decode(value(max(t.cur_money_of_q_task),0),0,0,value(sum(t.total_money_of_buy),0)*100/value(max(t.cur_money_of_q_task),0)),0) as cur_month_taskdone_percent,";
							show_field_string += "10082" + ",";
							show_num += 1;
						} else if (contrast_type == 2) {
							select1 += " value(cc.cur_month_taskdone_percent,0),";
							select_hb += " round(decode(value(max(t.cur_money_of_q_task),0),0,0,value(sum(t.total_money_of_buy),0)*100/value(max(t.cur_money_of_q_task),0)),0) as cur_month_taskdone_percent,";
							show_field_string += "10083" + ",";
							show_num += 1;
						} else if (contrast_type == 3) {
							select1 += " value(bb.cur_month_taskdone_percent,0),value(cc.cur_month_taskdone_percent,0),";
							select_tb += " round(decode(value(max(t.cur_money_of_q_task),0),0,0,value(sum(t.total_money_of_buy),0)*100/value(max(t.cur_money_of_q_task),0)),0) as cur_month_taskdone_percent,";
							select_hb += " round(decode(value(max(t.cur_money_of_q_task),0),0,0,value(sum(t.total_money_of_buy),0)*100/value(max(t.cur_money_of_q_task),0)),0) as cur_month_taskdone_percent,";
							show_field_string += "10082" + ",";
							show_field_string += "10083" + ",";
							show_num += 2;
						}
					} else if ("bb_3".equals(time_flag)) {// 月份
						select1 += " aa.cur_month_taskdone_percent,";
						select2 += " round(decode(value(max(t.cur_month_real_task_money),0),0,0,value(sum(t.total_money_of_buy),0)*100/value(max(t.cur_month_real_task_money),0)),0) as cur_month_taskdone_percent,";
						// 同比、环比判断
						if (contrast_type == 1) {
							select1 += " value(bb.cur_month_taskdone_percent,0),";
							select_tb += " round(decode(value(max(t.cur_month_real_task_money),0),0,0,value(sum(t.total_money_of_buy),0)*100/value(max(t.cur_month_real_task_money),0)),0) as cur_month_taskdone_percent,";
							show_field_string += "10082" + ",";
							show_num += 1;
						} else if (contrast_type == 2) {
							select1 += " value(cc.cur_month_taskdone_percent,0),";
							select_hb += " round(decode(value(max(t.cur_month_real_task_money),0),0,0,value(sum(t.total_money_of_buy),0)*100/value(max(t.cur_month_real_task_money),0)),0) as cur_month_taskdone_percent,";
							show_field_string += "10083" + ",";
							show_num += 1;
						} else if (contrast_type == 3) {
							select1 += " value(bb.cur_month_taskdone_percent,0),value(cc.cur_month_taskdone_percent,0),";
							select_tb += " round(decode(value(max(t.cur_month_real_task_money),0),0,0,value(sum(t.total_money_of_buy),0)*100/value(max(t.cur_month_real_task_money),0)),0) as cur_month_taskdone_percent,";
							select_hb += " round(decode(value(max(t.cur_month_real_task_money),0),0,0,value(sum(t.total_money_of_buy),0)*100/value(max(t.cur_month_real_task_money),0)),0) as cur_month_taskdone_percent,";
							show_field_string += "10082" + ",";
							show_field_string += "10083" + ",";
							show_num += 2;
						}
					}
				}
				// ---------------------

				// 平均单价
				if ("10111".equals(show_field[i])) {
					select1 += " aa.avg_price,";
					select2 += " decode(value(sum(t.total_counts_of_sale),0),0,0,value(sum(t.total_money_of_sale),0)/sum(t.total_counts_of_sale)) as avg_price,";
					// 同比、环比判断
					if (contrast_type == 1) {
						select1 += " value(bb.avg_price,0),";
						select_tb += " decode(value(sum(t.total_counts_of_sale),0),0,0,value(sum(t.total_money_of_sale),0)/sum(t.total_counts_of_sale)) as avg_price,";
						show_field_string += "10112" + ",";
						show_num += 1;
					} else if (contrast_type == 2) {
						select1 += " value(cc.avg_price,0),";
						select_hb += " decode(value(sum(t.total_counts_of_sale),0),0,0,value(sum(t.total_money_of_sale),0)/sum(t.total_counts_of_sale)) as avg_price,";
						show_field_string += "10113" + ",";
						show_num += 1;
					} else if (contrast_type == 3) {
						select1 += " value(bb.avg_price,0),value(cc.avg_price,0),";
						select_tb += " decode(value(sum(t.total_counts_of_sale),0),0,0,value(sum(t.total_money_of_sale),0)/sum(t.total_counts_of_sale)) as avg_price,";
						select_hb += " decode(value(sum(t.total_counts_of_sale),0),0,0,value(sum(t.total_money_of_sale),0)/sum(t.total_counts_of_sale)) as avg_price,";
						show_field_string += "10112" + ",";
						show_field_string += "10113" + ",";
						show_num += 2;
					}
				}
				// 平均毛利
				if ("10121".equals(show_field[i])) {
					select1 += " aa.avg_profit_of_konka,";
					select2 += " decode(value(sum(t.total_counts_of_buy),0), 0, 0, sum(t.total_money_of_buy - t.cash_price * t.total_counts_of_buy) / sum(t.total_counts_of_buy)) as avg_profit_of_konka,";
					// 同比、环比判断
					if (contrast_type == 1) {
						select1 += " value(bb.avg_profit_of_konka,0),";
						select_tb += " decode(value(sum(t.total_counts_of_buy),0), 0, 0, sum(t.total_money_of_buy - t.cash_price * t.total_counts_of_buy) / sum(t.total_counts_of_buy)) as avg_profit_of_konka,";
						show_field_string += "10122" + ",";
						show_num += 1;
					} else if (contrast_type == 2) {
						select1 += " value(cc.avg_profit_of_konka,0),";
						select_hb += " decode(value(sum(t.total_counts_of_buy),0), 0, 0, sum(t.total_money_of_buy - t.cash_price * t.total_counts_of_buy) / sum(t.total_counts_of_buy)) as avg_profit_of_konka,";
						show_field_string += "10123" + ",";
						show_num += 1;
					} else if (contrast_type == 3) {
						select1 += " value(bb.avg_profit_of_konka,0),value(cc.avg_profit_of_konka,0),";
						select_tb += " decode(value(sum(t.total_counts_of_buy),0), 0, 0, sum(t.total_money_of_buy - t.cash_price * t.total_counts_of_buy) / sum(t.total_counts_of_buy)) as avg_profit_of_konka,";
						select_hb += " decode(value(sum(t.total_counts_of_buy),0), 0, 0, sum(t.total_money_of_buy - t.cash_price * t.total_counts_of_buy) / sum(t.total_counts_of_buy)) as avg_profit_of_konka,";
						show_field_string += "10122" + ",";
						show_field_string += "10123" + ",";
						show_num += 2;
					}
				}
				if ("10131".equals(show_field[i])) {
					select1 += " aa.sale_price,";
					select2 += " value(sum(t.sale_price),0) as sale_price,";
					// 同比、环比判断
					if (contrast_type == 1) {
						select1 += " value(bb.sale_price,0),";
						select_tb += " value(sum(t.sale_price),0) as sale_price,";
						show_field_string += "10132" + ",";
						show_num += 1;
					} else if (contrast_type == 2) {
						select1 += " value(cc.sale_price,0),";
						select_hb += " value(sum(t.sale_price),0) as sale_price,";
						show_field_string += "10133" + ",";
						show_num += 1;
					} else if (contrast_type == 3) {
						select1 += " value(bb.sale_price,0),value(cc.sale_price,0),";
						select_tb += " value(sum(t.sale_price),0) as sale_price,";
						select_hb += " value(sum(t.sale_price),0) as sale_price,";
						show_field_string += "10132" + ",";
						show_field_string += "10133" + ",";
						show_num += 2;
					}
				}
				if ("10141".equals(show_field[i])) {
					select1 += " aa.cash_price,";
					select2 += " value(sum(t.cash_price),0) as cash_price,";
					// 同比、环比判断
					if (contrast_type == 1) {
						select1 += " value(bb.cash_price,0),";
						select_tb += " value(sum(t.cash_price),0) as cash_price,";
						show_field_string += "10142" + ",";
						show_num += 1;
					} else if (contrast_type == 2) {
						select1 += " value(cc.cash_price,0),";
						select_hb += " value(sum(t.cash_price),0) as cash_price,";
						show_field_string += "10143" + ",";
						show_num += 1;
					} else if (contrast_type == 3) {
						select1 += " value(bb.cash_price,0),value(cc.cash_price,0),";
						select_tb += " value(sum(t.cash_price),0) as cash_price,";
						select_hb += " value(sum(t.cash_price),0) as cash_price,";
						show_field_string += "10142" + ",";
						show_field_string += "10143" + ",";
						show_num += 2;
					}
				}
			}
		}
		select1 = StringUtils.removeEnd(select1, ",") + " from ( ";
		select2 = StringUtils.removeEnd(select2, ",");
		select_tb = StringUtils.removeEnd(select_tb, ",");
		select_hb = StringUtils.removeEnd(select_hb, ",");
		group = StringUtils.removeEnd(group, ",");
		order = StringUtils.removeEnd(order, ",");

		List<String> array = new ArrayList<String>();
		List<String> array_temp = new ArrayList<String>();
		// 因为，同比、环比时间需要特殊处理
		// if (StringUtils.isNotBlank(sell_date_start)) {
		// where += " and t.add_day >= ? ";
		// array.add(sell_date_start);
		//
		// // 同比、环比判断时间，重新定义时间范围
		// }
		// if (StringUtils.isNotBlank(sell_date_end)) {
		// where += " and t.add_day <= ? ";
		// array.add(sell_date_end);
		//
		// // 同比、环比判断时间，重新定义时间范围
		// }
		if (StringUtils.isNotBlank(dept_id)) {
			where += " and t.dept_id = ? ";
			array.add(dept_id);
			array_temp.add(dept_id);
		}
		if (StringUtils.isNotBlank(l4_dept_id)) {
			where += " and t.l4_dept_id = ? ";
			array.add(l4_dept_id);
			array_temp.add(l4_dept_id);
		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			where += " and t.l5_dept_id = ? ";
			array.add(l5_dept_id);
			array_temp.add(l5_dept_id);
		}
		if (StringUtils.isNotBlank(ywy_user_name)) {
			where += " and t.ywy_user_name like '%'||?||'%' ";
			array.add(ywy_user_name);
			array_temp.add(ywy_user_name);
		}
		if (StringUtils.isNotBlank(customer_name)) {
			where += " and t.customer_name like '%'||?||'%' ";
			array.add(customer_name);
			array_temp.add(customer_name);
		}
		if (StringUtils.isNotBlank(size_sec)) {
			where += " and t.size_sec = ? ";
			array.add(size_sec);
			array_temp.add(size_sec);
		}
		if (StringUtils.isNotBlank(model_name)) {
			where += " and t.model_name like '%'||?||'%' ";
			array.add(model_name);
			array_temp.add(model_name);
		}
		if (StringUtils.isNotBlank(label_db)) {
			where += " and t.label_db = ? ";
			array.add(label_db);
			array_temp.add(label_db);
		}
		if (StringUtils.isNotBlank(label_int)) {
			where += " and t.label_int = ? ";
			array.add(label_int);
			array_temp.add(label_int);
		}

		select2 += " from V_A_DETAILS_OF_SALE_AND_BUY_M t ";
		select2 += where;
		// 因为，同比、环比，时间需要单独处理
		if (StringUtils.isNotBlank(sell_date_start)) {
			select2 += " and t.add_day >= ? ";
			array.add(sell_date_start);
		}
		if (StringUtils.isNotBlank(sell_date_end)) {
			select2 += " and t.add_day <= ? ";
			array.add(sell_date_end);
		}
		select2 += group;
		select2 += order;
		select2 += " ) aa ";
		String sql = select1 + select2;

		// 同比、环比判断
		if (contrast_type == 1) {
			sql += " left join ( ";
			sql += select_tb;
			sql += " from V_A_DETAILS_OF_SALE_AND_BUY_M t ";
			sql += where;
			if (null != array_temp && array_temp.size() > 0) {
				for (String temp : array_temp) {
					array.add(temp);
				}
			}
			// 因为，同比、环比，时间需要特殊处理
			// 同比判断，重新定义时间范围
			if (StringUtils.isNotBlank(sell_date_start)) {

				sql += " and t.add_day >= ? ";
				String sell_date_start_ = String.valueOf((Integer.valueOf(sell_date_start.substring(0, 4)) - 1))
						+ sell_date_start.substring(4, 7);
				array.add(sell_date_start_);
			}
			if (StringUtils.isNotBlank(sell_date_end)) {
				sql += " and t.add_day <= ? ";
				String sell_date_end_ = String.valueOf((Integer.valueOf(sell_date_end.substring(0, 4)) - 1))
						+ sell_date_end.substring(4, 7);
				array.add(sell_date_end_);
			}
			//

			sql += group;
			sql += " ) bb ";
			sql += left_join_on_tb;
		} else if (contrast_type == 2) {
			sql += " left join ( ";
			sql += select_hb;
			sql += " from V_A_DETAILS_OF_SALE_AND_BUY_M t ";
			sql += where;
			if (null != array_temp && array_temp.size() > 0) {
				for (String temp : array_temp) {
					array.add(temp);
				}
			}
			// 环比判断，重新定义时间范围
			if (StringUtils.isNotBlank(sell_date_start)) {

				sql += " and t.add_day >= ? ";
				String sell_date_start_ = "";
				if (StringUtils.isNotBlank(time_flag) && "bb_1".equals(time_flag)) {
					// 年度环比
					sell_date_start_ = String.valueOf((Integer.valueOf(sell_date_start.substring(0, 4)) - 1))
							+ sell_date_start.substring(4, 7);
				} else if (StringUtils.isNotBlank(time_flag) && "bb_2".equals(time_flag)) {
					// 季度环比
					// 2013-01-01
					String month = sell_date_start.substring(5, 7);

					if ("01".equals(month) || "02".equals(month) || "03".equals(month)) {
						// 1季度环比时间：上年度4季度开始时间
						sell_date_start_ = String.valueOf((Integer.valueOf(sell_date_start.substring(0, 4)) - 1))
								+ "-10";
					} else if ("04".equals(month) || "05".equals(month) || "06".equals(month)) {
						// 2季度环比时间：当前年度1季度开始时间
						sell_date_start_ = sell_date_start.substring(0, 4) + "-01";
					} else if ("07".equals(month) || "08".equals(month) || "09".equals(month)) {
						// 3季度环比时间：当前年度2季度开始时间
						sell_date_start_ = sell_date_start.substring(0, 4) + "-04";
					} else if ("10".equals(month) || "11".equals(month) || "12".equals(month)) {
						// 4季度环比时间：当前年度3季度开始时间
						sell_date_start_ = sell_date_start.substring(0, 4) + "-07";
					}
				} else if (StringUtils.isNotBlank(time_flag) && "bb_3".equals(time_flag)) {
					// 月度环比
					String month = sell_date_start.substring(5, 7);
					switch (Integer.valueOf(month)) {
					case 1:
						sell_date_start_ = String.valueOf((Integer.valueOf(sell_date_start.substring(0, 4)) - 1))
								+ "-12";
						break;
					case 2:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-01";
						break;
					case 3:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-02";
						break;
					case 4:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-03";
						break;
					case 5:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-04";
						break;
					case 6:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-05";
						break;
					case 7:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-06";
						break;
					case 8:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-07";
						break;
					case 9:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-08";
						break;
					case 10:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-09";
						break;
					case 11:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-10";
						break;
					case 12:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-11";
						break;
					default:
						break;
					}
				}

				array.add(sell_date_start_);
			}
			if (StringUtils.isNotBlank(sell_date_end)) {
				sql += " and t.add_day <= ? ";
				String sell_date_end_ = "";
				if (StringUtils.isNotBlank(time_flag) && "bb_1".equals(time_flag)) {
					// 年度环比
					sell_date_end_ = String.valueOf((Integer.valueOf(sell_date_end.substring(0, 4)) - 1))
							+ sell_date_end.substring(4, 7);
				} else if (StringUtils.isNotBlank(time_flag) && "bb_2".equals(time_flag)) {
					// 季度环比
					// 2013-01-01
					String month = sell_date_end.substring(5, 7);

					if ("01".equals(month) || "02".equals(month) || "03".equals(month)) {
						// 1季度环比时间：上年度4季度结束时间
						sell_date_end_ = String.valueOf((Integer.valueOf(sell_date_end.substring(0, 4)) - 1)) + "-12";
					} else if ("04".equals(month) || "05".equals(month) || "06".equals(month)) {
						// 2季度环比时间：当前年度1季度结束时间
						sell_date_end_ = sell_date_end.substring(0, 4) + "-03";
					} else if ("07".equals(month) || "08".equals(month) || "09".equals(month)) {
						// 3季度环比时间：当前年度2季度结束时间
						sell_date_end_ = sell_date_end.substring(0, 4) + "-06";
					} else if ("10".equals(month) || "11".equals(month) || "12".equals(month)) {
						// 4季度环比时间：当前年度3季度结束时间
						sell_date_end_ = sell_date_end.substring(0, 4) + "-09";
					}
				} else if (StringUtils.isNotBlank(time_flag) && "bb_3".equals(time_flag)) {
					// 月度环比
					String month = sell_date_end.substring(5, 7);
					switch (Integer.valueOf(month)) {
					case 1:
						sell_date_end_ = String.valueOf((Integer.valueOf(sell_date_end.substring(0, 4)) - 1)) + "-12";
						break;
					case 2:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-01";
						break;
					case 3:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-02";
						break;
					case 4:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-03";
						break;
					case 5:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-04";
						break;
					case 6:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-05";
						break;
					case 7:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-06";
						break;
					case 8:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-07";
						break;
					case 9:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-08";
						break;
					case 10:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-09";
						break;
					case 11:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-10";
						break;
					case 12:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-11";
						break;
					default:
						break;
					}
				}
				array.add(sell_date_end_);
			}
			//
			sql += group;
			sql += " ) cc ";
			sql += left_join_on_hb;
		} else if (contrast_type == 3) {
			sql += " left join( ";
			sql += select_tb;
			sql += " from V_A_DETAILS_OF_SALE_AND_BUY_M t ";
			sql += where;
			if (null != array_temp && array_temp.size() > 0) {
				for (String temp : array_temp) {
					array.add(temp);
				}
			}

			// 同比判断，重新定义时间范围
			if (StringUtils.isNotBlank(sell_date_start)) {

				sql += " and t.add_day >= ? ";
				String sell_date_start_ = String.valueOf((Integer.valueOf(sell_date_start.substring(0, 4)) - 1))
						+ sell_date_start.substring(4, 7);
				array.add(sell_date_start_);
			}
			if (StringUtils.isNotBlank(sell_date_end)) {
				sql += " and t.add_day <= ? ";
				String sell_date_end_ = String.valueOf((Integer.valueOf(sell_date_end.substring(0, 4)) - 1))
						+ sell_date_end.substring(4, 7);
				array.add(sell_date_end_);
			}
			//

			sql += group;
			sql += " ) bb ";
			sql += left_join_on_tb;

			sql += " left join ( ";
			sql += select_hb;
			sql += " from V_A_DETAILS_OF_SALE_AND_BUY_M t ";
			sql += where;
			if (null != array_temp && array_temp.size() > 0) {
				for (String temp : array_temp) {
					array.add(temp);
				}
			}

			// 环比判断，重新定义时间范围
			if (StringUtils.isNotBlank(sell_date_start)) {

				sql += " and t.add_day >= ? ";
				String sell_date_start_ = "";
				if (StringUtils.isNotBlank(time_flag) && "bb_1".equals(time_flag)) {
					// 年度环比
					sell_date_start_ = String.valueOf((Integer.valueOf(sell_date_start.substring(0, 4)) - 1))
							+ sell_date_start.substring(4, 7);
				} else if (StringUtils.isNotBlank(time_flag) && "bb_2".equals(time_flag)) {
					// 季度环比
					// 2013-01-01
					String month = sell_date_start.substring(5, 7);

					if ("01".equals(month) || "02".equals(month) || "03".equals(month)) {
						// 1季度环比时间：上年度4季度开始时间
						sell_date_start_ = String.valueOf((Integer.valueOf(sell_date_start.substring(0, 4)) - 1))
								+ "-10";
					} else if ("04".equals(month) || "05".equals(month) || "06".equals(month)) {
						// 2季度环比时间：当前年度1季度开始时间
						sell_date_start_ = sell_date_start.substring(0, 4) + "-01";
					} else if ("07".equals(month) || "08".equals(month) || "09".equals(month)) {
						// 3季度环比时间：当前年度2季度开始时间
						sell_date_start_ = sell_date_start.substring(0, 4) + "-04";
					} else if ("10".equals(month) || "11".equals(month) || "12".equals(month)) {
						// 4季度环比时间：当前年度3季度开始时间
						sell_date_start_ = sell_date_start.substring(0, 4) + "-07";
					}
				} else if (StringUtils.isNotBlank(time_flag) && "bb_3".equals(time_flag)) {
					// 月度环比
					String month = sell_date_start.substring(5, 7);
					switch (Integer.valueOf(month)) {
					case 1:
						sell_date_start_ = String.valueOf((Integer.valueOf(sell_date_start.substring(0, 4)) - 1))
								+ "-12";
						break;
					case 2:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-01";
						break;
					case 3:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-02";
						break;
					case 4:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-03";
						break;
					case 5:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-04";
						break;
					case 6:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-05";
						break;
					case 7:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-06";
						break;
					case 8:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-07";
						break;
					case 9:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-08";
						break;
					case 10:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-09";
						break;
					case 11:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-10";
						break;
					case 12:
						sell_date_start_ = sell_date_start.substring(0, 4) + "-11";
						break;
					default:
						break;
					}
				}

				array.add(sell_date_start_);
			}
			if (StringUtils.isNotBlank(sell_date_end)) {
				sql += " and t.add_day <= ? ";
				String sell_date_end_ = "";
				if (StringUtils.isNotBlank(time_flag) && "bb_1".equals(time_flag)) {
					// 年度环比
					sell_date_end_ = String.valueOf((Integer.valueOf(sell_date_end.substring(0, 4)) - 1))
							+ sell_date_end.substring(4, 7);
				} else if (StringUtils.isNotBlank(time_flag) && "bb_2".equals(time_flag)) {
					// 季度环比
					// 2013-01-01
					String month = sell_date_end.substring(5, 7);

					if ("01".equals(month) || "02".equals(month) || "03".equals(month)) {
						// 1季度环比时间：上年度4季度结束时间
						sell_date_end_ = String.valueOf((Integer.valueOf(sell_date_end.substring(0, 4)) - 1)) + "-12";
					} else if ("04".equals(month) || "05".equals(month) || "06".equals(month)) {
						// 2季度环比时间：当前年度1季度结束时间
						sell_date_end_ = sell_date_end.substring(0, 4) + "-03";
					} else if ("07".equals(month) || "08".equals(month) || "09".equals(month)) {
						// 3季度环比时间：当前年度2季度结束时间
						sell_date_end_ = sell_date_end.substring(0, 4) + "-06";
					} else if ("10".equals(month) || "11".equals(month) || "12".equals(month)) {
						// 4季度环比时间：当前年度3季度结束时间
						sell_date_end_ = sell_date_end.substring(0, 4) + "-09";
					}
				} else if (StringUtils.isNotBlank(time_flag) && "bb_3".equals(time_flag)) {
					// 月度环比
					String month = sell_date_end.substring(5, 7);
					switch (Integer.valueOf(month)) {
					case 1:
						sell_date_end_ = String.valueOf((Integer.valueOf(sell_date_end.substring(0, 4)) - 1)) + "-12";
						break;
					case 2:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-01";
						break;
					case 3:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-02";
						break;
					case 4:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-03";
						break;
					case 5:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-04";
						break;
					case 6:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-05";
						break;
					case 7:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-06";
						break;
					case 8:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-07";
						break;
					case 9:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-08";
						break;
					case 10:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-09";
						break;
					case 11:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-10";
						break;
					case 12:
						sell_date_end_ = sell_date_end.substring(0, 4) + "-11";
						break;
					default:
						break;
					}
				}
				array.add(sell_date_end_);
			}
			//

			sql += group;
			sql += " ) cc ";
			sql += left_join_on_hb;
		}
		log.info("**********sql:" + sql);
		if (null != array && array.size() > 0) {
			String array_string = "";
			for (int i = 0; i < array.size(); i++) {
				array_string += array.get(i) + ",";
			}
			log.info("**********array:" + array.size());
			log.info("**********array_string:" + array_string);
		}

		List<?> entityList = super.getFacade().getBaseReportService().getBaseReportForBindToArray(sql, array.toArray());
		b.setGroup_by_field(group_by_field);
		b.setShow_field(show_field);
		b.setShow_num(show_num);
		b.setGroup_flag_string(group_flag_string);
		b.setShow_field_string(show_field_string);
		b.setEntityList(entityList);
		return b;
	}

	// 选择排序:从未排好的数中选一个最小值，插到已排好的序列中
	public static String[] selectSortForString(String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int idx = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j].compareTo(arr[idx]) < 0) {
					idx = j;
				}
			}
			if (idx != i) {
				String tmp = arr[idx];
				arr[idx] = arr[i];
				arr[i] = tmp;
			}
		}
		return arr;
	}

	public static String[] selectSortForInteger(String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int idx = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (Integer.valueOf(arr[j]) < Integer.valueOf(arr[idx])) {
					idx = j;
				}
			}
			if (idx != i) {
				String tmp = arr[idx];
				arr[idx] = arr[i];
				arr[i] = tmp;
			}
		}
		return arr;
	}
}