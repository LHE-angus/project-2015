package com.ebiz.mmt.web.struts.manager.admin;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaSell;
import com.ebiz.mmt.domain.KonkaSellDetails;
import com.ebiz.mmt.domain.KonkaStockDetails;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
//import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wang,Yang
 * @version 2011-9-25
 */
public class KonkaSellAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");

		KonkaR3Shop entity = super.getR3ShopByAddDate(mapping, form, request, response);

		Long recordCount = getFacade().getKonkaR3ShopService().getKonkaR3ShopCountByAddDate(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService().getKonkaR3ShopPaginatedListByAddDate(entity);

		super.setBranchNameForR3ShopListByKonkaR3ShopList(entityList);

		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward toSell(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.sell(mapping, form, request, response);
	}

	public ActionForward sell(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.getModPopeDom(form, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String state = (String) dynaBean.get("state");
		String r3_shop_id = (String) dynaBean.get("r3_shop_id");
		String cus_sn_like = (String) dynaBean.get("cus_sn_like");
		String sell_date_start = (String) dynaBean.get("sell_date_start");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
		String add_date_start = (String) dynaBean.get("add_date_start");
		String add_date_end = (String) dynaBean.get("add_date_end");

//		HttpSession session = request.getSession();
//		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		
//		PeRoleUser role = new PeRoleUser();
//		role.setUser_id(ui.getId());
//		role = getFacade().getPeRoleUserService().getPeRoleUser(role);

		KonkaR3Shop r3Shop = new KonkaR3Shop();
		r3Shop.setId(Long.valueOf(r3_shop_id));
		r3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3Shop);
		
		KonkaStockDetails details = new KonkaStockDetails();
		details.setR3_code(r3Shop.getR3_code());
		List<KonkaStockDetails> detailsList = getFacade().getKonkaStockDetailsService().getKonkaStockDetailsList(details);
		if(detailsList.size()==0){
			dynaBean.set("key", 1);
		}

		KonkaSell entity = new KonkaSell();
		entity.setIs_del(0);
		entity.setCus_sn(r3Shop.getR3_code());
		entity.getMap().put("cus_sn_like", cus_sn_like);

		if (StringUtils.isNotBlank(sell_date_start)) {
			entity.getMap().put("sell_date_start", sell_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(sell_date_end)) {
			entity.getMap().put("sell_date_end", sell_date_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(add_date_start)) {
			entity.getMap().put("add_date_start", add_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_date_end)) {
			entity.getMap().put("add_date_end", add_date_end + " 23:59:59");
		}

//		if (0 != ui.getId()) { // 超级管理员可以查看全部
//			entity.getMap().put("dept_id", ui.getDept_id());
//		}
//		if(role.getRole_id()>=20&&role.getRole_id()<60){// 事业部、分公司、经营部、办事处 的角色登录 可以看到自己及下属部门的上报
//			entity.getMap().put("dept_id", ui.getDept_id());
//		}
//		if(role.getRole_id() == 60){//业务员只能看到自己上报的数据
//			entity.setAdd_user_id(ui.getId());
//		}
		if (StringUtils.isEmpty(state)) {
			entity.getMap().put("state_in", new Integer[] { 0, 1, 2 });
		} else {
			entity.setState(Integer.parseInt(state));
		}
		// }

		Long recordCount = super.getFacade().getKonkaSellService().getKonkaSellCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaSell> konkaSellList = super.getFacade().getKonkaSellService().getKonkaSellPaginatedList(entity);

		request.setAttribute("konkaSellList", konkaSellList);
		return new ActionForward(response.encodeRedirectURL("/admin/KonkaSell/sell.jsp"));
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String r3_shop_id = (String) dynaBean.get("r3_shop_id");
		
		KonkaR3Shop r3Shop = new KonkaR3Shop();
		r3Shop.setId(Long.valueOf(r3_shop_id));
		r3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3Shop);
		request.setAttribute("r3Shop", r3Shop);
		
		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		request.setAttribute("konkaBranchAssignList", super.getBranchAssignByUserId(ui.getId()));

		// 取分配给自己所在分公司的产品型号
		request.setAttribute("kpmList", super.getDeptLinkProduct(request, response, null, null, null,null));

		dynaBean.set("queryString", super.serialize(request, "s_id", "method"));
		dynaBean.set("now_date", new Date());

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String r3_shop_id = (String) dynaBean.get("r3_shop_id");
		String is_temporary = (String) dynaBean.get("is_temporary");
		String sell_date_temp = (String) dynaBean.get("sell_date_temp");
		

		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		// PeProdUser peProdUser = (PeProdUser) session.getAttribute(Constants.PE_PROD_USER_SESSION);

		// 取所有型号对应的值
//		PePdModel kpm = new PePdModel();
		List<PePdModel> kpmList = super.getDeptLinkProduct(request, response, null, null, null,null);

		String value, money;
		List<KonkaSellDetails> ksdList = new ArrayList<KonkaSellDetails>();
		for (PePdModel PePdModel : kpmList) {
			KonkaSellDetails temp = new KonkaSellDetails();

			value = (String) dynaBean.get(PePdModel.getPd_id().toString());
			money = (String) dynaBean.get("money_" + PePdModel.getPd_id().toString());
			if ((GenericValidator.isLong(value)) && StringUtils.isNotBlank(money)) {//数量和单价都不为空
				temp.setPd_id(PePdModel.getPd_id());
				temp.setSell_count(Long.valueOf(value));
				temp.setSell_money(new BigDecimal(money));
				ksdList.add(temp);                                                   //添加到插入数据库的序列中
			} 

		}

		// 销售记录记录
		KonkaSell ks = new KonkaSell();
		super.copyProperties(ks, form);
		ks.setKonkaSellDetailsList(ksdList); // 存所有销售记录详细信息
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// 取R3客户名称 r3编码
		KonkaR3Shop r3Shop = new KonkaR3Shop();
		r3Shop.setId(Long.valueOf(r3_shop_id));
		r3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3Shop);
		if (null == ks.getS_id()) { // insert


			// 判断是否销售记录一天只允许销售记录一次
			KonkaSell ksell = new KonkaSell();
			ksell.setIs_del(0);
			ksell.setCus_sn(r3Shop.getR3_code());
			ksell.getMap().put("sell_date_start", sell_date_temp + " 00:00:00");
			ksell.getMap().put("sell_date_end", sell_date_temp + " 23:59:59");
			ksell = getFacade().getKonkaSellService().getKonkaSell(ksell);

			if (null != ksell) {
				super.saveError(request, "sell.add.info.had", ksell.getTitle());
				return this.sell(mapping, form, request, response);
			}

			// 查询部门信息
			KonkaDept kd = new KonkaDept();
			kd.setDept_id(ui.getDept_id());
			if (null != kd.getDept_id()) {
				kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
			} else {
				kd = null;
			}

			// init data start...
			String title = r3Shop.getCustomer_name() + "   " + sell_date_temp;
			ks.setTitle(title);
			ks.setCus_name(r3Shop.getCustomer_name());
			ks.setCus_sn(r3Shop.getR3_code());
			ks.setIs_del(0);
			ks.setAdd_date(new Date());
			ks.setAdd_user_id(ui.getId());
			ks.setAdd_real_name(ui.getReal_name());
			if (null != kd) {
				ks.setAdd_dept_id(kd.getDept_id());
				ks.setAdd_dept_name(kd.getDept_name());
			}

			if (StringUtils.isNotBlank(is_temporary) && "1".equals(is_temporary)) { // 暂存
				ks.setState(0);
			} else { // 正常 提交
				ks.setState(1);
			}
			ks.setSell_date(sdf.parse(sell_date_temp));
			// init data end.

			super.getFacade().getKonkaSellService().createKonkaSellIncludeDetails(ks);

			saveMessage(request, "entity.inserted");
		} else { // update

			// init data start...
			String title = r3Shop.getCustomer_name() + "   " + sell_date_temp;
			ks.setTitle(title);
			if (StringUtils.isNotBlank(is_temporary) && "1".equals(is_temporary)) { // 暂存
				ks.setState(0);
			} else {
				ks.setState(1);
			}
			ks.setSell_date(sdf.parse(sell_date_temp));

			super.getFacade().getKonkaSellService().modifyKonkaSellIncludeDetails(ks);

			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append("/admin/KonkaSell.do?method=sell");
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(ks.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String s_id = (String) dynaBean.get("s_id");
		String r3_shop_id = (String) dynaBean.get("r3_shop_id");

		if (!GenericValidator.isLong(s_id)) {
			saveError(request, "errors.long", new String[] { s_id });
			return mapping.findForward("list");
		}
		KonkaR3Shop r3Shop = new KonkaR3Shop();
		r3Shop.setId(Long.valueOf(r3_shop_id));
		r3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3Shop);
		request.setAttribute("r3Shop", r3Shop);

		// 取销售记录信息
		KonkaSell entity = new KonkaSell();
		entity.setS_id(Long.valueOf(s_id));
		entity = super.getFacade().getKonkaSellService().getKonkaSell(entity);

		// 取销售记录详细信息
		KonkaSellDetails ksd = new KonkaSellDetails();
		ksd.setS_id(entity.getS_id());
		List<KonkaSellDetails> ksdList = super.getFacade().getKonkaSellDetailsService().getKonkaSellDetailsList(ksd);
		String[] selected_pd_ids = new String[ksdList.size()];
		for(int t =0;t<ksdList.size();t++){
			selected_pd_ids[t] = ksdList.get(t).getPd_id().toString();
		}
		dynaBean.set("selected_pd_ids", StringUtils.join(selected_pd_ids,","));
		entity.setKonkaSellDetailsList(ksdList);

		// 取分配给自己所在分公司的产品型号
		request.setAttribute("kpmList", super.getDeptLinkProduct(request, response, null, null, null,null));

		entity.setQueryString(super.serialize(request, "s_id", "method"));
		super.copyProperties(form, entity);

		dynaBean.set("now_date", new Date());
		if (null != entity.getSell_date()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dynaBean.set("sell_date_temp", sdf.format(entity.getSell_date()));
		}
		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String s_id = (String) dynaBean.get("s_id");
		String r3_shop_id = (String) dynaBean.get("r3_shop_id");
		String r3_code = (String) dynaBean.get("r3_code");
		if (!GenericValidator.isLong(s_id)) {
			saveError(request, "errors.long", new String[] { s_id });
			return mapping.findForward("list");
		}

		KonkaR3Shop r3Shop = new KonkaR3Shop();
		if (GenericValidator.isLong(r3_shop_id)) {
			r3Shop.setId(Long.valueOf(r3_shop_id));
		}
		if (StringUtils.isNotBlank(r3_code)) {
			r3Shop.setR3_code(r3_code);
		}

		r3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3Shop);

		// 取销售记录信息
		KonkaSell entity = new KonkaSell();
		entity.setS_id(Long.valueOf(s_id));
		entity = super.getFacade().getKonkaSellService().getKonkaSell(entity);

		// 取销售记录详细信息
		KonkaSellDetails ksd = new KonkaSellDetails();
		ksd.setS_id(entity.getS_id());
		List<KonkaSellDetails> ksdList = super.getFacade().getKonkaSellDetailsService().getKonkaSellDetailsList(ksd);
		entity.setKonkaSellDetailsList(ksdList);

		Long sell_count_totle = 0L;
		BigDecimal sell_cost_totle = new BigDecimal(0);

		// 取分配给自己所在分公司的产品型号
		List<PePdModel> kpmList = super.getDeptLinkProduct(request, response, null, null, null,null);
		request.setAttribute("kpmList", kpmList);

		for (PePdModel i : kpmList) {
			for (KonkaSellDetails t : ksdList) {
				if (i.getPd_id().equals(t.getPd_id())) {
					sell_count_totle = sell_count_totle + t.getSell_count();
					sell_cost_totle = sell_cost_totle
							.add(new BigDecimal(t.getSell_count()).multiply(t.getSell_money()));
				}
			}
		}
		dynaBean.set("sell_count_totle", sell_count_totle);
		dynaBean.set("sell_cost_totle", sell_cost_totle);
		super.copyProperties(form, entity);

		// KONKA_R3_SHOP
		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setR3_code(entity.getCus_sn());
		konkaR3Shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
		if (null != konkaR3Shop) {
			dynaBean.set("cus_name", konkaR3Shop.getCustomer_name());
		}

		return mapping.findForward("view");
	}

	public ActionForward totle(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
//		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

//		PeRoleUser role = new PeRoleUser();
//		role.setUser_id(ui.getId());
//		role = getFacade().getPeRoleUserService().getPeRoleUser(role);

		List<PePdModel> kpmList = new ArrayList<PePdModel>();
		// 取分配给自己所在分公司的产品型号
		List<PePdModel> list = super.getDeptLinkProduct(request, response, null, null, null,null);

		for (PePdModel t : list) {
			KonkaSell sell = new KonkaSell();
			sell.setState(1);
			sell.getMap().put("pd_id", t.getPd_id());
//			if (role.getRole_id() >= 30 && role.getRole_id() <= 39) {// 分公司登录
//				sell.getMap().put("is_leader_dept_id", ui.getDept_id());
//			}
//			if (role.getRole_id() >= 40 && role.getRole_id() <= 59) {// 经营部办事处登录
//				sell.setAdd_dept_id(ui.getDept_id());
//			}
//			if (role.getRole_id() == 60) {// 业务员
//				sell.setAdd_user_id(ui.getId());
//			}

			KonkaSellDetails sellDetails = new KonkaSellDetails();
			sellDetails.setPd_id(t.getPd_id());
			List<KonkaSellDetails> sellDetailsList = getFacade().getKonkaSellDetailsService().getKonkaSellDetailsList(
					sellDetails);
			if (sellDetailsList.size() != 0) {
				Long sellCountForPd = 0L;
				BigDecimal sellCostForPd = new BigDecimal(0);
				if(null !=getFacade().getKonkaSellService().getKonkaSellCountByPd(sell)){
					sellCountForPd = getFacade().getKonkaSellService().getKonkaSellCountByPd(sell);
				}
				if(null !=getFacade().getKonkaSellService().getKonkaSellCountCostByPd(sell)){
					sellCostForPd = getFacade().getKonkaSellService().getKonkaSellCountCostByPd(sell);
				}
				PePdModel entity = new PePdModel();
				entity.setMd_name(t.getMd_name());
				entity.getMap().put("sellCountForPd", sellCountForPd);
				entity.getMap().put("sellCostForPd", sellCostForPd);
				kpmList.add(entity);
			}
		}
		Long sellCountTotle = 0L;
		BigDecimal sellCostTotle = new BigDecimal(0);

		for (PePdModel i : kpmList) {
			sellCountTotle = sellCountTotle + Long.valueOf(i.getMap().get("sellCountForPd").toString());
			sellCostTotle = sellCostTotle.add(new BigDecimal(i.getMap().get("sellCostForPd").toString()));
		}
		dynaBean.set("sellCountTotle", sellCountTotle);
		dynaBean.set("sellCostTotle", sellCostTotle);
		request.setAttribute("kpmList", kpmList);
		return new ActionForward(response.encodeRedirectURL("/admin/KonkaSell/totle.jsp"));
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String s_id = (String) dynaBean.get("s_id");
		String[] pks = (String[]) dynaBean.get("pks");

		if (!StringUtils.isBlank(s_id) && GenericValidator.isLong(s_id)) {
			KonkaSell entity = new KonkaSell();
			entity.setS_id(Long.valueOf(s_id));
			super.getFacade().getKonkaSellService().removeKonkaSell(entity);
		} else {
			KonkaSell entity = new KonkaSell();
			entity.getMap().put("pks", pks);
			super.getFacade().getKonkaSellService().removeKonkaSell(entity);
		}

		saveMessage(request, "entity.deleted");

		return this.sell(mapping, form, request, response);
	}
	
	public ActionForward getPePdModel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String pd_ids = (String) dynaBean.get("pd_ids");
		String selected_pd_ids = (String) dynaBean.get("selected_pd_ids");
		
		String[] pd_ids_array = pd_ids.split(",");
		
		if(StringUtils.isNotBlank(selected_pd_ids)){//将已经在页面上显示的，也就是暂存时上报了的产品从新增的列表中剔除掉
			String[] selected_pd_ids_array = selected_pd_ids.split(",");
			if(StringUtils.isNotBlank(pd_ids)){
				for(int i =0 ;i<selected_pd_ids_array.length;i++){
					for(int j=0;j<pd_ids_array.length;j++){
						if(pd_ids_array[j].equals(selected_pd_ids_array[i])){
							pd_ids_array[j]="0";
						}
					}
				}
			}
		}
		String new_pd_ids = StringUtils.join(pd_ids_array,",");
		
		PePdModel entity = new PePdModel();
		if(StringUtils.isNotBlank(new_pd_ids)){
			entity.getMap().put("selects", new_pd_ids);
			entity.setIs_del(0);
			List<PePdModel> entityList = getFacade().getPePdModelService().getPePdModelList(entity);
			String ret_tr = toTable(entityList);
			log.info("===========================sb:"+ ret_tr);
			super.renderText(response, ret_tr);
		}
		return null;
	}
	
	public ActionForward getLastSellRecord(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String r3_shop_id = (String) dynaBean.get("r3_shop_id");
//		HttpSession session = request.getSession();
		// 获取业务员信息
//		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		KonkaR3Shop r3Shop = new KonkaR3Shop();
		r3Shop.setId(Long.valueOf(r3_shop_id));
		r3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3Shop);

		// 获取最后一次上报的销售记录
		KonkaSell entity = new KonkaSell();
		entity.setIs_del(0);
		entity.setCus_sn(r3Shop.getR3_code());
//		entity.setAdd_user_id(ui.getId());
		List<KonkaSell> konkaSellList = super.getFacade().getKonkaSellService().getKonkaSellList(entity);

		if(konkaSellList != null && konkaSellList.size() > 0){
			KonkaSell ks = konkaSellList.get(0);
			// 取最后一次销售记录详细信息
			KonkaSellDetails ksd = new KonkaSellDetails();
			ksd.setS_id(ks.getS_id());
			List<KonkaSellDetails> ksdList = super.getFacade().getKonkaSellDetailsService().getKonkaSellDetailsList(ksd);
			String[] selected_pd_ids = new String[ksdList.size()];
			for(int t =0;t<ksdList.size();t++){
				selected_pd_ids[t] = ksdList.get(t).getPd_id().toString();
			}
			dynaBean.set("pd_ids", StringUtils.join(selected_pd_ids,","));
			
			getPePdModel(mapping, form, request, response);
		}
		return null;
	}

	public String toTable(List<PePdModel> entityList) throws Exception {
		StringBuffer sb = new StringBuffer();
		if (null != entityList && entityList.size() > 0) {
			for (PePdModel temp : entityList) {
				sb.append("<tr>");
				sb.append("<td align=\"left\">").append(temp.getMd_name()).append("</td>");
				sb.append("<td align=\"left\">销售数量：<input type=\"text\" name=\"").append(temp.getPd_id()).append("\" id=\"").append(temp.getPd_id()).append("\" size=\"10\" onblur=\"getCalculateAmount(\'").append(temp.getPd_id()).append("\',\'money_").append(temp.getPd_id()).append("\',\'total_").append(temp.getPd_id()).append("\');\" onkeyup=\"javascript:setOnlyNum(this);\" maxlength=\"15\" /><span style=\"color:#666;\">台</span>  ");
				sb.append("销售单价：<input type=\"text\" name=\"money_").append(temp.getPd_id()).append("\" id=\"money_").append(temp.getPd_id()).append("\" size=\"10\" onblur=\"getCalculateAmount(\'").append(temp.getPd_id()).append("\',\'money_").append(temp.getPd_id()).append("\',\'total_").append(temp.getPd_id()).append("\');\" onkeyup=\"javascript:setOnlyNum(this);\" maxlength=\"10\" /><span style=\"color:#666;\"> 元</span>  ");
				sb.append("销售总价：<input type=\"text\" name=\"total_").append(temp.getPd_id()).append("\" id=\"total_").append(temp.getPd_id()).append("\" size=\"10\" onkeyup=\"javascript:setOnlyDouble(this);\" maxlength=\"15\"  disabled=\"disabled\" /> <span style=\"color:#666;\"> 元</span></td>");
				sb.append("</tr>");
			}
		}
		return sb.toString();
	}

}