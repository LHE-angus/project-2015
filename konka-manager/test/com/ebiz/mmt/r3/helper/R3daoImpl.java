package com.ebiz.mmt.r3.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ebiz.mmt.domain.KonkaR3OrderLines;
import com.ebiz.mmt.r3.CALL_Get_SOXX;
import com.ebiz.mmt.r3.CALL_Get_SOXX_New;
import com.ebiz.mmt.r3.Call_Create_So;
import com.ebiz.mmt.r3.Call_Create_So_T;
import com.ebiz.mmt.r3.Call_Delete_So;
import com.ebiz.mmt.r3.Call_Get_Customer2;
import com.ebiz.mmt.r3.Call_Get_Customer_Knvp02;
import com.ebiz.mmt.r3.Call_Get_Customer_Kukla;
import com.ebiz.mmt.r3.Call_Get_KCXX;
import com.ebiz.mmt.r3.Call_Get_KHXD;
import com.ebiz.mmt.r3.Call_Get_KHYS;
import com.ebiz.mmt.r3.Call_Get_Lgort_Stock;
import com.ebiz.mmt.r3.Call_Get_Mara_Makt;
import com.ebiz.mmt.r3.Call_Get_So_Lips;
import com.ebiz.mmt.r3.Call_Get_Werks_Lgort;
import com.ebiz.mmt.r3.Call_Get_ZA006;
import com.ebiz.mmt.r3.Call_Get_ZDMTRXS;
import com.ebiz.mmt.r3.Call_Get_ZQD_ZYJHB;
import com.ebiz.mmt.r3.Call_Get_ZSOF;
import com.ebiz.mmt.r3.Call_Get_Zles20;
import com.ebiz.mmt.r3.Call_Get_Zles23;
import com.ebiz.mmt.r3.Call_Get_Zles29A;
import com.ebiz.mmt.r3.Call_Get_ZlesZJ98;
import com.ebiz.mmt.r3.Call_Update_So;
import com.ebiz.mmt.r3.ITR2;
import com.ebiz.mmt.r3.KCXX;
import com.ebiz.mmt.r3.KHXD;
import com.ebiz.mmt.r3.KHYS;
import com.ebiz.mmt.r3.KNA1;
import com.ebiz.mmt.r3.KNVP;
import com.ebiz.mmt.r3.KUKLA;
import com.ebiz.mmt.r3.MARA;
import com.ebiz.mmt.r3.ORDER_HEADER_IN;
import com.ebiz.mmt.r3.ReturnInfo;
import com.ebiz.mmt.r3.SOXX;
import com.ebiz.mmt.r3.StockCheckResult;
import com.ebiz.mmt.r3.ZA006;
import com.ebiz.mmt.r3.ZJ98;
import com.ebiz.mmt.r3.ZLEBIN;
import com.ebiz.mmt.r3.ZLES23;
import com.ebiz.mmt.r3.ZSOF;
import com.ebiz.mmt.r3.ZVBALR;
import com.ebiz.mmt.r3.ZYJHB;
import com.ebiz.mmt.r3.ZdmtrxCriteria;
import com.ebiz.mmt.r3.Zles29a;
import com.ebiz.mmt.r3.Zles29aCriteria;

@Repository
public class R3daoImpl implements R3Dao {

	@Override
	public List<KNA1> getCustomerList(String in_date, String bukrs, String[] kunnr) {

		List<KNA1> list = new ArrayList<KNA1>();

		if (bukrs == null || "".equals(bukrs)) {
			return null;
		}

		// bukrs +　kunnr　两者同时为空时
		if ((bukrs == null || "".equals(bukrs)) && (kunnr == null || kunnr.length <= 0)) {
			return null;
		}

		list = new Call_Get_Customer2().doCall(in_date, bukrs, kunnr);
		return list;
	}

	@Override
	public List<KNVP> getKnvpsList(String vkorg, String vtweg, String spart, String kunnr) {

		if (vkorg == null || "".equals(vkorg)) {
			return null;
		}
		if (vtweg == null || "".equals(vtweg)) {
			return null;
		}
		if (spart == null || "".equals(spart)) {
			return null;
		}

		if (kunnr == null)
			kunnr = "";

		List<KNVP> list = new ArrayList<KNVP>();
		list = new Call_Get_Customer_Knvp02().doCall(vkorg, vtweg, spart, kunnr);
		return list;
	}

	@Override
	public List<KUKLA> getkuklaList() {

		List<KUKLA> list = new ArrayList<KUKLA>();

		list = new Call_Get_Customer_Kukla().doCall();

		return list;
	}

	@Override
	public List<MARA> getMaraList(String erdat) {
		if (erdat == null || "".equals(erdat))
			return null;

		List<MARA> list = new ArrayList<MARA>();
		list = new Call_Get_Mara_Makt().doCall(erdat);
		return list;
	}

	@Override
	public List<StockCheckResult> checkStock(String zbukrs, List<KonkaR3OrderLines> itemList) {

		if (zbukrs == null || "".equals(zbukrs))
			return null;

		if (itemList == null)
			return null;

		List<StockCheckResult> list = new ArrayList<StockCheckResult>();
		list = new Call_Get_Lgort_Stock().doCall(zbukrs, itemList);

		return list;
	}

	@Override
	public List<StockCheckResult> checkStock2(String zwerks, String zlgort, String matnr) {
		if (zwerks == null || "".equals(zwerks)) {
			return null;
		}
		if (zlgort == null || "".equals(zlgort)) {
			return null;
		}

		List<StockCheckResult> list = new ArrayList<StockCheckResult>();
		list = new Call_Get_Werks_Lgort().doCall(zwerks, zlgort, matnr);
		return list;
	}

	@Override
	public List<KHXD> getKhxd(String v_kkber, String v_vkorg, String v_spart, String[] kunnr) {

		List<KHXD> list = new ArrayList<KHXD>();

		if (v_kkber == null || "".equals(v_kkber)) {
			return null;
		}
		if (v_spart == null || "".equals(v_spart)) {
			return null;
		}
		// v_vkorg +　kunnr　两者同时为空时
		if ((v_vkorg == null || "".equals(v_vkorg)) && (kunnr == null || kunnr.length <= 0)) {
			return null;
		}

		list = new Call_Get_KHXD().doCall(v_kkber, v_vkorg, v_spart, kunnr);

		return list;
	}

	@Override
	public List<KHYS> getKhys(String v_gjahr, String v_monat, String v_spart, String v_vkorg, String[] kunnr) {

		if (v_gjahr == null || "".equals(v_gjahr)) {
			return null;
		}
		if (v_monat == null || "".equals(v_monat)) {
			return null;
		}
		if (v_spart == null || "".equals(v_spart)) {
			return null;
		}

		// v_vkorg +　kunnr　两者同时为空时
		if ((v_vkorg == null || "".equals(v_vkorg)) && (kunnr == null || kunnr.length <= 0)) {
			return null;
		}

		List<KHYS> list = new ArrayList<KHYS>();
		list = new Call_Get_KHYS().doCall(v_gjahr, v_monat, v_spart, v_vkorg, kunnr);

		return list;
	}

	@Override
	public List<KCXX> getKcxx(String v_werks, String v_lgort, String v_lgpla, String v_matnr) {
		if (v_werks == null || "".equals(v_werks)) {
			return null;
		}
		if (v_lgort == null || "".equals(v_lgort)) {
			return null;
		}
		// if (v_lgpla == null || "".equals(v_lgpla)) {
		// return null;
		// }
		List<KCXX> list = new ArrayList<KCXX>();
		list = new Call_Get_KCXX().doCall(v_werks, v_lgort, v_lgpla, v_matnr);
		return list;
	}

	// 访问用于统计数据的接口
	@Override
	public List<SOXX> getSoxxTj(String v_vkorg, String v_vtweg, String v_spart, String v_audat_begin,
			String v_audat_end, String v_kunnr) {

		if (v_vkorg == null || "".equals(v_vkorg)) {
			return null;
		}
		if (v_vtweg == null || "".equals(v_vtweg)) {
			return null;
		}
		if (v_spart == null || "".equals(v_spart)) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		if (v_audat_begin != null && v_audat_begin.length() >= 0) {
			v_audat_begin = StringUtils.replace(v_audat_begin, "-", "");
		}

		// 如果结束日期为空,自动默认为今天
		if (v_audat_end == null || "".equals(v_audat_end)) {
			Calendar calendar = Calendar.getInstance();
			v_audat_end = sdf.format(calendar.getTime());
		}
		if (v_audat_end != null && v_audat_end.length() >= 0) {
			v_audat_end = StringUtils.replace(v_audat_end, "-", "");
		}

		List<SOXX> list = new ArrayList<SOXX>();
		list = new CALL_Get_SOXX().doCall(v_vkorg, v_vtweg, v_spart, v_audat_begin, v_audat_end, v_kunnr);
		return list;
	}

	// 访问用于直接查询数据的接口
	@Override
	public List<SOXX> getSoxxMx(String v_vkorg, String v_vtweg, String v_spart, String v_audat_begin,
			String v_audat_end, String v_kunnr) {

		if (v_vkorg == null || "".equals(v_vkorg)) {
			return null;
		}
		if (v_vtweg == null || "".equals(v_vtweg)) {
			return null;
		}
		if (v_spart == null || "".equals(v_spart)) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		if (v_audat_begin != null && v_audat_begin.length() >= 0) {
			v_audat_begin = StringUtils.replace(v_audat_begin, "-", "");
		}

		// 如果结束日期为空,自动默认为今天
		if (v_audat_end == null || "".equals(v_audat_end)) {
			Calendar calendar = Calendar.getInstance();
			v_audat_end = sdf.format(calendar.getTime());
		}
		if (v_audat_end != null && v_audat_end.length() >= 0) {
			v_audat_end = StringUtils.replace(v_audat_end, "-", "");
		}

		List<SOXX> list = new ArrayList<SOXX>();
		list = new CALL_Get_SOXX_New().doCall(v_vkorg, v_vtweg, v_spart, v_audat_begin, v_audat_end, v_kunnr);

		return list;
	}

	// 集采数据,用于查询
	@Override
	public List<ZLES23> getZles23(String erdatB, String erdatE, String ZEBELN) {
		SimpleDateFormat dateformate = new SimpleDateFormat("yyyy-MM-dd");
		// 如果结束日期为空,自动默认为今天
		if (erdatE == null || "".equals(erdatE)) {
			Calendar calendar = Calendar.getInstance();
			erdatE = dateformate.format(calendar.getTime());
		}

		List<ZLES23> list = new ArrayList<ZLES23>();
		list = new Call_Get_Zles23().doCall(erdatB, erdatE, ZEBELN);
		return list;
	}

	@Override
	public List<ZA006> getZa006(String vmatnr) {
		if (vmatnr == null || "".equals(vmatnr)) {
			return null;
		}
		List<ZA006> list = new ArrayList<ZA006>();
		list = new Call_Get_ZA006().doCall(vmatnr);
		return list;
	}

	@Override
	public List<StockCheckResult> getFGSKC(String zwerks, String zlgort, String zmatnr) {
		if (zwerks == null || "".equals(zwerks)) {
			return null;
		}
		if (zlgort == null || "".equals(zlgort)) {
			return null;
		}
		List<StockCheckResult> list = new ArrayList<StockCheckResult>();
		list = new Call_Get_Werks_Lgort().doCall(zwerks, zlgort, zmatnr);
		return list;
	}

	@Override
	public List<ZLEBIN> getZles20(String zwerks, String zlgort, String zlgpla, String zmatnr) {
		if (zwerks == null || "".equals(zwerks)) {
			return null;
		}
		if (zlgort == null || "".equals(zlgort)) {
			return null;
		}

		List<ZLEBIN> list = new ArrayList<ZLEBIN>();
		list = new Call_Get_Zles20().doCall(zwerks, zlgort, zlgpla, zmatnr);
		return list;
	}

	@Override
	public List<Zles29a> getZles29a(Zles29aCriteria zc) {
		List<Zles29a> list = new ArrayList<Zles29a>();
		list = new Call_Get_Zles29A().doCall(zc);
		return list;
	}

	@Override
	public List<ZJ98> getZlesZJ98(String v_matnr_begin, String v_matnr_end, String v_vtweg, String v_spart,
			String v_bstdk_begin, String v_bstdk_end, String v_cxb_begin, String v_cxb_end) {
		List<ZJ98> list = new ArrayList<ZJ98>();
		list = new Call_Get_ZlesZJ98().doCall(v_matnr_begin, v_matnr_end, v_vtweg, v_spart, v_bstdk_begin, v_bstdk_end,
				v_cxb_begin, v_cxb_end);
		return list;
	}

	@Override
	public List<ITR2> getITR2(ZdmtrxCriteria zc) {
		List<ITR2> list = new ArrayList<ITR2>();
		list = new Call_Get_ZDMTRXS().doCall(zc);
		return list;
	}

	@Override
	public List<ZYJHB> getYJHBList(String zlb, String zbukrs, String zlfgja, String zlfmon) {
		List<ZYJHB> list = new ArrayList<ZYJHB>();
		list = new Call_Get_ZQD_ZYJHB().doCall(zlb, zbukrs, zlfgja, zlfmon);
		return list;

	}

	@Override
	public ZSOF getR3Delivery(Long v_vbeln) {
		ZSOF zsof = new ZSOF();
		zsof = new Call_Get_ZSOF().doCall(v_vbeln);
		return zsof;
	}

	@Override
	public ReturnInfo createDestorySO(String orderNumber, String opName) {
		return new Call_Delete_So().doCall(orderNumber, opName);
	}

	@Override
	public ReturnInfo createReturnSO(ORDER_HEADER_IN orderHeader) {
		return new Call_Create_So_T().doCall(orderHeader);
	}

	@Override
	public ReturnInfo createSO(ORDER_HEADER_IN orderHeader) {
		return new Call_Create_So().doCall(orderHeader);
	}

	@Override
	public ReturnInfo updateSO(ORDER_HEADER_IN orderHeader, String orderNumber) {
		return new Call_Update_So().doCall(orderHeader, orderNumber);
	}

	@Override
	public List<ZVBALR> getZVBALRList(String v_vkorg, String v_vtweg, String v_spart, String v_audat_begin,
			String v_audat_end, String v_kunnr, String v_vbeln) {
		return new Call_Get_So_Lips().doCall(v_vkorg, v_vtweg, v_spart, v_audat_begin, v_audat_end, v_kunnr, v_vbeln);
	}

}
