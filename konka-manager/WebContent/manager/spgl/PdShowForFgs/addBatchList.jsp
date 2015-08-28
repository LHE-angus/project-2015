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
      <html-el:form action="/spgl/PdShowForFgs">
        <html-el:hidden property="method" value="addBatch" />
        <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
        <html-el:hidden property="prod_type" value="${af.map.prod_type}" />
        <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
          <tr>
            <td >&nbsp;商品编码：
            <html-el:text property="pd_sn_like" styleId="pd_sn_like" styleClass="webinput" />
            &nbsp;商品名称：
            <html-el:text property="pd_name_like" styleId="pd_name_like" styleClass="webinput" />
            &nbsp;&nbsp;&nbsp;分类标签 <html-el:select property="label_of_cate" styleId="label_of_cate">
            	<html-el:option value="">请选择--</html-el:option>
            	<html-el:option value="0">新品</html-el:option>
            	<html-el:option value="2">热销</html-el:option>
            	<html-el:option value="3">特惠</html-el:option>
            	<html-el:option value="4">工程机</html-el:option>
            	<html-el:option value="5">限时抢购</html-el:option>
            	<html-el:option value="6">团购</html-el:option>
            	<html-el:option value="7">精品</html-el:option>
            	<html-el:option value="8">每周一劲爆</html-el:option>
            	<html-el:option value="9">样机专区</html-el:option>
            </html-el:select>  &nbsp;&nbsp;
              <html-el:submit styleId="search_btn" styleClass="but1" value="搜索" /></td>
            
          </tr>
        </table>
      </html-el:form>
    <div class="rtabcont1">
    	<%@ include file="/commons/pages/messages.jsp" %>
    </div>
    <html-el:form action="/spgl/PdShowForFgs?method=insertBatch">
      <div class="rtabcont1">
        <input type="button" class="but3" name="delete" id="delete" value="提交" onclick="insertAll(this.form);" />
        <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
        <html-el:hidden property="prod_type" value="${af.map.prod_type}" />
        <html-el:hidden property="method" value="insertBatch" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center" nowrap="nowrap"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
          <td nowrap="nowrap" align="center">商品编码</td>
          <td nowrap="nowrap" align="center">商品主图</td>
          <td nowrap="nowrap" align="center">分类标签</td>
          <td width="20%" align="center" nowrap="nowrap">商品名称</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center">
                <input name="pks" type="checkbox" id="pks" value="${cur.id}"  />
             </td>
            <td align="left">${cur.pd_sn}</td>
            <td align="center"><img width="60px" height="40px" src="${ctx}/${fn:substringBefore(cur.main_pic, '.')}_240.jpg" /></td>
            <td align="center">
            <c:if test="${cur.label_of_cate eq 0}">新品</c:if>
            <c:if test="${cur.label_of_cate eq 2}">热销</c:if>
            <c:if test="${cur.label_of_cate eq 3}">特惠</c:if>
            <c:if test="${cur.label_of_cate eq 4}">工程机</c:if>
            <c:if test="${cur.label_of_cate eq 5}">限时抢购</c:if>
            <c:if test="${cur.label_of_cate eq 6}">团购</c:if>
            <c:if test="${cur.label_of_cate eq 7}">精品</c:if>
            <c:if test="${cur.label_of_cate eq 8}">每周一劲爆</c:if>
            <c:if test="${cur.label_of_cate eq 9}">样机专区</c:if>
            </td>
            <td align="center">
            	${cur.pd_name}
            </td>
          </tr>
        </c:forEach>
      </table>
       </div>
    </html-el:form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="../spgl/PdShowForFgs.do?method=addBatch">
      <table width="98%" border="0" align="right" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
			pager.addHiddenInputs("pd_sn_like", "${af.map.pd_sn_like}");
			pager.addHiddenInputs("prod_type", "${af.map.prod_type}");
			pager.addHiddenInputs("label_of_cate", "${af.map.label_of_cate}");
			pager.addHiddenInputs("pd_name_like", "${fn:escapeXml(af.map.pd_name_like)}");	
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

	
	
});

function insertAll(form){
	var checkedCount = 0;
	if (!form.pks) {
		return;
	}
	if(!form.pks.length) {
		if (form.pks.checked == true) {
			checkedCount = 1;
		}
	}
	for (var i = 0; i < form.pks.length; i++) {
		if (form.pks[i].checked == true) {
			checkedCount++;
		}
	}
	if (checkedCount == 0) {
		alert("\u81f3\u5c11\u9009\u62e9\u4e00\u4e2a\u9009\u9879\uff01");
	} else {
		if(confirm("\u60a8\u786e\u8ba4\u63d0\u4ea4\u8fd9\u4e9b\u6570\u636e\u5417\uff01")) {
			form.action="${ctx}/manager/spgl/PdShowForFgs.do?method=insertBatch";
			form.submit();
		}
	}
	
}

//]]></script>

<jsp:include page="/__analytics.jsp" />
</body>
</html>
