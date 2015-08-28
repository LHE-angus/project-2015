package com.ebiz.mmt.web.struts.manager.admin;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
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

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.KonkaR3StoreShow;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.mmt.web.util.ExcelUtil;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Pan,Gang
 * @version 2013-09-24
 */
public class KonkaR3StoreShowAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String store_name_like = (String) dynaBean.get("store_name_like");
		String dept_id = (String) dynaBean.get("dept_id");
		String year=(String) dynaBean.get("year");
		String month=(String) dynaBean.get("month");
		String task_name_like=(String) dynaBean.get("task_name_like");
		String category_name_like=(String) dynaBean.get("category_name_like");
		String excel_all = (String) dynaBean.get("excel_all");
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		KonkaR3StoreShow entity = new KonkaR3StoreShow();
		if (StringUtils.isNotBlank(store_name_like)) {
			entity.getMap().put("store_name_like", store_name_like);
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
		}
		if (StringUtils.isNotBlank(year)&&isValidInt(year)) {
			entity.setYear(Integer.valueOf(year));
		}
		if (StringUtils.isNotBlank(month)&&isValidInt(month)) {
			entity.setMonth(Integer.valueOf(month));
		}
		if (StringUtils.isNotBlank(task_name_like)) {
			//System.out.println(task_name_like);
			entity.getMap().put("task_name_like", task_name_like);
		}
		if (StringUtils.isNotBlank(category_name_like)) {
			entity.getMap().put("category_name_like", category_name_like);
		}

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(user.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		boolean role_id_eq_30 = false;
		boolean role_id_gt_30_lt_60 = false;

		for (PeRoleUser peRoleUser : peRoleUserList) {
			// 总部管理员
			if (peRoleUser.getRole_id() < 30L) {
				role_id_eq_30 = true;
			}
			// 分公司管理员
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() <= 188L) {
				role_id_gt_30_lt_60 = true;
			}
		}
		if (role_id_eq_30) {

		} else if (role_id_gt_30_lt_60) {
			entity.setDept_id(super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id());
		}

		Long recordCount = super.getFacade().getKonkaR3StoreShowService().getKonkaR3StoreShowLBCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			if (recordCount.intValue() > 20000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
						+ "！');history.back();");
				return null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + EncryptUtils.encodingFileName("门店展示演示设备数据") + ".xls");
			entity.getRow().setCount(recordCount.intValue());
			List<KonkaR3StoreShow> entityList1 = super.getFacade().getKonkaR3StoreShowService()
			        .getKonkaR3StoreShowLBPaginatedList(entity);
			request.setAttribute("entityList1", entityList1);
			return new ActionForward("/admin/KonkaR3StoreShow/listForReport.jsp");
		}
		
		List<KonkaR3StoreShow> entityList = super.getFacade().getKonkaR3StoreShowService().getKonkaR3StoreShowLBPaginatedList(entity);
        request.setAttribute("entityList", entityList);
		List<KonkaDept> deptlist=super.getDeptInfoListWithOutLimit(mapping, form, request, response);
		
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		resetToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String dept_id = (String) dynaBean.get("dept_id");
		String dept_name = (String) dynaBean.get("dept_name");
		String store_id = (String) dynaBean.get("store_id");
		String store_name = (String) dynaBean.get("store_name");
		String task_name = (String) dynaBean.get("task_name");
		String category_name = (String) dynaBean.get("category_name");
		String num = (String) dynaBean.get("num");
		
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaR3StoreShow entity = new KonkaR3StoreShow();
		super.copyProperties(entity, form);
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
		}
		if (StringUtils.isNotBlank(dept_name)) {
			entity.setDept_name(dept_name);			
		}
		if (StringUtils.isNotBlank(store_id)) {
			entity.setStore_id(Long.valueOf(store_id));
		}
		if (StringUtils.isNotBlank(store_name)) {
			entity.setStore_name(store_name);			
		}
		if (StringUtils.isNotBlank(task_name)) {
			entity.setTask_name(task_name);
		}
		if (StringUtils.isNotBlank(category_name)) {
			entity.setCategory_name(category_name);
		}
		if (null!=user&&null!=user.getId()) {
			entity.setAdd_user_id(Long.valueOf(user.getId()));
		}
		if (StringUtils.isNotBlank(num)) {
			BigDecimal num1=new BigDecimal(num);
			entity.setNum(num1);
		}
		if (StringUtils.isEmpty(id)) {
			entity.setAdd_date(new Date());
			super.getFacade().getKonkaR3StoreShowService().createKonkaR3StoreShow(entity);
			super.saveMessage(request, "entity.inserted");
		} else {
			super.getFacade().getKonkaR3StoreShowService().modifyKonkaR3StoreShow(entity);
			super.saveMessage(request, "entity.updated");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		String agent_name = java.net.URLDecoder.decode(entity.getQueryString(),"UTF-8");
		pathBuffer.append(super.encodeSerializedQueryString(agent_name));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

	}

	public ActionForward excelImport(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		KonkaR3StoreShow entity = new KonkaR3StoreShow();
		super.copyProperties(entity, form);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		File isFile = null;
		String fileSavePath = null;

		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.MD_PATH);
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
			String[][] dd=null;
			try{
				dd = ExcelUtil.getExcelData(isFile, 0);
			}catch(Exception e2){
				super.renderJavaScript(response, "alert('格式不对，请尝试将excel文件另存为97-2003格式后再倒入此模板！');history.back();");
				return null;
			}
			List<KonkaR3StoreShow> rList = new ArrayList<KonkaR3StoreShow>();
			KonkaR3StoreShow carno = new KonkaR3StoreShow();
			String errorResult="";
			for (int i = 1; i < dd.length; i++) {
				carno = new KonkaR3StoreShow();
				for (int j = 0; j < dd[i].length; j++) {
					if ("类型".equals(dd[0][j].trim())) {
						if ("".equals(dd[i][j])) {
							errorResult = "类型不能为空！";
							super.renderJavaScript(response, "window.onload=function(){alert('" + errorResult
							        + "');window.history.back();}");
							return null;
						}else{
							carno.setTask_name(dd[i][j]);
							if (carno.getTask_name().equals("展台展柜")) {
								carno.setTask_type(0);
							} else if (carno.getTask_name().equals("演示设备")) {
								carno.setTask_type(1);
							}
						}
					} else if ("分公司".equals(dd[0][j].trim())) {
						if ("".equals(dd[i][j])) {
							errorResult = "分公司不能为空！";
							super.renderJavaScript(response, "window.onload=function(){alert('" + errorResult
							        + "');window.history.back();}");
							return null;
						}else{
						    carno.setDept_name(dd[i][j]);
						}
					} else if ("门店ID".equals(dd[0][j].trim())) {
						if (isValidLong(dd[i][j])) {
							carno.setStore_id(Long.valueOf(dd[i][j]));

							KonkaR3Store kk = new KonkaR3Store();
							kk.setStore_id(carno.getStore_id());
							kk = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(kk);
							if (null != kk) {
								carno.setDept_id(kk.getDept_id());
								 KonkaDept kd=new KonkaDept();
									kd.setDept_id(carno.getDept_id());
									kd=super.getFacade().getKonkaDeptService().getKonkaDept(kd);
									if(!carno.getDept_name().equalsIgnoreCase(kd.getDept_name())){
										String ss = "门店所属部门与导入部门不一致！";
										super.renderJavaScript(response, "window.onload=function(){alert('" + ss
										        + "');window.history.back();}");
										return null;
									}
							}

						} else {
							errorResult = "门店ID请填写正整数！";
							super.renderJavaScript(response, "window.onload=function(){alert('" + errorResult
							        + "');window.history.back();}");
							return null;
						}
					} else if ("门店名称".equals(dd[0][j].trim())) {
						if ("".equals(dd[i][j])) {
							errorResult = "门店名称不能为空！";
							super.renderJavaScript(response, "window.onload=function(){alert('" + errorResult
							        + "');window.history.back();}");
							return null;
						}
						carno.setStore_name(dd[i][j]);
					} else if ("年".equals(dd[0][j].trim())) {
						if (isValidInt(dd[i][j])) {
							carno.setYear(Integer.valueOf(dd[i][j]));
						} else {
							errorResult = "年份请填写正整数！";
							super.renderJavaScript(response, "window.onload=function(){alert('" + errorResult
							        + "');window.history.back();}");
							return null;
						}

					} else if ("月".equals(dd[0][j].trim())) {
						if (isValidInt(dd[i][j])) {
							carno.setMonth(Integer.valueOf(dd[i][j]));
						} else {
							String ss = "月份请填写正整数！";
							super.renderJavaScript(response, "window.onload=function(){alert('" + ss
							        + "');window.history.back();}");
							return null;
						}

					} else if ("金额".equals(dd[0][j].trim())) {
						try {
							BigDecimal bd = new BigDecimal(dd[i][j]);
							carno.setTask_money(bd);
						} catch (Exception e) {
							carno.setTask_money(new BigDecimal("0.00"));
						}
					} else if ("尺寸/规格".equals(dd[0][j].trim())) {
						carno.setSize(dd[i][j]);
					} else if ("备注".equals(dd[0][j].trim())) {
						carno.setRemark(dd[i][j]);
					}else if ("品类名称".equals(dd[0][j].trim())) {
						if ("".equals(dd[i][j])) {
							errorResult = "品类名称不能为空！";
							super.renderJavaScript(response, "window.onload=function(){alert('" + errorResult
							        + "');window.history.back();}");
							return null;
						}else{
							carno.setCategory_name(dd[i][j]);	
						}
					}else if ("数量".equals(dd[0][j].trim())) {
						if ("".equals(dd[i][j])) {
							errorResult = "数量不能为空！";
							super.renderJavaScript(response, "window.onload=function(){alert('" + errorResult
							        + "');window.history.back();}");
							return null;
						}else{
							try {
								BigDecimal bd = new BigDecimal(dd[i][j]);
								carno.setNum(bd);
							} catch (Exception e) {
								carno.setNum(new BigDecimal("0"));
							}
						}
					}
					carno.setAdd_date(new Date());
					carno.setAdd_user_id(user.getId());
				}
				rList.add(carno);

			}

			// list去重，并按原顺序重新生成list
			List<KonkaR3StoreShow> rList2 = new ArrayList<KonkaR3StoreShow>();
			HashSet<KonkaR3StoreShow> result = new HashSet<KonkaR3StoreShow>();
			for (int i = 0; i < rList.size(); i++) {
				if (!result.add(rList.get(i))) {
				} else {
					result.add(rList.get(i));
					rList2.add(rList.get(i));
				}

			}
			String msg = super.getFacade().getKonkaR3StoreShowService().createKonkaR3StoreShow(rList2);
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

		KonkaR3StoreShow ec = new KonkaR3StoreShow();
		ec.setId(Long.valueOf(id));
		ec = super.getFacade().getKonkaR3StoreShowService().getKonkaR3StoreShow(ec);
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

		KonkaR3StoreShow entity = new KonkaR3StoreShow();
		entity.setId(Long.valueOf(id));
		super.getFacade().getKonkaR3StoreShowService().removeKonkaR3StoreShow(entity);

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
		super.saveToken(request);
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

		KonkaR3StoreShow entity = new KonkaR3StoreShow();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getKonkaR3StoreShowService().getKonkaR3StoreShow(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session
				.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(user.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade()
				.getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		boolean role_id_eq_30 = false;
		boolean role_id_gt_30_lt_60 = false;

		for (PeRoleUser peRoleUser : peRoleUserList) {
			// 总部管理员
			if (peRoleUser.getRole_id() < 30L) {
				role_id_eq_30 = true;
			}
			// 分公司管理员
			if (peRoleUser.getRole_id() >= 30L
					&& peRoleUser.getRole_id() <= 188L) {
				role_id_gt_30_lt_60 = true;
			}
		}
		KonkaR3Store entity = new KonkaR3Store();

		List<KonkaR3Store> r3Storelist = new ArrayList<KonkaR3Store>();
		List<KonkaDept> deptlist = super.getDeptInfoListWithOutLimit(mapping,
				form, request, response);

		if (null != deptlist && deptlist.size() > 0) {
			if (role_id_eq_30) {

			} else if (role_id_gt_30_lt_60) {
				entity.setDept_id(deptlist.get(0).getDept_id());
			}
		}

		r3Storelist = super.getFacade().getKonkaR3StoreService()
				.getKonkaR3StoreList(entity);
		List<String>taskNamelist=new ArrayList<String>();
		taskNamelist.add("展台展柜");
		taskNamelist.add("演示设备");
        request.setAttribute("taskNamelist", taskNamelist);
		request.setAttribute("konkaDeptList", deptlist);
		request.setAttribute("r3Storelist", r3Storelist);
		return mapping.findForward("input");
	}
	
	/**
	 * 通过分公司id 异步请求门店信息 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ajaxKonkaR3StoreList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String dept_id=(String)dynaBean.get("dept_id");
		
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session
				.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		
		KonkaR3Store entity = new KonkaR3Store();
		List<KonkaR3Store> r3Storelist = new ArrayList<KonkaR3Store>();
		entity.setDept_id(Long.valueOf(dept_id));
		r3Storelist = super.getFacade().getKonkaR3StoreService()
				.getKonkaR3StoreList(entity);

		request.setAttribute("r3Storelist", r3Storelist);
		super.renderJson(response, JSON.toJSONString(r3Storelist));
		return null;
	}
	
	public static boolean isValidLong(String str) {
		try {
			long _v = Long.parseLong(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isValidInt(String str) {
		try {
			int _v = Integer.parseInt(str);
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
