package com.ebiz.mmt.r3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ebiz.mmt.domain.KonkaR3OrderLines;
import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;

/**
 * 分公司库存(用于提交订单时(此刻机型没有选择),进行库存检验.物料必选) 客户提交订单的时候使用,查的也是分公司的库存,此时还没有具体到工厂和仓位
 * 
 * @author ZHOU
 * 
 */
public class Call_Get_Lgort_Stock {

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

    /**
     * 把某张订单上的订单行,一次性进行校验 然后返回各个物料行是否满足
     * 
     * @param zbukrs 不能为空
     * @param itemList //行数据 必填项 :物料,数量
     * @return 是否满足
     */
    public List<StockCheckResult> doCall(String zbukrs, List<KonkaR3OrderLines> itemList) {

        if (itemList == null || itemList.size() <= 0) {
            throw new IllegalArgumentException("参数不能为空!");
        }
        List<StockCheckResult> rList = new ArrayList<StockCheckResult>();


        JCO.Client mConnection = null;
        JCO.Repository mRepository = null;
        JCO.Function function = null;
        try {
            mConnection = ConnectPoolManager.getConnectionInSAPPool();
            mConnection.connect();
            mRepository = new JCO.Repository("KJQD", mConnection);
            function = this.createFunction("ZMMT_GET_LOGRT_STOCK", mRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mConnection != null && mRepository != null && function != null) {
            try {
                // 要查询的公司代码
                if (zbukrs != null && zbukrs.length() != 0) {
                    // 公司代码参数
                    function.getImportParameterList().setValue(zbukrs.toUpperCase(), "ZBUKRS");
                }

                JCO.Table table1 = null;
                table1 = function.getTableParameterList().getTable("ZMARD_LIST");

                for (KonkaR3OrderLines item : itemList) {
                    table1.appendRow();
                    table1.setValue(item.getMaterial_code(), "MATNR"); // 物料号
                    table1.setValue(item.getReview_amount(), "UMLME"); // 需求数量
                }


                // 调用
                mConnection.execute(function);
                // function.writeHTML("D:/Call_Get_Lgort_Stock.html");
                // //System.out.println("物料工厂仓位库存信息 begin");
                JCO.Table item1 = null;
                item1 = function.getTableParameterList().getTable("ZMARD_LIST");
                for (int i = 0; i < item1.getNumRows(); i++) {
                    item1.setRow(i);

                    // 物料 工厂 库位 需要数量 非限制使用库存 交货单锁定库存
                    // //System.out.println(item1.getString("MATNR") + '\t' +
                    // item1.getString("WERKS") + '\t'
                    // + item1.getString("LGORT") + '\t' + item1.getString("UMLME")
                    // + '\t' + item1.getString("LABST")
                    // + "\t" + item1.getString("SPEME"));
                    StockCheckResult stockCheckReuslt = new StockCheckResult();

                    stockCheckReuslt.setWerks(item1.getString("WERKS"));// 工厂
                    stockCheckReuslt.setName1(item1.getString("NAME1"));// 工厂NAME
                    stockCheckReuslt.setMatnr(item1.getString("MATNR"));// 物料号
                    stockCheckReuslt.setLgort(item1.getString("LGORT"));// 库位
                    stockCheckReuslt.setLgobe(item1.getString("LGOBE"));// 库位NAME
                    stockCheckReuslt.setBukrs(zbukrs);// 分公司

                    stockCheckReuslt.setAmount(new BigDecimal(item1.getString("UMLME") + ""));// 需求量

                    stockCheckReuslt.setLabst(new BigDecimal(item1.getString("LABST") + ""));// 非限制使用库存
                    stockCheckReuslt.setSpeme(new BigDecimal(item1.getString("SPEME") + ""));// 交货单锁定库存

                    // 剩余量 = labst - speme
                    stockCheckReuslt.setLamount(new BigDecimal(stockCheckReuslt.getLabst().intValue() - stockCheckReuslt.getSpeme().intValue()));

                    // 需求>剩余 = 0 不满足
                    if ((stockCheckReuslt.getAmount().intValue() - stockCheckReuslt.getLamount().intValue()) > 0) {
                        stockCheckReuslt.setIsOk(0);// 是否满足 0不满足,1满足
                    } else {
                        stockCheckReuslt.setIsOk(1);
                    }
                    rList.add(stockCheckReuslt);
                }

            } catch (Exception ex) {
                logger.info(ex.getMessage());

            } finally {
                mRepository = null;
                if (mConnection != null) {
                    ConnectPoolManager.releaseClient(mConnection);
                }
            }
        }
        return rList;
    }

    public static void main(String[] args) {
        Call_Get_Lgort_Stock cc = new Call_Get_Lgort_Stock();
        List<KonkaR3OrderLines> itemList = new ArrayList<KonkaR3OrderLines>();
        KonkaR3OrderLines kl = new KonkaR3OrderLines();
        kl.setMaterial_code("LED42F3700NF");
        kl.setReview_amount(new BigDecimal(11));
        itemList.add(kl);
        List<StockCheckResult> list = cc.doCall("F475", itemList);

        for (StockCheckResult s : list) {
            System.out.println(s.getIsOk());
        }
    }
}
