package com.ebiz.mmt.r3;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;

/**
 * y/p/q/d仓库存情况,但看不到移动平均价和总库存价值 <br/>
 * 可按机型过滤
 * 
 * @author zhou
 * 
 */
public class Call_Get_Zles20 {
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

    public List<ZLEBIN> doCall(String zwerks, String zlgort, String zlgpla, String zmatnr) {
        List<ZLEBIN> list = new ArrayList<ZLEBIN>();

        JCO.Client mConnection = null;
        JCO.Repository mRepository = null;
        JCO.Function function = null;
        try {
            mConnection = ConnectPoolManager.getConnectionInSAPPool();
            mConnection.connect();
            mRepository = new JCO.Repository("KJQD", mConnection);
            function = this.createFunction("ZMMT_GET_ZLES20", mRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mConnection != null && mRepository != null && function != null) {

            try {

                // 工厂 not null
                if (zwerks != null && zwerks.length() > 0) {
                    function.getImportParameterList().setValue(zwerks, "ZWERKS");
                }

                // 库位 not null
                if (zlgort != null && zlgort.length() > 0) {
                    function.getImportParameterList().setValue(zlgort, "ZLGORT");
                }
                // 仓位 choose
                if (zlgpla != null && zlgpla.length() > 0) {
                    function.getImportParameterList().setValue(zlgpla, "ZLGPLA");
                }
                // 机型 choose
                if (zmatnr != null && zmatnr.length() > 0) {
                    function.getImportParameterList().setValue(zmatnr, "ZMATNR");
                }

                // 调用
                mConnection.execute(function);
                // function.writeHTML("d:/Call_Get_Zles20.html");

                // LGPLA LGPLA CHAR 10 0 仓位
                // ZPNUM CHAR6 CHAR 6 0 长度为6的字符字段
                // MATNR MATNR CHAR 18 0 物料号
                // VERME LQUA_VERME QUAN 13 3 可用库存
                // TRAME LQUA_TRAME QUAN 13 3 未清转储数量
                // LGNUM LGNUM CHAR 3 0 仓库号/混合仓库
                // LGTYP LGTYP CHAR 3 0 仓储类型
                // NAME1 NAME1_GP CHAR 35 0 名称 1
                // LGBER LGBER CHAR 3 0 存储区
                // LPTYP LVS_LPTYP CHAR 2 0 仓位类型
                // PTYPT LVS_PTYPT CHAR 20 0 仓位类型说明
                // MAKTX MAKTX CHAR 40 0 物料描述（短文本）
                // CHARG CHARG_D CHAR 10 0 批号
                // WERKS WERKS_D CHAR 4 0 工厂
                // LGORT LGORT_D CHAR 4 0 库存地点
                // MEINS MEINS UNIT 3 0 基本计量单位
                // NEWPAGE FLAG CHAR 1 0 一般标记
                // LINES CHAR13 CHAR 13 0 13位字符字段
                // MENGE LQUA_VERME QUAN 13 3 可用库存
                // LINE INT2 5 0 行记录数量
                // NEWP FLAG CHAR 1 0 一般标记
                // VERPR VERPR CURR 11 2 移动平均价格/周期单价
                // SALK3 SALK3 CURR 13 2 估价的总库存价值
                // EDATU EDATU DATS 8 0 计划行日期

                JCO.Table item1 = null;
                item1 = function.getTableParameterList().getTable("ZLES_BIN_INFO_LIST");
                for (int i = 0; i < item1.getNumRows(); i++) {
                    item1.setRow(i);
                    ZLEBIN zlebin = new ZLEBIN();
                    zlebin.setCHARG(item1.getString("CHARG"));
                    if ("".equals(item1.getString("EDATU")) || "0000-00-00".equals(item1.getString("EDATU"))) {
                        zlebin.setEDATU("");
                    } else {
                        zlebin.setEDATU(item1.getString("EDATU"));
                    }

                    zlebin.setLGBER(item1.getString("LGBER"));
                    zlebin.setLGNUM(item1.getString("LGNUM"));
                    zlebin.setLGORT(item1.getString("LGORT"));
                    zlebin.setLGPLA(item1.getString("LGPLA"));
                    zlebin.setLGTYP(item1.getString("LGTYP"));

                    zlebin.setLINE(item1.getString("LINE").equals("") ? 0d : Double.valueOf(item1.getString("LINE")));
                    zlebin.setMENGE(item1.getString("MENGE").equals("") ? 0d : Double.valueOf(item1.getString("MENGE")));
                    zlebin.setSALK3(item1.getString("SALK3").equals("") ? 0d : Double.valueOf(item1.getString("SALK3")));
                    zlebin.setTRAME(item1.getString("TRAME").equals("") ? 0d : Double.valueOf(item1.getString("TRAME")));
                    zlebin.setVERME(item1.getString("VERME").equals("") ? 0d : Double.valueOf(item1.getString("VERME")));
                    zlebin.setVERPR(item1.getString("VERPR").equals("") ? 0d : Double.valueOf(item1.getString("VERPR")));

                    zlebin.setLINES(item1.getString("LINES"));
                    zlebin.setMATNR(item1.getString("MATNR"));
                    zlebin.setMAKTX(item1.getString("MAKTX"));
                    zlebin.setMEINS(item1.getString("MEINS"));
                    zlebin.setNAME1(item1.getString("NAME1"));
                    zlebin.setNEWP(item1.getString("NEWP"));
                    zlebin.setNEWPAGE(item1.getString("NEWPAGE"));
                    zlebin.setPTYPT(item1.getString("PTYPT"));
                    zlebin.setWERKS(item1.getString("WERKS"));
                    zlebin.setZPNUM(item1.getString("ZPNUM"));

                    list.add(zlebin);
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
        return list;

    }

    public static void main(String args[]) {
        Call_Get_Zles20 call_Get_Zles20 = new Call_Get_Zles20();
        // 工厂 库位 仓位 机型
        List<ZLEBIN> list = call_Get_Zles20.doCall("L00G", "Y031", "F131CPXSJ", "LED55X9800U");
        // System.out.println(list.size());

        // System.out.println(list.get(0).getSALK3());
    }
}
