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
        <html-el:form action="/admin/KonkaMobileCategorys">
          <html-el:hidden property="method" value="childlist" />
          <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
            <html-el:hidden property="c_index_1" value="${af.map.c_index}" />
            <table width="100%" border="0" cellspacing="5" cellpadding="0">
              <tr>
                <td width="250">
      			 &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb"> 类别名称：</strong>
        		  <html-el:text property="cate_name" styleId="cate_name" maxlength="40" style="width:90px;" /> 
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
		    <input class="but2" type="button" name="button"  value="新增" onclick="location.href='KonkaMobileCategorys.do?method=add&c_index=${af.map.c_index}&mod_id=${af.map.mod_id}';return false;" />
		    <input class="but3" type="submit" name="Submit3" value="回收站" onclick="confirmDeleteAll(document.getElementById('listForm'));" />
		 </td>
	    </tr>
	  </table>  
 </div>
   <div class="rtabcont1">
        <form id="listForm" name="listForm" method="post" action="KonkaMobileCategorys.do?method=delete">
        <input type="hidden" name="method" id="method" value="delete" />
        <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
       <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
   		<tr class="tabtt1">
              <td width="5%"  align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" />
              </td>
              <td width="150" nowrap="nowrap" align="center">类别名称</td>
              <td width="100" nowrap="nowrap" align="center">类别排序值</td>
              <td  nowrap="nowrap" align="center">说明</td>
              <td width="170" nowrap="nowrap" align="center">操作</td>
            </tr>
          <tbody>
        <c:forEach var='cur' items='${entityList}' varStatus="vs">
          <tr>
            <td align="center">
            <c:if test="${cur.is_del eq 0}"><input name="pks" type="checkbox" id="pks" value="${cur.c_index}" /> </c:if>
            <c:if test="${cur.is_del eq 1}"><input name="pks" type="checkbox" id="pks" value="${cur.c_index}" disabled="disabled" /></c:if>
            </td>
            <td align="left">
            <c:if test="${cur.is_type eq 0 }">
              <div style="color:green" style="padding-left:20px;">${cur.c_name}
              </div>
            </c:if>
            <c:if test="${cur.is_type eq 1 }">
              <div style="color:blue" style="padding-left:20px;">${cur.c_name}
              </div>
            </c:if>
            </td>
            <td align="center">${cur.order_sort}</td>
            <td align="left">${cur.c_comm}</td>
            <td align="center">
            <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaMobileCategorys.do', 'view','mod_id=${af.map.mod_id}&c_index=${cur.c_index}&'+$('#bottomPageForm').serialize())">查看</span>
			|
            <span style="cursor:pointer;" class="fblue" onclick="confirmUpdate(null, 'KonkaMobileCategorys.do', 'mod_id=${af.map.mod_id}&c_index=${cur.c_index}&par_index=${cur.par_index}')">修改</span>
            |
            <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaMobileCategorys.do', 'childlist','mod_id=${af.map.mod_id}&c_index=${cur.c_index}&'+$('#bottomPageForm').serialize())">子类别</span>
            |
            <c:if test="${cur.is_del eq 0}"><span style="cursor:pointer;"  class="fblue" onclick="confirmDelete('确定删除吗?', 'KonkaMobileCategorys.do', 'c_index=${cur.c_index}&mod_id=${af.map.mod_id}&c_type=${af.map.c_type}')">删除</span></c:if>
            <c:if test="${cur.is_del eq 1}"><span style="color:#999;" class="fblue">删除</span></c:if></td>
          </tr>
            </c:forEach>
            <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
						<tr align="center">
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
        <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaMobileCategorys.do">
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td height="40"><div style="text-align:right; padding-right:5px;">
                  <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
                  <script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "list");
			    	pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			    	pager.addHiddenInputs("cate_name", "${fn:escapeXml(af.map.cate_name)}");
		            document.write(pager.toString());
		          </script>
                </div></td>
            </tr>
          </table>
        </form>
        </div>
         </div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>                                  
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

});

String.prototype.trim = function(){ 
	return this.replace(/(^\s*)|(\s*$)/g, ""); 
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>