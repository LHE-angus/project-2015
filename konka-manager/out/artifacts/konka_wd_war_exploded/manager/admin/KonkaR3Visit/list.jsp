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
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/KonkaR3Visit">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>            
          <td><strong class="fb">模糊搜索：</strong>
              <html-el:text property="keyword" size="20" maxlength="20" styleId="keyword" title="请输入客户名称或所属地区或分公司所在地"/>
          </td>
          <td><strong class="fb">R3编码：</strong><html-el:text property="code_like" size="20" maxlength="20" styleId="code_like"  />
       	  </td>
       	  <td><strong class="fb">拜访状态：</strong>
                <html-el:select property="shop_visit_status" styleId="shop_visit_status" >
                  <html-el:option value="">-请选择-</html-el:option>
                  <html-el:option value="0">未拜访</html-el:option>
                  <html-el:option value="1">拜访中</html-el:option>
                  <html-el:option value="2">拜访完成</html-el:option>
                </html-el:select>
          </td>
        <td><input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="KonkaR3Visit.do">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
          <tr class="tabtt1">
            <td width="30" nowrap="nowrap" ><font class="blue">序号</font></td>
            <td width="80"  align="left" ><font class="blue">R3编码</font></td>
            <td width="nowrap" align="left" ><font class="blue">网点名称</font></td>
            <td width="80" nowrap="nowrap" align="center" ><font class="blue">拜访状态</font></td>
            <td width="80" align="center"><font class="blue">拜访次数</font></td>
            <td width="90" align="center" nowrap="nowrap" ><font class="blue">最后拜访日期</font></td>
            <td width="100" align="center"><font class="blue">操作</font></td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
              <td align="left">${cur.r3_code}</td>
              <td align="left">${cur.customer_name} </td>
              <td align="center">
                 <c:if test="${cur.map.visit_count eq 0}"> <span style="color: green;">未拜访</span> </c:if>
                 <c:if test="${cur.map.visit_count ne 0}">  
                     <c:if test="${cur.map.visit_status eq 1}"> <span style="color: #F00;">拜访中</span></c:if>
                     <c:if test="${cur.map.visit_status eq 2}"> <span style="color: #F00;">拜访完成</span></c:if>
                 </c:if>       
              </td>
              <td align="center">
                  ${cur.map.visit_count}
              </td>
              <td align="center">
               <c:if test="${cur.map.visit_count eq 0}"> -- </c:if>
               <c:if test="${cur.map.visit_count ne 0}"><fmt:formatDate value="${cur.map.visit_date }" pattern="yyyy-MM-dd"></fmt:formatDate></c:if>
              </td>
              <td align="center" nowrap="nowrap">
                <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaR3Visit.do', 'visit','shop_id=${cur.id}&shop_name=${cur.customer_name}&visit_count=${cur.map.visit_count}&'+$('#bottomPageForm').serialize())">拜访</span>
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
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaR3Visit.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("tree_param", "${tree_param}");
	            pager.addHiddenInputs("keyword", "${af.map.keyword}");
	            pager.addHiddenInputs("code_like", "${af.map.code_like}");
	            pager.addHiddenInputs("shop_visit_status","${af.map.shop_visit_status}");
	            document.write(pager.toString());
	      </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
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
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>