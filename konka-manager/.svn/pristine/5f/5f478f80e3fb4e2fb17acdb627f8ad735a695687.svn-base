<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<META charset="UTF-8">
<META name="viewport" content="width=device-width, initial-scale=1">
<META http-equiv=Expires content=0>
<META http-equiv=Cache-Control content=no-cache>
<META http-equiv=Pragma content=no-cache>
<title>预约点上报管理</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/mobile/css/common.css" />
</head>
<body>
<div class="oarcont">
  <div class="rtabcont2">
    <html-el:form action="/KonkaSpActivityBookReport" enctype="multipart/form-data">
      <html-el:hidden property="method" value="save" styleId="method" />
	<html-el:hidden property="id" value="${af.map.id}" />
	<html-el:hidden property="user_id" value="${user_id}" />
      <html-el:hidden property="userpass" value="${userpass}" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtab2">
       <tr>
          <th colspan="2" width="90%" align="left" class="bno" id="filetitle">预约基本信息</th>
        </tr>
      <tr>
      <td class="title_item" align="right" nowrap="nowrap"><font
				color="red">*</font>门店名称：</td>
       <td>
       ${af.map.store_name}
      </td>
      </tr>
      <tr>
      <td class="title_item" align="right" nowrap="nowrap"><font
				color="red">*</font>促销活动：</td>
       <td>
       ${af.map.sp_name}
      </td>
      </tr>
      <tr>
      <td class="title_item" align="right" nowrap="nowrap"><font
				color="red">*</font>预约点：</td>
       <td>
      ${af.map.addr_name}
      </td>
      </tr>
      
       <tr>
      <td class="title_item" align="right" nowrap="nowrap">消费者姓名: </td> 
        <td>${af.map.comsumer_name}</td> 
       </tr>
      
       <tr>
      	<td class="title_item" align="right" nowrap="nowrap">消费者电话: </td> 
        <td>${af.map.comsumer_phone}</td> 
       </tr>
      
       <tr>
      	<td class="title_item" align="right" nowrap="nowrap">预约尺寸: </td> 
        <td><c:forEach items="${sizeSecList}" var="sizeSec" varStatus="vs">
		        <c:if test="${sizeSec.field1 eq af.map.size_section}">${sizeSec.type_name}</c:if>
		   </c:forEach>
		</td> 
      </tr>
        <tr>
      	<td class="title_item" align="right" nowrap="nowrap">预约型号: </td> 
        <td>${af.map.model_name}</td> 
       </tr>
       
        <tr>
      	<td class="title_item" align="right" nowrap="nowrap">预约数量: </td> 
        <td>${af.map.num}</td> 
       </tr>
       
      <tr>
      	<td class="title_item" align="right" nowrap="nowrap">支付定金: </td> 
        <td><html-el:radio property="prepay_state" value="0" />是
            &nbsp;&nbsp;&nbsp;&nbsp;<html-el:radio property="prepay_state" value="1" />否
            &nbsp;&nbsp;&nbsp;&nbsp;
          <c:if test="${ not empty af.map.prepay_money}"><font color="red">￥${af.map.prepay_money}</font></c:if>
           
         </td> 
       </tr> 
       <tr>
      	<td class="title_item" align="right" nowrap="nowrap">备注: </td> 
        <td><html-el:textarea property="memo" styleId="memo"  rows="3" style="width:50%;">
        </html-el:textarea> </td> 
       </tr>
        <tr>
          <td>&nbsp;&nbsp;</td>
          <td>
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back()" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<!-- ****** Main Frame End ****** -->
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/commons.plugin.jxc.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=idialog"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">//<![CDATA[

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
