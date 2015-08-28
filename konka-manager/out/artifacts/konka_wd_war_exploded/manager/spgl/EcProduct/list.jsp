<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align: middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/spgl/EcProduct"> 
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td height="36" align="left" style="padding-left:5px;">&nbsp;产品型号：
            <html-el:text property="pd_sn_like" styleId="pd_sn_like" maxlength="30" onkeyup="upperCase(this.id)" styleClass="webinput" />
            &nbsp;产品名称：
            <html-el:text property="pd_name_like" styleId="pd_name_like"  maxlength="30" styleClass="webinput" />
            &nbsp;&nbsp;产品标签：
            <html-el:select property="label_of_cate" styleId="label_of_cate" onchange="this.form.submit();">
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
           	   &nbsp;产品分类：<html-el:select property="prod_type" styleId="prod_type" onchange="this.form.submit();" >  
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
           是否有效： <html-el:select property="state" styleId="state" onchange="this.form.submit();" >
            	<html-el:option value="1">有效</html-el:option>
            	<html-el:option value="0">无效</html-el:option>
            </html-el:select>&nbsp;&nbsp;&nbsp;     
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
		    <logic-el:match name="popedom" value="+1+">
		      <input class="but2" type="submit" name="Submit2" value="新增" onclick="location.href='EcProduct.do?method=add&mod_id=${af.map.mod_id}';" />
		    </logic-el:match>
		</td>
	</tr>
   </table>		
  </div>
  <div class="rtabcont1" style="overflow: auto;">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td  nowrap="nowrap" align="center">序号</td> 
          <td nowrap="nowrap" align="center">产品型号</td>
          <td nowrap="nowrap" align="center">产品名称</td>
          <td nowrap="nowrap" align="center">主图</td>
          <td nowrap="nowrap" align="center">品牌</td>
          <td nowrap="nowrap" align="center">产品分类</td>
          <td nowrap="nowrap" align="center">产品标签</td>
          <td nowrap="nowrap" align="center">产品规格</td>
          <td nowrap="nowrap" align="center">尺寸</td>
          <td nowrap="nowrap" align="center">是否有效</td>
          <td width="10%" nowrap="nowrap" align="center">创建日期</td>
          <td width="10%" nowrap="nowrap" align="center">操作</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs"> 
            <tr>
              <td align="center" nowrap="nowrap">${vs.count }</td>
              <td align="left" >${fn:escapeXml(cur.pd_sn)}</td>
              <td align="left" >${fn:escapeXml(cur.pd_name)}</td>
              <td align="center" width="150px;">
              <c:if test="${not empty cur.main_pic}">
              <c:set value="${fn:split(cur.main_pic, ',')[0]}" var="main_pic_path" />
              <img src="${ctx}/${fn:substringBefore(main_pic_path, '.')}_120.jpg" width="80px;" /> </c:if> 
              </td>
              <td align="left" >${fn:escapeXml(cur.brand_name)}</td>
              <td align="left" > <c:if test="${cur.prod_type eq 0}">彩电</c:if>
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
            <c:if test="${cur.prod_type eq 21}">食品饮料</c:if> </td>
              <td align="left" > <c:if test="${cur.label_of_cate eq 0}">新品</c:if>
          <c:if test="${cur.label_of_cate eq 2}">热销</c:if>
          <c:if test="${cur.label_of_cate eq 3}">特惠</c:if>
          <c:if test="${cur.label_of_cate eq 4}">工程机</c:if>
          <c:if test="${cur.label_of_cate eq 5}">限时抢购</c:if>
          <c:if test="${cur.label_of_cate eq 6}">团购</c:if>
          <c:if test="${cur.label_of_cate eq 7}">精品</c:if>  
           <c:if test="${cur.label_of_cate eq 8}">每周一劲爆</c:if>
          <c:if test="${cur.label_of_cate eq 9}">样机专区</c:if></td>
           <td align="center" > 
           <c:if test="${cur.label_3d eq 1}">&nbsp;3D</c:if>
           <c:if test="${cur.label_int eq 1}">&nbsp;智能</c:if>
           <c:if test="${cur.label_4k eq 1}">&nbsp;4K</c:if>
           </td>
           <td align="right" >${fn:escapeXml(cur.pd_size)}</td>
           <td align="left">
		     <c:if test="${cur.state eq 1}">有效</c:if>
		      <c:if test="${cur.state eq 0}">无效</c:if>
		      </td>
              <td align="left">
		         <fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" />
		      </td>
              <td align="center" nowrap="nowrap">
	              <logic-el:match name="popedom" value="+2+">
	                  <span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'EcProduct.do', 'view','id=${cur.id}&'+$('#bottomPageForm').serialize())">查看</span>
	                  <c:if test="${cur.state eq 1}">
	              	  <span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'EcProduct.do', 'edit','id=${cur.id}&'+$('#bottomPageForm').serialize())">修改</span>
	              	  </c:if>
	              	  <c:if test="${cur.state eq 0}">
	              	  <span style="#CCC">修改</span> 
	              	   </c:if>
	              	  <c:if test="${cur.state eq 1}">
	              	  	<span style="cursor: pointer;" class="fblue" onclick="doNeedMethod('是否无效！', 'EcProduct.do', 'delete','id=${cur.id}&'+$('#bottomPageForm').serialize())">无效</span>
	              	  </c:if>
	              	  <c:if test="${cur.state eq 0}">
	              	  	<span style="cursor: pointer;" class="fblue" onclick="doNeedMethod('是否有效！', 'EcProduct.do', 'restart','id=${cur.id}&'+$('#bottomPageForm').serialize())">有效</span>
	              	  </c:if>	
	              </logic-el:match>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="EcProduct.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;"> 
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("pd_sn_like", "${af.map.pd_sn_like}");
				pager.addHiddenInputs("pd_name_like", "${af.map.pd_name_like}");
				pager.addHiddenInputs("label_of_cate", "${af.map.label_of_cate}");
				pager.addHiddenInputs("prod_type", "${af.map.prod_type}");
				pager.addHiddenInputs("state", "${af.map.state}"); 
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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
});
function upperCase(x)
{
var y=document.getElementById(x).value;
document.getElementById(x).value=y.toUpperCase();
}

function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		//if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
