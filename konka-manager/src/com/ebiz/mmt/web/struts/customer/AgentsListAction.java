package com.ebiz.mmt.web.struts.customer;

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

import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.JBasePartner;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu,Hao
 * @version 2013-12-31
 */
public class AgentsListAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String partner_name_like = (String) dynaBean.get("partner_name_like"); // 网点名称
		String partner_sn_like = (String) dynaBean.get("partner_sn_like"); // 网点编码
		String p_level = (String) dynaBean.get("p_level"); // 网点级别
		String par_name_like = (String) dynaBean.get("par_name_like"); // 上级网点名称
		String link_name_like = (String) dynaBean.get("link_name_like"); // 联系人姓名
		String link_mobile_like = (String) dynaBean.get("link_mobile_like"); // 联系电话
		String is_del = (String) dynaBean.get("is_del");  //是否停用

		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);

		if (null == ui || null == ui.getCust_id()) {
			super.renderJavaScript(response, "alert('用户信息有误，请联系相关人员处理！');history.back();");
			return null;
		}

		KonkaR3Shop shop = new KonkaR3Shop();
		shop.setId(ui.getCust_id());
		shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
		if (null == shop) {
			super.renderJavaScript(response, "alert('客户不存在，请联系相关人员处理！');history.back();");
			return null;
		}

		JBasePartner entity = new JBasePartner();

		//判断当前登陆用户是网点还是客户
		if (shop.getIs_konka() == 0) { //网点
			JBasePartner jp = new JBasePartner();
			jp.setPartner_c_id(ui.getCust_id());
			jp.setIs_del(0);
			jp = super.getFacade().getJBasePartnerService().getJBasePartner(jp);
			if (null != jp) {
				entity.getMap().put("is_not_konka", jp.getPartner_id());
			} else {
				entity.getMap().put("is_not_konka", -1);     //原逻辑未知
			}
			request.setAttribute("is_not_konka", "1");
		} else {  //客户
			entity.getMap().put("is_konka", ui.getCust_id());
		}

		//网点名称
		if (StringUtils.isNotBlank(partner_name_like)) {
			partner_name_like = partner_name_like.trim();
			entity.getMap().put("partner_name_like", partner_name_like);
		}
		if (StringUtils.isNotBlank(partner_sn_like)) {
			partner_sn_like = partner_sn_like.trim();
			entity.getMap().put("partner_sn_like", partner_sn_like);
		}
		if (StringUtils.isNotBlank(p_level)) {
			entity.setP_level(Long.parseLong(p_level));
		}
		if (StringUtils.isNotBlank(par_name_like)) {
			par_name_like = par_name_like.trim();
			entity.getMap().put("par_name_like", par_name_like);
		}
		if (StringUtils.isNotBlank(link_name_like)) {
			link_name_like = link_name_like.trim();
			entity.getMap().put("link_name_like", link_name_like);
		}
		if (StringUtils.isNotBlank(link_mobile_like)) {
			entity.getMap().put("link_mobile_like", link_mobile_like);
		}
		if (StringUtils.isNotBlank(is_del)) {
			entity.setIs_del(Integer.valueOf(is_del));
		} else {
			entity.setIs_del(0);
		}
		entity.setPartner_type(1);
		entity.setPartner_obj(1);

		Long recordCount = super.getFacade().getJBasePartnerService().getJBasePartnerForLevelCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<JBasePartner> entityList = super.getFacade().getJBasePartnerService()
				.getJBasePartnerForLevelPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String partner_id = (String) dynaBean.get("partner_id");

		if (!GenericValidator.isLong(partner_id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		JBasePartner entity = new JBasePartner();
		entity.setPartner_id(Long.valueOf(partner_id));
		entity = super.getFacade().getJBasePartnerService().getJBasePartner(entity);
		entity.setQueryString(super.serialize(request, "partner_id", "method"));
		super.copyProperties(form, entity);
		
		//上级客户/网点名称
		if(null!=entity && entity.getC_id().equals(entity.getPar_c_id())){  //上级是客户
			KonkaR3Shop krs = new KonkaR3Shop();
			krs.setId(entity.getC_id());
			krs = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(krs);
			request.setAttribute("par_name", krs.getCustomer_name());
		}else{ //上级是网点
			JBasePartner jbp = new JBasePartner();
			jbp.setPartner_id(entity.getPar_c_id());
			jbp = super.getFacade().getJBasePartnerService().getJBasePartner(jbp);
			request.setAttribute("par_name", jbp.getPartner_name());
		}

		Long partner_c_id = entity.getPartner_c_id();
		if (null != partner_c_id) {
			KonkaR3Shop s = new KonkaR3Shop();
			s.setId(partner_c_id);
			s = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(s);

			if (null != s) {
				// 保证客户实体信息存在
				PeProdUser user = new PeProdUser();
				user.setCust_id(partner_c_id);
				List<PeProdUser> userList = super.getFacade().getPeProdUserService().getPeProdUserList(user);
				if (userList.size() > 0)
					request.setAttribute("partner_user_info", userList.get(0));
			}
		}

		return mapping.findForward("view");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String returnUrl = (String) dynaBean.get("returnUrl");
		
		if(StringUtils.isNotBlank(returnUrl)){
			request.setAttribute("returnUrl", returnUrl);
		}

		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);

		//查询上级网点名称，编码
		KonkaR3Shop shop = new KonkaR3Shop();
		shop.setId(ui.getCust_id());
		shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
		if (null == shop) {
			super.renderJavaScript(response, "alert('客户不存在，请联系相关人员处理！');history.back();");
			return null;
		}
		if (shop.getIs_konka() == 0) { //网点
			JBasePartner jp = new JBasePartner();
			jp.setPartner_c_id(ui.getCust_id());
			jp.setIs_del(0);
			jp = super.getFacade().getJBasePartnerService().getJBasePartner(jp);
			
			if (null != jp) {
				request.setAttribute("par_name", jp.getPartner_name());
				//分公司、经办、业务员
				KonkaDept kdept = new KonkaDept();
				kdept.setDept_id(jp.getDept_id());
				kdept = super.getFacade().getKonkaDeptService().getKonkaDept(kdept);
				if(null!=kdept){
					request.setAttribute("dept_name", kdept.getDept_name());
				}
				if(null!=jp.getJb_job_id()){
					KonkaDept jbname = new KonkaDept();
					jbname.setDept_id(jp.getJb_job_id());
					jbname = super.getFacade().getKonkaDeptService().getKonkaDept(jbname);
					if(null!=jbname){
						request.setAttribute("jb_name", jbname.getDept_name());
					}
				}
				PeProdUser puser = new PeProdUser();
				puser.setJob_id(jp.getYwy_job_id());
				puser = super.getFacade().getPeProdUserService().getPeProdUser(puser);
				if(null!=puser){
					request.setAttribute("ywy_name", puser.getUser_name());
				}
			}
		} else {  //客户
			request.setAttribute("par_name", shop.getCustomer_name());
			BranchAssign ba = new BranchAssign();
			ba.setKonka_r3_id(shop.getId());
			ba = super.getFacade().getBranchAssignService().getBranchAssign(ba);
			if(null!=ba){
				//分公司、经办、业务员
				KonkaDept kdept = new KonkaDept();
				kdept.setDept_id(ba.getFgs_id());
				kdept = super.getFacade().getKonkaDeptService().getKonkaDept(kdept);
				if(null!=kdept){
					request.setAttribute("dept_name", kdept.getDept_name());
				}
				if(null!=ba.getJyb_id()){
					KonkaDept jbname = new KonkaDept();
					jbname.setDept_id(ba.getJyb_id());
					jbname = super.getFacade().getKonkaDeptService().getKonkaDept(jbname);
					if(null!=jbname){
						request.setAttribute("jb_name", jbname.getDept_name());
					}
				}
				PeProdUser puser = new PeProdUser();
				puser.setId(ba.getUser_id());
				puser = super.getFacade().getPeProdUserService().getPeProdUser(puser);
				if(null!=puser){
					request.setAttribute("ywy_name", puser.getUser_name());
				}
			}
		}
		request.setAttribute("addormodify", "0");
		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String partner_id = (String) dynaBean.get("partner_id");

		if (!GenericValidator.isLong(partner_id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		JBasePartner entity = new JBasePartner();
		entity.setPartner_id(Long.valueOf(partner_id));
		entity = super.getFacade().getJBasePartnerService().getJBasePartner(entity);
		entity.setQueryString(super.serialize(request, "partner_id", "method"));
		super.copyProperties(form, entity);
		
		//上级客户/网点名称
		if(null!=entity && entity.getC_id().equals(entity.getPar_c_id())){  //上级是客户
			KonkaR3Shop krs = new KonkaR3Shop();
			krs.setId(entity.getC_id());
			krs = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(krs);
			request.setAttribute("par_name", krs.getCustomer_name());
		}else{ //上级是网点
			JBasePartner jbp = new JBasePartner();
			jbp.setPartner_id(entity.getPar_c_id());
			jbp = super.getFacade().getJBasePartnerService().getJBasePartner(jbp);
			request.setAttribute("par_name", jbp.getPartner_name());
		}
		
		//分公司、经办、业务员
		KonkaDept kdept = new KonkaDept();
		kdept.setDept_id(entity.getDept_id());
		kdept = super.getFacade().getKonkaDeptService().getKonkaDept(kdept);
		if(null!=kdept){
			request.setAttribute("dept_name", kdept.getDept_name());
		}
		if(null!=entity.getJb_job_id()){
			KonkaDept jbname = new KonkaDept();
			jbname.setDept_id(entity.getJb_job_id());
			jbname = super.getFacade().getKonkaDeptService().getKonkaDept(jbname);
			if(null!=jbname){
				request.setAttribute("jb_name", jbname.getDept_name());
			}
		}
		PeProdUser puser = new PeProdUser();
		puser.setJob_id(entity.getYwy_job_id());
		puser = super.getFacade().getPeProdUserService().getPeProdUser(puser);
		if(null!=puser){
			request.setAttribute("ywy_name", puser.getUser_name());
		}

		Long partner_c_id = entity.getPartner_c_id();
		if (null != partner_c_id) {
			KonkaR3Shop s = new KonkaR3Shop();
			s.setId(partner_c_id);
			s = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(s);

			if (null != s) {
				// 保证客户实体信息存在
				PeProdUser user = new PeProdUser();
				user.setCust_id(partner_c_id);
				List<PeProdUser> userList = super.getFacade().getPeProdUserService().getPeProdUserList(user);
				// user =
				// super.getFacade().getPeProdUserService().getPeProdUser(user);
				if (userList.size() > 0)
					request.setAttribute("partner_user_info", userList.get(0));
			}
		}

		// 取客户及网点信息
		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);

		//初始化所在城市
		if (null != entity.getArea_code() && String.valueOf(entity.getArea_code()).length() >= 6) {
			request.setAttribute("province", String.valueOf(entity.getArea_code()).substring(0, 2) + "0000");
			request.setAttribute("city", String.valueOf(entity.getArea_code()).substring(0, 4) + "00");
			request.setAttribute("country", String.valueOf(entity.getArea_code()).substring(0, 6));
			request.setAttribute("town", String.valueOf(entity.getArea_code()));
		}
		
		//初始化收货人所在城市
		if (null != entity.getConsignee_p_index() && String.valueOf(entity.getConsignee_p_index()).length() >= 6) {
			request.setAttribute("_province", String.valueOf(entity.getConsignee_p_index()).substring(0, 2) + "0000");
			request.setAttribute("_city", String.valueOf(entity.getConsignee_p_index()).substring(0, 4) + "00");
			request.setAttribute("_country", String.valueOf(entity.getConsignee_p_index()).substring(0, 6));
			request.setAttribute("_town", String.valueOf(entity.getConsignee_p_index()));
		}
		
		// 部门列表
		KonkaDept konka_dept = new KonkaDept();
		konka_dept.setPar_id(0L);
		konka_dept.getMap().put("order_by_dept_name", true);
		List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konka_dept);
		request.setAttribute("peDeptList", deptInfoList);
		request.setAttribute("addormodify", "1");
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!super.isTokenValid(request, true)) {
			super.saveMessage(request, "errors.token");
			return mapping.findForward("list");
		}
		resetToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String returnUrl = (String) dynaBean.get("returnUrl");
		String partner_id = (String) dynaBean.get("partner_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String partner_user_id = (String) dynaBean.get("partner_user_id");
		String user_name = (String) dynaBean.get("user_name");
		String pass_word = (String) dynaBean.get("pass_word");
		
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");
		
		String _province = (String) dynaBean.get("_province");
		String _city = (String) dynaBean.get("_city");
		String _country = (String) dynaBean.get("_country");
		String _town = (String) dynaBean.get("_town");
		
		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);

		JBasePartner entity = new JBasePartner();
		if (StringUtils.isNotBlank(user_name)) {
			entity.getMap().put("user_name", user_name);
		}
		if (StringUtils.isNotBlank(pass_word)) {
			entity.getMap().put("pass_word", pass_word);
		}
		entity.getMap().put("current_user_id", ui.getId());
		
		//设置所在城市中的最后维度的代码
		if (StringUtils.isNotBlank(town)) {
			entity.setArea_code(town);
		} else if (StringUtils.isNotBlank(country)) {
			entity.setArea_code(country);
		} else if (StringUtils.isNotBlank(city)) {
			entity.setArea_code(city);
		} else if (StringUtils.isNotBlank(province)) {
			entity.setArea_code(province);
		}
		
		//收货人城市
		if (StringUtils.isNotBlank(_town)) {
			entity.setConsignee_p_index(_town);
		} else if (StringUtils.isNotBlank(_country)) {
			entity.setConsignee_p_index(_country);
		} else if (StringUtils.isNotBlank(_city)) {
			entity.setConsignee_p_index(_city);
		} else if (StringUtils.isNotBlank(_province)) {
			entity.setConsignee_p_index(_province);
		}
		
		super.copyProperties(entity, form);
		
		Long r3_id = null;

		if (GenericValidator.isLong(partner_id)) {  //修改网点
			entity.setModify_user_id(ui.getId());
			entity.setModify_date(new Date());
			JBasePartner jBasePartner = new JBasePartner();
			jBasePartner.setPartner_id(Long.valueOf(partner_id));
			jBasePartner = super.getFacade().getJBasePartnerService().getJBasePartner(jBasePartner);
			if (jBasePartner.getPartner_type() == 1 && jBasePartner.getPartner_obj() == 1
					&& StringUtils.isNotBlank(partner_user_id)) {
				saveMessage(request, "entity.updated");
				entity.setPar_c_id(jBasePartner.getPar_c_id());
				entity.setPartner_c_id(jBasePartner.getPartner_c_id());
				super.getFacade().getKonkaR3ShopService().modifyKonkaR3ShopWithJBasePartner(entity);
			} else {
				saveMessage(request, "entity.updated");
				if (entity.getPartner_c_id() == null) {
					entity.getMap().put("set_partner_c_id_null", true);
				}
				super.getFacade().getJBasePartnerService().modifyJBasePartner(entity);
			}
		} else {
            //新增网点
			entity.setCreate_user_id(ui.getId());
			entity.setAdd_date(new Date());
			// 判断操作用户是客户还是下级网点
			if (null == ui || null == ui.getCust_id()) {
				super.renderJavaScript(response, "alert('用户信息有误，请联系相关人员处理！');history.back();");
				return null;
			}

			//当前用户关联信息
			KonkaR3Shop shop = new KonkaR3Shop();
			shop.setId(ui.getCust_id());
			shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
			if (null == shop) {
				super.renderJavaScript(response, "alert('客户不存在，请联系相关人员处理！');history.back();");
				return null;
			}

			// 客户
			if (shop.getIs_konka() == 1) {
				r3_id = shop.getId();
				
				KonkaR3Shop  r3shop = new KonkaR3Shop();
				r3shop.setId(r3_id);
				List<KonkaR3Shop> entityList = new ArrayList<KonkaR3Shop>();
				entityList.add(getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop));
				super.setBranchNameForR3ShopListByKonkaR3ShopList(entityList);
				if(null != entityList.get(0).getMap().get("fgs_id")){
					entity.setDept_id(Long.parseLong(entityList.get(0).getMap().get("fgs_id").toString()));
				}
				if(null != entityList.get(0).getMap().get("jyb_id")){
					entity.setJb_job_id(Long.parseLong(entityList.get(0).getMap().get("jyb_id").toString()));
				}
				if(null != entityList.get(0).getMap().get("ywy_id")){
					PeProdUser user = new PeProdUser();
					user.setId(Long.parseLong(entityList.get(0).getMap().get("ywy_id").toString()));
					user = super.getFacade().getPeProdUserService().getPeProdUser(user);
					entity.setYwy_job_id(user.getJob_id());
				}
				entity.setPar_c_id(shop.getId());
			} else {// 网点
				JBasePartner j = new JBasePartner();
				j.setIs_del(0);
				j.setPartner_c_id(shop.getId());
				List<JBasePartner> jList = super.getFacade().getJBasePartnerService().getJBasePartnerList(j);
				if (jList.size() > 0){
					r3_id = jList.get(0).getC_id();
					entity.setJb_job_id(jList.get(0).getJb_job_id());
					entity.setYwy_job_id(jList.get(0).getYwy_job_id());
					entity.setDept_id(jList.get(0).getDept_id());
					entity.setPar_c_id(jList.get(0).getPartner_id());
				}
			}

			if (null != r3_id){
				entity.setC_id(Long.valueOf(r3_id));
			}
			entity.setPartner_type(1);
			entity.setPartner_obj(1);
			
			Long id = super.getFacade().getKonkaR3ShopService().createKonkaR3ShopWithJBasePartner(entity);
			partner_id = String.valueOf(id);
			saveMessage(request, "entity.inserted");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		if(StringUtils.isNotBlank(returnUrl)){
			pathBuffer.append(returnUrl);
		}else{
			pathBuffer.append(mapping.findForward("success").getPath());
			pathBuffer.append("&mod_id=" + mod_id);
			pathBuffer.append("&");
		}
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward validateName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		StringBuffer oper = new StringBuffer("{\"result\":");
		String user_name = (String) dynaBean.get("user_name");
		PeProdUser entity = new PeProdUser();
		entity.setUser_name(user_name);
		// entity.setIs_del(0);
		List<PeProdUser> entityList = super.getFacade().getPeProdUserService().getPeProdUserList(entity);
		if (entityList.size() == 0) {
			oper.append(false);
		} else {
			oper.append(true);
		}
		oper.append("}");
		super.render(response, oper.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String partner_id = (String) dynaBean.get("partner_id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(partner_id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		// 停止用户信息
		String c_ids = partner_id;
		JBasePartner jbp = new JBasePartner();
		jbp.setPartner_id(Long.valueOf(partner_id));
		List<JBasePartner> jbpList = super.getFacade().getJBasePartnerService().getJBasePartnerList(jbp);
		if (jbpList.size() > 0) {
			for (JBasePartner jj : jbpList) {
				c_ids = c_ids + "," + jj.getPartner_c_id();
			}
		}
		PeProdUser pUser = new PeProdUser();
		// pUser.setCust_id(Long.valueOf(id));
		pUser.getMap().put("c_ids", c_ids);
		pUser.setIs_del(1);
		super.getFacade().getPeProdUserService().modifyPeProdUser(pUser);

		JBasePartner entity = new JBasePartner();
		entity.setPartner_id(Long.valueOf(partner_id));
		super.getFacade().getJBasePartnerService().removeJBasePartner(entity);

		saveMessage(request, "konka.close.success");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward reStart(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String partner_id = (String) dynaBean.get("partner_id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(partner_id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		// 停止用户信息
		String c_ids = partner_id;
		JBasePartner jbp = new JBasePartner();
		jbp.setPartner_id(Long.valueOf(partner_id));
		List<JBasePartner> jbpList = super.getFacade().getJBasePartnerService().getJBasePartnerList(jbp);
		if (jbpList.size() > 0) {
			for (JBasePartner jj : jbpList) {
				c_ids = c_ids + "," + jj.getPartner_c_id();
			}
		}
		PeProdUser pUser = new PeProdUser();
		// pUser.setCust_id(Long.valueOf(id));
		pUser.getMap().put("c_ids", c_ids);
		pUser.setIs_del(0);
		super.getFacade().getPeProdUserService().modifyPeProdUser(pUser);

		JBasePartner entity = new JBasePartner();
		entity.setPartner_id(Long.valueOf(partner_id));
		entity.setIs_del(0);
		super.getFacade().getJBasePartnerService().modifyJBasePartner(entity);

		saveMessage(request, "konka.restart.success");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public List<JBasePartner> getEntityList(Long cust_id) {

		List<JBasePartner> entityList = new ArrayList<JBasePartner>();

		KonkaR3Shop shop = new KonkaR3Shop();
		shop.setId(cust_id);
		shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);

		if (null != shop) {

			JBasePartner entity = new JBasePartner();

			if (shop.getIs_konka() == 0) {
				JBasePartner jp = new JBasePartner();
				jp.setPartner_c_id(cust_id);
				jp.setIs_del(0);
				List<JBasePartner> jpList = super.getFacade().getJBasePartnerService().getJBasePartnerList(jp);
				if (jpList.size() > 0) {
					entity.getMap().put("is_not_konka", jpList.get(0).getPartner_id());
				} else {
					entity.getMap().put("is_not_konka", -1);
				}

			} else {
				entity.getMap().put("is_konka", cust_id);
				
				// 如果是R3客户，增加客户的编号和名称
				JBasePartner jbp = new JBasePartner();
				jbp.setPartner_id(cust_id);
				jbp.setPartner_name(shop.getCustomer_name());
				entityList.add(jbp);
			}
			
			entity.setPartner_type(1);
			entity.setPartner_obj(1);
			entity.setIs_del(0);
			List<JBasePartner> entityList1 = super.getFacade().getJBasePartnerService()
					.getJBasePartnerForSonPaginatedList(entity);
			entityList.addAll(entityList1);
			
		} else {
			entityList = null;
		}
		return entityList;
	}
}
