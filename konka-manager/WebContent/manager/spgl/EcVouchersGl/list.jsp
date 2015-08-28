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
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/spgl/EcVouchersGl">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td height="36" align="left" style="padding-left:5px;">
          			&nbsp;&nbsp;<strong class="fb">优惠券名称  ：</strong> <html-el:text property="title_like" styleId="title_like" maxlength="30"  />&nbsp;&nbsp;
          			 &nbsp;&nbsp; 
              <strong class="fb"> 所属组织：</strong><html-el:select property="dept_id" styleId="dept_id" style="width:120px;" onchange="this.form.submit();">
	          				<html-el:option value="">请选择</html-el:option>
	          				<c:forEach items="${deptList}" var="cur">
	          					<html-el:option value="${cur.id}">${cur.group_name}</html-el:option>
	          				</c:forEach>
	          			</html-el:select> &nbsp;&nbsp;&nbsp;
	          		<strong class="fb">申请人  ：</strong> <html-el:text property="apply_name_like" styleId="apply_name_like" maxlength="30"  />	
             
          </td>
        </tr>
         <tr>
          <td height="36" align="left" style="padding-left:5px;">
            &nbsp;&nbsp;<strong class="fb">申请日期：</strong>
        		<html-el:text property="add_date_start" styleId="add_date_start"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				至
				<html-el:text property="add_date_end" styleId="add_date_end"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
             &nbsp;&nbsp;<strong class="fb">使用品类： </strong><html-el:select property="pd_type" styleId="pd_type">
             				<html-el:option value="">请选择</html-el:option>
             				<html-el:option value="0">彩电</html-el:option>
             				<html-el:option value="3">小家电</html-el:option>
             				<html-el:option value="5">洗衣机</html-el:option>
             </html-el:select>
             &nbsp;&nbsp;<strong class="fb">使用属性：</strong> <html-el:select property="goods_type" styleId="goods_type">
             				<html-el:option value="">请选择</html-el:option>
             				<html-el:option value="0">新品</html-el:option>
             				<html-el:option value="2">热销</html-el:option>
             				<html-el:option value="3">特惠</html-el:option>
             				<html-el:option value="7">精品</html-el:option>
             </html-el:select>
          </td>
          </tr>
         <tr>
          <td height="36" align="left" style="padding-left:5px;">
           &nbsp;&nbsp;<strong class="fb">可使用商品：</strong>
            <html-el:text property="goods_like" styleId="goods_like" maxlength="30" style="width:120px;" />
           &nbsp;&nbsp;<strong class="fb">状态：</strong> <html-el:select property="info_state" styleId="info_state">
             				<html-el:option value="">请选择</html-el:option>
             				<html-el:option value="0">未使用</html-el:option>
             				<html-el:option value="1">已使用</html-el:option>
             </html-el:select>&nbsp;&nbsp;
             <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" /> 
         	  <logic-el:match name="popedom" value="+8+"> 
         	 <input type="button" value="Excel" id="export_excel" class="but_excel" style="margin-left: 10px;" /><font color="red">备注：导出按钮注意保密</font>
             </logic-el:match>
          </td>
          </tr>
        
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
		<%@ include file="/commons/pages/messages.jsp"%>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					&nbsp; 
				<!--  	<input type="button" class="but2" value="新 增" onclick="location.href='EcVouchersGl.do?method=add&mod_id=${af.map.mod_id}';" />  
					&nbsp; <input type="button" class="but2" value="批量" onclick="location.href='EcVouchersGl.do?method=add2&mod_id=${af.map.mod_id}';" />  -->
				</td>
			</tr>
		</table>		
	</div>
  <div class="rtabcont1" style="overflow: auto;">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td nowrap="nowrap" align="center" >优惠券名称</td>
        <td width="5%" nowrap="nowrap" align="center">面额</td>
        <td width="5%" nowrap="nowrap" align="center">申请人</td>
        <td width="5%" nowrap="nowrap" align="center">申请日期</td>
        <td width="5%" nowrap="nowrap" align="center">组织</td>
        <td width="10%" nowrap="nowrap" align="center">使用品类</td>
        <td width="10%" nowrap="nowrap" align="center">使用属性</td>
        <td width="10%" nowrap="nowrap" align="center">可使用商品</td>
        <td width="5%" nowrap="nowrap" align="center">状态</td>
        <td width="10%" nowrap="nowrap" align="center">使用时间</td>
        <td width="10%" nowrap="nowrap" align="center">兑换人</td>
        <td width="10%" nowrap="nowrap" align="center">优惠券编码</td> 
        <td width="10%" nowrap="nowrap" align="center">优惠券密码</td>
        <td width="10%" nowrap="nowrap" align="center">操作</td>
      </tr>
      <c:forEach items="${entityList}" var="cur" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${vs.count}</td>
          <td align="left" nowrap="nowrap">${cur.title}</td>
          <td align="right" nowrap="nowrap">
          <fmt:formatNumber type="number" value="${cur.price}" maxFractionDigits="0"/>
          </td>
          <td align="left" nowrap="nowrap">${cur.map.apply_user_name}</td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.apply_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
          <td align="left" nowrap="nowrap">${cur.map.group_name}</td>
          <td align="left" nowrap="nowrap">${cur.map.pd_type}</td>
          <td align="left" nowrap="nowrap">${cur.map.goods_type}</td>
          <td align="left" nowrap="nowrap" title="${cur.map.pd_sn}">${fn:substring(cur.map.pd_sn,0,30)}</td>
          <td align="left" nowrap="nowrap">
          <c:if test="${cur.info_state eq 0}">未使用</c:if>
          <c:if test="${cur.info_state eq 1}">已使用</c:if>  
          </td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.used_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
          <td align="left" nowrap="nowrap">${cur.map.ec_user_name}</td>
          <td align="left" nowrap="nowrap">${cur.vouchers_code}</td>
          <td align="left" nowrap="nowrap">${cur.vouchers_pwd}</td>
          <td align="center" nowrap="nowrap"> 
         	<a  class="fblue" href="javascript:doNeedMethod(null, 'EcVouchersGl.do', 'view', 'id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize());">查看</a>
         	 <!--  <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'EcVouchersGl.do','edit' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">修改</span>   -->   
          	<!-- <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('您确定删除吗？', 'EcVouchersGl.do','delete' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">删除</span> -->
          </td>
        </tr>
      </c:forEach>
    </table>
    <c:if test="${not vs.last}">
      <div style="height:40px;"></div>
    </c:if>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="EcVouchersGl.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "list");
								pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
								pager.addHiddenInputs("title_like", "${af.map.title_like}");
								pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
								pager.addHiddenInputs("apply_name_like", "${af.map.apply_name_like}");
								pager.addHiddenInputs("add_date_start", "${af.map.add_date_start}");
								pager.addHiddenInputs("add_date_end", "${af.map.add_date_end}");
								pager.addHiddenInputs("pd_type", "${af.map.pd_type}");
								pager.addHiddenInputs("goods_type", "${af.map.goods_type}");
								pager.addHiddenInputs("goods_like", "${af.map.goods_like}");
								pager.addHiddenInputs("info_state", "${af.map.info_state}");
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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>  
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	// 导出excel
    $("#export_excel").click(function(){
    	 this.value="正在提交...";
    	 this.disabled=true;
    	 this.form.action="${ctx}/manager/spgl/EcVouchersGl.do?method=sheet";
    	 this.form.submit();
    	 this.form.action="${ctx}/manager/spgl/EcVouchersGl.do";
    	 this.value="导出Excel";
    	 this.disabled=false;
    });
	
});

//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
