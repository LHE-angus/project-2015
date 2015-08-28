<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
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
 <div class="rtabcont1" style="overflow-x:auto;">
    <table width="100%" border="1" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td nowrap="nowrap" align="left" >开始时间</td>
         <c:if test="${af.map.report_type_tj eq 5||af.map.report_type_tj eq 1||af.map.report_type_tj eq 2}">
        <td nowrap="nowrap" align="left" >结束时间</td>
          </c:if>
        <td  nowrap="nowrap" align="left">分公司</td> 
        <td  nowrap="nowrap" align="left">上报人</td>
        <c:if test="${af.map.report_type_tj eq 5||af.map.report_type_tj eq 1||af.map.report_type_tj eq 2}">
        	<td nowrap="nowrap" align="left">拜访类别</td>
        	<td nowrap="nowrap" align="left">客户编码</td>
        </c:if>
        <c:if test="${af.map.report_type_tj eq 5||af.map.report_type_tj eq 1||af.map.report_type_tj eq 2 || af.map.report_type_tj eq 3}">
         	<td nowrap="nowrap" width="150px;" align="left">客户名称</td>
        </c:if>
        <c:if test="${af.map.report_type_tj eq 5||af.map.report_type_tj eq 1||af.map.report_type_tj eq 2}">
         	<td  nowrap="nowrap" width="150px;" align="center">终端名称</td>
         	<td nowrap="nowrap" align="left">客户类型</td>
         	<td nowrap="nowrap" align="left">客户细分类型</td>
         	<td nowrap="nowrap" align="left">拜访类型</td>
        </c:if>
        <c:if test="${af.map.report_type_tj eq 5||af.map.report_type_tj eq 1||af.map.report_type_tj eq 2}">
	        <td nowrap="nowrap" align="left">反馈问题</td>
        </c:if>
        <td  nowrap="nowrap" align="left"><c:if test="${af.map.report_type_tj eq 5||af.map.report_type_tj eq 1||af.map.report_type_tj eq 2}">拜访说明</c:if> <c:if test="${af.map.report_type_tj eq 3}">  开拓说明</c:if><c:if test="${af.map.report_type_tj eq 4}">  事物说明</c:if></td>
        <c:if test="${af.map.report_type_tj eq 5||af.map.report_type_tj eq 1||af.map.report_type_tj eq 2}">
        	<td  nowrap="nowrap" align="left">被访人 </td>
        	<td  nowrap="nowrap" align="left">被访人电话</td>
        	<td nowrap="nowrap" align="left">拜访状态</td>
        </c:if>
        <c:if test="${af.map.report_type_tj eq 4}">
        	<td nowrap="nowrap" align="left">拜访状态</td>
        </c:if>
        <c:if test="${af.map.report_type_tj eq 3}">
        	<td nowrap="nowrap" align="left">开拓状态</td>
        </c:if>
        <td nowrap="nowrap" align="left" >创建时间</td>
        <td  nowrap="nowrap" align="left">状态</td>
        <td nowrap="nowrap" align="left">数据来源</td>
        <c:if test="${af.map.haveRole}">
        <td nowrap="nowrap" align="left">手机型号/IMEI号</td>
        </c:if>
        <td nowrap="nowrap" width="150px;" align="left">当前位置</td>
        <td nowrap="nowrap" width="150px;" align="left">附件</td>
      </tr>
      <c:forEach items="${allList}" var="cur" varStatus="vs">
      <tr style="color: <c:if test="${((cur.report_type eq 4 ||cur.report_type eq 1||cur.report_type eq 2)&&cur.state eq 0)||(cur.report_type eq 3 && cur.state eq 1)}">red</c:if>">
      	<td align="left" nowrap="nowrap">${vs.count}</td>
        <td align="center" nowrap="nowrap">
        	<fmt:formatDate value="${cur.begin_date}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate>
        </td>
        <c:if test="${af.map.report_type_tj eq 5||af.map.report_type_tj eq 1||af.map.report_type_tj eq 2}">
	        <td align="center" nowrap="nowrap">
	        	<fmt:formatDate value="${cur.end_date}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate>
	        </td>
        </c:if>
        <td align="left" nowrap="nowrap">${cur.map.subcomp_name}</td> 
        <td align="left" nowrap="nowrap">${cur.report_nae}</td>
        <c:if test="${af.map.report_type_tj eq 5 ||af.map.report_type_tj eq 1||af.map.report_type_tj eq 2}">
        	<td align="left" nowrap="nowrap">
	        	 <c:if test="${cur.report_type eq 1}"> 正常客户拜访</c:if>
		         <c:if test="${cur.report_type eq 2}"> 重拾客户拜访</c:if>
        	</td>
        	<td align="left" nowrap="nowrap">${cur.map.r3_code}</td>
        </c:if>
        <c:if test="${af.map.report_type_tj eq 5||af.map.report_type_tj eq 1||af.map.report_type_tj eq 2 || af.map.report_type_tj eq 3}">
            <td align="left" nowrap="nowrap" title="${cur.map.customer_name}">
			      	<c:out value="${cur.map.customer_name}" />  
           	</td>
        </c:if>
        <c:if test="${af.map.report_type_tj eq 5||af.map.report_type_tj eq 1||af.map.report_type_tj eq 2}">
            <td align="left" nowrap="nowrap" title="${cur.map.shop_name}">
			      	<c:out value="${cur.map.shop_name}" />  
                </td>
                <td>
		            <c:choose>  
					    <c:when test="${cur.map.par_customer_type_name eq null && fn:substring(cur.map.r3_code, 0, 2) eq 'WD'}">  
					                                        网点客户
					    </c:when>  
					   <c:otherwise>  
					      <c:out value="${cur.map.par_customer_type_name}" />  
					    </c:otherwise>  
					</c:choose> 
                </td>
                
                <td>
		            <c:choose>  
					    <c:when test="${cur.map.customer_type_name eq null && fn:substring(cur.map.r3_code, 0, 2) eq 'WD'}">  
					                                      
					    </c:when>  
					   <c:otherwise>  
					      <c:out value="${cur.map.customer_type_name}" />  
					    </c:otherwise>  
					</c:choose> 
                </td>
                
                
<!--                <td align="left" nowrap="nowrap" >${cur.map.visit_type_ids}</td>-->
                <td align="left" nowrap="nowrap" title="${cur.map.visit_type_names}" >
			      <c:out value="${cur.map.visit_type_names}" />  
            </td>
        </c:if>
        <c:if test="${af.map.report_type_tj eq 5 ||af.map.report_type_tj eq 1||af.map.report_type_tj eq 2}">
        <td align="left" nowrap="nowrap" title="${cur.feed_list}">
			      	<c:out value="${cur.feed_list}" />  
        </td>
        </c:if>
        <td align="left" nowrap="nowrap" title="${cur.visit_desc}">
			    <c:out value="${cur.visit_desc}" />  
        </td>
        <c:if test="${af.map.report_type_tj eq 5 ||af.map.report_type_tj eq 1||af.map.report_type_tj eq 2}">
	        <td align="left" nowrap="nowrap" title="${cur.consumer_name}">${cur.consumer_name}</td>
	        <td align="left" nowrap="nowrap" title="${cur.consumer_phone}">${cur.consumer_phone}</td>
        </c:if>
        <td align="left" nowrap="nowrap">
        	<c:if test="${af.map.report_type_tj eq 3}">
	          	<c:if test="${cur.state eq 1}">开拓中</c:if>
	          	<c:if test="${cur.state eq 2}">已开拓成功</c:if>
	          	<c:if test="${cur.state eq 3}">已关闭</c:if>
	       	</c:if>
           	<c:if test="${af.map.report_type_tj eq 4 ||af.map.report_type_tj eq 5 ||af.map.report_type_tj eq 1||af.map.report_type_tj eq 2}">
            	<c:if test="${cur.state eq 0}">需跟踪</c:if>
	          	<c:if test="${cur.state eq 1}">已关闭</c:if>
          	</c:if>
	    </td>
        <td align="left" nowrap="nowrap">
        	<fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate>
        </td>
        <td align="left" nowrap="nowrap">
          	<c:if test="${cur.is_del eq 0}">正常</c:if>
          	<c:if test="${cur.is_del eq 1}">已删除</c:if>
        </td>
        <td align="left" nowrap="nowrap">
        	<c:if test="${cur.data_source eq 0}">手机端</c:if>
	      	<c:if test="${cur.data_source eq 1}">手机端</c:if>
           	<c:if test="${cur.data_source eq 2}">web端</c:if>
           	<c:if test="${cur.data_source eq 3}">手机端</c:if>
        </td>
        <c:if test="${af.map.haveRole}">
         <td align="center" nowrap="nowrap">
        	${cur.map.phone_type}/${cur.imei}
        </td>
        </c:if>
        <td width="150px;" title="${cur.map.address}">
			     <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaMobileCustVisit.do','map' ,'visit_id=${cur.visit_id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())"> 
			     <c:out value="${cur.map.address}" />  
			     </span> 
        </td>
        <c:if test="${not empty cur.map.fj_paths}">
	    	<c:set var="fj_paths" value="${fn:split(cur.map.fj_paths,',')}" />
	    	<c:forEach items="${fj_paths}" var="fj_path" varStatus="vs1">
        	<td>
	        	<a href="http://qdgl.konka.com/${fj_path}" target="_blank">附件${vs1.count}</a>
          	</td>
	   		</c:forEach>
      	</c:if>
      </tr>
      </c:forEach>
    </table>
  </div>
</body>
</html>
