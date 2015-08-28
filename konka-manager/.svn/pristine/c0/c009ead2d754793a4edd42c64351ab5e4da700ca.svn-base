package com.ebiz.mmt.web.struts.manager.paragon;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaParagonShowinfo;
import com.ebiz.mmt.domain.KonkaParagonShowmanre;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 *
 * 
 * 
 */
public class SelectShowInfoAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(15);
		String shop_order_like = (String) dynaBean.get("shop_order_like");
		String area_id = (String) dynaBean.get("area_id");
		String part_company_id = (String) dynaBean.get("part_company_id");
		String shop_name_like = (String) dynaBean.get("shop_name_like");
		String user_id = (String) dynaBean.get("user_id");
		String mod_id = (String) dynaBean.get("mod_id");
		
		// 分公司列表
		KonkaDept konka_dept = new KonkaDept();
		konka_dept.setDept_type(3);
		konka_dept.setPar_id(0L);
		List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konka_dept);
		request.setAttribute("deptInfoList", deptInfoList);
		
		// 业务员列表
		KonkaParagonShowinfo entity = new KonkaParagonShowinfo();
		super.copyProperties(entity, form);
		if(StringUtils.isNotBlank(shop_order_like)){
			entity.setShow_shop_code(shop_order_like);
		}
		if(StringUtils.isNotBlank(area_id)){
			entity.setArea_id(new Long(area_id));
		}
		if(StringUtils.isNotBlank(part_company_id)){
			entity.setPart_company_id(new Long(part_company_id));
		}
		if(StringUtils.isNotBlank(shop_name_like)){
			entity.getMap().put("shop_name_like", shop_name_like);
		}
		entity.getMap().put("user_id_like", user_id);
		Long recordCount = getFacade().getKonkaParagonShowinfoService().getKonkaParagonShowinfoListForSelctCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaParagonShowinfo> entityList = super.getFacade().getKonkaParagonShowinfoService().getKonkaParagonShowinfoListForSelct(entity);
		request.setAttribute("entityList", entityList);

		KonkaParagonShowmanre showManre = new KonkaParagonShowmanre();
		if(StringUtils.isNotBlank(user_id)){
			showManre.setUser_id(new Long(user_id));
			dynaBean.set("user_id", user_id);
		}
		dynaBean.set("mod_id", mod_id);
		List<KonkaParagonShowmanre> showManreList  = getFacade().getKonkaParagonShowmanreService().getKonkaParagonShowmanreList(showManre);
		
		request.setAttribute("showManreList", showManreList);
		
		return mapping.findForward("list");

	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (isCancelled(request)) {
			return list(mapping, form, request, response);
		}
		resetToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String user_id = request.getParameter("user_id"); //上报员ID
		String[] pks = (String[]) dynaBean.get("pks");	// 门店ID
		
		// 获取登录用户 企业信息
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		
		KonkaParagonShowmanre entity = new KonkaParagonShowmanre();
		super.copyProperties(entity, form);
		if (!ArrayUtils.isEmpty(pks)) {
			entity.setUser_id(new Long(user_id));
			entity.setAddman(userInfo.getId());
			entity.setAddtime(new Date());
			for(int i=0;i<pks.length;i++){
				String show_shop_code =  (String)dynaBean.get("show_shop_codes_"+pks[i]);
				entity.setShow_shop_code(show_shop_code);
				getFacade().getKonkaParagonShowmanreService().createKonkaParagonShowmanre(entity);
			}
			saveMessage(request, "entity.inserted");
		} 
		
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append("/paragon/SelectShowInfo.do?");
		pathBuffer.append("user_id="+user_id);
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&selectype=mutiple&azaz="+Math.random());
		
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
	
}
