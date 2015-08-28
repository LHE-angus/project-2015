package com.ebiz.mmt.web.struts.member;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.EcSpecFbCal;
import com.ebiz.mmt.domain.EcSpecFbTeamList;
import com.ebiz.mmt.domain.EcSpecFbUgdetail;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaPeArticleInfo;

/**
 * @author Pan,Gang
 * @version 2014-06-12
 */
public class SjbAction extends BaseMemberAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		if (null == ecUser) {
			String msg = super.getMessage(request, "ec.nologin");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='"
			        + super.getCtxPath(request) + "/member/login.do';}");
			return null;
		}

		// 赛程竞猜列表
		EcSpecFbCal esf = new EcSpecFbCal();

		Date date = new Date();
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = sf1.format(date);
		esf.getMap().put("add_date", now);
		esf.getRow().setCount(4);
		esf.getMap().put("order_by_play_time", true);

		List<EcSpecFbCal> entityList = super.getFacade().getEcSpecFbCalService().getEcSpecFbCalList(esf);
		for (EcSpecFbCal ecSpecFbCal : entityList) {
			EcSpecFbTeamList s1 = new EcSpecFbTeamList();
			s1.setId(ecSpecFbCal.getA_team_id());
			s1 = super.getFacade().getEcSpecFbTeamListService().getEcSpecFbTeamList(s1);
			ecSpecFbCal.getMap().put("team_pic_1", s1.getTeam_flag_pic_url());
			ecSpecFbCal.getMap().put("team_name_1", s1.getTeam_name());
			ecSpecFbCal.getMap().put("team_main_pic_1", s1.getTeam_main_pic_url());

			// 处理时间
			Date dd = ecSpecFbCal.getPlay_time();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sf.format(dd);
			ecSpecFbCal.getMap().put("month", format1(time.substring(5, 7)));
			ecSpecFbCal.getMap().put("day", format1(time.substring(8, 10)));
			ecSpecFbCal.getMap().put("hour", format1(time.substring(11, 13)));
			ecSpecFbCal.getMap().put("minute", time.substring(14, 16));

			EcSpecFbTeamList s2 = new EcSpecFbTeamList();
			s2.setId(ecSpecFbCal.getB_team_id());
			s2 = super.getFacade().getEcSpecFbTeamListService().getEcSpecFbTeamList(s2);
			ecSpecFbCal.getMap().put("team_pic_2", s2.getTeam_flag_pic_url());
			ecSpecFbCal.getMap().put("team_name_2", s2.getTeam_name());
			ecSpecFbCal.getMap().put("team_main_pic_2", s2.getTeam_main_pic_url());

		}

		request.setAttribute("entityList", entityList);
		request.setAttribute("size", entityList.size());

		// 分组排名A
		EcSpecFbCal ea = new EcSpecFbCal();
		ea.getMap().put("group_name", "A组");
		List<EcSpecFbCal> groupAlist = super.getFacade().getEcSpecFbCalService().getEcSpecFbCalForTjList(ea);
		request.setAttribute("groupAlist", groupAlist);

		// 分组排名B
		EcSpecFbCal eb = new EcSpecFbCal();
		eb.getMap().put("group_name", "B组");
		List<EcSpecFbCal> groupBlist = super.getFacade().getEcSpecFbCalService().getEcSpecFbCalForTjList(eb);
		request.setAttribute("groupBlist", groupBlist);

		// 分组排名C
		EcSpecFbCal ec = new EcSpecFbCal();
		ec.getMap().put("group_name", "C组");
		List<EcSpecFbCal> groupClist = super.getFacade().getEcSpecFbCalService().getEcSpecFbCalForTjList(ec);
		request.setAttribute("groupClist", groupClist);

		// 分组排名D
		EcSpecFbCal ed = new EcSpecFbCal();
		ed.getMap().put("group_name", "D组");
		List<EcSpecFbCal> groupDlist = super.getFacade().getEcSpecFbCalService().getEcSpecFbCalForTjList(ed);
		request.setAttribute("groupDlist", groupDlist);

		// 分组排名E
		EcSpecFbCal ee = new EcSpecFbCal();
		ee.getMap().put("group_name", "E组");
		List<EcSpecFbCal> groupElist = super.getFacade().getEcSpecFbCalService().getEcSpecFbCalForTjList(ee);
		request.setAttribute("groupElist", groupElist);

		// 分组排名F
		EcSpecFbCal ef = new EcSpecFbCal();
		ef.getMap().put("group_name", "F组");
		List<EcSpecFbCal> groupFlist = super.getFacade().getEcSpecFbCalService().getEcSpecFbCalForTjList(ef);
		request.setAttribute("groupFlist", groupFlist);

		// 分组排名G
		EcSpecFbCal eg = new EcSpecFbCal();
		eg.getMap().put("group_name", "G组");
		List<EcSpecFbCal> groupGlist = super.getFacade().getEcSpecFbCalService().getEcSpecFbCalForTjList(eg);
		request.setAttribute("groupGlist", groupGlist);

		// 分组排名D
		EcSpecFbCal eh = new EcSpecFbCal();
		eh.getMap().put("group_name", "H组");
		List<EcSpecFbCal> groupHlist = super.getFacade().getEcSpecFbCalService().getEcSpecFbCalForTjList(eh);
		request.setAttribute("groupHlist", groupHlist);

		// 用户积分排行榜1-4
		EcSpecFbUgdetail user_jf = new EcSpecFbUgdetail();
		user_jf.getRow().setFirst(0);
		user_jf.getRow().setCount(4);
		List<EcSpecFbUgdetail> jfList_1_4 = super.getFacade().getEcSpecFbUgdetailService()
		        .getEcSpecFbUgdetailForTjList(user_jf);
		request.setAttribute("jfList_1_4", jfList_1_4);

		// 用户积分排行榜5-8
		EcSpecFbUgdetail user_jf2 = new EcSpecFbUgdetail();
		user_jf2.getRow().setFirst(4);
		user_jf2.getRow().setCount(4);
		List<EcSpecFbUgdetail> jfList_5_8 = super.getFacade().getEcSpecFbUgdetailService()
		        .getEcSpecFbUgdetailForTjList(user_jf2);
		request.setAttribute("jfList_5_8", jfList_5_8);

		// 用户积分排行榜9-12
		EcSpecFbUgdetail user_jf3 = new EcSpecFbUgdetail();
		user_jf3.getRow().setFirst(8);
		user_jf3.getRow().setCount(4);
		List<EcSpecFbUgdetail> jfList_9_12 = super.getFacade().getEcSpecFbUgdetailService()
		        .getEcSpecFbUgdetailForTjList(user_jf3);
		request.setAttribute("jfList_9_12", jfList_9_12);

		// 用户积分排行榜13-16
		EcSpecFbUgdetail user_jf4 = new EcSpecFbUgdetail();
		user_jf4.getRow().setFirst(12);
		user_jf4.getRow().setCount(4);
		List<EcSpecFbUgdetail> jfList_13_16 = super.getFacade().getEcSpecFbUgdetailService()
		        .getEcSpecFbUgdetailForTjList(user_jf4);
		request.setAttribute("jfList_13_16", jfList_13_16);

		// 用户积分排行榜17-20
		EcSpecFbUgdetail user_jf5 = new EcSpecFbUgdetail();
		user_jf5.getRow().setFirst(16);
		user_jf5.getRow().setCount(4);
		List<EcSpecFbUgdetail> jfList_17_20 = super.getFacade().getEcSpecFbUgdetailService()
		        .getEcSpecFbUgdetailForTjList(user_jf5);
		request.setAttribute("jfList_17_20", jfList_17_20);

		// 个人竞猜记录1-4
		List<EcSpecFbUgdetail> grList1 = getEcSpecFbUgdetailList(ecUser.getId(), 0, 4);
		request.setAttribute("grList1", grList1);
		// 个人竞猜记录5-8
		List<EcSpecFbUgdetail> grList2 = getEcSpecFbUgdetailList(ecUser.getId(), 4, 4);
		request.setAttribute("grList2", grList2);
		// 个人竞猜记录9-12
		List<EcSpecFbUgdetail> grList3 = getEcSpecFbUgdetailList(ecUser.getId(), 8, 4);
		request.setAttribute("grList3", grList3);
		// 个人竞猜记录13-16
		List<EcSpecFbUgdetail> grList4 = getEcSpecFbUgdetailList(ecUser.getId(), 12, 4);
		request.setAttribute("grList4", grList4);
		// 个人竞猜记录14-20
		List<EcSpecFbUgdetail> grList5 = getEcSpecFbUgdetailList(ecUser.getId(), 16, 4);
		request.setAttribute("grList5", grList5);
		// 个人竞猜记录21-24
		List<EcSpecFbUgdetail> grList6 = getEcSpecFbUgdetailList(ecUser.getId(), 20, 4);
		request.setAttribute("grList6", grList6);
		// 个人竞猜记录25-28
		List<EcSpecFbUgdetail> grList7 = getEcSpecFbUgdetailList(ecUser.getId(), 24, 4);
		request.setAttribute("grList7", grList7);
		// 个人竞猜记录29-32
		List<EcSpecFbUgdetail> grList8 = getEcSpecFbUgdetailList(ecUser.getId(), 28, 4);
		request.setAttribute("grList8", grList8);
		// 个人竞猜记录29-32
		List<EcSpecFbUgdetail> grList9 = getEcSpecFbUgdetailList(ecUser.getId(), 32, 4);
		request.setAttribute("grList9", grList9);
		// 个人竞猜记录29-32
		List<EcSpecFbUgdetail> grList10 = getEcSpecFbUgdetailList(ecUser.getId(), 36, 4);
		request.setAttribute("grList10", grList10);
		// 个人竞猜记录29-32
		List<EcSpecFbUgdetail> grList11 = getEcSpecFbUgdetailList(ecUser.getId(), 40, 4);
		request.setAttribute("grList11", grList11);
		// 个人竞猜记录29-32
		List<EcSpecFbUgdetail> grList12 = getEcSpecFbUgdetailList(ecUser.getId(), 44, 4);
		request.setAttribute("grList12", grList12);
		// 个人竞猜记录29-32
		List<EcSpecFbUgdetail> grList13 = getEcSpecFbUgdetailList(ecUser.getId(), 48, 4);
		request.setAttribute("grList13", grList13);
		// 个人竞猜记录29-32
		List<EcSpecFbUgdetail> grList14 = getEcSpecFbUgdetailList(ecUser.getId(), 52, 4);
		request.setAttribute("grList14", grList14);
		// 个人竞猜记录29-32
		List<EcSpecFbUgdetail> grList15 = getEcSpecFbUgdetailList(ecUser.getId(), 56, 4);
		request.setAttribute("grList15", grList15);
		// 个人竞猜记录29-32
		List<EcSpecFbUgdetail> grList16 = getEcSpecFbUgdetailList(ecUser.getId(), 60, 4);
		request.setAttribute("grList16", grList16);

		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
		entity.setId(831481L);
		entity = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfo(entity);
		request.setAttribute("entity", entity);

		return mapping.findForward("list");
	}

	public ActionForward ajax(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String is_win = (String) dynaBean.get("is_win");
		String id = (String) dynaBean.get("id");
		String a_team_result = (String) dynaBean.get("a_team_result");
		String b_team_result = (String) dynaBean.get("b_team_result");
		logger.info("id--->" + id);
		logger.info("is_win--->" + is_win);
		logger.info("a_team_result--->" + a_team_result);
		logger.info("b_team_result--->" + b_team_result);

		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		if (null == ecUser) {
			String msg = super.getMessage(request, "ec.nologin");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='"
			        + super.getCtxPath(request) + "/member/login.do';}");
			return null;
		}

		Map<String, String> maps = new HashMap<String, String>();

		EcSpecFbCal ec = new EcSpecFbCal();
		ec.setMatch_id(Long.valueOf(id));
		ec = super.getFacade().getEcSpecFbCalService().getEcSpecFbCal(ec);

		// 开赛前一个小时，停止竞猜
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(ec.getPlay_time());// 5:10 4:50
		calendar.add(Calendar.HOUR, -1);
		Date date = new Date();
		if (calendar.getTime().getTime() - date.getTime() < 0) {
			maps.put("status", "2");
			maps.put("msg", "开赛前一个小时，停止下注");
			super.renderJson(response, JSON.toJSONString(maps));
			return null;
		}

		// 只能猜一天以内的比赛

		long play_time = ec.getPlay_time().getTime();
		long now_time = new Date().getTime();
		if ((play_time - now_time) > 86400000) {
			maps.put("status", "3");
			maps.put("msg", "只能猜24小时以内的比赛");
			super.renderJson(response, JSON.toJSONString(maps));
			return null;
		}

		EcSpecFbUgdetail ef = new EcSpecFbUgdetail();
		ef.setGuess_user_id(ecUser.getId());
		ef.setMatch_id(Long.valueOf(id));
		List<EcSpecFbUgdetail> efList = super.getFacade().getEcSpecFbUgdetailService().getEcSpecFbUgdetailList(ef);
		if (null == efList || efList.size() == 0) {
			EcSpecFbUgdetail entity = new EcSpecFbUgdetail();
			if (StringUtils.isNotBlank(a_team_result)) {
				entity.setGuess_a_team_goal(Integer.valueOf(a_team_result));
			}
			if (StringUtils.isNotBlank(is_win)) {
				entity.setGuess_win(Integer.valueOf(is_win));
			}
			if (StringUtils.isNotBlank(b_team_result)) {
				entity.setGuess_b_team_goal(Integer.valueOf(b_team_result));
			}
			entity.setGuess_date(new Date());
			entity.setGuess_user_id(ecUser.getId());
			entity.setMatch_id(Long.valueOf(id));

			super.getFacade().getEcSpecFbUgdetailService().createEcSpecFbUgdetail(entity);

			maps.put("status", "1");
			maps.put("msg", "竞猜成功");
			super.renderJson(response, JSON.toJSONString(maps));
			return null;
		} else if (null != efList && efList.size() == 1) {
			EcSpecFbUgdetail entity = new EcSpecFbUgdetail();
			ef = efList.get(0);
			// 查询之前的记录 有没有猜输赢
			if (null != ef.getGuess_win()) {
				if (StringUtils.isNotBlank(is_win)) {
					maps.put("status", "4");
					maps.put("msg", "对不起，你已经猜过输赢了");
					super.renderJson(response, JSON.toJSONString(maps));
					return null;
				}
			} else {
				if (StringUtils.isNotBlank(is_win)) {
					entity.setGuess_win(Integer.valueOf(is_win));
				}
			}
			// 查询之前的记录 有没有猜比分
			if (null != ef.getGuess_a_team_goal() && null != ef.getGuess_b_team_goal()) {
				if (StringUtils.isNotBlank(a_team_result) && StringUtils.isNotBlank(b_team_result)) {
					maps.put("status", "5");
					maps.put("msg", "对不起，你已经猜过比分了");
					super.renderJson(response, JSON.toJSONString(maps));
					return null;
				}
			} else {
				if (StringUtils.isNotBlank(a_team_result) && StringUtils.isNotBlank(b_team_result)) {
					entity.setGuess_a_team_goal(Integer.valueOf(a_team_result));
					entity.setGuess_b_team_goal(Integer.valueOf(b_team_result));
				}
			}

			entity.setId(ef.getId());
			super.getFacade().getEcSpecFbUgdetailService().modifyEcSpecFbUgdetail(entity);

			maps.put("status", "1");
			maps.put("msg", "竞猜成功");
			super.renderJson(response, JSON.toJSONString(maps));
			return null;
		} else {
			maps.put("status", "5");
			maps.put("msg", "对不起，你已经猜过竞猜和输赢了");
			super.renderJson(response, JSON.toJSONString(maps));
			return null;
		}

	}

	public String format1(String ss) {
		return Integer.valueOf(ss).toString();
	}

	public List<EcSpecFbUgdetail> getEcSpecFbUgdetailList(Long id, Integer first, Integer count) {
		EcSpecFbUgdetail user_jc1 = new EcSpecFbUgdetail();
		user_jc1.getMap().put("user_id", id);
		user_jc1.getRow().setFirst(first);
		user_jc1.getRow().setCount(count);
		List<EcSpecFbUgdetail> grList1 = super.getFacade().getEcSpecFbUgdetailService().getEcSpecFbUgdetailForJlList(
		        user_jc1);
		if (null != grList1 && grList1.size() > 0) {
			for (EcSpecFbUgdetail pp : grList1) {
				EcSpecFbCal efc = new EcSpecFbCal();
				efc.setMatch_id(pp.getMatch_id());
				efc = super.getFacade().getEcSpecFbCalService().getEcSpecFbCal(efc);

				EcSpecFbTeamList es = new EcSpecFbTeamList();
				es.setId(efc.getA_team_id());
				es = super.getFacade().getEcSpecFbTeamListService().getEcSpecFbTeamList(es);

				EcSpecFbTeamList es1 = new EcSpecFbTeamList();
				es1.setId(efc.getB_team_id());
				es1 = super.getFacade().getEcSpecFbTeamListService().getEcSpecFbTeamList(es1);

				String team_a = es.getTeam_name();
				String team_b = es1.getTeam_name();
				pp.getMap().put("team_a", team_a);
				pp.getMap().put("team_b", team_b);

				int win_jf = pp.getGuess_win_gift() == null ? 0 : pp.getGuess_win_gift();
				int goal_jf = pp.getGuess_goal_gift() == null ? 0 : pp.getGuess_goal_gift();
				pp.getMap().put("total_jf", win_jf + goal_jf);

			}
		}

		return grList1;
	}

}