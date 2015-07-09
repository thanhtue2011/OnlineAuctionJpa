function Xoa(id){
	var dialog = $("<div title='Block'>Are you sure Block "+id+" ?</div>").dialog({
		autoOpen: false,
        modal: true,
    buttons: {
    	 "No":  function() {
             dialog.dialog('close');
         },
        "Yes": function() {
        	dialog.dialog('close');
        	if(document.getElementById(id).submit())
        		alert('done');
        	}
       
       
    }
});
	dialog.dialog('open');
}
