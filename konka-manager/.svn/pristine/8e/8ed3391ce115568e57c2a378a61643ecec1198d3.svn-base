package com.ebiz.mmt.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.SsoOaUserGroup;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.webservice.sso.Ldap;
import com.ebiz.ssi.web.struts.bean.Pager;

public class SsoOaUserGroupAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String add_date_start = (String) dynaBean.get("add_date_start");
		String add_date_end = (String) dynaBean.get("add_date_end");
		String update_date_start = (String) dynaBean.get("update_date_start");
		String update_date_end = (String) dynaBean.get("update_date_end");
		String group_name_like = (String) dynaBean.get("group_name_like");
		String display_name_like = (String) dynaBean.get("display_name_like");
		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		SsoOaUserGroup entity = new SsoOaUserGroup(); 
		super.copyProperties(entity, form);
		if (StringUtils.isNotBlank(add_date_start)) {
			entity.getMap().put("add_date_start", add_date_start );
		}
		if (StringUtils.isNotBlank(add_date_end)) {
			entity.getMap().put("add_date_end", add_date_end);
		}
		if (StringUtils.isNotBlank(update_date_start)) {
			entity.getMap().put("update_date_start", update_date_start );
		}
		if (StringUtils.isNotBlank(update_date_end)) {
			entity.getMap().put("update_date_end", update_date_end);
		}
		if (StringUtils.isNotBlank(group_name_like)) {
			entity.getMap().put("group_name_like", group_name_like );
		}
		if (StringUtils.isNotBlank(display_name_like)) {
			entity.getMap().put("display_name_like", display_name_like );
		}
		Long recordCount = getFacade().getSsoOaUserGroupService().getSsoOaUserGroupCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<SsoOaUserGroup> entityList = getFacade().getSsoOaUserGroupService().getSsoOaUserGroupPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (isCancelled(request)) {
			return list(mapping, form, request, response);
		}
		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}
		resetToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");

		SsoOaUserGroup entity = new SsoOaUserGroup();
		super.copyProperties(entity, form);

		if (StringUtils.isBlank(id)) {

			getFacade().getSsoOaUserGroupService().createSsoOaUserGroup(entity);
			saveMessage(request, "entity.inserted");
		} else {
			entity.setUpdate_date(new Date());
			getFacade().getSsoOaUserGroupService().modifySsoOaUserGroup(entity);
			saveMessage(request, "entity.updated");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			SsoOaUserGroup entity = new SsoOaUserGroup();
			entity.setId(Long.valueOf(id));
			getFacade().getSsoOaUserGroupService().removeSsoOaUserGroup(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			SsoOaUserGroup entity = new SsoOaUserGroup();
			entity.getMap().put("pks", pks);
			getFacade().getSsoOaUserGroupService().removeSsoOaUserGroup(entity);

			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		SsoOaUserGroup entity = new SsoOaUserGroup();
		entity.setId(new Long(id));
		entity = getFacade().getSsoOaUserGroupService().getSsoOaUserGroup(entity);

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);
		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		SsoOaUserGroup entity = new SsoOaUserGroup();
		entity.setId(new Long(id));
		entity = getFacade().getSsoOaUserGroupService().getSsoOaUserGroup(entity);

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		super.copyProperties(form, entity);
		return mapping.findForward("view");
	} 
	
	/**
	 * 更新LADP 域用户 速度比较慢
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SsoOaUserGroup entity = new SsoOaUserGroup(); 
		String msg="";
		String error="";
		String result="1";

		List<String> list =new ArrayList <String>();
		try{
			Ldap inst = new Ldap();
			inst.initial_Ldap();
			String where = "(objectClass=organizationalPerson)";
			//String where = "(objectClass=organizationalUnit)";
			inst.search_distinguishedName(where,list);
			inst.close_Ldap();
		}catch(Exception ex){
			error="LADP连接错误 ";
			result="0";
			ex.printStackTrace();
		}
		List<SsoOaUserGroup> entityList =new ArrayList<SsoOaUserGroup>();
		if(list!=null&&list.size()>0){
			for(String str:list){
				//displayName:叶 利娟1,sAMAccountName:yelijuan1,distinguishedName:CN=叶 利娟1,OU=重庆分公司,OU=康佳集团多媒体分公司,DC=ad,DC=konka,DC=com,
				if(str!=null){
					try{
						entity = new SsoOaUserGroup();
						String[] array=str.split(",");
						String displayName="";
						String sAMAccountName="";
						String distinguishedName=str;
						String cn="";
						String[] ou = new String [4];
						if(array.length>0){
							int z=3;
							for(int i=0;i<array.length;i++){
								if(array[i].indexOf("displayName")!=-1){
									displayName=array[i].split(":")[1];
								}else if(array[i].indexOf("sAMAccountName")!=-1){
									sAMAccountName=array[i].split(":")[1];
								}else if(array[i].indexOf("CN=")!=-1){
									cn=array[i].split("CN=")[1];
								}else if(array[i].indexOf("OU=")!=-1){
									if(z>-1){
										ou[z]=array[i].split("OU=")[1];
										z--; 
									}
								}
							}
						}
						entity.setDisplay_name(displayName);
						entity.setName(cn);
						entity.setSamaccount_name(sAMAccountName);
						entity.setDistinguishedname(distinguishedName);
						int z=0;
						for(int y=0;y<ou.length;y++){
							if(ou[y]!=null){
								z++;
							}
							if(z==1){
								entity.setGroup1(ou[y]);
							}else if(z==2){
								entity.setGroup2(ou[y]);
							}else if(z==3){
								entity.setGroup3(ou[y]);
							}else if(z==4){
								entity.setGroup4(ou[y]);
							}
						}
						
						entityList.add(entity);
						
					}catch(Exception ex){
						ex.printStackTrace();
						error="解析数据错误"+str;
						logger.info("\n"+str);
					}
				}
			}
		}else{
			error+="未抓取到信息 ";
			result="0";
		}
		int r=0;
		if(entityList!=null&&entityList.size()>0){
			entity = new SsoOaUserGroup(); 
			Long count = getFacade().getSsoOaUserGroupService().getSsoOaUserGroupCount(entity);
			if(count.longValue()>0){
				getFacade().getSsoOaUserGroupService().removeSsoOaUserGroup(entity);
			}
			for(SsoOaUserGroup o: entityList){
				getFacade().getSsoOaUserGroupService().createSsoOaUserGroup(o);
				r++;
			}
		}
		msg="成功获取："+r+"条信息";
		Map<String,String> map =new HashMap<String,String>();
		map.put("msg", msg);
		map.put("result", result);
		map.put("error", error);
		super.renderJson(response, JSON.toJSONString(map));	 	
		return null;
	} 
}
