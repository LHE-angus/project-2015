<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align: middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/manager/KonkaXxPdProp">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="80%" nowrap="nowrap">属性名称：
            <html-el:text property="prop_name_like" maxlength="20" size="10" />
            &nbsp;&nbsp;
            产品类别：
            <html-el:select property="cls_id" styleId="cls_id" style="width: 20%">
              <c:forEach var="cur" items="${basePdClassList}">
                <html-el:option value="${cur.cls_id}" styleId="${cur.full_name}_${cur.is_leaf}" >${fn:escapeXml(cur.tree_name)}</html-el:option>
              </c:forEach>
            </html-el:select>
            属性类别：
            <html-el:text property="category_name_like" maxlength="20" size="10" />
            &nbsp;&nbsp;
            <input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp"%>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><input class="but2" type="submit" name="Submit2" value="新增" onclick="location.href='KonkaXxPdProp.do?method=add&mod_id=${af.map.mod_id}';" /></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" align="center" nowrap="nowrap"><font class="blue">序号</font></td>
        <td width="20%" align="center" nowrap="nowrap"><font class="blue">属性名称</font></td>
        <td width="27%" align="center" nowrap="nowrap"><font class="blue">产品类别</font></td>
        <td width="8%" align="center" nowrap="nowrap"><font class="blue">添加时间</font></td>
        <td width="8%" align="center" nowrap="nowrap"><font class="blue">排序值</font></td>
        <td width="12%" align="center" nowrap="nowrap"><font class="blue">操作</font></td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left"><font class="blue12px">${fn:escapeXml(cur.prop_name)}
              <c:if test="${not empty cur.map.category_name}">[${fn:escapeXml(cur.map.category_name)}]</c:if>
              </font></td>
            <td align="left"><font class="blue12px">${fn:escapeXml(strList[vs.index])}</font></td>
            <td align="center"><font class="blue12px">
              <fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" />
              </font></td>
            <td align="right"><font class="blue12px">${cur.order_value}</font></td>
            <td align="center"><span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxPdProp.do', 'view','prop_id=${cur.prop_id}&'+$('#bottomPageForm').serialize())">查看</span> | <span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxPdProp.do', 'edit','prop_id=${cur.prop_id}&'+$('#bottomPageForm').serialize())">修改</span> | <span style="cursor:pointer;" class="fblue" onclick="confirmDelete(null, 'KonkaXxPdProp.do', 'prop_id=${cur.prop_id}&'+$('#bottomPageForm').serialize())">删除</span></td>
          </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}" step="1">
          <tr>
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
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxPdProp.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("prop_name_like", "${af.map.prop_name_like}");
				pager.addHiddenInputs("category_name_like", "${af.map.category_name_like}");
				pager.addHiddenInputs("cls_name_like", "${af.map.cls_name_like}");
				document.write(pager.toString());
			  </script>
            </div></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
});
//]]>
</script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
