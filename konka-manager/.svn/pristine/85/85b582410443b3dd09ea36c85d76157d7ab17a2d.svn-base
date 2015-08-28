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
import java.util.Date;
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
import com.alibaba.fastjson.JSONArray;
import com.ebiz.mmt.domain.KonkaCommentInfo;
import com.ebiz.mmt.domain.KonkaCommentRead;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileArticleInfo;
import com.ebiz.mmt.domain.KonkaPeArticleInfo;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.struts.MobileBaseAction;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu,Hao
 * @version 2013-05-02
 */
public class KonkaPeArticleInfoInterfaceAction extends MobileBaseAction {

	// SimpleDateFormat df = new SimpleDateFormat("yyyy/M/d");

	SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.getJson(mapping, form, request, response);
	}

	public ActionForward getJson(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		String article_type_id = (String) dynaBean.get("article_type_id");
		String title_like = (String) dynaBean.get("title_like");
		String startime = (String) dynaBean.get("startime");
		String endtime = (String) dynaBean.get("endtime");
		String is_sent = (String) dynaBean.get("is_send");

		Pager pager = (Pager) dynaBean.get("pager");
		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();

		PeProdUser peProdUser = checkUser(username, userpass);

		if (StringUtils.isNotBlank(article_type_id) && null != peProdUser && "-1".equals(article_type_id)) {
			entity.setArticle_type_id(-1L);

			entity.getMap().put("group_user_id", peProdUser.getId());
			if (null != peProdUser.getDept_id()) {
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

		} else {

			// 用户名或者密码不存在， 资讯信息类别为公开
			if (null == peProdUser) {
				entity.setMsg_info_type(0);
			} else {
				entity.getMap().put("group_user_id", peProdUser.getId());
			}

			if (StringUtils.isNotBlank(article_type_id)) {
				entity.setArticle_type_id(Long.valueOf(article_type_id));
			}
			if (!"-1".equals(article_type_id)) {
				entity.getMap().put("article_type_id_not_fgs", true);
			}
		}

		if (GenericValidator.isInt(is_sent)) {
			entity.setIs_sent(Integer.valueOf(is_sent));
		}

		if (StringUtils.isNotBlank(startime)) {
			entity.getMap().put("startime", startime);
		}

		if (StringUtils.isNotBlank(endtime)) {
			entity.getMap().put("endtime", endtime);
		}

		if (StringUtils.isNotBlank(title_like)) {
			entity.getMap().put("title_likes", title_like);
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

			if (!(StringUtils.isNotBlank(article_type_id) && null != peProdUser && "-1".equals(article_type_id))) {

				if (null == temp.getMap().get("article_type_name")) {
					konkaMobileArticleInfo.setArticle_type_name("");
				} else {
					konkaMobileArticleInfo.setArticle_type_name(temp.getMap().get("article_type_name").toString());
				}
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
			String ctx = getCtxPath(request);
			String small_pic = "";

			if (temp != null && temp.getImg_path() != null) {
				if (temp.getImg_path().indexOf(".") > -1) {
					konkaMobileArticleInfo.setImg_path(temp.getImg_path().substring(0, temp.getImg_path().indexOf("."))
							+ "_400"
							+ temp.getImg_path()
									.substring(temp.getImg_path().indexOf("."), temp.getImg_path().length()));
				}
			}

			if (null == temp.getId()) {
				konkaMobileArticleInfo.setLink_out_addr("");
			} else {

				String link = "/webservice/KonkaPeArticleInfoInterface.do?method=view&id=" + temp.getId();
				konkaMobileArticleInfo.setLink_out_addr(ctx + link);
			}

			if (null == temp.getPub_date()) {
				konkaMobileArticleInfo.setPub_date("");
			} else {
				konkaMobileArticleInfo.setPub_date(df1.format(temp.getPub_date()));
			}

			// 评论次数
			if (null == temp.getMap().get("comment_num")) {
				konkaMobileArticleInfo.getMap().put("comment_num", 0);
			} else {
				konkaMobileArticleInfo.getMap().put("comment_num",
						Integer.parseInt(temp.getMap().get("comment_num").toString()));
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
		// 用户名
		String user_id = (String) dynaBean.get("user_id");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		PeProdUser peProdUser = checkUserByIdAndPass(user_id, userpass);

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

			// 用户点击查看咨询信息的时候，记录下来已经查看 by XiaoGuoJian 2014-10-22-------start----
			if (null != peProdUser) {// 说明是手机端登录之后的查看，否则是web端的查看，不添加咨询阅读记录
				KonkaCommentRead commentRead = new KonkaCommentRead();
				commentRead.setLink_id(Long.valueOf(id));
				commentRead.setLink_tab("KONKA_PE_ARTICLE_INFO");
				Long count = super.getFacade().getKonkaCommentReadService().getKonkaCommentReadCount(commentRead);
				if (count == null || count == 0) {// 说明之前没有查看过这条咨询,则像read表中插入记录
					commentRead.setAdd_date(new Date());
					commentRead.setInfo_state(0l);
					commentRead.setIs_del(0l);
					commentRead.setMod_id(1l);// 手机端没有mod_id，默认填入1代表手机端的
					commentRead.setNick_name(peProdUser.getUser_name());// 没有昵称，默认存入用户名
					commentRead.setUser_id(peProdUser.getId());
					commentRead.setUser_name(peProdUser.getUser_name());
					super.getFacade().getKonkaCommentReadService().createKonkaCommentRead(commentRead);
				}
				request.setAttribute("is_mobile", true);
				request.setAttribute("user_id", user_id);
				request.setAttribute("id", id);
			}
			// 用户点击查看咨询信息的时候，记录下来已经查看 by XiaoGuoJian 2014-10-22-------end-------

			return new ActionForward("/KonkaPeArticleInfoInterface/view.jsp");
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
					response.addHeader("Content-Disposition",
							"attachment;filename=" + java.net.URLEncoder.encode(peAttachments.getFile_name(), "UTF-8"));
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

	protected PeProdUser checkUser(String username, String userpass) throws Exception {
		PeProdUser ui = new PeProdUser();
		if (StringUtils.isEmpty(username))
			return null;
		if (StringUtils.isEmpty(userpass))
			return null;
		username = username.trim();
		PeProdUser entity = new PeProdUser();
		entity.setUser_name(username);
		// entity.setId(Long.valueOf(username));
		entity.setIs_del(0);
		DESPlus des = new DESPlus();
		entity.setPass_word(des.encrypt(userpass));
		ui = getFacade().getPeProdUserService().getPeProdUser(entity);
		return ui;
	}

	protected PeProdUser checkUserByIdAndPass(String user_id, String userpass) throws Exception {
		PeProdUser ui = new PeProdUser();
		if (StringUtils.isEmpty(user_id))
			return null;
		if (StringUtils.isEmpty(userpass))
			return null;
		PeProdUser entity = new PeProdUser();
		// entity.setUser_name(username);
		entity.setId(Long.valueOf(user_id));
		entity.setIs_del(0);
		DESPlus des = new DESPlus();
		entity.setPass_word(des.encrypt(userpass));
		ui = getFacade().getPeProdUserService().getPeProdUser(entity);
		return ui;
	}

	protected PeProdUser checkUserById(String user_id) throws Exception {
		PeProdUser ui = new PeProdUser();
		if (StringUtils.isEmpty(user_id))
			return null;
		PeProdUser entity = new PeProdUser();
		entity.setId(Long.valueOf(user_id));
		entity.setIs_del(0);
		ui = getFacade().getPeProdUserService().getPeProdUser(entity);
		return ui;
	}

	/**
	 * 咨询评论页面，通过Ajax保存<br/>
	 * 正确的情况下返回 1,非正常情况下返回 0<br/>
	 * 2014-10-22 by Xiao,GuoJian
	 */
	public ActionForward ajaxSaveCommentInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String content = (String) dynaBean.get("content");// 评论内容
		String user_id = (String) dynaBean.get("user_id");// 用户id
		String link_id = (String) dynaBean.get("id");// 评论内容的id
		String anonymous = (String) dynaBean.get("anonymous");// 匿名评论
		String par_id = (String) dynaBean.get("par_id");// 父ID

		if (StringUtils.isEmpty(content)) {
			super.renderText(response, "0");
			return null;
		}
		if (StringUtils.isEmpty(user_id)) {
			super.renderText(response, "0");
			return null;
		}
		if (StringUtils.isEmpty(link_id)) {
			super.renderText(response, "0");
			return null;
		}
		if (StringUtils.isEmpty(anonymous)) {// 如果没有选择的话，则默认是非匿名评论
			anonymous = "0";
		}

		PeProdUser user = checkUserById(user_id);
		if (null == user) {
			super.renderText(response, "0");
			return null;
		} else {
			KonkaCommentInfo commentInfo = new KonkaCommentInfo();
			commentInfo.setAdd_date(new Date());
			commentInfo.setAnonymous(Integer.parseInt(anonymous));
			commentInfo.setContent(content);
			commentInfo.setInfo_state(0l);
			commentInfo.setIs_del(0l);
			commentInfo.setLink_id(Long.parseLong(link_id));
			commentInfo.setLink_tab("KONKA_PE_ARTICLE_INFO");
			commentInfo.setMod_id(1l);
			commentInfo.setNick_name(user.getUser_name());
			commentInfo.setUser_id(user.getId());
			commentInfo.setUser_name(user.getUser_name());
			if (StringUtils.isNotBlank(par_id)) {
				commentInfo.setPar_id(Long.parseLong(par_id));
			}
			super.getFacade().getKonkaCommentInfoService().createKonkaCommentInfo(commentInfo);
		}

		super.renderText(response, "1");
		return null;
	}

	public ActionForward ajaxGetMessage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");// 用户id
		String link_id = (String) dynaBean.get("id");// 评论内容的id
		String info_id = (String) dynaBean.get("info_id");// 已经显示的评论列表中最小的iD
		//System.out.println("user_id=" + user_id + "    link_id=" + link_id + "   info_id=" + info_id);

		if (StringUtils.isEmpty(user_id)) {
			super.renderText(response, "0");
			return null;
		}
		if (StringUtils.isEmpty(link_id)) {
			super.renderText(response, "0");
			return null;
		}
		if (StringUtils.isEmpty(info_id)) {
			super.renderText(response, "0");
			return null;
		}
		PeProdUser user = checkUserById(user_id);
		if (null == user) {
			super.renderText(response, "0");
			return null;
		}
		KonkaCommentInfo commentInfo = new KonkaCommentInfo();
		commentInfo.setIs_del(0l);
		commentInfo.setLink_id(Long.parseLong(link_id));
		commentInfo.setLink_tab("KONKA_PE_ARTICLE_INFO");
		commentInfo.getMap().put("order_by_id_desc", true);
		List<KonkaCommentInfo> entityList = super.getFacade().getKonkaCommentInfoService()
				.getKonkaCommentInfoList(commentInfo);

		List<KonkaCommentInfo> entityList1 = new ArrayList<KonkaCommentInfo>();
		if (null != entityList && entityList.size() > 0) {
			for (KonkaCommentInfo ci : entityList) {
				if (ci.getId() > Long.parseLong(info_id)) {
					entityList1.add(ci);
				}
			}
		}

		logger.info("josn---->{}" + JSONArray.toJSONString(entityList));
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		super.renderJson(response, JSONArray.toJSONString(entityList));

		// SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// // 封装成JSON字符串
		// StringBuffer json = new StringBuffer("{");
		//
		// StringBuffer jsonBuffer = new StringBuffer();
		// if (null != entityList && entityList.size() > 0) {
		// for (KonkaCommentInfo ci : entityList) {
		// if (ci.getId() > Long.parseLong(info_id)) {
		// jsonBuffer.append("{");
		// jsonBuffer.append("\"add_date\":\"").append(sf.format(ci.getAdd_date()));
		// jsonBuffer.append("\",\"content\":\"").append(ci.getContent());
		// jsonBuffer.append("\",\"anonymous\":\"").append(ci.getAnonymous());
		// jsonBuffer.append("\",\"user_name\":\"").append(ci.getUser_name());
		// jsonBuffer.append("\",\"id\":\"").append(ci.getId());
		// jsonBuffer.append("\"},");
		// }
		// }
		// String listStr = StringUtils.removeEnd(jsonBuffer.toString(), ",");
		// json.append("\"list\" : [").append(listStr).append("]");
		// }
		// json.append("}");
		//
		// logger.info("josn---->{}" + json);
		// response.setContentType("application/json;charset=UTF-8");
		// response.setHeader("Cache-Control", "no-cache");
		// PrintWriter out = response.getWriter();
		// out.print(json);
		// out.flush();
		// out.close();
		return null;
	}
}
