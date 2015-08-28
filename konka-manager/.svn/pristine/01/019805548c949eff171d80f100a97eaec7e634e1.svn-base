package com.ebiz.mmt.web.struts.jxcnokey;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.KonkaBranchCategory;
import com.ebiz.mmt.domain.KonkaPeArticleInfo;
import com.ebiz.mmt.domain.KonkaPeArticleType;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.UserInfo;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wu,Yang
 * @version 2011-10-19
 */
@SuppressWarnings("unused")
public class JxcReceiveInfoAction extends BaseJxcAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// infoType=zxxx资讯信息 infoType=znx站内信息infoType=xcpxx新品信息
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String infoType = (String) dynaBean.get("infoType");
		String title = (String) dynaBean.get("title");
		String st_pub_date = (String) dynaBean.get("st_pub_date");
		String en_pub_date = (String) dynaBean.get("en_pub_date");
		Pager pager = (Pager) dynaBean.get("pager");

		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
		super.copyProperties(entity, form);
		entity.getMap().put("title", title);
		entity.getMap().put("st_pub_date", st_pub_date);
		entity.getMap().put("en_pub_date", en_pub_date);
		entity.setIs_del(0l);
		entity.setStates(1l);// 已发布
		entity.setTitle(title);
		// entity.setMmt_audit(1l);// 已审核

		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}

		BaseProvinceListFour bplf = new BaseProvinceListFour();
		bplf.setP_index(new Long(super.getStdEntpMainByShopId(user.getEntp_shop().getShop_id()).getP_index()));
		bplf = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(bplf);
		if (null == bplf) {
			super.invalidOper(request, response, "errors.parm");
			return null;
		}

		KonkaBranchCategory konkaBranchCategory = new KonkaBranchCategory();
		konkaBranchCategory.setCategory_id(20l);
		konkaBranchCategory.setMmt_shop_id(user.getEntp_shop().getShop_id());
		List<KonkaBranchCategory> konkaBranchCategoryList = getFacade().getKonkaBranchCategoryService()
				.getKonkaBranchCategoryList(konkaBranchCategory);
		// 如果不是经销商那就是r3或代理商，但登录这系统只有r3和经销商，所以就是r3用户咯
		if (null != konkaBranchCategoryList && konkaBranchCategoryList.size() > 0) {// 经销商
			entity.getMap().put("public_target", "2");
		} else {// R3网点
			entity.getMap().put("public_target", "0");
		}

		entity.getMap().put("par_index", bplf.getP_index());
		entity.getMap().put("shop_id", user.getEntp_shop().getShop_id());// 网点类别
		entity.getMap().put("public_place_lt", 1);// 投放位置是全部或康佳专版的

		if ("zxxx".equals(infoType)) {
			entity.getMap().put("mod_id_in", "401010,403010");// 查询R3或经销商资讯
		} else if ("xcpxx".equals(infoType)) {
			entity.setArticle_mod_id(401020l);// 查询R3产品
		} else {
			entity.setArticle_mod_id(0l);
		}

		Long recordCount = getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfoCountForReceive(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaPeArticleInfo> konkaPeArticleInfoList = super.getFacade().getKonkaPeArticleInfoService()
				.getKonkaPeArticleInfoPaginatedListForReceive(entity);
		request.setAttribute("konkaPeArticleInfoList", konkaPeArticleInfoList);

		this.setNavStringToForm(request, infoType);
		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String infoType = (String) dynaBean.get("infoType");
		this.setNavStringToForm(request, infoType);

		if (GenericValidator.isLong(id)) {

			KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
			entity.setId(Long.valueOf(id));

			entity = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfo(entity);

			KonkaPeAttachments attachment = new KonkaPeAttachments();
			attachment.setLink_id(new Long(id));
			attachment.setIs_del((long) 0);
			request.setAttribute("attachmentList", getFacade().getKonkaPeAttachmentsService()
					.getKonkaPeAttachmentsList(attachment));

			if (null != entity.getArticle_type_id()) {
				KonkaPeArticleType konkaPeArticleType = new KonkaPeArticleType();
				konkaPeArticleType.setId(entity.getArticle_type_id());
				konkaPeArticleType = super.getFacade().getKonkaPeArticleTypeService()
						.getKonkaPeArticleType(konkaPeArticleType);
				String type_name = konkaPeArticleType.getType_name();
				request.setAttribute("type_name", type_name);
			}

			request.setAttribute("entity", entity);
			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}

	private void setNavStringToForm(HttpServletRequest request, String infoType) {
		String navString = "";
		if (StringUtils.isNotBlank(infoType)) {
			if ("zxxx".equals(infoType)) {
				navString = "资讯信息";
			}
			if ("znx".equals(infoType)) {
				navString = "站内信息";
			}
			if ("xcpxx".equals(infoType)) {
				navString = "新品信息";
			}
		}
		request.setAttribute("navString", navString);
	}
}