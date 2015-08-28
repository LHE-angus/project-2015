package com.ebiz.mmt.web.struts.customer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaOrderInfoTrans;
import com.ebiz.mmt.domain.KonkaOrderInfoTransDetails;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * Code by AutoGenerated Builder AutoGenerated Builder Version 2.1
 * 
 * @author Xiao,GuoJian
 * @datetime 2014-05-23
 */
public class KonkaOrderInfoTransAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.listForFHD(mapping, form, request, response);
	}

	/**
	 * @authorXiao,GuoJian
	 * @date 2014-05-23
	 */
	public ActionForward listForFHD(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(10);
		String trans_ensu_status = (String) dynaBean.get("trans_ensu_status");// 签收状态
		String trans_ensu_type = (String) dynaBean.get("trans_ensu_type");// 签收方式
		String trans_index_like = (String) dynaBean.get("trans_index_like");// 发货单号
		String r3_vbedl_like = (String) dynaBean.get("r3_vbedl_like");// 3物流单号
		String trans_ensu_date_s = (String) dynaBean.get("trans_ensu_date_s");// 签收日期
		String trans_ensu_date_e = (String) dynaBean.get("trans_ensu_date_e");// 签收日期
		String customer_name_like = (String) dynaBean.get("customer_name_like");// 客户名称
		String r3_code_like = (String) dynaBean.get("r3_code_like");// R3客户编码
		String r3_code_sdf_like = (String) dynaBean.get("r3_code_sdf_like");// 送达方
		String trans_unit_like = (String) dynaBean.get("trans_unit_like");// 承运单位
		String link_name_like = (String) dynaBean.get("link_name_like");// 司机姓名
		String link_phone_like = (String) dynaBean.get("link_phone_like");// 司机电话
		String trans_mode = (String) dynaBean.get("trans_mode");// 配送方式
		String trans_recl_user_like = (String) dynaBean.get("trans_recl_user_like");// 收货人姓名
		String trans_recl_user_phone_like = (String) dynaBean.get("trans_recl_user_phone_like");// 收货人电话
		String trans_recl_addr_like = (String) dynaBean.get("trans_recl_addr_like");// 收货人地址
		String trans_detail_status = (String) dynaBean.get("trans_detail_status");// 发货状态
		String is_print = (String) dynaBean.get("is_print");// 打印状态

		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		KonkaOrderInfoTrans entity = new KonkaOrderInfoTrans();

		if (StringUtils.isNotBlank(trans_ensu_status)) {
			entity.getMap().put("trans_ensu_status", trans_ensu_status);
		}
		if (StringUtils.isNotBlank(trans_ensu_type)) {
			entity.getMap().put("trans_ensu_type", trans_ensu_type);
		}
		if (StringUtils.isNotBlank(trans_index_like)) {
			entity.getMap().put("trans_index_like", trans_index_like);
		}
		if (StringUtils.isNotBlank(r3_vbedl_like)) {
			entity.getMap().put("r3_vbedl_like", r3_vbedl_like);
		}
		if (StringUtils.isNotBlank(trans_ensu_date_s)) {
			entity.getMap().put("trans_ensu_date_s", trans_ensu_date_s);
		}
		if (StringUtils.isNotBlank(trans_ensu_date_e)) {
			entity.getMap().put("trans_ensu_date_e", trans_ensu_date_e);
		}
		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like);
		}
		if (StringUtils.isNotBlank(r3_code_like)) {
			entity.getMap().put("r3_code_like", r3_code_like);
		}
		if (StringUtils.isNotBlank(r3_code_sdf_like)) {
			entity.getMap().put("r3_code_sdf_like", r3_code_sdf_like);
		}
		if (StringUtils.isNotBlank(trans_unit_like)) {
			entity.getMap().put("trans_unit_like", trans_unit_like);
		}
		if (StringUtils.isNotBlank(link_name_like)) {
			entity.getMap().put("link_name_like", link_name_like);
		}
		if (StringUtils.isNotBlank(link_phone_like)) {
			entity.getMap().put("link_phone_like", link_phone_like);
		}
		if (StringUtils.isNotBlank(trans_mode)) {
			entity.setTrans_mode(Integer.parseInt(trans_mode));
		}
		if (StringUtils.isNotBlank(trans_recl_user_like)) {
			entity.getMap().put("trans_recl_user_like", trans_recl_user_like);
		}
		if (StringUtils.isNotBlank(trans_recl_user_phone_like)) {
			entity.getMap().put("trans_recl_user_phone_like", trans_recl_user_phone_like);
		}
		if (StringUtils.isNotBlank(trans_recl_addr_like)) {
			entity.getMap().put("trans_recl_addr_like", trans_recl_addr_like);
		}
		if (StringUtils.isNotBlank(trans_detail_status)) {
			entity.getMap().put("trans_detail_status", trans_detail_status);
		}
		if (StringUtils.isNotBlank(is_print)) {
			entity.getMap().put("is_print", is_print);
		}
		if (null != userInfo) {
			entity.getMap().put("cust_id", userInfo.getCust_id());
		}

		Long recordCount = super.getFacade().getKonkaOrderInfoTransService().getKonkaOrderInfoTransForFHDCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());

		List<KonkaOrderInfoTrans> entityList = super.getFacade().getKonkaOrderInfoTransService()
				.getKonkaOrderInfoTransForFHDPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	/**
	 * @method:确认
	 * @author Xiao,GuoJian
	 */
	public ActionForward confirm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");// mod_id
		String trans_index_detail = (String) dynaBean.get("trans_index_detail");
		if (StringUtils.isBlank(trans_index_detail)) {
			super.renderJavaScript(response, "alert('数据丢失！');history.back();");
			return null;
		}
		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		KonkaOrderInfoTransDetails entity = new KonkaOrderInfoTransDetails();
		if (StringUtils.isNotBlank(trans_index_detail)) {
			entity.setTrans_index_detail(trans_index_detail);
		}
		entity.setIs_del(0);
		List<KonkaOrderInfoTransDetails> entityList = super.getFacade().getKonkaOrderInfoTransDetailsService()
				.getKonkaOrderInfoTransDetailsForConfirmList(entity);
		request.setAttribute("entityList", entityList);
		request.setAttribute("mod_id", mod_id);
		request.setAttribute("trans_index_detail",trans_index_detail );
		if (null!=ui&&null!=ui.getId()&&null!=ui.getPass_word()) {
			request.setAttribute("user_id", ui.getId());
			request.setAttribute("password", ui.getPass_word());
		}
		return new ActionForward("/../customer/KonkaOrderInfoTrans/confirm.jsp");
	}

	/**
	 * @method:确认的保存
	 * @author Xiao,GuoJian
	 */
	public ActionForward confirmSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");// mod_id
		String trans_id = (String) dynaBean.get("trans_id");
		String trans_ensu_user = (String) dynaBean.get("trans_ensu_user");
		String trans_ensu_user_phone = (String) dynaBean.get("trans_ensu_user_phone");
		String trans_ensu_date = (String) dynaBean.get("trans_ensu_date");
		String[] ensu_ids = request.getParameterValues("ensu_id");// Details中ID
		String[] trans_unensu_nums = request.getParameterValues("trans_unensu_num");// 未签收数量
		String[] trans_ensu_nums = request.getParameterValues("trans_ensu_num");// 本次签收数量
		String[] trans_ensu_descs = request.getParameterValues("trans_ensu_desc");// 签收说明

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		List<KonkaOrderInfoTransDetails> detailsList = new ArrayList<KonkaOrderInfoTransDetails>();
		if (null != ensu_ids) {
			for (int i = 0; i < ensu_ids.length; i++) {
				KonkaOrderInfoTransDetails details = new KonkaOrderInfoTransDetails();
				if (StringUtils.isNotBlank(ensu_ids[i])) {
					details.setEnsu_id(Long.parseLong(ensu_ids[i]));
				}
				if (StringUtils.isNotBlank(trans_ensu_nums[i]) && StringUtils.isNotEmpty(trans_unensu_nums[i])) {
					// 判断已签收数量和未签收数量的大小 来判定签收状态
					details.setTrans_ensu_num(Long.parseLong(trans_ensu_nums[i]));
					if (Long.parseLong(trans_unensu_nums[i]) == Long.parseLong(trans_ensu_nums[i])) {// 未签收和本次签收相等的情况下
																										// 确认收货
						details.setTrans_ensu_status(2);
					} else if (Long.parseLong(trans_unensu_nums[i]) > Long.parseLong(trans_ensu_nums[i])) {// 未签收大于本次签收的情况下
																											// 部分签收
						details.setTrans_ensu_status(1);
					} else if (Long.parseLong(trans_ensu_nums[i]) == 0) {// 本次签收0的情况下
																			// 全部拒收
						details.setTrans_ensu_status(3);
					}
				}
				details.setTrans_ensu_desc(trans_ensu_descs[i]);
				details.setTrans_ensu_user_phone(trans_ensu_user_phone);
				details.setTrans_ensu_user(trans_ensu_user);
				details.setTrans_ensu_type(1);
				details.setTrans_ensu_date(format.parse(trans_ensu_date));
				detailsList.add(details);
			}
		}
		KonkaOrderInfoTrans konkaOrderInfoTrans = new KonkaOrderInfoTrans();
		// if(StringUtils.isNotBlank(trans_id)){
		// konkaOrderInfoTrans.setTrans_id(Long.parseLong(trans_id));
		// konkaOrderInfoTrans.set
		super.getFacade().getKonkaOrderInfoTransDetailsService()
				.modifyKonkaOrderInfoTransDetailsForEnsu(detailsList, konkaOrderInfoTrans);
		// }
		return new ActionForward("/../customer/manager/KonkaOrderInfoTrans.do?mod_id=" + mod_id, true);
	}

	/**
	 * @method:动态手动添加确认
	 * @author Xiao,GuoJian
	 */
	public ActionForward ajaxSetTransDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String trans_index_detail = (String) dynaBean.get("trans_index_detail");
		StringBuffer sb = new StringBuffer("{");
		if (StringUtils.isBlank(trans_index_detail)) {
			sb = sb.append("}");
			super.renderJson(response, sb.toString());
			return null;
		}

		sb = sb.append("\"list\":[");
		KonkaOrderInfoTransDetails transDetails = new KonkaOrderInfoTransDetails();
		transDetails.setTrans_index_detail(trans_index_detail);
		transDetails.setIs_del(0);

		List<KonkaOrderInfoTransDetails> transDetailsList = super.getFacade().getKonkaOrderInfoTransDetailsService()
				.getKonkaOrderInfoTransDetailsForConfirmList(transDetails);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		if (null != transDetailsList && transDetailsList.size() > 0) {
			for (KonkaOrderInfoTransDetails temp : transDetailsList) {
				if (null != temp) {
					sb = sb.append("{");
					sb = sb.append("\"ensu_id\":\"").append(temp.getEnsu_id()).append("\",");
					sb = sb.append("\"r3_vbedl\":\"").append(temp.getR3_vbedl()).append("\",");
					sb = sb.append("\"trade_index\":\"").append(temp.getTrade_index()).append("\",");
					sb = sb.append("\"trans_index_detail\":\"").append(temp.getTrans_index_detail()).append("\",");
					sb = sb.append("\"add_date\":\"").append(sf.format(temp.getAdd_date())).append("\",");
					sb = sb.append("\"customer_name\":\"").append(temp.getMap().get("customer_name")).append("\",");
					sb = sb.append("\"model_name\":\"").append(temp.getModel_name()).append("\",");
					sb = sb.append("\"model_num\":\"").append(temp.getModel_num()).append("\",");
					sb = sb.append("\"trans_num\":\"").append(temp.getTrans_num()).append("\",");
					sb = sb.append("\"trans_ensured_num\":\"")
							.append(temp.getTrans_ensured_num() == null ? 0 : temp.getTrans_ensured_num())
							.append("\",");
					sb = sb.append("\"result_num\":\"")
							.append(temp.getTrans_num()
									- (temp.getTrans_ensured_num() == null ? 0 : temp.getTrans_ensured_num()))
							.append("\",");
					sb = sb.append("\"model_num\":\"").append(temp.getModel_num()).append("\"");
					sb = sb.append("},");
				}
			}
		}
		String sb_str = StringUtils.removeEnd(sb.toString(), ",") + "]}";
		logger.info("sb_str {}", sb_str);
		super.renderJson(response, sb_str);
		return null;
	}

}