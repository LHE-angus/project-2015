/*(function(v){
	$.get("http://konka.mymyty.com/mobile/admin/VersionCheck.do", {v:v}, function(data){
		if(data != "true"){
			alert('��Ŀǰʹ�õİ汾���ͣ�����������ʹ�ø�ϵͳ��лл��');
			location.href="../index.html";
		}
	});	
})('1.0')
*/

//$(function(){$(document).everyTime(600000,function(){$.get("http://konka.mymyty.com/mobile/admin/VersionCheck.do",{method:'u'})});});

function logout(){
	$.get("http://konka.mymyty.com/login.do?method=logout");
	location.href="../index.html";
}

function gohome(){
	location.href="IndexFrame.html";
}