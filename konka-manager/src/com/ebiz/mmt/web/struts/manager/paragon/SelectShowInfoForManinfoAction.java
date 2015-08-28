package com.ebiz.mmt.web.struts.manager.paragon;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaParagonShowinfo;
import com.ebiz.mmt.domain.KonkaParagonShowmanre;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 *
 * 
 * 
 */
public class SelectShowInfoForManinfoAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
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
	
}
