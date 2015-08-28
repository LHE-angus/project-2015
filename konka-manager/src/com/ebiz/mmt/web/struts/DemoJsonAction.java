package com.ebiz.mmt.web.struts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.support.Table;
import com.ebiz.mmt.domain.support.TableUtils;
import com.ebiz.mmt.domain.support.Tbody;
import com.ebiz.mmt.domain.support.Td;
import com.ebiz.mmt.domain.support.Thead;
import com.ebiz.mmt.domain.support.Tr;

/**
 * @author Jiang,JiaYong
 * @version 2012-09-01
 */
public class DemoJsonAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.getJson(mapping, form, request, response);
	}

	public ActionForward getJson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		int clumns_count = 6;
		int rows_count = 5;

		Tr tr1 = new Tr();
		List<Td> tdList1 = new ArrayList<Td>();
		for (int j = 1; j < clumns_count; j++) {
			Td td = new Td();
			if(j == 1){
				td.setText("月份");
			} else{
				td.setText(String.valueOf((char) (j + 63)) + "门店");
			}
			tdList1.add(td);
		}
		tr1.setTds(tdList1);

		List<Tr> trList1 = new ArrayList<Tr>();
		trList1.add(tr1);

		Thead thead = new Thead();
		thead.setTrs(trList1);

		List<Tr> trList = new ArrayList<Tr>();
		for (int i = 1; i < rows_count; i++) {
			Tr tr = new Tr();

			List<Td> tdList = new ArrayList<Td>();
			for (int j = 1; j < clumns_count; j++) {
				if(j == 1){
					Td td = new Td();
					td.setTdDataType("1");
					td.setText(i + "_" + "月份销量");
					tdList.add(td);
				} else {
					Td td = new Td();
					td.setTdDataType("0");
					if (j == 2) {
						td.setLink("javascript:alert('我是当前页面打开的！');");
					} else if (j == 3) {
						td.setLink("http://www.baidu.com");
					} else if (j == 4) {
						td.setLink("n-http://www.baidu.com");
					} else {
						td.setLink("");
					}
					
					td.setUnit("");
					//td.setUnit("万美元");
					td.setText(String.valueOf(Math.round(Math.random() * 1000)));
					tdList.add(td);
				}
			}
			tr.setTrDataType("0");
			tr.setTds(tdList);

			trList.add(tr);
		}
		Tbody tbody = new Tbody();
		tbody.setTrs(trList);

		Table t = new Table();
		t.setThead(thead);
		t.setTbody(tbody);
		t.setTitle("表格主标题");
		t.setSubTitle("表格副标题");

		super.renderJson(response, TableUtils.toJson(t));
		return null;
	}
}
