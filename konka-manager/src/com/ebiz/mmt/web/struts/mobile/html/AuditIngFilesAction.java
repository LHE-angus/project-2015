package com.ebiz.mmt.web.struts.mobile.html;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.KonkaoaFilesAuditNode;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.service.impl.InteractWebServiceImpl;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.mobile.admin.MobileBaseAction;
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
		String page = (String) dynaBean.get("currentPage");
		String pagelimit = (String) dynaBean.get("pagelimit");
		int currentPage = 1;
		int pageSize = 15;
		if (StringUtils.isNotEmpty(page)) {
			currentPage = Integer.parseInt(page);
		}
		if (StringUtils.isNotEmpty(pagelimit)) {
			pageSize = Integer.parseInt(pagelimit);
		}
		entity.getRow().setFirst((currentPage - 1) * pageSize);
		entity.getRow().setCount(pageSize);
		Long count = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesListForAuditIngCount(entity);
		
		if (count % pageSize > 0)
			request.setAttribute("pagelimit", (count / pageSize) + 1);
		else
			request.setAttribute("pagelimit", (count / pageSize));
		String outStr = "{htmlData:'";
		if (count > 0) {
			List<KonkaoaFiles> entityList = super.getFacade().getKonkaoaFilesService()
			.getKonkaoaFilesPaginatedListForAuditIng(entity);
			outStr += "<ol data-role=\"listview\">";
//			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			for (KonkaoaFiles _k : entityList) {
				outStr += "<li>";
				outStr += "<a href=\"javascript:void(0)\" onclick=location.href=\"AuditIngFiles_view.html?id="+_k.getId()+"\">";
				outStr += _k.getFile_no()+ " 《"+_k.getFile_title()+"》 ";
					if(_k.getFile_status()==0 || _k.getFile_status()==-3){
						outStr += "未审批";
					}else if(_k.getFile_status()==1 || _k.getFile_status()==-1|| _k.getFile_status()==-2){
						outStr += "审批中";
					}else if(_k.getFile_status()==2){
						outStr += "被驳回";
					}else if(_k.getFile_status()==3){
						outStr += "已批准";
					}
					outStr += "</a>";
					outStr += "</li>";
			}
			outStr += "</ol>',";
			outStr += "currentPage:" + currentPage + ",recordCount:" + count
					+ "}";
		} else {
			outStr += "',";
			outStr += "currentPage:" + currentPage + ",recordCount:" + count
					+ "}";
		}
		log.info(outStr);
		// 写日志
				createMobileSysOperLog(request, "KONKAOA_FILES", 720400l, "720400", "查询",
						"手机端-文件查询-列表");
				super.renderText(response, outStr);
				return null;
	}

	
	public ActionForward sendSms(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String outStr = "";
		outStr += "<ul data-role=\"listview\" data-inset=\"true\" data-theme=\"b\" data-divider-theme=\"a\">";
		outStr +="<li data-role=\"list-divider\">催办</li>";
		outStr +="<li>";
		String msg = null;
		if (!GenericValidator.isLong(id)) {
			msg="参数有误！";
			outStr +="<p><strong>结&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;果：</strong>"+msg+"</p>";
			outStr +="</li></ul>";
			super.renderText(response, outStr);
			return null;
		}
		
		KonkaoaFiles KonkaoaFiles = new KonkaoaFiles();
		KonkaoaFiles.setId(new Long(id));
		KonkaoaFiles = super.getFacade().getKonkaoaFilesService().getKonkaoaFiles(KonkaoaFiles);
		String msgContent = null;
		if (null == KonkaoaFiles) {
			msg="参数有误！";
			outStr +="<p><strong>结&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;果：</strong>"+msg+"</p>";
			outStr +="</li></ul>";
			super.renderText(response, outStr);
			return null;
		} else {
			msgContent = KonkaoaFiles.getSubmit_user()+"提交，"+KonkaoaFiles.getApply_user_name()+"负责的文件《"+KonkaoaFiles.getFile_title()+"》，请您及时办理。";
			outStr +="<p><strong>短信内容：</strong>"+msgContent+"</p>";
		}
		PeProdUser ui = new PeProdUser();
		ui.setId(KonkaoaFiles.getCur_audit_user_id());
		ui = super.getFacade().getPeProdUserService().getPeProdUser(ui);

		if (ui == null || StringUtils.isEmpty(ui.getLink_phone())) {
			msg="当前审批人没有填写手机号码，所以不能使用催办功能。";
			outStr +="<p><strong>结&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;果：</strong>"+msg+"</p>";
			outStr +="</li></ul>";
			super.renderText(response, outStr);
			return null;
		}

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
			outStr +="<p><strong>结&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;果：</strong>"+msg+"</p>";
			outStr +="</li></ul>";
			super.renderText(response, outStr);
			return null;
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
		outStr +="<p><strong>结&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;果：</strong>"+msg+"</p>";
		outStr +="</li></ul>";
		super.renderText(response, outStr);
		return null;
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
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		String id = (String) dynaBean.get("id");
		if(StringUtils.isBlank(id)){
			super.renderText(response, "错误！");
			return null;
		}

		KonkaoaFiles files = new KonkaoaFiles();
		files.setId(new Long(id));
		files = super.getFacade().getKonkaoaFilesService().getKonkaoaFiles(files);
		if (null == files) {
			super.renderText(response, "数据错误，或已被删除！");
			return null;
		}
		String outStr = "{htmlData:'";
		outStr += "<ul data-role=\"listview\" data-inset=\"true\" data-theme=\"b\" data-divider-theme=\"a\">";
		outStr +="<li data-role=\"list-divider\">标   题："+files.getFile_title()+"</li>";
		outStr +="<li>";
		outStr +="<p>编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号："+files.getFile_no()+"</p>";
		outStr +="<p>负&nbsp;&nbsp;责&nbsp;&nbsp;人："+files.getApply_user_name()+"</p>";
		if(files.getApply_user_tel()==null){
			outStr +="<p>电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：</p>";
		}else {
			outStr +="<p>电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话："+files.getApply_user_tel()+"</p>";
		}
		
		KonkaoaFilesAuditNode fan = new KonkaoaFilesAuditNode();
		fan.setLink_id(files.getId());
		fan.setAudit_type(0);
		List<KonkaoaFilesAuditNode> auditList = super.getFacade().getKonkaoaFilesAuditNodeService()
				.getKonkaoaFilesAuditNodeList(fan);

		if(auditList.size()!=0){
			String[] audit_user = new String[auditList.size()];
			for(int i=0;i<auditList.size();i++){
				audit_user[i]=auditList.get(i).getAudit_user();
			}
			outStr +="<p>审&nbsp;&nbsp;批&nbsp;&nbsp;人："+StringUtils.join(audit_user,",")+"</p>";
		}
		outStr +="<p>申&nbsp;&nbsp;请&nbsp;&nbsp;人："+files.getSubmit_user()+"</p>";
		if(files.getFile_status()==0 || files.getFile_status()==-3){
			outStr +="<p>当前状态：<strong>未审批</strong></p>";
		}else if(files.getFile_status()==1 || files.getFile_status()==-1|| files.getFile_status()==-2){
			outStr +="<p>当前状态：<strong>审批中</strong></p>";
		}else if(files.getFile_status()==2){
			outStr +="<p>当前状态：<strong>被驳回</strong></p>";
		}else if(files.getFile_status()==3){
			outStr +="<p>当前状态：<strong>已审批</strong></p>";
		}
		outStr +="<p>提交时间："+s.format(files.getSubmit_datetime())+"</p>";
		outStr +="<p>内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;容："+files.getContent()+"</p>";
		outStr += "</li>";
		outStr += "</ul>";
		// 审批记录显示
//		KonkaoaFilesAuditNode _fan = new KonkaoaFilesAuditNode();
//		_fan.setLink_id(files.getId());
//		_fan.setAudit_type(0);
//		_fan.getMap().put("is_audit", "is_audit");// 只查出经过审批后的审批记录
//		List<KonkaoaFilesAuditNode> auditNodeList=getFacade().getKonkaoaFilesAuditNodeService()
//				.getKonkaoaFilesAuditNodeList(_fan);
//		if(auditNodeList.size()!=0){
//			outStr +="<br/>";
//			outStr +="<br/>";
//			outStr +="<div data-role=\"controlgroup\"  data-type=\"horizontal\">";
//			outStr += "<ol data-role=\"listview\">";
//			for(KonkaoaFilesAuditNode t:auditNodeList){
//				outStr +="<li>";
//				if(t.getAudit_result()==2){
//					outStr +="<span style=\"color:green\">同意</span>";
//				}else if(t.getAudit_result()==1){
//					outStr +="<span style=\"color:red\">驳回</span>";
//				}else if(t.getAudit_result()==-1){
//					outStr +="<span style=\"color:red\">转发</span>";
//				}
//				outStr += "&nbsp;&nbsp;&nbsp;&nbsp;审批人："+t.getAudit_user();
//				outStr += "&nbsp;&nbsp;&nbsp;&nbsp;"+s.format(t.getAudit_datetime());
//				outStr +="</li>";
//			}
//			outStr +="</ol>";
//			outStr +="</div>";
//		}
		if(files.getFile_from()==1){
			outStr +="',file_from:1";
		}else{
			outStr +="',file_from:0";
		}
		if(files.getFile_status()==0||files.getFile_status()==-3){
			outStr +=",key:0";
		}else {
			outStr +=",key:1";
		}
		outStr +="}";
		super.renderText(response, outStr);
		return null;
	}
}