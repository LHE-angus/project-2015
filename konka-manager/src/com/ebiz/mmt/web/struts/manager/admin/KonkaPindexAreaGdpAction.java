package com.ebiz.mmt.web.struts.manager.admin;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.ebiz.mmt.domain.EcBaseCardLevel;
import com.ebiz.mmt.domain.EcBaseCardNo;
import com.ebiz.mmt.domain.EcBaseCardType;
import com.ebiz.mmt.domain.KonkaPindexAreaGdp;
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
public class KonkaPindexAreaGdpAction extends BaseAction {
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
		String p_name_like = (String) dynaBean.get("p_name_like");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaPindexAreaGdp entity = new KonkaPindexAreaGdp();
		if (StringUtils.isNotBlank(p_name_like)) {
			entity.getMap().put("p_name_like", p_name_like);
		}

		Long recordCount = super.getFacade().getKonkaPindexAreaGdpService().getKonkaPindexAreaGdpCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaPindexAreaGdp> entityList = super.getFacade().getKonkaPindexAreaGdpService()
		        .getKonkaPindexAreaGdpPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcBaseCardType et = new EcBaseCardType();
		List<EcBaseCardType> etList = super.getFacade().getEcBaseCardTypeService().getEcBaseCardTypeList(et);
		request.setAttribute("etList", etList);

		EcBaseCardLevel el = new EcBaseCardLevel();
		List<EcBaseCardLevel> elList = super.getFacade().getEcBaseCardLevelService().getEcBaseCardLevelList(el);
		request.setAttribute("elList", elList);

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String p_index = (String) dynaBean.get("p_index");
		String mod_id = (String) dynaBean.get("mod_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaPindexAreaGdp entity = new KonkaPindexAreaGdp();
		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(p_index)) {
			entity.setP_index(Long.valueOf(p_index));
		}
		super.getFacade().getKonkaPindexAreaGdpService().modifyKonkaPindexAreaGdp(entity);
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
		String ctxDir = null;

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
			List<KonkaPindexAreaGdp> rList = new ArrayList<KonkaPindexAreaGdp>();
			KonkaPindexAreaGdp carno = new KonkaPindexAreaGdp();
			for (int i = 1; i < dd.length; i++) {
				carno = new KonkaPindexAreaGdp();
				for (int j = 0; j < dd[i].length; j++) {
					if ("区域编号".equals(dd[0][j].trim())) {
						if (isValidLong(dd[i][j])) {
							carno.setP_index(Long.valueOf(dd[i][j]));
						} else {
							String ss = "区域编码请填写正整数！";
							super.renderJavaScript(response, "window.onload=function(){alert('" + ss
							        + "');window.history.back();}");
							return null;
						}
					} else if ("地区名".equals(dd[0][j].trim())) {
						if (StringUtils.isNotBlank(dd[i][j])) {
							carno.setP_name(dd[i][j]);
						}
					} else if ("GDP".equals(dd[0][j].trim())) {
						if (isValidDouble(dd[i][j])) {
							carno.setGdp(new BigDecimal(dd[i][j]).setScale(2, BigDecimal.ROUND_HALF_UP));
						} else {
							String ss = "GDP请填写数字！";
							super.renderJavaScript(response, "window.onload=function(){alert('" + ss
							        + "');window.history.back();}");
							return null;
						}
					} else if ("行政区域内人口".equals(dd[0][j].trim())) {
						if (isValidDouble(dd[i][j])) {
							carno.setP_area(new BigDecimal(dd[i][j]).setScale(0));
						} else {
							String ss = "行政区域内人口请填写数字！";
							super.renderJavaScript(response, "window.onload=function(){alert('" + ss
							        + "');window.history.back();}");
							return null;
						}
					} else if ("面积".equals(dd[0][j].trim())) {
						if (isValidDouble(dd[i][j])) {
							carno.setColumn_1(new BigDecimal(dd[i][j]).setScale(2, BigDecimal.ROUND_HALF_UP));
						} else {
							String ss = "面积请填写数字！";
							super.renderJavaScript(response, "window.onload=function(){alert('" + ss
							        + "');window.history.back();}");
							return null;
						}
					} else if ("家电客户数".equals(dd[0][j].trim())) {
						if (isValidDouble(dd[i][j])) {
							carno.setColumn_2(new BigDecimal(dd[i][j]).setScale(0));
						} else {
							String ss = "家电客户数请填写数字！";
							super.renderJavaScript(response, "window.onload=function(){alert('" + ss
							        + "');window.history.back();}");
							return null;
						}
					}
				}
				rList.add(carno);
			}
			String msg = super.getFacade().getKonkaPindexAreaGdpService().createKonkaPindexAreaGdp(rList);
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

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		HttpSession session = request.getSession();

		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcBaseCardNo ec = new EcBaseCardNo();
		ec.setId(Long.valueOf(id));
		ec = super.getFacade().getEcBaseCardNoService().getEcBaseCardNo(ec);
		super.copyProperties(form, ec);

		return mapping.findForward("view");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		EcBaseCardNo entity = new EcBaseCardNo();
		entity.setId(Long.valueOf(id));
		super.getFacade().getEcBaseCardNoService().removeEcBaseCardNo(entity);

		saveMessage(request, "entity.deleted");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.serialize(request, "id", "method"));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String p_index = (String) dynaBean.get("p_index");
		if (!GenericValidator.isLong(p_index)) {
			super.saveError(request, "errors.long", "p_index");
			return this.list(mapping, form, request, response);
		}

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaPindexAreaGdp entity = new KonkaPindexAreaGdp();
		entity.setP_index(Long.valueOf(p_index));

		entity = super.getFacade().getKonkaPindexAreaGdpService().getKonkaPindexAreaGdp(entity);
		entity.setQueryString(super.serialize(request, "p_index", "method"));

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
