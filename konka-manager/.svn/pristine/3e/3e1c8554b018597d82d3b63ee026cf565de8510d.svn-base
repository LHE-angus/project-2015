package com.ebiz.mmt.r3;

import java.util.ArrayList;
import java.util.List;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;

public class Call_Get_So_Lips {

    private JCO.Function createFunction(String name, JCO.Repository mRepository) throws Exception {
        try {
            IFunctionTemplate ft = mRepository.getFunctionTemplate(name.toUpperCase());
            if (ft == null) return null;
            return ft.getFunction();
        } catch (Exception ex) {
            throw new Exception("Problem retrieving JCO.Function object.");
        }
    }

    public List<ZVBALR> doCall(String v_vkorg, String v_vtweg, String v_spart, String v_audat_begin, String v_audat_end, String v_kunnr, String v_vbeln) {

        List<ZVBALR> list = new ArrayList<ZVBALR>();

        JCO.Client mConnection = null;
        JCO.Repository mRepository = null;
        JCO.Function function = null;
        try {
            mConnection = ConnectPoolManager.getConnectionInSAPPool();
            mConnection.connect();
            mRepository = new JCO.Repository("KJQD", mConnection);
            function = this.createFunction("ZMMT_GET_SO_LIPS", mRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mConnection != null && mRepository != null && function != null) {

            try {
                if (v_vkorg != null && !"".equals(v_vkorg)) {
                    function.getImportParameterList().setValue(v_vkorg, "V_VKORG");
                }

                if (v_vtweg != null && !"".equals(v_vtweg)) {
                    function.getImportParameterList().setValue(v_vtweg, "V_VTWEG");
                }
                if (v_spart != null && !"".equals(v_spart)) {
                    function.getImportParameterList().setValue(v_spart, "V_SPART");
                }

                if (v_audat_begin != null && !"".equals(v_audat_begin)) {
                    function.getImportParameterList().setValue(v_audat_begin, "V_AUDAT_BEGIN");
                }

                if (v_audat_end != null && !"".equals(v_audat_end)) {
                    function.getImportParameterList().setValue(v_audat_end, "V_AUDAT_END");
                }
                if (v_kunnr != null && !"".equals(v_kunnr)) {
                    function.getImportParameterList().setValue(v_kunnr, "V_KUNNR");
                }
                if (v_vbeln != null && !"".equals(v_vbeln)) {
                    function.getImportParameterList().setValue(v_vbeln, "V_VBELN");
                }

                // 调用执行
                mConnection.execute(function);
                // function.writeHTML("d:/Call_Get_So_Lips.html");
                JCO.Table items = function.getTableParameterList().getTable("ZVBALR_LIST");
                for (int i = 0; i < items.getNumRows(); i++) {
                    items.setRow(i);
                    ZVBALR vb = new ZVBALR();

                    if ("0000-00-00".equals(items.getString("VDATU"))) {
                        vb.setVDATU("");
                    } else {
                        vb.setVDATU(items.getString("VDATU"));
                    }
                    // 订单创建日期
                    if ("0000-00-00".equals(items.getString("ERDAT"))) {
                        vb.setERDAT("");
                    } else {
                        vb.setERDAT(items.getString("ERDAT"));
                    }

                    // 凭证日期
                    if ("0000-00-00".equals(items.getString("AUDAT"))) {
                        vb.setAUDAT("");
                    } else {
                        vb.setAUDAT(items.getString("AUDAT"));
                    }

                    vb.setVKBUR(items.getString("VKBUR"));
                    vb.setSPART(items.getString("SPART"));
                    vb.setVBELN(items.getString("VBELN"));

                    vb.setKUNNR(items.getString("KUNNR"));
                    vb.setAUART(items.getString("AUART"));
                    vb.setVTWEG(items.getString("VTWEG"));
                    vb.setVKORG(items.getString("VKORG"));
                    vb.setWEKUNNR(items.getString("WEKUNNR"));
                    vb.setREKUNNR(items.getString("REKUNNR"));
                    vb.setRGKUNNR(items.getString("RGKUNNR"));

                    vb.setNETWR(items.getString("NETWR"));
                    vb.setPOSNR(items.getString("POSNR"));
                    vb.setMATKL(items.getString("MATKL"));
                    vb.setMATNR(items.getString("MATNR"));
                    vb.setMEINS(items.getString("MEINS"));

                    vb.setKWMENG(items.getString("KWMENG"));
                    vb.setJWMENG(items.getString("JWMENG"));
                    vb.setMWMENG(items.getString("MWMENG"));
                    vb.setRWMENG(items.getString("RWMENG"));
                    vb.setKZWI1(items.getString("KZWI1"));
                    vb.setKZWI5(items.getString("KZWI5"));
                    vb.setKZWI6(items.getString("KZWI6"));

                    vb.setCMPRE(items.getString("CMPRE"));

                    vb.setKDMAT(items.getString("KDMAT"));

                    if ("0000-00-00".equals(items.getString("BSTDK"))) {
                        vb.setBSTDK("");
                    } else {
                        vb.setBSTDK(items.getString("BSTDK"));
                    }

                    vb.setBSTNK(items.getString("BSTNK"));
                    vb.setKNUMV(items.getString("KNUMV"));
                    vb.setWERKS(items.getString("WERKS"));
                    vb.setLGORT(items.getString("LGORT"));

                    vb.setWERKS_L(items.getString("WERKS_L"));
                    vb.setLGORT_L(items.getString("LGORT_L"));
                    // 交货单生成日期
                    if ("0000-00-00".equals(items.getString("ERDAT_L"))) {
                        vb.setERDAT_L("");
                    } else {
                        vb.setERDAT_L(items.getString("ERDAT_L"));
                    }
                    // 过账日期
                    if ("0000-00-00".equals(items.getString("MBDAT_L"))) {
                        vb.setMBDAT_L("");
                    } else {
                        vb.setMBDAT_L(items.getString("MBDAT_L"));
                    }

                    // vb.setVBELN_L(items.getString("VBELN_L"));
                    vb.setVBELN_L(null);// 未确认
                    vb.setPOSNR_L(items.getString("POSNR_L"));
                    vb.setPSTYV_L(items.getString("PSTYV_L"));
                    vb.setWADAT_IST(items.getString("WADAT_IST"));
                    vb.setVBELN_LES(items.getString("VBELN_LES"));
                    vb.setLFIMG_L(items.getString("LFIMG_L"));

                    vb.setK007(items.getString("K007"));
                    vb.setRB00(items.getString("RB00"));
                    list.add(vb);
                }
            } catch (Exception ex) {} finally {
                mRepository = null;
                if (mConnection != null) {
                    ConnectPoolManager.releaseClient(mConnection);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<ZVBALR> list = new Call_Get_So_Lips().doCall("KF47", "10", "10", "2013-09-01", "2013-09-30", "", "");
        // List<ZVBALR> list = new Call_Get_So_Lips().doCall("KF47", "10", "10",
        // "2013-09-01", "2013-09-30", "F147RMJ",
        // "0614080192");
        // System.out.println(list.size());
    }
}
