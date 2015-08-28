package com.ebiz.mmt.r3;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;

public class Call_Get_Customer_Knvp02 {
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

    public List<KNVP> doCall(String vkorg, String vtweg, String spart, String kunnr) {

        List<KNVP> list = new ArrayList<KNVP>();

        JCO.Client mConnection = null;
        JCO.Repository mRepository = null;
        JCO.Function function = null;
        try {
            mConnection = ConnectPoolManager.getConnectionInSAPPool();
            mConnection.connect();
            mRepository = new JCO.Repository("KJQD", mConnection);
            function = this.createFunction("ZMMT_GET_CUSTOMER_KNVP", mRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mConnection != null && mRepository != null && function != null) {
            try {
                // 前三个条件缺一不可
                // 1.销售组织
                function.getImportParameterList().setValue(vkorg.toUpperCase(), "ZVKORG");
                // 2.分销渠道
                function.getImportParameterList().setValue(vtweg.toUpperCase(), "ZVTWEG");
                // 3.产品组
                function.getImportParameterList().setValue(spart.toUpperCase(), "ZSPART");
                // 4.客户编码 不是必录条件
                // function.getImportParameterList().setValue("F1151012",
                // "ZKUNNR");
                if (kunnr != null && kunnr.length() > 0) {
                    function.getImportParameterList().setValue(kunnr.toUpperCase(), "ZKUNNR");
                }
                // 调用
                mConnection.execute(function);
                JCO.Table item1 = null;
                item1 = function.getTableParameterList().getTable("KNVP_LIST");
                for (int i = 0; i < item1.getNumRows(); i++) {
                    item1.setRow(i);
                    KNVP knvp = new KNVP();
                    knvp.setDEFPA(item1.getString("DEFPA"));
                    knvp.setKNREF(item1.getString("KNREF"));
                    knvp.setKUNN2(item1.getString("KUNN2"));
                    knvp.setKUNNR(item1.getString("KUNNR"));
                    knvp.setLIFNR(item1.getString("LIFNR"));
                    knvp.setMANDT(item1.getString("MANDT"));
                    knvp.setPARNR(item1.getString("PARNR"));
                    knvp.setPARVW(item1.getString("PARVW"));
                    knvp.setPARZA(item1.getString("PARZA"));
                    knvp.setPERNR(item1.getString("PERNR"));
                    knvp.setSPART(item1.getString("SPART"));
                    knvp.setVKORG(item1.getString("VKORG"));
                    knvp.setVTWEG(item1.getString("VTWEG"));
                    list.add(knvp);
                }
                // PARVW 字段是合作伙伴类型
                // SP 售达方
                // BP 收单方
                // PY 付款方
                // SH 送达方
            } catch (Exception ex) {
                logger.info(ex.getMessage());
            } finally {
                // mRepository.removeFunctionInterfaceFromCache("ZMMT_GET_CUSTOMER_KNVP");
                mRepository = null;
                if (mConnection != null) {
                    ConnectPoolManager.releaseClient(mConnection);
                }
            }
        }
        return list;
    }

    public static void main(String args[]) {
        Call_Get_Customer_Knvp02 call_Get_Customer_Knvp02 = new Call_Get_Customer_Knvp02();
        List<KNVP> list = new ArrayList<KNVP>();
        String vkorg = "KF47";
        String vtweg = "10";
        String spart = "10";
        String kunnr = "F1GM47";
        list = call_Get_Customer_Knvp02.doCall(vkorg, vtweg, spart, kunnr);
        // System.out.println(list.size());
        for (KNVP knvp : list) {
            System.out.println(knvp.getKUNN2() + "," + knvp.getKUNNR() + "," + knvp.getVKORG() + "," + knvp.getPARVW());
        }
    }
}
