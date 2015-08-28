package com.ebiz.mmt.r3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;


public class Call_Get_KHYS {
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

    public List<KHYS> doCall(String v_gjahr, String v_monat, String v_spart, String v_vkorg, String[] kunnr) {

        List<KHYS> list = new ArrayList<KHYS>();

        JCO.Client mConnection = null;
        JCO.Repository mRepository = null;
        JCO.Function function = null;
        try {
            mConnection = ConnectPoolManager.getConnectionInSAPPool();
            mConnection.connect();
            mRepository = new JCO.Repository("KJQD", mConnection);
            function = this.createFunction("ZMMT_GET_KHYS", mRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mConnection != null && mRepository != null && function != null) {
            try {
                // 要查询的会计年度
                // 年份
                if (v_gjahr != null && v_gjahr.length() != 0) {
                    // 信贷控制范围参数
                    function.getImportParameterList().setValue(v_gjahr.toUpperCase(), "V_GJAHR");
                }
                // 要查询的会计期间
                // 月份01
                if (v_monat != null && v_monat.length() != 0) {
                    function.getImportParameterList().setValue(v_monat.toUpperCase(), "V_MONAT");
                }

                // 设置要查询的产品组10
                if (v_spart != null && v_spart.length() != 0) {
                    function.getImportParameterList().setValue(v_spart.toUpperCase(), "V_SPART");
                }

                // 设置要查询的销售组织KF15
                if (v_vkorg != null && v_vkorg.length() != 0) {
                    function.getImportParameterList().setValue(v_vkorg.toUpperCase(), "V_VKORG");
                }

                // 如果查询固定的客户，也可以通过传入内表参数来查询
                // if(kunnr !=null && kunnr.length()!=0){
                // JCO.Table table1 = null;
                // table1 = function.getTableParameterList().getTable("ZKNA1_LIST");
                // table1.appendRow();
                // table1.setValue(kunnr.toUpperCase(), "KUNNR");//F1GM15
                // }
                //
                if (kunnr != null && kunnr.length > 0) {
                    JCO.Table table1 = null;
                    table1 = function.getTableParameterList().getTable("ZKNA1_LIST");
                    for (String s : kunnr) {
                        table1.appendRow();
                        table1.setValue(s.toUpperCase(), "KUNNR");// F1GM15
                    }

                }
                // 调用
                mConnection.execute(function);
                // function.writeHTML("D:/Call_Get_KHYS.html");

                // //System.out.println("客户应收信息 begin");
                JCO.Table item1 = null;
                item1 = function.getTableParameterList().getTable("ZKNA1_KHYS_LIST");
                for (int i = 0; i < item1.getNumRows(); i++) {
                    item1.setRow(i);
                    // KUNNR KUNNR CHAR 10 0 客户编号1
                    // VKORG VKORG CHAR 4 0 销售组织
                    // KUKLA KUKLA CHAR 2 0 客户分类
                    // VKBUR VKBUR CHAR 4 0 销售部门
                    // GJAHR GJAHR NUMC 4 0 会计年度
                    // MONAT MONAT NUMC 2 0 会计期间
                    // NAME1 NAME1 CHAR 30 0 名称
                    // BUTXT BUTXT CHAR 25 0 公司代码或公司的名称
                    // VTEXT VTEXT CHAR 20 0 描述
                    // BEZEI BEZEI CHAR 25 0 控制范围名称
                    // ZQC UMSAV CURR 15 2 期初余额
                    // ZJF UMSAV CURR 15 2 应收
                    // ZDF UMSAV CURR 15 2 回款
                    // ZYE UMSAV CURR 15 2 期末余额
                    //

                    // //System.out.println(item1.getString("KUNNR") + '\t'
                    // + item1.getString("VKORG") + '\t'
                    // + item1.getString("KUKLA") + '\t'
                    // + item1.getString("VKBUR") + '\t'
                    // + item1.getString("GJAHR") + '\t'
                    // + item1.getString("MONAT") + '\t'
                    // + item1.getString("NAME1") + '\t'
                    // + item1.getString("BUTXT") + '\t'
                    // + item1.getString("VTEXT") + '\t'
                    // + item1.getString("BEZEI") + '\t'
                    // + item1.getString("ZQC") + '\t'
                    // + item1.getString("ZJF") + '\t'
                    // + item1.getString("ZDF") + '\t'
                    // + item1.getString("ZYE") + '\t');

                    KHYS khys = new KHYS();
                    khys.setBEZEI(item1.getString("BEZEI"));
                    khys.setBUTXT(item1.getString("BUTXT"));
                    khys.setKUNNR(item1.getString("KUNNR"));
                    khys.setKUKLA(item1.getString("KUKLA"));
                    khys.setVKORG(item1.getString("VKORG"));
                    khys.setVKBUR(item1.getString("VKBUR"));
                    khys.setNAME1(item1.getString("NAME1"));
                    khys.setVTEXT(item1.getString("VTEXT"));

                    khys.setGJAHR(Integer.valueOf(item1.getString("GJAHR")));
                    khys.setMONAT(Integer.valueOf(item1.getString("MONAT")));

                    khys.setZDF(new BigDecimal(item1.getString("ZDF")));
                    khys.setZJF(new BigDecimal(item1.getString("ZJF")));
                    khys.setZQC(new BigDecimal(item1.getString("ZQC")));
                    khys.setZYE(new BigDecimal(item1.getString("ZYE")));
                    list.add(khys);
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
        Call_Get_KHYS call_Get_KHYS = new Call_Get_KHYS();
        call_Get_KHYS.doCall("2013", "4", "10", "KF18", new String[] {"F118TJHL"});
        // 年度不能为空
        // 月份不能为空
        // 产品组不能空

        // 销售组织和客户编码不能同时为空

    }
}
