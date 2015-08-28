package com.ebiz.mmt.web.struts.mobile.admin;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
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

import com.ebiz.mmt.domain.KonkaExpenseClaims;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaoaCategory;
import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.KonkaoaFilesAuditNode;
import com.ebiz.mmt.domain.KonkaoaFilesProperty;
import com.ebiz.mmt.domain.KonkaoaFilesRecipient;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.service.impl.InteractWebServiceImpl;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.util.Md5Encrypt;

/**
 * @author Hui,Gang
 * @version Build 2010-12-16
 */
public class AuditIngFilesAction extends MobileBaseAction {
	private static HashMap<String, String> properties = new HashMap<String, String>();
	static {
		InputStream inputStream = InteractWebServiceImpl.class.getClassLoader().getResourceAsStream(
				"webservice-url.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (Object key : p.keySet()) {
			properties.put((String) key, (String) p.get(key));
		}
	}
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_MOBILE);

		String map_file_status = (String) dynaBean.get("map_file_status");
		String file_title_like = (String) dynaBean.get("file_title_like");
		String file_no = (String) dynaBean.get("file_no");
		String mod_id = (String) dynaBean.get("mod_id");
		
		request.setAttribute("file_no", file_no);
		request.setAttribute("file_title_like", file_title_like);
		dynaBean.set("map_file_status", map_file_status);
		dynaBean.set("file_no", file_no);
		dynaBean.set("file_title_like", file_title_like);
		dynaBean.set("mod_id", mod_id);

		KonkaoaFiles entity = new KonkaoaFiles();
		if (ui.getDept_id() != 17761) {// 信息中心可以查看所有的文件
			entity.getMap().put("audit_user_id", ui.getId());
			entity.getMap().put("is_xxzx", "true");
		}
		entity.setFile_type(0);// 表示查询的是文件提交
		// entity.getMap().put("lt_file_status", 3);
		entity.getMap().put("map_file_status", map_file_status);
		entity.getMap().put("file_title_like", file_title_like);
		entity.getMap().put("st_submit_datetime", (String) dynaBean.get("st_submit_datetime"));
		entity.getMap().put("en_submit_datetime", (String) dynaBean.get("en_submit_datetime"));
		entity.getMap().put("submit_user_like", (String) dynaBean.get("submit_user_like"));
		entity.getMap().put("shopIds", null);
		if(file_no!=null){
			entity.setFile_no(file_no);
		}

		entity.setIs_del(0);
		//每页面展示3条信息
		String page = (String) dynaBean.get("page");
		String forward = (String) dynaBean.get("forward");
		int currentPage = 1;
		int pageSize = 3;
		if (StringUtils.isEmpty(page))
			request.setAttribute("page", currentPage);
		else {
			currentPage = (Integer.parseInt(page))
					+ (Integer.parseInt(forward));
			request.setAttribute("page", currentPage);
		}
		entity.getRow().setFirst((currentPage - 1) * pageSize);
		entity.getRow().setCount(pageSize);
		
		Long recordCount = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesListForAuditIngCount(entity);
		
		if (recordCount % pageSize > 0)
			request.setAttribute("pagelimit", (recordCount / pageSize) + 1);
		else
			request.setAttribute("pagelimit", (recordCount / pageSize));
		if (recordCount > 0) {
			List<KonkaoaFiles> entityList = super.getFacade().getKonkaoaFilesService()
			.getKonkaoaFilesPaginatedListForAuditIng(entity);

			if(entityList.size()!=0){
				request.setAttribute("id", entityList.get(0).getId());
			}
			request.setAttribute("entityList", entityList);
		}
		// 写日志
				createMobileSysOperLog(request, "KONKAOA_FILES", 720400l, "720400", "查询",
						"手机端-文件查询-列表");
		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaoaFiles files = new KonkaoaFiles();
		files.setId(new Long(id));
		files = super.getFacade().getKonkaoaFilesService().getKonkaoaFiles(files);
		if (null == files) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		super.copyProperties(form, files);

		super.setCategoryListToRequestScope(request);

		// 费用申请：费用类别
		KonkaoaCategory category = new KonkaoaCategory();
		category.setIs_del(0);
		category.setC_type(0);// 获取费用申请的费用类别信息表
		request.setAttribute("categoryList", super.getFacade().getKonkaoaCategoryService()
				.getKonkaoaCategoryListForFiles(category));

		// 文件属性
		// 0,选择多种费用类别
		KonkaoaFilesProperty filesProperty = new KonkaoaFilesProperty();
		filesProperty.setLink_id(files.getId());
		filesProperty.setC_type(0);
		filesProperty.setAdd_type(0L);
		List<KonkaoaFilesProperty> filesPropertyList = super.getFacade().getKonkaoaFilesPropertyService()
				.getKonkaoaFilesPropertyList(filesProperty);

		KonkaoaFilesProperty property = new KonkaoaFilesProperty();
		property.setLink_id(files.getId());
		property.setC_type(0);
		property.setAdd_type(1L);
		List<KonkaoaFilesProperty> propertyList = super.getFacade().getKonkaoaFilesPropertyService()
				.getKonkaoaFilesPropertyList(property);
		for (KonkaoaFilesProperty kfc : propertyList) {
			PeProdUser ppu = new PeProdUser();
			ppu.setId(kfc.getAdd_user_id());
			ppu = super.getFacade().getPeProdUserService().getPeProdUser(ppu);
			if (ppu != null) {
				kfc.getMap().put("real_name", ppu.getReal_name());
			}
		}
		request.setAttribute("propertyList", propertyList);

		for (KonkaoaFilesProperty kfp : filesPropertyList) {
			KonkaoaCategory kc = new KonkaoaCategory();
			kc.setC_index(kfp.getC_index());
			kc = super.getFacade().getKonkaoaCategoryService().getKonkaoaCategory(kc);
			kfp.getMap().put("c_name", kc.getC_name());

			KonkaoaFilesProperty appProperty = new KonkaoaFilesProperty();
			appProperty.setLink_id(files.getId());
			appProperty.setAdd_type(1L);
			appProperty.setC_type(0);
			appProperty.setC_index(kfp.getId());
			List<KonkaoaFilesProperty> appList = getFacade().getKonkaoaFilesPropertyService()
					.getKonkaoaFilesPropertyList(appProperty);
			if (0 != appList.size()) {
				kfp.getMap().put("appList", appList);

			}
		}
		request.setAttribute("filesPropertyList", filesPropertyList);

		// 费用申请相关信息
		KonkaExpenseClaims kec = new KonkaExpenseClaims();
		kec.setFile_id(files.getId());
		kec = super.getFacade().getKonkaExpenseClaimsService().getKonkaExpenseClaims(kec);
		if (null != kec) {
			request.setAttribute("konkaExpenseClaims", kec);
			// 获得对应的申请网点的名称
			if (null != kec.getR3_shop_id()) {
				KonkaR3Shop shop = new KonkaR3Shop();
				shop.setId(kec.getR3_shop_id());
				shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
				if (null != shop) {
					request.setAttribute("shop_name", shop.getCustomer_name());
				}
			}
		}

		// 下发范围
		KonkaoaFilesRecipient fr = new KonkaoaFilesRecipient();
		fr.setLink_id(files.getId());
		List<KonkaoaFilesRecipient> filesRecipientList = getFacade().getKonkaoaFilesRecipientService()
				.getKonkaoaFilesRecipientList(fr);
		if (null != filesRecipientList) {
			String fa_ids = "", fa_names = "", dept_ids = "", dept_names = "";
			for (KonkaoaFilesRecipient _fr : filesRecipientList) {
				switch (_fr.getReceive_user_type()) {
				case 0:
					fa_ids = fa_ids.concat(_fr.getReceive_id().toString()).concat(",");
					fa_names = fa_names.concat(_fr.getReceive_user().toString()).concat(",");
					break;
				case 1:
					dept_ids = dept_ids.concat(_fr.getReceive_id().toString()).concat(",");
					dept_names = dept_names.concat(_fr.getReceive_user().toString()).concat(",");
					break;
				}

			}
			dynaBean.set("fa_ids", fa_ids);
			dynaBean.set("fa_names", StringUtils.substringBeforeLast(fa_names, ","));
			dynaBean.set("dept_ids", dept_ids);
			if (kec != null && kec.getColumn_6() != null) {
				dynaBean.set("column_6", kec.getColumn_6());
			}
			dynaBean.set("dept_names", StringUtils.substringBeforeLast(dept_names, ","));
		}

		// 附件
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(files.getId());
		attachment.setLink_tab("FILES");
		attachment.setIs_del(0l);
		request.setAttribute("attachmentList", super.getFacade().getKonkaPeAttachmentsService()
				.getKonkaPeAttachmentsList(attachment));

		// // 审批记录显示
		// FilesAuditNode filesAuditNode = new KonkaoaFilesAuditNode();
		// filesAuditNode.setLink_id(files.getId());
		// filesAuditNode.setAudit_type(0);// 审批
		// filesAuditNode.getMap().put("is_audit", "is_audit");// 审批过的
		// // filesAuditNode.setAudit_result(2); //审批记录无论同不同意，应该都要显示
		// // request.setAttribute("filesAuditNodeList",
		// getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeList(
		// // filesAuditNode));
		// request.setAttribute("filesAuditNodeList",
		// getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeListForView(
		// filesAuditNode));
		// 审批记录显示
		KonkaoaFilesAuditNode fan = new KonkaoaFilesAuditNode();
		fan.setLink_id(files.getId());
		fan.setAudit_type(0);
		fan.getMap().put("is_audit", "is_audit");
		request.setAttribute("filesAuditNodeList", getFacade().getKonkaoaFilesAuditNodeService()
				.getKonkaoaFilesAuditNodeListForView(fan));
		// .getKonkaoaFilesAuditNodeList(fan));

		// 审批人查询
		KonkaoaFilesAuditNode _fan_ = new KonkaoaFilesAuditNode();
		_fan_.setLink_id(files.getId());
		_fan_.setAudit_type(0);
		request.setAttribute("_filesAuditNodeList", getFacade().getKonkaoaFilesAuditNodeService()
				.getKonkaoaFilesAuditNodeList(_fan_));

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_MOBILE);
		dynaBean.set("mod_id", mod_id);

		// 设置为已查看状态
		KonkaoaFilesRecipient filesRecipient = new KonkaoaFilesRecipient();
		filesRecipient.setLink_id(files.getId());
		filesRecipient.setReceive_id(ui.getId());
		filesRecipient.setView_date_time(new Date());
		getFacade().getKonkaoaFilesRecipientService().modifyKonkaoaFilesRecipient(filesRecipient);

		return mapping.findForward("view");
	}
	
	public ActionForward sendSms(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String msg = null;
		if (!GenericValidator.isLong(id)) {
			msg="参数有误！";
			request.setAttribute("msg", msg);
			return mapping.findForward("input");
		}
		
		KonkaoaFiles KonkaoaFiles = new KonkaoaFiles();
		KonkaoaFiles.setId(new Long(id));
		KonkaoaFiles = super.getFacade().getKonkaoaFilesService().getKonkaoaFiles(KonkaoaFiles);
		if (null == KonkaoaFiles) {
			msg="参数有误！";
			request.setAttribute("msg", msg);
			return mapping.findForward("input");
		} else {
			request.setAttribute("KonkaoaFiles", KonkaoaFiles);
		}
		PeProdUser ui = new PeProdUser();
		ui.setId(KonkaoaFiles.getCur_audit_user_id());
		ui = super.getFacade().getPeProdUserService().getPeProdUser(ui);

		if (ui == null || StringUtils.isEmpty(ui.getLink_phone())) {
			msg="当前审批人没有填写手机号码，所以不能使用催办功能。";
			request.setAttribute("msg", msg);
			return mapping.findForward("input");
		}
		String msgContent = KonkaoaFiles.getSubmit_user()+"提交，"+KonkaoaFiles.getApply_user_name()+"负责的文件《"+KonkaoaFiles.getFile_title()+"》，请您及时办理。";
		request.setAttribute("msgContent", msgContent);

		KonkaoaFiles konkaoaFiles = new KonkaoaFiles();
		konkaoaFiles.setId(new Long(id));
		konkaoaFiles = super.getFacade().getKonkaoaFilesService().getKonkaoaFiles(konkaoaFiles);

		// 测试用的uuid、key、网址，正式用的时候需要申请账号
//		String url = "http://sms.mymyty.com/webservice/MerchantSendSms.do";
//		String url = "http://192.168.0.29:8085/webservice/MerchantSendSms.do";// 外网上访问接口地址
		String mc_uuid = "cb59271b-c0d4-427c-a5dc-9cba9c0f52a4";// 签订商户协议后，在买卖提短信平台上面生产的“商户UUID”
		String key = "3D055735-03FB-4F09-AF29-87D362320AE6";// 校验码(content + mc_uuid + mobile + 商户协议中约定的key)经过MD5加密之后的值>
		String mobile = ui.getLink_phone();// 发送短信的手机号码
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
		HttpResponse ret = executePostMethod(properties.get("kongka.cb.fsdx"), qparams, Constants.SYS_ENCODING);

		// logger.info("response entity : {}", EntityUtils.toString(ret.getEntity()));
		// logger.info("********************* call end. ***********************");

		if (null == ret) {// 没有任何返回值
			msg="发送失败，请重试";
			request.setAttribute("msg", msg);
			return mapping.findForward("input");
		}

		String inputline = EntityUtils.toString(ret.getEntity());

		logger.info("--------------------------------------+{}", inputline);

		// 返回值对应表
		if ("0".equals(inputline)) {// 发送成功success;
			msg="发送成功";
		} else if ("-1".equals(inputline)) {// 参数为空params is null;
			msg="参数为空";
		} else if ("-2".equals(inputline)) {// 手机号码不合法mp is illegal;
			msg="手机号码不合法";
		} else if ("-3".equals(inputline)) {// IOException;
			msg="帐号或者密码错误";
		} else if ("-4".equals(inputline)) {// upload exception;
			msg="上传出现异常";
		} else if ("-41".equals(inputline)) {// upload file is too big(size :2M);
			msg="上传文件太大";
		} else if ("-5".equals(inputline)) {// ParseException;
			msg="通过异常";
			request.setAttribute("msg", msg);
			return mapping.findForward("input");
		} else if ("-11".equals(inputline)) {// 保留remain;
			msg="定时发送时间不是有效的时间格式";
		} else if ("-12".equals(inputline)) {// 校验不合法key illegal;
			msg="key校验不合法";
		} else if ("-13".equals(inputline)) {// ip illegal;
			msg="IP地址不合法";
		} else if ("-14".equals(inputline)) {// unknown error;
			msg="未知错误";
		} else if ("-101".equals(inputline)) {// 商户的参数为空mc param is null;
			msg="商户的参数为空";
		} else if ("-102".equals(inputline)) {// 商户不存在mc is not exist;
			msg="商户不存在";
		} else if ("-103".equals(inputline)) { // 商户密钥校验错误mc key is error;
			msg="商户密钥校验错误";
		} else if ("-104".equals(inputline)) {// 商户IP地址不是合同指定的mc ip is error;
			msg="商户IP地址不是合同指定的";
		} else if ("-105,**".equals(inputline)) {// 短信内容超过最大长度，**为数字表示最大长度
			msg="短信内容超过最大长度60";
		} else if ("-106,**".equals(inputline)) {// 群发短信超过最大限制，**为数字表示最大群发数
			msg="群发短信超过最大限制";
		} else {
			msg="发送失败";
		}
		// 写日志
		createMobileSysOperLog(request, "KONKAOA_FILES", new Long(id), "720400", "催办",
				"手机端-文件查询-催办");
		request.setAttribute("msg", msg);
		return mapping.findForward("input");
	}

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
			HttpResponse response = (HttpResponse) getHttpClient().execute(httpget);
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

}