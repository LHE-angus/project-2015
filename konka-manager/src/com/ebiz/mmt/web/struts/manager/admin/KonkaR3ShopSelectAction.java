package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileCategory;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;
/**
 * @author Jiang,JiaYong
 * @version 2013-05-10
 */
public class KonkaR3ShopSelectAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String fgs_dept_id = (String) dynaBean.get("fgs_dept_id");
		String jyb_dept_id = (String) dynaBean.get("jyb_dept_id");
		String bsc_dept_id = (String) dynaBean.get("bsc_dept_id");
		String code_like = (String) dynaBean.get("code_like");
		String keyword = (String) dynaBean.get("keyword");
		Pager pager = (Pager) dynaBean.get("pager");

		String dept_name = "";
		if(GenericValidator.isLong(fgs_dept_id)){
			KonkaDept kd = new KonkaDept();
			kd.setDept_id(Long.valueOf(fgs_dept_id));
			kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
			dept_name = kd.getDept_name();
		}
		String jyb_name = "";
		if(GenericValidator.isLong(jyb_dept_id)){
			KonkaDept kd = new KonkaDept();
			kd.setDept_id(Long.valueOf(jyb_dept_id));
			kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
			jyb_name = kd.getDept_name();
		}
		String bsc_name = "";
		if(GenericValidator.isLong(bsc_dept_id)){
			KonkaDept kd = new KonkaDept();
			kd.setDept_id(Long.valueOf(bsc_dept_id));
			kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
			bsc_name = kd.getDept_name();
		}
		
		KonkaR3Shop entity = new KonkaR3Shop();
		entity.setIs_del(new Long(0));
		entity.getMap().put("branch_area_name_like", dept_name);
		if(StringUtils.isNotEmpty(jyb_name)){
			entity.getMap().put("handle_name_like", jyb_name);
		}
		if(StringUtils.isNotEmpty(bsc_name)){
			entity.getMap().put("handle_name_like", bsc_name);
		}
		entity.getMap().put("code_like", code_like);
		entity.getMap().put("keyword", keyword);
		Long recordCount = getFacade().getKonkaR3ShopService().getKonkaR3ShopCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService().getKonkaR3ShopPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		
		//  判断当期用户类型
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		String dept_temp = "";
		if (peProdUser != null) {
			KonkaMobileCategory _t = new KonkaMobileCategory();
			_t.setC_index(peProdUser.getDept_id());
			dept_temp = super.getFacade().getKonkaMobileCategoryService().getKonkaMobileDept(_t);
		}
		if (null != dept_temp) { // 分公司用户
			request.setAttribute("isHeadquarters", "false");
		} else { // 总部用户
			request.setAttribute("isHeadquarters", "true");
		}
		
		/// 查询分公司信息
		KonkaDept kd = new KonkaDept();
		kd.setDept_type(3);
		kd.setPar_id(0L);
		kd.getMap().put("order_by_dept_name", true);
		List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(kd);
		request.setAttribute("konkaDeptList", konkaDeptList);
		
		dynaBean.set("fgs_dept_id", fgs_dept_id);
		dynaBean.set("jyb_dept_id", jyb_dept_id);
		dynaBean.set("bsc_dept_id", bsc_dept_id);
		dynaBean.set("keyword", keyword);
		dynaBean.set("code_like", code_like);
		logger.info("fgs_dept_id======={}",fgs_dept_id);
		logger.info("jyb_dept_id======={}",jyb_dept_id);
		logger.info("bsc_dept_id======={}",bsc_dept_id);
		
		return mapping.findForward("list");
	}
}
