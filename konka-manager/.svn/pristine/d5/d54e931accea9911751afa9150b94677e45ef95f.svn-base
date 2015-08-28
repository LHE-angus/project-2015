package com.ebiz.mmt.web.struts.manager.zmd;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ebiz.mmt.domain.KonkaXxActMessage;
import com.ebiz.mmt.domain.KonkaXxMessage;
import com.ebiz.mmt.domain.KonkaXxNoticeContent;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaXxMessageAction extends BaseZmdAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.getMessages(mapping, form, request, response);
	}
	
	public ActionForward getMessages(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		
		String user_id = (String) dynaBean.get("user_id");
		String num = "0";
	
		JSONObject result = new JSONObject();
		if (StringUtils.isNotBlank(user_id)) {
			//系统未提醒消息
			KonkaXxActMessage konkaXxActMessage = new KonkaXxActMessage();
			konkaXxActMessage.setRec_user_id(Long.valueOf(user_id));
			konkaXxActMessage.setState(0L); //0,系统未提醒；1,系统已提醒
			Long count = getFacade().getKonkaXxActMessageService().getKonkaXxActMessageCount(konkaXxActMessage);
			if (count > 0) {
				JSONArray arr = new JSONArray();
				List<String> pks = new ArrayList<String>();
				List<KonkaXxActMessage> actMessageList = getFacade().getKonkaXxActMessageService().getKonkaXxActMessageList(konkaXxActMessage);
				if (null != actMessageList && actMessageList.size() > 0) {
					for (int i = 0; i < actMessageList.size(); i++) {
						pks.add(actMessageList.get(i).getId().toString());
						
						PeProdUser user = new PeProdUser();
						user.setId(actMessageList.get(i).getSender_id());
						user = getFacade().getPeProdUserService().getPeProdUserResult(user);
						
						JSONObject obj = new JSONObject();
						obj.put("id", actMessageList.get(i).getId());
						obj.put("msg_type", actMessageList.get(i).getMsg_type());
						obj.put("msg_title", actMessageList.get(i).getMsg_title());
						if (null != user) {
							obj.put("sender_user", user.getReal_name());
						}
						arr.put(obj);
					}
				}
				result.put("list", arr);
				
				//修改活动消息表中已提醒的消息状态为已提醒
				String[] arrs = pks.toArray(new String[pks.size()]);
				KonkaXxActMessage am = new KonkaXxActMessage();
				am.getMap().put("pks", arrs);
				am.setState(1L);
				super.getFacade().getKonkaXxActMessageService().modifyKonkaXxActMessage(am);
				
				//删除系统已提醒消息
//				String[] arrs = pks.toArray(new String[pks.size()]);
//				KonkaXxActMessage kxam = new KonkaXxActMessage();
//				kxam.getMap().put("pks", arrs);
//				kxam.setRec_user_id(Long.valueOf(user_id));
//				super.getFacade().getKonkaXxActMessageService().removeKonkaXxActMessage(kxam);
			}
			num = count.toString();
			
			//未查看消息
			KonkaXxActMessage message = new KonkaXxActMessage();
			message.setRec_user_id(Long.valueOf(user_id));
			Long notReadCount = getFacade().getKonkaXxActMessageService().getKonkaXxActMessageCount(message);
			result.put("notReadCount", notReadCount);
			
			if (notReadCount > 0L) {
				message.getRow().setCount(8);
				List<KonkaXxActMessage> notReadList = getFacade().getKonkaXxActMessageService().getKonkaXxActMessageList(message);
				JSONArray notReaArray = new JSONArray();
				if (null != notReadList && notReadList.size() > 0) {
					for (KonkaXxActMessage kxam : notReadList) {
						JSONObject obj = new JSONObject();
						obj.put("id", kxam.getId());
						obj.put("msg_title", kxam.getMsg_title());
						obj.put("msg_type", kxam.getMsg_type());
						notReaArray.put(obj);
					}
					result.put("notReadList", notReaArray);
				}
			}
		}
		result.put("num", num);
		
		renderJson(response, result.toString());
		return null;
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String user_id = (String) dynaBean.get("user_id");
		
		if (StringUtils.isBlank(user_id)) {
			renderJavaScript(response, "参数丢失！");
			return null;
		}
		
		KonkaXxMessage entity = new KonkaXxMessage();
		entity.setRec_user_id(Long.valueOf(user_id));
		
		Long recordCount = getFacade().getKonkaXxMessageService().getKonkaXxMessageCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());
		List<KonkaXxMessage> list = getFacade().getKonkaXxMessageService().getKonkaXxMessagePaginatedList(entity);
		
		request.setAttribute("myMessageList", list);
		
		return mapping.findForward("list");
	}
	
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String out_id = (String) dynaBean.get("out_id");
		
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		
		if (StringUtils.isNotBlank(out_id) && !"undefined".equals(out_id)) {
			KonkaXxMessage message = new KonkaXxMessage();
			message.setOut_id(Long.valueOf(out_id));
			message.setRec_user_id(ui.getId());
			message = getFacade().getKonkaXxMessageService().getKonkaXxMessage(message);
			if (null != message) {
				if (message.getMsg_type() == 1) {
					KonkaXxNoticeContent content = new KonkaXxNoticeContent();
					content.setId(Long.valueOf(message.getMsg_content()));
					content = getFacade().getKonkaXxNoticeContentService().getKonkaXxNoticeContent(content);
					if (null != content) {
						message.getMap().put("notice", content.getNotice_content());
					}
				}
				
				super.copyProperties(form, message);
				
				if (0 == message.getState()) {//未查看
					message.setState(1);
					message.setRead_date(new Date());
					super.getFacade().getKonkaXxMessageService().modifyKonkaXxMessage(message);
					
					KonkaXxActMessage am = new KonkaXxActMessage();
					am.setId(message.getOut_id());
					am.setRec_user_id(message.getRec_user_id());
					super.getFacade().getKonkaXxActMessageService().removeKonkaXxActMessage(am);
				}
			}
			
			return mapping.findForward("view");
		}
		
		if (StringUtils.isBlank(id)) {
//			renderJavaScript(response, "参数丢失");
//			return null;
			return mapping.findForward("view");
		}
		
		KonkaXxMessage entity = new KonkaXxMessage();
		entity.setId(Long.valueOf(id));
		entity = getFacade().getKonkaXxMessageService().getKonkaXxMessage(entity);
		if (null != entity) {
			if (entity.getMsg_type() == 1) {
				KonkaXxNoticeContent noticeContent = new KonkaXxNoticeContent();
				noticeContent.setId(Long.valueOf(entity.getMsg_content()));
				noticeContent = getFacade().getKonkaXxNoticeContentService().getKonkaXxNoticeContent(noticeContent);
				if (null != noticeContent) {
					entity.getMap().put("notice", noticeContent.getNotice_content());
				}
			}
			
			super.copyProperties(form, entity);
			
			if (0 == entity.getState()) {//未查看
				entity.setState(1);
				entity.setRead_date(new Date());
				super.getFacade().getKonkaXxMessageService().modifyKonkaXxMessage(entity);
				
				KonkaXxActMessage am = new KonkaXxActMessage();
				am.setId(entity.getOut_id());
				am.setRec_user_id(entity.getRec_user_id());
				super.getFacade().getKonkaXxActMessageService().removeKonkaXxActMessage(am);
			}
		}
		
		return mapping.findForward("view");
	}
	
	
	public ActionForward show(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String msg_type = (String) dynaBean.get("msg_type");
		
		if (StringUtils.isBlank(id) && StringUtils.isBlank(msg_type)) {
			renderJavaScript(response, "参数丢失");
			return null;
		}
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		
		KonkaXxMessage entity = new KonkaXxMessage();
		entity.setOut_id(Long.valueOf(id));
		entity.setRec_user_id(ui.getId());
		entity = getFacade().getKonkaXxMessageService().getKonkaXxMessage(entity);
		if (null != entity) {
			if ("1".equals(msg_type)) {
				KonkaXxNoticeContent content = new KonkaXxNoticeContent();
				content.setNotice_id(Long.valueOf(id));
				content = getFacade().getKonkaXxNoticeContentService().getKonkaXxNoticeContent(content);
				if (null != content) {
					entity.getMap().put("notice", content.getNotice_content());
				}
			}
			
			super.copyProperties(form, entity);
			
			if (0 == entity.getState()) {//未查看
				entity.setState(1);
				entity.setRead_date(new Date());
				super.getFacade().getKonkaXxMessageService().modifyKonkaXxMessage(entity);
			}
		}
		
		
//		KonkaXxMessage entity = new KonkaXxMessage();
//		entity.setId(Long.valueOf(id));
//		entity = getFacade().getKonkaXxMessageService().getKonkaXxMessage(entity);
//		if (null != entity) {
//			if (entity.getMsg_type() == 1) {
//				KonkaXxNoticeContent noticeContent = new KonkaXxNoticeContent();
//				noticeContent.setId(Long.valueOf(entity.getOut_id()));
//				noticeContent = getFacade().getKonkaXxNoticeContentService().getKonkaXxNoticeContent(noticeContent);
//				if (null != noticeContent) {
//					entity.getMap().put("notice", noticeContent.getNotice_content());
//				}
//			}
//			
//			super.copyProperties(form, entity);
//			
//			if (0 == entity.getState()) {//未查看
//				entity.setState(1);
//				entity.setRead_date(new Date());
//				super.getFacade().getKonkaXxMessageService().modifyKonkaXxMessage(entity);
//			}
//		}
		
		return mapping.findForward("view");
	}
	
	
	
	
}
