<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
#imgPreviewWithStyles {
    background: #757575;
    -moz-border-radius: 5px;
    -webkit-border-radius: 5px;
    padding: 15px;
    z-index: 999;
    border: none;
    max-width: 330px;
}

/* Text below image */
#imgPreviewWithStyles span {
    color: white;
    text-align: center;
    display: block;
    padding: 10px 0 3px 0;
}
</style>
</head>
<body>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString} - ${ecHomeFloor.title}_${baseTypeData.type_name}</td>
      </tr>
    </table>
  </div>
   <div class="rtabcont1">
      <html-el:form action="/spgl/EcHomeFloorData">
        <html-el:hidden property="method" value="list" />
        <html-el:hidden property="mod_id" />
        <html-el:hidden property="floor_id"/>
        <html-el:hidden property="data_type_id"/>
        <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
          <tr>
            <td nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">标题</strong>：
              <html-el:text property="title" maxlength="20" size="20" /> 
              &nbsp;&nbsp;<html-el:submit styleId="search_btn" styleClass="but1" value="搜索" /></td>
          </tr>
        </table>
      </html-el:form>
    <div class="rtabcont1">
    	<%@ include file="/commons/pages/messages.jsp" %>
    </div>
    <html-el:form action="/spgl/EcHomeFloorData?method=delete">
      <div class="rtabcont1">
        <input type="button" class="but3" name="delete" id="delete" value="回收站" onclick="confirmDeleteAll(this.form);" />
        <input type="button" class="but2" name="add" value="添加 " onclick="location.href='EcHomeFloorData.do?method=add&mod_id=${af.map.mod_id}&floor_id=${af.map.floor_id}&data_type_id=${af.map.data_type_id}';" />
        <html-el:hidden property="mod_id" />
        <html-el:hidden property="floor_id"/>
        <html-el:hidden property="data_type_id"/>
        <html-el:hidden property="method" value="delete" />
        <input class="but5" type="button" name="Submit5" value="返回" id="btn_back" onclick="location.href='EcHomeFloor.do?method=home&id=${af.map.floor_id}&mod_id=${af.map.mod_id}';"/>
         <font color="red">${baseTypeData.memo}</font>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center" nowrap="nowrap"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
          <td nowrap="nowrap" align="center">标题</td> 
          <td width="8%" align="center" nowrap="nowrap">操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs"> 
          <tr>
            <td align="center">
                <input name="pks" type="checkbox" id="pks" value="${cur.id}" /> </td>
            <td align="left"> <c:if test="${empty cur.img_url}">${fn:escapeXml(cur.title)}</c:if>
            <c:if test="${not empty cur.img_url}">
            <a class="preview" rel="${fn:substringBefore(cur.img_url, '.')}_400.jpg" title="${fn:escapeXml(cur.title)}">${fn:escapeXml(cur.title)} </a>
            </c:if>
            </td> 
            <td align="center" nowrap="nowrap"> 
              	<a href="#" onclick="confirmUpdate(null, 'EcHomeFloorData.do', 'id=${cur.id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">修改</a> | 
                <a href="#" onclick="confirmDelete(null, 'EcHomeFloorData.do', 'data_type_id=${af.map.data_type_id }&mod_id=${af.map.mod_id}&id=${cur.id}&floor_id=${af.map.floor_id}&pager.requestPage=${af.map.pager.requestPage}')">删除</a>
            </td>
          </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}">
            <tr align="center">
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td> 
            </tr>
          </c:forEach>
      </table>
       </div>
    </html-el:form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="../spgl/EcHomeFloorData.do">
      <table width="98%" border="0" align="right" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
			pager.addHiddenInputs("info_state", "${af.map.info_state}");  
			pager.addHiddenInputs("floor_id", "${af.map.floor_id}");	
			pager.addHiddenInputs("mod_id", "${af.map.mod_id}");		
			pager.addHiddenInputs("data_type_id", "${af.map.data_type_id}");		
			pager.addHiddenInputs("title", "${fn:escapeXml(af.map.title)}");	
            document.write(pager.toString());
        </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.inputable.js"></script>
<script type="text/javascript">//<![CDATA[

$(document).ready(function(){
	$('a.preview').imgPreview({
		containerID: 'imgPreviewWithStyles',
		srcAttr: 'rel',
		imgCSS: {
			// Limit preview size:
			// height: 200,
			width: 300
		},
		// When container is shown:
		onShow: function(link){
			$('<span>' + link.title.replace("<", "&lt;").replace(">", "&gt;") + '</span>').appendTo(this);
		},
		// When container hides: 
		onHide: function(link){
			$('span', this).remove();
		}
	});
	
});
//]]></script>

<jsp:include page="/__analytics.jsp" />
</body>
</html>
