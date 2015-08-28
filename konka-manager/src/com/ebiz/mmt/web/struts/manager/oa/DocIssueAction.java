package com.ebiz.mmt.web.struts.manager.oa;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaOaModuleType;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaoaCategory;
import com.ebiz.mmt.domain.KonkaoaDocInfo;
import com.ebiz.mmt.domain.KonkaoaDocRecipient;
import com.ebiz.mmt.domain.KonkaoaFilesProperty;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.mmt.web.util.Md5Encrypt;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Xu,ZhengYang
 */

public class DocIssueAction extends BaseMmtoaAction {
	
	private static HttpClient httpClient = null;

	private static HttpClient getHttpClient() {
		if (httpClient == null) {
			org.apache.http.params.HttpParams params = new BasicHttpParams();
			// Increase max total connection to 200
			ConnManagerParams.setMaxTotalConnections(params, 200);
			// Increase default max connection per route to 20
			ConnPerRouteBean connPerRoute = new ConnPerRouteBean(20);
			// Increase max connections for localhost:80 to 50
			HttpHost localhost = new HttpHost("locahost", 80);
			connPerRoute.setMaxForRoute(new HttpRoute(localhost), 50);
			ConnManagerParams.setMaxConnectionsPerRoute(params, connPerRoute);

			SchemeRegistry schemeRegistry = new SchemeRegistry();
			schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
			schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

			ClientConnectionManager cm = new ThreadSafeClientConnManager(params, schemeRegistry);
			httpClient = new DefaultHttpClient(cm, params);
		}
		return httpClient;
	}

	protected HttpResponse executeGetMethod(String url, HashMap<String, String> qparams, String charset) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for (String key : qparams.keySet()) {
			params.add(new BasicNameValuePair(key, qparams.get(key)));
		}
		try {
			URI qurl = URI.create(url);
			URI uri = URIUtils.createURI(qurl.getScheme(), qurl.getHost(), qurl.getPort(), qurl.getPath(),
					URLEncodedUtils.format(params, charset), null);
			logger.info("method = executeGetMethod, url={}", uri.toString());

			HttpGet httpget = new HttpGet(uri);
			HttpResponse response = getHttpClient().execute(httpget);
			return response;
		} catch (ClientProtocolException e) {
			logger.error("method = executeGetMethod, url = {}", url);
			logger.error("ClientProtocolException : " + e.getMessage());

			for (NameValuePair nameValuePair : params) {
				logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
			}
		} catch (IOException e) {
			logger.error("method = executeGetMethod, url = {}", url);

			logger.error(e.getMessage());
			for (NameValuePair nameValuePair : params) {
				logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
			}
		} catch (URISyntaxException e) {
			logger.error("method = executeGetMethod, url = {}", url);

			logger.error(e.getMessage());
			for (NameValuePair nameValuePair : params) {
				logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
			}
		}
		return null;
	}

	protected HttpResponse executePostMethod(String url, List<NameValuePair> formparams, String charset) {
		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, charset);
			HttpPost httppost = new HttpPost(url);
			httppost.setEntity(entity);
			HttpResponse response = getHttpClient().execute(httppost);
			return response;
		} catch (UnsupportedEncodingException e) {
			logger.error("method = executePostMethod, url = {}", url);
			logger.error(e.getMessage());
			for (NameValuePair nameValuePair : formparams) {
				logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
			}
		} catch (ClientProtocolException e) {
			logger.error("method = executePostMethod, url = {}", url);
			logger.error(e.getMessage());

			for (NameValuePair nameValuePair : formparams) {
				logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
			}
		} catch (IOException e) {
			logger.error("method = executePostMethod, url = {}", url);
			logger.error(e.getMessage());

			for (NameValuePair nameValuePair : formparams) {
				logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
			}
		}
		return null;
	}

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// if (null == super.checkUserModPopeDom(form, request, "1")) {
		// return super.checkPopedomInvalid(request, response);
		// }
		saveToken(request);

		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		
		// 登录用户信息
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		// 部门信息
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(ui.getDept_id());
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		// 分公司取得
		if(konkaDept.getDept_type() > 2){
			KonkaDept fgs_dept = super.getSuperiorForDeptType(ui.getDept_id(),3);
			dynaBean.set("fgs_dept_name", fgs_dept.getDept_name());
		}	
		// 当前年月日
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date date = new Date();
		String ym = sdf.format(date);
		dynaBean.set("yymm", ym.substring(2, ym.length()));
		
		KonkaoaCategory category = new KonkaoaCategory();
		category.setIs_del(0);

		// 文件类别
		category.setC_type(11); // 11：文件类别
		List<KonkaoaCategory> fileTypeList = super.getFacade().getKonkaoaCategoryService()
				.getKonkaoaCategoryList(category);
		request.setAttribute("fileTypeList", fileTypeList);

		// 重要级别impLevel
		category.setC_type(12); // 12：重要级别
		List<KonkaoaCategory> impLevelList = super.getFacade().getKonkaoaCategoryService()
				.getKonkaoaCategoryList(category);
		request.setAttribute("impLevelList", impLevelList);
		
		dynaBean.set("doc_type", "0");
		dynaBean.set("receive_type", "0");

		//模板
		KonkaOaModuleType konkaOaModuleType = new KonkaOaModuleType();
		konkaOaModuleType.setIs_del(0);
		konkaOaModuleType.setDept_id(ui.getDept_id());
		List<KonkaOaModuleType> konkaOaModuleTypeList = super.getFacade().getKonkaOaModuleTypeService().getKonkaOaModuleTypeList(konkaOaModuleType);
		
		if(null != konkaOaModuleTypeList && konkaOaModuleTypeList.size() > 0){
			request.setAttribute("konkaOaModuleTypeList", konkaOaModuleTypeList);
		}
		
		request.setAttribute("is_add", "2");
		
		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

//		PeRoleUser peRoleUser = new PeRoleUser();
//		peRoleUser.setUser_id(userInfo.getId());
//		peRoleUser = this.getFacade().getPeRoleUserService().getPeRoleUser(peRoleUser);

		KonkaoaDocInfo entity = new KonkaoaDocInfo();
		super.copyProperties(entity, dynaBean);
		if (null == entity.getReceive_type()) {
		}
		if (null == entity.getIs_del()) {
			entity.setIs_del(0);
			dynaBean.set("is_del", "0");
		}

		// if (peRoleUser.getRole_id() == 30) { // 分公司管理员可看到全部分公司的下发文件，并且可以修改，删除
		// entity.getMap().put("is_fgs_admin", userInfo.getDept_id());
		// } else {
		entity.getMap().put("user_id", userInfo.getId());
		// }

		Long recordCount = getFacade().getKonkaoaDocInfoService().getKonkaoaDocInfoCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaoaDocInfo> entityList = getFacade().getKonkaoaDocInfoService().getKonkaoaDocInfoPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		// 重要级别impLevel
		// KonkaoaCategory category = new KonkaoaCategory();
		// category.setIs_del(0);
		// category.setC_type(12); // 12：重要级别
		// List<KonkaoaCategory> impLevelList =
		// super.getFacade().getKonkaoaCategoryService().getKonkaoaCategoryList(category);
		// request.setAttribute("impLevelList", impLevelList);

		KonkaoaCategory _category = new KonkaoaCategory();
		_category.setIs_del(0);
		_category.setC_type(11); // 11：文件类别
		List<KonkaoaCategory> fileTypeList = super.getFacade().getKonkaoaCategoryService()
				.getKonkaoaCategoryList(_category);
		request.setAttribute("fileTypeList", fileTypeList);

		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (isCancelled(request)) {
			return list(mapping, form, request, response);
		}
		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}
//		resetToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		KonkaoaDocInfo entity = new KonkaoaDocInfo();
		super.copyProperties(entity, form);

		String link_user_ids = (String) dynaBean.get("link_user_ids");
		String link_users = (String) dynaBean.get("link_users");
		String link_dept_ids = (String) dynaBean.get("link_dept_ids");
		String link_depts = (String) dynaBean.get("link_depts");
		String domsg = (String) dynaBean.get("domsg");

		String[] file_types = request.getParameterValues("file_types");
		if (null != file_types && file_types.length > 0) {
			String file_type = StringUtils.join(file_types, ",");
			file_type = "," + file_type + ",";
			entity.setFile_type(file_type);
		}

		// List<UploadFile> uploadFileList = super.uploadFile(form, true, 0, new
		// int[] { 240 });

		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OA_PATH, true, 0, new int[] { 240 });

		List<KonkaPeAttachments> filesAttachmentList = new ArrayList<KonkaPeAttachments>();
		KonkaPeAttachments filesAttachment = null;
		for (UploadFile uploadFile : uploadFileList) {
			filesAttachment = new KonkaPeAttachments();
			filesAttachment.setFile_name(uploadFile.getFileName());
			filesAttachment.setFile_type(uploadFile.getContentType());
			filesAttachment.setFile_size(new Long(uploadFile.getFileSize()));
			filesAttachment.setSave_path(uploadFile.getFileSavePath());
			filesAttachment.setSave_name(uploadFile.getFileSaveName());
			filesAttachment.setIs_del(0l);
			filesAttachment.setLink_tab(uploadFile.getFormName());
			filesAttachment.setAdd_user_name(ui.getUser_name());
			filesAttachment.setAdd_user_id(ui.getId());
			filesAttachmentList.add(filesAttachment);

		}
		entity.setAttachmentList(filesAttachmentList);

		if (StringUtils.isNotBlank(link_user_ids) && StringUtils.isNotBlank(link_users)) {
			entity.getMap().put("link_user_ids", link_user_ids);
			entity.getMap().put("link_users", link_users);
		}
		if (StringUtils.isNotBlank(link_dept_ids) && StringUtils.isNotBlank(link_depts)) {
			entity.getMap().put("link_dept_ids", link_dept_ids);
			entity.getMap().put("link_depts", link_depts);
		}

		if (null == entity.getId()) {
			entity.setAdd_time(new Date());
			entity.setAdd_user_id(ui.getId());
			entity.setAdd_user_name(ui.getUser_name());
			
			// 登录用户所在部门的提交文件编号最大值
			String file_no_lm = dynaBean.get("file_no_left") + "" + dynaBean.get("file_no_middle");
			entity.setFile_no(getFilesMaxNo(file_no_lm));

			if("1".equals(domsg)){
				PeProdUser  user = new PeProdUser(); 
				user.setIs_del(0);
				Long count=0L;
				if(StringUtils.isBlank(link_user_ids)&&StringUtils.isBlank(link_dept_ids)){
					user.getMap().put("dept_id_all_prod_user", super.getSuperiorForDeptType(ui.getDept_id(), 3).getDept_id());
				}else {
					if(StringUtils.isNotBlank(link_dept_ids)){
						user.getMap().put("depts_id_all_prod_user", link_dept_ids.substring(0, link_dept_ids.lastIndexOf(",")));
						if(StringUtils.isNotBlank(link_user_ids)){
							user.getMap().put("link_user_ids", link_user_ids.substring(0, link_user_ids.lastIndexOf(",")));
						}
					}else if(StringUtils.isBlank(link_dept_ids)){
						if(StringUtils.isNotBlank(link_user_ids)){
							user.getMap().put("only_link_user_ids", link_user_ids.substring(0, link_user_ids.lastIndexOf(",")));
						}
					}
				}
				count = getFacade().getPeProdUserService().getPeProdUserCount(user);
				if(count ==0L){
					String msg = "所选人数为0";
					super.renderJavaScript(response, "window.onload=function(){alert('"+ msg +"');history.back(1);}");
					return null;
				}
				String msgMaxPerson = super.getSysSetting("msgMaxPerson");//获取发送短信规定的最大人员数量
				Long number =20L;
				if(GenericValidator.isLong(msgMaxPerson)){
					number = Long.valueOf(msgMaxPerson);
				}
				
				if(count > number){
					String msg = "人数超过"+ number +",不能发送短信";
					super.renderJavaScript(response, "window.onload=function(){alert('"+ msg +"');history.back(1);}");
					return null;
				}else{
					List<PeProdUser> list = getFacade().getPeProdUserService().getPeProdUserList(user);
					if(entity.getTitle()!=null &&entity.getAdd_user_name()!=null){
						for(PeProdUser p: list){
							String msgContent = "公告：《"+entity.getTitle() +"》，下发人："+entity.getAdd_user_name()+"，请注意查看。";
							if(p.getLink_phone()!=null){
								String inputline = this.sendSms(msgContent, p.getLink_phone());
								
								// 返回值对应表
								if ("0".equals(inputline)) {// 发送成功success;
									super.renderJavaScript(response, "window.onload=function(){alert('发送成功');history.back(1);}");
								} else if ("-1".equals(inputline)) {// 参数为空params is null;
									super.renderJavaScript(response, "window.onload=function(){alert('参数为空');history.back(1);}");
									return null;
								} else if ("-2".equals(inputline)) {// 手机号码不合法mp is illegal;
									super.renderJavaScript(response, "window.onload=function(){alert('手机号码不合法');history.back(1);}");
									return null;
								} else if ("-3".equals(inputline)) {// IOException;
									super.renderJavaScript(response, "window.onload=function(){alert('帐号或者密码错误');history.back(1);}");
									return null;
								} else if ("-4".equals(inputline)) {// upload exception;
									super.renderJavaScript(response, "window.onload=function(){alert('上传出现异常');history.back(1);}");
									return null;
								} else if ("-41".equals(inputline)) {// upload file is too big(size :2M);
									super.renderJavaScript(response, "window.onload=function(){alert('上传文件太大');history.back(1);}");
									return null;
								} else if ("-5".equals(inputline)) {// ParseException;
									super.renderJavaScript(response, "window.onload=function(){alert('通过异常');history.back(1);}");
									return null;
								} else if ("-11".equals(inputline)) {// 保留remain;
									super.renderJavaScript(response, "window.onload=function(){alert('定时发送时间不是有效的时间格式');history.back(1);}");
									return null;
								} else if ("-12".equals(inputline)) {// 校验不合法key illegal;
									super.renderJavaScript(response, "window.onload=function(){alert('key校验不合法');history.back(1);}");
									return null;
								} else if ("-13".equals(inputline)) {// ip illegal;
									super.renderJavaScript(response, "window.onload=function(){alert('IP地址不合法');history.back(1);}");
									return null;
								} else if ("-14".equals(inputline)) {// unknown error;
									super.renderJavaScript(response, "window.onload=function(){alert('未知错误');history.back(1);}");
									return null;
								} else if ("-101".equals(inputline)) {// 商户的参数为空mc param is null;
									super.renderJavaScript(response, "window.onload=function(){alert('商户的参数为空');history.back(1);}");
									return null;
								} else if ("-102".equals(inputline)) {// 商户不存在mc is not exist;
									super.renderJavaScript(response, "window.onload=function(){alert('商户不存在');history.back(1);}");
									return null;
								} else if ("-103".equals(inputline)) { // 商户密钥校验错误mc key is error;
									super.renderJavaScript(response, "window.onload=function(){alert('商户密钥校验错误');history.back(1);}");
									return null;
								} else if ("-104".equals(inputline)) {// 商户IP地址不是合同指定的mc ip is error;
									super.renderJavaScript(response, "window.onload=function(){alert('商户IP地址不是合同指定的');history.back(1);}");
									return null;
								} else if ("-105,**".equals(inputline)) {// 短信内容超过最大长度，**为数字表示最大长度
									super.renderJavaScript(response, "window.onload=function(){alert('短信内容超过最大长度60');history.back(1);}");
									return null;
								} else if ("-106,**".equals(inputline)) {// 群发短信超过最大限制，**为数字表示最大群发数
									super.renderJavaScript(response, "window.onload=function(){alert('群发短信超过最大限制');history.back(1);}");
									return null;
								} else {
									super.renderJavaScript(response, "window.onload=function(){alert('发送失败，请重试');history.back(1);}");
									return null;
								}
							}
						}
					}
				}
				
			}
		
			getFacade().getKonkaoaDocInfoService().createKonkaoaDocInfo(entity);
			// super.createSysOperLog(request, "DOC_INFO", entity.getId(), mod_id, "添加", BeanUtils.describe(entity)
			// .toString());
			saveMessage(request, "entity.inserted");
		} else if (null != entity) {
			getFacade().getKonkaoaDocInfoService().modifyKonkaoaDocInfo(entity);
			// super.createSysOperLog(request, "DOC_INFO", entity.getId(), mod_id, "修改", BeanUtils.describe(entity)
			// .toString());
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

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// if (null == super.checkUserModPopeDom(form, request, "2")) {
		// return super.checkPopedomInvalid(request, response);
		// }
		saveToken(request);
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaoaCategory category = new KonkaoaCategory();
		category.setIs_del(0);
		// 文件类别
		category.setC_type(11); // 11：文件类别
		List<KonkaoaCategory> fileTypeList = super.getFacade().getKonkaoaCategoryService()
				.getKonkaoaCategoryList(category);
		request.setAttribute("fileTypeList", fileTypeList);

		// 重要级别impLevel
		// category.setC_type(12); // 12：重要级别
		// List<KonkaoaCategory> impLevelList =
		// super.getFacade().getKonkaoaCategoryService().getKonkaoaCategoryList(category);
		// request.setAttribute("impLevelList", impLevelList);

		KonkaoaDocInfo entity = new KonkaoaDocInfo();
		entity.setId(new Long(id));
		entity = getFacade().getKonkaoaDocInfoService().getKonkaoaDocInfo(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		String file_type = entity.getFile_type();
		String[] file_types = StringUtils.split(file_type, ",");

		ArrayList<KonkaoaFilesProperty> filesProperty11List = new ArrayList<KonkaoaFilesProperty>();

		if (file_types != null) {
			for (int i = 0; i < file_types.length; i++) {

				if (file_types[i] != null) {
					KonkaoaFilesProperty filesProperty = new KonkaoaFilesProperty();
					filesProperty.setC_index(Long.valueOf(file_types[i]));
					filesProperty11List.add(filesProperty);
				}
			}
		}
		// dynaBean.set("file_types", file_types);
		request.setAttribute("filesProperty11List", filesProperty11List);

		// 附件
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(new Long(id));
		request.setAttribute("attachmentList",
				getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachmentsList(attachment));

		if (null != entity.getReceive_type() && entity.getReceive_type() == 1) {
			// 公文下发接收人
			KonkaoaDocRecipient docRecipient = new KonkaoaDocRecipient();
			docRecipient.setReceive_type(0);// 接收类型为：人
			docRecipient.setLink_id(entity.getId());
			docRecipient.setReceive_user_type(0);
			List<KonkaoaDocRecipient> drList = super.getFacade().getKonkaoaDocRecipientService()
					.getKonkaoaDocRecipientList(docRecipient);

			Long[] user_ids = new Long[drList.size()];
			String[] user_names = new String[drList.size()];
			if (null != drList && drList.size() > 0) {
				for (int i = 0; i < drList.size(); i++) {
					user_ids[i] = drList.get(i).getReceive_id();
					user_names[i] = drList.get(i).getReceive_user();
				}
			}
			dynaBean.set("user_ids", StringUtils.join(user_ids, ",") + ",");
			logger.info("=====> user_ids is {}", StringUtils.join(user_ids, ","));
			dynaBean.set("user_names", StringUtils.join(user_names, ","));

			docRecipient.setReceive_user_type(1);
			drList = super.getFacade().getKonkaoaDocRecipientService().getKonkaoaDocRecipientList(docRecipient);

			Long[] dept_ids = new Long[drList.size()];
			String[] dept_names = new String[drList.size()];
			if (null != drList && drList.size() > 0) {
				for (int i = 0; i < drList.size(); i++) {
					dept_ids[i] = drList.get(i).getReceive_id();
					dept_names[i] = drList.get(i).getReceive_user();
				}
			}
			dynaBean.set("dept_ids", StringUtils.join(dept_ids, ",") + ",");
			logger.info("=====> dept_ids is {}", StringUtils.join(dept_ids, ","));
			dynaBean.set("dept_names", StringUtils.join(dept_names, ","));
		}
		

		// //模板
		// PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		// KonkaOaModuleType konkaOaModuleType = new KonkaOaModuleType();
		// konkaOaModuleType.setIs_del(0);
		// konkaOaModuleType.setDept_id(ui.getDept_id());
		// List<KonkaOaModuleType> konkaOaModuleTypeList =
		// super.getFacade().getKonkaOaModuleTypeService().getKonkaOaModuleTypeList(konkaOaModuleType);
		//		
		// if(null != konkaOaModuleTypeList && konkaOaModuleTypeList.size() > 0){
		// request.setAttribute("konkaOaModuleTypeList", konkaOaModuleTypeList);
		// }
		
		return mapping.findForward("input");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// if (null == super.checkUserModPopeDom(form, request, "4")) {
		// return super.checkPopedomInvalid(request, response);
		// }

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaoaDocInfo entity = new KonkaoaDocInfo();
			entity.setId(new Long(id));
			entity.setIs_del(1);
			getFacade().getKonkaoaDocInfoService().modifyKonkaoaDocInfo(entity);
			// super.createSysOperLog(request, "DOC_INFO", entity.getId(), mod_id, "删除", BeanUtils.describe(entity)
			// .toString());
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaoaDocInfo entity = new KonkaoaDocInfo();
			entity.getMap().put("pks", pks);
			for (String xx : pks) {
				entity.setId(new Long(xx));
				entity.setIs_del(1);
				getFacade().getKonkaoaDocInfoService().modifyKonkaoaDocInfo(entity);
			}
			entity.setIs_del(1);
			getFacade().getKonkaoaDocInfoService().modifyKonkaoaDocInfo(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// super.getModPopeDom(form, request);
		// setNaviStringToRequestScope(form, request);

		// HttpSession session = request.getSession();
		// UserInfo ui = (UserInfo) session.getAttribute(Keys.ADMIN_USER);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaoaDocInfo entity = new KonkaoaDocInfo();
		entity.setId(new Long(id));
		entity = getFacade().getKonkaoaDocInfoService().getKonkaoaDocInfo(entity);

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		// 更新浏览次数，需要判断是全部用户，还是指定用户？注：receive_type 0:全部用户，1：指定用户
		// if (entity.getReceive_type() == 1) {
		KonkaoaDocRecipient dr = new KonkaoaDocRecipient();
		// dr.setReceive_id(ui.getId());// 崩溃，更新浏览次数，更新接收人id干吗？
		dr.setLink_id(Long.valueOf(id));
		dr.setIs_view(1);
		super.getFacade().getKonkaoaDocRecipientService().modifyKonkaoaDocRecipient(dr);
		// }

		KonkaoaCategory category = new KonkaoaCategory();
		category.setIs_del(0);
		// 文件类别
		category.setC_type(11); // 11：文件类别
		List<KonkaoaCategory> fileTypeList = super.getFacade().getKonkaoaCategoryService()
				.getKonkaoaCategoryList(category);
		request.setAttribute("fileTypeList", fileTypeList);

		// 重要级别impLevel
		category.setC_type(12); // 12：重要级别
		List<KonkaoaCategory> impLevelList = super.getFacade().getKonkaoaCategoryService()
				.getKonkaoaCategoryList(category);
		request.setAttribute("impLevelList", impLevelList);

		super.copyProperties(form, entity);

		String file_type = entity.getFile_type();
		String[] file_types = StringUtils.split(file_type, ",");

		request.setAttribute("file_types", file_types);

		// 附件
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(new Long(id));
		request.setAttribute("attachmentList",
				getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachmentsList(attachment));

		if (null != entity.getReceive_type() && entity.getReceive_type() == 1) {
			// 公文下发接收人
			KonkaoaDocRecipient docRecipient = new KonkaoaDocRecipient();
			docRecipient.setReceive_type(0);
			docRecipient.setLink_id(entity.getId());
			docRecipient.setReceive_user_type(0);// 接收类型为：人
			List<KonkaoaDocRecipient> drList = super.getFacade().getKonkaoaDocRecipientService()
					.getKonkaoaDocRecipientList(docRecipient);

			Long[] user_ids = new Long[drList.size()];
			String[] user_names = new String[drList.size()];
			if (null != drList && drList.size() > 0) {
				for (int i = 0; i < drList.size(); i++) {
					user_ids[i] = drList.get(i).getReceive_id();
					user_names[i] = drList.get(i).getReceive_user();
				}
			}
			dynaBean.set("user_ids", StringUtils.join(user_ids, ","));
			dynaBean.set("user_names", StringUtils.join(user_names, ","));

			docRecipient.setReceive_user_type(1);// 接收类型为：部门
			drList = super.getFacade().getKonkaoaDocRecipientService().getKonkaoaDocRecipientList(docRecipient);

			Long[] dept_ids = new Long[drList.size()];
			String[] dept_names = new String[drList.size()];
			if (null != drList && drList.size() > 0) {
				for (int i = 0; i < drList.size(); i++) {
					dept_ids[i] = drList.get(i).getReceive_id();
					dept_names[i] = drList.get(i).getReceive_user();
				}
			}
			dynaBean.set("mod_id", mod_id);
			dynaBean.set("dept_ids", StringUtils.join(dept_ids, ","));
			dynaBean.set("dept_names", StringUtils.join(dept_names, ","));
		}

		return mapping.findForward("view");

	}

	public ActionForward checkFileNo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String file_no = request.getParameter("file_no");
		String id = request.getParameter("id");
		KonkaoaDocInfo docInfo = new KonkaoaDocInfo();

		if (StringUtils.isNotBlank(id)) {
			docInfo.getMap().put("id", Long.valueOf(id));
		}
		docInfo.setFile_no(file_no);
		docInfo.setIs_del(0);
		Long count = super.getFacade().getKonkaoaDocInfoService().getKonkaoaDocInfoCount(docInfo);
		String flag = "";

		if (count == 0) {
			flag = "0"; // user not exits
		} else {
			flag = "1"; // already exits
		}
		super.renderJson(response, flag);
		return null;
	}

	public ActionForward open(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		KonkaPeAttachments entity = new KonkaPeAttachments();
		entity.setLink_id(new Long(id));
		entity = super.getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachments(entity);

		File f;
		int i = 0;
		byte[] b = new byte[1024];

		response.reset();
		// response.setContentType("application/x-msdownload;charset=GBK");
		// response.setContentType(entity.getFile_type());

		String fileName = entity.getFile_name();
		// String contentType= entity.getFile_type()+
		// "charset=UTF-8;attachment;filename="+fileName;
		response.setContentType(entity.getFile_type());
		response.setCharacterEncoding("UTF-8");
		fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "inline; filename=" + new String(fileName.getBytes("UTF-8"), "GBK"));

		String ctxDir = getServlet().getServletContext().getRealPath(String.valueOf(File.separatorChar));
		if (!ctxDir.endsWith(String.valueOf(File.separatorChar))) {
			ctxDir = ctxDir + File.separatorChar;
		}

		String docName = new String(entity.getSave_path().getBytes("utf-8"));

		f = new File(ctxDir + "/" + docName);
		// f = new File("D:\\Program Files\\tt.txt");
		// 读取文件
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));

		OutputStream out = response.getOutputStream();
		response.flushBuffer();

		while ((i = br.read(b)) > 0)
			out.write(b, 0, i);
		out.close();

		return null;
	}

	//发送短信
	public String sendSms(String msgContent,String mobilePhone) throws Exception {

		if (StringUtils.isBlank(msgContent)) {
//			super.renderJavaScript(response, "window.onload=function(){alert('当前发送内容为空，所以不提供短信发送功能。');window.close();}");
			return null;
		}

		// 测试用的uuid、key、网址，正式用的时候需要申请账号
//		 String url = "http://sms.mymyty.com/webservice/MerchantSendSms.do";
		String url = "http://192.168.0.29:8085/webservice/MerchantSendSms.do";// 外网上访问接口地址
		String mc_uuid = "cb59271b-c0d4-427c-a5dc-9cba9c0f52a4";// 签订商户协议后，在买卖提短信平台上面生产的“商户UUID”
		String key = "3D055735-03FB-4F09-AF29-87D362320AE6";// 校验码(content + mc_uuid + mobile + 商户协议中约定的key)经过MD5加密之后的值>
		String mobile = mobilePhone;// 发送短信的手机号码
		String content = msgContent;// 短信内容
		String out_sms_sn = "konka0000000001";

		String privateKey = content + mc_uuid + mobile + out_sms_sn + key;
		logger.info("URLEncoder.encode before   privateKey : {}", privateKey);

		String privateKey_MD5 = Md5Encrypt.md5(privateKey);
		logger.info("URLEncoder.encode after   privateKey : {}", privateKey_MD5);

		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("method", "sendMessage"));
		qparams.add(new BasicNameValuePair("mobile", mobile));
		qparams.add(new BasicNameValuePair("mc_uuid", mc_uuid));
		qparams.add(new BasicNameValuePair("content", content));
		qparams.add(new BasicNameValuePair("out_sms_sn", out_sms_sn));
		qparams.add(new BasicNameValuePair("key", privateKey_MD5));

		logger.info("********************* call starting... ***********************");
		HttpResponse ret = executePostMethod(url, qparams, Constants.SYS_ENCODING);

		// logger.info("response entity : {}", EntityUtils.toString(ret.getEntity()));
		// logger.info("********************* call end. ***********************");

		if (null == ret) {// 没有任何返回值
//			super.renderJavaScript(response, "window.onload=function(){alert('发送失败，请重试');window.close();}");
			return null;
		}

		String inputline = EntityUtils.toString(ret.getEntity());

		logger.info("--------------------------------------+{"+ inputline+ "}");

		

		// super.renderJavaScript(response, "window.onload=function(){alert('" + inputline + "');window.close();}");
		return inputline;
	}

}