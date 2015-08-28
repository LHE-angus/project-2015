package com.ebiz.mmt.web.struts.manager.spgl;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcCashPrice;
import com.ebiz.mmt.domain.EcBaseCardLevel;
import com.ebiz.mmt.domain.EcCashPrice;
import com.ebiz.mmt.domain.EcBaseCardType;
import com.ebiz.mmt.domain.EcCashPrice;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.ExcelUtil;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author TUDP
 * @version 2013-12-20
 */
public class EcCashPriceAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form; 
		String pd_sn = (String)dynaBean.get("pd_sn");
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		EcCashPrice entity = new EcCashPrice();		
		entity.setUser_id(user.getId());	
		entity.setPd_sn(pd_sn);

		List<EcCashPrice> entityList = super.getFacade().getEcCashPriceService().getEcCashPriceList(entity);
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

		return mapping.findForward("input");
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

		EcCashPrice entity = new EcCashPrice();
		super.copyProperties(entity, form);
		entity.setUser_id(user.getId());
		if (StringUtils.isEmpty(id)) {
			super.getFacade().getEcCashPriceService().createEcCashPrice(entity);
			super.saveMessage(request, "entity.inserted");
		} else {
			super.getFacade().getEcCashPriceService().modifyEcCashPrice(entity);
			super.saveMessage(request, "entity.updated");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
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

		EcCashPrice entity = new EcCashPrice();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getEcCashPriceService().getEcCashPrice(entity);

		super.copyProperties(form, entity);

		return mapping.findForward("view");
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

		EcCashPrice entity = new EcCashPrice();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getEcCashPriceService().getEcCashPrice(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id"); 

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			EcCashPrice entity = new EcCashPrice();
			entity.setId(new Long(id));
			getFacade().getEcCashPriceService().removeEcCashPrice(entity);
		} else if (!ArrayUtils.isEmpty(pks)) {
			EcCashPrice entity = new EcCashPrice();
			entity.getMap().put("pks", pks);
			super.getFacade().getEcCashPriceService().removeEcCashPrice(entity);
		}
		saveMessage(request, "entity.deleted");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);		 
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
	
	public ActionForward excelImport(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		EcCashPrice entity = new EcCashPrice();
		super.copyProperties(entity, form);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		File isFile = null;
		String fileSavePath = null;

		List<UploadFile> uploadFileList = super.uploadFile(form, false);
		for (UploadFile uploadFile : uploadFileList) {
			if ("excel".equals(uploadFile.getFormName())) {
				fileSavePath = uploadFile.getFileSavePath();
			}
			logger.info("********************** {}", BeanUtils.describe(uploadFile));
		}

		if (fileSavePath != null) {
			String filetype = fileSavePath.substring(fileSavePath.lastIndexOf(".") + 1);
			if (!filetype.equalsIgnoreCase("xls") && !filetype.equalsIgnoreCase("xlsx")) {
				super.renderJavaScript(response, "alert('上传数据格式不正确！');history.back();");
				return null;
			}
			fileSavePath = request.getSession().getServletContext().getRealPath("/") + fileSavePath;
			isFile = new File(fileSavePath);

			String[][] dd = ExcelUtil.getExcelData(isFile, 0);
			for (int i = 0; i < dd.length; i++) {
				for (int j = 0; j < dd[i].length; j++) {
					//System.out.print(dd[i][j] + ",");
				}
				//System.out.println();
			}
			// 序号,会员卡号,发卡日期
			// 发卡人,客户,发卡部门,会员卡分类,会员卡等级,会员姓名,会员电话,会员身份证,会员卡激活有效期,会员卡激活时间,会员卡制作人,
			// 会员卡制作部门,会员卡有效期开始,会员卡备注,会员卡有效期结束,是否允许多次激活
			List<EcCashPrice> rList = new ArrayList<EcCashPrice>();
			EcCashPrice cashPrice = new EcCashPrice();
			for (int i = 1; i < dd.length; i++) {
				cashPrice = new EcCashPrice();
				cashPrice.setUser_id(user.getId());
				cashPrice.setInfo_state(new Integer(0));
				for (int j = 0;j < dd[0].length && j < dd[i].length; j++) {  
						if ("产品型号".equals(dd[0][j].trim())) {
							cashPrice.setPd_sn(dd[i][j]);
						} else if ("现款价".equals(dd[0][j].trim())) {
							try{
							cashPrice.setCash_price(new BigDecimal(dd[i][j].trim()));
							}catch(Exception e){}
						}  
				}
				if(cashPrice.getPd_sn()!=null)
				rList.add(cashPrice);
			}
			String msg = super.getFacade().getEcCashPriceService().createEcCashPrice(rList);
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

}
