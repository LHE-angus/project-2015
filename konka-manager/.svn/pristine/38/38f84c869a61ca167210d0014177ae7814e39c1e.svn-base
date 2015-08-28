package com.ebiz.mmt.web.struts.manager.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaDeptTree;
import com.ebiz.mmt.domain.KonkaMobileTestData;
import com.ebiz.mmt.domain.MobileSelectItem;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.MobileBaseAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaMobileTestDataQueryAction extends MobileBaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		String report_name_like = (String) dynaBean.get("report_name_like");
		String date_begin = (String) dynaBean.get("date_begin");
		String date_end = (String) dynaBean.get("date_end");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String yw_name = (String) dynaBean.get("yw_name");
		String is_del = (String) dynaBean.get("is_del");
		String excel_all = (String) dynaBean.get("excel_all");
		String dept_name_like = (String) dynaBean.get("dept_name_like");

		String l3_dept_id = (String) dynaBean.get("l3_dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String c_comm = (String) dynaBean.get("c_comm");
		String customer_cate_id = (String) dynaBean.get("customer_cate_id");

		// 型号查询、上样时间、下架时间、上样状态
		String model_name_like = (String) dynaBean.get("model_name_like");
		String up_date_begin = (String) dynaBean.get("up_date_begin");
		String up_date_end = (String) dynaBean.get("up_date_end");
		String down_date_begin = (String) dynaBean.get("down_date_begin");
		String down_date_end = (String) dynaBean.get("down_date_end");
		String test_statu = (String) dynaBean.get("test_statu");

		// 所属客户
		KonkaCategory konkaCategory = new KonkaCategory();
		List<KonkaCategory> konkaCategoryList = super.getFacade().getKonkaCategoryService()
		        .getKonkaCategoryGroupByCCommList(konkaCategory);
		request.setAttribute("konkaCategoryList", konkaCategoryList);

		KonkaMobileTestData entity = new KonkaMobileTestData();
		super.copyProperties(entity, form);

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_10 = false; // 非系统管理员
		boolean role_id_eq_60 = false; // 业务员
		// boolean role_id_eq_30 = false; // 分公司管理员
		// boolean role_id_eq_60 = false;
		String role_ids = "";
		for (PeRoleUser peRoleUser : peRoleUserList) {
			role_ids = role_ids + "," + peRoleUser.getRole_id();
			if (peRoleUser.getRole_id() == 10L) {
				role_id_eq_10 = true;
			}
			if (peRoleUser.getRole_id() == 60L) {
				role_id_eq_60 = true;
			}
		}

		if (StringUtils.isNotBlank(l5_dept_id)) {
			entity.getMap().put("dept_id_start", l5_dept_id);
		} else if (StringUtils.isNotBlank(l4_dept_id)) {
			entity.getMap().put("dept_id_start", l4_dept_id);
		} else if (StringUtils.isNotBlank(l3_dept_id)) {
			entity.getMap().put("dept_id_start", l3_dept_id);
		}
		entity.getMap().put("filter_by_job_id_eq", peProdUser.getJob_id());
		entity.getMap().put("session_user_id", peProdUser.getId());

		// 型号查询、上样时间、下架时间、上样状态
		if (StringUtils.isNotBlank(model_name_like)) {
			entity.getMap().put("model_name_like", model_name_like);
		}
		if (StringUtils.isNotBlank(up_date_begin)) {
			entity.getMap().put("up_date_begin", up_date_begin);
		}
		if (StringUtils.isNotBlank(up_date_end)) {
			entity.getMap().put("up_date_end", up_date_end);
		}
		if (StringUtils.isNotBlank(down_date_begin)) {
			entity.getMap().put("down_date_begin", down_date_begin);
		}
		if (StringUtils.isNotBlank(down_date_end)) {
			entity.getMap().put("down_date_end", down_date_end);
		}
		/*
		 * if (StringUtils.isNotBlank(test_statu)) {
		 * entity.getMap().put("test_statu", test_statu); } else {
		 * entity.getMap().put("test_statu_empty", true); }
		 */

		if (StringUtils.isNotBlank(test_statu)) {
			if (test_statu.equals("0")) {// 上架中
				entity.getMap().put("is_up_1", true);
			} else if (test_statu.equals("1")) {// 已下架
				entity.getMap().put("is_up_0", true);
			}
		} else {
			entity.getMap().put("is_up_1", true);// 默认取上架的，并回显
			dynaBean.set("test_statu", "0");
		}

		if (!role_id_eq_10) {
			// 非系统管理员
			// 分公司用户 未指定分公司查询条件 取当前登录用户部门ID
			KonkaDept kd = super.getSuperiorForDeptType(peProdUser.getDept_id(), 3);

			if (null != kd) {
				if (null == entity.getMap().get("dept_id_start")) {
					entity.getMap().put("dept_id_start", peProdUser.getDept_id());
				}
			}

			// 业务员
			if (role_id_eq_60) {
				entity.getMap().put("ye_name", peProdUser.getUser_name());

				if (null != peRoleUserList && peRoleUserList.size() == 1) {
					entity.getMap().put("filter_by_ywy", "true");
				}
			} else {
				if (StringUtils.isNotBlank(yw_name)) {
					entity.getMap().put("ye_name", yw_name);
				}
			}

		} else {
			// 系统管理员
			request.setAttribute("isFgsUser", "true");
		}

		entity.getMap().put("customer_name_like", customer_name_like);
		entity.getMap().put("report_name_like", report_name_like);
		entity.getMap().put("dept_name_like", dept_name_like);

		if (StringUtils.isNotEmpty(c_comm)) {
			String t_c_name = null;
			if (StringUtils.isNotEmpty(customer_cate_id)) {
				KonkaCategory t = new KonkaCategory();
				t.setIs_del(0);
				t.setC_index(Long.valueOf(customer_cate_id));
				t = getFacade().getKonkaCategoryService().getKonkaCategory(t);
				if (null != t) {
					t_c_name = t.getC_name();
				}
			}
			for (KonkaCategory tt : konkaCategoryList) {
				if (c_comm.equals(tt.getMap().get("par_index"))) {
					if (null != t_c_name) {
						entity.getMap().put("org_name_like", "[" + tt.getMap().get("c_comm") + "]" + t_c_name);
					} else {
						entity.getMap().put("org_name_like", tt.getMap().get("c_comm"));
					}

				}

			}
		}

		// 是否有效
		entity.setStatus(StringUtils.isNotBlank(is_del) ? Integer.valueOf(is_del) : 0);

		// if (StringUtils.isNotBlank(date_begin)) {
		// entity.getMap().put("startime", date_begin);
		// }
		// if (StringUtils.isNotBlank(date_end)) {
		// entity.getMap().put("endtime", date_end);
		// }
		SimpleDateFormat mm_fmt = new SimpleDateFormat("yyyy-MM");

		SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd");

		if (StringUtils.isNotBlank(date_begin)) {
			// Date d = DateUtils.parseDate(date_begin + " 00:00:00", new
			// String[] { "yyy-MM-dd HH:mm:ss" });
			// entity.getMap().put("report_date_begin", d);
			entity.getMap().put("startime", date_begin);
		} else {
			// 判断是否有时间没有的话取当前一个月的时间
			entity.getMap().put("startime", mm_fmt.format(new Date()) + "-01");
			dynaBean.set("date_begin", mm_fmt.format(new Date()) + "-01");
		}
		if (StringUtils.isNotBlank(date_end)) {
			// Date d = DateUtils.parseDate(date_end + " 23:59:59", new String[]
			// { "yyy-MM-dd HH:mm:ss" });
			// entity.getMap().put("report_date_end", d);
			entity.getMap().put("endtime", date_end);
		} else {
			entity.getMap().put("endtime", fmt1.format(new Date()));
			dynaBean.set("date_end", fmt1.format(new Date()));
		}

		Long recordCount = getFacade().getKonkaMobileTestDataService().getKonkaMobileTestDataCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaMobileTestData> entityList = getFacade().getKonkaMobileTestDataService()
		        .getKonkaMobileTestDataAndSailDatasPaginatedList(entity);
		for (KonkaMobileTestData t : entityList) {
			if (null != t.getOrg_name() && StringUtils.split(t.getOrg_name(), "]").length >= 2) {
				t.getMap().put("c_comm", StringUtils.replace(StringUtils.split(t.getOrg_name(), "]")[0], "[", ""));
				t.getMap().put("c_name", StringUtils.split(t.getOrg_name(), "]")[1]);
			}
			if (t.getDown_date() != null) {// 20141112
				if (t.getDown_date().getTime() > new Date().getTime()
				        && t.getUp_date().getTime() < new Date().getTime()) {
					t.getMap().put("up_self", true);
				}
			} else if (t.getDown_date() == null) {
				if(t.getUp_date()!=null){
					if (t.getUp_date().getTime() < new Date().getTime()) {
						t.getMap().put("up_self", true);
					}
				}
				
			}

			String save_path = (String) t.getMap().get("save_path");
			if (save_path != null && !save_path.equals("")) {
				String[] ss = save_path.split(",");
				if (ss.length > 0) {
					for (String xx : ss) {// 22#背面,33#正面
						//System.out.println("xx-->" + xx);
						if (xx.contains("正面")) {
							t.getMap().put("zm", xx.split("&")[0]);
						} else if (xx.contains("背面")) {
							t.getMap().put("bm", xx.split("&")[0]);
						} else if (xx.contains("侧面")) {
							t.getMap().put("cm", xx.split("&")[0]);
						}
					}
				}
			}

			//System.out.println(t.getMap().get("store_r3_sn"));
		}
		request.setAttribute("entityList", entityList);

		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			if (recordCount.intValue() > 20000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
				        + "！');history.back();");
				return null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("样机管理数据")
			        + ".xls");
			entity.getRow().setCount(recordCount.intValue());
			List<KonkaMobileTestData> entityList1 = getFacade().getKonkaMobileTestDataService()
			        .getKonkaMobileTestDataPaginatedList(entity);
			// dynaBean.set("excel_all", excel_all);
			// request.setAttribute("allList", entityList1);
			for (KonkaMobileTestData t : entityList1) {
				if (null != t.getOrg_name() && StringUtils.split(t.getOrg_name(), "]").length >= 2) {
					t.getMap().put("c_comm", StringUtils.replace(StringUtils.split(t.getOrg_name(), "]")[0], "[", ""));
					t.getMap().put("c_name", StringUtils.split(t.getOrg_name(), "]")[1]);
				}
				if (t.getDown_date() != null) {// 20141112
					if (t.getDown_date().getTime() > new Date().getTime()
					        && t.getUp_date().getTime() < new Date().getTime()) {
						t.getMap().put("up_self", true);
					}
				} else if (t.getDown_date() == null) {
					if (t.getUp_date() != null) {
						if (t.getUp_date().getTime() < new Date().getTime()) {
							t.getMap().put("up_self", true);
						}
					}
				}

				String save_path = (String) t.getMap().get("save_path");
				if (save_path != null && !save_path.equals("")) {
					String[] ss = save_path.split(",");
					if (ss.length > 0) {
						for (String xx : ss) {// 22#背面,33#正面
							//System.out.println("xx-->" + xx);
							if (xx.contains("正面")) {
								t.getMap().put("zm", xx.split("&")[0]);
							} else if (xx.contains("背面")) {
								t.getMap().put("bm", xx.split("&")[0]);
							} else if (xx.contains("侧面")) {
								t.getMap().put("cm", xx.split("&")[0]);
							}
						}
					}
				}

			}
			request.setAttribute("entityList1", entityList1);
			request.setAttribute("today", new Date());
			return new ActionForward("/admin/KonkaMobileTestDataQuery/excel.jsp");
		}

		// 尺寸sizeList
		List<MobileSelectItem> sizeList = new ArrayList<MobileSelectItem>();
		for (String str : size_strs) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(str);
			t.setName(str);
			sizeList.add(t);
		}
		request.setAttribute("sizeList", sizeList);

		Date now = new Date();
		Date yesterday = DateUtils.addDays(now, -1);
		Date two_days_ago = DateUtils.addDays(now, -2);
		request.setAttribute("now", now);
		request.setAttribute("yesterday", yesterday);
		request.setAttribute("two_days_ago", two_days_ago);

		KonkaDeptTree t = new KonkaDeptTree();
		t.setDept_id(peProdUser.getDept_id());
		t = super.getFacade().getKonkaDeptTreeService().getKonkaDeptTree(t);
		if (null != t && t.getIs_leaf() == 1) {
			request.setAttribute("current_dept", t);
		}

		request.setAttribute("cs_par_id", peProdUser.getDept_id());
		request.setAttribute("today", new Date());

		return mapping.findForward("list");
	}

	public ActionForward exportExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		DynaBean dynaBean = (DynaBean) form;
		String report_name_like = (String) dynaBean.get("report_name_like");
		String date_begin = (String) dynaBean.get("date_begin");
		String date_end = (String) dynaBean.get("date_end");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String yw_name = (String) dynaBean.get("yw_name");
		String is_del = (String) dynaBean.get("is_del");
		String dept_name_like = (String) dynaBean.get("dept_name_like");

		String l3_dept_id = (String) dynaBean.get("l3_dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String c_comm = (String) dynaBean.get("c_comm");

		KonkaMobileTestData entity = new KonkaMobileTestData();
		super.copyProperties(entity, form);

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_10 = false; // 非系统管理员
		boolean role_id_eq_60 = false; // 业务员
		boolean role_id_eq_30 = false; // 分公司管理员
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() == 10L) {
				role_id_eq_10 = true;
			}
			if (peRoleUser.getRole_id() == 60L) {
				role_id_eq_60 = true;
			}
			if (peRoleUser.getRole_id() == 30L) {
				role_id_eq_30 = true;
			}
		}

		if (StringUtils.isNotBlank(l5_dept_id)) {
			entity.getMap().put("dept_id_start", l5_dept_id);
		} else if (StringUtils.isNotBlank(l4_dept_id)) {
			entity.getMap().put("dept_id_start", l4_dept_id);
		} else if (StringUtils.isNotBlank(l3_dept_id)) {
			entity.getMap().put("dept_id_start", l3_dept_id);
		}
		entity.getMap().put("session_user_id", peProdUser.getId());

		if (!role_id_eq_10) {
			// 非系统管理员
			// 分公司用户 未指定分公司查询条件 取当前登录用户部门ID
			KonkaDept kd = super.getSuperiorForDeptType(peProdUser.getDept_id(), 3);
			if (null != kd) {
				if (null == entity.getMap().get("dept_id_start")) {
					entity.getMap().put("dept_id_start", peProdUser.getDept_id());
				}
			}

			if (!role_id_eq_30 && !role_id_eq_10) {
				entity.getMap().put("filter_by_job_id_eq", peProdUser.getJob_id());
				entity.getMap().remove("dept_id_start");
			}

			// 业务员
			if (role_id_eq_60) {
				entity.getMap().put("ye_name", peProdUser.getUser_name());

				if (null != peRoleUserList && peRoleUserList.size() == 1) {
					entity.getMap().put("filter_by_ywy", "true");
				}
			} else {
				if (StringUtils.isNotBlank(yw_name)) {
					entity.getMap().put("ye_name", yw_name);
				}
			}

		} else {
			// 系统管理员
			request.setAttribute("isFgsUser", "true");
		}

		entity.getMap().put("customer_name_like", customer_name_like);
		entity.getMap().put("report_name_like", report_name_like);
		entity.getMap().put("dept_name_like", dept_name_like);
		entity.getMap().put("c_comm", c_comm);

		// 是否有效
		entity.setStatus(StringUtils.isNotBlank(is_del) ? Integer.valueOf(is_del) : 0);

		SimpleDateFormat mm_fmt = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd");

		if (StringUtils.isNotBlank(date_begin)) {
			Date d = DateUtils.parseDate(date_begin + " 00:00:00", new String[] { "yyy-MM-dd HH:mm:ss" });
			entity.getMap().put("report_date_begin", d);
		} else {
			// 判断是否有时间没有的话取当前一个月的时间
			entity.getMap().put("report_date_begin", mm_fmt.format(new Date()) + "-01 00:00:00");
			dynaBean.set("date_begin", mm_fmt.format(new Date()) + "-01");
		}
		if (StringUtils.isNotBlank(date_end)) {
			Date d = DateUtils.parseDate(date_end + " 23:59:59", new String[] { "yyy-MM-dd HH:mm:ss" });
			entity.getMap().put("report_date_end", d);
		} else {
			entity.getMap().put("report_date_end", fmt.format(new Date()));
			dynaBean.set("date_end", fmt1.format(new Date()));
		}
		List<KonkaMobileTestData> entityList = getFacade().getKonkaMobileTestDataService()
		        .getKonkaMobileTestDataAndSailDatasPaginatedList(entity);
		for (KonkaMobileTestData t : entityList) {
			if (null != t.getOrg_name() && StringUtils.split(t.getOrg_name(), "]").length >= 2) {
				t.getMap().put("c_comm", StringUtils.replace(StringUtils.split(t.getOrg_name(), "]")[0], "[", ""));
				t.getMap().put("c_name", StringUtils.split(t.getOrg_name(), "]")[1]);
			}
			if (t.getDown_date() != null) {// 20141112
				if (t.getDown_date().getTime() > new Date().getTime()
				        && t.getUp_date().getTime() < new Date().getTime()) {
					t.getMap().put("up_self", true);
				}
			} else if (t.getDown_date() == null) {
				if (t.getUp_date().getTime() < new Date().getTime()) {
					t.getMap().put("up_self", true);
				}
			}

			String save_path = (String) t.getMap().get("save_path");
			if (save_path != null && !save_path.equals("")) {
				String[] ss = save_path.split(",");
				if (ss.length > 0) {
					for (String xx : ss) {// 22#背面,33#正面
						//System.out.println("xx-->" + xx);
						if (xx.contains("正面")) {
							t.getMap().put("zm", xx.split("&")[0]);
						} else if (xx.contains("背面")) {
							t.getMap().put("bm", xx.split("&")[0]);
						} else if (xx.contains("侧面")) {
							t.getMap().put("cm", xx.split("&")[0]);
						}
					}
				}
			}
		}
		request.setAttribute("entityList", entityList);

		// 类别 下拉选项
		List<BasePdClazz> basePdClazzList = super.getFacade().getRetailMsBaseService()
		        .getKonkaBasePdClazzListByClsIds();
		request.setAttribute("basePdClazzList", basePdClazzList);

		// 尺寸sizeList
		List<MobileSelectItem> sizeList = new ArrayList<MobileSelectItem>();
		for (String str : size_strs) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(str);
			t.setName(str);
			sizeList.add(t);
		}
		request.setAttribute("sizeList", sizeList);
		request.setAttribute("date", DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		return mapping.findForward("excel");
	}

	// 查看
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);
		super.saveToken(request);
		String report_type = (String) dynaBean.get("report_type");
		String id = (String) dynaBean.get("id");
		if (StringUtils.isBlank(id)) {
			super.renderHtml(response, "请选择一条记录修改!");
			return null;
		}
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			return null;
		}
		List<KonkaMobileTestData> entityList = new ArrayList<KonkaMobileTestData>();
		KonkaMobileTestData konkaMobileTestData = new KonkaMobileTestData();
		if (StringUtils.isNotBlank(id)) {
			konkaMobileTestData.setId(Long.valueOf(id));
			konkaMobileTestData.getRow().setFirst(0);
			konkaMobileTestData.getRow().setCount(1);
			entityList = getFacade().getKonkaMobileTestDataService().getKonkaMobileTestDataAndSailDatasPaginatedList(
			        konkaMobileTestData);
		}
		konkaMobileTestData = entityList.get(0);
		konkaMobileTestData.setQueryString(super.serialize(request, "visit_id", "method"));
		super.copyProperties(form, konkaMobileTestData);
		// 拿到图片
		Attachment attachment = new Attachment();
		attachment.setLink_tab("KONKA_MOBILE_TEST_DATA");
		if (StringUtils.isNotBlank(id)) {
			attachment.setLink_id(Long.valueOf(id));
			attachment.setDel_mark(new Short("0"));
			List<Attachment> attList = super.getFacade().getAttachmentService().getAttachmentList(attachment);
			List<Attachment> attListtemp = new ArrayList<Attachment>();
			for (Attachment attachment2 : attList) {
				//System.out.println(attachment2.getFile_desc());
				if ("正面".equals(attachment2.getFile_desc())) {
					dynaBean.set("front", attachment2);
				} else if ("背面".equals(attachment2.getFile_desc())) {
					dynaBean.set("back", attachment2);
				} else if ("侧面".equals(attachment2.getFile_desc())) {
					dynaBean.set("side", attachment2);
				} else {
					attListtemp.add(attachment2);
				}
			}
			request.setAttribute("fj_paths", JSON.toJSONString(attListtemp));
		}

		return mapping.findForward("view");
	}

	/*
	 * 办事处
	 */
	public ActionForward getDept(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		// subcomp_id
		DynaBean dynaBean = (DynaBean) form;
		String subcomp_id = (String) dynaBean.get("subcomp_id");
		logger.info("-------subcomp_id--->{}", subcomp_id);
		if (StringUtils.isNotEmpty(subcomp_id)) {
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setPar_id(new Long(subcomp_id));

			List<KonkaDept> baseDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);

			List<String> dataList = new ArrayList<String>();
			for (KonkaDept _t : baseDeptList) {
				dataList.add(StringUtils.join(new String[] { "[\"", _t.getDept_name(), "\",\"",
				        String.valueOf(_t.getDept_id()), "\"]" }));
			}
			super.renderJson(response, StringUtils.join(new String[] { "[", StringUtils.join(dataList, ","), "]" }));
		}
		return null;
	}

	/*
	 * 产品型号
	 */
	public ActionForward getModel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		// subcomp_id
		DynaBean dynaBean = (DynaBean) form;
		String size_id = (String) dynaBean.get("size_id");
		if (StringUtils.isNotEmpty(size_id)) {

			// String[] cls_ids = { category_id };
			// List<PePdModel> pePdModelList = super.getFacade()
			// .getRetailMsBaseService().getKonkaPePdModelListByClsIds(
			// cls_ids);
			//
			List<String> dataList = new ArrayList<String>();
			PePdModel t = new PePdModel();
			t.getMap().put("led_name_like", size_id);
			t.getMap().put("order_by_pd_name_desc", true);
			List<PePdModel> tList = new ArrayList<PePdModel>();
			tList = super.getFacade().getPePdModelService().getPePdModelList(t);
			for (PePdModel _t : tList) {
				dataList.add(StringUtils.join(new String[] { "[\"", _t.getMd_name(), "\",\"",
				        String.valueOf(_t.getPd_id()), "\"]" }));
			}
			super.renderJson(response, StringUtils.join(new String[] { "[", StringUtils.join(dataList, ","), "]" }));
		}
		return null;
	}

	/*
	 * 产品型号
	 */
	public ActionForward getCType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		// subcomp_id
		DynaBean dynaBean = (DynaBean) form;
		String c_comm = (String) dynaBean.get("c_comm");
		if (StringUtils.isNotEmpty(c_comm)) {

			List<String> dataList = new ArrayList<String>();
			KonkaCategory t = new KonkaCategory();
			t.setPar_index(c_comm);
			List<KonkaCategory> tList = super.getFacade().getKonkaCategoryService().getKonkaCategoryList(t);
			for (KonkaCategory _t : tList) {
				dataList.add(StringUtils.join(new String[] { "[\"", _t.getC_name(), "\",\"",
				        String.valueOf(_t.getC_index()), "\"]" }));
			}
			super.renderJson(response, StringUtils.join(new String[] { "[", StringUtils.join(dataList, ","), "]" }));
		}
		return null;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaMobileTestData entity = new KonkaMobileTestData();
		entity.setId(Long.valueOf(id));
		entity.setStatus(1);
		super.getFacade().getKonkaMobileTestDataService().modifyKonkaMobileTestData(entity);
		saveMessage(request, "konka.sail.data.update");

		// 将条件回显
		String l3_dept_id = (String) dynaBean.get("l3_dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String report_name_like = (String) dynaBean.get("report_name_like");
		String dept_name_like = (String) dynaBean.get("dept_name_like");
		String c_comm = (String) dynaBean.get("c_comm");
		String customer_cate_id = (String) dynaBean.get("customer_cate_id");
		String date_begin = (String) dynaBean.get("date_begin");
		String date_end = (String) dynaBean.get("date_end");
		String yw_name = (String) dynaBean.get("yw_name");
		String is_del = (String) dynaBean.get("is_del");
		String excel_all = (String) dynaBean.get("excel_all");
		String measure_id = (String) dynaBean.get("measure_id");
		String model_id = (String) dynaBean.get("model_id");
		String model_name_like = (String) dynaBean.get("model_name_like");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&l3_dept_id=" + l3_dept_id);
		pathBuffer.append("&l4_dept_id=" + l4_dept_id);
		pathBuffer.append("&l5_dept_id=" + l5_dept_id);
		pathBuffer.append("&customer_name_like=" + customer_name_like);
		pathBuffer.append("&report_name_like=" + report_name_like);
		pathBuffer.append("&dept_name_like=" + dept_name_like);
		pathBuffer.append("&c_comm=" + c_comm);
		pathBuffer.append("&customer_cate_id=" + customer_cate_id);
		pathBuffer.append("&date_begin=" + date_begin);
		pathBuffer.append("&date_end=" + date_end);
		pathBuffer.append("&yw_name=" + yw_name);
		pathBuffer.append("&is_del=" + is_del);
		pathBuffer.append("&excel_all=" + excel_all);
		pathBuffer.append("&measure_id=" + measure_id);
		pathBuffer.append("&model_id=" + model_id);
		pathBuffer.append("&model_name_like=" + model_name_like);

		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward reStrat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaMobileTestData entity = new KonkaMobileTestData();
		entity.setId(Long.valueOf(id));
		entity.setStatus(0);
		super.getFacade().getKonkaMobileTestDataService().modifyKonkaMobileTestData(entity);
		saveMessage(request, "konka.sail.data.update");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

}