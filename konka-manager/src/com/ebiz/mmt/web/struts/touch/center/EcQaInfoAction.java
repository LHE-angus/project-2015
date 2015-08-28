package com.ebiz.mmt.web.struts.touch.center;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EcQaInfo;
import com.ebiz.mmt.domain.EcQaInfo;
import com.ebiz.mmt.domain.EcQaInfo;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.web.struts.member.BaseMemberAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author tudp
 * @version 2013-09-14
 */
public class EcQaInfoAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		//账户中心用户登录验证
				if(ecUser.getUser_type().intValue()==1){
					String touch = (String)session.getAttribute("touch");
					if(!"1".equals(touch)){
						String ctx = super.getCtxPath(request);
						String url=ctx+"/touch/center/Skip.do";
						response.sendRedirect(url);
						return null; 
					}
				} 
		Pager pager = (Pager) dynaBean.get("pager");
		String start_date = (String) dynaBean.get("start_date");
		String end_date = (String) dynaBean.get("end_date");
		String qa_type_code = (String) dynaBean.get("qa_type_code");
		if (qa_type_code == null) {
			qa_type_code = "0";
			dynaBean.set("qa_type_code", qa_type_code);
		}
		EcQaInfo entity = new EcQaInfo();
		super.copyProperties(entity, form);
		entity.setOwn_sys(ecUser.getUser_type());
		entity.setQ_u_id(ecUser.getId().toString());
		entity.getMap().put("q_date_start", start_date);
		entity.getMap().put("q_date_end", end_date);

		Long recordCount = super.getFacade().getEcQaInfoService().getEcQaInfoCount(entity);
		pager.setPageSize(new Integer(5));
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcQaInfo> entityList = super.getFacade().getEcQaInfoService().getEcQaInfoPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String qa_type_code = (String) dynaBean.get("qa_type_code");

		EcQaInfo entity = new EcQaInfo();  
		if(qa_type_code!=null&&!"".equals(qa_type_code)){
		entity.setQa_type_code(new Integer(qa_type_code));
		}
		super.copyProperties(form, entity);
 

		return mapping.findForward("input");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser"); 
		String msg = "信息提交成功！"; 
		EcQaInfo entity = new EcQaInfo();
		super.copyProperties(entity, form);
		entity.setQ_u_id(ecUser.getId().toString());
		entity.setOwn_sys(ecUser.getUser_type());
		entity.setQ_ip(super.getIpAddr(request));
		entity.setInfo_state(new Integer(0));
		entity.setQ_date(new Date()); 
		entity.setQ_name(ecUser.getReal_name()); 
		
		 super.getFacade().getEcQaInfoService().createEcQaInfo(entity);
  
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='"
					+ super.getCtxPath(request) + "/touch/center/EcQaInfo.do?';}"); 
		return null;
	}
}
