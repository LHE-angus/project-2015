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
	    line-height:20px;
	}
	table tr td{
	    font-size:12px;
	}

</style>

<style media="print">
	.noprint{display:none;}
</style>

<c:set var="recordCount" scope="request" value="${recordCount}" />
<c:set var="totalCount" scope="request" value="${totalCount}" />
</head>
<body>

	<div id="p_div">
	<table width="100%" border="0" >
		<tr>
			<td colspan="5" align="center" style='font-weight: bold; font-size: 18px; font-family: 宋体'>康佳集团${fn:escapeXml(fgsDept.dept_name)}分公司提（退货）申请单</td>
		</tr>
		<tr>
			<td colspan="1" >&nbsp;</td>
			<td colspan="1" >&nbsp;</td>
			<td colspan="1" >&nbsp;</td>
			<td colspan="1" algin="right">${fn:escapeXml(nowDate)}&nbsp;&nbsp;</td>
			<td colspan="1" algin="right" >流水号:${fn:escapeXml(af.map.trade_index)}</td>
		</tr>
		<tr>
			<td colspan="1" >&nbsp;</td>
			<td colspan="1" align="left" style="font-size: 12px;" >客户名称:</td>
			<td colspan="1" align="left" style="font-size: 12px;">${fn:escapeXml(konkaR3Shop.r3_code)}&nbsp;&nbsp;${fn:escapeXml(konkaR3Shop.customer_name)}</td>
			<td colspan="1" align="right" style="font-size: 12px;">提货方式：</td>
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
			<td colspan="1" >&nbsp;</td>
		    <td colspan="2" align="left" style="font-size: 12px;">送货地址(电话):${orderinfo.user_tel }&nbsp;&nbsp;${orderinfo.user_phone }</td>
		    <td colspan="1" align="right" style="font-size: 12px;">出库仓位:</td>
		    <td colspan="1">&nbsp;</td>
	 	</tr>
	</table>

	<table width="100%" border="1" >
	  <tr>
	    <td colspan="3" >
		    <table width="100%" border="1" frame="void">
		      <tr>
		        <td width="15%" align="center">机型</td>
		        <td width="8%" align="center">数量</td>
		        <td width="10%" align="center">单价</td>
		        <td width="10%" align="center">金额</td>
		        <td width="10%" align="center">折扣%</td>
		        <td width="10%" align="center">折扣金额</td>
		        <td width="17%" align="center">行备注</td>
		      </tr>
		     <c:forEach items="${konkaOrderInfoDetailsList}" var="cur" varStatus="vs" >
              <tr>
                <td align="center">${fn:escapeXml(cur.pd_name)}</td>
                <td align="center">${fn:escapeXml(cur.good_count)}</td>
                <td align="center"><fmt:formatNumber value="${cur.good_price}" pattern="0.00" /></td>
                <td align="center"><fmt:formatNumber value="${cur.good_sum_price}" pattern="0.00" /></td>
                <td align="center"><fmt:formatNumber value="${cur.good_discount}" pattern="0.0" />%</td>
                <td align="center"><fmt:formatNumber value="${cur.good_discount_price}" pattern="0.00" /></td>
                <td align="center">${fn:escapeXml(cur.pd_remark)}</td>
              </tr>
            </c:forEach>
            <!-- 不足七行补7行,超过一直往下加 -->
		   <c:if test="${recordCount le 20}" >
		   	<c:forEach begin="${recordCount+1}" end="20">
		   		<tr>
			   		<td align="center">&nbsp;</td>
			   		<td align="center">&nbsp;</td>
			   		<td align="center">&nbsp;</td>
			   		<td align="center">&nbsp;</td>
			   		<td align="center">&nbsp;</td>
			   		<td align="center">&nbsp;</td>
			   		<td align="center">&nbsp;</td>
		   		</tr>
		   	</c:forEach>
		   </c:if>
		      <tr>
		        <td>合计</td>
		        <td align="center">${fn:escapeXml(s_order_num)}</td>
		        <td align="center">&nbsp;</td>
		        <td align="center"><fmt:formatNumber value="${s_price}" pattern="0.00" /></td>
		        <td align="center">&nbsp;</td>
		        <td align="center"><fmt:formatNumber value="${s_dis_price}" pattern="0.00" /></td>
		        <td align="center">&nbsp;</td>
		      </tr>
		      <tr>
		        <td colspan="4">销售订单号:<span style="margin-left: 20px">${orderinfo.r3_id}</span></td>
		        <td colspan="3">物流交货号:<span style="margin-left: 20px"></span></td>
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
		        <td  align="center">账期限额</td>
		        <td align="center">${KLIMK }</td>
		        <td>&nbsp;</td>
		      </tr>
		      <tr>
		        <td  align="center">账款余额</td>
		        <td align="center">${SKFOR }</td>
		        <td>&nbsp;</td>
		      </tr>
		      <tr>
		        <td  align="center">本次收款</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		      </tr>
		      <tr>
		        <td  align="center">收款方式</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		      </tr>
		      <tr style="line-height:22px;">
		        <td  align="center">本次发货<br />限额
		        </td>
		        <td>&nbsp;</td>
		        <td >&nbsp;</td>
		      </tr>
		      <tr>
		        <td align="center">发货后余额</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		      </tr>
		      <!-- 补空行  -->
		      <c:if test="${recordCount lt 20}">
			      <c:forEach begin="8" end="20">
			      	 <tr>
				        <td width="30%">&nbsp;</td>
				        <td width="35%">&nbsp;</td>
				        <td width="35%">&nbsp;</td>
				      </tr>
			      </c:forEach>
		      </c:if>
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
	    	<td colspan="1">客户订单号:</td>
	    	<td colspan="5">备注:${orderinfo.remark }</td>
	  	</tr>
	  	
	  	 <tr>
			  	<td colspan="1" width="20%" algin="center">总经理:</td>
			  	<td colspan="1" width="20%" algin="center">财务经理:</td>
			  	<td colspan="1" width="20%" algin="center">业务主管:</td>
			  	<td colspan="3" width="20%" algin="center">业务员:</td>
		  	</tr>
		  	
		<tr>
		  	<td colspan="6" width="100%" algin="center">审批信息</td>
	    </tr>
		<tr>
			
			<tr>
                  <td class="title_item" width="12%" style="text-align:center;">审核时间</td>
                  <td class="title_item" width="10%" style="text-align:center;">审核人</td>
				  <td class="title_item" width="18%" style="text-align:center;">审核角色</td>
                  <td class="title_item" width="10%" style="text-align:center;">审核状态</td>
                  <td class="title_item" width="10%" style="text-align:center;">意见</td>
			</tr>
			<c:forEach items="${konkaOrderInfoAuditList}" var="cur1" varStatus="vs">
			
				  
	      	    	<tr>
	      				<td align="center" nowrap="nowrap"><fmt:formatDate value="${cur1.audit_datetime}" pattern="yyyy/MM/dd HH:mm"/></td>
	      				<td align="center" nowrap="nowrap">${fn:escapeXml(cur1.audit_user)}</td>
	      				<td align="center" nowrap="nowrap">${fn:escapeXml(cur1.map.role_name)}</td>
	      				<td align="center" nowrap="nowrap">
	      					<c:choose>
	      						<c:when test="${cur1.audit_result eq 1}">审核通过</c:when>
	      						<c:when test="${cur1.audit_result eq -1}">驳回（至审核人）</c:when>
	      						<c:when test="${cur1.audit_result eq 0}">驳回（至制单）</c:when>
	      						<c:when test="${cur1.audit_result eq -9}">（客户）撤销</c:when>
	      					</c:choose>
	      				</td>
	      				<td>
	      					${cur1.audit_comment }
	                	</td>
	             </tr>
		   </c:forEach>
		  
	    </tr>
	    
	    
			
	</table>
	</div>
	<!-- 用form进行请求分页数据,填充好数据后再调用打印 -->

		<div id="np_div" class="noprint">
		<c:if test="${totalCount ge 20}">
		 <form name="bottomPageForm" method="post" action="KonkaJxcOrderAudit.do">
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
        window.print();
	});
	 $(".bgButtonBack").click(function(){
		 window.close();
	});
});

//]]></script>

</html>
