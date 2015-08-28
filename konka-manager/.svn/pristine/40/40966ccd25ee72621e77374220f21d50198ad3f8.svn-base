package com.ebiz.mmt.web.struts.customer;

import java.util.ArrayList;
import java.util.Date;
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

import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileSpRelation;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.KonkaR3StoreShow;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class KonkaR3StoreAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);

		super.encodeCharacterForGetMethod(dynaBean, request);

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		if (null == ui || null == ui.getCust_id()) {
			super.renderJavaScript(response, "alert('用户信息有误，请联系相关人员处理！');history.back();");
			return null;
		}

		KonkaR3Shop shop = new KonkaR3Shop();
		shop.setId(ui.getCust_id());
		shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
		if (null == shop || null == shop.getR3_code()) {
			super.renderJavaScript(response, "alert('客户信息有误，请联系相关人员处理！');history.back();");
			return null;
		}

		Pager pager = (Pager) dynaBean.get("pager");
		
		String store_name_like = (String) dynaBean.get("store_name_like");
		String ywy_name_like = (String) dynaBean.get("ywy_name_like");
		String zxy_name_like = (String) dynaBean.get("zxy_name_like");
		String link_man_like = (String) dynaBean.get("link_man_like");
		String is_del = (String) dynaBean.get("is_del");
		String is_sure = (String) dynaBean.get("is_sure");
		String excel_to_all = (String) dynaBean.get("excel_to_all");

		KonkaR3Store entity = new KonkaR3Store();
		super.copyProperties(entity, form);

		entity.setR3_code(shop.getR3_code());
		entity.setIs_del(0);
		if (StringUtils.isNotBlank(is_del)) {
			entity.setIs_del(Integer.valueOf(is_del));
		}
		if (StringUtils.isNotBlank(store_name_like)) {
			store_name_like = store_name_like.replaceAll("&#40;", "（").replaceAll("&#41;", "）");
			entity.getMap().put("store_name_like", store_name_like);
		}
		if (StringUtils.isNotBlank(ywy_name_like)) {
			entity.getMap().put("ywy_name_like", ywy_name_like);
		}
		if (StringUtils.isNotBlank(zxy_name_like)) {
			entity.getMap().put("zxy_name_like", zxy_name_like);
		}
		if (StringUtils.isNotBlank(link_man_like)) {
			entity.getMap().put("link_man_like", link_man_like);
		}
		if(StringUtils.isNotBlank(is_sure)){
        	if("1".equals(is_sure)){
        		entity.getMap().put("is_sure1", "1");
        	}
        	if("2".equals(is_sure)){
        		entity.getMap().put("is_sure2", "1");
        	}
        }

		Long recordCount = getFacade().getKonkaR3StoreService().getStoreDataCountForVIP(entity);
		pager.init(recordCount, 10, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<HashMap> entityList = getFacade().getKonkaR3StoreService().getStoreDataListForVIP(entity);
		request.setAttribute("entityList", entityList);

		if (StringUtils.isNotBlank(excel_to_all)) {

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("门店列表")
					+ ".xls");

			entity.getRow().setCount(recordCount.intValue());
			List<HashMap> entityList1 = getFacade().getKonkaR3StoreService().getKonkaR3StoreAndYwyNamePaginatedList(
					entity);
			request.setAttribute("allList", entityList1);
			return mapping.findForward("view");
		}

		return mapping.findForward("list");
	}
	
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String store_id = (String) dynaBean.get("store_id");

		if (!GenericValidator.isLong(store_id)) {
			saveError(request, "errors.long", new String[] { store_id });
			return mapping.findForward("list");
		}

		KonkaR3Store entity = new KonkaR3Store();
		entity.setStore_id(Long.valueOf(store_id));
		entity = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(entity);

		if (null == entity) {
			saveError(request, "errors.long", new String[] { store_id });
			return mapping.findForward("list");
		}
		entity.setQueryString(super.serialize(request, "store_id", "mod_id"));
		// request.setAttribute("entity", entity);
		super.copyProperties(form, entity);
		
		//查询创建、维护人
		if(entity.getCreate_user_id()!=null){
			PeProdUser user = new PeProdUser();
			user.setId(entity.getCreate_user_id());
			user = super.getFacade().getPeProdUserService().getPeProdUser(user);
			request.setAttribute("create_man",user.getReal_name());
		}
		
		if(entity.getModify_user_id()!=null){
			PeProdUser user = new PeProdUser();
			user.setId(entity.getModify_user_id());
			user = super.getFacade().getPeProdUserService().getPeProdUser(user);
			request.setAttribute("modify_man",user.getReal_name());
		}

		// 附件
		Attachment attachment = new Attachment();
		attachment.setLink_id(new Long(store_id));
		attachment.setLink_tab("KONKA_R3_STORE");
		attachment.setDel_mark(new Short("0"));
		request.setAttribute("attachmentList", getFacade().getAttachmentService().getAttachmentList(attachment));

		// 部门列表
		KonkaDept konka_dept = new KonkaDept();
		konka_dept.setPar_id(0L);
		List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService().getKonkaDeptListWithTreeNameForProdUser(
				konka_dept);
		request.setAttribute("peDeptList", deptInfoList);

		// 回显已有促销员 Lianghouen 2014-06-18
		KonkaMobileSpRelation konkaMobileSpRelation = new KonkaMobileSpRelation();
		konkaMobileSpRelation.getMap().put("shop_id_eq", store_id);
		konkaMobileSpRelation.getMap().put("is_del_eq", true);
		List<KonkaMobileSpRelation> konkaMobileSpRelationList = super.getFacade().getKonkaMobileSpRelationService()
				.getKonkaMobileSpRelationForCxyList(konkaMobileSpRelation);
		List<String> ids = new ArrayList<String>();
		StringBuffer temp = new StringBuffer("");
		if (konkaMobileSpRelationList.size() > 0) {
			for (KonkaMobileSpRelation konkaMobileSpRelation2 : konkaMobileSpRelationList) {
				PeProdUser pp = new PeProdUser();
				pp.setIs_del(0);
				pp.setId(konkaMobileSpRelation2.getSeller_id());
				pp = super.getFacade().getPeProdUserService().getCXYPeProdUser(pp);
				if (null != pp && !"".equals(pp.getReal_name()) && null != pp.getReal_name()) {
					String type = "";
					if (null != pp.getMap().get("sales_type")) {
						type = pp.getMap().get("sales_type").toString();
					}
					if ("1".equals(type)) {
						type = "兼职";
					}
					if ("2".equals(type)) {
						type = "全职";
					}
					temp.append(pp.getUser_name() + "(" + type + ")" + "，");
				}
			}
		}

		// 回显门店展台展柜
		KonkaR3StoreShow ks = new KonkaR3StoreShow();
		ks.setStore_id(Long.valueOf(store_id));

		ks.getRow().setFirst(0);
		ks.getRow().setCount(50);
		List<KonkaR3StoreShow> ksList = super.getFacade().getKonkaR3StoreShowService()
				.getKonkaR3StoreShowLBPaginatedList(ks);
		request.setAttribute("ksList", ksList);

		String exCXY = temp.toString();
		if (exCXY.length() > 0) {
			exCXY = temp.substring(0, temp.length() - 1);
		}
		request.setAttribute("exCXY", exCXY);

		return mapping.findForward("view");
	}
	
	
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String store_id = (String) dynaBean.get("store_id");

		if (!GenericValidator.isLong(store_id)) {
			this.saveError(request, "errors.long", new String[] { store_id });
			return mapping.findForward("list");
		}

		KonkaR3Store entity = new KonkaR3Store();
		entity.setStore_id(Long.valueOf(store_id));
		entity = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(entity);

		if (null == entity) {
			saveError(request, "errors.long", new String[] { store_id });
			return mapping.findForward("list");
		}

		//保留查询条件
		entity.setQueryString(super.serialize(request, "store_id", "method"));
		// end

		super.copyProperties(form, entity);
		
		//查询创建、维护人
		if(entity.getCreate_user_id()!=null){
			PeProdUser user = new PeProdUser();
			user.setId(entity.getCreate_user_id());
			user = super.getFacade().getPeProdUserService().getPeProdUser(user);
			request.setAttribute("create_man",user.getReal_name());
		}
		if(entity.getModify_user_id()!=null){
			PeProdUser user = new PeProdUser();
			user.setId(entity.getModify_user_id());
			user = super.getFacade().getPeProdUserService().getPeProdUser(user);
			request.setAttribute("modify_man",user.getReal_name());
		}

		// 附件
		Attachment attachment = new Attachment();
		attachment.setLink_id(new Long(store_id));
		attachment.setLink_tab("KONKA_R3_STORE");
		attachment.setDel_mark(new Short("0"));
		request.setAttribute("attachmentList", getFacade().getAttachmentService().getAttachmentList(attachment));

		// 回显已有促销员 Lianghouen 2015-05-19
		KonkaMobileSpRelation konkaMobileSpRelation = new KonkaMobileSpRelation();
		konkaMobileSpRelation.getMap().put("shop_id_eq", store_id);
		konkaMobileSpRelation.getMap().put("is_del_eq", true);
		List<KonkaMobileSpRelation> konkaMobileSpRelationList = super.getFacade().getKonkaMobileSpRelationService()
				.getKonkaMobileSpRelationForCxyList(konkaMobileSpRelation);
		List<String> ids = new ArrayList<String>();
		StringBuffer temp = new StringBuffer("");
		if (konkaMobileSpRelationList.size() > 0) {
			for (KonkaMobileSpRelation konkaMobileSpRelation2 : konkaMobileSpRelationList) {
				PeProdUser pp = new PeProdUser();
				pp.setIs_del(0);
				pp.setId(konkaMobileSpRelation2.getSeller_id());
				pp = super.getFacade().getPeProdUserService().getCXYPeProdUser(pp);
				if (null != pp && !"".equals(pp.getReal_name()) && null != pp.getReal_name()) {
					String type = "";
					if (null != pp.getMap().get("sales_type")) {
						type = pp.getMap().get("sales_type").toString();
					}
					if ("1".equals(type)) {
						type = "兼职";
					}
					if ("2".equals(type)) {
						type = "全职";
					}
					temp.append(pp.getUser_name() + "(" + type + ")" + "，");
				}
			}
		}
		String exCXY = temp.toString();
		if (exCXY.length() > 0) {
			exCXY = temp.substring(0, temp.length() - 1);
		}
		request.setAttribute("exCXY", exCXY);

		// 回显地区信息
		if (entity.getP_index() != null && entity.getP_index() != -1) {
			String p_index = entity.getP_index().toString();
			if (StringUtils.isNotBlank(p_index)) {
				if (!p_index.endsWith("00")) {
					if (p_index.length() == 6) {
						request.setAttribute("_province", StringUtils.substring(p_index, 0, 2) + "0000");
						request.setAttribute("_city", StringUtils.substring(p_index, 0, 4) + "00");
						request.setAttribute("_country", p_index);
					} else if (p_index.length() == 8) {
						request.setAttribute("_province", StringUtils.substring(p_index, 0, 2) + "0000");
						request.setAttribute("_city", StringUtils.substring(p_index, 0, 4) + "00");
						request.setAttribute("_country", StringUtils.substring(p_index, 0, 6));
						request.setAttribute("_town", p_index);
					}
				} else if (p_index.endsWith("0000")) {
					request.setAttribute("_province", p_index);
				} else if (p_index.endsWith("00")) {
					request.setAttribute("_province", StringUtils.substring(p_index, 0, 2) + "0000");
					request.setAttribute("_city", p_index);
				}
			}
		}

		// 回显门店展台展柜
		KonkaR3StoreShow ks = new KonkaR3StoreShow();
		ks.setStore_id(Long.valueOf(store_id));

		ks.getRow().setFirst(0);
		ks.getRow().setCount(50);
		List<KonkaR3StoreShow> ksList = super.getFacade().getKonkaR3StoreShowService()
				.getKonkaR3StoreShowLBPaginatedList(ks);
		request.setAttribute("ksList", ksList);

		return mapping.findForward("input");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String store_id = (String) dynaBean.get("store_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String province = (String) dynaBean.get("_province");
		String city = (String) dynaBean.get("_city");
		String country = (String) dynaBean.get("_country");
		String town = (String) dynaBean.get("_town");

		String[] compition = request.getParameterValues("competition");
		String compitions = "";
		if (null != compition && compition.length > 0) {
			for (String string : compition) {
				compitions = compitions + string + ",";
			}
		}

		KonkaR3Store entity = new KonkaR3Store();
		super.copyProperties(entity, form);
		
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		
		if (null != entity.getStore_name() && !"".equals(entity.getStore_name())) {

			String store_name = entity.getStore_name();
			store_name = store_name.replaceAll("&#40;", "（").replaceAll("&#41;", "）");

			entity.setStore_name(store_name);
		}
		entity.setCompetitions(compitions);// 这是竞争品牌
		// 人员居住地设定
		if (GenericValidator.isLong(province) && !GenericValidator.isLong(city)) {
			entity.setP_index(Long.valueOf(province));
		}
		if (GenericValidator.isLong(province) && GenericValidator.isLong(city) && !GenericValidator.isLong(country)) {
			entity.setP_index(Long.valueOf(city));
		}
		if (GenericValidator.isLong(province) && GenericValidator.isLong(city) && GenericValidator.isLong(country)
				&& !GenericValidator.isLong(town)) {
			entity.setP_index(Long.valueOf(country));
		}
		if (GenericValidator.isLong(province) && GenericValidator.isLong(city) && GenericValidator.isLong(country)
				&& GenericValidator.isLong(town)) {
			entity.setP_index(Long.valueOf(town));
		}

		String[] file_desc = request.getParameterValues("file_desc");
		// 附件处理
		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.MD_PATH, true, 0,
				new int[] { 240 });
		List<Attachment> filesAttachmentList = new ArrayList<Attachment>();
		Attachment filesAttachment = null;
		if (null != file_desc && file_desc.length > 0) {
			for (int i = 1; i < file_desc.length; i++) {
				if (null != uploadFileList && uploadFileList.size() > 0) {
					for (UploadFile uploadFile : uploadFileList) {
						if (("file_show_" + i).endsWith(uploadFile.getFormName())) {
							filesAttachment = new Attachment();
							filesAttachment.setFile_name(uploadFile.getFileName());
							filesAttachment.setFile_type(uploadFile.getContentType());
							filesAttachment.setFile_size(new Integer(uploadFile.getFileSize()));
							filesAttachment.setSave_path(uploadFile.getFileSavePath());
							filesAttachment.setSave_name(uploadFile.getFileSaveName());
							filesAttachment.setDel_mark(new Short("0"));
							filesAttachment.setFile_desc(file_desc[i]);
							filesAttachmentList.add(filesAttachment);
						}
					}
				}
			}
			entity.setAttachmentList(filesAttachmentList);
		}

		if (StringUtils.isBlank(store_id)) {// insert
			//2014-11-26 添加创建人ID
			entity.setCreate_user_id(ui.getId());
			//2014-11-27 添加创建时间
			entity.setAdd_date(new Date());
			entity.setIs_del(0);
			super.getFacade().getKonkaR3StoreService().createKonkaR3Store(entity);
			saveMessage(request, "entity.inserted");
		} else {// update

			entity.setStore_id(Long.valueOf(store_id));
			//2014-11-26 添加修改人ID
			entity.setModify_user_id(ui.getId());
			//2014-11-27 添加维护时间
			entity.setModify_date(new Date());
			getFacade().getKonkaR3StoreService().modifyKonkaR3Store(entity);
			
			//同步修改门店展台/演示设备
			List<KonkaR3StoreShow> storeshowlist=new ArrayList<KonkaR3StoreShow>();
			KonkaR3StoreShow storeshow=new KonkaR3StoreShow();
			storeshow.setStore_id(entity.getStore_id());
			storeshowlist=super.getFacade().getKonkaR3StoreShowService().getKonkaR3StoreShowList(storeshow);
			for (int i = 0; i < storeshowlist.size(); i++) {
				Long updateid=storeshowlist.get(i).getId();
				KonkaR3StoreShow updateEntity=new KonkaR3StoreShow();
				if(null!=updateid){
					updateEntity.setId(updateid);
					updateEntity.setStore_name(entity.getStore_name());
					super.getFacade().getKonkaR3StoreShowService().modifyKonkaR3StoreShow(updateEntity);
				}
			}
			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		logger.info(entity.getQueryString());
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
}
