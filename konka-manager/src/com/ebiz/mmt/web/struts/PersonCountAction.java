package com.ebiz.mmt.web.struts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.support.Table;
import com.ebiz.mmt.domain.support.TableUtils;
import com.ebiz.mmt.domain.support.Tbody;
import com.ebiz.mmt.domain.support.Td;
import com.ebiz.mmt.domain.support.Thead;
import com.ebiz.mmt.domain.support.Tr;

/**
 * @author Pan,Gang
 * @version 2013-08-30
 */
public class PersonCountAction extends BaseAction {

	public ActionForward demoJson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	        throws Exception {
		String josn = "{\"thead\":{\"trs\":[{\"trDataType\":null,\"tds\":[{\"unit\":null,\"text\":\"月份\",\"link\":null,\"tdDataType\":null},{\"unit\":null,\"text\":\"A门店\",\"link\":null,\"tdDataType\":null},{\"unit\":null,\"text\":\"B门店\",\"link\":null,\"tdDataType\":null},{\"unit\":null,\"text\":\"C门店\",\"link\":null,\"tdDataType\":null},{\"unit\":null,\"text\":\"D门店\",\"link\":null,\"tdDataType\":null}]}]},\"subTitle\":\"表格副标题\",\"title\":\"表格主标题\",\"tbody\":{\"trs\":[{\"trDataType\":\"0\",\"tds\":[{\"unit\":null,\"text\":\"1_月份销量\",\"link\":null,\"tdDataType\":\"1\"},{\"unit\":\"\",\"text\":\"46\",\"link\":\"javascript:alert('Current page javascript method!');\",\"tdDataType\":\"0\"},{\"unit\":\"\",\"text\":\"882\",\"link\":\" http://www.baidu.com\",\"tdDataType\":\"0\"},{\"unit\":\"\",\"text\":\"62\",\"link\":\"n- http://www.baidu.com\",\"tdDataType\":\"0\"},{\"unit\":\"\",\"text\":\"649\",\"link\":\"\",\"tdDataType\":\"0\"}]},{\"trDataType\":\"0\",\"tds\":[{\"unit\":null,\"text\":\"2_月份销量\",\"link\":null,\"tdDataType\":\"1\"},{\"unit\":\"\",\"text\":\"877\",\"link\":\"javascript:alert('Current page javascript method!');\",\"tdDataType\":\"0\"},{\"unit\":\"\",\"text\":\"153\",\"link\":\" http://www.baidu.com\",\"tdDataType\":\"0\"},{\"unit\":\"\",\"text\":\"795\",\"link\":\"n- http://www.baidu.com\",\"tdDataType\":\"0\"},{\"unit\":\"\",\"text\":\"599\",\"link\":\"\",\"tdDataType\":\"0\"}]},{\"trDataType\":\"0\",\"tds\":[{\"unit\":null,\"text\":\"3_月份销量\",\"link\":null,\"tdDataType\":\"1\"},{\"unit\":\"\",\"text\":\"962\",\"link\":\"javascript:alert('Current page javascript method!');\",\"tdDataType\":\"0\"},{\"unit\":\"\",\"text\":\"17\",\"link\":\" http://www.baidu.com\",\"tdDataType\":\"0\"},{\"unit\":\"\",\"text\":\"386\",\"link\":\"n- http://www.baidu.com\",\"tdDataType\":\"0\"},{\"unit\":\"\",\"text\":\"396\",\"link\":\"\",\"tdDataType\":\"0\"}]},{\"trDataType\":\"0\",\"tds\":[{\"unit\":null,\"text\":\"4_月份销量\",\"link\":null,\"tdDataType\":\"1\"},{\"unit\":\"\",\"text\":\"800\",\"link\":\"javascript:alert('Current page javascript method!');\",\"tdDataType\":\"0\"},{\"unit\":\"\",\"text\":\"600\",\"link\":\" http://www.baidu.com\",\"tdDataType\":\"0\"},{\"unit\":\"\",\"text\":\"512\",\"link\":\"n- http://www.baidu.com\",\"tdDataType\":\"0\"},{\"unit\":\"\",\"text\":\"660\",\"link\":\"\",\"tdDataType\":\"0\"}]}]}}";
		super.renderJson(response, josn);
		return null;
	}

	public ActionForward getPersonJsonForBranch(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");
		logger.info("dept_id+++++++++++++" + dept_id);

		PeProdUser entity = new PeProdUser();
		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("dept_id_start", dept_id);
		}
		List<PeProdUser> entityList = super.getFacade().getPeProdUserService().getPeProdUserForYwyAndCxyCount(entity);

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(Long.valueOf(dept_id));
		konkaDept = getFacade().getKonkaDeptService().getKonkaDept(konkaDept);

		Table table = new Table();
		table.setTitle(konkaDept.getDept_name());// 主标题
		table.setSubTitle("");// 副标题

		Thead thead = new Thead();

		List<Tr> theadTrList = new ArrayList<Tr>();
		Tr theadTr = new Tr();
		theadTr.setTrDataType("");
		List<Td> theadTdList = new ArrayList<Td>();

		Td tdType = new Td();
		tdType.setText("人员构成");
		tdType.setTdDataType(null);
		tdType.setUnit(null);
		tdType.setLink(null);

		Td tdNum = new Td();
		tdNum.setText("数量");
		tdNum.setTdDataType(null);
		tdNum.setUnit(null);
		tdNum.setLink(null);

		theadTdList.add(tdType);
		theadTdList.add(tdNum);
		theadTr.setTds(theadTdList);
		theadTrList.add(theadTr);

		thead.setTrs(theadTrList);

		table.setThead(thead);

		PeProdUser p = entityList.get(0);

		// 开始封装主题数据
		Tbody tbody = new Tbody();
		List<Tr> tbodyTr = new ArrayList<Tr>();
		// for (PeProdUser p : entityList) {

		Tr tr = new Tr();
		tr.setTrDataType("");

		List<Td> tdList = new ArrayList<Td>();
		Td td1 = new Td();
		td1.setText("业务员"); // 业务员
		td1.setUnit(null);
		td1.setLink(null);
		td1.setTdDataType("1");
		tdList.add(td1);

		Td td2 = new Td();
		td2.setText((String.valueOf((null == p.getMap().get("ywycount2") ? 0 : p.getMap().get("ywycount2"))))); // 业务员
		td2.setUnit("");
		td2.setLink("");
		td2.setTdDataType("0");
		tdList.add(td2);

		tr.setTds(tdList);

		tbodyTr.add(tr);// 一行数据封装一次

		Tr tr1 = new Tr();
		tr1.setTrDataType("");

		List<Td> tdList1 = new ArrayList<Td>();
		Td td3 = new Td();
		td3.setText("促销员"); // 促销员
		td3.setUnit(null);
		td3.setLink(null);
		td3.setTdDataType("1");
		tdList1.add(td3);

		Td td4 = new Td();
		td4.setText((String.valueOf((null == p.getMap().get("cxycount2") ? "0" : p.getMap().get("cxycount2"))))); // 促销员
		td4.setUnit("");
		td4.setLink("");
		td4.setTdDataType("0");
		tdList1.add(td4);

		tr1.setTds(tdList1);

		tbodyTr.add(tr1);

		Tr tr2 = new Tr();
		tr2.setTrDataType("");

		List<Td> tdList2 = new ArrayList<Td>();
		Td td5 = new Td();
		td5.setText("其他"); // 其他
		td5.setUnit(null);
		td5.setLink(null);
		td5.setTdDataType("1");
		tdList2.add(td5);

		Integer a = (Integer) (null == p.getMap().get("totalcount2") ? 0 : p.getMap().get("totalcount2"));
		Integer b = (Integer) (null == p.getMap().get("cxycount2") ? 0 : p.getMap().get("cxycount2"));
		Integer c = (Integer) (null == p.getMap().get("ywycount2") ? 0 : p.getMap().get("ywycount2"));
		Integer d = a - b - c;
		Td td6 = new Td();
		td6.setText(String.valueOf(d)); // 其他
		td6.setUnit("");
		td6.setLink("");
		td6.setTdDataType("0");
		td6.setLink("");
		tdList2.add(td6);

		tr2.setTds(tdList2);

		tbodyTr.add(tr2);
		// }
		tbody.setTrs(tbodyTr);

		table.setTbody(tbody);

		logger.info("----json--->{}", TableUtils.toJson(table));
		super.renderJson(response, TableUtils.toJson(table));
		return null;
	}
}