package com.ebiz.mmt.web.struts.webservice.wap.center;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcGoodsRebate;
import com.ebiz.mmt.domain.EcGroup;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcUserScoreRec;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.zxmall.BaseMemberAction;
import com.ebiz.org.apache.commons.lang3.StringUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author tudp
 * @version 2013-09-14
 */
public class EcGoodsRebateAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form; 
		// 从Session中取用户并判断
		EcUser ecUser = (EcUser) super.getSessionAttribute(request, Constants.EPP_USER);
		if (null == ecUser) {
			String msg = super.getMessage(request, "ec.nologin");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='" + super.getCtxPath(request)
					+ "/webservice/wap/login.do';}");
			return null;
		}
		
		String pd_sn_or_pd_name_like = (String) dynaBean.get("pd_sn_or_pd_name_like");
		String prod_type = (String) dynaBean.get("prod_type");
		String order_by_price_desc = (String) dynaBean.get("order_by_price_desc");
		String order_by_price_asc = (String) dynaBean.get("order_by_price_asc");
		Integer own_sys = ecUser.getUser_type();
		if (ecUser.getUser_type().intValue() == 1 && ecUser.getDept_id().intValue() > 0) {
			own_sys = new Integer(5);
		}
		KonkaBcompPd entity = new KonkaBcompPd();

		if (own_sys.intValue() == 2) {// 触网用户 根据客户ID(c_id)
			// ,分公司ID（dept_id)屏蔽商品
			if (ecUser.getPlat_sys() != null) {
				if (ecUser.getPlat_sys().intValue() == 1) {
					entity.getMap().put("plat_sys_eq_1", "1");
					entity.getMap().put("own_sys_new_in_02", "1");
					entity.getMap().put("own_sys_in_02_new", "1");
					entity.getMap().put("dept_id", ecUser.getDept_id());
					EcGroup eg = new EcGroup();
					eg.setId(ecUser.getDept_id());
					eg = super.getFacade().getEcGroupService().getEcGroup(eg);
					if (eg != null && eg.getLink_dept_id() != null) {
						entity.getMap().put("dept_id_new", eg.getLink_dept_id());
					}
					entity.getMap().put("c_id", ecUser.getCust_id());
				} else if (ecUser.getPlat_sys().intValue() == 0) {
					entity.getMap().put("plat_sys_eq_0", "1");
					entity.getMap().put("own_sys_new_in_02_for_zb", "1");
					entity.getMap().put("own_sys_in_02_new", "1");
					entity.getMap().put("dept_id", ecUser.getDept_id());
					EcGroup eg = new EcGroup();
					eg.setId(ecUser.getDept_id());
					eg = super.getFacade().getEcGroupService().getEcGroup(eg);
					if (eg != null && eg.getLink_dept_id() != null) {
						entity.getMap().put("dept_id_new", eg.getLink_dept_id());
					}
					entity.getMap().put("c_id", ecUser.getCust_id());
				}
			}

		}
		entity.setState(1); 
		entity.getMap().put("pindex_id_eq", new Long[] { -1L });
		
		if (StringUtils.isNotBlank(pd_sn_or_pd_name_like)) {
			entity.getMap().put("pd_sn_or_pd_name_like", pd_sn_or_pd_name_like); 
		}
		if (StringUtils.isNotBlank(order_by_price_desc)) {
			entity.getMap().put("order_by_price_desc", order_by_price_desc);
		}
		if (StringUtils.isNotBlank(order_by_price_asc)) {
			entity.getMap().put("order_by_price_asc", order_by_price_asc);
		}
		if (StringUtils.isNotBlank(prod_type)) {
			if ("1".equals(prod_type)) {
				entity.getMap().put("prod_type_in", new Integer[] { 4, 5, 6 });
			} else {
				entity.setProd_type(new Integer(prod_type));
			}
		}
		
		// 分页
		String page = request.getParameter("page");
		String forward = (String) dynaBean.get("forward");
		int currentPage = 1;
		int pageSize = 5;
		if (!StringUtils.isEmpty(page)) {
			currentPage = (Integer.parseInt(page)) + (Integer.parseInt(forward));
		}
		entity.getRow().setFirst((currentPage - 1) * pageSize);
		entity.getRow().setCount(pageSize);
		//总条数recordCount
		Long recordCount = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdForEcPriceAndSaleNewCount(entity);

		int pageCount = (int) Math.ceil((recordCount + pageSize - 1) / pageSize);
		
		dynaBean.set("recordCount", recordCount);
		
		if (recordCount > 0) {
			dynaBean.set("currentPage", currentPage);
			dynaBean.set("pageCount", pageCount);
			if (currentPage > pageCount) {
				super.renderJavaScript(response, "window.onload=function(){alert('当前页已是最后一页！');history.back();}");
				return null;
			}
			List<KonkaBcompPd> entityList = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdForEcPriceAndSaleNewPaginatedList(entity);
			for(KonkaBcompPd pd: entityList){
				EcGoodsRebate ecGoodsRebate = new EcGoodsRebate();				
				ecGoodsRebate.setGoods_id(pd.getId());
				ecGoodsRebate.setPlat_sys(ecUser.getPlat_sys());
				ecGoodsRebate.setOwn_sys(2);
				ecGoodsRebate.setDept_id(ecUser.getDept_id());
				ecGoodsRebate.setState(0);
				ecGoodsRebate.setC_id(ecUser.getCust_id());
				ecGoodsRebate = super.getFacade().getEcGoodsRebateService().getEcGoodsRebate(ecGoodsRebate);			
				if(ecUser.getCust_id()!=null&&ecGoodsRebate==null){
					ecGoodsRebate = new EcGoodsRebate();
					ecGoodsRebate.setGoods_id(pd.getId());
					ecGoodsRebate.setPlat_sys(ecUser.getPlat_sys());
					ecGoodsRebate.setOwn_sys(2);
					ecGoodsRebate.setDept_id(ecUser.getDept_id());
					ecGoodsRebate.setState(0);
					ecGoodsRebate = super.getFacade().getEcGoodsRebateService().getEcGoodsRebate(ecGoodsRebate);
				}
				pd.getMap().put("ecGoodsRebate", ecGoodsRebate); 
			}
			request.setAttribute("entityList", entityList);
		}
		
		return mapping.findForward("list");
	}
}
