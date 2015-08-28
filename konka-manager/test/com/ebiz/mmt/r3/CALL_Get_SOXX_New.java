package com.ebiz.mmt.r3;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ebiz.mmt.web.util.ArithUtil;
import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;

public class CALL_Get_SOXX_New {
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
            function = this.createFunction("ZMMT_GET_SOXXNEW", mRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mConnection != null && mRepository != null && function != null) {

            try {
                // 设置要查询的销售组织
                if (v_vkorg != "") {
                    function.getImportParameterList().setValue(v_vkorg, "V_VKORG");
                }
                // 设置要查询的分销渠道
                if (v_vtweg != "") {
                    function.getImportParameterList().setValue(v_vtweg, "V_VTWEG");
                }
                // 设置要查询的产品组
                if (v_spart != "") {
                    function.getImportParameterList().setValue(v_spart, "V_SPART");
                }
                // 设置要查询的创建日期开始(现在接口变成订单创建日期了,以前是凭证日期)
                if (v_audat_begin != "") {
                    function.getImportParameterList().setValue(v_audat_begin, "V_AUDAT_BEGIN");
                }
                // 设置要查询的创建日期
                if (v_audat_end != "") {
                    function.getImportParameterList().setValue(v_audat_end, "V_AUDAT_END");
                }

                // 要查询的客户，是个可选参数，可以不传入
                if (v_kunnr != "") {
                    function.getImportParameterList().setValue(v_kunnr, "V_KUNNR");
                }

                // 调用
                mConnection.execute(function);
                JCO.Table item1 = null;
                item1 = function.getTableParameterList().getTable("ZVBALR_LIST");
                for (int i = 0; i < item1.getNumRows(); i++) {
                    // KUNNR KUNNR CHAR 10 0 客户编号1
                    // AUART AUART CHAR 4 0 销售凭证类型
                    // AUDAT AUDAT DATS 8 0 凭证日期 (接收/发送日期)
                    // VKORG VKORG CHAR 4 0 销售组织
                    // VTWEG VTWEG CHAR 2 0 分销渠道
                    // SPART SPART CHAR 2 0 产品组
                    // VDATU DATUM DATS 8 0 日期
                    // VBELN VBELN CHAR 10 0 销售单号
                    // POSNR POSNR NUMC 6 0 销售和分销凭证的项目号
                    // MATNR MATNR CHAR 18 0 物料号
                    // MATKL MATKL CHAR 9 0 物料组
                    // MEINS MEINS UNIT 3 0 基本计量单位
                    // NETWR NETWR CURR 15 2 凭证货币计量的净价值
                    // KWMENG KWMENG QUAN 15 3 以销售单位表示的累计订单数量
                    // WEKUNNR KUNNR CHAR 10 0 客户编号1 --送达方
                    // REKUNNR KUNNR CHAR 10 0 客户编号1 --出具发票方
                    // RGKUNNR KUNNR CHAR 10 0 客户编号1 --付款方
                    // WERKS WERKS_EXT CHAR 4 0 工厂(自有或外部) --销售单出货工厂
                    // LGORT LGORT_D CHAR 4 0 库存地点 --销售单出货仓位

                    // JWMENG; 交货单数量(可按销售单号直接查,已sum
                    // CALL_Get_SOXX才有,以后会弃用,新的给0因为有了KF交货单和物流单后,无法再进行统计)
                    // MWMENG; 已发货数量(可按销售单号直接查,已sum,CALL_Get_SOXX
                    // 才有,以后会弃用,新的给0因为有了KF交货单和物流单后,无法再进行统计)
                    // RWMENG; 开发票数量(可按销售单号直接查,已sum,CALL_Get_SOXX
                    // 才有,以后会弃用,新的给0,因为有了KF交货单和物流单后,无法再进行统计)

                    //
                    // KDMAT MATNR_KU CHAR 35 0 客户所用的物料编号
                    // BSTNK BSTNK CHAR 20 0 客户采购订单编号
                    // BSTDK BSTDK DATS 8 0 客户采购订单日期

                    // VBELN_L VBELN_VL CHAR 10 0 FK交货
                    // WADAT_IST WADAT_IST DATS 8 0 实际货物移动日期(交货日期)
                    // VBELN_LES VBELN_VL CHAR 10 0 物流交货
                    // POSNR_L POSNR_VL NUMC 6 0 交货项目
                    // PSTYV_L PSTYV_VL CHAR 4 0 交货项目类别
                    // WERKS_L WERKS_D CHAR 4 0 物流工厂
                    // LGORT_L LGORT_D CHAR 4 0 物流发货地点

                    // LFIMG_L LFIMG QUAN 13 3 实际已交货量（按销售单位,意思就是说已经按销售单号sum好了）

                    /**
                     * AG 售达方 RE 出具发票方 RG 付款方 WE 送达方
                     */

                    // KZWI1 总净值金额
                    // KWMENG 每一order,每一条订单行(某机型的数量)
                    // CMPRE 项目信贷价格
                    // NETWR 凭证货币计量的净价值

                    // 1. KZWI1 / KWMENG = CMPRE (单价净值)
                    // 2. KZWI6 / KWMENG = CMPRE0 单价含税(折扣)

                    item1.setRow(i);
                    SOXX soxx = new SOXX();
                    // 凭证日期(会计)
                    if ("".equals(item1.getString("AUDAT")) || "0000-00-00".equals(item1.getString("AUDAT"))) {
                        soxx.setAUDAT("");
                    } else {
                        soxx.setAUDAT(item1.getString("AUDAT"));
                    }

                    soxx.setKUNNR(item1.getString("KUNNR"));

                    // 处理不同的单据,对数量的正负要求
                    if (item1.getString("AUART") != null && !"".equals(item1.getString("AUART"))) {
                        if (item1.getString("AUART").equalsIgnoreCase("ZFRE") || item1.getString("AUART").equalsIgnoreCase("ZFCR")
                                || item1.getString("AUART").equalsIgnoreCase("ZRE") || item1.getString("AUART").equalsIgnoreCase("ZECR")) {

                            // 订单类型
                            soxx.setAUART(item1.getString("AUART"));
                            // 订单数量
                            if (item1.getString("KWMENG") == null || "".equals(item1.getString("KWMENG"))) {
                                soxx.setKWMENG("0");
                            } else {
                                soxx.setKWMENG(ArithUtil.div(Double.valueOf(item1.getString("KWMENG")), -1f) + "");
                            }
                            // 实际已交货量（可按销售单号直接查,已sum CALL_Get_SOXX_New才有）
                            if (item1.getString("LFIMG_L") == null || "".equals(item1.getString("LFIMG_L"))) {
                                soxx.setLFIMG_L("0");
                            } else {
                                soxx.setLFIMG_L(ArithUtil.div(Double.valueOf(item1.getString("LFIMG_L")), -1f) + "");
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

                            // 订单类型
                            soxx.setAUART(item1.getString("AUART"));
                            // 订单数量
                            if (item1.getString("KWMENG") == null || "".equals(item1.getString("KWMENG"))) {
                                soxx.setKWMENG("0");
                            } else {
                                soxx.setKWMENG(item1.getString("KWMENG"));
                            }
                            // 实际已交货量（可按销售单号直接查,已sum CALL_Get_SOXX_New才有）
                            if (item1.getString("LFIMG_L") == null || "".equals(item1.getString("LFIMG_L"))) {
                                soxx.setLFIMG_L("0");
                            } else {
                                soxx.setLFIMG_L(item1.getString("LFIMG_L"));
                            }
                            // 总净额
                            if (item1.getString("KZWI1") == null || "".equals(item1.getString("KZWI1"))) {
                                soxx.setKZWI1("0");
                            } else {
                                soxx.setKZWI1(item1.getString("KZWI1"));
                            }
                            // 总额(含税)
                            if (item1.getString("KZWI6") == null || "".equals(item1.getString("KZWI6"))) {
                                soxx.setKZWI6("0");
                            } else {
                                soxx.setKZWI6(item1.getString("KZWI6"));
                            }
                        }
                    }

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

                    // 净价和
                    if ("".equals(item1.getString("NETWR")) || item1.getString("NETWR") == null) {
                        soxx.setNETWR("0");
                    } else {
                        soxx.setNETWR(item1.getString("NETWR"));
                    }

                    // 交货单数量 0
                    if ("".equals(item1.getString("JWMENG")) || item1.getString("JWMENG") == null) {
                        soxx.setJWMENG("0");
                    } else {
                        soxx.setJWMENG(item1.getString("JWMENG"));
                    }
                    // 已发货数量 0
                    if ("".equals(item1.getString("MWMENG")) || item1.getString("MWMENG") == null) {
                        soxx.setMWMENG("0");
                    } else {
                        soxx.setMWMENG(item1.getString("MWMENG"));
                    }
                    // 已开发票数量 0
                    if ("".equals(item1.getString("RWMENG")) || item1.getString("RWMENG") == null) {
                        soxx.setRWMENG("0");
                    } else {
                        soxx.setRWMENG(item1.getString("RWMENG"));
                    }

                    soxx.setMATNR(item1.getString("MATNR"));// 机型编码
                    soxx.setMATKL(item1.getString("MATKL"));// 物料组
                    soxx.setMEINS(item1.getString("MEINS"));// 单位
                    soxx.setPOSNR(item1.getString("POSNR"));// 销售单的行号
                    soxx.setSPART(item1.getString("SPART"));// 产品组
                    soxx.setVBELN(item1.getString("VBELN"));// 销售单号
                    // 创建日期(会计)
                    if ("".equals(item1.getString("VDATU")) || "0000-00-00".equals(item1.getString("VDATU"))) {
                        soxx.setVDATU("");
                    } else {
                        soxx.setVDATU(item1.getString("VDATU"));
                    }

                    soxx.setVKORG(item1.getString("VKORG"));// 销售组织
                    soxx.setVTWEG(item1.getString("VTWEG")); // 分销渠道

                    soxx.setWEKUNNR(item1.getString("WEKUNNR"));// 送达方
                    soxx.setREKUNNR(item1.getString("REKUNNR"));// 出具发票方
                    soxx.setRGKUNNR(item1.getString("RGKUNNR"));// 付款方
                    soxx.setWERKS(item1.getString("WERKS"));// 工厂
                    soxx.setLGORT(item1.getString("LGORT"));// 出货仓位
                    soxx.setVKBUR(item1.getString("VKBUR"));// 办事处
                    // 订单日期
                    if ("".equals(item1.getString("ERDAT")) || "0000-00-00".equals(item1.getString("ERDAT"))) {
                        soxx.setERDAT("");
                    } else {
                        soxx.setERDAT(item1.getString("ERDAT"));
                    }

                    soxx.setKDMAT(item1.getString("KDMAT"));// 客户所用的物料编号
                    soxx.setBSTNK(item1.getString("BSTNK")); // 客户采购订单编号

                    // 客户采购订单日期
                    if ("".equals(item1.getString("BSTDK")) || "0000-00-00".equals(item1.getString("BSTDK"))) {
                        soxx.setBSTDK("");
                    } else {
                        soxx.setBSTDK(item1.getString("BSTDK"));
                    }

                    soxx.setVBELN_L(item1.getString("VBELN_L"));// FK交货单号
                    soxx.setVBELN_LES(item1.getString("VBELN_LES"));// 物流交货单号
                    // 实际货物移动日期(交货日期) Y
                    if ("".equals(item1.getString("WADAT_IST")) || "0000-00-00".equals(item1.getString("WADAT_IST"))) {
                        soxx.setWADAT_IST("");
                    } else {
                        soxx.setWADAT_IST(item1.getString("WADAT_IST"));
                    }

                    soxx.setPOSNR_L(item1.getString("POSNR_L"));// 交货项目(一个订单可能分多次来交货)
                    soxx.setPSTYV_L(item1.getString("PSTYV_L"));// 交货项目类别
                    soxx.setWERKS_L(item1.getString("WERKS_L"));// 工厂
                    soxx.setLGORT_L(item1.getString("LGORT_L"));// 库存地点

                    list.add(soxx);

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
        CALL_Get_SOXX_New call_Get_SOXX_New = new CALL_Get_SOXX_New();
        call_Get_SOXX_New.doCall("KF72", "10", "10", "20150401", "20150401", "F172SNYG");
    }
}
