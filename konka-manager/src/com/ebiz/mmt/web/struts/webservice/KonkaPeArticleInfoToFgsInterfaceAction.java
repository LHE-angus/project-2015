package com.ebiz.mmt.web.struts.webservice;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileArticleInfo;
import com.ebiz.mmt.domain.KonkaPeArticleInfo;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu,Hao
 * @version 2013-11-20
 */
public class KonkaPeArticleInfoToFgsInterfaceAction extends BaseAction {
	SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.getJson(mapping, form, request, response);
	}

	public ActionForward getJson(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		// 用户名
		String user_id = (String) dynaBean.get("user_id");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		String title_like = (String) dynaBean.get("title_like");
		String startime = (String) dynaBean.get("startime");
		String endtime = (String) dynaBean.get("endtime");
		String is_sent = (String) dynaBean.get("is_send");

		Pager pager = (Pager) dynaBean.get("pager");
		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();

		//版本号,android_version,ios_version
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		// 用户名或者密码不存在， 资讯信息类别为公开
		PeProdUser peProdUser = checkUserid(user_id, userpass,android_version,ios_version);
		if (null == peProdUser) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}
		entity.getMap().put("group_user_id", peProdUser.getId());

		if (GenericValidator.isInt(is_sent)) {
			entity.setIs_sent(Integer.valueOf(is_sent));
		}

		if (StringUtils.isNotBlank(startime)) {
			entity.getMap().put("startime", startime);
		}

		if (StringUtils.isNotBlank(endtime)) {
			entity.getMap().put("endtime", endtime);
		}

		entity.setArticle_type_id(-1L);

		if (StringUtils.isNotBlank(title_like)) {
			entity.getMap().put("title_likes", title_like);
		}

		if (null != peProdUser && null != peProdUser.getDept_id()) {
			KonkaDept kDept = getKonkaDeptForFgs(peProdUser.getDept_id());
			if (null != kDept)
				entity.getMap().put("_is_fgs_user", kDept.getDept_id());
		} else {// 客户

			String dept_id = "-1";
			if (null != peProdUser.getCust_id()) {
				KonkaR3Shop krs = new KonkaR3Shop();
				krs.setId(peProdUser.getCust_id());
				krs = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(krs);
				if (krs != null && krs.getBranch_area_name_2() != null) {
					KonkaDept k = new KonkaDept();
					k.setDept_sn(krs.getBranch_area_name_2());
					k = super.getFacade().getKonkaDeptService().getKonkaDept(k);
					if (null != k)
						dept_id = k.getDept_id().toString();
				}

			}
			entity.getMap().put("_is_fgs_user", dept_id);
		}
		entity.setIs_del(0L);
		entity.setStates(1L);
		Long recordCount = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfoForMobileCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaPeArticleInfo> entityList = super.getFacade().getKonkaPeArticleInfoService()
				.getKonkaPeArticleInfoForMobilePaginatedList(entity);

		List<KonkaMobileArticleInfo> konkaMobileArticleInfoList = new ArrayList<KonkaMobileArticleInfo>();

		if (entityList.size() == 0 || entityList == null) {
			return null;
		}

		for (KonkaPeArticleInfo temp : entityList) {

			KonkaMobileArticleInfo konkaMobileArticleInfo = new KonkaMobileArticleInfo();
			if (temp.getTitle() == null) {
				konkaMobileArticleInfo.setTitle("");
			} else {
				konkaMobileArticleInfo.setTitle(temp.getTitle());
			}

			if (null == temp.getSummary()) {
				konkaMobileArticleInfo.setSummary("");
			} else {
				konkaMobileArticleInfo.setSummary(temp.getSummary());
			}

			if (null == temp.getAdd_user_name()) {
				konkaMobileArticleInfo.setAdd_user_name("");
			} else {
				konkaMobileArticleInfo.setAdd_user_name(temp.getAdd_user_name());
			}

			if (null == temp.getImg_path()) {
				konkaMobileArticleInfo.setImg_path("");
			} else {
				konkaMobileArticleInfo.setImg_path(temp.getImg_path());
			}

			if (null == temp.getImg_path()) {
				konkaMobileArticleInfo.setView_count(0L);
			} else {
				konkaMobileArticleInfo.setView_count(temp.getView_counts());
			}

			if (null == temp.getId()) {
				konkaMobileArticleInfo.setLink_out_addr("");
			} else {
				String ctx = getCtxPath(request);
				String link = "/webservice/KonkaPeArticleInfoToFgsInterface.do?method=view&id=" + temp.getId();
				konkaMobileArticleInfo.setLink_out_addr(ctx + link);
			}

			if (null == temp.getPub_date()) {
				konkaMobileArticleInfo.setPub_date("");
			} else {
				konkaMobileArticleInfo.setPub_date(df1.format(temp.getPub_date()));
			}

			konkaMobileArticleInfoList.add(konkaMobileArticleInfo);
		}

		super.renderTextJsonOrJsonp(request, response, JSON.toJSONString(konkaMobileArticleInfoList));
		return null;
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

			KonkaPeAttachments attachment = new KonkaPeAttachments();
			attachment.setLink_id(new Long(id));
			attachment.setIs_del((long) 0);
			request.setAttribute("attachmentList", getFacade().getKonkaPeAttachmentsService()
					.getKonkaPeAttachmentsList(attachment));

			KonkaPeArticleInfo update = new KonkaPeArticleInfo();
			update.setId(entity.getId());
			update.setView_counts(entity.getView_counts() + 1L);
			super.getFacade().getKonkaPeArticleInfoService().modifyKonkaPeArticleInfo(update);

			request.setAttribute("entity", entity);
			return new ActionForward("/KonkaPeArticleInfoToFgsInterface/view.jsp");
		} else {
			return null;
		}
	}

	public ActionForward downloadFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
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
			
			try {
				// 获取系统实际路径
				String SystemPath = getServlet().getServletContext().getRealPath(String.valueOf(File.separatorChar));

				String path = new String(SystemPath + peAttachments.getSave_path());
				File file = new File(path);
				if (file.exists()) {
					String filename = file.getName();

					// 取得文件的扩展名ext
					String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

					InputStream fis = new BufferedInputStream(new FileInputStream(path));
					byte[] buffer = new byte[fis.available()];
					fis.read(buffer);
					fis.close();

					response.reset();
					response.addHeader("Content-Disposition", "attachment;filename="
							+ java.net.URLEncoder.encode(peAttachments.getFile_name(), "UTF-8"));
					response.addHeader("Content-Length", "" + file.length()); // 设置返回的文件类型
					OutputStream toClient = new BufferedOutputStream(response.getOutputStream()); // 得到向客户端输出二进制数据的对象
					// 根据扩展名声称客户端浏览器mime类型
					if (ext.equals("DOC"))
						response.setContentType("application/msword");
					else
						response.setContentType("application/octet-stream"); // 设置返回的文件类型
					toClient.write(buffer); // 输出数据
					toClient.flush();
					toClient.close();
				} else {
					super.renderJavaScript(response, "window.onload=function(){alert('文件不存在！');history.back();}");
				}
			} catch (IOException ex) {
				ex.printStackTrace();
				super.renderJavaScript(response, "window.onload=function(){alert('下载出错！');history.back();}");
				return null;
			}
		}
		return null;
	}
}
