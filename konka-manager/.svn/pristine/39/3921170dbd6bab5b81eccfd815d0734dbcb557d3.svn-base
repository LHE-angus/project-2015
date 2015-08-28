package com.ebiz.mmt.r3;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;

public class Call_Update_So {
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
    public ReturnInfo doCall(ORDER_HEADER_IN orderHeader, String orderNumber) {
        ReturnInfo returnInfo = new ReturnInfo();

        JCO.Table ret = null;
        JCO.Client mConnection = null;
        JCO.Repository mRepository = null;
        JCO.Function function = null;
        try {
            mConnection = ConnectPoolManager.getConnectionInSAPPool();
            mConnection.connect();
            mRepository = new JCO.Repository("KJQD", mConnection);
            function = this.createFunction("ZMMT_UPDATE_SALE_ORDER", mRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mConnection != null && mRepository != null && function != null) {

            try {

                // 是销售定单单号
                function.getImportParameterList().setValue(orderNumber, "SALESDOCUMENT");

                // 当时进行操作人
                function.getImportParameterList().setValue(orderHeader.getOpername(), "OPERNAME");

                // Structure orderHeadIn //
                JCO.Structure orderHeadIn = null;
                JCO.Structure orderHeadInX = null;

                orderHeadIn = function.getImportParameterList().getStructure("ORDER_HEADER_IN");
                orderHeadInX = function.getImportParameterList().getStructure("ORDER_HEADER_INX");

                // 销售凭证类型在修改订单时不能修改,不需要传
                // orderHeadIn.setValue(orderHeader.getDoc_type(), "DOC_TYPE");
                // orderHeadInX.setValue("X", "DOC_TYPE");

                // 设置销售组织 分公司
                // orderHeadIn.setValue(orderHeader.getSales_org(), "SALES_ORG");
                // orderHeadInX.setValue("X", "SALES_ORG");

                // 设置分销渠道
                // orderHeadIn.setValue(orderHeader.getDistr_chan(), "DISTR_CHAN");
                // orderHeadInX.setValue("X", "DISTR_CHAN");

                // 设置产品组
                // orderHeadIn.setValue(orderHeader.getDivision(), "DIVISION");
                // orderHeadInX.setValue("X", "DIVISION");

                // 设置客户的采购订单日期
                // orderHeadIn.setValue(orderHeader.getPurch_date(), "PURCH_DATE");
                // orderHeadInX.setValue("X", "PURCH_DATE");

                // 设置客户的采购订单编号
                orderHeadIn.setValue(orderHeader.getPurch_no_c(), "PURCH_NO_C");
                orderHeadInX.setValue("X", "PURCH_NO_C");

                // 交货冻结（抬头）
                // orderHeadIn.setValue("Z2","DLV_BLOCK");
                orderHeadInX.setValue("X", "DLV_BLOCK");
                // 付款条件代码
                // orderHeadIn.setValue("11111111","PMNTTRMS");
                // orderHeadInX.setValue("X","PMNTTRMS");
                // 定价日期和汇率
                // orderHeadIn.setValue("11111111","PRICE_DATE");
                orderHeadInX.setValue("X", "PRICE_DATE");
                // 装运条件
                // orderHeadIn.setValue("11111111","SHIP_COND");
                // orderHeadInX.setValue("X","SHIP_COND");
                orderHeadInX.setValue("U", "UPDATEFLAG");

                // -------PARTNER DATA---------修改订单时不能修改这四个数据
                //
                // AG 售达方
                // RE 出具发票方
                // RG 付款方
                // WE 送达方
                //
                // JCO.Table orderPartners = null;
                // orderPartners =
                // function.getTableParameterList().getTable("PARTNERS");
                // orderPartners.appendRow();
                //
                //
                // orderPartners.setValue("000010","ITM_NUMBER");
                //
                // orderPartners.setValue("AG","PARTN_ROLE");
                // orderPartners.setValue("YBZHFY","PARTN_NUMB");
                //
                // orderPartners.appendRow();
                // orderPartners.setValue("000010","ITM_NUMBER");
                // orderPartners.setValue("RE","PARTN_ROLE");
                // orderPartners.setValue("YBZHFY","PARTN_NUMB");
                //
                //
                // orderPartners.appendRow();
                // orderPartners.setValue("000010","ITM_NUMBER");
                // orderPartners.setValue("RG","PARTN_ROLE");
                // orderPartners.setValue("YBZHFY","PARTN_NUMB");
                //
                // orderPartners.appendRow();
                // orderPartners.setValue("000010","ITM_NUMBER");
                // orderPartners.setValue("WE","PARTN_ROLE");
                // orderPartners.setValue("YBZHFY","PARTN_NUMB");

                // --------------table 订单行-------------
                JCO.Table orderItemsIn = null;
                orderItemsIn = function.getTableParameterList().getTable("ORDER_ITEM_IN");
                JCO.Table orderItemsInX = null;
                orderItemsInX = function.getTableParameterList().getTable("ORDER_ITEM_INX");

                // --------------table价格条件-------
                JCO.Table orderConditionsIn = null;
                JCO.Table orderConditionsInX = null;
                orderConditionsIn = function.getTableParameterList().getTable("CONDITIONS_IN");
                orderConditionsInX = function.getTableParameterList().getTable("CONDITIONS_INX");

                // --------------table行计划------
                // JCO.Table orderSchedulesIn = null;
                // orderSchedulesIn =
                // function.getTableParameterList().getTable("SCHEDULE_LINES");
                // JCO.Table orderSchedulesInX = null;
                // orderSchedulesInX =
                // function.getTableParameterList().getTable("SCHEDULE_LINESX");

                for (ORDER_ITEMS_IN line : orderHeader.getItemList()) {
                    // 订单行//
                    orderItemsIn.appendRow();
                    orderItemsIn.setValue(line.getITEMNO(), "ITM_NUMBER");// 改第x行订单行数据
                    orderItemsIn.setValue(line.getMATERIAL(), "MATERIAL");
                    orderItemsIn.setValue(line.getTARGET_QTY(), "TARGET_QTY");// 每个机型的要货数量
                    orderItemsIn.setValue("SET", "TARGET_QU");
                    orderItemsIn.setValue("1", "SALQTYNUM");
                    orderItemsIn.setValue("1", "SALQTYDEN");
                    orderItemsIn.setValue("SET", "SALES_UNIT");
                    orderItemsIn.setValue(line.getCUST_MAT35(), "CUST_MAT35");

                    orderItemsInX.appendRow();
                    orderItemsInX.setValue(line.getITEMNO(), "ITM_NUMBER");
                    orderItemsInX.setValue("X", "MATERIAL");
                    orderItemsInX.setValue("X", "CUST_MAT35");
                    orderItemsInX.setValue("X", "TARGET_QTY");
                    orderItemsInX.setValue("X", "TARGET_QU");
                    orderItemsInX.setValue("X", "SALQTYNUM");// 销售数量转换成SKU的分子(因子)
                    orderItemsInX.setValue("X", "SALQTYDEN");// 销售数量转换为 SKU 的值（除数）
                    orderItemsInX.setValue("X", "SALES_UNIT");// 销售单位

                    // 销售凭证项目类别
                    // orderItemsIn.setValue("ZFTN", "ITEM_CATEG"); // why?
                    // orderItemsInX.setValue("X", "ITEM_CATEG");
                    orderItemsInX.setValue("U", "UPDATEFLAG");

                    // 条件行 //
                    // 条件行项目号 10,20,30,40 ...
                    /*** PR01 **/
                    orderConditionsIn.appendRow();
                    orderConditionsIn.setValue(line.getITEMNO(), "ITM_NUMBER");
                    orderConditionsIn.setValue("PR01", "COND_TYPE");
                    orderConditionsIn.setValue("010", "COND_ST_NO");
                    orderConditionsIn.setValue("01", "COND_COUNT");
                    // 金额
                    orderConditionsIn.setValue(line.getCOND_VALUE(), "COND_VALUE");// 每一个机型的价格
                    // 货币码
                    orderConditionsIn.setValue("RMB", "CURRENCY");
                    // 条件定价单位
                    orderConditionsIn.setValue("1", "COND_P_UNT");
                    // 条件单位
                    orderConditionsIn.setValue("SET", "COND_UNIT");

                    /*** RR01 **/
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
                    orderConditionsIn.setValue(line.getK007_COND_VALUE() * -10, "COND_VALUE");// k007
                    // 货币码
                    orderConditionsIn.setValue("RMB", "CURRENCY");
                    orderConditionsIn.setValue("X", "COND_UPDAT");
                    /*** K007 **/
                    orderConditionsInX.appendRow();
                    orderConditionsInX.setValue(line.getITEMNO(), "ITM_NUMBER");
                    orderConditionsInX.setValue("K007", "COND_TYPE");
                    orderConditionsInX.setValue("113", "COND_ST_NO");
                    orderConditionsInX.setValue("01", "COND_COUNT");
                    orderConditionsInX.setValue("X", "COND_VALUE");
                    orderConditionsInX.setValue("X", "CURRENCY");
                    orderConditionsInX.setValue("X", "COND_UNIT");
                    orderConditionsInX.setValue("X", "COND_P_UNT");
                    orderConditionsInX.setValue("U", "UPDATEFLAG");// U

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
                    orderConditionsIn.setValue(line.getRB00_COND_VALUE(), "COND_VALUE");// rb00
                    // 货币码
                    orderConditionsIn.setValue("RMB", "CURRENCY");
                    orderConditionsIn.setValue("X", "COND_UPDAT");
                    /*** RB00 **/
                    orderConditionsInX.appendRow();
                    orderConditionsInX.setValue(line.getITEMNO(), "ITM_NUMBER");
                    orderConditionsInX.setValue("RB00", "COND_TYPE");
                    orderConditionsInX.setValue("210", "COND_ST_NO");
                    orderConditionsInX.setValue("01", "COND_COUNT");
                    orderConditionsInX.setValue("X", "COND_VALUE");
                    orderConditionsInX.setValue("X", "CURRENCY");
                    orderConditionsInX.setValue("X", "COND_UNIT");
                    orderConditionsInX.setValue("X", "COND_P_UNT");
                    orderConditionsInX.setValue("U", "UPDATEFLAG");

                    // --------------table ORDER_SCHEDULES-------------
                    // orderSchedulesIn.appendRow();

                    // 与行项目通过 Itm_Number 关联
                    // orderSchedulesIn.setValue(line.getITEMNO(), "ITM_NUMBER");
                    // test
                    // orderSchedulesIn.setValue(Long.valueOf(2), "SCHED_LINE");
                    // /
                    // 1.传什么值? 不传就报错!
                    // orderSchedulesIn.setValue(new Date(), "REQ_DATE");
                    // orderSchedulesIn.setValue(line.getTARGET_QTY(), "REQ_QTY");

                    // orderSchedulesInX.appendRow();
                    // orderSchedulesInX.setValue(line.getITEMNO(), "ITM_NUMBER");
                    // test
                    // orderSchedulesInX.setValue(Long.valueOf(2), "SCHED_LINE");
                    // orderSchedulesInX.setValue("X", "REQ_DATE");
                    // orderSchedulesInX.setValue("X", "REQ_QTY");
                    // orderSchedulesInX.setValue("U", "UPDATEFLAG");
                }

                // 是否提交数据库
                function.getImportParameterList().setValue("X", "COMMIT");

                // 调用执行
                mConnection.execute(function);
                // function.writeHTML("d:/so_update.html");
                // 所有信息从tables 里面取返回表信息,不是从export里面取
                ret = function.getTableParameterList().getTable("RETURN");
                List<ExcuteMsg> msgList = new ArrayList<ExcuteMsg>();
                for (int k = 0; k < ret.getNumRows(); k++) {
                    ret.setRow(k);
                    ExcuteMsg excutemsg = new ExcuteMsg();
                    excutemsg.setType(ret.getString("TYPE").toUpperCase());
                    excutemsg.setMessage(ret.getString("MESSAGE"));
                    msgList.add(excutemsg);
                }

                Boolean success = true;
                // 如果type有E,说明有错误返回
                for (ExcuteMsg msg : msgList) {
                    if (msg.getType() != null) {
                        if ("E".equals(msg.getType().toString().toUpperCase())) {
                            success = false;
                            break;
                        }
                    }
                }
                // 封装返回信息
                returnInfo.setSuccess(success);
                returnInfo.setItemNO(orderNumber);
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

    /**
     * 更改销售订单
     * 
     * 已知规则:1.已经交货的,不能减少数量 ,不能删除订单行 2.流水号不能修改 3.采购订单可以改,4.61单号必须填写,5.行项目号必须填写
     * 
     * 
     * @param args
     */
    public static void main(String args[]) {
        ORDER_HEADER_IN header = new ORDER_HEADER_IN();

        header.setSales_org("KF05");
        header.setDoc_type("ZFOR");
        header.setDistr_chan("10");
        header.setDivision("10");
        header.setPurch_date(new Date());
        header.setPurch_no_c("123456");// 手工填的 采购订单号 y
        header.setOpername("zhouhaojie");// y
        List<ORDER_ITEMS_IN> itemlist = new ArrayList<ORDER_ITEMS_IN>();

        ORDER_ITEMS_IN line = new ORDER_ITEMS_IN();
        line.setITEMNO(10);
        line.setMATERIAL("LED47X8100PDE");// 物料 y line2.setSALES_UNIT("SET");
        line.setTARGET_QTY(2);// 第一行订单行的机型的要货数量//y
        line.setCOND_VALUE(4599);// 第一行订单行的机型的单价含税金额//y
        line.setK007_COND_VALUE(-0);// y line2.setRB00_COND_VALUE(-250);// y
        line.setRB00_COND_VALUE(-10);
        line.setCUST_MAT35("test");
        line.setTARGET_QU("SET");

        ORDER_ITEMS_IN line2 = new ORDER_ITEMS_IN();
        line2.setITEMNO(20);
        line2.setMATERIAL("LED42M3820AF");// 物料 y,一个单里面只能用一次.
        line2.setTARGET_QTY(3);// 第一行订单行的机型的要货数量//y
        line2.setCOND_VALUE(4001);// 第一行订单行的机型的单价含税金额//y
        line2.setK007_COND_VALUE(0);// y line2.setRB00_COND_VALUE(-250);// y
        line2.setRB00_COND_VALUE(0);
        line2.setCUST_MAT35("remark");
        line2.setTARGET_QU("SET");

        itemlist.add(line);
        itemlist.add(line2);

        header.setItemList(itemlist);
        ReturnInfo returnInfo = new Call_Update_So().doCall(header, "8743748342");//
        returnInfo.getMsgList();
        // System.out.println(returnInfo.getSuccess());
        for (Object oo : returnInfo.getMsgList()) {
            ExcuteMsg em = (ExcuteMsg) oo;
            // System.out.println(em.getType() + ":" + em.getMessage());
        }
    }

}
