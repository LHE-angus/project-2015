/*
 * 
 */
package com.ebiz.mmt.service;

import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;

import com.ebiz.mmt.domain.JdxxEntpSell;

/**
 * The Interface InteractWebService.
 * 
 * @author Xi, Cheng
 * @version 2010-5-28 17:08:10
 */
public interface InteractWebService {

	// 家电下乡接口
	/**
	 * Gets the jdxx entp info by key seq.
	 * 
	 * @param params , Structure:{"keySeq":keySeq}
	 * @return the jdxx entp info by key sequence.<br />
	 *         Structure:{"stdEntpMain":StdEntpMain, "stdEntpUser":StdEntpUser}
	 */
	public HashMap<String, Object> getJdxxEntpInfoByKeySeq(HashMap<String, String> params);

	/**
	 * Gets the jdxx entp info by username & password.
	 * 
	 * @author Jiang,JiaYong
	 * @version 2011-03-24
	 * @param params , Structure:{"username":username,"password":passsword}
	 * @return the jdxx entp info by username & password.<br />
	 *         Structure:{"stdEntpMain":StdEntpMain, "stdEntpUser":StdEntpUser}
	 */
	public HashMap<String, Object> getJdxxEntpInfoByUserNameAndPass(HashMap<String, String> params);

	/**
	 * Gets the jdxx entp sell list.
	 * 
	 * @param params , Structure:{"username":username, "password":password, "keySeq":keySeq,
	 *            "identification":identification, "buyerName":buyerName, "sDate":sDate, "eDate":eDate,
	 *            "pdState":pdState, "pdType":pdType, "advPaySign":advPaySign, "pageSize":pageSize, "pageNo":pageNo}
	 * @return the jdxx entp sell list.<br />
	 *         Structure:{"allowNon":allowNon, "pdPrice":pdPrice, "pdState":pdState, "buyerName":buyerName,
	 *         "realAllowMoney":realAllowMoney, "mdName":mdName, "identification":identification,
	 *         "advPaySign":advPaySign, "backSign":backSign, "auditPIndex":auditPIndex, "id":id, "record_count":
	 *         record_count }
	 */
	public List<HashMap<String, String>> getJdxxEntpSellList(HashMap<String, String> params);

	/**
	 * Gets the jdxx pd details.
	 * 
	 * @param params the params, Structure:{"username":username, "password":password, "keySeq":keySeq,
	 *            "identification":identification, "pdSerial":pdSerial}
	 * @return the jdxx pd details, Structure:{"message":"", "Subscale":"13", "PdId":9088454, "SellentpId":"",
	 *         "mdName":"岳西冰箱-001", "PdType":1, "pdNumber":9400854, "priceLimit":2000.01,
	 *         "Ext1":"该类产品的库存数据量为：0, 请尽快补足库存。", "addDate":"2010-04-28 09:29:39.0", "result_code":1, "Sellentp":"",
	 *         "PdName":"冰箱", "limitallowmoney":"325", "ProductEntpName":"岳西县生产企业", "ProductEntpId":9088452}
	 */
	public HashMap<String, String> getJdxxPdDetails(HashMap<String, String> params);

	/**
	 * Gets the jdxx pd details with rpr.
	 * 
	 * @param params the params, Structure:{"username":username, "password":password, "keySeq":keySeq,
	 *            "identification":identification, "pdSerial":pdSerial "isAdvPay":isAdvPay, "idCard":idCard, "rpr":rpr}
	 * @return the jdxx pd details with rpr, Structure:{"message":"", "Subscale":"13", "PdId":9088454, "SellentpId":"",
	 *         "mdName":"岳西冰箱-001", "PdType":1, "pdNumber":9400854, "priceLimit":2000.01,
	 *         "Ext1":"该类产品的库存数据量为：0, 请尽快补足库存。", "addDate":"2010-04-28 09:29:39.0", "result_code":1, "Sellentp":"",
	 *         "PdName":"冰箱", "limitallowmoney":"325", "ProductEntpName":"岳西县生产企业", "ProductEntpId":9088452}
	 */
	public HashMap<String, String> getJdxxPdDetailsWithRpr(HashMap<String, String> params);

	/**
	 * Gets the jdxx sell list by rpr.
	 * 
	 * @param params the params, Structure:{"username":username, "password":password, "keySeq":keySeq, "rpr":rpr,
	 *            "idCard":idCard, "identification":identification}
	 * @return the jdxx sell list by rpr, Structure:{"Object0":3540185, "Object1":王怀林, "Object2":MELIFG191PM000738680,
	 *         "Object3":2008-04-24, "Object4":已补贴, "Object5":512925451216131, "Object6":冰箱, "Object7":否,
	 *         "record_count":record_count}
	 */
	public List<HashMap<String, String>> getJdxxSellListByRpr(HashMap<String, String> params);

	/**
	 * Creates the jdxx entp sell.
	 * 
	 * @param entity the entity
	 * @param username the username
	 * @param password the password
	 * @param keySeq the key seq
	 * @param isBeian the is beian
	 * @return the hash map
	 */
	public HashMap<String, String> createJdxxEntpSell(JdxxEntpSell entity, String username, String password,
			String keySeq, Integer isBeian);

	/**
	 * Modify jdxx entp sell.
	 * 
	 * @param entity the entity
	 * @param username the username
	 * @param password the password
	 * @param keySeq the key seq
	 * @param isBeian the is beian
	 * @return the hash map
	 */
	public HashMap<String, String> modifyJdxxEntpSell(JdxxEntpSell entity, String username, String password,
			String keySeq, Integer isBeian);

	/**
	 * Modify jdxx entp sell for admin.
	 * 
	 * @param entity the entity
	 * @param username the username
	 * @param password the password
	 * @param keySeq the key seq
	 * @param isBeian the is beian
	 * @return the hash map
	 */
	public HashMap<String, String> modifyJdxxEntpSellForAdmin(JdxxEntpSell entity, String username, String password,
			String keySeq, Integer isBeian);

	/**
	 * Gets the jdxx entp sell.
	 * 
	 * @param params the params, Structure:{"username":username, "password":password, "keySeq":keySeq, "id":id}
	 * @return the jdxx entp sell, Structure:{"BUYER_LINK":"010-", "BUYSB_LINK":"", "INVOICE_NO":"20100514",
	 *         "message":"000:成功!", "SUB_SCALE":"13", "BUYER_BANK_ACCOUNT":"", "IDENTIFICATION":"YXSCFG123FC239452786",
	 *         "BUYSB_MOBILE":"", "ADD_DATE":"2010-04-28", "BUYSB_NAME":"", "PD_ID":9088454, "ADV_PAY_SIGN":1,
	 *         "SELL_DATE":"2010-05-14", "SC_ENTP_ID":9088452, "SELL_ENTP_NAME":"试点门店", "BUYER_ADDR":"安徽岳西",
	 *         "BUYSB_ADDR":"", "BUYER_ID":"201005111111222222", "KIN":"本人", "MD_NAME":"岳西冰箱-001", "BUYER_BANK_USER":"",
	 *         "ALLOW_MEMO":"", "BUYER_MOBILE":"139", "SC_ENTP_NAME":"岳西县生产企业", "PD_STATE":30, "priceLimit":"2000.01",
	 *         "BUYER_NAME":"张三", "ALLOW_DATE":"", "REAL_ALLOW_MONEY":0, "BUYER_BANK_NAME":"", "result_code":1,
	 *         "HOUSEMASTER":"张三", "ALLOW_NON":0, "SELL_LOG_MEMO":"", "PD_PRICE":1800, "PD_SERIAL":"yxbx1hao001",
	 *         "PD_TYPE":1, "RPR_NUMBER":"20100514", "limitAllowMoney":"325", "SELL_ENTP_ID":9114933, "BUYSB_ID":""}
	 */
	public HashMap<String, String> getJdxxEntpSell(HashMap<String, String> params);

	/**
	 * Gets the jdxx entp sell return list.
	 * 
	 * @param params the params, Structure:{"username":username, "password":password, "keySeq":keySeq, "pdType":pdType,
	 *            "pdState":pdState, "identification":identification, "sDate":sDate, "eDate":eDate, "pageSize":pageSize,
	 *            "pageNo":pageNo}
	 * @return the jdxx entp sell return list, Structure:{"pdState":pdState, "pdPrice":pdPrice, "buyerName":buyerName,
	 *         "realAllowMoney":realAllowMoney, "identification":identification, "mdName":mdName, "id":id,
	 *         "pdName":pdName, "record_count":record_count}
	 */
	public List<HashMap<String, String>> getJdxxEntpSellReturnList(HashMap<String, String> params);

	/**
	 * Return jdxx entp sell.
	 * 
	 * @param params the params, Structure:{"username":username, "password":password, "keySeq":keySeq, "id":id}
	 * @return the hash map, Structure:{"message":message, "result_code":result_code}
	 */
	public HashMap<String, String> returnJdxxEntpSell(HashMap<String, String> params);

	/**
	 * verify jdxx identification.
	 * 
	 * @author Chen,LiLin
	 * @version 2011-2-12
	 * @param params the params, Structure:{"keySeq":keySeq, "identification":identification}
	 * @return the hash map, Structure:{"message":000:the card query failure, "result_code":0} {"message":001:第1版,
	 *         "result_code":1} {"message":002:第2版, "result_code":2}...
	 */
	public HashMap<String, String> verifyIdentification(HashMap<String, String> params);

	/**
	 * get jdxx buyer buy record by idCard.
	 * 
	 * @author Chen,LiLin
	 * @version 2011-2-15
	 * @param params the params, Structure:{"username":username, "password":password, "keySeq":keySeq, "idCard":idCard,
	 *            "identification":identification}
	 * @return the info list, Structure:{"Object0":记录ID,"Object1":购买人名,"Object2 ":标识卡号," Object3
	 *         ":购买日期,"Object4":状态,"Object5":购买人身份证号,"Object6":购买产品类别,"Object7 ":是否先行垫付},{...},...
	 */
	public List<HashMap<String, String>> getJdxxBuyerRecordListByIdcard(HashMap<String, String> params);

	// 以旧换新接口
	/**
	 * Gets the yjhx entp info by key seq and username.
	 * 
	 * @param params the params, Structure:{"keySeq":keySeq}
	 * @return the yjhx entp info by key seq and username, Structure:{"stdEntpMain":stdEntpMain,
	 *         "stdEntpUser":stdEntpUser}
	 */
	public HashMap<String, Object> getYjhxEntpInfoByKeySeq(HashMap<String, String> params);

	/**
	 * Gets the yjhx entp info by username & pass.
	 * 
	 * @author Jiang,JiaYong
	 * @version 2011-03-23
	 * @param params the params, Structure:{"username":username,"pass":pass}
	 * @return the yjhx entp info by key seq and username, Structure:{"stdEntpMain":stdEntpMain,
	 *         "stdEntpUser":stdEntpUser}
	 */
	public HashMap<String, Object> getYjhxEntpInfoByUserPass(HashMap<String, String> params);

	/**
	 * Verify yjhx voucher code.
	 * 
	 * @param params the params, Structure:{"voucherCode":voucherCode,"username":username
	 *            ,"password":password,"keySeq":keySeq}
	 * @return the hash map, Structure:{"message":message,"result_code":result_code} message Code 016:凭证编号未使用!
	 *         017:凭证编号已回收! 018:凭证编号已销售! 019:凭证编号对应销售记录冗余错误! 020:凭证编号无对应回收记录错误! 021:凭证编号对应回收记录冗余错误!
	 *         022:凭证编号不能为空且长度在20位以内! 023:凭证编号不存在!
	 */
	public HashMap<String, String> verifyYjhxVoucherCode(HashMap<String, String> params);

	/**
	 * Verify yjhx voucher code.
	 * 
	 * @param params the vcode, Structure:{"vcode":vcode}
	 * @return the hash map, Structure:{"message":message,"result_code":result_code} message
	 *         result_code="0":凭证号不存在;result_code="1":已存在且已销售 ;result_code="2":已回收未销售 ;
	 *         esult_code="3":凭证号存在但未使用(即没有人用它交旧或买家电)
	 */
	public HashMap<String, String> verifyVcodeByVcode(HashMap<String, String> params);

	/**
	 * Gets the yjhx entp callback list.
	 * 
	 * @param params the params, Structure:{"username":username,"password":password,
	 *            "keySeq":keySeq,"voucherCode":voucherCode ,"sDate":sDate,"eDate"
	 *            :eDate,"pdType":pdType,"customerIdCardType" :customerIdCardType,"customerIdCard":
	 *            "customerIdCard","pageSize":pageSize,"pageNo":pageNo}
	 * @return the yjhx entp callback list,Structure:{"ID":id,"pdType":pdType,"voucherCode"
	 *         :voucherCode,"hsDate":hsDate,"voucherSub" :voucherSub,"customerName"
	 *         :customerName,"record_cound":record_count} record_count为条件查询记录总数 pdType对应产品类别 1：电视机 2：冰箱 3：洗衣机 4：空调 5：电脑
	 */
	public List<HashMap<String, String>> getYjhxEntpCallbackList(HashMap<String, String> params);

	/**
	 * Verify yjhx hs serial.
	 * 
	 * @param params the params, Structure:{"hsSerial":hsSerial,"username":username,
	 *            "password":password,"keySeq":keySeq}
	 * @return the hash map, Structure:{"message":message,"result_code":result_code} message code 016:回收产品序列号***已经使用!
	 *         017:回收产品序列号，未使用，唯一! 018:回收产品序列号不能为空且长度在30位以内!
	 */
	public HashMap<String, String> verifyYjhxHsSerial(HashMap<String, String> params);

	/**
	 * Gets the yjhx entp callback.
	 * 
	 * @param params the params, Structure:{"voucherCode":voucherCode,"username":username
	 *            ,"password":password,"keySeq":keySeq}
	 * @return the yjhx entp callback,{ "HS_ENTP_ID":HS_ENTP_ID, "CUSTOMER_IDCARD":CUSTOMER_IDCARD, "HS_MODEL":HS_MODEL,
	 *         "CA_SPEC":CA_SPEC, "VOUCHER_CODE":VOUCHER_CODE, "message":message, "result_code":result_code,
	 *         "PD_TYPE_NAME":PD_TYPE_NAME, "CUSTOMER_IDCARD_TYPE":CUSTOMER_IDCARD_TYPE, "CUSTOMER_TEL":CUSTOMER_TEL,
	 *         "SELL_INFO_STATE":SELL_INFO_STATE, "HS_BRAND":HS_BRAND, "HS_MEMO":HS_MEMO,
	 *         "OLD_VOUCHER_CODE":OLD_VOUCHER_CODE, "HS_ADDR":HS_ADDR, "HS_SERIAL":HS_SERIAL, "HS_DATE":HS_DATE,
	 *         "HS_MONEY":HS_MONEY, "PD_TYPE":PD_TYPE, "HS_VOL":HS_VOL, "CUSTOMER_ADDR":CUSTOMER_ADDR,
	 *         "HS_INPUT_ENTP_ID":HS_INPUT_ENTP_ID, "HS_INPUT_DATE":HS_INPUT_DATE, "ENTP_NAME":ENTP_NAME,
	 *         "CJ_INFO_STATE":CJ_INFO_STATE, "HS_ENTP_NAME":HS_ENTP_NAME, "P_INDEX":P_INDEX,
	 *         "CUSTOMER_MOBILE":CUSTOMER_MOBILE, "CUSTOMER_NAME":CUSTOMER_NAME, "VOUCHER_SUB":VOUCHER_SUB,
	 *         "P_NAME":P_NAME }
	 */
	public HashMap<String, String> getYjhxEntpCallback(HashMap<String, String> params);

	/**
	 * Change yjhx voucher.
	 * 
	 * @param params the params, Structure:{"oldVoucherCode":oldVoucherCode,"newVoucherCode"
	 *            :newVoucherCode,"username":username ,"password":password,"keySeq":keySeq}
	 * @return the hash map, Structure:{"message":message,"result_code":result_code} message code 016:凭证号替换成功!
	 *         017:输入的凭证号已登记销售记录或此凭证已经有过替换记录，不能修改！ 018:输入的凭证号尚未登记回收记录或此凭证为替换过的旧凭证！ 019:输入的凭证号未分配到贵企业! 020:输入的凭证号不存在!
	 *         021:旧凭证编号不能为空且长度在20位以内! 022:新凭证编号不能为空且长度在20位以内! 023:输入的新凭证号已经使用! 024:输入的新凭证号为已经替换过的旧凭证!
	 *         025:输入的新凭证号未分配到贵企业! 026:输入的新凭证号不存在!
	 */
	public HashMap<String, String> changeYjhxVoucher(HashMap<String, String> params);

	/**
	 * Gets the yjhx entp sell list.
	 * 
	 * @param params the params, Structure:{"sType":sType,"voucherCode":voucherCode,
	 *            "sDate":sDate,"eDate":eDate,"pdType": pdType,"pageSize":pageSize ,"pageNo":pageNo,"username":username,
	 *            "password":password,"keySeq":keySeq}
	 * @return the yjhx entp sell list, Structure:{"returnInfoState":0,"voucherSub"
	 *         :0,"xsDate":"2010-05-19 16:57:27.0","voucherCode" :"DEBFPUSHBD0000066912"
	 *         ,"allowInfoState":0,"pdType":2,"auditInfoState" :0,"allowMoney":100,"id":580818,"record_count":6}
	 */
	public List<HashMap<String, String>> getYjhxEntpSellList(HashMap<String, String> params);

	/**
	 * Gets the yjhx entp sell list by id card invoice.
	 * 
	 * @param params the params, Structure:{"customerIdCardType":customerIdCardType,
	 *            "customerIdCard":customerIdCard,"invoiceNo" :invoiceNo,"username"
	 *            :username,"password":password,"keySeq":keySeq}
	 * @return the yjhx entp sell list by id card invoice
	 */
	public List<HashMap<String, String>> getYjhxEntpSellListByIdCardInvoice(HashMap<String, String> params);

	/**
	 * Gets the yjhx entp sell.
	 * 
	 * @param params the params, Structure:{"id":id,"username":username ,"password":password,"keySeq":keySeq}
	 * @return the yjhx entp sell, Structure:{"SUBSCALE":0.1,"XSDATE":"2010-01-14"
	 *         ,"XSSERIAL":"CLK-1","PDTYPE":2,"INVOICENO" :"苏59087654356","ID" :526500
	 *         ,"CUSTOMERIDCARDTYPE":1,"XSMODELNAME":"CW20100604","ALLOWMONEY" :15, "XSALLOWMONEY":15,"message"
	 *         :"000:成功!","result_code" :1,"RETURNINFOSTATE"
	 *         :0,"CUSTOMERTEL":"010-11223344","CUSTOMERNAME":"张晓柳","LIMITMONEY" :0,"AUDITINFOSTATE":1
	 *         ,"CUSTOMERIDCARD":"111222333344445555","ALLOWINFOSTATE" :1,"CUSTOMERADDR":"江苏南京","CUSTOMERMOBILE"
	 *         :"15011224455","XSBRAND":5,"XSMEMO":"","XSMONEY":150}
	 */
	public HashMap<String, String> getYjhxEntpSell(HashMap<String, String> params);

	/**
	 * Return yjhx entp sell.
	 * 
	 * @param params the params, Structure:{"voucherCode":voucherCode,"username":username
	 *            ,"password":password,"keySeq":keySeq}
	 * @return the hash map
	 */
	public HashMap<String, String> returnYjhxEntpSell(HashMap<String, String> params);

	/**
	 * Gets the xs hs count by idcard.
	 * 
	 * @param params the params, Structure:{"idCardType":idCardType, "idCard":idCard}
	 * @return the xs hs count by idcard, Structure:{"afterhsCount":6,"sellCount"
	 *         :0,"hsCount":6,"sparesellCount":5,"message" :"000:成功!","result_code":1,"aftersellCount":0}
	 */
	public HashMap<String, String> getXsHsCountByIdcard(HashMap<String, String> params);

	/**
	 * Check can continue to hs by idcard.
	 * 
	 * @param params the params, Structure:{"idCardType":idCardType, "idCard":idCard}
	 * @return can continue to hs by idcard, Structure:{"message":"false","result_code":1}
	 */
	public HashMap<String, String> verifyHsCountByIdcard(HashMap<String, String> params);

	/**
	 * get cancel order count per shop.
	 * 
	 * @return Structure:{["shop_id":"count1"],["cancel_order":count2]}
	 */
	public List<HashMap<String, String>> GetCancelOrderCount();
	
	/**
	 * @desc 康佳进销存客户端非密钥登录版
	 * @param method 接口方法名称：
	 * @param type 接口类型：1登录 2注销 3验证会话
	 * @return param state Number 必填，状态码 -1:参数丢失；0：已过期；1：正常；2：正常已延时
	 * @return param msg String 必填，消息 验证提示信息
	 * @return param user_name String 非必填，会话用户 当会话正常为必填
	 * @return param user_id Number 非必填，会话用户ID 当会话正常为必填
	 * @return param shop_id Number 非必填，会话商铺编码 当会话正常为必填
	 * @return {"state":"1","user_name":"sdmd","user_id":"123","shop_id":"345"}
	 */
	public HashMap<String, String> loginWebServiceForJxcClient(HashMap<String, String> params, int type);
	
	/**
	 * @author Li,Ka
	 * @desc 康佳进销存客户端发送单条短信接口
	 * @param url 接口方法名称：
	 * @param qparams 接口参数：包括method、	mc_uuid：签订商户协议后，在买卖提短信平台上面生产的“商户UUID”
	 *                         mobile：接受短信的手机号码/mobiles:接受短信的手机号码,以半角逗号分隔，如“13811111111,13822222222,13833333333”、
	 *                         content：发送的短信内容、
	 *                         key:校验码(content + mc_uuid + mobile/mobiles + 商户协议中约定的key)经过MD5加密之后的值>
	 * @param charset 字符编码
	 * @return "0"; // 发送成功success、
	 *         "-1"; // 参数为空params is null;
				"-2"; // 手机号码不合法mp is illegal;
				"-3"; // IOException;
				"-4"; // upload exception;
				"-41"; //upload file is too big(size :2M);
				"-5"; // ParseException;
				"-11"; // 保留remain;
				"-12"; // 校验不合法key illegal;
				"-13"; // ip illegal;
				"-14"; // unknown error;
				"-100"; // 商户ID为空mc uuid is null;
				"-101"; // 商户的参数为空mc param is null;
				"-102"; // 商户不存在mc is not exist;
				"-103"; // 商户密钥校验错误mc key is error;
				"-104"; // 商户IP地址不是合同指定的mc ip is error;
				"-105,**"; // 短信内容超过最大长度，**为数字表示最大长度
				"-106,**"; // 群发短信超过最大限制，**为数字表示最大群发数
	 */
	public HttpResponse sendMsgWebServiceForJxcClient(String url, List<NameValuePair> qparams, String charset);
	
}
