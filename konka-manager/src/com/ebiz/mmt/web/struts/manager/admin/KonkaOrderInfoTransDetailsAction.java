package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaOrderInfoTransDetails;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.org.apache.commons.lang3.StringUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaOrderInfoTransDetailsAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.listTZD(mapping, form, request, response);
	}
	public ActionForward listTZD(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String dept_id = (String)dynaBean.get("dept_id");//分 公 司
		String l4_dept_id = (String)dynaBean.get("l4_dept_id");//分 公 司
		String l5_dept_id = (String)dynaBean.get("l5_dept_id");//分 公 司
		String trans_status = (String)dynaBean.get("trans_status");//发货状态
		String trans_date_s = (String)dynaBean.get("trans_date_s");//发货时间
		String trans_date_e = (String)dynaBean.get("trans_date_e");//发货时间
		String r3_vbedl_like = (String)dynaBean.get("r3_vbedl_like");//R3物流单号
		String vbeln_like = (String)dynaBean.get("vbeln_like");//R3订单号
		String trade_index_like = (String)dynaBean.get("trade_index_like");//交易流水号
//		String trans_index_like = (String)dynaBean.get("trans_index_like");//发货单号
		String customer_name_like = (String)dynaBean.get("customer_name_like");//客户名称
		String r3_code_like = (String)dynaBean.get("r3_code_like");//R3客户编码
		String we_like = (String)dynaBean.get("we_like");//送达方
		String send_type = (String)dynaBean.get("send_type");//配送方式
		String user_name_like = (String)dynaBean.get("user_name_like");//收货人姓名
		String user_phone_like = (String)dynaBean.get("user_phone_like");//收货人电话
		String user_addr_like = (String)dynaBean.get("user_addr_like");//收货人地址
		
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		KonkaOrderInfoTransDetails entity=new KonkaOrderInfoTransDetails();
		
		if(StringUtils.isNotBlank("trans_status")){
			entity.getMap().put("trans_status", trans_status);
		}
		if(StringUtils.isNotBlank("trans_date_s")){
			entity.getMap().put("trans_date_s", trans_date_s);
		}
		if(StringUtils.isNotBlank("trans_date_e")){
			entity.getMap().put("trans_date_e", trans_date_e);
		}
		if(StringUtils.isNotBlank("r3_vbedl_like")){
			entity.getMap().put("r3_vbedl_like", r3_vbedl_like);
		}
		if(StringUtils.isNotBlank("vbeln_like")){
			entity.getMap().put("vbeln_like", vbeln_like);
		}
		if(StringUtils.isNotBlank("trade_index_like")){
			entity.getMap().put("trade_index_like", trade_index_like);
		}
//		if(StringUtils.isNotBlank("trans_index_like")){
//			entity.getMap().put("trans_index_like", trans_index_like);
//		}
		if(StringUtils.isNotBlank("customer_name_like")){
			entity.getMap().put("customer_name_like", customer_name_like);
		}
		if(StringUtils.isNotBlank("r3_code_like")){
			entity.getMap().put("r3_code_like", r3_code_like);
		}
		if(StringUtils.isNotBlank("we_like")){
			entity.getMap().put("we_like", we_like);
		}
		if(StringUtils.isNotBlank("send_type")){
			entity.getMap().put("send_type", send_type);
		}
		if(StringUtils.isNotBlank("user_name_like")){
			entity.getMap().put("user_name_like", user_name_like);
		}
		if(StringUtils.isNotBlank("user_phone_like")){
			entity.getMap().put("user_phone_like", user_phone_like);
		}
		if(StringUtils.isNotBlank("user_addr_like")){
			entity.getMap().put("user_addr_like", user_addr_like);
		}
		//分公司
		if (StringUtils.isNotBlank(l5_dept_id)) {
			entity.getMap().put("par_dept_id", l5_dept_id);
		} else if (StringUtils.isNotBlank(l4_dept_id)) {
			entity.getMap().put("par_dept_id", l4_dept_id);
		} else if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("par_dept_id", dept_id);
		}
		
		PeRoleUser roleUser = new PeRoleUser();
		roleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> userList = super.getFacade().getPeRoleUserService().getPeRoleUserList(roleUser);
		boolean role_id_eq_10 = false; // 是否为系统管理员
		for (PeRoleUser peRoleUser : userList) {
			if (peRoleUser.getRole_id() >= 10L && peRoleUser.getRole_id() < 30L) {
				role_id_eq_10 = true;
			}
		}
		if(role_id_eq_10){// 是系统管理员
			
		} else {// 非系统管理员
			// 数据级别判断开始
			Long __dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
			request.setAttribute("max_dlevel", max_dlevel);
			logger.info("Max level : {}", max_dlevel);
			switch (max_dlevel) {
			case 9:
				// 集团及以下部门可见
				__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
				break;
			case 8:
				// 分公司及以下部门可见
				KonkaDept dept_fgs = super.getKonkaDeptForFgs(Long.valueOf(__dept_id)); // 查询部门分公司
				if (null != dept_fgs && null != dept_fgs.getDept_id()) {
					__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				}
				break;
			case 7:
				// 我所在的部门及以下部门可见
				__dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
				break;
			case 0:
				break;
			default:
				// 出错
			}
			// 数据级别判断结束
			entity.getMap().put("session_user_id",userInfo.getId());//获取当前客户所查看的数据部门
			entity.getMap().put("par_or_children_dept_id", __dept_id);

			if (max_dlevel < 7) {
				entity.getMap().put("no_sys_man_user_id", userInfo.getId()); // 表示需要当前用户角色审核的订单
			}
		}
		
		Long recordCount = super.getFacade().getKonkaOrderInfoTransDetailsService().getKonkaOrderInfoTransDetailsPaginatedCountTZD(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());
		List<KonkaOrderInfoTransDetails> entityList=super.getFacade().getKonkaOrderInfoTransDetailsService().getKonkaOrderInfoTransDetailsPaginatedListTZD(entity);
		request.setAttribute("entityList", entityList);
		
		return new ActionForward("/admin/KonkaOrderInfoTransDetails/listTZD.jsp");
	}
	
	
	
}
