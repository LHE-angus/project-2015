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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script> 
<style type="text/css">
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
	
</style>   
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/spgl/EcVouchersApply" method="post">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save2" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <html-el:hidden property="prod_types" styleId="prod_types" value="${af.map.prod_types}"/>
      <html-el:hidden property="pd_name_hid" styleId="pd_name_hid" value="${af.map.pd_name_hid}"/>
      <html-el:hidden property="goods_types_hid" styleId="goods_types_hid" value="${af.map.goods_types_hid}"/>
      <html-el:hidden property="type_name_hid" styleId="type_name_hid" value="${af.map.type_name_hid}"/>
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">购物券名称：</td>
          <td width="88%" align="left">
          	${af.map.title}
          </td>
        </tr>
          <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">是否需要加密：</td>
          <td width="88%" align="left">
          <c:if test="${af.map.has_pwd eq 0}">不需要</c:if>
          <c:if test="${af.map.has_pwd eq 1}">需要</c:if>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">申请数量：</td>
          <td width="88%" align="left">${af.map.apply_num}</td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">面额：</td>
          <td width="88%" align="left">${af.map.price}</td>
        </tr>
          <tr>
          <td width="12%" nowrap="nowrap" class="title_item"  align="right">有效开始时间：</td>
          <td width="88%" align="left">
          <fmt:formatDate value="${af.map.start_date}" pattern="yyyy-MM-dd HH:mm:ss" />
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item"  align="right">有效截止时间：</td>
          <td width="88%" align="left">
          <fmt:formatDate value="${af.map.effective_date}" pattern="yyyy-MM-dd HH:mm:ss" />
          </td>
        </tr>
        <tr>
        <td width="12%" nowrap="nowrap" class="title_item" align="right">可使用产品类别：</td>
        <td align="left" width="88%">
        ${af.map.prod_name}
       </td>
      </tr>
       <tr>
        <td width="12%" nowrap="nowrap" class="title_item" align="right">可使用属性类别：</td>  
        <td align="left" width="88%">
        ${af.map.goods_types}
       </td>
      </tr>
       <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">所属系统：</td>
          <td width="88%" align="left">
          <c:if test="${af.map.own_sys eq 2}">触网</c:if>
          </td>
        </tr>
        <tr>  
          <td width="12%" nowrap="nowrap" class="title_item" align="right">所属总部/分公司：</td>
          <td width="88%" align="left">
           <c:if test="${af.map.plat_sys eq 0}">总部</c:if>
           <c:if test="${af.map.plat_sys eq 1}">分公司</c:if>
          </td>
        </tr>  
      <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">满多少元：</td> 
          <td width="88%" align="left">${af.map.m_menoy}</td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">满多少数量：</td> 
          <td width="88%" align="left">${af.map.m_num}</td>
        </tr>
        <tr>
          		<td class="title_item" nowrap="nowrap">是否可以叠加使用：</td>
          		<td>
          		<c:if test="${af.map.is_other eq 0}">可以</c:if> 
          		<c:if test="${af.map.is_other eq 1}">不可以</c:if>
          		</td>
          	</tr> 
       <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">备注：</td>
          <td width="88%" align="left">
		${af.map.memo}
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="center" colspan="2">审核记录</td> 
        </tr>
         <tr>
            <td colspan="2"><table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass"> 
                <tr>
                  <td class="title_item" width="5%" style="text-align:center;" nowrap="nowrap">序号</td>
                  <td class="title_item" width="18%" style="text-align:center;" nowrap="nowrap">登录名</td>
                  <td class="title_item" width="18%" style="text-align:center;" nowrap="nowrap">操作时间</td>
                  <td class="title_item" width="18%" style="text-align:center;" nowrap="nowrap">审核结果</td>
                  <td class="title_item" style="text-align:center;" nowrap="nowrap">意见</td>
                </tr>
                <c:forEach items="${eaList}" var="cur" varStatus="st">
                  <tr>
                    <td align="center" >${st.count}</td>
                    <td align="center">${cur.opr_user_real_name } </td>
                    <td align="center"><fmt:formatDate value="${cur.oper_date }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				    <td align="center">     
				       <c:if test="${cur.state eq 0 }">审核通过</c:if>
					   <c:if test="${cur.state eq 1 }">审核不通过</c:if>
				         </td>
                    <td align="center">${cur.remark}</td>
                  </tr>
                </c:forEach>
              </table></td>
         </tr>  
        <tr>
          <td>&nbsp;</td>  
          <td align="center"><html-el:button property="" value="发放" styleClass="but4" styleId="btn_submit" />
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();return false;" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=discuz"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#btn_submit").click(function(){
 		var isSubmit = Validator.Validate(this.form, 3);
		if (isSubmit) {
			if(confirm("您确认发放优惠券吗?")){
				$(":button").attr("disabled", "true");
				$(":reset").attr("disabled", "true");
				this.form.submit();
			}
			
		}
	});
	
});


function openEntpChild(){
	$.dialog({
		title:  "选择产品类别",
		width:  536,
		height: 295,
        lock:true ,
		content:"url:${ctx}/manager/spgl/EcVouchersApply.do?method=chooseProdType"
	});
}

function openTypeChild(){
	$.dialog({
		title:  "选择属性类别",
		width:  536,
		height: 295,
        lock:true ,
		content:"url:${ctx}/manager/spgl/EcVouchersApply.do?method=chooseGoodType"
	});
}


function setOnlyInt(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:\d+(?:\d+)?|\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\d+$/))obj.value=obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		if(obj.value.length == 0 || isNaN(obj.value) || obj.value == 0) obj.value = "1"; 
	});
}

//正数
function setOnlyPosNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
