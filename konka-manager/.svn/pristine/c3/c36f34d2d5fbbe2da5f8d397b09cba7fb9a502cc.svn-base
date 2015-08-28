<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${naviString}</title>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont">
  <div class="oartop" >
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2" style="overflow-x: auto;">
    <div align="right"><a href="${ctx}/manager/admin/Html4sjsb.do?mod_id=211100">快速上报</a></div>
    <%@ include file="/commons/pages/messages.jsp" %>
    <html-el:form action="/admin/KonkaMobileSaleDataInput.do" method="post" enctype="multipart/form-data">
      <html-el:hidden property="mod_id"/>
      <html-el:hidden property="method" value="save"/>
      <html-el:hidden property="queryString" />
      <c:set var="readonly" value="${empty af.map.bill_id ? false : true}"/>
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td colspan="2"  bgcolor="#CCCCCC" style="font-weight:bold;">选择待报送的门店</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>门店：</td>
          <td><select id="store_id" name="store_id">
	          <option value="">-请选择-</option>
	          <c:forEach var="cur" items="${storeList}">
	              <option value="${cur.id}">${cur.name}</option>
	          </c:forEach>
	         </select></td>
        </tr>
        <tr>
          <td colspan="2"  bgcolor="#CCCCCC" style="font-weight:bold;">填写销售明细</td>
        </tr>
        <tr>
          <td colspan="2"><table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
          		<tr>
          			<td align="center" nowrap="nowrap"><span style="color:red;">* </span>商品&nbsp;&nbsp;</td>
          			<td width="5%" align="center" nowrap="nowrap">单位</td>
          			<td width="6%" align="center" nowrap="nowrap"><span style="color:red;">* </span>数量</td>
          			<td width="8%" align="center" nowrap="nowrap"><span style="color:red;">* </span>单价（元）</td>
          			<td width="10%" align="center" nowrap="nowrap">总价（元）</td>
          			<td width="6%" align="center" nowrap="nowrap">顾客姓名</td>
          			<td width="10%" align="center" nowrap="nowrap">顾客电话</td>
          			<td width="10%" align="center" nowrap="nowrap">顾客地址</td>
          			<td width="10%" align="center" nowrap="nowrap">顾客身份证</td>
          			<td width="10%" align="center" nowrap="nowrap">备注</td>
          			<td width="10%" align="center" nowrap="nowrap">附件</td>
          			<c:if test="${not readonly}"><td width="5%" align="center" nowrap="nowrap" id="addPdTD" style="cursor:pointer;"><img src="${ctx}/images/+.gif" style="vertical-align:middle;" /></td></c:if>
          		</tr>
          		<c:set var="details" />
          		<c:if test="${readonly}">
          			<html-el:hidden property="method_type" value="1" />
          			<c:forEach var="cur1" items="${detailsList}">
          			<c:set var="details" value="1" />
          			<tr class="tr_pd">
	          			<td align="center" class="td_pd" nowrap="nowrap">${cur1.map.goods_name}</td>
	          			<td align="center" nowrap="nowrap"><span class="dw">台</span></td>
	          			<td align="center" nowrap="nowrap"><input name="num" id="num" size="10" onfocus="javascript:setOnlyInt(this)" value="${cur1.num}" maxlength="4" class="num" disabled="${readonly}" /></td>
	          			<td align="center" nowrap="nowrap"><input name="price" id="price" size="10" value="${cur1.price}" onfocus="javascript:setOnlyNum(this);" maxlength="6" class="price" disabled="${readonly}" /></td>
	          			<td align="center" nowrap="nowrap"><input name="goods_money" value="${cur1.money}" class="goods_money" size="10" maxlength="6"/></td>
	          			<td align="center" nowrap="nowrap"><input name="realname" size="6" maxlength="20" /></td>
	          			<td align="center" nowrap="nowrap"><input name="phonenum" size="12" maxlength="11" /></td>
	          			<td align="center" nowrap="nowrap"><input name="addresss" size="12" maxlength="60" /></td>
	          			<td align="center" nowrap="nowrap"><input name="mastercode" size="15" maxlength="18" /></td>
	          			<td align="center" nowrap="nowrap"><input name="memo" value="${cur1.memo}" class="memo" maxlength="60" /></td>
	          			<c:if test="${not readonly}"><td align="center" style="cursor:pointer;" class="td_del"><img src="${ctx}/images/x.gif" style="vertical-align:middle;top:0px;" /></td></c:if>
	          		</tr>
	          		</c:forEach>
          		</c:if>
          		<c:if test="${not readonly}">
          		<html-el:hidden property="method_type" value="0" />
          		<c:set var="details" value="0" />
          		<tr class="tr_pd">
          			<td align="center" nowrap="nowrap"><input type="hidden" name="goods_id" id="goods_id" value="" />
			    		<input class="change_class" type="text" id="goods_id_" size="18" maxlength="40" /></td>
          			<td align="center" nowrap="nowrap"><span class="dw">台</span></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="num" styleId="num" size="4" onfocus="javascript:setOnlyInt(this)" value="1" maxlength="6" styleClass="num" /></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="price" styleId="price" size="6" onfocus="javascript:setOnlyNum(this);"
          			 maxlength="6" styleClass="price" /></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="goods_money" styleClass="goods_money" size="10" maxlength="6" onfocus="javascript:setOnlyInt(this)" /></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="realname" size="6" maxlength="20" /></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="phonenum" size="12" maxlength="11" /></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="addresss" size="12" maxlength="60" /></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="mastercode" size="15" maxlength="18" /></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="memo" styleClass="memo" size="20" maxlength="60" /></td>
          			<td align="center" nowrap="nowrap">
          			<input name="xl" id="xl_1" type="hidden" value="1"/>
          			<div id="divFileHidden_1" style="display: none;">
			          <input name="file_hidden_1" type="file" id="file_hidden_1" style="width: 250px;" />
			          <img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" id="imgDelTr_1" title="删除"/></div>
			          <div id="divFile_1">
			          <input name="file_show_1" type="file" id="file_show_1" style="width: 250px;" />
			          <img src="../../images/+.gif" style="vertical-align:middle; cursor: pointer;" id="imgAddTr_1" class="m1" title="再添加一个" /></div>
      				</td>
          			<td align="center" id="del"><img src="${ctx}/images/x_gray.gif" style="vertical-align:middle;top:0px;" /></td>
          		</tr>
          		</c:if>
          		<tr id="tr_model" style="display:none;">
          			<td align="center" nowrap="nowrap"><input type="hidden" name="goods_id" id="" />
			    		<input class="change_class" type="text" size="18" maxlength="40" id="" /></td>
          			<td align="center" nowrap="nowrap"><span class="dw">台</span></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="num" size="4" onfocus="javascript:setOnlyInt(this)" maxlength="6" value="1" styleClass="num" /></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="price" size="6" onfocus="javascript:setOnlyNum(this);" maxlength="6" styleClass="price" /></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="goods_money" styleClass="goods_money" size="10"  maxlength="6" onfocus="javascript:setOnlyInt(this)" /></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="realname" size="6" maxlength="20" /></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="phonenum" size="12" maxlength="11" /></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="addresss" size="12" maxlength="60" /></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="mastercode" size="15" maxlength="18" /></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="memo" size="20" styleClass="memo" maxlength="60" /></td>
          			<td align="center" nowrap="nowrap">
          			<input name="xl" id="xl" class="xl" type="hidden" />
          			<div id="d1">
          			<div id="divFileHidden" style="display: none;">
			          <input name="file_hidden" type="file" id="file_hidden" style="width: 250px;" />
			          <img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" id="imgDelTr" title="删除"/></div>
			          <div id="divFile">
			          <input name="file_show" type="file" id="file_show" style="width: 250px;" />
			          <img src="../../images/+.gif" style="vertical-align:middle; cursor: pointer;" id="imgAddTr" class="m1" title="再添加一个" /></div>
      				</div>
      				</td>
          			<td align="center" style="cursor:pointer;"><img src="${ctx}/images/x.gif" style="vertical-align:middle;top:0px;" /></td>
          		</tr>
          		<tbody id="showAddTrsTbody"></tbody>
          </table>
          <div style="display:none;">总价：<span id="rec_money">0</span>元</div></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="" value="提 交" styleClass="but4" styleId="btn_submit" />
            	<html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=idialog"></script>
<script type="text/javascript" src="${ctx}/scripts/json2.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//定义AJAX获取的型号信息
	var pd_module_data = JSON.parse('${pePdModelJson}');
	
	// 计数，处理点击添加以后输入行的ID问题
	var num_count = 1;
	var num=0;
	
	// 搜索--判断和区分IE和非IE浏览器分别进行注册监听
	$("#goods_id_").bind("input propertychange", function() {
		initAutoDiv(0);
	});
	
	$("#opr_date").attr("dataType", "Require").attr("msg", "请选择日期！");
	$("#store_id").attr("dataType", "Require").attr("msg", "请选择您要上报的门店！");

	$("#addPdTD").click(function(){

		var tr_pd = $("#tr_model").clone(true).attr("class","tr_pd");
		tr_pd.find("input").eq(0).attr("id", "goods_id" + num_count);
		tr_pd.find("input").eq(1).attr("id", "goods_id_" + num_count);
		num = num_count+1;
		
		tr_pd.find("#imgAddTr").attr("id", "imgAddTr_" + num);
		tr_pd.find("#imgDelTr").attr("id", "imgDelTr_" + num);
		//var t1 = tr_pd.find("#file_hidden").clone(true).attr("id", "file_hidden_" + num).attr("name", "file_hidden_" + num);
		//alert(t1.attr("id"));
		//alert(t1.attr("name"));
		//$("#divFileHidden",tr_pd).prepend(t1);
		tr_pd.find("#divFileHidden").attr("id", "divFileHidden_" + num);
		//tr_pd.find("#file_hidden").remove();
		tr_pd.find("#file_hidden").attr("id", "file_hidden_" + num);
		tr_pd.find("#file_hidden_"+ num).attr("name", "file_hidden_" + num);
		tr_pd.find("#divFile").attr("id", "divFile_" + num);
		//var t2 = tr_pd.find("#file_show").clone(true).attr("id", "file_show_" + num).attr("name", "file_show_" + num);
		//tr_pd.find("#file_show").remove();
		tr_pd.find("#file_show").attr("id", "file_show_" + num);
		tr_pd.find("#file_show_"+num).attr("name", "file_show_" + num);
		tr_pd.find("#xl").val(num);
		
		
		num_count++;
		tr_pd.appendTo($("#showAddTrsTbody")).show();
		var lastTR = $("tr:last", "#showAddTrsTbody");
		
		$(".change_class").bind("input propertychange", function() {
			var index = $(this).attr("id").substring(9);
			initAutoDiv(index);
		});

		$("td:last", lastTR).click(function (){
			$(this).parent().remove();
			var index_reset=2;
			$(".xl").each(function(){
				alert(index_reset);
				this.val(index_reset);
				
				index_restet = index_restet + 1;
				alert(index_restet);
			});
		//	$(".price").blur();  //重新计算总金额
			if (window.parent.resizeFrameHeight) window.parent.resizeFrameHeight('mainFrame', 3);
		
			
			
			
			
	    }).css("cursor", "pointer");
		   
		//iframe高度自适应
		window.parent.resizeFrameHeight('mainFrame', 3);

		
	});

	$(".m1").each(function(){
		$(this).click(function(){
			var strs= new Array();
			strs=$(this).attr("id").split("_");
			var nm=strs[1];
			//alert(nm);
			$("#divFileHidden_"+nm).clone().find("#file_hidden_"+nm).attr("name", "file_" + new Date().getTime()+"_"+nm).end().appendTo($("#divFile_"+nm)).show();
		        resizeFrameHeight();
		        $("img[id='imgDelTr_"+nm+"'"+"]").each(function(){
		            $(this).click(function (){
		                $(this).parent().remove();
		                resizeFrameHeight();
		            });
		    });

		});

	});

	//$("#imgAddTr_"+num_count).click(function (){
       // $("#divFileHidden_"+num_count).clone().find("#file_hidden_"+num_count).attr("name", "file_" + new Date().getTime()).end().appendTo($("#divFile")).show();
       // resizeFrameHeight();
       // $("img[id='imgDelTr_"+num_count+"'"+"]").each(function(){
           // $(this).click(function (){
               // $(this).parent().remove();
               // resizeFrameHeight();
           // });
       // });
 	//});     

 function resizeFrameHeight(offset, min_height) {
		// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
		$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
} 

	if("${details}" == 1){                      //修改页面
		$(".td_del").click(function(){
			$(this).parent().remove();	
		});

		$(".tr_pd .td_pd .store_id").each(function(){
			$(this).val($(this).attr("id"));
		});

		$(".tr_pd .td_pd .goods_id").each(function(){
			$(this).val($(this).attr("id"));
		});
	}

	$(".num").blur(function(){
		var num = $(this).val();
		var price = $(this).parent().parent().children('td').eq(3).children().val();
		if($.trim(num).length > 0 && $.trim(price).length > 0){
			 var money = num * price;
			 var $money = $(this).parent().parent().children('td').eq(4).children();
			 $money.val(money);   //金额

			 var total_money = 0;
			 $(".goods_money").each(function(){
				var goods_money = $(this).val();
				if($.trim(goods_money).length > 0 ){
					total_money = parseFloat(total_money) + parseFloat(goods_money);
				}
			 });
			 $("#rec_money").val(total_money);
			 var discount  = $("#discount").val();
			 $("#money").val(total_money * parseFloat(discount) / 100);
		}
	});

	$(".price").blur(function(){
		var price = $(this).val();
		var num = $(this).parent().parent().children('td').eq(2).children().val();
		var num = $(this).parent().parent().children('td').eq(2).children().val();
		if(price<500||price>90000){
           alert("价格应该在500到90000之间");
           $(this).val("");
           return false;
		}
		if($.trim(num).length > 0 && $.trim(price).length > 0){
			 var money = num * price;
			 var $money = $(this).parent().parent().children('td').eq(4).children();
			 $money.val(money);   //金额

			 var total_money = 0;
			 $(".goods_money").each(function(){
					var goods_money = $(this).val();
					if($.trim(goods_money).length > 0 ){
						total_money = parseFloat(total_money) + parseFloat(goods_money);
					}
			 });
			 $("#rec_money").val(total_money);
			 var discount  = $("#discount").val();
			 $("#money").val(total_money * parseFloat(discount) / 100);
		}
	});

	$(".goods_money").blur(function(){
		var goods_money = $(this).val();
		var num = $(this).parent().parent().children('td').eq(2).children().val();
		if($.trim(num).length > 0 && $.trim(goods_money).length > 0){
			 var price = goods_money / num;
			 var $price = $(this).parent().parent().children('td').eq(3).children();
			 $price.val(price.toFixed(2));   //金额

			 var total_money = 0;
			 $(".goods_money").each(function(){
					var goods_money = $(this).val();
					if($.trim(goods_money).length > 0 ){
						total_money = parseFloat(total_money) + parseFloat(goods_money);
					}
			 });
			 $("#rec_money").val(total_money);
			 var discount  = $("#discount").val();
			 $("#money").val(total_money * parseFloat(discount) / 100);
		}
	});

	$("#btn_submit").click(function(){
		$(".tr_pd .goods_id").attr("dataType", "Require").attr("msg", "请选择！");
		$(".tr_pd .num").attr("dataType", "Require").attr("msg", "请填写！");
		$(".tr_pd .price").attr("dataType", "Require").attr("msg", "请填写！");
		if(Validator.Validate(this.form, 3)){
			$("#tr_model").remove();
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});
	
	// 输入关键字搜索
	function initAutoDiv(index){
		// 隐藏下拉框
		if($("#auto_prompt_div")){
	        $("#auto_prompt_div").remove();
		}
		
	 	var id1 = "goods_id_";
	    var id2 = "goods_id";
	    if(index != 0) { 
	    	id1 = "goods_id_" + index;
	        id2 = "goods_id" + index;
	    }
	    
	    // 输入框有改变将型号置为空
	    $("#" + id2).val("");
	    
		if($.trim($("#" + id1).val()).length >= 2){
			var val = $.trim($("#" + id1).val());
			var count = 0;
			for(var i = 0; i < pd_module_data.length; i++){
				var id = pd_module_data[i].id;
				var name = pd_module_data[i].name;
				if(name.toLowerCase().indexOf(val.toLowerCase()) != -1)
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
					var id = pd_module_data[i].id;
					var name = pd_module_data[i].name;
					if(name.toLowerCase().indexOf(val.toLowerCase()) != -1){
	                    var tr = $("<tr />").css("cursor", "pointer").attr("class", "tr_value").mouseout(function(){
	                        $(this).css("backgroundColor", "white").css("color", "black");
	                    }).mouseover(function(){
	                        $(this).css("backgroundColor", "#1C86EE").css("color", "white");
	                    }).click(function(){
	                        $("#" + id1).val($(this).find("td").eq(0).html());
	                        $("#" + id2).val($(this).find("td").eq(0).attr("id"));
	                	    $("#auto_prompt_div").remove();
	                    });
	                    var td = $("<td class=\"td_class\" />").html(name).css("fontSize", "12px").css("margin", "5px 5px 5px 5px").css("padding-left", "6px").attr("align", "left").attr("id", id);
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
	}
	
	//正则表达式：只能输入数字
	function setOnlyNum(obj) {
		$(obj).css("ime-mode", "disabled");
		$(obj).attr("t_value", "");
		$(obj).attr("o_value", "");
		$(obj).bind("dragenter",function(){
			return false;
		});
		$(obj).keypress(function (){
			if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
		}).keyup(function (){
			if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
		}).blur(function (){
			if(!obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\.\d+$/))obj.value=0+obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
			if(isNaN(obj.value)) obj.value = "";
		});
	}

	//正则表达式：只能输入数字
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
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
