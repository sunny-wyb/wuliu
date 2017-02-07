$(function() {
	$('.btn-download').on('click' , function() {
		var form = $("<form>");
		form.attr('style', 'display:none');
		form.attr('target', '');
		form.attr('method', 'post');
		form.attr('action', 'download.html');

		
		var param = getSearchParam();
		
		if (param.memberId) {
			var memberIdInput = $('<input>');
			memberIdInput.attr('type', 'hidden');
			memberIdInput.attr('name', 'memberId');
			memberIdInput.attr('value', param.memberId);
			form.append(memberIdInput);
		}
		
		if (param.carIndex) {
			var carIndexInput = $('<input>');
			carIndexInput.attr('type', 'hidden');
			carIndexInput.attr('name', 'carIndex');
			carIndexInput.attr('value', param.carIndex);
			form.append(carIndexInput);
		}
		
		if (param.orderDate) {
			var orderDateInput = $('<input>');
			orderDateInput.attr('type', 'hidden');
			orderDateInput.attr('name', 'orderDate');
			orderDateInput.attr('value', param.orderDate);
			form.append(orderDateInput);
		}
		
		if (param.orderNumber) {
			var orderNumberInput = $('<input>');
			orderNumberInput.attr('type', 'hidden');
			orderNumberInput.attr('name', 'orderNumber');
			orderNumberInput.attr('value', param.orderNumber);
			form.append(orderNumberInput);
		}

		$('body').append(form);
		form.submit();
		form.remove();
	});
	
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
    	  $( ".search-operation input[name=name]").val(ui.item.value);
      }
    });
	
	var date = $('.search-operation input[name=order-date]').val();
	$('.search-operation input[name=order-date]').datepicker();
	$('.search-operation input[name=order-date]').datepicker( "option", "dateFormat", "yy-mm-dd");
	if (date) {
		$('.search-operation input[name=order-date]').datepicker("setDate" , date);
	}
	
	var getSearchParam = function() {
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
		return param;
	}
	
	var doSearch = function() {
		
		var param = getSearchParam();
		
		if (!$.isEmptyObject(param)) {
			window.location.href='mergedorder.html?' + $.param(param);
		}
		else {
			window.location.href='mergedorder.html';
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
	};
	
	var totalPage = $('.pagination').data('total-page');
	var currentPage = $('.pagination').data('current-page');
	$(".pagination").Page({
	    totalPages: totalPage,//分页总数
	    liNums: 7,
	    currentPage : currentPage,
	    activeClass: 'activP', //active class style
	    callBack : function(page){
	    	var newParamStr = addParamToUrl({'page':page});
	    	window.location.href='mergedorder.html?' + newParamStr;
	    }
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