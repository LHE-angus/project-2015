<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" /> 
<title>开心猫</title> 
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/global.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/epp_index.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/address.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
<style type="text/css">
body {font-family: 'microsoft yahei',Verdana,Arial,Helvetica,sans-serif;}.new-mg-tb30{margin:30px 0;}
.new-abtn-type{display:block;padding:8px;border-radius:2px;background-color:#c00;font-size:14px;color:#fff;text-align:center;}
.new-ct{min-height:300px;background-color:#fff;}.new-mu_l2cw{display:block;overflow:hidden;_zoom:1;}.new-p-re {position: relative;}div {display: block;}
</style>
</head>
<body>  
<div class="mainbox">
<div class="maincont">   
<div class="new-ct">
	<div class="new-addr">   
	    <ul class="new-mu_l2w">
	     <c:forEach var="cur" items="${entityList}" varStatus="vs">
	      <li class="new-mu_l2">
        	<p class="new-tit new-p-re">
				<span class="new-txt"><c:out value="${cur.rel_name }"/></span><span class="new-txt-rd2"><c:out value="${cur.rel_phone}"/></span>
				<span class="new-txt-rd2 new-option-r" id="d${cur.id }" <c:if test="${cur.default_addr ne 1}" >onclick="defaultAddr(this)";</c:if>><span class="new-icon<c:if test="${cur.default_addr ne 1}" >2</c:if>"></span>默认地址</span> 
			</p>
            <span class="new-mu_l2a new-p-re">
                <span class="new-mu_l2cw">${cur.map.full_name }<c:out value="${cur.rel_addr }"/></span>     
				<div class="new-addr-btn">
                	<a href="?method=edit&id=${cur.id }">编辑</a><span class="new-addr-bar">|</span><a href="javascript:void(0);" id="t${cur.id }" onclick="deleteInfo(this);">删除</a>
                </div>
			</span>
        </li></c:forEach> 
       </ul>
	   <a href='?method=add' class="new-abtn-type new-mg-tb30">添加新地址</a>
    </div>
</div>
<div class="clear"></div>
</div>
</div>  
</body>
<script type="text/javascript">//<![CDATA[  
function deleteInfo(obj){
	var	id ="&id="+ obj.id.replace("t",""); 
	if(confirm("您确定删除吗？")){
		location.href="${ctx}/wap/center/EcUserAddrs.do?method=delete"+id;
	}
}
      
function defaultAddr(obj){
	var	id ="&id="+ obj.id.replace("d",""); 
	location.href="${ctx}/wap/center/EcUserAddrs.do?method=defaultaddr"+id;
}
     
function showNav(){  
	if(document.getElementById("jdkey").style.display=='none'){ 
		document.getElementById("jdkey").style.display='block';
	}else{
		document.getElementById("jdkey").style.display='none';
	} 
}
//]]>
</script> 
</html>