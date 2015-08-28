package com.ebiz.mmt.web.struts.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ebiz.mmt.domain.KonkaOrderMessageProcess;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.struts.BaseAction;

public class JStocksSummaryAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.messageView(mapping, form, request, response);
	}

	/**
	 * 显示推送消息详情
	 * IOS端 单个显示
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward messageView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");// 业务员id
		String dept_id = (String) dynaBean.get("dept_id");// 部门id
		String cust_name = (String) dynaBean.get("cust_name");// 客户
		String r3_code = (String) dynaBean.get("r3_code");// 客户编码
		String goods_name = (String) dynaBean.get("goods_name");// 商品新型号
		String total_cur_num = (String) dynaBean.get("total_cur_num");// 当前库存
		String avg_num = (String) dynaBean.get("avg_num");// 最低存量
		String total_num = (String) dynaBean.get("total_num");// 最高存量
		if (StringUtils.isEmpty(user_id)) {
			return null;
		}
		PeProdUser us = new PeProdUser();
		us.setId(Long.valueOf(user_id));
		us = super.getFacade().getPeProdUserService().getPeProdUser(us);
		if (null != us) {
			if (null != us.getUser_name()) {
				dynaBean.set("user_name", us.getUser_name());
			}
			if (null != us.getLink_tel()) {
				dynaBean.set("link_tel", us.getLink_tel());
			}
		}
		return new ActionForward(
				"/../webservice/JStocksSummary/messageView.jsp");
	}
	/**
	 * 显示推送消息详
	 * Android端  列表显示
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward messageView1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("bc_user_id");// 被催办人
		if (null==user_id||StringUtils.isEmpty(user_id)) {
	        return null;
		}
		KonkaOrderMessageProcess entity =new KonkaOrderMessageProcess();
		entity.setBc_user_id(Long.valueOf(user_id));//被催办人
		entity.setMessage_type(4);//库存预警的
		entity.setIs_send(0);//支持推送
		entity.setState(0);//未推送过的
		List<KonkaOrderMessageProcess> entityList= super.getFacade().getKonkaOrderMessageProcessService().getKonkaOrderMessageProcessList(entity);
		List<Long> ids=new ArrayList<Long>();
		for (KonkaOrderMessageProcess konkaOrderMessageProcess : entityList) {
			ids.add(konkaOrderMessageProcess.getId());
			Long ywy_user_id = konkaOrderMessageProcess.getC_user_id();
			PeProdUser user = new PeProdUser();
			if (null!=ywy_user_id) {
				user.setId(ywy_user_id);
				user = super.getFacade().getPeProdUserService().getPeProdUser(user);
				if (null != user) {
					if (null != user.getUser_name()) {
						konkaOrderMessageProcess.getMap().put("ywy_user_name", user.getUser_name());
					}
					if (null != user.getLink_tel()) {
						konkaOrderMessageProcess.getMap().put("ywy_tel", user.getLink_tel());
					}
				}
			}
		}
		request.setAttribute("entityList", entityList);
		//修改推送信息状态
		KonkaOrderMessageProcess update =new KonkaOrderMessageProcess();
		update.getMap().put("pks", ids.toArray());
		update.setState(1);//修改为已推送
		if(null!=ids&&ids.size()>0){
			super.getFacade().getKonkaOrderMessageProcessService().modifyKonkaOrderMessageProcess(update);
		}
		
		return new ActionForward(
				"/../webservice/JStocksSummary/messageView1.jsp");
	}
}