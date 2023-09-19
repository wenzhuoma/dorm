layui.config({
	base : "/js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;

	//添加学生
	$(".newsAdd_btn").click(function(){
		var index = layui.layer.open({
			title : "add student information",
			type : 2,
			content : "/dmanager/studentAdd",
			success : function(layero, index){
				layui.layer.tips('click here to go back List', '.layui-layer-setwin .layui-layer-close', {
					tips: 3
				});
			}
		})
		//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
		$(window).resize(function(){
			layui.layer.full(index);
		})
		layui.layer.full(index);
	})

    //分配宿舍
    $(".dormAssign_btn").click(function(){
        //alert('dormAssign_btn');
        var index = layui.layer.open({
            title : "assign dorm",
            type : 2,
            content : "/dmanager/toDormChoose",
            success : function(layero, index){
                layui.layer.tips('点击此处返回学生管理列表', '.layui-layer-setwin .layui-layer-close', {
                    tips: 3
                });
            }
        })
        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function(){
            layui.layer.full(index);
        })
        layui.layer.full(index);
    })

    $(".toPage").click(function(){
        $("#pageForm").submit();
    })

    $(".previousPage").click(function(){
        if(parseInt($("#pageNumber").val())==1){
            $("#pageNumber").val(1);
            return;
        }else{
            $("#pageNumber").val(parseInt($("#pageNumber").val())-1);
            $("#pageForm").submit();
        }
    })

    $(".nextPage").click(function(){
        if(parseInt($("#pageNumber").val())==parseInt($("#size").val())){
            $("#pageNumber").val(parseInt($("#size").val()));
            return;
        }else{
            $("#pageNumber").val(parseInt($("#pageNumber").val())+1);
            $("#pageForm").submit();
        }
    })

    $(".toPageOne").click(function(){
        $("#pageNumber").val(1);
        $("#pageForm").submit();
    })

    $(".toPageLast").click(function(){
        $("#pageNumber").val(parseInt($("#size").val()));
        $("#pageForm").submit();
    })

    $("#pageSize").change(function(){
        $("#pageForm").submit();
    })

})
