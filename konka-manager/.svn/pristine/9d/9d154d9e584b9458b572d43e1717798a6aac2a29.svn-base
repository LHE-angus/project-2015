package com.ebiz.mmt.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
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
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.domain.JdxxEntpSell;
import com.ebiz.mmt.domain.StdEntpMain;
import com.ebiz.mmt.domain.StdEntpUser;
import com.ebiz.mmt.service.InteractWebService;
import com.ebiz.mmt.web.util.KeyEncryptUtils;

/**
 * The Class InteractWebServiceImpl.
 * 
 * @author Xi, Cheng
 * @version 2010-5-29 9:53:31
 */
@Service
public class InteractWebServiceImpl implements InteractWebService {

	private final Logger logger = LoggerFactory.getLogger(InteractWebServiceImpl.class);

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private static DecimalFormat df_0_00 = new DecimalFormat("#0.00");

	private static HashMap<String, String> properties = new HashMap<String, String>();

	private HttpClient httpClient = null;

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

	private HttpResponse executePostMethod(String url, List<NameValuePair> formparams, String charset) {
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

	private String generateJdxxSellParamString(JdxxEntpSell t, String username, String password, String keySeq,
			Integer isBeian) {

		// * 用户名0、密码1、密钥2、标识卡号3、产品编号4、先行垫付5、身份证号6、户口本号7
		// * 销售金额8、销售日期9、购买人姓名10、发票号码11、补贴用户购买12、购买人身份证号码13、
		// * 购买人地址14、购买人固定电话15、购买人手机16、备注17、是否代理备案18、是否先行垫付补贴19、实际补贴金额20、
		// * 户口本号21、户主姓名22、购买人与户主关系23、购买人银行开户名24、购买人开户行25、购买人储蓄账户26、
		// * 代领人姓名27、代领人身份证号码28、代领人地址29、代领人固定电话30、代领人手机31、备注32,ID(33)

		StringBuilder sb = new StringBuilder();

		// 1 - 5
		sb.append(username);
		sb.append("|").append(password);
		sb.append("|").append(keySeq);
		sb.append("|").append(t.getIdentification());
		sb.append("|").append(t.getPd_serial());

		// 6 - 10
		sb.append("|").append(t.getAdv_pay_sign() == null ? "0" : t.getAdv_pay_sign());
		sb.append("|").append(t.getBuyer_id());
		sb.append("|").append(t.getRpr_number());
		sb.append("|").append(t.getPd_price() == null ? "" : df_0_00.format(t.getPd_price()));
		sb.append("|").append(t.getSell_date() == null ? "" : sdf.format(t.getSell_date()));

		// 11 - 15
		sb.append("|").append(t.getBuyer_name());
		sb.append("|").append(t.getInvoice_no());
		sb.append("|").append(t.getAllow_non());
		sb.append("|").append(t.getBuyer_id());
		sb.append("|").append(t.getBuyer_addr());

		// 16 - 20
		sb.append("|").append(t.getBuyer_link());
		sb.append("|").append(t.getBuyer_mobile());
		sb.append("|").append(t.getSell_log_memo());
		sb.append("|").append(isBeian);
		sb.append("|").append(t.getAdv_pay_sign());

		// 21 - 25
		sb.append("|").append(t.getReal_allow_money() == null ? "" : df_0_00.format(t.getReal_allow_money()));
		sb.append("|").append(t.getRpr_number());
		sb.append("|").append(t.getHousemaster());
		sb.append("|").append(t.getKin());
		sb.append("|").append(t.getBuyer_bank_user());

		// 36 - 30
		sb.append("|").append(t.getBuyer_bank_name());
		sb.append("|").append(t.getBuyer_bank_account());
		sb.append("|").append(t.getBuysb_name());
		sb.append("|").append(t.getBuysb_id());
		sb.append("|").append(t.getBuysb_addr());

		// 31 - 34
		sb.append("|").append(t.getBuysb_link());
		sb.append("|").append(t.getBuysb_mobile());
		sb.append("|").append(t.getAllow_memo());
		sb.append("|").append(t.getId());

		logger.info("this object params is : {}", sb.toString());
		return sb.toString().replaceAll("null", "");
	}

	@Override
	public HashMap<String, String> changeYjhxVoucher(HashMap<String, String> params) {
		String[] p = { params.get("username"), params.get("password"), params.get("keySeq"),
				params.get("oldVoucherCode"), params.get("newVoucherCode") };
		// Post提交数据
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("info", StringUtils.join(p, "|")));
		HttpResponse response = executePostMethod(properties.get("service.yjhx.changeYjhxVoucher.url"), qparams, "GBK");
		HashMap<String, String> result = new HashMap<String, String>();
		String[] keys = { "message", "result_code" };
		if (response != null && response.getEntity() != null) {
			String json = null;
			try {
				json = EntityUtils.toString(response.getEntity()).replaceAll(":null", ":\"\"").replaceAll("null", "");
				logger.info("json={}", json);
				JSONObject entity = new JSONObject(json);
				for (String key : keys) {
					result.put(key, entity.getString(key));
				}
			} catch (NumberFormatException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.changeYjhxVoucher.url"));
				logger.error("method = changeYjhxVoucher");
				for (NameValuePair nameValuePair : qparams) {
					logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
				}
				result.put("message", e.getMessage());
				result.put("result_code", "0");
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.changeYjhxVoucher.url"));
				logger.error("method = changeYjhxVoucher");
				for (NameValuePair nameValuePair : qparams) {
					logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
				}
				result.put("message", e.getMessage());
				result.put("result_code", "0");
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.changeYjhxVoucher.url"));
				logger.error("method = changeYjhxVoucher");
				for (NameValuePair nameValuePair : qparams) {
					logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
				}
				result.put("message", e.getMessage());
				result.put("result_code", "0");
			}
		} else {
			result.put("message", "Yjhx's Interface can not accessed!");
			result.put("result_code", "0");
		}
		return result;
	}

	@Override
	public HashMap<String, Object> getJdxxEntpInfoByKeySeq(HashMap<String, String> params) {
		HashMap<String, String> qparams = new HashMap<String, String>();
		qparams.put("info", params.get("keySeq"));
		qparams.put("method", "getEntpMain");
		HttpResponse response = executeGetMethod(properties.get("service.jdxx.url"), qparams, "GBK");
		if (response != null && response.getEntity() != null) {
			String json = null;
			try {
				String ret_json = EntityUtils.toString(response.getEntity());

				logger.info("Before decrypt jdxx's json:" + ret_json);

				byte[] decode = KeyEncryptUtils.myEncode(KeyEncryptUtils.SplitToBytes(ret_json), KeyEncryptUtils
						.GetXORKey(properties.get("sercice.privatekey")));

				json = new String(decode, "GBK");
				logger.info("After decrypt jdxx's json:" + json);

				json = json.replaceAll(":null", ":\"\"").replaceAll("null", "");
				JSONObject entity = new JSONObject(json);
				if (entity.getInt("result_code") == 1) {
					HashMap<String, Object> result = new HashMap<String, Object>();
					StdEntpMain entp = new StdEntpMain();
					entp.setAddr(entity.getString("ADDRESS"));
					entp.setC_index(entity.getString("C_INDEX"));
					entp.setCorporation(entity.getString("CORPORATION"));
					entp.setEmail(entity.getString("EMAIL"));
					entp.setEntp_ename(entity.getString("ENTP_ENAME"));
					entp.setEntp_id(entity.getLong("ENTP_ID"));
					entp.setEntp_kind(entity.getInt("CHARACTER"));
					entp.setEntp_licence(entity.getString("ENTP_LICENCE"));
					entp.setEntp_name(entity.getString("ENTP_NAME"));
					entp.setEntp_size(entity.getString("ENTP_SIZE"));
					entp.setEntp_sname(entity.getString("ENTP_SNAME"));
					entp.setEntp_type(entity.getInt("ENTP_TYPE"));
					entp.setFax(entity.getString("FAX"));
					entp.setIs_record(entity.getInt("IS_RECORD"));
					entp.setLinkman(entity.getString("LINKMAIN"));
					entp.setMain_pd(entity.getString("MAIN_PD"));
					entp.setOwn_sys(1);
					entp.setP_index(entity.getInt("P_INDEX"));

					if (StringUtils.isBlank(entity.getString("PAR_ID"))) {
						entp.setPar_id(0L);
					} else {
						entp.setPar_id(Long.parseLong(entity.getString("PAR_ID")));
					}

					entp.setPostcode(entity.getString("POSTCODE"));
					entp.setRoot_entp_id(entity.getLong("ROOT_ENTP_ID"));
					entp.setAdv_pay_sign(entity.getInt("ADV_PAY_SIGN"));
					entp.setSq_serial(entity.getString("SQ_SERIAL"));
					entp.setTel(entity.getString("TEL"));
					entp.setWww(entity.getString("WWW"));
					result.put("stdEntpMain", entp);
					StdEntpUser user = new StdEntpUser();
					user.setUser_name(entity.getString("USER_NAME"));
					user.setPass_word(entity.getString("PASS_WORD"));
					user.setUser_state(entity.getInt("USER_STATE"));
					user.setOwn_sys(1);
					user.setUser_type(entp.getEntp_type());
					result.put("stdEntpUser", user);
					return result;
				} else {
					// 接口系统正常处理数据，暂不出这部分日志（家电下乡密钥信息取得）
					// logger.error(entity.getString("message"));
					// logger.error("url = " +
					// properties.get("service.jdxx.url"));
					// logger.error("method = getJdxxEntpInfoByKeySeq");
					// for (String key : qparams.keySet()) {
					// logger.error(key + "=" + qparams.get(key));
					// }
					return null;
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.url"));
				logger.error("method = getJdxxEntpInfoByKeySeq");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.url"));
				logger.error("method = getJdxxEntpInfoByKeySeq");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			}
		}
		return null;
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-03-24
	 */
	@Override
	public HashMap<String, Object> getJdxxEntpInfoByUserNameAndPass(HashMap<String, String> params) {
		HashMap<String, String> qparams = new HashMap<String, String>();
		String[] p = { params.get("username"), params.get("password") };
		qparams.put("info", StringUtils.join(p, "|"));
		qparams.put("method", "getEntpMainByUser");
		HttpResponse response = executeGetMethod(properties.get("service.jdxx.url"), qparams, "GBK");
		if (response != null && response.getEntity() != null) {
			String json = null;
			try {
				String ret_json = EntityUtils.toString(response.getEntity());

				logger.info("Before decrypt jdxx's json:" + ret_json);

				byte[] decode = KeyEncryptUtils.myEncode(KeyEncryptUtils.SplitToBytes(ret_json), KeyEncryptUtils
						.GetXORKey(properties.get("sercice.privatekey")));

				json = new String(decode, "GBK");
				logger.info("After decrypt jdxx's json:" + json);

				json = json.replaceAll(":null", ":\"\"").replaceAll("null", "");
				JSONObject entity = new JSONObject(json);
				if (entity.getInt("result_code") != 1) {
					// 接口系统正常处理数据，暂不出这部分日志（家电下乡密钥信息取得）
					// logger.error(entity.getString("message"));
					// logger.error("url = " +
					// properties.get("service.jdxx.url"));
					// logger.error("method = getJdxxEntpInfoByKeySeq");
					// for (String key : qparams.keySet()) {
					// logger.error(key + "=" + qparams.get(key));
					// }
					return null;
				}
				HashMap<String, Object> result = new HashMap<String, Object>();
				StdEntpMain entp = new StdEntpMain();
				entp.setAddr(entity.getString("ADDRESS"));
				entp.setC_index(entity.getString("C_INDEX"));
				entp.setCorporation(entity.getString("CORPORATION"));
				entp.setEmail(entity.getString("EMAIL"));
				entp.setEntp_ename(entity.getString("ENTP_ENAME"));
				entp.setEntp_id(entity.getLong("ENTP_ID"));
				entp.setEntp_kind(entity.getInt("CHARACTER"));
				entp.setEntp_licence(entity.getString("ENTP_LICENCE"));
				entp.setEntp_name(entity.getString("ENTP_NAME"));
				entp.setEntp_size(entity.getString("ENTP_SIZE"));
				entp.setEntp_sname(entity.getString("ENTP_SNAME"));
				entp.setEntp_type(entity.getInt("ENTP_TYPE"));
				entp.setFax(entity.getString("FAX"));
				entp.setIs_record(entity.getInt("IS_RECORD"));
				entp.setLinkman(entity.getString("LINKMAIN"));
				entp.setMain_pd(entity.getString("MAIN_PD"));
				entp.setOwn_sys(1);
				entp.setP_index(entity.getInt("P_INDEX"));

				if (StringUtils.isBlank(entity.getString("PAR_ID"))) {
					entp.setPar_id(0L);
				} else {
					entp.setPar_id(Long.parseLong(entity.getString("PAR_ID")));
				}

				entp.setPostcode(entity.getString("POSTCODE"));
				entp.setRoot_entp_id(entity.getLong("ROOT_ENTP_ID"));
				entp.setAdv_pay_sign(entity.getInt("ADV_PAY_SIGN"));
				entp.setSq_serial(entity.getString("SQ_SERIAL"));
				entp.setTel(entity.getString("TEL"));
				entp.setWww(entity.getString("WWW"));
				result.put("stdEntpMain", entp);
				StdEntpUser user = new StdEntpUser();
				user.setUser_name(entity.getString("USER_NAME"));
				user.setPass_word(entity.getString("PASS_WORD"));
				user.setUser_state(entity.getInt("USER_STATE"));
				user.setOwn_sys(1);
				user.setUser_type(entp.getEntp_type());
				result.put("stdEntpUser", user);

				return result;
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.url"));
				logger.error("method = getJdxxEntpInfoByKeySeq");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.url"));
				logger.error("method = getJdxxEntpInfoByKeySeq");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			}
		}
		return null;
	}

	@Override
	public HashMap<String, Object> getYjhxEntpInfoByKeySeq(HashMap<String, String> params) {
		HashMap<String, String> qparams = new HashMap<String, String>();
		qparams.put("info", params.get("keySeq"));
		HttpResponse response = executeGetMethod(properties.get("service.yjhx.getYjhxEntpInfoByKeySeqAndUsername.url"),
				qparams, "GBK");
		if (response != null && response.getEntity() != null) {
			String json = null;
			try {
				String ret_json = EntityUtils.toString(response.getEntity());

				logger.info("Before decrypt yjhx's json:" + ret_json);

				byte[] decode = KeyEncryptUtils.myEncode(KeyEncryptUtils.SplitToBytes(ret_json), KeyEncryptUtils
						.GetXORKey(properties.get("sercice.privatekey")));

				json = new String(decode, "GBK");

				logger.info("After decrypt jdxx's json:" + json);

				json = json.replaceAll(":null", ":\"\"").replaceAll("null", "");

				JSONObject entity = new JSONObject(json);
				if (entity.getInt("result_code") == 1) {
					HashMap<String, Object> result = new HashMap<String, Object>();
					StdEntpMain entp = new StdEntpMain();
					entp.setAddr(entity.getString("ADDR"));
					entp.setC_index(entity.getString("C_INDEX"));
					entp.setCorporation(entity.getString("CORPORATION"));
					entp.setEmail(entity.getString("EMAIL"));
					entp.setEntp_ename(entity.getString("ENTP_ENAME"));
					entp.setEntp_id(entity.getLong("ENTP_ID"));
					entp.setEntp_kind(entity.getInt("ENTP_KIND"));
					entp.setEntp_licence(entity.getString("ENTP_LICENCE"));
					entp.setEntp_name(entity.getString("ENTP_NAME"));
					entp.setEntp_size(entity.getString("ENTP_SIZE"));
					entp.setEntp_sname(entity.getString("ENTP_SNAME"));
					entp.setEntp_type(entity.getInt("ENTP_TYPE"));
					entp.setFax(entity.getString("FAX"));
					entp.setIs_record(entity.getInt("IS_RECORD"));
					entp.setLinkman(entity.getString("LINKMAN"));
					entp.setMain_pd(entity.getString("MAIN_PD"));
					entp.setOwn_sys(2);
					entp.setP_index(entity.getInt("P_INDEX"));
					entp.setPar_id(entity.getLong("PAR_ID"));
					entp.setPostcode(entity.getString("POSTCODE"));
					entp.setRoot_entp_id(entity.getLong("ROOT_ENTP_ID"));
					entp.setSingle_callback(entity.getInt("SINGLE_CALLBACK"));
					entp.setSq_serial(entity.getString("SQ_SERIAL"));
					entp.setTel(entity.getString("TEL"));
					entp.setWww(entity.getString("WWW"));
					result.put("stdEntpMain", entp);
					StdEntpUser user = new StdEntpUser();
					user.setUser_name(entity.getString("USER_NAME"));
					user.setPass_word(entity.getString("PASS_WORD"));
					user.setUser_state(entity.getInt("USER_STATE"));
					user.setOwn_sys(2);
					user.setUser_type(entp.getEntp_type());
					result.put("stdEntpUser", user);
					return result;
				} else {
					// 接口系统正常处理数据，暂不出这部分日志（以旧换新密钥信息取得）
					// logger.error(entity.getString("message"));
					// logger.error("url = "
					// +
					// properties.get("service.yjhx.getYjhxEntpInfoByKeySeqAndUsername.url"));
					// logger.error("method = getYjhxEntpInfoByKeySeq");
					// for (String key : qparams.keySet()) {
					// logger.error(key + "=" + qparams.get(key));
					// }
					return null;
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.getYjhxEntpInfoByKeySeqAndUsername.url"));
				logger.error("method = getYjhxEntpInfoByKeySeq");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.getYjhxEntpInfoByKeySeqAndUsername.url"));
				logger.error("method = getYjhxEntpInfoByKeySeq");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			}
		}
		return null;
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-03-23
	 */
	@Override
	public HashMap<String, Object> getYjhxEntpInfoByUserPass(HashMap<String, String> params) {
		HashMap<String, String> qparams = new HashMap<String, String>();
		String[] p = { params.get("username"), params.get("password") };
		qparams.put("info", StringUtils.join(p, "|"));
		HttpResponse response = executeGetMethod(properties.get("service.yjhx.getYjhxEntpInfoByUserPass.url"), qparams,
				"GBK");
		if (response != null && response.getEntity() != null) {
			String json = null;
			try {
				String ret_json = EntityUtils.toString(response.getEntity());

				logger.info("Before decrypt yjhx's json:" + ret_json);

				byte[] decode = KeyEncryptUtils.myEncode(KeyEncryptUtils.SplitToBytes(ret_json), KeyEncryptUtils
						.GetXORKey(properties.get("sercice.privatekey")));

				json = new String(decode, "GBK");

				logger.info("After decrypt jdxx's json:" + json);

				json = json.replaceAll(":null", ":\"\"").replaceAll("null", "");

				JSONObject entity = new JSONObject(json);
				if (entity.getInt("result_code") != 1) {
					// 接口系统正常处理数据，暂不出这部分日志（以旧换新密钥信息取得）
					// logger.error(entity.getString("message"));
					// logger.error("url = "
					// +
					// properties.get("service.yjhx.getYjhxEntpInfoByKeySeqAndUsername.url"));
					// logger.error("method = getYjhxEntpInfoByKeySeq");
					// for (String key : qparams.keySet()) {
					// logger.error(key + "=" + qparams.get(key));
					// }
					return null;
				}

				HashMap<String, Object> result = new HashMap<String, Object>();
				StdEntpMain entp = new StdEntpMain();
				entp.setAddr(entity.getString("ADDR"));
				entp.setC_index(entity.getString("C_INDEX"));
				entp.setCorporation(entity.getString("CORPORATION"));
				entp.setEmail(entity.getString("EMAIL"));
				entp.setEntp_ename(entity.getString("ENTP_ENAME"));
				entp.setEntp_id(entity.getLong("ENTP_ID"));
				entp.setEntp_kind(entity.getInt("ENTP_KIND"));
				entp.setEntp_licence(entity.getString("ENTP_LICENCE"));
				entp.setEntp_name(entity.getString("ENTP_NAME"));
				entp.setEntp_size(entity.getString("ENTP_SIZE"));
				entp.setEntp_sname(entity.getString("ENTP_SNAME"));
				entp.setEntp_type(entity.getInt("ENTP_TYPE"));
				entp.setFax(entity.getString("FAX"));
				entp.setIs_record(entity.getInt("IS_RECORD"));
				entp.setLinkman(entity.getString("LINKMAN"));
				entp.setMain_pd(entity.getString("MAIN_PD"));
				entp.setOwn_sys(2);
				entp.setP_index(entity.getInt("P_INDEX"));
				entp.setPar_id(entity.getLong("PAR_ID"));
				entp.setPostcode(entity.getString("POSTCODE"));
				entp.setRoot_entp_id(entity.getLong("ROOT_ENTP_ID"));
				entp.setSingle_callback(entity.getInt("SINGLE_CALLBACK"));
				entp.setSq_serial(entity.getString("SQ_SERIAL"));
				entp.setTel(entity.getString("TEL"));
				entp.setWww(entity.getString("WWW"));
				result.put("stdEntpMain", entp);
				StdEntpUser user = new StdEntpUser();
				user.setUser_name(entity.getString("USER_NAME"));
				user.setPass_word(entity.getString("PASS_WORD"));
				user.setUser_state(entity.getInt("USER_STATE"));
				user.setOwn_sys(2);
				user.setUser_type(entp.getEntp_type());
				result.put("stdEntpUser", user);

				return result;
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.getYjhxEntpInfoByKeySeqAndUsername.url"));
				logger.error("method = getYjhxEntpInfoByKeySeq");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.getYjhxEntpInfoByKeySeqAndUsername.url"));
				logger.error("method = getYjhxEntpInfoByKeySeq");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			}
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String, String> getYjhxEntpCallback(HashMap<String, String> params) {
		HashMap<String, String> qparams = new HashMap<String, String>();
		qparams.put("info", params.get("voucherCode"));
		HttpResponse response = executeGetMethod(properties.get("service.yjhx.getYjhxEntpCallback.url"), qparams, "GBK");
		if (response != null && response.getEntity() != null) {
			String json = null;
			try {
				json = EntityUtils.toString(response.getEntity()).replaceAll(":null", ":\"\"").replaceAll("null", "");
				logger.info("json={}", json);
				JSONObject entity = new JSONObject(json);
				HashMap<String, String> result = new HashMap<String, String>();
				Iterator<String> keys = entity.keys();
				while (keys.hasNext()) {
					String key = keys.next();
					result.put(key, entity.getString(key));
				}
				return result;

			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.getYjhxEntpCallback.url"));
				logger.error("method = getYjhxEntpCallback");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.getYjhxEntpCallback.url"));
				logger.error("method = getYjhxEntpCallback");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			}
		}
		return null;
	}

	@Override
	public List<HashMap<String, String>> getYjhxEntpCallbackList(HashMap<String, String> params) {
		HashMap<String, String> qparams = new HashMap<String, String>();
		String[] p = { params.get("username"), params.get("password"), params.get("keySeq"), params.get("voucherCode"),
				params.get("sDate"), params.get("eDate"), params.get("pdType"), params.get("customerIdCardType"),
				params.get("customerIdCard"), params.get("pageSize"), params.get("pageNo") };
		qparams.put("info", StringUtils.join(p, "|"));
		HttpResponse response = executeGetMethod(properties.get("service.yjhx.getYjhxEntpCallbackList.url"), qparams,
				"GBK");
		if (response != null && response.getEntity() != null) {
			String json = null;
			List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
			try {
				json = EntityUtils.toString(response.getEntity()).replaceAll(":null", ":\"\"").replaceAll("null", "");
				logger.info("json={}", json);
				JSONObject entity = new JSONObject(json);
				Integer record_count = entity.getInt("record_count");
				if (record_count > 0) {
					JSONObject recordList = new JSONObject(entity.getString("list"));
					int size = JSONObject.getNames(recordList).length;
					for (int i = 0; i < size; i++) {
						JSONObject o = recordList.getJSONObject("record" + i);
						HashMap<String, String> row = new HashMap<String, String>();
						for (String k : JSONObject.getNames(o)) {
							row.put(k, o.getString(k));
						}
						row.put("record_count", String.valueOf(record_count));
						result.add(row);
					}
				}
				return result;
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.getYjhxEntpCallbackList.url"));
				logger.error("method = getYjhxEntpCallbackList");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				// 查询不到数据时，返回json中没有项目record_count，这时不出相关日志
				if (!e.getMessage().equals("JSONObject[\"record_count\"] not found.")) {
					logger.error(e.getMessage());
					logger.error("url = " + properties.get("service.yjhx.getYjhxEntpCallbackList.url"));
					logger.error("method = getYjhxEntpCallbackList");
					for (String key : qparams.keySet()) {
						logger.error(key + "=" + qparams.get(key));
					}
				}
			}
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String, String> getYjhxEntpSell(HashMap<String, String> params) {
		HashMap<String, String> qparams = new HashMap<String, String>();
		String[] p = { params.get("username"), params.get("password"), params.get("keySeq"), params.get("id") };
		qparams.put("info", StringUtils.join(p, "|"));
		HttpResponse response = executeGetMethod(properties.get("service.yjhx.getYjhxEntpSell.url"), qparams, "GBK");
		if (response != null && response.getEntity() != null) {
			String json = null;
			try {
				json = EntityUtils.toString(response.getEntity()).replaceAll(":null", ":\"\"").replaceAll("null", "");
				logger.info("json={}", json);
				JSONObject entity = new JSONObject(json);
				HashMap<String, String> result = new HashMap<String, String>();
				Iterator<String> keys = entity.keys();
				while (keys.hasNext()) {
					String key = keys.next();
					result.put(key, entity.getString(key));
				}
				return result;

			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.getYjhxEntpSell.url"));
				logger.error("method = getYjhxEntpSell");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.getYjhxEntpSell.url"));
				logger.error("method = getYjhxEntpSell");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			}
		}
		return null;
	}

	@Override
	public List<HashMap<String, String>> getYjhxEntpSellList(HashMap<String, String> params) {
		HashMap<String, String> qparams = new HashMap<String, String>();
		String[] p = { params.get("username"), params.get("password"), params.get("keySeq"), params.get("sType"),
				params.get("voucherCode"), params.get("sDate"), params.get("eDate"), params.get("pdType"),
				params.get("pageSize"), params.get("pageNo") };
		qparams.put("info", StringUtils.join(p, "|"));
		HttpResponse response = executeGetMethod(properties.get("service.yjhx.getYjhxEntpSellList.url"), qparams, "GBK");
		if (response != null && response.getEntity() != null) {
			String json = null;
			List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
			try {
				json = EntityUtils.toString(response.getEntity()).replaceAll(":null", ":\"\"").replaceAll("null", "");
				logger.info("json={}", json);
				JSONObject entity = new JSONObject(json);
				try {
					entity.getInt("result_code");
				} catch (Exception e) {
					Integer record_count = entity.getInt("record_count");
					if (record_count > 0) {
						JSONObject recordList = new JSONObject(entity.getString("list"));
						int size = JSONObject.getNames(recordList).length;
						for (int i = 0; i < size; i++) {
							JSONObject o = recordList.getJSONObject("record" + i);
							HashMap<String, String> row = new HashMap<String, String>();
							for (String k : JSONObject.getNames(o)) {
								row.put(k, o.getString(k));
							}
							row.put("record_count", String.valueOf(record_count));
							result.add(row);
						}
					}
					return result;
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.getYjhxEntpSellList.url"));
				logger.error("method = getYjhxEntpSellList");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				// 查询不到数据时，返回json中没有项目record_count，这时不出相关日志
				if (!e.getMessage().equals("JSONObject[\"record_count\"] not found.")) {
					logger.error(e.getMessage());
					logger.error("url = " + properties.get("service.yjhx.getYjhxEntpSellList.url"));
					logger.error("method = getYjhxEntpSellList");
					for (String key : qparams.keySet()) {
						logger.error(key + "=" + qparams.get(key));
					}
				}
			}
		}
		return null;
	}

	@Override
	public List<HashMap<String, String>> getYjhxEntpSellListByIdCardInvoice(HashMap<String, String> params) {
		HashMap<String, String> qparams = new HashMap<String, String>();
		String[] p = { params.get("username"), params.get("password"), params.get("keySeq"),
				params.get("customerIdCardType"), params.get("customerIdCard"), params.get("invoiceNo") };
		qparams.put("info", StringUtils.join(p, "|"));
		HttpResponse response = executeGetMethod(properties.get("service.yjhx.getYjhxEntpSellListByIdCardInvoice.url"),
				qparams, "GBK");
		if (response != null && response.getEntity() != null) {
			String json = null;
			List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
			try {
				json = EntityUtils.toString(response.getEntity()).replaceAll(":null", ":\"\"").replaceAll("null", "");
				logger.info("json={}", json);
				JSONObject entity = new JSONObject(json);
				try {
					entity.getInt("result_code");
				} catch (Exception e) {
					Integer record_count = entity.getInt("record_count");
					if (record_count > 0) {
						JSONObject recordList = new JSONObject(entity.getString("list"));
						int size = JSONObject.getNames(recordList).length;
						for (int i = 0; i < size; i++) {
							JSONObject o = recordList.getJSONObject("record" + i);
							HashMap<String, String> row = new HashMap<String, String>();
							for (String k : JSONObject.getNames(o)) {
								row.put(k, o.getString(k));
							}
							row.put("record_count", String.valueOf(record_count));
							result.add(row);
						}
					}
					return result;
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.getYjhxEntpSellListByIdCardInvoice.url"));
				logger.error("method = getYjhxEntpSellListByIdCardInvoice");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				// 查询不到数据时，返回json中没有项目record_count，这时不出相关日志
				if (!e.getMessage().equals("JSONObject[\"record_count\"] not found.")) {
					logger.error(e.getMessage());
					logger.error("url = " + properties.get("service.yjhx.getYjhxEntpSellListByIdCardInvoice.url"));
					logger.error("method = getYjhxEntpSellListByIdCardInvoice");
					for (String key : qparams.keySet()) {
						logger.error(key + "=" + qparams.get(key));
					}
				}
			}
		}
		return null;
	}

	@Override
	public HashMap<String, String> returnYjhxEntpSell(HashMap<String, String> params) {
		/*
		 * HashMap<String,String> qparams = new HashMap<String,String>(); String[] params =
		 * {user.getUser_name(),user.getPass_word(),keySeq,t.getVoucher_code()}; qparams.put("info",
		 * StringUtils.join(params, "|")); HttpResponse response =executeGetMethod(properties.get(
		 * "service.yjhx.returnPdToYjhx// System.url"),qparams,"GBK");
		 */
		// Post提交数据
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		String[] p = { params.get("username"), params.get("password"), params.get("keySeq"), params.get("voucherCode") };
		qparams.add(new BasicNameValuePair("info", StringUtils.join(p, "|")));
		HttpResponse response = executePostMethod(properties.get("service.yjhx.returnYjhxEntpSell.url"), qparams, "GBK");
		if (response != null && response.getEntity() != null) {
			String json = null;
			try {
				json = EntityUtils.toString(response.getEntity()).replaceAll(":null", ":\"\"").replaceAll("null", "");
				logger.info("json={}", json);
				JSONObject entity = new JSONObject(json);

				HashMap<String, String> result = new HashMap<String, String>();
				String[] keys = { "allowInfoState", "message", "result_code" };
				for (String key : keys) {
					result.put(key, entity.getString(key));
				}
				return result;

			} catch (NumberFormatException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.returnYjhxEntpSell.url"));
				logger.error("method = returnYjhxEntpSell");
				for (NameValuePair nameValuePair : qparams) {
					logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.returnYjhxEntpSell.url"));
				logger.error("method = returnYjhxEntpSell");
				for (NameValuePair nameValuePair : qparams) {
					logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
				}
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.returnYjhxEntpSell.url"));
				logger.error("method = returnYjhxEntpSell");
				for (NameValuePair nameValuePair : qparams) {
					logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
				}
			}
		}
		return null;
	}

	@Override
	public HashMap<String, String> verifyYjhxHsSerial(HashMap<String, String> params) {
		HashMap<String, String> qparams = new HashMap<String, String>();
		String[] p = { params.get("username"), params.get("password"), params.get("keySeq"), params.get("hsSerial") };
		qparams.put("info", StringUtils.join(p, "|"));
		HttpResponse response = executeGetMethod(properties.get("service.yjhx.verifyYjhxHsSerial.url"), qparams, "GBK");
		HashMap<String, String> result = new HashMap<String, String>();
		String[] keys = { "message", "result_code" };
		if (response != null && response.getEntity() != null) {
			String json = null;
			try {
				json = EntityUtils.toString(response.getEntity()).replaceAll(":null", ":\"\"").replaceAll("null", "");
				logger.info("json={}", json);
				JSONObject entity = new JSONObject(json);
				for (String key : keys) {
					result.put(key, entity.getString(key));
				}
			} catch (NumberFormatException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.verifyYjhxHsSerial.url"));
				logger.error("method = verifyYjhxHsSerial");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
				result.put("message", e.getMessage());
				result.put("result_code", "0");
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.verifyYjhxHsSerial.url"));
				logger.error("method = verifyYjhxHsSerial");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
				result.put("message", e.getMessage());
				result.put("result_code", "0");
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.verifyYjhxHsSerial.url"));
				logger.error("method = verifyYjhxHsSerial");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
				result.put("message", e.getMessage());
				result.put("result_code", "0");
			}
		} else {
			result.put("message", "Yjhx's Interface can not accessed!");
			result.put("result_code", "0");
		}
		return result;
	}

	@Override
	public HashMap<String, String> verifyYjhxVoucherCode(HashMap<String, String> params) {
		HashMap<String, String> qparams = new HashMap<String, String>();
		String[] p = { params.get("username"), params.get("password"), params.get("keySeq"), params.get("voucherCode") };
		qparams.put("info", StringUtils.join(p, "|"));
		HttpResponse response = executeGetMethod(properties.get("service.yjhx.verifyYjhxVoucherCode.url"), qparams,
				"GBK");
		HashMap<String, String> result = new HashMap<String, String>();
		String[] keys = { "message", "result_code" };
		if (response != null && response.getEntity() != null) {
			String json = null;
			try {
				json = EntityUtils.toString(response.getEntity()).replaceAll(":null", ":\"\"").replaceAll("null", "");
				logger.info("json={}", json);
				JSONObject entity = new JSONObject(json);
				for (String key : keys) {
					result.put(key, entity.getString(key));
				}
			} catch (NumberFormatException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.verifyYjhxVoucherCode.url"));
				logger.error("method = verifyYjhxVoucherCode");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
				result.put("message", e.getMessage());
				result.put("result_code", "0");
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.verifyYjhxVoucherCode.url"));
				logger.error("method = verifyYjhxVoucherCode");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
				result.put("message", e.getMessage());
				result.put("result_code", "0");
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.verifyYjhxVoucherCode.url"));
				logger.error("method = verifyYjhxVoucherCode");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
				result.put("message", e.getMessage());
				result.put("result_code", "0");
			}
		} else {
			result.put("message", "Yjhx's Interface can not accessed!");
			result.put("result_code", "0");
		}
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String, String> getJdxxEntpSell(HashMap<String, String> params) {
		HashMap<String, String> qparams = new HashMap<String, String>();
		String[] p = { params.get("username"), params.get("password"), params.get("keySeq"), params.get("id") };
		qparams.put("method", "getEntpSellAllow");
		qparams.put("info", StringUtils.join(p, "|"));
		HttpResponse response = executeGetMethod(properties.get("service.jdxx.url"), qparams, "GBK");
		if (response != null && response.getEntity() != null) {
			String json = null;
			try {
				json = EntityUtils.toString(response.getEntity()).replaceAll(":null", ":\"\"").replaceAll("null", "");
				logger.info("json={}", json);
				JSONObject entity = new JSONObject(json);
				HashMap<String, String> result = new HashMap<String, String>();
				Iterator<String> keys = entity.keys();
				while (keys.hasNext()) {
					String key = keys.next();
					result.put(key, entity.getString(key));
				}
				return result;
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.url"));
				logger.error("method = getJdxxEntpSell");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.url"));
				logger.error("method = getJdxxEntpSell");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			}
		}
		return null;
	}

	@Override
	public List<HashMap<String, String>> getJdxxEntpSellList(HashMap<String, String> params) {
		HashMap<String, String> qparams = new HashMap<String, String>();
		String[] p = { params.get("username"), params.get("password"), params.get("keySeq"),
				params.get("identification"), params.get("buyerName"), params.get("sDate"), params.get("eDate"),
				params.get("pdState"), params.get("pdType"), params.get("advPaySign"), params.get("pageSize"),
				params.get("pageNo") };
		qparams.put("info", StringUtils.join(p, "|"));
		qparams.put("method", "getXsList");
		HttpResponse response = executeGetMethod(properties.get("service.jdxx.url"), qparams, "GBK");
		if (response != null && response.getEntity() != null) {
			String json = null;
			List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
			try {
				json = EntityUtils.toString(response.getEntity()).replaceAll(":null", ":\"\"").replaceAll("null", "");
				logger.info("json={}", json);
				JSONObject entity = new JSONObject(json);
				try {
					entity.getInt("result_code");
				} catch (Exception e) {
					Integer record_count = entity.getInt("record_count");
					if (record_count > 0) {
						JSONObject recordList = new JSONObject(entity.getString("list"));
						int size = JSONObject.getNames(recordList).length;
						for (int i = 0; i < size; i++) {
							JSONObject o = recordList.getJSONObject("record" + i);
							HashMap<String, String> row = new HashMap<String, String>();
							for (String k : JSONObject.getNames(o)) {
								row.put(k, o.getString(k));
							}
							row.put("record_count", String.valueOf(record_count));
							result.add(row);
						}
					}
					return result;
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.url"));
				logger.error("method = getJdxxEntpSellList");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				// 查询不到数据时，返回json中没有项目record_count，这时不出相关日志
				if (!e.getMessage().equals("JSONObject[\"record_count\"] not found.")) {
					logger.error(e.getMessage());
					logger.error("url = " + properties.get("service.jdxx.url"));
					logger.error("method = getJdxxEntpSellList");
					for (String key : qparams.keySet()) {
						logger.error(key + "=" + qparams.get(key));
					}
				}
			}
		}
		return null;
	}

	@Override
	public List<HashMap<String, String>> getJdxxEntpSellReturnList(HashMap<String, String> params) {
		HashMap<String, String> qparams = new HashMap<String, String>();
		String[] p = { params.get("username"), params.get("password"), params.get("keySeq"), params.get("pdType"),
				params.get("pdState"), params.get("identification"), params.get("sDate"), params.get("eDate"),
				params.get("pageSize"), params.get("pageNo") };
		qparams.put("info", StringUtils.join(p, "|"));
		qparams.put("method", "getProductList");
		HttpResponse response = executeGetMethod(properties.get("service.jdxx.url"), qparams, "GBK");
		if (response != null && response.getEntity() != null) {
			String json = null;
			List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
			try {
				json = EntityUtils.toString(response.getEntity()).replaceAll(":null", ":\"\"").replaceAll("null", "");
				logger.info("json={}", json);
				JSONObject entity = new JSONObject(json);
				try {
					entity.getInt("result_code");
				} catch (Exception e) {
					Integer record_count = entity.getInt("record_count");
					if (record_count > 0) {
						JSONObject recordList = new JSONObject(entity.getString("list_p"));
						int size = JSONObject.getNames(recordList).length;
						for (int i = 0; i < size; i++) {
							JSONObject o = recordList.getJSONObject("record" + i);
							HashMap<String, String> row = new HashMap<String, String>();
							for (String k : JSONObject.getNames(o)) {
								row.put(k, o.getString(k));
							}
							row.put("record_count", String.valueOf(record_count));
							result.add(row);
						}
					}
					return result;
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.url"));
				logger.error("method = getJdxxEntpSellReturnList");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				// 查询不到数据时，返回json中没有项目record_count，这时不出相关日志
				if (!e.getMessage().equals("JSONObject[\"record_count\"] not found.")) {
					logger.error(e.getMessage());
					logger.error("url = " + properties.get("service.jdxx.url"));
					logger.error("method = getJdxxEntpSellReturnList");
					for (String key : qparams.keySet()) {
						logger.error(key + "=" + qparams.get(key));
					}
				}
			}
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String, String> getJdxxPdDetails(HashMap<String, String> params) {
		HashMap<String, String> qparams = new HashMap<String, String>();
		String[] p = { params.get("username"), params.get("password"), params.get("keySeq"),
				params.get("identification"), params.get("pdSerial") };
		qparams.put("info", StringUtils.join(p, "|"));
		qparams.put("method", "getPdByIdtfPdid");
		HttpResponse response = executeGetMethod(properties.get("service.jdxx.url"), qparams, "GBK");
		if (response != null && response.getEntity() != null) {
			String json = null;
			try {
				json = EntityUtils.toString(response.getEntity()).replaceAll(":null", ":\"\"").replaceAll("null", "");
				logger.info("json={}", json);
				JSONObject entity = new JSONObject(json);
				HashMap<String, String> result = new HashMap<String, String>();
				Iterator<String> keys = entity.keys();
				while (keys.hasNext()) {
					String key = keys.next();
					result.put(key, entity.getString(key));
				}
				return result;
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.url"));
				logger.error("method = getJdxxPdDetails");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.url"));
				logger.error("method = getJdxxPdDetails");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			}
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String, String> getJdxxPdDetailsWithRpr(HashMap<String, String> params) {
		HashMap<String, String> qparams = new HashMap<String, String>();
		String[] p = { params.get("username"), params.get("password"), params.get("keySeq"),
				params.get("identification"), params.get("pdSerial"), params.get("isAdvPay"), params.get("idCard"),
				params.get("rpr") };
		qparams.put("info", StringUtils.join(p, "|"));
		qparams.put("method", "getPdByIdtfPdidRpr");
		HttpResponse response = executeGetMethod(properties.get("service.jdxx.url"), qparams, "GBK");
		if (response != null && response.getEntity() != null) {
			String json = null;
			try {
				json = EntityUtils.toString(response.getEntity()).replaceAll(":null", ":\"\"").replaceAll("null", "");
				logger.info("json={}", json);
				JSONObject entity = new JSONObject(json);
				HashMap<String, String> result = new HashMap<String, String>();
				Iterator<String> keys = entity.keys();
				while (keys.hasNext()) {
					String key = keys.next();
					result.put(key, entity.getString(key));
				}
				return result;
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.url"));
				logger.error("method = getJdxxPdDetailsWithRpr");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.url"));
				logger.error("method = getJdxxPdDetailsWithRpr");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			}
		}
		return null;
	}

	@Override
	public List<HashMap<String, String>> getJdxxSellListByRpr(HashMap<String, String> params) {
		HashMap<String, String> qparams = new HashMap<String, String>();
		String[] p = { params.get("username"), params.get("password"), params.get("keySeq"), params.get("rpr"),
				params.get("idCard"), params.get("identification") };
		qparams.put("info", StringUtils.join(p, "|"));
		qparams.put("method", "getRprNumberList");
		HttpResponse response = executeGetMethod(properties.get("service.jdxx.url"), qparams, "GBK");
		if (response != null && response.getEntity() != null) {
			String json = null;
			List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
			try {
				json = EntityUtils.toString(response.getEntity()).replaceAll(":null", ":\"\"").replaceAll("null", "");
				logger.info("json={}", json);
				JSONObject entity = new JSONObject(json);
				try {
					entity.getInt("result_code");
				} catch (Exception e) {
					Integer record_count = entity.getInt("record_count");
					String list_f = entity.getString("list_f");
					if (list_f.length() > 2) {
						JSONObject recordList = new JSONObject(entity.getString("list_f"));
						int size = JSONObject.getNames(recordList).length;
						for (int i = 0; i < size; i++) {
							JSONObject o = recordList.getJSONObject("record" + i);
							HashMap<String, String> row = new HashMap<String, String>();
							for (String k : JSONObject.getNames(o)) {
								row.put(k, o.getString(k));
							}
							row.put("record_count", String.valueOf(record_count));
							result.add(row);
						}
					}
					return result;
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.url"));
				logger.error("method = getJdxxSellListByRpr");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				// 查询不到数据时，返回json中没有项目record_count，这时不出相关日志
				if (!e.getMessage().equals("JSONObject[\"record_count\"] not found.")) {
					logger.error(e.getMessage());
					logger.error("url = " + properties.get("service.jdxx.url"));
					logger.error("method = getJdxxSellListByRpr");
					for (String key : qparams.keySet()) {
						logger.error(key + "=" + qparams.get(key));
					}
				}
			}
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String, String> returnJdxxEntpSell(HashMap<String, String> params) {
		/*
		 * HashMap<String,String> qparams = new HashMap<String,String>(); String[] params = {user.getUser_name(),
		 * user.getPass_word(), params.get("keySeq"), params.get("id")}; qparams.put("info", StringUtils.join(params,
		 * "|")); HttpResponse response =executeGetMethod(properties.get( "service.jdxx.url"),qparams,"GBK");
		 */
		// Post提交数据
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		String[] p = { params.get("username"), params.get("password"), params.get("keySeq"), params.get("id") };
		qparams.add(new BasicNameValuePair("info", StringUtils.join(p, "|")));
		qparams.add(new BasicNameValuePair("method", "returnPd"));
		HttpResponse response = executePostMethod(properties.get("service.jdxx.url"), qparams, "GBK");
		if (response != null && response.getEntity() != null) {
			String json = null;
			try {
				json = EntityUtils.toString(response.getEntity()).replaceAll(":null", ":\"\"").replaceAll("null", "");
				logger.info("json={}", json);
				JSONObject entity = new JSONObject(json);
				HashMap<String, String> result = new HashMap<String, String>();
				Iterator<String> keys = entity.keys();
				while (keys.hasNext()) {
					String key = keys.next();
					result.put(key, entity.getString(key));
				}
				return result;
			} catch (NumberFormatException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.url"));
				logger.error("method = returnJdxxEntpSell");
				for (NameValuePair nameValuePair : qparams) {
					logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.url"));
				logger.error("method = returnJdxxEntpSell");
				for (NameValuePair nameValuePair : qparams) {
					logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
				}
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.url"));
				logger.error("method = returnJdxxEntpSell");
				for (NameValuePair nameValuePair : qparams) {
					logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
				}
			}
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String, String> createJdxxEntpSell(JdxxEntpSell entity, String username, String password,
			String keySeq, Integer isBeian) {
		// Post提交数据
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("info", generateJdxxSellParamString(entity, username, password, keySeq,
				isBeian)));
		qparams.add(new BasicNameValuePair("method", "saveSell"));
		HttpResponse response = executePostMethod(properties.get("service.jdxx.url"), qparams, "GBK");
		if (response != null && response.getEntity() != null) {
			String json = null;
			try {
				json = EntityUtils.toString(response.getEntity()).replaceAll(":null", ":\"\"").replaceAll("null", "");
				logger.info("json={}", json);
				JSONObject obj = new JSONObject(json);
				HashMap<String, String> result = new HashMap<String, String>();
				Iterator<String> keys = obj.keys();
				while (keys.hasNext()) {
					String key = keys.next();
					result.put(key, obj.getString(key));
				}
				return result;
			} catch (NumberFormatException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.url"));
				logger.error("method = createJdxxEntpSell");
				for (NameValuePair nameValuePair : qparams) {
					logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.url"));
				logger.error("method = createJdxxEntpSell");
				for (NameValuePair nameValuePair : qparams) {
					logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
				}
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.url"));
				logger.error("method = createJdxxEntpSell");
				for (NameValuePair nameValuePair : qparams) {
					logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
				}
			}
		} else {

			response = executePostMethod(properties.get("service.jdxx.url"), qparams, "GBK");
			if (response != null && response.getEntity() != null) {
				String json = null;
				try {
					json = EntityUtils.toString(response.getEntity()).replaceAll(":null", ":\"\"").replaceAll("null",
							"");
					logger.info("json={}", json);
					JSONObject obj = new JSONObject(json);
					HashMap<String, String> result = new HashMap<String, String>();
					Iterator<String> keys = obj.keys();
					while (keys.hasNext()) {
						String key = keys.next();
						result.put(key, obj.getString(key));
					}
					return result;
				} catch (NumberFormatException e) {
					logger.error(e.getMessage());
					logger.error("url = " + properties.get("service.jdxx.url"));
					logger.error("method = createJdxxEntpSell");
					for (NameValuePair nameValuePair : qparams) {
						logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
					}
				} catch (IOException e) {
					logger.error(e.getMessage());
					logger.error("url = " + properties.get("service.jdxx.url"));
					logger.error("method = createJdxxEntpSell");
					for (NameValuePair nameValuePair : qparams) {
						logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
					}
				} catch (JSONException e) {
					logger.error("json string: {}", json);
					logger.error(e.getMessage());
					logger.error("url = " + properties.get("service.jdxx.url"));
					logger.error("method = createJdxxEntpSell");
					for (NameValuePair nameValuePair : qparams) {
						logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
					}
				}
			} else {
				logger.error("Excute Create JdxxEntpSell return null!");
				logger.error("url = " + properties.get("service.jdxx.url"));
				logger.error("method = createJdxxEntpSell");
				for (NameValuePair nameValuePair : qparams) {
					logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
				}
			}
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String, String> modifyJdxxEntpSell(JdxxEntpSell entity, String username, String password,
			String keySeq, Integer isBeian) {
		// Post提交数据
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("info", generateJdxxSellParamString(entity, username, password, keySeq,
				isBeian)));
		qparams.add(new BasicNameValuePair("method", "saveSell"));
		HttpResponse response = executePostMethod(properties.get("service.jdxx.url"), qparams, "GBK");
		if (response != null && response.getEntity() != null) {
			String json = null;
			try {
				json = EntityUtils.toString(response.getEntity()).replaceAll(":null", ":\"\"").replaceAll("null", "");
				logger.info("json={}", json);
				JSONObject obj = new JSONObject(json);
				HashMap<String, String> result = new HashMap<String, String>();
				Iterator<String> keys = obj.keys();
				while (keys.hasNext()) {
					String key = keys.next();
					result.put(key, obj.getString(key));
				}
				return result;
			} catch (NumberFormatException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.url"));
				logger.error("method = modifyJdxxEntpSell");
				for (NameValuePair nameValuePair : qparams) {
					logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.url"));
				logger.error("method = modifyJdxxEntpSell");
				for (NameValuePair nameValuePair : qparams) {
					logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
				}
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.url"));
				logger.error("method = modifyJdxxEntpSell");
				for (NameValuePair nameValuePair : qparams) {
					logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
				}
			}
		} else {
			logger.error("Excute Modify JdxxEntpSell return null!");
			logger.error("url = " + properties.get("service.jdxx.url"));
			logger.error("method = modifyJdxxEntpSell");
			for (NameValuePair nameValuePair : qparams) {
				logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
			}
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String, String> modifyJdxxEntpSellForAdmin(JdxxEntpSell entity, String username, String password,
			String keySeq, Integer isBeian) {
		// Post提交数据
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("info", generateJdxxSellParamString(entity, username, password, keySeq,
				isBeian)));
		qparams.add(new BasicNameValuePair("method", "saveSellForAdmin"));
		HttpResponse response = executePostMethod(properties.get("service.jdxx.url"), qparams, "GBK");
		if (response != null && response.getEntity() != null) {
			String json = null;
			try {
				json = EntityUtils.toString(response.getEntity()).replaceAll(":null", ":\"\"").replaceAll("null", "");
				logger.info("json={}", json);
				JSONObject obj = new JSONObject(json);
				HashMap<String, String> result = new HashMap<String, String>();
				Iterator<String> keys = obj.keys();
				while (keys.hasNext()) {
					String key = keys.next();
					result.put(key, obj.getString(key));
				}
				return result;
			} catch (NumberFormatException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.url"));
				logger.error("method = modifyJdxxEntpSell");
				for (NameValuePair nameValuePair : qparams) {
					logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.url"));
				logger.error("method = modifyJdxxEntpSell");
				for (NameValuePair nameValuePair : qparams) {
					logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
				}
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.url"));
				logger.error("method = modifyJdxxEntpSell");
				for (NameValuePair nameValuePair : qparams) {
					logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
				}
			}
		} else {
			logger.error("Excute Modify JdxxEntpSell return null!");
			logger.error("url = " + properties.get("service.jdxx.url"));
			logger.error("method = modifyJdxxEntpSell");
			for (NameValuePair nameValuePair : qparams) {
				logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
			}
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String, String> getXsHsCountByIdcard(HashMap<String, String> params) {
		HashMap<String, String> qparams = new HashMap<String, String>();
		String[] p = { params.get("idCardType"), params.get("idCard") };
		qparams.put("info", StringUtils.join(p, "|"));
		HttpResponse response = executeGetMethod(properties.get("service.yjhx.getXsHsCountByIdcard.url"), qparams,
				"GBK");
		if (response != null && response.getEntity() != null) {
			String json = null;
			try {
				json = EntityUtils.toString(response.getEntity()).replaceAll(":null", ":\"\"").replaceAll("null", "");
				logger.info("json={}", json);
				JSONObject entity = new JSONObject(json);
				HashMap<String, String> result = new HashMap<String, String>();
				Iterator<String> keys = entity.keys();
				while (keys.hasNext()) {
					String key = keys.next();
					result.put(key, entity.getString(key));
				}
				return result;
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.getXsHsCountByIdcard.url"));
				logger.error("method = getXsHsCountByIdcard");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.getXsHsCountByIdcard.url"));
				logger.error("method = getXsHsCountByIdcard");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			}
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String, String> verifyHsCountByIdcard(HashMap<String, String> params) {
		HashMap<String, String> qparams = new HashMap<String, String>();
		String[] p = { params.get("idCardType"), params.get("idCard") };
		qparams.put("info", StringUtils.join(p, "|"));
		HttpResponse response = executeGetMethod(properties.get("service.yjhx.verifyHsCountByIdcard.url"), qparams,
				"GBK");
		if (response != null && response.getEntity() != null) {
			String json = null;
			try {
				json = EntityUtils.toString(response.getEntity()).replaceAll(":null", ":\"\"").replaceAll("null", "");
				logger.info("json={}", json);
				JSONObject entity = new JSONObject(json);
				HashMap<String, String> result = new HashMap<String, String>();
				Iterator<String> keys = entity.keys();
				while (keys.hasNext()) {
					String key = keys.next();
					result.put(key, entity.getString(key));
				}
				return result;
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.verifyHsCountByIdcard.url"));
				logger.error("method = verifyHsCountByIdcard");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.verifyHsCountByIdcard.url"));
				logger.error("method = verifyHsCountByIdcard");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			}
		}
		return null;
	}

	@Override
	public HashMap<String, String> verifyVcodeByVcode(HashMap<String, String> params) {
		HashMap<String, String> qparams = new HashMap<String, String>();
		qparams.put("vcode", params.get("vcode"));
		HttpResponse response = executeGetMethod(properties.get("service.yjhx.verifyVcodeByVcode.url"), qparams, "GBK");
		HashMap<String, String> result = new HashMap<String, String>();
		if (response != null && response.getEntity() != null) {
			String json = null;
			try {
				json = EntityUtils.toString(response.getEntity()).replaceAll("\r", "").replaceAll("\n", "");
				logger.info("json={}", json);
				result.put("result_code", StringUtils.substring(json, 25, 26));
			} catch (NumberFormatException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.verifyVcodeByVcode.url"));
				logger.error("method = verifyVcodeByVcode");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
				result.put("message", e.getMessage());
				result.put("result_code", "0");
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.yjhx.verifyVcodeByVcode.url"));
				logger.error("method = verifyVcodeByVcode");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
				result.put("message", e.getMessage());
				result.put("result_code", "0");
			}
		} else {
			result.put("message", "Yjhx's Interface can not accessed!");
			result.put("result_code", "0");
		}
		return result;
	}

	public static void main(String args[]) {
		String a = "var result_state_yjhx = \"0\";";
		// //System.out.println(a.length());
		// //System.out.println(StringUtils.substring(a, 25, 26));
		// String aa = StringUtils.substring(a, StringUtils.indexOf(a, "var"),
		// end)
	}

	/**
	 * @author Chen,LiLin
	 * @version 2011-2-12
	 */
	@Override
	public HashMap<String, String> verifyIdentification(HashMap<String, String> params) {
		HashMap<String, String> qparams = new HashMap<String, String>();
		qparams.put("keySeq", params.get("keySeq"));
		qparams.put("identification", params.get("identification"));
		HttpResponse response = executeGetMethod(properties.get("service.jdxx.bsk.url"), params, "GBK");
		HashMap<String, String> result = new HashMap<String, String>();
		if (response != null && response.getEntity() != null) {
			String json = null;
			try {
				json = EntityUtils.toString(response.getEntity()).replaceAll("\r", "").replaceAll("\n", "");
				logger.info("json={}", json);
				String[] info = StringUtils.substringsBetween(json, "<td", "</td>");
				logger.info("card added date string = {}", info[4].substring(1));
				Date date = DateUtils.parseDate(info[4].substring(1), new String[] { "yyyy-MM-dd" });
				if (date.before(DateUtils.parseDate("2010-12-16", new String[] { "yyyy-MM-dd" }))) {// 2010.12.16之后是第二版标识卡
					result.put("result_code", "1");
				} else {
					result.put("result_code", "2");
				}
			} catch (ParseException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.bsk.url"));
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
				result.put("message", "000:the card query failure");
				result.put("result_code", "0");
			} catch (IndexOutOfBoundsException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.bsk.url"));
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
				result.put("message", "000:the card query failure");
				result.put("result_code", "0");
			} catch (NumberFormatException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.bsk.url"));
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
				result.put("message", "000:the card query failure");
				result.put("result_code", "0");
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.bsk.url"));
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
				result.put("message", "000:the card query failure");
				result.put("result_code", "0");
			}
		} else {
			result.put("message", "000:the card query failure");
			result.put("result_code", "0");
		}
		return result;
	}

	@Override
	public List<HashMap<String, String>> getJdxxBuyerRecordListByIdcard(HashMap<String, String> params) {
		HashMap<String, String> qparams = new HashMap<String, String>();
		String[] p = { params.get("username"), params.get("password"), params.get("keySeq"), params.get("idCard"),
				params.get("identification") };
		qparams.put("method", "getInfoByBuyerID");
		qparams.put("info", StringUtils.join(p, "|"));
		HttpResponse response = executeGetMethod(properties.get("service.jdxx.url"), qparams, "GBK");
		if (response != null && response.getEntity() != null) {
			String json = null;
			List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
			try {
				json = EntityUtils.toString(response.getEntity()).replaceAll(":null", ":\"\"").replaceAll("null", "");
				logger.info("json={}", json);
				JSONObject entity = new JSONObject(json);
				try {
					entity.getInt("result_code");
				} catch (Exception e) {
					Integer record_count = entity.getInt("record_count");
					if (record_count > 0) {
						JSONObject recordList = new JSONObject(entity.getString("list_f"));
						int size = JSONObject.getNames(recordList).length;
						for (int i = 0; i < size; i++) {
							JSONObject o = recordList.getJSONObject("record" + i);
							HashMap<String, String> row = new HashMap<String, String>();
							for (String k : JSONObject.getNames(o)) {
								row.put(k, o.getString(k));
							}
							row.put("record_count", String.valueOf(record_count));
							result.add(row);
						}
					}
					return result;
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
				logger.error("url = " + properties.get("service.jdxx.url"));
				logger.error("method = getInfoByBuyerID");
				for (String key : qparams.keySet()) {
					logger.error(key + "=" + qparams.get(key));
				}
			} catch (JSONException e) {
				logger.error("json string: {}", json);
				// 查询不到数据时，返回json中没有项目record_count，这时不出相关日志
				if (!e.getMessage().equals("JSONObject[\"record_count\"] not found.")) {
					logger.error(e.getMessage());
					logger.error("url = " + properties.get("service.jdxx.url"));
					logger.error("method = getInfoByBuyerID");
					for (String key : qparams.keySet()) {
						logger.error(key + "=" + qparams.get(key));
					}
				}
			}
		}
		return null;
	}

	/**
	 * get cancel order count per shop.
	 * 
	 * @return Structure:{["shop_id":"count1"],["cancel_order":count2]}
	 */
	@Override
	public List<HashMap<String, String>> GetCancelOrderCount() {

		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String returnValue = null;
		logger.info("RequestUrl : {}", properties.get("cxda.cancel.order.url"));

		HttpResponse response = executeGetMethod(properties.get("cxda.cancel.order.url"),
				new HashMap<String, String>(), "GBK");

		if (response != null && response.getEntity() != null) {
			try {
				returnValue = EntityUtils.toString(response.getEntity());

				if (StringUtils.isNotBlank(returnValue)) {
					String[] cancelOrderStrings = returnValue.substring(1, returnValue.length() - 1).split("\\]\\,\\[");
					if (null != cancelOrderStrings) {
						for (int i = 0; i < cancelOrderStrings.length; i++) {
							HashMap<String, String> map = new HashMap<String, String>();
							String[] value = cancelOrderStrings[i].split(",");
							map.put("shop_id", value[0]);
							map.put("cancel_order", value[1]);
							result.add(map);
						}
					}
				}
			} catch (org.apache.http.ParseException e) {
				logger.error(e.getMessage());
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}

		logger.info("response entity : {}", returnValue);

		return result;

	}
	
//非密钥登录 
	private static String[] keys_verify = { "state", "msg", "user_name", "user_id", "shop_id" };// 会话验证

	@Override
	public HashMap<String, String> loginWebServiceForJxcClient(HashMap<String, String> params, int type) {
		HashMap<String, String> qparams = new HashMap<String, String>();
		// String method = "?method=".concat(params.get("method"));
		if (1 == type) {// 登录,暂时用不到

		}
		if (2 == type) {// 注销,暂时用不到

		}
		if (3 == type) {// 会话验证
			qparams.put("method", params.get("method"));
			qparams.put("s_id", params.get("s_id"));
		}
		HashMap<String, String> result = new HashMap<String, String>();
		HttpResponse response = executeGetMethod(properties.get("jxc.login.webservice"), qparams, "UTF-8");
		if (response != null && response.getEntity() != null) {
			try {
				String json = EntityUtils.toString(response.getEntity());
				logger.info("json={}", json);
				JSONObject entity = new JSONObject(json);
				for (String key : keys_verify) {
					if (entity.has(key)) {
						result.put(key, entity.getString(key));
					}
				}
			} catch (NumberFormatException e) {
				result.put("msg", e.getMessage());
				result.put("state", "-1");
			} catch (IOException e) {
				logger.error(e.getMessage());
				result.put("msg", e.getMessage());
				result.put("state", "-1");
			} catch (JSONException e) {
				logger.error(e.getMessage());
				result.put("msg", "接口返回数据有错误,非法JSONE数据！");
				result.put("state", "-1");
			}
		} else {
			result.put("msg", "接口暂时无法访问，可能是网络故障或接口系统临时停机维护，请等待一会再次尝试。");
			result.put("state", "-1");
		}
		return result;
	}
	
	@Override
	public HttpResponse sendMsgWebServiceForJxcClient(String url, List<NameValuePair> qparams, String charset) {
		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(qparams, charset);
			HttpPost httppost = new HttpPost(url);
			httppost.setEntity(entity);
			HttpResponse response = getHttpClient().execute(httppost);
			return response;
		} catch (UnsupportedEncodingException e) {
			logger.error("method = executePostMethod, url = {}", url);
			logger.error(e.getMessage());
			for (NameValuePair nameValuePair : qparams) {
				logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
			}
		} catch (ClientProtocolException e) {
			logger.error("method = executePostMethod, url = {}", url);
			logger.error(e.getMessage());

			for (NameValuePair nameValuePair : qparams) {
				logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
			}
		} catch (IOException e) {
			logger.error("method = executePostMethod, url = {}", url);
			logger.error(e.getMessage());

			for (NameValuePair nameValuePair : qparams) {
				logger.error(nameValuePair.getName() + "=" + nameValuePair.getValue());
			}
		}
		return null;
		
	}
	
}
