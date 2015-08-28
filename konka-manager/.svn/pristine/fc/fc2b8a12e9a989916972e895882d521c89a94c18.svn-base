package com.ebiz.mmt.web.struts.manager.spgl;

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

import com.ebiz.mmt.domain.EcBaseExpress;
import com.ebiz.mmt.domain.EcLuckyMain;
import com.ebiz.mmt.domain.EcLuckyTime;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Pan,Gang
 * @version 2013-09-24
 */
public class EcLuckyMainAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String title_like = (String) dynaBean.get("title_like");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcLuckyMain entity = new EcLuckyMain();
		if (StringUtils.isNotBlank(title_like)) {
			entity.getMap().put("title_like", title_like);

		}

		Long recordCount = super.getFacade().getEcLuckyMainService().getEcLuckyMainCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcLuckyMain> entityList = super.getFacade().getEcLuckyMainService().getEcLuckyMainPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		dynaBean.set("need_addr", "0");
		dynaBean.set("need_mobile", "0");
		dynaBean.set("is_del", "0");
		dynaBean.set("lucky_state", "0");
		dynaBean.set("is_pub", "0");

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String main_pic_hidden = (String) dynaBean.get("main_pic_hidden");
		String[] pic_hidden = request.getParameterValues("pic_hidden");
		String e_id = (String) dynaBean.get("e_id");// 活动开始和结束时间

		String[] had_ids = request.getParameterValues("had_ids");// 修改使用

		if (StringUtils.isBlank(id)) {
			if (isCancelled(request)) {
				return list(mapping, form, request, response);
			}
			if (!isTokenValid(request)) {
				saveError(request, "errors.token");
				return list(mapping, form, request, response);
			}
			resetToken(request);
		}

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcLuckyMain entity = new EcLuckyMain();
		super.copyProperties(entity, form);

		String main_pic_file = "";
		String pic_file = "";
		// 上传主图以及附图
		List<UploadFile> uploadFileList = super.uploadFile(form, true, 0, new int[] { 60, 86, 95, 120, 240, 280, 350,
		        400, 600, 800 });
		if (null != uploadFileList && uploadFileList.size() > 0) {
			// String[] fileArrs = new String[uploadFileList.size()];
			List<String> fileList = new ArrayList<String>();
			for (int i = 0; i < uploadFileList.size(); i++) {
				if ("main_pic_file".equalsIgnoreCase(uploadFileList.get(i).getFormName())) {
					main_pic_file = uploadFileList.get(i).getFileSavePath();
				} else if (uploadFileList.get(i).getFormName().startsWith("pic_file_")) {
					fileList.add(uploadFileList.get(i).getFileSavePath());
				}
			}
			for (String string : fileList) {
				logger.info("______________________________pic_file_" + string);
			}
			pic_file = StringUtils.join(fileList.toArray(), ",");
		}
		if (StringUtils.isBlank(id)) {// 新增商品
			if (StringUtils.isBlank(main_pic_file)) { // 新增产品
				super.renderJavaScript(response, "alert('主图没有上传！');history.back();");
				return null;
			}
		} else {// 修改商品
			if (StringUtils.isBlank(main_pic_file)) { // 没用重新上传主图
				main_pic_file = main_pic_hidden;
			}
			if (StringUtils.isBlank(pic_file)) { // 没有重新上传附图
				if (null != pic_hidden && pic_hidden.length > 0) {
					pic_file = StringUtils.join(pic_hidden, ",");
				}
			} else {
				if (null != pic_hidden && pic_hidden.length > 0) {
					pic_file = pic_file.concat("," + StringUtils.join(pic_hidden, ","));
				}
			}
		}
		logger.info("____________________________pic_file = " + pic_file);
		if (StringUtils.isNotBlank(pic_file)) {
			entity.setMain_pic(main_pic_file.concat("," + pic_file));
		} else {
			entity.setMain_pic(main_pic_file);
		}

		if (StringUtils.isEmpty(id)) {
			entity.setAdd_date(new Date());
			entity.setAdd_user_id(user.getId());
			super.getFacade().getEcLuckyMainService().createEcLuckyMain(entity, e_id);
			super.saveMessage(request, "entity.inserted");
		} else {
			entity.setId(Long.valueOf(id));
			super.getFacade().getEcLuckyMainService().modifyEcLuckyMain(entity, had_ids, e_id);
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

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String express_id = (String) dynaBean.get("express_id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(express_id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		EcBaseExpress entity = new EcBaseExpress();
		entity.setExpress_id(Long.valueOf(express_id));
		super.getFacade().getEcBaseExpressService().removeEcBaseExpress(entity);

		saveMessage(request, "entity.deleted");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.serialize(request, "express_id", "method"));
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

		EcLuckyMain entity = new EcLuckyMain();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getEcLuckyMainService().getEcLuckyMain(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));
		if (null != entity) {
			String main_pic = entity.getMain_pic();
			if (StringUtils.isNotBlank(main_pic)) {
				String main_pic_file = StringUtils.split(main_pic, ",")[0];
				dynaBean.set("main_pic_file", main_pic_file); // 主图

				List<String> list = new ArrayList<String>(); // 附图list
				String[] picArr = StringUtils.split(main_pic, ",");
				if (null != picArr && picArr.length > 0) {
					for (int i = 0; i < picArr.length; i++) {
						if (!StringUtils.equals(main_pic_file, picArr[i])) {
							list.add(picArr[i]);
						}
					}
				}
				request.setAttribute("picList", list);
			}
		}

		EcLuckyTime ect = new EcLuckyTime();
		ect.setLucky_id(Long.valueOf(id));
		List<EcLuckyTime> hadEntityList = super.getFacade().getEcLuckyTimeService().getEcLuckyTimeList(ect);
		request.setAttribute("hadEntityList", hadEntityList);

		super.copyProperties(form, entity);

		return mapping.findForward("input");
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

		EcLuckyMain entity = new EcLuckyMain();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getEcLuckyMainService().getEcLuckyMain(entity);

		if (null != entity) {
			String main_pic = entity.getMain_pic();
			if (StringUtils.isNotBlank(main_pic)) {
				String main_pic_file = StringUtils.split(main_pic, ",")[0];
				dynaBean.set("main_pic_file", main_pic_file); // 主图

				List<String> list = new ArrayList<String>(); // 附图list
				String[] picArr = StringUtils.split(main_pic, ",");
				if (null != picArr && picArr.length > 0) {
					for (int i = 0; i < picArr.length; i++) {
						if (!StringUtils.equals(main_pic_file, picArr[i])) {
							list.add(picArr[i]);
						}
					}
				}
				request.setAttribute("picList", list);
			}
		}

		super.copyProperties(form, entity);

		request.setAttribute("ecLuckyMain", entity);

		return mapping.findForward("view");
	}

	public ActionForward selectEcLuckyTime(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		return new ActionForward("/../manager/spgl/EcLuckyMain/selectEcLuckyTime.jsp");
	}

	public ActionForward deleteFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");

		if (StringUtils.isNotBlank(id) && GenericValidator.isLong(id)) {
			EcLuckyTime entity = new EcLuckyTime();
			entity.setId(new Long(id));
			super.getFacade().getEcLuckyTimeService().removeEcLuckyTime(entity);
		}

		super.renderText(response, "success");
		return null;
	}

}
