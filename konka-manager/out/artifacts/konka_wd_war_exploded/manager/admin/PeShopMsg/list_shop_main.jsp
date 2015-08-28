<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
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
<base target="_self">
</base>
</head>
<body>
<div class="oarcont" align="left">
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable1">
      <tr class="tabtt1">
        <td align="left"><label for="checkedAll"><strong class="fb"> 全选/全不选</strong></label>
          <input type="checkbox" id="checkedAll" value="-1"/>
          <div class="note">全选是指选取当前页面的所有网点</div></td>
      </tr>
    </table>
  </div>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
    <tr>
      <c:set var="list_size" value="${fn:length(entpList)}" />
      <c:forEach var="cur" items="${entpMainList}" varStatus="vs1">
        <td width="100%" align="left" nowrap="nowrap"  title="${cur.shop_name}"><label for="${cur.shop_id}"><input type="checkbox" id="${cur.shop_id}" value="${cur.shop_name}" />
          <span style="margin-right:10px;">${fnx:abbreviate(cur.shop_name, 2 * 15,'...')}</span></label> </td>
        <c:if test="${(vs1.count mod 2 eq 0) and vs1.count ne list_size}">
          <c:out value="</tr>" escapeXml="false" />
        </c:if>
      </c:forEach>
    </tr>
  </table>
  <table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td height="30" align="center"><input name="selectEntp" type="button" id="selectEntp"  value="确定" class="but2"/></td>
    </tr>
  </table>
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="PeShopMsg.do">
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
          <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "listShopMain");
            pager.addHiddenInputs("shop_name", "${fn:escapeXml(af.map.shop_name)}");
            pager.addHiddenInputs("category_id", "category_id");
            document.write(pager.toString());
          </script>
        </td>
      </tr>
    </table>
  </form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){	
	$(".Tab tr").mouseover(function(){  
		$(this).addClass("over");
	}).mouseout(function(){
		$(this).removeClass("over");
	})
	$(".Tab tr:even").addClass("alteven");
	$(".Tab tr:odd").addClass("altodd");
});
$(function() {    
	$("#checkedAll").click(function() {  
		var boxes = document.getElementsByTagName("input");
		var checkAll = document.getElementById("checkedAll");
		var len = boxes.length; 
		for(var i=0; i<len; i++) { 
			if(boxes[i].type=="checkbox") {
				if(checkAll.checked){
					boxes[i].checked = true;
				}else{
					boxes[i].checked = false;
				}
				 
			} 
		} 
		
	}); 
	
	$("#selectEntp").click(function(){
		var str="";
		var boxes = document.getElementsByTagName("input");
		var len = boxes.length; 
		for(var i=0; i<len; i++) { 
			if(boxes[i].type=="checkbox") {
				if(boxes[i].checked&&boxes[i].value!='-1'){
					str += "##"+boxes[i].value;
				}
			}
		}
		
		window.returnValue=str.substr(2,str.length);
		window.close();
	});
	
});  
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
