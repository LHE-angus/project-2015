package com.ebiz.mmt.web.struts.manager.oa;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaExpenseClaims;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaoaCategory;
import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Chen,Shunhua
 * @version Build 2010-12-16
 */
public class ExpenseQueryAction extends BaseMmtoaAction {
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

		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		DynaBean dynaBean = (DynaBean) form;
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		String map_file_status = (String) dynaBean.get("map_file_status");
		Pager pager = (Pager) dynaBean.get("pager");
		String r3_code = (String) dynaBean.get("r3_code");
		String customer_name = (String) dynaBean.get("customer_name");
		String r3_shop_id = null;
		String fgs_dept_id = (String) dynaBean.get("fgs_dept_id");
		String jyb_dept_id = (String) dynaBean.get("jyb_dept_id");
		String bsc_dept_id = (String) dynaBean.get("bsc_dept_id");
		String ywy_user_id = (String) dynaBean.get("ywy_user_id");
		String outexcl = (String) dynaBean.get("outexcl");
		KonkaR3Shop r3 = new KonkaR3Shop();
		if (StringUtils.isNotBlank(r3_code)) {
			r3.getMap().put("r3_code_like", r3_code);
		}
		if (StringUtils.isNotBlank(customer_name)) {
			r3.getMap().put("customer_name_like", customer_name);
		}
		if (StringUtils.isNotBlank(r3_code) || StringUtils.isNotBlank(customer_name)) {
			List<KonkaR3Shop> r3list = getFacade().getKonkaR3ShopService().getKonkaR3ShopList(r3);
			if (r3list.size() != 0) {
				r3_shop_id = r3list.get(0).getId().toString();
			}
		}

		KonkaoaFiles entity = new KonkaoaFiles();
		super.copyProperties(entity, form);
		if (ui.getDept_id() != 17761) {// 信息中心可以查看所有的文件
			// entity.getMap().put("audit_user_id", ui.getId());
			// entity.getMap().put("is_xxzx", "true");
		}
		entity.setFile_type(1);// 0文件提交 1费用申请
		// entity.getMap().put("lt_file_status", 3);
		entity.getMap().put("map_file_status", map_file_status);
		entity.getMap().put("file_title_like", dynaBean.get("file_title_like"));
		entity.getMap().put("st_submit_datetime", dynaBean.get("st_submit_datetime"));
		entity.getMap().put("en_submit_datetime", dynaBean.get("en_submit_datetime"));

		entity.getMap().put("st_archive_datetime", dynaBean.get("st_archive_datetime"));
		entity.getMap().put("en_archive_datetime", dynaBean.get("en_archive_datetime"));

		entity.getMap().put("submit_user_like", dynaBean.get("submit_user_like"));
		entity.getMap().put("r3_shop_id", r3_shop_id);
		entity.getMap().put("shopIds", null);
		entity.setIs_del(0);
		logger.info("fgs_dept_id============{}", fgs_dept_id);
		if (GenericValidator.isLong(fgs_dept_id)) {
			entity.getMap().put("konka_dept_id", fgs_dept_id);
		}
		if (GenericValidator.isLong(jyb_dept_id)) {
			entity.getMap().put("konka_dept_id", jyb_dept_id);
		}
		if (GenericValidator.isLong(bsc_dept_id)) {
			entity.getMap().put("konka_dept_id", bsc_dept_id);
		}
		if (GenericValidator.isLong(ywy_user_id)) {
			entity.getMap().put("ywy_user_id", ywy_user_id);
		}
		// 数据级别判断开始
		Long __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			entity.getMap().put("ywy_user_id", ui.getId());
			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				entity.getMap().put("user_dept_id", __dept_id);

			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("user_dept_id", __dept_id);

			break;
		case 0:
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("ywy_user_id", ui.getId());

			break;
		default:
			// 出错
		}

		Long recordCount = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesListForAuditIngCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaoaFiles> entityList = super.getFacade().getKonkaoaFilesService()
				.getKonkaoaFilesPaginatedListForAuditIng(entity);

		for (KonkaoaFiles t : entityList) {
			KonkaExpenseClaims claims = new KonkaExpenseClaims();
			claims.setFile_id(t.getId());
			claims = getFacade().getKonkaExpenseClaimsService().getKonkaExpenseClaims(claims);
			if (null != claims && null != claims.getR3_shop_id()) {
				t.getMap().put("column_6", claims.getColumn_6());

				KonkaR3Shop r3Shop = new KonkaR3Shop();
				r3Shop.setId(claims.getR3_shop_id());
				r3Shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3Shop);

				if (null != r3Shop) {
					t.getMap().put("r3_code", r3Shop.getR3_code());
					t.getMap().put("r3_shop_name", r3Shop.getCustomer_name());
				}
				if (null != t.getArchive_datetime()) {
					t.getMap().put("archive_datetime", s.format(t.getArchive_datetime()));
				}
			}
		}
		// 导出
		if (StringUtils.isNotBlank(outexcl) && "1".equals(outexcl)) {
			if (recordCount.intValue() > 20000) {
				renderJavaScript(response,
						"alert('" + super.getMessage(request, "export.too.many") + "！');history.back();");
				return null;
			}
            String filename = "费用查询";
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + EncryptUtils.encodingFileName(filename) + ".xls");
			entity.getRow().setFirst(0);
			entity.getRow().setCount(recordCount.intValue());
            entityList = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesPaginatedListForAuditIng(entity);
			request.setAttribute("allList", entityList);
			Integer count = entityList.size();
			if (count != null && count > 0) {
				return new ActionForward("/oa/ExpenseQuery/list_report.jsp");
			} else {
				return null;
			}
		}

		KonkaoaCategory category = new KonkaoaCategory();
		category.setIs_del(0);
		category.setC_type(11);
		request.setAttribute("category11List",
				getFacade().getKonkaoaCategoryService().getKonkaoaCategoryListForFiles(category));
		category.setC_type(12);
		request.setAttribute("category12List",
				getFacade().getKonkaoaCategoryService().getKonkaoaCategoryListForFiles(category));

		request.setAttribute("entityList", entityList);

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		dynaBean.set("fgs_dept_id", fgs_dept_id);
		dynaBean.set("jyb_dept_id", jyb_dept_id);
		dynaBean.set("bsc_dept_id", bsc_dept_id);
		dynaBean.set("ywy_user_id", ywy_user_id);

		return mapping.findForward("list");
	}

	public ActionForward download(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		DynaBean dynaBean = (DynaBean) form;
		String[] pks = (String[]) dynaBean.get("pks");
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		if (!ArrayUtils.isEmpty(pks)) {
			KonkaoaFiles entity = new KonkaoaFiles();
			if (ui.getDept_id() != 17761) {// 信息中心可以查看所有的文件
				entity.getMap().put("audit_user_id", ui.getId());
				entity.getMap().put("is_xxzx", "true");
			}
			entity.getMap().put("dept_id", ui.getDept_id());// 默认是查当前部门所有的费用申请表
			entity.getMap().put("pks", pks);
			entity.getRow().setFirst(0);
			entity.getRow().setCount(pks.length);
			List<KonkaoaFiles> entityList = super.getFacade().getKonkaoaFilesService()
					.getKonkaoaFilesPaginatedListForAuditIng(entity);

			WritableWorkbook wwb = null;
			String excel_name = "ExpenseQuery.xls";

			try {
				// 获取系统实际路径
				String SystemPath = getServlet().getServletContext().getRealPath(String.valueOf(File.separatorChar));

				// 首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
                wwb = Workbook.createWorkbook(new File(SystemPath + "files/download-template/" + excel_name));
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 创建一个可写入的工作表
			// Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置
			WritableSheet ws = wwb.createSheet("sheet1", 0);
			if (wwb != null) {
				ws.addCell(new Label(0, 0, "序号"));
				ws.addCell(new Label(1, 0, "文件编号"));
				ws.addCell(new Label(2, 0, "文件标题"));
				ws.addCell(new Label(3, 0, "R3网点名称"));
				ws.addCell(new Label(4, 0, "R3编码"));
				ws.addCell(new Label(5, 0, "费用总额"));
				ws.addCell(new Label(6, 0, "申请部门"));
				ws.addCell(new Label(7, 0, "申请人"));
				ws.addCell(new Label(8, 0, "申请时间"));
				ws.addCell(new Label(9, 0, "当前审批人"));
				ws.addCell(new Label(10, 0, "文件状态"));
			}
			Integer i = 1;
			for (KonkaoaFiles t : entityList) {
				KonkaExpenseClaims claims = new KonkaExpenseClaims();
				claims.setFile_id(t.getId());
				claims = getFacade().getKonkaExpenseClaimsService().getKonkaExpenseClaims(claims);
				if (null != claims) {
					t.getMap().put("column_6", claims.getColumn_6());
				}

				if (wwb != null) {
					ws.addCell(new Label(0, i, i.toString()));
					ws.addCell(new Label(1, i, t.getFile_no()));
					ws.addCell(new Label(2, i, t.getFile_title()));
					if (null != t.getMap().get("r3_shop_name")) {
						ws.addCell(new Label(3, i, t.getMap().get("r3_shop_name").toString()));
					}
					if (null != t.getMap().get("r3_code")) {
						ws.addCell(new Label(4, i, t.getMap().get("r3_code").toString()));
					}
					if (null != t.getMap().get("column_6")) {
						ws.addCell(new Label(5, i, t.getMap().get("column_6").toString()));
					}
					ws.addCell(new Label(6, i, t.getSubmit_dept()));
					ws.addCell(new Label(7, i, t.getSubmit_user()));
					ws.addCell(new Label(8, i, s.format(t.getSubmit_datetime())));
					if (null != t.getMap().get("cur_audit_user_name")) {
						ws.addCell(new Label(9, i, t.getMap().get("cur_audit_user_name").toString()));
					}
					if (0 == t.getFile_status() || -3 == t.getFile_status()) {
						ws.addCell(new Label(10, i, "未审批"));
					} else if (1 == t.getFile_status() || -1 == t.getFile_status() || -2 == t.getFile_status()) {
						ws.addCell(new Label(10, i, "审批中"));
					} else if (2 == t.getFile_status()) {
						ws.addCell(new Label(10, i, "被驳回"));
					} else if (3 == t.getFile_status()) {
						ws.addCell(new Label(10, i, "已批准"));
					}
				}
				i++;
			}
			try {
				wwb.write();// 从内存中写入文件中
				wwb.close();// 关闭资源，释放内存
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}

			String ctx = super.getCtxPath(request);
			super.renderJavaScript(response, "window.location.href='" + ctx + "/files/" + excel_name + "';");
		}
		return null;
	}
}