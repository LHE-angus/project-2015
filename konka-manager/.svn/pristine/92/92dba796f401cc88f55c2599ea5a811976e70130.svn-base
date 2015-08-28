package com.ebiz.mmt.web.struts.sfmall;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcGift;
import com.ebiz.mmt.domain.EcGiftOrde;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcUserScoreRec;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.web.struts.member.BaseMemberAction;

/**
 * @author Liu,ZhiXiang
 * @version 2013-09-11
 */
public class EcGiftOrdeAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.info("*****************" + getIntegralByUserId(1L));
		return null;
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		if (null == ecUser) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		if (null == ecUser) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");

		EcGiftOrde entity = new EcGiftOrde();
		super.copyProperties(entity, form);

		if (StringUtils.isBlank(id)) {
			// 新增

			entity.setOrder_from(1);
			entity.setTrade_index("");// 交易流水号 需要生产规则
			entity.setState(0);// 0-已预订 10-已兑换
			entity.setIs_del(0);

			// 只查询上架积分商品
			EcGift e = new EcGift();
			e.setId(entity.getGift_id());
			e.setState(1);
			e.getMap().put("is_upself", "true");
			e = super.getFacade().getEcGiftService().getEcGift(e);
			if (null == e) {
				String msg = "该积分商品不存在或已下架，不能下单，请重新选择！";
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
			if (StringUtils.isNotBlank(e.getDept_sn()) && !"-1".equals(e.getDept_sn())) {
				KonkaDept d = new KonkaDept();
				d.setDept_sn(e.getDept_sn());
				List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(d);
				if (null != konkaDeptList && konkaDeptList.size() > 0) {
					d = konkaDeptList.get(0);
					entity.setDept_id(d.getDept_id());
				} else {
					String msg = "该积分商品的所在分公司不存在或已删除，不能下单，请重新选择！";
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
					return null;
				}

			}
			entity.setIntegral(e.getNeed_integral());
			entity.setOrder_user_id(ecUser.getId());// 下单用户ID
			entity.setOrder_user_name(ecUser.getReal_name());// 下单人姓名

			Long surplus_integral = 0L;// 剩余积分
			surplus_integral = getIntegralByUserId(ecUser.getId());// 公用的剩余积分查询方法
			if (surplus_integral.compareTo(e.getNeed_integral()) == -1) {
				String msg = "您的积分不足，不能兑换该商品，请重新选择！";
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			super.getFacade().getEcGiftOrdeService().createEcGiftOrde(entity);

			saveMessage(request, "entity.inserted");
		} else {
			// 修改

			// entity.setId(Long.valueOf(id));
			// entity.setState(10);//订单状态：-30-已退货 -20-审核未通过 -10-已关闭 0-已预订 10-已兑换
			// 20-审核通过 30-下发处理 40-商家发货 50-客户已换货 60-确认收货

			// EcGiftOrde.state = 10 兑换操作时，增加记录
			if (entity.getState() == 10) {
				EcUserScoreRec ecUserScoreRec = new EcUserScoreRec();// 用户积分记录操作表
				ecUserScoreRec.setUser_id(ecUser.getId());
				ecUserScoreRec.setUser_name(ecUser.getUser_name());
				ecUserScoreRec.setOpr_type(1);// 积分操作类型：0-增加，1-减少
				ecUserScoreRec.setScore(entity.getIntegral().intValue());
				ecUserScoreRec.setOpr_id(ecUser.getId());
				entity.setEcUserScoreRec(ecUserScoreRec);
			}

			super.getFacade().getEcGiftOrdeService().modifyEcGiftOrdeIncludeStore(entity);
			saveMessage(request, "entity.updated");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&");
		// pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
}