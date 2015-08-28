package com.ebiz.mmt.r3;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;

/**
 * 总部业绩划拨数据导出
 * 
 * @author ZHOU
 * @since 2014-2-20
 */
public class Call_Get_ZQD_ZYJHB {
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

    public List<ZYJHB> doCall(String zlb, String zbukrs, String zlfgja, String zlfmon) {
        List<ZYJHB> list = new ArrayList<ZYJHB>();

        JCO.Client mConnection = null;
        JCO.Repository mRepository = null;
        JCO.Function function = null;
        try {
            mConnection = ConnectPoolManager.getConnectionInSAPPool();
            mConnection.connect();
            mRepository = new JCO.Repository("KJQD", mConnection);
            function = this.createFunction("ZQD_ZYJHBDR", mRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mConnection != null && mRepository != null && function != null) {

            try {

                // 类别
                // 销售组织代码
                // 会计年度
                // 会计月度
                // 2 set
                if (zlb != null && !"".equals(zlb)) {
                    function.getImportParameterList().setValue(zlb, "ZLB");
                }
                function.getImportParameterList().setValue(zbukrs, "ZBUKRS");
                function.getImportParameterList().setValue(zlfgja, "ZLFGJA");
                function.getImportParameterList().setValue(zlfmon, "ZLFMON");

                // 3 do call function
                mConnection.execute(function);

                // 4. log
                // function.writeHTML("D:/Call_Get_ZQD_ZYJHB.html");

                // 5. result
                // 类别 名称 代码 年度 月份 结算数量 结算金额 汇款金额
                // MANDT ZLB BUKRS LFGJA LFMON ZJSSL ZJSJE ZHKJE ERDAT UNAME
                // BUTXT
                JCO.Table item1 = null;
                item1 = function.getTableParameterList().getTable("LIST");

                for (int i = 0; i < item1.getNumRows(); i++) {
                    item1.setRow(i);
                    ZYJHB zyjhb = new ZYJHB();
                    zyjhb.setBUKRS(item1.getString("BUKRS"));
                    zyjhb.setBUTXT(item1.getString("BUTXT"));
                    zyjhb.setERDAT(item1.getString("ERDAT"));
                    zyjhb.setLFGJA(item1.getString("LFGJA"));
                    zyjhb.setLFMON(item1.getString("LFMON"));
                    zyjhb.setMANDT(item1.getString("MANDT"));
                    zyjhb.setUNAME(item1.getString("UNAME"));
                    zyjhb.setZHKJE(Double.valueOf(item1.getString("ZHKJE")));
                    zyjhb.setZJSJE(Double.valueOf(item1.getString("ZJSJE")));
                    zyjhb.setZJSSL(Double.valueOf(item1.getString("ZJSSL")));
                    zyjhb.setZLB(item1.getString("ZLB"));

                    list.add(zyjhb);
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

    // public static void main(String[] args) {
    // new Call_Get_ZQD_ZYJHB().doCall("大客户", "KF68", "2014", "02");
    // }
}
