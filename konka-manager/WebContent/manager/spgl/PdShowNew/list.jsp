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
<link href="${ctx}/scripts/jgrowl/jgrowl.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/asyncbox/skins/Chrome/asyncbox.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/colorbox.css" rel="stylesheet" type="text/css" />
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


.spancss{ 
width:60px;     
height:20px; 
border:1px solid #000; 
display:-moz-inline-box; /* css注释：for ff2 */ 
display:inline-block; 
text-align:center; 
line-height:20px; 
background-color: burlywood; 
} 

.login_div{position: absolute;	border: solid 1px #d1e3f5;top:10%;text-align: center;border:1px solid #E03425;background: #f5f4f4;left:30%;width:200px;padding-bottom: 0px;padding-top: 0px;display: none;z-index:10002;} 
fieldset {text-align:left;padding:10px;margin-top:5px; background:#fff;}
fieldset legend {color:#1E7ACE;font-weight:bold;padding:3px 20px 3px 20px;border:1px solid #E03425;background:#fff;}
fieldset label {float:left;width:50px;text-align:right;padding:4px;margin:1px;}
fieldset div {clear:left;margin-top:15px;margin-bottom:1px;}
fieldset .buttom {width:44px; padding:1px 10px; margin-left:10px;font-size:12px;border:1px #1E7ACE solid;background:#D0F0FF;}
#login_bg_div {display: none; position: absolute; top: 0%; left: 0%; width: 100%; height: 100%; background-color: black; z-index:10000; -moz-opacity: 0.7; opacity:.70; filter: alpha(opacity=60);}

.login_div2{position: absolute;	border: solid 1px #d1e3f5;top:10%;text-align: center;border:1px solid #E03425;background: #f5f4f4;left:30%;width:200px;padding-bottom: 0px;padding-top: 0px;display: none;z-index:10002;} 
fieldset {text-align:left;padding:10px;margin-top:5px; background:#fff;}
fieldset legend {color:#1E7ACE;font-weight:bold;padding:3px 20px 3px 20px;border:1px solid #E03425;background:#fff;}
fieldset label {float:left;width:50px;text-align:right;padding:4px;margin:1px;}
fieldset div {clear:left;margin-top:15px;margin-bottom:1px;}
fieldset .buttom2 {width:44px; padding:1px 10px; margin-left:10px;font-size:12px;border:1px #1E7ACE solid;background:#D0F0FF;}
#login_bg_div2 {display: none; position: absolute; top: 0%; left: 0%; width: 100%; height: 100%; background-color: black; z-index:10000; -moz-opacity: 0.7; opacity:.70; filter: alpha(opacity=60);}

</style>
</head>
<body style="font-family:Microsoft Yahei;">   
<div class="oarcont" id="body_oarcont">
  	<div class="login_div" id="login_div" style="display:none;z-index:10000;position:absolute;">
	<form action="" method="post" name="apLogin" id="apLogin">
	<fieldset>		
		<h3><span id="x_close" title="关闭" style="float:right;font-size:18px;color:red;cursor:pointer;">×</span></h3> 
		<font id="login_msg" color="#E03425"></font>
		<div><label for="price_stock" id="x1"></label><input type="text" name="price" id="price"  size="10" maxlength="10" /><font color="red" id="u_msg"></font><br/></div>
		<div class="cookiechk">
			<input name="login791" id="sbt_login" type="button" class="buttom"  value="提交" style="margin-left:40px;" /><input name="cancel" type="button" id="login_close" class="buttom" value="取消" />
		</div> 
	</fieldset>
	</form>
	</div>
	<div class="login_div2" id="login_div2" style="display:none;z-index:10000;position:absolute;">
	<form action="" method="post" name="apLogin2" id="apLogin2">
	<fieldset>		
		<h3><span id="x_close2" title="关闭" style="float:right;font-size:18px;color:red;cursor:pointer;">×</span></h3> 
		<font id="login_msg2" color="#E03425"></font>
		<div><label for="price_stock" id="x2"></label><input type="text" name="stock" id="stock"  size="10" maxlength="10" /><font color="red" id="u_msg2"></font><br/></div>
		<div class="cookiechk">
			<input name="login791" id="sbt_login2" type="button" class="buttom2"  value="提交" style="margin-left:40px;" /><input name="cancel2" type="button" id="login_close2" class="buttom" value="取消" />
		</div> 
	</fieldset>
	</form>
	</div>
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/spgl/PdShowNew">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td height="36" align="left" style="padding-left:5px;">&nbsp;商品型号：
            <html-el:text property="pd_sn_like" styleId="pd_sn_like" maxlength="30" onkeyup="upperCase(this.id)" styleClass="webinput" />
            &nbsp;商品编码：
            <html-el:text property="d_like" styleId="d_like" maxlength="30"  styleClass="webinput" />
            &nbsp;&nbsp;商品名称：
            <html-el:text property="pd_name_like" styleId="pd_name_like"  maxlength="30" styleClass="webinput" />
            &nbsp;&nbsp;分类标签：
            <html-el:select property="label_of_cate" styleId="label_of_cate">
            	<html-el:option value="">请选择--</html-el:option>
            	<html-el:option value="0">新品</html-el:option>
            	<html-el:option value="2">热销</html-el:option>
            	<html-el:option value="3">特惠</html-el:option>
            	<html-el:option value="4">工程机</html-el:option>
            	<html-el:option value="5">限时抢购</html-el:option>
            	<html-el:option value="6">团购</html-el:option>
            	<html-el:option value="7">精品</html-el:option>
            	<html-el:option value="8">每周一劲爆</html-el:option>
            	<html-el:option value="9">样机专区</html-el:option>
            </html-el:select>&nbsp;&nbsp;&nbsp;
           </td>
         </tr>
         <tr>  
           <td height="36" align="left" style="padding-left:5px;"> 
           	   &nbsp;产品分类：<html-el:select property="prod_type" styleId="prod_type"  >  
            	<html-el:option value="">请选择--</html-el:option>
            	<html-el:option value="0">彩电</html-el:option>
            	<html-el:option value="3">小家电</html-el:option>
            	<html-el:option value="4">冰箱</html-el:option>
            	<html-el:option value="5">洗衣机</html-el:option>
            	<html-el:option value="6">空调</html-el:option>
            	<html-el:option value="7">手机</html-el:option>
            	<html-el:option value="8">橙子</html-el:option>
            	<html-el:option value="9">戴尔</html-el:option>
            	<html-el:option value="10">配件</html-el:option>
            	<html-el:option value="20">礼品</html-el:option>
            	<html-el:option value="21">食品饮料</html-el:option>
            </html-el:select>&nbsp;&nbsp;&nbsp;	
            	是否上架： <html-el:select property="is_sj" styleId="is_sj" onchange="this.form.submit();">
            	<html-el:option value="">请选择--</html-el:option>
            	<html-el:option value="1">已上架</html-el:option>
            	<html-el:option value="2">已下架</html-el:option>
            </html-el:select>&nbsp;&nbsp;&nbsp;
            是否推荐： <html-el:select property="is_tj" styleId="is_tj" onchange="this.form.submit();">
            	<html-el:option value="">请选择--</html-el:option>
            	<html-el:option value="0">不推荐</html-el:option>
            	<html-el:option value="1">推荐</html-el:option>
            </html-el:select>&nbsp;&nbsp;&nbsp;       
           是否停用： <html-el:select property="state" styleId="state" onchange="this.form.submit();">
            	<html-el:option value="1">正常</html-el:option>
            	<html-el:option value="0">已停用</html-el:option>
            </html-el:select>&nbsp;&nbsp;&nbsp;   
          </td>
        </tr>
        <tr>
        <td height="36" align="left" style="padding-left:5px;">
         &nbsp;是否锁定： <html-el:select property="lock_state" styleId="lock_state" onchange="this.form.submit();">
            	<html-el:option value="">请选择--</html-el:option>
            	<html-el:option value="1">已锁定</html-el:option>
            	<html-el:option value="0">未锁定</html-el:option>
            </html-el:select>
            &nbsp;&nbsp;&nbsp;
          商品所属总部/分公司： <html-el:select property="is_zb" styleId="is_zb">
            	<html-el:option value="">请选择--</html-el:option>
            	<html-el:option value="1">总部产品</html-el:option>
            	<html-el:option value="2">分公司产品</html-el:option>
            </html-el:select>&nbsp;&nbsp;&nbsp;
     	所属组织：<html-el:select property="dept_sn" styleId="dept_sn" style="width:120px;">
	          				<html-el:option value="">请选择</html-el:option>
	          				<c:forEach items="${deptList}" var="cur">
	          					<html-el:option value="${cur.id}">${cur.group_name}</html-el:option>
	          				</c:forEach>
	          			</html-el:select> &nbsp;&nbsp;&nbsp;      
          <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" />
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
         <c:if test="${is_admin eq 1 or (is_fgs eq 1 and not empty af.map.is_binding)}">   
       <!-- <input type="button" class="but2" value="新 增" onclick="location.href='PdShowNew.do?method=add&prod_type=${af.map.prod_type}&mod_id=${af.map.mod_id}';" />   --> 
        <input type="button" class="but2" value="新增" onclick="location.href='PdShowNew.do?method=addNew&mod_id=${af.map.mod_id}';" />
       	<input type="button" class="but2" value="复制 " onclick="location.href='PdShowNew.do?method=addBatch&mod_id=${af.map.mod_id}';" />
        <input class="but2" type="submit"  value="上下架" onclick="batchMoidfy(document.getElementById('listForm'));" />
        </c:if>
        </td> 
      </tr>
    </table>
  </div>
  <div class="rtabcont1" style="overflow: auto;">         
    <c:set var="now">
      <fmt:formatDate value="${today}" pattern="yyyy-MM-dd HH:mm:ss" /> 
    </c:set>
    <form id="listForm" name="listForm" method="post" action="PdShowNew.do?method=batchModify">
      <input type="hidden" name="method" id="method" value="batchModify" /> 
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />   
    <div style="margin-left:8px;"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /><span style="color: red;font-size: 14px;">全选</span> </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <c:forEach items="${entityList}" var="cur" varStatus="vs">
       	<tr style="background-color:green;">  
       	  <td align="center" width="20px;" rowspan="5" style="background-color: white;"> 
       	  <c:choose>
       	  <c:when test="${is_admin eq 1 or (is_fgs eq 1 and cur.lock_state ne 1 and (fn:contains(af.map.group_id,cur.dept_sn) and cur.dept_sn ne 0 ))}">   
       	  <input name="pks" type="checkbox" id="pks_${cur.id}" value="${cur.id}" />
       	  </c:when> 
       	  <c:otherwise> <input name="pks" type="checkbox" id="pks_${cur.id}"  disabled="disabled" /></c:otherwise>  
       	  </c:choose>  
       	  </td>  
          <td align="center" colspan="4" width="315px;" nowrap="nowrap">商品信息</td>
          <td align="center" width="110px;" nowrap="nowrap">商品型号</td>   
          <td align="center" width="50px;" nowrap="nowrap">市场价</td> 
          <td align="center" width="50px;" nowrap="nowrap">会员价</td>   
          <td align="center" width="50px;" nowrap="nowrap">库存</td>   
          <td align="center" width="70px;" nowrap="nowrap">30天销量</td> 
          <td align="center" width="95px" nowrap="nowrap">上架时间</td>   
          <td align="center" width="95px" nowrap="nowrap">下架时间</td>    
          <td align="center" width="70px;" nowrap="nowrap">商品状态</td>  
          <td align="center" width="120px;" nowrap="nowrap">操作</td>  
        </tr>
       	<tr>
          <td align="left" style="width:60px;">商品名称</td> 
          <td align="center" colspan="3">${cur.pd_name}</td>
          <td align="left" style="width:60px;">产品分类</td> 
          <td align="left" colspan="8">    
          <c:if test="${cur.label_of_cate eq 0}">新品</c:if>
          <c:if test="${cur.label_of_cate eq 2}">热销</c:if>
          <c:if test="${cur.label_of_cate eq 3}">特惠</c:if>
          <c:if test="${cur.label_of_cate eq 4}">工程机</c:if>
          <c:if test="${cur.label_of_cate eq 5}">限时抢购</c:if>
          <c:if test="${cur.label_of_cate eq 6}">团购</c:if>
          <c:if test="${cur.label_of_cate eq 7}">精品</c:if>  
           <c:if test="${cur.label_of_cate eq 8}">每周一劲爆</c:if>
          <c:if test="${cur.label_of_cate eq 9}">样机专区</c:if>
          </td>  
        </tr>
        <tr> 
          <td align="left" style="width:60px;" nowrap="nowrap">商品编码</td>
          <td align="left" style="width:60px;">${cur.id}</td>  
          <td align="center" nowrap="nowrap" style="width:60px;">产品品类</td>   
          <td align="center" style="width:90px;"> 
            <c:if test="${cur.prod_type eq 0}">彩电</c:if>
            <c:if test="${cur.prod_type eq 1}">白电</c:if>
            <c:if test="${cur.prod_type eq 3}">小家电</c:if>
            <c:if test="${cur.prod_type eq 4}">冰箱</c:if>
            <c:if test="${cur.prod_type eq 5}">洗衣机</c:if>
            <c:if test="${cur.prod_type eq 6}">空调</c:if>
            <c:if test="${cur.prod_type eq 7}">手机</c:if>
            <c:if test="${cur.prod_type eq 8}">橙子</c:if>
             <c:if test="${cur.prod_type eq 9}">戴尔</c:if>
            <c:if test="${cur.prod_type eq 10}">配件</c:if>
            <c:if test="${cur.prod_type eq 20}">礼品</c:if>
            <c:if test="${cur.prod_type eq 21}">食品饮料</c:if>  
          </td>     
            <td  rowspan="3" width="110px;">${cur.pd_sn}</td>   
		    <td  rowspan="3" align="center">
		    <c:if test="${cur.dept_sn eq 0}">
		    <c:if test="${empty cur.map.zb_o_price}">未维护</c:if>
		    <c:if test="${not empty cur.map.zb_o_price}">${cur.map.zb_o_price}</c:if>
		    </c:if>
		    <c:if test="${cur.dept_sn ne 0}">
		    <c:if test="${empty cur.map.fgs_o_price}">未维护</c:if>
		    <c:if test="${not empty cur.map.fgs_o_price}"> ${cur.map.fgs_o_price}</c:if>
		    </c:if> 
		    </td>
		    <td  rowspan="3" align="center">
		     <c:if test="${cur.dept_sn eq 0}">
		    <c:if test="${empty cur.map.zb_price}">未维护</c:if>
		    <c:if test="${not empty cur.map.zb_price}">
		    <c:choose>
		    	<c:when test="${is_admin eq 1 or (is_fgs eq 1 and (fn:contains(af.map.group_id,cur.dept_sn) and cur.dept_sn ne 0 ) and cur.lock_state ne 1) }">  
		    	<a style="color: blue;" class="pp" id="price_${cur.id}" >${cur.map.zb_price}</a>  
		    	</c:when>
		    	<c:otherwise>${cur.map.zb_price}</c:otherwise>
		    </c:choose>
		    </c:if>
		    </c:if>
		    <c:if test="${cur.dept_sn ne 0}">
		    <c:if test="${empty cur.map.fgs_price}">未维护</c:if>
		    <c:if test="${not empty cur.map.fgs_price}"> 
		    <c:choose>
		    	<c:when test="${is_admin eq 1 or (is_fgs eq 1 and (fn:contains(af.map.group_id,cur.dept_sn) and cur.dept_sn ne 0 ) and cur.lock_state ne 1) }">
		    	<a style="color: blue;" class="pp" id="price_${cur.id}" >${cur.map.fgs_price}</a> 
		    	</c:when>
		    	<c:otherwise>${cur.map.fgs_price}</c:otherwise>
		    </c:choose> 
		     </c:if>
		    </c:if>
		    
		    </td>
		    <td  rowspan="3" align="center">
		    <c:if test="${cur.dept_sn eq 0}">
		    <c:if test="${empty cur.map.zb_stocks}">未维护</c:if>
		    <c:if test="${not empty cur.map.zb_stocks}">
		    <c:choose>
		    	<c:when test="${is_admin eq 1 or (is_fgs eq 1 and (fn:contains(af.map.group_id,cur.dept_sn) and cur.dept_sn ne 0 ) and cur.lock_state ne 1) }">
		    	<a style="color: blue;" id="stock_${cur.id}" onclick="modify2('${cur.id}')">${cur.map.zb_stocks}</a> 
		    	</c:when>
		    	<c:otherwise>${cur.map.zb_stocks}</c:otherwise>
		    </c:choose> 
		    </c:if>
		    </c:if>
		     <c:if test="${cur.dept_sn ne 0}">
		    <c:if test="${empty cur.map.fgs_stocks}">未维护</c:if>
		    <c:if test="${not empty cur.map.fgs_stocks}">
		    <c:choose>
		    	<c:when test="${is_admin eq 1 or (is_fgs eq 1 and (fn:contains(af.map.group_id,cur.dept_sn) and cur.dept_sn ne 0 ) and cur.lock_state ne 1) }">
		    	<a style="color: blue;" id="stock_${cur.id}" onclick="modify2('${cur.id}')">${cur.map.fgs_stocks}</a> 
		    	</c:when>
		    	<c:otherwise>${cur.map.fgs_stocks}</c:otherwise>
		    </c:choose> 
		    </c:if>
		    </c:if>  
		    </td>
		    <td  rowspan="3" align="center">${cur.map.sales_num}</td> 
		    <td  rowspan="3" width="127px;"> 
		    <c:if test="${cur.dept_sn eq 0}">
		    <c:if test="${empty cur.map.zb_up_time}">未维护</c:if>
		    <c:if test="${not empty cur.map.zb_up_time}">
		    <c:set var="zb_up_time"><fmt:formatDate value="${cur.map.zb_up_time}" pattern="yyyy-MM-dd HH:mm:ss"/> </c:set>
		    <fmt:formatDate value="${cur.map.zb_up_time}" pattern="yyyy-MM-dd HH:mm:ss"/>
		    </c:if>
		    </c:if>
		     <c:if test="${cur.dept_sn ne 0}">
		    <c:if test="${empty cur.map.fgs_up_time}">未维护</c:if>
		    <c:if test="${not empty cur.map.fgs_up_time}">
		    <c:set var="fgs_up_time"><fmt:formatDate value="${cur.map.fgs_up_time}" pattern="yyyy-MM-dd HH:mm:ss"/></c:set>
		    <fmt:formatDate value="${cur.map.fgs_up_time}" pattern="yyyy-MM-dd HH:mm:ss"/>
		    </c:if>
		    </c:if>
		    </td>
		    <td  rowspan="3" width="95px"> 
		    <c:if test="${cur.dept_sn eq 0}">
		    <c:if test="${empty cur.map.zb_down_time}">未维护</c:if>
		    <c:if test="${not empty cur.map.zb_down_time}">
		    <c:set var="zb_down_time"> <fmt:formatDate value="${cur.map.zb_down_time}" pattern="yyyy-MM-dd HH:mm:ss"/></c:set>
		    <fmt:formatDate value="${cur.map.zb_down_time}" pattern="yyyy-MM-dd HH:mm:ss"/>
		    </c:if>
		    </c:if>
		     <c:if test="${cur.dept_sn ne 0}">
		    <c:if test="${empty cur.map.fgs_down_time}">未维护</c:if>
		    <c:if test="${not empty cur.map.fgs_down_time}">
		    <c:set var="fgs_down_time"> <fmt:formatDate value="${cur.map.fgs_down_time}" pattern="yyyy-MM-dd HH:mm:ss"/></c:set>
		    <fmt:formatDate value="${cur.map.fgs_down_time}" pattern="yyyy-MM-dd HH:mm:ss"/>
		    </c:if>
		    </c:if> 
		    </td>
		    <td  rowspan="3" width="95px">  
		     <c:if test="${cur.dept_sn eq 0}">
		     	<c:choose>
		     		<c:when test="${empty cur.map.zb_up_time}"><span style="color: red;">已下架</span></c:when>
		     		<c:when test="${zb_up_time le now and zb_down_time ge now }"><span style="color: green;">上架中</span></c:when>
		     		<c:otherwise><span style="color: red;">已下架</span></c:otherwise>
		     	</c:choose>
		     </c:if>
		      <c:if test="${cur.dept_sn ne 0}">   
		     	<c:choose>
		     		<c:when test="${empty cur.map.fgs_up_time}"><span style="color: red;">已下架</span></c:when>
		     		<c:when test="${fgs_up_time le now and fgs_down_time ge now }"><span style="color: green;">上架中</span></c:when>
		     		<c:otherwise><span style="color: red;">已下架</span></c:otherwise>
		     	</c:choose>
		     </c:if>  
		    </td>      
		    <td  rowspan="3"><a style="color: blue;" href="javascript:loading();doNeedMethod(null, 'PdShowNew.do', 'view', 'id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">查看</a>&nbsp;&nbsp;
		 <c:if test="${is_admin eq 1}">
       	 <a style="color: blue" href="javascript:loading();doNeedMethod(null, 'PdShowNew.do', 'edit', 'id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">编辑</a> 
       	 </c:if>
       	 <c:if test="${is_fgs eq 1 and (fn:contains(af.map.group_id,cur.dept_sn) and cur.dept_sn ne 0 ) and cur.lock_state ne 1}">
       	  <a style="color: blue" href="javascript:loading();doNeedMethod(null, 'PdShowNew.do', 'edit', 'id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">编辑</a>
       	 </c:if>
       	  <c:if test="${is_fgs eq 1 and (cur.dept_sn eq 0 or cur.lock_state eq 1)}">  
       	  <a style="color: #ccc" >编辑</a>       
       	 </c:if>&nbsp;&nbsp;
       	 <c:if test="${is_admin eq 1 and cur.state eq 1}"> 
         <a style="color: blue" href="javascript:loading();doNeedMethod(null, 'PdShowNew.do', 'delete', 'id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">停用</a>     
         </c:if>
         <c:if test="${is_fgs eq 1 and (fn:contains(af.map.group_id,cur.dept_sn) and cur.dept_sn ne 0 ) and cur.lock_state ne 1 and cur.state eq 1}">  
       	  <a style="color: blue" href="javascript:loading();doNeedMethod(null, 'PdShowNew.do', 'delete', 'id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">停用</a>
       	 </c:if>
       	  <c:if test="${is_fgs eq 1 and (cur.dept_sn eq 0 or cur.lock_state eq 1) and cur.state eq 1}"> 
       	  <a style="color: #ccc" >停用</a>       
       	 </c:if>
       	  <c:if test="${is_fgs eq 1 and (cur.dept_sn eq 0 or cur.lock_state eq 1) and cur.state eq 0}"> 
       	  <a style="color: #ccc" >启用</a>       
       	 </c:if>  
       	 <c:if test="${is_admin eq 1 and cur.state eq 0}">  
         <a style="color: blue" href="javascript:loading();doNeedMethod(null, 'PdShowNew.do', 'restart', 'id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">启用</a>     
         </c:if>
       	  <c:if test="${is_fgs eq 1 and (fn:contains(af.map.group_id,cur.dept_sn) and cur.dept_sn ne 0 ) and cur.lock_state ne 1 and cur.state eq 0}">  
       	  <a style="color: blue" href="javascript:loading();doNeedMethod(null, 'PdShowNew.do', 'restart', 'id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">启用</a>
       	 </c:if>
       	 </td>
        </tr>
         <tr>  
          <td align="center" width="20%" style="height:104px;" colspan="3" rowspan="2" style="background:#FFFFFF;"><c:if test="${not empty cur.main_pic}">
              <c:set value="${fn:split(cur.main_pic, ',')[0]}" var="main_pic_path" />
              <img src="${ctx}/${fn:substringBefore(main_pic_path, '.')}_120.jpg" /> </c:if>     
          </td>  
          <td align="center"><c:if test="${cur.dept_sn eq 0}">总部产品</c:if>  
          <c:if test="${cur.dept_sn ne 0}">${cur.map.group_name}分公司产品</c:if></td>
        </tr>
        <tr>
          <td align="center"><c:if test="${cur.lock_state eq 0}">未锁定</c:if>  
          <c:if test="${cur.lock_state eq 1}">已锁定</c:if></td>  
          <!--  <td align="left">销售区域维护</td> -->
        </tr>
        <tr> 
          <td colspan="14" style="background:#f5f4f4">  
          <div style="margin:3px auto;">
        <div style="float:left;">
          <div style="float:left;"> 
            <a style="color: blue" class="v_price" id="v_price_${vs.count}" href="${ctx}/manager/spgl/EcGoodsPriceNew.do?method=list2&goods_id=${cur.id}"><span class="spancss"  >价格设置</span></a>&nbsp;&nbsp;
            <a style="color: blue" class="v_stock" href="${ctx}/manager/spgl/EcStocksNew.do?method=list2&goods_id=${cur.id}"><span class="spancss"  >库存维护</span></a>&nbsp;&nbsp;
            <a style="color: blue" class="v_integral" href="${ctx}/manager/spgl/EcGoodsIntegralNew.do?method=list2&goods_id=${cur.id}"><span class="spancss"  >积分设置</span></a>&nbsp;&nbsp;
            <a style="color: blue" class="v_rebate" href="${ctx}/manager/spgl/EcGoodsRebateNew.do?method=list2&goods_id=${cur.id}"><span class="spancss"  >佣金设置</span></a>&nbsp;&nbsp;
            <a style="color: blue" class="v_rule" href="${ctx}/manager/spgl/PdShowNew.do?method=ruleShow&id=${cur.id}"><span class="spancss"  >促销规则</span></a>&nbsp;&nbsp;  
            <a style="color: blue" class="v_upself" href="${ctx}/manager/spgl/EcBcompPdUpNew.do?method=list2&goods_id=${cur.id}"><span class="spancss"  >上架下架</span></a>&nbsp;&nbsp;
           	<a style="color: blue" href="javascript:loading();doNeedMethod(null, 'PdShowNew.do', 'editForTj', 'id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())"><span class="spancss" ><c:if test="${cur.is_tj eq 0 or empty cur.is_tj}">推荐</c:if><c:if test="${cur.is_tj eq 1}">不推荐</c:if></span></a>&nbsp;&nbsp;
            <c:if test="${is_admin eq 1}">  
           	<a style="color: blue" href="javascript:loading();doNeedMethod(null, 'PdShowNew.do', 'editForSd', 'id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())"><span class="spancss" ><c:if test="${cur.lock_state eq 0 or empty cur.lock_state}">锁定</c:if><c:if test="${cur.lock_state eq 1}">不锁定</c:if></span></a>&nbsp;&nbsp;       
           	</c:if>
          </div>
        </div>
      </div>
          </td>
      </tr>
    </c:forEach>
      </table>
      <c:if test="${not vs.last}">
        <div style="height:10px;"></div>    
      </c:if>
    </form>
  </div>  
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="PdShowNew.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "list");
								pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
								pager.addHiddenInputs("is_zb", "${af.map.is_zb}");
								pager.addHiddenInputs("pd_sn_like", "${af.map.pd_sn_like}");
								pager.addHiddenInputs("d_like", "${af.map.d_like}");
								pager.addHiddenInputs("pd_name_like", "${af.map.pd_name_like}");
								pager.addHiddenInputs("label_of_cate", "${af.map.label_of_cate}");
								pager.addHiddenInputs("is_sj", "${af.map.is_sj}");
								pager.addHiddenInputs("is_tj", "${af.map.is_tj}");
								pager.addHiddenInputs("lock_state", "${af.map.lock_state}"); 
								pager.addHiddenInputs("prod_type", "${af.map.prod_type}");
								pager.addHiddenInputs("dept_sn", "${af.map.dept_sn}");
								pager.addHiddenInputs("state", "${af.map.state}"); 
								document.write(pager.toString());
							</script>
            </div></td>
        </tr>
      </table>
    </form>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jgrowl/jgrowl.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/asyncbox/asyncbox.js"></script> 
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/colorbox/jquery.colorbox.min.js"></script>  
<script type="text/javascript">//<![CDATA[

var i=0;
$(document).ready(function(){ 

	<c:forEach items="${entityList}" var="cur" varStatus="vs">
		var id = "${cur.id}";
		var label_of_cate = "${cur.label_of_cate}";
		showCkboxEcho("label_of_cate_" + id, label_of_cate);
	</c:forEach>

	$(".pp").click(function(){
		var goods_id=$(this).attr("id");
		goods_id=goods_id.split("_")[1];
		var price=$("#price_"+goods_id).html();
		$("#x1").html("价格");
		$("#price").val(''); 
		$("#price").val(price);
		$("#login_div").fadeIn(500);   
		$("#login_close").click(function(){$("#login_div").fadeOut(500);});  
		$("#x_close").click(function(){$("#login_div").fadeOut(500);});

		var _top=getMousePosY();
		document.getElementById("login_div").style.top=_top-55+"px";
		$("#login_div").css({"left":"28%"}); 

		if(i==0){  
			i++;
			$("#sbt_login").click(function(){ 
				var price_stock=$('#price').val();  
				$('#u_msg').html('');
				var ex1 = /^\d+$/;
				var ex2 = /^\d+\.\d{2}$/;
				var ex3 = /^\d+\.\d{1}$/;
				var objExp1 = new RegExp(ex1);
				var objExp2 = new RegExp(ex2);
				var objExp3 = new RegExp(ex3);
				if(objExp1.test(price_stock)||objExp2.test(price_stock)||objExp3.test(price_stock)){
				}else{
					alert("价格必须为正整数或保留两位有效数字!");
					return;
				}
				if(parseFloat(price_stock)<=0) 
				{
					alert("价格必须为正整数或保留两位有效数字!");
					return;
				}
				if(price_stock!=null){price_stock=price_stock.replace(/(^\s*)|(\s*$)/g, "");}
				if(price_stock==''){$('#u_msg').html('请输入数字');return false;} 
				this.disabled=true;
				this.value="正在验证。。。";
				$.ajax({
					type: "POST",
					url:"${ctx}/manager/spgl/PdShowNew.do",
					data: {"method":"ajaxModifyPrice","goods_id":goods_id,"price_stock":price_stock,"timestamp":new Date().getTime() },
					dataType: "json",sync: false,
					error: function (xhr, ajaxOptions, thrownError) {alert('网络异常，请稍后再试。。。');document.getElementById("sbt_login").value="提交";document.getElementById("sbt_login").disabled=false; 
					},success: function(result){
						if(result.status=='1'){ 
							$("#login_div").hide();
							$("#price").val('');  
							$("#price_"+result.goods_id).html(result.price);
							document.getElementById("sbt_login").value="提交";
							document.getElementById("sbt_login").disabled=false;    
						}else{ 
							alert("网络异常，请稍后再试。。。");
							document.getElementById("sbt_login").value="提交";document.getElementById("sbt_login").disabled=false; 
						}
					}
				});
			});		
		}
		

	});

	  
	$(".v_price").fancybox({  
	    'overlayOpacity':'0.6', 
		'width':640,     
		'height':480,       
		'autoScale':false, 
		'hideOnOverlayClick':true,  
		'showCloseButton':true,   
		'transitionIn':'none',
		'transitionOut':'none',
		'type':'iframe',
		'onComplete': function() { 
		      var _top=getMousePosY();
			  _top=_top-450;       
		      $("#fancybox-wrap").css({'top':_top}); 
		   },
		 'onClosed':function(){  
				// window.location.href=window.location.href;
			 document.getElementById('button').form.submit();   
		}   
	});


	$(".v_stock").fancybox({   
	    'overlayOpacity':'0.6', 
		'width':640,     
		'height':480,       
		'autoScale':false, 
		'hideOnOverlayClick':true,     
		'transitionIn':'none',
		'transitionOut':'none',
		'type':'iframe',
		'onComplete': function() {
			  var _top=getMousePosY();
			  _top=_top-450;   
		      $("#fancybox-wrap").css({'top':_top}); 
		   },
		 'onClosed':function(){  
				// window.location.href=window.location.href;
			 document.getElementById('button').form.submit(); 
		}   
	});

	$(".v_rule").fancybox({   
	    'overlayOpacity':'0.6', 
		'width':640,     
		'height':480,       
		'autoScale':false, 
		'hideOnOverlayClick':true,     
		'transitionIn':'none',
		'transitionOut':'none',
		'type':'iframe',
		'onComplete': function() {
			  var _top=getMousePosY();
			  _top=_top-450;   
		      $("#fancybox-wrap").css({'top':_top}); 
		   },
		 'onClosed':function(){  
			// window.location.href=window.location.href;
				 document.getElementById('button').form.submit(); 
		}   
	}); 

	$(".v_upself").fancybox({     
	    'overlayOpacity':'0.6', 
		'width':640,     
		'height':480,       
		'autoScale':false, 
		'hideOnOverlayClick':true,     
		'transitionIn':'none',
		'transitionOut':'none',
		'type':'iframe',
		'onComplete': function() {
			  var _top=getMousePosY();
			  _top=_top-450;   
		      $("#fancybox-wrap").css({'top':_top}); 
		   },
		 'onClosed':function(){  
			// window.location.href=window.location.href;
				 document.getElementById('button').form.submit(); 
		}   
	});


	$(".v_integral").fancybox({     
	    'overlayOpacity':'0.6', 
		'width':640,     
		'height':480,       
		'autoScale':false, 
		'hideOnOverlayClick':true,     
		'transitionIn':'none',
		'transitionOut':'none',
		'type':'iframe',
		'onComplete': function() {
			  var _top=getMousePosY();
			  _top=_top-450;   
		      $("#fancybox-wrap").css({'top':_top}); 
		   },
		 'onClosed':function(){  
			// window.location.href=window.location.href;
				 document.getElementById('button').form.submit(); 
		}   
	});

	$(".v_rebate").fancybox({     
	    'overlayOpacity':'0.6', 
		'width':640,     
		'height':480,       
		'autoScale':false, 
		'hideOnOverlayClick':true,     
		'transitionIn':'none',
		'transitionOut':'none',
		'type':'iframe',
		'onComplete': function() {
			  var _top=getMousePosY();
			  _top=_top-450;   
		      $("#fancybox-wrap").css({'top':_top}); 
		   },
		 'onClosed':function(){  
			// window.location.href=window.location.href;
				 document.getElementById('button').form.submit(); 
		}   
	}); 
	

});


function batchMoidfy(form) {
	var checkedCount = 0;
	if (!form.pks) {
		return;
	}
	if(!form.pks.length) {
		if (form.pks.checked == true) {
			checkedCount = 1;
		}
	}
	for (var i = 0; i < form.pks.length; i++) {
		if (form.pks[i].checked == true) {
			checkedCount++;
		}
	}
	if (checkedCount == 0) {
		alert("\u81f3\u5c11\u9009\u62e9\u4e00\u4e2a\u9009\u9879\uff01");
	} else {  
		if(confirm("\u60a8\u786e\u8ba4\u63d0\u4ea4\u8fd9\u4e9b\u6570\u636e\u5417\uff01")) {
			form.action="${ctx}/manager/spgl/PdShowNew.do?method=batchMoidfy&mod_id=${af.map.mod_id}";  
			form.submit();
		}
	}
}



function showCkboxEcho(name, value){
	var obj = document.getElementsByName(name);
	for ( var i = 0; i < obj.length; i++) {
		if (value == obj[i].value) {
			obj[i].checked = true;
			//obj[i].nextSibling.getElementsByTagName("span")[0].className = "ck-visited";
			var parId = obj[i].parentNode.id;
			$("span", "#" + parId).addClass("ck-visited");
		}
	}
}


function modify2(goods_id){ 
	var stocks =$("#stock_"+goods_id).html();  
	$("#stock").val('');   
	$("#x2").html("库存");
	$("#stock").val(stocks);
	$("#login_div2").fadeIn(500);   
	$("#login_close2").click(function(){$("#login_div2").fadeOut(500);});  
	$("#x_close2").click(function(){$("#login_div2").fadeOut(500);});

	var _top=getMousePosY();
	document.getElementById("login_div2").style.top=_top-55+"px"; 

	$("#login_div2").css({"left":"57%"});     

	$("#sbt_login2").unbind("click").click(function(){ 
		var price_stock=$('#stock').val();
		$('#u_msg2').html('');
		if(!price_stock.match("^[0-9]*$"))
		{
			alert("请输入正确的库存数量!");
			return;
		}
		if(price_stock!=null){price_stock=price_stock.replace(/(^\s*)|(\s*$)/g, "");}
		if(price_stock==''){$('#u_msg2').html('请输入数字');return false;} 
		this.disabled=true;
		this.value="正在验证。。。";
		$.ajax({
			type: "POST",
			url:"${ctx}/manager/spgl/PdShowNew.do",
			data: {"method":"ajaxModifyStock","goods_id":goods_id,"price_stock":price_stock,"timestamp":new Date().getTime() },
			dataType: "json",sync: false,
			error: function (xhr, ajaxOptions, thrownError) {
				alert('网络异常，请稍后再试。。。');
			document.getElementById("sbt_login2").value="提交";
			document.getElementById("sbt_login2").disabled=false; 
			},success: function(result){
				if(result.status=='1'){ 
					$("#login_div2").hide();
					$("#stock").val(''); 
					$("#stock_"+result.goods_id).html(result.stock); 
					document.getElementById("sbt_login2").value="提交";
					document.getElementById("sbt_login2").disabled=false;    
				}else{ 
					alert("网络异常，请稍后再试。。。");
					document.getElementById("sbt_login2").value="提交";
					document.getElementById("sbt_login2").disabled=false; 
				}
			}
		});
	});	

	 
}


function loading(){
	jLoading("&nbsp;&nbsp;正在加载数据...", {autoHide:false, HorizontalPosition:"center", VerticalPosition:"center", MinWidth:150});
}
function getMousePosY(event) {              
	 var e = event || window.event;              
	 return e.clientY;
} 
function upperCase(x)
{
var y=document.getElementById(x).value;
document.getElementById(x).value=y.toUpperCase();
}
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
