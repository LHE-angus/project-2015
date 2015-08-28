<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>康佳渠道信息管理系统</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/styles/frame/css/css.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/styles/frame/css/global.css" />
<script language="javascript" type="text/javascript"
	src="${ctx}/styles/frame/js/jquery.js"></script>
<script language="javascript" type="text/javascript"
	src="${ctx}/styles/frame/js/jquery.flot.js"></script>
<script language="javascript" type="text/javascript"
	src="${ctx}/styles/frame/js/jquery.flot.pie.js"></script>
<script type="text/javascript" src="${ctx}/manager/scripts/charts.js"></script>
<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="${ctx}/styles/frame/js/excanvas.min.js"></script><![endif]-->
<style type="text/css">
#placeholder {
	width: 80px;
}

.demo-container {
	width: 80px;
	height: 80px;
	padding: 0px;
	margin: 0px;
	border: 0px;
	background: #fff;
	margin-left: auto;
	margin-right: auto;
	padding-top: 5px;
}

.demo-placeholder {
	width: 100%;
	height: 100%;
	font-size: 14px;
	line-height: 1.2em;
}

</style>
</head>
<body>
	<c:set var="role_id_eq_188" value="${false}" />
	<c:set var="role_id_ne_188" value="${false}" />
	<c:set var="role_id_eq_30" value="${false}" />
	<c:forEach var="cur" items="${peRoleUserList}" varStatus="vs">
		<c:if test="${cur.role_id eq 188}">
			<c:set var="role_id_eq_188" value="${true}" />
		</c:if>
		<c:if test="${cur.role_id eq 30}">
			<c:set var="role_id_eq_30" value="${true}" />
		</c:if>
		<c:if test="${cur.role_id ne 188}">
			<c:set var="role_id_ne_188" value="${true}" />
		</c:if>
	</c:forEach>
	<div class="m_l">
			<div style="width: 99%">

				<div class="k_tongzhi111">
					<span style="float: right; font-size: 12px;"></span><font class="">当月截止到昨日总体情况</font>
				</div>
				<table width="100%">
					<tr>
						<td>
							<div class="t1" style="position: relative;margin-left: 15px;">
								<div style="margin: 5px; margin-left: 10px; height: 89px;">
									<div class="demo-container">
										<div id="placeholder" class="demo-placeholder"><img src="${ctx }/styles/images/loading.gif" /></div>
									</div>
									<div
										style="position: absolute; top: 5px; left: 40px; width: 80px; height: 80px; line-height: 80px; text-align: center; color: #888;">
										<p>
											<span id="wc_jd"></span>
										</p>
									</div>
								</div>
								<div style="margin: 10px; margin-top: 10px; color: #4b4b4b;">任务完成进度</div>
							</div>
						</td>
							<td>
								<div class="t1">
									<div class="f_dangnian">
										<p style="color: #888;">
											<span id="s_dy_xse"></span>万元
										</p>
									</div>
									<div style="margin: 10px; margin-top: 10px; color: #4b4b4b;">当月销售额</div>
								</div>
							</td>
							<td>
								<div class="t1">
									<div class="lingshouliang">
										<p style="color: #888;">
											<span id="s_dy_lsl"></span>台
										</p>
									</div>
									<div style="margin: 10px; margin-top: 10px; color: #4b4b4b;">零售量</div>
								</div>
							</td>
							<td>
								<div class="t1">
									<div class="lianshoue">
										<p style="color: #888;">
											<span id="s_dy_lse"></span>万元
										</p>
									</div>
									<div style="margin: 10px; margin-top: 10px; color: #4b4b4b;">零售额</div>
								</div>
							</td>
					</tr>
				</table>
				<div class="clear"></div>
				<div></div>
			</div>
			<div class="k_tongzhi111">
				<span><a
					href="${ctx}/manager/admin/KonkaR3OrderRank.do?mod_id=109080">更多...</a></span><span><a
					href="javascript:fusionchartsShow('true');">后十名&nbsp;&nbsp;</a></span><span><a
					href="javascript:fusionchartsShow('');">前十名&nbsp;&nbsp;</a></span> <font
					class="">当月截止到昨日分公司任务进度</font>
			</div>
			<div class="right_contcont2"
				style="margin-bottom: 24px; height: 350px;">
				<div id="chartdiv"><img src="${ctx }/styles/images/loading.gif" />&nbsp;正在努力加载数据中。。。</div>
			</div>
			<div class="clear"></div>
		</div> 
	<c:if test="${!role_id_eq_188 or role_id_ne_188}">
		<div class="m_r">
			<div class="k_tongzhi">
					<span style="float: right; font-size: 12px;"><a
						href="${ctx}/manager/chengduoa/SelfEventCenter.do?method=list&mod_id=951000">更多</a></span>
				<font class="">待办事项</font>
			</div>
			<div class="right_contcont2" style="margin-top: 0px">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="rtab5" id="todo_table1">
					<tr>
						<th align="center">待办事项</th>
						<th width="24%">信息来源</th>
						<th align="center" width="20%">时间</th>
						<th align="center" width="15%">操作</th>
					</tr>

				</table>
				<div></div>
			</div>
			<!-- 待审订单 begin  WangKunLin 2014-06-10-->
				<div class="k_tongzhi">
				
					<span style="float: right; font-size: 12px;"><a
						href="${ctx}/manager/admin/KonkaOrderAudit.do?mod_id=205600">更多</a></span>
				
				<font class="">待审订单</font>
			</div>
			<div class="right_contcont2" style="margin-top: 0px">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="rtab5" id="todo_table2">
					<tr>
						<th width="30%" align="center">订单流水号</th>
						<th width="15%">分公司</th>
						<th width="30%">客户名称</th>
					<th align="center" width="15%">下单时间</th>
						<th align="center" width="10%">操作</th>
					</tr>
				</table>
				<div></div>
			</div>
			<div class="k_tongzhi">
					<span style="float: right; font-size: 12px;"><a
						href="${ctx}/manager/chengduoa/SsuedDocument.do?method=list&mod_id=952000">更多</a></span>
				<font class="">下发文件</font>
			</div>
			<div class="right_contcont2" style="margin-top: 0px">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="rtab5" id="down_file">
					<tr>
						<th align="right">标题</th>
						<th width="24%">信息来源</th>
						<th width="20%">时间</th>
						<th width="15%">操作</th>
					</tr>
				</table>
				<div></div>
			</div>
			<c:if test="${role_id_eq_30}">
				<div class="k_tongzhi111">
					<font class="">分公司客户情况统计表</font>
				</div>
				<div class="right_contcont2">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="rtab5">
						<tr>
							<th nowrap="nowrap">分公司代码</th>
							<th nowrap="nowrap">名称</th>
							<th nowrap="nowrap">客户总数</th>
							<th nowrap="nowrap">未分配数量</th>
						</tr>
						<c:forEach var="cur" items="${staticByFGSList}" varStatus="vc">
							<tr
								style="background-color:${cur.map.unassign_count < cur.map.total_count ? 'yellow' : ''};">
								<td>${cur.map.dept_sn}</td>
								<td>${cur.map.dept_name}</td>
								<td>${cur.map.total_count}</td>
								<td><c:if
										test="${cur.map.unassign_count < cur.map.total_count}">
										${cur.map.unassign_count}
									</c:if> <c:if test="${cur.map.unassign_count eq cur.map.total_count}">
            			${cur.map.unassign_count}
           		 </c:if></td>
							</tr>
						</c:forEach>
					</table>
					<div></div>
				</div>
			</c:if>
		</div>
	</c:if>
	<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//异步加载图表数据
	total_data();
	wait_do();

    //当月截止到昨天的总体情况
	function total_data(){
		$.ajax({
			type: "POST",
			cache : false,
			url: '${ctx}/manager/admin/Frames3.do',
			data : "method=total_data",
			dataType: "json",
			failure:function(){
				alert("数据请求失败！");
			},
			success: function(data){
				if(data.is_admin_data){
					//总体情况
					$("#s_dy_xse").text((Number(data.dy_js_money)).toFixed(2));
					$("#s_dy_lsl").text(data.dy_sail_num);
					$("#s_dy_lse").text((Number(data.dy_sail_money)).toFixed(2));
					$("#wc_jd").text(data.rw_wcl > 0 ? parseInt(data.rw_wcl) : '-');
					var placeholder = $("#placeholder");

					var ratio = Number(data.rw_wcl); // 单位 : %
					$.plot(placeholder, [{'data' : ratio}, {'data' : 100 - ratio}],
							{
								series: {
									pie: {
										innerRadius: 0.4,
										show: true
									}
								}
							});
				}
				var myDate = new Date();
				var year = myDate.getFullYear();    //获取完整的年份(4位,1970-????)
				var month = myDate.getMonth() + 1;
				fusionchartsShow(data.is_admin_data,year,month);
			}
		})
	}

	//当月截止到昨日分公司任务
	function fusionchartsShow(is_admin_data,year,month){
		var last_rank = "false";
		var this_caption = "前十名"
		if(last_rank == 'true'){
			this_caption = "后十名"
		}
		$.ajax( {
			type : "POST",
			cache : false,
			url : "${ctx}/webservice/KonkaR3RankInterface.do",
			data : "method=getKonkaR3SellToJson&year="+year+"&month="+month+"&last_rank="+last_rank,
			dataType: "json",
			error : function(data) { /* alert("Sorry! Error Code :" + data.status); */ },
			success : function(data) {
			if (data){
			  if(data.status == '0'){
				//任务进度
				var flash = "MSStackedColumn2DLineDY.swf";
				var chart = new FusionCharts("${ctx}/scripts/fusioncharts/swf/"+flash, "ChartId", "100%", "320", "0", "0");

				var xmldata = "<chart palette='2' caption='" + this_caption + "' shownames='1' showvalues='1' decimals='2' formatNumberScale='0' numberSuffix='万元' ";
				xmldata += "sNumberSuffix='%' setAdaptiveSYMin='1' showPlotBorder='1' showBorder='0' bgColor='FFFFFF' >";
				xmldata += "<categories>";
				var xml_data_set1 = "<dataset><dataset seriesName='任务销售额' showValues='0' color='F1683C'>";
				var xml_data_set2 = "<dataset><dataset seriesName='实际销售额' showValues='0' color='3366FF'>";
				var xml_data_set3 = "<lineSet seriesName='任务完成%' showValues='1' color='FFFF31' lineThickness='4'>";
				for(var i=0; i<data.list.length; i++){
					xmldata += "<category label='"+data.list[i].dept_name+"' />";
					xml_data_set1 +="<set value='"+data.list[i].rw_money+"' />";
					xml_data_set2 +="<set value='"+data.list[i].all_price+"' />";
					xml_data_set3 +="<set value='"+data.list[i].sale+"' />";
				}
				xmldata += "</categories>";
				xml_data_set1 +="</dataset></dataset>";
				xml_data_set2 +="</dataset></dataset>";
				xml_data_set3 +="</lineSet>";
				xmldata += xml_data_set1;
				xmldata += xml_data_set2;
				xmldata += xml_data_set3;
				xmldata += "</chart>";

				chart.setDataXML(xmldata);
				chart.render("chartdiv");
			  }
				//总体情况
				if(!is_admin_data){
					$("#s_dy_xse").text((Number(data.total_price)).toFixed(2));
					$("#s_dy_lsl").text(data.sell_num);
					$("#s_dy_lse").text((Number(data.sell_money)/10000).toFixed(2));
					$("#wc_jd").text(data.rw_sale > 0 ? parseInt(data.rw_sale) : '-');
					var placeholder = $("#placeholder");

					var ratio = Number(data.rw_sale); // unit : %
					$.plot(placeholder, [{'data' : ratio}, {'data' : 100 - ratio}],
							{
								series: {
									pie: {
										innerRadius: 0.4,
										show: true
									}
								}
							});
				}
			  }
			}
		});
	}

	//待办事项等内容
	function wait_do(){
		$.ajax( {
			type : "POST",
			cache : false,
			url : "${ctx}/manager/admin/Frames3.do",
			data : "method=file_list",
			dataType: "json",
			error : function(data) { /* alert("Sorry! Error Code :" + data.status); */ },
			success : function(data) {
				var table1 = $("#todo_table1");
				if(data.todo_list.length>0){
					jQuery.each(data.todo_list,function(index,tr){
						var tt = "<tr id='tr_"+tr.id+"'>" +
								"<td style='padding-left:3px;padding-right: 0px'>"+tr.map.title+"</td>" +
								"<td style='padding-left:3px;padding-right: 0px'>"+tr.fromPerson+"</td>" +
								"<td style='padding-left:3px;padding-right: 0px'>"+tr.map.add_date+"</td>" +
								"<td style='padding-left:3px;padding-right: 0px'>"+tr.eventDo+"</td></tr>";
						table1.append(tt);
					});
				}
				for(var i=0;i<5-data.todo_list.length;i++){
					table1.append("<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
				}

				var table2 = $("#todo_table2");
				if(data.auditentityList.length>0){
					jQuery.each(data.auditentityList,function(index,tr){
						var tt = "<tr id='tr_"+tr.id+"'>" +
								"<td style='padding-left:3px;padding-right: 0px'><a href='${ctx}/manager/admin/KonkaOrderSearch.do?method=view&order_id="+tr.id+"&mod_id=205600' style='color:blue;text-decoration:none;'>"+tr.trade_index+"</a></td>" +
								"<td style='padding-left:3px;padding-right: 0px'>"+tr.map.fgsName+"</td>" +
								"<td style='padding-left:3px;padding-right: 0px'>"+tr.user_shop_name+"</td>" +
								"<td style='padding-left:3px;padding-right: 0px'>"+tr.map.order_date+"</td>"+
								"<td style='padding-left:3px;padding-right: 0px'><span style='cursor:pointer;color:blue;' class='fblue' onclick=\"doNeedMethod(null, '${ctx}/manager/admin/KonkaOrderAudit.do', 'audit','id="+tr.id+"&mod_id=205600')\">审核</span></td></tr>";
						table2.append(tt);
					});
				}
				for(var i=0;i<5-data.auditentityList.length;i++){
					table2.append("<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
				}

				var table3 = $("#down_file");
				if(data._entityList.length>0){
					jQuery.each(data._entityList,function(index,tr){
						var tt = "<tr id='tr_"+tr.id+"'>" +
								"<td style='padding-left:3px;padding-right: 0px'>";

						if('notice'==tr.mod_type){
							tt += "<span style='cursor: pointer; color: blue;' onclick=\"javascript:window.location.href='${ctx}/manager/chengduoa/DocIssue.do?method=view&id="+tr.id+"&mod_id=952000'\">"+tr.title+"</span></td>";
						}else{
							tt += "<span style='cursor: pointer; color: blue;' onclick='view_and_print("+tr.id+");'>"+tr.title+"</span></td>";
						}
						tt += "<td style='padding-left:3px;padding-right: 0px'>"+tr.pass_man_name+"/"+tr.part_name+"</td><td style='padding-left:3px;padding-right: 0px'>"+tr.map.add_date+"</td>";
						if('notice'==tr.mod_type){
							tt += "<td style='padding-left:3px;padding-right: 0px'><a style='cursor: pointer; color: blue;' onclick=\"javascript:window.location.href='${ctx}/manager/chengduoa/DocIssue.do?method=view&id="+tr.id+"&mod_id=952000'\">查看</a></td>";
						}else{
							tt += "<td style='padding-left:3px;padding-right: 0px'><a style='cursor: pointer; color: blue;' onclick='view_and_print("+tr.id+")'>查看</a></td>";
						}
						table3.append(tt);
					});
				}
				for(var i=0;i<5-data._entityList.length;i++){
					table3.append("<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
				}
			}
		})
	}
});


function view_and_print(id) {
    window.showModalDialog("${ctx}/manager/oa/AuditIngFiles.do?azaz=" + Math.random() + "&method=view&id=" +id, window, "dialogWidth:800px;status:no;dialogHeight:600px");
}
//]]></script>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
	<jsp:include page="/__analytics.jsp" />
</body>
</html>