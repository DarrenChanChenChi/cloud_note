//登录处理
$(function(){
	$("#count").blur(function(){
		//清除原有提示信息
		$("count_span").html("");
		$("password_span").html("");
		//获取请求提交的数据
		var name = $("#count").val().trim();
		//检测数据格式
		if(name==""){
			$("#count_span").html("用户名为空");
		}else{
			$("#count_span").html("");
		}
	});
	$("#password").blur(function(){
		//清除原有提示信息
		$("count_span").html("");
		$("password_span").html("");
		//获取请求提交的数据
		var password = $("#password").val().trim();
		//检测数据格式
		if(password==""){
			$("#password_span").html("密码为空");
		}else{
			$("#password_span").html("");
		}
	});
});

function login(){
	//清除原有提示信息
	$("count_span").html("");
	$("password_span").html("");
	//获取请求提交的数据
	var name = $("#count").val().trim();
	var password = $("#password").val().trim();
	//检测数据格式
	var ok = true;//表单是否通过检测
	if(name==""){
		ok = false;
		$("#count_span").html("用户名为空");
	}
	if(password==""){
		ok = false;
		$("#password_span").html("密码为空");
	}
	//通过检测发送Ajax请求
	if(ok){
		//发送ajax
		$.ajax({
			url:"user/login.do",
			type:"post",
			data:{"username":name,"password":password},//这就是key，和controller类里面方法参数必须一样，相当于name
			dataType:"json",
			success:function(result){//result是服务器返回的json对象
				//成功处理
				if(result.status==0){//成功
					//将返回的用户ID写入Cookie
					var userId = result.data;//用户ID
					addCookie("userId",userId,2);//写入，2小时失效

					window.location.href="edit.html";//跳转页面
				}else if(result.status==1){//用户名错误
					$("#count_span").html(result.msg);
				}else if(result.status==2){//密码错误
					$("#password_span").html(result.msg);
				}
			},
			error:function(){
				//异常处理
				alert("登录失败");
			}
		});
	}
}

//重新登录
function redirect(userId){
	delCookie(userId);
	window.location.href="log_in.html";
}