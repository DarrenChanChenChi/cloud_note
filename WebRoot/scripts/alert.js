//弹出创建笔记本对话框
function alertAddBookWindow(){
	//显示背景色
	$(".opacity_bg").show();
	//弹出对话框
	$("#can").load("alert/alert_notebook.html");
}
//弹出创建笔记对话框
function alertAddNoteWindow(){
	//检测笔记本是否选中
	if($("#book_list li a").hasClass("checked")){
		//显示背景色
		$(".opacity_bg").show();
		//弹出对话框
		$("#can").load("alert/alert_note.html");
	}else{
		alert("请先选择笔记本");
	}
}
//关闭对话框
function closeWindow(){
	//隐藏背景色
	$(".opacity_bg").hide();
	//清空对话框内容
	$("#can").empty();
}

//弹出确认删除笔记（放入回收站）对话框
function alertRecycleNoteDelete(){
	 //显示背景色
	$(".opacity_bg").show();
	//弹出对话框
	$("#can").load("alert/alert_delete_note.html");
}

//弹出移动笔记对话框
function alertMoveNoteWindow(){
	 //显示背景色
	$(".opacity_bg").show();
	//弹出对话框
	$("#can").load("alert/alert_move.html",function(){
		//获取笔记本信息填充下拉单选项
		var bookLis = $("#book_list li");
		for(var i = 0; i < bookLis.length; i++){
			var $li = $(bookLis[i]);
			var bookName = $li.text().trim();
			var bookId = $li.data("bookId");
			//alert(bookId);
			//拼成一个option
			var opt = "<option value='"+bookId+"'>"+bookName+"</option>";
			//将option添加到select
			$("#moveSelect").append(opt);
		}
	});
}

//弹出确认恢复笔记对话框
function alertRecoverNote(){
	//显示背景色
	$(".opacity_bg").show();
	//弹出对话框
	$("#can").load("alert/alert_replay.html",function(){
		//获取笔记本信息填充下拉单选项
		var bookLis = $("#book_list li");
		for(var i = 0; i < bookLis.length; i++){
			var $li = $(bookLis[i]);
			var bookName = $li.text().trim();
			var bookId = $li.data("bookId");
			//alert(bookId);
			//拼成一个option
			var opt = "<option value='"+bookId+"'>"+bookName+"</option>";
			//将option添加到select
			$("#replaySelect").append(opt);
		}
	});
}

//在回收站弹出删除笔记对话框
function alertDeleteNote(){
	//显示背景色
	$(".opacity_bg").show();
	//弹出对话框
	$("#can").load("alert/alert_delete_rollback.html");
}

//在回收站点击弹出清空回收站对话框
function alertDeleteAll(){
	//显示背景色
	$(".opacity_bg").show();
	//弹出对话框
	$("#can").load("alert/alert_delete_all.html");
}

//双击弹出更改笔记本名的对话框
function alertRenameBook(){
	//显示背景色
	$(".opacity_bg").show();
	//弹出对话框
	$("#can").load("alert/alert_rename.html");
}

//点击，弹出删除笔记本对话框
function alertDeleteBook(){
	//显示背景色
	$(".opacity_bg").show();
	//弹出对话框
	$("#can").load("alert/alert_delete_notebook.html");
}

//点击，弹出删除活动笔记对话框
function alertDeleteNoteActivity(){
	//显示背景色
	$(".opacity_bg").show();
	//弹出对话框
	$("#can").load("alert/alert_delete_activity.html");
}
//弹出高级检索的对话框
function alertHighSearch(){
	//显示背景色
	$(".opacity_bg").show();
	//弹出对话框
	$("#can").load("alert/alert_highsearch.html");
}