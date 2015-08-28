<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div>
    <h3 align="center" ><strong class="fb">R3订单明细</strong></h3>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/OrderTest" enctype="multipart/form-data">
      <html-el:hidden property="r3_order_header_id" styleId="id" />
      <div style="height:20px;"><font color="blue" style="font-weight: bold;">订单头信息</font></div>
      <table width="80%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr>
          <td width="120" align="left" class="td_bord">订单单号</td>
          <td width="130">${af.map.r3_order_no}</td>
          <td width="120" align="left" class="td_bord">年份</td>
          <td width="130">${af.map.r3_order_year}</td>
          <td width="120" align="left" class="td_bord">客户编码</td>
          <td width="130">${af.map.customer_code}</td>
          <td width="120" align="left" class="td_bord">客户名称</td>
          <td width="130">${af.map.customer_name}</td>
        </tr>
        
        <tr>
          <td width="120" align="left" class="td_bord">销售单号</td>
          <td width="130">${af.map.r3_order_sell_no}</td>
          <td width="120" align="left" class="td_bord">凭证年份</td>
          <td width="130">${af.map.certify_year}</td>
          <td width="120" align="left" class="td_bord">物料类型</td>
          <td width="130">${af.map.material_type}</td>
          <td width="120" align="left" class="td_bord">周转物料类型</td>
          <td width="130">${af.map.turnover_material_type}</td>
        </tr>
        
        <tr>
          <td width="120" align="left" class="td_bord">申请类型</td>
          <td width="130">${af.map.apply_type}</td>
          <td width="120" align="left" class="td_bord">紧急程度</td>
          <td width="130">${af.map.emergency_level}</td>
          <td width="120" align="left" class="td_bord">申请日期</td>
          <td width="130"><fmt:formatDate pattern="yyyy-MM-dd" value="${af.map.apply_type}"></fmt:formatDate></td>
          <td width="120" align="left" class="td_bord">要求发货日期</td>
          <td width="130"><fmt:formatDate pattern="yyyy-MM-dd" value="${af.map.ship_date}"></fmt:formatDate></td>
        </tr>
        
        <tr>
          <td width="120" align="left" class="td_bord">要求发货方式</td>
          <td width="130">${af.map.delivery_type}</td>
          <td width="120" align="left" class="td_bord">建议承运商</td>
          <td width="130">${af.map.suggest_carrier}</td>
          <td width="120" align="left" class="td_bord">制单人</td>
          <td width="130">${af.map.creator}</td>
          <td width="120" align="left" class="td_bord">收货人</td>
          <td width="130">${af.map.recipient}</td>
        </tr>
        
        <tr>
          <td width="120" align="left" class="td_bord">收货人电话</td>
          <td width="130">${af.map.recipient_tel}</td>
          <td width="120" align="left" class="td_bord">总金额</td>
          <td width="130">${af.map.total_money}</td>
          <td width="120" colspan="1" align="left" class="td_bord">收货人地址</td>
          <td width="130" colspan="3">${af.map.recipient_address}</td>
        </tr>
        
        <tr>
         <td width="120" align="left" class="td_bord">备注</td>
          <td colspan='7'>
          ${af.map.remark }
          </td>
        </tr>
        
      </table>
      <div style="height:20px;"><font color="blue" style="font-weight: bold;">物料信息</font></div>
      
      <c:if test="${not empty entityLineList}">
      <div style="border: 1px">
        <table width="80%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
				<tr>
					<th width="5%" nowrap="nowrap">序号</th>
					<th width="5%" nowrap="nowrap">物料编号</th>
					<th width="8%" nowrap="nowrap">物料名称</th>
					<th width="8%" nowrap="nowrap">物料描述</th>
					<th width="8%" nowrap="nowrap">审批说明</th>
					<th width="8%" nowrap="nowrap">发货说明</th>
					<th width="8%" nowrap="nowrap">单位</th>
					<th width="8%" nowrap="nowrap">申请数量</th>
					<th width="8%" nowrap="nowrap">审批数量</th>
					<th width="8%" nowrap="nowrap">单价</th>
					<th width="8%" nowrap="nowrap">金额</th>
					<th width="8%" nowrap="nowrap">操作</th>
				</tr>
				
				  <c:forEach var="cur" items="${entityLineList}" varStatus="vs" >
				    <tr>
				      <td align="center" nowrap="nowrap" height="20">${vs.count}</td>
				      <td align="center" nowrap="nowrap" >${cur.material_code}</td>
				      <td align="center" nowrap="nowrap" >${cur.material_name}</td>
				      <td align="center" nowrap="nowrap" >${cur.material_desc}</td>
				      <td align="center" nowrap="nowrap" >${cur.review_notes}</td>
				      <td align="center" nowrap="nowrap" >${cur.ship_desc}</td>
				      <td align="center" nowrap="nowrap" >${cur.unit_code}</td>
				      <td align="center" nowrap="nowrap" >${cur.apply_amount}</td>
				      <td align="center" nowrap="nowrap" >${cur.review_amount}</td>
				      <td align="center" nowrap="nowrap" >${cur.unit_price}</td>
				      <td align="center" nowrap="nowrap" >${cur.total_money}</td>
				      <td>修改|删除</td>
				    </tr>
				    <tr>
				    <td align="center" nowrap="nowrap" height="20"><font style="font-weight: bold">总计</font></td>
				      <td align="center" nowrap="nowrap" ></td>
				      <td align="center" nowrap="nowrap" ></td>
				      <td align="center" nowrap="nowrap" ></td>
				      <td align="center" nowrap="nowrap" ></td>
				      <td align="center" nowrap="nowrap" ></td>
				      <td align="center" nowrap="nowrap" ></td>
				      <td align="center" nowrap="nowrap" ></td>
				      <td align="center" nowrap="nowrap" ></td>
				      <td align="center" nowrap="nowrap" ></td>
				      <td align="center" nowrap="nowrap" >cccc</td>
				      <td align="center" nowrap="nowrap" ></td>
				    </tr>
				  </c:forEach>
			</table>
		</div>
      </c:if>
      <!-- 不实现会签功能了 -->
<%--       <c:if test="${not empty countersignNodeList}"> --%>
<!--         <table width="98%" align="center" class="list1" style="margin-top:3px;"> -->
<!--           <tr> -->
<!--             <td width="120" align="center" nowrap="nowrap" class="td_bord">会签时间</td> -->
<!--             <td align="center" class="td_bord">会签意见</td> -->
<!--             <td width="200" align="center" nowrap="nowrap" class="td_bord">会签人/部门</td> -->
<!--           </tr> -->
<%--           <c:forEach var="cur" items="${countersignNodeList}" varStatus="vs"> --%>
<!--             <tr> -->
<%--               <td align="center"><c:if test="${not empty cur.audit_datetime}"> --%>
<%--                   <fmt:formatDate value="${cur.audit_datetime}" pattern="yyyy-MM-dd HH:mm:ss" /> --%>
<%--                 </c:if> --%>
<%--                 <c:if test="${empty cur.audit_datetime}"><span style="color:#f00">未会签</span> </c:if></td> --%>
<%--               <td align="left"><c:if test="${not empty cur.audit_comment}"> --%>
<%--                   <c:if test="${cur.audit_level eq 1}"><span style="color:#F00;font-weight:bold;">会签意见汇总：</span></c:if> --%>
<%--                   ${fn:escapeXml(cur.audit_comment)}</c:if> --%>
<%--                 <c:if test="${empty cur.audit_comment}"><span style="color:#f00">未会签</span> </c:if></td> --%>
<%--               <td>${cur.audit_user}/${cur.map.org_name}</td> --%>
<!--             </tr> -->
<%--           </c:forEach> --%>
<!--         </table> -->
<%--       </c:if> --%>
   
   
    </html-el:form>
    <div> <br />
      <label >
      <input class="but5" type="button"  value="返回" onclick="history.back();" />
      </label>
    </div>
  </div>
  <div class="clear"></div>
</div>

<script type="text/javascript">//<![CDATA[
                                          
$(document).ready(function(){
	$("#gotoAdd").click(function(){
		window.location.href = "${ctx}/jxc/KonkaJxcStockBill.do?method=add&mod_id=${af.map.mod_id}";
	});

	$(".bgSearch").click(function(){
    	var s_time = $("#add_date_gt").val();
		var e_time = $("#add_date_lt").val();
		if ("" != s_time && "" != e_time && s_time > e_time) {
			alert("开始日期不能大于结束日期！");
			return false;
		}
    });
});
	
		
//]]></script>


</body>
</html>
