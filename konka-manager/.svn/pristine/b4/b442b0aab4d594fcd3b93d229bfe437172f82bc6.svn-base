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

import com.ebiz.mmt.domain.EcVoteOption;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author TUDP
 * @version 2014-10-29
 */
public class EcVoteOptionAction extends BaseAction {
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
		String vote_id = (String) dynaBean.get("vote_id");
		
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcVoteOption entity = new EcVoteOption();
		if (StringUtils.isNotBlank(title_like)) {
			entity.getMap().put("title_like", title_like);
		}
		if (StringUtils.isNotBlank(vote_id)) {
			entity.setVote_id(new Long(vote_id));
		}
		Long recordCount = super.getFacade().getEcVoteOptionService().getEcVoteOptionCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcVoteOption> entityList = super.getFacade().getEcVoteOptionService().getEcVoteOptionPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		} 
		dynaBean.set("is_del", "0");
		dynaBean.set("vote_state", "0");
		dynaBean.set("is_pub", "0");

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String vote_id = (String) dynaBean.get("vote_id");
		String main_pic_hidden = (String) dynaBean.get("main_pic_hidden");
		String[] pic_hidden = request.getParameterValues("pic_hidden"); 

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcVoteOption entity = new EcVoteOption();
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
			pic_file = StringUtils.join(fileList.toArray(), ",");
		}
		if (StringUtils.isBlank(id)) {// 新增商品
			if (StringUtils.isBlank(main_pic_file)) { // 新增产品
				super.renderJavaScript(response, "alert('主图没有上传！');history.back();");
				return null;
			}
		} else {// 修改
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
		
		if (StringUtils.isNotBlank(pic_file)) {
			entity.setMain_pic(main_pic_file.concat("," + pic_file));
		} else {
			entity.setMain_pic(main_pic_file);
		}

		if (StringUtils.isEmpty(id)) {
			entity.setAdd_date(new Date());
			entity.setAdd_user_id(user.getId());
			super.getFacade().getEcVoteOptionService().createEcVoteOption(entity);
			super.saveMessage(request, "entity.inserted");
		} else {
			entity.setId(Long.valueOf(id));
			super.getFacade().getEcVoteOptionService().modifyEcVoteOption(entity);
			super.saveMessage(request, "entity.updated");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&vote_id=" + vote_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

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

		EcVoteOption entity = new EcVoteOption();
		entity.setId(Long.valueOf(id));
		super.getFacade().getEcVoteOptionService().removeEcVoteOption(entity);

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

		EcVoteOption entity = new EcVoteOption();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getEcVoteOptionService().getEcVoteOption(entity);
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

		EcVoteOption entity = new EcVoteOption();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getEcVoteOptionService().getEcVoteOption(entity);

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

		request.setAttribute("ecVoteOption", entity);

		return mapping.findForward("view");
	}
 

	public ActionForward deleteFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id"); 
		super.renderText(response, "success");
		return null;
	}

}
