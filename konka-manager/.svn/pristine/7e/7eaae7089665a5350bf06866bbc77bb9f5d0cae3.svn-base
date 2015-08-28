package com.ebiz.mmt.web.struts.manager.chengduoa;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaoaFilesAuditNode;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hui,Gang
 * @version 2011-12-28 下午2:37:28
 * 
 * 
 * @author zhou
 * @desc 审核流程节点生成过程
 * @since 2015-05-19
 */
public class AuditNodeManagerAction extends BaseMmtoaAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaoaFilesAuditNode entity = new KonkaoaFilesAuditNode();
		entity.setId(Long.valueOf(id));
		entity.setAudit_level(1l);
		KonkaoaFilesAuditNode nodeEntity = getFacade().getKonkaoaFilesAuditNodeService()
				.getKonkaoaFilesAuditNode(entity);
		if (null == nodeEntity) {
			return this.add(mapping, form, request, response);
		} else {
			if (null != nodeEntity.getLink_id()) {
				KonkaoaFilesAuditNode _entity = new KonkaoaFilesAuditNode();
				_entity.setLink_id(nodeEntity.getLink_id());
				List<KonkaoaFilesAuditNode> konkaoaFilesAuditNodeList = super.getFacade()
						.getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeList(_entity);

				if (null != konkaoaFilesAuditNodeList) {
					request.setAttribute("konkaoaFilesAuditNodeList", konkaoaFilesAuditNodeList);
				}
			}
			super.copyProperties(form, nodeEntity);
			return mapping.findForward("input");
		}
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		
		String audit_node_name_like = (String) dynaBean.get("audit_node_name_like");

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		KonkaoaFilesAuditNode entity = new KonkaoaFilesAuditNode();
		super.copyProperties(entity, form);

		if (ui.getDept_id() != 0) {
			KonkaDept konkaDept = new KonkaDept();
			konkaDept = getKonkaDeptForFgs(ui.getDept_id());
			entity.setDept_id(konkaDept.getDept_id());
		}

		if(StringUtils.isNotBlank("audit_node_name_like")){
			entity.getMap().put("audit_node_name_like", audit_node_name_like);
		}
		
		entity.setAudit_type(2);
		entity.setAudit_level(1l);

		Long recordCount = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		entity.getMap().put("is_audit_node_manager", 1);

		List<KonkaoaFilesAuditNode> entityList = getFacade().getKonkaoaFilesAuditNodeService()
				.getKonkaoaFilesAuditNodePaginatedList(entity);

		// 总部
		if (ui.getDept_id() == 0) {
			dynaBean.set("is_admin", "1");
			if (entityList.size() > 0) {
				for (KonkaoaFilesAuditNode temp : entityList) {
					if (temp.getDept_id() != null && !"".equals(String.valueOf(temp.getDept_id()))) {
						KonkaDept konkaDept = new KonkaDept();
						konkaDept.setDept_id(temp.getDept_id());
						konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
						if (konkaDept != null) {
							temp.getMap().put("dept_name", konkaDept.getDept_name());
						}
					}
				}
			}
		}
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (GenericValidator.isLong(id)) {
			KonkaoaFilesAuditNode konkaoaFilesAuditNode = new KonkaoaFilesAuditNode();
			konkaoaFilesAuditNode.setId(new Long(id));
			konkaoaFilesAuditNode = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNode(
					konkaoaFilesAuditNode);

			if (null != konkaoaFilesAuditNode) {
				KonkaoaFilesAuditNode entity = new KonkaoaFilesAuditNode();
				entity.setLink_id(konkaoaFilesAuditNode.getLink_id());

				List<KonkaoaFilesAuditNode> list = getFacade().getKonkaoaFilesAuditNodeService()
						.getKonkaoaFilesAuditNodeList(entity);

				if (null == list) {
					saveMessage(request, "entity.missing");
					return mapping.findForward("list");
				}

				request.setAttribute("filesAuditNodeList", list);

				// 获取流程的名称
				request.setAttribute("audit_node_name", konkaoaFilesAuditNode.getAudit_node_name());
				request.setAttribute("node_type", konkaoaFilesAuditNode.getNode_type());
			}

			return mapping.findForward("view");
		} else {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);

		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (isCancelled(request)) {
			return list(mapping, form, request, response);
		}
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
        String id = (String) dynaBean.get("id");// key id not link_id

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		KonkaoaFilesAuditNode entity = new KonkaoaFilesAuditNode();
		super.copyProperties(entity, form);

        // 审批节点数据
		String[] node_user_names = request.getParameterValues("node_user_name");
		String[] node_user_ids = request.getParameterValues("node_user_id");

		if (ArrayUtils.isEmpty(node_user_ids)) {
			return null;
		}

		KonkaDept konkaDept = new KonkaDept();
		if (ui.getDept_id() == 0L) {
			konkaDept.setDept_id(0L);
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		} else {
			konkaDept = getKonkaDeptForFgs(ui.getDept_id());
		}
		
        // 流程节点数据
		List<KonkaoaFilesAuditNode> konkaoaFilesAuditNodeList = new ArrayList<KonkaoaFilesAuditNode>();
		for (int i = 0; i < node_user_ids.length; i++) {
			KonkaoaFilesAuditNode kfan = new KonkaoaFilesAuditNode();
			kfan.setAudit_user(node_user_names[i]);
			kfan.setAudit_user_id(new Long(node_user_ids[i]));
			kfan.setAudit_level(new Long(i + 1));
			kfan.setNode_type(entity.getNode_type());
            kfan.setDept_id(konkaDept.getDept_id());// 当前新增流程节点那个人所在部门id
			konkaoaFilesAuditNodeList.add(kfan);
		}
        entity.setAudit_type(2);// 固定为2
		entity.setKonkaoaFilesAuditNodeList(konkaoaFilesAuditNodeList);

        if (StringUtils.isBlank(id)) {
            // 生成流程节点数据
			getFacade().getKonkaoaFilesAuditNodeService().createKonkaoaFilesAuditNode(entity);
			saveMessage(request, "entity.inserted");
		} else {
            // 流程节点数据更新
            entity.setLink_id(entity.getLink_id());
			getFacade().getKonkaoaFilesAuditNodeService().modifyKonkaoaFilesAuditNode(entity);
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

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String link_id = (String) dynaBean.get("link_id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(link_id) && GenericValidator.isLong(link_id)) {
			KonkaoaFilesAuditNode entity = new KonkaoaFilesAuditNode();
			entity.setLink_id(Long.parseLong(link_id));
			getFacade().getKonkaoaFilesAuditNodeService().removeKonkaoaFilesAuditNode(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaoaFilesAuditNode entity = new KonkaoaFilesAuditNode();
			entity.getMap().put("link_ids", pks);

			getFacade().getKonkaoaFilesAuditNodeService().removeKonkaoaFilesAuditNode(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "link_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward validateName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String audit_node_name = (String) dynaBean.get("audit_node_name");
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
	
		KonkaoaFilesAuditNode entity = new KonkaoaFilesAuditNode();

		KonkaDept konkaDept = new KonkaDept();
		if (ui.getDept_id() == 0L) {
			konkaDept.setDept_id(0L);
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		} else {
			konkaDept = getKonkaDeptForFgs(ui.getDept_id());
		}
		
		entity.setDept_id(konkaDept.getDept_id());
		entity.setAudit_type(2);
		entity.setAudit_level(1l);
		
		String isExist = "null";

		if (StringUtils.isNotBlank(audit_node_name)) {
			entity.setAudit_node_name(audit_node_name);
			List<KonkaoaFilesAuditNode> entityList = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeList(entity);
			if (entityList.size() == 0) {// 可用
				isExist = String.valueOf("0");
			} else {
				isExist = String.valueOf("11");
			}
		}

		super.render(response, isExist, "text/x-json;charset=UTF-8");
		return null;
	}
	
}