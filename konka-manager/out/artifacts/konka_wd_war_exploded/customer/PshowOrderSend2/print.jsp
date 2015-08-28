<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style media="print">  
.Noprint  {DISPLAY:  none;}  
</style> 
</head>
<body>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div id="print" class="rtabcont2" align="center" title="配货单" style="margin-top:4px;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td colspan="4" height="36" align="center"><span style="font-weight:bold;font-size:20px;">配货单</span></td>
      </tr>
      <tr>
        <td colspan="3" width="78%">&nbsp;</td>
        <td align="left">
          <input name="chk_pic_print" type="checkbox" id="chk_pic_print" ></input>
          <label class="label_pic_print" for="chk_pic_print"><b>打印图片</b></label>
          </td>
      </tr>
       <tr>
        <td colspan="3" width="78%">&nbsp;</td>
        <td align="left"><b>订单号：</b>
          <c:out value="${af.map.trade_index}" />
          </td>
      </tr>
      <tr>
        <td colspan="3" width="78%">&nbsp;</td>
        <td align="left" ><b>客&nbsp;&nbsp;户：</b><c:out value="${af.map.buyer_name}" /></td>
      </tr>
      <tr>
        <td colspan="3" width="78%">&nbsp;</td>    
        <td align="left" ><b>电&nbsp;&nbsp;话：</b>
          <c:out value="${af.map.buyer_mp}" /></td>
       </tr>
      <tr>
        <td colspan="3" width="78%">&nbsp;</td>     
        <td align="left" ><b>订单日期：</b>
          <fmt:formatDate value="${af.map.add_date}" pattern="yyyy年M月d日"/></td>
      </tr>
      <tr>
        <td colspan="3" width="78%">&nbsp;</td> 
        <td align="left" ><b>收货地址：</b>
          <c:out value="${af.map.buyer_addr}" /></td>
      </tr>
      <tr>
        <td colspan="4">&nbsp;</td> 
      </tr>
    </table>
    <table width="100%" border="1" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td class="title_item" width="5%" align="center">序号</td>
        <td class="title_item" width="15%" style="text-align: center;">产品型号</td>
		<td class="title_item" width="8%" style="text-align: center;">数量</td>
        <td class="title_item" width="8%" style="text-align: center;">单价</td>
        <td class="title_item" width="8%" nowrap="nowrap" style="text-align: center;">增值服务</td>
        <td class="title_item" width="8%" style="text-align: center;">金额</td>
      </tr>
      <c:forEach var="cur" items="${pshowOrdeDetails}" varStatus="vs">
        <tr>
        <td nowrap="nowrap" style="text-align: center;">${vs.count}</td>
        <td nowrap="nowrap" style="text-align: center;"> <img id="pic_${vs.count}" src="${ctx}/${fn:substringBefore(cur.map.main_pic, '.')}_240.jpg" width="50px" height="50px" style="display: none" /> 
        ${cur.map.pd_sn }</td>
        <td  nowrap="nowrap" style="text-align: center;">${cur.num }</td>
		<td  nowrap="nowrap" style="text-align: center;">${cur.price }</td>
		<td align="center" style="text-align: center;">
						 <c:forEach items="${bddetailsList}" var="cu">
						 	<c:if test="${cu.details_id eq cur.bill_item_id}">
						 		${cu.goods_name} ￥${cu.price}<br />
						 	</c:if>
						 </c:forEach>
				  </td>
        <td  nowrap="nowrap" style="text-align: center;">${cur.total_price}</td>
        </tr>
      </c:forEach>
      <tr>
        <td align="center" colspan="5"><font class="blue">金额合计（大写）：<span id="cnn_money"></span></font></td>
        <td  align="center" style="mso-number-format:'\@';"><span class="kz-price">
          <fmt:formatNumber type="currency" value="${t_price}" />
          </span></td>
      </tr>
    </table>
    <table width="100%" cellpadding="0" cellspacing="0" style="margin-top:20px;margin-bottom:10px;">
      <tr>
        <td align="right" height="25" width="40%" nowrap="nowrap"><b>签字：</b>
          <input type="text" style="border-bottom:1px solid #000;border-top:1px solid #fff;border-left:1px solid #fff;border-right:1px solid #fff;" size="20" /></td>
      </tr>
      <tr>
        <td align="right" height="25" nowrap="nowrap"><b>日期：</b>
          <input type="text" style="border-bottom:1px solid #000;border-top:1px solid #fff;border-left:1px solid #fff;border-right:1px solid #fff;text-align:center;" size="20" value="<fmt:formatDate value="${today}" pattern="yyyy年M月d日" />
          " /></td>
      </tr>
    </table>
  </div>
  <div align="left" style="margin-left:10px;margin-bottom:20px;">
    <input class="but5" type="button"  value="返回" onclick="history.back();return false;" />
    <input class="but_excel" type="button"  value="打印" onclick="win_print();" />
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#cnn_money").html(toUpperRMB("${t_price}"));

	var len = "${fn:length(pshowOrdeDetails)}";

	$("#chk_pic_print").click(function(){
		if($(this).attr("checked")=="checked"){
			for (var i = 1; i < len+1; i++) {
				$("#pic_" + i).css("display",'inline');;
			}
		}else{
			for (var i = 1; i < len+1; i++) {
				$("#pic_" + i).css("display",'none');;
			}
		}
	});
});

function win_print(){
	prnhtml=$("#print").html();
	var oWin=window.open("","_blank");  

	 var strPrint="<style media=\"print\">\n"; 
    strPrint=strPrint + ".Noprint  {DISPLAY:  none;}\n"; 
    strPrint=strPrint + " </style> \n"; 
    strPrint=strPrint+" <div class='Noprint'> <h4 style=’font-size:18px; text-align:center;’>打印预览区</h4></div>\n";  
    strPrint=strPrint + "<script type=\"text/javascript\">\n";  
    strPrint=strPrint + "function printWin()\n";  
    strPrint=strPrint + "{";  
    strPrint=strPrint +    "var oWin=window.open(\"\",\"_blank\");\n";  
    strPrint=strPrint + "oWin.document.write(document.getElementById(\"content\").innerHTML);\n";  
    strPrint=strPrint + "oWin.focus();\n";  
    strPrint=strPrint + "oWin.document.close();\n";  
    strPrint=strPrint + "oWin.print()\n";  
    strPrint=strPrint + "oWin.close()\n";  
    strPrint=strPrint + "}\n";  
    strPrint=strPrint + "<\/script>\n";  
      
    strPrint=strPrint + "<hr size='1'/>\n";  
    strPrint=strPrint + "<div id=\"content\">\n";  
    strPrint=strPrint + prnhtml + "\n";  
    strPrint=strPrint + "</div>\n";  
    strPrint=strPrint + "<hr size='1' />\n";  
    strPrint=strPrint + "<div class='Noprint' style='text-align:center'><button onclick='window.print()' style='padding-left:4px;padding-right:4px;'>打  印</button><button onclick='window.opener=null;window.close();' style='padding-left:4px;padding-right:4px;'>关  闭</button></div>\n";  
    oWin.document.write(strPrint);  
    oWin.focus();  
    oWin.document.close();  
}

//]]></script>
<jsp:include page="message.jsp" flush="true" />
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
