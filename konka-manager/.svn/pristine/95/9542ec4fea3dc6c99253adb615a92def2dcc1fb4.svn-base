<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet" type="text/css"
	href="${ctx}/commons/scripts/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/commons/scripts/themes/icon.css" />
<style type="text/css">
.rtable1 td {
	padding: 2px 5px;
}
</style>
</head>
<body>
<div style="margin-left: 5px; margin-right: 5px; margin-top: 5px;">
	 <table id="sign_table" width="100%" border="0" align="center" cellspacing="0" cellpadding="0" class="rtable3"> 
		      <tr><td>考勤时间: <br/>上午(08：00-12：00)<br/> 下午(14：00-18：00)</td></tr>
		      <tr>
		         <td >
		            <button id="sign" style="width:220px;height:60px;margin:5px" iconCls="icon-edit" plain="true" ></button>
		         </td> 
		      </tr>
		       <tr>
		        <td>
		        <button id="location" style="width:220px;height:60px;margin:5px" iconCls="icon-edit" plain="true"><h1>定位</h1></button>
		        </td>
		      </tr>
		      <tr>
		        <td>
		        <input type="hidden" id="sign_user" />
		        <button id="signviewbtn" style="width:220px;height:30px;margin:5px" iconCls="icon-edit" plain="true" >签到详细</button>
		        </td>
		      </tr>
	</table>
</div>
<!--指定人的签到详情-->
<div id="dlg_calendardiv" class="easyui-dialog" style="width:300px;height:500px;padding:10px 20px"  closed="true" buttons="dlg_calendardiv_buttons">  
      <div id="calendardiv" >
      </div>
      <div id="dlg_calendardiv_buttons">
	       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_calendardiv').dialog('close');" style="width:90px">退出</a>
	  </div>
</div>
 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/jquery.min.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/public.js"></script>
<script type="text/javascript">
		$(function(){
			//初始化
			$.post('${ctx}/webservice/YwtAttendanceSign.do?method=add',function(result){
	    		 $("#sign_table #sign").attr("sign",result.signType).html('<h1>'+result.signTypeName+'</h1>');
	    		 $("#sign_table #sign").click(function(){
	    			 var sign=$(this).attr("sign");
		    	       $("#sign_table #sign").hide();
		    	        $.ajax({
				    	   type: "POST",
				    	   url: "${ctx}/webservice/YwtAttendanceSign.do?method=newsign",
				    	   data: "sign_type="+sign,
				    	   success: function(result){
				    	     alert( "提示: " + result.res);
				    	     if(result.signType){
				    	    	 $("#sign_table #sign").attr("sign",result.signType);
						     }
						     if(result.signTypeName){
						    	 $("#sign_table #sign").html('<h1>'+result.signTypeName+'</h1>');
							 }
				    	     $("#sign_table #sign").show();
				    	   },
				    	   error:function(XMLHttpRequest, textStatus, errorThrown) {
				    		    alert("操作异常");
				    		    $("#sign_table #sign").show();
				    		}
				    	});
		    	 });
	    		 $("#sign_table #location").click(function(){
		    	       var sign=$(this).attr("location");
		    	       $("#sign_table #location").hide();
		    	        alert("定位异常");
		    		    $("#sign_table #location").show();
		    	 });
		    	 
	    		 if(result.sign_user_id){
					 $("#sign_table #sign_user").val(result.sign_user_id);
				 }
				 //查询详情
				 $("#sign_table #signviewbtn").click(function(){
					 var user_id=$("#sign_table #sign_user").val();
					 signview(user_id);
				 });
		        $('#dlg_sign').dialog('open').dialog('setTitle','签到'); 
	         });
		});
		
		//初始化签到信息
		var url;
		//查看签到详情
		function signview(user_id){
			$("#calendardiv").empty();
			$('#dlg_calendardiv').dialog('open').dialog('setTitle','签到详情');
			var calendardiv=$("#calendardiv");
			calendardiv.append('<input type="hidden" id="sign_user" value="'+user_id+'"/>');
			var yyyymm=$('<div><select name="_year" id="_year"><option value="2014">2014</option><option value="2015">2015</option>'+
					'<option value="2016">2016</option><option value="2017">2017</option><option value="2018">2018</option><option value="2019">2019</option></select>'
	                 +'<select name="_month" id="_month"><option value="1">1</option><option value="2">2</option><option value="3">3</option>'
	        		  +'<option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option>'
	        		  +'<option value="8">8</option><option value="9">9</option><option value="10">10</option><option value="11">11</option>'
	        		  +'<option value="12">12</option></select></div>');
			calendardiv.append(yyyymm);
			calendardiv.append('<div id="calendar"></div>');
			$("#_year").change(function(){
			   var userid=$("#sign_user").val();
				year_month_change(userid);
			});
			
   	        $("#_month").change(function(){
			var userid=$("#sign_user").val();
   	        	year_month_change(userid);
			});
			 $.ajax({
		    	   type: "POST",
		    	   url: "${ctx}/webservice/YwtAttendanceSign.do?method=mobileCalendar",
		    	   data: "sign_user_id="+user_id,
		    	   success: function(result){
		    	     $("#_year").val(result.year);
		    	     $("#_month").val(result.month);
		    	     var sign4 = result.sign4;
		    	     var maxday=parseInt(result.maxday);
		    	     initshow(sign4,maxday);
		    	   },
		    	   error:function(XMLHttpRequest, textStatus, errorThrown) {
		    		    alert("初始化数据失败!");
		    		}
		    	});
		    	
		}
		function year_month_change(userid){
			 var yearmonth='';
				var year=$("#_year").val();
				var month=$("#_month").val();
				if(year){
					yearmonth+='&year='+year;
				}
				if(month){
					yearmonth+='&month='+month;
				}
				$.ajax({
			    	   type: "POST",
			    	   url: "${ctx}/webservice/YwtAttendanceSign.do?method=mobileCalendar",
			    	   data: "sign_user_id="+userid+yearmonth,
			    	   success: function(result){
			    	     $("#_year").val(result.year);
			    	     $("#_month").val(result.month);
			    	     var sign4 = result.sign4;
			    	     var maxday=parseInt(result.maxday);
			    	     initshow(sign4,maxday);
			    	   },
			    	   error:function(XMLHttpRequest, textStatus, errorThrown) {
			    		    alert("初始化数据失败!");
			    		}
			    });
		}
		//sign4(一天都签了到的) maxday(日历的最大天数)
        function initshow(sign4,maxday){
        	 $('#calendar').empty();
        	 for(var i=1;i<=maxday;i++){
			     var dayhtml=$('<button value="'+i+'">'+i+'</button>').click(function(){
				        var userid=$("#sign_user").val();
                        var year=$("#_year").val();
                        var month=$("#_month").val();
				        var day=this.value;
				        $('#signview').remove();
				        $.ajax({
					    	   type: "POST",
					    	   url: "${ctx}/webservice/YwtAttendanceSign.do?method=mobileCalendarDay",
					    	   data: "sign_user_id="+userid+"&year="+year+"&month="+month+"&day="+day,
					    	   success: function(result){
						    	   var signviewlist=result.signviewlist;
						    	   var signview=$('<div id="signview"></div>');
						    	   var sign_table=$('<table><tr><td>类型</td><td>时间</td><td>状态</td></table>');
						    	   if(signviewlist){
						    		   $.each(signviewlist, function(i, n){
							    		   var sign_entity=signviewlist[i];
							    		   var sign_type1=sign_entity.SIGN_TYPE;
							    		   var sign_type='未知';
							    		   if(sign_type1==1){sign_type='上午签到';}else
							    		   if(sign_type1==2){sign_type='上午签退';}else
							    		   if(sign_type1==3){sign_type='下午签到';}else
							    		   if(sign_type1==4){sign_type='下午签退';}
							    		   var sign_state1=sign_entity.SIGN_STATE;
							    		   var sign_state='未知';
							    		   if(sign_state1==1){sign_state='正常';}else
								    	   if(sign_state1==2){sign_state='迟到';}else
								    	   if(sign_state1==3){sign_state='早退';}   
							    		   var sign_tr=('<tr><td>'+sign_type+'</td><td>'+sign_entity.SIGN_DATE+'</td><td>'+sign_state+'</td></tr><tr><td colspan="3" >IP&nbsp&nbsp'+sign_entity.IP+'</td></tr>');
							    		   sign_table.append(sign_tr);
							    			 });
							    	}
									signview.append(sign_table);
								    $("#calendar").append(signview);
						    	},
					    	   error:function(XMLHttpRequest, textStatus, errorThrown) {
					    		    alert("初始化数据失败!");
					    		}
				           });
				 });
			     $("#calendar").append(dayhtml);
				 dayhtml.css({
					 width: "30px",
					 height:"30px",
					 margin: "3px",
					 padding: "1px"
					 });
				 if(paramInArray(sign4,i)){
				     dayhtml.css({
					 color:"red",
					 background:"green"
					 });
				 }
				  if(i%6==0){
					  $("#calendar").append("<br/>");
				 }
			}
        }
		//判断一个值是否在一个指定数组里面
		function paramInArray(arrs,param){
			// 遍历是否在数组中
			for(var i=0,k=arrs.length;i<k;i++){
				console.log(arrs[i].SIGNTOTLE);
				var entity=arrs[i];
				if(param==entity.ADD_DATA_DAY&&entity.SIGNTOTLE==4){
					return true;
				}
			}
			// 如果不在数组中就会返回false
			return false;
		}
	</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
