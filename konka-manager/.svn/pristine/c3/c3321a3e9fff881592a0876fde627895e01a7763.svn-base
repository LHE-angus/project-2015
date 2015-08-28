package com.ebiz.mmt.r3;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;


public class Call_Get_ZSOF {
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

    public ZSOF doCall(Long v_vbeln) {
        List<ZSOF> list = new ArrayList<ZSOF>();

        JCO.Client mConnection = null;
        JCO.Repository mRepository = null;
        JCO.Function function = null;
        try {
            mConnection = ConnectPoolManager.getConnectionInSAPPool();
            mConnection.connect();
            mRepository = new JCO.Repository("KJQD", mConnection);
            function = this.createFunction("ZMMT_GET_ZSOF", mRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mConnection != null && mRepository != null && function != null) {

            try {
                // 设置要查询订单
                if (v_vbeln != null) {
                    function.getImportParameterList().setValue(v_vbeln, "V_VBELN");
                } else {
                    return null;
                }

                // 调用
                mConnection.execute(function);
                JCO.Table item1 = null;
                item1 = function.getTableParameterList().getTable("GT_LIST");
                for (int i = 0; i < item1.getNumRows(); i++) {
                    item1.setRow(i);
                    ZSOF zsof = new ZSOF();
                    zsof.setERDAT(item1.getString("ERDAT"));
                    zsof.setGJAHR(item1.getString("GJAHR"));
                    zsof.setKUNNR(item1.getString("KUNNR"));
                    zsof.setLFDAT(item1.getString("LFDAT"));
                    zsof.setMONAT(item1.getString("MONAT"));
                    zsof.setNAME1(item1.getString("NAME1"));
                    zsof.setSHDAT(item1.getString("SHDAT"));
                    zsof.setVBEDL(item1.getString("VBEDL"));
                    zsof.setVBELN(Long.valueOf(item1.getString("VBELN") == null ? "0" : item1.getString("VBELN")));
                    zsof.setVKORG(item1.getString("VKORG"));
                    zsof.setVTEXT(item1.getString("VTEXT"));
                    list.add(zsof);
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
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public static void main(String args[]) {
        Call_Get_ZSOF call_Get_ZSOF = new Call_Get_ZSOF();
        ZSOF zsof = call_Get_ZSOF.doCall(614621063L);
        /*
         * for(int i=0;i<list.size();i++){ //System.out.println("  ");
         * //System.out.print(list.get(i).getEBELN()+" ");
         * //System.out.print(list.get(i).getMATNR()+" ");
         * //System.out.print(list.get(i).getMENGE()+" ");
         * //System.out.print(list.get(i).getLFIMG()+" ");
         * //System.out.print(list.get(i).getWAMNG()+" ");
         * //System.out.print(list.get(i).getWEMNG()+" ");
         * //System.out.print(list.get(i).getLABST()+" ");
         * //System.out.print(list.get(i).getLABST1()+" ");
         * //System.out.print(list.get(i).getRESLO()+" ");
         * //System.out.print(list.get(i).getLGORT()+" ");
         * //System.out.print(list.get(i).getKUNNR()+" ");
         * //System.out.print(list.get(i).getBEDNR()+" ");
         * //System.out.print(list.get(i).getVBELN()+" ");
         * //System.out.print(list.get(i).getPOSNR()+" ");
         * //System.out.print(list.get(i).getLFIMG1()+" ");
         * //System.out.print(list.get(i).getMENGE1()+" ");
         * //System.out.print(list.get(i).getERDAT()+" ");
         * //System.out.print(list.get(i).getBUDAT1()+" ");
         * //System.out.print(list.get(i).getMENGE2()+" ");
         * //System.out.print(list.get(i).getBUDAT2()+" ");
         * //System.out.print(list.get(i).getTKNUM()+" ");
         * //System.out.print(list.get(i).getDATBG()+" ");
         * //System.out.print(list.get(i).getDATEN()+" ");
         * //System.out.print(list.get(i).getFAHZTD()+" ");
         * //System.out.print(list.get(i).getNAME1()+" ");
         * //System.out.print(list.get(i).getSIGNI()+" ");
         * //System.out.print(list.get(i).getTPBEZ()+" "); }
         */
        // System.out.println(zsof.getMONAT());
        // System.out.println(zsof.getLFDAT());
        // System.out.println(zsof.getSHDAT());
        // System.out.println(zsof.getVBEDL());
    }
}
