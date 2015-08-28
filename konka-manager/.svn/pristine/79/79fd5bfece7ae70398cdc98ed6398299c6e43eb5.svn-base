package com.ebiz.mmt.web.struts.manager.spgl;

import java.io.File;
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
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcBaseCardChangeInfo;
import com.ebiz.mmt.domain.EcBaseCardLevel;
import com.ebiz.mmt.domain.EcBaseCardNo;
import com.ebiz.mmt.domain.EcBaseCardType;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.ExcelUtil;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Pan,Gang
 * @version 2013-09-24
 */
public class EcBaseCardNoAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String card_no_like = (String) dynaBean.get("card_no_like");
		String member_name_like = (String) dynaBean.get("member_name_like");
		String card_level = (String) dynaBean.get("card_level");
		String card_sender_dept = (String) dynaBean.get("card_sender_dept");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcBaseCardNo entity = new EcBaseCardNo();
		if (StringUtils.isNotBlank(card_no_like)) {
			entity.getMap().put("card_no_like", card_no_like);
		}
		if (StringUtils.isNotBlank(member_name_like)) {
			entity.getMap().put("member_name_like", member_name_like);
		}
		if (StringUtils.isNotBlank(card_level)) {
			entity.setCard_level(Long.valueOf(card_level));
		}
		if (StringUtils.isNotBlank(card_sender_dept)) {
			entity.setCard_sender_dept(card_sender_dept);
		}

		Long recordCount = super.getFacade().getEcBaseCardNoService().getEcBaseCardNoCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcBaseCardNo> entityList = super.getFacade().getEcBaseCardNoService().getEcBaseCardNoPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		EcBaseCardLevel el = new EcBaseCardLevel();
		List<EcBaseCardLevel> elList = super.getFacade().getEcBaseCardLevelService().getEcBaseCardLevelList(el);
		request.setAttribute("elList", elList);

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		saveToken(request);
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
		if (isCancelled(request)) {
			return list(mapping, form, request, response);
		}
		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}
		resetToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String card_no = (String) dynaBean.get("card_no");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcBaseCardNo entity = new EcBaseCardNo();
		super.copyProperties(entity, form);

		if (StringUtils.isEmpty(id)) {
			if (StringUtils.isNotBlank(card_no)) {
				EcBaseCardNo ec = new EcBaseCardNo();
				ec.setCard_no(card_no);
				Long count = super.getFacade().getEcBaseCardNoService().getEcBaseCardNoCount(ec);
				if (count > 0) {
					super.renderJavaScript(response,
					        "window.onload=function(){alert('对不起，该会员卡号已经存在！');history.back();}");
					return null;
				}
			}

			super.getFacade().getEcBaseCardNoService().createEcBaseCardNo(entity);
			super.saveMessage(request, "entity.inserted");
		} else {
			super.getFacade().getEcBaseCardNoService().modifyEcBaseCardNo(entity);
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
			// 序号,会员卡号,发卡日期
			// 发卡人,客户,发卡部门,会员卡分类,会员卡等级,会员姓名,会员电话,会员身份证,会员卡激活有效期,会员卡激活时间,会员卡制作人,
			// 会员卡制作部门,会员卡有效期开始,会员卡备注,会员卡有效期结束,是否允许多次激活
			List<EcBaseCardNo> rList = new ArrayList<EcBaseCardNo>();
			EcBaseCardNo carno = new EcBaseCardNo();
			for (int i = 1; i < dd.length; i++) {
				carno = new EcBaseCardNo();
				for (int j = 0; j < dd[i].length; j++) {
					if ("会员卡号".equals(dd[0][j].trim())) {
						carno.setCard_no(dd[i][j]);
					} else if ("发卡日期".equals(dd[0][j].trim())) {
						try {
							if (!"".equals(dd[i][j].trim())) {
								String strDate = dd[i][j].trim();
								SimpleDateFormat df = null;
								Date date = null;
								df = new SimpleDateFormat("yyyy-MM-dd");
								try {
									date = df.parse(strDate);
								} catch (ParseException pe) {
								}
								carno.setCard_pub_date(date);
							}
						} catch (Exception e) {
						}
					} else if ("发卡人".equals(dd[0][j].trim())) {
						carno.setCard_sender(dd[i][j]);
					} else if ("发卡部门".equals(dd[0][j].trim())) {
						carno.setCard_sender_dept(dd[i][j]);
					} else if ("会员卡分类".equals(dd[0][j].trim())) {
						String name = dd[i][j].trim();
						EcBaseCardType cardType = new EcBaseCardType();
						cardType.setCard_type_name(name);
						List<EcBaseCardType> cardTypeList = super.getFacade().getEcBaseCardTypeService()
						        .getEcBaseCardTypeList(cardType);
						if (cardTypeList != null && cardTypeList.size() > 0) {
							cardType = cardTypeList.get(0);
							carno.setCard_type(cardType.getCard_type());
						}
						// carno.setCard_type(dd[i][j]);
					} else if ("会员卡等级".equals(dd[0][j].trim())) {
						String name = dd[i][j].trim();
						EcBaseCardLevel cardLevel = new EcBaseCardLevel();
						cardLevel.setCard_level_name(name);
						List<EcBaseCardLevel> cardLevelList = super.getFacade().getEcBaseCardLevelService()
						        .getEcBaseCardLevelList(cardLevel);
						if (cardLevelList != null && cardLevelList.size() > 0) {
							cardLevel = cardLevelList.get(0);
							carno.setCard_level(cardLevel.getCard_level());
						}
						// carno.setCard_level(dd[i][j]);
					} else if ("会员姓名".equals(dd[0][j].trim())) {
						carno.setMember_name(dd[i][j]);
					} else if ("会员电话".equals(dd[0][j].trim())) {
						carno.setMember_tel(dd[i][j]);
					} else if ("会员身份证".equals(dd[0][j].trim())) {
						carno.setMember_id(dd[i][j]);
					} else if ("会员卡激活有效期".equals(dd[0][j].trim())) {
						try {
							if (!"".equals(dd[i][j].trim())) {
								String strDate = dd[i][j].trim();
								SimpleDateFormat df = null;
								Date date = null;
								df = new SimpleDateFormat("yyyy-MM-dd");
								try {
									date = df.parse(strDate);
								} catch (ParseException pe) {
								}
								carno.setCard_act_valid_date(date);
							}
						} catch (Exception e) {
						}
					} else if ("会员卡激活时间".equals(dd[0][j].trim())) {
						try {
							if (!"".equals(dd[i][j].trim())) {
								String strDate = dd[i][j].trim();
								SimpleDateFormat df = null;
								Date date = null;
								df = new SimpleDateFormat("yyyy-MM-dd");
								try {
									date = df.parse(strDate);
								} catch (ParseException pe) {
								}
								carno.setCard_act_date(date);
							}
						} catch (Exception e) {
						}
					} else if ("会员卡制作人".equals(dd[0][j].trim())) {
						carno.setCard_creater(dd[i][j]);
					} else if ("会员卡制作部门".equals(dd[0][j].trim())) {
						carno.setCard_create_dept(dd[i][j]);
					} else if ("会员卡有效期开始".equals(dd[0][j].trim())) {
						try {
							if (!"".equals(dd[i][j].trim())) {
								String strDate = dd[i][j].trim();
								SimpleDateFormat df = null;
								Date date = null;
								df = new SimpleDateFormat("yyyy-MM-dd");
								try {
									date = df.parse(strDate);
								} catch (ParseException pe) {
								}
								carno.setCard_limit_start(date);
							}
						} catch (Exception e) {
						}
					} else if ("会员卡备注".equals(dd[0][j].trim())) {
						carno.setCard_memo(dd[i][j]);
					} else if ("会员卡有效期结束".equals(dd[0][j].trim())) {
						try {
							if (!"".equals(dd[i][j].trim())) {
								String strDate = dd[i][j].trim();
								SimpleDateFormat df = null;
								Date date = null;
								df = new SimpleDateFormat("yyyy-MM-dd");
								try {
									date = df.parse(strDate);
								} catch (ParseException pe) {
								}
								carno.setCard_limit_end(date);
							}
						} catch (Exception e) {
						}
					} else if ("是否允许多次激活".equals(dd[0][j].trim())) {
						if ("是".equals(dd[i][j].trim())) {
							carno.setCard_allow_mul_act(new Integer(1));
						} else {
							carno.setCard_allow_mul_act(new Integer(0));
						}
					}
				}
				rList.add(carno);
			}
			String msg = super.getFacade().getEcBaseCardNoService().createEcBaseCardNo(rList);
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

		EcBaseCardType et = new EcBaseCardType();
		List<EcBaseCardType> etList = super.getFacade().getEcBaseCardTypeService().getEcBaseCardTypeList(et);
		request.setAttribute("etList", etList);

		EcBaseCardLevel el = new EcBaseCardLevel();
		List<EcBaseCardLevel> elList = super.getFacade().getEcBaseCardLevelService().getEcBaseCardLevelList(el);
		request.setAttribute("elList", elList);

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		EcBaseCardNo entity = new EcBaseCardNo();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getEcBaseCardNoService().getEcBaseCardNo(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}

	public ActionForward validateCardNo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		StringBuffer oper = new StringBuffer("{\"result\":");
		String card_no = (String) dynaBean.get("card_no");
		EcBaseCardNo entity = new EcBaseCardNo();
		entity.setCard_no(card_no);
		Long count = super.getFacade().getEcBaseCardNoService().getEcBaseCardNoCount(entity);
		if ("0".equals(count.toString())) {
			oper.append(false);
		} else {
			oper.append(true);
		}
		oper.append("}");
		super.render(response, oper.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward updateBatch(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String[] pks = (String[]) dynaBean.get("pks");
		//System.out.println("pks------------>{}" + pks.length);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcBaseCardLevel el = new EcBaseCardLevel();
		List<EcBaseCardLevel> elList = super.getFacade().getEcBaseCardLevelService().getEcBaseCardLevelList(el);
		request.setAttribute("elList", elList);

		List<EcBaseCardNo> entityList = new ArrayList<EcBaseCardNo>();
		for (String id : pks) {
			EcBaseCardNo entity = new EcBaseCardNo();
			entity.setId(Long.valueOf(id));
			entity = super.getFacade().getEcBaseCardNoService().getEcBaseCardNo(entity);
			entityList.add(entity);
		}

		request.setAttribute("entityList", entityList);

		return new ActionForward("/../manager/spgl/EcBaseCardNo/updateBatch.jsp");
	}

	public ActionForward save1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String[] select2 = request.getParameterValues("select2");
		String card_level = (String) dynaBean.get("card_level");
		String opr_desc = (String) dynaBean.get("opr_desc");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcBaseCardNo entity = new EcBaseCardNo();
		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(card_level)) {
			entity.setCard_level(Long.valueOf(card_level));
		}
		entity.getMap().put("pks", select2);
		super.getFacade().getEcBaseCardNoService().modifyEcBaseCardNo(entity);

		for (String id : select2) {
			EcBaseCardNo ec = new EcBaseCardNo();
			ec.setId(Long.valueOf(id));
			ec = super.getFacade().getEcBaseCardNoService().getEcBaseCardNo(ec);
			EcBaseCardChangeInfo ecc = new EcBaseCardChangeInfo();
			ecc.setCard_no(ec.getCard_no());
			ecc.setOpr_date(new Date());
			ecc.setOpr_man(user.getReal_name());
			if (StringUtils.isNotBlank(opr_desc)) {
				ecc.setOpr_desc(opr_desc);
			}
			super.getFacade().getEcBaseCardChangeInfoService().createEcBaseCardChangeInfo(ecc);
		}

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

	public ActionForward listForHistory(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String card_no = (String) dynaBean.get("card_no");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcBaseCardChangeInfo entity = new EcBaseCardChangeInfo();
		if (StringUtils.isNotBlank(card_no)) {
			entity.setCard_no(card_no);
		}

		List<EcBaseCardChangeInfo> entityList = super.getFacade().getEcBaseCardChangeInfoService()
		        .getEcBaseCardChangeInfoList(entity);
		request.setAttribute("entityList", entityList);

		return new ActionForward("/../manager/spgl/EcBaseCardNo/listForHistory.jsp");
	}
}
