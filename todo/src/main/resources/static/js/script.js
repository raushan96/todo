$(document).on('click', '#deleteToDoItem', function () {

	var r = confirm("Do you want to delete selected item?");
	if (r == true) {
		var itemId = $(this).attr("data-value");
		$('<form method="post"><input type="hidden" name="id" value="' + itemId + '" /></form>').appendTo('body').submit();	    
	}
});
