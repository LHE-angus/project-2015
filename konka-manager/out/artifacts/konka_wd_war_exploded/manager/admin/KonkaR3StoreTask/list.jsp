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
.txt {float: left;padding-top:8px;}
.txt a {color: blue;}
.float_div{
	position: absolute;
	border: solid 1px #d1e3f5;
	top:220px;
	text-align: center;
	background: #f5f4f4;
	left:40%;
	width:400px;
	padding-bottom: 20px;
	padding-top: 0px;
	display: none;
	z-index:1000;
}
.float_div div{margin-top: 0px;}
.close{ float: right;bottom: 0px;color:red;}
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
    <html-el:form action="/admin/KonkaR3StoreTask">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td  align="left" style="padding-left:5px;">
          			&nbsp;&nbsp;分公司 ： <html-el:text property="dept_name_like" styleId="dept_name_like" styleClass="webinput" maxlength="30"/>
               &nbsp;&nbsp;门店所属经办：<html-el:text property="jb_name_like" styleId="jb_name_like" styleClass="webinput" maxlength="30"/>
               &nbsp;&nbsp;客户名称：<html-el:text property="customer_name_like" styleId="customer_name_like" styleClass="webinput" maxlength="30"/>
          </td>
        </tr>
        <tr>
          <td  align="left" style="padding-left:5px;">
          			&nbsp;&nbsp;客户R3编码 ： <html-el:text property="r3_code_like" styleId="r3_code_like" styleClass="webinput" maxlength="30"/>
               &nbsp;&nbsp;业务员：<html-el:text property="ywy_name_like" styleId="ywy_name_like" styleClass="webinput" maxlength="30"/>
               &nbsp;&nbsp;门店名称：<html-el:text property="store_name_like" styleId="store_name_like" styleClass="webinput" maxlength="30"/>
          </td>
        </tr>
        <tr>
          <td  align="left" style="padding-left:5px;">
          			&nbsp;&nbsp;日期 ： 
						<html-el:select property="year" styleId="year">
		          		  	<html-el:option value="2011">2011年</html-el:option>
		          		  	<html-el:option value="2012">2012年</html-el:option>
		          		  	<html-el:option value="2013">2013年</html-el:option>
		          		  	<html-el:option value="2014">2014年</html-el:option>
		          		  	<html-el:option value="2015">2015年</html-el:option>
		          		  	<html-el:option value="2016">2016年</html-el:option>
		          		  	<html-el:option value="2017">2017年</html-el:option>
		          		  	<html-el:option value="2018">2018年</html-el:option>
		          		  	<html-el:option value="2019">2019年</html-el:option>
		          		  	<html-el:option value="2020">2020年</html-el:option>
		          		  	<html-el:option value="2021">2021年</html-el:option>
		          		  	<html-el:option value="2022">2022年</html-el:option>
		          		  	<html-el:option value="2023">2023年</html-el:option>
		          		  	<html-el:option value="2024">2024年</html-el:option>
		          		  	<html-el:option value="2025">2025年</html-el:option>
		          		  	<html-el:option value="2026">2026年</html-el:option>
		          		  	<html-el:option value="2027">2027年</html-el:option>
	          		  	</html-el:select>
	          		  	<html-el:select property="month" styleId="month">
					      	<html-el:option value="1">1月</html-el:option>
					      	<html-el:option value="2">2月</html-el:option>
					      	<html-el:option value="3">3月</html-el:option>
					      	<html-el:option value="4">4月</html-el:option>
					      	<html-el:option value="5">5月</html-el:option>
					      	<html-el:option value="6">6月</html-el:option>
					      	<html-el:option value="7">7月</html-el:option>
					      	<html-el:option value="8">8月</html-el:option>
					      	<html-el:option value="9">9月</html-el:option>
					      	<html-el:option value="10">10月</html-el:option>
					      	<html-el:option value="11">11月</html-el:option>
					      	<html-el:option value="12">12月</html-el:option>
				      	</html-el:select>
				     
               &nbsp;&nbsp;促销员：<html-el:text property="cxy_name_like" styleId="cxy_name_like" styleClass="webinput" />
               &nbsp;&nbsp;
            <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" />
            <input type="button" value="导出" id="export_excel" class="but_excel" style='margin-left: 10px;' />
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
						<logic-el:match name="popedom" value="+1+">	
					<input name="button" type="button" class="but_excel" class="websub" id="btn_import" value="导入" />
          				</logic-el:match>&nbsp;
          			<a href="${ctx}/manager/admin/KonkaR3StoreTask/carndo.xls" class="fblue">excel模版下载</a>
				</td>
			</tr>
		</table>		
	</div>
  <div class="rtabcont1">
    <div style="overflow-x:auto;height: 340px;">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td width="5%"nowrap="nowrap" align="center" >年度</td>
        <td width="5%"nowrap="nowrap" align="center" >月份</td>
        <td width="10%" nowrap="nowrap" align="center">分公司</td>
        <td width="10%" nowrap="nowrap" align="center">门店所属经办</td>
        <td width="10%" nowrap="nowrap" align="center">客户名称</td>
        <td width="10%" nowrap="nowrap" align="center">客户R3编码</td>
        <td width="10%" nowrap="nowrap" align="center">门店ID</td>
        <td width="10%" nowrap="nowrap" align="center">门店名称</td>
        <td width="10%" nowrap="nowrap" align="center">业务员</td>
        <td width="10%" nowrap="nowrap" align="center">促销员</td>
        <td width="10%" nowrap="nowrap" align="center">任务额</td>
        <td width="6%" nowrap="nowrap" align="center">操作</td>
      </tr> 
      <c:forEach items="${entityList}" var="cur" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${vs.count}</td>
          <td align="left" nowrap="nowrap">${cur.year}</td>
          <td align="left" nowrap="nowrap">${cur.month}</td>
          <td align="left" nowrap="nowrap">${cur.dept_name}</td>
          <td align="left" nowrap="nowrap">${cur.jb_name}</td>
          <td align="left" nowrap="nowrap">${cur.customer_name}</td>
          <td align="left" nowrap="nowrap">${cur.r3_code}</td>
          <td align="right" nowrap="nowrap">${cur.store_id}</td>
          <td align="center" nowrap="nowrap">${cur.store_name}</td>
          <td align="center" nowrap="nowrap">${cur.ywy_name}</td>
          <td align="center" nowrap="nowrap">${cur.cxy_name}</td>
          <td align="right" nowrap="nowrap">${cur.task_money}</td>
          <td align="center" nowrap="nowrap"> 
         	 <!--  <a class="fblue" href="javascript:doNeedMethod(null, 'KonkaR3StoreShow.do', 'view', 'id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize());">查看</a>-->
         	 <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaR3StoreTask.do','edit' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">修改</span>  
          	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('您确定删除吗？', 'KonkaR3StoreTask.do','delete' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">删除</span>
          </td>
        </tr>
      </c:forEach>
    </table>
    </div>
    <c:if test="${not vs.last}">
      <div style="height:40px;"></div>
    </c:if>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaR3StoreTask.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "list");
								pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
								pager.addHiddenInputs("store_name_like", "${af.map.store_name_like}");
								pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");
								pager.addHiddenInputs("jb_name_like", "${af.map.jb_name_like}");
								pager.addHiddenInputs("cxy_name_like", "${af.map.cxy_name_like}");
								pager.addHiddenInputs("ywy_name_like", "${af.map.ywy_name_like}");
								pager.addHiddenInputs("dept_name_like", "${af.map.dept_name_like}");
								pager.addHiddenInputs("r3_code_like", "${af.map.r3_code_like}");
								pager.addHiddenInputs("year", "${af.map.year}");
								pager.addHiddenInputs("month", "${af.map.month}");
								document.write(pager.toString());
			  </script>
            </div></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div class="float_div" id="uploadPanel" style="z-index:10000;position:absolute;">
      <div style="font-size:14px;">
        <b>请选择Excel文件</b>
      </div><br />
	<form action="KonkaR3StoreTask.do?method=excelImport" method="post" enctype="multipart/form-data">
      <html-el:hidden property="method" value="excelImport" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	<input type="file" name="excel"/> <input type="button" name="btn_submit" class="but4" id="btn_submit" value="导入"/>
	</form>
	<span style="float: right;bottom: 0px;" id="btn_close"><span style="color:red;">取消</span>&nbsp;</span>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#btn_import").click(function(){$("#uploadPanel").fadeIn(500);	});
	$("#btn_close").click(function(){$("#uploadPanel").fadeOut(500);		
		$("#excel").after($("#excel").clone().val(""));   
		$("#excel").remove();		
	}); 
	$("#btn_submit").click(function(){
		this.disabled=true;
		this.value="正在导入。。。";
		$("#btn_close").hide(); 
		this.form.submit();
	}); 


	// 导出excel
    $("#export_excel").click(function(){
    	 this.value="正在提交...";
    	 this.disabled=true;
    	 this.form.action="${ctx}/manager/admin/KonkaR3StoreTask.do?method=excel";
    	 this.form.submit();
    	 this.form.action="${ctx}/manager/admin/KonkaR3StoreTask.do";
    	 this.value="导出Excel";
    	 this.disabled=false;
    });

	
});




//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
