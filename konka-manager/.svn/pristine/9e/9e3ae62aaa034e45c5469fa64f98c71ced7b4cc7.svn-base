package com.ebiz.mmt.web.struts.manager.admin;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.KonkaPeArticleInfo;
import com.ebiz.mmt.domain.KonkaPePublicClick;
import com.ebiz.mmt.domain.KonkaPePublicScope;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeShopCategory;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @desc 加盟跟进 -- 查看加盟信息点击情况
 * @author Dou，QingRen
 * @datetime 2011-10-12 10:05:13
 */
public class JoinInfoFollowAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}


	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);
		
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		
		Pager pager = (Pager) dynaBean.get("pager");
		String title = (String) dynaBean.get("title");
		String st_add_date = (String) dynaBean.get("st_add_date");
		String en_add_date = (String) dynaBean.get("en_add_date");
		
		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
		
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		entity.setAdd_user_id(peProdUser.getId());  //只能看见自己添加的记录
		
		entity.setTitle(title);
		entity.setArticle_mod_id(Long.valueOf(301100)); //加盟信息菜单的id
		
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
	
	/*
	 * 点击过该加盟信息的网点列表
	 */
	public ActionForward visitList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String article_id = (String) dynaBean.get("article_id");
		String shop_agree = (String) dynaBean.get("shop_agree");
		
		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
		entity.setId(Long.valueOf(article_id));
		entity = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfo(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		
		KonkaPePublicClick konkaPePublicClick = new KonkaPePublicClick();
		konkaPePublicClick.setArticle_id(Long.valueOf(article_id));
		if(StringUtils.isNotBlank(shop_agree)){
			konkaPePublicClick.setShop_agree(Long.valueOf(shop_agree));
		}
		List<KonkaPePublicClick> list = getFacade().getKonkaPePublicClickService().getKonkaPePublicClickList(konkaPePublicClick);
		request.setAttribute("entityList", list);
		
		return new ActionForward("/admin/JoinInfoFollow/visitList.jsp");
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

			//回显 发布对象信息
			KonkaPePublicScope konkaPePublicScope = new KonkaPePublicScope();
			konkaPePublicScope.setArticle_id(Long.valueOf(id));
			List<KonkaPePublicScope> list = super.getFacade().getKonkaPePublicScopeService().getKonkaPePublicScopeList(konkaPePublicScope);
			if(list.size()>0){
				for(KonkaPePublicScope temp : list){
					if(temp.getPublic_type() == 0L){
						dynaBean.set("public_type","0"); //所有网点
					}else if(temp.getPublic_type() == 1l){
						BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
						baseProvinceListFour.setP_index(temp.getPublic_scope());
						baseProvinceListFour = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvinceListFour);
						
						if (null != baseProvinceListFour) {
							String full_name = baseProvinceListFour.getFull_name();
							dynaBean.set("full_name",full_name);
						}
					}else if(temp.getPublic_type() == 2l){
						dynaBean.set("shop_category_id",temp.getPublic_scope());
					}
//					else if(temp.getPublic_type() == 3l){
//						dynaBean.set("shop_id",temp.getPublic_scope());
//					}
				}
			}

			// 网点类别列表
			PeShopCategory peShopCategory = new PeShopCategory();
			peShopCategory.setIs_del(0l);
			List<PeShopCategory> peShopCategoryList = super.getFacade().getPeShopCategoryService()
					.getPeShopCategoryList(peShopCategory);
			request.setAttribute("peShopCategoryList", peShopCategoryList);
			
			// super.copyProperties(form, entity);
			request.setAttribute("entity", entity);
			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}

}