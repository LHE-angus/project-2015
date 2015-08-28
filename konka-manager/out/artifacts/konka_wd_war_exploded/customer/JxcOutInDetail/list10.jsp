<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	select{font-family:Microsoft YAHEI;font-size:12px;}
	input{font-family:Microsoft YAHEI;font-size:12px;}
label {cursor:pointer;}
</style>
<title>初始化库存</title>
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
	<html-el:form action="/manager/JxcOutInDetail">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="type" value="10" />
	  <div class="rtabcont1">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
	      <tr>
	        <td height="36" align="left" style="padding-left:5px;">
	          &nbsp;<strong class="fb">移出仓库：</strong>
	          <html-el:select property="out_store_id" styleId="out_store_id">
	        		<html-el:option value="">请选择</html-el:option>
	          		<c:forEach var="cur" items="${jBaseStoreList}" varStatus="vs">
	          			<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
	          		</c:forEach>
	            </html-el:select>
	          &nbsp;<strong class="fb">移入仓库：</strong>
	          <html-el:select property="in_store_id" styleId="in_store_id">
	        		<html-el:option value="">请选择</html-el:option>
	          		<c:forEach var="cur" items="${jBaseStoreList}" varStatus="vs">
	          			<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
	          		</c:forEach>
	            </html-el:select>
	          &nbsp;<strong class="fb">移仓编码：</strong>
	          <html-el:text property="trans_index_like" styleClass="webinput" styleId="trans_index_like" maxlength="30"/>
	          &nbsp;<strong class="fb">商品名称：</strong>
	          <html-el:text property="goods_name_like" styleClass="webinput" styleId="goods_name_like" maxlength="60"/>
	          &nbsp;<strong class="fb">移出日期：</strong>
	          <input name="out_date_s" id="out_date_s" size="12" value="${af.map.out_date_s}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'2013-11-01',maxDate:'#F{$dp.$D(\'out_date_e\')}'})" />
	            	    至
	          <input name="out_date_e" id="out_date_e" size="12"  value="${af.map.out_date_e}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'#F{$dp.$D(\'out_date_s\')||\'2013-11-01\'}'})" />
	          &nbsp;
	          <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" />
	        </td>
	      </tr>
	    </table>
	  </div>
	</html-el:form>
	<div class="rtabcont1">
	  <%@ include file="/commons/pages/messages.jsp" %>
	  <input name="input" type="button" id="gotoAdd" class="bgButtonAdd" value="新 增" onclick="location.href='${ctx}/customer/manager/JxcOutInDetail.do?method=add&mod_id=${af.map.mod_id}&type=10'" />
<%-- 	  <input name="input" type="button" id="but_excel" class="but_excel" value="导 入" onclick="location.href='${ctx}/customer/manager/JBaseGoodsInitStock.do?method=excel&mod_id=${af.map.mod_id}'" /> --%>
	</div>
	<div class="rtabcont1">
	<div style="overflow-x: auto;">
	  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
	    <tr>
	      <th width="4%">序号</th>
	      <th width="10%">移出编码</th>
	      <th width="10%">移出仓库</th>
	      <th width="8%">移入仓库</th>
	      <th width="10%">型号</th>
	      <th width="8%">移入数量（台）</th>
	      <th width="8%">单价（元）</th>
	      <th width="8%">库存金额（元）</th>
	      <th width="8%">移出时间</th>
<!-- 	      <th width="8%">备注</th> -->
	      <th width="10%">操作</th>
	    </tr>
	    <c:forEach items="${entityList}" var="cur" varStatus="vs">
	      <tr>
	        <td align="center" bgcolor="#fff2dc">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
	        <td align="center">&nbsp;${cur.trans_index }&nbsp;</td>
	        <td align="left">&nbsp;${cur.map.out_store_name }&nbsp;</td>
	        <td align="left">&nbsp;${cur.map.in_store_name}&nbsp;</td>
	        <td align="center">&nbsp;${cur.goods_name }&nbsp;</td>
	        <td align="right">&nbsp;${cur.in_num}&nbsp;</td>
	        <td align="right">&nbsp;${cur.out_price}&nbsp;</td>
	        <td align="right">&nbsp;${cur.out_money}&nbsp;</td>
	        <td align="center"><fmt:formatDate value="${cur.out_date }" pattern="yyyy-MM-dd"/></td>
<%-- 	        <td align="left">&nbsp;${cur.memo }&nbsp;</td> --%>
	        <td align="center" nowrap="nowrap">
	        	<span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'JxcOutInDetail.do', 'view','&mod_id=${af.map.mod_id}&id=${cur.id}&'+$('#bottomPageForm').serialize())">查看</span> 
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
<!--                   <td>&nbsp;</td> -->
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
<!--                 <td>&nbsp;</td> -->
                <td>&nbsp;</td>
              </tr>
        	</c:forEach>
        </c:if>
	  </table>
	  <c:if test="${not empty entityList}">
	    <div class="rtabcont3">
	      <form id="bottomPageForm" name="bottomPageForm" method="post" action="JxcOutInDetail.do">
	        <table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">
	          <tr>
	            <td height="40" align="center"><script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "list");
		            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
		            pager.addHiddenInputs("type", "${af.map.type}");
		            pager.addHiddenInputs("in_store_id", "${af.map.in_store_id}");
		            pager.addHiddenInputs("out_store_id", "${af.map.out_store_id}");
		            pager.addHiddenInputs("out_date_s", "${af.map.out_date_s}");
		            pager.addHiddenInputs("out_date_e", "${af.map.out_date_e}");
		            pager.addHiddenInputs("goods_name_like", "${af.map.goods_name_like}");
		            pager.addHiddenInputs("trans_index_like", "${af.map.trans_index_like}");
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
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#out_store_id").multiselect({
		noneSelectedText: '<span >=请选择=</span>',
		selectedList: 1,
		multiple: false,
		minWidth:160
	}).multiselectfilter({label:"<span>搜索：</span>"});
	$("#in_store_id").multiselect({
		noneSelectedText: '<span >=请选择=</span>',
		selectedList: 1,
		multiple: false,
		minWidth:160
	}).multiselectfilter({label:"<span>搜索：</span>"});
});	
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>