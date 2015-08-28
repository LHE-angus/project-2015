<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title>品牌管理</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：库存管理 &gt; 品牌申请</div>
<div class="rtabcont1">
  <html-el:form action="/JxcBrandApply">
    <html-el:hidden property="keySeq" value="${af.map.keySeq}"/>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="36" ><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
            <tr>
              <td width="8%"><strong class="fb">&nbsp;&nbsp;品牌名称：</strong>
                <input  name="brand_name" type="text" id="brand_name" class="webinput" value="${af.map.brand_name}"/>
                &nbsp;&nbsp;<strong class="fb">审核状态：</strong>
                <html-el:select property="states" styleId="states" styleClass="bdfont" >
                  <html-el:option value="">--请选择--</html-el:option>
                  <html-el:option value="0">未审核</html-el:option>
                  <html-el:option value="1">审核通过</html-el:option>
                  <html-el:option value="-1">审核未通过</html-el:option>
                </html-el:select>
                &nbsp;&nbsp;
                <input name="button" type="button" class="bgSearch" id="button" onclick="location.href='JxcBrandApply.do?method=list&mod_id=${af.map.mod_id}&keySeq=${af.map.keySeq}&brand_name='+$('#brand_name').val()+'&states='+$('#states').val();" value="搜 索" /></td>
            </tr>
          </table></td>
      </tr>
    </table>
  </html-el:form>
</div>
<div class="rtabcont1">
  <input name="add" type="button" class="bgButtonAdd" value="新 增" onclick="location.href='JxcBrandApply.do?method=add&mod_id=${af.map.mod_id}&keySeq=${af.map.keySeq}';"/>
</div>
<div class="rtabcont1">
  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
    <tr>
      <th width="4%" >行号</th>
      <th width="15%" >品牌名称</th>
      <th width="10%" >申请人ID</th>
      <th width="12%" >申请人用户名</th>
      <th width="8%" >状态</th>
      <th width="27%" >审核备注</th>
      <th width="16%" >申请时间</th>
      <th width="8%" >操作</th>
    </tr>
    <c:forEach var="cur" items="${entityList}" varStatus="vs">
      <tr align="center">
        <td align="center" >${vs.count}</td>
        <td align="center" height="30">${fn:escapeXml(cur.brand_name)}</td>
        <td align="center" height="30">${fn:escapeXml(cur.apply_user_id)}</td>
        <td align="center" height="30">${fn:escapeXml(cur.apply_user_name)}</td>
        <td align="center" height="30"><c:if test="${cur.states eq 0}">未审核</c:if>
          <c:if test="${cur.states eq 1}">审核通过</c:if>
          <c:if test="${cur.states eq -1}">审核未通过</c:if></td>
        <td align="left" height="30">${fn:escapeXml(cur.remark)}</td>
        <td align="center" height="30"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        <td><c:if test="${cur.states eq 1}"><span style="color:#ccc" class="fblue"  >修改 </span>
        |
        <span style="color:#ccc" class="fblue"  >停用</span> </c:if>
          <c:if test="${cur.states  ne 1}"><span style="cursor: pointer;"  class="fblue"  onclick="confirmUpdate(null, 'JxcBrandApply.do', 'id=${cur.id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">修改</span>
          |
          <span style="cursor: pointer;"  class="fblue"  onclick="confirmDelete('JxcBrandApply.do','${cur.id}');">停用</span></c:if></td>
      </tr>
    </c:forEach>
  </table>
</div>
<div id="showPage" >
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="JxcBrandApply.do">
    <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
          <script type="text/javascript">
            	var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("brand_name", "${af.map.brand_name}");
				pager.addHiddenInputs("states", "${af.map.states}");	
				pager.addHiddenInputs("keySeq", "${af.map.keySeq}");	
	            document.write(pager.toString());
            </script></td>
      </tr>
    </table>
  </form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript" src="${ctx}/javascripts/lightBox.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript">//<![CDATA[

function confirmDelete(url,id) {
		if(confirm("确认需要停用选中的品牌吗？")) {
			location.href=url+"?method=delete&id="+id+"&keySeq=${af.map.keySeq}";
		}
	}
//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
