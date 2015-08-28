<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
      <td>当前位置：${naviString}</td>
      <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
    </tr>
  </table>
</div>
<div class="rtabcont1">
  <html-el:form action="/admin/OpinionType">
    <html-el:hidden property="method" value="list" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
      <tr>
        <td width="80%" nowrap="nowrap">   
         &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">意见类型名称：</strong>
          <html-el:text property="c_name_like" maxlength="40" style="width:90px;" />
          &nbsp;&nbsp;
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
		    <input class="but2" type="button" name="Submit2" value="新增" onclick="location.href='OpinionType.do?method=add&mod_id=${af.map.mod_id}';" />
		    </logic-el:match>
		</td>
	 </tr>
	</table>
  </div>
  <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="30" align="center" nowrap="nowrap">序号</td>
          <td nowrap="nowrap">资讯类别名称</td>
          <td width="12%" nowrap="nowrap" align="center">操作</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td nowrap="nowrap" align="center">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
              <td align="left"><font class="blue12px">${fn:escapeXml(cur.c_name)}</font></td>
              <td align="center" nowrap="nowrap">
              <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'OpinionType.do', 'view','c_index=${cur.c_index}&'+$('#bottomPageForm').serialize())">查看</span>
              |
              <logic-el:match name="popedom" value="+2+"><span class="fblue" style="cursor:pointer;" onclick="doNeedMethod(null, 'OpinionType.do', 'edit','c_index=${cur.c_index}&'+$('#bottomPageForm').serialize())">修改</span></logic-el:match>
              <logic-el:notMatch name="popedom" value="+2+"><span class="fblue" style="color:#ccc;">修改</span></logic-el:notMatch>
              |
              <logic-el:match name="popedom" value="+4+"><span class="fblue" style="cursor:pointer;" onclick="confirmDelete(null, 'OpinionType.do', 'c_index=${cur.c_index}&'+$('#bottomPageForm').serialize())">删除</span></logic-el:match>
			  <logic-el:notMatch name="popedom" value="+4+"><span class="fblue" style="color:#ccc;">删除</span></logic-el:notMatch>
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
            </tr>
          </c:forEach>
        </tbody>
      </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="OpinionType.do">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
			            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			            pager.addHiddenInputs("method", "list");
			            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			            pager.addHiddenInputs("tree_param", "${tree_param}");
			            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
			            pager.addHiddenInputs("c_name_like", "${af.map.c_name_like}");
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
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript">//<![CDATA[
   $(document).ready(function() {
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
