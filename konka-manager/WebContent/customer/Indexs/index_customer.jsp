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
		<link rel="stylesheet" type="text/css"  href="${ctx}/styles/customer/css/base.css" />
		<link rel="stylesheet" type="text/css"  href="${ctx}/styles/customer/css/index.css" />
	</head>
	<body >
		<div class="oarcont">
			<div class="oartop">
				<table width="400" border="0" cellpadding="0" cellspacing="0">
			    	<tr>
			        	<td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
			        	<td>&nbsp;当前位置：<font color="red">客户管理</font></td>
			      	</tr>
			    </table>
			</div>
			<div class="content clearfix">  
	            <div class="columnA" style="width: 50%">
	                <div class="border-box" style="min-height: 100px;">
	                    <div style="width: 100%">
	                    	<table width="100%">
	                    		<tr>
	                    			<td width="20%" align="center"><img src="${ctx }/styles/customer/images/store2.jpg" style="margin-top: 24px" /></td>
	                    			<td width="80%">
	                    				<div style="background-color: #efe7e7;margin: 20px 10px 0px 0px;padding-left: 5px; height: 50px; padding-top: 35px;
	                    					font-size: 15px ">
	                    					我的有效门店&nbsp;<font color="red" id="store_num"></font>&nbsp;个
	                    				</div>
	                    			</td>
	                    		</tr>
	                    	</table>
	                    </div>
	                    <div style="width: 100%">
	                    	<table width="100%">
	                    		<tr>
	                    			<td width="20%" align="center"><img src="${ctx }/styles/customer/images/agent2.jpg" style="margin-top: 24px" /></td>
	                    			<td width="80%">
	                    				<div style="background-color: #efe7e7;margin: 20px 10px 0px 0px;padding-left: 5px; height: 50px; padding-top: 35px;
	                    					font-size: 15px">
		                    				我的有效网点&nbsp;<font color="red" id="agent_num"></font>&nbsp;个。<span id="a_details"></span>
	                    				</div>
	                    			</td>
	                    		</tr>
	                    	</table>
	                    </div>
	                    <div style="width: 100%">
	                    	<table width="100%">
	                    		<tr>
	                    			<td width="20%" align="center"><img src="${ctx }/styles/customer/images/partner2.jpg" style="margin-top: 28px" /></td>
	                    			<td width="80%">
	                    				<div style="background-color: #efe7e7;margin: 20px 10px 0px 0px;padding-left: 5px; height: 50px; padding-top: 35px;
	                    					font-size: 15px">
		                    				我的有效供应商&nbsp;<font color="red" id="partner_num"></font>&nbsp;个。
	                    				</div>
	                    			</td>
	                    		</tr>
	                    	</table>
	                    </div>
	                    <div style="width: 100%">
	                    	<table width="100%">
	                    		<tr>
	                    			<td width="20%" height="50px" align="center"><img src="${ctx }/styles/customer/images/consumer2.jpg" style="margin-top: 0px"/></td>
	                    			<td width="80%">
	                    				<div style="background-color: #efe7e7;margin: 20px 10px 20px 0px;padding-left: 5px; height: 50px; padding-top: 35px;
	                    					font-size: 15px">
		                    				我的有效顾客共计&nbsp;<font color="red" id="consumer_num"></font>&nbsp;个。
	                    				</div>
	                    			</td>
	                    		</tr>
	                    	</table>
	                    </div>
	                </div>
	            </div>
	            <div class="columnB" style="width: 45%">
	                <div class="border-box" style="min-height: 100px">
	                    <h3 class="column-tit"><span class="fl">资讯</span><a class="fr" href="${ctx }/customer/manager/KonkaGroupPeArticleInfo.do?method=list&mod_id=200000110">更多</a></h3>   
                   	 	<div style="height: 25px;padding-left: 10px;padding-right: 10px">
                   	 		<div style="height: 180px">
                   	 			<div align="center"><font size="4" style="font-weight: bolder;font-family: serif;" id="info_title"></font></div>
                   	 			<div id="info_up_date" align="center"></div>
                   	 			<div id="info_content"></div>
                   	 		</div>
		                    <div align="right">
		                    	<a href="#" id="see_all"><img src="${ctx }/styles/customer/images/scanall.png" width="70px"/></a>
		                    	<img id="nextInfo" src="${ctx }/styles/customer/images/next.png" width="70px" style="cursor: pointer;"/>
		                    </div>
	                    </div>
	                    <ul class="columnB-list" style="margin-top: 180px" id='ul_list'>
	                    </ul>
	                </div>
	            </div>
	        </div>
		</div>
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script> 
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.easyui.min.js"></script>  
		<script type="text/javascript" src="${ctx}/commons/scripts/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${ctx}/commons/scripts/public.js"></script> 
		
	<script type="text/javascript">
	
		var arr_id = new Array(5);
		var arr_title = new Array(5);
		var arr_date = new Array(5);
		var arr_content = new Array(5);
			$(function(){
				//初始化
				$.post('${ctx}/customer/manager/Indexs.do?method=initCustomerIndex',function(result){
					$("#store_num").html("<a href='${ctx}/customer/manager/KonkaR3Store.do?mod_id=103020000' target='mainFrame'><font color='red'>"+result.store_num+"</font></a>");
					$("#agent_num").html("<a href='${ctx}/customer/manager/AgentsList.do?mod_id=103060000' target='mainFrame'><font color='red'>"+result.agent_num+"</font></a>");
					$("#a_details").html(result.agent_details);
					$("#partner_num").html("<a href='${ctx}/customer/manager/JBasePartner.do?mod_id=103080000' target='mainFrame'><font color='red'>"+result.partner_num+"</font></a>");
					$("#consumer_num").html("<a href='${ctx}/customer/manager/ConsumerInfo.do?mod_id=103040000' target='mainFrame'><font color='red'>"+result.consumer_num+"</font></a>");
					jQuery.each(result.GroupPeInfoList,function(index,da){
						arr_id[index]=da.id;
						arr_title[index]=da.title;
						arr_date[index]=da.map.pub_date;
						arr_content[index]=da.summary;
						if(index==0){
							$("#info_title").html(da.title);
							$("#info_up_date").html("发布时间："+da.map.pub_date);
							$("#see_all").attr("href","${ctx }/customer/manager/KonkaGroupPeArticleInfo.do?method=view&mod_id=200000110&id="+da.id);
							$("#nextInfo").attr("name",index);
							$("#info_content").html(da.summary);
						}else{
							var html = "<li><span class='columnB-list-type'>"+da.map.type_name+"</span>"+
	                         		   "<a href='${ctx}/customer/manager/KonkaGroupPeArticleInfo.do?method=view&id="+da.id+"&mod_id=200000110' target='_blank'>"+da.title+"</a>"+
	                         		   "<span class='columnB-list-date' style='width: 85px;'>"+da.map.pub_date+"</span></li>";
	                        $("#ul_list").append(html);
						}
					});
				},'json');
				
				//点击下一条，循环显示当前的5条记录
				$("#nextInfo").click(function(){
					var index = parseInt($(this).attr("name"));
					if(index==4){
						index = 0;
					}
					$("#info_title").html(arr_title[index+1]);
					$("#info_up_date").html("发布时间："+arr_date[index+1]);
					$("#see_all").attr("href","${ctx }/customer/manager/KonkaGroupPeArticleInfo.do?method=view&mod_id=200000110&id="+arr_id[index+1]);
					$("#info_content").html(arr_content[index+1]);
					$("#nextInfo").attr("name",index+1);
				});
			});
		</script>
	<jsp:include page="/customer/__analytics.jsp" />
	</body>
</html>
