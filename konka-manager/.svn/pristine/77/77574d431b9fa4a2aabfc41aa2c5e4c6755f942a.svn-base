<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
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
<div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
<div class="oarcont">
  <div class="rtabcont1">
      <html-el:form action="/admin/ArticleImg">
        <html-el:hidden property="method" value="viewByChannel" />
        <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
        <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
     		<tr>
          		<td width="15"></td>
          		<td width="70"><strong class="fb">栏目：</strong></td>
          		<td colspan="2"><html-el:select property="news_module" onchange="this.form.submit()">
                <html-el:option value="">全部</html-el:option>
                <html-el:optionsCollection name="sysModuleWebList" label="c_name" value="c_index" />
              	</html-el:select></td>
          	</tr>
     	</table>
      </html-el:form>
    <div class="rtabcont1">
    	<%@ include file="/commons/pages/messages.jsp" %>
    </div>
    <html-el:form action="/admin/ArticleImg?method=delete">
       <div class="rtabcont1">
      	<span style="float:right;"><input type="button" name="back" id="back" value="返回首页" onclick="location.href='ArticleImg.do?mod_id=${af.map.mod_id}'" /></span>
        <input type="button" class="but3" name="delete" id="delete" value="回收站" onclick="confirmDeleteAll(this.form);" />
        <input type="button" class="but2" name="add" value="添加 " onclick="location.href='ArticleImg.do?method=add&mod_id=${af.map.mod_id}&amp;channel=${af.map.channel}&amp;news_module=${af.map.news_module}&amp;page_flag=1';" />
        <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
        <html-el:hidden property="channel" />
        <html-el:hidden property="page_flag" value="1" />
        <html-el:hidden property="method" value="delete" />
      </div>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr>
          <td width="25%"><script type="text/javascript" src="${ctx}/commons/scripts/ppt/ppt.js"></script>
            <script type="text/javascript">showPptPlayer("${imgList}", "${ctx}/", 6, 270, 180, null, null)</script></td>
          <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="1" class="datagrid" style="table-layout:fixed;">
              <tr>
                <td width="5%" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
                <td nowrap="nowrap">标题</td>
                <td width="7%" nowrap="nowrap">点击量</td>
                <td width="7%" nowrap="nowrap">排序值</td>
                <td width="10%" nowrap="nowrap">发布时间</td>
                <td width="10%" nowrap="nowrap">操作</td>
              </tr>
              <c:forEach var="cur" items="${entityList}" varStatus="vs">
                <c:set var="disabled" value="" />
                <c:if test="${cur.info_state eq -1}">
                  <c:set var="disabled" value='disabled' />
                </c:if>
                <tr>
                  <td align="center"><c:if test="${empty disabled}">
                      <input name="pks" type="checkbox" id="pks" value="${cur.id}" />
                    </c:if>
                    <c:if test="${not empty disabled}">
                      <input name="pks" type="checkbox" id="pks" value="${cur.id}" disabled="disabled" />
                    </c:if></td>
                  <td align="left" nowrap="nowrap" style="text-overflow:ellipsis;overflow:hidden;">${vs.count}. <a href="ArticleImg.do?method=view&id=${cur.id}&mod_id=${af.map.mod_id}" rel="${ctx}/${fn:substringBefore(cur.image_path, '.')}_400.jpg" class="preview" title="${fn:escapeXml(cur.title)}">${fn:escapeXml(cur.title)}</a></td>
                  <td align="right" nowrap="nowrap">${cur.view_count}</td>
                  <td align="right" nowrap="nowrap" class="order_value_td" id="${cur.id}">${cur.order_value}</td>
                  <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.pub_date}" pattern="yyyy-MM-dd" /></td>
                  <td align="center" nowrap="nowrap"><c:if test="${cur.info_state ne -1}"><a href="#" onclick="confirmUpdate(null, 'ArticleImg.do', 'id=${cur.id}&mod_id=${af.map.mod_id}&amp;channel=${af.map.channel}&amp;page_flag=1')">修改</a> | <a href="#" onclick="confirmDelete(null, 'ArticleImg.do', 'mod_id=${af.map.mod_id}&id=${cur.id}&amp;channel=${af.map.channel}&amp;page_flag=1&pager.requestPage=${af.map.pager.requestPage}')">删除</a> </c:if></td>
                </tr>
              </c:forEach>
            </table></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
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


	$("td.order_value_td").inputable({
		inputName : "order_value", 
		inputMaxLength : 4,
		inputStyle : {color:"#F00",border:"1px solid #0099FF","text-align":"right","padding-right":"3px",width:"35px"},
		url : '../admin/ArticleImg.do?method=updateOrderValue',
		loadingPath	: '${ctx}/styles/images/loading.gif',
		paramProps : ['id'],
		onSuccess : function(){
			$("<div id=\"tip\" style='position:absolute;top:40px;right:10px;border:1px solid green;padding:10px;background-color:#FFF;color:green;'>^_^ 恭喜您！数据提交成功！</div>").appendTo("body");
		},
		onError : function(){
			$("<div id=\"tip\" style='position:absolute;top:40px;right:10px;border:1px solid red;padding:10px;background-color:#FFF;color:red;'>Y_Y 很抱歉！数据提交出错，请重新编辑！</div>").appendTo("body");
		}
	}).click(function(){$("#tip").remove();});
});
//]]></script>

<jsp:include page="/__analytics.jsp" />
</body>
</html>
