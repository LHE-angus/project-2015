<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript" language="javascript">
var init_arr = [];
var selected_arr = [];
function AreaMove(areaName,left_id,right_id,left_span_id,right_span_id){
	if(typeof(areaName) != "string" || areaName == ""){
	  alert("创建实例失败");
      return;
   }
   this.name = areaName;

   this.show_key = [];
   this.input_id_key = [];
   this.input_name_key = [];
   this.input_value_key = [];

   if(typeof(left_id) != "string" || left_id == "" ||
	  typeof(right_id) != "string" || right_id == ""){
	  alert("参数不足，缺少左右区域ID");
	  return;
   }
   this.left_area = document.getElementById(left_id);
   this.right_area = document.getElementById(right_id);  
   this.areas = [];
   this.areas["area_0"] = this.left_area;
   this.areas["area_1"] = this.right_area;
}

AreaMove.prototype.setup = function (){
   this.json_key = [];
   this.json_key["show_key"] = this.show_key;
   this.json_key["input_id"] = this.input_id_key;
   this.json_key["input_name"] = this.input_name_key;
   this.json_key["input_value"] = this.input_value_key;
};

AreaMove.prototype.addElement = function (e,flg){	
    this.areas["area_"+flg].appendChild(e);
};

AreaMove.prototype.createInputElement = function(jsonObj){
	var input = document.createElement("input");
    input.type="hidden";
    var input_id_key_arr = [];
    jsonToArr(input_id_key_arr,jsonObj,this.json_key["input_id"]);
    input.setAttribute("id",input_id_key_arr.join(","));
    
    var input_value_key_arr = [];
    jsonToArr(input_value_key_arr,jsonObj,this.json_key["input_value"]);
    input.value = input_value_key_arr.join(",");
    return input;
};

AreaMove.prototype.createAreaElement = function (jsonObj,flg){
	var input = this.createInputElement(jsonObj);
    if(flg == 0){
       if(containsJson(selected_arr,jsonObj,this.json_key["input_id"])) return;
    }else{
       var name_arr = this.json_key["input_name"];
       input.setAttribute("name",name_arr[flg]);
       jsonToMap(init_arr,jsonObj,this.json_key["input_id"],this.json_key["input_value"]);
       jsonToMap(selected_arr,jsonObj,this.json_key["input_id"],this.json_key["input_value"]);
    }

    var tr = document.createElement("tr");
    tr.setAttribute("id",this.name+"_row_"+input.id);
    var td = document.createElement("td");
    td.style.height = "20px";
    tr.style.cursor = 'pointer';
    tr.setAttribute("flg",""+flg);
    var span = document.createElement("span");
    var show_key_arr = [];
    jsonToArr(show_key_arr,jsonObj,this.json_key["show_key"]);
    
    span.innerHTML = show_key_arr.join(",");

    tr.onclick = function (){
       var flg = - parseInt(this.getAttribute("flg"));      
       var area = eval(this.id.split("_row_")[0]);   
       this.setAttribute("flg",(flg+1));
       var input = this.childNodes[0].childNodes[0];
       var id_str = input.id;
       var value_str = input.value;
       area.addElement(this,flg+1);

       var name_arr = area.json_key["input_name"];    
       if(flg ==0){
          selected_arr[id_str] = value_str;
          if(containsId(init_arr,id_str)){
        	  input.setAttribute("name",name_arr[flg+1]); 
          }else{
        	  input.setAttribute("name",name_arr[flg]); 
          }        
       }else{
          selected_arr[id_str] = "";
          input.setAttribute("name","");
       }
    };    
    td.appendChild(input);
    td.appendChild(span);
    tr.appendChild(td);
    this.addElement(tr,flg);	
};

AreaMove.prototype.createSelectAreas = function(jsonList,flg){
   if(jsonList.length > 0){
      for(var i = 0; i < jsonList.length; i++) {
    	  this.createAreaElement(jsonList[i],flg);
      }
   }
};
AreaMove.prototype.removeAreaElements = function(flg){
   var area = this.areas["area_"+flg];
   if(area && area.childNodes.length>0){
	  for(var i = area.childNodes.length -1; i >= 0;i--){
	      area.removeChild(area.childNodes[i]);
	  }
   }
};
	
function jsonToArr(arr,jsonObj,key_arr){
    for(var i = 0;i< key_arr.length;i++){
        arr[arr.length] = eval("jsonObj."+key_arr[i]);
    }
}

function containsId(arr,id){
    if(arr[id]==null || arr[id] ==""){
       return false;
    }
    return true;
}

function containsJson(arr,jsonObj,pk_key_arr){
    var js_key = [];
    jsonToArr(js_key,jsonObj,pk_key_arr);
    if(arr[js_key.join(",")] == null || arr[js_key.join(",")] == ""){
       return false;
    }
    return true;
}

function jsonToMap(arr,jsonObj,key_arr,value_arr){
    var js_key = [],js_value = [];
    jsonToArr(js_key,jsonObj,key_arr);
    jsonToArr(js_value,jsonObj,value_arr);
    arr[js_key.join(",")] = js_value.join(",");
}
</script>
</body>
</html>