<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_self">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
	table{
		border-collapse:collapse;
	}
	table tr{
	    line-height:22px;
	}
	table tr td{
	    font-size:12px;
	}
</style>
<!-- zx26a -->
<style media="print">
	.noprint{display:none;}
</style>
<!-- 默认每次取7条,但有可能最后一页不足7条 -->
<c:set var="recordCount" scope="request" value="${recordCount}" />
<c:set var="totalCount" scope="request" value="${totalCount}" />
</head>
<body style='margin:0 auto ;width:800px;height:500px'>
	
	<div style='margin:0 auto; '>
		<div style='margin:0 1px 0 1px;' >
		<table width="100%" border="0" >
			<tr>
				<td colspan="5" align="center" style='font-weight: bold; font-size: 18px; font-family: 宋体'>康佳集团${fn:escapeXml(fgsDept.dept_name)}分公司提（退货）申请单</td>
			</tr>
				<tr>
				<td colspan="3" ></td>
				<td colspan="1" algin="right">${orderdate}&nbsp;&nbsp;</td>
				<td colspan="1" algin="left" >流水号:${fn:escapeXml(af.map.trade_index)}</td>
			</tr>
			
			<tr>
				<td colspan="3" align="left" style="font-size: 12px;">客户名称:${fn:escapeXml(orderinfo.ag)}&nbsp;${fn:escapeXml(orderinfo.user_shop_name)}&nbsp;[&nbsp;${khxd.CTLPC }&nbsp;]</td>
				<td colspan="1" align="right" style="font-size: 12px;">提货方式:</td>
			    <td colspan="1" style="font-size: 12px;">
		       	    &nbsp;&nbsp;
					<input type="checkbox" onclick="{return false}" <c:choose><c:when test="${af.map.send_type eq 1}"> checked </c:when> </c:choose> />
		        	自提
		        	&nbsp;&nbsp;
		        	<input type="checkbox" onclick="{return false}" <c:choose><c:when test="${af.map.send_type eq 2}"> checked </c:when> </c:choose> />
		        	配送
		        </td>
			</tr>
			
			<tr>
			    <td colspan="3" align="left" style="font-size: 12px;">送达方:${orderinfo.we }&nbsp;${konkaR3Shop.customer_name }</td>
			    <td colspan="1" align="right" style="font-size: 12px;">出库仓位:</td>
			    <td colspan="1">${storeKey}</td>
		 	</tr>
			<tr>
			    <td colspan="5" align="left" style="font-size: 12px;">送货地址(电话):${orderinfo.user_addr }&nbsp;${orderinfo.user_tel }&nbsp;${orderinfo.user_phone }</td>
		 	</tr>
		</table>
	
		<table width="100%" border="1" >
		  <tr>
		    <td colspan="3" >
			    <table width="100%" border="1" frame="void">
			      <tr>
			        <td  align="left">机型</td>
			        <td  align="center">数量</td>
			        <td  align="center">单价</td>
			        <td  align="center">金额</td>
			        <td  align="center">折扣%</td>
			        <td  align="center">折扣金额</td>
			        <td  align="center">折后金额</td>
			        <td  align="left" >行备注</td>
			      </tr>
			     <c:forEach items="${konkaOrderInfoDetailsList}" var="cur" varStatus="vs" >
			       	  <tr>
		                <td align="left">${cur.pd_name}</td>
		                <td align="center">${cur.good_count}</td>
		                <td align="center"><fmt:formatNumber value="${cur.good_price}" pattern="0.00" /></td>
		                <td align="center"><fmt:formatNumber value="${cur.good_sum_price}" pattern="0.00" /></td>
		                <td align="center"><fmt:formatNumber value="${cur.good_discount}" pattern="0.00" /></td>
		                <td align="center"><fmt:formatNumber value="${cur.good_discount_price}" pattern="0.00" /></td>
		                <td align="center"><fmt:formatNumber value="${cur.map.af_dis_price}" pattern="0.00" /></td>
		                <td align="left" style="font-size:80%">${cur.pd_remark}</td> 
		              </tr>
	            </c:forEach>
	            <!-- 不足七行补7行,超过一直往下加 -->
			   <c:if test="${recordCount lt 7}" >
			   	<c:forEach begin="${recordCount+1}" end="7">
			   		<tr>
				   		<td align="left">&nbsp;</td>
				   		<td align="center">&nbsp;</td>
				   		<td align="center">&nbsp;</td>
				   		<td align="center">&nbsp;</td>
				   		<td align="center">&nbsp;</td>
				   		<td align="center">&nbsp;</td>
				   		<td align="center">&nbsp;</td>
				   		<td align="left">&nbsp;</td>
			   		</tr>
			   	</c:forEach>
			   </c:if>
			      <tr>
			        <td>合计</td>
			        <td align="center">${fn:escapeXml(s_order_num)}</td>
			        <td align="center">&nbsp;</td>
			        <td align="center"><fmt:formatNumber value="${s_price}" pattern="0.00" /></td>
			         <td align="center" colspan="2"><fmt:formatNumber value="${s_dis_price}" pattern="0.00" /></td>
			         <td align="center"><span style="font-weight: bold ;"><fmt:formatNumber value="${s_af_dis_price}" pattern="0.00" /></span></td>
			        <td align="center">&nbsp;</td>
			      </tr>
			      <tr>
			        <td colspan="4">销售订单号:<span style="margin-left: 20px">${orderinfo.r3_id}</span></td>
			        <td colspan="3">物流交货号:<span style="margin-left: 20px">${los}</span></td>
			      </tr>
			   </table>
		    </td>
		    <td colspan="2" >
			    <table width="100%"  border="1" frame="void" >
			      <tr>
			        <td width="40%" align="center">项目</td>
			        <td width="40%" align="center">金额</td>
			        <td width="20%" align="center">说明</td>
			      </tr>
			       <tr>
			        <td  align="left">信贷限额</td>
			        <td align="center">${khxd.KLIMK }</td>
			        <td>&nbsp;</td>
			      </tr>
			      
			      <tr>
			        <td  align="left" style='nowrap'>信贷风险总额</td>
			        <td align="center">${khxd.OBLIG }</td>
			        <td>&nbsp;</td>
			      </tr>
			      <tr>
			        <td  align="left">剩余额度</td>
			        <td align="center">${khxd.ZSYED }</td>
			        <td>&nbsp;</td>
			      </tr>
			      <tr>
			        <td  align="left">本次收款</td>
			        <td>&nbsp;</td>
			        <td>&nbsp;</td>
			      </tr>
			      <tr>
			        <td  align="left">收款方式</td>
			        <td>&nbsp;</td>
			        <td>&nbsp;</td>
			      </tr>
			      <tr >
			        <td  align="left">本次发货限额
			        </td>
			        <td>&nbsp;</td>
			        <td >&nbsp;</td>
			      </tr>
			      <tr>
			        <td align="left">发货后余额</td>
			        <td colspan="2">&nbsp;</td>
			      </tr>
			      <tr>
			        <td >收款人签名</td>
			        <td colspan="2">&nbsp;</td>
			      </tr>
			       <tr>
			        <td colspan="3">账款或账期审核人签名</td>
			        </tr>
			    </table>
		    </td>
		  </tr>
		</table>
		<table width="100%" border="1" frame="border" >
			<tr>
		    	<td colspan="1">客户订单号:${orderinfo.third_cg_order_num }</td>
		    	<td colspan="1">打印:${nowDate}</td>
		    	<td colspan="4" >备注:${orderinfo.remark }</td>
		  	</tr>
			<tr>
			  	<td colspan="1" width="20%" algin="center">总经理:${s34}</td>
			  	<td colspan="1" width="20%" algin="center">财务经理:${s39}</td>
			  	<td colspan="1" width="20%" algin="center">业务主管:${s50r40}</td>
			  	<td colspan="3" width="20%" algin="center">业务员:${s60}</td>
		  	</tr>
		</table>
		</div>
	
	</div>
	<!-- 用form进行请求分页数据,填充好数据后再调用打印 -->

		<div id="np_div" class="noprint">
		<c:if test="${totalCount gt 7}">
		 <form name="bottomPageForm" method="post"  action="KonkaJxcOrderAudit.do" >
		  <html-el:hidden property="method" value="print" />
	      <table width="100%" border="0" cellpadding="0" cellspacing="0">
	        <tr>
	          <td height="40">
		          <div style="text-align: center;">
		              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
		              <script type="text/javascript">
						var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
						pager.addHiddenInputs("method", "print");
						pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
						pager.addHiddenInputs("id", "${orderinfo.id}");
						document.write(pager.toString());
					  </script>
		            </div>
	            </td>
	        </tr>
	      </table>
	    </form>
		</c:if>
		</div>
		</div>
		<div class="noprint" align="center">
			  <div  class="noprint">
			 <span style="color:green"><font style="font-size: small;">已打印${orderinfo.printCount}次,标记打印</font></span>
			   	<input type="checkbox" id="printCount_btn" /> |
				 <input name="print" type="button" class="bgButtonPrint" value="打印"/>
				 <input name="close" type="button" class="bgButtonBack" value="关闭" />
			 </div>
		</div>
</body>

<script type="text/javascript" src="${ctx}/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$(".bgButtonPrint").click(function(){
		//累加打印次数
		if($("#printCount_btn").is(':checked')==true){
			$.ajax({
				type: "POST",
				cache: false,
				url: "KonkaJxcOrderAudit.do",
				data: "method=countPrint&orderId=${orderinfo.id}" ,
				dataType: "json",
				error: function(request, settings){},
				success: function(data) {
					 window.print();
				}
			});
		}else{
			 window.print();
		}
	});
	 $(".bgButtonBack").click(function(){
		 window.close();
	});
});

//]]></script>

</html>
