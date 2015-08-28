package com.ebiz.mmt.r3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;

/**
 * 根据工厂,库位,机型支持模糊查询相关库存信息
 * 
 * 目前是根据输入的参数条件而定,可以考正品90仓,也可以查物流仓(管理端查询库存,此时一般都维护了工厂,库位)
 * 
 * @author ZHOU
 * 
 */
public class Call_Get_Werks_Lgort {
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
     * 
     * @param zwerks not null
     * @param zlgort not null
     * @param zmatnr
     * @return
     */
    public List<StockCheckResult> doCall(String zwerks, String zlgort, String zmatnr) {
        List<StockCheckResult> list = new ArrayList<StockCheckResult>();

        JCO.Client mConnection = null;
        JCO.Repository mRepository = null;
        JCO.Function function = null;
        try {
            mConnection = ConnectPoolManager.getConnectionInSAPPool();
            mConnection.connect();
            mRepository = new JCO.Repository("KJQD", mConnection);
            function = this.createFunction("ZMMT_GET_WERKS_LOGRT", mRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mConnection != null && mRepository != null && function != null) {

            try {

                if (zwerks != "") {
                    function.getImportParameterList().setValue(zwerks, "ZWERKS");
                }

                if (zlgort != "") {
                    function.getImportParameterList().setValue(zlgort, "ZLGORT");
                }
                // 设置要查询的物料 ---可选参数
                // String zmatnr = "A1486E";
                if (zmatnr != null && !"".equals(zmatnr)) {
                    function.getImportParameterList().setValue(zmatnr, "ZMATNR");
                }


                // 调用
                mConnection.execute(function);
                // function.writeHTML("D:/Call_Get_Werks_Lgort.html");
                // //System.out.println("物料工厂库位库存信息 begin");
                JCO.Table item1 = null;
                item1 = function.getTableParameterList().getTable("ZMARD_LIST");
                for (int i = 0; i < item1.getNumRows(); i++) {
                    item1.setRow(i);
                    // 物料 工厂 库位 需要数量 库存数量 是否满足(1满足，0不满足)
                    // //System.out.println(item1.getString("MATNR") + '\t' +
                    // item1.getString("WERKS") + '\t'
                    // + item1.getString("LGORT") + '\t' + item1.getString("UMLME")
                    // + '\t' + item1.getString("LABST")
                    // + '\t' + item1.getString("LVORM"));

                    StockCheckResult stockCheckReuslt = new StockCheckResult();
                    stockCheckReuslt.setMatnr(item1.getString("MATNR"));// 物料号
                    stockCheckReuslt.setWerks(item1.getString("WERKS"));// 工厂
                    stockCheckReuslt.setName1(item1.getString("NAME1"));// 工厂NAME
                    stockCheckReuslt.setLgort(item1.getString("LGORT"));// 库位
                    stockCheckReuslt.setLgobe(item1.getString("LGOBE"));// 库位NAME
                    // 交货单锁定库存
                    stockCheckReuslt.setSpeme(new BigDecimal(item1.getString("SPEME") + ""));
                    // LABST 非限制使用库存
                    stockCheckReuslt.setLabst(new BigDecimal(item1.getString("LABST") + ""));
                    // 剩余量
                    if (stockCheckReuslt.getSpeme() != null && stockCheckReuslt.getLabst() != null) {
                        stockCheckReuslt.setLamount((stockCheckReuslt.getLabst()).subtract(stockCheckReuslt.getSpeme()));
                    } else {
                        stockCheckReuslt.setLamount(new BigDecimal("0"));
                    }

                    list.add(stockCheckReuslt);

                }
                // //System.out.println("物料工厂仓位库存信息 end");

            } catch (Exception ex) {
                logger.info(ex.getMessage());

            } finally {
                if (mConnection != null) {
                    ConnectPoolManager.releaseClient(mConnection);
                }
                mRepository = null;
            }
        }

        Collections.sort(list);
        return list;
    }

    public static void main(String args[]) {
        Call_Get_Werks_Lgort Call_Get_Werks_Lgort = new Call_Get_Werks_Lgort();

        // 1.工厂必输
        // 2.库位必输
        // 3.物料非必填
        // 使用方法. 工厂绑定分公司 然后人员登录时,自动带出工厂
        List<StockCheckResult> rList = new ArrayList<StockCheckResult>();
        rList = Call_Get_Werks_Lgort.doCall("L00C", "9034", null);
        // System.out.println(rList.size());
        for (StockCheckResult sr : rList) {
            System.out.println(sr.getMatnr());
            System.out.println(sr.getLabst());
            System.out.println(sr.getSpeme());
            System.out.println(sr.getLamount());
        }
    }
}
