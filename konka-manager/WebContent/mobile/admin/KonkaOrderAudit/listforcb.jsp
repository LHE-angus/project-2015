<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<META http-equiv=Expires content=0>
<META http-equiv=Cache-Control content=no-cache>
<META http-equiv=Pragma content=no-cache>
<title>康佳电器</title>
<script type="text/javascript" language="javascript">
var items = document.getElementsById("tab");
	var trs = items.getElemetsByTagName("tr");
	for(var i = 0; i<trs.length; i++){
		if(i%2 == 0){
			trs[i].style.bordercolor="#f90";
		}else{
			trs[i].style.bordercolor="#666";
		}
}
</script>
<link rel="stylesheet" type="text/css" href="${ctx}/mobile/css/common.css" />

<style type="text/css"></style>
</head>

<body>
<div id="fileContent">
  <div class="headtab31" ></div>
  <table width="100%" border="0" class="rtab3" id="tab">
    <tr>
    	<th width="20%" align="left" colspan="1" >分公司</th>
      <th width="30%" align="left" colspan="1" >催办时间</th>
      <th width="25%" align="left" colspan="1" >催办人</th>
      <th width="25%" style="background-image:none"  align="left">审核</th>
    </tr>
    <c:forEach items="${entityList}" var="cur" varStatus="vs">
      <tr style="cursor: pointer;" class="db_tr" onclick="goaudit('${cur.id}')">
      	<td colspan="4">
		      <table  width="100%" border="0"  class="rtab411" >
		      	<tr>
			        <td width="20%" align="left">
			       	 	<span class="">${cur.map.fgsName}</span>                       
					 </td>
					 <td width="30%" align="left">
						 <span class=""><fmt:formatDate value="${cur.map.cb_time}" pattern="yyyy-MM-dd" /></span> 
					 </td>
			         <td width="25%" align="left">
			   			<span class="">${cur.map.cb_user}</span> 
			         </td>
			        <td align="left" width="25%" rowspan="" rowspan="2" valign="center" >
			        	<a href="javascript:goaudit('${cur.id}')" data-role="button" data-icon="plus" data-theme="b" data-inline="true">审核</a>
			        </td>
			      </tr>
				  <tr>
				      <td colspan="3" width="100%" align="left">流水号：<span>${cur.trade_index}</span>     </td>
			      </tr>
		 </table>
		 </td>
      </tr>
	</c:forEach>
		   
    <c:forEach begin="${fn:length(entityList)}" end="13">
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
     </c:forEach>
	      

    
  </table>
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaOrderAudit.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:20px 0px;">
        <tr>
          <td align="left">
          <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
                var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "listForCb");
	            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

var recordNum = 0;
$(document).ready(function(){
});
function goaudit(id){
	window.location.href= "${ctx}/KonkaOrderAudit.do?method=audit&id="+ id +"&user_id="+getQueryString("user_id")+"&username="+getQueryString("username")+"&userpass="+getQueryString("userpass");
}

function goPage(method,page,forward,pagelimit){
	pagelimit = Math.ceil(recordNum / pagelimit);
	if(page == 1 && forward == -1){
		alert("已到首页！");
		return false;
	}
	else if(page == pagelimit && forward == 1){
		alert("已到尾页！");
		return false;
	}
	else if(pagelimit == 0){
		alert("无翻页信息！");
		return false;
	}
	else
		return true;
}

function goback(page,username,userpass) {
	if(goPage(null,page,-1,5)){
		window.location.href="KonkaOrderAudit.do?method=list&user_id="+getQueryString("user_id")+"&username="+getQueryString("username")+"&userpass="+getQueryString("userpass")+"&page="+page+"&forward=-1";
	}
}
</script>
</body>
</html>
