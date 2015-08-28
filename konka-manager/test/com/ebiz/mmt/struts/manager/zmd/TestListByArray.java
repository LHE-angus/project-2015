package com.ebiz.mmt.struts.manager.zmd;

import javax.xml.namespace.QName;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import com.ebiz.mmt.domain.MaterialObject;

public class TestListByArray {
	// import com.ebiz.mmt.domain.MaterialObject;
	// import com.mmt.MaterialObject;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws java.lang.Exception {

		EndpointReference targetEPR = new EndpointReference("http://61.191.235.162/axis2/services/MaterialStockService");

		MaterialObject[] argArrayMaterialObject = new MaterialObject[3];

		MaterialObject materialObject = new MaterialObject();
		materialObject.setMaterial("ZME2202KVA");
		materialObject.setPlant("LT0A");
		materialObject.setStorage("8004");
		argArrayMaterialObject[0] = materialObject;

		MaterialObject materialObject2 = new MaterialObject();
		materialObject2.setMaterial("BC-48DM");
		materialObject2.setPlant("LD01");
		materialObject2.setStorage("5012");
		argArrayMaterialObject[1] = materialObject2;

		MaterialObject materialObject3 = new MaterialObject();
		materialObject3.setMaterial("LC42F1000PD");
		materialObject3.setPlant("L00B");
		materialObject3.setStorage("9022");
		argArrayMaterialObject[2] = materialObject3;

		QName opName = new QName("http://mmt.com", "getMaterialStockListByArray");
		Object[] opAddEntryArgs = new Object[] { argArrayMaterialObject };
		Class[] types = new Class[] { MaterialObject[].class };

		RPCServiceClient serviceClient = new RPCServiceClient();

		Options options = new Options();
		options.setTo(targetEPR);
		serviceClient.setOptions(options);

		Object[] response = serviceClient.invokeBlocking(opName, opAddEntryArgs, types);
		MaterialObject[] retArrayMaterialObject = (MaterialObject[]) response[0];

		for (int i = 0; i < retArrayMaterialObject.length; i++) {
			MaterialObject tmp = retArrayMaterialObject[i];
			//System.out.println(tmp.getMaterial() + tmp.getPlant() + tmp.getStorage() + tmp.getLabst());
		}
	}
}
