<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>平板销售完成情况 </title>

<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />

<style type="text/css">
	.tips_div { background-color: yellow; border: 1px ;color: red ; height:100px; border-style: solid; font-size:16pt;font-weight:bold; text-align:center;  }
</style>
 
</head> 
<body>
	<div class="oartop" style="width:100% ">
		当前位置: 决策分析>>任务达成分析>>平板销售完成情况
	</div>
	
 <h1 align="center" style="padding-top: 20px;font-size: 22px;">${year}年${month}月平板内销各机型销售完成情况  </h1>
	<div>
		<div class="rtabcont1" id="tip_div1">
			<html-el:form action="/KonkaModelXSReport.do" >
			<html-el:hidden property="method" value="list" />
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
					<tr>
						<td><input id="sync_btn" type="button" id="s_button" class="but8" value="同步数据" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="search_btn" type="submit" class="bgSearch" id="s_button" value="搜 索" /></td>
					</tr>
				</table>
			</html-el:form>
		</div>
		
		<div class="rtabcont1" id="tip_div2">
			<P ><MARQUEE width="300px" direction="right"><span style="color:red;font-weight: bold;font-size: 16">正在同步...请稍候!</span></MARQUEE></P>
		</div>
	<div>
		<div class="rtabcont1" style="overflow-x:auto;">
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass" id="mytable">
					<tr>
						<td rowspan="2" style="text-align: center ; font-weight:bold; background-color: #FFD39B;">&nbsp;&nbsp;机型 &nbsp;&nbsp;</td>
						<td rowspan="1" style="text-align: center ; font-weight:bold; background-color: #A4D3EE;">进</td>
						<td colspan="6" style="text-align: center ; font-weight:bold; background-color: #CDB5CD;">销</td> 
						<td colspan="5" style="text-align: center ; font-weight:bold; background-color: #FFD39B;">存</td>
						<td colspan="2" style="text-align: center ; font-weight:bold; background-color: #A4D3EE;">利</td>
					</tr>
					
					<tr>
						<td align="center" nowrap="nowrap" style="text-align: center ; background-color: #A4D3EE;">生产计划</td>
						<td align="center" nowrap="nowrap"  style="text-align: center ; background-color: #CDB5CD;">销售计划</td>
						<td align="center"   style="text-align: center ; background-color: #CDB5CD;">${enddate}<br/></>结算</td>
						<td align="center"   style="text-align: center ; background-color: #CDB5CD;">当月结算截止<br/>${enddate}</td>
						<td align="center" nowrap="nowrap" style="text-align: center ; background-color: #CDB5CD;">${lastmonth }结算</td>
						<td align="center" nowrap="nowrap" style="text-align: center ; background-color: #CDB5CD;">实际完成率%</td>
						<td align="center" nowrap="nowrap" style="text-align: center ; background-color: #CDB5CD;">规格占比%</td>
						<td align="center" nowrap="nowrap" style="text-align: center ; background-color: #FFD39B;">库存总量</td>
						<td align="center" nowrap="nowrap" style="text-align: center ; background-color: #FFD39B;">有效库存</td>
						<td align="center" nowrap="nowrap" style="text-align: center ; background-color: #FFD39B;">分公司可卖库存</td>
						<td align="center" nowrap="nowrap" style="text-align: center ; background-color: #FFD39B;">大客户(库存)</td>
						<td align="center" nowrap="nowrap" style="text-align: center ; background-color: #FFD39B;">样机(库存)</td>
						<td align="center" nowrap="nowrap" style="text-align: center ; background-color: #A4D3EE;">总部毛利率%</td>
						<td align="center" nowrap="nowrap" style="text-align: center ; background-color: #A4D3EE;">分公司毛利率%</td>
					</tr>
					<c:choose>
				  	<c:when test="${entityList !=null}">
					<c:forEach var="cur" items="${entityList}" varStatus="vs" >
						<tr id="row_${vs.count}">
							<td align="center" nowrap="nowrap" class="xl_${vs.count}">${cur.XL}</td>
							<td align="center" nowrap="nowrap" class="mp_${vs.count}">${cur.MP}</td>
							<td align="center" nowrap="nowrap" class="sp_${vs.count}">${cur.SP}</td>
							<td align="center" nowrap="nowrap" class="menge4_${vs.count}"><fmt:formatNumber value="${cur.MENGE4}" pattern="#" /></td>
							<td align="center" nowrap="nowrap" class="menge_${vs.count}"><fmt:formatNumber value="${cur.MENGE}" pattern="#" /></td>
							<td align="center" nowrap="nowrap" class="menge2_${vs.count}"><fmt:formatNumber value="${cur.MENGE2}" pattern="#" /></td>
							<td align="center" nowrap="nowrap" class="spp_${vs.count}">${cur.SPP}%</td>
							<td align="center" nowrap="nowrap" class="ggzb_${vs.count}"></td>
							<td align="center" nowrap="nowrap" class="lbkum_${vs.count}"><fmt:formatNumber value="${cur.LBKUM}" pattern="#" /></td>
							<td align="center" nowrap="nowrap" class="labsf_${vs.count}"><fmt:formatNumber value="${cur.LABSF}" pattern="#" /></td>
							<td align="center" nowrap="nowrap" class="labs15_${vs.count}"><fmt:formatNumber value="${cur.LABS15}" pattern="#" /></td>
							<td align="center" nowrap="nowrap" class="labs5_${vs.count}"><fmt:formatNumber value="${cur.LABS5}" pattern="#" /></td>
							<td align="center" nowrap="nowrap" class="labs16_${vs.count}"><fmt:formatNumber value="${cur.LABS16}" pattern="#" /></td>
							<td align="center" nowrap="nowrap" class="zmlp_${vs.count}">${cur.ZMLP}%</td>
							<td align="center" nowrap="nowrap" class="fmlp_${vs.count}">${cur.FMLP}%</td>
						</tr>
						</c:forEach>
				  	</c:when>
				  	<c:otherwise>
				  		<tr>
				  	 	<td align="center" nowrap="nowrap" colspan="17">
					    	 <div class="tips_div">
								昨天数据还未从R/3进行同步,请在每天上午8:00后再尝试!
					 		</div>
					    </td>
					    </tr>
				  	</c:otherwise>
				  </c:choose>
				  
			</table>
		</div>

	<div class="rtabcont3">
	<span></span> 

	</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="../commons/scripts/jquery.js"></script>
<script type="text/javascript" >
	$(document).ready(function(){
		$("#tip_div2").hide();
		$("#sync_btn").click(function(){
			var pass = window.prompt("请输入同步密码!","");
			if(pass ==null || pass==""){
				return ;
			}
			
			if(pass !="951"){
				alert("sorry , operation denied !");
				return ;
			}
			
			// ajax begin 
			$("#tip_div1").hide();
			$("#tip_div2").show();
			$("#tips_div").text("");
			
			$.ajax({
				type: "POST",
				url: "${ctx}/webservice/KonkaModelXSReport.do?method=syncData4Report",
				dataType: "json",
				sync: true, 
				success: function(result) {
					$("#tip_div2").hide();
					$("#tip_div1").show();
				},
				error:function(){
					$("#tip_div2").hide();
					$("#tip_div1").show();
				}
			}); 
			// ajax end 
		});
	
		var length = "${fn:length(entityList)}";
		var xmenge =0.0 ;
		
		// 找出合计的那一行数据的某一列数据 
		for (var i = 1; i < length+1; i++) {
			$(".xl_"+i).each(function(){
				//12,2334 parseFloat : 12 
				if($(".xl_"+i).text()=="合计"){
					xmenge = parseFloat($(".menge_"+i).text());
				}
			});
		}
		// 计算所有行的规格占比 
		for (var i = 1; i < length+1; i++) {
			$(".ggzb_"+i).each(function(){
				if(xmenge!=0.0){
					$(".ggzb_"+i).text(((parseFloat($(".menge_"+i).text())/xmenge)*100).toFixed(2)+"%");
				}else{
					$(".ggzb_"+i).text("0.00%");				
				} 
			});
		}
		var $row29 = $("#row_29 >td") ; 
		$row29.css({"background-color": "#F7F709", "color":"green","font-weight": "bold", "font-size":"12px"});
		
	}) ;
</script>	

</body>
</html>