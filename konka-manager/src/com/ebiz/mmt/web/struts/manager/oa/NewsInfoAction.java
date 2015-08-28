package com.ebiz.mmt.web.struts.manager.oa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaPeArticleInfo;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Jin, QingHua
 */
public class NewsInfoAction extends BaseMmtoaAction {
	
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	return this.list(mapping, form, request, response);
}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
		entity.setArticle_mod_id(new Long(mod_id));
		entity.setId(Long.valueOf(id));
		//entity.setSign("bg");
		KonkaPeArticleInfo KonkaPeArticleInfoentity = getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfo(entity);
		if (null == KonkaPeArticleInfoentity) {
			return this.add(mapping, form, request, response);
		} else {
			// setNaviStringToScope(form, request);
			super.copyProperties(form, KonkaPeArticleInfoentity);
			return mapping.findForward("input");
		}
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	        throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		Pager pager = (Pager) dynaBean.get("pager");
		
		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
		super.copyProperties(entity, form);
		entity.getMap().put("st_add_datetime", dynaBean.get("st_add_datetime"));
		entity.getMap().put("en_add_datetime", dynaBean.get("en_add_datetime"));
	
		entity.getMap().put("st_invalid_date", dynaBean.get("st_invalid_date"));
		entity.getMap().put("en_invalid_date", dynaBean.get("en_invalid_date"));
		
		entity.setArticle_mod_id(new Long(mod_id));
		//entity.setSign("bg");
		//String i="1";
		//entity.setInfo_state(Short.parseShort( i ));
		Long recordCount = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfoCount(entity);
		pager.init(recordCount, 15, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
		List<KonkaPeArticleInfo> KonkaPeArticleInfoentity = getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfoPaginatedList(entity);
		request.setAttribute("KonkaPeArticleInfoentity", KonkaPeArticleInfoentity);
		
		return mapping.findForward("list");
	}
	

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (GenericValidator.isLong(id)) {
			KonkaPeArticleInfo KonkaPeArticleInfo = new KonkaPeArticleInfo();
			KonkaPeArticleInfo.setId(new Long(id));
			//KonkaPeArticleInfo.setSign("bg");
			KonkaPeArticleInfo entity = getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfo(KonkaPeArticleInfo);
			KonkaPeAttachments attachment = new KonkaPeAttachments();
			attachment.setLink_id(new Long(id));
			request.setAttribute("attachmentList", getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachmentsList(attachment));
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			// 增加视频播放功能,播放wmv、flv
//			if (null != entity.getVideo_path()) {
//				String[] video_types = StringUtils.split(entity.getVideo_path(), ".");
//				String video_type = "";
//				if (video_types.length >= 2) {
//					video_type = video_types[video_types.length - 1];
//					if ("flv".equals(video_type)) {
//						dynaBean.set("playFlv", "true");
//					} else if ("swf".equals(video_type)) {
//						dynaBean.set("playFlv", "true");
//					} else {
//						dynaBean.set("playMedia", "true");
//					}
//					logger.info("------>>>video_type:{}", video_type);
//				}
//			}

			super.copyProperties(form, entity);
			return mapping.findForward("view");
		} else {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
	}
	
	
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
		Date today = new Date();
		entity.setAdd_user_id(new Long(ui.getId()));
		entity.setArticle_mod_id(Long.parseLong(mod_id));
		entity.setAdd_date(today);
		super.copyProperties(form, entity);
		return mapping.findForward("input");
	}

	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
		super.copyProperties(entity, form);
		
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		entity.setAdd_user_id(ui.getId());

		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OA_PATH, true, 0,
				new int[] { 120, 240, 400,
				640 });
		List<KonkaPeAttachments> attachmentList = new ArrayList<KonkaPeAttachments>();
		KonkaPeAttachments attachment = null;
		// UploadFile uploadFile = null;
		for (UploadFile uploadFile : uploadFileList) {
			attachment = new KonkaPeAttachments();
			attachment.setFile_type(uploadFile.getContentType());
			attachment.setFile_size((long)uploadFile.getFileSize());
			attachment.setFile_name(uploadFile.getFileName());
			attachment.setSave_path(uploadFile.getFileSavePath());
			if ("image_path".equalsIgnoreCase(uploadFile.getFormName())) {
				entity.setImg_path(uploadFile.getFileSavePath());
			} else {
				attachmentList.add(attachment);
			}
		}
		entity.setKonkaPeAttachments(attachmentList);

		if (null == entity.getId()) {
			getFacade().getKonkaPeArticleInfoService().createKonkaPeArticleInfo(entity);
			saveMessage(request, "entity.inerted");
		} else {
			if ("".equals(entity.getImg_path())) {
				entity.setImg_path(null);
			}
			entity.setAdd_date(new Date());
			getFacade().getKonkaPeArticleInfoService().modifyKonkaPeArticleInfo(entity);
			saveMessage(request, "entity.updated");
		}
		ActionForward forward = new ActionForward("KonkaPeArticleInfo.do" + response.encodeURL("?mod_id=" + mod_id), true);
		KonkaPeArticleInfo KonkaPeArticleInfoentity = getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfo(entity);
		super.copyProperties(form, KonkaPeArticleInfoentity);
		return forward;
	}

}