<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>产品销售 &gt;  销售记录</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：产品销售 &gt;  销售记录</div>
  <div class="rtabcont1">
    <form action="<c:url value='/jxc/JxcSellBill.do'/>" method="post">
      <input type="hidden" name="method" value="list" />
      <input type="hidden" name="keySeq" id="keySeq" value="${af.map.keySeq}" />
      <input type="hidden" name="search_flag" value="search_flag" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
        <tr>
          <td><strong class="fb">销售日期：</strong>
            <input type="text" name="sell_date_ge" id="sell_date_ge" value="${af.map.sell_date_ge}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" class="webinput" style="cursor:pointer;width:80px;"/>
            &nbsp;至
            <input type="text" name="sell_date_le" id="sell_date_le" value="${af.map.sell_date_le}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" class="webinput" style="cursor:pointer;width:80px;"/>
            <strong class="fb">消费者：</strong>
            <input type="text" name="customer_name" id="customer_name"  class="webinput" />
            <!--
            <select name="customer_id">
              <option value="">选择客户</option>
              <c:forEach items="${customerList}" var="cur" >
                <c:if test="${cur.id == af.map.customer_id}">
                  <option value="${cur.id }" selected="selected">${cur.name }</option>
                </c:if>
                <c:if test="${cur.id != af.map.customer_id}">
                  <option value="${cur.id }" >${cur.name }</option>
                </c:if>
              </c:forEach>
            </select>
            -->
            <input class="bgSearch" type="submit" name="button" id="button" value="搜 索" /><span class="jxcTip">默认不显示数据，点搜索显示数据</span></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp"%>
    <!--   <input class="bgButton" type="button"  style="display: none;"  value="删除所选" onclick="confirmDeleteAll(this.form);"/>-->
    <!--    &nbsp;-->
    <input class="bgButtonAdd" name="input" type="button" id="gotoAdd"  value="新 增" />
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
      <tr>
        <th>行号</th>
        <th>销售日期</th>
        <th>销售单号</th>
        <th>消费者姓名</th>
        <th>联系电话</th>
        <th>应收金额</th>
        <th>实收金额</th>
        <!--
        <th>说明</th>
        -->
        <th>查看明细</th>
      </tr>
      <c:forEach items="${sellBillList}" var="cur" varStatus="status">
        <tr>
          <td align="center" bgcolor="#fff2dc">${status.count}</td>
          <td align="center" ><fmt:formatDate pattern="yyyy-MM-dd" value="${cur.sell_date}" /></td>
          <td  align="center">${fn:escapeXml(cur.sn)}</td>
          <td align="center" >${fn:escapeXml(cur.map.customer_name)}</td>
          <td align="center" >${fn:escapeXml(cur.map.tel)}</td>
          <td align="center" style="padding-right: 5px;"><fmt:formatNumber type="currency" value="${cur.money}" /></td>
          <td align="center" style="padding-right: 5px;"><fmt:formatNumber type="currency" value="${cur.pay_money}" /></td>
          <!-- <td align="center" >${fn:escapeXml(cur.remarks)}</td> -->
          <td align="center" ><a href="#" onclick="go('${ctx}/jxc/JxcSellBill.do?method=sellShow&sell_bill_id='+${cur.id})">查看明细</a></td>
        </tr>
      </c:forEach>
      <tr>
        <td align="center" bgcolor="#fff2dc"><font class="redbig" style="color: red;">合计</font></td>
        <td colspan="4" bgcolor="#ededed"><font class="red" style="color: red;"></font></td>
        <td bgcolor="#ededed" align="right" style="padding-right: 5px;"><font  style="color: red;">
          <fmt:formatNumber type="currency" value="${countMoney}" />
          </font></td>
        <td bgcolor="#ededed" align="right" style="padding-right: 5px;"><font  style="color: red;">
          <fmt:formatNumber type="currency" value="${countPayMoney}" />
          </font></td>
        <td bgcolor="#ededed"></td>
      </tr>
    </table>
  </div>
  <c:url value="" var="expPara">
    <c:param name="method" value="toExcel"/>
    <c:param name="type" value="xsdlist"/>
    <c:param name="keySeq" value="${af.map.keySeq}"/>
    <!--<c:param name="customer_id" value="${af.map.customer_id}"/>-->
    <c:param name="customer_name" value="${af.map.customer_name}"/>
    <c:param name="sell_date_ge" value="${af.map.sell_date_ge}"/>
    <c:param name="sell_date_le" value="${af.map.sell_date_le}"/>
  </c:url>
  <html-el:form action="/JxcSellBill${expPara}">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
      <tr>
        <!--
    <td height="26" style="padding-left:20px;"><div class="left"><input name="button" type="button" class="bgButton" id="print" value="打印" />
      <input name="button3" type="button" class="bgButton" id="toExcel" value="导出" />
    </div>
    </td>
    
  -->
      </tr>
    </table>
  </html-el:form>
  <c:if test="${not empty sellBillList}">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="36" align="center" ><form id="bottomPageForm" name="bottomPageForm" method="post" action="<c:url value='/jxc/JxcSellBill.do?method=list'/>">
            <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
                  <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            //pager.addHiddenInputs("customer_id", "${af.map.customer_id}");
            pager.addHiddenInputs("customer_name", "${af.map.customer_name}");
            pager.addHiddenInputs("sell_date_ge", "${af.map.sell_date_ge}");
            pager.addHiddenInputs("sell_date_le", "${af.map.sell_date_le}");
            pager.addHiddenInputs("keySeq", "${af.map.keySeq}");
            pager.addHiddenInputs("search_flag", "${af.map.search_flag}");
            document.write(pager.toString());
            </script></td>
              </tr>
            </table>
          </form></td>
      </tr>
    </table>
  </c:if>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
	$(document).ready(function(){
		$("#gotoAdd").click(function(){
			go("${ctx}/jxc/JxcSellBill.do?method=add");
		});
		var f=document.forms[0];
		 $(".bgSearch").click(function(){
		    	var s_time = $("#sell_date_ge").val();
				var e_time = $("#sell_date_le").val();
				if ("" != s_time && "" != e_time && s_time > e_time) {
					alert("开始日期不能大于结束日期！");
					return false;
				}
				if(!Validator.Validate(f, 1)){
					return false;
				}
		    });
	});

	function go(url){
		if(url.indexOf('?')>-1){
			url = url+"&keySeq=${af.map.keySeq}";
		}else{
			url = url+"?keySeq=${af.map.keySeq}";
		}
		location.href=url;
	}

	var type = "xsdlist";
	var keySeq = "${af.map.keySeq}";
	//var customer_id = "${af.map.customer_id}";
	var customer_name = "${af.map.customer_name}";
    var sell_date_ge = "${af.map.sell_date_ge}";
    var sell_date_le = "${af.map.sell_date_le}";
	var param ="keySeq=" + keySeq + "&type=" + type  + "&customer_name=" + customer_name + "&sell_date_ge=" + sell_date_ge + "&sell_date_le=" + sell_date_le;
	
	$("#toExcel").click(function(){
		this.form.submit();
		window.open("${ctx}/client/manager/JxcSellBill.do?method=toExcel&" + param, "下载页面", "height=100,width=100");
	});
	
	$("#print").click(function(){
		window.showModalDialog("${ctx}/jxc/JxcSellBill.do?method=print&"+param, window, "dialogWidth:900px;status:no;dialogHeight:580px"); 
	});
//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>