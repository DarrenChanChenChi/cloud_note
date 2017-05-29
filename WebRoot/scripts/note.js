function createNoteLi(noteId,noteTitle){
	//拼成一个笔记li
	//把noteId绑定到笔记li上
	//将li添加到笔记的ul上
	var sli = '<li class="online">'+
		'<a>'+
			'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>'+
		'</a>'+
		'<div class="note_menu" tabindex="-1">'+
			'<dl>'+
				'<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>'+
				'<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>'+
				'<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>'+
			'</dl>'+
		'</div>'+
	'</li>';
	//把noteId绑定到笔记li上
	var $li = $(sli);
	$li.data("noteId",noteId);
	$("#note_list").append($li);
}

//单击笔记本，显示笔记列表
function loadNotes(){
	//切换显示列表
	$("#pc_part_6").hide();//分享笔记
	$("#pc_part_5").hide();//预览笔记
	$("#pc_part_2").show();//全部笔记
	$("#pc_part_3").show();//编辑笔记
	$("#pc_part_4").hide();//回收站列表
	$("#pc_part_7").hide();//收藏列表
	$("#pc_part_8").hide();//活动列表
	//将当前点中的笔记本设置为选中状态
	$("#book_list li a").removeClass("checked");
	$(this).find("a").addClass("checked");
	//获取请求提交数据（获取当前点中li的笔记本ID）
	var bookId = $(this).data("bookId");
	var bookName = $(this).text();
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
				$("#note_list li").remove();
				//循环笔记本信息生成li，这段代码是为了显示笔记本没有笔记时的"X"号
				if(notes.length==0){
					//alert("------");
					$("#book_list li a.checked").parent().html(
					'<a class="checked">'+
					'<i class="fa fa-book" title="online" rel="tooltip-bottom"></i>'+
					bookName+
					'	<button type="button" id="bookDelete" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-times"></i></button>'+
					'</a>'
					);
				}
				for(var i = 0; i<notes.length; i++){
					var noteId = notes[i].cn_note_id;
					var noteTitle = notes[i].cn_note_title;
					createNoteLi(noteId,noteTitle);
				}
			}
		},
		error:function(){
			alert("加载笔记本列表异常");
		}
	});
}

//单击笔记，显示笔记信息
function loadNote(){
	//设置单击的笔记选中
	$("#note_list li a").removeClass("checked");
	$(this).find("a").addClass("checked");
	//获取要提交的数据
	var noteId = $(this).data("noteId");
	//发送Ajax请求
	$.ajax({
		url:"note/load.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var note = result.data;//返回的笔记json对象
				var noteTitle = note.cn_note_title;
				var noteBody = note.cn_note_body;
				var createTime = note.createTime;
				$("#input_note_title").val(noteTitle);//设置标题
				um.setContent(noteBody);//设置内容
				$("#create_date").html("笔记创建日期："+createTime);//设置笔记创建时间
			}
		},
		error:function(){
			alert("加载笔记信息异常");
		}
	});
}

//确认添加笔记
function sureAddNote(){
	//加载笔记本列表
	loadBooks();
	//获取请求提交数据
	var noteName = $("#input_note").val().trim();
	if(noteName==""){
		alert("笔记名不能为空");
		return;
	}
	var bookId =  $("#book_list li a[class='checked']").parent().data("bookId");
	//alert(bookId);
	//数据格式检查
	//发送Ajax请求
	$.ajax({
		url:"note/add.do",
		type:"post",
		data:{"noteName":noteName,"userId":userId,"bookId":bookId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var noteId = result.data;//笔记ID
				var noteTitle = noteName;
				createNoteLi(noteId,noteTitle);
				//关闭添加笔记本对话框
				closeWindow();
				//弹出成功提示
				alert(result.msg);
			}else if(result.status==3){
				alert(result.msg);
			}
		},
		error:function(){
			alert("创建笔记失败");
		}
	});
}

//确认保存笔记
function modifyNote(){
	//获取请求数据
	var noteName = $("#input_note_title").val().trim();
	var noteBody = um.getContent();
	var noteId = $("#note_list li a.checked").parent().data("noteId");
	if(noteName==""){
		alert("笔记名为空");
		return;
	}
	var $title = $("#note_list li a.checked").parent().siblings();
	for(var i = 0; i < $title.length; i++){
		//text()可以取到中间的文本
		if(noteName==$($title[i]).find("a").text()){
			alert("笔记名重复");
			return;
		}
	}
	//alert(noteBody);
	//数据格式验证
	//发送Ajax请求
	$.ajax({
		url:"note/modify.do",
		type:"post",
		data:{"noteName":noteName,"noteBody":noteBody,"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var map = result.data;//返回的笔记json对象
				var noteTitle = map.title;
				var noteBody = map.body;
				$("#note_list li a.checked").html('<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>');
				alert(result.msg);
			}else if(result.status==3){
				alert(result.msg);
			}
		},
		error:function(){
			alert("编辑笔记失败");
		}
	});
}

//显示笔记菜单
function showNoteMenu(){
	//获取当前点中的笔记li
	$li = $(this).parents("li");
	//将其他菜单隐藏
	//$("#note_list .note_menu").hide();
	//将当前li的菜单显示
	var $menu = $li.find(".note_menu");
	if($menu.is(":hidden")){
		$("#note_list .note_menu").hide();
		$menu.slideDown(500);
	}else{
		$menu.slideUp(500);
	}
}


//分享笔记功能
function shareNote(){
	//获取笔记ID
	var $li = $(this).parents("li");
	var noteId = $li.data("noteId");
	//发送Ajax请求
	$.ajax({
		url:"note/share.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert(result.msg);
			}else if(result.status==1){
				alert(result.msg);
			}
		},
		error:function(){
			alert("分享笔记异常");
		}
	});
}


//确认转移笔记操作
function sureMoveNote(){
	//获取请求参数
	var bookId = $("#moveSelect").val();
	var $noteLi = $("#note_list li a.checked").parent();
	var noteId = $noteLi.data("noteId");
	//alert(bookId);
	//发送Ajax请求
	$.ajax({
		url:"note/move.do",
		type:"post",
		data:{"bookId":bookId,"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//关闭转移对话框
				closeWindow();
				//移除笔记li
				$noteLi.remove();
				//弹出提示信息
				alert(result.msg);
			}else if(result.status==3){
				closeWindow();
				alert(result.msg);
			}
			
		},
		error:function(){
			alert("转移笔记失败");
		}
	});
}

//搜索笔记
function searchNote(event){
	var code = event.keyCode;
	//alert(code);
	if(code==13){//触发回车键
		//切换显示列表
		$("#pc_part_6").show();//将分享笔记列表显示
		$("#pc_part_5").show();//将预览笔记显示
		$("#pc_part_2").hide();//全部笔记
		$("#pc_part_3").hide();//编辑笔记
		$("#pc_part_4").hide();//回收站列表
		$("#pc_part_7").hide();//收藏列表
		$("#pc_part_8").hide();//活动列表
		//清空原有内容
		$("#noput_note_title").html("");
		$("#noput_note_title").next().html("");
		num = 1;//分页统计页数
		//获取请求参数
		var keyword = $("#search_note").val().trim();
		var pageSize = 10;
		var current = 1;
	    //发送Ajax请求
	    $.ajax({
	    	url:"note/search.do",
	    	type:"post",
	    	data:{"keyword":keyword,"pageSize":pageSize,"current":current},
	    	dataType:"json",
	    	success:function(result){
	    		if(result.status==0){
	    			//清空原有列表
	    			$("#search_list").empty();
	    			
	    			var notes = result.data;
	    			//循环生成列表li
	    			for(var i = 0; i < notes.length; i++){
	    				var title = notes[i].cn_share_title;
	    				var id = notes[i].cn_share_id;
	    				//拼字符串
	    				var sli = '<li class="online">';
							sli += '  <a>';
							sli += '	<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
							sli += title;
							sli += '	<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down" id="btn_like" title="收藏笔记"><i class="fa fa-star"></i></button>';
							sli += '  </a>';
							sli += '</li>';
	    				//绑定分享ID
	    				var $li = $(sli);
	    				$li.data("shareId",id);
	    				//添加到搜索结果列表区
	    				$("#search_list").append($li);
	    			}
	    		}
	    	},
	    	error:function(){
	    		alert("搜索分享笔记本异常");
	    	}
	    });
	  
	}
}

//分页
function sureAddPage(){
	//获取参数
	var keyword = $("#search_note").val().trim();
	var current = ++num;
	var pageSize = 10;
	//alert(current);
	//参数格式校验
	//发送Ajax请求
	$.ajax({
		url:"note/search.do",
		type:"post",
		data:{"keyword":keyword,"current":current,"pageSize":pageSize},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var notes = result.data;
    			//循环生成列表li
    			for(var i = 0; i < notes.length; i++){
    				var title = notes[i].cn_share_title;
    				var id = notes[i].cn_share_id;
    				//拼字符串
    				var sli = '<li class="online">';
						sli += '  <a>';
						sli += '	<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
						sli += title;
						sli += '	<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down" id="btn_like" title="收藏笔记"><i class="fa fa-star"></i></button>';
						sli += '  </a>';
						sli += '</li>';
    				//绑定分享ID
    				var $li = $(sli);
    				$li.data("shareId",id);
    				//添加到搜索结果列表区
    				$("#search_list").append($li);
    			}
			}
		},
		error:function(){
			alert("加载分页失败");
		}
	});
}

//单击搜索笔记，显示笔记信息
function loadSearchNote(){
	$("#comment-box").show();
	//设置单击的笔记选中
	$("#search_list li a").removeClass("checked");
	$(this).find("a").addClass("checked");
	//清空原有内容
	$("#noput_note_title").html("");
	$("#noput_note_title").next().html("");
	//获取shareId
	var $li = $("#search_list li a.checked").parent();
	var shareId = $li.data("shareId");
	
	//数据格式校验
	//发送Ajax请求
	$.ajax({
		url:"note/loadshare.do",
		type:"post",
		data:{"shareId":shareId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var share = result.data;//返回的是json对象
				var noteTitle = share.cn_share_title;
				var noteBody = share.cn_share_body;
				$("#noput_note_title").html(noteTitle);
				$("#noput_note_title").next().html(noteBody);
				//alert(shareId+",,,"+noteTitle);
				$("#comment-box").empty();
 	  			toggleDuoshuoComments("#comment-box",shareId,noteTitle);
			}
		},
		error:function(){
			alert("加载共享信息异常");
		}
	});
}

/**
 * 认将笔记添加到回收站
 */
function sureRecycleNote(){
	//获取笔记ID
	var $li = $("#note_list li a.checked").parent();
	var noteId = $li.data("noteId");
	//发送Ajax请求
	$.ajax({
		url:"note/recycle.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//删除笔记列表的li
				$li.remove();
//				//实例化Ueditor编辑器
//				um = UM.getEditor('myEditor');
				um.setContent("");
				$("#input_note_title").val("");
				//关闭确认对话框
				closeWindow();
				//弹出成功删除提示
				alert(result.msg);
				loadBooks();
			}
		},
		error:function(){
			alert("删除笔记异常");
		}
		
	});
}

/**
 * 单击回收站按钮，进入回收站列表
 */
function showRecycleNotes(){
	
	$("#comment-box").hide();
	//清空原有信息
	$("#noput_note_title").html("");
	$("#noput_note_title").next().html("");
	$.ajax({
		url:"note/showrecycle.do",
		type:"post",
		data:{"userId":userId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var notes = result.data;//笔记本json对象
				//切换显示列表
				$("#pc_part_6").hide();//将分享笔记列表显示
				$("#pc_part_5").show();//将预览笔记显示
				$("#pc_part_2").hide();//全部笔记
				$("#pc_part_3").hide();//编辑笔记
				$("#pc_part_4").show();//回收站列表
				$("#pc_part_7").hide();//收藏列表
				$("#pc_part_8").hide();//活动列表
				//清除原有的笔记列表
				$("#recycle_list li").remove();
				//循环笔记本信息生成li
				for(var i = 0; i<notes.length; i++){
					var noteId = notes[i].cn_note_id;
					var noteTitle = notes[i].cn_note_title;
					//拼成一个笔记li
					//把noteId绑定到笔记li上
					//将li添加到笔记的ul上
					var sli = '<li class="disable"><a><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> '+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_delete" title="彻底删除"><i class="fa fa-times"></i></button><button type="button" class="btn btn-default btn-xs btn_position_2 btn_replay" title="恢复至…"><i class="fa fa-reply"></i></button></a></li>';
					//把noteId绑定到笔记li上
					var $li = $(sli);
					$li.data("noteId",noteId);
					$("#recycle_list").append($li);
				}
			}
		},
		error:function(){
			alert("加载回收站笔记失败");
			}
		});
}

/**
 * 单击回收站笔记，显示笔记信息
 */
function loadRecycleNote(){
	//设置单击的笔记选中
	$("#recycle_list li a").removeClass("checked");
	$(this).find("a").addClass("checked");
	//清空原有内容
	$("#noput_note_title").html("");
	$("#noput_note_title").next().html("");
	//获取noteId
	var $li = $("#recycle_list li a.checked").parent();
	var noteId = $li.data("noteId");
	//数据格式校验
	//发送Ajax请求
	$.ajax({
		url:"note/load.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var note = result.data;//返回的笔记json对象
				var noteTitle = note.cn_note_title;
				var noteBody = note.cn_note_body;
				$("#noput_note_title").html(noteTitle);
				$("#noput_note_title").next().html(noteBody);
			}
		},
		error:function(){
			alert("加载笔记信息失败");
		}
	});
}

/**
 * 确认从回收站恢复笔记
 */
function sureReplayNote(){
	//获取笔记ID和笔记本ID
	var bookId = $("#replaySelect").val();
	var $li = $("#recycle_list li a.checked").parent();
	var noteId = $li.data("noteId");
	//alert(noteId);
	//进行样式检查
	//发送Ajax请求
	$.ajax({
		url:"note/recover.do",
		type:"post",
		data:{"bookId":bookId,"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//关闭转移对话框
				closeWindow();
				//移除笔记li
				$li.remove();
				//弹出提示信息
				alert(result.msg);
				loadBooks();
			}else if(result.status==3){
				closeWindow();
				alert(result.msg);
			}
		},
		error:function(){
			alert("恢复笔记失败");
		}
	});
}

/**
 * 彻底删除笔记
 */
function sureDeleteNote(){
	//获取noteId
	var $li = $("#recycle_list li a.checked").parent();
	var noteId = $li.data("noteId");
	//进行格式校验
	//发送Ajax请求
	$.ajax({
		url:"note/delete.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//关闭转移对话框
				closeWindow();
				//移除笔记li
				$li.remove();
				//弹出提示信息
				alert(result.msg);
			}
		},
		error:function(){
			alert("删除信息失败");
		}
	});
}
/**
 * 确认清空回收站
 */
function sureDeleteAll(){
	//获取参数
	//参数格式校验
	//发送Ajax请求
	$.ajax({
		url:"note/deleteall.do",
		type:"post",
		data:{"userId":userId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//关闭转移对话框
				closeWindow();
				//清空回收站列表
				$("#recycle_list").empty();
				//弹出提示信息
				alert(result.msg);
			}
		},
		error:function(){
			alert("清空回收站失败");
		}
	});
}

/**
 * 收藏笔记功能
 */
function likeNote(){
	//获取shareId
	var $li = $(this).parents("li");
	var shareId = $li.data("shareId");
	//进行校验
	//发送Ajax请求
	$.ajax({
		url:"note/like.do",
		type:"post",
		data:{"shareId":shareId,"userId":userId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert(result.msg);
			}else if(result.status==1){
				alert(result.msg);
			}
		},
		error:function(){
			alert("收藏笔记异常");
		}
		
	});
}


/**
 * 单击收藏夹按钮，进入收藏夹列表
 */
function showLike(){
	$("#comment-box").hide();
	//清空原有信息
	$("#noput_note_title").html("");
	$("#noput_note_title").next().html("");
	$.ajax({
		url:"note/showlike.do",
		type:"post",
		data:{"userId":userId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var notes = result.data;//笔记本json对象
				//切换显示列表
				$("#pc_part_6").hide();//将分享笔记列表显示
				$("#pc_part_5").show();//将预览笔记显示
				$("#pc_part_2").hide();//全部笔记
				$("#pc_part_3").hide();//编辑笔记
				$("#pc_part_4").hide();//回收站列表
				$("#pc_part_7").show();//收藏列表
				$("#pc_part_8").hide();//活动列表
				//清除原有的笔记列表
				$("#like_list li").remove();
				//循环笔记本信息生成li
				for(var i = 0; i<notes.length; i++){
					var id = notes[i].cn_like_id;
					var title = notes[i].cn_share_title;
					//拼成一个笔记li
					//把Id绑定到笔记li上
					//将li添加到笔记的ul上
					var sli = '<li class="idle"><a ><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> '+title+'<button type="button" class="btn btn-default btn-xs btn_position btn_delete" id="btn_deletelike" title="取消收藏"><i class="fa fa-times"></i></button></a></li>';
					//把noteId绑定到笔记li上
					var $li = $(sli);
					$li.data("likeId",id);
					$("#like_list").append($li);
					
				}
			}
		},
		error:function(){
			alert("加载收藏笔记失败");
		}
	});
}

/**
 * 单击收藏笔记，加载笔记信息
 */
function loadLike(){
	//设置单击的笔记选中
	$("#like_list li a").removeClass("checked");
	$(this).find("a").addClass("checked");
	//清空原有内容
	$("#noput_note_title").html("");
	$("#noput_note_title").next().html("");
	//获取likeId
	var $li = $("#like_list li a.checked").parent();
	var likeId = $li.data("likeId");
	//数据格式校验
	//发送Ajax请求
	$.ajax({
		url:"note/loadlike.do",
		type:"post",
		data:{"likeId":likeId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var like = result.data;//返回的笔记json对象
				var shareTitle = like.cn_share_title;
				var shareBody = like.cn_share_body;
				$("#noput_note_title").html(shareTitle);
				$("#noput_note_title").next().html(shareBody);
			}
		},
		error:function(){
			alert("加载笔记信息失败");
		}
	});
}
/**
 * 取消收藏
 */
function cancelLike(){
	//获取likeId
	var $li = $(this).parents("li");
	var likeId = $li.data("likeId");
	//发送Ajax请求
	$.ajax({
		url:"note/deletelike.do",
		type:"post",
		data:{"likeId":likeId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert(result.msg);
				showLike();
			}
		},
		error:function(){
			alert("取消收藏失败");
		}
	});
}
/**
 * 显示用户活动笔记列表
 */
function showActivityNotes(){
	$("#comment-box").hide();
	//清空原有信息
	$("#noput_note_title").html("");
	$("#noput_note_title").next().html("");
	$.ajax({
		url:"activity/loaduser.do",
		type:"post",
		data:{"userId":userId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var notes = result.data;//笔记本json对象
				//切换显示列表
				$("#pc_part_6").hide();//分享笔记
				$("#pc_part_5").show();//预览笔记
				$("#pc_part_2").hide();//全部笔记
				$("#pc_part_3").hide();//编辑笔记
				$("#pc_part_4").hide();//回收站列表
				$("#pc_part_7").hide();//收藏列表
				$("#pc_part_8").show();//活动列表
				//清除原有的笔记列表
				$("#activity_list li").remove();
				//循环笔记本信息生成li
				for(var i = 0; i<notes.length; i++){
					var noteActivityId = notes[i].cn_note_activity_id;
					var noteTitle = notes[i].cn_note_activity_title;
					//拼成一个笔记li
					//把noteId绑定到笔记li上
					//将li添加到笔记的ul上
					var sli = '<li class="disable"><a><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> '+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_delete" title="取消参加活动"><i class="fa fa-times"></i></button></a></li>';
					//把noteId绑定到笔记li上
					var $li = $(sli);
					$li.data("noteActivityId",noteActivityId);
					$("#activity_list").append($li);
				}
			}
		},
		error:function(){
			alert("加载活动笔记失败");
			}
		});
}
/**
 * 单击活动笔记，显示活动笔记信息
 */
function loadActivityNote(){
	//设置单击的笔记选中
	$("#activity_list li a").removeClass("checked");
	$(this).find("a").addClass("checked");
	//清空原有内容
	$("#noput_note_title").html("");
	$("#noput_note_title").next().html("");
	//获取noteId
	var $li = $("#activity_list li a.checked").parent();
	var noteActivityId = $li.data("noteActivityId");
	//数据格式校验
	//发送Ajax请求
	$.ajax({
		url:"activity/loadnote.do",
		type:"post",
		data:{"noteActivityId":noteActivityId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var noteActivity = result.data;//返回的笔记json对象
				var noteTitle = noteActivity.cn_note_activity_title;
				var noteBody = noteActivity.cn_note_activity_body;
				$("#noput_note_title").html(noteTitle);
				$("#noput_note_title").next().html(noteBody);
			}
		},
		error:function(){
			alert("加载笔记信息失败");
		}
	});
}
/**
 * 确认删除活动笔记
 */
function sureDeleteNoteActivity(){
	//获取noteId
	var $li = $("#activity_list li a.checked").parent();
	var noteActivityId = $li.data("noteActivityId");
	//进行格式校验
	//发送Ajax请求
	$.ajax({
		url:"activity/delete.do",
		type:"post",
		data:{"noteActivityId":noteActivityId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//关闭转移对话框
				closeWindow();
				//移除笔记li
				$li.remove();
				//弹出提示信息
				alert(result.msg);
			}
		},
		error:function(){
			alert("删除信息失败");
		}
	});
}

/**
 * 确认进行高级检索
 */
function sureHighSearch(){
	//切换显示列表
	$("#pc_part_6").hide();//将分享笔记列表显示
	$("#pc_part_5").hide();//将预览笔记显示
	$("#pc_part_2").show();//全部笔记
	$("#pc_part_3").show();//编辑笔记
	$("#pc_part_4").hide();//回收站列表
	$("#pc_part_7").hide();//收藏列表
	$("#pc_part_8").hide();//活动列表
	//获取请求参数
		var title = $("#title").val().trim();
		var status = $("#status").val();
		var beginTime = $("#beginTime").val().trim();
		var endTime = $("#endTime").val().trim();
		//发送Ajax请求
		$.ajax({
			url:"note/highsearch.do",
			type:"post",
			data:{"title":title,"status":status,"beginTime":beginTime,"endTime":endTime,"userId":userId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					//关闭转移对话框
					closeWindow();
					//清除原有的笔记列表
					$("#note_list li").remove();
					var notes = result.data;//笔记对象json数组
					alert("查询完毕，共"+notes.length+"条结果");
					setTimeout(function(){
						//关闭转移对话框
						closeWindow();
					},1000);
					for(var i = 0; i < notes.length; i++){
						var noteId = notes[i].cn_note_id;
						var noteTitle = notes[i].cn_note_title;
						var noteStatus = notes[i].cn_note_status_id;
						var createTime = notes[i].createTime;//匹配getCreateTime()方法
						var statusTxt = noteStatus=="1"?"正常":"回收站";
						createNoteLi(noteId,noteTitle);
					}
				}
			},
			error:function(){
				alert("搜索失败");
			}
		});
}
