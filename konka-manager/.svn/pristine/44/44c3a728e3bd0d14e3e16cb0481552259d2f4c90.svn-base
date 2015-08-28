package com.ebiz.mmt.web.struts.manager.spgl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
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
import com.ebiz.mmt.domain.EcRule;
import com.ebiz.mmt.domain.EcRuleGoods;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Pan,Gang
 * @version 2013-09-24
 */
public class EcRuleAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String rule_title_like = (String) dynaBean.get("rule_title_like");
		String pd_sn_like = (String) dynaBean.get("pd_sn_like");
		String info_state = (String) dynaBean.get("info_state");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcRule entity = new EcRule();

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

		if (zb) {
			request.setAttribute("is_admin", "1");

		} else if (!zb && fgs) {
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			request.setAttribute("is_fgs", "1");
			entity.setDept_id(fgs_dept.getDept_id());
		}

		if (StringUtils.isNotBlank(rule_title_like)) {
			entity.getMap().put("rule_title_like", rule_title_like);

		}
		if (StringUtils.isNotBlank(pd_sn_like)) {
			entity.getMap().put("pd_sn_like", pd_sn_like);
		}
		if (StringUtils.isNotBlank(info_state)) {
			entity.setInfo_state(Integer.valueOf(info_state));
		}

		Long recordCount = super.getFacade().getEcRuleService().getEcRuleCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcRule> entityList = super.getFacade().getEcRuleService().getEcRulePaginatedList(entity);
		if (null != entityList && entityList.size() > 0) {
			for (EcRule ecRule : entityList) {
				String pd_sn = "";
				List<String> ruleList = new ArrayList<String>();
				EcRuleGoods eg = new EcRuleGoods();
				eg.setRule_id(ecRule.getId());
				List<EcRuleGoods> egList = super.getFacade().getEcRuleGoodsService().getEcRuleGoodsList(eg);
				if (null != egList && egList.size() > 0) {
					for (EcRuleGoods ecRuleGoods : egList) {
						KonkaBcompPd kb = new KonkaBcompPd();
						kb.setId(ecRuleGoods.getGoods_id());
						kb = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kb);
						if (null != kb && null != kb.getPd_sn()) {
							ruleList.add(kb.getPd_sn());
						}
					}
				}

				KonkaDept kd = new KonkaDept();
				kd.setDept_id(ecRule.getDept_id());
				kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
				if (kd != null) {
					ecRule.getMap().put("dept_name", kd.getDept_name());
				}

				// 去重
				if (null != ruleList && ruleList.size() > 0) {
					HashSet<String> result = new HashSet<String>();
					for (int i = 0; i < ruleList.size(); i++) {
						if (!result.add(ruleList.get(i))) {
						} else {
							result.add(ruleList.get(i));
							ruleList.add(ruleList.get(i));
						}

					}
					pd_sn = StringUtils.join(result.toArray(), ",");
					ecRule.getMap().put("pd_sn", pd_sn);
				}

			}
		}

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

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

		if (zb) {
			request.setAttribute("is_admin", "1");

		} else if (!zb && fgs) {
			request.setAttribute("is_fgs", "1");

		}

		dynaBean.set("is_num", "0");
		dynaBean.set("is_area_limit", "0");
		dynaBean.set("is_area_allow", "0");
		dynaBean.set("is_price", "0");
		dynaBean.set("info_state", "0");
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String[] select2 = (String[]) request.getParameterValues("select2");
		String[] select4 = (String[]) request.getParameterValues("select4");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcRule entity = new EcRule();
		super.copyProperties(entity, form);
		entity.setAdd_date(new Date());
		entity.setIs_del(0);

		String s2 = "";
		if (null != select2 && select2.length > 0) {
			// for (String string : select2) {
			// s2 = s2 + string + ",";
			// }
			s2 = StringUtils.join(select2, ",");
		}

		String s4 = "";
		if (null != select4 && select4.length > 0) {
			// for (String string : select4) {
			// s4 = s4 + string + ",";
			// }
			s4 = StringUtils.join(select4, ",");
		}

		logger.info("s2------------->" + s2);
		logger.info("s4------------->" + s4);

		if (StringUtils.isEmpty(id)) {
			entity.setRule_area_limit(s2);
			entity.setRule_area_allow(s4);
			entity.setAdd_user_id(user.getId());
			super.getFacade().getEcRuleService().createEcRule(entity);
			super.saveMessage(request, "entity.inserted");
		} else {
			entity.setRule_area_limit(s2);
			entity.setRule_area_allow(s4);
			super.getFacade().getEcRuleService().modifyEcRule(entity);
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

		EcRule entity = new EcRule();
		entity.setId(Long.valueOf(id));
		super.getFacade().getEcRuleService().removeEcRule(entity);

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

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

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

		if (zb) {
			request.setAttribute("is_admin", "1");

		} else if (!zb && fgs) {
			request.setAttribute("is_fgs", "1");

		}

		EcRule entity = new EcRule();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getEcRuleService().getEcRule(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));

		if (null != entity.getRule_area_limit() && !"".equals(entity.getRule_area_limit())) {
			List<BaseProvinceListFour> list1 = new ArrayList<BaseProvinceListFour>();
			String[] s1 = entity.getRule_area_limit().split(",");
			for (String string : s1) {
				BaseProvinceListFour blf = new BaseProvinceListFour();
				blf.setP_index(Long.valueOf(string));
				blf = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(blf);
				list1.add(blf);
			}
			request.setAttribute("list1", list1);
		}

		if (null != entity.getRule_area_allow() && !"".equals(entity.getRule_area_allow())) {
			List<BaseProvinceListFour> list2 = new ArrayList<BaseProvinceListFour>();
			String[] s1 = entity.getRule_area_allow().split(",");
			for (String string : s1) {
				BaseProvinceListFour blf = new BaseProvinceListFour();
				blf.setP_index(Long.valueOf(string));
				blf = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(blf);
				list2.add(blf);
			}
			request.setAttribute("list2", list2);
		}

		super.copyProperties(form, entity);

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		return mapping.findForward("input");
	}

	public ActionForward ajaxSelectPindexList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String shop_province = (String) dynaBean.get("shop_province");
		String shop_city = (String) dynaBean.get("shop_city");
		String shop_country = (String) dynaBean.get("shop_country");
		// String shop_town = (String) dynaBean.get("shop_town");
		// String dept_id = (String) dynaBean.get("dept_id");
		// String shop_name_like = (String) dynaBean.get("shop_name_like");

		logger.info("省--------------》" + shop_province);
		logger.info("市--------------》" + shop_city);
		logger.info("县--------------》" + shop_country);

		BaseProvinceListFour entpShop = new BaseProvinceListFour();
		entpShop.setDel_mark(0);
		if (StringUtils.isNotBlank(shop_country)) {
			entpShop.setPar_index(Long.valueOf(shop_country));
		} else if (StringUtils.isNotBlank(shop_city) && StringUtils.isBlank(shop_country)) {
			entpShop.setPar_index(Long.valueOf(shop_city));
		} else if (StringUtils.isNotBlank(shop_province) && StringUtils.isBlank(shop_city)
		        && StringUtils.isBlank(shop_country)) {
			entpShop.setPar_index(Long.valueOf(shop_province));
		} else if (StringUtils.isBlank(shop_country) && StringUtils.isBlank(shop_city)
		        && StringUtils.isBlank(shop_province)) {
			entpShop.setPar_index(0L);
		}

		Long count = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFourCount(entpShop);
		// 查询结果过大
		if (500L <= count) {
			super.renderJson(response, "{\"status\":\"0\"}");
			return null;
		}

		// 查询结果为空
		List<BaseProvinceListFour> entpShopList = super.getFacade().getBaseProvinceListFourService()
		        .getBaseProvinceListFourList(entpShop);
		if (0 == entpShopList.size()) {
			super.renderJson(response, "{\"status\":\"-1\"}");
			return null;
		}

		// 遍历结果
		StringBuffer sb = new StringBuffer("{\"status\":\"1\",\"data\":[");
		for (BaseProvinceListFour es : entpShopList) {
			sb.append("{\"p_index\":\"").append(es.getP_index()).append("\", \"full_name\":\"").append(
			        es.getFull_name()).append("\"},");
		}
		sb.delete(sb.length() - 1, sb.length());
		sb.append("]}");
		logger.info("------json---->{}", sb.toString());
		super.renderJson(response, sb.toString());
		return null;
	}

	public ActionForward ajaxSelectPindexList2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String shop_province1 = (String) dynaBean.get("shop_province1");
		String shop_city1 = (String) dynaBean.get("shop_city1");
		String shop_country1 = (String) dynaBean.get("shop_country1");
		// String shop_town = (String) dynaBean.get("shop_town");
		// String dept_id = (String) dynaBean.get("dept_id");
		// String shop_name_like = (String) dynaBean.get("shop_name_like");

		logger.info("省--------------》" + shop_province1);
		logger.info("市--------------》" + shop_city1);
		logger.info("县--------------》" + shop_country1);

		BaseProvinceListFour entpShop = new BaseProvinceListFour();
		entpShop.setDel_mark(0);

		if (StringUtils.isNotBlank(shop_country1)) {
			entpShop.setPar_index(Long.valueOf(shop_country1));
		} else if (StringUtils.isNotBlank(shop_city1) && StringUtils.isBlank(shop_country1)) {
			entpShop.setPar_index(Long.valueOf(shop_city1));
		} else if (StringUtils.isNotBlank(shop_province1) && StringUtils.isBlank(shop_city1)
		        && StringUtils.isBlank(shop_country1)) {
			entpShop.setPar_index(Long.valueOf(shop_province1));
		} else if (StringUtils.isBlank(shop_country1) && StringUtils.isBlank(shop_city1)
		        && StringUtils.isBlank(shop_province1)) {
			entpShop.setPar_index(0L);
		}

		Long count = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFourCount(entpShop);
		// 查询结果过大
		if (500L <= count) {
			super.renderJson(response, "{\"status\":\"0\"}");
			return null;
		}

		// 查询结果为空
		List<BaseProvinceListFour> entpShopList = super.getFacade().getBaseProvinceListFourService()
		        .getBaseProvinceListFourList(entpShop);
		if (0 == entpShopList.size()) {
			super.renderJson(response, "{\"status\":\"-1\"}");
			return null;
		}

		// 遍历结果
		StringBuffer sb = new StringBuffer("{\"status\":\"1\",\"data\":[");
		for (BaseProvinceListFour es : entpShopList) {
			sb.append("{\"p_index\":\"").append(es.getP_index()).append("\", \"full_name\":\"").append(
			        es.getFull_name()).append("\"},");
		}
		sb.delete(sb.length() - 1, sb.length());
		sb.append("]}");
		logger.info("------json---->{}", sb.toString());
		super.renderJson(response, sb.toString());
		return null;
	}
}
