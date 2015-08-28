<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<title>进货记录</title>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body>
<div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：进货管理 &gt; 进货记录</div>
<html-el:form action="/JxcStockBill.do">
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
      <tr>
        <td height="36" align="left" style="padding-left:5px;"><input type="hidden" name="method" value="list" />
          <input type="hidden" name="keySeq" id="keySeq" value="${af.map.keySeq}" />
          <input type="text" name="add_date_gt" id="add_date_gt" class="webinput" value="${add_date_gt}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;width:80px;" />
          &nbsp;至
          <input type="text" name="add_date_lt" id="add_date_lt" class="webinput" value="${add_date_lt}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;width:80px;"/>
          <select name="supplier_id"  class="bdfont">
            <option value="">选择供应商</option>
            <c:forEach items="${listJxcSupplier}" var="cur">
              <c:if test="${cur.id == supplier_id}">
                <option selected="selected" value="${cur.id }">${cur.name }</option>
              </c:if>
              <c:if test="${cur.id != supplier_id}">
                <option value="${cur.id }">${cur.name }</option>
              </c:if>
            </c:forEach>
          </select>
          <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" />
          &nbsp;<span id="searchTip" class="jxcTip">默认不显示数据，点击搜索显示数据</span></td>
      </tr>
    </table>
  </div>
</html-el:form>
<div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
  <input name="input" type="button" id="gotoAdd" class="bgButtonAdd" value="新 增" />
</div>
<div class="rtabcont1">
  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
    <tr>
      <th width="5%">行号</th>
      <th width="10%">进货日期</th>
      <th width="12%">单据编号</th>
      <th width="15%">供应商名称</th>
      <th width="13%">应付金额(元)</th>
      <th width="13%">实付金额(元)</th>
      <th width="20%">说明</th>
      <th width="12%">操作</th>
    </tr>
    <c:forEach items="${listJxcStockBill}" var="cur" varStatus="status">
      <tr>
        <td align="center" bgcolor="#fff2dc">${status.count }</td>
        <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.in_date }" pattern="yyyy-MM-dd"></fmt:formatDate></td>
        <td align="center">${cur.sn}</td>
        <td align="center">${cur.map.supplier_name }</td>
        <td align="center"><fmt:formatNumber value="${cur.pay_money }" pattern="0.00" /></td>
        <td align="center"><fmt:formatNumber value="${cur.paid_money }" pattern="0.00" /></td>
        <td align="center"><span title="${fn:escapeXml(cur.remarks)}">${fn:escapeXml(fnx:abbreviate(cur.remarks, 2 * 10, '...'))}</span></td>
        <td align="center">
          <span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'JxcStockBill.do', 'view','&keySeq=${af.map.keySeq}&stock_bill_id=${cur.id}&'+$('#bottomPageForm').serialize())">查看明细</span></td>
      </tr>
    </c:forEach>
    <tr>
      <td align="center" bgcolor="#fff2dc"><font class="redbig" style="color: red;">合计</font></td>
      <td colspan="3" bgcolor="#ededed"><font class="red" style="color: red;"></font></td>
      <td bgcolor="#ededed"></td>
      <td bgcolor="#ededed"><font  style="color: red;">总计：
        <fmt:formatNumber value="${countMoney}" pattern="0.00" />
        </font></td>
      <td bgcolor="#ededed"></td>
      <td bgcolor="#ededed"></td>
    </tr>
  </table>
  <c:url value="" var="expPara">
    <c:param name="method" value="toExcel1"/>
    <c:param name="type" value="jhdlist"/>
    <c:param name="keySeq" value="${af.map.keySeq}"/>
    <c:param name="supplier_id" value="${af.map.supplier_id}"/>
    <c:param name="add_date_lt" value="${af.map.add_date_lt}"/>
    <c:param name="add_date_gt" value="${af.map.add_date_gt}"/>
  </c:url>
  <!--<html-el:form action="/JxcStockBill.do${expPara}">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;" >
      <tr>
        <td height="26"><div class="left"> 
            <input name="button" type="button" class="bgButton" id="printView" value="打印" onclick="openChild()"/>
            <input name="button" type="button" class="bgButton" id="toExcel" value="导出" />
          </div></td>
      </tr>
    </table>
  </html-el:form>
  -->
  <c:if test="${not empty listJxcStockBill}">
  <div class="rtabcont3">
	  <form id="bottomPageForm" name="bottomPageForm" method="post" action="JxcStockBill.do?method=list">
	  <table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">
	    <tr>
	        <td height="40" align="center"><script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("supplier_id", "${af.map.supplier_id}");
	            pager.addHiddenInputs("add_date_gt", "${add_date_gt}");
	            pager.addHiddenInputs("add_date_lt", "${add_date_lt}");
	            pager.addHiddenInputs("keySeq", "${af.map.keySeq}");
	            document.write(pager.toString());
	            </script></td>
	    </tr>
	  </table>
	  </form>
  </div>
  </c:if>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript">//<![CDATA[
	$(document).ready(function(){
		$("#gotoAdd").click(function(){
			window.location.href = "${ctx}/jxc/JxcStockBill.do?method=add&keySeq=${af.map.keySeq}";
		})
		var f=document.forms[0];
		 $(".bgSearch").click(function(){
		    	var s_time = $("#add_date_gt").val();
				var e_time = $("#add_date_lt").val();
				if ("" != s_time && "" != e_time && s_time > e_time) {
					alert("开始日期不能大于结束日期！");
					return false;
				}
				if(!Validator.Validate(f, 1)){
					return false;
				}
		    });
	});

	var type = "jhdlist";
	var keySeq = "${af.map.keySeq}";
	var supplier_id = "${af.map.supplier_id}";
	var add_date_lt = "${add_date_lt}";
	var add_date_gt = "${add_date_gt}";
	var param = "type=" + type + "&keySeq=" + keySeq + "&supplier_id=" + supplier_id+"&add_date_lt="+add_date_lt+"&add_date_gt="+add_date_gt;
	function openChild(){
	    window.showModalDialog("?method=print&"+param, window, "dialogWidth:900px;status:no;dialogHeight:580px");
	}
	$("#toExcel").click(function(){
		this.form.submit();
		//window.open("?method=toExcel1&" + param, "下载页面", "height=100,width=100");
	});
		
//]]></script> 
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>