package com.ebiz.mmt.r3;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ebiz.mmt.web.util.ArithUtil;
import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;

public class CALL_Get_SOXX {
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

    public List<SOXX> doCall(String v_vkorg, String v_vtweg, String v_spart, String v_audat_begin, String v_audat_end, String v_kunnr) {
        List<SOXX> list = new ArrayList<SOXX>();

        JCO.Client mConnection = null;
        JCO.Repository mRepository = null;
        JCO.Function function = null;
        try {
            mConnection = ConnectPoolManager.getConnectionInSAPPool();
            mConnection.connect();
            mRepository = new JCO.Repository("KJQD", mConnection);
            function = this.createFunction("ZMMT_GET_SOXX", mRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mConnection != null && mRepository != null && function != null) {

            try {

                // 设置要查询的销售组织
                // "KF47"; 1001
                if (v_vkorg != "") {
                    function.getImportParameterList().setValue(v_vkorg, "V_VKORG");
                }

                // 设置要查询的分销渠道
                // "10";
                if (v_vtweg != "") {
                    function.getImportParameterList().setValue(v_vtweg, "V_VTWEG");
                }
                // 设置要查询的产品组
                // "10";
                if (v_spart != "") {
                    function.getImportParameterList().setValue(v_spart, "V_SPART");
                }

                // ERDAT r3单据创建时间,不能修改
                // AUDAT r3单据日期(可前可后)
                // PRSDT r3定价日期
                // KETDAT 凭证请求交货日期
                // BSTDK 采购订单日期

                // 设置要查询的创建日期开始(R3现在接口变成订单创建日期了,以前是凭证日期)
                // "20080201";
                if (v_audat_begin != "") {
                    function.getImportParameterList().setValue(v_audat_begin, "V_AUDAT_BEGIN");
                }

                // 设置要查询的创建日期
                // "20080101";
                if (v_audat_end != "") {
                    function.getImportParameterList().setValue(v_audat_end, "V_AUDAT_END");
                }

                // 要查询的客户，是个可选参数，可以不传入
                // "F14703001";//
                if (v_kunnr != "") {
                    function.getImportParameterList().setValue(v_kunnr, "V_KUNNR");
                }


                // 调用
                mConnection.execute(function);
                // function.writeHTML("D:/call_get_soxx.html");

                JCO.Table item1 = null;
                item1 = function.getTableParameterList().getTable("ZVBALR_LIST");

                for (int i = 0; i < item1.getNumRows(); i++) {
                    item1.setRow(i);
                    SOXX soxx = new SOXX();
                    soxx.setAUART(item1.getString("AUART"));

                    soxx.setKUNNR(item1.getString("KUNNR"));

                    // 处理不同的单据,对数量的正负要求
                    if (item1.getString("AUART") != null && !"".equals(item1.getString("AUART"))) {
                        if (item1.getString("AUART").equalsIgnoreCase("ZFRE") || item1.getString("AUART").equalsIgnoreCase("ZFCR")
                                || item1.getString("AUART").equalsIgnoreCase("ZRE") || item1.getString("AUART").equalsIgnoreCase("ZECR")) {

                            // 订单数量
                            if (item1.getString("MWMENG") == null || "".equals(item1.getString("MWMENG"))) {
                                soxx.setKWMENG("0");
                            } else {
                                soxx.setKWMENG(ArithUtil.div(Double.valueOf(item1.getString("KWMENG")), -1f) + "");
                            }

                            // 交货单数量
                            if (item1.getString("MWMENG") == null || "".equals(item1.getString("MWMENG"))) {
                                soxx.setJWMENG("0");
                            } else {
                                soxx.setJWMENG(ArithUtil.div(Double.valueOf(item1.getString("JWMENG")), -1f) + "");
                            }

                            // 已发货数量
                            if (item1.getString("MWMENG") == null || "".equals(item1.getString("MWMENG"))) {
                                soxx.setMWMENG("0");
                            } else {
                                soxx.setMWMENG(ArithUtil.div(Double.valueOf(item1.getString("MWMENG")), -1f) + "");
                            }

                            // 已开发票数量
                            if (item1.getString("RWMENG") == null || "".equals(item1.getString("RWMENG"))) {
                                soxx.setRWMENG("0");
                            } else {
                                soxx.setRWMENG(ArithUtil.div(Double.valueOf(item1.getString("RWMENG")), -1f) + "");
                            }
                            // 总净额
                            if (item1.getString("KZWI1") == null || "".equals(item1.getString("KZWI1"))) {
                                soxx.setKZWI1("0");
                            } else {
                                soxx.setKZWI1(ArithUtil.div(Double.valueOf(item1.getString("KZWI1")), -1f) + "");
                            }
                            // 总额(含税)
                            if (item1.getString("KZWI6") == null || "".equals(item1.getString("KZWI6"))) {
                                soxx.setKZWI6("0");
                            } else {
                                soxx.setKZWI6(ArithUtil.div(Double.valueOf(item1.getString("KZWI6")), -1f) + "");
                            }

                        } else {
                            // 订单数量
                            if (item1.getString("KWMENG") == null || "".equals(item1.getString("KWMENG"))) {
                                soxx.setKWMENG("0");
                            } else {
                                soxx.setKWMENG(item1.getString("KWMENG"));
                            }

                            // 交货单数量
                            if (item1.getString("JWMENG") == null || "".equals(item1.getString("JWMENG"))) {
                                soxx.setJWMENG("0");
                            } else {
                                soxx.setJWMENG(item1.getString("JWMENG"));
                            }

                            // 已发货数量
                            if (item1.getString("MWMENG") == null || "".equals(item1.getString("MWMENG"))) {
                                soxx.setMWMENG("0");
                            } else {
                                soxx.setMWMENG(item1.getString("MWMENG"));
                            }

                            // 已开发票数量
                            if (item1.getString("RWMENG") == null || "".equals(item1.getString("RWMENG"))) {
                                soxx.setRWMENG("0");
                            } else {
                                soxx.setRWMENG(item1.getString("RWMENG"));
                            }
                            // 总净值金额(含税) 对
                            if (item1.getString("KZWI1") == null || "".equals(item1.getString("KZWI1"))) {
                                soxx.setKZWI1("0");
                            } else {
                                soxx.setKZWI1(item1.getString("KZWI1"));
                            }
                            // 总金额(含税)
                            if (item1.getString("KZWI6") == null || "".equals(item1.getString("KZWI6"))) {
                                soxx.setKZWI6("0");
                            } else {
                                soxx.setKZWI6(item1.getString("KZWI6"));
                            }
                        }
                    }

                    soxx.setMATNR(item1.getString("MATNR"));
                    soxx.setMATKL(item1.getString("MATKL"));
                    soxx.setMEINS(item1.getString("MEINS"));
                    soxx.setNETWR(item1.getString("NETWR"));
                    soxx.setPOSNR(item1.getString("POSNR"));
                    soxx.setSPART(item1.getString("SPART"));
                    soxx.setVBELN(item1.getString("VBELN"));
                    soxx.setVKBUR(item1.getString("VKBUR"));
                    // 创建日期(会计) N
                    if ("".equals(item1.getString("VDATU")) || "0000-00-00".equals(item1.getString("VDATU"))) {
                        soxx.setVDATU("");
                    } else {
                        soxx.setVDATU(item1.getString("VDATU"));
                    }
                    // 凭证日期(会计) Y
                    if ("".equals(item1.getString("AUDAT")) || "0000-00-00".equals(item1.getString("AUDAT"))) {
                        soxx.setAUDAT("");
                    } else {
                        soxx.setAUDAT(item1.getString("AUDAT"));
                    }
                    // 创建日期 Y
                    if ("".equals(item1.getString("ERDAT")) || "0000-00-00".equals(item1.getString("ERDAT"))) {
                        soxx.setERDAT("");
                    } else {
                        soxx.setERDAT(item1.getString("ERDAT"));
                    }
                    // 采购单日期Y 20131212 加
                    if ("".equals(item1.getString("BSTDK")) || "0000-00-00".equals(item1.getString("BSTDK"))) {
                        soxx.setBSTDK("");
                    } else {
                        soxx.setBSTDK(item1.getString("BSTDK"));
                    }
                    // 20131212 加
                    soxx.setBSTNK(item1.getString("BSTNK"));// 客户采购订单编码

                    soxx.setVKORG(item1.getString("VKORG"));// 销售组织 1001 kf234
                    soxx.setVTWEG(item1.getString("VTWEG"));// 产品组

                    soxx.setWEKUNNR(item1.getString("WEKUNNR"));// 送达方
                    soxx.setREKUNNR(item1.getString("REKUNNR"));// 出具发票方
                    soxx.setRGKUNNR(item1.getString("RGKUNNR"));// 付款方
                    soxx.setWERKS(item1.getString("WERKS"));// 工厂
                    soxx.setLGORT(item1.getString("LGORT"));// 出货仓位

                    // 净单价
                    if ("".equals(item1.getString("CMPRE")) || item1.getString("CMPRE") == null) {
                        soxx.setCMPRE("0");
                    } else {
                        soxx.setCMPRE(item1.getString("CMPRE"));
                    }

                    // 含税单价
                    if (Double.valueOf(soxx.getKWMENG()) != 0d) {
                        double d = ArithUtil.div(Double.valueOf(soxx.getKZWI6()), Double.valueOf(soxx.getKWMENG()));
                        soxx.setCMPRE0(d + "");
                    } else {
                        soxx.setCMPRE0("0");
                    }

                    if ("".equals(item1.getString("RB00")) || "".equals(item1.getString("RB00"))) {
                        soxx.setRB00("0");
                    } else {
                        soxx.setRB00(item1.getString("RB00"));
                    }
                    if (item1.getString("K007") == null || "".equals(item1.getString("K007"))) {
                        soxx.setK007("0");
                    } else {
                        soxx.setK007(item1.getString("K007"));
                    }

                    list.add(soxx);

                    //
                    // //System.out.println(item1.getString("KWMENG"));
                    // //System.out.println(item1.getString("JWMENG"));
                    // //System.out.println(item1.getString("MWMENG"));
                    // //System.out.println(item1.getString("RWMENG"));
                    // //System.out.println(item1.getString("NETWR"));
                    //

                    // //System.out.println(soxx.getBSTDK());
                    // //System.out.println(soxx.getBSTNK());

                    // //System.out.println(soxx.getCMPRE());
                    // //System.out.println(soxx.getCMPRE0());
                    // //System.out.println(soxx.getKZWI6());
                    // //System.out.println(soxx.getKZWI1());
                    // //System.out.println(soxx.getVKBUR());
                    // //System.out.println(item1.getString("AUDAT") + "," +
                    // item1.getString("ERDAT"));
                    // //System.out.println(item1.getString("WADAT_IST"));
                    // 订单数量
                    // //System.out.println(soxx.getKWMENG());
                    // 交货数量
                    // //System.out.println(soxx.getJWMENG());
                    // 已发货数量
                    // //System.out.println(soxx.getMWMENG());
                    // 已开发票数量
                    // //System.out.println(soxx.getRWMENG());

                    // 净单价
                    // //System.out.println(soxx.getCMPRE());
                    // 单价含税
                    // //System.out.println(soxx.getCMPRE0());

                    // 总净值金额(含税)
                    // //System.out.println(soxx.getKZWI1());
                    // 总金额(含税)
                    // //System.out.println(item1.getString("KZWI6"));

                    // //System.out.println(item1.getString("AUART"));
                    //
                    // //System.out.println(soxx.getMATKL());
                    // //System.out.println(soxx.getVTWEG());
                    // //System.out.println(soxx.getVKORG());
                    // //System.out.println(soxx.getLGORT());
                    // //System.out.println(soxx.getLGORT_L());
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

    // public static void main(String args[]) {
    // CALL_Get_SOXX call_Get_SOXX = new CALL_Get_SOXX();
    // call_Get_SOXX.doCall("KF47", "10", "10", "20060901", "20060901",
    // "F14703001");
    // call_Get_SOXX.doCall("1001", "10", "10", "20131101", "20131106", "");
    // call_Get_SOXX.doCall("1001", "10", "10", "20120210", "20120210",
    // "0001006203");
    // call_Get_SOXX.doCall("KF10", "10", "10", "20131011", "20131011",
    // "F110FYZSJD");
    // call_Get_SOXX.doCall("1001", "10", "10", "20140421", "20140522",
    // "0004300001");
    // 销售组织 不能为空
    // 分销渠道 不能为空
    // 产品组 不能为空
    // // 结束日期 不能为空,如果不填,默认为今天
    // }
}
