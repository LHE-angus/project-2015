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
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/PeRoleInfo">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr style="height:25px;">
          <td width="15" style="height:25px;"></td>
          <td style="height:25px;"><ul>
              <li style="padding-top:3px;"> 职位名称：
                <html-el:text property="role_name_like" maxlength="30"/>
                &nbsp;
                <c:if test="${!empty is_admin}"> 分公司：
                  <html-el:select property="dept_id" styleId="dept_id">
                    <html-el:option value="">-请选择-</html-el:option>
                    <c:forEach items="${konkaDeptList}" var="cur">
                      <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
                    </c:forEach>
                  </html-el:select>
                  &nbsp; </c:if>
                <input class="but1" type="submit" name="Submit" value="搜索" /> &nbsp; &nbsp;
              </li>
            </ul></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
     <logic-el:match name="popedom" value="+1+">
     <input class="but2" type="submit" name="Submit2" value="新增" onclick="location.href='PeRoleInfo.do?method=add&mod_id=${af.map.mod_id}&tree_param=${tree_param}';return false;" />
  </logic-el:match>
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="BaseProvinceList.do">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
          <tr class="tabtt1">
            <td width="5%" nowrap="nowrap"><font class="blue">序号</font></td>
            <td width="10%" nowrap="nowrap"><font class="blue">职位ID</font></td>
            <td width="10%" nowrap="nowrap"><font class="blue">职位类别</font></td>
            <td nowrap="nowrap"><font class="blue">职位名称</font></td>
            <td width="15%" align="center" nowrap="nowrap"><font class="blue">添加时间</font></td>
            <td width="10%" align="center" nowrap="nowrap"><font class="blue">操作</font></td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center">${vs.count}</td>
              <td align="left"><font class="blue12px">${cur.role_id}</font></td>
              <td align="left"><font class="blue12px">${empty cur.map.dept_name ? '系统职务' : '分公司职务-'}${cur.map.dept_name}</font></td>
              <td align="left"><font class="blue12px">${fn:escapeXml(cur.role_name)}</font></td>
              <td align="center"  nowrap="nowrap"><font class="blue12px">
                <fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" />
                </font></td>
              <td align="center" nowrap="nowrap">
             
              	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'SetModPopedom.do', 'view', 'mod_id=${af.map.mod_id}&role_id=${cur.role_id}')">权限</span> |
                <logic-el:match name="popedom" value="+2+"> <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'SetRoleInfo.do', 'view', 'mod_id=${af.map.mod_id}&role_id=${cur.role_id}&url=PeRoleInfo&'+$('#bottomPageForm').serialize())">人员</span> </logic-el:match>
                <logic-el:notMatch name="popedom" value="+2+"> <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'SetRoleInfo.do', 'view', 'readonly=true&mod_id=${af.map.mod_id}&role_id=${cur.role_id}&url=PeRoleInfo&'+$('#bottomPageForm').serialize())">人员</span> </logic-el:notMatch>
                | 
                  <logic-el:match name="popedom" value="+4+">
                <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'SetModPopedom.do', 'edit', 'mod_id=${af.map.mod_id}&role_id=${cur.role_id}&url=PeRoleInfo&'+$('#bottomPageForm').serialize())">授权</span>
             |<span style="cursor:pointer;" onclick="doNeedMethod(null, 'PeRoleInfo.do', 'edit','role_id=${cur.role_id}&'+$('#bottomPageForm').serialize())">修改</span>
              </logic-el:match>
               <logic-el:notMatch name="popedom" value="+4+">
                <span style="cursor:pointer;"><font color="grey">授权</font></span>
                | <span style="cursor:pointer;"><font color="grey">修改</font></span>
              </logic-el:notMatch>
                |
                 <logic-el:match name="popedom" value="+8+">
                 <span style="cursor:pointer;" onclick="confirmDelete(null, 'PeRoleInfo.do', 'role_id=${cur.role_id}&'+$('#bottomPageForm').serialize())">删除</span>
                 </logic-el:match>
                  <logic-el:match name="popedom" value="+8+">
                 <span ><font color="grey" style="cursor:pointer;">删除</font></span>
                 </logic-el:match>
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
    </form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="PeRoleInfo.do">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "list");
		            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
		            pager.addHiddenInputs("tree_param", "${tree_param}");
		            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
		            pager.addHiddenInputs("role_name_like", "${af.map.role_name_like}");
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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}	
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