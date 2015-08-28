package com.ebiz.mmt.web.struts.manager.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseProvinceList;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.MmtEntpShop;
import com.ebiz.mmt.domain.KonkaPeArticleInfo;
import com.ebiz.mmt.domain.KonkaPePublicScope;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @desc 加盟信息管理
 * @author Dou，QingRen
 * @datetime 2011-10-11 16:05:13
 */
public class JoinInfoManagerAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}

		saveToken(request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		
		//判断当前用户是否是分公司
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(ui.getDept_id());
		konkaDept = getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		if(konkaDept != null && konkaDept.getDept_type() == 3){ //分公司
			dynaBean.set("many_p_index", konkaDept.getM_areas_ids()); //分公司管辖范围
			dynaBean.set("disabled", "1"); //分公司不可以选择所有网点 
		}
		request.setAttribute("peRoleUser",  super.getRoleInfoByThisLogin(request));
		
		// 取得baseProvinceList省的值
		BaseProvinceList Province = new BaseProvinceList();
		Province.setDel_mark(new Short("0"));
		Province.setP_level(new Short("1"));
		List<BaseProvinceList> baseProvince1List = getFacade().getBaseProvinceListService().getBaseProvinceListList(
				Province);
		request.setAttribute("baseProvince1List", baseProvince1List);	


		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		String mod_code = (String) dynaBean.get("mod_id");
		String title = (String) dynaBean.get("title");
		String st_add_date = (String) dynaBean.get("st_add_date");
		String en_add_date = (String) dynaBean.get("en_add_date");

		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
		
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		entity.setAdd_user_id(peProdUser.getId());  //只能看见自己添加的记录
		
		entity.setTitle(title);
		entity.setArticle_mod_id(Long.valueOf(mod_code));
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtils.isNotBlank(st_add_date)){
			entity.getMap().put("st_add_date", simpleDateFormat.parse(st_add_date));
		}
		if(StringUtils.isNotBlank(en_add_date)){
			entity.getMap().put("en_add_date", simpleDateFormat.parse(en_add_date));
		}
		
		Long recordCount = getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfoCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaPeArticleInfo> entityList = getFacade().getKonkaPeArticleInfoService()
				.getKonkaPeArticleInfoPaginatedList(entity);
		request.setAttribute("entityList", entityList);


		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfo(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		// the line below is added for pagination
		entity.setQueryString(super.serialize(request, "id", "method"));
		// end

		super.copyProperties(form, entity);
		
		request.setAttribute("peRoleUser",  super.getRoleInfoByThisLogin(request));
		
		if(entity.getArticle_type_id() == 3){ //0所有 ，1地区  ，2网点 ，3省
			dynaBean.set("province", entity.getPub_p_index());
		}else if(entity.getArticle_type_id() == 1){
			dynaBean.set("province", entity.getPub_p_index());
			dynaBean.set("areas_ids", entity.getLink_out_addr());
			dynaBean.set("areas_names", entity.getSummary());
		}else if(entity.getArticle_type_id() == 2){
			dynaBean.set("province", entity.getPub_p_index());
			dynaBean.set("areas_ids", entity.getLink_out_addr());
			dynaBean.set("areas_names", entity.getSummary());
			dynaBean.set("shop_id", entity.getMmt_audit_desc());
			
			//显示5个以内网点名称
			KonkaPePublicScope konkaPePublicScope = new KonkaPePublicScope();
			konkaPePublicScope.setArticle_id(Long.valueOf(id));
			List<KonkaPePublicScope> list = super.getFacade().getKonkaPePublicScopeService().getKonkaPePublicScopeList(konkaPePublicScope);
			StringBuffer sb = new StringBuffer();
			for(int i=0 ;i<(list.size()>5?5:list.size());i++){//若网点超过五个只取五个名称
				KonkaPePublicScope temp = list.get(i);
				if(i==0){
					sb.append(temp.getPublic_scope());
				}else
					sb.append(",").append(temp.getPublic_scope());
			}
			MmtEntpShop konkaEntpShop = new MmtEntpShop();
			konkaEntpShop.getMap().put("selects", sb.toString());
			List<MmtEntpShop> konkaEntpShop_list = getFacade().getMmtEntpShopService().getMmtEntpShopList(konkaEntpShop);
			sb = new StringBuffer();
			for(MmtEntpShop shop : konkaEntpShop_list){
				sb.append(shop.getShop_name()).append(",");
			}
			dynaBean.set("shop_name", sb.toString());
		}else if(entity.getArticle_type_id() == 0){
			dynaBean.set("public_type", "0");
		}
		
		//判断当前用户是否是分公司
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(ui.getDept_id());
		konkaDept = getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		if(konkaDept != null && konkaDept.getDept_type() == 3){ //分公司
			dynaBean.set("many_p_index", konkaDept.getM_areas_ids()); //分公司管辖范围
			dynaBean.set("disabled", "1"); //分公司不可以选择所有网点 
		}
		
		// 取得baseProvinceList省的值
		BaseProvinceList Province = new BaseProvinceList();
		Province.setDel_mark(new Short("0"));
		Province.setP_level(new Short("1"));
		List<BaseProvinceList> baseProvince1List = getFacade().getBaseProvinceListService().getBaseProvinceListList(
				Province);
		request.setAttribute("baseProvince1List", baseProvince1List);	

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);
		if (isCancelled(request)) {
			return list(mapping, form, request, response);
		}
		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}
		resetToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String province = (String) dynaBean.get("province");  //
		String areas_ids = (String) dynaBean.get("areas_ids"); //
		String areas_names = (String) dynaBean.get("areas_names"); //
		String shop_id = (String) dynaBean.get("shop_id"); //
		String public_type = (String) dynaBean.get("public_type"); //发布类型

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
		super.copyProperties(entity, form);
		
		entity.setArticle_mod_id(Long.valueOf(mod_id));
		
		//以下为数据库必填项，无具体意义
		entity.setIs_top(0l);
		entity.setIs_link_out(0l);
		entity.setIs_display_index(0l);
		entity.setStates(0l);
		
		entity.setLink_out_addr(areas_ids);//存放地区id串 和 地区名串
		entity.setSummary(areas_names);
		if(StringUtils.isNotBlank(province)){
			entity.setPub_p_index(Long.valueOf(province)); //省代码
		}
		entity.setMmt_audit_desc(shop_id); //网点id串
		
		Long type = -1l ;  //在加盟信息表中，使用article_type_id表示发布对象类型： 0所有 ，1地区  ，2网点 ，3省
		if("1".equals(public_type)){//非所有网点发布
			if(StringUtils.isNotBlank(shop_id)){  //选择了网点 就以网点为准
				type = 2l;
			}else if(StringUtils.isNotBlank(areas_ids)){ //网点为空，以地区为准
				type = 1l;
			}else if(StringUtils.isNotBlank(province)){ //网点和地区均为空，则以省为准
				type = 3l;
			}else
				type = 4l; //未知
		}else{
			type = 0l; //所有网点
		}
		entity.setArticle_type_id(type);
		
		entity.getMap().put("public_type",public_type );
		entity.getMap().put("shop_id", shop_id);
		entity.getMap().put("areas_ids",areas_ids );
		entity.getMap().put("province",province );
		
		if (null == entity.getId()) {// insert
			entity.setAdd_user_id(peProdUser.getId());
			entity.setAdd_user_name(peProdUser.getUser_name());
			entity.setView_counts(0l);
			entity.setIs_del(0l);
		    
			getFacade().getKonkaPeArticleInfoService().createKonkaPeArticleInfoForJoinInfo(entity);//加盟信息新增
			
			saveMessage(request, "entity.inserted");
		} else if (null != entity) {// update
			getFacade().getKonkaPeArticleInfoService().modifyKonkaPeArticleInfoForJoinInfo(entity);//加盟信息修改
			
			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {

			KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
			entity.setId(Long.valueOf(id));
			entity = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfo(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			//回显 发布对象信息,Article_type_id标明发布对象类型： 0所有 ，1地区  ，2网点 ，3省
			KonkaPePublicScope konkaPePublicScope = new KonkaPePublicScope();
			konkaPePublicScope.setArticle_id(Long.valueOf(id));
			List<KonkaPePublicScope> list = super.getFacade().getKonkaPePublicScopeService().getKonkaPePublicScopeList(konkaPePublicScope);
			request.setAttribute("scopeList", list);
			dynaBean.set("article_type_id", entity.getArticle_type_id());
			
			if(entity.getArticle_type_id() == 0){
				
			}else if(entity.getArticle_type_id() == 3){//省
				BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
				baseProvinceListFour.setP_index(list.get(0).getPublic_scope());
				baseProvinceListFour = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(baseProvinceListFour);
				dynaBean.set("province",baseProvinceListFour==null ?"": baseProvinceListFour.getFull_name());
			}else if(entity.getArticle_type_id() == 1){//地区
				StringBuffer sb = new StringBuffer();
				for(int i=0 ;i<list.size();i++){
					KonkaPePublicScope temp = list.get(i);
					if(i==0){
						sb.append(temp.getPublic_scope());
					}else
						sb.append(",").append(temp.getPublic_scope());
				}	
				BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
				baseProvinceListFour.getMap().put("many_p_index", sb.toString());
				List<BaseProvinceListFour> baseProvinceListFour_list = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFourList(baseProvinceListFour);
				sb = new StringBuffer();
				for(BaseProvinceListFour city : baseProvinceListFour_list){
					sb.append(city.getFull_name()).append("&nbsp;");
				}
				dynaBean.set("citys", sb.toString());
			}else if(entity.getArticle_type_id() == 2){//网点
				StringBuffer sb = new StringBuffer();
				for(int i=0 ;i<list.size();i++){
					KonkaPePublicScope temp = list.get(i);
					if(i==0){
						sb.append(temp.getPublic_scope());
					}else
						sb.append(",").append(temp.getPublic_scope());
				}
				MmtEntpShop konkaEntpShop = new MmtEntpShop();
				konkaEntpShop.getMap().put("selects", sb.toString());
				List<MmtEntpShop> konkaEntpShop_list = getFacade().getMmtEntpShopService().getMmtEntpShopList(konkaEntpShop);
				sb = new StringBuffer();
				for(MmtEntpShop shop : konkaEntpShop_list){
					sb.append(shop.getShop_name()).append("<br />");
				}
				dynaBean.set("shops", sb.toString());
			}
			request.setAttribute("entity", entity);
			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		PeProdUser PeProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
			entity.setId(new Long(id));
			entity.setDel_date(new Date());
			entity.setIs_del((long) 1);
			entity.setDel_user_id(PeProdUser.getId());
			super.getFacade().getKonkaPeArticleInfoService().removeKonkaPeArticleInfo(entity);
			
			//删除 发布表内容
			KonkaPePublicScope konkaPePublicScope = new KonkaPePublicScope();
			konkaPePublicScope.setArticle_id(new Long(id));
			getFacade().getKonkaPePublicScopeService().removeKonkaPePublicScope(konkaPePublicScope);
			
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
			entity.setDel_date(new Date());
			entity.setDel_user_id(PeProdUser.getId());
			entity.setIs_del((long) 1);
			entity.getMap().put("pks", pks);
			super.getFacade().getKonkaPeArticleInfoService().removeKonkaPeArticleInfo(entity);
			
			//删除 发布表内容
			for(int i=0;i<pks.length;i++){
				if(StringUtils.isNotBlank(pks[i])){
					KonkaPePublicScope konkaPePublicScope = new KonkaPePublicScope();
					konkaPePublicScope.setArticle_id(new Long(pks[i]));
					getFacade().getKonkaPePublicScopeService().removeKonkaPePublicScope(konkaPePublicScope);
				}
			}
			
			saveMessage(request, "entity.deleted");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "pks", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end

		return forward;
	}
}