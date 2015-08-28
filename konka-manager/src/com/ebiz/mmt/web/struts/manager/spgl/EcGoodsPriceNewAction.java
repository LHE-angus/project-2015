package com.ebiz.mmt.web.struts.manager.spgl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.ebiz.mmt.domain.EcCust;
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
public class EcGoodsPriceNewAction extends BaseAction {

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
		String pd_name = (String) dynaBean.get("pd_name");

		dynaBean.set("pd_name", pd_name);// 添加的时候 回显产品名称

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
				dynaBean.set("lock_state", kpd.getLock_state());
				dynaBean.set("pd_sn", kpd.getPd_sn());
			} else {
				// 分公司添加商品
				dynaBean.set("pd_dept", super.getSuperiorForDeptType(user.getDept_id(), 3));
			}

		}

		EcGoodsPrice entity = new EcGoodsPrice();
		if (StringUtils.isNotBlank(goods_id)) {
			entity.setGoods_id(Long.valueOf(goods_id));
		}

		if (zb) {
			request.setAttribute("is_admin", "1");
		} else if (!zb && fgs) {
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			dynaBean.set("fgs_id", fgs_dept.getDept_id());
			dynaBean.set("fgs_name", fgs_dept.getDept_name());

			request.setAttribute("is_fgs", "1");
			EcGroup eg = new EcGroup();
			eg.setLink_dept_id(fgs_dept.getDept_id());
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			if (deptList != null && deptList.size() > 0) {
				List<String> ids = new ArrayList<String>();
				List<String> cidList = new ArrayList<String>();
				for (EcGroup ecGroup : deptList) {
					ids.add(ecGroup.getId().toString());
					EcCust ec = new EcCust();
					ec.setDel_mark(0);
					ec.setGroup_id(ecGroup.getId());
					List<EcCust> entityList1 = super.getFacade().getEcCustService().getEcCustList(ec);
					for (EcCust ecCust : entityList1) {
						cidList.add(ecCust.getId().toString());
					}
				}

				dynaBean.set("group_id", StringUtils.join(ids, ","));
				dynaBean.set("is_binding", true);
				entity.getMap().put("is_fgs", ids);

				// 显示r3客户价格
				entity.getMap().put("c_ids", cidList);
			} else {
				entity.getMap().put("is_fgs", new String[] { "999999" });
			}

			// KonkaDept kd = new KonkaDept();
			// kd.setDept_id(super.getSuperiorForDeptType(user.getDept_id(),
			// 3).getDept_id());
			// kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
			// String[] ids = StringUtils.split(kd.getM_areas_ids(), ",");
			// List<String> pindex_ids = new ArrayList<String>();
			// if (null == ids || ids.length == 0) {
			// super.renderJavaScript(response,
			// "window.onload=function(){alert('请去维护该分司的管辖区域！');history.back();}");
			// return null;
			// } else {
			// for (String ss : ids) {
			// pindex_ids.add(ss.substring(0, 4));
			// }
			// entity.getMap().put("pindex_ids", pindex_ids);
			// }

			// 显示r3客户价格
			// KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
			// konkaR3Shop.setBranch_area_name_2(kd.getDept_sn());
			// konkaR3Shop.setIs_del(0L);
			// List<KonkaR3Shop> entityList1 =
			// super.getFacade().getKonkaR3ShopService().getKonkaR3ShopList(konkaR3Shop);
			// List<String> cidList = new ArrayList<String>();
			// for (KonkaR3Shop konkaR3Shop2 : entityList1) {
			// cidList.add(konkaR3Shop2.getId().toString());
			// }
			// entity.getMap().put("c_ids", cidList);

		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('你没有权限查看价格！');history.back();}");
			return null;
		}

		Long recordCount = super.getFacade().getEcGoodsPriceService().getEcGoodsPriceForDeptNameAndPdNameNewCount(
		        entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcGoodsPrice> entityList = super.getFacade().getEcGoodsPriceService()
		        .getEcGoodsPriceForDeptNameAndPdNameNewList(entity);
		// 查询分公司管辖区域
		for (EcGoodsPrice ecGoodsPrice : entityList) {
			// EcGoodsPriceArea eca = new EcGoodsPriceArea();
			// eca.setPrice_id(ecGoodsPrice.getId());
			// List<EcGoodsPriceArea> areas =
			// super.getFacade().getEcGoodsPriceAreaService().getEcGoodsPriceAreaList(eca);
			// List<String> pNameList = new ArrayList<String>();
			// for (EcGoodsPriceArea ecGoodsPriceArea : areas) {
			// pNameList.add(ecGoodsPriceArea.getP_name());
			// }
			// String areaList = StringUtils.join(pNameList.toArray(), ',');
			// ecGoodsPrice.getMap().put("areaList", areaList);

			EcGoodsPriceArea eca = new EcGoodsPriceArea();
			eca.setPrice_id(ecGoodsPrice.getId());
			List<EcGoodsPriceArea> ecaList = super.getFacade().getEcGoodsPriceAreaService()
			        .getEcGoodsPriceAreaList(eca);
			if (ecaList != null && ecaList.size() > 0) {
				eca = ecaList.get(0);
				ecGoodsPrice.getMap().put("area_name", eca.getP_name());
			}

			if (null != ecGoodsPrice.getC_id()) {
				EcCust kk = new EcCust();
				kk.setId(ecGoodsPrice.getC_id());
				kk = super.getFacade().getEcCustService().getEcCust(kk);
				if (kk != null) {
					ecGoodsPrice.getMap().put("customer_name", kk.getCust_code());
				}
			}
		}

		request.setAttribute("entityList", entityList);
		dynaBean.set("goods_id", goods_id);

		return new ActionForward("/../manager/spgl/EcGoodsPriceNew/list2.jsp");
	}

	public ActionForward add2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String goods_id_1 = (String) dynaBean.get("goods_id_1");
		String pd_name = (String) dynaBean.get("pd_name");

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
			dynaBean.set("price_type", "0");

			EcGroup eg = new EcGroup();
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("groupList", deptList);

			EcGroup eg2 = new EcGroup();
			// eg2.getMap().put("id_not_eq", true);
			List<EcGroup> deptList2 = super.getFacade().getEcGroupService().getEcGroupList(eg2);
			request.setAttribute("groupList2", deptList2);

		} else if (!zb && fgs) {
			if (null != user.getDept_id()) {
				KonkaDept konkaDept = new KonkaDept();
				konkaDept.setDept_id(user.getDept_id());
				konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);

				// 分公司取得
				if (null != konkaDept.getDept_type() && konkaDept.getDept_type() > 2) {
					KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);
					dynaBean.set("fgs_id", fgs_dept.getDept_id());

					request.setAttribute("is_fgs", "1");
					dynaBean.set("price_type", "2");
					EcGroup eg = new EcGroup();
					eg.setLink_dept_id(fgs_dept.getDept_id());
					List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);

					request.setAttribute("groupList", deptList);

					EcGroup eg2 = new EcGroup();
					// eg2.getMap().put("id_not_eq", true);
					eg2.setLink_dept_id(fgs_dept.getDept_id());
					List<EcGroup> deptList2 = super.getFacade().getEcGroupService().getEcGroupList(eg2);
					request.setAttribute("groupList2", deptList2);
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
			dynaBean.set("pd_name", pd.getPd_name());
			dynaBean.set("label_of_cate", pd.getLabel_of_cate());
		} else {
			dynaBean.set("pd_name", pd_name);
		}
		if (!pd.getDept_sn().equals("0")) {
			KonkaDept kd = new KonkaDept();
			kd.setDept_id(Long.valueOf(pd.getDept_sn()));
			kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);

			dynaBean.set("dept_sn", pd.getDept_sn());
			// dynaBean.set("dept_name", kd.getDept_name());
		}

		dynaBean.set("goods_id", goods_id_1);

		return new ActionForward("/../manager/spgl/EcGoodsPriceNew/add2.jsp");
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
		// String town = (String) dynaBean.get("town");
		String goods_id = (String) dynaBean.get("goods_id");
		String remarks = (String) dynaBean.get("remarks");
		String own_sys = (String) dynaBean.get("own_sys");
		String c_id = (String) dynaBean.get("c_id");
		String dept_id_2 = (String) dynaBean.get("dept_id_2");

		String start_time = (String) dynaBean.get("start_time");
		String end_time = (String) dynaBean.get("end_time");
		String plat_sys = (String) dynaBean.get("plat_sys");
		String dept_id_3 = (String) dynaBean.get("dept_id_3");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaBcompPd kp = new KonkaBcompPd();
		kp.setId(Long.valueOf(goods_id));
		kp = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kp);
		if (kp == null) {
			super.renderJavaScript(response, "window.onload=function(){alert('商品不存在！');history.back();}");
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

		// 验证同一区域价格的唯一性
		EcGoodsPrice entity = new EcGoodsPrice();
		entity.setOwn_sys(Integer.valueOf(own_sys));
		entity.setPlat_sys(Integer.valueOf(plat_sys));
		if (price_type.equals("0")) {
			entity.setGoods_id(Long.valueOf(goods_id));
			if (StringUtils.isNotBlank(dept_id_2)) {
				entity.setDept_id(Long.valueOf(dept_id_2));
			} else {
				entity.getMap().put("dept_id_is_null", true);
			}
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

			if (StringUtils.isNotBlank(country)) {
				entity.getMap().put("pindex_id", country);
			} else if (StringUtils.isNotBlank(city) && StringUtils.isBlank(country)) {
				entity.getMap().put("pindex_id", city);
			} else if (StringUtils.isNotBlank(province) && StringUtils.isBlank(city)) {
				entity.getMap().put("pindex_id", province);
			}
			if (StringUtils.isNotBlank(dept_id_3)) {
				entity.setDept_id(Long.valueOf(dept_id_3));
			}

			Long count = super.getFacade().getEcGoodsPriceService().getEcGoodsPriceAndAreaCount(entity);
			if (count > 0) {
				String msg = super.getMessage(request, "konka.price.error");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		} else if (price_type.equals("4")) {
			entity.setGoods_id(Long.valueOf(goods_id));
			entity.setPrice_type(4);
			entity.setC_id(Long.valueOf(c_id));
			Long count = super.getFacade().getEcGoodsPriceService().getEcGoodsPriceCount(entity);
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
		if (StringUtils.isNotBlank(plat_sys)) {
			ec.setPlat_sys(Integer.valueOf(plat_sys));
		}
		if (StringUtils.isNotBlank(start_time)) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ec.setStart_time(sf.parse(start_time));
		}
		if (StringUtils.isNotBlank(end_time)) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ec.setEnd_time(sf.parse(end_time));
		}

		if (StringUtils.isEmpty(id)) {
			if (price_type.equals("2")) {// 分公司价格
				if (StringUtils.isNotBlank(dept_id)) {
					// EcGroup ecGroup = new EcGroup();
					// ecGroup.setId(Long.valueOf(dept_id));
					// ecGroup =
					// super.getFacade().getEcGroupService().getEcGroup(ecGroup);
					// if (ecGroup != null && ecGroup.getLink_dept_id() != null)
					// {
					// KonkaDept dept = new KonkaDept();
					// dept.setDept_id(ecGroup.getLink_dept_id());
					// dept =
					// super.getFacade().getKonkaDeptService().getKonkaDept(dept);
					// List<EcGoodsPriceArea> ecGoodsPriceAreaList = new
					// ArrayList<EcGoodsPriceArea>();
					// // 查询分公司管辖区域
					// BaseProvinceListFour bpl = new BaseProvinceListFour();
					// if (StringUtils.isBlank(dept.getM_areas_ids())) {
					// String msg = super.getMessage(request,
					// "ec.dept.area.error");
					// super.renderJavaScript(response,
					// "window.onload=function(){alert('" + msg
					// + "');history.back();}");
					// return null;
					// }
					// bpl.getMap().put("p_index_in",
					// dept.getM_areas_ids().split(","));
					// List<BaseProvinceListFour> bplList =
					// super.getFacade().getBaseProvinceListFourService()
					// .getBaseProvinceListFourList(bpl);
					// for (BaseProvinceListFour t1 : bplList) {
					// EcGoodsPriceArea t = new EcGoodsPriceArea();
					// t.setPindex_id(t1.getP_index());
					// t.setP_name(t1.getP_name());
					// ecGoodsPriceAreaList.add(t);
					// }
					// ec.setEcGoodsPriceAreaList(ecGoodsPriceAreaList);
					// }

					ec.setRemarks(remarks);
					super.getFacade().getEcGoodsPriceService().createEcGoodsPriceWithPindex(ec);

				}
				super.saveMessage(request, "entity.inserted");
			} else if (price_type.equals("3")) {// 县市价格
				// // 根据省市县 反查属于的分公司
				// if (zb) {
				// if (!p_index.equals("")) {
				// Boolean i_p = false;
				// p_index = p_index.substring(0, 3);
				// KonkaDept kd = new KonkaDept();
				// kd.setDept_type(3);
				// List<KonkaDept> kdList =
				// super.getFacade().getKonkaDeptService().getKonkaDeptList(kd);
				// for (KonkaDept konkaDept : kdList) {
				// if (konkaDept.getP_index() != null) {
				// String pp = konkaDept.getP_index().toString().substring(0,
				// 3);
				// if (pp.equals(p_index)) {
				// i_p = true;
				// p_index = konkaDept.getDept_id().toString();
				// break;
				// }
				// }
				// }
				// if (i_p) {
				// EcGroup ee = new EcGroup();
				// ee.setLink_dept_id(Long.valueOf(p_index));
				// ee = super.getFacade().getEcGroupService().getEcGroup(ee);
				// if (ee != null) {
				// ec.setDept_id(ee.getId());
				// }
				//
				// } else {
				// String msg = super.getMessage(request, "ec.dept.area.error");
				// super.renderJavaScript(response,
				// "window.onload=function(){alert('" + msg
				// + "');history.back();}");
				// return null;
				// }
				// }
				// } else if (fgs) {// 分公司触网添加县市价格，存分公司id-------------------
				// EcGroup ee = new EcGroup();
				// ee.setLink_dept_id(super.getSuperiorForDeptType(user.getDept_id(),
				// 3).getDept_id());
				// ee = super.getFacade().getEcGroupService().getEcGroup(ee);
				// if (ee != null) {
				// ec.setDept_id(ee.getId());
				// }
				// }
				if (StringUtils.isNotBlank(dept_id_3)) {
					ec.setDept_id(Long.valueOf(dept_id_3));
				}
				super.getFacade().getEcGoodsPriceService().createEcGoodsPriceAndArea(ec, province, city, country);
			} else if (price_type.equals("0")) {// 全国价格
				if (StringUtils.isNotBlank(dept_id_2)) {
					ec.setDept_id(Long.valueOf(dept_id_2));
				}
				super.getFacade().getEcGoodsPriceService().createEcGoodsPrice(ec);
			} else if (price_type.equals("4")) {// R3客户价格
				if (StringUtils.isNotBlank(c_id)) {
					ec.setC_id(Long.valueOf(c_id));
				}
				super.getFacade().getEcGoodsPriceService().createEcGoodsPrice(ec);
			}
		} else {
			super.getFacade().getEcGoodsPriceService().modifyEcGoodsPrice(ec);
			super.saveMessage(request, "entity.updated");
		}
		return new ActionForward("/../manager/spgl/EcGoodsPriceNew.do?method=list2&id=" + goods_id);

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

		return new ActionForward("/../manager/spgl/EcGoodsPriceNew.do?method=list2&id=" + goods_id);
	}

	public ActionForward listCustomer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		String fgs_id = (String) dynaBean.get("fgs_id");
		String cust_name_like = (String) dynaBean.get("cust_name_like");
		String cust_code_like = (String) dynaBean.get("cust_code_like");

		if (!GenericValidator.isLong(fgs_id)) {
			return null;
		}

		EcCust ec = new EcCust();
		ec.setDel_mark(0);
		if (StringUtils.isNotBlank(cust_name_like)) {
			ec.getMap().put("cust_name_like", cust_name_like);
		}
		if (StringUtils.isNotBlank(cust_code_like)) {
			ec.getMap().put("cust_code_like", cust_code_like);
		}
		ec.setGroup_id(Long.valueOf(fgs_id));

		Long recordCount = super.getFacade().getEcCustService().getEcCustCount(ec);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		ec.getRow().setFirst(pager.getFirstRow());
		ec.getRow().setCount(pager.getRowCount());

		List<EcCust> entityList = super.getFacade().getEcCustService().getEcCustPaginatedList(ec);
		request.setAttribute("entityList", entityList);

		dynaBean.set("fgs_id", fgs_id);

		return new ActionForward("/../manager/spgl/EcGoodsPriceNew/list-customer.jsp");
	}

}
