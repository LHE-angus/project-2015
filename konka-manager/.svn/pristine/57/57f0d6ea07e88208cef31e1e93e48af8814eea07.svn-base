package com.ebiz.mmt.r3;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;

/**
 * 销售订单创建
 * 
 * 目前针对ZFOR,ZFGC,ZFCR的单据进行处理
 * 
 * @author ZHOU
 * 
 */
public class Call_Create_So {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public JCO.Function createFunction(String name, JCO.Repository mRepository) throws Exception {
        try {
            IFunctionTemplate ft = mRepository.getFunctionTemplate(name.toUpperCase());
            if (ft == null) return null;
            return ft.getFunction();
        } catch (Exception ex) {
            throw new Exception("Problem retrieving JCO.Function object.");
        }
    }

    // orderHeader上有行的List
    public ReturnInfo doCall(ORDER_HEADER_IN orderHeader) {
        String itemNo = null;
        ReturnInfo returnInfo = new ReturnInfo();
        // 默认先给false
        returnInfo.setSuccess(false);

        JCO.Table itemPoItem = null;
        JCO.Client mConnection = null;
        JCO.Repository mRepository = null;
        JCO.Function function = null;

        try {
            mConnection = ConnectPoolManager.getConnectionInSAPPool();
            mConnection.connect();
            mRepository = new JCO.Repository("KJQD", mConnection);
            function = this.createFunction("ZMMT_CREATE_SALE_ORDER", mRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mConnection != null && mRepository != null && function != null) {

            try {

                if (orderHeader != null && orderHeader.getOpername() != null) {
                    // 当时进行操作人
                    function.getImportParameterList().setValue(orderHeader.getOpername(), "OPERNAME");
                }
                // Structure orderHeadIn //
                JCO.Structure orderHeadIn;
                orderHeadIn = function.getImportParameterList().getStructure("ORDER_HEADER_IN");

                JCO.Structure orderHeadInX = null;
                orderHeadInX = function.getImportParameterList().getStructure("ORDER_HEADER_INX");

                // 销售凭证类型
                // ZFOR 常规 ,ZFGC 工程机
                // 其中退货ZFRE(在另一个退货接口里面做),ZFCR虽然在此接口调用,但其实内部还是调用了r3其它接口程序
                orderHeadIn.setValue(orderHeader.getDoc_type(), "DOC_TYPE");
                orderHeadInX.setValue("X", "DOC_TYPE");

                // 设置销售组织 分公司
                orderHeadIn.setValue(orderHeader.getSales_org(), "SALES_ORG");
                orderHeadInX.setValue("X", "SALES_ORG");

                // 设置分销渠道
                orderHeadIn.setValue(orderHeader.getDistr_chan(), "DISTR_CHAN");
                orderHeadInX.setValue("X", "DISTR_CHAN");

                // 设置产品组
                orderHeadIn.setValue(orderHeader.getDivision(), "DIVISION");
                orderHeadInX.setValue("X", "DIVISION");

                // 设置客户的采购订单日期
                orderHeadIn.setValue(orderHeader.getPurch_date(), "PURCH_DATE");
                orderHeadInX.setValue("X", "PURCH_DATE");

                // 设置客户的采购订单 PO 号码
                orderHeadIn.setValue(orderHeader.getPurch_no_c(), "PURCH_NO_C");
                orderHeadInX.setValue("X", "PURCH_NO_C");

                // 设置客户的销售订单编号 渠道系统的流水号
                orderHeadIn.setValue(orderHeader.getPurch_no_s(), "PURCH_NO_S");
                orderHeadInX.setValue("X", "PURCH_NO_S");

                // 交货冻结（抬头）
                // orderHeadIn.setValue("Z2","DLV_BLOCK");
                orderHeadInX.setValue("X", "DLV_BLOCK");
                // 付款条件代码
                // orderHeadIn.setValue("x","PMNTTRMS");
                orderHeadInX.setValue("X", "PMNTTRMS");
                // 定价日期和汇率
                // orderHeadIn.setValue("x","PRICE_DATE");
                orderHeadInX.setValue("X", "PRICE_DATE");
                // 装运条件
                // orderHeadIn.setValue("EF","SHIP_COND");
                // orderHeadInX.setValue("X","SHIP_COND");

                orderHeadInX.setValue("I", "UPDATEFLAG");

                /**
                 * AG 售达方 RE 出具发票方 RG 付款方 WE 送达方
                 */
                JCO.Table orderPartners = null;
                orderPartners = function.getTableParameterList().getTable("ORDER_PARTNERS");

                // 售达方
                orderPartners.appendRow();
                orderPartners.setValue("AG", "PARTN_ROLE");
                // F6 ..
                if (StringUtils.startsWith(orderHeader.getAg().toUpperCase(), "F6")) {
                    orderPartners.setValue("CN", "COUNTRY");
                    orderPartners.setValue(orderHeader.getCustomerCity(), "CITY");
                    orderPartners.setValue(orderHeader.getCustomerName(), "NAME");
                }

                orderPartners.setValue(orderHeader.getAg(), "PARTN_NUMB");

                // 出票方
                orderPartners.appendRow();
                orderPartners.setValue("RE", "PARTN_ROLE");
                // F6 ..
                if (StringUtils.startsWith(orderHeader.getAg().toUpperCase(), "F6")) {
                    orderPartners.setValue("CN", "COUNTRY");
                    orderPartners.setValue(orderHeader.getCustomerCity(), "CITY");
                    orderPartners.setValue(orderHeader.getCustomerName(), "NAME");
                }
                orderPartners.setValue(orderHeader.getRe(), "PARTN_NUMB");

                // 付款方
                orderPartners.appendRow();
                orderPartners.setValue("RG", "PARTN_ROLE");
                // F6 ..
                if (StringUtils.startsWith(orderHeader.getAg().toUpperCase(), "F6")) {
                    orderPartners.setValue("CN", "COUNTRY");
                    orderPartners.setValue(orderHeader.getCustomerCity(), "CITY");
                    orderPartners.setValue(orderHeader.getCustomerName(), "NAME");
                }
                orderPartners.setValue(orderHeader.getRg(), "PARTN_NUMB");

                // 送达方
                orderPartners.appendRow();
                orderPartners.setValue("WE", "PARTN_ROLE");
                // F6 ..
                if (StringUtils.startsWith(orderHeader.getAg().toUpperCase(), "F6")) {
                    orderPartners.setValue("CN", "COUNTRY");
                    orderPartners.setValue(orderHeader.getCustomerCity(), "CITY");
                    orderPartners.setValue(orderHeader.getCustomerName(), "NAME");
                }
                orderPartners.setValue(orderHeader.getWe(), "PARTN_NUMB");

                // ------物料行-----------
                JCO.Table orderItemsIn = null;
                JCO.Table orderItemsInX = null;
                orderItemsIn = function.getTableParameterList().getTable("ORDER_ITEMS_IN");
                orderItemsInX = function.getTableParameterList().getTable("ORDER_ITEMS_INX");
                // ------价格条件-------
                JCO.Table orderConditionsIn = null;
                JCO.Table orderConditionsInX = null;
                orderConditionsIn = function.getTableParameterList().getTable("ORDER_CONDITIONS_IN");
                orderConditionsInX = function.getTableParameterList().getTable("ORDER_CONDITIONS_INX");
                // ------执行计划-------
                JCO.Table orderSchedulesIn = null;
                JCO.Table orderSchedulesInX = null;
                orderSchedulesIn = function.getTableParameterList().getTable("ORDER_SCHEDULES_IN");
                orderSchedulesInX = function.getTableParameterList().getTable("ORDER_SCHEDULES_INX");
                // 根据订单行列表遍历
                for (ORDER_ITEMS_IN line : orderHeader.getItemList()) {
                    if (line.getMATERIAL() != null && !("KF-22PB".equals(line.getMATERIAL()) || "KF-22KK".equals(line.getMATERIAL()) || "KF-22XD".equals(line.getMATERIAL()))) {
                        // table orderItemsIn //
                        orderItemsIn.appendRow();
                        // 物料行项目号
                        orderItemsIn.setValue(line.getITEMNO(), "ITM_NUMBER");
                        // 物料号
                        orderItemsIn.setValue(line.getMATERIAL(), "MATERIAL");
                        // 销售工厂,不是物流工厂!但目前系统传的是物流工厂(在订单审核过程中某角色的人来指定)
                        // orderItemsIn.setValue(line.getPLANT(), "PLANT");
                        // 库位(在订单审核过程中某角色的人来指定)
                        // orderItemsIn.setValue(line.getSTORE_LOC(), "STORE_LOC");
                        // 装运点一般和工厂相同
                        // orderItemsIn.setValue(line.getPLANT(), "SHIP_POINT");//
                        // 装运点
                        // 每一行订单行机型的数量
                        orderItemsIn.setValue(line.getTARGET_QTY(), "TARGET_QTY");
                        // 单位
                        orderItemsIn.setValue("SET", "TARGET_QU");
                        // 销售凭证项目类别

                        // ZFRE在另一个接口处理Call_Create_So_T
                        // ZFOR 常规 ,ZFGC 工程机 ,退货 ZFRE,贷项ZFCR
                        // if ("ZFOR".equals(orderHeader.getDoc_type()) ||
                        // "ZFGC".equals(orderHeader.getDoc_type())) {
                        // orderItemsIn.setValue("ZFTN", "ITEM_CATEG"); // 正向销售
                        // } else if("ZFRE".equals(orderHeader.getDoc_type())){
                        // orderItemsIn.setValue("ZREN", "ITEM_CATEG"); // 退货
                        // }else if("ZFCR".equals(orderHeader.getDoc_type())){
                        // orderItemsIn.setValue("ZGN", "ITEM_CATEG"); // 贷项
                        // }
                        // 销售数量转换成SKU的分子(因子)
                        orderItemsIn.setValue("1", "SALQTYNUM");
                        // 销售数量转换为 SKU 的值（除数）
                        orderItemsIn.setValue("1", "SALQTYDEN");
                        // 销售单位
                        orderItemsIn.setValue("SET", "SALES_UNIT");
                        // 客户物料号 //机型行上面的产品备注
                        orderItemsIn.setValue(line.getCUST_MAT35(), "CUST_MAT35");

                        orderItemsInX.appendRow();
                        orderItemsInX.setValue(line.getITEMNO(), "ITM_NUMBER");
                        orderItemsInX.setValue("X", "MATERIAL");
                        orderItemsInX.setValue("X", "PLANT");
                        orderItemsInX.setValue("X", "STORE_LOC");
                        orderItemsInX.setValue("X", "SHIP_POINT");
                        orderItemsInX.setValue("X", "TARGET_QTY");
                        orderItemsInX.setValue("X", "TARGET_QU");
                        orderItemsInX.setValue("X", "ITEM_CATEG");
                        orderItemsInX.setValue("X", "SALQTYNUM");
                        orderItemsInX.setValue("X", "SALQTYDEN");
                        orderItemsInX.setValue("X", "SALES_UNIT");
                        orderItemsInX.setValue("X", "CUST_MAT35");

                        // table ConditionsIn //
                        // 条件行项目号 10,20,30,40 ...
                        /*** PR01 **/
                        orderConditionsIn.appendRow();
                        // 行项目号
                        orderConditionsIn.setValue(line.getITEMNO(), "ITM_NUMBER");
                        // type
                        orderConditionsIn.setValue("PR01", "COND_TYPE");
                        // 条件步骤编号
                        orderConditionsIn.setValue("010", "COND_ST_NO");
                        // 条件计数器
                        orderConditionsIn.setValue("01", "COND_COUNT");
                        // 金额 每一个机型的含税单价
                        orderConditionsIn.setValue(line.getCOND_VALUE(), "COND_VALUE");
                        // 货币码
                        orderConditionsIn.setValue("RMB", "CURRENCY");
                        // 条件定价单位 ,一台开始订价
                        orderConditionsIn.setValue("1", "COND_P_UNT");
                        // 条件单位
                        orderConditionsIn.setValue("SET", "COND_UNIT");

                        orderConditionsInX.appendRow();
                        orderConditionsInX.setValue(line.getITEMNO(), "ITM_NUMBER");
                        orderConditionsInX.setValue("PR01", "COND_TYPE");
                        orderConditionsInX.setValue("010", "COND_ST_NO");
                        orderConditionsInX.setValue("01", "COND_COUNT");
                        orderConditionsInX.setValue("U", "UPDATEFLAG");// U
                        orderConditionsInX.setValue("X", "COND_VALUE");
                        orderConditionsInX.setValue("X", "CURRENCY");
                        orderConditionsInX.setValue("X", "COND_UNIT");
                        orderConditionsInX.setValue("X", "COND_P_UNT");

                        /*** K007 **/
                        orderConditionsIn.appendRow();
                        // 行项目号
                        orderConditionsIn.setValue(line.getITEMNO(), "ITM_NUMBER");
                        // type
                        orderConditionsIn.setValue("K007", "COND_TYPE");
                        // 条件步骤编号
                        orderConditionsIn.setValue("113", "COND_ST_NO");
                        // 条件计数器
                        orderConditionsIn.setValue("01", "COND_COUNT");
                        // 应该是每一行订单,各机型的折让率
                        orderConditionsIn.setValue(line.getK007_COND_VALUE() * 10, "COND_VALUE");
                        // 货币码
                        orderConditionsIn.setValue("RMB", "CURRENCY");
                        orderConditionsIn.setValue("X", "COND_UPDAT");

                        orderConditionsInX.appendRow();
                        orderConditionsInX.setValue(line.getITEMNO(), "ITM_NUMBER");
                        orderConditionsInX.setValue("K007", "COND_TYPE");
                        orderConditionsInX.setValue("113", "COND_ST_NO");
                        orderConditionsInX.setValue("01", "COND_COUNT");
                        orderConditionsInX.setValue("U", "UPDATEFLAG");// U
                        orderConditionsInX.setValue("X", "COND_VALUE");
                        orderConditionsInX.setValue("X", "CURRENCY");
                        orderConditionsInX.setValue("X", "COND_UNIT");
                        orderConditionsInX.setValue("X", "COND_P_UNT");

                        /*** RB00 **/
                        orderConditionsIn.appendRow();
                        // 行项目号
                        orderConditionsIn.setValue(line.getITEMNO(), "ITM_NUMBER");
                        // type
                        orderConditionsIn.setValue("RB00", "COND_TYPE");
                        // 条件步骤编号
                        orderConditionsIn.setValue("210", "COND_ST_NO");
                        // 条件计数器
                        orderConditionsIn.setValue("01", "COND_COUNT");
                        // 这里是折扣值
                        orderConditionsIn.setValue(line.getRB00_COND_VALUE(), "COND_VALUE");
                        // 货币码
                        orderConditionsIn.setValue("RMB", "CURRENCY");
                        orderConditionsIn.setValue("X", "COND_UPDAT");

                        /*** RB00 **/
                        orderConditionsInX.appendRow();
                        orderConditionsInX.setValue(line.getITEMNO(), "ITM_NUMBER");
                        orderConditionsInX.setValue("RB00", "COND_TYPE");
                        orderConditionsInX.setValue("210", "COND_ST_NO");
                        orderConditionsInX.setValue("01", "COND_COUNT");
                        orderConditionsInX.setValue("U", "UPDATEFLAG");// U
                        orderConditionsInX.setValue("X", "COND_VALUE");
                        orderConditionsInX.setValue("X", "CURRENCY");
                        orderConditionsInX.setValue("X", "COND_UNIT");
                        orderConditionsInX.setValue("X", "COND_P_UNT");

                        // table SchedulesIn //
                        orderSchedulesIn.appendRow();
                        // 计划行项目号
                        orderSchedulesIn.setValue(line.getITEMNO(), "ITM_NUMBER");
                        // 计划的号 以 1 递增 1,2,3
                        // orderSchedulesIn.setValue(Long.valueOf(line.getITEMNO() /
                        // 10), "SCHED_LINE");
                        // 计划行日期
                        orderSchedulesIn.setValue(new Date(), "REQ_DATE");
                        // 日期类型 日 1 周 2 月 3
                        // orderSchedulesIn.setValue("1","DATE_TYPE");
                        // 以 销售单位为单位的数量
                        orderSchedulesIn.setValue(line.getTARGET_QTY(), "REQ_QTY");

                        orderSchedulesInX.appendRow();
                        orderSchedulesInX.setValue(line.getITEMNO(), "ITM_NUMBER");
                        // orderSchedulesInX.setValue(Long.valueOf(line.getITEMNO()
                        // / 10), "SCHED_LINE");
                        orderSchedulesInX.setValue("X", "REQ_QTY");
                        orderSchedulesInX.setValue("U", "UPDATEFLAG");

                    } else if ("KF-22PB".equals(line.getMATERIAL()) || "KF-22XD".equals(line.getMATERIAL()) || "KF-22KK".equals(line.getMATERIAL())) {
                        // table orderItemsIn //
                        orderItemsIn.appendRow();
                        // 物料行项目号
                        orderItemsIn.setValue(line.getITEMNO(), "ITM_NUMBER");
                        // 物料号
                        orderItemsIn.setValue(line.getMATERIAL(), "MATERIAL");
                        // 销售工厂,不是物流工厂!但目前系统传的是物流工厂(在订单审核过程中某角色的人来指定)
                        // orderItemsIn.setValue(line.getPLANT(), "PLANT");
                        // 库位(在订单审核过程中某角色的人来指定)
                        // orderItemsIn.setValue(line.getSTORE_LOC(), "STORE_LOC");
                        // 装运点一般和工厂相同
                        // orderItemsIn.setValue(line.getPLANT(), "SHIP_POINT");//
                        // 装运点
                        // 每一行订单行机型的数量
                        orderItemsIn.setValue(line.getTARGET_QTY(), "TARGET_QTY");
                        // 单位
                        orderItemsIn.setValue("SET", "TARGET_QU");
                        // 销售凭证项目类别 这个比较特殊

                        // ZFRE在另一个接口处理Call_Create_So_T
                        // ZFOR 常规 ,ZFGC 工程机 ,退货 ZFRE,贷项ZFCR
                        // if ("ZFOR".equals(orderHeader.getDoc_type()) ||
                        // "ZFGC".equals(orderHeader.getDoc_type())) {
                        // orderItemsIn.setValue("ZJS", "ITEM_CATEG"); // 正向销售
                        // } else if("ZFRE".equals(orderHeader.getDoc_type())){
                        // orderItemsIn.setValue("ZJS", "ITEM_CATEG"); // 退货
                        // }else if("ZFCR".equals(orderHeader.getDoc_type())){
                        // orderItemsIn.setValue("ZGN", "ITEM_CATEG"); // 贷项
                        // }

                        // 销售数量转换成SKU的分子(因子)
                        orderItemsIn.setValue("1", "SALQTYNUM");
                        // 销售数量转换为 SKU 的值（除数）
                        orderItemsIn.setValue("1", "SALQTYDEN");
                        // 销售单位
                        orderItemsIn.setValue("SET", "SALES_UNIT");
                        // 客户物料号
                        orderItemsIn.setValue(line.getCUST_MAT35(), "CUST_MAT35");

                        orderItemsInX.appendRow();
                        orderItemsInX.setValue(line.getITEMNO(), "ITM_NUMBER");
                        orderItemsInX.setValue("X", "MATERIAL");
                        orderItemsInX.setValue("X", "PLANT");
                        orderItemsInX.setValue("X", "STORE_LOC");
                        orderItemsInX.setValue("X", "SHIP_POINT");
                        orderItemsInX.setValue("X", "TARGET_QTY");
                        orderItemsInX.setValue("X", "TARGET_QU");
                        orderItemsInX.setValue("X", "ITEM_CATEG");
                        orderItemsInX.setValue("X", "SALQTYNUM");
                        orderItemsInX.setValue("X", "SALQTYDEN");
                        orderItemsInX.setValue("X", "SALES_UNIT");
                        orderItemsInX.setValue("X", "CUST_MAT35");

                        // table ConditionsIn //
                        // 条件行项目号 10,20,30,40 ...
                        /*** PR01 **/
                        orderConditionsIn.appendRow();
                        // 行项目号
                        orderConditionsIn.setValue(line.getITEMNO(), "ITM_NUMBER");
                        // type
                        orderConditionsIn.setValue("PR01", "COND_TYPE");
                        // 条件步骤编号
                        orderConditionsIn.setValue("010", "COND_ST_NO");
                        // 条件计数器
                        orderConditionsIn.setValue("01", "COND_COUNT");
                        /**
                         * 这个特殊的机型可以录多行. 如果只录一行,KF-22PB ,-3000 ,1 表示对张单优惠3000. 同时要把K007的优惠抹掉
                         **/
                        orderConditionsIn.setValue(line.getCOND_VALUE(), "COND_VALUE");
                        // 货币码
                        orderConditionsIn.setValue("RMB", "CURRENCY");
                        // 条件定价单位 ,一台开始订价
                        orderConditionsIn.setValue("1", "COND_P_UNT");
                        // 条件单位
                        orderConditionsIn.setValue("SET", "COND_UNIT");

                        /*** K007 **/
                        orderConditionsIn.appendRow();
                        // 行项目号
                        orderConditionsIn.setValue(line.getITEMNO(), "ITM_NUMBER");
                        // type
                        orderConditionsIn.setValue("K007", "COND_TYPE");
                        // 条件步骤编号
                        orderConditionsIn.setValue("113", "COND_ST_NO");
                        // 条件计数器
                        orderConditionsIn.setValue("01", "COND_COUNT");
                        // KF-22PB 的录入使得这里要设为0
                        orderConditionsIn.setValue(0, "COND_VALUE");
                        // 货币码
                        orderConditionsIn.setValue("RMB", "CURRENCY");
                        orderConditionsIn.setValue("X", "COND_UPDAT");

                        /*** RB00 **/
                        /**
                         * 原则上, 录入了KF-22PB后,不能录入K007 和RB00 ,所以此处RB00不录 .上面有K007
                         * 是因为需要抹掉R3对客户设定的一个优惠折扣率
                         **/
                        // orderConditionsIn.appendRow();
                        // // 行项目号
                        // orderConditionsIn.setValue(line.getITEMNO(),
                        // "ITM_NUMBER");
                        // // type
                        // orderConditionsIn.setValue("RB00", "COND_TYPE");
                        // // 条件步骤编号
                        // orderConditionsIn.setValue("210", "COND_ST_NO");
                        // // 条件计数器
                        // orderConditionsIn.setValue("01", "COND_COUNT");
                        // // 这里是折扣值
                        // orderConditionsIn.setValue(0, "COND_VALUE");
                        // // 货币码
                        // orderConditionsIn.setValue("RMB", "CURRENCY");
                        // orderConditionsIn.setValue("X", "COND_UPDAT");

                        orderConditionsInX.appendRow();
                        orderConditionsInX.setValue(line.getITEMNO(), "ITM_NUMBER");
                        orderConditionsInX.setValue("PR01", "COND_TYPE");
                        orderConditionsInX.setValue("010", "COND_ST_NO");
                        orderConditionsInX.setValue("01", "COND_COUNT");
                        orderConditionsInX.setValue("U", "UPDATEFLAG");// U
                        orderConditionsInX.setValue("X", "COND_VALUE");
                        orderConditionsInX.setValue("X", "CURRENCY");
                        orderConditionsInX.setValue("X", "COND_UNIT");
                        orderConditionsInX.setValue("X", "COND_P_UNT");

                        orderConditionsInX.appendRow();
                        orderConditionsInX.setValue(line.getITEMNO(), "ITM_NUMBER");
                        orderConditionsInX.setValue("K007", "COND_TYPE");
                        orderConditionsInX.setValue("113", "COND_ST_NO");
                        orderConditionsInX.setValue("01", "COND_COUNT");
                        orderConditionsInX.setValue("U", "UPDATEFLAG");// U
                        orderConditionsInX.setValue("X", "COND_VALUE");
                        orderConditionsInX.setValue("X", "CURRENCY");
                        orderConditionsInX.setValue("X", "COND_UNIT");
                        orderConditionsInX.setValue("X", "COND_P_UNT");

                        // orderConditionsInX.appendRow();
                        // orderConditionsInX.setValue(line.getITEMNO(),
                        // "ITM_NUMBER");
                        // orderConditionsInX.setValue("RB00", "COND_TYPE");
                        // orderConditionsInX.setValue("210", "COND_ST_NO");
                        // orderConditionsInX.setValue("01", "COND_COUNT");
                        // orderConditionsInX.setValue("U", "UPDATEFLAG");// U
                        // orderConditionsInX.setValue("X", "COND_VALUE");
                        // orderConditionsInX.setValue("X", "CURRENCY");
                        // orderConditionsInX.setValue("X", "COND_UNIT");
                        // orderConditionsInX.setValue("X", "COND_P_UNT");

                        // table SchedulesIn //
                        orderSchedulesIn.appendRow();
                        // 计划行项目号
                        orderSchedulesIn.setValue(line.getITEMNO(), "ITM_NUMBER");
                        // 计划的号 以 1 递增 1,2,3
                        // orderSchedulesIn.setValue(Long.valueOf(line.getITEMNO() /
                        // 10), "SCHED_LINE");
                        // 计划行日期
                        orderSchedulesIn.setValue(new Date(), "REQ_DATE");
                        // 日期类型 日 1 周 2 月 3
                        // orderSchedulesIn.setValue("1","DATE_TYPE");
                        // 以 销售单位为单位的数量
                        orderSchedulesIn.setValue(line.getTARGET_QTY(), "REQ_QTY");

                        orderSchedulesInX.appendRow();
                        orderSchedulesInX.setValue(line.getITEMNO(), "ITM_NUMBER");
                        // orderSchedulesInX.setValue(Long.valueOf(line.getITEMNO()
                        // / 10), "SCHED_LINE");
                        orderSchedulesInX.setValue("X", "REQ_QTY");
                        orderSchedulesInX.setValue("X", "UPDATEFLAG");

                    }

                }

                // 调用执行
                mConnection.execute(function);

                // function.writeHTML("d:/so_create.html");
                // 输出返回信息

                if (function.getExportParameterList().getValue("SALESDOCUMENT") != null && function.getExportParameterList().getValue("SALESDOCUMENT").toString().length() >= 0) {
                    // 生成的R/3订单号
                    itemNo = (String) function.getExportParameterList().getValue("SALESDOCUMENT") + "";
                }
                // 返回的提示信息
                itemPoItem = function.getTableParameterList().getTable("RETURN");
                List<ExcuteMsg> msgList = new ArrayList<ExcuteMsg>();
                for (int k = 0; k < itemPoItem.getNumRows(); k++) {
                    itemPoItem.setRow(k);
                    ExcuteMsg excutemsg = new ExcuteMsg();
                    excutemsg.setType(itemPoItem.getString("TYPE").toUpperCase());
                    excutemsg.setMessage(itemPoItem.getString("MESSAGE"));
                    msgList.add(excutemsg);
                }

                Boolean successFlag = true;
                // 如果type有E,说明有错误返回
                for (ExcuteMsg msg : msgList) {
                    if (msg.getType() != null) {
                        if ("E".equals(msg.getType())) {
                            successFlag = false;
                            break;
                        }
                    }
                }
                returnInfo.setSuccess(successFlag);
                // 封装返回信息
                if (itemNo != null && !"".equals(itemNo)) {
                    returnInfo.setItemNO(itemNo);
                    returnInfo.setSuccess(true);
                }
                returnInfo.setMsgList(msgList);

            } catch (Exception ex) {
                logger.info(ex.getMessage());
            } finally {
                mRepository = null;
                if (mConnection != null) {
                    ConnectPoolManager.releaseClient(mConnection);
                }
            }
        }

        return returnInfo;
    }

    // public static void main(String args[]) {
    //
    // ORDER_HEADER_IN header = new ORDER_HEADER_IN();
    //
    // header.setSales_org("F475");
    // header.setDoc_type("ZFOR");//
    // header.setDistr_chan("10");
    // header.setDivision("10");
    // header.setAg("F6475GCZH");
    // header.setRe("F6475GCZH");
    // header.setRg("F6475GCZH");
    // header.setWe("F6475GCZH");
    // header.setPurch_date(new Date());
    // header.setPurch_no_s("21345653646564");// 流水号没有校验重复性
    // header.setPurch_no_c("123456");// 手工填的
    // header.setOpername("zhj");
    //
    // header.setCustomerCity("中山");
    // header.setCustomerName("F6475GCZH散户");
    // header.setCustomerTxt("");
    //
    // List<ORDER_ITEMS_IN> itemlist = new ArrayList<ORDER_ITEMS_IN>();
    // ORDER_ITEMS_IN line4 = new ORDER_ITEMS_IN();
    // line4.setITEMNO(30l);
    // line4.setMATERIAL("LED42M1200AF");// 物料
    // line4.setSALES_UNIT("SET");
    // line4.setTARGET_QTY(1);// 第一行订单行的机型的要货数量
    // line4.setCOND_VALUE(100);// 第一行订单行的机型的单价含税金额
    // line4.setK007_COND_VALUE(0);
    // line4.setRB00_COND_VALUE(0);
    // line4.setTARGET_QU("SET");
    // line4.setSALQTYDEN(1);// sku分子
    // line4.setSALQTYNUM(1);// /sku值
    // line4.setCUST_MAT35("dkddkdk");
    // itemlist.add(line4);
    //
    // ORDER_ITEMS_IN line3 = new ORDER_ITEMS_IN();
    // line3.setITEMNO(10l);
    // line3.setMATERIAL("LED47X8100PDE");// 物料
    // line3.setSALES_UNIT("SET");
    // line3.setTARGET_QTY(1);// 第一行订单行的机型的要货数量
    // line3.setCOND_VALUE(100);// 第一行订单行的机型的单价含税金额
    // line3.setK007_COND_VALUE(0);
    // line3.setRB00_COND_VALUE(0);
    // line3.setTARGET_QU("SET");
    // line3.setSALQTYDEN(1);// sku分子
    // line3.setSALQTYNUM(1);// /sku值
    // line3.setCUST_MAT35("xxyyy");
    // itemlist.add(line3);
    // //
    // ORDER_ITEMS_IN line2 = new ORDER_ITEMS_IN();
    // line2.setITEMNO(20L);
    // line2.setMATERIAL("KF-22PB");// 物料
    // line2.setSALES_UNIT("SET");
    // line2.setTARGET_QTY(1);// 第一行订单行的机型的要货数量
    // line2.setCOND_VALUE(-18);// 第一行订单行的机型的单价含税金额
    // line2.setK007_COND_VALUE(0);
    // line2.setRB00_COND_VALUE(0);
    // line2.setTARGET_QU("SET");
    // line2.setSALQTYDEN(1);// sku分子
    // line2.setSALQTYNUM(1);// /sku值
    // line2.setCUST_MAT35("机型描述测试");
    // itemlist.add(line2);
    //
    // header.setItemList(itemlist);
    //
    // Call_Create_So c = new Call_Create_So();
    // ReturnInfo returnInfo = c.doCall(header);
    //
    // for (ExcuteMsg em : (List<ExcuteMsg>) returnInfo.getMsgList()) {
    // //System.out.println(em.getType() + ":" + em.getMessage());
    // }
    // //System.out.println(returnInfo.getSuccess());
    // //System.out.println(returnInfo.getItemNO());
    //
    // }

}
