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
</head>
<body >
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
  <html-el:form action="/admin/KonkaPdModelPrices">
    <html-el:hidden property="method" value="list" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
        <td  nowrap="nowrap"><strong class="fb">&nbsp;&nbsp;&nbsp;&nbsp;型号：</strong>
          <html-el:text property="category_name_like" styleId="category_name_like" size="20" />
          </td>
          <td  nowrap="nowrap">
           		<strong class="fb">现款价：</strong>
            	<html-el:text property="cash_price_min" styleId="cash_price_min" maxlength="12" size="12" />
            	-
            	<html-el:text property="cash_price_max" styleId="cash_price_min" maxlength="12" size="12" />
            </td>
        <td>
       <input class="but1" type="submit" name="Submit" value="搜索" />
       </td>
      </tr>
    </table>
  </html-el:form>
 </div>
  <div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
			<logic-el:match name="popedom" value="+1+">  
			<input type="button" class="but_excel" onclick="doNeedMethod(null, 'KonkaPdModelPrices.do','addBatch' ,$('#bottomPageForm').serialize())" value="导入" />
		    <input name="button" type="button"  class="but2" value=" 新增 " onclick="location.href='KonkaPdModelPrices.do?method=add&mod_id=${af.map.mod_id}';" />
		    </logic-el:match>
		    <logic-el:match name="popedom" value="+4+">
		    <input class="but3" type="submit" name="Submit3" value="回收站" onclick="confirmDeleteAll(document.getElementById('listForm'));" />
		    </logic-el:match> 
		 </td>
	    </tr>
	  </table>  
  </div>
<div class="rtabcont1">
<form id="listForm" name="listForm" method="post" action="KonkaPdModelPrices.do?method=delete">
 <input type="hidden" name="method" id="method" value="delete" />
 <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
   <tr class="tabtt1">
    <c:if test="${is_division_or_admin}">
      <td width="30" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
     </c:if>
     <c:if test="${!is_division_or_admin}">
      <td width="30" align="center" nowrap="nowrap">序号</td>
     </c:if>
      <td nowrap="nowrap">型号</td>
      <td nowrap="nowrap">现款价</td>
      <td nowrap="nowrap">零售指导价</td>
      <td nowrap="nowrap">扣点</td>
      <td nowrap="nowrap">月份</td>
      <td nowrap="nowrap">添加时间</td>
      <td width="120" align="center" >操作</td>
    </tr>
    <tbody>
    <c:forEach var="cur" items="${entityList}" varStatus="vs">
      <tr>
        <c:if test="${is_division_or_admin}">
          <td align="center" nowrap="nowrap"><input name="pks" type="checkbox" id="pks" value="${cur.id}" /></td>
        </c:if>
        <c:if test="${! is_division_or_admin}">
          <td nowrap="nowrap" align="center">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
        </c:if>
        <td align="left">${cur.pd_name}</td>
        <td align="left">${cur.cash_price}</td>
        <td align="left">${cur.sale_price}</td>
        <td align="left">${cur.discount}</td>
        <td align="left">${cur.price_month}</td>
        <td align="left">
         <fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" />
        </td>
        <td align="center">
        <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaPdModelPrices.do', 'view','id=${cur.id}&'+$('#bottomPageForm').serialize())">查看</span>
        |
        <logic-el:match name="popedom" value="+2+"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaPdModelPrices.do', 'edit','id=${cur.id}&'+$('#bottomPageForm').serialize())">修改</span></logic-el:match>
		<logic-el:notMatch name="popedom" value="+2+"><span style="color:#ccc;" class="fblue" >修改</span></logic-el:notMatch>
		|
		<logic-el:match name="popedom" value="+4+"><span style="cursor:pointer;" class="fblue" onclick="confirmDelete(null, 'KonkaPdModelPrices.do','id=${cur.id}&'+$('#bottomPageForm').serialize())">删除</span></logic-el:match>
		<logic-el:notMatch name="popedom" value="+4+"><span style="color:#ccc;" class="fblue" >删除</span></logic-el:notMatch></td>
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
      </tr>
    </c:forEach>
    </tbody>
 </table>
</form>
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaPdModelPrices.do">
   <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
          <td height="40"><div style="text-align:right; padding-right:5px;"> 
          <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
          <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("tree_param", "${tree_param}");
            pager.addHiddenInputs("category_name_like", "${fn:escapeXml(af.map.category_name_like)}");
            pager.addHiddenInputs("cash_price_min", "${fn:escapeXml(af.map.cash_price_min)}");
            pager.addHiddenInputs("cash_price_max", "${fn:escapeXml(af.map.cash_price_max)}");
            document.write(pager.toString());
		   </script>
		   </div></td>
      </tr>
    </table>
   </form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>                                  
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">

</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
