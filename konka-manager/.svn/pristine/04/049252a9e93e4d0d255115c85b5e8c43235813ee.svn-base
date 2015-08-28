<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body onload="zdbq('user_name')">
  用户名：<input type="text" name="user_name" id="user_name">
	<!-- 存放名称的id -->
<input type="hidden" value="" name="zdbqid" id="zdbqid"/>
<!-- 显示的div -->
<div style="width:180px; z-index:99999999; display:none; background:#FFFFFF;1px #ccc solido position: absolute;border:1px blue solid" id="autoTxt"></div>
</body>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript">


var maxcount = 0;// 表示他最大的值
var thisCount =0;// 初始化他框的位置
var flagThis = 0;
var flag = 0; //标示是否选择自动补全
//自动补全方法
function zdbq(obj){
	var id = obj;
	document.getElementById("zdbqid").value = id;
  	jQuery("#"+id).keyup(function(even) { 
  		document.getElementById("autoTxt").style.width= 214+"px";
        var v = even.which;
	      //回车键：13；上键38；下键40// 当点击上下键或者确定键时阻止他传送数据    
	    if (v == 38 || v == 40 || v == 13){    
	        return;
	    }   
        var txt = jQuery("#"+id).val();//这里是取得他的输入框的值
        if (txt != "") {
        jQuery.ajax({
              url : "KonkaFightActivityManager.do?method=ajaxGetName",//从后台取得json数据
              type : "post", 
              dataType : "json",
              data : {"name" : txt},
              success : function(ls) {  
                  alert(ls.state); 
				  if(ls.state=="1"){  
					  flagThis = 1;
	                  var offset = jQuery("#"+id).offset();//offset() 方法返回或设置匹配元素相对于文档的偏移（位置）。
	                  jQuery("#autoTxt").show();
	                  jQuery("#autoTxt").css("top", (offset.top + 22) + "px");
	                  jQuery("#autoTxt").css("left", offset.left + "px");           
	                  var Candidate = "";
	                   maxcount = 0;//再重新得值
	                   for(i=0;i<ls.result.length;i++){
						   Candidate += "<li style='list-style:none' id='"+maxcount+"'>"+ls.result[i].user_name+"</li><span id='hhh' style=display:none >"+ls.result[i].id+"</span>";
		                   maxcount++; 
					  }    

	                                    
	                  jQuery("#autoTxt").html(Candidate);
	                  jQuery("#autoTxt li:eq(0)").css("background", "#A8A5A5");//初始化默认选择第一个数据
	                  
	                  //当单击某个ＬＩ时反映
	                  jQuery("#autoTxt li").click(function(){  
		                  flag = 1;
		//标示是否选择自动补全
		                  var jgname=jQuery("#autoTxt li:eq("+this.id+")").text(); //获取名称  
		                  var fid = jQuery("#autoTxt span:eq("+thisCount+")").text();
		//获取id
		                  jQuery("#"+id).val(jgname);
		//放入名称 
		                  $("#jcid").val(fid);
		//放入id
		                  jQuery("#autoTxt").html("");
		                  jQuery("#autoTxt").hide();                
	                  });
	                      //移动对象
	                  jQuery("#autoTxt li").hover(function(){
	                  		  jQuery("#autoTxt li").css("background", "#FFFFFF");
	                  		  jQuery("#autoTxt li:eq("+this.id+")").css("background", "#A8A5A5");
	                          thisCount=this.id;},
	                          function(){
	                          jQuery("#autoTxt li").css("background", "#FFFFFF");
	                          });

				  }else if(ls.state=="-1"){
					  jQuery("#autoTxt").html("");
		              jQuery("#autoTxt").hide();
		              maxcount = 0;
				}	
                    	  
              },
              error : function() {
	              jQuery("#autoTxt").html("");
	              jQuery("#autoTxt").hide();
	              maxcount = 0;
              }
          });
      } else {
	      jQuery("#autoTxt").html("");
	      jQuery("#autoTxt").hide();
          maxcount = 0;
      }
  }    
  );
  //=====================自动补全公共部分开始=================================
  //当单击ＢＯＤＹ时则隐藏搜索值
  jQuery("body").click(function(){
	  jQuery("#autoTxt").html("");
	  jQuery("#autoTxt").hide();
      thisCount=0;
  });
}




jQuery(function(){
	  //键盘按键移动事件上键38,下键40,确定键13
	  jQuery("body").keyup(
		  function(even){
		  var id = document.getElementById("zdbqid").value;
		  var v = even.which;
		  if(38==v){//上键
			  if(thisCount>0){ 
			  --thisCount;  
			  }else{
			  thisCount = maxcount-1;
			  }
              jQuery("#autoTxt li").css("background", "#FFFFFF");
              jQuery("#autoTxt li:eq("+thisCount+")").css("background", "#A8A5A5");
	      }else if(40==v){//下键
			  if(thisCount<maxcount-1){
			       ++thisCount;
			  }else{
			      thisCount = 0;
			  }
              jQuery("#autoTxt li").css("background", "#FFFFFF");
              jQuery("#autoTxt li:eq("+thisCount+")").css("background", "#A8A5A5");
	  }else if(13==v){//确定键
	  		  flag = 1;  //标示是否选择自动补全
	          var jgname=jQuery("#"+thisCount).text(); //获取名称  
	          var fid = jQuery("#autoTxt span:eq("+thisCount+")").text();
	//获取id
	          if(jgname!=""){
	          	jQuery("#"+id).val(jgname);
	//放入名称
	          }
	              if(fid!=""){
	              $("#jcid").val(fid);
	//放入id
	           }
	          jQuery("#autoTxt").html("");
	          jQuery("#autoTxt").hide();   
	  }else {
		  if(jQuery("#autoTxt").html()!=""){ 
		       thisCount=0;
		    }
	  }
	  } 
	  );
	});


</script>
</html>