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
<link href="${ctx}/commons/styles/select2.min.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	.rtable td {
		padding:3px 0 6px 0;
	}
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
	<html-el:form action="/manager/JBill.do">
		<html-el:hidden property="method" value="listForPrint" />
		<html-el:hidden property="mod_id" styleId="mod_id" />
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable1">
        	<tr>
        		<td nowrap="nowrap" align="right"><strong class="fb">创建日期：</strong></td>
        		<td>
          			<html-el:text property="opr_date_gt" size="10" maxlength="10" readonly="true" title="点击选择日期" styleClass="Wdate" style="cursor:pointer;" onclick="WdatePicker({readOnly:true})" />
            		-
            		<html-el:text property="opr_date_lt" size="10" maxlength="10" readonly="true" title="点击选择日期" styleClass="Wdate" style="cursor:pointer;" onclick="WdatePicker({readOnly:true})" />
            	</td>
        		<td nowrap="nowrap" align="right"><strong class="fb">销售单号：</strong></td>
        		<td><html-el:text property="bill_sn_like" styleClass="webinput" styleId="bill_sn_like" maxlength="30" size="20"/></td>
	        	<td nowrap="nowrap" align="right"><strong class="fb">单据类型：</strong></td>
	        	<td colspan="3">
	        		<html-el:select property="bill_type" styleId="bill_type" style="width:100px;">
		              <html-el:option value="">请选择</html-el:option>
		              <html-el:option value="20">零售</html-el:option>
		              <html-el:option value="21">零售退货</html-el:option>
		              <html-el:option value="30">零售通</html-el:option>
		            </html-el:select>
	        	</td>
	        </tr>
	        <tr>
	        	<td nowrap="nowrap" align="right"><strong class="fb">销售单位：</strong></td>
	        	<td><html-el:text property="p_name_like" styleClass="webinput" styleId="p_name_like" maxlength="30" size="20"/></td>
	        	<td nowrap="nowrap" align="right"><strong class="fb">销售单位编码：</strong></td>
	        	<td><html-el:text property="p_id_like" styleClass="webinput" styleId="p_id_like" maxlength="30" size="20"/></td>
	        	<td nowrap="nowrap" align="right"><strong class="fb">上报人：</strong></td>
	        	<td colspan="3"><html-el:text property="report_name_like" styleClass="webinput" styleId="report_name_like" maxlength="30" size="20"/></td>
	        </tr>
	        <tr>
	        	<td nowrap="nowrap" align="right"><strong class="fb">商品类型：</strong></td>
	          	<td>
	          		<html-el:select property="type_id" styleClass="type_id" styleId="type_id" onchange="selectGoodType(this)" style="width:100px;">
		       		<html-el:option value="">请选择</html-el:option>
	         			<c:forEach var="cur" items="${jBaseTypes}" varStatus="vs">
	         				<html-el:option value="${cur.type_id}">${cur.type_name}</html-el:option>
	         			</c:forEach>
		        	</html-el:select>
	          	</td>
	          	<td nowrap="nowrap" align="right"><strong class="fb">机型：</strong></td>
	          	<td>
	          		<html-el:text property="goods_name_like" styleClass="webinput" styleId="goods_name_like" maxlength="30" size="20"/>
	          		<!--<html-el:select property="goods_id" styleId="goods_id" styleClass="goods_id">
	            		<html-el:option value="">请选择</html-el:option>
	            			<c:forEach var="cur" items="${jBaseGoodsList}" varStatus="vs">
					    		<html-el:option value="${cur.goods_id}">${cur.goods_name}</html-el:option>
					    	</c:forEach>
					</html-el:select>-->
	          	</td>
	          	<td nowrap="nowrap" align="right"><strong class="fb">出货仓库：</strong></td>
	          	<td colspan="3">
	          		<html-el:select property="store_id" styleId="store_id" styleClass="store_id">
	            		<html-el:option value="">请选择</html-el:option>
	            			<c:forEach var="cur" items="${storesList}" varStatus="vs">
					    		<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
					    	</c:forEach>
					</html-el:select>
	          	</td>
	        </tr>
	        <tr>
	        	<td nowrap="nowrap" align="right"><strong class="fb">数据来源：</strong></td>
	          	<td>
	          		<html-el:select property="data_type" styleId="data_type" styleClass="data_type">
	            		<html-el:option value="">请选择</html-el:option>
	            		<html-el:option value="1">客户端</html-el:option>
	            		<html-el:option value="2">零售通</html-el:option>
					</html-el:select>
	          	</td>
	        	<td nowrap="nowrap" align="right"><strong class="fb">顾客姓名：</strong></td>
	        	<td><html-el:text property="customer_name_like" styleClass="webinput" styleId="customer_name_like" maxlength="30" size="20"/></td>
	        	<td nowrap="nowrap" align="right"><strong class="fb">顾客电话：</strong></td>
	        	<td><html-el:text property="mobile_like" styleClass="webinput" styleId="mobile_like" maxlength="30" size="20"/></td>
	        	<td nowrap="nowrap" colspan="2" align="center">
	        		<input name="button" type="submit" class="bgSearch" id="button" value="搜 索" style="height: 23px"/>&nbsp;
	        		<input type="button" class="but_excel" onclick="if(confirm('提示，您确认导出数据？')){doNeedMethod(null, 'JBill.do', 'listForPrint', 'export=true&' + $('#bottomPageForm').serialize())}" value="导出" />
	        	</td>
	        </tr>
	      </table>
			</html-el:form>
		</div>
  <div class="rtabcont1">
    <%@ include
	file="/commons/pages/messages.jsp"%>
  </div>
  <div class="rtabcont1" style="font-weight:700;color:#F00;"></div>
  <div class="rtabcont1" style="overflow-x: auto;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="rtable2">
      <tr class="tabtt1">
        <td width="5%" align="center" nowrap="nowrap">序号</td>
        <td align="center" nowrap="nowrap">销售单号</td>
        <td align="center" nowrap="nowrap">单据类型</td>
        <td align="center" nowrap="nowrap">销售日期</td>
        <td align="center" nowrap="nowrap">销售单位</td>
        <td align="center" nowrap="nowrap">销售单位编码</td>
        <td align="center" nowrap="nowrap">上报人</td>
        <td width="8%" nowrap="nowrap" align="center">商品类型</td>
        <td width="8%" nowrap="nowrap" align="center">机型</td>
        <td width="3%" nowrap="nowrap" align="center">尺寸</td>
        <td width="3%" nowrap="nowrap" align="center">数量</td>
        <td width="6%" nowrap="nowrap" align="center">单价</td>
        <td width="6%" nowrap="nowrap" align="center">销售参考单价</td>
        <td width="6%" nowrap="nowrap" align="center">金额</td>
        <td width="6%" nowrap="nowrap" align="center">出货仓库</td>
        <td width="6%" nowrap="nowrap" align="center">顾客姓名</td>
        <td width="6%" nowrap="nowrap" align="center">顾客电话</td>
		<td width="12%" nowrap="nowrap" align="center">地址</td>
		<td width="12%" nowrap="nowrap" align="center">附件</td>
        <td width="5%" nowrap="nowrap" align="center">数据来源</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr class="list-tr">
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
          <td align="left" nowrap="nowrap">${cur.BILL_SN}</td>
          <td align="left" nowrap="nowrap">
          	<c:if test="${cur.BILL_TYPE eq 20 }">零售</c:if>
          	<c:if test="${cur.BILL_TYPE eq 21 }">零售退货</c:if>
          	<c:if test="${cur.BILL_TYPE eq 30 }">零售通</c:if>
          </td>
          <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.OPR_DATE}" pattern="yyyy/MM/dd" /></td>
          <td align="left" nowrap="nowrap">${cur.P_NAME}</td>
          <td align="left" nowrap="nowrap">${cur.P_ID}</td>
          <td align="left" nowrap="nowrap">${cur.ADD_USER_NAME }</td>
          <td align="left" nowrap="nowrap">
          	<c:if test="${cur.BILL_TYPE eq 20 or cur.BILL_TYPE eq 21 }">${cur.TYPE_NAME}</c:if>
          	<c:if test="${cur.BILL_TYPE eq 30 }">康佳电视</c:if>
          </td>
          <td align="left" nowrap="nowrap">${cur.GOODS_NAME}</td>
          <td align="center" nowrap="nowrap">${cur.MD_SIZE }</td>
          <td align="right" nowrap="nowrap">${cur.NUM }</td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">${cur.PRICE }</span></td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">${cur.PRICE}</span></td>
          <td align="left" nowrap="nowrap">${cur.NUM*cur.PRICE}</td>
          <td align="left" nowrap="nowrap">${cur.STORE_NAME }</td>
          <td align="left" nowrap="nowrap">${cur.PARTNER_NAME}</td>
          <td align="left" nowrap="nowrap">${cur.LINK_MOBILE}</td>
          <td align="left" nowrap="nowrap">${cur._PROVINCE }${cur.CITY }${cur._COUNTRY }${cur._TOWN }${cur.CONSIGNEE_STREET }</td>
          <td align="left" nowrap="nowrap">
          	<c:if test="${not empty cur.FAPIAOS}">
           		<c:set var="fapiao" value="${fn:split(cur.FAPIAOS,',')}" />
          		<c:forEach items="${fapiao}" var="tt" varStatus="vs1">
          			<c:set var="num" value="${fn:length(tt)}" />
          				<a href=/${tt} target="_blank">&nbsp;附件${vs1.count}&nbsp;</a>
          		</c:forEach>
          	</c:if>
          </td>
          <td align="center" nowrap="nowrap">
          	<!-- ${fn:split('手机端,WEB端,IOS手机端,客户端', ',')[cur.DATA_SOURCE-1]} -->
          	<c:if test="${cur.BILL_TYPE eq 20 or cur.BILL_TYPE eq 21 }">客户端</c:if>
          	<c:if test="${cur.BILL_TYPE eq 30 }">零售通</c:if>
          </td>
        </tr>
        <c:if test="${vs.last eq true}">
          <c:set var="i" value="${vs.count}" />
        </c:if>
      </c:forEach>
      <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
        <tr align="center">
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </c:forEach>
    </table>
  </div>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="JBill.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript"> 
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "listForPrint");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("opr_date_gt", "${fn:escapeXml(af.map.opr_date_gt)}");							
			pager.addHiddenInputs("opr_date_lt", "${fn:escapeXml(af.map.opr_date_lt)}");							
			pager.addHiddenInputs("bill_sn_like", "${af.map.bill_sn_like}");							
			pager.addHiddenInputs("bill_type", "${af.map.bill_type}");							
			pager.addHiddenInputs("p_name_like", "${af.map.p_name_like}");
            pager.addHiddenInputs("p_id_like", "${af.map.p_id_like}");
            pager.addHiddenInputs("report_name_like", "${af.map.report_name_like}");
            pager.addHiddenInputs("type_id", "${af.map.type_id}");
            pager.addHiddenInputs("goods_name_like", "${af.map.goods_name_like}");
            pager.addHiddenInputs("type_id", "${af.map.type_id}");
            pager.addHiddenInputs("store_id", "${af.map.store_id}");
            pager.addHiddenInputs("data_type", "${af.map.data_type}");
            pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");
            pager.addHiddenInputs("mobile_like", "${af.map.mobile_like}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/select2.min.js?t=1"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	// 可查询选择框
	$('#goods_id').select2({
	    minimumInputLength: 2,
	    allowClear: true
	});
	
	$(".list-tr td").each(function(){
		var text = $(this).html();
		if($.trim(text).length == 0) {
			$(this).html("<span style='color:#CCC;'>未填写</span>");
		}
	});

	var queryForm = document.forms[0];
	$("#span_help").click(function(){
        $("#cvtooltipClose").cvtooltip({
            panel: "#body_oarcont",
            direction: 1,                
            width: 420,
            left: 320,
            top: 5,
            speed: 500,
            delay: 10000
        });
    });
	

	//导出excel
	$("#but_excel").click(function(){
		if(confirm("提示，您确认导出数据？")){
// 			loading();
			$("#bottomPageForm").append("<input type='hidden' name='export' value='1' />");
			$("#bottomPageForm").submit();
		}
	});

});

function selectGoodType(obj){
	var $this = $(obj);
	var goods_id = document.getElementById("goods_id");
	var type_id = $this.val();
	$.ajax({
		type: "POST",
		url: "JSubSellRec.do",
		data: {method : "ajaxGetGoodsList", "type_id": type_id},
		dataType: "json",
		cache:false,
		error: function(){alert("数据加载请求失败！");},
		success: function(ret){
			if(ret){
				goods_id.options.length=0; ;
				goods_id.options.add(new Option("请选择",""));;
				for(var i=0; i<ret.list.length; i++){			
					goods_id.options.add(new Option(ret.list[i].goods_name,ret.list[i].goods_id));;
				}
			}
		}
   });
}


// function loading(){
// 	jLoading("&nbsp;&nbsp;正在加载数据...", {autoHide:false, HorizontalPosition:"center", VerticalPosition:"center", MinWidth:150});
// }
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
