package com.ebiz.mmt.web.struts.manager.spgl;

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

import com.ebiz.mmt.domain.EcSpecFbTeamList;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class EcSpecFbTeamListAction extends BaseAction {
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
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String mod_id = (String) dynaBean.get("mod_id");
		String team_name_like = (String) dynaBean.get("team_name_like");
		String group_name = (String) dynaBean.get("group_name");
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session
				.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		EcSpecFbTeamList entity = new EcSpecFbTeamList();
		if (StringUtils.isNotBlank(team_name_like)) {
			entity.getMap().put("team_name_like", team_name_like);
		}
		if (StringUtils.isNotBlank(group_name)) {
			entity.setGroup_name(group_name);
		}
		Long recordCount = super.getFacade().getEcSpecFbTeamListService()
				.getEcSpecFbTeamListCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcSpecFbTeamList> entityList = super.getFacade()
				.getEcSpecFbTeamListService()
				.getEcSpecFbTeamListPaginatedList(entity);
		for (EcSpecFbTeamList ecSpecFbTeamList : entityList) {
			PeProdUser add_user=new PeProdUser();
			if (null!= ecSpecFbTeamList.getAdd_user_id()) {
				add_user.setId(ecSpecFbTeamList.getAdd_user_id());
				//System.out.println("ecSpecFbTeamList.getAdd_user_id():"+ecSpecFbTeamList.getAdd_user_id());
				add_user=super.getFacade().getPeProdUserService().getPeProdUser(add_user);
			}
			if (null!=add_user) {
				ecSpecFbTeamList.getMap().put("add_user", add_user.getUser_name()!=null?add_user.getUser_name():"");	
			}
			
		}
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
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String team_name = (String) dynaBean.get("team_name");
		String group_name = (String) dynaBean.get("group_name");
		String team_intro = (String) dynaBean.get("team_intro");
		String order_sort_num = (String) dynaBean.get("order_sort_num");
		String is_del = (String) dynaBean.get("is_del");
		String team_flag_pic_url_hidden = (String) dynaBean.get("team_flag_pic_url_hidden");
		String team_main_pic_url_hidden = (String) dynaBean.get("team_main_pic_url_hidden");
		
		//String[] pic_hidden = request.getParameterValues("pic_hidden");
		
		String team_flag_pic_url = "";
		String team_main_pic_url = "";

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		EcSpecFbTeamList entity = new EcSpecFbTeamList();
		super.copyProperties(entity, form);
		
		if (team_intro.length()>100) {
			super.renderJavaScript(response, "alert('对不起，球队介绍不能超过100个字！');history.back();");
			return null;
		}
		if (team_intro.length()==0) {
			super.renderJavaScript(response, "alert('对不起，球队介绍不能为空！');history.back();");
			return null;
		}
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}
		if (StringUtils.isNotBlank(team_name)) {
			entity.setTeam_name(String.valueOf(team_name));
		}
		if (StringUtils.isNotBlank(group_name)) {
			entity.setGroup_name(String.valueOf(group_name));
		}
		if (StringUtils.isNotBlank(team_intro)) {
			entity.setTeam_intro(team_intro);
		}
		if (StringUtils.isNotBlank(order_sort_num)) {
			entity.setOrder_sort_num(Long.valueOf(order_sort_num));
		}
		if (StringUtils.isNotBlank(team_intro)) {
			entity.setTeam_intro(team_intro);
		}
		if (StringUtils.isNotBlank(is_del)) {
			entity.setIs_del(Integer.valueOf(is_del));
		}
		entity.setAdd_user_id(user.getId());
		entity.setAdd_date(new Date());

		// 上传球队国旗
		List<UploadFile> uploadFileList = super.uploadFile(form, true, 0, new int[] { 60, 86, 95, 120, 191, 280, 350,
		        400, 600, 800 });
		//System.out.println("uploadFileList.size():"+uploadFileList.size());
		if (null != uploadFileList && uploadFileList.size() > 0) {
			for (int i = 0; i < uploadFileList.size(); i++) {
				if ("team_flag_pic_url".equalsIgnoreCase(uploadFileList.get(i).getFormName())) {
					team_flag_pic_url = uploadFileList.get(i).getFileSavePath();
				} else if ("team_main_pic_url".equalsIgnoreCase(uploadFileList.get(i).getFormName())) {
					team_main_pic_url=uploadFileList.get(i).getFileSavePath();
				}
			}
		}
		
		// 球队国旗
		if (StringUtils.isBlank(id)) {// 新增球队国旗
			if (StringUtils.isBlank(team_flag_pic_url)) { // 新增产品
				super.renderJavaScript(response, "alert('球队国旗没有上传！');history.back();");
				return null;
			}
		} else {// 修改商品
			if (StringUtils.isBlank(team_flag_pic_url)) { // 没用重新上传主图
				team_flag_pic_url = team_flag_pic_url_hidden;
			}
		}
		entity.setTeam_flag_pic_url(team_flag_pic_url);
		
		//球队图片
		if (StringUtils.isBlank(id)) {// 新增商品
			if (StringUtils.isBlank(team_main_pic_url)) { // 新增产品
				super.renderJavaScript(response, "alert('球队图片没有上传！');history.back();");
				return null;
			}
		} else {// 修改商品
			if (StringUtils.isBlank(team_main_pic_url)) { // 没用重新上传主图
				team_main_pic_url = team_main_pic_url_hidden;
			}
		}
		entity.setTeam_main_pic_url(team_main_pic_url);
		if (id!=null&&StringUtils.isNotBlank(id)) {//有id就修改 没有就插入
			super.getFacade().getEcSpecFbTeamListService().modifyEcSpecFbTeamList(entity);
		}else{
			super.getFacade().getEcSpecFbTeamListService().createEcSpecFbTeamList(entity);	
		}
		
		logger.info("++++++++++++++QueryString=" + super.encodeSerializedQueryString(entity.getQueryString()));
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
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
		EcSpecFbTeamList entity = new EcSpecFbTeamList();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getEcSpecFbTeamListService().getEcSpecFbTeamList(entity);
		//entity.setQueryString(super.serialize(request, "id", "method"));
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);
		//request.setAttribute("team_intro",entity.getTeam_intro());
		//logger.info("main_pic--------->"+entity.getTeam_flag_pic_url());
		//dynaBean.set("team_flag_pic_url", entity.getTeam_flag_pic_url());
		//request.setAttribute("team_flag_pic_url", entity.getTeam_flag_pic_url());
		return mapping.findForward("input");
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}
		EcSpecFbTeamList entity = new EcSpecFbTeamList();
		entity.setId(Long.valueOf(id));

		Integer number=super.getFacade().getEcSpecFbTeamListService().removeEcSpecFbTeamList(entity);
		//System.out.println(number);
		entity.setQueryString(super.serialize(request, "id", "method"));
		
		logger.info("++++++++++++++QueryString=" + super.encodeSerializedQueryString(entity.getQueryString()));
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		//return this.list(mapping, form, request, response);
		return forward;
	}
}
