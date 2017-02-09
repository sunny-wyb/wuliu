$(function() {
	
	
	var dialog = $('.dialog-form').dialog({
		width:580
	});
	
	dialog.dialog('close');
	
	$('tbody .col-operations .btn-edit').on('click' , function() {
		event.preventDefault();
		var data = $(this).closest('tr').data('json');
		
		$('.dialog-form form input[name=id]').val(data.id);
		$('.dialog-form form input[name=name]').val(data.name);
		$('.dialog-form form input[name=nick-name]').val(data.nickName);
		$('.dialog-form form input[name=tel-number]').val(data.telephoneNumber);
		$('.dialog-form form input[name=m-number]').val(data.mobileNumber);
		$('.dialog-form form input[name=w-price]').val(data.weightPriceForDisplay);
		$('.dialog-form form input[name=v-price]').val(data.volumnPriceForDisplay);
		$('.dialog-form form input[name=address]').val(data.address);
		$('.dialog-form form input[name=shop-address]').val(data.shopAddress);
		
		dialog.dialog('open');
	});
	
	$('tbody .col-operations .btn-delete').on('click' , function() {
		event.preventDefault();
		
		var data = $(this).closest('tr').data('json');
		
		
		  $.ajax({
			url : 'checkorder.html',  
			type : 'GET',
			dataType : 'json',
			data : {'memberId' : data.id},
			success : function(result) {
				if (result.result) {
					$.show_simple_dialog('提醒',"这个用户还存在相应的订单没有删除，所以这个用户的信息不能删除");
				}
				else {
					deleteMember(data);
				}
			}
		  });
		
	});
	
	var deleteMember = function(data) {
		$('<div></div>').appendTo('body')
		  .html('<div style="font-size: 18px;margin-top: 15;">确认删除这条记录？</div>')
		  .dialog({
		      modal: true, title: '提醒', zIndex: 10000, autoOpen: true,
		      width: 'auto', resizable: false,
		      buttons: {
		          "确定": function () {
		        	  $.ajax({
		        		url : 'delete.html',  
		        		type : 'POST',
		        		dataType : 'json',
		        		data : {'id' : data.id},
		        		success : function(result) {
		        			if (result.result) {
		        				window.location.reload(true);
		        			}
		        		}
		        	  });
		          },
		          "取消": function () {
		              $(this).dialog("close");
		          }
		      },
		      close: function (event, ui) {
		          $(this).remove();
		      }
		});
	}
	
	$('.btn-area .btns .btn-edit').on('click' , function() {
	});
	
	$('.btn-area .btns .btn-save').on('click' , function(event) {
		event.preventDefault();

		var jsonArray = $('.dialog-form form').serializeArray();
		var jsonData = {};
		jsonArray.forEach(function(ele , index) {
			if (ele.value) {
				jsonData[ele.name] = ele.value.trim();
			}
		});
		if (jsonData['v-price']) {
			jsonData['v-price'] = parseInt(100 * jsonData['v-price']);;
		}
		
		if (jsonData['w-price']) {
			jsonData['w-price'] = parseInt(100 * jsonData['w-price']);
		}
		
		$.ajax({ 
			url : "save.html", 
			data : jsonData,
			dataType : 'json',
			type : 'POST',
			success: function(result){
				if (result.result) {
					window.location.reload(true);
				}
			}}
		);
	});
	
	$('.btn-area .btns .btn-cancel').on('click' , function() {
		event.preventDefault();
		dialog.dialog('close');
	});
	
	$('.function-area .btn-create').on('click' , function() {
		event.preventDefault();
		
		$('.dialog-form form input[name=id]').val('');
		$('.dialog-form form input[name=name]').val('');
		$('.dialog-form form input[name=nick-name]').val('');
		$('.dialog-form form input[name=tel-number]').val('');
		$('.dialog-form form input[name=m-number]').val('');
		$('.dialog-form form input[name=w-price]').val('');
		$('.dialog-form form input[name=v-price]').val('');
		$('.dialog-form form input[name=address]').val('');
		$('.dialog-form form input[name=shop-address]').val('');
		
		$('.dialog-form').dialog({width:580});
	});
	
	
	var totalPage = $('.pagination').data('total-page');
	var currentPage = $('.pagination').data('current-page');
	$(".pagination").Page({
	    totalPages: totalPage,//分页总数
	    liNums: 7,
	    currentPage : currentPage,
	    activeClass: 'activP', //active class style
	    callBack : function(page){
	    	var newParamStr = addParamToUrl({'page':page});
	    	window.location.href='member.html?' + newParamStr;
	    }
	});
	
	var doSearch = function() {
		var name = $('.search-content input[name=name]').val();
		var param = {};
		if (name && name.trim().length > 0) {
			param.name = name.trim();
			param.name = encodeURIComponent(param.name);
		}
		
		if (!$.isEmptyObject(param)) {
			window.location.href='member.html?' + $.param(param);
		}
		else {
			window.location.href='member.html';
		}
	}
	
	$('.search-btns .btn-search').on('click' , function() {
		doSearch();
	});
	
	$('.search-btns .btn-cancel').on('click' , function() {
		$('.search-content input[name=name]').val('');
		doSearch();
	});
	
	var addParamToUrl = function(param) {
		if (window.location.search) {
			var oldParam = $.deparam(window.location.search.substr(1));
			if (param) {
				$.each(param , function(key , value) {
					oldParam[key] = value;
				});
				return $.param(oldParam);
			}
			else {
				return window.location.search.substr(1)
			}
		}
		else {
			return $.param(param);
		}
	};
	
	var getParam = function(name) {
		if (window.location.search) {
			var paramMap = $.deparam(window.location.search.substr(1));
			return paramMap[name];
		}
		else {
			return "";
		}
	};
});