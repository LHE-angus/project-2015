package com.ebiz.mmt.web.struts.manager.jf;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JfRule;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wu,ShangLong
 * @version 2013-06-25
 */
public class JfRuleAction extends BaseJfAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String dept_id = (String) dynaBean.get("dept_id");
		String pd_id_like = (String) dynaBean.get("pd_id_like");
		String jf_type = (String) dynaBean.get("jf_type");
		String jf_avl_type = (String) dynaBean.get("jf_avl_type");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		JfRule entity = new JfRule();
		if (user.getDept_id() > 0L) {
//			entity.setDept_id(user.getDept_id());
			//分公司
			List<KonkaDept> entp_list = super.getDeptInfoListWithOutLimit(mapping, form, request, response);
			if(entp_list != null && entp_list.size() == 1){
				entity.setDept_id(entp_list.get(0).getDept_id());
			}
		} else {
			if (StringUtils.isNotBlank(dept_id)) {
				entity.setDept_id(Long.valueOf(dept_id));
			}
		}
		if (StringUtils.isNotBlank(pd_id_like)) {
			entity.getMap().put("pd_id_like", pd_id_like);
		}
		if (StringUtils.isNotBlank(jf_type)) {
			entity.setJf_type(Integer.valueOf(jf_type));
		}
		if (StringUtils.isNotBlank(jf_avl_type)) {
			entity.setJf_avl_type(Integer.valueOf(jf_avl_type));
		}

		Long recordCount = super.getFacade().getJfRuleService().getJfRuleCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<JfRule> entityList = super.getFacade().getJfRuleService().getJfRulePaginatedList(entity);

		if (null != entityList && entityList.size() > 0) {
			for (JfRule rule : entityList) {
				KonkaDept dept = new KonkaDept();
				dept.setDept_id(rule.getDept_id());
				dept = super.getFacade().getKonkaDeptService().getKonkaDept(dept);
				rule.getMap().put("dept_name", dept.getDept_name());
			}

		}

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		PePdModel pd = new PePdModel();
		List<PePdModel> pdList = super.getFacade().getPePdModelService().getPePdModelList(pd);

		request.setAttribute("pdList", pdList);

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		PePdModel pd = new PePdModel();
		List<PePdModel> pdList = super.getFacade().getPePdModelService().getPePdModelList(pd);
		request.setAttribute("pdList", pdList);

		JfRule entity = new JfRule();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getJfRuleService().getJfRule(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String dept_id = (String) dynaBean.get("dept_id");
		String pd_id = (String) dynaBean.get("pd_id");
		String jf_avl_type=(String)dynaBean.get("jf_avl_type");
		String jf_avl_start = (String)dynaBean.get("jf_avl_start");
		String jf_avl_end = (String)dynaBean.get("jf_avl_end");
		String jf_type=(String)dynaBean.get("jf_type");
		String jf_value=(String)dynaBean.get("jf_value");
		
		JfRule entity = new JfRule();
		super.copyProperties(entity, form);
//		logger.info(mod_id);
		
		//定义一个临时对象
		JfRule temp = new JfRule();
		if (StringUtils.isEmpty(id)) {
			if (StringUtils.isNotBlank(dept_id)) {
				temp.setDept_id(Long.valueOf(dept_id));//分公司
			}
			if (StringUtils.isNotBlank(pd_id)) {
				temp.setPd_id(String.valueOf(pd_id));//产品
			}
			if (StringUtils.isNotBlank(jf_avl_type)) {
				temp.setJf_avl_type(Integer.valueOf(jf_avl_type));//有效期：长期有效，时间段有效
			}
			
			if ("1".equals(jf_avl_type)) {
				Long countRecord = getFacade().getJfRuleService().getJfRuleCount(temp);
				if (countRecord.intValue() > 0) { 
					String msg = super.getMessage(request, "jf.rule.error");
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
					return null;
				} else { 
					super.getFacade().getJfRuleService().createJfRule(entity);
					super.saveMessage(request, "entity.inserted");
				}
			} else if ("2".equals(jf_avl_type)) {
				Date start = DateUtils.parseDate(jf_avl_start, new String[]{"yyyy-MM-dd"}); 
				Date end = DateUtils.parseDate(jf_avl_end, new String[]{"yyyy-MM-dd"}); 
				List<JfRule> list = getFacade().getJfRuleService().getJfRuleList(temp);
				if (null != list && list.size() > 0) {
					boolean flag = true;
					for (JfRule jfRule : list) {
						Date start_date = jfRule.getJf_avl_start();
						Date end_date = jfRule.getJf_avl_end();
						if (start.getTime() < start_date.getTime() && end.getTime() < start_date.getTime() && end.getTime() >= start.getTime()) {
							flag = true;
						} else if (end.getTime() > end_date.getTime() && start.getTime() > end_date.getTime() && end.getTime() >= start.getTime()) {
							flag = true;
						} else {
							flag = false;
							break;
						}
					}
					
					if (flag) {
						super.getFacade().getJfRuleService().createJfRule(entity);
						super.saveMessage(request, "entity.inserted");
						
					} else {
						String msg = super.getMessage(request, "jf.rule.time");
						super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
						return null;
					}
				} else {
					if(end.getTime()>start.getTime()){
						super.getFacade().getJfRuleService().createJfRule(entity);
						super.saveMessage(request, "entity.inserted");
					}else{
						String msg = super.getMessage(request, "jf.rule.time");
						super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
						return null;
					}
				}
			}
			
			
		} else {
			if (StringUtils.isNotBlank(jf_type)) {
				temp.setJf_type(Integer.valueOf(jf_type));
			}
			if (StringUtils.isNotBlank(jf_value)) {
				Double _jf_value=Double.valueOf(jf_value);
				temp.setJf_value(BigDecimal.valueOf(_jf_value));
			}
			super.getFacade().getJfRuleService().modifyJfRule(entity);
			super.saveMessage(request, "entity.updated");
			
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
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		JfRule entity = new JfRule();
		entity.setId(Long.valueOf(id));
		super.getFacade().getJfRuleService().removeJfRule(entity);

		saveMessage(request, "entity.deleted");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.serialize(request, "id", "method"));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
}
