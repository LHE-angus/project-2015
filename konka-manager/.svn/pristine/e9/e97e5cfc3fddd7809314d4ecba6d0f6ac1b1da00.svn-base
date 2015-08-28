package com.ebiz.mmt.web.struts.manager.admin;

import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaPlanMoney;
import com.ebiz.mmt.domain.KonkaPlanRatio;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.TaskContent;
import com.ebiz.mmt.domain.TaskExcelDTO;
import com.ebiz.mmt.domain.TaskParticipant;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class TaskContentAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(
				Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('"
					+ msg + "');history.back();}");
			return null;
		}
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		// 多媒体事业本部 0 系统管理员
		if (userInfo.getDept_id() != 0) {
			konkaDept.setDept_id(userInfo.getDept_id());
		}
		List<KonkaDept> deptList = super.getFacade().getKonkaDeptService()
				.getKonkaDeptList(konkaDept);
		request.setAttribute("deptList", deptList);

		String task_type = (String) dynaBean.get("task_type");
		String ywy_id = (String) dynaBean.get("ywy_id");
		String ywy_name = (String) dynaBean.get("ywy_name");
		String customer_id = (String) dynaBean.get("customer_id");// r3code
		String customer_name = (String) dynaBean.get("customer_name");
		String dept_id = (String) dynaBean.get("dept_id");
		String task_year = (String) dynaBean.get("task_year");
		String task_month = (String) dynaBean.get("task_month");

		TaskContent tc = new TaskContent();

		if (StringUtils.isNotEmpty(task_year)) {
			tc.setTask_year(Integer.valueOf(task_year));
		}
		if (StringUtils.isNotEmpty(task_month)) {
			tc.setTask_month(Integer.valueOf(task_month));
		}
		if (StringUtils.isNotEmpty(task_type)) {
			tc.setTask_p_type(task_type);
		}
		if (StringUtils.isNotEmpty(ywy_id)) {
			tc.getMap().put("ywy_id", ywy_id);
		}
		if (StringUtils.isNotEmpty(ywy_name)) {
			tc.getMap().put("ywy_name", ywy_name);
		}
		if (StringUtils.isNotEmpty(customer_id)) {
			tc.getMap().put("customer_id", customer_id);
		}
		if (StringUtils.isNotEmpty(customer_name)) {
			tc.getMap().put("customer_name", customer_name);
		}
		if (StringUtils.isNotEmpty(dept_id)) {
			tc.getMap().put("dept_id", dept_id);
		}

		Long recordCount = super.getFacade().getTaskContentService()
				.getTaskContentCount(tc);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		tc.getRow().setFirst(pager.getFirstRow());
		tc.getRow().setCount(pager.getRowCount());
		List<TaskContent> list = new ArrayList<TaskContent>();
		list = super.getFacade().getTaskContentService()
				.getTaskContentPaginatedList(tc);
		request.setAttribute("entityList", list);
		request.setAttribute("errorSize", 0);
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(
				Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('"
					+ msg + "');history.back();}");
			return null;
		}
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		// 多媒体事业本部 0 系统管理员
		if (userInfo.getDept_id() != 0) {
			konkaDept.setDept_id(userInfo.getDept_id());
		}
		List<KonkaDept> deptList = super.getFacade().getKonkaDeptService()
				.getKonkaDeptList(konkaDept);
		request.setAttribute("deptList", deptList);
		return mapping.findForward("input");
	}

	// 编辑
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		saveToken(request);
		setNaviStringToRequestScope(form, request);
		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(
				Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('"
					+ msg + "');history.back();}");
			return null;
		}

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		// 多媒体事业本部 0 系统管理员
		if (userInfo.getDept_id() != 0) {
			konkaDept.setDept_id(userInfo.getDept_id());
		}
		List<KonkaDept> deptList = super.getFacade().getKonkaDeptService()
				.getKonkaDeptList(konkaDept);
		request.setAttribute("deptList", deptList);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");// taskContent主键ID not null
		TaskContent tc = new TaskContent();
		tc.setId(Long.valueOf(id));

		tc = super.getFacade().getTaskContentService().getTaskContent(tc);
		super.copyProperties(form, tc);

		return mapping.findForward("input");
	}

	// view
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(
				Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('"
					+ msg + "');history.back();}");
			return null;
		}

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		// 多媒体事业本部 0 系统管理员
		if (userInfo.getDept_id() != 0) {
			konkaDept.setDept_id(userInfo.getDept_id());
		}
		List<KonkaDept> deptList = super.getFacade().getKonkaDeptService()
				.getKonkaDeptList(konkaDept);
		request.setAttribute("deptList", deptList);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");// taskContent主键ID not null
		TaskContent tc = new TaskContent();
		tc.setId(Long.valueOf(id));

		tc = super.getFacade().getTaskContentService().getTaskContent(tc);
		super.copyProperties(form, tc);

		return mapping.findForward("view");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String participant_id = (String) dynaBean.get("participant_id");// taskParticipant的主键ID
		String id = (String) dynaBean.get("id");// taskContent主键ID
		String mod_id = (String) dynaBean.get("mod_id");
		String dept_id = (String) dynaBean.get("dept_id");
		String task_type = (String) dynaBean.get("task_type");
		String ywy_id = (String) dynaBean.get("ywy_id");// 岗位ID
		String ywy_name = (String) dynaBean.get("ywy_name");
		String customer_id = (String) dynaBean.get("customer_id");// r3code
		String customer_name = (String) dynaBean.get("customer_name");
		String task_xs = (String) dynaBean.get("task_xs");
		String task_ed = (String) dynaBean.get("task_ed");
		String task_year = (String) dynaBean.get("task_year");
		String task_month = (String) dynaBean.get("task_month");
		String task_desc = (String) dynaBean.get("task_desc");

		KonkaR3Shop k = new KonkaR3Shop();
		PeProdUser pe = new PeProdUser();
		KonkaDept kd = new KonkaDept();
		kd.setDept_id(Long.valueOf(dept_id));
		kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);

		if (StringUtils.isNotEmpty(customer_id)) {
			k.setR3_code(customer_id);
		//	k.setIs_del(0L);
			k.setBranch_area_name_2(kd.getDept_sn());
			k = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(k);

			if (k == null) {
				saveDirectlyError(request,
						"客户:" + customer_id + "," + kd.getDept_name()
								+ "不存在或已经停用!");
				return mapping.findForward("input");
			}
		}

		if (StringUtils.isNotEmpty(ywy_id)) {
			pe.setJob_id(ywy_id);
			pe.setIs_del(0);
			pe = super.getFacade().getPeProdUserService().getPeProdUser(pe);
			if (pe == null) {
				saveDirectlyError(request, "岗位ID:" + ywy_id
						+ "的业务员不存在或已经停用,请区分大小写!");
				return mapping.findForward("input");
			}
		}

		TaskContent tc = new TaskContent();
		TaskParticipant tp = new TaskParticipant();
		tp.setDept_id(Integer.valueOf(dept_id));

		tp.setDept_code(kd.getDept_sn());
		if (StringUtils.isEmpty(participant_id)) {
			if (StringUtils.isNotEmpty(customer_id)) {
				// r3code-->id ;
				tp.setCustomer_id(k.getId());
				tp.setCustomer_name(customer_name);
			}
			if (StringUtils.isNotEmpty(ywy_id)) {
				tp.setYwy_id(ywy_id);// 业务员岗位ID
				tp.setYwy_name(ywy_name);
			}
			// taskParticipant insert
			Long s = super.getFacade().getTaskParticipantService()
					.createTaskParticipant(tp);
			// taskContent insert
			if (s != null && s != 0l) {
				tc.setParticipant_id(Long.valueOf(s));
				tc.setTask_year(Integer.valueOf(task_year));
				tc.setTask_month(Integer.valueOf(task_month));
				tc.setTask_p_type(task_type);
				tc.setTask_xs(Double.valueOf(task_xs));
				tc.setTask_ed(Double.valueOf(task_ed));
				tc.setTask_desc(task_desc);
				super.getFacade().getTaskContentService().createTaskContent(tc);
			}
			saveMessage(request, "entity.inserted");

		} else {
			if (StringUtils.isNotEmpty(customer_id)) {
				// r3code-->id ;
				tp.setCustomer_id(k.getId());
				tp.setCustomer_name(customer_name);
			}
			if (StringUtils.isNotEmpty(ywy_id)) {
				tp.setYwy_id(ywy_id);// 业务员岗位ID
				tp.setYwy_name(ywy_name);
			}
			tp.setId(Long.valueOf(participant_id));
			// taskParticipant update
			super.getFacade().getTaskParticipantService()
					.modifyTaskParticipant(tp);
			// taskContent update
			tc.setId(Long.valueOf(id));
			tc.setParticipant_id(Long.valueOf(participant_id));
			tc.setTask_year(Integer.valueOf(task_year));
			tc.setTask_month(Integer.valueOf(task_month));
			tc.setTask_p_type(task_type);
			tc.setTask_xs(Double.valueOf(task_xs));
			tc.setTask_ed(Double.valueOf(task_ed));
			tc.setTask_desc(task_desc);
			super.getFacade().getTaskContentService().modifyTaskContent(tc);
			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");// taskContent主键ID
		// String participant_id = (String) dynaBean.get("participant_id");
		if (StringUtils.isNotEmpty(id)) {
			TaskContent tc = new TaskContent();
			tc.setId(Long.valueOf(id));
			tc = super.getFacade().getTaskContentService().getTaskContent(tc);

			TaskParticipant tp = new TaskParticipant();
			tp.setId(tc.getParticipant_id());
			super.getFacade().getTaskParticipantService()
					.removeTaskParticipant(tp);
			super.getFacade().getTaskContentService().removeTaskContent(tc);
			saveMessage(request, "entity.deleted");

		}
		return this.list(mapping, form, request, response);

	}

	public ActionForward add_excel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		return new ActionForward("/admin/TaskContent/form_excel.jsp");
	}

	public ActionForward save_excel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		// int isize = 0;
		// int msize = 0;

		// 上传的excel已经保存服务器某目录,现在读回内存
		List<UploadFile> uploadFilesList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH);
		// 经过UUID文件名运算,只有一份
		if (uploadFilesList.size() == 1) {
			// files/upload/2014/04/09/659234ca-2330-45d7-9a7f-f85864b116b4.xls
			String file_save_path = uploadFilesList.get(0).getFileSavePath();
			// D:\java\server\apache-tomcat-6.0.18\wtpwebapps\konka-wd\
			String ctxDir = getServlet().getServletContext().getRealPath(
					String.valueOf(File.separatorChar));
			
			// 附件保存路径更改遗留问题
			if (StringUtils.contains(file_save_path, MmtFilePathConfig.S_OTHERS_PATH)) {
				ctxDir = "/Attachment_new/konka-files/";
			}


			Workbook workbook = Workbook.getWorkbook(new File(ctxDir
					+ file_save_path));
			Sheet sheet = workbook.getSheet(0);

			List<TaskExcelDTO> excelList = new ArrayList<TaskExcelDTO>();
			List<TaskExcelDTO> errorList = new ArrayList<TaskExcelDTO>();
			List<TaskExcelDTO> returnErrorList = new ArrayList<TaskExcelDTO>();
			excelList = getDataFromExcel(sheet);
			if (excelList == null) {
				super.renderJavaScript(response,
						"window.onload=function(){alert('Excel文件内容为空!');history.back();}");
				return null;
			}

			excelList = removeDuplicate(excelList);// 22 33//excel导入的数据

			// 1校验必须填,并发现错误数据
			for (TaskExcelDTO t1 : excelList) {
				if (StringUtils.isEmpty(t1.getTasktype())
						|| StringUtils.isEmpty(t1.getDeptsn())
						|| t1.getYear() == null || t1.getMonth() == null) {
					t1.setDesc("任务类型,部门,年度,月份 可能为空!");

					errorList.add(t1);
				}
				if (StringUtils.isNotEmpty(t1.getTasktype())
						&& "customer".equals(t1.getTasktype())) {
					if (t1.getCustomercode() == null
							|| "".equals(t1.getCustomercode())) {
						t1.setDesc("客户编码为空!");
						errorList.add(t1);
					} else {
						KonkaR3Shop kk = new KonkaR3Shop();
						//2015年6月18日改为可以是删除状态的客户
						//kk.setIs_del(0l);
						kk.setR3_code(t1.getCustomercode());
						kk = super.getFacade().getKonkaR3ShopService()
								.getKonkaR3Shop(kk);
						if (kk == null) {
							t1.setDesc("错误的客户编码或部门编码!");
							errorList.add(t1);
						}
					}
				}
				if (StringUtils.isNotEmpty(t1.getTasktype())
						&& "ywy".equals(t1.getTasktype())) {
					if (t1.getYwyid() == null || "".equals(t1.getYwyid())) {
						t1.setDesc("业务员为空!");
						errorList.add(t1);
					} else {
						PeProdUser user = new PeProdUser();
						user.setJob_id(t1.getYwyid());
						user.setIs_del(0);
						user = super.getFacade().getPeProdUserService()
								.getPeProdUser(user);
						if (user == null) {
							t1.setDesc("错误的业务员岗位ID!");
							errorList.add(t1);
						}
					}

				}
			}

			// 全部数据正确才会执行数据更改
			if (errorList.size() > 0) {
				for (TaskExcelDTO t1 : errorList) {
					if (t1.getTasktype() != null
							|| !"".equals(t1.getTasktype())) {

						if (t1.getTasktype().equals("customer")) {
							t1.setTasktype("客户");
						}
						if (t1.getTasktype().equals("ywy")) {
							t1.setTasktype("业务员");
						}
					}
					returnErrorList.add(t1);
				}
				saveDirectlyError(request, "EXCEL数据异常,感受不会好了!");
				request.setAttribute("errorList", returnErrorList);
				request.setAttribute("errorSize", returnErrorList.size());
				return mapping.findForward("list");
			}

			// 用于减少查询次数
			String tempDeptCode = "";
			KonkaDept kd = new KonkaDept();
			// 2.重复数据怎么定义 某部门,某年度月份,某业务员,某客户
			for (TaskExcelDTO t2 : excelList) {
				//
				List<TaskContent> list = new ArrayList<TaskContent>();
				TaskContent tc = new TaskContent();
				tc.getMap().put("dept_code", t2.getDeptsn());
				tc.setTask_year(Integer.valueOf(t2.getYear()));
				tc.setTask_month(Integer.valueOf(t2.getMonth()));
				if ("ywy".equals(t2.getTasktype())) {
					tc.setTask_p_type("ywy");
					tc.getMap().put("ywy_id", t2.getYwyid());
				} else {
					tc.setTask_p_type("customer");
					tc.getMap().put("ustomer_code", t2.getCustomercode());
				}

				// 校验是否已经存在taskcontent
				list = super.getFacade().getTaskContentService()
						.getTaskContentList(tc);

				if (list.size() == 1) {
					// update
					if (StringUtils.isNotEmpty(t2.getTaskxs())) {
						tc.setTask_xs(Double.valueOf(t2.getTaskxs()));
					}
					if (StringUtils.isNotEmpty(t2.getTasked())) {
						tc.setTask_ed(Double.valueOf(t2.getTasked()));
					}
					tc.setTask_desc(t2.getDesc());
					tc.setParticipant_id(list.get(0).getParticipant_id());
					tc.setId(list.get(0).getId());
					super.getFacade().getTaskContentService()
							.modifyTaskContent(tc);

				} else if (list.size() == 0) {
					// insert
					Long taskparticipantid = 0L;

					// 参与者是否已经存在
					TaskParticipant tp = new TaskParticipant();
					TaskParticipant tp1 = new TaskParticipant();
					tp.setDept_code(t2.getDeptsn());
					if (!tempDeptCode.equals(t2.getDeptsn())) {
						kd.setDept_sn(t2.getDeptsn());
						kd = super.getFacade().getKonkaDeptService()
								.getKonkaDept(kd);
						tp.setDept_id(Integer.valueOf(String.valueOf(kd
								.getDept_id())));
						// 缓存一次
						tempDeptCode = t2.getDeptsn();
					} else {
						tp.setDept_id(Integer.valueOf(String.valueOf(kd
								.getDept_id())));
					}

					if ("customer".equals(t2.getTasktype())) {
						KonkaR3Shop ks = new KonkaR3Shop();
						ks.setR3_code(t2.getCustomercode());
						ks.setIs_del(0L);
						ks = super.getFacade().getKonkaR3ShopService()
								.getKonkaR3Shop(ks);
						tp.setCustomer_id(ks.getId());
						tp.setCustomer_name(ks.getCustomer_name());
					} else {
						tp.setYwy_id(t2.getYwyid());
						tp.setYwy_name(t2.getYwyname());
					}

					tp1 = super.getFacade().getTaskParticipantService()
							.getTaskParticipant(tp);
					if (tp1 != null) {
						taskparticipantid = tp1.getId();
						tp.setId(tp1.getId());
						super.getFacade().getTaskParticipantService()
								.modifyTaskParticipant(tp);
					} else {
						taskparticipantid = super.getFacade()
								.getTaskParticipantService()
								.createTaskParticipant(tp);
					}

					tc.setParticipant_id(taskparticipantid);
					if (StringUtils.isNotEmpty(t2.getTaskxs())) {
						tc.setTask_xs(Double.valueOf(t2.getTaskxs()));
					}
					if (StringUtils.isNotEmpty(t2.getTasked())) {
						tc.setTask_ed(Double.valueOf(t2.getTasked()));
					}
					tc.setTask_desc(t2.getDesc());
					super.getFacade().getTaskContentService()
							.createTaskContent(tc);
				} else {
					// error
				}
			}
		} else {
			saveDirectlyError(request, "更新失败,请检查EXCEL数据是否符合要求");
		}
		saveDirectlyMessage(request, "更新成功!");
		return this.list(mapping, form, request, response);

	}

	public static List<TaskExcelDTO> getDataFromExcel(Sheet sheet) {

		int rows = sheet.getRows();
		if (rows <= 1)
			return null;
		int s_row = 1;

		List<TaskExcelDTO> list = new ArrayList<TaskExcelDTO>();

		for (int i = s_row; i < rows; i++) {
			if (sheet.getCell(2, i).getContents() == null
					|| sheet.getCell(2, i).getContents() == "null"
					|| "".equals(sheet.getCell(2, i).getContents())) {
				break;
			}
			TaskExcelDTO t = new TaskExcelDTO();
			t.setDeptsn(sheet.getCell(0, i).getContents());
			t.setDeptname(sheet.getCell(1, i).getContents());
			t.setTasktype("客户".equals(sheet.getCell(2, i).getContents()) ? "customer"
					: "ywy");
			t.setCustomercode(sheet.getCell(3, i).getContents());
			t.setCustomername(sheet.getCell(4, i).getContents());
			t.setYwyid(sheet.getCell(5, i).getContents());
			t.setYwyname(sheet.getCell(6, i).getContents());
			t.setTaskxs(sheet.getCell(7, i).getContents());
			t.setTasked(sheet.getCell(8, i).getContents());
			t.setYear(Integer.valueOf(sheet.getCell(9, i).getContents()));
			t.setMonth(Integer.valueOf(sheet.getCell(10, i).getContents()));
			t.setDesc(sheet.getCell(11, i).getContents());

			list.add(t);
		}

		// 死循环,但你必须知道什么时候跳出
		// for (;;) {
		//
		// break;
		// }

		return list;

	}

	public static List<TaskExcelDTO> removeDuplicate(List<TaskExcelDTO> list) {
		HashSet h = new HashSet(list);
		list.clear();
		list.addAll(h);
		return list;
	}

	public ActionForm getTaskEd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		String dept_id = (String) dynaBean.get("dept_id");// 分公司
		String task_year = (String) dynaBean.get("task_year");// 任务年
		String task_month = (String) dynaBean.get("task_month");// 任务月
		String task_xs = (String) dynaBean.get("task_xs");// 任务系数

		String dept_sn="";
		if (StringUtils.isEmpty(dept_id) || StringUtils.isEmpty(task_year)
				|| StringUtils.isEmpty(task_month)
				|| StringUtils.isEmpty(task_xs)) {
			super.renderJavaScript(response, "alert('数据参数不完整')");
			return null;
		}

		KonkaDept dept=new KonkaDept();
		dept.setDept_id(Long.valueOf(dept_id));
		dept=super.getFacade().getKonkaDeptService().getKonkaDept(dept);
		if(null!=dept && null!=dept.getDept_sn()){
			dept_sn=dept.getDept_sn();
		}else{
			super.renderJavaScript(response, "alert('数据参数不完整')");
			return null;
		}
		
		KonkaPlanMoney konkaPlanMoney=new KonkaPlanMoney();
		konkaPlanMoney.setP(1);
		konkaPlanMoney.setY(Integer.parseInt(task_year+""+task_month));
		
		List<KonkaPlanMoney> moneyList=super.getFacade().getKonkaPlanMoneyService().getKonkaPlanMoneyList(konkaPlanMoney);
		if(null!=moneyList && moneyList.size()> 0){
			konkaPlanMoney=moneyList.get(0);
		}else{
			super.renderJavaScript(response, "alert('没有维护总部月额度')");
			return null;
		}
		
		KonkaPlanRatio ratio=new KonkaPlanRatio();
		ratio.setDept_sn(dept_sn);
		ratio.setY(Integer.parseInt(task_year));
		List<KonkaPlanRatio> ratiolist=super.getFacade().getKonkaPlanRatioService().getKonkaPlanRatioList(ratio);
		
		
		if(null!=ratiolist && ratiolist.size()> 0){
			ratio	=ratiolist.get(0);
		}else{
			super.renderJavaScript(response, "alert('没有维护分公司年度额度系数')");
			return null;
		}
		//System.out.println("task_ed:"+ratio.getRatio().multiply(konkaPlanMoney.getM()));
		super.renderJsonp(request, response, ""+ratio.getRatio().multiply(konkaPlanMoney.getM()).
				multiply(new BigDecimal(task_xs)).divide(new BigDecimal(100)));
		return null;
	}

	/**
	 * 客户任务系数统计表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listCustReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(
				Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('"
					+ msg + "');history.back();}");
			return null;
		}
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		// 多媒体事业本部 0 系统管理员
		if (userInfo.getDept_id() != 0) {
			konkaDept.setDept_id(userInfo.getDept_id());
		}
		List<KonkaDept> deptList = super.getFacade().getKonkaDeptService()
				.getKonkaDeptList(konkaDept);
		request.setAttribute("deptList", deptList);

		String dept_id = (String) dynaBean.get("dept_id");// 分公司
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");// 部门
		
		String r3_code_like = (String) dynaBean.get("r3_code_like");// r3code
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String task_year = (String) dynaBean.get("task_year");
		String task_month = (String) dynaBean.get("task_month");
		// 一级客户类型
		String customer_type1 = (String) dynaBean.get("v_customer_type1");
		// 二级客户类型
		String customer_type2 = (String) dynaBean.get("v_customer_type2");
		
		TaskContent entity = new TaskContent();
		Calendar calendar = Calendar.getInstance();
		
		if (StringUtils.isEmpty(task_year)||StringUtils.isEmpty(task_month)) {
			entity.getMap().put("year",""+calendar.get(Calendar.YEAR));
			entity.getMap().put("month","" + (calendar.get(Calendar.MONTH)+1));
			dynaBean.set("task_year", ""+calendar.get(Calendar.YEAR));
			int month=calendar.get(Calendar.MONTH)+1;
			dynaBean.set("task_month", "" + (month>9?month:"0"+month));
		}else {
			entity.getMap().put("year",task_year);
			entity.getMap().put("month",task_month);
			dynaBean.set("task_year", task_year);
			dynaBean.set("task_month", task_month);
		}
		
		if (StringUtils.isNotEmpty(dept_id)) {
			entity.getMap().put("fgs_id", dept_id);
			dynaBean.set("dept_id", dept_id);
		}
		if (StringUtils.isNotEmpty(l4_dept_id)) {
			entity.getMap().put("dept_id", l4_dept_id);
			dynaBean.set("l4_dept_id", l4_dept_id);
		}
		if (StringUtils.isNotEmpty(r3_code_like)) {
			entity.getMap().put("r3_code_like", r3_code_like);
			dynaBean.set("r3_code_like",r3_code_like);
		}
		if (StringUtils.isNotEmpty(customer_name_like)) {
			dynaBean.set("customer_name_like", customer_name_like);
			entity.getMap().put("customer_name_like", customer_name_like);
		}
	
		// 添加客户类型筛选条件
		if (StringUtils.isNotBlank(customer_type2)) {
			entity.getMap().put("cus_type2", customer_type2);
			dynaBean.set("customer_type2", customer_type2);
		} else {
			if (StringUtils.isNotBlank(customer_type1)) {
				entity.getMap().put("cus_type1", customer_type1);
				dynaBean.set("customer_type1", customer_type1);
			}
		}
		Long _dept_id = 0L;
		int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			// break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super
					.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				entity.getMap().put("dept_id_start", _dept_id);
				// entity.getMap().put("fgs_dept_value", _dept_id);
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", _dept_id);
			break;
		case 0:
			_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
			break;
		default:
			return null;
		}
	   entity.getMap().put("session_user_id", userInfo.getId());
		   
		Long recordCount = super.getFacade().getTaskContentService()
				.getTaskReportByCustCount(entity);
		
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
		List<Map<String, String>> list = super.getFacade().getTaskContentService()
				.getTaskReportByCustPaginatedList(entity);
		request.setAttribute("entityList", list);
		
		return new ActionForward("/admin/TaskContent/list_cust_report.jsp");
	
	}
	
	/**
	 * 业务员任务系数统计表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listYwyReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(
				Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('"
					+ msg + "');history.back();}");
			return null;
		}
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		// 多媒体事业本部 0 系统管理员
		if (userInfo.getDept_id() != 0) {
			konkaDept.setDept_id(userInfo.getDept_id());
		}
		List<KonkaDept> deptList = super.getFacade().getKonkaDeptService()
				.getKonkaDeptList(konkaDept);
		request.setAttribute("deptList", deptList);

		String ywy_name = (String) dynaBean.get("ywy_name");
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String task_year = (String) dynaBean.get("task_year");
		String task_month = (String) dynaBean.get("task_month");

		TaskContent tc = new TaskContent();

		if (StringUtils.isNotEmpty(task_year)) {
			tc.setTask_year(Integer.valueOf(task_year));
		}
		if (StringUtils.isNotEmpty(task_month)) {
			tc.setTask_month(Integer.valueOf(task_month));
		}

		if (StringUtils.isNotEmpty(ywy_name)) {
			tc.getMap().put("ywy_name", ywy_name);
		}

		if (StringUtils.isNotEmpty(l5_dept_id)) {
			tc.getMap().put("l5_dept_id", l5_dept_id);
		} else if (StringUtils.isNotEmpty(l4_dept_id)) {
			tc.getMap().put("l4_dept_id", l4_dept_id);
		} else if (StringUtils.isNotEmpty(dept_id)) {
			tc.getMap().put("dept_id", dept_id);
		}
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade()
				.getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		Boolean role_id_lt_30 = false;
		List<Long> role_ids = new ArrayList<Long>(); // 当前登录用户的角色列表
		for (PeRoleUser peRoleUser : peRoleUserList) {
			role_ids.add(peRoleUser.getRole_id());

			if (peRoleUser.getRole_id() >= 0L && peRoleUser.getRole_id() < 30L) {
				role_id_lt_30 = true;
				continue;
			}
		}
		if (!role_id_lt_30) {
			Long __dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
			request.setAttribute("max_dlevel", max_dlevel);
			logger.info("Max level : {}", max_dlevel);
			switch (max_dlevel) {
			case 9:
				// 集团及以下部门可见
				__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
				break;
			case 8:
				// 分公司及以下部门可见
				KonkaDept dept_fgs = super.getKonkaDeptForFgs(Long
						.valueOf(__dept_id)); // 查询部门分公司
				if (null != dept_fgs && null != dept_fgs.getDept_id()) {
					__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
					tc.getMap().put("dept_id_in", __dept_id);
					request.setAttribute("current_fgs_code", __dept_id);
					request.setAttribute("show_fgs", true);
				}
				break;
			case 7:
				// 我所在的部门及以下部门可见
				__dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
				tc.getMap().put("dept_id_in", __dept_id);
				request.setAttribute("current_fgs_code", __dept_id);
				request.setAttribute("current_dept_code", __dept_id);
				request.setAttribute("show_fgs", true);
				request.setAttribute("show_fgs_jb", true);
				break;
			case 0:
				tc.getMap().put("querybycust_userid_eq", userInfo.getId());
				request.setAttribute("show_fgs_gr", true);
				request.setAttribute("show_fgs", true);
				request.setAttribute("show_fgs_jb", true);
				break;
			default:
				// 出错
			}

		}
		tc.setTask_p_type("ywy");//只有是业务员的了类型的
		Long recordCount = super.getFacade().getTaskContentService()
				.getTaskContentYwyReportCount(tc);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		tc.getRow().setFirst(pager.getFirstRow());
		tc.getRow().setCount(pager.getRowCount());
		List<TaskContent> list = new ArrayList<TaskContent>();
		list = super.getFacade().getTaskContentService()
				.getTaskContentYwyReportPaginatedList(tc);
		request.setAttribute("entityList", list);
		request.setAttribute("errorSize", 0);
		return new ActionForward(
				"/../manager/admin/TaskContent/listYwyReport.jsp");

	}
	
}
