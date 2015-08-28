package com.ebiz.mmt.web.struts.customer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;

/**
 * @author Hu,Hao
 * @version 2013-06-11
 */
public class JxcUserInfoUpdateAction extends BaseClientJxcAction {
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		PeProdUser user_info = super.getSessionCustomerUserInfo(request);
		PeProdUser entity = new PeProdUser();
		entity.setId(user_info.getId());
		entity = super.getFacade().getPeProdUserService().getPeProdUser(entity);

		super.copyProperties(form, entity);

		if (null != entity.getP_index()) {
			BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
			baseProvinceListFour.setP_index(entity.getP_index());
			baseProvinceListFour = super.getFacade().getBaseProvinceListFourService()
					.getBaseProvinceListFour(baseProvinceListFour);

			//todo 需要检查是否为空的问题
			String p_index_name="";
			if(baseProvinceListFour != null)
				p_index_name = baseProvinceListFour.getFull_name();

			request.setAttribute("p_index_name", p_index_name);
		}

		// R3客户资料
		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(entity.getCust_id());
		konkaR3Shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
		
		//1为客户，0为网点
		if(0==konkaR3Shop.getIs_konka()){
			request.setAttribute("wd_ywyinfo", super.getFacade().getJBasePartnerService().getYWYAndDept(konkaR3Shop.getR3_code()));
		}
		
		//客户类型
		KonkaCategory kc = new KonkaCategory();
		if(konkaR3Shop != null && StringUtils.isNotEmpty(konkaR3Shop.getCustomer_type())){
			kc.setC_index(Long.valueOf(konkaR3Shop.getCustomer_type()));
			request.setAttribute("cust_type",  super.getFacade().getKonkaCategoryService().getKonkaCategory(kc).getC_name());
		}
		
		request.setAttribute("konkaR3Shop", konkaR3Shop);
		// 业务员资料
		if (null != konkaR3Shop) {
			BranchAssign branchAssign = new BranchAssign();
			branchAssign.setKonka_r3_id(konkaR3Shop.getId());
			branchAssign = super.getFacade().getBranchAssignService().getBranchAssign(branchAssign);// 一个客户只有一个业务员
			if (null != branchAssign) {
				PeProdUser salesman = new PeProdUser();
				salesman.setId(branchAssign.getUser_id());
				salesman = super.getFacade().getPeProdUserService().getPeProdUser(salesman);
				request.setAttribute("salesman", salesman);
			}
		}

		return mapping.findForward("view");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		PeProdUser user_info = super.getSessionCustomerUserInfo(request);
		// 获取选择的修改用户 企业信息
		PeProdUser entity = new PeProdUser();
		entity.setId(user_info.getId());
		entity = super.getFacade().getPeProdUserService().getPeProdUser(entity);

		super.copyProperties(form, entity);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (null != entity.getBirthday()) {
			entity.getMap().put("birthday", format.format(entity.getBirthday()));
		}

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
					// request.setAttribute("province", p_index);
				} else if (p_index.endsWith("00")) {
					if (p_index.length() == 6) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
						request.setAttribute("city", p_index);
					} else if (p_index.length() == 8) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "000000");
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "0000");
						request.setAttribute("country", StringUtils.substring(p_index, 0, 6) + "00");
					}
					// request.setAttribute("province",
					// StringUtils.substring(p_index, 0, 2) + "0000");
					// request.setAttribute("city", p_index);
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
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");

		String real_name = (String) dynaBean.get("real_name");
		String sex = (String) dynaBean.get("sex");
		String birthday = (String) dynaBean.get("birthday");
		String link_addr = (String) dynaBean.get("link_addr");
		String link_post = (String) dynaBean.get("link_post");
		String link_phone = (String) dynaBean.get("link_phone");
		String link_tel = (String) dynaBean.get("link_tel");
		String email = (String) dynaBean.get("email");
		String link_qq = (String) dynaBean.get("link_qq");
		String link_msn = (String) dynaBean.get("link_msn");

		PeProdUser user_info = super.getSessionCustomerUserInfo(request);

		PeProdUser entity = new PeProdUser();
		entity.setId(user_info.getId());
		if (StringUtils.isNotBlank(real_name)) {
			entity.setReal_name(real_name);
		}
		if (StringUtils.isNotBlank(sex)) {
			entity.setSex(Integer.valueOf(sex));
		}
		if (StringUtils.isNotBlank(birthday)) {
			entity.setBirthday(DateUtils.parseDate(birthday, new String[] { "yyyy-MM-dd" }));
		}
		if (StringUtils.isNotBlank(link_addr)) {
			entity.setLink_addr(link_addr);
		}
		if (StringUtils.isNotBlank(link_post)) {
			entity.setLink_post(link_post);
		}
		if (StringUtils.isNotBlank(link_phone)) {
			entity.setLink_phone(link_phone);
		}
		if (StringUtils.isNotBlank(link_tel)) {
			entity.setLink_tel(link_tel);
		}
		if (StringUtils.isNotBlank(email)) {
			entity.setEmail(email);
		}
		if (StringUtils.isNotBlank(link_qq)) {
			entity.setLink_qq(link_qq);
		}
		if (StringUtils.isNotBlank(link_msn)) {
			entity.setLink_msn(link_msn);
		}

		// 人员居住地设定
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

		// 人员居住地设定
		// if (StringUtils.isNotBlank(province) &&
		// !StringUtils.isNotBlank(city)) {
		// entity.setP_index(Long.valueOf(province));
		// }
		// if (StringUtils.isNotBlank(province) && StringUtils.isNotBlank(city)
		// && !StringUtils.isNotBlank(country)) {
		// entity.setP_index(Long.valueOf(city));
		// }
		// if (StringUtils.isNotBlank(province) && StringUtils.isNotBlank(city)
		// && StringUtils.isNotBlank(country)
		// && !StringUtils.isNotBlank(town)) {
		// entity.setP_index(Long.valueOf(country));
		// }
		// if (StringUtils.isNotBlank(province) && StringUtils.isNotBlank(city)
		// && StringUtils.isNotBlank(country)
		// && StringUtils.isNotBlank(town)) {
		// entity.setP_index(Long.valueOf(town));
		// }

		super.getFacade().getPeProdUserService().modifyPeProdUser(entity);
		saveMessage(request, "entity.updated");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
}
