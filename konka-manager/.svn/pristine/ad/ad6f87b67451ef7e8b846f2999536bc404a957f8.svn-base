package com.ebiz.mmt.r3;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;

/**
 * 机型接口
 * 
 * @author zhou
 * 
 */
public class Call_Get_Mara_Makt {
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
     * 物料同步接口 数据较多,需要考虑传输问题
     * 
     * @param erdat 开始日期
     */
    public List<MARA> doCall(String erdat) {
        // 物料主表
        List<MARA> maraList = new ArrayList<MARA>();
        // 物料描述表
        List<MAKT> maktList = new ArrayList<MAKT>();

        JCO.Client mConnection = null;
        JCO.Repository mRepository = null;
        JCO.Function function = null;
        try {
            mConnection = ConnectPoolManager.getConnectionInSAPPool();
            mConnection.connect();
            mRepository = new JCO.Repository("KJQD", mConnection);
            function = this.createFunction("ZMMT_GET_MARA_MAKT", mRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (mConnection != null && mRepository != null && function != null) {

            try {
                if (erdat != null && erdat.length() != 0) {
                    // 创建日期参数
                    function.getImportParameterList().setValue(erdat, "ZERDAT");
                }
                // 调用
                mConnection.execute(function);
                JCO.Table item1 = null;
                item1 = function.getTableParameterList().getTable("ZMARA_LIST");// 物料主数据
                for (int i = 0; i < item1.getNumRows(); i++) {
                    item1.setRow(i);
                    MARA mara = new MARA();
                    mara.setMANDT(item1.getString("MANDT"));
                    mara.setMATNR(item1.getString("MATNR"));

                    if (item1.getString("ERSDA").equals("") || item1.getString("ERSDA").equals("0000-00-00")) {
                        mara.setERSDA("");
                    } else {
                        mara.setERSDA(item1.getString("ERSDA"));
                    }

                    mara.setERNAM(item1.getString("ERNAM"));

                    if (item1.getString("LAEDA").equals("") || item1.getString("LAEDA").equals("0000-00-00")) {
                        mara.setLAEDA("");
                    } else {
                        mara.setLAEDA(item1.getString("LAEDA"));
                    }

                    mara.setAENAM(item1.getString("AENAM"));
                    mara.setVPSTA(item1.getString("VPSTA"));
                    mara.setPSTAT(item1.getString("PSTAT"));
                    mara.setMTART(item1.getString("MTART"));
                    mara.setMATKL(item1.getString("MATKL"));
                    mara.setMEINS(item1.getString("MEINS"));
                    mara.setGROES(item1.getString("GROES"));
                    if (item1.getString("LAENG") == null || "".equals(item1.getString("LAENG"))) {
                        mara.setLAENG("0");
                    } else {
                        mara.setLAENG(item1.getString("LAENG"));
                    }
                    if (item1.getString("BREIT") == null || "".equals(item1.getString("BREIT"))) {
                        mara.setBREIT("0");
                    } else {
                        mara.setBREIT(item1.getString("BREIT"));
                    }
                    if (item1.getString("HOEHE") == null || "".equals(item1.getString("HOEHE"))) {
                        mara.setHOEHE("0");
                    } else {
                        mara.setHOEHE(item1.getString("HOEHE"));
                    }

                    mara.setSPART(item1.getString("SPART"));

                    maraList.add(mara);

                }
                JCO.Table item2 = null;
                item2 = function.getTableParameterList().getTable("ZMAKT_LIST");// 物料描述
                for (int i = 0; i < item2.getNumRows(); i++) {
                    item2.setRow(i);
                    MAKT makt = new MAKT();
                    makt.setMANDT(item2.getString("MANDT"));
                    makt.setMATNR(item2.getString("MATNR"));
                    makt.setSPRAS(item2.getString("SPRAS"));
                    makt.setMAKTG(item2.getString("MAKTG"));
                    makt.setMAKTX(item2.getString("MAKTX"));
                    maktList.add(makt);
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
        // 合并追加
        for (MARA mara : maraList) {
            for (MAKT makt : maktList) {
                if (mara.getMATNR().equals(makt.getMATNR())) {
                    mara.setMakt(makt);
                    continue;
                }
            }
        }
        return maraList;

    }

    public static void main(String args[]) {
        Call_Get_Mara_Makt call_Get_Mara_Makt = new Call_Get_Mara_Makt();

        List<MARA> maraList = call_Get_Mara_Makt.doCall("2006-01-01");
        int i = 0;
        int j = 0;
        int k = 0;
        int hh = 0;

        for (MARA mara : maraList) {
            if (mara.getSPART() == null) {
                hh++;
            }
            if (mara.getSPART().equals("10")) {
                i++;
            }
            if (mara.getSPART().equals("18")) {
                j++;
            }
            if (mara.getSPART().equals("28")) {
                k++;
            }
        }
        // System.out.println(i);
        // System.out.println(j);
        // System.out.println(k);
        // System.out.println(hh);

    }
}
