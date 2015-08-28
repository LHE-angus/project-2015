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
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<style type="text/css"> 
/* html{height:100%; overflow:hidden;position:relative;} */
/*  body{height:100%; overflow-y:hidden;position:relative;} */
.container {width:98%;height:100%; overflow-x: auto;}
.logo2 { margin:0 auto; margin-right:5px; padding: 0 10px; margin-bottom: 6px;background-position: 5px;; background-image:url(../CeppOrder/images/logo2.jpg);background-repeat: no-repeat; height: 44px; }
.logo3 { margin:0 auto; margin-right:5px; padding: 0 10px; margin-bottom: 6px;background-position: 5px;; background-image:url(../CeppOrder/images/logo3.jpg);background-repeat: no-repeat; height: 44px; }
.logo4 { margin:0 auto; margin-right:5px; padding: 0 10px; margin-bottom: 6px;background-position: 5px;; background-image:url(../CeppOrder/images/logo4.jpg);background-repeat: no-repeat; height: 44px; }
.logo5 { margin:0 auto; margin-right:-200px; padding: 0 10px; margin-bottom: 6px;background-position: 5px;; background-image:url(../CeppOrder/images/logo5.jpg);background-repeat: no-repeat; height: 34px; }
.tabs_list2 {width:90%;border:10px;width: auto;border-collapse: collapse;margin-top:20px;margin-bottom:20px;text-align:left;}
.pictd{width: 500px;height: 290px;padding:30px 30px;}
.pic img{width:255px;height:157px;}
#show{background-image:url(../CeppOrder/images/table.png);background-repeat:repeat;}
#buydiv{background-image:url(../CeppOrder/images/but.png);background-repeat:repeat;width:80px;height:24px;}
.fla {width:100%;position:relative;height:100%;}
</style>
</head>
<body style="font-family:Microsoft Yahei;position:relative;">
 <div class="fla">
<!--  <div class="oartop">-->
<!--    <table width="100%" border="0" cellpadding="0" cellspacing="0">-->
<!--      <tr>-->
<!--        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>-->
<!--        <td nowrap="nowrap">当前位置：${naviString}</td>-->
<!--      </tr>-->
<!--    </table>-->
<!--  </div>-->
  <div class="container">
  <c:if test="${not empty XPentityList}">
  <!-- 新品首发 -->
  <div class="logo2" >
 
</div>
   <table class="tabs_list2">
      <tbody>
        <tr>
          <c:forEach items="${XPentityList}" var="cur" begin="0" end="1" >
           
           <td class="pictd">
           <div class="pic">
           <c:if test="${not empty cur.map.save_path}">
             
             <a title="康佳${cur.pd_code}&nbsp;${cur.pd_name}" href="<c:url value='CeppOrder.do?method=add&goods_id=${cur.pd_id}' />">
             <img src="${ctx}/${cur.map.save_path}" />
              <h4>${cur.pd_code}</h4>
             <p>${cur.pd_name}</p>
             </a>
            </c:if>
            </div>
          
<%--            <a><font class="redfont">${fnx:abbreviate(cur.pd_name, 2 * 16, '')}</font></a> --%>
          
           </td>
          </c:forEach>
        </tr>
      </tbody>
    </table>
    </c:if>
      <c:if test="${not empty JPentityList}">
    <!-- 精品推荐 -->
     <div class="logo5" >
</div>

   <table  class="tabs_list2">
      <tbody>
        
          <c:forEach items="${JPentityList}" var="cur"  varStatus="vs" end="20">
          <c:if test="${(vs.count-1) %4 eq 0 || vs.count eq 1}">
          	<tr>
          </c:if>
          
          <td class="pictd">
           <div class="pic">
           <c:if test="${not empty cur.map.save_path}">
             <a title="康佳${cur.pd_code}&nbsp;${cur.pd_name}" href="<c:url value='CeppOrder.do?method=add&goods_id=${cur.pd_id}' />">
             <img src="${ctx}/${cur.map.save_path}" />
              <h4>${cur.pd_code}</h4>
             <p>${cur.pd_name}</p>
             </a>
              </c:if>
            </div>

           
          
<%--            <a><font class="redfont">${fnx:abbreviate(cur.pd_name, 2 * 16, '')}</font></a> --%>
          
           </td>  
          
          <c:if test="${vs.count % 4 eq 0}">
           </tr>
          </c:if>  
          </c:forEach>
        
      </tbody>
    </table>
    </c:if>
     
    
     <c:if test="${not empty RXentityList}">
     <!-- 热销推荐 -->
     <div class="logo3" >
</div>

   <table class="tabs_list2">
      <tbody>
        <tr>
          <c:forEach items="${RXentityList}" var="cur"  begin="0" end="1">
             <td class="pictd">
           <div class="pic">
           <c:if test="${not empty cur.map.save_path}">
             
             <a title="康佳${cur.pd_code}&nbsp;${cur.pd_name}" href="<c:url value='CeppOrder.do?method=add&goods_id=${cur.pd_id}' />">
             <img src="${ctx}/${cur.map.save_path}" />
              <h4>${cur.pd_code}</h4>
             <p>${cur.pd_name}</p></a>
            </c:if>
            </div>
          
<%--            <a><font class="redfont">${fnx:abbreviate(cur.pd_name, 2 * 16, '')}</font></a> --%>
          
           </td>
          </c:forEach>
        </tr>
      </tbody>
    </table>
    </c:if>
    
       <c:if test="${not empty THentityList}">
     <!-- 特惠推荐 -->
     <div class="logo4" >
</div>

   <table class="tabs_list2">
      <tbody>
        <tr>
          <c:forEach items="${THentityList}" var="cur"  begin="0" end="1">
          
          <td class="pictd">
           <div class="pic">
           <c:if test="${not empty cur.map.save_path}">
             
             <a title="康佳${cur.pd_code}&nbsp;${cur.pd_name}" href="<c:url value='CeppOrder.do?method=add&goods_id=${cur.pd_id}' />">
             <img src="${ctx}/${cur.map.save_path}" />
              <h4>${cur.pd_code}</h4>
             <p>${cur.pd_name}</p></a>
            </c:if>
            </div>
          
<%--            <a><font class="redfont">${fnx:abbreviate(cur.pd_name, 2 * 16, '')}</font></a> --%>
          
           </td>
          </c:forEach>
        </tr>
      </tbody>
    </table>
    </c:if>
    
   
    <div id="buydiv" style="z-index:9999;position:absolute;right:35px;border:10px;top:10px;font-size:20px;font-color:blue;" >
   </div>
   
    <div id="show" style="z-index:9999;position:absolute;display:none;height:150px; right:60px;top:10px;width:180px; overflow-y:scroll;">
 <html-el:form action="/manager/CeppOrder.do" method="post">
  <html-el:hidden property="method" value="add" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
  <table border="0" style="border-right:0px;" width="100%" cellpadding="0" cellspacing="0" class="rtable2">
  <tr class="tabtt1"><td colspan="2" ><font style="color:blue;">请选择你要购买的商品</font></td></tr>
    <c:if test="${not empty entityList }">
   <c:forEach items="${entityList}" var="cur"  >
      <tr>
      <td width="15%"></td>
  <td  align="left"><input type="checkbox" value="${cur.pd_id}"  name="pd_ids">${cur.pd_code}</input></td>
   </tr>
    </c:forEach>
   <tr>
  <td colspan="2" align="center" valign="botton">
    <input type="submit" class="but2" value="下单"></input>
    <input type="button"  class="but5"  id="closefrom" class="btn3" value="关闭"></input>
  </td></tr>
  </c:if>
  </table>
   </html-el:form>
 </div>
  </div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<jsp:include page="/customer/__analytics.jsp" />

<script type="text/javascript">
$(document).ready(function(){
$("#buydiv").click(function(){
	$("#show").css("display","");
	$("#buydiv").css("display","none");
});

$("#closefrom").click(function(){
	$("#show").css("display","none");
	$("#buydiv").css("display","");
});

})


</script>


</div>
</body>
</html>
