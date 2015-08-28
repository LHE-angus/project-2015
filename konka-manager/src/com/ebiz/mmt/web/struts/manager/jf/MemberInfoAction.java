package com.ebiz.mmt.web.struts.manager.jf;

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
import com.ebiz.mmt.domain.JfScortsDetails;
import com.ebiz.mmt.domain.JfScortsExchange;
import com.ebiz.mmt.domain.MemberChangeCardInfo;
import com.ebiz.mmt.domain.MemberInfo;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author pangang
 * @version 2013-07-26
 */
public class MemberInfoAction extends BaseJfAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String user_name_like = (String) dynaBean.get("user_name_like");
		String mp_like = (String) dynaBean.get("mp_like");
		String user_sn_like = (String) dynaBean.get("user_sn_like");
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		MemberInfo entity = new MemberInfo();
		entity.setUser_state(0);// 默认只显示0

		if (StringUtils.isNotBlank(user_name_like)) {
			entity.getMap().put("user_name_like", user_name_like);
		}
		if (StringUtils.isNotBlank(mp_like)) {
			entity.getMap().put("mp_like", mp_like);
		}
		if (StringUtils.isNotBlank(user_sn_like)) {
			entity.getMap().put("user_sn_like", user_sn_like);
		}
		if (StringUtils.isNotBlank(town)) {
			entity.getMap().put("p_index_like", town);
		} else if (StringUtils.isNotBlank(country) && StringUtils.isBlank(town)) {
			entity.getMap().put("p_index_like", StringUtils.substring(country, 0, 6));
		} else if (StringUtils.isNotBlank(city) && StringUtils.isBlank(country) && StringUtils.isBlank(town)) {
			entity.getMap().put("p_index_like", StringUtils.substring(city, 0, 4));
		} else if (StringUtils.isNotBlank(province) && StringUtils.isBlank(city) && StringUtils.isBlank(country)
		        && StringUtils.isBlank(town)) {
			entity.getMap().put("p_index_like", StringUtils.substring(province, 0, 2));
		}

		Long recordCount = super.getFacade().getMemberInfoService().getMemberInfoCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<MemberInfo> entityList = super.getFacade().getMemberInfoService()
		        .getMemberInfoForTotalScortsPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		return mapping.findForward("input");
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

		MemberInfo entity = new MemberInfo();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getMemberInfoService().getMemberInfo(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);

		// 地区回显
		if (entity.getP_index() != null && entity.getP_index() != -1) {
			String p_index = entity.getP_index().toString();
			if (StringUtils.isNotBlank(p_index)) {
				if (!p_index.endsWith("00")) {
					if (p_index.length() == 6) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "00");
						request.setAttribute("country", p_index);
					} else if (p_index.length() == 8) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "00");
						request.setAttribute("country", StringUtils.substring(p_index, 0, 6));
						request.setAttribute("town", p_index);
					}
				} else if (p_index.endsWith("0000")) {
					if (p_index.length() == 6) {
						request.setAttribute("province", p_index);
					} else if (p_index.length() == 8) {
						request.setAttribute("province", p_index);
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "00");
					}
				} else if (p_index.endsWith("00")) {
					if (p_index.length() == 6) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
						request.setAttribute("city", p_index);
					} else if (p_index.length() == 8) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "00");
						request.setAttribute("country", StringUtils.substring(p_index, 0, 6));
					}
				}
			}
		}

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");

		MemberInfo entity = new MemberInfo();
		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(town)) {
			entity.setP_index(Long.valueOf(town));
		} else if (StringUtils.isNotBlank(country) && StringUtils.isBlank(town)) {
			entity.setP_index(Long.valueOf(country));
		} else if (StringUtils.isNotBlank(city) && StringUtils.isBlank(country) && StringUtils.isBlank(town)) {
			entity.setP_index(Long.valueOf(city));
		} else if (StringUtils.isNotBlank(province) && StringUtils.isBlank(city) && StringUtils.isBlank(country)
		        && StringUtils.isBlank(town)) {
			entity.setP_index(Long.valueOf(province));

		}
		if (StringUtils.isEmpty(id)) {
			super.getFacade().getMemberInfoService().createMemberInfo(entity);
			super.saveMessage(request, "entity.inserted");
		} else {
			super.getFacade().getMemberInfoService().modifyMemberInfo(entity);
			super.saveMessage(request, "entity.updated");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		// logger.info("pathBuffer++++++++++++++++++++++" + pathBuffer);
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

		MemberInfo entity = new MemberInfo();
		entity.setId(Long.valueOf(id));

		super.getFacade().getMemberInfoService().removeMemberInfo(entity);
		saveMessage(request, "konka.close.success");

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

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		MemberInfo entity = new MemberInfo();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getMemberInfoService().getMemberInfo(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);

		// 地区全称显示
		if (null != entity.getP_index()) {
			BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
			baseProvinceListFour.setP_index(entity.getP_index());
			baseProvinceListFour = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
			        baseProvinceListFour);

			String p_index_name = baseProvinceListFour.getFull_name();

			request.setAttribute("p_index_name", p_index_name);
		}

		return mapping.findForward("view");
	}

	public ActionForward memberCardStop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String stop_reason = (String) dynaBean.get("stop_reason");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		MemberInfo entity = new MemberInfo();
		entity.setId(Long.valueOf(id));
		entity.setUser_state(1);
		entity.setStop_reason(Integer.valueOf(stop_reason));
		entity.setStop_date(new Date());
		super.getFacade().getMemberInfoService().modifyMemberInfo(entity);
		saveMessage(request, "konka.close.success");

		return new ActionForward("/jf/MemberInfo.do?method=list&mod_id=" + mod_id, true);
	}

	public ActionForward memberCardChange(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		MemberInfo entity = new MemberInfo();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getMemberInfoService().getMemberInfo(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);

		// 地区回显
		if (entity.getP_index() != null && entity.getP_index() != -1) {
			String p_index = entity.getP_index().toString();
			if (StringUtils.isNotBlank(p_index)) {
				if (!p_index.endsWith("00")) {
					if (p_index.length() == 6) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "00");
						request.setAttribute("country", p_index);
					} else if (p_index.length() == 8) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "00");
						request.setAttribute("country", StringUtils.substring(p_index, 0, 6));
						request.setAttribute("town", p_index);
					}
				} else if (p_index.endsWith("0000")) {
					if (p_index.length() == 6) {
						request.setAttribute("province", p_index);
					} else if (p_index.length() == 8) {
						request.setAttribute("province", p_index);
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "00");
					}
				} else if (p_index.endsWith("00")) {
					if (p_index.length() == 6) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
						request.setAttribute("city", p_index);
					} else if (p_index.length() == 8) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "000000");
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "0000");
						request.setAttribute("country", StringUtils.substring(p_index, 0, 6) + "00");
					}
				}
			}
		}

		return new ActionForward("/jf/MemberInfo/memberCardChang.jsp");
	}

	public ActionForward memberCardSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String now_user_sn = (String) dynaBean.get("now_user_sn");
		super.getFacade().getMemberInfoService().createMemberCardChang(id, now_user_sn);

		return new ActionForward("/jf/MemberInfo.do?method=list&mod_id=" + mod_id, true);
	}

	public ActionForward look(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		MemberInfo entity = new MemberInfo();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}
		entity = super.getFacade().getMemberInfoService().getMemberInfo(entity);
		super.copyProperties(form, entity);

		if (null != entity.getP_index()) {
			BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
			baseProvinceListFour.setP_index(entity.getP_index());
			baseProvinceListFour = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
			        baseProvinceListFour);

			String p_index_name = baseProvinceListFour.getFull_name();

			request.setAttribute("p_index_name", p_index_name);
		}

		MemberInfo mi = new MemberInfo();
		if (StringUtils.isNotBlank(id)) {
			mi.setId(Long.valueOf(id));
		}
		mi = super.getFacade().getMemberInfoService().getMemberInfo(mi);

		MemberChangeCardInfo mci = new MemberChangeCardInfo();
		mci.setNow_user_sn(mi.getUser_sn());
		List<MemberChangeCardInfo> entityList = super.getFacade().getMemberChangeCardInfoService()
		        .getMemberChangeCardInfoList(mci);

		request.setAttribute("entityList", entityList);

		// 会员积分明细
		JfScortsDetails details = new JfScortsDetails();
		details.setStatus(1);// 1表示已经审核
		details.getMap().put("add_date_asc", true);

		// 如果已经换过会员卡，显示原会员卡记录
		if (null != entityList && entityList.size() > 0) {
			details.getMap().put("isChangCard", true);
			details.getMap().put("user_sn", entity.getUser_sn());
			List<JfScortsDetails> detailsList = super.getFacade().getJfScortsDetailsService()
			        .getJfScortsDetailsAndDeptNameForMemberCardList(details);
			request.setAttribute("detailsList", detailsList);
		} else {
			details.setUser_sn(entity.getUser_sn());
			List<JfScortsDetails> detailsList = super.getFacade().getJfScortsDetailsService()
			        .getJfScortsDetailsAndDeptNameList(details);
			request.setAttribute("detailsList", detailsList);
		}

		// 会员礼品兑换
		JfScortsExchange exchange = new JfScortsExchange();
		exchange.getMap().put("add_date_asc", true);

		if (null != entityList && entityList.size() > 0) {
			exchange.getMap().put("isChangCard", true);
			exchange.getMap().put("user_sn", entity.getUser_sn());
			List<JfScortsExchange> exchangeList = super.getFacade().getJfScortsExchangeService()
			        .getJfScortsExchangeAndGiftNameForMemberCardList(exchange);
			request.setAttribute("exchangeList", exchangeList);
		} else {
			exchange.setUser_sn(entity.getUser_sn());
			List<JfScortsExchange> exchangeList = super.getFacade().getJfScortsExchangeService()
			        .getJfScortsExchangeAndGiftNameList(exchange);
			request.setAttribute("exchangeList", exchangeList);
		}

		return new ActionForward("/jf/MemberInfo/look.jsp");
	}

	public ActionForward validateUserSN(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String user_sn = (String) dynaBean.get("user_sn");
		MemberInfo entity = new MemberInfo();
		String isExist = "null";

		if (StringUtils.isNotBlank(user_sn)) {
			entity.setUser_sn(user_sn);
			if (super.getFacade().getMemberInfoService().getMemberInfoCount(entity) > 0) {
				isExist = String.valueOf("1");
			} else {
				isExist = String.valueOf("0");
			}
		}
		super.render(response, isExist, "text/x-json;charset=UTF-8");
		return null;

	}

}
