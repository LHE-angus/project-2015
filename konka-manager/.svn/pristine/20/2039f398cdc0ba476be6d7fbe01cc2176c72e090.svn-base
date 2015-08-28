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
  <div class="rtabcont1">
    <html-el:form action="/manager/JBasePartner">
      <html-el:hidden property="method" value="jxslist" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>        
          <td><strong class="fb">网点名称：</strong>
          <html-el:text property="jxs_name_like" size="20" maxlength="20" styleId="jxs_name_like"  />
          &nbsp;&nbsp;<html-el:submit styleClass="but1" value="搜索" /> 
          <input type="button" id="btn_close" value="关闭" class="but1" style="margin-left:10px;" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="8%" align="center" nowrap="nowrap">序号</td>
          <td nowrap="nowrap">经销商名称</td>
          <td width="15%" align="center" >操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left">${cur.map.name}</td>
            <td align="center"><span style="cursor:pointer;" class="fblue select_class" id="${cur.jxs_r3_id}">选择</span></td>
          </tr>
          <c:if test="${vs.last eq true}"><c:set var="i" value="${vs.count}" /></c:if>
        </c:forEach>
        <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
          <tr align="center">
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </c:forEach>
      </table>
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="JBasePartner.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "jxslist");
			pager.addHiddenInputs("jxs_name_like", "${af.map.jxs_name_like}");	
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=idialog"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$(".select_class").click(function(){
		var val = $(this).attr("id");
		if(val == ""){
		  alert("R3编码为空，请确认数据的正确性！");
		  return;
		}
		//var ttt = $.dialog.tips('数据加载中...',60000,'loading.gif');
		$.ajax({
			type: "POST",
			url: "${ctx}/customer/manager/JBasePartner.do?method=getEntpInfoByR3Id&r3_id=" + val,
			data: {},
			dataType: "json",
			sync: true, 
			error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
			success: function(result) {
				if(result == null){
					alert("R3编码为空，请确认数据的正确性！");
					return;
				}
				var api = frameElement.api, W = api.opener;
				W.document.getElementById("partner_c_id").value = val;
				W.document.getElementById("partner_name").value = result.customer_name;
				
				api.close();
				//ttt.close();
			}
		});
		
	});
	
	// 关闭
	$(document).delegate("#btn_close", "click", function(){
		var api = frameElement.api, W = api.opener;
		api.close();
	});
});
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>