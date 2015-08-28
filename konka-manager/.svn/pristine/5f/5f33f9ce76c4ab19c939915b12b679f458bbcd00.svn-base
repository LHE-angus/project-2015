package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.AttachmentDao;
import com.ebiz.mmt.dao.FromDrpOrderDao;
import com.ebiz.mmt.dao.FromDrpOrderDetailDao;
import com.ebiz.mmt.dao.JBaseGoodsDao;
import com.ebiz.mmt.dao.JBaseStoreDao;
import com.ebiz.mmt.dao.JBillDao;
import com.ebiz.mmt.dao.JStocksVerifyDao;
import com.ebiz.mmt.dao.JSubSellRecDao;
import com.ebiz.mmt.dao.KonkaMobileSailDataDao;
import com.ebiz.mmt.dao.KonkaMobileTerminalFeedbackDao;
import com.ebiz.mmt.dao.KonkaOrderInfoAuditDao;
import com.ebiz.mmt.dao.KonkaOrderInfoDao;
import com.ebiz.mmt.dao.KonkaOrderInfoDetailsAuditDao;
import com.ebiz.mmt.dao.KonkaOrderInfoDetailsDao;
import com.ebiz.mmt.dao.KonkaOrderInfoUpdateRecordDao;
import com.ebiz.mmt.dao.KonkaR3ShopDao;
import com.ebiz.mmt.dao.KonkaXxSellBillDao;
import com.ebiz.mmt.dao.SysSettingDao;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.FromDrpOrder;
import com.ebiz.mmt.domain.FromDrpOrderDetail;
import com.ebiz.mmt.domain.JBaseGoods;
import com.ebiz.mmt.domain.JBaseStore;
import com.ebiz.mmt.domain.JBill;
import com.ebiz.mmt.domain.JStocksVerify;
import com.ebiz.mmt.domain.JSubSellRec;
import com.ebiz.mmt.domain.KonkaMobileSailData;
import com.ebiz.mmt.domain.KonkaMobileTerminalFeedback;
import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.domain.KonkaOrderInfoAudit;
import com.ebiz.mmt.domain.KonkaOrderInfoDetails;
import com.ebiz.mmt.domain.KonkaOrderInfoDetailsAudit;
import com.ebiz.mmt.domain.KonkaOrderInfoUpdateRecord;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.SysSetting;
import com.ebiz.mmt.service.InteractWebService;
import com.ebiz.mmt.service.KonkaOrderInfoService;
import com.taobao.api.sync.DateUtils;

/**
 * @author Wu,Yang
 * @version 2011-11-25 09:08
 */
@Service
public class KonkaOrderInfoServiceImpl implements KonkaOrderInfoService {

    @Resource
    private KonkaOrderInfoDao konkaOrderInfoDao;

    @Resource
    private KonkaOrderInfoDetailsDao konkaOrderInfoDetailsDao;

    @Resource
    private KonkaOrderInfoAuditDao konkaOrderInfoAuditDao;

    @Resource
    private KonkaOrderInfoUpdateRecordDao konkaOrderInfoUpdateRecordDao;

    @Resource
    InteractWebService interactWebService;

    @Resource
    private KonkaOrderInfoDetailsAuditDao konkaOrderInfoDetailsAuditDao;

    @Resource
    private KonkaXxSellBillDao konkaXxSellBillDao;

    @Resource
    private KonkaR3ShopDao konkaR3ShopDao;

    @Resource
    private JBaseGoodsDao jBaseGoodsDao;

    @Resource
    private JBillDao jBillDao;

    @Resource
    private JBaseStoreDao jBaseStoreDao;

    @Resource
    private JSubSellRecDao jSubSellRecDao;

    @Resource
    private JStocksVerifyDao jStocksVerifyDao;

    @Resource
    private KonkaMobileSailDataDao konkaMobileSailDataDao;
    @Resource
    private SysSettingDao sysSettingDao;

    // back up the data from drp
    @Resource
    private FromDrpOrderDao fromDrpOrderDao;

    @Resource
    private FromDrpOrderDetailDao fromDrpOrderDetailDao;

    @Override
    public HashMap<String, String> createKonkaOrderInfo(KonkaOrderInfo t) {
        // 两种情况
        // 1、第一次暂存 或者 第一次直接提交（非暂存后提交）
        // 2、暂存后再次暂存或提交
        HashMap<String, String> result = new HashMap<String, String>();
        Long orderId = 0L;
        String is_temp_save = (String) t.getMap().get("is_temp_save");
        if (StringUtils.isNotEmpty(is_temp_save) && is_temp_save.equals("true")) {
            orderId = t.getId();
            result.put("id", orderId.toString());
            // 暂存后提交 或 暂存后又暂存 (修改主订单) 先删除所有的详细，后添加详细
            List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList_del = t.getKonkaOrderInfoDetailsListForDel();
            for (KonkaOrderInfoDetails koid : konkaOrderInfoDetailsList_del) {
                this.konkaOrderInfoDetailsDao.deleteEntity(koid);// 删除该订单下的所有
                // 订单详细产品
            }

            List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = t.getKonkaOrderInfoDetailsList();
            if (konkaOrderInfoDetailsList != null && konkaOrderInfoDetailsList.size() > 0) {
                for (KonkaOrderInfoDetails kkoid : konkaOrderInfoDetailsList) {// 添加
                    // 订单详细产品
                    kkoid.setOrder_id(t.getId());
                    this.konkaOrderInfoDetailsDao.insertEntity(kkoid);
                }
            }
            this.konkaOrderInfoDao.updateEntity(t);// 修改主订单
        } else {
            // 第一次暂存 或者 第一次直接提交,添加主订单和订单详细
            orderId = konkaOrderInfoDao.insertEntity(t);// 添加主订单

            result.put("id", orderId.toString());
            List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = t.getKonkaOrderInfoDetailsList();
            for (KonkaOrderInfoDetails konkaOrderInfoDetails : konkaOrderInfoDetailsList) {
                konkaOrderInfoDetails.setOrder_id(orderId);
                konkaOrderInfoDetailsDao.insertEntity(konkaOrderInfoDetails);// 添加订单详细产品
            }
        }

        if (t.getIs_submit() == 1) {
            // 只有提交订单时才审核和发送短信（第一次直接提交 或者 暂存后提交）
            // 业务员自动审核
            List<KonkaOrderInfoAudit> konkaOrderInfoAuditList = t.getKonkaOrderInfoAuditList();
            if (null != konkaOrderInfoAuditList && konkaOrderInfoAuditList.size() > 0) {
                KonkaOrderInfoAudit konkaOrderInfoAudit = konkaOrderInfoAuditList.get(0);
                konkaOrderInfoAudit.setLink_id(orderId);
                konkaOrderInfoAuditDao.insertEntity(konkaOrderInfoAudit);
            }
        }

        return result;
    }

    @Override
    public KonkaOrderInfo getKonkaOrderInfo(KonkaOrderInfo t) {
        return this.konkaOrderInfoDao.selectEntity(t);
    }

    @Override
    public Long getKonkaOrderInfoCount(KonkaOrderInfo t) {
        return this.konkaOrderInfoDao.selectEntityCount(t);
    }

    @Override
    public List<KonkaOrderInfo> getKonkaOrderInfoList(KonkaOrderInfo t) {
        return this.konkaOrderInfoDao.selectEntityList(t);
    }

    @Override
    public List<KonkaOrderInfo> getKonkaOrderInfoListWithShopName(KonkaOrderInfo t) {
        return this.konkaOrderInfoDao.selectKonkaOrderInfoListWithShopName(t);
    }

    @Override
    public int modifyKonkaOrderInfo(KonkaOrderInfo t) {
        // 审核时修改订单
        if (t.getKonkaOrderInfoAudit() != null) {// 添加审核记录
            konkaOrderInfoAuditDao.insertEntity(t.getKonkaOrderInfoAudit());
        }
        List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList_del = t.getKonkaOrderInfoDetailsListForDel();
        if (konkaOrderInfoDetailsList_del != null && konkaOrderInfoDetailsList_del.size() > 0) {
            for (KonkaOrderInfoDetails kkoid : konkaOrderInfoDetailsList_del) {// 删除订单详细产品
                this.konkaOrderInfoDetailsDao.deleteEntity(kkoid);
            }
        }
        List<KonkaOrderInfoDetailsAudit> konkaOrderInfoDetailsAuditList_update = t.getKonkaOrderInfoDetailsAuditListForUpdate();
        if (konkaOrderInfoDetailsAuditList_update != null && konkaOrderInfoDetailsAuditList_update.size() > 0) {
            for (KonkaOrderInfoDetailsAudit kkoid : konkaOrderInfoDetailsAuditList_update) {// 更改
                // 审核产品记录
                this.konkaOrderInfoDetailsAuditDao.updateEntity(kkoid);
            }
        }

        List<KonkaOrderInfoDetailsAudit> konkaOrderInfoDetailsAuditList_add = t.getKonkaOrderInfoDetailsAuditList();
        if (konkaOrderInfoDetailsAuditList_add != null && konkaOrderInfoDetailsAuditList_add.size() > 0) {
            for (KonkaOrderInfoDetailsAudit kkoid : konkaOrderInfoDetailsAuditList_add) {// 添加
                // 审核产品记录
                this.konkaOrderInfoDetailsAuditDao.insertEntity(kkoid);
            }
        }

        KonkaR3Shop r3shop = new KonkaR3Shop();
        //System.out.println("cust_id====" + t.getCust_id());
        if (null != t.getCust_id()) {
            r3shop.setId(t.getCust_id());
            r3shop = konkaR3ShopDao.selectEntity(r3shop);
        }

        List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = t.getKonkaOrderInfoDetailsList();
        if (konkaOrderInfoDetailsList != null && konkaOrderInfoDetailsList.size() > 0) {
            for (KonkaOrderInfoDetails kkoid : konkaOrderInfoDetailsList) {// 添加

                Date today = new Date();
                int weeksAmouts = check_for_stock("check_for_stock") == 0 ? 4 : check_for_stock("check_for_stock");
                Date beginTime = DateUtils.addDays(today, weeksAmouts * -7);
                JBaseGoods jBaseGoods = new JBaseGoods();
                jBaseGoods.setIs_konka(1);
                jBaseGoods.setGoods_stutes(0);
                jBaseGoods.setC_id(t.getCust_id());
                jBaseGoods.setGoods_name(kkoid.getPd_name());
                List<JBaseGoods> jBaseGoodsList = jBaseGoodsDao.selectEntityList(jBaseGoods);
                Long Jbill_tuiHuo_count = 0l;
                Long Jbill_sale_count = 0l;
                Long order_info_count = 0l;
                if (null != r3shop) {
                    if (null != jBaseGoodsList && jBaseGoodsList.size() > 0) {
                        // 获取销售退货数量
                        JBill Jbill = new JBill();
                        Jbill.setBill_type(21);
                        Jbill.getMap().put("goods_id", jBaseGoodsList.get(0).getGoods_id());
                        Jbill.getMap().put("r3_id", r3shop.getId());
                        Jbill.getMap().put("opr_date_gt", DateUtils.formatDay(beginTime));
                        Jbill.getMap().put("opr_date_lt", DateUtils.formatDay(today));
                        Jbill_tuiHuo_count = jBillDao.selectCountForFourWeek(Jbill);
                        // 获取销售销售数量
                        JBill Jbill2 = new JBill();
                        Jbill2.setBill_type(20);
                        Jbill2.getMap().put("goods_id", jBaseGoodsList.get(0).getGoods_id());
                        Jbill2.getMap().put("r3_id", r3shop.getId());
                        Jbill2.getMap().put("opr_date_gt", DateUtils.formatDay(beginTime));
                        Jbill2.getMap().put("opr_date_lt", DateUtils.formatDay(today));
                        Jbill_sale_count = jBillDao.selectCountForFourWeek(Jbill2);
                        // 查询前四周的零售量
                        JSubSellRec jSubSellRec = new JSubSellRec();
                        jSubSellRec.setStatus(1);
                        jSubSellRec.getMap().put("r3_id", r3shop.getId());
                        jSubSellRec.getMap().put("add_date_gt", DateUtils.formatDay(beginTime));
                        jSubSellRec.getMap().put("add_date_lt", DateUtils.formatDay(today));
                        jSubSellRec.getMap().put("goods_id", jBaseGoodsList.get(0).getGoods_id());
                        order_info_count = jSubSellRecDao.selectJSubSellRecCountForFourWeek(jSubSellRec);
                    }
                    // 获得手机端销售数量
                    KonkaMobileSailData konkaMobileSailData = new KonkaMobileSailData();
                    konkaMobileSailData.getMap().put("opr_date_gt", DateUtils.formatDay(beginTime));
                    konkaMobileSailData.getMap().put("opr_date_lt", DateUtils.formatDay(today));
                    konkaMobileSailData.setIs_del(0);
                    konkaMobileSailData.setCustomer_r3_code(r3shop.getR3_code());
                    konkaMobileSailData.setModel_name(kkoid.getPd_name());
                    Long mobile_count = konkaMobileSailDataDao.selectKonkaMobileSailDataCountForFourWeek(konkaMobileSailData);
                    // 查询前四周的专卖店零售量
                    KonkaOrderInfo order3 = new KonkaOrderInfo();
                    order3.setIs_del(0);
                    order3.setZmd_order_flag(1);
                    order3.setR3_id(r3shop.getId());
                    order3.setCust_id(t.getCust_id());
                    order3.getMap().put("pd_id", kkoid.getPd_id());
                    order3.getMap().put("start_date", DateUtils.formatDay(beginTime));
                    order3.getMap().put("end_date", DateUtils.formatDay(today));
                    Long order_info_zmd_count = konkaOrderInfoDao.selectKonkaOrderInfoWithNoForFourWeekCount(order3);

                    if (null == mobile_count) {
                        mobile_count = 0l;
                    }
                    Long order_fourweek_count = order_info_zmd_count + order_info_count + mobile_count + Jbill_sale_count - Jbill_tuiHuo_count;

                    // 获得库存
                    Long curr_ku_count = getKhStocks(r3shop.getR3_code(), kkoid.getPd_name(), null);

                    BigDecimal bl = getBl(new Date(), r3shop.getR3_code(), kkoid.getPd_name());
                    curr_ku_count = curr_ku_count * Long.valueOf(bl.toString());

                    // 插入三个信息
                    kkoid.setSale_count(order_fourweek_count);
                    kkoid.setStore_num(curr_ku_count);
                    kkoid.setAdd_date(new Date());
                }
                // 订单详细产品
                this.konkaOrderInfoDetailsDao.insertEntity(kkoid);
            }
        }
        List<KonkaOrderInfoUpdateRecord> konkaOrderInfoUpdateRecordList = t.getKonkaOrderInfoUpdateRecordList();
        if (konkaOrderInfoUpdateRecordList != null && konkaOrderInfoUpdateRecordList.size() > 0) {
            for (KonkaOrderInfoUpdateRecord kkoiur : konkaOrderInfoUpdateRecordList) {// 添加
                // 订单修改记录
                this.konkaOrderInfoUpdateRecordDao.insertEntity(kkoiur);
            }
        }
        int id = this.konkaOrderInfoDao.updateEntity(t);
        return id;
    }

    @Override
    public int removeKonkaOrderInfo(KonkaOrderInfo t) {
        return this.konkaOrderInfoDao.deleteEntity(t);
    }

    @Override
    public List<KonkaOrderInfo> getKonkaOrderInfoPaginatedListWithShopName(KonkaOrderInfo t) {
        return this.konkaOrderInfoDao.selectKonkaOrderInfoPaginatedListWithShopName(t);
    }

    @Override
    public List<KonkaOrderInfo> getKonkaOrderInfoPaginatedList(KonkaOrderInfo t) {
        return this.konkaOrderInfoDao.selectEntityPaginatedList(t);
    }

    /**
     * @author Ren,zhong
     * @date 2013-06-09
     */
    @Override
    public List<KonkaOrderInfo> getKonkaOrderInfoResultForPaginatedList(KonkaOrderInfo t) {
        return this.konkaOrderInfoDao.selectKonkaOrderInfoResultForPaginatedList(t);
    }

    @Resource
    private AttachmentDao attachmentDao;

    @Resource
    private KonkaMobileTerminalFeedbackDao konkaMobileTerminalFeedbackDao;

    public Long getWeekSaleAmount(KonkaOrderInfo orderInfo, KonkaOrderInfoDetails konkaOrderInfoDetails, KonkaR3Shop r3shop, int week) {

        Long order_fourweek_count = 0l;
        Date today = new Date();
        int weeksAmouts = week == 0 ? 4 : week;
        Date beginTime = DateUtils.addDays(today, weeksAmouts * -7);
        JBaseGoods jBaseGoods = new JBaseGoods();
        jBaseGoods.setIs_konka(1);
        jBaseGoods.setGoods_stutes(0);
        jBaseGoods.setC_id(orderInfo.getCust_id());
        jBaseGoods.setGoods_name(konkaOrderInfoDetails.getPd_name());
        List<JBaseGoods> jBaseGoodsList = jBaseGoodsDao.selectEntityList(jBaseGoods);
        Long Jbill_tuiHuo_count = 0l;
        Long Jbill_sale_count = 0l;
        Long order_info_count = 0l;
        if (null != r3shop) {
            if (null != jBaseGoodsList && jBaseGoodsList.size() > 0) {
                // 获取销售退货数量
                JBill Jbill = new JBill();
                Jbill.setBill_type(21);
                Jbill.getMap().put("goods_id", jBaseGoodsList.get(0).getGoods_id());
                Jbill.getMap().put("r3_id", r3shop.getId());
                Jbill.getMap().put("opr_date_gt", DateUtils.formatDay(beginTime));
                Jbill.getMap().put("opr_date_lt", DateUtils.formatDay(today));
                Jbill_tuiHuo_count = jBillDao.selectCountForFourWeek(Jbill);
                // 获取销售销售数量
                JBill Jbill2 = new JBill();
                Jbill2.setBill_type(20);
                Jbill2.getMap().put("goods_id", jBaseGoodsList.get(0).getGoods_id());
                Jbill2.getMap().put("r3_id", r3shop.getId());
                Jbill2.getMap().put("opr_date_gt", DateUtils.formatDay(beginTime));
                Jbill2.getMap().put("opr_date_lt", DateUtils.formatDay(today));
                Jbill_sale_count = jBillDao.selectCountForFourWeek(Jbill2);
                // 查询前四周的零售量
                JSubSellRec jSubSellRec = new JSubSellRec();
                jSubSellRec.setStatus(1);
                jSubSellRec.getMap().put("r3_id", r3shop.getId());
                jSubSellRec.getMap().put("add_date_gt", DateUtils.formatDay(beginTime));
                jSubSellRec.getMap().put("add_date_lt", DateUtils.formatDay(today));
                jSubSellRec.getMap().put("goods_id", jBaseGoodsList.get(0).getGoods_id());
                order_info_count = jSubSellRecDao.selectJSubSellRecCountForFourWeek(jSubSellRec);
            }
            // 获得手机端销售数量
            KonkaMobileSailData konkaMobileSailData = new KonkaMobileSailData();
            konkaMobileSailData.getMap().put("opr_date_gt", DateUtils.formatDay(beginTime));
            konkaMobileSailData.getMap().put("opr_date_lt", DateUtils.formatDay(today));
            konkaMobileSailData.setIs_del(0);
            konkaMobileSailData.setCustomer_r3_code(r3shop.getR3_code());
            konkaMobileSailData.setModel_name(konkaOrderInfoDetails.getPd_name());
            Long mobile_count = konkaMobileSailDataDao.selectKonkaMobileSailDataCountForFourWeek(konkaMobileSailData);
            // 查询前四周的专卖店零售量
            KonkaOrderInfo order3 = new KonkaOrderInfo();
            order3.setIs_del(0);
            order3.setZmd_order_flag(1);
            order3.setR3_id(r3shop.getId());
            order3.setCust_id(orderInfo.getCust_id());
            order3.getMap().put("pd_id", konkaOrderInfoDetails.getPd_id());
            order3.getMap().put("start_date", DateUtils.formatDay(beginTime));
            order3.getMap().put("end_date", DateUtils.formatDay(today));
            Long order_info_zmd_count = konkaOrderInfoDao.selectKonkaOrderInfoWithNoForFourWeekCount(order3);

            if (null == mobile_count) {
                mobile_count = 0l;
            }
            order_fourweek_count = order_info_zmd_count + order_info_count + mobile_count + Jbill_sale_count - Jbill_tuiHuo_count;

        }
        return order_fourweek_count;
    }

    /**
     * @author Ren,zhong
     * @date 2013-08-06
     * 
     * @author zhou
     * @date 20150610
     */
    @Override
    public Long createKonkaOrderInfoOrder(KonkaOrderInfo t) {

        // 此实时必须一开始就创建,保持数据准确
        final Date today = new Date();
        Long order_id = 0L;
        String save_type = (String) t.getMap().get("save_type");
        String content1 = (String) t.getMap().get("content1");// 留言与附件
        String tel1 = (String) t.getMap().get("tel1");
        if ("insert".equals(save_type)) {
            t.setAdd_date(today);
            // 为了兼容DRP系统,自定义的订单日期
            if (t.getOrder_date() == null || "".equals(t.getOrder_date())) {
                t.setOrder_date(new Date());
            }
            order_id = konkaOrderInfoDao.insertEntity(t);
            // 如果是新增订单，先判断该订单的意见反馈是否已经存在，如果已经存在，不再添加
            KonkaMobileTerminalFeedback kt = new KonkaMobileTerminalFeedback();
            kt.getMap().put("have_where", true);
            kt.setTrade_index(t.getTrade_index());
            Long count = this.konkaMobileTerminalFeedbackDao.selectEntityCount(kt);
            if (count == 0) {
                if (null != content1 || "".equals(content1)) {
                    KonkaMobileTerminalFeedback kf = new KonkaMobileTerminalFeedback();
                    kf.setTrade_index(t.getTrade_index());
                    kf.setAdd_date(new Date());
                    if (t.getAdd_dept_id() != null) {
                        kf.setDept_id(t.getAdd_dept_id());
                    }
                    if (t.getAdd_dept_name() != null) {
                        kf.setDept_name(t.getAdd_dept_name());
                    }
                    kf.setContent(content1);
                    kf.setTel(tel1);
                    kf.setQuestion_id(t.getAdd_user_id());
                    kf.setQuestion_person(t.getAdd_user_name());
                    kf.setIs_del(0);
                    kf.setMessage_from(2);// 意见反馈
                    kf.setMessage_type(8);// 问题咨询
                    this.konkaMobileTerminalFeedbackDao.insertEntity(kf);
                }
            }

            KonkaR3Shop r3shop = new KonkaR3Shop();
            r3shop.setId(t.getCust_id());
            r3shop = konkaR3ShopDao.selectEntity(r3shop);

            List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = t.getKonkaOrderInfoDetailsList();
            for (KonkaOrderInfoDetails konkaOrderInfoDetails : konkaOrderInfoDetailsList) {
                konkaOrderInfoDetails.setOrder_id(order_id);
                int weeksAmouts = check_for_stock("check_for_stock") == 0 ? 4 : check_for_stock("check_for_stock");
                Date beginTime = DateUtils.addDays(today, weeksAmouts * -7);
                JBaseGoods jBaseGoods = new JBaseGoods();
                jBaseGoods.setIs_konka(1);
                jBaseGoods.setGoods_stutes(0);
                jBaseGoods.setC_id(t.getCust_id());
                jBaseGoods.setGoods_name(konkaOrderInfoDetails.getPd_name());
                List<JBaseGoods> jBaseGoodsList = jBaseGoodsDao.selectEntityList(jBaseGoods);
                Long Jbill_tuiHuo_count = 0l;
                Long Jbill_sale_count = 0l;
                Long order_info_count = 0l;
                if (null != r3shop) {
                    if (null != jBaseGoodsList && jBaseGoodsList.size() > 0) {
                        // 获取销售退货数量
                        JBill Jbill = new JBill();
                        Jbill.setBill_type(21);
                        Jbill.getMap().put("goods_id", jBaseGoodsList.get(0).getGoods_id());
                        Jbill.getMap().put("r3_id", r3shop.getId());
                        Jbill.getMap().put("opr_date_gt", DateUtils.formatDay(beginTime));
                        Jbill.getMap().put("opr_date_lt", DateUtils.formatDay(today));
                        Jbill_tuiHuo_count = jBillDao.selectCountForFourWeek(Jbill);
                        // 获取销售销售数量
                        JBill Jbill2 = new JBill();
                        Jbill2.setBill_type(20);
                        Jbill2.getMap().put("goods_id", jBaseGoodsList.get(0).getGoods_id());
                        Jbill2.getMap().put("r3_id", r3shop.getId());
                        Jbill2.getMap().put("opr_date_gt", DateUtils.formatDay(beginTime));
                        Jbill2.getMap().put("opr_date_lt", DateUtils.formatDay(today));
                        Jbill_sale_count = jBillDao.selectCountForFourWeek(Jbill2);
                        // 查询前四周的零售量
                        JSubSellRec jSubSellRec = new JSubSellRec();
                        jSubSellRec.setStatus(1);
                        jSubSellRec.getMap().put("r3_id", r3shop.getId());
                        jSubSellRec.getMap().put("add_date_gt", DateUtils.formatDay(beginTime));
                        jSubSellRec.getMap().put("add_date_lt", DateUtils.formatDay(today));
                        jSubSellRec.getMap().put("goods_id", jBaseGoodsList.get(0).getGoods_id());
                        order_info_count = jSubSellRecDao.selectJSubSellRecCountForFourWeek(jSubSellRec);
                    }
                    // 获得手机端销售数量
                    KonkaMobileSailData konkaMobileSailData = new KonkaMobileSailData();
                    konkaMobileSailData.getMap().put("opr_date_gt", DateUtils.formatDay(beginTime));
                    konkaMobileSailData.getMap().put("opr_date_lt", DateUtils.formatDay(today));
                    konkaMobileSailData.setIs_del(0);
                    konkaMobileSailData.setCustomer_r3_code(r3shop.getR3_code());
                    konkaMobileSailData.setModel_name(konkaOrderInfoDetails.getPd_name());
                    Long mobile_count = konkaMobileSailDataDao.selectKonkaMobileSailDataCountForFourWeek(konkaMobileSailData);
                    // 查询前四周的专卖店零售量
                    KonkaOrderInfo order3 = new KonkaOrderInfo();
                    order3.setIs_del(0);
                    order3.setZmd_order_flag(1);
                    order3.setR3_id(r3shop.getId());
                    order3.setCust_id(t.getCust_id());
                    order3.getMap().put("pd_id", konkaOrderInfoDetails.getPd_id());
                    order3.getMap().put("start_date", DateUtils.formatDay(beginTime));
                    order3.getMap().put("end_date", DateUtils.formatDay(today));
                    Long order_info_zmd_count = konkaOrderInfoDao.selectKonkaOrderInfoWithNoForFourWeekCount(order3);

                    if (null == mobile_count) {
                        mobile_count = 0l;
                    }
                    Long order_fourweek_count = order_info_zmd_count + order_info_count + mobile_count + Jbill_sale_count - Jbill_tuiHuo_count;
                    // 获得库存
                    Long curr_ku_count = getKhStocks(r3shop.getR3_code(), konkaOrderInfoDetails.getPd_name(), null);

                    BigDecimal bl = getBl(new Date(), r3shop.getR3_code(), konkaOrderInfoDetails.getPd_name());
                    curr_ku_count = curr_ku_count * Long.valueOf(bl.toString());

                    // 插入三个信息
                    konkaOrderInfoDetails.setSale_count(order_fourweek_count);

                    Long order_fourweek_count_4 = 0l;
                    Long order_fourweek_count_6 = 0l;
                    Long order_fourweek_count_8 = 0l;
                    Long order_fourweek_count_10 = 0l;
                    // 获取前4周的销量
                    order_fourweek_count_4 = this.getWeekSaleAmount(t, konkaOrderInfoDetails, r3shop, 4);

                    // 获取前6周的销量
                    order_fourweek_count_6 = this.getWeekSaleAmount(t, konkaOrderInfoDetails, r3shop, 6);

                    // 获取前8周的销量
                    order_fourweek_count_8 = this.getWeekSaleAmount(t, konkaOrderInfoDetails, r3shop, 8);
                    // 获取前10周的销量
                    order_fourweek_count_10 = this.getWeekSaleAmount(t, konkaOrderInfoDetails, r3shop, 10);

                    konkaOrderInfoDetails.setSale_count_4(new BigDecimal(order_fourweek_count_4));
                    konkaOrderInfoDetails.setSale_count_6(new BigDecimal(order_fourweek_count_6));
                    konkaOrderInfoDetails.setSale_count_8(new BigDecimal(order_fourweek_count_8));
                    konkaOrderInfoDetails.setSale_count_01_add(new BigDecimal(order_fourweek_count_10));
                    konkaOrderInfoDetails.setStore_num(curr_ku_count);
                    konkaOrderInfoDetails.setAdd_date(new Date());
                }
                konkaOrderInfoDetailsDao.insertEntity(konkaOrderInfoDetails);// 添加订单详细产品
            }
        } else if ("update".equals(save_type)) {
            order_id = t.getId();
            KonkaOrderInfoDetails koid = new KonkaOrderInfoDetails();
            koid.setOrder_id(order_id);
            this.konkaOrderInfoDetailsDao.deleteEntity(koid);

            KonkaOrderInfo ko = new KonkaOrderInfo();
            ko.setId(t.getId());
            ko = this.konkaOrderInfoDao.selectEntity(ko);

            // 如果是修改，先判断该订单的意见反馈是否已经存在，如果已经存在，再修改
            KonkaMobileTerminalFeedback kt = new KonkaMobileTerminalFeedback();
            kt.getMap().put("have_where", true);
            kt.setTrade_index(ko.getTrade_index());
            Long count = this.konkaMobileTerminalFeedbackDao.selectEntityCount(kt);
            if (count == 1) {
                if (null != content1 || "".equals(content1)) {
                    kt = this.konkaMobileTerminalFeedbackDao.selectEntity(kt);
                    kt.setContent(content1);
                    kt.setTel(tel1);
                    this.konkaMobileTerminalFeedbackDao.updateEntity(kt);
                }
            }

            KonkaR3Shop r3shop = new KonkaR3Shop();
            r3shop.setId(t.getCust_id());
            r3shop = konkaR3ShopDao.selectEntity(r3shop);

            List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = t.getKonkaOrderInfoDetailsList();
            if (konkaOrderInfoDetailsList != null && konkaOrderInfoDetailsList.size() > 0) {
                for (KonkaOrderInfoDetails kkoid : konkaOrderInfoDetailsList) {// 添加
                    // 订单详细产品
                    kkoid.setOrder_id(order_id);
                    if (null != r3shop) {
                        Date beginTime = DateUtils.addDays(today, -28);
                        JBaseGoods jBaseGoods = new JBaseGoods();
                        jBaseGoods.setIs_konka(1);
                        jBaseGoods.setGoods_stutes(0);
                        jBaseGoods.setC_id(t.getCust_id());
                        jBaseGoods.setGoods_name(kkoid.getPd_name());
                        List<JBaseGoods> jBaseGoodsList = jBaseGoodsDao.selectEntityList(jBaseGoods);
                        Long Jbill_tuiHuo_count = 0l;
                        Long Jbill_sale_count = 0l;
                        Long order_info_count = 0l;
                        if (null != r3shop) {
                            if (null != jBaseGoodsList && jBaseGoodsList.size() > 0) {
                                // 获取销售退货数量
                                JBill Jbill = new JBill();
                                Jbill.setBill_type(21);
                                Jbill.getMap().put("goods_id", jBaseGoodsList.get(0).getGoods_id());
                                Jbill.getMap().put("r3_id", r3shop.getId());
                                Jbill.getMap().put("opr_date_gt", DateUtils.formatDay(beginTime));
                                Jbill.getMap().put("opr_date_lt", DateUtils.formatDay(today));
                                Jbill_tuiHuo_count = jBillDao.selectCountForFourWeek(Jbill);
                                // 获取销售销售数量
                                JBill Jbill2 = new JBill();
                                Jbill2.setBill_type(20);
                                Jbill2.getMap().put("goods_id", jBaseGoodsList.get(0).getGoods_id());
                                Jbill2.getMap().put("r3_id", r3shop.getId());
                                Jbill2.getMap().put("opr_date_gt", DateUtils.formatDay(beginTime));
                                Jbill2.getMap().put("opr_date_lt", DateUtils.formatDay(today));
                                Jbill_sale_count = jBillDao.selectCountForFourWeek(Jbill2);
                                // 查询前四周的零售量
                                JSubSellRec jSubSellRec = new JSubSellRec();
                                jSubSellRec.setStatus(1);
                                jSubSellRec.getMap().put("r3_id", r3shop.getId());
                                jSubSellRec.getMap().put("add_date_gt", DateUtils.formatDay(beginTime));
                                jSubSellRec.getMap().put("add_date_lt", DateUtils.formatDay(today));
                                jSubSellRec.getMap().put("goods_id", jBaseGoodsList.get(0).getGoods_id());
                                order_info_count = jSubSellRecDao.selectJSubSellRecCountForFourWeek(jSubSellRec);
                            }
                            // 获得手机端销售数量
                            KonkaMobileSailData konkaMobileSailData = new KonkaMobileSailData();
                            konkaMobileSailData.getMap().put("opr_date_gt", DateUtils.formatDay(beginTime));
                            konkaMobileSailData.getMap().put("opr_date_lt", DateUtils.formatDay(today));
                            konkaMobileSailData.setIs_del(0);
                            konkaMobileSailData.setCustomer_r3_code(r3shop.getR3_code());
                            konkaMobileSailData.setModel_name(kkoid.getPd_name());
                            Long mobile_count = konkaMobileSailDataDao.selectKonkaMobileSailDataCountForFourWeek(konkaMobileSailData);
                            // 查询前四周的专卖店零售量
                            KonkaOrderInfo order3 = new KonkaOrderInfo();
                            order3.setIs_del(0);
                            order3.setZmd_order_flag(1);
                            order3.setR3_id(r3shop.getId());
                            order3.setCust_id(t.getCust_id());
                            order3.getMap().put("pd_id", kkoid.getPd_id());
                            order3.getMap().put("start_date", DateUtils.formatDay(beginTime));
                            order3.getMap().put("end_date", DateUtils.formatDay(today));
                            Long order_info_zmd_count = konkaOrderInfoDao.selectKonkaOrderInfoWithNoForFourWeekCount(order3);

                            if (null == mobile_count) {
                                mobile_count = 0l;
                            }
                            Long order_fourweek_count = order_info_zmd_count + order_info_count + mobile_count + Jbill_sale_count - Jbill_tuiHuo_count;
                            // 获得库存
                            Long curr_ku_count = getKhStocks(r3shop.getR3_code(), kkoid.getPd_name(), null);

                            BigDecimal bl = getBl(new Date(), r3shop.getR3_code(), kkoid.getPd_name());
                            curr_ku_count = curr_ku_count * Long.valueOf(bl.toString());

                            // 插入三个信息
                            kkoid.setSale_count(order_fourweek_count);


                            Long order_fourweek_count_4 = 0l;
                            Long order_fourweek_count_6 = 0l;
                            Long order_fourweek_count_8 = 0l;
                            // 获取前4周的销量
                            order_fourweek_count_4 = this.getWeekSaleAmount(t, kkoid, r3shop, 4);

                            // 获取前6周的销量
                            order_fourweek_count_6 = this.getWeekSaleAmount(t, kkoid, r3shop, 6);

                            // 获取前8周的销量
                            order_fourweek_count_8 = this.getWeekSaleAmount(t, kkoid, r3shop, 8);

                            kkoid.setSale_count_4(new BigDecimal(order_fourweek_count_4));
                            kkoid.setSale_count_6(new BigDecimal(order_fourweek_count_6));
                            kkoid.setSale_count_8(new BigDecimal(order_fourweek_count_8));


                            kkoid.setStore_num(curr_ku_count);
                            kkoid.setAdd_date(new Date());
                        }
                    }
                    this.konkaOrderInfoDetailsDao.insertEntity(kkoid);
                }
            }
            this.konkaOrderInfoDao.updateEntity(t);// 修改主订单
        }

        Attachment attachment = new Attachment();
        attachment.setLink_id(order_id);
        this.attachmentDao.deleteEntity(attachment);
        List<Attachment> attachmentList = t.getAttachmentList();
        if (null != attachmentList && attachmentList.size() > 0) {
            for (Attachment att : attachmentList) {
                att.setLink_id(order_id);
                this.attachmentDao.insertEntity(att);
            }
        }

        return order_id;
    }


    // ==============================需要验证的方法=================================
    /**
     * @author Wu,ShangLong
     * @date 2013-08-07
     */
    @Override
    public Long getKonkaOrderInfoWithNoSysManCount(KonkaOrderInfo t) {
        return this.konkaOrderInfoDao.selectKonkaOrderInfoWithNoSysManCount(t);
    }

    /**
     * @author Wu,ShangLong
     * @date 2013-08-07
     */
    @Override
    public List<KonkaOrderInfo> getKonkaOrderInfoWithNoSysManPaginatedList(KonkaOrderInfo t) {
        return this.konkaOrderInfoDao.selectKonkaOrderInfoWithNoSysManPaginatedList(t);
    }

    /**
     * @author Wu,ShangLong
     * @date 2013-08-07
     */
    @Override
    public Long getKonkaOrderInfoAndNextRoleCount(KonkaOrderInfo t) {
        return this.konkaOrderInfoDao.selectKonkaOrderInfoAndNextRoleCount(t);
    }

    /**
     * @author Wu,ShangLong
     * @date 2013-08-07
     */
    @Override
    public List<KonkaOrderInfo> getKonkaOrderInfoAndNextRoleResultForPaginatedList(KonkaOrderInfo t) {
        return this.konkaOrderInfoDao.selectKonkaOrderInfoAndNextRoleResultForPaginatedList(t);
    }



    @Override
    public List<KonkaOrderInfo> getKonkaOrderInfoAndNextRoleResultForPaginatedList1(KonkaOrderInfo t) {
        
        return konkaOrderInfoDao.selectKonkaOrderInfoAndNextRoleResultForPaginatedList1(t);
    }

    @Override
    public Long getKonkaOrderInfoAndNextRoleResultForPaginatedListcount1(KonkaOrderInfo t) {
        return konkaOrderInfoDao.selectKonkaOrderInfoAndNextRoleResultForPaginatedListcount1(t);
    }


    // ==============================需要验证的方法==============================


    /**
     * @author Pan,Gang
     * @date 2013-08-26
     */
    @Override
    public Long getKonkaOrderInfoSearchforPdNameCount(KonkaOrderInfo t) {
        return this.konkaOrderInfoDao.selectKonkaOrderInfoSearchforPdNameCount(t);
    }

    @Override
    public List<KonkaOrderInfo> getKonkaOrderInfoResultForR3CodePaginatedList(KonkaOrderInfo t) {
        return this.konkaOrderInfoDao.selectKonkaOrderInfoResultForR3CodePaginatedList(t);
    }

    @Override
    public Long getKonkaOrderInfoSearchforR3CodeCount(KonkaOrderInfo t) {
        return this.konkaOrderInfoDao.selectKonkaOrderInfoSearchforR3CodeCount(t);
    }

    /**
     * @author Hu,Hao
     * @date 2013-09-08
     */
    @Override
    public int modifyKonkaOrderInfoForconfirm(KonkaOrderInfo t) {
        int id = 0;
        id = this.konkaOrderInfoDao.updateEntity(t);

        // 专卖店销售单
        if (null != t.getKonkaXxSellBill()) {
            KonkaXxSellBill entity = t.getKonkaXxSellBill();
            this.konkaXxSellBillDao.updateEntity(entity);
        }

        return id;
    }

    @Override
    public Long getKonkaOrderInfoWithNoForFourWeekCount(KonkaOrderInfo t) {
        return this.konkaOrderInfoDao.selectKonkaOrderInfoWithNoForFourWeekCount(t);
    }

    protected Long getKhStocksOld(String r3_code, String md_name, Long store_id) {

        Long stocks = 0L;

        if (StringUtils.isBlank(md_name) || StringUtils.isBlank(r3_code)) {
            return stocks;
        }

        // 客户信息
        KonkaR3Shop kShop = new KonkaR3Shop();
        kShop.setR3_code(r3_code);
        List<KonkaR3Shop> kShopList = konkaR3ShopDao.selectEntityList(kShop);

        if (kShopList.size() == 1) {
            kShop = kShopList.get(0);
        } else {
            return stocks;
        }

        // 产品初始化信息
        JBaseGoods jGoods = new JBaseGoods();
        jGoods.setC_id(kShop.getId());
        jGoods.setGoods_name(md_name);
        List<JBaseGoods> jGoodsList = jBaseGoodsDao.selectEntityList(jGoods);
        if (jGoodsList.size() == 1) {
            jGoods = jGoodsList.get(0);
        } else {
            return stocks;
        }

        // 总库
        JBaseStore jStore = new JBaseStore();
        jStore.setC_id(kShop.getId());
        jStore.setStore_name("总库");
        List<JBaseStore> jStoreList = jBaseStoreDao.selectEntityList(jStore);
        if (jStoreList.size() == 1) {
            jStore = jStoreList.get(0);
        } else {
            return stocks;
        }

        Date new_date = null;

        if (null != store_id && String.valueOf(jStore.getStore_id()).equals(String.valueOf(store_id))) {

            // 取最后盘存时间
            JStocksVerify jstocks = new JStocksVerify();
            jstocks.getMap().put("max_date_id", true);
            jstocks.getMap().put("store_id_value", store_id);
            jstocks.getMap().put("goods_id_value", jGoods.getGoods_id());
            jstocks = jStocksVerifyDao.selectEntity(jstocks);

            if (jstocks == null) {
                return stocks;
            } else {
                stocks = jstocks.getVer_stocks();
            }

        } else {

            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // 取最后盘存时间
            JStocksVerify jstocks = new JStocksVerify();
            jstocks.getMap().put("max_date_id", true);
            jstocks.getMap().put("store_id_value", jStore.getStore_id());
            jstocks.getMap().put("goods_id_value", jGoods.getGoods_id());
            jstocks.setStore_id(jStore.getStore_id());
            jstocks.setGoods_id(jGoods.getGoods_id());
            jstocks = jStocksVerifyDao.selectEntity(jstocks);

            if (jstocks == null) {
                new_date = jGoods.getAdd_date();

            } else {
                new_date = jstocks.getOpr_date();

            }

            JBaseGoods entity = new JBaseGoods();
            entity.getMap().put("r3_code", r3_code);
            entity.getMap().put("r3_id", kShop.getId());
            entity.getMap().put("md_name", md_name);
            entity.getMap().put("goods_id", jGoods.getGoods_id());
            entity.getMap().put("s_date", fmt.format(new_date));

            entity = jBaseGoodsDao.selectJBaseGoodsForNum(entity);

            if (jstocks == null) {
                stocks = Long.valueOf(entity.getMap().get("num").toString()) + jGoods.getInit_count();
            } else {
                stocks = Long.valueOf(entity.getMap().get("num").toString()) + jstocks.getVer_stocks();
            }
        }

        return stocks;
    }

    /**
     * @author Xiao,GuoJian
     * @version 2014-07-08
     * @desc 根据R3_CODE,md_name,store_id取库存
     * @desc 返回库存=init_num+come_num-out_num
     */
    protected Long getKhStocks(String r3_code, String md_name, Long store_id) {
        Long result = 0L;
        if (StringUtils.isEmpty(r3_code) || StringUtils.isEmpty(md_name)) {
            return 0L;
        }
        KonkaR3Shop kShop = new KonkaR3Shop();
        kShop.setR3_code(r3_code);
        List<KonkaR3Shop> kShopList = konkaR3ShopDao.selectEntityList(kShop);
        if (kShopList != null && kShopList.size() > 0) {
            kShop = kShopList.get(0);
        } else {
            return result;
        }
        // 产品初始化信息
        JBaseGoods jGoods = new JBaseGoods();
        jGoods.setC_id(kShop.getId());
        jGoods.setGoods_name(md_name);
        List<JBaseGoods> jGoodsList = jBaseGoodsDao.selectEntityList(jGoods);
        if (jGoodsList.size() > 0) {
            jGoods = jGoodsList.get(0);
        } else {
            return result;
        }
        JBaseGoods jbg = new JBaseGoods();
        if (null != store_id) {// 送达方
            jbg.getMap().put("store_id", store_id);
        } else {
            jbg.getMap().put("r3_code", r3_code);
        }
        jbg.getMap().put("goods_id", jGoods.getGoods_id());
        jbg.getMap().put("md_name", jGoods.getGoods_name());
        jbg.getMap().put("r3_id", kShop.getId());

        // 原来的查库存
        // jbg = jBaseGoodsDao.selectJBaseGoodsForComeOutNumNew(jbg);

        // 现在的查库存 wangkunlin 2015-01-23修改
        List<JBaseGoods> jBaseList = jBaseGoodsDao.selectJBaseGoodsForComeOutNumWithMoney(jbg);

        // jBaseGoodsDao.getJBaseGoodsList(jbg);

        if (null != jBaseList && jBaseList.size() == 1) {
            jbg = jBaseList.get(0);

            if (jbg != null) {
                long come_num = Long.valueOf(jbg.getMap().get("come_num").toString());
                long out_num = Long.valueOf(jbg.getMap().get("out_num").toString());
                long init_num = Long.valueOf(jbg.getMap().get("init_num").toString());
                result = init_num + come_num - out_num;
            }
        }
        return result;
    }

    public BigDecimal getBl(Date date, String R3_code, String pd_name) {

        BigDecimal test = new BigDecimal("1");
        return test;
    }


    @Override
    public List<KonkaOrderInfo> getKonkaOrderInfoResultForR3CodeforCbPaginatedList(KonkaOrderInfo entity) {
        
        return konkaOrderInfoDao.selectKonkaOrderInfoResultForR3CodeforCbPaginatedList(entity);
    }

    @Override
    public Long getKonkaOrderInfoSearchforR3CodeandforcbCount(KonkaOrderInfo entity) {
        
        return konkaOrderInfoDao.selectKonkaOrderInfoSearchforR3CodeandforcbCount(entity);
    }

    protected int check_for_stock(String string) {
        SysSetting sysSetting = new SysSetting();
        sysSetting.setTitle(string);
        int weeksAmouts = 0;
        sysSetting = this.sysSettingDao.selectEntity(sysSetting);
        if (null != sysSetting && null != sysSetting.getContent()) {// 如果有记录，按照系统中设置的为准
            weeksAmouts = Integer.parseInt(sysSetting.getContent());
            // beginTime = DateUtils.addDays(today,daysAmount);
        }
        return weeksAmouts;

    }

    @Override
    public List<KonkaOrderInfo> getKonkaOrderInfoResultForR3CodeforCbList(KonkaOrderInfo entity) {
        
        return konkaOrderInfoDao.selectKonkaOrderInfoResultForR3CodeforCbList(entity);
    }

    @Override
    public List<HashMap> getKonkaOrderInfoDetailsList(KonkaOrderInfo entity) {
        return konkaOrderInfoDao.selectKonkaOrderInfoDetailsList(entity);
    }

    @Override
    public Long getKonkaOrderInfoDetailsCount(KonkaOrderInfo entity) {
        return konkaOrderInfoDao.selectKonkaOrderInfoDetailsCount(entity);
    }

    @Override
    public List<HashMap> getKonkaOrderInfoDetailsListCount(KonkaOrderInfo entity) {
        return konkaOrderInfoDao.selectKonkaOrderInfoDetailsListCount(entity);
    }

    @Override
    public KonkaOrderInfo getKonkaOrderInfoNumCountMondySum(KonkaOrderInfo entity) {
        
        return konkaOrderInfoDao.selectKonkaOrderInfoNumCountMondySum(entity);
    }

    @Override
    public KonkaOrderInfo getKonkaOrderInfoNumCountMondySum1(KonkaOrderInfo entity) {
        
        return konkaOrderInfoDao.selectKonkaOrderInfoNumCountMondySum1(entity);
    }

    @Override
    public KonkaOrderInfo getKonkaOrderInfoNumCountMondySum2(KonkaOrderInfo entity) {
        
        return konkaOrderInfoDao.selectKonkaOrderInfoNumCountMondySum2(entity);
    }

    @Override
    public List<KonkaOrderInfo> getKonkaOrderInfoForIhsPaginatedList(KonkaOrderInfo t) {
        return konkaOrderInfoDao.selectKonkaOrderInfoForIhsPaginatedList(t);
    }

    @Override
    public Long getKonkaOrderInfoForIhsCount(KonkaOrderInfo t) {
        return konkaOrderInfoDao.selectKonkaOrderInfoForIhsCount(t);
    }

    @Override
    public void createDrpOrderInfo(KonkaOrderInfo ko, FromDrpOrder fo) {
        this.createKonkaOrderInfoOrder(ko);

        if (fo.getDlist() != null && fo.getDlist().size() > 0) {
            for (FromDrpOrderDetail d : fo.getDlist()) {
                this.fromDrpOrderDetailDao.insertEntity(d);
            }
        }
        this.fromDrpOrderDao.insertEntity(fo);
    }

    @Override
    public List<HashMap> getWaitForAuditOrderList(KonkaOrderInfo t) {
        return konkaOrderInfoDao.selectWaitForAuditOrderList(t);
    }
}
