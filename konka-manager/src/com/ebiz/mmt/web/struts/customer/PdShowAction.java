package com.ebiz.mmt.web.struts.customer;

import java.util.ArrayList;
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

import com.ebiz.mmt.domain.EcBcompBinding;
import com.ebiz.mmt.domain.EcBindingPd;
import com.ebiz.mmt.domain.EcGoodsPriceModifyForTouc;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaBcompPdContent;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Pan,Gang
 * @version 2013-09-28
 */
public class PdShowAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String pd_sn_like = (String) dynaBean.get("pd_sn_like");
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		String label_of_cate = (String) dynaBean.get("label_of_cate");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaBcompPd entity = new KonkaBcompPd();
		// 查询r3客户所在分公司
		PeProdUser p_user = new PeProdUser();
		p_user.getMap().put("user_id", user.getId());
		p_user = super.getFacade().getPeProdUserService().getPeProdUserForFgs(p_user);
		// r3客户可以看到他分公司和总部的产品
		if (null != p_user  && null!=p_user.getMap() && null != p_user.getMap().get("fgs_id")) {
			entity.getMap().put("dept_sn_in", "0," + p_user.getMap().get("fgs_id"));

			// 分公司屏蔽的产品，不显示
			entity.getMap().put("fgs_id_eq", p_user.getMap().get("fgs_id").toString());

		}
		
		request.setAttribute("is_r3_customer", "1");// R3客户

		if (StringUtils.isNotBlank(pd_sn_like)) {
			entity.getMap().put("pd_sn_like", pd_sn_like);
		}
		if (StringUtils.isNotBlank(pd_name_like)) {
			entity.getMap().put("pd_name_like", pd_name_like);
		}
		if (StringUtils.isNotBlank(label_of_cate)) {
			entity.setLabel_of_cate(Integer.valueOf(label_of_cate));
		}
		entity.setState(1);

		Long recordCount = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdWithDeptAndMdForCustCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaBcompPd> entityList = super.getFacade().getKonkaBcompPdService()
		        .getKonkaBcompPdWithDeptAndMdForCustPaginatedList(entity);

		for (KonkaBcompPd konkaBcompPd : entityList) {
			EcGoodsPriceModifyForTouc ec = new EcGoodsPriceModifyForTouc();
			ec.setDept_id(user.getCust_id());
			ec.setType(11);
			ec.setGoods_id(konkaBcompPd.getId());
			List<EcGoodsPriceModifyForTouc> elLists = super.getFacade().getEcGoodsPriceModifyForToucService()
			        .getEcGoodsPriceModifyForToucList(ec);
			if (null != elLists && elLists.size() > 0) {
				konkaBcompPd.getMap().put("is_pingbi", true);
			}
		}
		request.setAttribute("entityList", entityList);

		// 取分公司列表
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		List<KonkaDept> deptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		request.setAttribute("deptList", deptList);
		request.setAttribute("today", new Date());

		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (GenericValidator.isLong(id)) {
			KonkaBcompPd entity = new KonkaBcompPd();
			entity.setId(Long.valueOf(id));
			entity = getFacade().getKonkaBcompPdService().getKonkaBcompPd(entity);
			if (null != entity) {
				String main_pic = entity.getMain_pic();
				if (StringUtils.isNotBlank(main_pic)) {
					String main_pic_file = StringUtils.split(main_pic, ",")[0];
					dynaBean.set("main_pic_file", main_pic_file); // 主图

					List<String> list = new ArrayList<String>(); // 附图list
					String[] picArr = StringUtils.split(main_pic, ",");
					if (null != picArr && picArr.length > 0) {
						for (int i = 0; i < picArr.length; i++) {
							if (!StringUtils.equals(main_pic_file, picArr[i])) {
								list.add(picArr[i]);
							}
						}
					}
					request.setAttribute("picList", list);
				}

				String pd_res = entity.getPd_res();
				dynaBean.set("pd_res_x", StringUtils.split(pd_res, ",")[0]);
				dynaBean.set("pd_res_y", StringUtils.split(pd_res, ",")[1]);
			}
			entity.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, entity);

			// 回显产品描述、产品规格、售后服务
			KonkaBcompPdContent pc = new KonkaBcompPdContent();
			pc.setKbp_id(Long.valueOf(id));
			List<KonkaBcompPdContent> pct = super.getFacade().getKonkaBcompPdContentService()
			        .getKonkaBcompPdContentList(pc);
			for (KonkaBcompPdContent pp : pct) {
				request.setAttribute("content" + pp.getType(), pp.getContent());
			}

			// 回显绑定的套餐
			List<Long> had_binding_idList = new ArrayList<Long>();
			EcBcompBinding ebb = new EcBcompBinding();
			ebb.setGoods_id(Long.valueOf(id));
			List<EcBcompBinding> ebbList = super.getFacade().getEcBcompBindingService().getEcBcompBindingList(ebb);
			if (null != ebbList && 0 != ebbList.size()) {
				for (EcBcompBinding ecBcompBinding : ebbList) {
					had_binding_idList.add(ecBcompBinding.getBinding_id());
				}
			}

			// 回显服务套餐
			EcBindingPd epd = new EcBindingPd();
			epd.setBinding_type(0);
			if (null != had_binding_idList && 0 != had_binding_idList.size()) {
				// 查询绑定的服务
				epd.getMap().put("id_in", had_binding_idList);
				List<EcBindingPd> ecBindingPdList = super.getFacade().getEcBindingPdService().getEcBindingPdList(epd);
				request.setAttribute("hadEntityList", ecBindingPdList);
			} else {
				request.setAttribute("hadEntityList", null);
			}

			// 回显商品套餐
			EcBindingPd epd1 = new EcBindingPd();
			epd1.setBinding_type(1);
			if (null != had_binding_idList && 0 != had_binding_idList.size()) {
				// 查询绑定的服务
				epd1.getMap().put("id_in", had_binding_idList);
				List<EcBindingPd> ecBindingPdList = super.getFacade().getEcBindingPdService().getEcBindingPdList(epd1);
				request.setAttribute("hadEntityList1", ecBindingPdList);
			} else {
				request.setAttribute("hadEntityList1", null);
			}

			// 取分公司列表
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_type(3);
			List<KonkaDept> deptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
			request.setAttribute("deptList", deptList);

		} else {
			this.saveError(request, "errors.long", new String[] { id });
			// the line below is added for pagination
			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append(mapping.findForward("success").getPath());
			pathBuffer.append("&mod_id=").append(mod_id);
			pathBuffer.append("&");
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			// end
			return forward;
		}
		return new ActionForward("/../customer/PdShow/view.jsp");
	}

	public ActionForward pingbi(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String goods_id_1 = (String) dynaBean.get("goods_id_1");
		String mod_id = (String) dynaBean.get("mod_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}
		if (null == user.getCust_id()) {
			return null;
		}

		EcGoodsPriceModifyForTouc et = new EcGoodsPriceModifyForTouc();
		et.setGoods_id(Long.valueOf(goods_id_1));
		et.setDept_id(user.getCust_id());
		et.setType(11);
		super.getFacade().getEcGoodsPriceModifyForToucService().createEcGoodsPriceModifyForTouc(et);

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward huifu(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String goods_id_1 = (String) dynaBean.get("goods_id_1");
		String mod_id = (String) dynaBean.get("mod_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}

		EcGoodsPriceModifyForTouc entity = new EcGoodsPriceModifyForTouc();
		entity.setType(11);
		entity.setGoods_id(Long.valueOf(goods_id_1));
		entity.setDept_id(user.getCust_id());
		super.getFacade().getEcGoodsPriceModifyForToucService().removeEcGoodsPriceModifyForTouc(entity);

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

}
