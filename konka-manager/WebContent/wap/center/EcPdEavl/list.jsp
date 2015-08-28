<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>开心猫</title> 
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/global.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/epp_index.css" /> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
<style type="text/css"> 
</style>
</head>
<body>  
<div class="mainbox">
<div class="maincont">     
      <table  style="width:100%" border="1">
        <tr class="tr1">
          <td width="40" nowrap="nowrap" align="left">商品名称</td>
          <td width="45" nowrap="nowrap" align="center">评分</td>
          <td nowrap="nowrap" align="center">评价信息</td> 
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr height="60">
              <td align="left"><a href="${ctx }/wap/PdShow.do?goods_id=${cur.goods_id }"><c:out value="${cur.map.pd_name }"/></a></td>
              <td align="center" nowrap="nowrap"> ${cur.eval_score } 分</td>
              <td align="left" style="line-height:22px;"><p style="margin-top:2px;font-size:12px;"> <b>标题：</b>
                  <c:out value="${cur.eval_title}"/>
                </p>
                <p style="font-size:12px;"> <b>优点：</b>
                  <c:out value="${cur.eval_con_merit}"/>
                </p>
                <p style="font-size:12px;"> <b>缺点：</b>
                  <c:out value="${cur.eval_con_defect}"/>
                </p>
                <p style="font-size:12px;"> <b>总结：</b>
                  <c:out value="${cur.eval_con_sumary}"/>
                </p></td>
              </tr>
              <tr>  
              	<td align="left" nowrap="nowrap" colspan="3">时间：<fmt:formatDate value="${cur.eval_date}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
              </tr> 
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