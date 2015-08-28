package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaBbItr2Import;
import com.ebiz.mmt.domain.KonkaBbZj98Import;
import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.domain.KonkaR3OrderLines;
import com.ebiz.mmt.r3.KCXX;
import com.ebiz.mmt.r3.KHXD;
import com.ebiz.mmt.r3.KHYS;
import com.ebiz.mmt.r3.KNA1;
import com.ebiz.mmt.r3.KNVP;
import com.ebiz.mmt.r3.KUKLA;
import com.ebiz.mmt.r3.MARA;
import com.ebiz.mmt.r3.ReturnInfo;
import com.ebiz.mmt.r3.SOXX;
import com.ebiz.mmt.r3.StockCheckResult;
import com.ebiz.mmt.r3.ZA006;
import com.ebiz.mmt.r3.ZLEBIN;
import com.ebiz.mmt.r3.ZLES23;
import com.ebiz.mmt.r3.ZSOF;
import com.ebiz.mmt.r3.ZVBALR;
import com.ebiz.mmt.r3.ZdmtrxCriteria;
import com.ebiz.mmt.r3.Zles29a;
import com.ebiz.mmt.r3.Zles29aCriteria;

/**
 * @author Hu,Hao
 * @version 2013-05-02
 */
public interface R3WebInterfaceService {

	/**
	 * 返回指定日期往后,以及指定分公司编码的R/3客户信息
	 * 
	 * @param in_date
	 *            必须条件
	 * @param bukrs
	 *            必须条件
	 * @param kunnr
	 *            可选条件
	 * @return
	 */
	ReturnInfo<KNA1> getCustomerList(String in_date, String bukrs, String[] kunnr);

	/**
	 * 返回指定的客户合作伙伴信息
	 * 
	 * @param vkorg
	 *            销售组织,必须条件
	 * @param vtweg
	 *            分销渠道,必须条件
	 * @param spart
	 *            产品组,必须条件
	 * @param kunnr
	 *            客户编码 可选条件
	 * @return
	 */
	ReturnInfo<KNVP> getKnvpsList(String vkorg, String vtweg, String spart, String kunnr);

	/**
	 * 返回客户类别信息(数据不会多,百来条)
	 * 
	 * @return
	 */
	ReturnInfo<KUKLA> getkuklaList();

	/**
	 * 物料同步接口
	 * 
	 * @param erdat
	 * @return
	 */
	ReturnInfo<MARA> getMaraList(String erdat);

	/**
	 * 把某张订单上的订单行,一次性进行校验 然后返回各个物料行是否满足
	 * 
	 * @param zbukrs
	 *            不能为空
	 * @param itemList
	 *            //行数据 必填项 :物料,数量
	 * @return此时没有维护工厂和仓位
	 */
	ReturnInfo<StockCheckResult> checkStock(String zbukrs, List<KonkaR3OrderLines> itemList);

	/**
	 * 
	 * @param zwerks
	 *            工厂
	 * @param zlgort
	 *            仓位
	 * @param matnr
	 *            行数据 必填项 :物料
	 * @return 此时有维护工厂和仓位
	 */
	ReturnInfo<StockCheckResult> checkStock2(String zwerks, String zlgort, String matnr);

	/**
	 * 正向销售订单同步至R3接口(新增)
	 * 
	 * @param konkaOrderInfo
	 *            订单信息包括行明细信息
	 * @param opername
	 *            当前操作人
	 * @return
	 */
	ReturnInfo saveKonkaOrderInfo(KonkaOrderInfo konkaOrderInfo, String opername);

	/**
	 * 退货订单同步至R3接口(新增)
	 * 
	 * @param konkaOrderInfo
	 *            订单信息包括行明细信息,需要指定退货原因
	 * @param opername
	 *            当前操作人
	 * @return
	 */
	ReturnInfo saveKonkaOrderInfoReturn(KonkaOrderInfo konkaOrderInfo, String opername);

	/**
	 * 对已经生成61单号的订单进行修改并同步至R3接口(修改)
	 * 
	 * @param konkaOrderInfo
	 *            订单信息(包括头表行表信息)
	 * @param orderNumber
	 *            61单号
	 * @param opername
	 *            当前操作人
	 * @return
	 */
	ReturnInfo modifyKonkaOrderInfo(KonkaOrderInfo konkaOrderInfo, String orderNumber, String opername);

	/**
	 * 删除R3订单
	 * 
	 * @param orderNumber
	 *            R3订单单号
	 * @param opername
	 *            当前操作人
	 * @return ReturnInfo操作结果对象
	 */
	ReturnInfo saveKonkaOrderInfoDestory(String orderNumber, String opername);

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
	ReturnInfo<KHXD> getKhxd(String v_kkber, String v_vkorg, String v_spart, String[] kunnr);

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
	ReturnInfo<KHYS> getKhys(String v_gjahr, String v_monat, String v_spart, String v_vkorg, String[] kunnr);

	/**
	 * 返回库存信息 可查移动平动价,库存总值
	 * 
	 * @param v_werks工厂不能为空
	 * @param v_lgort库存地点
	 *            不能为空
	 * @param v_lgpla仓位
	 *            不能为空
	 * @param v_matnr
	 *            物料编码
	 * @return
	 */
	ReturnInfo<KCXX> getKcxx(String v_werks, String v_lgort, String v_lgpla, String v_matnr);

	/**
	 * 返回分公司库存信息,分公司绑定工厂,工厂再绑定库位
	 * 
	 * @描述 目前是根据输入的参数条件而定,可以考正品90仓,也可以查物流仓
	 * @param zwerks
	 *            not null 工厂
	 * @param zlgort
	 *            not null 库位(仓库地点)
	 * @param zmatnr
	 *            物料
	 * @return
	 */
	ReturnInfo<StockCheckResult> getFGSKC(String zwerks, String zlgort, String zmatnr);

	/**
	 * 返回分公司库存信息,可以详细到机型,和仓位<br/>
	 * y/p/q/d可查,但没有移动平均价信息的值
	 * 
	 * @param zwerks
	 *            not null 工厂
	 * @param zlgort
	 *            not null 库位
	 * @param zlgpla
	 *            not null 仓位
	 * @param zmatnr
	 *            物料
	 * @return
	 */
	ReturnInfo<ZLEBIN> getZles20(String zwerks, String zlgort, String zlgpla, String zmatnr);

	/**
	 * 销售订单明细
	 * 
	 * @param v_vkorg
	 *            销售组织(分公司代码) 不能为空
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
	ReturnInfo<SOXX> getSoxx(String v_vkorg, String v_vtweg, String v_spart, String v_audat_begin, String v_audat_end,
			String v_kunnr);

	ReturnInfo<SOXX> getSoxxMX(String v_vkorg, String v_vtweg, String v_spart, String v_audat_begin,
			String v_audat_end, String v_kunnr);

	/**
	 * 分公司调拨评估表
	 * 
	 * @param zc
	 * @return
	 */
	ReturnInfo<Zles29a> getZles29a(Zles29aCriteria zc);

	/**
	 * 转储交货单情况
	 * 
	 * 由于sap接口原因,此接口数据跟sap事务码zles23不能完全一致.甚至sap的接口也是异常百出
	 * 
	 * 1.存在(ebeln为空,ebelp为空-->根据机型汇总的原因) kunnr为空的数据
	 * 
	 * 2.ebeln为空时,机型都没有,
	 * 
	 * 最大原因,有可能是同步的时候,这些转储单还没有后面的一系列操作. 我们定时更新的单据为(有转储单号ebeln,有ebelp)
	 * 
	 * 
	 * 3.此接口会比zles23按时间取到的单早
	 * 
	 * 
	 * 所以有猜考意义的数据: MATNR 机型 MENGE STO数量转储单的数量 WAMNG STO发货数 WEMNG STO收货数
	 * LFIMG实际已交货量(已汇总)<br>
	 * 
	 * 当机型分多次交货时,后面的交货单号也变得不准确
	 * 
	 * 2015-01-23 by zhouhaojie
	 * 
	 * 
	 * 
	 * @param erdat_b
	 *            转储单凭证日期
	 * @param erdat_e
	 *            转储单凭证日期
	 * @return
	 */
	ReturnInfo<ZLES23> getZles23(String erdat_b, String erdat_e, String ZEBELN);

	/**
	 * zj98 机型销存比
	 * 
	 * @param v_matnr_begin
	 *            机型起
	 * @param v_matnr_end
	 *            机型末
	 * @param v_vtweg
	 *            销售渠道
	 * @param v_spart
	 *            产品组
	 * @param v_bstdk_begin
	 *            销售日期起
	 * @param v_bstdk_end
	 *            销售日期末
	 * @param v_cxb_begin
	 *            存销比起
	 * @param v_cxb_end
	 *            存销比末
	 * @return
	 */
	ReturnInfo<KonkaBbZj98Import> getZlesZJ98(String v_matnr_begin, String v_matnr_end, String v_vtweg, String v_spart,
			String v_bstdk_begin, String v_bstdk_end, String v_cxb_begin, String v_cxb_end);

	/**
	 * 
	 * 机型销售利润zj98
	 */
	ReturnInfo<KonkaBbItr2Import> getITR2(ZdmtrxCriteria zc);

	/**
	 * 
	 * ZSOF,通过61单号查找R3物流信息
	 */
	ZSOF getR3Delivery(Long v_vbeln);

	ReturnInfo<ZA006> getZa006(String v_matnr);
	
	ReturnInfo<ZVBALR> getZVBALRList(String v_vkorg, String v_vtweg, String v_spart, String v_audat_begin,
			String v_audat_end, String v_kunnr, String v_vbeln);
}
