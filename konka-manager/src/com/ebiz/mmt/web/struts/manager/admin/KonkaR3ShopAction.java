package com.ebiz.mmt.web.struts.manager.admin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
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
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONException;
import org.json.JSONObject;

import com.ebiz.mmt.domain.EntpShop;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.MmtEntpShop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.StdEntpUser;
import com.ebiz.mmt.domain.UserInfo;
import com.ebiz.mmt.service.impl.InteractWebServiceImpl;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class KonkaR3ShopAction extends BaseAction {
	private HttpClient httpClient = null;

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

	private HttpClient getHttpClient() {
		if (httpClient == null) {
			HttpParams params = new BasicHttpParams();
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

	private HttpResponse executeGetMethod(String url, HashMap<String, String> qparams, String charset) {
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

	public ActionForward toR3Add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}

		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		dynaBean.set("queryString", super.serialize(request, "id", "method"));

		PeRoleUser role = new PeRoleUser();
		role.setUser_id(super.getSessionUserInfo(request).getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(role);

		boolean role_id_ge_30 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() >= 30L) {
				role_id_ge_30 = true;
				break;
			}
		}

		Long fgs_id = null;
		if (role_id_ge_30) {
			KonkaDept dept = new KonkaDept();
			dept.setDept_id(super.getSessionUserInfo(request).getDept_id());
			dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
			if (dept.getDept_type() == 3) {// 分公司
				fgs_id = super.getSessionUserInfo(request).getDept_id();
				dynaBean.set("branch_area_name", dept.getDept_name());
			} else if (dept.getDept_type() == 4 || dept.getDept_type() == 5) {// 经营部
				Long dept_id = super.getSessionUserInfo(request).getDept_id();
				fgs_id = super.getSuperiorForDeptType(dept_id, 3).getDept_id();
				dynaBean.set("branch_area_name", super.getSuperiorForDeptType(dept.getDept_id(), 3).getDept_name());
			}
		}

		List<KonkaDept> BranchList = super.getBranchListByJybId(0L, fgs_id);

		request.setAttribute("BranchList", BranchList);

		// 客户类型
		KonkaCategory kc = new KonkaCategory();
		kc.setC_type(10);
		kc.setIs_del(0);
		request.setAttribute("konkaCategoryList", super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc));

		// 企业类型
		kc = new KonkaCategory();
		kc.setC_type(12);
		kc.setIs_del(0);
		request.setAttribute("entpTypeList", super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc));

		// 客户规模（年销售额）
		kc = new KonkaCategory();
		kc.setC_type(13);
		kc.setIs_del(0);
		request.setAttribute("entpScaleList", super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc));

		return new ActionForward(response.encodeRedirectURL("/admin/KonkaR3Shop/r3Add.jsp"));
	}

	@Override
	public ActionForward getHandleList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;
		String branch_area_name = (String) dynaBean.get("branch_area_name");

		if (StringUtils.isEmpty(branch_area_name)) {
			return null;
		}
		KonkaDept dept = new KonkaDept();
		dept.setDept_id(super.getSessionUserInfo(request).getDept_id());
		dept = getFacade().getKonkaDeptService().getKonkaDept(dept);

		KonkaR3Shop r3 = new KonkaR3Shop();
		r3.setIs_del(0L);
		r3.setBranch_area_name(branch_area_name);
		// if(dept.getDept_type() == 4){
		// r3.setHandle_name(dept.getDept_name());
		// }
		// else if(dept.getDept_type() == 5){
		// r3.setHandle_name(super.getSuperiorForDeptType(dept.getDept_id(),
		// 4).getDept_name());
		// }
		List<KonkaR3Shop> handleList = getFacade().getKonkaR3ShopService().getKonkaR3ShopGroupByHandleName(r3);
		StringBuffer sb = new StringBuffer("[");
		if (handleList != null && handleList.size() != 0) {
			for (KonkaR3Shop t : handleList) {
				if (StringUtils.isNotBlank(t.getHandle_name())) {
					sb.append("{\"id\":\"" + t.getHandle_name().trim() + "\",");
					sb.append("\"name\":\"" + t.getHandle_name().trim() + "\"},");
				}
			}
		}
		sb.append("{\"author\":\"wy\"}]");
		log.info("=======sb.toString:  " + sb.toString());
		super.renderJson(response, sb.toString());
		return null;
	}

	// public ActionForward getHandleList(ActionMapping mapping, ActionForm
	// form, HttpServletRequest request,
	// HttpServletResponse response){
	// DynaBean dynaBean = (DynaBean) form;
	// String branch_dept_id = (String)dynaBean.get("branch_dept_id");
	//
	// if (StringUtils.isEmpty(branch_dept_id)) {
	// return null;
	// }
	//
	// PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request,
	// Constants.USER_INFO);
	//
	// PeRoleUser peRoleUser = new PeRoleUser();
	// peRoleUser.setUser_id(peProdUser.getId());
	// peRoleUser =
	// super.getFacade().getPeRoleUserService().getPeRoleUser(peRoleUser);
	// KonkaDept dept = new KonkaDept();
	// if(peRoleUser.getRole_id()>=30){
	// dept.getMap().put("dept_id", peProdUser.getDept_id());
	// }else if(peRoleUser.getRole_id()>=10 && peRoleUser.getRole_id()<30){
	// dept.getMap().put("dept_id", branch_dept_id);
	// }
	//
	// List<KonkaDept> deptList = super.getFacade().getKonkaDeptService()
	// .getKonkaDeptListWithTreeNameForProdUser(dept);
	//
	//
	// StringBuffer sb = new StringBuffer("[");
	// if(deptList!=null && deptList.size()!=0){
	// for(KonkaDept t : deptList){
	// sb.append("{\"id\":\"" + t.getDept_name() + "\",");
	// sb.append("\"name\":\"" + t.getMap().get("tree_name") + "\"},");
	// }
	// }
	// sb.append("{\"author\":\"wy\"}]");
	// log.info("=======sb.toString:  "+sb.toString());
	// super.renderJson(response, sb.toString());
	// return null;
	// }

	public ActionForward r3Add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.getModPopeDom(form, request);
		resetToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String createMmt = (String) dynaBean.get("createMmt");
		// String user_name = (String) dynaBean.get("user_name");
		// String user_type = (String) dynaBean.get("user_type");
		// String pass_word = (String) dynaBean.get("pass_word");
		// String real_name = (String) dynaBean.get("real_name");
		// String link_phone = (String) dynaBean.get("link_phone");
		// String link_addr = (String) dynaBean.get("link_addr");
		String[] r3_link_position = (String[]) dynaBean.get("r3_link_position");
		String[] r3_link_real_name = (String[]) dynaBean.get("r3_link_real_name");
		String[] r3_link_sex = (String[]) dynaBean.get("r3_link_sex");
		String[] r3_link_birthday = (String[]) dynaBean.get("r3_link_birthday");
		String[] r3_link_tel = (String[]) dynaBean.get("r3_link_tel");
		String[] r3_link_email = (String[]) dynaBean.get("r3_link_email");
		String[] r3_link_favor = (String[]) dynaBean.get("r3_link_favor");

		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		KonkaR3Shop r3Shop = new KonkaR3Shop();
		KonkaR3Shop r3 = new KonkaR3Shop();
		r3.setR3_code(dynaBean.get("r3_code").toString());
		r3.setIs_del(0L);
		List<KonkaR3Shop> r3List = getFacade().getKonkaR3ShopService().getKonkaR3ShopList(r3);
		if (r3List.size() != 0) {
			String msg = "R3编码已存在，请重新输入！";
			renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		super.copyProperties(r3Shop, form);

		r3Shop.setImport_user_id(ui.getId());
		r3Shop.setImport_date(new Date());
		r3Shop.setIs_del(0L);
		ActionForward forward = new ActionForward();
		if (StringUtils.isNotBlank(createMmt) && createMmt.equals("0")) {
			r3Shop.setIs_match(1L);

			HttpSession session = request.getSession();
			session.setAttribute("r3Shop", r3Shop);
			forward = new ActionForward(response.encodeRedirectURL("/admin/KonkaR3Shop/user.jsp"));
		} else if (StringUtils.isNotBlank(createMmt) && createMmt.equals("1")) {
			r3Shop.setIs_match(0L);

			// /*
			// * @author Ren,zhong
			// *
			// * @desc 保存r3网点同时保存登录用户
			// *
			// * @date 2013-06-06
			// */
			// PeProdUser user = new PeProdUser();
			// user.setUser_name(user_name);
			// if (StringUtils.isBlank(user_type)) {
			// user_type = "2";
			// }
			// user.setUser_type(Integer.valueOf(user_type));
			// DESPlus des = new DESPlus();
			// user.setPass_word(des.encrypt(pass_word));
			// user.setReal_name(real_name);
			// user.setLink_phone(link_phone);
			// user.setLink_addr(link_addr);
			// user.setProd_entp_id(185L);
			// r3Shop.setPeProdUser(user);

			// 客户联系人信息
			r3Shop.setR3_link_position(r3_link_position);
			r3Shop.setR3_link_real_name(r3_link_real_name);
			r3Shop.setR3_link_sex(r3_link_sex);
			r3Shop.setR3_link_birthday(r3_link_birthday);
			r3Shop.setR3_link_tel(r3_link_tel);
			r3Shop.setR3_link_email(r3_link_email);
			r3Shop.setR3_link_favor(r3_link_favor);

			// 维护时间
			r3Shop.setCreate_date(new Date());

			if (StringUtils.isNotBlank(town)) {
				r3Shop.setEntp_p_index(new Long(town));
			} else if (StringUtils.isNotBlank(country)) {
				r3Shop.setEntp_p_index(new Long(country));
			} else if (StringUtils.isNotBlank(city)) {
				r3Shop.setEntp_p_index(new Long(city));
			} else if (StringUtils.isNotBlank(province)) {
				r3Shop.setEntp_p_index(new Long(province));
			}
            if(null==r3Shop.getIs_sdf()){
            	r3Shop.setIs_sdf(0);
            }
            
			getFacade().getKonkaR3ShopService().createKonkaR3ShopAndLink(r3Shop);
			// getFacade().getKonkaR3ShopService().createKonkaR3Shop(r3Shop);
			// super.getFacade().getKonkaR3ShopService().createKonkaR3ShopWithPeProdUser(r3Shop);
			forward = new ActionForward(response.encodeRedirectURL("/admin/KonkaR3MmtMatch.do?method=list&is_kf=1&is_match=0"));
		}
		return forward;
	}

	public ActionForward toSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);

		String pass_word = (String) dynaBean.get("pass_word");
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		// String keySeq = (String) dynaBean.get("keySeq");
		//
		// dynaBean.set("keySeq_tag", keySeq);

		List<UploadFile> uploadFiles = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH);

		StdEntpUser stdEntpUser = new StdEntpUser();
		UserInfo userInfo = new UserInfo();

		super.copyProperties(userInfo, form);
		super.copyProperties(stdEntpUser, form);

		if (GenericValidator.isLong(country)) {
			userInfo.setP_index(new Long(country));
		} else if (GenericValidator.isLong(city)) {
			userInfo.setP_index(new Long(city));
		} else if (GenericValidator.isLong(province)) {
			userInfo.setP_index(new Long(province));
		}

		DESPlus des = new DESPlus();
		userInfo.setPassword(des.encrypt(pass_word));// 加密
		userInfo.getMap().put("noDESPluspass_word", pass_word);
		stdEntpUser.setPass_word(des.encrypt(pass_word));

		// // 密钥，密钥类型为空检查
		// if (StringUtils.isEmpty(keySeq)) {
		// String msg = getMessage(request, "client.keyseq.error");
		// renderJavaScript(response, "window.onload=function(){alert('" + msg +
		// "');history.back();}");
		// return null;
		// }
		// // 查询密钥信息
		// StdEntpUser seu = new StdEntpUser();
		// seu.getRow().setCount(2);
		// seu.setKey_seq(keySeq);
		// List<StdEntpUser> seuList =
		// super.getFacade().getStdEntpUserService().getStdEntpUserList(seu);
		//
		// // 密钥在数据库数据有多条数据
		// if (seuList.size() == 2) {
		// logger.error("key info toomany:{}",keySeq);
		// String msg = super.getMessage(request,
		// "client.login.stdentpuser.key.many");
		// renderJavaScript(response, "window.onload=function(){alert('" + msg +
		// "');history.back();}");
		// return null;
		// }

		// stdEntpUser.setKey_seq(keySeq);

		ArrayList<HashMap<String, Object>> pdTypeList = getMain_pd();

		request.setAttribute("pdTypeList", pdTypeList);

		if (0 != uploadFiles.size()) {
			userInfo.setUser_image(uploadFiles.get(0).getFileSavePath());
		}

		HttpSession session = request.getSession();
		session.setAttribute("stdEntpUser", stdEntpUser);
		session.setAttribute("userEntity", userInfo);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String[] main_pds = request.getParameterValues("main_pd");
		String main_pd = StringUtils.join(main_pds, ",");

		// StdEntpMain stdEntpMainCheck = new StdEntpMain();
		// List<UploadFile> uploadFiles = super.uploadFile(form, false);
		// String ctxDir =
		// getServlet().getServletContext().getRealPath(String.valueOf(File.separatorChar));

		KonkaR3Shop r3Shop = (KonkaR3Shop) request.getSession().getAttribute("r3Shop");
		// StdEntpMain stdEntpMain = new StdEntpMain();
		EntpShop entpShop = new EntpShop();
		MmtEntpShop konkaEntpShop = new MmtEntpShop();
		// StdEntpUser stdEntpUser = (StdEntpUser)
		// request.getSession().getAttribute("stdEntpUser");
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userEntity");

		// super.copyProperties(stdEntpMain, form);
		super.copyProperties(entpShop, form);
		entpShop.setMain_pd(main_pd);

		if (GenericValidator.isLong(country)) {
			entpShop.setP_index(new Integer(country));
		} else if (GenericValidator.isLong(city)) {
			entpShop.setP_index(new Integer(city));
		} else if (GenericValidator.isLong(province)) {
			entpShop.setP_index(new Integer(province));
		}

		// // 密钥，密钥类型为空检查
		// if (StringUtils.isEmpty(stdEntpUser.getKey_seq())) {
		// String msg = getMessage(request, "client.keyseq.error");
		// renderJavaScript(response, "window.onload=function(){alert('" + msg +
		// "');history.back();}");
		// return null;
		// }
		// // 查询密钥信息
		// StdEntpUser seu = new StdEntpUser();
		// seu.getRow().setCount(2);
		// seu.setKey_seq(stdEntpUser.getKey_seq());
		// List<StdEntpUser> seuList =
		// super.getFacade().getStdEntpUserService().getStdEntpUserList(seu);
		//
		// // 密钥在数据库数据有多条数据
		// if (seuList.size() == 2) {
		// logger.error("key info toomany:{}",stdEntpUser.getKey_seq());
		// String msg = super.getMessage(request,
		// "client.login.stdentpuser.key.many");
		// renderJavaScript(response, "window.onload=function(){alert('" + msg +
		// "');history.back();}");
		// return null;
		// }

		// for (UploadFile uploadFile : uploadFiles) {
		// if(uploadFile.getFileName().equals(stdEntpMain.getEntp_licence_img())){
		// stdEntpMain.setEntp_licence_img(uploadFile.getFileSavePath());
		// }else
		// if(uploadFile.getFileName().equals(stdEntpMain.getEntp_tax_licence_img())){
		// stdEntpMain.setEntp_tax_licence_img(uploadFile.getFileSavePath());
		// }else
		// if(uploadFile.getFileName().equals(stdEntpMain.getEntp_org_code_img())){
		// stdEntpMain.setEntp_org_code_img(uploadFile.getFileSavePath());
		// }
		// }

		// Date today = new Date();

		// 生成entp_id
		// Calendar cal = Calendar.getInstance();
		// Integer month = cal.get(Calendar.MONTH) +1 ;
		// Integer day = cal.get(Calendar.DATE) +1 ;
		// Integer hour = cal.get(Calendar.HOUR_OF_DAY) +1 ;
		// Integer minuter = cal.get(Calendar.MINUTE) +1 ;
		// Integer second = cal.get(Calendar.SECOND) +1 ;
		// String entp_code =
		// month.toString()+day.toString()+hour.toString()+minuter.toString()+second.toString();
		// String entp_id = "111"+ entp_code + "000";
		// stdEntpMainCheck.setEntp_id(Long.valueOf(entp_id));
		// stdEntpMainCheck=getFacade().getStdEntpMainService().getStdEntpMain(stdEntpMainCheck);
		//
		// while(null!=stdEntpMainCheck){
		// stdEntpMainCheck=null;
		// Calendar cal2 = Calendar.getInstance();
		// month = cal2.get(Calendar.MONTH) +1 ;
		// day = cal2.get(Calendar.DATE) ;
		// hour = cal2.get(Calendar.HOUR_OF_DAY) ;
		// minuter = cal2.get(Calendar.MINUTE) ;
		// second = cal2.get(Calendar.SECOND) ;
		// entp_code =
		// month.toString()+day.toString()+hour.toString()+minuter.toString()+second.toString();
		// entp_id = "111"+ entp_code + "000";
		// stdEntpMainCheck.setEntp_id(Long.valueOf(entp_id));
		// stdEntpMainCheck=getFacade().getStdEntpMainService().getStdEntpMain(stdEntpMainCheck);
		// }
		//
		// stdEntpMain.setP_index(entpShop.getP_index());
		// stdEntpMain.setEntp_id(Long.valueOf(entp_id));
		// stdEntpMain.setEntp_type(2);
		// stdEntpMain.setOwn_sys(3);
		// stdEntpMain.setRoot_entp_id(new Long(167));
		// stdEntpMain.setInfo_state(0);
		// stdEntpMain.setIs_record(0);
		// stdEntpMain.setSingle_callback(0);
		// stdEntpMain.setAdd_date(today);
		// stdEntpMain.setEntp_kind(1);
		//
		// entpShop.setShop_name(stdEntpMain.getEntp_name());
		// entpShop.setLink_user(stdEntpMain.getLinkman());
		// entpShop.setLink_phone(stdEntpMain.getTel());
		// entpShop.setStreet_addr(stdEntpMain.getAddr());
		// entpShop.setEntp_id(Long.valueOf(entp_id));
		// entpShop.setAdd_date(today);
		// entpShop.setIs_auth(1);
		// entpShop.setState(0);
		// entpShop.setShop_type(1);
		//
		// userInfo.setUser_type(1);
		// userInfo.setUser_state(0);
		// userInfo.setP_count(new Long(0));
		// userInfo.setO_count(new Long(0));
		// userInfo.setCard_audit_state(0);
		// userInfo.setAdd_date(today);
		// // userInfo.setIs_activate(1);

		// stdEntpUser.setUser_state(0);
		// stdEntpUser.setUser_type(2);
		// stdEntpUser.setOwn_sys(3);
		// stdEntpUser.setEntp_id(Long.valueOf(entp_id));
		// stdEntpUser.setMmt_user(stdEntpMain.getEntp_name());
		// stdEntpUser.setAdd_date(today);

		super.copyProperties(konkaEntpShop, entpShop);
		ActionForward forward = new ActionForward();
		forward = null;
		HashMap<String, Object> result = CreateMmtEntpShopUserInfo(entpShop, userInfo);
		// 返回值对应表
		if (result.get("reg_state") != null) {
			if (result.get("reg_state").toString().equals("1")) {// 发送成功success;
				entpShop.setShop_id(Long.valueOf(result.get("shop_id").toString()));
				getFacade().getKonkaR3ShopService().create(r3Shop, entpShop);// 匹配R3网点
				// HashMap<String, Object> key_result =
				// setKeySeq(userInfo);//连接密钥接口
				// if(key_result.get("opr_state")!=null){//连接成功
				// while("0".equals(key_result.get("opr_state").toString())&&"2105".equals(key_result.get("msg").toString())&&"2106".equals(key_result.get("msg").toString())){//连接成功但是创建密钥不成功且原因是密钥值的问题，反复生成直到成功为止
				// key_result = setKeySeq(userInfo);
				// }
				super.renderJavaScript(response, "window.onload=function(){alert('创建并匹配成功！');history.go(-5);}");
				// forward=this.list(mapping, form, request, response);
				forward = null;
				// }else if(key_result.get("opr_state")==null){//连接密钥接口失败
				// super.renderJavaScript(response,
				// "window.onload=function(){alert('商铺、用户信息创建并匹配成功，但密钥接口连接失败，请联系管理员');history.go(-5);}");
				// forward=null;
				// }
			} else if ("0".equals(result.get("reg_state").toString())) {// 失败
				log.info("2222222222222222222222");
				if ("1001".equals(result.get("msg").toString())) {// IP地址验证失败;
					super.renderJavaScript(response, "window.onload=function(){alert('IP地址验证失败');history.back();}");
				} else if ("1101".equals(result.get("msg").toString())) {// 用户名为空；
					super.renderJavaScript(response, "window.onload=function(){alert('用户名为空');history.back();}");
				} else if ("1102".equals(result.get("msg").toString())) {// upload
					// exception;
					super.renderJavaScript(response, "window.onload=function(){alert('用户名不合法');history.back();}");
				} else if ("1115".equals(result.get("msg").toString())) {// 用户名已存在;
					super.renderJavaScript(response, "window.onload=function(){alert('用户名已存在');history.back();}");
				} else if ("1103".equals(result.get("msg").toString())) {// 密码为空;
					super.renderJavaScript(response, "window.onload=function(){alert('密码为空');history.back();}");
				} else if ("1104".equals(result.get("msg").toString())) {// 密码过长;
					super.renderJavaScript(response, "window.onload=function(){alert('密码过长');history.back();}");
				} else if ("1105".equals(result.get("msg").toString())) {// 真实名过长;
					super.renderJavaScript(response, "window.onload=function(){alert('真实名过长');history.back();}");
				} else if ("1106".equals(result.get("msg").toString())) {// 性别为空;
					super.renderJavaScript(response, "window.onload=function(){alert('性别为空');history.back();}");
				} else if ("1107".equals(result.get("msg").toString())) {// 性别不合法;
					super.renderJavaScript(response, "window.onload=function(){alert('性别不合法');history.back();}");
				} else if ("1108".equals(result.get("msg").toString())) {// 出生日期不合法;
					super.renderJavaScript(response, "window.onload=function(){alert('出生日期不合法');history.back();}");
				} else if ("1109".equals(result.get("msg").toString())) {// 手机和固定电话号码不能同时为空;
					super.renderJavaScript(response,
							"window.onload=function(){alert('手机和固定电话号码不能同时为空');history.back();}");
				} else if ("1110".equals(result.get("msg").toString())) {// 手机号码不合法;
					super.renderJavaScript(response, "window.onload=function(){alert('手机号码不合法');history.back();}");
				} else if ("1111".equals(result.get("msg").toString())) {// 固定电话不合法;
					super.renderJavaScript(response, "window.onload=function(){alert('固定电话不合法');history.back();}");
				} else if ("1112".equals(result.get("msg").toString())) {// MSN号过长;
					super.renderJavaScript(response, "window.onload=function(){alert('MSN号过长');history.back();}");
				} else if ("1113".equals(result.get("msg").toString())) {// QQ号过长;
					super.renderJavaScript(response, "window.onload=function(){alert('QQ号过长');history.back();}");
				} else if ("1114".equals(result.get("msg").toString())) {// 用户所在地区编码值不合法;
					super.renderJavaScript(response, "window.onload=function(){alert('用户所在地区编码值不合法');history.back();}");
				} else if ("1201".equals(result.get("msg").toString())) {// 商铺名为空;
					super.renderJavaScript(response, "window.onload=function(){alert('商铺名为空');history.back();}");
				} else if ("1202".equals(result.get("msg").toString())) {// 商铺名过长;
					super.renderJavaScript(response, "window.onload=function(){alert('商铺名过长');history.back();}");
				} else if ("1224".equals(result.get("msg").toString())) {// 商铺名已存在;
					super.renderJavaScript(response, "window.onload=function(){alert('商铺名已存在');history.back();}");
				} else if ("1203".equals(result.get("msg").toString())) {// 商铺公告过长;
					super.renderJavaScript(response, "window.onload=function(){alert('商铺公告过长');history.back();}");
				} else if ("1204".equals(result.get("msg").toString())) {// 所属行业为空;
					super.renderJavaScript(response, "window.onload=function(){alert('所属行业为空');history.back();}");
				} else if ("1205".equals(result.get("msg").toString())) {// 所属行业过长;
					super.renderJavaScript(response, "window.onload=function(){alert('所属行业过长');history.back();}");
				} else if ("1206".equals(result.get("msg").toString())) {// 主营产品为空;
					super.renderJavaScript(response, "window.onload=function(){alert('主营产品为空');history.back();}");
				} else if ("1207".equals(result.get("msg").toString())) {// 主营产品过长;
					super.renderJavaScript(response, "window.onload=function(){alert('主营产品过长');history.back();}");
				} else if ("1208".equals(result.get("msg").toString())) {// 主营产品不合法;
					super.renderJavaScript(response, "window.onload=function(){alert('主营产品不合法');history.back();}");
				} else if ("1209".equals(result.get("msg").toString())) {// 商铺所属地区为空;
					super.renderJavaScript(response, "window.onload=function(){alert('商铺所属地区为空');history.back();}");
				} else if ("1210".equals(result.get("msg").toString())) {// 商铺所属地区编码值不合法;
					super.renderJavaScript(response, "window.onload=function(){alert('商铺所属地区编码值不合法');history.back();}");
				} else if ("1211".equals(result.get("msg").toString())) {// 地理位置经度为空;
					super.renderJavaScript(response, "window.onload=function(){alert('地理位置经度为空');history.back();}");
				} else if ("1212".equals(result.get("msg").toString())) {// 地理位置经度不合法;
					super.renderJavaScript(response, "window.onload=function(){alert('地理位置经度不合法');history.back();}");
				} else if ("1213".equals(result.get("msg").toString())) {// 地理位置纬度为空;
					super.renderJavaScript(response, "window.onload=function(){alert('地理位置纬度为空');history.back();}");
				} else if ("1214".equals(result.get("msg").toString())) {// 地理位置纬度不合法;
					super.renderJavaScript(response, "window.onload=function(){alert('地理位置纬度不合法');history.back();}");
				} else if ("1215".equals(result.get("msg").toString())) {// 联系人为空;
					super.renderJavaScript(response, "window.onload=function(){alert('联系人为空');history.back();}");
				} else if ("1216".equals(result.get("msg").toString())) {// 联系人过长;
					super.renderJavaScript(response, "window.onload=function(){alert('联系人过长');history.back();}");
				} else if ("1217".equals(result.get("msg").toString())) {// 联系电话为空;
					super.renderJavaScript(response, "window.onload=function(){alert('联系电话为空');history.back();}");
				} else if ("1218".equals(result.get("msg").toString())) {// 联系电话不合法;
					super.renderJavaScript(response, "window.onload=function(){alert('联系电话不合法');history.back();}");
				} else if ("1219".equals(result.get("msg").toString())) {// 街道地址为空;
					super.renderJavaScript(response, "window.onload=function(){alert('街道地址为空');history.back();}");
				} else if ("1220".equals(result.get("msg").toString())) {// 街道地址过长;
					super.renderJavaScript(response, "window.onload=function(){alert('街道地址过长');history.back();}");
				} else if ("1221".equals(result.get("msg").toString())) {// 地区邮编过长;
					super.renderJavaScript(response, "window.onload=function(){alert('地区邮编过长');history.back();}");
				} else if ("1222".equals(result.get("msg").toString())) {// 在线客服QQ过长;
					super.renderJavaScript(response, "window.onload=function(){alert('在线客服QQ过长');history.back();}");
				} else if ("1223".equals(result.get("msg").toString())) {// 支付宝账号过长;
					super.renderJavaScript(response, "window.onload=function(){alert('支付宝账号过长');history.back();}");
				} else if ("1224".equals(result.get("msg").toString())) {// 商铺名已存在;
					super.renderJavaScript(response, "window.onload=function(){alert('商铺名已存在');history.back();}");
				} else if ("1301".equals(result.get("msg").toString())) {// 注册完成后查询用户信息为空;
					super.renderJavaScript(response, "window.onload=function(){alert('注册完成后查询用户信息为空');history.back();}");
				} else if ("1302".equals(result.get("msg").toString())) {// 注册完成后查询商铺信息为空;
					super.renderJavaScript(response, "window.onload=function(){alert('注册完成后查询商铺信息为空');history.back();}");
				} else {
					super.renderJavaScript(response, "window.onload=function(){alert('');history.back();}");
				}
				forward = null;
			}
		} else if (result.get("reg_state") == null) {
			super.renderJavaScript(response, "window.onload=function(){alert('接口连接失败，请联系管理员');history.back();}");
			forward = null;
		}
		return forward;

		// // 判断密钥是否存在
		// if(seuList != null){
		// // 判断密钥是否被注销
		// Integer stdEntpUserState = seuList.get(0).getUser_state();
		// Integer own_sys = seuList.get(0).getOwn_sys();
		// if ((null == stdEntpUserState) || ("1".equals(own_sys) && 0 !=
		// stdEntpUserState) || ("2".equals(own_sys) && 1 != stdEntpUserState))
		// {
		// //密钥存在但是已被注销则更新
		// getFacade().getKonkaR3ShopService().update(r3Shop,entpShop,
		// stdEntpMain, stdEntpUser, userInfo ,konkaEntpShop);
		// }else{
		// //密钥存在但是没有被注销
		// String msg = super.getMessage(request,
		// "client.register.entpshop.had");
		// renderJavaScript(response, "window.onload=function(){alert('" + msg +
		// "');history.back();}");
		// return null;
		//
		// }
		// }
		// //密钥不存在则新建
		// if(seuList== null){
		// super.getFacade().getKonkaR3ShopService().create(r3Shop,entpShop,stdEntpMain,
		// stdEntpUser, userInfo ,konkaEntpShop);
		// }

		// super.saveMessage(request, "entity.inserted");
		// // the line below is added for pagination
		// StringBuffer pathBuffer = new StringBuffer();
		// pathBuffer.append("/admin/KonkaR3MmtMatch.do?method=list");
		// pathBuffer.append("&mod_id=" + 101010);
		// pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(r3Shop.getQueryString()));
		// ActionForward forward = new ActionForward(pathBuffer.toString(),
		// true);
		// // end
		// return forward;
	}

	public ActionForward validateName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String user_name = (String) dynaBean.get("user_name");
		UserInfo entity = new UserInfo();
		entity.setUser_name(user_name);
		entity.setUser_state(0);
		Long count = super.getFacade().getUserInfoService().getUserInfoCount(entity);
		if (0L == count) {
			super.renderText(response, "false");
		} else {
			super.renderText(response, "true");
		}
		return null;
	}

	public ActionForward validateR3Code(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		StringBuffer oper = new StringBuffer("{\"result\":");
		String r3_code = (String) dynaBean.get("r3_code");
		String now_id = (String) dynaBean.get("now_id"); // 用于修改：记录当前的id -----by
		// douqr

		KonkaR3Shop entity = new KonkaR3Shop();
		entity.setR3_code(r3_code);
		entity.setIs_del(0L);
		entity.getMap().put("now_id", now_id);
		Long count = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopCount(entity);
		if ("0".equals(count.toString())) {
			oper.append(false);
		} else {
			oper.append(true);
		}
		oper.append("}");
		super.render(response, oper.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward validateUserName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");
		String user_name = (String) dynaBean.get("user_name");
		String isExist = "null";
		logger.info("______________________user_id = " + user_id);
		if (StringUtils.isNotBlank(user_name)) {
			PeProdUser entity = new PeProdUser();
			if (StringUtils.isNotBlank(user_id)) {
				entity.getMap().put("not_this_id", user_id);
			}
			entity.setUser_name(user_name);
			Long recordCount = super.getFacade().getPeProdUserService().getPeProdUserCount(entity);
			if (0 == recordCount.intValue()) {
				isExist = "0";
			} else {
				isExist = "1";
			}
		}

		// super.render(response, isExist, "text/x-json;charset=UTF-8");
		super.renderJson(response, isExist);
		return null;
	}

	public HashMap<String, Object> CreateMmtEntpShopUserInfo(EntpShop entpShop, UserInfo userInfo) {

		HashMap<String, String> qparams = new HashMap<String, String>();
		qparams.put("user_name", userInfo.getUser_name());
		qparams.put("pass_word", userInfo.getPassword());
		qparams.put("realname", userInfo.getRealname());
		qparams.put("sex", userInfo.getSex().toString());
		if (null != userInfo.getBirthday()) {
			qparams.put("birthday", userInfo.getBirthday().toString());
		}
		qparams.put("mobile", userInfo.getMobile());
		qparams.put("tel", userInfo.getTel());
		qparams.put("msn", userInfo.getMsn());
		qparams.put("qq", userInfo.getQq());
		if (null != userInfo.getP_index()) {
			qparams.put("user_p_index", userInfo.getP_index().toString());
		}
		qparams.put("shop_name", entpShop.getShop_name());
		qparams.put("shop_desc", entpShop.getShop_desc());
		qparams.put("c_index", entpShop.getC_index());
		qparams.put("main_pd", entpShop.getMain_pd());
		if (null != entpShop.getP_index()) {
			qparams.put("shop_p_index", entpShop.getP_index().toString());
		}
		if (null != entpShop.getG_lng()) {
			qparams.put("g_lng", entpShop.getG_lng().toString());
		}
		if (null != entpShop.getG_lat()) {
			qparams.put("g_lat", entpShop.getG_lat().toString());
		}
		qparams.put("link_user", entpShop.getLink_user());
		qparams.put("link_phone", entpShop.getLink_phone());
		qparams.put("street_addr", entpShop.getStreet_addr());
		qparams.put("post_code", entpShop.getPost_code());
		qparams.put("online_qq", entpShop.getOnline_qq());
		qparams.put("alipay_email", entpShop.getAlipay_email());

		logger.info("********************* call starting... ***********************");
		HttpResponse response = executeGetMethod(properties.get("kongka.kh.mmtentp"), qparams, "UTF-8");

		HashMap<String, Object> result = new HashMap<String, Object>();
		if (response != null && response.getEntity() != null) {
			String json = null;
			try {
				String ret_json = EntityUtils.toString(response.getEntity());

				logger.info("Before decrypt mmtentp's json:" + ret_json);

				json = ret_json.replace("[", "").replace("]", "");
				String[] jsons = json.split("},");

				if (jsons.length != 1) {
					jsons[0] = jsons[0] + "}";
					jsons[1] = jsons[1] + "}";
					JSONObject entity = new JSONObject(jsons[0]);
					JSONObject entity2 = new JSONObject(jsons[1]);
					result.put("reg_state", entity.getInt("reg_state"));
					result.put("shop_id", entity2.getInt("shop_id"));
				} else if (jsons.length == 1) {
					JSONObject entity = new JSONObject(jsons[0]);
					logger.info("After decrypt mmtentp's json:" + entity);
					result.put("reg_state", entity.getInt("reg_state"));
					result.put("msg", entity.getInt("msg"));
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("kongka.kh.mmtentp"));
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("kongka.kh.mmtentp"));
			}
		}
		return result;
	}

	public String createKeySeq() {
		Calendar cal = Calendar.getInstance();
		Integer month = cal.get(Calendar.MONTH) + 1;
		Integer day = cal.get(Calendar.DATE) + 1;
		Integer hour = cal.get(Calendar.HOUR_OF_DAY) + 1;
		Integer minuter = cal.get(Calendar.MINUTE) + 1;
		Integer second = cal.get(Calendar.SECOND) + 1;
		String key_code = month.toString() + day.toString() + hour.toString() + minuter.toString() + second.toString();
		String keySeq = "11111111" + key_code + "00000000";
		return keySeq;
	}

	public HashMap<String, Object> setKeySeq(UserInfo userInfo) {
		HashMap<String, String> qparams = new HashMap<String, String>();
		String keySeq = createKeySeq();
		qparams.put("user_name", userInfo.getUser_name());
		qparams.put("pass_word", userInfo.getMap().get("noDESPluspass_word").toString());
		qparams.put("key_type", "31");
		qparams.put("key_seq", keySeq);
		HttpResponse response = executeGetMethod(properties.get("kongka.kh.mmtkeySeq"), qparams, "UTF-8");

		HashMap<String, Object> result = new HashMap<String, Object>();
		if (response != null && response.getEntity() != null) {
			String json = null;
			try {
				String ret_json = EntityUtils.toString(response.getEntity());

				logger.info("Before decrypt mmtentp's json:" + ret_json);
				JSONObject entity = new JSONObject(ret_json);
				result.put("opr_state", entity.getInt("opr_state"));
				result.put("msg", entity.getInt("msg"));

			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("kongka.kh.mmtkeySeq"));
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("kongka.kh.mmtkeySeq"));
			}
		}

		return result;
	}

	public ArrayList<HashMap<String, Object>> getMain_pd() {
		HashMap<String, String> qparams = new HashMap<String, String>();
		HttpResponse response = executeGetMethod(properties.get("kongka.kh.mmtPdType"), qparams, "UTF-8");
		ArrayList<HashMap<String, Object>> pdList = new ArrayList<HashMap<String, Object>>();
		if (response != null && response.getEntity() != null) {
			String json = null;
			try {
				String ret_json = EntityUtils.toString(response.getEntity());
				json = ret_json.replace("[", "").replace("]", "");

				String[] jsons = json.split("},");

				for (int i = 0; i < jsons.length; i++) {
					HashMap<String, Object> result = new HashMap<String, Object>();
					jsons[i] = jsons[i] + "}";
					logger.info("Before decrypt mmtPdType's json:" + jsons[i]);
					JSONObject entity = new JSONObject(jsons[i]);
					logger.info("After decrypt mmtPdType's json:" + entity);
					result.put("pd_type", entity.getString("pd_type"));
					result.put("pd_name", entity.getString("pd_name"));
					result.put("pd_type_sign", entity.getString("pd_type_sign"));
					pdList.add(result);
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("kongka.kh.mmtkeySeq"));
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("kongka.kh.mmtkeySeq"));
			}
		}
		return pdList;
	}

	public ActionForward saveEntpShop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);

		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String[] main_pds = request.getParameterValues("main_pd");
		String main_pd = StringUtils.join(main_pds, ",");

		EntpShop entpShop = new EntpShop();

		super.copyProperties(entpShop, form);
		entpShop.setMain_pd(main_pd);

		if (GenericValidator.isLong(country)) {
			entpShop.setP_index(new Integer(country));
		} else if (GenericValidator.isLong(city)) {
			entpShop.setP_index(new Integer(city));
		} else if (GenericValidator.isLong(province)) {
			entpShop.setP_index(new Integer(province));
		}
		request.getSession().setAttribute("entpEntity", entpShop);
		return new ActionForward(response.encodeRedirectURL("/admin/KonkaR3Shop/map.jsp"));
	}

	public ActionForward saveMmtShop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		KonkaR3Shop r3Shop = (KonkaR3Shop) request.getSession().getAttribute("r3Shop");
		EntpShop entpShop = (EntpShop) request.getSession().getAttribute("entpEntity");
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userEntity");

		super.copyProperties(entpShop, form);

		ActionForward forward = new ActionForward();
		forward = null;
		HashMap<String, Object> result = CreateMmtEntpShopUserInfo(entpShop, userInfo);
		// 返回值对应表
		if (result.get("reg_state") != null) {
			if (result.get("reg_state").toString().equals("1")) {// 发送成功success;
				entpShop.setShop_id(Long.valueOf(result.get("shop_id").toString()));
				getFacade().getKonkaR3ShopService().create(r3Shop, entpShop);// 匹配R3网点
				HashMap<String, Object> key_result = setKeySeq(userInfo);// 连接密钥接口
				if (key_result.get("opr_state") != null) {// 连接成功
					while ("0".equals(key_result.get("opr_state").toString())
							&& "2105".equals(key_result.get("msg").toString())
							&& "2106".equals(key_result.get("msg").toString())) {// 连接成功但是创建密钥不成功且原因是密钥值的问题，反复生成直到成功为止
						key_result = setKeySeq(userInfo);
					}
					super.renderJavaScript(response, "window.onload=function(){alert('创建并匹配成功！');history.go(-6);}");
				} else if (key_result.get("opr_state") == null) {// 连接密钥接口失败
					super.renderJavaScript(response,
							"window.onload=function(){alert('商铺、用户信息创建并匹配成功，但密钥接口连接失败，请联系管理员');history.go(-5);}");
				}
			} else if ("0".equals(result.get("reg_state").toString())) {// 失败
				if ("1001".equals(result.get("msg").toString())) {// IP地址验证失败;
					super.renderJavaScript(response, "window.onload=function(){alert('IP地址验证失败');history.back();}");
				} else if ("1101".equals(result.get("msg").toString())) {// 用户名为空；
					super.renderJavaScript(response, "window.onload=function(){alert('用户名为空');history.back();}");
				} else if ("1102".equals(result.get("msg").toString())) {// upload
					// exception;
					super.renderJavaScript(response, "window.onload=function(){alert('用户名不合法');history.back();}");
				} else if ("1115".equals(result.get("msg").toString())) {// 用户名已存在;
					super.renderJavaScript(response, "window.onload=function(){alert('用户名已存在');history.back();}");
				} else if ("1103".equals(result.get("msg").toString())) {// 密码为空;
					super.renderJavaScript(response, "window.onload=function(){alert('密码为空');history.back();}");
				} else if ("1104".equals(result.get("msg").toString())) {// 密码过长;
					super.renderJavaScript(response, "window.onload=function(){alert('密码过长');history.back();}");
				} else if ("1105".equals(result.get("msg").toString())) {// 真实名过长;
					super.renderJavaScript(response, "window.onload=function(){alert('真实名过长');history.back();}");
				} else if ("1106".equals(result.get("msg").toString())) {// 性别为空;
					super.renderJavaScript(response, "window.onload=function(){alert('性别为空');history.back();}");
				} else if ("1107".equals(result.get("msg").toString())) {// 性别不合法;
					super.renderJavaScript(response, "window.onload=function(){alert('性别不合法');history.back();}");
				} else if ("1108".equals(result.get("msg").toString())) {// 出生日期不合法;
					super.renderJavaScript(response, "window.onload=function(){alert('出生日期不合法');history.back();}");
				} else if ("1109".equals(result.get("msg").toString())) {// 手机和固定电话号码不能同时为空;
					super.renderJavaScript(response,
							"window.onload=function(){alert('手机和固定电话号码不能同时为空');history.back();}");
				} else if ("1110".equals(result.get("msg").toString())) {// 手机号码不合法;
					super.renderJavaScript(response, "window.onload=function(){alert('手机号码不合法');history.back();}");
				} else if ("1111".equals(result.get("msg").toString())) {// 固定电话不合法;
					super.renderJavaScript(response, "window.onload=function(){alert('固定电话不合法');history.back();}");
				} else if ("1112".equals(result.get("msg").toString())) {// MSN号过长;
					super.renderJavaScript(response, "window.onload=function(){alert('MSN号过长');history.back();}");
				} else if ("1113".equals(result.get("msg").toString())) {// QQ号过长;
					super.renderJavaScript(response, "window.onload=function(){alert('QQ号过长');history.back();}");
				} else if ("1114".equals(result.get("msg").toString())) {// 用户所在地区编码值不合法;
					super.renderJavaScript(response, "window.onload=function(){alert('用户所在地区编码值不合法');history.back();}");
				} else if ("1201".equals(result.get("msg").toString())) {// 商铺名为空;
					super.renderJavaScript(response, "window.onload=function(){alert('商铺名为空');history.back();}");
				} else if ("1202".equals(result.get("msg").toString())) {// 商铺名过长;
					super.renderJavaScript(response, "window.onload=function(){alert('商铺名过长');history.back();}");
				} else if ("1224".equals(result.get("msg").toString())) {// 商铺名已存在;
					super.renderJavaScript(response, "window.onload=function(){alert('商铺名已存在');history.back();}");
				} else if ("1203".equals(result.get("msg").toString())) {// 商铺公告过长;
					super.renderJavaScript(response, "window.onload=function(){alert('商铺公告过长');history.back();}");
				} else if ("1204".equals(result.get("msg").toString())) {// 所属行业为空;
					super.renderJavaScript(response, "window.onload=function(){alert('所属行业为空');history.back();}");
				} else if ("1205".equals(result.get("msg").toString())) {// 所属行业过长;
					super.renderJavaScript(response, "window.onload=function(){alert('所属行业过长');history.back();}");
				} else if ("1206".equals(result.get("msg").toString())) {// 主营产品为空;
					super.renderJavaScript(response, "window.onload=function(){alert('主营产品为空');history.back();}");
				} else if ("1207".equals(result.get("msg").toString())) {// 主营产品过长;
					super.renderJavaScript(response, "window.onload=function(){alert('主营产品过长');history.back();}");
				} else if ("1208".equals(result.get("msg").toString())) {// 主营产品不合法;
					super.renderJavaScript(response, "window.onload=function(){alert('主营产品不合法');history.back();}");
				} else if ("1209".equals(result.get("msg").toString())) {// 商铺所属地区为空;
					super.renderJavaScript(response, "window.onload=function(){alert('商铺所属地区为空');history.back();}");
				} else if ("1210".equals(result.get("msg").toString())) {// 商铺所属地区编码值不合法;
					super.renderJavaScript(response, "window.onload=function(){alert('商铺所属地区编码值不合法');history.back();}");
				} else if ("1211".equals(result.get("msg").toString())) {// 地理位置经度为空;
					super.renderJavaScript(response, "window.onload=function(){alert('地理位置经度为空');history.back();}");
				} else if ("1212".equals(result.get("msg").toString())) {// 地理位置经度不合法;
					super.renderJavaScript(response, "window.onload=function(){alert('地理位置经度不合法');history.back();}");
				} else if ("1213".equals(result.get("msg").toString())) {// 地理位置纬度为空;
					super.renderJavaScript(response, "window.onload=function(){alert('地理位置纬度为空');history.back();}");
				} else if ("1214".equals(result.get("msg").toString())) {// 地理位置纬度不合法;
					super.renderJavaScript(response, "window.onload=function(){alert('地理位置纬度不合法');history.back();}");
				} else if ("1215".equals(result.get("msg").toString())) {// 联系人为空;
					super.renderJavaScript(response, "window.onload=function(){alert('联系人为空');history.back();}");
				} else if ("1216".equals(result.get("msg").toString())) {// 联系人过长;
					super.renderJavaScript(response, "window.onload=function(){alert('联系人过长');history.back();}");
				} else if ("1217".equals(result.get("msg").toString())) {// 联系电话为空;
					super.renderJavaScript(response, "window.onload=function(){alert('联系电话为空');history.back();}");
				} else if ("1218".equals(result.get("msg").toString())) {// 联系电话不合法;
					super.renderJavaScript(response, "window.onload=function(){alert('联系电话不合法');history.back();}");
				} else if ("1219".equals(result.get("msg").toString())) {// 街道地址为空;
					super.renderJavaScript(response, "window.onload=function(){alert('街道地址为空');history.back();}");
				} else if ("1220".equals(result.get("msg").toString())) {// 街道地址过长;
					super.renderJavaScript(response, "window.onload=function(){alert('街道地址过长');history.back();}");
				} else if ("1221".equals(result.get("msg").toString())) {// 地区邮编过长;
					super.renderJavaScript(response, "window.onload=function(){alert('地区邮编过长');history.back();}");
				} else if ("1222".equals(result.get("msg").toString())) {// 在线客服QQ过长;
					super.renderJavaScript(response, "window.onload=function(){alert('在线客服QQ过长');history.back();}");
				} else if ("1223".equals(result.get("msg").toString())) {// 支付宝账号过长;
					super.renderJavaScript(response, "window.onload=function(){alert('支付宝账号过长');history.back();}");
				} else if ("1224".equals(result.get("msg").toString())) {// 商铺名已存在;
					super.renderJavaScript(response, "window.onload=function(){alert('商铺名已存在');history.back();}");
				} else if ("1301".equals(result.get("msg").toString())) {// 注册完成后查询用户信息为空;
					super.renderJavaScript(response, "window.onload=function(){alert('注册完成后查询用户信息为空');history.back();}");
				} else if ("1302".equals(result.get("msg").toString())) {// 注册完成后查询商铺信息为空;
					super.renderJavaScript(response, "window.onload=function(){alert('注册完成后查询商铺信息为空');history.back();}");
				} else {
					super.renderJavaScript(response, "window.onload=function(){alert('');history.back();}");
				}
			}
		} else if (result.get("reg_state") == null) {
			super.renderJavaScript(response, "window.onload=function(){alert('接口连接失败，请联系管理员');history.back();}");
		}
		return forward;

	}

	/**
	 * @author PanGang
	 * @version 2013-07-03
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String branch_area_name_like = (String) dynaBean.get("branch_area_name_like");

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_60 = false;

		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() == 60L) {
				role_id_eq_60 = true;
			}
		}

		if (!role_id_eq_60) {
			String msg = super.getMessage(request, "konka.r3.roleError");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaR3Shop entity = new KonkaR3Shop();
		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like);
		}
		if (StringUtils.isNotBlank(branch_area_name_like)) {
			entity.getMap().put("branch_area_name_like", branch_area_name_like);
		}

		entity.getMap().put("ywy_user_id", userInfo.getId());

		List<KonkaR3Shop> entityList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");

	}

}