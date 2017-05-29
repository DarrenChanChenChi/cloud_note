//加载笔记本
function loadBooks(){
	//加载笔记本列表
	$.ajax({
		url:"notebook/loadbooks.do",
		type:"post",
		data:{"userId":userId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var books = result.data;//笔记本json数组
				//清除原有的笔记本列表
				$("#book_list").empty();
				//循环books生成笔记本列表li
				for(var i = 0; i < books.length; i++){
					var bookId = books[i].cn_notebook_id;
					var bookName = books[i].cn_notebook_name;
					//拼成一个li
				    var sli = '<li class="online">'+
					'<a>'+
					'<i class="fa fa-book" title="online" rel="tooltip-bottom"></i>'+
					bookName+
					'</a>'+
					'</li>';
					//利用data函数将bookId和sli绑定
					var $li = $(sli);//将sli字符串转成jQuery对象
					//$li.data(key,name)
					$li.data("bookId",bookId);//将bookId绑定到$li
					//添加到<ul id="book_list">中
					$("#book_list").append($li);
				}
			}
		},
		error:function(){
			alert("加载笔记本列表失败");
		}
	});
}

//确认添加笔记本
function sureAddBook(){
	//获取请求提交数据
	var bookName = $("#input_notebook").val().trim();
	if(bookName==""){
		alert("笔记本名不能为空");
		return;
	}
	//数据格式检查
	//发送Ajax请求
	$.ajax({
		url:"notebook/add.do",
		type:"post",
		data:{"bookName":bookName,"userId":userId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var bookId = result.data;//笔记本ID
				//拼一个笔记本li添加到列表项
				//拼成一个li
			    var sli = '<li class="online">'+
				'<a>'+
				'<i class="fa fa-book" title="online" rel="tooltip-bottom"></i>'+
				bookName+
				'</a>'+
				'</li>';
				//利用data函数将bookId和sli绑定
				var $li = $(sli);//将sli字符串转成jQuery对象
				//$li.data(key,name)
				$li.data("bookId",bookId);//将bookId绑定到$li
				//添加到<ul id="book_list">中
				$("#book_list").append($li);
				//关闭添加笔记本对话框
				closeWindow();
				//弹出成功提示
				alert(result.msg);
			}else if(result.status==3){
				alert(result.msg);
			}
		},
		error:function(){
			alert("创建笔记本异常");
		}
	});
}

//确认重命名笔记本
function sureRenameBook(){
	//获取参数
	var bookName = $("#input_notebook_rename").val().trim();
	var $li = $("#book_list li a.checked").parent();
	var bookId = $li.data("bookId");
	if(bookName==""){
		alert("笔记本名不能为空");
		return;
	}
	var $title = $("#book_list li a.checked").parent().siblings();
	for(var i = 0; i < $title.length; i++){
		//text()可以取到中间的文本
		if(bookName==$($title[i]).find("a").text()){
			alert("笔记本名重复");
			return;
		}
	}
	//alert(bookId);
	//参数格式校验
	//发送Ajax请求
	$.ajax({
		url:"book/rename.do",
		type:"post",
		data:{"bookId":bookId,"bookName":bookName},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//刷新笔记本列表
				loadBooks();
				//关闭添加笔记本对话框
				closeWindow();
				//弹出成功提示
				alert(result.msg);
			}
		},
		error:function(){
			alert("更改笔记本名失败");
		}
	});
}

//确认删除笔记本
function sureDeleteBook(){
	//获取参数
	var $li = $("#book_list li a.checked").parent();
	var bookId = $li.data("bookId");
	//alert(bookId);
	//参数格式校验
	//发送Ajax请求
	$.ajax({
		url:"book/delete.do",
		type:"post",
		data:{"bookId":bookId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//刷新笔记本列表
				loadBooks();
				//关闭添加笔记本对话框
				closeWindow();
				//弹出成功提示
				alert(result.msg);
			}
		},
		error:function(){
			alert("删除笔记本失败");
		}
	});
}