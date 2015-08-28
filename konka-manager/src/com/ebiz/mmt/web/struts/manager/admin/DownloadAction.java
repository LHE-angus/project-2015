package com.ebiz.mmt.web.struts.manager.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Wang,Yang
 * 
 */
public class DownloadAction extends BaseAction {
	/**
	 * 根绝文件的保存名称 save_name 下载文件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		DynaBean dynaBean = (DynaBean) form;
		String save_name = (String) dynaBean.get("save_name");
		if (StringUtils.isBlank(save_name)) {
			super.renderJavaScript(response, "window.onload=function(){alert('错误！');history.back();}");
			return null;
		}
		KonkaPeAttachments attachments = new KonkaPeAttachments();
		attachments.setSave_name(save_name);
		attachments.setIs_del(0L);
		List<KonkaPeAttachments> attachmentsList = getFacade().getKonkaPeAttachmentsService()
				.getKonkaPeAttachmentsList(attachments);

		if (attachmentsList.size() == 0) {
			super.renderJavaScript(response, "window.onload=function(){alert('文件不存在！');history.back();}");
			return null;
		}
		KonkaPeAttachments peAttachments = attachmentsList.get(0);

		if (peAttachments.getSave_path() == null) {
			super.renderJavaScript(response, "window.onload=function(){alert('文件不存在！');history.back();}");
			return null;
		} else {
			String ctx = super.getCtxPath(request);
			if (!StringUtils.endsWith(ctx, File.separator)) {
				ctx += File.separator;
			}

			response.sendRedirect(ctx + peAttachments.getSave_path());
			return null;

			// 
			// try {
			// // 获取系统实际路径
			// String SystemPath = getServlet().getServletContext()
			// .getRealPath(String.valueOf(File.separatorChar));
			//
			// String path = new String(SystemPath
			// + peAttachments.getSave_path());
			// File file = new File(path);
			// if (file.exists()) {
			// String filename = file.getName();
			//
			// // 取得文件的扩展名ext
			// String ext = filename.substring(filename.lastIndexOf(".") + 1)
			// .toUpperCase();
			//
			// InputStream fis = new BufferedInputStream(new FileInputStream(
			// path));
			// byte[] buffer = new byte[fis.available()];
			// fis.read(buffer);
			// fis.close();
			//
			// response.reset();
			// response.addHeader(
			// "Content-Disposition",
			// "attachment;filename="
			// + java.net.URLEncoder.encode(
			// peAttachments.getFile_name(), "UTF-8"));
			// response.addHeader("Content-Length", "" + file.length()); //
			// 设置返回的文件类型
			// OutputStream toClient = new BufferedOutputStream(
			// response.getOutputStream()); // 得到向客户端输出二进制数据的对象
			// // 根据扩展名声称客户端浏览器mime类型
			// if (ext.equals("DOC"))
			// response.setContentType("application/msword");
			// else
			// response.setContentType("application/octet-stream"); // 设置返回的文件类型
			// toClient.write(buffer); // 输出数据
			// toClient.flush();
			// toClient.close();
			// }else{
			// super.renderJavaScript(response,
			// "window.onload=function(){alert('文件不存在！');history.back();}");
			// }
			// } catch (IOException ex) {
			// ex.printStackTrace();
			// super.renderJavaScript(response,
			// "window.onload=function(){alert('下载出错！');history.back();}");
			// return null;
			// }
			// }
			// return null;
		}
	}

	public ActionForward downloadFile1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		DynaBean dynaBean = (DynaBean) form;
		String save_name = (String) dynaBean.get("save_name");
		if (StringUtils.isBlank(save_name)) {
			super.renderJavaScript(response, "window.onload=function(){alert('错误！');history.back();}");
			return null;
		}
		Attachment attachments = new Attachment();
		attachments.setSave_name(save_name);
		List<Attachment> attachmentsList = getFacade().getAttachmentService().getAttachmentList(attachments);

		if (attachmentsList.size() == 0) {
			super.renderJavaScript(response, "window.onload=function(){alert('文件不存在！');history.back();}");
			return null;
		}

		Attachment peAttachments = attachmentsList.get(0);

		if (peAttachments.getSave_path() == null) {
			super.renderJavaScript(response, "window.onload=function(){alert('文件不存在！');history.back();}");
			return null;
		} else {
			String ctx = super.getCtxPath(request);
			if (!StringUtils.endsWith(ctx, File.separator)) {
				ctx += File.separator;
			}

			response.sendRedirect(ctx + peAttachments.getSave_path());
			return null;

		}
	}

	/**
	 * 查询零售查询中最大压缩成960的图片
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward downloadFile2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		DynaBean dynaBean = (DynaBean) form;
		String save_name = (String) dynaBean.get("save_name");
		if (StringUtils.isBlank(save_name)) {
			super.renderJavaScript(response, "window.onload=function(){alert('错误！');history.back();}");
			return null;
		}
		Attachment attachments = new Attachment();
		attachments.setSave_name(save_name);
		List<Attachment> attachmentsList = getFacade().getAttachmentService().getAttachmentList(attachments);

		if (attachmentsList.size() == 0) {
			super.renderJavaScript(response, "window.onload=function(){alert('文件不存在！');history.back();}");
			return null;
		}

		Attachment peAttachments = attachmentsList.get(0);

		if (peAttachments.getSave_path() == null) {
			super.renderJavaScript(response, "window.onload=function(){alert('文件不存在！');history.back();}");
			return null;
		} else {
			String ctx = super.getCtxPath(request);
			if (!StringUtils.endsWith(ctx, File.separator)) {
				ctx += File.separator;
			}
			String path = peAttachments.getSave_path();
			path = path.substring(0, path.indexOf(".")) + "_960.jpg";
			response.sendRedirect(ctx + path);
			return null;

		}
	}
}
