package com.ebiz.mmt.r3;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;

/**
 * 集采
 * 
 * @author 李德宇
 * @author zhou
 * 
 *         转储单AIxxx-->交货单28号-->发货过账49号-->客户收货54号
 * 
 *         sap事务码:zles23转储单刷数据 me23N,查转储单
 * 
 *         目前zles23存在异常数据,如已交货,但客户编码丢失
 */
public class Call_Get_Zles23 {
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
     * @param erdat_b 转储单凭证时间
     * @param erdat_e
     * @param ZEBELN
     * @return
     */
    public List<ZLES23> doCall(String erdat_b, String erdat_e, String ZEBELN) {
        List<ZLES23> list = new ArrayList<ZLES23>();

        JCO.Client mConnection = null;
        JCO.Repository mRepository = null;
        JCO.Function function = null;
        try {
            mConnection = ConnectPoolManager.getConnectionInSAPPool();
            mConnection.connect();
            mRepository = new JCO.Repository("KJQD", mConnection);
            function = this.createFunction("ZMMT_GET_ZLES23", mRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mConnection != null && mRepository != null && function != null) {

            try {

                // 转储单编号
                if (ZEBELN != null && !"".equals(ZEBELN)) {
                    function.getImportParameterList().setValue(ZEBELN, "ZEBELN");
                }

                if (erdat_b != null && !"".equals(erdat_b)) {
                    function.getImportParameterList().setValue(erdat_b, "ERDAT_B");
                }
                if (erdat_e != null && !"".equals(erdat_e)) {
                    function.getImportParameterList().setValue(erdat_e, "ERDAT_E");
                }
                // 调用
                mConnection.execute(function);
                // function.writeHTML("d:/Call_Get_Zles23.html");
                JCO.Table item1 = null;
                item1 = function.getTableParameterList().getTable("Z_LIST");
                for (int i = 0; i < item1.getNumRows(); i++) {
                    item1.setRow(i);
                    ZLES23 zles23 = new ZLES23();
                    zles23.setBEDNR(item1.getString("BEDNR"));
                    zles23.setDATBG("");
                    zles23.setDATEN("");
                    zles23.setEBELN(item1.getString("EBELN"));

                    zles23.setFAHZTD(item1.getString("FAHZTD"));
                    zles23.setKUNNR(item1.getString("KUNNR"));
                    zles23.setLABST(item1.getString("LABST"));
                    zles23.setLABST1(item1.getString("LABST1"));
                    zles23.setLFIMG(item1.getString("LFIMG"));
                    zles23.setLFIMG1(item1.getString("LFIMG1"));
                    zles23.setLGORT(item1.getString("LGORT"));
                    zles23.setMATNR(item1.getString("MATNR"));
                    zles23.setMENGE(item1.getString("MENGE"));
                    zles23.setMENGE1(item1.getString("MENGE1"));
                    zles23.setMENGE2(item1.getString("MENGE2"));
                    zles23.setNAME1(item1.getString("NAME1"));
                    zles23.setPOSNR(item1.getString("POSNR"));
                    zles23.setRESLO(item1.getString("RESLO"));
                    zles23.setSIGNI(item1.getString("SIGNI"));
                    zles23.setTKNUM(item1.getString("TKNUM"));
                    zles23.setTPBEZ(item1.getString("TPBEZ"));
                    zles23.setVBELN(item1.getString("VBELN"));
                    zles23.setWAMNG(item1.getString("WAMNG"));
                    zles23.setWEMNG(item1.getString("WEMNG"));

                    zles23.setEBELP(item1.getString("EBELP"));

                    // 转储单凭证日期
                    if ("0000-00-00".equals(item1.getString("BEDAT"))) {
                        zles23.setBEDAT("");
                    } else {
                        zles23.setBEDAT(item1.getString("BEDAT"));
                    }
                    if ("0000-00-00".equals(item1.getString("BUDAT1"))) {
                        zles23.setBUDAT1("");
                    } else {
                        zles23.setBUDAT1(item1.getString("BUDAT1"));
                    }

                    if ("0000-00-00".equals(item1.getString("BUDAT2"))) {
                        zles23.setBUDAT2("");
                    } else {
                        zles23.setBUDAT2(item1.getString("BUDAT2"));
                    }

                    if ("0000-00-00".equals(item1.getString("ERDAT"))) {
                        zles23.setERDAT("");
                    } else {
                        zles23.setERDAT(item1.getString("ERDAT"));
                    }

                    if ("0000-00-00".equals(item1.getString("AEDAT"))) {
                        zles23.setAEDAT("");
                    } else {
                        zles23.setAEDAT(item1.getString("AEDAT"));
                    }

                    list.add(zles23);
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
        Call_Get_Zles23 call_Get_Zles23 = new Call_Get_Zles23();
        List<ZLES23> list = call_Get_Zles23.doCall("2015-01-01", "2015-05-01", "AI36504190");

        for (int i = 0; i < list.size(); i++) {

            // //System.out.println((i + 1) + ":" + list.get(i).getEBELN() + " " +
            // list.get(i).getPOSNR() + " "
            // + list.get(i).getERDAT() + " " + list.get(i).getBUDAT1() + " " +
            // list.get(i).getVBELN());

            // System.out.println((i + 1) + "  " + list.get(i).getEBELN() + " " +
            // list.get(i).getEBELP() + " " + list.get(i).getERDAT());
            // + list.get(i).getMENGE() + " " + list.get(i).getWAMNG() + " " +
            // list.get(i).getWEMNG() + " "
            // + list.get(i).getLFIMG() + " " + list.get(i).getLFIMG1() + "  " +
            // list.get(i).getAEDAT() + "  "
            // + list.get(i).getBEDAT()
            //

        }

    }
}
