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
		<link rel="stylesheet" type="text/css" href="${ctx}/commons/scripts/themes/default/easyui.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/commons/scripts/themes/icon.css"/>
		<style>
			.rtable1 td {
				padding:2px 5px;
			}
			ol li {
				list-style-type: none
			}
		</style>
	</head>
	<body >
		<div class="oarcont">
			<div class="oartop">
				<table width="400" border="0" cellpadding="0" cellspacing="0">
			    	<tr>
			        	<td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
			        	<td>&nbsp;当前位置：<span id='nav'></span></td>
			      	</tr>
			    </table>
			</div>
		  	<div style="padding:5px">
		    	<form id="fm" method="post" >
		    		<input type="hidden" id='mod_id' value=''/>
		    		<input type="hidden" id='flag' value=''/>
		    		<input type="hidden" id='queryStr' value='${queryStr }'/>
		    		<input type="hidden" id='return_flag' value=''/>
					<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
				        <tr>
						   	<td style="padding-left: 5%;">
						   		<label for="area_name"><strong class="fb">区&nbsp;&nbsp;&nbsp;&nbsp;域：</strong></label>
				   				<select id="area_name" class="easyui-combobox" name="area_name" style="width:135px" data-options="editable:false">
				   					<option value="">-请选择-</option>
									<option value="华东">华东</option>
					                <option value="山东">山东</option>
									<option value="东北">东北</option>
									<option value="华北">华北</option>
									<option value="华南">华南</option>
									<option value="西南">西南</option>
									<option value="华中">华中</option>
									<option value="西北">西北</option>
				   				</select>			   		
						   	</td>
				        	<td colspan="2">
					            <label><strong class="fb">行政区划：</strong></label>  
					            <select name="province" id="province" style="width:180px;" class="easyui-combobox" data-options="editable:false">
					            </select>
					            &nbsp;
					            <select name="city" id="city" style="width:100px;" class="easyui-combobox" data-options="editable:false">
					            </select>
					            &nbsp;
					            <select name="country" id="country" style="width:100px;" class="easyui-combobox" data-options="editable:false">
					            </select>
					            &nbsp;
					            <select name="town" id="town" style="width:100px;" class="easyui-combobox" data-options="editable:false">
					            </select>
				        	</td>
						</tr>
						<tr>
						    <td style="padding-left: 5%">
						    	<label for="p_index"><strong class="fb">乡镇编码：</strong></label>
						   		<input class="easyui-validatebox" id="p_index" name="p_index" style="width:135px" maxlength="30" value="${p_index }"/>	
						   	</td>
						   	<td>
						   		<label for="t_type"><strong class="fb">类&nbsp;&nbsp;&nbsp;&nbsp;型：</strong></label>
						   		<select id="t_type" class="easyui-combobox" name="t_type" style="width:135px" data-options="editable:false">
									<option value="">-请选择-</option>
									<option value="1">乡镇</option>
									<option value="2">街道</option>
									<option value="3">开发区</option>
						   		</select>
						   	</td>
						   	<td>
						   		<label for="t_status"><strong class="fb">乡镇状态：</strong></label>
						   		<select id="t_status" class="easyui-combobox" name="t_status" style="width:135px" data-options="editable:false">
									<option value="">-请选择-</option>
									<option value="0">正常</option>
									<option value="1">撤销</option>
						   		</select>
						   	</td>
				        </tr>
						<tr>
						   	<td style="padding-left: 5%">
						   		<label for="dept_id"><strong class="fb">分 公 司：</strong></label>
				   				<input id="dept_id" name="dept_id" style="width:135px" value="${dept_id }"/>
						   	</td>
						   	<td> 
						   		<label for="modify_name"><strong class="fb">维&nbsp;护&nbsp;人：</strong></label>
						   		<input class="easyui-validatebox" type="text" name="modify_name" id="modify_name" value="${modify_name}" style="width: 135px"></input>
						   	</td>
						    <td>
						    	<label for="modify_date"><strong class="fb">维护日期：</strong></label>
						   		<input name="modify_date" id="modify_date" style="width: 135px" value="${modify_date }" title="点击选择日期" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true})" />
						   	</td>
				        </tr>
						<tr>
						   	<td colspan="3">
						   		<fieldset style="border-top:1px solid #ccc;padding-left:10px;" id ="fs_hide">
								  	<legend id="l_toggle" style="cursor: pointer;">展开>></legend>
								  	<ol style="margin-left:20px; padding-left:20px; list-style:decimal; display: none" id ="ol_hide">
								  		<li style="float:left; margin-right: 14%;">
								  			<label for="jd_in"><strong class="fb">家电入驻：</strong></label>
									   		<select id="jd_in" class="easyui-combobox" name="jd_in" style="width:135px" data-options="editable:false">
												<option value="">-请选择-</option>
												<option value="0">是</option>
												<option value="1">否</option>
									   		</select>
								  		</li>
								  		<li style="float:left; margin-right: 13%;">
								  			<label for="konka_in"><strong class="fb">康佳入驻：</strong></label>
									   		<select id="konka_in" class="easyui-combobox" name="konka_in" style="width:135px" data-options="editable:false">
												<option value="">-请选择-</option>
												<option value="0">是</option>
												<option value="1">否</option>
									   		</select>
								  		</li>
								  		<li style="padding-bottom: 5px">
								  			<label for="konka_rank"><strong class="fb">康佳排名：</strong></label>
									   		<select id="konka_rank" class="easyui-combobox" name="konka_rank" style="width:135px" data-options="editable:false">
												<option value="">-请选择-</option>
												<option value="1" >第1名</option>
												<option value="2" >第2名</option>
												<option value="3" >第3名</option>
												<option value="4" >第4名</option>
												<option value="5" >第5名</option>
												<option value="6" >第6名</option>
												<option value="7" >第7名</option>
												<option value="8" >第8名</option>
												<option value="9" >第9名</option>
												<option value="10" >第10名</option>
											</select>
								  		</li>
								  		<li style="float:left; margin-right: 14%">
								  			<label for="first_comp"><strong class="fb">第一对手：</strong></label>
						   					<select id="first_comp" class="easyui-combobox" name="first_comp" style="width:135px" data-options="editable:false">
												<option value="">-请选择-</option>
												<option value="海信">海信</option>
												<option value="TCL">TCL</option>
												<option value="创维">创维</option>
												<option value="海尔">海尔</option>
									   		</select>
								  		</li>
								  		<li style="float:left; margin-right: 13%">
								  			<label for="second_comp"><strong class="fb">第二对手：</strong></label>
						   					<select id="second_comp" class="easyui-combobox" name="second_comp" style="width:135px" data-options="editable:false">
												<option value="">-请选择-</option>
												<option value="海信">海信</option>
												<option value="TCL">TCL</option>
												<option value="创维">创维</option>
												<option value="海尔">海尔</option>
									   		</select>
								  		</li>
								  		<li style="padding-bottom: 5px">
								  			<label for="third_comp"><strong class="fb">第三对手：</strong></label>
						   					<select id="third_comp" class="easyui-combobox" name="third_comp" style="width:135px" data-options="editable:false">
												<option value="">-请选择-</option>
												<option value="海信">海信</option>
												<option value="TCL">TCL</option>
												<option value="创维">创维</option>
												<option value="海尔">海尔</option>
									   		</select>
								  		</li>
								  		<li style="float:left; margin-right: 10%">
								  			<label><strong class="fb">市场容量(金额)：</strong></label>
						   					<input class="easyui-validatebox" type="text" name="market_money_begin" id="market_money_begin" value="${market_money_begin }" style="width: 43px"></input>
						   					至
						   					<input class="easyui-validatebox" type="text" name="market_money_end" id="market_money_end" value="${market_money_end }" style="width: 43px"></input>万元
								  		</li>
								  		<li style="float:left; margin-right: 10%">
								  			<label><strong class="fb">市场容量(数量)：</strong></label>
						   					<input class="easyui-validatebox" type="text" name="market_num_begin" id="market_num_begin" value="${market_num_begin }" style="width: 41px"></input>
						   					至
						   					<input class="easyui-validatebox" type="text" name="market_num_end" id="market_num_end" value="${market_num_end }" style="width: 41px"></input>台
								  		</li>
								  		<li style="padding-bottom: 5px">
								  			<label><strong class="fb">市场占有率：</strong></label>
						   					<input class="easyui-validatebox" type="text" name="market_prop_begin" id="market_prop_begin" value="${market_prop_begin }" style="width: 45px"></input>
						   					至
						   					<input class="easyui-validatebox" type="text" name="market_prop_end" id="market_prop_end" value="${market_prop_end }" style="width: 45px"></input>%
								  		</li>
								  		<li style="float:left; margin-right: 13%">
								  			<label><strong class="fb">所辖村数：</strong></label>
						   					<input class="easyui-validatebox" type="text" name="t_num_begin" id="t_num_begin" value="${t_num_begin }" style="width: 52px"></input>
						   					至
						   					<input class="easyui-validatebox" type="text" name="t_num_end" id="t_num_end" value="${t_num_end }" style="width: 52px"></input>个
								  		</li>
								  		<li style="float:left; margin-right: 11%">
								  			<label><strong class="fb">人口：</strong></label>
						   					<input class="easyui-validatebox" type="text" name="human_num_begin" id="human_num_begin" value="${human_num_begin }" style="width: 69px"></input>
						   					至
						   					<input class="easyui-validatebox" type="text" name="human_num_end" id="human_num_end" value="${human_num_end }" style="width: 69px"></input>万人
								  		</li>
								  		<li style="padding-bottom: 5px">
								  			<label><strong class="fb">面积：</strong></label>
						   					<input class="easyui-validatebox" type="text" name="area_size_begin" id="area_size_begin" value="${area_size_begin }" style="width: 60px"></input>
						   					至
						   					<input class="easyui-validatebox" type="text" name="area_size_end" id="area_size_end" value="${area_size_end }" style="width: 60px"></input>平方公里
								  		</li>
								  		<li style="float:left; margin-right: 12%">
								  			<label><strong class="fb">GDP：</strong></label>
						   					<input class="easyui-validatebox" type="text" name="gdp_begin" id="gdp_begin" value="${gdp_begin }" style="width: 69px"></input>
						   					至
						   					<input class="easyui-validatebox" type="text" name="gdp_end" id="gdp_end" value="${gdp_end }" style="width: 69px"></input>万元
								  		</li>
								  	</ol>
								  </fieldset>
						   	</td>
				        </tr>
		      		</table>
		      	</form>
		  	</div>
		  	<div id="tb" style="height: auto;">
		  		<table width="100%">
		  			<tr>
		  				<!-- <td style="padding-left:15px">
		  					<a href="#" class="easyui-linkbutton" id="add" iconCls="icon-add">新   增</a>
		  				</td> -->
		  				<td align="right" style="padding-right:15px">
							<a href="#" class="easyui-linkbutton" id="search" iconCls="icon-search">查   询</a>
							&nbsp;
							<a href="#" class="easyui-linkbutton" id="exprot" iconCls="icon-redo">导   出</a>
		  				</td>
		  			</tr>
		  		</table>
			</div>
			<div class="rtabcont1" style="font-weight:700;margin-top: 0;margin-bottom: 0">
				当前查询结果客户数共<font color="red"><span id='kh_num'></span></font>个，门店/网点数共<font color="red"><span id='ag_num'></span></font>个，结算额共<font color="red"><span id='c_money'></span></font>万元，结算量共<font color="red"><span id='c_num'></span></font>台，销售额共<font color="red"><span id='s_money'></span></font>万元，销售量共<font color="red"><span id='s_num'></span></font>台。
		  	</div>
			<div id='tabdiv' style="margin:5px;height: 400px;width: 800">
				<table id="table" width="100%" border="0" cellspacing="0" cellpadding="0"></table> 
		  	</div>

</div>
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.easyui.min.js"></script>  
		<script type="text/javascript" src="${ctx}/commons/scripts/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=discuz"></script>
		<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/commons/scripts/public.js"></script> 
		<script type="text/javascript">
			
			//初始化
			$(function(){
				//获取模块id
				var mod_id = GetQueryString("mod_id");
				if(mod_id==''){
					mod_id = $("#mod_id").val();
				}
				
				//回传的搜索条件
				var area_name = GetFormString("queryStr","area_name");
				var province = GetFormString("queryStr","province");
				var city = GetFormString("queryStr","city");
				var country = GetFormString("queryStr","country");
				var town = GetFormString("queryStr","town");
				var p_index = GetFormString("queryStr","p_index");
				var t_type = GetFormString("queryStr","t_type");
				var t_status = GetFormString("queryStr","t_status");
				var dept_id = GetFormString("queryStr","dept_id");
				var modify_name = GetFormString("queryStr","modify_name");
				var modify_date = GetFormString("queryStr","modify_date");
				var jd_in = GetFormString("queryStr","jd_in");
				var konka_in = GetFormString("queryStr","konka_in");
				var konka_rank = GetFormString("queryStr","konka_rank");
				var first_comp = GetFormString("queryStr","first_comp");
				var second_comp = GetFormString("queryStr","second_comp");
				var third_comp = GetFormString("queryStr","third_comp");
				var market_money_begin = GetFormString("queryStr","market_money_begin");
				var market_money_end = GetFormString("queryStr","market_money_end");
				var market_num_begin = GetFormString("queryStr","market_num_begin");
				var market_num_end = GetFormString("queryStr","market_num_end");
				var market_prop_begin = GetFormString("queryStr","market_prop_begin");
				var market_prop_end = GetFormString("queryStr","market_prop_end");
				var t_num_begin = GetFormString("queryStr","t_num_begin");
				var t_num_end = GetFormString("queryStr","t_num_end");
				var gdp_begin = GetFormString("queryStr","gdp_begin");
				var gdp_end = GetFormString("queryStr","gdp_end");
				var human_num_begin = GetFormString("queryStr","human_num_begin");
				var human_num_end = GetFormString("queryStr","human_num_end");
				var area_size_begin = GetFormString("queryStr","area_size_begin");
				var area_size_end = GetFormString("queryStr","area_size_end");
				
				//初始化
				$.post('${ctx}/manager/admin/AreaFight.do?method=init&mod_id='+mod_id,function(result){
					$("#nav").text(result.local_str);
					
					//分公司初始化
					$('#dept_id').combobox({                 
						url:'${ctx}/manager/admin/CsAjax.do?method=getAjaxKonkaDeptList',  
					 	method:'post',              
					 	editable:false, //不可编辑状态                
					 	cache: false,               
					 	valueField:'DEPT_ID',                   
						textField:'DEPT_NAME'                   
					}); 
					var old_dept_id = GetFormString("queryStr","dept_id"); 
					if(old_dept_id!=''){
						$('#dept_id').combobox("setValue", old_dept_id);
					}else{
						$('#dept_id').combobox("setValue", result.dept_id);
					}
					
					//初始化城市列表
					function oneSelect(record){
						$('#city').combobox({  
							disabled:false,  
							url:'${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourListForUI&p_level=2&p_index='+$("#province").combobox('getValue'),  
							valueField:'p_index',                   
							textField:'p_name',
							editable:false, //不可编辑状态                
						 	cache: false,
							onLoadSuccess: function () { //加载完成后,设置选中第一个
								if(city==''){
					            	var val = $(this).combobox("getData");
									if(val.length>0){
						            	$(this).combobox("select", val[0].p_index);
									}
								}else{
									$(this).combobox("select", city);
								}
								//secondSelect(record);
							},
							onSelect:function(record){  
								secondSelect(record);
							}
						});
					}
					
					//初始化县、区列表
					function secondSelect(record){
						$("#country").combobox({  
							disabled:false,  
							url:'${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourListForUI&p_level=3&p_index='+$("#city").combobox('getValue'),  
							valueField:'p_index',                   
							textField:'p_name',
							editable:false, //不可编辑状态                
						 	cache: false,
							onLoadSuccess: function () { //加载完成后,设置选中第一个
								if(country==''){
					            	var val = $(this).combobox("getData");
					            	if(val.length>0){
						            	$(this).combobox("select", val[0].p_index);
									}
								}else{
									$(this).combobox("select", country);
								}
				            	//thirdSelect(record);
							},
							onSelect:function(record){  
								thirdSelect(record);
							}
						});
					}
					
					//初始化乡镇列表
					function thirdSelect(record){
						$("#town").combobox({  
							disabled:false,  
							url:'${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourListForUI&p_level=4&p_index='+$("#country").combobox('getValue'),  
							valueField:'p_index',                   
							textField:'p_name',
							editable:false, //不可编辑状态                
						 	cache: false,
							onLoadSuccess: function () { //加载完成后,设置选中第一个
								if(town==''){
					            	var val = $(this).combobox("getData");
					            	if(val.length>0){
						            	$(this).combobox("select", val[0].p_index);
									}
								}else{
					            	$(this).combobox("select", town);
								}
							}
						});
					}
					
					//省份初始化
					$('#province').combobox({                 
						url:'${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourListForUI&p_index=0&p_level=1',  
					 	method:'post',              
					 	editable:false, //不可编辑状态                
					 	cache: false,               
					 	valueField:'p_index',                   
						textField:'p_name',
						onLoadSuccess: function () { //加载完成后,设置选中第一个
							if(province==''){
				            	var val = $(this).combobox("getData");
				            	$(this).combobox("select", val[0].p_index);
							}else{
								$(this).combobox("select", province);
							}
						},
						onSelect:function(record){  
							oneSelect(record);
						}
					});

					//显示原条件
					$("#area_name").combobox("setValue",area_name);
					$("#p_index").val(p_index);
					$("#t_type").combobox("setValue",t_type);
					$("#t_status").combobox("setValue",t_status);
					$("#dept_id").combobox("setValue",dept_id);
					$("#modify_name").val(modify_name);
					$("#modify_date").val(modify_date);
					if(jd_in!=''||konka_in!=''||konka_rank!=''||first_comp!=''||second_comp!=''||third_comp!=''||market_money_begin!=''||market_money_end!=''||
					   market_num_begin!=''||market_num_end!=''||market_prop_begin!=''||market_prop_end!=''||t_num_begin!=''||t_num_end!=''||
					   gdp_begin!=''||gdp_end!=''||human_num_begin!=''||human_num_end!=''||area_size_begin!=''||area_size_end!=''){
						document.getElementById("l_toggle").innerText= "收起<<";
						$("#ol_hide").toggle();
						$("#fs_hide").toggleClass("filed_border");
						window.parent.resizeFrameHeight('mainFrame', 3);
						
						$("#jd_in").combobox("setValue",jd_in);
						$("#konka_in").combobox("setValue",konka_in);
						$("#konka_rank").combobox("setValue",konka_rank);
						$("#first_comp").combobox("setValue",first_comp);
						$("#second_comp").combobox("setValue",second_comp);
						$("#third_comp").combobox("setValue",third_comp);
						$("#market_money_begin").val(market_money_begin);
						$("#market_money_end").val(market_money_end);
						$("#market_num_begin").val(market_num_begin);
						$("#market_num_end").val(market_num_end);
						$("#market_prop_begin").val(market_prop_begin);
						$("#market_prop_end").val(market_prop_end);
						$("#t_num_begin").val(t_num_begin);
						$("#t_num_end").val(t_num_end);
						$("#gdp_begin").val(gdp_begin);
						$("#gdp_end").val(gdp_end);
						$("#human_num_begin").val(human_num_begin);
						$("#human_num_end").val(human_num_end);
						$("#area_size_begin").val(area_size_begin);
						$("#area_size_end").val(area_size_end);
					}
					
					
					//表格初始化
					$("#table").datagrid({
						title: '区域作战分析表',
						url:'${ctx}/manager/admin/AreaFight.do?method=queryList',
						method: 'post',
						queryParams:{
							area_name: area_name,
							province: province,
							city: city,
							country: country,
							town: town,
							p_index: p_index,
							t_type: t_type,
							t_status: t_status,
							dept_id: dept_id,
							modify_name: modify_name,
							modify_date: modify_date,
							jd_in: jd_in,
							konka_in: konka_in,
							konka_rank: konka_rank,
							first_comp: first_comp,
							second_comp: second_comp,
							third_comp: third_comp,
							market_money_begin: market_money_begin,
							market_money_end: market_money_end,
							market_num_begin: market_num_begin,
							market_num_end: market_num_end,
							market_prop_begin: market_prop_begin,
							market_prop_end: market_prop_end,
							t_num_begin: t_num_begin,
							t_num_end: t_num_end,
							gdp_begin: gdp_begin,
							gdp_end: gdp_end,
							human_num_begin: human_num_begin,
							human_num_end: human_num_end,
							area_size_begin: area_size_begin,
							area_size_end: area_size_end
						},
						fitColumns: false,
					    onLoadError:function(){
					    	$.messager.alert('温馨提示','数据请求失败!','error');  
					    },
					    onLoadSuccess:function(data){
					    	if(data.total==0){
					    		$("#kh_num").text("0");
								$("#ag_num").text("0");
								$("#c_money").text("0");
								$("#c_num").text("0");
								$("#s_money").text("0");
								$("#s_num").text("0");
					    	}else{
						    	$("#kh_num").text(commafy(data.tal.KH_TOTAL));
								$("#ag_num").text(commafy(data.tal.AGENT_TOTAL));
								$("#c_money").text(commafy(data.tal.C_MONEY));
								$("#c_num").text(commafy(data.tal.C_NUM));
								$("#s_money").text(commafy(data.tal.S_MONEY));
								$("#s_num").text(commafy(data.tal.S_NUM));
					    	}
					    },
					    loadMsg:'正在拼命加载中，请稍后。。。。',
					    remoteSort:false,
					    pagination:true,
					    rownumbers:true,
					    columns:[[
					     {title:'分公司',field:'DEPT_NAME',width:60,editor:'text',sortable:'true',align:'center'},
					     {title:'区域',field:'AREA_NAME',width:40,editor:'text',sortable:'true',align:'center'},
					     {title:'省',field:'PROVINCE',width:80,editor:'text',sortable:'true',align:'center'},
					     {title:'市',field:'CITY',width:80,editor:'text',sortable:'true',align:'center'},
					     {title:'县(区)',field:'COUNTRY',width:80,editor:'text',sortable:'true',align:'center'},
					     {title:'乡镇街',field:'TOWN',width:80,editor:'text',sortable:'true',align:'center',
					    	 formatter: function(value,row,index){
					    		 var queryStr = encodeURI(encodeURI(row.queryStr));
					    		 return "<span style='cursor:pointer;' class='fblue' onclick='doNeedMethod(null,\"${ctx}/manager/admin/AreaFight.do\", \"edit\",\""+mod_id+"\",\"op_type=view&tp_index="+row.P_INDEX+"&"+queryStr+"\",\"fm\")'>"+value+"</span>";
					     	 }
					     },
					     {title:'乡镇街编码',field:'P_INDEX',width:90,editor:'text',sortable:'true',align:'center'},
					     {title:'类型',field:'T_TYPE',width:60,editor:'text',sortable:'true',align:'center',
					    	formatter: function(value,row,index){
								if(value=='1'){
									return "乡镇";
								}else if(value=='2'){
									return "街道";
								}else if(value=='3'){
									return "开发区";
								}else{
									return "<span style='color:#CCC;'>未指定</span>";
								}
					    	}	 
					     },
					     {title:'所辖村数(个)',field:'T_NUM',width:90,editor:'text',sortable:'true',align:'center'},
					     {title:'GDP(万元)',field:'GDP',width:80,editor:'text',sortable:'true',align:'center'},
					     {title:'人口(万人)',field:'HUMAN',width:80,editor:'text',sortable:'true',align:'center'},
					     {title:'面积(平方公里)',field:'AREA_SIZE',width:100,editor:'text',sortable:'true',align:'center'},
					     {title:'市场容量(数量：台)',field:'MARKET_NUM',width:110,editor:'text',sortable:'true',align:'center'},
					     {title:'市场容量(金额：万元)',field:'MARKET_MONEY',width:140,editor:'text',sortable:'true',align:'center'},
					     {title:'家电入驻',field:'JD_IN',width:60,editor:'text',sortable:'true',align:'center',
					    	 formatter: function(value,row,index){
					    		 if(value=='0'){
					    			 return '是';
					    		 }else if(value=='1'){
					    			 return '否';
					    		 }else{
					    			 return "<span style='color:#CCC;'>未指定</span>";
					    		 }
					    	 }
					     },
					     {title:'康佳入驻',field:'KONKA_IN',width:60,editor:'text',sortable:'true',align:'center',
					    	 formatter: function(value,row,index){
					    		 if(value=='0'){
					    			 return '是';
					    		 }else if(value=='1'){
					    			 return '否';
					    		 }else{
					    			 return "<span style='color:#CCC;'>未指定</span>";
					    		 }
					    	 }	 
					     },
					     {title:'客户数',field:'KH_NUM',width:60,editor:'text',sortable:'true',align:'center',
					    	 formatter: function(value,row,index){
					    		var queryStr = encodeURI(encodeURI(row.queryStr));
						    	return "<span style='cursor:pointer;' class='fblue' onclick='doNeedMethod(null,\"${ctx}/manager/admin/AreaFight.do\", \"edit\",\""+mod_id+"\",\"op_type=view&tp_index="+row.P_INDEX+"&"+queryStr+"\",\"fm\")'>"+value+"</span>";
						     }
					     },
					     {title:'门店/网点数',field:'AGENT_NUM',width:60,editor:'text',sortable:'true',align:'center',
					    	 formatter: function(value,row,index){
					    		 var queryStr = encodeURI(encodeURI(row.queryStr));
					    		 return "<span style='cursor:pointer;' class='fblue' onclick='doNeedMethod(null,\"${ctx}/manager/admin/AreaFight.do\", \"edit\",\""+mod_id+"\",\"op_type=view&tp_index="+row.P_INDEX+"&"+queryStr+"\",\"fm\")'>"+value+"</span>";
						     }
					     },
					     {title:'本年结算额(万元)',field:'CCOUNT_MONEY',width:100,editor:'text',sortable:'true',align:'center'},
					     {title:'本年结算量(台)',field:'CCOUNT_NUM',width:100,editor:'text',sortable:'true',align:'center'},
					     {title:'本年销售额(万元)',field:'SALE_MONEY',width:100,editor:'text',sortable:'true',align:'center'},
					     {title:'本年销售量(台)',field:'SALE_NUM',width:100,editor:'text',sortable:'true',align:'center'},
					     {title:'康佳排名',field:'KONKA_RANK',width:70,editor:'text',sortable:'true',align:'center',
					    	 formatter: function(value,row,index){
					    		 if(value!=null){
					    			 return '第'+value+'名';
					    		 }else{
					    			 return "<span style='color:#CCC;'>未指定</span>";
					    		 }
					    	 }	
					     },
					     {title:'市场占有率',field:'MARKET_RATE',width:70,editor:'text',sortable:'true',align:'center'},
					     {title:'第一对手',field:'FIRST_COMP',width:60,editor:'text',sortable:'true',align:'center'},
					     {title:'第二对手',field:'SECOND_COMP',width:60,editor:'text',sortable:'true',align:'center'},
					     {title:'第三对手',field:'THIRD_COMP',width:60,editor:'text',sortable:'true',align:'center'},
					     {title:'康佳未进入原因',field:'NOT_IN_REASON',width:100,editor:'text',sortable:'true',align:'center'},
					     {title:'预计进入日期',field:'IN_DATE',width:90,editor:'text',sortable:'true',align:'center'},
					     {title:'乡镇状态',field:'T_STATUS',width:60,editor:'text',sortable:'true',align:'center',
					    	 formatter: function(value,row,index){
					    		 if(value=='0'){
					    			 return '正常';
					    		 }else if(value=='1'){
					    			 return '撤销';
					    		 }else{
					    			 return "<span style='color:#CCC;'>未指定</span>";
					    		 }
					    	 }	
					     },
					     {title:'维护人',field:'MODIFY_NAME',width:60,editor:'text',sortable:'true',align:'center'},
					     {title:'维护日期',field:'MODIFY_DATE',width:80,editor:'text',sortable:'true',align:'center'},
					     {title:'操作',field:'OPER',width:40,editor:'text',sortable:'true',align:'center',
					    	 formatter: function(value,row,index){
					    		 var queryStr = encodeURI(encodeURI(row.queryStr));
					    	 	return "<span style='cursor:pointer;' class='fblue' onclick='doNeedMethod(null,\"${ctx}/manager/admin/AreaFight.do\", \"edit\",\""+mod_id+"\",\"op_type=edit&tp_index="+row.P_INDEX+"&"+queryStr+"\",\"fm\")'>修改</span>";
					     	 }	 
					     }
					    ]],
					    onHeaderContextMenu: function(e, field){  
		                	e.preventDefault();  
		                    if (!$('#tmenu').length){  
		                    	createColumnMenu();  
		                  	}  
		                 	$('#tmenu').menu('show', {  
		                    	left:e.pageX,  
		                       	top:e.pageY  
		                   	});  
		               	}
					});
					
					// 得到datagrid的pager对象，初始化分页栏
					var pager = $('#table').datagrid('getPager');  
					pager.pagination({    
				   		showPageList:false,    
				    	pageSize:10,  
					    onBeforeRefresh:function(pageNumber, pageSize){
					    	$(this).pagination({pageNumber:$(".pagination-num").val()});
					     	$(this).pagination('loading');
					    }
					});
				},'json');
				 
				//查询按钮绑定事件 
				$("#search").bind('click',function(){
					$('#table').datagrid('load',{
						area_name: $("#area_name").combobox("getValue"),
						province: $("#province").combobox("getValue"),
						city: $("#city").combobox("getValue"),
						country: $("#country").combobox("getValue"),
						town: $("#town").combobox("getValue"),
						p_index: $("#p_index").val(),
						t_type: $("#t_type").combobox("getValue"),
						t_status: $("#t_status").combobox("getValue"),
						dept_id: $("#dept_id").combobox("getValue"),
						modify_name: $("#modify_name").val(),
						modify_date: $("#modify_date").val(),
						jd_in: $("#jd_in").combobox("getValue"),
						konka_in: $("#konka_in").combobox("getValue"),
						konka_rank: $("#konka_rank").combobox("getValue"),
						first_comp: $("#first_comp").combobox("getValue"),
						second_comp: $("#second_comp").combobox("getValue"),
						third_comp: $("#third_comp").combobox("getValue"),
						market_money_begin: $("#market_money_begin").val(),
						market_money_end: $("#market_money_end").val(),
						market_num_begin: $("#market_num_begin").val(),
						market_num_end: $("#market_num_end").val(),
						market_prop_begin: $("#market_prop_begin").val(),
						market_prop_end: $("#market_prop_end").val(),
						t_num_begin: $("#t_num_begin").val(),
						t_num_end: $("#t_num_end").val(),
						gdp_begin: $("#gdp_begin").val(),
						gdp_end: $("#gdp_end").val(),
						human_num_begin: $("#human_num_begin").val(),
						human_num_end: $("#human_num_end").val(),
						area_size_begin: $("#area_size_begin").val(),
						area_size_end: $("#area_size_end").val()
			   		}); 
				});
				
				//导出
				$("#exprot").bind('click',function(){
					$.messager.confirm('温馨提示', '是否导出数据？', function(r){  
						if (r){  
							//CNZZ统计代码
							var str = '&area_name='+$("#area_name").combobox("getValue")+
								'&province='+$("#province").combobox("getValue")+
								'&city='+$("#city").combobox("getValue")+
								'&country='+$("#country").combobox("getValue")+
								'&town='+$("#town").combobox("getValue")+
								'&p_index='+$("#p_index").val()+
								'&t_type='+$("#t_type").combobox("getValue")+
								'&t_status='+$("#t_status").combobox("getValue")+
								'&dept_id='+$("#dept_id").combobox("getValue")+
								'&modify_name='+$("#modify_name").val()+
								'&modify_date='+$("#modify_date").val()+
								'&jd_in='+$("#jd_in").combobox("getValue")+
								'&konka_in='+$("#konka_in").combobox("getValue")+
								'&konka_rank='+$("#konka_rank").combobox("getValue")+
								'&first_comp='+$("#first_comp").combobox("getValue")+
								'&second_comp='+$("#second_comp").combobox("getValue")+
								'&third_comp='+$("#third_comp").combobox("getValue")+
								'&market_money_begin='+$("#market_money_begin").val()+
								'&market_money_end='+$("#market_money_end").val()+
								'&market_num_begin='+$("#market_num_begin").val()+
								'&market_num_end='+$("#market_num_end").val()+
								'&market_prop_begin='+$("#market_prop_begin").val()+
								'&market_prop_end='+$("#market_prop_end").val()+
								'&t_num_begin='+$("#t_num_begin").val()+
								'&t_num_end='+$("#t_num_end").val()+
								'&kh_num_begin='+$("#kh_num_begin").val()+
								'&kh_num_end='+$("#kh_num_end").val()+
								'&agent_num_begin='+$("#agent_num_begin").val()+
								'&agent_num_end='+$("#agent_num_end").val()+
								'&gdp_begin='+$("#gdp_begin").val()+
								'&gdp_end='+$("#gdp_end").val()+
								'&human_num_begin='+$("#human_num_begin").val()+
								'&human_num_end='+$("#human_num_end").val()+
								'&area_size_begin='+$("#area_size_begin").val()+
								'&area_size_end='+$("#area_size_end").val();
							$("#fm").attr("action", "${ctx}/manager/admin/AreaFight.do?method=exportData"+str);
				    		$("#fm").submit();
						}  
					});
				});
				
				//展开收起更多查询条件
				$("#l_toggle").click(function(){
					if("展开>>"==this.innerText){
						this.innerText = "收起<<";
					}else{
						this.innerText = "展开>>";
					}
					$("#ol_hide").toggle();
					$("#fs_hide").toggleClass("filed_border");
					window.parent.resizeFrameHeight('mainFrame', 3);
				});
			});
		</script>
	<jsp:include page="/__analytics.jsp" />
	</body>
</html>

