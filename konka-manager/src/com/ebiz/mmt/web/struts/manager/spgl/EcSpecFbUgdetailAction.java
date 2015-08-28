package com.ebiz.mmt.web.struts.manager.spgl;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcSpecFbCal;
import com.ebiz.mmt.domain.EcSpecFbTeamList;
import com.ebiz.mmt.domain.EcSpecFbUgdetail;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.ExcelUtil;
import com.ebiz.ssi.web.struts.bean.Pager;

public class EcSpecFbUgdetailAction extends BaseAction {
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
		String add_time_start = (String) dynaBean.get("add_time_start");
		String add_time_end = (String) dynaBean.get("add_time_end");
		String match_id = (String) dynaBean.get("match_id");
		String user_name_like = (String) dynaBean.get("user_name_like");
		String real_name_like = (String) dynaBean.get("real_name_like");
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session
				.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		EcSpecFbUgdetail entity = new EcSpecFbUgdetail();
	    if (StringUtils.isNotBlank(add_time_start)) {
			entity.getMap().put("time_start", add_time_start);
		}
		if (StringUtils.isNotBlank(add_time_end)) {
			entity.getMap().put("time_end", add_time_end);
		}
		if (StringUtils.isNotBlank(match_id)) {
			entity.setMatch_id(Long.valueOf(match_id));
		}
		
		 if (StringUtils.isNotBlank(user_name_like)) {
				entity.getMap().put("user_name_like", user_name_like);
		 }
		 if (StringUtils.isNotBlank(real_name_like)) {
				entity.getMap().put("real_name_like", real_name_like);
		 }
		
		List<EcSpecFbCal> teamList = super.getFacade().getEcSpecFbCalService()
				.getEcSpecFbCalList(new EcSpecFbCal());
		for (EcSpecFbCal ecSpecFbCal : teamList) {
			EcSpecFbTeamList ea = new EcSpecFbTeamList();
			ea.setId(ecSpecFbCal.getA_team_id());
			ea = super.getFacade().getEcSpecFbTeamListService()
					.getEcSpecFbTeamList(ea);

			EcSpecFbTeamList eb = new EcSpecFbTeamList();
			eb.setId(ecSpecFbCal.getB_team_id());
			eb = super.getFacade().getEcSpecFbTeamListService()
					.getEcSpecFbTeamList(eb);
			String team_group_name_a="";
			String team_group_name_b="";
			if (null!=ea.getTeam_name()) {
				team_group_name_a+=ea.getTeam_name();
			}
			if (null!=ea.getGroup_name()) {
				team_group_name_a+="("+ea.getGroup_name()+")";
			}
			if (null!=eb.getTeam_name()) {
				team_group_name_b+=eb.getTeam_name();
			}
			if (null!=eb.getGroup_name()) {
				team_group_name_b+="("+eb.getGroup_name()+")";
			}
			ecSpecFbCal.getMap().put("team_name_a", team_group_name_a);
			ecSpecFbCal.getMap().put("team_name_b", team_group_name_b);

		}
		request.setAttribute("teamList", teamList);
		
		
		Long recordCount = super.getFacade().getEcSpecFbUgdetailService()
				.getEcSpecFbUgdetailCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
      
		List<EcSpecFbUgdetail> entityList = super.getFacade()
				.getEcSpecFbUgdetailService()
				.getEcSpecFbUgdetailPaginatedList(entity);
		for (EcSpecFbUgdetail ecSpecFbUgdetail : entityList) {
			EcSpecFbCal fb_cal = new EcSpecFbCal();
			fb_cal.setMatch_id(ecSpecFbUgdetail.getMatch_id());
			fb_cal = super.getFacade().getEcSpecFbCalService().getEcSpecFbCal(fb_cal);
			
			EcSpecFbTeamList ea_list=new EcSpecFbTeamList();
			EcSpecFbTeamList eb_list=new EcSpecFbTeamList();
			if (null!=fb_cal) {
				ea_list.setId(fb_cal.getA_team_id());
				ea_list=super.getFacade().getEcSpecFbTeamListService().getEcSpecFbTeamList(ea_list);
				ecSpecFbUgdetail.getMap().put("team_name_a", ea_list.getTeam_name()+"("+ea_list.getGroup_name()+")");
				
				eb_list.setId(fb_cal.getB_team_id());
				eb_list=super.getFacade().getEcSpecFbTeamListService().getEcSpecFbTeamList(eb_list);
				ecSpecFbUgdetail.getMap().put("team_name_b", eb_list.getTeam_name()+"("+eb_list.getGroup_name()+")");
			}
			
			EcUser ec_user=new EcUser();
			ec_user.setId(ecSpecFbUgdetail.getGuess_user_id());
			ec_user=super.getFacade().getEcUserService().getEcUser(ec_user);
			if (null!=ec_user) {
				ecSpecFbUgdetail.getMap().put("guess_user", ec_user.getUser_name()==null?"":ec_user.getUser_name());
				ecSpecFbUgdetail.getMap().put("real_user", ec_user.getReal_name()==null?"":ec_user.getReal_name());
			}
		}
		
		request.setAttribute("entityList", entityList);
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
		List<EcSpecFbCal> teamList = super.getFacade().getEcSpecFbCalService()
				.getEcSpecFbCalList(new EcSpecFbCal());
		for (EcSpecFbCal ecSpecFbCal : teamList) {
			EcSpecFbTeamList ea = new EcSpecFbTeamList();
			ea.setId(ecSpecFbCal.getA_team_id());
			ea = super.getFacade().getEcSpecFbTeamListService()
					.getEcSpecFbTeamList(ea);

			EcSpecFbTeamList eb = new EcSpecFbTeamList();
			eb.setId(ecSpecFbCal.getB_team_id());
			eb = super.getFacade().getEcSpecFbTeamListService()
					.getEcSpecFbTeamList(eb);

			ecSpecFbCal.getMap().put("team_name_a", ea.getTeam_name());
			ecSpecFbCal.getMap().put("team_name_b", eb.getTeam_name());

		}
		request.setAttribute("teamList", teamList);
		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}
		EcSpecFbUgdetail entity = new EcSpecFbUgdetail();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getEcSpecFbUgdetailService()
				.getEcSpecFbUgdetail(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		List<EcSpecFbCal> teamList = super.getFacade().getEcSpecFbCalService()
				.getEcSpecFbCalList(new EcSpecFbCal());
		for (EcSpecFbCal ecSpecFbCal : teamList) {
			EcSpecFbTeamList ea = new EcSpecFbTeamList();
			ea.setId(ecSpecFbCal.getA_team_id());
			ea = super.getFacade().getEcSpecFbTeamListService()
					.getEcSpecFbTeamList(ea);

			EcSpecFbTeamList eb = new EcSpecFbTeamList();
			eb.setId(ecSpecFbCal.getB_team_id());
			eb = super.getFacade().getEcSpecFbTeamListService()
					.getEcSpecFbTeamList(eb);

			ecSpecFbCal.getMap().put("team_name_a", ea.getTeam_name());
			ecSpecFbCal.getMap().put("team_name_b", eb.getTeam_name());

		}
		request.setAttribute("teamList", teamList);
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String match_id = (String) dynaBean.get("match_id");

		String guess_win = (String) dynaBean.get("guess_win");// 竞猜输赢
		String guess_a_team_goal = (String) dynaBean.get("guess_a_team_goal");// 竞猜主队得分
		String guess_b_team_goal = (String) dynaBean.get("guess_b_team_goal");// 竞猜客队得分

		Integer guess_win_1 = Integer.valueOf(guess_win);// 竞猜输赢
		Integer guess_a_team_goal_1 = Integer.valueOf(guess_a_team_goal);// 竞猜主队得分
		Integer guess_b_team_goal_1 = Integer.valueOf(guess_b_team_goal);// 竞猜客队得分
		
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session
				.getAttribute(Constants.USER_INFO);

		EcSpecFbCal fb_cal = new EcSpecFbCal();
		fb_cal.setMatch_id(Long.valueOf(match_id));
		fb_cal = super.getFacade().getEcSpecFbCalService()
				.getEcSpecFbCal(fb_cal);
		Integer playwin = fb_cal.getPlay_status();// 输赢
		Integer a_team_goal = fb_cal.getA_team_goal();// 主队得分
		Integer b_team_goal = fb_cal.getB_team_goal();// 客队得分

		EcSpecFbUgdetail entity = new EcSpecFbUgdetail();
		super.copyProperties(entity, form);
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}
		if (StringUtils.isNotBlank(guess_win)) {
			if (null!=playwin) {
				if (playwin.intValue() == guess_win_1.intValue()) {
					entity.setGuess_win_gift(30);
				}	
			}
		}
		if (StringUtils.isNotBlank(guess_a_team_goal)
				&& StringUtils.isNotBlank(guess_b_team_goal)) {
			if (null!=a_team_goal&&null!=b_team_goal) {
				if (guess_a_team_goal_1.intValue() == a_team_goal.intValue()
						&& guess_b_team_goal_1 == b_team_goal) {
					entity.setGuess_goal_gift(50);
				} else {
					entity.setGuess_goal_gift(-15);
				}
			}
		}
        entity.setMatch_id(Long.valueOf(match_id));
		entity.setGuess_win(guess_win_1);
		entity.setGuess_a_team_goal(guess_a_team_goal_1);
		entity.setGuess_b_team_goal(guess_b_team_goal_1);
		entity.setGuess_user_id(user.getId());
		entity.setGuess_date(new Date());

		if (id != null && StringUtils.isNotBlank(id)) {// 有id就修改 没有就插入
			super.getFacade().getEcSpecFbUgdetailService()
					.modifyEcSpecFbUgdetail(entity);
		} else {
			super.getFacade().getEcSpecFbUgdetailService()
					.createEcSpecFbUgdetail(entity);
		}

		logger.info("++++++++++++++QueryString="
				+ super.encodeSerializedQueryString(entity.getQueryString()));
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append(
				super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// return this.list(mapping, form, request, response);
		return forward;
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
		EcSpecFbUgdetail entity = new EcSpecFbUgdetail();
		entity.setId(Long.valueOf(id));

		Integer number=super.getFacade().getEcSpecFbUgdetailService().removeEcSpecFbUgdetail(entity);
		//System.out.println(number);
		entity.setQueryString(super.serialize(request, "id", "method"));
		
		logger.info("++++++++++++++QueryString=" + super.encodeSerializedQueryString(entity.getQueryString()));
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
	
	public ActionForward excel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String add_time_start = (String) dynaBean.get("add_time_start");
		String add_time_end = (String) dynaBean.get("add_time_end");
		String match_id = (String) dynaBean.get("match_id");
		String user_name_like = (String) dynaBean.get("user_name_like");
		String real_name_like = (String) dynaBean.get("real_name_like");
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session
				.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		EcSpecFbUgdetail entity = new EcSpecFbUgdetail();
	    if (StringUtils.isNotBlank(add_time_start)) {
			entity.getMap().put("time_start", add_time_start);
		}
		if (StringUtils.isNotBlank(add_time_end)) {
			entity.getMap().put("time_end", add_time_end);
		}
		if (StringUtils.isNotBlank(match_id)) {
			entity.setMatch_id(Long.valueOf(match_id));
		}
		 if (StringUtils.isNotBlank(user_name_like)) {
				entity.getMap().put("user_name_like", user_name_like);
		 }
		 if (StringUtils.isNotBlank(real_name_like)) {
				entity.getMap().put("real_name_like", real_name_like);
		 }
		/**
		 * 查找根据球队id查找球队信息
		 */
		List<EcSpecFbCal> teamList = super.getFacade().getEcSpecFbCalService()
				.getEcSpecFbCalList(new EcSpecFbCal());
		for (EcSpecFbCal ecSpecFbCal : teamList) {
			EcSpecFbTeamList ea = new EcSpecFbTeamList();
			ea.setId(ecSpecFbCal.getA_team_id());
			ea = super.getFacade().getEcSpecFbTeamListService()
					.getEcSpecFbTeamList(ea);

			EcSpecFbTeamList eb = new EcSpecFbTeamList();
			eb.setId(ecSpecFbCal.getB_team_id());
			eb = super.getFacade().getEcSpecFbTeamListService()
					.getEcSpecFbTeamList(eb);

			ecSpecFbCal.getMap().put("team_name_a", ea.getTeam_name());
			ecSpecFbCal.getMap().put("team_name_b", eb.getTeam_name());

		}
		request.setAttribute("teamList", teamList);
		
		/*List<EcSpecFbUgdetail> entityList = super.getFacade()
				.getEcSpecFbUgdetailService()
				.getEcSpecFbUgdetailList(entity);*/
		List<EcSpecFbUgdetail> entityList = super.getFacade()
				.getEcSpecFbUgdetailService()
				.getEcSpecFbUgdetailForExecList(entity);
		for (EcSpecFbUgdetail ecSpecFbUgdetail : entityList) {
			EcSpecFbCal fb_cal = new EcSpecFbCal();
			fb_cal.setMatch_id(ecSpecFbUgdetail.getMatch_id());
			fb_cal = super.getFacade().getEcSpecFbCalService().getEcSpecFbCal(fb_cal);
			
			EcSpecFbTeamList ea_list=new EcSpecFbTeamList();
			EcSpecFbTeamList eb_list=new EcSpecFbTeamList();
			if (null!=fb_cal) {
				ea_list.setId(fb_cal.getA_team_id());
				ea_list=super.getFacade().getEcSpecFbTeamListService().getEcSpecFbTeamList(ea_list);
				ecSpecFbUgdetail.getMap().put("team_name_a", ea_list.getTeam_name());
				ecSpecFbUgdetail.getMap().put("group_name_a", ea_list.getGroup_name());
				
				eb_list.setId(fb_cal.getB_team_id());
				eb_list=super.getFacade().getEcSpecFbTeamListService().getEcSpecFbTeamList(eb_list);
				ecSpecFbUgdetail.getMap().put("team_name_b", eb_list.getTeam_name());
				ecSpecFbUgdetail.getMap().put("group_name_b", eb_list.getGroup_name());
			}
			
			EcUser ec_user=new EcUser();
			ec_user.setId(ecSpecFbUgdetail.getGuess_user_id());
			ec_user=super.getFacade().getEcUserService().getEcUser(ec_user);
			if (null!=ec_user) {
				ecSpecFbUgdetail.getMap().put("guess_user", ec_user.getUser_name()==null?"":ec_user.getUser_name());
				ecSpecFbUgdetail.getMap().put("real_user", ec_user.getReal_name()==null?"":ec_user.getReal_name());
			}
		}

		ExcelUtil e = new ExcelUtil();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		int r = 0;
		e.setWorkbook(workbook);
		e.setSheet(sheet);
		e.createRow(r);
		e.setCell(0, "序号");
		e.setCell(1, "工卡号");
		e.setCell(2, "真实姓名");
		e.setCell(3, "比赛队");
		e.setCell(4, "竞猜时间");
		e.setCell(5, "竞猜输赢");
		e.setCell(6, "竞猜主队得分");
		e.setCell(7, "竞猜客队得分");
		e.setCell(8, "竞猜输赢得积分");
		e.setCell(9, "竞猜得分赢得积分数");
		e.setCell(10, "单场积分");
		e.setCell(11, "个人当前总积分");
		for (EcSpecFbUgdetail esfu : entityList) {
			r++;
			e.createRow(r);
			e.setCell(0, r);
			String guess_user="";
			if (null!=esfu.getMap().get("guess_user")) {
				guess_user=String.valueOf(esfu.getMap().get("guess_user"));
			}
			e.setCell(1, guess_user);
			String real_user="";
			if (null!=esfu.getMap().get("real_user")) {
				real_user=String.valueOf(esfu.getMap().get("real_user"));
			}
			e.setCell(2, real_user);
			Object teamA=esfu.getMap().get("team_name_a");
			Object teamB=esfu.getMap().get("team_name_b");
			Object group_name_a=esfu.getMap().get("group_name_a");
			Object group_name_b=esfu.getMap().get("group_name_b");
			String teamAB="";
			if (teamA!=null&&teamB!=null&&group_name_a!=null&&group_name_b!=null) {
				teamAB="("+String.valueOf(group_name_a)+")"+String.valueOf(teamA)+"-"+"("+String.valueOf(group_name_b)+")"+String.valueOf(teamB);
			}
			e.setCell(3, teamAB);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String guess_time=format.format(esfu.getGuess_date());
			e.setCell(4, guess_time==null?"":guess_time);
			Integer guess_win= esfu.getGuess_win();
			String guessToString="";
			if (guess_win!=null) {
				switch (guess_win) {
				case 0:
					guessToString="赢";
					break;
	             case 1:
	            	 guessToString="平";
					break;
				default:
					guessToString="输";
					break;
				}	
			}else {
				guessToString="";
			}
			e.setCell(5, guessToString);
			Integer guess_a_team_goal=0;
			if (null!=esfu.getGuess_a_team_goal()) {
				guess_a_team_goal=esfu.getGuess_a_team_goal();;
			}
			e.setCell(6, guess_a_team_goal);
			String guess_b_team_goal="";
			if (null!=esfu.getGuess_b_team_goal()) {
				guess_b_team_goal=String.valueOf(esfu.getGuess_b_team_goal());
			}
			e.setCell(7, guess_b_team_goal);
			Integer guess_win_gift=0;
			Integer danjifen=0;
			String total_score="";
			if (null!=esfu.getGuess_win_gift()) {
				guess_win_gift=esfu.getGuess_win_gift();
				danjifen+=esfu.getGuess_win_gift();
			}
			e.setCell(8, guess_win_gift);
			Integer guess_goal_gift=0;
			if (null!=esfu.getGuess_goal_gift()) {
				guess_goal_gift=esfu.getGuess_goal_gift();
				danjifen+=esfu.getGuess_goal_gift();
			}
			e.setCell(9, guess_goal_gift);
			e.setCell(10, danjifen);
			/*EcSpecFbUgdetail jifeng=new EcSpecFbUgdetail();
			jifeng.setGuess_user_id(esfu.getGuess_user_id());
			List<EcSpecFbUgdetail> jifenglist = super.getFacade()
					.getEcSpecFbUgdetailService()
					.getEcSpecFbUgdetailList(jifeng);
			for (EcSpecFbUgdetail ecSpecFbUgdetail : jifenglist) {
				if (null!=ecSpecFbUgdetail.getGuess_win_gift()) {
					danjifenS+=ecSpecFbUgdetail.getGuess_win_gift();
				}
				if (null!=ecSpecFbUgdetail.getGuess_goal_gift()) {
					danjifenS+=ecSpecFbUgdetail.getGuess_goal_gift();
				}
			}*/
			if (null!=esfu.getMap().get("total_score")) {
				total_score=String.valueOf(esfu.getMap().get("total_score"));
			}
			e.setCell(11, total_score); 
		}
		// 输出
		response.setHeader("Content-disposition", "attachment; filename=jicai_record.xls");// 设定输出文件头
		response.setContentType("application/vnd.ms-excel");// 定义输出类型
		OutputStream out = response.getOutputStream();
		e.exportXLS(out);
		return null;
	}
}
