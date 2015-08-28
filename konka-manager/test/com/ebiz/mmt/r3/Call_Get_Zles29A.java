package com.ebiz.mmt.r3;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;
import com.sap.mw.jco.JCO.Table;

/**
 * 分公司调拨计划评估
 * 
 * @author ZHOU
 * 
 */
public class Call_Get_Zles29A {
    ConnectPoolManager connectPoolManager;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private JCO.Function createFunction(String name, JCO.Repository mRepository) throws Exception {
        try {
            IFunctionTemplate ft = mRepository.getFunctionTemplate(name.toUpperCase());
            if (ft == null) return null;
            return ft.getFunction();
        } catch (Exception ex) {
            throw new Exception("Problem retrieving JCO.Function object.");
        }
    }

    public List<Zles29a> doCall(Zles29aCriteria zc) {
        List<Zles29a> list = new ArrayList<Zles29a>();

        JCO.Client mConnection = null;
        JCO.Repository mRepository = null;
        JCO.Function function = null;
        try {
            mConnection = ConnectPoolManager.getConnectionInSAPPool();
            mConnection.connect();
            mRepository = new JCO.Repository("KJQD", mConnection);
            function = this.createFunction("Z_RFC_ZLES29A", mRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mConnection != null && mRepository != null && function != null) {

            try {
                // 2.buildCriteria
                buildCriteria(function, zc);

                // 3.call function
                mConnection.execute(function);

                // 4. log
                // function.writeHTML("D:/Call_Get_Zles29A.html");

                // 5. result
                Table item1 = null;
                item1 = function.getTableParameterList().getTable("IT_TOTLE1");

                for (int i = 0; i < item1.getNumRows(); i++) {
                    item1.setRow(i);

                    // BZIRK BZIRK CHAR 6 0 销售地区
                    // CLASS CHAR 12 0 分公司
                    // CLASS2 CHAR 12 0 经营部
                    // KUNNR KUNNR CHAR 10 0 客户编号1
                    // MATNR MATNR CHAR 18 0 物料号
                    // MATKL MATKL CHAR 9 0 物料组
                    // LFIMG INT4 10 0 上月销量
                    // LFIMG1 INT4 10 0 本月销量
                    // SUM INT4 10 0 总量
                    // M_ZT INT4 10 0 在途数量
                    // M_WF INT4 10 0 未发数量
                    // PDLABST INT4 10 0 铺底仓数量
                    // ZZLABST INT4 10 0 周转仓数量
                    // JYLABST INT4 10 0 经营部仓数量
                    // CLLABST INT4 10 0 处理机仓数量
                    // YJLABST INT4 10 0 样机仓数量
                    // TJLABST INT4 10 0 待退基地仓数量
                    // PCLABST INT4 10 0 P仓数量
                    // ZCLABST INT4 10 0 Z仓数量
                    // BCLABST INT4 10 0 B仓数量
                    // DZLABST INT4 10 0 电子商务仓数量
                    // COLOR CHAR 4 0 颜色

                    Zles29a zles29a = new Zles29a();
                    zles29a.setBZIRK(item1.getString("BZIRK"));
                    zles29a.setCLASS(item1.getString("CLASS"));
                    zles29a.setCLASS2(item1.getString("CLASS2"));
                    zles29a.setKUNNR(item1.getString("KUNNR"));
                    zles29a.setMATNR(item1.getString("MATNR"));
                    zles29a.setMATKL(item1.getString("MATKL"));
                    zles29a.setLFIMG(item1.getString("LFIMG"));
                    zles29a.setLFIMG1(item1.getString("LFIMG1"));
                    zles29a.setSUM(item1.getString("SUM"));
                    zles29a.setMZT(item1.getString("M_ZT"));
                    zles29a.setMWF(item1.getString("M_WF"));
                    zles29a.setPDLABST(item1.getString("PDLABST"));
                    zles29a.setZZLABST(item1.getString("ZZLABST"));
                    zles29a.setJYLABST(item1.getString("JYLABST"));
                    zles29a.setCLLABST(item1.getString("CLLABST"));
                    zles29a.setYJLABST(item1.getString("YJLABST"));
                    zles29a.setTJLABST(item1.getString("TJLABST"));
                    zles29a.setPCLABST(item1.getString("PCLABST"));
                    zles29a.setZCLABST(item1.getString("ZCLABST"));
                    zles29a.setBCLABST(item1.getString("BCLABST"));
                    zles29a.setDZLABST(item1.getString("DZLABST"));

                    list.add(zles29a);

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

    private void buildCriteria(JCO.Function function, Zles29aCriteria zc) {

        // 1销售订单日期
        // 1.1 只使用BT,时间范围
        Table v_bstdk = null;
        v_bstdk = function.getTableParameterList().getTable("V_BSTDK");

        if ((zc.getV_BSTDK().get("LOW") != null && !"".equals(zc.getV_BSTDK().get("LOW"))) || (zc.getV_BSTDK().get("HIGH") != null && !"".equals(zc.getV_BSTDK().get("HIGH")))) {
            v_bstdk.appendRow();
            v_bstdk.setValue("I", "SIGN");
            v_bstdk.setValue("BT", "OPTION");

            if (zc.getV_BSTDK().get("LOW") != null && !"".equals(zc.getV_BSTDK().get("LOW"))) {
                v_bstdk.setValue(zc.getV_BSTDK().get("LOW"), "LOW");
            }
            if (zc.getV_BSTDK().get("HIGH") != null && !"".equals(zc.getV_BSTDK().get("HIGH"))) {
                v_bstdk.setValue(zc.getV_BSTDK().get("HIGH"), "HIGH");
            }
        }

        // 2.销售片区代码 000001,100001~100011
        // 2.1多个单值暂时不支持,只支持单个单值
        // 2.2范围(low,high都要有,且不能同时带*)
        // 2.3模糊查询(不能写到high,只能放到low)
        Table v_bzirk = null;
        v_bzirk = function.getTableParameterList().getTable("V_BZIRK");

        if ((zc.getV_BZIRK().get("LOW") != null && !"".equals(zc.getV_BZIRK().get("LOW"))) || (zc.getV_BZIRK().get("HIGH") != null && !"".equals(zc.getV_BZIRK().get("HIGH")))) {

            // 单个单值
            if (zc.getV_BZIRK().get("LOW") != null && !"".equals(zc.getV_BZIRK().get("LOW"))) {
                v_bzirk.appendRow();
                v_bzirk.setValue("I", "SIGN");
                v_bzirk.setValue("EQ", "OPTION");
                v_bzirk.setValue(zc.getV_BZIRK().get("LOW"), "LOW");
            }

            // 范围:
            v_bzirk.appendRow();
            v_bzirk.setValue("I", "SIGN");
            v_bzirk.setValue("BT", "OPTION");
            if (zc.getV_BZIRK().get("LOW") != null && !"".equals(zc.getV_BZIRK().get("LOW"))) {
                v_bzirk.setValue(zc.getV_BZIRK().get("LOW"), "LOW");
            }
            if (zc.getV_BZIRK().get("HIGH") != null && !"".equals(zc.getV_BZIRK().get("HIGH"))) {
                v_bzirk.setValue(zc.getV_BZIRK().get("HIGH"), "HIGH");
            }

            // 模糊查询
            if (zc.getV_BZIRK().get("LOW") != null && !"".equals(zc.getV_BZIRK().get("LOW"))) {
                v_bzirk.appendRow();
                v_bzirk.setValue("I", "SIGN");
                v_bzirk.setValue("CP", "OPTION");
                v_bzirk.setValue(zc.getV_BZIRK().get("LOW"), "LOW");
            }
        }


        // 多个单值 暂时不支持
        // v_bzirk.appendRow();
        // v_bzirk.setValue("I", "SIGN");
        // v_bzirk.setValue("EQ", "OPTION");
        // v_bzirk.setValue("", "LOW");
        //
        // v_bzirk.appendRow();
        // v_bzirk.setValue("I", "SIGN");
        // v_bzirk.setValue("EQ", "OPTION");
        // v_bzirk.setValue("", "LOW");

        // 3 分公司名称
        // 3.1多个单值暂时不支持,只支持单个单值
        // 3.2范围(low,high都要有,且不能同时带*),
        // 3.3模糊查询(不能写到high,只能放到low)
        Table v_class = null;
        v_class = function.getTableParameterList().getTable("V_CLASS");

        if ((zc.getV_CLASS().get("LOW") != null && !"".equals(zc.getV_CLASS().get("LOW"))) || (zc.getV_CLASS().get("HIGH") != null && !"".equals(zc.getV_CLASS().get("HIGH")))) {

            // 单个单值
            if (zc.getV_CLASS().get("LOW") != null && !"".equals(zc.getV_CLASS().get("LOW"))) {
                v_class.appendRow();
                v_class.setValue("I", "SIGN");
                v_class.setValue("EQ", "OPTION");
                v_class.setValue(zc.getV_CLASS().get("LOW"), "LOW");
            }

            // 范围:按名称的首字母升序
            v_class.appendRow();
            v_class.setValue("I", "SIGN");
            v_class.setValue("BT", "OPTION");

            if (zc.getV_CLASS().get("LOW") != null && !"".equals(zc.getV_CLASS().get("LOW"))) {
                v_class.setValue(zc.getV_CLASS().get("LOW"), "LOW");
            }
            if (zc.getV_CLASS().get("HIGH") != null && !"".equals(zc.getV_CLASS().get("HIGH"))) {
                v_class.setValue(zc.getV_CLASS().get("HIGH"), "HIGH");
            }

            // 模糊查询
            if (zc.getV_CLASS().get("LOW") != null && !"".equals(zc.getV_CLASS().get("LOW"))) {
                v_class.appendRow();
                v_class.setValue("I", "SIGN");
                v_class.setValue("CP", "OPTION");
                v_class.setValue(zc.getV_CLASS().get("LOW"), "LOW");
            }
            // 多个单值 暂时不支持
            // v_class.appendRow();
            // v_class.setValue("I", "SIGN");
            // v_class.setValue("EQ", "OPTION");
            // v_class.setValue("北京分公司", "LOW");
            //
            // v_class.appendRow();
            // v_class.setValue("I", "SIGN");
            // v_class.setValue("EQ", "OPTION");
            // v_class.setValue("长沙分公司", "LOW");
        }


        // 4转储订单号 UZ00-00000 ~UZ99-99999(没有生效)
        // 4.1多个单值暂时不支持,只支持单个单值
        // 4.2范围(low,high都要有,且不能同时带*)
        Table v_ebeln = null;
        v_ebeln = function.getTableParameterList().getTable("V_EBELN");
        if ((zc.getV_EBELN().get("LOW") != null && !"".equals(zc.getV_EBELN().get("LOW"))) || (zc.getV_EBELN().get("HIGH") != null && !"".equals(zc.getV_EBELN().get("HIGH")))) {


            // 单个单值
            if (zc.getV_EBELN().get("LOW") != null && !"".equals(zc.getV_EBELN().get("LOW"))) {
                v_ebeln.appendRow();
                v_ebeln.setValue("I", "SIGN");
                v_ebeln.setValue("EQ", "OPTION");
                v_ebeln.setValue(zc.getV_EBELN().get("LOW"), "LOW");
            }
            // 范围:
            v_ebeln.appendRow();
            v_ebeln.setValue("I", "SIGN");
            v_ebeln.setValue("BT", "OPTION");
            if (zc.getV_EBELN().get("LOW") != null && !"".equals(zc.getV_EBELN().get("LOW"))) {
                v_ebeln.setValue(zc.getV_EBELN().get("LOW"), "LOW");
            }
            if (zc.getV_EBELN().get("HIGH") != null && !"".equals(zc.getV_EBELN().get("HIGH"))) {
                v_ebeln.setValue(zc.getV_EBELN().get("HIGH"), "HIGH");
            }
            // 多个单值 暂时不支持
            // v_ebeln.appendRow();
            // v_ebeln.setValue("I", "SIGN");
            // v_ebeln.setValue("EQ", "OPTION");
            // v_ebeln.setValue("UZ00-00000", "LOW");
            //
            // v_ebeln.appendRow();
            // v_ebeln.setValue("I", "SIGN");
            // v_ebeln.setValue("EQ", "OPTION");
            // v_ebeln.setValue("UZ00-99999", "LOW");
        }


        // 5物料号
        // 3.1多个单值暂时不支持,只支持单个单值
        // 3.2范围(low,high都要有,且不能同时带*),
        // 3.3模糊查询(不能写到high,只能放到low)
        Table v_matnr = null;
        v_matnr = function.getTableParameterList().getTable("V_MATNR");
        if ((zc.getV_MATNR().get("LOW") != null && !"".equals(zc.getV_MATNR().get("LOW"))) || (zc.getV_MATNR().get("HIGH") != null && !"".equals(zc.getV_MATNR().get("HIGH")))) {

            if (zc.getV_MATNR().get("LOW") != null && !"".equals(zc.getV_MATNR().get("LOW"))) {
                // 单个单值
                v_matnr.appendRow();
                v_matnr.setValue("I", "SIGN");
                v_matnr.setValue("EQ", "OPTION");
                v_matnr.setValue(zc.getV_MATNR().get("LOW"), "LOW");
            }

            // 范围:按名称的首字母升序
            v_matnr.appendRow();
            v_matnr.setValue("I", "SIGN");
            v_matnr.setValue("BT", "OPTION");
            if (zc.getV_MATNR().get("LOW") != null && !"".equals(zc.getV_MATNR().get("LOW"))) {
                v_matnr.setValue(zc.getV_MATNR().get("LOW"), "LOW");
            }
            if (zc.getV_MATNR().get("HIGH") != null && !"".equals(zc.getV_MATNR().get("HIGH"))) {
                v_matnr.setValue(zc.getV_MATNR().get("HIGH"), "HIGH");
            }


            if (zc.getV_MATNR().get("LOW") != null && !"".equals(zc.getV_MATNR().get("LOW"))) {
                // 模糊查询
                v_matnr.appendRow();
                v_matnr.setValue("I", "SIGN");
                v_matnr.setValue("CP", "OPTION");
                v_matnr.setValue(zc.getV_MATNR().get("LOW"), "LOW");

                // 模糊查询
                // v_matnr.appendRow();
                // v_matnr.setValue("I", "SIGN");
                // v_matnr.setValue("CP", "OPTION");
                // v_matnr.setValue("LED55E52AD", "LOW");

            }

            // 多个单值 暂时不支持
            // v_matnr.appendRow();
            // v_matnr.setValue("I", "SIGN");
            // v_matnr.setValue("EQ", "OPTION");
            // v_matnr.setValue("LED55X8100PDE", "LOW");
            //
            // v_matnr.appendRow();
            // v_matnr.setValue("I", "SIGN");
            // v_matnr.setValue("EQ", "OPTION");
            // v_matnr.setValue("LED55X81xxxxE", "LOW");

        }

        // 6.物料组
        Table v_matkl = null;
        v_matkl = function.getTableParameterList().getTable("V_MATKL");

        if ((zc.getV_MATKL().get("LOW") != null && !"".equals(zc.getV_MATKL().get("LOW"))) || (zc.getV_MATKL().get("HIGH") != null && !"".equals(zc.getV_MATKL().get("HIGH")))) {

            if (zc.getV_MATKL().get("LOW") != null && !"".equals(zc.getV_MATKL().get("LOW"))) {
                // 单个值
                v_matkl.appendRow();
                v_matkl.setValue("I", "SIGN");
                v_matkl.setValue("EQ", "OPTION");
                v_matkl.setValue(zc.getV_MATKL().get("LOW"), "LOW");
            }

            // 范围
            v_matkl.appendRow();
            v_matkl.setValue("I", "SIGN");
            v_matkl.setValue("BT", "OPTION");
            if (zc.getV_MATKL().get("LOW") != null && !"".equals(zc.getV_MATKL().get("LOW"))) {
                v_matkl.setValue(zc.getV_MATKL().get("LOW"), "LOW");
            }

            if (zc.getV_MATKL().get("HIGH") != null && !"".equals(zc.getV_MATKL().get("HIGH"))) {
                v_matkl.setValue(zc.getV_MATKL().get("HIGH"), "HIGH");
            }

            if (zc.getV_MATKL().get("LOW") != null && !"".equals(zc.getV_MATKL().get("LOW"))) {
                // 模糊查询
                v_matkl.appendRow();
                v_matkl.setValue("I", "SIGN");
                v_matkl.setValue("CP", "OPTION");
                v_matkl.setValue(zc.getV_MATKL().get("LOW"), "LOW");
            }

            // 多个单值暂时不支持
            // v_matkl.appendRow();
            // v_matkl.setValue("I", "SIGN");
            // v_matkl.setValue("EQ", "OPTION");
            // v_matkl.setValue("001", "LOW");
            //
            // v_matkl.appendRow();
            // v_matkl.setValue("I", "SIGN");
            // v_matkl.setValue("EQ", "OPTION");
            // v_matkl.setValue("003", "LOW");
        }

        // 7.转储订单日期
        // 7.1 只使用BT,时间范围
        Table v_eindt = null;
        v_eindt = function.getTableParameterList().getTable("V_EINDT");
        if ((zc.getV_EINDT().get("LOW") != null && !"".equals(zc.getV_EINDT().get("LOW"))) || (zc.getV_EINDT().get("HIGH") != null && !"".equals(zc.getV_EINDT().get("HIGH")))) {
            v_eindt.appendRow();
            v_eindt.setValue("I", "SIGN");
            v_eindt.setValue("BT", "OPTION");
            if (zc.getV_EINDT().get("LOW") != null && !"".equals(zc.getV_EINDT().get("LOW"))) {
                v_eindt.setValue(zc.getV_EINDT().get("LOW"), "LOW");
            }
            if (zc.getV_EINDT().get("HIGH") != null && !"".equals(zc.getV_EINDT().get("HIGH"))) {
                v_eindt.setValue(zc.getV_EINDT().get("HIGH"), "HIGH");
            }
        }
        // 8 分销渠道 [10,11,20,30,31,35,40]
        // 8.1 使用BT
        Table v_vtweg = null;
        v_vtweg = function.getTableParameterList().getTable("V_VTWEG");
        if ((zc.getV_VTWEG().get("LOW") != null && !"".equals(zc.getV_VTWEG().get("LOW"))) || (zc.getV_VTWEG().get("HIGH") != null && !"".equals(zc.getV_VTWEG().get("HIGH")))) {

            v_vtweg.appendRow();
            v_vtweg.setValue("I", "SIGN");

            if (zc.getV_VTWEG().get("HIGH") != null && !"".equals(zc.getV_VTWEG().get("HIGH"))) {
                v_vtweg.setValue("BT", "OPTION");
            } else {
                v_vtweg.setValue("EQ", "OPTION");
            }

            if (zc.getV_VTWEG().get("HIGH") != null && !"".equals(zc.getV_VTWEG().get("HIGH"))) {
                v_vtweg.setValue(zc.getV_VTWEG().get("HIGH"), "HIGH");
            }
            if (zc.getV_VTWEG().get("LOW") != null && !"".equals(zc.getV_VTWEG().get("LOW"))) {
                v_vtweg.setValue(zc.getV_VTWEG().get("LOW"), "LOW");
            }

        }

        // 9.销售组织
        // 单个值
        if (zc.getV_VKORG() != null && !"".equals(zc.getV_VKORG())) {
            function.getImportParameterList().setValue(zc.getV_VKORG(), "V_VKORG");
        }
    }


    public static void main(String args[]) {
        Zles29aCriteria zc = new Zles29aCriteria();

        zc.getV_CLASS().put("LOW", "深圳分公司");
        zc.getV_EBELN().put("LOW", "UA00-00000");
        zc.getV_EBELN().put("HIGH", "UZ99-99999");

        // LED55E52AD
        zc.getV_MATNR().put("LOW", "LED*");

        zc.getV_BSTDK().put("LOW", "2013-12-01");
        zc.getV_BSTDK().put("HIGH", "2013-12-10");
        zc.getV_EINDT().put("LOW", "2013-12-01");
        zc.getV_EINDT().put("HIGH", "2013-12-10");

        zc.setV_VKORG("1001");
        zc.getV_VTWEG().put("LOW", "10");
        // zc.getV_VTWEG().put("HIGH", "20");

        // zc.getV_MATNR().put("HIGH", "");
        long s = System.currentTimeMillis();
        Call_Get_Zles29A call_Get_Zles29A = new Call_Get_Zles29A();
        call_Get_Zles29A.doCall(zc);
        long e = System.currentTimeMillis();

        // System.out.println("taketime:=======>" + (e - s) / 1000 + "s");
        // Hashtable<String, String> hs = new Hashtable<String, String>();
        // hs.put("LOW", "fdfs");
        // hs.put("HIGH", "23232");
        //
        // //System.out.println(hs.get("LOW"));
        // //System.out.println(hs.get("HIGH"));

    }
}
