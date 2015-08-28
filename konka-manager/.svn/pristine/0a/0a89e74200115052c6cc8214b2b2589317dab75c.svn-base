package com.ebiz.mmt.web.struts.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcVoteMain;
import com.ebiz.mmt.domain.EcVoteOption;
import com.ebiz.mmt.domain.EcVoteRecord;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Tudp
 * @version 2014-10-30
 */
public class EcVoteMainAction extends BaseMemberAction {
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser"); 
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser"); 
		EcVoteMain entity=new EcVoteMain(); 	  
		entity.setOwn_sys(ecUser.getUser_type());
		entity.setIs_pub(new Integer(1));
		entity.setIs_del(new Integer(0));
		Pager pager = (Pager) dynaBean.get("pager"); 
		Long recordCount=super.getFacade().getEcVoteMainService().getEcVoteMainCount(entity);
		
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
		List<EcVoteMain> entityList=super.getFacade().getEcVoteMainService().getEcVoteMainPaginatedList(entity);
		if(entityList.size()>0){
			entity =entityList.get(0);
			dynaBean.set("vote_id",entity.getId().toString());  
			return this.view(mapping, form, request, response);
		}
		return null;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String vote_id = (String) dynaBean.get("vote_id"); 
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		EcVoteMain entity = new EcVoteMain();
		entity.setId(Long.valueOf(vote_id));
		entity.setOwn_sys(ecUser.getUser_type());
		entity.setIs_pub(new Integer(1));
		entity.setIs_del(new Integer(0));
		entity = super.getFacade().getEcVoteMainService().getEcVoteMain(entity);
		
		if(entity!=null){
			if(entity.getMain_pic()!=null){
				String[] picArray =entity.getMain_pic().split(",");
				entity.getMap().put("picArray", picArray);
			}
			request.setAttribute("entity", entity); 
			
			//选项
			EcVoteOption option = new EcVoteOption();
			option.setVote_id(entity.getId()); 
			option.setOwn_sys(ecUser.getUser_type());
			option.setIs_pub(new Integer(1));
			option.setIs_del(new Integer(0)); 
			option.getMap().put("order_by_record", "1");
			request.setAttribute("voteOptionList", super.getFacade().getEcVoteOptionService().getEcVoteOptionList(option));
			
			option = new EcVoteOption();
			option.setVote_id(entity.getId()); 
			option.setOwn_sys(ecUser.getUser_type());
			option.setIs_pub(new Integer(1));
			option.setIs_del(new Integer(0));  
			request.setAttribute("voteOptionListOrderForOrderValue", super.getFacade().getEcVoteOptionService().getEcVoteOptionList(option));
			
		 	//更新浏览次数
			super.getFacade().getEcVoteMainService().modifyEcVoteMainViewCounts(entity);		
			
			EcVoteRecord record = new EcVoteRecord();
			record.setVote_id(entity.getId());
			record.setIs_del(new Integer(0));
			request.setAttribute("all_count", super.getFacade().getEcVoteRecordService().getEcVoteRecordCount(record));
		}else{
			return null;
		}
		return mapping.findForward("view");
	}
 
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String msg="";
		String result="0";
		DynaBean dynaBean = (DynaBean) form;
		String vote_id = (String) dynaBean.get("vote_id"); 
		String vote_option_id = (String) dynaBean.get("vote_option_id"); 
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		EcVoteMain entity = new EcVoteMain();
		entity.setId(Long.valueOf(vote_id));
		entity.setOwn_sys(ecUser.getUser_type());
		entity.setIs_pub(new Integer(1));
		entity.setIs_del(new Integer(0));
		entity = super.getFacade().getEcVoteMainService().getEcVoteMain(entity);
		
		EcVoteMain vote = new EcVoteMain();
		vote.setId(Long.valueOf(vote_id));
		vote.setOwn_sys(ecUser.getUser_type());
		vote.setIs_pub(new Integer(1));
		vote.setIs_del(new Integer(0));
		vote.getMap().put("is_start", "1");
		Long c = super.getFacade().getEcVoteMainService().getEcVoteMainCount(vote);
		
		if(c.intValue()==0){
			msg="投票活动还未开始";
		}		
		vote = new EcVoteMain();
		vote.setId(Long.valueOf(vote_id));
		vote.setOwn_sys(ecUser.getUser_type());
		vote.setIs_pub(new Integer(1));
		vote.setIs_del(new Integer(0));
		vote.getMap().put("is_not_end", "1");
		c = super.getFacade().getEcVoteMainService().getEcVoteMainCount(vote);
		
		if(c.intValue()==0){
			msg="投票活动还已经结束";
		}
		if(!"".equals(msg)){
			Map map =new HashMap();
			map.put("msg", msg);
			map.put("result", result);
			super.renderJson(response,  JSON.toJSONString(map));
			return null;
		}
		
		if(entity!=null){
			if(entity.getVote_state().intValue()!=1){
				if(entity.getVote_state().intValue()==0){
					msg="投票活动还未开始";
				}else{
					msg="投票活动已经结束";
				}
			}else{
				try{
					EcVoteOption option = new EcVoteOption();
					option.setVote_id(entity.getId());
					option.setId(Long.valueOf(vote_option_id));
					option.setOwn_sys(ecUser.getUser_type());
					option.setIs_pub(new Integer(1));
					option.setIs_del(new Integer(0));
					option = super.getFacade().getEcVoteOptionService().getEcVoteOption(option);
					if(option!=null){
						EcVoteRecord record = new EcVoteRecord();
						record.setUser_id(ecUser.getId());
						record.setVote_id(entity.getId());
						record.getMap().put("is_today", "1");//获取今天用户已经投票数
						Long count =super.getFacade().getEcVoteRecordService().getEcVoteRecordCount(record);
						if(count.intValue()<entity.getVote_all_num().intValue()){
							record = new EcVoteRecord();
							record.setUser_id(ecUser.getId());
							record.setOwn_sys(ecUser.getUser_type());
							record.setUser_name(ecUser.getUser_name());
							record.setVote_id(entity.getId());
							record.setVote_option_id(option.getId());
							record.setIp(super.getIpAddr(request));
							Long id =super.getFacade().getEcVoteRecordService().createEcVoteRecordForVote(record);
							if(id!=null){
								msg="投票成功";
								result="1";	
							}else{
								msg="您今天的"+entity.getVote_all_num()+"票已投完";	
							}
						}else{
							msg="您今天的"+entity.getVote_all_num()+"票已投完";
						}
					}else{
						msg="未找到投票选项";
					}	
				}catch(Exception ex){
					msg="发生异常";
				}
			}
		}else{
			msg="未找到投票主题";
		}

		Map map =new HashMap();
		map.put("msg", msg);
		map.put("result", result);
		super.renderJson(response,  JSON.toJSONString(map));
		return null;
	}
	
	
	public ActionForward viewOption(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String vote_option_id = (String) dynaBean.get("vote_option_id"); 
		if(vote_option_id!=null&&!"".equals(vote_option_id)){
			EcVoteOption option = new EcVoteOption();
			option.setId(new Long(vote_option_id)); 
			option.setIs_pub(new Integer(1));
			option.setIs_del(new Integer(0));
			request.setAttribute("entity", super.getFacade().getEcVoteOptionService().getEcVoteOption(option));
			//更新浏览次数
			super.getFacade().getEcVoteOptionService().modifyEcVoteOptionViewCounts(option);		
		}
		return new ActionForward("/../member/EcVoteMain/viewOption.jsp");
	}
}