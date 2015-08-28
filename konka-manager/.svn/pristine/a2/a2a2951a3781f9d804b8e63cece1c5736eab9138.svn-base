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
.rtable1 td {
	padding:0px 2px;
}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <html-el:form action="/admin/KonkaMobileCustVisit">
  <div class="rtabcont1">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
        <c:if test="${af.map.report_type_tj eq 3||af.map.report_type_tj eq 4}">
        <html-el:hidden property="report_type_tj" styleId="report_type_tj" value="${af.map.report_type_tj}"/>
        
        </c:if>
<!--      <html-el:hidden property="report_type" styleId="report_type" value="${af.map.report_type_tj}" />-->
      <html-el:hidden property="isfirst" value="first"/>
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
       	<tr>
       		<td align="right"><strong class="fb">分&nbsp;公&nbsp;司：</strong></td>
       		<td>
       		<html-el:select property="subcomp_id" styleId="subcomp_id" >
	    	 <html-el:option value="">-请选择-</html-el:option>
	    		<c:forEach items="${deptList}" var="cur">
	    		<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
	    		</c:forEach>
	    	</html-el:select>
<!--       			<html-el:text property="subcomp_name_like" styleId="subcomp_name_like" size="20"/>-->
       		</td>
       		<td align="right"><strong class="fb">上&nbsp;报&nbsp;人：</strong></td>
       		<td>
       			<html-el:text property="report_nae_like" styleId="report_nae_like" size="20"/>
       		</td>
       		<td align="right">
       			<c:if test="${af.map.report_type_tj eq 5|| af.map.report_type_tj eq 1||af.map.report_type_tj eq 2 || af.map.report_type_tj eq 4}">
       				<strong class="fb">拜访状态：</strong>
       			</c:if>
       			<c:if test="${af.map.report_type_tj eq 3}">
       				<strong class="fb">开拓状态：</strong>
       			</c:if>
       		</td>
       		<td>
       			<html-el:select property="state" styleId="state" style="width:140px;">
	            <c:if test="${af.map.report_type_tj eq 3}">
	               <html-el:option value="">－请选择－</html-el:option>
	      		   <html-el:option value="1">开拓中</html-el:option>
	      		   <html-el:option value="2">已开拓成功</html-el:option>
	      		   <html-el:option value="3">已关闭</html-el:option>
	      	    </c:if>
	            <c:if test="${af.map.report_type_tj eq 4 || af.map.report_type_tj eq 5||af.map.report_type_tj eq 1||af.map.report_type_tj eq 2}">
	            <html-el:option value="">－请选择－</html-el:option>
	      		   <html-el:option value="0">需跟踪</html-el:option>
	      		   <html-el:option value="1">已关闭</html-el:option>
      	        </c:if>
        	 	</html-el:select>
       		</td>
       	</tr>
       		<c:if test="${af.map.report_type_tj eq 3}">
       		<tr>
           <td align="right"><strong class="fb">客户名称：</strong></td>
       		<td>
       			<html-el:text property="customer_name_like" styleId="customer_name_like" maxlength="40"/>
       		</td>
            </tr>
       		</c:if>
       	<c:if test="${af.map.report_type_tj eq 5||af.map.report_type_tj eq 1||af.map.report_type_tj eq 2}">
       	<tr>
       		<td align="right"><strong class="fb">客户编码：</strong></td>
       		<td>
       			<html-el:text property="r3_code_like" styleId="r3_code_like" maxlength="40"/>
       		</td>
       		<td align="right"><strong class="fb">客户名称：</strong></td>
       		<td>
       			<html-el:text property="customer_name_like" styleId="customer_name_like" maxlength="40"/>
       		</td>
       		<td align="right"><strong class="fb">终端名称：</strong></td>
       		<td>
       			<html-el:text property="shop_name_like" styleId="shop_name_like" maxlength="40"/>
       		</td>
       	</tr>
       	</c:if>
       	<tr>
       		<td align="right"><strong class="fb">开始时间：</strong></td>
       		<td>
       			<html-el:text property="_begin_date" styleId="begin_date"  size="20" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择时间" />
       		</td>
       		<td align="right"><strong class="fb">结束时间：</strong></td>
       		<td>
       			<html-el:text property="_end_date" styleId="end_date"  size="20" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择时间" />
       		</td>
       		<td align="right"><strong class="fb">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：</strong></td>
       		<td>
       			<html-el:select property="is_del" styleId="is_del" style="width:140px;">
		           <html-el:option value="">－请选择－</html-el:option>
	      		   <html-el:option value="0">正常</html-el:option>
	      		   <html-el:option value="1">删除</html-el:option>
        	 	</html-el:select> 
       		</td>
       	</tr>
       
       	<tr>
       		<c:if test="${af.map.report_type_tj ne 4}">
       		<td align="right" ><strong class="fb">客户类型：</strong></td>
       		<td align="left">
       			<html-el:select property="v_customer_type1" styleId="v_customer_type1" style="width:80px;">
            	     <html-el:option value="">-请选择-</html-el:option>
	            </html-el:select> 
	            <html-el:select property="v_customer_type2" styleId="v_customer_type2" style="width:130px;">
	                <html-el:option value="">-请选择-</html-el:option>
	            </html-el:select>
        	</td>
        		</c:if>
            <c:if test="${af.map.report_type_tj eq 5||af.map.report_type_tj eq 1||af.map.report_type_tj eq 2}">
            <td align="right"><strong class="fb">拜访类型：</strong></td>
       		<td>
       			<html-el:text property="visit_type_name_like" styleId="visit_type_name_like" maxlength="40"/>
       		</td>
       		
       		<td align="right"><strong class="fb">拜访类别：</strong></td>
       		<td>
       			<html-el:select property="report_type_tj" styleId="report_type_tj" value="${af.map.report_type_tj}" style="width:140px;">
		           <html-el:option value="5">－请选择－</html-el:option>
	      		   <html-el:option value="1">正常客户拜访</html-el:option>
	      		   <html-el:option value="2">重拾客户拜访</html-el:option>
        	 	</html-el:select> 
       		</td>
            </c:if>
            <td colspan="3"></td>
        	<td align="right">
        		<input name="button" type="button" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" />
        	</td>
        	<td colspan="3"></td>
       	</tr>
       
      </table>
  </div>
  <div style="text-align: left;padding-left: 10px">
    <c:if test="${af.map.report_type_tj eq 1 ||af.map.report_type_tj eq 2||af.map.report_type_tj eq 5}">
  	<input type="button" class="but2" value="正常" onclick="location.href='KonkaMobileCustVisit.do?method=add&report_type=1&report_type_tj=${af.map.report_type_tj}&mod_id=${af.map.mod_id}&is_del=${af.map.is_del}';" />&nbsp;&nbsp;&nbsp;&nbsp;
  	<input type="button" class="but2" value="重拾" onclick="location.href='KonkaMobileCustVisit.do?method=add&report_type=2&report_type_tj=${af.map.report_type_tj}&mod_id=${af.map.mod_id}&is_del=${af.map.is_del}';" />&nbsp;&nbsp;&nbsp;&nbsp;
  	</c:if>
  	<c:if test="${af.map.report_type_tj eq 3 ||af.map.report_type_tj eq 4}">
  	<input type="button" class="but2" value="新增" onclick="location.href='KonkaMobileCustVisit.do?method=add&report_type=${af.map.report_type_tj}&report_type_tj=${af.map.report_type_tj}&mod_id=${af.map.mod_id}&is_del=${af.map.is_del}';" />&nbsp;&nbsp;&nbsp;&nbsp;
  	</c:if>
	<input class="but_excel" type="button" name="export_excel" id="export_excel" value="导出" />
  </div>
  </html-el:form>
  <div class="rtabcont1">
		<%@ include file="/commons/pages/messages.jsp"%>
  </div>
  <div class="rtabcont1" style="overflow-x:auto;">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
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
        <td  nowrap="nowrap" align="left">操作</td>
      </tr>
      <c:forEach items="${entityList}" var="cur" varStatus="vs">
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
            	<c:choose>  
			    	<c:when test="${fn:length(cur.map.customer_name) > 10}">  
			        	<c:out value="${fn:substring(cur.map.customer_name, 0, 10)}......" />  
			    	</c:when>  
			   	<c:otherwise>  
			      	<c:out value="${cur.map.customer_name}" />  
			    </c:otherwise>  
			    </c:choose> 
           	</td>
        </c:if>
        <c:if test="${af.map.report_type_tj eq 5||af.map.report_type_tj eq 1||af.map.report_type_tj eq 2}">
            <td align="left" nowrap="nowrap" title="${cur.map.shop_name}">
            	<c:choose>  
			    	<c:when test="${fn:length(cur.map.shop_name) > 10}">  
			        	<c:out value="${fn:substring(cur.map.shop_name, 0, 10)}......" />  
			    	</c:when>  
			   	<c:otherwise>  
			      	<c:out value="${cur.map.shop_name}" />  
			    </c:otherwise>  
			     </c:choose> 
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
                <c:choose>  
			    <c:when test="${fn:length(cur.map.visit_type_names) > 10}">  
			        <c:out value="${fn:substring(cur.map.visit_type_names, 0, 10)}......" />  
			    </c:when>  
			   <c:otherwise>  
			      <c:out value="${cur.map.visit_type_names}" />  
			    </c:otherwise>  
			    </c:choose> 
            </td>
        </c:if>
        <c:if test="${af.map.report_type_tj eq 5 ||af.map.report_type_tj eq 1||af.map.report_type_tj eq 2}">
        <td align="left" nowrap="nowrap" title="${cur.feed_list}">
        	<c:choose>  
				<c:when test="${fn:length(cur.feed_list) > 10}">  
			        <c:out value="${fn:substring(cur.feed_list, 0, 10)}......" />  
			    </c:when>  
			   	<c:otherwise>  
			      	<c:out value="${cur.feed_list}" />  
			    </c:otherwise>  
			</c:choose> 
        </td>
        </c:if>
        <td align="left" nowrap="nowrap" title="${cur.visit_desc}">
        	<c:choose>  
			    <c:when test="${fn:length(cur.visit_desc) > 10}">  
			        <c:out value="${fn:substring(cur.visit_desc, 0, 10)}......" />  
			    </c:when>  
			<c:otherwise>  
			    <c:out value="${cur.visit_desc}" />  
			</c:otherwise>  
			</c:choose> 
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
           <!--  <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaMobileCustVisit.do','map' ,'visit_id=${cur.visit_id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">定位</span> -->
            <c:choose>  
			    <c:when test="${fn:length(cur.map.address) > 10}">
			        <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaMobileCustVisit.do','map' ,'visit_id=${cur.visit_id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">  
			        <c:out value="${fn:substring(cur.map.address, 0, 10)}......" /> 
			        </span> 
			    </c:when>  
			<c:otherwise> 
			     <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaMobileCustVisit.do','map' ,'visit_id=${cur.visit_id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())"> 
			     <c:out value="${cur.map.address}" />  
			     </span> 
			</c:otherwise>  
			</c:choose> 
        </td>
        <td>
           <c:if test="${not empty cur.map.fj_paths}">
	          <c:set var="fj_paths" value="${fn:split(cur.map.fj_paths,',')}" />
	          <c:forEach items="${fj_paths}" var="fj_path" varStatus="vs1">
	          	      <a href="${ctx}/${fj_path}" target="_blank">&nbsp;附件${vs1.count}&nbsp;</a>
	          </c:forEach>
          </c:if>
          </td>
        <td align="center" nowrap="nowrap"> 
            <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaMobileCustVisit.do','view' ,'visit_id=${cur.visit_id}&mod_id=${af.map.mod_id}&report_type=${cur.report_type}&report_type_tj=${af.map.report_type_tj}&' + $('#bottomPageForm').serialize())"')">查看</span>
        	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaMobileCustVisit.do','edit' ,'visit_id=${cur.visit_id}&mod_id=${af.map.mod_id}&report_type=${cur.report_type}&report_type_tj=${af.map.report_type_tj}&' + $('#bottomPageForm').serialize())"')">修改</span>
          	<c:if test="${cur.is_del eq 0}">
            	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('确定要删除吗?', 'KonkaMobileCustVisit.do','delete' ,'visit_id=${cur.visit_id}&mod_id=${af.map.mod_id}&report_type=${cur.report_type}&report_type_tj=${af.map.report_type_tj}&' + $('#bottomPageForm').serialize())"')">删除</span> 
          	</c:if>
           	<c:if test="${cur.is_del eq 1}">
            	<span style="cursor:pointer;color:grey;" >删除</span> 
          	</c:if>
        </td> 
      </tr>
      </c:forEach>
    </table>
    <c:if test="${not vs.last}">
      <div style="height:10px;"></div>
    </c:if>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaMobileCustVisit.do">
    <input id='export_id' style="display:none"  name='excel_all' value='0' />
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="20"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
						var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "list");
								pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
								pager.addHiddenInputs("report_type_tj", "${af.map.report_type_tj}");
								pager.addHiddenInputs("_begin_date", "${af.map._begin_date}");
								pager.addHiddenInputs("_end_date", "${af.map._end_date}");
								pager.addHiddenInputs("state", "${af.map.state}");
								pager.addHiddenInputs("is_del", "${af.map.is_del}");
								pager.addHiddenInputs("r3_code_like", "${af.map.r3_code_like}");
								pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");
								pager.addHiddenInputs("shop_id", "${af.map.shop_id}");
								pager.addHiddenInputs("shop_name_like", "${af.map.shop_name_like}");
								pager.addHiddenInputs("visit_type_name_like", "${af.map.visit_type_name_like}");
								pager.addHiddenInputs("report_nae_like", "${af.map.report_nae_like}");
								pager.addHiddenInputs("subcomp_id", "${af.map.subcomp_id}");
								pager.addHiddenInputs("v_customer_type1", "${af.map.v_customer_type1}");	
						        pager.addHiddenInputs("v_customer_type2", "${af.map.v_customer_type2}");	
								pager.addHiddenInputs("isfirst", "${af.map.isfirst}");
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
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
$("#shop_id").attr("Require","false").attr("dataType", "Number").attr("msg", "终端名称必须填写数字");

$("#button").click(function(){
	if (Validator.Validate(this.form, 1)){
		this.form.submit();
		}
});
//客户类型初始化
$("#v_customer_type1").attr({"subElement": "v_customer_type2", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type1}"});
$("#v_customer_type2").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type2}"});
$("#v_customer_type1").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaShopType", "par_id", "0", false);
$("#v_customer_type1").change();
});
//导出excel
$("#export_excel").click(function(){
	if(confirm("提示，您确认导出数据？")){
		//CNZZ统计代码
		$("#export_id").val(1);
		$("#bottomPageForm").submit();
	}
	$("#export_id").val(0);
});
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
