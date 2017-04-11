function modalShow(url, width) {
	var html = '<div class="dialog-mask"></div>';
	if (width == undefined) {
		width = "50%";
	}
	html += '<div class="dialog-win" style="position: fixed; width: ' + width
			+ '; z-index: 11;left:0;top:0;right:0;margin:auto"></div>';
	$(document.body).append(html);
	$(".dialog-mask").click(function() {
		modalHide();
	});
	$(".dialog-win").load(url, function() {
		$(".dialog-win .close").click(function() {
			modalHide();
		});
	});
}
function modalHide() {
	$(".dialog-win").remove();
	$(".dialog-mask").remove();
}
//视频弹出层
function openVideo(title,url){
	layer.open({
		type: 1,
		title: title,
		area: ['830px', '550px'],
		shadeClose: true,
		success:function(layer){
			layer.find(".layui-layer-content").load(url,{});
			layer.removeClass("layui-anim");//禁用这个动画样式，影响全屏观看
		}
	}); 
}
// 通用弹出层iframe
function openView(title, url,width,height) {
	if (width == undefined) {
		width = "760px";
	} 
	if (height == undefined) {
		height = "480px";
	} 
	layer.open({
		type : 2,
		title : title,
		area : [ width, height ],
		shadeClose : true,
		content : url
	});
}
// 通用弹出层Div
function openDiv(title,url, width, height) {
	if (width == undefined) {
		width = "600px";
	}
	if (height == undefined) {
		height = "360px";
	}
	layer.open({
		title:title,
		type : 1,
		skin : 'layui-layer-demo', // 样式类名
		area : [ width, height ],
		shift : 2,
		maxmin: true,
		shadeClose : true, // 开启遮罩关闭
		success : function(layer) {
			layer.find(".layui-layer-content").load(url, {});
		}
	});
}
// 询问框
function deleteData(url) {
	layer.confirm('确定删除吗？', {
		shadeClose : true,
		btn : [ '确定' ]
	// 按钮
	}, function() {
		location.href = url;
	});
}
function deleteForm(id,obj) {
	layer.confirm('确定删除吗？', {
		shadeClose : true,
		btn : [ '确定' ]
	}, function() {
		dealAll(id,$(obj).attr('action'));
	});
}
function dealAll(id,url) {
	$.ajax({
		url:url,
		type:'post',
		data:$('#'+id+' input[name="id"]:checked').serialize(),
		dataType:'text',
		success:function(data){
			if(data=='success'){
				location.href = location.href;
			}else{
				layer.msg(data);
			}
		}
	});
}
function ajaxDeleteData(url, func) {
	layer.confirm('确定删除吗？', {
		shadeClose : true,
		btn : [ '确定' ]
	// 按钮
	}, function() {
		$.ajax({
			type : "post",
			async : false,
			url : url,
			dataType : "text",
			success : function(msg) {
				if (msg == "success") {
					if (typeof func === "function") {
						func();
					} else {
						location.href = location.href;
					}
					layer.closeAll();
				}
			}
		});
	});
}
function ajaxDeal(url) {
	layer.load(1, {
		shade : [ 0.1, '#fff' ]
	// 0.1透明度的白色背景
	});
	$.ajax({
		type : "post",
		url : url,
		dataType : "text",
		success : function(msg) {
			if (msg == "success") {
				layer.closeAll();
				layer.msg('操作成功');
			}
		}
	});
}
function ajaxCheck(url) {
	var flag = false;
	layer.load(1, {
		shade : [ 0.1, '#fff' ]
	// 0.1透明度的白色背景
	});
	$.ajax({
		type : "post",
		async : false,
		url : url,
		dataType : "text",
		success : function(msg) {
			layer.closeAll();
			if (msg == "success") {
				flag = true;
			}
		}
	});
	return flag;
}
// tab层
function openTab(title, url) {
	layer.open({
		title : title,
		type : 2,
		area : [ '800px', '600px' ],
		shadeClose : true, // 开启遮罩关闭
		content : [ url, 'no' ]
	});
}
// 星标动态样式
$(function() {
	$(".icon-star-o").parent().each(function() {
		var box = $(this);
		box.mouseover(function() {
			box.find(".icon-star-o").show();
		}).mouseout(function() {
			box.find(".icon-star-o").hide();
		});
	});
});
// ajax星号标记
function favorite(url, obj) {
	$.ajax({
		type : "post",
		url : url,
		dataType : "text",
		success : function(msg) {
			if (msg == "success") {
				$(obj).parent().find(".icon-star").hide();
				$(obj).parent().find(".icon-star-o").show();
			} else {
				$(obj).parent().find(".icon-star-o").hide();
				$(obj).parent().find(".icon-star").show();
			}
		}
	});
}
function love(url, obj) {
	$.ajax({
		type : "post",
		url : url,
		dataType : "text",
		success : function(msg) {
			if (msg == "success") {
				$(obj).html(parseInt($(obj).html()) + 1);
			}
		}
	});
}
//获取页面某块id区域内部的表单数据，封装成json格式
function getJsonData(id){
	var data = "";
    $('#'+id+' input[name][type!="checkbox"][type!="radio"]').each(function () {
        data += "\"" + $(this).attr("name") + "\":\"" + $(this).val() + "\",";
    });
    $('#'+id+' input:checked').each(function () {
    	data += "\"" + $(this).attr("name") + "\":\"" + $(this).val() + "\",";
    });
    return eval("({" + data.substring(0, data.length - 1) + "})");
}