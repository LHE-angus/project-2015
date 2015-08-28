package com.ebiz.mmt.web.util.yixun;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class GetApiUtils {

	private static final String ENCODING = "GBK";

	public static String getApiWithUrl(String url) throws Exception {
		InputStream is = null;
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse response = client.execute(httpGet);
			is = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, ENCODING));
			StringBuffer result = new StringBuffer();
			String string = null;
			if (null != (string = reader.readLine())) {
				result.append(string);
			}

			// //System.out.println("result: " + result.toString());
			return result.toString();
		} finally {
			// close stream here
			if (is != null) {
				is.close();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		String content = URLEncoder.encode("客户，您好，你的订单号是987，预计6天可以到货", "GBK");
		// String url =
		// "http://www.106551.com/ws/Send.aspx?CorpID=YX02055&Pwd=888888&Mobile=13905694991&Content="
		// + content;
		String url = "http://www.106551.com/ws/Send.aspx?CorpID=YX02055&Pwd=888888&Mobile=18978721234&Content="
		        + content;
		// /String url =
		// "http://www.106551.com/ws/BatchSend.aspx?CorpID=YX02055&Pwd=888888&Mobile=15922442117,13826574354&Content="
		// + content;// 群发
		String result = GetApiUtils.getApiWithUrl(url);
		//System.out.println("result: " + result);

	}
}
