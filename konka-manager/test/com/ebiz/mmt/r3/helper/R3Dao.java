package com.ebiz.mmt.r3.helper;

import java.util.List;

import com.ebiz.mmt.domain.KonkaR3OrderLines;
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

public interface R3Dao {

	/**
	 * 首先,bukrs 任何情况都不能为空,否则或者不到分公司信息
	 * 
	 * 1.输入kunnr参数 ,查询指定的客户信息 2.输入in_date参数,某个时间点后面的数据
	 * 
	 * 简言之, bukrs 不能空, kunnr 和in_date 两者不能同时为空
	 * 
	 * @param in_date
	 * @param bukrs
	 * @param kunnr
	 * @return
	 */
	List<KNA1> getCustomerList(String in_date, String bukrs, String[] kunnr);

	/**
	 * 返回指定的客户合作伙伴信息 vkorg vtweg spart 三者都不为空 kunnr 可以为空 ,如果输入kunnr
	 * 参数,则查询该客户的合作伙伴信息
	 * 
	 * @param vkorg
	 *            必须条件
	 * @param vtweg
	 *            必须条件
	 * @param spart
	 *            必须条件
	 * @param kunnr
	 *            客户编码 可选条件
	 * @return
	 */
	List<KNVP> getKnvpsList(String vkorg, String vtweg, String spart, String kunnr);

	/**
	 * 返回客户类别信息(数据不会多,百来条)
	 * 
	 * @return
	 */
	List<KUKLA> getkuklaList();

	/**
	 * 通过61订单号查找R3物流信息
	 * 
	 * @return
	 */
	ZSOF getR3Delivery(Long v_vbeln);

	/**
	 * 物料同步接口
	 * 
	 * @param erdat
	 *            不能为空
	 * @return
	 */
	List<MARA> getMaraList(String erdat);

	/**
	 * 把某张订单上的订单行,一次性进行校验 然后返回各个物料行是否满足
	 * 
	 * @param zbukrs
	 *            不能为空
	 * @param itemList
	 *            //行数据 必填项 :物料,数量
	 * @return 是否满足
	 */
	List<StockCheckResult> checkStock(String zbukrs, List<KonkaR3OrderLines> itemList);

	/**
	 * 库存查询
	 * 
	 * 订单在审核过程中,会指定每一行物料的工厂和仓位
	 * 
	 * @param zwerks
	 *            工厂
	 * @param zlgort
	 *            仓位
	 * @param matnr
	 *            物料
	 * @return
	 */
	List<StockCheckResult> checkStock2(String zwerks, String zlgort, String matnr);

	/**
	 * 返回客户账期信息
	 * 
	 * 如果指定客户编码了,就直接查客户编码,否则需要v_kkber 和 v_vkorg 和 v_spart三者一起使用 v_kkber v_spart
	 * 都不能为空 v_vkorg kunnr 不能同时为空
	 * 
	 * @param v_kkber
	 *            信用控制范围 KF01 是默认
	 * @param v_vkorg
	 *            分公司代码
	 * @param v_spart
	 *            产品组
	 * @param kunnr
	 *            客户编码 (如果有指定客户编码,则只查询指定客户编码的账期信息)
	 * @return
	 */
	List<KHXD> getKhxd(String v_kkber, String v_vkorg, String v_spart, String[] kunnr);

	/**
	 * 返回客户应收信息 v_gjahr v_monat v_spart 不能为空 v_vkorg 和kunnr 不能同时为空
	 * 
	 * @param v_gjahr
	 *            年份
	 * @param v_monat
	 *            月份
	 * @param v_spart
	 *            产品组
	 * @param v_vkorg
	 *            分公司代码
	 * @param kunnr
	 *            客户编码 : 客户编码 (如果有指定客户编码,则只查询指定客户编码的客户应收信息)
	 * @return
	 */
	List<KHYS> getKhys(String v_gjahr, String v_monat, String v_spart, String v_vkorg, String[] kunnr);

	/**
	 * 返回库存信息
	 * 
	 * @param v_werks工厂不能为空
	 * @param v_lgort库存地点
	 *            库位 不能为空
	 * @param v_lgpla仓位
	 *            不能为空
	 * @param v_matnr
	 *            物料编码
	 * @return
	 */

	List<KCXX> getKcxx(String v_werks, String v_lgort, String v_lgpla, String v_matnr);

	/**
	 * 返回分公司库存信息,分公司绑定工厂,工厂再绑定库位
	 * 
	 * @描述 目前是根据输入的参数条件而定,可以查正品90仓,也可以查物流仓
	 * @param zwerks
	 *            not null 工厂 L00c
	 * @param zlgort
	 *            not null 库位(仓库地点) 9034
	 * @param zmatnr
	 *            物料
	 * @return
	 */
	List<StockCheckResult> getFGSKC(String zwerks, String zlgort, String zmatnr);

	/**
	 * 返回分公司库存信息,可以详细到机型,和仓位ypqd
	 * 
	 * @param zwerks
	 *            not null 工厂
	 * @param zlgort
	 *            not null 库位(仓库地点)
	 * @param zlgpla
	 *            not null 仓位
	 * @param zmatnr
	 *            物料
	 * @return
	 */
	List<ZLEBIN> getZles20(String zwerks, String zlgort, String zlgpla, String zmatnr);

	/**
	 * 销售订单明细(用于统计数据用)
	 * 
	 * @param v_vkorg
	 *            销售组织(一般为分公司代码) 不能为空
	 * @param v_vtweg
	 *            分销渠道 不能为空
	 * @param v_spart
	 *            产品组 不能为空
	 * @param v_audat_begin
	 *            开始日期 可以为空,可能查询数据量多,速度较慢
	 * @param v_audat_end
	 *            结束日期 可以为空,但为空时会自动默认为今天
	 * @param v_kunnr
	 *            客户编码 可以为空
	 * @return
	 */
	List<SOXX> getSoxxTj(String v_vkorg, String v_vtweg, String v_spart, String v_audat_begin, String v_audat_end,
			String v_kunnr);

	/**
	 * 销售订单明细(用于查询,有交货单明细信息等)
	 */
	List<SOXX> getSoxxMx(String v_vkorg, String v_vtweg, String v_spart, String v_audat_begin, String v_audat_end,
			String v_kunnr);

	/**
	 * 集采数据(用于查询)
	 */
	List<ZLES23> getZles23(String erdatB, String erdatE, String ZEBELN);

	/**
	 * 分公司现款价
	 * 
	 * @param v_matnr
	 *            物料编码 不能为空
	 * @return
	 */
	List<ZA006> getZa006(String v_matnr);

	/**
	 * 分公司调拨评估表
	 * 
	 * @param zc
	 * @return
	 */
	List<Zles29a> getZles29a(Zles29aCriteria zc);

	/**
	 * zj98 机型销存比
	 */
	List<ZJ98> getZlesZJ98(String v_matnr_begin, String v_matnr_end, String v_vtweg, String v_spart,
			String v_bstdk_begin, String v_bstdk_end, String v_cxb_begin, String v_cxb_end);

	/**
	 * 
	 * 机型销售利润
	 */
	List<ITR2> getITR2(ZdmtrxCriteria zc);

	/**
	 * 分公司业绩划拨
	 * 
	 * @param zlb
	 * @param zbukrs
	 * @param zlfgja
	 * @param zlfmon
	 * @return
	 */
	List<ZYJHB> getYJHBList(String zlb, String zbukrs, String zlfgja, String zlfmon);

	List<ZVBALR> getZVBALRList(String v_vkorg, String v_vtweg, String v_spart, String v_audat_begin,
			String v_audat_end, String v_kunnr, String v_vbeln);

	/**
	 * 创建退货销售订单
	 * 
	 * @param orderHeader
	 * @return
	 */
	ReturnInfo createReturnSO(ORDER_HEADER_IN orderHeader);

	/**
	 * 创建正常销售订单
	 * 
	 * @param orderHeader
	 * @return
	 */
	ReturnInfo createSO(ORDER_HEADER_IN orderHeader);

	/**
	 * 修改销售订单 orderHeader 可以取得当前操作人
	 * 
	 * @param orderHeader
	 * @param orderNumber
	 *            61单号
	 * @return
	 */
	ReturnInfo updateSO(ORDER_HEADER_IN orderHeader, String orderNumber);

	/**
	 * 删除已经存在,但未交货的销售订单
	 * 
	 * @param orderNumber
	 *            61单号
	 * @param opName
	 *            当前操作人
	 * @return
	 */
	ReturnInfo createDestorySO(String orderNumber, String opName);
}
