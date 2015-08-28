package com.ebiz.mmt.r3;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;

public class Call_Delete_So {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public JCO.Function createFunction(String name, JCO.Repository mRepository) throws Exception {
		try {
			IFunctionTemplate ft = mRepository.getFunctionTemplate(name.toUpperCase());
			if (ft == null)
				return null;
			return ft.getFunction();
		} catch (Exception ex) {
			throw new Exception("Problem retrieving JCO.Function object.");
		}
	}

	// orderHeader上有行的List
	public ReturnInfo doCall(String orderNumber, String opName) {

		JCO.Table itemPoItem = null;
		ReturnInfo returnInfo = new ReturnInfo();
		returnInfo.setSuccess(false);


        JCO.Client mConnection = null;
        JCO.Repository mRepository = null;
        JCO.Function function = null;
        try {
            mConnection = ConnectPoolManager.getConnectionInSAPPool();
            mConnection.connect();
            mRepository = new JCO.Repository("KJQD", mConnection);
            function = this.createFunction("ZMMT_DEL_SALE_ORDER", mRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }

		try {
			// 订单编号
			function.getImportParameterList().setValue(orderNumber, "V_VBELN");
			// 当时进行操作人
			function.getImportParameterList().setValue(opName, "OPERNAME");
			//
			function.getImportParameterList().setValue("X", "COMMIT");
			// 调用执行
			mConnection.execute(function);

			// function.writeHTML("d:/Call_Delete_So.html");
			// 返回的提示信息
			itemPoItem = function.getTableParameterList().getTable("RETURN");
			List<ExcuteMsg> msgList = new ArrayList<ExcuteMsg>();
			for (int k = 0; k < itemPoItem.getNumRows(); k++) {
				itemPoItem.setRow(k);
				ExcuteMsg excutemsg = new ExcuteMsg();
				excutemsg.setType(itemPoItem.getString("TYPE").toUpperCase());
				excutemsg.setMessage(itemPoItem.getString("MESSAGE"));
				msgList.add(excutemsg);
			}
			Boolean success = true;
			// 如果type有E,说明有错误返回
			for (ExcuteMsg msg : msgList) {
				if (msg.getType() != null) {
					if ("E".equals(msg.getType().toString().toUpperCase())) {
						success = false;
						break;
					}
				}
			}
			// 封装返回信息
			returnInfo.setSuccess(success);
			returnInfo.setMsgList(msgList);
			// 不作为删除成功的条件判断
			returnInfo.setItemNO(orderNumber);

		} catch (Exception ex) {
			logger.info(ex.getMessage());
			//
		} finally {
            mRepository = null;
            
            if (mConnection != null) {
                ConnectPoolManager.releaseClient(mConnection);
            }
		}

		return returnInfo;
	}

	/**
	 * 删除销售订单
	 * 
	 * @param args
	 */
    public static void main(String args[]) {
        Call_Delete_So cds = new Call_Delete_So();
        ReturnInfo info = cds.doCall("620169217", "konkaqd");

        System.out.println(info.getSuccess());

    }
}