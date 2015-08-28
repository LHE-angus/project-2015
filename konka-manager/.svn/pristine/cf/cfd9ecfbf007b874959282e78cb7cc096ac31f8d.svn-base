package com.ebiz.mmt.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSONObject;
import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.domain.KonkaOrderMessageProcess;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.org.apache.commons.lang3.StringUtils;

public class KonkaOrderMessageProcessAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return null;
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request,Constants.USER_INFO);
		String order_id = (String) dynaBean.get("order_id");
		String link_table = (String) dynaBean.get("link_table");
		if (StringUtils.isBlank(order_id)) {
			return null;
		}
		KonkaOrderInfo KonkaOrderInfo = new KonkaOrderInfo();
		KonkaOrderInfo.setId(Long.valueOf(order_id));
		KonkaOrderInfo = super.getFacade().getKonkaOrderInfoService()
				.getKonkaOrderInfo(KonkaOrderInfo);
		String dept_id = KonkaOrderInfo.getAdd_dept_id().toString();
		String role_id = KonkaOrderInfo.getNext_audit_role_id().toString();
        String cust_name=KonkaOrderInfo.getUser_shop_name();
		
		List<PeProdUser> userList = getUserList(dept_id, role_id, null);
		List<String> uidList=new ArrayList<String>();
		Long addcount=0L;
		if (null != userList) {
			for (PeProdUser ppuser : userList) {
				KonkaOrderMessageProcess konKaOrderMessageProcess = new KonkaOrderMessageProcess();

				konKaOrderMessageProcess.setC_user_id(userInfo.getId());// 催办人用户ID
				konKaOrderMessageProcess.setBc_user_id(ppuser.getId());// 被催办人用户ID
				konKaOrderMessageProcess.setAdd_date(new Date());// 添加时间
				konKaOrderMessageProcess.setLink_id(KonkaOrderInfo.getId());// 连接表id
				if (StringUtils.isNotBlank(link_table)) {
					konKaOrderMessageProcess.setLink_table(link_table);// 连接的表名
				} else {
					konKaOrderMessageProcess.setLink_table("konka_order_info");// 连接的表名
				}
				konKaOrderMessageProcess.setState(0);// 状态
				konKaOrderMessageProcess.setMessage_type(0);// 类型（0订单，1。。。2。。。）
				konKaOrderMessageProcess.setTitle("客户"+cust_name+"待办理的订单催办");// 标题
				konKaOrderMessageProcess.setRemark("催办订单");//备注
				konKaOrderMessageProcess.setIs_send(0);// 是否推送

				addcount+=super.getFacade().getKonkaOrderMessageProcessService().createKonkaOrderMessageProcess(konKaOrderMessageProcess);
				uidList.add(ppuser.getId().toString());
			}
			
			super.getFacade().getIosPushMessageService().SendMessage("催办订单", "客户"+cust_name+"待办理的订单催办", 2, 
					"http://qdgl.konka.com/manager/admin/KonkaOrderSearch.do?method=view&order_id="+order_id, "客户"+cust_name+"待办理的订单催办", uidList);
				
		}
		
		
		JSONObject jonsObject = new JSONObject();
		
		//查出催办多少次
		KonkaOrderMessageProcess konKaOrderMessageProcess = new KonkaOrderMessageProcess();
		konKaOrderMessageProcess.setLink_id(Long.valueOf(order_id));
		if (StringUtils.isNotBlank(link_table)) {
			konKaOrderMessageProcess.setLink_table(link_table);// 连接的表名
		} else {
			konKaOrderMessageProcess.setLink_table("konka_order_info");// 连接的表名
		}
		Long count = super.getFacade().getKonkaOrderMessageProcessService().getKonkaOrderMessageProcessCount(konKaOrderMessageProcess);
		
		jonsObject.put("addcount",addcount);
		jonsObject.put("count",count);
		super.renderJson(response, jonsObject.toString());
		return null;
	}
	
	/*
	 * 获取待审核人list
	 */

	public List<PeProdUser> getUserList(String dept_id, String role_id,
			String cust_id) {
		PeProdUser user = new PeProdUser();
		PeRoleInfo r = new PeRoleInfo();
		r.setRole_id(Long.valueOf(role_id));
		r = super.getFacade().getPeRoleInfoService().getPeRoleInfo(r);
		if (null != r && r.getD_level() != null) {
			switch (r.getD_level()) {
			case 9:
				// 集团及以下部门可见
				dept_id = "0";
				break;
			case 8:
				// 分公司及以下部门可见
				if (GenericValidator.isLong(dept_id)) {
					KonkaDept dept = super.getKonkaDeptForFgs(Long
							.valueOf(dept_id));
					if (null != dept && null != dept.getDept_id()) {
						dept_id = dept.getDept_id().toString();
					}
				}
				break;
			case 7:
				// 我所在的部门及以下部门可见
				break;
			case 0:
				// 无部门查看权限，仅限自己管理的数据
				if (GenericValidator.isLong(cust_id)) {
					BranchAssign ba = new BranchAssign();
					ba.setKonka_r3_id(Long.valueOf(cust_id));
					ba = super.getFacade().getBranchAssignService()
							.getBranchAssign(ba);

					if (null != ba) {
						user.setId(ba.getUser_id());
					}
				}
				break;
			default:
				// 出错
			}
		}

		if (StringUtils.isNotBlank(dept_id)) {
			user.getMap().put("par_or_children_dept_id", dept_id);
		}
		if (StringUtils.isNotBlank(role_id)) {
			user.getMap().put("role_id", role_id);
		}
		user.setIs_del(0);

		List<PeProdUser> userList = getFacade().getPeProdUserService()
				.getPeProdUserListByDeptIdAndRoleId(user);

		return userList;

	}

}
