package com.ebiz.mmt.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.ebiz.mmt.dao.AreaFightInfoDao;
import com.ebiz.mmt.dao.BaseProvinceListFourDao;
import com.ebiz.mmt.dao.ChannelDataImport2Dao;
import com.ebiz.mmt.dao.ChannelDataImportDao;
import com.ebiz.mmt.dao.EcBaseExpressDao;
import com.ebiz.mmt.dao.EcMessageDao;
import com.ebiz.mmt.dao.EcOrderExpressInfoDao;
import com.ebiz.mmt.dao.EcUserDao;
import com.ebiz.mmt.dao.JBaseStoreDao;
import com.ebiz.mmt.dao.JsSellsDao;
import com.ebiz.mmt.dao.JsStocksDao;
import com.ebiz.mmt.dao.JsTimesDao;
import com.ebiz.mmt.dao.KonkaBbYjhbDao;
import com.ebiz.mmt.dao.KonkaBcompPdDao;
import com.ebiz.mmt.dao.KonkaCategoryDao;
import com.ebiz.mmt.dao.KonkaDeptDao;
import com.ebiz.mmt.dao.KonkaDeptJsMoneyDao;
import com.ebiz.mmt.dao.KonkaJobsDao;
import com.ebiz.mmt.dao.KonkaOrderMessageProcessDao;
import com.ebiz.mmt.dao.KonkaParagonShowshopDetailDao;
import com.ebiz.mmt.dao.KonkaPriceManagerDao;
import com.ebiz.mmt.dao.KonkaR3BackmoneyDao;
import com.ebiz.mmt.dao.KonkaR3DeptStockInfoDao;
import com.ebiz.mmt.dao.KonkaR3OrderDao;
import com.ebiz.mmt.dao.KonkaR3ShopCreditDao;
import com.ebiz.mmt.dao.KonkaR3ShopDao;
import com.ebiz.mmt.dao.KonkaR3ZsofDao;
import com.ebiz.mmt.dao.KonkaSalesDept2Dao;
import com.ebiz.mmt.dao.KonkaSalesDeptDao;
import com.ebiz.mmt.dao.KonkaSellDetailsDao;
import com.ebiz.mmt.dao.KonkaSellReportTmpDao;
import com.ebiz.mmt.dao.KonkaStockDetailsDao;
import com.ebiz.mmt.dao.KonkaXxSellBillDao;
import com.ebiz.mmt.dao.KonkaXxSellBillDetailsDao;
import com.ebiz.mmt.dao.KonkaXxSellBillDetailsDstDao;
import com.ebiz.mmt.dao.KonkaXxZmdDailyDistDao;
import com.ebiz.mmt.dao.KonkaXxZmdDailyDistDetailDao;
import com.ebiz.mmt.dao.KonkaXxZmdDailyStmsDao;
import com.ebiz.mmt.dao.KonkaXxZmdDailyStmsDetailDao;
import com.ebiz.mmt.dao.KonkaXxZmdDao;
import com.ebiz.mmt.dao.KonkaZles23ResultInfoDao;
import com.ebiz.mmt.dao.PePdModelDao;
import com.ebiz.mmt.dao.PeProdUserDao;
import com.ebiz.mmt.dao.PshowOrdeAuditDao;
import com.ebiz.mmt.dao.PshowOrdeDetailsDao;
import com.ebiz.mmt.dao.PshowOrderDao;
import com.ebiz.mmt.dao.SysSettingDao;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.ChannelDataImport;
import com.ebiz.mmt.domain.ChannelDataImport2;
import com.ebiz.mmt.domain.EcBaseExpress;
import com.ebiz.mmt.domain.EcMessage;
import com.ebiz.mmt.domain.EcOrderExpressInfo;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.JBaseGoods;
import com.ebiz.mmt.domain.JBaseGoodsInitStock;
import com.ebiz.mmt.domain.JBaseStore;
import com.ebiz.mmt.domain.JBaseStoreR3;
import com.ebiz.mmt.domain.JBaseType;
import com.ebiz.mmt.domain.JStocksStoreSummary;
import com.ebiz.mmt.domain.JStocksSummary;
import com.ebiz.mmt.domain.JsSells;
import com.ebiz.mmt.domain.JsStocks;
import com.ebiz.mmt.domain.JsTimes;
import com.ebiz.mmt.domain.KonkaBaseTypeData;
import com.ebiz.mmt.domain.KonkaBbItr2Import;
import com.ebiz.mmt.domain.KonkaBbYjhb;
import com.ebiz.mmt.domain.KonkaBbZj98Import;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaDeptJsMoney;
import com.ebiz.mmt.domain.KonkaFgsCkSn;
import com.ebiz.mmt.domain.KonkaFgsEd;
import com.ebiz.mmt.domain.KonkaJobs;
import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.domain.KonkaOrderMessageProcess;
import com.ebiz.mmt.domain.KonkaPartership;
import com.ebiz.mmt.domain.KonkaR3Backmoney;
import com.ebiz.mmt.domain.KonkaR3DeptStockInfo;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3ShopCredit;
import com.ebiz.mmt.domain.KonkaR3Zsof;
import com.ebiz.mmt.domain.KonkaSalesDept;
import com.ebiz.mmt.domain.KonkaSalesDept2;
import com.ebiz.mmt.domain.KonkaSellDetails;
import com.ebiz.mmt.domain.KonkaSendMailInfo;
import com.ebiz.mmt.domain.KonkaSendMailUser;
import com.ebiz.mmt.domain.KonkaSpList;
import com.ebiz.mmt.domain.KonkaStockDetails;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxSellBillDetails;
import com.ebiz.mmt.domain.KonkaXxSellBillDetailsDst;
import com.ebiz.mmt.domain.KonkaXxZmdDailyDist;
import com.ebiz.mmt.domain.KonkaXxZmdDailyDistDetail;
import com.ebiz.mmt.domain.KonkaYjglYjed;
import com.ebiz.mmt.domain.KonkaYwHzTj;
import com.ebiz.mmt.domain.KonkaZles23ResultInfo;
import com.ebiz.mmt.domain.LogOfJob;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PshowOrdeAudit;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.domain.StatisticalDimensionData;
import com.ebiz.mmt.domain.StatisticalDimensionDataMiddle;
import com.ebiz.mmt.domain.StatisticalDimensionDataMonth;
import com.ebiz.mmt.domain.StatisticalDimensionDataMonthMiddle;
import com.ebiz.mmt.domain.SysSetting;
import com.ebiz.mmt.r3.KCXX;
import com.ebiz.mmt.r3.KHXD;
import com.ebiz.mmt.r3.KHYS;
import com.ebiz.mmt.r3.KNA1;
import com.ebiz.mmt.r3.KUKLA;
import com.ebiz.mmt.r3.MARA;
import com.ebiz.mmt.r3.ReturnInfo;
import com.ebiz.mmt.r3.SOXX;
import com.ebiz.mmt.r3.ZLES23;
import com.ebiz.mmt.r3.ZSOF;
import com.ebiz.mmt.r3.ZYJHB;
import com.ebiz.mmt.r3.ZdmtrxCriteria;
import com.ebiz.mmt.r3.Zles29a;
import com.ebiz.mmt.r3.Zles29aCriteria;
import com.ebiz.mmt.r3.helper.DAOFactory;
import com.ebiz.mmt.r3.helper.R3Dao;
import com.ebiz.mmt.r3.helper.R3daoImpl;
import com.ebiz.mmt.service.AutoRunService;
import com.ebiz.mmt.service.Facade;
import com.ebiz.mmt.service.JStocksStoreSummaryService;
import com.ebiz.mmt.service.JStocksSummaryService;
import com.ebiz.mmt.service.KonkaYwHzTjService;
import com.ebiz.mmt.service.ToDrpOrderService;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.ArithUtil;
import com.sf.integration.warehouse.MailnoQuery;
import com.sf.integration.warehouse.Order;
import com.sf.integration.warehouse.OrderFy;
import com.sf.integration.warehouse.SfOrderService;

/**
 * @author XingXiuDong
 * @version 2010.07.06
 *
 * @author zhou
 * @version 20141126
 * @desc 修改事务过长不提交,操作表大量锁的问题
 */
@Service
@SuppressWarnings("unused")
public class AutoRunServiceImpl extends BaseAction implements AutoRunService, ApplicationContextAware, ServletContextAware {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String DEFAULT_HTML_FILE_NAME = "index.html";

    private String indexUrl;

    private String newsUrl;

    private String recieveClientStaticsEmail;

    private String recieveClientStaticsEmailSubject;

    private Facade facade;

    private ServletContext servletContext;

    private JavaMailSenderImpl mailSender;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.facade = (Facade) applicationContext.getBean("facade");
        this.mailSender = (JavaMailSenderImpl) applicationContext.getBean("mailSender");
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public void setIndexUrl(String indexUrl) {
        this.indexUrl = indexUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public void setRecieveClientStaticsEmail(String recieveClientStaticsEmail) {
        this.recieveClientStaticsEmail = recieveClientStaticsEmail;
    }

    public void generateIndexHTML() {

        // Get Server absolute root path
        String ctxPath = servletContext.getRealPath(File.separator);
        if (!ctxPath.endsWith(File.separator)) {
            ctxPath += File.separator;
        }

        // Write to file
        String filePath = ctxPath + DEFAULT_HTML_FILE_NAME;

        writeUrlToHtmlFile(indexUrl, new File(filePath), Constants.SYS_ENCODING);
    }

    public void generateNewsHTML() {
        // Get Server root path
        String ctxPath = servletContext.getRealPath(File.separator);
        if (!ctxPath.endsWith(File.separator)) {
            ctxPath += File.separator;
        }

        // Write to file
        String filePath = ctxPath + "news" + File.separator + DEFAULT_HTML_FILE_NAME;

        writeUrlToHtmlFile(newsUrl, new File(filePath), Constants.SYS_ENCODING);
    }

    public void writeUrlToHtmlFile(String url, File obj, String encoder) {
        HttpClient httpclient = new DefaultHttpClient();

        try {
            HttpGet httpget = new HttpGet(url);

            // logger.info("Request URI: {}", httpget.getURI());

            // Create a response handler to get static html file's source code
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String responseBody = httpclient.execute(httpget, responseHandler);

            // logger.info("Request Body: {}", responseBody);

            // Write to file
            FileUtils.writeStringToFile(obj, responseBody, encoder);

        } catch (ClientProtocolException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public void sendEmailClientStatics() {
        String[] emails = StringUtils.split(recieveClientStaticsEmail, ",");

        Map<String, Object> model = new HashMap<String, Object>();
        Date date = new Date();

        // 注意：时间前有一个空格。
        Long time = date.getTime() - 24 * 60 * 60 * 1000;
        Date yestoday = new Date(time);
        model.put("yestoday", yestoday);
        String date_le = DateFormatUtils.format(yestoday, "yyyy-MM-dd");
        date_le = date_le + " 00:00:00";
        String date_ge = DateFormatUtils.format(yestoday, "yyyy-MM-dd");
        date_ge = date_ge + " 23:59:59";

    }

    public void setRecieveClientStaticsEmailSubject(String recieveClientStaticsEmailSubject) {
        this.recieveClientStaticsEmailSubject = recieveClientStaticsEmailSubject;
    }

    public void deleteDownloadExcelFile() {
        logger.info("start deleteDownloadExcelFile ...");
        String downloadExcelFileLocation = "";
        try {
            String fileLocation = "files\\download_excel\\";
            String ctxDir = this.servletContext.getRealPath(String.valueOf(File.separatorChar));
            if (!ctxDir.endsWith(String.valueOf(File.separatorChar))) {
                ctxDir = ctxDir + File.separatorChar;
            }
            downloadExcelFileLocation = ctxDir + fileLocation;
            FileUtils.deleteDirectory(new File(downloadExcelFileLocation));
            logger.info("has deleted : '{}' all files!", downloadExcelFileLocation);
            logger.info("end deleteDownloadExcelFile...");
        } catch (IOException e) {
            logger.info("do not find downloadExcelFileLocation: {}", downloadExcelFileLocation);
        }
    }

    @Resource
    private KonkaSellReportTmpDao konkaSellReportTmpDao;

    @Resource
    private ChannelDataImportDao channelDataImportDao;

    @Resource
    private KonkaZles23ResultInfoDao konkaZles23ResultInfoDao;

    @Resource
    private KonkaR3ZsofDao konkaR3ZsofDao;

    @Resource
    private KonkaBcompPdDao konkaBcompPdDao;

    @Resource
    ChannelDataImport2Dao channelDataImport2Dao;

    @Resource
    KonkaPriceManagerDao konkaPriceManagerDao;

    @Resource
    private KonkaSellDetailsDao konkaSellDetailsDao;

    @Resource
    private KonkaStockDetailsDao konkaStockDetailsDao;

    @Resource
    private KonkaCategoryDao konkaCategoryDao;

    @Resource
    private PshowOrderDao pshowOrderDao;

    @Resource
    private EcUserDao ecUserDao;

    @Resource
    private BaseProvinceListFourDao baseProvinceListFourDao;

    @Resource
    private EcBaseExpressDao ecBaseExpressDao;

    @Resource
    private PshowOrdeDetailsDao pshowOrdeDetailsDao;
    @Resource
    private KonkaYwHzTjService konkaYwHzTjService;
    @Resource
    JStocksSummaryService jStocksSummaryService;
    @Resource
    KonkaOrderMessageProcessDao konkaOrderMessageProcessDao;
    @Resource
    PeProdUserDao peProdUserDao;

    @Resource
    KonkaJobsDao konkaJobsDao;

    // @Resource
    // private KonkaStockRegulationDao konkaStockRegulationDao;
    @Resource
    JStocksStoreSummaryService jStocksStoreSummaryService;

    protected List<KonkaStockDetails> stockList(KonkaStockDetails _t) {
        List<KonkaStockDetails> list = new ArrayList<KonkaStockDetails>();
        list = this.konkaStockDetailsDao.selectEntityList(_t);
        return list;
    }

    protected List<ChannelDataImport> importList(ChannelDataImport _t) {
        List<ChannelDataImport> list = new ArrayList<ChannelDataImport>();
        list = this.channelDataImportDao.selectChannelDataImportListToSum(_t);
        return list;
    }

    protected List<KonkaSellDetails> exportList(KonkaSellDetails _t) {
        List<KonkaSellDetails> list = new ArrayList<KonkaSellDetails>();
        list = this.konkaSellDetailsDao.selectKonkaSellDetailsToSum(_t);
        return list;
    }

    @Resource
    private KonkaParagonShowshopDetailDao konkaParagonShowshopDetailDao;

    @Resource
    private KonkaXxSellBillDao konkaXxSellBillDao;

    @Resource
    private KonkaXxZmdDao konkaXxZmdDao;

    @Resource
    private KonkaXxZmdDailyStmsDao konkaXxZmdDailyStmsDao;

    @Resource
    private KonkaXxZmdDailyStmsDetailDao konkaXxZmdDailyStmsDetailDao;

    @Resource
    private KonkaDeptDao konkaDeptDao;

    @Resource
    private KonkaXxSellBillDetailsDao konkaXxSellBillDetailsDao;

    @Resource
    private KonkaXxSellBillDetailsDstDao konkaXxSellBillDetailsDstDao;

    @Resource
    private KonkaXxZmdDailyDistDao konkaXxZmdDailyDistDao;

    @Resource
    private KonkaXxZmdDailyDistDetailDao konkaXxZmdDailyDistDetailDao;

    @Override
    public void autoGenerateDailyDistData() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        KonkaDept konkaDept = new KonkaDept();
        konkaDept.setDept_type(3); // 分公司
        // konkaDept.getMap().put("xxqd", 1000000);//新兴渠道
        List<KonkaDept> deptList = this.konkaDeptDao.selectEntityList(konkaDept);
        if (null != deptList && deptList.size() > 0) {
            for (KonkaDept dept : deptList) {
                Date yesterday = DateUtils.addDays(new Date(), -1);

                // 待发货订单 ：财务审核通过，未发货 Begin
                List<KonkaXxZmdDailyDistDetail> konkaXxZmdDailyToBeDistDetailList = new ArrayList<KonkaXxZmdDailyDistDetail>();

                KonkaXxSellBill toBeDistBill = new KonkaXxSellBill();
                toBeDistBill.setDept_id(dept.getDept_id());
                toBeDistBill.setSell_state(20L);// 已审核通过
                toBeDistBill.getMap().put("toBeDist", "true");
                Long toBeBillCount = this.konkaXxSellBillDao.selectEntityCount(toBeDistBill);
                Long today_total_toBeDis_pd_count = 0L; // 待发货产品总量
                List<KonkaXxSellBill> toBeDistList = this.konkaXxSellBillDao.selectEntityList(toBeDistBill);
                if (null != toBeDistList && toBeDistList.size() > 0) {
                    for (KonkaXxSellBill tbdb : toBeDistList) {

                        KonkaXxSellBillDetailsDst dst = new KonkaXxSellBillDetailsDst();
                        dst.setSell_bill_id(tbdb.getSell_bill_id());
                        List<KonkaXxSellBillDetailsDst> dstList = this.konkaXxSellBillDetailsDstDao.selectEntityList(dst);
                        if (null != dstList && dstList.size() > 0) {
                            for (KonkaXxSellBillDetailsDst dd : dstList) {
                                today_total_toBeDis_pd_count += dd.getCounts();

                                KonkaXxZmdDailyDistDetail dailyDistDetail = new KonkaXxZmdDailyDistDetail();
                                dailyDistDetail.setSell_bill_id(dd.getSell_bill_id());
                                dailyDistDetail.setSell_bill_detail_id(dd.getSell_bill_details_id());
                                dailyDistDetail.setFactory_id(dd.getFactory_id());
                                dailyDistDetail.setStore_id(dd.getStore_id());
                                dailyDistDetail.setStore_name(dd.getStore_name());
                                dailyDistDetail.setAdd_bill_user_id(tbdb.getAdd_user_id());
                                dailyDistDetail.setPd_dist_id(dd.getPd_dist_id());
                                dailyDistDetail.setPd_cls_name(dd.getPd_cls_name());

                                KonkaXxSellBillDetails bd = new KonkaXxSellBillDetails(); // 取产品型号
                                bd.setSell_bill_details_id(dd.getSell_bill_details_id());
                                bd = this.konkaXxSellBillDetailsDao.selectEntity(bd);
                                if (null != bd) {
                                    dailyDistDetail.setPd_name(bd.getMd_name());
                                }

                                dailyDistDetail.setCounts(dd.getCounts());
                                dailyDistDetail.setDst_user_id(dd.getDst_user_id());
                                dailyDistDetail.setDst_user_name(dd.getDst_user_name());

                                konkaXxZmdDailyToBeDistDetailList.add(dailyDistDetail);
                            }
                        }

                    }
                }
                KonkaXxZmdDailyDist dailyDist = new KonkaXxZmdDailyDist();
                dailyDist.setDept_id(dept.getDept_id());
                dailyDist.setDist_type(0); // 0-待发货日报
                dailyDist.setDist_title(dept.getDept_desc() + "待发货订单日报(" + df.format(yesterday) + ")"); // 报表标题
                dailyDist.setDist_date(new Date()); // 报表日期
                dailyDist.setToday_total_bill_count(toBeBillCount.intValue());
                dailyDist.setToday_total_pd_count(today_total_toBeDis_pd_count.intValue());

                Long toBeDailyId = this.konkaXxZmdDailyDistDao.insertEntity(dailyDist);

                for (KonkaXxZmdDailyDistDetail kxzddd : konkaXxZmdDailyToBeDistDetailList) {
                    kxzddd.setDist_id(toBeDailyId);
                    this.konkaXxZmdDailyDistDetailDao.insertEntity(kxzddd);
                }
                // 待发货订单 ：财务审核通过，未发货 End

                // 当天已发货订单Begin
                List<KonkaXxZmdDailyDistDetail> konkaXxZmdDailyDistDetailList = new ArrayList<KonkaXxZmdDailyDistDetail>();
                Long today_total_dist_pd_count = 0L; // 已发货产品总量

                KonkaXxSellBill distBill = new KonkaXxSellBill();
                distBill.setDept_id(dept.getDept_id());
                distBill.getMap().put("distSellState", 30); // 已发货订单
                distBill.getMap().put("add_date_between", df.format(yesterday));
                Long billCount = this.konkaXxSellBillDao.selectEntityCount(distBill);
                List<KonkaXxSellBill> distBillList = this.konkaXxSellBillDao.selectEntityList(distBill);
                if (null != distBillList && distBillList.size() > 0) {
                    for (KonkaXxSellBill db : distBillList) {
                        KonkaXxSellBillDetailsDst dst = new KonkaXxSellBillDetailsDst();
                        dst.setSell_bill_id(db.getSell_bill_id());
                        List<KonkaXxSellBillDetailsDst> dstList = this.konkaXxSellBillDetailsDstDao.selectEntityList(dst);
                        if (null != dstList && dstList.size() > 0) {
                            for (KonkaXxSellBillDetailsDst dd : dstList) {
                                today_total_dist_pd_count += dd.getCounts();

                                KonkaXxZmdDailyDistDetail dailyDistDetail = new KonkaXxZmdDailyDistDetail();
                                dailyDistDetail.setSell_bill_id(dd.getSell_bill_id());
                                dailyDistDetail.setSell_bill_detail_id(dd.getSell_bill_details_id());
                                dailyDistDetail.setFactory_id(dd.getFactory_id());
                                dailyDistDetail.setStore_id(dd.getStore_id());
                                dailyDistDetail.setStore_name(dd.getStore_name());
                                dailyDistDetail.setAdd_bill_user_id(db.getAdd_user_id());
                                dailyDistDetail.setPd_dist_id(dd.getPd_dist_id());
                                dailyDistDetail.setPd_cls_name(dd.getPd_cls_name());

                                KonkaXxSellBillDetails bd = new KonkaXxSellBillDetails(); // 取产品型号
                                bd.setSell_bill_details_id(dd.getSell_bill_details_id());
                                bd = this.konkaXxSellBillDetailsDao.selectEntity(bd);
                                if (null != bd) {
                                    dailyDistDetail.setPd_name(bd.getMd_name());
                                }

                                dailyDistDetail.setCounts(dd.getCounts());
                                dailyDistDetail.setDst_user_id(dd.getDst_user_id());
                                dailyDistDetail.setDst_user_name(dd.getDst_user_name());

                                konkaXxZmdDailyDistDetailList.add(dailyDistDetail);
                            }
                        }
                    }
                }

                KonkaXxZmdDailyDist kxzdd = new KonkaXxZmdDailyDist();
                kxzdd.setDept_id(dept.getDept_id());
                kxzdd.setDist_type(1); // 0-已发货日报
                kxzdd.setDist_title(dept.getDept_desc() + "已发货订单日报(" + df.format(yesterday) + ")"); // 报表标题
                kxzdd.setDist_date(new Date()); // 报表日期
                kxzdd.setToday_total_bill_count(billCount.intValue());
                kxzdd.setToday_total_pd_count(today_total_dist_pd_count.intValue());

                Long dailyId = this.konkaXxZmdDailyDistDao.insertEntity(kxzdd);

                for (KonkaXxZmdDailyDistDetail kxzddd : konkaXxZmdDailyDistDetailList) {
                    kxzddd.setDist_id(dailyId);
                    this.konkaXxZmdDailyDistDetailDao.insertEntity(kxzddd);
                }
                // 当天已发货订单End
            }
        }

    }

    @Resource
    private KonkaR3ShopDao konkaR3ShopDao;

    @Resource
    private KonkaSalesDeptDao konkaSalesDeptDao;

    @Resource
    private KonkaSalesDept2Dao konkaSalesDept2Dao;

    @Resource
    private EcOrderExpressInfoDao ecOrderExpressInfoDao;

    /**
     * 定时同步R3客户
     */
    @Override
    public void createGenerateKonkaR3Shop() {
        if(checkStatus("createGenerateKonkaR3Shop")) {
            // 添加记录日志
            LogOfJob log = new LogOfJob();
            Date start_time = new Date();
            int m;
            int n;
            try {
                String str_date = "1970-01-01";
                // 销售组织
                KonkaSalesDept ksd = new KonkaSalesDept();
                // konkaDept.setDept_type(3);
                // ksd.setSales_org_code("F288");
                ksd.setIs_del(0);
                List<KonkaSalesDept> ksdList = this.konkaSalesDeptDao.selectEntityList(ksd);
                m = 0;
                n = 0;
                if (ksdList != null && ksdList.size() > 0) {
                    // 取分公司
                    for (KonkaSalesDept temp : ksdList) {

                        String area_name = "";// 大区
                        Long p_index = null;// 所在地区
                        Integer p_area = null;// 地区编码
                        String dept_name = "";// 分公司名称

                        KonkaDept kDept = new KonkaDept();// 取分公司

                        KonkaDept kDept1 = new KonkaDept();
                        kDept1.setDept_sn(temp.getP_sales_org_code());
                        if (this.konkaDeptDao.selectEntityList(kDept1).size() > 0) {
                            kDept = this.konkaDeptDao.selectEntityList(kDept1).get(0);
                        }

                        if (null != kDept) {
                            if (null != kDept.getP_index()) {
                                p_index = kDept.getP_index();
                            }

                            if (null != kDept.getP_area()) {
                                p_area = kDept.getP_area();
                            }

                            if (null != kDept.getDept_name()) {
                                dept_name = kDept.getDept_name();
                            }

                            if (kDept.getP_area() != null) {
                                switch (kDept.getP_area()) {
                                    case 10:
                                        area_name = "华东";
                                        break;
                                    case 20:
                                        area_name = "山东";
                                        break;
                                    case 30:
                                        area_name = "东北";
                                        break;
                                    case 40:
                                        area_name = "华北";
                                        break;
                                    case 50:
                                        area_name = "华南";
                                        break;
                                    case 60:
                                        area_name = "西南";
                                        break;
                                    case 70:
                                        area_name = "华中";
                                        break;
                                    case 80:
                                        area_name = "西北";
                                        break;
                                }
                            }
                        }

                        SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
                        List<KNA1> kak1List = new R3daoImpl().getCustomerList(str_date, temp.getSales_org_code(), null);
                        m++;
                        if (kak1List.size() > 0) {
                            for (KNA1 kna1 : kak1List) {
                                KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
                                konkaR3Shop.setR3_code(kna1.getKUNNR());
                                List<KonkaR3Shop> konkaR3ShopList = this.konkaR3ShopDao.selectEntityList(konkaR3Shop);

                                // update by zhou 2013/09/26
                                konkaR3Shop.setImport_date(new Date());
                                // konkaR3Shop.setCustomer_code(kna1.getKUNNR());//不更新合并编码
                                konkaR3Shop.setCustomer_name(kna1.getNAME1());
                                konkaR3Shop.setImport_user_id(1L);
                                konkaR3Shop.setR3_sales_org_code(temp.getSales_org_code());
                                konkaR3Shop.setCustomer_type(kna1.getKUKLA());
                                if (konkaR3ShopList.size() < 1) {
                                    if (p_area != null) {
                                        konkaR3Shop.setArea_code(Long.valueOf(p_area));
                                    }
                                    konkaR3Shop.setCustomer_code(kna1.getKUNNR());
                                    konkaR3Shop.setArea_name(area_name);
                                    konkaR3Shop.setBranch_area_code(p_index);
                                    konkaR3Shop.setBranch_area_name(dept_name);
                                    konkaR3Shop.setBranch_area_name_2(temp.getP_sales_org_code());// 分公司编码

                                    if (null != kna1.getERDAT()) {
                                        try {
                                            konkaR3Shop.setAdd_date(sFormat.parse(kna1.getERDAT()));
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    konkaR3Shop.setHandle_name(temp.getSales_org_name());
                                    konkaR3Shop.setIs_del(0L);
                                    if (kna1.getIS_AG()) {
                                        konkaR3Shop.setIs_sdf(0);
                                    } else {
                                        konkaR3Shop.setIs_sdf(1);
                                    }
                                    konkaR3Shop.setIs_minus_sales(0);
                                    // r3删除与冻结标志
                                    konkaR3Shop.setIs_loevm(Long.valueOf(kna1.getLOEVM()));
                                    konkaR3Shop.setIs_cassd(Long.valueOf(kna1.getCASSD()));

                                    // 设置默认客户状态为新客户
                                    konkaR3Shop.setShop_status("1");
                                    // insert
                                    this.konkaR3ShopDao.insertEntity(konkaR3Shop);
                                } else {
                                    KonkaR3Shop konkaR3Shop1 = konkaR3ShopList.get(0);

                                    if (konkaR3Shop1.getArea_code() == null || "".equals(String.valueOf(konkaR3Shop1.getArea_code()))) {
                                        if (p_area != null && !"".equals(String.valueOf(p_area))) {
                                            konkaR3Shop.setArea_code(Long.valueOf(p_area));
                                        }
                                    }
                                    if (konkaR3Shop1.getArea_name() == null || "".equals(konkaR3Shop1.getArea_name())) {
                                        konkaR3Shop.setArea_name(area_name);
                                    }
                                    if (konkaR3Shop1.getBranch_area_code() == null || "".equals(String.valueOf(konkaR3Shop1.getBranch_area_code()))) {
                                        konkaR3Shop.setBranch_area_code(p_index);
                                    }
                                    if (konkaR3Shop1.getBranch_area_name() == null || "".equals(konkaR3Shop1.getBranch_area_name())) {
                                        konkaR3Shop.setBranch_area_name(dept_name);
                                    }
                                    if (konkaR3Shop1.getBranch_area_name_2() == null || "".equals(konkaR3Shop1.getBranch_area_name_2())) {
                                        konkaR3Shop.setBranch_area_name_2(temp.getP_sales_org_code());// 分公司编码
                                    }

                                    if (konkaR3Shop1.getHandle_name() == null || "".equals(konkaR3Shop1.getHandle_name())) {
                                        konkaR3Shop.setHandle_name(temp.getSales_org_name());
                                    }

                                    // 是ag,售达方
                                    if (kna1.getIS_AG()) {
                                        konkaR3Shop.setIs_sdf(0);
                                    } else {
                                        konkaR3Shop.setIs_sdf(1);
                                    }

                                    if (null != kna1.getERDAT()) {
                                        try {
                                            konkaR3Shop.setAdd_date(sFormat.parse(kna1.getERDAT()));
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    konkaR3Shop.setId(konkaR3Shop1.getId());
                                    // r3删除与冻结标志
                                    konkaR3Shop.setIs_loevm(Long.valueOf(kna1.getLOEVM()));
                                    konkaR3Shop.setIs_cassd(Long.valueOf(kna1.getCASSD()));
                                    // update
                                    // 不更新合并编码
                                    // konkaR3Shop.setCustomer_code(null);
                                    this.konkaR3ShopDao.updateEntity(konkaR3Shop);
                                }
                                n++;
                            }
                        }
                    }
                }
                log.setJob_status("成功");
                log.setComments("本次查询销售组织数量为：" + m + "，插入或修改R3客户数为：" + n);
            } catch (Exception e) {
                log.setJob_status("失败");
                log.setComments(e.getMessage());
                e.printStackTrace();
            }
            Date end_time = new Date();
            log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
            log.setJob_type("同步R3客户");
            log.setStart_time(start_time);
            log.setEnd_time(end_time);
            log.setLink_table("KONKA_R3_SHOP");
            log.setOper_user_id("00000");
            log.setOper_real_name("系统");
            log.setOper_user_ip("127.0.0.1");
            log.setOper_fun("createGenerateKonkaR3Shop");
            this.facade.getLogOfJobService().createLogOfJob(log);
        }
    }

    @Resource
    private PePdModelDao pePdModelDao;

    @Resource
    private SysSettingDao sysSettingDao;


    /**
     * 定时同步产品库
     *
     * @author zhou 只作新增,不做修改
     */
    @Override
    public void createGeneratePePdModel() {
        // 添加记录日志
        LogOfJob log = new LogOfJob();
        Date start_time = new Date();

        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.MONTH, -1);
        String day_first = df.format(calendar.getTime());

        int m;
        int n;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sformat = new SimpleDateFormat("yyyyMMddHHmmss");
            // 更新本月数据.看sap是否有新的产品上线
            String erdat = day_first;
            List<MARA> list = new ArrayList<MARA>();
            list = new R3daoImpl().getMaraList(erdat);
            List<String> kktvs = getkktvs();
            Object[] kktv = kktvs.toArray();
            m = 0;
            n = 0;

            if (list.size() > 0) {
                for (MARA temp : list) {
                    m++;
                    if (null != temp.getMATNR() && !"".equals(temp.getMATNR())) {
                        PePdModel entity = new PePdModel();
                        entity.setMd_name(StringUtils.trim(temp.getMATNR()));
                        List<PePdModel> entityList = this.pePdModelDao.selectEntityList(entity);
                        if (null != temp.getMakt()) {
                            if (null != temp.getMakt().getMAKTG()) {
                                entity.setPd_desc(temp.getMakt().getMAKTG());
                            }
                        }
                        if (null != temp.getMTART()) {
                            entity.setMat_type(temp.getMTART());
                        }
                        if (null != temp.getMATKL()) {
                            entity.setMat_group(temp.getMATKL());
                        }
                        if (null != temp.getSPART()) {
                            entity.setCp_group(temp.getSPART());
                        }
                        if (null != temp.getMEINS()) {
                            entity.setMd_dw(temp.getMEINS());
                        }

                        entity.setIs_sell(1);
                        entity.setOrder_value(0);
                        entity.setCls_id(1010100L);
                        entity.setPar_cls_id(1010000L);
                        entity.setAudit_state(1);
                        /**
                         * 114:康佳(规则未知)<br>
                         * 115:现代(产口组为28)子品牌<br>
                         * 116:KKTV(子品牌)
                         */
                        // 默认114康佳产品
                        entity.setBrand_id(114L);
                        // 现代
                        if (temp.getSPART().equals("28")) {
                            entity.setSub_brand_id(115L);
                        }
                        // kktv
                        if (org.apache.commons.lang.ArrayUtils.contains(kktv, temp.getMATNR().toUpperCase())) {
                            entity.setSub_brand_id(116L);
                        }

                        try {
                            if (StringUtils.isNotBlank(temp.getERSDA())) entity.setAdd_date(sdf.parse(temp.getERSDA()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String md_name = StringUtils.trim((temp.getMATNR())).toUpperCase();
                        if (StringUtils.startsWith(md_name, "LED")) {
                            String s = StringUtils.substring(md_name, 3, 5);
                            if (GenericValidator.isInt(s)) {
                                Integer size = Integer.valueOf(s);
                                if (GenericValidator.isInRange(size, 0, 26)) {
                                    entity.setSize_sec(1);
                                } else if (size == 32) {
                                    entity.setSize_sec(2);
                                } else if (size <= 39 && size >= 37) {
                                    entity.setSize_sec(3);
                                } else if (size <= 43 && size >= 40) {
                                    entity.setSize_sec(4);
                                } else if (size <= 50 && size >= 46) {
                                    entity.setSize_sec(5);
                                } else if (size >= 55) {
                                    entity.setSize_sec(6);
                                } else {
                                    entity.setSize_sec(-1);
                                }
                                entity.setMd_size(String.valueOf(size));

                            } else {
                                entity.setMd_size("0");
                                entity.setSize_sec(-1);
                            }
                        } else {
                            entity.setMd_size("0");
                            entity.setSize_sec(-1);
                        }

                        if (entityList.size() == 0) {
                            entity.setIs_del(0);
                            entity.setLast_edit_date(new Date());
                            this.pePdModelDao.insertEntity(entity);
                            n++;
                        }

                        SysSetting ss = new SysSetting();
                        ss.setTitle("datapatch");
                        ss.setContent(sformat.format(new Date()));
                        this.sysSettingDao.updateEntity(ss);
                    }
                }
            }
            log.setJob_status("成功");
            log.setComments("本次查询到产品数为：" + m + "，插入产品数为：" + n);
        } catch (Exception e) {
            log.setJob_status("失败");
            log.setComments(e.getMessage());
            e.printStackTrace();
        }
        Date end_time = new Date();
        log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
        log.setJob_type("同步产品库");
        log.setStart_time(start_time);
        log.setEnd_time(end_time);
        log.setLink_table("KONKA_PE_PD_MODEL");
        log.setOper_user_id("00000");
        log.setOper_real_name("系统");
        log.setOper_user_ip("127.0.0.1");
        log.setOper_fun("createGeneratePePdModel");
        this.facade.getLogOfJobService().createLogOfJob(log);
    }

    private static List<String> getkktvs() {

        List<String> kktvlist = new ArrayList<String>(20);
        InputStream ins = AutoRunServiceImpl.class.getResourceAsStream("/kktv.properties");
        Properties p = new Properties();
        try {
            p.load(ins);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Object key : p.keySet()) {
            kktvlist.add(p.getProperty((String) key).trim().toUpperCase());
        }
        return kktvlist;
    }

    @Resource
    private KonkaR3BackmoneyDao konkaR3BackmoneyDao;

    /**
     * 定时同步回款
     */
    @Override
    public void createKonkaR3BackMoney() {
        if(checkStatus("createKonkaR3BackMoney")) {
            // 添加记录日志
            LogOfJob log = new LogOfJob();
            Date start_time = new Date();
            int m;
            int n;
            try {
                String v_gjahr = "";// 年
                String v_monat = "";// 月
                SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy");
                SimpleDateFormat fmt2 = new SimpleDateFormat("MM");

                v_gjahr = fmt1.format(new Date());
                v_monat = fmt2.format(new Date());
                String[] month_value = {("" + (Integer.valueOf(v_monat) - 1)), v_monat};

                // 销售组织
                KonkaSalesDept ksd = new KonkaSalesDept();
                ksd.setIs_del(0);
                List<KonkaSalesDept> ksdList = this.konkaSalesDeptDao.selectEntityList(ksd);

                List<KonkaR3Backmoney> entityList = new ArrayList<KonkaR3Backmoney>();

                m = 0;
                n = 0;

                for (int i = 0; i < month_value.length; i++) {
                    for (KonkaSalesDept temp : ksdList) {
                        v_monat = month_value[i];
                        // 经办
                        KonkaDept kDept1 = new KonkaDept();
                        kDept1.setDept_sn(temp.getSales_org_code());
                        List<KonkaDept> kDeptList1 = this.konkaDeptDao.selectEntityList(kDept1);
                        if (kDeptList1.size() > 0) {
                            kDept1 = kDeptList1.get(0);
                        }
                        // 查询分公司
                        KonkaDept kDept = new KonkaDept();
                        kDept.setDept_sn(temp.getP_sales_org_code());
                        List<KonkaDept> kDeptList = this.konkaDeptDao.selectEntityList(kDept);

                        if (kDeptList.size() > 0) {
                            kDept = kDeptList.get(0);
                        }
                        // List<KHYS> khysList = new R3daoImpl().getKhys(v_gjahr,
                        // month_value[i], "10", temp.getSales_org_code(), null);

                        if (null != temp || null != temp.getSales_org_code()) {
                            // continue;
                        }
                        ReturnInfo<KHYS> info = new ReturnInfo<KHYS>();
                        List<KHYS> khysList = new ArrayList<KHYS>();

                        info = this.facade.getR3WebInterfaceService().getKhys(v_gjahr, v_monat, "10", temp.getSales_org_code(), null);

                        if (info.getSuccess() == true) {
                            khysList = info.getDataResult();
                        }

                        if (khysList.size() > 0) {
                            for (KHYS khys : khysList) {
                                m++;
                                KonkaR3Backmoney konkaR3Backmoney = new KonkaR3Backmoney();
                                konkaR3Backmoney.setYear(Long.valueOf(v_gjahr));// 年份
                                if (kDept.getDept_name() != null && !"".equals(kDept.getDept_name())) {
                                    konkaR3Backmoney.setBranch_area_name(kDept.getDept_name());// 分公司名称
                                }
                                if (kDept.getDept_id() != null) {
                                    konkaR3Backmoney.setBranch_area_code(kDept.getDept_id());
                                }
                                if (kDept1.getDept_name() != null) {
                                    konkaR3Backmoney.setHandle_name(kDept1.getDept_name());
                                }

                                String area_name = null;
                                if (kDept.getP_area() != null) {
                                    switch (kDept.getP_area()) {
                                        case 10:
                                            area_name = "华东";
                                            break;
                                        case 20:
                                            area_name = "山东";
                                            break;
                                        case 30:
                                            area_name = "东北";
                                            break;
                                        case 40:
                                            area_name = "华北";
                                            break;
                                        case 50:
                                            area_name = "华南";
                                            break;
                                        case 60:
                                            area_name = "西南";
                                            break;
                                        case 70:
                                            area_name = "华中";
                                            break;
                                        case 80:
                                            area_name = "西北";
                                            break;
                                    }
                                    konkaR3Backmoney.setArea_code(Long.valueOf(kDept.getP_area()));
                                }
                                konkaR3Backmoney.setArea_name(area_name);// 大区
                                konkaR3Backmoney.setCustomer_type(khys.getKUKLA());// 客户类型
                                konkaR3Backmoney.setR3_code(khys.getKUNNR());// R3客户编号
                                konkaR3Backmoney.setCustomer_name(khys.getNAME1());// 客户名称
                                konkaR3Backmoney.setAdd_uid(0L);
                                konkaR3Backmoney.setAdd_date(new Date());// add时间

                                Double zdf = 0.00;
                                if (null != khys.getZDF() && !"".equals(khys.getZDF().toString())) {
                                    zdf = zdf - Double.valueOf(khys.getZDF().toString()) / 10000;
                                }

                                zdf = ArithUtil.round(zdf, 2);

                                switch (Integer.valueOf(month_value[i])) {
                                    case 1:
                                        konkaR3Backmoney.setColumn_1(zdf);
                                        break;
                                    case 2:
                                        konkaR3Backmoney.setColumn_2(zdf);
                                        break;
                                    case 3:
                                        konkaR3Backmoney.setColumn_3(zdf);
                                        break;
                                    case 4:
                                        konkaR3Backmoney.setColumn_4(zdf);
                                        break;
                                    case 5:
                                        konkaR3Backmoney.setColumn_5(zdf);
                                        break;
                                    case 6:
                                        konkaR3Backmoney.setColumn_6(zdf);
                                        break;
                                    case 7:
                                        konkaR3Backmoney.setColumn_7(zdf);
                                        break;
                                    case 8:
                                        konkaR3Backmoney.setColumn_8(zdf);
                                        break;
                                    case 9:
                                        konkaR3Backmoney.setColumn_9(zdf);
                                        break;
                                    case 10:
                                        konkaR3Backmoney.setColumn_10(zdf);
                                        break;
                                    case 11:
                                        konkaR3Backmoney.setColumn_11(zdf);
                                        break;
                                    case 12:
                                        konkaR3Backmoney.setColumn_12(zdf);
                                        break;
                                }
                                entityList.add(konkaR3Backmoney);
                            }
                        }
                    }
                }

                if (entityList.size() > 0) {
                    for (KonkaR3Backmoney temp : entityList) {
                        KonkaR3Backmoney entity = new KonkaR3Backmoney();
                        entity.setYear(temp.getYear());
                        entity.setR3_code(temp.getR3_code());
                        List<KonkaR3Backmoney> entityList1 = this.konkaR3BackmoneyDao.selectKonkaR3BackmoneyListForTb(entity);
                        if (entityList1.size() > 0) {// 更新
                            temp.setId(entityList1.get(0).getId());
                            this.konkaR3BackmoneyDao.updateEntity(temp);
                        } else {
                            this.konkaR3BackmoneyDao.insertEntity(temp);// 新增
                        }
                        n++;
                    }
                }
                log.setJob_status("成功");
                log.setComments("本次查询到回款数量为：" + m + "插入或更新的回款数量为：" + n);
            } catch (Exception e) {
                log.setJob_status("失败");
                log.setComments(e.getMessage());
                e.printStackTrace();
            }
            Date end_time = new Date();
            log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
            log.setJob_type("同步回款");
            log.setStart_time(start_time);
            log.setEnd_time(end_time);
            log.setLink_table("KONKA_R3_BACKMONEY");
            log.setOper_user_id("00000");
            log.setOper_real_name("系统");
            log.setOper_user_ip("127.0.0.1");
            log.setOper_fun("createKonkaR3BackMoney");
            this.facade.getLogOfJobService().createLogOfJob(log);
        }
    }

    @Resource
    private KonkaR3ShopCreditDao konkaR3ShopCreditDao;

    /**
     * khxd 定时同步账期(信用剩余额度等信息)
     */
    @Override
    public void createKonkaR3ShopCredit() {
        if(checkStatus("createKonkaR3ShopCredit")) {
            // 添加记录日志
            LogOfJob log = new LogOfJob();
            Date start_time = new Date();
            int m;
            int n;
            try {
                String v_kkber = "KF01";
                String v_spart = "10";

                // 销售组织
                KonkaSalesDept ksd = new KonkaSalesDept();
                ksd.setIs_del(0);
                List<KonkaSalesDept> ksdList = this.konkaSalesDeptDao.selectEntityList(ksd);

                List<KonkaR3ShopCredit> entityList = new ArrayList<KonkaR3ShopCredit>();
                m = 0;
                n = 0;

                if (ksdList.size() > 0) {
                    for (KonkaSalesDept temp : ksdList) {

                        List<KHXD> khxdList = new R3daoImpl().getKhxd(v_kkber, temp.getSales_org_code(), v_spart, null);

                        if (khxdList.size() > 0) {
                            for (KHXD temp1 : khxdList) {
                                KonkaR3ShopCredit konkaR3ShopCredit = new KonkaR3ShopCredit();
                                konkaR3ShopCredit.setKunnr(temp1.getKUNNR());
                                konkaR3ShopCredit.setCtlpc(temp1.getCTLPC());
                                konkaR3ShopCredit.setDbekr(temp1.getDBEKR());
                                konkaR3ShopCredit.setKlimk(temp1.getKLIMK());
                                konkaR3ShopCredit.setKlprz(temp1.getKLPRZ());
                                konkaR3ShopCredit.setOblig(temp1.getOBLIG());
                                konkaR3ShopCredit.setSauft(temp1.getSAUFT());
                                konkaR3ShopCredit.setSkfor(temp1.getSKFOR());
                                konkaR3ShopCredit.setVkorg(temp1.getVKORG());
                                konkaR3ShopCredit.setZlimt(temp1.getZLIMT());
                                konkaR3ShopCredit.setZsyed(temp1.getZSYED());
                                konkaR3ShopCredit.setUmsav(temp1.getUMSAV());
                                konkaR3ShopCredit.setKlime(temp1.getKLIME());
                                konkaR3ShopCredit.setKlimg(temp1.getKLIMG());
                                konkaR3ShopCredit.setSyed(temp1.getSYED());
                                entityList.add(konkaR3ShopCredit);
                                m++;
                            }
                        }
                    }
                }
                for (KonkaR3ShopCredit temp : entityList) {
                    if (StringUtils.isNotBlank(temp.getKunnr())) {
                        KonkaR3ShopCredit konkaR3ShopCredit = new KonkaR3ShopCredit();
                        konkaR3ShopCredit.setKunnr(temp.getKunnr());
                        Long count = this.konkaR3ShopCreditDao.selectEntityCount(konkaR3ShopCredit);

                        if (count == 0) {// 不存在客户
                            temp.setLast_update_time(new Date());
                            this.konkaR3ShopCreditDao.insertEntity(temp);
                        } else {
                            temp.setLast_update_time(new Date());
                            this.konkaR3ShopCreditDao.updateEntity(temp);
                        }
                        n++;
                    }
                }
                log.setJob_status("成功");
                log.setComments("本次查询到账期数量为：" + m + "插入或更新的账期数量为：" + n);
            } catch (Exception e) {
                log.setJob_status("失败");
                log.setComments(e.getMessage());
                e.printStackTrace();
            }
            Date end_time = new Date();
            log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
            log.setJob_type("同步回款");
            log.setStart_time(start_time);
            log.setEnd_time(end_time);
            log.setLink_table("KONKA_R3_SHOP_CREDIT");
            log.setOper_user_id("00000");
            log.setOper_real_name("系统");
            log.setOper_user_ip("127.0.0.1");
            log.setOper_fun("createKonkaR3ShopCredit");
            this.facade.getLogOfJobService().createLogOfJob(log);
        }
    }

    @Resource
    private KonkaDeptJsMoneyDao konkaDeptJsMoneyDao;


    /**
     * 集采数据
     *
     *
     *
     * 目前的sap接口是按照转储单的创建日期来同步,会存在一部分没有交货的转储单
     *
     * 没有交货的转储单不能作为客户的实际进货数
     *
     * 所以,需要想办法把这部分没有交货的转储单修复完整
     *
     * @author zhou 2015/01/21
     *
     */
    @Override
    public void autoSyncKonkaZles23ResultInfo() throws SQLException {
        if(checkStatus("autoSyncKonkaZles23ResultInfo")){
            // 添加记录日志
            LogOfJob log = new LogOfJob();
            Date start_time = new Date();
            try {
                // 计算当前日期往前推1天的时间
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                // 为了让集采的时间是08:00:00
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Calendar c = Calendar.getInstance();
                c.add(Calendar.DATE, -1);
                Date date = c.getTime();
                // 同步开始时间
                String date_s = sdf.format(date);
                // 同步截止时间
                String date_e = sdf.format(new Date());
                // 转储单编码
                String ebeln = "";

                List<ZLES23> dataList = new ArrayList<ZLES23>();
                dataList = new R3daoImpl().getZles23(date_s, date_e, null);
                //
                List<KonkaZles23ResultInfo> zlList = new ArrayList<KonkaZles23ResultInfo>();

                for (ZLES23 zles : dataList) {
                    KonkaZles23ResultInfo konkazles23 = new KonkaZles23ResultInfo();

                    if (!(zles.getBEDNR().equals(""))) {
                        konkazles23.setBednr(zles.getBEDNR());
                    } else {
                        konkazles23.setBednr(null);
                    }

                    /*
                     * 旧的代码 try { if (!(zles.getERDAT().equals(""))) {
                     * konkazles23.setErdat(sdf.parse(zles.getERDAT())); } else {
                     * konkazles23.setErdat(null); } } catch (ParseException e) { e.printStackTrace(); }
                     */
                    // 新的代码，让集采的时间变成08:00:00 update by wangkl 2015-04-02
                    try {
                        {
                            if (!(zles.getERDAT().equals(""))) {
                                if (zles.getERDAT().length() > 10) {
                                    konkazles23.setErdat(sdf1.parse(zles.getERDAT().substring(0, 10) + " 08:00:00"));
                                } else if (zles.getERDAT().length() == 10) {
                                    konkazles23.setErdat(sdf1.parse(zles.getERDAT() + " 08:00:00"));
                                }
                            } else {
                                konkazles23.setErdat(null);
                            }
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    try {
                        if (!(zles.getBEDAT().equals(""))) {
                            konkazles23.setBedat(sdf.parse(zles.getBEDAT()));
                        } else {
                            konkazles23.setBedat(null);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (!(zles.getBUDAT1().equals(""))) {
                            konkazles23.setBudat1(sdf.parse(zles.getBUDAT1()));
                        } else {
                            konkazles23.setBudat1(null);
                        }
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }

                    try {
                        if (!(zles.getBUDAT2().equals(""))) {
                            konkazles23.setBudat2(sdf.parse(zles.getBUDAT2()));
                        } else {
                            konkazles23.setBudat2(null);
                        }
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }

                    // 没意义,不给值
                    konkazles23.setDatbg(null);
                    konkazles23.setDaten(null);

                    konkazles23.setEbeln(zles.getEBELN());
                    konkazles23.setEbelp(Long.valueOf(zles.getEBELP()));
                    konkazles23.setFahztd(new BigDecimal(zles.getFAHZTD()));
                    konkazles23.setKunnr(zles.getKUNNR());// 客户编码-送达方
                    konkazles23.setLabst(new BigDecimal(zles.getLABST()));
                    konkazles23.setLabst1(new BigDecimal(zles.getLABST1()));
                    konkazles23.setLfimg(new BigDecimal(zles.getLFIMG()));
                    konkazles23.setLfimg1(new BigDecimal(zles.getLFIMG1()));
                    konkazles23.setLgort(zles.getLGORT());
                    konkazles23.setMatnr(zles.getMATNR());
                    konkazles23.setMenge(new BigDecimal(zles.getMENGE()));
                    konkazles23.setMenge1(new BigDecimal(zles.getMENGE1()));
                    konkazles23.setMenge2(new BigDecimal(zles.getMENGE2()));
                    konkazles23.setName1(zles.getNAME1());
                    konkazles23.setPosnr(zles.getPOSNR());// 项目号
                    konkazles23.setReslo(zles.getRESLO());
                    konkazles23.setSigni(zles.getSIGNI());
                    konkazles23.setTknum(zles.getTKNUM());
                    konkazles23.setTpbez(zles.getTPBEZ());
                    konkazles23.setVbeln(zles.getVBELN());
                    konkazles23.setWamng(new BigDecimal(zles.getWAMNG()));
                    konkazles23.setWemng(new BigDecimal(zles.getWEMNG()));

                    zlList.add(konkazles23);

                }

                // 按日期删除数据,防止一天同步多次,导致数据重复
                KonkaZles23ResultInfo ki = new KonkaZles23ResultInfo();
                ki.getMap().put("bedat_s", date_s + " " + "00:00:00");
                ki.getMap().put("bedat_e", date_e + " " + "23:59:59");
                if (ebeln != null && !"".equals(ebeln)) {
                    ki.setEbeln(ebeln);
                }
                konkaZles23ResultInfoDao.deleteEntity(ki);

                for (KonkaZles23ResultInfo kzles : zlList) {
                    konkaZles23ResultInfoDao.insertEntity(kzles);
                    // 初始化商品库
                    if (StringUtils.isNotEmpty(kzles.getKunnr())) {// 如果客户编码-送达方不为空
                        KonkaPartership ship = new KonkaPartership();
                        ship.setSongdf_id(kzles.getKunnr());
                        List<KonkaPartership> shipList = this.facade.getKonkaPartershipService().getKonkaPartershipList(ship);
                        if (null != shipList && shipList.size() > 0) {
                            KonkaR3Shop r3Shop = new KonkaR3Shop();
                            r3Shop.setR3_code(shipList.get(0).getShoudf_id());
                            r3Shop = this.konkaR3ShopDao.selectKonkaR3ShopByR3Code(r3Shop);
                            if (null != r3Shop) {
                                this.initOneGoodsOfCust(r3Shop.getId(), kzles.getMatnr(), shipList.get(0).getSongdf_id(), shipList.get(0).getShoudf_id(), kzles.getPrice());
                            }

                        }
                    }

                }

                //
                List<ZLES23> UdataList = new ArrayList<ZLES23>();
                // 更新有转储单号但交货不完整的数据,这部分数据已经保存
                List<KonkaZles23ResultInfo> ulist = new ArrayList<KonkaZles23ResultInfo>();
                ulist = this.facade.getKonkaZles23ResultInfoService().getKonkaZles23ResultInfoWithEbelnList();
                if (ulist != null && ulist.size() > 0) {
                    for (KonkaZles23ResultInfo u : ulist) {
                        if (u.getEbeln() != null && !"".equals(u.getEbeln())) {
                            // sap接口,如果使用了转储单号,其它条件直接忽略
                            UdataList = new R3daoImpl().getZles23(date_s, date_e, u.getEbeln());
                            if (UdataList != null && UdataList.size() > 0) {
                                for (ZLES23 z : UdataList) {
                                    // 是否需要判断客户,暂时未知
                                    if (u.getEbeln().equals(z.getEBELN()) && u.getEbelp().equals(Long.valueOf(z.getEBELP())) && u.getMatnr().equals(z.getMATNR())) {
                                        KonkaZles23ResultInfo ux = u;
                                        ux.setId(u.getId());
                                        ux.setKunnr(z.getKUNNR());

                                        ux.setWamng(new BigDecimal(z.getWAMNG()));
                                        ux.setWemng(new BigDecimal(z.getWEMNG()));
                                        ux.setLfimg(new BigDecimal(z.getLFIMG()));
                                        ux.setLabst(new BigDecimal(z.getLABST()));
                                        ux.setLabst1(new BigDecimal(z.getLABST1()));

                                        ux.setLgort(z.getLGORT());

                                        ux.setBednr(z.getBEDNR());
                                        ux.setVbeln(z.getVBELN());
                                        ux.setPosnr(z.getPOSNR());
                                        try {
                                            // if (!z.getERDAT().equals("")) {
                                            // ux.setErdat(sdf1.parse(z.getERDAT() + " 08:00:00"));
                                            // }
                                            if (!z.getERDAT().equals("")) {
                                                if (z.getERDAT().length() > 10) {
                                                    ux.setErdat(sdf1.parse(z.getERDAT().substring(10) + " 08:00:00"));
                                                } else if (z.getERDAT().length() == 10) {
                                                    ux.setErdat(sdf1.parse(z.getERDAT() + " 08:00:00"));
                                                }
                                            }

                                        } catch (ParseException e) {

                                        }

                                        try {
                                            if (!z.getBUDAT1().equals("")) {
                                                ux.setBudat1(sdf.parse(z.getBUDAT1()));
                                            }
                                        } catch (ParseException e) {

                                        }
                                        try {
                                            if (!z.getBUDAT2().equals("")) {
                                                ux.setBudat2(sdf.parse(z.getBUDAT2()));
                                            }
                                        } catch (ParseException e) {

                                        }
                                        ux.setLfimg1(new BigDecimal(z.getLFIMG1()));
                                        ux.setMenge2(new BigDecimal(z.getMENGE2()));
                                        // update
                                        konkaZles23ResultInfoDao.updateEntity(ux);

                                    }

                                }
                            }
                        }
                    }
                }
                // 更新有转储单号但交货不完整的数据 end
                log.setJob_status("成功");
            } catch (Exception e) {
                log.setJob_status("失败");
                log.setComments(e.getMessage());
                e.printStackTrace();
            }
            Date end_time = new Date();
            log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
            log.setJob_type("同步集采数据");
            log.setStart_time(start_time);
            log.setEnd_time(end_time);
            log.setLink_table("KONKA_ZLES23_RESULT_INFO");
            log.setOper_user_id("00000");
            log.setOper_real_name("系统");
            log.setOper_user_ip("127.0.0.1");
            log.setOper_fun("autoSyncKonkaZles23ResultInfo");
            this.facade.getLogOfJobService().createLogOfJob(log);
        }
    }

    /**
     * 定时同步物流信息
     */
    @Override
    public void autoSyncKonkaR3Zsof() throws SQLException {
        // 添加记录日志
        LogOfJob log = new LogOfJob();
        Date start_time = new Date();

        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.MONTH, -1);
        String day_first = df.format(calendar.getTime());

        try {
            KonkaOrderInfo entity = new KonkaOrderInfo();
            entity.setIs_sh(0);
            entity.setSync_state(1);
            entity.getMap().put("r3_id_not_null", "y");
            entity.getMap().put("start_date", day_first + " 00:00:00");

            List<KonkaOrderInfo> entityList = new ArrayList<KonkaOrderInfo>();
            entityList = this.facade.getKonkaOrderInfoService().getKonkaOrderInfoList(entity);

            // 从R3得到物流发货信息--ldy
            for (KonkaOrderInfo konkaOrderInfo : entityList) {
                Long r3_order_id = konkaOrderInfo.getR3_id();// 得到订单号
                if (r3_order_id != null) {
                    // 得到本地ZSOF数据：
                    KonkaR3Zsof konkaR3Zsof = new KonkaR3Zsof();
                    konkaR3Zsof.setVbeln(r3_order_id);
                    konkaR3Zsof = this.facade.getKonkaR3ZsofService().getKonkaR3Zsof(konkaR3Zsof);

                    ZSOF zsof = new ZSOF();
                    // can be null
                    zsof = this.facade.getR3WebInterfaceService().getR3Delivery(r3_order_id);

                    // 本地无记录:
                    // zsof.getVBEDL() 物流号
                    if (konkaR3Zsof == null && zsof != null && zsof.getVBEDL() != null) {
                        KonkaR3Zsof konkaR3Zsof2 = new KonkaR3Zsof();
                        konkaR3Zsof2.setVbedl(zsof.getVBEDL());
                        if (!("0000-00-00").equals(zsof.getLFDAT())) {
                            konkaR3Zsof2.setLfdat(zsof.getLFDAT());
                        }
                        if (!("0000-00-00").equals(zsof.getSHDAT())) {
                            konkaR3Zsof2.setShdat(zsof.getSHDAT());
                        }
                        konkaR3Zsof2.setErdat(zsof.getERDAT());
                        konkaR3Zsof2.setGjahr(zsof.getGJAHR());
                        konkaR3Zsof2.setKunnr(zsof.getKUNNR());
                        konkaR3Zsof2.setMonat(zsof.getMONAT());
                        konkaR3Zsof2.setName1(zsof.getNAME1());
                        konkaR3Zsof2.setVbeln(zsof.getVBELN());
                        konkaR3Zsof2.setVkorg(zsof.getVKORG());
                        konkaR3Zsof2.setVtext(zsof.getVTEXT());
                        this.facade.getKonkaR3ZsofService().createKonkaR3Zsof(konkaR3Zsof2);
                    }

                    // 本地已经有记录:
                    if (konkaR3Zsof != null && zsof != null && zsof.getVBEDL() != null) {

                        konkaR3Zsof.setVbeln(r3_order_id);
                        konkaR3Zsof.setVbedl(zsof.getVBEDL());
                        if (!("0000-00-00").equals(zsof.getLFDAT())) {
                            konkaR3Zsof.setLfdat(zsof.getLFDAT());
                        }
                        if (!("0000-00-00").equals(zsof.getSHDAT())) {
                            konkaR3Zsof.setShdat(zsof.getSHDAT());
                        }
                        konkaR3Zsof.setErdat(zsof.getERDAT());
                        konkaR3Zsof.setGjahr(zsof.getGJAHR());
                        konkaR3Zsof.setKunnr(zsof.getKUNNR());
                        konkaR3Zsof.setMonat(zsof.getMONAT());
                        konkaR3Zsof.setName1(zsof.getNAME1());
                        konkaR3Zsof.setVbeln(zsof.getVBELN());
                        konkaR3Zsof.setVkorg(zsof.getVKORG());
                        konkaR3Zsof.setVtext(zsof.getVTEXT());
                        this.facade.getKonkaR3ZsofService().modifyKonkaR3Zsof(konkaR3Zsof);
                    }
                }
            }

            for (KonkaOrderInfo konkaOrderInfo : entityList) {
                KonkaR3Zsof of = new KonkaR3Zsof();
                of.setVbeln(konkaOrderInfo.getR3_id());
                of = this.facade.getKonkaR3ZsofService().getKonkaR3Zsof(of);
                if (of != null && konkaOrderInfo.getId() != null) {
                    KonkaOrderInfo k = new KonkaOrderInfo();
                    k.setId(konkaOrderInfo.getId());
                    k.setR3_id(konkaOrderInfo.getR3_id());
                    if (of.getLfdat() != null && !"".equals(of.getLfdat())) {
                        k.setIs_delivered(1);
                    }
                    if (of.getShdat() != null && !"".equals(of.getShdat())) {
                        k.setIs_sh(1);
                    }
                    this.facade.getKonkaOrderInfoService().modifyKonkaOrderInfoForconfirm(k);

                }

            }
            log.setJob_status("成功");
        } catch (Exception e) {
            log.setJob_status("失败");
            log.setComments(e.getMessage());
            e.printStackTrace();
        }
        Date end_time = new Date();
        log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
        log.setJob_type("同步物流信息");
        log.setStart_time(start_time);
        log.setEnd_time(end_time);
        log.setLink_table("KONKA_R3_ZSOF");
        log.setOper_user_id("00000");
        log.setOper_real_name("系统");
        log.setOper_user_ip("127.0.0.1");
        log.setOper_fun("autoSyncKonkaR3Zsof");
        this.facade.getLogOfJobService().createLogOfJob(log);
    }

    /**
     * 定时同步R3订单明细
     */
    @Override
    public void autoKonkaR3OrderDetailForTj() throws SQLException {
        if(checkStatus("autoKonkaR3OrderDetailForTj")){
            // 添加记录日志
            LogOfJob log = new LogOfJob();
            Date start_time = new Date();
            try {
                // 需要同步销售组织集合
                Set<String> syncVkorgList = new HashSet<String>();

                // 取出所有销售组织(分公司级别的)
                List<KonkaSalesDept> konkaSalesDeptList = new ArrayList<KonkaSalesDept>();
                KonkaSalesDept konkaSalesDept = new KonkaSalesDept();
                konkaSalesDept.setIs_del(0);
                konkaSalesDeptList = this.konkaSalesDeptDao.selectEntityList(konkaSalesDept);
                for (KonkaSalesDept kd : konkaSalesDeptList) {
                    syncVkorgList.add(kd.getSales_org_code());// 为了去重,减少数据操作
                }

                // 计算当前日期往前推30天的时间
                Map<String, Long> map = new HashMap<String, Long>();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                String year = c.get(Calendar.YEAR) + "";
                c.add(Calendar.DATE, -30);
                Date s_date = c.getTime();

                // =========================同步参数 正式 start=============================//
                // 产品组
                String v_vtweg = "10";
                // 分销渠道
                String v_spart = "10";
                // 同步开始时间
                String v_audat_begin = sdf.format(s_date);
                // 同步截止时间
                String v_audat_end = sdf.format(new Date());
                //
                String v_kunnr = "";
                // =========================同步参数 正式 end=============================//
                Long isize = 0l;
                Long msize = 0l;

                // step 1 :分公司-客户订单数据 操作channelDataImport表
                // 一个销售组织一个月提交一次
                for (String korg : syncVkorgList) {
                    List<SOXX> dataList = new ArrayList<SOXX>();
                    List<ChannelDataImport> insertList = new ArrayList<ChannelDataImport>();

                    // 1.从接口取数据回来
                    dataList = new R3daoImpl().getSoxxTj(korg, v_vtweg, v_spart, v_audat_begin, v_audat_end, v_kunnr);
                    Date import_date = new Date();

                    // 2.删除相关数据
                    String startDate = null;
                    String endDate = null;
                    startDate = v_audat_begin + " 00:00:00";
                    endDate = v_audat_end + " 23:59:59";
                    ChannelDataImport cdi = new ChannelDataImport();
                    cdi.setColumn_25(korg);
                    // yyyy-MM-dd hh24:mi:ss 创建日期
                    cdi.getMap().put("startDate", startDate);
                    cdi.getMap().put("endDate", endDate);
                    // 不作更新,会把相应的数据先删除再作插入
                    channelDataImportDao.deleteEntity(cdi);

                    // 3.打包数据
                    insertList = fgsdataPackage(dataList);

                    // 4.批量提交待插入数据
                    if (insertList.size() > 0) {
                        this.channelDataImportDao.insertChannelDataImportBatch(insertList);
                    }
                    insertList = null;
                    dataList = null;
                }

                // step 2 : 总部-分公司的订单数据,操作channelDataImport2表

                // 取出总部销售组织 1001 一个 ,另外是以分公司为单位的客户 300多个
                List<KonkaSalesDept2> ksList = new ArrayList<KonkaSalesDept2>();
                // 需要同步的客户集合 orgType = 2||3 大客户或分公司(此处只做总部-分公司的订单明细用来做统计)
                Set<String> ctmList = new HashSet<String>();

                KonkaSalesDept2 ks = new KonkaSalesDept2();
                ks.setIs_del(0);
                ks.setOrg_type(1);// 康佳集团
                ksList = this.konkaSalesDept2Dao.selectEntityList(ks);
                syncVkorgList.clear();
                for (KonkaSalesDept2 kd : ksList) {
                    syncVkorgList.add(kd.getSales_org_code());// 为了去重,减少数据操作
                }
                // 客户集合 orgType = 2||3 大客户或分公司
                ks.setOrg_type(2);
                ksList.clear();
                ksList = this.konkaSalesDept2Dao.selectEntityList(ks);
                for (KonkaSalesDept2 kd : ksList) {
                    ctmList.add(kd.getSales_org_code());// 为了去重,减少数据操作
                }
                ks.setOrg_type(3);
                ksList.clear();
                ksList = this.konkaSalesDept2Dao.selectEntityList(ks);
                for (KonkaSalesDept2 kd : ksList) {
                    ctmList.add(kd.getSales_org_code());// 为了去重,减少数据操作
                }

                // 处理客户编码10位,前面不足补0
                List<String> _tempList = new ArrayList<String>();
                _tempList.addAll(ctmList);
                ctmList.clear();
                for (String s : _tempList) {
                    ctmList.add(StringUtils.leftPad(s, 10, '0'));
                }

                // 一般syncVkorgList.size()=1 ,ctmList =300 这种大客户和一般的客户在数量上有大差别
                for (String korg : syncVkorgList) {
                    for (String ctm : ctmList) {
                        List<SOXX> dataList = new ArrayList<SOXX>();
                        List<ChannelDataImport2> insertList = new ArrayList<ChannelDataImport2>();

                        // 1.从接口取数据回来
                        dataList = new R3daoImpl().getSoxxTj(korg, v_vtweg, v_spart, v_audat_begin, v_audat_end, ctm);

                        String startDate = null;
                        String endDate = null;
                        // 2.删除相关数据
                        if (dataList.size() > 0) {
                            startDate = v_audat_begin + " 00:00:00";
                            endDate = v_audat_end + " 23:59:59";
                            ChannelDataImport2 cdi = new ChannelDataImport2();
                            cdi.setColumn_25(korg);
                            cdi.setColumn_1(ctm);
                            // yyyy-MM-dd hh24:mi:ss R3订单时间
                            cdi.getMap().put("startDate", startDate);
                            cdi.getMap().put("endDate", endDate);
                            // 不作更新,会把相应的数据先删除再作插入
                            channelDataImport2Dao.deleteEntity(cdi);
                        }
                        // 3.打包数据
                        insertList = zbdataPackage(dataList);

                        // 4.批量提交待插入数据
                        if (insertList.size() > 0) {
                            this.channelDataImport2Dao.insertChannelDataImport2Batch(insertList);
                        }

                        insertList = null;
                        dataList = null;
                    }
                }

                // 生成总部订单之后 ，统计截止到当日结算数据合计 start

                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
                SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
                Date day = new Date();

                KonkaDeptJsMoney kdjMoney = new KonkaDeptJsMoney();
                kdjMoney.getMap().put("this_day_s", format1.format(day) + "-01 00:00:00");
                kdjMoney.getMap().put("this_day_e", format2.format(day) + " 23:59:59");

                // 删除当日已有数据
                KonkaDeptJsMoney km = new KonkaDeptJsMoney();
                km.getMap().put("d_day_s", format2.format(day) + " 00:00:00");
                km.getMap().put("d_day_e", format2.format(day) + " 23:59:59");
                this.konkaDeptJsMoneyDao.deleteEntity(km);

                List<KonkaDeptJsMoney> kdjList = this.konkaDeptJsMoneyDao.selectKonkaDeptJsMoneyToR3List(kdjMoney);

                if (kdjList.size() > 0) {
                    // KonkaDeptJsMoney k = new KonkaDeptJsMoney();
                    // k.getMap().put("d_day_s", format2.format(day) + " 00:00:00");
                    // k.getMap().put("d_day_e", format2.format(day) + " 23:59:59");

                    for (KonkaDeptJsMoney temp : kdjList) {
                        if (null != temp.getMap().get("dept_name_")) temp.setDept_name(temp.getMap().get("dept_name_").toString());
                        if (null != temp.getMap().get("dept_id_")) temp.setDept_id(Long.valueOf(temp.getMap().get("dept_id_").toString()));
                        if (null != temp.getMap().get("dept_sn_")) temp.setDept_sn(temp.getMap().get("dept_sn_").toString());
                        if (null != temp.getMap().get("js_money_")) temp.setJs_money(new BigDecimal(temp.getMap().get("js_money_").toString()));
                        temp.setType(20);
                        temp.setJs_date(day);
                        temp.setAdd_date(day);
                        this.konkaDeptJsMoneyDao.insertEntity(temp);
                    }
                }
                // 生成总部订单之前 ，统计截止到当日结算数据合计

                // 运行完同步之后，执行库存结转，保存库存记录
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(day);
                /*
                 * if (calendar.get(Calendar.DATE) == calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
                 * {// 如果是月末，执行盘点统计 syncJStocksSummaryForPd(); }
                 */
                log.setJob_status("成功");
            } catch (Exception e) {
                log.setJob_status("失败");
                log.setComments(e.getMessage());
                e.printStackTrace();
            }
            Date end_time = new Date();
            log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
            log.setJob_type("同步R3订单明细");
            log.setStart_time(start_time);
            log.setEnd_time(end_time);
            log.setLink_table("CHANNEL_DATA_IMPORT");
            log.setOper_user_id("00000");
            log.setOper_real_name("系统");
            log.setOper_user_ip("127.0.0.1");
            log.setOper_fun("autoKonkaR3OrderDetailForTj");
            this.facade.getLogOfJobService().createLogOfJob(log);
        }
    }

    // 封装分公司数据列表
    private List<ChannelDataImport> fgsdataPackage(List<SOXX> dataList) {

        List<ChannelDataImport> insertList = new ArrayList<ChannelDataImport>();

        Date import_date = new Date();

        for (SOXX s : dataList) {
            ChannelDataImport im = new ChannelDataImport();
            im.setImport_date(import_date);
            im.setImport_uid(1l); // 系统管理员的id
            im.setColumn_1(s.getKUNNR());// 售达方
            // im.setColumn_4();// 名称(售)
            // im.setColumn_2("");// 分
            // im.setColumn_3("");// 地域类别
            im.setColumn_5(s.getWEKUNNR());// 送达方
            // im.setColumn_6();// column_6送达方 名
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                if (!(s.getERDAT().equals(""))) {
                    im.setColumn_7(sdf2.parse(s.getERDAT() + " 08:00:00"));
                } else {
                    im.setColumn_7(null);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            } // 创建日期 订单在r/3创建的时间

            try {
                if (!(s.getERDAT().equals(""))) {
                    im.setColumn_26(sdf2.parse(s.getAUDAT() + " 08:00:00"));
                } else {
                    im.setColumn_26(null);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            } // 订单r/3凭证日期

            im.setColumn_8(s.getVBELN());// 销售单号
            im.setColumn_11(s.getMATNR());// 机型

            // 由于业务需要做出别扭调整 column_27-->column_12
            // 交货单数量
            im.setColumn_12(s.getJWMENG());

            // 含税单价
            im.setColumn_13(new BigDecimal(Double.valueOf(s.getCMPRE0())));
            // 总金额（含税）算已交货的
            im.setColumn_14(new BigDecimal(s.getKZWI6()));

            im.setColumn_9(s.getAUART());// 订单类型 zfor zfgc zfre..
            im.setColumn_10(s.getPOSNR());// column_10 项目号

            im.setColumn_15(new BigDecimal(s.getK007()));
            im.setColumn_16(new BigDecimal(s.getRB00()));

            im.setColumn_17(new BigDecimal(s.getKZWI1()));// 总净值金额(含税)

            // 用于统计的接口没有以下几个字段
            // im.setColumn_19(s.getVBELN_L());// KF交货单
            // im.setColumn_20(s.getVBELN_LES());// 物流交货单
            // im.setColumn_21(s.getLGORT_L());// KF发货仓位

            im.setColumn_23(s.getMATKL());// 客户物料号
            im.setColumn_24(s.getVKBUR());// 办事处
            im.setColumn_25(s.getVKORG());// 销售组织

            // 订单行机型数量
            // // 由于业务需要做出别扭调整原 column_27-->column_12 互换
            im.setColumn_27(new BigDecimal(s.getKWMENG()));
            // 已发货数量
            im.setColumn_28(new BigDecimal(s.getMWMENG()));
            // 已开发票数量
            im.setColumn_29(new BigDecimal(s.getRWMENG()));
            // 总金额（含税）
            im.setColumn_30(new BigDecimal(s.getKZWI6()));

            insertList.add(im);
        }
        return insertList;
    }

    // 封装总部数据列表
    private List<ChannelDataImport2> zbdataPackage(List<SOXX> dataList) {

        List<ChannelDataImport2> insertList = new ArrayList<ChannelDataImport2>();
        Date import_date = new Date();

        // 3.封装插入数据列表
        for (SOXX s : dataList) {
            ChannelDataImport2 im = new ChannelDataImport2();
            im.setImport_date(import_date);
            im.setImport_uid(1l); // 系统管理员的id
            im.setColumn_1(s.getKUNNR());// 售达方
            // im.setColumn_4();// 名称(售)
            // im.setColumn_2(column_1);// 分
            // im.setColumn_3(column_1);// 地域类别
            im.setColumn_5(s.getWEKUNNR());// 送达方
            // im.setColumn_6();// column_6送达方 名
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

            // 这个地方是为了让那个同步的时间能定位到08:00:00这个时间
            SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                if (!(s.getERDAT().equals(""))) {
                    im.setColumn_7(sdf3.parse(s.getERDAT() + " 08:00:00"));
                } else {
                    im.setColumn_7(null);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            } // 创建日期 订单在r/3创建的时间

            // 这是以前的代码
            // try {
            // if (!(s.getAUDAT().equals(""))) {
            // im.setColumn_26(sdf2.parse(s.getAUDAT()));
            // } else {
            // im.setColumn_26(null);
            // }
            // } catch (ParseException e) {
            // e.printStackTrace();
            // } // 订单r/3凭证日期

            // 这是修改后的代码，update by wangkl 2015-04-02
            try {
                if (!(s.getAUDAT().equals(""))) {
                    im.setColumn_26(sdf3.parse(s.getAUDAT() + " 08:00:00"));
                } else {
                    im.setColumn_26(null);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            } // 订单r/3凭证日期

            try {
                if (!(s.getBSTDK().equals(""))) {
                    im.setColumn_31(sdf2.parse(s.getBSTDK()));
                } else {
                    im.setColumn_31(null);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            } // 客户采购订单日期

            im.setColumn_8(s.getVBELN());// 销售单号
            im.setColumn_11(s.getMATNR());// 机型
            im.setColumn_12(s.getKWMENG());// 每一订单行机型数量
            // 含税单价
            im.setColumn_13(new BigDecimal(Double.valueOf(s.getCMPRE0())));
            // 总金额（含税）
            im.setColumn_14(new BigDecimal(Double.valueOf(s.getKZWI6())));

            im.setColumn_9(s.getAUART());// 订单类型 zfor zfgc zfre..
            im.setColumn_10(s.getPOSNR());// column_10 项目号
            // K007（含税）总部订单此值暂时没用
            // im.setColumn_15(new
            // BigDecimal(ArithUtil.sub(Double.valueOf(s.getKZWI1()),
            // Double.valueOf(s.getKZWI6()))));
            im.setColumn_15(new BigDecimal(ArithUtil.sub(Double.valueOf(s.getKZWI1()), Double.valueOf(s.getKZWI6()))));

            im.setColumn_16(new BigDecimal(0));// RB00(含税)总部订单此值暂时没用

            im.setColumn_17(new BigDecimal(s.getKZWI1()));// 总净值金额(含税)

            // 交货日期
            // try {
            // if (!(s.getWADAT_IST().equals(""))) {
            // im.setColumn_18(sdf2.parse(s.getWADAT_IST()));
            // } else {
            // im.setColumn_18(null);
            // }
            //
            // } catch (ParseException e) {
            // e.printStackTrace();
            // }
            // im.setColumn_19(s.getVBELN_L());// KF交货单
            // im.setColumn_20(s.getVBELN_LES());// 物流交货单
            // im.setColumn_21(s.getLGORT_L());// KF发货仓位

            im.setColumn_22(s.getBSTNK());// 采购订单编号

            im.setColumn_23(s.getMATKL());// 物料组

            im.setColumn_24(s.getVKBUR());// 办事处
            im.setColumn_25(s.getVKORG());// 销售组织

            // 交货单数量
            im.setColumn_27(new BigDecimal(Double.valueOf(s.getJWMENG())));
            // 已发货数量
            im.setColumn_28(new BigDecimal(Double.valueOf(s.getMWMENG())));
            // 已开发票数量
            im.setColumn_29(new BigDecimal(Double.valueOf(s.getRWMENG())));
            insertList.add(im);
        }

        return insertList;
    }

    /**
     * 定时同步R3客户分类
     */
    @Override
    public void autoSyncKonkaR3Kukla() {
        if(checkStatus("autoSyncKonkaR3Kukla")){
            // 添加记录日志
            LogOfJob log = new LogOfJob();
            Date start_time = new Date();
            try {
                ArrayList<KonkaCategory> insertList = new ArrayList<KonkaCategory>();
                ArrayList<KonkaCategory> updateList = new ArrayList<KonkaCategory>();

                R3Dao r3dao = new R3daoImpl();
                ArrayList<KUKLA> kuklaList = (ArrayList<KUKLA>) r3dao.getkuklaList();

                for (KUKLA k : kuklaList) {
                    KonkaCategory kc = new KonkaCategory();
                    kc.setC_index(Long.valueOf(k.getKUKLA()));
                    kc.setC_name(k.getVTEXT());
                    Long count_size = this.konkaCategoryDao.selectEntityCount(kc);
                    if (count_size > 0l) {
                        updateList.add(kc);
                    } else {
                        kc.setIs_del(1);
                        insertList.add(kc);
                    }
                }

                for (KonkaCategory kk : insertList) {
                    this.konkaCategoryDao.insertEntity(kk);
                }
                for (KonkaCategory kg : updateList) {
                    this.konkaCategoryDao.insertEntity(kg);
                }
                log.setJob_status("成功");
            } catch (Exception e) {
                log.setJob_status("失败");
                log.setComments(e.getMessage());
                e.printStackTrace();
            }
            Date end_time = new Date();
            log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
            log.setJob_type("同步R3客户分类");
            log.setStart_time(start_time);
            log.setEnd_time(end_time);
            log.setLink_table("KONKA_CATEGORY");
            log.setOper_user_id("00000");
            log.setOper_real_name("系统");
            log.setOper_user_ip("127.0.0.1");
            log.setOper_fun("autoSyncKonkaR3Kukla");
            this.facade.getLogOfJobService().createLogOfJob(log);
        }
    }

    /**
     * 每天8:40,18:40定时发送邮件
     */
    @Override
    public void autoSendMail() {
        // 添加记录日志
        LogOfJob log = new LogOfJob();
        Date start_time = new Date();
        try {
            String msg = "";
            StringBuffer error = new StringBuffer();
            Properties props = new Properties();
            try {
                props.load(getClass().getResourceAsStream("/mail.properties"));
            } catch (IOException e2) {
                msg = "发生错误";
                error.append(e2.getMessage());
            }
            String form = props.getProperty("mail.username");// 发件人邮箱
            String senderName = props.getProperty("mail.senderName");// 发件人

            SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
            Date date = new Date();
            String date_str = df.format(date);

            String row = props.getProperty("sendinfo.rows");// 发件人邮箱
            String[] rows = new String[] {row};
            if (row != null && !"".equals(row)) {
                rows = row.trim().split(",");
            } else {
                rows = new String[] {""};
            }
            for (int r = 0; r < rows.length; r++) {

                String to = "";// 主送
                String cc = "";// 抄送
                String[] tos = new String[] {};// 多个主送
                String[] ccs = new String[] {};// 多个抄送

                String to_name = "";
                String cc_name = "";
                String[] to_names = new String[] {};// 多个主送
                String[] cc_names = new String[] {};// 多个抄送
                String to_name_str = "";
                String cc_name_str = "";
                String subject = props.getProperty("sendinfo.title" + rows[r]) + date_str; // 主题
                String url = props.getProperty("sendinfo.url" + rows[r]);
                String url_fj = props.getProperty("sendinfo.url" + rows[r] + ".fj");
                String url_fjname = props.getProperty("sendinfo.url" + rows[r] + ".fjname");
                String info_type = props.getProperty("sendinfo.info_type" + rows[r]);
                logger.info("------------------------->url_fj:" + url_fj);

                // 主送
                KonkaSendMailUser entity = new KonkaSendMailUser();
                entity.setState(new Integer(1));
                entity.setSend_type(new Integer(1));
                if (StringUtils.isNotBlank(info_type)) {
                    entity.setInfo_type(Integer.valueOf(info_type));
                }
                List<KonkaSendMailUser> toList = facade.getKonkaSendMailUserService().getKonkaSendMailUserList(entity);
                if (toList != null) {
                    KonkaSendMailUser sendUser = new KonkaSendMailUser();
                    for (int i = 0; i < toList.size(); i++) {
                        sendUser = new KonkaSendMailUser();
                        sendUser = toList.get(i);
                        if (i == 0) {
                            to = sendUser.getEmail();
                            to_name = sendUser.getReal_name();
                        } else {
                            to += "," + sendUser.getEmail();
                            to_name += "," + sendUser.getReal_name();
                        }
                    }
                    if (toList.size() > 1) {
                        tos = to.split(",");
                        to_names = to_name.split(",");
                    }
                }

                // 抄送
                KonkaSendMailUser entity1 = new KonkaSendMailUser();
                entity1.setState(new Integer(1));
                entity1.setSend_type(new Integer(2));
                if (StringUtils.isNotBlank(info_type)) {
                    entity1.setInfo_type(Integer.valueOf(info_type));
                }
                List<KonkaSendMailUser> ccList = facade.getKonkaSendMailUserService().getKonkaSendMailUserList(entity1);

                if (ccList != null) {
                    KonkaSendMailUser sendUser = new KonkaSendMailUser();
                    for (int i = 0; i < ccList.size(); i++) {
                        sendUser = new KonkaSendMailUser();
                        sendUser = ccList.get(i);
                        if (i == 0) {
                            cc = sendUser.getEmail();
                            cc_name = sendUser.getReal_name();
                        } else {
                            cc += "," + sendUser.getEmail();
                            cc_name += "," + sendUser.getReal_name();
                        }
                    }
                    if (ccList.size() > 1) {
                        ccs = cc.split(",");
                        cc_names = cc_name.split(",");
                    }
                }
                // 组合收件人 保存到数据库

                if (tos != null && tos.length > 1) {
                    for (int i = 0; i < tos.length; i++) {
                        if (to_name_str.length() < 1800) {
                            if (i == 0) {
                                to_name_str = to_names[i] + "<" + tos[i] + ">";
                            } else {
                                to_name_str += ";" + to_names[i] + "<" + tos[i] + ">";
                            }
                        }
                    }
                } else {
                    to_name_str = to_name + "<" + to + ">";
                }
                if (to_name_str.length() >= 1800) {
                    to_name_str += "...";
                }

                if (ccs != null && ccs.length > 1) {
                    for (int i = 0; i < ccs.length; i++) {
                        if (cc_name_str.length() < 1800) {
                            if (i == 0) {
                                cc_name_str = cc_names[i] + "<" + ccs[i] + ">";
                            } else {
                                cc_name_str += ";" + cc_names[i] + "<" + ccs[i] + ">";
                            }
                        }
                    }
                } else {
                    cc_name_str = cc_name + "<" + cc + ">";
                }
                if (cc_name_str.length() >= 1800) {
                    cc_name_str += "...";
                }

                String ctxPath = servletContext.getRealPath(File.separator);
                if (!ctxPath.endsWith(File.separator)) {
                    ctxPath += File.separator;
                }
                // Write to file
                String filePath = ctxPath + "sendmail" + File.separator + System.currentTimeMillis() + ".xls";
                if (url_fj != null && !"".equals(url_fj)) {
                    writeUrlToHtmlFile(url_fj, new File(filePath), Constants.SYS_ENCODING);
                }
                String body = "";// 内容

                HttpClient httpclient = new DefaultHttpClient();
                try {
                    HttpGet httpget = new HttpGet(url);
                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    body = httpclient.execute(httpget, responseHandler);
                    logger.info("-------------->>邮件内容\n" + url + body);
                } catch (ClientProtocolException e) {
                    msg = "读取内容出错";
                    error.append(e.getMessage());
                    logger.error(e.getMessage());
                } catch (IOException e) {
                    msg = "读取内容出错";
                    error.append(e.getMessage());
                    logger.error(e.getMessage());
                }

                if (to != null && !"".equals(to)) {
                    try {
                        logger.info("-------------->>开始发送邮件" + r);
                        mailSender.setDefaultEncoding("gbk");
                        MimeMessage mailMessage = mailSender.createMimeMessage();
                        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);
                        if (tos != null && tos.length > 1) {
                            messageHelper.setTo(tos);
                        } else {
                            messageHelper.setTo(to);
                        }
                        if (ccs != null && ccs.length > 1) {
                            messageHelper.setCc(ccs);
                        } else if (cc != null && !"".equals(cc)) {
                            messageHelper.setCc(cc);
                        }
                        messageHelper.setFrom(form);
                        messageHelper.setSubject(subject);
                        messageHelper.setText(body, true);
                        messageHelper.setSentDate(new Date());
                        File file = new File(filePath);
                        if (file.exists()) {
                            messageHelper.addAttachment(url_fjname + file.getName(), file);
                        }
                        if ("".equals(msg)) {// 如果发生错误就不发送邮件了
                            mailSender.send(mailMessage);
                        }
                        logger.info("-------------->>邮件发送结束" + r);
                        if (file.exists()) {
                            file.delete();
                        }
                    } catch (MessagingException e1) {
                        logger.error(e1.toString());
                        msg += "邮件发送发生错误";
                        error.append(e1.getMessage());
                    } catch (Exception e) {
                        logger.error(e.toString());
                        msg += "邮件发送发生错误";
                        error.append(e.getMessage());
                    }
                } else {
                    msg += "收件人为空，请设置收件人信息";
                }

                // 保存邮件发送信息
                KonkaSendMailInfo mailInfo = new KonkaSendMailInfo();
                mailInfo.setTo_name(to_name_str);
                mailInfo.setCc_nane(cc_name_str);
                mailInfo.setTitle(subject + msg);
                mailInfo.setContent(body + "\n" + error.toString());
                mailInfo.setSend_name(senderName);
                mailInfo.setAdd_date(date);
                facade.getKonkaSendMailInfoService().createKonkaSendMailInfo(mailInfo);
            }
            log.setJob_status("成功");
            log.setComments(msg);
        } catch (Exception e) {
            log.setJob_status("失败");
            log.setComments(e.getMessage());
            e.printStackTrace();
        }
        Date end_time = new Date();
        log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
        log.setJob_type("定时发送邮件");
        log.setStart_time(start_time);
        log.setEnd_time(end_time);
        log.setLink_table("KONKA_SEND_MAIL_USER");
        log.setOper_user_id("00000");
        log.setOper_real_name("系统");
        log.setOper_user_ip("127.0.0.1");
        log.setOper_fun("autoSendMail");
        this.facade.getLogOfJobService().createLogOfJob(log);
    }

    public void autoSendMail2() {
        String msg = "";
        Properties props = new Properties();
        try {
            props.load(getClass().getResourceAsStream("/mail.properties"));
        } catch (IOException e2) {
            msg = "  发生错误";
        }
        String form = props.getProperty("mail.username");// 发件人邮箱
        String senderName = props.getProperty("mail.senderName");// 发件人

        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        Date date = new Date();
        String date_str = df.format(date);

        String row = props.getProperty("sendinfo.rows");// 发件人邮箱
        String[] rows = new String[] {row};
        if (row != null && !"".equals(row)) {
            rows = row.trim().split(",");
        } else {
            rows = new String[] {""};
        }
        int c = 0;
        for (int r = 0; r < rows.length; r++) {
            String to = "";// 主送
            String to_name = "";

            String subject = props.getProperty("sendinfo.title" + rows[r]) + date_str; // 主题
            String url = props.getProperty("sendinfo.url" + rows[r]);
            String url_fj = props.getProperty("sendinfo.url" + rows[r] + ".fj");
            String url_fjname = props.getProperty("sendinfo.url" + rows[r] + ".fjname");
            String info_type = props.getProperty("sendinfo.info_type" + rows[r]);

            String ctxPath = servletContext.getRealPath(File.separator);
            if (!ctxPath.endsWith(File.separator)) {
                ctxPath += File.separator;
            }
            // Write to file
            String filePath = ctxPath + "sendmail" + File.separator + System.currentTimeMillis() + ".xls";
            if (url_fj != null && !"".equals(url_fj)) {
                writeUrlToHtmlFile(url_fj, new File(filePath), Constants.SYS_ENCODING);
            }
            String body = "";// 内容

            HttpClient httpclient = new DefaultHttpClient();
            try {
                HttpGet httpget = new HttpGet(url);
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                body = httpclient.execute(httpget, responseHandler);
                logger.info("-------------->>邮件内容\n" + url + body);
            } catch (ClientProtocolException e) {
                msg = "读取内容出错";
                logger.error(e.getMessage());
            } catch (IOException e) {
                msg = "读取内容出错";
                logger.error(e.getMessage());
            }

            // 主送
            KonkaSendMailUser entity = new KonkaSendMailUser();
            entity.setState(new Integer(1));
            entity.setSend_type(new Integer(1));
            if (StringUtils.isNotBlank(info_type)) {
                entity.setInfo_type(Integer.valueOf(info_type));
            }
            List<KonkaSendMailUser> toList = facade.getKonkaSendMailUserService().getKonkaSendMailUserList(entity);
            logger.info("-------------->>用户数：" + toList.size());
            try {
                if ("".equals(msg) && toList.size() > 0) {// 如果发生错误就不发送邮件了
                    logger.info("-------------->>开始发送邮件" + r);
                    mailSender.setDefaultEncoding("gbk");
                    MimeMessage mailMessage = mailSender.createMimeMessage();
                    MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);
                    messageHelper.setFrom(form);
                    messageHelper.setSubject(subject);
                    messageHelper.setText(body, true);
                    messageHelper.setSentDate(new Date());
                    File file = new File(filePath);
                    if (file.exists()) {
                        messageHelper.addAttachment(url_fjname + file.getName(), file);
                    }

                    for (KonkaSendMailUser user : toList) {
                        c++;
                        to = user.getEmail();
                        // 保存邮件发送信息
                        final KonkaSendMailInfo mailInfo = new KonkaSendMailInfo();
                        mailInfo.setTo_name(user.getReal_name() + "<" + to + ">");
                        mailInfo.setTitle(subject + msg);
                        mailInfo.setContent(body);
                        mailInfo.setSend_name(senderName);
                        mailInfo.setAdd_date(new Date());

                        logger.info("-------------->>邮件发送" + c + to);
                        messageHelper.setTo(to);
                        mailSender.send(mailMessage);
                        facade.getKonkaSendMailInfoService().createKonkaSendMailInfo(mailInfo);
                        logger.info("-------------->>邮件发送" + c + "结束");

                    }
                    logger.info("-------------->>邮件发送结束" + r);
                    if (file.exists()) {
                        file.delete();
                    }
                } else {
                    KonkaSendMailInfo mailInfo = new KonkaSendMailInfo();
                    mailInfo.setTitle(subject + msg);
                    mailInfo.setContent(body);
                    mailInfo.setSend_name(senderName);
                    mailInfo.setAdd_date(date);
                    facade.getKonkaSendMailInfoService().createKonkaSendMailInfo(mailInfo);
                }
            } catch (MessagingException e1) {
                logger.error(e1.toString());
                msg += "邮件发送发生错误";
            }

        }

    }

    /**
     * @author Hu,Hao
     * @version 2013-12-11
     * @desc 定时同步分公司调拨计划评估
     */

    @Resource
    private KonkaR3DeptStockInfoDao konkaR3DeptStockInfoDao;

    /**
     * 定时同步分公司调拨计划评估
     */
    @Override
    public void createAutoKonkaR3DeptStockInfo() {
        if(checkStatus("createAutoKonkaR3DeptStockInfo")){
            // 添加记录日志
            LogOfJob log = new LogOfJob();
            Date start_time = new Date();
            try {
                // 初始化查询条件
                // 取当前时间
                SimpleDateFormat formaty = new SimpleDateFormat("yyyyMM");
                SimpleDateFormat formatd = new SimpleDateFormat("dd");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");

                Date now = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(now);
                cal.add(Calendar.MONTH, -3);
                Date t_date = cal.getTime();

                // 当前日期
                String now_date = formaty.format(now).toString() + formatd.format(now).toString();

                // 当月第一天
                String now_y_f = formaty.format(now).toString() + "01";

                // 前三个月
                String t_ = formaty.format(t_date).toString() + "01";

                // 销售日期(*)
                Hashtable<String, String> v_BSTDK = new Hashtable<String, String>();
                v_BSTDK.put("LOW", now_y_f);
                v_BSTDK.put("HIGH", now_date);

                // 转储单号 (*):
                Hashtable<String, String> v_EBELN = new Hashtable<String, String>();
                v_EBELN.put("LOW", "UA00-00000");
                v_EBELN.put("HIGH", "UZ99-99999");

                // 转储单日期(*):
                Hashtable<String, String> v_EINDT = new Hashtable<String, String>();
                v_EINDT.put("LOW", t_);
                v_EINDT.put("HIGH", now_date);

                // 分销渠道 (*):
                Hashtable<String, String> v_VTWEG = new Hashtable<String, String>();
                v_VTWEG.put("LOW", "10");

                Zles29aCriteria zc = new Zles29aCriteria();
                zc.setV_BSTDK(v_BSTDK);
                zc.setV_EBELN(v_EBELN);
                zc.setV_EINDT(v_EINDT);
                zc.setV_VTWEG(v_VTWEG);

                // 销售组织
                zc.setV_VKORG("1001");

                // 机型数组
                String[] matnrArrs = {"LED*", "LC*", "PDP*"};

                List<KonkaR3DeptStockInfo> entityList = new ArrayList<KonkaR3DeptStockInfo>();

                for (int i = 0; i < matnrArrs.length; i++) {
                    // 机型(*):
                    Hashtable<String, String> v_MATNR = new Hashtable<String, String>();
                    v_MATNR.put("LOW", matnrArrs[i]);
                    zc.setV_MATNR(v_MATNR);

                    // 查询LED数据
                    List<Zles29a> list = new ArrayList<Zles29a>();
                    list = DAOFactory.getInstance().getZles29a(zc);

                    if (list.size() > 0) {
                        for (Zles29a t1 : list) {
                            KonkaR3DeptStockInfo k = new KonkaR3DeptStockInfo();

                            k.setBzirk(t1.getBZIRK());// 销售地区
                            k.setClass1(t1.getCLASS());// 分公司
                            k.setClass2(t1.getCLASS2());// 经营部
                            k.setKunnr(t1.getKUNNR());// 客户编号
                            k.setMatkl(t1.getMATKL());// 物料组
                            k.setMatnr(t1.getMATNR());// 物料号

                            // B仓
                            if (null != t1.getBCLABST() && !"".equals(t1.getBCLABST())) k.setBclabst(new BigDecimal(t1.getBCLABST()));
                            // 质检Q仓
                            if (null != t1.getBCLABST() && !"".equals(t1.getBCLABST())) k.setCllabst(new BigDecimal(t1.getCLLABST()));
                            // 电子商务仓
                            if (null != t1.getDZLABST() && !"".equals(t1.getDZLABST())) k.setDzlabst(new BigDecimal(t1.getDZLABST()));
                            // 经营部数量仓
                            if (null != t1.getJYLABST() && !"".equals(t1.getJYLABST())) k.setJylabst(new BigDecimal(t1.getJYLABST()));
                            // 上月销量
                            if (null != t1.getLFIMG() && !"".equals(t1.getLFIMG())) k.setLfimg(new BigDecimal(t1.getLFIMG()));
                            // 当月销量
                            if (null != t1.getLFIMG1() && !"".equals(t1.getLFIMG1())) k.setLfimg1(new BigDecimal(t1.getLFIMG1()));
                            // 未发数量
                            if (null != t1.getMWF() && !"".equals(t1.getMWF())) k.setMwf(new BigDecimal(t1.getMWF()));
                            // 在途数量
                            if (null != t1.getMZT() && !"".equals(t1.getMZT())) k.setMzt(new BigDecimal(t1.getMZT()));
                            // 在途数量
                            if (null != t1.getMZT() && !"".equals(t1.getMZT())) k.setMzt(new BigDecimal(t1.getMZT()));
                            // P仓数量
                            if (null != t1.getPCLABST() && !"".equals(t1.getPCLABST())) k.setPclabst(new BigDecimal(t1.getPCLABST()));
                            // 铺底仓数量
                            if (null != t1.getPDLABST() && !"".equals(t1.getPDLABST())) k.setPdlabst(new BigDecimal(t1.getPDLABST()));
                            // 总量
                            if (null != t1.getSUM() && !"".equals(t1.getSUM())) k.setSum_(new BigDecimal(t1.getSUM()));
                            // 退机T仓数量
                            if (null != t1.getTJLABST() && !"".equals(t1.getTJLABST())) k.setTjlabst(new BigDecimal(t1.getTJLABST()));
                            // 样机数量
                            if (null != t1.getYJLABST() && !"".equals(t1.getYJLABST())) k.setYjlabst(new BigDecimal(t1.getYJLABST()));
                            // Z仓数量
                            if (null != t1.getZCLABST() && !"".equals(t1.getZCLABST())) k.setZclabst(new BigDecimal(t1.getZCLABST()));
                            // 周转90仓
                            if (null != t1.getZZLABST() && !"".equals(t1.getZZLABST())) k.setZzlabst(new BigDecimal(t1.getZZLABST()));

                            entityList.add(k);
                        }
                    }
                }

                // 插入数据
                if (entityList.size() > 0) {
                    KonkaR3DeptStockInfo kr = new KonkaR3DeptStockInfo();
                    kr.getMap().put("del_s_date", format.format(new Date()) + "-01 00:00:00");
                    this.konkaR3DeptStockInfoDao.deleteEntity(kr);

                    for (KonkaR3DeptStockInfo temp : entityList) {
                        this.konkaR3DeptStockInfoDao.insertEntity(temp);
                    }
                }
                log.setJob_status("成功");
            } catch (Exception e) {
                log.setJob_status("失败");
                log.setComments(e.getMessage());
                e.printStackTrace();
            }
            Date end_time = new Date();
            log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
            log.setJob_type("分公司调拨计划评估");
            log.setStart_time(start_time);
            log.setEnd_time(end_time);
            log.setLink_table("KONKA_R3_DEPT_STOCK_INFO");
            log.setOper_user_id("00000");
            log.setOper_real_name("系统");
            log.setOper_user_ip("127.0.0.1");
            log.setOper_fun("createAutoKonkaR3DeptStockInfo");
            this.facade.getLogOfJobService().createLogOfJob(log);
        }
    }

    // 定期更新物流费用
    @Override
    public void modifyOrderFy() {
        if(checkStatus("modifyOrderFy")){
            // 添加记录日志
            LogOfJob log = new LogOfJob();
            Date start_time = new Date();
            try {
                EcOrderExpressInfo p = new EcOrderExpressInfo();
                p.getMap().put("logistic_sn_is_not_null", true);
                p.getMap().put("logistic_price_is_null", true);
                List<EcOrderExpressInfo> entityList = this.ecOrderExpressInfoDao.selectEntityList(p);

                String ss = "";
                int i = 0;
                if (null != entityList && entityList.size() > 0) {
                    for (EcOrderExpressInfo pp : entityList) {
                        try {
                            if (StringUtils.isNotBlank(pp.getLogistic_sn())) {
                                // TestSfService sfService = new TestSfService();
                                // String orderFyXml =
                                // sfService.xsddfy(pp.getLogistic_sn().trim());

                                PshowOrder po = new PshowOrder();
                                po.setTrade_index(pp.getTrade_index());
                                po = this.pshowOrderDao.selectEntity(po);

                                SfOrderService sf = new SfOrderService();
                                String sxddmxOpName = "queryMailnoDetailService";
                                OrderFy orderData = new OrderFy();
                                if (po != null && po.getOpr_dept_id() != null && po.getOpr_dept_id().intValue() == 10) {
                                    orderData.setCheckword("IHcoDnPIalG8oYBzxMYomOwMmHzdykSO");
                                    orderData.setCustid("kjjt");
                                }
                                orderData.setMailno(pp.getLogistic_sn().trim());
                                String xsddfy = orderData.toXml();
                                // //System.out.println("xxxxxxxxxxxxx" + xsddfy);
                                String orderFyXml = sf.sfWebService3(xsddfy, sxddmxOpName);

                                // //System.out.println("运单费用明细：" + orderFyXml);
                                Document doc1 = DocumentHelper.parseText(orderFyXml);
                                Element rootElt3 = doc1.getRootElement();
                                Iterator iter3 = rootElt3.elementIterator("orders");
                                while (iter3.hasNext()) {
                                    Element order1 = (Element) iter3.next();
                                    Iterator iter4 = order1.elementIterator("order");
                                    while (iter4.hasNext()) {
                                        Element order2 = (Element) iter4.next();
                                        String freight = order2.elementTextTrim("freight");
                                        // //System.out.println("运费：-----》{}" + freight);
                                        if (StringUtils.isNotBlank(freight)) {
                                            EcOrderExpressInfo p3 = new EcOrderExpressInfo();
                                            p3.setId(pp.getId());
                                            p3.setLogistic_price(new BigDecimal(freight));
                                            this.ecOrderExpressInfoDao.updateEntity(p3);
                                            i++;
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            ss = "更新失败,顺丰系统连接超时！";
                        }
                    }
                }
                log.setJob_status("成功");
            } catch (Exception e) {
                log.setJob_status("失败");
                log.setComments(e.getMessage());
                e.printStackTrace();
            }
            Date end_time = new Date();
            log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
            log.setJob_type("更新顺丰物流运费");
            log.setStart_time(start_time);
            log.setEnd_time(end_time);
            log.setLink_table("EC_ORDER_EXPRESS_INFO");
            log.setOper_user_id("00000");
            log.setOper_real_name("系统");
            log.setOper_user_ip("127.0.0.1");
            log.setOper_fun("modifyOrderFy");
            this.facade.getLogOfJobService().createLogOfJob(log);
        }
    }

    // 定期更新订单状态
    @Override
    public void modifyOrderState() throws Exception {
        if(checkStatus("modifyOrderState")){
            // 添加记录日志
            LogOfJob log = new LogOfJob();
            Date start_time = new Date();
            try {
                EcOrderExpressInfo ec = new EcOrderExpressInfo();
                ec.getMap().put("ts_not_in", true);
                List<EcOrderExpressInfo> ecList = this.ecOrderExpressInfoDao.selectEntityList(ec);
                if (null != ecList && ecList.size() > 0) {
                    for (EcOrderExpressInfo ee : ecList) {
                        if (null != ee.getLogistic_content() && ee.getLogistic_content().indexOf("已签收") != -1) {
                            PshowOrder pp = new PshowOrder();
                            pp.setTrade_index(ee.getTrade_index());
                            pp = this.pshowOrderDao.selectEntity(pp);
                            // if (null != pp && pp.getState() == 40 && pp.getPay_way()
                            // != 0) {
                            if (null != pp && pp.getState() == 40) {
                                PshowOrder p = new PshowOrder();
                                p.setState(60);
                                p.setId(pp.getId());
                                this.pshowOrderDao.updateEntity(p);

                                PshowOrdeAudit poa = new PshowOrdeAudit();
                                poa.setRemark("系统自动更新");
                                poa.setOper_date(new Date());
                                poa.setOrder_id(pp.getId());
                                poa.setState(60);
                                poa.setOpr_user_id(0L);// 默认超级管理员
                                poa.setOpr_user_real_name("超级管理员");
                                poa.setTotal_price(pp.getTotal_price());
                                this.pshowOrdeAuditDao.insertEntity(poa);

                                EcUser ecUser = new EcUser();
                                ecUser.setId(p.getOrder_user_id());
                                ecUser = this.ecUserDao.selectEntity(ecUser);
                                PshowOrdeDetails pd = new PshowOrdeDetails();
                                pd.setOrder_id(p.getId());
                                List<PshowOrdeDetails> pdList = this.pshowOrdeDetailsDao.selectEntityList(pd);
                                String pd_name_num = "";// 机型*数量
                                BigDecimal rebate = new BigDecimal("0.0");
                                for (PshowOrdeDetails ps : pdList) {
                                    KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
                                    konkaBcompPd.setId(ps.getPd_id());
                                    konkaBcompPd = this.konkaBcompPdDao.selectEntity(konkaBcompPd);
                                    pd_name_num += konkaBcompPd.getPd_sn() + "*" + ps.getNum() + " ";
                                    if (ps.getRebates() != null) {
                                        rebate = rebate.add(ps.getRebates());
                                    }
                                }

                                if (null != ecUser.getLink_phone() && !"".equals(ecUser.getLink_phone())) {
                                    String msg =
                                            ecUser.getReal_name() + "您好," + p.getBuyer_name() + "的订单" + p.getTrade_index() + "已签收,积分" + p.getIntegral() + "已到账;佣金"
                                                    + rebate.setScale(2).toString() + ",请您及时进入会员中心,点击提现或转积分![奉献精致产品,引领美妙生活!康佳EPP平台,客服热线0755-61368827]";
                                    if (ecUser.getUser_type().intValue() == 2) {
                                        if (ecUser.getDept_id() != null && ecUser.getDept_id().intValue() == 2110) {
                                            msg =
                                                    "【康佳&顺丰直销商城】" + ecUser.getReal_name() + "您好," + p.getBuyer_name() + "的订单" + p.getTrade_index() + "（" + pd_name_num
                                                            + "）已签收[奉献精致产品,引领美妙生活!客服热线0755-61368827]";
                                        } else {
                                            msg =
                                                    "【康佳网上直销商城】" + ecUser.getReal_name() + "您好," + p.getBuyer_name() + "的订单" + p.getTrade_index() + "（" + pd_name_num
                                                            + "）已签收[奉献精致产品,引领美妙生活!客服热线0755-61368827]";
                                        }
                                    }

                                    if (getSendMessageResult(ecUser.getLink_phone(), msg)) {// 给下单用户发短信
                                        EcMessage ecm = new EcMessage();
                                        ecm.setAdd_date(new Date());
                                        ecm.setContent(msg);
                                        ecm.setMobile(ecUser.getLink_phone());
                                        ecm.setOrder_id(p.getId().toString());
                                        ecm.setSend_date(new Date());
                                        ecm.setOrder_state(60);
                                        ecm.setSend_state(0);
                                        ecMessageDao.insertEntity(ecm);
                                    } else {
                                        EcMessage ecm = new EcMessage();
                                        ecm.setAdd_date(new Date());
                                        ecm.setContent(msg);
                                        ecm.setMobile(ecUser.getLink_phone());
                                        ecm.setOrder_id(p.getId().toString());
                                        ecm.setSend_date(new Date());
                                        ecm.setOrder_state(60);
                                        ecm.setSend_state(1);
                                        ecMessageDao.insertEntity(ecm);
                                    }
                                }

                                // 更新物流最终状态到数据库
                                // SfOrderService sf = new SfOrderService();
                                // String sxddmxOpName = "sfexpressService";
                                // MailnoQuery mq = new MailnoQuery();
                                // mq.setTracking_type("1");
                                // mq.setMethod_type("1");
                                // mq.setTracking_number(ee.getLogistic_sn().trim());
                                // String xml2 = mq.toXml();
                                // //System.out.println("xml2--------->" + xml2);
                                // String returnXml = sf.sfWebService2(xml2,
                                // sxddmxOpName);
                                // //System.out.println("returnxml----------->" +
                                // returnXml);
                                // if (!"".equals(returnXml) &&
                                // returnXml.indexOf("remark") != -1) {
                                // EcOrderExpressInfo ep = new EcOrderExpressInfo();
                                // ep.setId(ee.getId());
                                // ep.setLogistic_content(returnXml.replaceAll("&#x9",
                                // ""));
                                // this.ecOrderExpressInfoDao.updateEntity(ep);
                                // }

                            }
                            continue;
                        } else if (null != ee.getLogistic_content() && ee.getLogistic_content().indexOf("签收人是") != -1) {
                            PshowOrder pp = new PshowOrder();
                            pp.setTrade_index(ee.getTrade_index());
                            pp = this.pshowOrderDao.selectEntity(pp);
                            // if (null != pp && pp.getState() == 40 && pp.getPay_way()
                            // != 0) {
                            if (null != pp && pp.getState() == 40) {
                                PshowOrder p = new PshowOrder();
                                p.setState(60);
                                p.setId(pp.getId());
                                this.pshowOrderDao.updateEntity(p);

                                PshowOrdeAudit poa = new PshowOrdeAudit();
                                poa.setRemark("系统自动更新");
                                poa.setOper_date(new Date());
                                poa.setOrder_id(pp.getId());
                                poa.setState(60);
                                poa.setOpr_user_id(0L);// 默认超级管理员
                                poa.setOpr_user_real_name("超级管理员");
                                poa.setTotal_price(pp.getTotal_price());
                                this.pshowOrdeAuditDao.insertEntity(poa);

                                EcUser ecUser = new EcUser();
                                ecUser.setId(p.getOrder_user_id());
                                ecUser = this.ecUserDao.selectEntity(ecUser);
                                PshowOrdeDetails pd = new PshowOrdeDetails();
                                pd.setOrder_id(p.getId());
                                List<PshowOrdeDetails> pdList = this.pshowOrdeDetailsDao.selectEntityList(pd);
                                String pd_name_num = "";// 机型*数量
                                BigDecimal rebate = new BigDecimal("0.0");
                                for (PshowOrdeDetails ps : pdList) {
                                    KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
                                    konkaBcompPd.setId(ps.getPd_id());
                                    konkaBcompPd = this.konkaBcompPdDao.selectEntity(konkaBcompPd);
                                    pd_name_num += konkaBcompPd.getPd_sn() + "*" + ps.getNum() + " ";
                                    if (ps.getRebates() != null) {
                                        rebate = rebate.add(ps.getRebates());
                                    }
                                }

                                if (null != ecUser.getLink_phone() && !"".equals(ecUser.getLink_phone())) {
                                    String msg =
                                            ecUser.getReal_name() + "您好," + p.getBuyer_name() + "的订单" + p.getTrade_index() + "已签收,积分" + p.getIntegral() + "已到账;佣金"
                                                    + rebate.setScale(2).toString() + ",请您及时进入会员中心,点击提现或转积分![奉献精致产品,引领美妙生活!康佳EPP平台,客服热线0755-61368827]";
                                    if (ecUser.getUser_type().intValue() == 2) {
                                        if (ecUser.getDept_id() != null && ecUser.getDept_id().intValue() == 2110) {
                                            msg =
                                                    "【康佳&顺丰直销商城】" + ecUser.getReal_name() + "您好," + p.getBuyer_name() + "的订单" + p.getTrade_index() + "（" + pd_name_num
                                                            + "）已签收[奉献精致产品,引领美妙生活!客服热线0755-61368827]";
                                        } else {
                                            msg =
                                                    "【康佳网上直销商城】" + ecUser.getReal_name() + "您好," + p.getBuyer_name() + "的订单" + p.getTrade_index() + "（" + pd_name_num
                                                            + "）已签收[奉献精致产品,引领美妙生活!客服热线0755-61368827]";
                                        }
                                    }

                                    if (getSendMessageResult(ecUser.getLink_phone(), msg)) {// 给下单用户发短信
                                        EcMessage ecm = new EcMessage();
                                        ecm.setAdd_date(new Date());
                                        ecm.setContent(msg);
                                        ecm.setMobile(ecUser.getLink_phone());
                                        ecm.setOrder_id(p.getId().toString());
                                        ecm.setSend_date(new Date());
                                        ecm.setOrder_state(60);
                                        ecm.setSend_state(0);
                                        ecMessageDao.insertEntity(ecm);
                                    } else {
                                        EcMessage ecm = new EcMessage();
                                        ecm.setAdd_date(new Date());
                                        ecm.setContent(msg);
                                        ecm.setMobile(ecUser.getLink_phone());
                                        ecm.setOrder_id(p.getId().toString());
                                        ecm.setSend_date(new Date());
                                        ecm.setOrder_state(60);
                                        ecm.setSend_state(1);
                                        ecMessageDao.insertEntity(ecm);
                                    }
                                }

                                // 更新物流最终状态到数据库
                                // SfOrderService sf = new SfOrderService();
                                // String sxddmxOpName = "sfexpressService";
                                // MailnoQuery mq = new MailnoQuery();
                                // mq.setTracking_type("1");
                                // mq.setMethod_type("1");
                                // mq.setTracking_number(ee.getLogistic_sn().trim());
                                // String xml2 = mq.toXml();
                                // //System.out.println("xml2--------->" + xml2);
                                // String returnXml = sf.sfWebService2(xml2,
                                // sxddmxOpName);
                                // //System.out.println("returnxml----------->" +
                                // returnXml);
                                // if (!"".equals(returnXml) &&
                                // returnXml.indexOf("remark") != -1) {
                                // EcOrderExpressInfo ep = new EcOrderExpressInfo();
                                // ep.setId(ee.getId());
                                // ep.setLogistic_content(returnXml.replaceAll("&#x9",
                                // ""));
                                // this.ecOrderExpressInfoDao.updateEntity(ep);
                                // }

                            }
                            continue;
                        } else {

                            PshowOrder pp = new PshowOrder();
                            pp.setTrade_index(ee.getTrade_index());
                            pp = this.pshowOrderDao.selectEntity(pp);
                            if (null != pp && pp.getState() == 40) {
                                SfOrderService sf = new SfOrderService();
                                String sxddmxOpName = "sfexpressService";
                                MailnoQuery mq = new MailnoQuery();
                                mq.setTracking_type("1");
                                mq.setMethod_type("1");
                                if (pp.getOpr_dept_id() != null && pp.getOpr_dept_id().intValue() == 10) {// 哈尔滨分公司
                                    mq.setHead("kjjt,IHcoDnPIalG8oYBzxMYomOwMmHzdykSO");
                                }
                                mq.setTracking_number(ee.getLogistic_sn().trim());
                                String xml2 = mq.toXml();
                                String returnXml = sf.sfWebService2(xml2, sxddmxOpName);
                                if (!"".equals(returnXml) && returnXml.indexOf("remark") != -1) {
                                    EcOrderExpressInfo ep = new EcOrderExpressInfo();
                                    ep.setId(ee.getId());
                                    ep.setLogistic_content(returnXml.replaceAll("&#x9", ""));
                                    this.ecOrderExpressInfoDao.updateEntity(ep);
                                }
                            }
                        }
                    }
                }
                log.setJob_status("成功");
            } catch (Exception e) {
                log.setJob_status("失败");
                log.setComments(e.getMessage());
                e.printStackTrace();
            }
            Date end_time = new Date();
            log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
            log.setJob_type("同步订单在顺丰的状态");
            log.setStart_time(start_time);
            log.setEnd_time(end_time);
            log.setLink_table("EC_MESSAGE");
            log.setOper_user_id("00000");
            log.setOper_real_name("系统");
            log.setOper_user_ip("127.0.0.1");
            log.setOper_fun("modifyOrderState");
            this.facade.getLogOfJobService().createLogOfJob(log);
        }
    }

    /**
     * 初始化订单新产品到库存里面
     */
    @Override
    public void syncR3OrderToCustPd() {
        if(checkStatus("syncR3OrderToCustPd")){
            syncR3OrderToCustPd1(null);
        }
    }

    /**
     * 初始化订单新产品到库存里面
     *
     * @param in_r3_code r3编码
     */
    @Override
    public void syncR3OrderToCustPd1(String in_r3_code) {
        // 添加记录日志
        LogOfJob log = new LogOfJob();
        Date start_time = new Date();

        List<KonkaR3Shop> shopList;
        // int i = 1;
        long req_num;
        long res_num;
        try {
            // Date start_date1 = DateUtils.addMonths(new Date(), -1 *
            // Constants.DEFAULT_MONTH_SYNC_PD_OF_R3ORDER);//提前一个月
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -3);
            Date start_date = calendar.getTime();// 获取3天前的时间,但是ChannelDataImport中的时间节点是00:00:00,所以相当于提前两天
            // 待系统稳定之后，修改成两天前的，相当于提前一天的

            // 查询全部客户目录
            KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
            if (in_r3_code != null) {
                konkaR3Shop.setR3_code(in_r3_code);
            }
            konkaR3Shop.setIs_del(0L);
            shopList = this.facade.getKonkaR3ShopService().getKonkaR3ShopList(konkaR3Shop);
            req_num = 0;
            res_num = 0;
            if (shopList != null && shopList.size() > 0) {
                for (KonkaR3Shop oner3shop : shopList) {
                    // 获取客户R3编码
                    String r3_code = oner3shop.getR3_code();

                    // 查询客户R3（进货）订单记录
                    ChannelDataImport data = new ChannelDataImport();
                    data.setColumn_1(r3_code);
                    data.getMap().put("import_date_gt", DateFormatUtils.format(start_date, "yyyy-MM-dd HH:mm:ss"));
                    List<ChannelDataImport> groupByPdStatList = this.facade.getChannelDataImportService().getGroupByPdStatList(data);
                    if (groupByPdStatList != null && groupByPdStatList.size() > 0) {
                        // int j = 1;
                        for (ChannelDataImport onedata : groupByPdStatList) {
                            String pd_name = onedata.getColumn_11();
                            String num = onedata.getColumn_12();
                            BigDecimal money = onedata.getColumn_14();

                            if (StringUtils.isNotBlank(pd_name)) {
                                // 如果产品没有初始化，则不将客户的R3订单初始化进去
                                JBaseGoods jb = new JBaseGoods();
                                // jb.setGoods_name(pd_name);
                                jb.setC_id(oner3shop.getId());
                                // 如果该客户没有初始化过任何一件商品，则不进行商品的初始化操作
                                if (this.facade.getJBaseGoodsService().getJBaseGoodsCount(jb) > 0) {
                                    // 初始化产品
                                    res_num = res_num + initOneGoodsOfCust(oner3shop.getId(), pd_name, onedata.getColumn_5(), r3_code, onedata.getColumn_13());
                                    req_num++;
                                } else {
                                    logger.info("没有初始化商品，不进行自动商品同步");
                                }
                            }
                            // logger.info(
                            // "done. {}({}%). {}: {}, {} {} {}",
                            // new String[] { String.valueOf(j), String.valueOf(j *
                            // 100 / groupByPdStatList.size()),
                            // oner3shop.getR3_code(), oner3shop.getCustomer_name(),
                            // pd_name, num,
                            // money.toString() });
                            // j++;

                        }

                        // logger.info(
                        // "done. {}({}%). {}: {}",
                        // new String[] { String.valueOf(i), String.valueOf(i * 100
                        // / shopList.size()),
                        // oner3shop.getR3_code(), oner3shop.getCustomer_name() });
                        // i++;
                    }

                }
            }
            log.setJob_status("成功");
            log.setComments("查询到的客户个数有：" + shopList.size() + ",查询到的商品数据为" + req_num + "台");
        } catch (Exception e) {
            log.setJob_status("失败");
            log.setComments(e.getMessage());
            e.printStackTrace();
        }
        Date end_time = new Date();
        log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
        log.setJob_type("同步订单在顺丰的状态");
        log.setStart_time(start_time);
        log.setEnd_time(end_time);
        log.setLink_table("J_BASE_GOODS_INIT_STOCK");
        log.setOper_user_id("00000");
        log.setOper_real_name("系统");
        log.setOper_user_ip("127.0.0.1");
        log.setOper_fun("syncR3OrderToCustPd1");
        this.facade.getLogOfJobService().createLogOfJob(log);
    }

    /**
     * 初始化客户的一个产品
     */
    public int initOneGoodsOfCust(Long cust_id, String goods_name, String sale_r3_code, String r3_code, BigDecimal price) {
        if (null == cust_id || StringUtils.isBlank(goods_name)) {
            logger.info("cust_id为空");
            return 0;
        }
        // Date start_date = DateUtils.addMonths(new Date(), -1 *
        // Constants.DEFAULT_MONTH_SYNC_PD_OF_R3ORDER);// 获取30天前的时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -3);
        Date start_date = calendar.getTime();// 获取3天前的时间,但是ChannelDataImport中的时间节点是00:00:00,所以相当于提前两天
        // 待系统稳定之后，修改成两天前的，相当于提前一天的

        if (StringUtils.isEmpty(r3_code)) {
            logger.info("r3_code为空");
            return 0;
        }
        if (StringUtils.isEmpty(sale_r3_code)) {
            logger.info("sale_r3_code为空");
            return 0;
        }

        JBaseGoods one = new JBaseGoods();
        one.setC_id(cust_id);
        one.setGoods_name(goods_name);
        one.setIs_konka(1);
        JBaseGoods existgoods = this.facade.getJBaseGoodsService().getJBaseGoods(one);
        Long insert_goods_id = 0L;
        if (existgoods == null) {// 未添加商品数据

            // 获取客户商品单位ID
            JBaseType type_10002 = new JBaseType();
            type_10002.setPar_id(10002L);
            type_10002.setType_name("台");
            type_10002.setC_id(cust_id);
            type_10002.setIs_del(0);
            type_10002 = this.facade.getJBaseTypeService().getJBaseType(type_10002);
            Long tai_id = null;
            if (null == type_10002) {
                // 初始化 商品单位 信息
                JBaseType __type = new JBaseType();
                __type.setPar_id(10002L);
                __type.setType_name("台");
                __type.setIs_del(0);
                __type.setType_desc("大件描述单位");
                __type.setC_id(cust_id);
                tai_id = this.facade.getJBaseTypeService().createJBaseType(__type);
            } else {
                tai_id = type_10002.getType_id();
            }
            one.setGoods_unit(tai_id);

            // 获取客户商品类型
            if (one.getIs_konka() == 1) {
                JBaseType type_10001 = new JBaseType();
                type_10001.setPar_id(10001L);
                type_10001.setType_name("康佳电视");
                type_10001.setC_id(cust_id);
                type_10001.setIs_del(0);
                type_10001 = this.facade.getJBaseTypeService().getJBaseType(type_10001);
                Long konka_tv_id = null;
                if (null == type_10001) {
                    // 初始化 商品单位 信息
                    JBaseType __type = new JBaseType();
                    __type.setPar_id(10001L);
                    __type.setType_name("康佳电视");
                    __type.setType_desc("系统初始化");
                    __type.setC_id(cust_id);
                    konka_tv_id = this.facade.getJBaseTypeService().createJBaseType(__type);
                } else {
                    konka_tv_id = type_10001.getType_id();
                }
                one.setGoods_type(konka_tv_id);
            }

            one.getMap().put("opr_date", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

            insert_goods_id = this.facade.getJBaseGoodsService().createJBaseGoods(one);
            // 初始化“新品”的库存end.
        } else {// 已添加商品数据
            insert_goods_id = existgoods.getGoods_id();
        }

        Long insert_store_id = 0l;
        JBaseStoreR3 r3store = new JBaseStoreR3();
        r3store.setSale_r3_code(sale_r3_code);
        r3store.setR3_code(r3_code);
        List<JBaseStoreR3> r3List = this.facade.getJBaseStoreR3Service().getJBaseStoreR3List(r3store);
        if (r3List != null && r3List.size() > 0) {// 有对应的仓库
            if (r3List.get(0) != null && r3List.get(0).getStore_id() != null) {
                insert_store_id = r3List.get(0).getStore_id();
            }
        } else {// 没有对应的仓库
            // 添加新仓库
            JBaseStore store = new JBaseStore();
            store.setC_id(cust_id);
            Long count = this.facade.getJBaseStoreService().getJBaseStoreCount(store);
            store.setIs_del(0);
            store.setAdd_date(start_date);
            store.setStore_sn("CK-" + cust_id + "-" + (count + 1));
            store.setStore_name("CK-" + cust_id + "-" + (count + 1));
            if (sale_r3_code.equals(r3_code)) {// 总库，添加默认出入仓库
                store.setIs_dft_buy_store(1);
                store.setIs_dft_sell_store(1);
                store.setStore_name("总库");
            } else {// 非总库
                store.setIs_dft_buy_store(0);
                store.setIs_dft_sell_store(0);
            }
            insert_store_id = this.facade.getJBaseStoreService().createJBaseStore(store);

            // 添加新仓库对应送达方关系
            JBaseStoreR3 r3store1 = new JBaseStoreR3();
            r3store1.setStore_id(insert_store_id);
            r3store1.setIs_del(0);
            r3store1.setAdd_date(start_date);
            r3store1.setR3_code(r3_code);
            r3store1.setSale_r3_code(sale_r3_code);
            this.facade.getJBaseStoreR3Service().createJBaseStoreR3(r3store1);
        }

        JBaseGoodsInitStock jstock = new JBaseGoodsInitStock();
        jstock.setGoods_id(insert_goods_id);
        jstock.setStore_id(insert_store_id);
        jstock.setC_id(cust_id);
        List<JBaseGoodsInitStock> existstockList = this.facade.getJBaseGoodsInitStockService().getJBaseGoodsInitStockList(jstock);
        if (null != existstockList && existstockList.size() > 0) {// 之前有初始化库存，不操作
        } else {// 之前没有初始化库存，新增初始化库存
            jstock.setInit_date(start_date);
            jstock.setInit_count(0l);
            jstock.setInit_state(0);
            jstock.setInit_user(0l);
            if (null != price) {
                jstock.setBuy_price(price);
                jstock.setInit_money(new BigDecimal("0"));
            }
            jstock.getMap().put("is_first_init",true);
            this.facade.getJBaseGoodsInitStockService().createJBaseGoodsInitStockAndStock(jstock);
            return 1;
        }
        logger.info("{} - {} Initialized.", cust_id, goods_name);
        return 0;
    }

    @Override
    public void sendMail(EcUser t) {
        String msg = "";
        String to = "";//
        String to_name = "";

        to = t.getEmail();
        to_name = t.getReal_name();

        Properties props = new Properties();
        try {
            props.load(getClass().getResourceAsStream("/mail.properties"));
        } catch (IOException e2) {
            msg = "  发生错误";
        }
        String form = props.getProperty("mail.username");// 发件人邮箱
        String senderName = props.getProperty("mail.senderName");// 发件人

        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        Date date = new Date();
        String date_str = df.format(date);

        String body = "";// 内容
        String subject = "";// 主题

        body = "欢迎光临！！";
        logger.info("-------------->>邮件内容\n" + body);
        subject = "激活账号！";
        if (to != null && !"".equals(to)) {
            try {
                logger.info("-------------->>开始发送邮件");
                mailSender.setDefaultEncoding("gbk");
                MimeMessage mailMessage = mailSender.createMimeMessage();
                MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);
                messageHelper.setTo(to);
                messageHelper.setFrom(form);
                try {
                    InternetAddress add = new InternetAddress(form, senderName);
                    messageHelper.setFrom(add);
                } catch (UnsupportedEncodingException e) {
                    logger.error(e.toString());
                    msg += "邮件发送发生错误";
                }

                messageHelper.setSubject(subject);
                messageHelper.setText(body, true);
                messageHelper.setSentDate(new Date());
                if ("".equals(msg)) {// 如果发生错误就不发送邮件了
                    mailSender.send(mailMessage);
                }
                logger.info("-------------->>邮件发送结束");
            } catch (MessagingException e1) {
                logger.error(e1.toString());
                msg += "邮件发送发生错误";
            }
        } else {
            msg += "收件人为空，请设置收件人信息";
        }

    }

    @Override
    public void sendOrderToSf() {
        PshowOrder pp = new PshowOrder();
        pp.getMap().put("express_id_is_not_null", true);
        pp.getMap().put("express_id_in", true);
        pp.getMap().put("trade_index_not_in", true);
        List<PshowOrder> entityList = this.pshowOrderDao.selectEntityList(pp);
        for (PshowOrder p : entityList) {
            SfOrderService sf = new SfOrderService();
            String sxddmxOpName = "sfexpressService";
            Order order = new Order();
            order.setOrderid(p.getTrade_index());
            order.setExpress_type("1");
            order.setParcel_quantity("1");
            order.setPay_method("1");
            if (p.getBuyer_p_index() != null && p.getBuyer_p_index() != -1) {
                String p_index = p.getBuyer_p_index().toString();
                if (StringUtils.isNotBlank(p_index)) {
                    if (!p_index.endsWith("00")) {
                        if (p_index.length() == 6) {
                            BaseProvinceListFour baseProvince = new BaseProvinceListFour();
                            baseProvince.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 2) + "0000"));
                            baseProvince = this.baseProvinceListFourDao.selectEntity(baseProvince);
                            order.setD_province(baseProvince.getP_name());

                            BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
                            baseProvince1.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 4) + "00"));
                            baseProvince1 = this.baseProvinceListFourDao.selectEntity(baseProvince1);
                            order.setD_city(baseProvince1.getP_name());

                            BaseProvinceListFour baseProvince2 = new BaseProvinceListFour();
                            baseProvince2.setP_index(Long.valueOf(p_index));
                            baseProvince2 = this.baseProvinceListFourDao.selectEntity(baseProvince2);
                            order.setD_county(baseProvince2.getP_name());
                        } else if (p_index.length() == 8) {
                            BaseProvinceListFour baseProvince = new BaseProvinceListFour();
                            baseProvince.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 2) + "0000"));
                            baseProvince = this.baseProvinceListFourDao.selectEntity(baseProvince);
                            order.setD_province(baseProvince.getP_name());

                            BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
                            baseProvince1.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 4) + "00"));
                            baseProvince1 = this.baseProvinceListFourDao.selectEntity(baseProvince1);
                            order.setD_city(baseProvince1.getP_name());

                            BaseProvinceListFour baseProvince2 = new BaseProvinceListFour();
                            baseProvince2.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 6)));
                            baseProvince2 = this.baseProvinceListFourDao.selectEntity(baseProvince2);
                            order.setD_county(baseProvince2.getP_name());

                        }
                    } else if (p_index.endsWith("0000")) {
                        if (p_index.length() == 6) {
                            BaseProvinceListFour baseProvince = new BaseProvinceListFour();
                            baseProvince.setP_index(Long.valueOf(p_index));
                            baseProvince = this.baseProvinceListFourDao.selectEntity(baseProvince);
                            order.setD_province(baseProvince.getP_name());
                            order.setD_city("");
                            order.setD_county("");
                        } else if (p_index.length() == 8) {
                            BaseProvinceListFour baseProvince = new BaseProvinceListFour();
                            baseProvince.setP_index(Long.valueOf(p_index));
                            baseProvince = this.baseProvinceListFourDao.selectEntity(baseProvince);
                            order.setD_province(baseProvince.getP_name());

                            BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
                            baseProvince1.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 4) + "00"));
                            baseProvince1 = this.baseProvinceListFourDao.selectEntity(baseProvince1);
                            order.setD_city(baseProvince1.getP_name());
                            order.setD_county("");
                        }
                    } else if (p_index.endsWith("00")) {
                        if (p_index.length() == 6) {
                            BaseProvinceListFour baseProvince = new BaseProvinceListFour();
                            baseProvince.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 2) + "0000"));
                            baseProvince = this.baseProvinceListFourDao.selectEntity(baseProvince);
                            order.setD_province(baseProvince.getP_name());

                            BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
                            baseProvince1.setP_index(Long.valueOf(p_index));
                            baseProvince1 = this.baseProvinceListFourDao.selectEntity(baseProvince1);
                            order.setD_city(baseProvince1.getP_name());
                            order.setD_county("");

                        } else if (p_index.length() == 8) {

                            BaseProvinceListFour baseProvince = new BaseProvinceListFour();
                            baseProvince.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 2) + "0000"));
                            baseProvince = this.baseProvinceListFourDao.selectEntity(baseProvince);
                            order.setD_province(baseProvince.getP_name());

                            BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
                            baseProvince1.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 4) + "00"));
                            baseProvince1 = this.baseProvinceListFourDao.selectEntity(baseProvince1);
                            order.setD_city(baseProvince1.getP_name());

                            BaseProvinceListFour baseProvince2 = new BaseProvinceListFour();
                            baseProvince2.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 6)));
                            baseProvince2 = this.baseProvinceListFourDao.selectEntity(baseProvince2);
                            order.setD_county(baseProvince2.getP_name());

                        }
                    }
                }
            }

            order.setD_contact(p.getBuyer_name());
            order.setD_tel(p.getBuyer_tel());
            order.setD_mobile(p.getBuyer_mp());
            order.setD_address(p.getBuyer_addr());

            order.setCargo("");
            order.setCargo_count("");
            order.setCargo_unit("");
            order.setCargo_weight("");
            order.setCargo_amount("");
            order.setCargo_total_weight("1");
            order.setSendstarttime("");
            String xml = order.toXml();
            String returnXml = sf.sfWebService2(xml, sxddmxOpName);

            try {
                Document doc = DocumentHelper.parseText(returnXml);
                Element rootElt = doc.getRootElement();
                if (rootElt.elementTextTrim("Head").equalsIgnoreCase("OK")) {
                    Iterator<Element> iter = rootElt.elementIterator("Body");
                    while (iter.hasNext()) {
                        Element iter2 = iter.next();
                        Iterator<Element> iter3 = iter2.elementIterator("OrderResponse");
                        while (iter3.hasNext()) {
                            Element iter4 = iter3.next();
                            if (StringUtils.isNotBlank(iter4.attributeValue("mailno"))) {
                                EcOrderExpressInfo ex = new EcOrderExpressInfo();
                                ex.setTrade_index(p.getTrade_index());
                                ex.setExpress_id(p.getExpress_id());
                                EcBaseExpress ep = new EcBaseExpress();
                                ep.setExpress_id(p.getExpress_id());
                                ep = this.ecBaseExpressDao.selectEntity(ep);
                                ex.setExpress_name(ep.getExpress_name());
                                ex.setLogistic_sn(iter4.attributeValue("mailno"));
                                ex.setAdd_date(new Date());
                                this.ecOrderExpressInfoDao.insertEntity(ex);
                                // //System.out.println("订单入顺丰成功！！！");
                            }
                        }
                    }
                }
            } catch (Exception e) {
                // //System.out.println("报错啦！！");
            }

        }

    }

    // 同步方法
    // 1.按当前日期来同步,通过配置来取得月结开始和月结结束
    // 2.机型分别为LED*,LC*,PDP*
    // 3.分销渠道,产品组 固定为10
    // 4. 由于机型的数量固定,而且数量不会太多. 估计(目前大约)500+500+50 个机型是极限

    // 一.同步机型存销比(上个月数据,昨天数据,今天数据)
    // 二.同步机型利润(只需要今天数据,只有当月的)
    // 三.同步前,需要清除一些垃圾数据(机型存比:同步的时间点如果为一个月的第一天,那么删除上个月的数据;删除前天的数据 ;
    // 机型利润:每天清除)
    @Override
    public void autoSyncKonkaModelXSReport() throws SQLException {
        if(checkStatus("autoSyncKonkaModelXSReport")){
            // 添加记录日志
            LogOfJob log = new LogOfJob();
            Date start_time = new Date();
            try {
                // 前天
                String qianTian = "";
                // 昨天
                String zuoTian = "";
                // 当月月初
                String firstDayOfCurrentMonth = "";
                // 上月初
                String fisrtDayOfLastMonth = "";
                // 上月尾
                String lastDayOfLastMonth = "";

                String y = "";// year
                String m = "";// month
                String d = "";// the first day of a month
                String d2 = "";// the last day of a month

                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

                Date date = new Date();
                Calendar c = Calendar.getInstance();
                // 昨天
                c.setTime(date);
                c.add(Calendar.DATE, -1);
                zuoTian = sdf.format(c.getTime());

                // 前天
                c.setTime(date);
                c.add(Calendar.DATE, -2);
                qianTian = sdf.format(c.getTime());

                // ===========================时间配置start================================//
                // 当月月初
                KonkaSpList ksp = new KonkaSpList();
                c.setTime(date);
                y = c.get(Calendar.YEAR) + "";
                if (c.get(Calendar.MONTH) + 1 >= 10) {
                    m = String.valueOf(c.get(Calendar.MONTH) + 1);
                } else {
                    m = "0" + String.valueOf(c.get(Calendar.MONTH) + 1);
                }
                d = "01";

                ksp.setYear(Integer.valueOf(y));
                ksp.setMonth(Integer.valueOf(m));
                ksp.setIs_del(0);
                ksp = this.facade.getKonkaSpListService().getKonkaSpList(ksp);
                // 如果系统没有维护时间,就以自然月进行数据同步
                if (ksp.getS_date() != null && ksp.getE_date() != null) {
                    firstDayOfCurrentMonth = sdf.format(ksp.getS_date());
                } else {
                    firstDayOfCurrentMonth = y + m + d;
                }

                // 上月初
                KonkaSpList ksp2 = new KonkaSpList();
                c.setTime(date);
                c.add(Calendar.MONTH, -1);
                y = c.get(Calendar.YEAR) + "";
                if (c.get(Calendar.MONTH) + 1 >= 10) {
                    m = String.valueOf(c.get(Calendar.MONTH) + 1);
                } else {
                    m = "0" + String.valueOf(c.get(Calendar.MONTH) + 1);
                }
                d = "01";
                d2 = "" + c.getActualMaximum(Calendar.DAY_OF_MONTH);

                ksp2.setYear(Integer.valueOf(y));
                ksp2.setMonth(Integer.valueOf(m));
                ksp2.setIs_del(0);
                ksp2 = this.facade.getKonkaSpListService().getKonkaSpList(ksp);
                // 如果系统没有维护时间,就以自然月进行数据同步
                if (ksp2.getS_date() != null && ksp2.getE_date() != null) {
                    fisrtDayOfLastMonth = sdf.format(ksp2.getS_date());
                    lastDayOfLastMonth = sdf.format(ksp2.getE_date());
                } else {
                    // 上月初
                    fisrtDayOfLastMonth = y + m + d;
                    // 上月尾
                    lastDayOfLastMonth = y + m + d2;
                }

                // ============================时间配置end=============================//

                // 一 .机型存销
                List<KonkaBbZj98Import> zj98LastMonthList = new ArrayList<KonkaBbZj98Import>();
                List<KonkaBbZj98Import> zj98toQiantiandayList = new ArrayList<KonkaBbZj98Import>();
                List<KonkaBbZj98Import> zj98toZuotiandayList = new ArrayList<KonkaBbZj98Import>();

                String[] matnrs = {"LED*", "LC*", "PDP*"};
                // 上个月数据 Y
                zj98LastMonthList = this.getZJ98Data(matnrs, fisrtDayOfLastMonth, lastDayOfLastMonth);
                // 截至前天的数据
                zj98toQiantiandayList = this.getZJ98Data(matnrs, firstDayOfCurrentMonth, qianTian);
                // 截至昨天的数据
                zj98toZuotiandayList = this.getZJ98Data(matnrs, firstDayOfCurrentMonth, zuoTian);
                //
                List<KonkaBbZj98Import> list1 = new ArrayList<KonkaBbZj98Import>();

                // 上个月数据
                for (KonkaBbZj98Import ki : zj98LastMonthList) {
                    ki.setYear(Long.valueOf(ksp2.getYear()));
                    ki.setMonth(Long.valueOf(ksp2.getMonth()));
                    ki.setDay(28l);// 写死一天
                    list1.add(ki);
                }

                // 截至前天数据
                c.setTime(date);
                c.add(Calendar.DATE, -2);
                for (KonkaBbZj98Import ki : zj98toQiantiandayList) {
                    ki.setYear(Long.valueOf(c.get(Calendar.YEAR)));
                    ki.setMonth(Long.valueOf(c.get(Calendar.MONTH) + 1));
                    ki.setDay(Long.valueOf(c.get(Calendar.DAY_OF_MONTH)));
                    list1.add(ki);
                }
                // 截至昨天数据
                c.setTime(date);
                c.add(Calendar.DATE, -1);
                for (KonkaBbZj98Import ki : zj98toZuotiandayList) {
                    ki.setYear(Long.valueOf(c.get(Calendar.YEAR)));
                    ki.setMonth(Long.valueOf(c.get(Calendar.MONTH) + 1));
                    ki.setDay(Long.valueOf(c.get(Calendar.DAY_OF_MONTH)));
                    list1.add(ki);
                }

                // 二 .机型利润()
                List<KonkaBbItr2Import> itr2toZuoTianList = new ArrayList<KonkaBbItr2Import>();
                itr2toZuoTianList = this.getZdmrtxsData(matnrs, firstDayOfCurrentMonth, zuoTian);
                List<KonkaBbItr2Import> list2 = new ArrayList<KonkaBbItr2Import>();
                list2 = itr2toZuoTianList;
                // execute function
                this.facade.getKonkaBbZj98ImportService().createZJ98AndItr2Sync(list1, list2);
                log.setJob_status("成功");
            } catch (Exception e) {
                log.setJob_status("失败");
                log.setComments(e.getMessage());
                e.printStackTrace();
            }
            Date end_time = new Date();
            log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
            log.setJob_type("同步存销比与毛利分析接口数据");
            log.setStart_time(start_time);
            log.setEnd_time(end_time);
            log.setLink_table("KONKA_BB_ITR2_IMPORT");
            log.setOper_user_id("00000");
            log.setOper_real_name("系统");
            log.setOper_user_ip("127.0.0.1");
            log.setOper_fun("autoSyncKonkaModelXSReport");
            this.facade.getLogOfJobService().createLogOfJob(log);
        }
    }

    // zj98 机型存销比
    protected List<KonkaBbZj98Import> getZJ98Data(String[] matnrs, String begin_date, String end_date) {

        // init params //
        String v_vtweg = "10";
        String v_spart = "10";
        // String v_matnr_begin = "";
        // String v_matnr_end = "";
        // String v_bstdk_begin = "";
        // String v_bstdk_end = "";
        // String v_cxb_begin = "";
        // String v_cxb_end = "";
        // 目前就只有这三类机型需求
        // String[] matnrs = { "LED*", "LC*", "PDP*" };

        List<List<KonkaBbZj98Import>> tempList = new ArrayList<List<KonkaBbZj98Import>>();
        List<KonkaBbZj98Import> destList = new ArrayList<KonkaBbZj98Import>();
        for (String matnr : matnrs) {
            ReturnInfo<KonkaBbZj98Import> info = new ReturnInfo<KonkaBbZj98Import>();
            List<KonkaBbZj98Import> list = new ArrayList<KonkaBbZj98Import>();

            info = this.facade.getR3WebInterfaceService().getZlesZJ98(matnr, null, v_vtweg, v_spart, begin_date, end_date, null, null);
            if (info.getSuccess() == true) {
                list = info.getDataResult();
                tempList.add(list);
            }

        }

        for (List<KonkaBbZj98Import> sourceList : tempList) {
            for (KonkaBbZj98Import ki : sourceList) {
                destList.add(ki);
            }
        }
        tempList.clear();
        return destList;

    }

    // zdmrtxs 机型利润(当月截至今天数据)
    protected List<KonkaBbItr2Import> getZdmrtxsData(String[] matnrs, String begin_date, String end_date) {

        List<List<KonkaBbItr2Import>> tempList = new ArrayList<List<KonkaBbItr2Import>>();
        List<KonkaBbItr2Import> destList = new ArrayList<KonkaBbItr2Import>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date date2 = new Date();
        String m = "";
        if (end_date != null && !"".equals(end_date)) {
            try {
                date2 = sdf.parse(end_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date2);
        ZdmtrxCriteria zc = new ZdmtrxCriteria();
        if (c.get(Calendar.MONTH) + 1 >= 10) {
            m = (c.get(Calendar.MONTH) + 1) + "";
        } else {
            m = "0" + (c.get(Calendar.MONTH) + 1);
        }
        for (String matnr : matnrs) {
            zc.resetFieldValue();
            // 机型
            zc.getV_MATNR().put("LOW", matnr);
            // 年度
            zc.getV_LFGJA().put("LOW", c.get(Calendar.YEAR) + "");
            zc.getV_LFGJA().put("HIGH", c.get(Calendar.YEAR) + "");
            // 月份
            zc.getV_LFMON().put("LOW", m);
            zc.getV_LFMON().put("HIGH", m);

            ReturnInfo<KonkaBbItr2Import> info = new ReturnInfo<KonkaBbItr2Import>();
            List<KonkaBbItr2Import> list = new ArrayList<KonkaBbItr2Import>();

            info = this.facade.getR3WebInterfaceService().getITR2(zc);
            if (info.getSuccess() == true) {
                list = info.getDataResult();
            }
            tempList.add(list);
        }

        for (List<KonkaBbItr2Import> sourceList : tempList) {
            for (KonkaBbItr2Import ki : sourceList) {
                destList.add(ki);
            }
        }
        return destList;
    }

    /**
     * @author Liu,ZhiXiang
     * @version 2014-01-09
     * @desc 进销存定时结算
     */
    @Resource
    private JsTimesDao jsTimesDao;

    @Resource
    private JsSellsDao jsSellsDao;

    @Resource
    private JsStocksDao jsStocksDao;

    @Resource
    private JBaseStoreDao jBaseStoreDao;

    @Resource
    private EcMessageDao ecMessageDao;

    @Resource
    private PshowOrdeAuditDao pshowOrdeAuditDao;

    public void setJsTimesDao(JsTimesDao jsTimesDao) {
        this.jsTimesDao = jsTimesDao;
    }

    public void setJsSellsDao(JsSellsDao jsSellsDao) {
        this.jsSellsDao = jsSellsDao;
    }

    public void setJsStocksDao(JsStocksDao jsStocksDao) {
        this.jsStocksDao = jsStocksDao;
    }

    public void setjBaseStoreDao(JBaseStoreDao jBaseStoreDao) {
        this.jBaseStoreDao = jBaseStoreDao;
    }

    @Override
    public void createAutoJsTimes() {
        if(checkStatus("createAutoJsTimes")){
            // 添加记录日志
            LogOfJob log = new LogOfJob();
            Date start_time = new Date();

            try {
                List<JsTimes> jsTimesList = this.jsTimesDao.selectJsTimesListForCId(new JsTimes());
                if (null != jsTimesList && jsTimesList.size() > 0) {
                    for (JsTimes j : jsTimesList) {
                        if (j.getC_id() != null) {
                            this.createAutoJsTimesForCId(j.getC_id());
                        }
                    }
                }
                log.setJob_status("成功");
            } catch (Exception e) {
                log.setJob_status("失败");
                log.setComments(e.getMessage());
                e.printStackTrace();
            }
            Date end_time = new Date();
            log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
            log.setJob_type("进销存结算");
            log.setStart_time(start_time);
            log.setEnd_time(end_time);
            log.setLink_table("JS_TIMES");
            log.setOper_user_id("00000");
            log.setOper_real_name("系统");
            log.setOper_user_ip("127.0.0.1");
            log.setOper_fun("createAutoJsTimes");
            this.facade.getLogOfJobService().createLogOfJob(log);
        }
    }

    /**
     * 以客户为单位,更新进项与销项
     *
     * @param c_id 客户ID
     */
    @Override
    public void createAutoJsTimesForCId(Long c_id) {

        // 已降序排
        JsTimes t = new JsTimes();
        t.setC_id(c_id);
        List<JsTimes> jsTimesList = this.jsTimesDao.selectEntityList(t);

        JBaseStore jBaseStore = new JBaseStore();
        jBaseStore.setC_id(c_id);
        jBaseStore.setIs_del(0);
        jBaseStore.setStore_name("总库");
        List<JBaseStore> jBaseStoreList = this.jBaseStoreDao.selectEntityList(jBaseStore);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String today = df.format(new Date());

        if (jsTimesList != null && jsTimesList.size() > 0 && jBaseStoreList != null && jBaseStoreList.size() > 0) {
            t = jsTimesList.get(0);
            jBaseStore = jBaseStoreList.get(0);
            // 非客户ID
            Long store_id = jBaseStore.getStore_id();
            // 上次结算的时间
            String day_last = df.format(t.getJs_date());
            // ---------------------------进项处理-----------------------
            String sql_push = "";
            // --------------R3订单
            String sql_push_1 = " SELECT t_.* FROM (SELECT (SELECT j.STORE_ID FROM J_BASE_STORE j WHERE j.IS_DEL = 0 ";
            sql_push_1 += " AND j.STORE_NAME = '总库' AND j.C_ID = (SELECT a.ID FROM KONKA_R3_SHOP a WHERE a.R3_CODE = t.COLUMN_1) AND rownum < 2) AS STORE_ID, ";
            sql_push_1 += " (SELECT a.ID FROM KONKA_R3_SHOP a WHERE a.R3_CODE = t.COLUMN_1) AS C_ID, ";
            sql_push_1 += " t.COLUMN_11 AS MD_NAME, ";
            sql_push_1 += " value (t.COLUMN_12, 0) AS STOCKS, ";
            sql_push_1 += " value (t.COLUMN_14, 0) AS TOTAL_COST, ";
            sql_push_1 += " value (t.COLUMN_13, 0) AS COST, ";
            sql_push_1 += " 1 AS BUY_TYPE, ";
            sql_push_1 += " t.COLUMN_7 AS ADD_DATE, ";
            sql_push_1 += " t.ID AS LINK_ID, ";
            sql_push_1 += " 'CHANNEL_DATA_IMPORT' AS LINK_TAB ";
            sql_push_1 += " FROM CHANNEL_DATA_IMPORT t WHERE t.COLUMN_9 != 'ZFRE' ";

            // 时间范围
            sql_push_1 += " AND t.COLUMN_7 > to_date('" + day_last + "','yyyy-MM-dd hh24:mi:ss') ";
            sql_push_1 += " AND t.COLUMN_7 <= to_date('" + today + "','yyyy-MM-dd hh24:mi:ss') ";

            sql_push_1 += " ) t_ WHERE 1 = 1 AND t_.STORE_ID IS NOT NULL AND t_.C_ID IS NOT NULL ";

            // 客户ID(JS_TIMES)
            sql_push_1 += " AND t_.C_ID = " + c_id;
            sql_push_1 += " ORDER BY t_.C_ID ASC,t_.STORE_ID ASC,t_.MD_NAME ASC,t_.ADD_DATE ASC,t_.LINK_ID ASC ";

            // ---------------盘盈
            String sql_push_3 = " SELECT t_.STORE_ID,t_.C_ID,t_.MD_NAME,t_.STOCKS,t_.TOTAL_COST,decode (t_.STOCKS,0,0,round(t_.TOTAL_COST / t_.STOCKS,2)) AS COST, ";
            sql_push_3 += " t_.BUY_TYPE,t_.ADD_DATE,t_.LINK_ID,t_.LINK_TAB ";
            sql_push_3 += " FROM (SELECT (SELECT j.STORE_ID FROM J_BASE_STORE j WHERE j.IS_DEL = 0 AND j.STORE_NAME = '总库'  AND j.C_ID = a.C_ID AND rownum < 2) AS STORE_ID, ";
            sql_push_3 += " a.C_ID,(SELECT jb.GOODS_NAME FROM J_BASE_GOODS jb WHERE jb.IS_KONKA = 1 AND jb.C_ID = a.C_ID AND jb.GOODS_ID = a.GOODS_ID AND rownum < 2) AS MD_NAME, ";
            sql_push_3 += " value (a.VER_STOCKS, 0) AS STOCKS,(SELECT value (sum (b.GOODS_COST), 0) FROM J_STOCKS_STACK b WHERE a.BILL_SN = b.BILL_ID_PUSH) AS TOTAL_COST, ";
            sql_push_3 += " 3 AS BUY_TYPE,a.ADD_DATE AS ADD_DATE,a.ID AS LINK_ID,'J_STOCKS_VERIFY' AS LINK_TAB ";
            sql_push_3 += " FROM J_STOCKS_VERIFY a WHERE a.TRADE_TYPE = 31 ";
            sql_push_3 += " AND a.GOODS_ID IN (SELECT jb.GOODS_ID FROM J_BASE_GOODS jb WHERE jb.IS_KONKA = 1 AND jb.C_ID = a.C_ID) ";

            // 时间范围
            sql_push_3 += " AND a.ADD_DATE > to_date('" + day_last + "','yyyy-MM-dd hh24:mi:ss') ";
            sql_push_3 += " AND a.ADD_DATE <= to_date('" + today + "','yyyy-MM-dd hh24:mi:ss') ";
            // 客户ID(JS_TIMES)
            sql_push_3 += " AND a.C_ID = " + c_id;

            sql_push_3 += " ) t_ WHERE 1 = 1 AND t_.STORE_ID IS NOT NULL AND t_.C_ID IS NOT NULL ";
            sql_push_3 += " ORDER BY t_.C_ID ASC,t_.STORE_ID ASC,t_.MD_NAME ASC,t_.ADD_DATE ASC,t_.LINK_ID ASC ";

            // ---------------进货
            String sql_push_4 =
                    " SELECT t_.* FROM (SELECT (SELECT j.STORE_ID FROM J_BASE_STORE j WHERE j.IS_DEL = 0 AND j.STORE_NAME = '总库' AND j.C_ID = a.C_ID AND rownum < 2) AS STORE_ID, ";
            sql_push_4 += " a.C_ID,(SELECT jb.GOODS_NAME FROM J_BASE_GOODS jb WHERE jb.IS_KONKA = 1 AND jb.C_ID = a.C_ID AND jb.GOODS_ID = b.GOODS_ID AND rownum < 2) AS MD_NAME, ";
            sql_push_4 += " value (b.NUM, 0) AS STOCKS,value (b.MONEY, 0) AS TOTAL_COST,value (b.PRICE, 0) AS COST,4 AS BUY_TYPE, ";
            sql_push_4 += " a.ADD_DATE AS ADD_DATE,b.BILL_ITEM_ID AS LINK_ID,'J_BILL_DETAILS' AS LINK_TAB ";
            sql_push_4 += " FROM J_BILL a, J_BILL_DETAILS b WHERE a.BILL_TYPE = 10 AND a.BILL_ID = b.BILL_ID ";
            sql_push_4 += " AND b.GOODS_ID IN (SELECT jb.GOODS_ID FROM J_BASE_GOODS jb WHERE jb.IS_KONKA = 1 AND jb.C_ID = a.C_ID) ";

            // 时间范围
            sql_push_4 += " AND a.ADD_DATE > to_date('" + day_last + "','yyyy-MM-dd hh24:mi:ss') ";
            sql_push_4 += " AND a.ADD_DATE <= to_date('" + today + "','yyyy-MM-dd hh24:mi:ss') ";
            // 客户ID(JS_TIMES)
            sql_push_4 += " AND a.C_ID = " + c_id;

            sql_push_4 += " ) t_ WHERE 1 = 1 AND t_.STORE_ID IS NOT NULL AND t_.C_ID IS NOT NULL ";
            sql_push_4 += " ORDER BY t_.C_ID ASC,t_.STORE_ID ASC,t_.MD_NAME ASC,t_.ADD_DATE ASC,t_.LINK_ID ASC ";

            // -----------------零售退货
            String sql_push_5 =
                    " SELECT t_.* FROM (SELECT (SELECT j.STORE_ID FROM J_BASE_STORE j WHERE j.IS_DEL = 0 AND j.STORE_NAME = '总库' AND j.C_ID = a.C_ID AND rownum < 2) AS STORE_ID, ";
            sql_push_5 += " a.C_ID,(SELECT jb.GOODS_NAME FROM J_BASE_GOODS jb WHERE jb.IS_KONKA = 1 AND jb.C_ID = a.C_ID AND jb.GOODS_ID = b.GOODS_ID AND rownum < 2) AS MD_NAME, ";
            sql_push_5 += " abs (value (b.NUM, 0)) AS STOCKS,abs (value (b.NUM, 0)) * abs (value (b.COST, 0)) AS TOTAL_COST,abs (value (b.COST, 0)) AS COST, ";
            sql_push_5 += " 5 AS BUY_TYPE,a.ADD_DATE AS ADD_DATE,b.BILL_ITEM_ID AS LINK_ID,'J_BILL_DETAILS' AS LINK_TAB ";
            sql_push_5 += " FROM J_BILL a, J_BILL_DETAILS b WHERE a.BILL_TYPE = 21 AND a.BILL_ID = b.BILL_ID ";
            sql_push_5 += " AND b.GOODS_ID IN (SELECT jb.GOODS_ID FROM J_BASE_GOODS jb WHERE jb.IS_KONKA = 1 AND jb.C_ID = a.C_ID) ";

            // 时间范围
            sql_push_5 += " AND a.ADD_DATE > to_date('" + day_last + "','yyyy-MM-dd hh24:mi:ss') ";
            sql_push_5 += " AND a.ADD_DATE <= to_date('" + today + "','yyyy-MM-dd hh24:mi:ss') ";
            // 客户ID(JS_TIMES)
            sql_push_5 += " AND a.C_ID = " + c_id;

            sql_push_5 += " ) t_ WHERE 1 = 1 AND t_.STORE_ID IS NOT NULL AND t_.C_ID IS NOT NULL ";
            sql_push_5 += " ORDER BY t_.C_ID ASC,t_.STORE_ID ASC,t_.MD_NAME ASC,t_.ADD_DATE ASC,t_.LINK_ID ASC ";

            // -----------------零售手机端开单退货
            String sql_push_6 =
                    " SELECT t_.* FROM (SELECT (SELECT j.STORE_ID FROM J_BASE_STORE j WHERE j.IS_DEL = 0 AND j.STORE_NAME = '总库' AND j.C_ID = a.CUSTOMER_ID AND rownum < 2) AS STORE_ID,";
            sql_push_6 += " a.CUSTOMER_ID AS C_ID,a.MODEL_NAME AS MD_NAME,abs (value (a.NUM, 0)) AS STOCKS,abs (value (a.ALL_PRICE, 0)) AS TOTAL_COST, ";
            sql_push_6 += " value (a.SINGLE_PRICE, 0) AS COST,6 AS BUY_TYPE,a.SALE_DATE AS ADD_DATE,a.ID AS LINK_ID,'KONKA_MOBILE_SAIL_DATA' AS LINK_TAB ";
            sql_push_6 += " FROM KONKA_MOBILE_SAIL_DATA a WHERE a.NUM < 0 AND a.IS_DEL = 0 ";

            // 时间范围
            sql_push_6 += " AND a.SALE_DATE > to_date('" + day_last + "','yyyy-MM-dd hh24:mi:ss') ";
            sql_push_6 += " AND a.SALE_DATE <= to_date('" + today + "','yyyy-MM-dd hh24:mi:ss') ";
            // 客户ID(JS_TIMES)
            sql_push_6 += " AND a.CUSTOMER_ID = " + c_id;

            sql_push_6 += " ) t_ WHERE 1 = 1 AND t_.STORE_ID IS NOT NULL AND t_.C_ID IS NOT NULL ";
            sql_push_6 += " ORDER BY t_.C_ID ASC,t_.STORE_ID ASC,t_.MD_NAME ASC,t_.ADD_DATE ASC,t_.LINK_ID ASC ";

            // -----------------专卖店开单退货
            String sql_push_7 =
                    " SELECT t_.* FROM (SELECT (SELECT j.STORE_ID FROM J_BASE_STORE j WHERE j.IS_DEL = 0 AND j.STORE_NAME = '总库' AND j.C_ID = (SELECT krs.ID FROM KONKA_R3_SHOP krs WHERE krs.R3_CODE = (SELECT kxz.R3_ID FROM KONKA_XX_ZMD kxz WHERE kxz.ZMD_ID = a.ZMD_ID AND rownum < 2) AND rownum < 2) AND rownum < 2) AS STORE_ID, ";
            sql_push_7 +=
                    " (SELECT krs.ID FROM KONKA_R3_SHOP krs WHERE krs.R3_CODE = (SELECT kxz.R3_ID FROM KONKA_XX_ZMD kxz WHERE kxz.ZMD_ID = a.ZMD_ID AND rownum < 2) AND rownum < 2) AS C_ID, ";
            sql_push_7 += " b.MD_NAME AS MD_NAME,value (b.COUNTS, 0) AS STOCKS,value (b.COUNTS, 0) * value (b.PRICE, 0) AS TOTAL_COST,value (b.PRICE, 0) AS COST, ";
            sql_push_7 += " 7 AS BUY_TYPE,a.SELL_DATE AS ADD_DATE,b.SELL_BILL_DETAILS_ID AS LINK_ID,'KONKA_XX_SELL_BILL_DETAILS' AS LINK_TAB ";
            sql_push_7 += " FROM KONKA_XX_SELL_BILL a, KONKA_XX_SELL_BILL_DETAILS b WHERE a.SELL_STATE = 70 AND a.RETURN_STATE = 1 AND a.SELL_BILL_ID = b.SELL_BILL_ID ";

            // 时间范围
            sql_push_7 += " AND a.SELL_DATE > to_date('" + day_last + "','yyyy-MM-dd hh24:mi:ss') ";
            sql_push_7 += " AND a.SELL_DATE <= to_date('" + today + "','yyyy-MM-dd hh24:mi:ss') ";

            sql_push_7 += " ) t_ WHERE 1 = 1 AND t_.STORE_ID IS NOT NULL AND t_.C_ID IS NOT NULL ";
            // 客户ID(JS_TIMES)
            sql_push_7 += " AND t_.C_ID = " + c_id;

            sql_push_7 += " ORDER BY t_.C_ID ASC,t_.STORE_ID ASC,t_.MD_NAME ASC,t_.ADD_DATE ASC,t_.LINK_ID ASC ";

            sql_push =
                    "(" + sql_push_1 + ")" + " UNION ALL " + "(" + sql_push_3 + ")" + " UNION ALL " + "(" + sql_push_4 + ")" + " UNION ALL " + "(" + sql_push_5 + ")"
                            + " UNION ALL " + "(" + sql_push_6 + ")" + " UNION ALL " + "(" + sql_push_7 + ")";

            //
            // //System.out.println("sql_push_1:" + sql_push_1 + ";");
            // //System.out.println("sql_push_3:" + sql_push_3 + ";");
            // //System.out.println("sql_push_4:" + sql_push_4 + ";");
            // //System.out.println("sql_push_5:" + sql_push_5 + ";");
            // //System.out.println("sql_push_6:" + sql_push_6 + ";");
            // //System.out.println("sql_push_7:" + sql_push_7 + ";");
            // //System.out.println("sql_push:" + sql_push);

            sql_push =
                    " select SEQ_JS_STOCKS.nextval,aa.*,aa.STOCKS as NUM,aa.TOTAL_COST as MONEY from (" + sql_push
                            + ") aa where 1 = 1 order by aa.C_ID ASC,aa.STORE_ID ASC,aa.MD_NAME ASC,aa.ADD_DATE ASC ";

            sql_push = " insert into JS_STOCKS (ID,STORE_ID,C_ID,MD_NAME,STOCKS,TOTAL_COST,COST,BUY_TYPE,ADD_DATE,LINK_ID,LINK_TAB,NUM,MONEY) " + sql_push;

            this.facade.getBaseReportService().createDataForSql(sql_push);

            // ---------------------------进项处理-----------------------

            // ---------------------------销项处理-----------------------
            String sql_pop = "";
            // 零售通零售数据
            String sql_pop_1 = " SELECT t_.* FROM (SELECT a.CUSTOMER_ID AS C_ID,a.MODEL_NAME AS MD_NAME,value (a.NUM, 0) AS SELL_COUNT, ";
            sql_pop_1 += " value (a.ALL_PRICE, 0) AS SELL_MONEY,value (a.SINGLE_PRICE, 0) AS SELL_PRICE,a.SALE_DATE AS SELL_DATE, ";
            sql_pop_1 += " 1 AS SELL_TYPE,a.ID AS LINK_ID,'KONKA_MOBILE_SAIL_DATA' AS LINK_TAB ";
            sql_pop_1 += " FROM KONKA_MOBILE_SAIL_DATA a WHERE a.NUM >= 0 AND a.IS_DEL = 0 ";

            // 时间范围
            sql_pop_1 += " AND a.SALE_DATE > to_date('" + day_last + "','yyyy-MM-dd hh24:mi:ss') ";
            sql_pop_1 += " AND a.SALE_DATE <= to_date('" + today + "','yyyy-MM-dd hh24:mi:ss') ";
            // 客户ID(JS_TIMES)
            sql_pop_1 += " AND a.CUSTOMER_ID = " + c_id;

            sql_pop_1 += " ) t_ WHERE 1 = 1 AND t_.C_ID IS NOT NULL ";
            sql_pop_1 += " ORDER BY t_.C_ID ASC,t_.MD_NAME ASC,t_.SELL_DATE ASC,t_.LINK_ID ASC ";

            // 进销存零售开单
            String sql_pop_2 =
                    " SELECT t_.* FROM (SELECT a.C_ID,(SELECT jb.GOODS_NAME FROM J_BASE_GOODS jb WHERE jb.IS_KONKA = 1 AND jb.C_ID = a.C_ID AND jb.GOODS_ID = b.GOODS_ID AND rownum < 2) AS MD_NAME, ";
            sql_pop_2 += " value (b.NUM, 0) AS SELL_COUNT,value (b.MONEY, 0) AS SELL_MONEY,value (b.PRICE, 0) AS SELL_PRICE,a.ADD_DATE AS SELL_DATE,2 AS SELL_TYPE, ";
            sql_pop_2 += " b.BILL_ITEM_ID AS LINK_ID,'J_BILL_DETAILS' AS LINK_TAB ";
            sql_pop_2 += " FROM J_BILL a, J_BILL_DETAILS b WHERE a.BILL_TYPE = 20 AND a.BILL_ID = b.BILL_ID ";
            sql_pop_2 += " AND b.GOODS_ID IN (SELECT jb.GOODS_ID FROM J_BASE_GOODS jb WHERE jb.IS_KONKA = 1 AND jb.C_ID = a.C_ID) ";

            // 时间范围
            sql_pop_2 += " AND a.ADD_DATE > to_date('" + day_last + "','yyyy-MM-dd hh24:mi:ss') ";
            sql_pop_2 += " AND a.ADD_DATE <= to_date('" + today + "','yyyy-MM-dd hh24:mi:ss') ";
            // 客户ID(JS_TIMES)
            sql_pop_2 += " AND a.C_ID = " + c_id;

            sql_pop_2 += " ) t_ WHERE 1 = 1 AND t_.C_ID IS NOT NULL ";
            sql_pop_2 += " ORDER BY t_.C_ID ASC,t_.MD_NAME ASC,t_.SELL_DATE ASC,t_.LINK_ID ASC ";

            // 专卖店开单
            String sql_pop_3 =
                    " SELECT t_.* FROM (SELECT (SELECT krs.ID FROM KONKA_R3_SHOP krs WHERE krs.R3_CODE = (SELECT kxz.R3_ID FROM KONKA_XX_ZMD kxz WHERE kxz.ZMD_ID = a.ZMD_ID AND rownum < 2) AND rownum < 2) AS C_ID, ";
            sql_pop_3 += " b.MD_NAME AS MD_NAME,value (b.COUNTS, 0) AS SELL_COUNT,value (b.COUNTS, 0) * value (b.PRICE, 0) AS SELL_MONEY, ";
            sql_pop_3 += " value (b.PRICE, 0) AS SELL_PRICE,a.SELL_DATE,3 AS SELL_TYPE,b.SELL_BILL_DETAILS_ID AS LINK_ID,'KONKA_XX_SELL_BILL_DETAILS' AS LINK_TAB ";
            sql_pop_3 += " FROM KONKA_XX_SELL_BILL a, KONKA_XX_SELL_BILL_DETAILS b WHERE a.SELL_STATE = 70 AND a.RETURN_STATE = 0 AND a.SELL_BILL_ID = b.SELL_BILL_ID ";

            // 时间范围
            sql_pop_3 += " AND a.SELL_DATE > to_date('" + day_last + "','yyyy-MM-dd hh24:mi:ss') ";
            sql_pop_3 += " AND a.SELL_DATE <= to_date('" + today + "','yyyy-MM-dd hh24:mi:ss') ";

            sql_pop_3 += " ) t_ WHERE 1 = 1 AND t_.C_ID IS NOT NULL ";

            // 客户ID(JS_TIMES)
            sql_pop_3 += " AND t_.C_ID = " + c_id;

            sql_pop_3 += " ORDER BY t_.C_ID ASC,t_.MD_NAME ASC,t_.SELL_DATE ASC,t_.LINK_ID ASC ";

            // String sql_pop_4 = "";

            // 盘亏
            String sql_pop_5 = " SELECT t_.C_ID,t_.MD_NAME,t_.SELL_COUNT,t_.SELL_MONEY,decode (t_.SELL_COUNT,0,0,round(t_.SELL_MONEY / t_.SELL_COUNT,2)) AS SELL_PRICE, ";
            sql_pop_5 += " t_.SELL_DATE,t_.SELL_TYPE,t_.LINK_ID,t_.LINK_TAB ";
            sql_pop_5 +=
                    " FROM (SELECT a.C_ID,a.VER_STOCKS AS SELL_COUNT,(SELECT value (sum (b.GOODS_COST), 0) FROM J_STOCKS_STACK b WHERE a.BILL_SN = b.BILL_ID_POP) AS SELL_MONEY, ";
            sql_pop_5 += " (SELECT jb.GOODS_NAME FROM J_BASE_GOODS jb WHERE jb.IS_KONKA = 1 AND jb.C_ID = a.C_ID AND jb.GOODS_ID = a.GOODS_ID AND rownum < 2) AS MD_NAME, ";
            sql_pop_5 += " a.ADD_DATE AS SELL_DATE,5 AS SELL_TYPE,a.ID AS LINK_ID,'J_STOCKS_VERIFY' AS LINK_TAB ";
            sql_pop_5 += " FROM J_STOCKS_VERIFY a WHERE a.TRADE_TYPE = 30 AND a.GOODS_ID IN (SELECT jb.GOODS_ID FROM J_BASE_GOODS jb WHERE jb.IS_KONKA = 1 AND jb.C_ID = a.C_ID) ";

            // 时间范围
            sql_pop_5 += " AND a.ADD_DATE > to_date('" + day_last + "','yyyy-MM-dd hh24:mi:ss') ";
            sql_pop_5 += " AND a.ADD_DATE <= to_date('" + today + "','yyyy-MM-dd hh24:mi:ss') ";
            // 客户ID(JS_TIMES)
            sql_pop_5 += " AND a.C_ID = " + c_id;

            sql_pop_5 += "  ) t_ WHERE 1 = 1 AND t_.C_ID IS NOT NULL ";
            sql_pop_5 += " ORDER BY t_.C_ID ASC,t_.MD_NAME ASC,t_.SELL_DATE ASC,t_.LINK_ID ASC ";

            // 进货退货
            String sql_pop_6 =
                    " SELECT t_.* FROM (SELECT a.C_ID,(SELECT jb.GOODS_NAME FROM J_BASE_GOODS jb WHERE jb.IS_KONKA = 1 AND jb.C_ID = a.C_ID AND jb.GOODS_ID = b.GOODS_ID AND rownum < 2) AS MD_NAME, ";
            sql_pop_6 += " value (b.NUM, 0) AS SELL_COUNT,value (b.MONEY, 0) AS SELL_MONEY,value (b.PRICE, 0) AS SELL_PRICE,a.ADD_DATE AS SELL_DATE, ";
            sql_pop_6 += " 6 AS SELL_TYPE,b.BILL_ITEM_ID AS LINK_ID,'J_BILL_DETAILS' AS LINK_TAB ";
            sql_pop_6 += " FROM J_BILL a, J_BILL_DETAILS b WHERE a.BILL_TYPE = 11 AND a.BILL_ID = b.BILL_ID ";
            sql_pop_6 += " AND b.GOODS_ID IN (SELECT jb.GOODS_ID FROM J_BASE_GOODS jb WHERE jb.IS_KONKA = 1 AND jb.C_ID = a.C_ID) ";

            // 时间范围
            sql_pop_6 += " AND a.ADD_DATE > to_date('" + day_last + "','yyyy-MM-dd hh24:mi:ss') ";
            sql_pop_6 += " AND a.ADD_DATE <= to_date('" + today + "','yyyy-MM-dd hh24:mi:ss') ";
            // 客户ID(JS_TIMES)
            sql_pop_6 += " AND a.C_ID = " + c_id;

            sql_pop_6 += " ) t_ WHERE 1 = 1 AND t_.C_ID IS NOT NULL ";
            sql_pop_6 += " ORDER BY t_.C_ID ASC,t_.MD_NAME ASC,t_.SELL_DATE ASC,t_.LINK_ID ASC ";

            // R3订单退货（ZFRE的订单）
            String sql_pop_7 = " SELECT t_.* FROM (SELECT (SELECT a.ID FROM KONKA_R3_SHOP a WHERE a.R3_CODE = t.COLUMN_1) AS C_ID, ";
            sql_pop_7 += " t.COLUMN_11 AS MD_NAME,abs (value (t.COLUMN_12, 0)) AS SELL_COUNT,abs (value (t.COLUMN_14, 0)) AS SELL_MONEY, ";
            sql_pop_7 += " abs (value (t.COLUMN_13, 0)) AS SELL_PRICE,t.COLUMN_7 AS SELL_DATE,7 AS SELL_TYPE,t.ID AS LINK_ID,'CHANNEL_DATA_IMPORT' AS LINK_TAB ";
            sql_pop_7 += " FROM CHANNEL_DATA_IMPORT t WHERE t.COLUMN_9 = 'ZFRE' ";

            // 时间范围
            sql_pop_7 += " AND t.COLUMN_7 > to_date('" + day_last + "','yyyy-MM-dd hh24:mi:ss') ";
            sql_pop_7 += " AND t.COLUMN_7 <= to_date('" + today + "','yyyy-MM-dd hh24:mi:ss') ";

            sql_pop_7 += " ) t_ WHERE 1 = 1 AND t_.C_ID IS NOT NULL ";
            // 客户ID(JS_TIMES)
            sql_pop_7 += " AND t_.C_ID = " + c_id;

            sql_pop_7 += " ORDER BY t_.C_ID ASC,t_.MD_NAME ASC,t_.SELL_DATE ASC,t_.LINK_ID ASC ";

            sql_pop =
                    "(" + sql_pop_1 + ")" + " UNION ALL " + "(" + sql_pop_2 + ")" + " UNION ALL " + "(" + sql_pop_3 + ")" + " UNION ALL " + "(" + sql_pop_5 + ")" + " UNION ALL "
                            + "(" + sql_pop_6 + ")" + " UNION ALL " + "(" + sql_pop_7 + ")";

            sql_pop = " select aa.* from (" + sql_pop + ") aa where 1 = 1 order by aa.C_ID ASC,aa.MD_NAME ASC,aa.SELL_DATE ASC ";

            //
            // //System.out.println("sql_pop_1:" + sql_pop_1 + ";");
            // //System.out.println("sql_pop_2:" + sql_pop_2 + ";");
            // //System.out.println("sql_pop_3:" + sql_pop_3 + ";");
            // //System.out.println("sql_pop_5:" + sql_pop_5 + ";");
            // //System.out.println("sql_pop_6:" + sql_pop_6 + ";");
            // //System.out.println("sql_pop_7:" + sql_pop_7 + ";");
            //
            // //System.out.println("sql_pop:" + sql_pop + ";");

            // 销项
            List<?> list_sell = this.facade.getBaseReportService().getBaseReportForArray(sql_pop);
            // C_ID MD_NAME SELL_COUNT SELL_MONEY SELL_PRICE SELL_DATE SELL_TYPE
            // LINK_ID LINK_TAB
            if (null != list_sell && list_sell.size() > 0) {
                for (int i = 0; i < list_sell.size(); i++) {
                    Object[] obj = (Object[]) list_sell.get(i);
                    JsSells jsSells = new JsSells();
                    jsSells.setC_id(new Long(obj[0].toString()));
                    // modelName 有可能为空,0上报时,机型可以不填
                    if (obj[1] != null) jsSells.setMd_name(obj[1].toString());
                    jsSells.setSell_count(new Long(obj[2].toString()));
                    jsSells.setSell_money(new BigDecimal(obj[3].toString()));
                    jsSells.setSell_price(new BigDecimal(obj[4].toString()));
                    try {
                        jsSells.setSell_date(df.parse(obj[5].toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    jsSells.setSell_type(Integer.valueOf(obj[6].toString()));
                    jsSells.setLink_id(new Long(obj[7].toString()));
                    jsSells.setLink_tab(obj[8].toString());

                    // 某客户某机型的剩余库存
                    JsStocks jsStocks = new JsStocks();
                    jsStocks.setC_id(jsSells.getC_id());
                    jsStocks.setStore_id(store_id);
                    jsStocks.setMd_name(jsSells.getMd_name());
                    jsStocks.getMap().put("stocks_gt", 0);

                    Long total_stocks = this.jsStocksDao.selectJsStockForSumStocks(jsStocks);

                    if (total_stocks > 0) {

                        BigDecimal total_cost = new BigDecimal("0");
                        List<JsStocks> jsStocksList = this.jsStocksDao.selectEntityList(jsStocks);

                        // 销量已经大于库存
                        if (jsSells.getSell_count() >= total_stocks) {
                            // 该客户库存中，此型号产品不足
                            for (JsStocks j : jsStocksList) {
                                total_cost = total_cost.add(j.getTotal_cost());
                                j.setStocks(0L);
                                j.setTotal_cost(new BigDecimal("0"));
                                // 更新机型库存为0
                                this.jsStocksDao.updateEntity(j);
                            }

                            // ? insert
                            jsSells.setTotal_cost(total_cost);
                            this.jsSellsDao.insertEntity(jsSells);

                        } else {
                            // 销量已经小于库存
                            Long sell_count = jsSells.getSell_count();
                            for (JsStocks j : jsStocksList) {
                                if (sell_count > 0) {
                                    if (j.getStocks() > sell_count) {

                                        // 数量*金额
                                        BigDecimal sell_cost = new BigDecimal(sell_count).multiply(j.getCost()).setScale(2, BigDecimal.ROUND_HALF_UP);
                                        total_cost = total_cost.add(sell_cost);
                                        j.setStocks(j.getStocks() - sell_count);
                                        j.setTotal_cost(j.getTotal_cost().subtract(sell_cost));
                                        this.jsStocksDao.updateEntity(j);
                                        sell_count = 0L;
                                        break;
                                    } else {
                                        sell_count = sell_count - j.getStocks();
                                        total_cost = total_cost.add(j.getTotal_cost());
                                        j.setStocks(0L);
                                        j.setTotal_cost(new BigDecimal("0"));
                                        this.jsStocksDao.updateEntity(j);
                                    }
                                } else {
                                    // ?
                                    break;
                                }
                            }
                            // ? insert
                            jsSells.setTotal_cost(total_cost);
                            this.jsSellsDao.insertEntity(jsSells);
                        }
                    } else {
                        // 该客户库存中，已无此型号产品
                        jsSells.setTotal_cost(new BigDecimal(0));
                        this.jsSellsDao.insertEntity(jsSells);
                    }
                }
            }

            // ---------------------------销项处理-----------------------

            // ---------------------------结算时间点处理-----------------------
            JsTimes jsTimes = new JsTimes();
            jsTimes.setC_id(c_id);
            try {
                jsTimes.setJs_date(df.parse(today));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.jsTimesDao.insertEntity(jsTimes);
            // ---------------------------结算时间点处理-----------------------

        } else if ((jsTimesList == null || jsTimesList.size() <= 0) && jBaseStoreList != null && jBaseStoreList.size() > 0) {
            // 客户库存初始化
            jBaseStore = jBaseStoreList.get(0);
            Long store_id = jBaseStore.getStore_id();

            // String sql = " SELECT a.STORE_ID," + c_id
            // +
            // " AS C_ID,(SELECT jb.GOODS_NAME FROM J_BASE_GOODS jb WHERE jb.IS_KONKA = 1 AND jb.C_ID = "
            // + c_id
            // + " AND jb.GOODS_ID = a.GOODS_ID AND rownum < 2) AS MD_NAME, ";
            // sql +=
            // " value (a.STOCKS, 0) AS STOCKS,value (a.TOTAL_COST, 0) AS TOTAL_COST,decode (value (a.STOCKS, 0),0,0,round (value (a.TOTAL_COST, 0) / value (a.STOCKS, 0), 2)) AS COST, ";
            // sql += " -1 AS BUY_TYPE,to_date('" + today +
            // "','yyyy-MM-dd hh24:mi:ss') AS ADD_DATE, ";
            // sql += " -1 AS LINK_ID,'J_STOCKS' AS LINK_TAB ";
            // sql +=
            // " FROM J_STOCKS a WHERE a.STORE_ID IN (SELECT j.STORE_ID FROM J_BASE_STORE j WHERE j.IS_DEL = 0 AND j.STORE_NAME = '总库' AND j.C_ID = "
            // + c_id + " AND rownum < 2) ";
            // sql +=
            // " AND a.GOODS_ID IN (SELECT jb.GOODS_ID FROM J_BASE_GOODS jb WHERE jb.IS_KONKA = 1 AND jb.C_ID = "
            // + c_id + ") AND a.STOCKS > 0 ";

            String sql =
                    " SELECT SEQ_JS_STOCKS.nextval,a.STORE_ID,a.C_ID,(SELECT jb.GOODS_NAME FROM J_BASE_GOODS jb WHERE jb.IS_KONKA = 1 AND jb.C_ID = a.C_ID AND jb.GOODS_ID = a.GOODS_ID AND rownum < 2) AS MD_NAME, ";
            sql += " 1 AS STOCKS,a.GOODS_COST AS TOTAL_COST,a.GOODS_COST AS COST,-1 AS BUY_TYPE,to_date('" + today + "','yyyy-MM-dd hh24:mi:ss') AS ADD_DATE, ";
            sql += " a.STACK_ID AS LINK_ID,'J_STOCKS_STACK' AS LINK_TAB,1 AS NUM,a.GOODS_COST AS MONEY ";
            sql += " FROM J_STOCKS_STACK a WHERE a.STATUS = 0 AND a.C_ID = " + c_id;
            sql += " AND a.STORE_ID IN (SELECT j.STORE_ID FROM J_BASE_STORE j WHERE j.IS_DEL = 0 AND j.STORE_NAME = '总库' AND j.C_ID = a.C_ID AND rownum < 2) ";
            sql += " AND a.GOODS_ID IN (SELECT jb.GOODS_ID FROM J_BASE_GOODS jb WHERE jb.IS_KONKA = 1 AND jb.C_ID = a.C_ID) ";
            sql += " ORDER BY a.STACK_ID ASC ";

            sql = " insert into JS_STOCKS (ID,STORE_ID,C_ID,MD_NAME,STOCKS,TOTAL_COST,COST,BUY_TYPE,ADD_DATE,LINK_ID,LINK_TAB,NUM,MONEY) " + sql;

            this.facade.getBaseReportService().createDataForSql(sql);

            JsTimes jsTimes = new JsTimes();
            jsTimes.setC_id(c_id);
            try {
                jsTimes.setJs_date(df.parse(today));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.jsTimesDao.insertEntity(jsTimes);
        }
    }

    @Resource
    private KonkaBbYjhbDao konkaBbYjhbDao;

    /**
     * 同步业绩划拨
     */
    @Override
    public void autoSyncYJHBReport() throws SQLException {
        // 1.销售组织
        // 2.年份，月份
        // 3.接R3数据
        // 4.数据覆盖
        if(checkStatus("autoSyncYJHBReport")){
            // 添加记录日志
            LogOfJob log = new LogOfJob();
            Date start_time = new Date();

            try {
                List<KonkaDept> fList = new ArrayList<KonkaDept>();
                KonkaDept kd = new KonkaDept();
                kd.setDept_type(3);
                fList = konkaDeptDao.selectEntityList(kd);

                List<String> salesOrgList = new ArrayList<String>();
                for (KonkaDept kk : fList) {
                    if (kk.getDept_sn() != null && !"".equals(kk.getDept_sn())) salesOrgList.add(kk.getDept_sn());
                }

                String _year = "";
                String _month = "";

                Calendar c = Calendar.getInstance();
                _year = String.valueOf(c.get(Calendar.YEAR));
                _month = String.valueOf(c.get(Calendar.MONTH) + 1);

                // 接口数据
                List<ZYJHB> yjhbList = new ArrayList<ZYJHB>();
                List<ZYJHB> yjhbAllList = new ArrayList<ZYJHB>();
                // zlb 电商 ,大客户 此参数可不传,一次性取出
                for (String sorg : salesOrgList) {
                    yjhbList = new R3daoImpl().getYJHBList(null, sorg, _year, _month);
                    if (yjhbList != null && yjhbList.size() > 0) {
                        yjhbAllList.addAll(yjhbList);
                    }
                }

                List<KonkaBbYjhb> konkaBbYjhbList = new ArrayList<KonkaBbYjhb>();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date erdat = null;
                // 接口数据转渠道系统数据
                for (ZYJHB hb : yjhbAllList) {
                    KonkaBbYjhb t = new KonkaBbYjhb();
                    t.setBukrs(hb.getBUKRS());
                    t.setButxt(hb.getBUTXT());
                    if (hb.getERDAT() != "") {
                        try {
                            erdat = sdf.parse(hb.getERDAT());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    t.setErdat(erdat);
                    t.setMandt(hb.getMANDT());
                    t.setLfgja(hb.getLFGJA());
                    t.setLfmon(hb.getLFMON());
                    t.setSynctime(new Date());
                    t.setSyncusr("1");// admin
                    t.setUname(hb.getUNAME());
                    t.setZhkje(hb.getZHKJE());
                    t.setZjsje(hb.getZJSJE());
                    t.setZjssl(hb.getZJSSL());
                    t.setZlb(hb.getZLB());
                    konkaBbYjhbList.add(t);
                }
                // 先清除
                KonkaBbYjhb t = new KonkaBbYjhb();
                for (KonkaBbYjhb x : konkaBbYjhbList) {
                    t.setBukrs(x.getBukrs());
                    t.setLfgja(x.getLfgja());
                    t.setLfmon(x.getLfmon());
                    t.setId(null);// 无法确认ID,此处按其它条件删除
                    konkaBbYjhbDao.deleteEntity(t);
                }

                konkaBbYjhbDao.insertEntityBatch(konkaBbYjhbList);
                log.setJob_status("成功");
            } catch (Exception e) {
                log.setJob_status("失败");
                log.setComments(e.getMessage());
                e.printStackTrace();
            }
            Date end_time = new Date();
            log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
            log.setJob_type("同步业绩划拨");
            log.setStart_time(start_time);
            log.setEnd_time(end_time);
            log.setLink_table("KONKA_BB_YJHB");
            log.setOper_user_id("00000");
            log.setOper_real_name("系统");
            log.setOper_user_ip("127.0.0.1");
            log.setOper_fun("autoSyncYJHBReport");
            this.facade.getLogOfJobService().createLogOfJob(log);
        }
    }

    @Override
    public void sendMessage() throws Exception {
        EcMessage ec = new EcMessage();
        ec.setSend_state(1);
        List<EcMessage> ecList = super.getFacade().getEcMessageService().getEcMessageList(ec);
        for (EcMessage ecMessage : ecList) {
            if (getSendMessageResult(ecMessage.getMobile(), ecMessage.getContent())) {
                EcMessage ecm = new EcMessage();
                ecm.setId(ecMessage.getId());
                ecm.setSend_date(new Date());
                ecm.setSend_state(0);
                ecMessageDao.updateEntity(ecm);
            } else {
                EcMessage ecm = new EcMessage();
                ecm.setId(ecMessage.getId());
                ecm.setSend_date(new Date());
                ecm.setSend_state(1);
                ecMessageDao.updateEntity(ecm);
            }
        }
    }

    @Resource
    private KonkaR3OrderDao konkaR3OrderDao;

    /**
     * 每天整理订单表（konka_order_info）数据到临时表konka_r3_order
     *
     * @author Lianghouen
     * @date 2014-6-16
     */
    @Override
    public Long createR3Order() {
        if(checkStatus("createR3Order")){
            // 添加记录日志
            LogOfJob log = new LogOfJob();
            Date start_time = new Date();

            Long result = 0L;
            try {
                // 同步统计数据
                result = this.konkaR3OrderDao.setOrderData();
                log.setJob_status("成功");
            } catch (Exception e) {
                log.setJob_status("失败");
                log.setComments(e.getMessage());
                e.printStackTrace();
            }
            Date end_time = new Date();
            log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
            log.setJob_type("更新R3订单统计数据");
            log.setStart_time(start_time);
            log.setEnd_time(end_time);
            log.setLink_table("konka_r3_order");
            log.setOper_user_id("00000");
            log.setOper_real_name("系统");
            log.setOper_user_ip("127.0.0.1");
            log.setOper_fun("createR3Order");
            this.facade.getLogOfJobService().createLogOfJob(log);
            return result;
        }
        return 0L;
    }

    @Override
    public void sendMessageForBirthday() {
        EcUser ecUser = new EcUser();
        ecUser.setIs_act(0);// 审核通过
        ecUser.setUser_type(1);// 工卡会员
        ecUser.getMap().put("message_for_birthday", true);
        List<EcUser> ecUserList = this.ecUserDao.selectEntityList(ecUser);
        SimpleDateFormat df = new SimpleDateFormat("MM-dd");
        Date now = new Date();

        List<String> mobileList = new ArrayList<String>();
        for (EcUser ecUser2 : ecUserList) {
            if (df.format(now).equals(df.format(ecUser2.getBirthday()))) {
                mobileList.add(ecUser2.getLink_phone());
            }
        }
        String mobile = StringUtils.join(mobileList, ",");
        String content = "生日到，祝福到，短信问候先送到。开心猫感谢您一路相伴！祝您：生日快乐！幸福安康！生活事业步步高！";
        try {
            if (getSendMessageResultByBatch(mobile, content)) {
                for (EcUser ecUser2 : ecUserList) {
                    if (df.format(now).equals(df.format(ecUser2.getBirthday()))) {
                        EcMessage ecm = new EcMessage();
                        ecm.setContent(content);
                        ecm.setMobile(ecUser2.getLink_phone());
                        ecm.setSend_date(new Date());
                        ecm.setSend_state(0);
                        ecMessageDao.insertEntity(ecm);
                    }
                }
            } else {
                for (EcUser ecUser2 : ecUserList) {
                    if (df.format(now).equals(df.format(ecUser2.getBirthday()))) {
                        EcMessage ecm = new EcMessage();
                        ecm.setContent(content);
                        ecm.setMobile(ecUser2.getLink_phone());
                        ecm.setSend_date(new Date());
                        ecm.setSend_state(1);
                        ecMessageDao.insertEntity(ecm);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 自动同步客户库存汇总到JStocksSummary表中
     *
     * @desc 如果r3_code为空，则遍历插入全部客户的全部商品数据
     * @throws ParseException
     */
    @Override
    public void syncJStocksSummaryForKh() {
        if(checkStatus("syncJStocksSummaryForKh")){
            operateJStocksSummary(null, 0);
        }
    }

    /**
     * 自动同步库存盘点到JStocksSummary表中
     *
     * @desc 如果r3_code为空，则遍历插入全部客户的全部商品数据
     * @throws ParseException
     */
    @Override
    public void syncJStocksSummaryForPd() {
        if(checkStatus("syncJStocksSummaryForPd")){
            operateJStocksSummary(null, 1);
        }
    }

    /**
     * 对表JStocksSummary进行操作
     *
     * @param r3_code 客户编码
     * @param type 类型
     */
    public void operateJStocksSummary(String r3_code, Integer type) {
        // 添加记录日志
        LogOfJob log = new LogOfJob();
        Date start_time = new Date();

        Date start_date = DateUtils.addMonths(new Date(), -1 * Constants.DEFAULT_MONTH_SYNC_PD_OF_R3ORDER);

        // 查询全部客户目录
        KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
        if (r3_code != null) {
            konkaR3Shop.setR3_code(r3_code);
        }
        konkaR3Shop.setIs_del(0L);
        konkaR3Shop.setIs_konka(1);
        konkaR3Shop.getMap().put("konka_kh", true);
        List<KonkaR3Shop> shopList = this.facade.getKonkaR3ShopService().getKonkaR3ShopList(konkaR3Shop);
        Date date = new Date();

        try {
            long res_num = 0;// 总共成功初始化了多少条数据
            List<JStocksSummary> summaryList = new ArrayList<JStocksSummary>();
            List<JStocksSummary> inList = new ArrayList<JStocksSummary>();
            if (shopList != null && shopList.size() > 0) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for (KonkaR3Shop oner3shop : shopList) {
                    // 获取客户R3编码
                    if (oner3shop != null) {
                        if (oner3shop.getR3_code() != null && oner3shop.getId() != null) {
                            JBaseGoods jb = new JBaseGoods();
                            jb.setC_id(oner3shop.getId());
                            // 如果该客户没有初始化过任何一件商品，则不进行商品的操作
                            if (this.facade.getJBaseGoodsService().getJBaseGoodsCount(jb) > 0) {
                                JBaseGoods jBaseGoods = new JBaseGoods();
                                jBaseGoods.getMap().put("r3_code", oner3shop.getR3_code());
                                jBaseGoods.getMap().put("r3_id", oner3shop.getId());
                                List<JBaseGoods> entityList = this.facade.getJBaseGoodsService().getJBaseGoodsForComeOutNumWithMoney(jBaseGoods);
                                if (entityList != null && entityList.size() > 0) {
                                    for (JBaseGoods goods : entityList) {
                                        if (goods != null) {
                                            JStocksSummary summary = new JStocksSummary();
                                            summary.setC_id(oner3shop.getId());
                                            summary.setR3_code(oner3shop.getR3_code());
                                            summary.setCustomer_name(oner3shop.getCustomer_name());
                                            summary.setGoods_name(goods.getMap().get("goods_name").toString());
                                            summary.setGoods_id(Long.parseLong(goods.getMap().get("goods_id").toString()));
                                            summary.setInit_num(Long.parseLong(goods.getMap().get("init_num").toString()));
                                            summary.setInit_money(new BigDecimal(goods.getMap().get("init_money").toString()));
                                            summary.setCome_num(Long.parseLong(goods.getMap().get("come_num").toString()));
                                            summary.setCome_money(new BigDecimal(goods.getMap().get("come_money").toString()));
                                            summary.setOut_num(Long.parseLong(goods.getMap().get("out_num").toString()));
                                            summary.setOut_money(new BigDecimal(goods.getMap().get("out_money").toString()));
                                            summary.setSale_cost(new BigDecimal(goods.getMap().get("sale_cost").toString()));
                                            summary.setOpr_date(df.parse(goods.getMap().get("opr_date").toString()));
                                            summary.setAdd_date(date);
                                            summary.setType(type);// 客户库存汇总
                                            // jStocksSummaryService.createJStocksSummary(summary);
                                            summaryList.add(summary);
                                            res_num++;
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }

            // zhou updated 20140929
            // 如果是客户库存汇总则删除之前的数据
            if (null != type && type == 0) {
                JStocksSummary jss = new JStocksSummary();
                jss.setType(type);
                jStocksSummaryService.removeJStocksSummary(jss);
            }

            // 执行保存 对大批量数据的保存进行分批处理
            long total = summaryList.size();
            long everyCount = 500;
            long cycelTotal = total / everyCount;
            if (total % everyCount != 0) {
                cycelTotal += 1;
            }

            for (int i = 0; i < cycelTotal; i++) {
                // 判断剩下的list数据，大于指定?
                if (summaryList.size() >= everyCount) {
                    for (int j = 0; j < everyCount; j++) {
                        if (summaryList.get(j) == null) {
                            break;
                        }
                        inList.add(summaryList.get(j));
                    }
                } else {
                    everyCount = summaryList.size();
                    for (int j = 0; j < everyCount; j++) {
                        if (summaryList.get(j) == null) {
                            break;
                        }
                        inList.add(summaryList.get(j));
                    }
                }
                // 每次循环保存的数据输出
                jStocksSummaryService.createJStocksSummaryList(inList);
                // 移出已经保存过的数据
                summaryList.removeAll(inList);
                inList.clear();// 移出当前保存的数据
            }
            log.setJob_status("成功");
            log.setComments("查询到的客户个数有：" + shopList.size() + ",理论上查询到的商品数据为" + res_num + "条！");
        } catch (ParseException e) {
            log.setJob_status("失败");
            log.setComments(e.getMessage());
            e.printStackTrace();
        }
        Date end_time = new Date();
        log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
        if (type != null && type == 0) {
            log.setJob_type("客户库存汇总");
        } else {
            log.setJob_type("盘点月度库存");
        }
        log.setStart_time(start_time);
        log.setEnd_time(end_time);
        log.setLink_table("J_STOCKS_SUMMARY");
        log.setOper_user_id("00000");
        log.setOper_real_name("系统");
        log.setOper_user_ip("127.0.0.1");
        log.setOper_fun("operateJStocksSummary");
        this.facade.getLogOfJobService().createLogOfJob(log);
    }

    /**
     * 自动同步客户分仓库存汇总到JStocksStoreSummary表中
     *
     * @desc 如果r3_code为空，则遍历插入全部客户分仓的全部商品数据
     */
    @Override
    public void syncJStocksStoreSummaryForKh() {
        if(checkStatus("syncJStocksStoreSummaryForKh")){
            operateJStocksStoreSummary(null);
        }
    }

    /**
     * 对表JStocksStoreSummary进行操作
     *
     * @param r3_code 客户编码
     */
    public void operateJStocksStoreSummary(String r3_code) {
        // 添加记录日志
        LogOfJob log = new LogOfJob();
        Date start_time = new Date();
        Date start_date = DateUtils.addMonths(new Date(), -1 * Constants.DEFAULT_MONTH_SYNC_PD_OF_R3ORDER);

        // 查询全部客户目录
        KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
        if (r3_code != null) {
            konkaR3Shop.setR3_code(r3_code);
        }
        konkaR3Shop.setIs_del(0L);
        konkaR3Shop.setIs_konka(1);
        konkaR3Shop.getMap().put("konka_kh", true);
        List<KonkaR3Shop> shopList = this.facade.getKonkaR3ShopService().getKonkaR3ShopList(konkaR3Shop);
        Date date = new Date();

        try {
            // long kh_num = 0;// 客户数量
            long res_num = 0;// 总共成功初始化了多少条数据
            List<JStocksStoreSummary> summaryList = new ArrayList<JStocksStoreSummary>();
            List<JStocksStoreSummary> inList = new ArrayList<JStocksStoreSummary>();
            if (shopList != null && shopList.size() > 0) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for (KonkaR3Shop oner3shop : shopList) {
                    // 获取客户R3编码
                    // kh_num++;
                    if (oner3shop != null) {
                        if (oner3shop.getR3_code() != null && oner3shop.getId() != null) {
                            JBaseGoods jb = new JBaseGoods();
                            jb.setC_id(oner3shop.getId());
                            // 如果该客户没有初始化过任何一件商品，则不进行商品的操作
                            if (this.facade.getJBaseGoodsService().getJBaseGoodsCount(jb) > 0) {
                                JBaseGoods jBaseGoods = new JBaseGoods();
                                jBaseGoods.getMap().put("r3_code", oner3shop.getR3_code());
                                jBaseGoods.getMap().put("r3_id", oner3shop.getId());
                                List<JBaseGoods> entityList = this.facade.getJBaseGoodsService().getJBaseGoodsForComeOutNumWithMoney2(jBaseGoods);
                                if (entityList != null && entityList.size() > 0) {
                                    for (JBaseGoods goods : entityList) {
                                        if (goods != null) {
                                            JStocksStoreSummary summary = new JStocksStoreSummary();
                                            summary.setC_id(oner3shop.getId());
                                            summary.setR3_code(oner3shop.getR3_code());
                                            summary.setCustomer_name(oner3shop.getCustomer_name());
                                            summary.setStore_id(Long.parseLong(goods.getMap().get("store_id").toString()));
                                            summary.setGoods_name(goods.getMap().get("goods_name").toString());
                                            summary.setGoods_id(Long.parseLong(goods.getMap().get("goods_id").toString()));
                                            summary.setInit_num(Long.parseLong(goods.getMap().get("init_num").toString()));
                                            summary.setInit_money(new BigDecimal(goods.getMap().get("init_money").toString()));
                                            summary.setCome_num(Long.parseLong(goods.getMap().get("come_num").toString()));
                                            summary.setCome_money(new BigDecimal(goods.getMap().get("come_money").toString()));
                                            summary.setOut_num(Long.parseLong(goods.getMap().get("out_num").toString()));
                                            summary.setOut_money(new BigDecimal(goods.getMap().get("out_money").toString()));
                                            summary.setSale_cost(new BigDecimal(goods.getMap().get("sale_cost").toString()));
                                            summary.setOpr_date(df.parse(goods.getMap().get("opr_date").toString()));
                                            summary.setAdd_date(date);
                                            summaryList.add(summary);
                                            res_num++;
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }

            // 删除之前的数据
            JStocksStoreSummary jsss = new JStocksStoreSummary();
            jStocksStoreSummaryService.removeJStocksStoreSummary(jsss);


            // 执行保存 对大批量数据的保存进行分批处理
            long total = summaryList.size();
            long everyCount = 500;
            long cycelTotal = total / everyCount;
            if (total % everyCount != 0) {
                cycelTotal += 1;
            }

            for (int i = 0; i < cycelTotal; i++) {
                // 判断剩下的list数据，大于指定?
                if (summaryList.size() >= everyCount) {
                    for (int j = 0; j < everyCount; j++) {
                        if (summaryList.get(j) == null) {
                            break;
                        }
                        inList.add(summaryList.get(j));
                    }
                } else {
                    everyCount = summaryList.size();
                    for (int j = 0; j < everyCount; j++) {
                        if (summaryList.get(j) == null) {
                            break;
                        }
                        inList.add(summaryList.get(j));
                    }
                }
                // 每次循环保存的数据输出
                jStocksStoreSummaryService.createJStocksStoreSummaryList(inList);
                // 移出已经保存过的数据
                summaryList.removeAll(inList);
                inList.clear();// 移出当前保存的数据
            }
            log.setJob_status("成功");
            log.setComments("查询到的客户个数有：" + shopList.size() + ",理论上查询到的商品数据为" + res_num + "条！");
        } catch (ParseException e) {
            log.setJob_status("失败");
            log.setComments(e.getMessage());
            e.printStackTrace();
        }
        Date end_time = new Date();
        log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
        log.setJob_type("库存分仓查询");
        log.setStart_time(start_time);
        log.setEnd_time(end_time);
        log.setLink_table("J_STOCKS_STORE_SUMMARY");
        log.setOper_user_id("00000");
        log.setOper_real_name("系统");
        log.setOper_user_ip("127.0.0.1");
        log.setOper_fun("operateJStocksStoreSummary");
        this.facade.getLogOfJobService().createLogOfJob(log);
    }

    /**
     * 业务汇总统计 小时定时器 一个小时同步 当前月 最新数据到konka_yw_hz_tj表
     */
    @Override
    public void syncKonkaYwHzTjByHour() {
        if(checkStatus("syncKonkaYwHzTjByHour")){
            // 添加记录日志
            LogOfJob log = new LogOfJob();
            Date start_time = new Date();
            try {
                Calendar calendar = Calendar.getInstance();
                KonkaYwHzTj entity = new KonkaYwHzTj();
                int month = calendar.get(Calendar.MONTH) + 1;
                String monthStr = month < 10 ? "0" + month : "" + month;
                entity.getMap().put("begin_time", calendar.get(Calendar.YEAR) + "-" + monthStr + "-" + "01" + " 00:00:00");
                int maxDay = this.day(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1);
                entity.getMap().put("end_time", calendar.get(Calendar.YEAR) + "-" + monthStr + "-" + ((maxDay < 10) ? ("0" + maxDay) : maxDay) + " 23:59:59");
                entity.getMap().put("update_time", calendar.get(Calendar.YEAR) + "-" + monthStr + "-" + "15" + " 00:00:00");
                entity.getMap().put("last_update_time", new Date());

                PeProdUser u = new PeProdUser();
                u.setIs_del(0);
                // 根据用户分次操作
                List<PeProdUser> userlist = facade.getPeProdUserService().getPeProdUserList(u);
                for (int i = 0; i < userlist.size(); i++) {
                    entity.getMap().put("user_id", userlist.get(i).getId());
                    this.konkaYwHzTjService.removeAndCreate(entity);
                }
                log.setJob_status("成功");
            } catch (Exception e) {
                log.setJob_status("失败");
                log.setComments(e.getMessage());
                e.printStackTrace();
            }
            Date end_time = new Date();
            log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
            log.setJob_type("业务汇总统计");
            log.setStart_time(start_time);
            log.setEnd_time(end_time);
            log.setLink_table("KONKA_YW_HZ_TJ");
            log.setOper_user_id("00000");
            log.setOper_real_name("系统");
            log.setOper_user_ip("127.0.0.1");
            log.setOper_fun("syncKonkaYwHzTjByHour");
            this.facade.getLogOfJobService().createLogOfJob(log);
        }
    }

    /**
     * 业务汇总统计 月定时器 每月1号三点同步所有 最新数据到konka_yw_hz_tj表
     */
    @Override
    public void syncKonkaYwHzTjByMonth() {
        if(checkStatus("syncKonkaYwHzTjByMonth")){
            // 添加记录日志
            LogOfJob log = new LogOfJob();
            Date start_time = new Date();
            try {
                Calendar cc = Calendar.getInstance();
                String year[] = new String[2];
                year[0] = String.valueOf(cc.get(Calendar.YEAR) - 1);
                year[1] = String.valueOf(cc.get(Calendar.YEAR));
                String month[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
                for (int i = 0; i < year.length; i++) {
                    for (int j = 0; j < month.length; j++) {
                        int maxDay = this.day(Integer.parseInt(year[i]), Integer.parseInt(month[j]));
                        KonkaYwHzTj entity = new KonkaYwHzTj();
                        entity.getMap().put("begin_time", year[i] + "-" + month[j] + "-" + "01" + " 00:00:00");
                        entity.getMap().put("end_time", year[i] + "-" + month[j] + "-" + ((maxDay < 10) ? ("0" + maxDay) : maxDay) + " 23:59:59");
                        entity.getMap().put("update_time", year[i] + "-" + month[j] + "-" + "15" + " 00:00:00");
                        entity.getMap().put("last_update_time", new Date());
                        List<PeProdUser> userlist = super.getFacade().getPeProdUserService().getPeProdUserList(new PeProdUser());
                        for (int k = 0; k < userlist.size(); k++) {
                            entity.getMap().put("user_id", userlist.get(k).getId());
                            this.konkaYwHzTjService.removeAndCreate(entity);
                        }
                    }
                }
                log.setJob_status("成功");
            } catch (Exception e) {
                log.setJob_status("失败");
                log.setComments(e.getMessage());
                e.printStackTrace();
            }
            Date end_time = new Date();
            log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
            log.setJob_type("业务汇总统计-月度");
            log.setStart_time(start_time);
            log.setEnd_time(end_time);
            log.setLink_table("KONKA_YW_HZ_TJ");
            log.setOper_user_id("00000");
            log.setOper_real_name("系统");
            log.setOper_user_ip("127.0.0.1");
            log.setOper_fun("syncKonkaYwHzTjByMonth");
            this.facade.getLogOfJobService().createLogOfJob(log);
        }
    }

    // 通过年月计算当前月有多少天
    private static int day(int year, int month) {
        int maxDay = 0;
        int day = 1;
        /**
         * 与其他语言环境敏感类一样，Calendar 提供了一个类方法 getInstance， 以获得此类型的一个通用的对象。Calendar 的 getInstance 方法返回一 个
         * Calendar 对象，其日历字段已由当前日期和时间初始化：
         */
        Calendar calendar = Calendar.getInstance();
        /**
         * 实例化日历各个字段,这里的day为实例化使用
         */
        calendar.set(year, month - 1, day);
        /**
         * Calendar.Date:表示一个月中的某天 calendar.getActualMaximum(int field):返回指定日历字段可能拥有的最大值
         */
        maxDay = calendar.getActualMaximum(Calendar.DATE);
        return maxDay;
    }

    /**
     * 执行插入数据的SQL语句，并且更新相应的比例数据 2014-11-07
     */
    @Override
    public void syncAutoRunUpdateStatementForDataInsertAndUpdate() {
        // 添加记录日志
        LogOfJob log = new LogOfJob();
        Date start_time = new Date();
        try {
            // 先删除过渡表中的数据
            StatisticalDimensionDataMiddle dataMiddle = new StatisticalDimensionDataMiddle();
            this.facade.getStatisticalDimensionDataMiddleService().removeStatisticalDimensionDataMiddle(dataMiddle);
            // 再删除最终表中的数据
            StatisticalDimensionData data = new StatisticalDimensionData();
            this.facade.getStatisticalDimensionDataService().removeStatisticalDimensionData(data);

            // 执行查询数据到过渡表中
            int result1 = this.facade.getStatisticalDimensionDataService().AutoRunUpdateStatementForInsertAndUpdate();
            // 执行从过度表中查询并且计算数据到最终表中
            int result2 = this.facade.getStatisticalDimensionDataService().AutoRunUpdateStatementForDataInsert();

            log.setJob_status("成功");
            if (result1 == 0) {
                log.setComments("执行查询数据到过渡表中出现异常，结束客户分类统计基础数据-年度");
            } else {
                if (result2 == 0) {
                    log.setComments("执行从过度表中查询并且计算数据到最终表中出现异常，结束客户分类统计基础数据-年度");
                } else {
                    log.setComments("运行正常，结束客户分类统计基础数据-年度");
                }
            }
        } catch (Exception e) {
            log.setJob_status("失败");
            log.setComments(e.getMessage());
            e.printStackTrace();
        }
        Date end_time = new Date();
        log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
        log.setJob_type("客户分类统计-年度");
        log.setStart_time(start_time);
        log.setEnd_time(end_time);
        log.setLink_table("STATISTICAL_DIMENSION_DATA");
        log.setOper_user_id("00000");
        log.setOper_real_name("系统");
        log.setOper_user_ip("127.0.0.1");
        log.setOper_fun("syncKonkaYwHzTjByMonth");
        this.facade.getLogOfJobService().createLogOfJob(log);
    }

    /**
     * 执行插入数据的SQL语句，并且更新相应的比例数据 2014-11-07
     */
    @Override
    public void syncAutoRunUpdateStatementForDataMonthInsertAndUpdate() {
        // 添加记录日志
        LogOfJob log = new LogOfJob();
        Date start_time = new Date();
        // 执行查询数据到过渡表中
        int result1;
        // 执行从过度表中查询并且计算数据到最终表中
        int result2;
        try {
            // 先删除过渡表中的数据
            StatisticalDimensionDataMonthMiddle dataMiddle = new StatisticalDimensionDataMonthMiddle();
            this.facade.getStatisticalDimensionDataMonthMiddleService().removeStatisticalDimensionDataMonthMiddle(dataMiddle);
            // 再删除最终表中的数据
            StatisticalDimensionDataMonth data = new StatisticalDimensionDataMonth();
            this.facade.getStatisticalDimensionDataMonthService().removeStatisticalDimensionDataMonth(data);

            result1 = this.facade.getStatisticalDimensionDataMonthService().AutoRunUpdateStatementForDataMonthInsertAndUpdate();
            result2 = this.facade.getStatisticalDimensionDataMonthService().AutoRunUpdateStatementForDataMonthInsert();
            if (result1 == 0) {
                log.setJob_status("失败");
                log.setComments("执行查询数据到过渡表中出现异常，结束客户分类统计基础数据-月度");
            } else {
                if (result2 == 0) {
                    log.setJob_status("失败");
                    log.setComments("执行从过度表中查询并且计算数据到最终表中出现异常，结束客户分类统计基础数据-月度");
                } else {
                    log.setJob_status("成功");
                    log.setComments("运行正常，结束客户分类统计基础数据-月度");
                }
            }
        } catch (Exception e) {
            log.setJob_status("失败");
            log.setComments(e.getMessage());
            e.printStackTrace();
        }
        Date end_time = new Date();
        log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
        log.setJob_type("初始化统计的月度基础数据");
        log.setStart_time(start_time);
        log.setEnd_time(end_time);
        log.setLink_table("STATISTICAL_DIMENSION_DATA_MONTH");
        log.setOper_user_id("00000");
        log.setOper_real_name("系统");
        log.setOper_user_ip("127.0.0.1");
        log.setOper_fun("syncAutoRunUpdateStatementForDataMonthInsertAndUpdate");
        this.facade.getLogOfJobService().createLogOfJob(log);
    }

    @Override
    public void syncKonkaFgsEd() {

        Calendar cc = Calendar.getInstance();

        // 先获取分公司的所有仓位
        KonkaFgsCkSn kc = new KonkaFgsCkSn();
        List<KonkaFgsCkSn> kcList = this.facade.getKonkaFgsCkSnService().getKonkaFgsCkSnList(kc);
        if (kcList != null && kcList.size() > 0) {
            // 删除临时表
            this.facade.getKonkaFgsEdService().removeKonkaFgsEd(new KonkaFgsEd());

            for (KonkaFgsCkSn konkaFgsCkSn : kcList) {
                List<KCXX> kxList = new R3daoImpl().getKcxx(konkaFgsCkSn.getGc_sn(), konkaFgsCkSn.getCw_sn(), null, null);
                if (kxList != null && kxList.size() > 0) {
                    for (KCXX kcxx : kxList) {
                        KonkaFgsEd ke = new KonkaFgsEd();
                        if (kcxx.getMATNR() != null) {// 型号
                            ke.setPd_sn(kcxx.getMATNR());
                        }
                        if (kcxx.getVERME() != null) {// 剩余库存
                            ke.setNum(kcxx.getVERME());
                        }
                        if (kcxx.getVERPR() != null) {// 单价
                            ke.setPrice(kcxx.getVERPR());
                        }
                        ke.setDept_id(konkaFgsCkSn.getDept_id());// 分公司id
                        this.facade.getKonkaFgsEdService().createKonkaFgsEd(ke);

                    }
                }
            }

            KonkaFgsEd kfd = new KonkaFgsEd();
            List<KonkaFgsEd> kfdList = this.facade.getKonkaFgsEdService().getKonkaFgsEdGroupByDeptIdList(kfd);
            if (kfdList != null && kfdList.size() > 0) {
                for (KonkaFgsEd konkaFgsEd : kfdList) {
                    BigDecimal dept_id_1 = (BigDecimal) konkaFgsEd.getMap().get("dept_id");
                    Long dept_id = dept_id_1.longValue();
                    BigDecimal total_ed = new BigDecimal("0.00");
                    if (konkaFgsEd.getMap().get("total_ed") != null) {
                        total_ed = (BigDecimal) konkaFgsEd.getMap().get("total_ed");
                    }
                    KonkaYjglYjed ky = new KonkaYjglYjed();
                    ky.setDept_id(dept_id);
                    ky.setYjed_year(cc.get(Calendar.YEAR));
                    ky = this.facade.getKonkaYjglYjedService().getKonkaYjglYjed(ky);
                    if (ky == null) {
                        KonkaYjglYjed kyd = new KonkaYjglYjed();
                        kyd.setDept_id(dept_id);
                        kyd.setYjed_year(cc.get(Calendar.YEAR));// 默认当前年
                        kyd.setUpdate_time(new Date());
                        kyd.setYjed_used(total_ed);
                        kyd.setYjed_jz(new BigDecimal("0.00"));
                        kyd.setYjed_ls(new BigDecimal("0.00"));
                        kyd.setYjed_zx(new BigDecimal("0.00"));
                        kyd.setYjed_total(kyd.getYjed_jz().add(kyd.getYjed_ls()).add(kyd.getYjed_zx()));
                        kyd.setYjed_not_use(kyd.getYjed_total().subtract(kyd.getYjed_used()));
                        this.facade.getKonkaYjglYjedService().createKonkaYjglYjed(kyd);
                    } else {
                        KonkaYjglYjed kyd = new KonkaYjglYjed();
                        kyd.setDept_id(dept_id);
                        kyd.setYjed_year(cc.get(Calendar.YEAR));// 默认当前年
                        kyd.setYjed_used(total_ed);
                        // kyd.setYjed_total(ky.getYjed_jz().add(ky.getYjed_ls()).add(ky.getYjed_zx()));
                        kyd.setYjed_not_use(ky.getYjed_total().subtract(kyd.getYjed_used()));
                        this.facade.getKonkaYjglYjedService().modifyKonkaYjglYjed(kyd);
                    }
                }
            }

        }

    }

    /**
     * 每月月初(1号凌晨)系统自动结转客户库存
     */
    @Override
    public void syncAutoRunUpdateStatementForInsertJStocksVerify() {
        if(checkStatus("syncAutoRunUpdateStatementForInsertJStocksVerify")){
            // 添加记录日志
            LogOfJob log = new LogOfJob();
            Date start_time = new Date();
            try {
                // 执行结转数据到库存库存表中
                int result1 = this.facade.getJStocksVerifyService().AutoRunUpdateStatementForInsertJStocksVerify();

                if (result1 == 0) {
                    log.setJob_status("失败");
                    log.setComments("执行自动结转客户库存出现异常，结束自动结转客户库存");
                } else {
                    log.setJob_status("成功");
                    log.setComments("执行自动结转客户库存正常，结束自动结转客户库存");
                }
            } catch (Exception e) {
                log.setJob_status("失败");
                log.setComments(e.getMessage());
                e.printStackTrace();
            }
            Date end_time = new Date();
            log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
            log.setJob_type("初始化统计的零售基础数据");
            log.setStart_time(start_time);
            log.setEnd_time(end_time);
            log.setLink_table("J_STOCKS_VERIFY");
            log.setOper_user_id("00000");
            log.setOper_real_name("系统");
            log.setOper_user_ip("127.0.0.1");
            log.setOper_fun("syncAutoRunUpdateStatementForInsertJStocksVerify");
            this.facade.getLogOfJobService().createLogOfJob(log);
        }
    }

    /**
     * 1、零售数据的同步<br/>
     * 因为目前零售数据只允许修改两天之内的数据，为了保险起见，两天之内的数据的处理方法时候删除三天之内同步过的，然后重新更新三天之内的；<br/>
     * 但是对于无效的数据，因为无效的数据没有时间限制，所以处理方法是，查询三天以前的无效数据，并且更新时间实在三天之内的，说明是三天之内修改的， 然后根据ID<br/>
     * 零售数据日数据更新2014-12-10<br/>
     * 2、结算数据(地采)的同步<br/>
     * 首先删除31天之内同步过来的数据，然后再重新同步31内的数据<br/>
     * 3、结算数据(集采)的同步<br/>
     * 首先删除31天之内同步过来的数据，然后再重新同步31内的数据<br/>
     *
     * @return
     */
    @Override
    public void autoSyncStatisticalDimensionRetailData() {
        // 添加记录日志
        LogOfJob log = new LogOfJob();
        Date start_time = new Date();
        try {
            // 分7步操作
            // 1、删除三天之内的零售上报已经同步过来的数据
            this.facade.getStatisticalDimensionRetailDataService().AutoRunStatisticalDimensionRetailDataDelete();

            // 2、将三天之内新的零售上报数据同步过来
            this.facade.getStatisticalDimensionRetailDataService().AutoRunStatisticalDimensionRetailDataInsert();

            // 3、查询三天之前全部的零售上报无效数据，然后更新成删除
            this.facade.getStatisticalDimensionRetailDataService().deleteStatisticalDimensionRetailDataByIDs();

            // 4、删除31天之内的结算数据(地采)已经同步过来的数据
            this.facade.getStatisticalDimensionRetailDataService().AutoRunStatisticalDimensionRetailDataDeleteForDiCai();

            // 5、将31天之内新的结算数据(地采)数据同步过来
            this.facade.getStatisticalDimensionRetailDataService().AutoRunStatisticalDimensionRetailDataInsertForDiCai();

            // 6、删除31天之内的结算数据(集采)已经同步过来的数据
            this.facade.getStatisticalDimensionRetailDataService().AutoRunStatisticalDimensionRetailDataDeleteForJiCai();

            // 7、将31之内新的结算数据(集采)数据同步过来
            this.facade.getStatisticalDimensionRetailDataService().AutoRunStatisticalDimensionRetailDataInsertForJiCai();
            log.setJob_status("成功");
        } catch (Exception e) {
            log.setJob_status("失败");
            log.setComments(e.getMessage());
            e.printStackTrace();
        }
        Date end_time = new Date();
        log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
        log.setJob_type("初始化统计的零售基础数据");
        log.setStart_time(start_time);
        log.setEnd_time(end_time);
        log.setLink_table("STATISTICAL_DIMENSION_RETAIL_DATA");
        log.setOper_user_id("00000");
        log.setOper_real_name("系统");
        log.setOper_user_ip("127.0.0.1");
        log.setOper_fun("autoSyncStatisticalDimensionRetailData");
        this.facade.getLogOfJobService().createLogOfJob(log);
    }

    /**
     * 初始化统计的省市县镇基础数据表 2014-12-16
     *
     * @return
     */
    @Override
    public void autoSyncStatisticalDimensionArea() {
        // 添加记录日志
        LogOfJob log = new LogOfJob();
        Date start_time = new Date();
        try {
            // 1、首先删除区域基础数据表中在信息
            this.facade.getStatisticalDimensionAreaService().removeStatisticalDimensionAreaAll(null);

            // 2、插入乡镇数据
            this.facade.getStatisticalDimensionAreaService().createStatisticalDimensionAreaWithTown();

            // 3、插入县数据
            this.facade.getStatisticalDimensionAreaService().createStatisticalDimensionAreaWithCounty();

            // 4、插入市镇数据
            this.facade.getStatisticalDimensionAreaService().createStatisticalDimensionAreaWithCity();

            // 5、插入省镇数据
            this.facade.getStatisticalDimensionAreaService().createStatisticalDimensionAreaWithProvince();
            log.setJob_status("成功");
        } catch (Exception e) {
            log.setJob_status("失败");
            log.setComments(e.getMessage());
            e.printStackTrace();
        }
        Date end_time = new Date();
        log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
        log.setJob_type("初始化统计的区域基础数据");
        log.setStart_time(start_time);
        log.setEnd_time(end_time);
        log.setLink_table("STATISTICAL_DIMENSION_AREA");
        log.setOper_user_id("00000");
        log.setOper_real_name("系统");
        log.setOper_user_ip("127.0.0.1");
        log.setOper_fun("autoSyncStatisticalDimensionArea");
        this.facade.getLogOfJobService().createLogOfJob(log);
    }

    /**
     * 初始化客户统计分析中客户的分公司基础数据 2014-12-16
     *
     * @return
     */
    @Override
    public void autoSyncStatisticalDimensionKhfgs() {
        // 添加记录日志
        LogOfJob log = new LogOfJob();
        Date start_time = new Date();
        try {
            // 1、首先删除分公司基础数据表中的信息
            this.facade.getStatisticalDimensionKhfgsService().removeStatisticalDimensionKhfgsAll(null);

            // 2、插入分公司数据
            this.facade.getStatisticalDimensionKhfgsService().initStatisticalDimensionKhfgs();
            log.setJob_status("成功");
        } catch (Exception e) {
            log.setJob_status("失败");
            log.setComments(e.getMessage());
            e.printStackTrace();
        }
        Date end_time = new Date();
        log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
        log.setJob_type("初始化统计的分公司基础数据");
        log.setStart_time(start_time);
        log.setEnd_time(end_time);
        log.setLink_table("STATISTICAL_DIMENSION_KHFGS");
        log.setOper_user_id("00000");
        log.setOper_real_name("系统");
        log.setOper_user_ip("127.0.0.1");
        log.setOper_fun("autoSyncStatisticalDimensionKhfgs");
        this.facade.getLogOfJobService().createLogOfJob(log);
    }

    /**
     * 统计的客户门店基础数据表 2014-12-16
     *
     * @return
     */
    @Override
    public void autoSyncStatisticalDimensionStore() {
        // 添加记录日志
        LogOfJob log = new LogOfJob();
        Date start_time = new Date();
        try {
            // 1、首先删除门店基础数据表中的信息
            this.facade.getStatisticalDimensionStoreService().removeStatisticalDimensionStoreAll(null);

            // 2、插入门店数据
            this.facade.getStatisticalDimensionStoreService().initStatisticalDimensionStore();
            log.setJob_status("成功");
        } catch (Exception e) {
            log.setJob_status("失败");
            log.setComments(e.getMessage());
            e.printStackTrace();
        }
        Date end_time = new Date();
        log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
        log.setJob_type("初始化统计的门店基础数据");
        log.setStart_time(start_time);
        log.setEnd_time(end_time);
        log.setLink_table("STATISTICAL_DIMENSION_STORE");
        log.setOper_user_id("00000");
        log.setOper_real_name("系统");
        log.setOper_user_ip("127.0.0.1");
        log.setOper_fun("autoSyncStatisticalDimensionStore");
        this.facade.getLogOfJobService().createLogOfJob(log);
    }

    /**
     * 统计的客户门店基础数据表 2014-12-16
     *
     * @return
     */
    @Override
    public void autoSyncStatisticalDimensionMd() {
        // 添加记录日志
        LogOfJob log = new LogOfJob();
        Date start_time = new Date();
        try {
            // 1、首先删除商品基础数据表中的信息
            this.facade.getStatisticalDimensionMdService().removeStatisticalDimensionMdAll(null);

            // 2、插入商品数据
            this.facade.getStatisticalDimensionMdService().initStatisticalDimensionMd();
            log.setJob_status("成功");
        } catch (Exception e) {
            log.setJob_status("失败");
            log.setComments(e.getMessage());
            e.printStackTrace();
        }
        Date end_time = new Date();
        log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
        log.setJob_type("初始化统计的商品基础数据");
        log.setStart_time(start_time);
        log.setEnd_time(end_time);
        log.setLink_table("STATISTICAL_DIMENSION_MD");
        log.setOper_user_id("00000");
        log.setOper_real_name("系统");
        log.setOper_user_ip("127.0.0.1");
        log.setOper_fun("autoSyncStatisticalDimensionMd");
        this.facade.getLogOfJobService().createLogOfJob(log);
    }

    /**
     * 初始化统计分析的时间，插入数据库中的最新时间到当前时间之后三天的这个时间段的时间插入到数据库中 2014-12-19
     *
     * @return
     */
    @Override
    public void autoSyncStatisticalDimensionTimeDay() {
        // 添加记录日志
        LogOfJob log = new LogOfJob();
        Date start_time = new Date();
        try {
            // 每天自动执行，插入的时间是数据库中的最新的时间，到 当前时间的后三天 这段时间，这样就算定时器在某天未执行，也不会影响数据的正确性
            this.facade.getStatisticalDimensionTimeDayService().initStatisticalDimensionTimeDay();
            log.setJob_status("成功");
        } catch (Exception e) {
            log.setJob_status("失败");
            log.setComments(e.getMessage());
            e.printStackTrace();
        }
        Date end_time = new Date();
        log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
        log.setJob_type("初始化统计分析时间");
        log.setStart_time(start_time);
        log.setEnd_time(end_time);
        log.setLink_table("STATISTICAL_DIMENSION_TIME_DAY");
        log.setOper_user_id("00000");
        log.setOper_real_name("系统");
        log.setOper_user_ip("127.0.0.1");
        log.setOper_fun("autoSyncStatisticalDimensionTimeDay");
        this.facade.getLogOfJobService().createLogOfJob(log);
    }

    /**
     * BI 零售数据
     */
    @Override
    public void autoSyncBIMobileSalesData() {
        if(checkStatus("autoSyncBIMobileSalesData")){
            // 添加记录日志
            LogOfJob log = new LogOfJob();
            Date start_time = new Date();
            try {
                this.facade.getDataSync4BIService().updateMobileSalesData4BI();
                log.setJob_status("成功");
            } catch (Exception e) {
                log.setJob_status("失败");
                log.setComments(e.getMessage());
                e.printStackTrace();
            }
            Date end_time = new Date();
            log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
            log.setJob_type("BI客户零售");
            log.setStart_time(start_time);
            log.setEnd_time(end_time);
            log.setLink_table("BI_MOBILE_SALE_DATAS");
            log.setOper_user_id("00000");
            log.setOper_real_name("系统");
            log.setOper_user_ip("127.0.0.1");
            log.setOper_fun("autoSyncBIMobileSalesData");
            this.facade.getLogOfJobService().createLogOfJob(log);
        }
    }

    /**
     * BI 客户库存数据
     */
    @Override
    public void autoSYncBIStockInfoData() {
        if(checkStatus("autoSYncBIStockInfoData")){
            // 添加记录日志
            LogOfJob log = new LogOfJob();
            Date start_time = new Date();

            try {
                this.facade.getDataSync4BIService().updateStockInfo4BI();
                log.setJob_status("成功");
            } catch (Exception e) {
                log.setJob_status("失败");
                log.setComments(e.getMessage());
                e.printStackTrace();
            }
            Date end_time = new Date();
            log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
            log.setJob_type("BI客户库存");
            log.setStart_time(start_time);
            log.setEnd_time(end_time);
            log.setLink_table("BI_STOCK_INFO");
            log.setOper_user_id("00000");
            log.setOper_real_name("系统");
            log.setOper_user_ip("127.0.0.1");
            log.setOper_fun("autoSYncBIStockInfoData");
            this.facade.getLogOfJobService().createLogOfJob(log);
        }
    }

    @Override
    public void autoSendMessage() throws Exception {
        // 已付款数量
        int num1 = 0;
        PshowOrdeDetails pd = new PshowOrdeDetails();
        pd.setPd_id(100018651L);
        pd.getMap().put("state_in", new Integer[] {5, 10, 20, 30, 40, 50, 60});
        List<PshowOrdeDetails> pdList = this.facade.getPshowOrdeDetailsService().getPshowOrdeDetailsForPayList(pd);
        if (null != pdList && pdList.size() > 0) {
            for (PshowOrdeDetails pshowOrdeDetails : pdList) {
                num1 = num1 + pshowOrdeDetails.getNum().intValue();
            }
        }
        if (num1 >= 50) {
            PshowOrder p = new PshowOrder();
            p.getMap().put("prod_type", "8");
            p.getMap().put("pd_name_like", "赣南脐橙");
            p.getMap().put("state_in", new Integer[] {5, 10, 20, 30, 40, 50, 60});
            Long recordCount = this.facade.getPshowOrderService().getPshowOrdeForDeptNameAndFullNameListCount(p);
            p.getRow().setFirst(0);
            p.getRow().setCount(recordCount.intValue());
            List<PshowOrder> entityList = this.facade.getPshowOrderService().getPshowOrdeForDeptNameAndFullNameList(p);
            for (PshowOrder pshowOrder : entityList) {

                String pd_name_num = "";
                PshowOrdeDetails pp = new PshowOrdeDetails();
                pp.setOrder_id(pshowOrder.getId());
                List<PshowOrdeDetails> ppList = this.pshowOrdeDetailsDao.selectEntityList(pp);

                for (PshowOrdeDetails ps : ppList) {
                    KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
                    konkaBcompPd.setId(ps.getPd_id());
                    konkaBcompPd = this.konkaBcompPdDao.selectEntity(konkaBcompPd);
                    pd_name_num += konkaBcompPd.getPd_sn() + "*" + ps.getNum() + " ";
                }
                // {工号姓名}您好！您预购的{产品名称}*{数量}（订单号为：{订单号}）现已团购成功，请凭工卡及短信验证码{x月x日在x地}领取，敬请注意！[奉献精致产品,引领美妙生活!康佳EPP平台,客服热线0755-61368827]

                String content =
                        pshowOrder.getBuyer_name() + "您好!您预购的" + pd_name_num + "（订单号为:" + pshowOrder.getTrade_index() + "）"
                                + "现已团购成功，请凭工卡及短信验证码12月17日在深圳总部南门自提领取，敬请注意！[奉献精致产品,引领美妙生活!康佳EPP平台,客服热线0755-61368827]";
                if (getSendMessageResult(pshowOrder.getBuyer_mp(), content)) {
                    EcMessage ecm = new EcMessage();
                    ecm.setContent(content);
                    ecm.setMobile(pshowOrder.getBuyer_mp());
                    ecm.setSend_date(new Date());
                    ecm.setSend_state(0);
                    ecMessageDao.insertEntity(ecm);
                } else {
                    EcMessage ecm = new EcMessage();
                    ecm.setContent(content);
                    ecm.setMobile(pshowOrder.getBuyer_mp());
                    ecm.setSend_date(new Date());
                    ecm.setSend_state(0);
                    ecMessageDao.insertEntity(ecm);
                }
            }

        } else {

            PshowOrder p = new PshowOrder();
            p.getMap().put("prod_type", "8");
            p.getMap().put("pd_name_like", "赣南脐橙");
            p.getMap().put("state_in", new Integer[] {5, 10, 20, 30, 40, 50, 60});
            Long recordCount = this.facade.getPshowOrderService().getPshowOrdeForDeptNameAndFullNameListCount(p);
            p.getRow().setFirst(0);
            p.getRow().setCount(recordCount.intValue());
            List<PshowOrder> entityList = this.facade.getPshowOrderService().getPshowOrdeForDeptNameAndFullNameList(p);
            for (PshowOrder pshowOrder : entityList) {

                String pd_name_num = "";
                PshowOrdeDetails pp = new PshowOrdeDetails();
                pp.setOrder_id(pshowOrder.getId());
                List<PshowOrdeDetails> ppList = this.pshowOrdeDetailsDao.selectEntityList(pp);

                for (PshowOrdeDetails ps : ppList) {
                    KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
                    konkaBcompPd.setId(ps.getPd_id());
                    konkaBcompPd = this.konkaBcompPdDao.selectEntity(konkaBcompPd);
                    pd_name_num += konkaBcompPd.getPd_sn() + "*" + ps.getNum() + " ";
                }
                // {工号姓名}您好！您预购的{产品名称}*{数量}（订单号为：{订单号}）因团购数量不够，现已取消，我们将尽快退还货款，届时敬请查收！[奉献精致产品,引领美妙生活!康佳EPP平台,客服热线0755-61368827]

                String content =
                        pshowOrder.getBuyer_name() + "您好!您预购的" + pd_name_num + "（订单号为:" + pshowOrder.getTrade_index() + "）"
                                + "因团购数量不够，现已取消，我们将尽快退还货款，届时敬请查收！[奉献精致产品,引领美妙生活!康佳EPP平台,客服热线0755-61368827]";
                if (getSendMessageResult(pshowOrder.getBuyer_mp(), content)) {
                    EcMessage ecm = new EcMessage();
                    ecm.setContent(content);
                    ecm.setMobile(pshowOrder.getBuyer_mp());
                    ecm.setSend_date(new Date());
                    ecm.setSend_state(0);
                    ecMessageDao.insertEntity(ecm);
                } else {
                    EcMessage ecm = new EcMessage();
                    ecm.setContent(content);
                    ecm.setMobile(pshowOrder.getBuyer_mp());
                    ecm.setSend_date(new Date());
                    ecm.setSend_state(0);
                    ecMessageDao.insertEntity(ecm);
                }
            }

        }
    }

    @Override
    public void autoStoreCustomerRelData4BI() {
        if(checkStatus("autoStoreCustomerRelData4BI")){
            // 添加记录日志
            LogOfJob log = new LogOfJob();
            Date start_time = new Date();

            try {
                this.facade.getDataSync4BIService().updateStoreCustomerRelData4BI();
                log.setJob_status("成功");
            } catch (Exception e) {
                log.setJob_status("失败");
                log.setComments(e.getMessage());
                e.printStackTrace();
            }
            Date end_time = new Date();
            log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
            log.setJob_type("BI客户与门店关系");
            log.setStart_time(start_time);
            log.setEnd_time(end_time);
            log.setLink_table("BI_STORE_CUSTOMER_REL");
            log.setOper_user_id("00000");
            log.setOper_real_name("系统");
            log.setOper_user_ip("127.0.0.1");
            log.setOper_fun("autoStoreCustomerRelData4BI");
            this.facade.getLogOfJobService().createLogOfJob(log);
        }
    }

    @Override
    public void autoStoreSaleInfoData4BI() {
        if(checkStatus("autoStoreSaleInfoData4BI")){
            LogOfJob log = new LogOfJob();
            Date start_time = new Date();
            try {
                this.facade.getDataSync4BIService().updateStoreSaleInfoData4BI();
                log.setJob_status("成功");
            } catch (Exception e) {
                log.setJob_status("失败");
                log.setComments(e.getMessage());
                e.printStackTrace();
            }
            Date end_time = new Date();
            log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
            log.setJob_type("BI门店销量");
            log.setStart_time(start_time);
            log.setEnd_time(end_time);
            log.setLink_table("BI_STORE_SALE_INFO");
            log.setOper_user_id("00000");
            log.setOper_real_name("系统");
            log.setOper_user_ip("127.0.0.1");
            log.setOper_fun("autoStoreSaleInfoData4BI");
            this.facade.getLogOfJobService().createLogOfJob(log);
        }
    }

    @Override
    public void autoYJGLData4BI() {
        if(checkStatus("autoYJGLData4BI")){
            LogOfJob log = new LogOfJob();
            Date start_time = new Date();
            try {
                this.facade.getDataSync4BIService().updateYJGLData4BI();
                log.setJob_status("成功");
            } catch (Exception e) {
                log.setJob_status("失败");
                log.setComments(e.getMessage());
                e.printStackTrace();
            }
            Date end_time = new Date();
            log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
            log.setJob_type("BI样机数据");
            log.setStart_time(start_time);
            log.setEnd_time(end_time);
            log.setLink_table("BI_YJGL_DATA");
            log.setOper_user_id("00000");
            log.setOper_real_name("系统");
            log.setOper_user_ip("127.0.0.1");
            log.setOper_fun("autoYJGLData4BI");
            this.facade.getLogOfJobService().createLogOfJob(log);
        }
    }

    /**
     * 自动更新客户状态
     */
    @Override
    public void autoChangeShopStatus() {
        if(checkStatus("autoChangeShopStatus")){
            // 添加记录日志
            LogOfJob log = new LogOfJob();
            Date start_time = new Date();

            try {
                this.konkaR3ShopDao.changeShopStatus();
                log.setJob_status("成功");
            } catch (Exception e) {
                log.setJob_status("失败");
                log.setComments(e.getMessage());
                e.printStackTrace();
            }
            Date end_time = new Date();
            log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
            log.setJob_type("自动判断客户状态");
            log.setStart_time(start_time);
            log.setEnd_time(end_time);
            log.setLink_table("KONKA_R3_SHOP");
            log.setOper_user_id("00000");
            log.setOper_real_name("系统");
            log.setOper_user_ip("127.0.0.1");
            log.setOper_fun("autoChangeShopStatus");
            this.facade.getLogOfJobService().createLogOfJob(log);
        }
    }

    @Override
    public void autoCustGoodsRepertory() {
        if(checkStatus("autoCustGoodsRepertory")){
            // 添加记录日志
            LogOfJob log = new LogOfJob();
            Date start_time = new Date();
            try {
                JStocksSummary entity = new JStocksSummary();
                KonkaBaseTypeData taskType = new KonkaBaseTypeData();
                taskType.setType_id(100020L);
                taskType = this.facade.getKonkaBaseTypeDataService().getKonkaBaseTypeData(taskType);
                if (null != taskType && null != taskType.getField1()) {
                    entity.getMap().put("weekNum", Integer.valueOf(taskType.getField1()));
                    entity.getMap().put("goods_name_type", 2);
                    entity.getMap().put("downAvg", 2);

                    // entity.getMap().put("filter_by_ywy_id_eq", 15115);
                    Long total = this.facade.getJStocksSummaryService().getCustRepertoryReportListCount(entity);

                    entity.getRow().setFirst(0);
                    entity.getRow().setCount(Integer.valueOf("" + total));
                    List<Map<String, Object>> entityList = this.facade.getJStocksSummaryService().getCustRepertoryReportList(entity);
                    for (int j = 0; j < entityList.size(); j++) {

                        Map<String, Object> map = entityList.get(j);
                        Object user_id = map.get("USER_ID");
                        Object cust_name = map.get("CUSTOMER_NAME");
                        Object r3_code = map.get("R3_CODE");
                        Object dept_id = map.get("L4_DEPT_ID");
                        Object goods_name = map.get("GOODS_NAME");
                        Object total_cur_num = map.get("TOTLE_CUR_NUM");
                        Object avg_num = map.get("AVG_NUM");
                        Object total_num = map.get("TOTAL_NUM");
                        Object goods_name_type = map.get("GOODS_NAME_TYPE");

                        String message = "";
                        List<String> aliasList = new ArrayList<String>();// 推送用户列表
                        message = cust_name + "客户 ：" + goods_name + " 型号 ：" + " 库存紧张 赶紧补货！" + "当前剩余库存 ：" + total_cur_num + " 台！";

                        if (null != user_id) {
                            // 业务员总经理查找
                            PeProdUser user = new PeProdUser();

                            List<PeProdUser> userlist = new ArrayList<PeProdUser>();
                            if (null != dept_id) {
                                user.getMap().put("dept_id", dept_id);
                                user.getMap().put("role_id", 34);
                                userlist = peProdUserDao.selectPeProdUserByDeptIdAndRoleIdResult(user);
                            }

                            aliasList.add("" + user_id);
                            for (PeProdUser peProdUser : userlist) {
                                aliasList.add("" + peProdUser.getId());
                            }
                            /**
                             * android 端推送消息
                             */
                            if (null != user_id) {
                                for (String userId : aliasList) {
                                    KonkaOrderMessageProcess konKaOrderMessageProcess = new KonkaOrderMessageProcess();
                                    konKaOrderMessageProcess.setC_user_id(Long.valueOf("" + user_id));
                                    konKaOrderMessageProcess.setBc_user_id(Long.valueOf(userId));// 被催办人用户ID
                                    konKaOrderMessageProcess.setAdd_date(new Date());// 添加时间
                                    konKaOrderMessageProcess.setState(0);// 状态
                                    konKaOrderMessageProcess.setMessage_type(4);// 类型（0订单，1。。。2。。。）
                                    konKaOrderMessageProcess.setTitle("库存提醒");// 标题
                                    konKaOrderMessageProcess.setRemark(message);// 备注
                                    konKaOrderMessageProcess.setIs_send(0);// 是否推送
                                    konkaOrderMessageProcessDao.insertEntity(konKaOrderMessageProcess);
                                }
                            }

                        }
                    }

                    String alert = "库存提醒";
                    String title = "库存提醒";
                    Integer type = 4;// 表示库存提醒

                    KonkaOrderMessageProcess t = new KonkaOrderMessageProcess();
                    t.setMessage_type(4);
                    List<Map<String, Object>> messagelist = this.konkaOrderMessageProcessDao.selectAll_BCB_List(t);
                    for (int i = 0; i < messagelist.size(); i++) {
                        String url = "http://qdgl.konka.com/webservice/JStocksSummary.do?method=messageView1";
                        Map<String, Object> message = messagelist.get(i);
                        Object bc_user_id = message.get("BC_USER_ID");
                        //System.out.println("==========bc_user_id============" + bc_user_id);
                        Object bc_count = message.get("BC_COUNT");
                        url += "&bc_user_id=" + bc_user_id;
                        // 分批 一批100
                        if (i % 500 != 0) {
                            this.facade.getIosPushMessageService().SendMessage(alert, title, type, url, "你有" + bc_count + "+条库存提醒 点击查看详情", "" + bc_user_id);
                        } else {
                            //System.out.println("在这里睡1分钟---------------------------------------------");
                            try {
                                Thread.sleep(60000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                log.setJob_status("成功");
            }catch (Exception e) {
                log.setJob_status("失败");
                log.setComments(e.getMessage());
                e.printStackTrace();
            }
            Date end_time = new Date();
            log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
            log.setJob_type("缺货提醒消息推送");
            log.setStart_time(start_time);
            log.setEnd_time(end_time);
            log.setLink_table("J_Stocks_Summary");
            log.setOper_user_id("00000");
            log.setOper_real_name("系统");
            log.setOper_user_ip("127.0.0.1");
            log.setOper_fun("autoCustGoodsRepertory");
            this.facade.getLogOfJobService().createLogOfJob(log);
        }
    }


    @Resource
    private AreaFightInfoDao areaFightInfoDao;

    /**
     * 区域作战分析统计
     */
    @Override
    public void autoUpdateAreaInfo() {
        if(checkStatus("autoUpdateAreaInfo")){
            // 添加记录日志
            LogOfJob log = new LogOfJob();
            Date start_time = new Date();
            try {
                this.areaFightInfoDao.autoCountAreaInfo();
                log.setJob_status("成功");
            } catch (Exception e) {
                log.setJob_status("失败");
                log.setComments(e.getMessage());
                e.printStackTrace();
            }
            Date end_time = new Date();
            log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time, end_time));
            log.setJob_type("区域作战");
            log.setStart_time(start_time);
            log.setEnd_time(end_time);
            log.setLink_table("AREA_FIGHT_INFO");
            log.setOper_user_id("00000");
            log.setOper_real_name("系统");
            log.setOper_user_ip("127.0.0.1");
            log.setOper_fun("autoUpdateAreaInfo");
            this.facade.getLogOfJobService().createLogOfJob(log);
        }
    }

    /**
     * 统计耗时
     *
     * @param start
     * @param end
     * @return
     */
    public static String getTimeInterval(Date start, Date end) {
        Long sec = (end.getTime() - start.getTime()) / 1000;
        return sec == 0 ? String.valueOf(end.getTime() - start.getTime()) + "ms" : sec.toString() + "s";
    }

    @Resource
    ToDrpOrderService toDrpOrderService;

    @Override
    public void autoYsncDRP() {
        if(checkStatus("autoYsncDRP")){
            toDrpOrderService.ceateTransQDorderDataToDRP();
        }
    }


    /*
     * 该方法用于同步地采的订单数据到商品栈中分三步走
      * 一、取前一天的完全发货的数据押入
      * 二、取前一天再栈里面存入的前面没处理完毕的订单（每次前推30天）
     * 三、将辅助表中的数据清除，然后存入新的不能处理的数据 (non-Javadoc)
     *
     * @see com.ebiz.mmt.service.AutoRunService#autoKonkaFifoStockInDc()
     */
    @Override
    public void autoKonkaFifoStockInDc() {
        // TODO Auto-generated method stub
        if(checkStatus("autoKonkaFifoStockInDc")){
            // 添加记录日志
            LogOfJob log = new LogOfJob();
            Date start_time = new Date();
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Calendar cal = Calendar.getInstance();
                // 拿到进货批次
                String stock_in_batch = facade.getJxcFifoStocksService().getJxcFifoStockInBatch();
                // 1、取前一天的数据并入仓
                facade.getJxcFifoStocksService().StockInDcLastDay(stock_in_batch);
                // 2、根据辅助表中数据扫钱扫之前的数据然后处理辅助表数据
                facade.getJxcFifoStocksService().StockInDcCheck(stock_in_batch);
                log.setJob_status("成功");
                log.setComments("同步地采的订单数据到商品栈中先进先出");
            } catch (Exception e) {
                log.setJob_status("失败");
                log.setComments(e.getMessage());
                e.printStackTrace();
            }
            Date end_time = new Date();
            log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time,end_time));
            log.setJob_type("先进先出同步地采");
            log.setStart_time(start_time);
            log.setEnd_time(end_time);
            log.setLink_table("CHANNEL_DATA_IMPORT－>KONKA_SO_FIFO");
            log.setOper_user_id("00000");
            log.setOper_real_name("系统");
            log.setOper_user_ip("127.0.0.1");
            log.setOper_fun("autoKonkaFifoStockInDc");
            this.facade.getLogOfJobService().createLogOfJob(log);
        }
    }

    // 集采的同步
    @Override
    public void autoKonkaFifoStockInJc() {
        // TODO Auto-generated method stub
        if(checkStatus("autoKonkaFifoStockInJc")){
            // 添加记录日志
            LogOfJob log = new LogOfJob();
            Date start_time = new Date();
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Calendar cal = Calendar.getInstance();
                // 拿到进货批次
                String stock_in_batch = facade.getJxcFifoStocksService().getJxcFifoStockInBatch();
                // 1、取前一天的数据并入仓
                facade.getJxcFifoStocksService().StockInJcLastDay(stock_in_batch);
                // 2、根据辅助表中数据扫钱扫之前的数据然后处理辅助表数据
                facade.getJxcFifoStocksService().StockInJcCheck(stock_in_batch);
                log.setJob_status("成功");
                log.setComments("成功同步集采的订单数据到商品栈中先进先出");
            } catch (Exception e) {
                log.setJob_status("失败");
                log.setComments(e.getMessage());
                e.printStackTrace();
            }
            Date end_time = new Date();
            log.setSpend_time(AutoRunServiceImpl.getTimeInterval(start_time,end_time));
            log.setJob_type("先进先出同步集采");
            log.setStart_time(start_time);
            log.setEnd_time(end_time);
            log.setLink_table("KONKA_ZLES23_RESULT_INFO－>KONKA_SO_FIFO");
            log.setOper_user_id("00000");
            log.setOper_real_name("系统");
            log.setOper_user_ip("127.0.0.1");
            log.setOper_fun("autoKonkaFifoStockInJc");
            this.facade.getLogOfJobService().createLogOfJob(log);
        }
    }


    /**
     * 检验是否停用
     * add by Liang Houen on 2015-08-18
     * @param func_name
     * @return
     */
    public boolean checkStatus(String func_name){
        KonkaJobs jobs = new KonkaJobs();
        jobs.setFun_name(func_name);
        jobs = this.konkaJobsDao.selectEntity(jobs);
        if(null!=jobs && 0==jobs.getStatus()) {
            return true;
        }else{
            logger.info(func_name+"方法执行的任务被停用");
            return false;
        }
    }
}

