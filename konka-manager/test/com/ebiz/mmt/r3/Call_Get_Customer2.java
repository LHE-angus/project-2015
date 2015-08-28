/*
 * Created on 2013-6-4
 * 
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code
 * Generation&gt;Code and Comments
 */
package com.ebiz.mmt.r3;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;

/**
 * 取R3客户
 */
public class Call_Get_Customer2 {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String ZVTWEG = "10";
    private final String ZSPART = "10";

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
     * @param erdat
     * @param bukrs
     * @param kunnr 由于r3系统不支持一次查询多个客户的合作伙伴信息,因此kunnr数组长度必须为1或0
     * @return
     */
    public List<KNA1> doCall(String erdat, String bukrs, String[] kunnr) {
        // 客户主数据
        List<KNA1> listKna1s = new ArrayList<KNA1>();
        // 分公司编码数据 分公司编码不一定是销售组织代码
        List<KNB1> lisKnb1s = new ArrayList<KNB1>();
        // 地址
        List<ADRC> listAdrc = new ArrayList<ADRC>();

        // 客户主数据 外部传参r3编码,要求结果出来指定的那几个r3编码.目前是外部只会传进来一个
        List<KNA1> returnKna1List = new ArrayList<KNA1>();

        // 合作伙伴
        List<KNVP> knvplist = new ArrayList<KNVP>();

        JCO.Function function = null;
        JCO.Function function2 = null;
        JCO.Client mConnection = null;
        JCO.Repository mRepository = null;

        try {
            mConnection = ConnectPoolManager.getConnectionInSAPPool();
            mConnection.connect();
            mRepository = new JCO.Repository("KJQD", mConnection);
            function = this.createFunction("ZMMT_GET_CUSTOMER", mRepository);
            function2 = this.createFunction("ZMMT_GET_CUSTOMER_KNVP", mRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mConnection != null && mRepository != null && function != null && function2 != null) {
            try {
                // 1.创建日期
                if (erdat != null && erdat.length() != 0) {
                    function.getImportParameterList().setValue(erdat, "ZERDAT");
                }
                // 2.此处是销售组织代码
                if (bukrs != null && bukrs.length() != 0) {
                    function.getImportParameterList().setValue(bukrs, "ZBUKRS");
                }

                // 已通过其它办法FIXED
                // 3.客户编码(此处接口有bug. 随时输入销售组织都能查出来值,所以使用时,必须保证输入的销售组织是正确的)
                // if (kunnr != null && kunnr.length > 0) {
                // JCO.Table table1 = null;
                // table1 = function.getTableParameterList().getTable("KNB1_LIST");
                // for (String s : kunnr) {
                // table1.appendRow();
                // table1.setValue(s, "KUNNR");
                // table1.setValue(bukrs, "BUKRS");
                // }
                // }

                // knvp接口
                // 2.销售组织代码
                if (bukrs != null && bukrs.length() > 0) {
                    // 1.销售组织
                    function2.getImportParameterList().setValue(bukrs.toUpperCase(), "ZVKORG");
                    // 2.分销渠道
                    function2.getImportParameterList().setValue(ZVTWEG, "ZVTWEG");// deadcode
                    // 3.产品组
                    function2.getImportParameterList().setValue(ZSPART, "ZSPART");// deadcode
                }

                // BUG !!!!!!!!!!!
                // there is a bug when kuunr.length >1
                //
                // 外部必须保证kunnr的长度为1个
                // fixed 2014/09/15 ,外部只传进来一个
                if (kunnr != null && kunnr.length == 1) {
                    // 查合作伙伴时,只能允许单独查一个
                    function2.getImportParameterList().setValue(kunnr[0], "ZKUNNR");
                }
                // 调用
                mConnection.execute(function);
                mConnection.execute(function2);

                // 输出返回信息
                JCO.Table item1 = null;
                item1 = function.getTableParameterList().getTable("KNA1_LIST");// 客户基本数据
                for (int i = 0; i < item1.getNumRows(); i++) {
                    item1.setRow(i);
                    KNA1 kna1 = new KNA1();
                    kna1.setKUNNR(item1.getString("KUNNR"));
                    kna1.setMANDT(item1.getString("MANDT"));
                    kna1.setADRNR(item1.getString("ADRNR"));
                    kna1.setBBBNR(item1.getString("BBBNR"));
                    kna1.setDEAR1(item1.getString("DEAR1"));
                    kna1.setDEAR2(item1.getString("DEAR2"));
                    kna1.setDEAR3(item1.getString("DEAR3"));
                    kna1.setDEAR4(item1.getString("DEAR4"));
                    kna1.setDEAR5(item1.getString("DEAR5"));
                    kna1.setFISKN(item1.getString("FISKN"));
                    kna1.setLAND1(item1.getString("LAND1"));
                    kna1.setNAME1(item1.getString("NAME1"));
                    kna1.setNAME2(item1.getString("NAME2"));
                    kna1.setPSTLZ(item1.getString("PSTLZ"));
                    kna1.setORT01(item1.getString("ORT01"));
                    kna1.setKUKLA(item1.getString("KUKLA"));
                    kna1.setKTOKD(item1.getString("KTOKD"));

                    if (item1.getString("LOEVM").equals("X") || item1.getString("LOEVM").equals("01")) {
                        kna1.setLOEVM("1");
                    } else {
                        kna1.setLOEVM("0");
                    }
                    if (item1.getString("CASSD").equals("X") || item1.getString("CASSD").equals("01")) {
                        kna1.setCASSD("1");
                    } else {
                        kna1.setCASSD("0");
                    }
                    if ("".equals(item1.getString("ERDAT")) || "0000-00-00".equals(item1.getString("ERDAT"))) {
                        kna1.setERDAT("");
                    } else {
                        kna1.setERDAT(item1.getString("ERDAT"));
                    }
                    listKna1s.add(kna1);
                }

                JCO.Table item2 = null;
                item2 = function.getTableParameterList().getTable("KNB1_LIST");// 分公司层面的数据表
                for (int i = 0; i < item2.getNumRows(); i++) {
                    item2.setRow(i);
                    KNB1 knb1 = new KNB1();
                    knb1.setKUNNR(item2.getString("KUNNR"));
                    knb1.setMANDT(item2.getString("MANDT"));
                    knb1.setBUKRS(item2.getString("BUKRS"));
                    knb1.setERDAT(item2.getString("ERDAT"));
                    if ("".equals(item2.getString("ERDAT")) || "0000-00-00".equals(item2.getString("ERDAT"))) {
                        knb1.setERDAT("");
                    } else {
                        knb1.setERDAT(item2.getString("ERDAT"));
                    }
                    knb1.setPERNR(item2.getString("PERNR"));
                    knb1.setERNAM(item2.getString("ERNAM"));
                    lisKnb1s.add(knb1);
                }

                JCO.Table item3 = null;
                item3 = function.getTableParameterList().getTable("ADRC_LIST"); // 地址信息
                for (int i = 0; i < item3.getNumRows(); i++) {
                    item3.setRow(i);
                    // STREET 是地址信息 ADDRNUMBER 对应 ADRNR
                    ADRC adrc = new ADRC();
                    adrc.setADDRNUMBER(item3.getString("ADDRNUMBER"));
                    adrc.setSTREET(item3.getString("STREET"));
                    adrc.setSTREETCODE(item3.getString("STREETCODE"));
                    adrc.setCITY_CODE(item3.getString("CITY_CODE"));
                    listAdrc.add(adrc);
                }

                JCO.Table item4 = null;
                item4 = function2.getTableParameterList().getTable("KNVP_LIST");

                for (int i = 0; i < item4.getNumRows(); i++) {
                    item4.setRow(i);
                    KNVP knvp = new KNVP();
                    knvp.setDEFPA(item4.getString("DEFPA"));
                    knvp.setKNREF(item4.getString("KNREF"));
                    knvp.setKUNN2(item4.getString("KUNN2"));
                    knvp.setKUNNR(item4.getString("KUNNR"));
                    knvp.setLIFNR(item4.getString("LIFNR"));
                    knvp.setMANDT(item4.getString("MANDT"));
                    knvp.setPARNR(item4.getString("PARNR"));
                    knvp.setPARVW(item4.getString("PARVW"));
                    knvp.setPARZA(item4.getString("PARZA"));
                    knvp.setPERNR(item4.getString("PERNR"));
                    knvp.setSPART(item4.getString("SPART"));
                    knvp.setVKORG(item4.getString("VKORG"));
                    knvp.setVTWEG(item4.getString("VTWEG"));
                    knvplist.add(knvp);
                }
                // 合并分公司代码,为每一个客户找到它的分公司
                List<KNA1> templistKna1s1 = listKna1s;
                for (KNA1 kna1 : templistKna1s1) {
                    for (KNB1 knb1 : lisKnb1s) {
                        if ((kna1.getKUNNR()).equals(knb1.getKUNNR())) {
                            kna1.setKnb1(knb1);
                            break;
                        }
                    }
                }

                // 为每一个客户配上地址信息
                for (KNA1 kna1 : listKna1s) {
                    for (ADRC adrc : listAdrc) {
                        if ((kna1.getADRNR()).equals(adrc.getADDRNUMBER())) {
                            kna1.setSTREET(adrc.getSTREET());
                            break;
                        }
                    }
                }

                // 过滤指定的客户,手工同步的时候,会传多个客户过来查询
                // 如果kunnr 不为空, 那么过滤后再返回结果
                if (kunnr != null && kunnr.length > 0) {
                    returnKna1List.clear();
                    for (String ku : kunnr) {
                        for (KNA1 kna : listKna1s) {
                            if (ku.equals(kna.getKUNNR())) {
                                returnKna1List.add(kna);
                            }
                        }
                    }
                    listKna1s.clear();
                    listKna1s = returnKna1List;
                } else {
                    // do nothing
                }

                // 这里需要非常的小心,如果如果销售组织和r3编码不匹配的话,会导致查到到错误的合作伙伴的数据,导致客户数据错误
                // 计算客户售达方,送达方关系
                for (KNA1 kk : listKna1s) {
                    for (KNVP kp : knvplist) {
                        if (kk.getKUNNR().equals(kp.getKUNNR())) {
                            // 在knvp中,客户号和客户合作伙伴号相同,并且为AG,那么判定它为售达方
                            if (kp.getKUNNR().equals(kp.getKUNN2()) && "AG".equals(kp.getPARVW().toUpperCase())) {
                                kk.setIS_AG(true);// 默认值是false
                                break;
                            }
                        }
                    }
                }

            } catch (Exception ex) {} finally {
                mRepository = null;
                if (mConnection != null) {
                    ConnectPoolManager.releaseClient(mConnection);
                }

            }
        }


        return listKna1s;
    }

    public static void main(String args[]) {
        Call_Get_Customer2 call_Get_Customer = new Call_Get_Customer2();
        List<KNA1> list = call_Get_Customer.doCall("2013-12-01", "KF31", new String[] {"F131CLQX"});
        System.out.println(list.size());
        for (KNA1 kna1 : list) {
            System.out.println(kna1.getKUNNR() + "," + kna1.getIS_AG());
        }

    }
}
