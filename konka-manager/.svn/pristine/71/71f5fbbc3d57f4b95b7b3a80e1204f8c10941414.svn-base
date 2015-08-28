<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title> 集采数据</title>

<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />

<style type="text/css">
	.tips_div {text-align:left; background-color: yellow;width:470px;  border: 1px ;color: red ; border-style: solid; }
</style>
 
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/R3InterfaceTest.do">
      <html-el:hidden property="method" value="list16" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td>
          	<strong class="fb">开始日期：</strong>
          	<html-el:text property="erdat_b" size="20" maxlength="20" styleId="erdat_b" styleClass="webinput" onclick="new Calendar(1990, 2021, 0).show(this);"/>
          </td>
          <td>
          	<strong class="fb">结束日期：</strong>
          	<html-el:text property="erdat_e" size="20" maxlength="20" styleId="erdat_e" styleClass="webinput" onclick="new Calendar(1990, 2021, 0).show(this);"/>
          </td>
          <td><input name="button" type="submit" class="bgSearch" id="s_button" value="搜 索"/></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <td nowrap="nowrap" align="left" >凭证号</td>
          <td nowrap="nowrap" align="left" >机型</td>
          <td nowrap="nowrap" align="left" >STO数量</td>
          <td nowrap="nowrap" align="left" >交货单数</td>
          <td nowrap="nowrap" align="left" >STO发货数</td>
          <td nowrap="nowrap" align="left" >STO收货数</td>
          <td nowrap="nowrap" align="left" >发货仓位库存</td>
          <td nowrap="nowrap" align="left" >收货仓位库存</td>
          <td nowrap="nowrap" align="left" >发货仓位</td>
          <td nowrap="nowrap" align="left" >收货仓位</td>
          <td nowrap="nowrap" align="left" >客户名称</td>
          <td nowrap="nowrap" align="left" >请求号码</td>
          <td nowrap="nowrap" align="left" >发机单号</td>
          <td nowrap="nowrap" align="left" >项目</td>
          <td nowrap="nowrap" align="left" >订单数量</td>
          <td nowrap="nowrap" align="left" >总部发货数量</td>
          <td nowrap="nowrap" align="left" >交货单创建日期</td>
          <td nowrap="nowrap" align="left" >发货日期</td>
          <td nowrap="nowrap" align="left" >分公司已收数量</td>
          <td nowrap="nowrap" align="left" >收货日期</td>
          <td nowrap="nowrap" align="left" >运单号</td>
          <td nowrap="nowrap" align="left" >运输开始日期</td>
          <td nowrap="nowrap" align="left" >运输结束日期</td>
          <td nowrap="nowrap" align="left" >约定在途时间</td>
          <td nowrap="nowrap" align="left" >承运商</td>
          <td nowrap="nowrap" align="left" >车牌</td>
          <td nowrap="nowrap" align="left" >联系电话</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="left" nowrap="nowrap">${cur.EBELN}</td>
            <td align="left" nowrap="nowrap">${cur.MATNR}</td>
            <td align="left" nowrap="nowrap">${cur.MENGE}</td>
            <td align="left" nowrap="nowrap">${cur.LFIMG}</td>
            <td align="left" nowrap="nowrap">${cur.WAMNG}</td>
            <td align="left" nowrap="nowrap">${cur.WEMNG}</td>
            <td align="left" nowrap="nowrap">${cur.LABST}</td>
            <td align="left" nowrap="nowrap">${cur.LABST1}</td>
            <td align="left" nowrap="nowrap">${cur.RESLO}</td>
            <td align="left" nowrap="nowrap">${cur.LGORT}</td>
            <td align="left" nowrap="nowrap">${cur.KUNNR}</td>
            <td align="left" nowrap="nowrap">${cur.BEDNR}</td>
            <td align="left" nowrap="nowrap">${cur.VBELN}</td>
            <td align="left" nowrap="nowrap">${cur.POSNR}</td>
            <td align="left" nowrap="nowrap">${cur.LFIMG1}</td>
            <td align="left" nowrap="nowrap">${cur.MENGE1}</td>
            <td align="left" nowrap="nowrap">${cur.ERDAT}</td>
            <td align="left" nowrap="nowrap">${cur.BUDAT1}</td>
            <td align="left" nowrap="nowrap">${cur.MENGE2}</td>
            <td align="left" nowrap="nowrap">${cur.BUDAT2}</td>
            <td align="left" nowrap="nowrap">${cur.TKNUM}</td>
            <td align="left" nowrap="nowrap">${cur.DATBG}</td>
            <td align="left" nowrap="nowrap">${cur.DATEN}</td>
            <td align="left" nowrap="nowrap">${cur.FAHZTD}</td>
            <td align="left" nowrap="nowrap">${cur.NAME1}</td>
            <td align="left" nowrap="nowrap">${cur.SIGNI}</td>
            <td align="left" nowrap="nowrap">${cur.TPBEZ}</td>
          </tr>
        </c:forEach>
      </table>
    <br />
    
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="../commons/scripts/jquery.js"></script>
<script type="text/javascript" >
	$(document).ready(function(){
	$("#s_button").click(function(){
		if($("#erdat_b").val()==null || $("#erdat_b").val()==""){
			alert("开始日期不能为空");
			return false ;
		}
		if($("#erdat_e").val()==null || $("#erdat_e").val()==""){
			alert("结束日期不能为空");
			return false ;
		}
		return true;
	});
	}) ;
</script>
</body>
</html>