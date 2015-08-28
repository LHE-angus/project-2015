<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<title>“节能产品惠民工程”补贴确认单</title>
<style type="text/css">
body {
	height:100%;
	width:100%;
}
.word {
	width:556px;
	font-size:16px;
	margin:10px auto;
	font-family:'仿宋_GB2312', '宋体';
}
.word p {
	text-indent:2em;
	margin:10px;
}
.word div {
	margin:10px;
}
.word p.title {
	text-align:center;
	font-size:22px;
	padding:20px 0px;
	font-weight:800;
	font-family:'黑体', '仿宋_GB2312', '宋体';
	text-indent:0px;
}
.u {
	text-decoration:underline;
}
.l {
	float:left;
}
.r {
	float:right;
}
</style>
</head>
<body>
<div class="word">
  <p class="title">“节能产品惠民工程”补贴确认单</p>
  <p style="text-align: left;" >本人（${jxcCustomer.name}）于<span class="u">
    <fmt:formatDate pattern="yyyy年MM月dd日" value="${jxcSellBill.sell_date}" />
    </span>购买了享受国家财政补贴的（ <span class="u">${jnhmPdContents.sub_type_name}</span>） <span class="u">${jxcSellBillDetails.count}</span> 台。详细购买信息如下：</p>
  联系电话：（<span class="u">${jxcCustomer.tel}</span>），地址：（<span class="u">${jxcCustomer.addr}</span><span class="u"></span> ），购买产品型号为：<span class="u">${jxcSellBillDetails.brand_name}、${jxcSellBillDetails.pd_name}</span> ，享受国家节能补贴金额：<span class="u">${jxcSellBillDetails.allowance_money}</span>元，产品唯一编码：<span class="u">${jxcSellBillDetails.pd_unique_code}</span>。
  <p style="text-align: left;" >本人承诺以上信息真实有效，自愿提供购买发票复印件、身份证（正、反面）复印件，且配合国家开展的“节能产品惠民工程”相关专项核查，不配合核查工作将要求返还补贴。</p>
  <div><span class="l">客户签名：<span class="u">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></span><span class="r">（${af.map.userShopName}） </span></div>
  <br/>
  <div><span class="l">日    期: <span class="u">&nbsp;&nbsp;&nbsp;&nbsp;</span>年<span class="u">&nbsp;&nbsp;&nbsp;&nbsp;</span>月<span class="u">&nbsp;&nbsp;&nbsp;&nbsp;</span>日</span> <span class="r">日    期：<span class="u">&nbsp;&nbsp;&nbsp;&nbsp;</span>年 <span class="u">&nbsp;&nbsp;&nbsp;&nbsp;</span>月<span class="u">&nbsp;&nbsp;&nbsp;&nbsp;</span>日</span></div>
</div>
<br/><br/><br/><br/>
<div align="center">
  <input name="button" type="button" class="bgButtonPrint" value="打印" onclick="this.style.display='none';window.print();"/>
  <input name="button" type="button" class="bgButtonBack" value="返回" onclick="history.back();return false;" />
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>