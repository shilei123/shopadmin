var eventsTypeHtml = "";
//加载layui的laydate组件
layui.use('laydate', function(){});
$("#startTime").click(function() {
	layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'});
});
$("#endTime").click(function() {
	layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'});
});	
//初始化
	$(function() {
		queryEventsType();
		var vId =$("#vId").val();
		if(vId != ""){
			queryEcents(vId);
		}else{
			clearForm();
		}
	});
	
	var queryEventsType = function() {
		var isuse = $("#isuse");
		isuse.empty();
			$.ajax({
				url :path_ + "/view/shop/events/events!queryEventsType.action",
				type : 'POST',
				data : null,
				dataType: "json",
				success : function(data) {
					var html = "<option value='-1'>-请选择-</option>";
					$(data.dictionaryList).each(function(index) {
						var eventsType = data.dictionaryList[index];
						html += "<option value='" + eventsType.code + "'>" + eventsType.name + "</option>";
					});
					eventsTypeHtml = html;
					isuse.append(html);
				}
			});
	};
	var queryEcents = function(vId){
		data = {"events.id" : vId};
		$.ajax({
			type : "POST",
			url : path_ + "/view/shop/events/events!queryEvents.action",
			data : data,
			dataType : "json",
			success : function(data) {
				setIsuse();
				$("#goodsChildTable tbody").html('');
				setEventsForm(data);
			}
		});
	};
	
	//选择商品
	$("#chooseGoodsBtn").click(function(){
		//打开商品列表窗口
		var url=path_ +"/view/shop/advertise/queryGoods.jsp";
		showLayerModal("选择商品",url,800,400);
	});

	//选择商品的回调函数
	var selectGoods = function(obj) {
		$('#goodsId').val(obj.goodsId);
		$('#goodsName').val(obj.goodsName);
	};

	var checkGoodsSumbit = function(){
		var goodsName=$("#goodsName").val();
		if(goodsName.length == 0){
			$("#errorMsg").html("请输入完整的商品信息！");
			$("#goodsName").focus();
			return false;
		}
		var eventsMoney=$("#eventsMoney").val();
		if(eventsMoney.length == 0){
			$("#errorMsg").html("请输入完整的商品信息！");
			$("#eventsMoney").focus();
			return false;
		}
		$("#errorMsg").html("&nbsp;");
		return true;
	};


	//添加商品按钮
	$(".addGoodsParam").click(function() {
		addGoodsParams();
	});

	//添加商品函数
	var addGoodsParams = function() {
		if(!checkGoodsSumbit()) {
			return;
		}
		
		var childGoodsValue = "";
		var existsGoodsKey = "";
		var childGoodsLabel = "";
		
		var goodsName  = $("#goodsName").val();
		var goodsPrice = $("#eventsMoney").val();
		var goodsRange = $("#scope").val();
		
		var split = $("#goodsId").val();
		
			var valName = $("#scope").find("option:selected").text();
			var propName = "生效范围";
			
			existsGoodsKey = split ;
			childGoodsLabel =  propName + "：&nbsp;&nbsp;" + valName + "&nbsp;";
			childGoodsValue = split;
			
		//根据标识检测是否填过相同的商品
		if($("#"+existsGoodsKey).length == 0) {
			var trHtml = "";
			trHtml += "<tr>";
			trHtml += "    <td width='38%'>&nbsp;商品名称：<input name='goodsName' value='"+goodsName+"'  class='am-form-field' readonly='readonly' style=\"width:150px;display: inline;\"/>&nbsp;<input type='hidden' id='"+existsGoodsKey+"' value='"+childGoodsValue+"'/></td>";
			trHtml += "    <td width='36%'>&nbsp;商品活动价格：<input name='goodsPrice' value='"+goodsPrice+"' class='am-form-field' readonly='readonly' style=\"width:150px;display: inline;\"/>&nbsp;</td>";
			trHtml += "    <td style='text-align:left;'><input type='hidden' name='goodsRange' value='"+goodsRange+"' style=\"width:80px;\"/><div style='margin-top:5px;margin-bottom:5px;'>"+childGoodsLabel+"</div></td>";
			trHtml += "    <td>&nbsp;<input type='hidden' name='eventsGoodsId' value=''/><a href='javascript:void(0);'onclick='deleteGoodChilds(\""+existsGoodsKey+"\")'><span class='am-icon-minus-square' style='width: auto;color:red;font-size: 18px;' title='删除'></span></a>&nbsp;</td>";
			trHtml += "</tr>";
			$("#goodsChildTable tbody").prepend(trHtml);
		} else {
			$("#errorMsg").html("该商品已经添加！");
		}
		$("#goodsId").val('');
		$("#goodsName").val('');
		$("#eventsMoney").val('');
		$("#scope").val('1');
		showOrHideGoodsPriceTable();
	};

	//删除所选商品
	var deleteGoodChilds = function(existsGoodsKey) {
		if($("#"+existsGoodsKey).length > 0) {
			$("#"+existsGoodsKey).parent().parent().remove();
		}
		showOrHideGoodsPriceTable();
	};

	//删除活动商品中间表商品
	var delEventsGoodChilds = function(existsGoodsKey,eventsgoodsid){
		$.ajax({
			url :path_ + "/view/shop/events/events!delEventsGoods.action",
			type : 'POST',
			data : {"eventsGoods.id":eventsgoodsid},
			dataType: "json",
			success : function(data) {
				window.parent.showMsg("活动商品删除成功");
			},
			error : function(e) {
				window.parent.showAlert("操作失败");
			}
		});
		if($("#"+existsGoodsKey).length > 0) {
			$("#"+existsGoodsKey).parent().parent().remove();
		}
		showOrHideGoodsPriceTable();
	};

	//隐藏和显示商品名称、价格、生效范围
	var showOrHideGoodsPriceTable = function() {
		var goodsChildCount = $("#goodsChildTable tbody tr").length; //商品数量
		if(goodsChildCount > 0) {
			$("#goodsChildDiv").parent().parent().show();
		} else {
			$("#goodsChildDiv").parent().parent().hide();
		}
	};

	//表单验证
	var checkSumbit = function() {
		var name=$("#name").val();
		if(name.length == 0){
			$("#errorMsg").html("活动名称不能为空！");
			$("#name").focus();
			return false;
		}
		
		var isuse = $("#isuse").val();
		if(isuse == "-1"){
			$("#errorMsg").html("是否启用不能为空！");
			$("#isuse").focus();
			return false;
		}
		var trs = $("#goodsChildTable tbody tr");
		if(trs.length == 0){
			$("#errorMsg").html("活动商品不能为空！");
			$("#goodsName").focus();
			return false;
		}
		
		$("#errorMsg").html("&nbsp;");
		return true;
	};

	//保存
	$("#saveBtn").click(function() {
		//表单验证
		if(!checkSumbit()) {
			return;
		}
		
		var data = getPageData();
		$.ajax({
			type : "POST",
			url : path_ + "/view/shop/events/events!save.action",
			data : data,
			dataType : "json",
			success : function(json) {
				closeCurrentWin();
				window.parent.showMsg("操作成功!");
				window.parent.queryEvents();
			},
			error : function(e) {
				window.parent.showAlert("操作失败！");
			}
		});
	});
	
	var setIsuse = function(){
		var isuse = $("#isuse");
		isuse.empty();
		isuse.append(eventsTypeHtml);
	}
	
	var clearForm = function() {
		$("#vId").val('');
		$("#name").val('');
		$("#memo").val('');
		$("#startTime").val('');
		$("#endTime").val('');
		$("#goodsChildTable tbody").html('');
		setIsuse();
		$("#errorMsg").html('&nbsp;');
	};

	var setEventsForm = function(data) {
		setIsuse();
		$("#vId").val(data.eventsList[0].id);
		$("#name").val(data.eventsList[0].name);
		$("#isuse").val(data.eventsList[0].isuse);
		$("#memo").val(data.eventsList[0].memo);
		$("#startTime").val(data.eventsList[0].startTime);
		$("#endTime").val(data.eventsList[0].endTime);
		var trHtml_ = "";
		if(data.eventsList[0].eventsgoodsid != null){
		$(data.eventsList).each(function(index) {
			var eventsGoods = data.eventsList[index];
			var propName = "生效范围";
			var valName = "";
			var existsGoodsKey=eventsGoods.goodsId;
			var eventsgoodsid = eventsGoods.eventsgoodsid;
			if(eventsGoods.scope == '1'){
				valName ="所有";
			}
			if(eventsGoods.scope == '0'){
				valName ="活动";
			}
			var childGoodsLabels =  propName + "：&nbsp;&nbsp;" + valName + "&nbsp;";
			trHtml_ += "<tr>";
			trHtml_ += "    <td width='38%'>&nbsp;商品名称：<input name='goodsName' value='"+eventsGoods.goodsName+"'  class='am-form-field' readonly='readonly' style=\"width:150px;display: inline;\"/>&nbsp;<input type='hidden' id='"+existsGoodsKey+"' value='"+existsGoodsKey+"'/></td>";
			trHtml_ += "    <td width='36%'>&nbsp;商品活动价格：<input name='goodsPrice' value='"+eventsGoods.eventsMoney+"' class='am-form-field' readonly='readonly' style=\"width:150px;display: inline;\"/>&nbsp;</td>";
			trHtml_ += "    <td style='text-align:left;'><input type='hidden' name='goodsRange' value='"+eventsGoods.scope+"' style=\"width:80px;\"/><div style='margin-top:5px;margin-bottom:5px;'>"+childGoodsLabels+"</div></td>";
			trHtml_ += "    <td>&nbsp;<input type='hidden' name='eventsGoodsId' value='"+eventsgoodsid+"'/><a href='javascript:void(0);'onclick='delEventsGoodChilds(\""+existsGoodsKey+"\",\""+eventsgoodsid+"\")'><span class='am-icon-minus-square' style='width: auto;color:red;font-size: 18px;' title='删除'></span></a>&nbsp;</td>";
			trHtml_ += "</tr>";
		});
		}
		$("#goodsChildTable tbody").html(trHtml_);
		showOrHideGoodsPriceTable();
		$("#errorMsg").html("&nbsp;");
	};

	//获取页面数据
	var getPageData = function() {
		//得到商品信息串
		var trs = $("#goodsChildTable tbody tr");
		var childGoods = new Array();
		for (var i = 0; i < trs.length; i++) {
			var $tr = $(trs[i]);
			var tds = $tr.children("td");
			
			var childGoodsId = $(tds[0]).children("input:eq(1)").val();
			var goodsPrice = $(tds[1]).children("input:eq(0)").val();
			var goodsRange = $(tds[2]).children("input:eq(0)").val();
			var eventsGoodsId = $(tds[3]).children("input:eq(0)").val();
			
			var tempjson = {"childGoodsId":childGoodsId,"goodsPrice":goodsPrice,"goodsRange":goodsRange,"eventsGoodsId":eventsGoodsId};
			childGoods.push(tempjson);
		}

		var data = {
			"events.id" :$("#vId").val(),
			"events.name" : $("#name").val(),
			"events.isuse" : $("#isuse").val(),
			"events.startTime" : $("#startTime").val(),
			"events.endTime" : $("#endTime").val(),
			"events.memo" : $("#memo").val(),
			"eventsGoodsList" : JSON.stringify(childGoods),
		};
		return data;
	};
	
	//关闭窗口
	var closeCurrentWin = function() {
		window.parent.closeModal("editeEventsModal");
	};
	$("#closeBtn").click(closeCurrentWin);