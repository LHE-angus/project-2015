<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳网上直销商城</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/zxmall.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/zxmall/__inc/top.jsp" flush="true" />
<jsp:include page="/zxmall/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/zxmall/__inc/nav.jsp" flush="true" />
<!-- second start -->
<div class="maincont">
  <jsp:include page="/zxmall/__inc/_mleft.jsp" flush="true" />
  <!--right-->
  <div class="zxmall_right padbot45">
    <div class="position"><a href="${ctx }/zxmall/Index.do">首页</a> &gt; <a href="${ctx }/zxmall/center/Index.do">会员中心</a> &gt;商品佣金查询</div>
    <div class="zxmalltab3">
      <html-el:form action="/center/EcBcompPdRebates">
        <html-el:hidden property="method" styleId="method" value="list" />
        <html-el:hidden property="type" value="1"/>
        <table width="100%" border="0" cellspacing="0" cellpadding="0"  class="zxmall_form_table0">
          <tr>
          <td width="30%">
         商品型号：<!--   <html-el:text  property="pd_sn" styleId="pd_sn" ></html-el:text>  -->
         <html-el:hidden styleClass="title_item" property="pd_id" styleId="pd_id" value="${af.map.pd_id}" />
    		<html-el:text property="pd_id_1" styleId="pd_id_1" maxlength="50" />
          </td>
          <td width="30%">
         验证码：<html-el:text  styleId="verificationCode" property="verificationCode" />&nbsp;&nbsp;<a class="roundCode" hidefocus="hideFocus" style="hide-focus: true"><img src="${ctx}/images/VerificationCode.jpg" width="76" height="24" style="border:1px solid #A1BCA3;cursor:pointer;" alt="验证码，看不清楚请用鼠标点击此处！" onclick="$(this).hide().attr('src', this.src + '?' + new Date().getTime()).fadeIn();" /></a></td>
          <td width="30%"><input class="inputbtn" type="submit" name="Submit" value="查询" />
          </td>
          </tr>
        </table>
      </html-el:form>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="zxmall_form_table2">
        <tr class="tr1">
          <td width="30%" nowrap="nowrap" align="center">商品型号</td>
          <td width="20%" nowrap="nowrap" align="center">佣金</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr height="60">
              <td align="center" nowrap="nowrap">${cur.map.pd_sn}</td>
              <td align="center" nowrap="nowrap">
              <c:if test="${cur.b_type eq 0}"><fmt:formatNumber value="${cur.b_value}" pattern="###,##0.00" />%</c:if>
              <c:if test="${cur.b_type eq 1}"><fmt:formatNumber value="${cur.b_value}" pattern="###,##0.00" />元</c:if>
              </td>
            </tr>
          </c:forEach>
          <c:if test="${empty entityList }">
            <tr height="60">
              <td align="center" nowrap="nowrap" colspan="2"> 暂无佣金记录 </td>
            </tr>
          </c:if>
        </tbody>
      </table>
      <c:if test="${af.map.pager.pageCount>1}">
        <form id="bottomPageForm" name="bottomPageForm" method="post" action="?">
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td height="40"><div style="text-align: right; padding-right: 5px;">
                  <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
                  <script type="text/javascript">
			                     var pager = new Pager(document.bottomPageForm, parseInt('${af.map.pager.recordCount}'), parseInt('${af.map.pager.pageSize}'), parseInt('${af.map.pager.currentPage}'));
			                     pager.addHiddenInputs("method", "list");
			                     pager.addHiddenInputs("pd_sn", "<c:out value='${af.map.pd_sn}'/>"); 
			                     pager.addHiddenInputs("type", "<c:out value='${af.map.type}'/>"); 	
			                     document.write(pager.toString());
			                 </script>
                </div></td>
            </tr>
          </table>
        </form>
      </c:if>
    </div>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript">//<![CDATA[

$(document).ready(function(){
//定义AJAX获取的型号信息
var pd_module_data = eval('${kpListJson}');

$("#pd_id_1").blur(function(){
	var t = $("#pd_id_1").val();
	$("#pd_id").val(t);
});


// 搜索--判断和区分IE和非IE浏览器分别进行注册监听
$("#pd_id_1").bind("input propertychange", function() {
	initAutoDiv();
});

//输入关键字搜索
function initAutoDiv(index){
		try{
		// 隐藏下拉框
		if($("#auto_prompt_div")){
	        $("#auto_prompt_div").remove();
		}
		
	 	var id1 = "pd_id_1";
	    // 输入框有改变将型号置为空
	   $("#pd_id").val("");
	    
		if($.trim($("#" + id1).val()).length >= 2){
			var val = $.trim($("#" + id1).val());
			var count = 0;
			for(var i = 0; i < pd_module_data.length; i++){
				var id = pd_module_data[i].pd_sn;
				var pd_sn = pd_module_data[i].pd_sn;
				if(pd_sn.toLowerCase().indexOf(val.toLowerCase()) != -1)
					count++;
			}

			var top = $("#" + id1).offset().top;
	        var left = $("#" + id1).offset().left;
	        var width = $("#" + id1).width();
	        if(width <= 90) width = 90; // 强制宽度最小90
	        var auto_prompt_div = $("<div />").width(width).css({"height":"200px", "overflow-y":"auto", "overflow-x":"hidden", "position":"absolute", "backgroundColor":"white", "border":"1px solid #1C86EE", "left" : left}).css("top", top + $("#" + id1).height() + 6).attr("id", "auto_prompt_div");
	        var table = $("<table width='100%' id=\"div_table\" />").attr("cellpadding", "0").attr("cellspacing", "0");
	        
			// 满足条件控制在30个内，如果超过30个则不显示
			if(count != 0){
				for(var i = 0; i < pd_module_data.length; i++){
					var id = pd_module_data[i].pd_sn;
					var pd_sn = pd_module_data[i].pd_sn;
					if(pd_sn.toLowerCase().indexOf(val.toLowerCase()) != -1){
	                    var tr = $("<tr />").css("cursor", "pointer").attr("class", "tr_value").mouseout(function(){
	                        $(this).css("backgroundColor", "white").css("color", "black");
	                    }).mouseover(function(){
	                        $(this).css("backgroundColor", "#1C86EE").css("color", "white");
	                    }).click(function(){
	                        $("#" + id1).val($(this).find("td").eq(0).html());
	                        $("#pd_id").val($(this).find("td").eq(0).attr("id"));
	                	    $("#auto_prompt_div").remove();
	                    });
	                    var td = $("<td class=\"td_class\" />").html(pd_sn).css("fontSize", "12px").css("margin", "5px 5px 5px 5px").css("padding-left", "6px").attr("align", "left").attr("id", id);
	                    tr.append(td);
	                    table.append(tr);
				    }
				}
			} else {
	            var tr = $("<tr />").css("cursor", "pointer").attr("class", "tr_value").mouseout(function(){
	                $(this).css("backgroundColor", "white").css("color", "black");
	            }).mouseover(function(){
	                $(this).css("backgroundColor", "#1C86EE").css("color", "white");
	            });
	            var td = $("<td />").html("提示，您搜索的型号“" + val + "”，共检索到 " + count + " 条数据，请精确搜索条件！").css("fontSize", "12px").css("margin", "5px 5px 5px 5px").css("padding-left", "6px").attr("align", "left");
	            tr.append(td);
	            table.append(tr);
			}

			auto_prompt_div.append(table);
	        $(document.body).append(auto_prompt_div);
		}
	}catch(e){
	}
   }	

});


//]]></script>
<jsp:include page="/zxmall/__inc/footer.jsp" flush="true" />
<!-- six footer -->
</body>
</html>
