/**
 * 
 */

(function ($) {
  $.show_simple_dialog = function (title , content) {
	$('<div></div>').appendTo('body')
	  .html('<p style="font-size: 18px;margin-top: 15;width:100%;height:100%;text-align:center;vertical-align:middle;">' + content + '</p>')
	  .dialog({
	      modal: true, title: title, zIndex: 10000, autoOpen: true,
	      resizable: false, width:300,
	      close: function (event, ui) {
	          $(this).remove();
	      }
	});
  };
})(jQuery);