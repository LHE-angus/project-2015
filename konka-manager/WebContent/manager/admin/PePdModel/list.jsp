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
    <html-el:form action="/admin/PePdModel">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td>
          	
            
          	<strong class="fb">&nbsp;&nbsp;产品型号：</strong>
            <html-el:text property="md_name_like" styleId="md_name_like" maxlength="40" />
            &nbsp;&nbsp;
            <strong class="fb">参考价格：</strong>
            <html-el:text property="price_ref_ge" styleId="price_ref_ge" maxlength="12" size="12" />
            -
            <html-el:text property="price_ref_le" styleId="price_ref_le" maxlength="12" size="12" />
            &nbsp;&nbsp;
            </td>
        </tr>
        <tr>
        	<td>
             &nbsp;&nbsp;
            <strong class="fb">入库时间：</strong>
            <html-el:text property="add_date_s" styleId="add_date_s"  size="12" maxlength="10" readonly="true" onclick="new Calendar(2010, 2050, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />-
			<html-el:text property="add_date_e" styleId="add_date_e"  size="12" maxlength="10" readonly="true" onclick="new Calendar(2010, 2050, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
           	&nbsp;&nbsp;
           	<strong class="fb">品牌：</strong>
            <html-el:select property="brand_id" styleId="brand_id" >
            		<html-el:option value="">全部</html-el:option>
            		<html-el:option value="114">康佳</html-el:option>
            </html-el:select>
            <html-el:select property="sub_brand_id" styleId="sub_brand_id" >
            		<html-el:option value="">全部</html-el:option>
            		<html-el:option value="114">康佳</html-el:option>
            		<html-el:option value="115">现代</html-el:option>
            		<html-el:option value="116">KKTV</html-el:option>
            </html-el:select>
			&nbsp;&nbsp;
			<strong class="fb">是否智能：</strong>
            <html-el:select property="label_int" styleId="label_int" onchange="this.form.submit();">
            		<html-el:option value="">全部</html-el:option>
            		<html-el:option value="1">是</html-el:option>
            		<html-el:option value="0">否</html-el:option>
            	</html-el:select>
           	&nbsp;&nbsp;
           	<strong class="fb">是否3D：</strong>
           	<html-el:select property="label_3d" styleId="label_3d" onchange="this.form.submit();">
           		<html-el:option value="">全部</html-el:option>
           		<html-el:option value="1">是</html-el:option>
           		<html-el:option value="0">否</html-el:option>
           	</html-el:select>
            
			</td>
        </tr>
        <tr>
        <td> &nbsp;&nbsp;
            <strong class="fb">是否配件：</strong>
            <html-el:select property="is_parts" styleId="is_parts" onchange="this.form.submit();">
            		<html-el:option value="">全部</html-el:option>
            		<html-el:option value="1">是</html-el:option>
            		<html-el:option value="0">否</html-el:option>
            	</html-el:select>
            	 &nbsp;&nbsp;
            <strong class="fb">是否4K：</strong>
            <html-el:select property="is_4k" styleId="is_4k" onchange="this.form.submit();">
            		<html-el:option value="">全部</html-el:option>
            		<html-el:option value="1">是</html-el:option>
            		<html-el:option value="0">否</html-el:option>
            </html-el:select>
            &nbsp;&nbsp;
           <strong class="fb">是否网络：</strong>
           <html-el:select property="label_www" styleId="label_www" onchange="this.form.submit();">
            		<html-el:option value="">全部</html-el:option>
            		<html-el:option value="1">是</html-el:option>
            		<html-el:option value="0">否</html-el:option>
            	</html-el:select>
            &nbsp;&nbsp;
           <strong class="fb">是否易TV：</strong>
           <html-el:select property="is_ytv" styleId="is_ytv" onchange="this.form.submit();">
            		<html-el:option value="">全部</html-el:option>
            		<html-el:option value="1">是</html-el:option>
            		<html-el:option value="0">否</html-el:option>
            	</html-el:select>
            &nbsp;&nbsp;
            <strong class="fb">是否有效：</strong>
            <html-el:select property="is_del" styleId="is_del" onchange="this.form.submit();">
            		<html-el:option value="0">有效</html-el:option>
            		<html-el:option value="1">无效</html-el:option>
            	</html-el:select>&nbsp;&nbsp;&nbsp;&nbsp;<input class="but1" type="submit" name="Submit" value="搜索" />
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
		      <input class="btn_sync" type="button" value="同步" onclick="if(toYz())doNeedMethod('是否同步', 'PePdModel.do','tbModel' ,$('#bottomPageForm').serialize())" />
		      <input class="but2" type="submit" name="Submit2" value="新增" onclick="location.href='PePdModel.do?method=add&mod_id=${af.map.mod_id}&tree_param=${tree_param}';" />
		    </logic-el:match>
		    <input class="but10" type="submit" value="二维码" onclick="location.href='PePdModel.do?method=qRCode&mod_id=${af.map.mod_id}&tree_param=${tree_param}';" />
		</td>
	</tr>
   </table>		
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="PePdModel.do?method=delete">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td  nowrap="nowrap" align="center">序号</td> 
          <td nowrap="nowrap" align="center">产品型号</td>
          <td nowrap="nowrap" align="center">品牌</td>
          <td nowrap="nowrap" align="center">尺寸</td>
          <td nowrap="nowrap" align="center">系列</td>
          <td nowrap="nowrap" align="center">配件</td>
          <td nowrap="nowrap" align="center">4K</td>
          <td nowrap="nowrap" align="center">3D电视</td>
          <td nowrap="nowrap" align="center">智能电视</td>
          <td nowrap="nowrap" align="center">网络电视</td>
          <td nowrap="nowrap" align="center">易TV</td>
          <td nowrap="nowrap" align="center">是否有效</td>
          <td width="10%" nowrap="nowrap" align="center">创建日期</td>
          <td width="8%" nowrap="nowrap" align="center">参考价格</td>
          <td align="right">排序</td>
          <td width="10%" nowrap="nowrap" align="center">操作</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap">${vs.count }</td>
              <td align="left" >${fn:escapeXml(cur.md_name)}</td>
              <td align="center">
              
              <c:choose>
              	<c:when test="${cur.sub_brand_id eq 114}">康佳</c:when>
              	<c:when test="${cur.sub_brand_id eq 115}">现代</c:when>
              	<c:when test="${cur.sub_brand_id eq 116}">KKTV</c:when>
              	<c:otherwise>/</c:otherwise>
              </c:choose>
              </td>
              
              <td height="28" align="right">
              	<c:if test="${(cur.md_size eq 0) || (empty cur.md_size)}">无</c:if>
              	<c:if test="${cur.md_size ne 0}">${cur.md_size}</c:if>
              </td>
              <td height="28" align="left">${cur.md_serise}<c:if test="${empty cur.md_serise}">无</c:if></td>
              <td height="28" align="left">
              <c:choose>
              	<c:when test="${cur.is_parts eq 1}">是</c:when>
              	<c:otherwise>否</c:otherwise>
              </c:choose></td>	
              <td height="28" align="left">
              <c:choose>
              	<c:when test="${cur.is_4k eq 1}">是</c:when>
              	<c:otherwise>否</c:otherwise>
              </c:choose></td>	
              <td height="28" align="left">
              <c:choose>
              	<c:when test="${cur.label_3d eq 1}">是</c:when>
              	<c:otherwise>否</c:otherwise>
              </c:choose>
              </td>
              <td height="28" align="left">
              <c:choose>
              	<c:when test="${cur.label_int eq 1}">是</c:when>
              	<c:otherwise>否</c:otherwise>
              </c:choose></td>
              <td height="28" align="left">
              <c:choose>
              	<c:when test="${cur.label_www eq 1}">是</c:when>
              	<c:otherwise>否</c:otherwise>
              </c:choose></td>
              <td height="28" align="left">
              <c:choose>
              	<c:when test="${cur.is_ytv eq 1}">是</c:when>
              	<c:otherwise>否</c:otherwise>
              </c:choose></td>
              <td height="28" align="left">
              <c:choose>
              	<c:when test="${cur.is_del eq 0}">有效</c:when>
              	<c:otherwise>无效</c:otherwise>
              </c:choose>
              </td>	
              <td align="left">
		         <fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" />
		      </td>
              <td align="right"><fmt:formatNumber value="${cur.price_ref}" pattern="￥0" /></td>
              <td align="right">${cur.order_value}</td>
              <td align="center" nowrap="nowrap">
	              <logic-el:match name="popedom" value="+2+">
	              	  <span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'PePdModel.do', 'editProperty','pd_id=${cur.pd_id}&'+$('#bottomPageForm').serialize())">属性</span>
	              	  <c:if test="${cur.is_del eq 0}">
	              	  <span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'PePdModel.do', 'edit','pd_id=${cur.pd_id}&'+$('#bottomPageForm').serialize())">修改</span>
	              	  </c:if>
	              	  <c:if test="${cur.is_del eq 1}">
	              	    <span style="color: #ccc;">修改</span>
	              	  </c:if>
	              	  <c:if test="${cur.is_del eq 0}">
	              	  	<span style="cursor: pointer;" class="fblue" onclick="doNeedMethod('是否设为无效！', 'PePdModel.do', 'modifyDel','pd_id=${cur.pd_id}&is_del=1&'+$('#bottomPageForm').serialize())">无效</span>
	              	  </c:if>
	              	  <c:if test="${cur.is_del eq 1}">
	              	  	<span style="cursor: pointer;" class="fblue" onclick="doNeedMethod('是否设为有效！', 'PePdModel.do', 'modifyDel','pd_id=${cur.pd_id}&is_del=0&'+$('#bottomPageForm').serialize())">有效</span>
	              	  </c:if>	
	              </logic-el:match>
	              <logic-el:notMatch name="popedom" value="+2+">
	                  <span style="color: #ccc" class="fblue" >属性</span>
	                  |
	                  <span style="color:#ccc;" class="fblue" >修改</span>
	              </logic-el:notMatch>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="PePdModel.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;"> 
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("tree_param", "${tree_param}");
				pager.addHiddenInputs("md_name_like", "${fn:escapeXml(af.map.md_name_like)}");
				pager.addHiddenInputs("price_ref_ge", "${af.map.price_ref_ge}");
				pager.addHiddenInputs("price_ref_le", "${af.map.price_ref_le}");
				pager.addHiddenInputs("label_www", "${af.map.label_www}");
				pager.addHiddenInputs("label_3d", "${af.map.label_3d}");
				pager.addHiddenInputs("is_parts", "${af.map.is_parts}");
				pager.addHiddenInputs("is_4k", "${af.map.is_4k}");
				pager.addHiddenInputs("label_int", "${af.map.label_int}");
				pager.addHiddenInputs("is_del", "${af.map.is_del}");
				pager.addHiddenInputs("add_date_s", "${af.map.add_date_s}");
				pager.addHiddenInputs("add_date_e", "${af.map.add_date_e}");
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
	$("#price_ref_ge,#price_ref_le").focus(setOnlyNum);
	$("#span_help").click(function(){
        $("#cvtooltipClose").cvtooltip({
            panel: "#body_oarcont",
            direction: 1,                
            width: 420,
            left: 320,
            top: 5,
            speed: 500,
            delay: 10000
        });
    });	
	
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
});
function toYz(){
	   var pwd = prompt("请输入密码");
       if(pwd == "951"){
              return true;
       }else{
			 alert("对不起！你没有权限操作");
			 return false;	
	   }
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
