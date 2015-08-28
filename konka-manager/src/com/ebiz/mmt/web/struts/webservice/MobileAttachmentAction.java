package com.ebiz.mmt.web.struts.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.KonkaMobileSailData;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.org.apache.commons.lang3.StringUtils;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class MobileAttachmentAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.save(mapping, form, request, response);
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		KonkaMobileSailData entity = new KonkaMobileSailData();
		String link_id = request.getParameter("link_id");
		String link_tab = request.getParameter("link_tab");
		String file_desc = request.getParameter("file_desc");
		logger.info("link_id-------------->{}" + link_id);
		logger.info("link_tab-------------->{}" + link_tab);

		// 附件处理
		List<UploadFile> uploadFileList = new ArrayList<UploadFile>();
		if (link_tab != null && "KONKA_MOBILE_SAIL_DATA".equals(link_tab.trim())) {
			uploadFileList = super.uploadFile(form, MmtFilePathConfig.LST_PATH, true, 0, new int[] { 960 });
		} else if (link_tab != null && "KONKA_MOBILE_CUST_VISIT".equals(link_tab.trim())) {
			uploadFileList = super.uploadFile(form, MmtFilePathConfig.YWT_PATH, true, 0, new int[] { 960 });
		} else {
			uploadFileList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH, true, 0, new int[] { 960 });
		}

		List<Attachment> filesAttachmentList = new ArrayList<Attachment>();
		Attachment filesAttachment = null;
		if (null != uploadFileList && uploadFileList.size() > 0) {
			for (UploadFile uploadFile : uploadFileList) {
				filesAttachment = new Attachment();
				filesAttachment.setFile_name(uploadFile.getFileName());
				filesAttachment.setFile_type(uploadFile.getContentType());
				filesAttachment.setFile_size(new Integer(uploadFile.getFileSize()));
				filesAttachment.setSave_path(uploadFile.getFileSavePath());
				filesAttachment.setSave_name(uploadFile.getFileSaveName());
				filesAttachment.setDel_mark(new Short("0"));
				if (StringUtils.isNotBlank(file_desc)) {
					filesAttachment.setFile_desc(file_desc);
				}
				filesAttachmentList.add(filesAttachment);
			}
		}
		entity.setAttachmentList(filesAttachmentList);
		logger.info("filesAttachmentList------>{}" + filesAttachmentList.size());
		super.getFacade().getKonkaMobileSailDataService().createKonkaMobileSailDataForAttachment(entity, link_id,
				link_tab);
		super.renderText(response, "success");
		return null;
	}
}
