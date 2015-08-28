package com.ebiz.mmt.web.struts.webservice;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.validator.GenericValidator;
import org.apache.log4j.chainsaw.Main;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
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
		Map<String, Object> allmap = new HashMap<String, Object>();
		DynaBean dynaBean = (DynaBean) form;
		 String id= (String) dynaBean.get("id");;
		
		 String title= (String) dynaBean.get("title");
		
		 String message_type= (String) dynaBean.get("message_type");
		
		 String link_id= (String) dynaBean.get("link_id");;
		
		 String link_table= (String) dynaBean.get("link_tab");
	
		 String c_user_id= (String) dynaBean.get("c_user_id");
		
		 String bc_user_id= (String) dynaBean.get("bc_user_id");
		
		 String begin_date = (String) dynaBean.get("_begin_date");
		 String end_date = (String) dynaBean.get("_end_date");
		
		 String remark= (String) dynaBean.get("remark");
		
		 String state= (String) dynaBean.get("state");
		
		 String is_send= (String) dynaBean.get("is_send");
		 
		KonkaOrderMessageProcess entity=new KonkaOrderMessageProcess();
		
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}
		if (StringUtils.isNotBlank(title)) {
			entity.setTitle(title);
		}
		if (StringUtils.isNotBlank(message_type)) {
			entity.setMessage_type(Integer.valueOf(message_type));
		}
		if (StringUtils.isNotBlank(link_id)) {
			entity.setLink_id(Long.valueOf(link_id));
		}
		if (StringUtils.isNotBlank(link_table)) {
			entity.setLink_table(link_table);
		}
		if (StringUtils.isNotBlank(c_user_id)) {
			entity.setC_user_id(Long.valueOf(c_user_id));
		}
		if (StringUtils.isNotBlank(bc_user_id)) {
			entity.setBc_user_id(Long.valueOf(bc_user_id));
		}else{
			return null;
		}
		if (StringUtils.isNotBlank(remark)) {
			entity.setRemark(remark);
		}
		if (StringUtils.isNotBlank(state)) {
			entity.setState(Integer.valueOf(state));
		}
		if (StringUtils.isNotBlank(is_send)) {
			entity.setIs_send(Integer.valueOf(is_send));
		}
		if (StringUtils.isNotBlank(begin_date)) {
			begin_date = begin_date.split(" ")[0];
			entity.getMap().put("time_start", begin_date+" 00:00:00");
		}
		if (StringUtils.isNotBlank(end_date)) {
			end_date = end_date.split(" ")[0];
			entity.getMap().put("time_end", end_date+" 23:59:59");
		}
		List<KonkaOrderMessageProcess> entityList=(List<KonkaOrderMessageProcess>)super.getFacade().getKonkaOrderMessageProcessService().getKonkaOrderMessageProcessList(entity);
		
		allmap.put("entityList", entityList);
		
		JSONArray jsonArray = JSONArray.fromObject(allmap);

		int start = jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString().substring(start + 1, end + 1));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 返回一个页面，就是手机端点击查看那个消息后的页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id= (String) dynaBean.get("id");;
		
		String title= (String) dynaBean.get("title");
		
		String message_type= (String) dynaBean.get("message_type");
		
		String link_id= (String) dynaBean.get("link_id");;
		
		String link_table= (String) dynaBean.get("link_tab");
		
		String c_user_id= (String) dynaBean.get("c_user_id");
		
		String bc_user_id= (String) dynaBean.get("bc_user_id");
		
		String begin_date = (String) dynaBean.get("_begin_date");
		String end_date = (String) dynaBean.get("_end_date");
		
		String remark= (String) dynaBean.get("remark");
		
		String state= (String) dynaBean.get("state");
		
		String is_send= (String) dynaBean.get("is_send");
		
		KonkaOrderMessageProcess entity=new KonkaOrderMessageProcess();
		
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}
		if (StringUtils.isNotBlank(title)) {
			entity.setTitle(title);
		}
		if (StringUtils.isNotBlank(message_type)) {
			entity.setMessage_type(Integer.valueOf(message_type));
		}
		if (StringUtils.isNotBlank(link_id)) {
			entity.setLink_id(Long.valueOf(link_id));
		}
		if (StringUtils.isNotBlank(link_table)) {
			entity.setLink_table(link_table);
		}
		if (StringUtils.isNotBlank(c_user_id)) {
			entity.setC_user_id(Long.valueOf(c_user_id));
		}
		if (StringUtils.isNotBlank(bc_user_id)) {
			entity.setBc_user_id(Long.valueOf(bc_user_id));
		}
		if (StringUtils.isNotBlank(remark)) {
			entity.setRemark(remark);
		}
		if (StringUtils.isNotBlank(state)) {
			entity.setState(Integer.valueOf(state));
		}
		if (StringUtils.isNotBlank(is_send)) {
			entity.setIs_send(Integer.valueOf(is_send));
		}
		if (StringUtils.isNotBlank(begin_date)) {
		    begin_date=begin_date.split(" ")[0];
			entity.getMap().put("time_start", begin_date+" 00:00:00");
		}
		if (StringUtils.isNotBlank(end_date)) {
			end_date=end_date.split(" ")[0];
			entity.getMap().put("time_end", end_date+" 23:59:59");
		}
		List<KonkaOrderMessageProcess> entityList=(List<KonkaOrderMessageProcess>)super.getFacade().getKonkaOrderMessageProcessService().getKonkaOrderMessageProcessList(entity);
		
		request.setAttribute("entityList",entityList);
		return new ActionForward();
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
		String cust_id = KonkaOrderInfo.getCust_id().toString();

		List<PeProdUser> userList = getUserList(dept_id, role_id, cust_id);
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
				konKaOrderMessageProcess.setTitle("超过15天未办理的订单催办");// 标题
				konKaOrderMessageProcess.setIs_send(0);// 是否推送

				super.getFacade().getKonkaOrderMessageProcessService().createKonkaOrderMessageProcess(konKaOrderMessageProcess);
			}
		}

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
	
	//拿list
	public ActionForward getKonkaOrderMessageProcessList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");
		String link_table = (String) dynaBean.get("link_table");
		List<KonkaOrderMessageProcess> entityList=this.getKonkaOrderMessageProcessList(user_id,link_table);
		request.setAttribute("entityList", entityList);
		List<HashMap> list =new ArrayList<HashMap>();
		HashMap map= new HashMap();
		if(null!=entityList){
		for(KonkaOrderMessageProcess kkmp:entityList){	
		map= new HashMap();
    	map.put("id",kkmp.getId()==null?"":kkmp.getId());
    	map.put("title", kkmp.getTitle()==null?"":kkmp.getTitle());
    	map.put("message_type", kkmp.getMessage_type()==null?0:kkmp.getMessage_type());
    	map.put("link_id", kkmp.getLink_id()==null?"":kkmp.getLink_id());
    	map.put("link_table", kkmp.getLink_table()==null?"konka_order_info":kkmp.getLink_table());
    	map.put("c_user_id", kkmp.getC_user_id()==null?"":kkmp.getC_user_id());
    	map.put("bc_user_id", kkmp.getBc_user_id()==null?"":kkmp.getBc_user_id());
    	map.put("add_date", kkmp.getAdd_date()==null?new Date():kkmp.getAdd_date());
    	map.put("remark", kkmp.getRemark()==null?"":kkmp.getRemark());
    	map.put("state", kkmp.getState()==null?0:kkmp.getState());
    	map.put("is_send", kkmp.getIs_send()==null?0:kkmp.getIs_send());
    	list.add(map);
		}
		}
		logger.info(entityList.toString());
		super.renderJson(response,JSON.toJSONString(list));
		return null;
	}
	
	//实现 拿 list的代码
	public List<KonkaOrderMessageProcess> getKonkaOrderMessageProcessList(String user_id,String link_table) {
		KonkaOrderMessageProcess konkaOrderMessageProcess =new KonkaOrderMessageProcess();
		if(StringUtils.isNotBlank(user_id)){
			konkaOrderMessageProcess.setBc_user_id(Long.valueOf(user_id));
		}
		if(StringUtils.isNotBlank(link_table)){
			konkaOrderMessageProcess.setLink_table(link_table);
		}else{
			konkaOrderMessageProcess.setLink_table("konka_order_info");	
		}
		List<KonkaOrderMessageProcess> komplist =
				super.getFacade().getKonkaOrderMessageProcessService()
				.getKonkaOrderMessageProcessList(konkaOrderMessageProcess);
		return komplist;
	}
}
