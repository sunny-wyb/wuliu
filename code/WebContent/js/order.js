$(function() {
	var dialog = $('.dialog-form').dialog({
		width:580
	});
	dialog.dialog('close');
	
	$('.col-operation .btn-edit').on('click' , function() {
		event.preventDefault();
		var ele = $($(this).siblings('script[name=form-group]').html());
		addToDialog(ele);
		bind();
		dialog.dialog('open');
	});
	
	$('.col-operation .btn-delete').on('click' , function() {
		event.preventDefault();
		var data = $(this).closest('td').data('data');
		if (data) {
			var id = data.id;
			$('<div></div>').appendTo('body')
			  .html('<div style="font-size: 18px;margin-top: 15;">确认删除这条记录？</div>')
			  .dialog({
			      modal: true, title: '提醒', zIndex: 10000, autoOpen: true,
			      width: 'auto', resizable: false,
			      buttons: {
			          "确定": function () {
			        	  $.ajax({
			        		url : 'deleteorder.html',  
			        		type : 'POST',
			        		dataType : 'json',
			        		data : {'id' : id},
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
	});
	
	$('.function-area .btn-create').on('click' , function() {
		var ele = $($(this).siblings('script[name=form-create]').html());
		addToDialog(ele);
		bind();
		dialog.dialog('open');
	});
	
	
	$('.dialog-form').on('click' , function() {
		calculate();
	});
	
	var date = $('.search-operation input[name=order-date]').val();
	$('.search-operation input[name=order-date]').datepicker();
	$('.search-operation input[name=order-date]').datepicker( "option", "dateFormat", "yy-mm-dd");
	if (date) {
		$('.search-operation input[name=order-date]').datepicker("setDate" , date);
	}
	
	
	$( ".search-operation input[name=name]" ).autocomplete({
      source: function( request, response ) {
          $.ajax( {
              url: "searchMember.html",
              dataType: "json",
              data: {
                term:encodeURIComponent(request.term) 
              },
              success: function( data ) {
                response( data );
              }
            } );
      },
      minLength: 2,
      select: function( event, ui ) {
    	  $( ".search-operation input[name=member-id]").val(ui.item.id );
      }
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
	    	window.location.href='order.html?' + newParamStr;
	    }
	});
	
	
	var doSearch = function() {
		var memberId = $('.search-content input[name=member-id]').val();
		var carIndex = $('.search-content input[name=car-index]').val();
		var orderDate = $('.search-content input[name=order-date]').val();
		var orderNumber = $('.search-content input[name=order-number]').val();
		var name = $('.search-content input[name=name]').val();
		var param = {};
		if (memberId && memberId.trim().length > 0 && name && name.trim().length > 0) {
			param.memberId = memberId.trim();
		}
		if (carIndex && carIndex.trim().length > 0) {
			param.carIndex = carIndex.trim();
		}
		if (orderDate && orderDate.trim().length > 0) {
			param.orderDate = orderDate.trim();
		}
		if (orderNumber && orderNumber.trim().length > 0) {
			param.orderNumber = orderNumber.trim();
		}
		
		if (!$.isEmptyObject(param)) {
			window.location.href='order.html?' + $.param(param);
		}
		else {
			window.location.href='order.html';
		}
	}
	
	$('.search-btns .btn-search').on('click' , function() {
		doSearch();
	});
	
	$('.search-btns .btn-cancel').on('click' , function() {
		clearSearchInput();
		doSearch();
	});
	
	var clearSearchInput = function() {
		$('.search-content input[name=member-id]').val('');
		$('.search-content input[name=car-index]').val('');
		$('.search-content input[name=order-date]').val('');
		$('.search-content input[name=order-number]').val('');
	}
	
	var addToDialog = function(ele) {
		$('.dialog-form').empty();
		$('.dialog-form').append(ele);
		$( ".form-item input[name=member-name]" ).autocomplete({
	      source: function( request, response ) {
	          $.ajax( {
	              url: "searchMember.html",
	              dataType: "json",
	              data: {
	                term:encodeURIComponent(request.term) 
	              },
	              success: function( data ) {
	                response( data );
	              }
	            } );
	      },
	      minLength: 2,
	      select: function( event, ui ) {
	    	  $('.dialog-form form input[name=member-id]').val(ui.item.id );
	    //	  $('.dialog-form form input[name=member-name]').val(ui.item.value);
	      }
	    });
	};
	
	var bind = function() {
		$('.btn-area .btns .btn-save').on('click' , function(event) {
			event.preventDefault();
			manageOrder();
		});
		
		$('.btn-area .btns .btn-cancel').on('click' , function(event) {
			event.preventDefault();
			dialog.dialog('close');
		});
		
		$('.detail-col-operation .action-delete').on('click' , function() {
			$(this).closest('.detail-cols').remove();
		});
		
		$('.detail-col-operation .action-add').on('click' , function() {
			var ele = $(this).closest('.detail-cols');
			var copyEle = $(this).closest('.detail-cols').clone(true);
			clearInput(copyEle);
			ele.after(copyEle);
		});
		
		var value = $('.dialog-form form input[name=order-date]').val();
		$('.dialog-form form input[name=order-date]').datepicker();
		$('.dialog-form form input[name=order-date]').datepicker( "option", "dateFormat", "yy-mm-dd");
		if (value) {
			$('.dialog-form form input[name=order-date]').datepicker("setDate" , value);
		}
	};
	
	var manageOrder = function() {
		var param = {};
		
		var orderId = $('.dialog-form form input[name=order-id]').val();
		var carIndex = $('.dialog-form form input[name=car-index]').val();
		var orderDate = $('.dialog-form form input[name=order-date]').val();
		var orderNumber = $('.dialog-form form input[name=order-number]').val();
		var zzFee = $('.dialog-form form input[name=zz-fee]').val();
		var jsFee = $('.dialog-form form input[name=js-fee]').val();
		var dsFee = $('.dialog-form form input[name=ds-fee]').val();
		var comments = $('.dialog-form form input[name=comments]').val();
		var memberId = $('.dialog-form form input[name=member-id]').val();
		
		if (zzFee) {
			zzFee = parseInt(zzFee * 100);
		}
		if (jsFee) {
			jsFee = parseInt(jsFee * 100);
		}
		if (dsFee) {
			dsFee = parseInt(dsFee * 100);
		}
		
		if (orderId != '') {
			param.orderId = orderId;
		}
		param.carIndex = carIndex;
		param.orderDate = orderDate;
		param.orderNumber = orderNumber;
		param.zzFee = zzFee;
		param.jsFee = jsFee;
		param.dsFee = dsFee;
		param.comments = comments;
		param.memberId = memberId;
		
		if (!checkOrderNumber(orderNumber)) {
			return;
		}
		
		var detailList = [];
		var valid = true;
		$('.dialog-form form .detail-item .detail-cols').each(function(index , e) {
			if (!checkOrderDetail($(e))) {
				valid = false;
				return;
			}
			
			var detailParam = {};
			if ($(e).find('input[name=detail-id]').val() != '') {
				detailParam.id= $(e).find('input[name=detail-id]').val();
			}
			detailParam.count= $(e).find('input[name=count]').val();
			detailParam.weight = $(e).find('input[name=weight]').val();
			detailParam.length = $(e).find('input[name=length]').val();
			detailParam.width = $(e).find('input[name=width]').val();
			detailParam.height = $(e).find('input[name=height]').val();
			detailParam.totalWeight = $(e).find('input[name=total-weight]').val();
			detailParam.totalVolumn = $(e).find('input[name=total-volumn]').val();
			
			if (detailParam.length) {
				detailParam.length = parseInt(detailParam.length * 10);
			}
			
			if (detailParam.width) {
				detailParam.width = parseInt(detailParam.width * 10);
			}
			
			if (detailParam.height) {
				detailParam.height = parseInt(detailParam.height * 10);
			}
			
			if (detailParam.weight) {
				detailParam.weight = parseInt(detailParam.weight * 1000);
			}
			
			detailList.push(detailParam);
		});
		
		if (!valid) {
			return;
		}
		param.detailList = JSON.stringify(detailList);
		
		  $.ajax({
			url : 'manageorder.html',  
			type : 'POST',
			dataType : 'json',
			data : param,
			success : function(result) {
				if (result.result) {
					window.location.reload(true);
				}
			}
		  });
	};
	
	var checkOrderDetail = function(e) {
		if (e.find('input[name=count]').val() != '' && e.find('input[name=weight]').val() 
				&& e.find('input[name=length]').val()  && e.find('input[name=width]').val() 
				 && e.find('input[name=height]').val()  && $(e).find('input[name=total-weight]').val()
				 && $(e).find('input[name=total-volumn]').val()) {
			return true;
		}
		else {
			return false;
		}
	};
	
	var clearInput = function(e) {
		e.find('input').each(function(index , ele) {
			$(ele).val('');
		});
	}
	
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
	
	var checkOrderNumber = function (orderNumber) {
		if (!(orderNumber && orderNumber.trim().length > 0)) {
			return false;
		}
		
		var items = orderNumber.split('-');
		if (items.length != 3) {
			return false;
		}
		
		var count = parseInt(items[2]);
		
		var total = 0;
		$('.dialog-form form .detail-item .detail-cols').each(function(index , e) {
			total += parseInt($(e).find('input[name=count]').val());
		});
		
		return total == count;
	}
	
	var calculate = function() {
		$('.dialog-form form .detail-item .detail-cols').each(function(index , e) {
			var detailParam = {};
			if ($(e).find('input[name=detail-id]').val() != '') {
				detailParam.id= $(e).find('input[name=detail-id]').val();
			}
			detailParam.count= $(e).find('input[name=count]').val();
			detailParam.weight = $(e).find('input[name=weight]').val();
			detailParam.length = $(e).find('input[name=length]').val();
			detailParam.width = $(e).find('input[name=width]').val();
			detailParam.height = $(e).find('input[name=height]').val();
			
			if (!$.trim(detailParam.count)) {
				return;
			}
			
			if ($.trim(detailParam.length) && $.trim(detailParam.width) && $.trim(detailParam.height)) {
				var volumn = parseInt($.trim(detailParam.length) * 10) * parseInt($.trim(detailParam.width) * 10) * parseInt($.trim(detailParam.height) * 10);			
				var totalVolumn = volumn * $.trim(detailParam.count);
				if (totalVolumn % 1000000 == 0) {
					totalVolumn = totalVolumn / 1000000;
				}
				else {
					totalVolumn = Math.ceil(totalVolumn / 1000000.0);
				}
				totalVolumn = totalVolumn / 1000.0;
				totalVolumn.toFixed(3);
				$(e).find('input[name=total-volumn]').val(totalVolumn);
			}
			
			if ($.trim(detailParam.weight)) {
				var weight = parseInt($.trim(detailParam.weight) * 1000);
				var totalWeight = weight * $.trim(detailParam.count);
				if(totalWeight % 1000 == 0) {
					totalWeight = totalWeight / 1000;
				}
				else {
					totalWeight = (totalWeight / 1000.0).toFixed(1);
				}
				$(e).find('input[name=total-weight]').val(totalWeight);
			}
			
			
			if (detailParam.length) {
				detailParam.length = parseInt(detailParam.length * 10);
			}
			
			if (detailParam.width) {
				detailParam.width = parseInt(detailParam.width * 10);
			}
			
			if (detailParam.height) {
				detailParam.height = parseInt(detailParam.height * 10);
			}
			
			if (detailParam.weight) {
				detailParam.weight = parseInt(detailParam.weight * 1000);
			}
		});
	}
});