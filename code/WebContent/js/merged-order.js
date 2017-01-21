$(function() {
	$('.btn-download').on('click' , function() {
		var form = $("<form>");
		form.attr('style', 'display:none');
		form.attr('target', '');
		form.attr('method', 'post');
		form.attr('action', 'download.html');

		$('body').append(form);

		form.submit();
		form.remove();
	});
});