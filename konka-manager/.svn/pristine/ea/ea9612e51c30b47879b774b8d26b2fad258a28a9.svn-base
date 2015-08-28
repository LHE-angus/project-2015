package com.ebiz.mmt.web.struts.manager.spgl;

import java.text.SimpleDateFormat;
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
import com.ebiz.mmt.domain.EcGoodsIntegral;
import com.ebiz.mmt.domain.EcGoodsPrice;
import com.ebiz.mmt.domain.EcGoodsPriceArea;
import com.ebiz.mmt.domain.EcGroup;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Pan,Gang
 * @version 2013-09-09
 */
public class EcGoodsIntegralNewAction extends BaseAction {

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
		String id = (String) dynaBean.get("id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaBcompPd entity = new KonkaBcompPd();
		if (StringUtils.isNotBlank(pd_sn_like)) {
			entity.getMap().put("pd_sn_like", pd_sn_like);
		}
		if (StringUtils.isNotBlank(pd_name_like)) {
			entity.getMap().put("pd_name_like", pd_name_like);
		}
		if (StringUtils.isNotBlank(label_of_cate)) {
			entity.setLabel_of_cate(Integer.valueOf(label_of_cate));
		}
		entity.setState(1);// 1表示正常
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}

		Long recordCount = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaBcompPd> entityList = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String id = (String) dynaBean.get("id");
		String price_type = (String) dynaBean.get("price_type");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		EcGoodsPrice ecp = new EcGoodsPrice();
		ecp.setGoods_id(Long.valueOf(id));
		ecp.setPrice_type(Integer.valueOf(price_type));

		Long recordCount = super.getFacade().getEcGoodsPriceService().getEcGoodsPriceForDeptNameAndPdNameCount(ecp);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		ecp.getRow().setFirst(pager.getFirstRow());
		ecp.getRow().setCount(pager.getRowCount());

		List<EcGoodsPrice> entityList = super.getFacade().getEcGoodsPriceService()
		        .getEcGoodsPriceForDeptNameAndPdNameList(ecp);
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
		request.setAttribute("entityList", entityList);
		request.setAttribute("goods_id_1", id);

		return mapping.findForward("view");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String goods_id_1 = (String) dynaBean.get("goods_id_1");
		String price_type = (String) dynaBean.get("price_type");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaBcompPd pd = new KonkaBcompPd();
		pd.setId(Long.valueOf(goods_id_1));
		pd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(pd);
		dynaBean.set("pd_name", pd.getPd_name());
		dynaBean.set("label_of_cate", pd.getLabel_of_cate());

		dynaBean.set("goods_id", goods_id_1);
		dynaBean.set("price_type", price_type);
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String price_type = (String) dynaBean.get("price_type");
		String goods_id = (String) dynaBean.get("goods_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaBcompPd pd = new KonkaBcompPd();
		pd.setId(Long.valueOf(goods_id));
		pd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(pd);
		dynaBean.set("pd_name", pd.getPd_name());

		if (GenericValidator.isLong(id)) {
			EcGoodsPrice ec = new EcGoodsPrice();
			ec.setId(Long.valueOf(id));
			ec.setPrice_type(Integer.valueOf(price_type));
			ec = super.getFacade().getEcGoodsPriceService().getEcGoodsPrice(ec);
			ec.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, ec);
			dynaBean.set("price_type", price_type);
		} else {
			this.saveError(request, "errors.long", new String[] { id });
			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append(mapping.findForward("success").getPath());
			pathBuffer.append("&mod_id=").append(mod_id);
			pathBuffer.append("&");
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			// end
			return forward;
		}
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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
		return new ActionForward("/../manager/spgl/EcGoodsPrice.do?method=view&id=" + goods_id + "&price_type="
		        + price_type);

	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String goods_id = (String) dynaBean.get("goods_id");
		String price_type = (String) dynaBean.get("price_type");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		EcGoodsPrice ec = new EcGoodsPrice();
		ec.setId(Long.valueOf(id));
		super.getFacade().getEcGoodsPriceService().removeEcGoodsPrice(ec);

		saveMessage(request, "entity.deleted");

		return new ActionForward("/../manager/spgl/EcGoodsPrice.do?method=view&id=" + goods_id + "&price_type="
		        + price_type);
	}

	public ActionForward list2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String goods_id = (String) dynaBean.get("goods_id");

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
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 178 || pu.getRole_id().intValue() == 140317
				        || pu.getRole_id().intValue() == 1001) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}

		KonkaBcompPd kpd = new KonkaBcompPd();
		if (StringUtils.isNotBlank(goods_id)) {
			kpd.setId(Long.valueOf(goods_id));
			kpd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kpd);
			if (null != kpd) {
				dynaBean.set("pd_dept", kpd.getDept_sn());
				dynaBean.set("pd_name", kpd.getPd_name());
				dynaBean.set("lock_state", kpd.getLock_state());
				dynaBean.set("pd_sn", kpd.getPd_sn());
			} else {
				// 分公司添加商品
				dynaBean.set("pd_dept", super.getSuperiorForDeptType(user.getDept_id(), 3));
			}

		}

		EcGoodsIntegral entity = new EcGoodsIntegral();
		entity.setOwn_sys(2);
		if (StringUtils.isNotBlank(goods_id)) {
			entity.setGoods_id(Long.valueOf(goods_id));
		}

		if (zb) {
			request.setAttribute("is_admin", "1");
		} else if (fgs) {
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			dynaBean.set("fgs_id", fgs_dept.getDept_id());
			dynaBean.set("fgs_name", fgs_dept.getDept_name());

			EcGroup eg = new EcGroup();
			eg.setLink_dept_id(fgs_dept.getDept_id());
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			if (deptList != null && deptList.size() > 0) {
				List<String> ids = new ArrayList<String>();
				for (EcGroup ecGroup : deptList) {
					ids.add(ecGroup.getId().toString());
				}
				dynaBean.set("group_id", StringUtils.join(ids, ","));
				dynaBean.set("is_binding", true);
				entity.getMap().put("is_fgs", ids);
			} else {
				entity.getMap().put("is_fgs", new String[] { "999999" });
			}

			request.setAttribute("is_fgs", "1");

		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('你没有权限查看价格！');history.back();}");
			return null;
		}

		Long recordCount = super.getFacade().getEcGoodsIntegralService().getEcGoodsIntegralNewCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcGoodsIntegral> entityList = super.getFacade().getEcGoodsIntegralService()
		        .getEcGoodsIntegralNewPaginatedList(entity);

		request.setAttribute("entityList", entityList);
		dynaBean.set("goods_id", goods_id);

		return new ActionForward("/../manager/spgl/EcGoodsIntegralNew/list2.jsp");
	}

	public ActionForward add2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String goods_id_1 = (String) dynaBean.get("goods_id_1");
		String mod_id = (String) dynaBean.get("mod_id");

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
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 140317 || pu.getRole_id().intValue() == 1001) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}

		if (zb) {
			request.setAttribute("is_admin", "1");
			EcGroup eg = new EcGroup();
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("sybDeptInfoList", deptList);
		} else if (fgs) {
			request.setAttribute("is_fgs", "1");
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			EcGroup eg = new EcGroup();
			eg.setLink_dept_id(fgs_dept.getDept_id());
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("sybDeptInfoList", deptList);
			if (null != user.getDept_id()) {
				KonkaDept konkaDept = new KonkaDept();
				konkaDept.setDept_id(user.getDept_id());
				konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
				// 分公司取得
				if (null != konkaDept.getDept_type() && konkaDept.getDept_type() > 2) {
					dynaBean.set("fgs_id", fgs_dept.getDept_id());
				}

			}

		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('你没有权限查看价格！');history.back();}");
			return null;
		}

		KonkaBcompPd pd = new KonkaBcompPd();
		pd.setId(Long.valueOf(goods_id_1));
		pd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(pd);

		if (null != pd) {
			dynaBean.set("dept_sn", pd.getDept_sn());
			dynaBean.set("pd_name", pd.getPd_name());
			dynaBean.set("pd_sn", pd.getPd_sn());
		}

		dynaBean.set("goods_id", goods_id_1);
		dynaBean.set("mod_id", mod_id);
		dynaBean.set("state", "0");

		return new ActionForward("/../manager/spgl/EcGoodsIntegralNew/add2.jsp");
	}

	public ActionForward save2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");// 上架表id
		String start_date = (String) dynaBean.get("start_date");
		String end_date = (String) dynaBean.get("end_date");
		String goods_id = (String) dynaBean.get("goods_id");
		String dept_id = (String) dynaBean.get("dept_id");
		String integral_type = (String) dynaBean.get("integral_type");
		String plat_sys = (String) dynaBean.get("plat_sys");
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
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 140317 || pu.getRole_id().intValue() == 1001) {
					zb = true;// 总部
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;// 分公司
				}
			}
		}

		EcGoodsIntegral entity = new EcGoodsIntegral();
		super.copyProperties(entity, form);
		if (StringUtils.isBlank(id)) {

			EcGoodsIntegral en = new EcGoodsIntegral();
			if (StringUtils.isNotBlank(dept_id)) {
				en.setDept_id(Long.valueOf(dept_id));
			}
			en.setPlat_sys(Integer.valueOf(plat_sys));
			en.setOwn_sys(2);
			en.setIntegral_type(Integer.valueOf(integral_type));
			en.setGoods_id(Long.valueOf(goods_id));
			Long count = super.getFacade().getEcGoodsIntegralService().getEcGoodsIntegralCount(en);
			if (count > 0) {
				super.renderJavaScript(response, "window.onload=function(){alert('该商品的积分已经存在！');history.back();}");
				return null;
			}

			if (GenericValidator.isLong(goods_id)) {
				entity.setGoods_id(Long.valueOf(goods_id));
				entity.setAdd_u_id(user.getId());
				entity.setAdd_date(new Date());
				entity.setOwn_sys(2);
				if (StringUtils.isNotBlank(start_date)) {
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					entity.setStart_date(sf.parse(start_date));
				}
				if (StringUtils.isNotBlank(end_date)) {
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					entity.setEnd_date(sf.parse(end_date));
				}

				super.getFacade().getEcGoodsIntegralService().createEcGoodsIntegral(entity);
				saveMessage(request, "entity.inserted");
			}
		} else {
			entity.setGoods_id(Long.valueOf(goods_id));
			entity.setOwn_sys(2);
			if (StringUtils.isNotBlank(start_date)) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				entity.setStart_date(sf.parse(start_date));
			}
			if (StringUtils.isNotBlank(end_date)) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				entity.setEnd_date(sf.parse(end_date));
			}

			super.getFacade().getEcGoodsIntegralService().modifyEcGoodsIntegral(entity);
			saveMessage(request, "entity.updated");
		}
		return new ActionForward("/../manager/spgl/EcGoodsIntegralNew.do?method=list2&goods_id=" + goods_id
		        + "&mod_id=" + mod_id);

	}

	public ActionForward edit2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String goods_id = (String) dynaBean.get("goods_id");

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
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 140317 || pu.getRole_id().intValue() == 1001) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}

		if (zb) {
			request.setAttribute("is_admin", "1");
			EcGroup eg = new EcGroup();
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("sybDeptInfoList", deptList);
		} else if (fgs) {
			request.setAttribute("is_fgs", "1");
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			EcGroup eg = new EcGroup();
			eg.setLink_dept_id(fgs_dept.getDept_id());
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("sybDeptInfoList", deptList);
			if (null != user.getDept_id()) {
				KonkaDept konkaDept = new KonkaDept();
				konkaDept.setDept_id(user.getDept_id());
				konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
				// 分公司取得
				if (null != konkaDept.getDept_type() && konkaDept.getDept_type() > 2) {
					dynaBean.set("fgs_id", fgs_dept.getDept_id());
				}

			}

		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('你没有权限查看价格！');history.back();}");
			return null;
		}

		KonkaBcompPd pd = new KonkaBcompPd();
		pd.setId(Long.valueOf(goods_id));
		pd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(pd);

		if (null != pd) {
			dynaBean.set("dept_sn", pd.getDept_sn());
			dynaBean.set("pd_name", pd.getPd_name());
			dynaBean.set("pd_sn", pd.getPd_sn());
			dynaBean.set("label_of_cate", pd.getLabel_of_cate());
		}
		if (!pd.getDept_sn().equals("0")) {
			KonkaDept kd = new KonkaDept();
			kd.setDept_id(Long.valueOf(pd.getDept_sn()));
			kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);

			dynaBean.set("dept_sn", pd.getDept_sn());
			// dynaBean.set("dept_name", kd.getDept_name());
		}

		if (GenericValidator.isLong(id)) {
			EcGoodsIntegral entity = new EcGoodsIntegral();
			entity.setId(Long.valueOf(id));
			entity = super.getFacade().getEcGoodsIntegralService().getEcGoodsIntegral(entity);
			super.copyProperties(form, entity);
			dynaBean.set("mod_id", mod_id);
			dynaBean.set("goods_id", goods_id);
		}

		return new ActionForward("/../manager/spgl/EcGoodsIntegralNew/add2.jsp");
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

		EcGoodsIntegral ec = new EcGoodsIntegral();
		ec.setId(Long.valueOf(id));
		super.getFacade().getEcGoodsIntegralService().removeEcGoodsIntegral(ec);

		saveMessage(request, "entity.deleted");

		return new ActionForward("/../manager/spgl/EcGoodsIntegralNew.do?method=list2&id=" + goods_id);
	}

}
