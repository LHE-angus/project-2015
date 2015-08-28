package com.ebiz.mmt.web.struts.manager.spgl;

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

import com.ebiz.mmt.domain.EcGiftOrde;
import com.ebiz.mmt.domain.EcUserScoreRec;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Liu,ZhiXiang
 * @version 2013-09-17
 */
public class EcGiftOrdeEditAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	// 修改订单
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String trade_index_like = (String) dynaBean.get("trade_index_like");
		String order_user_name_like = (String) dynaBean.get("order_user_name_like");
		String state = (String) dynaBean.get("state");
		String buyer_name_like = (String) dynaBean.get("buyer_name_like");
		String buyer_mp_like = (String) dynaBean.get("buyer_mp_like");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(user.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		boolean role_id_eq_30 = false;
		boolean role_id_gt_30_lt_60 = false;

		for (PeRoleUser peRoleUser : peRoleUserList) {
			// 总部管理员
			if (peRoleUser.getRole_id() < 30L || peRoleUser.getRole_id().intValue()==179 || peRoleUser.getRole_id().intValue()==178 || peRoleUser.getRole_id().intValue()==140317 || peRoleUser.getRole_id().intValue()==1001 ) {
				role_id_eq_30 = true;
			}
			// 分公司管理员
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() < 60L) {
				role_id_gt_30_lt_60 = true;
			}
		}
		EcGiftOrde entity = new EcGiftOrde();
		// 总部可以查看所有订单
		// 分公司只能看到分公司的订单
		if (role_id_gt_30_lt_60) {
			entity.setOpr_dept_id(user.getDept_id());
		}
		// 总部和分公司之外的不能查看
		if (!role_id_gt_30_lt_60 && !role_id_eq_30) {
			String msg = super.getMessage(request, "konka.r3.roleError");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		if (StringUtils.isNotBlank(trade_index_like)) {
			entity.getMap().put("trade_index_like", trade_index_like);
		}
		if (StringUtils.isNotBlank(order_user_name_like)) {
			entity.getMap().put("order_user_name_like", order_user_name_like);
		}
		if (StringUtils.isNotBlank(state)) {
			entity.setState(Integer.valueOf(state));
		}
		if (StringUtils.isNotBlank(buyer_name_like)) {
			entity.getMap().put("buyer_name_like", buyer_name_like);
		}
		if (StringUtils.isNotBlank(buyer_mp_like)) {
			entity.getMap().put("buyer_mp_like", buyer_mp_like);
		}
		entity.getMap().put("edit_state", true);

		Long recordCount = super.getFacade().getEcGiftOrdeService()
				.getEcGiftOrdeForDeptNameAndFullNameListCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<EcGiftOrde> entityList = super.getFacade().getEcGiftOrdeService()
				.getEcGiftOrdeForDeptNameAndFullNamePaginatedList(entity);

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
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

		EcGiftOrde entity = new EcGiftOrde();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}
		entity = super.getFacade().getEcGiftOrdeService().getEcGiftOrde(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		// 地区回显
		if (entity.getBuyer_p_index() != null && entity.getBuyer_p_index() != -1) {
			String p_index = entity.getBuyer_p_index().toString();
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

		return new ActionForward("/../manager/spgl/EcGiftOrdeEdit/edit.jsp");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String buyer_name = (String) dynaBean.get("buyer_name");
		String buyer_tel = (String) dynaBean.get("buyer_tel");
		String buyer_mp = (String) dynaBean.get("buyer_mp");
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");
		String deliver_way = (String) dynaBean.get("deliver_way");
		String deliver_is_call = (String) dynaBean.get("deliver_is_call");
		String deliver_time = (String) dynaBean.get("deliver_time");
		String state = (String) dynaBean.get("state");
		String buyer_addr = (String) dynaBean.get("buyer_addr");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcGiftOrde entity = new EcGiftOrde();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}
		if (StringUtils.isNotBlank(buyer_name)) {
			entity.setBuyer_name(buyer_name);
		}
		if (StringUtils.isNotBlank(buyer_tel)) {
			entity.setBuyer_tel(buyer_tel);
		}
		if (StringUtils.isNotBlank(buyer_mp)) {
			entity.setBuyer_mp(buyer_mp);
		}
		if (StringUtils.isNotBlank(deliver_way)) {
			entity.setDeliver_way(Integer.valueOf(deliver_way));
		}
		if (StringUtils.isNotBlank(deliver_is_call)) {
			entity.setDeliver_is_call(Integer.valueOf(deliver_is_call));
		}
		if (StringUtils.isNotBlank(deliver_time)) {
			entity.setDeliver_time(Integer.valueOf(deliver_time));
		}
		if (StringUtils.isNotBlank(state)) {
			entity.setState(Integer.valueOf(state));
		}
		if (StringUtils.isNotBlank(buyer_addr)) {
			entity.setBuyer_addr(buyer_addr);
		}

		if (StringUtils.isNotBlank(town)) {
			entity.setBuyer_p_index(Long.valueOf(town));
		} else if (StringUtils.isNotBlank(country) && StringUtils.isBlank(town)) {
			entity.setBuyer_p_index(Long.valueOf(country));
		} else if (StringUtils.isNotBlank(city) && StringUtils.isBlank(country) && StringUtils.isBlank(town)) {
			entity.setBuyer_p_index(Long.valueOf(city));
		} else if (StringUtils.isNotBlank(province) && StringUtils.isBlank(city) && StringUtils.isBlank(country)
				&& StringUtils.isBlank(town)) {
			entity.setBuyer_p_index(Long.valueOf(province));
		}

		if (StringUtils.isNotBlank(id) && StringUtils.isNotBlank(state)) {
			// 用户积分记录操作表
			EcGiftOrde t = new EcGiftOrde();
			t.setId(new Long(id));
			t = super.getFacade().getEcGiftOrdeService().getEcGiftOrde(t);

			if (null != t) {
				EcUserScoreRec e = new EcUserScoreRec();
				e.setUser_id(t.getOrder_user_id());
				e.setUser_name(t.getOrder_user_name());
				e.setScore(t.getIntegral().intValue());
				e.setAll_score(t.getIntegral().intValue());
				e.setOpr_id(user.getId());
				if (t.getState() <= 0 && Integer.valueOf(state) > 0) {

					String integral_str = super.getFacade().getEcUserScoreRecService()
							.getEcUserScoreRecForUserTotalScore(t.getOrder_user_id());
					Long integral = new Long(integral_str);
					if (integral.compareTo(t.getIntegral()) == -1) {
						String msg = super.getMessage(request, "ec.gift.order.no.integral");
						super.renderJavaScript(response, "window.onload=function(){alert('" + msg
								+ "');history.back();}");
						return null;
					}
					e.setOpr_type(1);// 支付积分
					e.setNote("\u8ba2\u5355\u72b6\u6001\u4fee\u6539\uff0c\u652f\u4ed8\u79ef\u5206");// 订单状态修改，支付积分
					entity.setEcUserScoreRec(e);
				}
				if (t.getState() > 0 && Integer.valueOf(state) <= 0) {
					e.setOpr_type(0);// 返还积分
					e.setNote("\u8ba2\u5355\u72b6\u6001\u4fee\u6539\uff0c\u8fd4\u8fd8\u79ef\u5206");// 订单状态修改，返还积分
					entity.setEcUserScoreRec(e);
				}
			}
		}
		super.getFacade().getEcGiftOrdeService().modifyEcGiftOrdeIncludeStore(entity);

		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
}