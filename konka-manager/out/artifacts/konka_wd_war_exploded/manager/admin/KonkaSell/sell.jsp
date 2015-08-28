<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
    <html-el:form action="/admin/KonkaSell">
      <html-el:hidden property="method" value="sell" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="add_date_start" value="${af.map.add_date_start}"/>
      <html-el:hidden property="add_date_end" value="${af.map.add_date_end}"/>
      <html-el:hidden property="r3_shop_id" />
       <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
        <td width="15"></td>
          <td width="350"><strong class="fb">销售时间：</strong>
			      <html-el:text property="sell_date_start"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
			              至
			      <html-el:text property="sell_date_end"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
          <td width="50"></td>
          <td><strong class="fb">状态：</strong>
			      	<html-el:select property="state" styleId="state">
				        <html-el:option value="">请选择...</html-el:option>
				        <html-el:option value="0">已暂存</html-el:option>
				        <html-el:option value="1">已上传</html-el:option>
			      	</html-el:select></td>
		  <td><html-el:submit value="搜索" styleClass="but1"/></td>
        </tr>
     </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
  <logic-el:match name="popedom" value="+1+">
  <c:if test="${empty af.map.key }">
  <input type="button" name="add" id="add" value=" 上报数据 " onclick="location.href='KonkaSell.do?method=add&r3_shop_id=${af.map.r3_shop_id}&mod_id=${af.map.mod_id}&report_count=${af.map.pager.recordCount}&'+$('#bottomPageForm').serialize();" />
  </c:if>
  <c:if test="${not empty af.map.key }">
  <input type="button" name="add" id="add" value=" 上报数据 " onclick="alert('此网点还未进行过库存初始操作，请先初始库存后再进行销售上报。注意：初始库存时请注意库存日期！');location.href='KonkaStock.do?method=edit&r3_shop_id=${af.map.r3_shop_id}&mod_id=201010';" />
  </c:if>
</logic-el:match>
  </div>
    <div class="rtabcont1">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap">序号</td>
        <td nowrap="nowrap">标题</td>
        <td width="40" nowrap="nowrap">时间</td>
        <td width="10%" nowrap="nowrap">上报人</td>
        <td width="5%" nowrap="nowrap">状态</td>
        <td width="120" align="center">操作</td>
        <c:if test="${is_leader}">
        	<th width="6%" nowrap="nowrap">锁定</th>
        </c:if>
      </tr>
      <c:forEach items="${konkaSellList}" var="cur" varStatus="vs">
        <tr>
          <td align="center">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
          <td align="left" title="${cur.title}">${cur.title}</td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="HH:mm" /></td>
          <td align="left" nowrap="nowrap"><c:out value="${cur.add_real_name}" /></td>
          <td align="center" nowrap="nowrap">
          	<c:choose>
          		<c:when test="${cur.state eq 0}">已暂存</c:when>
          		<c:when test="${cur.state eq 1}">已上传</c:when>
          		<c:when test="${cur.state eq 2}">已锁定</c:when>
          		<c:otherwise>状态错误</c:otherwise>
          	</c:choose>
          </td>
          <td align="center" nowrap="nowrap">
          <logic-el:match name="popedom" value="+0+">
          	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaSell.do', 'view','s_id=${cur.s_id}&'+$('#bottomPageForm').serialize())">查看</span>
      	  </logic-el:match>
      	  <logic-el:match name="popedom" value="+2+">
      		<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaSell.do', 'edit','s_id=${cur.s_id}&r3_shop_id=${af.map.r3_shop_id}&'+$('#bottomPageForm').serialize())">修改</span>
      	  </logic-el:match>
      	  <logic-el:notMatch name="popedom" value="+2+">
				<span style="color:#CCC;">修改</span>
			</logic-el:notMatch>
      	  <logic-el:match name="popedom" value="+4+">	
      		<span style="cursor:pointer;" class="fblue" onclick="confirmDelete(null, 'KonkaSell.do', 'mod_id=${af.map.mod_id}&s_id=${cur.s_id}&r3_shop_id=${af.map.r3_shop_id}&pager.requestPage=${af.map.pager.requestPage}')">删除</span>
          </logic-el:match>
          <logic-el:notMatch name="popedom" value="+4+">
				<span style="color:#CCC;">删除</span>
			</logic-el:notMatch>
          	<!--<c:if test="${cur.state eq 1}">
          		<span style="color:#ccc;" class="fblue" >修改</span>
          		<span style="color:#ccc;" class="fblue" >删除</span>
          	</c:if>
           	<c:if test="${cur.state ne 1}"> -->
          	<!--</c:if> -->
          	
          </td>
          <c:if test="${is_leader}">
	          <td align="center" nowrap="nowrap">
	          	<c:if test="${cur.state eq 2}">
	          		<span style="color:#ccc;">锁定</span>
	          	</c:if>
	          	<c:if test="${cur.state ne 2}">
	          		<span style="cursor:pointer;"  onclick="javascript:lock('${cur.s_id}');">锁定</span>
	          	</c:if>
	          </td>
          </c:if>
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
          <c:if test="${is_leader}">
          	<td>&nbsp;</td>
          </c:if>
        </tr>
      </c:forEach>
    </table>
  </div>
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="${ctx}/manager/admin/KonkaSell.do">
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
          <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "sell");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("r3_shop_id", "${af.map.r3_shop_id}");
            pager.addHiddenInputs("cus_sn_like", "${af.map.cus_sn_like}");
            pager.addHiddenInputs("sell_date_start", "${af.map.sell_date_start}");
            pager.addHiddenInputs("sell_date_end", "${af.map.sell_date_end}");
            pager.addHiddenInputs("state", "${af.map.state}");
            document.write(pager.toString());
            </script></td>
      </tr>
    </table>
  </form>
  </div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
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
