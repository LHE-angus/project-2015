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

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EcGoodsPrice;
import com.ebiz.mmt.domain.EcGoodsPriceArea;
import com.ebiz.mmt.domain.EcGoodsPriceModifyForTouc;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Pan,Gang
 * @version 2013-09-26
 */
public class EcGoodsPriceModifyForTouchNetAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list2(mapping, form, request, response);
	}

	public ActionForward pingbi(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String goods_id_1 = (String) dynaBean.get("goods_id_1");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}

		EcGoodsPriceModifyForTouc et = new EcGoodsPriceModifyForTouc();
		et.setGoods_id(Long.valueOf(goods_id_1));
		et.setDept_id(user.getId());
		et = super.getFacade().getEcGoodsPriceModifyForToucService().getEcGoodsPriceModifyForTouc(et);
		if (null != et) {
			EcGoodsPriceModifyForTouc et1 = new EcGoodsPriceModifyForTouc();
			et1.setGoods_id(et.getGoods_id());
			et1.setDept_id(user.getId());
			super.getFacade().getEcGoodsPriceModifyForToucService().removeEcGoodsPriceModifyForTouc(et1);

			EcGoodsPriceModifyForTouc entity = new EcGoodsPriceModifyForTouc();
			entity.setGoods_id(Long.valueOf(goods_id_1));
			entity.setDept_id(user.getId());
			entity.setType(10);
			entity.setAdd_date(new Date());
			super.getFacade().getEcGoodsPriceModifyForToucService().createEcGoodsPriceModifyForTouc(entity);
		} else {
			EcGoodsPriceModifyForTouc entity = new EcGoodsPriceModifyForTouc();
			entity.setGoods_id(Long.valueOf(goods_id_1));
			entity.setDept_id(user.getId());
			entity.setType(10);
			entity.setAdd_date(new Date());
			super.getFacade().getEcGoodsPriceModifyForToucService().createEcGoodsPriceModifyForTouc(entity);
		}

		return new ActionForward("/../customer/manager/EcGoodsPriceModifyForTouchNet.do?method=list2&goods_id="
		        + goods_id_1);
	}

	public ActionForward huifu(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String goods_id_1 = (String) dynaBean.get("goods_id_1");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}

		EcGoodsPriceModifyForTouc entity = new EcGoodsPriceModifyForTouc();
		entity.setGoods_id(Long.valueOf(goods_id_1));
		entity.setDept_id(user.getId());
		super.getFacade().getEcGoodsPriceModifyForToucService().removeEcGoodsPriceModifyForTouc(entity);

		return new ActionForward("/../customer/manager/EcGoodsPriceModifyForTouchNet.do?method=list2&goods_id="
		        + goods_id_1);
	}

	public ActionForward list2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String goods_id = (String) dynaBean.get("goods_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}
		request.setAttribute("is_r3_customer", "1");// R3客户

		KonkaBcompPd kpd = new KonkaBcompPd();
		kpd.setId(Long.valueOf(goods_id));
		kpd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kpd);

		dynaBean.set("pd_dept", kpd.getDept_sn());
		dynaBean.set("lock_state", kpd.getLock_state());

		EcGoodsPrice entity = new EcGoodsPrice();
		if (StringUtils.isNotBlank(goods_id)) {
			entity.setGoods_id(Long.valueOf(goods_id));
		}

		Long recordCount = super.getFacade().getEcGoodsPriceService().getEcGoodsPriceForDeptNameAndPdNameCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcGoodsPrice> entityList = super.getFacade().getEcGoodsPriceService()
		        .getEcGoodsPriceForDeptNameAndPdNameList(entity);
		// 查询分公司管辖区域
		for (EcGoodsPrice ecGoodsPrice : entityList) {
			EcGoodsPriceArea eca = new EcGoodsPriceArea();
			eca.setPrice_id(ecGoodsPrice.getId());
			List<EcGoodsPriceArea> areas = super.getFacade().getEcGoodsPriceAreaService().getEcGoodsPriceAreaList(eca);
			List<String> pNameList = new ArrayList<String>();
			for (EcGoodsPriceArea ecGoodsPriceArea : areas) {
				pNameList.add(ecGoodsPriceArea.getP_name());
			}
			String areaList = StringUtils.join(pNameList.toArray(), ',');
			ecGoodsPrice.getMap().put("areaList", areaList);
		}

		// 查询屏蔽的商品
		EcGoodsPriceModifyForTouc ept = new EcGoodsPriceModifyForTouc();
		ept.setGoods_id(Long.valueOf(goods_id));
		ept.setDept_id(user.getId());
		List<EcGoodsPriceModifyForTouc> eptList = super.getFacade().getEcGoodsPriceModifyForToucService()
		        .getEcGoodsPriceModifyForToucList(ept);
		for (EcGoodsPriceModifyForTouc ecGoodsPriceModifyForTouc : eptList) {

			KonkaBcompPd pd = new KonkaBcompPd();
			pd.setId(ecGoodsPriceModifyForTouc.getGoods_id());
			pd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(pd);
			ecGoodsPriceModifyForTouc.getMap().put("pd_name", pd.getPd_name());

		}

		// 判断该商品是否已经屏蔽
		ept.setType(10);
		Long count = super.getFacade().getEcGoodsPriceModifyForToucService().getEcGoodsPriceModifyForToucCount(ept);
		if (count > 0) {
			dynaBean.set("is_pingbi", true);
		}

		request.setAttribute("eptList", eptList);
		request.setAttribute("entityList", entityList);

		return new ActionForward("/../customer/EcGoodsPriceModifyForTouchNet/list2.jsp");
	}

	public ActionForward add2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String goods_id_1 = (String) dynaBean.get("goods_id_1");
		logger.info("+++goods_id_1" + goods_id_1);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id().intValue() < 30) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}

		KonkaBcompPd pd = new KonkaBcompPd();
		pd.setId(Long.valueOf(goods_id_1));
		pd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(pd);
		dynaBean.set("pd_name", pd.getPd_name());
		dynaBean.set("label_of_cate", pd.getLabel_of_cate());

		if (zb) {
			request.setAttribute("is_admin", "1");
			dynaBean.set("price_type", "0");
		}
		if (fgs) {
			request.setAttribute("is_fgs", "1");
			dynaBean.set("price_type", "2");
		}

		dynaBean.set("goods_id", goods_id_1);
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		return new ActionForward("/../manager/spgl/EcGoodsPriceModifyForTouchNet/add2.jsp");
	}

	public ActionForward save2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String dept_id = (String) dynaBean.get("dept_id");
		String price_type = (String) dynaBean.get("price_type");
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");
		String goods_id = (String) dynaBean.get("goods_id");
		String remarks = (String) dynaBean.get("remarks");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaBcompPd kpd = new KonkaBcompPd();
		kpd.setId(Long.valueOf(goods_id));
		kpd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kpd);

		// 验证同一区域价格的唯一性
		EcGoodsPrice entity = new EcGoodsPrice();
		if (price_type.equals("0")) {
			entity.setGoods_id(Long.valueOf(goods_id));
			entity.setPrice_type(0);
			Long count = super.getFacade().getEcGoodsPriceService().getEcGoodsPriceCount(entity);
			if (count > 0) {
				String msg = super.getMessage(request, "konka.price.error");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		} else if (price_type.equals("2")) {
			entity.setGoods_id(Long.valueOf(goods_id));
			entity.setDept_id(Long.valueOf(dept_id));
			entity.setPrice_type(2);
			Long count = super.getFacade().getEcGoodsPriceService().getEcGoodsPriceCount(entity);
			if (count > 0) {
				String msg = super.getMessage(request, "konka.price.error");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		} else if (price_type.equals("3")) {
			entity.setGoods_id(Long.valueOf(goods_id));
			entity.setPrice_type(3);
			if (StringUtils.isNotBlank(town)) {
				entity.getMap().put("pindex_id", town);
			} else if (StringUtils.isNotBlank(country) && StringUtils.isBlank(town)) {
				entity.getMap().put("pindex_id", country);
			} else if (StringUtils.isNotBlank(city)) {
				entity.getMap().put("pindex_id", city);
			} else if (StringUtils.isNotBlank(province) && StringUtils.isBlank(city)) {
				entity.getMap().put("pindex_id", province);
			}
			Long count = super.getFacade().getEcGoodsPriceService().getEcGoodsPriceAndAreaCount(entity);
			if (count > 0) {
				String msg = super.getMessage(request, "konka.price.error");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		}

		EcGoodsPrice ec = new EcGoodsPrice();
		super.copyProperties(ec, form);
		ec.setGoods_id(Long.valueOf(goods_id));

		ec.setPrice_type(Integer.valueOf(price_type));
		if (StringUtils.isNotBlank(dept_id)) {
			ec.setDept_id(Long.valueOf(dept_id));
		}

		if (StringUtils.isEmpty(id)) {
			if (price_type.equals("2")) {
				if (StringUtils.isNotBlank(dept_id)) {
					KonkaDept dept = new KonkaDept();
					dept.setDept_id(Long.valueOf(dept_id));
					dept = super.getFacade().getKonkaDeptService().getKonkaDept(dept);
					List<EcGoodsPriceArea> ecGoodsPriceAreaList = new ArrayList<EcGoodsPriceArea>();

					// 查询分公司管辖区域
					BaseProvinceListFour bpl = new BaseProvinceListFour();
					if (StringUtils.isBlank(dept.getM_areas_ids())) {
						String msg = super.getMessage(request, "ec.dept.area.error");
						super.renderJavaScript(response, "window.onload=function(){alert('" + msg
						        + "');history.back();}");
						return null;
					}
					bpl.getMap().put("p_index_in", dept.getM_areas_ids().split(","));
					List<BaseProvinceListFour> bplList = super.getFacade().getBaseProvinceListFourService()
					        .getBaseProvinceListFourList(bpl);
					for (BaseProvinceListFour t1 : bplList) {
						EcGoodsPriceArea t = new EcGoodsPriceArea();
						t.setPindex_id(t1.getP_index());
						t.setP_name(t1.getP_name());
						ecGoodsPriceAreaList.add(t);
					}
					ec.setEcGoodsPriceAreaList(ecGoodsPriceAreaList);
					ec.setRemarks(remarks);
					super.getFacade().getEcGoodsPriceService().createEcGoodsPriceWithPindex(ec);

				}
				super.saveMessage(request, "entity.inserted");
			} else if (price_type.equals("3")) {
				// super.getFacade().getEcGoodsPriceService().createEcGoodsPriceAndArea(ec,
				// province, city, country, town);
			} else if (price_type.equals("0")) {
				super.getFacade().getEcGoodsPriceService().createEcGoodsPrice(ec);
			}
		} else {
			super.getFacade().getEcGoodsPriceService().modifyEcGoodsPrice(ec);
			super.saveMessage(request, "entity.updated");
		}

		// end
		return new ActionForward("/../manager/spgl/EcGoodsPriceModifyForTouchNet.do?method=list2&goods_id=" + goods_id);

	}

	public ActionForward delete2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String goods_id = (String) dynaBean.get("goods_id");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		EcGoodsPrice ec = new EcGoodsPrice();
		ec.setId(Long.valueOf(id));
		super.getFacade().getEcGoodsPriceService().removeEcGoodsPrice(ec);

		saveMessage(request, "entity.deleted");

		return new ActionForward("/../manager/spgl/EcGoodsPriceModifyForTouchNet.do?method=list2&goods_id=" + goods_id);
	}

}
