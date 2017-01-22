$(function() {
	$('.btn-download').on('click' , function() {
		var form = $("<form>");
		form.attr('style', 'display:none');
		form.attr('target', '');
		form.attr('method', 'post');
		form.attr('action', 'download.html');

		
		var param = getParam();
		
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
		
		if (param.orderIndex) {
			var orderIndexInput = $('<input>');
			orderIndexInput.attr('type', 'hidden');
			orderIndexInput.attr('name', 'orderIndex');
			orderIndexInput.attr('value', param.orderIndex);
			form.append(orderIndexInput);
		}

		$('body').append(form);
		form.submit();
		form.remove();
	});
	
	$( ".search-operation input[name=name]" ).autocomplete({
      source: 'searchMember.html',
      minLength: 2,
      select: function( event, ui ) {
    	  $( ".search-operation input[name=member-id]").val(ui.item.id );
    	  $( ".search-operation input[name=name]").val(ui.item.value);
      }
    });
	
	var getParam = function() {
		var memberId = $('.search-content input[name=member-id]').val();
		var carIndex = $('.search-content input[name=car-index]').val();
		var orderDate = $('.search-content input[name=order-date]').val();
		var orderIndex = $('.search-content input[name=order-index]').val();
		var param = {};
		if (memberId && memberId.trim().length > 0) {
			param.memberId = memberId.trim();
		}
		if (carIndex && carIndex.trim().length > 0) {
			param.carIndex = carIndex.trim();
		}
		if (orderDate && orderDate.trim().length > 0) {
			param.orderDate = orderDate.trim();
		}
		if (orderIndex && orderIndex .trim().length > 0) {
			param.orderIndex= orderIndex.trim();
		}
		return param;
	}
	
	var doSearch = function() {
		
		var param = getParam();
		window.location.href='mergedorder.html?' + encodeURI($.param(param));
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
		$('.search-content input[name=order-index]').val('');
	}
});