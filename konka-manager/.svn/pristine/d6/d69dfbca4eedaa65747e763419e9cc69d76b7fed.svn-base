package com.ebiz.mmt.web.struts.manager.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcBaseCardNo;
import com.ebiz.mmt.domain.KonkaPdModelTask;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.mmt.web.util.ExcelUtil;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Pan,Gang
 * @version 2013-09-24
 */
public class KonkaPdModelTaskAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String md_name_like = (String) dynaBean.get("md_name_like");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaPdModelTask entity = new KonkaPdModelTask();
		if (StringUtils.isNotBlank(md_name_like)) {
			entity.getMap().put("md_name_like", md_name_like);
		}

		Long recordCount = super.getFacade().getKonkaPdModelTaskService().getKonkaPdModelTaskCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaPdModelTask> entityList = super.getFacade().getKonkaPdModelTaskService()
		        .getKonkaPdModelTaskPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaPdModelTask entity = new KonkaPdModelTask();
		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}
		super.getFacade().getKonkaPdModelTaskService().modifyKonkaPdModelTask(entity);
		super.saveMessage(request, "entity.updated");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

	}

	public ActionForward excelImport(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		EcBaseCardNo entity = new EcBaseCardNo();
		super.copyProperties(entity, form);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		File isFile = null;
		String fileSavePath = null;
		String ctxDir = "";

		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH);
		for (UploadFile uploadFile : uploadFileList) {
			if ("excel".equals(uploadFile.getFormName())) {
				fileSavePath = uploadFile.getFileSavePath();
				break;
			}
		}

		if (fileSavePath != null) {
			String filetype = fileSavePath.substring(fileSavePath.lastIndexOf(".") + 1);
			if (!filetype.equalsIgnoreCase("xls") && !filetype.equalsIgnoreCase("xlsx")) {
				super.renderJavaScript(response, "alert('上传数据格式不正确！');history.back();");
				return null;
			}
			ctxDir = request.getSession().getServletContext().getRealPath("/");
			// 附件保存路径更改遗留问题
			if (StringUtils.contains(fileSavePath, MmtFilePathConfig.S_OTHERS_PATH)) {
				ctxDir = "";
				fileSavePath = "/Attachment_new/konka-files/" + fileSavePath;
			}

			fileSavePath = ctxDir + fileSavePath;
			isFile = new File(fileSavePath);

			String[][] dd = ExcelUtil.getExcelData(isFile, 0);
			List<KonkaPdModelTask> rList = new ArrayList<KonkaPdModelTask>();
			KonkaPdModelTask carno = new KonkaPdModelTask();
			for (int i = 1; i < dd.length; i++) {
				carno = new KonkaPdModelTask();
				for (int j = 0; j < dd[i].length; j++) {
					if ("产品型号".equals(dd[0][j].trim())) {
						if (StringUtils.isNotBlank(dd[i][j])) {
							carno.setMd_name(dd[i][j]);
						} else {
							String ss = "产品型号不能为空！";
							super.renderJavaScript(response, "window.onload=function(){alert('" + ss
							        + "');window.history.back();}");
							return null;
						}
					} else if ("年".equals(dd[0][j].trim())) {
						if (GenericValidator.isInt(dd[i][j])) {
							carno.setYear(Integer.valueOf(dd[i][j]));
						} else {
							String ss = "请正确填写年份！";
							super.renderJavaScript(response, "window.onload=function(){alert('" + ss
							        + "');window.history.back();}");
							return null;
						}
					} else if ("月".equals(dd[0][j].trim())) {
						if (GenericValidator.isInt(dd[i][j])) {
							carno.setMonth(Integer.valueOf(dd[i][j]));
						} else {
							String ss = "请正确填写月份！";
							super.renderJavaScript(response, "window.onload=function(){alert('" + ss
							        + "');window.history.back();}");
							return null;
						}

					} else if ("生产计划".equals(dd[0][j].trim())) {
						if (GenericValidator.isInt(dd[i][j])) {
							carno.setSc_plan_num(Long.valueOf(dd[i][j]));
						} else {
							String ss = "生产计划必须是正整数！";
							super.renderJavaScript(response, "window.onload=function(){alert('" + ss
							        + "');window.history.back();}");
							return null;
						}
					} else if ("销售计划".equals(dd[0][j].trim())) {
						if (GenericValidator.isInt(dd[i][j])) {
							carno.setSails_plan_num(Long.valueOf(dd[i][j]));
						} else {
							String ss = "销售计划必须是正整数！";
							super.renderJavaScript(response, "window.onload=function(){alert('" + ss
							        + "');window.history.back();}");
							return null;
						}
					} else if ("是否退市".equals(dd[0][j].trim())) {
						if (GenericValidator.isInt(dd[i][j])) {
							carno.setIs_sails(Integer.valueOf(dd[i][j]));
						} else {
							String ss = "是否退市请填写0或者1！";
							super.renderJavaScript(response, "window.onload=function(){alert('" + ss
							        + "');window.history.back();}");
							return null;
						}
					}
					carno.setAdd_date(new Date());
				}
				rList.add(carno);
			}
			String msg = super.getFacade().getKonkaPdModelTaskService().createKonkaPdModelTask(rList);
			if (!"".equals(msg)) {
				msg = " 系统提示 ,信息导入失败：\\n" + msg;
				log.info(msg);
				super.renderJavaScript(response, "alert('" + msg + "');history.back();");
				return null;
			}

		} else {
			super.renderJavaScript(response, "alert('请选择上传Excel文件！');history.back();");
			return null;
		}
		super.saveMessage(request, "entity.inserted");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaPdModelTask entity = new KonkaPdModelTask();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getKonkaPdModelTaskService().getKonkaPdModelTask(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}

	public static boolean isValidLong(String str) {
		try {
			long _v = Long.parseLong(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isValidDouble(String str) {
		try {
			double _v = Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
