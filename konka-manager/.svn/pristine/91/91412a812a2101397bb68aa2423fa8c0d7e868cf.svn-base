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
 * 销售订单退货处理
 * 
 * 目前针对ZFRE的单据进行处理
 * 
 * @author ZHOU
 * 
 */
public class Call_Create_So_T {

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

    public ReturnInfo doCall(ORDER_HEADER_IN orderHeader) {

        String itemNo = null;
        ReturnInfo returnInfo = new ReturnInfo();
        returnInfo.setSuccess(false);

        JCO.Table itemPoItem = null;
        JCO.Client mConnection = null;
        JCO.Repository mRepository = null;
        JCO.Function function = null;

        try {
            mConnection = ConnectPoolManager.getConnectionInSAPPool();
            mConnection.connect();
            mRepository = new JCO.Repository("KJQD", mConnection);
            function = this.createFunction("ZMMT_CREATE_CUSTOMERRETURN", mRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mConnection != null && mRepository != null && function != null) {
            try {
                // 当时进行操作人 目前无效
                function.getImportParameterList().setValue(orderHeader.getOpername(), "OPERNAME");

                JCO.Structure orderHeadIn = null;
                orderHeadIn = function.getImportParameterList().getStructure("RETURN_HEADER_IN");

                JCO.Structure orderHeadInX = null;
                orderHeadInX = function.getImportParameterList().getStructure("RETURN_HEADER_INX");

                // 销售凭证类型
                // 此接口目前只处理退货 ZFRE
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
                // orderHeadInX.setValue("X", "SHIP_COND");

                orderHeadInX.setValue("I", "UPDATEFLAG");

                // 退货原因 R3 必须
                // 001 销售会谈
                // 002 贸易展览会销售活动
                // 003 电视商业
                // 004 客户建议
                // 005 报纸广告
                // 006 极好的价格
                // 007 快速交货
                // 008 优良服务
                // 100 价格差异：价格太高
                // 101 质量低劣
                // 102 转运中受损
                // 103 数量不符
                // 104 物料损坏
                // 105 免费样本
                // 200 价格差异：价格太低
                // EDI 贷项凭单处理
                // F01 客户退货
                // F02 客户补差

                // 如果退货原因为空,默认给001
                if (orderHeader.getOrdreason() == null || "".equals(orderHeader.getOrdreason())) {
                    orderHeadIn.setValue("001", "ORD_REASON");
                } else {
                    orderHeadIn.setValue(orderHeader.getOrdreason(), "ORD_REASON");
                }

                orderHeadInX.setValue("X", "ORD_REASON");

                /**
                 * AG 售达方 RE 出具发票方 RG 付款方 WE 送达方
                 */
                JCO.Table orderPartners = null;
                orderPartners = function.getTableParameterList().getTable("RETURN_PARTNERS");

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

                // 行表信息 loop
                // ------物料行-----------
                JCO.Table orderItemsIn = null;
                JCO.Table orderItemsInX = null;
                orderItemsIn = function.getTableParameterList().getTable("RETURN_ITEMS_IN");
                orderItemsInX = function.getTableParameterList().getTable("RETURN_ITEMS_INX");
                // ------价格条件-------
                JCO.Table orderConditionsIn = null;
                JCO.Table orderConditionsInX = null;
                orderConditionsIn = function.getTableParameterList().getTable("RETURN_CONDITIONS_IN");
                orderConditionsInX = function.getTableParameterList().getTable("RETURN_CONDITIONS_INX");
                // ------执行计划-------
                JCO.Table orderSchedulesIn = null;
                JCO.Table orderSchedulesInX = null;
                orderSchedulesIn = function.getTableParameterList().getTable("RETURN_SCHEDULES_IN");
                orderSchedulesInX = function.getTableParameterList().getTable("RETURN_SCHEDULES_INX");

                for (ORDER_ITEMS_IN line : orderHeader.getItemList()) {
                    if (line.getMATERIAL() != null) {
                        // table orderItemsIn //
                        orderItemsIn.appendRow();
                        // 物料行项目号
                        orderItemsIn.setValue(line.getITEMNO(), "ITM_NUMBER");
                        // 物料号
                        orderItemsIn.setValue(line.getMATERIAL(), "MATERIAL");

                        // 销售工厂,不是物流工厂!但目前系统传的是物流工厂(在订单审核过程中某角色的人来指定)
                        // orderItemsIn.setValue("KF47", "PLANT");
                        // 库位(在订单审核过程中某角色的人来指定)
                        // orderItemsIn.setValue("0001", "STORE_LOC");
                        // 装运点一般和工厂相同
                        // orderItemsIn.setValue("KF47", "SHIP_POINT");

                        // 每一行订单行机型的数量
                        orderItemsIn.setValue(line.getTARGET_QTY(), "TARGET_QTY");
                        // 单位
                        orderItemsIn.setValue("SET", "TARGET_QU");

                        // 退货类型,经测试,可以不指定此参数
                        // orderItemsIn.setValue("ZREN", "ITEM_CATEG"); // 退货

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
                        // 条件步骤编号 有特殊意义
                        orderConditionsIn.setValue("010", "COND_ST_NO");
                        // 条件计数器
                        orderConditionsIn.setValue("01", "COND_COUNT");
                        // 金额 每一个机型的含税单价
                        orderConditionsIn.setValue(line.getCOND_VALUE(), "COND_VALUE");
                        // 货币码
                        orderConditionsIn.setValue("RMB", "CURRENCY");
                        // 条件定价单位 ,一台开始订价
                        orderConditionsIn.setValue("1", "COND_P_UNT");
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
                        // 应该是每一行订单,各机型的折让率
                        orderConditionsIn.setValue(line.getK007_COND_VALUE() * 10, "COND_VALUE");
                        // 货币码
                        orderConditionsIn.setValue("RMB", "CURRENCY");
                        orderConditionsIn.setValue("X", "COND_UPDAT");

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


                        orderConditionsInX.appendRow();
                        orderConditionsInX.setValue(line.getITEMNO(), "ITM_NUMBER");
                        orderConditionsInX.setValue("PR01", "COND_TYPE");
                        orderConditionsInX.setValue("010", "COND_ST_NO");
                        orderConditionsInX.setValue("01", "COND_COUNT");
                        orderConditionsInX.setValue("X", "COND_VALUE");
                        orderConditionsInX.setValue("X", "CURRENCY");
                        orderConditionsInX.setValue("X", "COND_UNIT");
                        orderConditionsInX.setValue("X", "COND_P_UNT");
                        orderConditionsInX.setValue("U", "UPDATEFLAG");// U

                        orderConditionsInX.appendRow();
                        orderConditionsInX.setValue(line.getITEMNO(), "ITM_NUMBER");
                        orderConditionsInX.setValue("K007", "COND_TYPE");
                        orderConditionsInX.setValue("113", "COND_ST_NO");
                        orderConditionsInX.setValue("01", "COND_COUNT");
                        orderConditionsInX.setValue("X", "COND_VALUE");
                        orderConditionsInX.setValue("X", "CURRENCY");
                        orderConditionsInX.setValue("X", "COND_UNIT");
                        orderConditionsInX.setValue("X", "COND_P_UNT");
                        orderConditionsInX.setValue("U", "UPDATEFLAG");// U 代表更新sap原有的已经维护的值?

                        orderConditionsInX.appendRow();
                        orderConditionsInX.setValue(line.getITEMNO(), "ITM_NUMBER");
                        orderConditionsInX.setValue("RB00", "COND_TYPE");
                        orderConditionsInX.setValue("210", "COND_ST_NO");
                        orderConditionsInX.setValue("01", "COND_COUNT");
                        orderConditionsInX.setValue("X", "COND_VALUE");
                        orderConditionsInX.setValue("X", "CURRENCY");
                        orderConditionsInX.setValue("X", "COND_UNIT");
                        orderConditionsInX.setValue("X", "COND_P_UNT");
                        orderConditionsInX.setValue("U", "UPDATEFLAG");// U



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
                        orderSchedulesIn.setValue("1", "DATE_TYPE");
                        // 以 销售单位为单位的数量
                        orderSchedulesIn.setValue(line.getTARGET_QTY(), "REQ_QTY");

                        orderSchedulesInX.appendRow();
                        orderSchedulesInX.setValue(line.getITEMNO(), "ITM_NUMBER");
                        // orderSchedulesInX.setValue(Long.valueOf(line.getITEMNO()
                        // / 10), "SCHED_LINE");
                        orderSchedulesInX.setValue("X", "REQ_QTY");
                        orderSchedulesInX.setValue("X", "UPDATEFLAG");

                    } else {
                        // do other
                    }
                }

                // 是否提交数据库
                function.getImportParameterList().setValue("X", "COMMIT");
                // 调用执行
                mConnection.execute(function);

                // function.writeHTML("d:/Call_Create_So_T.html");
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
                    excutemsg.setType(itemPoItem.getString("TYPE"));
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
                // 封装返回信息
                returnInfo.setMsgList(msgList);

            } catch (Exception ex) {
                logger.info(ex.getMessage());
                //
            } finally {
                mRepository = null;
                
                if (mConnection != null) {
                    ConnectPoolManager.releaseClient(mConnection);
                }
            }
        }
        return returnInfo;
    }

    /**
     * 创建退货销售订单
     * 
     * @param args
     */
    public static void main(String args[]) {
        ORDER_HEADER_IN header = new ORDER_HEADER_IN();
        header.setSales_org("KF24");
        header.setDoc_type("ZFRE");//
        header.setDistr_chan("10");
        header.setDivision("10");
        header.setAg("F1GM24");// 一次性客户,散户
        header.setRe("F1GM24");
        header.setRg("F1GM24");
        header.setWe("F1GM24");
        header.setPurch_date(new Date());
        header.setPurch_no_s("213456536466000");// 流水号没有校验重复性
        header.setPurch_no_c("123456");// 手工填的
        header.setOpername("zhj");

        header.setCustomerCity("城市");
        header.setCustomerName("F1GM24户");
        header.setCustomerTxt("");

        List<ORDER_ITEMS_IN> itemlist = new ArrayList<ORDER_ITEMS_IN>();
        ORDER_ITEMS_IN line4 = new ORDER_ITEMS_IN();
        line4.setITEMNO(10l);
        line4.setMATERIAL("LED42M1200AF");// 物料
        line4.setSALES_UNIT("SET");
        line4.setTARGET_QTY(1);// 第一行订单行的机型的要货数量
        line4.setCOND_VALUE(1200);// 第一行订单行的机型的单价含税金额
        line4.setK007_COND_VALUE(10);// 正负都OK
        line4.setRB00_COND_VALUE(0);// 正负都OK
        line4.setTARGET_QU("SET");
        line4.setSALQTYDEN(1);// sku分子
        line4.setSALQTYNUM(1);// /sku值
        line4.setCUST_MAT35("LED42M1200AF");
        itemlist.add(line4);

        ORDER_ITEMS_IN line3 = new ORDER_ITEMS_IN();
        line3.setITEMNO(20l);
        line3.setMATERIAL("LED47X8100PDE");// 物料
        line3.setSALES_UNIT("SET");
        line3.setTARGET_QTY(1);// 第一行订单行的机型的要货数量
        line3.setCOND_VALUE(1500);// 第一行订单行的机型的单价含税金额
        line3.setK007_COND_VALUE(1);// 正负都OK
        line3.setRB00_COND_VALUE(-200);// 正负都OK
        line3.setTARGET_QU("SET");
        line3.setSALQTYDEN(1);// sku分子
        line3.setSALQTYNUM(1);// /sku值
        line3.setCUST_MAT35("LED47X8100PDE");
        itemlist.add(line3);

        header.setItemList(itemlist);

        Call_Create_So_T c = new Call_Create_So_T();
        ReturnInfo returnInfo = c.doCall(header);

        for (ExcuteMsg em : (List<ExcuteMsg>) returnInfo.getMsgList()) {
            // System.out.println(em.getType() + ":" + em.getMessage());
        }
        // System.out.println(returnInfo.getSuccess());
        System.out.println(returnInfo.getItemNO());

    }

}
