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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/javascripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>  
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
      <html-el:form action="/admin/KonkaBooths">
        <html-el:hidden property="method" value="list" />
        <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
 		<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
       	 <tr>
       	    <td width="15"></td>
 			<td><strong class="fb">R3编码　：</strong><html-el:text property="r3_code_like" size="15" maxlength="20" styleId="r3_code_like"  /></td>
 			<td><strong class="fb">分公司名称</strong>：
	        	<html-el:select property="branch_area_name_select" styleId="branch_area_name_select" value="${af.map.branch_area_name_select }">
	        	<html-el:optionsCollection name="BranchList" label="branch_area_name" value="branch_area_name" />
	        	</html-el:select>
          	</td>
 			<td></td>
       	 </tr>
       	 <tr>
       	     <td width="15"></td>
 			 <td><strong class="fb">业务时间：</strong> <html-el:text property="date_start" size="10" maxlength="10" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM'})" style="cursor:pointer;text-align:center;" title="点击选择日期" />
                 	 至
      			<html-el:text property="date_end" size="10" maxlength="10" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM'});" style="cursor:pointer;text-align:center;" title="点击选择日期" />
 			 </td>
 			  <td> </td>
	        <td><html-el:submit styleClass="but1" value="搜索" /></td>
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
		    <input name="button" type="button"  class="but2" value=" 新增 " onclick="location.href='KonkaBooths.do?method=add&mod_id=${af.map.mod_id}';" />
		    </logic-el:match>
		    <logic-el:match name="popedom" value="+1+">  
		    <input name="button" type="button"  class="but2" value=" 导出 " onclick="location.href='KonkaBooths.do?method=toExcel&mod_id=${af.map.mod_id}';" />
		    </logic-el:match>
		 </td>
	    </tr>
	  </table>  
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <!-- <input type="button" onclick="doNeedMethod(null, 'KonkaSell.do','totle' ,$('#bottomPageForm').serialize())" value="合计"></input> -->
    <form id="listForm" name="listForm" method="post" action="SellDataStatistics.do?method=delete">
        <input type="hidden" name="method" id="method" value="delete" />
        <input type="hidden" name="mod_code" id="mod_code" value="${af.map.mod_code}" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="30" align="center">序号</td>
          <td nowrap="nowrap" align="center">门店名称</td>
          <td nowrap="nowrap" align="center">年累计销售额</td>
          <td nowrap="nowrap" align="center">展台延米数</td>
          <td nowrap="nowrap" align="center">有无直销员</td>
          <td nowrap="nowrap" align="center">直销员姓名</td>
          <td nowrap="nowrap" align="center">直销员电话</td>
          <td nowrap="nowrap" align="center">最近改造时间</td>
          <td nowrap="nowrap" align="center">形象照片</td>
          <td nowrap="nowrap" align="center">门店地址</td>
          <td nowrap="nowrap" align="center">操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count }</td>
            <td align="left" nowrap="nowrap">${cur.map.r3_name}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.booths_sale}" type="currency" pattern="0.00" /></td>
            <td nowrap="nowrap">${cur.booths_num}</td>
            <td nowrap="nowrap">
            <c:choose>
            <c:when test="${cur.if_man eq 0}">无</c:when>
            <c:when test="${cur.if_man eq 1}">有</c:when>
            </c:choose>
            </td>
            <td nowrap="nowrap">${cur.man_name}</td>
            <td nowrap="nowrap">${cur.man_phone}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.last_rebuild}" type="both" pattern="yyyy-MM-dd"/></td>
            <td nowrap="nowrap">
            <c:if test="${cur.photos ne null}">
            	<a href="${ctx}/${cur.photos}" target="_blank"><img src="${ctx}/${cur.photos}" style="max-width:160px;max-height:120px;" /></a>
            </c:if>
            </td>
            <td nowrap="nowrap">${cur.map.r3_addr}</td>
            <td nowrap="nowrap">
				<logic-el:match name="popedom" value="+4+">
					<span style="cursor:pointer;" onclick="doNeedMethod(null, 'KonkaBooths.do','delete' ,'mod_id=${af.map.mod_id}&id=${cur.booths_id}&' + $('#bottomPageForm').serialize())">删</span>
				</logic-el:match>
				<logic-el:notMatch name="popedom" value="+4+">
					<span style="color:#CCC;">删</span>
				</logic-el:notMatch>
				<logic-el:match name="popedom" value="+8+">
					<span style="cursor:pointer;" onclick="doNeedMethod(null, 'KonkaBooths.do','edit' ,'mod_id=${af.map.mod_id}&id=${cur.booths_id}&' + $('#bottomPageForm').serialize())">改</span>
				</logic-el:match>
				<logic-el:notMatch name="popedom" value="+8+">
					<span style="color:#CCC;">改</span>
				</logic-el:notMatch>
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
          </tr>
        </c:forEach>
      </table>
    </form>
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="SellDataStatistics.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("date_start", "${af.map.date_start}");
            pager.addHiddenInputs("date_end", "${af.map.date_end}");
            pager.addHiddenInputs("r3_code_like", "${af.map.r3_code_like}");
            pager.addHiddenInputs("branch_area_name_select", "${af.map.branch_area_name_select}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>