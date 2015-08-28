package com.ebiz.mmt.r3;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;


public class Call_Get_ZA006 {
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

    public List<ZA006> doCall(String v_matnr) {
        List<ZA006> list = new ArrayList<ZA006>();
        JCO.Client mConnection = null;
        JCO.Repository mRepository = null;
        JCO.Function function = null;
        try {
            mConnection = ConnectPoolManager.getConnectionInSAPPool();
            mConnection.connect();
            mRepository = new JCO.Repository("KJQD", mConnection);
            function = this.createFunction("ZMMT_GET_ZA006", mRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mConnection != null && mRepository != null && function != null) {
            try {
                // 不传入物料号，则查询所有的物料
                if (v_matnr != null && !"".equals(v_matnr)) {
                    function.getImportParameterList().setValue(v_matnr, "V_MATNR");
                }
                // 调用
                mConnection.execute(function);

                JCO.Table item1 = function.getTableParameterList().getTable("ZA006_LIST");
                if (item1 != null) {
                    for (int i = 0; i < item1.getNumRows(); i++) {
                        item1.setRow(i);
                        ZA006 za006 = new ZA006();
                        za006.setWLBM(item1.getString("MATNR"));
                        za006.setWLJG(item1.getString("CMPRE"));
                        list.add(za006);
                    }
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
        Call_Get_ZA006 call_Get_ZA006 = new Call_Get_ZA006();
        List<ZA006> list = call_Get_ZA006.doCall("BCD-140JL-FS");
        System.out.println(list.get(0).getWLBM() + "-" + list.get(0).getWLJG());
    }
}
