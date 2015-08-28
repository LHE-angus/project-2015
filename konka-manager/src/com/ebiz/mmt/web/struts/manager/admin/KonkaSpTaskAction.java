package com.ebiz.mmt.web.struts.manager.admin;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaPlanRatio;
import com.ebiz.mmt.domain.KonkaSpActivityManager;
import com.ebiz.mmt.domain.KonkaSpTask;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class KonkaSpTaskAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		dynaBean.set("year", String.valueOf(calendar.get(Calendar.YEAR)));

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
			} else {
				if (konkaDept.getDept_type() !=null && konkaDept.getDept_type() == 3) {
				// 分公司
				KonkaDept dept_fgs = super.getKonkaDeptForFgs(peProdUser.getDept_id()); // 查询部门分公司
				request.setAttribute("dept_fgs", dept_fgs);
				dynaBean.set("fgs_sn", konkaDept.getDept_sn());
				dynaBean.set("dept_type", "3");
				} else if (konkaDept.getDept_type() !=null && (konkaDept.getDept_type() == 4 || konkaDept.getDept_type() == 5)) {
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
		}
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);//导航
		super.getModPopeDom(form, request);//操作权限

		DynaBean dynaBean = (DynaBean) form;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		dynaBean.set("this_year", String.valueOf(calendar.get(Calendar.YEAR)));
		Pager pager = (Pager) dynaBean.get("pager");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String fgs_sn = (String) dynaBean.get("fgs_sn");
		String dept_type = (String) dynaBean.get("dept_type");

		KonkaSpTask entity = new KonkaSpTask();
		if (StringUtils.isNotBlank(year)) {
			entity.setYear(Integer.valueOf(year));
		}

		if (StringUtils.isNotBlank(month)) {
			entity.setMonth(Integer.valueOf(month));
		}

		if (StringUtils.isNotBlank(fgs_sn)) {
			entity.setDept_sn(fgs_sn);
		}
		if (StringUtils.isNotBlank(dept_type) && "3".equals(dept_type)) {
			PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
			if (null != peProdUser) {
				KonkaDept konkaDept = new KonkaDept();
				konkaDept.setDept_id(peProdUser.getDept_id());
				konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
				if (null != konkaDept) {
					entity.setDept_sn(konkaDept.getDept_sn());
					dynaBean.set("dept_sn", konkaDept.getDept_sn());
				}
			}

		}

		Long recordCount = super.getFacade().getKonkaSpTaskService().getKonkaSpTaskCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaSpTask> entityList = super.getFacade().getKonkaSpTaskService().getKonkaSpTaskPaginatedList(entity);
		
		//查询达标场次和开展场次--START--
		for(KonkaSpTask kst: entityList){
			KonkaSpActivityManager spActivityManager = new KonkaSpActivityManager();
			spActivityManager.setIs_del(0);
			spActivityManager.setFile_status(3);
			spActivityManager.getMap().put("hd_status_ne_num", 0);
			spActivityManager.setDept_id(kst.getDept_id());
			spActivityManager.getMap().put("add_sp_date_k", kst.getYear()+"-"+kst.getMonth()+"-01 00:00:00");
			spActivityManager.getMap().put("add_sp_date_je", kst.getYear()+"-"+(kst.getMonth()+1)+"-01 00:00:00");
			long kzcc = super.getFacade().getKonkaSpActivityManagerService().getKonkaSpActivityManagerCount(spActivityManager);
			kst.getMap().put("kzcc", kzcc);
			
			spActivityManager.getMap().put("cmp_total_sail_money", kst.getTask_money());
			long dbcc = super.getFacade().getKonkaSpActivityManagerService().getKonkaSpActivityManagerCount(spActivityManager);
			kst.getMap().put("dbcc", dbcc);
			if(kst.getTask_money()!=null&&kst.getTask_money().intValue()!=0){
				BigDecimal b_dbcc = new BigDecimal(dbcc);
				kst.getMap().put("ccwcl", b_dbcc.multiply(new BigDecimal(100)).divide(kst.getTask_money(),2, BigDecimal.ROUND_HALF_EVEN));
			} else {
				kst.getMap().put("ccwcl", 0);
			}
		}
		//查询达标场次和开展场次--END--
		
		request.setAttribute("entityList", entityList);

//		KonkaDept d = new KonkaDept();
//		d.setDept_type(3);
//		// d.getMap().put("order_by_dept_name", "not_empty");
//		// d.getMap().put("dept_sn_not_null", "not_empty");
//		List<KonkaDept> deptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(d);
//		request.setAttribute("deptList", deptList);
		request.setAttribute("deptList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		// KonkaPlanMoney entity = new KonkaPlanMoney();
		// // the line below is added for pagination
		// entity.setQueryString(super.serialize(request, "mod_id", "year", "fgs_sn"));
		// // end
		//
		// super.copyProperties(form, entity);

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String year = (String) dynaBean.get("year");

		KonkaSpTask entity = new KonkaSpTask();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(new Long(id));
		}
		if (StringUtils.isNotBlank(year)) {
			entity.setYear(Integer.valueOf(year));
		}
		entity = super.getFacade().getKonkaSpTaskService().getKonkaSpTask(entity);

		// the line below is added for pagination
		entity.setQueryString(super.serialize(request, "mod_id", "year", "fgs_sn"));
		// end

		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		// String year = (String) dynaBean.get("year");
		String dept_type = (String) dynaBean.get("dept_type");
		String task_money = (String) dynaBean.get("task_money");
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		KonkaSpTask entity = new KonkaSpTask();
		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(task_money)) {
			entity.setTask_money(new BigDecimal(task_money));
		}
		if(peProdUser!=null){
			entity.setUpdate_user(peProdUser.getUser_name());
			entity.setUpdate_user_job_id(peProdUser.getJob_id());
			entity.setUpdate_date(new Date());
		}

		if (StringUtils.isNotBlank(id)) {
			// update
			if (null == super.checkUserModPopeDom(form, request, "2")) {
				return super.checkPopedomInvalid(request, response);
			}
			super.getFacade().getKonkaSpTaskService().modifyKonkaSpTask(entity);
			saveMessage(request, "entity.updated");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&dept_type=" + dept_type);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaSpTask entity = new KonkaSpTask();
			entity.setId(Long.valueOf(id));
			getFacade().getKonkaSpTaskService().removeKonkaSpTask(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaSpTask entity = new KonkaSpTask();
			entity.getMap().put("pks", pks);
			for (String xx : pks) {
				entity.setId(Long.valueOf(xx));
				getFacade().getKonkaSpTaskService().removeKonkaSpTask(entity);
			}
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward add_jb(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		KonkaDept d = new KonkaDept();
		d.setDept_type(3);
		d.getMap().put("order_by_dept_name", "not_empty");
		d.getMap().put("dept_sn_not_null", "not_empty");
		List<KonkaDept> deptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(d);
		request.setAttribute("deptList", deptList);

		// KonkaPlanRatio entity = new KonkaPlanRatio();
		// // the line below is added for pagination
		// entity.setQueryString(super.serialize(request, "mod_id", "year", "fgs_sn"));
		// // end
		//
		// super.copyProperties(form, entity);

		return new ActionForward("/admin/KonkaPlanRatio/form_jb.jsp");
	}

	public ActionForward save_jb(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String y = (String) dynaBean.get("y");
		String fgs_sn = (String) dynaBean.get("fgs_sn");
		String dept_sn = (String) dynaBean.get("dept_sn");
		String dept_type = (String) dynaBean.get("dept_type");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		String this_year = String.valueOf(calendar.get(Calendar.YEAR));
		if (!this_year.equals(y)) {
			String msg = "系统当前年度是：" + this_year + "年，不能操作其他年度的任务总额记录！";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaPlanRatio entity = new KonkaPlanRatio();
		super.copyProperties(entity, form);

		if (StringUtils.isBlank(id)) {
			if (StringUtils.isBlank(fgs_sn)) {
				String msg = "分公司编码为空，请重新选择！";
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else if (StringUtils.isBlank(dept_sn)) {
				// 判断分公司的任务系数是否已存在
				KonkaPlanRatio kpr = new KonkaPlanRatio();
				kpr.getMap().put("fgs_dept", "true");
				kpr.setFgs_sn(fgs_sn);
				kpr.setY(Integer.valueOf(y));
				Long recordCount = super.getFacade().getKonkaPlanRatioService().getKonkaPlanRatioCount(kpr);
				if (recordCount > 0) {
					super.renderJavaScript(response, "window.onload=function(){alert('" + y
							+ "年度，该分公司的任务系数已存在！');history.back();}");
					return null;
				}

				KonkaDept k = new KonkaDept();
				k.setDept_sn(fgs_sn);
				List<KonkaDept> list = super.getFacade().getKonkaDeptService().getKonkaDeptList(k);
				if (null != list && list.size() > 0) {
					k = list.get(0);
					entity.setDept_name(k.getDept_name());
				}
				entity.setDept_sn(fgs_sn);
			} else {
				// 判断经营部的任务系数是否已存在
				KonkaPlanRatio kpr = new KonkaPlanRatio();
				kpr.setDept_sn(dept_sn);
				kpr.setY(Integer.valueOf(y));
				Long recordCount = super.getFacade().getKonkaPlanRatioService().getKonkaPlanRatioCount(kpr);
				if (recordCount > 0) {
					super.renderJavaScript(response, "window.onload=function(){alert('" + y
							+ "年度，该经办的任务系数已存在！');history.back();}");
					return null;
				}

				KonkaDept k = new KonkaDept();
				k.setDept_sn(dept_sn);
				List<KonkaDept> list = super.getFacade().getKonkaDeptService().getKonkaDeptList(k);
				if (null != list && list.size() > 0) {
					k = list.get(0);
					entity.setDept_name(k.getDept_name());
				}
			}

			if (null == super.checkUserModPopeDom(form, request, "1")) {
				return super.checkPopedomInvalid(request, response);
			}
			super.getFacade().getKonkaPlanRatioService().createKonkaPlanRatio(entity);
			saveMessage(request, "entity.inserted");
		} else {
			// update
			if (null == super.checkUserModPopeDom(form, request, "2")) {
				return super.checkPopedomInvalid(request, response);
			}
			super.getFacade().getKonkaPlanRatioService().modifyKonkaPlanRatio(entity);
			saveMessage(request, "entity.updated");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append("/admin/KonkaPlanRatio.do?method=list");
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&dept_type=" + dept_type);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward list_jb(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		dynaBean.set("this_year", String.valueOf(calendar.get(Calendar.YEAR)));

		String y = (String) dynaBean.get("y");
		String fgs_sn = (String) dynaBean.get("fgs_sn");
		KonkaPlanRatio k = new KonkaPlanRatio();
		if (StringUtils.isNotBlank(y)) {
			k.setY(Integer.valueOf(y));
		}
		if (StringUtils.isNotBlank(fgs_sn)) {
			k.setFgs_sn(fgs_sn);
		}
		List<KonkaPlanRatio> entityList = super.getFacade().getKonkaPlanRatioService().getKonkaPlanRatioList(k);
		request.setAttribute("entityList", entityList);
		return new ActionForward("/admin/KonkaPlanRatio/list_jb.jsp");
	}

	public ActionForward edit_jb(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String y = (String) dynaBean.get("y");

		KonkaPlanRatio t = new KonkaPlanRatio();
		if (StringUtils.isNotBlank(id)) {
			t.setId(new Long(id));
		}
		if (StringUtils.isNotBlank(y)) {
			t.setY(Integer.valueOf(y));
		}
		List<KonkaPlanRatio> list = super.getFacade().getKonkaPlanRatioService().getKonkaPlanRatioList(t);
		if (null != list && list.size() > 0) {
			KonkaPlanRatio entity = new KonkaPlanRatio();
			entity = list.get(0);
			// the line below is added for pagination
			entity.setQueryString(super.serialize(request, "mod_id", "year", "fgs_sn"));
			// end

			super.copyProperties(form, entity);
			request.setAttribute("konkaPlanRatio", entity);
		} else {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

		KonkaDept d = new KonkaDept();
		d.setDept_type(3);
		d.getMap().put("order_by_dept_name", "not_empty");
		d.getMap().put("dept_sn_not_null", "not_empty");
		List<KonkaDept> deptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(d);
		request.setAttribute("deptList", deptList);

		return new ActionForward("/admin/KonkaPlanRatio/form_jb.jsp");
	}

	public ActionForward add_excel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}

		return new ActionForward("/admin/KonkaSpTask/form_excel.jsp");
	}

	public ActionForward save_excel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		

		List<UploadFile> uploadFilesList = super.uploadFile(form, MmtFilePathConfig.MD_PATH);
		if (uploadFilesList.size() == 1) {
			String file_save_path = uploadFilesList.get(0).getFileSavePath();
			String ctxDir = getServlet().getServletContext().getRealPath(String.valueOf(File.separatorChar));
			// 附件保存路径更改遗留问题
			if (StringUtils.contains(file_save_path, MmtFilePathConfig.S_MD_PATH)) {
				ctxDir = "/Attachment_new/konka-files/";
			}

			try {
				Workbook workbook = Workbook.getWorkbook(new File(ctxDir + file_save_path));
				Sheet sheet = workbook.getSheet(0);
				int rows = sheet.getRows();
				if (rows <= 1) {
					super.renderJavaScript(response, "window.onload=function(){alert('Excel文件内容为空！');history.back();}");
					return null;
				}
				int start_row = 1;
				int end_row = rows;
				List<KonkaSpTask> entityList = new ArrayList<KonkaSpTask>();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				for (int i = start_row; i < end_row; i++) {
					KonkaSpTask entity = new KonkaSpTask();
					entity.setAdd_user(peProdUser.getUser_name());
					entity.setAdd_user_job_id(peProdUser.getJob_id());
					if (StringUtils.isNotBlank(sheet.getCell(1, i).getContents())) {
						if (sheet.getCell(1, i).getContents().length() > 30) {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
									+ "行，分公司名称长度大于30！');history.back();}");
							return null;
						} else {
							KonkaDept dept = new KonkaDept();
							dept.setDept_name(sheet.getCell(1, i).getContents());
							dept.setDept_type(3);//分公司
							List<KonkaDept> listDept = super.getFacade().getKonkaDeptService().getKonkaDeptList(dept);
							if(listDept!=null && listDept.size()==1){
								if(listDept.get(0).getDept_name().equals(sheet.getCell(1, i).getContents())){
									entity.setDept_name(sheet.getCell(1, i).getContents());
									entity.setDept_sn(listDept.get(0).getDept_sn());
									entity.setDept_id(listDept.get(0).getDept_id());
								} else {
									super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
											+ "行，分公司名称不准确或者不存在！');history.back();}");
									return null;
								}
							} else {
								super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
										+ "行，分公司名称不准确或者不存在！');history.back();}");
								return null;
							}
							
						}
						
						
					} else {
						super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1) + "行，分公司名称为空！');history.back();}");
						return null;
					}
					if (StringUtils.isNotBlank(sheet.getCell(2, i).getContents())) {
						// (0 列，i行) --- 年度
						// 验证年份
						String regYear = "[0-9]{4}+";// 正则表达示:四位的数字
						if ((sheet.getCell(2, i).getContents()).matches(regYear)) {
							entity.setYear(Integer.valueOf(sheet.getCell(2, i).getContents()));
						} else {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1) + "行，年份不是"
									+ "年份！');history.back();}");
							return null;
						}
					} else {
						super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
								+ "行，年度为空！');history.back();}");
						return null;
					}
					if (StringUtils.isNotBlank(sheet.getCell(3, i).getContents())) {
						// 验证月份
						String regMonth = "[0-9]{1,2}+";// 正则表达示:二位的数字
						if ((sheet.getCell(3, i).getContents()).matches(regMonth)) {
							entity.setMonth(Integer.valueOf(sheet.getCell(3, i).getContents()));
						} else {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1) + "行，月份不是"
									+ "月份！');history.back();}");
							return null;
						}
					} else {
						super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
								+ "行，月份为空！');history.back();}");
						return null;
					}
					if (StringUtils.isNotBlank(sheet.getCell(4, i).getContents())) {
						Pattern pattern = Pattern.compile("[0-9]*");
						if (pattern.matcher(sheet.getCell(4, i).getContents()).matches()) {
							entity.setTask_money(new BigDecimal(sheet.getCell(4, i).getContents()));
						} else {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
									+ "行，计划任务不是数字')}");
							return null;
						}
					} else {
						super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1) + "行，计划任务为空')}");
						return null;
					}
					entityList.add(entity);
				}

				// 插入数据库

				super.getFacade().getKonkaSpTaskService().createKonkaSpTaskForExcel(entityList);
				saveMessage(request, "entity.inserted");
				// the line below is added for pagination
				StringBuffer pathBuffer = new StringBuffer();
//				pathBuffer.append(mapping.findForward("success").getPath());
				pathBuffer.append("/admin/KonkaSpTask.do");
				pathBuffer.append("?mod_id=" + mod_id);
				// pathBuffer.append("&year=" + year);
				// pathBuffer.append("&month=" + month);
				// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
				ActionForward forward = new ActionForward(pathBuffer.toString(), true);
				// end
				return forward;

			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('Excel文件未选择！');history.back();}");
			return null;
		}
	}
	
	public ActionForward save_excel1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		List<UploadFile> uploadFilesList = super.uploadFile(form, MmtFilePathConfig.MD_PATH);
		if (uploadFilesList.size() == 1) {
			String file_save_path = uploadFilesList.get(0).getFileSavePath();
			String ctxDir = getServlet().getServletContext().getRealPath(String.valueOf(File.separatorChar));
			// 附件保存路径更改遗留问题
			if (StringUtils.contains(file_save_path, MmtFilePathConfig.S_MD_PATH)) {
				ctxDir = "/Attachment_new/konka-files/";
			}

			try {
				Workbook workbook = Workbook.getWorkbook(new File(ctxDir + file_save_path));
				Sheet sheet = workbook.getSheet(0);
				int rows = sheet.getRows();
				if (rows <= 1) {
					super.renderJavaScript(response, "window.onload=function(){alert('Excel文件内容为空！');history.back();}");
					return null;
				}
				int start_row = 1;
				int end_row = rows;
				List<KonkaSpTask> entityList = new ArrayList<KonkaSpTask>();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				for (int i = start_row; i < end_row; i++) {
					KonkaSpTask entity = new KonkaSpTask();
					if (StringUtils.isNotBlank(sheet.getCell(1, i).getContents())) {
						if (sheet.getCell(1, i).getContents().length() > 30) {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
									+ "行，分公司名称长度大于30！');history.back();}");
							return null;
						}
						entity.setDept_name(sheet.getCell(1, i).getContents());
					} else {
						super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1) + "行，分公司名称为空！');history.back();}");
						return null;
					}
					if (StringUtils.isNotBlank(sheet.getCell(2, i).getContents())) {
						// (2列，i行) --- 分公司编码
						if (sheet.getCell(2, i).getContents().length() > 30) {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
									+ "行，分公司编码长度大于30！');history.back();}");
							return null;
						}
						KonkaDept k = new KonkaDept();
						k.setDept_sn(sheet.getCell(2, i).getContents());
						k.setDept_type(3);
						List<KonkaDept> list = super.getFacade().getKonkaDeptService().getKonkaDeptList(k);
						if (null == list || list.size() == 0) {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
									+ "行，分公司编码在系统中不存在！');history.back();}");
							return null;
						}
						entity.setDept_sn(sheet.getCell(2, i).getContents());
					} else {
						super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
								+ "行，分公司编码为空！');history.back();}");
						return null;
					}
					if (StringUtils.isNotBlank(sheet.getCell(3, i).getContents())) {
						// (0 列，i行) --- 年度
						// 验证年份
						String regYear = "[0-9]{4}+";// 正则表达示:四位的数字
						if ((sheet.getCell(3, i).getContents()).matches(regYear)) {
							entity.setYear(Integer.valueOf(sheet.getCell(3, i).getContents()));
						} else {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1) + "行，年份不是"
									+ "年份！');history.back();}");
							return null;
						}
					} else {
						super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
								+ "行，年度为空！');history.back();}");
						return null;
					}
					if (StringUtils.isNotBlank(sheet.getCell(4, i).getContents())) {
						// 验证月份
						String regMonth = "[0-9]{1,2}+";// 正则表达示:二位的数字
						if ((sheet.getCell(4, i).getContents()).matches(regMonth)) {
							entity.setMonth(Integer.valueOf(sheet.getCell(4, i).getContents()));
						} else {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1) + "行，月份不是"
									+ "月份！');history.back();}");
							return null;
						}
					} else {
						super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
								+ "行，月份为空！');history.back();}");
						return null;
					}
					if (StringUtils.isNotBlank(sheet.getCell(5, i).getContents())) {
						Pattern pattern = Pattern.compile("[0-9]*");
						if (pattern.matcher(sheet.getCell(5, i).getContents()).matches()) {
							entity.setTask_money(new BigDecimal(sheet.getCell(5, i).getContents()));
						} else {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
									+ "行，计划任务不是数字')}");
							return null;
						}
					} else {
						super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1) + "行，计划任务为空')}");
						return null;
					}
					entityList.add(entity);
				}

				// 插入数据库
				if (null != entityList && entityList.size() > 0) {
					for (int i = 0; i < entityList.size(); i++) {
						KonkaSpTask k = entityList.get(i);
						log.info("****************第" + (i + 2) + "行数据：分公司名称" + k.getDept_name() + ",分公司编码："
								+ k.getDept_sn() + ",年份：" + k.getYear() + ",月份：" + "计划任务：" + k.getTask_money());
					}
				}

//				super.getFacade().getKonkaSpTaskService().createKonkaSpTaskForExcel(entityList);
				saveMessage(request, "entity.inserted");
				// the line below is added for pagination
				StringBuffer pathBuffer = new StringBuffer();
				pathBuffer.append(mapping.findForward("success").getPath());
				pathBuffer.append("&mod_id=" + mod_id);
				// pathBuffer.append("&year=" + year);
				// pathBuffer.append("&month=" + month);
				// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
				ActionForward forward = new ActionForward(pathBuffer.toString(), true);
				// end
				return forward;

			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('Excel文件未选择！');history.back();}");
			return null;
		}
	}

	public ActionForward getKonkaDeptForFgsSn(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String fgs_sn = (String) dynaBean.get("fgs_sn");
		KonkaDept kd = new KonkaDept();
		kd.setDept_sn(fgs_sn);
		kd.setDept_type(3);
		List<KonkaDept> list = super.getFacade().getKonkaDeptService().getKonkaDeptList(kd);

		String par_id = "";
		if (null != list && list.size() > 0) {
			par_id = String.valueOf(list.get(0).getDept_id());
		}

		StringBuffer sb = new StringBuffer("{");
		sb = sb.append("\"list\":[");
		if (StringUtils.isBlank(par_id)) {
			sb = sb.append("]}");
			super.renderJson(response, sb.toString());
			return null;
		}

		KonkaDept dept = new KonkaDept();
		if (null != par_id) {
			dept.setPar_id(new Long(par_id));
		}
		dept.getMap().put("order_by_dept_name", "no_empty");
		dept.getMap().put("dept_sn_not_null", "not_empty");
		List<KonkaDept> entityList = super.getFacade().getKonkaDeptService().getKonkaDeptList(dept);

		if (null != entityList && entityList.size() > 0) {
			for (KonkaDept entity : entityList) {
				sb = sb.append("{");
				sb = sb.append("\"dept_sn\":\"").append(entity.getDept_sn()).append("\",");
				sb = sb.append("\"dept_name\":\"").append(entity.getDept_name()).append("\"");
				sb = sb.append("},");
			}
		}

		String sb_str = StringUtils.removeEnd(sb.toString(), ",") + "]}";
		logger.info("sb_str {}", sb_str);
		super.renderJson(response, sb_str);
		return null;
	}

	public ActionForward CheckKonkaDeptForDeptSn(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String dept_sn = (String) dynaBean.get("dept_sn");
		KonkaDept k = new KonkaDept();
		k.setDept_sn(dept_sn);
		List<KonkaDept> list = super.getFacade().getKonkaDeptService().getKonkaDeptList(k);
		if (null != list && list.size() > 0) {
			super.render(response, "1", "text/x-json;charset=UTF-8");
		} else {
			super.render(response, "0", "text/x-json;charset=UTF-8");
		}
		return null;
	}

}
