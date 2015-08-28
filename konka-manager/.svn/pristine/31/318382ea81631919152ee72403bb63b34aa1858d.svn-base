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
      <!--  <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td> --> 
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/SsoOaUserBind">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
       <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td nowrap="nowrap">&nbsp;&nbsp;           
            <strong class="fb">绑定用户：</strong>      <html-el:text property="user_name" styleId="user_name" size="20" maxlength="40" />      
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      	      	           
            <strong class="fb">OA用户名：</strong>      <html-el:text property="samaccount_name" styleId="samaccount_name" size="20" maxlength="40" />   
           </td>
        </tr>
        <tr>
          <td nowrap="nowrap">&nbsp;&nbsp;      		
          	<strong class="fb">绑定时间：</strong>
        		<html-el:text property="add_date_start" styleId="add_date_start"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				至
				<html-el:text property="add_date_end" styleId="add_date_end"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
  			&nbsp;&nbsp;&nbsp;<strong class="fb">状态：</strong>
            <html-el:select property="state" styleId="state">
            	<html-el:option value="">--请选择--</html-el:option>
            	<html-el:option value="0">正常</html-el:option>
            	<html-el:option value="1">注销</html-el:option>
            </html-el:select>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="but1" type="submit" name="Submit" value="搜索" />
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
          <input class="but3" type="submit" name="Submit3" value="删除" onclick="confirmDeleteAll(document.getElementById('listForm'));" /> 
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
  <div style="overflow-x:scroll;">
    <form id="listForm" name="listForm" method="post" action="SsoOaUserBind.do?method=delete">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
        <tr class="tabtt1">
          <td width="5%" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
          <td width="8%" nowrap="nowrap" align="center">绑定时间</td>
          <td width="8%" nowrap="nowrap" align="center">OA用户名</td>
          <td width="5%" nowrap="nowrap" align="center">关联表</td>
          <td width="5%" nowrap="nowrap" align="center">绑定用户名</td> 
          <td width="5%" nowrap="nowrap" align="center">状态</td> 
          <td width="8%" nowrap="nowrap" align="center">操作</td>
        </tr>    
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap" >
                <input name="pks" type="checkbox" id="pks_${cur.id}" value="${cur.id}" />
              </td>
              <td align="left" nowrap="nowrap" ><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
               <td align="left" nowrap="nowrap">${cur.samaccount_name}</td>
              <td align="left" nowrap="nowrap">${cur.link_tab}</td>
              <td align="left" nowrap="nowrap">${cur.user_name}</td> 
              <td align="center" nowrap="nowrap">
              <c:if test="${cur.state eq 0}">正常</c:if>
              <c:if test="${cur.state eq 1}">注销</c:if>
              </td>
              <td align="center" nowrap="nowrap">
                <c:choose>
		              <c:when test="${fn:contains(popedom, '+8+')}">
		             <span style="cursor: pointer;color: blue;" onclick="doNeedMethod(null, 'SsoOaUserBind.do', 'valid','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">启用</span>|
		              </c:when>
		              <c:otherwise>
		              <span style="color:#ccc;">启用</span>|
		              </c:otherwise>
			    </c:choose>
			    <c:choose>
		              <c:when test="${fn:contains(popedom, '+16+')}">
		             <span style="cursor: pointer;color: blue;" onclick="doNeedMethod('是否注销！', 'SsoOaUserBind.do', 'invalid','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">注销</span>|
		              </c:when>
		              <c:otherwise>
		              <span style="color:#ccc;">注销</span>|
		              </c:otherwise>
			    </c:choose>
                <span style="cursor: pointer;color: blue;" onclick="confirmUpdate(null, 'SsoOaUserBind.do', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">修改</span>
                |
                <span style="cursor: pointer;color: blue;" onclick="confirmDelete(null, 'SsoOaUserBind.do', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">删除</span>
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
    </div>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="SsoOaUserBind.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
			var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("samaccount_name", "${fn:escapeXml(af.map.samaccount_name)}");
            pager.addHiddenInputs("user_name", "${fn:escapeXml(af.map.user_name)}");
			pager.addHiddenInputs("state", "${af.map.state}");
			pager.addHiddenInputs("add_date_end", "${af.map.add_date_end}");
			pager.addHiddenInputs("add_date_start", "${af.map.add_date_start}");
			pager.addHiddenInputs("type_id", "${af.map.type_id}");
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
