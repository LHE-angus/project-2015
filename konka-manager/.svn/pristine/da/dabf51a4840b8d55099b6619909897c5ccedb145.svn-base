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
.webinput {
	background:#f5f4f4;
	padding-left: 5px;
	height: 19px;
	line-height: 19px;
	/*font-family: Arial, Helvetica, sans-serif;*/
	border: #ccc solid 1px;
}
ul.ckUl{list-style-type:none;display:inline;}
ul.ckUl li{float:left;margin:auto 5px auto 0px;/*padding:2px 5px;*/}
input,textarea,select{font-family:Microsoft Yahei;font-size:12px;}
.ck-li{position:relative;}
.ck-li .hidden-accessible{position:absolute !important;clip:rect(1px 1px 1px 1px);}
.ck-li .ck-default{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #CCC;background: #F6F6F6;font-weight: bold;color:#C4C4C4;cursor:pointer;}
.ck-li .ck-hover{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #FBCB09; background:#FDF5CE;font-weight: bold;color:#C77405;cursor:pointer;}
.ck-li .ck-visited{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:2px solid #EF0F28/*#FF4800/*FBD850*/; background:white url("${ctx}/styles/customer/images/ck-visited.gif") right bottom no-repeat;font-weight:bold;color:#EF0F28/*#FF4800/*#EB8F00*/;cursor:pointer;}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont" id="body_oarcont">
  <div class="rtabcont1">
		<%@ include file="/commons/pages/messages.jsp"%>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
				    <c:if test="${is_admin eq 1}">
					<input type="button" class="but2" value="上架" onclick="location.href='EcBcompPdUpNew.do?method=add2&mod_id=${af.map.mod_id}&goods_id_1=${af.map.goods_id}&pd_name=${af.map.pd_name}';" />
					</c:if>
			 		<c:if test="${is_fgs eq 1 and af.map.lock_state ne 1}">
			 		<input type="button" class="but2" value="上架" onclick="location.href='EcBcompPdUpNew.do?method=add2&mod_id=${af.map.mod_id}&goods_id_1=${af.map.goods_id}&pd_name=${af.map.pd_name}';" />
			 		</c:if>
				</td>
			</tr>
		</table>		
	</div>
 <div style="background-color: yellow;font-size: 15px;">商品型号：${af.map.pd_sn}&nbsp;&nbsp;&nbsp;产品所属:<c:if test="${af.map.pd_dept eq 0}">总部产品</c:if>
             <c:if test="${af.map.pd_dept ne 0}">分公司产品</c:if>
  </div>	
  <div class="rtabcont1">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr >
        <td width="5%"  align="center">序号</td>
        <td  width="10%"  align="center">总部 /分公司</td>
        <td  width="10%"  align="center">分公司</td>
        <td  width="10%"  align="center" >上架时间</td>
        <td  width="10%"  align="center" >下架时间</td>
        <td  width="10%"  align="center" >添加人</td>
        <!--  <td  width="10%"  align="center" >最后修改人</td>
        <td  width="10%"  align="center" >修改时间</td>  -->
        <td  width="10%"  align="center" >状态</td>
        <td  width="10%"  align="center">操作</td>
      </tr>
      <c:forEach items="${entityList}" var="cur" varStatus="vs">
        <tr>
          <td align="center" >${vs.count}</td>
           <td  align="left" >
         	 <c:if test="${cur.plat_sys eq 0}">总部</c:if>
             <c:if test="${cur.plat_sys eq 1}">分公司</c:if>  
          </td>
          <td align="center" >
          	${cur.map.dept_name}
          </td>
           <td align="center" >
          	<fmt:formatDate value="${cur.up_time}" pattern="yyyy-MM-dd"/>
          </td>
          <td align="center" ><fmt:formatDate value="${cur.down_time}" pattern="yyyy-MM-dd"/></td>
           <td align="center" >${cur.map.add_name}</td>
         <!--  <td align="center" >${cur.map.modify_name}</td>  
          <td align="center" ><fmt:formatDate value="${cur.modify_date}" pattern="yyyy-MM-dd"/></td>  -->
          <td align="center" >
          <c:if test="${cur.map.is_up eq 0}"><span style="color: green;">上架中</span></c:if>
          <c:if test="${cur.map.is_up eq 1}"><span style="color: red;">已下架</span></c:if>
          </td>
          <td align="center" >
             <c:if test="${is_admin eq 1}"> 
         	 <input type="button" class="but2" value="修改" onclick="location.href='EcBcompPdUpNew.do?method=edit2&id=${cur.id}&goods_id=${af.map.goods_id}&mod_id=${af.map.mod_id}';" />
          	 </c:if>
          	 <c:if test="${is_fgs eq 1 and af.map.lock_state ne 1 and cur.plat_sys eq 1 and   (fn:contains(af.map.group_id,cur.added_dept_id) and cur.added_dept_id ne 0 ) }">       
          	  <input type="button" class="but2" value="修改" onclick=" location.href='EcBcompPdUpNew.do?method=edit2&id=${cur.id}&goods_id=${af.map.goods_id}&mod_id=${af.map.mod_id}';" />
          	 </c:if>
          </td>
        </tr>
      </c:forEach>
    </table>
    <c:if test="${not vs.last}">
      <div style="height:40px;"></div>
    </c:if>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="EcBcompPdUpNew.do">  
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "list2");
								pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
								pager.addHiddenInputs("goods_id", "${af.map.goods_id}");
								document.write(pager.toString());
							</script>
            </div></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
});

//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
