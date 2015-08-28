package com.ebiz.mmt.r3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;

/**
 * 这个也可以查y/p/q/d 并且可以看到移动平均价,总库存价值等信息<br/>
 * 这个sap程序不能通过机型过滤
 * 
 * @author ZHOU
 * 
 */
public class Call_Get_KCXX {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public JCO.Function createFunction(String name, JCO.Repository mRepository) throws Exception {
        try {
            IFunctionTemplate ft = mRepository.getFunctionTemplate(name.toUpperCase());
            if (ft == null) {
                return null;
            }
            return ft.getFunction();
        } catch (Exception ex) {
            throw new Exception("Problem retrieving JCO.Function object.");
        }
    }

    public List<KCXX> doCall(String v_werks, String v_lgort, String v_lgpla, String v_matnr) {

        List<KCXX> list = new ArrayList<KCXX>();
        List<KCXX> fitlerlist = new ArrayList<KCXX>();

        JCO.Client mConnection = null;
        JCO.Repository mRepository = null;
        JCO.Function function = null;
        try {
            mConnection = ConnectPoolManager.getConnectionInSAPPool();
            mConnection.connect();
            mRepository = new JCO.Repository("KJQD", mConnection);
            function = this.createFunction("ZMMT_GET_KCXX", mRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mConnection != null && mRepository != null && function != null) {
            try {
                // 工厂 L00A
                // "L00A";
                if (v_werks != null && v_werks.length() != 0) {
                    function.getImportParameterList().setValue(v_werks.toUpperCase(), "V_WERKS");
                }

                // 库存地点 Y006,一般分公司有库存地点 库位
                // "Y006";
                if (v_lgort != null && v_lgort.length() != 0) {
                    function.getImportParameterList().setValue(v_lgort.toUpperCase(), "V_LGORT");
                }
                // LGPLA 仓位 一般来说,是用客户编码做仓位 仓位
                // "F106BSZYD";
                if (v_lgpla != null && v_lgpla.length() != 0) {
                    function.getImportParameterList().setValue(v_lgpla.toUpperCase(), "V_LGPLA");
                }
                // 物料号
                // "LBG32FLBG32F";
                if (v_matnr != null && v_matnr.length() != 0) {
                    JCO.Table table1 = null;
                    table1 = function.getTableParameterList().getTable("ZMARA_LIST");
                    table1.appendRow();
                    table1.setValue(v_matnr, "MATNR");
                }

                // 调用
                mConnection.execute(function);
                JCO.Table item1 = null;
                item1 = function.getTableParameterList().getTable("ZLES_BIN_INFO_LIST");
                for (int i = 0; i < item1.getNumRows(); i++) {
                    item1.setRow(i);

                    // LGPLA LGPLA CHAR 10 0 仓位(客户编码做仓位)
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

                    KCXX kcxx = new KCXX();
                    kcxx.setCHARG(item1.getString("CHARG"));
                    kcxx.setLGBER(item1.getString("LGBER"));
                    kcxx.setLGNUM(item1.getString("LGNUM"));
                    kcxx.setLGORT(item1.getString("LGORT"));
                    kcxx.setLGPLA(item1.getString("LGPLA"));
                    kcxx.setLGTYP(item1.getString("LGTYP"));
                    kcxx.setLINES(item1.getString("LINES"));
                    kcxx.setLPTYP(item1.getString("LPTYP"));
                    kcxx.setMAKTX(item1.getString("MAKTX"));
                    kcxx.setMATNR(item1.getString("MATNR"));
                    kcxx.setMEINS(item1.getString("MEINS"));
                    kcxx.setNAME1(item1.getString("NAME1"));
                    kcxx.setNEWP(item1.getString("NEWP"));
                    kcxx.setNEWPAGE(item1.getString("NEWPAGE"));
                    kcxx.setWERKS(item1.getString("WERKS"));
                    kcxx.setZPNUM(item1.getString("ZPNUM"));
                    kcxx.setPTYPT(item1.getString("PTYPT"));

                    // //System.out.println(item1.getString("MATNR") + "xxxx");

                    if (item1.getString("EDATU").equals("") || item1.getString("EDATU").equals("0000-00-00")) {
                        kcxx.setEDATU("");
                    } else {
                        kcxx.setEDATU(item1.getString("EDATU"));
                    }
                    kcxx.setSALK3(new BigDecimal(item1.getString("SALK3")));
                    kcxx.setTRAME(new BigDecimal(item1.getString("TRAME")));
                    kcxx.setVERME(new BigDecimal(item1.getString("VERME")));
                    kcxx.setVERPR(new BigDecimal(item1.getString("VERPR")));
                    kcxx.setMENGE(new BigDecimal(item1.getString("MENGE")));
                    kcxx.setLINE(new BigDecimal(item1.getString("LINE")));
                    list.add(kcxx);
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

        // 由于sap没有提供,手工过滤
        if (v_matnr != null && v_matnr.length() != 0) {
            for (KCXX kx : list) {
                if (kx.getMATNR().equals(v_matnr.trim())) {
                    fitlerlist.add(kx);
                }
            }
        }
        return fitlerlist.size() > 0 ? fitlerlist : list;

    }

    public static void main(String args[]) {
        Call_Get_KCXX call_Get_KCXX = new Call_Get_KCXX();
        // List<KCXX> list = call_Get_KCXX.doCall("L00A", "Y006", "", "");
        List<KCXX> list = call_Get_KCXX.doCall("L00A", "Y006", "", "");

        // "Y006" "F106BSZYD" "L00A"
        // 工厂不能为空
        // 库存地点 不能为空 (跟分公司有关)
        // 仓位 不能为空
        // 查指定物料号无效,没有作相应的过滤
        // System.out.println(list.size());
        for (KCXX kc : list) {
            // System.out.println("kcxx:" + kc.getVERME() + "," + kc.getMENGE() + "," +
            // kc.getSALK3() + ","
            // + kc.getVERPR() + "," + kc.getMATNR() + "," + kc.getLGPLA());
        }
    }
}
