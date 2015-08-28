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
import com.ebiz.mmt.domain.KonkaMobileSailData;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu,Hao
 * @version 2013-12-04
 */
public class MqtKonkaStocksPreviewAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		String dept_sn = (String) dynaBean.get("dept_sn");
		String excel_to_all = (String) dynaBean.get("excel_to_all");

		KonkaR3Shop entity = new KonkaR3Shop();

		if (StringUtils.isNotBlank(customer_name_like))
			entity.getMap().put("customer_name_like", customer_name_like);

		if (StringUtils.isNotBlank(r3_code_like))
			entity.getMap().put("r3_code_like", r3_code_like);

		if (StringUtils.isNotBlank(pd_name_like))
			entity.getMap().put("pd_name_like", pd_name_like);
		
		if (StringUtils.isNotBlank(dept_sn))
			entity.getMap().put("dept_sn", dept_sn);

		// 数据级别判断开始
		Long __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			
			//分公司列表
			KonkaDept kd = new KonkaDept();
			kd.setDept_type(3);
			List<KonkaDept> kdList = super.getFacade().getKonkaDeptService().getKonkaDeptList(kd);
			request.setAttribute("kdList", kdList);
			
			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_sn()) {
				entity.getMap().put("dept_sn", dept_fgs.getDept_sn());
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);
			break;
		case 0:
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", ui.getId());

		Long recordCount = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopForStocksCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaR3Shop> entityList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopForStocksList(entity);
			
		if (StringUtils.isNotBlank(excel_to_all)) {

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("客户库存数据")
					+ ".xls");

			entity.getRow().setCount(recordCount.intValue());
			List<KonkaR3Shop> entityList1 = getFacade().getKonkaR3ShopService()
					.getKonkaR3ShopForStocksList(entity);
			request.setAttribute("allList", entityList1);
			return mapping.findForward("view");
		}
		
		request.setAttribute("entityList", entityList);
		
		return mapping.findForward("list");
	}
}
