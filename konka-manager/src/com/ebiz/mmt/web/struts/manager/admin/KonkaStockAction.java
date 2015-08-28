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

import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaStockDetails;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wang,Yang
 * @version 2011-11-09
 */
public class KonkaStockAction extends BaseAction {

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
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		KonkaR3Shop entity = super.getKonkaR3ShopForStockSell(mapping, form, request, response, ui.getDept_id());
		entity.setIs_match(null);

		Long recordCount = getFacade().getKonkaR3ShopService().getKonkaR3ShopCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService().getKonkaR3ShopPaginatedList(entity);

		super.setBranchNameForR3ShopListByKonkaR3ShopList(entityList);

		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String key = (String)dynaBean.get("key");
		String mod_id = (String)dynaBean.get("mod_id");
		String stock_date = (String) dynaBean.get("stock_date");
		String queryString = (String) dynaBean.get("queryString");
		KonkaR3Shop r3Shop = (KonkaR3Shop) request.getSession().getAttribute("r3");
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		
		if(StringUtils.isNotBlank(key)){
			if(StringUtils.isNotBlank(stock_date)){
				KonkaStockDetails details = new KonkaStockDetails();
				details.setR3_code(r3Shop.getR3_code());
				List<KonkaStockDetails> detailsList = getFacade().getKonkaStockDetailsService().getKonkaStockDetailsList(details);
				if(detailsList.size()!=0){
					for(KonkaStockDetails t :detailsList){
						t.setStock_date(s.parse(stock_date));
						getFacade().getKonkaStockDetailsService().modifyKonkaStockDetails(t);
					}
				}
			}
		}

		// 取所有型号对应的值
		List<PePdModel> kpmList = getDeptLinkProduct(request, response, null, null, null,null);

		String stock_count, stock_cost;

		List<KonkaStockDetails> ksdList = new ArrayList<KonkaStockDetails>();

		for (PePdModel pePdModel : kpmList) {
			KonkaStockDetails temp = new KonkaStockDetails();

			stock_count = (String) dynaBean.get(pePdModel.getPd_id().toString());
			stock_cost = (String) dynaBean.get(pePdModel.getPd_id().toString() + "_1");
			if (GenericValidator.isLong(stock_count) && GenericValidator.isDouble(stock_cost)) {
				temp.setPd_id(pePdModel.getPd_id());
				temp.setR3_code(r3Shop.getR3_code());
				temp.setRegulation(0L);
				temp.setCurrent_cost(new BigDecimal(0));
				temp.setCurrent_count(0L);
				if (!GenericValidator.isLong(stock_count)) {
					temp.setStock_count(0L);
				} else {
					temp.setStock_count(Long.valueOf(stock_count));
				}
				if (!GenericValidator.isDouble(stock_cost)) {
					temp.setStock_cost(new BigDecimal(0));
				} else {
					temp.setStock_cost(new BigDecimal(stock_cost));
				}
				if (StringUtils.isNotBlank(stock_date)) {
					temp.setStock_date(s.parse(stock_date));
				} else {
					temp.setStock_date(new Date());
				}
				ksdList.add(temp);
			}
		}

		getFacade().getKonkaStockDetailsService().createKonkaStockDetailsWithR3Code(ksdList);
		if(StringUtils.isBlank(key)){
			saveMessage(request, "entity.inserted");
		}else{
			saveMessage(request, "entity.updated");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(queryString));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean)form;
		super.getModPopeDom(form, request);
		
		String r3_shop_id = (String) dynaBean.get("r3_shop_id");

		HttpSession session = request.getSession();
		
		if (!GenericValidator.isLong(r3_shop_id)) {
			this.saveError(request, "errors.long", new String[] { r3_shop_id });
			return mapping.findForward("list");
		}

		KonkaR3Shop r3Shop = new KonkaR3Shop();
		r3Shop.setId(Long.valueOf(r3_shop_id));
		r3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3Shop);
		request.setAttribute("r3Shop", r3Shop);
		session.setAttribute("r3", r3Shop);
		
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		String today = s.format(new Date());
		
		PeProdUser ui = (PeProdUser)request.getSession().getAttribute(Constants.USER_INFO);
		
		KonkaStockDetails details = new KonkaStockDetails();
		details.setR3_code(r3Shop.getR3_code());
		List<KonkaStockDetails> detailsList = getFacade().getKonkaStockDetailsService().getKonkaStockDetailsList(
				details);
		details.setQueryString(super.serialize(request, "id", "mod_id", "tree_param"));
		super.copyProperties(form, details);
		
		PePdModel model = new PePdModel();
		model.getMap().put("keyword", r3Shop.getR3_code());
		List<PePdModel> pePdList = getFacade().getKonkaRealTimeStockService().getIsUseProductByKeyword(model);//查询此网点有入库和销售的所有型号
		
		request.setAttribute("detailsList", detailsList);
		if(detailsList.size()!=0){
			if(pePdList.size()!=0){
				for(KonkaStockDetails t : detailsList){
					for(int i=0 ;i< pePdList.size();i++){
						if(t.getPd_id().equals(pePdList.get(i).getPd_id())){
							pePdList.remove(i);
						}
					}
				}
			}
			
			dynaBean.set("stock_date", s.format(detailsList.get(0).getStock_date()));
			
			PeRoleUser _peRoleUser = new PeRoleUser();
			_peRoleUser.setUser_id(ui.getId());
			List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

			boolean role_id_eq_30 = false;
			for (PeRoleUser peRoleUser: peRoleUserList) {
				if (peRoleUser.getRole_id() == 30L) {
					role_id_eq_30 = true;
				}
			}
			
			if(role_id_eq_30){//分公司管理员
				request.setAttribute("key", 1);//管理员可以修改日期
			}
		}else if(detailsList.size()==0){//此网点未进行过初始化
			dynaBean.set("stock_date", today);
			request.setAttribute("key", 1);//业务员第一次上报时可以选择日期
		}
		String[] pd_idsArray = new String[pePdList.size()];
		if(pePdList.size()!=0){
			int i=0;
			for(PePdModel t : pePdList){
				pd_idsArray[i]= t.getPd_id().toString();
				i++;
			}
			request.setAttribute("pePdList", pePdList);
			dynaBean.set("pd_ids", StringUtils.join(pd_idsArray,","));
		}

		return mapping.findForward("input");
	}

	public ActionForward getPePdModel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		KonkaR3Shop r3Shop = (KonkaR3Shop) request.getSession().getAttribute("r3");

		String pd_ids = (String) dynaBean.get("pd_ids");

		PePdModel entity = new PePdModel();
		if (StringUtils.isNotBlank(pd_ids)) {
			entity.getMap().put("keyword", r3Shop.getR3_code());
			entity.getMap().put("pd_ids", pd_ids);
			entity.setIs_del(0);
			List<PePdModel> entityList = getFacade().getKonkaRealTimeStockService()
					.getPePdModelWithStockDetails(entity);
			String ret_tr = toTable(entityList);
			log.info("===========================sb:" + ret_tr);
			super.renderText(response, ret_tr);
		}
		return null;
	}

	public String toTable(List<PePdModel> entityList) throws Exception {
		StringBuffer sb = new StringBuffer();
		if (null != entityList && entityList.size() > 0) {
			for (PePdModel temp : entityList) {
					if (null == temp.getMap().get("stock_count") && null == temp.getMap().get("stock_cost")) {
						sb.append("<tr>");
						sb.append("<td align=\"left\">").append(temp.getMd_name()).append("</td>");
						sb.append("<td align=\"left\"><input type=\"text\" name=\"")
								.append(temp.getPd_id())
								.append("\" id=\"")
								.append(temp.getPd_id())
								.append("\" size=\"10\"  onkeyup=\"javascript:setOnlyNum(this);\" maxlength=\"15\" /><span style=\"color:#666;\">台</span>  ")
								.append("</td>");
						sb.append("<td align=\"left\"><input type=\"text\" name=\"")
								.append(temp.getPd_id())
								.append("_1\" id=\"")
								.append(temp.getPd_id())
								.append("_1\" size=\"10\"  onkeyup=\"javascript:setOnlyDouble(this);\" maxlength=\"8\" /><span style=\"color:#666;\">元</span>  ")
								.append("</td>");
						sb.append("<td></td>");
						sb.append("</tr>");
					} else if (null != temp.getMap().get("stock_count") && null != temp.getMap().get("stock_cost")) {

					}
			}
		}
		log.info(sb.toString());
		return sb.toString();
	}

	public ActionForward toEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		KonkaR3Shop r3Shop = (KonkaR3Shop) request.getSession().getAttribute("r3");
		String id = (String) dynaBean.get("id");
		KonkaStockDetails stock = new KonkaStockDetails();
		stock.setR3_code(r3Shop.getR3_code());
		stock.setId(Long.valueOf(id));
		stock = getFacade().getKonkaStockDetailsService().getKonkaStockDetails(stock);
		request.setAttribute("r3Shop", r3Shop);
		request.setAttribute("stock", stock);
		return new ActionForward(response.encodeRedirectURL("/admin/KonkaStock/toEdit.jsp"));
	}

	public ActionForward editSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// DynaBean dynaBean = (DynaBean) form;
		KonkaStockDetails stockDetails = new KonkaStockDetails();
		super.copyProperties(stockDetails, form);
		getFacade().getKonkaStockDetailsService().modifyKonkaStockDetails(stockDetails);
		return this.edit(mapping, form, request, response);
	}
}