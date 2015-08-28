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
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
   <div class="rtabcont1">
      <html-el:form action="/spgl/ArticleImgNew">
        <html-el:hidden property="method" value="list" />
        <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
        <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
          <tr>
            <td nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">标题</strong>：
              <html-el:text property="title" maxlength="20" size="20" />
               &nbsp;<strong class="fb">栏目：</strong>
              <html-el:select property="news_module" onchange="this.form.submit()" styleId="news_module">
                <html-el:option value="">-请选择-</html-el:option>
                <html-el:optionsCollection name="sysModuleWebList" label="c_name" value="c_index" />
              </html-el:select> &nbsp;&nbsp; 
              <strong class="fb"> 所属组织：</strong><html-el:select property="dept_id" styleId="dept_id" style="width:120px;">
	          				<html-el:option value="">请选择</html-el:option>
	          				<c:forEach items="${deptList}" var="cur">
	          					<html-el:option value="${cur.id}">${cur.group_name}</html-el:option>
	          				</c:forEach>
	          			</html-el:select> 
            </td></tr><tr>
            <td >&nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">发布日期：</strong>
              <html-el:text property="st_pub_date"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2000, 2030, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
              至
              <html-el:text property="en_pub_date"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2000, 2030, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
              <span id="span_msg_date_error" style="color:#F00;margin-left:1em;display:none;">*起始日期必须小于等于终止日期</span>
            &nbsp;&nbsp;<strong class="fb">&nbsp;&nbsp;&nbsp;&nbsp;信息状态：</strong>
              <html-el:select property="info_state" onchange="this.form.submit()">
                <html-el:option value="">全部</html-el:option>
                <html-el:option value="0">关闭</html-el:option>
                <html-el:option value="1">发布</html-el:option>
              </html-el:select>
              &nbsp;&nbsp;<html-el:submit styleId="search_btn" styleClass="but1" value="搜索" /></td>
          </tr>
        </table>
      </html-el:form>
    <div class="rtabcont1">
    	<%@ include file="/commons/pages/messages.jsp" %>
    </div>
    <html-el:form action="/spgl/ArticleImgNew?method=delete">
      <div class="rtabcont1" style="overflow: auto;">
       <!--  <input type="button" class="but3" name="delete" id="delete" value="回收站" onclick="confirmDeleteAll(this.form);" />  -->
          <logic-el:match name="popedom" value="+1+"> 
        <input type="button" class="but2" name="add" value="添加 " onclick="location.href='ArticleImgNew.do?method=add&mod_id=${af.map.mod_id}&news_module=${af.map.news_module}';" />
        </logic-el:match>
        <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
        <html-el:hidden property="method" value="delete" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
         <!--   <td width="5%" align="center" nowrap="nowrap"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>  -->
          <td width="5%" align="center" nowrap="nowrap">序号</td>
          <td nowrap="nowrap" align="center">标题</td>
          <td width="13%" align="center" nowrap="nowrap">栏目</td>
          <td width="10%" align="center" nowrap="nowrap">所属组织</td>
          <td width="8%" align="center" nowrap="nowrap">点击量</td>
          <td width="8%" align="center" nowrap="nowrap">排序值</td>
          <td width="8%" align="center" nowrap="nowrap">状态</td>
          <td width="8%" align="center" nowrap="nowrap">添加人</td>
          <td width="8%" align="center" nowrap="nowrap">发布时间</td>
          <td width="8%" align="center" nowrap="nowrap">失效时间</td>
          <td width="8%" align="center" nowrap="nowrap">操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <c:set var="disabled" value="" />
          <c:if test="${cur.info_state eq -1}">
            <c:set var="disabled" value='disabled' />
          </c:if>
          <tr>
            <td align="center" style="display: none;"><c:if test="${empty disabled}">
                <input name="pks" type="checkbox" id="pks" value="${cur.id}" />
              </c:if>
              <c:if test="${not empty disabled}">
                <input name="pks" type="checkbox" id="pks" value="${cur.id}" disabled="disabled" />
              </c:if></td>
             <td align="left" nowrap="nowrap">${vs.count}</td>  
            <td align="left"><a class="preview" href="ArticleImgNew.do?method=view&id=${cur.id}&mod_id=${af.map.mod_id}" rel="${ctx}/${fn:substringBefore(cur.image_path, '.')}_400.jpg" title="${fn:escapeXml(cur.title)}">${fn:escapeXml(cur.title)}</a></td>
            <td>
            	<c:forEach items="${sysModuleWebList}" var="cur1">
	            	<c:if test="${cur.news_module eq cur1.c_index}">${cur1.c_name}</c:if>
            	</c:forEach>
            </td>
            <td align="left" nowrap="nowrap">${cur.map.group_name}</td> 
            <td align="right" nowrap="nowrap">${cur.view_count}</td>
            <td align="right" nowrap="nowrap" class="order_value_td" id="${cur.id}">${cur.order_value}</td>
            <td align="center" nowrap="nowrap"><c:choose>
                <c:when test="${cur.info_state eq 0}">关闭</c:when>
                <c:when test="${cur.info_state eq 1}">发布</c:when>
              </c:choose></td>
             <td align="left" nowrap="nowrap">${cur.map.add_user_name}</td>   
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.pub_date}" pattern="yyyy-MM-dd" /></td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.invalid_date}" pattern="yyyy-MM-dd" /></td>
            <td align="center" nowrap="nowrap">
             <!--<c:if test="${cur.info_state eq -1}">
            <span style="color:#ccc">修改 | 删除</span></c:if>  -->
                <logic-el:match name="popedom" value="+2+"> 
              	<a href="#" onclick="confirmUpdate(null, 'ArticleImgNew.do', 'id=${cur.id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">修改</a>   
             	</logic-el:match>
             	 <logic-el:notMatch name="popedom" value="+2+"> 
            	 <span style="color: #ccc">修改</span>
            	 </logic-el:notMatch>
             <!--     <a href="#" onclick="confirmDelete(null, 'ArticleImgNew.do', 'mod_id=${af.map.mod_id}&id=${cur.id}&pager.requestPage=${af.map.pager.requestPage}')">删除</a>  -->
            </td>
          </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}">
            <tr align="center">
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </c:forEach>
      </table>
       </div>
    </html-el:form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="../spgl/ArticleImgNew.do">
      <table width="98%" border="0" align="right" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
			pager.addHiddenInputs("news_module", "${af.map.news_module}");
			pager.addHiddenInputs("st_pub_date", "${af.map.st_pub_date}");
			pager.addHiddenInputs("en_pub_date", "${af.map.en_pub_date}");			
			pager.addHiddenInputs("info_state", "${af.map.info_state}");
			pager.addHiddenInputs("dept_id", "${af.map.dept_id}");  
			pager.addHiddenInputs("mod_id", "${af.map.mod_id}");			
			pager.addHiddenInputs("tunnel", "${af.map.tunnel}");
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
                                          
$("input[type='text'][name='en_pub_date']").bind("propertychange", function() {
	var s_v = $("input[type='text'][name='st_pub_date']").val();
	if (s_v.length > 0 && s_v > this.value) {
		$("#span_msg_date_error").show();
		$(":submit").attr("disabled", "true");
	} else {
		$("#span_msg_date_error").hide();
		$(":submit").removeAttr("disabled");
	}
});
$("input[type='text'][name='st_pub_date']").bind("propertychange", function() {
	var e_v = $("input[type='text'][name='en_pub_date']").val();
	if (e_v.length > 0 && e_v < this.value) {
		$("#span_msg_date_error").show();
		$(":submit").attr("disabled", "true");
	} else {
		$("#span_msg_date_error").hide();
		$(":submit").removeAttr("disabled");
	}
});

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
		url : '../spgl/ArticleImgNew.do?method=updateOrderValue',
		loadingPath	: '${ctx}/styles/images/loading.gif',
		paramProps : ['id'],
		onSuccess : function(){
			$("<div id=\"tip\" style='position:absolute;top:150px;right:10px;border:1px solid green;padding:10px;background-color:#FFF;color:green;'>^_^ 恭喜您！数据提交成功！</div>").appendTo("body");
		},
		onError : function(){
			$("<div id=\"tip\" style='position:absolute;top:150px;right:10px;border:1px solid red;padding:10px;background-color:#FFF;color:green;'>Y_Y 很抱歉！数据提交出错，请重新编辑！</div>").appendTo("body");
		}
	}).click(function(){$("#tip").remove();});
	
	$("#news_module").val("${af.map.news_module}");
});
//]]></script>

<jsp:include page="/__analytics.jsp" />
</body>
</html>
