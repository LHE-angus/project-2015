<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <html-el:form action="/manager/KonkaJxcBaseAddr.do">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
        <tr>
          <td width="6%"><strong class="fb">收货人：</strong></td>
          <td width="18%"><html-el:text property="user_name_like" styleClass="webinput" styleId="user_name_like" /></td>
          <td><html-el:submit value="搜 索" styleClass="bgSearch"/></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <form id="listForm" name="listForm" method="post" action="KonkaJxcBaseAddr.do?method=delete">
    <div class="rtabcont1">
      <%@ include file="/commons/pages/messages.jsp" %>
      <input type="button" name="delete" id="delete" class="bgButton" value="删除全部" onclick="this.form.action += '&' + $('#bottomPageForm').serialize();confirmDeleteAll(this.form);" />&nbsp;
      <input type="button" name="add" id="add" class="bgButtonAdd" value="新 增" onclick="location.href='KonkaJxcBaseAddr.do?method=add&mod_id=${af.map.mod_id}';" />
    </div>
    <div class="rtabcont1">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr> 
          <th width="5%" nowrap="nowrap"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></th>
          <th width="10%"nowrap="nowrap">收货人</th>
          <th width="12%"nowrap="nowrap">联系电话</th>
          <th width="15%"nowrap="nowrap">所属区域</th>
          <th nowrap="nowrap">详细地址</th>
          <th width="8%"nowrap="nowrap">添加时间</th>
          <th width="8%"nowrap="nowrap">修改默认</th>
          <th width="10%"nowrap="nowrap">操作</th>
        </tr>
        <c:forEach items="${entityList}" var="cur" varStatus="vs">
          <tr> 
           <td align="center"> 
                <c:if test="${cur.is_del eq 0}"> 
                  <input name="pks" type="checkbox" id="pks" value="${cur.id}" /> 
                </c:if> 
                <c:if test="${cur.is_del eq 1}"> 
                  <input name="pks" type="checkbox" id="pks" value="${cur.id}" disabled="disabled"/> 
              </c:if></td>
            <td align="center" nowrap="nowrap">${cur.user_name} </td>
            <td nowrap="nowrap">
            <c:if test="${not empty cur.user_tel}">固定电话：${cur.user_tel}<br/></c:if>
            <c:if test="${not empty cur.user_phone}">手机号码：${cur.user_phone}</c:if>
            </td>
            <td nowrap="nowrap">${cur.map.full_name}</td>
            <td nowrap="nowrap">${fn:substring(cur.user_addr, 0, 40)}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
            <td align="center" nowrap="nowrap">
            <c:if test="${cur.is_default eq 0}">
            	默认地址
           </c:if>
            <c:if test="${cur.is_default ne 0}">
            	<span style="cursor:pointer;"  class="fblue" onclick="doNeedMethod('您确认设置成默认地址吗？', 'KonkaJxcBaseAddr.do', 'update_default','&id=${cur.id}&'+$('#bottomPageForm').serialize())">设置默认</span>
           </c:if>
            </td>
            <td align="center" nowrap="nowrap">
             <span style="cursor:pointer;"  class="fblue" onclick="confirmUpdate(null, 'KonkaJxcBaseAddr.do', 'id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">修改</span>
             <span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'KonkaJxcBaseAddr.do', 'view','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">查看</span> 
             <span style="cursor:pointer;"  class="fblue" onclick="doNeedMethod('您确认删除该记录吗？', 'KonkaJxcBaseAddr.do', 'delete','&id=${cur.id}&'+$('#bottomPageForm').serialize())">删除</span>
         	</td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </form>
  <div class="rtabcont3">
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaJxcBaseAddr.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("user_name_like", "${af.map.user_name_like}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var f=document.forms[0];
	 $(".bgSearch").click(function(){
			if(!Validator.Validate(f, 1)){
				return false;
			}
	    });
	});
//]]></script>
</body>
</html>