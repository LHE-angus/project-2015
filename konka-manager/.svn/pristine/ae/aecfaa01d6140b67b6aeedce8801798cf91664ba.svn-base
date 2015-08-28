package com.ebiz.mmt.web.struts.jxc;

import java.util.HashMap;
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
import com.ebiz.mmt.domain.StdEntpUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wu,Yang
 * @version 2011-10-19
 */
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

		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}

		BaseProvinceListFour bplf = new BaseProvinceListFour();
		bplf.setP_index(new Long(user.getStdEntpMain().getP_index()));
		bplf = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(bplf);
		if (null == bplf) {
			super.invalidOper(request, response, "errors.parm");
			return null;
		}

		// 是否经销商（非直供）列表
		KonkaBranchCategory konkaBranchCategoryJxs = new KonkaBranchCategory();
		konkaBranchCategoryJxs.setCategory_id(20l);
		konkaBranchCategoryJxs.setMmt_shop_id(user.getStdEntpMain().getShop_id());
		List<KonkaBranchCategory> konkaBranchCategoryList = getFacade().getKonkaBranchCategoryService()
				.getKonkaBranchCategoryList(konkaBranchCategoryJxs);
		
		HashMap<String, String> result = super.getKonkaDeptIdAndType(user.getStdEntpMain().getShop_id(), false);
		String isDls = result.get("result_code");// 0：失败   10：代理商   20：经销商(直供)
		
		if (StringUtils.equals("10", isDls)) {
			entity.getMap().put("public_target", "1");// 代理商
			entity.getMap().put("shop_type", 10L);
		} else if (StringUtils.equals("20", isDls) || (null != konkaBranchCategoryList && konkaBranchCategoryList.size() > 0)) {
			entity.getMap().put("public_target", "2");// 经销商
			entity.getMap().put("shop_type", 20L);
		} else {// R3网点
			entity.getMap().put("public_target", "0");
			entity.getMap().put("shop_type", 0L);
		}
		
		entity.getMap().put("ssqy_p_index", bplf.getPar_index());// 网点所属区域p_index
		entity.getMap().put("par_index", bplf.getP_index());
		entity.getMap().put("shop_id", user.getStdEntpMain().getShop_id());// 网点类别
		entity.getMap().put("public_place_lt", 1);// 投放位置是全部或康佳专版的

		if ("zxxx".equals(infoType)) {
			entity.getMap().put("mod_id_in", "401010,402010,403010");// 查询R3、经销商、代理商资讯，根据public_target给予区分
		} else if ("xcpxx".equals(infoType)) {
			entity.getMap().put("mod_id_in", "401020,402020,403020");// 查询R3、经销商、代理商新产品，根据public_target给予区分
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