package com.ebiz.mmt.web.struts.manager.chengduoa;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringEscapeUtils;
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
import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.service.impl.InteractWebServiceImpl;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.util.Md5Encrypt;

public class DiaLogAction extends BaseMmtoaAction {

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

	public ActionForward selectUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String orgId = (String) dynaBean.get("myselect");// 部门ID，如果不传择默认为1
		String selectedUsersID = (String) dynaBean.get("selectedUsersID");// 已经选择的人员ID字符串，格式：***,**,**
		String key_word = (String) dynaBean.get("key_word"); // 搜索关键字 姓名
		String selectype = (String) dynaBean.get("selectype");// 传值为：signal时表示单选；不传则表示多选
		String selectedIds = (String) dynaBean.get("selectedIds");// 已经选择的人员ID字符串，格式：***,**,**,用于审批流程

		if (!"".equals(selectedUsersID) && selectedUsersID != null && selectedUsersID.indexOf(",") > 0) {
			selectedUsersID = selectedUsersID.substring(0, selectedUsersID.length() - 1);
		}

		if (!"".equals(selectedIds) && selectedIds != null && selectedIds.indexOf(",") > 0) {
			dynaBean.set("selectedIds", selectedIds.substring(0, selectedIds.length() - 1));
			selectedIds = selectedIds.substring(0, selectedIds.length() - 1);
		}

		PeProdUser peProdUser = new PeProdUser();
		peProdUser.setIs_del(0);

		if (StringUtils.isNotBlank(key_word)) {
			peProdUser.getMap().put("real_name_like", key_word);
		}

		PeProdUser _peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		KonkaDept kd = super.getSuperiorForDeptType(_peProdUser.getDept_id(), 3);
		if (null != kd) {
			if (null != kd.getDept_id() && !"".equals(String.valueOf(kd.getDept_id()))) {
				peProdUser.getMap().put("dept_id_in", kd.getDept_id());
			}
		}

		peProdUser.getMap().put("user_id", _peProdUser.getId());
		if (!"".equals(selectedUsersID) && selectedUsersID != null && !"".equals(selectedIds) && selectedIds != null) {
			String sumIds = selectedUsersID + "," + selectedIds;
			if (!",".equals(StringUtils.trim(sumIds))) {
				peProdUser.getMap().put("id_not_in", sumIds);
				peProdUser.getMap().put("ids_not_in", sumIds);
			}
		} else {
			if (!"".equals(selectedUsersID) && selectedUsersID != null) {
				peProdUser.getMap().put("id_not_in", selectedUsersID);
				peProdUser.getMap().put("ids_not_in", selectedUsersID);
			} else {
				peProdUser.getMap().put("id_not_in", selectedIds);
				peProdUser.getMap().put("ids_not_in", selectedIds);
			}
		}

		peProdUser.getRow().setCount(100);
		List<PeProdUser> peProdUserList = super.getFacade().getPeProdUserService().getPeProdUserListForSelectUser(
				peProdUser);
		// String noStr = super.getSysSetting("authorisedUserName");
		for (PeProdUser _t : peProdUserList) {
			_t.getMap().put("department", _t.getDepartment() + "(" + _t.getReal_name() + ")");
		}
		request.setAttribute("entityList", peProdUserList);

		if (StringUtils.isNotBlank(selectedUsersID)) {
			PeProdUser user = new PeProdUser();
			user.setIs_del(0);
			user.getMap().put("ids", selectedUsersID);
			user.getMap().put("id_not_in", null);
			List<PeProdUser> selectPeProdUserList = super.getFacade().getPeProdUserService().getPeProdUserList(user);
			for (PeProdUser _t : selectPeProdUserList) {
				_t.getMap().put("department", _t.getDepartment() + "(" + _t.getReal_name() + ")");
			}
			request.setAttribute("selectList", selectPeProdUserList);
		}

//		PeRoleUser peRoleUser = (PeRoleUser) super.getSessionAttribute(request, Constants.ROLE_INFO);
		

		@SuppressWarnings("unchecked")
		List<PeRoleUser> peRoleUserList = (List<PeRoleUser>) request.getSession()
				.getAttribute(Constants.ROLE_INFO_LIST);
		boolean role_id_le_29 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() <= 29) {
				role_id_le_29 = true;
				break;
			}
		}
		if (role_id_le_29) {// 分公司用户以上的
			request.setAttribute("konkadeptList", getFacade().getKonkaDeptService()
					.getKonkaDeptListWithTreeNameAndFullName(new KonkaDept()));
		}

		String address = "/chengduoa/Dialog/selectUser.jsp?1=1&";
		if (selectype != null)
			address = address + "selectype=signal&";
		else if (orgId != null)
			address += "myselect=" + orgId + "&";
		return new ActionForward(address);
	}

	public ActionForward selectDept(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String orgId = (String) dynaBean.get("myselect");// 部门ID，如果不传择默认为1
		String selectedDeptsID = (String) dynaBean.get("selectedDeptsID");// 已经选择的部门的ID字符串，格式：***,**,**
		String selectype = (String) dynaBean.get("selectype");// 传值为：signal
		// 时表示单选；不传则表示多选
		String search_dept_name = (String) dynaBean.get("search_dept_name");// 部门名称搜索

		// 获取登陆者的信息
		PeProdUser PeProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		// setNaviStringToRequestScope(form, request);

		KonkaDept entity = new KonkaDept();
		super.copyProperties(entity, form);

		// 下面一行在 2013/6/3 被Xing,Xiudong注释，放开部门查询只能查询其部门及其子部门的限制，其后两行为新增解决方案
//		entity.getMap().put("dept_id", PeProdUser.getDept_id());
		// KonkaDept dept_3 =
		// super.getSuperiorForDeptType(PeProdUser.getDept_id(), 3);

		// entity.getMap().put("dept_id", dept_3.getDept_id());

		// update by zhou
		entity.getMap().put("dept_id", PeProdUser.getDept_id());

		if (!"".equals(selectedDeptsID) && selectedDeptsID != null) {
			selectedDeptsID = selectedDeptsID.substring(0, selectedDeptsID.length() - 1);
		}

		entity.getMap().put("dept_not_in", selectedDeptsID);

		if (StringUtils.isNotBlank(search_dept_name)) {
			entity.getMap().put("dept_name_like", search_dept_name);
		}

		// 部门列表
		List<KonkaDept> konkadeptList = getFacade().getKonkaDeptService().getKonkaDeptListWithTreeNameForProdUser(
				entity);
		request.setAttribute("konkadeptList", konkadeptList);

		if (!"".equals(selectedDeptsID) && selectedDeptsID != null) {
			entity.getRow().setCount(100);
			entity.getMap().put("dept_ids", selectedDeptsID);
			entity.getMap().put("dept_not_in", null);
			List<KonkaDept> selectList = getFacade().getKonkaDeptService().getKonkaDeptListWithTreeNameForProdUser(
					entity);
			request.setAttribute("selectList", selectList);
		}

		entity.getMap().put("selectype", selectype);
		String address = "/chengduoa/Dialog/selectDept.jsp?1=1&";
		if (selectype != null)
			address = address + "selectype=signal&";
		else if (orgId != null)
			address += "myselect=" + orgId + "&";
		return new ActionForward(address);
	}

	public ActionForward getQueryUserNames(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String key_word = (String) dynaBean.get("key_word");
		String role_id = (String) dynaBean.get("role_id");// 角色ID
		String selectedUsersID = (String) dynaBean.get("selectedUsersID");// 已经选择的人员ID字符串，格式：***,**,**
		String selectedIds = (String) dynaBean.get("selectedIds");// 已经选择的人员ID字符串，格式：***,**,**,用于审批流程

		StringBuffer sb = new StringBuffer("[");

		PeProdUser peProdUser = new PeProdUser();

		if (!"".equals(selectedUsersID) && selectedUsersID != null && selectedUsersID.indexOf(",") > 0) {
			selectedUsersID = selectedUsersID.substring(0, selectedUsersID.length());
		}

		peProdUser.setIs_del(new Integer(0));
		peProdUser.getMap().put("real_name_like", key_word);
		peProdUser.getMap().put("role_id_not_null", role_id);

		if ("" != selectedUsersID && selectedUsersID != null && "" != selectedIds && selectedIds != null) {
			String sumIds = selectedUsersID + "," + selectedIds;
			peProdUser.getMap().put("id_not_in", sumIds);
		} else {
			if ("" != selectedUsersID && selectedUsersID != null) {
				peProdUser.getMap().put("id_not_in", selectedUsersID);
			} else {
				peProdUser.getMap().put("id_not_in", selectedIds);
			}
		}
		peProdUser.getMap().put("dept_id_in", role_id);

		PeProdUser _peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

//		PeRoleUser roleUser = new PeRoleUser();
//		roleUser.setUser_id(_peProdUser.getId());
//		roleUser = getFacade().getPeRoleUserService().getPeRoleUser(roleUser);

		KonkaDept kd = super.getSuperiorForDeptType(_peProdUser.getDept_id(), 3);
		if (null != kd) {
			peProdUser.getMap().put("dept_id_in", kd.getDept_id());
		}

		// peProdUser.getMap().put("user_id", _peProdUser.getId());

		peProdUser.getRow().setCount(100);
		List<PeProdUser> PeProdUserList = getFacade().getPeProdUserService().getPeProdUserListForSelectUser(peProdUser);
		for (PeProdUser entity : PeProdUserList) {
			sb.append("{\"user_id\":\"").append(String.valueOf(entity.getId())).append("\",");
			sb.append("\"user_name\":\"");
			sb.append(StringEscapeUtils.escapeJavaScript(entity.getDepartment() + "(" + entity.getReal_name() + ")"));
			sb.append("\"},");
		}
		sb.append("{\"end\":\"\"}]");

		super.renderJson(response, sb.toString());

		return null;
	}

	public ActionForward getQueryDeptNames(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String key_word = (String) dynaBean.get("key_word");
		String pageCount = (String) dynaBean.get("pageCount");
		String selectedDeptsID = (String) dynaBean.get("selectedDeptsID");// 已经选择的部门的ID字符串，格式：***,**,**

		StringBuffer sb = new StringBuffer("[");
		int count = 20;

		if (StringUtils.isNotBlank(pageCount)) {
			count = Integer.valueOf(pageCount);
		}

		KonkaDept entity = new KonkaDept();

		if (StringUtils.isNotBlank(key_word)) {
			entity.getMap().put("dept_name_like", key_word);
		}

		PeProdUser _peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		entity.getMap().put("dept_id", _peProdUser.getDept_id());

		if (!"".equals(selectedDeptsID) && selectedDeptsID != null) {
			entity.getMap().put("dept_not_in", selectedDeptsID);
		}
		entity.getRow().setCount(count);
		List<KonkaDept> organizationaList = getFacade().getKonkaDeptService().getKonkaDeptListWithTreeNameForProdUser(
				entity);
		for (KonkaDept temp : organizationaList) {
			sb.append("{\"dept_id\":\"").append(String.valueOf(temp.getDept_id())).append("\",");
			sb.append("\"dept_name\":\"").append(
					StringEscapeUtils.escapeJavaScript((String) temp.getMap().get("tree_name"))).append("\"},");
		}
		sb.append("{\"end\":\"\"}]");

		super.renderJson(response, sb.toString());

		return null;
	}

	public ActionForward openSms(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaoaFiles KonkaoaFiles = new KonkaoaFiles();
		KonkaoaFiles.setId(new Long(id));
		KonkaoaFiles = super.getFacade().getKonkaoaFilesService().getKonkaoaFiles(KonkaoaFiles);
		if (null == KonkaoaFiles) {
			super.renderJavaScript(response, "window.onload=function(){alert('参数有误');window.close();}");
			return null;
		} else {
			request.setAttribute("KonkaoaFiles", KonkaoaFiles);
		}
		PeProdUser ui = new PeProdUser();
		ui.setId(KonkaoaFiles.getCur_audit_user_id());
		ui = super.getFacade().getPeProdUserService().getPeProdUser(ui);

		if (ui == null || StringUtils.isEmpty(ui.getLink_phone())) {
			super.renderJavaScript(response,
					"window.onload=function(){alert('当前审批人没有填写手机号码，所以不能使用催办功能。');window.close();}");
			return null;
		}

		super.copyProperties(form, KonkaoaFiles);
		request.setAttribute("peProdUser", ui);

		String address = "/chengduoa/Dialog/openSms.jsp";
		return new ActionForward(address);
	}

	public ActionForward sendSms(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String msgContent = (String) dynaBean.get("sms_comment");// 发送内容
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.renderJavaScript(response, "window.onload=function(){alert('参数有误');window.close();}");
			return null;
		}

		if (null == msgContent) {
			super
					.renderJavaScript(response,
							"window.onload=function(){alert('当前发送内容为空，所以不提供短信发送功能。');window.close();}");
			return null;
		}

		KonkaoaFiles konkaoaFiles = new KonkaoaFiles();
		konkaoaFiles.setId(new Long(id));
		konkaoaFiles = super.getFacade().getKonkaoaFilesService().getKonkaoaFiles(konkaoaFiles);
		PeProdUser ui = new PeProdUser();
		ui.setId(konkaoaFiles.getCur_audit_user_id());
		ui = super.getFacade().getPeProdUserService().getPeProdUser(ui);

		logger.info("-------------------------------{}", StringUtils.isEmpty(ui.getLink_phone()));
		if (ui == null || StringUtils.isEmpty(ui.getLink_phone())) {
			super.renderJavaScript(response,
					"window.onload=function(){alert('当前审批人没有填写手机号码，所以不能使用催办功能。');window.close();}");
			return null;
		}

		// 测试用的uuid、key、网址，正式用的时候需要申请账号
		// String url = "http://sms.mymyty.com/webservice/MerchantSendSms.do";
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
		qparams.add(new BasicNameValuePair("method", "sendMessages"));
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
			return null;
		}

		String inputline = EntityUtils.toString(ret.getEntity());

		// 返回值对应表
		if ("0".equals(inputline)) {// 发送成功success;
			super.renderJavaScript(response, "window.onload=function(){alert('发送成功');window.close();}");
		} else if ("-1".equals(inputline)) {// 参数为空params is null;
			super.renderJavaScript(response, "window.onload=function(){alert('参数为空');window.close();}");
		} else if ("-2".equals(inputline)) {// 手机号码不合法mp is illegal;
			super.renderJavaScript(response, "window.onload=function(){alert('手机号码不合法');window.close();}");
		} else if ("-3".equals(inputline)) {// IOException;
			super.renderJavaScript(response, "window.onload=function(){alert('帐号或者密码错误');window.close();}");
		} else if ("-4".equals(inputline)) {// upload exception;
			super.renderJavaScript(response, "window.onload=function(){alert('上传出现异常');window.close();}");
		} else if ("-41".equals(inputline)) {// upload file is too big(size :2M);
			super.renderJavaScript(response, "window.onload=function(){alert('上传文件太大');window.close();}");
		} else if ("-5".equals(inputline)) {// ParseException;
			super.renderJavaScript(response, "window.onload=function(){alert('通过异常');window.close();}");
		} else if ("-11".equals(inputline)) {// 保留remain;
			super.renderJavaScript(response, "window.onload=function(){alert('定时发送时间不是有效的时间格式');window.close();}");
		} else if ("-12".equals(inputline)) {// 校验不合法key illegal;
			super.renderJavaScript(response, "window.onload=function(){alert('key校验不合法');window.close();}");
		} else if ("-13".equals(inputline)) {// ip illegal;
			super.renderJavaScript(response, "window.onload=function(){alert('IP地址不合法');window.close();}");
		} else if ("-14".equals(inputline)) {// unknown error;
			super.renderJavaScript(response, "window.onload=function(){alert('未知错误');window.close();}");
		} else if ("-101".equals(inputline)) {// 商户的参数为空mc param is null;
			super.renderJavaScript(response, "window.onload=function(){alert('商户的参数为空');window.close();}");
		} else if ("-102".equals(inputline)) {// 商户不存在mc is not exist;
			super.renderJavaScript(response, "window.onload=function(){alert('商户不存在');window.close();}");
		} else if ("-103".equals(inputline)) { // 商户密钥校验错误mc key is error;
			super.renderJavaScript(response, "window.onload=function(){alert('商户密钥校验错误');window.close();}");
		} else if ("-104".equals(inputline)) {// 商户IP地址不是合同指定的mc ip is error;
			super.renderJavaScript(response, "window.onload=function(){alert('商户IP地址不是合同指定的');window.close();}");
		} else if ("-105,**".equals(inputline)) {// 短信内容超过最大长度，**为数字表示最大长度
			super.renderJavaScript(response, "window.onload=function(){alert('短信内容超过最大长度60');window.close();}");
		} else if ("-106,**".equals(inputline)) {// 群发短信超过最大限制，**为数字表示最大群发数
			super.renderJavaScript(response, "window.onload=function(){alert('群发短信超过最大限制');window.close();}");
		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('');window.close();}");
		}

		logger.info("--------------------------------------+{}", inputline);
		// super.renderJavaScript(response, "window.onload=function(){alert('" + inputline + "');window.close();}");
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
}
