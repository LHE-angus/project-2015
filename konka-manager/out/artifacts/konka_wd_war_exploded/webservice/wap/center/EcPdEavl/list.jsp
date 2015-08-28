<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>触网</title> 
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/global.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/epp_index.css" />  
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/ping.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
<style type="text/css"> 
</style>
</head>
<body><div class="top_class"><span class="lspan"><a href="javascript:void(0);history.back();"><img src="${ctx}/styles/epp/mobile/images/return.gif" /></a></span><h3>评论信息</h3><a href="javascript:void(0);showNav();" id="btnJdkey" class="new-a-jd"><span>触网</span></a>
	<div class="new-jd-tab" style="top:45px;display:none;" id="jdkey">
	<div class="new-tbl-type">
	<a href="<c:url value='/webservice/wap/Index.do' />" class="new-tbl-cell"><span class="icon">首页</span><p style="color:#6e6e6e;">首页</p>	</a>
	<a href="<c:url value='/webservice/wap/ProdType.do' />" class="new-tbl-cell"><span class="icon2">产品分类</span><p style="color:#6e6e6e;">产品分类</p></a>
	<a href="<c:url value='/webservice/wap/ShoppingCar.do' />" id="html5_cart" class="new-tbl-cell"><span class="icon3 on">购物车</span><p style="color:#6e6e6e;" class="on">购物车</p></a>
	<a href="<c:url value='/webservice/wap/center/Index.do' />" class="new-tbl-cell"><span class="icon4">会员中心</span> <p style="color:#6e6e6e;">会员中心</p></a>
	</div>
	</div>
</div>
<div id="content">
<div class="mainbox">
<div class="maincont">     
       <table style="width:100%">
        <tr height="40">
          <th width="35%" nowrap="nowrap" align="center">商品名称</th> 
          <th width="65%" nowrap="nowrap" align="center">评价信息</th> 
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr height="60">
              <td align="left" valign="top" ><a href="${ctx }/webservice/wap/PdShow.do?goods_id=${cur.goods_id }" style="margin:5px;"><c:out value="${cur.map.pd_name }"/></a></td> 
              <td align="left" style="line-height:22px;"> 
              	评分：<span class="star${cur.eval_score }"></span><br/>
               <c:out value="${cur.eval_title}"/>
               <c:out value="${cur.eval_con_sumary}"/>
               <br/><fmt:formatDate value="${cur.eval_date}" pattern="yyyy-MM-dd hh:mm:ss" />
               <c:if test="${not empty cur.konkaPeAttachmentsList}"><div>			    
			    晒单：  <c:forEach var="imgs" items="${cur.konkaPeAttachmentsList}" varStatus="vsi"> 
			       <a href="${ctx}/${imgs.save_path}" target="_blank"><img src="${ctx}/${imgs.save_path}" width="60"></img></a> 
			      </c:forEach> </div>
			  </c:if>	
			  <c:if test="${not empty cur.re_content}">
		      	  <p><span style="font-size:12px;line-height:40px;">客服回复</span>： <font color="red"><c:out value="${cur.re_content}"/></font></p>
		      	  </c:if>	
              </td> 
            </tr> 
           <tr><td colspan="2"><hr/></td></tr>
           
          </c:forEach>
          <c:if test="${empty entityList }">
            <tr height="60">
              <td align="center" nowrap="nowrap" colspan="4"> 暂无商品评价记录 </td>
            </tr>
          </c:if>
        </tbody>
      </table>
      <c:if test="${af.map.pager.pageCount>1}">
        <form id="bottomPageForm" name="bottomPageForm" method="post" action="?">
          <table style="width:100%">
            <tr>
              <td height="40"><div style="text-align: right; padding-right: 5px;">
                  <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
                  <script type="text/javascript">
			                     var pager = new Pager(document.bottomPageForm, parseInt('${af.map.pager.recordCount}'), parseInt('${af.map.pager.pageSize}'), parseInt('${af.map.pager.currentPage}'));
			                     pager.addHiddenInputs("method", "list");
			                     pager.addHiddenInputs("start_date", "<c:out value='${af.map.start_date}'/>"); 	
			                     pager.addHiddenInputs("end_date", "<c:out value='${af.map.end_date}'/>"); 	
			                     document.write(pager.toString());
			                 </script>
                </div></td>
            </tr>
          </table>
        </form>
      </c:if>
    </div>
</div>
</div>  
</body>
<script type="text/javascript">//<![CDATA[  
$(document).ready(function(){// 
		
}); 
     
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