<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	select{font-family:Microsoft YAHEI;font-size:12px;}
	input{font-family:Microsoft YAHEI;font-size:12px;}
</style>
<title>订单记录</title>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body style="font-family:Microsoft Yahei;">
	<div class="oartop">
	    <table width="400" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
	        <td>当前位置：${naviString}</td>
	      </tr>
	    </table>
  	</div>
	<html-el:form action="/manager/JBaseGoods">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
	  <div class="rtabcont1">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
	      <tr>
	        <td height="36" align="left" style="padding-left:5px;">
	          &nbsp;<strong class="fb">商品类型：</strong>
	          <html-el:select property="goods_type">
	          	<html-el:option value="">请选择...</html-el:option>
	          	<c:forEach items="${list10001}" var="cur">
	          	  <html-el:option value="${cur.type_id}">${cur.type_name}</html-el:option>
	          	</c:forEach>
	          </html-el:select>
	          &nbsp;<strong class="fb">商品名称：</strong>
	          <html-el:text property="goods_name_like" styleClass="webinput" styleId="goods_name_like" maxlength="40"/>
	          &nbsp;<strong class="fb">商品条码：</strong>
	          <html-el:text property="goods_sn_like" styleClass="webinput" styleId="goods_sn_like" maxlength="40"/>
	          &nbsp;<strong class="fb">商品状态：</strong>
	          <html-el:select property="goods_stutes" onchange="this.form.submit();">
	          	<html-el:option value="0">上架</html-el:option>
	          	<html-el:option value="1">下架</html-el:option>
	          </html-el:select>
	          &nbsp;
	          <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" />
	        </td>
	      </tr>
	    </table>
	  </div>
	</html-el:form>
	<div class="rtabcont1">
	  <%@ include file="/commons/pages/messages.jsp" %>
	  <input name="input" type="button" id="gotoAdd" class="bgButtonAdd" value="新 增" onclick="location.href='${ctx}/customer/manager/JBaseGoods.do?method=add&mod_id=${af.map.mod_id}'" />
	  <input name="input" type="button" id="but_excel" class="but_excel" value="导 入" onclick="location.href='${ctx}/customer/manager/ImportCustomerPdData.do?method=add&mod_id=${af.map.mod_id}'" />
	</div>
	<div class="rtabcont1">
	<div style="overflow-x: auto">
	  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
	    <tr>
	      <th width="5%">序号</th>
	      <th width="10%">商品类型</th>
	      <th width="">商品名称</th>
	      <th width="20%">商品条码</th>
	      <th width="8%">商品单位</th>
	      <th width="8%">商品状态</th>
	      <th width="10%">销售单价</th>
	      <th width="10%">操作</th>
	    </tr>
	    <c:forEach items="${entityList}" var="cur" varStatus="vs">
	      <tr>
	        <td align="center" bgcolor="#fff2dc">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
	        <td align="center" nowrap="nowrap">
	        	<c:forEach items="${list10001}" var="c1">
	        		<c:if test="${c1.type_id eq cur.goods_type}">${c1.type_name}</c:if>
	        	</c:forEach>
	        </td>
	        <td align="left"><c:if test="${cur.is_konka eq 1}"><span style="color:#F00;">[康佳]</span>&nbsp;</c:if>${cur.goods_name}</td>
	        <td align="left">${cur.goods_sn}</td>
	        <td align="center">
	        	<c:forEach items="${list10002}" var="c2">
	        		<c:if test="${c2.type_id eq cur.goods_unit}">${c2.type_name}</c:if>
	        	</c:forEach>
	        </td>
	        <td align="center">
	        	<c:if test="${cur.goods_stutes eq 0}"><span style="color:green;">上架</span></c:if>
	          	<c:if test="${cur.goods_stutes eq 1}"><span style="color:#f00;">下架</span></c:if>
	        </td>
	        <td align="right"><fmt:formatNumber value="${cur.sell_price}" pattern="0.00" /></td>
	        <td align="center" nowrap="nowrap">
	        	<c:if test="${cur.goods_stutes eq 0}">
	        	<span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'JBaseGoods.do', 'view','&mod_id=${af.map.mod_id}&goods_id=${cur.goods_id}&'+$('#bottomPageForm').serialize())">查看</span> | 
	        	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'JBaseGoods.do', 'edit','&mod_id=${af.map.mod_id}&goods_id=${cur.goods_id}&'+$('#bottomPageForm').serialize())">修改</span> | 
	        	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('您确定下架吗？', 'JBaseGoods.do', 'delete','&goods_id=${cur.goods_id}&'+$('#bottomPageForm').serialize())">下架</span>
	        	</c:if>
	        	<c:if test="${cur.goods_stutes eq 1}">
	        	<span class="fblue" style="color:#ccc;">查看</span> | 
	        	<span class="fblue" style="color:#ccc;">修改</span> | 
	        	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('您确定上架吗？', 'JBaseGoods.do', 'reStart','&mod_id=${af.map.mod_id}&goods_id=${cur.goods_id}&'+$('#bottomPageForm').serialize())">上架</span>
	        	</c:if>
	        </td>
	      </tr>
	      <c:if test="${vs.last}">
              <c:forEach begin="1" end="${9 - vs.index}">
                <tr>
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
           	</c:if>
	    </c:forEach>
	    <c:if test="${empty entityList}">
        	<c:forEach begin="0" end="9">
        	  <tr>
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
        </c:if>
	  </table>
	  <c:if test="${not empty entityList}">
	    <div class="rtabcont3">
	      <form id="bottomPageForm" name="bottomPageForm" method="post" action="JBaseGoods.do">
	        <table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">
	          <tr>
	            <td height="40" align="center"><script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "list");
		            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
		            pager.addHiddenInputs("goods_type", "${af.map.goods_type}");
		            pager.addHiddenInputs("goods_stutes", "${af.map.goods_stutes}");
		            pager.addHiddenInputs("goods_sn_like", "${af.map.goods_sn_like}");
		            pager.addHiddenInputs("goods_name_like", "${af.map.goods_name_like}");
		            document.write(pager.toString());
		            </script></td>
	          </tr>
	        </table>
	      </form>
	    </div>
	  </c:if>
	  </div>
	</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/customer/script/rowEffect.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

});	
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>