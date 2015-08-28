package com.ebiz.mmt.web.struts;

import java.net.URLEncoder;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONObject;

import com.ebiz.mmt.web.util.yixun.GetApiUtils;
import com.ebiz.mmt.web.util.yixun.config.YixunConfig; 

public class SendMobileMessageAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.sendMobileMessage(mapping, form, request, response);
	}

	/**
	 * @author Pan,Gang
	 * @param CorpID
	 *            -帐号
	 * @param Pwd
	 *            -密码
	 * @param Mobile
	 *            -发送手机号
	 * @param Content
	 *            -发送内容
	 * @param Content
	 *            -单个手机号码发短信
	 * @return
	 * @throws Exception
	 */
	public ActionForward sendMobileMessage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// http://www.106551.com/ws/Send.aspx?CorpID=YX02055&Pwd=888888&Mobile=18756989157&Content=abc&Cell=*&SendTime=*
		String mobile = (String) dynaBean.get("mobile");
		String state = "0";
		JSONObject obj = new JSONObject();

		if (StringUtils.isNotBlank(mobile)) {
			// HttpSession session = request.getSession();

			// 随机生成6位整数
			// String ranNm = String.valueOf(generateCheckPass());
			// logger.info("_______________________________手机验证码：" + ranNm);

			String paygateway = YixunConfig.paygateway;
			String CorpID = YixunConfig.CorpID;
			String Key = YixunConfig.key;
			// String msg = URLEncoder.encode(getMessage(request,
			// "send.message.for.register", new String[] { ranNm }),
			// "GBK");
			String msg = URLEncoder.encode("验证码是987654", "GBK");

			StringBuffer sb = new StringBuffer();
			sb.append(paygateway);
			sb.append("CorpID=").append(CorpID);
			sb.append("&Pwd=").append(Key);
			sb.append("&Mobile=").append(mobile);
			sb.append("&Content=").append(msg);
			String redirectUrl = sb.toString();

			logger.info("____________________________url1 = " + redirectUrl);
			String result = GetApiUtils.getApiWithUrl(redirectUrl);
			// String result = "0";
			if ("0".equals(result)) {
				logger.info("____________________result.toString = 发送成功");
			} else if ("-1".equals(result)) {
				logger.info("____________________result.toString = 帐号未注册");
			} else if ("-2".equals(result)) {
				logger.info("____________________result.toString = 其他错误");
			} else if ("-3".equals(result)) {
				logger.info("____________________result.toString = 密码错误");
			} else if ("-4".equals(result)) {
				logger.info("____________________result.toString = 手机号格式不对");
			} else if ("-5".equals(result)) {
				logger.info("____________________result.toString = 余额不足");
			} else if ("-6".equals(result)) {
				logger.info("____________________result.toString = 定时发送时间不是有效的时间格式");
			}

			if ("0".equals(result)) {
				state = "1";
			} else {
				state = "2";
			}
		}
		obj.put("state", state);
		super.renderJson(response, obj.toString());
		return null;
	}

	/**
	 * @author Pan,Gang
	 * @param CorpID
	 *            -帐号
	 * @param Pwd
	 *            -密码
	 * @param Mobile
	 *            -发送手机号
	 * @param Content
	 *            -发送内容
	 * @param Content
	 *            -多个手机号码发短信
	 * @return
	 * @throws Exception
	 */

	public ActionForward sendMobileMessageByGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// http://www.106551.com/ws/Send.aspx?CorpID=YX02055&Pwd=888888&Mobile=18756989157&Content=abc&Cell=*&SendTime=*
		String mobile = (String) dynaBean.get("mobile");
		// 用逗号分隔开来
		// 比如18977777777,18766666666
		String state = "0";
		JSONObject obj = new JSONObject();

		if (StringUtils.isNotBlank(mobile)) {
			// HttpSession session = request.getSession();

			// 随机生成6位整数
			// String ranNm = String.valueOf(generateCheckPass());
			// logger.info("_______________________________手机验证码：" + ranNm);

			String paygateway = YixunConfig.smsgateway_forBatch;
			String CorpID = YixunConfig.CorpID;
			String Key = YixunConfig.key;
			// String msg = URLEncoder.encode(getMessage(request,
			// "send.message.for.register", new String[] { ranNm }),
			// "GBK");
			String msg = URLEncoder.encode("验证码是987654", "GBK");

			StringBuffer sb = new StringBuffer();
			sb.append(paygateway);
			sb.append("CorpID=").append(CorpID);
			sb.append("&Pwd=").append(Key);
			sb.append("&Mobile=").append(mobile);
			sb.append("&Content=").append(msg);
			String redirectUrl = sb.toString();

			logger.info("____________________________url1 = " + redirectUrl);
			String result = GetApiUtils.getApiWithUrl(redirectUrl);
			// String result = "0";
			if ("0".equals(result)) {
				logger.info("____________________result.toString = 发送成功进入待发进程");
			} else if ("1".equals(result)) {
				logger.info("____________________result.toString = 直接发送成功");
			} else if ("-1".equals(result)) {
				logger.info("____________________result.toString = 帐号未注册");
			} else if ("-2".equals(result)) {
				logger.info("____________________result.toString = 其他错误");
			} else if ("-3".equals(result)) {
				logger.info("____________________result.toString = 密码错误");
			} else if ("-4".equals(result)) {
				logger.info("____________________result.toString = 一次提交信息不能超过600个手机号码");
			} else if ("-5".equals(result)) {
				logger.info("____________________result.toString = 企业号帐户余额不足，请先充值再提交短信息！");
			} else if ("-6".equals(result)) {
				logger.info("____________________result.toString = 定时发送时间不是有效的时间格式");
			} else if ("-7".equals(result)) {
				logger.info("____________________result.toString = 发送短信内容包含黑字典关键字");
			} else if ("-8".equals(result)) {
				logger.info("____________________result.toString = 发送内容需在3到250个字之间");
			} else if ("-9".equals(result)) {
				logger.info("____________________result.toString = 发送号码为空");
			}
			if ("0".equals(result)) {
				state = "1";
			} else if ("1".equals(result)) {
				state = "1";
			} else {
				state = "2";
			}
		}
		obj.put("state", state);
		super.renderJson(response, obj.toString());
		return null;
	}

	/**
	 * @desc 随机生成没有重复数字的
	 * @return
	 */
	public String getrannumber() {
		StringBuffer strbufguess = new StringBuffer();
		String strguess = new String();
		int[] nums = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Random rannum = new Random();
		int count;
		int i = 0, temp_i = 0;
		for (int j = 10; j > 4; j--) {
			i = 0;
			temp_i = 0;
			count = rannum.nextInt(j);
			while (i <= count) {
				if (nums[temp_i] == -1)
					temp_i++;
				else {
					i++;
					temp_i++;
				}
			}
			strbufguess.append(Integer.toString(nums[temp_i - 1]));
			nums[temp_i - 1] = -1;
		}
		strguess = strbufguess.toString();
		rannum = null;
		strbufguess = null;
		nums = null;
		return strguess;
	}

	/**
	 * 随机生成6位数字字符数组，有重复
	 * 
	 * @return rands
	 */
	public static char[] generateCheckPass() {
		String chars = "0123456789";
		char[] rands = new char[6];
		for (int i = 0; i < 6; i++) {
			int rand = (int) (Math.random() * 10);
			rands[i] = chars.charAt(rand);
		}
		return rands;
	}

}
