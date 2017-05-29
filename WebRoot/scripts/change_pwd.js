$(function(){
	$("#changePassword").click(function(){
		//清除原有提示信息
		$("#warning_1 span").html("");
		$("#warning_2 span").html("");
		$("#warning_3 span").html("");
		$("#warning_1").hide();
		$("#warning_2").hide();
		$("#warning_3").hide(); 
		//获取请求提交的参数
		var userId = getCookie("userId");
		var last_password = $("#last_password").val().trim();
		var new_password = $("#new_password").val().trim();
		var final_password = $("#final_password").val().trim();
		//检查参数的格式
		var ok = true;
		if(new_password==""){
			$("#warning_2 span").html("新密码为空");
			$("#warning_2").show();
			ok=false;
		}else{
			if(new_password.length<6){
				$("#warning_2 span").html("密码长度小于6位");
				$("#warning_2").show();
				ok=false;
			}
		}
		if(new_password!=final_password){
			$("#warning_3 span").html("密码输入不一致");
			$("#warning_3").show();
			ok=false;
		}
		//发送Ajax请求
		if(ok){
			$.ajax({
				url:"user/changepwd.do",
				type:"post",
				data:{"lastPassword":last_password,"newPassword":new_password,"userId":userId},
				dataType:"json",
				success:function(result){
					if(result.status==0){//成功
						alert(result.msg);//提示框
						$("#back").click();//触发返回按钮单击
					}else if(result.status==2){//原密码错误
						$("#warning_1 span").html(result.msg);
						$("#warning_1").show();
					}	   
				},
				error:function(){
					alert("修改密码异常");
				}
			});
		}
	});
});