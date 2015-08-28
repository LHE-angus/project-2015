package com.taobao.api.sync;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Task;
import com.taobao.api.domain.Trade;
import com.taobao.api.internal.util.AtsUtils;
import com.taobao.api.internal.util.TaobaoUtils;
import com.taobao.api.request.TopatsResultGetRequest;
import com.taobao.api.request.TopatsTradesSoldGetRequest;
import com.taobao.api.response.TopatsResultGetResponse;
import com.taobao.api.response.TopatsTradesSoldGetResponse;
import com.taobao.api.response.TradeFullinfoGetResponse;

/**
 * @author Tudp
 * @version 2013-09-27
 */
public class TaoBaoService {

	private static final Log log = LogFactory.getLog(TaoBaoService.class);
	public static String API_URL = "http://gw.api.tbsandbox.com/router/rest";//订单同步沙箱测试地址
	//订单同步真实地址
	//private static final String API_URL = "http://gw.api.taobao.com/router/rest";
	public static String APP_KEY = "test";
	public static String APP_SECRET = "test";
	public static String SESSION_KEY = "6101813112fbded1142381ece45b633a381c53976144a932074082786";
	public static String FIELDS = "tid,seller_nick,buyer_nick,payment,orders";
	
	/**
	 * 创建抓取订单同步任务
	 * @param startTime 开始时间 20130901
	 * @param endTime 结束时间 20130930
	 * @throws Exception
	 * @return taskId
	 */	
	public Long createTask(String startTime,String endTime) throws Exception {
		TaobaoClient client=new DefaultTaobaoClient(API_URL, APP_KEY, APP_SECRET);
		TopatsTradesSoldGetRequest req=new TopatsTradesSoldGetRequest();
		req.setFields(FIELDS);
		req.setStartTime(startTime);
		req.setEndTime(endTime);
		//req.setIsAcookie(true);
		TopatsTradesSoldGetResponse response = client.execute(req , SESSION_KEY);
		if(response.isSuccess()){
			Long taskId=response.getTask().getTaskId();			
			log.info("任务创建成功");
			log.info("taskId:"+taskId);
			//save task....任务保存到数据库taskId FIELDS startTime endTime
			return taskId;
		}else{
			String errorCode = response.getErrorCode();
			String rspBody = response.getBody();
			log.info("errorCode:"+errorCode);//
			if("15".equals(errorCode)){
				try{
				String[] x = rspBody.split("TaskId=")[1].split("\"");
				Long  taskId=new Long(x[0]);
				log.info("任务已存在,taskId:"+taskId);
				return taskId;
				}catch(Exception e){ 
				}
			}
			log.info(rspBody);
		}
		return null;
	}
	
	/**
	 * 根据任务id 查询任务下载文件地址， 如果任务状态 等于 'done' 返回下载地址，否则，返回 null
	 * @param taskId
	 * @return DownloadUrl
	 * @throws ApiException
	 */
	public String getTaskResultUrl(Long taskId) throws ApiException {
		final TaobaoClient client=new DefaultTaobaoClient(API_URL, APP_KEY, APP_SECRET);
		TopatsResultGetRequest req = new TopatsResultGetRequest();
		req.setTaskId(taskId);
		TopatsResultGetResponse rsp = client.execute(req);
		if (rsp.isSuccess()) {
			if ("done".equals(rsp.getTask().getStatus())) {
				return rsp.getTask().getDownloadUrl();
			}
		}
		return null;
	}
	
	/**
	 * 根据任务id 查询任务信息
	 * @param taskId
	 * @return Task
	 * @throws ApiException
	 */
	public Task getTask(Long taskId) throws ApiException {
		final TaobaoClient client=new DefaultTaobaoClient(API_URL, APP_KEY, APP_SECRET);
		TopatsResultGetRequest req = new TopatsResultGetRequest();
		req.setTaskId(taskId);
		TopatsResultGetResponse rsp = client.execute(req);
		if (rsp.isSuccess()) {
			return rsp.getTask();
		}
		return null;
	}
	
	/**
	 * 下载文件
	 * @param url
	 * @return zip File
	 * @throws Exception
	 */
	public File download(String url) throws Exception {
		File zip = AtsUtils.download(url, new File("e:/Downloads/Trade/Zip"));		
		return zip;
	}
	
	/**
	 * 解析文件, 获取订单信息
	 * @param zip
	 * @return List&lt;Trade&gt;
	 * @throws Exception
	 */
	public List<Trade> getTradeList(File zip) throws Exception {
		List<File> files = AtsUtils.unzip(zip, new File("e:/Downloads/Trade/Unzip"));
		List<Trade> list=new ArrayList<Trade>();
		for (File file : files) {
			BufferedReader br = null;
			try {
				FileReader fr = new FileReader(file);
				br = new BufferedReader(fr);
				String line = null;
				while ((line = br.readLine()) != null) {
					TradeFullinfoGetResponse rsp = TaobaoUtils.parseResponse(line, TradeFullinfoGetResponse.class);
					Trade trade = rsp.getTrade(); 
					list.add(trade);
				}
			} finally {
				if (br != null) {
					br.close();
				}
			}
			if(file.exists()){//解析完成后删除文件
				file.delete();
			}
		}
		return list;		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) { 
		
		try {
			
			final TaoBaoService service = new TaoBaoService();
			
			final Long taskId = service.createTask("20130901", "20130914");
			if(taskId!=null){
			new Timer().schedule(new TimerTask() {
				int i=0;
				public void run() {
					try {i++;
						if(taskId!=null&&"done".equals(service.getTask(taskId).getStatus())){
						File zip=service.download(service.getTaskResultUrl(taskId));
						service.getTradeList(zip);
						this.cancel();
						}else{
							log.info("i:"+i);
						}
						if(i>30){
							log.info("定时任务结束了");
							this.cancel();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}, 0L, 5 * 60 * 1000L);
			}else{
				log.info("创建任务失败");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
			//Proposal.startProposal3();
//			
//			final TaobaoClient client=new DefaultTaobaoClient(API_URL, APP_KEY, APP_SECRET);
//			TopatsTradesSoldGetRequest req=new TopatsTradesSoldGetRequest();
//			req.setFields("tid,seller_nick,buyer_nick,payment,orders");
//			req.setStartTime("20130901");
//			req.setEndTime("20130922");
//			//req.setIsAcookie(true);
//			final TopatsTradesSoldGetResponse response = client.execute(req , SESSION_KEY);
//			final Long id=response.getTask().getTaskId();
//			log.info("task_id:"+id);
//			
//			new Timer().schedule(new TimerTask() {
//				int i=0;
//				public void run() {
//					try {i++;
//						if(id!=null&&"done".equals(response.getTask().getStatus())){
//						TopApiService topSerice=new TopApiService(client);
//						topSerice.downloadAndProcess(topSerice.getTaskResultUrl(id)); 
//						}else{
//							log.info("i:"+i);
//						}
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}, 0L, 1 * 60 * 1000L);
			 
	}

}
