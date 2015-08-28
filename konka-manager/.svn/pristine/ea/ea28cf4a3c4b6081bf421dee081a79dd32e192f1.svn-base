package com.ebiz.mmt.r3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;

/**
 * 客户信贷
 * 
 * @author zhou
 * 
 */
public class Call_Get_KHXD {
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

    public List<KHXD> doCall(String v_kkber, String v_vkorg, String v_spart, String[] kunnr) {
        List<KHXD> list = new ArrayList<KHXD>();

        JCO.Client mConnection = null;
        JCO.Repository mRepository = null;
        JCO.Function function = null;
        try {
            mConnection = ConnectPoolManager.getConnectionInSAPPool();
            mConnection.connect();
            mRepository = new JCO.Repository("KJQD", mConnection);
            function = this.createFunction("ZMMT_GET_KHXD", mRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mConnection != null && mRepository != null && function != null) {
            try {
                // 信贷控制范围
                if (v_kkber != null && v_kkber.length() != 0) {
                    function.getImportParameterList().setValue(v_kkber.toUpperCase(), "V_KKBER");
                }
                // 产品组
                if (v_spart != null && v_spart.length() != 0) {
                    function.getImportParameterList().setValue(v_spart.toUpperCase(), "V_SPART");
                }
                // 销售组织
                if (v_vkorg != null && v_vkorg.length() != 0) {
                    function.getImportParameterList().setValue(v_vkorg.toUpperCase(), "V_VKORG");
                }
                // // 如果想查询固定的客户，也可以通过传入内表参数来查询
                // if (kunnr != null && kunnr.length() != 0) {
                // JCO.Table table1 = null;
                // table1 = function.getTableParameterList().getTable("ZKNA1_LIST");
                // table1.appendRow();
                // table1.setValue(kunnr.toUpperCase(), "KUNNR");
                // }
                if (kunnr != null && kunnr.length > 0) {
                    JCO.Table table1 = null;
                    table1 = function.getTableParameterList().getTable("ZKNA1_LIST");
                    for (String s : kunnr) {
                        table1.appendRow();
                        table1.setValue(s.toUpperCase(), "KUNNR");
                    }
                }

                
                // 调用
                mConnection.execute(function);

                // function.writeHTML("D:/Call_Get_KHXD.html");

                JCO.Table item1 = null;
                item1 = function.getTableParameterList().getTable("ZKNA1_XYGL_LIST");
                for (int i = 0; i < item1.getNumRows(); i++) {
                    item1.setRow(i);

                    // VKORG; // 销售组织
                    // KUNNR ; //客户编码
                    // CTLPC; // (CTLPC 风险类别)信用评估等级
                    // DBWAE; // 币种;
                    //
                    // KLIME;// (原始分配金额)信贷限额: 单个控制范围的限额
                    // KLIMG;// (当月分配金额)信贷限额: 所有控制区的总信贷限额
                    // DBEKR;// (信用账期额度)固定账期额度
                    // ZLIMT;// (总经理担保额度)临时信贷额度
                    // KLIMK;// (信贷限额)
                    //
                    // KLPRZ;// (使用的信贷限额)
                    // SKFOR;// (余额)
                    // NAME1; // 客户名称
                    // KUKLA;// 客户分类
                    // VTEXT;// 描述
                    //
                    // SAUFT ;//用于贷方限额检查的销售值总额
                    // OBLIG;// 信贷风险总额
                    // UMSAV ;//用本币计的结转余额
                    // ZSYED; // 剩余额度

                    // //System.out.println(item1.getString("KUNNR") + '\t'
                    // + item1.getString("NAME1") + '\t'
                    // + item1.getString("VKORG") + '\t'
                    // + item1.getString("CTLPC") + '\t'
                    // + item1.getString("DBWAE") + '\t'
                    // + item1.getString("DBEKR") + '\t'
                    // + item1.getString("ZLIMT") + '\t'
                    // + item1.getString("KLIMK") + '\t'
                    // + item1.getString("OBLIG") + '\t'
                    // + item1.getString("KLPRZ") + '\t'
                    // + item1.getString("SKFOR") + '\t'
                    // + item1.getString("SAUFT") + '\t'
                    // + item1.getString("UMSAV") + '\t'
                    // + item1.getString("KLIME") + '\t'
                    // + item1.getString("KLIMG") + '\t'
                    // + item1.getString("KUKLA") + '\t'
                    // + item1.getString("VTEXT") + '\t'
                    // + item1.getString("ZSYED") + '\t'
                    // + item1.getString("ZSYZT"));

                    KHXD khxd = new KHXD();
                    khxd.setKUNNR(item1.getString("KUNNR"));
                    khxd.setNAME1(item1.getString("NAME1"));
                    khxd.setVKORG(item1.getString("VKORG"));
                    khxd.setCTLPC(item1.getString("CTLPC"));
                    khxd.setDBWAE(item1.getString("DBWAE"));
                    khxd.setVTEXT(item1.getString("VTEXT"));
                    khxd.setKUKLA(item1.getString("KUKLA"));
                    khxd.setZSYZT(item1.getString("ZSYZT"));

                    // khxd.setDBEKR(new BigDecimal(item1.getString("DBEKR")));
                    // khxd.setZLIMT(new BigDecimal(item1.getString("ZLIMT")));
                    // khxd.setKLIMK(new BigDecimal(item1.getString("KLIMK")));
                    // khxd.setKLIME(new BigDecimal(item1.getString("KLIME")));
                    // khxd.setKLIMG(new BigDecimal(item1.getString("KLIMG")));
                    // khxd.setOBLIG(new BigDecimal(item1.getString("OBLIG")));
                    // khxd.setKLPRZ(new BigDecimal(item1.getString("KLPRZ")));
                    // khxd.setSKFOR(new BigDecimal(item1.getString("SKFOR")));
                    // khxd.setSAUFT(new BigDecimal(item1.getString("SAUFT")));
                    // khxd.setUMSAV(new BigDecimal(item1.getString("UMSAV")));
                    // khxd.setZSYED(new BigDecimal(item1.getString("ZSYED")));
                    //

                    if ("".equals(item1.getString("DBEKR")) || item1.getString("DBEKR") == null) {
                        khxd.setDBEKR(new BigDecimal("0"));
                    } else {
                        khxd.setDBEKR(new BigDecimal(item1.getString("DBEKR")));
                    }

                    if ("".equals(item1.getString("ZLIMT")) || item1.getString("ZLIMT") == null) {
                        khxd.setZLIMT(new BigDecimal("0"));
                    } else {
                        khxd.setZLIMT(new BigDecimal(item1.getString("ZLIMT")));
                    }

                    if ("".equals(item1.getString("KLIMK")) || item1.getString("KLIMK") == null) {
                        khxd.setKLIMK(new BigDecimal("0"));
                    } else {
                        khxd.setKLIMK(new BigDecimal(item1.getString("KLIMK")));
                    }

                    if ("".equals(item1.getString("KLIME")) || item1.getString("KLIME") == null) {
                        khxd.setKLIME(new BigDecimal("0"));
                    } else {
                        khxd.setKLIME(new BigDecimal(item1.getString("KLIME")));
                    }

                    if ("".equals(item1.getString("KLIMG")) || item1.getString("KLIMG") == null) {
                        khxd.setKLIMG(new BigDecimal("0"));
                    } else {
                        khxd.setKLIMG(new BigDecimal(item1.getString("KLIMG")));
                    }

                    if ("".equals(item1.getString("OBLIG")) || item1.getString("OBLIG") == null) {
                        khxd.setOBLIG(new BigDecimal("0"));
                    } else {
                        khxd.setOBLIG(new BigDecimal(item1.getString("OBLIG")));
                    }

                    if ("".equals(item1.getString("KLPRZ")) || item1.getString("KLPRZ") == null) {
                        khxd.setKLPRZ(new BigDecimal("0"));
                    } else {
                        khxd.setKLPRZ(new BigDecimal(item1.getString("KLPRZ")));
                    }

                    if ("".equals(item1.getString("SKFOR")) || item1.getString("SKFOR") == null) {
                        khxd.setSKFOR(new BigDecimal("0"));
                    } else {
                        khxd.setSKFOR(new BigDecimal(item1.getString("SKFOR")));
                    }

                    if ("".equals(item1.getString("SAUFT")) || item1.getString("SAUFT") == null) {
                        khxd.setSAUFT(new BigDecimal("0"));
                    } else {
                        khxd.setSAUFT(new BigDecimal(item1.getString("SAUFT")));
                    }

                    if ("".equals(item1.getString("UMSAV")) || item1.getString("UMSAV") == null) {
                        khxd.setUMSAV(new BigDecimal("0"));
                    } else {
                        khxd.setUMSAV(new BigDecimal(item1.getString("UMSAV")));
                    }

                    if ("".equals(item1.getString("ZSYED")) || item1.getString("ZSYED") == null) {
                        khxd.setZSYED(new BigDecimal("0"));
                    } else {
                        khxd.setZSYED(new BigDecimal(item1.getString("ZSYED")));
                    }
                    //
                    // 剩余额度= (信贷风险总额 >=0 ? 信贷限额-信贷风险总额 : 信贷限额)
                    if (khxd.getKLIMK() != null && khxd.getOBLIG() != null) {
                        if (khxd.getOBLIG().compareTo(new BigDecimal("0")) >= 0) {
                            khxd.setSYED(khxd.getKLIMK().subtract(khxd.getOBLIG()));
                        } else {
                            khxd.setSYED(khxd.getKLIMK());
                        }
                    } else {
                        khxd.setSYED(new BigDecimal("0"));
                    }

                    list.add(khxd);
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

        // 删除最后一行统计信息
        if (list.size() >= 1) {
            list.remove(list.size() - 1);
        }
        return list;
    }

    public static void main(String args[]) {
        Call_Get_KHXD call_Get_KHXD = new Call_Get_KHXD();

        String s[] =
                {"KF72", "KF71", "KF68", "KF67", "KF66", "KF62", "KF60", "KF55", "KF49", "KF47", "KF46", "KF42", "KF38", "KF37", "KF36", "KF35", "KF34", "KF33", "KF32", "KF31",
                        "KF30", "KF28", "KF27", "KF26", "KF25", "KF24", "KF23", "KF22", "KF21", "KF20", "KF18", "KF17", "KF16", "KF15", "KF13", "KF12", "KF11", "KF10", "KF09",
                        "KF08", "KF07", "KF06", "KF05", "F711", "F682", "F671", "F669", "F666", "F624", "F623", "F621", "F603", "F492", "F491"};

        // F1GM15
        // List<KHXD> list = call_Get_KHXD.doCall("KF01", "TC01", "10", new
        // String[] { "T107SYLT" });
        // //System.out.println(list.size() + "f");

        for (String x : s) {
            // F1GM15
            List<KHXD> list = call_Get_KHXD.doCall("KF01", x, "10", null);
            // System.out.println(list.size() + x);
        }

        // for (KHXD item1 : list) {
        // //System.out.println(item1.getOBLIG());
        // }
        // for (KHXD item1 : list) {
        // //System.out.println(item1.getKUNNR() + '\t' + item1.getKUKLA() +
        // '\t' + item1.getCTLPC() + '\t'
        // + item1.getDBWAE() + '\t' + item1.getNAME1() + '\t' +
        // item1.getVKORG() + '\t' + item1.getVTEXT()
        // + '\t' + item1.getDBEKR() + '\t' + item1.getKLIME() + '\t' +
        // item1.getKLIMG() + '\t'
        // + item1.getKLIMK() + '\t' + item1.getKLPRZ() + '\t' +
        // item1.getOBLIG() + '\t' + item1.getSAUFT()
        // + '\t' + item1.getSKFOR() + '\t' + item1.getUMSAV() + '\t' +
        // item1.getZLIMT() + '\t'
        // + item1.getZSYED() + '\t' + item1.getZSYZT());
        // }

        // BigDecimal b1 = new BigDecimal("1");
        // BigDecimal b2 = new BigDecimal("0");
        // //System.out.println(b1.compareTo(b2));
    }
}
