$(function() {
	var dialog = $('.dialog-form').dialog({
		width:530
	});
	dialog.dialog('close');
	
	
	$('.btn-area .btns .btn-save').on('click' , function(event) {
		event.preventDefault();
		
		var param = {};
		
		var orderId = $('.dialog-form form order-id').val();
		var carIndex = $('.dialog-form form input[name=car-index]').val();
		var orderDate = $('.dialog-form form input[name=order-data]').val();
		var orderIndex = $('.dialog-form form input[name=order-index]').val();
		var zzFee = $('.dialog-form form input[name=zz-fee]').val();
		var jsFee = $('.dialog-form form input[name=js-fee]').val();
		var dsFee = $('.dialog-form form input[name=ds-fee]').val();
		var comments = $('.dialog-form form input[name=comments]').val();
		var memberId = $('.dialog-form form input[name=member-id]').val();
		
		param.orderId = orderId;
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
			var detailParam = {};
			detailParam.detailId = $(e).find('input[name=detail-id]').val();
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
		console.log(param);
		console.log(JSON.stringify(param));
		
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
	});
	
	$('.function-area .btn-create').on('click' , function() {
		dialog.dialog('open');
	});
	
	$('.col-operation .btn-view').on('click' , function() {
		var ele = $(this).siblings('script[name=offerGroup]').html();
		test(ele);
		dialog.dialog('open');
		$(this).closest('td').data('data').wuliuOrderDetailModels.forEach(function(e , index) {
		});
	});
	
	var test = function(ele) {
		$('form .detail-item').empty();
		$('form .detail-item').append(ele);
	};
	
});