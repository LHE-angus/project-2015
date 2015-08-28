<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.alt {
	background-color:#eee;
}
.over {
	background-color:#F4FBFF;
}
.rowlight td {padding:0px 3px;}
</style>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align: middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
      <table width="100%" border="1" cellspacing="0" cellpadding="0" class="rowlight" style="border-collapse:collapse;border:1px solid #eee;">
        <tr>
          <th width="5%">层级</th>
          <th width="20%">模块名称</th>
          <th >模块描述</th>
          <th width="10%">是否删除</th>
          <th width="10%"><span style="text-align: center;">分公司可授权</span></th>
        </tr>
        <c:forEach var="cur" items="${sysModuleList}" varStatus="vs">
          <c:if test="${cur.map.level eq 1}">
            <tr>
              <td colspan="5" height="25" align="right"><span style="font-weight:800;">${cur.mod_name}</span></td>
            </tr>
          </c:if>
          <c:if var="is_dir" test="${empty cur.mod_url or cur.map.level eq 1}">
            <tr>
              <td style="text-align:center;">${cur.map.level}</td>
              <td nowrap="nowrap"><c:forEach begin="1" end="${cur.map.level - 1}">&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>
                <strong>${cur.mod_name}</strong></td>
              <td><span style="color:#999;">${cur.mod_desc}</span></td>
              <td><c:if test="${cur.is_del eq 0}">未删除</c:if>
              	 <c:if test="${cur.is_del eq 1}">已删除</c:if>
              </td>
              <td nowrap="nowrap" style="text-align:center;">
                  <c:if test="${cur.fgs_auth_flag eq 0}">
                  	<input type="checkbox" name="pks" value="${cur.mod_id}" class="tt" checked="checked" onclick="tijiao(this,'${cur.mod_id}')" />
                  </c:if>
                  <c:if test="${cur.fgs_auth_flag eq 1}">
                  	<input type="checkbox" name="pks" value="${cur.mod_id}" class="tt" onclick="tijiao(this,'${cur.mod_id}')"/>
                  </c:if> 授&nbsp;&nbsp;权</td>
            </tr>
          </c:if>
          <c:if test="${not is_dir}">
          <tr>
            <td style="text-align:center;">${cur.map.level}</td>
            <td nowrap="nowrap"><c:forEach begin="1" end="${cur.map.level - 1}">&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>
              ${cur.mod_name}</td>
              <td><span style="color:#999;">${cur.mod_desc}</span></td>
            <td nowrap="nowrap"><c:if test="${cur.is_del eq 0}">未删除</c:if>
              	 <c:if test="${cur.is_del eq 1}">已删除</c:if></td>
            <td style="text-align:center;">
            <c:if test="${cur.fgs_auth_flag eq 0}">
              <input type="checkbox" name="pks" value="${cur.mod_id}" class="tt" checked="checked" onclick="tijiao(this,'${cur.mod_id}')" />
              </c:if>
              <c:if test="${cur.fgs_auth_flag eq 1}">
              	<input type="checkbox" name="pks" value="${cur.mod_id}" class="tt" onclick="tijiao(this,'${cur.mod_id}')"/>
              </c:if> 授&nbsp;&nbsp;权</td> 
          </tr>
            </c:if>
        </c:forEach>
        <tr>
          <td colspan="5">&nbsp;</td>
        </tr>
      </table>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$(".rowlight tr").mouseover(function(){  
		$(this).addClass("over");
	}).mouseout(function(){
		$(this).removeClass("over");
	});
	$(".rowlight tr:even").addClass("alt");

	
});

function tijiao(obj,mod_id){
	var state;
	if(obj.checked){
		state=1;//选中
	}else{
		state=2;//未选中
	}
	
	$.ajax({
		type: "POST",
		url: "${ctx}/manager/admin/SetFgsModPopedom.do",
		data: "method=ajaxUpdate&state="+state+"&c_mod_id="+mod_id,
		dataType: "json",
		success:function(result) {
			if(result==0){
				alert("操作成功！");
			}else{
				alert("操作失败！");
			}
		}
	});
	
}


//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
