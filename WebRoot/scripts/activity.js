/**
 * 加载活动笔记列表
 * @param activityId
 */
function loadActivityNotes(activityId){
	var pageSize = 10;
	var current = 1;
	$.ajax({
		url:"activity/loadnotes.do",
		type:"post",
		data:{"activityId":activityId,"pageSize":pageSize,"current":current},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var notes = result.data;
				//清空原有笔记列表
				$("#note_list li").remove();
				for(var i = 0; i < notes.length; i++){
					var title = notes[i].cn_note_activity_title;
					var noteActivityId = notes[i].cn_note_activity_id;
					var sli = '<li class="online"><a ><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> '+title+'<button type="button" class="btn btn-default btn-xs btn_position_3 btn_up"><i class="fa fa-thumbs-o-up"></i></button><button type="button" class="btn btn-default btn-xs btn_position_2 btn_down"><i class="fa fa-thumbs-o-down"></i></button></a></li>';
					var $li = $(sli);
					$li.data("noteActivityId",noteActivityId);
					$("#note_list").append($li);
				}
			}
		},
		error:function(){
			alert("加载参与活动笔记失败");
		}
	});
}

/**
 * 加载更多活动笔记（分页）
 * @param activityId
 * @param num
 */
function sureAddPage(activityId,num){
	var current = num;
	var pageSize = 10;
	$.ajax({
		url:"activity/loadnotes.do",
		type:"post",
		data:{"activityId":activityId,"pageSize":pageSize,"current":current},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var notes = result.data;
				//清空原有笔记列表
				for(var i = 0; i < notes.length; i++){
					var title = notes[i].cn_note_activity_title;
					var noteActivityId = notes[i].cn_note_activity_id;
					var sli = '<li class="online"><a ><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> '+title+'<button type="button" class="btn btn-default btn-xs btn_position_3 btn_up"><i class="fa fa-thumbs-o-up"></i></button><button type="button" class="btn btn-default btn-xs btn_position_2 btn_down"><i class="fa fa-thumbs-o-down"></i></button></a></li>';
					var $li = $(sli);
					$li.data("noteActivityId",noteActivityId);
					$("#note_list").append($li);
				}
			}
		},
		error:function(){
			alert("加载参与活动笔记失败");
		}
	});
}

/**
 * 加载活动笔记内容
 */
function loadActivityNote(){
	//设置单击的笔记选中
	$("#note_list li a").removeClass("checked");
	$(this).find("a").addClass("checked");
	//清空原有内容
	$("#content_body h4").html("");
	$("#content_body div").html("");
	var $li = $("#note_list li a.checked").parent();
	var noteActivityId = $li.data("noteActivityId");
	$.ajax({
		url:"activity/loadnote.do",
		type:"post",
		data:{"noteActivityId":noteActivityId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var note = result.data;
				var title = note.cn_note_activity_title;
				var body = note.cn_note_activity_body;
				var upNum = note.cn_note_activity_up;
				var downNum = note.cn_note_activity_down;
				//alert(body);
				$("#content_body h4").html(title);
				$("#content_body div").html(body);
				$("#up").html(upNum);
				$("#down").html(downNum);
			}
		},
		error:function(){
			alert("加载活动笔记信息失败");
		}
	});
}

/**
 * 点击确定，添加活动笔记
 * @param activityId
 */
function sureAdd(activityId){
	//获取noteId
	var $li = $("#tnote_list li a.checked").parents("li");
	var noteId = $li.data("noteId");
	if(noteId==null){
		alert("请选择笔记");
		return;
	}
	$.ajax({
		url:"activity/add.do",
		type:"post",
		data:{"activityId":activityId,"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//loadActivityNotes(activityId);
				alert(result.msg);
				//location.reload();
			}
			if(result.status==3){
				alert(result.msg);
				//location.reload();
			}
			setTimeout(function(){
				location.reload();
			},2000);
		},
		error:function(){
			alert("新增活动笔记失败");
		}
	});
}
/**
 * 弹出参加活动提示框，并进行选择
 */
function joinActivity() {
	$('#modalBasic_15,.opacity_bg').show();
	$('#select_notebook ul').empty();
	loadBooks();
	//点击选择笔记本
	$("#book_list").on("click","li",function(){
		//将当前点中的笔记本设置为选中状态
		$("#book_list li a").removeClass("checked");
		$(this).find("a").addClass("checked");
		//清空原有的内容
		$("#tnote_list").empty();
		//获取请求提交数据（获取当前点中li的笔记本ID）
		var bookId = $(this).data("bookId");
		//发送Ajax请求
		$.ajax({
			url:"note/loadnotes.do",
			type:"post",
			data:{"bookId":bookId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					var notes = result.data;//笔记本json对象
					//清除原有的笔记列表
					$("#tnote_list li").remove();
					//循环笔记本信息生成li
					for(var i = 0; i<notes.length; i++){
						var noteId = notes[i].cn_note_id;
						var noteTitle = notes[i].cn_note_title;
						var sli = '<li class="online"><a ><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle+'</a></li>';
						var $li = $(sli);
						$li.data("noteId",noteId);
						$("#tnote_list").append($li);
					}
				}
			},
			error:function(){
				alert("加载笔记本列表异常");
			}
		});
	});
	//点击选择笔记
	$("#tnote_list").on("click","li",function(){
		//设置单击的笔记选中
		$("#tnote_list li a").removeClass("checked");
		$(this).find("a").addClass("checked");
	});
}
