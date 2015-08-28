package com.ebiz.mmt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

import com.ebiz.mmt.service.IosPushMessageService;
import com.ebiz.org.apache.commons.lang3.StringUtils;
@Service
public class IosPushMessageServiceImpl implements IosPushMessageService {
	private static final JPushClient jpush = 
		new JPushClient("e9ac1e55b2107d5c5e7298c7","6c02a7906a0e2585f58e6e75",true,7);
	private static final  JPushClient jpush1 = 
		new JPushClient("b34d552a2bd2aff927d5b4a2","0482b2590c5f3ec174c711df", true,7);
	private static final JPushClient jpush2 = 
		new JPushClient( "f9f93ea46cd7ce2f638ff428","2484b5ab3156a9d938046197",true,7);
	private static final JPushClient jpush3 = 
		new JPushClient( "8ec34e9133674ef3ca7349ac","9107f6c46118f2eaa0678cc7",true,7);
	
	
	@SuppressWarnings("unchecked")
	@Override
	/**
	 * @该方法用于发送广播信息是群发的信息
	 * @alert 弹出框的内容
	 * @title 标题
	 * @type 类型 1 订单  2 催办  3 资讯
	 * @url  链接地址
	 * @message 信息文本
	 */
	public void SendMessage(String alert, String title, Integer type, String url, String message) {
		
		if (StringUtils.isNotBlank(alert) && StringUtils.isNotBlank(title) && null != type
				&& StringUtils.isNotBlank(url) && StringUtils.isNotBlank(message)) {
			Map map = new HashMap();
			map.put("url", url);
			map.put("type", type.toString());
			PushPayload puShpayLoad = PushPayload.newBuilder().setPlatform(Platform.ios()).setAudience(Audience.all())
					.setMessage(Message.newBuilder().setMsgContent(message).build()).setNotification(
							Notification.ios(alert, map)).build();
			try {
				jpush.sendPush(puShpayLoad);
				jpush1.sendPush(puShpayLoad);
			//	jpush2.sendPush(puShpayLoad);
			//	jpush3.sendPush(puShpayLoad);
			} catch (APIConnectionException e) {
				e.printStackTrace();
			} catch (APIRequestException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	/**
	 *  @该方法用于发送对单个用户的信息
	 * @alert 弹出框的内容
	 * @title 标题
	 * @type 类型 1 订单  2 催办  3 资讯
	 * @url  链接地址
	 * @message 信息文本
	 * @aliasList 对点的uid(单个用户id)
	 */
	public void SendMessage(String alert, String title, Integer type, String url, String message, String alias) {
		

		if (StringUtils.isNotBlank(alert) && StringUtils.isNotBlank(title) && null != type
				&& StringUtils.isNotBlank(url) && StringUtils.isNotBlank(message)&& StringUtils.isNotBlank(alias)) {
			Map map = new HashMap();
			map.put("url", url);
			map.put("type", type.toString());
			PushPayload puShpayLoad = PushPayload.newBuilder().setPlatform(Platform.ios()).setAudience(
					Audience.alias(alias)).setMessage(Message.newBuilder().setMsgContent(message).build())
					.setNotification(Notification.ios(alert, map)).build();
			try {
				jpush.sendPush(puShpayLoad);
				jpush1.sendPush(puShpayLoad);
			//	jpush2.sendPush(puShpayLoad);
			//	jpush3.sendPush(puShpayLoad);
			} catch (APIConnectionException e) {
				e.printStackTrace();
			} catch (APIRequestException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	/**
	 * @alert 弹出框的内容
	 * @title 标题
	 * @type 类型 1 订单  2 催办  3 资讯
	 * @url  链接地址
	 * @message 信息文本
	 * @aliasList 对点的uid集合(用户id)
	 */
	public void SendMessage(String alert, String title, Integer type, String url, String message, List<String> aliasList) {
		
		if (StringUtils.isNotBlank(alert) && StringUtils.isNotBlank(title) && null != type
				&& StringUtils.isNotBlank(url) && StringUtils.isNotBlank(message)&& 
				null!=aliasList&&aliasList.size()>0) {
		Map map = new HashMap();
		map.put("url", url);
		map.put("type", type.toString());
		PushPayload puShpayLoad = PushPayload.newBuilder().setPlatform(Platform.ios()).setAudience(
				Audience.alias(aliasList)).setMessage(Message.newBuilder().setMsgContent(message).build())
				.setNotification(Notification.ios(alert, map)).build();
		try {
			jpush.sendPush(puShpayLoad);
			jpush1.sendPush(puShpayLoad);
			//jpush2.sendPush(puShpayLoad);
			//jpush3.sendPush(puShpayLoad);
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
}}
