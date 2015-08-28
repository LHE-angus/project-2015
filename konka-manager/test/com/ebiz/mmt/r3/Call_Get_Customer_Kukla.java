package com.ebiz.mmt.r3;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;


public class Call_Get_Customer_Kukla {

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

    public List<KUKLA> doCall() {
        List<KUKLA> kuklaList = new ArrayList<KUKLA>();

        JCO.Client mConnection = null;
        JCO.Repository mRepository = null;
        JCO.Function function = null;
        try {
            mConnection = ConnectPoolManager.getConnectionInSAPPool();
            mConnection.connect();
            mRepository = new JCO.Repository("KJQD", mConnection);
            function = this.createFunction("ZMMT_GET_CUSTOMER_KUKLA", mRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mConnection != null && mRepository != null && function != null) {
            try {
                // 调用
                mConnection.execute(function);
                JCO.Table item1 = null;
                item1 = function.getTableParameterList().getTable("ZTKUKT_LIST");
                for (int i = 0; i < item1.getNumRows(); i++) {
                    item1.setRow(i);
                    KUKLA kukla = new KUKLA();
                    kukla.setMANDT(item1.getString("MANDT"));
                    kukla.setSPRAS(item1.getString("SPRAS"));
                    kukla.setKUKLA(item1.getString("KUKLA"));
                    kukla.setVTEXT(item1.getString("VTEXT"));
                    kuklaList.add(kukla);
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

        return kuklaList;

    }

    public static void main(String args[]) {
        Call_Get_Customer_Kukla call_Get_Customer_Kukla = new Call_Get_Customer_Kukla();
        long s = System.currentTimeMillis();
        Call_Delete_So cds = new Call_Delete_So();
        for (int i = 0; i < 5; i++) {
            System.out.println(i + 1);

            call_Get_Customer_Kukla.doCall();
            // cds.doCall("2233445566", "konkaqd");
            // System.out.println(i);

        }
        long e = System.currentTimeMillis();
        System.out.println((e - s) / 1000);
    }
}
