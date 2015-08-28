package com.ebiz.mmt.r3;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;
import com.sap.mw.jco.JCO.Table;

/**
 * 存销比
 * 
 * @author ZHOU
 */
public class Call_Get_ZlesZJ98 {
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

    public List<ZJ98> doCall(String v_matnr_begin, String v_matnr_end, String v_vtweg, String v_spart, String v_bstdk_begin, String v_bstdk_end, String v_cxb_begin,
            String v_cxb_end) {
        List<ZJ98> list = new ArrayList<ZJ98>();

        JCO.Client mConnection = null;
        JCO.Repository mRepository = null;
        JCO.Function function = null;
        try {
            mConnection = ConnectPoolManager.getConnectionInSAPPool();
            mConnection.connect();
            mRepository = new JCO.Repository("KJQD", mConnection);
            function = this.createFunction("Z_RFC_ZJ98A", mRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mConnection != null && mRepository != null && function != null) {
            try {
                // 设置要查询的分销渠道
                // "10";
                JCO.Table v_vtweg2 = null;
                v_vtweg2 = function.getTableParameterList().getTable("V_VTWEG");
                // 单值
                if (v_vtweg != null && !"".equals(v_vtweg)) {
                    v_vtweg2.appendRow();
                    v_vtweg2.setValue("I", "SIGN");
                    v_vtweg2.setValue("EQ", "OPTION");
                    v_vtweg2.setValue(v_vtweg, "LOW");
                }

                // 设置要查询的产品组
                // "10";
                JCO.Table v_spart2 = null;
                v_spart2 = function.getTableParameterList().getTable("V_SPART");
                if (v_spart != null && !"".equals(v_spart)) {
                    v_spart2.appendRow();
                    v_spart2.setValue("I", "SIGN");
                    v_spart2.setValue("EQ", "OPTION");
                    v_spart2.setValue(v_spart, "LOW");
                }

                // 设置要查询的型号
                Table v_matnr = null;
                v_matnr = function.getTableParameterList().getTable("V_MATNR");
                if ((v_matnr_begin != null && !"".equals(v_matnr_begin)) || (v_matnr_end != null && !"".equals(v_matnr_end))) {
                    // 单个值
                    if (v_matnr_begin != null && !"".equals(v_matnr_begin)) {
                        v_matnr.appendRow();
                        v_matnr.setValue("I", "SIGN");
                        v_matnr.setValue("EQ", "OPTION");
                        v_matnr.setValue(v_matnr_begin, "LOW");
                    }

                    // 范围
                    v_matnr.appendRow();
                    v_matnr.setValue("I", "SIGN");
                    v_matnr.setValue("BT", "OPTION");
                    if (v_matnr_begin != null && !"".equals(v_matnr_begin)) {
                        v_matnr.setValue(v_matnr_begin, "LOW");
                    }
                    if (v_matnr_end != null && !"".equals(v_matnr_end)) {
                        v_matnr.setValue(v_matnr_end, "HIGH");
                    }

                    // 模糊查询
                    if (v_matnr_begin != null && !"".equals(v_matnr_begin)) {
                        v_matnr.appendRow();
                        v_matnr.setValue("I", "SIGN");
                        v_matnr.setValue("CP", "OPTION");
                        v_matnr.setValue(v_matnr_begin, "LOW");
                    }
                }

                // 设置要查询的日期
                // "20131201"，"20131212";
                JCO.Table v_bstdk2 = null;
                v_bstdk2 = function.getTableParameterList().getTable("V_BSTDK");
                if ((v_bstdk_begin != null && !"".equals(v_bstdk_begin)) || (v_bstdk_end != null && !"".equals(v_bstdk_end))) {

                    v_bstdk2.appendRow();
                    v_bstdk2.setValue("I", "SIGN");
                    v_bstdk2.setValue("BT", "OPTION");
                    if (v_bstdk_begin != null && !"".equals(v_bstdk_begin)) {
                        v_bstdk2.setValue(v_bstdk_begin, "LOW");
                    }
                    if (v_bstdk_end != null && !"".equals(v_bstdk_end)) {
                        v_bstdk2.setValue(v_bstdk_end, "HIGH");
                    }
                }

                // 要查询的存销比(存销比影响产品组的输出)
                JCO.Table v_cxb = null;
                v_cxb = function.getTableParameterList().getTable("V_CXB");
                if ((v_cxb_begin != null && !"".equals(v_cxb_begin)) || (v_cxb_end != null && !"".equals(v_cxb_end))) {
                    v_cxb.appendRow();
                    v_cxb.setValue("I", "SIGN");
                    v_cxb.setValue("BT", "OPTION");
                    if (v_cxb_begin != null && !"".equals(v_cxb_begin)) {
                        v_cxb.setValue(v_cxb_begin, "LOW");
                    }
                    if (v_cxb_end != null && !"".equals(v_cxb_end)) {
                        v_cxb.setValue(v_cxb_end, "HIGH");
                    }
                }
                mConnection.execute(function);
                JCO.Table item1 = null;
                item1 = function.getTableParameterList().getTable("IT_FXITEM");

                for (int i = 0; i < item1.getNumRows(); i++) {
                    item1.setRow(i);
                    ZJ98 zj98 = new ZJ98();
                    zj98.setVTWEG(item1.getString("VTWEG"));
                    zj98.setMATKL(item1.getString("MATKL"));
                    zj98.setMATNR(item1.getString("MATNR"));
                    zj98.setMENGE(item1.getString("MENGE"));
                    zj98.setSAAMT(item1.getString("SAAMT"));
                    zj98.setNETPR(item1.getString("NETPR"));
                    zj98.setCOAMT(item1.getString("COAMT"));
                    zj98.setUNIT_CO(item1.getString("UNIT_CO"));
                    zj98.setUNIT_ML(item1.getString("UNIT_ML"));
                    zj98.setML(item1.getString("ML"));
                    zj98.setMLV(item1.getString("MLV"));
                    zj98.setGCB(item1.getString("GCB"));
                    zj98.setGXMLV(item1.getString("GXMLV"));
                    zj98.setLBKUM(item1.getString("LBKUM"));
                    zj98.setLABSFK(item1.getString("LABSFK"));
                    zj98.setLABS2(item1.getString("LABS2"));
                    zj98.setLABS3(item1.getString("LABS3"));
                    zj98.setLABS16(item1.getString("LABS16"));
                    zj98.setLABS6(item1.getString("LABS6"));
                    zj98.setLABS11(item1.getString("LABS11"));
                    zj98.setLABS12(item1.getString("LABS12"));
                    zj98.setLABS13(item1.getString("LABS13"));
                    zj98.setLABS14(item1.getString("LABS14"));
                    zj98.setLABS15(item1.getString("LABS15"));
                    zj98.setLABS17(item1.getString("LABS17"));
                    zj98.setLABS18(item1.getString("LABS18"));
                    zj98.setLABS10(item1.getString("LABS10"));
                    zj98.setLABSDF(item1.getString("LABSDF"));
                    zj98.setLABS5(item1.getString("LABS5"));
                    zj98.setLABS4(item1.getString("LABS4"));
                    zj98.setLABS7(item1.getString("LABS7"));
                    zj98.setLABSF(item1.getString("LABSF"));
                    zj98.setLABSZ(item1.getString("LABSZ"));
                    zj98.setVERPR(item1.getString("VERPR"));
                    zj98.setSALK3(item1.getString("SALK3"));
                    zj98.setSTOCK_GCB(item1.getString("STOCK_GCB"));
                    zj98.setSTOCK_ML(item1.getString("STOCK_ML"));
                    zj98.setSTOCK_MLV(item1.getString("STOCK_MLV"));
                    zj98.setSTOCK_CXB(item1.getString("STOCK_CXB"));
                    list.add(zj98);
                }
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
        return list;
    }

    public static void main(String args[]) {
        long s = System.currentTimeMillis();
        Call_Get_ZlesZJ98 call_Get_ZlesZJ98 = new Call_Get_ZlesZJ98();
        List<ZJ98> list = call_Get_ZlesZJ98.doCall("LED19*", "", "10", "10", "20140101", "20140101", "10", "99999");
        long e = System.currentTimeMillis();
        // System.out.println(list.size());
        // System.out.println("taketime:=======>" + (e - s) / 1000 + "s");
    }
}
