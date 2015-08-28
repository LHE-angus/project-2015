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
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/PersonPwd">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
       <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;真实姓名：
          		<html-el:text property="real_name_like" size="20" maxlength="20" styleId="real_name_like"  />
          		&nbsp;&nbsp;身份证号码：	
          		<html-el:text property="id_card_no_like" size="20" maxlength="20" styleId="id_card_no_like"  />
          		&nbsp;&nbsp;&nbsp;&nbsp;
        		<input class="but1" type="submit" name="Submit" value="搜索" />
        	</td>
        </tr>
      </table>
    </html-el:form>
  </div>
 <div class="rtabcont1">
    <%@ include
	file="/commons/pages/messages.jsp"%>
  </div>
  <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
        <tr class="tabtt1">
          <td width="5%" align="center">序号</td>
          <td width="15%" nowrap="nowrap">姓名</td>
          <td nowrap="nowrap">身份证号码</td>
          <td width="10%" nowrap="nowrap" align="center">邮箱</td>
          <td width="10%" nowrap="nowrap" align="center">注册时间</td>
          <td width="15%" nowrap="nowrap" align="center">操作</td>
        </tr>    
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap">${vs.count}</td>
              <td align="left" nowrap="nowrap" >${cur.real_name}</td>
              <td align="left" nowrap="nowrap" >${cur.id_card_no}</td>
              <td align="left" nowrap="nowrap">${cur.email}</td>
              <td align="center" nowrap="nowrap"  ><fmt:formatDate value="${cur.reg_date}" pattern="yyyy-MM-dd HH:mm" /></td>
              <td align="center" valign="top">
               	<span style="cursor:pointer;" class="fblue" onclick="if(confirm('是否重置？')){doNeedMethod(null, 'PersonPwd.do', 'initPassword','id=${cur.id}&'+$('#bottomPageForm').serialize())}" title="重置密码">初始化密码</span>
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
            </tr>
          </c:forEach>
        </tbody>
      </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="PersonPwd.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
			var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("real_name_like", "${af.map.real_name_like}");
            pager.addHiddenInputs("id_card_no_like", "${af.map.id_card_no_like}");
			document.write(pager.toString());
            </script>
           </td>
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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
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
