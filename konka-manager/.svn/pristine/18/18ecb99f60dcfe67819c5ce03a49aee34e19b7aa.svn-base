package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Jiang,JiaYong
 * @version 2013-08-24
 * @desc 订单等级，跳转到新的流程
 */
public class KonkaR3ShopNewAction extends BaseAction {
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String dept_id = (String) dynaBean.get("dept_id");
		String r3_code = (String) dynaBean.get("r3_code");

		Pager pager = (Pager) dynaBean.get("pager");
		
		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		
		KonkaR3Shop entity = new KonkaR3Shop();
		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like);
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("dept_id", dept_id);
		}
		if (StringUtils.isNotBlank(r3_code)) {
			entity.setR3_code(r3_code);
		}

		// 数据级别判断开始
		Long __dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
		if(0!=__dept_id){
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
			request.setAttribute("current_fgs_code", dept_fgs.getDept_id());
		}
		int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				entity.getMap().put("dept_id_start", __dept_id);
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);
			break;
		case 0:
			__dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			//entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", userInfo.getId());
		entity.getMap().put("user_id", userInfo.getId());
		// 数据级别判断结束
		
		entity.setIs_del(0L);
		
		Long recordCount = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopForOrderCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
		List<KonkaR3Shop> entityList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopForOrderList(entity);
		request.setAttribute("entityList", entityList);

		// 辅助跳转到订单下单页面
		request.getSession().setAttribute("from_manager_is_salesman", "1");
		request.getSession().setAttribute("from_manager_user_id", userInfo.getId());
		return mapping.findForward("list");

	}

}