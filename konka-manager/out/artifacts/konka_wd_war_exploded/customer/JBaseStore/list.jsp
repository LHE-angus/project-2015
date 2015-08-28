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
<title>仓库信息</title>
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
	<html-el:form action="/manager/JBaseStore">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
	  <div class="rtabcont1">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
	      <tr>
	        <td height="36" align="left" style="padding-left:5px;">
	          &nbsp;<strong class="fb">仓库名称：</strong>
	          <html-el:text property="store_name_like" styleClass="webinput" styleId="store_name_like" maxlength="40"/>
	          &nbsp;<strong class="fb">仓库编码：</strong>
	          <html-el:text property="store_sn_like" styleClass="webinput" styleId="store_sn_like" maxlength="40"/>
	          &nbsp;
	          <strong class="fb">是否停用：</strong>
              <html-el:select property="is_del" styleId="is_del" style="width:70px;" onchange="this.form.submit();">
                <html-el:option value="">请选择</html-el:option>
                <html-el:option value="0">未停用</html-el:option>
                <html-el:option value="1">已停用</html-el:option>
              </html-el:select>&nbsp;&nbsp;
	          &nbsp;<strong class="fb">送达方编码：</strong>
	          <html-el:text property="sale_r3_code_like" styleClass="webinput" styleId="sale_r3_code_like" maxlength="20"/>&nbsp;&nbsp;&nbsp;&nbsp;
	          <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" />
	        </td>
	      </tr>
	    </table>
	  </div>
	</html-el:form>
	<div class="rtabcont1">
	  <%@ include file="/commons/pages/messages.jsp" %>
	  <input name="input" type="button" id="gotoAdd" class="bgButtonAdd" value="新 增" onclick="location.href='${ctx}/customer/manager/JBaseStore.do?method=add&mod_id=${af.map.mod_id}'" />
	  <font color="red">注：总库的送达方必须是客户自己，并且默认进出货仓库应该是总库</font>
	</div>
	<div class="rtabcont1">
	<div style="overflow-x: auto">
	  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
	    <tr>
	      <th width="5%" nowrap="nowrap">序号</th>
	      <th width="11%" nowrap="nowrap">仓库编码</th>
	      <th width="15%" nowrap="nowrap">仓库名称</th>
	      <th width="20%" nowrap="nowrap">仓库地址</th>
	      <th width="10%" nowrap="nowrap">送达方编码</th>
	      <th width="15%" nowrap="nowrap">备注</th>
	      <th width="6%" nowrap="nowrap">默认进货仓库</th>
	      <th width="6%" nowrap="nowrap">默认出货仓库</th>
	      <th width="4%" nowrap="nowrap">是否停用</th>
	      <th width="8%" nowrap="nowrap">添加时间</th>
	      <th width="10%" nowrap="nowrap">操作</th>
	    </tr>
	    <c:forEach items="${entityList}" var="cur" varStatus="vs">
	      <tr>
	        <td align="center" bgcolor="#fff2dc">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
	        <td align="left" nowrap="nowrap">${cur.store_sn}</td>
	        <td align="left" nowrap="nowrap">${cur.store_name}</td>
	        <td align="left" nowrap="nowrap">${cur.store_addr}</td>
	        <td align="left" nowrap="nowrap">${cur.map.sale_r3_code}</td>
	        <td align="left" nowrap="nowrap">${cur.remarks}</td>
	        <td align="center" nowrap="nowrap">
	        	<c:if test="${cur.is_dft_buy_store eq 1}"><span style="color:green;">是</span></c:if>
	          	<c:if test="${cur.is_dft_buy_store eq 0}"><span>否</span></c:if>
	        </td>
	        <td align="center" nowrap="nowrap">
	        	<c:if test="${cur.is_dft_sell_store eq 1}"><span style="color:green;">是</span></c:if>
	          	<c:if test="${cur.is_dft_sell_store eq 0}"><span>否</span></c:if>
	        </td>
	        <td align="center" nowrap="nowrap">
	        	<c:if test="${cur.is_del eq 0}"><span style="color:green;">否</span></c:if>
	          	<c:if test="${cur.is_del eq 1}"><span style="color:#f00;">是</span></c:if>
	        </td>
	        <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
	        <td align="center" nowrap="nowrap">
	            <c:if test="${cur.is_del eq 0}">
	            <c:if test="${cur_r3_code ne cur.map.sale_r3_code}">
	        	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'JBaseStore.do', 'edit','&mod_id=${af.map.mod_id}&store_id=${cur.store_id}&'+$('#bottomPageForm').serialize())">修改</span> | 
	        	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('您确认停用吗？', 'JBaseStore.do', 'delete','&store_id=${cur.store_id}&'+$('#bottomPageForm').serialize())">停用</span>
	        	</c:if>
	        	<c:if test="${cur_r3_code eq cur.map.sale_r3_code}">
	        	<span style="color:grey;">修改</span> | 
	        	<span style="color:grey;">停用</span>
	        	</c:if>
	        	</c:if>
	        	<c:if test="${cur.is_del eq 1 }">
	        	<span class="fblue" style="color:#ccc;">修改</span>   | 
	        	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('您确定启用吗？', 'JBaseStore.do', 'reStart','&mod_id=${af.map.mod_id}&store_id=${cur.store_id}&'+$('#bottomPageForm').serialize());">启用</span>  
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
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
        	</c:forEach>
        </c:if>
	  </table>
	  </div>
	  <c:if test="${not empty entityList}">
	    <div class="rtabcont3">
	      <form id="bottomPageForm" name="bottomPageForm" method="post" action="JBaseStore.do">
	        <table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">
	          <tr>
	            <td height="40" align="center"><script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "list");
		            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
		            pager.addHiddenInputs("store_sn_like", "${af.map.store_sn_like}");
		            pager.addHiddenInputs("store_name_like", "${af.map.store_name_like}");
		            pager.addHiddenInputs("sale_r3_code_like", "${af.map.sale_r3_code_like}");
		            pager.addHiddenInputs("is_del", "${af.map.is_del}");
		            document.write(pager.toString());
		            </script></td>
	          </tr>
	        </table>
	      </form>
	    </div>
	  </c:if>
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