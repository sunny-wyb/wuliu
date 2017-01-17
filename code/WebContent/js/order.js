$(function() {
	var dialog = $('.dialog-form').dialog({
		width:530
	});
	dialog.dialog('close');
	
	$('.col-operation .btn-view').on('click' , function() {
		var ele = $($(this).siblings('script[name=form-group]').html());
		addToDialog(ele);
		bind();
		dialog.dialog('open');
	});
	
	$('.function-area .btn-create').on('click' , function() {
		var ele = $($(this).siblings('script[name=form-create]').html());
		addToDialog(ele);
		bind();
		dialog.dialog('open');
	});
	
	$('.detail-col-operation .action-add').on('click' , function() {
		
	});
	
	
	
	var addToDialog = function(ele) {
		$('.dialog-form').empty();
		$('.dialog-form').append(ele);
		$( ".form-item input[name=member-name]" ).autocomplete({
	      source: 'searchMember.html',
	      minLength: 2,
	      select: function( event, ui ) {
	    	  $('.dialog-form form input[name=member-id]').val(ui.item.id );
	    	  $('.dialog-form form input[name=member-name]').val(ui.item.value);
	    	  console.log( "Selected: " + ui.item.value + " aka " + ui.item.id );
	      }
	    } );
	};
	
	var bind = function() {
		$('.btn-area .btns .btn-save').on('click' , function(event) {
			event.preventDefault();
			manageOrder();
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
	};
	
	var manageOrder = function() {
		var param = {};
		
		var orderId = $('.dialog-form form input[name=order-id]').val();
		var carIndex = $('.dialog-form form input[name=car-index]').val();
		var orderDate = $('.dialog-form form input[name=order-data]').val();
		var orderIndex = $('.dialog-form form input[name=order-index]').val();
		var zzFee = $('.dialog-form form input[name=zz-fee]').val();
		var jsFee = $('.dialog-form form input[name=js-fee]').val();
		var dsFee = $('.dialog-form form input[name=ds-fee]').val();
		var comments = $('.dialog-form form input[name=comments]').val();
		var memberId = $('.dialog-form form input[name=member-id]').val();
		
		if (orderId != '') {
			param.orderId = orderId;
		}
		param.carIndex = carIndex;
		param.orderDate = orderDate;
		param.orderIndex = orderIndex;
		param.zzFee = zzFee;
		param.jsFee = jsFee;
		param.dsFee = dsFee;
		param.comments = comments;
		param.memberId = memberId;
		
		var detailList = [];
		$('.dialog-form form .detail-item .detail-cols').each(function(index , e) {
			if (!checkOrderDetail($(e))) {
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
			detailList.push(detailParam);
		});
		
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
});