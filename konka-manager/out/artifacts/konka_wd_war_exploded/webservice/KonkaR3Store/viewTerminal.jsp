<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>终端</title>
<link rel="stylesheet" href="${ctx}/webservice/KonkaR3Store/css/base.css" />
</head>
<body>
<header class="navbar layout_box box_v_c">  
	<div class="navbar-left" style="display: none;"><span class="backpic"><img src="${ctx}/webservice/KonkaR3Store/images/topback.png" /></span></div>
    <div class="navbar-center"><span class="red2">
    <c:if test="${terminal_type eq '1'}">${entity.store_name}</c:if>
        	<c:if test="${terminal_type eq '2'}">${entity.partner_name}</c:if> 
        	</span></div>
</header>
<article class="main-wdzs">
	<section class="wdzs-des layout_box box_v_c sizing_border">
    	<div class="wdpic"><img  width="170px;" height="120px;" src="http://qdgl.konka.com/${attachmentList[0].save_path}" /></div>
        <div class="wdzs-des-info">
        	<h3>
        	<c:if test="${terminal_type eq '1'}">${entity.store_name}</c:if>
        	<c:if test="${terminal_type eq '2'}">${entity.partner_name}</c:if>
        	<span class="blue">距离：${null eq distanct ? 0 : distanct}公里</span></h3>
            <p style="margin-top:5px;">    
            <c:if test="${empty gps.addr and terminal_type eq '1'}">
            ${entity.province}${entity.city}${entity.country}${entity.town}
            </c:if>
             <c:if test="${empty gps.addr and terminal_type eq '2'}">
            ${entity.link_addr}
            </c:if>
            <c:if test="${not empty gps.addr}">  
           ${gps.addr}
            </c:if>
            </p>
            <p style="margin-top:18px;"><span>联系人：<span class="red2">${entity.link_man}台</span></span></p>  
            <p style="margin-top:18px;"><span>联系电话：<span class="red2">${entity.linka_tel}台</span></span></p>  
            <p style="margin-top:18px;"><span>销售量：<span class="red2">${entity.map.s_num}台</span></span></p>  
            <p style="display: none;"><span class="wdzs-des-pf">评分：
            <img src="${ctx}/webservice/KonkaR3Store/images/redstart.png" />
            <img src="${ctx}/webservice/KonkaR3Store/images/redstart.png" />
            <img src="${ctx}/webservice/KonkaR3Store/images/redstart.png" />
            <img src="${ctx}/webservice/KonkaR3Store/images/redstart.png" />
            <img src="${ctx}/webservice/KonkaR3Store/images/redstart.png" />
            </span></p>
        </div>
    </section>
    <section class="wdzs-detail"> 
    	<ul class="cont-nav-list qhNavList">
        	<li class="current" style="width:50%">网点介绍</li><li style="display: none;">促销活动</li><li style="width:50%">畅销商品</li><li style="display: none;">用户评价</li>
        </ul>
        <div class="wdzs-cont qhContList">
        	<div class="wdzs-contA">
        		<c:forEach items="${attachmentList}" var ="cur">
            	<img width="300" height="200" src="http://qdgl.konka.com/${cur.save_path}" />
        	</c:forEach>
                <p>${entity.memo}</p>
            </div>
            <div class="wdzs-contB"><p style="padding:1rem;">促销活动：一次优惠狂欢，活动期间，康佳将携4K电视、全新易TV、限量安卓智能电视等多款机型为消费者带来专属优惠与好礼。</p></div>
            
            <div class="wdzs-contC">
            <table width="100%" border="1" cellpadding="0" cellspacing="1" style="border: 1px #ccc solid;">     
            	<c:forEach items="${salesTop10List}" var ="cur">   
            	<tr >
				    <td rowspan="2" width="180px;" style="border-right:none;border-top:none;border-bottom:none;"><img width="170px" height="120px"  style="margin:5px 5px 5px 5px;" src="${ctx}/${fn:substringBefore(cur.map.main_pic, '.')}_240.jpg" /></td>
				    <td style="border-left:none;border-top:none;border-bottom:none;"><span style="margin-left:5px;">${cur.map.md_name} </span></td>
  				</tr>
				<tr style="border-right:none;">
				 	<td style="border-left:none;border-top:none;"><span style="margin-left:5px;">${cur.map.pd_desc}</span></td>   
  				</tr>
  				<tr height="10px">  
				 	<td colspan="2" style="border: none;"></td>             
  				</tr>
  				</c:forEach>
            </table>
            </div>
            
            <div class="wdzs-contD">
            <p style="padding:1rem;">用户评价：
            <img src="${ctx}/webservice/KonkaR3Store/images/redstart.png" />
            <img src="${ctx}/webservice/KonkaR3Store/images/redstart.png" />
            <img src="${ctx}/webservice/KonkaR3Store/images/redstart.png" />
            <img src="${ctx}/webservice/KonkaR3Store/images/redstart.png" />
            <img src="${ctx}/webservice/KonkaR3Store/images/redstart.png" />
            </p></div>
        </div>
    </section>
</article>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/scripts/print.js"></script>
<script type="text/javascript">
$(".qhNavList li").click(function(){
	$(this).addClass("current").siblings(".current").removeClass("current");
	$(".qhContList").children(":eq("+$(this).index()+")").css("display","block").siblings().css("display","none");
});
</script>
</body>
</html>