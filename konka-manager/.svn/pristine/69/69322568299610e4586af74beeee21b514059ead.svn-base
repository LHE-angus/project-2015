package com.ebiz.mmt.web.struts.manager.spgl;

import java.text.SimpleDateFormat;
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

import com.ebiz.mmt.domain.EcSpecFbCal;
import com.ebiz.mmt.domain.EcSpecFbTeamList;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class EcSpecFbCalAction extends BaseAction {
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
		String a_team_id = (String) dynaBean.get("a_team_id");
		String b_team_id = (String) dynaBean.get("b_team_id");
		String add_time_start = (String) dynaBean.get("add_time_start");
		String add_time_end = (String) dynaBean.get("add_time_end");
		
		
		HttpSession session = request.getSession();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		PeProdUser user = (PeProdUser) session
				.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		EcSpecFbCal entity = new EcSpecFbCal();
		if (StringUtils.isNotBlank(a_team_id)) {
			entity.setA_team_id(Long.valueOf(a_team_id));
		}
		if (StringUtils.isNotBlank(b_team_id)) {
			entity.setB_team_id(Long.valueOf(b_team_id));
		}
		if (StringUtils.isNotBlank(add_time_start)) {
			
			entity.getMap().put("time_start", add_time_start);
		}
		if (StringUtils.isNotBlank(add_time_end)) {
			entity.getMap().put("time_end", add_time_end);
		}

		Long recordCount = super.getFacade().getEcSpecFbCalService()
				.getEcSpecFbCalCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
		List<EcSpecFbCal> entityList = super.getFacade()
				.getEcSpecFbCalService().getEcSpecFbCalPaginatedList(entity);
		for (EcSpecFbCal ecSpecFbCal : entityList) {
			EcSpecFbTeamList fb_team_a=new EcSpecFbTeamList();
			fb_team_a.setId(ecSpecFbCal.getA_team_id());
			fb_team_a= super.getFacade().getEcSpecFbTeamListService().getEcSpecFbTeamList(fb_team_a);
			
			EcSpecFbTeamList fb_team_b=new EcSpecFbTeamList();
			fb_team_b.setId(ecSpecFbCal.getB_team_id());
			fb_team_b= super.getFacade().getEcSpecFbTeamListService().getEcSpecFbTeamList(fb_team_b);
			
			PeProdUser add_user=new PeProdUser();
			if (null!= ecSpecFbCal.getAdd_user_id()) {
				add_user.setId(ecSpecFbCal.getAdd_user_id());
				//System.out.println("ecSpecFbCal.getAdd_user_id():"+ecSpecFbCal.getAdd_user_id());
				add_user=super.getFacade().getPeProdUserService().getPeProdUser(add_user);
			}
			
			
			PeProdUser gift_user=new PeProdUser();
            if (null!= ecSpecFbCal.getGift_add_user_id()) {
            	gift_user.setId(ecSpecFbCal.getGift_add_user_id());
    			//System.out.println("ecSpecFbCal.getGift_add_user_id():"+ecSpecFbCal.getGift_add_user_id());
    			gift_user=super.getFacade().getPeProdUserService().getPeProdUser(gift_user);
			}
            String team_group_name_a="";
			String team_group_name_b="";
			if (null!=fb_team_a.getTeam_name()) {
				team_group_name_a+=fb_team_a.getTeam_name();
			}
			if (null!=fb_team_a.getGroup_name()) {
				team_group_name_a+="("+fb_team_a.getGroup_name()+")";
			}
			if (null!=fb_team_b.getTeam_name()) {
				team_group_name_b+=fb_team_b.getTeam_name();
			}
			if (null!=fb_team_b.getGroup_name()) {
				team_group_name_b+="("+fb_team_b.getGroup_name()+")";
			}
			ecSpecFbCal.getMap().put("a_team", team_group_name_a);
			ecSpecFbCal.getMap().put("b_team", team_group_name_b);
			
			if (null!=add_user) {
				ecSpecFbCal.getMap().put("add_user",add_user.getUser_name() == null ? "" : add_user.getUser_name());
			}
			if (null!=gift_user) {
				ecSpecFbCal.getMap().put("gift_user",gift_user.getUser_name() == null ? "" : gift_user.getUser_name());
			}
		}
		request.setAttribute("entityList", entityList);
		List<EcSpecFbTeamList> teamList = super.getFacade()
				.getEcSpecFbTeamListService().getEcSpecFbTeamListList(new EcSpecFbTeamList());
		request.setAttribute("teamList", teamList);
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		setNaviStringToRequestScope(form, request);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session
				.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		List<EcSpecFbTeamList> teamList = super.getFacade()
				.getEcSpecFbTeamListService().getEcSpecFbTeamListList(new EcSpecFbTeamList());
		
		request.setAttribute("teamList", teamList);
		
		return mapping.findForward("input");
	}
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String match_id = (String) dynaBean.get("match_id");
		if (!GenericValidator.isLong(match_id)) {
			super.saveError(request, "errors.long", "match_id");
			return this.list(mapping, form, request, response);
		}
		EcSpecFbCal entity = new EcSpecFbCal();
		entity.setMatch_id(Long.valueOf(match_id));

		entity =super.getFacade()
				.getEcSpecFbCalService().getEcSpecFbCal(entity);
		Date playdate=entity.getPlay_time();
		Integer hour= playdate.getHours();
		String hour1="";
		if (hour==0) {
			hour1="00";
		}else if (1<=hour&&hour<10) {
			hour1="0"+hour;
		}else {
			hour1=hour+"";
		}
		//System.out.println("hour1:"+hour1);
		dynaBean.set("hour", hour1);
		entity.setQueryString(super.serialize(request, "match_id", "method"));
		super.copyProperties(form, entity);
		
		List<EcSpecFbTeamList> teamList = super.getFacade()
				.getEcSpecFbTeamListService().getEcSpecFbTeamListList(new EcSpecFbTeamList());
		request.setAttribute("teamList", teamList);
		//request.setAttribute("team_intro",entity.getTeam_intro());
		//logger.info("main_pic--------->"+entity.getTeam_flag_pic_url());
		//dynaBean.set("team_flag_pic_url", entity.getTeam_flag_pic_url());
		//request.setAttribute("team_flag_pic_url", entity.getTeam_flag_pic_url());
		return mapping.findForward("input");
	}
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String match_id = (String) dynaBean.get("match_id");
		String a_team_id = (String) dynaBean.get("a_team_id");
		String b_team_id = (String) dynaBean.get("b_team_id");
		String play_time = (String) dynaBean.get("play_time");
		String hour = (String) dynaBean.get("hour");
		String play_status = (String) dynaBean.get("play_status");
		String a_team_goal = (String) dynaBean.get("a_team_goal");
		String b_team_goal = (String) dynaBean.get("b_team_goal");
		String order_sort_num = (String) dynaBean.get("order_sort_num");
		String is_del = (String) dynaBean.get("is_del");
		String a_team_result = (String) dynaBean.get("a_team_result");
		String b_team_result = (String) dynaBean.get("b_team_result");
		String match_addr = (String) dynaBean.get("match_addr");
		String match_desc = (String) dynaBean.get("match_desc");
        
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		EcSpecFbCal entity = new EcSpecFbCal();
		super.copyProperties(entity, form);
		
		if (StringUtils.isNotBlank(match_id)) {
			entity.setMatch_id(Long.valueOf(match_id));
		}
		if (StringUtils.isNotBlank(a_team_id)) {
			entity.setA_team_id(Long.valueOf(a_team_id));
		}
		if (StringUtils.isNotBlank(b_team_id)) {
			entity.setB_team_id(Long.valueOf(b_team_id));
		}
		//System.out.println("play_time："+play_time+"hour:"+hour);
		if (StringUtils.isNotBlank(play_time)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String hour1=hour!=""&&hour!=null?hour:"00";
			Date date = sdf.parse(play_time+" "+hour1+":00:00");
			entity.setPlay_time(date);
		}
		if (StringUtils.isNotBlank(play_status)) {
			entity.setPlay_status(Integer.valueOf(play_status));
		}
		if (StringUtils.isNotBlank(a_team_goal)) {
			entity.setA_team_goal(Integer.valueOf(a_team_goal));
		}
		if (StringUtils.isNotBlank(b_team_goal)) {
			entity.setB_team_goal(Integer.valueOf(b_team_goal));
		}
		if (StringUtils.isNotBlank(order_sort_num)) {
			entity.setOrder_sort_num(Long.valueOf(order_sort_num));
		}
		if (StringUtils.isNotBlank(is_del)) {
			entity.setIs_del(Integer.valueOf(is_del));
		}
		if (StringUtils.isNotBlank(a_team_result)) {
			entity.setA_team_result(Integer.valueOf(a_team_result));
		}
		if (StringUtils.isNotBlank(b_team_result)) {
			entity.setB_team_result(Integer.valueOf(b_team_result));
		}
		if (StringUtils.isNotBlank(match_addr)) {
			entity.setMatch_addr(match_addr);
		}
		if (StringUtils.isNotBlank(match_desc)) {
			entity.setMatch_desc(match_desc);
		}
		entity.setAdd_date(new Date());
		
		if (StringUtils.isNotBlank(a_team_goal)&&StringUtils.isNotBlank(b_team_goal)) {
			Integer a_team = Integer.valueOf(a_team_goal);
		    Integer b_team  = Integer.valueOf(b_team_goal);
			if (a_team>b_team) {
				entity.setPlay_status(0);
				entity.setA_team_result(3);
				entity.setB_team_result(0);
			}else if (a_team==b_team) {
				entity.setPlay_status(1);
				entity.setA_team_result(1);
				entity.setB_team_result(1);
			}else {
				entity.setPlay_status(2);
				entity.setA_team_result(0);
				entity.setB_team_result(3);
			}
			
			if (null!=user) {
				entity.setAdd_user_id(user.getId());
				entity.setGift_add_user_id(user.getId());
			}
			entity.setGift_add_date(new Date());
		}
		
		if (match_id!=null&&StringUtils.isNotBlank(match_id)) {//有id就修改 没有就插入
			super.getFacade().getEcSpecFbCalService().modifyEcSpecFbCal(entity);
		}else{
			super.getFacade().getEcSpecFbCalService().createEcSpecFbCal(entity);	
		}
		
		logger.info("++++++++++++++QueryString=" + super.encodeSerializedQueryString(entity.getQueryString()));
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		//return this.list(mapping, form, request, response);
		return forward;
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String match_id = (String) dynaBean.get("match_id");
		String mod_id = (String) dynaBean.get("mod_id");
		if (!GenericValidator.isLong(match_id)) {
			super.saveError(request, "errors.long", "match_id");
			return this.list(mapping, form, request, response);
		}
		EcSpecFbCal entity = new EcSpecFbCal();
		entity.setMatch_id(Long.valueOf(match_id));

		Integer number=super.getFacade().getEcSpecFbCalService().removeEcSpecFbCal(entity);
		//System.out.println(number);
		entity.setQueryString(super.serialize(request, "match_id", "method"));
		
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
