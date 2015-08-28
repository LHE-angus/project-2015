package com.ebiz.mmt.r3;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ebiz.org.apache.commons.lang3.StringUtils;
import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;
import com.sap.mw.jco.JCO.Table;

/**
 * 机型日销毛利分析接口(目前无法精确指定日期,只能算当月月初到今天的数据)
 * 
 * <p>
 * 1.目前接口支持日期跨度查询
 * </p>
 * <p>
 * 2.客户单值查询
 * </p>
 * <p>
 * 3.销售组织单值查询
 * </p>
 * <p>
 * 4.机型模糊查询 Like
 * </p>
 * 
 * @author ZHOU
 * @since 2013-1-20
 */
public class Call_Get_ZDMTRXS {
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

    public List<ITR2> doCall(ZdmtrxCriteria zc) {
        List<ITR2> list = new ArrayList<ITR2>();

        JCO.Client mConnection = null;
        JCO.Repository mRepository = null;
        JCO.Function function = null;
        try {
            mConnection = ConnectPoolManager.getConnectionInSAPPool();
            mConnection.connect();
            mRepository = new JCO.Repository("KJQD", mConnection);
            function = this.createFunction("Z_RFC_ZDMTRXS", mRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mConnection != null && mRepository != null && function != null) {

            try {

                // V_LFGJA 年度 多值
                // V_LFMON 月份 多值
                // V_KUNNR 客户 多值
                // V_VKORG 销售组织 多值
                // V_MATNR 机型 多值

                // 2.buildCriteria
                buildCriteria(function, zc);

                // 3 do call function
                mConnection.execute(function);

                // 4. log
                // function.writeHTML("D:/Z_RFC_ZDMTRXS.html");

                // MATNR MATNR CHAR 18 0 物料号
                // LFIMG LFIMG QUAN 13 3 实际已交货量（按销售单位）
                // ZNET_SINCOME ZNET_SINCOME CURR 15 2 销售收入(不含税)
                // Z_COST Z_COST CURR 15 2 销售成本
                // ZPROFIT ZPROFIT CURR 15 2 销售毛利
                // ZPROFIT_V ZPROFIT_V CURR 11 2 毛利率*100
                // 5. result
                JCO.Table item1 = null;
                item1 = function.getTableParameterList().getTable("IT_R2");

                for (int i = 0; i < item1.getNumRows(); i++) {
                    item1.setRow(i);
                    // String matnr = item1.getString("MATNR");
                    // String lfimg = item1.getString("LFIMG");
                    // String znet_sincome = item1.getString("ZNET_SINCOME");
                    // String z_cost = item1.getString("Z_COST");
                    // String zprofit = item1.getString("ZPROFIT");
                    // String zprofit_v = item1.getString("ZPROFIT_V");

                    ITR2 itr = new ITR2();
                    itr.setLFIMG(item1.getString("LFIMG"));
                    itr.setMATNR(item1.getString("MATNR"));
                    itr.setZCOST(item1.getString("Z_COST"));
                    itr.setZNET_SINCOME(item1.getString("ZNET_SINCOME"));
                    itr.setZPROFIT(item1.getString("ZPROFIT"));
                    itr.setZPROFIT_V(item1.getString("ZPROFIT_V"));
                    list.add(itr);
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

    private void buildCriteria(JCO.Function function, ZdmtrxCriteria zc) {
        // 1 年度
        Table v_lfgja = null;
        v_lfgja = function.getTableParameterList().getTable("V_LFGJA");
        if (StringUtils.isNotEmpty(zc.getV_LFGJA().get("LOW")) || StringUtils.isNotEmpty(zc.getV_LFGJA().get("HIGH"))) {
            v_lfgja.appendRow();
            v_lfgja.setValue("I", "SIGN");
            v_lfgja.setValue("BT", "OPTION");
            v_lfgja.setValue(zc.getV_LFGJA().get("LOW"), "LOW");
            if (StringUtils.isNotEmpty(zc.getV_LFGJA().get("HIGH"))) {
                v_lfgja.setValue(zc.getV_LFGJA().get("HIGH"), "HIGH");
            }
        }

        // 2 月份
        Table v_lfmon = null;
        v_lfmon = function.getTableParameterList().getTable("V_LFMON");
        if (StringUtils.isNotEmpty(zc.getV_LFMON().get("LOW")) || StringUtils.isNotEmpty(zc.getV_LFMON().get("HIGH"))) {
            v_lfmon.appendRow();
            v_lfmon.setValue("I", "SIGN");
            v_lfmon.setValue("BT", "OPTION");
            v_lfmon.setValue(zc.getV_LFMON().get("LOW"), "LOW");
            if (StringUtils.isNotEmpty(zc.getV_LFMON().get("HIGH"))) {
                v_lfmon.setValue(zc.getV_LFMON().get("HIGH"), "HIGH");
            }
        }

        // 3客户(一般不用.接口取数是按机型统计所有分公司的销量,库存,利润等信息)
        Table v_kunnr = null;
        v_kunnr = function.getTableParameterList().getTable("V_KUNNR");
        if (StringUtils.isNotEmpty(zc.getV_KUNNR().get("LOW")) || StringUtils.isNotEmpty(zc.getV_KUNNR().get("HIGH"))) {
            v_kunnr.appendRow();
            v_kunnr.setValue("I", "SIGN");
            v_kunnr.setValue("EQ", "OPTION");
            v_kunnr.setValue(zc.getV_KUNNR().get("LOW"), "LOW");
        }

        // 4销售组织
        Table v_vkorg = null;
        v_vkorg = function.getTableParameterList().getTable("V_VKORG");
        if (StringUtils.isNotEmpty(zc.getV_VKORG().get("LOW")) || StringUtils.isNotEmpty(zc.getV_VKORG().get("HIGH"))) {
            v_vkorg.appendRow();
            v_vkorg.setValue("I", "SIGN");
            v_vkorg.setValue("EQ", "OPTION");
            v_vkorg.setValue(zc.getV_VKORG().get("LOW"), "LOW");

        }

        // 5机型
        Table v_matnr = null;
        v_matnr = function.getTableParameterList().getTable("V_MATNR");
        if (StringUtils.isNotEmpty(zc.getV_MATNR().get("LOW")) || StringUtils.isNotEmpty(zc.getV_MATNR().get("HIGH"))) {
            v_matnr.appendRow();
            v_matnr.setValue("I", "SIGN");
            v_matnr.setValue("CP", "OPTION");
            v_matnr.setValue(zc.getV_MATNR().get("LOW"), "LOW");
        }
    }

    public static void main(String[] args) {
        ZdmtrxCriteria zc = new ZdmtrxCriteria();

        zc.getV_LFGJA().put("LOW", "2013");
        zc.getV_LFGJA().put("HIGH", "2013");

        zc.getV_LFMON().put("LOW", "01");
        zc.getV_LFMON().put("HIGH", "01");

        // zc.getV_KUNNR().put("LOW", "F14701001");
        // zc.getV_KUNNR().put("HIGH", "F14701001");

        zc.getV_MATNR().put("LOW", "LC*");
        zc.getV_MATNR().put("HIGH", "");

        // zc.getV_VKORG().put("LOW", "KF47");
        // zc.getV_VKORG().put("HIGH", "");

        long s = System.currentTimeMillis();
        Call_Get_ZDMTRXS call_Get_ZDMTRXS = new Call_Get_ZDMTRXS();
        List<ITR2> list = call_Get_ZDMTRXS.doCall(zc);
        // System.out.println(list.size());


        long e = System.currentTimeMillis();
        // //System.out.println("taketime:=======>" + (e - s) / 1000 + "s");

    }
}
