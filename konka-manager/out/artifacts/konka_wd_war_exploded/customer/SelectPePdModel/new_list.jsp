<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>请选择产品</title>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<style type="text/css">
select,input{font-family:Microsoft Yahei;color:#74685F}
input[type='submit'],input[type='button'] { border-radius:3px; border:1px solid #CCC; padding:3px 5px;background-color:#eee; color:#555; cursor: pointer; margin-right: 1px;}
input[type='submit']:hover,input[type='button']:hover { background-color:#ccc; border:1px solid #74685F;}
input[type='submit']:active,input[type='button']:active { color:#F00; }

	.sub_div{
		
	}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div id="navTab" class="tabsPage" style="text-align:left;">
	<div class="navTab-panel tabsPageContent">
		<div class="pageContent">
			<html-el:form action="/manager/SelectPePdModel.do">
				<html-el:hidden property="queryString" styleId="queryString" />
				<html-el:hidden property="method" styleId="method" value="save" />
				<html-el:hidden property="id" styleId="id" />
				<div style="height:5px;"></div>
				<table width="100%" border="0" align="left" class="list">
					<tr>
						<td>品&nbsp;&nbsp;&nbsp;&nbsp;类：
							<html-el:select property="cls_id" styleId="cls_id" onchange="getQueryNames($('#key_word').val());">
								<option value="">-请选择品类-</option>
								<html-el:optionsCollection label="tree_name" value="cls_id"  name="clsList" />
							</html-el:select>
						</td>
					</tr>
					<tr>
						<td>
						关键字 ：<html-el:text property="key_word" styleId="key_word" size="24" maxlength="100" style="width:200px;" title="可以输入中文、全拼、简拼进行关键字即时搜索" />
						</td>
					</tr>
          			<tr>
            			<td>
            				<table width="100%" border="0" cellspacing="0" cellpadding="0" id="product_table">
                				<tr>
                  					<td>
                  						<div class="sub_div" ><input type="hidden" value=""/><span></span></div>
                  						<div style="height:28px;font-size:14px;">待选择区</div>
                    					<div id="wait_choose" style="border:1px solid #d0d0d0; width: 180px; height: 250px; overflow: auto;">
                    						<c:forEach var="cur" items="${entityList}" varStatus="vs">
                        						<div class="sub_div"><input type="hidden" value="${cur.pd_id}"/><span>${cur.md_name}</span></div>
                      						</c:forEach>
                    					</div>
                    				</td>
                  					<td>
                  						<div style="height:28px;font-size:14px;">已选择区</div>
                  						<div id="have_choose" style="border:1px solid #d0d0d0; width: 180px; height: 250px; overflow: auto;">
                    					</div>
                    				</td>
                				</tr>
              				</table>
              			</td>
          			</tr>
        		</table>
      		</html-el:form>
    	</div>
  	</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
	//鼠标悬浮在div上时，该div变色
	$(".sub_div").hover(function(){
		this.style.backgroundColor="#6495ED";
	},function(){
		this.style.backgroundColor="#FFFFFF";
	}).click(function(){
		this.style.backgroundColor="#FFFFFF";
		if("wait_choose"==$(this).parent().attr("id")){
			//移动对象
			$(this).appendTo("#have_choose"); 
		}else{
			//移动对象
			$(this).appendTo("#wait_choose"); 
		}
		//alert($(this).text());
		//alert($(this).children('input').val());
	});
	
	var api = frameElement.api, W = api.opener; 
	api.button({ 
		    name:'提交', 
		    callback:ok 
		},
		{
		    name:'关闭', 
		    cancel:true
		}
	); 
	/* 函数ok即为上面添加按钮方法中callback回调函数调用的函数 */ 
	function ok() 
	{ 
		<c:if test="${af.map.selectype eq 'signal'}">
		if ($("#select2 option").length > 1) {
			alert("单选，只能选择一个！");
		} else {
		</c:if>
		var ids = "";
		var names = "";
		var inputs = $("#have_choose").find("input");
		var spans = $("#have_choose").find("span");
		if (inputs.length > 50){
			alert("不能选择超过50个型号");
			return false;
		}
		inputs.each(function(index,da){
			if ( index == 0 ) {
				ids += $(this).val();
				names += spans.eq(index).text();
			} else {
				ids += "," + $(this).val();
				names += "," + spans.eq(index).text();
			}
		});
		W.document.getElementById("pro_ids").value = ids;
		W.document.getElementById("pro_names").value = names;
		W.getProInfo();
	};
$(document).ready(function(){
	var f = document.forms[0];
	$("#key_word").attr("autocomplete", "off");
	$("#key_word").attr("disableautocomplete", "true");
	
	$("#btn_submit").click(function(){
		<c:if test="${af.map.selectype eq 'signal'}">
		if ($("#select2 option").length > 1) {
			alert("单选，只能选择一个！");
		} else {
		</c:if>
		var ids = "";
		var names = "";
		var inputs = $("#have_choose").find("input");
		var spans = $("#have_choose").find("span");
		if (inputs.length > 50){
			alert("不能选择超过50个型号");
			return false;
		}
		inputs.each(function(index,da){
			if ( index == 0 ) {
				ids += $(this).val();
				names += spans.eq(index).text();
			} else {
				ids += "," + $(this).val();
				names += "," + spans.eq(index).text();
			}
		});
		W.document.getElementById("pro_ids").value = ids;
		W.document.getElementById("pro_names").value = names;
		api.close();
	});
	
    var bind_name = 'input';
    if (navigator.userAgent.indexOf("MSIE") != -1){
        bind_name = 'propertychange';
    }
    $('#key_word').bind(bind_name, function(){
    	getQueryNames($('#key_word').val());
    });
    
    $('#key_word').keypress(function (e) {
        var k = e.keyCode || e.which;
        if (k == 13) {
            return false;
        }
    });
});

//查询产品列表
function getQueryNames(key_word) {
	//清空待选项
   	$("#wait_choose").empty();
   	$.ajax({
		type: "POST",
		cache: false,
		url: "${ctx}/jxcnokey/SelectPePdModel.do",
		data: "method=" + "getQueryNames" + "&cls_id=" + $("#cls_id").val()+"&key_word=" + $("#key_word").val(),
		dataType: "json",
		error: function(request, settings){},
		success: function(data) {
			if (data.length >= 1) {
				var clone_div = $("#product_table tr:eq(0) td:eq(0) div:eq(0)");
				for(var i = 0; i < data.length - 1; i++) {
					var clo = clone_div.clone(true);
					clo.children("input").val(data[i].id);
					clo.children("span").text(data[i].name);
					$("#wait_choose").append(clo);
				}
			}
		}
	});
}

//]]></script> 
</body>
</html>
